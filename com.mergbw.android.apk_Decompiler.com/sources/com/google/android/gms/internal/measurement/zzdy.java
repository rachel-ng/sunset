package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdq;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@22.0.2 */
final class zzdy extends zzdq.zza {
    private final /* synthetic */ Boolean zzc;
    private final /* synthetic */ zzdq zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdy(zzdq zzdq, Boolean bool) {
        super(zzdq);
        this.zzc = bool;
        this.zzd = zzdq;
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        if (this.zzc != null) {
            ((zzdb) Preconditions.checkNotNull(this.zzd.zzj)).setMeasurementEnabled(this.zzc.booleanValue(), this.zza);
        } else {
            ((zzdb) Preconditions.checkNotNull(this.zzd.zzj)).clearMeasurementEnabled(this.zza);
        }
    }
}
