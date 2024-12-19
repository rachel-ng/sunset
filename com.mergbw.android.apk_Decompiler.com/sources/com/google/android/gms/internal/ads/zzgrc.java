package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgrc {
    @Nullable
    private zzgrn zza = null;
    @Nullable
    private zzgzf zzb = null;
    @Nullable
    private Integer zzc = null;

    private zzgrc() {
    }

    /* synthetic */ zzgrc(zzgrb zzgrb) {
    }

    public final zzgrc zza(zzgzf zzgzf) throws GeneralSecurityException {
        this.zzb = zzgzf;
        return this;
    }

    public final zzgrc zzb(@Nullable Integer num) {
        this.zzc = num;
        return this;
    }

    public final zzgrc zzc(zzgrn zzgrn) {
        this.zza = zzgrn;
        return this;
    }

    public final zzgre zzd() throws GeneralSecurityException {
        zzgzf zzgzf;
        zzgze zza2;
        zzgrn zzgrn = this.zza;
        if (zzgrn == null || (zzgzf = this.zzb) == null) {
            throw new GeneralSecurityException("Cannot build without parameters and/or key material");
        } else if (zzgrn.zzc() != zzgzf.zza()) {
            throw new GeneralSecurityException("Key size mismatch");
        } else if (zzgrn.zza() && this.zzc == null) {
            throw new GeneralSecurityException("Cannot create key without ID requirement with parameters with ID requirement");
        } else if (this.zza.zza() || this.zzc == null) {
            if (this.zza.zzf() == zzgrl.zzd) {
                zza2 = zzgpm.zza;
            } else if (this.zza.zzf() == zzgrl.zzc || this.zza.zzf() == zzgrl.zzb) {
                zza2 = zzgpm.zza(this.zzc.intValue());
            } else if (this.zza.zzf() == zzgrl.zza) {
                zza2 = zzgpm.zzb(this.zzc.intValue());
            } else {
                throw new IllegalStateException("Unknown AesCmacParametersParameters.Variant: ".concat(String.valueOf(String.valueOf(this.zza.zzf()))));
            }
            return new zzgre(this.zza, this.zzb, zza2, this.zzc, (zzgrd) null);
        } else {
            throw new GeneralSecurityException("Cannot create key with ID requirement with parameters without ID requirement");
        }
    }
}
