package com.google.android.gms.internal.ads;

import java.util.Iterator;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzgbj extends zzfzd {
    final /* synthetic */ Iterator zza;
    final /* synthetic */ zzfyh zzb;

    zzgbj(Iterator it, zzfyh zzfyh) {
        this.zza = it;
        this.zzb = zzfyh;
    }

    /* access modifiers changed from: protected */
    @CheckForNull
    public final Object zza() {
        while (this.zza.hasNext()) {
            Iterator it = this.zza;
            zzfyh zzfyh = this.zzb;
            Object next = it.next();
            if (zzfyh.zza(next)) {
                return next;
            }
        }
        zzb();
        return null;
    }
}
