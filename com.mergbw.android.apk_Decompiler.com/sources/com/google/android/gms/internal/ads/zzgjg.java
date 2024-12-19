package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final /* synthetic */ class zzgjg implements zzgpa {
    public final zzghi zza(zzghx zzghx, Integer num) {
        zzgjm zzgjm = (zzgjm) zzghx;
        int i = zzgjh.zza;
        if (zzgjm.zzc() != 24) {
            zzgjc zzgjc = new zzgjc((zzgjb) null);
            zzgjc.zzc(zzgjm);
            zzgjc.zza(num);
            zzgjc.zzb(zzgzf.zzc(zzgjm.zzc()));
            return zzgjc.zzd();
        }
        throw new GeneralSecurityException("192 bit AES GCM Parameters are not valid");
    }
}
