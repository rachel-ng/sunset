package com.google.android.gms.internal.ads;

import java.io.Serializable;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzgay extends zzfzw implements Serializable {
    final Object zza;
    final Object zzb;

    zzgay(Object obj, Object obj2) {
        this.zza = obj;
        this.zzb = obj2;
    }

    public final Object getKey() {
        return this.zza;
    }

    public final Object getValue() {
        return this.zzb;
    }

    public final Object setValue(Object obj) {
        throw new UnsupportedOperationException();
    }
}
