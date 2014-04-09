package com.pixplicity.easyprefs.library;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Prefs {

    private static String KEY;

    private static SharedPreferences mPrefs;

    private static Context mContext;

    /**
     * Initialize the Prefs helper class to keep a reference to the SharedPreference for this
     * application the SharedPreference will use the package name of the application as the Key.
     */
    public static void initPrefs(Context context) {
        if (mPrefs == null) {
            mContext = context;
            KEY = mContext.getPackageName();
            if (KEY == null) {
                throw new NullPointerException("Prefs key may not be null");
            }
            mPrefs = mContext.getSharedPreferences(KEY, Context.MODE_PRIVATE);

        }
    }

    /**
     * Returns an instance of the shared preference for this app.
     *
     * @return an Instance of the SharedPreference
     */
    public static SharedPreferences getPreferences() {
        if (mPrefs != null) {
            return mPrefs;

        }
        throw new RuntimeException(
                "Prefs class not correctly instantiated please call Prefs.iniPrefs(context) in the Application class onCreate.");
    }

    /**
     * @see android.content.SharedPreferences#getAll()
     */
    public static Map<String, ?> getAll() {
        return getPreferences().getAll();
    }

    /**
     * @see android.content.SharedPreferences#getInt(String, int)
     */
    public static int getInt(final String key, final int defValue) {
        return getPreferences().getInt(key, defValue);
    }

    /**
     * @see android.content.SharedPreferences#getBoolean(String, boolean)
     */
    public static boolean getBoolean(final String key, final boolean defValue) {
        return getPreferences().getBoolean(key, defValue);
    }

    /**
     * @see android.content.SharedPreferences#getLong(String, long)
     */
    public static long getLong(final String key, final long defValue) {
        return getPreferences().getLong(key, defValue);
    }

    /**
     * @see android.content.SharedPreferences#getFloat(String, float)
     */
    public static float getFloat(final String key, final float defValue) {
        return getPreferences().getFloat(key, defValue);
    }

    /**
     * @see android.content.SharedPreferences#getString(String, String)
     */
    public static String getString(final String key, final String defValue) {
        return getPreferences().getString(key, defValue);
    }


    /**
     * @see android.content.SharedPreferences#getStringSet(String, java.util.Set)
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static Set<String> getStringSet(final String key, final Set<String> defValue) {
        SharedPreferences prefs = getPreferences();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return prefs.getStringSet(key, defValue);
        } else {
            if (prefs.contains(key + "#LENGTH")) {
                HashSet<String> set = new HashSet<String>();
                // Workaround for pre-HC's lack of StringSets
                int stringSetLength = prefs.getInt(key + "#LENGTH", -1);
                if (stringSetLength >= 0) {
                    for (int i = 0; i < stringSetLength; i++) {
                        prefs.getString(key + "[" + i + "]", null);
                    }
                }
                return set;
            }
        }
        return null;
    }

    /**
     * @see android.content.SharedPreferences.Editor#putLong(String, long)
     */
    public static boolean putLong(final String key, final long value) {
        final Editor editor = getPreferences().edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    /**
     * @see android.content.SharedPreferences.Editor#putInt(String, int)
     */
    public static boolean putInt(final String key, final int value) {
        final Editor editor = getPreferences().edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    /**
     * @see android.content.SharedPreferences.Editor#putFloat(String, float)
     */
    public static boolean putFloat(final String key, final float value) {
        final Editor editor = getPreferences().edit();
        editor.putFloat(key, value);
        return editor.commit();
    }

    /**
     * @see android.content.SharedPreferences.Editor#putBoolean(String, boolean)
     */
    public static boolean putBoolean(final String key, final boolean value) {
        final Editor editor = getPreferences().edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    /**
     * @see android.content.SharedPreferences.Editor#putString(String, String)
     */
    public static boolean putString(final String key, final String value) {
        final Editor editor = getPreferences().edit();
        editor.putString(key, value);
        return editor.commit();
    }

    /**
     * @see android.content.SharedPreferences.Editor#putStringSet(String, java.util.Set)
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static boolean putStringSet(final String key, final Set<String> value) {
        final Editor editor = getPreferences().edit();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            editor.putStringSet(key, value);
        } else {
            // Workaround for pre-HC's lack of StringSets
            int stringSetLength = 0;
            if (mPrefs.contains(key + "#LENGTH")) {
                // First read what the value was
                stringSetLength = mPrefs.getInt(key + "#LENGTH", -1);
            }
            editor.putInt(key + "#LENGTH", value.size());
            int i = 0;
            Iterator<String> iter = value.iterator();
            while (iter.hasNext()) {
                editor.putString(key + "[" + i + "]", iter.next());
                i++;
            }
            for (; i < stringSetLength; i++) {
                // Remove any remaining values
                editor.remove(key + "[" + i + "]");
            }
        }
        return editor.commit();
    }

    /**
     * @see android.content.SharedPreferences.Editor#remove(String)
     */
    public static boolean remove(final String key) {
        SharedPreferences prefs = getPreferences();
        final Editor editor = prefs.edit();
        if (prefs.contains(key + "#LENGTH")) {
            // Workaround for pre-HC's lack of StringSets
            int stringSetLength = prefs.getInt(key + "#LENGTH", -1);
            if (stringSetLength >= 0) {
                editor.remove(key + "#LENGTH");
                for (int i = 0; i < stringSetLength; i++) {
                    editor.remove(key + "[" + i + "]");
                }
            }
        }
        editor.remove(key);
        return editor.commit();
    }

    /**
     * @see android.content.SharedPreferences#contains(String)
     */
    public static boolean contains(final String key) {
        return getPreferences().contains(key);
    }
}
