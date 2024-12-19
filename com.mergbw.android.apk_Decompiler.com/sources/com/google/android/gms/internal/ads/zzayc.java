package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzayc implements Runnable {
    final /* synthetic */ int zza;
    final /* synthetic */ zzaye zzb;

    zzayc(zzaye zzaye, int i, boolean z) {
        this.zza = i;
        this.zzb = zzaye;
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x000c */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r4 = this;
            int r0 = r4.zza
            com.google.android.gms.internal.ads.zzaye r1 = r4.zzb
            if (r0 <= 0) goto L_0x000c
            int r0 = r0 * 1000
            long r2 = (long) r0
            java.lang.Thread.sleep(r2)     // Catch:{ InterruptedException -> 0x000c }
        L_0x000c:
            android.content.Context r0 = r1.zza     // Catch:{ all -> 0x002e }
            android.content.pm.PackageManager r0 = r0.getPackageManager()     // Catch:{ all -> 0x002e }
            android.content.Context r2 = r1.zza     // Catch:{ all -> 0x002e }
            java.lang.String r2 = r2.getPackageName()     // Catch:{ all -> 0x002e }
            r3 = 0
            android.content.pm.PackageInfo r0 = r0.getPackageInfo(r2, r3)     // Catch:{ all -> 0x002e }
            android.content.Context r1 = r1.zza     // Catch:{ all -> 0x002e }
            java.lang.String r2 = r1.getPackageName()     // Catch:{ all -> 0x002e }
            int r0 = r0.versionCode     // Catch:{ all -> 0x002e }
            java.lang.String r0 = java.lang.Integer.toString(r0)     // Catch:{ all -> 0x002e }
            com.google.android.gms.internal.ads.zzaus r0 = com.google.android.gms.internal.ads.zzfrm.zza(r1, r2, r0)     // Catch:{ all -> 0x002e }
            goto L_0x002f
        L_0x002e:
            r0 = 0
        L_0x002f:
            com.google.android.gms.internal.ads.zzaye r1 = r4.zzb
            r1.zzm = r0
            int r1 = r4.zza
            r2 = 4
            if (r1 >= r2) goto L_0x0076
            if (r0 != 0) goto L_0x003c
            goto L_0x006d
        L_0x003c:
            boolean r1 = r0.zzar()
            if (r1 == 0) goto L_0x006d
            java.lang.String r1 = r0.zzi()
            java.lang.String r2 = "0000000000000000000000000000000000000000000000000000000000000000"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x006d
            boolean r1 = r0.zzas()
            if (r1 == 0) goto L_0x006d
            com.google.android.gms.internal.ads.zzavh r1 = r0.zzg()
            boolean r1 = r1.zze()
            if (r1 == 0) goto L_0x006d
            com.google.android.gms.internal.ads.zzavh r0 = r0.zzg()
            long r0 = r0.zza()
            r2 = -2
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto L_0x006d
            goto L_0x0076
        L_0x006d:
            com.google.android.gms.internal.ads.zzaye r0 = r4.zzb
            int r1 = r4.zza
            r2 = 1
            int r1 = r1 + r2
            r0.zzo(r1, r2)
        L_0x0076:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzayc.run():void");
    }
}
