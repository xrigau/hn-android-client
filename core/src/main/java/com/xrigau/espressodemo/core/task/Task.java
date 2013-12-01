package com.xrigau.espressodemo.core.task;

import com.xrigau.espressodemo.core.service.Services;

interface Task<T> {

    T execute(Services services);
}
