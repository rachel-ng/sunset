package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.HashMap;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgti {
    private HashMap zza = new HashMap();

    public final zzgtk zza() {
        if (this.zza != null) {
            zzgtk zzgtk = new zzgtk(Collections.unmodifiableMap(this.zza), (zzgtj) null);
            this.zza = null;
            return zzgtk;
        }
        throw new IllegalStateException("cannot call build() twice");
    }
}
