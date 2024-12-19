package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdq;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@22.0.2 */
final class zzej extends zzdq.zza {
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ boolean zze;
    private final /* synthetic */ zzdc zzf;
    private final /* synthetic */ zzdq zzg;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzej(zzdq zzdq, String str, String str2, boolean z, zzdc zzdc) {
        super(zzdq);
        this.zzc = str;
        this.zzd = str2;
        this.zze = z;
        this.zzf = zzdc;
        this.zzg = zzdq;
    }

    /* access modifiers changed from: protected */
    public final void zzb() {
        this.zzf.zza((Bundle) null);
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        ((zzdb) Preconditions.checkNotNull(this.zzg.zzj)).getUserProperties(this.zzc, this.zzd, this.zze, this.zzf);
    }
}
