package com.xrigau.hnandroid.util;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class LoadingAdapter extends BaseAdapter implements HNAdapter {

    private static final int POSITIONS_AHEAD_TO_START_LOADING = 6;
    private final List<Integer> alreadyRequestedPositions = new ArrayList<Integer>();

    private OnNextPageRequestedListener onNextPageRequestListener;

    @Override
    public final View getView(int position, View convertView, ViewGroup parent) {
        if (isCloseToEnd(position) && notAlreadyLoaded(position) && validListener()) {
            onNextPageRequestListener.onNextPageRequested();
        }

        if (convertView == null) {
            convertView = createView(position, parent);
        }
        return setUpView(convertView, position);
    }

    private boolean isCloseToEnd(int position) {
        return position == getCount() - POSITIONS_AHEAD_TO_START_LOADING;
    }

    private boolean notAlreadyLoaded(int position) {
        return !alreadyRequestedPositions.contains(position);
    }

    private boolean validListener() {
        return onNextPageRequestListener != null;
    }

    public void setOnNextPageRequestedListener(OnNextPageRequestedListener onNextPageRequestListener) {
        this.onNextPageRequestListener = onNextPageRequestListener;
    }
}
