package com.google.android.gms.internal.ads;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.view.Display;
import com.google.android.material.chip.Chip$$ExternalSyntheticApiModelOutline0;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzabh {
    public static boolean zza(Context context) {
        DisplayManager displayManager = (DisplayManager) context.getSystemService("display");
        Display display = displayManager != null ? displayManager.getDisplay(0) : null;
        if (display == null || !Chip$$ExternalSyntheticApiModelOutline0.m(display)) {
            return false;
        }
        for (int i : Chip$$ExternalSyntheticApiModelOutline0.m(display).getSupportedHdrTypes()) {
            if (i == 1) {
                return true;
            }
        }
        return false;
    }
}
