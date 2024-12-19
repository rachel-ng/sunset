package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgvf extends zzhbo implements zzhdf {
    /* access modifiers changed from: private */
    public static final zzgvf zza;
    private static volatile zzhdm zzb;
    /* access modifiers changed from: private */
    public int zzc;
    private int zzd;

    static {
        zzgvf zzgvf = new zzgvf();
        zza = zzgvf;
        zzhbo.zzca(zzgvf.class, zzgvf);
    }

    private zzgvf() {
    }

    public static zzgve zzd() {
        return (zzgve) zza.zzaZ();
    }

    public static zzgvf zzf(zzhac zzhac, zzhay zzhay) throws zzhcd {
        return (zzgvf) zzhbo.zzbr(zza, zzhac, zzhay);
    }

    public final int zza() {
        return this.zzc;
    }

    public final int zzc() {
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
                return zzbR(zza, "\u0000\u0002\u0000\u0000\u0002\u0003\u0002\u0000\u0000\u0000\u0002\u000b\u0003\u000b", new Object[]{"zzc", "zzd"});
            case 3:
                return new zzgvf();
            case 4:
                return new zzgve((zzgvd) null);
            case 5:
                return zza;
            case 6:
                zzhdm zzhdm = zzb;
                if (zzhdm == null) {
                    synchronized (zzgvf.class) {
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
}
