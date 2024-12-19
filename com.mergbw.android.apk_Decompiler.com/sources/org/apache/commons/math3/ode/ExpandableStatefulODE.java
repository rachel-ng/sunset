package org.apache.commons.math3.ode;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;

public class ExpandableStatefulODE {
    private List<SecondaryComponent> components;
    private final FirstOrderDifferentialEquations primary;
    private final EquationsMapper primaryMapper;
    private final double[] primaryState;
    private final double[] primaryStateDot;
    private double time = Double.NaN;

    public ExpandableStatefulODE(FirstOrderDifferentialEquations firstOrderDifferentialEquations) {
        int dimension = firstOrderDifferentialEquations.getDimension();
        this.primary = firstOrderDifferentialEquations;
        this.primaryMapper = new EquationsMapper(0, dimension);
        this.primaryState = new double[dimension];
        this.primaryStateDot = new double[dimension];
        this.components = new ArrayList();
    }

    public FirstOrderDifferentialEquations getPrimary() {
        return this.primary;
    }

    public int getTotalDimension() {
        if (this.components.isEmpty()) {
            return this.primaryMapper.getDimension();
        }
        List<SecondaryComponent> list = this.components;
        EquationsMapper access$000 = list.get(list.size() - 1).mapper;
        return access$000.getFirstIndex() + access$000.getDimension();
    }

    public void computeDerivatives(double d, double[] dArr, double[] dArr2) throws MaxCountExceededException, DimensionMismatchException {
        this.primaryMapper.extractEquationData(dArr, this.primaryState);
        this.primary.computeDerivatives(d, this.primaryState, this.primaryStateDot);
        for (SecondaryComponent next : this.components) {
            next.mapper.extractEquationData(dArr, next.state);
            next.equation.computeDerivatives(d, this.primaryState, this.primaryStateDot, next.state, next.stateDot);
            next.mapper.insertEquationData(next.stateDot, dArr2);
        }
        this.primaryMapper.insertEquationData(this.primaryStateDot, dArr2);
    }

    public int addSecondaryEquations(SecondaryEquations secondaryEquations) {
        int i;
        if (this.components.isEmpty()) {
            this.components = new ArrayList();
            i = this.primary.getDimension();
        } else {
            List<SecondaryComponent> list = this.components;
            SecondaryComponent secondaryComponent = list.get(list.size() - 1);
            i = secondaryComponent.mapper.getDimension() + secondaryComponent.mapper.getFirstIndex();
        }
        this.components.add(new SecondaryComponent(secondaryEquations, i));
        return this.components.size() - 1;
    }

    public EquationsMapper getPrimaryMapper() {
        return this.primaryMapper;
    }

    public EquationsMapper[] getSecondaryMappers() {
        int size = this.components.size();
        EquationsMapper[] equationsMapperArr = new EquationsMapper[size];
        for (int i = 0; i < size; i++) {
            equationsMapperArr[i] = this.components.get(i).mapper;
        }
        return equationsMapperArr;
    }

    public void setTime(double d) {
        this.time = d;
    }

    public double getTime() {
        return this.time;
    }

    public void setPrimaryState(double[] dArr) throws DimensionMismatchException {
        int length = dArr.length;
        double[] dArr2 = this.primaryState;
        if (length == dArr2.length) {
            System.arraycopy(dArr, 0, dArr2, 0, dArr.length);
            return;
        }
        throw new DimensionMismatchException(dArr.length, this.primaryState.length);
    }

    public double[] getPrimaryState() {
        return (double[]) this.primaryState.clone();
    }

    public double[] getPrimaryStateDot() {
        return (double[]) this.primaryStateDot.clone();
    }

    public void setSecondaryState(int i, double[] dArr) throws DimensionMismatchException {
        double[] access$100 = this.components.get(i).state;
        if (dArr.length == access$100.length) {
            System.arraycopy(dArr, 0, access$100, 0, dArr.length);
            return;
        }
        throw new DimensionMismatchException(dArr.length, access$100.length);
    }

    public double[] getSecondaryState(int i) {
        return (double[]) this.components.get(i).state.clone();
    }

    public double[] getSecondaryStateDot(int i) {
        return (double[]) this.components.get(i).stateDot.clone();
    }

    public void setCompleteState(double[] dArr) throws DimensionMismatchException {
        if (dArr.length == getTotalDimension()) {
            this.primaryMapper.extractEquationData(dArr, this.primaryState);
            for (SecondaryComponent next : this.components) {
                next.mapper.extractEquationData(dArr, next.state);
            }
            return;
        }
        throw new DimensionMismatchException(dArr.length, getTotalDimension());
    }

    public double[] getCompleteState() throws DimensionMismatchException {
        double[] dArr = new double[getTotalDimension()];
        this.primaryMapper.insertEquationData(this.primaryState, dArr);
        for (SecondaryComponent next : this.components) {
            next.mapper.insertEquationData(next.state, dArr);
        }
        return dArr;
    }

    private static class SecondaryComponent {
        /* access modifiers changed from: private */
        public final SecondaryEquations equation;
        /* access modifiers changed from: private */
        public final EquationsMapper mapper;
        /* access modifiers changed from: private */
        public final double[] state;
        /* access modifiers changed from: private */
        public final double[] stateDot;

        public SecondaryComponent(SecondaryEquations secondaryEquations, int i) {
            int dimension = secondaryEquations.getDimension();
            this.equation = secondaryEquations;
            this.mapper = new EquationsMapper(i, dimension);
            this.state = new double[dimension];
            this.stateDot = new double[dimension];
        }
    }
}
