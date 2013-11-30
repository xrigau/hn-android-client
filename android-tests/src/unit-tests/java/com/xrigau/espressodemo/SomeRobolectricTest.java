package com.xrigau.espressodemo;

import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
public class SomeRobolectricTest {

    @Test
    public void testSomething() {
        assertNotNull(new TextView(Robolectric.application));
        assertNotNull(new SomethingElse(Robolectric.application));
    }
}
