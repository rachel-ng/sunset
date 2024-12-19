package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgxl extends zzhbo implements zzhdf {
    /* access modifiers changed from: private */
    public static final zzgxl zza;
    private static volatile zzhdm zzb;
    private int zzc;
    private String zzd = "";
    private zzgwm zze;

    static {
        zzgxl zzgxl = new zzgxl();
        zza = zzgxl;
        zzhbo.zzca(zzgxl.class, zzgxl);
    }

    private zzgxl() {
    }

    public static zzgxk zzc() {
        return (zzgxk) zza.zzaZ();
    }

    public static zzgxl zze() {
        return zza;
    }

    public static zzgxl zzf(zzhac zzhac, zzhay zzhay) throws zzhcd {
        return (zzgxl) zzhbo.zzbr(zza, zzhac, zzhay);
    }

    static /* synthetic */ void zzh(zzgxl zzgxl, String str) {
        str.getClass();
        zzgxl.zzd = str;
    }

    static /* synthetic */ void zzi(zzgxl zzgxl, zzgwm zzgwm) {
        zzgwm.getClass();
        zzgxl.zze = zzgwm;
        zzgxl.zzc |= 1;
    }

    public final zzgwm zza() {
        zzgwm zzgwm = this.zze;
        return zzgwm == null ? zzgwm.zze() : zzgwm;
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
                return zzbR(zza, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002ဉ\u0000", new Object[]{"zzc", "zzd", "zze"});
            case 3:
                return new zzgxl();
            case 4:
                return new zzgxk((zzgxj) null);
            case 5:
                return zza;
            case 6:
                zzhdm zzhdm = zzb;
                if (zzhdm == null) {
                    synchronized (zzgxl.class) {
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

    public final String zzg() {
        return this.zzd;
    }
}
