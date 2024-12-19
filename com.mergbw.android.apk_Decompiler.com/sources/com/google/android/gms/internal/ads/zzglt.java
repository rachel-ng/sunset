package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzglt {
    public static final /* synthetic */ int zza = 0;
    private static final zzgze zzb;
    private static final zzgpu zzc = zzgpu.zzb(new zzglp(), zzglo.class, zzgqm.class);
    private static final zzgpq zzd;
    private static final zzgor zze = zzgor.zzb(new zzglr(), zzgli.class, zzgql.class);
    private static final zzgon zzf;

    static {
        zzgze zzb2 = zzgra.zzb("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey");
        zzb = zzb2;
        zzd = zzgpq.zzb(new zzglq(), zzb2, zzgqm.class);
        zzf = zzgon.zzb(new zzgls(), zzb2, zzgql.class);
    }

    public static /* synthetic */ zzgli zza(zzgql zzgql, zzgic zzgic) {
        if (zzgql.zzg().equals("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey")) {
            try {
                zzgxi zze2 = zzgxi.zze(zzgql.zze(), zzhay.zza());
                if (zze2.zza() == 0) {
                    return zzgli.zza(zzf(zze2.zzf(), zzgql.zzc()), zzgql.zzf());
                }
                String valueOf = String.valueOf(zze2);
                throw new GeneralSecurityException("KmsEnvelopeAeadKeys are only accepted with version 0, got " + valueOf);
            } catch (zzhcd e) {
                throw new GeneralSecurityException("Parsing KmsEnvelopeAeadKey failed: ", e);
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to LegacyKmsEnvelopeAeadProtoSerialization.parseKey");
        }
    }

    public static /* synthetic */ zzglo zzb(zzgqm zzgqm) {
        if (zzgqm.zzc().zzi().equals("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey")) {
            try {
                return zzf(zzgxl.zzf(zzgqm.zzc().zzh(), zzhay.zza()), zzgqm.zzc().zzg());
            } catch (zzhcd e) {
                throw new GeneralSecurityException("Parsing KmsEnvelopeAeadKeyFormat failed: ", e);
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to LegacyKmsEnvelopeAeadProtoSerialization.parseParameters: ".concat(String.valueOf(zzgqm.zzc().zzi())));
        }
    }

    public static /* synthetic */ zzgql zzc(zzgli zzgli, zzgic zzgic) {
        zzgxh zzc2 = zzgxi.zzc();
        zzc2.zza(zzg(zzgli.zzb()));
        return zzgql.zza("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey", ((zzgxi) zzc2.zzbr()).zzaN(), zzgwg.REMOTE, zzh(zzgli.zzb().zzc()), zzgli.zzd());
    }

    public static /* synthetic */ zzgqm zzd(zzglo zzglo) {
        zzgwl zza2 = zzgwm.zza();
        zza2.zzb("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey");
        zza2.zzc(zzg(zzglo).zzaN());
        zza2.zza(zzh(zzglo.zzc()));
        return zzgqm.zzb((zzgwm) zza2.zzbr());
    }

    public static void zze(zzgpl zzgpl) throws GeneralSecurityException {
        zzgpl.zzi(zzc);
        zzgpl.zzh(zzd);
        zzgpl.zzg(zze);
        zzgpl.zzf(zzf);
    }

    private static zzglo zzf(zzgxl zzgxl, zzgxn zzgxn) throws GeneralSecurityException {
        zzgll zzgll;
        zzglm zzglm;
        zzgwl zza2 = zzgwm.zza();
        zza2.zzb(zzgxl.zza().zzi());
        zza2.zzc(zzgxl.zza().zzh());
        zza2.zza(zzgxn.RAW);
        zzghx zza3 = zzgie.zza(((zzgwm) zza2.zzbr()).zzaV());
        if (zza3 instanceof zzgjz) {
            zzgll = zzgll.zza;
        } else if (zza3 instanceof zzgks) {
            zzgll = zzgll.zzc;
        } else if (zza3 instanceof zzgmh) {
            zzgll = zzgll.zzb;
        } else if (zza3 instanceof zzgja) {
            zzgll = zzgll.zzd;
        } else if (zza3 instanceof zzgjm) {
            zzgll = zzgll.zze;
        } else if (zza3 instanceof zzgkm) {
            zzgll = zzgll.zzf;
        } else {
            throw new GeneralSecurityException("Unsupported DEK parameters when parsing ".concat(zza3.toString()));
        }
        zzglk zzglk = new zzglk((zzglj) null);
        int ordinal = zzgxn.ordinal();
        if (ordinal == 1) {
            zzglm = zzglm.zza;
        } else if (ordinal == 3) {
            zzglm = zzglm.zzb;
        } else {
            int zza4 = zzgxn.zza();
            throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + zza4);
        }
        zzglk.zzd(zzglm);
        zzglk.zzc(zzgxl.zzg());
        zzglk.zza((zzgii) zza3);
        zzglk.zzb(zzgll);
        return zzglk.zze();
    }

    private static zzgxl zzg(zzglo zzglo) throws GeneralSecurityException {
        try {
            zzgwm zzf2 = zzgwm.zzf(zzgie.zzb(zzglo.zzb()), zzhay.zza());
            zzgxk zzc2 = zzgxl.zzc();
            zzc2.zzb(zzglo.zzd());
            zzc2.zza(zzf2);
            return (zzgxl) zzc2.zzbr();
        } catch (zzhcd e) {
            throw new GeneralSecurityException("Parsing KmsEnvelopeAeadKeyFormat failed: ", e);
        }
    }

    private static zzgxn zzh(zzglm zzglm) throws GeneralSecurityException {
        if (zzglm.zza.equals(zzglm)) {
            return zzgxn.TINK;
        }
        if (zzglm.zzb.equals(zzglm)) {
            return zzgxn.RAW;
        }
        throw new GeneralSecurityException("Unable to serialize variant: ".concat(String.valueOf(String.valueOf(zzglm))));
    }
}
