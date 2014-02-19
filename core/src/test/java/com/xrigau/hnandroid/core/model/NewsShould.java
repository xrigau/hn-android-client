package com.xrigau.hnandroid.core.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class NewsShould {

    @Test
    public void createNewsUsingBuilder() {
        String expectedUrl = "someUrl";

        News news = new News.Builder().url(expectedUrl).build();

        assertNotNull(news);
        assertThat(news.getUrl(), is(expectedUrl));
    }
}
