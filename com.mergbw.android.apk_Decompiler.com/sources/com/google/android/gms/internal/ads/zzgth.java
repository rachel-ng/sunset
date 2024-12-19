package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgth implements zzghw {
    private final zzghw zza;
    private final zzgxn zzb;
    private final byte[] zzc;

    private zzgth(zzghw zzghw, zzgxn zzgxn, byte[] bArr) {
        this.zza = zzghw;
        this.zzb = zzgxn;
        this.zzc = bArr;
    }

    public static zzghw zza(zzgou zzgou) throws GeneralSecurityException {
        byte[] bArr;
        zzgql zza2 = zzgou.zza(zzghh.zza());
        zzgwe zza3 = zzgwh.zza();
        zza3.zzb(zza2.zzg());
        zza3.zzc(zza2.zze());
        zza3.zza(zza2.zzb());
        zzghw zzghw = (zzghw) zzgib.zzb((zzgwh) zza3.zzbr(), zzghw.class);
        zzgxn zzc2 = zza2.zzc();
        zzgxn zzgxn = zzgxn.UNKNOWN_PREFIX;
        int ordinal = zzc2.ordinal();
        if (ordinal != 1) {
            if (ordinal != 2) {
                if (ordinal == 3) {
                    bArr = zzgpm.zza.zzc();
                } else if (ordinal != 4) {
                    throw new GeneralSecurityException("unknown output prefix type");
                }
            }
            bArr = zzgpm.zza(zzgou.zzb().intValue()).zzc();
        } else {
            bArr = zzgpm.zzb(zzgou.zzb().intValue()).zzc();
        }
        return new zzgth(zzghw, zzc2, bArr);
    }
}
