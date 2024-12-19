package com.google.android.gms.internal.ads;

@Deprecated
/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgwp extends zzhbo implements zzhdf {
    /* access modifiers changed from: private */
    public static final zzgwp zza;
    private static volatile zzhdm zzb;
    private String zzc = "";
    private String zzd = "";
    private int zze;
    private boolean zzf;
    private String zzg = "";

    static {
        zzgwp zzgwp = new zzgwp();
        zza = zzgwp;
        zzhbo.zzca(zzgwp.class, zzgwp);
    }

    private zzgwp() {
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
                return zzbR(zza, "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003\u000b\u0004\u0007\u0005Ȉ", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg"});
            case 3:
                return new zzgwp();
            case 4:
                return new zzgwo((zzgwn) null);
            case 5:
                return zza;
            case 6:
                zzhdm zzhdm = zzb;
                if (zzhdm == null) {
                    synchronized (zzgwp.class) {
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
