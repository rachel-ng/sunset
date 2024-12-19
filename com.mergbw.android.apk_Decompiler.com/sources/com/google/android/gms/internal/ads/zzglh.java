package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzglh {
    public static final /* synthetic */ int zza = 0;
    private static final zzgze zzb;
    private static final zzgpu zzc = zzgpu.zzb(new zzgld(), zzglc.class, zzgqm.class);
    private static final zzgpq zzd;
    private static final zzgor zze = zzgor.zzb(new zzglf(), zzgla.class, zzgql.class);
    private static final zzgon zzf;

    static {
        zzgze zzb2 = zzgra.zzb("type.googleapis.com/google.crypto.tink.KmsAeadKey");
        zzb = zzb2;
        zzd = zzgpq.zzb(new zzgle(), zzb2, zzgqm.class);
        zzf = zzgon.zzb(new zzglg(), zzb2, zzgql.class);
    }

    public static /* synthetic */ zzgla zza(zzgql zzgql, zzgic zzgic) {
        if (zzgql.zzg().equals("type.googleapis.com/google.crypto.tink.KmsAeadKey")) {
            try {
                zzgxc zze2 = zzgxc.zze(zzgql.zze(), zzhay.zza());
                if (zze2.zza() == 0) {
                    return zzgla.zza(zzglc.zzc(zze2.zzf().zzf(), zzf(zzgql.zzc())), zzgql.zzf());
                }
                String valueOf = String.valueOf(zze2);
                throw new GeneralSecurityException("KmsAeadKey are only accepted with version 0, got " + valueOf);
            } catch (zzhcd e) {
                throw new GeneralSecurityException("Parsing KmsAeadKey failed: ", e);
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to LegacyKmsAeadProtoSerialization.parseKey");
        }
    }

    public static /* synthetic */ zzglc zzb(zzgqm zzgqm) {
        if (zzgqm.zzc().zzi().equals("type.googleapis.com/google.crypto.tink.KmsAeadKey")) {
            try {
                return zzglc.zzc(zzgxf.zze(zzgqm.zzc().zzh(), zzhay.zza()).zzf(), zzf(zzgqm.zzc().zzg()));
            } catch (zzhcd e) {
                throw new GeneralSecurityException("Parsing KmsAeadKeyFormat failed: ", e);
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to LegacyKmsAeadProtoSerialization.parseParameters: ".concat(String.valueOf(zzgqm.zzc().zzi())));
        }
    }

    public static /* synthetic */ zzgql zzc(zzgla zzgla, zzgic zzgic) {
        zzgxb zzc2 = zzgxc.zzc();
        zzgxe zza2 = zzgxf.zza();
        zza2.zza(zzgla.zzb().zzd());
        zzc2.zza((zzgxf) zza2.zzbr());
        return zzgql.zza("type.googleapis.com/google.crypto.tink.KmsAeadKey", ((zzgxc) zzc2.zzbr()).zzaN(), zzgwg.REMOTE, zzg(zzgla.zzb().zzb()), zzgla.zzd());
    }

    public static /* synthetic */ zzgqm zzd(zzglc zzglc) {
        zzgwl zza2 = zzgwm.zza();
        zza2.zzb("type.googleapis.com/google.crypto.tink.KmsAeadKey");
        zzgxe zza3 = zzgxf.zza();
        zza3.zza(zzglc.zzd());
        zza2.zzc(((zzgxf) zza3.zzbr()).zzaN());
        zza2.zza(zzg(zzglc.zzb()));
        return zzgqm.zzb((zzgwm) zza2.zzbr());
    }

    public static void zze(zzgpl zzgpl) throws GeneralSecurityException {
        zzgpl.zzi(zzc);
        zzgpl.zzh(zzd);
        zzgpl.zzg(zze);
        zzgpl.zzf(zzf);
    }

    private static zzglb zzf(zzgxn zzgxn) throws GeneralSecurityException {
        zzgxn zzgxn2 = zzgxn.UNKNOWN_PREFIX;
        int ordinal = zzgxn.ordinal();
        if (ordinal == 1) {
            return zzglb.zza;
        }
        if (ordinal == 3) {
            return zzglb.zzb;
        }
        int zza2 = zzgxn.zza();
        throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + zza2);
    }

    private static zzgxn zzg(zzglb zzglb) throws GeneralSecurityException {
        if (zzglb.zza.equals(zzglb)) {
            return zzgxn.TINK;
        }
        if (zzglb.zzb.equals(zzglb)) {
            return zzgxn.RAW;
        }
        throw new GeneralSecurityException("Unable to serialize variant: ".concat(zzglb.toString()));
    }
}
