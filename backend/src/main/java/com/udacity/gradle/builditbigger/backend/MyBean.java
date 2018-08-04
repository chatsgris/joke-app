package com.udacity.gradle.builditbigger.backend;

import com.bluecat94.jokeprovider.*;

/** The object model for the data we are sending through endpoints */
public class MyBean {

    private String myData;

    public String getData() {
        return myData;
    }

    public void setData() {
        myData = jokeProvider.getJoke();
    }
}