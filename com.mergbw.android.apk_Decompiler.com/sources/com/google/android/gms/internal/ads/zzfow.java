package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfow {
    private final String zza;
    private final String zzb;

    private zzfow(String str, String str2) {
        this.zza = str;
        this.zzb = str2;
    }

    public static zzfow zza(String str, String str2) {
        zzfqd.zzb(str, "Name is null or empty");
        zzfqd.zzb(str2, "Version is null or empty");
        return new zzfow(str, str2);
    }

    public final String zzb() {
        return this.zza;
    }

    public final String zzc() {
        return this.zzb;
    }
}
