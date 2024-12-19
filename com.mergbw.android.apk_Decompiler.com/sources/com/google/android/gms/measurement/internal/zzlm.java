package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzlm implements Runnable {
    private final /* synthetic */ zzo zza;
    private final /* synthetic */ Bundle zzb;
    private final /* synthetic */ zzkx zzc;

    zzlm(zzkx zzkx, zzo zzo, Bundle bundle) {
        this.zza = zzo;
        this.zzb = bundle;
        this.zzc = zzkx;
    }

    public final void run() {
        zzfl zza2 = this.zzc.zzb;
        if (zza2 == null) {
            this.zzc.zzj().zzg().zza("Failed to send default event parameters to service");
            return;
        }
        try {
            Preconditions.checkNotNull(this.zza);
            zza2.zza(this.zzb, this.zza);
        } catch (RemoteException e) {
            this.zzc.zzj().zzg().zza("Failed to send default event parameters to service", e);
        }
    }
}
