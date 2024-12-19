package com.google.android.gms.internal.ads;

import android.media.AudioAttributes;
import android.media.AudioFormat;
import com.google.android.exoplayer2.drm.FrameworkMediaDrm$$ExternalSyntheticApiModelOutline0;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzrc {
    public static zzqa zza(AudioFormat audioFormat, AudioAttributes audioAttributes, boolean z) {
        int m = FrameworkMediaDrm$$ExternalSyntheticApiModelOutline0.m(audioFormat, audioAttributes);
        if (m == 0) {
            return zzqa.zza;
        }
        zzpy zzpy = new zzpy();
        boolean z2 = false;
        if (zzgd.zza > 32 && m == 2) {
            z2 = true;
        }
        zzpy.zza(true);
        zzpy.zzb(z2);
        zzpy.zzc(z);
        return zzpy.zzd();
    }
}
