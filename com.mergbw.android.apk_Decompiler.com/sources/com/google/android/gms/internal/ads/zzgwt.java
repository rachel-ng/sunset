package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgwt extends zzhbo implements zzhdf {
    /* access modifiers changed from: private */
    public static final zzgwt zza;
    private static volatile zzhdm zzb;
    private int zzc;
    private zzgwh zzd;
    /* access modifiers changed from: private */
    public int zze;
    /* access modifiers changed from: private */
    public int zzf;
    /* access modifiers changed from: private */
    public int zzg;

    static {
        zzgwt zzgwt = new zzgwt();
        zza = zzgwt;
        zzhbo.zzca(zzgwt.class, zzgwt);
    }

    private zzgwt() {
    }

    public static zzgws zze() {
        return (zzgws) zza.zzaZ();
    }

    static /* synthetic */ void zzh(zzgwt zzgwt, zzgwh zzgwh) {
        zzgwh.getClass();
        zzgwt.zzd = zzgwh;
        zzgwt.zzc |= 1;
    }

    public final int zza() {
        return this.zzf;
    }

    public final zzgwh zzc() {
        zzgwh zzgwh = this.zzd;
        return zzgwh == null ? zzgwh.zze() : zzgwh;
    }

    public final zzgwj zzd() {
        zzgwj zzb2 = zzgwj.zzb(this.zze);
        return zzb2 == null ? zzgwj.UNRECOGNIZED : zzb2;
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
                return zzbR(zza, "\u0000\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဉ\u0000\u0002\f\u0003\u000b\u0004\f", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg"});
            case 3:
                return new zzgwt();
            case 4:
                return new zzgws((zzgwq) null);
            case 5:
                return zza;
            case 6:
                zzhdm zzhdm = zzb;
                if (zzhdm == null) {
                    synchronized (zzgwt.class) {
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

    public final zzgxn zzg() {
        zzgxn zzb2 = zzgxn.zzb(this.zzg);
        return zzb2 == null ? zzgxn.UNRECOGNIZED : zzb2;
    }

    public final boolean zzl() {
        return (this.zzc & 1) != 0;
    }
}
