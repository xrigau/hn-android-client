package com.xrigau.hnandroid.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.xrigau.hnandroid.BuildConfig;
import com.xrigau.hnandroid.core.model.Summary;
import com.xrigau.hnandroid.core.task.SummaryTask;
import com.xrigau.hnandroid.core.task.TaskExecutor;

import retrofit.RetrofitError;

public class SummaryTaskLoader extends AsyncTaskLoader<Summary> { // TODO This class is almost the same as NewsTaskLoader DUPLICATION!

    private final String url;

    public SummaryTaskLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    public Summary loadInBackground() {
        try {
            return new TaskExecutor(BuildConfig.HTTP_CLIENT).execute(new SummaryTask(url));
        } catch (RetrofitError e) {
            Log.w("HN", "Ingored exception - " + e.getLocalizedMessage());
        }
        return null;
    }
}
