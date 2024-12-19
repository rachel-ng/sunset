package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfwm extends zzfvp {
    final /* synthetic */ zzfwn zza;
    private final zzfws zzb;

    zzfwm(zzfwn zzfwn, zzfws zzfws) {
        this.zza = zzfwn;
        this.zzb = zzfws;
    }

    public final void zzb(Bundle bundle) {
        int i = bundle.getInt("statusCode", 8150);
        String string = bundle.getString("sessionToken");
        zzfwq zzc = zzfwr.zzc();
        zzc.zzb(i);
        if (string != null) {
            zzc.zza(string);
        }
        this.zzb.zza(zzc.zzc());
        if (i == 8157) {
            this.zza.zzc();
        }
    }
}
