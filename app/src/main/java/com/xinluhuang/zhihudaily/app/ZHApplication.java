package com.xinluhuang.zhihudaily.app;

import android.app.Application;
import android.content.Context;

public class ZHApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getBaseContext();
    }

    public static Context getContext() {
        return context;
    }
}
