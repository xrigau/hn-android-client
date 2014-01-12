package com.xrigau.hnandroid.presentation.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.util.Log;

import com.xrigau.hnandroid.BuildConfig;
import com.xrigau.hnandroid.core.task.BaseTask;
import com.xrigau.hnandroid.presentation.activity.HNActivity;

public class HNFragment extends Fragment {

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    protected <T> void execute(BaseTask<T> task, DetachableTaskListener<T> listener) {
        getHNActivity().execute(task, listener);
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

}
