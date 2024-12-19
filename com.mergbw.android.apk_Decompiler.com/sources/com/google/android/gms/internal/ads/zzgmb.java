package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgmb extends zzgih {
    private final zzgmh zza;
    private final zzgzf zzb;
    private final zzgze zzc;
    @Nullable
    private final Integer zzd;

    private zzgmb(zzgmh zzgmh, zzgzf zzgzf, zzgze zzgze, @Nullable Integer num) {
        this.zza = zzgmh;
        this.zzb = zzgzf;
        this.zzc = zzgze;
        this.zzd = num;
    }

    public static zzgmb zza(zzgmg zzgmg, zzgzf zzgzf, @Nullable Integer num) throws GeneralSecurityException {
        zzgze zzgze;
        zzgmg zzgmg2 = zzgmg.zzc;
        if (zzgmg != zzgmg2 && num == null) {
            String obj = zzgmg.toString();
            throw new GeneralSecurityException("For given Variant " + obj + " the value of idRequirement must be non-null");
        } else if (zzgmg == zzgmg2 && num != null) {
            throw new GeneralSecurityException("For given Variant NO_PREFIX the value of idRequirement must be null");
        } else if (zzgzf.zza() == 32) {
            zzgmh zzc2 = zzgmh.zzc(zzgmg);
            if (zzc2.zzb() == zzgmg2) {
                zzgze = zzgpm.zza;
            } else if (zzc2.zzb() == zzgmg.zzb) {
                zzgze = zzgpm.zza(num.intValue());
            } else if (zzc2.zzb() == zzgmg.zza) {
                zzgze = zzgpm.zzb(num.intValue());
            } else {
                throw new IllegalStateException("Unknown Variant: ".concat(zzc2.zzb().toString()));
            }
            return new zzgmb(zzc2, zzgzf, zzgze, num);
        } else {
            int zza2 = zzgzf.zza();
            throw new GeneralSecurityException("XChaCha20Poly1305 key must be constructed with key of length 32 bytes, not " + zza2);
        }
    }

    public final zzgmh zzb() {
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
