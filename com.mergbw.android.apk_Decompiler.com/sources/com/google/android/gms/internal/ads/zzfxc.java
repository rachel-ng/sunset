package com.google.android.gms.internal.ads;

import android.os.IBinder;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfxc extends zzfww {
    final /* synthetic */ IBinder zza;
    final /* synthetic */ zzfxf zzb;

    zzfxc(zzfxf zzfxf, IBinder iBinder) {
        this.zza = iBinder;
        this.zzb = zzfxf;
    }

    public final void zza() {
        this.zzb.zza.zzn = zzfvn.zzb(this.zza);
        zzfxg.zzq(this.zzb.zza);
        this.zzb.zza.zzh = false;
        for (Runnable run : this.zzb.zza.zze) {
            run.run();
        }
        this.zzb.zza.zze.clear();
    }
}
