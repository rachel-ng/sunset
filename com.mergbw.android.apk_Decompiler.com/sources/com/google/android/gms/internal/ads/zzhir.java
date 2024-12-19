package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzhir extends zzhbo implements zzhdf {
    /* access modifiers changed from: private */
    public static final zzhir zza;
    private static volatile zzhdm zzb;
    private int zzc;
    private int zzd;
    private String zze = "";
    private zzhhh zzf;
    private zzhhl zzg;
    private int zzh;
    private zzhbw zzi = zzbG();
    private String zzj = "";
    private int zzk;
    private zzhca zzl = zzhbo.zzbK();
    private byte zzm = 2;

    static {
        zzhir zzhir = new zzhir();
        zza = zzhir;
        zzhbo.zzca(zzhir.class, zzhir);
    }

    private zzhir() {
    }

    public static zzhiq zze() {
        return (zzhiq) zza.zzaZ();
    }

    static /* synthetic */ void zzh(zzhir zzhir, int i) {
        zzhir.zzc |= 1;
        zzhir.zzd = i;
    }

    static /* synthetic */ void zzi(zzhir zzhir, String str) {
        str.getClass();
        zzhir.zzc |= 2;
        zzhir.zze = str;
    }

    static /* synthetic */ void zzj(zzhir zzhir, zzhhh zzhhh) {
        zzhhh.getClass();
        zzhir.zzf = zzhhh;
        zzhir.zzc |= 4;
    }

    static /* synthetic */ void zzk(zzhir zzhir, zzhip zzhip) {
        zzhir.zzk = zzhip.zza();
        zzhir.zzc |= 64;
    }

    static /* synthetic */ void zzl(zzhir zzhir, String str) {
        str.getClass();
        zzhca zzhca = zzhir.zzl;
        if (!zzhca.zzc()) {
            zzhir.zzl = zzhbo.zzbL(zzhca);
        }
        zzhir.zzl.add(str);
    }

    public final int zzc() {
        return this.zzl.size();
    }

    /* access modifiers changed from: protected */
    public final Object zzde(zzhbn zzhbn, Object obj, Object obj2) {
        zzhbn zzhbn2 = zzhbn.GET_MEMOIZED_IS_INITIALIZED;
        byte b2 = 1;
        switch (zzhbn.ordinal()) {
            case 0:
                return Byte.valueOf(this.zzm);
            case 1:
                if (obj == null) {
                    b2 = 0;
                }
                this.zzm = b2;
                return null;
            case 2:
                return zzbR(zza, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0002\u0003\u0001ᔄ\u0000\u0002ဈ\u0001\u0003ᐉ\u0002\u0004ᐉ\u0003\u0005င\u0004\u0006\u0016\u0007ဈ\u0005\b᠌\u0006\t\u001a", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", zzhio.zza, "zzl"});
            case 3:
                return new zzhir();
            case 4:
                return new zzhiq((zzhfr) null);
            case 5:
                return zza;
            case 6:
                zzhdm zzhdm = zzb;
                if (zzhdm == null) {
                    synchronized (zzhir.class) {
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
        return this.zze;
    }
}
