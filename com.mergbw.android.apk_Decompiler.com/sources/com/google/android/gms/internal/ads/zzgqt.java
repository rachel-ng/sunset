package com.google.android.gms.internal.ads;

import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzgqt {
    private final Class zza;
    private final zzgze zzb;

    /* synthetic */ zzgqt(Class cls, zzgze zzgze, zzgqs zzgqs) {
        this.zza = cls;
        this.zzb = zzgze;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzgqt)) {
            return false;
        }
        zzgqt zzgqt = (zzgqt) obj;
        if (!zzgqt.zza.equals(this.zza) || !zzgqt.zzb.equals(this.zzb)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.zza, this.zzb});
    }

    public final String toString() {
        zzgze zzgze = this.zzb;
        String simpleName = this.zza.getSimpleName();
        String valueOf = String.valueOf(zzgze);
        return simpleName + ", object identifier: " + valueOf;
    }
}
