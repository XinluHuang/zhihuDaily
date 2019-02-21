package com.xinluhuang.mylibrary.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.xinluhuang.mylibrary.app.MyApplication;


public class SPUtil {
    private static final String FILE_NAME = "share_data";
    public static final String isDay = "daynight";

    private SPUtil() {
    }

    public static Object get(String key, Object defaultObject) {
        SharedPreferences sharedPreferences= MyApplication.getInstance().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        if (defaultObject instanceof Boolean) {
            return sharedPreferences.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof String) {
            return sharedPreferences.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sharedPreferences.getInt(key, (Integer) defaultObject);
        }

        return null;
    }

    public static void put(String key, Object object) {
        SharedPreferences.Editor editor = MyApplication.getInstance().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).edit();
        if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof String) {
            editor.putString(key, (String) object);
        }else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        }
        editor.commit();
    }

    public static void remove(String key) {
        SharedPreferences sharedPreferences=MyApplication.getInstance().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.commit();
    }
}
