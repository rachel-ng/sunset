package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
final class zzhbh implements zzhdc {
    private static final zzhbh zza = new zzhbh();

    private zzhbh() {
    }

    public static zzhbh zza() {
        return zza;
    }

    public final zzhdb zzb(Class cls) {
        if (zzhbo.class.isAssignableFrom(cls)) {
            try {
                return (zzhdb) zzhbo.zzbh(cls.asSubclass(zzhbo.class)).zzbN();
            } catch (Exception e) {
                throw new RuntimeException("Unable to get message info for ".concat(String.valueOf(cls.getName())), e);
            }
        } else {
            throw new IllegalArgumentException("Unsupported message type: ".concat(String.valueOf(cls.getName())));
        }
    }

    public final boolean zzc(Class cls) {
        return zzhbo.class.isAssignableFrom(cls);
    }
}
