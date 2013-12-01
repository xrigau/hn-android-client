package com.xrigau.espressodemo.core.task;

import com.xrigau.espressodemo.core.service.Services;

import retrofit.RestAdapter;

class TaskExecutor {

    private static final String API_URL = "http://node-hnapi.herokuapp.com";

    <T> T execute(Task<T> task) {
        RestAdapter restAdapter = new RestAdapter.Builder().setServer(API_URL).build();
        return task.execute(restAdapter.create(Services.class));
    }

}
