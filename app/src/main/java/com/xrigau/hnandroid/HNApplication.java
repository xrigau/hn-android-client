package com.xrigau.hnandroid;

import android.app.Application;

import com.novoda.notils.logger.simple.Log;

public class HNApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.SHOW_LOGS = BuildConfig.DEBUG;
    }
}
