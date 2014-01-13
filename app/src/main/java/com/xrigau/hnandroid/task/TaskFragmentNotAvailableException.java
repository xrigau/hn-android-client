package com.xrigau.hnandroid.task;

public class TaskFragmentNotAvailableException extends IllegalStateException {
    public TaskFragmentNotAvailableException() {
        super("TaskFragment is not attached to the Activity, so tasks cannot be executed");
    }
}
