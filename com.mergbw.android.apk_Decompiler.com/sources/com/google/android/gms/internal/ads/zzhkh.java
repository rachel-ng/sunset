package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public abstract class zzhkh {
    public static zzhkh zzb(Class cls) {
        if (System.getProperty("java.vm.name").equalsIgnoreCase("Dalvik")) {
            return new zzhkc(cls.getSimpleName());
        }
        return new zzhke(cls.getSimpleName());
    }

    public abstract void zza(String str);
}
