package com.example.android.notification;

import android.content.Context;
import android.icu.util.TimeUnit;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

/**
 * Created by kevinsun on 9/15/17.
 */

public class AutoRemind {

    private static final String TAG = "TAG_FOR_FIREBASE";

    private static final int MINUTES = 1;

    private static final int SECONDS = (int) (java.util.concurrent.TimeUnit.MINUTES.toSeconds(MINUTES));

    private static final int SYC_TIME_WINDOW = SECONDS;

    synchronized public static void autoRemind(Context context){

        Driver driver = new GooglePlayDriver(context);

        FirebaseJobDispatcher firebaseJobDispatcher = new FirebaseJobDispatcher(driver);

        Job remindJob = firebaseJobDispatcher.newJobBuilder()
                .setService(RemindJobService.class)

                .setTag(TAG)

                .setConstraints(Constraint.DEVICE_CHARGING)

                .setLifetime(Lifetime.FOREVER)

                .setRecurring(true)

                // set trigger time to be 1 mins to 2 mins
                .setTrigger(Trigger.executionWindow(SECONDS, SECONDS + SYC_TIME_WINDOW))

                .setReplaceCurrent(true)

                .build();

        firebaseJobDispatcher.schedule(remindJob);



    }
}
