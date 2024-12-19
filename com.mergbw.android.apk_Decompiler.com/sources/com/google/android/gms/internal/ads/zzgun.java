package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgun extends zzhbo implements zzhdf {
    /* access modifiers changed from: private */
    public static final zzgun zza;
    private static volatile zzhdm zzb;
    private int zzc;
    private zzguq zzd;
    /* access modifiers changed from: private */
    public int zze;

    static {
        zzgun zzgun = new zzgun();
        zza = zzgun;
        zzhbo.zzca(zzgun.class, zzgun);
    }

    private zzgun() {
    }

    public static zzgum zzc() {
        return (zzgum) zza.zzaZ();
    }

    public static zzgun zze() {
        return zza;
    }

    static /* synthetic */ void zzg(zzgun zzgun, zzguq zzguq) {
        zzguq.getClass();
        zzgun.zzd = zzguq;
        zzgun.zzc |= 1;
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
                return new zzgun();
            case 4:
                return new zzgum((zzgul) null);
            case 5:
                return zza;
            case 6:
                zzhdm zzhdm = zzb;
                if (zzhdm == null) {
                    synchronized (zzgun.class) {
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
        zzguq zzguq = this.zzd;
        return zzguq == null ? zzguq.zze() : zzguq;
    }
}
