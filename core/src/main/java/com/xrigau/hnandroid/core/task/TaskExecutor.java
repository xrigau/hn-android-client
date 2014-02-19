package com.xrigau.hnandroid.core.task;

import com.xrigau.hnandroid.core.service.Services;

import retrofit.RestAdapter;
import retrofit.client.Client;

import static retrofit.RestAdapter.LogLevel.FULL;
import static retrofit.RestAdapter.LogLevel.NONE;

public class TaskExecutor {

    private static final String API_URL = "http://hackednews-json-api.herokuapp.com";

    private final Client client;
    private final boolean enableLog;

    public TaskExecutor(Client client) {
        this.client = client;
        enableLog = true;
    }

    public <T> T execute(Task<T> task) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setServer(API_URL)
                .setClient(client)
                .setLogLevel(enableLog ? FULL : NONE)
                .build();
        return task.execute(restAdapter.create(Services.class));
    }

}
