package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzhde;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public final class zzhbm<ContainingType extends zzhde, Type> extends zzhaw<ContainingType, Type> {
    final zzhde zza;
    final Object zzb;
    final zzhde zzc;
    final zzhbl zzd;

    zzhbm(zzhde zzhde, Object obj, zzhde zzhde2, zzhbl zzhbl, Class cls) {
        if (zzhde == null) {
            throw new IllegalArgumentException("Null containingTypeDefaultInstance");
        } else if (zzhbl.zzc == zzhfg.MESSAGE && zzhde2 == null) {
            throw new IllegalArgumentException("Null messageDefaultInstance");
        } else {
            this.zza = zzhde;
            this.zzb = obj;
            this.zzc = zzhde2;
            this.zzd = zzhbl;
        }
    }
}
