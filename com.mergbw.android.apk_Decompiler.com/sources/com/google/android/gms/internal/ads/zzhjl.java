package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzhjl extends zzhbo implements zzhdf {
    /* access modifiers changed from: private */
    public static final zzhjl zza;
    private static volatile zzhdm zzb;
    private int zzc;
    private int zzd;
    private String zze = "";
    private zzhbw zzf = zzbG();
    private int zzg;
    private zzhca zzh = zzbK();
    private zzhac zzi = zzhac.zzb;

    static {
        zzhjl zzhjl = new zzhjl();
        zza = zzhjl;
        zzhbo.zzca(zzhjl.class, zzhjl);
    }

    private zzhjl() {
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
                return zzbR(zza, "\u0001\u0006\u0000\u0001\u0001\u0007\u0006\u0000\u0002\u0000\u0001င\u0000\u0002ဈ\u0001\u0003\u0016\u0005င\u0002\u0006\u001b\u0007ည\u0003", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", zzhjj.class, "zzi"});
            case 3:
                return new zzhjl();
            case 4:
                return new zzhjk((zzhfr) null);
            case 5:
                return zza;
            case 6:
                zzhdm zzhdm = zzb;
                if (zzhdm == null) {
                    synchronized (zzhjl.class) {
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
