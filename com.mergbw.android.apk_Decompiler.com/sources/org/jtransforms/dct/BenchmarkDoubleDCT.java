package org.jtransforms.dct;

import android.support.v4.media.session.PlaybackStateCompat;
import java.lang.reflect.Array;
import java.util.Arrays;
import org.jtransforms.utils.CommonUtils;
import org.jtransforms.utils.IOUtils;
import pl.edu.icm.jlargearrays.ConcurrencyUtils;
import pl.edu.icm.jlargearrays.DoubleLargeArray;

public class BenchmarkDoubleDCT {
    private static boolean doScaling = false;
    private static boolean doWarmup = true;
    private static int niter = 200;
    private static int nsize = 16;
    private static int nthread = 8;
    private static long[] sizes1D = {PlaybackStateCompat.ACTION_SET_REPEAT_MODE, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED, PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE, PlaybackStateCompat.ACTION_SET_PLAYBACK_SPEED, 8388608, 16777216, 33554432, 10368, 27000, 75600, 165375, 362880, 1562500, 3211264, 6250000};
    private static long[] sizes2D = {256, 512, 1024, PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH, PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM, PlaybackStateCompat.ACTION_PLAY_FROM_URI, 16384, PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID, 260, 520, 1050, 1458, 1960, 2916, 4116, 5832};
    private static long[] sizes3D = {16, 32, 64, 128, 256, 512, 1024, PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH, 5, 17, 30, 95, 180, 270, 324, 420};
    private static int threadsBegin2D = 65636;
    private static int threadsBegin3D = 65636;

    private BenchmarkDoubleDCT() {
    }

    public static void parseArguments(String[] strArr) {
        if (strArr.length > 0) {
            int i = 0;
            nthread = Integer.parseInt(strArr[0]);
            threadsBegin2D = Integer.parseInt(strArr[1]);
            threadsBegin3D = Integer.parseInt(strArr[2]);
            niter = Integer.parseInt(strArr[3]);
            doWarmup = Boolean.parseBoolean(strArr[4]);
            doScaling = Boolean.parseBoolean(strArr[5]);
            int parseInt = Integer.parseInt(strArr[6]);
            nsize = parseInt;
            sizes1D = new long[parseInt];
            sizes2D = new long[parseInt];
            sizes3D = new long[parseInt];
            for (int i2 = 0; i2 < nsize; i2++) {
                sizes1D[i2] = (long) Integer.parseInt(strArr[i2 + 7]);
            }
            int i3 = 0;
            while (true) {
                int i4 = nsize;
                if (i3 >= i4) {
                    break;
                }
                sizes2D[i3] = (long) Integer.parseInt(strArr[i4 + 7 + i3]);
                i3++;
            }
            while (true) {
                int i5 = nsize;
                if (i >= i5) {
                    break;
                }
                sizes3D[i] = (long) Integer.parseInt(strArr[i5 + 7 + i5 + i]);
                i++;
            }
        } else {
            System.out.println("Default settings are used.");
        }
        ConcurrencyUtils.setNumberOfThreads(nthread);
        CommonUtils.setThreadsBeginN_2D((long) threadsBegin2D);
        CommonUtils.setThreadsBeginN_3D((long) threadsBegin3D);
        System.out.println("nthred = " + nthread);
        System.out.println("threadsBegin2D = " + threadsBegin2D);
        System.out.println("threadsBegin3D = " + threadsBegin3D);
        System.out.println("niter = " + niter);
        System.out.println("doWarmup = " + doWarmup);
        System.out.println("doScaling = " + doScaling);
        System.out.println("nsize = " + nsize);
        System.out.println("sizes1D[] = " + Arrays.toString(sizes1D));
        System.out.println("sizes2D[] = " + Arrays.toString(sizes2D));
        System.out.println("sizes3D[] = " + Arrays.toString(sizes3D));
    }

