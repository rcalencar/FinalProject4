package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.test.AndroidTestCase;
import android.util.Pair;

import com.udacity.gradle.builditbigger.services.EndpointsAsyncTask;

/**
 * Created by rodrigo.alencar on 5/20/16.
 */
public class EndpointsAsyncTaskTest extends AndroidTestCase {

    public void testNotNull() {
        final String[] string = new String[1];
        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(getContext()) {
            @Override
            protected void onPostExecute(String result) {
                string[0] = result;
            }
        };
        endpointsAsyncTask.execute();
        assertNotNull(string[0]);
    }
}
