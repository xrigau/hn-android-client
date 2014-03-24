package com.xrigau.hnandroid.core.task;


class PageTokenDiscombobulator {

    private static final String TOKEN_PREFIX = "x?fnid=";
    private final String token;

    PageTokenDiscombobulator(String path) {
        if (malformedPath(path)) {
            throw new IllegalArgumentException("Path [" + path + "] is malformed and so it can't be parsed");
        }
        this.token = path.replace(TOKEN_PREFIX, "");
    }

    private boolean malformedPath(String path) {
        return !path.startsWith(TOKEN_PREFIX);
    }

    String getToken() {
        return token;
    }

}
