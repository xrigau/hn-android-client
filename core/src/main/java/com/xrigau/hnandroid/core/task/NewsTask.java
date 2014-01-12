package com.xrigau.hnandroid.core.task;

import com.xrigau.hnandroid.core.model.NewsResponse;
import com.xrigau.hnandroid.core.service.Services;

public class NewsTask extends BaseTask<NewsResponse> {

    static final String FIRST_PAGE = "news";

    private final String path;

    public NewsTask(String path) {
        this.path = path;
    }

    public NewsTask() {
        this(FIRST_PAGE);
    }

    @Override
    public NewsResponse execute(Services services) {
        return services.news(path);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NewsTask newsTask = (NewsTask) o;

        if (!path.equals(newsTask.path)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return path.hashCode();
    }
}
