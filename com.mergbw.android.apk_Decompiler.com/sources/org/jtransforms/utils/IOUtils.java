package org.jtransforms.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;
import java.util.Random;
import org.apache.commons.math3.util.FastMath;
import pl.edu.icm.jlargearrays.DoubleLargeArray;
import pl.edu.icm.jlargearrays.FloatLargeArray;

public class IOUtils {
    private static final String FF = "%.4f";

    private IOUtils() {
    }

    public static double computeRMSE(float f, float f2) {
        double d = (double) (f - f2);
        return FastMath.sqrt(d * d);
    }

    public static double computeRMSE(float[] fArr, float[] fArr2) {
        if (fArr.length == fArr2.length) {
            double d = 0.0d;
            for (int i = 0; i < fArr.length; i++) {
                double d2 = (double) (fArr[i] - fArr2[i]);
                d += d2 * d2;
            }
            return FastMath.sqrt(d / ((double) fArr.length));
        }
        throw new IllegalArgumentException("Arrays are not the same size");
    }

    public static double computeRMSE(FloatLargeArray floatLargeArray, FloatLargeArray floatLargeArray2) {
        if (floatLargeArray.length() == floatLargeArray2.length()) {
            double d = 0.0d;
            for (long j = 0; j < floatLargeArray.length(); j++) {
                double d2 = (double) (floatLargeArray.getFloat(j) - floatLargeArray2.getFloat(j));
                d += d2 * d2;
            }
            return FastMath.sqrt(d / ((double) floatLargeArray.length()));
        }
        throw new IllegalArgumentException("Arrays are not the same size.");
    }

    public static double computeRMSE(float[][] fArr, float[][] fArr2) {
        if (fArr.length == fArr2.length && fArr[0].length == fArr2[0].length) {
            double d = 0.0d;
            for (int i = 0; i < fArr.length; i++) {
                for (int i2 = 0; i2 < fArr[0].length; i2++) {
                    double d2 = (double) (fArr[i][i2] - fArr2[i][i2]);
                    d += d2 * d2;
                }
            }
            return FastMath.sqrt(d / ((double) (fArr.length * fArr[0].length)));
        }
        throw new IllegalArgumentException("Arrays are not the same size");
    }

    public static double computeRMSE(float[][][] fArr, float[][][] fArr2) {
        if (fArr.length == fArr2.length) {
            float[][] fArr3 = fArr[0];
            int length = fArr3.length;
            float[][] fArr4 = fArr2[0];
            if (length == fArr4.length && fArr3[0].length == fArr4[0].length) {
                double d = 0.0d;
                for (int i = 0; i < fArr.length; i++) {
                    for (int i2 = 0; i2 < fArr[0].length; i2++) {
                        for (int i3 = 0; i3 < fArr[0][0].length; i3++) {
                            double d2 = (double) (fArr[i][i2][i3] - fArr2[i][i2][i3]);
                            d += d2 * d2;
                        }
                    }
                }
                int length2 = fArr.length;
                float[][] fArr5 = fArr[0];
                return FastMath.sqrt(d / ((double) ((length2 * fArr5.length) * fArr5[0].length)));
            }
        }
        throw new IllegalArgumentException("Arrays are not the same size");
    }

    public static double computeRMSE(double d, double d2) {
        double d3 = d - d2;
        return FastMath.sqrt(d3 * d3);
    }

    public static double computeRMSE(double[] dArr, double[] dArr2) {
        if (dArr.length == dArr2.length) {
            double d = 0.0d;
            for (int i = 0; i < dArr.length; i++) {
                double d2 = dArr[i] - dArr2[i];
                d += d2 * d2;
            }
            return FastMath.sqrt(d / ((double) dArr.length));
        }
        throw new IllegalArgumentException("Arrays are not the same size");
    }

    public static double computeRMSE(DoubleLargeArray doubleLargeArray, DoubleLargeArray doubleLargeArray2) {
        if (doubleLargeArray.length() == doubleLargeArray2.length()) {
            double d = 0.0d;
            for (long j = 0; j < doubleLargeArray.length(); j++) {
                double d2 = doubleLargeArray.getDouble(j) - doubleLargeArray2.getDouble(j);
                d += d2 * d2;
            }
            return FastMath.sqrt(d / ((double) doubleLargeArray.length()));
        }
        throw new IllegalArgumentException("Arrays are not the same size.");
    }

    public static double computeRMSE(double[][] dArr, double[][] dArr2) {
        if (dArr.length == dArr2.length && dArr[0].length == dArr2[0].length) {
            double d = 0.0d;
            for (int i = 0; i < dArr.length; i++) {
                for (int i2 = 0; i2 < dArr[0].length; i2++) {
                    double d2 = dArr[i][i2] - dArr2[i][i2];
                    d += d2 * d2;
                }
            }
            return FastMath.sqrt(d / ((double) (dArr.length * dArr[0].length)));
        }
        throw new IllegalArgumentException("Arrays are not the same size");
    }

    public static double computeRMSE(double[][][] dArr, double[][][] dArr2) {
        if (dArr.length == dArr2.length) {
            double[][] dArr3 = dArr[0];
            int length = dArr3.length;
            double[][] dArr4 = dArr2[0];
            if (length == dArr4.length && dArr3[0].length == dArr4[0].length) {
                double d = 0.0d;
                for (int i = 0; i < dArr.length; i++) {
                    for (int i2 = 0; i2 < dArr[0].length; i2++) {
                        for (int i3 = 0; i3 < dArr[0][0].length; i3++) {
                            double d2 = dArr[i][i2][i3] - dArr2[i][i2][i3];
                            d += d2 * d2;
                        }
                    }
                }
                int length2 = dArr.length;
                double[][] dArr5 = dArr[0];
                return FastMath.sqrt(d / ((double) ((length2 * dArr5.length) * dArr5[0].length)));
            }
        }
        throw new IllegalArgumentException("Arrays are not the same size");
    }

