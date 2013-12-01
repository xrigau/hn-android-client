package com.xrigau.espressodemo.core.task;

import com.xrigau.espressodemo.core.model.PostList;
import com.xrigau.espressodemo.core.service.Services;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TaskExecutorShould {

    @Test
    public void executeNewsRequest() {
        NewsTask task = mock(NewsTask.class);

        new TaskExecutor().execute(task);

        verify(task).execute(any(Services.class));
    }

    @Test
    public void getsNewsList() {
        NewsTask task = new NewsTask();

        PostList posts = new TaskExecutor().execute(task);

        assertThat(posts.size(), is(not(0)));
        assertNotNull(posts.get(0));
    }

}
