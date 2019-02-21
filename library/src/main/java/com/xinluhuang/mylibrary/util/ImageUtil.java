package com.xinluhuang.mylibrary.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class ImageUtil {
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        float heightR = 1;
        float widthR = 1;
        Log.d("my", "height "+options.outHeight+" width "+options.outWidth);
        heightR = Math.round((float) options.outHeight / (float) reqHeight);
        widthR = Math.round((float) options.outWidth / (float) reqWidth);
        int size = Math.round(heightR < widthR ? heightR : widthR);
        options.inJustDecodeBounds = false;
        options.inSampleSize=size;
        return BitmapFactory.decodeResource(res, resId, options);
    }
}