    public static void fillMatrix_1D(long j, double[] dArr) {
        Random random = new Random(2);
        for (int i = 0; ((long) i) < j; i++) {
            dArr[i] = random.nextDouble();
        }
    }

    public static void fillMatrix_1D(long j, DoubleLargeArray doubleLargeArray) {
        Random random = new Random(2);
        for (long j2 = 0; j2 < j; j2++) {
            doubleLargeArray.setDouble(j2, random.nextDouble());
        }
    }

    public static void fillMatrix_1D(long j, FloatLargeArray floatLargeArray) {
        Random random = new Random(2);
        for (long j2 = 0; j2 < j; j2++) {
            floatLargeArray.setDouble(j2, (double) random.nextFloat());
        }
    }

    public static void fillMatrix_1D(long j, float[] fArr) {
        Random random = new Random(2);
        for (int i = 0; ((long) i) < j; i++) {
            fArr[i] = random.nextFloat();
        }
    }

    public static void fillMatrix_2D(long j, long j2, double[] dArr) {
        Random random = new Random(2);
        int i = 0;
        while (true) {
            long j3 = (long) i;
            if (j3 < j) {
                int i2 = 0;
                while (true) {
                    long j4 = (long) i2;
                    if (j4 >= j2) {
                        break;
                    }
                    dArr[(int) ((j3 * j2) + j4)] = random.nextDouble();
                    i2++;
                }
                i++;
            } else {
                return;
            }
        }
    }

    public static void fillMatrix_2D(long j, long j2, FloatLargeArray floatLargeArray) {
        Random random = new Random(2);
        for (long j3 = 0; j3 < j; j3++) {
            for (long j4 = 0; j4 < j2; j4++) {
                floatLargeArray.setFloat((j3 * j2) + j4, random.nextFloat());
            }
            FloatLargeArray floatLargeArray2 = floatLargeArray;
        }
    }

    public static void fillMatrix_2D(long j, long j2, DoubleLargeArray doubleLargeArray) {
        Random random = new Random(2);
        for (long j3 = 0; j3 < j; j3++) {
            for (long j4 = 0; j4 < j2; j4++) {
                doubleLargeArray.setDouble((j3 * j2) + j4, random.nextDouble());
            }
            DoubleLargeArray doubleLargeArray2 = doubleLargeArray;
        }
    }

    public static void fillMatrix_2D(long j, long j2, float[] fArr) {
        Random random = new Random(2);
        int i = 0;
        while (true) {
            long j3 = (long) i;
            if (j3 < j) {
                int i2 = 0;
                while (true) {
                    long j4 = (long) i2;
                    if (j4 >= j2) {
                        break;
                    }
                    fArr[(int) ((j3 * j2) + j4)] = random.nextFloat();
                    i2++;
                }
                i++;
            } else {
                return;
            }
        }
    }

    public static void fillMatrix_2D(long j, long j2, double[][] dArr) {
        Random random = new Random(2);
        for (int i = 0; ((long) i) < j; i++) {
            for (int i2 = 0; ((long) i2) < j2; i2++) {
                dArr[i][i2] = random.nextDouble();
            }
        }
    }

    public static void fillMatrix_2D(long j, long j2, float[][] fArr) {
        Random random = new Random(2);
        for (int i = 0; ((long) i) < j; i++) {
            for (int i2 = 0; ((long) i2) < j2; i2++) {
                fArr[i][i2] = random.nextFloat();
            }
        }
    }

    public static void fillMatrix_3D(long j, long j2, long j3, double[] dArr) {
        Random random = new Random(2);
        long j4 = j2 * j3;
        int i = 0;
        while (true) {
            long j5 = (long) i;
            if (j5 < j) {
                int i2 = 0;
                while (true) {
                    long j6 = (long) i2;
                    if (j6 >= j2) {
                        break;
                    }
                    int i3 = 0;
                    while (true) {
                        long j7 = (long) i3;
                        if (j7 >= j3) {
                            break;
                        }
                        dArr[(int) ((j5 * j4) + (j6 * j3) + j7)] = random.nextDouble();
                        i3++;
                    }
                    i2++;
                }
                i++;
            } else {
                return;
            }
        }
    }

    public static void fillMatrix_3D(long j, long j2, long j3, DoubleLargeArray doubleLargeArray) {
        Random random = new Random(2);
        long j4 = j2 * j3;
        for (long j5 = 0; j5 < j; j5++) {
            for (long j6 = 0; j6 < j2; j6++) {
                for (long j7 = 0; j7 < j3; j7++) {
                    doubleLargeArray.setDouble((j5 * j4) + (j6 * j3) + j7, random.nextDouble());
                }
                DoubleLargeArray doubleLargeArray2 = doubleLargeArray;
            }
            DoubleLargeArray doubleLargeArray3 = doubleLargeArray;
        }
    }

