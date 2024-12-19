package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzgeh;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzggi extends zzgeh.zzi implements Runnable {
    private final Runnable zza;

    public zzggi(Runnable runnable) {
        runnable.getClass();
        this.zza = runnable;
    }

    /* access modifiers changed from: protected */
    public final String zza() {
        String obj = this.zza.toString();
        return "task=[" + obj + "]";
    }

    public final void run() {
        try {
            this.zza.run();
        } catch (Throwable th) {
            zzd(th);
            throw th;
        }
    }
}
