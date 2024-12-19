package com.google.android.gms.internal.ads;

import java.util.LinkedHashMap;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public class zzhkk {
    final LinkedHashMap zza;

    zzhkk(int i) {
        this.zza = zzhkm.zzb(i);
    }

    /* access modifiers changed from: package-private */
    public final zzhkk zza(Object obj, zzhky zzhky) {
        zzhkx.zza(obj, "key");
        zzhkx.zza(zzhky, "provider");
        this.zza.put(obj, zzhky);
        return this;
    }
}
