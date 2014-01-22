package com.xrigau.hnandroid.task;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

import com.xrigau.hnandroid.BuildConfig;
import com.xrigau.hnandroid.core.task.BaseTask;
import com.xrigau.hnandroid.core.task.TaskExecutor;

import java.util.ArrayList;
import java.util.List;

public class TaskFragment extends Fragment implements HNAsyncTask.OnAsyncTaskFinishedListener {

    private final static List<BaseTask<?>> RUNNING_TASKS = new ArrayList<BaseTask<?>>();

    private final TaskExecutor executor = new TaskExecutor(BuildConfig.HTTP_CLIENT, BuildConfig.DEBUG);

    private TaskResultDelegate delegate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        delegate = (TaskResultDelegate) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        delegate = null;
    }

    public <T> void execute(final BaseTask<T> task) {
        if (isAlreadyRunning(task)) {
            return;
        }

        RUNNING_TASKS.add(task);
        new HNAsyncTask<T>(task, this).executeWith(executor);
    }

    private boolean validDelegte() {
        return delegate != null;
    }

    private <T> boolean isAlreadyRunning(BaseTask<T> task) {
        return RUNNING_TASKS.contains(task);
    }

    @Override
    public void onAsyncTaskFinished(BaseTask<?> task, TaskResult<?> taskResult) {
        RUNNING_TASKS.remove(task);

        if (validDelegte()) {
            delegate.onResult(task, taskResult);
        }
    }
}
