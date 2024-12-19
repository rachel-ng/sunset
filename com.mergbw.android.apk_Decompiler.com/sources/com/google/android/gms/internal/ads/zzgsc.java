package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgsc {
    @Nullable
    private Integer zza = null;
    @Nullable
    private Integer zzb = null;
    private zzgsd zzc = null;
    private zzgse zzd = zzgse.zzd;

    private zzgsc() {
    }

    /* synthetic */ zzgsc(zzgsb zzgsb) {
    }

    public final zzgsc zza(zzgsd zzgsd) {
        this.zzc = zzgsd;
        return this;
    }

    public final zzgsc zzb(int i) throws GeneralSecurityException {
        this.zza = Integer.valueOf(i);
        return this;
    }

    public final zzgsc zzc(int i) throws GeneralSecurityException {
        this.zzb = Integer.valueOf(i);
        return this;
    }

    public final zzgsc zzd(zzgse zzgse) {
        this.zzd = zzgse;
        return this;
    }

    public final zzgsg zze() throws GeneralSecurityException {
        Integer num = this.zza;
        if (num == null) {
            throw new GeneralSecurityException("key size is not set");
        } else if (this.zzb == null) {
            throw new GeneralSecurityException("tag size is not set");
        } else if (this.zzc == null) {
            throw new GeneralSecurityException("hash type is not set");
        } else if (this.zzd == null) {
            throw new GeneralSecurityException("variant is not set");
        } else if (num.intValue() >= 16) {
            Integer num2 = this.zzb;
            int intValue = num2.intValue();
            zzgsd zzgsd = this.zzc;
            if (intValue >= 10) {
                if (zzgsd == zzgsd.zza) {
                    if (intValue > 20) {
                        throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 20 bytes for SHA1", new Object[]{num2}));
                    }
                } else if (zzgsd == zzgsd.zzb) {
                    if (intValue > 28) {
                        throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 28 bytes for SHA224", new Object[]{num2}));
                    }
                } else if (zzgsd == zzgsd.zzc) {
                    if (intValue > 32) {
                        throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 32 bytes for SHA256", new Object[]{num2}));
                    }
                } else if (zzgsd == zzgsd.zzd) {
                    if (intValue > 48) {
                        throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 48 bytes for SHA384", new Object[]{num2}));
                    }
                } else if (zzgsd != zzgsd.zze) {
                    throw new GeneralSecurityException("unknown hash type; must be SHA256, SHA384 or SHA512");
                } else if (intValue > 64) {
                    throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 64 bytes for SHA512", new Object[]{num2}));
                }
                return new zzgsg(this.zza.intValue(), this.zzb.intValue(), this.zzd, this.zzc, (zzgsf) null);
            }
            throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; must be at least 10 bytes", new Object[]{num2}));
        } else {
            throw new InvalidAlgorithmParameterException(String.format("Invalid key size in bytes %d; must be at least 16 bytes", new Object[]{this.zza}));
        }
    }
}
