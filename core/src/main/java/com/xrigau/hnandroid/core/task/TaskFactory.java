package com.xrigau.hnandroid.core.task;

import com.xrigau.hnandroid.core.model.NewsResponse;
import com.xrigau.hnandroid.core.model.Summary;

import retrofit.client.UrlConnectionClient;
import rx.Observable;
import rx.Subscriber;

public class TaskFactory {

    public static Observable<NewsResponse> fetchNews() {
        return observableFrom(newsTask());
    }

    private static Task<NewsResponse> newsTask() {
        return new NewsTask();
    }

    public static Observable<NewsResponse> fetchNews(String page) {
        return observableFrom(newsTask(page));
    }

    public static Task<NewsResponse> newsTask(String page) {
        return new NewsTask(page);
    }

    public static Observable<Summary> fetchSummary(String url) {
        return observableFrom(summaryTask(url));
    }

    public static Task<Summary> summaryTask(String url) {
        return new SummaryTask(url);
    }

    private static <T> Observable<T> observableFrom(final Task<T> task) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                new TaskExecutor(new UrlConnectionClient()).execute(task);
            }
        });
    }
}
