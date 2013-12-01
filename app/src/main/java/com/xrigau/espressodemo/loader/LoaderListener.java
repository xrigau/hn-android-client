package com.xrigau.espressodemo.loader;

public interface LoaderListener<T> {
    void onLoadStarted();

    void onLoadFinished(T result);
}
