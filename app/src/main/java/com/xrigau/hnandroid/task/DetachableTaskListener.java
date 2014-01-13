package com.xrigau.hnandroid.task;

public interface DetachableTaskListener<T> {
    void onLoadFinished(TaskResult<T> taskResult);
    boolean isAttached();
}
