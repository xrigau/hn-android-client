package com.xrigau.hnandroid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.xrigau.hnandroid.core.task.BaseTask;
import com.xrigau.hnandroid.task.DetachableTaskListener;
import com.xrigau.hnandroid.task.TaskFragment;
import com.xrigau.hnandroid.task.TaskFragmentNotAvailableException;
import com.xrigau.hnandroid.task.TaskResult;

public class HNActivity extends Activity {

    private TaskFragment taskFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addTaskFragment();
    }

    private void addTaskFragment() {
        TaskFragment fragment = findTaskFragment();
        if (fragment != null) {
            taskFragment = fragment;
            return;
        }
        taskFragment = new TaskFragment();
        getFragmentManager().beginTransaction().add(taskFragment, getString(R.string.fragment_task_tag)).commit();
    }

    private TaskFragment findTaskFragment() {
        String tag = getString(R.string.fragment_task_tag);
        return (TaskFragment) getFragmentManager().findFragmentByTag(tag);
    }

    public <T> void execute(BaseTask<T> task, DetachableTaskListener<T> listener) {
        if (taskFragment == null) {
            listener.onLoadFinished(new TaskResult<T>(new TaskFragmentNotAvailableException()));
            return;
        }
        taskFragment.execute(task, listener);
    }

    public void toast(int stringResource) {
        Toast.makeText(this, stringResource, Toast.LENGTH_LONG).show();
    }
}
