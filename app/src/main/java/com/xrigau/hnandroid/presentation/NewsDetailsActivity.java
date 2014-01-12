package com.xrigau.hnandroid.presentation;

import android.app.Activity;
import android.os.Bundle;

import com.xrigau.hnandroid.R;

public class NewsDetailsActivity extends Activity {

    public static final String EXTRA_NEWS = "com.xrigau.hnandroid.EXTRA_NEWS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
    }

}
