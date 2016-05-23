package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.test.AndroidTestCase;
import android.util.Pair;

import com.udacity.gradle.builditbigger.services.EndpointsAsyncTask;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by rodrigo.alencar on 5/20/16.
 */
public class EndpointsAsyncTaskTest extends AndroidTestCase {

    public void testNotNull() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);

        final String[] string = new String[1];
        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(getContext()) {
            @Override
            protected void onPostExecute(String result) {
                string[0] = result;
                signal.countDown();
            }
        };
        endpointsAsyncTask.execute();
        signal.await(10, TimeUnit.SECONDS);
        assertNotNull(string[0]);
    }
}
