package com.google.android.gms.internal.consent_sdk;

import java.util.NoSuchElementException;

/* compiled from: com.google.android.ump:user-messaging-platform@@2.2.0 */
final class zzdf extends zzdj {
    private static final Object zza = new Object();
    private Object zzb;

    zzdf(Object obj) {
        this.zzb = obj;
    }

    public final boolean hasNext() {
        return this.zzb != zza;
    }

    public final Object next() {
        Object obj = this.zzb;
        Object obj2 = zza;
        if (obj != obj2) {
            this.zzb = obj2;
            return obj;
        }
        throw new NoSuchElementException();
    }
}
