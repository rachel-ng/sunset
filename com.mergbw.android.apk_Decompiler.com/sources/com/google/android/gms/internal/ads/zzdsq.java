package com.google.android.gms.internal.ads;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzdsq {
    private final Map zza = new HashMap();

    zzdsq() {
    }

    @Nullable
    public final synchronized zzdsp zza(String str) {
        return (zzdsp) this.zza.get(str);
    }

    public final String zzb(String str) {
        zzbtt zzbtt;
        zzdsp zza2 = zza(str);
        if (zza2 == null || (zzbtt = zza2.zzb) == null) {
            return "";
        }
        return zzbtt.toString();
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't wrap try/catch for region: R(14:6|7|8|(11:12|13|(2:15|16)|17|18|19|(3:21|(2:25|26)|23)|27|28|29|30)|10|(0)|17|18|19|(0)|27|28|29|30) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x001d */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0019 A[SYNTHETIC, Splitter:B:15:0x0019] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0031  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void zzc(java.lang.String r6, @javax.annotation.Nullable com.google.android.gms.internal.ads.zzfim r7) {
        /*
            r5 = this;
            monitor-enter(r5)
            java.util.Map r0 = r5.zza     // Catch:{ all -> 0x0043 }
            boolean r0 = r0.containsKey(r6)     // Catch:{ all -> 0x0043 }
            if (r0 == 0) goto L_0x000b
            monitor-exit(r5)
            return
        L_0x000b:
            com.google.android.gms.internal.ads.zzdsp r0 = new com.google.android.gms.internal.ads.zzdsp     // Catch:{ all -> 0x0043 }
            r1 = 0
            if (r7 != 0) goto L_0x0012
        L_0x0010:
            r2 = r1
            goto L_0x0016
        L_0x0012:
            com.google.android.gms.internal.ads.zzbtt r2 = r7.zze()     // Catch:{ zzfhv -> 0x0010 }
        L_0x0016:
            if (r7 != 0) goto L_0x0019
            goto L_0x001d
        L_0x0019:
            com.google.android.gms.internal.ads.zzbtt r1 = r7.zzf()     // Catch:{ zzfhv -> 0x001d }
        L_0x001d:
            com.google.android.gms.internal.ads.zzbeg r3 = com.google.android.gms.internal.ads.zzbep.zzjr     // Catch:{ all -> 0x0043 }
            com.google.android.gms.internal.ads.zzben r4 = com.google.android.gms.ads.internal.client.zzba.zzc()     // Catch:{ all -> 0x0043 }
            java.lang.Object r3 = r4.zza(r3)     // Catch:{ all -> 0x0043 }
            java.lang.Boolean r3 = (java.lang.Boolean) r3     // Catch:{ all -> 0x0043 }
            boolean r3 = r3.booleanValue()     // Catch:{ all -> 0x0043 }
            r4 = 1
            if (r3 != 0) goto L_0x0031
            goto L_0x0039
        L_0x0031:
            r3 = 0
            if (r7 != 0) goto L_0x0036
        L_0x0034:
            r4 = r3
            goto L_0x0039
        L_0x0036:
            r7.zzC()     // Catch:{ zzfhv -> 0x0034 }
        L_0x0039:
            r0.<init>(r6, r2, r1, r4)     // Catch:{ all -> 0x0043 }
            java.util.Map r7 = r5.zza     // Catch:{ all -> 0x0043 }
            r7.put(r6, r0)     // Catch:{ all -> 0x0043 }
            monitor-exit(r5)
            return
        L_0x0043:
            r6 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x0043 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzdsq.zzc(java.lang.String, com.google.android.gms.internal.ads.zzfim):void");
    }

    /* access modifiers changed from: package-private */
    public final synchronized void zzd(String str, zzbte zzbte) {
        if (!this.zza.containsKey(str)) {
            try {
                this.zza.put(str, new zzdsp(str, zzbte.zzf(), zzbte.zzg(), true));
            } catch (Throwable unused) {
            }
        }
    }
}
