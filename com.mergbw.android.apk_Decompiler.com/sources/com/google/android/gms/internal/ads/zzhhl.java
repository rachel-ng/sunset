package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzhhl extends zzhbo implements zzhdf {
    /* access modifiers changed from: private */
    public static final zzhhl zza;
    private static volatile zzhdm zzb;
    private int zzc;
    private zzhhk zzd;
    private zzhca zze = zzbK();
    private zzhac zzf = zzhac.zzb;
    private zzhac zzg;
    private int zzh;
    private zzhac zzi;
    private byte zzj = 2;

    static {
        zzhhl zzhhl = new zzhhl();
        zza = zzhhl;
        zzhbo.zzca(zzhhl.class, zzhhl);
    }

    private zzhhl() {
        zzhac zzhac = zzhac.zzb;
        this.zzg = zzhac;
        this.zzi = zzhac;
    }

    /* access modifiers changed from: protected */
    public final Object zzde(zzhbn zzhbn, Object obj, Object obj2) {
        zzhbn zzhbn2 = zzhbn.GET_MEMOIZED_IS_INITIALIZED;
        byte b2 = 1;
        switch (zzhbn.ordinal()) {
            case 0:
                return Byte.valueOf(this.zzj);
            case 1:
                if (obj == null) {
                    b2 = 0;
                }
                this.zzj = b2;
                return null;
            case 2:
                return zzbR(zza, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0001\u0001\u0001ဉ\u0000\u0002Л\u0003ည\u0001\u0004ည\u0002\u0005င\u0003\u0006ည\u0004", new Object[]{"zzc", "zzd", "zze", zzhhd.class, "zzf", "zzg", "zzh", "zzi"});
            case 3:
                return new zzhhl();
            case 4:
                return new zzhhi((zzhfr) null);
            case 5:
                return zza;
            case 6:
                zzhdm zzhdm = zzb;
                if (zzhdm == null) {
                    synchronized (zzhhl.class) {
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
