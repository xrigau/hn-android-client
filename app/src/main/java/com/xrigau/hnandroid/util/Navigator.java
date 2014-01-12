package com.xrigau.hnandroid.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.xrigau.hnandroid.core.model.News;
import com.xrigau.hnandroid.presentation.activity.NewsDetailsActivity;

public class Navigator {

    private final Context context;

    public Navigator(Context context) {
        this.context = context;
    }

    public void toDetails(News news) {
        Intent intent = intent(NewsDetailsActivity.class);
        intent.putExtra(NewsDetailsActivity.EXTRA_NEWS, news);
        start(intent);
    }

    private Intent intent(Class<? extends Activity> activityClass) {
        return new Intent(context, activityClass);
    }

    private void start(Intent intent) {
        context.startActivity(intent);
    }
}
