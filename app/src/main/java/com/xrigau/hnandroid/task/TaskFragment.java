package com.xrigau.hnandroid.task;

import android.app.Fragment;
import android.os.Bundle;
import android.util.LruCache;

import com.xrigau.hnandroid.BuildConfig;
import com.xrigau.hnandroid.core.task.BaseTask;
import com.xrigau.hnandroid.core.task.TaskExecutor;

import java.util.HashMap;
import java.util.Map;

public class TaskFragment extends Fragment {

    private static final int RESPONSE_CACHE_SIZE = 20;
    private final static LruCache<BaseTask<?>, Object> RESPONSE_CACHE = new LruCache<BaseTask<?>, Object>(RESPONSE_CACHE_SIZE);
    private final static Map<BaseTask<?>, DetachableTaskListener> TASKS_LISTENERS = new HashMap<BaseTask<?>, DetachableTaskListener>();

    private final TaskExecutor executor = new TaskExecutor(BuildConfig.HTTP_CLIENT, BuildConfig.DEBUG);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public <T> void execute(final BaseTask<T> task, DetachableTaskListener<T> listener) {
        T result = (T) RESPONSE_CACHE.get(task);
        if (isCached(result)) {
            listener.onLoadFinished(new TaskResult<T>(result, null));
            return;
        }
        boolean alreadyRunning = TASKS_LISTENERS.containsKey(task);
        updateListener(task, listener);
        if (alreadyRunning) {
            return;
        }

        new HNAsyncTask<T>(task, RESPONSE_CACHE, TASKS_LISTENERS).execute(executor);
    }

    private boolean isCached(Object result) {
        return result != null;
    }

    private <T> void updateListener(BaseTask<T> task, DetachableTaskListener<T> listener) {
        TASKS_LISTENERS.put(task, listener);
    }

}
