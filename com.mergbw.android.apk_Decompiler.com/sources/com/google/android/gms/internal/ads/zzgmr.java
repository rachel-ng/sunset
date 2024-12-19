package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgmr {
    public static final /* synthetic */ int zza = 0;
    private static final zzgze zzb;
    private static final zzgpu zzc = zzgpu.zzb(new zzgmn(), zzgjm.class, zzgqm.class);
    private static final zzgpq zzd;
    private static final zzgor zze = zzgor.zzb(new zzgmp(), zzgje.class, zzgql.class);
    private static final zzgon zzf;

    static {
        zzgze zzb2 = zzgra.zzb("type.googleapis.com/google.crypto.tink.AesEaxKey");
        zzb = zzb2;
        zzd = zzgpq.zzb(new zzgmo(), zzb2, zzgqm.class);
        zzf = zzgon.zzb(new zzgmq(), zzb2, zzgql.class);
    }

    public static /* synthetic */ zzgje zza(zzgql zzgql, zzgic zzgic) {
        if (zzgql.zzg().equals("type.googleapis.com/google.crypto.tink.AesEaxKey")) {
            try {
                zzgut zze2 = zzgut.zze(zzgql.zze(), zzhay.zza());
                if (zze2.zza() == 0) {
                    zzgjj zzd2 = zzgjm.zzd();
                    zzd2.zzb(zze2.zzg().zzd());
                    zzd2.zza(zze2.zzf().zza());
                    zzd2.zzc(16);
                    zzd2.zzd(zzf(zzgql.zzc()));
                    zzgjm zze3 = zzd2.zze();
                    zzgjc zza2 = zzgje.zza();
                    zza2.zzc(zze3);
                    zza2.zzb(zzgzf.zzb(zze2.zzg().zzB(), zzgic));
                    zza2.zza(zzgql.zzf());
                    return zza2.zzd();
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (zzhcd unused) {
                throw new GeneralSecurityException("Parsing AesEaxcKey failed");
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to AesEaxProtoSerialization.parseKey");
        }
    }

    public static /* synthetic */ zzgjm zzb(zzgqm zzgqm) {
        if (zzgqm.zzc().zzi().equals("type.googleapis.com/google.crypto.tink.AesEaxKey")) {
            try {
                zzguw zze2 = zzguw.zze(zzgqm.zzc().zzh(), zzhay.zza());
                zzgjj zzd2 = zzgjm.zzd();
                zzd2.zzb(zze2.zza());
                zzd2.zza(zze2.zzf().zza());
                zzd2.zzc(16);
                zzd2.zzd(zzf(zzgqm.zzc().zzg()));
                return zzd2.zze();
            } catch (zzhcd e) {
                throw new GeneralSecurityException("Parsing AesEaxParameters failed: ", e);
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to AesEaxProtoSerialization.parseParameters: ".concat(String.valueOf(zzgqm.zzc().zzi())));
        }
    }

    public static /* synthetic */ zzgql zzc(zzgje zzgje, zzgic zzgic) {
        zzgus zzc2 = zzgut.zzc();
        zzc2.zzb(zzg(zzgje.zzb()));
        byte[] zzd2 = zzgje.zzd().zzd(zzgic);
        zzc2.zza(zzhac.zzv(zzd2, 0, zzd2.length));
        return zzgql.zza("type.googleapis.com/google.crypto.tink.AesEaxKey", ((zzgut) zzc2.zzbr()).zzaN(), zzgwg.SYMMETRIC, zzh(zzgje.zzb().zze()), zzgje.zze());
    }

    public static /* synthetic */ zzgqm zzd(zzgjm zzgjm) {
        zzgwl zza2 = zzgwm.zza();
        zza2.zzb("type.googleapis.com/google.crypto.tink.AesEaxKey");
        zzguv zzc2 = zzguw.zzc();
        zzc2.zzb(zzg(zzgjm));
        zzc2.zza(zzgjm.zzc());
        zza2.zzc(((zzguw) zzc2.zzbr()).zzaN());
        zza2.zza(zzh(zzgjm.zze()));
        return zzgqm.zzb((zzgwm) zza2.zzbr());
    }

    public static void zze(zzgpl zzgpl) throws GeneralSecurityException {
        zzgpl.zzi(zzc);
        zzgpl.zzh(zzd);
        zzgpl.zzg(zze);
        zzgpl.zzf(zzf);
    }

    private static zzgjk zzf(zzgxn zzgxn) throws GeneralSecurityException {
        zzgxn zzgxn2 = zzgxn.UNKNOWN_PREFIX;
        int ordinal = zzgxn.ordinal();
        if (ordinal == 1) {
            return zzgjk.zza;
        }
        if (ordinal != 2) {
            if (ordinal == 3) {
                return zzgjk.zzc;
            }
            if (ordinal != 4) {
                int zza2 = zzgxn.zza();
                throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + zza2);
            }
        }
        return zzgjk.zzb;
    }

    private static zzguz zzg(zzgjm zzgjm) throws GeneralSecurityException {
        zzguy zzc2 = zzguz.zzc();
        zzc2.zza(zzgjm.zzb());
        return (zzguz) zzc2.zzbr();
    }

    private static zzgxn zzh(zzgjk zzgjk) throws GeneralSecurityException {
        if (zzgjk.zza.equals(zzgjk)) {
            return zzgxn.TINK;
        }
        if (zzgjk.zzb.equals(zzgjk)) {
            return zzgxn.CRUNCHY;
        }
        if (zzgjk.zzc.equals(zzgjk)) {
            return zzgxn.RAW;
        }
        throw new GeneralSecurityException("Unable to serialize variant: ".concat(String.valueOf(String.valueOf(zzgjk))));
    }
}
