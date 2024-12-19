package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.extractor.ts.PsExtractor;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzaju implements zzajr {
    private final zzfu zza;
    private final int zzb;
    private final int zzc;
    private int zzd;
    private int zze;

    public zzaju(zzajn zzajn) {
        zzfu zzfu = zzajn.zza;
        this.zza = zzfu;
        zzfu.zzK(12);
        this.zzc = zzfu.zzp() & 255;
        this.zzb = zzfu.zzp();
    }

    public final int zza() {
        return -1;
    }

    public final int zzb() {
        return this.zzb;
    }

    public final int zzc() {
        int i = this.zzc;
        if (i == 8) {
            return this.zza.zzm();
        }
        if (i == 16) {
            return this.zza.zzq();
        }
        int i2 = this.zzd;
        this.zzd = i2 + 1;
        if (i2 % 2 != 0) {
            return this.zze & 15;
        }
        int zzm = this.zza.zzm();
        this.zze = zzm;
        return (zzm & PsExtractor.VIDEO_STREAM_MASK) >> 4;
    }
}
