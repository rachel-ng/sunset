package com.google.android.gms.internal.ads;

import com.google.firebase.analytics.FirebaseAnalytics;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzcrn {
    private final zzdvc zza;
    private final zzfhf zzb;

    zzcrn(zzdvc zzdvc, zzfhf zzfhf) {
        this.zza = zzdvc;
        this.zzb = zzfhf;
    }

    public final void zza(long j, int i) {
        String str;
        zzdvb zza2 = this.zza.zza();
        zza2.zzd(this.zzb.zzb.zzb);
        zza2.zzb("action", "ad_closed");
        zza2.zzb("show_time", String.valueOf(j));
        zza2.zzb(FirebaseAnalytics.Param.AD_FORMAT, "app_open_ad");
        int i2 = i - 1;
        if (i2 != 0) {
            str = i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? "u" : "ac" : "cb" : "cc" : "bb";
        } else {
            str = "h";
        }
        zza2.zzb("acr", str);
        zza2.zzf();
    }
}
