package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgwz extends zzhbo implements zzhdf {
    /* access modifiers changed from: private */
    public static final zzgwz zza;
    private static volatile zzhdm zzb;
    /* access modifiers changed from: private */
    public int zzc;
    private zzhca zzd = zzbK();

    static {
        zzgwz zzgwz = new zzgwz();
        zza = zzgwz;
        zzhbo.zzca(zzgwz.class, zzgwz);
    }

    private zzgwz() {
    }

    public static zzgww zza() {
        return (zzgww) zza.zzaZ();
    }

    static /* synthetic */ void zze(zzgwz zzgwz, zzgwy zzgwy) {
        zzgwy.getClass();
        zzhca zzhca = zzgwz.zzd;
        if (!zzhca.zzc()) {
            zzgwz.zzd = zzhbo.zzbL(zzhca);
        }
        zzgwz.zzd.add(zzgwy);
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
                return zzbR(zza, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u000b\u0002\u001b", new Object[]{"zzc", "zzd", zzgwy.class});
            case 3:
                return new zzgwz();
            case 4:
                return new zzgww((zzgwv) null);
            case 5:
                return zza;
            case 6:
                zzhdm zzhdm = zzb;
                if (zzhdm == null) {
                    synchronized (zzgwz.class) {
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
