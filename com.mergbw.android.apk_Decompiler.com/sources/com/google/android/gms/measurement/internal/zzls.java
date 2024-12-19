package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzls implements Runnable {
    private final /* synthetic */ boolean zza = true;
    private final /* synthetic */ zzo zzb;
    private final /* synthetic */ boolean zzc;
    private final /* synthetic */ zzae zzd;
    private final /* synthetic */ zzae zze;
    private final /* synthetic */ zzkx zzf;

    zzls(zzkx zzkx, boolean z, zzo zzo, boolean z2, zzae zzae, zzae zzae2) {
        this.zzb = zzo;
        this.zzc = z2;
        this.zzd = zzae;
        this.zze = zzae2;
        this.zzf = zzkx;
    }

    public final void run() {
        zzfl zza2 = this.zzf.zzb;
        if (zza2 == null) {
            this.zzf.zzj().zzg().zza("Discarding data. Failed to send conditional user property to service");
            return;
        }
        if (this.zza) {
            Preconditions.checkNotNull(this.zzb);
            this.zzf.zza(zza2, (AbstractSafeParcelable) this.zzc ? null : this.zzd, this.zzb);
        } else {
            try {
                if (TextUtils.isEmpty(this.zze.zza)) {
                    Preconditions.checkNotNull(this.zzb);
                    zza2.zza(this.zzd, this.zzb);
                } else {
                    zza2.zza(this.zzd);
                }
            } catch (RemoteException e) {
                this.zzf.zzj().zzg().zza("Failed to send conditional user property to the service", e);
            }
        }
        this.zzf.zzaq();
    }
}
