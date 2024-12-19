package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzhld implements zzhkp {
    private static final zzhkp zza = zzhkq.zza(Collections.emptySet());
    private final List zzb;
    private final List zzc;

    /* synthetic */ zzhld(List list, List list2, zzhlb zzhlb) {
        this.zzb = list;
        this.zzc = list2;
    }

    public static zzhlc zza(int i, int i2) {
        return new zzhlc(i, i2, (zzhlb) null);
    }

    /* renamed from: zzc */
    public final Set zzb() {
        int size = this.zzb.size();
        ArrayList arrayList = new ArrayList(this.zzc.size());
        int size2 = this.zzc.size();
        for (int i = 0; i < size2; i++) {
            Collection collection = (Collection) ((zzhky) this.zzc.get(i)).zzb();
            size += collection.size();
            arrayList.add(collection);
        }
        HashSet zza2 = zzhkm.zza(size);
        int size3 = this.zzb.size();
        for (int i2 = 0; i2 < size3; i2++) {
            Object zzb2 = ((zzhky) this.zzb.get(i2)).zzb();
            zzb2.getClass();
            zza2.add(zzb2);
        }
        int size4 = arrayList.size();
        for (int i3 = 0; i3 < size4; i3++) {
            for (Object next : (Collection) arrayList.get(i3)) {
                next.getClass();
                zza2.add(next);
            }
        }
        return Collections.unmodifiableSet(zza2);
    }
}
