package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.test.espresso.IdlingResource;

import com.bluecat94.jokeactivity.JokeMainActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;


/**
 * Created by mimiliu on 8/4/18.
 */

public class JokeAsyncTask extends AsyncTask<Void, Void, String> {
    private static MyApi myApiService = null;
    private Context mContext;
    private SimpleIdlingResource mIdlingResource;

    JokeAsyncTask(Context context, @Nullable final SimpleIdlingResource idlingResource) {
        this.mContext = context;
        this.mIdlingResource = idlingResource;
    }

    @Override
    protected String doInBackground(Void... contexts) {
        if (mIdlingResource != null) {
            mIdlingResource.setIdleState(false);
        }

        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
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
            String data = myApiService.sayJoke().execute().getData();
            if (mIdlingResource != null) {
                mIdlingResource.setIdleState(true);
            }
            return data;
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String joke) {
        Intent intent = new Intent(mContext, JokeMainActivity.class);
        String JOKE_EXTRA_KEY = "JOKE_EXTRA_KEY";
        intent.putExtra(JOKE_EXTRA_KEY, joke);
        mContext.startActivity(intent);
    }
}
