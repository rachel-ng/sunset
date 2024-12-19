package org.apache.commons.math3.analysis.differentiation;

import com.alibaba.fastjson.asm.Opcodes;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

public class SparseGradient implements RealFieldElement<SparseGradient>, Serializable {
    private static final long serialVersionUID = 20131025;
    private final Map<Integer, Double> derivatives;
    private double value;

    private SparseGradient(double d, Map<Integer, Double> map) {
        this.value = d;
        HashMap hashMap = new HashMap();
        this.derivatives = hashMap;
        if (map != null) {
            hashMap.putAll(map);
        }
    }

    private SparseGradient(double d, double d2, Map<Integer, Double> map) {
        this.value = d;
        this.derivatives = new HashMap();
        if (map != null) {
            for (Map.Entry next : map.entrySet()) {
                this.derivatives.put(next.getKey(), Double.valueOf(((Double) next.getValue()).doubleValue() * d2));
            }
        }
    }

    public static SparseGradient createConstant(double d) {
        return new SparseGradient(d, Collections.emptyMap());
    }

    public static SparseGradient createVariable(int i, double d) {
        return new SparseGradient(d, Collections.singletonMap(Integer.valueOf(i), Double.valueOf(1.0d)));
    }

    public int numVars() {
        return this.derivatives.size();
    }

    public double getDerivative(int i) {
        Double d = this.derivatives.get(Integer.valueOf(i));
        if (d == null) {
            return 0.0d;
        }
        return d.doubleValue();
    }

    public double getValue() {
        return this.value;
    }

    public double getReal() {
        return this.value;
    }

    public SparseGradient add(SparseGradient sparseGradient) {
        SparseGradient sparseGradient2 = new SparseGradient(this.value + sparseGradient.value, this.derivatives);
        for (Map.Entry next : sparseGradient.derivatives.entrySet()) {
            Integer num = (Integer) next.getKey();
            num.intValue();
            Double d = sparseGradient2.derivatives.get(num);
            if (d == null) {
                sparseGradient2.derivatives.put(num, next.getValue());
            } else {
                sparseGradient2.derivatives.put(num, Double.valueOf(d.doubleValue() + ((Double) next.getValue()).doubleValue()));
            }
        }
        return sparseGradient2;
    }

    public void addInPlace(SparseGradient sparseGradient) {
        this.value += sparseGradient.value;
        for (Map.Entry next : sparseGradient.derivatives.entrySet()) {
            Integer num = (Integer) next.getKey();
            num.intValue();
            Double d = this.derivatives.get(num);
            if (d == null) {
                this.derivatives.put(num, next.getValue());
            } else {
                this.derivatives.put(num, Double.valueOf(d.doubleValue() + ((Double) next.getValue()).doubleValue()));
            }
        }
    }

    public SparseGradient add(double d) {
        return new SparseGradient(this.value + d, this.derivatives);
    }

    public SparseGradient subtract(SparseGradient sparseGradient) {
        SparseGradient sparseGradient2 = new SparseGradient(this.value - sparseGradient.value, this.derivatives);
        for (Map.Entry next : sparseGradient.derivatives.entrySet()) {
            Integer num = (Integer) next.getKey();
            num.intValue();
            Double d = sparseGradient2.derivatives.get(num);
            if (d == null) {
                sparseGradient2.derivatives.put(num, Double.valueOf(-((Double) next.getValue()).doubleValue()));
            } else {
                sparseGradient2.derivatives.put(num, Double.valueOf(d.doubleValue() - ((Double) next.getValue()).doubleValue()));
            }
        }
        return sparseGradient2;
    }

    public SparseGradient subtract(double d) {
        return new SparseGradient(this.value - d, this.derivatives);
    }

    public SparseGradient multiply(SparseGradient sparseGradient) {
        SparseGradient sparseGradient2 = new SparseGradient(this.value * sparseGradient.value, Collections.emptyMap());
        for (Map.Entry next : this.derivatives.entrySet()) {
            sparseGradient2.derivatives.put(next.getKey(), Double.valueOf(sparseGradient.value * ((Double) next.getValue()).doubleValue()));
        }
        for (Map.Entry next2 : sparseGradient.derivatives.entrySet()) {
            Integer num = (Integer) next2.getKey();
            num.intValue();
            Double d = sparseGradient2.derivatives.get(num);
            if (d == null) {
                sparseGradient2.derivatives.put(num, Double.valueOf(this.value * ((Double) next2.getValue()).doubleValue()));
            } else {
                sparseGradient2.derivatives.put(num, Double.valueOf(d.doubleValue() + (this.value * ((Double) next2.getValue()).doubleValue())));
            }
        }
        return sparseGradient2;
    }

