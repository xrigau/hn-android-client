package com.xrigau.hnandroid.core.task;

import com.xrigau.hnandroid.core.service.Services;

public interface Task<T> {
    public T execute(Services services);
}
