package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgjo {
    @Nullable
    private zzgjz zza = null;
    @Nullable
    private zzgzf zzb = null;
    @Nullable
    private Integer zzc = null;

    private zzgjo() {
    }

    /* synthetic */ zzgjo(zzgjn zzgjn) {
    }

    public final zzgjo zza(@Nullable Integer num) {
        this.zzc = num;
        return this;
    }

    public final zzgjo zzb(zzgzf zzgzf) {
        this.zzb = zzgzf;
        return this;
    }

    public final zzgjo zzc(zzgjz zzgjz) {
        this.zza = zzgjz;
        return this;
    }

    public final zzgjq zzd() throws GeneralSecurityException {
        zzgzf zzgzf;
        zzgze zzb2;
        zzgjz zzgjz = this.zza;
        if (zzgjz == null || (zzgzf = this.zzb) == null) {
            throw new GeneralSecurityException("Cannot build without parameters and/or key material");
        } else if (zzgjz.zzb() != zzgzf.zza()) {
            throw new GeneralSecurityException("Key size mismatch");
        } else if (zzgjz.zza() && this.zzc == null) {
            throw new GeneralSecurityException("Cannot create key without ID requirement with parameters with ID requirement");
        } else if (this.zza.zza() || this.zzc == null) {
            if (this.zza.zzd() == zzgjx.zzc) {
                zzb2 = zzgpm.zza;
            } else if (this.zza.zzd() == zzgjx.zzb) {
                zzb2 = zzgpm.zza(this.zzc.intValue());
            } else if (this.zza.zzd() == zzgjx.zza) {
                zzb2 = zzgpm.zzb(this.zzc.intValue());
            } else {
                throw new IllegalStateException("Unknown AesGcmParameters.Variant: ".concat(String.valueOf(String.valueOf(this.zza.zzd()))));
            }
            return new zzgjq(this.zza, this.zzb, zzb2, this.zzc, (zzgjp) null);
        } else {
            throw new GeneralSecurityException("Cannot create key with ID requirement with parameters without ID requirement");
        }
    }
}
