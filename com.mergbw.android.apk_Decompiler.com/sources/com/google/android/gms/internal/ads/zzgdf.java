package com.google.android.gms.internal.ads;

import java.util.Iterator;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzgdf extends zzgbh {
    final transient Object zza;

    zzgdf(Object obj) {
        obj.getClass();
        this.zza = obj;
    }

    public final boolean contains(@CheckForNull Object obj) {
        return this.zza.equals(obj);
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final /* synthetic */ Iterator iterator() {
        return new zzgbl(this.zza);
    }

    public final int size() {
        return 1;
    }

    public final String toString() {
        String obj = this.zza.toString();
        return "[" + obj + "]";
    }

    /* access modifiers changed from: package-private */
    public final int zza(Object[] objArr, int i) {
        objArr[i] = this.zza;
        return i + 1;
    }

    public final zzgbc zzd() {
        return zzgbc.zzn(this.zza);
    }

    public final zzgdi zze() {
        return new zzgbl(this.zza);
    }

    /* access modifiers changed from: package-private */
    public final boolean zzf() {
        return false;
    }
}
