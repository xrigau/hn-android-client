package com.xrigau.hnandroid.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

public class ObservableScrollView extends ScrollView {

    private final List<OnScrollChangedListener> observers = new ArrayList<OnScrollChangedListener>();

    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ObservableScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        for (OnScrollChangedListener listener : observers) {
            listener.onVerticalScrollChanged(t);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        post(new Runnable() {
            @Override
            public void run() {
                for (OnScrollChangedListener listener : observers) {
                    listener.onVerticalScrollChanged(getScrollY());
                }
            }
        });
    }

    public void addOnScrollChangedListener(OnScrollChangedListener onScrollChangedListener) {
        observers.add(onScrollChangedListener);
    }

    public void removeOnScrollChangedListener(OnScrollChangedListener onScrollChangedListener) {
        observers.remove(onScrollChangedListener);
    }

    public static interface OnScrollChangedListener {
        public void onVerticalScrollChanged(int offsetY);
    }
}
