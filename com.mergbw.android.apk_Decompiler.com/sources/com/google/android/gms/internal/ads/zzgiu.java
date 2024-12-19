package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgiu {
    public static final /* synthetic */ int zza = 0;
    private static final zzgpy zzb = zzgpy.zzb(new zzgir(), zzgiq.class, zzggy.class);
    private static final zzghj zzc = zzgos.zzd("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey", zzggy.class, zzgwg.SYMMETRIC, zzgue.zzh());
    private static final zzgpc zzd = new zzgis();
    private static final zzgpa zze = new zzgit();
    private static final int zzf = 2;

    public static void zza(boolean z) throws GeneralSecurityException {
        int i = zzf;
        if (zzgoc.zza(i)) {
            int i2 = zzgmm.zza;
            zzgmm.zze(zzgpl.zzc());
            zzgpi.zza().zze(zzb);
            zzgph zzb2 = zzgph.zzb();
            HashMap hashMap = new HashMap();
            hashMap.put("AES128_CTR_HMAC_SHA256", zzgma.zze);
            zzgiw zzgiw = new zzgiw((zzgiv) null);
            zzgiw.zza(16);
            zzgiw.zzc(32);
            zzgiw.zze(16);
            zzgiw.zzd(16);
            zzgiw.zzb(zzgix.zzc);
            zzgiw.zzf(zzgiy.zzc);
            hashMap.put("AES128_CTR_HMAC_SHA256_RAW", zzgiw.zzg());
            hashMap.put("AES256_CTR_HMAC_SHA256", zzgma.zzf);
            zzgiw zzgiw2 = new zzgiw((zzgiv) null);
            zzgiw2.zza(32);
            zzgiw2.zzc(32);
            zzgiw2.zze(32);
            zzgiw2.zzd(16);
            zzgiw2.zzb(zzgix.zzc);
            zzgiw2.zzf(zzgiy.zzc);
            hashMap.put("AES256_CTR_HMAC_SHA256_RAW", zzgiw2.zzg());
            zzb2.zzd(Collections.unmodifiableMap(hashMap));
            zzgpd.zza().zzb(zzd, zzgja.class);
            zzgpb.zzb().zzc(zze, zzgja.class);
            zzgoj.zzc().zzf(zzc, i, true);
            return;
        }
        throw new GeneralSecurityException("Can not use AES-CTR-HMAC in FIPS-mode, as BoringCrypto module is not available.");
    }
}
