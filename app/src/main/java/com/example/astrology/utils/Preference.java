package com.example.astrology.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class Preference {

    static String preference_name = "astrology";

    @SuppressLint("NewApi")
    public static void setBoolean(String key, boolean value, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(preference_name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static boolean getBoolean(String key, boolean defValue, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(preference_name, Context.MODE_PRIVATE);
        boolean value = preferences.getBoolean(key, defValue);
        return value;
    }

    @SuppressLint("NewApi")
    public static void setString(String key, String value, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(preference_name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getString(String key, String defValue, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(preference_name, Context.MODE_PRIVATE);
        String value = preferences.getString(key, defValue);
        return value;
    }

    @SuppressLint("NewApi")
    public static void setLong(String key, long value, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(preference_name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public static long getLong(String key, long defValue, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(preference_name, Context.MODE_PRIVATE);
        long value = preferences.getLong(key, defValue);
        return value;

    }

    @SuppressLint("NewApi")
    public static void setInteger(String key, int value, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(preference_name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static void setFloat(String key, float value, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(preference_name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public static float getFloat(String key, float defValue, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(preference_name, Context.MODE_PRIVATE);
        return preferences.getFloat(key, defValue);
    }

    public static boolean contains(String key, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(preference_name, Context.MODE_PRIVATE);
        return preferences.contains(key);
    }

    public static int getInteger(String key, int defValue, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(preference_name, Context.MODE_PRIVATE);
        int value = preferences.getInt(key, defValue);
        return value;
    }

    public static void clearDriverProfilePreference(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(preference_name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }
}
