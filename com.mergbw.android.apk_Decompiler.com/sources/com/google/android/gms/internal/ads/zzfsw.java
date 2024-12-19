package com.google.android.gms.internal.ads;

import java.io.File;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfsw {
    private final zzbac zza;
    private final File zzb;
    private final File zzc;
    private final File zzd;
    private byte[] zze;

    public zzfsw(zzbac zzbac, File file, File file2, File file3) {
        this.zza = zzbac;
        this.zzb = file;
        this.zzc = file3;
        this.zzd = file2;
    }

    public final zzbac zza() {
        return this.zza;
    }

    public final File zzb() {
        return this.zzc;
    }

    public final File zzc() {
        return this.zzb;
    }

    public final boolean zzd(long j) {
        return this.zza.zzc() - (System.currentTimeMillis() / 1000) < 3600;
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0059 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x005a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final byte[] zze() {
        /*
            r9 = this;
            byte[] r0 = r9.zze
            r1 = 0
            if (r0 != 0) goto L_0x0055
            java.io.File r0 = r9.zzd
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ IOException -> 0x004e, all -> 0x0049 }
            r2.<init>(r0)     // Catch:{ IOException -> 0x004e, all -> 0x0049 }
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ IOException -> 0x004f, all -> 0x0046 }
            r0.<init>()     // Catch:{ IOException -> 0x004f, all -> 0x0046 }
            r3 = 256(0x100, float:3.59E-43)
        L_0x0013:
            byte[] r4 = new byte[r3]     // Catch:{ IOException -> 0x004f, all -> 0x0046 }
            r5 = 0
            r6 = r5
        L_0x0017:
            if (r6 >= r3) goto L_0x0025
            int r7 = r3 - r6
            int r7 = r2.read(r4, r6, r7)     // Catch:{ IOException -> 0x004f, all -> 0x0046 }
            r8 = -1
            if (r7 != r8) goto L_0x0023
            goto L_0x0025
        L_0x0023:
            int r6 = r6 + r7
            goto L_0x0017
        L_0x0025:
            if (r6 != 0) goto L_0x0029
            r4 = r1
            goto L_0x002d
        L_0x0029:
            com.google.android.gms.internal.ads.zzhac r4 = com.google.android.gms.internal.ads.zzhac.zzv(r4, r5, r6)     // Catch:{ IOException -> 0x004f, all -> 0x0046 }
        L_0x002d:
            if (r4 != 0) goto L_0x003b
            com.google.android.gms.internal.ads.zzhac r0 = com.google.android.gms.internal.ads.zzhac.zzu(r0)     // Catch:{ IOException -> 0x004f, all -> 0x0046 }
            byte[] r0 = r0.zzB()     // Catch:{ IOException -> 0x004f, all -> 0x0046 }
            com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r2)
            goto L_0x0053
        L_0x003b:
            r0.add(r4)     // Catch:{ IOException -> 0x004f, all -> 0x0046 }
            int r3 = r3 + r3
            r4 = 8192(0x2000, float:1.14794E-41)
            int r3 = java.lang.Math.min(r3, r4)     // Catch:{ IOException -> 0x004f, all -> 0x0046 }
            goto L_0x0013
        L_0x0046:
            r0 = move-exception
            r1 = r2
            goto L_0x004a
        L_0x0049:
            r0 = move-exception
        L_0x004a:
            com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r1)
            throw r0
        L_0x004e:
            r2 = r1
        L_0x004f:
            com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r2)
            r0 = r1
        L_0x0053:
            r9.zze = r0
        L_0x0055:
            byte[] r0 = r9.zze
            if (r0 != 0) goto L_0x005a
            return r1
        L_0x005a:
            int r1 = r0.length
            byte[] r0 = java.util.Arrays.copyOf(r0, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzfsw.zze():byte[]");
    }
}
