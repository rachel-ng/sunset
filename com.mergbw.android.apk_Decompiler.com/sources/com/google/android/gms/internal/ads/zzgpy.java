package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public abstract class zzgpy {
    private final Class zza;
    private final Class zzb;

    /* synthetic */ zzgpy(Class cls, Class cls2, zzgpx zzgpx) {
        this.zza = cls;
        this.zzb = cls2;
    }

    public static zzgpy zzb(zzgpw zzgpw, Class cls, Class cls2) {
        return new zzgpv(cls, cls2, zzgpw);
    }

    public abstract Object zza(zzghi zzghi) throws GeneralSecurityException;

    public final Class zzc() {
        return this.zza;
    }

    public final Class zzd() {
        return this.zzb;
    }
}
