package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgie {
    public static zzghx zza(byte[] bArr) throws GeneralSecurityException {
        try {
            zzgwm zzf = zzgwm.zzf(bArr, zzhay.zza());
            zzgpl zzc = zzgpl.zzc();
            zzgqm zza = zzgqm.zza(zzf);
            if (!zzc.zzk(zza)) {
                return new zzgov(zza);
            }
            return zzc.zzb(zza);
        } catch (IOException e) {
            throw new GeneralSecurityException("Failed to parse proto", e);
        }
    }

    public static byte[] zzb(zzghx zzghx) throws GeneralSecurityException {
        return ((zzgqm) zzgpl.zzc().zze(zzghx, zzgqm.class)).zzc().zzaV();
    }
}
