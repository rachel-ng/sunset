package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.measurement.zzdq;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@22.0.2 */
final class zzfe extends zzdq.zza {
    private final /* synthetic */ Activity zzc;
    private final /* synthetic */ zzdc zzd;
    private final /* synthetic */ zzdq.zzd zze;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzfe(zzdq.zzd zzd2, Activity activity, zzdc zzdc) {
        super(zzdq.this);
        this.zzc = activity;
        this.zzd = zzdc;
        this.zze = zzd2;
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        ((zzdb) Preconditions.checkNotNull(zzdq.this.zzj)).onActivitySaveInstanceState(ObjectWrapper.wrap(this.zzc), this.zzd, this.zzb);
    }
}
