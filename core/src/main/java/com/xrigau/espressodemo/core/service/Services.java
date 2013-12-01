package com.xrigau.espressodemo.core.service;

import com.xrigau.espressodemo.core.model.PostList;

import retrofit.http.GET;
import retrofit.http.Path;

public interface Services {

    @GET("/news")
    PostList news();

    @GET("/news2")
    PostList news2();

    @GET("/item/{postId}")
    PostList item(
            @Path("postId") String postId
    );

    @GET("/user/{username}")
    PostList user(
            @Path("username") String username
    );

}
