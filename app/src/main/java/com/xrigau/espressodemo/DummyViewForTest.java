package com.xrigau.espressodemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Dummy class. Just want to see that Robolectric works.
 */
public class DummyViewForTest extends View {
    public DummyViewForTest(Context context) {
        super(context);
    }

    public DummyViewForTest(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DummyViewForTest(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
