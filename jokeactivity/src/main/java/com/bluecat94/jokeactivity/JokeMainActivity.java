package com.bluecat94.jokeactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
public class JokeMainActivity extends AppCompatActivity {

    String JOKE_EXTRA_KEY = "JOKE_EXTRA_KEY";
    String joke;
    JokeFragment jokeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();

        if (savedInstanceState != null) {
            jokeFragment = (JokeFragment) fragmentManager.getFragment(savedInstanceState, "JOKE_FRAGMENT");
            fragmentManager.beginTransaction()
                    .replace(R.id.joke_activity_container, jokeFragment).commit();
        } else {
            Intent intent = getIntent();
            joke = intent.getExtras().getString(JOKE_EXTRA_KEY);
            Bundle bundle = new Bundle();
            bundle.putString(JOKE_EXTRA_KEY, joke);
            jokeFragment = new JokeFragment();
            jokeFragment.setArguments(bundle);

            fragmentManager.beginTransaction()
                    .add(R.id.joke_activity_container, jokeFragment).commit();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(JOKE_EXTRA_KEY, joke);
        getSupportFragmentManager().putFragment(outState, "JOKE_FRAGMENT", jokeFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.joke_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
