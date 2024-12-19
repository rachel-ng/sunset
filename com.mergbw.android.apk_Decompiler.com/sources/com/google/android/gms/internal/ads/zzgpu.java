package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public abstract class zzgpu {
    private final Class zza;
    private final Class zzb;

    /* synthetic */ zzgpu(Class cls, Class cls2, zzgpt zzgpt) {
        this.zza = cls;
        this.zzb = cls2;
    }

    public static zzgpu zzb(zzgps zzgps, Class cls, Class cls2) {
        return new zzgpr(cls, cls2, zzgps);
    }

    public abstract zzgqq zza(zzghx zzghx) throws GeneralSecurityException;

    public final Class zzc() {
        return this.zza;
    }

    public final Class zzd() {
        return this.zzb;
    }
}
