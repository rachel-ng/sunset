package com.google.android.gms.internal.ads;

import android.media.AudioTrack;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzri {
    public static void zza(AudioTrack audioTrack, zzpx zzpx) {
        audioTrack.setPreferredDevice(zzpx == null ? null : zzpx.zza);
    }
}
