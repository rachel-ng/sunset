package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgsd {
    public static final zzgsd zza = new zzgsd("SHA1");
    public static final zzgsd zzb = new zzgsd("SHA224");
    public static final zzgsd zzc = new zzgsd("SHA256");
    public static final zzgsd zzd = new zzgsd("SHA384");
    public static final zzgsd zze = new zzgsd("SHA512");
    private final String zzf;

    private zzgsd(String str) {
        this.zzf = str;
    }

    public final String toString() {
        return this.zzf;
    }
}
