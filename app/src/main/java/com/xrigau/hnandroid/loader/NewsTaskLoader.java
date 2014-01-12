package com.xrigau.hnandroid.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.xrigau.hnandroid.BuildConfig;
import com.xrigau.hnandroid.core.model.NewsResponse;
import com.xrigau.hnandroid.core.task.NewsTask;
import com.xrigau.hnandroid.core.task.TaskExecutor;

import retrofit.RetrofitError;

public class NewsTaskLoader extends AsyncTaskLoader<NewsResponse> {

    private final String page;

    public NewsTaskLoader(Context context, String page) {
        super(context);
        this.page = page;
    }

    @Override
    public NewsResponse loadInBackground() {
        try {
            return new TaskExecutor(BuildConfig.HTTP_CLIENT).execute(new NewsTask(page));
        } catch (RetrofitError e) {
            Log.w("HN", "Ingored exception - " + e.getLocalizedMessage());
        }
        return null;
    }
}
