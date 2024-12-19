package com.google.android.gms.ads.internal.util.client;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public final class zzr implements zze {
    private final String zza;

    public zzr() {
        throw null;
    }

    public zzr(String str) {
        this.zza = str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0085, code lost:
        if (com.google.android.gms.common.util.ClientLibraryUtils.isPackageSide() == false) goto L_0x00d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00b2, code lost:
        if (com.google.android.gms.common.util.ClientLibraryUtils.isPackageSide() == false) goto L_0x00d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00d2, code lost:
        if (com.google.android.gms.common.util.ClientLibraryUtils.isPackageSide() == false) goto L_0x00d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00d4, code lost:
        android.net.TrafficStats.clearThreadStatsTag();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00d7, code lost:
        return r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zza(java.lang.String r10) {
        /*
            r9 = this;
            java.lang.String r0 = ". "
            java.lang.String r1 = "Received non-success response code "
            java.lang.String r2 = "Error while pinging URL: "
            java.lang.String r3 = "Error while parsing ping URL: "
            java.lang.String r4 = "Pinging URL: "
            r5 = 0
            boolean r6 = com.google.android.gms.common.util.ClientLibraryUtils.isPackageSide()     // Catch:{ IndexOutOfBoundsException -> 0x008c, IOException -> 0x008a, RuntimeException -> 0x0088 }
            if (r6 == 0) goto L_0x0016
            r6 = 263(0x107, float:3.69E-43)
            android.net.TrafficStats.setThreadStatsTag(r6)     // Catch:{ IndexOutOfBoundsException -> 0x008c, IOException -> 0x008a, RuntimeException -> 0x0088 }
        L_0x0016:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ IndexOutOfBoundsException -> 0x008c, IOException -> 0x008a, RuntimeException -> 0x0088 }
            r6.<init>(r4)     // Catch:{ IndexOutOfBoundsException -> 0x008c, IOException -> 0x008a, RuntimeException -> 0x0088 }
            r6.append(r10)     // Catch:{ IndexOutOfBoundsException -> 0x008c, IOException -> 0x008a, RuntimeException -> 0x0088 }
            java.lang.String r4 = r6.toString()     // Catch:{ IndexOutOfBoundsException -> 0x008c, IOException -> 0x008a, RuntimeException -> 0x0088 }
            com.google.android.gms.ads.internal.util.client.zzm.zze(r4)     // Catch:{ IndexOutOfBoundsException -> 0x008c, IOException -> 0x008a, RuntimeException -> 0x0088 }
            java.net.URL r4 = new java.net.URL     // Catch:{ IndexOutOfBoundsException -> 0x008c, IOException -> 0x008a, RuntimeException -> 0x0088 }
            r4.<init>(r10)     // Catch:{ IndexOutOfBoundsException -> 0x008c, IOException -> 0x008a, RuntimeException -> 0x0088 }
            java.net.URLConnection r4 = r4.openConnection()     // Catch:{ IndexOutOfBoundsException -> 0x008c, IOException -> 0x008a, RuntimeException -> 0x0088 }
            java.net.HttpURLConnection r4 = (java.net.HttpURLConnection) r4     // Catch:{ IndexOutOfBoundsException -> 0x008c, IOException -> 0x008a, RuntimeException -> 0x0088 }
            com.google.android.gms.ads.internal.client.zzay.zzb()     // Catch:{ all -> 0x008e }
            java.lang.String r6 = r9.zza     // Catch:{ all -> 0x008e }
            r7 = 60000(0xea60, float:8.4078E-41)
            r4.setConnectTimeout(r7)     // Catch:{ all -> 0x008e }
            r8 = 1
            r4.setInstanceFollowRedirects(r8)     // Catch:{ all -> 0x008e }
            r4.setReadTimeout(r7)     // Catch:{ all -> 0x008e }
            if (r6 == 0) goto L_0x0049
            java.lang.String r7 = "User-Agent"
            r4.setRequestProperty(r7, r6)     // Catch:{ all -> 0x008e }
        L_0x0049:
            r4.setUseCaches(r5)     // Catch:{ all -> 0x008e }
            com.google.android.gms.ads.internal.util.client.zzl r6 = new com.google.android.gms.ads.internal.util.client.zzl     // Catch:{ all -> 0x008e }
            r7 = 0
            r6.<init>(r7)     // Catch:{ all -> 0x008e }
            r6.zzc(r4, r7)     // Catch:{ all -> 0x008e }
            int r7 = r4.getResponseCode()     // Catch:{ all -> 0x008e }
            r6.zze(r4, r7)     // Catch:{ all -> 0x008e }
            r6 = 200(0xc8, float:2.8E-43)
            if (r7 < r6) goto L_0x0067
            r6 = 300(0x12c, float:4.2E-43)
            if (r7 < r6) goto L_0x0065
            goto L_0x0067
        L_0x0065:
            r5 = r8
            goto L_0x007e
        L_0x0067:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x008e }
            r6.<init>(r1)     // Catch:{ all -> 0x008e }
            r6.append(r7)     // Catch:{ all -> 0x008e }
            java.lang.String r1 = " from pinging URL: "
            r6.append(r1)     // Catch:{ all -> 0x008e }
            r6.append(r10)     // Catch:{ all -> 0x008e }
            java.lang.String r1 = r6.toString()     // Catch:{ all -> 0x008e }
            com.google.android.gms.ads.internal.util.client.zzm.zzj(r1)     // Catch:{ all -> 0x008e }
        L_0x007e:
            r4.disconnect()     // Catch:{ IndexOutOfBoundsException -> 0x008c, IOException -> 0x008a, RuntimeException -> 0x0088 }
            boolean r10 = com.google.android.gms.common.util.ClientLibraryUtils.isPackageSide()
            if (r10 == 0) goto L_0x00d7
            goto L_0x00d4
        L_0x0088:
            r1 = move-exception
            goto L_0x0095
        L_0x008a:
            r1 = move-exception
            goto L_0x0095
        L_0x008c:
            r1 = move-exception
            goto L_0x00b5
        L_0x008e:
            r1 = move-exception
            r4.disconnect()     // Catch:{ IndexOutOfBoundsException -> 0x008c, IOException -> 0x008a, RuntimeException -> 0x0088 }
            throw r1     // Catch:{ IndexOutOfBoundsException -> 0x008c, IOException -> 0x008a, RuntimeException -> 0x0088 }
        L_0x0093:
            r10 = move-exception
            goto L_0x00d8
        L_0x0095:
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x0093 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0093 }
            r3.<init>(r2)     // Catch:{ all -> 0x0093 }
            r3.append(r10)     // Catch:{ all -> 0x0093 }
            r3.append(r0)     // Catch:{ all -> 0x0093 }
            r3.append(r1)     // Catch:{ all -> 0x0093 }
            java.lang.String r10 = r3.toString()     // Catch:{ all -> 0x0093 }
            com.google.android.gms.ads.internal.util.client.zzm.zzj(r10)     // Catch:{ all -> 0x0093 }
            boolean r10 = com.google.android.gms.common.util.ClientLibraryUtils.isPackageSide()
            if (r10 == 0) goto L_0x00d7
            goto L_0x00d4
        L_0x00b5:
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x0093 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0093 }
            r2.<init>(r3)     // Catch:{ all -> 0x0093 }
            r2.append(r10)     // Catch:{ all -> 0x0093 }
            r2.append(r0)     // Catch:{ all -> 0x0093 }
            r2.append(r1)     // Catch:{ all -> 0x0093 }
            java.lang.String r10 = r2.toString()     // Catch:{ all -> 0x0093 }
            com.google.android.gms.ads.internal.util.client.zzm.zzj(r10)     // Catch:{ all -> 0x0093 }
            boolean r10 = com.google.android.gms.common.util.ClientLibraryUtils.isPackageSide()
            if (r10 == 0) goto L_0x00d7
        L_0x00d4:
            android.net.TrafficStats.clearThreadStatsTag()
        L_0x00d7:
            return r5
        L_0x00d8:
            boolean r0 = com.google.android.gms.common.util.ClientLibraryUtils.isPackageSide()
            if (r0 == 0) goto L_0x00e1
            android.net.TrafficStats.clearThreadStatsTag()
        L_0x00e1:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.util.client.zzr.zza(java.lang.String):boolean");
    }
}
