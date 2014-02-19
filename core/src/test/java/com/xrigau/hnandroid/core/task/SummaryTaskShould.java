package com.xrigau.hnandroid.core.task;

import com.xrigau.hnandroid.core.debug.MockClient;
import com.xrigau.hnandroid.core.model.Summary;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class SummaryTaskShould {

    @Test
    public void getNewsSummary() {
        SummaryTask task = new SummaryTask("someNewsUrl");

        Summary response = new TaskExecutor(new MockClient()).execute(task);
        assertNotNull(response);
    }
}
