package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgta implements zzgro {
    private final zzgre zza;

    public zzgta(zzgre zzgre) throws GeneralSecurityException {
        if (zzgoc.zza(1)) {
            this.zza = zzgre;
            return;
        }
        throw new GeneralSecurityException("Can not use AES-CMAC in FIPS-mode.");
    }
}
