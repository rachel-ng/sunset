package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzere implements zzexw {
    private final zzgge zza;
    private final zzdst zzb;
    private final zzdxf zzc;
    private final zzerg zzd;

    public zzere(zzgge zzgge, zzdst zzdst, zzdxf zzdxf, zzerg zzerg) {
        this.zza = zzgge;
        this.zzb = zzdst;
        this.zzc = zzdxf;
        this.zzd = zzerg;
    }

    public final int zza() {
        return 1;
    }

    public final ListenableFuture zzb() {
        if (!((Boolean) zzba.zzc().zza(zzbep.zzlH)).booleanValue() || this.zzd.zza() == null) {
            if (!zzfyv.zzd((String) zzba.zzc().zza(zzbep.zzbs))) {
                if (((Boolean) zzba.zzc().zza(zzbep.zzlH)).booleanValue() || (!this.zzd.zzd() && this.zzc.zzt())) {
                    this.zzd.zzc(true);
                    return this.zza.zzb(new zzerd(this));
                }
            }
            return zzgft.zzh(new zzerf(new Bundle()));
        }
        zzerf zza2 = this.zzd.zza();
        zza2.getClass();
        return zzgft.zzh(zza2);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't wrap try/catch for region: R(12:4|5|6|(3:9|10|(1:12))|13|14|(1:16)|17|18|25|23|1) */
    /* JADX WARNING: Can't wrap try/catch for region: R(9:5|6|(3:9|10|(1:12))|13|14|(1:16)|17|18|25) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0067 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0076 */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x006d A[Catch:{ zzfhv -> 0x0076 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ com.google.android.gms.internal.ads.zzerf zzc() throws java.lang.Exception {
        /*
            r8 = this;
            com.google.android.gms.internal.ads.zzbeg r0 = com.google.android.gms.internal.ads.zzbep.zzbs
            com.google.android.gms.internal.ads.zzben r1 = com.google.android.gms.ads.internal.client.zzba.zzc()
            java.lang.Object r0 = r1.zza(r0)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r1 = ";"
            java.lang.String[] r0 = r0.split(r1)
            java.util.List r0 = java.util.Arrays.asList(r0)
            android.os.Bundle r1 = new android.os.Bundle
            r1.<init>()
            java.util.Iterator r0 = r0.iterator()
        L_0x001f:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x007a
            java.lang.Object r2 = r0.next()
            java.lang.String r2 = (java.lang.String) r2
            com.google.android.gms.internal.ads.zzdst r3 = r8.zzb     // Catch:{ zzfhv -> 0x001f }
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ zzfhv -> 0x001f }
            r4.<init>()     // Catch:{ zzfhv -> 0x001f }
            com.google.android.gms.internal.ads.zzfim r3 = r3.zzc(r2, r4)     // Catch:{ zzfhv -> 0x001f }
            r3.zzC()     // Catch:{ zzfhv -> 0x001f }
            com.google.android.gms.internal.ads.zzdxf r4 = r8.zzc     // Catch:{ zzfhv -> 0x001f }
            boolean r4 = r4.zzt()     // Catch:{ zzfhv -> 0x001f }
            android.os.Bundle r5 = new android.os.Bundle     // Catch:{ zzfhv -> 0x001f }
            r5.<init>()     // Catch:{ zzfhv -> 0x001f }
            com.google.android.gms.internal.ads.zzbeg r6 = com.google.android.gms.internal.ads.zzbep.zzlH     // Catch:{ zzfhv -> 0x001f }
            com.google.android.gms.internal.ads.zzben r7 = com.google.android.gms.ads.internal.client.zzba.zzc()     // Catch:{ zzfhv -> 0x001f }
            java.lang.Object r6 = r7.zza(r6)     // Catch:{ zzfhv -> 0x001f }
            java.lang.Boolean r6 = (java.lang.Boolean) r6     // Catch:{ zzfhv -> 0x001f }
            boolean r6 = r6.booleanValue()     // Catch:{ zzfhv -> 0x001f }
            if (r6 == 0) goto L_0x0058
            if (r4 == 0) goto L_0x0067
        L_0x0058:
            com.google.android.gms.internal.ads.zzbtt r4 = r3.zzf()     // Catch:{ zzfhv -> 0x0067 }
            if (r4 == 0) goto L_0x0067
            java.lang.String r6 = "sdk_version"
            java.lang.String r4 = r4.toString()     // Catch:{ zzfhv -> 0x0067 }
            r5.putString(r6, r4)     // Catch:{ zzfhv -> 0x0067 }
        L_0x0067:
            com.google.android.gms.internal.ads.zzbtt r3 = r3.zze()     // Catch:{ zzfhv -> 0x0076 }
            if (r3 == 0) goto L_0x0076
            java.lang.String r4 = "adapter_version"
            java.lang.String r3 = r3.toString()     // Catch:{ zzfhv -> 0x0076 }
            r5.putString(r4, r3)     // Catch:{ zzfhv -> 0x0076 }
        L_0x0076:
            r1.putBundle(r2, r5)     // Catch:{ zzfhv -> 0x001f }
            goto L_0x001f
        L_0x007a:
            com.google.android.gms.internal.ads.zzerf r0 = new com.google.android.gms.internal.ads.zzerf
            r0.<init>(r1)
            com.google.android.gms.internal.ads.zzbeg r1 = com.google.android.gms.internal.ads.zzbep.zzlH
            com.google.android.gms.internal.ads.zzben r2 = com.google.android.gms.ads.internal.client.zzba.zzc()
            java.lang.Object r1 = r2.zza(r1)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x0096
            com.google.android.gms.internal.ads.zzerg r1 = r8.zzd
            r1.zzb(r0)
        L_0x0096:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzere.zzc():com.google.android.gms.internal.ads.zzerf");
    }
}
