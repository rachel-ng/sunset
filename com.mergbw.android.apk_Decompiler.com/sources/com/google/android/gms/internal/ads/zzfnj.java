package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfnj extends zzhbo implements zzhdf {
    /* access modifiers changed from: private */
    public static final zzfnj zza;
    private static volatile zzhdm zzb;
    private int zzc;
    private zzfnf zzd;

    static {
        zzfnj zzfnj = new zzfnj();
        zza = zzfnj;
        zzhbo.zzca(zzfnj.class, zzfnj);
    }

    private zzfnj() {
    }

    public static zzfni zza() {
        return (zzfni) zza.zzaZ();
    }

    static /* synthetic */ void zzd(zzfnj zzfnj, zzfnf zzfnf) {
        zzfnf.getClass();
        zzfnj.zzd = zzfnf;
        zzfnj.zzc |= 1;
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
                return zzbR(zza, "\u0000\u0001\u0000\u0001\u0006\u0006\u0001\u0000\u0000\u0000\u0006ဉ\u0000", new Object[]{"zzc", "zzd"});
            case 3:
                return new zzfnj();
            case 4:
                return new zzfni((zzfng) null);
            case 5:
                return zza;
            case 6:
                zzhdm zzhdm = zzb;
                if (zzhdm == null) {
                    synchronized (zzfnj.class) {
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
