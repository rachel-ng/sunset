package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgli extends zzgih {
    private final zzglo zza;
    private final zzgze zzb;
    @Nullable
    private final Integer zzc;

    private zzgli(zzglo zzglo, zzgze zzgze, @Nullable Integer num) {
        this.zza = zzglo;
        this.zzb = zzgze;
        this.zzc = num;
    }

    public static zzgli zza(zzglo zzglo, @Nullable Integer num) throws GeneralSecurityException {
        zzgze zzgze;
        if (zzglo.zzc() == zzglm.zzb) {
            if (num == null) {
                zzgze = zzgpm.zza;
            } else {
                throw new GeneralSecurityException("For given Variant NO_PREFIX the value of idRequirement must be null");
            }
        } else if (zzglo.zzc() != zzglm.zza) {
            throw new GeneralSecurityException("Unknown Variant: ".concat(String.valueOf(String.valueOf(zzglo.zzc()))));
        } else if (num != null) {
            zzgze = zzgpm.zzb(num.intValue());
        } else {
            throw new GeneralSecurityException("For given Variant TINK the value of idRequirement must be non-null");
        }
        return new zzgli(zzglo, zzgze, num);
    }

    public final zzglo zzb() {
        return this.zza;
    }

    public final zzgze zzc() {
        return this.zzb;
    }

    public final Integer zzd() {
        return this.zzc;
    }
}
