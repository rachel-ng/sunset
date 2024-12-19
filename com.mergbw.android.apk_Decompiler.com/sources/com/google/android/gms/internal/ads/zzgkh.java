package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashMap;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgkh {
    private static final zzgpy zza = zzgpy.zzb(new zzgke(), zzgkd.class, zzggy.class);
    private static final zzgpa zzb = new zzgkf();
    private static final zzgpc zzc = new zzgkg();
    private static final zzghj zzd = zzgos.zzd("type.googleapis.com/google.crypto.tink.AesGcmSivKey", zzggy.class, zzgwg.SYMMETRIC, zzgvi.zzg());

    public static void zza(boolean z) throws GeneralSecurityException {
        if (zzgoc.zza(1)) {
            int i = zzgnb.zza;
            zzgnb.zze(zzgpl.zzc());
            if (zzb()) {
                zzgpi.zza().zze(zza);
                zzgph zzb2 = zzgph.zzb();
                HashMap hashMap = new HashMap();
                zzgkj zzgkj = new zzgkj((zzgki) null);
                zzgkj.zza(16);
                zzgkj.zzb(zzgkk.zza);
                hashMap.put("AES128_GCM_SIV", zzgkj.zzc());
                zzgkj zzgkj2 = new zzgkj((zzgki) null);
                zzgkj2.zza(16);
                zzgkj2.zzb(zzgkk.zzc);
                hashMap.put("AES128_GCM_SIV_RAW", zzgkj2.zzc());
                zzgkj zzgkj3 = new zzgkj((zzgki) null);
                zzgkj3.zza(32);
                zzgkj3.zzb(zzgkk.zza);
                hashMap.put("AES256_GCM_SIV", zzgkj3.zzc());
                zzgkj zzgkj4 = new zzgkj((zzgki) null);
                zzgkj4.zza(32);
                zzgkj4.zzb(zzgkk.zzc);
                hashMap.put("AES256_GCM_SIV_RAW", zzgkj4.zzc());
                zzb2.zzd(Collections.unmodifiableMap(hashMap));
                zzgpd.zza().zzb(zzc, zzgkm.class);
                zzgpb.zzb().zzc(zzb, zzgkm.class);
                zzgoj.zzc().zzd(zzd, true);
                return;
            }
            return;
        }
        throw new GeneralSecurityException("Registering AES GCM SIV is not supported in FIPS mode");
    }

    private static boolean zzb() {
        try {
            Cipher.getInstance("AES/GCM-SIV/NoPadding");
            return true;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException unused) {
            return false;
        }
    }
}
