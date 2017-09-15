package com.example.android.notification;

import android.content.Context;

import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;

/**
 * Created by kevinsun on 9/15/17.
 */

public class AutoRemind {

    synchronized public static void autoRemind(Context context){

        Driver driver = new GooglePlayDriver(context);

        FirebaseJobDispatcher firebaseJobDispatcher = new FirebaseJobDispatcher(driver);

        Job remindJob = firebaseJobDispatcher.newJobBuilder()
                .setService(RemindJobService.class)

    }
}
