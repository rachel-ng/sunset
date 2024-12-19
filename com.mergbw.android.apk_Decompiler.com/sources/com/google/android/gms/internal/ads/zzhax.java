package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
final class zzhax {
    private final Object zza;
    private final int zzb;

    zzhax(Object obj, int i) {
        this.zza = obj;
        this.zzb = i;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzhax)) {
            return false;
        }
        zzhax zzhax = (zzhax) obj;
        if (this.zza == zzhax.zza && this.zzb == zzhax.zzb) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return (System.identityHashCode(this.zza) * 65535) + this.zzb;
    }
}
