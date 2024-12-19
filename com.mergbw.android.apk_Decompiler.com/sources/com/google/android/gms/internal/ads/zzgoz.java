package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final /* synthetic */ class zzgoz implements zzgpa {
    public final zzghi zza(zzghx zzghx, Integer num) {
        int i = zzgpb.zza;
        zzgwm zzc = ((zzgov) zzghx).zzb().zzc();
        zzghj zzb = zzgoj.zzc().zzb(zzc.zzi());
        if (zzgoj.zzc().zze(zzc.zzi())) {
            zzgwh zza = zzb.zza(zzc.zzh());
            return new zzgou(zzgql.zza(zza.zzg(), zza.zzf(), zza.zzc(), zzc.zzg(), num), zzghh.zza());
        }
        throw new GeneralSecurityException("Creating new keys is not allowed.");
    }
}
