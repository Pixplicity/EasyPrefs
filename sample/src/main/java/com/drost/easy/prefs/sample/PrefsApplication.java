package com.drost.easy.prefs.sample;

import com.drost.easy.prefs.Prefs;

import android.app.Application;

public class PrefsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize the Prefs class
        Prefs.iniPrefs(this);
    }
}
