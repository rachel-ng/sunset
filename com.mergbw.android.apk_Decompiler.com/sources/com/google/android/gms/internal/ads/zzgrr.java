package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgrr implements zzghy {
    private static final zzgrr zza = new zzgrr();

    private zzgrr() {
    }

    static void zzd() throws GeneralSecurityException {
        zzgpi.zza().zzf(zza);
    }

    public final Class zza() {
        return zzgro.class;
    }

    public final Class zzb() {
        return zzgro.class;
    }

    public final /* bridge */ /* synthetic */ Object zzc(zzgqk zzgqk) throws GeneralSecurityException {
        if (zzgqk.zzb() != null) {
            for (List<zzgqi> it : zzgqk.zze()) {
                for (zzgqi zze : it) {
                    zzgro zzgro = (zzgro) zze.zze();
                }
            }
            return new zzgrq(zzgqk, (zzgrp) null);
        }
        throw new GeneralSecurityException("no primary in primitive set");
    }
}
