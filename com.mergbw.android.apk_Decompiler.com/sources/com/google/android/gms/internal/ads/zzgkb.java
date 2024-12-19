package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgkb {
    @Nullable
    private zzgkm zza = null;
    @Nullable
    private zzgzf zzb = null;
    @Nullable
    private Integer zzc = null;

    private zzgkb() {
    }

    /* synthetic */ zzgkb(zzgka zzgka) {
    }

    public final zzgkb zza(@Nullable Integer num) {
        this.zzc = num;
        return this;
    }

    public final zzgkb zzb(zzgzf zzgzf) {
        this.zzb = zzgzf;
        return this;
    }

    public final zzgkb zzc(zzgkm zzgkm) {
        this.zza = zzgkm;
        return this;
    }

    public final zzgkd zzd() throws GeneralSecurityException {
        zzgzf zzgzf;
        zzgze zzb2;
        zzgkm zzgkm = this.zza;
        if (zzgkm == null || (zzgzf = this.zzb) == null) {
            throw new GeneralSecurityException("Cannot build without parameters and/or key material");
        } else if (zzgkm.zzb() != zzgzf.zza()) {
            throw new GeneralSecurityException("Key size mismatch");
        } else if (zzgkm.zza() && this.zzc == null) {
            throw new GeneralSecurityException("Cannot create key without ID requirement with parameters with ID requirement");
        } else if (this.zza.zza() || this.zzc == null) {
            if (this.zza.zzd() == zzgkk.zzc) {
                zzb2 = zzgpm.zza;
            } else if (this.zza.zzd() == zzgkk.zzb) {
                zzb2 = zzgpm.zza(this.zzc.intValue());
            } else if (this.zza.zzd() == zzgkk.zza) {
                zzb2 = zzgpm.zzb(this.zzc.intValue());
            } else {
                throw new IllegalStateException("Unknown AesGcmSivParameters.Variant: ".concat(String.valueOf(String.valueOf(this.zza.zzd()))));
            }
            return new zzgkd(this.zza, this.zzb, zzb2, this.zzc, (zzgkc) null);
        } else {
            throw new GeneralSecurityException("Cannot create key with ID requirement with parameters without ID requirement");
        }
    }
}