    public void multiplyInPlace(SparseGradient sparseGradient) {
        for (Map.Entry next : this.derivatives.entrySet()) {
            this.derivatives.put(next.getKey(), Double.valueOf(sparseGradient.value * ((Double) next.getValue()).doubleValue()));
        }
        for (Map.Entry next2 : sparseGradient.derivatives.entrySet()) {
            Integer num = (Integer) next2.getKey();
            num.intValue();
            Double d = this.derivatives.get(num);
            if (d == null) {
                this.derivatives.put(num, Double.valueOf(this.value * ((Double) next2.getValue()).doubleValue()));
            } else {
                this.derivatives.put(num, Double.valueOf(d.doubleValue() + (this.value * ((Double) next2.getValue()).doubleValue())));
            }
        }
        this.value *= sparseGradient.value;
    }

    public SparseGradient multiply(double d) {
        return new SparseGradient(this.value * d, d, this.derivatives);
    }

    public SparseGradient multiply(int i) {
        double d = (double) i;
        return new SparseGradient(this.value * d, d, this.derivatives);
    }

    public SparseGradient divide(SparseGradient sparseGradient) {
        SparseGradient sparseGradient2 = new SparseGradient(this.value / sparseGradient.value, Collections.emptyMap());
        for (Map.Entry next : this.derivatives.entrySet()) {
            sparseGradient2.derivatives.put(next.getKey(), Double.valueOf(((Double) next.getValue()).doubleValue() / sparseGradient.value));
        }
        for (Map.Entry next2 : sparseGradient.derivatives.entrySet()) {
            Integer num = (Integer) next2.getKey();
            num.intValue();
            Double d = sparseGradient2.derivatives.get(num);
            if (d == null) {
                sparseGradient2.derivatives.put(num, Double.valueOf(((-sparseGradient2.value) / sparseGradient.value) * ((Double) next2.getValue()).doubleValue()));
            } else {
                sparseGradient2.derivatives.put(num, Double.valueOf(d.doubleValue() - ((sparseGradient2.value / sparseGradient.value) * ((Double) next2.getValue()).doubleValue())));
            }
        }
        return sparseGradient2;
    }

    public SparseGradient divide(double d) {
        return new SparseGradient(this.value / d, 1.0d / d, this.derivatives);
    }

    public SparseGradient negate() {
        return new SparseGradient(-this.value, -1.0d, this.derivatives);
    }

    public Field<SparseGradient> getField() {
        return new Field<SparseGradient>() {
            public SparseGradient getZero() {
                return SparseGradient.createConstant(0.0d);
            }

            public SparseGradient getOne() {
                return SparseGradient.createConstant(1.0d);
            }

            public Class<? extends FieldElement<SparseGradient>> getRuntimeClass() {
                return SparseGradient.class;
            }
        };
    }

    public SparseGradient remainder(double d) {
        return new SparseGradient(FastMath.IEEEremainder(this.value, d), this.derivatives);
    }

    public SparseGradient remainder(SparseGradient sparseGradient) {
        return subtract(sparseGradient.multiply(FastMath.rint((this.value - FastMath.IEEEremainder(this.value, sparseGradient.value)) / sparseGradient.value)));
    }

    public SparseGradient abs() {
        return Double.doubleToLongBits(this.value) < 0 ? negate() : this;
    }

    public SparseGradient ceil() {
        return createConstant(FastMath.ceil(this.value));
    }

    public SparseGradient floor() {
        return createConstant(FastMath.floor(this.value));
    }

    public SparseGradient rint() {
        return createConstant(FastMath.rint(this.value));
    }

    public long round() {
        return FastMath.round(this.value);
    }

    public SparseGradient signum() {
        return createConstant(FastMath.signum(this.value));
    }

