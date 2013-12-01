package com.xrigau.espressodemo.core.task;

import com.xrigau.espressodemo.core.model.PostList;
import com.xrigau.espressodemo.core.service.Services;

public class NewsTask implements Task<PostList> {

    @Override
    public PostList execute(Services services) {
        return services.news();
    }
}
