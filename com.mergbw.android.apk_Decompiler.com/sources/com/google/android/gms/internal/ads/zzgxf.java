package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgxf extends zzhbo implements zzhdf {
    /* access modifiers changed from: private */
    public static final zzgxf zza;
    private static volatile zzhdm zzb;
    private String zzc = "";

    static {
        zzgxf zzgxf = new zzgxf();
        zza = zzgxf;
        zzhbo.zzca(zzgxf.class, zzgxf);
    }

    private zzgxf() {
    }

    public static zzgxe zza() {
        return (zzgxe) zza.zzaZ();
    }

    public static zzgxf zzd() {
        return zza;
    }

    public static zzgxf zze(zzhac zzhac, zzhay zzhay) throws zzhcd {
        return (zzgxf) zzhbo.zzbr(zza, zzhac, zzhay);
    }

    static /* synthetic */ void zzg(zzgxf zzgxf, String str) {
        str.getClass();
        zzgxf.zzc = str;
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
                return zzbR(zza, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001Ȉ", new Object[]{"zzc"});
            case 3:
                return new zzgxf();
            case 4:
                return new zzgxe((zzgxd) null);
            case 5:
                return zza;
            case 6:
                zzhdm zzhdm = zzb;
                if (zzhdm == null) {
                    synchronized (zzgxf.class) {
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

    public final String zzf() {
        return this.zzc;
    }
}
