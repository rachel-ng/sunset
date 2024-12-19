package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
final class zzkc implements zzkd {
    private static <E> zzjt<E> zzc(Object obj, long j) {
        return (zzjt) zzmg.zze(obj, j);
    }

    public final <L> List<L> zza(Object obj, long j) {
        zzjt zzc = zzc(obj, j);
        if (zzc.zzc()) {
            return zzc;
        }
        int size = zzc.size();
        zzjt zza = zzc.zza(size == 0 ? 10 : size << 1);
        zzmg.zza(obj, j, (Object) zza);
        return zza;
    }

    zzkc() {
    }

    public final void zzb(Object obj, long j) {
        zzc(obj, j).zzb();
    }

    public final <E> void zza(Object obj, Object obj2, long j) {
        zzjt zzc = zzc(obj, j);
        zzjt zzc2 = zzc(obj2, j);
        int size = zzc.size();
        int size2 = zzc2.size();
        if (size > 0 && size2 > 0) {
            if (!zzc.zzc()) {
                zzc = zzc.zza(size2 + size);
            }
            zzc.addAll(zzc2);
        }
        if (size > 0) {
            zzc2 = zzc;
        }
        zzmg.zza(obj, j, (Object) zzc2);
    }
}
