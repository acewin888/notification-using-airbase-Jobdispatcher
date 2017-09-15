package com.example.android.notification;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by kevinsun on 9/14/17.
 */

public class RemindService extends IntentService {

    public RemindService() {
        super("ok");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String action = intent.getAction();

        RemindTask.execute(this, action);
    }
}
