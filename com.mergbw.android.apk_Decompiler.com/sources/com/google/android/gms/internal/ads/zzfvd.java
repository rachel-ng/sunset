package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfvd {
    private final String zza;
    private final long zzb;

    public zzfvd() {
        this.zza = null;
        this.zzb = -1;
    }

    public zzfvd(String str, long j) {
        this.zza = str;
        this.zzb = j;
    }

    public final long zza() {
        return this.zzb;
    }

    public final String zzb() {
        return this.zza;
    }

    public final boolean zzc() {
        return this.zza != null && this.zzb >= 0;
    }
}
