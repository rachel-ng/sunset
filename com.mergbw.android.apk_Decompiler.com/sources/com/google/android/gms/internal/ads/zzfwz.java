package com.google.android.gms.internal.ads;

import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfwz extends zzfww {
    final /* synthetic */ TaskCompletionSource zza;
    final /* synthetic */ zzfww zzb;
    final /* synthetic */ zzfxg zzc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzfwz(zzfxg zzfxg, TaskCompletionSource taskCompletionSource, TaskCompletionSource taskCompletionSource2, zzfww zzfww) {
        super(taskCompletionSource);
        this.zza = taskCompletionSource2;
        this.zzb = zzfww;
        this.zzc = zzfxg;
    }

    public final void zza() {
        synchronized (this.zzc.zzg) {
            zzfxg.zzn(this.zzc, this.zza);
            if (this.zzc.zzl.getAndIncrement() > 0) {
                this.zzc.zzc.zzc("Already connected to the service.", new Object[0]);
            }
            zzfxg.zzp(this.zzc, this.zzb);
        }
    }
}
