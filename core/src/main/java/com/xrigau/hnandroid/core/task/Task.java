package com.xrigau.hnandroid.core.task;

import com.xrigau.hnandroid.core.service.Services;

interface Task<T> {

    T execute(Services services);
}
