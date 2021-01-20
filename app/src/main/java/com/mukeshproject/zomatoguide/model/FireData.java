package com.mukeshproject.zomatoguide.model;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class FireData extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseDatabase firedatabase = FirebaseDatabase.getInstance();

    }
}
