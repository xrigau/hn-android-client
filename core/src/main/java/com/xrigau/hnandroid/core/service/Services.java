package com.xrigau.hnandroid.core.service;

import com.xrigau.hnandroid.core.model.NewsResponse;
import com.xrigau.hnandroid.core.model.Summary;

import retrofit.http.GET;
import retrofit.http.Query;

public interface Services {

    @GET("/news")
    NewsResponse news();

    @GET("/news2")
    NewsResponse news2();

    @GET("/x")
    NewsResponse news(@Query("fnid") String token);

    @GET("/summary")
    Summary summary(@Query("url") String url);

}
