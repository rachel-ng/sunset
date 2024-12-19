package com.google.android.gms.measurement.internal;

import com.google.android.exoplayer2.ExoPlayer;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzmm {
    final /* synthetic */ zzmh zza;
    private zzml zzb;

    zzmm(zzmh zzmh) {
        this.zza = zzmh;
    }

    /* access modifiers changed from: package-private */
    public final void zza(long j) {
        this.zzb = new zzml(this, this.zza.zzb().currentTimeMillis(), j);
        this.zza.zzc.postDelayed(this.zzb, ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS);
    }

    /* access modifiers changed from: package-private */
    public final void zza() {
        this.zza.zzt();
        if (this.zzb != null) {
            this.zza.zzc.removeCallbacks(this.zzb);
        }
        this.zza.zzk().zzn.zza(false);
        this.zza.zza(false);
    }
}
