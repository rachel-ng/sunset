package com.google.android.gms.internal.ads;

import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgks extends zzgii {
    private final zzgkr zza;

    private zzgks(zzgkr zzgkr) {
        this.zza = zzgkr;
    }

    public static zzgks zzc(zzgkr zzgkr) {
        return new zzgks(zzgkr);
    }

    public final boolean equals(Object obj) {
        return (obj instanceof zzgks) && ((zzgks) obj).zza == this.zza;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{zzgks.class, this.zza});
    }

    public final String toString() {
        String obj = this.zza.toString();
        return "ChaCha20Poly1305 Parameters (variant: " + obj + ")";
    }

    public final boolean zza() {
        return this.zza != zzgkr.zzc;
    }

    public final zzgkr zzb() {
        return this.zza;
    }
}
