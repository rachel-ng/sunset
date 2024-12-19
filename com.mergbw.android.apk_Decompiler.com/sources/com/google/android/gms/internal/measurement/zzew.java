package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdq;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@22.0.2 */
final class zzew extends zzdq.zza {
    private final /* synthetic */ zzdq.zzb zzc;
    private final /* synthetic */ zzdq zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzew(zzdq zzdq, zzdq.zzb zzb) {
        super(zzdq);
        this.zzc = zzb;
        this.zzd = zzdq;
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        ((zzdb) Preconditions.checkNotNull(this.zzd.zzj)).registerOnMeasurementEventListener(this.zzc);
    }
}
