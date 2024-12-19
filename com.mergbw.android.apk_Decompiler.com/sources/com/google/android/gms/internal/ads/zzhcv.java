package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
final class zzhcv implements zzhdc {
    private final zzhdc[] zza;

    zzhcv(zzhdc... zzhdcArr) {
        this.zza = zzhdcArr;
    }

    public final zzhdb zzb(Class cls) {
        for (int i = 0; i < 2; i++) {
            zzhdc zzhdc = this.zza[i];
            if (zzhdc.zzc(cls)) {
                return zzhdc.zzb(cls);
            }
        }
        throw new UnsupportedOperationException("No factory is available for message type: ".concat(String.valueOf(cls.getName())));
    }

    public final boolean zzc(Class cls) {
        for (int i = 0; i < 2; i++) {
            if (this.zza[i].zzc(cls)) {
                return true;
            }
        }
        return false;
    }
}
