package com.xrigau.hnandroid.core.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class SummaryShould {

    @Test
    public void createSummaryUsingBuilder() {
        String expectedTitle = "someTitle";

        Summary news = new Summary.Builder()
                .title(expectedTitle)
                .build();

        assertNotNull(news);
        assertThat(news.getTitle(), is(expectedTitle));
    }
}
