package com.xrigau.hnandroid.task;

import android.os.AsyncTask;
import android.util.Log;

import com.xrigau.hnandroid.BuildConfig;
import com.xrigau.hnandroid.core.task.BaseTask;
import com.xrigau.hnandroid.core.task.TaskExecutor;

import retrofit.RetrofitError;

final class HNAsyncTask<T> extends AsyncTask<TaskExecutor, Throwable, TaskResult<T>> {

    interface OnAsyncTaskFinishedListener {
        void onAsyncTaskFinished(BaseTask<?> task, TaskResult<?> taskResult);
    }

    private final BaseTask<T> task;
    private final OnAsyncTaskFinishedListener listener;

    HNAsyncTask(BaseTask<T> task, OnAsyncTaskFinishedListener listener) {
        this.task = task;
        this.listener = listener;
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
        listener.onAsyncTaskFinished(task, taskResult);
    }

    private void log(Throwable error) {
        if (BuildConfig.DEBUG) {
            Log.e("HN", "An error occurred", error);
        }
    }
}
