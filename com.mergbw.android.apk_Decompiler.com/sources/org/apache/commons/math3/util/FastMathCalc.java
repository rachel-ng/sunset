package org.apache.commons.math3.util;

import java.io.PrintStream;
import org.apache.commons.math3.exception.DimensionMismatchException;

class FastMathCalc {
    private static final double[] FACT = {1.0d, 1.0d, 2.0d, 6.0d, 24.0d, 120.0d, 720.0d, 5040.0d, 40320.0d, 362880.0d, 3628800.0d, 3.99168E7d, 4.790016E8d, 6.2270208E9d, 8.71782912E10d, 1.307674368E12d, 2.0922789888E13d, 3.55687428096E14d, 6.402373705728E15d, 1.21645100408832E17d};
    private static final long HEX_40000000 = 1073741824;
    private static final double[][] LN_SPLIT_COEF = {new double[]{2.0d, 0.0d}, new double[]{0.6666666269302368d, 3.9736429850260626E-8d}, new double[]{0.3999999761581421d, 2.3841857910019882E-8d}, new double[]{0.2857142686843872d, 1.7029898543501842E-8d}, new double[]{0.2222222089767456d, 1.3245471311735498E-8d}, new double[]{0.1818181574344635d, 2.4384203044354907E-8d}, new double[]{0.1538461446762085d, 9.140260083262505E-9d}, new double[]{0.13333332538604736d, 9.220590270857665E-9d}, new double[]{0.11764700710773468d, 1.2393345855018391E-8d}, new double[]{0.10526403784751892d, 8.251545029714408E-9d}, new double[]{0.0952233225107193d, 1.2675934823758863E-8d}, new double[]{0.08713622391223907d, 1.1430250008909141E-8d}, new double[]{0.07842259109020233d, 2.404307984052299E-9d}, new double[]{0.08371849358081818d, 1.176342548272881E-8d}, new double[]{0.030589580535888672d, 1.2958646899018938E-9d}, new double[]{0.14982303977012634d, 1.225743062930824E-8d}};
    private static final String TABLE_END_DECL = "    };";
    private static final String TABLE_START_DECL = "    {";

    private FastMathCalc() {
    }

    private static void buildSinCosTables(double[] dArr, double[] dArr2, double[] dArr3, double[] dArr4, int i, double[] dArr5, double[] dArr6) {
        int i2;
        int i3 = i;
        double[] dArr7 = new double[2];
        int i4 = 0;
        while (true) {
            if (i4 >= 7) {
                break;
            }
            double d = ((double) i4) / 8.0d;
            slowSin(d, dArr7);
            dArr[i4] = dArr7[0];
            dArr2[i4] = dArr7[1];
            slowCos(d, dArr7);
            dArr3[i4] = dArr7[0];
            dArr4[i4] = dArr7[1];
            i4++;
        }
        for (i2 = 7; i2 < i3; i2++) {
            double[] dArr8 = new double[2];
            double[] dArr9 = new double[2];
            double[] dArr10 = new double[2];
            double[] dArr11 = new double[2];
            if ((i2 & 1) == 0) {
                int i5 = i2 / 2;
                dArr8[0] = dArr[i5];
                dArr8[1] = dArr2[i5];
                dArr9[0] = dArr3[i5];
                dArr9[1] = dArr4[i5];
                splitMult(dArr8, dArr9, dArr7);
                dArr[i2] = dArr7[0] * 2.0d;
                dArr2[i2] = dArr7[1] * 2.0d;
                splitMult(dArr9, dArr9, dArr10);
                splitMult(dArr8, dArr8, dArr11);
                dArr11[0] = -dArr11[0];
                dArr11[1] = -dArr11[1];
                splitAdd(dArr10, dArr11, dArr7);
                dArr3[i2] = dArr7[0];
                dArr4[i2] = dArr7[1];
            } else {
                int i6 = i2 / 2;
                dArr8[0] = dArr[i6];
                dArr8[1] = dArr2[i6];
                dArr9[0] = dArr3[i6];
                dArr9[1] = dArr4[i6];
                int i7 = i6 + 1;
                dArr10[0] = dArr[i7];
                dArr10[1] = dArr2[i7];
                double[] dArr12 = {dArr3[i7], dArr4[i7]};
                splitMult(dArr8, dArr12, dArr11);
                splitMult(dArr9, dArr10, dArr7);
                splitAdd(dArr7, dArr11, dArr7);
                dArr[i2] = dArr7[0];
                dArr2[i2] = dArr7[1];
                splitMult(dArr9, dArr12, dArr7);
                splitMult(dArr8, dArr10, dArr11);
                dArr11[0] = -dArr11[0];
                dArr11[1] = -dArr11[1];
                splitAdd(dArr7, dArr11, dArr7);
                dArr3[i2] = dArr7[0];
                dArr4[i2] = dArr7[1];
            }
        }
        for (int i8 = 0; i8 < i3; i8++) {
            double[] dArr13 = new double[2];
            double[] dArr14 = {dArr3[i8], dArr4[i8]};
            splitReciprocal(dArr14, dArr13);
            splitMult(new double[]{dArr[i8], dArr2[i8]}, dArr13, dArr14);
            dArr5[i8] = dArr14[0];
            dArr6[i8] = dArr14[1];
        }
    }

