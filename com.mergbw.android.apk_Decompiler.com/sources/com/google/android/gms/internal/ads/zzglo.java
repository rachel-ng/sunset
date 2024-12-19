package com.google.android.gms.internal.ads;

import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzglo extends zzgii {
    private final zzglm zza;
    private final String zzb;
    private final zzgll zzc;
    private final zzgii zzd;

    /* synthetic */ zzglo(zzglm zzglm, String str, zzgll zzgll, zzgii zzgii, zzgln zzgln) {
        this.zza = zzglm;
        this.zzb = str;
        this.zzc = zzgll;
        this.zzd = zzgii;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzglo)) {
            return false;
        }
        zzglo zzglo = (zzglo) obj;
        if (!zzglo.zzc.equals(this.zzc) || !zzglo.zzd.equals(this.zzd) || !zzglo.zzb.equals(this.zzb) || !zzglo.zza.equals(this.zza)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{zzglo.class, this.zzb, this.zzc, this.zzd, this.zza});
    }

    public final String toString() {
        zzglm zzglm = this.zza;
        zzgii zzgii = this.zzd;
        String valueOf = String.valueOf(this.zzc);
        String valueOf2 = String.valueOf(zzgii);
        String valueOf3 = String.valueOf(zzglm);
        return "LegacyKmsEnvelopeAead Parameters (kekUri: " + this.zzb + ", dekParsingStrategy: " + valueOf + ", dekParametersForNewKeys: " + valueOf2 + ", variant: " + valueOf3 + ")";
    }

    public final boolean zza() {
        return this.zza != zzglm.zzb;
    }

    public final zzgii zzb() {
        return this.zzd;
    }

    public final zzglm zzc() {
        return this.zza;
    }

    public final String zzd() {
        return this.zzb;
    }
}
