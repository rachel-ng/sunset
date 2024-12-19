package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.HashMap;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfjq {
    private final HashMap zza = new HashMap();

    public final zzfjp zza(zzfjg zzfjg, Context context, zzfiy zzfiy, zzfjw zzfjw) {
        zzfjp zzfjp = (zzfjp) this.zza.get(zzfjg);
        if (zzfjp != null) {
            return zzfjp;
        }
        zzfjd zzfjd = new zzfjd(zzfjj.zza(zzfjg, context));
        zzfjp zzfjp2 = new zzfjp(zzfjd, new zzfjy(zzfjd, zzfiy, zzfjw));
        this.zza.put(zzfjg, zzfjp2);
        return zzfjp2;
    }
}
