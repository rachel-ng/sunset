package com.google.android.gms.internal.ads;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
final class zzhch implements Map.Entry {
    private final Map.Entry zza;

    /* synthetic */ zzhch(Map.Entry entry, zzhcg zzhcg) {
        this.zza = entry;
    }

    public final Object getKey() {
        return this.zza.getKey();
    }

    public final Object getValue() {
        if (((zzhcj) this.zza.getValue()) == null) {
            return null;
        }
        throw null;
    }

    public final Object setValue(Object obj) {
        if (obj instanceof zzhde) {
            return ((zzhcj) this.zza.getValue()).zzc((zzhde) obj);
        }
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }

    public final zzhcj zza() {
        return (zzhcj) this.zza.getValue();
    }
}
