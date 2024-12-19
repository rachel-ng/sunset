package org.apache.commons.math3.optimization.univariate;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

@Deprecated
public class BrentOptimizer extends BaseAbstractUnivariateOptimizer {
    private static final double GOLDEN_SECTION = ((3.0d - FastMath.sqrt(5.0d)) * 0.5d);
    private static final double MIN_RELATIVE_TOLERANCE = (FastMath.ulp(1.0d) * 2.0d);
    private final double absoluteThreshold;
    private final double relativeThreshold;

    public BrentOptimizer(double d, double d2, ConvergenceChecker<UnivariatePointValuePair> convergenceChecker) {
        super(convergenceChecker);
        double d3 = MIN_RELATIVE_TOLERANCE;
        if (d < d3) {
            throw new NumberIsTooSmallException(Double.valueOf(d), Double.valueOf(d3), true);
        } else if (d2 > 0.0d) {
            this.relativeThreshold = d;
            this.absoluteThreshold = d2;
        } else {
            throw new NotStrictlyPositiveException(Double.valueOf(d2));
        }
    }

    public BrentOptimizer(double d, double d2) {
        this(d, d2, (ConvergenceChecker<UnivariatePointValuePair>) null);
    }

    /* access modifiers changed from: protected */
    public UnivariatePointValuePair doOptimize() {
        double d;
        boolean z;
        double d2;
        double d3;
        double d4;
        BrentOptimizer brentOptimizer = this;
        int i = 0;
        boolean z2 = getGoalType() == GoalType.MINIMIZE;
        double min = getMin();
        double startValue = getStartValue();
        double max = getMax();
        ConvergenceChecker<UnivariatePointValuePair> convergenceChecker = getConvergenceChecker();
        if (min >= max) {
            double d5 = min;
            min = max;
            max = d5;
        }
        double computeObjectiveValue = brentOptimizer.computeObjectiveValue(startValue);
        if (!z2) {
            computeObjectiveValue = -computeObjectiveValue;
        }
        UnivariatePointValuePair univariatePointValuePair = new UnivariatePointValuePair(startValue, z2 ? computeObjectiveValue : -computeObjectiveValue);
        double d6 = computeObjectiveValue;
        double d7 = d6;
        double d8 = d7;
        UnivariatePointValuePair univariatePointValuePair2 = univariatePointValuePair;
        UnivariatePointValuePair univariatePointValuePair3 = null;
        double d9 = 0.0d;
        double d10 = 0.0d;
        double d11 = startValue;
        double d12 = max;
        double d13 = d11;
        while (true) {
            double d14 = (min + d12) * 0.5d;
            ConvergenceChecker<UnivariatePointValuePair> convergenceChecker2 = convergenceChecker;
            int i2 = i;
            double d15 = d11;
            double abs = (brentOptimizer.relativeThreshold * FastMath.abs(startValue)) + brentOptimizer.absoluteThreshold;
            double d16 = abs * 2.0d;
            if (FastMath.abs(startValue - d14) <= d16 - ((d12 - min) * 0.5d)) {
                return brentOptimizer.best(univariatePointValuePair, brentOptimizer.best(univariatePointValuePair3, univariatePointValuePair2, z2), z2);
            }
            if (FastMath.abs(d9) > abs) {
                double d17 = startValue - d13;
                double d18 = (d6 - d7) * d17;
                double d19 = startValue - d15;
                double d20 = (d6 - d8) * d19;
                d = d13;
                double d21 = (d19 * d20) - (d17 * d18);
                double d22 = 2.0d * (d20 - d18);
                if (d22 > 0.0d) {
                    d21 = -d21;
                } else {
                    d22 = -d22;
                }
                double d23 = min - startValue;
                if (d21 <= d22 * d23 || d21 >= (d12 - startValue) * d22 || FastMath.abs(d21) >= FastMath.abs(0.5d * d22 * d9)) {
                    if (startValue < d14) {
                        d23 = d12 - startValue;
                    }
                    d10 = GOLDEN_SECTION * d23;
                    d9 = d23;
                } else {
                    double d24 = d21 / d22;
                    double d25 = startValue + d24;
                    if (d25 - min < d16 || d12 - d25 < d16) {
                        if (startValue <= d14) {
                            d9 = d10;
                            d10 = abs;
                        } else {
                            d24 = -abs;
                        }
                    }
                    d9 = d10;
                    d10 = d24;
                }
            } else {
                d = d13;
                double d26 = startValue < d14 ? d12 - startValue : min - startValue;
                d9 = d26;
                d10 = GOLDEN_SECTION * d26;
            }
            double d27 = FastMath.abs(d10) < abs ? d10 >= 0.0d ? abs + startValue : startValue - abs : startValue + d10;
            double computeObjectiveValue2 = brentOptimizer.computeObjectiveValue(d27);
            if (!z2) {
                computeObjectiveValue2 = -computeObjectiveValue2;
            }
            UnivariatePointValuePair univariatePointValuePair4 = new UnivariatePointValuePair(d27, z2 ? computeObjectiveValue2 : -computeObjectiveValue2);
            univariatePointValuePair = brentOptimizer.best(univariatePointValuePair, brentOptimizer.best(univariatePointValuePair2, univariatePointValuePair4, z2), z2);
            ConvergenceChecker<UnivariatePointValuePair> convergenceChecker3 = convergenceChecker2;
            int i3 = i2;
            if (convergenceChecker2 != null && convergenceChecker3.converged(i3, univariatePointValuePair2, univariatePointValuePair4)) {
                return univariatePointValuePair;
            }
            if (computeObjectiveValue2 <= d6) {
                if (d27 < startValue) {
                    d12 = startValue;
                } else {
                    min = startValue;
                }
                z = z2;
                d2 = startValue;
                d7 = d8;
                startValue = d27;
                d8 = d6;
                d6 = computeObjectiveValue2;
                d11 = d;
            } else {
                if (d27 < startValue) {
                    min = d27;
                } else {
                    d12 = d27;
                }
                if (computeObjectiveValue2 > d8) {
                    z = z2;
                    d2 = d;
                    if (Precision.equals(d2, startValue)) {
                        d3 = d27;
                    } else {
                        if (computeObjectiveValue2 > d7) {
                            d4 = d27;
                            double d28 = d15;
                            if (!Precision.equals(d28, startValue) && !Precision.equals(d28, d2)) {
                                d11 = d28;
                            }
                        } else {
                            d4 = d27;
                        }
                        d7 = computeObjectiveValue2;
                        d11 = d4;
                    }
                } else {
                    z = z2;
                    d3 = d27;
                    d2 = d;
                }
                d7 = d8;
                d8 = computeObjectiveValue2;
                d11 = d2;
                d2 = d3;
            }
            i = i3 + 1;
            convergenceChecker = convergenceChecker3;
            d13 = d2;
            z2 = z;
            brentOptimizer = this;
            UnivariatePointValuePair univariatePointValuePair5 = univariatePointValuePair4;
            univariatePointValuePair3 = univariatePointValuePair2;
            univariatePointValuePair2 = univariatePointValuePair5;
        }
    }

    private UnivariatePointValuePair best(UnivariatePointValuePair univariatePointValuePair, UnivariatePointValuePair univariatePointValuePair2, boolean z) {
        if (univariatePointValuePair == null) {
            return univariatePointValuePair2;
        }
        if (univariatePointValuePair2 == null) {
            return univariatePointValuePair;
        }
        return z ? univariatePointValuePair.getValue() <= univariatePointValuePair2.getValue() ? univariatePointValuePair : univariatePointValuePair2 : univariatePointValuePair.getValue() >= univariatePointValuePair2.getValue() ? univariatePointValuePair : univariatePointValuePair2;
    }
}
