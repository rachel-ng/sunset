package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgwy extends zzhbo implements zzhdf {
    /* access modifiers changed from: private */
    public static final zzgwy zza;
    private static volatile zzhdm zzb;
    private String zzc = "";
    /* access modifiers changed from: private */
    public int zzd;
    /* access modifiers changed from: private */
    public int zze;
    /* access modifiers changed from: private */
    public int zzf;

    static {
        zzgwy zzgwy = new zzgwy();
        zza = zzgwy;
        zzhbo.zzca(zzgwy.class, zzgwy);
    }

    private zzgwy() {
    }

    public static zzgwx zza() {
        return (zzgwx) zza.zzaZ();
    }

    static /* synthetic */ void zzd(zzgwy zzgwy, String str) {
        str.getClass();
        zzgwy.zzc = str;
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
                return zzbR(zza, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001Ȉ\u0002\f\u0003\u000b\u0004\f", new Object[]{"zzc", "zzd", "zze", "zzf"});
            case 3:
                return new zzgwy();
            case 4:
                return new zzgwx((zzgwv) null);
            case 5:
                return zza;
            case 6:
                zzhdm zzhdm = zzb;
                if (zzhdm == null) {
                    synchronized (zzgwy.class) {
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
