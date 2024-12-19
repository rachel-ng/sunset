package pl.edu.icm.jlargearrays;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.Random;
import org.apache.commons.math3.geometry.VectorFormat;

public class Benchmark {
    private static void writeToFile(long[] jArr, int[] iArr, double[][] dArr, String str) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(str));
            bufferedWriter.write(System.getProperty("os.name") + " " + System.getProperty("os.arch") + " " + System.getProperty("os.version"));
            bufferedWriter.newLine();
            StringBuilder sb = new StringBuilder();
            sb.append(System.getProperty("java.vendor"));
            sb.append(" ");
            sb.append(System.getProperty("java.version"));
            bufferedWriter.write(sb.toString());
            bufferedWriter.newLine();
            bufferedWriter.write("Available processors (cores): " + Runtime.getRuntime().availableProcessors());
            bufferedWriter.newLine();
            bufferedWriter.write("Total memory (bytes): " + Runtime.getRuntime().totalMemory());
            bufferedWriter.newLine();
            bufferedWriter.write("Number of threads: {");
            for (int i = 0; i < iArr.length; i++) {
                if (i < iArr.length - 1) {
                    bufferedWriter.write(iArr[i] + ",");
                } else {
                    bufferedWriter.write(iArr[iArr.length - 1] + VectorFormat.DEFAULT_SUFFIX);
                }
            }
            bufferedWriter.newLine();
            bufferedWriter.write("Sizes: {");
            for (int i2 = 0; i2 < jArr.length; i2++) {
                if (i2 < jArr.length - 1) {
                    bufferedWriter.write(jArr[i2] + ",");
                } else {
                    bufferedWriter.write(jArr[jArr.length - 1] + VectorFormat.DEFAULT_SUFFIX);
                }
            }
            bufferedWriter.newLine();
            bufferedWriter.write("Timings: {");
            for (int i3 = 0; i3 < iArr.length; i3++) {
                bufferedWriter.write(VectorFormat.DEFAULT_PREFIX);
                if (i3 < iArr.length - 1) {
                    for (int i4 = 0; i4 < jArr.length; i4++) {
                        if (i4 < jArr.length - 1) {
                            bufferedWriter.write(dArr[i3][i4] + ",");
                        } else {
                            bufferedWriter.write(dArr[i3][i4] + "},");
                        }
                    }
                    bufferedWriter.newLine();
                } else {
                    for (int i5 = 0; i5 < jArr.length; i5++) {
                        if (i5 < jArr.length - 1) {
                            bufferedWriter.write(dArr[i3][i5] + ",");
                        } else {
                            bufferedWriter.write(dArr[i3][i5] + "}}");
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static double[][] benchmarkJavaArraysByteSequential(long[] jArr, int[] iArr, int i, String str) {
        Object obj;
        int i2;
        long j;
        long[] jArr2 = jArr;
        int[] iArr2 = iArr;
        int i3 = i;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            Object obj2 = null;
            if (i5 >= jArr2.length) {
                int length = iArr2.length;
                int[] iArr3 = new int[2];
                iArr3[1] = jArr2.length;
                iArr3[0] = length;
                double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr3);
                System.out.println("Benchmarking java arrays (bytes, sequentual)");
                int i6 = 0;
                while (i6 < iArr2.length) {
                    int i7 = iArr2[i6];
                    Thread[] threadArr = new Thread[i7];
                    System.out.println("\tNumber of threads = " + i7);
                    int i8 = i4;
                    while (i8 < jArr2.length) {
                        System.out.print("\tSize = " + jArr2[i8]);
                        final byte[] bArr = new byte[((int) jArr2[i8])];
                        double nanoTime = (double) System.nanoTime();
                        int i9 = i4;
                        while (i9 < i3) {
                            double[][] dArr2 = dArr;
                            long j2 = jArr2[i8] / ((long) i7);
                            int i10 = 0;
                            while (i10 < i7) {
                                final int i11 = (int) (((long) i10) * j2);
                                if (i10 == i7 - 1) {
                                    i2 = i6;
                                    j = jArr2[i8];
                                } else {
                                    i2 = i6;
                                    j = ((long) i11) + j2;
                                }
                                final int i12 = (int) j;
                                Thread thread = new Thread(new Runnable() {
                                    public void run() {
                                        for (int i = i11; i < i12; i++) {
                                            byte[] bArr = bArr;
                                            bArr[i] = 1;
                                            bArr[i] = (byte) (1 + 1);
                                        }
                                    }
                                });
                                threadArr[i10] = thread;
                                thread.start();
                                i10++;
                                i6 = i2;
                            }
                            int i13 = i6;
                            int i14 = 0;
                            while (i14 < i7) {
                                try {
                                    threadArr[i14].join();
                                    obj = null;
                                } catch (Exception e) {
                                    e = e;
                                    obj = null;
                                    e.printStackTrace();
                                    i9++;
                                    obj2 = obj;
                                    dArr = dArr2;
                                    i6 = i13;
                                }
                                try {
                                    threadArr[i14] = null;
                                    i14++;
                                } catch (Exception e2) {
                                    e = e2;
                                    e.printStackTrace();
                                    i9++;
                                    obj2 = obj;
                                    dArr = dArr2;
                                    i6 = i13;
                                }
                            }
                            obj = null;
                            i9++;
                            obj2 = obj;
                            dArr = dArr2;
                            i6 = i13;
                        }
                        double[][] dArr3 = dArr;
                        int i15 = i6;
                        dArr3[i15][i8] = ((((double) System.nanoTime()) - nanoTime) / 1.0E9d) / ((double) i3);
                        System.out.println(" : " + String.format("%.7f sec", new Object[]{Double.valueOf(dArr3[i15][i8])}));
                        i8++;
                        obj2 = obj2;
                        i4 = 0;
                        dArr = dArr3;
                        i6 = i15;
                    }
                    int i16 = i4;
                    double[][] dArr4 = dArr;
                    Object obj3 = obj2;
                    i6++;
                    i4 = i16;
                }
                double[][] dArr5 = dArr;
                writeToFile(jArr2, iArr2, dArr5, str);
                return dArr5;
            } else if (jArr2[i5] > 2147483643) {
                double[][] dArr6 = null;
                return null;
            } else {
                i5++;
            }
        }
    }

    public static double[][] benchmarkJavaArraysDoubleSequential(long[] jArr, int[] iArr, int i, String str) {
        Object obj;
        int i2;
        long j;
        long[] jArr2 = jArr;
        int[] iArr2 = iArr;
        int i3 = i;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            Object obj2 = null;
            if (i5 >= jArr2.length) {
                int length = iArr2.length;
                int[] iArr3 = new int[2];
                iArr3[1] = jArr2.length;
                iArr3[0] = length;
                double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr3);
                System.out.println("Benchmarking java arrays (doubles, sequentual)");
                int i6 = 0;
                while (i6 < iArr2.length) {
                    int i7 = iArr2[i6];
                    Thread[] threadArr = new Thread[i7];
                    System.out.println("\tNumber of threads = " + i7);
                    int i8 = i4;
                    while (i8 < jArr2.length) {
                        System.out.print("\tSize = " + jArr2[i8]);
                        final double[] dArr2 = new double[((int) jArr2[i8])];
                        double nanoTime = (double) System.nanoTime();
                        int i9 = i4;
                        while (i9 < i3) {
                            double[][] dArr3 = dArr;
                            long j2 = jArr2[i8] / ((long) i7);
                            int i10 = 0;
                            while (i10 < i7) {
                                final int i11 = (int) (((long) i10) * j2);
                                if (i10 == i7 - 1) {
                                    i2 = i6;
                                    j = jArr2[i8];
                                } else {
                                    i2 = i6;
                                    j = ((long) i11) + j2;
                                }
                                final int i12 = (int) j;
                                Thread thread = new Thread(new Runnable() {
                                    public void run() {
                                        for (int i = i11; i < i12; i++) {
                                            double[] dArr = dArr2;
                                            dArr[i] = 1.0d;
                                            dArr[i] = 1.0d + 1.0d;
                                        }
                                    }
                                });
                                threadArr[i10] = thread;
                                thread.start();
                                i10++;
                                i6 = i2;
                            }
                            int i13 = i6;
                            int i14 = 0;
                            while (i14 < i7) {
                                try {
                                    threadArr[i14].join();
                                    obj = null;
                                } catch (Exception e) {
                                    e = e;
                                    obj = null;
                                    e.printStackTrace();
                                    i9++;
                                    obj2 = obj;
                                    dArr = dArr3;
                                    i6 = i13;
                                }
                                try {
                                    threadArr[i14] = null;
                                    i14++;
                                } catch (Exception e2) {
                                    e = e2;
                                    e.printStackTrace();
                                    i9++;
                                    obj2 = obj;
                                    dArr = dArr3;
                                    i6 = i13;
                                }
                            }
                            obj = null;
                            i9++;
                            obj2 = obj;
                            dArr = dArr3;
                            i6 = i13;
                        }
                        double[][] dArr4 = dArr;
                        int i15 = i6;
                        dArr4[i15][i8] = ((((double) System.nanoTime()) - nanoTime) / 1.0E9d) / ((double) i3);
                        System.out.println(" : " + String.format("%.7f sec", new Object[]{Double.valueOf(dArr4[i15][i8])}));
                        i8++;
                        obj2 = obj2;
                        i4 = 0;
                        dArr = dArr4;
                        i6 = i15;
                    }
                    int i16 = i4;
                    double[][] dArr5 = dArr;
                    Object obj3 = obj2;
                    i6++;
                    i4 = i16;
                }
                double[][] dArr6 = dArr;
                writeToFile(jArr2, iArr2, dArr6, str);
                return dArr6;
            } else if (jArr2[i5] > 2147483643) {
                double[][] dArr7 = null;
                return null;
            } else {
                i5++;
            }
        }
    }

    public static double[][] benchmarkJavaArraysByteRandom(long[] jArr, int[] iArr, int i, String str) {
        int i2;
        long j;
        byte[] bArr;
        long[] jArr2 = jArr;
        int[] iArr2 = iArr;
        int i3 = i;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            Object obj = null;
            if (i5 >= jArr2.length) {
                int[] iArr3 = new int[((int) jArr2[jArr2.length - 1])];
                int length = iArr2.length;
                int[] iArr4 = new int[2];
                iArr4[1] = jArr2.length;
                iArr4[0] = length;
                double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr4);
                Random random = new Random(0);
                System.out.println("generating random indices.");
                int i6 = (int) jArr2[jArr2.length - 1];
                for (int i7 = 0; i7 < i6; i7++) {
                    iArr3[i7] = (int) (random.nextDouble() * ((double) (i6 - 1)));
                }
                System.out.println("Benchmarking java arrays (bytes, random)");
                int i8 = 0;
                while (i8 < iArr2.length) {
                    int i9 = iArr2[i8];
                    Thread[] threadArr = new Thread[i9];
                    System.out.println("\tNumber of threads = " + i9);
                    int i10 = i4;
                    while (i10 < jArr2.length) {
                        System.out.print("\tSize = " + jArr2[i10]);
                        long j2 = jArr2[i10];
                        byte[] bArr2 = new byte[((int) j2)];
                        double nanoTime = (double) System.nanoTime();
                        int i11 = 0;
                        while (i11 < i3) {
                            long j3 = j2;
                            byte[] bArr3 = bArr2;
                            long j4 = jArr2[i10] / ((long) i9);
                            int i12 = 0;
                            while (i12 < i9) {
                                final int i13 = (int) (((long) i12) * j4);
                                if (i12 == i9 - 1) {
                                    bArr = bArr3;
                                    i2 = i10;
                                    j = j3;
                                } else {
                                    bArr = bArr3;
                                    i2 = i10;
                                    j = ((long) i13) + j4;
                                }
                                final int i14 = (int) j;
                                byte[] bArr4 = bArr;
                                final int[] iArr5 = iArr3;
                                Thread[] threadArr2 = threadArr;
                                int[] iArr6 = iArr3;
                                int i15 = i9;
                                final long j5 = j3;
                                int i16 = i8;
                                final byte[] bArr5 = bArr4;
                                Thread thread = new Thread(new Runnable() {
                                    public void run() {
                                        for (int i = i13; i < i14; i++) {
                                            int i2 = (int) (((long) iArr5[i]) % j5);
                                            byte[] bArr = bArr5;
                                            bArr[i2] = 1;
                                            bArr[i2] = (byte) (1 + 1);
                                        }
                                    }
                                });
                                threadArr2[i12] = thread;
                                thread.start();
                                i12++;
                                long[] jArr3 = jArr;
                                int[] iArr7 = iArr;
                                i9 = i15;
                                i10 = i2;
                                bArr3 = bArr4;
                                iArr3 = iArr6;
                                threadArr = threadArr2;
                                i8 = i16;
                            }
                            byte[] bArr6 = bArr3;
                            int i17 = i10;
                            Thread[] threadArr3 = threadArr;
                            int i18 = i8;
                            int[] iArr8 = iArr3;
                            int i19 = i9;
                            int i20 = 0;
                            while (i20 < i19) {
                                try {
                                    threadArr3[i20].join();
                                    try {
                                        threadArr3[i20] = null;
                                        i20++;
                                    } catch (Exception e) {
                                        e = e;
                                        e.printStackTrace();
                                        i11++;
                                        jArr2 = jArr;
                                        int[] iArr9 = iArr;
                                        i9 = i19;
                                        j2 = j3;
                                        i10 = i17;
                                        bArr2 = bArr6;
                                        iArr3 = iArr8;
                                        threadArr = threadArr3;
                                        i8 = i18;
                                    }
                                } catch (Exception e2) {
                                    e = e2;
                                    e.printStackTrace();
                                    i11++;
                                    jArr2 = jArr;
                                    int[] iArr92 = iArr;
                                    i9 = i19;
                                    j2 = j3;
                                    i10 = i17;
                                    bArr2 = bArr6;
                                    iArr3 = iArr8;
                                    threadArr = threadArr3;
                                    i8 = i18;
                                }
                            }
                            i11++;
                            jArr2 = jArr;
                            int[] iArr922 = iArr;
                            i9 = i19;
                            j2 = j3;
                            i10 = i17;
                            bArr2 = bArr6;
                            iArr3 = iArr8;
                            threadArr = threadArr3;
                            i8 = i18;
                        }
                        int i21 = i10;
                        Thread[] threadArr4 = threadArr;
                        int i22 = i8;
                        int[] iArr10 = iArr3;
                        int i23 = i9;
                        dArr[i22][i21] = ((((double) System.nanoTime()) - nanoTime) / 1.0E9d) / ((double) i3);
                        System.out.println(" : " + String.format("%.7f sec", new Object[]{Double.valueOf(dArr[i22][i21])}));
                        i10 = i21 + 1;
                        int[] iArr11 = iArr;
                        obj = null;
                        i4 = 0;
                        iArr3 = iArr10;
                        jArr2 = jArr;
                    }
                    int i24 = i4;
                    Object obj2 = obj;
                    int[] iArr12 = iArr3;
                    i8++;
                    iArr2 = iArr;
                    jArr2 = jArr;
                }
                writeToFile(jArr2, iArr2, dArr, str);
                return dArr;
            } else if (jArr2[i5] > 2147483643) {
                double[][] dArr2 = null;
                return null;
            } else {
                i5++;
            }
        }
    }

    public static double[][] benchmarkJavaArraysDoubleRandom(long[] jArr, int[] iArr, int i, String str) {
        int i2;
        long j;
        double[] dArr;
        long[] jArr2 = jArr;
        int[] iArr2 = iArr;
        int i3 = i;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            Object obj = null;
            if (i5 >= jArr2.length) {
                int[] iArr3 = new int[((int) jArr2[jArr2.length - 1])];
                int length = iArr2.length;
                int[] iArr4 = new int[2];
                iArr4[1] = jArr2.length;
                iArr4[0] = length;
                double[][] dArr2 = (double[][]) Array.newInstance(Double.TYPE, iArr4);
                Random random = new Random(0);
                System.out.println("generating random indices.");
                int i6 = (int) jArr2[jArr2.length - 1];
                for (int i7 = 0; i7 < i6; i7++) {
                    iArr3[i7] = (int) (random.nextDouble() * ((double) (i6 - 1)));
                }
                System.out.println("Benchmarking java arrays (double, random)");
                int i8 = 0;
                while (i8 < iArr2.length) {
                    int i9 = iArr2[i8];
                    Thread[] threadArr = new Thread[i9];
                    System.out.println("\tNumber of threads = " + i9);
                    int i10 = i4;
                    while (i10 < jArr2.length) {
                        System.out.print("\tSize = " + jArr2[i10]);
                        long j2 = jArr2[i10];
                        double[] dArr3 = new double[((int) j2)];
                        double nanoTime = (double) System.nanoTime();
                        int i11 = 0;
                        while (i11 < i3) {
                            long j3 = j2;
                            double[] dArr4 = dArr3;
                            long j4 = jArr2[i10] / ((long) i9);
                            int i12 = 0;
                            while (i12 < i9) {
                                final int i13 = (int) (((long) i12) * j4);
                                if (i12 == i9 - 1) {
                                    dArr = dArr4;
                                    i2 = i10;
                                    j = j3;
                                } else {
                                    dArr = dArr4;
                                    i2 = i10;
                                    j = ((long) i13) + j4;
                                }
                                final int i14 = (int) j;
                                double[] dArr5 = dArr;
                                final int[] iArr5 = iArr3;
                                Thread[] threadArr2 = threadArr;
                                int[] iArr6 = iArr3;
                                int i15 = i9;
                                final long j5 = j3;
                                int i16 = i8;
                                final double[] dArr6 = dArr5;
                                Thread thread = new Thread(new Runnable() {
                                    public void run() {
                                        for (int i = i13; i < i14; i++) {
                                            int i2 = (int) (((long) iArr5[i]) % j5);
                                            double[] dArr = dArr6;
                                            dArr[i2] = 1.0d;
                                            dArr[i2] = 1.0d + 1.0d;
                                        }
                                    }
                                });
                                threadArr2[i12] = thread;
                                thread.start();
                                i12++;
                                long[] jArr3 = jArr;
                                int[] iArr7 = iArr;
                                i9 = i15;
                                i10 = i2;
                                dArr4 = dArr5;
                                iArr3 = iArr6;
                                threadArr = threadArr2;
                                i8 = i16;
                            }
                            double[] dArr7 = dArr4;
                            int i17 = i10;
                            Thread[] threadArr3 = threadArr;
                            int i18 = i8;
                            int[] iArr8 = iArr3;
                            int i19 = i9;
                            int i20 = 0;
                            while (i20 < i19) {
                                try {
                                    threadArr3[i20].join();
                                    try {
                                        threadArr3[i20] = null;
                                        i20++;
                                    } catch (Exception e) {
                                        e = e;
                                        e.printStackTrace();
                                        i11++;
                                        jArr2 = jArr;
                                        int[] iArr9 = iArr;
                                        i9 = i19;
                                        j2 = j3;
                                        i10 = i17;
                                        dArr3 = dArr7;
                                        iArr3 = iArr8;
                                        threadArr = threadArr3;
                                        i8 = i18;
                                    }
                                } catch (Exception e2) {
                                    e = e2;
                                    e.printStackTrace();
                                    i11++;
                                    jArr2 = jArr;
                                    int[] iArr92 = iArr;
                                    i9 = i19;
                                    j2 = j3;
                                    i10 = i17;
                                    dArr3 = dArr7;
                                    iArr3 = iArr8;
                                    threadArr = threadArr3;
                                    i8 = i18;
                                }
                            }
                            i11++;
                            jArr2 = jArr;
                            int[] iArr922 = iArr;
                            i9 = i19;
                            j2 = j3;
                            i10 = i17;
                            dArr3 = dArr7;
                            iArr3 = iArr8;
                            threadArr = threadArr3;
                            i8 = i18;
                        }
                        int i21 = i10;
                        Thread[] threadArr4 = threadArr;
                        int i22 = i8;
                        int[] iArr10 = iArr3;
                        int i23 = i9;
                        dArr2[i22][i21] = ((((double) System.nanoTime()) - nanoTime) / 1.0E9d) / ((double) i3);
                        System.out.println(" : " + String.format("%.7f sec", new Object[]{Double.valueOf(dArr2[i22][i21])}));
                        i10 = i21 + 1;
                        int[] iArr11 = iArr;
                        obj = null;
                        i4 = 0;
                        iArr3 = iArr10;
                        jArr2 = jArr;
                    }
                    int i24 = i4;
                    Object obj2 = obj;
                    int[] iArr12 = iArr3;
                    i8++;
                    iArr2 = iArr;
                    jArr2 = jArr;
                }
                writeToFile(jArr2, iArr2, dArr2, str);
                return dArr2;
            } else if (jArr2[i5] > 2147483643) {
                double[][] dArr8 = null;
                return null;
            } else {
                i5++;
            }
        }
    }

    public static double[][] benchmarkJLargeArraysByteSequentual(long[] jArr, int[] iArr, int i, String str) {
        long[] jArr2 = jArr;
        int[] iArr2 = iArr;
        int i2 = i;
        int length = iArr2.length;
        int[] iArr3 = new int[2];
        iArr3[1] = jArr2.length;
        int i3 = 0;
        iArr3[0] = length;
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr3);
        System.out.println("Benchmarking JLargeArrays (bytes, sequentual)");
        int i4 = 0;
        while (i4 < iArr2.length) {
            int i5 = iArr2[i4];
            Thread[] threadArr = new Thread[i5];
            System.out.println("\tNumber of threads = " + i5);
            int i6 = i3;
            while (i6 < jArr2.length) {
                System.out.print("\tSize = " + jArr2[i6]);
                ByteLargeArray byteLargeArray = new ByteLargeArray(jArr2[i6]);
                double nanoTime = (double) System.nanoTime();
                int i7 = i3;
                while (i7 < i2) {
                    double[][] dArr2 = dArr;
                    long j = jArr2[i6] / ((long) i5);
                    int i8 = 0;
                    while (i8 < i5) {
                        int i9 = i4;
                        long j2 = ((long) i8) * j;
                        long j3 = i8 == i5 + -1 ? jArr2[i6] : j2 + j;
                        double d = nanoTime;
                        final long j4 = j2;
                        int i10 = i7;
                        final long j5 = j3;
                        final ByteLargeArray byteLargeArray2 = byteLargeArray;
                        Thread thread = new Thread(new Runnable() {
                            public void run() {
                                for (long j = j4; j < j5; j++) {
                                    byteLargeArray2.setByte(j, (byte) 1);
                                    ByteLargeArray byteLargeArray = byteLargeArray2;
                                    byteLargeArray.setByte(j, (byte) (byteLargeArray.getByte(j) + 1));
                                }
                            }
                        });
                        threadArr[i8] = thread;
                        thread.start();
                        i8++;
                        i7 = i10;
                        i4 = i9;
                        j = j;
                        nanoTime = d;
                    }
                    int i11 = i4;
                    double d2 = nanoTime;
                    int i12 = i7;
                    int i13 = 0;
                    while (i13 < i5) {
                        try {
                            threadArr[i13].join();
                            threadArr[i13] = null;
                            i13++;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    i7 = i12 + 1;
                    dArr = dArr2;
                    i4 = i11;
                    nanoTime = d2;
                }
                double[][] dArr3 = dArr;
                int i14 = i4;
                dArr3[i14][i6] = ((((double) System.nanoTime()) - nanoTime) / 1.0E9d) / ((double) i2);
                System.out.println(" : " + String.format("%.7f sec", new Object[]{Double.valueOf(dArr3[i14][i6])}));
                i6++;
                i3 = 0;
                dArr = dArr3;
                i4 = i14;
            }
            int i15 = i3;
            double[][] dArr4 = dArr;
            i4++;
        }
        writeToFile(jArr2, iArr2, dArr, str);
        return dArr;
    }

    public static double[][] benchmarkJLargeArraysByteSequentualNative(long[] jArr, int[] iArr, int i, String str) {
        long[] jArr2 = jArr;
        int[] iArr2 = iArr;
        int i2 = i;
        boolean z = true;
        LargeArray.setMaxSizeOf32bitArray(1);
        int length = iArr2.length;
        int[] iArr3 = new int[2];
        iArr3[1] = jArr2.length;
        int i3 = 0;
        iArr3[0] = length;
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr3);
        System.out.println("Benchmarking JLargeArrays (bytes, sequentual, native)");
        for (int i4 = 0; i4 < iArr2.length; i4++) {
            int i5 = iArr2[i4];
            Thread[] threadArr = new Thread[i5];
            System.out.println("\tNumber of threads = " + i5);
            int i6 = i3;
            while (i6 < jArr2.length) {
                System.out.print("\tSize = " + jArr2[i6]);
                ByteLargeArray byteLargeArray = new ByteLargeArray(jArr2[i6]);
                double nanoTime = (double) System.nanoTime();
                int i7 = i3;
                while (i7 < i2) {
                    long j = jArr2[i6] / ((long) i5);
                    int i8 = 0;
                    while (i8 < i5) {
                        double d = nanoTime;
                        final long j2 = ((long) i8) * j;
                        int i9 = i7;
                        final long j3 = i8 == i5 + -1 ? jArr2[i6] : j2 + j;
                        final ByteLargeArray byteLargeArray2 = byteLargeArray;
                        Thread thread = new Thread(new Runnable() {
                            public void run() {
                                for (long j = j2; j < j3; j++) {
                                    byteLargeArray2.setToNative(j, (byte) 1);
                                    ByteLargeArray byteLargeArray = byteLargeArray2;
                                    byteLargeArray.setToNative(j, Byte.valueOf((byte) (byteLargeArray.getFromNative(j).byteValue() + 1)));
                                }
                            }
                        });
                        threadArr[i8] = thread;
                        thread.start();
                        i8++;
                        j = j;
                        nanoTime = d;
                        i7 = i9;
                    }
                    double d2 = nanoTime;
                    int i10 = i7;
                    int i11 = 0;
                    while (i11 < i5) {
                        try {
                            threadArr[i11].join();
                            threadArr[i11] = null;
                            i11++;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    i7 = i10 + 1;
                    nanoTime = d2;
                }
                dArr[i4][i6] = ((((double) System.nanoTime()) - nanoTime) / 1.0E9d) / ((double) i2);
                System.out.println(" : " + String.format("%.7f sec", new Object[]{Double.valueOf(dArr[i4][i6])}));
                i6++;
                z = true;
                i3 = 0;
            }
            boolean z2 = z;
            int i12 = i3;
        }
        writeToFile(jArr2, iArr2, dArr, str);
        return dArr;
    }

    public static double[][] benchmarkJLargeArraysByteSequentual_safe(long[] jArr, int[] iArr, int i, String str) {
        long[] jArr2 = jArr;
        int[] iArr2 = iArr;
        int i2 = i;
        int length = iArr2.length;
        int[] iArr3 = new int[2];
        iArr3[1] = jArr2.length;
        int i3 = 0;
        iArr3[0] = length;
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr3);
        System.out.println("Benchmarking JLargeArrays (bytes, sequentual, with bounds checking)");
        int i4 = 0;
        while (i4 < iArr2.length) {
            int i5 = iArr2[i4];
            Thread[] threadArr = new Thread[i5];
            System.out.println("\tNumber of threads = " + i5);
            int i6 = i3;
            while (i6 < jArr2.length) {
                System.out.print("\tSize = " + jArr2[i6]);
                ByteLargeArray byteLargeArray = new ByteLargeArray(jArr2[i6]);
                double nanoTime = (double) System.nanoTime();
                int i7 = i3;
                while (i7 < i2) {
                    double[][] dArr2 = dArr;
                    long j = jArr2[i6] / ((long) i5);
                    int i8 = 0;
                    while (i8 < i5) {
                        int i9 = i4;
                        long j2 = ((long) i8) * j;
                        long j3 = i8 == i5 + -1 ? jArr2[i6] : j2 + j;
                        double d = nanoTime;
                        final long j4 = j2;
                        int i10 = i7;
                        final long j5 = j3;
                        final ByteLargeArray byteLargeArray2 = byteLargeArray;
                        Thread thread = new Thread(new Runnable() {
                            public void run() {
                                for (long j = j4; j < j5; j++) {
                                    byteLargeArray2.setByte_safe(j, (byte) 1);
                                    ByteLargeArray byteLargeArray = byteLargeArray2;
                                    byteLargeArray.setByte_safe(j, (byte) (byteLargeArray.getByte_safe(j) + 1));
                                }
                            }
                        });
                        threadArr[i8] = thread;
                        thread.start();
                        i8++;
                        i7 = i10;
                        i4 = i9;
                        j = j;
                        nanoTime = d;
                    }
                    int i11 = i4;
                    double d2 = nanoTime;
                    int i12 = i7;
                    int i13 = 0;
                    while (i13 < i5) {
                        try {
                            threadArr[i13].join();
                            threadArr[i13] = null;
                            i13++;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    i7 = i12 + 1;
                    dArr = dArr2;
                    i4 = i11;
                    nanoTime = d2;
                }
                double[][] dArr3 = dArr;
                int i14 = i4;
                dArr3[i14][i6] = ((((double) System.nanoTime()) - nanoTime) / 1.0E9d) / ((double) i2);
                System.out.println(" : " + String.format("%.7f sec", new Object[]{Double.valueOf(dArr3[i14][i6])}));
                i6++;
                i3 = 0;
                dArr = dArr3;
                i4 = i14;
            }
            int i15 = i3;
            double[][] dArr4 = dArr;
            i4++;
        }
        writeToFile(jArr2, iArr2, dArr, str);
        return dArr;
    }

    public static double[][] benchmarkJLargeArraysDoubleSequentual(long[] jArr, int[] iArr, int i, String str) {
        long[] jArr2 = jArr;
        int[] iArr2 = iArr;
        int i2 = i;
        int length = iArr2.length;
        int[] iArr3 = new int[2];
        iArr3[1] = jArr2.length;
        int i3 = 0;
        iArr3[0] = length;
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr3);
        System.out.println("Benchmarking JLargeArrays (doubles, sequentual)");
        int i4 = 0;
        while (i4 < iArr2.length) {
            int i5 = iArr2[i4];
            Thread[] threadArr = new Thread[i5];
            System.out.println("\tNumber of threads = " + i5);
            int i6 = i3;
            while (i6 < jArr2.length) {
                System.out.print("\tSize = " + jArr2[i6]);
                DoubleLargeArray doubleLargeArray = new DoubleLargeArray(jArr2[i6]);
                double nanoTime = (double) System.nanoTime();
                int i7 = i3;
                while (i7 < i2) {
                    double[][] dArr2 = dArr;
                    long j = jArr2[i6] / ((long) i5);
                    int i8 = 0;
                    while (i8 < i5) {
                        int i9 = i4;
                        long j2 = ((long) i8) * j;
                        long j3 = i8 == i5 + -1 ? jArr2[i6] : j2 + j;
                        double d = nanoTime;
                        final long j4 = j2;
                        int i10 = i7;
                        final long j5 = j3;
                        final DoubleLargeArray doubleLargeArray2 = doubleLargeArray;
                        Thread thread = new Thread(new Runnable() {
                            public void run() {
                                for (long j = j4; j < j5; j++) {
                                    doubleLargeArray2.setDouble(j, 1.0d);
                                    DoubleLargeArray doubleLargeArray = doubleLargeArray2;
                                    doubleLargeArray.setDouble(j, doubleLargeArray.getDouble(j) + 1.0d);
                                }
                            }
                        });
                        threadArr[i8] = thread;
                        thread.start();
                        i8++;
                        i7 = i10;
                        i4 = i9;
                        j = j;
                        nanoTime = d;
                    }
                    int i11 = i4;
                    double d2 = nanoTime;
                    int i12 = i7;
                    int i13 = 0;
                    while (i13 < i5) {
                        try {
                            threadArr[i13].join();
                            threadArr[i13] = null;
                            i13++;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    i7 = i12 + 1;
                    dArr = dArr2;
                    i4 = i11;
                    nanoTime = d2;
                }
                double[][] dArr3 = dArr;
                int i14 = i4;
                dArr3[i14][i6] = ((((double) System.nanoTime()) - nanoTime) / 1.0E9d) / ((double) i2);
                System.out.println(" : " + String.format("%.7f sec", new Object[]{Double.valueOf(dArr3[i14][i6])}));
                i6++;
                i3 = 0;
                dArr = dArr3;
                i4 = i14;
            }
            int i15 = i3;
            double[][] dArr4 = dArr;
            i4++;
        }
        writeToFile(jArr2, iArr2, dArr, str);
        return dArr;
    }

    public static double[][] benchmarkJLargeArraysDoubleSequentualNative(long[] jArr, int[] iArr, int i, String str) {
        long[] jArr2 = jArr;
        int[] iArr2 = iArr;
        int i2 = i;
        boolean z = true;
        LargeArray.setMaxSizeOf32bitArray(1);
        int length = iArr2.length;
        int[] iArr3 = new int[2];
        iArr3[1] = jArr2.length;
        int i3 = 0;
        iArr3[0] = length;
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr3);
        System.out.println("Benchmarking JLargeArrays (doubles, sequentual, native)");
        for (int i4 = 0; i4 < iArr2.length; i4++) {
            int i5 = iArr2[i4];
            Thread[] threadArr = new Thread[i5];
            System.out.println("\tNumber of threads = " + i5);
            int i6 = i3;
            while (i6 < jArr2.length) {
                System.out.print("\tSize = " + jArr2[i6]);
                DoubleLargeArray doubleLargeArray = new DoubleLargeArray(jArr2[i6]);
                double nanoTime = (double) System.nanoTime();
                int i7 = i3;
                while (i7 < i2) {
                    long j = jArr2[i6] / ((long) i5);
                    int i8 = 0;
                    while (i8 < i5) {
                        double d = nanoTime;
                        final long j2 = ((long) i8) * j;
                        int i9 = i7;
                        final long j3 = i8 == i5 + -1 ? jArr2[i6] : j2 + j;
                        final DoubleLargeArray doubleLargeArray2 = doubleLargeArray;
                        Thread thread = new Thread(new Runnable() {
                            public void run() {
                                for (long j = j2; j < j3; j++) {
                                    doubleLargeArray2.setToNative(j, Double.valueOf(1.0d));
                                    DoubleLargeArray doubleLargeArray = doubleLargeArray2;
                                    doubleLargeArray.setToNative(j, Double.valueOf(doubleLargeArray.getFromNative(j).doubleValue() + 1.0d));
                                }
                            }
                        });
                        threadArr[i8] = thread;
                        thread.start();
                        i8++;
                        j = j;
                        nanoTime = d;
                        i7 = i9;
                    }
                    double d2 = nanoTime;
                    int i10 = i7;
                    int i11 = 0;
                    while (i11 < i5) {
                        try {
                            threadArr[i11].join();
                            threadArr[i11] = null;
                            i11++;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    i7 = i10 + 1;
                    nanoTime = d2;
                }
                dArr[i4][i6] = ((((double) System.nanoTime()) - nanoTime) / 1.0E9d) / ((double) i2);
                System.out.println(" : " + String.format("%.7f sec", new Object[]{Double.valueOf(dArr[i4][i6])}));
                i6++;
                z = true;
                i3 = 0;
            }
            boolean z2 = z;
            int i12 = i3;
        }
        writeToFile(jArr2, iArr2, dArr, str);
        return dArr;
    }

    public static double[][] benchmarkJLargeArraysByteRandom(long[] jArr, int[] iArr, int i, String str) {
        long[] jArr2 = jArr;
        int[] iArr2 = iArr;
        int i2 = i;
        boolean z = true;
        int[] iArr3 = new int[((int) jArr2[jArr2.length - 1])];
        int length = iArr2.length;
        int[] iArr4 = new int[2];
        iArr4[1] = jArr2.length;
        int i3 = 0;
        iArr4[0] = length;
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr4);
        Random random = new Random(0);
        System.out.println("generating random indices.");
        int i4 = (int) jArr2[jArr2.length - 1];
        for (int i5 = 0; i5 < i4; i5++) {
            iArr3[i5] = (int) (random.nextDouble() * ((double) (i4 - 1)));
        }
        System.out.println("Benchmarking JLargeArrays (bytes, random)");
        int i6 = 0;
        while (i6 < iArr2.length) {
            int i7 = iArr2[i6];
            Thread[] threadArr = new Thread[i7];
            System.out.println("\tNumber of threads = " + i7);
            int i8 = i3;
            while (i8 < jArr2.length) {
                System.out.print("\tSize = " + jArr2[i8]);
                ByteLargeArray byteLargeArray = new ByteLargeArray(jArr2[i8]);
                int i9 = (int) jArr2[i8];
                double nanoTime = (double) System.nanoTime();
                int i10 = i3;
                while (i10 < i2) {
                    double[][] dArr2 = dArr;
                    long j = jArr2[i8] / ((long) i7);
                    int i11 = 0;
                    while (i11 < i7) {
                        long j2 = ((long) i11) * j;
                        double d = nanoTime;
                        long j3 = i11 == i7 + -1 ? jArr2[i8] : j2 + j;
                        AnonymousClass10 r23 = r5;
                        int i12 = i9;
                        final long j4 = j2;
                        int i13 = i8;
                        ByteLargeArray byteLargeArray2 = byteLargeArray;
                        final long j5 = j3;
                        Thread[] threadArr2 = threadArr;
                        final int[] iArr5 = iArr3;
                        int[] iArr6 = iArr3;
                        int i14 = i7;
                        final int i15 = i12;
                        int i16 = i6;
                        final ByteLargeArray byteLargeArray3 = byteLargeArray2;
                        AnonymousClass10 r5 = new Runnable() {
                            public void run() {
                                for (long j = j4; j < j5; j++) {
                                    long j2 = (long) (iArr5[(int) j] % i15);
                                    byteLargeArray3.setByte(j2, (byte) 1);
                                    ByteLargeArray byteLargeArray = byteLargeArray3;
                                    byteLargeArray.setByte(j2, (byte) (byteLargeArray.getByte(j2) + 1));
                                }
                            }
                        };
                        Thread thread = new Thread(r5);
                        threadArr2[i11] = thread;
                        thread.start();
                        i11++;
                        i7 = i14;
                        i8 = i13;
                        byteLargeArray = byteLargeArray2;
                        nanoTime = d;
                        threadArr = threadArr2;
                        iArr3 = iArr6;
                        i9 = i12;
                        i10 = i10;
                        i6 = i16;
                    }
                    double d2 = nanoTime;
                    int i17 = i9;
                    int i18 = i10;
                    int i19 = i8;
                    ByteLargeArray byteLargeArray4 = byteLargeArray;
                    Thread[] threadArr3 = threadArr;
                    int i20 = i6;
                    int[] iArr7 = iArr3;
                    int i21 = i7;
                    int i22 = 0;
                    while (i22 < i21) {
                        try {
                            threadArr3[i22].join();
                            threadArr3[i22] = null;
                            i22++;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    i10 = i18 + 1;
                    i7 = i21;
                    i8 = i19;
                    byteLargeArray = byteLargeArray4;
                    dArr = dArr2;
                    nanoTime = d2;
                    threadArr = threadArr3;
                    iArr3 = iArr7;
                    i9 = i17;
                    i6 = i20;
                }
                int i23 = i8;
                Thread[] threadArr4 = threadArr;
                int i24 = i6;
                int[] iArr8 = iArr3;
                double[][] dArr3 = dArr;
                int i25 = i7;
                dArr3[i24][i23] = ((((double) System.nanoTime()) - nanoTime) / 1.0E9d) / ((double) i2);
                System.out.println(" : " + String.format("%.7f sec", new Object[]{Double.valueOf(dArr3[i24][i23])}));
                int i26 = i23 + 1;
                z = true;
                i3 = 0;
                iArr3 = iArr8;
                i8 = i26;
            }
            boolean z2 = z;
            int[] iArr9 = iArr3;
            int i27 = i3;
            double[][] dArr4 = dArr;
            i6++;
        }
        double[][] dArr5 = dArr;
        writeToFile(jArr2, iArr2, dArr5, str);
        return dArr5;
    }

    public static double[][] benchmarkJLargeArraysDoubleRandom(long[] jArr, int[] iArr, int i, String str) {
        long[] jArr2 = jArr;
        int[] iArr2 = iArr;
        int i2 = i;
        boolean z = true;
        int[] iArr3 = new int[((int) jArr2[jArr2.length - 1])];
        int length = iArr2.length;
        int[] iArr4 = new int[2];
        iArr4[1] = jArr2.length;
        int i3 = 0;
        iArr4[0] = length;
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr4);
        Random random = new Random(0);
        System.out.println("generating random indices.");
        int i4 = (int) jArr2[jArr2.length - 1];
        for (int i5 = 0; i5 < i4; i5++) {
            iArr3[i5] = (int) (random.nextDouble() * ((double) (i4 - 1)));
        }
        System.out.println("Benchmarking JLargeArrays (doubles, random)");
        int i6 = 0;
        while (i6 < iArr2.length) {
            int i7 = iArr2[i6];
            Thread[] threadArr = new Thread[i7];
            System.out.println("\tNumber of threads = " + i7);
            int i8 = i3;
            while (i8 < jArr2.length) {
                System.out.print("\tSize = " + jArr2[i8]);
                DoubleLargeArray doubleLargeArray = new DoubleLargeArray(jArr2[i8]);
                int i9 = (int) jArr2[i8];
                double nanoTime = (double) System.nanoTime();
                int i10 = i3;
                while (i10 < i2) {
                    double[][] dArr2 = dArr;
                    long j = jArr2[i8] / ((long) i7);
                    int i11 = 0;
                    while (i11 < i7) {
                        long j2 = ((long) i11) * j;
                        double d = nanoTime;
                        long j3 = i11 == i7 + -1 ? jArr2[i8] : j2 + j;
                        AnonymousClass11 r23 = r5;
                        int i12 = i9;
                        final long j4 = j2;
                        int i13 = i8;
                        DoubleLargeArray doubleLargeArray2 = doubleLargeArray;
                        final long j5 = j3;
                        Thread[] threadArr2 = threadArr;
                        final int[] iArr5 = iArr3;
                        int[] iArr6 = iArr3;
                        int i14 = i7;
                        final int i15 = i12;
                        int i16 = i6;
                        final DoubleLargeArray doubleLargeArray3 = doubleLargeArray2;
                        AnonymousClass11 r5 = new Runnable() {
                            public void run() {
                                for (long j = j4; j < j5; j++) {
                                    long j2 = (long) (iArr5[(int) j] % i15);
                                    doubleLargeArray3.setDouble(j2, 1.0d);
                                    DoubleLargeArray doubleLargeArray = doubleLargeArray3;
                                    doubleLargeArray.setDouble(j2, doubleLargeArray.getDouble(j2) + 1.0d);
                                }
                            }
                        };
                        Thread thread = new Thread(r5);
                        threadArr2[i11] = thread;
                        thread.start();
                        i11++;
                        i7 = i14;
                        i8 = i13;
                        doubleLargeArray = doubleLargeArray2;
                        nanoTime = d;
                        threadArr = threadArr2;
                        iArr3 = iArr6;
                        i9 = i12;
                        i10 = i10;
                        i6 = i16;
                    }
                    double d2 = nanoTime;
                    int i17 = i9;
                    int i18 = i10;
                    int i19 = i8;
                    DoubleLargeArray doubleLargeArray4 = doubleLargeArray;
                    Thread[] threadArr3 = threadArr;
                    int i20 = i6;
                    int[] iArr7 = iArr3;
                    int i21 = i7;
                    int i22 = 0;
                    while (i22 < i21) {
                        try {
                            threadArr3[i22].join();
                            threadArr3[i22] = null;
                            i22++;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    i10 = i18 + 1;
                    i7 = i21;
                    i8 = i19;
                    doubleLargeArray = doubleLargeArray4;
                    dArr = dArr2;
                    nanoTime = d2;
                    threadArr = threadArr3;
                    iArr3 = iArr7;
                    i9 = i17;
                    i6 = i20;
                }
                int i23 = i8;
                Thread[] threadArr4 = threadArr;
                int i24 = i6;
                int[] iArr8 = iArr3;
                double[][] dArr3 = dArr;
                int i25 = i7;
                dArr3[i24][i23] = ((((double) System.nanoTime()) - nanoTime) / 1.0E9d) / ((double) i2);
                System.out.println(" : " + String.format("%.7f sec", new Object[]{Double.valueOf(dArr3[i24][i23])}));
                int i26 = i23 + 1;
                z = true;
                i3 = 0;
                iArr3 = iArr8;
                i8 = i26;
            }
            boolean z2 = z;
            int[] iArr9 = iArr3;
            int i27 = i3;
            double[][] dArr4 = dArr;
            i6++;
        }
        double[][] dArr5 = dArr;
        writeToFile(jArr2, iArr2, dArr5, str);
        return dArr5;
    }

    public static void benchmarkByteSequential(long[] jArr, int[] iArr, int i, String str) {
        benchmarkJavaArraysDoubleSequential(jArr, iArr, i, str + System.getProperty("file.separator") + "java_arrays_byte_sequential.txt");
        System.gc();
        benchmarkJLargeArraysByteSequentual(jArr, iArr, i, str + System.getProperty("file.separator") + "jlargearrays_byte_sequentual.txt");
    }

    public static void benchmarkDoubleSequential(long[] jArr, int[] iArr, int i, String str) {
        benchmarkJavaArraysDoubleSequential(jArr, iArr, i, str + System.getProperty("file.separator") + "java_arrays_double_sequential.txt");
        System.gc();
        benchmarkJLargeArraysDoubleSequentual(jArr, iArr, i, str + System.getProperty("file.separator") + "jlargearrays_double_sequentual.txt");
    }

    public static void benchmarkByteRandom(long[] jArr, int[] iArr, int i, String str) {
        benchmarkJavaArraysByteRandom(jArr, iArr, i, str + System.getProperty("file.separator") + "java_arrays_byte_random.txt");
        System.gc();
        benchmarkJLargeArraysByteRandom(jArr, iArr, i, str + System.getProperty("file.separator") + "jlargearrays_byte_random.txt");
    }

    public static void benchmarkDoubleRandom(long[] jArr, int[] iArr, int i, String str) {
        benchmarkJavaArraysDoubleRandom(jArr, iArr, i, str + System.getProperty("file.separator") + "java_arrays_double_random.txt");
        System.gc();
        benchmarkJLargeArraysDoubleRandom(jArr, iArr, i, str + System.getProperty("file.separator") + "jlargearrays_double_random.txt");
    }

    public static void benchmarkByteLargeArray() {
        System.out.println("Benchmarking ByteLargeArray.");
        long pow = (long) Math.pow(2.0d, 32.0d);
        long nanoTime = System.nanoTime();
        ByteLargeArray byteLargeArray = new ByteLargeArray(pow);
        PrintStream printStream = System.out;
        printStream.println("Constructor time: " + (((double) (System.nanoTime() - nanoTime)) / 1.0E9d) + " sec");
        for (int i = 0; i < 5; i++) {
            long nanoTime2 = System.nanoTime();
            for (long j = 0; j < pow; j++) {
                byteLargeArray.getByte(j);
                byteLargeArray.setByte(j, (byte) 1);
                byteLargeArray.setByte(j, (byte) (byteLargeArray.getByte(j) + 1));
            }
            PrintStream printStream2 = System.out;
            printStream2.println("Computation time: " + (((double) (System.nanoTime() - nanoTime2)) / 1.0E9d) + "sec");
        }
    }

    public static void benchmarkByteLargeArrayInANewThread() {
        System.out.println("Benchmarking ByteLargeArray in a new thread.");
        final long pow = (long) Math.pow(2.0d, 32.0d);
        long nanoTime = System.nanoTime();
        final ByteLargeArray byteLargeArray = new ByteLargeArray(pow);
        PrintStream printStream = System.out;
        printStream.println("Constructor time: " + (((double) (System.nanoTime() - nanoTime)) / 1.0E9d) + " sec");
        for (int i = 0; i < 5; i++) {
            long nanoTime2 = System.nanoTime();
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    for (long j = 0; j < pow; j++) {
                        byteLargeArray.setByte(j, (byte) 1);
                        ByteLargeArray byteLargeArray = byteLargeArray;
                        byteLargeArray.setByte(j, (byte) (byteLargeArray.getByte(j) + 1));
                    }
                }
            });
            thread.start();
            try {
                thread.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
            PrintStream printStream2 = System.out;
            printStream2.println("Computation time: " + (((double) (System.nanoTime() - nanoTime2)) / 1.0E9d) + " sec");
        }
    }

    public static void benchmarkFloatLargeArray() {
        System.out.println("Benchmarking FloatLargeArray.");
        long pow = (long) Math.pow(2.0d, 32.0d);
        long nanoTime = System.nanoTime();
        FloatLargeArray floatLargeArray = new FloatLargeArray(pow);
        PrintStream printStream = System.out;
        printStream.println("Constructor time: " + (((double) (System.nanoTime() - nanoTime)) / 1.0E9d) + " sec");
        for (int i = 0; i < 5; i++) {
            long nanoTime2 = System.nanoTime();
            for (long j = 0; j < pow; j++) {
                floatLargeArray.getFloat(j);
                floatLargeArray.setFloat(j, 1.0f);
                floatLargeArray.setFloat(j, floatLargeArray.getFloat(j) + 1.0f);
            }
            PrintStream printStream2 = System.out;
            printStream2.println("Computation time: " + (((double) (System.nanoTime() - nanoTime2)) / 1.0E9d) + "sec");
        }
    }

    public static void benchmarkFloatLargeArrayInANewThread() {
        System.out.println("Benchmarking FloatLargeArray in a new thread.");
        final long pow = (long) Math.pow(2.0d, 32.0d);
        long nanoTime = System.nanoTime();
        final FloatLargeArray floatLargeArray = new FloatLargeArray(pow);
        PrintStream printStream = System.out;
        printStream.println("Constructor time: " + (((double) (System.nanoTime() - nanoTime)) / 1.0E9d) + " sec");
        for (int i = 0; i < 5; i++) {
            long nanoTime2 = System.nanoTime();
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    for (long j = 0; j < pow; j++) {
                        floatLargeArray.setFloat(j, 1.0f);
                        FloatLargeArray floatLargeArray = floatLargeArray;
                        floatLargeArray.setFloat(j, floatLargeArray.getFloat(j) + 1.0f);
                    }
                }
            });
            thread.start();
            try {
                thread.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
            PrintStream printStream2 = System.out;
            printStream2.println("Computation time: " + (((double) (System.nanoTime() - nanoTime2)) / 1.0E9d) + " sec");
        }
    }

    public static void benchmarkByteLargeArrayNative() {
        System.out.println("Benchmarking ByteLargeArray native.");
        long pow = (long) Math.pow(2.0d, 32.0d);
        long nanoTime = System.nanoTime();
        ByteLargeArray byteLargeArray = new ByteLargeArray(pow, false);
        PrintStream printStream = System.out;
        printStream.println("Constructor time: " + (((double) (System.nanoTime() - nanoTime)) / 1.0E9d) + " sec");
        if (byteLargeArray.isLarge()) {
            for (int i = 0; i < 5; i++) {
                long nanoTime2 = System.nanoTime();
                for (long j = 0; j < pow; j++) {
                    byteLargeArray.getFromNative(j);
                    byteLargeArray.setToNative(j, (byte) 1);
                    byteLargeArray.setToNative(j, Byte.valueOf((byte) (byteLargeArray.getFromNative(j).byteValue() + 1)));
                }
                PrintStream printStream2 = System.out;
                printStream2.println("Computation time: " + (((double) (System.nanoTime() - nanoTime2)) / 1.0E9d) + " sec");
            }
        }
    }

    public static void benchmarkByteLargeArrayNativeInANewThread() {
        System.out.println("Benchmarking ByteLargeArray native in a new thread.");
        final long pow = (long) Math.pow(2.0d, 32.0d);
        long nanoTime = System.nanoTime();
        final ByteLargeArray byteLargeArray = new ByteLargeArray(pow);
        PrintStream printStream = System.out;
        printStream.println("Constructor time: " + (((double) (System.nanoTime() - nanoTime)) / 1.0E9d) + " sec");
        for (int i = 0; i < 5; i++) {
            long nanoTime2 = System.nanoTime();
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    for (long j = 0; j < pow; j++) {
                        byteLargeArray.setToNative(j, (byte) 1);
                        ByteLargeArray byteLargeArray = byteLargeArray;
                        byteLargeArray.setToNative(j, Byte.valueOf((byte) (byteLargeArray.getFromNative(j).byteValue() + 1)));
                    }
                }
            });
            thread.start();
            try {
                thread.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
            PrintStream printStream2 = System.out;
            printStream2.println("Computation time: " + (((double) (System.nanoTime() - nanoTime2)) / 1.0E9d) + " sec");
        }
    }

    public static void benchmarkFloatLargeArrayNative() {
        System.out.println("Benchmarking FloatLargeArray native.");
        long pow = (long) Math.pow(2.0d, 32.0d);
        long nanoTime = System.nanoTime();
        FloatLargeArray floatLargeArray = new FloatLargeArray(pow, false);
        PrintStream printStream = System.out;
        printStream.println("Constructor time: " + (((double) (System.nanoTime() - nanoTime)) / 1.0E9d) + " sec");
        if (floatLargeArray.isLarge()) {
            for (int i = 0; i < 5; i++) {
                long nanoTime2 = System.nanoTime();
                for (long j = 0; j < pow; j++) {
                    floatLargeArray.getFromNative(j);
                    floatLargeArray.setToNative(j, Float.valueOf(1.0f));
                    floatLargeArray.setToNative(j, Float.valueOf(floatLargeArray.getFromNative(j).floatValue() + 1.0f));
                }
                PrintStream printStream2 = System.out;
                printStream2.println("Computation time: " + (((double) (System.nanoTime() - nanoTime2)) / 1.0E9d) + " sec");
            }
        }
    }

    public static void benchmarkFloatLargeArrayNativeInANewThread() {
        System.out.println("Benchmarking FloatLargeArray native in a new thread.");
        final long pow = (long) Math.pow(2.0d, 32.0d);
        long nanoTime = System.nanoTime();
        final FloatLargeArray floatLargeArray = new FloatLargeArray(pow);
        PrintStream printStream = System.out;
        printStream.println("Constructor time: " + (((double) (System.nanoTime() - nanoTime)) / 1.0E9d) + " sec");
        for (int i = 0; i < 5; i++) {
            long nanoTime2 = System.nanoTime();
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    for (long j = 0; j < pow; j++) {
                        floatLargeArray.setToNative(j, Float.valueOf(1.0f));
                        FloatLargeArray floatLargeArray = floatLargeArray;
                        floatLargeArray.setToNative(j, Float.valueOf(floatLargeArray.getFromNative(j).floatValue() + 1.0f));
                    }
                }
            });
            thread.start();
            try {
                thread.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
            PrintStream printStream2 = System.out;
            printStream2.println("Computation time: " + (((double) (System.nanoTime() - nanoTime2)) / 1.0E9d) + " sec");
        }
    }

    public static void benchmarkArithmeticAdd() {
        System.out.println("Benchmarking addition of two ByteLargeArrays.");
        LargeArray.setMaxSizeOf32bitArray(1);
        long pow = (long) Math.pow(2.0d, 27.0d);
        LargeArray generateRandom = LargeArrayUtils.generateRandom(LargeArrayType.BYTE, pow);
        LargeArray generateRandom2 = LargeArrayUtils.generateRandom(LargeArrayType.BYTE, pow);
        LargeArray convert = LargeArrayUtils.convert(generateRandom, LargeArrayType.LONG);
        LargeArray convert2 = LargeArrayUtils.convert(generateRandom2, LargeArrayType.LONG);
        int i = 1;
        while (true) {
            if (i > 16) {
                break;
            }
            ConcurrencyUtils.setNumberOfThreads(i);
            LargeArrayArithmetics.add(generateRandom, generateRandom2);
            LargeArrayArithmetics.add(generateRandom, generateRandom2);
            long nanoTime = System.nanoTime();
            for (int i2 = 0; i2 < 5; i2++) {
                LargeArrayArithmetics.add(generateRandom, generateRandom2);
            }
            System.out.println("Average computation time using " + i + " threads: " + (((double) (System.nanoTime() - nanoTime)) / 1.0E9d) + " sec");
            i += 2;
        }
        System.out.println("Benchmarking addition of two LongLargeArrays.");
        for (int i3 = 1; i3 <= 16; i3 += 2) {
            ConcurrencyUtils.setNumberOfThreads(i3);
            LargeArrayArithmetics.add(convert, convert2);
            LargeArrayArithmetics.add(convert, convert2);
            long nanoTime2 = System.nanoTime();
            for (int i4 = 0; i4 < 5; i4++) {
                LargeArrayArithmetics.add(convert, convert2);
            }
            System.out.println("Average computation time using " + i3 + " threads: " + (((double) (System.nanoTime() - nanoTime2)) / 1.0E9d) + " sec");
        }
    }

    public static void benchmarkStatisticsAvg() {
        System.out.println("Benchmarking avgKahan (DoubleLargeArray of length = 2^28).");
        LargeArray.setMaxSizeOf32bitArray(1);
        LargeArray generateRandom = LargeArrayUtils.generateRandom(LargeArrayType.DOUBLE, (long) Math.pow(2.0d, 28.0d));
        int i = 1;
        while (true) {
            if (i > 16) {
                break;
            }
            ConcurrencyUtils.setNumberOfThreads(i);
            LargeArrayStatistics.avgKahan(generateRandom);
            LargeArrayStatistics.avgKahan(generateRandom);
            long nanoTime = System.nanoTime();
            for (int i2 = 0; i2 < 5; i2++) {
                LargeArrayStatistics.avgKahan(generateRandom);
            }
            System.out.println("Average computation time using " + i + " threads: " + (((double) (System.nanoTime() - nanoTime)) / 1.0E9d) + " sec");
            i++;
        }
        System.out.println("Benchmarking avg (DoubleLargeArray of length = 2^28).");
        LargeArray.setMaxSizeOf32bitArray(1);
        for (int i3 = 1; i3 <= 16; i3++) {
            ConcurrencyUtils.setNumberOfThreads(i3);
            LargeArrayStatistics.avg(generateRandom);
            LargeArrayStatistics.avg(generateRandom);
            long nanoTime2 = System.nanoTime();
            for (int i4 = 0; i4 < 5; i4++) {
                LargeArrayStatistics.avg(generateRandom);
            }
            System.out.println("Average computation time using " + i3 + " threads: " + (((double) (System.nanoTime() - nanoTime2)) / 1.0E9d) + " sec");
        }
    }

    public static void main(String[] strArr) {
        long[] jArr = new long[6];
        for (int i = 0; i < 6; i++) {
            int i2 = 27 + i;
            if (i2 == 31) {
                jArr[i] = ((long) Math.pow(2.0d, 31.0d)) - 4;
            } else {
                jArr[i] = (long) Math.pow(2.0d, (double) i2);
            }
        }
        long[] jArr2 = new long[4];
        for (int i3 = 0; i3 < 4; i3++) {
            jArr2[i3] = (long) Math.pow(2.0d, (double) (32 + i3));
        }
        int[] iArr = {1, 2, 4, 8, 16};
        LargeArray.setMaxSizeOf32bitArray(1);
        benchmarkByteSequential(jArr, iArr, 10, "/tmp/");
        benchmarkDoubleSequential(jArr, iArr, 10, "/tmp/");
        benchmarkByteRandom(jArr, iArr, 10, "/tmp/");
        benchmarkDoubleRandom(jArr, iArr, 10, "/tmp/");
        System.exit(0);
    }
}
