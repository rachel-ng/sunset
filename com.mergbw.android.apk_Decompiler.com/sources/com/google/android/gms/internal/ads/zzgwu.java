package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgwu extends zzhbo implements zzhdf {
    /* access modifiers changed from: private */
    public static final zzgwu zza;
    private static volatile zzhdm zzb;
    /* access modifiers changed from: private */
    public int zzc;
    private zzhca zzd = zzbK();

    static {
        zzgwu zzgwu = new zzgwu();
        zza = zzgwu;
        zzhbo.zzca(zzgwu.class, zzgwu);
    }

    private zzgwu() {
    }

    public static zzgwr zzd() {
        return (zzgwr) zza.zzaZ();
    }

    public static zzgwu zzg(InputStream inputStream, zzhay zzhay) throws IOException {
        return (zzgwu) zzhbo.zzbu(zza, inputStream, zzhay);
    }

    static /* synthetic */ void zzj(zzgwu zzgwu, zzgwt zzgwt) {
        zzgwt.getClass();
        zzhca zzhca = zzgwu.zzd;
        if (!zzhca.zzc()) {
            zzgwu.zzd = zzhbo.zzbL(zzhca);
        }
        zzgwu.zzd.add(zzgwt);
    }

    public final int zza() {
        return this.zzd.size();
    }

    public final int zzc() {
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
                return zzbR(zza, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u000b\u0002\u001b", new Object[]{"zzc", "zzd", zzgwt.class});
            case 3:
                return new zzgwu();
            case 4:
                return new zzgwr((zzgwq) null);
            case 5:
                return zza;
            case 6:
                zzhdm zzhdm = zzb;
                if (zzhdm == null) {
                    synchronized (zzgwu.class) {
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

    public final zzgwt zze(int i) {
        return (zzgwt) this.zzd.get(i);
    }

    public final List zzh() {
        return this.zzd;
    }
}
