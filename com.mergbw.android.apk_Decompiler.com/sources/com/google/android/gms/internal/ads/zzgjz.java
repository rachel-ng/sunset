package com.google.android.gms.internal.ads;

import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgjz extends zzgii {
    private final int zza;
    private final int zzb = 12;
    private final int zzc = 16;
    private final zzgjx zzd;

    /* synthetic */ zzgjz(int i, int i2, int i3, zzgjx zzgjx, zzgjy zzgjy) {
        this.zza = i;
        this.zzd = zzgjx;
    }

    public static zzgjw zzc() {
        return new zzgjw((zzgjv) null);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzgjz)) {
            return false;
        }
        zzgjz zzgjz = (zzgjz) obj;
        if (zzgjz.zza == this.zza) {
            int i = zzgjz.zzb;
            int i2 = zzgjz.zzc;
            if (zzgjz.zzd == this.zzd) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{zzgjz.class, Integer.valueOf(this.zza), 12, 16, this.zzd});
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzd);
        return "AesGcm Parameters (variant: " + valueOf + ", 12-byte IV, 16-byte tag, and " + this.zza + "-byte key)";
    }

    public final boolean zza() {
        return this.zzd != zzgjx.zzc;
    }

    public final int zzb() {
        return this.zza;
    }

    public final zzgjx zzd() {
        return this.zzd;
    }
}
