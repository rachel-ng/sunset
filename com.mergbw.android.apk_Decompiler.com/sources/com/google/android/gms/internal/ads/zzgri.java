package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgri {
    private static final zzgpa zza = new zzgrf();
    private static final zzgpy zzb = zzgpy.zzb(new zzgrg(), zzgre.class, zzgro.class);
    private static final zzgpy zzc = zzgpy.zzb(new zzgrh(), zzgre.class, zzghw.class);
    private static final zzghj zzd = zzgos.zzd("type.googleapis.com/google.crypto.tink.AesCmacKey", zzghw.class, zzgwg.SYMMETRIC, zzgtv.zzh());

    public static /* synthetic */ zzgre zzb(zzgrn zzgrn, Integer num) {
        zze(zzgrn);
        zzgrc zzgrc = new zzgrc((zzgrb) null);
        zzgrc.zzc(zzgrn);
        zzgrc.zza(zzgzf.zzc(zzgrn.zzc()));
        zzgrc.zzb(num);
        return zzgrc.zzd();
    }

    public static void zzd(boolean z) throws GeneralSecurityException {
        if (zzgoc.zza(1)) {
            int i = zzgsy.zza;
            zzgsy.zze(zzgpl.zzc());
            zzgpb.zzb().zzc(zza, zzgrn.class);
            zzgpi.zza().zze(zzb);
            zzgpi.zza().zze(zzc);
            zzgph zzb2 = zzgph.zzb();
            HashMap hashMap = new HashMap();
            hashMap.put("AES_CMAC", zzgst.zze);
            hashMap.put("AES256_CMAC", zzgst.zze);
            zzgrk zzgrk = new zzgrk((zzgrj) null);
            zzgrk.zza(32);
            zzgrk.zzb(16);
            zzgrk.zzc(zzgrl.zzd);
            hashMap.put("AES256_CMAC_RAW", zzgrk.zzd());
            zzb2.zzd(Collections.unmodifiableMap(hashMap));
            zzgoj.zzc().zzd(zzd, true);
            return;
        }
        throw new GeneralSecurityException("Registering AES CMAC is not supported in FIPS mode");
    }

    /* access modifiers changed from: private */
    public static void zze(zzgrn zzgrn) throws GeneralSecurityException {
        if (zzgrn.zzc() != 32) {
            throw new GeneralSecurityException("AesCmacKey size wrong, must be 32 bytes");
        }
    }
}