    public SparseGradient copySign(SparseGradient sparseGradient) {
        long doubleToLongBits = Double.doubleToLongBits(this.value);
        long doubleToLongBits2 = Double.doubleToLongBits(sparseGradient.value);
        int i = (doubleToLongBits > 0 ? 1 : (doubleToLongBits == 0 ? 0 : -1));
        if ((i < 0 || doubleToLongBits2 < 0) && (i >= 0 || doubleToLongBits2 >= 0)) {
            return negate();
        }
        return this;
    }

    public SparseGradient copySign(double d) {
        long doubleToLongBits = Double.doubleToLongBits(this.value);
        long doubleToLongBits2 = Double.doubleToLongBits(d);
        int i = (doubleToLongBits > 0 ? 1 : (doubleToLongBits == 0 ? 0 : -1));
        if ((i < 0 || doubleToLongBits2 < 0) && (i >= 0 || doubleToLongBits2 >= 0)) {
            return negate();
        }
        return this;
    }

    public SparseGradient scalb(int i) {
        SparseGradient sparseGradient = new SparseGradient(FastMath.scalb(this.value, i), Collections.emptyMap());
        for (Map.Entry next : this.derivatives.entrySet()) {
            sparseGradient.derivatives.put(next.getKey(), Double.valueOf(FastMath.scalb(((Double) next.getValue()).doubleValue(), i)));
        }
        return sparseGradient;
    }

    public SparseGradient hypot(SparseGradient sparseGradient) {
        if (Double.isInfinite(this.value) || Double.isInfinite(sparseGradient.value)) {
            return createConstant(Double.POSITIVE_INFINITY);
        }
        if (Double.isNaN(this.value) || Double.isNaN(sparseGradient.value)) {
            return createConstant(Double.NaN);
        }
        int exponent = FastMath.getExponent(this.value);
        int exponent2 = FastMath.getExponent(sparseGradient.value);
        if (exponent > exponent2 + 27) {
            return abs();
        }
        if (exponent2 > exponent + 27) {
            return sparseGradient.abs();
        }
        int i = (exponent + exponent2) / 2;
        int i2 = -i;
        SparseGradient scalb = scalb(i2);
        SparseGradient scalb2 = sparseGradient.scalb(i2);
        return scalb.multiply(scalb).add(scalb2.multiply(scalb2)).sqrt().scalb(i);
    }

    public static SparseGradient hypot(SparseGradient sparseGradient, SparseGradient sparseGradient2) {
        return sparseGradient.hypot(sparseGradient2);
    }

    public SparseGradient reciprocal() {
        double d = this.value;
        return new SparseGradient(1.0d / d, -1.0d / (d * d), this.derivatives);
    }

    public SparseGradient sqrt() {
        double sqrt = FastMath.sqrt(this.value);
        return new SparseGradient(sqrt, 0.5d / sqrt, this.derivatives);
    }

    public SparseGradient cbrt() {
        double cbrt = FastMath.cbrt(this.value);
        return new SparseGradient(cbrt, 1.0d / ((3.0d * cbrt) * cbrt), this.derivatives);
    }

    public SparseGradient rootN(int i) {
        if (i == 2) {
            return sqrt();
        }
        if (i == 3) {
            return cbrt();
        }
        double d = (double) i;
        double pow = FastMath.pow(this.value, 1.0d / d);
        return new SparseGradient(pow, 1.0d / (d * FastMath.pow(pow, i - 1)), this.derivatives);
    }

    public SparseGradient pow(double d) {
        return new SparseGradient(FastMath.pow(this.value, d), FastMath.pow(this.value, d - 1.0d) * d, this.derivatives);
    }

    public SparseGradient pow(int i) {
        if (i == 0) {
            return getField().getOne();
        }
        double pow = FastMath.pow(this.value, i - 1);
        return new SparseGradient(this.value * pow, ((double) i) * pow, this.derivatives);
    }

    public SparseGradient pow(SparseGradient sparseGradient) {
        return log().multiply(sparseGradient).exp();
    }

    public static SparseGradient pow(double d, SparseGradient sparseGradient) {
        if (d == 0.0d) {
            double d2 = sparseGradient.value;
            if (d2 == 0.0d) {
                return sparseGradient.compose(1.0d, Double.NEGATIVE_INFINITY);
            }
            if (d2 < 0.0d) {
                return sparseGradient.compose(Double.NaN, Double.NaN);
            }
            return sparseGradient.getField().getZero();
        }
        double pow = FastMath.pow(d, sparseGradient.value);
        return new SparseGradient(pow, pow * FastMath.log(d), sparseGradient.derivatives);
    }

