package com.xrigau.hnandroid;

import android.app.Fragment;
import android.util.Log;

import com.xrigau.hnandroid.core.task.BaseTask;
import com.xrigau.hnandroid.util.Navigator;

public class HNFragment extends Fragment {

    protected <T> void execute(BaseTask<T> task) {
        getHNActivity().execute(task);
    }

    private HNActivity getHNActivity() {
        return ((HNActivity) getActivity());
    }

    protected void log(Throwable error) {
        if (BuildConfig.DEBUG) {
            Log.e("HN", "An error occurred", error);
        }
    }

    protected void toast(int stringResource) {
        getHNActivity().toast(stringResource);
    }

    protected Navigator navigate() {
        return new Navigator(getActivity());
    }

    protected void clearCache() {
        getHNActivity().clearCache();
    }
}
