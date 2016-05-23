package com.udacity.gradle.builditbigger.services;

import com.example.rodrigo.alencar.myapplication.backend.jokeMakerApi.JokeMakerApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

/**
 * Created by rodrigo.alencar on 5/23/16.
 */
public class JokeMakerApiBuilderHelper {

    public static JokeMakerApi.Builder build() {
        JokeMakerApi.Builder builder = new JokeMakerApi.Builder(AndroidHttp.newCompatibleTransport(),
                new AndroidJsonFactory(), null)
                .setRootUrl("https://finalproject4-1320.appspot.com/_ah/api/");
        return builder;
    }

}
