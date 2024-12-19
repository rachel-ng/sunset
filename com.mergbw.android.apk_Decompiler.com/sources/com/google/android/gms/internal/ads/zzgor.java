package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public abstract class zzgor {
    private final Class zza;
    private final Class zzb;

    /* synthetic */ zzgor(Class cls, Class cls2, zzgoq zzgoq) {
        this.zza = cls;
        this.zzb = cls2;
    }

    public static zzgor zzb(zzgop zzgop, Class cls, Class cls2) {
        return new zzgoo(cls, cls2, zzgop);
    }

    public abstract zzgqq zza(zzghi zzghi, @Nullable zzgic zzgic) throws GeneralSecurityException;

    public final Class zzc() {
        return this.zza;
    }

    public final Class zzd() {
        return this.zzb;
    }
}
