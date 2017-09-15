package com.example.android.notification;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.AsyncTask;

/**
 * Created by kevinsun on 9/15/17.
 */

public class RemindJobService extends com.firebase.jobdispatcher.JobService {
    /**
     * becasue the Jobservice runs on the main thread, so it is necessary to use async task for
     * more heavy use case
     *
     * @param jobParameters
     * @return
     */

    private AsyncTask asyncTask;


    @Override
    public boolean onStartJob(com.firebase.jobdispatcher.JobParameters job) {

        asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {

                return null;
            }
        };
        return true;
    }

    @Override
    public boolean onStopJob(com.firebase.jobdispatcher.JobParameters job) {

        if(asyncTask != null){
            asyncTask.cancel(true);
        }
        return true;
    }
}
