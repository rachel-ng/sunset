package com.google.android.gms.internal.ads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzgfq implements Runnable {
    final Future zza;
    final zzgfp zzb;

    zzgfq(Future future, zzgfp zzgfp) {
        this.zza = future;
        this.zzb = zzgfp;
    }

    public final void run() {
        Throwable zza2;
        Future future = this.zza;
        if (!(future instanceof zzggw) || (zza2 = zzggx.zza((zzggw) future)) == null) {
            try {
                this.zzb.zzb(zzgft.zzp(this.zza));
            } catch (ExecutionException e) {
                this.zzb.zza(e.getCause());
            } catch (Throwable th) {
                this.zzb.zza(th);
            }
        } else {
            this.zzb.zza(zza2);
        }
    }

    public final String toString() {
        zzfxy zza2 = zzfxz.zza(this);
        zza2.zza(this.zzb);
        return zza2.toString();
    }
}
