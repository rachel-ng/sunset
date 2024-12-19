package com.google.android.gms.internal.ads;

import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfqg {
    private final zzfph zza;
    private final ArrayList zzb;

    public zzfqg(zzfph zzfph, String str) {
        ArrayList arrayList = new ArrayList();
        this.zzb = arrayList;
        this.zza = zzfph;
        arrayList.add(str);
    }

    public final zzfph zza() {
        return this.zza;
    }

    public final ArrayList zzb() {
        return this.zzb;
    }

    public final void zzc(String str) {
        this.zzb.add(str);
    }
}
