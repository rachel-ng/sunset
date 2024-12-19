package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgkv {
    public static final /* synthetic */ int zza = 0;
    private static final zzgpy zzb = zzgpy.zzb(new zzgkt(), zzgla.class, zzggy.class);
    private static final zzghj zzc = zzgos.zzd("type.googleapis.com/google.crypto.tink.KmsAeadKey", zzggy.class, zzgwg.REMOTE, zzgxc.zzg());
    private static final zzgpa zzd = new zzgku();

    public static void zza(boolean z) throws GeneralSecurityException {
        if (zzgoc.zza(1)) {
            int i = zzglh.zza;
            zzglh.zze(zzgpl.zzc());
            zzgpi.zza().zze(zzb);
            zzgpb.zzb().zzc(zzd, zzglc.class);
            zzgoj.zzc().zzd(zzc, true);
            return;
        }
        throw new GeneralSecurityException("Registering KMS AEAD is not supported in FIPS mode");
    }
}
