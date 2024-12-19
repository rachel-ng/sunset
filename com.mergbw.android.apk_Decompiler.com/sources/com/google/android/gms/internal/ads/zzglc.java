package com.google.android.gms.internal.ads;

import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzglc extends zzgii {
    private final String zza;
    private final zzglb zzb;

    private zzglc(String str, zzglb zzglb) {
        this.zza = str;
        this.zzb = zzglb;
    }

    public static zzglc zzc(String str, zzglb zzglb) {
        return new zzglc(str, zzglb);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzglc)) {
            return false;
        }
        zzglc zzglc = (zzglc) obj;
        if (!zzglc.zza.equals(this.zza) || !zzglc.zzb.equals(this.zzb)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{zzglc.class, this.zza, this.zzb});
    }

    public final String toString() {
        String obj = this.zzb.toString();
        return "LegacyKmsAead Parameters (keyUri: " + this.zza + ", variant: " + obj + ")";
    }

    public final boolean zza() {
        return this.zzb != zzglb.zzb;
    }

    public final zzglb zzb() {
        return this.zzb;
    }

    public final String zzd() {
        return this.zza;
    }
}
