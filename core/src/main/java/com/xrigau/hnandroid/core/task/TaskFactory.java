package com.xrigau.hnandroid.core.task;

import com.xrigau.hnandroid.core.model.NewsResponse;
import com.xrigau.hnandroid.core.model.Summary;

public class TaskFactory {

    public static Task<NewsResponse> newsTask(String page) {
        if (page == null || page.length() == 0) {
            return newsTask();
        }
        return new NewsTask(page);
    }

    public static Task<NewsResponse> newsTask() {
        return new NewsTask();
    }

    public static Task<Summary> summaryTask(String url) {
        return new SummaryTask(url);
    }
}
