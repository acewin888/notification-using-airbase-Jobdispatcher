package com.example.android.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by kevinsun on 9/14/17.
 */

public class NotificationUtil {

    private static final int INTENT = 1123;

    private static final int INCREASE_INTENT = 12;

    private static final int IGNORE_NOTIFICATION = 23;


    public static void popNotification(Context context) {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setSmallIcon(R.drawable.ic_accessibility_white_24dp)
                .setLargeIcon(largeIcon(context))
                .setContentTitle(context.getString(R.string.title))
                .setContentText(context.getString(R.string.body))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(
                        context.getString(R.string.body)))
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(contentIntent(context))
                .addAction(increaseCount(context))
                .addAction(ignoreNotification(context))
                .setAutoCancel(true);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            notificationBuilder.setPriority(Notification.PRIORITY_HIGH);
        }

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        manager.notify(110, notificationBuilder.build());
    }


    // this will allow user to tap on notification and go to main page
    private static PendingIntent contentIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);

        return PendingIntent.getActivity(context, INTENT, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private static Bitmap largeIcon(Context context) {
        Resources res = context.getResources();
        Bitmap largeIcon = BitmapFactory.decodeResource(res, R.drawable.ic_accessibility_white_24dp);
        return largeIcon;
    }

    /**
     * whenever user click notification actions, the notification cancels anyway
     * @param context
     */
    public static void clearNotification(Context context) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        manager.cancelAll();
    }

    /**
     * two methods for setting up two actions in the notification
     *
     * @param context
     * @return
     */
    private static NotificationCompat.Action increaseCount(Context context) {
        Intent increaseIntent = new Intent(context, RemindService.class);
        increaseIntent.setAction(RemindTask.COUNT_INCREASE);

        PendingIntent pendingIntent = PendingIntent.getService(context, INCREASE_INTENT,
                increaseIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Action action = new NotificationCompat.Action(R.drawable.ic_accessibility_white_24dp,
                "plus one", pendingIntent);

        return action;
    }

    private static NotificationCompat.Action ignoreNotification(Context context) {
        Intent ignoreIntent = new Intent(context, RemindService.class);
        ignoreIntent.setAction(RemindTask.NOTIFICATION_CANCEL);

        PendingIntent pendingIntent = PendingIntent.getService(context, IGNORE_NOTIFICATION,
                ignoreIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Action action = new NotificationCompat.Action(R.drawable.ic_pan_tool_black_24dp,
                "cancel", pendingIntent);

        return action;

    }

}
