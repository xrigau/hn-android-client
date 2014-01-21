package com.xrigau.hnandroid.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.xrigau.hnandroid.R;

import static com.xrigau.hnandroid.views.ObservableScrollView.OnScrollChangedListener;

public class NewsDetailsParallaxLayout extends FrameLayout implements OnScrollChangedListener {

    private static final float PARALLAX_FRICTION_FACTOR = 0.4f;

    private View parallaxBackgroundView;

    public NewsDetailsParallaxLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NewsDetailsParallaxLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        ObservableScrollView scrollView = (ObservableScrollView) findViewById(R.id.scroll);
        scrollView.addOnScrollChangedListener(this);
        parallaxBackgroundView = findViewById(R.id.parallax_background_view);
    }

    @Override
    public void onVerticalScrollChanged(int offsetY) {
        parallaxBackgroundView.setTranslationY(-offsetY * PARALLAX_FRICTION_FACTOR);
    }
}
