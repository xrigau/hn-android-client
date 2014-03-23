package com.xrigau.hnandroid.task;

import com.xrigau.hnandroid.BuildConfig;
import com.xrigau.hnandroid.core.model.NewsResponse;
import com.xrigau.hnandroid.core.model.Summary;
import com.xrigau.hnandroid.core.task.NewsTask;
import com.xrigau.hnandroid.core.task.SummaryTask;
import com.xrigau.hnandroid.core.task.Task;
import com.xrigau.hnandroid.core.task.TaskExecutor;

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

    private static Task<NewsResponse> newsTask(String page) {
        return new NewsTask(page);
    }

    public static Observable<Summary> fetchSummary(String url) {
        return observableFrom(summaryTask(url));
    }

    private static Task<Summary> summaryTask(String url) {
        return new SummaryTask(url);
    }

    private static <T> Observable<T> observableFrom(final Task<T> task) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(new TaskExecutor(BuildConfig.HTTP_CLIENT).execute(task));
                    subscriber.onCompleted();
                } catch (Throwable error) {
                    subscriber.onError(error);
                }
            }
        });
    }
}
