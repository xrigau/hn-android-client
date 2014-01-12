package com.xrigau.hnandroid.util;

public class TaskFragmentNotAvailableException extends IllegalStateException {
    public TaskFragmentNotAvailableException() {
        super("TaskFragment is not attached to the Activity, so tasks cannot be executed");
    }
}