    static double slowCos(double d, double[] dArr) {
        double[] dArr2 = new double[2];
        double[] dArr3 = new double[2];
        double[] dArr4 = new double[2];
        double[] dArr5 = new double[2];
        split(d, dArr2);
        dArr3[1] = 0.0d;
        dArr3[0] = 0.0d;
        for (int length = FACT.length - 1; length >= 0; length--) {
            splitMult(dArr2, dArr3, dArr5);
            dArr3[0] = dArr5[0];
            dArr3[1] = dArr5[1];
            if ((length & 1) == 0) {
                split(FACT[length], dArr5);
                splitReciprocal(dArr5, dArr4);
                if ((length & 2) != 0) {
                    dArr4[0] = -dArr4[0];
                    dArr4[1] = -dArr4[1];
                }
                splitAdd(dArr3, dArr4, dArr5);
                dArr3[0] = dArr5[0];
                dArr3[1] = dArr5[1];
            }
        }
        if (dArr != null) {
            dArr[0] = dArr3[0];
            dArr[1] = dArr3[1];
        }
        return dArr3[0] + dArr3[1];
    }

    static double slowSin(double d, double[] dArr) {
        double[] dArr2 = new double[2];
        double[] dArr3 = new double[2];
        double[] dArr4 = new double[2];
        double[] dArr5 = new double[2];
        split(d, dArr2);
        dArr3[1] = 0.0d;
        dArr3[0] = 0.0d;
        for (int length = FACT.length - 1; length >= 0; length--) {
            splitMult(dArr2, dArr3, dArr5);
            dArr3[0] = dArr5[0];
            dArr3[1] = dArr5[1];
            if ((length & 1) != 0) {
                split(FACT[length], dArr5);
                splitReciprocal(dArr5, dArr4);
                if ((length & 2) != 0) {
                    dArr4[0] = -dArr4[0];
                    dArr4[1] = -dArr4[1];
                }
                splitAdd(dArr3, dArr4, dArr5);
                dArr3[0] = dArr5[0];
                dArr3[1] = dArr5[1];
            }
        }
        if (dArr != null) {
            dArr[0] = dArr3[0];
            dArr[1] = dArr3[1];
        }
        return dArr3[0] + dArr3[1];
    }

    static double slowexp(double d, double[] dArr) {
        double[] dArr2 = new double[2];
        double[] dArr3 = new double[2];
        double[] dArr4 = new double[2];
        double[] dArr5 = new double[2];
        split(d, dArr2);
        dArr3[1] = 0.0d;
        dArr3[0] = 0.0d;
        for (int length = FACT.length - 1; length >= 0; length--) {
            splitMult(dArr2, dArr3, dArr5);
            dArr3[0] = dArr5[0];
            dArr3[1] = dArr5[1];
            split(FACT[length], dArr5);
            splitReciprocal(dArr5, dArr4);
            splitAdd(dArr3, dArr4, dArr5);
            dArr3[0] = dArr5[0];
            dArr3[1] = dArr5[1];
        }
        if (dArr != null) {
            dArr[0] = dArr3[0];
            dArr[1] = dArr3[1];
        }
        return dArr3[0] + dArr3[1];
    }

