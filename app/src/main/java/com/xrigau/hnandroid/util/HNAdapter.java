package com.xrigau.hnandroid.util;

import android.view.View;
import android.view.ViewGroup;

public interface HNAdapter {
    public View createView(int position, ViewGroup parent);

    public View setUpView(View view, int position);
}
