package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgbs {
    public static ArrayList zza(int i) {
        zzfzz.zza(i, "initialArraySize");
        return new ArrayList(i);
    }

    public static List zzb(List list, zzfxu zzfxu) {
        if (list instanceof RandomAccess) {
            return new zzgbp(list, zzfxu);
        }
        return new zzgbr(list, zzfxu);
    }
}
