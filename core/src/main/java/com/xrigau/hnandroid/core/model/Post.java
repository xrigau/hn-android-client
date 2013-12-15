package com.xrigau.hnandroid.core.model;

public class Post {

    private final String id;
    private final String title;
    private final String link;
    private final String domain;
    private final int score;
    private final String user;
    private final String timestamp;
    private final int comments;

    public Post(String id, String title, String link, String domain, int score, String user, String timestamp, int comments) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.domain = domain;
        this.score = score;
        this.user = user;
        this.timestamp = timestamp;
        this.comments = comments;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDomain() {
        return domain;
    }

    public int getScore() {
        return score;
    }

    public String getUser() {
        return user;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public int getComments() {
        return comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Post post = (Post) o;

        if (!id.equals(post.id)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public static class Builder {

        private String id;
        private String title;
        private String link;
        private String domain;
        private int score;
        private String user;
        private String timestamp;
        private int comments;

        public Builder id(String id) {
            this.id = id;
            return this;    
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder link(String link) {
            this.link = link;
            return this;
        }

        public Builder domain(String domain) {
            this.domain = domain;
            return this;
        }

        public Builder score(int score) {
            this.score = score;
            return this;
        }

        public Builder user(String user) {
            this.user = user;
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

        public Post build() {
            if (id == null) {
                id = "";
            }
            return new Post(id, title, link, domain, score, user, timestamp, comments);
        }
    }
    
}
