package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdq;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@22.0.2 */
final class zzer extends zzdq.zza {
    private final /* synthetic */ boolean zzc;
    private final /* synthetic */ zzdq zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzer(zzdq zzdq, boolean z) {
        super(zzdq);
        this.zzc = z;
        this.zzd = zzdq;
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        ((zzdb) Preconditions.checkNotNull(this.zzd.zzj)).setDataCollectionEnabled(this.zzc);
    }
}
