package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgni {
    public static final /* synthetic */ int zza = 0;
    private static final zzgze zzb;
    private static final zzgpu zzc = zzgpu.zzb(new zzgne(), zzgks.class, zzgqm.class);
    private static final zzgpq zzd;
    private static final zzgor zze = zzgor.zzb(new zzgng(), zzgkn.class, zzgql.class);
    private static final zzgon zzf;

    static {
        zzgze zzb2 = zzgra.zzb("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key");
        zzb = zzb2;
        zzd = zzgpq.zzb(new zzgnf(), zzb2, zzgqm.class);
        zzf = zzgon.zzb(new zzgnh(), zzb2, zzgql.class);
    }

    public static /* synthetic */ zzgkn zza(zzgql zzgql, zzgic zzgic) {
        if (zzgql.zzg().equals("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key")) {
            try {
                zzgvo zze2 = zzgvo.zze(zzgql.zze(), zzhay.zza());
                if (zze2.zza() == 0) {
                    return zzgkn.zza(zzf(zzgql.zzc()), zzgzf.zzb(zze2.zzf().zzB(), zzgic), zzgql.zzf());
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (zzhcd unused) {
                throw new GeneralSecurityException("Parsing ChaCha20Poly1305Key failed");
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to ChaCha20Poly1305ProtoSerialization.parseKey");
        }
    }

    public static /* synthetic */ zzgks zzb(zzgqm zzgqm) {
        if (zzgqm.zzc().zzi().equals("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key")) {
            try {
                zzgvr.zzd(zzgqm.zzc().zzh(), zzhay.zza());
                return zzgks.zzc(zzf(zzgqm.zzc().zzg()));
            } catch (zzhcd e) {
                throw new GeneralSecurityException("Parsing ChaCha20Poly1305Parameters failed: ", e);
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to ChaCha20Poly1305ProtoSerialization.parseParameters: ".concat(String.valueOf(zzgqm.zzc().zzi())));
        }
    }

    public static /* synthetic */ zzgql zzc(zzgkn zzgkn, zzgic zzgic) {
        zzgvn zzc2 = zzgvo.zzc();
        byte[] zzd2 = zzgkn.zzd().zzd(zzgic);
        zzc2.zza(zzhac.zzv(zzd2, 0, zzd2.length));
        return zzgql.zza("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key", ((zzgvo) zzc2.zzbr()).zzaN(), zzgwg.SYMMETRIC, zzg(zzgkn.zzb().zzb()), zzgkn.zze());
    }

    public static /* synthetic */ zzgqm zzd(zzgks zzgks) {
        zzgwl zza2 = zzgwm.zza();
        zza2.zzb("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key");
        zza2.zzc(zzgvr.zzc().zzaN());
        zza2.zza(zzg(zzgks.zzb()));
        return zzgqm.zzb((zzgwm) zza2.zzbr());
    }

    public static void zze(zzgpl zzgpl) throws GeneralSecurityException {
        zzgpl.zzi(zzc);
        zzgpl.zzh(zzd);
        zzgpl.zzg(zze);
        zzgpl.zzf(zzf);
    }

    private static zzgkr zzf(zzgxn zzgxn) throws GeneralSecurityException {
        zzgxn zzgxn2 = zzgxn.UNKNOWN_PREFIX;
        int ordinal = zzgxn.ordinal();
        if (ordinal == 1) {
            return zzgkr.zza;
        }
        if (ordinal != 2) {
            if (ordinal == 3) {
                return zzgkr.zzc;
            }
            if (ordinal != 4) {
                int zza2 = zzgxn.zza();
                throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + zza2);
            }
        }
        return zzgkr.zzb;
    }

    private static zzgxn zzg(zzgkr zzgkr) throws GeneralSecurityException {
        if (zzgkr.zza.equals(zzgkr)) {
            return zzgxn.TINK;
        }
        if (zzgkr.zzb.equals(zzgkr)) {
            return zzgxn.CRUNCHY;
        }
        if (zzgkr.zzc.equals(zzgkr)) {
            return zzgxn.RAW;
        }
        throw new GeneralSecurityException("Unable to serialize variant: ".concat(zzgkr.toString()));
    }
}