    public static void benchmarkForward_1D() {
        int i = nsize;
        double[] dArr = new double[i];
        double[] dArr2 = new double[i];
        for (int i2 = 0; i2 < nsize; i2++) {
            System.out.println("Forward DCT 1D of size " + sizes1D[i2]);
            if (doWarmup) {
                DoubleDCT_1D doubleDCT_1D = new DoubleDCT_1D(sizes1D[i2]);
                long j = sizes1D[i2];
                double[] dArr3 = new double[((int) j)];
                IOUtils.fillMatrix_1D(j, dArr3);
                doubleDCT_1D.forward(dArr3, doScaling);
                IOUtils.fillMatrix_1D(sizes1D[i2], dArr3);
                doubleDCT_1D.forward(dArr3, doScaling);
            }
            long nanoTime = System.nanoTime();
            DoubleDCT_1D doubleDCT_1D2 = new DoubleDCT_1D(sizes1D[i2]);
            dArr2[i2] = ((double) (System.nanoTime() - nanoTime)) / 1000000.0d;
            long j2 = sizes1D[i2];
            double[] dArr4 = new double[((int) j2)];
            if (doWarmup) {
                IOUtils.fillMatrix_1D(j2, dArr4);
                doubleDCT_1D2.forward(dArr4, doScaling);
                IOUtils.fillMatrix_1D(sizes1D[i2], dArr4);
                doubleDCT_1D2.forward(dArr4, doScaling);
            }
            double d = Double.MAX_VALUE;
            for (int i3 = 0; i3 < niter; i3++) {
                IOUtils.fillMatrix_1D(sizes1D[i2], dArr4);
                long nanoTime2 = System.nanoTime();
                doubleDCT_1D2.forward(dArr4, doScaling);
                double nanoTime3 = (double) (System.nanoTime() - nanoTime2);
                if (nanoTime3 < d) {
                    d = nanoTime3;
                }
            }
            double d2 = d / 1000000.0d;
            dArr[i2] = d2;
            dArr2[i2] = dArr2[i2] + d2;
            System.out.println("\tBest execution time without constructor: " + String.format("%.2f", new Object[]{Double.valueOf(dArr[i2])}) + " msec");
            System.out.println("\tBest execution time with constructor: " + String.format("%.2f", new Object[]{Double.valueOf(dArr2[i2])}) + " msec");
            System.gc();
            CommonUtils.sleep(5000);
        }
        IOUtils.writeFFTBenchmarkResultsToFile("benchmarkDoubleForwardDCT_1D.txt", nthread, niter, doWarmup, doScaling, sizes1D, dArr, dArr2);
    }

    public static void benchmarkForward_2D_input_1D() {
        int i = nsize;
        double[] dArr = new double[i];
        double[] dArr2 = new double[i];
        for (int i2 = 0; i2 < nsize; i2++) {
            System.out.println("Forward DCT 2D (input 1D) of size " + sizes2D[i2] + " x " + sizes2D[i2]);
            if (doWarmup) {
                long j = sizes2D[i2];
                DoubleDCT_2D doubleDCT_2D = new DoubleDCT_2D(j, j);
                long j2 = sizes2D[i2];
                DoubleLargeArray doubleLargeArray = new DoubleLargeArray(j2 * j2, false);
                long j3 = sizes2D[i2];
                IOUtils.fillMatrix_2D(j3, j3, doubleLargeArray);
                doubleDCT_2D.forward(doubleLargeArray, doScaling);
                long j4 = sizes2D[i2];
                IOUtils.fillMatrix_2D(j4, j4, doubleLargeArray);
                doubleDCT_2D.forward(doubleLargeArray, doScaling);
            }
            long nanoTime = System.nanoTime();
            long j5 = sizes2D[i2];
            DoubleDCT_2D doubleDCT_2D2 = new DoubleDCT_2D(j5, j5);
            dArr2[i2] = ((double) (System.nanoTime() - nanoTime)) / 1000000.0d;
            long j6 = sizes2D[i2];
            DoubleLargeArray doubleLargeArray2 = new DoubleLargeArray(j6 * j6, false);
            double d = Double.MAX_VALUE;
            for (int i3 = 0; i3 < niter; i3++) {
                long j7 = sizes2D[i2];
                IOUtils.fillMatrix_2D(j7, j7, doubleLargeArray2);
                long nanoTime2 = System.nanoTime();
                doubleDCT_2D2.forward(doubleLargeArray2, doScaling);
                double nanoTime3 = (double) (System.nanoTime() - nanoTime2);
                if (nanoTime3 < d) {
                    d = nanoTime3;
                }
            }
            double d2 = d / 1000000.0d;
            dArr[i2] = d2;
            dArr2[i2] = dArr2[i2] + d2;
            System.out.println("\tBest execution time without constructor: " + String.format("%.2f", new Object[]{Double.valueOf(dArr[i2])}) + " msec");
            System.out.println("\tBest execution time with constructor: " + String.format("%.2f", new Object[]{Double.valueOf(dArr2[i2])}) + " msec");
            System.gc();
            CommonUtils.sleep(5000);
        }
        IOUtils.writeFFTBenchmarkResultsToFile("benchmarkDoubleForwardDCT_2D_input_1D.txt", nthread, niter, doWarmup, doScaling, sizes2D, dArr, dArr2);
    }

