package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;
import android.util.Log;

import com.udacity.gradle.builditbigger.services.EndpointsAsyncTask;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by rodrigo.alencar on 5/20/16.
 */
public class EndpointsAsyncTaskTest extends AndroidTestCase {

    public static final String TAG = "AndroidTestCase";

    public void testNotNull() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);

        Log.d(TAG, "starting");

        final String[] string = new String[1];
        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(getContext(), new EndpointsAsyncTask.EndpointsAsyncTaskCallBack() {
            @Override
            public void result(String result) {
                string[0] = result;
                Log.d(TAG, "result " + result);
                signal.countDown();
            }
        });
        endpointsAsyncTask.execute();
        signal.await(10, TimeUnit.SECONDS);
        assertNotNull(string[0]);
    }
}
