package org.apache.commons.math3.analysis.integration.gauss;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.util.Pair;

public class SymmetricGaussIntegrator extends GaussIntegrator {
    public SymmetricGaussIntegrator(double[] dArr, double[] dArr2) throws NonMonotonicSequenceException, DimensionMismatchException {
        super(dArr, dArr2);
    }

    public SymmetricGaussIntegrator(Pair<double[], double[]> pair) throws NonMonotonicSequenceException {
        this(pair.getFirst(), pair.getSecond());
    }

    public double integrate(UnivariateFunction univariateFunction) {
        UnivariateFunction univariateFunction2 = univariateFunction;
        int numberOfPoints = getNumberOfPoints();
        int i = 0;
        if (numberOfPoints == 1) {
            return getWeight(0) * univariateFunction2.value(0.0d);
        }
        int i2 = numberOfPoints / 2;
        double d = 0.0d;
        double d2 = 0.0d;
        while (i < i2) {
            double point = getPoint(i);
            double weight = (getWeight(i) * (univariateFunction2.value(point) + univariateFunction2.value(-point))) - d;
            double d3 = d2 + weight;
            i++;
            double d4 = d3;
            d = (d3 - d2) - weight;
            d2 = d4;
        }
        return numberOfPoints % 2 != 0 ? d2 + ((getWeight(i2) * univariateFunction2.value(0.0d)) - d) : d2;
    }
}
