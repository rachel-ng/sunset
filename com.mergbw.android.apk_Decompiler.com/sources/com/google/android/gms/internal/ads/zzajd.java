package com.google.android.gms.internal.ads;

import android.util.Pair;
import com.google.android.exoplayer2.C;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzajd implements zzaji {
    private final long[] zza;
    private final long[] zzb;
    private final long zzc;

    private zzajd(long[] jArr, long[] jArr2, long j) {
        this.zza = jArr;
        this.zzb = jArr2;
        this.zzc = j == C.TIME_UNSET ? zzgd.zzr(jArr2[jArr2.length - 1]) : j;
    }

    public static zzajd zzb(long j, zzahv zzahv, long j2) {
        int length = zzahv.zzd.length;
        int i = length + 1;
        long[] jArr = new long[i];
        long[] jArr2 = new long[i];
        jArr[0] = j;
        long j3 = 0;
        jArr2[0] = 0;
        for (int i2 = 1; i2 <= length; i2++) {
            int i3 = i2 - 1;
            j += (long) (zzahv.zzb + zzahv.zzd[i3]);
            j3 += (long) (zzahv.zzc + zzahv.zze[i3]);
            jArr[i2] = j;
            jArr2[i2] = j3;
        }
        return new zzajd(jArr, jArr2, j2);
    }

    private static Pair zzf(long j, long[] jArr, long[] jArr2) {
        int zzc2 = zzgd.zzc(jArr, j, true, true);
        long j2 = jArr[zzc2];
        long j3 = jArr2[zzc2];
        int i = zzc2 + 1;
        if (i == jArr.length) {
            return Pair.create(Long.valueOf(j2), Long.valueOf(j3));
        }
        long j4 = jArr[i];
        return Pair.create(Long.valueOf(j), Long.valueOf(((long) ((j4 == j2 ? 0.0d : (((double) j) - ((double) j2)) / ((double) (j4 - j2))) * ((double) (jArr2[i] - j3)))) + j3));
    }

    public final long zza() {
        return this.zzc;
    }

    public final int zzc() {
        return C.RATE_UNSET_INT;
    }

    public final long zzd() {
        return -1;
    }

    public final long zze(long j) {
        return zzgd.zzr(((Long) zzf(j, this.zza, this.zzb).second).longValue());
    }

    public final zzaer zzg(long j) {
        Pair zzf = zzf(zzgd.zzu(Math.max(0, Math.min(j, this.zzc))), this.zzb, this.zza);
        zzaeu zzaeu = new zzaeu(zzgd.zzr(((Long) zzf.first).longValue()), ((Long) zzf.second).longValue());
        return new zzaer(zzaeu, zzaeu);
    }

    public final boolean zzh() {
        return true;
    }
}
