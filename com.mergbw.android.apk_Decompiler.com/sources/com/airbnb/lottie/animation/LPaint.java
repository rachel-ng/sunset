package com.airbnb.lottie.animation;

import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.os.LocaleList;
import androidx.core.view.ViewCompat;
import com.airbnb.lottie.utils.MiscUtils;

public class LPaint extends Paint {
    public void setTextLocales(LocaleList localeList) {
    }

    public LPaint() {
    }

    public LPaint(int i) {
        super(i);
    }

    public LPaint(PorterDuff.Mode mode) {
        setXfermode(new PorterDuffXfermode(mode));
    }

    public LPaint(int i, PorterDuff.Mode mode) {
        super(i);
        setXfermode(new PorterDuffXfermode(mode));
    }

    public void setAlpha(int i) {
        if (Build.VERSION.SDK_INT < 30) {
            setColor((MiscUtils.clamp(i, 0, 255) << 24) | (getColor() & ViewCompat.MEASURED_SIZE_MASK));
            return;
        }
        super.setAlpha(MiscUtils.clamp(i, 0, 255));
    }
}
