package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgns implements zzggy {
    private final zzggy zza;
    private final byte[] zzb;

    private zzgns(zzggy zzggy, byte[] bArr) {
        this.zza = zzggy;
        int length = bArr.length;
        if (length == 0 || length == 5) {
            this.zzb = bArr;
            return;
        }
        throw new IllegalArgumentException("identifier has an invalid length");
    }

    public static zzggy zzb(zzgou zzgou) throws GeneralSecurityException {
        byte[] bArr;
        zzgql zza2 = zzgou.zza(zzghh.zza());
        zzgwe zza3 = zzgwh.zza();
        zza3.zzb(zza2.zzg());
        zza3.zzc(zza2.zze());
        zza3.zza(zza2.zzb());
        zzggy zzggy = (zzggy) zzgib.zzb((zzgwh) zza3.zzbr(), zzggy.class);
        zzgxn zzc = zza2.zzc();
        zzgxn zzgxn = zzgxn.UNKNOWN_PREFIX;
        int ordinal = zzc.ordinal();
        if (ordinal != 1) {
            if (ordinal != 2) {
                if (ordinal == 3) {
                    bArr = zzgpm.zza.zzc();
                } else if (ordinal != 4) {
                    throw new GeneralSecurityException("unknown output prefix type ".concat(String.valueOf(String.valueOf(zzc))));
                }
            }
            bArr = zzgpm.zza(zzgou.zzb().intValue()).zzc();
        } else {
            bArr = zzgpm.zzb(zzgou.zzb().intValue()).zzc();
        }
        return new zzgns(zzggy, bArr);
    }

    public static zzggy zzc(zzggy zzggy, zzgze zzgze) {
        return new zzgns(zzggy, zzgze.zzc());
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] bArr3 = this.zzb;
        if (bArr3.length == 0) {
            return this.zza.zza(bArr, bArr2);
        }
        if (zzgra.zzc(bArr3, bArr)) {
            return this.zza.zza(Arrays.copyOfRange(bArr, 5, bArr.length), bArr2);
        }
        throw new GeneralSecurityException("wrong prefix");
    }
}
