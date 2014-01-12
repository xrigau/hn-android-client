package com.xrigau.hnandroid.core.task;

import com.xrigau.hnandroid.core.debug.MockClient;
import com.xrigau.hnandroid.core.model.NewsResponse;
import com.xrigau.hnandroid.core.model.PostList;
import com.xrigau.hnandroid.core.service.Services;

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

        new TaskExecutor(new MockClient()).execute(task);

        verify(task).execute(any(Services.class));
    }

    @Test
    public void getsNewsList() {
        NewsTask task = new NewsTask("/news");

        NewsResponse response = new TaskExecutor(new MockClient()).execute(task);
        PostList posts = response.getNews();

        assertThat(posts.size(), is(not(0)));
        assertNotNull(posts.get(0));
    }

}
