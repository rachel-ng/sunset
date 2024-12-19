package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgio {
    @Nullable
    private zzgja zza = null;
    @Nullable
    private zzgzf zzb = null;
    @Nullable
    private zzgzf zzc = null;
    @Nullable
    private Integer zzd = null;

    private zzgio() {
    }

    /* synthetic */ zzgio(zzgin zzgin) {
    }

    public final zzgio zza(zzgzf zzgzf) {
        this.zzb = zzgzf;
        return this;
    }

    public final zzgio zzb(zzgzf zzgzf) {
        this.zzc = zzgzf;
        return this;
    }

    public final zzgio zzc(@Nullable Integer num) {
        this.zzd = num;
        return this;
    }

    public final zzgio zzd(zzgja zzgja) {
        this.zza = zzgja;
        return this;
    }

    public final zzgiq zze() throws GeneralSecurityException {
        zzgze zzb2;
        zzgja zzgja = this.zza;
        if (zzgja != null) {
            zzgzf zzgzf = this.zzb;
            if (zzgzf == null || this.zzc == null) {
                throw new GeneralSecurityException("Cannot build without key material");
            } else if (zzgja.zzb() != zzgzf.zza()) {
                throw new GeneralSecurityException("AES key size mismatch");
            } else if (zzgja.zzc() != this.zzc.zza()) {
                throw new GeneralSecurityException("HMAC key size mismatch");
            } else if (this.zza.zza() && this.zzd == null) {
                throw new GeneralSecurityException("Cannot create key without ID requirement with parameters with ID requirement");
            } else if (this.zza.zza() || this.zzd == null) {
                if (this.zza.zzh() == zzgiy.zzc) {
                    zzb2 = zzgpm.zza;
                } else if (this.zza.zzh() == zzgiy.zzb) {
                    zzb2 = zzgpm.zza(this.zzd.intValue());
                } else if (this.zza.zzh() == zzgiy.zza) {
                    zzb2 = zzgpm.zzb(this.zzd.intValue());
                } else {
                    throw new IllegalStateException("Unknown AesCtrHmacAeadParameters.Variant: ".concat(String.valueOf(String.valueOf(this.zza.zzh()))));
                }
                return new zzgiq(this.zza, this.zzb, this.zzc, zzb2, this.zzd, (zzgip) null);
            } else {
                throw new GeneralSecurityException("Cannot create key with ID requirement with parameters without ID requirement");
            }
        } else {
            throw new GeneralSecurityException("Cannot build without parameters");
        }
    }
}
