package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zzga {
    public String zza;
    public Bundle zzb;
    private String zzc;
    private long zzd;

    public final zzbd zza() {
        return new zzbd(this.zza, new zzbc(new Bundle(this.zzb)), this.zzc, this.zzd);
    }

    public static zzga zza(zzbd zzbd) {
        return new zzga(zzbd.zza, zzbd.zzc, zzbd.zzb.zzb(), zzbd.zzd);
    }

    public final String toString() {
        String str = this.zzc;
        String str2 = this.zza;
        String valueOf = String.valueOf(this.zzb);
        return "origin=" + str + ",name=" + str2 + ",params=" + valueOf;
    }

    private zzga(String str, String str2, Bundle bundle, long j) {
        this.zza = str;
        this.zzc = str2;
        this.zzb = bundle == null ? new Bundle() : bundle;
        this.zzd = j;
    }
}