    private static void split(double d, double[] dArr) {
        if (d >= 8.0E298d || d <= -8.0E298d) {
            double d2 = (((9.313225746154785E-10d * d) + d) - d) * 1.073741824E9d;
            dArr[0] = d2;
            dArr[1] = d - d2;
            return;
        }
        double d3 = 1.073741824E9d * d;
        double d4 = (d + d3) - d3;
        dArr[0] = d4;
        dArr[1] = d - d4;
    }

    private static void resplit(double[] dArr) {
        double d = dArr[0];
        double d2 = dArr[1];
        double d3 = d + d2;
        double d4 = -((d3 - d) - d2);
        if (d3 >= 8.0E298d || d3 <= -8.0E298d) {
            double d5 = (((9.313225746154785E-10d * d3) + d3) - d3) * 1.073741824E9d;
            dArr[0] = d5;
            dArr[1] = (d3 - d5) + d4;
            return;
        }
        double d6 = 1.073741824E9d * d3;
        double d7 = (d3 + d6) - d6;
        dArr[0] = d7;
        dArr[1] = (d3 - d7) + d4;
    }

    private static void splitMult(double[] dArr, double[] dArr2, double[] dArr3) {
        dArr3[0] = dArr[0] * dArr2[0];
        double d = dArr[0];
        double d2 = dArr2[1];
        double d3 = dArr[1];
        dArr3[1] = (d * d2) + (dArr2[0] * d3) + (d3 * d2);
        resplit(dArr3);
    }

    private static void splitAdd(double[] dArr, double[] dArr2, double[] dArr3) {
        dArr3[0] = dArr[0] + dArr2[0];
        dArr3[1] = dArr[1] + dArr2[1];
        resplit(dArr3);
    }

    static void splitReciprocal(double[] dArr, double[] dArr2) {
        if (dArr[0] == 0.0d) {
            dArr[0] = dArr[1];
            dArr[1] = 0.0d;
        }
        dArr2[0] = 0.9999997615814209d / dArr[0];
        double d = dArr[0];
        double d2 = dArr[1];
        double d3 = ((2.384185791015625E-7d * d) - (0.9999997615814209d * d2)) / ((d * d) + (d * d2));
        dArr2[1] = d3;
        if (d3 != d3) {
            dArr2[1] = 0.0d;
        }
        resplit(dArr2);
        for (int i = 0; i < 2; i++) {
            double d4 = dArr2[0];
            double d5 = dArr[0];
            double d6 = dArr[1];
            double d7 = dArr2[1];
            dArr2[1] = d7 + (((((1.0d - (d4 * d5)) - (d4 * d6)) - (d5 * d7)) - (d6 * d7)) * (d4 + d7));
        }
    }

    private static void quadMult(double[] dArr, double[] dArr2, double[] dArr3) {
        double[] dArr4 = new double[2];
        double[] dArr5 = new double[2];
        double[] dArr6 = new double[2];
        split(dArr[0], dArr4);
        split(dArr2[0], dArr5);
        splitMult(dArr4, dArr5, dArr6);
        dArr3[0] = dArr6[0];
        dArr3[1] = dArr6[1];
        split(dArr2[1], dArr5);
        splitMult(dArr4, dArr5, dArr6);
        double d = dArr3[0];
        double d2 = dArr6[0];
        double d3 = d + d2;
        double d4 = dArr3[1] - ((d3 - d) - d2);
        dArr3[1] = d4;
        dArr3[0] = d3;
        double d5 = dArr6[1];
        double d6 = d3 + d5;
        dArr3[1] = d4 - ((d6 - d3) - d5);
        dArr3[0] = d6;
        split(dArr[1], dArr4);
        split(dArr2[0], dArr5);
        splitMult(dArr4, dArr5, dArr6);
        double d7 = dArr3[0];
        double d8 = dArr6[0];
        double d9 = d7 + d8;
        double d10 = dArr3[1] - ((d9 - d7) - d8);
        dArr3[1] = d10;
        dArr3[0] = d9;
        double d11 = dArr6[1];
        double d12 = d9 + d11;
        dArr3[1] = d10 - ((d12 - d9) - d11);
        dArr3[0] = d12;
        split(dArr[1], dArr4);
        split(dArr2[1], dArr5);
        splitMult(dArr4, dArr5, dArr6);
        double d13 = dArr3[0];
        double d14 = dArr6[0];
        double d15 = d13 + d14;
        double d16 = dArr3[1] - ((d15 - d13) - d14);
        dArr3[1] = d16;
        dArr3[0] = d15;
        double d17 = dArr6[1];
        double d18 = d15 + d17;
        dArr3[1] = d16 - ((d18 - d15) - d17);
        dArr3[0] = d18;
    }

