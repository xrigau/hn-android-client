package com.xrigau.hnandroid.core.model;

public class NewsResponse {

    private static final int INVALID_PAGE = -1;

    private final PostList news;
    private final String nextPagePath;
    private final int currentPage;
    private final int nextPage;

    public NewsResponse(PostList news, String nextPagePath, int currentPage, int nextPage) {
        this.news = news;
        this.nextPagePath = nextPagePath;
        this.currentPage = currentPage;
        this.nextPage = nextPage;
    }

    public PostList getNews() {
        return news;
    }

    public String getNextPagePath() {
        return nextPagePath;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public boolean isLastPage() {
        return nextPage == INVALID_PAGE;
    }
}
