package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.util.client.zzm;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzcfs extends zzcfp {
    public static final /* synthetic */ int zzd = 0;
    private static final Set zze = Collections.synchronizedSet(new HashSet());
    private static final DecimalFormat zzf = new DecimalFormat("#,###");
    private File zzg;
    private boolean zzh;

    public zzcfs(zzcee zzcee) {
        super(zzcee);
        File cacheDir = this.zza.getCacheDir();
        if (cacheDir == null) {
            zzm.zzj("Context.getCacheDir() returned null");
            return;
        }
        File file = new File(zzftv.zza(zzftu.zza(), cacheDir, "admobVideoStreams"));
        this.zzg = file;
        if (!file.isDirectory() && !this.zzg.mkdirs()) {
            zzm.zzj("Could not create preload cache directory at ".concat(String.valueOf(this.zzg.getAbsolutePath())));
            this.zzg = null;
        } else if (!this.zzg.setReadable(true, false) || !this.zzg.setExecutable(true, false)) {
            zzm.zzj("Could not set cache file permissions at ".concat(String.valueOf(this.zzg.getAbsolutePath())));
            this.zzg = null;
        }
    }

    private final File zza(File file) {
        return new File(zzftv.zza(zzftu.zza(), this.zzg, String.valueOf(file.getName()).concat(".done")));
    }

    public final void zzf() {
        this.zzh = true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x02a8, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x02a9, code lost:
        r26 = r4;
        r24 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x02af, code lost:
        r23 = r1;
        r25 = r3;
        r26 = r4;
        r20 = r5;
        r27 = r6;
        r28 = r7;
        r24 = r14;
        r21 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x02bf, code lost:
        r2 = r19;
        r5 = r20;
        r15 = r21;
        r1 = r23;
        r14 = r24;
        r3 = r25;
        r4 = r26;
        r6 = r27;
        r7 = r28;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x02d3, code lost:
        r26 = r4;
        r24 = r14;
        r21 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x02e2, code lost:
        throw new java.io.IOException("abort requested");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x02e3, code lost:
        r26 = r4;
        r24 = r14;
        r21 = r15;
        r15 = "downloadTimeout";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:?, code lost:
        r11 = "Timeout exceeded. Limit: " + java.lang.Long.toString(r10) + " sec";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x030c, code lost:
        throw new java.io.IOException("stream cache time limit exceeded");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x030d, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x0310, code lost:
        r26 = r4;
        r24 = r14;
        r21 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x0318, code lost:
        r26 = r4;
        r24 = r14;
        r21 = r15;
        r15 = "sizeExceeded";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:?, code lost:
        r11 = "File too big for full file cache. Size: " + java.lang.Integer.toString(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x033c, code lost:
        throw new java.io.IOException("stream cache file size limit exceeded");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x033d, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x0340, code lost:
        r2 = r11;
        r1 = r24;
        r11 = r26;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x0347, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x034a, code lost:
        r1 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x034e, code lost:
        r26 = r4;
        r24 = r14;
        r21 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:?, code lost:
        r26.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x035c, code lost:
        if (com.google.android.gms.ads.internal.util.zze.zzm(3) == false) goto L_0x0381;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x035e, code lost:
        com.google.android.gms.ads.internal.util.client.zzm.zze("Preloaded " + zzf.format((long) r13) + " bytes from " + r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x0381, code lost:
        r12.setReadable(true, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x038a, code lost:
        if (r0.isFile() == false) goto L_0x0394;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x038c, code lost:
        r0.setLastModified(java.lang.System.currentTimeMillis());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:?, code lost:
        r0.createNewFile();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:?, code lost:
        zzh(r9, r12.getAbsolutePath(), r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x03a0, code lost:
        r1 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:?, code lost:
        zze.remove(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x03a5, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x03a7, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x03ab, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x03ad, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x03ae, code lost:
        r1 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x03b1, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x03b4, code lost:
        r26 = r4;
        r1 = r14;
        r21 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x03b9, code lost:
        r15 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x03bb, code lost:
        r11 = r26;
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x03bf, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x03c2, code lost:
        r1 = r14;
        r15 = r15;
        r2 = null;
        r11 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x03cb, code lost:
        if ((r0 instanceof java.lang.RuntimeException) != false) goto L_0x03cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x03cd, code lost:
        com.google.android.gms.ads.internal.zzu.zzo().zzw(r0, "VideoStreamFullFileCache.preload");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:?, code lost:
        r11.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x03db, code lost:
        if (r8.zzh == false) goto L_0x03f4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x03dd, code lost:
        com.google.android.gms.ads.internal.util.client.zzm.zzi("Preload aborted for URL \"" + r9 + "\"");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x03f4, code lost:
        com.google.android.gms.ads.internal.util.client.zzm.zzk("Preload failed for URL \"" + r9 + "\"", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x0416, code lost:
        com.google.android.gms.ads.internal.util.client.zzm.zzj("Could not delete partial cache file at ".concat(java.lang.String.valueOf(r12.getAbsolutePath())));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x0427, code lost:
        zzg(r9, r12.getAbsolutePath(), r15, r2);
        zze.remove(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:196:?, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x010d, code lost:
        r15 = "error";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:?, code lost:
        r1 = com.google.android.gms.internal.ads.zzfuq.zza().zzn(new com.google.android.gms.internal.ads.zzcfr(r9), 265, -1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0121, code lost:
        if ((r1 instanceof java.net.HttpURLConnection) == false) goto L_0x016e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0123, code lost:
        r2 = r1.getResponseCode();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0129, code lost:
        if (r2 >= 400) goto L_0x012c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x012c, code lost:
        r15 = "badUrl";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0161, code lost:
        throw new java.io.IOException("HTTP status code " + r2 + " at " + r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0162, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0165, code lost:
        r2 = "HTTP request failed. Code: " + java.lang.Integer.toString(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0167, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x016a, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x016b, code lost:
        r1 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:?, code lost:
        r7 = r1.getContentLength();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0172, code lost:
        if (r7 >= 0) goto L_0x0195;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0174, code lost:
        com.google.android.gms.ads.internal.util.client.zzm.zzj("Stream cache aborted, missing content-length header at " + r9);
        zzg(r9, r12.getAbsolutePath(), "contentLengthMissing", (java.lang.String) null);
        r3.remove(r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0194, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0195, code lost:
        r2 = zzf.format((long) r7);
        r6 = ((java.lang.Integer) com.google.android.gms.ads.internal.client.zzba.zzc().zza(com.google.android.gms.internal.ads.zzbep.zzv)).intValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x01ac, code lost:
        if (r7 <= r6) goto L_0x01e8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x01ae, code lost:
        com.google.android.gms.ads.internal.util.client.zzm.zzj("Content length " + r2 + " exceeds limit at " + r9);
        r0 = new java.lang.StringBuilder();
        r0.append("File too big for full file cache. Size: ");
        r0.append(r2);
        zzg(r9, r12.getAbsolutePath(), "sizeExceeded", r0.toString());
        r3.remove(r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x01e7, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x01e8, code lost:
        com.google.android.gms.ads.internal.util.client.zzm.zze("Caching " + r2 + " bytes from " + r9);
        r5 = java.nio.channels.Channels.newChannel(r1.getInputStream());
        r4 = new java.io.FileOutputStream(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:?, code lost:
        r3 = r4.getChannel();
        r2 = java.nio.ByteBuffer.allocate(1048576);
        r16 = com.google.android.gms.ads.internal.zzu.zzB();
        r17 = r16.currentTimeMillis();
        r1 = new com.google.android.gms.ads.internal.util.zzcb(((java.lang.Long) com.google.android.gms.ads.internal.client.zzba.zzc().zza(com.google.android.gms.internal.ads.zzbep.zzy)).longValue());
        r10 = ((java.lang.Long) com.google.android.gms.ads.internal.client.zzba.zzc().zza(com.google.android.gms.internal.ads.zzbep.zzx)).longValue();
        r13 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0249, code lost:
        r19 = r5.read(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x024d, code lost:
        if (r19 < 0) goto L_0x034e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x024f, code lost:
        r13 = r13 + r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0251, code lost:
        if (r13 > r6) goto L_0x0318;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:?, code lost:
        r2.flip();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x025a, code lost:
        if (r3.write(r2) > 0) goto L_0x0256;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x025c, code lost:
        r2.clear();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x026b, code lost:
        if ((r16.currentTimeMillis() - r17) > (1000 * r10)) goto L_0x02e3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x026d, code lost:
        r19 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x0271, code lost:
        if (r8.zzh != false) goto L_0x02d3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x0277, code lost:
        if (r1.zzb() == false) goto L_0x02af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0279, code lost:
        r20 = r12.getAbsolutePath();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x027f, code lost:
        r21 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x0283, code lost:
        r23 = r1;
        r1 = r1;
        r24 = r14;
        r14 = com.google.android.gms.ads.internal.util.client.zzf.zza;
        r25 = r3;
        r26 = r4;
        r4 = r20;
        r20 = r5;
        r27 = r6;
        r28 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:?, code lost:
        r1 = new com.google.android.gms.internal.ads.zzcfj(r29, r30, r4, r13, r7, false);
        r14.post(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x02a6, code lost:
        r0 = e;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:136:0x0397 */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x03ab A[ExcHandler: RuntimeException (e java.lang.RuntimeException), PHI: r21 r24 r26 
      PHI: (r21v6 java.lang.String) = (r21v3 java.lang.String), (r21v3 java.lang.String), (r21v3 java.lang.String), (r21v3 java.lang.String), (r21v3 java.lang.String), (r21v15 java.lang.String), (r21v15 java.lang.String) binds: [B:127:0x0354, B:136:0x0397, B:137:?, B:134:0x0394, B:135:?, B:93:0x0281, B:96:0x029f] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r24v4 java.lang.String) = (r24v1 java.lang.String), (r24v1 java.lang.String), (r24v1 java.lang.String), (r24v1 java.lang.String), (r24v1 java.lang.String), (r24v11 java.lang.String), (r24v14 java.lang.String) binds: [B:127:0x0354, B:136:0x0397, B:137:?, B:134:0x0394, B:135:?, B:93:0x0281, B:96:0x029f] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r26v7 java.io.FileOutputStream) = (r26v4 java.io.FileOutputStream), (r26v4 java.io.FileOutputStream), (r26v4 java.io.FileOutputStream), (r26v4 java.io.FileOutputStream), (r26v4 java.io.FileOutputStream), (r26v14 java.io.FileOutputStream), (r26v17 java.io.FileOutputStream) binds: [B:127:0x0354, B:136:0x0397, B:137:?, B:134:0x0394, B:135:?, B:93:0x0281, B:96:0x029f] A[DONT_GENERATE, DONT_INLINE], Splitter:B:96:0x029f] */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x03cd  */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x03dd  */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x03f4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzt(java.lang.String r30) {
        /*
            r29 = this;
            r8 = r29
            r9 = r30
            java.io.File r0 = r8.zzg
            r10 = 0
            r11 = 0
            if (r0 == 0) goto L_0x0438
        L_0x000a:
            java.io.File r0 = r8.zzg
            if (r0 != 0) goto L_0x0010
            r3 = r10
            goto L_0x002c
        L_0x0010:
            java.io.File[] r0 = r0.listFiles()
            int r1 = r0.length
            r2 = r10
            r3 = r2
        L_0x0017:
            if (r2 >= r1) goto L_0x002c
            r4 = r0[r2]
            java.lang.String r4 = r4.getName()
            java.lang.String r5 = ".done"
            boolean r4 = r4.endsWith(r5)
            if (r4 != 0) goto L_0x0029
            int r3 = r3 + 1
        L_0x0029:
            int r2 = r2 + 1
            goto L_0x0017
        L_0x002c:
            com.google.android.gms.internal.ads.zzbeg r0 = com.google.android.gms.internal.ads.zzbep.zzu
            com.google.android.gms.internal.ads.zzben r1 = com.google.android.gms.ads.internal.client.zzba.zzc()
            java.lang.Object r0 = r1.zza(r0)
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            if (r3 <= r0) goto L_0x0090
            java.io.File r0 = r8.zzg
            if (r0 != 0) goto L_0x0043
            goto L_0x0085
        L_0x0043:
            java.io.File[] r0 = r0.listFiles()
            int r1 = r0.length
            r2 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r4 = r10
            r5 = r11
        L_0x004f:
            if (r4 >= r1) goto L_0x006c
            r6 = r0[r4]
            java.lang.String r7 = r6.getName()
            java.lang.String r12 = ".done"
            boolean r7 = r7.endsWith(r12)
            if (r7 != 0) goto L_0x0069
            long r12 = r6.lastModified()
            int r7 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r7 >= 0) goto L_0x0069
            r5 = r6
            r2 = r12
        L_0x0069:
            int r4 = r4 + 1
            goto L_0x004f
        L_0x006c:
            if (r5 == 0) goto L_0x0082
            boolean r0 = r5.delete()
            java.io.File r1 = r8.zza(r5)
            boolean r2 = r1.isFile()
            if (r2 == 0) goto L_0x0083
            boolean r1 = r1.delete()
            r0 = r0 & r1
            goto L_0x0083
        L_0x0082:
            r0 = r10
        L_0x0083:
            if (r0 != 0) goto L_0x000a
        L_0x0085:
            java.lang.String r0 = "Unable to expire stream cache"
            com.google.android.gms.ads.internal.util.client.zzm.zzj(r0)
            java.lang.String r0 = "expireFailed"
            r8.zzg(r9, r11, r0, r11)
            return r10
        L_0x0090:
            java.lang.String r0 = com.google.android.gms.ads.internal.util.client.zzf.zzf(r30)
            java.io.File r12 = new java.io.File
            com.google.android.gms.internal.ads.zzftw r1 = com.google.android.gms.internal.ads.zzftu.zza()
            java.io.File r2 = r8.zzg
            java.lang.String r0 = com.google.android.gms.internal.ads.zzftv.zza(r1, r2, r0)
            r12.<init>(r0)
            java.io.File r0 = r8.zza(r12)
            boolean r1 = r12.isFile()
            r13 = 1
            if (r1 == 0) goto L_0x00cf
            boolean r1 = r0.isFile()
            if (r1 != 0) goto L_0x00b5
            goto L_0x00cf
        L_0x00b5:
            long r0 = r12.length()
            int r0 = (int) r0
            java.lang.String r1 = java.lang.String.valueOf(r30)
            java.lang.String r2 = "Stream cache hit at "
            java.lang.String r1 = r2.concat(r1)
            com.google.android.gms.ads.internal.util.client.zzm.zze(r1)
            java.lang.String r1 = r12.getAbsolutePath()
            r8.zzh(r9, r1, r0)
            return r13
        L_0x00cf:
            java.io.File r1 = r8.zzg
            java.lang.String r1 = r1.getAbsolutePath()
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r2 = java.lang.String.valueOf(r30)
            java.util.Set r3 = zze
            java.lang.String r14 = r1.concat(r2)
            monitor-enter(r3)
            boolean r1 = r3.contains(r14)     // Catch:{ all -> 0x0435 }
            if (r1 == 0) goto L_0x0109
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0435 }
            r0.<init>()     // Catch:{ all -> 0x0435 }
            java.lang.String r1 = "Stream cache already in progress at "
            r0.append(r1)     // Catch:{ all -> 0x0435 }
            r0.append(r9)     // Catch:{ all -> 0x0435 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0435 }
            com.google.android.gms.ads.internal.util.client.zzm.zzj(r0)     // Catch:{ all -> 0x0435 }
            java.lang.String r0 = r12.getAbsolutePath()     // Catch:{ all -> 0x0435 }
            java.lang.String r1 = "inProgress"
            r8.zzg(r9, r0, r1, r11)     // Catch:{ all -> 0x0435 }
            monitor-exit(r3)     // Catch:{ all -> 0x0435 }
            return r10
        L_0x0109:
            r3.add(r14)     // Catch:{ all -> 0x0435 }
            monitor-exit(r3)     // Catch:{ all -> 0x0435 }
            java.lang.String r15 = "error"
            com.google.android.gms.internal.ads.zzfuq r1 = com.google.android.gms.internal.ads.zzfuq.zza()     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            com.google.android.gms.internal.ads.zzcfr r2 = new com.google.android.gms.internal.ads.zzcfr     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            r2.<init>(r9)     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            r4 = 265(0x109, float:3.71E-43)
            r5 = -1
            java.net.HttpURLConnection r1 = r1.zzn(r2, r4, r5)     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            boolean r2 = r1 instanceof java.net.HttpURLConnection     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            if (r2 == 0) goto L_0x016e
            int r2 = r1.getResponseCode()     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            r4 = 400(0x190, float:5.6E-43)
            if (r2 >= r4) goto L_0x012c
            goto L_0x016e
        L_0x012c:
            java.lang.String r15 = "badUrl"
            java.lang.String r0 = java.lang.Integer.toString(r2)     // Catch:{ IOException -> 0x0169, RuntimeException -> 0x0167 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0169, RuntimeException -> 0x0167 }
            r1.<init>()     // Catch:{ IOException -> 0x0169, RuntimeException -> 0x0167 }
            java.lang.String r3 = "HTTP request failed. Code: "
            r1.append(r3)     // Catch:{ IOException -> 0x0169, RuntimeException -> 0x0167 }
            r1.append(r0)     // Catch:{ IOException -> 0x0169, RuntimeException -> 0x0167 }
            java.lang.String r1 = r1.toString()     // Catch:{ IOException -> 0x0169, RuntimeException -> 0x0167 }
            java.io.IOException r0 = new java.io.IOException     // Catch:{ IOException -> 0x0164, RuntimeException -> 0x0162 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0164, RuntimeException -> 0x0162 }
            r3.<init>()     // Catch:{ IOException -> 0x0164, RuntimeException -> 0x0162 }
            java.lang.String r4 = "HTTP status code "
            r3.append(r4)     // Catch:{ IOException -> 0x0164, RuntimeException -> 0x0162 }
            r3.append(r2)     // Catch:{ IOException -> 0x0164, RuntimeException -> 0x0162 }
            java.lang.String r2 = " at "
            r3.append(r2)     // Catch:{ IOException -> 0x0164, RuntimeException -> 0x0162 }
            r3.append(r9)     // Catch:{ IOException -> 0x0164, RuntimeException -> 0x0162 }
            java.lang.String r2 = r3.toString()     // Catch:{ IOException -> 0x0164, RuntimeException -> 0x0162 }
            r0.<init>(r2)     // Catch:{ IOException -> 0x0164, RuntimeException -> 0x0162 }
            throw r0     // Catch:{ IOException -> 0x0164, RuntimeException -> 0x0162 }
        L_0x0162:
            r0 = move-exception
            goto L_0x0165
        L_0x0164:
            r0 = move-exception
        L_0x0165:
            r2 = r1
            goto L_0x016b
        L_0x0167:
            r0 = move-exception
            goto L_0x016a
        L_0x0169:
            r0 = move-exception
        L_0x016a:
            r2 = r11
        L_0x016b:
            r1 = r14
            goto L_0x03c9
        L_0x016e:
            int r7 = r1.getContentLength()     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            if (r7 >= 0) goto L_0x0195
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            r0.<init>()     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            java.lang.String r1 = "Stream cache aborted, missing content-length header at "
            r0.append(r1)     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            r0.append(r9)     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            java.lang.String r0 = r0.toString()     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            com.google.android.gms.ads.internal.util.client.zzm.zzj(r0)     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            java.lang.String r0 = r12.getAbsolutePath()     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            java.lang.String r1 = "contentLengthMissing"
            r8.zzg(r9, r0, r1, r11)     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            r3.remove(r14)     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            return r10
        L_0x0195:
            java.text.DecimalFormat r2 = zzf     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            long r4 = (long) r7     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            java.lang.String r2 = r2.format(r4)     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            com.google.android.gms.internal.ads.zzbeg r4 = com.google.android.gms.internal.ads.zzbep.zzv     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            com.google.android.gms.internal.ads.zzben r5 = com.google.android.gms.ads.internal.client.zzba.zzc()     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            java.lang.Object r4 = r5.zza(r4)     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            java.lang.Integer r4 = (java.lang.Integer) r4     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            int r6 = r4.intValue()     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            if (r7 <= r6) goto L_0x01e8
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            r0.<init>()     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            java.lang.String r1 = "Content length "
            r0.append(r1)     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            r0.append(r2)     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            java.lang.String r1 = " exceeds limit at "
            r0.append(r1)     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            r0.append(r9)     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            java.lang.String r0 = r0.toString()     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            com.google.android.gms.ads.internal.util.client.zzm.zzj(r0)     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            r0.<init>()     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            java.lang.String r1 = "File too big for full file cache. Size: "
            r0.append(r1)     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            r0.append(r2)     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            java.lang.String r0 = r0.toString()     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            java.lang.String r1 = r12.getAbsolutePath()     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            java.lang.String r2 = "sizeExceeded"
            r8.zzg(r9, r1, r2, r0)     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            r3.remove(r14)     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            return r10
        L_0x01e8:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            r3.<init>()     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            java.lang.String r4 = "Caching "
            r3.append(r4)     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            r3.append(r2)     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            java.lang.String r2 = " bytes from "
            r3.append(r2)     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            r3.append(r9)     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            java.lang.String r2 = r3.toString()     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            com.google.android.gms.ads.internal.util.client.zzm.zze(r2)     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            java.io.InputStream r1 = r1.getInputStream()     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            java.nio.channels.ReadableByteChannel r5 = java.nio.channels.Channels.newChannel(r1)     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            r4.<init>(r12)     // Catch:{ IOException -> 0x03c1, RuntimeException -> 0x03bf }
            java.nio.channels.FileChannel r3 = r4.getChannel()     // Catch:{ IOException -> 0x03b3, RuntimeException -> 0x03b1 }
            r1 = 1048576(0x100000, float:1.469368E-39)
            java.nio.ByteBuffer r2 = java.nio.ByteBuffer.allocate(r1)     // Catch:{ IOException -> 0x03b3, RuntimeException -> 0x03b1 }
            com.google.android.gms.common.util.Clock r16 = com.google.android.gms.ads.internal.zzu.zzB()     // Catch:{ IOException -> 0x03b3, RuntimeException -> 0x03b1 }
            long r17 = r16.currentTimeMillis()     // Catch:{ IOException -> 0x03b3, RuntimeException -> 0x03b1 }
            com.google.android.gms.internal.ads.zzbeg r1 = com.google.android.gms.internal.ads.zzbep.zzy     // Catch:{ IOException -> 0x03b3, RuntimeException -> 0x03b1 }
            com.google.android.gms.internal.ads.zzben r11 = com.google.android.gms.ads.internal.client.zzba.zzc()     // Catch:{ IOException -> 0x03b3, RuntimeException -> 0x03b1 }
            java.lang.Object r1 = r11.zza(r1)     // Catch:{ IOException -> 0x03b3, RuntimeException -> 0x03b1 }
            java.lang.Long r1 = (java.lang.Long) r1     // Catch:{ IOException -> 0x03b3, RuntimeException -> 0x03b1 }
            long r10 = r1.longValue()     // Catch:{ IOException -> 0x03b3, RuntimeException -> 0x03b1 }
            com.google.android.gms.ads.internal.util.zzcb r1 = new com.google.android.gms.ads.internal.util.zzcb     // Catch:{ IOException -> 0x03b3, RuntimeException -> 0x03b1 }
            r1.<init>(r10)     // Catch:{ IOException -> 0x03b3, RuntimeException -> 0x03b1 }
            com.google.android.gms.internal.ads.zzbeg r10 = com.google.android.gms.internal.ads.zzbep.zzx     // Catch:{ IOException -> 0x03b3, RuntimeException -> 0x03b1 }
            com.google.android.gms.internal.ads.zzben r11 = com.google.android.gms.ads.internal.client.zzba.zzc()     // Catch:{ IOException -> 0x03b3, RuntimeException -> 0x03b1 }
            java.lang.Object r10 = r11.zza(r10)     // Catch:{ IOException -> 0x03b3, RuntimeException -> 0x03b1 }
            java.lang.Long r10 = (java.lang.Long) r10     // Catch:{ IOException -> 0x03b3, RuntimeException -> 0x03b1 }
            long r10 = r10.longValue()     // Catch:{ IOException -> 0x03b3, RuntimeException -> 0x03b1 }
            r13 = 0
        L_0x0249:
            int r19 = r5.read(r2)     // Catch:{ IOException -> 0x03b3, RuntimeException -> 0x03b1 }
            if (r19 < 0) goto L_0x034e
            int r13 = r13 + r19
            if (r13 > r6) goto L_0x0318
            r2.flip()     // Catch:{ IOException -> 0x030f, RuntimeException -> 0x030d }
        L_0x0256:
            int r19 = r3.write(r2)     // Catch:{ IOException -> 0x030f, RuntimeException -> 0x030d }
            if (r19 > 0) goto L_0x0256
            r2.clear()     // Catch:{ IOException -> 0x030f, RuntimeException -> 0x030d }
            long r19 = r16.currentTimeMillis()     // Catch:{ IOException -> 0x030f, RuntimeException -> 0x030d }
            long r19 = r19 - r17
            r21 = 1000(0x3e8, double:4.94E-321)
            long r21 = r21 * r10
            int r19 = (r19 > r21 ? 1 : (r19 == r21 ? 0 : -1))
            if (r19 > 0) goto L_0x02e3
            r19 = r2
            boolean r2 = r8.zzh     // Catch:{ IOException -> 0x030f, RuntimeException -> 0x030d }
            if (r2 != 0) goto L_0x02d3
            boolean r2 = r1.zzb()     // Catch:{ IOException -> 0x030f, RuntimeException -> 0x030d }
            if (r2 == 0) goto L_0x02af
            java.lang.String r20 = r12.getAbsolutePath()     // Catch:{ IOException -> 0x030f, RuntimeException -> 0x030d }
            android.os.Handler r2 = com.google.android.gms.ads.internal.util.client.zzf.zza     // Catch:{ IOException -> 0x030f, RuntimeException -> 0x030d }
            r21 = r15
            com.google.android.gms.internal.ads.zzcfj r15 = new com.google.android.gms.internal.ads.zzcfj     // Catch:{ IOException -> 0x02a8, RuntimeException -> 0x02a6 }
            r22 = 0
            r23 = r1
            r1 = r15
            r24 = r14
            r14 = r2
            r2 = r29
            r25 = r3
            r3 = r30
            r26 = r4
            r4 = r20
            r20 = r5
            r5 = r13
            r27 = r6
            r6 = r7
            r28 = r7
            r7 = r22
            r1.<init>(r2, r3, r4, r5, r6, r7)     // Catch:{ IOException -> 0x03ad, RuntimeException -> 0x03ab }
            r14.post(r15)     // Catch:{ IOException -> 0x03ad, RuntimeException -> 0x03ab }
            goto L_0x02bf
        L_0x02a6:
            r0 = move-exception
            goto L_0x02a9
        L_0x02a8:
            r0 = move-exception
        L_0x02a9:
            r26 = r4
            r24 = r14
            goto L_0x03ae
        L_0x02af:
            r23 = r1
            r25 = r3
            r26 = r4
            r20 = r5
            r27 = r6
            r28 = r7
            r24 = r14
            r21 = r15
        L_0x02bf:
            r2 = r19
            r5 = r20
            r15 = r21
            r1 = r23
            r14 = r24
            r3 = r25
            r4 = r26
            r6 = r27
            r7 = r28
            goto L_0x0249
        L_0x02d3:
            r26 = r4
            r24 = r14
            r21 = r15
            java.lang.String r15 = "externalAbort"
            java.io.IOException r0 = new java.io.IOException     // Catch:{ IOException -> 0x0349, RuntimeException -> 0x0347 }
            java.lang.String r1 = "abort requested"
            r0.<init>(r1)     // Catch:{ IOException -> 0x0349, RuntimeException -> 0x0347 }
            throw r0     // Catch:{ IOException -> 0x0349, RuntimeException -> 0x0347 }
        L_0x02e3:
            r26 = r4
            r24 = r14
            r21 = r15
            java.lang.String r15 = "downloadTimeout"
            java.lang.String r0 = java.lang.Long.toString(r10)     // Catch:{ IOException -> 0x0349, RuntimeException -> 0x0347 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0349, RuntimeException -> 0x0347 }
            r1.<init>()     // Catch:{ IOException -> 0x0349, RuntimeException -> 0x0347 }
            java.lang.String r2 = "Timeout exceeded. Limit: "
            r1.append(r2)     // Catch:{ IOException -> 0x0349, RuntimeException -> 0x0347 }
            r1.append(r0)     // Catch:{ IOException -> 0x0349, RuntimeException -> 0x0347 }
            java.lang.String r0 = " sec"
            r1.append(r0)     // Catch:{ IOException -> 0x0349, RuntimeException -> 0x0347 }
            java.lang.String r11 = r1.toString()     // Catch:{ IOException -> 0x0349, RuntimeException -> 0x0347 }
            java.io.IOException r0 = new java.io.IOException     // Catch:{ IOException -> 0x033f, RuntimeException -> 0x033d }
            java.lang.String r1 = "stream cache time limit exceeded"
            r0.<init>(r1)     // Catch:{ IOException -> 0x033f, RuntimeException -> 0x033d }
            throw r0     // Catch:{ IOException -> 0x033f, RuntimeException -> 0x033d }
        L_0x030d:
            r0 = move-exception
            goto L_0x0310
        L_0x030f:
            r0 = move-exception
        L_0x0310:
            r26 = r4
            r24 = r14
            r21 = r15
            goto L_0x03ae
        L_0x0318:
            r26 = r4
            r24 = r14
            r21 = r15
            java.lang.String r15 = "sizeExceeded"
            java.lang.String r0 = java.lang.Integer.toString(r13)     // Catch:{ IOException -> 0x0349, RuntimeException -> 0x0347 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0349, RuntimeException -> 0x0347 }
            r1.<init>()     // Catch:{ IOException -> 0x0349, RuntimeException -> 0x0347 }
            java.lang.String r2 = "File too big for full file cache. Size: "
            r1.append(r2)     // Catch:{ IOException -> 0x0349, RuntimeException -> 0x0347 }
            r1.append(r0)     // Catch:{ IOException -> 0x0349, RuntimeException -> 0x0347 }
            java.lang.String r11 = r1.toString()     // Catch:{ IOException -> 0x0349, RuntimeException -> 0x0347 }
            java.io.IOException r0 = new java.io.IOException     // Catch:{ IOException -> 0x033f, RuntimeException -> 0x033d }
            java.lang.String r1 = "stream cache file size limit exceeded"
            r0.<init>(r1)     // Catch:{ IOException -> 0x033f, RuntimeException -> 0x033d }
            throw r0     // Catch:{ IOException -> 0x033f, RuntimeException -> 0x033d }
        L_0x033d:
            r0 = move-exception
            goto L_0x0340
        L_0x033f:
            r0 = move-exception
        L_0x0340:
            r2 = r11
            r1 = r24
            r11 = r26
            goto L_0x03c9
        L_0x0347:
            r0 = move-exception
            goto L_0x034a
        L_0x0349:
            r0 = move-exception
        L_0x034a:
            r1 = r24
            goto L_0x03bb
        L_0x034e:
            r26 = r4
            r24 = r14
            r21 = r15
            r26.close()     // Catch:{ IOException -> 0x03ad, RuntimeException -> 0x03ab }
            r1 = 3
            boolean r1 = com.google.android.gms.ads.internal.util.zze.zzm(r1)     // Catch:{ IOException -> 0x03ad, RuntimeException -> 0x03ab }
            if (r1 == 0) goto L_0x0381
            java.text.DecimalFormat r1 = zzf     // Catch:{ IOException -> 0x03ad, RuntimeException -> 0x03ab }
            long r2 = (long) r13     // Catch:{ IOException -> 0x03ad, RuntimeException -> 0x03ab }
            java.lang.String r1 = r1.format(r2)     // Catch:{ IOException -> 0x03ad, RuntimeException -> 0x03ab }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x03ad, RuntimeException -> 0x03ab }
            r2.<init>()     // Catch:{ IOException -> 0x03ad, RuntimeException -> 0x03ab }
            java.lang.String r3 = "Preloaded "
            r2.append(r3)     // Catch:{ IOException -> 0x03ad, RuntimeException -> 0x03ab }
            r2.append(r1)     // Catch:{ IOException -> 0x03ad, RuntimeException -> 0x03ab }
            java.lang.String r1 = " bytes from "
            r2.append(r1)     // Catch:{ IOException -> 0x03ad, RuntimeException -> 0x03ab }
            r2.append(r9)     // Catch:{ IOException -> 0x03ad, RuntimeException -> 0x03ab }
            java.lang.String r1 = r2.toString()     // Catch:{ IOException -> 0x03ad, RuntimeException -> 0x03ab }
            com.google.android.gms.ads.internal.util.client.zzm.zze(r1)     // Catch:{ IOException -> 0x03ad, RuntimeException -> 0x03ab }
        L_0x0381:
            r1 = 1
            r2 = 0
            r12.setReadable(r1, r2)     // Catch:{ IOException -> 0x03ad, RuntimeException -> 0x03ab }
            boolean r1 = r0.isFile()     // Catch:{ IOException -> 0x03ad, RuntimeException -> 0x03ab }
            if (r1 == 0) goto L_0x0394
            long r1 = java.lang.System.currentTimeMillis()     // Catch:{ IOException -> 0x03ad, RuntimeException -> 0x03ab }
            r0.setLastModified(r1)     // Catch:{ IOException -> 0x03ad, RuntimeException -> 0x03ab }
            goto L_0x0397
        L_0x0394:
            r0.createNewFile()     // Catch:{ IOException -> 0x0397, RuntimeException -> 0x03ab }
        L_0x0397:
            java.lang.String r0 = r12.getAbsolutePath()     // Catch:{ IOException -> 0x03ad, RuntimeException -> 0x03ab }
            r8.zzh(r9, r0, r13)     // Catch:{ IOException -> 0x03ad, RuntimeException -> 0x03ab }
            java.util.Set r0 = zze     // Catch:{ IOException -> 0x03ad, RuntimeException -> 0x03ab }
            r1 = r24
            r0.remove(r1)     // Catch:{ IOException -> 0x03a9, RuntimeException -> 0x03a7 }
            r0 = 1
            return r0
        L_0x03a7:
            r0 = move-exception
            goto L_0x03b9
        L_0x03a9:
            r0 = move-exception
            goto L_0x03b9
        L_0x03ab:
            r0 = move-exception
            goto L_0x03ae
        L_0x03ad:
            r0 = move-exception
        L_0x03ae:
            r1 = r24
            goto L_0x03b9
        L_0x03b1:
            r0 = move-exception
            goto L_0x03b4
        L_0x03b3:
            r0 = move-exception
        L_0x03b4:
            r26 = r4
            r1 = r14
            r21 = r15
        L_0x03b9:
            r15 = r21
        L_0x03bb:
            r11 = r26
            r2 = 0
            goto L_0x03c9
        L_0x03bf:
            r0 = move-exception
            goto L_0x03c2
        L_0x03c1:
            r0 = move-exception
        L_0x03c2:
            r1 = r14
            r21 = r15
            r15 = r21
            r2 = 0
            r11 = 0
        L_0x03c9:
            boolean r3 = r0 instanceof java.lang.RuntimeException
            if (r3 == 0) goto L_0x03d6
            java.lang.String r3 = "VideoStreamFullFileCache.preload"
            com.google.android.gms.internal.ads.zzcby r4 = com.google.android.gms.ads.internal.zzu.zzo()
            r4.zzw(r0, r3)
        L_0x03d6:
            r11.close()     // Catch:{ IOException | NullPointerException -> 0x03d9 }
        L_0x03d9:
            boolean r3 = r8.zzh
            if (r3 == 0) goto L_0x03f4
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r3 = "Preload aborted for URL \""
            r0.<init>(r3)
            r0.append(r9)
            java.lang.String r3 = "\""
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            com.google.android.gms.ads.internal.util.client.zzm.zzi(r0)
            goto L_0x040a
        L_0x03f4:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Preload failed for URL \""
            r3.<init>(r4)
            r3.append(r9)
            java.lang.String r4 = "\""
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            com.google.android.gms.ads.internal.util.client.zzm.zzk(r3, r0)
        L_0x040a:
            boolean r0 = r12.exists()
            if (r0 == 0) goto L_0x0427
            boolean r0 = r12.delete()
            if (r0 != 0) goto L_0x0427
            java.lang.String r0 = r12.getAbsolutePath()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r3 = "Could not delete partial cache file at "
            java.lang.String r0 = r3.concat(r0)
            com.google.android.gms.ads.internal.util.client.zzm.zzj(r0)
        L_0x0427:
            java.lang.String r0 = r12.getAbsolutePath()
            r8.zzg(r9, r0, r15, r2)
            java.util.Set r0 = zze
            r0.remove(r1)
        L_0x0433:
            r1 = 0
            return r1
        L_0x0435:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0435 }
            throw r0
        L_0x0438:
            java.lang.String r0 = "noCacheDir"
            r1 = 0
            r8.zzg(r9, r1, r0, r1)
            goto L_0x0433
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzcfs.zzt(java.lang.String):boolean");
    }
}
