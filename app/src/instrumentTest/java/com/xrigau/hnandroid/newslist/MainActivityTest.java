package com.xrigau.hnandroid.newslist;

import android.test.ActivityInstrumentationTestCase2;

import com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers;
import com.xrigau.hnandroid.R;
import com.xrigau.hnandroid.core.model.News;

import static com.google.android.apps.common.testing.ui.espresso.Espresso.onData;
import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.matches;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.*;
import static com.xrigau.hnandroid.EspressoTestsMatchers.isEmpty;
import static com.xrigau.hnandroid.EspressoTestsMatchers.withChildCount;
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

    public void testDisplayNews() {
        onData(is(instanceOf(News.class))).atPosition(FIRST).check(matches(isDisplayed()));

        onView(ViewMatchers.withId(R.id.list)).check(matches(withChildCount(is(greaterThan(0))))); // Because the previous instruction makes sure we have data
    }

    public void testNewsIsDisplayedProperly() {
        onData(is(instanceOf(News.class)))
                .atPosition(FIRST).onChildView(withId(R.id.title))
                .check(matches(allOf(isDisplayed(), withText(not(isEmpty())))));

        onData(is(instanceOf(News.class)))
                .atPosition(FIRST).onChildView(withId(R.id.domain))
                .check(matches(allOf(isDisplayed(), withText(not(isEmpty()))))); // Remove this if using real data

        onData(is(instanceOf(News.class)))
                .atPosition(FIRST).onChildView(withId(R.id.time))
                .check(matches(allOf(isDisplayed(), withText(not(isEmpty())))));

        onData(is(instanceOf(News.class)))
                .atPosition(FIRST).onChildView(withId(R.id.comments))
                .check(matches(allOf(isDisplayed(), withText(not(isEmpty()))))); // Remove this if using real data

        onView(withId(R.id.loading)).check(matches(not(isDisplayed())));
    }

    public void testMockItemShowsCorrectData() { // Ignore test if using real data
        News news = new News.Builder()
                .title("Not Wanted")
                .domain("ninjasandrobots.com")
                .timestamp("3 hours ago")
                .comments(25)
                .build();

        onData(allOf(is(instanceOf(News.class)), is(news)))
                .onChildView(withId(R.id.title))
                .check(matches(withText(news.getTitle())));

        onData(allOf(is(instanceOf(News.class)), is(news)))
                .onChildView(withId(R.id.domain))
                .check(matches(withText(news.getDomain())));

        onData(allOf(is(instanceOf(News.class)), is(news)))
                .onChildView(withId(R.id.time))
                .check(matches(withText(news.getTimestamp())));

        onData(allOf(is(instanceOf(News.class)), is(news)))
                .onChildView(withId(R.id.comments))
                .check(matches(withText(containsString(Integer.toString(news.getComments())))));
    }
}
