package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfnk extends zzhbo implements zzhdf {
    /* access modifiers changed from: private */
    public static final zzfnk zza;
    private static volatile zzhdm zzb;
    /* access modifiers changed from: private */
    public zzhca zzc = zzbK();

    static {
        zzfnk zzfnk = new zzfnk();
        zza = zzfnk;
        zzhbo.zzca(zzfnk.class, zzfnk);
    }

    private zzfnk() {
    }

    public static zzfnh zzc() {
        return (zzfnh) zza.zzaZ();
    }

    static /* synthetic */ void zzf(zzfnk zzfnk, zzfnj zzfnj) {
        zzfnj.getClass();
        zzhca zzhca = zzfnk.zzc;
        if (!zzhca.zzc()) {
            zzfnk.zzc = zzhbo.zzbL(zzhca);
        }
        zzfnk.zzc.add(zzfnj);
    }

    public final int zza() {
        return this.zzc.size();
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
                return zzbR(zza, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zzc", zzfnj.class});
            case 3:
                return new zzfnk();
            case 4:
                return new zzfnh((zzfng) null);
            case 5:
                return zza;
            case 6:
                zzhdm zzhdm = zzb;
                if (zzhdm == null) {
                    synchronized (zzfnk.class) {
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
