package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.pm.PackageManager;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import com.google.common.base.Optional;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zzgx {
    private static volatile Optional<Boolean> zza = Optional.absent();
    private static final Object zzb = new Object();

    private static boolean zza(Context context) {
        try {
            if ((context.getPackageManager().getApplicationInfo("com.google.android.gms", 0).flags & TsExtractor.TS_STREAM_TYPE_AC3) != 0) {
                return true;
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x007d, code lost:
        if ("com.google.android.gms".equals(r0.packageName) != false) goto L_0x007f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean zza(android.content.Context r5, android.net.Uri r6) {
        /*
            java.lang.String r6 = r6.getAuthority()
            java.lang.String r0 = "com.google.android.gms.phenotype"
            boolean r0 = r0.equals(r6)
            r1 = 0
            if (r0 != 0) goto L_0x0024
            java.lang.String r5 = "PhenotypeClientHelper"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r6)
            java.lang.String r6 = " is an unsupported authority. Only com.google.android.gms.phenotype authority is supported."
            r0.append(r6)
            java.lang.String r6 = r0.toString()
            android.util.Log.e(r5, r6)
            return r1
        L_0x0024:
            com.google.common.base.Optional<java.lang.Boolean> r6 = zza
            boolean r6 = r6.isPresent()
            if (r6 == 0) goto L_0x0039
            com.google.common.base.Optional<java.lang.Boolean> r5 = zza
            java.lang.Object r5 = r5.get()
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            return r5
        L_0x0039:
            java.lang.Object r6 = zzb
            monitor-enter(r6)
            com.google.common.base.Optional<java.lang.Boolean> r0 = zza     // Catch:{ all -> 0x009e }
            boolean r0 = r0.isPresent()     // Catch:{ all -> 0x009e }
            if (r0 == 0) goto L_0x0052
            com.google.common.base.Optional<java.lang.Boolean> r5 = zza     // Catch:{ all -> 0x009e }
            java.lang.Object r5 = r5.get()     // Catch:{ all -> 0x009e }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ all -> 0x009e }
            boolean r5 = r5.booleanValue()     // Catch:{ all -> 0x009e }
            monitor-exit(r6)     // Catch:{ all -> 0x009e }
            return r5
        L_0x0052:
            java.lang.String r0 = "com.google.android.gms"
            java.lang.String r2 = r5.getPackageName()     // Catch:{ all -> 0x009e }
            boolean r0 = r0.equals(r2)     // Catch:{ all -> 0x009e }
            if (r0 == 0) goto L_0x005f
            goto L_0x007f
        L_0x005f:
            android.content.pm.PackageManager r0 = r5.getPackageManager()     // Catch:{ all -> 0x009e }
            java.lang.String r2 = "com.google.android.gms.phenotype"
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x009e }
            r4 = 29
            if (r3 >= r4) goto L_0x006d
            r3 = r1
            goto L_0x006f
        L_0x006d:
            r3 = 268435456(0x10000000, float:2.5243549E-29)
        L_0x006f:
            android.content.pm.ProviderInfo r0 = r0.resolveContentProvider(r2, r3)     // Catch:{ all -> 0x009e }
            if (r0 == 0) goto L_0x0086
            java.lang.String r2 = "com.google.android.gms"
            java.lang.String r0 = r0.packageName     // Catch:{ all -> 0x009e }
            boolean r0 = r2.equals(r0)     // Catch:{ all -> 0x009e }
            if (r0 == 0) goto L_0x0086
        L_0x007f:
            boolean r5 = zza(r5)     // Catch:{ all -> 0x009e }
            if (r5 == 0) goto L_0x0086
            r1 = 1
        L_0x0086:
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r1)     // Catch:{ all -> 0x009e }
            com.google.common.base.Optional r5 = com.google.common.base.Optional.of(r5)     // Catch:{ all -> 0x009e }
            zza = r5     // Catch:{ all -> 0x009e }
            monitor-exit(r6)     // Catch:{ all -> 0x009e }
            com.google.common.base.Optional<java.lang.Boolean> r5 = zza
            java.lang.Object r5 = r5.get()
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            return r5
        L_0x009e:
            r5 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x009e }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzgx.zza(android.content.Context, android.net.Uri):boolean");
    }
}
