package com.xrigau.hnandroid.core.model;

public class Summary {

    private final String name;
    private final String image;
    private final String description;
    private final String text;
    private final String url;
    private final String type;
    private final String schema;
    private final String dateAccessed;

    public Summary(String name, String image, String description, String text, String url, String type, String schema, String dateAccessed) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.text = text;
        this.url = url;
        this.type = type;
        this.schema = schema;
        this.dateAccessed = dateAccessed;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public String getText() {
        return text;
    }

    public String getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }

    public String getSchema() {
        return schema;
    }

    public String getDateAccessed() {
        return dateAccessed;
    }

    public static class Builder {
        
        private String name;
        private String image;
        private String description;
        private String text;
        private String url;
        private String type;
        private String schema;
        private String dateAccessed;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder image(String image) {
            this.image = image;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder text(String description) {
            this.description = description;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder schema(String schema) {
            this.schema = schema;
            return this;
        }

        public Builder dateAccessed(String dateAccessed) {
            this.dateAccessed = dateAccessed;
            return this;
        }

        public Summary build() {
            return new Summary(name, image, description, text, url, type, schema, dateAccessed);
        }
    }
    
}
