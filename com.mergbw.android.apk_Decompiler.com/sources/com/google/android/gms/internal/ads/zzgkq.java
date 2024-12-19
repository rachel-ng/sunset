package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgkq {
    public static final /* synthetic */ int zza = 0;
    private static final zzgpy zzb = zzgpy.zzb(new zzgko(), zzgkn.class, zzggy.class);
    private static final zzgpa zzc = new zzgkp();
    private static final zzghj zzd = zzgos.zzd("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key", zzggy.class, zzgwg.SYMMETRIC, zzgvo.zzg());

    public static void zza(boolean z) throws GeneralSecurityException {
        if (zzgoc.zza(1)) {
            int i = zzgni.zza;
            zzgni.zze(zzgpl.zzc());
            zzgpi.zza().zze(zzb);
            zzgpb.zzb().zzc(zzc, zzgks.class);
            zzgph zzb2 = zzgph.zzb();
            HashMap hashMap = new HashMap();
            hashMap.put("CHACHA20_POLY1305", zzgks.zzc(zzgkr.zza));
            hashMap.put("CHACHA20_POLY1305_RAW", zzgks.zzc(zzgkr.zzc));
            zzb2.zzd(Collections.unmodifiableMap(hashMap));
            zzgoj.zzc().zzd(zzd, true);
            return;
        }
        throw new GeneralSecurityException("Registering ChaCha20Poly1305 is not supported in FIPS mode");
    }
}
