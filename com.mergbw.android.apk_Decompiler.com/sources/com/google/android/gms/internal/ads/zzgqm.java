package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgqm implements zzgqq {
    private final zzgze zza;
    private final zzgwm zzb;

    private zzgqm(zzgwm zzgwm, zzgze zzgze) {
        this.zzb = zzgwm;
        this.zza = zzgze;
    }

    public static zzgqm zza(zzgwm zzgwm) throws GeneralSecurityException {
        return new zzgqm(zzgwm, zzgra.zza(zzgwm.zzi()));
    }

    public static zzgqm zzb(zzgwm zzgwm) {
        return new zzgqm(zzgwm, zzgra.zzb(zzgwm.zzi()));
    }

    public final zzgwm zzc() {
        return this.zzb;
    }

    public final zzgze zzd() {
        return this.zza;
    }
}
