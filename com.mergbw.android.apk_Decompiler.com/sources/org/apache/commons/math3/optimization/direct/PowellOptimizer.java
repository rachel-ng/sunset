package org.apache.commons.math3.optimization.direct;

import java.lang.reflect.Array;
import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.MultivariateOptimizer;
import org.apache.commons.math3.optimization.PointValuePair;
import org.apache.commons.math3.optimization.univariate.BracketFinder;
import org.apache.commons.math3.optimization.univariate.BrentOptimizer;
import org.apache.commons.math3.optimization.univariate.SimpleUnivariateValueChecker;
import org.apache.commons.math3.optimization.univariate.UnivariatePointValuePair;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

@Deprecated
public class PowellOptimizer extends BaseAbstractMultivariateOptimizer<MultivariateFunction> implements MultivariateOptimizer {
    private static final double MIN_RELATIVE_TOLERANCE = (FastMath.ulp(1.0d) * 2.0d);
    private final double absoluteThreshold;
    private final LineSearch line;
    private final double relativeThreshold;

    public PowellOptimizer(double d, double d2, ConvergenceChecker<PointValuePair> convergenceChecker) {
        this(d, d2, FastMath.sqrt(d), FastMath.sqrt(d2), convergenceChecker);
    }

    public PowellOptimizer(double d, double d2, double d3, double d4, ConvergenceChecker<PointValuePair> convergenceChecker) {
        super(convergenceChecker);
        double d5 = MIN_RELATIVE_TOLERANCE;
        if (d < d5) {
            throw new NumberIsTooSmallException(Double.valueOf(d), Double.valueOf(d5), true);
        } else if (d2 > 0.0d) {
            this.relativeThreshold = d;
            this.absoluteThreshold = d2;
            this.line = new LineSearch(d3, d4);
        } else {
            throw new NotStrictlyPositiveException(Double.valueOf(d2));
        }
    }

    public PowellOptimizer(double d, double d2) {
        this(d, d2, (ConvergenceChecker<PointValuePair>) null);
    }

    public PowellOptimizer(double d, double d2, double d3, double d4) {
        this(d, d2, d3, d4, (ConvergenceChecker<PointValuePair>) null);
    }

