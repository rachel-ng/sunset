package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgll {
    public static final zzgll zza = new zzgll("ASSUME_AES_GCM");
    public static final zzgll zzb = new zzgll("ASSUME_XCHACHA20POLY1305");
    public static final zzgll zzc = new zzgll("ASSUME_CHACHA20POLY1305");
    public static final zzgll zzd = new zzgll("ASSUME_AES_CTR_HMAC");
    public static final zzgll zze = new zzgll("ASSUME_AES_EAX");
    public static final zzgll zzf = new zzgll("ASSUME_AES_GCM_SIV");
    private final String zzg;

    private zzgll(String str) {
        this.zzg = str;
    }

    public final String toString() {
        return this.zzg;
    }
}
