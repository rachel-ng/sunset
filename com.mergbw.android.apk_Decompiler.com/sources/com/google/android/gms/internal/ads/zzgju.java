package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgju {
    public static final /* synthetic */ int zza = 0;
    private static final zzgpy zzb = zzgpy.zzb(new zzgjr(), zzgjq.class, zzggy.class);
    private static final zzghj zzc = zzgos.zzd("type.googleapis.com/google.crypto.tink.AesGcmKey", zzggy.class, zzgwg.SYMMETRIC, zzgvc.zzg());
    private static final zzgpc zzd = new zzgjs();
    private static final zzgpa zze = new zzgjt();
    private static final int zzf = 2;

    public static void zza(boolean z) throws GeneralSecurityException {
        int i = zzf;
        if (zzgoc.zza(i)) {
            int i2 = zzgmw.zza;
            zzgmw.zze(zzgpl.zzc());
            zzgpi.zza().zze(zzb);
            zzgph zzb2 = zzgph.zzb();
            HashMap hashMap = new HashMap();
            hashMap.put("AES128_GCM", zzgma.zza);
            zzgjw zzgjw = new zzgjw((zzgjv) null);
            zzgjw.zza(12);
            zzgjw.zzb(16);
            zzgjw.zzc(16);
            zzgjw.zzd(zzgjx.zzc);
            hashMap.put("AES128_GCM_RAW", zzgjw.zze());
            hashMap.put("AES256_GCM", zzgma.zzb);
            zzgjw zzgjw2 = new zzgjw((zzgjv) null);
            zzgjw2.zza(12);
            zzgjw2.zzb(32);
            zzgjw2.zzc(16);
            zzgjw2.zzd(zzgjx.zzc);
            hashMap.put("AES256_GCM_RAW", zzgjw2.zze());
            zzb2.zzd(Collections.unmodifiableMap(hashMap));
            zzgpd.zza().zzb(zzd, zzgjz.class);
            zzgpb.zzb().zzc(zze, zzgjz.class);
            zzgoj.zzc().zzf(zzc, i, true);
            return;
        }
        throw new GeneralSecurityException("Can not use AES-GCM in FIPS-mode, as BoringCrypto module is not available.");
    }
}
