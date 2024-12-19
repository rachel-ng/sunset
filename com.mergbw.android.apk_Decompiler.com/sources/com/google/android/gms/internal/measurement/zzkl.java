package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
public final class zzkl<K, V> {
    static <K, V> int zza(zzkk<K, V> zzkk, K k, V v) {
        return zzjd.zza(zzkk.zza, 1, k) + zzjd.zza(zzkk.zzc, 2, v);
    }

    static <K, V> void zza(zzit zzit, zzkk<K, V> zzkk, K k, V v) throws IOException {
        zzjd.zza(zzit, zzkk.zza, 1, k);
        zzjd.zza(zzit, zzkk.zzc, 2, v);
    }
}
