package com.globant.equattrocchio.cleanarchitecture;

import android.app.Application;

import io.realm.Realm;

public class GlobalState extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
    }
}
