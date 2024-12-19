package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgkn extends zzgih {
    private final zzgks zza;
    private final zzgzf zzb;
    private final zzgze zzc;
    @Nullable
    private final Integer zzd;

    private zzgkn(zzgks zzgks, zzgzf zzgzf, zzgze zzgze, @Nullable Integer num) {
        this.zza = zzgks;
        this.zzb = zzgzf;
        this.zzc = zzgze;
        this.zzd = num;
    }

    public static zzgkn zza(zzgkr zzgkr, zzgzf zzgzf, @Nullable Integer num) throws GeneralSecurityException {
        zzgze zzgze;
        zzgkr zzgkr2 = zzgkr.zzc;
        if (zzgkr != zzgkr2 && num == null) {
            String obj = zzgkr.toString();
            throw new GeneralSecurityException("For given Variant " + obj + " the value of idRequirement must be non-null");
        } else if (zzgkr == zzgkr2 && num != null) {
            throw new GeneralSecurityException("For given Variant NO_PREFIX the value of idRequirement must be null");
        } else if (zzgzf.zza() == 32) {
            zzgks zzc2 = zzgks.zzc(zzgkr);
            if (zzc2.zzb() == zzgkr2) {
                zzgze = zzgpm.zza;
            } else if (zzc2.zzb() == zzgkr.zzb) {
                zzgze = zzgpm.zza(num.intValue());
            } else if (zzc2.zzb() == zzgkr.zza) {
                zzgze = zzgpm.zzb(num.intValue());
            } else {
                throw new IllegalStateException("Unknown Variant: ".concat(zzc2.zzb().toString()));
            }
            return new zzgkn(zzc2, zzgzf, zzgze, num);
        } else {
            int zza2 = zzgzf.zza();
            throw new GeneralSecurityException("ChaCha20Poly1305 key must be constructed with key of length 32 bytes, not " + zza2);
        }
    }

    public final zzgks zzb() {
        return this.zza;
    }

    public final zzgze zzc() {
        return this.zzc;
    }

    public final zzgzf zzd() {
        return this.zzb;
    }

    @Nullable
    public final Integer zze() {
        return this.zzd;
    }
}
