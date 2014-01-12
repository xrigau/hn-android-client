package com.xrigau.hnandroid.loader;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;

import com.xrigau.hnandroid.core.model.NewsResponse;
import com.xrigau.hnandroid.core.task.NewsTask;

public class NewsTaskLoaderCallbacks implements LoaderManager.LoaderCallbacks<NewsResponse> {

    public static final String PAGE = "page";

    private final Context context;
    private final LoaderListener<NewsResponse> listener;

    public NewsTaskLoaderCallbacks(Context context, LoaderListener<NewsResponse> listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        listener.onLoadStarted();
        return new NewsTaskLoader(context, args.getString(PAGE, NewsTask.FIRST_PAGE));
    }

    @Override
    public void onLoadFinished(Loader<NewsResponse> loader, NewsResponse response) {
        listener.onLoadFinished(response);
    }

    @Override
    public void onLoaderReset(Loader<NewsResponse> loader) {
        listener.onLoadStarted();
    }
}
