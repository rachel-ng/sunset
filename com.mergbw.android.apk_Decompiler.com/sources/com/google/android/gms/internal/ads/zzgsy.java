package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgsy {
    public static final /* synthetic */ int zza = 0;
    private static final zzgze zzb;
    private static final zzgpu zzc = zzgpu.zzb(new zzgsu(), zzgrn.class, zzgqm.class);
    private static final zzgpq zzd;
    private static final zzgor zze = zzgor.zzb(new zzgsw(), zzgre.class, zzgql.class);
    private static final zzgon zzf;

    static {
        zzgze zzb2 = zzgra.zzb("type.googleapis.com/google.crypto.tink.AesCmacKey");
        zzb = zzb2;
        zzd = zzgpq.zzb(new zzgsv(), zzb2, zzgqm.class);
        zzf = zzgon.zzb(new zzgsx(), zzb2, zzgql.class);
    }

    public static /* synthetic */ zzgql zza(zzgre zzgre, zzgic zzgic) {
        zzgtu zzc2 = zzgtv.zzc();
        zzc2.zzb(zzg(zzgre.zzb()));
        byte[] zzd2 = zzgre.zzd().zzd(zzgic);
        zzc2.zza(zzhac.zzv(zzd2, 0, zzd2.length));
        return zzgql.zza("type.googleapis.com/google.crypto.tink.AesCmacKey", ((zzgtv) zzc2.zzbr()).zzaN(), zzgwg.SYMMETRIC, zzh(zzgre.zzb().zzf()), zzgre.zze());
    }

    public static /* synthetic */ zzgqm zzb(zzgrn zzgrn) {
        zzgwl zza2 = zzgwm.zza();
        zza2.zzb("type.googleapis.com/google.crypto.tink.AesCmacKey");
        zzgtx zzc2 = zzgty.zzc();
        zzc2.zzb(zzg(zzgrn));
        zzc2.zza(zzgrn.zzc());
        zza2.zzc(((zzgty) zzc2.zzbr()).zzaN());
        zza2.zza(zzh(zzgrn.zzf()));
        return zzgqm.zzb((zzgwm) zza2.zzbr());
    }

    public static /* synthetic */ zzgre zzc(zzgql zzgql, zzgic zzgic) {
        if (zzgql.zzg().equals("type.googleapis.com/google.crypto.tink.AesCmacKey")) {
            try {
                zzgtv zze2 = zzgtv.zze(zzgql.zze(), zzhay.zza());
                if (zze2.zza() == 0) {
                    zzgrk zze3 = zzgrn.zze();
                    zze3.zza(zze2.zzg().zzd());
                    zze3.zzb(zze2.zzf().zza());
                    zze3.zzc(zzf(zzgql.zzc()));
                    zzgrn zzd2 = zze3.zzd();
                    zzgrc zza2 = zzgre.zza();
                    zza2.zzc(zzd2);
                    zza2.zza(zzgzf.zzb(zze2.zzg().zzB(), zzgic));
                    zza2.zzb(zzgql.zzf());
                    return zza2.zzd();
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (zzhcd | IllegalArgumentException unused) {
                throw new GeneralSecurityException("Parsing AesCmacKey failed");
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to AesCmacProtoSerialization.parseKey");
        }
    }

    public static /* synthetic */ zzgrn zzd(zzgqm zzgqm) {
        if (zzgqm.zzc().zzi().equals("type.googleapis.com/google.crypto.tink.AesCmacKey")) {
            try {
                zzgty zze2 = zzgty.zze(zzgqm.zzc().zzh(), zzhay.zza());
                zzgrk zze3 = zzgrn.zze();
                zze3.zza(zze2.zza());
                zze3.zzb(zze2.zzf().zza());
                zze3.zzc(zzf(zzgqm.zzc().zzg()));
                return zze3.zzd();
            } catch (zzhcd e) {
                throw new GeneralSecurityException("Parsing AesCmacParameters failed: ", e);
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to AesCmacProtoSerialization.parseParameters: ".concat(String.valueOf(zzgqm.zzc().zzi())));
        }
    }

    public static void zze(zzgpl zzgpl) throws GeneralSecurityException {
        zzgpl.zzi(zzc);
        zzgpl.zzh(zzd);
        zzgpl.zzg(zze);
        zzgpl.zzf(zzf);
    }

    private static zzgrl zzf(zzgxn zzgxn) throws GeneralSecurityException {
        zzgxn zzgxn2 = zzgxn.UNKNOWN_PREFIX;
        int ordinal = zzgxn.ordinal();
        if (ordinal == 1) {
            return zzgrl.zza;
        }
        if (ordinal == 2) {
            return zzgrl.zzc;
        }
        if (ordinal == 3) {
            return zzgrl.zzd;
        }
        if (ordinal == 4) {
            return zzgrl.zzb;
        }
        int zza2 = zzgxn.zza();
        throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + zza2);
    }

    private static zzgub zzg(zzgrn zzgrn) {
        zzgua zzc2 = zzgub.zzc();
        zzc2.zza(zzgrn.zzb());
        return (zzgub) zzc2.zzbr();
    }

    private static zzgxn zzh(zzgrl zzgrl) throws GeneralSecurityException {
        if (zzgrl.zza.equals(zzgrl)) {
            return zzgxn.TINK;
        }
        if (zzgrl.zzb.equals(zzgrl)) {
            return zzgxn.CRUNCHY;
        }
        if (zzgrl.zzd.equals(zzgrl)) {
            return zzgxn.RAW;
        }
        if (zzgrl.zzc.equals(zzgrl)) {
            return zzgxn.LEGACY;
        }
        throw new GeneralSecurityException("Unable to serialize variant: ".concat(String.valueOf(String.valueOf(zzgrl))));
    }
}
