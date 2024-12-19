package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgmf {
    public static final /* synthetic */ int zza = 0;
    private static final zzgpy zzb = zzgpy.zzb(new zzgmc(), zzgmb.class, zzggy.class);
    private static final zzghj zzc = zzgos.zzd("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key", zzggy.class, zzgwg.SYMMETRIC, zzgxt.zzg());
    private static final zzgpc zzd = new zzgmd();
    private static final zzgpa zze = new zzgme();

    public static void zza(boolean z) throws GeneralSecurityException {
        if (zzgoc.zza(1)) {
            int i = zzgnz.zza;
            zzgnz.zze(zzgpl.zzc());
            zzgpi.zza().zze(zzb);
            zzgph zzb2 = zzgph.zzb();
            HashMap hashMap = new HashMap();
            hashMap.put("XCHACHA20_POLY1305", zzgmh.zzc(zzgmg.zza));
            hashMap.put("XCHACHA20_POLY1305_RAW", zzgmh.zzc(zzgmg.zzc));
            zzb2.zzd(Collections.unmodifiableMap(hashMap));
            zzgpb.zzb().zzc(zze, zzgmh.class);
            zzgpd.zza().zzb(zzd, zzgmh.class);
            zzgoj.zzc().zzd(zzc, true);
            return;
        }
        throw new GeneralSecurityException("Registering XChaCha20Poly1305 is not supported in FIPS mode");
    }
}
