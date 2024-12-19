package org.jtransforms.dct;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.math3.util.FastMath;
import org.jtransforms.fft.FloatFFT_1D;
import org.jtransforms.utils.CommonUtils;
import pl.edu.icm.jlargearrays.ConcurrencyUtils;
import pl.edu.icm.jlargearrays.FloatLargeArray;
import pl.edu.icm.jlargearrays.LargeArray;
import pl.edu.icm.jlargearrays.LargeArrayUtils;
import pl.edu.icm.jlargearrays.LongLargeArray;

public class FloatDCT_1D {
    private static final float PI = 3.1415927f;
    private FloatFFT_1D fft;
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
    public float[] w;
    /* access modifiers changed from: private */
    public FloatLargeArray wl;

    public FloatDCT_1D(long j) {
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
                    float[] fArr = new float[((i * 5) / 4)];
                    this.w = fArr;
                    int i2 = iArr[0];
                    this.nw = i2;
                    if (j > ((long) (i2 << 2))) {
                        int i3 = i >> 2;
                        this.nw = i3;
                        CommonUtils.makewt(i3, iArr, fArr);
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
                    this.fft = new FloatFFT_1D(j * 2);
                }
            } else if (CommonUtils.isPowerOf2(j)) {
                this.isPowerOfTwo = true;
                this.ipl = new LongLargeArray((long) FastMath.ceil((double) ((1 << ((int) (((long) (FastMath.log(((double) (j / 2)) + 0.5d) / FastMath.log(2.0d))) / 2))) + 2)));
                this.wl = new FloatLargeArray((this.nl * 5) / 4);
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
                this.fft = new FloatFFT_1D(j * 2);
            }
        } else {
            throw new IllegalArgumentException("n must be greater than 0");
        }
    }

    public void forward(float[] fArr, boolean z) {
        forward(fArr, 0, z);
    }

    public void forward(FloatLargeArray floatLargeArray, boolean z) {
        forward(floatLargeArray, 0, z);
    }

    public void forward(float[] fArr, int i, boolean z) {
        boolean z2;
        int i2;
        float[] fArr2 = fArr;
        int i3 = i;
        boolean z3 = z;
        Class<FloatDCT_1D> cls = FloatDCT_1D.class;
        int i4 = this.n;
        if (i4 != 1) {
            if (this.useLargeArrays) {
                forward(new FloatLargeArray(fArr2), (long) i3, z3);
                return;
            }
            boolean z4 = false;
            if (this.isPowerOfTwo) {
                float f = fArr2[(i3 + i4) - 1];
                for (int i5 = i4 - 2; i5 >= 2; i5 -= 2) {
                    int i6 = i3 + i5;
                    int i7 = i6 - 1;
                    fArr2[i6 + 1] = fArr2[i6] - fArr2[i7];
                    fArr2[i6] = fArr2[i6] + fArr2[i7];
                }
                fArr2[i3 + 1] = fArr2[i3] - f;
                fArr2[i3] = fArr2[i3] + f;
                int i8 = this.n;
                if (i8 > 4) {
                    float[] fArr3 = fArr;
                    int i9 = i;
                    rftbsub(i8, fArr3, i9, this.nc, this.w, this.nw);
                    CommonUtils.cftbsub(this.n, fArr3, i9, this.ip, this.nw, this.w);
                } else if (i8 == 4) {
                    CommonUtils.cftbsub(i8, fArr, i, this.ip, this.nw, this.w);
                }
                CommonUtils.dctsub(this.n, fArr, i, this.nc, this.w, this.nw);
                if (z3) {
                    int i10 = this.n;
                    CommonUtils.scale(i10, (float) FastMath.sqrt(2.0d / ((double) i10)), fArr2, i3, false);
                    fArr2[i3] = fArr2[i3] / ((float) FastMath.sqrt(2.0d));
                    return;
                }
                return;
            }
            int i11 = i4 * 2;
            float[] fArr4 = new float[i11];
            System.arraycopy(fArr2, i3, fArr4, 0, i4);
            int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
            for (int i12 = this.n; i12 < i11; i12++) {
                fArr4[i12] = fArr4[(i11 - i12) - 1];
            }
            this.fft.realForward(fArr4);
            if (numberOfThreads <= 1 || ((long) this.n) <= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
                float[] fArr5 = fArr4;
                i2 = i11;
                z2 = false;
                for (int i13 = 0; i13 < this.n; i13++) {
                    int i14 = i13 * 2;
                    float[] fArr6 = this.w;
                    int i15 = i14 + 1;
                    fArr2[i3 + i13] = (fArr6[i14] * fArr5[i14]) - (fArr6[i15] * fArr5[i15]);
                }
            } else {
                int i16 = this.n / 2;
                Future[] futureArr = new Future[2];
                int i17 = 0;
                for (int i18 = 2; i17 < i18; i18 = 2) {
                    int i19 = i17 * i16;
                    int i20 = i17 == 1 ? this.n : i19 + i16;
                    int i21 = i17;
                    Future[] futureArr2 = futureArr;
                    final int i22 = i19;
                    float[] fArr7 = fArr4;
                    final int i23 = i20;
                    int i24 = i11;
                    final int i25 = i;
                    int i26 = i18;
                    final float[] fArr8 = fArr;
                    boolean z5 = z4;
                    final float[] fArr9 = fArr7;
                    futureArr2[i21] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            for (int i = i22; i < i23; i++) {
                                int i2 = i * 2;
                                int i3 = i2 + 1;
                                fArr8[i25 + i] = (FloatDCT_1D.this.w[i2] * fArr9[i2]) - (FloatDCT_1D.this.w[i3] * fArr9[i3]);
                            }
                        }
                    });
                    i17 = i21 + 1;
                    i11 = i24;
                    z4 = z5;
                    fArr4 = fArr7;
                    futureArr = futureArr2;
                }
                i2 = i11;
                z2 = z4;
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr);
                } catch (InterruptedException e) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
                } catch (ExecutionException e2) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
                }
            }
            if (z3) {
                CommonUtils.scale(this.n, 1.0f / ((float) FastMath.sqrt((double) i2)), fArr2, i3, z2);
                fArr2[i3] = fArr2[i3] / ((float) FastMath.sqrt(2.0d));
            }
        }
    }

    public void forward(FloatLargeArray floatLargeArray, long j, boolean z) {
        long j2;
        FloatLargeArray floatLargeArray2 = floatLargeArray;
        long j3 = j;
        boolean z2 = z;
        Class<FloatDCT_1D> cls = FloatDCT_1D.class;
        long j4 = this.nl;
        if (j4 != 1) {
            if (!this.useLargeArrays) {
                if (floatLargeArray.isLarge() || floatLargeArray.isConstant() || j3 >= 2147483647L) {
                    throw new IllegalArgumentException("The data array is too big.");
                }
                forward(floatLargeArray.getData(), (int) j3, z2);
            } else if (this.isPowerOfTwo) {
                float f = floatLargeArray2.getFloat((j4 + j3) - 1);
                long j5 = this.nl - 2;
                while (j5 >= 2) {
                    long j6 = j3 + j5;
                    long j7 = j6 - 1;
                    floatLargeArray2.setFloat(j6 + 1, floatLargeArray2.getFloat(j6) - floatLargeArray2.getFloat(j7));
                    floatLargeArray2.setFloat(j6, floatLargeArray2.getFloat(j6) + floatLargeArray2.getFloat(j7));
                    j5 -= 2;
                    j3 = j;
                }
                floatLargeArray2.setFloat(j3 + 1, floatLargeArray.getFloat(j) - f);
                floatLargeArray2.setFloat(j3, floatLargeArray.getFloat(j) + f);
                long j8 = this.nl;
                if (j8 > 4) {
                    FloatLargeArray floatLargeArray3 = floatLargeArray;
                    long j9 = j;
                    rftbsub(j8, floatLargeArray3, j9, this.ncl, this.wl, this.nwl);
                    CommonUtils.cftbsub(this.nl, floatLargeArray3, j9, this.ipl, this.nwl, this.wl);
                } else if (j8 == 4) {
                    CommonUtils.cftbsub(j8, floatLargeArray, j, this.ipl, this.nwl, this.wl);
                }
                CommonUtils.dctsub(this.nl, floatLargeArray, j, this.ncl, this.wl, this.nwl);
                if (z2) {
                    long j10 = this.nl;
                    CommonUtils.scale(j10, (float) FastMath.sqrt(2.0d / ((double) j10)), floatLargeArray, j, false);
                    floatLargeArray2.setFloat(j3, floatLargeArray.getFloat(j) / ((float) FastMath.sqrt(2.0d)));
                }
            } else {
                long j11 = j4 * 2;
                FloatLargeArray floatLargeArray4 = new FloatLargeArray(j11);
                long j12 = j11;
                LargeArrayUtils.arraycopy(floatLargeArray, j, floatLargeArray4, 0, this.nl);
                int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
                for (long j13 = this.nl; j13 < j12; j13++) {
                    floatLargeArray4.setFloat(j13, floatLargeArray4.getFloat((j12 - j13) - 1));
                }
                this.fft.realForward(floatLargeArray4);
                int i = 1;
                if (numberOfThreads <= 1 || this.nl <= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
                    j2 = j12;
                    for (long j14 = 0; j14 < this.nl; j14++) {
                        long j15 = j14 * 2;
                        long j16 = j15 + 1;
                        floatLargeArray2.setFloat(j3 + j14, (this.wl.getFloat(j15) * floatLargeArray4.getFloat(j15)) - (this.wl.getFloat(j16) * floatLargeArray4.getFloat(j16)));
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
                        final FloatLargeArray floatLargeArray5 = floatLargeArray;
                        final FloatLargeArray floatLargeArray6 = floatLargeArray4;
                        futureArr2[i4] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                for (long j = j18; j < j19; j++) {
                                    long j2 = 2 * j;
                                    long j3 = j2 + 1;
                                    floatLargeArray5.setFloat(j20 + j, (FloatDCT_1D.this.wl.getFloat(j2) * floatLargeArray6.getFloat(j2)) - (FloatDCT_1D.this.wl.getFloat(j3) * floatLargeArray6.getFloat(j3)));
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
                    CommonUtils.scale(this.nl, 1.0f / ((float) FastMath.sqrt((double) j2)), floatLargeArray, j, false);
                    floatLargeArray2.setFloat(j3, floatLargeArray.getFloat(j) / ((float) FastMath.sqrt(2.0d)));
                }
            }
        }
    }

    public void inverse(float[] fArr, boolean z) {
        inverse(fArr, 0, z);
    }

    public void inverse(FloatLargeArray floatLargeArray, boolean z) {
        inverse(floatLargeArray, 0, z);
    }

    public void inverse(float[] fArr, int i, boolean z) {
        float[] fArr2 = fArr;
        int i2 = i;
        boolean z2 = z;
        Class<FloatDCT_1D> cls = FloatDCT_1D.class;
        int i3 = this.n;
        if (i3 != 1) {
            if (this.useLargeArrays) {
                inverse(new FloatLargeArray(fArr2), (long) i2, z2);
                return;
            }
            int i4 = 2;
            if (this.isPowerOfTwo) {
                if (z2) {
                    CommonUtils.scale(i3, (float) FastMath.sqrt(2.0d / ((double) i3)), fArr2, i2, false);
                    fArr2[i2] = fArr2[i2] / ((float) FastMath.sqrt(2.0d));
                }
                CommonUtils.dctsub(this.n, fArr, i, this.nc, this.w, this.nw);
                int i5 = this.n;
                if (i5 > 4) {
                    float[] fArr3 = fArr;
                    int i6 = i;
                    CommonUtils.cftfsub(i5, fArr3, i6, this.ip, this.nw, this.w);
                    rftfsub(this.n, fArr3, i6, this.nc, this.w, this.nw);
                } else if (i5 == 4) {
                    CommonUtils.cftfsub(i5, fArr, i, this.ip, this.nw, this.w);
                }
                float f = fArr2[i2];
                float f2 = fArr2[i2 + 1];
                float f3 = f - f2;
                fArr2[i2] = f + f2;
                while (true) {
                    int i7 = this.n;
                    if (i4 < i7) {
                        int i8 = i2 + i4;
                        int i9 = i8 + 1;
                        fArr2[i8 - 1] = fArr2[i8] - fArr2[i9];
                        fArr2[i8] = fArr2[i8] + fArr2[i9];
                        i4 += 2;
                    } else {
                        fArr2[(i7 + i2) - 1] = f3;
                        return;
                    }
                }
            } else {
                int i10 = i3 * 2;
                if (z2) {
                    CommonUtils.scale(i3, (float) FastMath.sqrt((double) i10), fArr2, i2, false);
                    fArr2[i2] = fArr2[i2] * ((float) FastMath.sqrt(2.0d));
                }
                float[] fArr4 = new float[i10];
                if (ConcurrencyUtils.getNumberOfThreads() <= 1 || ((long) this.n) <= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
                    for (int i11 = 0; i11 < this.n; i11++) {
                        int i12 = i11 * 2;
                        float f4 = fArr2[i2 + i11];
                        float[] fArr5 = this.w;
                        fArr4[i12] = fArr5[i12] * f4;
                        int i13 = i12 + 1;
                        fArr4[i13] = (-fArr5[i13]) * f4;
                    }
                } else {
                    int i14 = this.n / 2;
                    Future[] futureArr = new Future[2];
                    int i15 = 0;
                    while (i15 < 2) {
                        final int i16 = i15 * i14;
                        final int i17 = i15 == 1 ? this.n : i16 + i14;
                        final float[] fArr6 = fArr;
                        int i18 = i15;
                        final int i19 = i;
                        Future[] futureArr2 = futureArr;
                        final float[] fArr7 = fArr4;
                        futureArr2[i18] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                for (int i = i16; i < i17; i++) {
                                    int i2 = i * 2;
                                    float f = fArr6[i19 + i];
                                    fArr7[i2] = FloatDCT_1D.this.w[i2] * f;
                                    int i3 = i2 + 1;
                                    fArr7[i3] = (-FloatDCT_1D.this.w[i3]) * f;
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
                this.fft.realInverse(fArr4, true);
                System.arraycopy(fArr4, 0, fArr2, i2, this.n);
            }
        }
    }

    public void inverse(FloatLargeArray floatLargeArray, long j, boolean z) {
        long j2;
        FloatLargeArray floatLargeArray2;
        FloatLargeArray floatLargeArray3 = floatLargeArray;
        long j3 = j;
        boolean z2 = z;
        Class<FloatDCT_1D> cls = FloatDCT_1D.class;
        long j4 = this.nl;
        if (j4 != 1) {
            if (!this.useLargeArrays) {
                if (floatLargeArray.isLarge() || floatLargeArray.isConstant() || j3 >= 2147483647L) {
                    throw new IllegalArgumentException("The data array is too big.");
                }
                inverse(floatLargeArray.getData(), (int) j3, z2);
            } else if (this.isPowerOfTwo) {
                if (z2) {
                    CommonUtils.scale(j4, (float) FastMath.sqrt(2.0d / ((double) j4)), floatLargeArray, j, false);
                    floatLargeArray3.setFloat(j3, floatLargeArray.getFloat(j) / ((float) FastMath.sqrt(2.0d)));
                }
                CommonUtils.dctsub(this.nl, floatLargeArray, j, this.ncl, this.wl, this.nwl);
                long j5 = this.nl;
                if (j5 > 4) {
                    FloatLargeArray floatLargeArray4 = floatLargeArray;
                    long j6 = j;
                    CommonUtils.cftfsub(j5, floatLargeArray4, j6, this.ipl, this.nwl, this.wl);
                    rftfsub(this.nl, floatLargeArray4, j6, this.ncl, this.wl, this.nwl);
                } else if (j5 == 4) {
                    CommonUtils.cftfsub(j5, floatLargeArray, j, this.ipl, this.nwl, this.wl);
                }
                long j7 = j3 + 1;
                float f = floatLargeArray.getFloat(j) - floatLargeArray3.getFloat(j7);
                floatLargeArray3.setFloat(j3, floatLargeArray.getFloat(j) + floatLargeArray3.getFloat(j7));
                long j8 = 2;
                while (true) {
                    long j9 = this.nl;
                    if (j8 < j9) {
                        long j10 = j3 + j8;
                        long j11 = j10 + 1;
                        floatLargeArray3.setFloat(j10 - 1, floatLargeArray3.getFloat(j10) - floatLargeArray3.getFloat(j11));
                        floatLargeArray3.setFloat(j10, floatLargeArray3.getFloat(j10) + floatLargeArray3.getFloat(j11));
                        j8 += 2;
                    } else {
                        floatLargeArray3.setFloat((j3 + j9) - 1, f);
                        return;
                    }
                }
            } else {
                long j12 = j4 * 2;
                if (z2) {
                    j2 = j12;
                    CommonUtils.scale(j4, (float) FastMath.sqrt((double) j12), floatLargeArray, j, false);
                    floatLargeArray3.setFloat(j3, floatLargeArray.getFloat(j) * ((float) FastMath.sqrt(2.0d)));
                } else {
                    j2 = j12;
                }
                final FloatLargeArray floatLargeArray5 = new FloatLargeArray(j2);
                int i = 1;
                if (ConcurrencyUtils.getNumberOfThreads() <= 1 || this.nl <= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
                    floatLargeArray2 = floatLargeArray5;
                    for (long j13 = 0; j13 < this.nl; j13++) {
                        long j14 = j13 * 2;
                        float f2 = floatLargeArray3.getFloat(j3 + j13);
                        FloatLargeArray floatLargeArray6 = floatLargeArray2;
                        floatLargeArray6.setFloat(j14, this.wl.getFloat(j14) * f2);
                        long j15 = j14 + 1;
                        floatLargeArray6.setFloat(j15, (-this.wl.getFloat(j15)) * f2);
                    }
                } else {
                    int i2 = 2;
                    long j16 = this.nl / ((long) 2);
                    Future[] futureArr = new Future[2];
                    int i3 = 0;
                    while (i3 < i2) {
                        final long j17 = ((long) i3) * j16;
                        final long j18 = i3 == i ? this.nl : j17 + j16;
                        int i4 = i3;
                        final FloatLargeArray floatLargeArray7 = floatLargeArray;
                        final long j19 = j;
                        FloatLargeArray floatLargeArray8 = floatLargeArray5;
                        futureArr[i4] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                for (long j = j17; j < j18; j++) {
                                    long j2 = 2 * j;
                                    float f = floatLargeArray7.getFloat(j19 + j);
                                    floatLargeArray5.setFloat(j2, FloatDCT_1D.this.wl.getFloat(j2) * f);
                                    long j3 = j2 + 1;
                                    floatLargeArray5.setFloat(j3, (-FloatDCT_1D.this.wl.getFloat(j3)) * f);
                                }
                            }
                        });
                        i3 = i4 + 1;
                        i2 = i2;
                        i = 1;
                    }
                    floatLargeArray2 = floatLargeArray5;
                    try {
                        ConcurrencyUtils.waitForCompletion(futureArr);
                    } catch (InterruptedException e) {
                        Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
                    } catch (ExecutionException e2) {
                        Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
                    }
                }
                FloatLargeArray floatLargeArray9 = floatLargeArray2;
                this.fft.realInverse(floatLargeArray9, true);
                LargeArrayUtils.arraycopy(floatLargeArray9, 0, floatLargeArray, j, this.nl);
            }
        }
    }

    private float[] makect(int i) {
        int i2 = i * 2;
        float f = PI / ((float) i2);
        float[] fArr = new float[i2];
        fArr[0] = 1.0f;
        for (int i3 = 1; i3 < i; i3++) {
            int i4 = i3 * 2;
            double d = (double) (((float) i3) * f);
            fArr[i4] = (float) FastMath.cos(d);
            fArr[i4 + 1] = -((float) FastMath.sin(d));
        }
        return fArr;
    }

    private FloatLargeArray makect(long j) {
        long j2 = j * 2;
        float f = PI / ((float) j2);
        FloatLargeArray floatLargeArray = new FloatLargeArray(j2);
        floatLargeArray.setFloat(0, 1.0f);
        for (long j3 = 1; j3 < j; j3++) {
            long j4 = j3 * 2;
            double d = (double) (((float) j3) * f);
            floatLargeArray.setFloat(j4, (float) FastMath.cos(d));
            floatLargeArray.setFloat(j4 + 1, -((float) FastMath.sin(d)));
        }
        return floatLargeArray;
    }

    private static void rftfsub(int i, float[] fArr, int i2, int i3, float[] fArr2, int i4) {
        int i5 = i >> 1;
        int i6 = (i3 * 2) / i5;
        int i7 = 0;
        for (int i8 = 2; i8 < i5; i8 += 2) {
            i7 += i6;
            float f = 0.5f - fArr2[(i4 + i3) - i7];
            float f2 = fArr2[i4 + i7];
            int i9 = i2 + i8;
            int i10 = i2 + (i - i8);
            float f3 = fArr[i9];
            float f4 = f3 - fArr[i10];
            int i11 = i9 + 1;
            int i12 = i10 + 1;
            float f5 = fArr[i11] + fArr[i12];
            float f6 = (f * f4) - (f2 * f5);
            float f7 = (f * f5) + (f2 * f4);
            fArr[i9] = f3 - f6;
            fArr[i11] = fArr[i11] - f7;
            fArr[i10] = fArr[i10] + f6;
            fArr[i12] = fArr[i12] - f7;
        }
    }

    private static void rftfsub(long j, FloatLargeArray floatLargeArray, long j2, long j3, FloatLargeArray floatLargeArray2, long j4) {
        FloatLargeArray floatLargeArray3 = floatLargeArray;
        FloatLargeArray floatLargeArray4 = floatLargeArray2;
        long j5 = j >> 1;
        long j6 = (j3 * 2) / j5;
        long j7 = 0;
        long j8 = 2;
        while (j8 < j5) {
            long j9 = j7 + j6;
            float f = 0.5f - floatLargeArray4.getFloat((j4 + j3) - j9);
            float f2 = floatLargeArray4.getFloat(j4 + j9);
            long j10 = j2 + j8;
            long j11 = j2 + (j - j8);
            float f3 = floatLargeArray3.getFloat(j10) - floatLargeArray3.getFloat(j11);
            long j12 = j10 + 1;
            long j13 = j9;
            long j14 = j11 + 1;
            float f4 = floatLargeArray3.getFloat(j12) + floatLargeArray3.getFloat(j14);
            float f5 = (f * f3) - (f2 * f4);
            float f6 = (f * f4) + (f2 * f3);
            floatLargeArray3.setFloat(j10, floatLargeArray3.getFloat(j10) - f5);
            floatLargeArray3.setFloat(j12, floatLargeArray3.getFloat(j12) - f6);
            floatLargeArray3.setFloat(j11, floatLargeArray3.getFloat(j11) + f5);
            floatLargeArray3.setFloat(j14, floatLargeArray3.getFloat(j14) - f6);
            j8 += 2;
            j5 = j5;
            j6 = j6;
            j7 = j13;
            floatLargeArray4 = floatLargeArray2;
        }
    }

    private static void rftbsub(int i, float[] fArr, int i2, int i3, float[] fArr2, int i4) {
        int i5 = i >> 1;
        int i6 = (i3 * 2) / i5;
        int i7 = 0;
        for (int i8 = 2; i8 < i5; i8 += 2) {
            i7 += i6;
            float f = 0.5f - fArr2[(i4 + i3) - i7];
            float f2 = fArr2[i4 + i7];
            int i9 = i2 + i8;
            int i10 = i2 + (i - i8);
            float f3 = fArr[i9];
            float f4 = f3 - fArr[i10];
            int i11 = i9 + 1;
            int i12 = i10 + 1;
            float f5 = fArr[i11] + fArr[i12];
            float f6 = (f * f4) + (f2 * f5);
            float f7 = (f * f5) - (f2 * f4);
            fArr[i9] = f3 - f6;
            fArr[i11] = fArr[i11] - f7;
            fArr[i10] = fArr[i10] + f6;
            fArr[i12] = fArr[i12] - f7;
        }
    }

    private static void rftbsub(long j, FloatLargeArray floatLargeArray, long j2, long j3, FloatLargeArray floatLargeArray2, long j4) {
        FloatLargeArray floatLargeArray3 = floatLargeArray;
        FloatLargeArray floatLargeArray4 = floatLargeArray2;
        long j5 = j >> 1;
        long j6 = (j3 * 2) / j5;
        long j7 = 0;
        long j8 = 2;
        while (j8 < j5) {
            long j9 = j7 + j6;
            float f = 0.5f - floatLargeArray4.getFloat((j4 + j3) - j9);
            float f2 = floatLargeArray4.getFloat(j4 + j9);
            long j10 = j2 + j8;
            long j11 = j2 + (j - j8);
            float f3 = floatLargeArray3.getFloat(j10) - floatLargeArray3.getFloat(j11);
            long j12 = j10 + 1;
            long j13 = j9;
            long j14 = j11 + 1;
            float f4 = floatLargeArray3.getFloat(j12) + floatLargeArray3.getFloat(j14);
            float f5 = (f * f3) + (f2 * f4);
            float f6 = (f * f4) - (f2 * f3);
            floatLargeArray3.setFloat(j10, floatLargeArray3.getFloat(j10) - f5);
            floatLargeArray3.setFloat(j12, floatLargeArray3.getFloat(j12) - f6);
            floatLargeArray3.setFloat(j11, floatLargeArray3.getFloat(j11) + f5);
            floatLargeArray3.setFloat(j14, floatLargeArray3.getFloat(j14) - f6);
            j8 += 2;
            j5 = j5;
            j6 = j6;
            j7 = j13;
            floatLargeArray4 = floatLargeArray2;
        }
    }
}
