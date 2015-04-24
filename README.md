EasyPreferences
===============
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-EasyPreferences-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/1488)
[![Build Status](https://travis-ci.org/Pixplicity/EasyPreferences.svg?branch=master)](https://travis-ci.org/Pixplicity/EasyPreferences)

A small library containing a wrapper/helper for the shared preferences of Android.

With this library you can initialize the shared preference inside the onCreate of the Application class of your app.

```Java
public class PrefsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize the Prefs class
        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();
    }
}
```

After initialization you can use simple one line method calls like `Prefs.putString(key, string)`, `Prefs.putLong(key, long)` or `Prefs.putBoolean(key, boolean)` to save values to the Shared Preferences anywhere in your app.

Retrieving data from the Shared Preferences can be as simple as `String data = Prefs.getString(key, default value)` now you simply get the String if the Shared Preferences contains the key or the default String value entered. So you don't use those pesky `contains()` checks or `data != null` checks.

For some examples see the sample App.

# Download
Download the latest [AAR](http://search.maven.org/#search|ga|1|g:"com.pixplicity.easyprefs") or grab via Maven:
```XML
<dependency>
  <groupId>com.pixplicity.easyprefs</groupId>
  <artifactId>library</artifactId>
  <version>1.5</version>
  <type>aar</type>
</dependency>
```

or Gradle:
```Gradle
compile 'com.pixplicity.easyprefs:library:1.5'
```

# License
```
Copyright 2014 Pixplicity, bv.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
