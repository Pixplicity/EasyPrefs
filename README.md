EasyPreferences
===============

A small library containing a wrapper/helper for the shared preferences of android.

With this library you can initialize the shared preference inside the oncreate of the Application class of your app.

```Java
public class PrefsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize the Prefs class
        Prefs.iniPrefs(this);
    }
}
```

After initialization you can use simple one line methode calls like `Prefs.putString(key, string)`, `Prefs.putLong(key, long)` or `Prefs.putBoolean(key, boolean)` to save values to the sharedpreference.

If you want to retrieve a String you can simply call `Prefs.getString(key, default value)` now you simply get the String if the Sharedpreferences contain the key or the default String value entered. So you don't use those pesky `contains()` checks or `val != null` checks.


[![Build Status](https://travis-ci.org/aegis123/EasyPreferences.png?branch=master)](https://travis-ci.org/aegis123/EasyPreferences)
