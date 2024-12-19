package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgtg {
    public static final /* synthetic */ int zza = 0;
    private static final zzgze zzb;
    private static final zzgoh zzc;
    private static final zzgoh zzd;
    private static final zzgpu zze = zzgpu.zzb(new zzgtc(), zzgsg.class, zzgqm.class);
    private static final zzgpq zzf;
    private static final zzgor zzg = zzgor.zzb(new zzgte(), zzgrv.class, zzgql.class);
    private static final zzgon zzh;

    static {
        zzgze zzb2 = zzgra.zzb("type.googleapis.com/google.crypto.tink.HmacKey");
        zzb = zzb2;
        zzgof zza2 = zzgoh.zza();
        zza2.zza(zzgxn.RAW, zzgse.zzd);
        zza2.zza(zzgxn.TINK, zzgse.zza);
        zza2.zza(zzgxn.LEGACY, zzgse.zzc);
        zza2.zza(zzgxn.CRUNCHY, zzgse.zzb);
        zzc = zza2.zzb();
        zzgof zza3 = zzgoh.zza();
        zza3.zza(zzgvt.SHA1, zzgsd.zza);
        zza3.zza(zzgvt.SHA224, zzgsd.zzb);
        zza3.zza(zzgvt.SHA256, zzgsd.zzc);
        zza3.zza(zzgvt.SHA384, zzgsd.zzd);
        zza3.zza(zzgvt.SHA512, zzgsd.zze);
        zzd = zza3.zzb();
        zzf = zzgpq.zzb(new zzgtd(), zzb2, zzgqm.class);
        zzh = zzgon.zzb(new zzgtf(), zzb2, zzgql.class);
    }

    public static /* synthetic */ zzgql zza(zzgrv zzgrv, zzgic zzgic) {
        zzgvv zzc2 = zzgvw.zzc();
        zzc2.zzb(zzf(zzgrv.zzb()));
        byte[] zzd2 = zzgrv.zzd().zzd(zzgic);
        zzc2.zza(zzhac.zzv(zzd2, 0, zzd2.length));
        return zzgql.zza("type.googleapis.com/google.crypto.tink.HmacKey", ((zzgvw) zzc2.zzbr()).zzaN(), zzgwg.SYMMETRIC, (zzgxn) zzc.zzb(zzgrv.zzb().zzg()), zzgrv.zze());
    }

    public static /* synthetic */ zzgqm zzb(zzgsg zzgsg) {
        zzgwl zza2 = zzgwm.zza();
        zza2.zzb("type.googleapis.com/google.crypto.tink.HmacKey");
        zzgvy zzd2 = zzgvz.zzd();
        zzd2.zzb(zzf(zzgsg));
        zzd2.zza(zzgsg.zzc());
        zza2.zzc(((zzgvz) zzd2.zzbr()).zzaN());
        zza2.zza((zzgxn) zzc.zzb(zzgsg.zzg()));
        return zzgqm.zzb((zzgwm) zza2.zzbr());
    }

    public static /* synthetic */ zzgrv zzc(zzgql zzgql, zzgic zzgic) {
        if (zzgql.zzg().equals("type.googleapis.com/google.crypto.tink.HmacKey")) {
            try {
                zzgvw zzf2 = zzgvw.zzf(zzgql.zze(), zzhay.zza());
                if (zzf2.zza() == 0) {
                    zzgsc zze2 = zzgsg.zze();
                    zze2.zzb(zzf2.zzh().zzd());
                    zze2.zzc(zzf2.zzg().zza());
                    zze2.zza((zzgsd) zzd.zzc(zzf2.zzg().zzc()));
                    zze2.zzd((zzgse) zzc.zzc(zzgql.zzc()));
                    zzgsg zze3 = zze2.zze();
                    zzgrt zza2 = zzgrv.zza();
                    zza2.zzc(zze3);
                    zza2.zzb(zzgzf.zzb(zzf2.zzh().zzB(), zzgic));
                    zza2.zza(zzgql.zzf());
                    return zza2.zzd();
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (zzhcd | IllegalArgumentException unused) {
                throw new GeneralSecurityException("Parsing HmacKey failed");
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to HmacProtoSerialization.parseKey");
        }
    }

    public static /* synthetic */ zzgsg zzd(zzgqm zzgqm) {
        if (zzgqm.zzc().zzi().equals("type.googleapis.com/google.crypto.tink.HmacKey")) {
            try {
                zzgvz zzg2 = zzgvz.zzg(zzgqm.zzc().zzh(), zzhay.zza());
                if (zzg2.zzc() == 0) {
                    zzgsc zze2 = zzgsg.zze();
                    zze2.zzb(zzg2.zza());
                    zze2.zzc(zzg2.zzh().zza());
                    zze2.zza((zzgsd) zzd.zzc(zzg2.zzh().zzc()));
                    zze2.zzd((zzgse) zzc.zzc(zzgqm.zzc().zzg()));
                    return zze2.zze();
                }
                int zzc2 = zzg2.zzc();
                throw new GeneralSecurityException("Parsing HmacParameters failed: unknown Version " + zzc2);
            } catch (zzhcd e) {
                throw new GeneralSecurityException("Parsing HmacParameters failed: ", e);
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to HmacProtoSerialization.parseParameters: ".concat(String.valueOf(zzgqm.zzc().zzi())));
        }
    }

    public static void zze(zzgpl zzgpl) throws GeneralSecurityException {
        zzgpl.zzi(zze);
        zzgpl.zzh(zzf);
        zzgpl.zzg(zzg);
        zzgpl.zzf(zzh);
    }

    private static zzgwc zzf(zzgsg zzgsg) throws GeneralSecurityException {
        zzgwb zzd2 = zzgwc.zzd();
        zzd2.zzb(zzgsg.zzb());
        zzd2.zza((zzgvt) zzd.zzb(zzgsg.zzf()));
        return (zzgwc) zzd2.zzbr();
    }
}
