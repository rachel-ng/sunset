package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zzdd;
import com.google.android.gms.ads.internal.util.client.zzm;
import com.google.android.gms.ads.rewarded.OnAdMetadataChangedListener;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfgi implements OnAdMetadataChangedListener {
    final /* synthetic */ zzdd zza;
    final /* synthetic */ zzfgk zzb;

    zzfgi(zzfgk zzfgk, zzdd zzdd) {
        this.zza = zzdd;
        this.zzb = zzfgk;
    }

    public final void onAdMetadataChanged() {
        if (this.zzb.zzi != null) {
            try {
                this.zza.zze();
            } catch (RemoteException e) {
                zzm.zzl("#007 Could not call remote method.", e);
            }
        }
    }
}
