package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzhit extends zzhbo implements zzhdf {
    /* access modifiers changed from: private */
    public static final zzhit zza;
    private static volatile zzhdm zzb;
    private int zzc;
    private String zzd = "";
    private long zze;
    private boolean zzf;
    private int zzg;
    private String zzh = "";
    private String zzi = "";
    private boolean zzj;

    static {
        zzhit zzhit = new zzhit();
        zza = zzhit;
        zzhbo.zzca(zzhit.class, zzhit);
    }

    private zzhit() {
    }

    public static zzhis zzc() {
        return (zzhis) zza.zzaZ();
    }

    static /* synthetic */ void zzf(zzhit zzhit, String str) {
        zzhit.zzc |= 1;
        zzhit.zzd = str;
    }

    static /* synthetic */ void zzg(zzhit zzhit, long j) {
        zzhit.zzc |= 2;
        zzhit.zze = j;
    }

    static /* synthetic */ void zzh(zzhit zzhit, boolean z) {
        zzhit.zzc |= 4;
        zzhit.zzf = z;
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
                return zzbR(zza, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဂ\u0001\u0003ဇ\u0002\u0004᠌\u0003\u0005ဈ\u0004\u0006ဈ\u0005\u0007ဇ\u0006", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", zzhiv.zza, "zzh", "zzi", "zzj"});
            case 3:
                return new zzhit();
            case 4:
                return new zzhis((zzhfr) null);
            case 5:
                return zza;
            case 6:
                zzhdm zzhdm = zzb;
                if (zzhdm == null) {
                    synchronized (zzhit.class) {
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
