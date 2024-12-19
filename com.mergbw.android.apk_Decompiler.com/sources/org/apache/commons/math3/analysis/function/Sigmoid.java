package org.apache.commons.math3.analysis.function;

import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
import org.apache.commons.math3.analysis.FunctionUtils;
import org.apache.commons.math3.analysis.ParametricUnivariateFunction;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.util.FastMath;

public class Sigmoid implements UnivariateDifferentiableFunction, DifferentiableUnivariateFunction {
    private final double hi;
    private final double lo;

    public Sigmoid() {
        this(0.0d, 1.0d);
    }

    public Sigmoid(double d, double d2) {
        this.lo = d;
        this.hi = d2;
    }

    @Deprecated
    public UnivariateFunction derivative() {
        return FunctionUtils.toDifferentiableUnivariateFunction(this).derivative();
    }

    public double value(double d) {
        return value(d, this.lo, this.hi);
    }

    public static class Parametric implements ParametricUnivariateFunction {
        public double value(double d, double... dArr) throws NullArgumentException, DimensionMismatchException {
            validateParameters(dArr);
            return Sigmoid.value(d, dArr[0], dArr[1]);
        }

        public double[] gradient(double d, double... dArr) throws NullArgumentException, DimensionMismatchException {
            validateParameters(dArr);
            double exp = 1.0d / (FastMath.exp(-d) + 1.0d);
            return new double[]{1.0d - exp, exp};
        }

        private void validateParameters(double[] dArr) throws NullArgumentException, DimensionMismatchException {
            if (dArr == null) {
                throw new NullArgumentException();
            } else if (dArr.length != 2) {
                throw new DimensionMismatchException(dArr.length, 2);
            }
        }
    }

    /* access modifiers changed from: private */
    public static double value(double d, double d2, double d3) {
        return d2 + ((d3 - d2) / (FastMath.exp(-d) + 1.0d));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v2, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v4, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v11, resolved type: boolean} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.commons.math3.analysis.differentiation.DerivativeStructure value(org.apache.commons.math3.analysis.differentiation.DerivativeStructure r25) throws org.apache.commons.math3.exception.DimensionMismatchException {
        /*
            r24 = this;
            r0 = r24
            int r1 = r25.getOrder()
            r2 = 1
            int r1 = r1 + r2
            double[] r3 = new double[r1]
            double r4 = r25.getValue()
            double r4 = -r4
            double r4 = org.apache.commons.math3.util.FastMath.exp(r4)
            boolean r6 = java.lang.Double.isInfinite(r4)
            r7 = 0
            r9 = 0
            if (r6 == 0) goto L_0x0026
            double r4 = r0.lo
            r3[r9] = r4
            java.util.Arrays.fill(r3, r2, r1, r7)
        L_0x0023:
            r1 = r25
            goto L_0x0085
        L_0x0026:
            double[] r6 = new double[r1]
            r10 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r12 = r4 + r10
            double r12 = r10 / r12
            double r14 = r0.hi
            double r7 = r0.lo
            double r14 = r14 - r7
            r7 = r9
        L_0x0034:
            if (r7 >= r1) goto L_0x007c
            r6[r7] = r10
            r8 = r7
            r16 = 0
        L_0x003b:
            if (r8 < 0) goto L_0x006d
            double r16 = r16 * r4
            r18 = r6[r8]
            double r16 = r16 + r18
            if (r8 <= r2) goto L_0x0061
            int r2 = r8 + -1
            int r19 = r7 - r8
            int r10 = r19 + 2
            double r10 = (double) r10
            int r19 = r8 + -2
            r20 = r6[r19]
            double r10 = r10 * r20
            r20 = r10
            double r9 = (double) r2
            r22 = r6[r2]
            double r9 = r9 * r22
            double r10 = r20 - r9
            r6[r2] = r10
            r2 = 0
            r9 = 0
            goto L_0x0066
        L_0x0061:
            r2 = r9
            r9 = 0
            r6[r2] = r9
        L_0x0066:
            int r8 = r8 + -1
            r9 = r2
            r2 = 1
            r10 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            goto L_0x003b
        L_0x006d:
            r2 = r9
            r9 = 0
            double r14 = r14 * r12
            double r16 = r16 * r14
            r3[r7] = r16
            int r7 = r7 + 1
            r9 = r2
            r2 = 1
            r10 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            goto L_0x0034
        L_0x007c:
            r2 = r9
            r4 = r3[r2]
            double r6 = r0.lo
            double r4 = r4 + r6
            r3[r2] = r4
            goto L_0x0023
        L_0x0085:
            org.apache.commons.math3.analysis.differentiation.DerivativeStructure r1 = r1.compose(r3)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.analysis.function.Sigmoid.value(org.apache.commons.math3.analysis.differentiation.DerivativeStructure):org.apache.commons.math3.analysis.differentiation.DerivativeStructure");
    }
}
