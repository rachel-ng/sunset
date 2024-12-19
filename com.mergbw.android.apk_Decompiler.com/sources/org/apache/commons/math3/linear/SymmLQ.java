package org.apache.commons.math3.linear;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.ExceptionContext;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.IterationManager;
import org.apache.commons.math3.util.MathUtils;

public class SymmLQ extends PreconditionedIterativeLinearSolver {
    private static final String OPERATOR = "operator";
    private static final String THRESHOLD = "threshold";
    private static final String VECTOR = "vector";
    private static final String VECTOR1 = "vector1";
    private static final String VECTOR2 = "vector2";
    private final boolean check;
    private final double delta;

    private static class State {
        static final double CBRT_MACH_PREC;
        static final double MACH_PREC;

        /* renamed from: a  reason: collision with root package name */
        private final RealLinearOperator f76a;

        /* renamed from: b  reason: collision with root package name */
        private final RealVector f77b;
        private boolean bIsNull;
        private double beta;
        private double beta1;
        private double bstep;
        private double cgnorm;
        private final boolean check;
        private double dbar;
        private final double delta;
        private double gammaZeta;
        private double gbar;
        private double gmax;
        private double gmin;
        private final boolean goodb;
        private boolean hasConverged;
        private double lqnorm;
        private final RealLinearOperator m;
        private final RealVector mb;
        private double minusEpsZeta;
        private double oldb;
        private RealVector r1;
        private RealVector r2;
        private double rnorm;
        private final double shift;
        private double snprod;
        private double tnorm;
        private RealVector wbar;
        private final RealVector xL;
        private RealVector y;
        private double ynorm2;

        static {
            double ulp = FastMath.ulp(1.0d);
            MACH_PREC = ulp;
            CBRT_MACH_PREC = FastMath.cbrt(ulp);
        }

        public State(RealLinearOperator realLinearOperator, RealLinearOperator realLinearOperator2, RealVector realVector, boolean z, double d, double d2, boolean z2) {
            this.f76a = realLinearOperator;
            this.m = realLinearOperator2;
            this.f77b = realVector;
            this.xL = new ArrayRealVector(realVector.getDimension());
            this.goodb = z;
            this.shift = d;
            this.mb = realLinearOperator2 != null ? realLinearOperator2.operate(realVector) : realVector;
            this.hasConverged = false;
            this.check = z2;
            this.delta = d2;
        }

        private static void checkSymmetry(RealLinearOperator realLinearOperator, RealVector realVector, RealVector realVector2, RealVector realVector3) throws NonSelfAdjointOperatorException {
            double dotProduct = realVector2.dotProduct(realVector2);
            double dotProduct2 = realVector.dotProduct(realVector3);
            double d = (MACH_PREC + dotProduct) * CBRT_MACH_PREC;
            if (FastMath.abs(dotProduct - dotProduct2) > d) {
                NonSelfAdjointOperatorException nonSelfAdjointOperatorException = new NonSelfAdjointOperatorException();
                ExceptionContext context = nonSelfAdjointOperatorException.getContext();
                context.setValue("operator", realLinearOperator);
                context.setValue(SymmLQ.VECTOR1, realVector);
                context.setValue(SymmLQ.VECTOR2, realVector2);
                context.setValue(SymmLQ.THRESHOLD, Double.valueOf(d));
                throw nonSelfAdjointOperatorException;
            }
        }

        private static void throwNPDLOException(RealLinearOperator realLinearOperator, RealVector realVector) throws NonPositiveDefiniteOperatorException {
            NonPositiveDefiniteOperatorException nonPositiveDefiniteOperatorException = new NonPositiveDefiniteOperatorException();
            ExceptionContext context = nonPositiveDefiniteOperatorException.getContext();
            context.setValue("operator", realLinearOperator);
            context.setValue("vector", realVector);
            throw nonPositiveDefiniteOperatorException;
        }

