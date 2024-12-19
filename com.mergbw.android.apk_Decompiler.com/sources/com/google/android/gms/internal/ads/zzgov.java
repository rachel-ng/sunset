package com.google.android.gms.internal.ads;

import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgov extends zzghx {
    private final zzgqm zza;

    public zzgov(zzgqm zzgqm) {
        this.zza = zzgqm;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzgov)) {
            return false;
        }
        zzgqm zzgqm = ((zzgov) obj).zza;
        if (!this.zza.zzc().zzg().equals(zzgqm.zzc().zzg()) || !this.zza.zzc().zzi().equals(zzgqm.zzc().zzi()) || !this.zza.zzc().zzh().equals(zzgqm.zzc().zzh())) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        zzgqm zzgqm = this.zza;
        return Objects.hash(new Object[]{zzgqm.zzc(), zzgqm.zzd()});
    }

    public final String toString() {
        String str;
        String zzi = this.zza.zzc().zzi();
        zzgxn zzg = this.zza.zzc().zzg();
        zzgxn zzgxn = zzgxn.UNKNOWN_PREFIX;
        int ordinal = zzg.ordinal();
        if (ordinal != 1) {
            str = ordinal != 2 ? ordinal != 3 ? ordinal != 4 ? "UNKNOWN" : "CRUNCHY" : "RAW" : "LEGACY";
        } else {
            str = "TINK";
        }
        return String.format("(typeUrl=%s, outputPrefixType=%s)", new Object[]{zzi, str});
    }

    public final boolean zza() {
        return this.zza.zzc().zzg() != zzgxn.RAW;
    }

    public final zzgqm zzb() {
        return this.zza;
    }
}
