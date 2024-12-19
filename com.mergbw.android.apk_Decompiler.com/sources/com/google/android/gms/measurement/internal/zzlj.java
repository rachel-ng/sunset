package com.google.android.gms.measurement.internal;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzlj implements Runnable {
    private final /* synthetic */ zzkp zza;
    private final /* synthetic */ zzkx zzb;

    zzlj(zzkx zzkx, zzkp zzkp) {
        this.zza = zzkp;
        this.zzb = zzkx;
    }

    public final void run() {
        zzfl zza2 = this.zzb.zzb;
        if (zza2 == null) {
            this.zzb.zzj().zzg().zza("Failed to send current screen to service");
            return;
        }
        try {
            zzkp zzkp = this.zza;
            if (zzkp == null) {
                zza2.zza(0, (String) null, (String) null, this.zzb.zza().getPackageName());
            } else {
                zza2.zza(zzkp.zzc, this.zza.zza, this.zza.zzb, this.zzb.zza().getPackageName());
            }
            this.zzb.zzaq();
        } catch (RemoteException e) {
            this.zzb.zzj().zzg().zza("Failed to send current screen to the service", e);
        }
    }
}