    public static void benchmarkForward_2D_input_2D() {
        int i = nsize;
        double[] dArr = new double[i];
        double[] dArr2 = new double[i];
        for (int i2 = 0; i2 < nsize; i2++) {
            System.out.println("Forward DCT 2D (input 2D) of size " + sizes2D[i2] + " x " + sizes2D[i2]);
            if (doWarmup) {
                long j = sizes2D[i2];
                DoubleDCT_2D doubleDCT_2D = new DoubleDCT_2D(j, j);
                long j2 = sizes2D[i2];
                int i3 = (int) j2;
                int i4 = (int) j2;
                int[] iArr = new int[2];
                iArr[1] = i4;
                iArr[0] = i3;
                double[][] dArr3 = (double[][]) Array.newInstance(Double.TYPE, iArr);
                long j3 = sizes2D[i2];
                IOUtils.fillMatrix_2D(j3, j3, dArr3);
                doubleDCT_2D.forward(dArr3, doScaling);
                long j4 = sizes2D[i2];
                IOUtils.fillMatrix_2D(j4, j4, dArr3);
                doubleDCT_2D.forward(dArr3, doScaling);
            }
            long nanoTime = System.nanoTime();
            long j5 = sizes2D[i2];
            DoubleDCT_2D doubleDCT_2D2 = new DoubleDCT_2D(j5, j5);
            dArr2[i2] = ((double) (System.nanoTime() - nanoTime)) / 1000000.0d;
            long j6 = sizes2D[i2];
            int[] iArr2 = new int[2];
            iArr2[1] = (int) j6;
            iArr2[0] = (int) j6;
            double[][] dArr4 = (double[][]) Array.newInstance(Double.TYPE, iArr2);
            double d = Double.MAX_VALUE;
            for (int i5 = 0; i5 < niter; i5++) {
                long j7 = sizes2D[i2];
                IOUtils.fillMatrix_2D(j7, j7, dArr4);
                long nanoTime2 = System.nanoTime();
                doubleDCT_2D2.forward(dArr4, doScaling);
                double nanoTime3 = (double) (System.nanoTime() - nanoTime2);
                if (nanoTime3 < d) {
                    d = nanoTime3;
                }
            }
            double d2 = d / 1000000.0d;
            dArr[i2] = d2;
            dArr2[i2] = dArr2[i2] + d2;
            System.out.println("\tBest execution time without constructor: " + String.format("%.2f", new Object[]{Double.valueOf(dArr[i2])}) + " msec");
            System.out.println("\tBest execution time with constructor: " + String.format("%.2f", new Object[]{Double.valueOf(dArr2[i2])}) + " msec");
            double[][] dArr5 = null;
            System.gc();
            CommonUtils.sleep(5000);
        }
        IOUtils.writeFFTBenchmarkResultsToFile("benchmarkDoubleForwardDCT_2D_input_2D.txt", nthread, niter, doWarmup, doScaling, sizes2D, dArr, dArr2);
    }

    public static void benchmarkForward_3D_input_1D() {
        int i = nsize;
        double[] dArr = new double[i];
        double[] dArr2 = new double[i];
        for (int i2 = 0; i2 < nsize; i2++) {
            System.out.println("Forward DCT 3D (input 1D) of size " + sizes3D[i2] + " x " + sizes3D[i2] + " x " + sizes3D[i2]);
            if (doWarmup) {
                long j = sizes3D[i2];
                DoubleDCT_3D doubleDCT_3D = new DoubleDCT_3D(j, j, j);
                long j2 = sizes3D[i2];
                DoubleLargeArray doubleLargeArray = new DoubleLargeArray(j2 * j2 * j2, false);
                long j3 = sizes3D[i2];
                DoubleLargeArray doubleLargeArray2 = doubleLargeArray;
                IOUtils.fillMatrix_3D(j3, j3, j3, doubleLargeArray2);
                doubleDCT_3D.forward(doubleLargeArray, doScaling);
                long j4 = sizes3D[i2];
                IOUtils.fillMatrix_3D(j4, j4, j4, doubleLargeArray2);
                doubleDCT_3D.forward(doubleLargeArray, doScaling);
            }
            long nanoTime = System.nanoTime();
            long j5 = sizes3D[i2];
            DoubleDCT_3D doubleDCT_3D2 = new DoubleDCT_3D(j5, j5, j5);
            dArr2[i2] = ((double) (System.nanoTime() - nanoTime)) / 1000000.0d;
            long j6 = sizes3D[i2];
            DoubleLargeArray doubleLargeArray3 = new DoubleLargeArray(j6 * j6 * j6, false);
            double d = Double.MAX_VALUE;
            for (int i3 = 0; i3 < niter; i3++) {
                long j7 = sizes3D[i2];
                IOUtils.fillMatrix_3D(j7, j7, j7, doubleLargeArray3);
                long nanoTime2 = System.nanoTime();
                doubleDCT_3D2.forward(doubleLargeArray3, doScaling);
                double nanoTime3 = (double) (System.nanoTime() - nanoTime2);
                if (nanoTime3 < d) {
                    d = nanoTime3;
                }
            }
            double d2 = d / 1000000.0d;
            dArr[i2] = d2;
            dArr2[i2] = dArr2[i2] + d2;
            System.out.println("\tBest execution time without constructor: " + String.format("%.2f", new Object[]{Double.valueOf(dArr[i2])}) + " msec");
            System.out.println("\tBest execution time with constructor: " + String.format("%.2f", new Object[]{Double.valueOf(dArr2[i2])}) + " msec");
            System.gc();
            CommonUtils.sleep(5000);
        }
        IOUtils.writeFFTBenchmarkResultsToFile("benchmarkDoubleForwardDCT_3D_input_1D.txt", nthread, niter, doWarmup, doScaling, sizes3D, dArr, dArr2);
    }

