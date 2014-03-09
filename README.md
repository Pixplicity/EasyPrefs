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
