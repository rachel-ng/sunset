package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgmw {
    public static final /* synthetic */ int zza = 0;
    private static final zzgze zzb;
    private static final zzgpu zzc = zzgpu.zzb(new zzgms(), zzgjz.class, zzgqm.class);
    private static final zzgpq zzd;
    private static final zzgor zze = zzgor.zzb(new zzgmu(), zzgjq.class, zzgql.class);
    private static final zzgon zzf;

    static {
        zzgze zzb2 = zzgra.zzb("type.googleapis.com/google.crypto.tink.AesGcmKey");
        zzb = zzb2;
        zzd = zzgpq.zzb(new zzgmt(), zzb2, zzgqm.class);
        zzf = zzgon.zzb(new zzgmv(), zzb2, zzgql.class);
    }

    public static /* synthetic */ zzgjq zza(zzgql zzgql, zzgic zzgic) {
        if (zzgql.zzg().equals("type.googleapis.com/google.crypto.tink.AesGcmKey")) {
            try {
                zzgvc zze2 = zzgvc.zze(zzgql.zze(), zzhay.zza());
                if (zze2.zza() == 0) {
                    zzgjw zzc2 = zzgjz.zzc();
                    zzc2.zzb(zze2.zzf().zzd());
                    zzc2.zza(12);
                    zzc2.zzc(16);
                    zzc2.zzd(zzf(zzgql.zzc()));
                    zzgjz zze3 = zzc2.zze();
                    zzgjo zza2 = zzgjq.zza();
                    zza2.zzc(zze3);
                    zza2.zzb(zzgzf.zzb(zze2.zzf().zzB(), zzgic));
                    zza2.zza(zzgql.zzf());
                    return zza2.zzd();
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (zzhcd unused) {
                throw new GeneralSecurityException("Parsing AesGcmKey failed");
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to AesGcmProtoSerialization.parseKey");
        }
    }

    public static /* synthetic */ zzgjz zzb(zzgqm zzgqm) {
        if (zzgqm.zzc().zzi().equals("type.googleapis.com/google.crypto.tink.AesGcmKey")) {
            try {
                zzgvf zzf2 = zzgvf.zzf(zzgqm.zzc().zzh(), zzhay.zza());
                if (zzf2.zzc() == 0) {
                    zzgjw zzc2 = zzgjz.zzc();
                    zzc2.zzb(zzf2.zza());
                    zzc2.zza(12);
                    zzc2.zzc(16);
                    zzc2.zzd(zzf(zzgqm.zzc().zzg()));
                    return zzc2.zze();
                }
                throw new GeneralSecurityException("Only version 0 parameters are accepted");
            } catch (zzhcd e) {
                throw new GeneralSecurityException("Parsing AesGcmParameters failed: ", e);
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to AesGcmProtoSerialization.parseParameters: ".concat(String.valueOf(zzgqm.zzc().zzi())));
        }
    }

    public static /* synthetic */ zzgql zzc(zzgjq zzgjq, zzgic zzgic) {
        zzgvb zzc2 = zzgvc.zzc();
        byte[] zzd2 = zzgjq.zzd().zzd(zzgic);
        zzc2.zza(zzhac.zzv(zzd2, 0, zzd2.length));
        return zzgql.zza("type.googleapis.com/google.crypto.tink.AesGcmKey", ((zzgvc) zzc2.zzbr()).zzaN(), zzgwg.SYMMETRIC, zzg(zzgjq.zzb().zzd()), zzgjq.zze());
    }

    public static /* synthetic */ zzgqm zzd(zzgjz zzgjz) {
        zzgwl zza2 = zzgwm.zza();
        zza2.zzb("type.googleapis.com/google.crypto.tink.AesGcmKey");
        zzgve zzd2 = zzgvf.zzd();
        zzd2.zza(zzgjz.zzb());
        zza2.zzc(((zzgvf) zzd2.zzbr()).zzaN());
        zza2.zza(zzg(zzgjz.zzd()));
        return zzgqm.zzb((zzgwm) zza2.zzbr());
    }

    public static void zze(zzgpl zzgpl) throws GeneralSecurityException {
        zzgpl.zzi(zzc);
        zzgpl.zzh(zzd);
        zzgpl.zzg(zze);
        zzgpl.zzf(zzf);
    }

    private static zzgjx zzf(zzgxn zzgxn) throws GeneralSecurityException {
        zzgxn zzgxn2 = zzgxn.UNKNOWN_PREFIX;
        int ordinal = zzgxn.ordinal();
        if (ordinal == 1) {
            return zzgjx.zza;
        }
        if (ordinal != 2) {
            if (ordinal == 3) {
                return zzgjx.zzc;
            }
            if (ordinal != 4) {
                int zza2 = zzgxn.zza();
                throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + zza2);
            }
        }
        return zzgjx.zzb;
    }

    private static zzgxn zzg(zzgjx zzgjx) throws GeneralSecurityException {
        if (zzgjx.zza.equals(zzgjx)) {
            return zzgxn.TINK;
        }
        if (zzgjx.zzb.equals(zzgjx)) {
            return zzgxn.CRUNCHY;
        }
        if (zzgjx.zzc.equals(zzgjx)) {
            return zzgxn.RAW;
        }
        throw new GeneralSecurityException("Unable to serialize variant: ".concat(String.valueOf(String.valueOf(zzgjx))));
    }
}
