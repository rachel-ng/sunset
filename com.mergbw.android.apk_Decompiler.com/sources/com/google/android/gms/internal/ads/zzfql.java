package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfql implements Runnable {
    zzfql() {
    }

    public final void run() {
        if (zzfqo.zzc != null) {
            zzfqo.zzc.post(zzfqo.zzd);
            zzfqo.zzc.postDelayed(zzfqo.zze, 200);
        }
    }
}
