package com.xrigau.hnandroid.newsdetails;

import android.os.Bundle;

import com.xrigau.hnandroid.R;
import com.xrigau.hnandroid.HNActivity;

public class NewsDetailsActivity extends HNActivity {

    public static final String EXTRA_NEWS = "com.xrigau.hnandroid.EXTRA_NEWS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
    }

}
