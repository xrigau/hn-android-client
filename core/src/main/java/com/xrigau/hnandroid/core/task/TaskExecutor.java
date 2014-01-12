package com.xrigau.hnandroid.core.task;

import com.xrigau.hnandroid.core.service.Services;

import retrofit.RestAdapter;
import retrofit.client.Client;

public class TaskExecutor {

    private static final String API_URL = "http://hackednews-json-api.herokuapp.com";

    private final Client client;

    public TaskExecutor(Client client) {
        this.client = client;
    }

    public <T> T execute(Task<T> task) {
        RestAdapter restAdapter = new RestAdapter.Builder().setClient(client).setLogLevel(RestAdapter.LogLevel.FULL).setServer(API_URL).build();
        return task.execute(restAdapter.create(Services.class));
    }

}
