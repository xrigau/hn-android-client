package com.xrigau.hnandroid.core.service;

import com.xrigau.hnandroid.core.model.NewsResponse;
import com.xrigau.hnandroid.core.model.Summary;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface Services {

    @GET("/{path}")
    NewsResponse news(@Path("path") String path);

    @GET("/summary")
    Summary summary(@Query("url") String url);

}
