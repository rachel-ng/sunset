package com.google.android.gms.internal.ads;

import java.util.AbstractList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public final class zzhby extends AbstractList {
    private final List zza;
    private final zzhbx zzb;

    public zzhby(List list, zzhbx zzhbx) {
        this.zza = list;
        this.zzb = zzhbx;
    }

    public final Object get(int i) {
        return this.zzb.zzb(this.zza.get(i));
    }

    public final int size() {
        return this.zza.size();
    }
}
