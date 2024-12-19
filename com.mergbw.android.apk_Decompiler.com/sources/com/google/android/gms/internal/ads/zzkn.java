package com.google.android.gms.internal.ads;

import android.content.Context;
import com.bumptech.glide.disklrucache.DiskLruCache$$ExternalSyntheticApiModelOutline0;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzkn {
    public static zzpj zza(Context context, zzkw zzkw, boolean z, String str) {
        zzpf zzb = zzpf.zzb(context);
        if (zzb == null) {
            zzfk.zzf("ExoPlayerImpl", "MediaMetricsService unavailable.");
            return new zzpj(DiskLruCache$$ExternalSyntheticApiModelOutline0.m(), str);
        }
        if (z) {
            zzkw.zzz(zzb);
        }
        return new zzpj(zzb.zza(), str);
    }
}
