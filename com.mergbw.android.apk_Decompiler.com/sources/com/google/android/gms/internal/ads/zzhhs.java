package com.google.android.gms.internal.ads;

@Deprecated
/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzhhs extends zzhbo implements zzhdf {
    /* access modifiers changed from: private */
    public static final zzhhs zza;
    private static volatile zzhdm zzb;
    private int zzc;
    private int zzd;
    private zzhhr zze;
    private zzhhr zzf;

    static {
        zzhhs zzhhs = new zzhhs();
        zza = zzhhs;
        zzhbo.zzca(zzhhs.class, zzhhs);
    }

    private zzhhs() {
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
                return zzbR(zza, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001᠌\u0000\u0002ဉ\u0001\u0003ဉ\u0002", new Object[]{"zzc", "zzd", zzhho.zza, "zze", "zzf"});
            case 3:
                return new zzhhs();
            case 4:
                return new zzhhm((zzhfr) null);
            case 5:
                return zza;
            case 6:
                zzhdm zzhdm = zzb;
                if (zzhdm == null) {
                    synchronized (zzhhs.class) {
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
