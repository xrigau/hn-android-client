package com.xrigau.hnandroid.core.task;

import com.xrigau.hnandroid.core.model.Summary;
import com.xrigau.hnandroid.core.service.Services;

public class SummaryTask implements Task<Summary> {

    private final String url;

    public SummaryTask(String url) {
        this.url = url;
    }

    @Override
    public Summary execute(Services services) {
        return services.summary(url);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SummaryTask that = (SummaryTask) o;

        if (!url.equals(that.url)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return url.hashCode();
    }
}
