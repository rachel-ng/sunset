package com.google.android.gms.internal.ads;

import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgkm extends zzgii {
    private final int zza;
    private final zzgkk zzb;

    /* synthetic */ zzgkm(int i, zzgkk zzgkk, zzgkl zzgkl) {
        this.zza = i;
        this.zzb = zzgkk;
    }

    public static zzgkj zzc() {
        return new zzgkj((zzgki) null);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzgkm)) {
            return false;
        }
        zzgkm zzgkm = (zzgkm) obj;
        return zzgkm.zza == this.zza && zzgkm.zzb == this.zzb;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{zzgkm.class, Integer.valueOf(this.zza), this.zzb});
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzb);
        return "AesGcmSiv Parameters (variant: " + valueOf + ", " + this.zza + "-byte key)";
    }

    public final boolean zza() {
        return this.zzb != zzgkk.zzc;
    }

    public final int zzb() {
        return this.zza;
    }

    public final zzgkk zzd() {
        return this.zzb;
    }
}