    /* access modifiers changed from: protected */
    public PointValuePair doOptimize() {
        double d;
        PointValuePair pointValuePair;
        PointValuePair pointValuePair2;
        ConvergenceChecker<PointValuePair> convergenceChecker;
        int i;
        double d2;
        int i2;
        GoalType goalType = getGoalType();
        double[] startPoint = getStartPoint();
        int length = startPoint.length;
        int[] iArr = new int[2];
        int i3 = 1;
        iArr[1] = length;
        int i4 = 0;
        iArr[0] = length;
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr);
        for (int i5 = 0; i5 < length; i5++) {
            dArr[i5][i5] = 1.0d;
        }
        ConvergenceChecker<PointValuePair> convergenceChecker2 = getConvergenceChecker();
        double computeObjectiveValue = computeObjectiveValue(startPoint);
        double[] dArr2 = (double[]) startPoint.clone();
        int i6 = 0;
        while (true) {
            i6 += i3;
            int i7 = i4;
            int i8 = i7;
            d = computeObjectiveValue;
            double d3 = 0.0d;
            while (i7 < length) {
                double[] copyOf = MathArrays.copyOf(dArr[i7]);
                UnivariatePointValuePair search = this.line.search(startPoint, copyOf);
                double value = search.getValue();
                double[][] dArr3 = dArr;
                startPoint = newPointAndDirection(startPoint, copyOf, search.getPoint())[i4];
                double d4 = d - value;
                if (d4 > d3) {
                    d3 = d4;
                    i8 = i7;
                }
                i7++;
                dArr = dArr3;
                d = value;
            }
            double[][] dArr4 = dArr;
            double d5 = computeObjectiveValue - d;
            ConvergenceChecker<PointValuePair> convergenceChecker3 = convergenceChecker2;
            boolean z = d5 * 2.0d <= (this.relativeThreshold * (FastMath.abs(computeObjectiveValue) + FastMath.abs(d))) + this.absoluteThreshold;
            pointValuePair = new PointValuePair(dArr2, computeObjectiveValue);
            pointValuePair2 = new PointValuePair(startPoint, d);
            if (z || convergenceChecker3 == null) {
                convergenceChecker = convergenceChecker3;
            } else {
                convergenceChecker = convergenceChecker3;
                z = convergenceChecker.converged(i6, pointValuePair, pointValuePair2);
            }
            if (z) {
                break;
            }
            double[] dArr5 = new double[length];
            double[] dArr6 = new double[length];
            for (int i9 = 0; i9 < length; i9++) {
                dArr5[i9] = startPoint[i9] - dArr2[i9];
                dArr6[i9] = (startPoint[i9] * 2.0d) - dArr2[i9];
            }
            dArr2 = (double[]) startPoint.clone();
            double computeObjectiveValue2 = computeObjectiveValue(dArr6);
            if (computeObjectiveValue > computeObjectiveValue2) {
                double d6 = d5 - d3;
                double d7 = computeObjectiveValue - computeObjectiveValue2;
                if (((((computeObjectiveValue + computeObjectiveValue2) - (d * 2.0d)) * 2.0d) * (d6 * d6)) - ((d3 * d7) * d7) < 0.0d) {
                    UnivariatePointValuePair search2 = this.line.search(startPoint, dArr5);
                    d2 = search2.getValue();
                    double[][] newPointAndDirection = newPointAndDirection(startPoint, dArr5, search2.getPoint());
                    i2 = 0;
                    double[] dArr7 = newPointAndDirection[0];
                    int i10 = length - 1;
                    dArr4[i8] = dArr4[i10];
                    i = 1;
                    dArr4[i10] = newPointAndDirection[1];
                    startPoint = dArr7;
                    i4 = i2;
                    i3 = i;
                    dArr = dArr4;
                    computeObjectiveValue = d2;
                    convergenceChecker2 = convergenceChecker;
                }
            }
            i2 = 0;
            i = 1;
            d2 = d;
            i4 = i2;
            i3 = i;
            dArr = dArr4;
            computeObjectiveValue = d2;
            convergenceChecker2 = convergenceChecker;
        }
        return goalType == GoalType.MINIMIZE ? d < computeObjectiveValue ? pointValuePair2 : pointValuePair : d > computeObjectiveValue ? pointValuePair2 : pointValuePair;
    }

    private double[][] newPointAndDirection(double[] dArr, double[] dArr2, double d) {
        int length = dArr.length;
        double[] dArr3 = new double[length];
        double[] dArr4 = new double[length];
        for (int i = 0; i < length; i++) {
            double d2 = dArr2[i] * d;
            dArr4[i] = d2;
            dArr3[i] = dArr[i] + d2;
        }
        return new double[][]{dArr3, dArr4};
    }

    private class LineSearch extends BrentOptimizer {
        private static final double ABS_TOL_UNUSED = Double.MIN_VALUE;
        private static final double REL_TOL_UNUSED = 1.0E-15d;
        private final BracketFinder bracket = new BracketFinder();

        LineSearch(double d, double d2) {
            super(1.0E-15d, Double.MIN_VALUE, new SimpleUnivariateValueChecker(d, d2));
        }

        public UnivariatePointValuePair search(final double[] dArr, final double[] dArr2) {
            final int length = dArr.length;
            AnonymousClass1 r8 = new UnivariateFunction() {
                public double value(double d) {
                    double[] dArr = new double[length];
                    for (int i = 0; i < length; i++) {
                        dArr[i] = dArr[i] + (dArr2[i] * d);
                    }
                    return PowellOptimizer.this.computeObjectiveValue(dArr);
                }
            };
            GoalType goalType = PowellOptimizer.this.getGoalType();
            this.bracket.search(r8, goalType, 0.0d, 1.0d);
            return optimize(Integer.MAX_VALUE, r8, goalType, this.bracket.getLo(), this.bracket.getHi(), this.bracket.getMid());
        }
    }
}
