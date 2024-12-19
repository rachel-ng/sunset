package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfvr extends zzfwd {
    private String zza;
    private String zzb;

    zzfvr() {
    }

    public final zzfwd zza(String str) {
        this.zzb = str;
        return this;
    }

    public final zzfwd zzb(String str) {
        this.zza = str;
        return this;
    }

    public final zzfwe zzc() {
        return new zzfvt(this.zza, this.zzb, (zzfvs) null);
    }
}
