package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzhij extends zzhbo implements zzhdf {
    /* access modifiers changed from: private */
    public static final zzhij zza;
    private static volatile zzhdm zzb;
    private int zzc;
    private int zzd;
    private String zze = "";
    private zzhac zzf = zzhac.zzb;
    private zzhac zzg = zzhac.zzb;

    static {
        zzhij zzhij = new zzhij();
        zza = zzhij;
        zzhbo.zzca(zzhij.class, zzhij);
    }

    private zzhij() {
    }

    public static zzhif zzc() {
        return (zzhif) zza.zzaZ();
    }

    static /* synthetic */ void zzf(zzhij zzhij, zzhii zzhii) {
        zzhij.zzd = zzhii.zza();
        zzhij.zzc |= 1;
    }

    static /* synthetic */ void zzg(zzhij zzhij, String str) {
        zzhij.zzc |= 2;
        zzhij.zze = "image/png";
    }

    static /* synthetic */ void zzh(zzhij zzhij, zzhac zzhac) {
        zzhac.getClass();
        zzhij.zzc |= 4;
        zzhij.zzf = zzhac;
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
                return zzbR(zza, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001᠌\u0000\u0002ဈ\u0001\u0003ည\u0002\u0004ည\u0003", new Object[]{"zzc", "zzd", zzhih.zza, "zze", "zzf", "zzg"});
            case 3:
                return new zzhij();
            case 4:
                return new zzhif((zzhfr) null);
            case 5:
                return zza;
            case 6:
                zzhdm zzhdm = zzb;
                if (zzhdm == null) {
                    synchronized (zzhij.class) {
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
