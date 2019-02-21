package com.xinluhuang.mylibrary.app;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    private static MyApplication instance;
    public static Context getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
    }
}
