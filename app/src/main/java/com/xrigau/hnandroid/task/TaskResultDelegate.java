package com.xrigau.hnandroid.task;

public interface TaskResultDelegate<T> {
    void delegateResult(TaskResult<T> taskResult);
}
