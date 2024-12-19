package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgjh {
    public static final /* synthetic */ int zza = 0;
    private static final zzgpy zzb = zzgpy.zzb(new zzgjf(), zzgje.class, zzggy.class);
    private static final zzghj zzc = zzgos.zzd("type.googleapis.com/google.crypto.tink.AesEaxKey", zzggy.class, zzgwg.SYMMETRIC, zzgut.zzh());
    private static final zzgpa zzd = new zzgjg();

    public static void zza(boolean z) throws GeneralSecurityException {
        if (zzgoc.zza(1)) {
            int i = zzgmr.zza;
            zzgmr.zze(zzgpl.zzc());
            zzgpi.zza().zze(zzb);
            zzgph zzb2 = zzgph.zzb();
            HashMap hashMap = new HashMap();
            hashMap.put("AES128_EAX", zzgma.zzc);
            zzgjj zzgjj = new zzgjj((zzgji) null);
            zzgjj.zza(16);
            zzgjj.zzb(16);
            zzgjj.zzc(16);
            zzgjj.zzd(zzgjk.zzc);
            hashMap.put("AES128_EAX_RAW", zzgjj.zze());
            hashMap.put("AES256_EAX", zzgma.zzd);
            zzgjj zzgjj2 = new zzgjj((zzgji) null);
            zzgjj2.zza(16);
            zzgjj2.zzb(32);
            zzgjj2.zzc(16);
            zzgjj2.zzd(zzgjk.zzc);
            hashMap.put("AES256_EAX_RAW", zzgjj2.zze());
            zzb2.zzd(Collections.unmodifiableMap(hashMap));
            zzgpb.zzb().zzc(zzd, zzgjm.class);
            zzgoj.zzc().zzd(zzc, true);
            return;
        }
        throw new GeneralSecurityException("Registering AES EAX is not supported in FIPS mode");
    }
}
