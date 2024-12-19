package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdq;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@22.0.2 */
final class zzdt extends zzdq.zza {
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ zzdc zze;
    private final /* synthetic */ zzdq zzf;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdt(zzdq zzdq, String str, String str2, zzdc zzdc) {
        super(zzdq);
        this.zzc = str;
        this.zzd = str2;
        this.zze = zzdc;
        this.zzf = zzdq;
    }

    /* access modifiers changed from: protected */
    public final void zzb() {
        this.zze.zza((Bundle) null);
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        ((zzdb) Preconditions.checkNotNull(this.zzf.zzj)).getConditionalUserProperties(this.zzc, this.zzd, this.zze);
    }
}
