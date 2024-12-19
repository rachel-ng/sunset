package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgvl extends zzhbo implements zzhdf {
    /* access modifiers changed from: private */
    public static final zzgvl zza;
    private static volatile zzhdm zzb;
    /* access modifiers changed from: private */
    public int zzc;
    private int zzd;

    static {
        zzgvl zzgvl = new zzgvl();
        zza = zzgvl;
        zzhbo.zzca(zzgvl.class, zzgvl);
    }

    private zzgvl() {
    }

    public static zzgvk zzd() {
        return (zzgvk) zza.zzaZ();
    }

    public static zzgvl zzf(zzhac zzhac, zzhay zzhay) throws zzhcd {
        return (zzgvl) zzhbo.zzbr(zza, zzhac, zzhay);
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
                return zzbR(zza, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\u000b", new Object[]{"zzd", "zzc"});
            case 3:
                return new zzgvl();
            case 4:
                return new zzgvk((zzgvj) null);
            case 5:
                return zza;
            case 6:
                zzhdm zzhdm = zzb;
                if (zzhdm == null) {
                    synchronized (zzgvl.class) {
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
