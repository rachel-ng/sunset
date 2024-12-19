package com.google.android.gms.measurement.internal;

import java.util.HashMap;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
public final /* synthetic */ class zzgv implements Callable {
    private /* synthetic */ zzgt zza;
    private /* synthetic */ String zzb;

    public /* synthetic */ zzgv(zzgt zzgt, String str) {
        this.zza = zzgt;
        this.zzb = str;
    }

    public final Object call() {
        zzgt zzgt = this.zza;
        String str = this.zzb;
        zzg zze = zzgt.zzh().zze(str);
        HashMap hashMap = new HashMap();
        hashMap.put("platform", "android");
        hashMap.put("package_name", str);
        hashMap.put("gmp_version", 97001L);
        if (zze != null) {
            String zzaf = zze.zzaf();
            if (zzaf != null) {
                hashMap.put("app_version", zzaf);
            }
            hashMap.put("app_version_int", Long.valueOf(zze.zze()));
            hashMap.put("dynamite_version", Long.valueOf(zze.zzo()));
        }
        return hashMap;
    }
}
