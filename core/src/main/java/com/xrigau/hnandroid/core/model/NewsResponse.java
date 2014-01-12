package com.xrigau.hnandroid.core.model;

public class NewsResponse {

    private final PostList items;
    private final String currentPage;
    private final String nextPage;

    public NewsResponse(PostList items, String currentPage, String nextPage) {
        this.items = items;
        this.currentPage = currentPage;
        this.nextPage = nextPage;
    }

    public PostList getNews() {
        return items;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public String getNextPage() {
        return nextPage;
    }

}
