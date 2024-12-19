package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgjc {
    @Nullable
    private zzgjm zza = null;
    @Nullable
    private zzgzf zzb = null;
    @Nullable
    private Integer zzc = null;

    private zzgjc() {
    }

    /* synthetic */ zzgjc(zzgjb zzgjb) {
    }

    public final zzgjc zza(@Nullable Integer num) {
        this.zzc = num;
        return this;
    }

    public final zzgjc zzb(zzgzf zzgzf) {
        this.zzb = zzgzf;
        return this;
    }

    public final zzgjc zzc(zzgjm zzgjm) {
        this.zza = zzgjm;
        return this;
    }

    public final zzgje zzd() throws GeneralSecurityException {
        zzgzf zzgzf;
        zzgze zzb2;
        zzgjm zzgjm = this.zza;
        if (zzgjm == null || (zzgzf = this.zzb) == null) {
            throw new GeneralSecurityException("Cannot build without parameters and/or key material");
        } else if (zzgjm.zzc() != zzgzf.zza()) {
            throw new GeneralSecurityException("Key size mismatch");
        } else if (zzgjm.zza() && this.zzc == null) {
            throw new GeneralSecurityException("Cannot create key without ID requirement with parameters with ID requirement");
        } else if (this.zza.zza() || this.zzc == null) {
            if (this.zza.zze() == zzgjk.zzc) {
                zzb2 = zzgpm.zza;
            } else if (this.zza.zze() == zzgjk.zzb) {
                zzb2 = zzgpm.zza(this.zzc.intValue());
            } else if (this.zza.zze() == zzgjk.zza) {
                zzb2 = zzgpm.zzb(this.zzc.intValue());
            } else {
                throw new IllegalStateException("Unknown AesEaxParameters.Variant: ".concat(String.valueOf(String.valueOf(this.zza.zze()))));
            }
            return new zzgje(this.zza, this.zzb, zzb2, this.zzc, (zzgjd) null);
        } else {
            throw new GeneralSecurityException("Cannot create key with ID requirement with parameters without ID requirement");
        }
    }
}
