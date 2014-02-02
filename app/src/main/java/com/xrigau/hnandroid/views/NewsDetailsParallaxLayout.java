package com.xrigau.hnandroid.views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.xrigau.hnandroid.R;

import static com.xrigau.hnandroid.views.ObservableScrollView.OnScrollChangedListener;

public class NewsDetailsParallaxLayout extends FrameLayout implements OnScrollChangedListener {

    private static final float PARALLAX_FRICTION_FACTOR = 0.4f;

    private View parallaxBackgroundView;
    private float translationY;

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
        translationY = -offsetY;
        parallaxBackgroundView.setTranslationY(translationY * PARALLAX_FRICTION_FACTOR);
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        if (isNotParallaxBackgroundView(child)) {
            return super.drawChild(canvas, child, drawingTime);
        }

        float bottom = child.getMeasuredHeight() + translationY;
        if (notVisible(bottom)) {
            return false;
        }

        return drawClippedView(canvas, child, drawingTime, bottom);
    }

    private boolean isNotParallaxBackgroundView(View child) {
        return child.getId() != parallaxBackgroundView.getId();
    }

    private boolean notVisible(float bottom) {
        return Float.compare(bottom, 0) < 0;
    }

    private boolean drawClippedView(Canvas canvas, View child, long drawingTime, float bottom) {
        canvas.save();
        canvas.clipRect(getTop(), getLeft(), getRight(), bottom);
        boolean result = super.drawChild(canvas, child, drawingTime);
        canvas.restore();
        return result;
    }
}
