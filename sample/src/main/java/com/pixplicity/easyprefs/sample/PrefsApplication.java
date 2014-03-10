package com.pixplicity.easyprefs.sample;


import com.pixplicity.easyprefs.library.Prefs;

import android.app.Application;

public class PrefsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize the Prefs class
        Prefs.iniPrefs(this);
    }
}
