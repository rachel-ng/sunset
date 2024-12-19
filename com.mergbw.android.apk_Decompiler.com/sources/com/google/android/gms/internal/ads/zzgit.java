package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final /* synthetic */ class zzgit implements zzgpa {
    public final zzghi zza(zzghx zzghx, Integer num) {
        zzgja zzgja = (zzgja) zzghx;
        int i = zzgiu.zza;
        if (zzgja.zzb() == 16 || zzgja.zzb() == 32) {
            zzgio zzgio = new zzgio((zzgin) null);
            zzgio.zzd(zzgja);
            zzgio.zzc(num);
            zzgio.zza(zzgzf.zzc(zzgja.zzb()));
            zzgio.zzb(zzgzf.zzc(zzgja.zzc()));
            return zzgio.zze();
        }
        throw new GeneralSecurityException("AES key size must be 16 or 32 bytes");
    }
}
