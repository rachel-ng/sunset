package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfzg extends zzgbv {
    final /* synthetic */ zzfzi zza;

    zzfzg(zzfzi zzfzi) {
        this.zza = zzfzi;
    }

    public final boolean contains(@CheckForNull Object obj) {
        return zzgab.zza(this.zza.zza.entrySet(), obj);
    }

    public final Iterator iterator() {
        return new zzfzh(this.zza);
    }

    public final boolean remove(@CheckForNull Object obj) {
        if (!contains(obj)) {
            return false;
        }
        zzfzi zzfzi = this.zza;
        zzfzv.zzo(zzfzi.zzb, ((Map.Entry) Objects.requireNonNull((Map.Entry) obj)).getKey());
        return true;
    }

    /* access modifiers changed from: package-private */
    public final Map zza() {
        return this.zza;
    }
}
