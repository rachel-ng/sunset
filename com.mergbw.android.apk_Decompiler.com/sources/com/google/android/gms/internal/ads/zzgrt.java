package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgrt {
    @Nullable
    private zzgsg zza = null;
    @Nullable
    private zzgzf zzb = null;
    @Nullable
    private Integer zzc = null;

    private zzgrt() {
    }

    /* synthetic */ zzgrt(zzgrs zzgrs) {
    }

    public final zzgrt zza(@Nullable Integer num) {
        this.zzc = num;
        return this;
    }

    public final zzgrt zzb(zzgzf zzgzf) {
        this.zzb = zzgzf;
        return this;
    }

    public final zzgrt zzc(zzgsg zzgsg) {
        this.zza = zzgsg;
        return this;
    }

    public final zzgrv zzd() throws GeneralSecurityException {
        zzgzf zzgzf;
        zzgze zza2;
        zzgsg zzgsg = this.zza;
        if (zzgsg == null || (zzgzf = this.zzb) == null) {
            throw new GeneralSecurityException("Cannot build without parameters and/or key material");
        } else if (zzgsg.zzc() != zzgzf.zza()) {
            throw new GeneralSecurityException("Key size mismatch");
        } else if (zzgsg.zza() && this.zzc == null) {
            throw new GeneralSecurityException("Cannot create key without ID requirement with parameters with ID requirement");
        } else if (this.zza.zza() || this.zzc == null) {
            if (this.zza.zzg() == zzgse.zzd) {
                zza2 = zzgpm.zza;
            } else if (this.zza.zzg() == zzgse.zzc || this.zza.zzg() == zzgse.zzb) {
                zza2 = zzgpm.zza(this.zzc.intValue());
            } else if (this.zza.zzg() == zzgse.zza) {
                zza2 = zzgpm.zzb(this.zzc.intValue());
            } else {
                throw new IllegalStateException("Unknown HmacParameters.Variant: ".concat(String.valueOf(String.valueOf(this.zza.zzg()))));
            }
            return new zzgrv(this.zza, this.zzb, zza2, this.zzc, (zzgru) null);
        } else {
            throw new GeneralSecurityException("Cannot create key with ID requirement with parameters without ID requirement");
        }
    }
}
