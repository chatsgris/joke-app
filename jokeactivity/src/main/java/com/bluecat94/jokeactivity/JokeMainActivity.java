package com.bluecat94.jokeactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class JokeMainActivity extends AppCompatActivity {

    String JOKE_EXTRA_KEY = "JOKE_EXTRA_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_main);

        Intent intent = getIntent();
        String joke = intent.getExtras().getString(JOKE_EXTRA_KEY);

        Bundle bundle = new Bundle();
        bundle.putString(JOKE_EXTRA_KEY, joke);
        JokeFragment jokeFragment = new JokeFragment();
        jokeFragment.setArguments(bundle);

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.joke_activity_container, jokeFragment).commit();
    }
}
