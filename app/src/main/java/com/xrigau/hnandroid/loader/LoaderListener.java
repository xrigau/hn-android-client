package com.xrigau.hnandroid.loader;

public interface LoaderListener<T> {
    void onLoadStarted();

    void onLoadFinished(T result);
}
