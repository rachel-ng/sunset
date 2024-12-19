package com.google.android.gms.ads.internal.util;

import android.app.Activity;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.client.zzay;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.ads.internal.util.client.zzf;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.internal.ads.zzbep;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public class zzv extends zzu {
    static final boolean zze(int i, int i2, int i3) {
        return Math.abs(i - i2) <= i3;
    }

    public final boolean zzd(Activity activity, Configuration configuration) {
        if (!((Boolean) zzba.zzc().zza(zzbep.zzeO)).booleanValue()) {
            return false;
        }
        if (((Boolean) zzba.zzc().zza(zzbep.zzeQ)).booleanValue()) {
            return activity.isInMultiWindowMode();
        }
        zzay.zzb();
        int zzy = zzf.zzy(activity, configuration.screenHeightDp);
        int zzy2 = zzf.zzy(activity, configuration.screenWidthDp);
        zzu.zzp();
        DisplayMetrics zzt = zzt.zzt((WindowManager) activity.getApplicationContext().getSystemService("window"));
        int i = zzt.heightPixels;
        int i2 = zzt.widthPixels;
        int identifier = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        int dimensionPixelSize = identifier > 0 ? activity.getResources().getDimensionPixelSize(identifier) : 0;
        int round = ((int) Math.round(((double) activity.getResources().getDisplayMetrics().density) + 0.5d)) * ((Integer) zzba.zzc().zza(zzbep.zzeM)).intValue();
        if (!zze(i, zzy + dimensionPixelSize, round)) {
            return true;
        }
        if (zze(i2, zzy2, round)) {
            return false;
        }
        return true;
    }
}