    public SparseGradient exp() {
        double exp = FastMath.exp(this.value);
        return new SparseGradient(exp, exp, this.derivatives);
    }

    public SparseGradient expm1() {
        return new SparseGradient(FastMath.expm1(this.value), FastMath.exp(this.value), this.derivatives);
    }

    public SparseGradient log() {
        return new SparseGradient(FastMath.log(this.value), 1.0d / this.value, this.derivatives);
    }

    public SparseGradient log10() {
        return new SparseGradient(FastMath.log10(this.value), 1.0d / (FastMath.log(10.0d) * this.value), this.derivatives);
    }

    public SparseGradient log1p() {
        return new SparseGradient(FastMath.log1p(this.value), 1.0d / (this.value + 1.0d), this.derivatives);
    }

    public SparseGradient cos() {
        return new SparseGradient(FastMath.cos(this.value), -FastMath.sin(this.value), this.derivatives);
    }

    public SparseGradient sin() {
        return new SparseGradient(FastMath.sin(this.value), FastMath.cos(this.value), this.derivatives);
    }

    public SparseGradient tan() {
        double tan = FastMath.tan(this.value);
        return new SparseGradient(tan, (tan * tan) + 1.0d, this.derivatives);
    }

    public SparseGradient acos() {
        double acos = FastMath.acos(this.value);
        double d = this.value;
        return new SparseGradient(acos, -1.0d / FastMath.sqrt(1.0d - (d * d)), this.derivatives);
    }

    public SparseGradient asin() {
        double asin = FastMath.asin(this.value);
        double d = this.value;
        return new SparseGradient(asin, 1.0d / FastMath.sqrt(1.0d - (d * d)), this.derivatives);
    }

    public SparseGradient atan() {
        double atan = FastMath.atan(this.value);
        double d = this.value;
        return new SparseGradient(atan, 1.0d / ((d * d) + 1.0d), this.derivatives);
    }

    public SparseGradient atan2(SparseGradient sparseGradient) {
        SparseGradient sparseGradient2;
        SparseGradient sqrt = multiply(this).add(sparseGradient.multiply(sparseGradient)).sqrt();
        if (sparseGradient.value >= 0.0d) {
            sparseGradient2 = divide(sqrt.add(sparseGradient)).atan().multiply(2);
        } else {
            SparseGradient multiply = divide(sqrt.subtract(sparseGradient)).atan().multiply(-2);
            sparseGradient2 = multiply.add(multiply.value <= 0.0d ? -3.141592653589793d : 3.141592653589793d);
        }
        sparseGradient2.value = FastMath.atan2(this.value, sparseGradient.value);
        return sparseGradient2;
    }

    public static SparseGradient atan2(SparseGradient sparseGradient, SparseGradient sparseGradient2) {
        return sparseGradient.atan2(sparseGradient2);
    }

    public SparseGradient cosh() {
        return new SparseGradient(FastMath.cosh(this.value), FastMath.sinh(this.value), this.derivatives);
    }

    public SparseGradient sinh() {
        return new SparseGradient(FastMath.sinh(this.value), FastMath.cosh(this.value), this.derivatives);
    }

    public SparseGradient tanh() {
        double tanh = FastMath.tanh(this.value);
        return new SparseGradient(tanh, 1.0d - (tanh * tanh), this.derivatives);
    }

    public SparseGradient acosh() {
        double acosh = FastMath.acosh(this.value);
        double d = this.value;
        return new SparseGradient(acosh, 1.0d / FastMath.sqrt((d * d) - 1.0d), this.derivatives);
    }

    public SparseGradient asinh() {
        double asinh = FastMath.asinh(this.value);
        double d = this.value;
        return new SparseGradient(asinh, 1.0d / FastMath.sqrt((d * d) + 1.0d), this.derivatives);
    }

    public SparseGradient atanh() {
        double atanh = FastMath.atanh(this.value);
        double d = this.value;
        return new SparseGradient(atanh, 1.0d / (1.0d - (d * d)), this.derivatives);
    }

    public SparseGradient toDegrees() {
        return new SparseGradient(FastMath.toDegrees(this.value), FastMath.toDegrees(1.0d), this.derivatives);
    }

