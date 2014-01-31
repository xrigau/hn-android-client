package com.xrigau.hnandroid.newslist;

import android.os.Bundle;

import com.xrigau.hnandroid.HNActivity;
import com.xrigau.hnandroid.R;
import com.xrigau.hnandroid.task.TaskResult;

public class MainActivity extends HNActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
    }

    @Override
    public void deliverResult(TaskResult taskResult) {
        NewsListFragment newsListFragment = (NewsListFragment) getFragmentManager().findFragmentByTag(getString(R.string.fragment_news_list_tag));
        if (isFragmentAttached(newsListFragment)) {
            newsListFragment.onLoadFinished(taskResult);
        }
    }
}
