package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
final class zznf implements zznr {
    final /* synthetic */ zznc zza;

    zznf(zznc zznc) {
        this.zza = zznc;
    }

    public final void zza(String str, String str2, Bundle bundle) {
        if (!TextUtils.isEmpty(str)) {
            this.zza.zzl().zzb((Runnable) new zzni(this, str, str2, bundle));
        } else if (this.zza.zzm != null) {
            this.zza.zzm.zzj().zzg().zza("AppId not known when logging event", str2);
        }
    }
}
