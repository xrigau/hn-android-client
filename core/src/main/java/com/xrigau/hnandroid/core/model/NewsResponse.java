package com.xrigau.hnandroid.core.model;

import java.io.Serializable;

public class NewsResponse implements Serializable {

    private static final long serialVersionUID = 476566476345866584L;

    private final NewsList items;
    private final String currentPage;
    private final String nextPage;

    public NewsResponse(NewsList items, String currentPage, String nextPage) {
        this.items = items;
        this.currentPage = currentPage;
        this.nextPage = nextPage;
    }

    public NewsList getNews() {
        return items;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public String getNextPage() {
        return nextPage;
    }

}
