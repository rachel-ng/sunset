package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public abstract class zzgpq {
    private final zzgze zza;
    private final Class zzb;

    /* synthetic */ zzgpq(zzgze zzgze, Class cls, zzgpp zzgpp) {
        this.zza = zzgze;
        this.zzb = cls;
    }

    public static zzgpq zzb(zzgpo zzgpo, zzgze zzgze, Class cls) {
        return new zzgpn(zzgze, cls, zzgpo);
    }

    public abstract zzghx zza(zzgqq zzgqq) throws GeneralSecurityException;

    public final zzgze zzc() {
        return this.zza;
    }

    public final Class zzd() {
        return this.zzb;
    }
}
