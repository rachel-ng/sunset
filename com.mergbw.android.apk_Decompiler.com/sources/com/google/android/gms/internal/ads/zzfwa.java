package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfwa extends zzfwt {
    private String zza;
    private String zzb;

    zzfwa() {
    }

    public final zzfwt zza(String str) {
        this.zzb = str;
        return this;
    }

    public final zzfwt zzb(String str) {
        this.zza = str;
        return this;
    }

    public final zzfwu zzc() {
        return new zzfwc(this.zza, this.zzb, (zzfwb) null);
    }
}
