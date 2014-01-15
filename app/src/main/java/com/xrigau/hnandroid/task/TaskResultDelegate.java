package com.xrigau.hnandroid.task;

import com.xrigau.hnandroid.core.task.BaseTask;

public interface TaskResultDelegate<T> {
    <T> void onResult(BaseTask<T> task, TaskResult<T> taskResult);
}
