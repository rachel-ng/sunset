package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdg;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzlu implements Runnable {
    private final /* synthetic */ String zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ zzo zzc;
    private final /* synthetic */ zzdg zzd;
    private final /* synthetic */ zzkx zze;

    zzlu(zzkx zzkx, String str, String str2, zzo zzo, zzdg zzdg) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = zzo;
        this.zzd = zzdg;
        this.zze = zzkx;
    }

    public final void run() {
        ArrayList<Bundle> arrayList = new ArrayList<>();
        try {
            zzfl zza2 = this.zze.zzb;
            if (zza2 == null) {
                this.zze.zzj().zzg().zza("Failed to get conditional properties; not connected to service", this.zza, this.zzb);
                return;
            }
            Preconditions.checkNotNull(this.zzc);
            arrayList = zznp.zzb(zza2.zza(this.zza, this.zzb, this.zzc));
            this.zze.zzaq();
            this.zze.zzq().zza(this.zzd, arrayList);
        } catch (RemoteException e) {
            this.zze.zzj().zzg().zza("Failed to get conditional properties; remote exception", this.zza, this.zzb, e);
        } finally {
            this.zze.zzq().zza(this.zzd, arrayList);
        }
    }
}
