package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdq;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@22.0.2 */
final class zzex extends zzdq.zza {
    private final /* synthetic */ Long zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ String zze;
    private final /* synthetic */ Bundle zzf;
    private final /* synthetic */ boolean zzg;
    private final /* synthetic */ boolean zzh;
    private final /* synthetic */ zzdq zzi;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzex(zzdq zzdq, Long l, String str, String str2, Bundle bundle, boolean z, boolean z2) {
        super(zzdq);
        this.zzc = l;
        this.zzd = str;
        this.zze = str2;
        this.zzf = bundle;
        this.zzg = z;
        this.zzh = z2;
        this.zzi = zzdq;
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        Long l = this.zzc;
        ((zzdb) Preconditions.checkNotNull(this.zzi.zzj)).logEvent(this.zzd, this.zze, this.zzf, this.zzg, this.zzh, l == null ? this.zza : l.longValue());
    }
}
