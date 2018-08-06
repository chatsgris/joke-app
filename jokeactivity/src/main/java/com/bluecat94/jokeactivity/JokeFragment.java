package com.bluecat94.jokeactivity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class JokeFragment extends Fragment {

    String JOKE_EXTRA_KEY = "JOKE_EXTRA_KEY";
    String joke;

    public JokeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(JOKE_EXTRA_KEY, joke);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_joke, container, false);
        TextView jokeTextView = rootView.findViewById(R.id.jokeTextView);

        if(savedInstanceState != null) {
            joke = savedInstanceState.getString(JOKE_EXTRA_KEY);
        } else {
            joke = this.getArguments().getString(JOKE_EXTRA_KEY);
        }
        jokeTextView.setText(joke);
        return rootView;
    }
}
