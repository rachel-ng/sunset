package com.google.android.gms.measurement.internal;

import android.os.Handler;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzcz;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
abstract class zzat {
    private static volatile Handler zza;
    private final zzil zzb;
    private final Runnable zzc;
    /* access modifiers changed from: private */
    public volatile long zzd;

    private final Handler zzd() {
        Handler handler;
        if (zza != null) {
            return zza;
        }
        synchronized (zzat.class) {
            if (zza == null) {
                zza = new zzcz(this.zzb.zza().getMainLooper());
            }
            handler = zza;
        }
        return handler;
    }

    public abstract void zzb();

    zzat(zzil zzil) {
        Preconditions.checkNotNull(zzil);
        this.zzb = zzil;
        this.zzc = new zzaw(this, zzil);
    }

    /* access modifiers changed from: package-private */
    public final void zza() {
        this.zzd = 0;
        zzd().removeCallbacks(this.zzc);
    }

    public final void zza(long j) {
        zza();
        if (j >= 0) {
            this.zzd = this.zzb.zzb().currentTimeMillis();
            if (!zzd().postDelayed(this.zzc, j)) {
                this.zzb.zzj().zzg().zza("Failed to schedule delayed post. time", Long.valueOf(j));
            }
        }
    }

    public final boolean zzc() {
        return this.zzd != 0;
    }
}
