package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.Set;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzgcx extends zzfzd {
    final Iterator zza;
    final /* synthetic */ zzgcy zzb;

    zzgcx(zzgcy zzgcy) {
        this.zzb = zzgcy;
        this.zza = zzgcy.zza.iterator();
    }

    /* access modifiers changed from: protected */
    @CheckForNull
    public final Object zza() {
        while (this.zza.hasNext()) {
            Iterator it = this.zza;
            Set set = this.zzb.zzb;
            Object next = it.next();
            if (set.contains(next)) {
                return next;
            }
        }
        zzb();
        return null;
    }
}
