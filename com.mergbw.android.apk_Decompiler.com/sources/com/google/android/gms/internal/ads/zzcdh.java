package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzcdh implements Runnable {
    final /* synthetic */ zzcdi zza;

    zzcdh(zzcdi zzcdi) {
        this.zza = zzcdi;
    }

    public final void run() {
        zzcdi zzcdi = this.zza;
        if (zzcdi.zzq != null) {
            zzcdi.zzq.zzd();
        }
    }
}
