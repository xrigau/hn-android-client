package com.xrigau.hnandroid.core.task;

import com.xrigau.hnandroid.core.debug.MockClient;
import com.xrigau.hnandroid.core.model.News;
import com.xrigau.hnandroid.core.model.NewsList;
import com.xrigau.hnandroid.core.model.NewsResponse;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class NewsTaskShould {

    @Test
    public void getNewsList() {
        NewsTask task = new NewsTask(NewsTask.FIRST_PAGE);

        NewsResponse response = new TaskExecutor(new MockClient()).execute(task);
        NewsList news = response.getNews();

        int expectedItemCount = 10;
        assertThat(news.size(), is(expectedItemCount));
        for (News n : news) {
            assertNotNull(n);
        }
    }
}
