package org.jtransforms.fft;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.Arrays;
import org.apache.commons.math3.util.FastMath;
import org.jtransforms.utils.CommonUtils;
import org.jtransforms.utils.IOUtils;
import pl.edu.icm.jlargearrays.ConcurrencyUtils;
import pl.edu.icm.jlargearrays.FloatLargeArray;

public class BenchmarkFloatFFT {
    private static boolean doScaling = false;
    private static boolean doWarmup = true;
    private static int niter = 100;
    private static int nsize = 8;
    private static int nthread = 16;
    private static long[] sizes1D = {PlaybackStateCompat.ACTION_SET_REPEAT_MODE, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED, PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE, PlaybackStateCompat.ACTION_SET_PLAYBACK_SPEED, 8388608, 16777216, 33554432, 10368, 27000, 75600, 165375, 362880, 1562500, 3211264, 6250000};
    private static long[] sizes2D = {256, 512, 1024, PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH, PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM, PlaybackStateCompat.ACTION_PLAY_FROM_URI, 16384, PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID, 260, 520, 1050, 1458, 1960, 2916, 4116, 5832};
    private static long[] sizes3D = {16, 32, 64, 128, 256, 512, 1024, PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH, 5, 17, 30, 95, 180, 270, 324, 420};
    private static int threadsBegin2D = 65536;
    private static int threadsBegin3D = 65536;

