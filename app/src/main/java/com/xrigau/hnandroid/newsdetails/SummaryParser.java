package com.xrigau.hnandroid.newsdetails;

import com.xrigau.hnandroid.core.model.ParsedSummary;
import com.xrigau.hnandroid.core.model.Summary;

import in.uncod.android.bypass.Bypass;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

class SummaryParser {

    public static Func1<Summary, Observable<ParsedSummary>> parseSummary() {
        return new Func1<Summary, Observable<ParsedSummary>>() {
            @Override
            public Observable<ParsedSummary> call(Summary summary) {
                return parseMarkdown(summary);
            }
        };
    }

    private static Observable<ParsedSummary> parseMarkdown(final Summary summary) {
        return Observable.create(new Observable.OnSubscribe<ParsedSummary>() {
            @Override
            public void call(Subscriber<? super ParsedSummary> subscriber) {
                try {
                    CharSequence parsedMarkdown = toSpannable(summary.getText());
                    subscriber.onNext(new ParsedSummary(summary, parsedMarkdown));
                    subscriber.onCompleted();
                } catch (Throwable error) {
                    subscriber.onError(error);
                }
            }
        });
    }

    private static CharSequence toSpannable(String summary) {
        return new Bypass().markdownToSpannable(summary);
    }

}
