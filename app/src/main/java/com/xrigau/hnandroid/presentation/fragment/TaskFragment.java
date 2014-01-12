package com.xrigau.hnandroid.presentation.fragment;

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
        Object result = RESPONSE_CACHE.get(task);
        if (isCached(result)) {
            listener.onLoadFinished((T) result, null);
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

    private static final class HNAsyncTask<T> extends AsyncTask<TaskExecutor, Throwable, T> {

        private final BaseTask<T> task;

        private HNAsyncTask(BaseTask<T> task) {
            this.task = task;
        }

        @Override
        protected T doInBackground(TaskExecutor... params) {
            TaskExecutor executor = params[0];
            try {
                return executor.execute(task);
            } catch (RetrofitError e) {
                Log.w("HN", "Ingored exception - " + e.getLocalizedMessage());
                publishProgress(e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(T result) {
            DetachableTaskListener<T> listener = TASKS_LISTENERS.remove(task);
            if (isError(result)) {
                return;
            }

            RESPONSE_CACHE.put(task, result);
            if (listener.isAttached()) {
                listener.onLoadFinished(result, null);
            }
        }

        private boolean isError(T result) {
            return result == null;
        }

        @Override
        protected void onProgressUpdate(Throwable... error) {
            DetachableTaskListener<T> listener = getListener(task);
            if (listener.isAttached()) {
                listener.onLoadFinished(null, error[0]);
            }
        }

        private <T> DetachableTaskListener<T> getListener(BaseTask<T> task) {
            return TASKS_LISTENERS.get(task);
        }
    }

}
