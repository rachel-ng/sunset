package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.util.FastMath;

public abstract class BaseSecantSolver extends AbstractUnivariateSolver implements BracketedUnivariateSolver<UnivariateFunction> {
    protected static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6d;
    private AllowedSolution allowed = AllowedSolution.ANY_SIDE;
    private final Method method;

    protected enum Method {
        REGULA_FALSI,
        ILLINOIS,
        PEGASUS
    }

    protected BaseSecantSolver(double d, Method method2) {
        super(d);
        this.method = method2;
    }

    protected BaseSecantSolver(double d, double d2, Method method2) {
        super(d, d2);
        this.method = method2;
    }

    protected BaseSecantSolver(double d, double d2, double d3, Method method2) {
        super(d, d2, d3);
        this.method = method2;
    }

    public double solve(int i, UnivariateFunction univariateFunction, double d, double d2, AllowedSolution allowedSolution) {
        return solve(i, univariateFunction, d, d2, d + ((d2 - d) * 0.5d), allowedSolution);
    }

    public double solve(int i, UnivariateFunction univariateFunction, double d, double d2, double d3, AllowedSolution allowedSolution) {
        this.allowed = allowedSolution;
        return super.solve(i, univariateFunction, d, d2, d3);
    }

    public double solve(int i, UnivariateFunction univariateFunction, double d, double d2, double d3) {
        return solve(i, univariateFunction, d, d2, d3, AllowedSolution.ANY_SIDE);
    }

    /* access modifiers changed from: protected */
    public final double doSolve() throws ConvergenceException {
        double d;
        double min = getMin();
        double max = getMax();
        double computeObjectiveValue = computeObjectiveValue(min);
        double computeObjectiveValue2 = computeObjectiveValue(max);
        double d2 = 0.0d;
        if (computeObjectiveValue == 0.0d) {
            return min;
        }
        if (computeObjectiveValue2 == 0.0d) {
            return max;
        }
        verifyBracketing(min, max);
        double functionValueAccuracy = getFunctionValueAccuracy();
        double absoluteAccuracy = getAbsoluteAccuracy();
        double relativeAccuracy = getRelativeAccuracy();
        boolean z = false;
        while (true) {
            double d3 = min;
            d = max - (((max - min) * computeObjectiveValue2) / (computeObjectiveValue2 - computeObjectiveValue));
            double computeObjectiveValue3 = computeObjectiveValue(d);
            int i = (computeObjectiveValue3 > d2 ? 1 : (computeObjectiveValue3 == d2 ? 0 : -1));
            if (i == 0) {
                return d;
            }
            if (computeObjectiveValue2 * computeObjectiveValue3 < d2) {
                d3 = max;
                z = !z;
                computeObjectiveValue = computeObjectiveValue2;
            } else {
                int i2 = AnonymousClass1.$SwitchMap$org$apache$commons$math3$analysis$solvers$BaseSecantSolver$Method[this.method.ordinal()];
                if (i2 == 1) {
                    computeObjectiveValue *= 0.5d;
                } else if (i2 == 2) {
                    computeObjectiveValue *= computeObjectiveValue2 / (computeObjectiveValue2 + computeObjectiveValue3);
                } else if (i2 != 3) {
                    throw new MathInternalError();
                } else if (d == max) {
                    throw new ConvergenceException();
                }
            }
            if (FastMath.abs(computeObjectiveValue3) <= functionValueAccuracy) {
                int i3 = AnonymousClass1.$SwitchMap$org$apache$commons$math3$analysis$solvers$AllowedSolution[this.allowed.ordinal()];
                if (i3 != 1) {
                    if (i3 == 2) {
                        if (z) {
                            break;
                        }
                    } else if (i3 != 3) {
                        if (i3 != 4) {
                            if (i3 != 5) {
                                throw new MathInternalError();
                            } else if (i >= 0) {
                                return d;
                            }
                        } else if (computeObjectiveValue3 <= 0.0d) {
                            return d;
                        }
                    } else if (!z) {
                        return d;
                    }
                } else {
                    break;
                }
            }
            double d4 = computeObjectiveValue;
            if (FastMath.abs(d - d3) < FastMath.max(relativeAccuracy * FastMath.abs(d), absoluteAccuracy)) {
                int i4 = AnonymousClass1.$SwitchMap$org$apache$commons$math3$analysis$solvers$AllowedSolution[this.allowed.ordinal()];
                if (i4 == 1) {
                    return d;
                }
                if (i4 == 2) {
                    return z ? d : d3;
                }
                if (i4 == 3) {
                    return z ? d3 : d;
                }
                if (i4 == 4) {
                    return computeObjectiveValue3 <= 0.0d ? d : d3;
                }
                if (i4 == 5) {
                    return i >= 0 ? d : d3;
                }
                throw new MathInternalError();
            }
            max = d;
            computeObjectiveValue2 = computeObjectiveValue3;
            min = d3;
            computeObjectiveValue = d4;
            d2 = 0.0d;
        }
        return d;
    }

