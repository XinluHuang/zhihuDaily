package com.xinluhuang.zhihudaily.util;

import android.util.Log;

public class LogUtil {
    private static final boolean DEBUG=true;
    private static final String TAG="my";

    private static int level=1;
    public static void d(String content){
        if(DEBUG && content!=null) {
            Log.d(TAG, content);
        }
    }
}
