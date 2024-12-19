package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgnb {
    public static final /* synthetic */ int zza = 0;
    private static final zzgze zzb;
    private static final zzgpu zzc = zzgpu.zzb(new zzgmx(), zzgkm.class, zzgqm.class);
    private static final zzgpq zzd;
    private static final zzgor zze = zzgor.zzb(new zzgmz(), zzgkd.class, zzgql.class);
    private static final zzgon zzf;

    static {
        zzgze zzb2 = zzgra.zzb("type.googleapis.com/google.crypto.tink.AesGcmSivKey");
        zzb = zzb2;
        zzd = zzgpq.zzb(new zzgmy(), zzb2, zzgqm.class);
        zzf = zzgon.zzb(new zzgna(), zzb2, zzgql.class);
    }

    public static /* synthetic */ zzgkd zza(zzgql zzgql, zzgic zzgic) {
        if (zzgql.zzg().equals("type.googleapis.com/google.crypto.tink.AesGcmSivKey")) {
            try {
                zzgvi zze2 = zzgvi.zze(zzgql.zze(), zzhay.zza());
                if (zze2.zza() == 0) {
                    zzgkj zzc2 = zzgkm.zzc();
                    zzc2.zza(zze2.zzf().zzd());
                    zzc2.zzb(zzf(zzgql.zzc()));
                    zzgkm zzc3 = zzc2.zzc();
                    zzgkb zza2 = zzgkd.zza();
                    zza2.zzc(zzc3);
                    zza2.zzb(zzgzf.zzb(zze2.zzf().zzB(), zzgic));
                    zza2.zza(zzgql.zzf());
                    return zza2.zzd();
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (zzhcd unused) {
                throw new GeneralSecurityException("Parsing AesGcmSivKey failed");
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to AesGcmSivProtoSerialization.parseKey");
        }
    }

    public static /* synthetic */ zzgkm zzb(zzgqm zzgqm) {
        if (zzgqm.zzc().zzi().equals("type.googleapis.com/google.crypto.tink.AesGcmSivKey")) {
            try {
                zzgvl zzf2 = zzgvl.zzf(zzgqm.zzc().zzh(), zzhay.zza());
                if (zzf2.zzc() == 0) {
                    zzgkj zzc2 = zzgkm.zzc();
                    zzc2.zza(zzf2.zza());
                    zzc2.zzb(zzf(zzgqm.zzc().zzg()));
                    return zzc2.zzc();
                }
                throw new GeneralSecurityException("Only version 0 parameters are accepted");
            } catch (zzhcd e) {
                throw new GeneralSecurityException("Parsing AesGcmSivParameters failed: ", e);
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to AesGcmSivProtoSerialization.parseParameters: ".concat(String.valueOf(zzgqm.zzc().zzi())));
        }
    }

    public static /* synthetic */ zzgql zzc(zzgkd zzgkd, zzgic zzgic) {
        zzgvh zzc2 = zzgvi.zzc();
        byte[] zzd2 = zzgkd.zzd().zzd(zzgic);
        zzc2.zza(zzhac.zzv(zzd2, 0, zzd2.length));
        return zzgql.zza("type.googleapis.com/google.crypto.tink.AesGcmSivKey", ((zzgvi) zzc2.zzbr()).zzaN(), zzgwg.SYMMETRIC, zzg(zzgkd.zzb().zzd()), zzgkd.zze());
    }

    public static /* synthetic */ zzgqm zzd(zzgkm zzgkm) {
        zzgwl zza2 = zzgwm.zza();
        zza2.zzb("type.googleapis.com/google.crypto.tink.AesGcmSivKey");
        zzgvk zzd2 = zzgvl.zzd();
        zzd2.zza(zzgkm.zzb());
        zza2.zzc(((zzgvl) zzd2.zzbr()).zzaN());
        zza2.zza(zzg(zzgkm.zzd()));
        return zzgqm.zzb((zzgwm) zza2.zzbr());
    }

    public static void zze(zzgpl zzgpl) throws GeneralSecurityException {
        zzgpl.zzi(zzc);
        zzgpl.zzh(zzd);
        zzgpl.zzg(zze);
        zzgpl.zzf(zzf);
    }

    private static zzgkk zzf(zzgxn zzgxn) throws GeneralSecurityException {
        zzgxn zzgxn2 = zzgxn.UNKNOWN_PREFIX;
        int ordinal = zzgxn.ordinal();
        if (ordinal == 1) {
            return zzgkk.zza;
        }
        if (ordinal != 2) {
            if (ordinal == 3) {
                return zzgkk.zzc;
            }
            if (ordinal != 4) {
                int zza2 = zzgxn.zza();
                throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + zza2);
            }
        }
        return zzgkk.zzb;
    }

    private static zzgxn zzg(zzgkk zzgkk) throws GeneralSecurityException {
        if (zzgkk.zza.equals(zzgkk)) {
            return zzgxn.TINK;
        }
        if (zzgkk.zzb.equals(zzgkk)) {
            return zzgxn.CRUNCHY;
        }
        if (zzgkk.zzc.equals(zzgkk)) {
            return zzgxn.RAW;
        }
        throw new GeneralSecurityException("Unable to serialize variant: ".concat(String.valueOf(String.valueOf(zzgkk))));
    }
}
