package com.example.android.notification;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by kevinsun on 9/14/17.
 */

public class PreferenceUtil {

    private static final int DEFAULT = 0;

    public static final String COUNT = "count";

    public static final String NOTIFICATION_COUNT = "notification_count";

    public static void setNumber(Context context, int count) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(COUNT, count);
        editor.apply();
    }

    public static int getCount(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        int count = sharedPreferences.getInt(COUNT, DEFAULT);

        return count;
    }

    public static void incrementCount(Context context) {
        int newCount = getCount(context);
        setNumber(context, ++newCount);
    }

    public static int getNotificationCount(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        int notificationCount = sharedPreferences.getInt(NOTIFICATION_COUNT, DEFAULT);

        return notificationCount;
    }

    public static void incrementNotificationCount(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        int count = sharedPreferences.getInt(NOTIFICATION_COUNT, DEFAULT);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(NOTIFICATION_COUNT, ++count);

        editor.apply();
    }
}