    public static void fillMatrix_3D(long j, long j2, long j3, FloatLargeArray floatLargeArray) {
        Random random = new Random(2);
        long j4 = j2 * j3;
        for (long j5 = 0; j5 < j; j5++) {
            for (long j6 = 0; j6 < j2; j6++) {
                for (long j7 = 0; j7 < j3; j7++) {
                    FloatLargeArray floatLargeArray2 = floatLargeArray;
                    floatLargeArray2.setDouble((j5 * j4) + (j6 * j3) + j7, (double) random.nextFloat());
                }
                FloatLargeArray floatLargeArray3 = floatLargeArray;
            }
            FloatLargeArray floatLargeArray4 = floatLargeArray;
        }
    }

    public static void fillMatrix_3D(long j, long j2, long j3, float[] fArr) {
        Random random = new Random(2);
        long j4 = j2 * j3;
        int i = 0;
        while (true) {
            long j5 = (long) i;
            if (j5 < j) {
                int i2 = 0;
                while (true) {
                    long j6 = (long) i2;
                    if (j6 >= j2) {
                        break;
                    }
                    int i3 = 0;
                    while (true) {
                        long j7 = (long) i3;
                        if (j7 >= j3) {
                            break;
                        }
                        fArr[(int) ((j5 * j4) + (j6 * j3) + j7)] = random.nextFloat();
                        i3++;
                    }
                    i2++;
                }
                i++;
            } else {
                return;
            }
        }
    }

    public static void fillMatrix_3D(long j, long j2, long j3, double[][][] dArr) {
        Random random = new Random(2);
        for (int i = 0; ((long) i) < j; i++) {
            for (int i2 = 0; ((long) i2) < j2; i2++) {
                for (int i3 = 0; ((long) i3) < j3; i3++) {
                    dArr[i][i2][i3] = random.nextDouble();
                }
            }
        }
    }

    public static void fillMatrix_3D(long j, long j2, long j3, float[][][] fArr) {
        Random random = new Random(2);
        for (int i = 0; ((long) i) < j; i++) {
            for (int i2 = 0; ((long) i2) < j2; i2++) {
                for (int i3 = 0; ((long) i3) < j3; i3++) {
                    fArr[i][i2][i3] = random.nextFloat();
                }
            }
        }
    }

    public static void showComplex_1D(double[] dArr, String str) {
        System.out.println(str);
        System.out.println("-------------------");
        for (int i = 0; i < dArr.length; i += 2) {
            int i2 = i + 1;
            double d = dArr[i2];
            if (d == 0.0d) {
                System.out.println(String.format(FF, new Object[]{Double.valueOf(dArr[i])}));
            } else if (dArr[i] == 0.0d) {
                System.out.println(String.format(FF, new Object[]{Double.valueOf(dArr[i2])}) + "i");
            } else if (d < 0.0d) {
                System.out.println(String.format(FF, new Object[]{Double.valueOf(dArr[i])}) + " - " + String.format(FF, new Object[]{Double.valueOf(-dArr[i2])}) + "i");
            } else {
                System.out.println(String.format(FF, new Object[]{Double.valueOf(dArr[i])}) + " + " + String.format(FF, new Object[]{Double.valueOf(dArr[i2])}) + "i");
            }
        }
        System.out.println();
    }

