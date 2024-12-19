package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgwh extends zzhbo implements zzhdf {
    /* access modifiers changed from: private */
    public static final zzgwh zza;
    private static volatile zzhdm zzb;
    private String zzc = "";
    private zzhac zzd = zzhac.zzb;
    /* access modifiers changed from: private */
    public int zze;

    static {
        zzgwh zzgwh = new zzgwh();
        zza = zzgwh;
        zzhbo.zzca(zzgwh.class, zzgwh);
    }

    private zzgwh() {
    }

    public static zzgwe zza() {
        return (zzgwe) zza.zzaZ();
    }

    public static zzgwh zze() {
        return zza;
    }

    static /* synthetic */ void zzh(zzgwh zzgwh, String str) {
        str.getClass();
        zzgwh.zzc = str;
    }

    static /* synthetic */ void zzi(zzgwh zzgwh, zzhac zzhac) {
        zzhac.getClass();
        zzgwh.zzd = zzhac;
    }

    public final zzgwg zzc() {
        zzgwg zzb2 = zzgwg.zzb(this.zze);
        return zzb2 == null ? zzgwg.UNRECOGNIZED : zzb2;
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
                return new zzgwh();
            case 4:
                return new zzgwe((zzgwd) null);
            case 5:
                return zza;
            case 6:
                zzhdm zzhdm = zzb;
                if (zzhdm == null) {
                    synchronized (zzgwh.class) {
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

    public final String zzg() {
        return this.zzc;
    }
}
