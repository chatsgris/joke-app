package com.bluecat94.jokeactivity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class JokeFragment extends Fragment {

    String JOKE_EXTRA_KEY = "JOKE_EXTRA_KEY";

    public JokeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_joke, container, false);
        TextView jokeTextView = rootView.findViewById(R.id.jokeTextView);
        String joke = this.getArguments().getString(JOKE_EXTRA_KEY);
        jokeTextView.setText(joke);
        return rootView;
    }
}
