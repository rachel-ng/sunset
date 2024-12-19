package org.apache.commons.math3.ode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class JacobianMatrices {
    /* access modifiers changed from: private */
    public boolean dirtyParameter;
    private ExpandableStatefulODE efode;
    private int index;
    /* access modifiers changed from: private */
    public List<ParameterJacobianProvider> jacobianProviders;
    /* access modifiers changed from: private */
    public MainStateJacobianProvider jode;
    private double[] matricesData;
    /* access modifiers changed from: private */
    public int paramDim;
    /* access modifiers changed from: private */
    public ParameterizedODE pode;
    /* access modifiers changed from: private */
    public ParameterConfiguration[] selectedParameters;
    /* access modifiers changed from: private */
    public int stateDim;

    public JacobianMatrices(FirstOrderDifferentialEquations firstOrderDifferentialEquations, double[] dArr, String... strArr) throws DimensionMismatchException {
        this(new MainStateJacobianWrapper(firstOrderDifferentialEquations, dArr), strArr);
    }

    public JacobianMatrices(MainStateJacobianProvider mainStateJacobianProvider, String... strArr) {
        this.efode = null;
        this.index = -1;
        this.jode = mainStateJacobianProvider;
        this.pode = null;
        this.stateDim = mainStateJacobianProvider.getDimension();
        int i = 0;
        if (strArr == null) {
            this.selectedParameters = null;
            this.paramDim = 0;
        } else {
            this.selectedParameters = new ParameterConfiguration[strArr.length];
            for (int i2 = 0; i2 < strArr.length; i2++) {
                this.selectedParameters[i2] = new ParameterConfiguration(strArr[i2], Double.NaN);
            }
            this.paramDim = strArr.length;
        }
        this.dirtyParameter = false;
        this.jacobianProviders = new ArrayList();
        int i3 = this.stateDim;
        this.matricesData = new double[((this.paramDim + i3) * i3)];
        while (true) {
            int i4 = this.stateDim;
            if (i < i4) {
                this.matricesData[(i4 + 1) * i] = 1.0d;
                i++;
            } else {
                return;
            }
        }
    }

    public void registerVariationalEquations(ExpandableStatefulODE expandableStatefulODE) throws DimensionMismatchException, MismatchedEquations {
        FirstOrderDifferentialEquations firstOrderDifferentialEquations = this.jode;
        if (firstOrderDifferentialEquations instanceof MainStateJacobianWrapper) {
            firstOrderDifferentialEquations = ((MainStateJacobianWrapper) firstOrderDifferentialEquations).ode;
        }
        if (expandableStatefulODE.getPrimary() == firstOrderDifferentialEquations) {
            this.efode = expandableStatefulODE;
            int addSecondaryEquations = expandableStatefulODE.addSecondaryEquations(new JacobiansSecondaryEquations());
            this.index = addSecondaryEquations;
            this.efode.setSecondaryState(addSecondaryEquations, this.matricesData);
            return;
        }
        throw new MismatchedEquations();
    }

    public void addParameterJacobianProvider(ParameterJacobianProvider parameterJacobianProvider) {
        this.jacobianProviders.add(parameterJacobianProvider);
    }

    public void setParameterizedODE(ParameterizedODE parameterizedODE) {
        this.pode = parameterizedODE;
        this.dirtyParameter = true;
    }

    public void setParameterStep(String str, double d) throws UnknownParameterException {
        for (ParameterConfiguration parameterConfiguration : this.selectedParameters) {
            if (str.equals(parameterConfiguration.getParameterName())) {
                parameterConfiguration.setHP(d);
                this.dirtyParameter = true;
                return;
            }
        }
        throw new UnknownParameterException(str);
    }

    public void setInitialMainStateJacobian(double[][] dArr) throws DimensionMismatchException {
        checkDimension(this.stateDim, dArr);
        checkDimension(this.stateDim, dArr[0]);
        int i = 0;
        for (double[] arraycopy : dArr) {
            System.arraycopy(arraycopy, 0, this.matricesData, i, this.stateDim);
            i += this.stateDim;
        }
        ExpandableStatefulODE expandableStatefulODE = this.efode;
        if (expandableStatefulODE != null) {
            expandableStatefulODE.setSecondaryState(this.index, this.matricesData);
        }
    }

    public void setInitialParameterJacobian(String str, double[] dArr) throws UnknownParameterException, DimensionMismatchException {
        checkDimension(this.stateDim, dArr);
        int i = this.stateDim;
        int i2 = i * i;
        for (ParameterConfiguration parameterName : this.selectedParameters) {
            if (str.equals(parameterName.getParameterName())) {
                System.arraycopy(dArr, 0, this.matricesData, i2, this.stateDim);
                ExpandableStatefulODE expandableStatefulODE = this.efode;
                if (expandableStatefulODE != null) {
                    expandableStatefulODE.setSecondaryState(this.index, this.matricesData);
                    return;
                }
                return;
            }
            i2 += this.stateDim;
        }
        throw new UnknownParameterException(str);
    }

    public void getCurrentMainSetJacobian(double[][] dArr) {
        double[] secondaryState = this.efode.getSecondaryState(this.index);
        int i = 0;
        int i2 = 0;
        while (true) {
            int i3 = this.stateDim;
            if (i < i3) {
                System.arraycopy(secondaryState, i2, dArr[i], 0, i3);
                i2 += this.stateDim;
                i++;
            } else {
                return;
            }
        }
    }

    public void getCurrentParameterJacobian(String str, double[] dArr) {
        double[] secondaryState = this.efode.getSecondaryState(this.index);
        int i = this.stateDim;
        int i2 = i * i;
        ParameterConfiguration[] parameterConfigurationArr = this.selectedParameters;
        int length = parameterConfigurationArr.length;
        int i3 = 0;
        while (i3 < length) {
            if (parameterConfigurationArr[i3].getParameterName().equals(str)) {
                System.arraycopy(secondaryState, i2, dArr, 0, this.stateDim);
                return;
            } else {
                i2 += this.stateDim;
                i3++;
            }
        }
    }

    private void checkDimension(int i, Object obj) throws DimensionMismatchException {
        int length = obj == null ? 0 : Array.getLength(obj);
        if (length != i) {
            throw new DimensionMismatchException(length, i);
        }
    }

    private class JacobiansSecondaryEquations implements SecondaryEquations {
        private JacobiansSecondaryEquations() {
        }

        public int getDimension() {
            return JacobianMatrices.this.stateDim * (JacobianMatrices.this.stateDim + JacobianMatrices.this.paramDim);
        }

        public void computeDerivatives(double d, double[] dArr, double[] dArr2, double[] dArr3, double[] dArr4) throws MaxCountExceededException, DimensionMismatchException {
            int i;
            int i2;
            int i3;
            ParameterConfiguration[] parameterConfigurationArr;
            double[] dArr5 = dArr4;
            if (JacobianMatrices.this.dirtyParameter && JacobianMatrices.this.paramDim != 0) {
                JacobianMatrices.this.jacobianProviders.add(new ParameterJacobianWrapper(JacobianMatrices.this.jode, JacobianMatrices.this.pode, JacobianMatrices.this.selectedParameters));
                boolean unused = JacobianMatrices.this.dirtyParameter = false;
            }
            int access$200 = JacobianMatrices.this.stateDim;
            int[] iArr = new int[2];
            iArr[1] = JacobianMatrices.this.stateDim;
            iArr[0] = access$200;
            double[][] dArr6 = (double[][]) Array.newInstance(Double.TYPE, iArr);
            JacobianMatrices.this.jode.computeMainStateJacobian(d, dArr, dArr2, dArr6);
            for (int i4 = 0; i4 < JacobianMatrices.this.stateDim; i4++) {
                double[] dArr7 = dArr6[i4];
                for (int i5 = 0; i5 < JacobianMatrices.this.stateDim; i5++) {
                    double d2 = 0.0d;
                    int i6 = i5;
                    for (int i7 = 0; i7 < JacobianMatrices.this.stateDim; i7++) {
                        d2 += dArr7[i7] * dArr3[i6];
                        i6 += JacobianMatrices.this.stateDim;
                    }
                    dArr5[(JacobianMatrices.this.stateDim * i4) + i5] = d2;
                }
            }
            if (JacobianMatrices.this.paramDim != 0) {
                double[] dArr8 = new double[JacobianMatrices.this.stateDim];
                int access$2002 = JacobianMatrices.this.stateDim * JacobianMatrices.this.stateDim;
                ParameterConfiguration[] access$700 = JacobianMatrices.this.selectedParameters;
                int length = access$700.length;
                int i8 = 0;
                while (i8 < length) {
                    ParameterConfiguration parameterConfiguration = access$700[i8];
                    boolean z = false;
                    int i9 = 0;
                    while (!z && i9 < JacobianMatrices.this.jacobianProviders.size()) {
                        ParameterJacobianProvider parameterJacobianProvider = (ParameterJacobianProvider) JacobianMatrices.this.jacobianProviders.get(i9);
                        if (parameterJacobianProvider.isSupported(parameterConfiguration.getParameterName())) {
                            i3 = i9;
                            i2 = i8;
                            i = length;
                            parameterConfigurationArr = access$700;
                            parameterJacobianProvider.computeParameterJacobian(d, dArr, dArr2, parameterConfiguration.getParameterName(), dArr8);
                            for (int i10 = 0; i10 < JacobianMatrices.this.stateDim; i10++) {
                                double[] dArr9 = dArr6[i10];
                                double d3 = dArr8[i10];
                                int i11 = access$2002;
                                for (int i12 = 0; i12 < JacobianMatrices.this.stateDim; i12++) {
                                    d3 += dArr9[i12] * dArr3[i11];
                                    i11++;
                                }
                                dArr5[access$2002 + i10] = d3;
                            }
                            z = true;
                        } else {
                            i3 = i9;
                            i2 = i8;
                            i = length;
                            parameterConfigurationArr = access$700;
                        }
                        i9 = i3 + 1;
                        access$700 = parameterConfigurationArr;
                        i8 = i2;
                        length = i;
                    }
                    int i13 = i8;
                    int i14 = length;
                    ParameterConfiguration[] parameterConfigurationArr2 = access$700;
                    if (!z) {
                        Arrays.fill(dArr5, access$2002, JacobianMatrices.this.stateDim + access$2002, 0.0d);
                    }
                    access$2002 += JacobianMatrices.this.stateDim;
                    i8 = i13 + 1;
                    access$700 = parameterConfigurationArr2;
                    length = i14;
                }
            }
        }
    }

    private static class MainStateJacobianWrapper implements MainStateJacobianProvider {
        private final double[] hY;
        /* access modifiers changed from: private */
        public final FirstOrderDifferentialEquations ode;

        public MainStateJacobianWrapper(FirstOrderDifferentialEquations firstOrderDifferentialEquations, double[] dArr) throws DimensionMismatchException {
            this.ode = firstOrderDifferentialEquations;
            this.hY = (double[]) dArr.clone();
            if (dArr.length != firstOrderDifferentialEquations.getDimension()) {
                throw new DimensionMismatchException(firstOrderDifferentialEquations.getDimension(), dArr.length);
            }
        }

        public int getDimension() {
            return this.ode.getDimension();
        }

        public void computeDerivatives(double d, double[] dArr, double[] dArr2) throws MaxCountExceededException, DimensionMismatchException {
            this.ode.computeDerivatives(d, dArr, dArr2);
        }

        public void computeMainStateJacobian(double d, double[] dArr, double[] dArr2, double[][] dArr3) throws MaxCountExceededException, DimensionMismatchException {
            double[] dArr4 = dArr;
            int dimension = this.ode.getDimension();
            double[] dArr5 = new double[dimension];
            for (int i = 0; i < dimension; i++) {
                double d2 = dArr4[i];
                dArr4[i] = this.hY[i] + d2;
                this.ode.computeDerivatives(d, dArr4, dArr5);
                for (int i2 = 0; i2 < dimension; i2++) {
                    dArr3[i2][i] = (dArr5[i2] - dArr2[i2]) / this.hY[i];
                }
                dArr4[i] = d2;
            }
        }
    }

    public static class MismatchedEquations extends MathIllegalArgumentException {
        private static final long serialVersionUID = 20120902;

        public MismatchedEquations() {
            super(LocalizedFormats.UNMATCHED_ODE_IN_EXPANDED_SET, new Object[0]);
        }
    }
}
