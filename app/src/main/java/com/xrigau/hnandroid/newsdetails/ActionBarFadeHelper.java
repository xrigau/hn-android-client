package com.xrigau.hnandroid.newsdetails;

import android.app.ActionBar;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.xrigau.hnandroid.views.ObservableScrollView;

class ActionBarFadeHelper implements ObservableScrollView.OnScrollChangedListener {

    private static final int OPAQUE = 255;
    private static final int TRANSPARENT = 0;
    private static final int MIN_OFFSET = 0;
    private static final float MAX_OPACITY = 0.80f;

    private final ActionBar actionBar;
    private final View systemBarTintOverlay;
    private final int maxScrollableHeight;
    private final Drawable actionBarDrawable;

    ActionBarFadeHelper(ActionBar actionBar, View systemBarTintOverlay, int maxScrollableHeight, int actionBarHeight, int color) {
        this.actionBar = actionBar;
        this.systemBarTintOverlay = systemBarTintOverlay;
        this.maxScrollableHeight = maxScrollableHeight - actionBarHeight;
        this.actionBarDrawable = new ColorDrawable(color);
        init();
    }

    private void init() {
        actionBarDrawable.setAlpha(TRANSPARENT);
        actionBar.setBackgroundDrawable(actionBarDrawable);
    }

    @Override
    public void onVerticalScrollChanged(int offsetY) {
        offsetY = clampOffset(offsetY);
        float alpha = (float) offsetY / (float) maxScrollableHeight;
        setAlpha(alpha * MAX_OPACITY);
    }

    private int clampOffset(int offsetY) {
        return Math.min(Math.max(offsetY, MIN_OFFSET), maxScrollableHeight);
    }

    private void setAlpha(float alpha) {
        actionBarDrawable.setAlpha((int) (alpha * OPAQUE));
        systemBarTintOverlay.setAlpha(alpha);
    }
}
