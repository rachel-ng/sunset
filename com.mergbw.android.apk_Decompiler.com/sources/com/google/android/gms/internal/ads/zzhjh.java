package com.google.android.gms.internal.ads;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzhjh extends zzhbo implements zzhdf {
    /* access modifiers changed from: private */
    public static final zzhjh zza;
    private static volatile zzhdm zzb;
    private zzhca zzA = zzhbo.zzbK();
    private zzhca zzB = zzhbo.zzbK();
    private zzhjb zzC;
    private zzhca zzD = zzbK();
    private zzhgt zzE;
    private String zzF = "";
    private zzhgj zzG;
    private zzhca zzH = zzbK();
    private zzhhs zzI;
    private int zzJ;
    private zzhca zzK = zzbK();
    private zzhca zzL = zzbK();
    private long zzM;
    private zzhjg zzN;
    private zzhhz zzO;
    private byte zzP = 2;
    private int zzc;
    private int zzd;
    private int zze;
    private String zzf = "";
    private String zzg = "";
    private String zzh = "";
    private zzhgp zzi;
    private zzhca zzj = zzbK();
    private zzhca zzk = zzbK();
    private String zzl = "";
    private zzhij zzm;
    private boolean zzn;
    private zzhca zzo = zzhbo.zzbK();
    private String zzp = "";
    private boolean zzu;
    private boolean zzv;
    private zzhac zzw = zzhac.zzb;
    private zzhit zzx;
    private boolean zzy;
    private String zzz = "";

    static {
        zzhjh zzhjh = new zzhjh();
        zza = zzhjh;
        zzhbo.zzca(zzhjh.class, zzhjh);
    }

    private zzhjh() {
    }

    public static zzhgn zzc() {
        return (zzhgn) zza.zzaZ();
    }

    static /* synthetic */ void zzi(zzhjh zzhjh, zzhim zzhim) {
        zzhjh.zzd = zzhim.zza();
        zzhjh.zzc |= 1;
    }

    static /* synthetic */ void zzj(zzhjh zzhjh, String str) {
        str.getClass();
        zzhjh.zzc |= 4;
        zzhjh.zzf = str;
    }

    static /* synthetic */ void zzk(zzhjh zzhjh, String str) {
        str.getClass();
        zzhjh.zzc |= 8;
        zzhjh.zzg = str;
    }

    static /* synthetic */ void zzl(zzhjh zzhjh, zzhgp zzhgp) {
        zzhgp.getClass();
        zzhjh.zzi = zzhgp;
        zzhjh.zzc |= 32;
    }

    static /* synthetic */ void zzm(zzhjh zzhjh, zzhir zzhir) {
        zzhir.getClass();
        zzhca zzhca = zzhjh.zzj;
        if (!zzhca.zzc()) {
            zzhjh.zzj = zzhbo.zzbL(zzhca);
        }
        zzhjh.zzj.add(zzhir);
    }

    static /* synthetic */ void zzn(zzhjh zzhjh, String str) {
        zzhjh.zzc |= 64;
        zzhjh.zzl = str;
    }

    static /* synthetic */ void zzo(zzhjh zzhjh) {
        zzhjh.zzc &= -65;
        zzhjh.zzl = zza.zzl;
    }

    static /* synthetic */ void zzp(zzhjh zzhjh, zzhij zzhij) {
        zzhij.getClass();
        zzhjh.zzm = zzhij;
        zzhjh.zzc |= 128;
    }

    static /* synthetic */ void zzq(zzhjh zzhjh, zzhit zzhit) {
        zzhit.getClass();
        zzhjh.zzx = zzhit;
        zzhjh.zzc |= 8192;
    }

    static /* synthetic */ void zzr(zzhjh zzhjh, Iterable iterable) {
        zzhca zzhca = zzhjh.zzA;
        if (!zzhca.zzc()) {
            zzhjh.zzA = zzhbo.zzbL(zzhca);
        }
        zzgzi.zzaQ(iterable, zzhjh.zzA);
    }

    static /* synthetic */ void zzs(zzhjh zzhjh, Iterable iterable) {
        zzhca zzhca = zzhjh.zzB;
        if (!zzhca.zzc()) {
            zzhjh.zzB = zzhbo.zzbL(zzhca);
        }
        zzgzi.zzaQ(iterable, zzhjh.zzB);
    }

    /* access modifiers changed from: protected */
    public final Object zzde(zzhbn zzhbn, Object obj, Object obj2) {
        zzhbn zzhbn2 = zzhbn.GET_MEMOIZED_IS_INITIALIZED;
        byte b2 = 1;
        switch (zzhbn.ordinal()) {
            case 0:
                return Byte.valueOf(this.zzP);
            case 1:
                if (obj == null) {
                    b2 = 0;
                }
                this.zzP = b2;
                return null;
            case 2:
                return zzbR(zza, "\u0001\"\u0000\u0001\u0001\"\"\u0000\t\u0001\u0001ဈ\u0002\u0002ဈ\u0003\u0003ဈ\u0004\u0004Л\u0005ဇ\b\u0006\u001a\u0007ဈ\t\bဇ\n\tဇ\u000b\n᠌\u0000\u000b᠌\u0001\fဉ\u0005\rဈ\u0006\u000eဉ\u0007\u000fည\f\u0010\u001b\u0011ဉ\r\u0012ဇ\u000e\u0013ဈ\u000f\u0014\u001a\u0015\u001a\u0016ဉ\u0010\u0017\u001b\u0018ဉ\u0011\u0019ဈ\u0012\u001aဉ\u0013\u001b\u001b\u001cဉ\u0014\u001d᠌\u0015\u001e\u001b\u001f\u001b ဂ\u0016!ဉ\u0017\"ဉ\u0018", new Object[]{"zzc", "zzf", "zzg", "zzh", "zzj", zzhir.class, "zzn", "zzo", "zzp", "zzu", "zzv", "zzd", zzhil.zza, "zze", zzhgl.zza, "zzi", "zzl", "zzm", "zzw", "zzk", zzhjl.class, "zzx", "zzy", "zzz", "zzA", "zzB", "zzC", "zzD", zzhjv.class, "zzE", "zzF", "zzG", "zzH", zzhhb.class, "zzI", "zzJ", zzhiy.zza, "zzK", zzhhx.class, "zzL", zzhie.class, "zzM", "zzN", "zzO"});
            case 3:
                return new zzhjh();
            case 4:
                return new zzhgn((zzhfr) null);
            case 5:
                return zza;
            case 6:
                zzhdm zzhdm = zzb;
                if (zzhdm == null) {
                    synchronized (zzhjh.class) {
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
        return this.zzl;
    }

    public final String zzg() {
        return this.zzf;
    }

    public final List zzh() {
        return this.zzj;
    }
}
