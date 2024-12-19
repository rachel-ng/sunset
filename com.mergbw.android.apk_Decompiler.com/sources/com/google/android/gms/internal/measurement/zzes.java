package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdq;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@22.0.2 */
final class zzes extends zzdq.zza {
    private final /* synthetic */ zzdc zzc;
    private final /* synthetic */ int zzd;
    private final /* synthetic */ zzdq zze;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzes(zzdq zzdq, zzdc zzdc, int i) {
        super(zzdq);
        this.zzc = zzdc;
        this.zzd = i;
        this.zze = zzdq;
    }

    /* access modifiers changed from: protected */
    public final void zzb() {
        this.zzc.zza((Bundle) null);
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        ((zzdb) Preconditions.checkNotNull(this.zze.zzj)).getTestFlag(this.zzc, this.zzd);
    }
}
