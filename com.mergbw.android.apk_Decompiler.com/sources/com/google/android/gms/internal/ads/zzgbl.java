package com.google.android.gms.internal.ads;

import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzgbl extends zzgdi {
    private Object zza;

    zzgbl(Object obj) {
        this.zza = obj;
    }

    public final boolean hasNext() {
        return this.zza != this;
    }

    public final Object next() {
        Object obj = this.zza;
        this.zza = this;
        if (obj != this) {
            return obj;
        }
        throw new NoSuchElementException();
    }
}
