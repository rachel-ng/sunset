package com.google.android.gms.internal.ads;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzggn implements Runnable {
    @CheckForNull
    zzggq zza;

    zzggn(zzggq zzggq) {
        this.zza = zzggq;
    }

    public final void run() {
        ListenableFuture zze;
        String str;
        zzggq zzggq = this.zza;
        if (zzggq != null && (zze = zzggq.zza) != null) {
            this.zza = null;
            if (zze.isDone()) {
                zzggq.zzs(zze);
                return;
            }
            try {
                ScheduledFuture zzv = zzggq.zzb;
                zzggq.zzb = null;
                str = "Timed out";
                if (zzv != null) {
                    long abs = Math.abs(zzv.getDelay(TimeUnit.MILLISECONDS));
                    if (abs > 10) {
                        str = "Timed out (timeout delayed by " + abs + " ms after scheduled time)";
                    }
                }
                zzggq.zzd(new zzggp(str + ": " + zze.toString(), (zzggo) null));
                zze.cancel(true);
            } catch (Throwable th) {
                zze.cancel(true);
                throw th;
            }
        }
    }
}
