package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgds {
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003e, code lost:
        if (((r6 & 1) & r3) != 0) goto L_0x0049;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0041, code lost:
        if (r0 > 0) goto L_0x0049;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0044, code lost:
        if (r5 > 0) goto L_0x0049;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0047, code lost:
        if (r5 < 0) goto L_0x0049;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004a, code lost:
        return r6 + r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int zza(int r5, int r6, java.math.RoundingMode r7) {
        /*
            r7.getClass()
            int r6 = r5 / 8
            int r0 = r6 * 8
            int r0 = r5 - r0
            if (r0 != 0) goto L_0x000c
            goto L_0x004e
        L_0x000c:
            r1 = 8
            r5 = r5 ^ r1
            int[] r2 = com.google.android.gms.internal.ads.zzgdr.zza
            int r3 = r7.ordinal()
            r2 = r2[r3]
            int r5 = r5 >> 31
            r3 = 1
            r5 = r5 | r3
            r4 = 0
            switch(r2) {
                case 1: goto L_0x004b;
                case 2: goto L_0x004e;
                case 3: goto L_0x0047;
                case 4: goto L_0x0049;
                case 5: goto L_0x0044;
                case 6: goto L_0x0025;
                case 7: goto L_0x0025;
                case 8: goto L_0x0025;
                default: goto L_0x001f;
            }
        L_0x001f:
            java.lang.AssertionError r5 = new java.lang.AssertionError
            r5.<init>()
            throw r5
        L_0x0025:
            int r0 = java.lang.Math.abs(r0)
            int r1 = java.lang.Math.abs(r1)
            int r1 = r1 - r0
            int r0 = r0 - r1
            if (r0 != 0) goto L_0x0041
            java.math.RoundingMode r0 = java.math.RoundingMode.HALF_UP
            if (r7 == r0) goto L_0x0049
            java.math.RoundingMode r0 = java.math.RoundingMode.HALF_EVEN
            if (r7 != r0) goto L_0x003a
            goto L_0x003b
        L_0x003a:
            r3 = r4
        L_0x003b:
            r7 = r6 & 1
            r7 = r7 & r3
            if (r7 == 0) goto L_0x004e
            goto L_0x0049
        L_0x0041:
            if (r0 <= 0) goto L_0x004e
            goto L_0x0049
        L_0x0044:
            if (r5 <= 0) goto L_0x004e
            goto L_0x0049
        L_0x0047:
            if (r5 >= 0) goto L_0x004e
        L_0x0049:
            int r6 = r6 + r5
            return r6
        L_0x004b:
            com.google.android.gms.internal.ads.zzgdv.zzb(r4)
        L_0x004e:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzgds.zza(int, int, java.math.RoundingMode):int");
    }
}
