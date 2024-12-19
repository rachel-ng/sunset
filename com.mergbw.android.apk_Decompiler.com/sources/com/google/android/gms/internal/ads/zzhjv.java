package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzhjv extends zzhbo implements zzhdf {
    /* access modifiers changed from: private */
    public static final zzhjv zza;
    private static volatile zzhdm zzb;
    private int zzc;
    private String zzd = "";
    private String zze = "";
    private int zzf = 4;
    private zzhca zzg = zzhbo.zzbK();
    private String zzh = "";
    private String zzi = "";
    private boolean zzj;
    private double zzk;
    private zzhca zzl = zzbK();
    private int zzm;
    private boolean zzn;
    private boolean zzo;
    private boolean zzp;
    private boolean zzu;

    static {
        zzhjv zzhjv = new zzhjv();
        zza = zzhjv;
        zzhbo.zzca(zzhjv.class, zzhjv);
    }

    private zzhjv() {
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
                return zzbR(zza, "\u0001\u000e\u0000\u0001\u0001\u000e\u000e\u0000\u0002\u0000\u0001ဈ\u0000\u0002᠌\u0002\u0003\u001a\u0004ဈ\u0003\u0005ဈ\u0004\u0006ဇ\u0005\u0007က\u0006\b\u001b\tဈ\u0001\n᠌\u0007\u000bဇ\b\fဇ\t\rဇ\n\u000eဇ\u000b", new Object[]{"zzc", "zzd", "zzf", zzhjt.zza, "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", zzhjr.class, "zze", "zzm", zzhjo.zza, "zzn", "zzo", "zzp", "zzu"});
            case 3:
                return new zzhjv();
            case 4:
                return new zzhjm((zzhfr) null);
            case 5:
                return zza;
            case 6:
                zzhdm zzhdm = zzb;
                if (zzhdm == null) {
                    synchronized (zzhjv.class) {
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
