package com.xrigau.hnandroid.core.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class SummaryShould {

    @Test
    public void createSummaryUsingBuilder() {
        String expectedName = "someName";

        Summary news = new Summary.Builder()
                .name(expectedName)
                .build();

        assertNotNull(news);
        assertThat(news.getName(), is(expectedName));
    }
}
