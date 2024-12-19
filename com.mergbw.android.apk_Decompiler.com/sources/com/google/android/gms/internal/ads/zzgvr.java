package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgvr extends zzhbo implements zzhdf {
    /* access modifiers changed from: private */
    public static final zzgvr zza;
    private static volatile zzhdm zzb;

    static {
        zzgvr zzgvr = new zzgvr();
        zza = zzgvr;
        zzhbo.zzca(zzgvr.class, zzgvr);
    }

    private zzgvr() {
    }

    public static zzgvr zzc() {
        return zza;
    }

    public static zzgvr zzd(zzhac zzhac, zzhay zzhay) throws zzhcd {
        return (zzgvr) zzhbo.zzbr(zza, zzhac, zzhay);
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
                return zzbR(zza, "\u0000\u0000", (Object[]) null);
            case 3:
                return new zzgvr();
            case 4:
                return new zzgvq((zzgvp) null);
            case 5:
                return zza;
            case 6:
                zzhdm zzhdm = zzb;
                if (zzhdm == null) {
                    synchronized (zzgvr.class) {
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
