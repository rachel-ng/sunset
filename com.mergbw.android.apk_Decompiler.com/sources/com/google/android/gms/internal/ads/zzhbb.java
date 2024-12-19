package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
final class zzhbb {
    private static final zzhaz zza = new zzhba();
    private static final zzhaz zzb;

    static {
        zzhaz zzhaz = null;
        try {
            zzhaz = (zzhaz) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor((Class[]) null).newInstance((Object[]) null);
        } catch (Exception unused) {
        }
        zzb = zzhaz;
    }

    static zzhaz zza() {
        zzhaz zzhaz = zzb;
        if (zzhaz != null) {
            return zzhaz;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }

    static zzhaz zzb() {
        return zza;
    }
}
