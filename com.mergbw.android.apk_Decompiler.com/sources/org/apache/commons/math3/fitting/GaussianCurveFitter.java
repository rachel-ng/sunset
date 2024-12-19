package org.apache.commons.math3.fitting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.math3.analysis.function.Gaussian;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.fitting.AbstractCurveFitter;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem;
import org.apache.commons.math3.linear.DiagonalMatrix;
import org.apache.commons.math3.util.FastMath;

public class GaussianCurveFitter extends AbstractCurveFitter {
    private static final Gaussian.Parametric FUNCTION = new Gaussian.Parametric() {
        public double value(double d, double... dArr) {
            try {
                return super.value(d, dArr);
            } catch (NotStrictlyPositiveException unused) {
                return Double.POSITIVE_INFINITY;
            }
        }

        public double[] gradient(double d, double... dArr) {
            try {
                return super.gradient(d, dArr);
            } catch (NotStrictlyPositiveException unused) {
                return new double[]{Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY};
            }
        }
    };
    private final double[] initialGuess;
    private final int maxIter;

    private GaussianCurveFitter(double[] dArr, int i) {
        this.initialGuess = dArr;
        this.maxIter = i;
    }

    public static GaussianCurveFitter create() {
        return new GaussianCurveFitter((double[]) null, Integer.MAX_VALUE);
    }

    public GaussianCurveFitter withStartPoint(double[] dArr) {
        return new GaussianCurveFitter((double[]) dArr.clone(), this.maxIter);
    }

    public GaussianCurveFitter withMaxIterations(int i) {
        return new GaussianCurveFitter(this.initialGuess, i);
    }

    /* access modifiers changed from: protected */
    public LeastSquaresProblem getProblem(Collection<WeightedObservedPoint> collection) {
        int size = collection.size();
        double[] dArr = new double[size];
        double[] dArr2 = new double[size];
        int i = 0;
        for (WeightedObservedPoint next : collection) {
            dArr[i] = next.getY();
            dArr2[i] = next.getWeight();
            i++;
        }
        AbstractCurveFitter.TheoreticalValuesFunction theoreticalValuesFunction = new AbstractCurveFitter.TheoreticalValuesFunction(FUNCTION, collection);
        double[] dArr3 = this.initialGuess;
        if (dArr3 == null) {
            dArr3 = new ParameterGuesser(collection).guess();
        }
        return new LeastSquaresBuilder().maxEvaluations(Integer.MAX_VALUE).maxIterations(this.maxIter).start(dArr3).target(dArr).weight(new DiagonalMatrix(dArr2)).model(theoreticalValuesFunction.getModelFunction(), theoreticalValuesFunction.getModelFunctionJacobian()).build();
    }

    public static class ParameterGuesser {
        private final double mean;
        private final double norm;
        private final double sigma;

        private boolean isBetween(double d, double d2, double d3) {
            return (d >= d2 && d <= d3) || (d >= d3 && d <= d2);
        }

        public ParameterGuesser(Collection<WeightedObservedPoint> collection) {
            if (collection == null) {
                throw new NullArgumentException(LocalizedFormats.INPUT_ARRAY, new Object[0]);
            } else if (collection.size() >= 3) {
                double[] basicGuess = basicGuess((WeightedObservedPoint[]) sortObservations(collection).toArray(new WeightedObservedPoint[0]));
                this.norm = basicGuess[0];
                this.mean = basicGuess[1];
                this.sigma = basicGuess[2];
            } else {
                throw new NumberIsTooSmallException(Integer.valueOf(collection.size()), 3, true);
            }
        }

        public double[] guess() {
            return new double[]{this.norm, this.mean, this.sigma};
        }

        private List<WeightedObservedPoint> sortObservations(Collection<WeightedObservedPoint> collection) {
            ArrayList arrayList = new ArrayList(collection);
            Collections.sort(arrayList, new Comparator<WeightedObservedPoint>() {
                public int compare(WeightedObservedPoint weightedObservedPoint, WeightedObservedPoint weightedObservedPoint2) {
                    if (weightedObservedPoint == null && weightedObservedPoint2 == null) {
                        return 0;
                    }
                    if (weightedObservedPoint == null) {
                        return -1;
                    }
                    if (weightedObservedPoint2 == null) {
                        return 1;
                    }
                    if (weightedObservedPoint.getX() < weightedObservedPoint2.getX()) {
                        return -1;
                    }
                    if (weightedObservedPoint.getX() > weightedObservedPoint2.getX()) {
                        return 1;
                    }
                    if (weightedObservedPoint.getY() < weightedObservedPoint2.getY()) {
                        return -1;
                    }
                    if (weightedObservedPoint.getY() > weightedObservedPoint2.getY()) {
                        return 1;
                    }
                    if (weightedObservedPoint.getWeight() < weightedObservedPoint2.getWeight()) {
                        return -1;
                    }
                    return weightedObservedPoint.getWeight() > weightedObservedPoint2.getWeight() ? 1 : 0;
                }
            });
            return arrayList;
        }

