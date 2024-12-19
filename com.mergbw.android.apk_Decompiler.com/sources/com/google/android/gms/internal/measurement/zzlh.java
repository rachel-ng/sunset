package com.google.android.gms.internal.measurement;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
final class zzlh {
    private static final zzlh zza = new zzlh();
    private final zzlk zzb = new zzkh();
    private final ConcurrentMap<Class<?>, zzll<?>> zzc = new ConcurrentHashMap();

    public static zzlh zza() {
        return zza;
    }

    public final <T> zzll<T> zza(Class<T> cls) {
        zzjm.zza(cls, "messageType");
        zzll<T> zzll = (zzll) this.zzc.get(cls);
        if (zzll != null) {
            return zzll;
        }
        zzll<T> zza2 = this.zzb.zza(cls);
        zzjm.zza(cls, "messageType");
        zzjm.zza(zza2, "schema");
        zzll<T> putIfAbsent = this.zzc.putIfAbsent(cls, zza2);
        return putIfAbsent != null ? putIfAbsent : zza2;
    }

    public final <T> zzll<T> zza(T t) {
        return zza(t.getClass());
    }

    private zzlh() {
    }
}
