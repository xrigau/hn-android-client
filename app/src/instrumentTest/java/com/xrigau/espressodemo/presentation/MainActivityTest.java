package com.xrigau.espressodemo.presentation;

import android.test.ActivityInstrumentationTestCase2;

import com.xrigau.espressodemo.R;

import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.Espresso.pressBack;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.click;
import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.matches;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.*;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    public void testActivityExists() {
        assertNotNull(getActivity());
    }

    public void testActionBarIsSetUpCorrectly() {
        onView(withText(R.string.app_name)).check(matches(isDisplayed()));
//        onView(withDrawable(R.drawable.ic_launcher)).check(matches(isDisplayed()));
//        onView(overflowMenu()).check(doesNotExist());
    }

    public void testButtonsAreDisplaying() {
        onView(withId(R.id.button_with_id)).check(matches(isDisplayed()));
        onView(withId(R.id.button_with_id)).check(matches(isClickable()));

        onView(withText(R.string.simple_button)).check(matches(isDisplayed())).check(matches(isClickable()));

        onView(withClassName(containsString("ImageButton"))).check(matches(allOf(isDisplayed(), isClickable())));

        onView(withContentDescription("Best content description ever")).check(matches(allOf(isDisplayed(), isClickable())));

        onView(withText(startsWith("Hello visitor"))).check(matches(allOf(isDisplayed(), isClickable())));
    }

    public void testVisitorButtonOpensActivity() {
        onView(withText(startsWith("Hello visitor"))).perform(click());

        onView(withId(R.id.visitor_text)).check(matches(isDisplayed()));

        pressBack();

        onView(withId(R.id.button_with_id)).check(matches(isDisplayed()));
    }

//    public void testImageButtonHasCorrectBackgroundAndSrc() {
//        onView(withDrawable(android.R.drawable.ic_menu_gallery)).check(matches(isDisplayed()));
//        onView(withDrawable(android.R.drawable.ic_menu_gallery)).check(matches(withText(isEmpty())));
//
//        onView(withDrawable(android.R.drawable.ic_menu_gallery)).check(matches(allOf(withDrawable(android.R.drawable.alert_dark_frame), isDisplayed())));
//    }
}
