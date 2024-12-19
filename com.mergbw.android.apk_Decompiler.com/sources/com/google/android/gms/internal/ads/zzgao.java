package com.google.android.gms.internal.ads;

import java.util.Comparator;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzgao extends zzgar {
    zzgao() {
        super((zzgaq) null);
    }

    static final zzgar zzg(int i) {
        return i < 0 ? zzgar.zzb : i > 0 ? zzgar.zzc : zzgar.zza;
    }

    public final int zza() {
        return 0;
    }

    public final zzgar zzb(int i, int i2) {
        return zzg(i < i2 ? -1 : i > i2 ? 1 : 0);
    }

    public final zzgar zzc(long j, long j2) {
        int i = (j > j2 ? 1 : (j == j2 ? 0 : -1));
        return zzg(i < 0 ? -1 : i > 0 ? 1 : 0);
    }

    public final zzgar zzd(Object obj, Object obj2, Comparator comparator) {
        return zzg(comparator.compare(obj, obj2));
    }

    public final zzgar zze(boolean z, boolean z2) {
        return zzg(zzgdw.zza(z, z2));
    }

    public final zzgar zzf(boolean z, boolean z2) {
        return zzg(zzgdw.zza(z2, z));
    }
}
