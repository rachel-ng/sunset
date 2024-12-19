package com.google.android.gms.internal.ads;

@Deprecated
/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgxq extends zzhbo implements zzhdf {
    /* access modifiers changed from: private */
    public static final zzgxq zza;
    private static volatile zzhdm zzb;
    private String zzc = "";
    private zzhca zzd = zzbK();

    static {
        zzgxq zzgxq = new zzgxq();
        zza = zzgxq;
        zzhbo.zzca(zzgxq.class, zzgxq);
    }

    private zzgxq() {
    }

    public static zzgxq zzc() {
        return zza;
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
                return zzbR(zza, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001Ȉ\u0002\u001b", new Object[]{"zzc", "zzd", zzgwp.class});
            case 3:
                return new zzgxq();
            case 4:
                return new zzgxp((zzgxo) null);
            case 5:
                return zza;
            case 6:
                zzhdm zzhdm = zzb;
                if (zzhdm == null) {
                    synchronized (zzgxq.class) {
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
