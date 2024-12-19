package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgdp {
    static {
        Math.log(2.0d);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0073, code lost:
        r4 = (double) (r2 + r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x007d, code lost:
        r4 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0086, code lost:
        if ((-9.223372036854776E18d - r4) >= 1.0d) goto L_0x008a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0088, code lost:
        r0 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x008a, code lost:
        r0 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x008f, code lost:
        if (r4 >= 9.223372036854776E18d) goto L_0x0092;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0092, code lost:
        r1 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0094, code lost:
        if ((r0 & r1) == false) goto L_0x0098;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0097, code lost:
        return (long) r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00b7, code lost:
        throw new java.lang.ArithmeticException("rounded value is out of range for input " + r8 + " and rounding mode " + java.lang.String.valueOf(r10));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0028, code lost:
        if (java.lang.Math.abs(r8 - r4) == 0.5d) goto L_0x007d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long zza(double r8, java.math.RoundingMode r10) {
        /*
            boolean r0 = com.google.android.gms.internal.ads.zzgdq.zza(r8)
            if (r0 == 0) goto L_0x00b8
            int[] r0 = com.google.android.gms.internal.ads.zzgdo.zza
            int r1 = r10.ordinal()
            r0 = r0[r1]
            r1 = 1
            r2 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            r4 = 0
            switch(r0) {
                case 1: goto L_0x0076;
                case 2: goto L_0x0065;
                case 3: goto L_0x0056;
                case 4: goto L_0x007d;
                case 5: goto L_0x0045;
                case 6: goto L_0x0040;
                case 7: goto L_0x002b;
                case 8: goto L_0x001c;
                default: goto L_0x0016;
            }
        L_0x0016:
            java.lang.AssertionError r8 = new java.lang.AssertionError
            r8.<init>()
            throw r8
        L_0x001c:
            double r4 = java.lang.Math.rint(r8)
            double r6 = r8 - r4
            double r6 = java.lang.Math.abs(r6)
            int r0 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r0 != 0) goto L_0x007e
            goto L_0x007d
        L_0x002b:
            double r4 = java.lang.Math.rint(r8)
            double r6 = r8 - r4
            double r6 = java.lang.Math.abs(r6)
            int r0 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r0 != 0) goto L_0x007e
            double r2 = java.lang.Math.copySign(r2, r8)
            double r4 = r8 + r2
            goto L_0x007e
        L_0x0040:
            double r4 = java.lang.Math.rint(r8)
            goto L_0x007e
        L_0x0045:
            boolean r0 = zzb(r8)
            if (r0 == 0) goto L_0x004c
            goto L_0x007d
        L_0x004c:
            long r2 = (long) r8
            int r0 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r0 <= 0) goto L_0x0053
            r0 = r1
            goto L_0x0054
        L_0x0053:
            r0 = -1
        L_0x0054:
            long r4 = (long) r0
            goto L_0x0073
        L_0x0056:
            int r0 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r0 <= 0) goto L_0x007d
            boolean r0 = zzb(r8)
            if (r0 == 0) goto L_0x0061
            goto L_0x007d
        L_0x0061:
            long r2 = (long) r8
            r4 = 1
            goto L_0x0073
        L_0x0065:
            int r0 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r0 >= 0) goto L_0x007d
            boolean r0 = zzb(r8)
            if (r0 == 0) goto L_0x0070
            goto L_0x007d
        L_0x0070:
            long r2 = (long) r8
            r4 = -1
        L_0x0073:
            long r2 = r2 + r4
            double r4 = (double) r2
            goto L_0x007e
        L_0x0076:
            boolean r0 = zzb(r8)
            com.google.android.gms.internal.ads.zzgdv.zzb(r0)
        L_0x007d:
            r4 = r8
        L_0x007e:
            r2 = -4332462841530417152(0xc3e0000000000000, double:-9.223372036854776E18)
            double r2 = r2 - r4
            r6 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r0 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            r2 = 0
            if (r0 >= 0) goto L_0x008a
            r0 = r1
            goto L_0x008b
        L_0x008a:
            r0 = r2
        L_0x008b:
            r6 = 4890909195324358656(0x43e0000000000000, double:9.223372036854776E18)
            int r3 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r3 >= 0) goto L_0x0092
            goto L_0x0093
        L_0x0092:
            r1 = r2
        L_0x0093:
            r0 = r0 & r1
            if (r0 == 0) goto L_0x0098
            long r8 = (long) r4
            return r8
        L_0x0098:
            java.lang.ArithmeticException r0 = new java.lang.ArithmeticException
            java.lang.String r10 = java.lang.String.valueOf(r10)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "rounded value is out of range for input "
            r1.<init>(r2)
            r1.append(r8)
            java.lang.String r8 = " and rounding mode "
            r1.append(r8)
            r1.append(r10)
            java.lang.String r8 = r1.toString()
            r0.<init>(r8)
            throw r0
        L_0x00b8:
            java.lang.ArithmeticException r8 = new java.lang.ArithmeticException
            java.lang.String r9 = "input is infinite or NaN"
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzgdp.zza(double, java.math.RoundingMode):long");
    }

    public static boolean zzb(double d) {
        if (!zzgdq.zza(d)) {
            return false;
        }
        if (d == 0.0d) {
            return true;
        }
        zzfyg.zzf(zzgdq.zza(d), "not a normal value");
        int exponent = Math.getExponent(d);
        long doubleToRawLongBits = Double.doubleToRawLongBits(d) & 4503599627370495L;
        return 52 - Long.numberOfTrailingZeros(exponent == -1023 ? doubleToRawLongBits + doubleToRawLongBits : doubleToRawLongBits | 4503599627370496L) <= Math.getExponent(d);
    }
}
