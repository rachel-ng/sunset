package org.apache.commons.math3.exception;

import org.apache.commons.math3.exception.util.LocalizedFormats;

public class NoBracketingException extends MathIllegalArgumentException {
    private static final long serialVersionUID = -3629324471511904459L;
    private final double fHi;
    private final double fLo;
    private final double hi;
    private final double lo;

    public NoBracketingException(double d, double d2, double d3, double d4) {
        this(LocalizedFormats.SAME_SIGN_AT_ENDPOINTS, d, d2, d3, d4, new Object[0]);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public NoBracketingException(org.apache.commons.math3.exception.util.Localizable r8, double r9, double r11, double r13, double r15, java.lang.Object... r17) {
        /*
            r7 = this;
            r0 = r7
            java.lang.Double r1 = java.lang.Double.valueOf(r9)
            java.lang.Double r2 = java.lang.Double.valueOf(r11)
            java.lang.Double r3 = java.lang.Double.valueOf(r13)
            java.lang.Double r4 = java.lang.Double.valueOf(r15)
            r5 = 5
            java.lang.Object[] r5 = new java.lang.Object[r5]
            r6 = 0
            r5[r6] = r1
            r1 = 1
            r5[r1] = r2
            r1 = 2
            r5[r1] = r3
            r1 = 3
            r5[r1] = r4
            r1 = 4
            r5[r1] = r17
            r1 = r8
            r7.<init>(r8, r5)
            r1 = r9
            r0.lo = r1
            r1 = r11
            r0.hi = r1
            r1 = r13
            r0.fLo = r1
            r1 = r15
            r0.fHi = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.exception.NoBracketingException.<init>(org.apache.commons.math3.exception.util.Localizable, double, double, double, double, java.lang.Object[]):void");
    }

    public double getLo() {
        return this.lo;
    }

    public double getHi() {
        return this.hi;
    }

    public double getFLo() {
        return this.fLo;
    }

    public double getFHi() {
        return this.fHi;
    }
}
