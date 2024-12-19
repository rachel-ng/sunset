package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzasa implements zzaqo {
    private final Map zza = new LinkedHashMap(16, 0.75f, true);
    private long zzb = 0;
    private final zzarz zzc;
    private final int zzd;

    public zzasa(zzarz zzarz, int i) {
        this.zzc = zzarz;
        this.zzd = 5242880;
    }

    static int zze(InputStream inputStream) throws IOException {
        return (zzn(inputStream) << 24) | zzn(inputStream) | (zzn(inputStream) << 8) | (zzn(inputStream) << 16);
    }

    static long zzf(InputStream inputStream) throws IOException {
        return (((long) zzn(inputStream)) & 255) | ((((long) zzn(inputStream)) & 255) << 8) | ((((long) zzn(inputStream)) & 255) << 16) | ((((long) zzn(inputStream)) & 255) << 24) | ((((long) zzn(inputStream)) & 255) << 32) | ((((long) zzn(inputStream)) & 255) << 40) | ((((long) zzn(inputStream)) & 255) << 48) | ((((long) zzn(inputStream)) & 255) << 56);
    }

    static String zzh(zzary zzary) throws IOException {
        return new String(zzm(zzary, zzf(zzary)), "UTF-8");
    }

    static void zzj(OutputStream outputStream, int i) throws IOException {
        outputStream.write(i & 255);
        outputStream.write((i >> 8) & 255);
        outputStream.write((i >> 16) & 255);
        outputStream.write((i >> 24) & 255);
    }

    static void zzk(OutputStream outputStream, long j) throws IOException {
        outputStream.write((byte) ((int) j));
        outputStream.write((byte) ((int) (j >>> 8)));
        outputStream.write((byte) ((int) (j >>> 16)));
        outputStream.write((byte) ((int) (j >>> 24)));
        outputStream.write((byte) ((int) (j >>> 32)));
        outputStream.write((byte) ((int) (j >>> 40)));
        outputStream.write((byte) ((int) (j >>> 48)));
        outputStream.write((byte) ((int) (j >>> 56)));
    }

    static void zzl(OutputStream outputStream, String str) throws IOException {
        byte[] bytes = str.getBytes("UTF-8");
        int length = bytes.length;
        zzk(outputStream, (long) length);
        outputStream.write(bytes, 0, length);
    }

    static byte[] zzm(zzary zzary, long j) throws IOException {
        int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
        long zza2 = zzary.zza();
        if (i >= 0 && j <= zza2) {
            int i2 = (int) j;
            if (((long) i2) == j) {
                byte[] bArr = new byte[i2];
                new DataInputStream(zzary).readFully(bArr);
                return bArr;
            }
        }
        throw new IOException("streamToBytes length=" + j + ", maxLength=" + zza2);
    }

    private static int zzn(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        if (read != -1) {
            return read;
        }
        throw new EOFException();
    }

    private final void zzo(String str, zzarx zzarx) {
        if (!this.zza.containsKey(str)) {
            this.zzb += zzarx.zza;
        } else {
            this.zzb += zzarx.zza - ((zzarx) this.zza.get(str)).zza;
        }
        this.zza.put(str, zzarx);
    }

    private final void zzp(String str) {
        zzarx zzarx = (zzarx) this.zza.remove(str);
        if (zzarx != null) {
            this.zzb -= zzarx.zza;
        }
    }

    private static final String zzq(String str) {
        int length = str.length() / 2;
        return String.valueOf(String.valueOf(str.substring(0, length).hashCode())).concat(String.valueOf(String.valueOf(str.substring(length).hashCode())));
    }

    public final synchronized zzaqn zza(String str) {
        zzary zzary;
        zzarx zzarx = (zzarx) this.zza.get(str);
        if (zzarx == null) {
            return null;
        }
        File zzg = zzg(str);
        try {
            zzary = new zzary(new BufferedInputStream(new FileInputStream(zzg)), zzg.length());
            zzarx zza2 = zzarx.zza(zzary);
            if (!TextUtils.equals(str, zza2.zzb)) {
                zzarq.zza("%s: key=%s, found=%s", zzg.getAbsolutePath(), str, zza2.zzb);
                zzp(str);
                zzary.close();
                return null;
            }
            byte[] zzm = zzm(zzary, zzary.zza());
            zzaqn zzaqn = new zzaqn();
            zzaqn.zza = zzm;
            zzaqn.zzb = zzarx.zzc;
            zzaqn.zzc = zzarx.zzd;
            zzaqn.zzd = zzarx.zze;
            zzaqn.zze = zzarx.zzf;
            zzaqn.zzf = zzarx.zzg;
            List<zzaqw> list = zzarx.zzh;
            TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
            for (zzaqw zzaqw : list) {
                treeMap.put(zzaqw.zza(), zzaqw.zzb());
            }
            zzaqn.zzg = treeMap;
            zzaqn.zzh = Collections.unmodifiableList(zzarx.zzh);
            zzary.close();
            return zzaqn;
        } catch (IOException e) {
            zzarq.zza("%s: %s", zzg.getAbsolutePath(), e.toString());
            zzi(str);
            return null;
        } catch (Throwable th) {
            zzary.close();
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x005d, code lost:
        return;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:24:0x0056 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void zzb() {
        /*
            r8 = this;
            monitor-enter(r8)
            com.google.android.gms.internal.ads.zzarz r0 = r8.zzc     // Catch:{ all -> 0x005e }
            java.io.File r0 = r0.zza()     // Catch:{ all -> 0x005e }
            boolean r1 = r0.exists()     // Catch:{ all -> 0x005e }
            r2 = 0
            if (r1 != 0) goto L_0x0024
            boolean r1 = r0.mkdirs()     // Catch:{ all -> 0x005e }
            if (r1 != 0) goto L_0x005c
            java.lang.String r0 = r0.getAbsolutePath()     // Catch:{ all -> 0x005e }
            r1 = 1
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x005e }
            r1[r2] = r0     // Catch:{ all -> 0x005e }
            java.lang.String r0 = "Unable to create cache dir %s"
            com.google.android.gms.internal.ads.zzarq.zzb(r0, r1)     // Catch:{ all -> 0x005e }
            monitor-exit(r8)
            return
        L_0x0024:
            java.io.File[] r0 = r0.listFiles()     // Catch:{ all -> 0x005e }
            if (r0 == 0) goto L_0x005c
        L_0x002a:
            int r1 = r0.length     // Catch:{ all -> 0x005e }
            if (r2 >= r1) goto L_0x005c
            r1 = r0[r2]     // Catch:{ all -> 0x005e }
            long r3 = r1.length()     // Catch:{ IOException -> 0x0056 }
            com.google.android.gms.internal.ads.zzary r5 = new com.google.android.gms.internal.ads.zzary     // Catch:{ IOException -> 0x0056 }
            java.io.BufferedInputStream r6 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x0056 }
            java.io.FileInputStream r7 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0056 }
            r7.<init>(r1)     // Catch:{ IOException -> 0x0056 }
            r6.<init>(r7)     // Catch:{ IOException -> 0x0056 }
            r5.<init>(r6, r3)     // Catch:{ IOException -> 0x0056 }
            com.google.android.gms.internal.ads.zzarx r6 = com.google.android.gms.internal.ads.zzarx.zza(r5)     // Catch:{ all -> 0x0051 }
            r6.zza = r3     // Catch:{ all -> 0x0051 }
            java.lang.String r3 = r6.zzb     // Catch:{ all -> 0x0051 }
            r8.zzo(r3, r6)     // Catch:{ all -> 0x0051 }
            r5.close()     // Catch:{ IOException -> 0x0056 }
            goto L_0x0059
        L_0x0051:
            r3 = move-exception
            r5.close()     // Catch:{ IOException -> 0x0056 }
            throw r3     // Catch:{ IOException -> 0x0056 }
        L_0x0056:
            r1.delete()     // Catch:{ all -> 0x005e }
        L_0x0059:
            int r2 = r2 + 1
            goto L_0x002a
        L_0x005c:
            monitor-exit(r8)
            return
        L_0x005e:
            r0 = move-exception
            monitor-exit(r8)     // Catch:{ all -> 0x005e }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzasa.zzb():void");
    }

    public final synchronized void zzc(String str, boolean z) {
        zzaqn zza2 = zza(str);
        if (zza2 != null) {
            zza2.zzf = 0;
            zza2.zze = 0;
            zzd(str, zza2);
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:52:0x0160 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void zzd(java.lang.String r16, com.google.android.gms.internal.ads.zzaqn r17) {
        /*
            r15 = this;
            r1 = r15
            r0 = r16
            r2 = r17
            monitor-enter(r15)
            long r3 = r1.zzb     // Catch:{ all -> 0x0197 }
            byte[] r5 = r2.zza     // Catch:{ all -> 0x0197 }
            int r5 = r5.length     // Catch:{ all -> 0x0197 }
            long r6 = (long) r5     // Catch:{ all -> 0x0197 }
            long r3 = r3 + r6
            int r6 = r1.zzd     // Catch:{ all -> 0x0197 }
            long r7 = (long) r6     // Catch:{ all -> 0x0197 }
            int r3 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            r4 = 1063675494(0x3f666666, float:0.9)
            if (r3 <= 0) goto L_0x001e
            float r3 = (float) r5     // Catch:{ all -> 0x0197 }
            float r5 = (float) r6     // Catch:{ all -> 0x0197 }
            float r5 = r5 * r4
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 > 0) goto L_0x0195
        L_0x001e:
            java.io.File r3 = r15.zzg(r16)     // Catch:{ all -> 0x0197 }
            r6 = 0
            java.io.BufferedOutputStream r7 = new java.io.BufferedOutputStream     // Catch:{ IOException -> 0x0160 }
            java.io.FileOutputStream r8 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0160 }
            r8.<init>(r3)     // Catch:{ IOException -> 0x0160 }
            r7.<init>(r8)     // Catch:{ IOException -> 0x0160 }
            com.google.android.gms.internal.ads.zzarx r8 = new com.google.android.gms.internal.ads.zzarx     // Catch:{ IOException -> 0x0160 }
            r8.<init>(r0, r2)     // Catch:{ IOException -> 0x0160 }
            r9 = 538247942(0x20150306, float:1.2621791E-19)
            zzj(r7, r9)     // Catch:{ IOException -> 0x013a }
            java.lang.String r9 = r8.zzb     // Catch:{ IOException -> 0x013a }
            zzl(r7, r9)     // Catch:{ IOException -> 0x013a }
            java.lang.String r9 = r8.zzc     // Catch:{ IOException -> 0x013a }
            if (r9 != 0) goto L_0x0043
            java.lang.String r9 = ""
        L_0x0043:
            zzl(r7, r9)     // Catch:{ IOException -> 0x013a }
            long r9 = r8.zzd     // Catch:{ IOException -> 0x013a }
            zzk(r7, r9)     // Catch:{ IOException -> 0x013a }
            long r9 = r8.zze     // Catch:{ IOException -> 0x013a }
            zzk(r7, r9)     // Catch:{ IOException -> 0x013a }
            long r9 = r8.zzf     // Catch:{ IOException -> 0x013a }
            zzk(r7, r9)     // Catch:{ IOException -> 0x013a }
            long r9 = r8.zzg     // Catch:{ IOException -> 0x013a }
            zzk(r7, r9)     // Catch:{ IOException -> 0x013a }
            java.util.List r9 = r8.zzh     // Catch:{ IOException -> 0x013a }
            if (r9 == 0) goto L_0x0084
            int r10 = r9.size()     // Catch:{ IOException -> 0x013a }
            zzj(r7, r10)     // Catch:{ IOException -> 0x013a }
            java.util.Iterator r9 = r9.iterator()     // Catch:{ IOException -> 0x013a }
        L_0x0069:
            boolean r10 = r9.hasNext()     // Catch:{ IOException -> 0x013a }
            if (r10 == 0) goto L_0x0087
            java.lang.Object r10 = r9.next()     // Catch:{ IOException -> 0x013a }
            com.google.android.gms.internal.ads.zzaqw r10 = (com.google.android.gms.internal.ads.zzaqw) r10     // Catch:{ IOException -> 0x013a }
            java.lang.String r11 = r10.zza()     // Catch:{ IOException -> 0x013a }
            zzl(r7, r11)     // Catch:{ IOException -> 0x013a }
            java.lang.String r10 = r10.zzb()     // Catch:{ IOException -> 0x013a }
            zzl(r7, r10)     // Catch:{ IOException -> 0x013a }
            goto L_0x0069
        L_0x0084:
            zzj(r7, r6)     // Catch:{ IOException -> 0x013a }
        L_0x0087:
            r7.flush()     // Catch:{ IOException -> 0x013a }
            byte[] r2 = r2.zza     // Catch:{ IOException -> 0x0160 }
            r7.write(r2)     // Catch:{ IOException -> 0x0160 }
            r7.close()     // Catch:{ IOException -> 0x0160 }
            long r9 = r3.length()     // Catch:{ IOException -> 0x0160 }
            r8.zza = r9     // Catch:{ IOException -> 0x0160 }
            r15.zzo(r0, r8)     // Catch:{ IOException -> 0x0160 }
            long r7 = r1.zzb     // Catch:{ IOException -> 0x0160 }
            int r0 = r1.zzd     // Catch:{ IOException -> 0x0160 }
            long r9 = (long) r0     // Catch:{ IOException -> 0x0160 }
            int r0 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r0 >= 0) goto L_0x00a6
            goto L_0x0195
        L_0x00a6:
            boolean r0 = com.google.android.gms.internal.ads.zzarq.zzb     // Catch:{ IOException -> 0x0160 }
            if (r0 == 0) goto L_0x00b1
            java.lang.String r0 = "Pruning old cache entries."
            java.lang.Object[] r2 = new java.lang.Object[r6]     // Catch:{ IOException -> 0x0160 }
            com.google.android.gms.internal.ads.zzarq.zzd(r0, r2)     // Catch:{ IOException -> 0x0160 }
        L_0x00b1:
            long r7 = r1.zzb     // Catch:{ IOException -> 0x0160 }
            long r9 = android.os.SystemClock.elapsedRealtime()     // Catch:{ IOException -> 0x0160 }
            java.util.Map r0 = r1.zza     // Catch:{ IOException -> 0x0160 }
            java.util.Set r0 = r0.entrySet()     // Catch:{ IOException -> 0x0160 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ IOException -> 0x0160 }
            r2 = r6
        L_0x00c2:
            boolean r11 = r0.hasNext()     // Catch:{ IOException -> 0x0160 }
            r12 = 2
            if (r11 == 0) goto L_0x0111
            java.lang.Object r11 = r0.next()     // Catch:{ IOException -> 0x0160 }
            java.util.Map$Entry r11 = (java.util.Map.Entry) r11     // Catch:{ IOException -> 0x0160 }
            java.lang.Object r11 = r11.getValue()     // Catch:{ IOException -> 0x0160 }
            com.google.android.gms.internal.ads.zzarx r11 = (com.google.android.gms.internal.ads.zzarx) r11     // Catch:{ IOException -> 0x0160 }
            java.lang.String r13 = r11.zzb     // Catch:{ IOException -> 0x0160 }
            java.io.File r13 = r15.zzg(r13)     // Catch:{ IOException -> 0x0160 }
            boolean r13 = r13.delete()     // Catch:{ IOException -> 0x0160 }
            if (r13 == 0) goto L_0x00e9
            long r13 = r1.zzb     // Catch:{ IOException -> 0x0160 }
            long r4 = r11.zza     // Catch:{ IOException -> 0x0160 }
            long r13 = r13 - r4
            r1.zzb = r13     // Catch:{ IOException -> 0x0160 }
            goto L_0x00fb
        L_0x00e9:
            java.lang.String r4 = "Could not delete cache entry for key=%s, filename=%s"
            java.lang.String r5 = r11.zzb     // Catch:{ IOException -> 0x0160 }
            java.lang.String r11 = zzq(r5)     // Catch:{ IOException -> 0x0160 }
            java.lang.Object[] r13 = new java.lang.Object[r12]     // Catch:{ IOException -> 0x0160 }
            r13[r6] = r5     // Catch:{ IOException -> 0x0160 }
            r5 = 1
            r13[r5] = r11     // Catch:{ IOException -> 0x0160 }
            com.google.android.gms.internal.ads.zzarq.zza(r4, r13)     // Catch:{ IOException -> 0x0160 }
        L_0x00fb:
            r0.remove()     // Catch:{ IOException -> 0x0160 }
            int r2 = r2 + 1
            long r4 = r1.zzb     // Catch:{ IOException -> 0x0160 }
            float r4 = (float) r4     // Catch:{ IOException -> 0x0160 }
            int r5 = r1.zzd     // Catch:{ IOException -> 0x0160 }
            float r5 = (float) r5     // Catch:{ IOException -> 0x0160 }
            r11 = 1063675494(0x3f666666, float:0.9)
            float r5 = r5 * r11
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 >= 0) goto L_0x010f
            goto L_0x0111
        L_0x010f:
            r4 = r11
            goto L_0x00c2
        L_0x0111:
            boolean r0 = com.google.android.gms.internal.ads.zzarq.zzb     // Catch:{ IOException -> 0x0160 }
            if (r0 == 0) goto L_0x0195
            java.lang.String r0 = "pruned %d files, %d bytes, %d ms"
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ IOException -> 0x0160 }
            long r4 = r1.zzb     // Catch:{ IOException -> 0x0160 }
            long r4 = r4 - r7
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ IOException -> 0x0160 }
            long r7 = android.os.SystemClock.elapsedRealtime()     // Catch:{ IOException -> 0x0160 }
            long r7 = r7 - r9
            java.lang.Long r5 = java.lang.Long.valueOf(r7)     // Catch:{ IOException -> 0x0160 }
            r7 = 3
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ IOException -> 0x0160 }
            r7[r6] = r2     // Catch:{ IOException -> 0x0160 }
            r2 = 1
            r7[r2] = r4     // Catch:{ IOException -> 0x0160 }
            r7[r12] = r5     // Catch:{ IOException -> 0x0160 }
            com.google.android.gms.internal.ads.zzarq.zzd(r0, r7)     // Catch:{ IOException -> 0x0160 }
            monitor-exit(r15)
            return
        L_0x013a:
            r0 = move-exception
            java.lang.String r2 = "%s"
            java.lang.String r0 = r0.toString()     // Catch:{ IOException -> 0x0160 }
            r4 = 1
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch:{ IOException -> 0x0160 }
            r5[r6] = r0     // Catch:{ IOException -> 0x0160 }
            com.google.android.gms.internal.ads.zzarq.zza(r2, r5)     // Catch:{ IOException -> 0x0160 }
            r7.close()     // Catch:{ IOException -> 0x0160 }
            java.lang.String r0 = "Failed to write header for %s"
            java.lang.String r2 = r3.getAbsolutePath()     // Catch:{ IOException -> 0x0160 }
            r4 = 1
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch:{ IOException -> 0x0160 }
            r5[r6] = r2     // Catch:{ IOException -> 0x0160 }
            com.google.android.gms.internal.ads.zzarq.zza(r0, r5)     // Catch:{ IOException -> 0x0160 }
            java.io.IOException r0 = new java.io.IOException     // Catch:{ IOException -> 0x0160 }
            r0.<init>()     // Catch:{ IOException -> 0x0160 }
            throw r0     // Catch:{ IOException -> 0x0160 }
        L_0x0160:
            boolean r0 = r3.delete()     // Catch:{ all -> 0x0197 }
            if (r0 != 0) goto L_0x0174
            java.lang.String r0 = r3.getAbsolutePath()     // Catch:{ all -> 0x0197 }
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x0197 }
            r2[r6] = r0     // Catch:{ all -> 0x0197 }
            java.lang.String r0 = "Could not clean up file %s"
            com.google.android.gms.internal.ads.zzarq.zza(r0, r2)     // Catch:{ all -> 0x0197 }
        L_0x0174:
            com.google.android.gms.internal.ads.zzarz r0 = r1.zzc     // Catch:{ all -> 0x0197 }
            java.io.File r0 = r0.zza()     // Catch:{ all -> 0x0197 }
            boolean r0 = r0.exists()     // Catch:{ all -> 0x0197 }
            if (r0 != 0) goto L_0x0195
            java.lang.Object[] r0 = new java.lang.Object[r6]     // Catch:{ all -> 0x0197 }
            java.lang.String r2 = "Re-initializing cache after external clearing."
            com.google.android.gms.internal.ads.zzarq.zza(r2, r0)     // Catch:{ all -> 0x0197 }
            java.util.Map r0 = r1.zza     // Catch:{ all -> 0x0197 }
            r0.clear()     // Catch:{ all -> 0x0197 }
            r2 = 0
            r1.zzb = r2     // Catch:{ all -> 0x0197 }
            r15.zzb()     // Catch:{ all -> 0x0197 }
            monitor-exit(r15)
            return
        L_0x0195:
            monitor-exit(r15)
            return
        L_0x0197:
            r0 = move-exception
            monitor-exit(r15)     // Catch:{ all -> 0x0197 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzasa.zzd(java.lang.String, com.google.android.gms.internal.ads.zzaqn):void");
    }

    public final File zzg(String str) {
        return new File(this.zzc.zza(), zzq(str));
    }

    public final synchronized void zzi(String str) {
        boolean delete = zzg(str).delete();
        zzp(str);
        if (!delete) {
            zzarq.zza("Could not delete cache entry for key=%s, filename=%s", str, zzq(str));
        }
    }

    public zzasa(File file, int i) {
        this.zzc = new zzarw(this, file);
        this.zzd = 20971520;
    }
}
