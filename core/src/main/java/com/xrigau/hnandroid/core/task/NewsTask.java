package com.xrigau.hnandroid.core.task;

import com.xrigau.hnandroid.core.model.NewsResponse;
import com.xrigau.hnandroid.core.service.Services;

public class NewsTask implements Task<NewsResponse> {

    public static final String FIRST_PAGE = "news";

    private final String path;

    public NewsTask(String path) {
        this.path = path;
    }

    @Override
    public NewsResponse execute(Services services) {
        return services.news(path);
    }
}
