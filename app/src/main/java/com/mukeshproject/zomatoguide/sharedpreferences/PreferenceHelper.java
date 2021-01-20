package com.mukeshproject.zomatoguide.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {

    private static final String SHARED_PREFERENCE_KEY = "com.mukeshproject.zomatoguide";

    public static SharedPreferences getShatedPreference(Context context) {
        return context.getSharedPreferences(SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE);
    }

    public static void writeLatitudeToPreference(Context context, String key, double value) {

        SharedPreferences.Editor editor = getShatedPreference(context).edit();
        editor.putString(key, String.valueOf(value));
        editor.apply();
    }

    public static void writeLongitudeToPreference(Context context, String key, double value) {

        SharedPreferences.Editor editor = getShatedPreference(context).edit();
        editor.putString(key, String.valueOf(value));
        editor.apply();
    }

    public static void writeCityNameToPreference(Context context, String key, String value) {

        SharedPreferences.Editor editor = getShatedPreference(context).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void writeEntityIdToPreference(Context context, String key, int value) {

        SharedPreferences.Editor editor = getShatedPreference(context).edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static void writeResIdToPreference(Context context, String key, int value) {

        SharedPreferences.Editor editor = getShatedPreference(context).edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static double getLatitudeFromPreference(Context context, String key) {

        return Double.parseDouble(getShatedPreference(context).getString(key, "11.6643"));
    }

    public static double getLongitudeFromPreference(Context context, String key) {

        return Double.parseDouble(getShatedPreference(context).getString(key, "78.1460"));
    }


    public static String getCityNameFromPreference(Context context, String key) {

        return getShatedPreference(context).getString(key, null);
    }

    public static int getEntityIdFromPreference(Context context, String key) {

        return getShatedPreference(context).getInt(key, 0);
    }

    public static int getResIdFromPreference(Context context, String key) {

        return getShatedPreference(context).getInt(key, 0);
    }
}
