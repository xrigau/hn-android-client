package com.xrigau.hnandroid.newsdetails;

import android.text.Html;
import android.text.Spanned;
import android.widget.TextView;

import com.commonsware.cwac.anddown.AndDown;
import com.xrigau.hnandroid.core.model.Summary;

class SummaryContent {

    private final Spanned converted;

    static SummaryContent from(Summary summary) {
        return new SummaryContent(summary.getText());
    }

    private SummaryContent(String content) {
        converted = convert(content);
    }

    private Spanned convert(String content) {
        String htmlText = new AndDown().markdownToHtml(content);
        return Html.fromHtml(htmlText);
    }

    void into(TextView textView) {
        textView.setText(converted);
    }

}
