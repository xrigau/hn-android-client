package com.xrigau.hnandroid.newsdetails;

import in.uncod.android.bypass.Bypass;
import rx.Observable;
import rx.Subscriber;

class SummaryParser {

    public static Observable<CharSequence> parseMarkdown(final String summary) {
        return Observable.create(new Observable.OnSubscribe<CharSequence>() {
            @Override
            public void call(Subscriber<? super CharSequence> subscriber) {
                try {
                    subscriber.onNext(toSpannable(summary));
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
