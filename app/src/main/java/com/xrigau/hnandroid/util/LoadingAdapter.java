package com.xrigau.hnandroid.util;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Subscriber;
import rx.observers.Subscribers;

public abstract class LoadingAdapter<T> extends BaseAdapter implements HNAdapter {

    private static final int POSITIONS_AHEAD_TO_START_LOADING = 6;

    private final List<Integer> alreadyRequestedPositions = new ArrayList<Integer>();
    private final Subscriber<T> paginationSubscriber;

    protected LoadingAdapter(Observer<? super T> observer) {
        paginationSubscriber = Subscribers.from(observer);
    }

    @Override
    public final View getView(int position, View convertView, ViewGroup parent) {
        if (isCloseToEnd(position) && notAlreadyLoaded(position)) {
            paginationSubscriber.onNext(getNextPage());
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

    protected abstract T getNextPage();

}
