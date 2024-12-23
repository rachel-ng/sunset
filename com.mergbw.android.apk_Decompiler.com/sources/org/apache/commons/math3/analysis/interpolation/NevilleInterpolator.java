package org.apache.commons.math3.analysis.interpolation;

import java.io.Serializable;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;

public class NevilleInterpolator implements UnivariateInterpolator, Serializable {
    static final long serialVersionUID = 3003707660147873733L;

    public PolynomialFunctionLagrangeForm interpolate(double[] dArr, double[] dArr2) throws DimensionMismatchException, NumberIsTooSmallException, NonMonotonicSequenceException {
        return new PolynomialFunctionLagrangeForm(dArr, dArr2);
    }
}
