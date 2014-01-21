package com.xrigau.hnandroid.util;

import android.app.ActionBar;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

import com.xrigau.hnandroid.views.ObservableScrollView;

public class ActionBarFadeHelper implements ObservableScrollView.OnScrollChangedListener {

    private final ActionBar actionBar;
    private final int maxScrollableHeight;
    private final Drawable actionBarDrawable;

    public ActionBarFadeHelper(ActionBar actionBar, int maxScrollableHeight, int actionBarHeight, int color) {
        this.actionBar = actionBar;
        this.maxScrollableHeight = maxScrollableHeight - actionBarHeight;
        this.actionBarDrawable = new ColorDrawable(color);
        init();
    }

    private void init() {
        actionBarDrawable.setAlpha(0);
        actionBar.setBackgroundDrawable(actionBarDrawable);
    }

    @Override
    public void onVerticalScrollChanged(int offsetY) {
        offsetY = clampOffset(offsetY);
        setAlpha((float) offsetY / (float) maxScrollableHeight);
    }

    private int clampOffset(int offsetY) {
        return Math.min(Math.max(offsetY, 0), maxScrollableHeight);
    }

    private void setAlpha(float alpha) {
        actionBarDrawable.setAlpha((int) (alpha * 255));
    }
}