        private static void daxpy(double d, RealVector realVector, RealVector realVector2) {
            int dimension = realVector.getDimension();
            for (int i = 0; i < dimension; i++) {
                realVector2.setEntry(i, (realVector.getEntry(i) * d) + realVector2.getEntry(i));
            }
        }

        private static void daxpbypz(double d, RealVector realVector, double d2, RealVector realVector2, RealVector realVector3) {
            int dimension = realVector3.getDimension();
            for (int i = 0; i < dimension; i++) {
                realVector3.setEntry(i, (realVector.getEntry(i) * d) + (realVector2.getEntry(i) * d2) + realVector3.getEntry(i));
            }
        }

        /* access modifiers changed from: package-private */
        public void refineSolution(RealVector realVector) {
            int dimension = this.xL.getDimension();
            int i = 0;
            if (this.lqnorm >= this.cgnorm) {
                double sqrt = FastMath.sqrt(this.tnorm);
                double d = this.gbar;
                if (d == 0.0d) {
                    d = MACH_PREC * sqrt;
                }
                double d2 = this.gammaZeta / d;
                double d3 = (this.bstep + (this.snprod * d2)) / this.beta1;
                if (!this.goodb) {
                    while (i < dimension) {
                        realVector.setEntry(i, this.xL.getEntry(i) + (this.wbar.getEntry(i) * d2));
                        i++;
                    }
                    return;
                }
                while (i < dimension) {
                    realVector.setEntry(i, this.xL.getEntry(i) + (this.wbar.getEntry(i) * d2) + (this.mb.getEntry(i) * d3));
                    i++;
                }
            } else if (!this.goodb) {
                realVector.setSubVector(0, this.xL);
            } else {
                double d4 = this.bstep / this.beta1;
                while (i < dimension) {
                    realVector.setEntry(i, this.xL.getEntry(i) + (this.mb.getEntry(i) * d4));
                    i++;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void init() {
            this.xL.set(0.0d);
            RealVector copy = this.f77b.copy();
            this.r1 = copy;
            RealLinearOperator realLinearOperator = this.m;
            RealVector copy2 = realLinearOperator == null ? this.f77b.copy() : realLinearOperator.operate(copy);
            this.y = copy2;
            RealLinearOperator realLinearOperator2 = this.m;
            if (realLinearOperator2 != null && this.check) {
                checkSymmetry(realLinearOperator2, this.r1, copy2, realLinearOperator2.operate(copy2));
            }
            double dotProduct = this.r1.dotProduct(this.y);
            this.beta1 = dotProduct;
            if (dotProduct < 0.0d) {
                throwNPDLOException(this.m, this.y);
            }
            double d = this.beta1;
            if (d == 0.0d) {
                this.bIsNull = true;
                return;
            }
            this.bIsNull = false;
            double sqrt = FastMath.sqrt(d);
            this.beta1 = sqrt;
            RealVector mapMultiply = this.y.mapMultiply(1.0d / sqrt);
            RealVector operate = this.f76a.operate(mapMultiply);
            this.y = operate;
            if (this.check) {
                RealLinearOperator realLinearOperator3 = this.f76a;
                checkSymmetry(realLinearOperator3, mapMultiply, operate, realLinearOperator3.operate(operate));
            }
            daxpy(-this.shift, mapMultiply, this.y);
            double dotProduct2 = mapMultiply.dotProduct(this.y);
            daxpy((-dotProduct2) / this.beta1, this.r1, this.y);
            daxpy((-mapMultiply.dotProduct(this.y)) / mapMultiply.dotProduct(mapMultiply), mapMultiply, this.y);
            RealVector copy3 = this.y.copy();
            this.r2 = copy3;
            RealLinearOperator realLinearOperator4 = this.m;
            if (realLinearOperator4 != null) {
                this.y = realLinearOperator4.operate(copy3);
            }
            this.oldb = this.beta1;
            double dotProduct3 = this.r2.dotProduct(this.y);
            this.beta = dotProduct3;
            if (dotProduct3 < 0.0d) {
                throwNPDLOException(this.m, this.y);
            }
            double sqrt2 = FastMath.sqrt(this.beta);
            this.beta = sqrt2;
            double d2 = this.beta1;
            this.cgnorm = d2;
            this.gbar = dotProduct2;
            this.dbar = sqrt2;
            this.gammaZeta = d2;
            this.minusEpsZeta = 0.0d;
            this.bstep = 0.0d;
            this.snprod = 1.0d;
            this.tnorm = (dotProduct2 * dotProduct2) + (sqrt2 * sqrt2);
            this.ynorm2 = 0.0d;
            double abs = FastMath.abs(dotProduct2) + MACH_PREC;
            this.gmax = abs;
            this.gmin = abs;
            if (this.goodb) {
                ArrayRealVector arrayRealVector = new ArrayRealVector(this.f76a.getRowDimension());
                this.wbar = arrayRealVector;
                arrayRealVector.set(0.0d);
            } else {
                this.wbar = mapMultiply;
            }
            updateNorms();
        }

        /* access modifiers changed from: package-private */
        public void update() {
            RealVector mapMultiply = this.y.mapMultiply(1.0d / this.beta);
            RealVector operate = this.f76a.operate(mapMultiply);
            this.y = operate;
            daxpbypz(-this.shift, mapMultiply, (-this.beta) / this.oldb, this.r1, operate);
            double dotProduct = mapMultiply.dotProduct(this.y);
            daxpy((-dotProduct) / this.beta, this.r2, this.y);
            this.r1 = this.r2;
            RealVector realVector = this.y;
            this.r2 = realVector;
            RealLinearOperator realLinearOperator = this.m;
            if (realLinearOperator != null) {
                this.y = realLinearOperator.operate(realVector);
            }
            this.oldb = this.beta;
            double dotProduct2 = this.r2.dotProduct(this.y);
            this.beta = dotProduct2;
            if (dotProduct2 < 0.0d) {
                throwNPDLOException(this.m, this.y);
            }
            double sqrt = FastMath.sqrt(this.beta);
            this.beta = sqrt;
            double d = this.tnorm;
            double d2 = this.oldb;
            this.tnorm = d + (dotProduct * dotProduct) + (d2 * d2) + (sqrt * sqrt);
            double d3 = this.gbar;
            double sqrt2 = FastMath.sqrt((d3 * d3) + (d2 * d2));
            double d4 = this.gbar / sqrt2;
            double d5 = this.oldb / sqrt2;
            double d6 = this.dbar;
            double d7 = (d4 * d6) + (d5 * dotProduct);
            this.gbar = (d6 * d5) - (dotProduct * d4);
            double d8 = this.beta;
            double d9 = d5 * d8;
            this.dbar = (-d4) * d8;
            double d10 = this.gammaZeta / sqrt2;
            double d11 = d10 * d4;
            double d12 = d10 * d5;
            double d13 = d9;
            int dimension = this.xL.getDimension();
            int i = 0;
            while (i < dimension) {
                double entry = this.xL.getEntry(i);
                double entry2 = mapMultiply.getEntry(i);
                double entry3 = this.wbar.getEntry(i);
                this.xL.setEntry(i, entry + (entry3 * d11) + (entry2 * d12));
                this.wbar.setEntry(i, (entry3 * d5) - (entry2 * d4));
                i++;
                dimension = dimension;
                d11 = d11;
            }
            double d14 = this.bstep;
            double d15 = this.snprod;
            this.bstep = d14 + (d4 * d15 * d10);
            this.snprod = d15 * d5;
            this.gmax = FastMath.max(this.gmax, sqrt2);
            this.gmin = FastMath.min(this.gmin, sqrt2);
            this.ynorm2 += d10 * d10;
            this.gammaZeta = this.minusEpsZeta - (d7 * d10);
            this.minusEpsZeta = (-d13) * d10;
            updateNorms();
        }

        private void updateNorms() {
            double d;
            double sqrt = FastMath.sqrt(this.tnorm);
            double sqrt2 = FastMath.sqrt(this.ynorm2);
            double d2 = MACH_PREC;
            double d3 = sqrt * d2;
            double d4 = sqrt * sqrt2;
            double d5 = d4 * d2;
            double d6 = d4 * this.delta;
            double d7 = this.gbar;
            if (d7 != 0.0d) {
                d3 = d7;
            }
            double d8 = this.gammaZeta;
            double d9 = this.minusEpsZeta;
            this.lqnorm = FastMath.sqrt((d8 * d8) + (d9 * d9));
            double abs = ((this.snprod * this.beta1) * this.beta) / FastMath.abs(d3);
            this.cgnorm = abs;
            if (this.lqnorm <= abs) {
                d = this.gmax / this.gmin;
            } else {
                d = this.gmax / FastMath.min(this.gmin, FastMath.abs(d3));
            }
            if (d2 * d >= 0.1d) {
                throw new IllConditionedOperatorException(d);
            } else if (this.beta1 > d5) {
                this.rnorm = FastMath.min(this.cgnorm, this.lqnorm);
                double d10 = this.cgnorm;
                this.hasConverged = d10 <= d5 || d10 <= d6;
            } else {
                throw new SingularOperatorException();
            }
        }

        /* access modifiers changed from: package-private */
        public boolean hasConverged() {
            return this.hasConverged;
        }

        /* access modifiers changed from: package-private */
        public boolean bEqualsNullVector() {
            return this.bIsNull;
        }

        /* access modifiers changed from: package-private */
        public boolean betaEqualsZero() {
            return this.beta < MACH_PREC;
        }

        /* access modifiers changed from: package-private */
        public double getNormOfResidual() {
            return this.rnorm;
        }
    }

    public SymmLQ(int i, double d, boolean z) {
        super(i);
        this.delta = d;
        this.check = z;
    }

    public SymmLQ(IterationManager iterationManager, double d, boolean z) {
        super(iterationManager);
        this.delta = d;
        this.check = z;
    }

    public final boolean getCheck() {
        return this.check;
    }

    public RealVector solve(RealLinearOperator realLinearOperator, RealLinearOperator realLinearOperator2, RealVector realVector) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, MaxCountExceededException, NonSelfAdjointOperatorException, NonPositiveDefiniteOperatorException, IllConditionedOperatorException {
        MathUtils.checkNotNull(realLinearOperator);
        return solveInPlace(realLinearOperator, realLinearOperator2, realVector, new ArrayRealVector(realLinearOperator.getColumnDimension()), false, 0.0d);
    }

    public RealVector solve(RealLinearOperator realLinearOperator, RealLinearOperator realLinearOperator2, RealVector realVector, boolean z, double d) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, MaxCountExceededException, NonSelfAdjointOperatorException, NonPositiveDefiniteOperatorException, IllConditionedOperatorException {
        MathUtils.checkNotNull(realLinearOperator);
        return solveInPlace(realLinearOperator, realLinearOperator2, realVector, new ArrayRealVector(realLinearOperator.getColumnDimension()), z, d);
    }

    public RealVector solve(RealLinearOperator realLinearOperator, RealLinearOperator realLinearOperator2, RealVector realVector, RealVector realVector2) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, NonSelfAdjointOperatorException, NonPositiveDefiniteOperatorException, IllConditionedOperatorException, MaxCountExceededException {
        MathUtils.checkNotNull(realVector2);
        return solveInPlace(realLinearOperator, realLinearOperator2, realVector, realVector2.copy(), false, 0.0d);
    }

    public RealVector solve(RealLinearOperator realLinearOperator, RealVector realVector) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, NonSelfAdjointOperatorException, IllConditionedOperatorException, MaxCountExceededException {
        MathUtils.checkNotNull(realLinearOperator);
        ArrayRealVector arrayRealVector = new ArrayRealVector(realLinearOperator.getColumnDimension());
        arrayRealVector.set(0.0d);
        return solveInPlace(realLinearOperator, (RealLinearOperator) null, realVector, arrayRealVector, false, 0.0d);
    }

    public RealVector solve(RealLinearOperator realLinearOperator, RealVector realVector, boolean z, double d) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, NonSelfAdjointOperatorException, IllConditionedOperatorException, MaxCountExceededException {
        MathUtils.checkNotNull(realLinearOperator);
        return solveInPlace(realLinearOperator, (RealLinearOperator) null, realVector, new ArrayRealVector(realLinearOperator.getColumnDimension()), z, d);
    }

    public RealVector solve(RealLinearOperator realLinearOperator, RealVector realVector, RealVector realVector2) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, NonSelfAdjointOperatorException, IllConditionedOperatorException, MaxCountExceededException {
        MathUtils.checkNotNull(realVector2);
        return solveInPlace(realLinearOperator, (RealLinearOperator) null, realVector, realVector2.copy(), false, 0.0d);
    }

    public RealVector solveInPlace(RealLinearOperator realLinearOperator, RealLinearOperator realLinearOperator2, RealVector realVector, RealVector realVector2) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, NonSelfAdjointOperatorException, NonPositiveDefiniteOperatorException, IllConditionedOperatorException, MaxCountExceededException {
        return solveInPlace(realLinearOperator, realLinearOperator2, realVector, realVector2, false, 0.0d);
    }

    public RealVector solveInPlace(RealLinearOperator realLinearOperator, RealLinearOperator realLinearOperator2, RealVector realVector, RealVector realVector2, boolean z, double d) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, NonSelfAdjointOperatorException, NonPositiveDefiniteOperatorException, IllConditionedOperatorException, MaxCountExceededException {
        RealVector realVector3 = realVector2;
        checkParameters(realLinearOperator, realLinearOperator2, realVector, realVector2);
        IterationManager iterationManager = getIterationManager();
        iterationManager.resetIterationCount();
        iterationManager.incrementIterationCount();
        State state = new State(realLinearOperator, realLinearOperator2, realVector, z, d, this.delta, this.check);
        state.init();
        state.refineSolution(realVector3);
        State state2 = state;
        DefaultIterativeLinearSolverEvent defaultIterativeLinearSolverEvent = new DefaultIterativeLinearSolverEvent(this, iterationManager.getIterations(), realVector2, realVector, state.getNormOfResidual());
        if (state2.bEqualsNullVector()) {
            iterationManager.fireTerminationEvent(defaultIterativeLinearSolverEvent);
            return realVector3;
        }
        boolean z2 = state2.betaEqualsZero() || state2.hasConverged();
        iterationManager.fireInitializationEvent(defaultIterativeLinearSolverEvent);
        if (!z2) {
            do {
                iterationManager.incrementIterationCount();
                RealVector realVector4 = realVector2;
                RealVector realVector5 = realVector;
                iterationManager.fireIterationStartedEvent(new DefaultIterativeLinearSolverEvent(this, iterationManager.getIterations(), realVector4, realVector5, state2.getNormOfResidual()));
                state2.update();
                state2.refineSolution(realVector3);
                iterationManager.fireIterationPerformedEvent(new DefaultIterativeLinearSolverEvent(this, iterationManager.getIterations(), realVector4, realVector5, state2.getNormOfResidual()));
            } while (!state2.hasConverged());
        }
        iterationManager.fireTerminationEvent(new DefaultIterativeLinearSolverEvent(this, iterationManager.getIterations(), realVector2, realVector, state2.getNormOfResidual()));
        return realVector3;
    }

    public RealVector solveInPlace(RealLinearOperator realLinearOperator, RealVector realVector, RealVector realVector2) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, NonSelfAdjointOperatorException, IllConditionedOperatorException, MaxCountExceededException {
        return solveInPlace(realLinearOperator, (RealLinearOperator) null, realVector, realVector2, false, 0.0d);
    }
}
