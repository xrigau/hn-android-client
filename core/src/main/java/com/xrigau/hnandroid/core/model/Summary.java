package com.xrigau.hnandroid.core.model;

import com.google.gson.annotations.SerializedName;

public class Summary {

    private final String title;
    @SerializedName("content")
    private final String text;
    @SerializedName("lead_image_url")
    private final String image;

    public Summary(String title, String text, String image) {
        this.title = title;
        this.text = text;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getImage() {
        return image;
    }

    public static class Builder {

        private String title;
        private String text;
        private String image;

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder image(String image) {
            this.image = image;
            return this;
        }

        public Summary build() {
            return new Summary(title, text, image);
        }
    }

}
