package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzeee implements zzfkw {
    protected final Context zza;
    protected final String zzb;
    protected final zzbyd zzc;

    public zzeee(Context context, String str, zzbyd zzbyd, int i) {
        this.zza = context;
        this.zzb = str;
        this.zzc = zzbyd;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: java.io.InputStreamReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v6, resolved type: java.io.InputStreamReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v7, resolved type: java.io.InputStreamReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v8, resolved type: java.io.InputStreamReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v15, resolved type: java.io.BufferedOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v9, resolved type: java.io.InputStreamReader} */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        r2 = new java.io.InputStreamReader(r3.getInputStream());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        com.google.android.gms.ads.internal.zzu.zzp();
        r0 = com.google.android.gms.ads.internal.util.zzt.zzN(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
        com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r2);
        r6.zzg(r0);
        r9.zzc = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0133, code lost:
        if (android.text.TextUtils.isEmpty(r0) == false) goto L_0x014f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0145, code lost:
        if (((java.lang.Boolean) com.google.android.gms.ads.internal.client.zzba.zzc().zza(com.google.android.gms.internal.ads.zzbep.zzfB)).booleanValue() == false) goto L_0x0148;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x014e, code lost:
        throw new com.google.android.gms.internal.ads.zzdzd(3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x014f, code lost:
        r9.zzd = com.google.android.gms.ads.internal.zzu.zzB().elapsedRealtime() - r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0160, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0161, code lost:
        r7 = r2;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x01fb A[Catch:{ all -> 0x020b }] */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x020a A[Catch:{ all -> 0x020b }] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:56:0x015b=Splitter:B:56:0x015b, B:96:0x020c=Splitter:B:96:0x020c} */
    /* renamed from: zzb */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.ads.zzeed zza(com.google.android.gms.internal.ads.zzeec r21) throws com.google.android.gms.internal.ads.zzdzd {
        /*
            r20 = this;
            r1 = r20
            r0 = r21
            java.lang.String r2 = "Received error HTTP response code: "
            java.lang.String r3 = "AdRequestServiceImpl: Sending request: "
            java.lang.String r4 = "SDK version: "
            java.lang.String r5 = r0.zza
            int r13 = r0.zzb
            java.util.Map r14 = r0.zzc
            byte[] r15 = r0.zzd
            java.lang.String r0 = r0.zze
            com.google.android.gms.common.util.Clock r6 = com.google.android.gms.ads.internal.zzu.zzB()
            long r16 = r6.elapsedRealtime()
            r12 = 1
            com.google.android.gms.internal.ads.zzeed r11 = new com.google.android.gms.internal.ads.zzeed     // Catch:{ IOException -> 0x0212 }
            r11.<init>()     // Catch:{ IOException -> 0x0212 }
            java.lang.String r6 = r1.zzb     // Catch:{ IOException -> 0x0212 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0212 }
            r7.<init>(r4)     // Catch:{ IOException -> 0x0212 }
            r7.append(r6)     // Catch:{ IOException -> 0x0212 }
            java.lang.String r4 = r7.toString()     // Catch:{ IOException -> 0x0212 }
            com.google.android.gms.ads.internal.util.client.zzm.zzi(r4)     // Catch:{ IOException -> 0x0212 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0212 }
            r4.<init>(r3)     // Catch:{ IOException -> 0x0212 }
            r4.append(r5)     // Catch:{ IOException -> 0x0212 }
            java.lang.String r3 = r4.toString()     // Catch:{ IOException -> 0x0212 }
            com.google.android.gms.ads.internal.util.client.zzm.zze(r3)     // Catch:{ IOException -> 0x0212 }
            java.net.URL r3 = new java.net.URL     // Catch:{ IOException -> 0x0212 }
            r3.<init>(r5)     // Catch:{ IOException -> 0x0212 }
            java.util.HashMap r4 = new java.util.HashMap     // Catch:{ IOException -> 0x0212 }
            r4.<init>()     // Catch:{ IOException -> 0x0212 }
            r5 = 0
        L_0x004d:
            java.net.URLConnection r3 = r3.openConnection()     // Catch:{ IOException -> 0x0212 }
            java.net.HttpURLConnection r3 = (java.net.HttpURLConnection) r3     // Catch:{ IOException -> 0x0212 }
            com.google.android.gms.ads.internal.util.zzt r6 = com.google.android.gms.ads.internal.zzu.zzp()     // Catch:{ zzdzd -> 0x01e6, all -> 0x01e3 }
            android.content.Context r7 = r1.zza     // Catch:{ zzdzd -> 0x01e6, all -> 0x01e3 }
            java.lang.String r8 = r1.zzb     // Catch:{ zzdzd -> 0x01e6, all -> 0x01e3 }
            r9 = 0
            r18 = 0
            r10 = r3
            r19 = r11
            r11 = r18
            r1 = r12
            r12 = r13
            r6.zzf(r7, r8, r9, r10, r11, r12)     // Catch:{ zzdzd -> 0x01df }
            java.util.Set r6 = r14.entrySet()     // Catch:{ zzdzd -> 0x01df }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ zzdzd -> 0x01df }
        L_0x0070:
            boolean r7 = r6.hasNext()     // Catch:{ zzdzd -> 0x01df }
            if (r7 == 0) goto L_0x008c
            java.lang.Object r7 = r6.next()     // Catch:{ zzdzd -> 0x01df }
            java.util.Map$Entry r7 = (java.util.Map.Entry) r7     // Catch:{ zzdzd -> 0x01df }
            java.lang.Object r8 = r7.getKey()     // Catch:{ zzdzd -> 0x01df }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ zzdzd -> 0x01df }
            java.lang.Object r7 = r7.getValue()     // Catch:{ zzdzd -> 0x01df }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ zzdzd -> 0x01df }
            r3.addRequestProperty(r8, r7)     // Catch:{ zzdzd -> 0x01df }
            goto L_0x0070
        L_0x008c:
            boolean r6 = android.text.TextUtils.isEmpty(r0)     // Catch:{ zzdzd -> 0x01df }
            if (r6 != 0) goto L_0x0097
            java.lang.String r6 = "Content-Type"
            r3.setRequestProperty(r6, r0)     // Catch:{ zzdzd -> 0x01df }
        L_0x0097:
            int r6 = r15.length     // Catch:{ zzdzd -> 0x01df }
            r7 = 0
            if (r6 <= 0) goto L_0x00b9
            r3.setDoOutput(r1)     // Catch:{ zzdzd -> 0x01df }
            r3.setFixedLengthStreamingMode(r6)     // Catch:{ zzdzd -> 0x01df }
            java.io.BufferedOutputStream r6 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x00b4 }
            java.io.OutputStream r8 = r3.getOutputStream()     // Catch:{ all -> 0x00b4 }
            r6.<init>(r8)     // Catch:{ all -> 0x00b4 }
            r6.write(r15)     // Catch:{ all -> 0x00b1 }
            com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r6)     // Catch:{ zzdzd -> 0x01df }
            goto L_0x00b9
        L_0x00b1:
            r0 = move-exception
            r7 = r6
            goto L_0x00b5
        L_0x00b4:
            r0 = move-exception
        L_0x00b5:
            com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r7)     // Catch:{ zzdzd -> 0x01df }
            throw r0     // Catch:{ zzdzd -> 0x01df }
        L_0x00b9:
            com.google.android.gms.ads.internal.util.client.zzl r6 = new com.google.android.gms.ads.internal.util.client.zzl     // Catch:{ zzdzd -> 0x01df }
            r6.<init>(r7)     // Catch:{ zzdzd -> 0x01df }
            r6.zzc(r3, r15)     // Catch:{ zzdzd -> 0x01df }
            int r8 = r3.getResponseCode()     // Catch:{ zzdzd -> 0x01df }
            java.util.Map r9 = r3.getHeaderFields()     // Catch:{ zzdzd -> 0x01df }
            java.util.Set r9 = r9.entrySet()     // Catch:{ zzdzd -> 0x01df }
            java.util.Iterator r9 = r9.iterator()     // Catch:{ zzdzd -> 0x01df }
        L_0x00d1:
            boolean r10 = r9.hasNext()     // Catch:{ zzdzd -> 0x01df }
            if (r10 == 0) goto L_0x0102
            java.lang.Object r10 = r9.next()     // Catch:{ zzdzd -> 0x01df }
            java.util.Map$Entry r10 = (java.util.Map.Entry) r10     // Catch:{ zzdzd -> 0x01df }
            java.lang.Object r11 = r10.getKey()     // Catch:{ zzdzd -> 0x01df }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ zzdzd -> 0x01df }
            java.lang.Object r10 = r10.getValue()     // Catch:{ zzdzd -> 0x01df }
            java.util.List r10 = (java.util.List) r10     // Catch:{ zzdzd -> 0x01df }
            boolean r12 = r4.containsKey(r11)     // Catch:{ zzdzd -> 0x01df }
            if (r12 == 0) goto L_0x00f9
            java.lang.Object r11 = r4.get(r11)     // Catch:{ zzdzd -> 0x01df }
            java.util.List r11 = (java.util.List) r11     // Catch:{ zzdzd -> 0x01df }
            r11.addAll(r10)     // Catch:{ zzdzd -> 0x01df }
            goto L_0x00d1
        L_0x00f9:
            java.util.ArrayList r12 = new java.util.ArrayList     // Catch:{ zzdzd -> 0x01df }
            r12.<init>(r10)     // Catch:{ zzdzd -> 0x01df }
            r4.put(r11, r12)     // Catch:{ zzdzd -> 0x01df }
            goto L_0x00d1
        L_0x0102:
            r6.zze(r3, r8)     // Catch:{ zzdzd -> 0x01df }
            r9 = r19
            r9.zza = r8     // Catch:{ zzdzd -> 0x01dd }
            r9.zzb = r4     // Catch:{ zzdzd -> 0x01dd }
            java.lang.String r10 = ""
            r9.zzc = r10     // Catch:{ zzdzd -> 0x01dd }
            r10 = 200(0xc8, float:2.8E-43)
            r11 = 300(0x12c, float:4.2E-43)
            if (r8 < r10) goto L_0x0168
            if (r8 >= r11) goto L_0x0168
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ all -> 0x0163 }
            java.io.InputStream r0 = r3.getInputStream()     // Catch:{ all -> 0x0163 }
            r2.<init>(r0)     // Catch:{ all -> 0x0163 }
            com.google.android.gms.ads.internal.zzu.zzp()     // Catch:{ all -> 0x0160 }
            java.lang.String r0 = com.google.android.gms.ads.internal.util.zzt.zzN(r2)     // Catch:{ all -> 0x0160 }
            com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r2)     // Catch:{ zzdzd -> 0x01dd }
            r6.zzg(r0)     // Catch:{ zzdzd -> 0x01dd }
            r9.zzc = r0     // Catch:{ zzdzd -> 0x01dd }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ zzdzd -> 0x01dd }
            if (r0 == 0) goto L_0x014f
            com.google.android.gms.internal.ads.zzbeg r0 = com.google.android.gms.internal.ads.zzbep.zzfB     // Catch:{ zzdzd -> 0x01dd }
            com.google.android.gms.internal.ads.zzben r2 = com.google.android.gms.ads.internal.client.zzba.zzc()     // Catch:{ zzdzd -> 0x01dd }
            java.lang.Object r0 = r2.zza(r0)     // Catch:{ zzdzd -> 0x01dd }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ zzdzd -> 0x01dd }
            boolean r0 = r0.booleanValue()     // Catch:{ zzdzd -> 0x01dd }
            if (r0 == 0) goto L_0x0148
            goto L_0x014f
        L_0x0148:
            com.google.android.gms.internal.ads.zzdzd r0 = new com.google.android.gms.internal.ads.zzdzd     // Catch:{ zzdzd -> 0x01dd }
            r2 = 3
            r0.<init>(r2)     // Catch:{ zzdzd -> 0x01dd }
            throw r0     // Catch:{ zzdzd -> 0x01dd }
        L_0x014f:
            com.google.android.gms.common.util.Clock r0 = com.google.android.gms.ads.internal.zzu.zzB()     // Catch:{ zzdzd -> 0x01dd }
            long r4 = r0.elapsedRealtime()     // Catch:{ zzdzd -> 0x01dd }
            long r4 = r4 - r16
            r9.zzd = r4     // Catch:{ zzdzd -> 0x01dd }
        L_0x015b:
            r3.disconnect()     // Catch:{ IOException -> 0x0210 }
            goto L_0x0209
        L_0x0160:
            r0 = move-exception
            r7 = r2
            goto L_0x0164
        L_0x0163:
            r0 = move-exception
        L_0x0164:
            com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r7)     // Catch:{ zzdzd -> 0x01dd }
            throw r0     // Catch:{ zzdzd -> 0x01dd }
        L_0x0168:
            if (r8 < r11) goto L_0x01b6
            r6 = 400(0x190, float:5.6E-43)
            if (r8 >= r6) goto L_0x01b6
            java.lang.String r6 = "Location"
            java.lang.String r6 = r3.getHeaderField(r6)     // Catch:{ zzdzd -> 0x01dd }
            boolean r7 = android.text.TextUtils.isEmpty(r6)     // Catch:{ zzdzd -> 0x01dd }
            if (r7 != 0) goto L_0x01a9
            java.net.URL r7 = new java.net.URL     // Catch:{ zzdzd -> 0x01dd }
            r7.<init>(r6)     // Catch:{ zzdzd -> 0x01dd }
            int r5 = r5 + r1
            com.google.android.gms.internal.ads.zzbeg r6 = com.google.android.gms.internal.ads.zzbep.zzeY     // Catch:{ zzdzd -> 0x01dd }
            com.google.android.gms.internal.ads.zzben r8 = com.google.android.gms.ads.internal.client.zzba.zzc()     // Catch:{ zzdzd -> 0x01dd }
            java.lang.Object r6 = r8.zza(r6)     // Catch:{ zzdzd -> 0x01dd }
            java.lang.Integer r6 = (java.lang.Integer) r6     // Catch:{ zzdzd -> 0x01dd }
            int r6 = r6.intValue()     // Catch:{ zzdzd -> 0x01dd }
            if (r5 > r6) goto L_0x019c
            r3.disconnect()     // Catch:{ IOException -> 0x0210 }
            r12 = r1
            r3 = r7
            r11 = r9
            r1 = r20
            goto L_0x004d
        L_0x019c:
            java.lang.String r0 = "Too many redirects."
            com.google.android.gms.ads.internal.util.client.zzm.zzj(r0)     // Catch:{ zzdzd -> 0x01dd }
            com.google.android.gms.internal.ads.zzdzd r0 = new com.google.android.gms.internal.ads.zzdzd     // Catch:{ zzdzd -> 0x01dd }
            java.lang.String r2 = "Too many redirects"
            r0.<init>(r1, r2)     // Catch:{ zzdzd -> 0x01dd }
            throw r0     // Catch:{ zzdzd -> 0x01dd }
        L_0x01a9:
            java.lang.String r0 = "No location header to follow redirect."
            com.google.android.gms.ads.internal.util.client.zzm.zzj(r0)     // Catch:{ zzdzd -> 0x01dd }
            com.google.android.gms.internal.ads.zzdzd r0 = new com.google.android.gms.internal.ads.zzdzd     // Catch:{ zzdzd -> 0x01dd }
            java.lang.String r2 = "No location header to follow redirect"
            r0.<init>(r1, r2)     // Catch:{ zzdzd -> 0x01dd }
            throw r0     // Catch:{ zzdzd -> 0x01dd }
        L_0x01b6:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ zzdzd -> 0x01dd }
            r0.<init>()     // Catch:{ zzdzd -> 0x01dd }
            r0.append(r2)     // Catch:{ zzdzd -> 0x01dd }
            r0.append(r8)     // Catch:{ zzdzd -> 0x01dd }
            java.lang.String r0 = r0.toString()     // Catch:{ zzdzd -> 0x01dd }
            com.google.android.gms.ads.internal.util.client.zzm.zzj(r0)     // Catch:{ zzdzd -> 0x01dd }
            com.google.android.gms.internal.ads.zzdzd r0 = new com.google.android.gms.internal.ads.zzdzd     // Catch:{ zzdzd -> 0x01dd }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ zzdzd -> 0x01dd }
            r4.<init>()     // Catch:{ zzdzd -> 0x01dd }
            r4.append(r2)     // Catch:{ zzdzd -> 0x01dd }
            r4.append(r8)     // Catch:{ zzdzd -> 0x01dd }
            java.lang.String r2 = r4.toString()     // Catch:{ zzdzd -> 0x01dd }
            r0.<init>(r1, r2)     // Catch:{ zzdzd -> 0x01dd }
            throw r0     // Catch:{ zzdzd -> 0x01dd }
        L_0x01dd:
            r0 = move-exception
            goto L_0x01e9
        L_0x01df:
            r0 = move-exception
            r9 = r19
            goto L_0x01e9
        L_0x01e3:
            r0 = move-exception
            r1 = r12
            goto L_0x020c
        L_0x01e6:
            r0 = move-exception
            r9 = r11
            r1 = r12
        L_0x01e9:
            com.google.android.gms.internal.ads.zzbeg r2 = com.google.android.gms.internal.ads.zzbep.zzis     // Catch:{ all -> 0x020b }
            com.google.android.gms.internal.ads.zzben r4 = com.google.android.gms.ads.internal.client.zzba.zzc()     // Catch:{ all -> 0x020b }
            java.lang.Object r2 = r4.zza(r2)     // Catch:{ all -> 0x020b }
            java.lang.Boolean r2 = (java.lang.Boolean) r2     // Catch:{ all -> 0x020b }
            boolean r2 = r2.booleanValue()     // Catch:{ all -> 0x020b }
            if (r2 == 0) goto L_0x020a
            com.google.android.gms.common.util.Clock r0 = com.google.android.gms.ads.internal.zzu.zzB()     // Catch:{ all -> 0x020b }
            long r4 = r0.elapsedRealtime()     // Catch:{ all -> 0x020b }
            long r4 = r4 - r16
            r9.zzd = r4     // Catch:{ all -> 0x020b }
            goto L_0x015b
        L_0x0209:
            return r9
        L_0x020a:
            throw r0     // Catch:{ all -> 0x020b }
        L_0x020b:
            r0 = move-exception
        L_0x020c:
            r3.disconnect()     // Catch:{ IOException -> 0x0210 }
            throw r0     // Catch:{ IOException -> 0x0210 }
        L_0x0210:
            r0 = move-exception
            goto L_0x0214
        L_0x0212:
            r0 = move-exception
            r1 = r12
        L_0x0214:
            java.lang.String r2 = r0.getMessage()
            java.lang.String r2 = java.lang.String.valueOf(r2)
            java.lang.String r3 = "Error while connecting to ad server: "
            java.lang.String r2 = r3.concat(r2)
            com.google.android.gms.ads.internal.util.client.zzm.zzj(r2)
            com.google.android.gms.internal.ads.zzdzd r3 = new com.google.android.gms.internal.ads.zzdzd
            r3.<init>(r1, r2, r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzeee.zza(com.google.android.gms.internal.ads.zzeec):com.google.android.gms.internal.ads.zzeed");
    }
}