        private double[] basicGuess(WeightedObservedPoint[] weightedObservedPointArr) {
            double d;
            WeightedObservedPoint[] weightedObservedPointArr2 = weightedObservedPointArr;
            int findMaxY = findMaxY(weightedObservedPointArr);
            double y = weightedObservedPointArr2[findMaxY].getY();
            double x = weightedObservedPointArr2[findMaxY].getX();
            double d2 = y + ((x - y) / 2.0d);
            try {
                d = interpolateXAtY(weightedObservedPointArr, findMaxY, 1, d2) - interpolateXAtY(weightedObservedPointArr, findMaxY, -1, d2);
            } catch (OutOfRangeException unused) {
                d = weightedObservedPointArr2[weightedObservedPointArr2.length - 1].getX() - weightedObservedPointArr2[0].getX();
            }
            return new double[]{y, x, d / (FastMath.sqrt(FastMath.log(2.0d) * 2.0d) * 2.0d)};
        }

        private int findMaxY(WeightedObservedPoint[] weightedObservedPointArr) {
            int i = 0;
            for (int i2 = 1; i2 < weightedObservedPointArr.length; i2++) {
                if (weightedObservedPointArr[i2].getY() > weightedObservedPointArr[i].getY()) {
                    i = i2;
                }
            }
            return i;
        }

        private double interpolateXAtY(WeightedObservedPoint[] weightedObservedPointArr, int i, int i2, double d) throws OutOfRangeException {
            if (i2 != 0) {
                WeightedObservedPoint[] interpolationPointsForY = getInterpolationPointsForY(weightedObservedPointArr, i, i2, d);
                WeightedObservedPoint weightedObservedPoint = interpolationPointsForY[0];
                WeightedObservedPoint weightedObservedPoint2 = interpolationPointsForY[1];
                if (weightedObservedPoint.getY() == d) {
                    return weightedObservedPoint.getX();
                }
                if (weightedObservedPoint2.getY() == d) {
                    return weightedObservedPoint2.getX();
                }
                return weightedObservedPoint.getX() + (((d - weightedObservedPoint.getY()) * (weightedObservedPoint2.getX() - weightedObservedPoint.getX())) / (weightedObservedPoint2.getY() - weightedObservedPoint.getY()));
            }
            throw new ZeroException();
        }

        private WeightedObservedPoint[] getInterpolationPointsForY(WeightedObservedPoint[] weightedObservedPointArr, int i, int i2, double d) throws OutOfRangeException {
            WeightedObservedPoint weightedObservedPoint;
            WeightedObservedPoint weightedObservedPoint2;
            if (i2 != 0) {
                do {
                    int i3 = i + i2;
                    if (i2 < 0) {
                        if (i3 < 0) {
                            throw new OutOfRangeException(Double.valueOf(d), Double.valueOf(Double.NEGATIVE_INFINITY), Double.valueOf(Double.POSITIVE_INFINITY));
                        }
                    } else if (i3 >= weightedObservedPointArr.length) {
                        throw new OutOfRangeException(Double.valueOf(d), Double.valueOf(Double.NEGATIVE_INFINITY), Double.valueOf(Double.POSITIVE_INFINITY));
                    }
                    weightedObservedPoint = weightedObservedPointArr[i];
                    i += i2;
                    weightedObservedPoint2 = weightedObservedPointArr[i];
                } while (!isBetween(d, weightedObservedPoint.getY(), weightedObservedPoint2.getY()));
                if (i2 < 0) {
                    return new WeightedObservedPoint[]{weightedObservedPoint2, weightedObservedPoint};
                }
                return new WeightedObservedPoint[]{weightedObservedPoint, weightedObservedPoint2};
            }
            throw new ZeroException();
        }
    }
}
