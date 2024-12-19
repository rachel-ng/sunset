package org.jtransforms.dct;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.math3.util.FastMath;
import org.jtransforms.fft.DoubleFFT_1D;
import org.jtransforms.utils.CommonUtils;
import pl.edu.icm.jlargearrays.ConcurrencyUtils;
import pl.edu.icm.jlargearrays.DoubleLargeArray;
import pl.edu.icm.jlargearrays.LargeArray;
import pl.edu.icm.jlargearrays.LargeArrayUtils;
import pl.edu.icm.jlargearrays.LongLargeArray;

public class DoubleDCT_1D {
    private static final double PI = 3.141592653589793d;
    private DoubleFFT_1D fft;
    private int[] ip;
    private LongLargeArray ipl;
    private boolean isPowerOfTwo = false;
    private int n;
    private int nc;
    private long ncl;
    private long nl;
    private int nw;
    private long nwl;
    private boolean useLargeArrays;
    /* access modifiers changed from: private */
    public double[] w;
    /* access modifiers changed from: private */
    public DoubleLargeArray wl;

    public DoubleDCT_1D(long j) {
        if (j >= 1) {
            boolean z = CommonUtils.isUseLargeArrays() || j > ((long) LargeArray.getMaxSizeOf32bitArray());
            this.useLargeArrays = z;
            this.n = (int) j;
            this.nl = j;
            if (!z) {
                if (j > 268435456) {
                    throw new IllegalArgumentException("n must be smaller or equal to 268435456 when useLargeArrays argument is set to false");
                } else if (CommonUtils.isPowerOf2(j)) {
                    this.isPowerOfTwo = true;
                    int[] iArr = new int[((int) FastMath.ceil((double) ((1 << (((int) (FastMath.log(((double) (j / 2)) + 0.5d) / FastMath.log(2.0d))) / 2)) + 2)))];
                    this.ip = iArr;
                    int i = this.n;
                    double[] dArr = new double[((i * 5) / 4)];
                    this.w = dArr;
                    int i2 = iArr[0];
                    this.nw = i2;
                    if (j > ((long) (i2 << 2))) {
                        int i3 = i >> 2;
                        this.nw = i3;
                        CommonUtils.makewt(i3, iArr, dArr);
                    }
                    int[] iArr2 = this.ip;
                    int i4 = iArr2[1];
                    this.nc = i4;
                    if (j > ((long) i4)) {
                        int i5 = this.n;
                        this.nc = i5;
                        CommonUtils.makect(i5, this.w, this.nw, iArr2);
                    }
                } else {
                    this.w = makect(this.n);
                    this.fft = new DoubleFFT_1D(j * 2);
                }
            } else if (CommonUtils.isPowerOf2(j)) {
                this.isPowerOfTwo = true;
                this.ipl = new LongLargeArray((long) FastMath.ceil((double) ((1 << ((int) (((long) (FastMath.log(((double) (j / 2)) + 0.5d) / FastMath.log(2.0d))) / 2))) + 2)));
                this.wl = new DoubleLargeArray((this.nl * 5) / 4);
                long j2 = this.ipl.getLong(0);
                this.nwl = j2;
                if (j > (j2 << 2)) {
                    long j3 = this.nl >> 2;
                    this.nwl = j3;
                    CommonUtils.makewt(j3, this.ipl, this.wl);
                }
                long j4 = this.ipl.getLong(1);
                this.ncl = j4;
                if (j > j4) {
                    long j5 = this.nl;
                    this.ncl = j5;
                    CommonUtils.makect(j5, this.wl, this.nwl, this.ipl);
                }
            } else {
                this.wl = makect(j);
                this.fft = new DoubleFFT_1D(j * 2);
            }
        } else {
            throw new IllegalArgumentException("n must be greater than 0");
        }
    }

    public void forward(double[] dArr, boolean z) {
        forward(dArr, 0, z);
    }

    public void forward(DoubleLargeArray doubleLargeArray, boolean z) {
        forward(doubleLargeArray, 0, z);
    }

