package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzguk extends zzhbo implements zzhdf {
    /* access modifiers changed from: private */
    public static final zzguk zza;
    private static volatile zzhdm zzb;
    private int zzc;
    private int zzd;
    private zzguq zze;
    /* access modifiers changed from: private */
    public zzhac zzf = zzhac.zzb;

    static {
        zzguk zzguk = new zzguk();
        zza = zzguk;
        zzhbo.zzca(zzguk.class, zzguk);
    }

    private zzguk() {
    }

    public static zzguj zzc() {
        return (zzguj) zza.zzaZ();
    }

    public static zzguk zze() {
        return zza;
    }

    static /* synthetic */ void zzh(zzguk zzguk, zzguq zzguq) {
        zzguq.getClass();
        zzguk.zze = zzguq;
        zzguk.zzc |= 1;
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
                return new zzguk();
            case 4:
                return new zzguj((zzgui) null);
            case 5:
                return zza;
            case 6:
                zzhdm zzhdm = zzb;
                if (zzhdm == null) {
                    synchronized (zzguk.class) {
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

    public final zzguq zzf() {
        zzguq zzguq = this.zze;
        return zzguq == null ? zzguq.zze() : zzguq;
    }

    public final zzhac zzg() {
        return this.zzf;
    }
}
