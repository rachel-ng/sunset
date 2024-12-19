package org.apache.commons.math3.transform;

import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.google.android.exoplayer2.C;
import java.lang.reflect.Array;
import java.util.Arrays;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class TransformUtils {
    private static final int[] POWERS_OF_TWO = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288, 1048576, 2097152, 4194304, 8388608, 16777216, 33554432, AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL, C.BUFFER_FLAG_FIRST_SAMPLE, 268435456, 536870912, 1073741824};

    private TransformUtils() {
    }

    public static double[] scaleArray(double[] dArr, double d) {
        for (int i = 0; i < dArr.length; i++) {
            dArr[i] = dArr[i] * d;
        }
        return dArr;
    }

    public static Complex[] scaleArray(Complex[] complexArr, double d) {
        for (int i = 0; i < complexArr.length; i++) {
            complexArr[i] = new Complex(complexArr[i].getReal() * d, complexArr[i].getImaginary() * d);
        }
        return complexArr;
    }

    public static double[][] createRealImaginaryArray(Complex[] complexArr) {
        int[] iArr = new int[2];
        iArr[1] = complexArr.length;
        iArr[0] = 2;
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr);
        double[] dArr2 = dArr[0];
        double[] dArr3 = dArr[1];
        for (int i = 0; i < complexArr.length; i++) {
            Complex complex = complexArr[i];
            dArr2[i] = complex.getReal();
            dArr3[i] = complex.getImaginary();
        }
        return dArr;
    }

    public static Complex[] createComplexArray(double[][] dArr) throws DimensionMismatchException {
        if (dArr.length == 2) {
            double[] dArr2 = dArr[0];
            double[] dArr3 = dArr[1];
            if (dArr2.length == dArr3.length) {
                int length = dArr2.length;
                Complex[] complexArr = new Complex[length];
                for (int i = 0; i < length; i++) {
                    complexArr[i] = new Complex(dArr2[i], dArr3[i]);
                }
                return complexArr;
            }
            throw new DimensionMismatchException(dArr3.length, dArr2.length);
        }
        throw new DimensionMismatchException(dArr.length, 2);
    }

    public static int exactLog2(int i) throws MathIllegalArgumentException {
        int binarySearch = Arrays.binarySearch(POWERS_OF_TWO, i);
        if (binarySearch >= 0) {
            return binarySearch;
        }
        throw new MathIllegalArgumentException(LocalizedFormats.NOT_POWER_OF_TWO_CONSIDER_PADDING, Integer.valueOf(i));
    }
}