    private BenchmarkFloatFFT() {
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

    public static void benchmarkComplexForward_1D() {
        int i = nsize;
        double[] dArr = new double[i];
        double[] dArr2 = new double[i];
        for (int i2 = 0; i2 < nsize; i2++) {
            System.out.println("Complex forward FFT 1D of size " + sizes1D[i2]);
            if (doWarmup) {
                FloatFFT_1D floatFFT_1D = new FloatFFT_1D(sizes1D[i2]);
                long j = sizes1D[i2];
                float[] fArr = new float[((int) (j * 2))];
                IOUtils.fillMatrix_1D(j * 2, fArr);
                floatFFT_1D.complexForward(fArr);
                IOUtils.fillMatrix_1D(sizes1D[i2] * 2, fArr);
                floatFFT_1D.complexForward(fArr);
            }
            long nanoTime = System.nanoTime();
            FloatFFT_1D floatFFT_1D2 = new FloatFFT_1D(sizes1D[i2]);
            dArr2[i2] = ((double) (System.nanoTime() - nanoTime)) / 1000000.0d;
            float[] fArr2 = new float[((int) (sizes1D[i2] * 2))];
            double d = Double.MAX_VALUE;
            for (int i3 = 0; i3 < niter; i3++) {
                IOUtils.fillMatrix_1D(sizes1D[i2] * 2, fArr2);
                long nanoTime2 = System.nanoTime();
                floatFFT_1D2.complexForward(fArr2);
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
        IOUtils.writeFFTBenchmarkResultsToFile("benchmarkFloatComplexForwardFFT_1D.txt", nthread, niter, doWarmup, doScaling, sizes1D, dArr, dArr2);
    }

    public static void benchmarkRealForward_1D() {
        int i = nsize;
        double[] dArr = new double[i];
        double[] dArr2 = new double[i];
        for (int i2 = 0; i2 < nsize; i2++) {
            System.out.println("Real forward FFT 1D of size " + sizes1D[i2]);
            if (doWarmup) {
                FloatFFT_1D floatFFT_1D = new FloatFFT_1D(sizes1D[i2]);
                long j = sizes1D[i2];
                float[] fArr = new float[((int) (j * 2))];
                IOUtils.fillMatrix_1D(j, fArr);
                floatFFT_1D.realForwardFull(fArr);
                IOUtils.fillMatrix_1D(sizes1D[i2], fArr);
                floatFFT_1D.realForwardFull(fArr);
            }
            long nanoTime = System.nanoTime();
            FloatFFT_1D floatFFT_1D2 = new FloatFFT_1D(sizes1D[i2]);
            dArr2[i2] = ((double) (System.nanoTime() - nanoTime)) / 1000000.0d;
            float[] fArr2 = new float[((int) (sizes1D[i2] * 2))];
            double d = Double.MAX_VALUE;
            for (int i3 = 0; i3 < niter; i3++) {
                IOUtils.fillMatrix_1D(sizes1D[i2], fArr2);
                long nanoTime2 = System.nanoTime();
                floatFFT_1D2.realForwardFull(fArr2);
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
        IOUtils.writeFFTBenchmarkResultsToFile("benchmarkFloatRealForwardFFT_1D.txt", nthread, niter, doWarmup, doScaling, sizes1D, dArr, dArr2);
    }

    public static void benchmarkComplexForward_2D_input_1D() {
        int i = nsize;
        double[] dArr = new double[i];
        double[] dArr2 = new double[i];
        boolean z = false;
        int i2 = 0;
        while (i2 < nsize) {
            System.out.println("Complex forward FFT 2D (input 1D) of size " + sizes2D[i2] + " x " + sizes2D[i2]);
            if (doWarmup) {
                long j = sizes2D[i2];
                FloatFFT_2D floatFFT_2D = new FloatFFT_2D(j, j);
                long j2 = sizes2D[i2];
                FloatLargeArray floatLargeArray = new FloatLargeArray(j2 * 2 * j2, z);
                long j3 = sizes2D[i2];
                IOUtils.fillMatrix_2D(j3, j3 * 2, floatLargeArray);
                floatFFT_2D.complexForward(floatLargeArray);
                long j4 = sizes2D[i2];
                IOUtils.fillMatrix_2D(j4, j4 * 2, floatLargeArray);
                floatFFT_2D.complexForward(floatLargeArray);
            }
            long nanoTime = System.nanoTime();
            long j5 = sizes2D[i2];
            FloatFFT_2D floatFFT_2D2 = new FloatFFT_2D(j5, j5);
            double d = 1000000.0d;
            dArr2[i2] = ((double) (System.nanoTime() - nanoTime)) / 1000000.0d;
            long j6 = sizes2D[i2];
            FloatLargeArray floatLargeArray2 = new FloatLargeArray(j6 * 2 * j6, z);
            int i3 = niter;
            if (sizes2D[i2] >= PlaybackStateCompat.ACTION_PLAY_FROM_URI) {
                i3 = FastMath.max(1, i3 / 10);
            }
            double d2 = Double.MAX_VALUE;
            int i4 = z;
            while (i4 < i3) {
                long j7 = sizes2D[i2];
                int i5 = i2;
                IOUtils.fillMatrix_2D(j7, j7 * 2, floatLargeArray2);
                long nanoTime2 = System.nanoTime();
                floatFFT_2D2.complexForward(floatLargeArray2);
                double nanoTime3 = (double) (System.nanoTime() - nanoTime2);
                if (nanoTime3 < d2) {
                    d2 = nanoTime3;
                }
                i4++;
                i2 = i5;
                d = 1000000.0d;
            }
            int i6 = i2;
            double d3 = d2 / d;
            dArr[i6] = d3;
            dArr2[i6] = dArr2[i6] + d3;
            System.out.println("\tBest execution time without constructor: " + String.format("%.2f", new Object[]{Double.valueOf(dArr[i6])}) + " msec");
            System.out.println("\tBest execution time with constructor: " + String.format("%.2f", new Object[]{Double.valueOf(dArr2[i6])}) + " msec");
            System.gc();
            CommonUtils.sleep(5000);
            i2 = i6 + 1;
            z = false;
        }
        IOUtils.writeFFTBenchmarkResultsToFile("benchmarkFloatComplexForwardFFT_2D_input_1D.txt", nthread, niter, doWarmup, doScaling, sizes2D, dArr, dArr2);
    }

    public static void benchmarkComplexForward_2D_input_2D() {
        int i = nsize;
        double[] dArr = new double[i];
        double[] dArr2 = new double[i];
        int i2 = 0;
        int i3 = 0;
        while (i3 < nsize) {
            System.out.println("Complex forward FFT 2D (input 2D) of size " + sizes2D[i3] + " x " + sizes2D[i3]);
            if (doWarmup) {
                long j = sizes2D[i3];
                FloatFFT_2D floatFFT_2D = new FloatFFT_2D(j, j);
                long j2 = sizes2D[i3];
                int[] iArr = new int[2];
                iArr[1] = ((int) j2) * 2;
                iArr[i2] = (int) j2;
                float[][] fArr = (float[][]) Array.newInstance(Float.TYPE, iArr);
                long j3 = sizes2D[i3];
                IOUtils.fillMatrix_2D(j3, j3 * 2, fArr);
                floatFFT_2D.complexForward(fArr);
                long j4 = sizes2D[i3];
                IOUtils.fillMatrix_2D(j4, j4 * 2, fArr);
                floatFFT_2D.complexForward(fArr);
            }
            long nanoTime = System.nanoTime();
            long j5 = sizes2D[i3];
            FloatFFT_2D floatFFT_2D2 = new FloatFFT_2D(j5, j5);
            dArr2[i3] = ((double) (System.nanoTime() - nanoTime)) / 1000000.0d;
            long j6 = sizes2D[i3];
            int[] iArr2 = new int[2];
            iArr2[1] = ((int) j6) * 2;
            iArr2[i2] = (int) j6;
            float[][] fArr2 = (float[][]) Array.newInstance(Float.TYPE, iArr2);
            double d = Double.MAX_VALUE;
            int i4 = i2;
            while (i4 < niter) {
                double[] dArr3 = dArr;
                long j7 = sizes2D[i3];
                int i5 = i3;
                IOUtils.fillMatrix_2D(j7, j7 * 2, fArr2);
                long nanoTime2 = System.nanoTime();
                floatFFT_2D2.complexForward(fArr2);
                double nanoTime3 = (double) (System.nanoTime() - nanoTime2);
                if (nanoTime3 < d) {
                    d = nanoTime3;
                }
                i4++;
                dArr = dArr3;
                i3 = i5;
            }
            int i6 = i3;
            double[] dArr4 = dArr;
            double d2 = d / 1000000.0d;
            dArr4[i6] = d2;
            dArr2[i6] = dArr2[i6] + d2;
            System.out.println("\tBest execution time without constructor: " + String.format("%.2f", new Object[]{Double.valueOf(dArr4[i6])}) + " msec");
            System.out.println("\tBest execution time with constructor: " + String.format("%.2f", new Object[]{Double.valueOf(dArr2[i6])}) + " msec");
            float[][] fArr3 = null;
            System.gc();
            CommonUtils.sleep(5000);
            i3 = i6 + 1;
            i2 = 0;
        }
        double[] dArr5 = dArr;
        IOUtils.writeFFTBenchmarkResultsToFile("benchmarkFloatComplexForwardFFT_2D_input_2D.txt", nthread, niter, doWarmup, doScaling, sizes2D, dArr, dArr2);
    }

    public static void benchmarkRealForward_2D_input_1D() {
        int i = nsize;
        double[] dArr = new double[i];
        double[] dArr2 = new double[i];
        for (int i2 = 0; i2 < nsize; i2++) {
            System.out.println("Real forward FFT 2D (input 1D) of size " + sizes2D[i2] + " x " + sizes2D[i2]);
            if (doWarmup) {
                long j = sizes2D[i2];
                FloatFFT_2D floatFFT_2D = new FloatFFT_2D(j, j);
                long j2 = sizes2D[i2];
                FloatLargeArray floatLargeArray = new FloatLargeArray(j2 * 2 * j2, false);
                long j3 = sizes2D[i2];
                IOUtils.fillMatrix_2D(j3, j3, floatLargeArray);
                floatFFT_2D.realForwardFull(floatLargeArray);
                long j4 = sizes2D[i2];
                IOUtils.fillMatrix_2D(j4, j4, floatLargeArray);
                floatFFT_2D.realForwardFull(floatLargeArray);
            }
            long nanoTime = System.nanoTime();
            long j5 = sizes2D[i2];
            FloatFFT_2D floatFFT_2D2 = new FloatFFT_2D(j5, j5);
            dArr2[i2] = ((double) (System.nanoTime() - nanoTime)) / 1000000.0d;
            long j6 = sizes2D[i2];
            FloatLargeArray floatLargeArray2 = new FloatLargeArray(2 * j6 * j6, false);
            double d = Double.MAX_VALUE;
            for (int i3 = 0; i3 < niter; i3++) {
                long j7 = sizes2D[i2];
                IOUtils.fillMatrix_2D(j7, j7, floatLargeArray2);
                long nanoTime2 = System.nanoTime();
                floatFFT_2D2.realForwardFull(floatLargeArray2);
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
        IOUtils.writeFFTBenchmarkResultsToFile("benchmarkFloatRealForwardFFT_2D_input_1D.txt", nthread, niter, doWarmup, doScaling, sizes2D, dArr, dArr2);
    }

    public static void benchmarkRealForward_2D_input_2D() {
        int i = nsize;
        double[] dArr = new double[i];
        double[] dArr2 = new double[i];
        for (int i2 = 0; i2 < nsize; i2++) {
            System.out.println("Real forward FFT 2D (input 2D) of size " + sizes2D[i2] + " x " + sizes2D[i2]);
            if (doWarmup) {
                long j = sizes2D[i2];
                FloatFFT_2D floatFFT_2D = new FloatFFT_2D(j, j);
                long j2 = sizes2D[i2];
                int i3 = (int) j2;
                int[] iArr = new int[2];
                iArr[1] = ((int) j2) * 2;
                iArr[0] = i3;
                float[][] fArr = (float[][]) Array.newInstance(Float.TYPE, iArr);
                long j3 = sizes2D[i2];
                IOUtils.fillMatrix_2D(j3, j3, fArr);
                floatFFT_2D.realForwardFull(fArr);
                long j4 = sizes2D[i2];
                IOUtils.fillMatrix_2D(j4, j4, fArr);
                floatFFT_2D.realForwardFull(fArr);
            }
            long nanoTime = System.nanoTime();
            long j5 = sizes2D[i2];
            FloatFFT_2D floatFFT_2D2 = new FloatFFT_2D(j5, j5);
            dArr2[i2] = ((double) (System.nanoTime() - nanoTime)) / 1000000.0d;
            long j6 = sizes2D[i2];
            int[] iArr2 = new int[2];
            iArr2[1] = ((int) j6) * 2;
            iArr2[0] = (int) j6;
            float[][] fArr2 = (float[][]) Array.newInstance(Float.TYPE, iArr2);
            double d = Double.MAX_VALUE;
            for (int i4 = 0; i4 < niter; i4++) {
                long j7 = sizes2D[i2];
                IOUtils.fillMatrix_2D(j7, j7, fArr2);
                long nanoTime2 = System.nanoTime();
                floatFFT_2D2.realForwardFull(fArr2);
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
            float[][] fArr3 = null;
            System.gc();
            CommonUtils.sleep(5000);
        }
        IOUtils.writeFFTBenchmarkResultsToFile("benchmarkFloatRealForwardFFT_2D_input_2D.txt", nthread, niter, doWarmup, doScaling, sizes2D, dArr, dArr2);
    }

    public static void benchmarkComplexForward_3D_input_1D() {
        int i = nsize;
        double[] dArr = new double[i];
        double[] dArr2 = new double[i];
        for (int i2 = 0; i2 < nsize; i2++) {
            System.out.println("Complex forward FFT 3D (input 1D) of size " + sizes3D[i2] + " x " + sizes3D[i2] + " x " + sizes3D[i2]);
            long j = 2;
            if (doWarmup) {
                long j2 = sizes3D[i2];
                FloatFFT_3D floatFFT_3D = new FloatFFT_3D(j2, j2, j2);
                long j3 = sizes3D[i2];
                FloatLargeArray floatLargeArray = new FloatLargeArray(j3 * j3 * 2 * j3, false);
                long j4 = sizes3D[i2];
                FloatLargeArray floatLargeArray2 = floatLargeArray;
                IOUtils.fillMatrix_3D(j4, j4, j4 * 2, floatLargeArray2);
                floatFFT_3D.complexForward(floatLargeArray);
                long j5 = sizes3D[i2];
                IOUtils.fillMatrix_3D(j5, j5, j5 * 2, floatLargeArray2);
                floatFFT_3D.complexForward(floatLargeArray);
            }
            long nanoTime = System.nanoTime();
            long j6 = sizes3D[i2];
            FloatFFT_3D floatFFT_3D2 = new FloatFFT_3D(j6, j6, j6);
            dArr2[i2] = ((double) (System.nanoTime() - nanoTime)) / 1000000.0d;
            long j7 = sizes3D[i2];
            FloatLargeArray floatLargeArray3 = new FloatLargeArray(j7 * j7 * 2 * j7, false);
            int i3 = niter;
            int i4 = 1;
            if (sizes3D[i2] >= 1024) {
                i3 = FastMath.max(1, i3 / 10);
            }
            int i5 = 0;
            double d = Double.MAX_VALUE;
            while (i5 < i3) {
                long j8 = sizes3D[i2];
                long j9 = j8 * j;
                int i6 = i5;
                int i7 = i4;
                IOUtils.fillMatrix_3D(j8, j8, j9, floatLargeArray3);
                long nanoTime2 = System.nanoTime();
                floatFFT_3D2.complexForward(floatLargeArray3);
                double nanoTime3 = (double) (System.nanoTime() - nanoTime2);
                if (nanoTime3 < d) {
                    d = nanoTime3;
                }
                i5 = i6 + 1;
                i4 = i7;
                j = 2;
            }
            int i8 = i4;
            double d2 = d / 1000000.0d;
            dArr[i2] = d2;
            dArr2[i2] = dArr2[i2] + d2;
            PrintStream printStream = System.out;
            StringBuilder sb = new StringBuilder("\tBest execution time without constructor: ");
            Object[] objArr = new Object[i8];
            objArr[0] = Double.valueOf(dArr[i2]);
            sb.append(String.format("%.2f", objArr));
            sb.append(" msec");
            printStream.println(sb.toString());
            PrintStream printStream2 = System.out;
            StringBuilder sb2 = new StringBuilder("\tBest execution time with constructor: ");
            Object[] objArr2 = new Object[i8];
            objArr2[0] = Double.valueOf(dArr2[i2]);
            sb2.append(String.format("%.2f", objArr2));
            sb2.append(" msec");
            printStream2.println(sb2.toString());
            System.gc();
            CommonUtils.sleep(5000);
        }
        IOUtils.writeFFTBenchmarkResultsToFile("benchmarkFloatComplexForwardFFT_3D_input_1D.txt", nthread, niter, doWarmup, doScaling, sizes3D, dArr, dArr2);
    }

    public static void benchmarkComplexForward_3D_input_3D() {
        int i = nsize;
        double[] dArr = new double[i];
        double[] dArr2 = new double[i];
        for (int i2 = 0; i2 < nsize; i2++) {
            System.out.println("Complex forward FFT 3D (input 3D) of size " + sizes3D[i2] + " x " + sizes3D[i2] + " x " + sizes3D[i2]);
            if (doWarmup) {
                long j = sizes3D[i2];
                FloatFFT_3D floatFFT_3D = new FloatFFT_3D(j, j, j);
                long j2 = sizes3D[i2];
                int[] iArr = new int[3];
                iArr[2] = ((int) j2) * 2;
                iArr[1] = (int) j2;
                iArr[0] = (int) j2;
                float[][][] fArr = (float[][][]) Array.newInstance(Float.TYPE, iArr);
                long j3 = sizes3D[i2];
                float[][][] fArr2 = fArr;
                IOUtils.fillMatrix_3D(j3, j3, j3 * 2, fArr2);
                floatFFT_3D.complexForward(fArr);
                long j4 = sizes3D[i2];
                IOUtils.fillMatrix_3D(j4, j4, j4 * 2, fArr2);
                floatFFT_3D.complexForward(fArr);
            }
            long nanoTime = System.nanoTime();
            long j5 = sizes3D[i2];
            FloatFFT_3D floatFFT_3D2 = new FloatFFT_3D(j5, j5, j5);
            dArr2[i2] = ((double) (System.nanoTime() - nanoTime)) / 1000000.0d;
            long j6 = sizes3D[i2];
            int[] iArr2 = new int[3];
            iArr2[2] = ((int) j6) * 2;
            iArr2[1] = (int) j6;
            iArr2[0] = (int) j6;
            float[][][] fArr3 = (float[][][]) Array.newInstance(Float.TYPE, iArr2);
            double d = Double.MAX_VALUE;
            for (int i3 = 0; i3 < niter; i3++) {
                long j7 = sizes3D[i2];
                IOUtils.fillMatrix_3D(j7, j7, j7 * 2, fArr3);
                long nanoTime2 = System.nanoTime();
                floatFFT_3D2.complexForward(fArr3);
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
            float[][][] fArr4 = null;
            System.gc();
            CommonUtils.sleep(5000);
        }
        IOUtils.writeFFTBenchmarkResultsToFile("benchmarkFloatComplexForwardFFT_3D_input_3D.txt", nthread, niter, doWarmup, doScaling, sizes3D, dArr, dArr2);
    }

    public static void benchmarkRealForward_3D_input_1D() {
        int i = nsize;
        double[] dArr = new double[i];
        double[] dArr2 = new double[i];
        for (int i2 = 0; i2 < nsize; i2++) {
            System.out.println("Real forward FFT 3D (input 1D) of size " + sizes3D[i2] + " x " + sizes3D[i2] + " x " + sizes3D[i2]);
            if (doWarmup) {
                long j = sizes3D[i2];
                FloatFFT_3D floatFFT_3D = new FloatFFT_3D(j, j, j);
                long j2 = sizes3D[i2];
                FloatLargeArray floatLargeArray = new FloatLargeArray(j2 * j2 * 2 * j2, false);
                floatFFT_3D.realForwardFull(floatLargeArray);
                long j3 = sizes3D[i2];
                IOUtils.fillMatrix_3D(j3, j3, j3, floatLargeArray);
                floatFFT_3D.realForwardFull(floatLargeArray);
            }
            long nanoTime = System.nanoTime();
            long j4 = sizes3D[i2];
            FloatFFT_3D floatFFT_3D2 = new FloatFFT_3D(j4, j4, j4);
            dArr2[i2] = ((double) (System.nanoTime() - nanoTime)) / 1000000.0d;
            long j5 = sizes3D[i2];
            FloatLargeArray floatLargeArray2 = new FloatLargeArray(j5 * j5 * 2 * j5, false);
            double d = Double.MAX_VALUE;
            for (int i3 = 0; i3 < niter; i3++) {
                long j6 = sizes3D[i2];
                IOUtils.fillMatrix_3D(j6, j6, j6, floatLargeArray2);
                long nanoTime2 = System.nanoTime();
                floatFFT_3D2.realForwardFull(floatLargeArray2);
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
        IOUtils.writeFFTBenchmarkResultsToFile("benchmarkFloatRealForwardFFT_3D_input_1D.txt", nthread, niter, doWarmup, doScaling, sizes3D, dArr, dArr2);
    }

    public static void benchmarkRealForward_3D_input_3D() {
        int i = nsize;
        double[] dArr = new double[i];
        double[] dArr2 = new double[i];
        for (int i2 = 0; i2 < nsize; i2++) {
            System.out.println("Real forward FFT 3D (input 3D) of size " + sizes3D[i2] + " x " + sizes3D[i2] + " x " + sizes3D[i2]);
            if (doWarmup) {
                long j = sizes3D[i2];
                FloatFFT_3D floatFFT_3D = new FloatFFT_3D(j, j, j);
                long j2 = sizes3D[i2];
                int[] iArr = new int[3];
                iArr[2] = ((int) j2) * 2;
                iArr[1] = (int) j2;
                iArr[0] = (int) j2;
                float[][][] fArr = (float[][][]) Array.newInstance(Float.TYPE, iArr);
                long j3 = sizes3D[i2];
                float[][][] fArr2 = fArr;
                IOUtils.fillMatrix_3D(j3, j3, j3, fArr2);
                floatFFT_3D.realForwardFull(fArr);
                long j4 = sizes3D[i2];
                IOUtils.fillMatrix_3D(j4, j4, j4, fArr2);
                floatFFT_3D.realForwardFull(fArr);
            }
            long nanoTime = System.nanoTime();
            long j5 = sizes3D[i2];
            FloatFFT_3D floatFFT_3D2 = new FloatFFT_3D(j5, j5, j5);
            dArr2[i2] = ((double) (System.nanoTime() - nanoTime)) / 1000000.0d;
            long j6 = sizes3D[i2];
            int[] iArr2 = new int[3];
            iArr2[2] = ((int) j6) * 2;
            iArr2[1] = (int) j6;
            iArr2[0] = (int) j6;
            float[][][] fArr3 = (float[][][]) Array.newInstance(Float.TYPE, iArr2);
            double d = Double.MAX_VALUE;
            for (int i3 = 0; i3 < niter; i3++) {
                long j7 = sizes3D[i2];
                IOUtils.fillMatrix_3D(j7, j7, j7, fArr3);
                long nanoTime2 = System.nanoTime();
                floatFFT_3D2.realForwardFull(fArr3);
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
            float[][][] fArr4 = null;
            System.gc();
            CommonUtils.sleep(5000);
        }
        IOUtils.writeFFTBenchmarkResultsToFile("benchmarkFloatRealForwardFFT_3D_input_3D.txt", nthread, niter, doWarmup, doScaling, sizes3D, dArr, dArr2);
    }

    public static void main(String[] strArr) {
        parseArguments(strArr);
        benchmarkComplexForward_1D();
        benchmarkRealForward_1D();
        benchmarkComplexForward_2D_input_1D();
        benchmarkComplexForward_2D_input_2D();
        benchmarkRealForward_2D_input_1D();
        benchmarkRealForward_2D_input_2D();
        benchmarkComplexForward_3D_input_1D();
        benchmarkComplexForward_3D_input_3D();
        benchmarkRealForward_3D_input_1D();
        benchmarkRealForward_3D_input_3D();
        System.exit(0);
    }
}
