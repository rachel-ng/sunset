package org.apache.commons.math3.analysis.interpolation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableVectorFunction;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.CombinatoricsUtils;

public class HermiteInterpolator implements UnivariateDifferentiableVectorFunction {
    private final List<Double> abscissae = new ArrayList();
    private final List<double[]> bottomDiagonal = new ArrayList();
    private final List<double[]> topDiagonal = new ArrayList();

    public void addSamplePoint(double d, double[]... dArr) throws ZeroException, MathArithmeticException {
        double[][] dArr2 = dArr;
        for (int i = 0; i < dArr2.length; i++) {
            double[] dArr3 = (double[]) dArr2[i].clone();
            if (i > 1) {
                double factorial = 1.0d / ((double) CombinatoricsUtils.factorial(i));
                for (int i2 = 0; i2 < dArr3.length; i2++) {
                    dArr3[i2] = dArr3[i2] * factorial;
                }
            }
            int size = this.abscissae.size();
            this.bottomDiagonal.add(size - i, dArr3);
            int i3 = i;
            double[] dArr4 = dArr3;
            while (i3 < size) {
                i3++;
                int i4 = size - i3;
                double[] dArr5 = this.bottomDiagonal.get(i4);
                double doubleValue = 1.0d / (d - this.abscissae.get(i4).doubleValue());
                if (!Double.isInfinite(doubleValue)) {
                    for (int i5 = 0; i5 < dArr3.length; i5++) {
                        dArr5[i5] = (dArr4[i5] - dArr5[i5]) * doubleValue;
                    }
                    dArr4 = dArr5;
                } else {
                    throw new ZeroException(LocalizedFormats.DUPLICATED_ABSCISSA_DIVISION_BY_ZERO, Double.valueOf(d));
                }
            }
            this.topDiagonal.add(dArr4.clone());
            this.abscissae.add(Double.valueOf(d));
        }
    }

    public PolynomialFunction[] getPolynomials() throws NoDataException {
        checkInterpolation();
        PolynomialFunction polynomial = polynomial(0.0d);
        int length = this.topDiagonal.get(0).length;
        PolynomialFunction[] polynomialFunctionArr = new PolynomialFunction[length];
        for (int i = 0; i < length; i++) {
            polynomialFunctionArr[i] = polynomial;
        }
        PolynomialFunction polynomial2 = polynomial(1.0d);
        for (int i2 = 0; i2 < this.topDiagonal.size(); i2++) {
            double[] dArr = this.topDiagonal.get(i2);
            for (int i3 = 0; i3 < length; i3++) {
                polynomialFunctionArr[i3] = polynomialFunctionArr[i3].add(polynomial2.multiply(polynomial(dArr[i3])));
            }
            polynomial2 = polynomial2.multiply(polynomial(-this.abscissae.get(i2).doubleValue(), 1.0d));
        }
        return polynomialFunctionArr;
    }

    public double[] value(double d) throws NoDataException {
        checkInterpolation();
        int length = this.topDiagonal.get(0).length;
        double[] dArr = new double[length];
        double d2 = 1.0d;
        for (int i = 0; i < this.topDiagonal.size(); i++) {
            double[] dArr2 = this.topDiagonal.get(i);
            for (int i2 = 0; i2 < length; i2++) {
                dArr[i2] = dArr[i2] + (dArr2[i2] * d2);
            }
            d2 *= d - this.abscissae.get(i).doubleValue();
        }
        return dArr;
    }

    public DerivativeStructure[] value(DerivativeStructure derivativeStructure) throws NoDataException {
        checkInterpolation();
        int length = this.topDiagonal.get(0).length;
        DerivativeStructure[] derivativeStructureArr = new DerivativeStructure[length];
        Arrays.fill(derivativeStructureArr, derivativeStructure.getField().getZero());
        DerivativeStructure one = derivativeStructure.getField().getOne();
        for (int i = 0; i < this.topDiagonal.size(); i++) {
            double[] dArr = this.topDiagonal.get(i);
            for (int i2 = 0; i2 < length; i2++) {
                derivativeStructureArr[i2] = derivativeStructureArr[i2].add(one.multiply(dArr[i2]));
            }
            one = one.multiply(derivativeStructure.subtract(this.abscissae.get(i).doubleValue()));
        }
        return derivativeStructureArr;
    }

    private void checkInterpolation() throws NoDataException {
        if (this.abscissae.isEmpty()) {
            throw new NoDataException(LocalizedFormats.EMPTY_INTERPOLATION_SAMPLE);
        }
    }

    private PolynomialFunction polynomial(double... dArr) {
        return new PolynomialFunction(dArr);
    }
}
