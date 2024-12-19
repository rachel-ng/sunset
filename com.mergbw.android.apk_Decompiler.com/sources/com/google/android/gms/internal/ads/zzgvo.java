package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgvo extends zzhbo implements zzhdf {
    /* access modifiers changed from: private */
    public static final zzgvo zza;
    private static volatile zzhdm zzb;
    private int zzc;
    /* access modifiers changed from: private */
    public zzhac zzd = zzhac.zzb;

    static {
        zzgvo zzgvo = new zzgvo();
        zza = zzgvo;
        zzhbo.zzca(zzgvo.class, zzgvo);
    }

    private zzgvo() {
    }

    public static zzgvn zzc() {
        return (zzgvn) zza.zzaZ();
    }

    public static zzgvo zze(zzhac zzhac, zzhay zzhay) throws zzhcd {
        return (zzgvo) zzhbo.zzbr(zza, zzhac, zzhay);
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
                return zzbR(zza, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\n", new Object[]{"zzc", "zzd"});
            case 3:
                return new zzgvo();
            case 4:
                return new zzgvn((zzgvm) null);
            case 5:
                return zza;
            case 6:
                zzhdm zzhdm = zzb;
                if (zzhdm == null) {
                    synchronized (zzgvo.class) {
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
