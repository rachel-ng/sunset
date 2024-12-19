package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgvz extends zzhbo implements zzhdf {
    /* access modifiers changed from: private */
    public static final zzgvz zza;
    private static volatile zzhdm zzb;
    private int zzc;
    private zzgwc zzd;
    /* access modifiers changed from: private */
    public int zze;
    private int zzf;

    static {
        zzgvz zzgvz = new zzgvz();
        zza = zzgvz;
        zzhbo.zzca(zzgvz.class, zzgvz);
    }

    private zzgvz() {
    }

    public static zzgvy zzd() {
        return (zzgvy) zza.zzaZ();
    }

    public static zzgvz zzf() {
        return zza;
    }

    public static zzgvz zzg(zzhac zzhac, zzhay zzhay) throws zzhcd {
        return (zzgvz) zzhbo.zzbr(zza, zzhac, zzhay);
    }

    static /* synthetic */ void zzi(zzgvz zzgvz, zzgwc zzgwc) {
        zzgwc.getClass();
        zzgvz.zzd = zzgwc;
        zzgvz.zzc |= 1;
    }

    public final int zza() {
        return this.zze;
    }

    public final int zzc() {
        return this.zzf;
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
                return zzbR(zza, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002\u000b\u0003\u000b", new Object[]{"zzc", "zzd", "zze", "zzf"});
            case 3:
                return new zzgvz();
            case 4:
                return new zzgvy((zzgvx) null);
            case 5:
                return zza;
            case 6:
                zzhdm zzhdm = zzb;
                if (zzhdm == null) {
                    synchronized (zzgvz.class) {
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

    public final zzgwc zzh() {
        zzgwc zzgwc = this.zzd;
        return zzgwc == null ? zzgwc.zzf() : zzgwc;
    }
}
