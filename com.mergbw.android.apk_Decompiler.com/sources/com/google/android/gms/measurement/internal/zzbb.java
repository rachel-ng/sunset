package com.google.android.gms.measurement.internal;

import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzbb implements Iterator<String> {
    private Iterator<String> zza;
    private final /* synthetic */ zzbc zzb;

    public final /* synthetic */ Object next() {
        return this.zza.next();
    }

    zzbb(zzbc zzbc) {
        this.zzb = zzbc;
        this.zza = zzbc.zza.keySet().iterator();
    }

    public final void remove() {
        throw new UnsupportedOperationException("Remove not supported");
    }

    public final boolean hasNext() {
        return this.zza.hasNext();
    }
}
