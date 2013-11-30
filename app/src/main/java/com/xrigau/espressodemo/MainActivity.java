package com.xrigau.espressodemo;

import android.app.Activity;
import android.os.Bundle;

import com.xrigau.espressodemo.core.Something;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Something a = new Something();
        a.toString();
    }
}