    public static void showComplex_2D(int i, int i2, double[] dArr, String str) {
        StringBuilder sb = new StringBuilder(String.format(str + ": complex array 2D: %d rows, %d columns\n\n", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
        int i3 = i;
        for (int i4 = 0; i4 < i3; i4++) {
            for (int i5 = 0; i5 < i2 * 2; i5 += 2) {
                int i6 = (i4 * 2 * i2) + i5;
                int i7 = i6 + 1;
                double d = dArr[i7];
                if (d == 0.0d) {
                    sb.append(String.format("%.4f\t", new Object[]{Double.valueOf(dArr[i6])}));
                } else {
                    double d2 = dArr[i6];
                    if (d2 == 0.0d) {
                        sb.append(String.format("%.4fi\t", new Object[]{Double.valueOf(d)}));
                    } else if (d < 0.0d) {
                        sb.append(String.format("%.4f - %.4fi\t", new Object[]{Double.valueOf(d2), Double.valueOf(-dArr[i7])}));
                    } else {
                        sb.append(String.format("%.4f + %.4fi\t", new Object[]{Double.valueOf(d2), Double.valueOf(dArr[i7])}));
                    }
                }
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void showComplex_2D(double[][] dArr, String str) {
        double[][] dArr2 = dArr;
        int length = dArr2.length;
        int length2 = dArr2[0].length;
        StringBuilder sb = new StringBuilder(String.format(str + ": complex array 2D: %d rows, %d columns\n\n", new Object[]{Integer.valueOf(length), Integer.valueOf(length2)}));
        for (int i = 0; i < length; i++) {
            for (int i2 = 0; i2 < length2; i2 += 2) {
                double[] dArr3 = dArr2[i];
                int i3 = i2 + 1;
                double d = dArr3[i3];
                if (d == 0.0d) {
                    sb.append(String.format("%.4f\t", new Object[]{Double.valueOf(dArr3[i2])}));
                } else {
                    double d2 = dArr3[i2];
                    if (d2 == 0.0d) {
                        sb.append(String.format("%.4fi\t", new Object[]{Double.valueOf(d)}));
                    } else if (d < 0.0d) {
                        sb.append(String.format("%.4f - %.4fi\t", new Object[]{Double.valueOf(d2), Double.valueOf(-dArr2[i][i3])}));
                    } else {
                        sb.append(String.format("%.4f + %.4fi\t", new Object[]{Double.valueOf(d2), Double.valueOf(dArr2[i][i3])}));
                    }
                }
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void showComplex_3D(int i, int i2, int i3, double[] dArr, String str) {
        int i4 = i2;
        int i5 = i4 * 2 * i3;
        int i6 = i3 * 2;
        System.out.println(str);
        System.out.println("-------------------");
        int i7 = 0;
        while (i7 < i6) {
            System.out.println("(:,:," + (i7 / 2) + ")=\n");
            int i8 = i;
            int i9 = 0;
            while (i9 < i8) {
                int i10 = 0;
                while (i10 < i4) {
                    int i11 = (i9 * i5) + (i10 * i6) + i7;
                    int i12 = i11 + 1;
                    double d = dArr[i12];
                    if (d == 0.0d) {
                        System.out.print(String.format(FF, new Object[]{Double.valueOf(dArr[i11])}) + "\t");
                    } else if (dArr[i11] == 0.0d) {
                        System.out.print(String.format(FF, new Object[]{Double.valueOf(dArr[i12])}) + "i\t");
                    } else if (d < 0.0d) {
                        System.out.print(String.format(FF, new Object[]{Double.valueOf(dArr[i11])}) + " - " + String.format(FF, new Object[]{Double.valueOf(-dArr[i12])}) + "i\t");
                    } else {
                        System.out.print(String.format(FF, new Object[]{Double.valueOf(dArr[i11])}) + " + " + String.format(FF, new Object[]{Double.valueOf(dArr[i12])}) + "i\t");
                    }
                    i10++;
                    i4 = i2;
                }
                System.out.println("");
                i9++;
                i4 = i2;
            }
            i7 += 2;
            i4 = i2;
        }
        System.out.println("");
    }

    public static void showComplex_3D(double[][][] dArr, String str) {
        int i;
        int i2;
        int i3;
        double[][][] dArr2 = dArr;
        System.out.println(str);
        System.out.println("-------------------");
        int length = dArr2.length;
        int i4 = 0;
        double[][] dArr3 = dArr2[0];
        int length2 = dArr3.length;
        int length3 = dArr3[0].length;
        for (int i5 = 0; i5 < length3; i5 += 2) {
            System.out.println("(:,:," + (i5 / 2) + ")=\n");
            int i6 = i4;
            while (i6 < length) {
                int i7 = i4;
                while (i7 < length2) {
                    double[] dArr4 = dArr2[i6][i7];
                    int i8 = i5 + 1;
                    double d = dArr4[i8];
                    if (d == 0.0d) {
                        PrintStream printStream = System.out;
                        StringBuilder sb = new StringBuilder();
                        Object[] objArr = new Object[1];
                        objArr[i4] = Double.valueOf(dArr2[i6][i7][i5]);
                        sb.append(String.format(FF, objArr));
                        sb.append("\t");
                        printStream.print(sb.toString());
                    } else if (dArr4[i5] == 0.0d) {
                        PrintStream printStream2 = System.out;
                        StringBuilder sb2 = new StringBuilder();
                        Object[] objArr2 = new Object[1];
                        objArr2[i4] = Double.valueOf(dArr2[i6][i7][i8]);
                        sb2.append(String.format(FF, objArr2));
                        sb2.append("i\t");
                        printStream2.print(sb2.toString());
                    } else {
                        if (d < 0.0d) {
                            PrintStream printStream3 = System.out;
                            StringBuilder sb3 = new StringBuilder();
                            i = length;
                            Object[] objArr3 = new Object[1];
                            objArr3[i4] = Double.valueOf(dArr2[i6][i7][i5]);
                            sb3.append(String.format(FF, objArr3));
                            sb3.append(" - ");
                            i2 = length3;
                            sb3.append(String.format(FF, new Object[]{Double.valueOf(-dArr2[i6][i7][i8])}));
                            sb3.append("i\t");
                            printStream3.print(sb3.toString());
                            i3 = 0;
                        } else {
                            i = length;
                            i2 = length3;
                            PrintStream printStream4 = System.out;
                            StringBuilder sb4 = new StringBuilder();
                            i3 = 0;
                            sb4.append(String.format(FF, new Object[]{Double.valueOf(dArr2[i6][i7][i5])}));
                            sb4.append(" + ");
                            sb4.append(String.format(FF, new Object[]{Double.valueOf(dArr2[i6][i7][i8])}));
                            sb4.append("i\t");
                            printStream4.print(sb4.toString());
                        }
                        i7++;
                        i4 = i3;
                        length3 = i2;
                        length = i;
                    }
                    i = length;
                    i3 = i4;
                    i2 = length3;
                    i7++;
                    i4 = i3;
                    length3 = i2;
                    length = i;
                }
                int i9 = i4;
                int i10 = length3;
                System.out.println("");
                i6++;
                length = length;
            }
            int i11 = length;
            int i12 = i4;
            int i13 = length3;
        }
        System.out.println("");
    }

    public static void showComplex_3D(int i, int i2, int i3, float[] fArr, String str) {
        int i4 = i2;
        int i5 = i4 * 2 * i3;
        int i6 = i3 * 2;
        System.out.println(str);
        System.out.println("-------------------");
        int i7 = 0;
        while (i7 < i6) {
            System.out.println("(:,:," + (i7 / 2) + ")=\n");
            int i8 = i;
            int i9 = 0;
            while (i9 < i8) {
                int i10 = 0;
                while (i10 < i4) {
                    int i11 = (i9 * i5) + (i10 * i6) + i7;
                    int i12 = i11 + 1;
                    float f = fArr[i12];
                    if (f == 0.0f) {
                        System.out.print(String.format(FF, new Object[]{Float.valueOf(fArr[i11])}) + "\t");
                    } else if (fArr[i11] == 0.0f) {
                        System.out.print(String.format(FF, new Object[]{Float.valueOf(fArr[i12])}) + "i\t");
                    } else if (f < 0.0f) {
                        System.out.print(String.format(FF, new Object[]{Float.valueOf(fArr[i11])}) + " - " + String.format(FF, new Object[]{Float.valueOf(-fArr[i12])}) + "i\t");
                    } else {
                        System.out.print(String.format(FF, new Object[]{Float.valueOf(fArr[i11])}) + " + " + String.format(FF, new Object[]{Float.valueOf(fArr[i12])}) + "i\t");
                    }
                    i10++;
                    i4 = i2;
                }
                System.out.println("");
                i9++;
                i4 = i2;
            }
            i7 += 2;
            i4 = i2;
        }
        System.out.println("");
    }

    public static void showReal_1D(double[] dArr, String str) {
        System.out.println(str);
        System.out.println("-------------------");
        for (int i = 0; i < dArr.length; i++) {
            System.out.println(String.format(FF, new Object[]{Double.valueOf(dArr[i])}));
        }
        System.out.println();
    }

    public static void showReal_2D(int i, int i2, double[] dArr, String str) {
        System.out.println(str);
        System.out.println("-------------------");
        for (int i3 = 0; i3 < i; i3++) {
            for (int i4 = 0; i4 < i2; i4++) {
                int i5 = (i3 * i2) + i4;
                if (FastMath.abs(dArr[i5]) < 5.0E-5d) {
                    System.out.print("0\t");
                } else {
                    System.out.print(String.format(FF, new Object[]{Double.valueOf(dArr[i5])}) + "\t");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void showReal_3D(int i, int i2, int i3, double[] dArr, String str) {
        int i4 = i2 * i3;
        System.out.println(str);
        System.out.println("-------------------");
        for (int i5 = 0; i5 < i3; i5++) {
            System.out.println();
            System.out.println("(:,:," + i5 + ")=\n");
            for (int i6 = 0; i6 < i; i6++) {
                for (int i7 = 0; i7 < i2; i7++) {
                    int i8 = (i6 * i4) + (i7 * i3) + i5;
                    if (FastMath.abs(dArr[i8]) <= 5.0E-5d) {
                        System.out.print("0\t");
                    } else {
                        System.out.print(String.format(FF, new Object[]{Double.valueOf(dArr[i8])}) + "\t");
                    }
                }
                System.out.println();
            }
        }
        System.out.println();
    }

    public static void showReal_3D(double[][][] dArr, String str) {
        System.out.println(str);
        System.out.println("-------------------");
        int length = dArr.length;
        double[][] dArr2 = dArr[0];
        int length2 = dArr2.length;
        int length3 = dArr2[0].length;
        for (int i = 0; i < length3; i++) {
            System.out.println();
            System.out.println("(:,:," + i + ")=\n");
            for (int i2 = 0; i2 < length; i2++) {
                for (int i3 = 0; i3 < length2; i3++) {
                    if (FastMath.abs(dArr[i2][i3][i]) <= 5.0E-5d) {
                        System.out.print("0\t");
                    } else {
                        System.out.print(String.format(FF, new Object[]{Double.valueOf(dArr[i2][i3][i])}) + "\t");
                    }
                }
                System.out.println();
            }
        }
        System.out.println();
    }

    public static void writeToFileComplex_1D(double[] dArr, String str) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(str));
            for (int i = 0; i < dArr.length; i += 2) {
                int i2 = i + 1;
                double d = dArr[i2];
                if (d == 0.0d) {
                    bufferedWriter.write(String.format(FF, new Object[]{Double.valueOf(dArr[i])}));
                    bufferedWriter.newLine();
                } else if (dArr[i] == 0.0d) {
                    bufferedWriter.write(String.format(FF, new Object[]{Double.valueOf(dArr[i2])}) + "i");
                    bufferedWriter.newLine();
                } else if (d < 0.0d) {
                    bufferedWriter.write(String.format(FF, new Object[]{Double.valueOf(dArr[i])}) + " - " + String.format(FF, new Object[]{Double.valueOf(-dArr[i2])}) + "i");
                    bufferedWriter.newLine();
                } else {
                    bufferedWriter.write(String.format(FF, new Object[]{Double.valueOf(dArr[i])}) + " + " + String.format(FF, new Object[]{Double.valueOf(dArr[i2])}) + "i");
                    bufferedWriter.newLine();
                }
            }
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFileComplex_1D(float[] fArr, String str) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(str));
            for (int i = 0; i < fArr.length; i += 2) {
                int i2 = i + 1;
                float f = fArr[i2];
                if (f == 0.0f) {
                    bufferedWriter.write(String.format(FF, new Object[]{Float.valueOf(fArr[i])}));
                    bufferedWriter.newLine();
                } else if (fArr[i] == 0.0f) {
                    bufferedWriter.write(String.format(FF, new Object[]{Float.valueOf(fArr[i2])}) + "i");
                    bufferedWriter.newLine();
                } else if (f < 0.0f) {
                    bufferedWriter.write(String.format(FF, new Object[]{Float.valueOf(fArr[i])}) + " - " + String.format(FF, new Object[]{Float.valueOf(-fArr[i2])}) + "i");
                    bufferedWriter.newLine();
                } else {
                    bufferedWriter.write(String.format(FF, new Object[]{Float.valueOf(fArr[i])}) + " + " + String.format(FF, new Object[]{Float.valueOf(fArr[i2])}) + "i");
                    bufferedWriter.newLine();
                }
            }
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFileComplex_2D(int i, int i2, double[] dArr, String str) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(str));
            int i3 = i;
            for (int i4 = 0; i4 < i3; i4++) {
                int i5 = 0;
                while (i5 < i2 * 2) {
                    int i6 = (i4 * 2 * i2) + i5;
                    if (FastMath.abs(dArr[i6]) < 5.0E-5d) {
                        int i7 = i6 + 1;
                        if (FastMath.abs(dArr[i7]) < 5.0E-5d) {
                            if (dArr[i7] >= 0.0d) {
                                bufferedWriter.write("0 + 0i\t");
                            } else {
                                bufferedWriter.write("0 - 0i\t");
                            }
                            i5 += 2;
                        }
                    }
                    int i8 = i6 + 1;
                    if (FastMath.abs(dArr[i8]) < 5.0E-5d) {
                        if (dArr[i8] >= 0.0d) {
                            bufferedWriter.write(String.format(FF, new Object[]{Double.valueOf(dArr[i6])}) + " + 0i\t");
                        } else {
                            bufferedWriter.write(String.format(FF, new Object[]{Double.valueOf(dArr[i6])}) + " - 0i\t");
                        }
                        i5 += 2;
                    } else {
                        if (FastMath.abs(dArr[i6]) < 5.0E-5d) {
                            if (dArr[i8] >= 0.0d) {
                                bufferedWriter.write("0 + " + String.format(FF, new Object[]{Double.valueOf(dArr[i8])}) + "i\t");
                            } else {
                                bufferedWriter.write("0 - " + String.format(FF, new Object[]{Double.valueOf(-dArr[i8])}) + "i\t");
                            }
                        } else if (dArr[i8] < 0.0d) {
                            bufferedWriter.write(String.format(FF, new Object[]{Double.valueOf(dArr[i6])}) + " - " + String.format(FF, new Object[]{Double.valueOf(-dArr[i8])}) + "i\t");
                        } else {
                            bufferedWriter.write(String.format(FF, new Object[]{Double.valueOf(dArr[i6])}) + " + " + String.format(FF, new Object[]{Double.valueOf(dArr[i8])}) + "i\t");
                        }
                        i5 += 2;
                    }
                }
                bufferedWriter.newLine();
            }
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFileComplex_2D(int i, int i2, float[] fArr, String str) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(str));
            int i3 = i;
            for (int i4 = 0; i4 < i3; i4++) {
                int i5 = 0;
                while (i5 < i2 * 2) {
                    int i6 = (i4 * 2 * i2) + i5;
                    if (((double) FastMath.abs(fArr[i6])) < 5.0E-5d) {
                        int i7 = i6 + 1;
                        if (((double) FastMath.abs(fArr[i7])) < 5.0E-5d) {
                            if (((double) fArr[i7]) >= 0.0d) {
                                bufferedWriter.write("0 + 0i\t");
                            } else {
                                bufferedWriter.write("0 - 0i\t");
                            }
                            i5 += 2;
                        }
                    }
                    int i8 = i6 + 1;
                    if (((double) FastMath.abs(fArr[i8])) < 5.0E-5d) {
                        if (((double) fArr[i8]) >= 0.0d) {
                            bufferedWriter.write(String.format(FF, new Object[]{Float.valueOf(fArr[i6])}) + " + 0i\t");
                        } else {
                            bufferedWriter.write(String.format(FF, new Object[]{Float.valueOf(fArr[i6])}) + " - 0i\t");
                        }
                        i5 += 2;
                    } else {
                        if (((double) FastMath.abs(fArr[i6])) < 5.0E-5d) {
                            if (((double) fArr[i8]) >= 0.0d) {
                                bufferedWriter.write("0 + " + String.format(FF, new Object[]{Float.valueOf(fArr[i8])}) + "i\t");
                            } else {
                                bufferedWriter.write("0 - " + String.format(FF, new Object[]{Float.valueOf(-fArr[i8])}) + "i\t");
                            }
                        } else if (fArr[i8] < 0.0f) {
                            bufferedWriter.write(String.format(FF, new Object[]{Float.valueOf(fArr[i6])}) + " - " + String.format(FF, new Object[]{Float.valueOf(-fArr[i8])}) + "i\t");
                        } else {
                            bufferedWriter.write(String.format(FF, new Object[]{Float.valueOf(fArr[i6])}) + " + " + String.format(FF, new Object[]{Float.valueOf(fArr[i8])}) + "i\t");
                        }
                        i5 += 2;
                    }
                }
                bufferedWriter.newLine();
            }
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFileComplex_2D(double[][] dArr, String str) {
        double[][] dArr2 = dArr;
        int length = dArr2.length;
        int length2 = dArr2[0].length;
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(str));
            for (int i = 0; i < length; i++) {
                int i2 = 0;
                while (i2 < length2 * 2) {
                    if (FastMath.abs(dArr2[i][i2]) < 5.0E-5d) {
                        int i3 = i2 + 1;
                        if (FastMath.abs(dArr2[i][i3]) < 5.0E-5d) {
                            if (dArr2[i][i3] >= 0.0d) {
                                bufferedWriter.write("0 + 0i\t");
                            } else {
                                bufferedWriter.write("0 - 0i\t");
                            }
                            i2 += 2;
                        }
                    }
                    int i4 = i2 + 1;
                    if (FastMath.abs(dArr2[i][i4]) < 5.0E-5d) {
                        if (dArr2[i][i4] >= 0.0d) {
                            bufferedWriter.write(String.format(FF, new Object[]{Double.valueOf(dArr2[i][i2])}) + " + 0i\t");
                        } else {
                            bufferedWriter.write(String.format(FF, new Object[]{Double.valueOf(dArr2[i][i2])}) + " - 0i\t");
                        }
                        i2 += 2;
                    } else {
                        if (FastMath.abs(dArr2[i][i2]) < 5.0E-5d) {
                            if (dArr2[i][i4] >= 0.0d) {
                                bufferedWriter.write("0 + " + String.format(FF, new Object[]{Double.valueOf(dArr2[i][i4])}) + "i\t");
                            } else {
                                bufferedWriter.write("0 - " + String.format(FF, new Object[]{Double.valueOf(-dArr2[i][i4])}) + "i\t");
                            }
                        } else if (dArr2[i][i4] < 0.0d) {
                            bufferedWriter.write(String.format(FF, new Object[]{Double.valueOf(dArr2[i][i2])}) + " - " + String.format(FF, new Object[]{Double.valueOf(-dArr2[i][i4])}) + "i\t");
                        } else {
                            bufferedWriter.write(String.format(FF, new Object[]{Double.valueOf(dArr2[i][i2])}) + " + " + String.format(FF, new Object[]{Double.valueOf(dArr2[i][i4])}) + "i\t");
                        }
                        i2 += 2;
                    }
                }
                bufferedWriter.newLine();
            }
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFileComplex_3D(int i, int i2, int i3, double[] dArr, String str) {
        int i4 = i2;
        int i5 = i4 * i3 * 2;
        int i6 = i3 * 2;
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(str));
            for (int i7 = 0; i7 < i6; i7 += 2) {
                bufferedWriter.newLine();
                bufferedWriter.write("(:,:," + (i7 / 2) + ")=");
                bufferedWriter.newLine();
                bufferedWriter.newLine();
                int i8 = i;
                for (int i9 = 0; i9 < i8; i9++) {
                    for (int i10 = 0; i10 < i4; i10++) {
                        int i11 = (i9 * i5) + (i10 * i6) + i7;
                        int i12 = i11 + 1;
                        double d = dArr[i12];
                        if (d == 0.0d) {
                            bufferedWriter.write(String.format(FF, new Object[]{Double.valueOf(dArr[i11])}) + "\t");
                        } else if (dArr[i11] == 0.0d) {
                            bufferedWriter.write(String.format(FF, new Object[]{Double.valueOf(dArr[i12])}) + "i\t");
                        } else if (d < 0.0d) {
                            bufferedWriter.write(String.format(FF, new Object[]{Double.valueOf(dArr[i11])}) + " - " + String.format(FF, new Object[]{Double.valueOf(-dArr[i12])}) + "i\t");
                        } else {
                            bufferedWriter.write(String.format(FF, new Object[]{Double.valueOf(dArr[i11])}) + " + " + String.format(FF, new Object[]{Double.valueOf(dArr[i12])}) + "i\t");
                        }
                    }
                    bufferedWriter.newLine();
                }
            }
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFileComplex_3D(double[][][] dArr, String str) {
        double[][][] dArr2 = dArr;
        int length = dArr2.length;
        double[][] dArr3 = dArr2[0];
        int length2 = dArr3.length;
        int length3 = dArr3[0].length;
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(str));
            for (int i = 0; i < length3 * 2; i += 2) {
                bufferedWriter.newLine();
                bufferedWriter.write("(:,:," + (i / 2) + ")=");
                bufferedWriter.newLine();
                bufferedWriter.newLine();
                for (int i2 = 0; i2 < length; i2++) {
                    for (int i3 = 0; i3 < length2; i3++) {
                        double[] dArr4 = dArr2[i2][i3];
                        int i4 = i + 1;
                        double d = dArr4[i4];
                        if (d == 0.0d) {
                            bufferedWriter.write(String.format(FF, new Object[]{Double.valueOf(dArr2[i2][i3][i])}) + "\t");
                        } else if (dArr4[i] == 0.0d) {
                            bufferedWriter.write(String.format(FF, new Object[]{Double.valueOf(dArr2[i2][i3][i4])}) + "i\t");
                        } else if (d < 0.0d) {
                            bufferedWriter.write(String.format(FF, new Object[]{Double.valueOf(dArr2[i2][i3][i])}) + " - " + String.format(FF, new Object[]{Double.valueOf(-dArr2[i2][i3][i4])}) + "i\t");
                        } else {
                            bufferedWriter.write(String.format(FF, new Object[]{Double.valueOf(dArr2[i2][i3][i])}) + " + " + String.format(FF, new Object[]{Double.valueOf(dArr2[i2][i3][i4])}) + "i\t");
                        }
                    }
                    bufferedWriter.newLine();
                }
            }
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFileReal_1D(double[] dArr, String str) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(str));
            for (int i = 0; i < dArr.length; i++) {
                bufferedWriter.write(String.format(FF, new Object[]{Double.valueOf(dArr[i])}));
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFileReal_1D(float[] fArr, String str) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(str));
            for (int i = 0; i < fArr.length; i++) {
                bufferedWriter.write(String.format(FF, new Object[]{Float.valueOf(fArr[i])}));
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFileReal_2D(int i, int i2, double[] dArr, String str) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(str));
            for (int i3 = 0; i3 < i; i3++) {
                for (int i4 = 0; i4 < i2; i4++) {
                    int i5 = (i3 * i2) + i4;
                    if (FastMath.abs(dArr[i5]) < 5.0E-5d) {
                        bufferedWriter.write("0\t");
                    } else {
                        bufferedWriter.write(String.format(FF, new Object[]{Double.valueOf(dArr[i5])}) + "\t");
                    }
                }
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFileReal_2D(int i, int i2, float[] fArr, String str) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(str));
            for (int i3 = 0; i3 < i; i3++) {
                for (int i4 = 0; i4 < i2; i4++) {
                    int i5 = (i3 * i2) + i4;
                    if (((double) FastMath.abs(fArr[i5])) < 5.0E-5d) {
                        bufferedWriter.write("0\t");
                    } else {
                        bufferedWriter.write(String.format(FF, new Object[]{Float.valueOf(fArr[i5])}) + "\t");
                    }
                }
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFileReal_3D(int i, int i2, int i3, double[] dArr, String str) {
        int i4 = i2 * i3;
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(str));
            for (int i5 = 0; i5 < i3; i5++) {
                bufferedWriter.newLine();
                bufferedWriter.write("(:,:," + i5 + ")=");
                bufferedWriter.newLine();
                bufferedWriter.newLine();
                for (int i6 = 0; i6 < i; i6++) {
                    for (int i7 = 0; i7 < i2; i7++) {
                        bufferedWriter.write(String.format(FF, new Object[]{Double.valueOf(dArr[(i6 * i4) + (i7 * i3) + i5])}) + "\t");
                    }
                    bufferedWriter.newLine();
                }
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFFTBenchmarkResultsToFile(String str, int i, int i2, boolean z, boolean z2, long[] jArr, double[] dArr, double[] dArr2) {
        long[] jArr2 = jArr;
        double[] dArr3 = dArr;
        double[] dArr4 = dArr2;
        String str2 = "\tscaling performed = ";
        String[] strArr = {"os.name", "os.version", "os.arch", "java.vendor", "java.version"};
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(str, false));
            bufferedWriter.write(new Date().toString());
            bufferedWriter.newLine();
            bufferedWriter.write("System properties:");
            bufferedWriter.newLine();
            bufferedWriter.write("\tos.name = " + System.getProperty(strArr[0]));
            bufferedWriter.newLine();
            bufferedWriter.write("\tos.version = " + System.getProperty(strArr[1]));
            bufferedWriter.newLine();
            bufferedWriter.write("\tos.arch = " + System.getProperty(strArr[2]));
            bufferedWriter.newLine();
            bufferedWriter.write("\tjava.vendor = " + System.getProperty(strArr[3]));
            bufferedWriter.newLine();
            bufferedWriter.write("\tjava.version = " + System.getProperty(strArr[4]));
            bufferedWriter.newLine();
            bufferedWriter.write("\tavailable processors = " + Runtime.getRuntime().availableProcessors());
            bufferedWriter.newLine();
            bufferedWriter.write("Settings:");
            bufferedWriter.newLine();
            bufferedWriter.write("\tused processors = " + i);
            bufferedWriter.newLine();
            bufferedWriter.write("\tTHREADS_BEGIN_N_2D = " + CommonUtils.getThreadsBeginN_2D());
            bufferedWriter.newLine();
            bufferedWriter.write("\tTHREADS_BEGIN_N_3D = " + CommonUtils.getThreadsBeginN_3D());
            bufferedWriter.newLine();
            bufferedWriter.write("\tnumber of iterations = " + i2);
            bufferedWriter.newLine();
            bufferedWriter.write("\twarm-up performed = " + z);
            bufferedWriter.newLine();
            bufferedWriter.write(str2 + z2);
            bufferedWriter.newLine();
            bufferedWriter.write("--------------------------------------------------------------------------------------------------");
            bufferedWriter.newLine();
            bufferedWriter.write("sizes=[");
            long[] jArr3 = jArr;
            for (int i3 = 0; i3 < jArr3.length; i3++) {
                bufferedWriter.write(Long.toString(jArr3[i3]));
                if (i3 < jArr3.length - 1) {
                    bufferedWriter.write(", ");
                } else {
                    bufferedWriter.write("]");
                }
            }
            bufferedWriter.newLine();
            bufferedWriter.write("times without constructor(in msec)=[");
            double[] dArr5 = dArr;
            for (int i4 = 0; i4 < dArr5.length; i4++) {
                bufferedWriter.write(String.format("%.2f", new Object[]{Double.valueOf(dArr5[i4])}));
                if (i4 < dArr5.length - 1) {
                    bufferedWriter.write(", ");
                } else {
                    bufferedWriter.write("]");
                }
            }
            bufferedWriter.newLine();
            bufferedWriter.write("times with constructor(in msec)=[");
            for (int i5 = 0; i5 < dArr5.length; i5++) {
                double[] dArr6 = dArr2;
                bufferedWriter.write(String.format("%.2f", new Object[]{Double.valueOf(dArr6[i5])}));
                if (i5 < dArr6.length - 1) {
                    bufferedWriter.write(", ");
                } else {
                    bufferedWriter.write("]");
                }
            }
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
