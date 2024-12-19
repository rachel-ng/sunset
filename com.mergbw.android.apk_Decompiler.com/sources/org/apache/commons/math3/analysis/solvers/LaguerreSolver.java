package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.complex.ComplexUtils;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;

public class LaguerreSolver extends AbstractPolynomialSolver {
    private static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6d;
    private final ComplexSolver complexSolver;

    public LaguerreSolver() {
        this(1.0E-6d);
    }

    public LaguerreSolver(double d) {
        super(d);
        this.complexSolver = new ComplexSolver();
    }

    public LaguerreSolver(double d, double d2) {
        super(d, d2);
        this.complexSolver = new ComplexSolver();
    }

    public LaguerreSolver(double d, double d2, double d3) {
        super(d, d2, d3);
        this.complexSolver = new ComplexSolver();
    }

    public double doSolve() throws TooManyEvaluationsException, NumberIsTooLargeException, NoBracketingException {
        double min = getMin();
        double max = getMax();
        double startValue = getStartValue();
        double functionValueAccuracy = getFunctionValueAccuracy();
        verifySequence(min, startValue, max);
        double computeObjectiveValue = computeObjectiveValue(startValue);
        if (FastMath.abs(computeObjectiveValue) <= functionValueAccuracy) {
            return startValue;
        }
        double computeObjectiveValue2 = computeObjectiveValue(min);
        if (FastMath.abs(computeObjectiveValue2) <= functionValueAccuracy) {
            return min;
        }
        if (computeObjectiveValue * computeObjectiveValue2 < 0.0d) {
            return laguerre(min, startValue, computeObjectiveValue2, computeObjectiveValue);
        }
        double computeObjectiveValue3 = computeObjectiveValue(max);
        if (FastMath.abs(computeObjectiveValue3) <= functionValueAccuracy) {
            return max;
        }
        if (computeObjectiveValue * computeObjectiveValue3 < 0.0d) {
            return laguerre(startValue, max, computeObjectiveValue, computeObjectiveValue3);
        }
        throw new NoBracketingException(min, max, computeObjectiveValue2, computeObjectiveValue3);
    }

    @Deprecated
    public double laguerre(double d, double d2, double d3, double d4) {
        Complex[] convertToComplex = ComplexUtils.convertToComplex(getCoefficients());
        Complex complex = new Complex((d + d2) * 0.5d, 0.0d);
        Complex solve = this.complexSolver.solve(convertToComplex, complex);
        if (this.complexSolver.isRoot(d, d2, solve)) {
            return solve.getReal();
        }
        Complex[] solveAll = this.complexSolver.solveAll(convertToComplex, complex);
        for (int i = 0; i < solveAll.length; i++) {
            if (this.complexSolver.isRoot(d, d2, solveAll[i])) {
                return solveAll[i].getReal();
            }
        }
        return Double.NaN;
    }

    public Complex[] solveAllComplex(double[] dArr, double d) throws NullArgumentException, NoDataException, TooManyEvaluationsException {
        return solveAllComplex(dArr, d, Integer.MAX_VALUE);
    }

    public Complex[] solveAllComplex(double[] dArr, double d, int i) throws NullArgumentException, NoDataException, TooManyEvaluationsException {
        setup(i, new PolynomialFunction(dArr), Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, d);
        return this.complexSolver.solveAll(ComplexUtils.convertToComplex(dArr), new Complex(d, 0.0d));
    }

    public Complex solveComplex(double[] dArr, double d) throws NullArgumentException, NoDataException, TooManyEvaluationsException {
        return solveComplex(dArr, d, Integer.MAX_VALUE);
    }

    public Complex solveComplex(double[] dArr, double d, int i) throws NullArgumentException, NoDataException, TooManyEvaluationsException {
        setup(i, new PolynomialFunction(dArr), Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, d);
        return this.complexSolver.solve(ComplexUtils.convertToComplex(dArr), new Complex(d, 0.0d));
    }

    private class ComplexSolver {
        private ComplexSolver() {
        }

        public boolean isRoot(double d, double d2, Complex complex) {
            if (!LaguerreSolver.this.isSequence(d, complex.getReal(), d2)) {
                return false;
            }
            if (FastMath.abs(complex.getImaginary()) <= FastMath.max(LaguerreSolver.this.getRelativeAccuracy() * complex.abs(), LaguerreSolver.this.getAbsoluteAccuracy()) || complex.abs() <= LaguerreSolver.this.getFunctionValueAccuracy()) {
                return true;
            }
            return false;
        }

