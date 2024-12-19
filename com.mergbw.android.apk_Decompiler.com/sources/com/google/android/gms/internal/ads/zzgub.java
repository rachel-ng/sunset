package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgub extends zzhbo implements zzhdf {
    /* access modifiers changed from: private */
    public static final zzgub zza;
    private static volatile zzhdm zzb;
    /* access modifiers changed from: private */
    public int zzc;

    static {
        zzgub zzgub = new zzgub();
        zza = zzgub;
        zzhbo.zzca(zzgub.class, zzgub);
    }

    private zzgub() {
    }

    public static zzgua zzc() {
        return (zzgua) zza.zzaZ();
    }

    public static zzgub zze() {
        return zza;
    }

    public final int zza() {
        return this.zzc;
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
                return zzbR(zza, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u000b", new Object[]{"zzc"});
            case 3:
                return new zzgub();
            case 4:
                return new zzgua((zzgtz) null);
            case 5:
                return zza;
            case 6:
                zzhdm zzhdm = zzb;
                if (zzhdm == null) {
                    synchronized (zzgub.class) {
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
