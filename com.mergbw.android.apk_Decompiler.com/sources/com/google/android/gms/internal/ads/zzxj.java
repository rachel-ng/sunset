package com.google.android.gms.internal.ads;

import android.net.Uri;
import com.google.android.exoplayer2.C;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzxj extends zzdc {
    private static final Object zzc = new Object();
    private static final zzbu zzd;
    private final long zze;
    private final long zzf;
    private final boolean zzg;
    private final zzbu zzh;
    private final zzbk zzi;

    static {
        zzaw zzaw = new zzaw();
        zzaw.zza("SinglePeriodTimeline");
        zzaw.zzb(Uri.EMPTY);
        zzd = zzaw.zzc();
    }

    public zzxj(long j, long j2, long j3, long j4, long j5, long j6, long j7, boolean z, boolean z2, boolean z3, Object obj, zzbu zzbu, zzbk zzbk) {
        this.zze = j4;
        this.zzf = j5;
        this.zzg = z;
        zzbu.getClass();
        this.zzh = zzbu;
        this.zzi = zzbk;
    }

    public final int zza(Object obj) {
        return zzc.equals(obj) ? 0 : -1;
    }

    public final int zzb() {
        return 1;
    }

    public final int zzc() {
        return 1;
    }

    public final zzcz zzd(int i, zzcz zzcz, boolean z) {
        zzeq.zza(i, 0, 1);
        zzcz.zzl((Object) null, z ? zzc : null, 0, this.zze, 0, zzd.zza, false);
        return zzcz;
    }

    public final zzdb zze(int i, zzdb zzdb, long j) {
        zzeq.zza(i, 0, 1);
        zzbk zzbk = this.zzi;
        zzdb.zza(zzdb.zza, this.zzh, (Object) null, C.TIME_UNSET, C.TIME_UNSET, C.TIME_UNSET, this.zzg, false, zzbk, 0, this.zzf, 0, 0, 0);
        return zzdb;
    }

    public final Object zzf(int i) {
        zzeq.zza(i, 0, 1);
        return zzc;
    }
}
