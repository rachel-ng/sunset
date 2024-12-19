package com.google.android.gms.measurement.internal;

import com.google.android.exoplayer2.ExoPlayer;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzjr extends zzat {
    private final /* synthetic */ zziv zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzjr(zziv zziv, zzil zzil) {
        super(zzil);
        this.zza = zziv;
    }

    public final void zzb() {
        if (this.zza.zzu.zzah()) {
            this.zza.zzp.zza(ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS);
        }
    }
}
