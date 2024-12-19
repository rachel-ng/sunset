package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzdg;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzlo implements Runnable {
    private final /* synthetic */ zzbd zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ zzdg zzc;
    private final /* synthetic */ zzkx zzd;

    zzlo(zzkx zzkx, zzbd zzbd, String str, zzdg zzdg) {
        this.zza = zzbd;
        this.zzb = str;
        this.zzc = zzdg;
        this.zzd = zzkx;
    }

    public final void run() {
        byte[] bArr = null;
        try {
            zzfl zza2 = this.zzd.zzb;
            if (zza2 == null) {
                this.zzd.zzj().zzg().zza("Discarding data. Failed to send event to service to bundle");
                return;
            }
            bArr = zza2.zza(this.zza, this.zzb);
            this.zzd.zzaq();
            this.zzd.zzq().zza(this.zzc, bArr);
        } catch (RemoteException e) {
            this.zzd.zzj().zzg().zza("Failed to send event to the service to bundle", e);
        } finally {
            this.zzd.zzq().zza(this.zzc, bArr);
        }
    }
}
