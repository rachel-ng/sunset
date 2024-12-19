package com.google.android.gms.internal.ads;

import android.view.View;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfpd extends zzfpg {
    private static final zzfpd zzb = new zzfpd();

    private zzfpd() {
    }

    public static zzfpd zza() {
        return zzb;
    }

    public final void zzb(boolean z) {
        for (zzfon zzg : zzfpe.zza().zzc()) {
            zzg.zzg().zzk(z);
        }
    }

    public final boolean zzc() {
        for (zzfon zzf : zzfpe.zza().zzb()) {
            View zzf2 = zzf.zzf();
            if (zzf2 != null && zzf2.hasWindowFocus()) {
                return true;
            }
        }
        return false;
    }
}
