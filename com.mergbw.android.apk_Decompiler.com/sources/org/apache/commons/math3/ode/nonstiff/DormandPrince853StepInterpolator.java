package org.apache.commons.math3.ode.nonstiff;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.AbstractIntegrator;
import org.apache.commons.math3.ode.EquationsMapper;
import org.apache.commons.math3.ode.sampling.StepInterpolator;

class DormandPrince853StepInterpolator extends RungeKuttaStepInterpolator {
    private static final double B_01 = 0.054293734116568765d;
    private static final double B_06 = 4.450312892752409d;
    private static final double B_07 = 1.8915178993145003d;
    private static final double B_08 = -5.801203960010585d;
    private static final double B_09 = 0.3111643669578199d;
    private static final double B_10 = -0.1521609496625161d;
    private static final double B_11 = 0.20136540080403034d;
    private static final double B_12 = 0.04471061572777259d;
    private static final double C14 = 0.1d;
    private static final double C15 = 0.2d;
    private static final double C16 = 0.7777777777777778d;
    private static final double[][] D = {new double[]{-8.428938276109013d, 0.5667149535193777d, -3.0689499459498917d, 2.38466765651207d, 2.1170345824450285d, -0.871391583777973d, 2.2404374302607883d, 0.6315787787694688d, -0.08899033645133331d, 18.148505520854727d, -9.194632392478356d, -4.436036387594894d}, new double[]{10.427508642579134d, 242.28349177525817d, 165.20045171727028d, -374.5467547226902d, -22.113666853125302d, 7.733432668472264d, -30.674084731089398d, -9.332130526430229d, 15.697238121770845d, -31.139403219565178d, -9.35292435884448d, 35.81684148639408d}, new double[]{19.985053242002433d, -387.0373087493518d, -189.17813819516758d, 527.8081592054236d, -11.573902539959631d, 6.8812326946963d, -1.0006050966910838d, 0.7777137798053443d, -2.778205752353508d, -60.19669523126412d, 84.32040550667716d, 11.99229113618279d}, new double[]{-25.69393346270375d, -154.18974869023643d, -231.5293791760455d, 357.6391179106141d, 93.4053241836243d, -37.45832313645163d, 104.0996495089623d, 29.8402934266605d, -43.53345659001114d, 96.32455395918828d, -39.17726167561544d, -149.72683625798564d}};
    private static final double K14_01 = 0.0018737681664791894d;
    private static final double K14_06 = -4.450312892752409d;
    private static final double K14_07 = -1.6380176890978755d;
    private static final double K14_08 = 5.554964922539782d;
    private static final double K14_09 = -0.4353557902216363d;
    private static final double K14_10 = 0.30545274794128174d;
    private static final double K14_11 = -0.19316434850839564d;
    private static final double K14_12 = -0.03714271806722689d;
    private static final double K14_13 = -0.008298d;
    private static final double K15_01 = -0.022459085953066622d;
    private static final double K15_06 = -4.422011983080043d;
    private static final double K15_07 = -1.8379759110070617d;
    private static final double K15_08 = 5.746280211439194d;
    private static final double K15_09 = -0.3111643669578199d;
    private static final double K15_10 = 0.1521609496625161d;
    private static final double K15_11 = -0.2014737481327276d;
    private static final double K15_12 = -0.04432804463693693d;
    private static final double K15_13 = -3.4046500868740456E-4d;
    private static final double K15_14 = 0.1413124436746325d;
    private static final double K16_01 = -0.4831900357003607d;
    private static final double K16_06 = -9.147934308113573d;
    private static final double K16_07 = 5.791903296748099d;
    private static final double K16_08 = 9.870193778407696d;
    private static final double K16_09 = 0.04556282049746119d;
    private static final double K16_10 = 0.1521609496625161d;
    private static final double K16_11 = -0.20136540080403034d;
    private static final double K16_12 = -0.04471061572777259d;
    private static final double K16_13 = -0.0013990241651590145d;
    private static final double K16_14 = 2.9475147891527724d;
    private static final double K16_15 = -9.15095847217987d;
    private static final long serialVersionUID = 20111120;
    private double[][] v;
    private boolean vectorsInitialized;
    private double[][] yDotKLast;

