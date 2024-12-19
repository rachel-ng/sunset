package com.google.android.gms.internal.ads;

import java.util.Comparator;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public abstract class zzgcn implements Comparator {
    protected zzgcn() {
    }

    public static zzgcn zzb(Comparator comparator) {
        return new zzgan(comparator);
    }

    public static zzgcn zzc() {
        return zzgcl.zza;
    }

    public abstract int compare(Object obj, Object obj2);

    public zzgcn zza() {
        return new zzgcw(this);
    }
}
