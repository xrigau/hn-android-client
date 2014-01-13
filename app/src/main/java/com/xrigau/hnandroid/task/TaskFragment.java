package com.xrigau.hnandroid.task;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;

import com.xrigau.hnandroid.BuildConfig;
import com.xrigau.hnandroid.core.task.BaseTask;
import com.xrigau.hnandroid.core.task.TaskExecutor;

import java.util.HashMap;
import java.util.Map;

import retrofit.RetrofitError;

public class TaskFragment extends Fragment {

    private static final int CACHE_SIZE = 20;
    private final static LruCache<BaseTask<?>, Object> RESPONSE_CACHE = new LruCache<BaseTask<?>, Object>(CACHE_SIZE);
    private final static Map<BaseTask<?>, DetachableTaskListener> TASKS_LISTENERS = new HashMap<BaseTask<?>, DetachableTaskListener>();

    private final TaskExecutor executor = new TaskExecutor(BuildConfig.HTTP_CLIENT, BuildConfig.DEBUG);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public <T> void execute(final BaseTask<T> task, DetachableTaskListener<T> listener) {
        T result = (T) RESPONSE_CACHE.get(task);
        if (isCached(result)) {
            listener.onLoadFinished(new TaskResult<T>(result, null));
            return;
        }
        boolean alreadyRunning = TASKS_LISTENERS.containsKey(task);
        updateListener(task, listener);
        if (alreadyRunning) {
            return;
        }

        new HNAsyncTask<T>(task).execute(executor);
    }

    private boolean isCached(Object result) {
        return result != null;
    }

    private <T> void updateListener(BaseTask<T> task, DetachableTaskListener<T> listener) {
        TASKS_LISTENERS.put(task, listener);
    }

    private static final class HNAsyncTask<T> extends AsyncTask<TaskExecutor, Throwable, TaskResult<T>> {

        private final BaseTask<T> task;

        private HNAsyncTask(BaseTask<T> task) {
            this.task = task;
        }

        @Override
        protected TaskResult<T> doInBackground(TaskExecutor... params) {
            TaskExecutor executor = params[0];
            T result = null;
            Throwable error = null;
            try {
                result = executor.execute(task);
            } catch (RetrofitError e) {
                log(e);
                error = e;
            }
            if (zeroBytesResponse(result)) {
                error = new NullPointerException("Result is null, means response came with 0 bytes!");
            }
            return new TaskResult<T>(result, error);
        }

        private boolean zeroBytesResponse(T result) {
            return result == null;
        }

        @Override
        protected void onPostExecute(TaskResult<T> taskResult) {
            DetachableTaskListener<T> listener = TASKS_LISTENERS.remove(task);
            if (isSuccess(taskResult)) {
                RESPONSE_CACHE.put(task, taskResult.result);
            }

            if (listener.isAttached()) {
                listener.onLoadFinished(taskResult);
            }
        }

        private boolean isSuccess(TaskResult taskResult) {
            return taskResult.result != null && taskResult.error == null;
        }

        private void log(Throwable error) {
            if (BuildConfig.DEBUG) {
                Log.e("HN", "An error occurred", error);
            }
        }
    }

}
