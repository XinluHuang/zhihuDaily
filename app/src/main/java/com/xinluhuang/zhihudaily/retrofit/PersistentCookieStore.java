package com.xinluhuang.zhihudaily.retrofit;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.Cookie;

public class PersistentCookieStore {
    private final Map<String, ConcurrentHashMap<String, Cookie>> cookies;
    private static final String LOG_TAG = "PersistentCookieStore";
    private static final String COOKIE_PREFS = "Cookies_Prefs";

    public PersistentCookieStore(Context context) {
        SharedPreferences cookiePrefs = context.getSharedPreferences(COOKIE_PREFS, 0);
        cookies = new HashMap<>();

        //将持久化的cookies缓存到内存中 即map cookies
        Map<String, ?> prefsMap = cookiePrefs.getAll();
        for (Map.Entry<String, ?> entry : prefsMap.entrySet()) {
            String[] cookieNames = TextUtils.split((String) entry.getValue(), ",");
            for (String name : cookieNames) {
                String encodedCookie = cookiePrefs.getString(name, null);
                if (encodedCookie != null) {
                    Cookie decodedCookie = decodeCookie(encodedCookie);
                    if (decodedCookie != null) {
                        if (!cookies.containsKey(entry.getKey())) {
                            cookies.put(entry.getKey(), new ConcurrentHashMap<String, Cookie>());
                        }
                        cookies.get(entry.getKey()).put(name, decodedCookie);
                    }
                }
            }
        }
    }

    private Cookie decodeCookie(String encodedCookie) {
        return null;
    }

}
