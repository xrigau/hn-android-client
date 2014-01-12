package com.xrigau.hnandroid.core.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class PostShould {

    @Test
    public void createPostUsingBuilder() {
        String expectedTitle = "someTitle";

        Post post = new Post.Builder().title(expectedTitle).build();

        assertNotNull(post);
        assertThat(post.getTitle(), is(expectedTitle));
    }
}
