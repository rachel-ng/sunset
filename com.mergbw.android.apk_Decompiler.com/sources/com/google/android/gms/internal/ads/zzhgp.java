package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzhgp extends zzhbo implements zzhdf {
    /* access modifiers changed from: private */
    public static final zzhgp zza;
    private static volatile zzhdm zzb;
    private int zzc;
    private String zzd = "";

    static {
        zzhgp zzhgp = new zzhgp();
        zza = zzhgp;
        zzhbo.zzca(zzhgp.class, zzhgp);
    }

    private zzhgp() {
    }

    public static zzhgo zzc() {
        return (zzhgo) zza.zzaZ();
    }

    static /* synthetic */ void zzf(zzhgp zzhgp, String str) {
        zzhgp.zzc |= 1;
        zzhgp.zzd = str;
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
                return zzbR(zza, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဈ\u0000", new Object[]{"zzc", "zzd"});
            case 3:
                return new zzhgp();
            case 4:
                return new zzhgo((zzhfr) null);
            case 5:
                return zza;
            case 6:
                zzhdm zzhdm = zzb;
                if (zzhdm == null) {
                    synchronized (zzhgp.class) {
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
}
