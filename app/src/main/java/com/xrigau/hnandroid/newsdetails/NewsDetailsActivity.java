package com.xrigau.hnandroid.newsdetails;

import android.os.Bundle;
import android.view.MenuItem;

import com.xrigau.hnandroid.HNActivity;
import com.xrigau.hnandroid.R;

public class NewsDetailsActivity extends HNActivity {

    public static final String EXTRA_NEWS = "com.xrigau.hnandroid.EXTRA_NEWS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // TODO: Implement proper navigation
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
