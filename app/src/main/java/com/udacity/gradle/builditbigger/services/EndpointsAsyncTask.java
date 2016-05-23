package com.udacity.gradle.builditbigger.services;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.rodrigo.alencar.myapplication.backend.jokeMakerApi.JokeMakerApi;
import com.google.api.client.util.Strings;
import com.rodrigoalencar.jokeui.JokeUI;
import com.rodrigoalencar.jokeui.JokeUIFragment;

import java.io.IOException;

/**
 * Created by rodrigo.alencar on 5/20/16.
 */
public class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {
    private static JokeMakerApi myApiService = null;

    private final EndpointsAsyncTaskCallBack endpointsAsyncTaskCallBack;
    private final Context context;

    public interface EndpointsAsyncTaskCallBack {
        void result(String result);
    }

    public EndpointsAsyncTask(Context context, EndpointsAsyncTaskCallBack endpointsAsyncTaskCallBack) {
        this.context = context;
        this.endpointsAsyncTaskCallBack = endpointsAsyncTaskCallBack;
    }

    @Override
    protected String doInBackground(Void... params) {
        if(myApiService == null) {  // Only do this once
            JokeMakerApi.Builder builder = JokeMakerApiBuilderHelper.build();

            myApiService = builder.build();
        }

        try {
            return myApiService.makeJoke().execute().getData();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        endpointsAsyncTaskCallBack.result(result);
    }
}