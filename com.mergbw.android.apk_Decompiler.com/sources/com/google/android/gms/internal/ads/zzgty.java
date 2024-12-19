package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgty extends zzhbo implements zzhdf {
    /* access modifiers changed from: private */
    public static final zzgty zza;
    private static volatile zzhdm zzb;
    private int zzc;
    /* access modifiers changed from: private */
    public int zzd;
    private zzgub zze;

    static {
        zzgty zzgty = new zzgty();
        zza = zzgty;
        zzhbo.zzca(zzgty.class, zzgty);
    }

    private zzgty() {
    }

    public static zzgtx zzc() {
        return (zzgtx) zza.zzaZ();
    }

    public static zzgty zze(zzhac zzhac, zzhay zzhay) throws zzhcd {
        return (zzgty) zzhbo.zzbr(zza, zzhac, zzhay);
    }

    static /* synthetic */ void zzh(zzgty zzgty, zzgub zzgub) {
        zzgub.getClass();
        zzgty.zze = zzgub;
        zzgty.zzc |= 1;
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
                return zzbR(zza, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000", new Object[]{"zzc", "zzd", "zze"});
            case 3:
                return new zzgty();
            case 4:
                return new zzgtx((zzgtw) null);
            case 5:
                return zza;
            case 6:
                zzhdm zzhdm = zzb;
                if (zzhdm == null) {
                    synchronized (zzgty.class) {
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
        zzgub zzgub = this.zze;
        return zzgub == null ? zzgub.zze() : zzgub;
    }
}
