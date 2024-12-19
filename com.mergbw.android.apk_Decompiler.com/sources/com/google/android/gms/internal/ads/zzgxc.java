package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgxc extends zzhbo implements zzhdf {
    /* access modifiers changed from: private */
    public static final zzgxc zza;
    private static volatile zzhdm zzb;
    private int zzc;
    private int zzd;
    private zzgxf zze;

    static {
        zzgxc zzgxc = new zzgxc();
        zza = zzgxc;
        zzhbo.zzca(zzgxc.class, zzgxc);
    }

    private zzgxc() {
    }

    public static zzgxb zzc() {
        return (zzgxb) zza.zzaZ();
    }

    public static zzgxc zze(zzhac zzhac, zzhay zzhay) throws zzhcd {
        return (zzgxc) zzhbo.zzbr(zza, zzhac, zzhay);
    }

    public static zzhdm zzg() {
        return zza.zzbM();
    }

    static /* synthetic */ void zzh(zzgxc zzgxc, zzgxf zzgxf) {
        zzgxf.getClass();
        zzgxc.zze = zzgxf;
        zzgxc.zzc |= 1;
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
                return new zzgxc();
            case 4:
                return new zzgxb((zzgxa) null);
            case 5:
                return zza;
            case 6:
                zzhdm zzhdm = zzb;
                if (zzhdm == null) {
                    synchronized (zzgxc.class) {
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

    public final zzgxf zzf() {
        zzgxf zzgxf = this.zze;
        return zzgxf == null ? zzgxf.zzd() : zzgxf;
    }
}
