package org.jtransforms.fft;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.math3.util.FastMath;
import org.jtransforms.utils.CommonUtils;
import pl.edu.icm.jlargearrays.ConcurrencyUtils;
import pl.edu.icm.jlargearrays.DoubleLargeArray;
import pl.edu.icm.jlargearrays.LargeArray;
import pl.edu.icm.jlargearrays.LargeArrayUtils;
import pl.edu.icm.jlargearrays.LongLargeArray;

public final class DoubleFFT_1D {
    private static final double PI = 3.141592653589793d;
    private static final double TWO_PI = 6.283185307179586d;
    private static final int[] factors = {4, 2, 3, 5};
    /* access modifiers changed from: private */
    public double[] bk1;
    /* access modifiers changed from: private */
    public DoubleLargeArray bk1l;
    /* access modifiers changed from: private */
    public double[] bk2;
    /* access modifiers changed from: private */
    public DoubleLargeArray bk2l;
    private int[] ip;
    private LongLargeArray ipl;
    private int n;
    private int nBluestein;
    private long nBluesteinl;
    private int nc;
    private long ncl;
    private long nl;
    private int nw;
    private long nwl;
    private Plans plan;
    private boolean useLargeArrays;
    private double[] w;
    private DoubleLargeArray wl;
    private double[] wtable;
    private double[] wtable_r;
    private DoubleLargeArray wtable_rl;
    private DoubleLargeArray wtablel;

    private enum Plans {
        SPLIT_RADIX,
        MIXED_RADIX,
        BLUESTEIN
    }

    public DoubleFFT_1D(long j) {
        if (j >= 1) {
            boolean z = CommonUtils.isUseLargeArrays() || j * 2 > ((long) LargeArray.getMaxSizeOf32bitArray());
            this.useLargeArrays = z;
            this.n = (int) j;
            this.nl = j;
            if (!z) {
                if (CommonUtils.isPowerOf2(j)) {
                    this.plan = Plans.SPLIT_RADIX;
                    int[] iArr = new int[(((int) FastMath.ceil((double) ((1 << (((int) (FastMath.log(((double) j) + 0.5d) / FastMath.log(2.0d))) / 2)) + 2))) + 2)];
                    this.ip = iArr;
                    int i = this.n;
                    double[] dArr = new double[i];
                    this.w = dArr;
                    int i2 = (i * 2) >> 2;
                    this.nw = i2;
                    CommonUtils.makewt(i2, iArr, dArr);
                    int i3 = this.n >> 2;
                    this.nc = i3;
                    CommonUtils.makect(i3, this.w, this.nw, this.ip);
                } else if (CommonUtils.getReminder(j, factors) >= 211) {
                    this.plan = Plans.BLUESTEIN;
                    int nextPow2 = CommonUtils.nextPow2((this.n * 2) - 1);
                    this.nBluestein = nextPow2;
                    this.bk1 = new double[(nextPow2 * 2)];
                    this.bk2 = new double[(nextPow2 * 2)];
                    int[] iArr2 = new int[(((int) FastMath.ceil((double) ((1 << (((int) (FastMath.log(((double) nextPow2) + 0.5d) / FastMath.log(2.0d))) / 2)) + 2))) + 2)];
                    this.ip = iArr2;
                    int i4 = this.nBluestein;
                    double[] dArr2 = new double[i4];
                    this.w = dArr2;
                    int i5 = (i4 * 2) >> 2;
                    this.nw = i5;
                    CommonUtils.makewt(i5, iArr2, dArr2);
                    int i6 = this.nBluestein >> 2;
                    this.nc = i6;
                    CommonUtils.makect(i6, this.w, this.nw, this.ip);
                    bluesteini();
                } else {
                    this.plan = Plans.MIXED_RADIX;
                    int i7 = this.n;
                    this.wtable = new double[((i7 * 4) + 15)];
                    this.wtable_r = new double[((i7 * 2) + 15)];
                    cffti();
                    rffti();
                }
            } else if (CommonUtils.isPowerOf2(j)) {
                this.plan = Plans.SPLIT_RADIX;
                this.ipl = new LongLargeArray(((long) FastMath.ceil((double) ((1 << ((int) (((long) (FastMath.log(((double) this.nl) + 0.5d) / FastMath.log(2.0d))) / 2))) + 2))) + 2);
                DoubleLargeArray doubleLargeArray = new DoubleLargeArray(this.nl);
                this.wl = doubleLargeArray;
                long j2 = (this.nl * 2) >> 2;
                this.nwl = j2;
                CommonUtils.makewt(j2, this.ipl, doubleLargeArray);
                long j3 = this.nl >> 2;
                this.ncl = j3;
                CommonUtils.makect(j3, this.wl, this.nwl, this.ipl);
            } else if (CommonUtils.getReminder(this.nl, factors) >= 211) {
                this.plan = Plans.BLUESTEIN;
                this.nBluesteinl = CommonUtils.nextPow2((this.nl * 2) - 1);
                this.bk1l = new DoubleLargeArray(this.nBluesteinl * 2);
                this.bk2l = new DoubleLargeArray(this.nBluesteinl * 2);
                this.ipl = new LongLargeArray(((long) FastMath.ceil((double) ((1 << ((int) (((long) (FastMath.log(((double) this.nBluesteinl) + 0.5d) / FastMath.log(2.0d))) / 2))) + 2))) + 2);
                DoubleLargeArray doubleLargeArray2 = new DoubleLargeArray(this.nBluesteinl);
                this.wl = doubleLargeArray2;
                long j4 = (this.nBluesteinl * 2) >> 2;
                this.nwl = j4;
                CommonUtils.makewt(j4, this.ipl, doubleLargeArray2);
                long j5 = this.nBluesteinl >> 2;
                this.ncl = j5;
                CommonUtils.makect(j5, this.wl, this.nwl, this.ipl);
                bluesteinil();
            } else {
                this.plan = Plans.MIXED_RADIX;
                this.wtablel = new DoubleLargeArray((this.nl * 4) + 15);
                this.wtable_rl = new DoubleLargeArray((this.nl * 2) + 15);
                cfftil();
                rfftil();
            }
        } else {
            throw new IllegalArgumentException("n must be greater than 0");
        }
    }

    public void complexForward(double[] dArr) {
        complexForward(dArr, 0);
    }

    public void complexForward(DoubleLargeArray doubleLargeArray) {
        complexForward(doubleLargeArray, 0);
    }

    public void complexForward(double[] dArr, int i) {
        if (this.useLargeArrays) {
            complexForward(new DoubleLargeArray(dArr), (long) i);
        } else if (this.n != 1) {
            int i2 = AnonymousClass29.$SwitchMap$org$jtransforms$fft$DoubleFFT_1D$Plans[this.plan.ordinal()];
            if (i2 == 1) {
                CommonUtils.cftbsub(this.n * 2, dArr, i, this.ip, this.nw, this.w);
            } else if (i2 == 2) {
                cfftf(dArr, i, -1);
            } else if (i2 == 3) {
                bluestein_complex(dArr, i, -1);
            }
        }
    }

    /* renamed from: org.jtransforms.fft.DoubleFFT_1D$29  reason: invalid class name */
    static /* synthetic */ class AnonymousClass29 {
        static final /* synthetic */ int[] $SwitchMap$org$jtransforms$fft$DoubleFFT_1D$Plans;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                org.jtransforms.fft.DoubleFFT_1D$Plans[] r0 = org.jtransforms.fft.DoubleFFT_1D.Plans.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$jtransforms$fft$DoubleFFT_1D$Plans = r0
                org.jtransforms.fft.DoubleFFT_1D$Plans r1 = org.jtransforms.fft.DoubleFFT_1D.Plans.SPLIT_RADIX     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$jtransforms$fft$DoubleFFT_1D$Plans     // Catch:{ NoSuchFieldError -> 0x001d }
                org.jtransforms.fft.DoubleFFT_1D$Plans r1 = org.jtransforms.fft.DoubleFFT_1D.Plans.MIXED_RADIX     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$jtransforms$fft$DoubleFFT_1D$Plans     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.jtransforms.fft.DoubleFFT_1D$Plans r1 = org.jtransforms.fft.DoubleFFT_1D.Plans.BLUESTEIN     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.DoubleFFT_1D.AnonymousClass29.<clinit>():void");
        }
    }

    public void complexForward(DoubleLargeArray doubleLargeArray, long j) {
        if (!this.useLargeArrays) {
            if (doubleLargeArray.isLarge() || doubleLargeArray.isConstant() || j >= 2147483647L) {
                throw new IllegalArgumentException("The data array is too big.");
            }
            complexForward(doubleLargeArray.getData(), (int) j);
        } else if (this.nl != 1) {
            int i = AnonymousClass29.$SwitchMap$org$jtransforms$fft$DoubleFFT_1D$Plans[this.plan.ordinal()];
            if (i == 1) {
                CommonUtils.cftbsub(2 * this.nl, doubleLargeArray, j, this.ipl, this.nwl, this.wl);
            } else if (i == 2) {
                cfftf(doubleLargeArray, j, -1);
            } else if (i == 3) {
                bluestein_complex(doubleLargeArray, j, -1);
            }
        }
    }

    public void complexInverse(double[] dArr, boolean z) {
        complexInverse(dArr, 0, z);
    }

    public void complexInverse(DoubleLargeArray doubleLargeArray, boolean z) {
        complexInverse(doubleLargeArray, 0, z);
    }

    public void complexInverse(double[] dArr, int i, boolean z) {
        if (this.useLargeArrays) {
            complexInverse(new DoubleLargeArray(dArr), (long) i, z);
        } else if (this.n != 1) {
            int i2 = AnonymousClass29.$SwitchMap$org$jtransforms$fft$DoubleFFT_1D$Plans[this.plan.ordinal()];
            if (i2 == 1) {
                CommonUtils.cftfsub(this.n * 2, dArr, i, this.ip, this.nw, this.w);
            } else if (i2 == 2) {
                cfftf(dArr, i, 1);
            } else if (i2 == 3) {
                bluestein_complex(dArr, i, 1);
            }
            if (z) {
                int i3 = this.n;
                CommonUtils.scale(i3, 1.0d / ((double) i3), dArr, i, true);
            }
        }
    }

    public void complexInverse(DoubleLargeArray doubleLargeArray, long j, boolean z) {
        DoubleLargeArray doubleLargeArray2 = doubleLargeArray;
        long j2 = j;
        boolean z2 = z;
        if (!this.useLargeArrays) {
            if (doubleLargeArray.isLarge() || doubleLargeArray.isConstant() || j2 >= 2147483647L) {
                throw new IllegalArgumentException("The data array is too big.");
            }
            complexInverse(doubleLargeArray.getData(), (int) j2, z2);
        } else if (this.nl != 1) {
            int i = AnonymousClass29.$SwitchMap$org$jtransforms$fft$DoubleFFT_1D$Plans[this.plan.ordinal()];
            if (i == 1) {
                CommonUtils.cftfsub(2 * this.nl, doubleLargeArray, j, this.ipl, this.nwl, this.wl);
            } else if (i == 2) {
                cfftf(doubleLargeArray, j2, 1);
            } else if (i == 3) {
                bluestein_complex(doubleLargeArray, j2, 1);
            }
            if (z2) {
                long j3 = this.nl;
                CommonUtils.scale(j3, 1.0d / ((double) j3), doubleLargeArray, j, true);
            }
        }
    }

    public void realForward(double[] dArr) {
        realForward(dArr, 0);
    }

    public void realForward(DoubleLargeArray doubleLargeArray) {
        realForward(doubleLargeArray, 0);
    }

    public void realForward(double[] dArr, int i) {
        if (this.useLargeArrays) {
            realForward(new DoubleLargeArray(dArr), (long) i);
        } else if (this.n != 1) {
            int i2 = AnonymousClass29.$SwitchMap$org$jtransforms$fft$DoubleFFT_1D$Plans[this.plan.ordinal()];
            if (i2 == 1) {
                int i3 = this.n;
                if (i3 > 4) {
                    double[] dArr2 = dArr;
                    int i4 = i;
                    CommonUtils.cftfsub(i3, dArr2, i4, this.ip, this.nw, this.w);
                    CommonUtils.rftfsub(this.n, dArr2, i4, this.nc, this.w, this.nw);
                } else if (i3 == 4) {
                    CommonUtils.cftx020(dArr, i);
                }
                double d = dArr[i];
                int i5 = i + 1;
                double d2 = dArr[i5];
                dArr[i] = d + d2;
                dArr[i5] = d - d2;
            } else if (i2 == 2) {
                rfftf(dArr, i);
                for (int i6 = this.n - 1; i6 >= 2; i6--) {
                    int i7 = i + i6;
                    double d3 = dArr[i7];
                    int i8 = i7 - 1;
                    dArr[i7] = dArr[i8];
                    dArr[i8] = d3;
                }
            } else if (i2 == 3) {
                bluestein_real_forward(dArr, i);
            }
        }
    }

    public void realForward(DoubleLargeArray doubleLargeArray, long j) {
        DoubleLargeArray doubleLargeArray2 = doubleLargeArray;
        long j2 = j;
        if (!this.useLargeArrays) {
            if (doubleLargeArray.isLarge() || doubleLargeArray.isConstant() || j2 >= 2147483647L) {
                throw new IllegalArgumentException("The data array is too big.");
            }
            realForward(doubleLargeArray.getData(), (int) j2);
        } else if (this.nl != 1) {
            int i = AnonymousClass29.$SwitchMap$org$jtransforms$fft$DoubleFFT_1D$Plans[this.plan.ordinal()];
            if (i == 1) {
                long j3 = this.nl;
                if (j3 > 4) {
                    DoubleLargeArray doubleLargeArray3 = doubleLargeArray;
                    long j4 = j;
                    CommonUtils.cftfsub(j3, doubleLargeArray3, j4, this.ipl, this.nwl, this.wl);
                    CommonUtils.rftfsub(this.nl, doubleLargeArray3, j4, this.ncl, this.wl, this.nwl);
                } else if (j3 == 4) {
                    CommonUtils.cftx020(doubleLargeArray, j);
                }
                long j5 = j2 + 1;
                double d = doubleLargeArray.getDouble(j) - doubleLargeArray2.getDouble(j5);
                doubleLargeArray2.setDouble(j2, doubleLargeArray.getDouble(j) + doubleLargeArray2.getDouble(j5));
                doubleLargeArray2.setDouble(j5, d);
            } else if (i == 2) {
                rfftf(doubleLargeArray, j);
                long j6 = this.nl;
                while (true) {
                    j6--;
                    if (j6 >= 2) {
                        long j7 = j2 + j6;
                        double d2 = doubleLargeArray2.getDouble(j7);
                        long j8 = j7 - 1;
                        doubleLargeArray2.setDouble(j7, doubleLargeArray2.getDouble(j8));
                        doubleLargeArray2.setDouble(j8, d2);
                    } else {
                        return;
                    }
                }
            } else if (i == 3) {
                bluestein_real_forward(doubleLargeArray, j);
            }
        }
    }

    public void realForwardFull(double[] dArr) {
        realForwardFull(dArr, 0);
    }

    public void realForwardFull(DoubleLargeArray doubleLargeArray) {
        realForwardFull(doubleLargeArray, 0);
    }

    public void realForwardFull(double[] dArr, int i) {
        int i2;
        double[] dArr2 = dArr;
        int i3 = i;
        Class<DoubleFFT_1D> cls = DoubleFFT_1D.class;
        if (this.useLargeArrays) {
            realForwardFull(new DoubleLargeArray(dArr2), (long) i3);
            return;
        }
        int i4 = this.n * 2;
        int i5 = AnonymousClass29.$SwitchMap$org$jtransforms$fft$DoubleFFT_1D$Plans[this.plan.ordinal()];
        if (i5 == 1) {
            realForward(dArr, i);
            int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
            if (numberOfThreads <= 1 || ((long) (this.n / 2)) <= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
                for (int i6 = 0; i6 < this.n / 2; i6++) {
                    int i7 = i6 * 2;
                    int i8 = ((i4 - i7) % i4) + i3;
                    int i9 = i7 + i3;
                    dArr2[i8] = dArr2[i9];
                    dArr2[i8 + 1] = -dArr2[i9 + 1];
                }
            } else {
                Future[] futureArr = new Future[numberOfThreads];
                int i10 = (this.n / 2) / numberOfThreads;
                int i11 = 0;
                while (i11 < numberOfThreads) {
                    final int i12 = i11 * i10;
                    final int i13 = i11 == numberOfThreads + -1 ? this.n / 2 : i12 + i10;
                    int i14 = i11;
                    final int i15 = i;
                    Future[] futureArr2 = futureArr;
                    final int i16 = i4;
                    int i17 = numberOfThreads;
                    final double[] dArr3 = dArr;
                    futureArr2[i14] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            for (int i = i12; i < i13; i++) {
                                int i2 = i * 2;
                                int i3 = i15;
                                int i4 = i16;
                                int i5 = ((i4 - i2) % i4) + i3;
                                double[] dArr = dArr3;
                                dArr[i5] = dArr[i3 + i2];
                                dArr[i5 + 1] = -dArr[i3 + i2 + 1];
                            }
                        }
                    });
                    i11 = i14 + 1;
                    futureArr = futureArr2;
                    numberOfThreads = i17;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr);
                } catch (InterruptedException e) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
                } catch (ExecutionException e2) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
                }
            }
            int i18 = i3 + 1;
            dArr2[this.n + i3] = -dArr2[i18];
            dArr2[i18] = 0.0d;
        } else if (i5 == 2) {
            rfftf(dArr, i);
            int i19 = this.n;
            if (i19 % 2 == 0) {
                i2 = i19 / 2;
            } else {
                i2 = (i19 + 1) / 2;
            }
            for (int i20 = 1; i20 < i2; i20++) {
                int i21 = i20 * 2;
                int i22 = (i3 + i4) - i21;
                int i23 = i21 + i3;
                dArr2[i22 + 1] = -dArr2[i23];
                dArr2[i22] = dArr2[i23 - 1];
            }
            int i24 = 1;
            while (true) {
                int i25 = this.n;
                if (i24 < i25) {
                    int i26 = (i25 + i3) - i24;
                    int i27 = i26 + 1;
                    double d = dArr2[i27];
                    dArr2[i27] = dArr2[i26];
                    dArr2[i26] = d;
                    i24++;
                } else {
                    dArr2[i3 + 1] = 0.0d;
                    return;
                }
            }
        } else if (i5 == 3) {
            bluestein_real_full(dArr2, i3, -1);
        }
    }

    public void realForwardFull(DoubleLargeArray doubleLargeArray, long j) {
        long j2;
        DoubleLargeArray doubleLargeArray2 = doubleLargeArray;
        long j3 = j;
        Class<DoubleFFT_1D> cls = DoubleFFT_1D.class;
        if (this.useLargeArrays) {
            long j4 = this.nl * 2;
            int i = AnonymousClass29.$SwitchMap$org$jtransforms$fft$DoubleFFT_1D$Plans[this.plan.ordinal()];
            if (i == 1) {
                realForward(doubleLargeArray, j);
                int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
                if (numberOfThreads <= 1 || this.nl / 2 <= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
                    for (long j5 = 0; j5 < this.nl / 2; j5++) {
                        long j6 = j5 * 2;
                        long j7 = ((j4 - j6) % j4) + j3;
                        long j8 = j6 + j3;
                        doubleLargeArray2.setDouble(j7, doubleLargeArray2.getDouble(j8));
                        doubleLargeArray2.setDouble(j7 + 1, -doubleLargeArray2.getDouble(j8 + 1));
                    }
                } else {
                    Future[] futureArr = new Future[numberOfThreads];
                    long j9 = (this.nl / 2) / ((long) numberOfThreads);
                    int i2 = 0;
                    while (i2 < numberOfThreads) {
                        final long j10 = ((long) i2) * j9;
                        final long j11 = i2 == numberOfThreads + -1 ? this.nl / 2 : j10 + j9;
                        int i3 = i2;
                        final long j12 = j;
                        final long j13 = j4;
                        Future[] futureArr2 = futureArr;
                        final DoubleLargeArray doubleLargeArray3 = doubleLargeArray;
                        futureArr2[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                for (long j = j10; j < j11; j++) {
                                    long j2 = 2 * j;
                                    long j3 = j12;
                                    long j4 = j13;
                                    long j5 = ((j4 - j2) % j4) + j3;
                                    DoubleLargeArray doubleLargeArray = doubleLargeArray3;
                                    doubleLargeArray.setDouble(j5, doubleLargeArray.getDouble(j3 + j2));
                                    DoubleLargeArray doubleLargeArray2 = doubleLargeArray3;
                                    doubleLargeArray2.setDouble(j5 + 1, -doubleLargeArray2.getDouble(j12 + j2 + 1));
                                }
                            }
                        });
                        i2 = i3 + 1;
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
                long j14 = j3 + 1;
                doubleLargeArray2.setDouble(this.nl + j3, -doubleLargeArray2.getDouble(j14));
                doubleLargeArray2.setDouble(j14, 0.0d);
            } else if (i == 2) {
                rfftf(doubleLargeArray, j);
                long j15 = this.nl;
                if (j15 % 2 == 0) {
                    j2 = j15 / 2;
                } else {
                    j2 = (j15 + 1) / 2;
                }
                long j16 = 1;
                while (j16 < j2) {
                    long j17 = j16 * 2;
                    long j18 = (j3 + j4) - j17;
                    long j19 = j17 + j3;
                    doubleLargeArray2.setDouble(j18 + 1, -doubleLargeArray2.getDouble(j19));
                    doubleLargeArray2.setDouble(j18, doubleLargeArray2.getDouble(j19 - 1));
                    j16++;
                    j2 = j2;
                }
                long j20 = 1;
                while (true) {
                    long j21 = this.nl;
                    if (j20 < j21) {
                        long j22 = (j21 + j3) - j20;
                        long j23 = j22 + 1;
                        double d = doubleLargeArray2.getDouble(j23);
                        doubleLargeArray2.setDouble(j23, doubleLargeArray2.getDouble(j22));
                        doubleLargeArray2.setDouble(j22, d);
                        j20++;
                    } else {
                        doubleLargeArray2.setDouble(j3 + 1, 0.0d);
                        return;
                    }
                }
            } else if (i == 3) {
                bluestein_real_full(doubleLargeArray, j, -1);
            }
        } else if (doubleLargeArray.isLarge() || doubleLargeArray.isConstant() || j3 >= 2147483647L) {
            throw new IllegalArgumentException("The data array is too big.");
        } else {
            realForwardFull(doubleLargeArray.getData(), (int) j3);
        }
    }

    public void realInverse(double[] dArr, boolean z) {
        realInverse(dArr, 0, z);
    }

    public void realInverse(DoubleLargeArray doubleLargeArray, boolean z) {
        realInverse(doubleLargeArray, 0, z);
    }

    public void realInverse(double[] dArr, int i, boolean z) {
        double[] dArr2 = dArr;
        int i2 = i;
        boolean z2 = z;
        if (this.useLargeArrays) {
            realInverse(new DoubleLargeArray(dArr), (long) i2, z2);
        } else if (this.n != 1) {
            int i3 = AnonymousClass29.$SwitchMap$org$jtransforms$fft$DoubleFFT_1D$Plans[this.plan.ordinal()];
            if (i3 != 1) {
                if (i3 == 2) {
                    for (int i4 = 2; i4 < this.n; i4++) {
                        int i5 = i2 + i4;
                        int i6 = i5 - 1;
                        double d = dArr2[i6];
                        dArr2[i6] = dArr2[i5];
                        dArr2[i5] = d;
                    }
                    rfftb(dArr, i);
                    if (z2) {
                        int i7 = this.n;
                        CommonUtils.scale(i7, 1.0d / ((double) i7), dArr, i, false);
                    }
                } else if (i3 == 3) {
                    bluestein_real_inverse(dArr, i);
                    if (z2) {
                        int i8 = this.n;
                        CommonUtils.scale(i8, 1.0d / ((double) i8), dArr, i, false);
                    }
                }
            } else {
                int i9 = i2 + 1;
                double d2 = (dArr2[i2] - dArr2[i9]) * 0.5d;
                dArr2[i9] = d2;
                dArr2[i2] = dArr2[i2] - d2;
                int i10 = this.n;
                if (i10 > 4) {
                    double[] dArr3 = dArr;
                    int i11 = i;
                    CommonUtils.rftfsub(i10, dArr3, i11, this.nc, this.w, this.nw);
                    CommonUtils.cftbsub(this.n, dArr3, i11, this.ip, this.nw, this.w);
                } else if (i10 == 4) {
                    CommonUtils.cftxc020(dArr, i);
                }
                if (z2) {
                    int i12 = this.n;
                    CommonUtils.scale(i12, 1.0d / (((double) i12) / 2.0d), dArr, i, false);
                }
            }
        }
    }

    public void realInverse(DoubleLargeArray doubleLargeArray, long j, boolean z) {
        DoubleLargeArray doubleLargeArray2 = doubleLargeArray;
        long j2 = j;
        boolean z2 = z;
        if (this.useLargeArrays) {
            long j3 = 1;
            if (this.nl != 1) {
                int i = AnonymousClass29.$SwitchMap$org$jtransforms$fft$DoubleFFT_1D$Plans[this.plan.ordinal()];
                if (i == 1) {
                    long j4 = 1 + j2;
                    doubleLargeArray2.setDouble(j4, (doubleLargeArray.getDouble(j) - doubleLargeArray2.getDouble(j4)) * 0.5d);
                    doubleLargeArray2.setDouble(j2, doubleLargeArray.getDouble(j) - doubleLargeArray2.getDouble(j4));
                    long j5 = this.nl;
                    if (j5 > 4) {
                        DoubleLargeArray doubleLargeArray3 = doubleLargeArray;
                        long j6 = j;
                        CommonUtils.rftfsub(j5, doubleLargeArray3, j6, this.ncl, this.wl, this.nwl);
                        CommonUtils.cftbsub(this.nl, doubleLargeArray3, j6, this.ipl, this.nwl, this.wl);
                    } else if (j5 == 4) {
                        CommonUtils.cftxc020(doubleLargeArray, j);
                    }
                    if (z2) {
                        long j7 = this.nl;
                        CommonUtils.scale(j7, 1.0d / (((double) j7) / 2.0d), doubleLargeArray, j, false);
                    }
                } else if (i == 2) {
                    for (long j8 = 2; j8 < this.nl; j8++) {
                        long j9 = j2 + j8;
                        long j10 = j9 - j3;
                        double d = doubleLargeArray2.getDouble(j10);
                        doubleLargeArray2.setDouble(j10, doubleLargeArray2.getDouble(j9));
                        doubleLargeArray2.setDouble(j9, d);
                        j3 = 1;
                    }
                    rfftb(doubleLargeArray, j);
                    if (z2) {
                        long j11 = this.nl;
                        CommonUtils.scale(j11, 1.0d / ((double) j11), doubleLargeArray, j, false);
                    }
                } else if (i == 3) {
                    bluestein_real_inverse(doubleLargeArray, j);
                    if (z2) {
                        long j12 = this.nl;
                        CommonUtils.scale(j12, 1.0d / ((double) j12), doubleLargeArray, j, false);
                    }
                }
            }
        } else if (doubleLargeArray.isLarge() || doubleLargeArray.isConstant() || j2 >= 2147483647L) {
            throw new IllegalArgumentException("The data array is too big.");
        } else {
            realInverse(doubleLargeArray.getData(), (int) j2, z2);
        }
    }

    public void realInverseFull(double[] dArr, boolean z) {
        realInverseFull(dArr, 0, z);
    }

    public void realInverseFull(DoubleLargeArray doubleLargeArray, boolean z) {
        realInverseFull(doubleLargeArray, 0, z);
    }

    public void realInverseFull(double[] dArr, int i, boolean z) {
        int i2;
        double[] dArr2 = dArr;
        int i3 = i;
        boolean z2 = z;
        Class<DoubleFFT_1D> cls = DoubleFFT_1D.class;
        if (this.useLargeArrays) {
            realInverseFull(new DoubleLargeArray(dArr2), (long) i3, z2);
            return;
        }
        int i4 = this.n * 2;
        int i5 = AnonymousClass29.$SwitchMap$org$jtransforms$fft$DoubleFFT_1D$Plans[this.plan.ordinal()];
        boolean z3 = true;
        if (i5 == 1) {
            realInverse2(dArr, i, z);
            int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
            if (numberOfThreads <= 1 || ((long) (this.n / 2)) <= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
                for (int i6 = 0; i6 < this.n / 2; i6++) {
                    int i7 = i6 * 2;
                    int i8 = ((i4 - i7) % i4) + i3;
                    int i9 = i7 + i3;
                    dArr2[i8] = dArr2[i9];
                    dArr2[i8 + 1] = -dArr2[i9 + 1];
                }
            } else {
                Future[] futureArr = new Future[numberOfThreads];
                int i10 = (this.n / 2) / numberOfThreads;
                int i11 = 0;
                while (i11 < numberOfThreads) {
                    final int i12 = i11 * i10;
                    final int i13 = i11 == numberOfThreads + -1 ? this.n / 2 : i12 + i10;
                    int i14 = i11;
                    final int i15 = i;
                    Future[] futureArr2 = futureArr;
                    final int i16 = i4;
                    boolean z4 = z3;
                    final double[] dArr3 = dArr;
                    futureArr2[i14] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            for (int i = i12; i < i13; i++) {
                                int i2 = i * 2;
                                int i3 = i15;
                                int i4 = i16;
                                int i5 = ((i4 - i2) % i4) + i3;
                                double[] dArr = dArr3;
                                dArr[i5] = dArr[i3 + i2];
                                dArr[i5 + 1] = -dArr[i3 + i2 + 1];
                            }
                        }
                    });
                    i11 = i14 + 1;
                    futureArr = futureArr2;
                    z3 = z4;
                }
                boolean z5 = z3;
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr);
                } catch (InterruptedException e) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
                } catch (ExecutionException e2) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
                }
            }
            int i17 = i3 + 1;
            dArr2[this.n + i3] = -dArr2[i17];
            dArr2[i17] = 0.0d;
        } else if (i5 == 2) {
            rfftf(dArr, i);
            if (z2) {
                int i18 = this.n;
                CommonUtils.scale(i18, 1.0d / ((double) i18), dArr, i, false);
            }
            int i19 = this.n;
            if (i19 % 2 == 0) {
                i2 = i19 / 2;
            } else {
                i2 = (i19 + 1) / 2;
            }
            for (int i20 = 1; i20 < i2; i20++) {
                int i21 = i20 * 2;
                int i22 = i3 + i21;
                int i23 = (i3 + i4) - i21;
                double d = -dArr2[i22];
                dArr2[i22] = d;
                dArr2[i23 + 1] = -d;
                dArr2[i23] = dArr2[i22 - 1];
            }
            int i24 = 1;
            while (true) {
                int i25 = this.n;
                if (i24 < i25) {
                    int i26 = (i25 + i3) - i24;
                    int i27 = i26 + 1;
                    double d2 = dArr2[i27];
                    dArr2[i27] = dArr2[i26];
                    dArr2[i26] = d2;
                    i24++;
                } else {
                    dArr2[i3 + 1] = 0.0d;
                    return;
                }
            }
        } else if (i5 == 3) {
            bluestein_real_full(dArr2, i3, 1);
            if (z2) {
                int i28 = this.n;
                CommonUtils.scale(i28, 1.0d / ((double) i28), dArr, i, true);
            }
        }
    }

    public void realInverseFull(DoubleLargeArray doubleLargeArray, long j, boolean z) {
        long j2;
        DoubleLargeArray doubleLargeArray2 = doubleLargeArray;
        long j3 = j;
        boolean z2 = z;
        Class<DoubleFFT_1D> cls = DoubleFFT_1D.class;
        if (this.useLargeArrays) {
            long j4 = this.nl * 2;
            int i = AnonymousClass29.$SwitchMap$org$jtransforms$fft$DoubleFFT_1D$Plans[this.plan.ordinal()];
            if (i == 1) {
                realInverse2(doubleLargeArray, j, z);
                int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
                if (numberOfThreads <= 1 || this.nl / 2 <= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
                    for (long j5 = 0; j5 < this.nl / 2; j5++) {
                        long j6 = j5 * 2;
                        long j7 = ((j4 - j6) % j4) + j3;
                        long j8 = j6 + j3;
                        doubleLargeArray2.setDouble(j7, doubleLargeArray2.getDouble(j8));
                        doubleLargeArray2.setDouble(j7 + 1, -doubleLargeArray2.getDouble(j8 + 1));
                    }
                } else {
                    Future[] futureArr = new Future[numberOfThreads];
                    long j9 = (this.nl / 2) / ((long) numberOfThreads);
                    int i2 = 0;
                    while (i2 < numberOfThreads) {
                        final long j10 = ((long) i2) * j9;
                        final long j11 = i2 == numberOfThreads + -1 ? this.nl / 2 : j10 + j9;
                        int i3 = i2;
                        final long j12 = j;
                        final long j13 = j4;
                        Future[] futureArr2 = futureArr;
                        final DoubleLargeArray doubleLargeArray3 = doubleLargeArray;
                        futureArr2[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                for (long j = j10; j < j11; j++) {
                                    long j2 = 2 * j;
                                    long j3 = j12;
                                    long j4 = j13;
                                    long j5 = ((j4 - j2) % j4) + j3;
                                    DoubleLargeArray doubleLargeArray = doubleLargeArray3;
                                    doubleLargeArray.setDouble(j5, doubleLargeArray.getDouble(j3 + j2));
                                    DoubleLargeArray doubleLargeArray2 = doubleLargeArray3;
                                    doubleLargeArray2.setDouble(j5 + 1, -doubleLargeArray2.getDouble(j12 + j2 + 1));
                                }
                            }
                        });
                        i2 = i3 + 1;
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
                long j14 = j3 + 1;
                doubleLargeArray2.setDouble(this.nl + j3, -doubleLargeArray2.getDouble(j14));
                doubleLargeArray2.setDouble(j14, 0.0d);
            } else if (i == 2) {
                rfftf(doubleLargeArray, j);
                if (z2) {
                    long j15 = this.nl;
                    CommonUtils.scale(j15, 1.0d / ((double) j15), doubleLargeArray, j, false);
                }
                long j16 = this.nl;
                if (j16 % 2 == 0) {
                    j2 = j16 / 2;
                } else {
                    j2 = (j16 + 1) / 2;
                }
                for (long j17 = 1; j17 < j2; j17++) {
                    long j18 = j17 * 2;
                    long j19 = j3 + j18;
                    long j20 = (j3 + j4) - j18;
                    doubleLargeArray2.setDouble(j19, -doubleLargeArray2.getDouble(j19));
                    doubleLargeArray2.setDouble(j20 + 1, -doubleLargeArray2.getDouble(j19));
                    doubleLargeArray2.setDouble(j20, doubleLargeArray2.getDouble(j19 - 1));
                }
                long j21 = 1;
                while (true) {
                    long j22 = this.nl;
                    if (j21 < j22) {
                        long j23 = (j22 + j3) - j21;
                        long j24 = j23 + 1;
                        double d = doubleLargeArray2.getDouble(j24);
                        doubleLargeArray2.setDouble(j24, doubleLargeArray2.getDouble(j23));
                        doubleLargeArray2.setDouble(j23, d);
                        j21++;
                    } else {
                        doubleLargeArray2.setDouble(j3 + 1, 0.0d);
                        return;
                    }
                }
            } else if (i == 3) {
                bluestein_real_full(doubleLargeArray, j, 1);
                if (z2) {
                    long j25 = this.nl;
                    CommonUtils.scale(j25, 1.0d / ((double) j25), doubleLargeArray, j, true);
                }
            }
        } else if (doubleLargeArray.isLarge() || doubleLargeArray.isConstant() || j3 >= 2147483647L) {
            throw new IllegalArgumentException("The data array is too big.");
        } else {
            realInverseFull(doubleLargeArray.getData(), (int) j3, z2);
        }
    }

    /* access modifiers changed from: protected */
    public void realInverse2(double[] dArr, int i, boolean z) {
        double[] dArr2 = dArr;
        int i2 = i;
        boolean z2 = z;
        if (this.useLargeArrays) {
            realInverse2(new DoubleLargeArray(dArr2), (long) i2, z2);
        } else if (this.n != 1) {
            int i3 = AnonymousClass29.$SwitchMap$org$jtransforms$fft$DoubleFFT_1D$Plans[this.plan.ordinal()];
            if (i3 == 1) {
                int i4 = this.n;
                if (i4 > 4) {
                    double[] dArr3 = dArr;
                    int i5 = i;
                    CommonUtils.cftfsub(i4, dArr3, i5, this.ip, this.nw, this.w);
                    CommonUtils.rftbsub(this.n, dArr3, i5, this.nc, this.w, this.nw);
                } else if (i4 == 4) {
                    CommonUtils.cftbsub(i4, dArr, i, this.ip, this.nw, this.w);
                }
                double d = dArr2[i2];
                int i6 = i2 + 1;
                double d2 = dArr2[i6];
                dArr2[i2] = d + d2;
                dArr2[i6] = d - d2;
                if (z2) {
                    int i7 = this.n;
                    CommonUtils.scale(i7, 1.0d / ((double) i7), dArr, i, false);
                }
            } else if (i3 == 2) {
                rfftf(dArr, i);
                for (int i8 = this.n - 1; i8 >= 2; i8--) {
                    int i9 = i2 + i8;
                    double d3 = dArr2[i9];
                    int i10 = i9 - 1;
                    dArr2[i9] = dArr2[i10];
                    dArr2[i10] = d3;
                }
                if (z2) {
                    int i11 = this.n;
                    CommonUtils.scale(i11, 1.0d / ((double) i11), dArr, i, false);
                }
                int i12 = this.n;
                if (i12 % 2 == 0) {
                    int i13 = i12 / 2;
                    for (int i14 = 1; i14 < i13; i14++) {
                        int i15 = (i14 * 2) + i2 + 1;
                        dArr2[i15] = -dArr2[i15];
                    }
                    return;
                }
                int i16 = (i12 - 1) / 2;
                for (int i17 = 0; i17 < i16; i17++) {
                    int i18 = (i17 * 2) + i2 + 1;
                    dArr2[i18] = -dArr2[i18];
                }
            } else if (i3 == 3) {
                bluestein_real_inverse2(dArr, i);
                if (z2) {
                    int i19 = this.n;
                    CommonUtils.scale(i19, 1.0d / ((double) i19), dArr, i, false);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void realInverse2(DoubleLargeArray doubleLargeArray, long j, boolean z) {
        DoubleLargeArray doubleLargeArray2 = doubleLargeArray;
        long j2 = j;
        boolean z2 = z;
        if (!this.useLargeArrays) {
            if (doubleLargeArray.isLarge() || doubleLargeArray.isConstant() || j2 >= 2147483647L) {
                throw new IllegalArgumentException("The data array is too big.");
            }
            realInverse2(doubleLargeArray.getData(), (int) j2, z2);
        } else if (this.nl != 1) {
            int i = AnonymousClass29.$SwitchMap$org$jtransforms$fft$DoubleFFT_1D$Plans[this.plan.ordinal()];
            if (i == 1) {
                long j3 = this.nl;
                if (j3 > 4) {
                    DoubleLargeArray doubleLargeArray3 = doubleLargeArray;
                    long j4 = j;
                    CommonUtils.cftfsub(j3, doubleLargeArray3, j4, this.ipl, this.nwl, this.wl);
                    CommonUtils.rftbsub(this.nl, doubleLargeArray3, j4, this.ncl, this.wl, this.nwl);
                } else if (j3 == 4) {
                    CommonUtils.cftbsub(j3, doubleLargeArray, j, this.ipl, this.nwl, this.wl);
                }
                long j5 = j2 + 1;
                double d = doubleLargeArray.getDouble(j) - doubleLargeArray2.getDouble(j5);
                doubleLargeArray2.setDouble(j2, doubleLargeArray.getDouble(j) + doubleLargeArray2.getDouble(j5));
                doubleLargeArray2.setDouble(j5, d);
                if (z2) {
                    long j6 = this.nl;
                    CommonUtils.scale(j6, 1.0d / ((double) j6), doubleLargeArray, j, false);
                }
            } else if (i == 2) {
                rfftf(doubleLargeArray, j);
                long j7 = this.nl;
                while (true) {
                    j7--;
                    if (j7 < 2) {
                        break;
                    }
                    long j8 = j2 + j7;
                    double d2 = doubleLargeArray2.getDouble(j8);
                    long j9 = j8 - 1;
                    doubleLargeArray2.setDouble(j8, doubleLargeArray2.getDouble(j9));
                    doubleLargeArray2.setDouble(j9, d2);
                }
                if (z2) {
                    long j10 = this.nl;
                    CommonUtils.scale(j10, 1.0d / ((double) j10), doubleLargeArray, j, false);
                }
                long j11 = this.nl;
                if (j11 % 2 == 0) {
                    long j12 = j11 / 2;
                    for (long j13 = 1; j13 < j12; j13++) {
                        long j14 = (j13 * 2) + j2 + 1;
                        doubleLargeArray2.setDouble(j14, -doubleLargeArray2.getDouble(j14));
                    }
                    return;
                }
                long j15 = (j11 - 1) / 2;
                for (long j16 = 0; j16 < j15; j16++) {
                    long j17 = (j16 * 2) + j2 + 1;
                    doubleLargeArray2.setDouble(j17, -doubleLargeArray2.getDouble(j17));
                }
            } else if (i == 3) {
                bluestein_real_inverse2(doubleLargeArray, j);
                if (z2) {
                    long j18 = this.nl;
                    CommonUtils.scale(j18, 1.0d / ((double) j18), doubleLargeArray, j, false);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void cffti(int i, int i2) {
        int i3;
        int i4 = i;
        int i5 = 1;
        if (i4 != 1) {
            int i6 = i4 * 2;
            int i7 = i4 * 4;
            int i8 = i4;
            int i9 = 0;
            int i10 = 0;
            int i11 = 0;
            loop0:
            while (true) {
                int i12 = i9 + 1;
                i3 = 2;
                i10 = i12 <= 4 ? factors[i9] : i10 + 2;
                while (true) {
                    int i13 = i8 / i10;
                    if (i8 - (i10 * i13) != 0) {
                        break;
                    }
                    i11++;
                    this.wtable[i2 + i11 + 1 + i7] = (double) i10;
                    if (i10 == 2 && i11 != 1) {
                        for (int i14 = 2; i14 <= i11; i14++) {
                            double[] dArr = this.wtable;
                            int i15 = i2 + (i11 - i14) + 2 + i7;
                            dArr[i15 + 1] = dArr[i15];
                        }
                        this.wtable[i2 + 2 + i7] = 2.0d;
                    }
                    if (i13 == 1) {
                        break loop0;
                    }
                    i4 = i;
                    i8 = i13;
                }
                i9 = i12;
            }
            double[] dArr2 = this.wtable;
            double d = (double) i4;
            dArr2[i2 + i7] = d;
            dArr2[i2 + 1 + i7] = (double) i11;
            double d2 = 6.283185307179586d / d;
            int i16 = 1;
            int i17 = 1;
            int i18 = 1;
            while (i16 <= i11) {
                double d3 = d2;
                int i19 = (int) this.wtable[i2 + i16 + i5 + i7];
                int i20 = i17 * i19;
                int i21 = i4 / i20;
                int i22 = i21 + i21 + i3;
                int i23 = i19 - 1;
                int i24 = i5;
                int i25 = 0;
                while (i24 <= i23) {
                    double[] dArr3 = this.wtable;
                    int i26 = i2 + i18;
                    dArr3[(i26 - 1) + i6] = 1.0d;
                    double d4 = 0.0d;
                    dArr3[i26 + i6] = 0.0d;
                    int i27 = i25 + i17;
                    double d5 = ((double) i27) * d3;
                    int i28 = i18;
                    int i29 = 4;
                    while (i29 <= i22) {
                        i28 += 2;
                        d4 += 1.0d;
                        double d6 = d4 * d5;
                        int i30 = i2 + i28 + i6;
                        this.wtable[i30 - 1] = FastMath.cos(d6);
                        this.wtable[i30] = FastMath.sin(d6);
                        i29 += 2;
                        i27 = i27;
                    }
                    int i31 = i27;
                    if (i19 > 5) {
                        double[] dArr4 = this.wtable;
                        int i32 = i2 + i18 + i6;
                        int i33 = i2 + i28 + i6;
                        dArr4[i32 - 1] = dArr4[i33 - 1];
                        dArr4[i32] = dArr4[i33];
                    }
                    i24++;
                    int i34 = i;
                    i18 = i28;
                    i25 = i31;
                }
                i16++;
                i4 = i;
                i17 = i20;
                d2 = d3;
                i5 = 1;
                i3 = 2;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void cffti() {
        int i;
        int i2;
        int i3 = this.n;
        if (i3 != 1) {
            int i4 = i3 * 2;
            int i5 = i3 * 4;
            int i6 = 0;
            int i7 = 0;
            int i8 = 0;
            loop0:
            while (true) {
                int i9 = i6 + 1;
                i = 2;
                i7 = i9 <= 4 ? factors[i6] : i7 + 2;
                while (true) {
                    int i10 = i3 / i7;
                    if (i3 - (i7 * i10) != 0) {
                        break;
                    }
                    i2 = i8 + 1;
                    this.wtable[i8 + 2 + i5] = (double) i7;
                    if (i7 == 2 && i2 != 1) {
                        for (int i11 = 2; i11 <= i2; i11++) {
                            int i12 = (i2 - i11) + 2 + i5;
                            double[] dArr = this.wtable;
                            dArr[i12 + 1] = dArr[i12];
                        }
                        this.wtable[i5 + 2] = 2.0d;
                    }
                    if (i10 == 1) {
                        break loop0;
                    }
                    int i13 = i2;
                    i3 = i10;
                    i8 = i13;
                }
                i6 = i9;
            }
            double[] dArr2 = this.wtable;
            int i14 = this.n;
            dArr2[i5] = (double) i14;
            dArr2[i5 + 1] = (double) i2;
            double d = 6.283185307179586d / ((double) i14);
            int i15 = 1;
            int i16 = 1;
            int i17 = 1;
            while (i15 <= i2) {
                i15++;
                int i18 = (int) this.wtable[i15 + i5];
                int i19 = i16 * i18;
                int i20 = this.n / i19;
                int i21 = i20 + i20 + i;
                int i22 = i18 - 1;
                int i23 = 1;
                int i24 = 0;
                while (i23 <= i22) {
                    double[] dArr3 = this.wtable;
                    dArr3[(i17 - 1) + i4] = 1.0d;
                    int i25 = i17 + i4;
                    double d2 = 0.0d;
                    dArr3[i25] = 0.0d;
                    int i26 = i24 + i16;
                    int i27 = i17;
                    double d3 = ((double) i26) * d;
                    int i28 = i2;
                    int i29 = 4;
                    while (i29 <= i21) {
                        i27 += 2;
                        d2 += 1.0d;
                        double d4 = d2 * d3;
                        int i30 = i27 + i4;
                        this.wtable[i30 - 1] = FastMath.cos(d4);
                        this.wtable[i30] = FastMath.sin(d4);
                        i29 += 2;
                        i22 = i22;
                    }
                    int i31 = i22;
                    if (i18 > 5) {
                        int i32 = i27 + i4;
                        double[] dArr4 = this.wtable;
                        dArr4[i25 - 1] = dArr4[i32 - 1];
                        dArr4[i25] = dArr4[i32];
                    }
                    i23++;
                    i24 = i26;
                    i17 = i27;
                    i2 = i28;
                    i22 = i31;
                    i = 2;
                }
                int i33 = i17;
                i16 = i19;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void cfftil() {
        long j;
        long j2;
        long j3;
        long j4;
        long j5;
        long j6;
        long j7;
        long j8 = this.nl;
        long j9 = 1;
        if (j8 != 1) {
            long j10 = 2;
            long j11 = j8 * 2;
            long j12 = 4;
            long j13 = j8 * 4;
            long j14 = 0;
            long j15 = 0;
            long j16 = 0;
            loop0:
            while (true) {
                long j17 = j14 + j9;
                long j18 = j17 <= j12 ? (long) factors[(int) j14] : j15 + j10;
                while (true) {
                    long j19 = j8 / j18;
                    j = 0;
                    if (j8 - (j18 * j19) != 0) {
                        break;
                    }
                    j2 = j16 + j9;
                    long j20 = j16 + j10 + j13;
                    j3 = j11;
                    this.wtablel.setDouble(j20, (double) j18);
                    j4 = 1;
                    if (j18 == j10 && j2 != 1) {
                        long j21 = j10;
                        while (j21 <= j2) {
                            long j22 = (j2 - j21) + j10 + j13;
                            DoubleLargeArray doubleLargeArray = this.wtablel;
                            doubleLargeArray.setDouble(j22 + 1, doubleLargeArray.getDouble(j22));
                            j21++;
                            j10 = 2;
                        }
                        this.wtablel.setDouble(j13 + 2, 2.0d);
                    }
                    if (j19 == 1) {
                        break loop0;
                    }
                    long j23 = j2;
                    j8 = j19;
                    j11 = j3;
                    j9 = 1;
                    j10 = 2;
                    j16 = j23;
                    j12 = 4;
                }
                j15 = j18;
                j14 = j17;
            }
            this.wtablel.setDouble(j13, (double) this.nl);
            this.wtablel.setDouble(j13 + 1, (double) j2);
            double d = 6.283185307179586d / ((double) this.nl);
            long j24 = 1;
            long j25 = 1;
            long j26 = 1;
            while (j24 <= j2) {
                long j27 = j24 + j4;
                long j28 = j2;
                long j29 = (long) this.wtablel.getDouble(j27 + j13);
                long j30 = j26 * j29;
                long j31 = j27;
                long j32 = this.nl / j30;
                long j33 = j32 + j32 + 2;
                long j34 = j29 - j4;
                long j35 = j;
                long j36 = j4;
                while (j36 <= j34) {
                    long j37 = j13;
                    long j38 = j30;
                    this.wtablel.setDouble((j25 - j4) + j3, 1.0d);
                    long j39 = j25 + j3;
                    long j40 = j25;
                    this.wtablel.setDouble(j39, 0.0d);
                    long j41 = j35 + j26;
                    double d2 = ((double) j41) * d;
                    double d3 = 0.0d;
                    long j42 = 4;
                    while (j42 <= j33) {
                        j40 += 2;
                        d3 += 1.0d;
                        double d4 = d3 * d2;
                        long j43 = j33;
                        long j44 = j40 + j3;
                        this.wtablel.setDouble(j44 - 1, FastMath.cos(d4));
                        this.wtablel.setDouble(j44, FastMath.sin(d4));
                        j42 += 2;
                        j33 = j43;
                        d = d;
                        d2 = d2;
                    }
                    long j45 = j33;
                    double d5 = d;
                    if (j29 > 5) {
                        long j46 = j40 + j3;
                        DoubleLargeArray doubleLargeArray2 = this.wtablel;
                        j6 = j29;
                        j7 = 1;
                        j5 = j26;
                        doubleLargeArray2.setDouble(j39 - 1, doubleLargeArray2.getDouble(j46 - 1));
                        DoubleLargeArray doubleLargeArray3 = this.wtablel;
                        doubleLargeArray3.setDouble(j39, doubleLargeArray3.getDouble(j46));
                    } else {
                        j6 = j29;
                        j5 = j26;
                        j7 = 1;
                    }
                    j36 += j7;
                    j35 = j41;
                    j29 = j6;
                    j26 = j5;
                    j30 = j38;
                    j13 = j37;
                    j25 = j40;
                    j = 0;
                    j4 = j7;
                    j33 = j45;
                    d = d5;
                }
                long j47 = j25;
                j24 = j31;
                j2 = j28;
                j26 = j30;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void rffti() {
        int i;
        int i2 = this.n;
        int i3 = 1;
        if (i2 != 1) {
            int i4 = i2 * 2;
            int i5 = 0;
            int i6 = 0;
            int i7 = 0;
            loop0:
            while (true) {
                int i8 = i5 + 1;
                i6 = i8 <= 4 ? factors[i5] : i6 + 2;
                while (true) {
                    int i9 = i2 / i6;
                    if (i2 - (i6 * i9) != 0) {
                        break;
                    }
                    i = i7 + 1;
                    this.wtable_r[i7 + 2 + i4] = (double) i6;
                    if (i6 == 2 && i != 1) {
                        for (int i10 = 2; i10 <= i; i10++) {
                            int i11 = (i - i10) + 2 + i4;
                            double[] dArr = this.wtable_r;
                            dArr[i11 + 1] = dArr[i11];
                        }
                        this.wtable_r[i4 + 2] = 2.0d;
                    }
                    if (i9 == 1) {
                        break loop0;
                    }
                    i7 = i;
                    i2 = i9;
                }
                i5 = i8;
            }
            double[] dArr2 = this.wtable_r;
            int i12 = this.n;
            dArr2[i4] = (double) i12;
            dArr2[i4 + 1] = (double) i;
            double d = 6.283185307179586d / ((double) i12);
            if (i7 != 0) {
                int i13 = 1;
                int i14 = 1;
                int i15 = 0;
                while (i13 <= i7) {
                    i13++;
                    int i16 = (int) this.wtable_r[i13 + i4];
                    int i17 = i14 * i16;
                    int i18 = this.n / i17;
                    int i19 = i16 - i3;
                    int i20 = i3;
                    int i21 = 0;
                    while (i20 <= i19) {
                        i21 += i14;
                        int i22 = i14;
                        double d2 = ((double) i21) * d;
                        double d3 = 0.0d;
                        int i23 = i15;
                        int i24 = 3;
                        while (i24 <= i18) {
                            i23 += 2;
                            d3 += 1.0d;
                            double d4 = d3 * d2;
                            int i25 = i23 + this.n;
                            this.wtable_r[i25 - 2] = FastMath.cos(d4);
                            this.wtable_r[i25 - 1] = FastMath.sin(d4);
                            i24 += 2;
                            i13 = i13;
                        }
                        int i26 = i13;
                        i15 += i18;
                        i20++;
                        i14 = i22;
                        i3 = 1;
                    }
                    int i27 = i13;
                    i14 = i17;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void rfftil() {
        long j;
        long j2;
        long j3;
        long j4 = this.nl;
        long j5 = 1;
        if (j4 != 1) {
            long j6 = 2;
            long j7 = j4 * 2;
            long j8 = 0;
            long j9 = 0;
            long j10 = 0;
            long j11 = 0;
            loop0:
            while (true) {
                long j12 = j9 + j5;
                j10 = j12 <= 4 ? (long) factors[(int) j9] : j10 + j6;
                while (true) {
                    long j13 = j4 / j10;
                    if (j4 - (j10 * j13) != j8) {
                        break;
                    }
                    j = j11 + j5;
                    long j14 = j13;
                    this.wtable_rl.setDouble(j11 + j6 + j7, (double) j10);
                    if (j10 == j6) {
                        j3 = 1;
                        if (j != 1) {
                            long j15 = j6;
                            while (j15 <= j) {
                                long j16 = (j - j15) + j6 + j7;
                                DoubleLargeArray doubleLargeArray = this.wtable_rl;
                                doubleLargeArray.setDouble(j16 + 1, doubleLargeArray.getDouble(j16));
                                j15++;
                                j10 = j10;
                                j6 = 2;
                            }
                            j2 = j10;
                            this.wtable_rl.setDouble(j7 + 2, 2.0d);
                        } else {
                            j2 = j10;
                        }
                    } else {
                        j2 = j10;
                        j3 = 1;
                    }
                    if (j14 == j3) {
                        break loop0;
                    }
                    j11 = j;
                    j5 = j3;
                    j4 = j14;
                    j10 = j2;
                    j6 = 2;
                    j8 = 0;
                }
                j9 = j12;
            }
            this.wtable_rl.setDouble(j7, (double) this.nl);
            this.wtable_rl.setDouble(j7 + j3, (double) j);
            double d = 6.283185307179586d / ((double) this.nl);
            if (j11 != 0) {
                long j17 = 0;
                long j18 = 1;
                long j19 = 1;
                while (j19 <= j11) {
                    j19++;
                    long j20 = (long) this.wtable_rl.getDouble(j19 + j7);
                    long j21 = j18 * j20;
                    long j22 = j7;
                    long j23 = this.nl / j21;
                    long j24 = j20 - 1;
                    long j25 = j17;
                    long j26 = 1;
                    long j27 = 0;
                    while (j26 <= j24) {
                        long j28 = j24;
                        long j29 = j27 + j18;
                        long j30 = j18;
                        double d2 = ((double) j29) * d;
                        double d3 = 0.0d;
                        long j31 = 3;
                        long j32 = j25;
                        while (j31 <= j23) {
                            j32 += 2;
                            d3 += 1.0d;
                            double d4 = d3 * d2;
                            double d5 = d2;
                            long j33 = j32 + this.nl;
                            this.wtable_rl.setDouble(j33 - 2, FastMath.cos(d4));
                            this.wtable_rl.setDouble(j33 - 1, FastMath.sin(d4));
                            j31 += 2;
                            d2 = d5;
                            d = d;
                            j29 = j29;
                            j19 = j19;
                        }
                        long j34 = j29;
                        long j35 = j19;
                        j25 += j23;
                        j26++;
                        j18 = j30;
                        j24 = j28;
                        d = d;
                        j27 = j34;
                    }
                    long j36 = j19;
                    j18 = j21;
                    j17 = j25;
                    j7 = j22;
                }
            }
        }
    }

    private void bluesteini() {
        double d = 3.141592653589793d / ((double) this.n);
        double[] dArr = this.bk1;
        dArr[0] = 1.0d;
        dArr[1] = 0.0d;
        int i = 0;
        int i2 = 1;
        while (true) {
            int i3 = this.n;
            if (i2 >= i3) {
                break;
            }
            int i4 = i2 * 2;
            i += i4 - 1;
            if (i >= i3 * 2) {
                i -= i3 * 2;
            }
            double d2 = ((double) i) * d;
            this.bk1[i4] = FastMath.cos(d2);
            this.bk1[i4 + 1] = FastMath.sin(d2);
            i2++;
        }
        double d3 = 1.0d / ((double) this.nBluestein);
        double[] dArr2 = this.bk2;
        double[] dArr3 = this.bk1;
        dArr2[0] = dArr3[0] * d3;
        dArr2[1] = dArr3[1] * d3;
        for (int i5 = 2; i5 < this.n * 2; i5 += 2) {
            double[] dArr4 = this.bk2;
            double[] dArr5 = this.bk1;
            dArr4[i5] = dArr5[i5] * d3;
            int i6 = i5 + 1;
            dArr4[i6] = dArr5[i6] * d3;
            int i7 = this.nBluestein;
            dArr4[(i7 * 2) - i5] = dArr4[i5];
            dArr4[((i7 * 2) - i5) + 1] = dArr4[i6];
        }
        CommonUtils.cftbsub(this.nBluestein * 2, this.bk2, 0, this.ip, this.nw, this.w);
    }

    private void bluesteinil() {
        double d = 3.141592653589793d / ((double) this.nl);
        this.bk1l.setDouble(0, 1.0d);
        this.bk1l.setDouble(1, 0.0d);
        int i = 1;
        long j = 0;
        while (true) {
            long j2 = this.nl;
            if (((long) i) >= j2) {
                break;
            }
            int i2 = i * 2;
            j += (long) (i2 - 1);
            if (j >= j2 * 2) {
                j -= j2 * 2;
            }
            double d2 = ((double) j) * d;
            this.bk1l.setDouble((long) i2, FastMath.cos(d2));
            this.bk1l.setDouble((long) (i2 + 1), FastMath.sin(d2));
            i++;
        }
        double d3 = 1.0d / ((double) this.nBluesteinl);
        this.bk2l.setDouble(0, this.bk1l.getDouble(0) * d3);
        this.bk2l.setDouble(1, this.bk1l.getDouble(1) * d3);
        int i3 = 2;
        while (true) {
            long j3 = (long) i3;
            if (j3 < this.nl * 2) {
                this.bk2l.setDouble(j3, this.bk1l.getDouble(j3) * d3);
                long j4 = (long) (i3 + 1);
                this.bk2l.setDouble(j4, this.bk1l.getDouble(j4) * d3);
                DoubleLargeArray doubleLargeArray = this.bk2l;
                doubleLargeArray.setDouble((this.nBluesteinl * 2) - j3, doubleLargeArray.getDouble(j3));
                DoubleLargeArray doubleLargeArray2 = this.bk2l;
                doubleLargeArray2.setDouble(((this.nBluesteinl * 2) - j3) + 1, doubleLargeArray2.getDouble(j4));
                i3 += 2;
            } else {
                CommonUtils.cftbsub(this.nBluesteinl * 2, this.bk2l, 0, this.ipl, this.nwl, this.wl);
                return;
            }
        }
    }

    private void bluestein_complex(double[] dArr, int i, int i2) {
        Class<DoubleFFT_1D> cls = DoubleFFT_1D.class;
        double[] dArr2 = new double[(this.nBluestein * 2)];
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = 0;
        if (numberOfThreads <= 1 || ((long) this.n) < CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
            if (i2 > 0) {
                for (int i4 = 0; i4 < this.n; i4++) {
                    int i5 = i4 * 2;
                    int i6 = i5 + 1;
                    int i7 = i + i5;
                    int i8 = i + i6;
                    double d = dArr[i7];
                    double[] dArr3 = this.bk1;
                    dArr2[i5] = (d * dArr3[i5]) - (dArr[i8] * dArr3[i6]);
                    dArr2[i6] = (dArr[i7] * dArr3[i6]) + (dArr[i8] * dArr3[i5]);
                }
            } else {
                for (int i9 = 0; i9 < this.n; i9++) {
                    int i10 = i9 * 2;
                    int i11 = i10 + 1;
                    int i12 = i + i10;
                    int i13 = i + i11;
                    double d2 = dArr[i12];
                    double[] dArr4 = this.bk1;
                    dArr2[i10] = (d2 * dArr4[i10]) + (dArr[i13] * dArr4[i11]);
                    dArr2[i11] = ((-dArr[i12]) * dArr4[i11]) + (dArr[i13] * dArr4[i10]);
                }
            }
            CommonUtils.cftbsub(this.nBluestein * 2, dArr2, 0, this.ip, this.nw, this.w);
            if (i2 > 0) {
                for (int i14 = 0; i14 < this.nBluestein; i14++) {
                    int i15 = i14 * 2;
                    int i16 = i15 + 1;
                    double d3 = dArr2[i15];
                    double[] dArr5 = this.bk2;
                    double d4 = dArr5[i16];
                    double d5 = dArr2[i16];
                    double d6 = dArr5[i15];
                    dArr2[i15] = (d3 * d6) + (d5 * d4);
                    dArr2[i16] = ((-d3) * d4) + (d5 * d6);
                }
            } else {
                for (int i17 = 0; i17 < this.nBluestein; i17++) {
                    int i18 = i17 * 2;
                    int i19 = i18 + 1;
                    double d7 = dArr2[i18];
                    double[] dArr6 = this.bk2;
                    double d8 = dArr6[i19];
                    double d9 = dArr2[i19];
                    double d10 = dArr6[i18];
                    dArr2[i18] = (d7 * d10) - (d9 * d8);
                    dArr2[i19] = (d7 * d8) + (d9 * d10);
                }
            }
            CommonUtils.cftfsub(this.nBluestein * 2, dArr2, 0, this.ip, this.nw, this.w);
            if (i2 > 0) {
                while (i3 < this.n) {
                    int i20 = i3 * 2;
                    int i21 = i20 + 1;
                    double[] dArr7 = this.bk1;
                    dArr[i + i20] = (dArr7[i20] * dArr2[i20]) - (dArr7[i21] * dArr2[i21]);
                    dArr[i + i21] = (dArr7[i21] * dArr2[i20]) + (dArr7[i20] * dArr2[i21]);
                    i3++;
                }
                return;
            }
            while (i3 < this.n) {
                int i22 = i3 * 2;
                int i23 = i22 + 1;
                double[] dArr8 = this.bk1;
                dArr[i + i22] = (dArr8[i22] * dArr2[i22]) + (dArr8[i23] * dArr2[i23]);
                dArr[i + i23] = ((-dArr8[i23]) * dArr2[i22]) + (dArr8[i22] * dArr2[i23]);
                i3++;
            }
            return;
        }
        int i24 = (numberOfThreads < 4 || ((long) this.n) < CommonUtils.getThreadsBeginN_1D_FFT_4Threads()) ? 2 : 4;
        Future[] futureArr = new Future[i24];
        int i25 = this.n / i24;
        int i26 = 0;
        while (i26 < i24) {
            final int i27 = i26 * i25;
            final int i28 = i26 == i24 + -1 ? this.n : i27 + i25;
            final int i29 = i2;
            final int i30 = i;
            final double[] dArr9 = dArr2;
            int i31 = i26;
            final double[] dArr10 = dArr;
            futureArr[i31] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    if (i29 > 0) {
                        for (int i = i27; i < i28; i++) {
                            int i2 = i * 2;
                            int i3 = i2 + 1;
                            int i4 = i30;
                            int i5 = i4 + i2;
                            int i6 = i4 + i3;
                            dArr9[i2] = (dArr10[i5] * DoubleFFT_1D.this.bk1[i2]) - (dArr10[i6] * DoubleFFT_1D.this.bk1[i3]);
                            dArr9[i3] = (dArr10[i5] * DoubleFFT_1D.this.bk1[i3]) + (dArr10[i6] * DoubleFFT_1D.this.bk1[i2]);
                        }
                        return;
                    }
                    for (int i7 = i27; i7 < i28; i7++) {
                        int i8 = i7 * 2;
                        int i9 = i8 + 1;
                        int i10 = i30;
                        int i11 = i10 + i8;
                        int i12 = i10 + i9;
                        dArr9[i8] = (dArr10[i11] * DoubleFFT_1D.this.bk1[i8]) + (dArr10[i12] * DoubleFFT_1D.this.bk1[i9]);
                        dArr9[i9] = ((-dArr10[i11]) * DoubleFFT_1D.this.bk1[i9]) + (dArr10[i12] * DoubleFFT_1D.this.bk1[i8]);
                    }
                }
            });
            i26 = i31 + 1;
        }
        String str = null;
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
        } catch (ExecutionException e2) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
        }
        CommonUtils.cftbsub(this.nBluestein * 2, dArr2, 0, this.ip, this.nw, this.w);
        int i32 = this.nBluestein / i24;
        int i33 = 0;
        while (i33 < i24) {
            final int i34 = i33 * i32;
            final int i35 = i33 == i24 + -1 ? this.nBluestein : i34 + i32;
            final int i36 = i2;
            final double[] dArr11 = dArr2;
            futureArr[i33] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    if (i36 > 0) {
                        for (int i = i34; i < i35; i++) {
                            int i2 = i * 2;
                            int i3 = i2 + 1;
                            double d = ((-dArr11[i2]) * DoubleFFT_1D.this.bk2[i3]) + (dArr11[i3] * DoubleFFT_1D.this.bk2[i2]);
                            double[] dArr = dArr11;
                            dArr[i2] = (dArr[i2] * DoubleFFT_1D.this.bk2[i2]) + (dArr11[i3] * DoubleFFT_1D.this.bk2[i3]);
                            dArr11[i3] = d;
                        }
                        return;
                    }
                    for (int i4 = i34; i4 < i35; i4++) {
                        int i5 = i4 * 2;
                        int i6 = i5 + 1;
                        double d2 = (dArr11[i5] * DoubleFFT_1D.this.bk2[i6]) + (dArr11[i6] * DoubleFFT_1D.this.bk2[i5]);
                        double[] dArr2 = dArr11;
                        dArr2[i5] = (dArr2[i5] * DoubleFFT_1D.this.bk2[i5]) - (dArr11[i6] * DoubleFFT_1D.this.bk2[i6]);
                        dArr11[i6] = d2;
                    }
                }
            });
            i33++;
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e3) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e3);
        } catch (ExecutionException e4) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e4);
        }
        CommonUtils.cftfsub(this.nBluestein * 2, dArr2, 0, this.ip, this.nw, this.w);
        int i37 = this.n / i24;
        while (i3 < i24) {
            final int i38 = i3 * i37;
            final int i39 = i3 == i24 + -1 ? this.n : i38 + i37;
            final int i40 = i2;
            final int i41 = i;
            final double[] dArr12 = dArr;
            int i42 = i24;
            String str2 = str;
            final double[] dArr13 = dArr2;
            futureArr[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    if (i40 > 0) {
                        for (int i = i38; i < i39; i++) {
                            int i2 = i * 2;
                            int i3 = i2 + 1;
                            int i4 = i41;
                            dArr12[i4 + i2] = (DoubleFFT_1D.this.bk1[i2] * dArr13[i2]) - (DoubleFFT_1D.this.bk1[i3] * dArr13[i3]);
                            dArr12[i4 + i3] = (DoubleFFT_1D.this.bk1[i3] * dArr13[i2]) + (DoubleFFT_1D.this.bk1[i2] * dArr13[i3]);
                        }
                        return;
                    }
                    for (int i5 = i38; i5 < i39; i5++) {
                        int i6 = i5 * 2;
                        int i7 = i6 + 1;
                        int i8 = i41;
                        dArr12[i8 + i6] = (DoubleFFT_1D.this.bk1[i6] * dArr13[i6]) + (DoubleFFT_1D.this.bk1[i7] * dArr13[i7]);
                        dArr12[i8 + i7] = ((-DoubleFFT_1D.this.bk1[i7]) * dArr13[i6]) + (DoubleFFT_1D.this.bk1[i6] * dArr13[i7]);
                    }
                }
            });
            i3++;
            str = str2;
            i24 = i42;
        }
        String str3 = str;
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e5) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e5);
        } catch (ExecutionException e6) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e6);
        }
    }

    private void bluestein_complex(DoubleLargeArray doubleLargeArray, long j, int i) {
        DoubleLargeArray doubleLargeArray2;
        DoubleLargeArray doubleLargeArray3 = doubleLargeArray;
        Class<DoubleFFT_1D> cls = DoubleFFT_1D.class;
        DoubleLargeArray doubleLargeArray4 = new DoubleLargeArray(this.nBluesteinl * 2);
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (numberOfThreads <= 1 || this.nl <= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
            long j2 = 1;
            if (i > 0) {
                long j3 = 0;
                while (j3 < this.nl) {
                    long j4 = j3 * 2;
                    long j5 = j4 + j2;
                    long j6 = j + j4;
                    long j7 = j + j5;
                    DoubleLargeArray doubleLargeArray5 = doubleLargeArray;
                    doubleLargeArray4.setDouble(j4, (doubleLargeArray5.getDouble(j6) * this.bk1l.getDouble(j4)) - (doubleLargeArray5.getDouble(j7) * this.bk1l.getDouble(j5)));
                    doubleLargeArray4.setDouble(j5, (doubleLargeArray5.getDouble(j6) * this.bk1l.getDouble(j5)) + (doubleLargeArray5.getDouble(j7) * this.bk1l.getDouble(j4)));
                    j3++;
                    j2 = 1;
                }
                doubleLargeArray2 = doubleLargeArray;
            } else {
                doubleLargeArray2 = doubleLargeArray;
                long j8 = 1;
                for (long j9 = 0; j9 < this.nl; j9++) {
                    long j10 = j9 * 2;
                    long j11 = j10 + j8;
                    long j12 = j + j10;
                    long j13 = j + j11;
                    doubleLargeArray4.setDouble(j10, (doubleLargeArray2.getDouble(j12) * this.bk1l.getDouble(j10)) + (doubleLargeArray2.getDouble(j13) * this.bk1l.getDouble(j11)));
                    doubleLargeArray4.setDouble(j11, ((-doubleLargeArray2.getDouble(j12)) * this.bk1l.getDouble(j11)) + (doubleLargeArray2.getDouble(j13) * this.bk1l.getDouble(j10)));
                    j8 = 1;
                }
            }
            CommonUtils.cftbsub(this.nBluesteinl * 2, doubleLargeArray4, 0, this.ipl, this.nwl, this.wl);
            if (i > 0) {
                for (long j14 = 0; j14 < this.nBluesteinl; j14++) {
                    long j15 = j14 * 2;
                    long j16 = j15 + 1;
                    double d = ((-doubleLargeArray4.getDouble(j15)) * this.bk2l.getDouble(j16)) + (doubleLargeArray4.getDouble(j16) * this.bk2l.getDouble(j15));
                    doubleLargeArray4.setDouble(j15, (doubleLargeArray4.getDouble(j15) * this.bk2l.getDouble(j15)) + (doubleLargeArray4.getDouble(j16) * this.bk2l.getDouble(j16)));
                    doubleLargeArray4.setDouble(j16, d);
                }
            } else {
                long j17 = 1;
                for (long j18 = 0; j18 < this.nBluesteinl; j18++) {
                    long j19 = j18 * 2;
                    long j20 = j19 + j17;
                    double d2 = (doubleLargeArray4.getDouble(j19) * this.bk2l.getDouble(j20)) + (doubleLargeArray4.getDouble(j20) * this.bk2l.getDouble(j19));
                    doubleLargeArray4.setDouble(j19, (doubleLargeArray4.getDouble(j19) * this.bk2l.getDouble(j19)) - (doubleLargeArray4.getDouble(j20) * this.bk2l.getDouble(j20)));
                    doubleLargeArray4.setDouble(j20, d2);
                    j17 = 1;
                }
            }
            CommonUtils.cftfsub(this.nBluesteinl * 2, doubleLargeArray4, 0, this.ipl, this.nwl, this.wl);
            if (i > 0) {
                for (long j21 = 0; j21 < this.nl; j21++) {
                    long j22 = j21 * 2;
                    long j23 = j22 + 1;
                    doubleLargeArray2.setDouble(j + j22, (this.bk1l.getDouble(j22) * doubleLargeArray4.getDouble(j22)) - (this.bk1l.getDouble(j23) * doubleLargeArray4.getDouble(j23)));
                    doubleLargeArray2.setDouble(j + j23, (this.bk1l.getDouble(j23) * doubleLargeArray4.getDouble(j22)) + (this.bk1l.getDouble(j22) * doubleLargeArray4.getDouble(j23)));
                }
                return;
            }
            long j24 = 1;
            for (long j25 = 0; j25 < this.nl; j25++) {
                long j26 = j25 * 2;
                long j27 = j26 + j24;
                doubleLargeArray2.setDouble(j + j26, (this.bk1l.getDouble(j26) * doubleLargeArray4.getDouble(j26)) + (this.bk1l.getDouble(j27) * doubleLargeArray4.getDouble(j27)));
                doubleLargeArray2.setDouble(j + j27, ((-this.bk1l.getDouble(j27)) * doubleLargeArray4.getDouble(j26)) + (this.bk1l.getDouble(j26) * doubleLargeArray4.getDouble(j27)));
                j24 = 1;
            }
            return;
        }
        int i2 = 4;
        if (numberOfThreads < 4 || this.nl <= CommonUtils.getThreadsBeginN_1D_FFT_4Threads()) {
            i2 = 2;
        }
        int i3 = i2;
        Future[] futureArr = new Future[i3];
        long j28 = (long) i3;
        long j29 = this.nl / j28;
        int i4 = 0;
        while (i4 < i3) {
            final long j30 = ((long) i4) * j29;
            final long j31 = i4 == i3 + -1 ? this.nl : j30 + j29;
            final int i5 = i;
            long j32 = j28;
            final long j33 = j;
            Future[] futureArr2 = futureArr;
            final DoubleLargeArray doubleLargeArray6 = doubleLargeArray4;
            int i6 = i3;
            final DoubleLargeArray doubleLargeArray7 = doubleLargeArray;
            futureArr2[i4] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    long j = 2;
                    long j2 = 1;
                    if (i5 > 0) {
                        long j3 = j30;
                        while (j3 < j31) {
                            long j4 = j3 * j;
                            long j5 = j4 + j2;
                            long j6 = j33;
                            long j7 = j6 + j4;
                            long j8 = j6 + j5;
                            doubleLargeArray6.setDouble(j4, (doubleLargeArray7.getDouble(j7) * DoubleFFT_1D.this.bk1l.getDouble(j4)) - (doubleLargeArray7.getDouble(j8) * DoubleFFT_1D.this.bk1l.getDouble(j5)));
                            doubleLargeArray6.setDouble(j5, (doubleLargeArray7.getDouble(j7) * DoubleFFT_1D.this.bk1l.getDouble(j5)) + (doubleLargeArray7.getDouble(j8) * DoubleFFT_1D.this.bk1l.getDouble(j4)));
                            j3++;
                            j2 = 1;
                            j = 2;
                        }
                        return;
                    }
                    long j9 = 1;
                    for (long j10 = j30; j10 < j31; j10++) {
                        long j11 = j10 * 2;
                        long j12 = j11 + j9;
                        long j13 = j33;
                        long j14 = j13 + j11;
                        long j15 = j13 + j12;
                        doubleLargeArray6.setDouble(j11, (doubleLargeArray7.getDouble(j14) * DoubleFFT_1D.this.bk1l.getDouble(j11)) + (doubleLargeArray7.getDouble(j15) * DoubleFFT_1D.this.bk1l.getDouble(j12)));
                        doubleLargeArray6.setDouble(j12, ((-doubleLargeArray7.getDouble(j14)) * DoubleFFT_1D.this.bk1l.getDouble(j12)) + (doubleLargeArray7.getDouble(j15) * DoubleFFT_1D.this.bk1l.getDouble(j11)));
                        j9 = 1;
                    }
                }
            });
            i4++;
            i3 = i6;
            j28 = j32;
            futureArr = futureArr2;
            DoubleLargeArray doubleLargeArray8 = doubleLargeArray;
        }
        long j34 = j28;
        Future[] futureArr3 = futureArr;
        int i7 = i3;
        String str = null;
        try {
            ConcurrencyUtils.waitForCompletion(futureArr3);
        } catch (InterruptedException e) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
        } catch (ExecutionException e2) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
        }
        CommonUtils.cftbsub(this.nBluesteinl * 2, doubleLargeArray4, 0, this.ipl, this.nwl, this.wl);
        long j35 = this.nBluesteinl / j34;
        int i8 = 0;
        while (i8 < i7) {
            final long j36 = ((long) i8) * j35;
            final long j37 = i8 == i7 + -1 ? this.nBluesteinl : j36 + j35;
            final int i9 = i;
            final DoubleLargeArray doubleLargeArray9 = doubleLargeArray4;
            futureArr3[i8] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    long j = 2;
                    long j2 = 1;
                    if (i9 > 0) {
                        long j3 = j36;
                        while (j3 < j37) {
                            long j4 = j3 * j;
                            long j5 = j4 + j2;
                            double d = ((-doubleLargeArray9.getDouble(j4)) * DoubleFFT_1D.this.bk2l.getDouble(j5)) + (doubleLargeArray9.getDouble(j5) * DoubleFFT_1D.this.bk2l.getDouble(j4));
                            DoubleLargeArray doubleLargeArray = doubleLargeArray9;
                            doubleLargeArray.setDouble(j4, (doubleLargeArray.getDouble(j4) * DoubleFFT_1D.this.bk2l.getDouble(j4)) + (doubleLargeArray9.getDouble(j5) * DoubleFFT_1D.this.bk2l.getDouble(j5)));
                            doubleLargeArray9.setDouble(j5, d);
                            j3++;
                            j2 = 1;
                            j = 2;
                        }
                        return;
                    }
                    long j6 = 1;
                    for (long j7 = j36; j7 < j37; j7++) {
                        long j8 = j7 * 2;
                        long j9 = j8 + j6;
                        double d2 = (doubleLargeArray9.getDouble(j8) * DoubleFFT_1D.this.bk2l.getDouble(j9)) + (doubleLargeArray9.getDouble(j9) * DoubleFFT_1D.this.bk2l.getDouble(j8));
                        DoubleLargeArray doubleLargeArray2 = doubleLargeArray9;
                        doubleLargeArray2.setDouble(j8, (doubleLargeArray2.getDouble(j8) * DoubleFFT_1D.this.bk2l.getDouble(j8)) - (doubleLargeArray9.getDouble(j9) * DoubleFFT_1D.this.bk2l.getDouble(j9)));
                        doubleLargeArray9.setDouble(j9, d2);
                        j6 = 1;
                    }
                }
            });
            i8++;
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr3);
        } catch (InterruptedException e3) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e3);
        } catch (ExecutionException e4) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e4);
        }
        CommonUtils.cftfsub(this.nBluesteinl * 2, doubleLargeArray4, 0, this.ipl, this.nwl, this.wl);
        long j38 = this.nl / j34;
        int i10 = 0;
        while (i10 < i7) {
            final long j39 = ((long) i10) * j38;
            final long j40 = i10 == i7 + -1 ? this.nl : j39 + j38;
            final int i11 = i;
            final long j41 = j;
            final DoubleLargeArray doubleLargeArray10 = doubleLargeArray;
            int i12 = i7;
            String str2 = str;
            final DoubleLargeArray doubleLargeArray11 = doubleLargeArray4;
            futureArr3[i10] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    long j = 2;
                    long j2 = 1;
                    if (i11 > 0) {
                        long j3 = j39;
                        while (j3 < j40) {
                            long j4 = j3 * j;
                            long j5 = j4 + j2;
                            long j6 = j41;
                            doubleLargeArray10.setDouble(j6 + j4, (DoubleFFT_1D.this.bk1l.getDouble(j4) * doubleLargeArray11.getDouble(j4)) - (DoubleFFT_1D.this.bk1l.getDouble(j5) * doubleLargeArray11.getDouble(j5)));
                            doubleLargeArray10.setDouble(j6 + j5, (DoubleFFT_1D.this.bk1l.getDouble(j5) * doubleLargeArray11.getDouble(j4)) + (DoubleFFT_1D.this.bk1l.getDouble(j4) * doubleLargeArray11.getDouble(j5)));
                            j3++;
                            j2 = 1;
                            j = 2;
                        }
                        return;
                    }
                    long j7 = 1;
                    for (long j8 = j39; j8 < j40; j8++) {
                        long j9 = j8 * 2;
                        long j10 = j9 + j7;
                        long j11 = j41;
                        doubleLargeArray10.setDouble(j11 + j9, (DoubleFFT_1D.this.bk1l.getDouble(j9) * doubleLargeArray11.getDouble(j9)) + (DoubleFFT_1D.this.bk1l.getDouble(j10) * doubleLargeArray11.getDouble(j10)));
                        doubleLargeArray10.setDouble(j11 + j10, ((-DoubleFFT_1D.this.bk1l.getDouble(j10)) * doubleLargeArray11.getDouble(j9)) + (DoubleFFT_1D.this.bk1l.getDouble(j9) * doubleLargeArray11.getDouble(j10)));
                        j7 = 1;
                    }
                }
            });
            i10++;
            str = str2;
            i7 = i12;
        }
        String str3 = str;
        try {
            ConcurrencyUtils.waitForCompletion(futureArr3);
        } catch (InterruptedException e5) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e5);
        } catch (ExecutionException e6) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e6);
        }
    }

    private void bluestein_real_full(double[] dArr, int i, int i2) {
        Class<DoubleFFT_1D> cls = DoubleFFT_1D.class;
        double[] dArr2 = new double[(this.nBluestein * 2)];
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = 0;
        if (numberOfThreads <= 1 || ((long) this.n) < CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
            if (i2 > 0) {
                for (int i4 = 0; i4 < this.n; i4++) {
                    int i5 = i4 * 2;
                    int i6 = i5 + 1;
                    int i7 = i + i4;
                    double d = dArr[i7];
                    double[] dArr3 = this.bk1;
                    dArr2[i5] = d * dArr3[i5];
                    dArr2[i6] = dArr[i7] * dArr3[i6];
                }
            } else {
                for (int i8 = 0; i8 < this.n; i8++) {
                    int i9 = i8 * 2;
                    int i10 = i9 + 1;
                    int i11 = i + i8;
                    double d2 = dArr[i11];
                    double[] dArr4 = this.bk1;
                    dArr2[i9] = d2 * dArr4[i9];
                    dArr2[i10] = (-dArr[i11]) * dArr4[i10];
                }
            }
            CommonUtils.cftbsub(this.nBluestein * 2, dArr2, 0, this.ip, this.nw, this.w);
            if (i2 > 0) {
                for (int i12 = 0; i12 < this.nBluestein; i12++) {
                    int i13 = i12 * 2;
                    int i14 = i13 + 1;
                    double d3 = dArr2[i13];
                    double[] dArr5 = this.bk2;
                    double d4 = dArr5[i14];
                    double d5 = dArr2[i14];
                    double d6 = dArr5[i13];
                    dArr2[i13] = (d3 * d6) + (d5 * d4);
                    dArr2[i14] = ((-d3) * d4) + (d5 * d6);
                }
            } else {
                for (int i15 = 0; i15 < this.nBluestein; i15++) {
                    int i16 = i15 * 2;
                    int i17 = i16 + 1;
                    double d7 = dArr2[i16];
                    double[] dArr6 = this.bk2;
                    double d8 = dArr6[i17];
                    double d9 = dArr2[i17];
                    double d10 = dArr6[i16];
                    dArr2[i16] = (d7 * d10) - (d9 * d8);
                    dArr2[i17] = (d7 * d8) + (d9 * d10);
                }
            }
            CommonUtils.cftfsub(this.nBluestein * 2, dArr2, 0, this.ip, this.nw, this.w);
            if (i2 > 0) {
                while (i3 < this.n) {
                    int i18 = i3 * 2;
                    int i19 = i18 + 1;
                    double[] dArr7 = this.bk1;
                    dArr[i + i18] = (dArr7[i18] * dArr2[i18]) - (dArr7[i19] * dArr2[i19]);
                    dArr[i + i19] = (dArr7[i19] * dArr2[i18]) + (dArr7[i18] * dArr2[i19]);
                    i3++;
                }
                return;
            }
            while (i3 < this.n) {
                int i20 = i3 * 2;
                int i21 = i20 + 1;
                double[] dArr8 = this.bk1;
                dArr[i + i20] = (dArr8[i20] * dArr2[i20]) + (dArr8[i21] * dArr2[i21]);
                dArr[i + i21] = ((-dArr8[i21]) * dArr2[i20]) + (dArr8[i20] * dArr2[i21]);
                i3++;
            }
            return;
        }
        int i22 = (numberOfThreads < 4 || ((long) this.n) < CommonUtils.getThreadsBeginN_1D_FFT_4Threads()) ? 2 : 4;
        Future[] futureArr = new Future[i22];
        int i23 = this.n / i22;
        int i24 = 0;
        while (i24 < i22) {
            final int i25 = i24 * i23;
            final int i26 = i24 == i22 + -1 ? this.n : i25 + i23;
            final int i27 = i2;
            final int i28 = i;
            final double[] dArr9 = dArr2;
            int i29 = i24;
            final double[] dArr10 = dArr;
            futureArr[i29] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    if (i27 > 0) {
                        for (int i = i25; i < i26; i++) {
                            int i2 = i * 2;
                            int i3 = i2 + 1;
                            int i4 = i28 + i;
                            dArr9[i2] = dArr10[i4] * DoubleFFT_1D.this.bk1[i2];
                            dArr9[i3] = dArr10[i4] * DoubleFFT_1D.this.bk1[i3];
                        }
                        return;
                    }
                    for (int i5 = i25; i5 < i26; i5++) {
                        int i6 = i5 * 2;
                        int i7 = i6 + 1;
                        int i8 = i28 + i5;
                        dArr9[i6] = dArr10[i8] * DoubleFFT_1D.this.bk1[i6];
                        dArr9[i7] = (-dArr10[i8]) * DoubleFFT_1D.this.bk1[i7];
                    }
                }
            });
            i24 = i29 + 1;
        }
        String str = null;
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
        } catch (ExecutionException e2) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
        }
        CommonUtils.cftbsub(this.nBluestein * 2, dArr2, 0, this.ip, this.nw, this.w);
        int i30 = this.nBluestein / i22;
        int i31 = 0;
        while (i31 < i22) {
            final int i32 = i31 * i30;
            final int i33 = i31 == i22 + -1 ? this.nBluestein : i32 + i30;
            final int i34 = i2;
            final double[] dArr11 = dArr2;
            futureArr[i31] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    if (i34 > 0) {
                        for (int i = i32; i < i33; i++) {
                            int i2 = i * 2;
                            int i3 = i2 + 1;
                            double d = ((-dArr11[i2]) * DoubleFFT_1D.this.bk2[i3]) + (dArr11[i3] * DoubleFFT_1D.this.bk2[i2]);
                            double[] dArr = dArr11;
                            dArr[i2] = (dArr[i2] * DoubleFFT_1D.this.bk2[i2]) + (dArr11[i3] * DoubleFFT_1D.this.bk2[i3]);
                            dArr11[i3] = d;
                        }
                        return;
                    }
                    for (int i4 = i32; i4 < i33; i4++) {
                        int i5 = i4 * 2;
                        int i6 = i5 + 1;
                        double d2 = (dArr11[i5] * DoubleFFT_1D.this.bk2[i6]) + (dArr11[i6] * DoubleFFT_1D.this.bk2[i5]);
                        double[] dArr2 = dArr11;
                        dArr2[i5] = (dArr2[i5] * DoubleFFT_1D.this.bk2[i5]) - (dArr11[i6] * DoubleFFT_1D.this.bk2[i6]);
                        dArr11[i6] = d2;
                    }
                }
            });
            i31++;
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e3) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e3);
        } catch (ExecutionException e4) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e4);
        }
        CommonUtils.cftfsub(this.nBluestein * 2, dArr2, 0, this.ip, this.nw, this.w);
        int i35 = this.n / i22;
        while (i3 < i22) {
            final int i36 = i3 * i35;
            final int i37 = i3 == i22 + -1 ? this.n : i36 + i35;
            final int i38 = i2;
            final double[] dArr12 = dArr;
            final int i39 = i;
            int i40 = i22;
            String str2 = str;
            final double[] dArr13 = dArr2;
            futureArr[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    if (i38 > 0) {
                        for (int i = i36; i < i37; i++) {
                            int i2 = i * 2;
                            int i3 = i2 + 1;
                            dArr12[i39 + i2] = (DoubleFFT_1D.this.bk1[i2] * dArr13[i2]) - (DoubleFFT_1D.this.bk1[i3] * dArr13[i3]);
                            dArr12[i39 + i3] = (DoubleFFT_1D.this.bk1[i3] * dArr13[i2]) + (DoubleFFT_1D.this.bk1[i2] * dArr13[i3]);
                        }
                        return;
                    }
                    for (int i4 = i36; i4 < i37; i4++) {
                        int i5 = i4 * 2;
                        int i6 = i5 + 1;
                        dArr12[i39 + i5] = (DoubleFFT_1D.this.bk1[i5] * dArr13[i5]) + (DoubleFFT_1D.this.bk1[i6] * dArr13[i6]);
                        dArr12[i39 + i6] = ((-DoubleFFT_1D.this.bk1[i6]) * dArr13[i5]) + (DoubleFFT_1D.this.bk1[i5] * dArr13[i6]);
                    }
                }
            });
            i3++;
            str = str2;
            i22 = i40;
        }
        String str3 = str;
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e5) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e5);
        } catch (ExecutionException e6) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e6);
        }
    }

    private void bluestein_real_full(DoubleLargeArray doubleLargeArray, long j, long j2) {
        DoubleLargeArray doubleLargeArray2;
        DoubleLargeArray doubleLargeArray3 = doubleLargeArray;
        Class<DoubleFFT_1D> cls = DoubleFFT_1D.class;
        DoubleLargeArray doubleLargeArray4 = new DoubleLargeArray(this.nBluesteinl * 2);
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (numberOfThreads <= 1 || this.nl <= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
            DoubleLargeArray doubleLargeArray5 = doubleLargeArray4;
            int i = (j2 > 0 ? 1 : (j2 == 0 ? 0 : -1));
            long j3 = 1;
            if (i > 0) {
                for (long j4 = 0; j4 < this.nl; j4++) {
                    long j5 = j4 * 2;
                    long j6 = j5 + 1;
                    long j7 = j + j4;
                    DoubleLargeArray doubleLargeArray6 = doubleLargeArray;
                    DoubleLargeArray doubleLargeArray7 = doubleLargeArray5;
                    doubleLargeArray7.setDouble(j5, doubleLargeArray6.getDouble(j7) * this.bk1l.getDouble(j5));
                    doubleLargeArray7.setDouble(j6, doubleLargeArray6.getDouble(j7) * this.bk1l.getDouble(j6));
                }
                DoubleLargeArray doubleLargeArray8 = doubleLargeArray;
                doubleLargeArray2 = doubleLargeArray5;
            } else {
                DoubleLargeArray doubleLargeArray9 = doubleLargeArray;
                doubleLargeArray2 = doubleLargeArray5;
                long j8 = 0;
                while (j8 < this.nl) {
                    long j9 = j8 * 2;
                    long j10 = j9 + j3;
                    long j11 = j + j8;
                    doubleLargeArray2.setDouble(j9, doubleLargeArray9.getDouble(j11) * this.bk1l.getDouble(j9));
                    doubleLargeArray2.setDouble(j10, (-doubleLargeArray9.getDouble(j11)) * this.bk1l.getDouble(j10));
                    j8++;
                    j3 = 1;
                }
            }
            DoubleLargeArray doubleLargeArray10 = doubleLargeArray2;
            CommonUtils.cftbsub(this.nBluesteinl * 2, doubleLargeArray2, 0, this.ipl, this.nwl, this.wl);
            if (i > 0) {
                long j12 = 0;
                while (j12 < this.nBluesteinl) {
                    long j13 = j12 * 2;
                    long j14 = j13 + 1;
                    double d = ((-doubleLargeArray10.getDouble(j13)) * this.bk2l.getDouble(j14)) + (doubleLargeArray10.getDouble(j14) * this.bk2l.getDouble(j13));
                    doubleLargeArray10.setDouble(j13, (doubleLargeArray10.getDouble(j13) * this.bk2l.getDouble(j13)) + (doubleLargeArray10.getDouble(j14) * this.bk2l.getDouble(j14)));
                    doubleLargeArray10.setDouble(j14, d);
                    j12++;
                    DoubleLargeArray doubleLargeArray11 = doubleLargeArray;
                }
            } else {
                long j15 = 1;
                for (long j16 = 0; j16 < this.nBluesteinl; j16++) {
                    long j17 = j16 * 2;
                    long j18 = j17 + j15;
                    double d2 = (doubleLargeArray10.getDouble(j17) * this.bk2l.getDouble(j18)) + (doubleLargeArray10.getDouble(j18) * this.bk2l.getDouble(j17));
                    doubleLargeArray10.setDouble(j17, (doubleLargeArray10.getDouble(j17) * this.bk2l.getDouble(j17)) - (doubleLargeArray10.getDouble(j18) * this.bk2l.getDouble(j18)));
                    doubleLargeArray10.setDouble(j18, d2);
                    j15 = 1;
                }
            }
            CommonUtils.cftfsub(this.nBluesteinl * 2, doubleLargeArray10, 0, this.ipl, this.nwl, this.wl);
            if (i > 0) {
                for (long j19 = 0; j19 < this.nl; j19++) {
                    long j20 = j19 * 2;
                    long j21 = j20 + 1;
                    DoubleLargeArray doubleLargeArray12 = doubleLargeArray;
                    doubleLargeArray12.setDouble(j + j20, (this.bk1l.getDouble(j20) * doubleLargeArray10.getDouble(j20)) - (this.bk1l.getDouble(j21) * doubleLargeArray10.getDouble(j21)));
                    doubleLargeArray12.setDouble(j + j21, (this.bk1l.getDouble(j21) * doubleLargeArray10.getDouble(j20)) + (this.bk1l.getDouble(j20) * doubleLargeArray10.getDouble(j21)));
                }
                return;
            }
            DoubleLargeArray doubleLargeArray13 = doubleLargeArray;
            long j22 = 1;
            for (long j23 = 0; j23 < this.nl; j23++) {
                long j24 = j23 * 2;
                long j25 = j24 + j22;
                doubleLargeArray13.setDouble(j + j24, (this.bk1l.getDouble(j24) * doubleLargeArray10.getDouble(j24)) + (this.bk1l.getDouble(j25) * doubleLargeArray10.getDouble(j25)));
                doubleLargeArray13.setDouble(j + j25, ((-this.bk1l.getDouble(j25)) * doubleLargeArray10.getDouble(j24)) + (this.bk1l.getDouble(j24) * doubleLargeArray10.getDouble(j25)));
                j22 = 1;
            }
            return;
        }
        int i2 = 4;
        if (numberOfThreads < 4 || this.nl <= CommonUtils.getThreadsBeginN_1D_FFT_4Threads()) {
            i2 = 2;
        }
        int i3 = i2;
        Future[] futureArr = new Future[i3];
        long j26 = (long) i3;
        long j27 = this.nl / j26;
        int i4 = 0;
        while (i4 < i3) {
            final long j28 = ((long) i4) * j27;
            final long j29 = j2;
            long j30 = j26;
            final long j31 = i4 == i3 + -1 ? this.nl : j28 + j27;
            Future[] futureArr2 = futureArr;
            final long j32 = j;
            int i5 = i3;
            final DoubleLargeArray doubleLargeArray14 = doubleLargeArray4;
            DoubleLargeArray doubleLargeArray15 = doubleLargeArray4;
            final DoubleLargeArray doubleLargeArray16 = doubleLargeArray;
            futureArr2[i4] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    long j = 2;
                    if (j29 > 0) {
                        long j2 = j28;
                        while (j2 < j31) {
                            long j3 = j2 * j;
                            long j4 = j3 + 1;
                            long j5 = j32 + j2;
                            doubleLargeArray14.setDouble(j3, doubleLargeArray16.getDouble(j5) * DoubleFFT_1D.this.bk1l.getDouble(j3));
                            doubleLargeArray14.setDouble(j4, doubleLargeArray16.getDouble(j5) * DoubleFFT_1D.this.bk1l.getDouble(j4));
                            j2++;
                            j = 2;
                        }
                        return;
                    }
                    for (long j6 = j28; j6 < j31; j6++) {
                        long j7 = j6 * 2;
                        long j8 = j7 + 1;
                        long j9 = j32 + j6;
                        doubleLargeArray14.setDouble(j7, doubleLargeArray16.getDouble(j9) * DoubleFFT_1D.this.bk1l.getDouble(j7));
                        doubleLargeArray14.setDouble(j8, (-doubleLargeArray16.getDouble(j9)) * DoubleFFT_1D.this.bk1l.getDouble(j8));
                    }
                }
            });
            i4++;
            i3 = i5;
            futureArr = futureArr2;
            doubleLargeArray4 = doubleLargeArray15;
            j26 = j30;
            DoubleLargeArray doubleLargeArray17 = doubleLargeArray;
        }
        long j33 = j26;
        Future[] futureArr3 = futureArr;
        int i6 = i3;
        DoubleLargeArray doubleLargeArray18 = doubleLargeArray4;
        String str = null;
        try {
            ConcurrencyUtils.waitForCompletion(futureArr3);
        } catch (InterruptedException e) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
        } catch (ExecutionException e2) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
        }
        CommonUtils.cftbsub(this.nBluesteinl * 2, doubleLargeArray18, 0, this.ipl, this.nwl, this.wl);
        long j34 = this.nBluesteinl / j33;
        int i7 = 0;
        while (i7 < i6) {
            final long j35 = ((long) i7) * j34;
            final long j36 = i7 == i6 + -1 ? this.nBluesteinl : j35 + j34;
            final long j37 = j2;
            final DoubleLargeArray doubleLargeArray19 = doubleLargeArray18;
            futureArr3[i7] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    long j = 2;
                    long j2 = 1;
                    if (j37 > 0) {
                        long j3 = j35;
                        while (j3 < j36) {
                            long j4 = j3 * j;
                            long j5 = j4 + j2;
                            double d = ((-doubleLargeArray19.getDouble(j4)) * DoubleFFT_1D.this.bk2l.getDouble(j5)) + (doubleLargeArray19.getDouble(j5) * DoubleFFT_1D.this.bk2l.getDouble(j4));
                            DoubleLargeArray doubleLargeArray = doubleLargeArray19;
                            doubleLargeArray.setDouble(j4, (doubleLargeArray.getDouble(j4) * DoubleFFT_1D.this.bk2l.getDouble(j4)) + (doubleLargeArray19.getDouble(j5) * DoubleFFT_1D.this.bk2l.getDouble(j5)));
                            doubleLargeArray19.setDouble(j5, d);
                            j3++;
                            j2 = 1;
                            j = 2;
                        }
                        return;
                    }
                    long j6 = 1;
                    for (long j7 = j35; j7 < j36; j7++) {
                        long j8 = j7 * 2;
                        long j9 = j8 + j6;
                        double d2 = (doubleLargeArray19.getDouble(j8) * DoubleFFT_1D.this.bk2l.getDouble(j9)) + (doubleLargeArray19.getDouble(j9) * DoubleFFT_1D.this.bk2l.getDouble(j8));
                        DoubleLargeArray doubleLargeArray2 = doubleLargeArray19;
                        doubleLargeArray2.setDouble(j8, (doubleLargeArray2.getDouble(j8) * DoubleFFT_1D.this.bk2l.getDouble(j8)) - (doubleLargeArray19.getDouble(j9) * DoubleFFT_1D.this.bk2l.getDouble(j9)));
                        doubleLargeArray19.setDouble(j9, d2);
                        j6 = 1;
                    }
                }
            });
            i7++;
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr3);
        } catch (InterruptedException e3) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e3);
        } catch (ExecutionException e4) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e4);
        }
        CommonUtils.cftfsub(this.nBluesteinl * 2, doubleLargeArray18, 0, this.ipl, this.nwl, this.wl);
        long j38 = this.nl / j33;
        int i8 = 0;
        while (i8 < i6) {
            final long j39 = ((long) i8) * j38;
            final long j40 = i8 == i6 + -1 ? this.nl : j39 + j38;
            final long j41 = j2;
            final DoubleLargeArray doubleLargeArray20 = doubleLargeArray;
            final long j42 = j;
            int i9 = i6;
            String str2 = str;
            final DoubleLargeArray doubleLargeArray21 = doubleLargeArray18;
            futureArr3[i8] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    long j = 2;
                    long j2 = 1;
                    if (j41 > 0) {
                        long j3 = j39;
                        while (j3 < j40) {
                            long j4 = j3 * j;
                            long j5 = j4 + j2;
                            doubleLargeArray20.setDouble(j42 + j4, (DoubleFFT_1D.this.bk1l.getDouble(j4) * doubleLargeArray21.getDouble(j4)) - (DoubleFFT_1D.this.bk1l.getDouble(j5) * doubleLargeArray21.getDouble(j5)));
                            doubleLargeArray20.setDouble(j42 + j5, (DoubleFFT_1D.this.bk1l.getDouble(j5) * doubleLargeArray21.getDouble(j4)) + (DoubleFFT_1D.this.bk1l.getDouble(j4) * doubleLargeArray21.getDouble(j5)));
                            j3++;
                            j2 = 1;
                            j = 2;
                        }
                        return;
                    }
                    long j6 = 1;
                    for (long j7 = j39; j7 < j40; j7++) {
                        long j8 = j7 * 2;
                        long j9 = j8 + j6;
                        doubleLargeArray20.setDouble(j42 + j8, (DoubleFFT_1D.this.bk1l.getDouble(j8) * doubleLargeArray21.getDouble(j8)) + (DoubleFFT_1D.this.bk1l.getDouble(j9) * doubleLargeArray21.getDouble(j9)));
                        doubleLargeArray20.setDouble(j42 + j9, ((-DoubleFFT_1D.this.bk1l.getDouble(j9)) * doubleLargeArray21.getDouble(j8)) + (DoubleFFT_1D.this.bk1l.getDouble(j8) * doubleLargeArray21.getDouble(j9)));
                        j6 = 1;
                    }
                }
            });
            i8++;
            str = str2;
            i6 = i9;
        }
        String str3 = str;
        try {
            ConcurrencyUtils.waitForCompletion(futureArr3);
        } catch (InterruptedException e5) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e5);
        } catch (ExecutionException e6) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e6);
        }
    }

    private void bluestein_real_forward(double[] dArr, int i) {
        Class<DoubleFFT_1D> cls = DoubleFFT_1D.class;
        final double[] dArr2 = new double[(this.nBluestein * 2)];
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (numberOfThreads <= 1 || ((long) this.n) < CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
            for (int i2 = 0; i2 < this.n; i2++) {
                int i3 = i2 * 2;
                int i4 = i3 + 1;
                int i5 = i + i2;
                double d = dArr[i5];
                double[] dArr3 = this.bk1;
                dArr2[i3] = d * dArr3[i3];
                dArr2[i4] = (-dArr[i5]) * dArr3[i4];
            }
            CommonUtils.cftbsub(this.nBluestein * 2, dArr2, 0, this.ip, this.nw, this.w);
            for (int i6 = 0; i6 < this.nBluestein; i6++) {
                int i7 = i6 * 2;
                int i8 = i7 + 1;
                double d2 = dArr2[i7];
                double[] dArr4 = this.bk2;
                double d3 = dArr4[i8];
                double d4 = dArr2[i8];
                double d5 = dArr4[i7];
                dArr2[i7] = (d2 * d5) - (d4 * d3);
                dArr2[i8] = (d2 * d3) + (d4 * d5);
            }
        } else {
            int i9 = (numberOfThreads < 4 || ((long) this.n) < CommonUtils.getThreadsBeginN_1D_FFT_4Threads()) ? 2 : 4;
            Future[] futureArr = new Future[i9];
            int i10 = this.n / i9;
            int i11 = 0;
            while (i11 < i9) {
                final int i12 = i11 * i10;
                final int i13 = i11 == i9 + -1 ? this.n : i12 + i10;
                final int i14 = i;
                final double[] dArr5 = dArr2;
                int i15 = i11;
                final double[] dArr6 = dArr;
                futureArr[i15] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i12; i < i13; i++) {
                            int i2 = i * 2;
                            int i3 = i2 + 1;
                            int i4 = i14 + i;
                            dArr5[i2] = dArr6[i4] * DoubleFFT_1D.this.bk1[i2];
                            dArr5[i3] = (-dArr6[i4]) * DoubleFFT_1D.this.bk1[i3];
                        }
                    }
                });
                i11 = i15 + 1;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
            } catch (ExecutionException e2) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
            }
            CommonUtils.cftbsub(this.nBluestein * 2, dArr2, 0, this.ip, this.nw, this.w);
            int i16 = this.nBluestein / i9;
            int i17 = 0;
            while (i17 < i9) {
                final int i18 = i17 * i16;
                final int i19 = i17 == i9 + -1 ? this.nBluestein : i18 + i16;
                futureArr[i17] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i18; i < i19; i++) {
                            int i2 = i * 2;
                            int i3 = i2 + 1;
                            double d = (dArr2[i2] * DoubleFFT_1D.this.bk2[i3]) + (dArr2[i3] * DoubleFFT_1D.this.bk2[i2]);
                            double[] dArr = dArr2;
                            dArr[i2] = (dArr[i2] * DoubleFFT_1D.this.bk2[i2]) - (dArr2[i3] * DoubleFFT_1D.this.bk2[i3]);
                            dArr2[i3] = d;
                        }
                    }
                });
                i17++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e3) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e4);
            }
        }
        CommonUtils.cftfsub(this.nBluestein * 2, dArr2, 0, this.ip, this.nw, this.w);
        int i20 = this.n;
        if (i20 % 2 == 0) {
            double[] dArr7 = this.bk1;
            dArr[i] = (dArr7[0] * dArr2[0]) + (dArr7[1] * dArr2[1]);
            dArr[i + 1] = (dArr7[i20] * dArr2[i20]) + (dArr7[i20 + 1] * dArr2[i20 + 1]);
            for (int i21 = 1; i21 < this.n / 2; i21++) {
                int i22 = i21 * 2;
                int i23 = i22 + 1;
                double[] dArr8 = this.bk1;
                dArr[i + i22] = (dArr8[i22] * dArr2[i22]) + (dArr8[i23] * dArr2[i23]);
                dArr[i + i23] = ((-dArr8[i23]) * dArr2[i22]) + (dArr8[i22] * dArr2[i23]);
            }
            return;
        }
        double[] dArr9 = this.bk1;
        dArr[i] = (dArr9[0] * dArr2[0]) + (dArr9[1] * dArr2[1]);
        dArr[i + 1] = ((-dArr9[i20]) * dArr2[i20 - 1]) + (dArr9[i20 - 1] * dArr2[i20]);
        int i24 = 1;
        while (true) {
            int i25 = this.n;
            if (i24 < (i25 - 1) / 2) {
                int i26 = i24 * 2;
                int i27 = i26 + 1;
                double[] dArr10 = this.bk1;
                dArr[i + i26] = (dArr10[i26] * dArr2[i26]) + (dArr10[i27] * dArr2[i27]);
                dArr[i + i27] = ((-dArr10[i27]) * dArr2[i26]) + (dArr10[i26] * dArr2[i27]);
                i24++;
            } else {
                double[] dArr11 = this.bk1;
                dArr[(i + i25) - 1] = (dArr11[i25 - 1] * dArr2[i25 - 1]) + (dArr11[i25] * dArr2[i25]);
                return;
            }
        }
    }

    private void bluestein_real_forward(DoubleLargeArray doubleLargeArray, long j) {
        long j2;
        DoubleLargeArray doubleLargeArray2;
        DoubleLargeArray doubleLargeArray3 = doubleLargeArray;
        long j3 = j;
        Class<DoubleFFT_1D> cls = DoubleFFT_1D.class;
        DoubleLargeArray doubleLargeArray4 = new DoubleLargeArray(this.nBluesteinl * 2);
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (numberOfThreads <= 1 || this.nl <= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
            DoubleLargeArray doubleLargeArray5 = doubleLargeArray4;
            for (long j4 = 0; j4 < this.nl; j4++) {
                long j5 = j4 * 2;
                long j6 = j5 + 1;
                long j7 = j + j4;
                DoubleLargeArray doubleLargeArray6 = doubleLargeArray;
                DoubleLargeArray doubleLargeArray7 = doubleLargeArray5;
                doubleLargeArray7.setDouble(j5, doubleLargeArray6.getDouble(j7) * this.bk1l.getDouble(j5));
                doubleLargeArray7.setDouble(j6, (-doubleLargeArray6.getDouble(j7)) * this.bk1l.getDouble(j6));
            }
            DoubleLargeArray doubleLargeArray8 = doubleLargeArray;
            j2 = j;
            DoubleLargeArray doubleLargeArray9 = doubleLargeArray5;
            doubleLargeArray2 = doubleLargeArray9;
            CommonUtils.cftbsub(this.nBluesteinl * 2, doubleLargeArray9, 0, this.ipl, this.nwl, this.wl);
            long j8 = 0;
            while (j8 < this.nBluesteinl) {
                long j9 = j8 * 2;
                long j10 = j9 + 1;
                double d = (doubleLargeArray2.getDouble(j9) * this.bk2l.getDouble(j10)) + (doubleLargeArray2.getDouble(j10) * this.bk2l.getDouble(j9));
                doubleLargeArray2.setDouble(j9, (doubleLargeArray2.getDouble(j9) * this.bk2l.getDouble(j9)) - (doubleLargeArray2.getDouble(j10) * this.bk2l.getDouble(j10)));
                doubleLargeArray2.setDouble(j10, d);
                j8++;
                DoubleLargeArray doubleLargeArray10 = doubleLargeArray;
            }
        } else {
            int i = 4;
            if (numberOfThreads < 4 || this.nl <= CommonUtils.getThreadsBeginN_1D_FFT_4Threads()) {
                i = 2;
            }
            int i2 = i;
            Future[] futureArr = new Future[i2];
            long j11 = (long) i2;
            long j12 = this.nl / j11;
            int i3 = 0;
            while (i3 < i2) {
                long j13 = ((long) i3) * j12;
                Future[] futureArr2 = futureArr;
                final long j14 = j13;
                final long j15 = i3 == i2 + -1 ? this.nl : j13 + j12;
                final long j16 = j;
                int i4 = i2;
                final DoubleLargeArray doubleLargeArray11 = doubleLargeArray4;
                DoubleLargeArray doubleLargeArray12 = doubleLargeArray4;
                final DoubleLargeArray doubleLargeArray13 = doubleLargeArray;
                futureArr2[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (long j = j14; j < j15; j++) {
                            long j2 = 2 * j;
                            long j3 = j2 + 1;
                            long j4 = j16 + j;
                            doubleLargeArray11.setDouble(j2, doubleLargeArray13.getDouble(j4) * DoubleFFT_1D.this.bk1l.getDouble(j2));
                            doubleLargeArray11.setDouble(j3, (-doubleLargeArray13.getDouble(j4)) * DoubleFFT_1D.this.bk1l.getDouble(j3));
                        }
                    }
                });
                i3++;
                i2 = i4;
                doubleLargeArray4 = doubleLargeArray12;
                futureArr = futureArr2;
            }
            Future[] futureArr3 = futureArr;
            int i5 = i2;
            DoubleLargeArray doubleLargeArray14 = doubleLargeArray4;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
            } catch (ExecutionException e2) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
            }
            CommonUtils.cftbsub(this.nBluesteinl * 2, doubleLargeArray14, 0, this.ipl, this.nwl, this.wl);
            long j17 = this.nBluesteinl / j11;
            int i6 = 0;
            while (i6 < i5) {
                final long j18 = ((long) i6) * j17;
                final long j19 = i6 == i5 + -1 ? this.nBluesteinl : j18 + j17;
                final DoubleLargeArray doubleLargeArray15 = doubleLargeArray14;
                futureArr3[i6] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (long j = j18; j < j19; j++) {
                            long j2 = 2 * j;
                            long j3 = j2 + 1;
                            double d = (doubleLargeArray15.getDouble(j2) * DoubleFFT_1D.this.bk2l.getDouble(j3)) + (doubleLargeArray15.getDouble(j3) * DoubleFFT_1D.this.bk2l.getDouble(j2));
                            DoubleLargeArray doubleLargeArray = doubleLargeArray15;
                            doubleLargeArray.setDouble(j2, (doubleLargeArray.getDouble(j2) * DoubleFFT_1D.this.bk2l.getDouble(j2)) - (doubleLargeArray15.getDouble(j3) * DoubleFFT_1D.this.bk2l.getDouble(j3)));
                            doubleLargeArray15.setDouble(j3, d);
                        }
                    }
                });
                i6++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e3) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e4);
            }
            j2 = j;
            doubleLargeArray2 = doubleLargeArray14;
        }
        CommonUtils.cftfsub(this.nBluesteinl * 2, doubleLargeArray2, 0, this.ipl, this.nwl, this.wl);
        if (this.nl % 2 == 0) {
            long j20 = 1;
            DoubleLargeArray doubleLargeArray16 = doubleLargeArray;
            doubleLargeArray16.setDouble(j2, (this.bk1l.getDouble(0) * doubleLargeArray2.getDouble(0)) + (this.bk1l.getDouble(1) * doubleLargeArray2.getDouble(1)));
            doubleLargeArray16.setDouble(j2 + 1, (this.bk1l.getDouble(this.nl) * doubleLargeArray2.getDouble(this.nl)) + (this.bk1l.getDouble(this.nl + 1) * doubleLargeArray2.getDouble(this.nl + 1)));
            long j21 = 1;
            while (j21 < this.nl / 2) {
                long j22 = j21 * 2;
                long j23 = j22 + j20;
                long j24 = j;
                doubleLargeArray16.setDouble(j24 + j22, (this.bk1l.getDouble(j22) * doubleLargeArray2.getDouble(j22)) + (this.bk1l.getDouble(j23) * doubleLargeArray2.getDouble(j23)));
                doubleLargeArray16.setDouble(j24 + j23, ((-this.bk1l.getDouble(j23)) * doubleLargeArray2.getDouble(j22)) + (this.bk1l.getDouble(j22) * doubleLargeArray2.getDouble(j23)));
                j21++;
                j20 = 1;
            }
            return;
        }
        DoubleLargeArray doubleLargeArray17 = doubleLargeArray;
        long j25 = j2;
        doubleLargeArray17.setDouble(j25, (this.bk1l.getDouble(0) * doubleLargeArray2.getDouble(0)) + (this.bk1l.getDouble(1) * doubleLargeArray2.getDouble(1)));
        doubleLargeArray17.setDouble(j25 + 1, ((-this.bk1l.getDouble(this.nl)) * doubleLargeArray2.getDouble(this.nl - 1)) + (this.bk1l.getDouble(this.nl - 1) * doubleLargeArray2.getDouble(this.nl)));
        long j26 = 1;
        while (true) {
            long j27 = this.nl;
            if (j26 < (j27 - 1) / 2) {
                long j28 = j26 * 2;
                long j29 = j28 + 1;
                doubleLargeArray17.setDouble(j25 + j28, (this.bk1l.getDouble(j28) * doubleLargeArray2.getDouble(j28)) + (this.bk1l.getDouble(j29) * doubleLargeArray2.getDouble(j29)));
                doubleLargeArray17.setDouble(j25 + j29, ((-this.bk1l.getDouble(j29)) * doubleLargeArray2.getDouble(j28)) + (this.bk1l.getDouble(j28) * doubleLargeArray2.getDouble(j29)));
                j26++;
            } else {
                doubleLargeArray17.setDouble((j25 + j27) - 1, (this.bk1l.getDouble(j27 - 1) * doubleLargeArray2.getDouble(this.nl - 1)) + (this.bk1l.getDouble(this.nl) * doubleLargeArray2.getDouble(this.nl)));
                return;
            }
        }
    }

    private void bluestein_real_inverse(double[] dArr, int i) {
        int i2;
        int i3;
        int i4;
        Class<DoubleFFT_1D> cls = DoubleFFT_1D.class;
        final double[] dArr2 = new double[(this.nBluestein * 2)];
        int i5 = 0;
        if (this.n % 2 != 0) {
            double d = dArr[i];
            double[] dArr3 = this.bk1;
            dArr2[0] = d * dArr3[0];
            dArr2[1] = dArr[i] * dArr3[1];
            int i6 = 1;
            while (true) {
                i3 = this.n;
                if (i6 >= (i3 - 1) / 2) {
                    break;
                }
                int i7 = i6 * 2;
                int i8 = i7 + 1;
                int i9 = i + i7;
                int i10 = i + i8;
                double d2 = dArr[i9];
                double[] dArr4 = this.bk1;
                dArr2[i7] = (d2 * dArr4[i7]) - (dArr[i10] * dArr4[i8]);
                dArr2[i8] = (dArr[i9] * dArr4[i8]) + (dArr[i10] * dArr4[i7]);
                i6++;
            }
            double d3 = dArr[(i + i3) - 1];
            double[] dArr5 = this.bk1;
            int i11 = i + 1;
            dArr2[i3 - 1] = (d3 * dArr5[i3 - 1]) - (dArr[i11] * dArr5[i3]);
            dArr2[i3] = (dArr[(i + i3) - 1] * dArr5[i3]) + (dArr[i11] * dArr5[i3 - 1]);
            dArr2[i3 + 1] = (dArr[(i + i3) - 1] * dArr5[i3 + 1]) + (dArr[i11] * dArr5[i3 + 2]);
            dArr2[i3 + 2] = (dArr[(i + i3) - 1] * dArr5[i3 + 2]) - (dArr[i11] * dArr5[i3 + 1]);
            int i12 = ((i3 - 1) / 2) + 2;
            while (true) {
                int i13 = this.n;
                if (i12 >= i13) {
                    break;
                }
                int i14 = i12 * 2;
                int i15 = i14 + 1;
                int i16 = (i + (i13 * 2)) - i14;
                int i17 = i16 + 1;
                double d4 = dArr[i16];
                double[] dArr6 = this.bk1;
                dArr2[i14] = (d4 * dArr6[i14]) + (dArr[i17] * dArr6[i15]);
                dArr2[i15] = (dArr[i16] * dArr6[i15]) - (dArr[i17] * dArr6[i14]);
                i12++;
            }
        } else {
            double d5 = dArr[i];
            double[] dArr7 = this.bk1;
            dArr2[0] = d5 * dArr7[0];
            dArr2[1] = dArr[i] * dArr7[1];
            int i18 = 1;
            while (true) {
                i4 = this.n;
                if (i18 >= i4 / 2) {
                    break;
                }
                int i19 = i18 * 2;
                int i20 = i19 + 1;
                int i21 = i + i19;
                int i22 = i + i20;
                double d6 = dArr[i21];
                double[] dArr8 = this.bk1;
                dArr2[i19] = (d6 * dArr8[i19]) - (dArr[i22] * dArr8[i20]);
                dArr2[i20] = (dArr[i21] * dArr8[i20]) + (dArr[i22] * dArr8[i19]);
                i18++;
            }
            int i23 = i + 1;
            double d7 = dArr[i23];
            double[] dArr9 = this.bk1;
            dArr2[i4] = d7 * dArr9[i4];
            dArr2[i4 + 1] = dArr[i23] * dArr9[i4 + 1];
            int i24 = (i4 / 2) + 1;
            while (true) {
                int i25 = this.n;
                if (i24 >= i25) {
                    break;
                }
                int i26 = i24 * 2;
                int i27 = i26 + 1;
                int i28 = (i + (i25 * 2)) - i26;
                int i29 = i28 + 1;
                double d8 = dArr[i28];
                double[] dArr10 = this.bk1;
                dArr2[i26] = (d8 * dArr10[i26]) + (dArr[i29] * dArr10[i27]);
                dArr2[i27] = (dArr[i28] * dArr10[i27]) - (dArr[i29] * dArr10[i26]);
                i24++;
            }
        }
        CommonUtils.cftbsub(this.nBluestein * 2, dArr2, 0, this.ip, this.nw, this.w);
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (numberOfThreads <= 1 || ((long) this.n) < CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
            int i30 = 0;
            while (true) {
                i2 = this.nBluestein;
                if (i30 >= i2) {
                    break;
                }
                int i31 = i30 * 2;
                int i32 = i31 + 1;
                double d9 = dArr2[i31];
                double[] dArr11 = this.bk2;
                double d10 = dArr11[i32];
                double d11 = dArr2[i32];
                double d12 = dArr11[i31];
                dArr2[i31] = (d9 * d12) + (d11 * d10);
                dArr2[i32] = ((-d9) * d10) + (d11 * d12);
                i30++;
            }
            CommonUtils.cftfsub(i2 * 2, dArr2, 0, this.ip, this.nw, this.w);
            while (i5 < this.n) {
                int i33 = i5 * 2;
                int i34 = i33 + 1;
                double[] dArr12 = this.bk1;
                dArr[i + i5] = (dArr12[i33] * dArr2[i33]) - (dArr12[i34] * dArr2[i34]);
                i5++;
            }
            return;
        }
        int i35 = (numberOfThreads < 4 || ((long) this.n) < CommonUtils.getThreadsBeginN_1D_FFT_4Threads()) ? 2 : 4;
        Future[] futureArr = new Future[i35];
        int i36 = this.nBluestein / i35;
        int i37 = 0;
        while (i37 < i35) {
            final int i38 = i37 * i36;
            final int i39 = i37 == i35 + -1 ? this.nBluestein : i38 + i36;
            futureArr[i37] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    for (int i = i38; i < i39; i++) {
                        int i2 = i * 2;
                        int i3 = i2 + 1;
                        double d = ((-dArr2[i2]) * DoubleFFT_1D.this.bk2[i3]) + (dArr2[i3] * DoubleFFT_1D.this.bk2[i2]);
                        double[] dArr = dArr2;
                        dArr[i2] = (dArr[i2] * DoubleFFT_1D.this.bk2[i2]) + (dArr2[i3] * DoubleFFT_1D.this.bk2[i3]);
                        dArr2[i3] = d;
                    }
                }
            });
            i37++;
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
        } catch (ExecutionException e2) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
        }
        CommonUtils.cftfsub(this.nBluestein * 2, dArr2, 0, this.ip, this.nw, this.w);
        int i40 = this.n / i35;
        while (i5 < i35) {
            final int i41 = i5 * i40;
            final int i42 = i5 == i35 + -1 ? this.n : i41 + i40;
            final double[] dArr13 = dArr;
            final int i43 = i;
            final double[] dArr14 = dArr2;
            futureArr[i5] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    for (int i = i41; i < i42; i++) {
                        int i2 = i * 2;
                        int i3 = i2 + 1;
                        dArr13[i43 + i] = (DoubleFFT_1D.this.bk1[i2] * dArr14[i2]) - (DoubleFFT_1D.this.bk1[i3] * dArr14[i3]);
                    }
                }
            });
            i5++;
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e3) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e3);
        } catch (ExecutionException e4) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e4);
        }
    }

    private void bluestein_real_inverse(DoubleLargeArray doubleLargeArray, long j) {
        long j2;
        long j3;
        long j4;
        long j5;
        DoubleLargeArray doubleLargeArray2 = doubleLargeArray;
        Class<DoubleFFT_1D> cls = DoubleFFT_1D.class;
        DoubleLargeArray doubleLargeArray3 = new DoubleLargeArray(this.nBluesteinl * 2);
        long j6 = 1;
        if (this.nl % 2 == 0) {
            doubleLargeArray3.setDouble(0, doubleLargeArray.getDouble(j) * this.bk1l.getDouble(0));
            doubleLargeArray3.setDouble(1, doubleLargeArray.getDouble(j) * this.bk1l.getDouble(1));
            long j7 = 1;
            while (true) {
                j5 = this.nl;
                if (j7 >= j5 / 2) {
                    break;
                }
                long j8 = j7 * 2;
                long j9 = j8 + j6;
                long j10 = j + j8;
                long j11 = j + j9;
                doubleLargeArray3.setDouble(j8, (doubleLargeArray2.getDouble(j10) * this.bk1l.getDouble(j8)) - (doubleLargeArray2.getDouble(j11) * this.bk1l.getDouble(j9)));
                doubleLargeArray3.setDouble(j9, (doubleLargeArray2.getDouble(j10) * this.bk1l.getDouble(j9)) + (doubleLargeArray2.getDouble(j11) * this.bk1l.getDouble(j8)));
                j6 = 1;
                j7++;
            }
            long j12 = j6;
            long j13 = j + j12;
            doubleLargeArray3.setDouble(j5, doubleLargeArray2.getDouble(j13) * this.bk1l.getDouble(this.nl));
            doubleLargeArray3.setDouble(this.nl + j12, doubleLargeArray2.getDouble(j13) * this.bk1l.getDouble(this.nl + j12));
            long j14 = (this.nl / 2) + j12;
            while (true) {
                long j15 = this.nl;
                if (j14 >= j15) {
                    break;
                }
                long j16 = j14 * 2;
                long j17 = j16 + j12;
                long j18 = (j + (j15 * 2)) - j16;
                long j19 = j14;
                long j20 = j18 + j12;
                doubleLargeArray3.setDouble(j16, (doubleLargeArray2.getDouble(j18) * this.bk1l.getDouble(j16)) + (doubleLargeArray2.getDouble(j20) * this.bk1l.getDouble(j17)));
                doubleLargeArray3.setDouble(j17, (doubleLargeArray2.getDouble(j18) * this.bk1l.getDouble(j17)) - (doubleLargeArray2.getDouble(j20) * this.bk1l.getDouble(j16)));
                j12 = 1;
                j14 = j19 + 1;
            }
            j2 = j12;
        } else {
            long j21 = 1;
            doubleLargeArray3.setDouble(0, doubleLargeArray.getDouble(j) * this.bk1l.getDouble(0));
            doubleLargeArray3.setDouble(1, doubleLargeArray.getDouble(j) * this.bk1l.getDouble(1));
            long j22 = 1;
            while (true) {
                j4 = this.nl;
                if (j22 >= (j4 - j21) / 2) {
                    break;
                }
                long j23 = j22 * 2;
                long j24 = j23 + j21;
                long j25 = j + j23;
                long j26 = j + j24;
                doubleLargeArray3.setDouble(j23, (doubleLargeArray2.getDouble(j25) * this.bk1l.getDouble(j23)) - (doubleLargeArray2.getDouble(j26) * this.bk1l.getDouble(j24)));
                doubleLargeArray3.setDouble(j24, (doubleLargeArray2.getDouble(j25) * this.bk1l.getDouble(j24)) + (doubleLargeArray2.getDouble(j26) * this.bk1l.getDouble(j23)));
                j21 = 1;
                j22++;
            }
            long j27 = j + j21;
            doubleLargeArray3.setDouble(j4 - j21, (doubleLargeArray2.getDouble((j + j4) - j21) * this.bk1l.getDouble(this.nl - j21)) - (doubleLargeArray2.getDouble(j27) * this.bk1l.getDouble(this.nl)));
            long j28 = this.nl;
            doubleLargeArray3.setDouble(j28, (doubleLargeArray2.getDouble((j + j28) - 1) * this.bk1l.getDouble(this.nl)) + (doubleLargeArray2.getDouble(j27) * this.bk1l.getDouble(this.nl - 1)));
            long j29 = this.nl;
            long j30 = j27;
            doubleLargeArray3.setDouble(j29 + 1, (doubleLargeArray2.getDouble((j + j29) - 1) * this.bk1l.getDouble(this.nl + 1)) + (doubleLargeArray2.getDouble(j30) * this.bk1l.getDouble(this.nl + 2)));
            long j31 = this.nl;
            long j32 = 1;
            doubleLargeArray3.setDouble(j31 + 2, (doubleLargeArray2.getDouble((j + j31) - 1) * this.bk1l.getDouble(this.nl + 2)) - (doubleLargeArray2.getDouble(j30) * this.bk1l.getDouble(this.nl + 1)));
            long j33 = ((this.nl - 1) / 2) + 2;
            while (true) {
                long j34 = this.nl;
                if (j33 >= j34) {
                    break;
                }
                long j35 = j33 * 2;
                long j36 = j35 + j32;
                long j37 = (j + (j34 * 2)) - j35;
                long j38 = j33;
                long j39 = j37 + j32;
                doubleLargeArray3.setDouble(j35, (doubleLargeArray2.getDouble(j37) * this.bk1l.getDouble(j35)) + (doubleLargeArray2.getDouble(j39) * this.bk1l.getDouble(j36)));
                doubleLargeArray3.setDouble(j36, (doubleLargeArray2.getDouble(j37) * this.bk1l.getDouble(j36)) - (doubleLargeArray2.getDouble(j39) * this.bk1l.getDouble(j35)));
                j33 = j38 + 1;
                j32 = 1;
            }
            j2 = j32;
        }
        long j40 = j2;
        CommonUtils.cftbsub(this.nBluesteinl * 2, doubleLargeArray3, 0, this.ipl, this.nwl, this.wl);
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (numberOfThreads <= 1 || this.nl <= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
            long j41 = 0;
            while (true) {
                j3 = this.nBluesteinl;
                if (j41 >= j3) {
                    break;
                }
                long j42 = j41 * 2;
                long j43 = j42 + j40;
                double d = ((-doubleLargeArray3.getDouble(j42)) * this.bk2l.getDouble(j43)) + (doubleLargeArray3.getDouble(j43) * this.bk2l.getDouble(j42));
                doubleLargeArray3.setDouble(j42, (doubleLargeArray3.getDouble(j42) * this.bk2l.getDouble(j42)) + (doubleLargeArray3.getDouble(j43) * this.bk2l.getDouble(j43)));
                doubleLargeArray3.setDouble(j43, d);
                j41 += j40;
            }
            CommonUtils.cftfsub(j3 * 2, doubleLargeArray3, 0, this.ipl, this.nwl, this.wl);
            for (long j44 = 0; j44 < this.nl; j44 += j40) {
                long j45 = j44 * 2;
                long j46 = j45 + j40;
                doubleLargeArray2.setDouble(j + j44, (this.bk1l.getDouble(j45) * doubleLargeArray3.getDouble(j45)) - (this.bk1l.getDouble(j46) * doubleLargeArray3.getDouble(j46)));
            }
            return;
        }
        int i = 4;
        if (numberOfThreads < 4 || this.nl <= CommonUtils.getThreadsBeginN_1D_FFT_4Threads()) {
            i = 2;
        }
        int i2 = i;
        Future[] futureArr = new Future[i2];
        long j47 = (long) i2;
        long j48 = this.nBluesteinl / j47;
        int i3 = 0;
        while (i3 < i2) {
            final long j49 = ((long) i3) * j48;
            final long j50 = i3 == i2 + -1 ? this.nBluesteinl : j49 + j48;
            long j51 = j47;
            final DoubleLargeArray doubleLargeArray4 = doubleLargeArray3;
            futureArr[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    for (long j = j49; j < j50; j++) {
                        long j2 = 2 * j;
                        long j3 = j2 + 1;
                        double d = ((-doubleLargeArray4.getDouble(j2)) * DoubleFFT_1D.this.bk2l.getDouble(j3)) + (doubleLargeArray4.getDouble(j3) * DoubleFFT_1D.this.bk2l.getDouble(j2));
                        DoubleLargeArray doubleLargeArray = doubleLargeArray4;
                        doubleLargeArray.setDouble(j2, (doubleLargeArray.getDouble(j2) * DoubleFFT_1D.this.bk2l.getDouble(j2)) + (doubleLargeArray4.getDouble(j3) * DoubleFFT_1D.this.bk2l.getDouble(j3)));
                        doubleLargeArray4.setDouble(j3, d);
                    }
                }
            });
            i3++;
            j47 = j51;
        }
        long j52 = j47;
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
        } catch (ExecutionException e2) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
        }
        Future[] futureArr2 = futureArr;
        CommonUtils.cftfsub(this.nBluesteinl * 2, doubleLargeArray3, 0, this.ipl, this.nwl, this.wl);
        long j53 = this.nl / j52;
        int i4 = 0;
        while (i4 < i2) {
            final long j54 = ((long) i4) * j53;
            final long j55 = i4 == i2 + -1 ? this.nl : j54 + j53;
            final DoubleLargeArray doubleLargeArray5 = doubleLargeArray;
            final long j56 = j;
            int i5 = i2;
            final DoubleLargeArray doubleLargeArray6 = doubleLargeArray3;
            futureArr2[i4] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    for (long j = j54; j < j55; j++) {
                        long j2 = 2 * j;
                        long j3 = j2 + 1;
                        doubleLargeArray5.setDouble(j56 + j, (DoubleFFT_1D.this.bk1l.getDouble(j2) * doubleLargeArray6.getDouble(j2)) - (DoubleFFT_1D.this.bk1l.getDouble(j3) * doubleLargeArray6.getDouble(j3)));
                    }
                }
            });
            i4++;
            i2 = i5;
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr2);
        } catch (InterruptedException e3) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e3);
        } catch (ExecutionException e4) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e4);
        }
    }

    private void bluestein_real_inverse2(double[] dArr, int i) {
        Class<DoubleFFT_1D> cls = DoubleFFT_1D.class;
        final double[] dArr2 = new double[(this.nBluestein * 2)];
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (numberOfThreads <= 1 || ((long) this.n) < CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
            for (int i2 = 0; i2 < this.n; i2++) {
                int i3 = i2 * 2;
                int i4 = i3 + 1;
                int i5 = i + i2;
                double d = dArr[i5];
                double[] dArr3 = this.bk1;
                dArr2[i3] = d * dArr3[i3];
                dArr2[i4] = dArr[i5] * dArr3[i4];
            }
            CommonUtils.cftbsub(this.nBluestein * 2, dArr2, 0, this.ip, this.nw, this.w);
            for (int i6 = 0; i6 < this.nBluestein; i6++) {
                int i7 = i6 * 2;
                int i8 = i7 + 1;
                double d2 = dArr2[i7];
                double[] dArr4 = this.bk2;
                double d3 = dArr4[i8];
                double d4 = dArr2[i8];
                double d5 = dArr4[i7];
                dArr2[i7] = (d2 * d5) + (d4 * d3);
                dArr2[i8] = ((-d2) * d3) + (d4 * d5);
            }
        } else {
            int i9 = (numberOfThreads < 4 || ((long) this.n) < CommonUtils.getThreadsBeginN_1D_FFT_4Threads()) ? 2 : 4;
            Future[] futureArr = new Future[i9];
            int i10 = this.n / i9;
            int i11 = 0;
            while (i11 < i9) {
                final int i12 = i11 * i10;
                final int i13 = i11 == i9 + -1 ? this.n : i12 + i10;
                final int i14 = i;
                final double[] dArr5 = dArr2;
                int i15 = i11;
                final double[] dArr6 = dArr;
                futureArr[i15] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i12; i < i13; i++) {
                            int i2 = i * 2;
                            int i3 = i2 + 1;
                            int i4 = i14 + i;
                            dArr5[i2] = dArr6[i4] * DoubleFFT_1D.this.bk1[i2];
                            dArr5[i3] = dArr6[i4] * DoubleFFT_1D.this.bk1[i3];
                        }
                    }
                });
                i11 = i15 + 1;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
            } catch (ExecutionException e2) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
            }
            CommonUtils.cftbsub(this.nBluestein * 2, dArr2, 0, this.ip, this.nw, this.w);
            int i16 = this.nBluestein / i9;
            int i17 = 0;
            while (i17 < i9) {
                final int i18 = i17 * i16;
                final int i19 = i17 == i9 + -1 ? this.nBluestein : i18 + i16;
                futureArr[i17] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i18; i < i19; i++) {
                            int i2 = i * 2;
                            int i3 = i2 + 1;
                            double d = ((-dArr2[i2]) * DoubleFFT_1D.this.bk2[i3]) + (dArr2[i3] * DoubleFFT_1D.this.bk2[i2]);
                            double[] dArr = dArr2;
                            dArr[i2] = (dArr[i2] * DoubleFFT_1D.this.bk2[i2]) + (dArr2[i3] * DoubleFFT_1D.this.bk2[i3]);
                            dArr2[i3] = d;
                        }
                    }
                });
                i17++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e3) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e4);
            }
        }
        CommonUtils.cftfsub(this.nBluestein * 2, dArr2, 0, this.ip, this.nw, this.w);
        int i20 = this.n;
        if (i20 % 2 == 0) {
            double[] dArr7 = this.bk1;
            dArr[i] = (dArr7[0] * dArr2[0]) - (dArr7[1] * dArr2[1]);
            dArr[i + 1] = (dArr7[i20] * dArr2[i20]) - (dArr7[i20 + 1] * dArr2[i20 + 1]);
            for (int i21 = 1; i21 < this.n / 2; i21++) {
                int i22 = i21 * 2;
                int i23 = i22 + 1;
                double[] dArr8 = this.bk1;
                dArr[i + i22] = (dArr8[i22] * dArr2[i22]) - (dArr8[i23] * dArr2[i23]);
                dArr[i + i23] = (dArr8[i23] * dArr2[i22]) + (dArr8[i22] * dArr2[i23]);
            }
            return;
        }
        double[] dArr9 = this.bk1;
        dArr[i] = (dArr9[0] * dArr2[0]) - (dArr9[1] * dArr2[1]);
        dArr[i + 1] = (dArr9[i20] * dArr2[i20 - 1]) + (dArr9[i20 - 1] * dArr2[i20]);
        int i24 = 1;
        while (true) {
            int i25 = this.n;
            if (i24 < (i25 - 1) / 2) {
                int i26 = i24 * 2;
                int i27 = i26 + 1;
                double[] dArr10 = this.bk1;
                dArr[i + i26] = (dArr10[i26] * dArr2[i26]) - (dArr10[i27] * dArr2[i27]);
                dArr[i + i27] = (dArr10[i27] * dArr2[i26]) + (dArr10[i26] * dArr2[i27]);
                i24++;
            } else {
                double[] dArr11 = this.bk1;
                dArr[(i + i25) - 1] = (dArr11[i25 - 1] * dArr2[i25 - 1]) - (dArr11[i25] * dArr2[i25]);
                return;
            }
        }
    }

    private void bluestein_real_inverse2(DoubleLargeArray doubleLargeArray, long j) {
        long j2;
        DoubleLargeArray doubleLargeArray2;
        DoubleLargeArray doubleLargeArray3 = doubleLargeArray;
        long j3 = j;
        Class<DoubleFFT_1D> cls = DoubleFFT_1D.class;
        DoubleLargeArray doubleLargeArray4 = new DoubleLargeArray(this.nBluesteinl * 2);
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (numberOfThreads <= 1 || this.nl <= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
            DoubleLargeArray doubleLargeArray5 = doubleLargeArray4;
            for (long j4 = 0; j4 < this.nl; j4++) {
                long j5 = j4 * 2;
                long j6 = j5 + 1;
                long j7 = j + j4;
                DoubleLargeArray doubleLargeArray6 = doubleLargeArray;
                DoubleLargeArray doubleLargeArray7 = doubleLargeArray5;
                doubleLargeArray7.setDouble(j5, doubleLargeArray6.getDouble(j7) * this.bk1l.getDouble(j5));
                doubleLargeArray7.setDouble(j6, doubleLargeArray6.getDouble(j7) * this.bk1l.getDouble(j6));
            }
            DoubleLargeArray doubleLargeArray8 = doubleLargeArray;
            j2 = j;
            DoubleLargeArray doubleLargeArray9 = doubleLargeArray5;
            doubleLargeArray2 = doubleLargeArray9;
            CommonUtils.cftbsub(this.nBluesteinl * 2, doubleLargeArray9, 0, this.ipl, this.nwl, this.wl);
            long j8 = 0;
            while (j8 < this.nBluesteinl) {
                long j9 = j8 * 2;
                long j10 = j9 + 1;
                double d = ((-doubleLargeArray2.getDouble(j9)) * this.bk2l.getDouble(j10)) + (doubleLargeArray2.getDouble(j10) * this.bk2l.getDouble(j9));
                doubleLargeArray2.setDouble(j9, (doubleLargeArray2.getDouble(j9) * this.bk2l.getDouble(j9)) + (doubleLargeArray2.getDouble(j10) * this.bk2l.getDouble(j10)));
                doubleLargeArray2.setDouble(j10, d);
                j8++;
                DoubleLargeArray doubleLargeArray10 = doubleLargeArray;
            }
        } else {
            int i = 4;
            if (numberOfThreads < 4 || this.nl <= CommonUtils.getThreadsBeginN_1D_FFT_4Threads()) {
                i = 2;
            }
            int i2 = i;
            Future[] futureArr = new Future[i2];
            long j11 = (long) i2;
            long j12 = this.nl / j11;
            int i3 = 0;
            while (i3 < i2) {
                long j13 = ((long) i3) * j12;
                Future[] futureArr2 = futureArr;
                final long j14 = j13;
                final long j15 = i3 == i2 + -1 ? this.nl : j13 + j12;
                final long j16 = j;
                int i4 = i2;
                final DoubleLargeArray doubleLargeArray11 = doubleLargeArray4;
                DoubleLargeArray doubleLargeArray12 = doubleLargeArray4;
                final DoubleLargeArray doubleLargeArray13 = doubleLargeArray;
                futureArr2[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (long j = j14; j < j15; j++) {
                            long j2 = 2 * j;
                            long j3 = j2 + 1;
                            long j4 = j16 + j;
                            doubleLargeArray11.setDouble(j2, doubleLargeArray13.getDouble(j4) * DoubleFFT_1D.this.bk1l.getDouble(j2));
                            doubleLargeArray11.setDouble(j3, doubleLargeArray13.getDouble(j4) * DoubleFFT_1D.this.bk1l.getDouble(j3));
                        }
                    }
                });
                i3++;
                i2 = i4;
                doubleLargeArray4 = doubleLargeArray12;
                futureArr = futureArr2;
            }
            Future[] futureArr3 = futureArr;
            int i5 = i2;
            DoubleLargeArray doubleLargeArray14 = doubleLargeArray4;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
            } catch (ExecutionException e2) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
            }
            CommonUtils.cftbsub(this.nBluesteinl * 2, doubleLargeArray14, 0, this.ipl, this.nwl, this.wl);
            long j17 = this.nBluesteinl / j11;
            int i6 = 0;
            while (i6 < i5) {
                final long j18 = ((long) i6) * j17;
                final long j19 = i6 == i5 + -1 ? this.nBluesteinl : j18 + j17;
                final DoubleLargeArray doubleLargeArray15 = doubleLargeArray14;
                futureArr3[i6] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (long j = j18; j < j19; j++) {
                            long j2 = 2 * j;
                            long j3 = j2 + 1;
                            double d = ((-doubleLargeArray15.getDouble(j2)) * DoubleFFT_1D.this.bk2l.getDouble(j3)) + (doubleLargeArray15.getDouble(j3) * DoubleFFT_1D.this.bk2l.getDouble(j2));
                            DoubleLargeArray doubleLargeArray = doubleLargeArray15;
                            doubleLargeArray.setDouble(j2, (doubleLargeArray.getDouble(j2) * DoubleFFT_1D.this.bk2l.getDouble(j2)) + (doubleLargeArray15.getDouble(j3) * DoubleFFT_1D.this.bk2l.getDouble(j3)));
                            doubleLargeArray15.setDouble(j3, d);
                        }
                    }
                });
                i6++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e3) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e4);
            }
            j2 = j;
            doubleLargeArray2 = doubleLargeArray14;
        }
        CommonUtils.cftfsub(this.nBluesteinl * 2, doubleLargeArray2, 0, this.ipl, this.nwl, this.wl);
        if (this.nl % 2 == 0) {
            long j20 = 1;
            DoubleLargeArray doubleLargeArray16 = doubleLargeArray;
            doubleLargeArray16.setDouble(j2, (this.bk1l.getDouble(0) * doubleLargeArray2.getDouble(0)) - (this.bk1l.getDouble(1) * doubleLargeArray2.getDouble(1)));
            doubleLargeArray16.setDouble(j2 + 1, (this.bk1l.getDouble(this.nl) * doubleLargeArray2.getDouble(this.nl)) - (this.bk1l.getDouble(this.nl + 1) * doubleLargeArray2.getDouble(this.nl + 1)));
            long j21 = 1;
            while (j21 < this.nl / 2) {
                long j22 = j21 * 2;
                long j23 = j22 + j20;
                long j24 = j;
                doubleLargeArray16.setDouble(j24 + j22, (this.bk1l.getDouble(j22) * doubleLargeArray2.getDouble(j22)) - (this.bk1l.getDouble(j23) * doubleLargeArray2.getDouble(j23)));
                doubleLargeArray16.setDouble(j24 + j23, (this.bk1l.getDouble(j23) * doubleLargeArray2.getDouble(j22)) + (this.bk1l.getDouble(j22) * doubleLargeArray2.getDouble(j23)));
                j21++;
                j20 = 1;
            }
            return;
        }
        DoubleLargeArray doubleLargeArray17 = doubleLargeArray;
        long j25 = j2;
        doubleLargeArray17.setDouble(j25, (this.bk1l.getDouble(0) * doubleLargeArray2.getDouble(0)) - (this.bk1l.getDouble(1) * doubleLargeArray2.getDouble(1)));
        doubleLargeArray17.setDouble(j25 + 1, (this.bk1l.getDouble(this.nl) * doubleLargeArray2.getDouble(this.nl - 1)) + (this.bk1l.getDouble(this.nl - 1) * doubleLargeArray2.getDouble(this.nl)));
        long j26 = 1;
        while (true) {
            long j27 = this.nl;
            if (j26 < (j27 - 1) / 2) {
                long j28 = j26 * 2;
                long j29 = j28 + 1;
                doubleLargeArray17.setDouble(j25 + j28, (this.bk1l.getDouble(j28) * doubleLargeArray2.getDouble(j28)) - (this.bk1l.getDouble(j29) * doubleLargeArray2.getDouble(j29)));
                doubleLargeArray17.setDouble(j25 + j29, (this.bk1l.getDouble(j29) * doubleLargeArray2.getDouble(j28)) + (this.bk1l.getDouble(j28) * doubleLargeArray2.getDouble(j29)));
                j26++;
            } else {
                doubleLargeArray17.setDouble((j25 + j27) - 1, (this.bk1l.getDouble(j27 - 1) * doubleLargeArray2.getDouble(this.nl - 1)) - (this.bk1l.getDouble(this.nl) * doubleLargeArray2.getDouble(this.nl)));
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void rfftf(double[] dArr, int i) {
        int i2 = this.n;
        if (i2 != 1) {
            double[] dArr2 = new double[i2];
            int i3 = i2 * 2;
            int i4 = (int) this.wtable_r[i3 + 1];
            int i5 = i3 - 1;
            int i6 = 1;
            int i7 = 1;
            while (i7 <= i4) {
                int i8 = (int) this.wtable_r[(i4 - i7) + 2 + i3];
                int i9 = i2 / i8;
                int i10 = this.n / i2;
                int i11 = i10 * i9;
                int i12 = i5 - ((i8 - 1) * i10);
                int i13 = 1 - i6;
                if (i8 != 2) {
                    if (i8 != 3) {
                        if (i8 != 4) {
                            if (i8 != 5) {
                                if (i10 == 1) {
                                    i13 = 1 - i13;
                                }
                                if (i13 == 0) {
                                    radfg(i10, i8, i9, i11, dArr, i, dArr2, 0, i12);
                                    i6 = 1;
                                } else {
                                    radfg(i10, i8, i9, i11, dArr2, 0, dArr, i, i12);
                                    i6 = 0;
                                }
                                i7++;
                                i2 = i9;
                                i5 = i12;
                            } else if (i13 == 0) {
                                radf5(i10, i9, dArr, i, dArr2, 0, i12);
                            } else {
                                radf5(i10, i9, dArr2, 0, dArr, i, i12);
                            }
                        } else if (i13 == 0) {
                            radf4(i10, i9, dArr, i, dArr2, 0, i12);
                        } else {
                            radf4(i10, i9, dArr2, 0, dArr, i, i12);
                        }
                    } else if (i13 == 0) {
                        radf3(i10, i9, dArr, i, dArr2, 0, i12);
                    } else {
                        radf3(i10, i9, dArr2, 0, dArr, i, i12);
                    }
                } else if (i13 == 0) {
                    radf2(i10, i9, dArr, i, dArr2, 0, i12);
                } else {
                    radf2(i10, i9, dArr2, 0, dArr, i, i12);
                }
                i6 = i13;
                i7++;
                i2 = i9;
                i5 = i12;
            }
            if (i6 != 1) {
                System.arraycopy(dArr2, 0, dArr, i, this.n);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void rfftf(DoubleLargeArray doubleLargeArray, long j) {
        DoubleLargeArray doubleLargeArray2;
        long j2;
        DoubleFFT_1D doubleFFT_1D = this;
        if (doubleFFT_1D.nl != 1) {
            DoubleLargeArray doubleLargeArray3 = new DoubleLargeArray(doubleFFT_1D.nl);
            long j3 = doubleFFT_1D.nl * 2;
            long j4 = (long) doubleFFT_1D.wtable_rl.getDouble(j3 + 1);
            long j5 = doubleFFT_1D.nl;
            long j6 = j3 - 1;
            long j7 = 1;
            long j8 = 1;
            while (j8 <= j4) {
                int i = (int) doubleFFT_1D.wtable_rl.getDouble((j4 - j8) + 2 + j3);
                long j9 = (long) i;
                long j10 = j5 / j9;
                long j11 = doubleFFT_1D.nl / j5;
                long j12 = j11 * j10;
                long j13 = j6 - (((long) (i - 1)) * j11);
                long j14 = 1 - j7;
                if (i == 2) {
                    j2 = j4;
                    doubleLargeArray2 = doubleLargeArray3;
                    if (j14 == 0) {
                        radf2(j11, j10, doubleLargeArray, j, doubleLargeArray2, 0, j13);
                    } else {
                        radf2(j11, j10, doubleLargeArray2, 0, doubleLargeArray, j, j13);
                    }
                } else if (i == 3) {
                    j2 = j4;
                    doubleLargeArray2 = doubleLargeArray3;
                    if (j14 == 0) {
                        radf3(j11, j10, doubleLargeArray, j, doubleLargeArray2, 0, j13);
                    } else {
                        radf3(j11, j10, doubleLargeArray2, 0, doubleLargeArray, j, j13);
                    }
                } else if (i == 4) {
                    j2 = j4;
                    doubleLargeArray2 = doubleLargeArray3;
                    if (j14 == 0) {
                        radf4(j11, j10, doubleLargeArray, j, doubleLargeArray2, 0, j13);
                    } else {
                        radf4(j11, j10, doubleLargeArray2, 0, doubleLargeArray, j, j13);
                    }
                } else if (i != 5) {
                    if (j11 == 1) {
                        j14 = 1 - j14;
                    }
                    if (j14 == 0) {
                        j2 = j4;
                        doubleLargeArray2 = doubleLargeArray3;
                        radfg(j11, j9, j10, j12, doubleLargeArray, j, doubleLargeArray3, 0, j13);
                        j7 = 1;
                    } else {
                        j2 = j4;
                        doubleLargeArray2 = doubleLargeArray3;
                        radfg(j11, j9, j10, j12, doubleLargeArray2, 0, doubleLargeArray, j, j13);
                        j7 = 0;
                    }
                    j8++;
                    doubleFFT_1D = this;
                    j5 = j10;
                    j6 = j13;
                    j4 = j2;
                    doubleLargeArray3 = doubleLargeArray2;
                } else {
                    j2 = j4;
                    doubleLargeArray2 = doubleLargeArray3;
                    if (j14 == 0) {
                        radf5(j11, j10, doubleLargeArray, j, doubleLargeArray2, 0, j13);
                    } else {
                        radf5(j11, j10, doubleLargeArray2, 0, doubleLargeArray, j, j13);
                    }
                }
                j7 = j14;
                j8++;
                doubleFFT_1D = this;
                j5 = j10;
                j6 = j13;
                j4 = j2;
                doubleLargeArray3 = doubleLargeArray2;
            }
            DoubleLargeArray doubleLargeArray4 = doubleLargeArray3;
            if (j7 != 1) {
                LargeArrayUtils.arraycopy(doubleLargeArray4, 0, doubleLargeArray, j, this.nl);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void rfftb(double[] dArr, int i) {
        int i2;
        int i3;
        int i4 = this.n;
        if (i4 != 1) {
            double[] dArr2 = new double[i4];
            int i5 = i4 * 2;
            int i6 = (int) this.wtable_r[i5 + 1];
            int i7 = i4;
            int i8 = 1;
            int i9 = 1;
            int i10 = 0;
            while (i8 <= i6) {
                int i11 = i8 + 1;
                int i12 = (int) this.wtable_r[i11 + i5];
                int i13 = i12 * i9;
                int i14 = this.n / i13;
                int i15 = i14 * i9;
                if (i12 == 2) {
                    i2 = i12;
                    if (i10 == 0) {
                        radb2(i14, i9, dArr, i, dArr2, 0, i7);
                    } else {
                        radb2(i14, i9, dArr2, 0, dArr, i, i7);
                    }
                } else if (i12 == 3) {
                    i2 = i12;
                    if (i10 == 0) {
                        radb3(i14, i9, dArr, i, dArr2, 0, i7);
                    } else {
                        radb3(i14, i9, dArr2, 0, dArr, i, i7);
                    }
                } else if (i12 == 4) {
                    i2 = i12;
                    if (i10 == 0) {
                        radb4(i14, i9, dArr, i, dArr2, 0, i7);
                    } else {
                        radb4(i14, i9, dArr2, 0, dArr, i, i7);
                    }
                } else if (i12 != 5) {
                    if (i10 == 0) {
                        i3 = i14;
                        i2 = i12;
                        radbg(i14, i12, i9, i15, dArr, i, dArr2, 0, i7);
                    } else {
                        i3 = i14;
                        i2 = i12;
                        radbg(i3, i2, i9, i15, dArr2, 0, dArr, i, i7);
                    }
                    i14 = i3;
                    if (i14 != 1) {
                        i7 += (i2 - 1) * i14;
                        i8 = i11;
                        i9 = i13;
                    }
                } else {
                    i2 = i12;
                    if (i10 == 0) {
                        radb5(i14, i9, dArr, i, dArr2, 0, i7);
                    } else {
                        radb5(i14, i9, dArr2, 0, dArr, i, i7);
                    }
                }
                i10 = 1 - i10;
                i7 += (i2 - 1) * i14;
                i8 = i11;
                i9 = i13;
            }
            if (i10 != 0) {
                System.arraycopy(dArr2, 0, dArr, i, this.n);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void rfftb(DoubleLargeArray doubleLargeArray, long j) {
        DoubleLargeArray doubleLargeArray2;
        long j2;
        int i;
        DoubleFFT_1D doubleFFT_1D = this;
        if (doubleFFT_1D.nl != 1) {
            DoubleLargeArray doubleLargeArray3 = new DoubleLargeArray(doubleFFT_1D.nl);
            long j3 = doubleFFT_1D.nl * 2;
            long j4 = (long) doubleFFT_1D.wtable_rl.getDouble(j3 + 1);
            long j5 = doubleFFT_1D.nl;
            long j6 = 1;
            long j7 = 1;
            long j8 = 0;
            while (j6 <= j4) {
                long j9 = j6 + 1;
                int i2 = (int) doubleFFT_1D.wtable_rl.getDouble(j9 + j3);
                long j10 = (long) i2;
                long j11 = j10 * j7;
                long j12 = doubleFFT_1D.nl / j11;
                long j13 = j12 * j7;
                if (i2 == 2) {
                    i = i2;
                    j2 = j4;
                    doubleLargeArray2 = doubleLargeArray3;
                    if (j8 == 0) {
                        radb2(j12, j7, doubleLargeArray, j, doubleLargeArray2, 0, j5);
                    } else {
                        radb2(j12, j7, doubleLargeArray2, 0, doubleLargeArray, j, j5);
                    }
                } else if (i2 == 3) {
                    i = i2;
                    j2 = j4;
                    doubleLargeArray2 = doubleLargeArray3;
                    if (j8 == 0) {
                        radb3(j12, j7, doubleLargeArray, j, doubleLargeArray2, 0, j5);
                    } else {
                        radb3(j12, j7, doubleLargeArray2, 0, doubleLargeArray, j, j5);
                    }
                } else if (i2 == 4) {
                    i = i2;
                    j2 = j4;
                    doubleLargeArray2 = doubleLargeArray3;
                    if (j8 == 0) {
                        radb4(j12, j7, doubleLargeArray, j, doubleLargeArray2, 0, j5);
                    } else {
                        radb4(j12, j7, doubleLargeArray2, 0, doubleLargeArray, j, j5);
                    }
                } else if (i2 != 5) {
                    if (j8 == 0) {
                        i = i2;
                        j2 = j4;
                        doubleLargeArray2 = doubleLargeArray3;
                        radbg(j12, j10, j7, j13, doubleLargeArray, j, doubleLargeArray3, 0, j5);
                    } else {
                        i = i2;
                        j2 = j4;
                        doubleLargeArray2 = doubleLargeArray3;
                        radbg(j12, j10, j7, j13, doubleLargeArray2, 0, doubleLargeArray, j, j5);
                    }
                    if (j12 != 1) {
                        j5 += ((long) (i - 1)) * j12;
                        doubleFFT_1D = this;
                        j6 = j9;
                        j7 = j11;
                        j4 = j2;
                        doubleLargeArray3 = doubleLargeArray2;
                    }
                } else {
                    i = i2;
                    j2 = j4;
                    doubleLargeArray2 = doubleLargeArray3;
                    if (j8 == 0) {
                        radb5(j12, j7, doubleLargeArray, j, doubleLargeArray2, 0, j5);
                    } else {
                        radb5(j12, j7, doubleLargeArray2, 0, doubleLargeArray, j, j5);
                    }
                }
                j8 = 1 - j8;
                j5 += ((long) (i - 1)) * j12;
                doubleFFT_1D = this;
                j6 = j9;
                j7 = j11;
                j4 = j2;
                doubleLargeArray3 = doubleLargeArray2;
            }
            DoubleLargeArray doubleLargeArray4 = doubleLargeArray3;
            if (j8 != 0) {
                LargeArrayUtils.arraycopy(doubleLargeArray4, 0, doubleLargeArray, j, this.nl);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void radf2(int i, int i2, double[] dArr, int i3, double[] dArr2, int i4, int i5) {
        int i6 = i;
        int i7 = i2;
        int i8 = i7 * i6;
        int i9 = i6 * 2;
        for (int i10 = 0; i10 < i7; i10++) {
            int i11 = i4 + (i10 * i9);
            int i12 = i3 + (i10 * i6);
            double d = dArr[i12];
            double d2 = dArr[i12 + i8];
            dArr2[i11] = d + d2;
            dArr2[(i11 + i9) - 1] = d - d2;
        }
        int i13 = 2;
        if (i6 >= 2) {
            if (i6 != 2) {
                int i14 = 0;
                while (i14 < i7) {
                    i9 = i14 * i6;
                    int i15 = i9 * 2;
                    int i16 = i15 + i6;
                    int i17 = i9 + i8;
                    for (int i18 = i13; i18 < i6; i18 += 2) {
                        int i19 = (i18 - 1) + i5;
                        int i20 = i4 + i18 + i15;
                        int i21 = i4 + (i6 - i18) + i16;
                        int i22 = i3 + i18;
                        int i23 = i22 + i9;
                        int i24 = i22 + i17;
                        double d3 = dArr[i23 - 1];
                        double d4 = dArr[i23];
                        double d5 = dArr[i24 - 1];
                        double d6 = dArr[i24];
                        double[] dArr3 = this.wtable_r;
                        double d7 = dArr3[i19 - 1];
                        double d8 = dArr3[i19];
                        double d9 = (d7 * d5) + (d8 * d6);
                        double d10 = (d7 * d6) - (d8 * d5);
                        dArr2[i20] = d4 + d10;
                        dArr2[i20 - 1] = d3 + d9;
                        dArr2[i21] = d10 - d4;
                        dArr2[i21 - 1] = d3 - d9;
                    }
                    i14++;
                    i13 = 2;
                }
                if (i6 % 2 != 1) {
                    i13 = 2;
                } else {
                    return;
                }
            }
            int i25 = i9 * i13;
            for (int i26 = 0; i26 < i7; i26++) {
                int i27 = i4 + i25 + i6;
                int i28 = ((i3 + i6) - 1) + (i26 * i6);
                dArr2[i27] = -dArr[i28 + i8];
                dArr2[i27 - 1] = dArr[i28];
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void radf2(long j, long j2, DoubleLargeArray doubleLargeArray, long j3, DoubleLargeArray doubleLargeArray2, long j4, long j5) {
        long j6;
        long j7;
        DoubleLargeArray doubleLargeArray3 = doubleLargeArray;
        DoubleLargeArray doubleLargeArray4 = doubleLargeArray2;
        long j8 = j2 * j;
        long j9 = 2;
        long j10 = j * 2;
        long j11 = 0;
        while (j11 < j2) {
            long j12 = j4 + (j11 * j10);
            long j13 = (j12 + j10) - 1;
            long j14 = j3 + (j11 * j);
            long j15 = j10;
            double d = doubleLargeArray3.getDouble(j14);
            double d2 = doubleLargeArray3.getDouble(j14 + j8);
            doubleLargeArray4.setDouble(j12, d + d2);
            doubleLargeArray4.setDouble(j13, d - d2);
            j11++;
            doubleLargeArray3 = doubleLargeArray;
            j10 = j15;
            j9 = 2;
        }
        long j16 = j9;
        long j17 = j10;
        int i = (j > j16 ? 1 : (j == j16 ? 0 : -1));
        if (i >= 0) {
            if (i != 0) {
                j7 = j17;
                long j18 = 0;
                while (j18 < j2) {
                    j7 = j18 * j;
                    long j19 = j7 * j16;
                    long j20 = j19 + j;
                    long j21 = j7 + j8;
                    long j22 = 2;
                    while (j22 < j) {
                        long j23 = j8;
                        long j24 = (j22 - 1) + j5;
                        long j25 = j18;
                        long j26 = j4 + j22 + j19;
                        long j27 = j19;
                        long j28 = j4 + (j - j22) + j20;
                        long j29 = j3 + j22;
                        long j30 = j20;
                        long j31 = j29 + j7;
                        long j32 = j7;
                        long j33 = j29 + j21;
                        long j34 = j21;
                        DoubleLargeArray doubleLargeArray5 = doubleLargeArray;
                        double d3 = doubleLargeArray5.getDouble(j31 - 1);
                        double d4 = doubleLargeArray5.getDouble(j31);
                        long j35 = j22;
                        double d5 = doubleLargeArray5.getDouble(j33 - 1);
                        double d6 = doubleLargeArray5.getDouble(j33);
                        long j36 = j28;
                        double d7 = d3;
                        double d8 = this.wtable_rl.getDouble(j24 - 1);
                        double d9 = this.wtable_rl.getDouble(j24);
                        double d10 = (d8 * d5) + (d9 * d6);
                        double d11 = (d8 * d6) - (d9 * d5);
                        doubleLargeArray4.setDouble(j26, d4 + d11);
                        doubleLargeArray4.setDouble(j26 - 1, d7 + d10);
                        double d12 = d11 - d4;
                        long j37 = j36;
                        doubleLargeArray4.setDouble(j37, d12);
                        doubleLargeArray4.setDouble(j37 - 1, d7 - d10);
                        j22 = j35 + 2;
                        j19 = j27;
                        j8 = j23;
                        j18 = j25;
                        j20 = j30;
                        j7 = j32;
                        j21 = j34;
                    }
                    long j38 = j7;
                    j16 = 2;
                    j18++;
                    j8 = j8;
                }
                j6 = j8;
                if (j % j16 == 1) {
                    return;
                }
            } else {
                j6 = j8;
                j7 = j17;
            }
            long j39 = j7 * j16;
            long j40 = 0;
            while (j40 < j2) {
                long j41 = j4 + j39 + j;
                long j42 = ((j3 + j) - 1) + (j40 * j);
                DoubleLargeArray doubleLargeArray6 = doubleLargeArray;
                doubleLargeArray4.setDouble(j41, -doubleLargeArray6.getDouble(j42 + j6));
                doubleLargeArray4.setDouble(j41 - 1, doubleLargeArray6.getDouble(j42));
                j40++;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void radb2(int i, int i2, double[] dArr, int i3, double[] dArr2, int i4, int i5) {
        int i6 = i;
        int i7 = i2;
        int i8 = i7 * i6;
        for (int i9 = 0; i9 < i7; i9++) {
            int i10 = i9 * i6;
            int i11 = i10 * 2;
            int i12 = i11 + i6;
            int i13 = i4 + i10;
            double d = dArr[i3 + i11];
            double d2 = dArr[((i3 + i6) - 1) + i12];
            dArr2[i13] = d + d2;
            dArr2[i13 + i8] = d - d2;
        }
        int i14 = 2;
        if (i6 >= 2) {
            if (i6 != 2) {
                int i15 = 0;
                while (i15 < i7) {
                    int i16 = i15 * i6;
                    int i17 = i16 * 2;
                    int i18 = i17 + i6;
                    int i19 = i16 + i8;
                    for (int i20 = i14; i20 < i6; i20 += 2) {
                        int i21 = (i20 - 1) + i5;
                        int i22 = i4 + i20;
                        double[] dArr3 = this.wtable_r;
                        double d3 = dArr3[i21 - 1];
                        double d4 = dArr3[i21];
                        int i23 = i3 + i20 + i17;
                        int i24 = i3 + (i6 - i20) + i18;
                        int i25 = i22 + i16;
                        int i26 = i22 + i19;
                        double d5 = dArr[i23 - 1];
                        double d6 = dArr[i24 - 1];
                        double d7 = d5 - d6;
                        double d8 = dArr[i23];
                        double d9 = dArr[i24];
                        double d10 = d8 + d9;
                        dArr2[i25 - 1] = d5 + d6;
                        dArr2[i25] = d8 - d9;
                        dArr2[i26 - 1] = (d3 * d7) - (d4 * d10);
                        dArr2[i26] = (d3 * d10) + (d4 * d7);
                    }
                    i15++;
                    i14 = 2;
                }
                if (i6 % 2 == 1) {
                    return;
                }
            }
            for (int i27 = 0; i27 < i7; i27++) {
                int i28 = i27 * i6;
                int i29 = ((i4 + i6) - 1) + i28;
                int i30 = i3 + (i28 * 2) + i6;
                dArr2[i29] = dArr[i30 - 1] * 2.0d;
                dArr2[i29 + i8] = dArr[i30] * -2.0d;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void radb2(long j, long j2, DoubleLargeArray doubleLargeArray, long j3, DoubleLargeArray doubleLargeArray2, long j4, long j5) {
        long j6;
        long j7;
        long j8;
        long j9;
        DoubleFFT_1D doubleFFT_1D = this;
        DoubleLargeArray doubleLargeArray3 = doubleLargeArray;
        DoubleLargeArray doubleLargeArray4 = doubleLargeArray2;
        long j10 = j2 * j;
        long j11 = 0;
        while (true) {
            j6 = 2;
            if (j11 >= j2) {
                break;
            }
            long j12 = j11 * j;
            long j13 = 2 * j12;
            long j14 = j4 + j12;
            double d = doubleLargeArray3.getDouble(j3 + j13);
            double d2 = doubleLargeArray3.getDouble(((j3 + j) - 1) + j13 + j);
            doubleLargeArray4.setDouble(j14, d + d2);
            doubleLargeArray4.setDouble(j14 + j10, d - d2);
            j11++;
        }
        int i = (j > 2 ? 1 : (j == 2 ? 0 : -1));
        if (i >= 0) {
            if (i != 0) {
                long j15 = 0;
                while (j15 < j2) {
                    long j16 = j15 * j;
                    long j17 = j16 * j6;
                    long j18 = j17 + j;
                    long j19 = j16 + j10;
                    long j20 = j6;
                    while (j20 < j) {
                        long j21 = (j20 - 1) + j5;
                        long j22 = j4 + j20;
                        long j23 = j10;
                        double d3 = doubleFFT_1D.wtable_rl.getDouble(j21 - 1);
                        long j24 = j15;
                        long j25 = j3 + j20 + j17;
                        long j26 = j3 + (j - j20) + j18;
                        long j27 = j22 + j16;
                        long j28 = j16;
                        long j29 = j22 + j19;
                        double d4 = doubleFFT_1D.wtable_rl.getDouble(j21);
                        long j30 = j25 - 1;
                        double d5 = d3;
                        long j31 = j26 - 1;
                        double d6 = doubleLargeArray3.getDouble(j30) - doubleLargeArray3.getDouble(j31);
                        double d7 = doubleLargeArray3.getDouble(j25) + doubleLargeArray3.getDouble(j26);
                        double d8 = doubleLargeArray3.getDouble(j25);
                        double d9 = doubleLargeArray3.getDouble(j30);
                        double d10 = doubleLargeArray3.getDouble(j26);
                        doubleLargeArray4.setDouble(j27 - 1, d9 + doubleLargeArray3.getDouble(j31));
                        doubleLargeArray4.setDouble(j27, d8 - d10);
                        doubleLargeArray4.setDouble(j29 - 1, (d5 * d6) - (d4 * d7));
                        doubleLargeArray4.setDouble(j29, (d5 * d7) + (d4 * d6));
                        j20 += 2;
                        j6 = 2;
                        j18 = j18;
                        j17 = j17;
                        j10 = j23;
                        j15 = j24;
                        j16 = j28;
                        doubleFFT_1D = this;
                        doubleLargeArray3 = doubleLargeArray;
                    }
                    long j32 = j10;
                    long j33 = j6;
                    j15++;
                    doubleFFT_1D = this;
                    doubleLargeArray3 = doubleLargeArray;
                }
                j7 = j10;
                j9 = j6;
                j8 = 1;
                if (j % j9 == 1) {
                    return;
                }
            } else {
                j7 = j10;
                j9 = 2;
                j8 = 1;
            }
            for (long j34 = 0; j34 < j2; j34 += j8) {
                long j35 = j34 * j;
                long j36 = ((j4 + j) - j8) + j35;
                long j37 = j3 + (j35 * j9) + j;
                DoubleLargeArray doubleLargeArray5 = doubleLargeArray;
                doubleLargeArray4.setDouble(j36, doubleLargeArray5.getDouble(j37 - j8) * 2.0d);
                doubleLargeArray4.setDouble(j36 + j7, doubleLargeArray5.getDouble(j37) * -2.0d);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void radf3(int i, int i2, double[] dArr, int i3, double[] dArr2, int i4, int i5) {
        long j;
        int i6 = i;
        int i7 = i2;
        int i8 = i5 + i6;
        int i9 = i7 * i6;
        int i10 = 0;
        int i11 = 0;
        while (true) {
            j = 4605975682916830379L;
            if (i11 >= i7) {
                break;
            }
            int i12 = i11 * i6;
            int i13 = ((i11 * 3) + 1) * i6;
            int i14 = i3 + i12;
            int i15 = (i9 * 2) + i14;
            double d = dArr[i14];
            double d2 = dArr[i14 + i9];
            double d3 = dArr[i15];
            double d4 = d2 + d3;
            dArr2[i4 + (i12 * 3)] = d + d4;
            dArr2[i4 + i13 + i6] = (d3 - d2) * 0.8660254037844387d;
            dArr2[((i4 + i6) - 1) + i13] = d + (d4 * -0.5d);
            i11++;
        }
        if (i6 != 1) {
            while (i10 < i7) {
                int i16 = i10 * i6;
                int i17 = i16 * 3;
                int i18 = i16 + i9;
                int i19 = i18 + i9;
                int i20 = i17 + i6;
                int i21 = i20 + i6;
                int i22 = 2;
                while (i22 < i6) {
                    int i23 = i22 - 1;
                    int i24 = i23 + i5;
                    int i25 = i23 + i8;
                    double[] dArr3 = this.wtable_r;
                    double d5 = dArr3[i24 - 1];
                    double d6 = dArr3[i24];
                    double d7 = dArr3[i25 - 1];
                    double d8 = dArr3[i25];
                    int i26 = i3 + i22;
                    int i27 = i4 + i22;
                    int i28 = i26 + i16;
                    int i29 = i26 + i18;
                    int i30 = i26 + i19;
                    double d9 = dArr[i28 - 1];
                    double d10 = dArr[i28];
                    double d11 = dArr[i29 - 1];
                    double d12 = dArr[i29];
                    double d13 = dArr[i30 - 1];
                    double d14 = dArr[i30];
                    double d15 = (d5 * d11) + (d6 * d12);
                    double d16 = (d5 * d12) - (d6 * d11);
                    double d17 = (d7 * d13) + (d8 * d14);
                    double d18 = (d7 * d14) - (d8 * d13);
                    double d19 = d15 + d17;
                    double d20 = d16 + d18;
                    double d21 = d9 + (d19 * -0.5d);
                    double d22 = d10 + (d20 * -0.5d);
                    double d23 = (d16 - d18) * 0.8660254037844387d;
                    double d24 = (d17 - d15) * 0.8660254037844387d;
                    int i31 = i27 + i17;
                    int i32 = i4 + (i6 - i22) + i20;
                    int i33 = i27 + i21;
                    dArr2[i31 - 1] = d9 + d19;
                    dArr2[i31] = d10 + d20;
                    dArr2[i32 - 1] = d21 - d23;
                    dArr2[i32] = d24 - d22;
                    dArr2[i33 - 1] = d21 + d23;
                    dArr2[i33] = d22 + d24;
                    i22 += 2;
                    j = 4605975682916830379L;
                }
                long j2 = j;
                i10++;
                j = j2;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void radf3(long j, long j2, DoubleLargeArray doubleLargeArray, long j3, DoubleLargeArray doubleLargeArray2, long j4, long j5) {
        DoubleLargeArray doubleLargeArray3 = doubleLargeArray;
        DoubleLargeArray doubleLargeArray4 = doubleLargeArray2;
        long j6 = j5 + j;
        long j7 = j2 * j;
        long j8 = 0;
        while (j8 < j2) {
            long j9 = j8 * j;
            long j10 = ((j8 * 3) + 1) * j;
            long j11 = j3 + j9;
            long j12 = j11 + j7;
            long j13 = j11 + (2 * j7);
            double d = doubleLargeArray3.getDouble(j11);
            double d2 = doubleLargeArray3.getDouble(j12);
            double d3 = doubleLargeArray3.getDouble(j13);
            double d4 = d2 + d3;
            doubleLargeArray4.setDouble(j4 + (j9 * 3), d + d4);
            doubleLargeArray4.setDouble(j4 + j10 + j, (d3 - d2) * 0.8660254037844387d);
            doubleLargeArray4.setDouble(((j4 + j) - 1) + j10, d + (d4 * -0.5d));
            j8++;
            doubleLargeArray3 = doubleLargeArray;
            j6 = j6;
        }
        long j14 = j6;
        if (j != 1) {
            long j15 = 0;
            while (j15 < j2) {
                long j16 = j15 * j;
                long j17 = j16 * 3;
                long j18 = j16 + j7;
                long j19 = j18 + j7;
                long j20 = j17 + j;
                long j21 = j20 + j;
                long j22 = 2;
                while (j22 < j) {
                    long j23 = j22 - 1;
                    long j24 = j7;
                    long j25 = j23 + j5;
                    long j26 = j15;
                    long j27 = j23 + j14;
                    long j28 = j20;
                    double d5 = this.wtable_rl.getDouble(j25 - 1);
                    double d6 = this.wtable_rl.getDouble(j25);
                    long j29 = j17;
                    double d7 = this.wtable_rl.getDouble(j27 - 1);
                    long j30 = j3 + j22;
                    long j31 = j4 + j22;
                    double d8 = this.wtable_rl.getDouble(j27);
                    long j32 = j30 + j16;
                    long j33 = j16;
                    long j34 = j30 + j18;
                    long j35 = j18;
                    long j36 = j30 + j19;
                    long j37 = j19;
                    DoubleLargeArray doubleLargeArray5 = doubleLargeArray;
                    double d9 = doubleLargeArray5.getDouble(j32 - 1);
                    double d10 = doubleLargeArray5.getDouble(j32);
                    double d11 = doubleLargeArray5.getDouble(j34 - 1);
                    double d12 = doubleLargeArray5.getDouble(j34);
                    double d13 = d9;
                    double d14 = doubleLargeArray5.getDouble(j36 - 1);
                    double d15 = doubleLargeArray5.getDouble(j36);
                    double d16 = (d5 * d11) + (d6 * d12);
                    double d17 = (d5 * d12) - (d6 * d11);
                    double d18 = (d7 * d14) + (d8 * d15);
                    double d19 = (d7 * d15) - (d8 * d14);
                    double d20 = d16 + d18;
                    double d21 = d17 + d19;
                    double d22 = d13 + (d20 * -0.5d);
                    double d23 = d10 + (d21 * -0.5d);
                    double d24 = (d17 - d19) * 0.8660254037844387d;
                    long j38 = j31 + j29;
                    long j39 = j4 + (j - j22) + j28;
                    double d25 = (d18 - d16) * 0.8660254037844387d;
                    long j40 = j31 + j21;
                    DoubleLargeArray doubleLargeArray6 = doubleLargeArray2;
                    doubleLargeArray6.setDouble(j38 - 1, d13 + d20);
                    doubleLargeArray6.setDouble(j38, d10 + d21);
                    doubleLargeArray6.setDouble(j39 - 1, d22 - d24);
                    doubleLargeArray6.setDouble(j39, d25 - d23);
                    doubleLargeArray6.setDouble(j40 - 1, d22 + d24);
                    doubleLargeArray6.setDouble(j40, d23 + d25);
                    j22 += 2;
                    doubleLargeArray4 = doubleLargeArray6;
                    j20 = j28;
                    j7 = j24;
                    j15 = j26;
                    j17 = j29;
                    j19 = j37;
                    j16 = j33;
                    j18 = j35;
                }
                DoubleLargeArray doubleLargeArray7 = doubleLargeArray4;
                long j41 = j7;
                j15++;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void radb3(int i, int i2, double[] dArr, int i3, double[] dArr2, int i4, int i5) {
        double d;
        int i6 = i;
        int i7 = i2;
        int i8 = i5 + i6;
        int i9 = 0;
        int i10 = 0;
        while (true) {
            d = -0.5d;
            if (i10 >= i7) {
                break;
            }
            int i11 = i10 * i6;
            int i12 = i3 + (i11 * 3);
            int i13 = (i6 * 2) + i12;
            double d2 = dArr[i12];
            double d3 = dArr[i13 - 1] * 2.0d;
            double d4 = (-0.5d * d3) + d2;
            double d5 = dArr[i13] * 1.7320508075688774d;
            dArr2[i4 + i11] = d2 + d3;
            dArr2[i4 + ((i10 + i7) * i6)] = d4 - d5;
            dArr2[i4 + (((i7 * 2) + i10) * i6)] = d4 + d5;
            i10++;
        }
        if (i6 != 1) {
            int i14 = i7 * i6;
            while (i9 < i7) {
                int i15 = i9 * i6;
                int i16 = i15 * 3;
                int i17 = i16 + i6;
                int i18 = i17 + i6;
                int i19 = i15 + i14;
                int i20 = i19 + i14;
                int i21 = 2;
                while (i21 < i6) {
                    int i22 = i3 + i21;
                    int i23 = i4 + i21;
                    int i24 = i22 + i16;
                    int i25 = i22 + i18;
                    int i26 = i3 + (i6 - i21) + i17;
                    double d6 = dArr[i24 - 1];
                    double d7 = dArr[i24];
                    double d8 = dArr[i25 - 1];
                    double d9 = dArr[i25];
                    double d10 = dArr[i26 - 1];
                    double d11 = dArr[i26];
                    double d12 = d8 + d10;
                    double d13 = d6 + (d12 * d);
                    double d14 = d9 - d11;
                    double d15 = d7 + (d14 * d);
                    double d16 = (d8 - d10) * 0.8660254037844387d;
                    double d17 = (d9 + d11) * 0.8660254037844387d;
                    double d18 = d13 - d17;
                    double d19 = d13 + d17;
                    double d20 = d15 + d16;
                    double d21 = d15 - d16;
                    int i27 = i21 - 1;
                    int i28 = i27 + i5;
                    int i29 = i27 + i8;
                    double[] dArr3 = this.wtable_r;
                    double d22 = dArr3[i28 - 1];
                    double d23 = dArr3[i28];
                    double d24 = dArr3[i29 - 1];
                    double d25 = dArr3[i29];
                    int i30 = i23 + i15;
                    int i31 = i23 + i19;
                    int i32 = i23 + i20;
                    dArr2[i30 - 1] = d6 + d12;
                    dArr2[i30] = d7 + d14;
                    dArr2[i31 - 1] = (d22 * d18) - (d23 * d20);
                    dArr2[i31] = (d22 * d20) + (d23 * d18);
                    dArr2[i32 - 1] = (d24 * d19) - (d25 * d21);
                    dArr2[i32] = (d24 * d21) + (d25 * d19);
                    i21 += 2;
                    d = -0.5d;
                }
                i9++;
                d = -0.5d;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void radb3(long j, long j2, DoubleLargeArray doubleLargeArray, long j3, DoubleLargeArray doubleLargeArray2, long j4, long j5) {
        long j6;
        long j7;
        DoubleFFT_1D doubleFFT_1D = this;
        DoubleLargeArray doubleLargeArray3 = doubleLargeArray;
        DoubleLargeArray doubleLargeArray4 = doubleLargeArray2;
        long j8 = j5 + j;
        long j9 = 0;
        while (true) {
            j6 = 3;
            j7 = -4620693217682128896L;
            if (j9 >= j2) {
                break;
            }
            long j10 = j9 * j;
            long j11 = j3 + (3 * j10);
            long j12 = j11 + (j * 2);
            double d = doubleLargeArray3.getDouble(j11);
            double d2 = doubleLargeArray3.getDouble(j12 - 1) * 2.0d;
            double d3 = (-0.5d * d2) + d;
            double d4 = doubleLargeArray3.getDouble(j12) * 1.7320508075688774d;
            doubleLargeArray4.setDouble(j4 + j10, d + d2);
            doubleLargeArray4.setDouble(j4 + ((j9 + j2) * j), d3 - d4);
            doubleLargeArray4.setDouble(j4 + (((2 * j2) + j9) * j), d3 + d4);
            j9++;
            j8 = j8;
        }
        long j13 = j8;
        if (j != 1) {
            long j14 = j2 * j;
            long j15 = 0;
            while (j15 < j2) {
                long j16 = j15 * j;
                long j17 = j16 * j6;
                long j18 = j17 + j;
                long j19 = j18 + j;
                long j20 = j16 + j14;
                long j21 = j20 + j14;
                long j22 = 2;
                while (j22 < j) {
                    long j23 = j3 + j22;
                    long j24 = j4 + j22;
                    long j25 = j23 + j17;
                    long j26 = j23 + j19;
                    long j27 = j14;
                    long j28 = j3 + (j - j22) + j18;
                    long j29 = j17;
                    double d5 = doubleLargeArray3.getDouble(j25 - 1);
                    double d6 = doubleLargeArray3.getDouble(j25);
                    long j30 = j15;
                    double d7 = doubleLargeArray3.getDouble(j26 - 1);
                    double d8 = doubleLargeArray3.getDouble(j26);
                    long j31 = j16;
                    double d9 = doubleLargeArray3.getDouble(j28 - 1);
                    double d10 = doubleLargeArray3.getDouble(j28);
                    double d11 = d7 + d9;
                    double d12 = d5 + (d11 * -0.5d);
                    double d13 = d8 - d10;
                    double d14 = d6 + (d13 * -0.5d);
                    double d15 = (d7 - d9) * 0.8660254037844387d;
                    double d16 = (d8 + d10) * 0.8660254037844387d;
                    double d17 = d12 - d16;
                    double d18 = d12 + d16;
                    double d19 = d14 + d15;
                    double d20 = d14 - d15;
                    long j32 = j22 - 1;
                    long j33 = j32 + j5;
                    long j34 = j32 + j13;
                    double d21 = d19;
                    double d22 = doubleFFT_1D.wtable_rl.getDouble(j33 - 1);
                    double d23 = doubleFFT_1D.wtable_rl.getDouble(j33);
                    double d24 = doubleFFT_1D.wtable_rl.getDouble(j34 - 1);
                    long j35 = j24 + j31;
                    double d25 = doubleFFT_1D.wtable_rl.getDouble(j34);
                    long j36 = j24 + j20;
                    double d26 = d24;
                    long j37 = j24 + j21;
                    doubleLargeArray4.setDouble(j35 - 1, d5 + d11);
                    doubleLargeArray4.setDouble(j35, d6 + d13);
                    doubleLargeArray4.setDouble(j36 - 1, (d22 * d17) - (d23 * d21));
                    doubleLargeArray4.setDouble(j36, (d22 * d21) + (d23 * d17));
                    doubleLargeArray4.setDouble(j37 - 1, (d26 * d18) - (d25 * d20));
                    doubleLargeArray4.setDouble(j37, (d26 * d20) + (d25 * d18));
                    j22 += 2;
                    doubleFFT_1D = this;
                    doubleLargeArray3 = doubleLargeArray;
                    j17 = j29;
                    j14 = j27;
                    j7 = -4620693217682128896L;
                    j15 = j30;
                    j16 = j31;
                }
                long j38 = j14;
                long j39 = j7;
                j15++;
                doubleFFT_1D = this;
                doubleLargeArray3 = doubleLargeArray;
                j6 = 3;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void radf4(int i, int i2, double[] dArr, int i3, double[] dArr2, int i4, int i5) {
        int i6 = i;
        int i7 = i2;
        int i8 = i5 + i6;
        int i9 = i8 + i6;
        int i10 = i7 * i6;
        for (int i11 = 0; i11 < i7; i11++) {
            int i12 = i11 * i6;
            int i13 = i12 * 4;
            int i14 = i12 + i10;
            int i15 = i14 + i10;
            double d = dArr[i3 + i12];
            double d2 = dArr[i3 + i14];
            double d3 = dArr[i3 + i15];
            double d4 = dArr[i3 + i15 + i10];
            double d5 = d2 + d4;
            double d6 = d + d3;
            int i16 = i4 + i13;
            int i17 = i4 + i13 + i6 + i6;
            dArr2[i16] = d5 + d6;
            int i18 = i17 - 1;
            dArr2[i18 + i6 + i6] = d6 - d5;
            dArr2[i18] = d - d3;
            dArr2[i17] = d4 - d2;
        }
        int i19 = 2;
        if (i6 >= 2) {
            if (i6 != 2) {
                int i20 = 0;
                while (i20 < i7) {
                    int i21 = i20 * i6;
                    int i22 = i21 + i10;
                    int i23 = i22 + i10;
                    int i24 = i23 + i10;
                    int i25 = i21 * 4;
                    int i26 = i25 + i6;
                    int i27 = i26 + i6;
                    int i28 = i27 + i6;
                    for (int i29 = i19; i29 < i6; i29 += 2) {
                        int i30 = i29 - 1;
                        int i31 = i30 + i5;
                        int i32 = i30 + i8;
                        int i33 = i30 + i9;
                        double[] dArr3 = this.wtable_r;
                        double d7 = dArr3[i31 - 1];
                        double d8 = dArr3[i31];
                        double d9 = dArr3[i32 - 1];
                        double d10 = dArr3[i32];
                        double d11 = dArr3[i33 - 1];
                        double d12 = dArr3[i33];
                        int i34 = i3 + i29;
                        int i35 = i4 + i29;
                        int i36 = i4 + (i6 - i29);
                        int i37 = i34 + i21;
                        int i38 = i34 + i22;
                        int i39 = i34 + i23;
                        int i40 = i34 + i24;
                        double d13 = dArr[i37 - 1];
                        double d14 = dArr[i37];
                        double d15 = dArr[i38 - 1];
                        double d16 = dArr[i38];
                        double d17 = dArr[i39 - 1];
                        double d18 = dArr[i39];
                        double d19 = dArr[i40 - 1];
                        double d20 = dArr[i40];
                        double d21 = (d7 * d15) + (d8 * d16);
                        double d22 = (d7 * d16) - (d8 * d15);
                        double d23 = (d9 * d17) + (d10 * d18);
                        double d24 = (d9 * d18) - (d10 * d17);
                        double d25 = (d11 * d19) + (d12 * d20);
                        double d26 = (d11 * d20) - (d12 * d19);
                        double d27 = d21 + d25;
                        double d28 = d25 - d21;
                        double d29 = d22 + d26;
                        double d30 = d22 - d26;
                        double d31 = d14 + d24;
                        double d32 = d14 - d24;
                        double d33 = d13 + d23;
                        double d34 = d13 - d23;
                        int i41 = i35 + i25;
                        int i42 = i36 + i26;
                        int i43 = i35 + i27;
                        int i44 = i36 + i28;
                        dArr2[i41 - 1] = d27 + d33;
                        dArr2[i44 - 1] = d33 - d27;
                        dArr2[i41] = d29 + d31;
                        dArr2[i44] = d29 - d31;
                        dArr2[i43 - 1] = d30 + d34;
                        dArr2[i42 - 1] = d34 - d30;
                        dArr2[i43] = d28 + d32;
                        dArr2[i42] = d28 - d32;
                    }
                    i20++;
                    i19 = 2;
                }
                if (i6 % 2 == 1) {
                    return;
                }
            }
            for (int i45 = 0; i45 < i7; i45++) {
                int i46 = i45 * i6;
                int i47 = i46 * 4;
                int i48 = i46 + i10;
                int i49 = i48 + i10;
                int i50 = i47 + i6;
                int i51 = i50 + i6;
                int i52 = (i3 + i6) - 1;
                double d35 = dArr[i46 + i52];
                double d36 = dArr[i48 + i52];
                double d37 = dArr[i49 + i52];
                double d38 = dArr[i52 + i49 + i10];
                double d39 = (d36 + d38) * -0.7071067811865476d;
                double d40 = (d36 - d38) * 0.7071067811865476d;
                int i53 = (i4 + i6) - 1;
                dArr2[i47 + i53] = d40 + d35;
                dArr2[i53 + i51] = d35 - d40;
                dArr2[i4 + i50] = d39 - d37;
                dArr2[i4 + i51 + i6] = d39 + d37;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void radf4(long j, long j2, DoubleLargeArray doubleLargeArray, long j3, DoubleLargeArray doubleLargeArray2, long j4, long j5) {
        long j6;
        DoubleLargeArray doubleLargeArray3;
        DoubleLargeArray doubleLargeArray4 = doubleLargeArray;
        DoubleLargeArray doubleLargeArray5 = doubleLargeArray2;
        long j7 = j5 + j;
        long j8 = j7 + j;
        long j9 = j2 * j;
        long j10 = 0;
        while (true) {
            j6 = 4;
            if (j10 >= j2) {
                break;
            }
            long j11 = j10 * j;
            long j12 = 4 * j11;
            long j13 = j11 + j9;
            long j14 = j13 + j9;
            long j15 = j14 + j9;
            double d = doubleLargeArray4.getDouble(j3 + j11);
            long j16 = j8;
            double d2 = doubleLargeArray4.getDouble(j3 + j13);
            long j17 = j7;
            double d3 = doubleLargeArray4.getDouble(j3 + j14);
            long j18 = j9;
            double d4 = doubleLargeArray4.getDouble(j3 + j15);
            double d5 = d2 + d4;
            double d6 = d + d3;
            long j19 = j4 + j12 + j + j;
            long j20 = j10;
            doubleLargeArray5.setDouble(j4 + j12, d5 + d6);
            long j21 = j19 - 1;
            doubleLargeArray5.setDouble(j21 + j + j, d6 - d5);
            doubleLargeArray5.setDouble(j21, d - d3);
            doubleLargeArray5.setDouble(j19, d4 - d2);
            j10 = j20 + 1;
            doubleLargeArray4 = doubleLargeArray;
            j8 = j16;
            j7 = j17;
            j9 = j18;
        }
        long j22 = j7;
        long j23 = j8;
        long j24 = j9;
        long j25 = 2;
        int i = (j > 2 ? 1 : (j == 2 ? 0 : -1));
        if (i >= 0) {
            if (i != 0) {
                long j26 = 0;
                while (j26 < j2) {
                    long j27 = j26 * j;
                    long j28 = j27 + j24;
                    long j29 = j28 + j24;
                    long j30 = j29 + j24;
                    long j31 = j27 * j6;
                    long j32 = j31 + j;
                    long j33 = j32 + j;
                    long j34 = j33 + j;
                    long j35 = j25;
                    while (j35 < j) {
                        long j36 = j35 - 1;
                        long j37 = j36 + j5;
                        long j38 = j36 + j22;
                        long j39 = j26;
                        long j40 = j36 + j23;
                        long j41 = j30;
                        double d7 = this.wtable_rl.getDouble(j37 - 1);
                        double d8 = this.wtable_rl.getDouble(j37);
                        double d9 = this.wtable_rl.getDouble(j38 - 1);
                        double d10 = this.wtable_rl.getDouble(j38);
                        double d11 = this.wtable_rl.getDouble(j40 - 1);
                        long j42 = j3 + j35;
                        long j43 = j4 + j35;
                        long j44 = j4 + (j - j35);
                        double d12 = this.wtable_rl.getDouble(j40);
                        long j45 = j42 + j27;
                        long j46 = j42 + j28;
                        long j47 = j42 + j29;
                        long j48 = j42 + j41;
                        double d13 = d11;
                        DoubleLargeArray doubleLargeArray6 = doubleLargeArray;
                        double d14 = doubleLargeArray6.getDouble(j45 - 1);
                        double d15 = doubleLargeArray6.getDouble(j45);
                        double d16 = d14;
                        double d17 = doubleLargeArray6.getDouble(j46 - 1);
                        double d18 = doubleLargeArray6.getDouble(j46);
                        double d19 = d15;
                        double d20 = doubleLargeArray6.getDouble(j47 - 1);
                        double d21 = doubleLargeArray6.getDouble(j47);
                        double d22 = doubleLargeArray6.getDouble(j48 - 1);
                        double d23 = doubleLargeArray6.getDouble(j48);
                        double d24 = (d7 * d17) + (d8 * d18);
                        double d25 = (d7 * d18) - (d17 * d8);
                        double d26 = (d9 * d20) + (d10 * d21);
                        double d27 = (d9 * d21) - (d20 * d10);
                        double d28 = (d13 * d22) + (d12 * d23);
                        double d29 = (d13 * d23) - (d22 * d12);
                        double d30 = d24 + d28;
                        double d31 = d25 + d29;
                        double d32 = d25 - d29;
                        double d33 = d19 + d27;
                        double d34 = d16 + d26;
                        double d35 = d28 - d24;
                        long j49 = j43 + j31;
                        double d36 = d19 - d27;
                        long j50 = j44 + j32;
                        long j51 = j43 + j33;
                        double d37 = d16 - d26;
                        long j52 = j44 + j34;
                        double d38 = d32;
                        long j53 = j51;
                        DoubleLargeArray doubleLargeArray7 = doubleLargeArray2;
                        doubleLargeArray7.setDouble(j49 - 1, d30 + d34);
                        doubleLargeArray7.setDouble(j52 - 1, d34 - d30);
                        doubleLargeArray7.setDouble(j49, d31 + d33);
                        doubleLargeArray7.setDouble(j52, d31 - d33);
                        doubleLargeArray7.setDouble(j53 - 1, d38 + d37);
                        doubleLargeArray7.setDouble(j50 - 1, d37 - d38);
                        doubleLargeArray7.setDouble(j53, d35 + d36);
                        doubleLargeArray7.setDouble(j50, d35 - d36);
                        j25 = 2;
                        j35 += 2;
                        doubleLargeArray5 = doubleLargeArray7;
                        j30 = j41;
                        j26 = j39;
                        j27 = j27;
                        j28 = j28;
                        j29 = j29;
                    }
                    DoubleLargeArray doubleLargeArray8 = doubleLargeArray5;
                    j26++;
                    j6 = 4;
                }
                doubleLargeArray3 = doubleLargeArray5;
                if (j % j25 == 1) {
                    return;
                }
            } else {
                doubleLargeArray3 = doubleLargeArray5;
            }
            for (long j54 = 0; j54 < j2; j54++) {
                long j55 = j54 * j;
                long j56 = j55 * 4;
                long j57 = j55 + j24;
                long j58 = j57 + j24;
                long j59 = j58 + j24;
                long j60 = j56 + j;
                long j61 = j60 + j;
                long j62 = (j3 + j) - 1;
                DoubleLargeArray doubleLargeArray9 = doubleLargeArray;
                double d39 = doubleLargeArray9.getDouble(j62 + j55);
                double d40 = doubleLargeArray9.getDouble(j62 + j57);
                double d41 = doubleLargeArray9.getDouble(j62 + j58);
                double d42 = doubleLargeArray9.getDouble(j62 + j59);
                double d43 = (d40 + d42) * -0.7071067811865476d;
                double d44 = (d40 - d42) * 0.7071067811865476d;
                long j63 = (j4 + j) - 1;
                doubleLargeArray3.setDouble(j63 + j56, d44 + d39);
                doubleLargeArray3.setDouble(j63 + j61, d39 - d44);
                doubleLargeArray3.setDouble(j4 + j60, d43 - d41);
                doubleLargeArray3.setDouble(j4 + j61 + j, d43 + d41);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void radb4(int i, int i2, double[] dArr, int i3, double[] dArr2, int i4, int i5) {
        int i6 = i;
        int i7 = i2;
        int i8 = i5 + i6;
        int i9 = i8 + i6;
        int i10 = i7 * i6;
        for (int i11 = 0; i11 < i7; i11++) {
            int i12 = i11 * i6;
            int i13 = i12 * 4;
            int i14 = i12 + i10;
            int i15 = i14 + i10;
            int i16 = i13 + i6;
            int i17 = i16 + i6;
            double d = dArr[i3 + i13];
            double d2 = dArr[i3 + i17];
            int i18 = (i3 + i6) - 1;
            double d3 = dArr[i17 + i6 + i18];
            double d4 = dArr[i18 + i16];
            double d5 = d - d3;
            double d6 = d + d3;
            double d7 = d4 + d4;
            double d8 = d2 + d2;
            dArr2[i4 + i12] = d6 + d7;
            dArr2[i4 + i14] = d5 - d8;
            dArr2[i4 + i15] = d6 - d7;
            dArr2[i4 + i15 + i10] = d5 + d8;
        }
        int i19 = 2;
        if (i6 >= 2) {
            if (i6 != 2) {
                int i20 = 0;
                while (i20 < i7) {
                    int i21 = i20 * i6;
                    int i22 = i21 + i10;
                    int i23 = i22 + i10;
                    int i24 = i23 + i10;
                    int i25 = i21 * 4;
                    int i26 = i25 + i6;
                    int i27 = i26 + i6;
                    int i28 = i27 + i6;
                    for (int i29 = i19; i29 < i6; i29 += 2) {
                        int i30 = i29 - 1;
                        int i31 = i30 + i5;
                        int i32 = i30 + i8;
                        int i33 = i30 + i9;
                        double[] dArr3 = this.wtable_r;
                        double d9 = dArr3[i31 - 1];
                        double d10 = dArr3[i31];
                        double d11 = dArr3[i32 - 1];
                        double d12 = dArr3[i32];
                        double d13 = dArr3[i33 - 1];
                        double d14 = dArr3[i33];
                        int i34 = i3 + i29;
                        int i35 = i3 + (i6 - i29);
                        int i36 = i4 + i29;
                        int i37 = i34 + i25;
                        int i38 = i35 + i26;
                        int i39 = i34 + i27;
                        int i40 = i35 + i28;
                        double d15 = dArr[i37 - 1];
                        double d16 = dArr[i37];
                        double d17 = dArr[i38 - 1];
                        double d18 = dArr[i38];
                        double d19 = dArr[i39 - 1];
                        double d20 = dArr[i39];
                        double d21 = dArr[i40 - 1];
                        double d22 = dArr[i40];
                        double d23 = d16 + d22;
                        double d24 = d16 - d22;
                        double d25 = d20 - d18;
                        double d26 = d20 + d18;
                        double d27 = d15 - d21;
                        double d28 = d15 + d21;
                        double d29 = d19 - d17;
                        double d30 = d19 + d17;
                        double d31 = d28 - d30;
                        double d32 = d24 - d25;
                        double d33 = d27 - d26;
                        double d34 = d27 + d26;
                        double d35 = d23 + d29;
                        double d36 = d23 - d29;
                        int i41 = i36 + i21;
                        int i42 = i36 + i22;
                        int i43 = i36 + i23;
                        int i44 = i36 + i24;
                        dArr2[i41 - 1] = d28 + d30;
                        dArr2[i41] = d24 + d25;
                        dArr2[i42 - 1] = (d9 * d33) - (d10 * d35);
                        dArr2[i42] = (d9 * d35) + (d10 * d33);
                        dArr2[i43 - 1] = (d11 * d31) - (d12 * d32);
                        dArr2[i43] = (d11 * d32) + (d12 * d31);
                        dArr2[i44 - 1] = (d13 * d34) - (d14 * d36);
                        dArr2[i44] = (d13 * d36) + (d14 * d34);
                    }
                    i20++;
                    i19 = 2;
                }
                if (i6 % 2 == 1) {
                    return;
                }
            }
            for (int i45 = 0; i45 < i7; i45++) {
                int i46 = i45 * i6;
                int i47 = i46 * 4;
                int i48 = i46 + i10;
                int i49 = i48 + i10;
                int i50 = i47 + i6;
                int i51 = i50 + i6;
                int i52 = (i3 + i6) - 1;
                double d37 = dArr[i47 + i52];
                double d38 = dArr[i52 + i51];
                double d39 = dArr[i3 + i50];
                double d40 = dArr[i3 + i51 + i6];
                double d41 = d39 + d40;
                double d42 = d40 - d39;
                double d43 = d37 - d38;
                double d44 = d37 + d38;
                int i53 = (i4 + i6) - 1;
                dArr2[i46 + i53] = d44 + d44;
                dArr2[i48 + i53] = (d43 - d41) * 1.4142135623730951d;
                dArr2[i49 + i53] = d42 + d42;
                dArr2[i53 + i49 + i10] = (d43 + d41) * -1.4142135623730951d;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void radb4(long j, long j2, DoubleLargeArray doubleLargeArray, long j3, DoubleLargeArray doubleLargeArray2, long j4, long j5) {
        long j6;
        long j7;
        DoubleLargeArray doubleLargeArray3 = doubleLargeArray;
        DoubleLargeArray doubleLargeArray4 = doubleLargeArray2;
        long j8 = j5 + j;
        long j9 = j8 + j;
        long j10 = j2 * j;
        long j11 = 0;
        while (true) {
            j6 = 4;
            if (j11 >= j2) {
                break;
            }
            long j12 = j11 * j;
            long j13 = 4 * j12;
            long j14 = j12 + j10;
            long j15 = j14 + j10;
            long j16 = j13 + j;
            long j17 = j16 + j;
            long j18 = j17 + j;
            double d = doubleLargeArray3.getDouble(j3 + j13);
            double d2 = doubleLargeArray3.getDouble(j3 + j17);
            long j19 = (j3 + j) - 1;
            long j20 = j9;
            double d3 = doubleLargeArray3.getDouble(j19 + j18);
            long j21 = j8;
            double d4 = doubleLargeArray3.getDouble(j19 + j16);
            double d5 = d - d3;
            double d6 = d + d3;
            double d7 = d4 + d4;
            double d8 = d2 + d2;
            doubleLargeArray4.setDouble(j4 + j12, d6 + d7);
            doubleLargeArray4.setDouble(j4 + j14, d5 - d8);
            doubleLargeArray4.setDouble(j4 + j15, d6 - d7);
            doubleLargeArray4.setDouble(j4 + j15 + j10, d5 + d8);
            j11++;
            doubleLargeArray3 = doubleLargeArray;
            j8 = j21;
            j9 = j20;
        }
        long j22 = j8;
        long j23 = j9;
        long j24 = 2;
        int i = (j > 2 ? 1 : (j == 2 ? 0 : -1));
        if (i >= 0) {
            if (i != 0) {
                long j25 = 0;
                while (j25 < j2) {
                    long j26 = j25 * j;
                    long j27 = j26 + j10;
                    long j28 = j27 + j10;
                    long j29 = j28 + j10;
                    long j30 = j26 * j6;
                    long j31 = j30 + j;
                    long j32 = j31 + j;
                    long j33 = j32 + j;
                    long j34 = j24;
                    while (j34 < j) {
                        long j35 = j34 - 1;
                        long j36 = j35 + j5;
                        long j37 = j35 + j22;
                        long j38 = j10;
                        long j39 = j35 + j23;
                        long j40 = j25;
                        long j41 = j28;
                        double d9 = this.wtable_rl.getDouble(j36 - 1);
                        double d10 = this.wtable_rl.getDouble(j36);
                        double d11 = this.wtable_rl.getDouble(j37 - 1);
                        double d12 = this.wtable_rl.getDouble(j37);
                        double d13 = this.wtable_rl.getDouble(j39 - 1);
                        long j42 = j3 + j34;
                        long j43 = j3 + (j - j34);
                        long j44 = j4 + j34;
                        double d14 = this.wtable_rl.getDouble(j39);
                        long j45 = j42 + j30;
                        double d15 = d13;
                        long j46 = j43 + j31;
                        long j47 = j42 + j32;
                        double d16 = d11;
                        long j48 = j43 + j33;
                        double d17 = d9;
                        DoubleLargeArray doubleLargeArray5 = doubleLargeArray;
                        double d18 = doubleLargeArray5.getDouble(j45 - 1);
                        double d19 = doubleLargeArray5.getDouble(j45);
                        long j49 = j27;
                        double d20 = doubleLargeArray5.getDouble(j46 - 1);
                        double d21 = doubleLargeArray5.getDouble(j46);
                        long j50 = j26;
                        double d22 = doubleLargeArray5.getDouble(j47 - 1);
                        double d23 = doubleLargeArray5.getDouble(j47);
                        double d24 = d22;
                        double d25 = doubleLargeArray5.getDouble(j48 - 1);
                        double d26 = doubleLargeArray5.getDouble(j48);
                        double d27 = d19 + d26;
                        double d28 = d19 - d26;
                        double d29 = d23 - d21;
                        double d30 = d23 + d21;
                        double d31 = d18 - d25;
                        double d32 = d18 + d25;
                        double d33 = d24 - d20;
                        double d34 = d24 + d20;
                        double d35 = d32 - d34;
                        double d36 = d28 - d29;
                        double d37 = d31 - d30;
                        double d38 = d31 + d30;
                        double d39 = d27 + d33;
                        double d40 = d27 - d33;
                        long j51 = j44 + j50;
                        double d41 = d38;
                        long j52 = j44 + j49;
                        double d42 = d39;
                        long j53 = j44 + j41;
                        long j54 = j44 + j29;
                        doubleLargeArray4.setDouble(j51 - 1, d32 + d34);
                        doubleLargeArray4.setDouble(j51, d28 + d29);
                        doubleLargeArray4.setDouble(j52 - 1, (d17 * d37) - (d10 * d42));
                        doubleLargeArray4.setDouble(j52, (d17 * d42) + (d10 * d37));
                        doubleLargeArray4.setDouble(j53 - 1, (d16 * d35) - (d12 * d36));
                        doubleLargeArray4.setDouble(j53, (d16 * d36) + (d12 * d35));
                        doubleLargeArray4.setDouble(j54 - 1, (d15 * d41) - (d14 * d40));
                        doubleLargeArray4.setDouble(j54, (d15 * d40) + (d14 * d41));
                        j24 = 2;
                        j34 += 2;
                        j25 = j40;
                        j10 = j38;
                        j28 = j41;
                        j27 = j49;
                        j26 = j50;
                    }
                    long j55 = j10;
                    j25++;
                    j6 = 4;
                }
                j7 = j10;
                if (j % j24 == 1) {
                    return;
                }
            } else {
                j7 = j10;
            }
            long j56 = 0;
            while (j56 < j2) {
                long j57 = j56 * j;
                long j58 = j57 * 4;
                long j59 = j57 + j7;
                long j60 = j59 + j7;
                long j61 = j58 + j;
                long j62 = j61 + j;
                long j63 = (j3 + j) - 1;
                DoubleLargeArray doubleLargeArray6 = doubleLargeArray;
                double d43 = doubleLargeArray6.getDouble(j63 + j58);
                double d44 = doubleLargeArray6.getDouble(j63 + j62);
                long j64 = j56;
                double d45 = doubleLargeArray6.getDouble(j3 + j61);
                double d46 = doubleLargeArray6.getDouble(j3 + j62 + j);
                double d47 = d45 + d46;
                double d48 = d46 - d45;
                double d49 = d43 - d44;
                double d50 = d43 + d44;
                long j65 = (j4 + j) - 1;
                doubleLargeArray4.setDouble(j65 + j57, d50 + d50);
                doubleLargeArray4.setDouble(j65 + j59, (d49 - d47) * 1.4142135623730951d);
                doubleLargeArray4.setDouble(j65 + j60, d48 + d48);
                doubleLargeArray4.setDouble(j65 + j60 + j7, (d49 + d47) * -1.4142135623730951d);
                j56 = j64 + 1;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void radf5(int i, int i2, double[] dArr, int i3, double[] dArr2, int i4, int i5) {
        long j;
        int i6 = i;
        int i7 = i2;
        int i8 = i5 + i6;
        int i9 = i8 + i6;
        int i10 = i9 + i6;
        int i11 = i7 * i6;
        int i12 = 0;
        while (true) {
            j = 4606741575090066687L;
            if (i12 >= i7) {
                break;
            }
            int i13 = i12 * i6;
            int i14 = i13 * 5;
            int i15 = i14 + i6;
            int i16 = i15 + i6;
            int i17 = i16 + i6;
            int i18 = i13 + i11;
            int i19 = i18 + i11;
            int i20 = i19 + i11;
            int i21 = (i4 + i6) - 1;
            double d = dArr[i3 + i13];
            double d2 = dArr[i3 + i18];
            double d3 = dArr[i3 + i19];
            double d4 = dArr[i3 + i20];
            double d5 = dArr[i3 + i20 + i11];
            double d6 = d5 + d2;
            double d7 = d5 - d2;
            double d8 = d4 + d3;
            double d9 = d4 - d3;
            dArr2[i4 + i14] = d + d6 + d8;
            dArr2[i21 + i15] = d + (d6 * 0.30901699437494745d) + (d8 * -0.8090169943749473d);
            dArr2[i4 + i16] = (d7 * 0.9510565162951535d) + (d9 * 0.5877852522924732d);
            dArr2[i21 + i17] = d + (d6 * -0.8090169943749473d) + (d8 * 0.30901699437494745d);
            dArr2[i4 + i17 + i6] = (d7 * 0.5877852522924732d) - (d9 * 0.9510565162951535d);
            i12++;
        }
        if (i6 != 1) {
            int i22 = 0;
            while (i22 < i7) {
                int i23 = i22 * i6;
                int i24 = i23 * 5;
                int i25 = i24 + i6;
                int i26 = i25 + i6;
                int i27 = i26 + i6;
                int i28 = i27 + i6;
                int i29 = i23 + i11;
                int i30 = i29 + i11;
                int i31 = i30 + i11;
                int i32 = i31 + i11;
                int i33 = 2;
                while (i33 < i6) {
                    int i34 = i33 - 1;
                    int i35 = i34 + i5;
                    int i36 = i34 + i8;
                    int i37 = i34 + i9;
                    int i38 = i34 + i10;
                    double[] dArr3 = this.wtable_r;
                    double d10 = dArr3[i35 - 1];
                    double d11 = dArr3[i35];
                    double d12 = dArr3[i36 - 1];
                    double d13 = dArr3[i36];
                    double d14 = dArr3[i37 - 1];
                    double d15 = dArr3[i37];
                    double d16 = dArr3[i38 - 1];
                    double d17 = dArr3[i38];
                    int i39 = i3 + i33;
                    int i40 = i4 + i33;
                    int i41 = i4 + (i6 - i33);
                    int i42 = i39 + i23;
                    int i43 = i39 + i29;
                    int i44 = i39 + i30;
                    int i45 = i39 + i31;
                    int i46 = i39 + i32;
                    double d18 = dArr[i42 - 1];
                    double d19 = dArr[i42];
                    double d20 = dArr[i43 - 1];
                    double d21 = dArr[i43];
                    double d22 = dArr[i44 - 1];
                    double d23 = dArr[i44];
                    double d24 = dArr[i45 - 1];
                    double d25 = dArr[i45];
                    double d26 = dArr[i46 - 1];
                    double d27 = dArr[i46];
                    double d28 = (d10 * d20) + (d11 * d21);
                    double d29 = (d10 * d21) - (d11 * d20);
                    double d30 = (d12 * d22) + (d13 * d23);
                    double d31 = (d12 * d23) - (d13 * d22);
                    double d32 = (d14 * d24) + (d15 * d25);
                    double d33 = (d14 * d25) - (d15 * d24);
                    double d34 = (d16 * d26) + (d17 * d27);
                    double d35 = (d16 * d27) - (d17 * d26);
                    double d36 = d28 + d34;
                    double d37 = d34 - d28;
                    double d38 = d29 - d35;
                    double d39 = d29 + d35;
                    double d40 = d30 + d32;
                    double d41 = d32 - d30;
                    double d42 = d31 - d33;
                    double d43 = d31 + d33;
                    double d44 = d18 + (d36 * 0.30901699437494745d) + (d40 * -0.8090169943749473d);
                    double d45 = d19 + (d39 * 0.30901699437494745d) + (d43 * -0.8090169943749473d);
                    double d46 = d18 + (d36 * -0.8090169943749473d) + (d40 * 0.30901699437494745d);
                    double d47 = d19 + (d39 * -0.8090169943749473d) + (d43 * 0.30901699437494745d);
                    double d48 = (d38 * 0.9510565162951535d) + (d42 * 0.5877852522924732d);
                    double d49 = (d37 * 0.9510565162951535d) + (d41 * 0.5877852522924732d);
                    double d50 = (d38 * 0.5877852522924732d) - (d42 * 0.9510565162951535d);
                    double d51 = (d37 * 0.5877852522924732d) - (d41 * 0.9510565162951535d);
                    int i47 = i40 + i24;
                    int i48 = i41 + i25;
                    int i49 = i40 + i26;
                    int i50 = i41 + i27;
                    int i51 = i40 + i28;
                    dArr2[i47 - 1] = d18 + d36 + d40;
                    dArr2[i47] = d19 + d39 + d43;
                    dArr2[i49 - 1] = d44 + d48;
                    dArr2[i48 - 1] = d44 - d48;
                    dArr2[i49] = d45 + d49;
                    dArr2[i48] = d49 - d45;
                    dArr2[i51 - 1] = d46 + d50;
                    dArr2[i50 - 1] = d46 - d50;
                    dArr2[i51] = d47 + d51;
                    dArr2[i50] = d51 - d47;
                    i33 += 2;
                    j = 4606741575090066687L;
                }
                long j2 = j;
                i22++;
                j = j2;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void radf5(long j, long j2, DoubleLargeArray doubleLargeArray, long j3, DoubleLargeArray doubleLargeArray2, long j4, long j5) {
        DoubleLargeArray doubleLargeArray3 = doubleLargeArray;
        DoubleLargeArray doubleLargeArray4 = doubleLargeArray2;
        long j6 = j5 + j;
        long j7 = j6 + j;
        long j8 = j7 + j;
        long j9 = j2 * j;
        long j10 = 0;
        while (j10 < j2) {
            long j11 = j10 * j;
            long j12 = 5 * j11;
            long j13 = j12 + j;
            long j14 = j13 + j;
            long j15 = j14 + j;
            long j16 = j11 + j9;
            long j17 = j16 + j9;
            long j18 = j17 + j9;
            long j19 = j18 + j9;
            long j20 = (j4 + j) - 1;
            double d = doubleLargeArray3.getDouble(j3 + j11);
            long j21 = j8;
            double d2 = doubleLargeArray3.getDouble(j3 + j16);
            long j22 = j7;
            double d3 = doubleLargeArray3.getDouble(j3 + j17);
            long j23 = j6;
            double d4 = doubleLargeArray3.getDouble(j3 + j18);
            long j24 = j9;
            double d5 = doubleLargeArray3.getDouble(j3 + j19);
            double d6 = d5 + d2;
            double d7 = d5 - d2;
            double d8 = d4 + d3;
            double d9 = d4 - d3;
            doubleLargeArray4.setDouble(j4 + j12, d + d6 + d8);
            doubleLargeArray4.setDouble(j20 + j13, (d6 * 0.30901699437494745d) + d + (d8 * -0.8090169943749473d));
            doubleLargeArray4.setDouble(j4 + j14, (d7 * 0.9510565162951535d) + (d9 * 0.5877852522924732d));
            doubleLargeArray4.setDouble(j20 + j15, d + (d6 * -0.8090169943749473d) + (d8 * 0.30901699437494745d));
            doubleLargeArray4.setDouble(j4 + j15 + j, (d7 * 0.5877852522924732d) - (d9 * 0.9510565162951535d));
            j10++;
            doubleLargeArray3 = doubleLargeArray;
            j8 = j21;
            j7 = j22;
            j6 = j23;
            j9 = j24;
        }
        long j25 = j6;
        long j26 = j7;
        long j27 = j8;
        long j28 = j9;
        if (j != 1) {
            long j29 = 0;
            while (j29 < j2) {
                long j30 = j29 * j;
                long j31 = j30 * 5;
                long j32 = j31 + j;
                long j33 = j32 + j;
                long j34 = j33 + j;
                long j35 = j34 + j;
                long j36 = j30 + j28;
                long j37 = j36 + j28;
                long j38 = j37 + j28;
                long j39 = j38 + j28;
                long j40 = 2;
                while (j40 < j) {
                    long j41 = j40 - 1;
                    long j42 = j29;
                    long j43 = j41 + j5;
                    long j44 = j35;
                    long j45 = j41 + j25;
                    long j46 = j34;
                    long j47 = j41 + j26;
                    long j48 = j33;
                    long j49 = j41 + j27;
                    long j50 = j32;
                    double d10 = this.wtable_rl.getDouble(j43 - 1);
                    double d11 = this.wtable_rl.getDouble(j43);
                    long j51 = j31;
                    double d12 = this.wtable_rl.getDouble(j45 - 1);
                    double d13 = this.wtable_rl.getDouble(j45);
                    double d14 = this.wtable_rl.getDouble(j47 - 1);
                    double d15 = this.wtable_rl.getDouble(j47);
                    double d16 = this.wtable_rl.getDouble(j49 - 1);
                    long j52 = j3 + j40;
                    long j53 = j4 + j40;
                    long j54 = j4 + (j - j40);
                    double d17 = this.wtable_rl.getDouble(j49);
                    long j55 = j52 + j30;
                    long j56 = j30;
                    long j57 = j52 + j36;
                    double d18 = d16;
                    long j58 = j52 + j37;
                    double d19 = d14;
                    long j59 = j52 + j38;
                    double d20 = d12;
                    long j60 = j52 + j39;
                    double d21 = d11;
                    DoubleLargeArray doubleLargeArray5 = doubleLargeArray;
                    double d22 = doubleLargeArray5.getDouble(j55 - 1);
                    double d23 = doubleLargeArray5.getDouble(j55);
                    double d24 = doubleLargeArray5.getDouble(j57 - 1);
                    double d25 = doubleLargeArray5.getDouble(j57);
                    double d26 = d22;
                    double d27 = doubleLargeArray5.getDouble(j58 - 1);
                    double d28 = doubleLargeArray5.getDouble(j58);
                    double d29 = doubleLargeArray5.getDouble(j59 - 1);
                    double d30 = doubleLargeArray5.getDouble(j59);
                    double d31 = doubleLargeArray5.getDouble(j60 - 1);
                    double d32 = doubleLargeArray5.getDouble(j60);
                    double d33 = (d10 * d24) + (d21 * d25);
                    double d34 = (d10 * d25) - (d21 * d24);
                    double d35 = (d20 * d27) + (d13 * d28);
                    double d36 = (d20 * d28) - (d27 * d13);
                    double d37 = (d19 * d29) + (d15 * d30);
                    double d38 = (d19 * d30) - (d29 * d15);
                    double d39 = (d18 * d31) + (d17 * d32);
                    double d40 = (d32 * d18) - (d31 * d17);
                    double d41 = d33 + d39;
                    double d42 = d39 - d33;
                    double d43 = d34 - d40;
                    double d44 = d34 + d40;
                    double d45 = d35 + d37;
                    double d46 = d37 - d35;
                    double d47 = d36 - d38;
                    double d48 = d36 + d38;
                    double d49 = d26 + (d41 * 0.30901699437494745d) + (d45 * -0.8090169943749473d);
                    double d50 = d23 + (d44 * 0.30901699437494745d) + (d48 * -0.8090169943749473d);
                    double d51 = d26 + (d41 * -0.8090169943749473d) + (d45 * 0.30901699437494745d);
                    double d52 = d23 + (d44 * -0.8090169943749473d) + (d48 * 0.30901699437494745d);
                    double d53 = (d43 * 0.9510565162951535d) + (d47 * 0.5877852522924732d);
                    double d54 = (d42 * 0.9510565162951535d) + (d46 * 0.5877852522924732d);
                    double d55 = (d43 * 0.5877852522924732d) - (d47 * 0.9510565162951535d);
                    long j61 = j53 + j51;
                    double d56 = (d42 * 0.5877852522924732d) - (d46 * 0.9510565162951535d);
                    long j62 = j53 + j48;
                    long j63 = j54 + j50;
                    long j64 = j54 + j46;
                    long j65 = j53 + j44;
                    double d57 = d26 + d41 + d45;
                    DoubleLargeArray doubleLargeArray6 = doubleLargeArray2;
                    doubleLargeArray6.setDouble(j61 - 1, d57);
                    doubleLargeArray6.setDouble(j61, d23 + d44 + d48);
                    doubleLargeArray6.setDouble(j62 - 1, d49 + d53);
                    doubleLargeArray6.setDouble(j63 - 1, d49 - d53);
                    doubleLargeArray6.setDouble(j62, d50 + d54);
                    doubleLargeArray6.setDouble(j63, d54 - d50);
                    doubleLargeArray6.setDouble(j65 - 1, d51 + d55);
                    doubleLargeArray6.setDouble(j64 - 1, d51 - d55);
                    doubleLargeArray6.setDouble(j65, d52 + d56);
                    doubleLargeArray6.setDouble(j64, d56 - d52);
                    j40 += 2;
                    j32 = j50;
                    j29 = j42;
                    j35 = j44;
                    j34 = j46;
                    j33 = j48;
                    j31 = j51;
                    j30 = j56;
                }
                DoubleLargeArray doubleLargeArray7 = doubleLargeArray;
                j29++;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void radb5(int i, int i2, double[] dArr, int i3, double[] dArr2, int i4, int i5) {
        long j;
        int i6 = i;
        int i7 = i2;
        int i8 = i5 + i6;
        int i9 = i8 + i6;
        int i10 = i9 + i6;
        int i11 = i7 * i6;
        int i12 = 0;
        while (true) {
            j = 4606741575090066687L;
            if (i12 >= i7) {
                break;
            }
            int i13 = i12 * i6;
            int i14 = i13 * 5;
            int i15 = i14 + i6;
            int i16 = i15 + i6;
            int i17 = i16 + i6;
            int i18 = i13 + i11;
            int i19 = i18 + i11;
            int i20 = i19 + i11;
            int i21 = (i3 + i6) - 1;
            double d = dArr[i3 + i14];
            double d2 = dArr[i3 + i16] * 2.0d;
            double d3 = dArr[i3 + i17 + i6] * 2.0d;
            double d4 = dArr[i21 + i15] * 2.0d;
            double d5 = dArr[i21 + i17] * 2.0d;
            double d6 = d + (d4 * 0.30901699437494745d) + (d5 * -0.8090169943749473d);
            double d7 = d + (-0.8090169943749473d * d4) + (0.30901699437494745d * d5);
            double d8 = (d2 * 0.9510565162951535d) + (d3 * 0.5877852522924732d);
            double d9 = (d2 * 0.5877852522924732d) - (d3 * 0.9510565162951535d);
            dArr2[i4 + i13] = d + d4 + d5;
            dArr2[i4 + i18] = d6 - d8;
            dArr2[i4 + i19] = d7 - d9;
            dArr2[i4 + i20] = d7 + d9;
            dArr2[i4 + i20 + i11] = d6 + d8;
            i12++;
        }
        if (i6 != 1) {
            int i22 = 0;
            while (i22 < i7) {
                int i23 = i22 * i6;
                int i24 = i23 * 5;
                int i25 = i24 + i6;
                int i26 = i25 + i6;
                int i27 = i26 + i6;
                int i28 = i27 + i6;
                int i29 = i23 + i11;
                int i30 = i29 + i11;
                int i31 = i30 + i11;
                int i32 = i31 + i11;
                int i33 = 2;
                while (i33 < i6) {
                    int i34 = i33 - 1;
                    int i35 = i34 + i5;
                    int i36 = i34 + i8;
                    int i37 = i34 + i9;
                    int i38 = i34 + i10;
                    double[] dArr3 = this.wtable_r;
                    double d10 = dArr3[i35 - 1];
                    double d11 = dArr3[i35];
                    double d12 = dArr3[i36 - 1];
                    double d13 = dArr3[i36];
                    double d14 = dArr3[i37 - 1];
                    double d15 = dArr3[i37];
                    double d16 = dArr3[i38 - 1];
                    double d17 = dArr3[i38];
                    int i39 = i3 + i33;
                    int i40 = i3 + (i6 - i33);
                    int i41 = i4 + i33;
                    int i42 = i39 + i24;
                    int i43 = i40 + i25;
                    int i44 = i39 + i26;
                    int i45 = i40 + i27;
                    int i46 = i39 + i28;
                    double d18 = dArr[i42 - 1];
                    double d19 = dArr[i42];
                    double d20 = dArr[i43 - 1];
                    double d21 = dArr[i43];
                    double d22 = dArr[i44 - 1];
                    double d23 = dArr[i44];
                    double d24 = dArr[i45 - 1];
                    double d25 = dArr[i45];
                    double d26 = dArr[i46 - 1];
                    double d27 = dArr[i46];
                    double d28 = d23 + d21;
                    double d29 = d23 - d21;
                    double d30 = d27 + d25;
                    double d31 = d27 - d25;
                    double d32 = d22 - d20;
                    double d33 = d22 + d20;
                    double d34 = d26 - d24;
                    double d35 = d26 + d24;
                    double d36 = d18 + (d33 * 0.30901699437494745d) + (d35 * -0.8090169943749473d);
                    double d37 = d19 + (d29 * 0.30901699437494745d) + (d31 * -0.8090169943749473d);
                    double d38 = d18 + (d33 * -0.8090169943749473d) + (d35 * 0.30901699437494745d);
                    double d39 = d19 + (d29 * -0.8090169943749473d) + (d31 * 0.30901699437494745d);
                    double d40 = (d32 * 0.9510565162951535d) + (d34 * 0.5877852522924732d);
                    double d41 = (d28 * 0.9510565162951535d) + (d30 * 0.5877852522924732d);
                    double d42 = (d32 * 0.5877852522924732d) - (d34 * 0.9510565162951535d);
                    double d43 = (d28 * 0.5877852522924732d) - (d30 * 0.9510565162951535d);
                    double d44 = d38 - d43;
                    double d45 = d38 + d43;
                    double d46 = d39 + d42;
                    double d47 = d39 - d42;
                    double d48 = d36 + d41;
                    double d49 = d36 - d41;
                    double d50 = d37 - d40;
                    double d51 = d37 + d40;
                    int i47 = i41 + i23;
                    int i48 = i41 + i29;
                    int i49 = i41 + i30;
                    int i50 = i41 + i31;
                    int i51 = i41 + i32;
                    dArr2[i47 - 1] = d18 + d33 + d35;
                    dArr2[i47] = d19 + d29 + d31;
                    dArr2[i48 - 1] = (d10 * d49) - (d11 * d51);
                    dArr2[i48] = (d10 * d51) + (d11 * d49);
                    dArr2[i49 - 1] = (d12 * d44) - (d13 * d46);
                    dArr2[i49] = (d12 * d46) + (d13 * d44);
                    dArr2[i50 - 1] = (d14 * d45) - (d15 * d47);
                    dArr2[i50] = (d14 * d47) + (d15 * d45);
                    dArr2[i51 - 1] = (d16 * d48) - (d17 * d50);
                    dArr2[i51] = (d16 * d50) + (d17 * d48);
                    i33 += 2;
                    j = 4606741575090066687L;
                }
                long j2 = j;
                i22++;
                j = j2;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void radb5(long j, long j2, DoubleLargeArray doubleLargeArray, long j3, DoubleLargeArray doubleLargeArray2, long j4, long j5) {
        DoubleFFT_1D doubleFFT_1D = this;
        DoubleLargeArray doubleLargeArray3 = doubleLargeArray;
        DoubleLargeArray doubleLargeArray4 = doubleLargeArray2;
        long j6 = j5 + j;
        long j7 = j6 + j;
        long j8 = j7 + j;
        long j9 = j2 * j;
        long j10 = 0;
        while (j10 < j2) {
            long j11 = j10 * j;
            long j12 = 5 * j11;
            long j13 = j12 + j;
            long j14 = j13 + j;
            long j15 = j14 + j;
            long j16 = j11 + j9;
            long j17 = j16 + j9;
            long j18 = j17 + j9;
            long j19 = j18 + j9;
            long j20 = (j3 + j) - 1;
            double d = doubleLargeArray3.getDouble(j3 + j12);
            long j21 = j8;
            double d2 = doubleLargeArray3.getDouble(j3 + j14) * 2.0d;
            long j22 = j7;
            double d3 = doubleLargeArray3.getDouble(j3 + j15 + j) * 2.0d;
            long j23 = j6;
            double d4 = doubleLargeArray3.getDouble(j20 + j13) * 2.0d;
            long j24 = j9;
            double d5 = doubleLargeArray3.getDouble(j20 + j15) * 2.0d;
            double d6 = (d4 * 0.30901699437494745d) + d + (d5 * -0.8090169943749473d);
            double d7 = d + (-0.8090169943749473d * d4) + (0.30901699437494745d * d5);
            double d8 = (d2 * 0.9510565162951535d) + (d3 * 0.5877852522924732d);
            double d9 = (d2 * 0.5877852522924732d) - (d3 * 0.9510565162951535d);
            doubleLargeArray4.setDouble(j4 + j11, d + d4 + d5);
            doubleLargeArray4.setDouble(j4 + j16, d6 - d8);
            doubleLargeArray4.setDouble(j4 + j17, d7 - d9);
            doubleLargeArray4.setDouble(j4 + j18, d7 + d9);
            doubleLargeArray4.setDouble(j4 + j19, d6 + d8);
            j10++;
            j9 = j24;
            j7 = j22;
            j6 = j23;
            j8 = j21;
        }
        long j25 = j6;
        long j26 = j7;
        long j27 = j8;
        long j28 = j9;
        if (j != 1) {
            long j29 = 0;
            while (j29 < j2) {
                long j30 = j29 * j;
                long j31 = j30 * 5;
                long j32 = j31 + j;
                long j33 = j32 + j;
                long j34 = j33 + j;
                long j35 = j34 + j;
                long j36 = j30 + j28;
                long j37 = j36 + j28;
                long j38 = j37 + j28;
                long j39 = j38 + j28;
                long j40 = 2;
                while (j40 < j) {
                    long j41 = j40 - 1;
                    long j42 = j29;
                    long j43 = j41 + j5;
                    long j44 = j30;
                    long j45 = j41 + j25;
                    long j46 = j34;
                    long j47 = j41 + j26;
                    long j48 = j33;
                    long j49 = j41 + j27;
                    long j50 = j32;
                    double d10 = doubleFFT_1D.wtable_rl.getDouble(j43 - 1);
                    double d11 = doubleFFT_1D.wtable_rl.getDouble(j43);
                    double d12 = doubleFFT_1D.wtable_rl.getDouble(j45 - 1);
                    double d13 = doubleFFT_1D.wtable_rl.getDouble(j45);
                    double d14 = doubleFFT_1D.wtable_rl.getDouble(j47 - 1);
                    double d15 = doubleFFT_1D.wtable_rl.getDouble(j47);
                    double d16 = doubleFFT_1D.wtable_rl.getDouble(j49 - 1);
                    long j51 = j3 + j40;
                    long j52 = j3 + (j - j40);
                    long j53 = j4 + j40;
                    double d17 = doubleFFT_1D.wtable_rl.getDouble(j49);
                    long j54 = j51 + j31;
                    long j55 = j52 + j50;
                    double d18 = d16;
                    long j56 = j51 + j48;
                    double d19 = d14;
                    long j57 = j52 + j46;
                    double d20 = d12;
                    long j58 = j51 + j35;
                    double d21 = d10;
                    double d22 = doubleLargeArray3.getDouble(j54 - 1);
                    double d23 = doubleLargeArray3.getDouble(j54);
                    double d24 = doubleLargeArray3.getDouble(j55 - 1);
                    double d25 = doubleLargeArray3.getDouble(j55);
                    double d26 = d22;
                    double d27 = doubleLargeArray3.getDouble(j56 - 1);
                    double d28 = doubleLargeArray3.getDouble(j56);
                    double d29 = d27;
                    double d30 = doubleLargeArray3.getDouble(j57 - 1);
                    double d31 = doubleLargeArray3.getDouble(j57);
                    double d32 = d30;
                    double d33 = doubleLargeArray3.getDouble(j58 - 1);
                    double d34 = doubleLargeArray3.getDouble(j58);
                    double d35 = d28 + d25;
                    double d36 = d28 - d25;
                    double d37 = d34 + d31;
                    double d38 = d34 - d31;
                    double d39 = d29 - d24;
                    double d40 = d29 + d24;
                    double d41 = d33 - d32;
                    double d42 = d33 + d32;
                    double d43 = d26 + (d40 * 0.30901699437494745d) + (d42 * -0.8090169943749473d);
                    double d44 = d23 + (d36 * 0.30901699437494745d) + (d38 * -0.8090169943749473d);
                    double d45 = d26 + (d40 * -0.8090169943749473d) + (d42 * 0.30901699437494745d);
                    double d46 = d23 + (d36 * -0.8090169943749473d) + (d38 * 0.30901699437494745d);
                    double d47 = (d39 * 0.9510565162951535d) + (d41 * 0.5877852522924732d);
                    double d48 = (d35 * 0.9510565162951535d) + (d37 * 0.5877852522924732d);
                    double d49 = (d39 * 0.5877852522924732d) - (d41 * 0.9510565162951535d);
                    double d50 = (d35 * 0.5877852522924732d) - (d37 * 0.9510565162951535d);
                    double d51 = d45 - d50;
                    double d52 = d45 + d50;
                    double d53 = d46 + d49;
                    double d54 = d46 - d49;
                    double d55 = d43 + d48;
                    double d56 = d43 - d48;
                    double d57 = d44 - d47;
                    double d58 = d44 + d47;
                    long j59 = j53 + j44;
                    double d59 = d55;
                    long j60 = j53 + j36;
                    double d60 = d51;
                    long j61 = j53 + j37;
                    long j62 = j53 + j38;
                    long j63 = j53 + j39;
                    double d61 = d26 + d40 + d42;
                    DoubleLargeArray doubleLargeArray5 = doubleLargeArray2;
                    doubleLargeArray5.setDouble(j59 - 1, d61);
                    doubleLargeArray5.setDouble(j59, d23 + d36 + d38);
                    doubleLargeArray5.setDouble(j60 - 1, (d21 * d56) - (d11 * d58));
                    doubleLargeArray5.setDouble(j60, (d21 * d58) + (d11 * d56));
                    doubleLargeArray5.setDouble(j61 - 1, (d20 * d60) - (d13 * d53));
                    doubleLargeArray5.setDouble(j61, (d20 * d53) + (d13 * d60));
                    doubleLargeArray5.setDouble(j62 - 1, (d19 * d52) - (d15 * d54));
                    doubleLargeArray5.setDouble(j62, (d19 * d54) + (d15 * d52));
                    doubleLargeArray5.setDouble(j63 - 1, (d18 * d59) - (d17 * d57));
                    doubleLargeArray5.setDouble(j63, (d18 * d57) + (d17 * d59));
                    j40 += 2;
                    doubleFFT_1D = this;
                    doubleLargeArray3 = doubleLargeArray;
                    doubleLargeArray4 = doubleLargeArray5;
                    j32 = j50;
                    j29 = j42;
                    j30 = j44;
                    j34 = j46;
                    j33 = j48;
                    j31 = j31;
                }
                DoubleLargeArray doubleLargeArray6 = doubleLargeArray4;
                j29++;
                doubleFFT_1D = this;
                doubleLargeArray3 = doubleLargeArray;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void radfg(int i, int i2, int i3, int i4, double[] dArr, int i5, double[] dArr2, int i6, int i7) {
        double d;
        double d2;
        int i8;
        int i9;
        int i10 = i;
        int i11 = i2;
        int i12 = i3;
        int i13 = i4;
        double[] dArr3 = dArr;
        int i14 = i5;
        double[] dArr4 = dArr2;
        int i15 = i6;
        double d3 = 6.283185307179586d / ((double) i11);
        double cos = FastMath.cos(d3);
        double sin = FastMath.sin(d3);
        int i16 = (i11 + 1) / 2;
        int i17 = (i10 - 1) / 2;
        if (i10 != 1) {
            for (int i18 = 0; i18 < i13; i18++) {
                dArr4[i15 + i18] = dArr3[i14 + i18];
            }
            int i19 = 1;
            while (i19 < i11) {
                int i20 = i19 * i12 * i10;
                double d4 = sin;
                for (int i21 = 0; i21 < i12; i21++) {
                    int i22 = (i21 * i10) + i20;
                    dArr4[i15 + i22] = dArr3[i22 + i14];
                }
                i19++;
                sin = d4;
            }
            d2 = sin;
            if (i17 <= i12) {
                int i23 = -i10;
                int i24 = 1;
                while (i24 < i11) {
                    int i25 = i23 + i10;
                    int i26 = i25 - 1;
                    int i27 = i24 * i12 * i10;
                    int i28 = i25;
                    int i29 = 2;
                    while (i29 < i10) {
                        int i30 = i26 + 2;
                        int i31 = i30 + i7;
                        int i32 = i14 + i29;
                        int i33 = i15 + i29;
                        int i34 = i30;
                        double[] dArr5 = this.wtable_r;
                        double d5 = dArr5[i31 - 1];
                        double d6 = dArr5[i31];
                        for (int i35 = 0; i35 < i12; i35++) {
                            int i36 = (i35 * i10) + i27;
                            int i37 = i33 + i36;
                            int i38 = i32 + i36;
                            double d7 = dArr3[i38 - 1];
                            double d8 = dArr3[i38];
                            dArr4[i37 - 1] = (d5 * d7) + (d6 * d8);
                            dArr4[i37] = (d8 * d5) - (d7 * d6);
                        }
                        i29 += 2;
                        i26 = i34;
                    }
                    i24++;
                    i23 = i28;
                }
            } else {
                int i39 = -i10;
                int i40 = 1;
                while (i40 < i11) {
                    i39 += i10;
                    int i41 = i40 * i12 * i10;
                    double d9 = cos;
                    int i42 = 0;
                    while (i42 < i12) {
                        int i43 = i39 - 1;
                        int i44 = (i42 * i10) + i41;
                        int i45 = i39;
                        int i46 = 2;
                        while (i46 < i10) {
                            int i47 = i43 + 2;
                            int i48 = i47 + i7;
                            int i49 = i47;
                            double[] dArr6 = this.wtable_r;
                            double d10 = dArr6[i48 - 1];
                            double d11 = dArr6[i48];
                            int i50 = i15 + i46 + i44;
                            int i51 = i14 + i46 + i44;
                            double d12 = dArr3[i51 - 1];
                            double d13 = dArr3[i51];
                            dArr4[i50 - 1] = (d10 * d12) + (d11 * d13);
                            dArr4[i50] = (d10 * d13) - (d11 * d12);
                            i46 += 2;
                            i43 = i49;
                        }
                        i42++;
                        i39 = i45;
                    }
                    int i52 = i39;
                    i40++;
                    cos = d9;
                }
            }
            d = cos;
            i9 = 2;
            if (i17 >= i12) {
                int i53 = 1;
                while (i53 < i16) {
                    int i54 = i53 * i12 * i10;
                    int i55 = (i11 - i53) * i12 * i10;
                    int i56 = 0;
                    while (i56 < i12) {
                        int i57 = i56 * i10;
                        int i58 = i57 + i54;
                        int i59 = i57 + i55;
                        for (int i60 = 2; i60 < i10; i60 += 2) {
                            int i61 = i14 + i60;
                            int i62 = i15 + i60;
                            int i63 = i61 + i58;
                            int i64 = i61 + i59;
                            int i65 = i62 + i58;
                            int i66 = i62 + i59;
                            double d14 = dArr4[i65 - 1];
                            double d15 = dArr4[i65];
                            double d16 = dArr4[i66 - 1];
                            double d17 = dArr4[i66];
                            dArr3[i63 - 1] = d14 + d16;
                            dArr3[i63] = d15 + d17;
                            dArr3[i64 - 1] = d15 - d17;
                            dArr3[i64] = d16 - d14;
                        }
                        i56++;
                    }
                    i53++;
                }
            } else {
                for (int i67 = 1; i67 < i16; i67++) {
                    int i68 = i67 * i12 * i10;
                    int i69 = (i11 - i67) * i12 * i10;
                    int i70 = 2;
                    while (i70 < i10) {
                        int i71 = i14 + i70;
                        int i72 = i15 + i70;
                        int i73 = i17;
                        for (int i74 = 0; i74 < i12; i74++) {
                            int i75 = i74 * i10;
                            int i76 = i75 + i68;
                            int i77 = i75 + i69;
                            int i78 = i71 + i76;
                            int i79 = i71 + i77;
                            int i80 = i72 + i76;
                            int i81 = i72 + i77;
                            double d18 = dArr4[i80 - 1];
                            double d19 = dArr4[i80];
                            double d20 = dArr4[i81 - 1];
                            double d21 = dArr4[i81];
                            dArr3[i78 - 1] = d18 + d20;
                            dArr3[i78] = d19 + d21;
                            dArr3[i79 - 1] = d19 - d21;
                            dArr3[i79] = d20 - d18;
                        }
                        i70 += 2;
                        i17 = i73;
                    }
                    int i82 = i17;
                }
            }
            i8 = i17;
        } else {
            d2 = sin;
            d = cos;
            i8 = i17;
            i9 = 2;
            System.arraycopy(dArr4, i15, dArr3, i14, i13);
        }
        for (int i83 = 1; i83 < i16; i83++) {
            int i84 = i83 * i12 * i10;
            int i85 = (i11 - i83) * i12 * i10;
            for (int i86 = 0; i86 < i12; i86++) {
                int i87 = i86 * i10;
                int i88 = i87 + i84;
                int i89 = i87 + i85;
                double d22 = dArr4[i15 + i88];
                double d23 = dArr4[i15 + i89];
                dArr3[i88 + i14] = d22 + d23;
                dArr3[i89 + i14] = d23 - d22;
            }
        }
        int i90 = (i11 - 1) * i13;
        double d24 = 1.0d;
        double d25 = 0.0d;
        int i91 = 1;
        while (i91 < i16) {
            double d26 = (d * d24) - (d2 * d25);
            d25 = (d25 * d) + (d24 * d2);
            int i92 = i91 * i13;
            int i93 = (i11 - i91) * i13;
            for (int i94 = 0; i94 < i13; i94++) {
                int i95 = i15 + i94;
                int i96 = i14 + i94;
                dArr4[i95 + i92] = dArr3[i96] + (dArr3[i96 + i13] * d26);
                dArr4[i95 + i93] = dArr3[i96 + i90] * d25;
            }
            double d27 = d25;
            int i97 = i9;
            double d28 = d26;
            while (i97 < i16) {
                double d29 = (d26 * d28) - (d25 * d27);
                d27 = (d27 * d26) + (d28 * d25);
                int i98 = i97 * i13;
                int i99 = (i11 - i97) * i13;
                int i100 = i90;
                for (int i101 = 0; i101 < i13; i101++) {
                    int i102 = i15 + i101;
                    int i103 = i14 + i101;
                    int i104 = i102 + i92;
                    dArr4[i104] = dArr4[i104] + (dArr3[i103 + i98] * d29);
                    int i105 = i102 + i93;
                    dArr4[i105] = dArr4[i105] + (dArr3[i103 + i99] * d27);
                }
                i97++;
                i90 = i100;
                d28 = d29;
            }
            int i106 = i90;
            i91++;
            d24 = d26;
        }
        for (int i107 = 1; i107 < i16; i107++) {
            int i108 = i107 * i13;
            for (int i109 = 0; i109 < i13; i109++) {
                int i110 = i15 + i109;
                dArr4[i110] = dArr4[i110] + dArr3[i14 + i109 + i108];
            }
        }
        if (i10 >= i12) {
            for (int i111 = 0; i111 < i12; i111++) {
                int i112 = i111 * i10;
                int i113 = i112 * i11;
                for (int i114 = 0; i114 < i10; i114++) {
                    dArr3[i14 + i114 + i113] = dArr4[i15 + i114 + i112];
                }
            }
        } else {
            for (int i115 = 0; i115 < i10; i115++) {
                for (int i116 = 0; i116 < i12; i116++) {
                    int i117 = i116 * i10;
                    dArr3[i14 + i115 + (i117 * i11)] = dArr4[i15 + i115 + i117];
                }
            }
        }
        int i118 = i11 * i10;
        for (int i119 = 1; i119 < i16; i119++) {
            int i120 = i119 * i12 * i10;
            int i121 = (i11 - i119) * i12 * i10;
            int i122 = i119 * 2 * i10;
            for (int i123 = 0; i123 < i12; i123++) {
                int i124 = i123 * i10;
                int i125 = i123 * i118;
                dArr3[((((i14 + i10) - 1) + i122) - i10) + i125] = dArr4[i124 + i120 + i15];
                dArr3[i14 + i122 + i125] = dArr4[i124 + i121 + i15];
            }
        }
        if (i10 != 1) {
            if (i8 >= i12) {
                for (int i126 = 1; i126 < i16; i126++) {
                    int i127 = i126 * i12 * i10;
                    int i128 = (i11 - i126) * i12 * i10;
                    int i129 = i126 * 2 * i10;
                    int i130 = 0;
                    while (i130 < i12) {
                        int i131 = i130 * i118;
                        int i132 = i130 * i10;
                        int i133 = i118;
                        for (int i134 = i9; i134 < i10; i134 += 2) {
                            int i135 = i14 + i134 + i129 + i131;
                            int i136 = (((i14 + (i10 - i134)) + i129) - i10) + i131;
                            int i137 = i15 + i134 + i132;
                            int i138 = i137 + i127;
                            int i139 = i137 + i128;
                            double d30 = dArr4[i138 - 1];
                            double d31 = dArr4[i138];
                            double d32 = dArr4[i139 - 1];
                            double d33 = dArr4[i139];
                            dArr3[i135 - 1] = d30 + d32;
                            dArr3[i136 - 1] = d30 - d32;
                            dArr3[i135] = d31 + d33;
                            dArr3[i136] = d33 - d31;
                        }
                        i130++;
                        i118 = i133;
                    }
                    int i140 = i118;
                }
                return;
            }
            int i141 = i118;
            int i142 = 1;
            while (i142 < i16) {
                int i143 = i142 * i12 * i10;
                int i144 = (i11 - i142) * i12 * i10;
                int i145 = i142 * 2 * i10;
                int i146 = i9;
                while (i146 < i10) {
                    int i147 = i14 + i146;
                    int i148 = (i10 - i146) + i14;
                    int i149 = i15 + i146;
                    for (int i150 = 0; i150 < i12; i150++) {
                        int i151 = i150 * i141;
                        int i152 = i147 + i145 + i151;
                        int i153 = ((i148 + i145) - i10) + i151;
                        int i154 = i149 + (i150 * i10);
                        int i155 = i154 + i143;
                        int i156 = i154 + i144;
                        double d34 = dArr4[i155 - 1];
                        double d35 = dArr4[i155];
                        double d36 = dArr4[i156 - 1];
                        double d37 = dArr4[i156];
                        dArr3[i152 - 1] = d34 + d36;
                        dArr3[i153 - 1] = d34 - d36;
                        dArr3[i152] = d35 + d37;
                        dArr3[i153] = d37 - d35;
                    }
                    i146 += 2;
                    int i157 = i2;
                }
                i142++;
                i11 = i2;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void radfg(long j, long j2, long j3, long j4, DoubleLargeArray doubleLargeArray, long j5, DoubleLargeArray doubleLargeArray2, long j6, long j7) {
        double d;
        DoubleLargeArray doubleLargeArray3;
        DoubleFFT_1D doubleFFT_1D = this;
        long j8 = j;
        long j9 = j2;
        DoubleLargeArray doubleLargeArray4 = doubleLargeArray;
        DoubleLargeArray doubleLargeArray5 = doubleLargeArray2;
        double d2 = 6.283185307179586d / ((double) j9);
        double cos = FastMath.cos(d2);
        double sin = FastMath.sin(d2);
        long j10 = (j9 + 1) / 2;
        long j11 = (j8 - 1) / 2;
        int i = (j8 > 1 ? 1 : (j8 == 1 ? 0 : -1));
        if (i != 0) {
            for (long j12 = 0; j12 < j4; j12++) {
                doubleLargeArray5.setDouble(j6 + j12, doubleLargeArray4.getDouble(j5 + j12));
            }
            for (long j13 = 1; j13 < j9; j13++) {
                long j14 = j13 * j3 * j8;
                long j15 = 0;
                while (j15 < j3) {
                    long j16 = (j15 * j8) + j14;
                    doubleLargeArray5.setDouble(j6 + j16, doubleLargeArray4.getDouble(j5 + j16));
                    j15++;
                    j14 = j14;
                }
            }
            int i2 = (j11 > j3 ? 1 : (j11 == j3 ? 0 : -1));
            if (i2 <= 0) {
                long j17 = -j8;
                long j18 = 1;
                while (j18 < j9) {
                    j17 += j8;
                    long j19 = j17 - 1;
                    long j20 = j18 * j3 * j8;
                    long j21 = 2;
                    while (j21 < j8) {
                        long j22 = j19 + 2;
                        long j23 = j17;
                        long j24 = j22 + j7;
                        long j25 = j5 + j21;
                        long j26 = j6 + j21;
                        long j27 = j22;
                        double d3 = doubleFFT_1D.wtable_rl.getDouble(j24 - 1);
                        double d4 = doubleFFT_1D.wtable_rl.getDouble(j24);
                        long j28 = 0;
                        while (j28 < j3) {
                            long j29 = (j28 * j8) + j20;
                            long j30 = j26 + j29;
                            long j31 = j25 + j29;
                            long j32 = j18;
                            double d5 = doubleLargeArray4.getDouble(j31 - 1);
                            double d6 = doubleLargeArray4.getDouble(j31);
                            doubleLargeArray5.setDouble(j30 - 1, (d3 * d5) + (d4 * d6));
                            doubleLargeArray5.setDouble(j30, (d6 * d3) - (d5 * d4));
                            j28++;
                            j8 = j;
                            long j33 = j2;
                            doubleLargeArray4 = doubleLargeArray;
                            j18 = j32;
                        }
                        long j34 = j18;
                        j21 += 2;
                        doubleFFT_1D = this;
                        j8 = j;
                        long j35 = j2;
                        doubleLargeArray4 = doubleLargeArray;
                        j17 = j23;
                        j19 = j27;
                    }
                    long j36 = j17;
                    j18++;
                    doubleFFT_1D = this;
                    j8 = j;
                    j9 = j2;
                    doubleLargeArray4 = doubleLargeArray;
                }
            } else {
                long j37 = j8;
                long j38 = -j37;
                long j39 = 1;
                while (j39 < j2) {
                    j38 += j37;
                    long j40 = j39 * j3 * j37;
                    long j41 = 0;
                    while (j41 < j3) {
                        long j42 = j38 - 1;
                        long j43 = (j41 * j37) + j40;
                        long j44 = 2;
                        while (j44 < j37) {
                            long j45 = j42 + 2;
                            long j46 = j38;
                            long j47 = j45 + j7;
                            long j48 = j40;
                            long j49 = j45;
                            double d7 = this.wtable_rl.getDouble(j47 - 1);
                            double d8 = this.wtable_rl.getDouble(j47);
                            long j50 = j6 + j44 + j43;
                            long j51 = j5 + j44 + j43;
                            double d9 = cos;
                            DoubleLargeArray doubleLargeArray6 = doubleLargeArray;
                            double d10 = doubleLargeArray6.getDouble(j51 - 1);
                            double d11 = doubleLargeArray6.getDouble(j51);
                            doubleLargeArray5.setDouble(j50 - 1, (d7 * d10) + (d8 * d11));
                            doubleLargeArray5.setDouble(j50, (d7 * d11) - (d8 * d10));
                            j44 += 2;
                            j37 = j;
                            j38 = j46;
                            j40 = j48;
                            j42 = j49;
                            i2 = i2;
                            cos = d9;
                            j39 = j39;
                            j41 = j41;
                        }
                        long j52 = j38;
                        int i3 = i2;
                        long j53 = j39;
                        long j54 = j40;
                        double d12 = cos;
                        DoubleLargeArray doubleLargeArray7 = doubleLargeArray;
                        j41++;
                        j37 = j;
                        cos = d12;
                    }
                    long j55 = j38;
                    int i4 = i2;
                    double d13 = cos;
                    DoubleLargeArray doubleLargeArray8 = doubleLargeArray;
                    j39++;
                    j37 = j;
                    cos = d13;
                }
            }
            d = cos;
            doubleLargeArray3 = doubleLargeArray;
            if (i2 >= 0) {
                long j56 = 1;
                while (j56 < j10) {
                    long j57 = j56 * j3 * j;
                    long j58 = (j2 - j56) * j3 * j;
                    long j59 = 0;
                    while (j59 < j3) {
                        long j60 = j59 * j;
                        long j61 = j60 + j57;
                        long j62 = j60 + j58;
                        long j63 = 2;
                        while (j63 < j) {
                            long j64 = j5 + j63;
                            long j65 = j6 + j63;
                            long j66 = j58;
                            long j67 = j64 + j61;
                            long j68 = j57;
                            long j69 = j64 + j62;
                            long j70 = j56;
                            long j71 = j65 + j61;
                            long j72 = j61;
                            long j73 = j65 + j62;
                            long j74 = j62;
                            double d14 = doubleLargeArray5.getDouble(j71 - 1);
                            double d15 = doubleLargeArray5.getDouble(j71);
                            long j75 = j59;
                            double d16 = doubleLargeArray5.getDouble(j73 - 1);
                            double d17 = doubleLargeArray5.getDouble(j73);
                            long j76 = j69;
                            doubleLargeArray3.setDouble(j67 - 1, d14 + d16);
                            doubleLargeArray3.setDouble(j67, d15 + d17);
                            doubleLargeArray3.setDouble(j76 - 1, d15 - d17);
                            doubleLargeArray3.setDouble(j76, d16 - d14);
                            j63 += 2;
                            j58 = j66;
                            j56 = j70;
                            j62 = j74;
                            j57 = j68;
                            j61 = j72;
                            j59 = j75;
                        }
                        long j77 = j56;
                        long j78 = j58;
                        long j79 = j57;
                        j59++;
                    }
                    j56++;
                }
            } else {
                long j80 = 1;
                while (j80 < j10) {
                    long j81 = j80 * j3 * j;
                    long j82 = (j2 - j80) * j3 * j;
                    long j83 = 2;
                    while (j83 < j) {
                        long j84 = j5 + j83;
                        long j85 = j6 + j83;
                        long j86 = 0;
                        while (j86 < j3) {
                            long j87 = j86 * j;
                            long j88 = j87 + j81;
                            long j89 = j87 + j82;
                            long j90 = j82;
                            long j91 = j84 + j88;
                            long j92 = j81;
                            long j93 = j84 + j89;
                            long j94 = j84;
                            long j95 = j85 + j88;
                            long j96 = j80;
                            long j97 = j85 + j89;
                            long j98 = j85;
                            double d18 = doubleLargeArray5.getDouble(j95 - 1);
                            double d19 = doubleLargeArray5.getDouble(j95);
                            long j99 = j83;
                            double d20 = doubleLargeArray5.getDouble(j97 - 1);
                            double d21 = doubleLargeArray5.getDouble(j97);
                            long j100 = j93;
                            doubleLargeArray3.setDouble(j91 - 1, d18 + d20);
                            doubleLargeArray3.setDouble(j91, d19 + d21);
                            doubleLargeArray3.setDouble(j100 - 1, d19 - d21);
                            doubleLargeArray3.setDouble(j100, d20 - d18);
                            j86++;
                            j82 = j90;
                            j85 = j98;
                            j80 = j96;
                            j81 = j92;
                            j84 = j94;
                            j83 = j99;
                        }
                        long j101 = j80;
                        long j102 = j82;
                        long j103 = j81;
                        j83 += 2;
                    }
                    j80++;
                }
            }
        } else {
            d = cos;
            doubleLargeArray3 = doubleLargeArray4;
            LargeArrayUtils.arraycopy(doubleLargeArray2, j6, doubleLargeArray, j5, j4);
        }
        for (long j104 = 1; j104 < j10; j104++) {
            long j105 = j104 * j3 * j;
            long j106 = (j2 - j104) * j3 * j;
            long j107 = 0;
            while (j107 < j3) {
                long j108 = j107 * j;
                long j109 = j108 + j105;
                long j110 = j108 + j106;
                long j111 = j106;
                double d22 = doubleLargeArray5.getDouble(j6 + j109);
                double d23 = doubleLargeArray5.getDouble(j6 + j110);
                doubleLargeArray3.setDouble(j5 + j109, d22 + d23);
                doubleLargeArray3.setDouble(j5 + j110, d23 - d22);
                j107++;
                j106 = j111;
                j105 = j105;
            }
        }
        long j112 = (j2 - 1) * j4;
        double d24 = 1.0d;
        double d25 = 0.0d;
        long j113 = 1;
        while (j113 < j10) {
            double d26 = (d * d24) - (sin * d25);
            d25 = (d25 * d) + (d24 * sin);
            long j114 = j113 * j4;
            long j115 = (j2 - j113) * j4;
            long j116 = 0;
            while (j116 < j4) {
                long j117 = j6 + j116;
                long j118 = j113;
                long j119 = j5 + j116;
                doubleLargeArray5.setDouble(j117 + j114, doubleLargeArray3.getDouble(j119) + (doubleLargeArray3.getDouble(j119 + j4) * d26));
                doubleLargeArray5.setDouble(j117 + j115, doubleLargeArray3.getDouble(j119 + j112) * d25);
                j116++;
                j113 = j118;
                j114 = j114;
            }
            long j120 = j114;
            long j121 = j113;
            double d27 = d25;
            double d28 = d26;
            long j122 = 2;
            while (j122 < j10) {
                double d29 = (d26 * d28) - (d25 * d27);
                d27 = (d27 * d26) + (d28 * d25);
                long j123 = j122 * j4;
                long j124 = (j2 - j122) * j4;
                long j125 = 0;
                while (j125 < j4) {
                    long j126 = j6 + j125;
                    long j127 = j5 + j125;
                    long j128 = j112;
                    long j129 = j126 + j120;
                    doubleLargeArray5.setDouble(j129, doubleLargeArray5.getDouble(j129) + (doubleLargeArray3.getDouble(j127 + j123) * d29));
                    long j130 = j126 + j115;
                    doubleLargeArray5.setDouble(j130, doubleLargeArray5.getDouble(j130) + (doubleLargeArray3.getDouble(j127 + j124) * d27));
                    j125++;
                    j123 = j123;
                    j112 = j128;
                    d25 = d25;
                }
                long j131 = j112;
                double d30 = d25;
                j122++;
                d28 = d29;
            }
            double d31 = d25;
            j113 = j121 + 1;
            d24 = d26;
            j112 = j112;
        }
        for (long j132 = 1; j132 < j10; j132++) {
            long j133 = j132 * j4;
            for (long j134 = 0; j134 < j4; j134++) {
                long j135 = j6 + j134;
                doubleLargeArray5.setDouble(j135, doubleLargeArray5.getDouble(j135) + doubleLargeArray3.getDouble(j5 + j134 + j133));
            }
        }
        if (j >= j3) {
            for (long j136 = 0; j136 < j3; j136++) {
                long j137 = j136 * j;
                long j138 = j137 * j2;
                for (long j139 = 0; j139 < j; j139++) {
                    doubleLargeArray3.setDouble(j5 + j139 + j138, doubleLargeArray5.getDouble(j6 + j139 + j137));
                }
            }
        } else {
            for (long j140 = 0; j140 < j; j140++) {
                for (long j141 = 0; j141 < j3; j141++) {
                    long j142 = j141 * j;
                    doubleLargeArray3.setDouble(j5 + j140 + (j142 * j2), doubleLargeArray5.getDouble(j6 + j140 + j142));
                }
            }
        }
        long j143 = j2 * j;
        for (long j144 = 1; j144 < j10; j144++) {
            long j145 = j144 * j3 * j;
            long j146 = (j2 - j144) * j3 * j;
            long j147 = j144 * 2 * j;
            long j148 = 0;
            while (j148 < j3) {
                long j149 = j148 * j;
                long j150 = j148 * j143;
                doubleLargeArray3.setDouble(((((j5 + j) - 1) + j147) - j) + j150, doubleLargeArray5.getDouble(j6 + j149 + j145));
                doubleLargeArray3.setDouble(j5 + j147 + j150, doubleLargeArray5.getDouble(j6 + j149 + j146));
                j148++;
                j146 = j146;
                j145 = j145;
            }
        }
        if (i != 0) {
            if (j11 >= j3) {
                long j151 = 1;
                while (j151 < j10) {
                    long j152 = j151 * j3 * j;
                    long j153 = (j2 - j151) * j3 * j;
                    long j154 = j151 * 2 * j;
                    long j155 = 0;
                    while (j155 < j3) {
                        long j156 = j155 * j143;
                        long j157 = j155 * j;
                        long j158 = 2;
                        while (j158 < j) {
                            long j159 = j143;
                            long j160 = j5 + j158 + j154 + j156;
                            long j161 = j154;
                            long j162 = (((j5 + (j - j158)) + j154) - j) + j156;
                            long j163 = j6 + j158 + j157;
                            long j164 = j156;
                            long j165 = j163 + j152;
                            long j166 = j152;
                            long j167 = j163 + j153;
                            long j168 = j153;
                            double d32 = doubleLargeArray5.getDouble(j165 - 1);
                            double d33 = doubleLargeArray5.getDouble(j165);
                            long j169 = j151;
                            double d34 = doubleLargeArray5.getDouble(j167 - 1);
                            double d35 = doubleLargeArray5.getDouble(j167);
                            doubleLargeArray3.setDouble(j160 - 1, d32 + d34);
                            doubleLargeArray3.setDouble(j162 - 1, d32 - d34);
                            doubleLargeArray3.setDouble(j160, d33 + d35);
                            doubleLargeArray3.setDouble(j162, d35 - d33);
                            j158 += 2;
                            j143 = j159;
                            j154 = j161;
                            j156 = j164;
                            j152 = j166;
                            j153 = j168;
                            j151 = j169;
                            j155 = j155;
                        }
                        long j170 = j143;
                        long j171 = j151;
                        long j172 = j153;
                        long j173 = j154;
                        long j174 = j152;
                        j155++;
                    }
                    long j175 = j143;
                    j151++;
                }
                return;
            }
            long j176 = j143;
            long j177 = 1;
            while (j177 < j10) {
                long j178 = j177 * j3 * j;
                long j179 = (j2 - j177) * j3 * j;
                long j180 = j177 * 2 * j;
                long j181 = 2;
                while (j181 < j) {
                    long j182 = j5 + j181;
                    long j183 = j5 + (j - j181);
                    long j184 = j6 + j181;
                    long j185 = 0;
                    while (j185 < j3) {
                        long j186 = j176;
                        long j187 = j185 * j186;
                        long j188 = j182;
                        long j189 = j182 + j180 + j187;
                        long j190 = j180;
                        long j191 = ((j183 + j180) - j) + j187;
                        long j192 = j184 + (j185 * j);
                        long j193 = j183;
                        long j194 = j192 + j178;
                        long j195 = j178;
                        long j196 = j192 + j179;
                        long j197 = j179;
                        double d36 = doubleLargeArray5.getDouble(j194 - 1);
                        double d37 = doubleLargeArray5.getDouble(j194);
                        long j198 = j177;
                        double d38 = doubleLargeArray5.getDouble(j196 - 1);
                        double d39 = doubleLargeArray5.getDouble(j196);
                        doubleLargeArray3.setDouble(j189 - 1, d36 + d38);
                        doubleLargeArray3.setDouble(j191 - 1, d36 - d38);
                        doubleLargeArray3.setDouble(j189, d37 + d39);
                        doubleLargeArray3.setDouble(j191, d39 - d37);
                        j185++;
                        j182 = j188;
                        j180 = j190;
                        j176 = j186;
                        j183 = j193;
                        j179 = j197;
                        j178 = j195;
                        j177 = j198;
                        j181 = j181;
                    }
                    long j199 = j176;
                    long j200 = j177;
                    long j201 = j179;
                    long j202 = j180;
                    long j203 = j178;
                    j181 += 2;
                }
                long j204 = j176;
                j177++;
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0183  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0216  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0231  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x025c A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x025d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void radbg(int r40, int r41, int r42, int r43, double[] r44, int r45, double[] r46, int r47, int r48) {
        /*
            r39 = this;
            r0 = r39
            r1 = r40
            r2 = r41
            r3 = r42
            r4 = r43
            r5 = r44
            r6 = r45
            r7 = r46
            r8 = r47
            r9 = 4618760256179416344(0x401921fb54442d18, double:6.283185307179586)
            double r11 = (double) r2
            double r9 = r9 / r11
            double r11 = org.apache.commons.math3.util.FastMath.cos(r9)
            double r9 = org.apache.commons.math3.util.FastMath.sin(r9)
            int r13 = r1 + -1
            r14 = 2
            int r13 = r13 / r14
            int r15 = r2 + 1
            int r15 = r15 / r14
            int r16 = r2 * r1
            r17 = 0
            if (r1 < r3) goto L_0x004e
            r14 = r17
        L_0x0030:
            if (r14 >= r3) goto L_0x006f
            int r18 = r14 * r1
            int r19 = r14 * r16
            r0 = r17
        L_0x0038:
            if (r0 >= r1) goto L_0x0049
            int r20 = r8 + r0
            int r20 = r20 + r18
            int r21 = r6 + r0
            int r21 = r21 + r19
            r21 = r5[r21]
            r7[r20] = r21
            int r0 = r0 + 1
            goto L_0x0038
        L_0x0049:
            int r14 = r14 + 1
            r0 = r39
            goto L_0x0030
        L_0x004e:
            r0 = r17
        L_0x0050:
            if (r0 >= r1) goto L_0x006f
            int r14 = r8 + r0
            int r18 = r6 + r0
            r19 = r9
            r9 = r17
        L_0x005a:
            if (r9 >= r3) goto L_0x006a
            int r10 = r9 * r1
            int r10 = r10 + r14
            int r21 = r9 * r16
            int r21 = r18 + r21
            r21 = r5[r21]
            r7[r10] = r21
            int r9 = r9 + 1
            goto L_0x005a
        L_0x006a:
            int r0 = r0 + 1
            r9 = r19
            goto L_0x0050
        L_0x006f:
            r19 = r9
            int r0 = r6 + r1
            r9 = 1
            int r0 = r0 - r9
            r10 = r9
        L_0x0076:
            if (r10 >= r15) goto L_0x00af
            int r14 = r2 - r10
            int r16 = r10 * 2
            int r18 = r10 * r3
            int r18 = r18 * r1
            int r14 = r14 * r3
            int r14 = r14 * r1
            int r16 = r16 * r1
            r9 = r17
        L_0x0086:
            if (r9 >= r3) goto L_0x00ab
            int r22 = r9 * r1
            int r23 = r22 * r2
            int r24 = r0 + r16
            int r24 = r24 + r23
            int r24 = r24 - r1
            int r25 = r6 + r16
            int r25 = r25 + r23
            r23 = r5[r24]
            r25 = r5[r25]
            int r22 = r8 + r22
            int r27 = r22 + r18
            double r23 = r23 + r23
            r7[r27] = r23
            int r22 = r22 + r14
            double r25 = r25 + r25
            r7[r22] = r25
            int r9 = r9 + 1
            goto L_0x0086
        L_0x00ab:
            int r10 = r10 + 1
            r9 = 1
            goto L_0x0076
        L_0x00af:
            if (r1 == r9) goto L_0x0177
            if (r13 < r3) goto L_0x0116
            r9 = 1
        L_0x00b4:
            if (r9 >= r15) goto L_0x0177
            int r0 = r2 - r9
            int r10 = r9 * r3
            int r10 = r10 * r1
            int r0 = r0 * r3
            int r0 = r0 * r1
            int r14 = r9 * 2
            int r14 = r14 * r1
            r16 = r13
            r13 = r17
        L_0x00c4:
            if (r13 >= r3) goto L_0x0111
            int r18 = r13 * r1
            int r22 = r18 + r10
            int r18 = r18 + r0
            int r23 = r13 * r2
            int r23 = r23 * r1
            int r23 = r23 + r14
            r24 = r0
            r0 = 2
        L_0x00d5:
            if (r0 >= r1) goto L_0x010c
            int r25 = r1 - r0
            int r26 = r8 + r0
            int r25 = r6 + r25
            int r27 = r6 + r0
            int r28 = r26 + r22
            int r26 = r26 + r18
            int r27 = r27 + r23
            int r25 = r25 + r23
            int r25 = r25 - r1
            int r29 = r27 + -1
            r29 = r5[r29]
            r31 = r5[r27]
            int r27 = r25 + -1
            r33 = r5[r27]
            r35 = r5[r25]
            int r25 = r28 + -1
            double r37 = r29 + r33
            r7[r25] = r37
            int r25 = r26 + -1
            double r29 = r29 - r33
            r7[r25] = r29
            double r29 = r31 - r35
            r7[r28] = r29
            double r31 = r31 + r35
            r7[r26] = r31
            int r0 = r0 + 2
            goto L_0x00d5
        L_0x010c:
            int r13 = r13 + 1
            r0 = r24
            goto L_0x00c4
        L_0x0111:
            int r9 = r9 + 1
            r13 = r16
            goto L_0x00b4
        L_0x0116:
            r16 = r13
            r9 = 1
        L_0x0119:
            if (r9 >= r15) goto L_0x0179
            int r0 = r2 - r9
            int r10 = r9 * r3
            int r10 = r10 * r1
            int r0 = r0 * r3
            int r0 = r0 * r1
            int r13 = r9 * 2
            int r13 = r13 * r1
            r14 = 2
        L_0x0126:
            if (r14 >= r1) goto L_0x0172
            int r18 = r1 - r14
            int r22 = r8 + r14
            int r18 = r6 + r18
            int r23 = r6 + r14
            r8 = r17
        L_0x0132:
            if (r8 >= r3) goto L_0x016d
            int r24 = r8 * r1
            int r25 = r24 + r10
            int r24 = r24 + r0
            int r26 = r8 * r2
            int r26 = r26 * r1
            int r26 = r26 + r13
            int r25 = r22 + r25
            int r24 = r22 + r24
            int r27 = r23 + r26
            int r26 = r18 + r26
            int r26 = r26 - r1
            int r28 = r27 + -1
            r28 = r5[r28]
            r30 = r5[r27]
            int r27 = r26 + -1
            r32 = r5[r27]
            r26 = r5[r26]
            int r34 = r25 + -1
            double r35 = r28 + r32
            r7[r34] = r35
            int r34 = r24 + -1
            double r28 = r28 - r32
            r7[r34] = r28
            double r28 = r30 - r26
            r7[r25] = r28
            double r30 = r30 + r26
            r7[r24] = r30
            int r8 = r8 + 1
            goto L_0x0132
        L_0x016d:
            int r14 = r14 + 2
            r8 = r47
            goto L_0x0126
        L_0x0172:
            int r9 = r9 + 1
            r8 = r47
            goto L_0x0119
        L_0x0177:
            r16 = r13
        L_0x0179:
            int r0 = r2 + -1
            int r0 = r0 * r4
            r8 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r13 = 0
            r10 = 1
        L_0x0181:
            if (r10 >= r15) goto L_0x0211
            int r18 = r2 - r10
            double r22 = r11 * r8
            double r24 = r19 * r13
            double r22 = r22 - r24
            double r13 = r13 * r11
            double r8 = r8 * r19
            double r13 = r13 + r8
            int r8 = r10 * r4
            int r18 = r18 * r4
            r9 = r17
        L_0x0195:
            if (r9 >= r4) goto L_0x01bb
            int r24 = r6 + r9
            r25 = r11
            r11 = r47
            int r12 = r11 + r9
            int r27 = r24 + r8
            r28 = r7[r12]
            int r30 = r12 + r4
            r30 = r7[r30]
            double r30 = r30 * r22
            double r28 = r28 + r30
            r5[r27] = r28
            int r24 = r24 + r18
            int r12 = r12 + r0
            r27 = r7[r12]
            double r27 = r27 * r13
            r5[r24] = r27
            int r9 = r9 + 1
            r11 = r25
            goto L_0x0195
        L_0x01bb:
            r25 = r11
            r11 = r47
            r29 = r13
            r27 = r22
            r9 = 2
        L_0x01c4:
            if (r9 >= r15) goto L_0x0207
            int r12 = r2 - r9
            double r31 = r22 * r27
            double r33 = r13 * r29
            double r31 = r31 - r33
            double r29 = r29 * r22
            double r27 = r27 * r13
            double r29 = r29 + r27
            int r24 = r9 * r4
            int r12 = r12 * r4
            r27 = r0
            r0 = r17
        L_0x01db:
            if (r0 >= r4) goto L_0x0200
            int r28 = r6 + r0
            int r33 = r11 + r0
            int r34 = r28 + r8
            r35 = r5[r34]
            int r37 = r33 + r24
            r37 = r7[r37]
            double r37 = r37 * r31
            double r35 = r35 + r37
            r5[r34] = r35
            int r28 = r28 + r18
            r34 = r5[r28]
            int r33 = r33 + r12
            r36 = r7[r33]
            double r36 = r36 * r29
            double r34 = r34 + r36
            r5[r28] = r34
            int r0 = r0 + 1
            goto L_0x01db
        L_0x0200:
            int r9 = r9 + 1
            r0 = r27
            r27 = r31
            goto L_0x01c4
        L_0x0207:
            r27 = r0
            int r10 = r10 + 1
            r8 = r22
            r11 = r25
            goto L_0x0181
        L_0x0211:
            r11 = r47
            r9 = 1
        L_0x0214:
            if (r9 >= r15) goto L_0x022e
            int r0 = r9 * r4
            r8 = r17
        L_0x021a:
            if (r8 >= r4) goto L_0x022b
            int r10 = r11 + r8
            r12 = r7[r10]
            int r14 = r10 + r0
            r18 = r7[r14]
            double r12 = r12 + r18
            r7[r10] = r12
            int r8 = r8 + 1
            goto L_0x021a
        L_0x022b:
            int r9 = r9 + 1
            goto L_0x0214
        L_0x022e:
            r9 = 1
        L_0x022f:
            if (r9 >= r15) goto L_0x0259
            int r0 = r2 - r9
            int r8 = r9 * r3
            int r8 = r8 * r1
            int r0 = r0 * r3
            int r0 = r0 * r1
            r10 = r17
        L_0x023a:
            if (r10 >= r3) goto L_0x0256
            int r12 = r10 * r1
            int r13 = r11 + r12
            int r12 = r12 + r6
            int r14 = r12 + r8
            int r12 = r12 + r0
            r18 = r5[r14]
            r22 = r5[r12]
            int r12 = r13 + r8
            double r24 = r18 - r22
            r7[r12] = r24
            int r13 = r13 + r0
            double r18 = r18 + r22
            r7[r13] = r18
            int r10 = r10 + 1
            goto L_0x023a
        L_0x0256:
            int r9 = r9 + 1
            goto L_0x022f
        L_0x0259:
            r9 = 1
            if (r1 != r9) goto L_0x025d
            return
        L_0x025d:
            r13 = r16
            if (r13 < r3) goto L_0x02af
            r0 = r9
        L_0x0262:
            if (r0 >= r15) goto L_0x0301
            int r8 = r2 - r0
            int r10 = r0 * r3
            int r10 = r10 * r1
            int r8 = r8 * r3
            int r8 = r8 * r1
            r12 = r17
        L_0x026d:
            if (r12 >= r3) goto L_0x02ab
            int r14 = r12 * r1
            r9 = 2
        L_0x0272:
            if (r9 >= r1) goto L_0x02a7
            int r16 = r11 + r9
            int r18 = r6 + r9
            int r16 = r16 + r14
            int r19 = r16 + r10
            int r16 = r16 + r8
            int r18 = r18 + r14
            int r20 = r18 + r10
            int r18 = r18 + r8
            int r22 = r20 + -1
            r22 = r5[r22]
            r24 = r5[r20]
            int r20 = r18 + -1
            r26 = r5[r20]
            r28 = r5[r18]
            int r18 = r19 + -1
            double r30 = r22 - r28
            r7[r18] = r30
            int r18 = r16 + -1
            double r22 = r22 + r28
            r7[r18] = r22
            double r22 = r24 + r26
            r7[r19] = r22
            double r24 = r24 - r26
            r7[r16] = r24
            int r9 = r9 + 2
            goto L_0x0272
        L_0x02a7:
            int r12 = r12 + 1
            r9 = 1
            goto L_0x026d
        L_0x02ab:
            int r0 = r0 + 1
            r9 = 1
            goto L_0x0262
        L_0x02af:
            r9 = 1
        L_0x02b0:
            if (r9 >= r15) goto L_0x0301
            int r0 = r2 - r9
            int r8 = r9 * r3
            int r8 = r8 * r1
            int r0 = r0 * r3
            int r0 = r0 * r1
            r10 = 2
        L_0x02ba:
            if (r10 >= r1) goto L_0x02fc
            int r12 = r11 + r10
            int r14 = r6 + r10
            r16 = r15
            r15 = r17
        L_0x02c4:
            if (r15 >= r3) goto L_0x02f7
            int r18 = r15 * r1
            int r19 = r12 + r18
            int r20 = r19 + r8
            int r19 = r19 + r0
            int r18 = r14 + r18
            int r22 = r18 + r8
            int r18 = r18 + r0
            int r23 = r22 + -1
            r23 = r5[r23]
            r25 = r5[r22]
            int r22 = r18 + -1
            r27 = r5[r22]
            r29 = r5[r18]
            int r18 = r20 + -1
            double r31 = r23 - r29
            r7[r18] = r31
            int r18 = r19 + -1
            double r23 = r23 + r29
            r7[r18] = r23
            double r22 = r25 + r27
            r7[r20] = r22
            double r25 = r25 - r27
            r7[r19] = r25
            int r15 = r15 + 1
            goto L_0x02c4
        L_0x02f7:
            int r10 = r10 + 2
            r15 = r16
            goto L_0x02ba
        L_0x02fc:
            r16 = r15
            int r9 = r9 + 1
            goto L_0x02b0
        L_0x0301:
            java.lang.System.arraycopy(r7, r11, r5, r6, r4)
            r9 = 1
        L_0x0305:
            if (r9 >= r2) goto L_0x031e
            int r0 = r9 * r3
            int r0 = r0 * r1
            r4 = r17
        L_0x030c:
            if (r4 >= r3) goto L_0x031b
            int r8 = r4 * r1
            int r8 = r8 + r0
            int r10 = r6 + r8
            int r8 = r8 + r11
            r14 = r7[r8]
            r5[r10] = r14
            int r4 = r4 + 1
            goto L_0x030c
        L_0x031b:
            int r9 = r9 + 1
            goto L_0x0305
        L_0x031e:
            if (r13 > r3) goto L_0x0377
            int r0 = -r1
            r9 = 1
        L_0x0322:
            if (r9 >= r2) goto L_0x0374
            int r0 = r0 + r1
            int r4 = r0 + -1
            int r8 = r9 * r3
            int r8 = r8 * r1
            r10 = 2
        L_0x032b:
            if (r10 >= r1) goto L_0x036d
            r12 = 2
            int r4 = r4 + r12
            int r12 = r4 + r48
            r13 = r39
            double[] r14 = r13.wtable_r
            int r15 = r12 + -1
            r15 = r14[r15]
            r18 = r14[r12]
            int r12 = r6 + r10
            int r14 = r11 + r10
            r43 = r0
            r0 = r17
        L_0x0343:
            if (r0 >= r3) goto L_0x0368
            int r20 = r0 * r1
            int r20 = r20 + r8
            int r21 = r12 + r20
            int r20 = r14 + r20
            int r22 = r20 + -1
            r22 = r7[r22]
            r24 = r7[r20]
            int r20 = r21 + -1
            double r26 = r15 * r22
            double r28 = r18 * r24
            double r26 = r26 - r28
            r5[r20] = r26
            double r24 = r24 * r15
            double r22 = r22 * r18
            double r24 = r24 + r22
            r5[r21] = r24
            int r0 = r0 + 1
            goto L_0x0343
        L_0x0368:
            int r10 = r10 + 2
            r0 = r43
            goto L_0x032b
        L_0x036d:
            r13 = r39
            r43 = r0
            int r9 = r9 + 1
            goto L_0x0322
        L_0x0374:
            r13 = r39
            goto L_0x03c1
        L_0x0377:
            r13 = r39
            int r0 = -r1
            r9 = 1
        L_0x037b:
            if (r9 >= r2) goto L_0x03c1
            int r0 = r0 + r1
            int r4 = r9 * r3
            int r4 = r4 * r1
            r8 = r17
        L_0x0383:
            if (r8 >= r3) goto L_0x03be
            int r10 = r0 + -1
            int r12 = r8 * r1
            int r12 = r12 + r4
            r14 = 2
        L_0x038b:
            if (r14 >= r1) goto L_0x03bb
            r15 = 2
            int r10 = r10 + r15
            int r16 = r10 + r48
            double[] r15 = r13.wtable_r
            int r18 = r16 + -1
            r18 = r15[r18]
            r20 = r15[r16]
            int r15 = r6 + r14
            int r16 = r11 + r14
            int r15 = r15 + r12
            int r16 = r16 + r12
            int r22 = r16 + -1
            r22 = r7[r22]
            r24 = r7[r16]
            int r16 = r15 + -1
            double r26 = r18 * r22
            double r28 = r20 * r24
            double r26 = r26 - r28
            r5[r16] = r26
            double r18 = r18 * r24
            double r20 = r20 * r22
            double r18 = r18 + r20
            r5[r15] = r18
            int r14 = r14 + 2
            goto L_0x038b
        L_0x03bb:
            int r8 = r8 + 1
            goto L_0x0383
        L_0x03be:
            int r9 = r9 + 1
            goto L_0x037b
        L_0x03c1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.DoubleFFT_1D.radbg(int, int, int, int, double[], int, double[], int, int):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0247  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x030e  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0334  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0377 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x0378  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void radbg(long r53, long r55, long r57, long r59, pl.edu.icm.jlargearrays.DoubleLargeArray r61, long r62, pl.edu.icm.jlargearrays.DoubleLargeArray r64, long r65, long r67) {
        /*
            r52 = this;
            r0 = r52
            r1 = r53
            r3 = r55
            r13 = r61
            r14 = r64
            r5 = 4618760256179416344(0x401921fb54442d18, double:6.283185307179586)
            double r7 = (double) r3
            double r5 = r5 / r7
            double r7 = org.apache.commons.math3.util.FastMath.cos(r5)
            double r5 = org.apache.commons.math3.util.FastMath.sin(r5)
            r15 = 1
            long r9 = r1 - r15
            r17 = 2
            long r9 = r9 / r17
            long r11 = r3 + r15
            long r11 = r11 / r17
            long r19 = r3 * r1
            int r21 = (r1 > r57 ? 1 : (r1 == r57 ? 0 : -1))
            r22 = 0
            if (r21 < 0) goto L_0x0063
            r24 = r22
        L_0x002f:
            int r21 = (r24 > r57 ? 1 : (r24 == r57 ? 0 : -1))
            if (r21 >= 0) goto L_0x005e
            long r26 = r24 * r1
            long r28 = r24 * r19
            r30 = r22
        L_0x0039:
            int r21 = (r30 > r1 ? 1 : (r30 == r1 ? 0 : -1))
            if (r21 >= 0) goto L_0x0057
            long r32 = r65 + r30
            r34 = r5
            long r5 = r32 + r26
            long r32 = r62 + r30
            r36 = r7
            long r7 = r32 + r28
            double r7 = r13.getDouble(r7)
            r14.setDouble(r5, r7)
            long r30 = r30 + r15
            r5 = r34
            r7 = r36
            goto L_0x0039
        L_0x0057:
            r34 = r5
            r36 = r7
            long r24 = r24 + r15
            goto L_0x002f
        L_0x005e:
            r34 = r5
            r36 = r7
            goto L_0x0095
        L_0x0063:
            r34 = r5
            r36 = r7
            r5 = r22
        L_0x0069:
            int r7 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r7 >= 0) goto L_0x0095
            long r7 = r65 + r5
            long r24 = r62 + r5
            r26 = r22
        L_0x0073:
            int r21 = (r26 > r57 ? 1 : (r26 == r57 ? 0 : -1))
            if (r21 >= 0) goto L_0x0091
            long r28 = r26 * r1
            r30 = r9
            long r9 = r7 + r28
            long r28 = r26 * r19
            r32 = r7
            long r7 = r24 + r28
            double r7 = r13.getDouble(r7)
            r14.setDouble(r9, r7)
            long r26 = r26 + r15
            r9 = r30
            r7 = r32
            goto L_0x0073
        L_0x0091:
            r30 = r9
            long r5 = r5 + r15
            goto L_0x0069
        L_0x0095:
            r30 = r9
            long r5 = r62 + r1
            long r5 = r5 - r15
            r7 = r15
        L_0x009b:
            int r9 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
            if (r9 >= 0) goto L_0x00eb
            long r9 = r3 - r7
            long r19 = r7 * r17
            long r24 = r7 * r57
            long r24 = r24 * r1
            long r9 = r9 * r57
            long r9 = r9 * r1
            long r19 = r19 * r1
            r26 = r22
        L_0x00ae:
            int r21 = (r26 > r57 ? 1 : (r26 == r57 ? 0 : -1))
            if (r21 >= 0) goto L_0x00e3
            long r28 = r26 * r1
            long r32 = r28 * r3
            long r38 = r5 + r19
            long r38 = r38 + r32
            r40 = r5
            long r5 = r38 - r1
            long r38 = r62 + r19
            long r3 = r38 + r32
            double r5 = r13.getDouble(r5)
            double r3 = r13.getDouble(r3)
            long r28 = r65 + r28
            r32 = r11
            long r11 = r28 + r24
            double r5 = r5 + r5
            r14.setDouble(r11, r5)
            long r5 = r28 + r9
            double r3 = r3 + r3
            r14.setDouble(r5, r3)
            long r26 = r26 + r15
            r3 = r55
            r11 = r32
            r5 = r40
            goto L_0x00ae
        L_0x00e3:
            r40 = r5
            r32 = r11
            long r7 = r7 + r15
            r3 = r55
            goto L_0x009b
        L_0x00eb:
            r32 = r11
            int r3 = (r1 > r15 ? 1 : (r1 == r15 ? 0 : -1))
            if (r3 == 0) goto L_0x0238
            int r4 = (r30 > r57 ? 1 : (r30 == r57 ? 0 : -1))
            if (r4 < 0) goto L_0x019c
            r4 = r15
        L_0x00f6:
            int r6 = (r4 > r32 ? 1 : (r4 == r32 ? 0 : -1))
            if (r6 >= 0) goto L_0x0238
            long r6 = r55 - r4
            long r8 = r4 * r57
            long r8 = r8 * r1
            long r6 = r6 * r57
            long r6 = r6 * r1
            long r10 = r4 * r17
            long r10 = r10 * r1
            r19 = r22
        L_0x0107:
            int r12 = (r19 > r57 ? 1 : (r19 == r57 ? 0 : -1))
            if (r12 >= 0) goto L_0x018e
            long r24 = r19 * r1
            long r26 = r24 + r8
            long r24 = r24 + r6
            long r28 = r19 * r55
            long r28 = r28 * r1
            long r28 = r28 + r10
            r38 = r17
        L_0x0119:
            int r12 = (r38 > r1 ? 1 : (r38 == r1 ? 0 : -1))
            if (r12 >= 0) goto L_0x017a
            long r40 = r1 - r38
            long r42 = r65 + r38
            long r40 = r62 + r40
            long r44 = r62 + r38
            r46 = r6
            long r6 = r42 + r26
            r48 = r8
            long r8 = r42 + r24
            r42 = r10
            long r10 = r44 + r28
            long r40 = r40 + r28
            r12 = r3
            r44 = r4
            long r3 = r40 - r1
            long r0 = r10 - r15
            double r0 = r13.getDouble(r0)
            double r10 = r13.getDouble(r10)
            r40 = r10
            long r10 = r3 - r15
            double r10 = r13.getDouble(r10)
            double r2 = r13.getDouble(r3)
            long r4 = r6 - r15
            r21 = r12
            double r12 = r0 + r10
            r14.setDouble(r4, r12)
            long r4 = r8 - r15
            double r0 = r0 - r10
            r14.setDouble(r4, r0)
            double r10 = r40 - r2
            r14.setDouble(r6, r10)
            double r10 = r40 + r2
            r14.setDouble(r8, r10)
            long r38 = r38 + r17
            r0 = r52
            r1 = r53
            r13 = r61
            r3 = r21
            r10 = r42
            r4 = r44
            r6 = r46
            r8 = r48
            goto L_0x0119
        L_0x017a:
            r21 = r3
            r44 = r4
            r46 = r6
            r48 = r8
            r42 = r10
            long r19 = r19 + r15
            r0 = r52
            r1 = r53
            r13 = r61
            goto L_0x0107
        L_0x018e:
            r21 = r3
            r44 = r4
            long r4 = r44 + r15
            r0 = r52
            r1 = r53
            r13 = r61
            goto L_0x00f6
        L_0x019c:
            r21 = r3
            r0 = r15
        L_0x019f:
            int r2 = (r0 > r32 ? 1 : (r0 == r32 ? 0 : -1))
            if (r2 >= 0) goto L_0x023a
            long r2 = r55 - r0
            long r4 = r0 * r57
            r11 = r53
            long r4 = r4 * r11
            long r2 = r2 * r57
            long r2 = r2 * r11
            long r6 = r0 * r17
            long r6 = r6 * r11
            r8 = r17
        L_0x01b2:
            int r10 = (r8 > r11 ? 1 : (r8 == r11 ? 0 : -1))
            if (r10 >= 0) goto L_0x0232
            long r19 = r11 - r8
            long r24 = r65 + r8
            long r19 = r62 + r19
            long r26 = r62 + r8
            r28 = r22
        L_0x01c0:
            int r10 = (r28 > r57 ? 1 : (r28 == r57 ? 0 : -1))
            if (r10 >= 0) goto L_0x0223
            long r38 = r28 * r11
            long r40 = r38 + r4
            long r38 = r38 + r2
            long r42 = r28 * r55
            long r42 = r42 * r11
            long r42 = r42 + r6
            r44 = r2
            long r2 = r24 + r40
            r40 = r4
            long r4 = r24 + r38
            r38 = r6
            long r6 = r26 + r42
            long r42 = r19 + r42
            r46 = r0
            long r0 = r42 - r11
            long r10 = r6 - r15
            r13 = r61
            double r10 = r13.getDouble(r10)
            double r6 = r13.getDouble(r6)
            r42 = r8
            long r8 = r0 - r15
            double r8 = r13.getDouble(r8)
            double r0 = r13.getDouble(r0)
            long r12 = r2 - r15
            r48 = r2
            double r2 = r10 + r8
            r14.setDouble(r12, r2)
            long r2 = r4 - r15
            double r10 = r10 - r8
            r14.setDouble(r2, r10)
            double r2 = r6 - r0
            r8 = r48
            r14.setDouble(r8, r2)
            double r6 = r6 + r0
            r14.setDouble(r4, r6)
            long r28 = r28 + r15
            r11 = r53
            r6 = r38
            r4 = r40
            r8 = r42
            r2 = r44
            r0 = r46
            goto L_0x01c0
        L_0x0223:
            r46 = r0
            r44 = r2
            r40 = r4
            r38 = r6
            r42 = r8
            long r8 = r42 + r17
            r11 = r53
            goto L_0x01b2
        L_0x0232:
            r46 = r0
            long r0 = r46 + r15
            goto L_0x019f
        L_0x0238:
            r21 = r3
        L_0x023a:
            long r0 = r55 - r15
            long r0 = r0 * r59
            r2 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r4 = 0
            r6 = r15
        L_0x0243:
            int r8 = (r6 > r32 ? 1 : (r6 == r32 ? 0 : -1))
            if (r8 >= 0) goto L_0x0306
            long r8 = r55 - r6
            double r10 = r36 * r2
            double r12 = r34 * r4
            double r10 = r10 - r12
            double r4 = r4 * r36
            double r2 = r2 * r34
            double r4 = r4 + r2
            long r2 = r6 * r59
            long r8 = r8 * r59
            r12 = r22
        L_0x0259:
            int r19 = (r12 > r59 ? 1 : (r12 == r59 ? 0 : -1))
            if (r19 >= 0) goto L_0x0290
            long r19 = r62 + r12
            r24 = r6
            long r6 = r65 + r12
            r26 = r12
            long r12 = r19 + r2
            double r28 = r14.getDouble(r6)
            r38 = r2
            long r2 = r6 + r59
            double r2 = r14.getDouble(r2)
            double r2 = r2 * r10
            double r2 = r28 + r2
            r15 = r61
            r15.setDouble(r12, r2)
            long r2 = r19 + r8
            long r6 = r6 + r0
            double r6 = r14.getDouble(r6)
            double r6 = r6 * r4
            r15.setDouble(r2, r6)
            r2 = 1
            long r12 = r26 + r2
            r15 = r2
            r6 = r24
            r2 = r38
            goto L_0x0259
        L_0x0290:
            r15 = r61
            r38 = r2
            r24 = r6
            r12 = r4
            r6 = r10
            r2 = r17
        L_0x029a:
            int r16 = (r2 > r32 ? 1 : (r2 == r32 ? 0 : -1))
            if (r16 >= 0) goto L_0x02f8
            long r19 = r55 - r2
            double r26 = r10 * r6
            double r40 = r4 * r12
            double r26 = r26 - r40
            double r12 = r12 * r10
            double r6 = r6 * r4
            double r12 = r12 + r6
            long r6 = r2 * r59
            long r19 = r19 * r59
            r40 = r22
        L_0x02af:
            int r16 = (r40 > r59 ? 1 : (r40 == r59 ? 0 : -1))
            if (r16 >= 0) goto L_0x02ec
            long r42 = r62 + r40
            long r44 = r65 + r40
            r46 = r0
            long r0 = r42 + r38
            double r48 = r15.getDouble(r0)
            r50 = r4
            long r4 = r44 + r6
            double r4 = r14.getDouble(r4)
            double r4 = r4 * r26
            double r4 = r48 + r4
            r15.setDouble(r0, r4)
            long r0 = r42 + r8
            double r4 = r15.getDouble(r0)
            r42 = r6
            long r6 = r44 + r19
            double r6 = r14.getDouble(r6)
            double r6 = r6 * r12
            double r4 = r4 + r6
            r15.setDouble(r0, r4)
            r0 = 1
            long r40 = r40 + r0
            r6 = r42
            r0 = r46
            r4 = r50
            goto L_0x02af
        L_0x02ec:
            r46 = r0
            r50 = r4
            r0 = 1
            long r2 = r2 + r0
            r6 = r26
            r0 = r46
            goto L_0x029a
        L_0x02f8:
            r46 = r0
            r50 = r4
            r0 = 1
            long r6 = r24 + r0
            r15 = r0
            r2 = r10
            r0 = r46
            goto L_0x0243
        L_0x0306:
            r15 = r61
            r0 = 1
        L_0x030a:
            int r2 = (r0 > r32 ? 1 : (r0 == r32 ? 0 : -1))
            if (r2 >= 0) goto L_0x032e
            long r2 = r0 * r59
            r4 = r22
        L_0x0312:
            int r6 = (r4 > r59 ? 1 : (r4 == r59 ? 0 : -1))
            if (r6 >= 0) goto L_0x032a
            long r6 = r65 + r4
            double r8 = r14.getDouble(r6)
            long r10 = r6 + r2
            double r10 = r14.getDouble(r10)
            double r8 = r8 + r10
            r14.setDouble(r6, r8)
            r6 = 1
            long r4 = r4 + r6
            goto L_0x0312
        L_0x032a:
            r6 = 1
            long r0 = r0 + r6
            goto L_0x030a
        L_0x032e:
            r0 = 1
        L_0x0330:
            int r2 = (r0 > r32 ? 1 : (r0 == r32 ? 0 : -1))
            if (r2 >= 0) goto L_0x0375
            long r2 = r55 - r0
            long r4 = r0 * r57
            long r4 = r4 * r53
            long r2 = r2 * r57
            long r2 = r2 * r53
            r6 = r22
        L_0x0340:
            int r8 = (r6 > r57 ? 1 : (r6 == r57 ? 0 : -1))
            if (r8 >= 0) goto L_0x036d
            long r8 = r6 * r53
            long r10 = r65 + r8
            long r8 = r62 + r8
            long r12 = r8 + r4
            long r8 = r8 + r2
            double r12 = r15.getDouble(r12)
            double r8 = r15.getDouble(r8)
            r19 = r0
            long r0 = r10 + r4
            r24 = r4
            double r4 = r12 - r8
            r14.setDouble(r0, r4)
            long r10 = r10 + r2
            double r12 = r12 + r8
            r14.setDouble(r10, r12)
            r0 = 1
            long r6 = r6 + r0
            r0 = r19
            r4 = r24
            goto L_0x0340
        L_0x036d:
            r19 = r0
            r0 = 1
            long r2 = r19 + r0
            r0 = r2
            goto L_0x0330
        L_0x0375:
            if (r21 != 0) goto L_0x0378
            return
        L_0x0378:
            int r0 = (r30 > r57 ? 1 : (r30 == r57 ? 0 : -1))
            if (r0 < 0) goto L_0x0413
            r1 = 1
        L_0x037e:
            int r3 = (r1 > r32 ? 1 : (r1 == r32 ? 0 : -1))
            if (r3 >= 0) goto L_0x040f
            long r3 = r55 - r1
            long r5 = r1 * r57
            long r5 = r5 * r53
            long r3 = r3 * r57
            long r3 = r3 * r53
            r7 = r22
        L_0x038e:
            int r9 = (r7 > r57 ? 1 : (r7 == r57 ? 0 : -1))
            if (r9 >= 0) goto L_0x0406
            long r9 = r7 * r53
            r11 = r17
        L_0x0396:
            int r13 = (r11 > r53 ? 1 : (r11 == r53 ? 0 : -1))
            if (r13 >= 0) goto L_0x03f8
            long r19 = r65 + r11
            long r24 = r62 + r11
            long r19 = r19 + r9
            r13 = r0
            r26 = r1
            long r0 = r19 + r5
            r30 = r7
            long r7 = r19 + r3
            long r24 = r24 + r9
            r19 = r9
            long r9 = r24 + r5
            r34 = r5
            long r5 = r24 + r3
            r28 = r3
            r24 = 1
            long r2 = r9 - r24
            double r2 = r15.getDouble(r2)
            double r9 = r15.getDouble(r9)
            r36 = r11
            long r11 = r5 - r24
            double r11 = r15.getDouble(r11)
            double r4 = r15.getDouble(r5)
            r38 = r9
            long r9 = r0 - r24
            r40 = r0
            double r0 = r2 - r4
            r14.setDouble(r9, r0)
            long r0 = r7 - r24
            double r2 = r2 + r4
            r14.setDouble(r0, r2)
            double r9 = r38 + r11
            r0 = r40
            r14.setDouble(r0, r9)
            double r9 = r38 - r11
            r14.setDouble(r7, r9)
            long r11 = r36 + r17
            r0 = r13
            r9 = r19
            r1 = r26
            r3 = r28
            r7 = r30
            r5 = r34
            goto L_0x0396
        L_0x03f8:
            r13 = r0
            r26 = r1
            r28 = r3
            r34 = r5
            r30 = r7
            r24 = 1
            long r7 = r30 + r24
            goto L_0x038e
        L_0x0406:
            r13 = r0
            r26 = r1
            r24 = 1
            long r1 = r26 + r24
            goto L_0x037e
        L_0x040f:
            r16 = r0
            goto L_0x04ac
        L_0x0413:
            r13 = r0
            r0 = 1
        L_0x0416:
            int r2 = (r0 > r32 ? 1 : (r0 == r32 ? 0 : -1))
            if (r2 >= 0) goto L_0x04aa
            long r2 = r55 - r0
            long r4 = r0 * r57
            long r4 = r4 * r53
            long r2 = r2 * r57
            long r2 = r2 * r53
            r6 = r17
        L_0x0426:
            int r8 = (r6 > r53 ? 1 : (r6 == r53 ? 0 : -1))
            if (r8 >= 0) goto L_0x04a0
            long r8 = r65 + r6
            long r10 = r62 + r6
            r19 = r22
        L_0x0430:
            int r12 = (r19 > r57 ? 1 : (r19 == r57 ? 0 : -1))
            if (r12 >= 0) goto L_0x0491
            long r24 = r19 * r53
            long r26 = r8 + r24
            r30 = r8
            long r8 = r26 + r4
            r16 = r13
            long r12 = r26 + r2
            long r24 = r10 + r24
            r26 = r10
            long r10 = r24 + r4
            r34 = r4
            long r4 = r24 + r2
            r28 = r2
            r24 = 1
            long r2 = r10 - r24
            double r2 = r15.getDouble(r2)
            double r10 = r15.getDouble(r10)
            r36 = r0
            long r0 = r4 - r24
            double r0 = r15.getDouble(r0)
            double r4 = r15.getDouble(r4)
            r38 = r6
            long r6 = r8 - r24
            r40 = r8
            double r8 = r2 - r4
            r14.setDouble(r6, r8)
            long r6 = r12 - r24
            double r2 = r2 + r4
            r14.setDouble(r6, r2)
            double r2 = r10 + r0
            r4 = r40
            r14.setDouble(r4, r2)
            double r10 = r10 - r0
            r14.setDouble(r12, r10)
            long r19 = r19 + r24
            r13 = r16
            r10 = r26
            r2 = r28
            r8 = r30
            r4 = r34
            r0 = r36
            r6 = r38
            goto L_0x0430
        L_0x0491:
            r36 = r0
            r28 = r2
            r34 = r4
            r38 = r6
            r16 = r13
            r24 = 1
            long r6 = r38 + r17
            goto L_0x0426
        L_0x04a0:
            r36 = r0
            r16 = r13
            r24 = 1
            long r0 = r36 + r24
            goto L_0x0416
        L_0x04aa:
            r16 = r13
        L_0x04ac:
            r5 = r64
            r6 = r65
            r8 = r61
            r9 = r62
            r0 = r53
            r11 = r59
            pl.edu.icm.jlargearrays.LargeArrayUtils.arraycopy((pl.edu.icm.jlargearrays.DoubleLargeArray) r5, (long) r6, (pl.edu.icm.jlargearrays.DoubleLargeArray) r8, (long) r9, (long) r11)
            r2 = 1
        L_0x04bd:
            int r4 = (r2 > r55 ? 1 : (r2 == r55 ? 0 : -1))
            if (r4 >= 0) goto L_0x04e2
            long r4 = r2 * r57
            long r4 = r4 * r0
            r6 = r22
        L_0x04c6:
            int r8 = (r6 > r57 ? 1 : (r6 == r57 ? 0 : -1))
            if (r8 >= 0) goto L_0x04dd
            long r8 = r6 * r0
            long r8 = r8 + r4
            long r10 = r62 + r8
            long r8 = r65 + r8
            double r8 = r14.getDouble(r8)
            r15.setDouble(r10, r8)
            r28 = 1
            long r6 = r6 + r28
            goto L_0x04c6
        L_0x04dd:
            r28 = 1
            long r2 = r2 + r28
            goto L_0x04bd
        L_0x04e2:
            r28 = 1
            if (r16 > 0) goto L_0x0570
            long r2 = -r0
            r4 = r28
        L_0x04e9:
            int r6 = (r4 > r55 ? 1 : (r4 == r55 ? 0 : -1))
            if (r6 >= 0) goto L_0x0613
            long r2 = r2 + r0
            long r6 = r2 - r28
            long r8 = r4 * r57
            long r8 = r8 * r0
            r10 = r17
        L_0x04f5:
            int r12 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r12 >= 0) goto L_0x0564
            long r6 = r6 + r17
            long r12 = r6 + r67
            r59 = r2
            r2 = r52
            pl.edu.icm.jlargearrays.DoubleLargeArray r3 = r2.wtable_rl
            r19 = r6
            long r6 = r12 - r28
            double r6 = r3.getDouble(r6)
            pl.edu.icm.jlargearrays.DoubleLargeArray r3 = r2.wtable_rl
            double r12 = r3.getDouble(r12)
            long r24 = r62 + r10
            long r26 = r65 + r10
            r30 = r22
        L_0x0517:
            int r3 = (r30 > r57 ? 1 : (r30 == r57 ? 0 : -1))
            if (r3 >= 0) goto L_0x0553
            long r32 = r30 * r0
            long r32 = r32 + r8
            r34 = r8
            long r8 = r24 + r32
            long r2 = r26 + r32
            r28 = 1
            long r0 = r2 - r28
            double r0 = r14.getDouble(r0)
            double r2 = r14.getDouble(r2)
            r32 = r4
            long r4 = r8 - r28
            double r36 = r6 * r0
            double r38 = r12 * r2
            r40 = r10
            double r10 = r36 - r38
            r15.setDouble(r4, r10)
            double r2 = r2 * r6
            double r0 = r0 * r12
            double r2 = r2 + r0
            r15.setDouble(r8, r2)
            long r30 = r30 + r28
            r2 = r52
            r0 = r53
            r4 = r32
            r8 = r34
            r10 = r40
            goto L_0x0517
        L_0x0553:
            r32 = r4
            r34 = r8
            r40 = r10
            r28 = 1
            long r10 = r40 + r17
            r0 = r53
            r2 = r59
            r6 = r19
            goto L_0x04f5
        L_0x0564:
            r59 = r2
            r32 = r4
            long r4 = r32 + r28
            r0 = r53
            r28 = 1
            goto L_0x04e9
        L_0x0570:
            long r2 = -r0
            r4 = r2
            r2 = 1
        L_0x0574:
            int r6 = (r2 > r55 ? 1 : (r2 == r55 ? 0 : -1))
            if (r6 >= 0) goto L_0x0613
            long r4 = r4 + r0
            long r6 = r2 * r57
            long r6 = r6 * r0
            r8 = r22
        L_0x057e:
            int r10 = (r8 > r57 ? 1 : (r8 == r57 ? 0 : -1))
            if (r10 >= 0) goto L_0x0600
            r10 = 1
            long r12 = r4 - r10
            long r19 = r8 * r0
            long r19 = r19 + r6
            r24 = r17
        L_0x058c:
            int r16 = (r24 > r0 ? 1 : (r24 == r0 ? 0 : -1))
            if (r16 >= 0) goto L_0x05e7
            long r12 = r12 + r17
            long r0 = r12 + r67
            r59 = r4
            r4 = r52
            pl.edu.icm.jlargearrays.DoubleLargeArray r5 = r4.wtable_rl
            r26 = r6
            long r6 = r0 - r10
            double r5 = r5.getDouble(r6)
            pl.edu.icm.jlargearrays.DoubleLargeArray r7 = r4.wtable_rl
            double r0 = r7.getDouble(r0)
            long r28 = r62 + r24
            long r30 = r65 + r24
            r32 = r12
            long r12 = r28 + r19
            r28 = r2
            long r2 = r30 + r19
            r30 = r8
            long r7 = r2 - r10
            double r7 = r14.getDouble(r7)
            double r2 = r14.getDouble(r2)
            long r14 = r12 - r10
            double r34 = r5 * r7
            double r36 = r0 * r2
            double r10 = r34 - r36
            r9 = r61
            r9.setDouble(r14, r10)
            double r5 = r5 * r2
            double r0 = r0 * r7
            double r5 = r5 + r0
            r9.setDouble(r12, r5)
            long r24 = r24 + r17
            r0 = r53
            r4 = r59
            r14 = r64
            r15 = r9
            r6 = r26
            r2 = r28
            r8 = r30
            r12 = r32
            r10 = 1
            goto L_0x058c
        L_0x05e7:
            r28 = r2
            r59 = r4
            r26 = r6
            r30 = r8
            r0 = r10
            r9 = r15
            r4 = r52
            long r2 = r30 + r0
            r0 = r53
            r4 = r59
            r14 = r64
            r8 = r2
            r2 = r28
            goto L_0x057e
        L_0x0600:
            r28 = r2
            r59 = r4
            r9 = r15
            r0 = 1
            r4 = r52
            long r2 = r28 + r0
            r0 = r53
            r4 = r59
            r14 = r64
            goto L_0x0574
        L_0x0613:
            r4 = r52
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.DoubleFFT_1D.radbg(long, long, long, long, pl.edu.icm.jlargearrays.DoubleLargeArray, long, pl.edu.icm.jlargearrays.DoubleLargeArray, long, long):void");
    }

    /* access modifiers changed from: package-private */
    public void cfftf(double[] dArr, int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8 = this.n;
        int i9 = i8 * 2;
        double[] dArr2 = new double[i9];
        int i10 = 4;
        int i11 = i8 * 4;
        int i12 = 0;
        int[] iArr = {0};
        int i13 = (int) this.wtable[i11 + 1];
        int i14 = 2;
        int i15 = 2;
        int i16 = 0;
        int i17 = i9;
        int i18 = 1;
        while (i15 <= i13 + 1) {
            int i19 = (int) this.wtable[i15 + i11];
            int i20 = i19 * i18;
            int i21 = this.n / i20;
            int i22 = i21 + i21;
            int i23 = i22 * i18;
            if (i19 == i14) {
                i5 = i19;
                i4 = i15;
                i3 = i14;
                i6 = i13;
                i7 = i12;
                if (i16 == 0) {
                    passf2(i22, i18, dArr, i, dArr2, 0, i17, i2);
                } else {
                    passf2(i22, i18, dArr2, 0, dArr, i, i17, i2);
                }
            } else if (i19 == 3) {
                i5 = i19;
                i4 = i15;
                i3 = i14;
                i6 = i13;
                i7 = i12;
                if (i16 == 0) {
                    passf3(i22, i18, dArr, i, dArr2, 0, i17, i2);
                } else {
                    passf3(i22, i18, dArr2, 0, dArr, i, i17, i2);
                }
            } else if (i19 == i10) {
                i5 = i19;
                i4 = i15;
                i3 = i14;
                i6 = i13;
                i7 = i12;
                if (i16 == 0) {
                    passf4(i22, i18, dArr, i, dArr2, 0, i17, i2);
                } else {
                    passf4(i22, i18, dArr2, 0, dArr, i, i17, i2);
                }
            } else if (i19 != 5) {
                if (i16 == 0) {
                    i5 = i19;
                    i4 = i15;
                    i3 = i14;
                    i6 = i13;
                    i7 = i12;
                    passfg(iArr, i22, i19, i18, i23, dArr, i, dArr2, 0, i17, i2);
                } else {
                    i5 = i19;
                    i4 = i15;
                    i3 = i14;
                    i6 = i13;
                    i7 = i12;
                    passfg(iArr, i22, i5, i18, i23, dArr2, 0, dArr, i, i17, i2);
                }
                if (iArr[i7] == 0) {
                    i17 += (i5 - 1) * i22;
                    i15 = i4 + 1;
                    i12 = i7;
                    i18 = i20;
                    i13 = i6;
                    i14 = i3;
                    i10 = 4;
                }
            } else {
                i5 = i19;
                i4 = i15;
                i3 = i14;
                i6 = i13;
                i7 = i12;
                if (i16 == 0) {
                    passf5(i22, i18, dArr, i, dArr2, 0, i17, i2);
                } else {
                    passf5(i22, i18, dArr2, 0, dArr, i, i17, i2);
                }
            }
            i16 = 1 - i16;
            i17 += (i5 - 1) * i22;
            i15 = i4 + 1;
            i12 = i7;
            i18 = i20;
            i13 = i6;
            i14 = i3;
            i10 = 4;
        }
        int i24 = i12;
        if (i16 != 0) {
            System.arraycopy(dArr2, i24, dArr, i, i9);
        }
    }

    /* access modifiers changed from: package-private */
    public void cfftf(DoubleLargeArray doubleLargeArray, long j, int i) {
        DoubleLargeArray doubleLargeArray2;
        int i2;
        long j2;
        long j3;
        int i3;
        long j4;
        long j5;
        int i4;
        DoubleLargeArray doubleLargeArray3;
        DoubleFFT_1D doubleFFT_1D = this;
        int i5 = i;
        long j6 = doubleFFT_1D.nl * 2;
        DoubleLargeArray doubleLargeArray4 = new DoubleLargeArray(j6);
        long j7 = doubleFFT_1D.nl * 4;
        int[] iArr = {0};
        long j8 = (long) doubleFFT_1D.wtablel.getDouble(j7 + 1);
        long j9 = 2;
        long j10 = j6;
        long j11 = 1;
        long j12 = 0;
        while (j9 <= j8 + 1) {
            int i6 = (int) doubleFFT_1D.wtablel.getDouble(j9 + j7);
            long j13 = (long) i6;
            long j14 = j13 * j11;
            long j15 = doubleFFT_1D.nl / j14;
            long j16 = j15 + j15;
            long j17 = j16 * j11;
            if (i6 == 2) {
                j4 = j8;
                j5 = j6;
                i4 = i6;
                i3 = i5;
                doubleLargeArray3 = doubleLargeArray4;
                if (j12 == 0) {
                    passf2(j16, j11, doubleLargeArray, j, doubleLargeArray3, 0, j10, (long) i3);
                } else {
                    passf2(j16, j11, doubleLargeArray3, 0, doubleLargeArray, j, j10, (long) i3);
                }
            } else if (i6 == 3) {
                j4 = j8;
                j5 = j6;
                i4 = i6;
                i3 = i5;
                doubleLargeArray3 = doubleLargeArray4;
                if (j12 == 0) {
                    passf3(j16, j11, doubleLargeArray, j, doubleLargeArray3, 0, j10, (long) i3);
                } else {
                    passf3(j16, j11, doubleLargeArray3, 0, doubleLargeArray, j, j10, (long) i3);
                }
            } else if (i6 == 4) {
                j4 = j8;
                j5 = j6;
                i4 = i6;
                i3 = i5;
                doubleLargeArray3 = doubleLargeArray4;
                if (j12 == 0) {
                    passf4(j16, j11, doubleLargeArray, j, doubleLargeArray3, 0, j10, i);
                } else {
                    passf4(j16, j11, doubleLargeArray3, 0, doubleLargeArray, j, j10, i);
                }
            } else if (i6 != 5) {
                if (j12 == 0) {
                    j3 = j8;
                    j2 = j6;
                    i2 = i6;
                    doubleLargeArray2 = doubleLargeArray4;
                    passfg(iArr, j16, j13, j11, j17, doubleLargeArray, j, doubleLargeArray2, 0, j10, (long) i5);
                } else {
                    j3 = j8;
                    j2 = j6;
                    i2 = i6;
                    doubleLargeArray2 = doubleLargeArray4;
                    passfg(iArr, j16, j13, j11, j17, doubleLargeArray2, 0, doubleLargeArray, j, j10, (long) i);
                }
                if (iArr[0] != 0) {
                    j12 = 1 - j12;
                }
                i3 = i;
                j10 += ((long) (i2 - 1)) * j16;
                j9++;
                doubleFFT_1D = this;
                i5 = i3;
                j11 = j14;
                j8 = j3;
                j6 = j2;
                doubleLargeArray4 = doubleLargeArray2;
            } else {
                j4 = j8;
                j5 = j6;
                i4 = i6;
                doubleLargeArray3 = doubleLargeArray4;
                if (j12 == 0) {
                    i3 = i;
                    passf5(j16, j11, doubleLargeArray, j, doubleLargeArray3, 0, j10, (long) i3);
                } else {
                    i3 = i;
                    passf5(j16, j11, doubleLargeArray3, 0, doubleLargeArray, j, j10, (long) i3);
                }
            }
            j12 = 1 - j12;
            j10 += ((long) (i2 - 1)) * j16;
            j9++;
            doubleFFT_1D = this;
            i5 = i3;
            j11 = j14;
            j8 = j3;
            j6 = j2;
            doubleLargeArray4 = doubleLargeArray2;
        }
        long j18 = j6;
        DoubleLargeArray doubleLargeArray5 = doubleLargeArray4;
        if (j12 != 0) {
            LargeArrayUtils.arraycopy(doubleLargeArray5, 0, doubleLargeArray, j, j18);
        }
    }

    /* access modifiers changed from: package-private */
    public void passf2(int i, int i2, double[] dArr, int i3, double[] dArr2, int i4, int i5, int i6) {
        int i7 = i;
        int i8 = i2;
        int i9 = i7 * i8;
        if (i7 <= 2) {
            for (int i10 = 0; i10 < i8; i10++) {
                int i11 = i10 * i7;
                int i12 = i3 + (i11 * 2);
                int i13 = i12 + i7;
                double d = dArr[i12];
                double d2 = dArr[i12 + 1];
                double d3 = dArr[i13];
                double d4 = dArr[i13 + 1];
                int i14 = i4 + i11;
                int i15 = i14 + i9;
                dArr2[i14] = d + d3;
                dArr2[i14 + 1] = d2 + d4;
                dArr2[i15] = d - d3;
                dArr2[i15 + 1] = d2 - d4;
            }
            return;
        }
        int i16 = 0;
        while (i16 < i8) {
            int i17 = 0;
            while (i17 < i7 - 1) {
                int i18 = i16 * i7;
                int i19 = i3 + i17 + (i18 * 2);
                int i20 = i19 + i7;
                double d5 = dArr[i19];
                double d6 = dArr[i19 + 1];
                double d7 = dArr[i20];
                double d8 = dArr[i20 + 1];
                int i21 = i17 + i5;
                double[] dArr3 = this.wtable;
                double d9 = dArr3[i21];
                int i22 = i16;
                double d10 = ((double) i6) * dArr3[i21 + 1];
                double d11 = d5 - d7;
                double d12 = d6 - d8;
                int i23 = i4 + i17 + i18;
                int i24 = i23 + i9;
                dArr2[i23] = d5 + d7;
                dArr2[i23 + 1] = d6 + d8;
                dArr2[i24] = (d9 * d11) - (d10 * d12);
                dArr2[i24 + 1] = (d9 * d12) + (d10 * d11);
                i17 += 2;
                i7 = i;
                int i25 = i2;
                i16 = i22;
            }
            int i26 = i6;
            i16++;
            i7 = i;
            i8 = i2;
        }
    }

    /* access modifiers changed from: package-private */
    public void passf2(long j, long j2, DoubleLargeArray doubleLargeArray, long j3, DoubleLargeArray doubleLargeArray2, long j4, long j5, long j6) {
        DoubleLargeArray doubleLargeArray3 = doubleLargeArray;
        DoubleLargeArray doubleLargeArray4 = doubleLargeArray2;
        long j7 = j * j2;
        long j8 = 2;
        long j9 = 1;
        if (j <= 2) {
            long j10 = 0;
            while (j10 < j2) {
                long j11 = j10 * j;
                long j12 = j3 + (j11 * j8);
                long j13 = j12 + j;
                double d = doubleLargeArray3.getDouble(j12);
                double d2 = doubleLargeArray3.getDouble(j12 + j9);
                double d3 = doubleLargeArray3.getDouble(j13);
                double d4 = doubleLargeArray3.getDouble(j13 + j9);
                long j14 = j4 + j11;
                long j15 = j14 + j7;
                doubleLargeArray4.setDouble(j14, d + d3);
                doubleLargeArray4.setDouble(j14 + 1, d2 + d4);
                doubleLargeArray4.setDouble(j15, d - d3);
                doubleLargeArray4.setDouble(j15 + 1, d2 - d4);
                j10++;
                j9 = 1;
                j7 = j7;
                j8 = 2;
            }
            return;
        }
        long j16 = j7;
        long j17 = 1;
        long j18 = 0;
        while (j18 < j2) {
            long j19 = 0;
            while (j19 < j - j17) {
                long j20 = j18 * j;
                long j21 = j3 + j19 + (j20 * 2);
                long j22 = j21 + j;
                double d5 = doubleLargeArray3.getDouble(j21);
                double d6 = doubleLargeArray3.getDouble(j21 + j17);
                double d7 = doubleLargeArray3.getDouble(j22);
                double d8 = doubleLargeArray3.getDouble(j22 + j17);
                long j23 = j19 + j5;
                double d9 = this.wtablel.getDouble(j23);
                long j24 = j18;
                double d10 = ((double) j6) * this.wtablel.getDouble(j23 + 1);
                double d11 = d5 - d7;
                double d12 = d6 - d8;
                long j25 = j4 + j19 + j20;
                long j26 = j25 + j16;
                DoubleLargeArray doubleLargeArray5 = doubleLargeArray2;
                doubleLargeArray5.setDouble(j25, d5 + d7);
                doubleLargeArray5.setDouble(j25 + 1, d6 + d8);
                doubleLargeArray5.setDouble(j26, (d9 * d11) - (d10 * d12));
                doubleLargeArray5.setDouble(j26 + 1, (d9 * d12) + (d10 * d11));
                doubleLargeArray3 = doubleLargeArray;
                doubleLargeArray4 = doubleLargeArray5;
                j18 = j24;
                j19 += 2;
                j17 = 1;
            }
            j18 += j17;
            doubleLargeArray3 = doubleLargeArray;
            doubleLargeArray4 = doubleLargeArray4;
        }
    }

    /* access modifiers changed from: package-private */
    public void passf3(int i, int i2, double[] dArr, int i3, double[] dArr2, int i4, int i5, int i6) {
        int i7 = i;
        int i8 = i2;
        int i9 = i6;
        int i10 = i5 + i7;
        int i11 = i8 * i7;
        double d = 0.8660254037844387d;
        double d2 = -0.5d;
        int i12 = 2;
        if (i7 == 2) {
            int i13 = 1;
            while (i13 <= i8) {
                int i14 = i3 + (((i13 * 3) - 2) * i7);
                int i15 = i14 + i7;
                int i16 = i14 - i7;
                double d3 = dArr[i14];
                double d4 = dArr[i14 + 1];
                double d5 = dArr[i15];
                double d6 = dArr[i15 + 1];
                double d7 = dArr[i16];
                double d8 = dArr[i16 + 1];
                double d9 = d3 + d5;
                double d10 = d7 + (d9 * d2);
                double d11 = d4 + d6;
                double d12 = d8 + (d11 * d2);
                double d13 = ((double) i9) * 0.8660254037844387d;
                double d14 = (d3 - d5) * d13;
                double d15 = d13 * (d4 - d6);
                int i17 = i4 + ((i13 - 1) * i7);
                int i18 = i17 + i11;
                int i19 = i18 + i11;
                dArr2[i17] = d7 + d9;
                dArr2[i17 + 1] = d8 + d11;
                dArr2[i18] = d10 - d15;
                dArr2[i18 + 1] = d12 + d14;
                dArr2[i19] = d10 + d15;
                dArr2[i19 + 1] = d12 - d14;
                i13++;
                d2 = -0.5d;
            }
        } else {
            int i20 = 1;
            while (i20 <= i8) {
                int i21 = i3 + (((i20 * 3) - i12) * i7);
                int i22 = i4 + ((i20 - 1) * i7);
                int i23 = 0;
                while (i23 < i7 - 1) {
                    int i24 = i23 + i21;
                    int i25 = i24 + i7;
                    int i26 = i24 - i7;
                    double d16 = dArr[i24];
                    double d17 = dArr[i24 + 1];
                    double d18 = dArr[i25];
                    double d19 = dArr[i25 + 1];
                    double d20 = dArr[i26];
                    double d21 = dArr[i26 + 1];
                    double d22 = d16 + d18;
                    double d23 = d20 + (d22 * -0.5d);
                    double d24 = d17 + d19;
                    double d25 = d21 + (d24 * -0.5d);
                    int i27 = i22;
                    double d26 = (double) i9;
                    double d27 = d26 * d;
                    double d28 = (d16 - d18) * d27;
                    double d29 = d27 * (d17 - d19);
                    double d30 = d23 - d29;
                    double d31 = d23 + d29;
                    double d32 = d25 + d28;
                    double d33 = d25 - d28;
                    int i28 = i23 + i5;
                    int i29 = i23 + i10;
                    double[] dArr3 = this.wtable;
                    double d34 = dArr3[i28];
                    double d35 = dArr3[i28 + 1] * d26;
                    double d36 = dArr3[i29];
                    double d37 = d26 * dArr3[i29 + 1];
                    int i30 = i23 + i27;
                    int i31 = i30 + i11;
                    int i32 = i31 + i11;
                    dArr2[i30] = d20 + d22;
                    dArr2[i30 + 1] = d21 + d24;
                    dArr2[i31] = (d34 * d30) - (d35 * d32);
                    dArr2[i31 + 1] = (d34 * d32) + (d35 * d30);
                    dArr2[i32] = (d36 * d31) - (d37 * d33);
                    dArr2[i32 + 1] = (d36 * d33) + (d37 * d31);
                    i23 += 2;
                    i22 = i27;
                    d = 0.8660254037844387d;
                }
                i20++;
                d = 0.8660254037844387d;
                i12 = 2;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void passf3(long j, long j2, DoubleLargeArray doubleLargeArray, long j3, DoubleLargeArray doubleLargeArray2, long j4, long j5, long j6) {
        DoubleLargeArray doubleLargeArray3 = doubleLargeArray;
        DoubleLargeArray doubleLargeArray4 = doubleLargeArray2;
        long j7 = j6;
        long j8 = j5 + j;
        long j9 = j2 * j;
        long j10 = 2;
        long j11 = 3;
        if (j == 2) {
            long j12 = 1;
            while (j12 <= j2) {
                long j13 = j3 + (((j12 * j11) - j10) * j);
                long j14 = j13 + j;
                long j15 = j13 - j;
                double d = doubleLargeArray3.getDouble(j13);
                double d2 = doubleLargeArray3.getDouble(j13 + 1);
                double d3 = doubleLargeArray3.getDouble(j14);
                double d4 = doubleLargeArray3.getDouble(j14 + 1);
                double d5 = doubleLargeArray3.getDouble(j15);
                long j16 = j9;
                double d6 = doubleLargeArray3.getDouble(j15 + 1);
                double d7 = d + d3;
                double d8 = d5 + (d7 * -0.5d);
                double d9 = d2 + d4;
                double d10 = d6 + (d9 * -0.5d);
                double d11 = d6;
                double d12 = ((double) j7) * 0.8660254037844387d;
                double d13 = (d - d3) * d12;
                double d14 = d12 * (d2 - d4);
                long j17 = j4 + ((j12 - 1) * j);
                long j18 = j17 + j16;
                long j19 = j18 + j16;
                doubleLargeArray4.setDouble(j17, doubleLargeArray3.getDouble(j15) + d7);
                doubleLargeArray4.setDouble(j17 + 1, d11 + d9);
                doubleLargeArray4.setDouble(j18, d8 - d14);
                doubleLargeArray4.setDouble(j18 + 1, d10 + d13);
                doubleLargeArray4.setDouble(j19, d8 + d14);
                doubleLargeArray4.setDouble(j19 + 1, d10 - d13);
                j12++;
                j7 = j6;
                j9 = j16;
                j10 = 2;
                j11 = 3;
            }
            return;
        }
        long j20 = j9;
        long j21 = 1;
        while (j21 <= j2) {
            long j22 = j3 + (((j21 * 3) - 2) * j);
            long j23 = j4 + ((j21 - 1) * j);
            long j24 = 0;
            while (j24 < j - 1) {
                long j25 = j24 + j22;
                long j26 = j22;
                long j27 = j25 + j;
                long j28 = j21;
                long j29 = j25 - j;
                double d15 = doubleLargeArray3.getDouble(j25);
                double d16 = doubleLargeArray3.getDouble(j25 + 1);
                double d17 = doubleLargeArray3.getDouble(j27);
                double d18 = doubleLargeArray3.getDouble(j27 + 1);
                double d19 = doubleLargeArray3.getDouble(j29);
                double d20 = doubleLargeArray3.getDouble(j29 + 1);
                double d21 = d15 + d17;
                double d22 = d19 + (d21 * -0.5d);
                double d23 = d16 + d18;
                double d24 = d20 + (d23 * -0.5d);
                double d25 = d20;
                double d26 = (double) j6;
                double d27 = d26 * 0.8660254037844387d;
                double d28 = (d15 - d17) * d27;
                double d29 = d27 * (d16 - d18);
                double d30 = d22 - d29;
                double d31 = d22 + d29;
                double d32 = d24 + d28;
                double d33 = d24 - d28;
                long j30 = j24 + j5;
                double d34 = d32;
                long j31 = j24 + j8;
                double d35 = this.wtablel.getDouble(j30);
                double d36 = this.wtablel.getDouble(j30 + 1) * d26;
                double d37 = this.wtablel.getDouble(j31);
                double d38 = d26 * this.wtablel.getDouble(j31 + 1);
                long j32 = j24 + j23;
                long j33 = j8;
                long j34 = j32 + j20;
                long j35 = j23;
                long j36 = j34 + j20;
                DoubleLargeArray doubleLargeArray5 = doubleLargeArray2;
                doubleLargeArray5.setDouble(j32, d19 + d21);
                doubleLargeArray5.setDouble(j32 + 1, d25 + d23);
                doubleLargeArray5.setDouble(j34, (d35 * d30) - (d36 * d34));
                doubleLargeArray5.setDouble(j34 + 1, (d35 * d34) + (d36 * d30));
                doubleLargeArray5.setDouble(j36, (d37 * d31) - (d38 * d33));
                doubleLargeArray5.setDouble(j36 + 1, (d37 * d33) + (d38 * d31));
                j24 += 2;
                doubleLargeArray3 = doubleLargeArray;
                doubleLargeArray4 = doubleLargeArray5;
                j22 = j26;
                j21 = j28;
                j8 = j33;
                j23 = j35;
            }
            long j37 = j8;
            j21++;
            doubleLargeArray3 = doubleLargeArray;
            doubleLargeArray4 = doubleLargeArray4;
        }
    }

    /* access modifiers changed from: package-private */
    public void passf4(int i, int i2, double[] dArr, int i3, double[] dArr2, int i4, int i5, int i6) {
        int i7 = i;
        int i8 = i2;
        int i9 = i6;
        int i10 = i5 + i7;
        int i11 = i10 + i7;
        int i12 = i8 * i7;
        if (i7 == 2) {
            int i13 = 0;
            while (i13 < i8) {
                int i14 = i13 * i7;
                int i15 = i3 + (i14 * 4);
                int i16 = i15 + 1;
                int i17 = i16 + i7;
                int i18 = i17 + i7;
                int i19 = i18 + i7;
                double d = dArr[i15];
                double d2 = dArr[i16];
                double d3 = dArr[i17 - 1];
                double d4 = dArr[i17];
                double d5 = dArr[i18 - 1];
                double d6 = dArr[i18];
                double d7 = dArr[i19 - 1];
                double d8 = dArr[i19];
                double d9 = d2 - d6;
                double d10 = d2 + d6;
                double d11 = d8 - d4;
                double d12 = d4 + d8;
                double d13 = d - d5;
                double d14 = d + d5;
                double d15 = d3 - d7;
                double d16 = d3 + d7;
                int i20 = i4 + i14;
                int i21 = i20 + i12;
                int i22 = i21 + i12;
                int i23 = i22 + i12;
                dArr2[i20] = d14 + d16;
                dArr2[i20 + 1] = d10 + d12;
                double d17 = (double) i9;
                double d18 = d11 * d17;
                dArr2[i21] = d13 + d18;
                double d19 = d17 * d15;
                dArr2[i21 + 1] = d9 + d19;
                dArr2[i22] = d14 - d16;
                dArr2[i22 + 1] = d10 - d12;
                dArr2[i23] = d13 - d18;
                dArr2[i23 + 1] = d9 - d19;
                i13++;
                i7 = i;
                i8 = i2;
            }
        } else {
            int i24 = i2;
            int i25 = 0;
            while (i25 < i24) {
                int i26 = i25 * i;
                int i27 = i3 + 1 + (i26 * 4);
                int i28 = 0;
                while (i28 < i - 1) {
                    int i29 = i28 + i27;
                    int i30 = i29 + i;
                    int i31 = i30 + i;
                    int i32 = i31 + i;
                    double d20 = dArr[i29 - 1];
                    double d21 = dArr[i29];
                    double d22 = dArr[i30 - 1];
                    double d23 = dArr[i30];
                    double d24 = dArr[i31 - 1];
                    double d25 = dArr[i31];
                    double d26 = dArr[i32 - 1];
                    double d27 = dArr[i32];
                    double d28 = d21 - d25;
                    double d29 = d21 + d25;
                    double d30 = d23 + d27;
                    double d31 = d27 - d23;
                    double d32 = d20 - d24;
                    double d33 = d20 + d24;
                    double d34 = d22 - d26;
                    double d35 = d22 + d26;
                    double d36 = d33 - d35;
                    double d37 = d29 - d30;
                    double d38 = (double) i9;
                    double d39 = d31 * d38;
                    double d40 = d32 + d39;
                    double d41 = d32 - d39;
                    double d42 = d34 * d38;
                    double d43 = d28 + d42;
                    double d44 = d28 - d42;
                    int i33 = i28 + i5;
                    int i34 = i28 + i10;
                    int i35 = i28 + i11;
                    double[] dArr3 = this.wtable;
                    double d45 = dArr3[i33];
                    double d46 = dArr3[i33 + 1] * d38;
                    double d47 = dArr3[i34];
                    double d48 = dArr3[i34 + 1] * d38;
                    double d49 = dArr3[i35];
                    double d50 = d38 * dArr3[i35 + 1];
                    int i36 = i4 + i28 + i26;
                    int i37 = i36 + i12;
                    int i38 = i37 + i12;
                    int i39 = i38 + i12;
                    dArr2[i36] = d33 + d35;
                    dArr2[i36 + 1] = d29 + d30;
                    dArr2[i37] = (d45 * d40) - (d46 * d43);
                    dArr2[i37 + 1] = (d45 * d43) + (d46 * d40);
                    dArr2[i38] = (d47 * d36) - (d48 * d37);
                    dArr2[i38 + 1] = (d47 * d37) + (d48 * d36);
                    dArr2[i39] = (d49 * d41) - (d50 * d44);
                    dArr2[i39 + 1] = (d49 * d44) + (d50 * d41);
                    i28 += 2;
                    int i40 = i2;
                    i9 = i6;
                    i27 = i27;
                }
                i25++;
                i24 = i2;
                i9 = i6;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void passf4(long j, long j2, DoubleLargeArray doubleLargeArray, long j3, DoubleLargeArray doubleLargeArray2, long j4, long j5, int i) {
        DoubleLargeArray doubleLargeArray3 = doubleLargeArray;
        DoubleLargeArray doubleLargeArray4 = doubleLargeArray2;
        int i2 = i;
        long j6 = j5 + j;
        long j7 = j6 + j;
        long j8 = j2 * j;
        long j9 = 2;
        long j10 = 4;
        if (j == 2) {
            long j11 = 0;
            while (j11 < j2) {
                long j12 = j11 * j;
                long j13 = j3 + (j12 * j10);
                long j14 = j13 + 1;
                long j15 = j14 + j;
                long j16 = j15 + j;
                long j17 = j8;
                long j18 = j16 + j;
                double d = doubleLargeArray3.getDouble(j13);
                double d2 = doubleLargeArray3.getDouble(j14);
                long j19 = j12;
                double d3 = doubleLargeArray3.getDouble(j15 - 1);
                double d4 = doubleLargeArray3.getDouble(j15);
                double d5 = doubleLargeArray3.getDouble(j16 - 1);
                double d6 = doubleLargeArray3.getDouble(j16);
                double d7 = d3;
                double d8 = doubleLargeArray3.getDouble(j18 - 1);
                double d9 = doubleLargeArray3.getDouble(j18);
                double d10 = d2 - d6;
                double d11 = d2 + d6;
                double d12 = d9 - d4;
                double d13 = d4 + d9;
                double d14 = d - d5;
                double d15 = d + d5;
                double d16 = d7 - d8;
                double d17 = d7 + d8;
                long j20 = j4 + j19;
                double d18 = d16;
                long j21 = j20 + j17;
                double d19 = d14;
                long j22 = j21 + j17;
                long j23 = j21;
                long j24 = j22 + j17;
                long j25 = j22;
                DoubleLargeArray doubleLargeArray5 = doubleLargeArray2;
                doubleLargeArray5.setDouble(j20, d15 + d17);
                doubleLargeArray5.setDouble(j20 + 1, d11 + d13);
                double d20 = (double) i;
                double d21 = d12 * d20;
                double d22 = d19 + d21;
                double d23 = d21;
                long j26 = j23;
                doubleLargeArray5.setDouble(j26, d22);
                double d24 = d20 * d18;
                doubleLargeArray5.setDouble(j26 + 1, d10 + d24);
                long j27 = j25;
                doubleLargeArray5.setDouble(j27, d15 - d17);
                doubleLargeArray5.setDouble(j27 + 1, d11 - d13);
                long j28 = j24;
                doubleLargeArray5.setDouble(j28, d19 - d23);
                doubleLargeArray5.setDouble(j28 + 1, d10 - d24);
                j11++;
                doubleLargeArray3 = doubleLargeArray;
                int i3 = i;
                DoubleLargeArray doubleLargeArray6 = doubleLargeArray5;
                j8 = j17;
                j10 = 4;
            }
        } else {
            long j29 = j8;
            DoubleLargeArray doubleLargeArray7 = doubleLargeArray4;
            long j30 = 0;
            while (j30 < j2) {
                long j31 = j30 * j;
                long j32 = j3 + 1 + (j31 * 4);
                long j33 = 0;
                while (j33 < j - 1) {
                    long j34 = j33 + j32;
                    long j35 = j32;
                    long j36 = j34 + j;
                    long j37 = j36 + j;
                    long j38 = j30;
                    long j39 = j37 + j;
                    long j40 = j31;
                    DoubleLargeArray doubleLargeArray8 = doubleLargeArray;
                    double d25 = doubleLargeArray8.getDouble(j34 - 1);
                    double d26 = doubleLargeArray8.getDouble(j34);
                    long j41 = j7;
                    double d27 = doubleLargeArray8.getDouble(j36 - 1);
                    double d28 = doubleLargeArray8.getDouble(j36);
                    long j42 = j6;
                    double d29 = doubleLargeArray8.getDouble(j37 - 1);
                    double d30 = doubleLargeArray8.getDouble(j37);
                    double d31 = d27;
                    double d32 = doubleLargeArray8.getDouble(j39 - 1);
                    double d33 = doubleLargeArray8.getDouble(j39);
                    double d34 = d26 - d30;
                    double d35 = d26 + d30;
                    double d36 = d28 + d33;
                    double d37 = d33 - d28;
                    double d38 = d25 - d29;
                    double d39 = d25 + d29;
                    double d40 = d31 - d32;
                    double d41 = d31 + d32;
                    double d42 = d39 - d41;
                    double d43 = d35 - d36;
                    double d44 = d35;
                    double d45 = (double) i;
                    double d46 = d37 * d45;
                    double d47 = d38 + d46;
                    double d48 = d38 - d46;
                    double d49 = d40 * d45;
                    double d50 = d34 + d49;
                    double d51 = d34 - d49;
                    long j43 = j33 + j5;
                    double d52 = d48;
                    long j44 = j33 + j42;
                    double d53 = d50;
                    long j45 = j33 + j41;
                    double d54 = this.wtablel.getDouble(j43);
                    double d55 = this.wtablel.getDouble(j43 + 1) * d45;
                    double d56 = this.wtablel.getDouble(j44);
                    double d57 = this.wtablel.getDouble(j44 + 1) * d45;
                    double d58 = this.wtablel.getDouble(j45);
                    double d59 = d45 * this.wtablel.getDouble(j45 + 1);
                    long j46 = j4 + j33 + j40;
                    double d60 = d59;
                    long j47 = j46 + j29;
                    double d61 = d57;
                    long j48 = j47 + j29;
                    long j49 = j47;
                    long j50 = j48 + j29;
                    DoubleLargeArray doubleLargeArray9 = doubleLargeArray2;
                    doubleLargeArray9.setDouble(j46, d39 + d41);
                    doubleLargeArray9.setDouble(j46 + 1, d44 + d36);
                    long j51 = j49;
                    doubleLargeArray9.setDouble(j51, (d54 * d47) - (d55 * d53));
                    doubleLargeArray9.setDouble(j51 + 1, (d54 * d53) + (d55 * d47));
                    doubleLargeArray9.setDouble(j48, (d56 * d42) - (d61 * d43));
                    doubleLargeArray9.setDouble(j48 + 1, (d56 * d43) + (d61 * d42));
                    doubleLargeArray9.setDouble(j50, (d58 * d52) - (d60 * d51));
                    doubleLargeArray9.setDouble(j50 + 1, (d58 * d51) + (d60 * d52));
                    j33 += 2;
                    j9 = 2;
                    doubleLargeArray7 = doubleLargeArray9;
                    j32 = j35;
                    j30 = j38;
                    j31 = j40;
                    j7 = j41;
                    j6 = j42;
                }
                long j52 = j30;
                long j53 = j7;
                DoubleLargeArray doubleLargeArray10 = doubleLargeArray7;
                long j54 = j9;
                j30 = j52 + 1;
                j6 = j6;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void passf5(int i, int i2, double[] dArr, int i3, double[] dArr2, int i4, int i5, int i6) {
        int i7 = i;
        int i8 = i2;
        int i9 = i6;
        int i10 = i5 + i7;
        int i11 = i10 + i7;
        int i12 = i11 + i7;
        int i13 = i8 * i7;
        double d = 0.9510565162951535d;
        double d2 = -0.8090169943749473d;
        double d3 = 0.30901699437494745d;
        if (i7 == 2) {
            int i14 = 1;
            while (i14 <= i8) {
                int i15 = i3 + (((i14 * 5) - 4) * i7);
                int i16 = i15 + 1;
                int i17 = i16 + i7;
                int i18 = i16 - i7;
                int i19 = i17 + i7;
                int i20 = i19 + i7;
                double d4 = dArr[i15];
                double d5 = dArr[i16];
                double d6 = dArr[i17 - 1];
                double d7 = dArr[i17];
                double d8 = dArr[i18 - 1];
                double d9 = dArr[i18];
                double d10 = dArr[i19 - 1];
                double d11 = dArr[i19];
                double d12 = dArr[i20 - 1];
                double d13 = dArr[i20];
                double d14 = d5 - d13;
                double d15 = d5 + d13;
                double d16 = d7 - d11;
                double d17 = d7 + d11;
                double d18 = d4 - d12;
                double d19 = d4 + d12;
                double d20 = d6 - d10;
                double d21 = d6 + d10;
                double d22 = d8 + (d19 * 0.30901699437494745d) + (d21 * d2);
                double d23 = d9 + (d15 * 0.30901699437494745d) + (d17 * d2);
                double d24 = d8 + (d19 * d2) + (d21 * 0.30901699437494745d);
                double d25 = d9 + (d15 * d2) + (d17 * 0.30901699437494745d);
                double d26 = (double) i9;
                double d27 = ((d18 * 0.9510565162951535d) + (d20 * 0.5877852522924732d)) * d26;
                double d28 = ((d14 * 0.9510565162951535d) + (d16 * 0.5877852522924732d)) * d26;
                double d29 = ((d18 * 0.5877852522924732d) - (d20 * 0.9510565162951535d)) * d26;
                double d30 = d26 * ((d14 * 0.5877852522924732d) - (d16 * 0.9510565162951535d));
                int i21 = i4 + ((i14 - 1) * i7);
                int i22 = i21 + i13;
                int i23 = i22 + i13;
                int i24 = i23 + i13;
                int i25 = i24 + i13;
                dArr2[i21] = d8 + d19 + d21;
                dArr2[i21 + 1] = d9 + d15 + d17;
                dArr2[i22] = d22 - d28;
                dArr2[i22 + 1] = d23 + d27;
                dArr2[i23] = d24 - d30;
                dArr2[i23 + 1] = d25 + d29;
                dArr2[i24] = d24 + d30;
                dArr2[i24 + 1] = d25 - d29;
                dArr2[i25] = d22 + d28;
                dArr2[i25 + 1] = d23 - d27;
                i14++;
                d2 = -0.8090169943749473d;
            }
        } else {
            int i26 = 1;
            while (i26 <= i8) {
                int i27 = i3 + 1 + (((i26 * 5) - 4) * i7);
                int i28 = i4 + ((i26 - 1) * i7);
                int i29 = 0;
                while (i29 < i7 - 1) {
                    int i30 = i29 + i27;
                    int i31 = i30 + i7;
                    int i32 = i30 - i7;
                    int i33 = i31 + i7;
                    int i34 = i33 + i7;
                    double d31 = dArr[i30 - 1];
                    double d32 = dArr[i30];
                    double d33 = dArr[i31 - 1];
                    double d34 = dArr[i31];
                    double d35 = dArr[i32 - 1];
                    double d36 = dArr[i32];
                    double d37 = dArr[i33 - 1];
                    double d38 = dArr[i33];
                    double d39 = dArr[i34 - 1];
                    double d40 = dArr[i34];
                    double d41 = d32 - d40;
                    double d42 = d32 + d40;
                    double d43 = d34 - d38;
                    double d44 = d34 + d38;
                    double d45 = d31 - d39;
                    double d46 = d31 + d39;
                    double d47 = d33 - d37;
                    double d48 = d33 + d37;
                    double d49 = d35 + (d46 * d3) + (d48 * -0.8090169943749473d);
                    double d50 = d36 + (d42 * d3) + (d44 * -0.8090169943749473d);
                    double d51 = d35 + (d46 * -0.8090169943749473d) + (d48 * d3);
                    double d52 = d36 + (d42 * -0.8090169943749473d) + (d44 * d3);
                    double d53 = (double) i9;
                    double d54 = ((d45 * d) + (d47 * 0.5877852522924732d)) * d53;
                    double d55 = ((d41 * d) + (d43 * 0.5877852522924732d)) * d53;
                    double d56 = ((d45 * 0.5877852522924732d) - (d47 * d)) * d53;
                    double d57 = ((d41 * 0.5877852522924732d) - (d43 * d)) * d53;
                    double d58 = d51 - d57;
                    double d59 = d51 + d57;
                    double d60 = d52 + d56;
                    double d61 = d52 - d56;
                    double d62 = d49 + d55;
                    double d63 = d49 - d55;
                    double d64 = d50 - d54;
                    double d65 = d50 + d54;
                    int i35 = i29 + i5;
                    int i36 = i29 + i10;
                    int i37 = i29 + i11;
                    int i38 = i29 + i12;
                    double[] dArr3 = this.wtable;
                    double d66 = dArr3[i35];
                    double d67 = dArr3[i35 + 1] * d53;
                    double d68 = dArr3[i36];
                    double d69 = dArr3[i36 + 1] * d53;
                    double d70 = dArr3[i37];
                    double d71 = dArr3[i37 + 1] * d53;
                    double d72 = dArr3[i38];
                    double d73 = d53 * dArr3[i38 + 1];
                    int i39 = i29 + i28;
                    int i40 = i39 + i13;
                    int i41 = i40 + i13;
                    int i42 = i41 + i13;
                    int i43 = i42 + i13;
                    dArr2[i39] = d35 + d46 + d48;
                    dArr2[i39 + 1] = d36 + d42 + d44;
                    dArr2[i40] = (d66 * d63) - (d67 * d65);
                    dArr2[i40 + 1] = (d66 * d65) + (d67 * d63);
                    dArr2[i41] = (d68 * d58) - (d69 * d60);
                    dArr2[i41 + 1] = (d68 * d60) + (d69 * d58);
                    dArr2[i42] = (d70 * d59) - (d71 * d61);
                    dArr2[i42 + 1] = (d70 * d61) + (d71 * d59);
                    dArr2[i43] = (d72 * d62) - (d73 * d64);
                    dArr2[i43 + 1] = (d72 * d64) + (d73 * d62);
                    i29 += 2;
                    d = 0.9510565162951535d;
                    d3 = 0.30901699437494745d;
                }
                i26++;
                d = 0.9510565162951535d;
                d3 = 0.30901699437494745d;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void passf5(long j, long j2, DoubleLargeArray doubleLargeArray, long j3, DoubleLargeArray doubleLargeArray2, long j4, long j5, long j6) {
        DoubleLargeArray doubleLargeArray3 = doubleLargeArray;
        DoubleLargeArray doubleLargeArray4 = doubleLargeArray2;
        long j7 = j6;
        long j8 = j5 + j;
        long j9 = j8 + j;
        long j10 = j9 + j;
        long j11 = j2 * j;
        long j12 = 2;
        if (j == 2) {
            long j13 = 1;
            while (j13 <= j2) {
                long j14 = j3 + (((j13 * 5) - 4) * j);
                long j15 = j14 + 1;
                long j16 = j15 + j;
                long j17 = j11;
                long j18 = j15 - j;
                long j19 = j13;
                long j20 = j16 + j;
                long j21 = j20 + j;
                double d = doubleLargeArray3.getDouble(j14);
                double d2 = doubleLargeArray3.getDouble(j15);
                double d3 = d;
                double d4 = doubleLargeArray3.getDouble(j16 - 1);
                double d5 = doubleLargeArray3.getDouble(j16);
                double d6 = d4;
                double d7 = doubleLargeArray3.getDouble(j18 - 1);
                double d8 = doubleLargeArray3.getDouble(j18);
                double d9 = doubleLargeArray3.getDouble(j20 - 1);
                double d10 = doubleLargeArray3.getDouble(j20);
                double d11 = d7;
                double d12 = doubleLargeArray3.getDouble(j21 - 1);
                double d13 = doubleLargeArray3.getDouble(j21);
                double d14 = d2 - d13;
                double d15 = d2 + d13;
                double d16 = d5 - d10;
                double d17 = d5 + d10;
                double d18 = d3 - d12;
                double d19 = d3 + d12;
                double d20 = d6 - d9;
                double d21 = d6 + d9;
                double d22 = d11 + (d19 * 0.30901699437494745d) + (d21 * -0.8090169943749473d);
                double d23 = d8 + (d15 * 0.30901699437494745d) + (d17 * -0.8090169943749473d);
                double d24 = d11 + (d19 * -0.8090169943749473d) + (d21 * 0.30901699437494745d);
                double d25 = d8 + (d15 * -0.8090169943749473d) + (d17 * 0.30901699437494745d);
                double d26 = d17;
                double d27 = (double) j6;
                double d28 = ((d18 * 0.9510565162951535d) + (d20 * 0.5877852522924732d)) * d27;
                double d29 = ((d14 * 0.9510565162951535d) + (d16 * 0.5877852522924732d)) * d27;
                long j22 = j4 + ((j19 - 1) * j);
                long j23 = j22 + j17;
                double d30 = ((d18 * 0.5877852522924732d) - (d20 * 0.9510565162951535d)) * d27;
                long j24 = j23 + j17;
                double d31 = d27 * ((d14 * 0.5877852522924732d) - (d16 * 0.9510565162951535d));
                long j25 = j24 + j17;
                long j26 = j24;
                long j27 = j25 + j17;
                double d32 = d11 + d19 + d21;
                DoubleLargeArray doubleLargeArray5 = doubleLargeArray2;
                doubleLargeArray5.setDouble(j22, d32);
                doubleLargeArray5.setDouble(j22 + 1, d8 + d15 + d26);
                doubleLargeArray5.setDouble(j23, d22 - d29);
                doubleLargeArray5.setDouble(j23 + 1, d23 + d28);
                long j28 = j26;
                doubleLargeArray5.setDouble(j28, d24 - d31);
                doubleLargeArray5.setDouble(j28 + 1, d25 + d30);
                doubleLargeArray5.setDouble(j25, d24 + d31);
                doubleLargeArray5.setDouble(j25 + 1, d25 - d30);
                doubleLargeArray5.setDouble(j27, d22 + d29);
                doubleLargeArray5.setDouble(j27 + 1, d23 - d28);
                j13 = j19 + 1;
                doubleLargeArray3 = doubleLargeArray;
                long j29 = j6;
                DoubleLargeArray doubleLargeArray6 = doubleLargeArray5;
                j11 = j17;
            }
        } else {
            long j30 = j11;
            DoubleLargeArray doubleLargeArray7 = doubleLargeArray4;
            long j31 = 1;
            while (j31 <= j2) {
                long j32 = j3 + 1 + (((j31 * 5) - 4) * j);
                long j33 = j4 + ((j31 - 1) * j);
                long j34 = 0;
                while (j34 < j - 1) {
                    long j35 = j34 + j32;
                    long j36 = j32;
                    long j37 = j35 + j;
                    long j38 = j31;
                    long j39 = j35 - j;
                    long j40 = j37 + j;
                    long j41 = j10;
                    long j42 = j40 + j;
                    long j43 = j9;
                    DoubleLargeArray doubleLargeArray8 = doubleLargeArray;
                    long j44 = j8;
                    long j45 = j6;
                    double d33 = doubleLargeArray8.getDouble(j35 - 1);
                    double d34 = doubleLargeArray8.getDouble(j35);
                    double d35 = doubleLargeArray8.getDouble(j37 - 1);
                    double d36 = doubleLargeArray8.getDouble(j37);
                    double d37 = d35;
                    double d38 = doubleLargeArray8.getDouble(j39 - 1);
                    double d39 = doubleLargeArray8.getDouble(j39);
                    double d40 = doubleLargeArray8.getDouble(j40 - 1);
                    double d41 = doubleLargeArray8.getDouble(j40);
                    double d42 = d38;
                    double d43 = doubleLargeArray8.getDouble(j42 - 1);
                    double d44 = doubleLargeArray8.getDouble(j42);
                    double d45 = d34 - d44;
                    double d46 = d34 + d44;
                    double d47 = d36 - d41;
                    double d48 = d36 + d41;
                    double d49 = d33 - d43;
                    double d50 = d33 + d43;
                    double d51 = d37 - d40;
                    double d52 = d37 + d40;
                    double d53 = d42 + (d50 * 0.30901699437494745d) + (d52 * -0.8090169943749473d);
                    double d54 = d39 + (d46 * 0.30901699437494745d) + (d48 * -0.8090169943749473d);
                    double d55 = d42 + (d50 * -0.8090169943749473d) + (d52 * 0.30901699437494745d);
                    double d56 = d39 + (d46 * -0.8090169943749473d) + (d48 * 0.30901699437494745d);
                    double d57 = d48;
                    double d58 = d46;
                    double d59 = (double) j6;
                    double d60 = ((d49 * 0.9510565162951535d) + (d51 * 0.5877852522924732d)) * d59;
                    double d61 = ((d45 * 0.9510565162951535d) + (d47 * 0.5877852522924732d)) * d59;
                    double d62 = ((d49 * 0.5877852522924732d) - (d51 * 0.9510565162951535d)) * d59;
                    double d63 = ((d45 * 0.5877852522924732d) - (d47 * 0.9510565162951535d)) * d59;
                    double d64 = d55 - d63;
                    double d65 = d55 + d63;
                    double d66 = d56 + d62;
                    double d67 = d56 - d62;
                    double d68 = d53 + d61;
                    double d69 = d53 - d61;
                    double d70 = d54 - d60;
                    double d71 = d54 + d60;
                    long j46 = j34 + j5;
                    double d72 = d68;
                    long j47 = j34 + j44;
                    double d73 = d66;
                    long j48 = j34 + j43;
                    double d74 = d64;
                    long j49 = j34 + j41;
                    double d75 = this.wtablel.getDouble(j46);
                    double d76 = this.wtablel.getDouble(j46 + 1) * d59;
                    double d77 = this.wtablel.getDouble(j47);
                    double d78 = this.wtablel.getDouble(j47 + 1) * d59;
                    double d79 = this.wtablel.getDouble(j48);
                    double d80 = this.wtablel.getDouble(j48 + 1) * d59;
                    double d81 = this.wtablel.getDouble(j49);
                    double d82 = d59 * this.wtablel.getDouble(j49 + 1);
                    long j50 = j34 + j33;
                    double d83 = d82;
                    long j51 = j50 + j30;
                    double d84 = d80;
                    long j52 = j51 + j30;
                    double d85 = d78;
                    long j53 = j52 + j30;
                    long j54 = j52;
                    long j55 = j53 + j30;
                    double d86 = d42 + d50 + d52;
                    DoubleLargeArray doubleLargeArray9 = doubleLargeArray2;
                    doubleLargeArray9.setDouble(j50, d86);
                    doubleLargeArray9.setDouble(j50 + 1, d39 + d58 + d57);
                    doubleLargeArray9.setDouble(j51, (d75 * d69) - (d76 * d71));
                    doubleLargeArray9.setDouble(j51 + 1, (d75 * d71) + (d76 * d69));
                    long j56 = j54;
                    doubleLargeArray9.setDouble(j56, (d77 * d74) - (d85 * d73));
                    doubleLargeArray9.setDouble(j56 + 1, (d77 * d73) + (d85 * d74));
                    doubleLargeArray9.setDouble(j53, (d79 * d65) - (d84 * d67));
                    doubleLargeArray9.setDouble(j53 + 1, (d79 * d67) + (d84 * d65));
                    doubleLargeArray9.setDouble(j55, (d81 * d72) - (d83 * d70));
                    doubleLargeArray9.setDouble(j55 + 1, (d81 * d70) + (d83 * d72));
                    j34 += 2;
                    doubleLargeArray7 = doubleLargeArray9;
                    j12 = 2;
                    j32 = j36;
                    j31 = j38;
                    j10 = j41;
                    j9 = j43;
                    j8 = j44;
                }
                long j57 = j31;
                long j58 = j9;
                long j59 = j10;
                DoubleLargeArray doubleLargeArray10 = doubleLargeArray7;
                long j60 = j12;
                j31 = j57 + 1;
                j8 = j8;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void passfg(int[] iArr, int i, int i2, int i3, int i4, double[] dArr, int i5, double[] dArr2, int i6, int i7, int i8) {
        int i9;
        int i10;
        int i11 = i;
        int i12 = i2;
        int i13 = i3;
        int i14 = i4;
        double[] dArr3 = dArr;
        int i15 = i5;
        double[] dArr4 = dArr2;
        int i16 = i6;
        int i17 = i8;
        int i18 = i11 / 2;
        int i19 = (i12 + 1) / 2;
        int i20 = i12 * i11;
        if (i11 >= i13) {
            for (int i21 = 1; i21 < i19; i21++) {
                int i22 = i21 * i11;
                int i23 = (i12 - i21) * i11;
                for (int i24 = 0; i24 < i13; i24++) {
                    int i25 = i24 * i11;
                    int i26 = i25 + (i22 * i13);
                    int i27 = i25 + (i23 * i13);
                    int i28 = i25 * i12;
                    for (int i29 = 0; i29 < i11; i29++) {
                        int i30 = i16 + i29;
                        int i31 = i15 + i29;
                        double d = dArr3[i31 + i22 + i28];
                        double d2 = dArr3[i31 + i23 + i28];
                        dArr4[i30 + i26] = d + d2;
                        dArr4[i30 + i27] = d - d2;
                    }
                }
            }
            int i32 = 0;
            while (i32 < i13) {
                int i33 = i32 * i11;
                int i34 = i33 * i12;
                int i35 = i18;
                for (int i36 = 0; i36 < i11; i36++) {
                    dArr4[i16 + i36 + i33] = dArr3[i15 + i36 + i34];
                }
                i32++;
                i18 = i35;
            }
            i10 = i18;
            i9 = i20;
        } else {
            i10 = i18;
            int i37 = 1;
            while (i37 < i19) {
                int i38 = i12 - i37;
                int i39 = i37 * i13 * i11;
                int i40 = i38 * i13 * i11;
                int i41 = i37 * i11;
                int i42 = i38 * i11;
                int i43 = i20;
                int i44 = 0;
                while (i44 < i11) {
                    for (int i45 = 0; i45 < i13; i45++) {
                        int i46 = i45 * i11;
                        int i47 = i46 * i12;
                        int i48 = i15 + i44;
                        double d3 = dArr3[i48 + i41 + i47];
                        double d4 = dArr3[i48 + i42 + i47];
                        int i49 = i16 + i44 + i46;
                        dArr4[i49 + i39] = d3 + d4;
                        dArr4[i49 + i40] = d3 - d4;
                    }
                    i44++;
                    int i50 = i8;
                }
                i37++;
                int i51 = i8;
                i20 = i43;
            }
            i9 = i20;
            for (int i52 = 0; i52 < i11; i52++) {
                for (int i53 = 0; i53 < i13; i53++) {
                    int i54 = i53 * i11;
                    dArr4[i16 + i52 + i54] = dArr3[i15 + i52 + (i54 * i12)];
                }
            }
        }
        int i55 = 2 - i11;
        int i56 = (i12 - 1) * i14;
        int i57 = i55;
        int i58 = 1;
        int i59 = 0;
        while (i58 < i19) {
            int i60 = i57 + i11;
            int i61 = i58 * i14;
            int i62 = (i12 - i58) * i14;
            int i63 = i60 + i7;
            int i64 = i55;
            double[] dArr5 = this.wtable;
            double d5 = dArr5[i63 - 2];
            int i65 = i60;
            double d6 = (double) i8;
            double d7 = dArr5[i63 - 1] * d6;
            for (int i66 = 0; i66 < i14; i66++) {
                int i67 = i15 + i66;
                int i68 = i16 + i66;
                dArr3[i67 + i61] = dArr4[i68] + (dArr4[i68 + i14] * d5);
                dArr3[i67 + i62] = dArr4[i68 + i56] * d7;
            }
            i59 += i11;
            int i69 = i65;
            int i70 = 2;
            while (i70 < i19) {
                double d8 = d6;
                int i71 = i2 - i70;
                int i72 = i56;
                int i73 = i69 + i59;
                int i74 = i59;
                int i75 = i9;
                if (i73 > i75) {
                    i73 -= i75;
                }
                int i76 = i73 + i7;
                int i77 = i73;
                double[] dArr6 = this.wtable;
                double d9 = dArr6[i76 - 2];
                double d10 = dArr6[i76 - 1] * d8;
                int i78 = i70 * i14;
                int i79 = i71 * i14;
                i9 = i75;
                for (int i80 = 0; i80 < i14; i80++) {
                    int i81 = i15 + i80;
                    int i82 = i16 + i80;
                    int i83 = i81 + i61;
                    dArr3[i83] = dArr3[i83] + (dArr4[i82 + i78] * d9);
                    int i84 = i81 + i62;
                    dArr3[i84] = dArr3[i84] + (dArr4[i82 + i79] * d10);
                }
                i70++;
                i59 = i74;
                d6 = d8;
                i56 = i72;
                i69 = i77;
            }
            i12 = i2;
            int i85 = i56;
            int i86 = i59;
            i58++;
            int i87 = i3;
            i55 = i64;
            i57 = i65;
        }
        int i88 = i8;
        int i89 = i55;
        for (int i90 = 1; i90 < i19; i90++) {
            int i91 = i90 * i14;
            for (int i92 = 0; i92 < i14; i92++) {
                int i93 = i16 + i92;
                dArr4[i93] = dArr4[i93] + dArr4[i93 + i91];
            }
        }
        for (int i94 = 1; i94 < i19; i94++) {
            int i95 = i94 * i14;
            int i96 = (i12 - i94) * i14;
            for (int i97 = 1; i97 < i14; i97 += 2) {
                int i98 = i16 + i97;
                int i99 = i15 + i97;
                int i100 = i99 + i95;
                int i101 = i99 + i96;
                double d11 = dArr3[i100 - 1];
                double d12 = dArr3[i100];
                double d13 = dArr3[i101 - 1];
                double d14 = dArr3[i101];
                int i102 = i98 + i95;
                int i103 = i98 + i96;
                dArr4[i102 - 1] = d11 - d14;
                dArr4[i103 - 1] = d11 + d14;
                dArr4[i102] = d12 + d13;
                dArr4[i103] = d12 - d13;
            }
        }
        iArr[0] = 1;
        if (i11 != 2) {
            iArr[0] = 0;
            System.arraycopy(dArr4, i16, dArr3, i15, i14);
            int i104 = i3;
            int i105 = i104 * i11;
            for (int i106 = 1; i106 < i12; i106++) {
                int i107 = i106 * i105;
                for (int i108 = 0; i108 < i104; i108++) {
                    int i109 = i108 * i11;
                    int i110 = i16 + i109 + i107;
                    int i111 = i109 + i15 + i107;
                    dArr3[i111] = dArr4[i110];
                    dArr3[i111 + 1] = dArr4[i110 + 1];
                }
            }
            if (i10 <= i104) {
                int i112 = 0;
                for (int i113 = 1; i113 < i12; i113++) {
                    i112 += 2;
                    int i114 = i113 * i104 * i11;
                    int i115 = 3;
                    while (i115 < i11) {
                        int i116 = i112 + 2;
                        int i117 = i116 + i7;
                        double[] dArr7 = this.wtable;
                        double d15 = dArr7[i117 - 2];
                        int i118 = i116;
                        double d16 = ((double) i88) * dArr7[i117 - 1];
                        int i119 = i15 + i115;
                        int i120 = i16 + i115;
                        for (int i121 = 0; i121 < i104; i121++) {
                            int i122 = (i121 * i11) + i114;
                            int i123 = i119 + i122;
                            int i124 = i120 + i122;
                            double d17 = dArr4[i124 - 1];
                            double d18 = dArr4[i124];
                            dArr3[i123 - 1] = (d15 * d17) - (d16 * d18);
                            dArr3[i123] = (d18 * d15) + (d17 * d16);
                        }
                        i115 += 2;
                        i112 = i118;
                    }
                }
                return;
            }
            int i125 = i89;
            int i126 = 1;
            while (i126 < i12) {
                i125 += i11;
                int i127 = i126 * i104 * i11;
                int i128 = 0;
                while (i128 < i104) {
                    int i129 = (i128 * i11) + i127;
                    int i130 = i125;
                    int i131 = 3;
                    while (i131 < i11) {
                        int i132 = i130 + 2;
                        int i133 = i130 + 1 + i7;
                        double[] dArr8 = this.wtable;
                        double d19 = dArr8[i133 - 1];
                        int i134 = i125;
                        double d20 = ((double) i88) * dArr8[i133];
                        int i135 = i15 + i131 + i129;
                        int i136 = i16 + i131 + i129;
                        double d21 = dArr4[i136 - 1];
                        double d22 = dArr4[i136];
                        dArr3[i135 - 1] = (d19 * d21) - (d20 * d22);
                        dArr3[i135] = (d19 * d22) + (d20 * d21);
                        i131 += 2;
                        i11 = i;
                        i125 = i134;
                        i130 = i132;
                        i127 = i127;
                    }
                    int i137 = i125;
                    int i138 = i127;
                    i128++;
                    i11 = i;
                }
                int i139 = i125;
                i126++;
                i11 = i;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void passfg(int[] iArr, long j, long j2, long j3, long j4, DoubleLargeArray doubleLargeArray, long j5, DoubleLargeArray doubleLargeArray2, long j6, long j7, long j8) {
        long j9;
        long j10;
        DoubleLargeArray doubleLargeArray3 = doubleLargeArray;
        DoubleLargeArray doubleLargeArray4 = doubleLargeArray2;
        long j11 = j8;
        long j12 = j / 2;
        long j13 = (j2 + 1) / 2;
        long j14 = j2 * j;
        if (j >= j3) {
            long j15 = 1;
            while (j15 < j13) {
                long j16 = j15 * j;
                long j17 = (j2 - j15) * j;
                long j18 = 0;
                while (j18 < j3) {
                    long j19 = j18 * j;
                    long j20 = j19 + (j16 * j3);
                    long j21 = j19 + (j17 * j3);
                    long j22 = j19 * j2;
                    long j23 = 0;
                    while (j23 < j) {
                        long j24 = j6 + j23;
                        long j25 = j5 + j23;
                        double d = doubleLargeArray3.getDouble(j25 + j16 + j22);
                        long j26 = j17;
                        double d2 = doubleLargeArray3.getDouble(j25 + j17 + j22);
                        doubleLargeArray4.setDouble(j24 + j20, d + d2);
                        doubleLargeArray4.setDouble(j24 + j21, d - d2);
                        j23++;
                        long j27 = j8;
                        j14 = j14;
                        j17 = j26;
                    }
                    long j28 = j14;
                    long j29 = j17;
                    j18++;
                    long j30 = j8;
                }
                long j31 = j14;
                j15++;
                long j32 = j8;
            }
            j9 = j14;
            for (long j33 = 0; j33 < j3; j33++) {
                long j34 = j33 * j;
                long j35 = j34 * j2;
                long j36 = 0;
                while (j36 < j) {
                    doubleLargeArray4.setDouble(j6 + j36 + j34, doubleLargeArray3.getDouble(j5 + j36 + j35));
                    j36++;
                    j34 = j34;
                }
            }
            j10 = j13;
        } else {
            j9 = j14;
            long j37 = 1;
            while (j37 < j13) {
                long j38 = j2 - j37;
                long j39 = j37 * j3 * j;
                long j40 = j38 * j3 * j;
                long j41 = j37 * j;
                long j42 = j38 * j;
                long j43 = 0;
                while (j43 < j) {
                    long j44 = 0;
                    while (j44 < j3) {
                        long j45 = j44 * j;
                        long j46 = j45 * j2;
                        long j47 = j5 + j43;
                        long j48 = j41;
                        double d3 = doubleLargeArray3.getDouble(j47 + j41 + j46);
                        long j49 = j42;
                        double d4 = doubleLargeArray3.getDouble(j47 + j42 + j46);
                        long j50 = j6 + j43 + j45;
                        doubleLargeArray4.setDouble(j50 + j39, d3 + d4);
                        doubleLargeArray4.setDouble(j50 + j40, d3 - d4);
                        j44++;
                        j13 = j13;
                        j39 = j39;
                        j42 = j49;
                        j41 = j48;
                    }
                    long j51 = j13;
                    long j52 = j42;
                    long j53 = j39;
                    long j54 = j41;
                    j43++;
                }
                long j55 = j13;
                j37++;
            }
            j10 = j13;
            for (long j56 = 0; j56 < j; j56++) {
                for (long j57 = 0; j57 < j3; j57++) {
                    long j58 = j57 * j;
                    doubleLargeArray4.setDouble(j6 + j56 + j58, doubleLargeArray3.getDouble(j5 + j56 + (j58 * j2)));
                }
            }
        }
        long j59 = 2 - j;
        long j60 = (j2 - 1) * j4;
        long j61 = j59;
        long j62 = 1;
        long j63 = 0;
        while (j62 < j10) {
            long j64 = j61 + j;
            long j65 = j62 * j4;
            long j66 = (j2 - j62) * j4;
            long j67 = j64 + j7;
            long j68 = j64;
            long j69 = j59;
            double d5 = this.wtablel.getDouble(j67 - 2);
            long j70 = j62;
            double d6 = (double) j8;
            long j71 = j12;
            double d7 = this.wtablel.getDouble(j67 - 1) * d6;
            long j72 = 0;
            while (j72 < j4) {
                long j73 = j5 + j72;
                double d8 = d6;
                long j74 = j6 + j72;
                double d9 = d7;
                doubleLargeArray3.setDouble(j73 + j65, doubleLargeArray4.getDouble(j74) + (doubleLargeArray4.getDouble(j74 + j4) * d5));
                doubleLargeArray3.setDouble(j73 + j66, d9 * doubleLargeArray4.getDouble(j74 + j60));
                j72++;
                d6 = d8;
                j63 = j63;
                d7 = d9;
            }
            double d10 = d6;
            j63 += j;
            long j75 = j68;
            long j76 = 2;
            while (j76 < j10) {
                long j77 = j2 - j76;
                long j78 = j75 + j63;
                if (j78 > j9) {
                    j78 -= j9;
                }
                long j79 = j78 + j7;
                long j80 = j60;
                long j81 = j78;
                double d11 = this.wtablel.getDouble(j79 - 2);
                long j82 = j63;
                double d12 = this.wtablel.getDouble(j79 - 1) * d10;
                long j83 = j76 * j4;
                long j84 = j77 * j4;
                long j85 = 0;
                while (j85 < j4) {
                    long j86 = j5 + j85;
                    long j87 = j6 + j85;
                    long j88 = j76;
                    long j89 = j86 + j65;
                    double d13 = d12;
                    doubleLargeArray3.setDouble(j89, doubleLargeArray3.getDouble(j89) + (doubleLargeArray4.getDouble(j87 + j83) * d11));
                    long j90 = j86 + j66;
                    doubleLargeArray3.setDouble(j90, doubleLargeArray3.getDouble(j90) + (doubleLargeArray4.getDouble(j87 + j84) * d13));
                    j85++;
                    d11 = d11;
                    j76 = j88;
                    d12 = d13;
                }
                j76++;
                j60 = j80;
                j75 = j81;
                j63 = j82;
            }
            long j91 = j60;
            long j92 = j63;
            j62 = j70 + 1;
            j61 = j68;
            j59 = j69;
            j12 = j71;
        }
        long j93 = j59;
        long j94 = j12;
        for (long j95 = 1; j95 < j10; j95++) {
            long j96 = j95 * j4;
            for (long j97 = 0; j97 < j4; j97++) {
                long j98 = j6 + j97;
                doubleLargeArray4.setDouble(j98, doubleLargeArray4.getDouble(j98) + doubleLargeArray4.getDouble(j98 + j96));
            }
        }
        long j99 = 1;
        while (j99 < j10) {
            long j100 = j99 * j4;
            long j101 = (j2 - j99) * j4;
            long j102 = 1;
            while (j102 < j4) {
                long j103 = j6 + j102;
                long j104 = j5 + j102;
                long j105 = j99;
                long j106 = j104 + j100;
                long j107 = j104 + j101;
                long j108 = j102;
                double d14 = doubleLargeArray3.getDouble(j106 - 1);
                double d15 = doubleLargeArray3.getDouble(j106);
                double d16 = doubleLargeArray3.getDouble(j107 - 1);
                double d17 = doubleLargeArray3.getDouble(j107);
                long j109 = j103 + j100;
                long j110 = j103 + j101;
                doubleLargeArray4.setDouble(j109 - 1, d14 - d17);
                doubleLargeArray4.setDouble(j110 - 1, d14 + d17);
                doubleLargeArray4.setDouble(j109, d15 + d16);
                doubleLargeArray4.setDouble(j110, d15 - d16);
                j102 = j108 + 2;
                doubleLargeArray3 = doubleLargeArray;
                j99 = j105;
                j101 = j101;
                j100 = j100;
            }
            doubleLargeArray3 = doubleLargeArray;
            j99++;
        }
        iArr[0] = 1;
        if (j != 2) {
            iArr[0] = 0;
            DoubleFFT_1D doubleFFT_1D = this;
            LargeArrayUtils.arraycopy(doubleLargeArray2, j6, doubleLargeArray, j5, j4);
            long j111 = j3 * j;
            for (long j112 = 1; j112 < j2; j112++) {
                long j113 = j112 * j111;
                long j114 = 0;
                while (j114 < j3) {
                    long j115 = j114 * j;
                    long j116 = j6 + j115 + j113;
                    long j117 = j5 + j115 + j113;
                    DoubleLargeArray doubleLargeArray5 = doubleLargeArray;
                    doubleLargeArray5.setDouble(j117, doubleLargeArray4.getDouble(j116));
                    doubleLargeArray5.setDouble(j117 + 1, doubleLargeArray4.getDouble(j116 + 1));
                    j114++;
                    j111 = j111;
                }
                DoubleLargeArray doubleLargeArray6 = doubleLargeArray;
                long j118 = j111;
            }
            DoubleLargeArray doubleLargeArray7 = doubleLargeArray;
            if (j94 <= j3) {
                long j119 = 1;
                long j120 = 0;
                while (j119 < j2) {
                    long j121 = 2;
                    j120 += 2;
                    long j122 = j119 * j3 * j;
                    long j123 = 3;
                    while (j123 < j) {
                        long j124 = j120 + j121;
                        long j125 = j124 + j7;
                        long j126 = j125 - 1;
                        long j127 = j124;
                        double d18 = doubleFFT_1D.wtablel.getDouble(j125 - j121);
                        long j128 = j119;
                        double d19 = ((double) j8) * doubleFFT_1D.wtablel.getDouble(j126);
                        long j129 = j5 + j123;
                        long j130 = j6 + j123;
                        long j131 = 0;
                        while (j131 < j3) {
                            long j132 = (j131 * j) + j122;
                            long j133 = j122;
                            long j134 = j129 + j132;
                            long j135 = j129;
                            long j136 = j130 + j132;
                            double d20 = doubleLargeArray4.getDouble(j136 - 1);
                            double d21 = doubleLargeArray4.getDouble(j136);
                            DoubleLargeArray doubleLargeArray8 = doubleLargeArray;
                            doubleLargeArray8.setDouble(j134 - 1, (d18 * d20) - (d19 * d21));
                            doubleLargeArray8.setDouble(j134, (d21 * d18) + (d20 * d19));
                            j131++;
                            long j137 = j8;
                            doubleLargeArray7 = doubleLargeArray8;
                            j122 = j133;
                            j129 = j135;
                        }
                        DoubleLargeArray doubleLargeArray9 = doubleLargeArray7;
                        long j138 = j122;
                        j123 += 2;
                        j121 = 2;
                        j119 = j128;
                        j120 = j127;
                    }
                    DoubleLargeArray doubleLargeArray10 = doubleLargeArray7;
                    j119++;
                }
                return;
            }
            DoubleLargeArray doubleLargeArray11 = doubleLargeArray7;
            long j139 = 1;
            long j140 = j93;
            while (j139 < j2) {
                j140 += j;
                long j141 = j139 * j3 * j;
                long j142 = 0;
                while (j142 < j3) {
                    long j143 = (j142 * j) + j141;
                    long j144 = j140;
                    long j145 = 3;
                    while (j145 < j) {
                        long j146 = j144 + 2;
                        long j147 = j141;
                        long j148 = j144 + 1 + j7;
                        long j149 = j140;
                        double d22 = doubleFFT_1D.wtablel.getDouble(j148 - 1);
                        long j150 = j139;
                        long j151 = j142;
                        double d23 = ((double) j8) * doubleFFT_1D.wtablel.getDouble(j148);
                        long j152 = j5 + j145 + j143;
                        long j153 = j6 + j145 + j143;
                        long j154 = j143;
                        double d24 = doubleLargeArray4.getDouble(j153 - 1);
                        double d25 = doubleLargeArray4.getDouble(j153);
                        doubleLargeArray11.setDouble(j152 - 1, (d22 * d24) - (d23 * d25));
                        doubleLargeArray11.setDouble(j152, (d22 * d25) + (d23 * d24));
                        j145 += 2;
                        doubleFFT_1D = this;
                        doubleLargeArray4 = doubleLargeArray2;
                        j139 = j150;
                        j141 = j147;
                        j144 = j146;
                        j140 = j149;
                        j142 = j151;
                        j143 = j154;
                    }
                    long j155 = j141;
                    long j156 = j140;
                    j142++;
                    doubleFFT_1D = this;
                    doubleLargeArray4 = doubleLargeArray2;
                    j139 = j139;
                }
                long j157 = j140;
                doubleFFT_1D = this;
                doubleLargeArray4 = doubleLargeArray2;
                j139++;
            }
        }
    }
}
