package com.google.android.gms.internal.ads;

import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgtp {
    private final zzghk zza;
    private final int zzb;
    private final String zzc;
    private final String zzd;

    /* synthetic */ zzgtp(zzghk zzghk, int i, String str, String str2, zzgto zzgto) {
        this.zza = zzghk;
        this.zzb = i;
        this.zzc = str;
        this.zzd = str2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzgtp)) {
            return false;
        }
        zzgtp zzgtp = (zzgtp) obj;
        if (this.zza != zzgtp.zza || this.zzb != zzgtp.zzb || !this.zzc.equals(zzgtp.zzc) || !this.zzd.equals(zzgtp.zzd)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.zza, Integer.valueOf(this.zzb), this.zzc, this.zzd});
    }

    public final String toString() {
        return String.format("(status=%s, keyId=%s, keyType='%s', keyPrefix='%s')", new Object[]{this.zza, Integer.valueOf(this.zzb), this.zzc, this.zzd});
    }

    public final int zza() {
        return this.zzb;
    }
}
