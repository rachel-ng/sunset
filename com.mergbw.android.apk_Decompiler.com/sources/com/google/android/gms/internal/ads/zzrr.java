package com.google.android.gms.internal.ads;

import android.media.AudioRouting;
import android.media.AudioRouting$OnRoutingChangedListener;
import android.media.AudioTrack;
import android.os.Handler;
import android.os.Looper;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzrr {
    private final AudioTrack zza;
    private final zzpw zzb;
    private AudioRouting$OnRoutingChangedListener zzc = new zzrq(this);

    public zzrr(AudioTrack audioTrack, zzpw zzpw) {
        this.zza = audioTrack;
        this.zzb = zzpw;
        audioTrack.addOnRoutingChangedListener(this.zzc, new Handler(Looper.myLooper()));
    }

    /* access modifiers changed from: private */
    public void zzc(AudioRouting audioRouting) {
        if (this.zzc != null && audioRouting.getRoutedDevice() != null) {
            this.zzb.zzh(audioRouting.getRoutedDevice());
        }
    }

    public void zzb() {
        AudioRouting$OnRoutingChangedListener audioRouting$OnRoutingChangedListener = this.zzc;
        audioRouting$OnRoutingChangedListener.getClass();
        this.zza.removeOnRoutingChangedListener(audioRouting$OnRoutingChangedListener);
        this.zzc = null;
    }
}
