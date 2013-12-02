package com.xrigau.espressodemo.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.xrigau.espressodemo.BuildConfig;
import com.xrigau.espressodemo.core.model.PostList;
import com.xrigau.espressodemo.core.task.NewsTask;
import com.xrigau.espressodemo.core.task.TaskExecutor;

public class PostsTaskLoader extends AsyncTaskLoader<PostList> {

    public PostsTaskLoader(Context context) {
        super(context);
    }

    @Override
    public PostList loadInBackground() {
        return new TaskExecutor(BuildConfig.HTTP_CLIENT).execute(new NewsTask());
    }
}
