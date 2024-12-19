package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzadc {
    public static final zzadc zza = new zzadc(-3, C.TIME_UNSET, -1);
    /* access modifiers changed from: private */
    public final int zzb;
    /* access modifiers changed from: private */
    public final long zzc;
    /* access modifiers changed from: private */
    public final long zzd;

    private zzadc(int i, long j, long j2) {
        this.zzb = i;
        this.zzc = j;
        this.zzd = j2;
    }

    public static zzadc zzd(long j, long j2) {
        return new zzadc(-1, j, j2);
    }

    public static zzadc zze(long j) {
        return new zzadc(0, C.TIME_UNSET, j);
    }

    public static zzadc zzf(long j, long j2) {
        return new zzadc(-2, j, j2);
    }
}
