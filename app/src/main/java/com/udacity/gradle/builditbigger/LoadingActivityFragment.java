package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.rodrigoalencar.jokeui.JokeUI;
import com.rodrigoalencar.jokeui.JokeUIFragment;
import com.udacity.gradle.builditbigger.services.EndpointsAsyncTask;

public class LoadingActivityFragment extends Fragment implements EndpointsAsyncTask.EndpointsAsyncTaskCallBack {

    private static final String TAG = "LoadingActivityFragment";
    private ProgressBar spinner;
    private EndpointsAsyncTask asyncTask;

    public LoadingActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    @Override
    public void onPause() {
        super.onPause();

        if(asyncTask != null && asyncTask.getStatus() != AsyncTask.Status.FINISHED) {
            Log.d(TAG, "asyncTask.cancel(true)");

            asyncTask.cancel(true);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_loading, container, false);

        spinner = (ProgressBar) root.findViewById(R.id.progressBar);

        showJoke();

        return root;
    }

    private void showJoke() {
        asyncTask = new EndpointsAsyncTask(getContext(), this);
        asyncTask.execute();
    }

    @Override
    public void result(String result) {
        if(result == null) {
            result = getString(R.string.server_error);
        }
        Intent intent = new Intent(getContext(), JokeUI.class);
        intent.putExtra(JokeUIFragment.ARG_JOKE_TEXT, result);
        getContext().startActivity(intent);
    }
}
