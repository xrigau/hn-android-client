package com.xrigau.hnandroid.core.task;

import com.xrigau.hnandroid.core.model.NewsResponse;
import com.xrigau.hnandroid.core.model.Summary;

public class TaskFactory {

    public static BaseTask<NewsResponse> newsTask(String page) {
        if (page == null || page.length() == 0) {
            return new NewsTask();
        }
        return new NewsTask(page);
    }

    public static BaseTask<Summary> summaryTask(String url) {
        return new SummaryTask(url);
    }
}
