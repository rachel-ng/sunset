package org.apache.commons.math3.optim.linear;

import java.util.ArrayList;
import org.apache.commons.math3.exception.TooManyIterationsException;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

public class SimplexSolver extends LinearOptimizer {
    static final double DEFAULT_CUT_OFF = 1.0E-10d;
    private static final double DEFAULT_EPSILON = 1.0E-6d;
    static final int DEFAULT_ULPS = 10;
    private final double cutOff;
    private final double epsilon;
    private final int maxUlps;
    private PivotSelectionRule pivotSelection;
    private SolutionCallback solutionCallback;

    public SimplexSolver() {
        this(1.0E-6d, 10, 1.0E-10d);
    }

    public SimplexSolver(double d) {
        this(d, 10, 1.0E-10d);
    }

    public SimplexSolver(double d, int i) {
        this(d, i, 1.0E-10d);
    }

    public SimplexSolver(double d, int i, double d2) {
        this.epsilon = d;
        this.maxUlps = i;
        this.cutOff = d2;
        this.pivotSelection = PivotSelectionRule.DANTZIG;
    }

    public PointValuePair optimize(OptimizationData... optimizationDataArr) throws TooManyIterationsException {
        return super.optimize(optimizationDataArr);
    }

    /* access modifiers changed from: protected */
    public void parseOptimizationData(OptimizationData... optimizationDataArr) {
        super.parseOptimizationData(optimizationDataArr);
        this.solutionCallback = null;
        for (SolutionCallback solutionCallback2 : optimizationDataArr) {
            if (solutionCallback2 instanceof SolutionCallback) {
                this.solutionCallback = solutionCallback2;
            } else if (solutionCallback2 instanceof PivotSelectionRule) {
                this.pivotSelection = (PivotSelectionRule) solutionCallback2;
            }
        }
    }

    private Integer getPivotColumn(SimplexTableau simplexTableau) {
        double d = 0.0d;
        Integer num = null;
        for (int numObjectiveFunctions = simplexTableau.getNumObjectiveFunctions(); numObjectiveFunctions < simplexTableau.getWidth() - 1; numObjectiveFunctions++) {
            double entry = simplexTableau.getEntry(0, numObjectiveFunctions);
            if (entry < d) {
                num = Integer.valueOf(numObjectiveFunctions);
                if (this.pivotSelection == PivotSelectionRule.BLAND && isValidPivotColumn(simplexTableau, numObjectiveFunctions)) {
                    break;
                }
                d = entry;
            }
        }
        return num;
    }

    private boolean isValidPivotColumn(SimplexTableau simplexTableau, int i) {
        for (int numObjectiveFunctions = simplexTableau.getNumObjectiveFunctions(); numObjectiveFunctions < simplexTableau.getHeight(); numObjectiveFunctions++) {
            if (Precision.compareTo(simplexTableau.getEntry(numObjectiveFunctions, i), 0.0d, this.cutOff) > 0) {
                return true;
            }
        }
        return false;
    }

    private Integer getPivotRow(SimplexTableau simplexTableau, int i) {
        SimplexTableau simplexTableau2 = simplexTableau;
        ArrayList<Integer> arrayList = new ArrayList<>();
        double d = Double.MAX_VALUE;
        for (int numObjectiveFunctions = simplexTableau.getNumObjectiveFunctions(); numObjectiveFunctions < simplexTableau.getHeight(); numObjectiveFunctions++) {
            double entry = simplexTableau2.getEntry(numObjectiveFunctions, simplexTableau.getWidth() - 1);
            double entry2 = simplexTableau2.getEntry(numObjectiveFunctions, i);
            if (Precision.compareTo(entry2, 0.0d, this.cutOff) > 0) {
                double abs = FastMath.abs(entry / entry2);
                int compare = Double.compare(abs, d);
                if (compare == 0) {
                    arrayList.add(Integer.valueOf(numObjectiveFunctions));
                } else if (compare < 0) {
                    arrayList.clear();
                    arrayList.add(Integer.valueOf(numObjectiveFunctions));
                    d = abs;
                }
            }
        }
        Integer num = null;
        if (arrayList.size() == 0) {
            return null;
        }
        if (arrayList.size() <= 1) {
            return (Integer) arrayList.get(0);
        }
        if (simplexTableau.getNumArtificialVariables() > 0) {
            for (Integer num2 : arrayList) {
                int i2 = 0;
                while (true) {
                    if (i2 < simplexTableau.getNumArtificialVariables()) {
                        int artificialVariableOffset = simplexTableau.getArtificialVariableOffset() + i2;
                        if (Precision.equals(simplexTableau2.getEntry(num2.intValue(), artificialVariableOffset), 1.0d, this.maxUlps) && num2.equals(simplexTableau2.getBasicRow(artificialVariableOffset))) {
                            return num2;
                        }
                        i2++;
                    }
                }
            }
        }
        int width = simplexTableau.getWidth();
        for (Integer num3 : arrayList) {
            int basicVariable = simplexTableau2.getBasicVariable(num3.intValue());
            if (basicVariable < width) {
                num = num3;
                width = basicVariable;
            }
        }
        return num;
    }

    /* access modifiers changed from: protected */
    public void doIteration(SimplexTableau simplexTableau) throws TooManyIterationsException, UnboundedSolutionException {
        incrementIterationCount();
        Integer pivotColumn = getPivotColumn(simplexTableau);
        Integer pivotRow = getPivotRow(simplexTableau, pivotColumn.intValue());
        if (pivotRow != null) {
            simplexTableau.performRowOperations(pivotColumn.intValue(), pivotRow.intValue());
            return;
        }
        throw new UnboundedSolutionException();
    }

    /* access modifiers changed from: protected */
    public void solvePhase1(SimplexTableau simplexTableau) throws TooManyIterationsException, UnboundedSolutionException, NoFeasibleSolutionException {
        if (simplexTableau.getNumArtificialVariables() != 0) {
            while (!simplexTableau.isOptimal()) {
                doIteration(simplexTableau);
            }
            if (!Precision.equals(simplexTableau.getEntry(0, simplexTableau.getRhsOffset()), 0.0d, this.epsilon)) {
                throw new NoFeasibleSolutionException();
            }
        }
    }

    public PointValuePair doOptimize() throws TooManyIterationsException, UnboundedSolutionException, NoFeasibleSolutionException {
        SolutionCallback solutionCallback2 = this.solutionCallback;
        if (solutionCallback2 != null) {
            solutionCallback2.setTableau((SimplexTableau) null);
        }
        SimplexTableau simplexTableau = new SimplexTableau(getFunction(), getConstraints(), getGoalType(), isRestrictedToNonNegative(), this.epsilon, this.maxUlps);
        solvePhase1(simplexTableau);
        simplexTableau.dropPhase1Objective();
        SolutionCallback solutionCallback3 = this.solutionCallback;
        if (solutionCallback3 != null) {
            solutionCallback3.setTableau(simplexTableau);
        }
        while (!simplexTableau.isOptimal()) {
            doIteration(simplexTableau);
        }
        PointValuePair solution = simplexTableau.getSolution();
        if (isRestrictedToNonNegative()) {
            double[] point = solution.getPoint();
            int i = 0;
            while (i < point.length) {
                if (Precision.compareTo(point[i], 0.0d, this.epsilon) >= 0) {
                    i++;
                } else {
                    throw new NoFeasibleSolutionException();
                }
            }
        }
        return solution;
    }
}
