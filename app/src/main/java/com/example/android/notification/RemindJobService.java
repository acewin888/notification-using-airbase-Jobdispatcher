package com.example.android.notification;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.AsyncTask;

/**
 * Created by kevinsun on 9/15/17.
 */

public class RemindJobService extends JobService {
    /**
     * becasue the Jobservice runs on the main thread, so it is necessary to use async task for
     * more heavy use case
     *
     * @param jobParameters
     * @return
     */

    private AsyncTask asyncTask;

    @Override
    public boolean onStartJob(final JobParameters jobParameters) {

        asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {

                RemindTask.execute(RemindJobService.this, RemindTask.START_CHARGING);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                jobFinished(jobParameters, false);
            }
        };

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        if (asyncTask != null) {
            asyncTask.cancel(true);
        }
        return true;
    }
}
