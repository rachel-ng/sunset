package com.clj.fastble.utils;

import android.util.Log;

public final class BleLog {
    private static final String defaultTag = "FastBle";
    public static boolean isPrint = true;

    public static void d(String str) {
        if (isPrint && str != null) {
            Log.d(defaultTag, str);
        }
    }

    public static void i(String str) {
        if (isPrint && str != null) {
            Log.i(defaultTag, str);
        }
    }

    public static void w(String str) {
        if (isPrint && str != null) {
            Log.w(defaultTag, str);
        }
    }

    public static void e(String str) {
        if (isPrint && str != null) {
            Log.e(defaultTag, str);
        }
    }
}
