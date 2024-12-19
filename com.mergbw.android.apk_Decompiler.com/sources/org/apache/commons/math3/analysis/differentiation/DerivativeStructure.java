package org.apache.commons.math3.analysis.differentiation;

import java.io.Serializable;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

public class DerivativeStructure implements RealFieldElement<DerivativeStructure>, Serializable {
    private static final long serialVersionUID = 20120730;
    /* access modifiers changed from: private */
    public transient DSCompiler compiler;
    private final double[] data;

    private DerivativeStructure(DSCompiler dSCompiler) {
        this.compiler = dSCompiler;
        this.data = new double[dSCompiler.getSize()];
    }

    public DerivativeStructure(int i, int i2) throws NumberIsTooLargeException {
        this(DSCompiler.getCompiler(i, i2));
    }

    public DerivativeStructure(int i, int i2, double d) throws NumberIsTooLargeException {
        this(i, i2);
        this.data[0] = d;
    }

    public DerivativeStructure(int i, int i2, int i3, double d) throws NumberIsTooLargeException {
        this(i, i2, d);
        if (i3 >= i) {
            throw new NumberIsTooLargeException(Integer.valueOf(i3), Integer.valueOf(i), false);
        } else if (i2 > 0) {
            this.data[DSCompiler.getCompiler(i3, i2).getSize()] = 1.0d;
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DerivativeStructure(double r17, org.apache.commons.math3.analysis.differentiation.DerivativeStructure r19, double r20, org.apache.commons.math3.analysis.differentiation.DerivativeStructure r22) throws org.apache.commons.math3.exception.DimensionMismatchException {
        /*
            r16 = this;
            r0 = r16
            r1 = r19
            r2 = r22
            org.apache.commons.math3.analysis.differentiation.DSCompiler r3 = r1.compiler
            r0.<init>((org.apache.commons.math3.analysis.differentiation.DSCompiler) r3)
            org.apache.commons.math3.analysis.differentiation.DSCompiler r3 = r0.compiler
            org.apache.commons.math3.analysis.differentiation.DSCompiler r4 = r2.compiler
            r3.checkCompatibility(r4)
            org.apache.commons.math3.analysis.differentiation.DSCompiler r5 = r0.compiler
            double[] r8 = r1.data
            double[] r12 = r2.data
            double[] r14 = r0.data
            r15 = 0
            r9 = 0
            r13 = 0
            r6 = r17
            r10 = r20
            r5.linearCombination(r6, r8, r9, r10, r12, r13, r14, r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.analysis.differentiation.DerivativeStructure.<init>(double, org.apache.commons.math3.analysis.differentiation.DerivativeStructure, double, org.apache.commons.math3.analysis.differentiation.DerivativeStructure):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DerivativeStructure(double r22, org.apache.commons.math3.analysis.differentiation.DerivativeStructure r24, double r25, org.apache.commons.math3.analysis.differentiation.DerivativeStructure r27, double r28, org.apache.commons.math3.analysis.differentiation.DerivativeStructure r30) throws org.apache.commons.math3.exception.DimensionMismatchException {
        /*
            r21 = this;
            r0 = r21
            r1 = r24
            r2 = r27
            r3 = r30
            org.apache.commons.math3.analysis.differentiation.DSCompiler r4 = r1.compiler
            r0.<init>((org.apache.commons.math3.analysis.differentiation.DSCompiler) r4)
            org.apache.commons.math3.analysis.differentiation.DSCompiler r4 = r0.compiler
            org.apache.commons.math3.analysis.differentiation.DSCompiler r5 = r2.compiler
            r4.checkCompatibility(r5)
            org.apache.commons.math3.analysis.differentiation.DSCompiler r4 = r0.compiler
            org.apache.commons.math3.analysis.differentiation.DSCompiler r5 = r3.compiler
            r4.checkCompatibility(r5)
            org.apache.commons.math3.analysis.differentiation.DSCompiler r6 = r0.compiler
            double[] r9 = r1.data
            double[] r13 = r2.data
            double[] r1 = r3.data
            double[] r2 = r0.data
            r20 = 0
            r10 = 0
            r14 = 0
            r18 = 0
            r7 = r22
            r11 = r25
            r15 = r28
            r17 = r1
            r19 = r2
            r6.linearCombination(r7, r9, r10, r11, r13, r14, r15, r17, r18, r19, r20)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.analysis.differentiation.DerivativeStructure.<init>(double, org.apache.commons.math3.analysis.differentiation.DerivativeStructure, double, org.apache.commons.math3.analysis.differentiation.DerivativeStructure, double, org.apache.commons.math3.analysis.differentiation.DerivativeStructure):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DerivativeStructure(double r25, org.apache.commons.math3.analysis.differentiation.DerivativeStructure r27, double r28, org.apache.commons.math3.analysis.differentiation.DerivativeStructure r30, double r31, org.apache.commons.math3.analysis.differentiation.DerivativeStructure r33, double r34, org.apache.commons.math3.analysis.differentiation.DerivativeStructure r36) throws org.apache.commons.math3.exception.DimensionMismatchException {
        /*
            r24 = this;
            r0 = r24
            r1 = r27
            r2 = r30
            r3 = r33
            r4 = r36
            r6 = r25
            r10 = r28
            r14 = r31
            r18 = r34
            org.apache.commons.math3.analysis.differentiation.DSCompiler r5 = r1.compiler
            r0.<init>((org.apache.commons.math3.analysis.differentiation.DSCompiler) r5)
            org.apache.commons.math3.analysis.differentiation.DSCompiler r5 = r0.compiler
            org.apache.commons.math3.analysis.differentiation.DSCompiler r8 = r2.compiler
            r5.checkCompatibility(r8)
            org.apache.commons.math3.analysis.differentiation.DSCompiler r5 = r0.compiler
            org.apache.commons.math3.analysis.differentiation.DSCompiler r8 = r3.compiler
            r5.checkCompatibility(r8)
            org.apache.commons.math3.analysis.differentiation.DSCompiler r5 = r0.compiler
            org.apache.commons.math3.analysis.differentiation.DSCompiler r8 = r4.compiler
            r5.checkCompatibility(r8)
            org.apache.commons.math3.analysis.differentiation.DSCompiler r5 = r0.compiler
            double[] r8 = r1.data
            double[] r12 = r2.data
            double[] r1 = r3.data
            r16 = r1
            double[] r1 = r4.data
            r20 = r1
            double[] r1 = r0.data
            r22 = r1
            r23 = 0
            r9 = 0
            r13 = 0
            r17 = 0
            r21 = 0
            r5.linearCombination(r6, r8, r9, r10, r12, r13, r14, r16, r17, r18, r20, r21, r22, r23)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.analysis.differentiation.DerivativeStructure.<init>(double, org.apache.commons.math3.analysis.differentiation.DerivativeStructure, double, org.apache.commons.math3.analysis.differentiation.DerivativeStructure, double, org.apache.commons.math3.analysis.differentiation.DerivativeStructure, double, org.apache.commons.math3.analysis.differentiation.DerivativeStructure):void");
    }

    public DerivativeStructure(int i, int i2, double... dArr) throws DimensionMismatchException, NumberIsTooLargeException {
        this(i, i2);
        int length = dArr.length;
        double[] dArr2 = this.data;
        if (length == dArr2.length) {
            System.arraycopy(dArr, 0, dArr2, 0, dArr2.length);
            return;
        }
        throw new DimensionMismatchException(dArr.length, this.data.length);
    }

    private DerivativeStructure(DerivativeStructure derivativeStructure) {
        this.compiler = derivativeStructure.compiler;
        this.data = (double[]) derivativeStructure.data.clone();
    }

    public int getFreeParameters() {
        return this.compiler.getFreeParameters();
    }

    public int getOrder() {
        return this.compiler.getOrder();
    }

    public DerivativeStructure createConstant(double d) {
        return new DerivativeStructure(getFreeParameters(), getOrder(), d);
    }

    public double getReal() {
        return this.data[0];
    }

    public double getValue() {
        return this.data[0];
    }

    public double getPartialDerivative(int... iArr) throws DimensionMismatchException, NumberIsTooLargeException {
        return this.data[this.compiler.getPartialDerivativeIndex(iArr)];
    }

    public double[] getAllDerivatives() {
        return (double[]) this.data.clone();
    }

    public DerivativeStructure add(double d) {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this);
        double[] dArr = derivativeStructure.data;
        dArr[0] = dArr[0] + d;
        return derivativeStructure;
    }

    public DerivativeStructure add(DerivativeStructure derivativeStructure) throws DimensionMismatchException {
        this.compiler.checkCompatibility(derivativeStructure.compiler);
        DerivativeStructure derivativeStructure2 = new DerivativeStructure(this);
        this.compiler.add(this.data, 0, derivativeStructure.data, 0, derivativeStructure2.data, 0);
        return derivativeStructure2;
    }

    public DerivativeStructure subtract(double d) {
        return add(-d);
    }

    public DerivativeStructure subtract(DerivativeStructure derivativeStructure) throws DimensionMismatchException {
        this.compiler.checkCompatibility(derivativeStructure.compiler);
        DerivativeStructure derivativeStructure2 = new DerivativeStructure(this);
        this.compiler.subtract(this.data, 0, derivativeStructure.data, 0, derivativeStructure2.data, 0);
        return derivativeStructure2;
    }

    public DerivativeStructure multiply(int i) {
        return multiply((double) i);
    }

    public DerivativeStructure multiply(double d) {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this);
        int i = 0;
        while (true) {
            double[] dArr = derivativeStructure.data;
            if (i >= dArr.length) {
                return derivativeStructure;
            }
            dArr[i] = dArr[i] * d;
            i++;
        }
    }

    public DerivativeStructure multiply(DerivativeStructure derivativeStructure) throws DimensionMismatchException {
        this.compiler.checkCompatibility(derivativeStructure.compiler);
        DerivativeStructure derivativeStructure2 = new DerivativeStructure(this.compiler);
        this.compiler.multiply(this.data, 0, derivativeStructure.data, 0, derivativeStructure2.data, 0);
        return derivativeStructure2;
    }

    public DerivativeStructure divide(double d) {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this);
        int i = 0;
        while (true) {
            double[] dArr = derivativeStructure.data;
            if (i >= dArr.length) {
                return derivativeStructure;
            }
            dArr[i] = dArr[i] / d;
            i++;
        }
    }

    public DerivativeStructure divide(DerivativeStructure derivativeStructure) throws DimensionMismatchException {
        this.compiler.checkCompatibility(derivativeStructure.compiler);
        DerivativeStructure derivativeStructure2 = new DerivativeStructure(this.compiler);
        this.compiler.divide(this.data, 0, derivativeStructure.data, 0, derivativeStructure2.data, 0);
        return derivativeStructure2;
    }

    public DerivativeStructure remainder(double d) {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this);
        double[] dArr = derivativeStructure.data;
        dArr[0] = FastMath.IEEEremainder(dArr[0], d);
        return derivativeStructure;
    }

    public DerivativeStructure remainder(DerivativeStructure derivativeStructure) throws DimensionMismatchException {
        this.compiler.checkCompatibility(derivativeStructure.compiler);
        DerivativeStructure derivativeStructure2 = new DerivativeStructure(this.compiler);
        this.compiler.remainder(this.data, 0, derivativeStructure.data, 0, derivativeStructure2.data, 0);
        return derivativeStructure2;
    }

    public DerivativeStructure negate() {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        int i = 0;
        while (true) {
            double[] dArr = derivativeStructure.data;
            if (i >= dArr.length) {
                return derivativeStructure;
            }
            dArr[i] = -this.data[i];
            i++;
        }
    }

    public DerivativeStructure abs() {
        return Double.doubleToLongBits(this.data[0]) < 0 ? negate() : this;
    }

    public DerivativeStructure ceil() {
        return new DerivativeStructure(this.compiler.getFreeParameters(), this.compiler.getOrder(), FastMath.ceil(this.data[0]));
    }

    public DerivativeStructure floor() {
        return new DerivativeStructure(this.compiler.getFreeParameters(), this.compiler.getOrder(), FastMath.floor(this.data[0]));
    }

    public DerivativeStructure rint() {
        return new DerivativeStructure(this.compiler.getFreeParameters(), this.compiler.getOrder(), FastMath.rint(this.data[0]));
    }

    public long round() {
        return FastMath.round(this.data[0]);
    }

    public DerivativeStructure signum() {
        return new DerivativeStructure(this.compiler.getFreeParameters(), this.compiler.getOrder(), FastMath.signum(this.data[0]));
    }

    public DerivativeStructure copySign(DerivativeStructure derivativeStructure) {
        long doubleToLongBits = Double.doubleToLongBits(this.data[0]);
        long doubleToLongBits2 = Double.doubleToLongBits(derivativeStructure.data[0]);
        int i = (doubleToLongBits > 0 ? 1 : (doubleToLongBits == 0 ? 0 : -1));
        if ((i < 0 || doubleToLongBits2 < 0) && (i >= 0 || doubleToLongBits2 >= 0)) {
            return negate();
        }
        return this;
    }

    public DerivativeStructure copySign(double d) {
        long doubleToLongBits = Double.doubleToLongBits(this.data[0]);
        long doubleToLongBits2 = Double.doubleToLongBits(d);
        int i = (doubleToLongBits > 0 ? 1 : (doubleToLongBits == 0 ? 0 : -1));
        if ((i < 0 || doubleToLongBits2 < 0) && (i >= 0 || doubleToLongBits2 >= 0)) {
            return negate();
        }
        return this;
    }

    public int getExponent() {
        return FastMath.getExponent(this.data[0]);
    }

    public DerivativeStructure scalb(int i) {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        int i2 = 0;
        while (true) {
            double[] dArr = derivativeStructure.data;
            if (i2 >= dArr.length) {
                return derivativeStructure;
            }
            dArr[i2] = FastMath.scalb(this.data[i2], i);
            i2++;
        }
    }

    public DerivativeStructure hypot(DerivativeStructure derivativeStructure) throws DimensionMismatchException {
        this.compiler.checkCompatibility(derivativeStructure.compiler);
        if (Double.isInfinite(this.data[0]) || Double.isInfinite(derivativeStructure.data[0])) {
            return new DerivativeStructure(this.compiler.getFreeParameters(), this.compiler.getFreeParameters(), Double.POSITIVE_INFINITY);
        }
        if (Double.isNaN(this.data[0]) || Double.isNaN(derivativeStructure.data[0])) {
            return new DerivativeStructure(this.compiler.getFreeParameters(), this.compiler.getFreeParameters(), Double.NaN);
        }
        int exponent = getExponent();
        int exponent2 = derivativeStructure.getExponent();
        if (exponent > exponent2 + 27) {
            return abs();
        }
        if (exponent2 > exponent + 27) {
            return derivativeStructure.abs();
        }
        int i = (exponent + exponent2) / 2;
        int i2 = -i;
        DerivativeStructure scalb = scalb(i2);
        DerivativeStructure scalb2 = derivativeStructure.scalb(i2);
        return scalb.multiply(scalb).add(scalb2.multiply(scalb2)).sqrt().scalb(i);
    }

    public static DerivativeStructure hypot(DerivativeStructure derivativeStructure, DerivativeStructure derivativeStructure2) throws DimensionMismatchException {
        return derivativeStructure.hypot(derivativeStructure2);
    }

    public DerivativeStructure compose(double... dArr) throws DimensionMismatchException {
        if (dArr.length == getOrder() + 1) {
            DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
            this.compiler.compose(this.data, 0, dArr, derivativeStructure.data, 0);
            return derivativeStructure;
        }
        throw new DimensionMismatchException(dArr.length, getOrder() + 1);
    }

    public DerivativeStructure reciprocal() {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        this.compiler.pow(this.data, 0, -1, derivativeStructure.data, 0);
        return derivativeStructure;
    }

    public DerivativeStructure sqrt() {
        return rootN(2);
    }

    public DerivativeStructure cbrt() {
        return rootN(3);
    }

    public DerivativeStructure rootN(int i) {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        this.compiler.rootN(this.data, 0, i, derivativeStructure.data, 0);
        return derivativeStructure;
    }

    public Field<DerivativeStructure> getField() {
        return new Field<DerivativeStructure>() {
            public DerivativeStructure getZero() {
                return new DerivativeStructure(DerivativeStructure.this.compiler.getFreeParameters(), DerivativeStructure.this.compiler.getOrder(), 0.0d);
            }

            public DerivativeStructure getOne() {
                return new DerivativeStructure(DerivativeStructure.this.compiler.getFreeParameters(), DerivativeStructure.this.compiler.getOrder(), 1.0d);
            }

            public Class<? extends FieldElement<DerivativeStructure>> getRuntimeClass() {
                return DerivativeStructure.class;
            }
        };
    }

    public static DerivativeStructure pow(double d, DerivativeStructure derivativeStructure) {
        DerivativeStructure derivativeStructure2 = new DerivativeStructure(derivativeStructure.compiler);
        derivativeStructure.compiler.pow(d, derivativeStructure.data, 0, derivativeStructure2.data, 0);
        return derivativeStructure2;
    }

    public DerivativeStructure pow(double d) {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        this.compiler.pow(this.data, 0, d, derivativeStructure.data, 0);
        return derivativeStructure;
    }

    public DerivativeStructure pow(int i) {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        this.compiler.pow(this.data, 0, i, derivativeStructure.data, 0);
        return derivativeStructure;
    }

    public DerivativeStructure pow(DerivativeStructure derivativeStructure) throws DimensionMismatchException {
        this.compiler.checkCompatibility(derivativeStructure.compiler);
        DerivativeStructure derivativeStructure2 = new DerivativeStructure(this.compiler);
        this.compiler.pow(this.data, 0, derivativeStructure.data, 0, derivativeStructure2.data, 0);
        return derivativeStructure2;
    }

    public DerivativeStructure exp() {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        this.compiler.exp(this.data, 0, derivativeStructure.data, 0);
        return derivativeStructure;
    }

    public DerivativeStructure expm1() {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        this.compiler.expm1(this.data, 0, derivativeStructure.data, 0);
        return derivativeStructure;
    }

    public DerivativeStructure log() {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        this.compiler.log(this.data, 0, derivativeStructure.data, 0);
        return derivativeStructure;
    }

    public DerivativeStructure log1p() {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        this.compiler.log1p(this.data, 0, derivativeStructure.data, 0);
        return derivativeStructure;
    }

    public DerivativeStructure log10() {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        this.compiler.log10(this.data, 0, derivativeStructure.data, 0);
        return derivativeStructure;
    }

    public DerivativeStructure cos() {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        this.compiler.cos(this.data, 0, derivativeStructure.data, 0);
        return derivativeStructure;
    }

    public DerivativeStructure sin() {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        this.compiler.sin(this.data, 0, derivativeStructure.data, 0);
        return derivativeStructure;
    }

    public DerivativeStructure tan() {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        this.compiler.tan(this.data, 0, derivativeStructure.data, 0);
        return derivativeStructure;
    }

    public DerivativeStructure acos() {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        this.compiler.acos(this.data, 0, derivativeStructure.data, 0);
        return derivativeStructure;
    }

    public DerivativeStructure asin() {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        this.compiler.asin(this.data, 0, derivativeStructure.data, 0);
        return derivativeStructure;
    }

    public DerivativeStructure atan() {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        this.compiler.atan(this.data, 0, derivativeStructure.data, 0);
        return derivativeStructure;
    }

    public DerivativeStructure atan2(DerivativeStructure derivativeStructure) throws DimensionMismatchException {
        this.compiler.checkCompatibility(derivativeStructure.compiler);
        DerivativeStructure derivativeStructure2 = new DerivativeStructure(this.compiler);
        this.compiler.atan2(this.data, 0, derivativeStructure.data, 0, derivativeStructure2.data, 0);
        return derivativeStructure2;
    }

    public static DerivativeStructure atan2(DerivativeStructure derivativeStructure, DerivativeStructure derivativeStructure2) throws DimensionMismatchException {
        return derivativeStructure.atan2(derivativeStructure2);
    }

    public DerivativeStructure cosh() {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        this.compiler.cosh(this.data, 0, derivativeStructure.data, 0);
        return derivativeStructure;
    }

    public DerivativeStructure sinh() {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        this.compiler.sinh(this.data, 0, derivativeStructure.data, 0);
        return derivativeStructure;
    }

    public DerivativeStructure tanh() {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        this.compiler.tanh(this.data, 0, derivativeStructure.data, 0);
        return derivativeStructure;
    }

    public DerivativeStructure acosh() {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        this.compiler.acosh(this.data, 0, derivativeStructure.data, 0);
        return derivativeStructure;
    }

    public DerivativeStructure asinh() {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        this.compiler.asinh(this.data, 0, derivativeStructure.data, 0);
        return derivativeStructure;
    }

    public DerivativeStructure atanh() {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        this.compiler.atanh(this.data, 0, derivativeStructure.data, 0);
        return derivativeStructure;
    }

    public DerivativeStructure toDegrees() {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        int i = 0;
        while (true) {
            double[] dArr = derivativeStructure.data;
            if (i >= dArr.length) {
                return derivativeStructure;
            }
            dArr[i] = FastMath.toDegrees(this.data[i]);
            i++;
        }
    }

    public DerivativeStructure toRadians() {
        DerivativeStructure derivativeStructure = new DerivativeStructure(this.compiler);
        int i = 0;
        while (true) {
            double[] dArr = derivativeStructure.data;
            if (i >= dArr.length) {
                return derivativeStructure;
            }
            dArr[i] = FastMath.toRadians(this.data[i]);
            i++;
        }
    }

    public double taylor(double... dArr) throws MathArithmeticException {
        return this.compiler.taylor(this.data, 0, dArr);
    }

    public DerivativeStructure linearCombination(DerivativeStructure[] derivativeStructureArr, DerivativeStructure[] derivativeStructureArr2) throws DimensionMismatchException {
        double[] dArr = new double[derivativeStructureArr.length];
        for (int i = 0; i < derivativeStructureArr.length; i++) {
            dArr[i] = derivativeStructureArr[i].getValue();
        }
        double[] dArr2 = new double[derivativeStructureArr2.length];
        for (int i2 = 0; i2 < derivativeStructureArr2.length; i2++) {
            dArr2[i2] = derivativeStructureArr2[i2].getValue();
        }
        double linearCombination = MathArrays.linearCombination(dArr, dArr2);
        DerivativeStructure zero = derivativeStructureArr[0].getField().getZero();
        for (int i3 = 0; i3 < derivativeStructureArr.length; i3++) {
            zero = zero.add(derivativeStructureArr[i3].multiply(derivativeStructureArr2[i3]));
        }
        double[] allDerivatives = zero.getAllDerivatives();
        allDerivatives[0] = linearCombination;
        return new DerivativeStructure(zero.getFreeParameters(), zero.getOrder(), allDerivatives);
    }

    public DerivativeStructure linearCombination(double[] dArr, DerivativeStructure[] derivativeStructureArr) throws DimensionMismatchException {
        double[] dArr2 = new double[derivativeStructureArr.length];
        for (int i = 0; i < derivativeStructureArr.length; i++) {
            dArr2[i] = derivativeStructureArr[i].getValue();
        }
        double linearCombination = MathArrays.linearCombination(dArr, dArr2);
        DerivativeStructure zero = derivativeStructureArr[0].getField().getZero();
        for (int i2 = 0; i2 < dArr.length; i2++) {
            zero = zero.add(derivativeStructureArr[i2].multiply(dArr[i2]));
        }
        double[] allDerivatives = zero.getAllDerivatives();
        allDerivatives[0] = linearCombination;
        return new DerivativeStructure(zero.getFreeParameters(), zero.getOrder(), allDerivatives);
    }

    public DerivativeStructure linearCombination(DerivativeStructure derivativeStructure, DerivativeStructure derivativeStructure2, DerivativeStructure derivativeStructure3, DerivativeStructure derivativeStructure4) throws DimensionMismatchException {
        double linearCombination = MathArrays.linearCombination(derivativeStructure.getValue(), derivativeStructure2.getValue(), derivativeStructure3.getValue(), derivativeStructure4.getValue());
        double[] allDerivatives = derivativeStructure.multiply(derivativeStructure2).add(derivativeStructure3.multiply(derivativeStructure4)).getAllDerivatives();
        allDerivatives[0] = linearCombination;
        return new DerivativeStructure(getFreeParameters(), getOrder(), allDerivatives);
    }

    public DerivativeStructure linearCombination(double d, DerivativeStructure derivativeStructure, double d2, DerivativeStructure derivativeStructure2) throws DimensionMismatchException {
        double linearCombination = MathArrays.linearCombination(d, derivativeStructure.getValue(), d2, derivativeStructure2.getValue());
        double[] allDerivatives = derivativeStructure.multiply(d).add(derivativeStructure2.multiply(d2)).getAllDerivatives();
        allDerivatives[0] = linearCombination;
        return new DerivativeStructure(getFreeParameters(), getOrder(), allDerivatives);
    }

    public DerivativeStructure linearCombination(DerivativeStructure derivativeStructure, DerivativeStructure derivativeStructure2, DerivativeStructure derivativeStructure3, DerivativeStructure derivativeStructure4, DerivativeStructure derivativeStructure5, DerivativeStructure derivativeStructure6) throws DimensionMismatchException {
        double linearCombination = MathArrays.linearCombination(derivativeStructure.getValue(), derivativeStructure2.getValue(), derivativeStructure3.getValue(), derivativeStructure4.getValue(), derivativeStructure5.getValue(), derivativeStructure6.getValue());
        double[] allDerivatives = derivativeStructure.multiply(derivativeStructure2).add(derivativeStructure3.multiply(derivativeStructure4)).add(derivativeStructure5.multiply(derivativeStructure6)).getAllDerivatives();
        allDerivatives[0] = linearCombination;
        return new DerivativeStructure(getFreeParameters(), getOrder(), allDerivatives);
    }

    public DerivativeStructure linearCombination(double d, DerivativeStructure derivativeStructure, double d2, DerivativeStructure derivativeStructure2, double d3, DerivativeStructure derivativeStructure3) throws DimensionMismatchException {
        double linearCombination = MathArrays.linearCombination(d, derivativeStructure.getValue(), d2, derivativeStructure2.getValue(), d3, derivativeStructure3.getValue());
        double d4 = d;
        DerivativeStructure derivativeStructure4 = derivativeStructure;
        double[] allDerivatives = derivativeStructure.multiply(d).add(derivativeStructure2.multiply(d2)).add(derivativeStructure3.multiply(d3)).getAllDerivatives();
        allDerivatives[0] = linearCombination;
        return new DerivativeStructure(getFreeParameters(), getOrder(), allDerivatives);
    }

    public DerivativeStructure linearCombination(DerivativeStructure derivativeStructure, DerivativeStructure derivativeStructure2, DerivativeStructure derivativeStructure3, DerivativeStructure derivativeStructure4, DerivativeStructure derivativeStructure5, DerivativeStructure derivativeStructure6, DerivativeStructure derivativeStructure7, DerivativeStructure derivativeStructure8) throws DimensionMismatchException {
        double linearCombination = MathArrays.linearCombination(derivativeStructure.getValue(), derivativeStructure2.getValue(), derivativeStructure3.getValue(), derivativeStructure4.getValue(), derivativeStructure5.getValue(), derivativeStructure6.getValue(), derivativeStructure7.getValue(), derivativeStructure8.getValue());
        double[] allDerivatives = derivativeStructure.multiply(derivativeStructure2).add(derivativeStructure3.multiply(derivativeStructure4)).add(derivativeStructure5.multiply(derivativeStructure6)).add(derivativeStructure7.multiply(derivativeStructure8)).getAllDerivatives();
        allDerivatives[0] = linearCombination;
        return new DerivativeStructure(getFreeParameters(), getOrder(), allDerivatives);
    }

    public DerivativeStructure linearCombination(double d, DerivativeStructure derivativeStructure, double d2, DerivativeStructure derivativeStructure2, double d3, DerivativeStructure derivativeStructure3, double d4, DerivativeStructure derivativeStructure4) throws DimensionMismatchException {
        double linearCombination = MathArrays.linearCombination(d, derivativeStructure.getValue(), d2, derivativeStructure2.getValue(), d3, derivativeStructure3.getValue(), d4, derivativeStructure4.getValue());
        double[] allDerivatives = derivativeStructure.multiply(d).add(derivativeStructure2.multiply(d2)).add(derivativeStructure3.multiply(d3)).add(derivativeStructure4.multiply(d4)).getAllDerivatives();
        allDerivatives[0] = linearCombination;
        return new DerivativeStructure(getFreeParameters(), getOrder(), allDerivatives);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DerivativeStructure)) {
            return false;
        }
        DerivativeStructure derivativeStructure = (DerivativeStructure) obj;
        if (getFreeParameters() == derivativeStructure.getFreeParameters() && getOrder() == derivativeStructure.getOrder() && MathArrays.equals(this.data, derivativeStructure.data)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (getFreeParameters() * 229) + 227 + (getOrder() * 233) + (MathUtils.hash(this.data) * 239);
    }

    private Object writeReplace() {
        return new DataTransferObject(this.compiler.getFreeParameters(), this.compiler.getOrder(), this.data);
    }

    private static class DataTransferObject implements Serializable {
        private static final long serialVersionUID = 20120730;
        private final double[] data;
        private final int order;
        private final int variables;

        public DataTransferObject(int i, int i2, double[] dArr) {
            this.variables = i;
            this.order = i2;
            this.data = dArr;
        }

        private Object readResolve() {
            return new DerivativeStructure(this.variables, this.order, this.data);
        }
    }
}
