package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfsj extends zzhbo implements zzhdf {
    /* access modifiers changed from: private */
    public static final zzfsj zza;
    private static volatile zzhdm zzb;
    private int zzc;
    private int zzd;
    private String zze = "";
    private String zzf = "";
    private zzfsd zzg;

    static {
        zzfsj zzfsj = new zzfsj();
        zza = zzfsj;
        zzhbo.zzca(zzfsj.class, zzfsj);
    }

    private zzfsj() {
    }

    public static zzfsf zza() {
        return (zzfsf) zza.zzaZ();
    }

    static /* synthetic */ void zzd(zzfsj zzfsj, zzfsi zzfsi) {
        zzfsj.zzd = zzfsi.zza();
        zzfsj.zzc |= 1;
    }

    static /* synthetic */ void zze(zzfsj zzfsj, String str) {
        str.getClass();
        zzfsj.zzc |= 2;
        zzfsj.zze = str;
    }

    static /* synthetic */ void zzf(zzfsj zzfsj, zzfsd zzfsd) {
        zzfsd.getClass();
        zzfsj.zzg = zzfsd;
        zzfsj.zzc |= 8;
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
                return zzbR(zza, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001᠌\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဉ\u0003", new Object[]{"zzc", "zzd", zzfsh.zza, "zze", "zzf", "zzg"});
            case 3:
                return new zzfsj();
            case 4:
                return new zzfsf((zzfse) null);
            case 5:
                return zza;
            case 6:
                zzhdm zzhdm = zzb;
                if (zzhdm == null) {
                    synchronized (zzfsj.class) {
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
