package com.xrigau.hnandroid.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.xrigau.hnandroid.R;

public class CustomFontTextView extends TextView {

    private final FontWriter fontWriter;

    public CustomFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        fontWriter = FontWriter.create(this, attrs, R.styleable.CustomFontTextView);
        init();
    }

    public CustomFontTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        fontWriter = FontWriter.create(this, attrs, R.styleable.CustomFontTextView);
        init();
    }

    private void init() {
        setCustomFont(R.styleable.CustomFontTextView_font);
    }

    private void setCustomFont(int customFontId) {
        fontWriter.setCustomFont(customFontId);
    }

    public void setCustomFont(String customFontName) {
        fontWriter.setCustomFont(customFontName);
    }
}