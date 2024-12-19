package com.mergbw.core.utils;

import android.app.Activity;
import android.app.Application;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class UIUtils {
    public static float dp2px(float f) {
        return TypedValue.applyDimension(1, f, Resources.getSystem().getDisplayMetrics());
    }

    public static float sp2px(float f) {
        return TypedValue.applyDimension(2, f, Resources.getSystem().getDisplayMetrics());
    }

    public static void setCustomDensity(Activity activity, Application application) {
        DisplayMetrics displayMetrics = application.getResources().getDisplayMetrics();
        float f = ((float) displayMetrics.widthPixels) / 1920.0f;
        float f2 = (displayMetrics.scaledDensity / displayMetrics.density) * f;
        int i = (int) (160.0f * f);
        displayMetrics.density = f;
        displayMetrics.scaledDensity = f2;
        displayMetrics.densityDpi = i;
        DisplayMetrics displayMetrics2 = activity.getResources().getDisplayMetrics();
        displayMetrics2.density = f;
        displayMetrics2.scaledDensity = f2;
        displayMetrics2.densityDpi = i;
    }
}
