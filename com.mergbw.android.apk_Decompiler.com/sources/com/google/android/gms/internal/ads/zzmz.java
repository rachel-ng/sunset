package com.google.android.gms.internal.ads;

import android.util.SparseArray;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzmz {
    private final zzah zza;
    private final SparseArray zzb;

    public zzmz(zzah zzah, SparseArray sparseArray) {
        this.zza = zzah;
        SparseArray sparseArray2 = new SparseArray(zzah.zzb());
        for (int i = 0; i < zzah.zzb(); i++) {
            int zza2 = zzah.zza(i);
            zzmy zzmy = (zzmy) sparseArray.get(zza2);
            zzmy.getClass();
            sparseArray2.append(zza2, zzmy);
        }
        this.zzb = sparseArray2;
    }

    public final int zza(int i) {
        return this.zza.zza(i);
    }

    public final int zzb() {
        return this.zza.zzb();
    }

    public final zzmy zzc(int i) {
        zzmy zzmy = (zzmy) this.zzb.get(i);
        zzmy.getClass();
        return zzmy;
    }

    public final boolean zzd(int i) {
        return this.zza.zzc(i);
    }
}
