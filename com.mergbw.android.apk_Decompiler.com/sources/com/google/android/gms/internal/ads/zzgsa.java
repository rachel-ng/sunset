package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgsa {
    private static final zzgpy zza = zzgpy.zzb(new zzgrw(), zzgrv.class, zzgro.class);
    private static final zzgpy zzb = zzgpy.zzb(new zzgrx(), zzgrv.class, zzghw.class);
    private static final zzghj zzc = zzgos.zzd("type.googleapis.com/google.crypto.tink.HmacKey", zzghw.class, zzgwg.SYMMETRIC, zzgvw.zzi());
    private static final zzgpc zzd = new zzgry();
    private static final zzgpa zze = new zzgrz();
    private static final int zzf = 2;

    public static void zza(boolean z) throws GeneralSecurityException {
        int i = zzf;
        if (zzgoc.zza(i)) {
            int i2 = zzgtg.zza;
            zzgtg.zze(zzgpl.zzc());
            zzgpi.zza().zze(zza);
            zzgpi.zza().zze(zzb);
            zzgph zzb2 = zzgph.zzb();
            HashMap hashMap = new HashMap();
            hashMap.put("HMAC_SHA256_128BITTAG", zzgst.zza);
            zzgsc zzgsc = new zzgsc((zzgsb) null);
            zzgsc.zzb(32);
            zzgsc.zzc(16);
            zzgsc.zzd(zzgse.zzd);
            zzgsc.zza(zzgsd.zzc);
            hashMap.put("HMAC_SHA256_128BITTAG_RAW", zzgsc.zze());
            zzgsc zzgsc2 = new zzgsc((zzgsb) null);
            zzgsc2.zzb(32);
            zzgsc2.zzc(32);
            zzgsc2.zzd(zzgse.zza);
            zzgsc2.zza(zzgsd.zzc);
            hashMap.put("HMAC_SHA256_256BITTAG", zzgsc2.zze());
            zzgsc zzgsc3 = new zzgsc((zzgsb) null);
            zzgsc3.zzb(32);
            zzgsc3.zzc(32);
            zzgsc3.zzd(zzgse.zzd);
            zzgsc3.zza(zzgsd.zzc);
            hashMap.put("HMAC_SHA256_256BITTAG_RAW", zzgsc3.zze());
            zzgsc zzgsc4 = new zzgsc((zzgsb) null);
            zzgsc4.zzb(64);
            zzgsc4.zzc(16);
            zzgsc4.zzd(zzgse.zza);
            zzgsc4.zza(zzgsd.zze);
            hashMap.put("HMAC_SHA512_128BITTAG", zzgsc4.zze());
            zzgsc zzgsc5 = new zzgsc((zzgsb) null);
            zzgsc5.zzb(64);
            zzgsc5.zzc(16);
            zzgsc5.zzd(zzgse.zzd);
            zzgsc5.zza(zzgsd.zze);
            hashMap.put("HMAC_SHA512_128BITTAG_RAW", zzgsc5.zze());
            zzgsc zzgsc6 = new zzgsc((zzgsb) null);
            zzgsc6.zzb(64);
            zzgsc6.zzc(32);
            zzgsc6.zzd(zzgse.zza);
            zzgsc6.zza(zzgsd.zze);
            hashMap.put("HMAC_SHA512_256BITTAG", zzgsc6.zze());
            zzgsc zzgsc7 = new zzgsc((zzgsb) null);
            zzgsc7.zzb(64);
            zzgsc7.zzc(32);
            zzgsc7.zzd(zzgse.zzd);
            zzgsc7.zza(zzgsd.zze);
            hashMap.put("HMAC_SHA512_256BITTAG_RAW", zzgsc7.zze());
            hashMap.put("HMAC_SHA512_512BITTAG", zzgst.zzd);
            zzgsc zzgsc8 = new zzgsc((zzgsb) null);
            zzgsc8.zzb(64);
            zzgsc8.zzc(64);
            zzgsc8.zzd(zzgse.zzd);
            zzgsc8.zza(zzgsd.zze);
            hashMap.put("HMAC_SHA512_512BITTAG_RAW", zzgsc8.zze());
            zzb2.zzd(Collections.unmodifiableMap(hashMap));
            zzgpb.zzb().zzc(zze, zzgsg.class);
            zzgpd.zza().zzb(zzd, zzgsg.class);
            zzgoj.zzc().zzf(zzc, i, true);
            return;
        }
        throw new GeneralSecurityException("Can not use HMAC in FIPS-mode, as BoringCrypto module is not available.");
    }
}
