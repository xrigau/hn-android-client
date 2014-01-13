package com.xrigau.hnandroid.task;

import android.os.AsyncTask;
import android.util.Log;
import android.util.LruCache;

import com.xrigau.hnandroid.BuildConfig;
import com.xrigau.hnandroid.core.task.BaseTask;
import com.xrigau.hnandroid.core.task.TaskExecutor;

import java.util.Map;

import retrofit.RetrofitError;

final class HNAsyncTask<T> extends AsyncTask<TaskExecutor, Throwable, TaskResult<T>> {

    private final BaseTask<T> task;
    private final LruCache<BaseTask<?>, Object> responseCache;
    private final Map<BaseTask<?>, DetachableTaskListener> tasksListeners;

    HNAsyncTask(BaseTask<T> task, LruCache<BaseTask<?>, Object> responseCache, Map<BaseTask<?>, DetachableTaskListener> tasksListeners) {
        this.task = task;
        this.responseCache = responseCache;
        this.tasksListeners = tasksListeners;
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
        DetachableTaskListener<T> listener = tasksListeners.remove(task);
        if (isSuccess(taskResult)) {
            responseCache.put(task, taskResult.result);
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
