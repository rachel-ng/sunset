package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgwm extends zzhbo implements zzhdf {
    /* access modifiers changed from: private */
    public static final zzgwm zza;
    private static volatile zzhdm zzb;
    private String zzc = "";
    private zzhac zzd = zzhac.zzb;
    /* access modifiers changed from: private */
    public int zze;

    static {
        zzgwm zzgwm = new zzgwm();
        zza = zzgwm;
        zzhbo.zzca(zzgwm.class, zzgwm);
    }

    private zzgwm() {
    }

    public static zzgwl zza() {
        return (zzgwl) zza.zzaZ();
    }

    public static zzgwl zzc(zzgwm zzgwm) {
        return (zzgwl) zza.zzba(zzgwm);
    }

    public static zzgwm zze() {
        return zza;
    }

    public static zzgwm zzf(byte[] bArr, zzhay zzhay) throws zzhcd {
        return (zzgwm) zzhbo.zzbx(zza, bArr, zzhay);
    }

    static /* synthetic */ void zzj(zzgwm zzgwm, String str) {
        str.getClass();
        zzgwm.zzc = str;
    }

    static /* synthetic */ void zzk(zzgwm zzgwm, zzhac zzhac) {
        zzhac.getClass();
        zzgwm.zzd = zzhac;
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
                return zzbR(zza, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002\n\u0003\f", new Object[]{"zzc", "zzd", "zze"});
            case 3:
                return new zzgwm();
            case 4:
                return new zzgwl((zzgwk) null);
            case 5:
                return zza;
            case 6:
                zzhdm zzhdm = zzb;
                if (zzhdm == null) {
                    synchronized (zzgwm.class) {
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
        zzgxn zzb2 = zzgxn.zzb(this.zze);
        return zzb2 == null ? zzgxn.UNRECOGNIZED : zzb2;
    }

    public final zzhac zzh() {
        return this.zzd;
    }

    public final String zzi() {
        return this.zzc;
    }
}
