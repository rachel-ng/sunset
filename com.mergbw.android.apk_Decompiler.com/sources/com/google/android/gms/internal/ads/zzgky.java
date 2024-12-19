package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final /* synthetic */ class zzgky implements zzgpw {
    public final Object zza(zzghi zzghi) {
        zzgli zzgli = (zzgli) zzghi;
        int i = zzgkz.zza;
        String zzd = zzgli.zzb().zzd();
        zzgii zzb = zzgli.zzb().zzb();
        zzggy zzb2 = zzghv.zza(zzd).zzb();
        int i2 = zzgkw.zza;
        try {
            return zzgns.zzc(new zzgkw(zzgwm.zzf(zzgie.zzb(zzb), zzhay.zza()), zzb2), zzgli.zzc());
        } catch (zzhcd e) {
            throw new GeneralSecurityException(e);
        }
    }
}
