package com.google.android.gms.measurement.internal;

import androidx.collection.LruCache;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzb;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
final class zzgz extends LruCache<String, zzb> {
    private final /* synthetic */ zzgt zza;

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object create(Object obj) {
        String str = (String) obj;
        Preconditions.checkNotEmpty(str);
        return zzgt.zza(this.zza, str);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzgz(zzgt zzgt, int i) {
        super(20);
        this.zza = zzgt;
    }
}
