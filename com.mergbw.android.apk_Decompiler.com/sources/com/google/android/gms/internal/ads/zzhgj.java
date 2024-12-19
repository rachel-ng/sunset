package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzhgj extends zzhbo implements zzhdf {
    private static final zzhbx zza = new zzhfs();
    private static final zzhbx zzb = new zzhft();
    /* access modifiers changed from: private */
    public static final zzhgj zzc;
    private static volatile zzhdm zzd;
    private boolean zzA;
    private zzhbw zzB = zzbG();
    private int zze;
    private int zzf;
    private boolean zzg;
    private String zzh = "";
    private zzhca zzi = zzhbo.zzbK();
    private int zzj;
    private boolean zzk;
    private boolean zzl;
    private boolean zzm;
    private String zzn = "";
    private int zzo;
    private int zzp;
    private int zzu;
    private boolean zzv;
    private zzhca zzw = zzbK();
    private boolean zzx;
    private long zzy;
    private zzhbw zzz = zzbG();

    static {
        zzhgj zzhgj = new zzhgj();
        zzc = zzhgj;
        zzhbo.zzca(zzhgj.class, zzhgj);
    }

    private zzhgj() {
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
                return zzbR(zzc, "\u0001\u0013\u0000\u0001\u0001\u0013\u0013\u0000\u0004\u0000\u0001᠌\u0000\u0002ဇ\u0001\u0003ဈ\u0002\u0004\u001a\u0005᠌\u0003\u0006ဇ\u0004\u0007ဇ\u0005\bဇ\u0006\tဈ\u0007\nင\b\u000bင\t\fင\n\rဇ\u000b\u000e\u001b\u000fဇ\f\u0010ဂ\r\u0011ࠬ\u0012ဇ\u000e\u0013ࠬ", new Object[]{"zze", "zzf", zzhgh.zza, "zzg", "zzh", "zzi", "zzj", zzhgb.zza, "zzk", "zzl", "zzm", "zzn", "zzo", "zzp", "zzu", "zzv", "zzw", zzhfz.class, "zzx", "zzy", "zzz", zzhfl.zzb(), "zzA", "zzB", zzhge.zza});
            case 3:
                return new zzhgj();
            case 4:
                return new zzhfu((zzhfr) null);
            case 5:
                return zzc;
            case 6:
                zzhdm zzhdm = zzd;
                if (zzhdm == null) {
                    synchronized (zzhgj.class) {
                        zzhdm = zzd;
                        if (zzhdm == null) {
                            zzhdm = new zzhbj(zzc);
                            zzd = zzhdm;
                        }
                    }
                }
                return zzhdm;
            default:
                throw new UnsupportedOperationException();
        }
    }
}
