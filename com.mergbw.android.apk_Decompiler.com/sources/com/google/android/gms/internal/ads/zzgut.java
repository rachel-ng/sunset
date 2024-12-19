package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgut extends zzhbo implements zzhdf {
    /* access modifiers changed from: private */
    public static final zzgut zza;
    private static volatile zzhdm zzb;
    private int zzc;
    private int zzd;
    private zzguz zze;
    /* access modifiers changed from: private */
    public zzhac zzf = zzhac.zzb;

    static {
        zzgut zzgut = new zzgut();
        zza = zzgut;
        zzhbo.zzca(zzgut.class, zzgut);
    }

    private zzgut() {
    }

    public static zzgus zzc() {
        return (zzgus) zza.zzaZ();
    }

    public static zzgut zze(zzhac zzhac, zzhay zzhay) throws zzhcd {
        return (zzgut) zzhbo.zzbr(zza, zzhac, zzhay);
    }

    public static zzhdm zzh() {
        return zza.zzbM();
    }

    static /* synthetic */ void zzi(zzgut zzgut, zzguz zzguz) {
        zzguz.getClass();
        zzgut.zze = zzguz;
        zzgut.zzc |= 1;
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
                return zzbR(zza, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000\u0003\n", new Object[]{"zzc", "zzd", "zze", "zzf"});
            case 3:
                return new zzgut();
            case 4:
                return new zzgus((zzgur) null);
            case 5:
                return zza;
            case 6:
                zzhdm zzhdm = zzb;
                if (zzhdm == null) {
                    synchronized (zzgut.class) {
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

    public final zzguz zzf() {
        zzguz zzguz = this.zze;
        return zzguz == null ? zzguz.zze() : zzguz;
    }

    public final zzhac zzg() {
        return this.zzf;
    }
}
