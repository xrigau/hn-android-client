package com.xrigau.espressodemo;

import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
public class DummyViewForTestShould {

    @Test
    public void workWithRobolectricProperly() {
        // Test that Robolectric works, nothing else
        assertNotNull(new TextView(Robolectric.application));
        assertNotNull(new DummyViewForTest(Robolectric.application));
    }
}
