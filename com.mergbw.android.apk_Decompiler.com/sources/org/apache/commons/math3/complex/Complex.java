package org.apache.commons.math3.complex;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;

public class Complex implements FieldElement<Complex>, Serializable {
    public static final Complex I = new Complex(0.0d, 1.0d);
    public static final Complex INF = new Complex(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
    public static final Complex NaN = new Complex(Double.NaN, Double.NaN);
    public static final Complex ONE = new Complex(1.0d, 0.0d);
    public static final Complex ZERO = new Complex(0.0d, 0.0d);
    private static final long serialVersionUID = -6195664516687396620L;
    private final double imaginary;
    private final transient boolean isInfinite;
    private final transient boolean isNaN;
    private final double real;

    public Complex(double d) {
        this(d, 0.0d);
    }

    public Complex(double d, double d2) {
        this.real = d;
        this.imaginary = d2;
        boolean z = false;
        boolean z2 = Double.isNaN(d) || Double.isNaN(d2);
        this.isNaN = z2;
        if (!z2 && (Double.isInfinite(d) || Double.isInfinite(d2))) {
            z = true;
        }
        this.isInfinite = z;
    }

    public double abs() {
        double abs;
        double sqrt;
        if (this.isNaN) {
            return Double.NaN;
        }
        if (isInfinite()) {
            return Double.POSITIVE_INFINITY;
        }
        if (FastMath.abs(this.real) < FastMath.abs(this.imaginary)) {
            double d = this.imaginary;
            if (d == 0.0d) {
                return FastMath.abs(this.real);
            }
            double d2 = this.real / d;
            abs = FastMath.abs(d);
            sqrt = FastMath.sqrt((d2 * d2) + 1.0d);
        } else {
            double d3 = this.real;
            if (d3 == 0.0d) {
                return FastMath.abs(this.imaginary);
            }
            double d4 = this.imaginary / d3;
            abs = FastMath.abs(d3);
            sqrt = FastMath.sqrt((d4 * d4) + 1.0d);
        }
        return abs * sqrt;
    }

    public Complex add(Complex complex) throws NullArgumentException {
        MathUtils.checkNotNull(complex);
        if (this.isNaN || complex.isNaN) {
            return NaN;
        }
        return createComplex(this.real + complex.getReal(), this.imaginary + complex.getImaginary());
    }

    public Complex add(double d) {
        if (this.isNaN || Double.isNaN(d)) {
            return NaN;
        }
        return createComplex(this.real + d, this.imaginary);
    }

    public Complex conjugate() {
        if (this.isNaN) {
            return NaN;
        }
        return createComplex(this.real, -this.imaginary);
    }

    public Complex divide(Complex complex) throws NullArgumentException {
        MathUtils.checkNotNull(complex);
        if (this.isNaN || complex.isNaN) {
            return NaN;
        }
        double real2 = complex.getReal();
        double imaginary2 = complex.getImaginary();
        if (real2 == 0.0d && imaginary2 == 0.0d) {
            return NaN;
        }
        if (complex.isInfinite() && !isInfinite()) {
            return ZERO;
        }
        if (FastMath.abs(real2) < FastMath.abs(imaginary2)) {
            double d = real2 / imaginary2;
            double d2 = (real2 * d) + imaginary2;
            double d3 = this.real;
            double d4 = this.imaginary;
            return createComplex(((d3 * d) + d4) / d2, ((d4 * d) - d3) / d2);
        }
        double d5 = imaginary2 / real2;
        double d6 = (imaginary2 * d5) + real2;
        double d7 = this.imaginary;
        double d8 = this.real;
        return createComplex(((d7 * d5) + d8) / d6, (d7 - (d8 * d5)) / d6);
    }

    public Complex divide(double d) {
        if (this.isNaN || Double.isNaN(d)) {
            return NaN;
        }
        if (d == 0.0d) {
            return NaN;
        }
        if (Double.isInfinite(d)) {
            return !isInfinite() ? ZERO : NaN;
        }
        return createComplex(this.real / d, this.imaginary / d);
    }

    public Complex reciprocal() {
        if (this.isNaN) {
            return NaN;
        }
        double d = this.real;
        if (d == 0.0d && this.imaginary == 0.0d) {
            return INF;
        }
        if (this.isInfinite) {
            return ZERO;
        }
        if (FastMath.abs(d) < FastMath.abs(this.imaginary)) {
            double d2 = this.real;
            double d3 = this.imaginary;
            double d4 = d2 / d3;
            double d5 = 1.0d / ((d2 * d4) + d3);
            return createComplex(d4 * d5, -d5);
        }
        double d6 = this.imaginary;
        double d7 = this.real;
        double d8 = d6 / d7;
        double d9 = 1.0d / ((d6 * d8) + d7);
        return createComplex(d9, (-d9) * d8);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Complex)) {
            return false;
        }
        Complex complex = (Complex) obj;
        if (complex.isNaN) {
            return this.isNaN;
        }
        if (!MathUtils.equals(this.real, complex.real) || !MathUtils.equals(this.imaginary, complex.imaginary)) {
            return false;
        }
        return true;
    }

    public static boolean equals(Complex complex, Complex complex2, int i) {
        return Precision.equals(complex.real, complex2.real, i) && Precision.equals(complex.imaginary, complex2.imaginary, i);
    }

    public static boolean equals(Complex complex, Complex complex2) {
        return equals(complex, complex2, 1);
    }

    public static boolean equals(Complex complex, Complex complex2, double d) {
        return Precision.equals(complex.real, complex2.real, d) && Precision.equals(complex.imaginary, complex2.imaginary, d);
    }

    public static boolean equalsWithRelativeTolerance(Complex complex, Complex complex2, double d) {
        return Precision.equalsWithRelativeTolerance(complex.real, complex2.real, d) && Precision.equalsWithRelativeTolerance(complex.imaginary, complex2.imaginary, d);
    }

    public int hashCode() {
        if (this.isNaN) {
            return 7;
        }
        return ((MathUtils.hash(this.imaginary) * 17) + MathUtils.hash(this.real)) * 37;
    }

    public double getImaginary() {
        return this.imaginary;
    }

    public double getReal() {
        return this.real;
    }

    public boolean isNaN() {
        return this.isNaN;
    }

    public boolean isInfinite() {
        return this.isInfinite;
    }

    public Complex multiply(Complex complex) throws NullArgumentException {
        MathUtils.checkNotNull(complex);
        if (this.isNaN || complex.isNaN) {
            return NaN;
        }
        if (Double.isInfinite(this.real) || Double.isInfinite(this.imaginary) || Double.isInfinite(complex.real) || Double.isInfinite(complex.imaginary)) {
            return INF;
        }
        double d = this.real;
        double d2 = complex.real;
        double d3 = this.imaginary;
        double d4 = complex.imaginary;
        return createComplex((d * d2) - (d3 * d4), (d * d4) + (d3 * d2));
    }

    public Complex multiply(int i) {
        if (this.isNaN) {
            return NaN;
        }
        if (Double.isInfinite(this.real) || Double.isInfinite(this.imaginary)) {
            return INF;
        }
        double d = (double) i;
        return createComplex(this.real * d, this.imaginary * d);
    }

    public Complex multiply(double d) {
        if (this.isNaN || Double.isNaN(d)) {
            return NaN;
        }
        if (Double.isInfinite(this.real) || Double.isInfinite(this.imaginary) || Double.isInfinite(d)) {
            return INF;
        }
        return createComplex(this.real * d, this.imaginary * d);
    }

    public Complex negate() {
        if (this.isNaN) {
            return NaN;
        }
        return createComplex(-this.real, -this.imaginary);
    }

    public Complex subtract(Complex complex) throws NullArgumentException {
        MathUtils.checkNotNull(complex);
        if (this.isNaN || complex.isNaN) {
            return NaN;
        }
        return createComplex(this.real - complex.getReal(), this.imaginary - complex.getImaginary());
    }

    public Complex subtract(double d) {
        if (this.isNaN || Double.isNaN(d)) {
            return NaN;
        }
        return createComplex(this.real - d, this.imaginary);
    }

    public Complex acos() {
        if (this.isNaN) {
            return NaN;
        }
        Complex sqrt1z = sqrt1z();
        Complex complex = I;
        return add(sqrt1z.multiply(complex)).log().multiply(complex.negate());
    }

    public Complex asin() {
        if (this.isNaN) {
            return NaN;
        }
        Complex sqrt1z = sqrt1z();
        Complex complex = I;
        return sqrt1z.add(multiply(complex)).log().multiply(complex.negate());
    }

    public Complex atan() {
        if (this.isNaN) {
            return NaN;
        }
        Complex complex = I;
        return add(complex).divide(complex.subtract(this)).log().multiply(complex.divide(createComplex(2.0d, 0.0d)));
    }

    public Complex cos() {
        if (this.isNaN) {
            return NaN;
        }
        return createComplex(FastMath.cos(this.real) * FastMath.cosh(this.imaginary), (-FastMath.sin(this.real)) * FastMath.sinh(this.imaginary));
    }

    public Complex cosh() {
        if (this.isNaN) {
            return NaN;
        }
        return createComplex(FastMath.cosh(this.real) * FastMath.cos(this.imaginary), FastMath.sinh(this.real) * FastMath.sin(this.imaginary));
    }

    public Complex exp() {
        if (this.isNaN) {
            return NaN;
        }
        double exp = FastMath.exp(this.real);
        return createComplex(FastMath.cos(this.imaginary) * exp, exp * FastMath.sin(this.imaginary));
    }

    public Complex log() {
        if (this.isNaN) {
            return NaN;
        }
        return createComplex(FastMath.log(abs()), FastMath.atan2(this.imaginary, this.real));
    }

    public Complex pow(Complex complex) throws NullArgumentException {
        MathUtils.checkNotNull(complex);
        return log().multiply(complex).exp();
    }

    public Complex pow(double d) {
        return log().multiply(d).exp();
    }

    public Complex sin() {
        if (this.isNaN) {
            return NaN;
        }
        return createComplex(FastMath.sin(this.real) * FastMath.cosh(this.imaginary), FastMath.cos(this.real) * FastMath.sinh(this.imaginary));
    }

    public Complex sinh() {
        if (this.isNaN) {
            return NaN;
        }
        return createComplex(FastMath.sinh(this.real) * FastMath.cos(this.imaginary), FastMath.cosh(this.real) * FastMath.sin(this.imaginary));
    }

    public Complex sqrt() {
        if (this.isNaN) {
            return NaN;
        }
        double d = this.real;
        if (d == 0.0d && this.imaginary == 0.0d) {
            return createComplex(0.0d, 0.0d);
        }
        double sqrt = FastMath.sqrt((FastMath.abs(d) + abs()) / 2.0d);
        if (this.real >= 0.0d) {
            return createComplex(sqrt, this.imaginary / (2.0d * sqrt));
        }
        return createComplex(FastMath.abs(this.imaginary) / (2.0d * sqrt), FastMath.copySign(1.0d, this.imaginary) * sqrt);
    }

    public Complex sqrt1z() {
        return createComplex(1.0d, 0.0d).subtract(multiply(this)).sqrt();
    }

    public Complex tan() {
        if (this.isNaN || Double.isInfinite(this.real)) {
            return NaN;
        }
        double d = this.imaginary;
        if (d > 20.0d) {
            return createComplex(0.0d, 1.0d);
        }
        if (d < -20.0d) {
            return createComplex(0.0d, -1.0d);
        }
        double d2 = this.real * 2.0d;
        double d3 = d * 2.0d;
        double cos = FastMath.cos(d2) + FastMath.cosh(d3);
        return createComplex(FastMath.sin(d2) / cos, FastMath.sinh(d3) / cos);
    }

    public Complex tanh() {
        if (this.isNaN || Double.isInfinite(this.imaginary)) {
            return NaN;
        }
        double d = this.real;
        if (d > 20.0d) {
            return createComplex(1.0d, 0.0d);
        }
        if (d < -20.0d) {
            return createComplex(-1.0d, 0.0d);
        }
        double d2 = d * 2.0d;
        double d3 = this.imaginary * 2.0d;
        double cosh = FastMath.cosh(d2) + FastMath.cos(d3);
        return createComplex(FastMath.sinh(d2) / cosh, FastMath.sin(d3) / cosh);
    }

    public double getArgument() {
        return FastMath.atan2(getImaginary(), getReal());
    }

    public List<Complex> nthRoot(int i) throws NotPositiveException {
        if (i > 0) {
            ArrayList arrayList = new ArrayList();
            if (this.isNaN) {
                arrayList.add(NaN);
                return arrayList;
            } else if (isInfinite()) {
                arrayList.add(INF);
                return arrayList;
            } else {
                double d = (double) i;
                double pow = FastMath.pow(abs(), 1.0d / d);
                double argument = getArgument() / d;
                double d2 = 6.283185307179586d / d;
                for (int i2 = 0; i2 < i; i2++) {
                    arrayList.add(createComplex(FastMath.cos(argument) * pow, FastMath.sin(argument) * pow));
                    argument += d2;
                }
                return arrayList;
            }
        } else {
            throw new NotPositiveException(LocalizedFormats.CANNOT_COMPUTE_NTH_ROOT_FOR_NEGATIVE_N, Integer.valueOf(i));
        }
    }

    /* access modifiers changed from: protected */
    public Complex createComplex(double d, double d2) {
        return new Complex(d, d2);
    }

    public static Complex valueOf(double d, double d2) {
        if (Double.isNaN(d) || Double.isNaN(d2)) {
            return NaN;
        }
        return new Complex(d, d2);
    }

    public static Complex valueOf(double d) {
        if (Double.isNaN(d)) {
            return NaN;
        }
        return new Complex(d);
    }

    /* access modifiers changed from: protected */
    public final Object readResolve() {
        return createComplex(this.real, this.imaginary);
    }

    public ComplexField getField() {
        return ComplexField.getInstance();
    }

    public String toString() {
        return "(" + this.real + ", " + this.imaginary + ")";
    }
}
