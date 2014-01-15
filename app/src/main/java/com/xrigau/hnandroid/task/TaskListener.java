package com.xrigau.hnandroid.task;

public interface TaskListener<T> {
    void onLoadFinished(TaskResult<T> taskResult);
}
