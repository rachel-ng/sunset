package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzhfq extends zzhbo implements zzhdf {
    /* access modifiers changed from: private */
    public static final zzhfq zza;
    private static volatile zzhdm zzb;
    private zzhca zzc = zzbK();

    static {
        zzhfq zzhfq = new zzhfq();
        zza = zzhfq;
        zzhbo.zzca(zzhfq.class, zzhfq);
    }

    private zzhfq() {
    }

    public static zzhfp zzc() {
        return (zzhfp) zza.zzaZ();
    }

    static /* synthetic */ void zzf(zzhfq zzhfq, zzhfo zzhfo) {
        zzhfo.getClass();
        zzhca zzhca = zzhfq.zzc;
        if (!zzhca.zzc()) {
            zzhfq.zzc = zzhbo.zzbL(zzhca);
        }
        zzhfq.zzc.add(zzhfo);
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
                return zzbR(zza, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zzc", zzhfo.class});
            case 3:
                return new zzhfq();
            case 4:
                return new zzhfp((zzhfm) null);
            case 5:
                return zza;
            case 6:
                zzhdm zzhdm = zzb;
                if (zzhdm == null) {
                    synchronized (zzhfq.class) {
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
