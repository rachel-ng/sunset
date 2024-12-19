package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzle implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ zzo zzb;
    private final /* synthetic */ boolean zzc;
    private final /* synthetic */ zzkx zzd;

    zzle(zzkx zzkx, AtomicReference atomicReference, zzo zzo, boolean z) {
        this.zza = atomicReference;
        this.zzb = zzo;
        this.zzc = z;
        this.zzd = zzkx;
    }

    public final void run() {
        synchronized (this.zza) {
            try {
                zzfl zza2 = this.zzd.zzb;
                if (zza2 == null) {
                    this.zzd.zzj().zzg().zza("Failed to get all user properties; not connected to service");
                    this.zza.notify();
                    return;
                }
                Preconditions.checkNotNull(this.zzb);
                this.zza.set(zza2.zza(this.zzb, this.zzc));
                this.zzd.zzaq();
                this.zza.notify();
            } catch (RemoteException e) {
                try {
                    this.zzd.zzj().zzg().zza("Failed to get all user properties; remote exception", e);
                    this.zza.notify();
                } catch (Throwable th) {
                    this.zza.notify();
                    throw th;
                }
            }
        }
    }
}
