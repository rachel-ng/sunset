package com.google.android.gms.internal.ads;

import com.google.android.gms.tasks.OnFailureListener;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final /* synthetic */ class zzfrt implements OnFailureListener {
    public final /* synthetic */ zzfrx zza;

    public /* synthetic */ zzfrt(zzfrx zzfrx) {
        this.zza = zzfrx;
    }

    public final void onFailure(Exception exc) {
        this.zza.zzf(exc);
    }
}
