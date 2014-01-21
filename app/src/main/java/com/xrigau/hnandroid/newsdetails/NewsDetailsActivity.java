package com.xrigau.hnandroid.newsdetails;

import android.os.Bundle;
import android.view.MenuItem;

import com.xrigau.hnandroid.HNActivity;
import com.xrigau.hnandroid.R;
import com.xrigau.hnandroid.task.TaskResult;
import com.xrigau.hnandroid.util.ActionBarFadeHelper;
import com.xrigau.hnandroid.views.ObservableScrollView;

public class NewsDetailsActivity extends HNActivity {

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
            finish(); // TODO: Implement proper navigation
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void deliverResult(TaskResult taskResult) {
        NewsDetailsFragment newsListFragment = (NewsDetailsFragment) getFragmentManager().findFragmentByTag(getString(R.string.fragment_news_details_tag));
        if (isAttached(newsListFragment)) {
            newsListFragment.onLoadFinished(taskResult);
        }
    }

    private boolean isAttached(NewsDetailsFragment newsListFragment) {
        return newsListFragment != null && newsListFragment.isAdded();
    }
}
