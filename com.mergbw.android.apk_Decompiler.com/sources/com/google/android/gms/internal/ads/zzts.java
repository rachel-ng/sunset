package com.google.android.gms.internal.ads;

import android.media.metrics.LogSessionId;
import com.bumptech.glide.disklrucache.DiskLruCache$$ExternalSyntheticApiModelOutline0;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzts {
    public static void zza(zztk zztk, zzpj zzpj) {
        LogSessionId zza = zzpj.zza();
        if (!zza.equals(DiskLruCache$$ExternalSyntheticApiModelOutline0.m())) {
            zztk.zzb.setString("log-session-id", zza.getStringId());
        }
    }
}
