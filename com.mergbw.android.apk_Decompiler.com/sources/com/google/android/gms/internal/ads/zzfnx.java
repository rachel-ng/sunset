package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfnx extends zzhbo implements zzhdf {
    /* access modifiers changed from: private */
    public static final zzfnx zza;
    private static volatile zzhdm zzb;
    private zzhca zzc = zzbK();

    static {
        zzfnx zzfnx = new zzfnx();
        zza = zzfnx;
        zzhbo.zzca(zzfnx.class, zzfnx);
    }

    private zzfnx() {
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
                return zzbR(zza, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zzc", zzfnu.class});
            case 3:
                return new zzfnx();
            case 4:
                return new zzfnw((zzfnv) null);
            case 5:
                return zza;
            case 6:
                zzhdm zzhdm = zzb;
                if (zzhdm == null) {
                    synchronized (zzfnx.class) {
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
