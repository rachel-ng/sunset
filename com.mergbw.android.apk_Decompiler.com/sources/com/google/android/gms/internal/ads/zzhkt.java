package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzhkt extends zzhkl {
    private static final zzhky zza = zzhkq.zza(Collections.emptyMap());

    /* synthetic */ zzhkt(Map map, zzhkr zzhkr) {
        super(map);
    }

    public static zzhks zzc(int i) {
        return new zzhks(i, (zzhkr) null);
    }

    /* renamed from: zzd */
    public final Map zzb() {
        LinkedHashMap zzb = zzhkm.zzb(zza().size());
        for (Map.Entry entry : zza().entrySet()) {
            zzb.put(entry.getKey(), ((zzhky) entry.getValue()).zzb());
        }
        return Collections.unmodifiableMap(zzb);
    }
}
