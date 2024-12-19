package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgtv extends zzhbo implements zzhdf {
    /* access modifiers changed from: private */
    public static final zzgtv zza;
    private static volatile zzhdm zzb;
    private int zzc;
    private int zzd;
    /* access modifiers changed from: private */
    public zzhac zze = zzhac.zzb;
    private zzgub zzf;

    static {
        zzgtv zzgtv = new zzgtv();
        zza = zzgtv;
        zzhbo.zzca(zzgtv.class, zzgtv);
    }

    private zzgtv() {
    }

    public static zzgtu zzc() {
        return (zzgtu) zza.zzaZ();
    }

    public static zzgtv zze(zzhac zzhac, zzhay zzhay) throws zzhcd {
        return (zzgtv) zzhbo.zzbr(zza, zzhac, zzhay);
    }

    public static zzhdm zzh() {
        return zza.zzbM();
    }

    static /* synthetic */ void zzj(zzgtv zzgtv, zzgub zzgub) {
        zzgub.getClass();
        zzgtv.zzf = zzgub;
        zzgtv.zzc |= 1;
    }

    public final int zza() {
        return this.zzd;
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
                return zzbR(zza, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\n\u0003ဉ\u0000", new Object[]{"zzc", "zzd", "zze", "zzf"});
            case 3:
                return new zzgtv();
            case 4:
                return new zzgtu((zzgtt) null);
            case 5:
                return zza;
            case 6:
                zzhdm zzhdm = zzb;
                if (zzhdm == null) {
                    synchronized (zzgtv.class) {
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

    public final zzgub zzf() {
        zzgub zzgub = this.zzf;
        return zzgub == null ? zzgub.zze() : zzgub;
    }

    public final zzhac zzg() {
        return this.zze;
    }
}
