package com.google.android.gms.internal.ads;

import java.util.Iterator;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzgcr extends zzgbh {
    private final transient zzgbf zza;
    private final transient zzgbc zzb;

    zzgcr(zzgbf zzgbf, zzgbc zzgbc) {
        this.zza = zzgbf;
        this.zzb = zzgbc;
    }

    public final boolean contains(@CheckForNull Object obj) {
        return this.zza.get(obj) != null;
    }

    public final /* synthetic */ Iterator iterator() {
        return this.zzb.listIterator(0);
    }

    public final int size() {
        return this.zza.size();
    }

    /* access modifiers changed from: package-private */
    public final int zza(Object[] objArr, int i) {
        return this.zzb.zza(objArr, i);
    }

    public final zzgbc zzd() {
        return this.zzb;
    }

    public final zzgdi zze() {
        return this.zzb.listIterator(0);
    }

    /* access modifiers changed from: package-private */
    public final boolean zzf() {
        return true;
    }
}