    public DormandPrince853StepInterpolator() {
        double[][] dArr = null;
        this.yDotKLast = null;
        this.v = null;
        this.vectorsInitialized = false;
    }

    public DormandPrince853StepInterpolator(DormandPrince853StepInterpolator dormandPrince853StepInterpolator) {
        super(dormandPrince853StepInterpolator);
        if (dormandPrince853StepInterpolator.currentState == null) {
            double[][] dArr = null;
            this.yDotKLast = null;
            this.v = null;
            this.vectorsInitialized = false;
            return;
        }
        int length = dormandPrince853StepInterpolator.currentState.length;
        this.yDotKLast = new double[3][];
        int i = 0;
        while (true) {
            double[][] dArr2 = this.yDotKLast;
            if (i >= dArr2.length) {
                break;
            }
            double[] dArr3 = new double[length];
            dArr2[i] = dArr3;
            System.arraycopy(dormandPrince853StepInterpolator.yDotKLast[i], 0, dArr3, 0, length);
            i++;
        }
        this.v = new double[7][];
        int i2 = 0;
        while (true) {
            double[][] dArr4 = this.v;
            if (i2 < dArr4.length) {
                double[] dArr5 = new double[length];
                dArr4[i2] = dArr5;
                System.arraycopy(dormandPrince853StepInterpolator.v[i2], 0, dArr5, 0, length);
                i2++;
            } else {
                this.vectorsInitialized = dormandPrince853StepInterpolator.vectorsInitialized;
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public StepInterpolator doCopy() {
        return new DormandPrince853StepInterpolator(this);
    }

    public void reinitialize(AbstractIntegrator abstractIntegrator, double[] dArr, double[][] dArr2, boolean z, EquationsMapper equationsMapper, EquationsMapper[] equationsMapperArr) {
        super.reinitialize(abstractIntegrator, dArr, dArr2, z, equationsMapper, equationsMapperArr);
        int length = this.currentState.length;
        this.yDotKLast = new double[3][];
        int i = 0;
        while (true) {
            double[][] dArr3 = this.yDotKLast;
            if (i >= dArr3.length) {
                break;
            }
            dArr3[i] = new double[length];
            i++;
        }
        this.v = new double[7][];
        int i2 = 0;
        while (true) {
            double[][] dArr4 = this.v;
            if (i2 < dArr4.length) {
                dArr4[i2] = new double[length];
                i2++;
            } else {
                this.vectorsInitialized = false;
                return;
            }
        }
    }

    public void storeTime(double d) {
        super.storeTime(d);
        this.vectorsInitialized = false;
    }

    /* access modifiers changed from: protected */
    public void computeInterpolatedStateAndDerivatives(double d, double d2) throws MaxCountExceededException {
        char c2 = 6;
        if (!this.vectorsInitialized) {
            char c3 = 7;
            if (this.v == null) {
                this.v = new double[7][];
                for (int i = 0; i < 7; i++) {
                    this.v[i] = new double[this.interpolatedState.length];
                }
            }
            finalizeStep();
            int i2 = 0;
            while (i2 < this.interpolatedState.length) {
                double d3 = this.yDotK[0][i2];
                double d4 = this.yDotK[5][i2];
                double d5 = this.yDotK[c2][i2];
                double d6 = this.yDotK[c3][i2];
                double d7 = this.yDotK[8][i2];
                double d8 = this.yDotK[9][i2];
                double d9 = this.yDotK[10][i2];
                double d10 = this.yDotK[11][i2];
                double d11 = this.yDotK[12][i2];
                double[][] dArr = this.yDotKLast;
                double d12 = dArr[0][i2];
                double d13 = dArr[1][i2];
                double d14 = dArr[2][i2];
                double[][] dArr2 = this.v;
                double[] dArr3 = dArr2[0];
                double d15 = (B_01 * d3) + (B_06 * d4) + (B_07 * d5) + (B_08 * d6) + (B_09 * d7) + (B_10 * d8) + (B_11 * d9) + (B_12 * d10);
                dArr3[i2] = d15;
                double d16 = d3 - d15;
                dArr2[1][i2] = d16;
                dArr2[2][i2] = (dArr3[i2] - d16) - this.yDotK[12][i2];
                int i3 = 0;
                while (true) {
                    double[][] dArr4 = D;
                    if (i3 >= dArr4.length) {
                        break;
                    }
                    double[] dArr5 = this.v[i3 + 3];
                    double[] dArr6 = dArr4[i3];
                    dArr5[i2] = (dArr6[0] * d3) + (dArr6[1] * d4) + (dArr6[2] * d5) + (dArr6[3] * d6) + (dArr6[4] * d7) + (dArr6[5] * d8) + (dArr6[6] * d9) + (dArr6[7] * d10) + (dArr6[8] * d11) + (dArr6[9] * d12) + (dArr6[10] * d13) + (dArr6[11] * d14);
                    i3++;
                }
                i2++;
                c3 = 7;
                c2 = 6;
            }
            this.vectorsInitialized = true;
        }
        double d17 = 1.0d - d;
        double d18 = d * 2.0d;
        double d19 = d * d;
        double d20 = 1.0d - d18;
        double d21 = (2.0d - (d * 3.0d)) * d;
        double d22 = d18 * (((d18 - 3.0d) * d) + 1.0d);
        double d23 = ((((5.0d * d) - 8.0d) * d) + 3.0d) * d19;
        double d24 = ((((d * (15.0d - (6.0d * d))) - 0.375d) * d) + 3.0d) * d19;
        double d25 = d19 * d * ((((d * (18.0d - (7.0d * d))) - 0.28125d) * d) + 4.0d);
        if (this.previousState == null || d > 0.5d) {
            for (int i4 = 0; i4 < this.interpolatedState.length; i4++) {
                double[] dArr7 = this.interpolatedState;
                double d26 = this.currentState[i4];
                double[][] dArr8 = this.v;
                dArr7[i4] = d26 - ((dArr8[0][i4] - ((dArr8[1][i4] + ((dArr8[2][i4] + ((dArr8[3][i4] + ((dArr8[4][i4] + ((dArr8[5][i4] + (dArr8[6][i4] * d)) * d17)) * d)) * d17)) * d)) * d)) * d2);
                double[] dArr9 = this.interpolatedDerivatives;
                double[][] dArr10 = this.v;
                dArr9[i4] = dArr10[0][i4] + (dArr10[1][i4] * d20) + (dArr10[2][i4] * d21) + (dArr10[3][i4] * d22) + (dArr10[4][i4] * d23) + (dArr10[5][i4] * d24) + (dArr10[6][i4] * d25);
            }
            return;
        }
        for (int i5 = 0; i5 < this.interpolatedState.length; i5++) {
            double[] dArr11 = this.interpolatedState;
            double d27 = this.previousState[i5];
            double[][] dArr12 = this.v;
            dArr11[i5] = d27 + (this.h * d * (dArr12[0][i5] + ((dArr12[1][i5] + ((dArr12[2][i5] + ((dArr12[3][i5] + ((dArr12[4][i5] + ((dArr12[5][i5] + (dArr12[6][i5] * d)) * d17)) * d)) * d17)) * d)) * d17)));
            double[] dArr13 = this.interpolatedDerivatives;
            double[][] dArr14 = this.v;
            dArr13[i5] = dArr14[0][i5] + (dArr14[1][i5] * d20) + (dArr14[2][i5] * d21) + (dArr14[3][i5] * d22) + (dArr14[4][i5] * d23) + (dArr14[5][i5] * d24) + (dArr14[6][i5] * d25);
        }
    }

    /* access modifiers changed from: protected */
    public void doFinalize() throws MaxCountExceededException {
        if (this.currentState != null) {
            double[] dArr = new double[this.currentState.length];
            double globalPreviousTime = getGlobalPreviousTime();
            for (int i = 0; i < this.currentState.length; i++) {
                dArr[i] = this.currentState[i] + (this.h * ((this.yDotK[0][i] * K14_01) + (this.yDotK[5][i] * K14_06) + (this.yDotK[6][i] * K14_07) + (this.yDotK[7][i] * K14_08) + (this.yDotK[8][i] * K14_09) + (this.yDotK[9][i] * K14_10) + (this.yDotK[10][i] * K14_11) + (this.yDotK[11][i] * K14_12) + (this.yDotK[12][i] * K14_13)));
            }
            this.integrator.computeDerivatives((this.h * C14) + globalPreviousTime, dArr, this.yDotKLast[0]);
            for (int i2 = 0; i2 < this.currentState.length; i2++) {
                dArr[i2] = this.currentState[i2] + (this.h * ((this.yDotK[0][i2] * K15_01) + (this.yDotK[5][i2] * K15_06) + (this.yDotK[6][i2] * K15_07) + (this.yDotK[7][i2] * K15_08) + (this.yDotK[8][i2] * K15_09) + (this.yDotK[9][i2] * 0.1521609496625161d) + (this.yDotK[10][i2] * K15_11) + (this.yDotK[11][i2] * K15_12) + (this.yDotK[12][i2] * K15_13) + (this.yDotKLast[0][i2] * K15_14)));
            }
            this.integrator.computeDerivatives((this.h * C15) + globalPreviousTime, dArr, this.yDotKLast[1]);
            for (int i3 = 0; i3 < this.currentState.length; i3++) {
                double[][] dArr2 = this.yDotKLast;
                dArr[i3] = this.currentState[i3] + (this.h * ((this.yDotK[0][i3] * K16_01) + (this.yDotK[5][i3] * K16_06) + (this.yDotK[6][i3] * K16_07) + (this.yDotK[7][i3] * K16_08) + (this.yDotK[8][i3] * K16_09) + (this.yDotK[9][i3] * 0.1521609496625161d) + (this.yDotK[10][i3] * K16_11) + (this.yDotK[11][i3] * K16_12) + (this.yDotK[12][i3] * K16_13) + (dArr2[0][i3] * K16_14) + (dArr2[1][i3] * K16_15)));
            }
            this.integrator.computeDerivatives(globalPreviousTime + (this.h * C16), dArr, this.yDotKLast[2]);
        }
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        try {
            finalizeStep();
            int length = this.currentState == null ? -1 : this.currentState.length;
            objectOutput.writeInt(length);
            for (int i = 0; i < length; i++) {
                objectOutput.writeDouble(this.yDotKLast[0][i]);
                objectOutput.writeDouble(this.yDotKLast[1][i]);
                objectOutput.writeDouble(this.yDotKLast[2][i]);
            }
            super.writeExternal(objectOutput);
        } catch (MaxCountExceededException e) {
            IOException iOException = new IOException(e.getLocalizedMessage());
            iOException.initCause(e);
            throw iOException;
        }
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        double[] dArr;
        this.yDotKLast = new double[3][];
        int readInt = objectInput.readInt();
        double[][] dArr2 = this.yDotKLast;
        double[] dArr3 = null;
        dArr2[0] = readInt < 0 ? null : new double[readInt];
        if (readInt < 0) {
            dArr = null;
        } else {
            dArr = new double[readInt];
        }
        dArr2[1] = dArr;
        if (readInt >= 0) {
            dArr3 = new double[readInt];
        }
        dArr2[2] = dArr3;
        for (int i = 0; i < readInt; i++) {
            this.yDotKLast[0][i] = objectInput.readDouble();
            this.yDotKLast[1][i] = objectInput.readDouble();
            this.yDotKLast[2][i] = objectInput.readDouble();
        }
        super.readExternal(objectInput);
    }
}
