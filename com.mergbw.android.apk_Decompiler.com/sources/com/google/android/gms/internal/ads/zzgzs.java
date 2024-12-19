package com.google.android.gms.internal.ads;

import java.util.Comparator;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
final class zzgzs implements Comparator {
    zzgzs() {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzhac zzhac = (zzhac) obj;
        zzhac zzhac2 = (zzhac) obj2;
        zzgzv zzs = zzhac.iterator();
        zzgzv zzs2 = zzhac2.iterator();
        while (zzs.hasNext() && zzs2.hasNext()) {
            int compareTo = Integer.valueOf(zzs.zza() & 255).compareTo(Integer.valueOf(zzs2.zza() & 255));
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return Integer.valueOf(zzhac.zzd()).compareTo(Integer.valueOf(zzhac2.zzd()));
    }
}
