package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdq;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@22.0.2 */
final class zzdz extends zzdq.zza {
    private final /* synthetic */ zzdq zzc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdz(zzdq zzdq) {
        super(zzdq);
        this.zzc = zzdq;
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        ((zzdb) Preconditions.checkNotNull(this.zzc.zzj)).resetAnalyticsData(this.zza);
    }
}
