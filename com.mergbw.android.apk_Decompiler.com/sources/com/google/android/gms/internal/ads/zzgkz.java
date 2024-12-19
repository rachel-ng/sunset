package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgkz {
    public static final /* synthetic */ int zza = 0;
    private static final zzghj zzb = zzgos.zzd("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey", zzggy.class, zzgwg.SYMMETRIC, zzgxi.zzg());
    private static final zzgpa zzc = new zzgkx();
    private static final zzgpy zzd = zzgpy.zzb(new zzgky(), zzgli.class, zzggy.class);

    public static void zza(boolean z) throws GeneralSecurityException {
        if (zzgoc.zza(1)) {
            int i = zzglt.zza;
            zzglt.zze(zzgpl.zzc());
            zzgpb.zzb().zzc(zzc, zzglo.class);
            zzgpi.zza().zze(zzd);
            zzgoj.zzc().zzd(zzb, true);
            return;
        }
        throw new GeneralSecurityException("Registering KMS Envelope AEAD is not supported in FIPS mode");
    }
}
