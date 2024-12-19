package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgvc extends zzhbo implements zzhdf {
    /* access modifiers changed from: private */
    public static final zzgvc zza;
    private static volatile zzhdm zzb;
    private int zzc;
    /* access modifiers changed from: private */
    public zzhac zzd = zzhac.zzb;

    static {
        zzgvc zzgvc = new zzgvc();
        zza = zzgvc;
        zzhbo.zzca(zzgvc.class, zzgvc);
    }

    private zzgvc() {
    }

    public static zzgvb zzc() {
        return (zzgvb) zza.zzaZ();
    }

    public static zzgvc zze(zzhac zzhac, zzhay zzhay) throws zzhcd {
        return (zzgvc) zzhbo.zzbr(zza, zzhac, zzhay);
    }

    public static zzhdm zzg() {
        return zza.zzbM();
    }

    public final int zza() {
        return this.zzc;
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
                return zzbR(zza, "\u0000\u0002\u0000\u0000\u0001\u0003\u0002\u0000\u0000\u0000\u0001\u000b\u0003\n", new Object[]{"zzc", "zzd"});
            case 3:
                return new zzgvc();
            case 4:
                return new zzgvb((zzgva) null);
            case 5:
                return zza;
            case 6:
                zzhdm zzhdm = zzb;
                if (zzhdm == null) {
                    synchronized (zzgvc.class) {
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

    public final zzhac zzf() {
        return this.zzd;
    }
}
