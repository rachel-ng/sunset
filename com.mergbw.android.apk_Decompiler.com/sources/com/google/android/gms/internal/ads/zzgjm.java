package com.google.android.gms.internal.ads;

import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgjm extends zzgii {
    private final int zza;
    private final int zzb;
    private final int zzc = 16;
    private final zzgjk zzd;

    /* synthetic */ zzgjm(int i, int i2, int i3, zzgjk zzgjk, zzgjl zzgjl) {
        this.zza = i;
        this.zzb = i2;
        this.zzd = zzgjk;
    }

    public static zzgjj zzd() {
        return new zzgjj((zzgji) null);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzgjm)) {
            return false;
        }
        zzgjm zzgjm = (zzgjm) obj;
        if (zzgjm.zza == this.zza && zzgjm.zzb == this.zzb) {
            int i = zzgjm.zzc;
            if (zzgjm.zzd == this.zzd) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{zzgjm.class, Integer.valueOf(this.zza), Integer.valueOf(this.zzb), 16, this.zzd});
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzd);
        return "AesEax Parameters (variant: " + valueOf + ", " + this.zzb + "-byte IV, 16-byte tag, and " + this.zza + "-byte key)";
    }

    public final boolean zza() {
        return this.zzd != zzgjk.zzc;
    }

    public final int zzb() {
        return this.zzb;
    }

    public final int zzc() {
        return this.zza;
    }

    public final zzgjk zze() {
        return this.zzd;
    }
}
