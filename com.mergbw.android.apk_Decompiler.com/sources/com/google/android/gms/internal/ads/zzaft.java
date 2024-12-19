package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.extractor.avi.AviExtractor;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzaft implements zzafj {
    public final String zza;

    private zzaft(String str) {
        this.zza = str;
    }

    public static zzaft zzb(zzfu zzfu) {
        return new zzaft(zzfu.zzA(zzfu.zzb(), zzfxs.zzc));
    }

    public final int zza() {
        return AviExtractor.FOURCC_strn;
    }
}