    public void forward(double[] dArr, int i, boolean z) {
        int i2;
        double[] dArr2 = dArr;
        int i3 = i;
        boolean z2 = z;
        Class<DoubleDCT_1D> cls = DoubleDCT_1D.class;
        int i4 = this.n;
        if (i4 != 1) {
            if (this.useLargeArrays) {
                forward(new DoubleLargeArray(dArr2), (long) i3, z2);
                return;
            }
            int i5 = 2;
            if (this.isPowerOfTwo) {
                double d = dArr2[(i3 + i4) - 1];
                for (int i6 = i4 - 2; i6 >= 2; i6 -= 2) {
                    int i7 = i3 + i6;
                    int i8 = i7 - 1;
                    dArr2[i7 + 1] = dArr2[i7] - dArr2[i8];
                    dArr2[i7] = dArr2[i7] + dArr2[i8];
                }
                dArr2[i3 + 1] = dArr2[i3] - d;
                dArr2[i3] = dArr2[i3] + d;
                int i9 = this.n;
                if (i9 > 4) {
                    double[] dArr3 = dArr;
                    int i10 = i;
                    rftbsub(i9, dArr3, i10, this.nc, this.w, this.nw);
                    CommonUtils.cftbsub(this.n, dArr3, i10, this.ip, this.nw, this.w);
                } else if (i9 == 4) {
                    CommonUtils.cftbsub(i9, dArr, i, this.ip, this.nw, this.w);
                }
                CommonUtils.dctsub(this.n, dArr, i, this.nc, this.w, this.nw);
                if (z2) {
                    int i11 = this.n;
                    CommonUtils.scale(i11, FastMath.sqrt(2.0d / ((double) i11)), dArr, i, false);
                    dArr2[i3] = dArr2[i3] / FastMath.sqrt(2.0d);
                    return;
                }
                return;
            }
            int i12 = i4 * 2;
            double[] dArr4 = new double[i12];
            System.arraycopy(dArr2, i3, dArr4, 0, i4);
            int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
            for (int i13 = this.n; i13 < i12; i13++) {
                dArr4[i13] = dArr4[(i12 - i13) - 1];
            }
            this.fft.realForward(dArr4);
            if (numberOfThreads <= 1 || ((long) this.n) <= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
                double[] dArr5 = dArr4;
                i2 = i12;
                for (int i14 = 0; i14 < this.n; i14++) {
                    int i15 = i14 * 2;
                    double[] dArr6 = this.w;
                    int i16 = i15 + 1;
                    dArr2[i3 + i14] = (dArr6[i15] * dArr5[i15]) - (dArr6[i16] * dArr5[i16]);
                }
            } else {
                int i17 = this.n / 2;
                Future[] futureArr = new Future[2];
                int i18 = 0;
                while (i18 < i5) {
                    int i19 = i18 * i17;
                    int i20 = i18 == 1 ? this.n : i19 + i17;
                    int i21 = i18;
                    final int i22 = i19;
                    Future[] futureArr2 = futureArr;
                    final int i23 = i20;
                    double[] dArr7 = dArr4;
                    final int i24 = i;
                    int i25 = i12;
                    final double[] dArr8 = dArr;
                    int i26 = i5;
                    final double[] dArr9 = dArr7;
                    futureArr2[i21] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            for (int i = i22; i < i23; i++) {
                                int i2 = i * 2;
                                int i3 = i2 + 1;
                                dArr8[i24 + i] = (DoubleDCT_1D.this.w[i2] * dArr9[i2]) - (DoubleDCT_1D.this.w[i3] * dArr9[i3]);
                            }
                        }
                    });
                    i18 = i21 + 1;
                    i12 = i25;
                    i5 = i26;
                    futureArr = futureArr2;
                    dArr4 = dArr7;
                }
                i2 = i12;
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr);
                } catch (InterruptedException e) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
                } catch (ExecutionException e2) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
                }
            }
            if (z2) {
                CommonUtils.scale(this.n, 1.0d / FastMath.sqrt((double) i2), dArr, i, false);
                dArr2[i3] = dArr2[i3] / FastMath.sqrt(2.0d);
            }
        }
    }

    public void forward(DoubleLargeArray doubleLargeArray, long j, boolean z) {
        long j2;
        DoubleLargeArray doubleLargeArray2 = doubleLargeArray;
        long j3 = j;
        boolean z2 = z;
        Class<DoubleDCT_1D> cls = DoubleDCT_1D.class;
        long j4 = this.nl;
        long j5 = 1;
        if (j4 != 1) {
            if (!this.useLargeArrays) {
                if (doubleLargeArray.isLarge() || doubleLargeArray.isConstant() || j3 >= 2147483647L) {
                    throw new IllegalArgumentException("The data array is too big.");
                }
                forward(doubleLargeArray.getData(), (int) j3, z2);
            } else if (this.isPowerOfTwo) {
                double d = doubleLargeArray2.getDouble((j4 + j3) - 1);
                long j6 = this.nl - 2;
                while (j6 >= 2) {
                    long j7 = j3 + j6;
                    double d2 = d;
                    long j8 = j7 - j5;
                    doubleLargeArray2.setDouble(j7 + j5, doubleLargeArray2.getDouble(j7) - doubleLargeArray2.getDouble(j8));
                    doubleLargeArray2.setDouble(j7, doubleLargeArray2.getDouble(j7) + doubleLargeArray2.getDouble(j8));
                    j6 -= 2;
                    d = d2;
                    j5 = 1;
                }
                double d3 = d;
                doubleLargeArray2.setDouble(j5 + j3, doubleLargeArray.getDouble(j) - d3);
                doubleLargeArray2.setDouble(j3, doubleLargeArray.getDouble(j) + d3);
                long j9 = this.nl;
                if (j9 > 4) {
                    DoubleLargeArray doubleLargeArray3 = doubleLargeArray;
                    long j10 = j;
                    rftbsub(j9, doubleLargeArray3, j10, this.ncl, this.wl, this.nwl);
                    CommonUtils.cftbsub(this.nl, doubleLargeArray3, j10, this.ipl, this.nwl, this.wl);
                } else if (j9 == 4) {
                    CommonUtils.cftbsub(j9, doubleLargeArray, j, this.ipl, this.nwl, this.wl);
                }
                CommonUtils.dctsub(this.nl, doubleLargeArray, j, this.ncl, this.wl, this.nwl);
                if (z2) {
                    long j11 = this.nl;
                    CommonUtils.scale(j11, FastMath.sqrt(2.0d / ((double) j11)), doubleLargeArray, j, false);
                    doubleLargeArray2.setDouble(j3, doubleLargeArray.getDouble(j) / FastMath.sqrt(2.0d));
                }
            } else {
                long j12 = j4 * 2;
                DoubleLargeArray doubleLargeArray4 = new DoubleLargeArray(j12);
                LargeArrayUtils.arraycopy(doubleLargeArray, j, doubleLargeArray4, 0, this.nl);
                int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
                for (long j13 = this.nl; j13 < j12; j13++) {
                    doubleLargeArray4.setDouble(j13, doubleLargeArray4.getDouble((j12 - j13) - 1));
                }
                this.fft.realForward(doubleLargeArray4);
                int i = 1;
                if (numberOfThreads <= 1 || this.nl <= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
                    j2 = j12;
                    for (long j14 = 0; j14 < this.nl; j14++) {
                        long j15 = j14 * 2;
                        long j16 = j15 + 1;
                        doubleLargeArray2.setDouble(j3 + j14, (this.wl.getDouble(j15) * doubleLargeArray4.getDouble(j15)) - (this.wl.getDouble(j16) * doubleLargeArray4.getDouble(j16)));
                    }
                } else {
                    int i2 = 2;
                    long j17 = this.nl / ((long) 2);
                    Future[] futureArr = new Future[2];
                    int i3 = 0;
                    while (i3 < i2) {
                        final long j18 = ((long) i3) * j17;
                        Future[] futureArr2 = futureArr;
                        int i4 = i3;
                        final long j19 = i3 == i ? this.nl : j18 + j17;
                        int i5 = i;
                        final long j20 = j;
                        long j21 = j12;
                        final DoubleLargeArray doubleLargeArray5 = doubleLargeArray;
                        final DoubleLargeArray doubleLargeArray6 = doubleLargeArray4;
                        futureArr2[i4] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                for (long j = j18; j < j19; j++) {
                                    long j2 = 2 * j;
                                    long j3 = j2 + 1;
                                    doubleLargeArray5.setDouble(j20 + j, (DoubleDCT_1D.this.wl.getDouble(j2) * doubleLargeArray6.getDouble(j2)) - (DoubleDCT_1D.this.wl.getDouble(j3) * doubleLargeArray6.getDouble(j3)));
                                }
                            }
                        });
                        i3 = i4 + 1;
                        i = i5;
                        i2 = i2;
                        futureArr = futureArr2;
                        j12 = j21;
                    }
                    j2 = j12;
                    try {
                        ConcurrencyUtils.waitForCompletion(futureArr);
                    } catch (InterruptedException e) {
                        Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
                    } catch (ExecutionException e2) {
                        Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
                    }
                }
                if (z2) {
                    CommonUtils.scale(this.nl, 1.0d / FastMath.sqrt((double) j2), doubleLargeArray, j, false);
                    doubleLargeArray2.setDouble(j3, doubleLargeArray.getDouble(j) / FastMath.sqrt(2.0d));
                }
            }
        }
    }

    public void inverse(double[] dArr, boolean z) {
        inverse(dArr, 0, z);
    }

    public void inverse(DoubleLargeArray doubleLargeArray, boolean z) {
        inverse(doubleLargeArray, 0, z);
    }

    public void inverse(double[] dArr, int i, boolean z) {
        double[] dArr2 = dArr;
        int i2 = i;
        boolean z2 = z;
        Class<DoubleDCT_1D> cls = DoubleDCT_1D.class;
        int i3 = this.n;
        if (i3 != 1) {
            if (this.useLargeArrays) {
                inverse(new DoubleLargeArray(dArr2), (long) i2, z2);
                return;
            }
            int i4 = 2;
            if (this.isPowerOfTwo) {
                if (z2) {
                    CommonUtils.scale(i3, FastMath.sqrt(2.0d / ((double) i3)), dArr, i, false);
                    dArr2[i2] = dArr2[i2] / FastMath.sqrt(2.0d);
                }
                CommonUtils.dctsub(this.n, dArr, i, this.nc, this.w, this.nw);
                int i5 = this.n;
                if (i5 > 4) {
                    double[] dArr3 = dArr;
                    int i6 = i;
                    CommonUtils.cftfsub(i5, dArr3, i6, this.ip, this.nw, this.w);
                    rftfsub(this.n, dArr3, i6, this.nc, this.w, this.nw);
                } else if (i5 == 4) {
                    CommonUtils.cftfsub(i5, dArr, i, this.ip, this.nw, this.w);
                }
                double d = dArr2[i2];
                double d2 = dArr2[i2 + 1];
                double d3 = d - d2;
                dArr2[i2] = d + d2;
                while (true) {
                    int i7 = this.n;
                    if (i4 < i7) {
                        int i8 = i2 + i4;
                        int i9 = i8 + 1;
                        dArr2[i8 - 1] = dArr2[i8] - dArr2[i9];
                        dArr2[i8] = dArr2[i8] + dArr2[i9];
                        i4 += 2;
                    } else {
                        dArr2[(i7 + i2) - 1] = d3;
                        return;
                    }
                }
            } else {
                int i10 = i3 * 2;
                if (z2) {
                    CommonUtils.scale(i3, FastMath.sqrt((double) i10), dArr, i, false);
                    dArr2[i2] = dArr2[i2] * FastMath.sqrt(2.0d);
                }
                double[] dArr4 = new double[i10];
                if (ConcurrencyUtils.getNumberOfThreads() <= 1 || ((long) this.n) <= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
                    for (int i11 = 0; i11 < this.n; i11++) {
                        int i12 = i11 * 2;
                        double d4 = dArr2[i2 + i11];
                        double[] dArr5 = this.w;
                        dArr4[i12] = dArr5[i12] * d4;
                        int i13 = i12 + 1;
                        dArr4[i13] = (-dArr5[i13]) * d4;
                    }
                } else {
                    int i14 = this.n / 2;
                    Future[] futureArr = new Future[2];
                    int i15 = 0;
                    while (i15 < 2) {
                        final int i16 = i15 * i14;
                        final int i17 = i15 == 1 ? this.n : i16 + i14;
                        final double[] dArr6 = dArr;
                        int i18 = i15;
                        final int i19 = i;
                        Future[] futureArr2 = futureArr;
                        final double[] dArr7 = dArr4;
                        futureArr2[i18] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                for (int i = i16; i < i17; i++) {
                                    int i2 = i * 2;
                                    double d = dArr6[i19 + i];
                                    dArr7[i2] = DoubleDCT_1D.this.w[i2] * d;
                                    int i3 = i2 + 1;
                                    dArr7[i3] = (-DoubleDCT_1D.this.w[i3]) * d;
                                }
                            }
                        });
                        i15 = i18 + 1;
                        futureArr = futureArr2;
                    }
                    try {
                        ConcurrencyUtils.waitForCompletion(futureArr);
                    } catch (InterruptedException e) {
                        Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
                    } catch (ExecutionException e2) {
                        Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
                    }
                }
                this.fft.realInverse(dArr4, true);
                System.arraycopy(dArr4, 0, dArr2, i2, this.n);
            }
        }
    }

    public void inverse(DoubleLargeArray doubleLargeArray, long j, boolean z) {
        long j2;
        DoubleDCT_1D doubleDCT_1D;
        DoubleDCT_1D doubleDCT_1D2 = this;
        DoubleLargeArray doubleLargeArray2 = doubleLargeArray;
        long j3 = j;
        boolean z2 = z;
        Class<DoubleDCT_1D> cls = DoubleDCT_1D.class;
        long j4 = doubleDCT_1D2.nl;
        if (j4 != 1) {
            if (!doubleDCT_1D2.useLargeArrays) {
                if (doubleLargeArray.isLarge() || doubleLargeArray.isConstant() || j3 >= 2147483647L) {
                    throw new IllegalArgumentException("The data array is too big.");
                }
                doubleDCT_1D2.inverse(doubleLargeArray.getData(), (int) j3, z2);
                DoubleDCT_1D doubleDCT_1D3 = doubleDCT_1D2;
            } else if (doubleDCT_1D2.isPowerOfTwo) {
                if (z2) {
                    CommonUtils.scale(j4, FastMath.sqrt(2.0d / ((double) j4)), doubleLargeArray, j, false);
                    doubleLargeArray2.setDouble(j3, doubleLargeArray.getDouble(j) / FastMath.sqrt(2.0d));
                }
                CommonUtils.dctsub(doubleDCT_1D2.nl, doubleLargeArray, j, doubleDCT_1D2.ncl, doubleDCT_1D2.wl, doubleDCT_1D2.nwl);
                long j5 = doubleDCT_1D2.nl;
                if (j5 > 4) {
                    DoubleLargeArray doubleLargeArray3 = doubleLargeArray;
                    long j6 = j;
                    CommonUtils.cftfsub(j5, doubleLargeArray3, j6, doubleDCT_1D2.ipl, doubleDCT_1D2.nwl, doubleDCT_1D2.wl);
                    rftfsub(doubleDCT_1D2.nl, doubleLargeArray3, j6, doubleDCT_1D2.ncl, doubleDCT_1D2.wl, doubleDCT_1D2.nwl);
                } else if (j5 == 4) {
                    CommonUtils.cftfsub(j5, doubleLargeArray, j, doubleDCT_1D2.ipl, doubleDCT_1D2.nwl, doubleDCT_1D2.wl);
                }
                long j7 = j3 + 1;
                double d = doubleLargeArray.getDouble(j) - doubleLargeArray2.getDouble(j7);
                doubleLargeArray2.setDouble(j3, doubleLargeArray.getDouble(j) + doubleLargeArray2.getDouble(j7));
                long j8 = 2;
                while (true) {
                    long j9 = doubleDCT_1D2.nl;
                    if (j8 < j9) {
                        long j10 = j3 + j8;
                        long j11 = j10 + 1;
                        doubleLargeArray2.setDouble(j10 - 1, doubleLargeArray2.getDouble(j10) - doubleLargeArray2.getDouble(j11));
                        doubleLargeArray2.setDouble(j10, doubleLargeArray2.getDouble(j10) + doubleLargeArray2.getDouble(j11));
                        j8 += 2;
                        doubleDCT_1D2 = this;
                    } else {
                        doubleLargeArray2.setDouble((j3 + j9) - 1, d);
                        return;
                    }
                }
            } else {
                long j12 = j4 * 2;
                if (z2) {
                    j2 = j12;
                    CommonUtils.scale(j4, FastMath.sqrt((double) j12), doubleLargeArray, j, false);
                    doubleLargeArray2.setDouble(j3, doubleLargeArray.getDouble(j) * FastMath.sqrt(2.0d));
                } else {
                    j2 = j12;
                }
                DoubleLargeArray doubleLargeArray4 = new DoubleLargeArray(j2);
                int i = 1;
                if (ConcurrencyUtils.getNumberOfThreads() > 1) {
                    DoubleDCT_1D doubleDCT_1D4 = this;
                    if (doubleDCT_1D4.nl > CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
                        int i2 = 2;
                        long j13 = doubleDCT_1D4.nl / ((long) 2);
                        Future[] futureArr = new Future[2];
                        int i3 = 0;
                        while (i3 < i2) {
                            final long j14 = ((long) i3) * j13;
                            final long j15 = i3 == i ? doubleDCT_1D4.nl : j14 + j13;
                            int i4 = i3;
                            final DoubleLargeArray doubleLargeArray5 = doubleLargeArray;
                            DoubleDCT_1D doubleDCT_1D5 = doubleDCT_1D4;
                            final long j16 = j;
                            final DoubleLargeArray doubleLargeArray6 = doubleLargeArray4;
                            futureArr[i4] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                                public void run() {
                                    for (long j = j14; j < j15; j++) {
                                        long j2 = 2 * j;
                                        double d = doubleLargeArray5.getDouble(j16 + j);
                                        doubleLargeArray6.setDouble(j2, DoubleDCT_1D.this.wl.getDouble(j2) * d);
                                        long j3 = j2 + 1;
                                        doubleLargeArray6.setDouble(j3, (-DoubleDCT_1D.this.wl.getDouble(j3)) * d);
                                    }
                                }
                            });
                            i3 = i4 + 1;
                            doubleDCT_1D4 = doubleDCT_1D5;
                            i2 = i2;
                            i = 1;
                            DoubleLargeArray doubleLargeArray7 = doubleLargeArray;
                        }
                        doubleDCT_1D = doubleDCT_1D4;
                        try {
                            ConcurrencyUtils.waitForCompletion(futureArr);
                        } catch (InterruptedException e) {
                            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
                        } catch (ExecutionException e2) {
                            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
                        }
                        DoubleLargeArray doubleLargeArray8 = doubleLargeArray;
                        DoubleDCT_1D doubleDCT_1D6 = doubleDCT_1D;
                        doubleDCT_1D6.fft.realInverse(doubleLargeArray4, true);
                        LargeArrayUtils.arraycopy(doubleLargeArray4, 0, doubleLargeArray, j, doubleDCT_1D6.nl);
                    }
                    doubleDCT_1D = doubleDCT_1D4;
                } else {
                    doubleDCT_1D = this;
                }
                for (long j17 = 0; j17 < doubleDCT_1D.nl; j17++) {
                    long j18 = j17 * 2;
                    DoubleDCT_1D doubleDCT_1D7 = doubleDCT_1D;
                    double d2 = doubleLargeArray.getDouble(j3 + j17);
                    doubleLargeArray4.setDouble(j18, doubleDCT_1D7.wl.getDouble(j18) * d2);
                    long j19 = j18 + 1;
                    doubleLargeArray4.setDouble(j19, (-doubleDCT_1D7.wl.getDouble(j19)) * d2);
                }
                DoubleLargeArray doubleLargeArray82 = doubleLargeArray;
                DoubleDCT_1D doubleDCT_1D62 = doubleDCT_1D;
                doubleDCT_1D62.fft.realInverse(doubleLargeArray4, true);
                LargeArrayUtils.arraycopy(doubleLargeArray4, 0, doubleLargeArray, j, doubleDCT_1D62.nl);
            }
        }
    }

    private double[] makect(int i) {
        int i2 = i * 2;
        double d = 3.141592653589793d / ((double) i2);
        double[] dArr = new double[i2];
        dArr[0] = 1.0d;
        for (int i3 = 1; i3 < i; i3++) {
            int i4 = i3 * 2;
            double d2 = ((double) i3) * d;
            dArr[i4] = FastMath.cos(d2);
            dArr[i4 + 1] = -FastMath.sin(d2);
        }
        return dArr;
    }

    private DoubleLargeArray makect(long j) {
        long j2 = j * 2;
        double d = 3.141592653589793d / ((double) j2);
        DoubleLargeArray doubleLargeArray = new DoubleLargeArray(j2);
        doubleLargeArray.setDouble(0, 1.0d);
        for (long j3 = 1; j3 < j; j3++) {
            long j4 = j3 * 2;
            double d2 = ((double) j3) * d;
            doubleLargeArray.setDouble(j4, FastMath.cos(d2));
            doubleLargeArray.setDouble(j4 + 1, -FastMath.sin(d2));
        }
        return doubleLargeArray;
    }

    private static void rftfsub(int i, double[] dArr, int i2, int i3, double[] dArr2, int i4) {
        int i5 = i >> 1;
        int i6 = (i3 * 2) / i5;
        int i7 = 0;
        for (int i8 = 2; i8 < i5; i8 += 2) {
            i7 += i6;
            double d = 0.5d - dArr2[(i4 + i3) - i7];
            double d2 = dArr2[i4 + i7];
            int i9 = i2 + i8;
            int i10 = i2 + (i - i8);
            double d3 = dArr[i9];
            double d4 = d3 - dArr[i10];
            int i11 = i9 + 1;
            int i12 = i10 + 1;
            double d5 = dArr[i11] + dArr[i12];
            double d6 = (d * d4) - (d2 * d5);
            double d7 = (d * d5) + (d2 * d4);
            dArr[i9] = d3 - d6;
            dArr[i11] = dArr[i11] - d7;
            dArr[i10] = dArr[i10] + d6;
            dArr[i12] = dArr[i12] - d7;
        }
    }

    private static void rftfsub(long j, DoubleLargeArray doubleLargeArray, long j2, long j3, DoubleLargeArray doubleLargeArray2, long j4) {
        DoubleLargeArray doubleLargeArray3 = doubleLargeArray;
        DoubleLargeArray doubleLargeArray4 = doubleLargeArray2;
        long j5 = j >> 1;
        long j6 = (j3 * 2) / j5;
        long j7 = 0;
        long j8 = 2;
        while (j8 < j5) {
            j7 += j6;
            double d = 0.5d - doubleLargeArray4.getDouble((j4 + j3) - j7);
            double d2 = doubleLargeArray4.getDouble(j4 + j7);
            long j9 = j2 + j8;
            long j10 = j2 + (j - j8);
            double d3 = doubleLargeArray3.getDouble(j9) - doubleLargeArray3.getDouble(j10);
            long j11 = j9 + 1;
            long j12 = j6;
            long j13 = j10 + 1;
            double d4 = doubleLargeArray3.getDouble(j11) + doubleLargeArray3.getDouble(j13);
            double d5 = (d * d3) - (d2 * d4);
            double d6 = (d * d4) + (d2 * d3);
            doubleLargeArray3.setDouble(j9, doubleLargeArray3.getDouble(j9) - d5);
            doubleLargeArray3.setDouble(j11, doubleLargeArray3.getDouble(j11) - d6);
            doubleLargeArray3.setDouble(j10, doubleLargeArray3.getDouble(j10) + d5);
            doubleLargeArray3.setDouble(j13, doubleLargeArray3.getDouble(j13) - d6);
            j8 += 2;
            j5 = j5;
            j6 = j12;
            doubleLargeArray4 = doubleLargeArray2;
        }
    }

    private static void rftbsub(int i, double[] dArr, int i2, int i3, double[] dArr2, int i4) {
        int i5 = i >> 1;
        int i6 = (i3 * 2) / i5;
        int i7 = 0;
        for (int i8 = 2; i8 < i5; i8 += 2) {
            i7 += i6;
            double d = 0.5d - dArr2[(i4 + i3) - i7];
            double d2 = dArr2[i4 + i7];
            int i9 = i2 + i8;
            int i10 = i2 + (i - i8);
            double d3 = dArr[i9];
            double d4 = d3 - dArr[i10];
            int i11 = i9 + 1;
            int i12 = i10 + 1;
            double d5 = dArr[i11] + dArr[i12];
            double d6 = (d * d4) + (d2 * d5);
            double d7 = (d * d5) - (d2 * d4);
            dArr[i9] = d3 - d6;
            dArr[i11] = dArr[i11] - d7;
            dArr[i10] = dArr[i10] + d6;
            dArr[i12] = dArr[i12] - d7;
        }
    }

    private static void rftbsub(long j, DoubleLargeArray doubleLargeArray, long j2, long j3, DoubleLargeArray doubleLargeArray2, long j4) {
        DoubleLargeArray doubleLargeArray3 = doubleLargeArray;
        DoubleLargeArray doubleLargeArray4 = doubleLargeArray2;
        long j5 = j >> 1;
        long j6 = (j3 * 2) / j5;
        long j7 = 0;
        long j8 = 2;
        while (j8 < j5) {
            j7 += j6;
            double d = 0.5d - doubleLargeArray4.getDouble((j4 + j3) - j7);
            double d2 = doubleLargeArray4.getDouble(j4 + j7);
            long j9 = j2 + j8;
            long j10 = j2 + (j - j8);
            double d3 = doubleLargeArray3.getDouble(j9) - doubleLargeArray3.getDouble(j10);
            long j11 = j9 + 1;
            long j12 = j6;
            long j13 = j10 + 1;
            double d4 = doubleLargeArray3.getDouble(j11) + doubleLargeArray3.getDouble(j13);
            double d5 = (d * d3) + (d2 * d4);
            double d6 = (d * d4) - (d2 * d3);
            doubleLargeArray3.setDouble(j9, doubleLargeArray3.getDouble(j9) - d5);
            doubleLargeArray3.setDouble(j11, doubleLargeArray3.getDouble(j11) - d6);
            doubleLargeArray3.setDouble(j10, doubleLargeArray3.getDouble(j10) + d5);
            doubleLargeArray3.setDouble(j13, doubleLargeArray3.getDouble(j13) - d6);
            j8 += 2;
            j5 = j5;
            j6 = j12;
            doubleLargeArray4 = doubleLargeArray2;
        }
    }
}
