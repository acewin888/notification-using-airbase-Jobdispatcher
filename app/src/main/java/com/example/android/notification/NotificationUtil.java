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
                .setAutoCancel(true);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            notificationBuilder.setPriority(Notification.PRIORITY_HIGH);
        }

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        manager.notify(110, notificationBuilder.build());
    }

    private static PendingIntent contentIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);

        return PendingIntent.getActivity(context, INTENT, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private static Bitmap largeIcon(Context context) {
        Resources res = context.getResources();
        Bitmap largeIcon = BitmapFactory.decodeResource(res, R.drawable.ic_accessibility_white_24dp);
        return largeIcon;
    }
}
