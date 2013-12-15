package com.xrigau.hnandroid.core.service;

import com.xrigau.hnandroid.core.model.NewsResponse;

import retrofit.http.GET;
import retrofit.http.Path;

public interface Services {

    @GET("/news/{page}")
    NewsResponse news(@Path("page") int page);

}