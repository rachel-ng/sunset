package com.google.android.gms.internal.ads;

import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgmh extends zzgii {
    private final zzgmg zza;

    private zzgmh(zzgmg zzgmg) {
        this.zza = zzgmg;
    }

    public static zzgmh zzc(zzgmg zzgmg) {
        return new zzgmh(zzgmg);
    }

    public final boolean equals(Object obj) {
        return (obj instanceof zzgmh) && ((zzgmh) obj).zza == this.zza;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{zzgmh.class, this.zza});
    }

    public final String toString() {
        String obj = this.zza.toString();
        return "XChaCha20Poly1305 Parameters (variant: " + obj + ")";
    }

    public final boolean zza() {
        return this.zza != zzgmg.zzc;
    }

    public final zzgmg zzb() {
        return this.zza;
    }
}
