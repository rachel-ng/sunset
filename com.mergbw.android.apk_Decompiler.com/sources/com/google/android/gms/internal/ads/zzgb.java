package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.util.TimestampAdjuster;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgb {
    private long zza;
    private long zzb;
    private long zzc;
    private final ThreadLocal zzd = new ThreadLocal();

    public zzgb(long j) {
        zzi(0);
    }

    public static long zzg(long j) {
        return (j * 1000000) / 90000;
    }

    public static long zzh(long j) {
        return (j * 90000) / 1000000;
    }

    public final synchronized long zza(long j) {
        if (!zzj()) {
            long j2 = this.zza;
            if (j2 == TimestampAdjuster.MODE_SHARED) {
                Long l = (Long) this.zzd.get();
                if (l != null) {
                    j2 = l.longValue();
                } else {
                    throw null;
                }
            }
            this.zzb = j2 - j;
            notifyAll();
        }
        this.zzc = j;
        return j + this.zzb;
    }

    public final synchronized long zzb(long j) {
        if (j == C.TIME_UNSET) {
            return C.TIME_UNSET;
        }
        long j2 = this.zzc;
        if (j2 != C.TIME_UNSET) {
            long zzh = zzh(j2);
            long j3 = (4294967296L + zzh) / 8589934592L;
            long j4 = ((-1 + j3) * 8589934592L) + j;
            j += j3 * 8589934592L;
            if (Math.abs(j4 - zzh) < Math.abs(j - zzh)) {
                j = j4;
            }
        }
        return zza(zzg(j));
    }

    public final synchronized long zzc(long j) {
        if (j == C.TIME_UNSET) {
            return C.TIME_UNSET;
        }
        long j2 = this.zzc;
        if (j2 != C.TIME_UNSET) {
            long zzh = zzh(j2);
            long j3 = zzh / 8589934592L;
            Long.signum(j3);
            long j4 = (j3 * 8589934592L) + j;
            j += (j3 + 1) * 8589934592L;
            if (j4 >= zzh) {
                j = j4;
            }
        }
        return zza(zzg(j));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0019, code lost:
        return com.google.android.exoplayer2.C.TIME_UNSET;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized long zzd() {
        /*
            r4 = this;
            monitor-enter(r4)
            long r0 = r4.zza     // Catch:{ all -> 0x001f }
            r2 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 == 0) goto L_0x0018
            r2 = 9223372036854775806(0x7ffffffffffffffe, double:NaN)
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 != 0) goto L_0x0016
            goto L_0x0018
        L_0x0016:
            monitor-exit(r4)
            return r0
        L_0x0018:
            monitor-exit(r4)
            r0 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            return r0
        L_0x001f:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x001f }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzgb.zzd():long");
    }

    public final synchronized long zze() {
        long j;
        j = this.zzc;
        return j != C.TIME_UNSET ? j + this.zzb : zzd();
    }

    public final synchronized long zzf() {
        return this.zzb;
    }

    public final synchronized void zzi(long j) {
        this.zza = j;
        this.zzb = j == Long.MAX_VALUE ? 0 : -9223372036854775807L;
        this.zzc = C.TIME_UNSET;
    }

    public final synchronized boolean zzj() {
        return this.zzb != C.TIME_UNSET;
    }
}
