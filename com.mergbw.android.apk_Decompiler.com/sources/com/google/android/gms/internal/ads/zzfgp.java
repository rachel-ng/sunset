package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zzby;
import com.google.android.gms.ads.internal.util.client.zzm;
import com.google.android.gms.ads.rewarded.OnAdMetadataChangedListener;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfgp implements OnAdMetadataChangedListener {
    final /* synthetic */ zzby zza;
    final /* synthetic */ zzfgq zzb;

    zzfgp(zzfgq zzfgq, zzby zzby) {
        this.zza = zzby;
        this.zzb = zzfgq;
    }

    public final void onAdMetadataChanged() {
        if (this.zzb.zzd != null) {
            try {
                this.zza.zze();
            } catch (RemoteException e) {
                zzm.zzl("#007 Could not call remote method.", e);
            }
        }
    }
}
