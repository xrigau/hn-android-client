package com.xrigau.hnandroid;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.LruCache;
import android.widget.Toast;

import com.xrigau.hnandroid.core.task.BaseTask;
import com.xrigau.hnandroid.task.TaskFragment;
import com.xrigau.hnandroid.task.TaskFragmentNotAvailableException;
import com.xrigau.hnandroid.task.TaskResult;
import com.xrigau.hnandroid.task.TaskResultDelegate;
import com.xrigau.hnandroid.util.Navigator;

public abstract class HNActivity extends Activity implements TaskResultDelegate {

    private static final int RESPONSE_CACHE_SIZE = 20;
    private final static LruCache<BaseTask<?>, Object> RESPONSE_CACHE = new LruCache<BaseTask<?>, Object>(RESPONSE_CACHE_SIZE);

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

    public void toast(int stringResource) {
        Toast.makeText(this, stringResource, Toast.LENGTH_LONG).show();
    }

    protected boolean isFragmentAttached(Fragment fragment) {
        return fragment != null && fragment.isAdded();
    }

    public <T> void execute(BaseTask<T> task) {
        T result = (T) RESPONSE_CACHE.get(task);
        if (isCached(result)) {
            deliverResult(new TaskResult<T>(result));
            return;
        }

        if (taskFragmentUnavailable()) {
            deliverResult(new TaskResult<T>(new TaskFragmentNotAvailableException()));
            return;
        }
        taskFragment.execute(task);
    }

    private boolean isCached(Object result) {
        return result != null;
    }

    private boolean taskFragmentUnavailable() {
        return taskFragment == null;
    }

    @Override
    public void onResult(BaseTask task, TaskResult taskResult) {
        if (isSuccess(taskResult)) {
            RESPONSE_CACHE.put(task, taskResult.result);
        }
        deliverResult(taskResult);
    }

    private boolean isSuccess(TaskResult taskResult) {
        return taskResult.result != null && taskResult.error == null;
    }

    protected abstract <T> void deliverResult(TaskResult<T> taskResult);

    protected void clearCache() {
        RESPONSE_CACHE.evictAll();
    }
}
