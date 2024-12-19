package com.google.android.gms.internal.ads;

import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgrn extends zzgsj {
    private final int zza;
    private final int zzb;
    private final zzgrl zzc;

    /* synthetic */ zzgrn(int i, int i2, zzgrl zzgrl, zzgrm zzgrm) {
        this.zza = i;
        this.zzb = i2;
        this.zzc = zzgrl;
    }

    public static zzgrk zze() {
        return new zzgrk((zzgrj) null);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzgrn)) {
            return false;
        }
        zzgrn zzgrn = (zzgrn) obj;
        if (zzgrn.zza == this.zza && zzgrn.zzd() == zzd() && zzgrn.zzc == this.zzc) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{zzgrn.class, Integer.valueOf(this.zza), Integer.valueOf(this.zzb), this.zzc});
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzc);
        return "AES-CMAC Parameters (variant: " + valueOf + ", " + this.zzb + "-byte tags, and " + this.zza + "-byte key)";
    }

    public final boolean zza() {
        return this.zzc != zzgrl.zzd;
    }

    public final int zzb() {
        return this.zzb;
    }

    public final int zzc() {
        return this.zza;
    }

    public final int zzd() {
        zzgrl zzgrl = this.zzc;
        if (zzgrl == zzgrl.zzd) {
            return this.zzb;
        }
        if (zzgrl == zzgrl.zza || zzgrl == zzgrl.zzb || zzgrl == zzgrl.zzc) {
            return this.zzb + 5;
        }
        throw new IllegalStateException("Unknown variant");
    }

    public final zzgrl zzf() {
        return this.zzc;
    }
}
