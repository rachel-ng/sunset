package com.google.android.gms.internal.ads;

import android.os.IBinder;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final /* synthetic */ class zzfwx implements IBinder.DeathRecipient {
    public final /* synthetic */ zzfxg zza;

    public /* synthetic */ zzfwx(zzfxg zzfxg) {
        this.zza = zzfxg;
    }

    public final void binderDied() {
        zzfxg.zzj(this.zza);
    }
}
