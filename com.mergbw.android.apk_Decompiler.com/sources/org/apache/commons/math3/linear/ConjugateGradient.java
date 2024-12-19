package org.apache.commons.math3.linear;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.ExceptionContext;
import org.apache.commons.math3.util.IterationManager;

public class ConjugateGradient extends PreconditionedIterativeLinearSolver {
    public static final String OPERATOR = "operator";
    public static final String VECTOR = "vector";
    private boolean check;
    private final double delta;

    public ConjugateGradient(int i, double d, boolean z) {
        super(i);
        this.delta = d;
        this.check = z;
    }

    public ConjugateGradient(IterationManager iterationManager, double d, boolean z) throws NullArgumentException {
        super(iterationManager);
        this.delta = d;
        this.check = z;
    }

    public final boolean getCheck() {
        return this.check;
    }

    public RealVector solveInPlace(RealLinearOperator realLinearOperator, RealLinearOperator realLinearOperator2, RealVector realVector, RealVector realVector2) throws NullArgumentException, NonPositiveDefiniteOperatorException, NonSquareOperatorException, DimensionMismatchException, MaxCountExceededException {
        double d;
        RealLinearOperator realLinearOperator3;
        String str;
        RealVector realVector3;
        RealLinearOperator realLinearOperator4 = realLinearOperator2;
        checkParameters(realLinearOperator, realLinearOperator2, realVector, realVector2);
        IterationManager iterationManager = getIterationManager();
        iterationManager.resetIterationCount();
        double norm = this.delta * realVector.getNorm();
        RealVector unmodifiableRealVector = RealVector.unmodifiableRealVector(realVector);
        iterationManager.incrementIterationCount();
        RealVector unmodifiableRealVector2 = RealVector.unmodifiableRealVector(realVector2);
        RealVector copy = realVector2.copy();
        RealVector combine = realVector.combine(1.0d, -1.0d, realLinearOperator.operate(copy));
        RealVector unmodifiableRealVector3 = RealVector.unmodifiableRealVector(combine);
        double norm2 = combine.getNorm();
        RealVector realVector4 = realLinearOperator4 == null ? combine : null;
        DefaultIterativeLinearSolverEvent defaultIterativeLinearSolverEvent = r0;
        RealVector realVector5 = copy;
        RealVector realVector6 = combine;
        DefaultIterativeLinearSolverEvent defaultIterativeLinearSolverEvent2 = new DefaultIterativeLinearSolverEvent(this, iterationManager.getIterations(), unmodifiableRealVector2, unmodifiableRealVector, unmodifiableRealVector3, norm2);
        iterationManager.fireInitializationEvent(defaultIterativeLinearSolverEvent);
        if (norm2 <= norm) {
            iterationManager.fireTerminationEvent(defaultIterativeLinearSolverEvent);
            return realVector2;
        }
        double d2 = norm2;
        double d3 = 0.0d;
        while (true) {
            iterationManager.incrementIterationCount();
            iterationManager.fireIterationStartedEvent(new DefaultIterativeLinearSolverEvent(this, iterationManager.getIterations(), unmodifiableRealVector2, unmodifiableRealVector, unmodifiableRealVector3, d2));
            RealVector operate = realLinearOperator4 != null ? realLinearOperator4.operate(realVector6) : realVector4;
            double dotProduct = realVector6.dotProduct(operate);
            RealVector realVector7 = realVector6;
            if (!this.check || dotProduct > 0.0d) {
                if (iterationManager.getIterations() == 2) {
                    RealVector realVector8 = realVector5;
                    realVector8.setSubVector(0, operate);
                    realVector3 = realVector8;
                    d = norm;
                    realLinearOperator3 = realLinearOperator;
                    str = OPERATOR;
                } else {
                    RealVector realVector9 = realVector5;
                    realVector3 = realVector9;
                    d = norm;
                    str = OPERATOR;
                    realVector9.combineToSelf(dotProduct / d3, 1.0d, operate);
                    realLinearOperator3 = realLinearOperator;
                }
                RealVector operate2 = realLinearOperator3.operate(realVector3);
                double dotProduct2 = realVector3.dotProduct(operate2);
                if (!this.check || dotProduct2 > 0.0d) {
                    double d4 = dotProduct / dotProduct2;
                    realVector2.combineToSelf(1.0d, d4, realVector3);
                    realVector7.combineToSelf(1.0d, -d4, operate2);
                    double norm3 = realVector7.getNorm();
                    RealVector realVector10 = realVector7;
                    DefaultIterativeLinearSolverEvent defaultIterativeLinearSolverEvent3 = new DefaultIterativeLinearSolverEvent(this, iterationManager.getIterations(), unmodifiableRealVector2, unmodifiableRealVector, unmodifiableRealVector3, norm3);
                    iterationManager.fireIterationPerformedEvent(defaultIterativeLinearSolverEvent3);
                    if (norm3 <= d) {
                        iterationManager.fireTerminationEvent(defaultIterativeLinearSolverEvent3);
                        return realVector2;
                    }
                    realVector5 = realVector3;
                    d2 = norm3;
                    realVector6 = realVector10;
                    d3 = dotProduct;
                    norm = d;
                    realLinearOperator4 = realLinearOperator2;
                    realVector4 = operate;
                } else {
                    NonPositiveDefiniteOperatorException nonPositiveDefiniteOperatorException = new NonPositiveDefiniteOperatorException();
                    ExceptionContext context = nonPositiveDefiniteOperatorException.getContext();
                    context.setValue(str, realLinearOperator3);
                    context.setValue(VECTOR, realVector3);
                    throw nonPositiveDefiniteOperatorException;
                }
            } else {
                NonPositiveDefiniteOperatorException nonPositiveDefiniteOperatorException2 = new NonPositiveDefiniteOperatorException();
                ExceptionContext context2 = nonPositiveDefiniteOperatorException2.getContext();
                context2.setValue(OPERATOR, realLinearOperator4);
                context2.setValue(VECTOR, realVector7);
                throw nonPositiveDefiniteOperatorException2;
            }
        }
    }
}
