package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgnz {
    public static final /* synthetic */ int zza = 0;
    private static final zzgze zzb;
    private static final zzgpu zzc = zzgpu.zzb(new zzgnv(), zzgmh.class, zzgqm.class);
    private static final zzgpq zzd;
    private static final zzgor zze = zzgor.zzb(new zzgnx(), zzgmb.class, zzgql.class);
    private static final zzgon zzf;

    static {
        zzgze zzb2 = zzgra.zzb("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key");
        zzb = zzb2;
        zzd = zzgpq.zzb(new zzgnw(), zzb2, zzgqm.class);
        zzf = zzgon.zzb(new zzgny(), zzb2, zzgql.class);
    }

    public static /* synthetic */ zzgmb zza(zzgql zzgql, zzgic zzgic) {
        if (zzgql.zzg().equals("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key")) {
            try {
                zzgxt zze2 = zzgxt.zze(zzgql.zze(), zzhay.zza());
                if (zze2.zza() == 0) {
                    return zzgmb.zza(zzf(zzgql.zzc()), zzgzf.zzb(zze2.zzf().zzB(), zzgic), zzgql.zzf());
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (zzhcd unused) {
                throw new GeneralSecurityException("Parsing XChaCha20Poly1305Key failed");
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to XChaCha20Poly1305ProtoSerialization.parseKey");
        }
    }

    public static /* synthetic */ zzgmh zzb(zzgqm zzgqm) {
        if (zzgqm.zzc().zzi().equals("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key")) {
            try {
                if (zzgxw.zze(zzgqm.zzc().zzh(), zzhay.zza()).zza() == 0) {
                    return zzgmh.zzc(zzf(zzgqm.zzc().zzg()));
                }
                throw new GeneralSecurityException("Only version 0 parameters are accepted");
            } catch (zzhcd e) {
                throw new GeneralSecurityException("Parsing XChaCha20Poly1305Parameters failed: ", e);
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to XChaCha20Poly1305ProtoSerialization.parseParameters: ".concat(String.valueOf(zzgqm.zzc().zzi())));
        }
    }

    public static /* synthetic */ zzgql zzc(zzgmb zzgmb, zzgic zzgic) {
        zzgxs zzc2 = zzgxt.zzc();
        byte[] zzd2 = zzgmb.zzd().zzd(zzgic);
        zzc2.zza(zzhac.zzv(zzd2, 0, zzd2.length));
        return zzgql.zza("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key", ((zzgxt) zzc2.zzbr()).zzaN(), zzgwg.SYMMETRIC, zzg(zzgmb.zzb().zzb()), zzgmb.zze());
    }

    public static /* synthetic */ zzgqm zzd(zzgmh zzgmh) {
        zzgwl zza2 = zzgwm.zza();
        zza2.zzb("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key");
        zza2.zzc(zzgxw.zzd().zzaN());
        zza2.zza(zzg(zzgmh.zzb()));
        return zzgqm.zzb((zzgwm) zza2.zzbr());
    }

    public static void zze(zzgpl zzgpl) throws GeneralSecurityException {
        zzgpl.zzi(zzc);
        zzgpl.zzh(zzd);
        zzgpl.zzg(zze);
        zzgpl.zzf(zzf);
    }

    private static zzgmg zzf(zzgxn zzgxn) throws GeneralSecurityException {
        zzgxn zzgxn2 = zzgxn.UNKNOWN_PREFIX;
        int ordinal = zzgxn.ordinal();
        if (ordinal == 1) {
            return zzgmg.zza;
        }
        if (ordinal != 2) {
            if (ordinal == 3) {
                return zzgmg.zzc;
            }
            if (ordinal != 4) {
                int zza2 = zzgxn.zza();
                throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + zza2);
            }
        }
        return zzgmg.zzb;
    }

    private static zzgxn zzg(zzgmg zzgmg) throws GeneralSecurityException {
        if (zzgmg.zza.equals(zzgmg)) {
            return zzgxn.TINK;
        }
        if (zzgmg.zzb.equals(zzgmg)) {
            return zzgxn.CRUNCHY;
        }
        if (zzgmg.zzc.equals(zzgmg)) {
            return zzgxn.RAW;
        }
        throw new GeneralSecurityException("Unable to serialize variant: ".concat(zzgmg.toString()));
    }
}
