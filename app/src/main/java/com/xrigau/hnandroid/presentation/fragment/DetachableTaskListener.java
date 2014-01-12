package com.xrigau.hnandroid.presentation.fragment;

public interface DetachableTaskListener<T> {
    void onLoadFinished(T result, Throwable error);
    boolean isAttached();
}
