package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgjw {
    @Nullable
    private Integer zza = null;
    @Nullable
    private Integer zzb = null;
    @Nullable
    private Integer zzc = null;
    private zzgjx zzd = zzgjx.zzc;

    private zzgjw() {
    }

    /* synthetic */ zzgjw(zzgjv zzgjv) {
    }

    public final zzgjw zza(int i) throws GeneralSecurityException {
        this.zzb = 12;
        return this;
    }

    public final zzgjw zzc(int i) throws GeneralSecurityException {
        this.zzc = 16;
        return this;
    }

    public final zzgjw zzd(zzgjx zzgjx) {
        this.zzd = zzgjx;
        return this;
    }

    public final zzgjz zze() throws GeneralSecurityException {
        Integer num = this.zza;
        if (num == null) {
            throw new GeneralSecurityException("Key size is not set");
        } else if (this.zzd == null) {
            throw new GeneralSecurityException("Variant is not set");
        } else if (this.zzb == null) {
            throw new GeneralSecurityException("IV size is not set");
        } else if (this.zzc != null) {
            int intValue = num.intValue();
            this.zzb.intValue();
            this.zzc.intValue();
            return new zzgjz(intValue, 12, 16, this.zzd, (zzgjy) null);
        } else {
            throw new GeneralSecurityException("Tag size is not set");
        }
    }

    public final zzgjw zzb(int i) throws GeneralSecurityException {
        if (i == 16 || i == 24 || i == 32) {
            this.zza = Integer.valueOf(i);
            return this;
        }
        throw new InvalidAlgorithmParameterException(String.format("Invalid key size %d; only 16-byte, 24-byte and 32-byte AES keys are supported", new Object[]{Integer.valueOf(i)}));
    }
}
