package com.xrigau.hnandroid.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.xrigau.hnandroid.core.model.News;
import com.xrigau.hnandroid.newsdetails.NewsDetailsActivity;

public class Navigator {

    private final Context context;

    public Navigator(Context context) {
        this.context = context;
    }

    private Intent intent(Class<? extends Activity> activityClass) {
        return new Intent(context, activityClass);
    }

    private void start(Intent intent) {
        context.startActivity(intent);
    }

    public void toDetails(News news) {
        Intent intent = intent(NewsDetailsActivity.class);
        intent.putExtra(NewsDetailsActivity.EXTRA_NEWS, news);
        start(intent);
    }

    public void toExternalAndroidBrowser(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        start(intent);
    }
}
