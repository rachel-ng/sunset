package com.google.android.gms.internal.ads;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
final class zzhcq extends zzhcs {
    private zzhcq() {
        throw null;
    }

    /* synthetic */ zzhcq(zzhcp zzhcp) {
        super((zzhcr) null);
    }

    /* access modifiers changed from: package-private */
    public final List zza(Object obj, long j) {
        zzhca zzhca = (zzhca) zzhfa.zzh(obj, j);
        if (zzhca.zzc()) {
            return zzhca;
        }
        int size = zzhca.size();
        zzhca zzf = zzhca.zzf(size == 0 ? 10 : size + size);
        zzhfa.zzv(obj, j, zzf);
        return zzf;
    }

    /* access modifiers changed from: package-private */
    public final void zzb(Object obj, long j) {
        ((zzhca) zzhfa.zzh(obj, j)).zzb();
    }

    /* access modifiers changed from: package-private */
    public final void zzc(Object obj, Object obj2, long j) {
        zzhca zzhca = (zzhca) zzhfa.zzh(obj, j);
        zzhca zzhca2 = (zzhca) zzhfa.zzh(obj2, j);
        int size = zzhca.size();
        int size2 = zzhca2.size();
        if (size > 0 && size2 > 0) {
            if (!zzhca.zzc()) {
                zzhca = zzhca.zzf(size2 + size);
            }
            zzhca.addAll(zzhca2);
        }
        if (size > 0) {
            zzhca2 = zzhca;
        }
        zzhfa.zzv(obj, j, zzhca2);
    }
}
