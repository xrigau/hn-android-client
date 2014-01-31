package com.xrigau.hnandroid.core.model;

import java.io.Serializable;

public class News implements Serializable {

    private final String title;
    private final String url;
    private final String domain;
    private final int points;
    private final String user;
    private final String userUrl;
    private final String timestamp;
    private final int comments;
    private final String commentsUrl;

    public News(String title, String url, String domain, int points, String user, String userUrl, String timestamp, int comments, String commentsUrl) {
        this.title = title;
        this.url = url;
        this.domain = domain;
        this.points = points;
        this.user = user;
        this.userUrl = userUrl;
        this.timestamp = timestamp;
        this.comments = comments;
        this.commentsUrl = commentsUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getDomain() {
        return domain;
    }

    public int getPoints() {
        return points;
    }

    public String getUser() {
        return user;
    }

    public String getUserUrl() {
        return userUrl;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public int getComments() {
        return comments;
    }

    public String getCommentsUrl() {
        return commentsUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        News news = (News) o;

        if (!url.equals(news.url)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return url.hashCode();
    }

    public static class Builder {

        private String title;
        private String url;
        private String domain;
        private int points;
        private String user;
        private String userUrl;
        private String timestamp;
        private int comments;
        private String commentsUrl;

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder domain(String domain) {
            this.domain = domain;
            return this;
        }

        public Builder points(int points) {
            this.points = points;
            return this;
        }

        public Builder user(String user) {
            this.user = user;
            return this;
        }

        public Builder userUrl(String userUrl) {
            this.userUrl = userUrl;
            return this;
        }

        public Builder timestamp(String timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder comments(int comments) {
            this.comments = comments;
            return this;
        }

        public Builder commentsUrl(String commentsUrl) {
            this.commentsUrl = commentsUrl;
            return this;
        }

        public News build() {
            if (title == null) {
                title = "";
            }
            return new News(title, url, domain, points, user, userUrl, timestamp, comments, commentsUrl);
        }
    }

}
