package com.xrigau.hnandroid.core.task;

import com.xrigau.hnandroid.core.model.NewsResponse;
import com.xrigau.hnandroid.core.service.Services;

public class NewsTask implements Task<NewsResponse> {

    static final String FIRST_PAGE = "news";
    static final String SECOND_PAGE = "news2";

    private final String path;

    public NewsTask(String path) {
        this.path = path;
    }

    public NewsTask() {
        this(FIRST_PAGE);
    }

    @Override
    public NewsResponse execute(Services services) {
        if (FIRST_PAGE.equals(path)) {
            return services.news();
        } else if (SECOND_PAGE.equals(path)) {
            return services.news2();
        }

        return services.news(new PageTokenDiscombobulator(path).getToken());
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
