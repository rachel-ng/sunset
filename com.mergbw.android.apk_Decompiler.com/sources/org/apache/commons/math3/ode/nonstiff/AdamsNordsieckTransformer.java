package org.apache.commons.math3.ode.nonstiff;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayFieldVector;
import org.apache.commons.math3.linear.FieldDecompositionSolver;
import org.apache.commons.math3.linear.FieldLUDecomposition;
import org.apache.commons.math3.linear.FieldMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.QRDecomposition;
import org.apache.commons.math3.linear.RealMatrix;

public class AdamsNordsieckTransformer {
    private static final Map<Integer, AdamsNordsieckTransformer> CACHE = new HashMap();
    private final double[] c1;
    private final Array2DRowRealMatrix update;

    private AdamsNordsieckTransformer(int i) {
        FieldMatrix<BigFraction> buildP = buildP(i);
        FieldDecompositionSolver solver = new FieldLUDecomposition(buildP).getSolver();
        BigFraction[] bigFractionArr = new BigFraction[i];
        Arrays.fill(bigFractionArr, BigFraction.ONE);
        BigFraction[] bigFractionArr2 = (BigFraction[]) solver.solve(new ArrayFieldVector((T[]) bigFractionArr, false)).toArray();
        BigFraction[][] bigFractionArr3 = (BigFraction[][]) buildP.getData();
        for (int length = bigFractionArr3.length - 1; length > 0; length--) {
            bigFractionArr3[length] = bigFractionArr3[length - 1];
        }
        BigFraction[] bigFractionArr4 = new BigFraction[i];
        bigFractionArr3[0] = bigFractionArr4;
        Arrays.fill(bigFractionArr4, BigFraction.ZERO);
        this.update = MatrixUtils.bigFractionMatrixToRealMatrix(solver.solve(new Array2DRowFieldMatrix((T[][]) bigFractionArr3, false)));
        this.c1 = new double[i];
        for (int i2 = 0; i2 < i; i2++) {
            this.c1[i2] = bigFractionArr2[i2].doubleValue();
        }
    }

    public static AdamsNordsieckTransformer getInstance(int i) {
        AdamsNordsieckTransformer adamsNordsieckTransformer;
        Map<Integer, AdamsNordsieckTransformer> map = CACHE;
        synchronized (map) {
            adamsNordsieckTransformer = map.get(Integer.valueOf(i));
            if (adamsNordsieckTransformer == null) {
                adamsNordsieckTransformer = new AdamsNordsieckTransformer(i);
                map.put(Integer.valueOf(i), adamsNordsieckTransformer);
            }
        }
        return adamsNordsieckTransformer;
    }

    public int getNSteps() {
        return this.c1.length;
    }

    private FieldMatrix<BigFraction> buildP(int i) {
        int[] iArr = new int[2];
        iArr[1] = i;
        iArr[0] = i;
        BigFraction[][] bigFractionArr = (BigFraction[][]) Array.newInstance(BigFraction.class, iArr);
        int i2 = 0;
        while (i2 < bigFractionArr.length) {
            BigFraction[] bigFractionArr2 = bigFractionArr[i2];
            i2++;
            int i3 = -i2;
            int i4 = i3;
            for (int i5 = 0; i5 < bigFractionArr2.length; i5++) {
                bigFractionArr2[i5] = new BigFraction((i5 + 2) * i4);
                i4 *= i3;
            }
        }
        return new Array2DRowFieldMatrix((T[][]) bigFractionArr, false);
    }

    public Array2DRowRealMatrix initializeHighOrderDerivatives(double d, double[] dArr, double[][] dArr2, double[][] dArr3) {
        double[][] dArr4 = dArr2;
        int i = 1;
        int[] iArr = new int[2];
        iArr[1] = this.c1.length;
        char c2 = 0;
        iArr[0] = (dArr4.length - 1) * 2;
        double[][] dArr5 = (double[][]) Array.newInstance(Double.TYPE, iArr);
        int[] iArr2 = new int[2];
        iArr2[1] = dArr4[0].length;
        iArr2[0] = (dArr4.length - 1) * 2;
        double[][] dArr6 = (double[][]) Array.newInstance(Double.TYPE, iArr2);
        double[] dArr7 = dArr4[0];
        double[] dArr8 = dArr3[0];
        int i2 = 1;
        while (i2 < dArr4.length) {
            double d2 = dArr[i2] - dArr[c2];
            double d3 = d2 / d;
            double d4 = 1.0d / d;
            int i3 = i2 * 2;
            int i4 = i3 - 2;
            int i5 = i3 - i;
            double[] dArr9 = dArr5[i5];
            int i6 = 0;
            for (double[] dArr10 = dArr5[i4]; i6 < dArr10.length; dArr10 = dArr10) {
                d4 *= d3;
                dArr10[i6] = d2 * d4;
                dArr9[i6] = ((double) (i6 + 2)) * d4;
                i6++;
            }
            double[] dArr11 = dArr4[i2];
            double[] dArr12 = dArr3[i2];
            double[] dArr13 = dArr6[i4];
            double[] dArr14 = dArr6[i5];
            for (int i7 = 0; i7 < dArr11.length; i7++) {
                dArr13[i7] = (dArr11[i7] - dArr7[i7]) - (dArr8[i7] * d2);
                dArr14[i7] = dArr12[i7] - dArr8[i7];
            }
            i2++;
            i = 1;
            c2 = 0;
        }
        return new Array2DRowRealMatrix(new QRDecomposition(new Array2DRowRealMatrix(dArr5, false)).getSolver().solve((RealMatrix) new Array2DRowRealMatrix(dArr6, false)).getData(), false);
    }

    public Array2DRowRealMatrix updateHighOrderDerivativesPhase1(Array2DRowRealMatrix array2DRowRealMatrix) {
        return this.update.multiply(array2DRowRealMatrix);
    }

    public void updateHighOrderDerivativesPhase2(double[] dArr, double[] dArr2, Array2DRowRealMatrix array2DRowRealMatrix) {
        double[][] dataRef = array2DRowRealMatrix.getDataRef();
        for (int i = 0; i < dataRef.length; i++) {
            double[] dArr3 = dataRef[i];
            double d = this.c1[i];
            for (int i2 = 0; i2 < dArr3.length; i2++) {
                dArr3[i2] = dArr3[i2] + ((dArr[i2] - dArr2[i2]) * d);
            }
        }
    }
}