    public static void benchmarkForward_3D_input_3D() {
        int i = nsize;
        double[] dArr = new double[i];
        double[] dArr2 = new double[i];
        for (int i2 = 0; i2 < nsize; i2++) {
            System.out.println("Forward DCT 3D (input 3D) of size " + sizes3D[i2] + " x " + sizes3D[i2] + " x " + sizes3D[i2]);
            if (doWarmup) {
                long j = sizes3D[i2];
                DoubleDCT_3D doubleDCT_3D = new DoubleDCT_3D(j, j, j);
                long j2 = sizes3D[i2];
                int[] iArr = new int[3];
                iArr[2] = (int) j2;
                iArr[1] = (int) j2;
                iArr[0] = (int) j2;
                double[][][] dArr3 = (double[][][]) Array.newInstance(Double.TYPE, iArr);
                long j3 = sizes3D[i2];
                double[][][] dArr4 = dArr3;
                IOUtils.fillMatrix_3D(j3, j3, j3, dArr4);
                doubleDCT_3D.forward(dArr3, doScaling);
                long j4 = sizes3D[i2];
                IOUtils.fillMatrix_3D(j4, j4, j4, dArr4);
                doubleDCT_3D.forward(dArr3, doScaling);
            }
            long nanoTime = System.nanoTime();
            long j5 = sizes3D[i2];
            DoubleDCT_3D doubleDCT_3D2 = new DoubleDCT_3D(j5, j5, j5);
            dArr2[i2] = ((double) (System.nanoTime() - nanoTime)) / 1000000.0d;
            long j6 = sizes3D[i2];
            int[] iArr2 = new int[3];
            iArr2[2] = (int) j6;
            iArr2[1] = (int) j6;
            iArr2[0] = (int) j6;
            double[][][] dArr5 = (double[][][]) Array.newInstance(Double.TYPE, iArr2);
            double d = Double.MAX_VALUE;
            for (int i3 = 0; i3 < niter; i3++) {
                long j7 = sizes3D[i2];
                IOUtils.fillMatrix_3D(j7, j7, j7, dArr5);
                long nanoTime2 = System.nanoTime();
                doubleDCT_3D2.forward(dArr5, doScaling);
                double nanoTime3 = (double) (System.nanoTime() - nanoTime2);
                if (nanoTime3 < d) {
                    d = nanoTime3;
                }
            }
            double d2 = d / 1000000.0d;
            dArr[i2] = d2;
            dArr2[i2] = dArr2[i2] + d2;
            System.out.println("\tBest execution time without constructor: " + String.format("%.2f", new Object[]{Double.valueOf(dArr[i2])}) + " msec");
            System.out.println("\tBest execution time with constructor: " + String.format("%.2f", new Object[]{Double.valueOf(dArr2[i2])}) + " msec");
            double[][][] dArr6 = null;
            System.gc();
            CommonUtils.sleep(5000);
        }
        IOUtils.writeFFTBenchmarkResultsToFile("benchmarkDoubleForwardDCT_3D_input_3D.txt", nthread, niter, doWarmup, doScaling, sizes3D, dArr, dArr2);
    }

    public static void main(String[] strArr) {
        parseArguments(strArr);
        benchmarkForward_1D();
        benchmarkForward_2D_input_1D();
        benchmarkForward_2D_input_2D();
        benchmarkForward_3D_input_1D();
        benchmarkForward_3D_input_3D();
        System.exit(0);
    }
}
