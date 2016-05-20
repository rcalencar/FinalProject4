package com.udacity.gradle.builditbigger.services;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.rodrigo.alencar.myapplication.backend.jokeMakerApi.JokeMakerApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.util.Strings;
import com.rodrigoalencar.jokeui.JokeUI;
import com.rodrigoalencar.jokeui.JokeUIFragment;

import java.io.IOException;

/**
 * Created by rodrigo.alencar on 5/20/16.
 */
public class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {
    private static JokeMakerApi myApiService = null;
    private Context context;

    public EndpointsAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Void... params) {
        if(myApiService == null) {  // Only do this once
            JokeMakerApi.Builder builder = new JokeMakerApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        try {
            return myApiService.makeJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if(Strings.isNullOrEmpty(result)) {
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(context, JokeUI.class);
            intent.putExtra(JokeUIFragment.ARG_JOKE_TEXT, result);
            context.startActivity(intent);
        }
    }
}