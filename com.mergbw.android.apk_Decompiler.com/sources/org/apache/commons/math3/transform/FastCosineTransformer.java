package org.apache.commons.math3.transform;

import java.io.Serializable;
import org.apache.commons.math3.analysis.FunctionUtils;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.ArithmeticUtils;
import org.apache.commons.math3.util.FastMath;

public class FastCosineTransformer implements RealTransformer, Serializable {
    static final long serialVersionUID = 20120212;
    private final DctNormalization normalization;

    public FastCosineTransformer(DctNormalization dctNormalization) {
        this.normalization = dctNormalization;
    }

    public double[] transform(double[] dArr, TransformType transformType) throws MathIllegalArgumentException {
        if (transformType != TransformType.FORWARD) {
            double length = 2.0d / ((double) (dArr.length - 1));
            if (this.normalization == DctNormalization.ORTHOGONAL_DCT_I) {
                length = FastMath.sqrt(length);
            }
            return TransformUtils.scaleArray(fct(dArr), length);
        } else if (this.normalization != DctNormalization.ORTHOGONAL_DCT_I) {
            return fct(dArr);
        } else {
            return TransformUtils.scaleArray(fct(dArr), FastMath.sqrt(2.0d / ((double) (dArr.length - 1))));
        }
    }

    public double[] transform(UnivariateFunction univariateFunction, double d, double d2, int i, TransformType transformType) throws MathIllegalArgumentException {
        return transform(FunctionUtils.sample(univariateFunction, d, d2, i), transformType);
    }

    /* access modifiers changed from: protected */
    public double[] fct(double[] dArr) throws MathIllegalArgumentException {
        double[] dArr2 = dArr;
        double[] dArr3 = new double[dArr2.length];
        int length = dArr2.length - 1;
        if (ArithmeticUtils.isPowerOfTwo((long) length)) {
            double d = 0.5d;
            if (length == 1) {
                double d2 = dArr2[0];
                double d3 = dArr2[1];
                dArr3[0] = (d2 + d3) * 0.5d;
                dArr3[1] = (dArr2[0] - d3) * 0.5d;
                return dArr3;
            }
            double[] dArr4 = new double[length];
            dArr4[0] = (dArr2[0] + dArr2[length]) * 0.5d;
            int i = length >> 1;
            dArr4[i] = dArr2[i];
            double d4 = (dArr2[0] - dArr2[length]) * 0.5d;
            int i2 = 1;
            while (i2 < i) {
                int i3 = length - i2;
                double d5 = (dArr2[i2] + dArr2[i3]) * d;
                double[] dArr5 = dArr4;
                double d6 = (((double) i2) * 3.141592653589793d) / ((double) length);
                double sin = FastMath.sin(d6) * (dArr2[i2] - dArr2[i3]);
                double cos = FastMath.cos(d6) * (dArr2[i2] - dArr2[i3]);
                dArr5[i2] = d5 - sin;
                dArr5[i3] = d5 + sin;
                d4 += cos;
                i2++;
                dArr4 = dArr5;
                d = 0.5d;
            }
            double[] dArr6 = dArr4;
            Complex[] transform = new FastFourierTransformer(DftNormalization.STANDARD).transform(dArr4, TransformType.FORWARD);
            dArr3[0] = transform[0].getReal();
            dArr3[1] = d4;
            for (int i4 = 1; i4 < i; i4++) {
                int i5 = i4 * 2;
                dArr3[i5] = transform[i4].getReal();
                dArr3[i5 + 1] = dArr3[i5 - 1] - transform[i4].getImaginary();
            }
            dArr3[length] = transform[i].getReal();
            return dArr3;
        }
        throw new MathIllegalArgumentException(LocalizedFormats.NOT_POWER_OF_TWO_PLUS_ONE, Integer.valueOf(dArr2.length));
    }
}