    static double expint(int i, double[] dArr) {
        double[] dArr2 = new double[2];
        double[] dArr3 = new double[2];
        double[] dArr4 = {2.718281828459045d, 1.4456468917292502E-16d};
        split(1.0d, dArr3);
        while (i > 0) {
            if ((i & 1) != 0) {
                quadMult(dArr3, dArr4, dArr2);
                dArr3[0] = dArr2[0];
                dArr3[1] = dArr2[1];
            }
            quadMult(dArr4, dArr4, dArr2);
            dArr4[0] = dArr2[0];
            dArr4[1] = dArr2[1];
            i >>= 1;
        }
        if (dArr != null) {
            dArr[0] = dArr3[0];
            dArr[1] = dArr3[1];
            resplit(dArr);
        }
        return dArr3[0] + dArr3[1];
    }

    static double[] slowLog(double d) {
        double[] dArr = new double[2];
        double[] dArr2 = new double[2];
        double[] dArr3 = new double[2];
        double[] dArr4 = new double[2];
        split(d, dArr);
        dArr[0] = dArr[0] + 1.0d;
        resplit(dArr);
        splitReciprocal(dArr, dArr4);
        dArr[0] = dArr[0] - 2.0d;
        resplit(dArr);
        splitMult(dArr, dArr4, dArr3);
        dArr[0] = dArr3[0];
        dArr[1] = dArr3[1];
        splitMult(dArr, dArr, dArr2);
        double[][] dArr5 = LN_SPLIT_COEF;
        dArr3[0] = dArr5[dArr5.length - 1][0];
        dArr3[1] = dArr5[dArr5.length - 1][1];
        for (int length = dArr5.length - 2; length >= 0; length--) {
            splitMult(dArr3, dArr2, dArr4);
            dArr3[0] = dArr4[0];
            dArr3[1] = dArr4[1];
            splitAdd(dArr3, LN_SPLIT_COEF[length], dArr4);
            dArr3[0] = dArr4[0];
            dArr3[1] = dArr4[1];
        }
        splitMult(dArr3, dArr, dArr4);
        dArr3[0] = dArr4[0];
        dArr3[1] = dArr4[1];
        return dArr3;
    }

    static void printarray(PrintStream printStream, String str, int i, double[][] dArr) {
        printStream.println(str);
        checkLen(i, dArr.length);
        printStream.println("    { ");
        int length = dArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            double[] dArr2 = dArr[i2];
            printStream.print("        {");
            int length2 = dArr2.length;
            for (int i4 = 0; i4 < length2; i4++) {
                printStream.printf("%-25.25s", new Object[]{format(dArr2[i4])});
            }
            printStream.println("}, // " + i3);
            i2++;
            i3++;
        }
        printStream.println(TABLE_END_DECL);
    }

    static void printarray(PrintStream printStream, String str, int i, double[] dArr) {
        printStream.println(str + "=");
        checkLen(i, dArr.length);
        printStream.println(TABLE_START_DECL);
        int length = dArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            printStream.printf("        %s%n", new Object[]{format(dArr[i2])});
        }
        printStream.println(TABLE_END_DECL);
    }

    static String format(double d) {
        if (d != d) {
            return "Double.NaN,";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(d >= 0.0d ? "+" : "");
        sb.append(Double.toString(d));
        sb.append("d,");
        return sb.toString();
    }

    private static void checkLen(int i, int i2) throws DimensionMismatchException {
        if (i != i2) {
            throw new DimensionMismatchException(i2, i);
        }
    }
}
