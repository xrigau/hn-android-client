package com.xrigau.hnandroid.core.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class NewsShould {

    @Test
    public void createNewsUsingBuilder() {
        String expectedTitle = "someTitle";

        News news = new News.Builder().title(expectedTitle).build();

        assertNotNull(news);
        assertThat(news.getTitle(), is(expectedTitle));
    }
}
