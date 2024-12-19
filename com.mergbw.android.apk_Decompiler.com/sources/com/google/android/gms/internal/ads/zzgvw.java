package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgvw extends zzhbo implements zzhdf {
    /* access modifiers changed from: private */
    public static final zzgvw zza;
    private static volatile zzhdm zzb;
    private int zzc;
    private int zzd;
    private zzgwc zze;
    /* access modifiers changed from: private */
    public zzhac zzf = zzhac.zzb;

    static {
        zzgvw zzgvw = new zzgvw();
        zza = zzgvw;
        zzhbo.zzca(zzgvw.class, zzgvw);
    }

    private zzgvw() {
    }

    public static zzgvv zzc() {
        return (zzgvv) zza.zzaZ();
    }

    public static zzgvw zze() {
        return zza;
    }

    public static zzgvw zzf(zzhac zzhac, zzhay zzhay) throws zzhcd {
        return (zzgvw) zzhbo.zzbr(zza, zzhac, zzhay);
    }

    public static zzhdm zzi() {
        return zza.zzbM();
    }

    static /* synthetic */ void zzj(zzgvw zzgvw, zzgwc zzgwc) {
        zzgwc.getClass();
        zzgvw.zze = zzgwc;
        zzgvw.zzc |= 1;
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
                return new zzgvw();
            case 4:
                return new zzgvv((zzgvu) null);
            case 5:
                return zza;
            case 6:
                zzhdm zzhdm = zzb;
                if (zzhdm == null) {
                    synchronized (zzgvw.class) {
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

    public final zzgwc zzg() {
        zzgwc zzgwc = this.zze;
        return zzgwc == null ? zzgwc.zzf() : zzgwc;
    }

    public final zzhac zzh() {
        return this.zzf;
    }
}
