package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzguw extends zzhbo implements zzhdf {
    /* access modifiers changed from: private */
    public static final zzguw zza;
    private static volatile zzhdm zzb;
    private int zzc;
    private zzguz zzd;
    /* access modifiers changed from: private */
    public int zze;

    static {
        zzguw zzguw = new zzguw();
        zza = zzguw;
        zzhbo.zzca(zzguw.class, zzguw);
    }

    private zzguw() {
    }

    public static zzguv zzc() {
        return (zzguv) zza.zzaZ();
    }

    public static zzguw zze(zzhac zzhac, zzhay zzhay) throws zzhcd {
        return (zzguw) zzhbo.zzbr(zza, zzhac, zzhay);
    }

    static /* synthetic */ void zzg(zzguw zzguw, zzguz zzguz) {
        zzguz.getClass();
        zzguw.zzd = zzguz;
        zzguw.zzc |= 1;
    }

    public final int zza() {
        return this.zze;
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
                return zzbR(zza, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002\u000b", new Object[]{"zzc", "zzd", "zze"});
            case 3:
                return new zzguw();
            case 4:
                return new zzguv((zzguu) null);
            case 5:
                return zza;
            case 6:
                zzhdm zzhdm = zzb;
                if (zzhdm == null) {
                    synchronized (zzguw.class) {
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
        zzguz zzguz = this.zzd;
        return zzguz == null ? zzguz.zze() : zzguz;
    }
}
