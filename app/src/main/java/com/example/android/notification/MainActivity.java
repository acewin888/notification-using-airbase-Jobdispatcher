package com.example.android.notification;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.os.Build.VERSION_CODES.N;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private ImageButton imageButton;

    private TextView numberView, notificationView;

    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageButton = (ImageButton) findViewById(R.id.imageButton);

        numberView = (TextView) findViewById(R.id.number_display);

        notificationView = (TextView) findViewById(R.id.display);

        updateCount();
        updateNotification();
        AutoRemind.autoRemind(this);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mToast != null) {
                    mToast.cancel();
                }
                mToast = Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_SHORT);
                mToast.show();

                Intent intent = new Intent(MainActivity.this, RemindService.class);

                intent.setAction(RemindTask.COUNT_INCREASE);
                startService(intent);

                Intent intent1 = new Intent(MainActivity.this, RemindService.class);
                intent1.setAction(RemindTask.NOTIFICATION_INCREASE);
                startService(intent1);

                NotificationUtil.popNotification(MainActivity.this);

            }
        });

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    private void updateCount() {
        int count = PreferenceUtil.getCount(this);

        numberView.setText("" + count);
    }

    private void updateNotification() {
        int notificationCount = PreferenceUtil.getNotificationCount(this);

        notificationView.setText("the notification appears " + notificationCount + " times");
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        if (s.equals(PreferenceUtil.COUNT)) {
            updateCount();
        } else if (s.equals(PreferenceUtil.NOTIFICATION_COUNT)) {
            updateNotification();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }
}
