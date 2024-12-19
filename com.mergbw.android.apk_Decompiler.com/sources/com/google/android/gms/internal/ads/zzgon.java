package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public abstract class zzgon {
    private final zzgze zza;
    private final Class zzb;

    /* synthetic */ zzgon(zzgze zzgze, Class cls, zzgom zzgom) {
        this.zza = zzgze;
        this.zzb = cls;
    }

    public static zzgon zzb(zzgol zzgol, zzgze zzgze, Class cls) {
        return new zzgok(zzgze, cls, zzgol);
    }

    public abstract zzghi zza(zzgqq zzgqq, @Nullable zzgic zzgic) throws GeneralSecurityException;

    public final zzgze zzc() {
        return this.zza;
    }

    public final Class zzd() {
        return this.zzb;
    }
}
