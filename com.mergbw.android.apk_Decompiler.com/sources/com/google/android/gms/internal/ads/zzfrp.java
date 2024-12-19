package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Looper;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfrp {
    private final Context zza;
    private final Looper zzb;

    public zzfrp(Context context, Looper looper) {
        this.zza = context;
        this.zzb = looper;
    }

    public final void zza(String str) {
        zzfsf zza2 = zzfsj.zza();
        zza2.zza(this.zza.getPackageName());
        zza2.zzc(zzfsi.BLOCKED_IMPRESSION);
        zzfsc zza3 = zzfsd.zza();
        zza3.zzb(str);
        zza3.zza(zzfsb.BLOCKED_REASON_BACKGROUND);
        zza2.zzb(zza3);
        new zzfrq(this.zza, this.zzb, (zzfsj) zza2.zzbr()).zza();
    }
}
