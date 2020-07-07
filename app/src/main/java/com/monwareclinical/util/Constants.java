package com.monwareclinical.util;

import android.content.Context;

import com.monwareclinical.model.User;

public class Constants {

    static Constants instance;

    Context context;

    User user;

    Constants(Context context) {
        this.context = context;
    }

    public static synchronized Constants getInstance(Context context) {
        if (instance == null)
            instance = new Constants(context);
        return instance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
