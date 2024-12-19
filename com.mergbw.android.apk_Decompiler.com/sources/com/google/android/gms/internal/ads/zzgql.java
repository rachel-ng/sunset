package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgql implements zzgqq {
    private final String zza;
    private final zzgze zzb;
    private final zzhac zzc;
    private final zzgwg zzd;
    private final zzgxn zze;
    @Nullable
    private final Integer zzf;

    private zzgql(String str, zzgze zzgze, zzhac zzhac, zzgwg zzgwg, zzgxn zzgxn, @Nullable Integer num) {
        this.zza = str;
        this.zzb = zzgze;
        this.zzc = zzhac;
        this.zzd = zzgwg;
        this.zze = zzgxn;
        this.zzf = num;
    }

    public static zzgql zza(String str, zzhac zzhac, zzgwg zzgwg, zzgxn zzgxn, @Nullable Integer num) throws GeneralSecurityException {
        if (zzgxn == zzgxn.RAW) {
            if (num != null) {
                throw new GeneralSecurityException("Keys with output prefix type raw should not have an id requirement.");
            }
        } else if (num == null) {
            throw new GeneralSecurityException("Keys with output prefix type different from raw should have an id requirement.");
        }
        return new zzgql(str, zzgra.zza(str), zzhac, zzgwg, zzgxn, num);
    }

    public final zzgwg zzb() {
        return this.zzd;
    }

    public final zzgxn zzc() {
        return this.zze;
    }

    public final zzgze zzd() {
        return this.zzb;
    }

    public final zzhac zze() {
        return this.zzc;
    }

    @Nullable
    public final Integer zzf() {
        return this.zzf;
    }

    public final String zzg() {
        return this.zza;
    }
}
