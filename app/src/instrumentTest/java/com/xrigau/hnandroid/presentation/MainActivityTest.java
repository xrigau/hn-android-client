package com.xrigau.hnandroid.presentation;

import android.test.ActivityInstrumentationTestCase2;

import com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers;
import com.xrigau.hnandroid.R;
import com.xrigau.hnandroid.core.model.Post;

import static com.google.android.apps.common.testing.ui.espresso.Espresso.onData;
import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.matches;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.*;
import static com.xrigau.hnandroid.presentation.helper.EspressoTestsMatchers.isEmpty;
import static com.xrigau.hnandroid.presentation.helper.EspressoTestsMatchers.withChildCount;
import static org.hamcrest.Matchers.*;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private static final int FIRST = 0;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    public void testDisplayPosts() {
        onData(is(instanceOf(Post.class))).atPosition(FIRST).check(matches(isDisplayed()));

        onView(ViewMatchers.withId(R.id.list)).check(matches(withChildCount(is(greaterThan(0))))); // Because the previous instruction makes sure we have data
    }

    public void testPostIsDisplayedProperly() {
        onData(is(instanceOf(Post.class)))
                .atPosition(FIRST).onChildView(withId(R.id.title))
                .check(matches(allOf(isDisplayed(), withText(not(isEmpty())))));

        onData(is(instanceOf(Post.class)))
                .atPosition(FIRST).onChildView(withId(R.id.domain))
                .check(matches(allOf(isDisplayed(), withText(not(isEmpty()))))); // Remove this if using real data

        onData(is(instanceOf(Post.class)))
                .atPosition(FIRST).onChildView(withId(R.id.time))
                .check(matches(allOf(isDisplayed(), withText(not(isEmpty())))));

        onData(is(instanceOf(Post.class)))
                .atPosition(FIRST).onChildView(withId(R.id.comments))
                .check(matches(allOf(isDisplayed(), withText(not(isEmpty()))))); // Remove this if using real data

        onView(withId(R.id.loading)).check(matches(not(isDisplayed())));
    }

    public void testMockItemShowsCorrectData() { // Ignore test if using real data
        Post post = new Post.Builder()
                .title("Not Wanted")
                .domain("ninjasandrobots.com")
                .timestamp("3 hours ago")
                .comments(25)
                .build();

        onData(allOf(is(instanceOf(Post.class)), is(post)))
                .onChildView(withId(R.id.title))
                .check(matches(withText(post.getTitle())));

        onData(allOf(is(instanceOf(Post.class)), is(post)))
                .onChildView(withId(R.id.domain))
                .check(matches(withText(post.getDomain())));

        onData(allOf(is(instanceOf(Post.class)), is(post)))
                .onChildView(withId(R.id.time))
                .check(matches(withText(post.getTimestamp())));

        onData(allOf(is(instanceOf(Post.class)), is(post)))
                .onChildView(withId(R.id.comments))
                .check(matches(withText(containsString(Integer.toString(post.getComments())))));
    }
}
