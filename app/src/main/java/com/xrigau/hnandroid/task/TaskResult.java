package com.xrigau.hnandroid.task;

public class TaskResult<T> {
    public final T result;
    public final Throwable error;

    public TaskResult(Throwable error) {
        this.result = null;
        this.error = error;
    }

    TaskResult(T result, Throwable error) {
        this.result = result;
        this.error = error;
    }
}
