package com.google.android.gms.internal.ads;

import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzgqc {
    private final Class zza;
    private final Class zzb;

    /* synthetic */ zzgqc(Class cls, Class cls2, zzgqb zzgqb) {
        this.zza = cls;
        this.zzb = cls2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzgqc)) {
            return false;
        }
        zzgqc zzgqc = (zzgqc) obj;
        if (!zzgqc.zza.equals(this.zza) || !zzgqc.zzb.equals(this.zzb)) {
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
        return simpleName + " with primitive type: " + simpleName2;
    }
}
