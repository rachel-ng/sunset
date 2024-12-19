package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
final class zzlq extends zzlx {
    private final /* synthetic */ zzlm zza;

    public final Iterator<Map.Entry<K, V>> iterator() {
        return new zzlo(this.zza);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private zzlq(zzlm zzlm) {
        super(zzlm);
        this.zza = zzlm;
    }
}
