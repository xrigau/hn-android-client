package com.xrigau.hnandroid.newsdetails;

import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.xrigau.hnandroid.core.model.Summary;

import in.uncod.android.bypass.Bypass;

class SummaryContent {

    private final CharSequence converted;

    static SummaryContent from(Summary summary) {
        return new SummaryContent(summary.getText());
    }

    private SummaryContent(String content) {
        converted = convert(content);
    }

    private CharSequence convert(String content) {
        return new Bypass().markdownToSpannable(content);
    }

    void into(TextView textView) {
        textView.setText(converted);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

}
