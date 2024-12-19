package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzguh extends zzhbo implements zzhdf {
    /* access modifiers changed from: private */
    public static final zzguh zza;
    private static volatile zzhdm zzb;
    private int zzc;
    private zzgun zzd;
    private zzgvz zze;

    static {
        zzguh zzguh = new zzguh();
        zza = zzguh;
        zzhbo.zzca(zzguh.class, zzguh);
    }

    private zzguh() {
    }

    public static zzgug zza() {
        return (zzgug) zza.zzaZ();
    }

    public static zzguh zzd(zzhac zzhac, zzhay zzhay) throws zzhcd {
        return (zzguh) zzhbo.zzbr(zza, zzhac, zzhay);
    }

    static /* synthetic */ void zzg(zzguh zzguh, zzgun zzgun) {
        zzgun.getClass();
        zzguh.zzd = zzgun;
        zzguh.zzc |= 1;
    }

    static /* synthetic */ void zzh(zzguh zzguh, zzgvz zzgvz) {
        zzgvz.getClass();
        zzguh.zze = zzgvz;
        zzguh.zzc |= 2;
    }

    /* access modifiers changed from: protected */
    public final Object zzde(zzhbn zzhbn, Object obj, Object obj2) {
        zzhbn zzhbn2 = zzhbn.GET_MEMOIZED_IS_INITIALIZED;
        switch (zzhbn.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return zzbR(zza, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001", new Object[]{"zzc", "zzd", "zze"});
            case 3:
                return new zzguh();
            case 4:
                return new zzgug((zzguf) null);
            case 5:
                return zza;
            case 6:
                zzhdm zzhdm = zzb;
                if (zzhdm == null) {
                    synchronized (zzguh.class) {
                        zzhdm = zzb;
                        if (zzhdm == null) {
                            zzhdm = new zzhbj(zza);
                            zzb = zzhdm;
                        }
                    }
                }
                return zzhdm;
            default:
                throw new UnsupportedOperationException();
        }
    }

    public final zzgun zze() {
        zzgun zzgun = this.zzd;
        return zzgun == null ? zzgun.zze() : zzgun;
    }

    public final zzgvz zzf() {
        zzgvz zzgvz = this.zze;
        return zzgvz == null ? zzgvz.zzf() : zzgvz;
    }
}
