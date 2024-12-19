package com.google.android.gms.internal.ads;

import android.view.View;
import java.util.UUID;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public abstract class zzfoj {
    public static zzfoj zza(zzfok zzfok, zzfol zzfol) {
        zzfqd.zza();
        return new zzfon(zzfok, zzfol, UUID.randomUUID().toString());
    }

    public abstract void zzb(View view, zzfoq zzfoq, String str);

    public abstract void zzc();

    public abstract void zzd(View view);

    public abstract void zze();
}
