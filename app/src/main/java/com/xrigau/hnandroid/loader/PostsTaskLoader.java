package com.xrigau.hnandroid.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.xrigau.hnandroid.BuildConfig;
import com.xrigau.hnandroid.core.model.NewsResponse;
import com.xrigau.hnandroid.core.task.NewsTask;
import com.xrigau.hnandroid.core.task.TaskExecutor;

public class PostsTaskLoader extends AsyncTaskLoader<NewsResponse> {

    private final int page;

    public PostsTaskLoader(Context context, int page) {
        super(context);
        this.page = page;
    }

    @Override
    public NewsResponse loadInBackground() {
        return new TaskExecutor(BuildConfig.HTTP_CLIENT).execute(new NewsTask(page));
    }
}
