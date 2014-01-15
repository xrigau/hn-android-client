package com.xrigau.hnandroid.task;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.LruCache;

import com.xrigau.hnandroid.BuildConfig;
import com.xrigau.hnandroid.core.task.BaseTask;
import com.xrigau.hnandroid.core.task.TaskExecutor;

import java.util.ArrayList;
import java.util.List;

public class TaskFragment extends Fragment implements HNAsyncTask.OnAsyncTaskFinishedListener {

    private static final int RESPONSE_CACHE_SIZE = 20;
    private final static LruCache<BaseTask<?>, Object> RESPONSE_CACHE = new LruCache<BaseTask<?>, Object>(RESPONSE_CACHE_SIZE);
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
        T result = (T) RESPONSE_CACHE.get(task);
        if (isCached(result) && delegate != null) {
            delegate.delegateResult(new TaskResult<T>(result, null));
            return;
        }
        if (RUNNING_TASKS.contains(task)) {
            return;
        }

        RUNNING_TASKS.add(task);
        new HNAsyncTask<T>(task, RESPONSE_CACHE, this).execute(executor);
    }

    private boolean isCached(Object result) {
        return result != null;
    }

    @Override
    public void onAsyncTaskFinished(BaseTask<?> task, TaskResult<?> taskResult) {
        RUNNING_TASKS.remove(task);
        if (delegate != null) {
            delegate.delegateResult(taskResult);
        }
    }
}
