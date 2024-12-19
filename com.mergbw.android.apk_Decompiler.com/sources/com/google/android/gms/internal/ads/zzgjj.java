package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgjj {
    @Nullable
    private Integer zza = null;
    @Nullable
    private Integer zzb = null;
    @Nullable
    private Integer zzc = null;
    private zzgjk zzd = zzgjk.zzc;

    private zzgjj() {
    }

    /* synthetic */ zzgjj(zzgji zzgji) {
    }

    public final zzgjj zzc(int i) throws GeneralSecurityException {
        this.zzc = 16;
        return this;
    }

    public final zzgjj zzd(zzgjk zzgjk) {
        this.zzd = zzgjk;
        return this;
    }

    public final zzgjm zze() throws GeneralSecurityException {
        Integer num = this.zza;
        if (num == null) {
            throw new GeneralSecurityException("Key size is not set");
        } else if (this.zzb == null) {
            throw new GeneralSecurityException("IV size is not set");
        } else if (this.zzd == null) {
            throw new GeneralSecurityException("Variant is not set");
        } else if (this.zzc != null) {
            int intValue = num.intValue();
            int intValue2 = this.zzb.intValue();
            this.zzc.intValue();
            return new zzgjm(intValue, intValue2, 16, this.zzd, (zzgjl) null);
        } else {
            throw new GeneralSecurityException("Tag size is not set");
        }
    }

    public final zzgjj zza(int i) throws GeneralSecurityException {
        if (i == 12 || i == 16) {
            this.zzb = Integer.valueOf(i);
            return this;
        }
        throw new GeneralSecurityException(String.format("Invalid IV size in bytes %d; acceptable values have 12 or 16 bytes", new Object[]{Integer.valueOf(i)}));
    }

    public final zzgjj zzb(int i) throws GeneralSecurityException {
        if (i == 16 || i == 24 || i == 32) {
            this.zza = Integer.valueOf(i);
            return this;
        }
        throw new InvalidAlgorithmParameterException(String.format("Invalid key size %d; only 16-byte, 24-byte and 32-byte AES keys are supported", new Object[]{Integer.valueOf(i)}));
    }
}
