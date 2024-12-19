package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgla extends zzgih {
    private final zzglc zza;
    private final zzgze zzb;
    @Nullable
    private final Integer zzc;

    private zzgla(zzglc zzglc, zzgze zzgze, @Nullable Integer num) {
        this.zza = zzglc;
        this.zzb = zzgze;
        this.zzc = num;
    }

    public static zzgla zza(zzglc zzglc, @Nullable Integer num) throws GeneralSecurityException {
        zzgze zzgze;
        if (zzglc.zzb() == zzglb.zza) {
            if (num != null) {
                zzgze = zzgze.zzb(ByteBuffer.allocate(5).put((byte) 1).putInt(num.intValue()).array());
            } else {
                throw new GeneralSecurityException("For given Variant TINK the value of idRequirement must be non-null");
            }
        } else if (zzglc.zzb() != zzglb.zzb) {
            throw new GeneralSecurityException("Unknown Variant: ".concat(zzglc.zzb().toString()));
        } else if (num == null) {
            zzgze = zzgze.zzb(new byte[0]);
        } else {
            throw new GeneralSecurityException("For given Variant NO_PREFIX the value of idRequirement must be null");
        }
        return new zzgla(zzglc, zzgze, num);
    }

    public final zzglc zzb() {
        return this.zza;
    }

    public final zzgze zzc() {
        return this.zzb;
    }

    public final Integer zzd() {
        return this.zzc;
    }
}
