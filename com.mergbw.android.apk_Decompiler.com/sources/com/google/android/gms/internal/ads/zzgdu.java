package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgdu {
    private static final long[][] zza = {new long[]{291830, 126401071349994536L}, new long[]{885594168, 725270293939359937L, 3569819667048198375L}, new long[]{273919523040L, 15, 7363882082L, 992620450144556L}, new long[]{47636622961200L, 2, 2570940, 211991001, 3749873356L}, new long[]{7999252175582850L, 2, 4130806001517L, 149795463772692060L, 186635894390467037L, 3967304179347715805L}, new long[]{585226005592931976L, 2, 123635709730000L, 9233062284813009L, 43835965440333360L, 761179012939631437L, 1263739024124850375L}, new long[]{Long.MAX_VALUE, 2, 325, 9375, 28178, 450775, 9780504, 1795265022}};

    public static long zzb(long j, long j2) {
        zzgdv.zza("a", j);
        zzgdv.zza("b", j2);
        if (j == 0) {
            return j2;
        }
        if (j2 == 0) {
            return j;
        }
        int numberOfTrailingZeros = Long.numberOfTrailingZeros(j);
        long j3 = j >> numberOfTrailingZeros;
        int numberOfTrailingZeros2 = Long.numberOfTrailingZeros(j2);
        long j4 = j2 >> numberOfTrailingZeros2;
        while (j3 != j4) {
            long j5 = j3 - j4;
            long j6 = (j5 >> 63) & j5;
            long j7 = (j5 - j6) - j6;
            j3 = j7 >> Long.numberOfTrailingZeros(j7);
            j4 += j6;
        }
        return j3 << Math.min(numberOfTrailingZeros, numberOfTrailingZeros2);
    }

    public static long zzc(long j, long j2) {
        int numberOfLeadingZeros = Long.numberOfLeadingZeros(j) + Long.numberOfLeadingZeros(~j) + Long.numberOfLeadingZeros(j2) + Long.numberOfLeadingZeros(~j2);
        if (numberOfLeadingZeros > 65) {
            return j * j2;
        }
        long j3 = j ^ j2;
        boolean z = true;
        boolean z2 = numberOfLeadingZeros < 64;
        int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
        boolean z3 = i < 0;
        if (j2 != Long.MIN_VALUE) {
            z = false;
        }
        long j4 = (j3 >>> 63) + Long.MAX_VALUE;
        if (z2 || (z & z3)) {
            return j4;
        }
        long j5 = j * j2;
        return (i == 0 || j5 / j == j2) ? j5 : j4;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0049, code lost:
        if (r8 > 0) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004c, code lost:
        if (r8 < 0) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0050, code lost:
        return r0 + ((long) r8);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long zza(long r8, long r10, java.math.RoundingMode r12) {
        /*
            r12.getClass()
            long r0 = r8 / r10
            long r2 = r10 * r0
            long r2 = r8 - r2
            r4 = 0
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 != 0) goto L_0x0010
            goto L_0x0055
        L_0x0010:
            long r8 = r8 ^ r10
            int[] r6 = com.google.android.gms.internal.ads.zzgdt.zza
            int r7 = r12.ordinal()
            r6 = r6[r7]
            r7 = 63
            long r8 = r8 >> r7
            int r8 = (int) r8
            r8 = r8 | 1
            switch(r6) {
                case 1: goto L_0x0051;
                case 2: goto L_0x0055;
                case 3: goto L_0x004c;
                case 4: goto L_0x004e;
                case 5: goto L_0x0049;
                case 6: goto L_0x0028;
                case 7: goto L_0x0028;
                case 8: goto L_0x0028;
                default: goto L_0x0022;
            }
        L_0x0022:
            java.lang.AssertionError r8 = new java.lang.AssertionError
            r8.<init>()
            throw r8
        L_0x0028:
            long r2 = java.lang.Math.abs(r2)
            long r9 = java.lang.Math.abs(r10)
            long r9 = r9 - r2
            long r2 = r2 - r9
            int r9 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r9 != 0) goto L_0x0046
            java.math.RoundingMode r9 = java.math.RoundingMode.HALF_UP
            if (r12 == r9) goto L_0x004e
            java.math.RoundingMode r9 = java.math.RoundingMode.HALF_EVEN
            if (r12 != r9) goto L_0x0055
            r9 = 1
            long r9 = r9 & r0
            int r9 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            if (r9 == 0) goto L_0x0055
            goto L_0x004e
        L_0x0046:
            if (r9 <= 0) goto L_0x0055
            goto L_0x004e
        L_0x0049:
            if (r8 <= 0) goto L_0x0055
            goto L_0x004e
        L_0x004c:
            if (r8 >= 0) goto L_0x0055
        L_0x004e:
            long r8 = (long) r8
            long r0 = r0 + r8
            return r0
        L_0x0051:
            r8 = 0
            com.google.android.gms.internal.ads.zzgdv.zzb(r8)
        L_0x0055:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzgdu.zza(long, long, java.math.RoundingMode):long");
    }
}
