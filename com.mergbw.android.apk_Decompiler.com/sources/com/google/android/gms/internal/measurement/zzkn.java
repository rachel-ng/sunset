package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
public final class zzkn<K, V> extends LinkedHashMap<K, V> {
    private static final zzkn<?, ?> zza;
    private boolean zzb = true;

    private static int zza(Object obj) {
        if (obj instanceof byte[]) {
            return zzjm.zza((byte[]) obj);
        }
        if (!(obj instanceof zzjp)) {
            return obj.hashCode();
        }
        throw new UnsupportedOperationException();
    }

    public final int hashCode() {
        int i = 0;
        for (Map.Entry entry : entrySet()) {
            i += zza(entry.getValue()) ^ zza(entry.getKey());
        }
        return i;
    }

    public static <K, V> zzkn<K, V> zza() {
        return zza;
    }

    public final zzkn<K, V> zzb() {
        return isEmpty() ? new zzkn<>() : new zzkn<>(this);
    }

    public final V put(K k, V v) {
        zze();
        zzjm.zza(k);
        zzjm.zza(v);
        return super.put(k, v);
    }

    public final V remove(Object obj) {
        zze();
        return super.remove(obj);
    }

    public final Set<Map.Entry<K, V>> entrySet() {
        return isEmpty() ? Collections.emptySet() : super.entrySet();
    }

    static {
        zzkn<?, ?> zzkn = new zzkn<>();
        zza = zzkn;
        zzkn.zzb = false;
    }

    private zzkn() {
    }

    private zzkn(Map<K, V> map) {
        super(map);
    }

    public final void clear() {
        zze();
        super.clear();
    }

    private final void zze() {
        if (!this.zzb) {
            throw new UnsupportedOperationException();
        }
    }

    public final void zzc() {
        this.zzb = false;
    }

    public final void zza(zzkn<K, V> zzkn) {
        zze();
        if (!zzkn.isEmpty()) {
            putAll(zzkn);
        }
    }

    public final void putAll(Map<? extends K, ? extends V> map) {
        zze();
        for (Object next : map.keySet()) {
            zzjm.zza(next);
            zzjm.zza(map.get(next));
        }
        super.putAll(map);
    }

    public final boolean equals(Object obj) {
        boolean z;
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        if (this == map) {
            return true;
        }
        if (size() != map.size()) {
            return false;
        }
        for (Map.Entry entry : entrySet()) {
            if (!map.containsKey(entry.getKey())) {
                return false;
            }
            Object value = entry.getValue();
            Object obj2 = map.get(entry.getKey());
            if (!(value instanceof byte[]) || !(obj2 instanceof byte[])) {
                z = value.equals(obj2);
                continue;
            } else {
                z = Arrays.equals((byte[]) value, (byte[]) obj2);
                continue;
            }
            if (!z) {
                return false;
            }
        }
        return true;
    }

    public final boolean zzd() {
        return this.zzb;
    }
}
