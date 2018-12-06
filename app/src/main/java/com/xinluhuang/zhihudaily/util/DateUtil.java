package com.xinluhuang.zhihudaily.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {
    public static final String DATE_FOR_API = "yyyyMMdd";
    public static final String DATE_FOR_HEAD = "MM月dd日 E";

    public static String getDayAfter(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, day);
        return new SimpleDateFormat(DATE_FOR_API)
                .format(calendar.getTime());
    }

    public static String getToday() {
        return getDayAfter(0);
    }

    public static String getDayBefore(int day) {
        return getDayAfter(-day);
    }

    public static String getDayAfterForHead(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, day);
        return new SimpleDateFormat(DATE_FOR_HEAD)
                .format(calendar.getTime());
    }
    public static String getDayBeforeForHead(int day) {
        return getDayAfterForHead(-day);
    }

    private DateUtil() {
    }
}
