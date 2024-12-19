package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfsd extends zzhbo implements zzhdf {
    private static final zzhbx zza = new zzfry();
    /* access modifiers changed from: private */
    public static final zzfsd zzb;
    private static volatile zzhdm zzc;
    private int zzd;
    private zzhbw zze = zzbG();
    private String zzf = "";
    private String zzg = "";
    private String zzh = "";

    static {
        zzfsd zzfsd = new zzfsd();
        zzb = zzfsd;
        zzhbo.zzca(zzfsd.class, zzfsd);
    }

    private zzfsd() {
    }

    public static zzfsc zza() {
        return (zzfsc) zzb.zzaZ();
    }

    static /* synthetic */ void zzd(zzfsd zzfsd, zzfsb zzfsb) {
        zzfsb.getClass();
        zzhbw zzhbw = zzfsd.zze;
        if (!zzhbw.zzc()) {
            zzfsd.zze = zzhbo.zzbH(zzhbw);
        }
        zzfsd.zze.zzi(zzfsb.zza());
    }

    static /* synthetic */ void zze(zzfsd zzfsd, String str) {
        str.getClass();
        zzfsd.zzd |= 1;
        zzfsd.zzf = str;
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
                return zzbR(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001ࠞ\u0002ဈ\u0000\u0003ဈ\u0001\u0004ဈ\u0002", new Object[]{"zzd", "zze", zzfsa.zza, "zzf", "zzg", "zzh"});
            case 3:
                return new zzfsd();
            case 4:
                return new zzfsc((zzfry) null);
            case 5:
                return zzb;
            case 6:
                zzhdm zzhdm = zzc;
                if (zzhdm == null) {
                    synchronized (zzfsd.class) {
                        zzhdm = zzc;
                        if (zzhdm == null) {
                            zzhdm = new zzhbj(zzb);
                            zzc = zzhdm;
                        }
                    }
                }
                return zzhdm;
            default:
                throw new UnsupportedOperationException();
        }
    }
}
