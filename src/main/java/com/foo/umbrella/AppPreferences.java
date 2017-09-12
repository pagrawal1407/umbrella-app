package com.foo.umbrella;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Parag on 9/7/2017.
 */

public class AppPreferences {
    public static final String KEY_PREFS_LOCATION = "Location";
    public static final String KEY_PREFS_UNIT = "Unit";
    private static final String APP_SHARED_PREFS = AppPreferences.class.getSimpleName(); //  Name of the file -.xml
    private SharedPreferences _sharedPrefs;
    private SharedPreferences.Editor _prefsEditor;

    public AppPreferences(Context context) {
        this._sharedPrefs = context.getSharedPreferences(APP_SHARED_PREFS, Activity.MODE_PRIVATE);
        this._prefsEditor = _sharedPrefs.edit();
    }

    public String getLocation() {
        return _sharedPrefs.getString(KEY_PREFS_LOCATION, "");
    }

    public void saveLocation(String text) {
        _prefsEditor.putString(KEY_PREFS_LOCATION, text);
        _prefsEditor.commit();
    }

    public String getUnit() {
        return _sharedPrefs.getString(KEY_PREFS_UNIT, "");
    }

    public void saveUnit(String text) {
        _prefsEditor.putString(KEY_PREFS_UNIT, text);
        _prefsEditor.commit();
    }

}
