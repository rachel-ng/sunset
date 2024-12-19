package com.google.android.gms.internal.ads;

import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzgqv {
    private final Class zza;
    private final Class zzb;

    /* synthetic */ zzgqv(Class cls, Class cls2, zzgqu zzgqu) {
        this.zza = cls;
        this.zzb = cls2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzgqv)) {
            return false;
        }
        zzgqv zzgqv = (zzgqv) obj;
        if (!zzgqv.zza.equals(this.zza) || !zzgqv.zzb.equals(this.zzb)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.zza, this.zzb});
    }

    public final String toString() {
        Class cls = this.zzb;
        String simpleName = this.zza.getSimpleName();
        String simpleName2 = cls.getSimpleName();
        return simpleName + " with serialization type: " + simpleName2;
    }
}