    /* renamed from: org.apache.commons.math3.analysis.solvers.BaseSecantSolver$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$math3$analysis$solvers$AllowedSolution;
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$math3$analysis$solvers$BaseSecantSolver$Method;

        /* JADX WARNING: Can't wrap try/catch for region: R(17:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|13|14|15|17|18|19|20|(3:21|22|24)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|(2:1|2)|3|5|6|7|9|10|11|12|13|14|15|17|18|19|20|(3:21|22|24)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(21:0|1|2|3|5|6|7|9|10|11|12|13|14|15|17|18|19|20|21|22|24) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0033 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x004f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0059 */
        static {
            /*
                org.apache.commons.math3.analysis.solvers.AllowedSolution[] r0 = org.apache.commons.math3.analysis.solvers.AllowedSolution.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$commons$math3$analysis$solvers$AllowedSolution = r0
                r1 = 1
                org.apache.commons.math3.analysis.solvers.AllowedSolution r2 = org.apache.commons.math3.analysis.solvers.AllowedSolution.ANY_SIDE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$org$apache$commons$math3$analysis$solvers$AllowedSolution     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.commons.math3.analysis.solvers.AllowedSolution r3 = org.apache.commons.math3.analysis.solvers.AllowedSolution.LEFT_SIDE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$org$apache$commons$math3$analysis$solvers$AllowedSolution     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.commons.math3.analysis.solvers.AllowedSolution r4 = org.apache.commons.math3.analysis.solvers.AllowedSolution.RIGHT_SIDE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r3 = $SwitchMap$org$apache$commons$math3$analysis$solvers$AllowedSolution     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.commons.math3.analysis.solvers.AllowedSolution r4 = org.apache.commons.math3.analysis.solvers.AllowedSolution.BELOW_SIDE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r5 = 4
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r3 = $SwitchMap$org$apache$commons$math3$analysis$solvers$AllowedSolution     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.commons.math3.analysis.solvers.AllowedSolution r4 = org.apache.commons.math3.analysis.solvers.AllowedSolution.ABOVE_SIDE     // Catch:{ NoSuchFieldError -> 0x003e }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r5 = 5
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                org.apache.commons.math3.analysis.solvers.BaseSecantSolver$Method[] r3 = org.apache.commons.math3.analysis.solvers.BaseSecantSolver.Method.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$org$apache$commons$math3$analysis$solvers$BaseSecantSolver$Method = r3
                org.apache.commons.math3.analysis.solvers.BaseSecantSolver$Method r4 = org.apache.commons.math3.analysis.solvers.BaseSecantSolver.Method.ILLINOIS     // Catch:{ NoSuchFieldError -> 0x004f }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x004f }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x004f }
            L_0x004f:
                int[] r1 = $SwitchMap$org$apache$commons$math3$analysis$solvers$BaseSecantSolver$Method     // Catch:{ NoSuchFieldError -> 0x0059 }
                org.apache.commons.math3.analysis.solvers.BaseSecantSolver$Method r3 = org.apache.commons.math3.analysis.solvers.BaseSecantSolver.Method.PEGASUS     // Catch:{ NoSuchFieldError -> 0x0059 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0059 }
                r1[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0059 }
            L_0x0059:
                int[] r0 = $SwitchMap$org$apache$commons$math3$analysis$solvers$BaseSecantSolver$Method     // Catch:{ NoSuchFieldError -> 0x0063 }
                org.apache.commons.math3.analysis.solvers.BaseSecantSolver$Method r1 = org.apache.commons.math3.analysis.solvers.BaseSecantSolver.Method.REGULA_FALSI     // Catch:{ NoSuchFieldError -> 0x0063 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0063 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0063 }
            L_0x0063:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.analysis.solvers.BaseSecantSolver.AnonymousClass1.<clinit>():void");
        }
    }
}
