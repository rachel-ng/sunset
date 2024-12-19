package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgzb implements zzghw {
    private static final byte[] zza = {0};
    private final zzgts zzb;
    private final int zzc;
    private final byte[] zzd;
    private final byte[] zze;

    private zzgzb(zzgre zzgre) throws GeneralSecurityException {
        this.zzb = new zzgyy(zzgre.zzd().zzd(zzghh.zza()));
        this.zzc = zzgre.zzb().zzb();
        this.zzd = zzgre.zzc().zzc();
        if (zzgre.zzb().zzf().equals(zzgrl.zzc)) {
            this.zze = Arrays.copyOf(zza, 1);
        } else {
            this.zze = new byte[0];
        }
    }

    public static zzghw zza(zzgre zzgre) throws GeneralSecurityException {
        return new zzgzb(zzgre);
    }

    public static zzghw zzb(zzgrv zzgrv) throws GeneralSecurityException {
        return new zzgzb(zzgrv);
    }

    public final byte[] zzc(byte[] bArr) throws GeneralSecurityException {
        byte[] bArr2 = this.zze;
        if (bArr2.length > 0) {
            return zzgyd.zzb(this.zzd, this.zzb.zza(zzgyd.zzb(bArr, bArr2), this.zzc));
        }
        return zzgyd.zzb(this.zzd, this.zzb.zza(bArr, this.zzc));
    }

    private zzgzb(zzgrv zzgrv) throws GeneralSecurityException {
        String valueOf = String.valueOf(String.valueOf(zzgrv.zzb().zzf()));
        this.zzb = new zzgza("HMAC".concat(valueOf), new SecretKeySpec(zzgrv.zzd().zzd(zzghh.zza()), "HMAC"));
        this.zzc = zzgrv.zzb().zzb();
        this.zzd = zzgrv.zzc().zzc();
        if (zzgrv.zzb().zzg().equals(zzgse.zzc)) {
            this.zze = Arrays.copyOf(zza, 1);
        } else {
            this.zze = new byte[0];
        }
    }

    public zzgzb(zzgts zzgts, int i) throws GeneralSecurityException {
        this.zzb = zzgts;
        this.zzc = i;
        this.zzd = new byte[0];
        this.zze = new byte[0];
        zzgts.zza(new byte[0], i);
    }
}