        public Complex[] solveAll(Complex[] complexArr, Complex complex) throws NullArgumentException, NoDataException, TooManyEvaluationsException {
            if (complexArr != null) {
                int length = complexArr.length;
                int i = length - 1;
                if (i != 0) {
                    Complex[] complexArr2 = new Complex[length];
                    for (int i2 = 0; i2 <= i; i2++) {
                        complexArr2[i2] = complexArr[i2];
                    }
                    Complex[] complexArr3 = new Complex[i];
                    for (int i3 = 0; i3 < i; i3++) {
                        int i4 = i - i3;
                        int i5 = i4 + 1;
                        Complex[] complexArr4 = new Complex[i5];
                        System.arraycopy(complexArr2, 0, complexArr4, 0, i5);
                        complexArr3[i3] = solve(complexArr4, complex);
                        Complex complex2 = complexArr2[i4];
                        for (int i6 = i4 - 1; i6 >= 0; i6--) {
                            Complex complex3 = complexArr2[i6];
                            complexArr2[i6] = complex2;
                            complex2 = complex3.add(complex2.multiply(complexArr3[i3]));
                        }
                    }
                    return complexArr3;
                }
                throw new NoDataException(LocalizedFormats.POLYNOMIAL);
            }
            throw new NullArgumentException();
        }

        public Complex solve(Complex[] complexArr, Complex complex) throws NullArgumentException, NoDataException, TooManyEvaluationsException {
            Complex complex2;
            Complex complex3;
            Complex[] complexArr2 = complexArr;
            if (complexArr2 != null) {
                int length = complexArr2.length;
                int i = length - 1;
                if (i != 0) {
                    double absoluteAccuracy = LaguerreSolver.this.getAbsoluteAccuracy();
                    double relativeAccuracy = LaguerreSolver.this.getRelativeAccuracy();
                    double functionValueAccuracy = LaguerreSolver.this.getFunctionValueAccuracy();
                    Complex complex4 = new Complex((double) i, 0.0d);
                    int i2 = length - 2;
                    double d = functionValueAccuracy;
                    Complex complex5 = new Complex((double) i2, 0.0d);
                    Complex complex6 = new Complex(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
                    Complex complex7 = complex;
                    while (true) {
                        Complex complex8 = complexArr2[i];
                        int i3 = i2;
                        Complex complex9 = Complex.ZERO;
                        Complex complex10 = Complex.ZERO;
                        while (i3 >= 0) {
                            complex10 = complex9.add(complex7.multiply(complex10));
                            complex9 = complex8.add(complex7.multiply(complex9));
                            complex8 = complexArr2[i3].add(complex7.multiply(complex8));
                            i3--;
                            i2 = i2;
                        }
                        int i4 = i2;
                        Complex complex11 = complex4;
                        Complex complex12 = complex5;
                        Complex multiply = complex10.multiply(new Complex(2.0d, 0.0d));
                        if (complex7.subtract(complex6).abs() <= FastMath.max(complex7.abs() * relativeAccuracy, absoluteAccuracy) || complex8.abs() <= d) {
                            return complex7;
                        }
                        Complex divide = complex9.divide(complex8);
                        Complex multiply2 = divide.multiply(divide);
                        Complex complex13 = complex11;
                        Complex subtract = complex13.multiply(multiply2.subtract(multiply.divide(complex8))).subtract(multiply2);
                        Complex complex14 = complex12;
                        Complex sqrt = complex14.multiply(subtract).sqrt();
                        Complex add = divide.add(sqrt);
                        Complex subtract2 = divide.subtract(sqrt);
                        if (add.abs() <= subtract2.abs()) {
                            add = subtract2;
                        }
                        if (add.equals(new Complex(0.0d, 0.0d))) {
                            complex3 = complex7.add(new Complex(absoluteAccuracy, absoluteAccuracy));
                            complex2 = new Complex(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
                        } else {
                            complex3 = complex7.subtract(complex13.divide(add));
                            complex2 = complex7;
                        }
                        complex7 = complex3;
                        LaguerreSolver.this.incrementEvaluationCount();
                        complex5 = complex14;
                        complex4 = complex13;
                        i2 = i4;
                        complex6 = complex2;
                        complexArr2 = complexArr;
                    }
                } else {
                    throw new NoDataException(LocalizedFormats.POLYNOMIAL);
                }
            } else {
                throw new NullArgumentException();
            }
        }
    }
}
