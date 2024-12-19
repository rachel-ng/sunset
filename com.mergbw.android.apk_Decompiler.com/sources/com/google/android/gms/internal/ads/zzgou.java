package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgou extends zzghi {
    private final zzgql zza;

    public zzgou(zzgql zzgql, @Nullable zzgic zzgic) throws GeneralSecurityException {
        zzc(zzgql, zzgic);
        this.zza = zzgql;
    }

    private static void zzc(zzgql zzgql, @Nullable zzgic zzgic) throws GeneralSecurityException {
        int i = zzgot.zzb[zzgql.zzb().ordinal()];
    }

    public final zzgql zza(@Nullable zzgic zzgic) throws GeneralSecurityException {
        zzc(this.zza, zzgic);
        return this.zza;
    }

    @Nullable
    public final Integer zzb() {
        return this.zza.zzf();
    }
}
