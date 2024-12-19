package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzvi extends zzdc {
    private final zzbu zzc;

    public zzvi(zzbu zzbu) {
        this.zzc = zzbu;
    }

    public final int zza(Object obj) {
        return obj == zzvh.zzd ? 0 : -1;
    }

    public final int zzb() {
        return 1;
    }

    public final int zzc() {
        return 1;
    }

    public final zzcz zzd(int i, zzcz zzcz, boolean z) {
        Object obj = null;
        Integer num = z ? 0 : null;
        if (z) {
            obj = zzvh.zzd;
        }
        zzcz.zzl(num, obj, 0, C.TIME_UNSET, 0, zzd.zza, true);
        return zzcz;
    }

    public final zzdb zze(int i, zzdb zzdb, long j) {
        zzdb zzdb2 = zzdb;
        zzdb.zza(zzdb.zza, this.zzc, (Object) null, C.TIME_UNSET, C.TIME_UNSET, C.TIME_UNSET, false, true, (zzbk) null, 0, C.TIME_UNSET, 0, 0, 0);
        zzdb zzdb3 = zzdb;
        zzdb3.zzm = true;
        return zzdb3;
    }

    public final Object zzf(int i) {
        return zzvh.zzd;
    }
}