    public SparseGradient toRadians() {
        return new SparseGradient(FastMath.toRadians(this.value), FastMath.toRadians(1.0d), this.derivatives);
    }

    public double taylor(double... dArr) {
        double d = this.value;
        for (int i = 0; i < dArr.length; i++) {
            d += dArr[i] * getDerivative(i);
        }
        return d;
    }

    public SparseGradient compose(double d, double d2) {
        return new SparseGradient(d, d2, this.derivatives);
    }

    public SparseGradient linearCombination(SparseGradient[] sparseGradientArr, SparseGradient[] sparseGradientArr2) throws DimensionMismatchException {
        SparseGradient zero = sparseGradientArr[0].getField().getZero();
        for (int i = 0; i < sparseGradientArr.length; i++) {
            zero = zero.add(sparseGradientArr[i].multiply(sparseGradientArr2[i]));
        }
        double[] dArr = new double[sparseGradientArr.length];
        for (int i2 = 0; i2 < sparseGradientArr.length; i2++) {
            dArr[i2] = sparseGradientArr[i2].getValue();
        }
        double[] dArr2 = new double[sparseGradientArr2.length];
        for (int i3 = 0; i3 < sparseGradientArr2.length; i3++) {
            dArr2[i3] = sparseGradientArr2[i3].getValue();
        }
        zero.value = MathArrays.linearCombination(dArr, dArr2);
        return zero;
    }

    public SparseGradient linearCombination(double[] dArr, SparseGradient[] sparseGradientArr) {
        SparseGradient zero = sparseGradientArr[0].getField().getZero();
        for (int i = 0; i < dArr.length; i++) {
            zero = zero.add(sparseGradientArr[i].multiply(dArr[i]));
        }
        double[] dArr2 = new double[sparseGradientArr.length];
        for (int i2 = 0; i2 < sparseGradientArr.length; i2++) {
            dArr2[i2] = sparseGradientArr[i2].getValue();
        }
        zero.value = MathArrays.linearCombination(dArr, dArr2);
        return zero;
    }

    public SparseGradient linearCombination(SparseGradient sparseGradient, SparseGradient sparseGradient2, SparseGradient sparseGradient3, SparseGradient sparseGradient4) {
        SparseGradient add = sparseGradient.multiply(sparseGradient2).add(sparseGradient3.multiply(sparseGradient4));
        add.value = MathArrays.linearCombination(sparseGradient.value, sparseGradient2.value, sparseGradient3.value, sparseGradient4.value);
        return add;
    }

    public SparseGradient linearCombination(double d, SparseGradient sparseGradient, double d2, SparseGradient sparseGradient2) {
        SparseGradient add = sparseGradient.multiply(d).add(sparseGradient2.multiply(d2));
        add.value = MathArrays.linearCombination(d, sparseGradient.value, d2, sparseGradient2.value);
        return add;
    }

    public SparseGradient linearCombination(SparseGradient sparseGradient, SparseGradient sparseGradient2, SparseGradient sparseGradient3, SparseGradient sparseGradient4, SparseGradient sparseGradient5, SparseGradient sparseGradient6) {
        SparseGradient add = sparseGradient.multiply(sparseGradient2).add(sparseGradient3.multiply(sparseGradient4)).add(sparseGradient5.multiply(sparseGradient6));
        add.value = MathArrays.linearCombination(sparseGradient.value, sparseGradient2.value, sparseGradient3.value, sparseGradient4.value, sparseGradient5.value, sparseGradient6.value);
        return add;
    }

    public SparseGradient linearCombination(double d, SparseGradient sparseGradient, double d2, SparseGradient sparseGradient2, double d3, SparseGradient sparseGradient3) {
        SparseGradient sparseGradient4 = sparseGradient;
        SparseGradient sparseGradient5 = sparseGradient2;
        SparseGradient sparseGradient6 = sparseGradient3;
        double d4 = d3;
        SparseGradient add = sparseGradient4.multiply(d).add(sparseGradient5.multiply(d2)).add(sparseGradient6.multiply(d4));
        double d5 = sparseGradient4.value;
        double d6 = d5;
        add.value = MathArrays.linearCombination(d, d6, d2, sparseGradient5.value, d4, sparseGradient6.value);
        return add;
    }

