package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgmm {
    public static final /* synthetic */ int zza = 0;
    private static final zzgze zzb;
    private static final zzgpu zzc = zzgpu.zzb(new zzgmi(), zzgja.class, zzgqm.class);
    private static final zzgpq zzd;
    private static final zzgor zze = zzgor.zzb(new zzgmk(), zzgiq.class, zzgql.class);
    private static final zzgon zzf;

    static {
        zzgze zzb2 = zzgra.zzb("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey");
        zzb = zzb2;
        zzd = zzgpq.zzb(new zzgmj(), zzb2, zzgqm.class);
        zzf = zzgon.zzb(new zzgml(), zzb2, zzgql.class);
    }

    public static /* synthetic */ zzgiq zza(zzgql zzgql, zzgic zzgic) {
        if (zzgql.zzg().equals("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey")) {
            try {
                zzgue zze2 = zzgue.zze(zzgql.zze(), zzhay.zza());
                if (zze2.zza() != 0) {
                    throw new GeneralSecurityException("Only version 0 keys are accepted");
                } else if (zze2.zzf().zza() != 0) {
                    throw new GeneralSecurityException("Only version 0 keys inner AES CTR keys are accepted");
                } else if (zze2.zzg().zza() == 0) {
                    zzgiw zzf2 = zzgja.zzf();
                    zzf2.zza(zze2.zzf().zzg().zzd());
                    zzf2.zzc(zze2.zzg().zzh().zzd());
                    zzf2.zzd(zze2.zzf().zzf().zza());
                    zzf2.zze(zze2.zzg().zzg().zza());
                    zzf2.zzb(zzf(zze2.zzg().zzg().zzc()));
                    zzf2.zzf(zzg(zzgql.zzc()));
                    zzgja zzg = zzf2.zzg();
                    zzgio zza2 = zzgiq.zza();
                    zza2.zzd(zzg);
                    zza2.zza(zzgzf.zzb(zze2.zzf().zzg().zzB(), zzgic));
                    zza2.zzb(zzgzf.zzb(zze2.zzg().zzh().zzB(), zzgic));
                    zza2.zzc(zzgql.zzf());
                    return zza2.zze();
                } else {
                    throw new GeneralSecurityException("Only version 0 keys inner HMAC keys are accepted");
                }
            } catch (zzhcd unused) {
                throw new GeneralSecurityException("Parsing AesCtrHmacAeadKey failed");
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to AesCtrHmacAeadProtoSerialization.parseKey");
        }
    }

    public static /* synthetic */ zzgja zzb(zzgqm zzgqm) {
        if (zzgqm.zzc().zzi().equals("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey")) {
            try {
                zzguh zzd2 = zzguh.zzd(zzgqm.zzc().zzh(), zzhay.zza());
                if (zzd2.zzf().zzc() == 0) {
                    zzgiw zzf2 = zzgja.zzf();
                    zzf2.zza(zzd2.zze().zza());
                    zzf2.zzc(zzd2.zzf().zza());
                    zzf2.zzd(zzd2.zze().zzf().zza());
                    zzf2.zze(zzd2.zzf().zzh().zza());
                    zzf2.zzb(zzf(zzd2.zzf().zzh().zzc()));
                    zzf2.zzf(zzg(zzgqm.zzc().zzg()));
                    return zzf2.zzg();
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (zzhcd e) {
                throw new GeneralSecurityException("Parsing AesCtrHmacAeadParameters failed: ", e);
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to AesCtrHmacAeadProtoSerialization.parseParameters: ".concat(String.valueOf(zzgqm.zzc().zzi())));
        }
    }

    public static /* synthetic */ zzgql zzc(zzgiq zzgiq, zzgic zzgic) {
        zzgud zzc2 = zzgue.zzc();
        zzguj zzc3 = zzguk.zzc();
        zzgup zzc4 = zzguq.zzc();
        zzc4.zza(zzgiq.zzb().zzd());
        zzc3.zzb((zzguq) zzc4.zzbr());
        byte[] zzd2 = zzgiq.zzd().zzd(zzgic);
        zzc3.zza(zzhac.zzv(zzd2, 0, zzd2.length));
        zzc2.zza((zzguk) zzc3.zzbr());
        zzgvv zzc5 = zzgvw.zzc();
        zzc5.zzb(zzh(zzgiq.zzb()));
        byte[] zzd3 = zzgiq.zze().zzd(zzgic);
        zzc5.zza(zzhac.zzv(zzd3, 0, zzd3.length));
        zzc2.zzb((zzgvw) zzc5.zzbr());
        return zzgql.zza("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey", ((zzgue) zzc2.zzbr()).zzaN(), zzgwg.SYMMETRIC, zzi(zzgiq.zzb().zzh()), zzgiq.zzf());
    }

    public static /* synthetic */ zzgqm zzd(zzgja zzgja) {
        zzgwl zza2 = zzgwm.zza();
        zza2.zzb("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey");
        zzgug zza3 = zzguh.zza();
        zzgum zzc2 = zzgun.zzc();
        zzgup zzc3 = zzguq.zzc();
        zzc3.zza(zzgja.zzd());
        zzc2.zzb((zzguq) zzc3.zzbr());
        zzc2.zza(zzgja.zzb());
        zza3.zza((zzgun) zzc2.zzbr());
        zzgvy zzd2 = zzgvz.zzd();
        zzd2.zzb(zzh(zzgja));
        zzd2.zza(zzgja.zzc());
        zza3.zzb((zzgvz) zzd2.zzbr());
        zza2.zzc(((zzguh) zza3.zzbr()).zzaN());
        zza2.zza(zzi(zzgja.zzh()));
        return zzgqm.zzb((zzgwm) zza2.zzbr());
    }

    public static void zze(zzgpl zzgpl) throws GeneralSecurityException {
        zzgpl.zzi(zzc);
        zzgpl.zzh(zzd);
        zzgpl.zzg(zze);
        zzgpl.zzf(zzf);
    }

    private static zzgix zzf(zzgvt zzgvt) throws GeneralSecurityException {
        zzgvt zzgvt2 = zzgvt.UNKNOWN_HASH;
        zzgxn zzgxn = zzgxn.UNKNOWN_PREFIX;
        int ordinal = zzgvt.ordinal();
        if (ordinal == 1) {
            return zzgix.zza;
        }
        if (ordinal == 2) {
            return zzgix.zzd;
        }
        if (ordinal == 3) {
            return zzgix.zzc;
        }
        if (ordinal == 4) {
            return zzgix.zze;
        }
        if (ordinal == 5) {
            return zzgix.zzb;
        }
        int zza2 = zzgvt.zza();
        throw new GeneralSecurityException("Unable to parse HashType: " + zza2);
    }

    private static zzgiy zzg(zzgxn zzgxn) throws GeneralSecurityException {
        zzgvt zzgvt = zzgvt.UNKNOWN_HASH;
        zzgxn zzgxn2 = zzgxn.UNKNOWN_PREFIX;
        int ordinal = zzgxn.ordinal();
        if (ordinal == 1) {
            return zzgiy.zza;
        }
        if (ordinal != 2) {
            if (ordinal == 3) {
                return zzgiy.zzc;
            }
            if (ordinal != 4) {
                int zza2 = zzgxn.zza();
                throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + zza2);
            }
        }
        return zzgiy.zzb;
    }

    private static zzgwc zzh(zzgja zzgja) throws GeneralSecurityException {
        zzgvt zzgvt;
        zzgwb zzd2 = zzgwc.zzd();
        zzd2.zzb(zzgja.zze());
        zzgix zzg = zzgja.zzg();
        if (zzgix.zza.equals(zzg)) {
            zzgvt = zzgvt.SHA1;
        } else if (zzgix.zzb.equals(zzg)) {
            zzgvt = zzgvt.SHA224;
        } else if (zzgix.zzc.equals(zzg)) {
            zzgvt = zzgvt.SHA256;
        } else if (zzgix.zzd.equals(zzg)) {
            zzgvt = zzgvt.SHA384;
        } else if (zzgix.zze.equals(zzg)) {
            zzgvt = zzgvt.SHA512;
        } else {
            throw new GeneralSecurityException("Unable to serialize HashType ".concat(String.valueOf(String.valueOf(zzg))));
        }
        zzd2.zza(zzgvt);
        return (zzgwc) zzd2.zzbr();
    }

    private static zzgxn zzi(zzgiy zzgiy) throws GeneralSecurityException {
        if (zzgiy.zza.equals(zzgiy)) {
            return zzgxn.TINK;
        }
        if (zzgiy.zzb.equals(zzgiy)) {
            return zzgxn.CRUNCHY;
        }
        if (zzgiy.zzc.equals(zzgiy)) {
            return zzgxn.RAW;
        }
        throw new GeneralSecurityException("Unable to serialize variant: ".concat(String.valueOf(String.valueOf(zzgiy))));
    }
}
