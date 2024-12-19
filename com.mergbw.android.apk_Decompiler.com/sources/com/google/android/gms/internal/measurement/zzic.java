package com.google.android.gms.internal.measurement;

import java.util.Comparator;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
final class zzic implements Comparator<zzia> {
    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzia zzia = (zzia) obj;
        zzia zzia2 = (zzia) obj2;
        zzig zzig = (zzig) zzia.iterator();
        zzig zzig2 = (zzig) zzia2.iterator();
        while (zzig.hasNext() && zzig2.hasNext()) {
            int compareTo = Integer.valueOf(zzia.zza(zzig.zza())).compareTo(Integer.valueOf(zzia.zza(zzig2.zza())));
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return Integer.valueOf(zzia.zzb()).compareTo(Integer.valueOf(zzia2.zzb()));
    }

    zzic() {
    }
}
