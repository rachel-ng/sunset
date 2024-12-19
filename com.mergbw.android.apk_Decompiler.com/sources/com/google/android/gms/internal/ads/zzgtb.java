package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgtb implements zzgro {
    private final zzgrv zza;

    public zzgtb(zzgrv zzgrv) throws GeneralSecurityException {
        if (zzgoc.zza(2)) {
            this.zza = zzgrv;
            return;
        }
        throw new GeneralSecurityException("Can not use HMAC in FIPS-mode, as BoringCrypto module is not available.");
    }
}
