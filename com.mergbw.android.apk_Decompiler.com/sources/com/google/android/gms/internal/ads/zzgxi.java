package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgxi extends zzhbo implements zzhdf {
    /* access modifiers changed from: private */
    public static final zzgxi zza;
    private static volatile zzhdm zzb;
    private int zzc;
    private int zzd;
    private zzgxl zze;

    static {
        zzgxi zzgxi = new zzgxi();
        zza = zzgxi;
        zzhbo.zzca(zzgxi.class, zzgxi);
    }

    private zzgxi() {
    }

    public static zzgxh zzc() {
        return (zzgxh) zza.zzaZ();
    }

    public static zzgxi zze(zzhac zzhac, zzhay zzhay) throws zzhcd {
        return (zzgxi) zzhbo.zzbr(zza, zzhac, zzhay);
    }

    public static zzhdm zzg() {
        return zza.zzbM();
    }

    static /* synthetic */ void zzh(zzgxi zzgxi, zzgxl zzgxl) {
        zzgxl.getClass();
        zzgxi.zze = zzgxl;
        zzgxi.zzc |= 1;
    }

    public final int zza() {
        return this.zzd;
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
                return zzbR(zza, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000", new Object[]{"zzc", "zzd", "zze"});
            case 3:
                return new zzgxi();
            case 4:
                return new zzgxh((zzgxg) null);
            case 5:
                return zza;
            case 6:
                zzhdm zzhdm = zzb;
                if (zzhdm == null) {
                    synchronized (zzgxi.class) {
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

    public final zzgxl zzf() {
        zzgxl zzgxl = this.zze;
        return zzgxl == null ? zzgxl.zze() : zzgxl;
    }
}
