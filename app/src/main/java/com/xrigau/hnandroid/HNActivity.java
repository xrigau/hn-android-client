package com.xrigau.hnandroid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.xrigau.hnandroid.core.task.BaseTask;
import com.xrigau.hnandroid.task.*;
import com.xrigau.hnandroid.util.Navigator;

public abstract class HNActivity extends Activity implements TaskResultDelegate {

    private TaskFragment taskFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TaskFragment fragment = findTaskFragment();
        if (alreadyAdded(fragment)) {
            taskFragment = fragment;
            return;
        }
        addTaskFragment();
    }

    private TaskFragment findTaskFragment() {
        String tag = getString(R.string.fragment_task_tag);
        return (TaskFragment) getFragmentManager().findFragmentByTag(tag);
    }

    private boolean alreadyAdded(TaskFragment fragment) {
        return fragment != null;
    }

    private void addTaskFragment() {
        taskFragment = new TaskFragment();
        getFragmentManager().beginTransaction().add(taskFragment, getString(R.string.fragment_task_tag)).commit();
    }

    public <T> void execute(BaseTask<T> task) {
        if (taskFragmentUnavailable()) {
            delegateResult(new TaskResult<T>(new TaskFragmentNotAvailableException()));
            return;
        }
        taskFragment.execute(task);
    }

    private boolean taskFragmentUnavailable() {
        return taskFragment == null;
    }

    public void toast(int stringResource) {
        Toast.makeText(this, stringResource, Toast.LENGTH_LONG).show();
    }

    protected Navigator navigate() {
        return new Navigator(this);
    }
}
