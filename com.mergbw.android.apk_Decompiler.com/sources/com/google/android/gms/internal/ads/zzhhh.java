package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzhhh extends zzhbo implements zzhdf {
    /* access modifiers changed from: private */
    public static final zzhhh zza;
    private static volatile zzhdm zzb;
    private int zzc;
    private zzhhg zzd;
    private zzhca zze = zzbK();
    private zzhac zzf = zzhac.zzb;
    private zzhac zzg = zzhac.zzb;
    private int zzh;
    private byte zzi = 2;

    static {
        zzhhh zzhhh = new zzhhh();
        zza = zzhhh;
        zzhbo.zzca(zzhhh.class, zzhhh);
    }

    private zzhhh() {
    }

    public static zzhhe zzc() {
        return (zzhhe) zza.zzaZ();
    }

    static /* synthetic */ void zzf(zzhhh zzhhh, zzhhd zzhhd) {
        zzhhd.getClass();
        zzhca zzhca = zzhhh.zze;
        if (!zzhca.zzc()) {
            zzhhh.zze = zzhbo.zzbL(zzhca);
        }
        zzhhh.zze.add(zzhhd);
    }

    /* access modifiers changed from: protected */
    public final Object zzde(zzhbn zzhbn, Object obj, Object obj2) {
        zzhbn zzhbn2 = zzhbn.GET_MEMOIZED_IS_INITIALIZED;
        byte b2 = 1;
        switch (zzhbn.ordinal()) {
            case 0:
                return Byte.valueOf(this.zzi);
            case 1:
                if (obj == null) {
                    b2 = 0;
                }
                this.zzi = b2;
                return null;
            case 2:
                return zzbR(zza, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0001\u0001\u0001ဉ\u0000\u0002Л\u0003ည\u0001\u0004ည\u0002\u0005င\u0003", new Object[]{"zzc", "zzd", "zze", zzhhd.class, "zzf", "zzg", "zzh"});
            case 3:
                return new zzhhh();
            case 4:
                return new zzhhe((zzhfr) null);
            case 5:
                return zza;
            case 6:
                zzhdm zzhdm = zzb;
                if (zzhdm == null) {
                    synchronized (zzhhh.class) {
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
