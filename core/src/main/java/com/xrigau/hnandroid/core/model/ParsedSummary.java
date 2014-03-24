package com.xrigau.hnandroid.core.model;

public class ParsedSummary {

    private final Summary summary;
    private final CharSequence parsedMarkdown;

    public ParsedSummary(Summary summary, CharSequence parsedMarkdown) {
        this.summary = summary;
        this.parsedMarkdown = parsedMarkdown;
    }

    public String getTitle() {
        return summary.getTitle();
    }

    public String getText() {
        return summary.getText();
    }

    public String getImage() {
        return summary.getImage();
    }

    public CharSequence getParsedMarkdown() {
        return parsedMarkdown;
    }
}
