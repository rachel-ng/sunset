package org.apache.commons.math3.stat.inference;

public class BinomialTest {
    public boolean binomialTest(int i, int i2, double d, AlternativeHypothesis alternativeHypothesis, double d2) {
        return binomialTest(i, i2, d, alternativeHypothesis) < d2;
    }

    /* renamed from: org.apache.commons.math3.stat.inference.BinomialTest$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$math3$stat$inference$AlternativeHypothesis;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                org.apache.commons.math3.stat.inference.AlternativeHypothesis[] r0 = org.apache.commons.math3.stat.inference.AlternativeHypothesis.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$commons$math3$stat$inference$AlternativeHypothesis = r0
                org.apache.commons.math3.stat.inference.AlternativeHypothesis r1 = org.apache.commons.math3.stat.inference.AlternativeHypothesis.GREATER_THAN     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$commons$math3$stat$inference$AlternativeHypothesis     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.commons.math3.stat.inference.AlternativeHypothesis r1 = org.apache.commons.math3.stat.inference.AlternativeHypothesis.LESS_THAN     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$commons$math3$stat$inference$AlternativeHypothesis     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.commons.math3.stat.inference.AlternativeHypothesis r1 = org.apache.commons.math3.stat.inference.AlternativeHypothesis.TWO_SIDED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.stat.inference.BinomialTest.AnonymousClass1.<clinit>():void");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x004f A[EDGE_INSN: B:41:0x004f->B:24:0x004f ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public double binomialTest(int r10, int r11, double r12, org.apache.commons.math3.stat.inference.AlternativeHypothesis r14) {
        /*
            r9 = this;
            if (r10 < 0) goto L_0x00a8
            if (r11 < 0) goto L_0x009e
            r0 = 0
            int r2 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            r3 = 0
            r4 = 1
            if (r2 < 0) goto L_0x008c
            r5 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r2 = (r12 > r5 ? 1 : (r12 == r5 ? 0 : -1))
            if (r2 > 0) goto L_0x008c
            r2 = 2
            if (r10 < r11) goto L_0x0076
            if (r14 == 0) goto L_0x0070
            org.apache.commons.math3.distribution.BinomialDistribution r7 = new org.apache.commons.math3.distribution.BinomialDistribution
            r8 = 0
            r7.<init>(r8, r10, r12)
            int[] r12 = org.apache.commons.math3.stat.inference.BinomialTest.AnonymousClass1.$SwitchMap$org$apache$commons$math3$stat$inference$AlternativeHypothesis
            int r13 = r14.ordinal()
            r12 = r12[r13]
            if (r12 == r4) goto L_0x0069
            if (r12 == r2) goto L_0x0064
            r13 = 3
            if (r12 != r13) goto L_0x0050
        L_0x002c:
            double r12 = r7.probability(r3)
            double r4 = r7.probability(r10)
            int r14 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r14 != 0) goto L_0x0041
            r4 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r12 = r12 * r4
            double r0 = r0 + r12
            int r3 = r3 + 1
        L_0x003e:
            int r10 = r10 + -1
            goto L_0x004b
        L_0x0041:
            int r14 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r14 >= 0) goto L_0x0049
            double r0 = r0 + r12
            int r3 = r3 + 1
            goto L_0x004b
        L_0x0049:
            double r0 = r0 + r4
            goto L_0x003e
        L_0x004b:
            if (r3 > r11) goto L_0x004f
            if (r10 >= r11) goto L_0x002c
        L_0x004f:
            return r0
        L_0x0050:
            org.apache.commons.math3.exception.MathInternalError r10 = new org.apache.commons.math3.exception.MathInternalError
            org.apache.commons.math3.exception.util.LocalizedFormats r11 = org.apache.commons.math3.exception.util.LocalizedFormats.OUT_OF_RANGE_SIMPLE
            java.lang.Object[] r12 = new java.lang.Object[r13]
            r12[r3] = r14
            org.apache.commons.math3.stat.inference.AlternativeHypothesis r13 = org.apache.commons.math3.stat.inference.AlternativeHypothesis.TWO_SIDED
            r12[r4] = r13
            org.apache.commons.math3.stat.inference.AlternativeHypothesis r13 = org.apache.commons.math3.stat.inference.AlternativeHypothesis.LESS_THAN
            r12[r2] = r13
            r10.<init>(r11, r12)
            throw r10
        L_0x0064:
            double r10 = r7.cumulativeProbability(r11)
            return r10
        L_0x0069:
            int r11 = r11 - r4
            double r10 = r7.cumulativeProbability(r11)
            double r5 = r5 - r10
            return r5
        L_0x0070:
            org.apache.commons.math3.exception.NullArgumentException r10 = new org.apache.commons.math3.exception.NullArgumentException
            r10.<init>()
            throw r10
        L_0x0076:
            org.apache.commons.math3.exception.MathIllegalArgumentException r12 = new org.apache.commons.math3.exception.MathIllegalArgumentException
            org.apache.commons.math3.exception.util.LocalizedFormats r13 = org.apache.commons.math3.exception.util.LocalizedFormats.BINOMIAL_INVALID_PARAMETERS_ORDER
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            java.lang.Object[] r14 = new java.lang.Object[r2]
            r14[r3] = r10
            r14[r4] = r11
            r12.<init>(r13, r14)
            throw r12
        L_0x008c:
            org.apache.commons.math3.exception.OutOfRangeException r10 = new org.apache.commons.math3.exception.OutOfRangeException
            java.lang.Double r11 = java.lang.Double.valueOf(r12)
            java.lang.Integer r12 = java.lang.Integer.valueOf(r3)
            java.lang.Integer r13 = java.lang.Integer.valueOf(r4)
            r10.<init>(r11, r12, r13)
            throw r10
        L_0x009e:
            org.apache.commons.math3.exception.NotPositiveException r10 = new org.apache.commons.math3.exception.NotPositiveException
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            r10.<init>(r11)
            throw r10
        L_0x00a8:
            org.apache.commons.math3.exception.NotPositiveException r11 = new org.apache.commons.math3.exception.NotPositiveException
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            r11.<init>(r10)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.stat.inference.BinomialTest.binomialTest(int, int, double, org.apache.commons.math3.stat.inference.AlternativeHypothesis):double");
    }
}
