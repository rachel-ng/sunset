package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
final class zzhcz {
    zzhcz() {
    }

    public static final boolean zza(Object obj) {
        return !((zzhcy) obj).zze();
    }

    public static final Object zzb(Object obj, Object obj2) {
        zzhcy zzhcy = (zzhcy) obj;
        zzhcy zzhcy2 = (zzhcy) obj2;
        if (!zzhcy2.isEmpty()) {
            if (!zzhcy.zze()) {
                zzhcy = zzhcy.zzb();
            }
            zzhcy.zzd(zzhcy2);
        }
        return zzhcy;
    }
}
