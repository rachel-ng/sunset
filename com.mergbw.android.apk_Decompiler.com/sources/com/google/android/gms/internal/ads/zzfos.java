package com.google.android.gms.internal.ads;

import java.util.Timer;
import java.util.TimerTask;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfos extends TimerTask {
    final /* synthetic */ Timer zza;
    final /* synthetic */ zzfou zzb;
    final /* synthetic */ zzchs zzc;

    zzfos(zzfou zzfou, zzchs zzchs, Timer timer) {
        this.zzc = zzchs;
        this.zza = timer;
        this.zzb = zzfou;
    }

    public final void run() {
        this.zzb.zzg();
        this.zzc.zza(true);
        this.zza.cancel();
    }
}
