package com.google.android.gms.internal.ads;

import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgsg extends zzgsj {
    private final int zza;
    private final int zzb;
    private final zzgse zzc;
    private final zzgsd zzd;

    /* synthetic */ zzgsg(int i, int i2, zzgse zzgse, zzgsd zzgsd, zzgsf zzgsf) {
        this.zza = i;
        this.zzb = i2;
        this.zzc = zzgse;
        this.zzd = zzgsd;
    }

    public static zzgsc zze() {
        return new zzgsc((zzgsb) null);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzgsg)) {
            return false;
        }
        zzgsg zzgsg = (zzgsg) obj;
        if (zzgsg.zza == this.zza && zzgsg.zzd() == zzd() && zzgsg.zzc == this.zzc && zzgsg.zzd == this.zzd) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{zzgsg.class, Integer.valueOf(this.zza), Integer.valueOf(this.zzb), this.zzc, this.zzd});
    }

    public final String toString() {
        zzgsd zzgsd = this.zzd;
        String valueOf = String.valueOf(this.zzc);
        String valueOf2 = String.valueOf(zzgsd);
        return "HMAC Parameters (variant: " + valueOf + ", hashType: " + valueOf2 + ", " + this.zzb + "-byte tags, and " + this.zza + "-byte key)";
    }

    public final boolean zza() {
        return this.zzc != zzgse.zzd;
    }

    public final int zzb() {
        return this.zzb;
    }

    public final int zzc() {
        return this.zza;
    }

    public final int zzd() {
        zzgse zzgse = this.zzc;
        if (zzgse == zzgse.zzd) {
            return this.zzb;
        }
        if (zzgse == zzgse.zza || zzgse == zzgse.zzb || zzgse == zzgse.zzc) {
            return this.zzb + 5;
        }
        throw new IllegalStateException("Unknown variant");
    }

    public final zzgsd zzf() {
        return this.zzd;
    }

    public final zzgse zzg() {
        return this.zzc;
    }
}
