package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzhjg extends zzhbo implements zzhdf {
    /* access modifiers changed from: private */
    public static final zzhjg zza;
    private static volatile zzhdm zzb;
    private int zzc;
    private int zzd;
    private String zze = "";

    static {
        zzhjg zzhjg = new zzhjg();
        zza = zzhjg;
        zzhbo.zzca(zzhjg.class, zzhjg);
    }

    private zzhjg() {
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
                return zzbR(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002ဈ\u0001", new Object[]{"zzc", "zzd", zzhje.zza, "zze"});
            case 3:
                return new zzhjg();
            case 4:
                return new zzhjc((zzhfr) null);
            case 5:
                return zza;
            case 6:
                zzhdm zzhdm = zzb;
                if (zzhdm == null) {
                    synchronized (zzhjg.class) {
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
