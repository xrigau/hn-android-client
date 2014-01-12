package com.xrigau.hnandroid.loader;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;

import com.xrigau.hnandroid.core.model.Summary;

public class SummaryTaskLoaderCallbacks implements LoaderManager.LoaderCallbacks<Summary> {  // TODO This class is almost the same as NewsTaskLoaderCallbacks DUPLICATION!

    public static final String URL = "url";

    private final Context context;
    private final LoaderListener<Summary> listener;

    public SummaryTaskLoaderCallbacks(Context context, LoaderListener<Summary> listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        listener.onLoadStarted();
        return new SummaryTaskLoader(context, args.getString(URL));
    }

    @Override
    public void onLoadFinished(Loader<Summary> loader, Summary response) {
        listener.onLoadFinished(response);
    }

    @Override
    public void onLoaderReset(Loader<Summary> loader) {
        listener.onLoadStarted();
    }
}
