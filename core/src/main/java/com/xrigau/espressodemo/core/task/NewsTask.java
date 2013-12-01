package com.xrigau.espressodemo.core.task;

import com.xrigau.espressodemo.core.model.PostList;
import com.xrigau.espressodemo.core.service.Services;

import java.lang.reflect.Type;

import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

class NewsTask implements Task<PostList> {

    @Override
    public PostList execute(Services services) {
        return services.news();
    }
}
