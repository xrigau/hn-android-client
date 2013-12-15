package com.xrigau.hnandroid.core.task;

import com.xrigau.hnandroid.core.model.NewsResponse;
import com.xrigau.hnandroid.core.service.Services;

public class NewsTask implements Task<NewsResponse> {

    private final int page;

    public NewsTask(int page) {
        this.page = page;
    }

    @Override
    public NewsResponse execute(Services services) {
        return services.news(page);
    }
}
