package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgue extends zzhbo implements zzhdf {
    /* access modifiers changed from: private */
    public static final zzgue zza;
    private static volatile zzhdm zzb;
    private int zzc;
    private int zzd;
    private zzguk zze;
    private zzgvw zzf;

    static {
        zzgue zzgue = new zzgue();
        zza = zzgue;
        zzhbo.zzca(zzgue.class, zzgue);
    }

    private zzgue() {
    }

    public static zzgud zzc() {
        return (zzgud) zza.zzaZ();
    }

    public static zzgue zze(zzhac zzhac, zzhay zzhay) throws zzhcd {
        return (zzgue) zzhbo.zzbr(zza, zzhac, zzhay);
    }

    public static zzhdm zzh() {
        return zza.zzbM();
    }

    static /* synthetic */ void zzi(zzgue zzgue, zzguk zzguk) {
        zzguk.getClass();
        zzgue.zze = zzguk;
        zzgue.zzc |= 1;
    }

    static /* synthetic */ void zzj(zzgue zzgue, zzgvw zzgvw) {
        zzgvw.getClass();
        zzgue.zzf = zzgvw;
        zzgue.zzc |= 2;
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
                return zzbR(zza, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000\u0003ဉ\u0001", new Object[]{"zzc", "zzd", "zze", "zzf"});
            case 3:
                return new zzgue();
            case 4:
                return new zzgud((zzguc) null);
            case 5:
                return zza;
            case 6:
                zzhdm zzhdm = zzb;
                if (zzhdm == null) {
                    synchronized (zzgue.class) {
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

    public final zzguk zzf() {
        zzguk zzguk = this.zze;
        return zzguk == null ? zzguk.zze() : zzguk;
    }

    public final zzgvw zzg() {
        zzgvw zzgvw = this.zzf;
        return zzgvw == null ? zzgvw.zze() : zzgvw;
    }
}
