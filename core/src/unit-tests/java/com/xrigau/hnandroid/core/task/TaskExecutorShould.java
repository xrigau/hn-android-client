package com.xrigau.hnandroid.core.task;

import com.xrigau.hnandroid.core.debug.MockClient;
import com.xrigau.hnandroid.core.service.Services;

import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TaskExecutorShould {

    @Test
    public void executeTask() {
        Task task = mock(Task.class);

        new TaskExecutor(new MockClient()).execute(task);

        verify(task).execute(any(Services.class));
    }

}
