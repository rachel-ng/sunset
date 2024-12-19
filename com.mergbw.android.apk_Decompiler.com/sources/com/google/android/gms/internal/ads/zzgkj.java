package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgkj {
    @Nullable
    private Integer zza = null;
    private zzgkk zzb = zzgkk.zzc;

    private zzgkj() {
    }

    /* synthetic */ zzgkj(zzgki zzgki) {
    }

    public final zzgkj zzb(zzgkk zzgkk) {
        this.zzb = zzgkk;
        return this;
    }

    public final zzgkm zzc() throws GeneralSecurityException {
        Integer num = this.zza;
        if (num == null) {
            throw new GeneralSecurityException("Key size is not set");
        } else if (this.zzb != null) {
            return new zzgkm(num.intValue(), this.zzb, (zzgkl) null);
        } else {
            throw new GeneralSecurityException("Variant is not set");
        }
    }

    public final zzgkj zza(int i) throws GeneralSecurityException {
        if (i == 16 || i == 32) {
            this.zza = Integer.valueOf(i);
            return this;
        }
        throw new InvalidAlgorithmParameterException(String.format("Invalid key size %d; only 16-byte and 32-byte AES keys are supported", new Object[]{Integer.valueOf(i)}));
    }
}
