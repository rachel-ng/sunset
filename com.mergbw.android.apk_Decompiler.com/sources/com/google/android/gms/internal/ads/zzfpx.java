package com.google.android.gms.internal.ads;

import android.app.UiModeManager;
import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfpx {
    private static UiModeManager zza;

    public static zzfop zza() {
        UiModeManager uiModeManager = zza;
        if (uiModeManager == null) {
            return zzfop.OTHER;
        }
        int currentModeType = uiModeManager.getCurrentModeType();
        if (currentModeType == 1) {
            return zzfop.MOBILE;
        }
        if (currentModeType != 4) {
            return zzfop.OTHER;
        }
        return zzfop.CTV;
    }

    public static void zzb(Context context) {
        if (context != null) {
            zza = (UiModeManager) context.getSystemService("uimode");
        }
    }
}
