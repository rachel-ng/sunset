package com.google.android.gms.internal.ads;

import android.os.SystemClock;
import com.google.android.exoplayer2.C;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzja {
    private final long zza;
    private final long zzb;
    private long zzc = C.TIME_UNSET;
    private long zzd = C.TIME_UNSET;
    private long zze = C.TIME_UNSET;
    private long zzf = C.TIME_UNSET;
    private long zzg = C.TIME_UNSET;
    private long zzh = C.TIME_UNSET;
    private float zzi = 1.03f;
    private float zzj = 0.97f;
    private float zzk = 1.0f;
    private long zzl = C.TIME_UNSET;
    private long zzm = C.TIME_UNSET;
    private long zzn = C.TIME_UNSET;

    /* synthetic */ zzja(float f, float f2, long j, float f3, long j2, long j3, float f4, zziz zziz) {
        this.zza = j2;
        this.zzb = j3;
    }

    private static long zzf(long j, long j2, float f) {
        return (long) ((((float) j) * 0.999f) + (((float) j2) * 9.999871E-4f));
    }

    private final void zzg() {
        long j;
        long j2 = this.zzc;
        if (j2 != C.TIME_UNSET) {
            j = this.zzd;
            if (j == C.TIME_UNSET) {
                long j3 = this.zzf;
                if (j3 != C.TIME_UNSET && j2 < j3) {
                    j2 = j3;
                }
                j = this.zzg;
                if (j == C.TIME_UNSET || j2 <= j) {
                    j = j2;
                }
            }
        } else {
            j = -9223372036854775807L;
        }
        if (this.zze != j) {
            this.zze = j;
            this.zzh = j;
            this.zzm = C.TIME_UNSET;
            this.zzn = C.TIME_UNSET;
            this.zzl = C.TIME_UNSET;
        }
    }

    public final long zzb() {
        return this.zzh;
    }

    public final void zzc() {
        long j = this.zzh;
        if (j != C.TIME_UNSET) {
            long j2 = j + this.zzb;
            this.zzh = j2;
            long j3 = this.zzg;
            if (j3 != C.TIME_UNSET && j2 > j3) {
                this.zzh = j3;
            }
            this.zzl = C.TIME_UNSET;
        }
    }

    public final void zzd(zzbk zzbk) {
        long j = zzbk.zzc;
        this.zzc = zzgd.zzr(C.TIME_UNSET);
        long j2 = zzbk.zzd;
        this.zzf = zzgd.zzr(C.TIME_UNSET);
        long j3 = zzbk.zze;
        this.zzg = zzgd.zzr(C.TIME_UNSET);
        float f = zzbk.zzf;
        this.zzj = 0.97f;
        float f2 = zzbk.zzg;
        this.zzi = 1.03f;
        zzg();
    }

    public final void zze(long j) {
        this.zzd = j;
        zzg();
    }

    public final float zza(long j, long j2) {
        long j3;
        if (this.zzc == C.TIME_UNSET) {
            return 1.0f;
        }
        long j4 = j - j2;
        long j5 = this.zzm;
        if (j5 == C.TIME_UNSET) {
            this.zzm = j4;
            this.zzn = 0;
        } else {
            long max = Math.max(j4, zzf(j5, j4, 0.999f));
            this.zzm = max;
            this.zzn = zzf(this.zzn, Math.abs(j4 - max), 0.999f);
        }
        if (this.zzl != C.TIME_UNSET && SystemClock.elapsedRealtime() - this.zzl < 1000) {
            return this.zzk;
        }
        this.zzl = SystemClock.elapsedRealtime();
        long j6 = this.zzm + (this.zzn * 3);
        if (this.zzh > j6) {
            long zzr = zzgd.zzr(1000);
            float f = (float) zzr;
            long[] jArr = {j6, this.zze, this.zzh - (((long) ((this.zzk - 4.0f) * f)) + ((long) ((this.zzi - 4.0f) * f)))};
            j3 = jArr[0];
            for (int i = 1; i < 3; i++) {
                long j7 = jArr[i];
                if (j7 > j3) {
                    j3 = j7;
                }
            }
            this.zzh = j3;
        } else {
            long max2 = Math.max(this.zzh, Math.min(j - ((long) (Math.max(0.0f, this.zzk - 4.0f) / 1.0E-7f)), j6));
            this.zzh = max2;
            long j8 = this.zzg;
            if (j8 == C.TIME_UNSET || max2 <= j8) {
                j3 = max2;
            } else {
                this.zzh = j8;
                j3 = j8;
            }
        }
        long j9 = j - j3;
        if (Math.abs(j9) < this.zza) {
            this.zzk = 1.0f;
            return 1.0f;
        }
        float max3 = Math.max(this.zzj, Math.min((((float) j9) * 1.0E-7f) + 1.0f, this.zzi));
        this.zzk = max3;
        return max3;
    }
}
