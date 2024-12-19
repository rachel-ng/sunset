package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
final class zzkp implements zzkm {
    public final int zza(int i, Object obj, Object obj2) {
        zzkn zzkn = (zzkn) obj;
        zzkl zzkl = (zzkl) obj2;
        if (zzkn.isEmpty()) {
            return 0;
        }
        Iterator it = zzkn.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Map.Entry entry = (Map.Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw new NoSuchMethodError();
    }

    public final zzkk<?, ?> zza(Object obj) {
        zzkl zzkl = (zzkl) obj;
        throw new NoSuchMethodError();
    }

    public final Object zza(Object obj, Object obj2) {
        zzkn zzkn = (zzkn) obj;
        zzkn zzkn2 = (zzkn) obj2;
        if (!zzkn2.isEmpty()) {
            if (!zzkn.zzd()) {
                zzkn = zzkn.zzb();
            }
            zzkn.zza(zzkn2);
        }
        return zzkn;
    }

    public final Object zzb(Object obj) {
        return zzkn.zza().zzb();
    }

    public final Object zzc(Object obj) {
        ((zzkn) obj).zzc();
        return obj;
    }

    public final Map<?, ?> zzd(Object obj) {
        return (zzkn) obj;
    }

    public final Map<?, ?> zze(Object obj) {
        return (zzkn) obj;
    }

    zzkp() {
    }

    public final boolean zzf(Object obj) {
        return !((zzkn) obj).zzd();
    }
}
