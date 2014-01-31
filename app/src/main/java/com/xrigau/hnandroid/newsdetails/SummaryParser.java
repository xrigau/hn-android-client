package com.xrigau.hnandroid.newsdetails;

import android.os.AsyncTask;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.xrigau.hnandroid.core.model.Summary;

import in.uncod.android.bypass.Bypass;

class SummaryParser {

    private final String content;

    static SummaryParser from(Summary summary) {
        return new SummaryParser(summary.getText());
    }

    private SummaryParser(String content) {
        this.content = content;
    }

    void into(final TextView textView) {

        new AsyncTask<Void, Void, CharSequence>() {
            @Override
            protected CharSequence doInBackground(Void... params) {
                return new Bypass().markdownToSpannable(content);
            }

            @Override
            protected void onPostExecute(CharSequence converted) {
                if (textView == null) {
                    return;
                }
                textView.setText(converted);
                textView.setMovementMethod(LinkMovementMethod.getInstance());
            }
        }.execute();
    }

}
