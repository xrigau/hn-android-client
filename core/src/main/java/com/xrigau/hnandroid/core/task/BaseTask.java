package com.xrigau.hnandroid.core.task;

public abstract class BaseTask<T> implements Task<T> {
    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract int hashCode();
}
