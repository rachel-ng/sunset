package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzhhd extends zzhbo implements zzhdf {
    /* access modifiers changed from: private */
    public static final zzhhd zza;
    private static volatile zzhdm zzb;
    private int zzc;
    private zzhac zzd = zzhac.zzb;
    private zzhac zze = zzhac.zzb;
    private byte zzf = 2;

    static {
        zzhhd zzhhd = new zzhhd();
        zza = zzhhd;
        zzhbo.zzca(zzhhd.class, zzhhd);
    }

    private zzhhd() {
    }

    public static zzhhc zzc() {
        return (zzhhc) zza.zzaZ();
    }

    static /* synthetic */ void zzf(zzhhd zzhhd, zzhac zzhac) {
        zzhhd.zzc |= 1;
        zzhhd.zzd = zzhac;
    }

    static /* synthetic */ void zzg(zzhhd zzhhd, zzhac zzhac) {
        zzhhd.zzc |= 2;
        zzhhd.zze = zzhac;
    }

    /* access modifiers changed from: protected */
    public final Object zzde(zzhbn zzhbn, Object obj, Object obj2) {
        zzhbn zzhbn2 = zzhbn.GET_MEMOIZED_IS_INITIALIZED;
        byte b2 = 1;
        switch (zzhbn.ordinal()) {
            case 0:
                return Byte.valueOf(this.zzf);
            case 1:
                if (obj == null) {
                    b2 = 0;
                }
                this.zzf = b2;
                return null;
            case 2:
                return zzbR(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0001\u0001ᔊ\u0000\u0002ည\u0001", new Object[]{"zzc", "zzd", "zze"});
            case 3:
                return new zzhhd();
            case 4:
                return new zzhhc((zzhfr) null);
            case 5:
                return zza;
            case 6:
                zzhdm zzhdm = zzb;
                if (zzhdm == null) {
                    synchronized (zzhhd.class) {
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
