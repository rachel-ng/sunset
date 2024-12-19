package com.google.android.gms.internal.ads;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
final class zzhdo {
    private static final zzhdo zza = new zzhdo();
    private final zzhea zzb = new zzhcw();
    private final ConcurrentMap zzc = new ConcurrentHashMap();

    private zzhdo() {
    }

    public static zzhdo zza() {
        return zza;
    }

    public final zzhdz zzb(Class cls) {
        zzhcb.zzc(cls, "messageType");
        zzhdz zzhdz = (zzhdz) this.zzc.get(cls);
        if (zzhdz == null) {
            zzhdz = this.zzb.zza(cls);
            zzhcb.zzc(cls, "messageType");
            zzhdz zzhdz2 = (zzhdz) this.zzc.putIfAbsent(cls, zzhdz);
            return zzhdz2 == null ? zzhdz : zzhdz2;
        }
    }
}
