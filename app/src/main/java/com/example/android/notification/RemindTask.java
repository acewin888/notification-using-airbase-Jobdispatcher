package com.example.android.notification;

import android.content.Context;

/**
 * Created by kevinsun on 9/14/17.
 */

public class RemindTask {

    public static final String COUNT_INCREASE = "count_increase";

    public static final String NOTIFICATION_INCREASE = "notification_increase";

    public static final String NOTIFICATION_CANCEL = "notification_cancel";


    public static void execute(Context context, String action) {
        if (action.equals(COUNT_INCREASE)) {
            PreferenceUtil.incrementCount(context);
            NotificationUtil.clearNotification(context);
        }  if (action.equals(NOTIFICATION_INCREASE)) {
            PreferenceUtil.incrementNotificationCount(context);
        } if (action.equals(NOTIFICATION_CANCEL)){
            NotificationUtil.clearNotification(context);
        }
    }

}
