package com.mergbw.core.utils;

import android.util.Log;

public class MeRGBWLog {
    private static boolean DEBUG = true;
    private static final String TAG = "mytest";
    public static boolean TEST_ENABLE = false;

    public static void i(String str) {
        if (DEBUG) {
            Log.i(TAG, str);
        }
    }

    public static void e(String str) {
        if (DEBUG) {
            Log.e(TAG, str);
        }
    }
}
