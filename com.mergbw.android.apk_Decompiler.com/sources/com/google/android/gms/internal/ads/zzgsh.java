package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgsh {
    @Deprecated
    static final zzgxq zza;
    @Deprecated
    static final zzgxq zzb;
    @Deprecated
    static final zzgxq zzc;

    static {
        zzgxq zzc2 = zzgxq.zzc();
        zza = zzc2;
        zzb = zzc2;
        zzc = zzc2;
        try {
            zza();
        } catch (GeneralSecurityException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static void zza() throws GeneralSecurityException {
        zzgsn.zzd();
        zzgrr.zzd();
        zzgsa.zza(true);
        if (!zzgod.zzb()) {
            zzgri.zzd(true);
        }
    }
}
