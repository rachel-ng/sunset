package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final /* synthetic */ class zzgjt implements zzgpa {
    public final zzghi zza(zzghx zzghx, Integer num) {
        zzgjz zzgjz = (zzgjz) zzghx;
        int i = zzgju.zza;
        if (zzgjz.zzb() != 24) {
            zzgjo zzgjo = new zzgjo((zzgjn) null);
            zzgjo.zzc(zzgjz);
            zzgjo.zza(num);
            zzgjo.zzb(zzgzf.zzc(zzgjz.zzb()));
            return zzgjo.zzd();
        }
        throw new GeneralSecurityException("192 bit AES GCM Parameters are not valid");
    }
}
