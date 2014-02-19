package com.xrigau.hnandroid.newsdetails;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

import com.xrigau.hnandroid.R;
import com.xrigau.hnandroid.views.ObservableScrollView;

public class NewsDetailsActivity extends Activity {

    public static final String EXTRA_NEWS = "com.xrigau.hnandroid.EXTRA_NEWS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        setUpActionBar();
    }

    private void setUpActionBar() {
        getActionBar().setDisplayHomeAsUpEnabled(true);
        int imageHeight = getResources().getDimensionPixelSize(R.dimen.news_image_height);
        int actionBarHeight = getResources().getDimensionPixelSize(R.dimen.action_bar_height);
        int actionBarColor = getResources().getColor(R.color.action_bar_background);
        ActionBarFadeHelper actionBarFadeHelper = new ActionBarFadeHelper(getActionBar(), imageHeight, actionBarHeight, actionBarColor);
        ((ObservableScrollView) findViewById(R.id.scroll)).addOnScrollChangedListener(actionBarFadeHelper);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // TODO: Implement proper navigation using parentActivity
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