    public SparseGradient linearCombination(SparseGradient sparseGradient, SparseGradient sparseGradient2, SparseGradient sparseGradient3, SparseGradient sparseGradient4, SparseGradient sparseGradient5, SparseGradient sparseGradient6, SparseGradient sparseGradient7, SparseGradient sparseGradient8) {
        SparseGradient add = sparseGradient.multiply(sparseGradient2).add(sparseGradient3.multiply(sparseGradient4)).add(sparseGradient5.multiply(sparseGradient6)).add(sparseGradient7.multiply(sparseGradient8));
        add.value = MathArrays.linearCombination(sparseGradient.value, sparseGradient2.value, sparseGradient3.value, sparseGradient4.value, sparseGradient5.value, sparseGradient6.value, sparseGradient7.value, sparseGradient8.value);
        return add;
    }

    public SparseGradient linearCombination(double d, SparseGradient sparseGradient, double d2, SparseGradient sparseGradient2, double d3, SparseGradient sparseGradient3, double d4, SparseGradient sparseGradient4) {
        SparseGradient sparseGradient5 = sparseGradient;
        SparseGradient sparseGradient6 = sparseGradient2;
        SparseGradient sparseGradient7 = sparseGradient3;
        SparseGradient sparseGradient8 = sparseGradient4;
        double d5 = d4;
        SparseGradient add = sparseGradient5.multiply(d).add(sparseGradient6.multiply(d2)).add(sparseGradient7.multiply(d3)).add(sparseGradient8.multiply(d5));
        double d6 = sparseGradient5.value;
        double d7 = sparseGradient6.value;
        double d8 = d7;
        SparseGradient sparseGradient9 = add;
        sparseGradient9.value = MathArrays.linearCombination(d, d6, d2, d8, d3, sparseGradient7.value, d5, sparseGradient8.value);
        return sparseGradient9;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0035  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r9) {
        /*
            r8 = this;
            r0 = 1
            if (r8 != r9) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r9 instanceof org.apache.commons.math3.analysis.differentiation.SparseGradient
            r2 = 0
            if (r1 == 0) goto L_0x006a
            org.apache.commons.math3.analysis.differentiation.SparseGradient r9 = (org.apache.commons.math3.analysis.differentiation.SparseGradient) r9
            double r3 = r8.value
            double r5 = r9.value
            boolean r1 = org.apache.commons.math3.util.Precision.equals((double) r3, (double) r5, (int) r0)
            if (r1 != 0) goto L_0x0016
            return r2
        L_0x0016:
            java.util.Map<java.lang.Integer, java.lang.Double> r1 = r8.derivatives
            int r1 = r1.size()
            java.util.Map<java.lang.Integer, java.lang.Double> r3 = r9.derivatives
            int r3 = r3.size()
            if (r1 == r3) goto L_0x0025
            return r2
        L_0x0025:
            java.util.Map<java.lang.Integer, java.lang.Double> r1 = r8.derivatives
            java.util.Set r1 = r1.entrySet()
            java.util.Iterator r1 = r1.iterator()
        L_0x002f:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x0069
            java.lang.Object r3 = r1.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            java.util.Map<java.lang.Integer, java.lang.Double> r4 = r9.derivatives
            java.lang.Object r5 = r3.getKey()
            boolean r4 = r4.containsKey(r5)
            if (r4 != 0) goto L_0x0048
            return r2
        L_0x0048:
            java.lang.Object r4 = r3.getValue()
            java.lang.Double r4 = (java.lang.Double) r4
            double r4 = r4.doubleValue()
            java.util.Map<java.lang.Integer, java.lang.Double> r6 = r9.derivatives
            java.lang.Object r3 = r3.getKey()
            java.lang.Object r3 = r6.get(r3)
            java.lang.Double r3 = (java.lang.Double) r3
            double r6 = r3.doubleValue()
            boolean r3 = org.apache.commons.math3.util.Precision.equals((double) r4, (double) r6, (int) r0)
            if (r3 != 0) goto L_0x002f
            return r2
        L_0x0069:
            return r0
        L_0x006a:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.analysis.differentiation.SparseGradient.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        return (MathUtils.hash(this.value) * 809) + 743 + (this.derivatives.hashCode() * Opcodes.GOTO);
    }
}
