package org.jtransforms.fft;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.math3.util.FastMath;
import org.jtransforms.utils.CommonUtils;
import pl.edu.icm.jlargearrays.ConcurrencyUtils;
import pl.edu.icm.jlargearrays.FloatLargeArray;
import pl.edu.icm.jlargearrays.LargeArray;
import pl.edu.icm.jlargearrays.LargeArrayUtils;
import pl.edu.icm.jlargearrays.LongLargeArray;

public final class FloatFFT_1D {
    private static final float PI = 3.1415927f;
    private static final float TWO_PI = 6.2831855f;
    private static final int[] factors = {4, 2, 3, 5};
    /* access modifiers changed from: private */
    public float[] bk1;
    /* access modifiers changed from: private */
    public FloatLargeArray bk1l;
    /* access modifiers changed from: private */
    public float[] bk2;
    /* access modifiers changed from: private */
    public FloatLargeArray bk2l;
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
    private float[] w;
    private FloatLargeArray wl;
    private float[] wtable;
    private float[] wtable_r;
    private FloatLargeArray wtable_rl;
    private FloatLargeArray wtablel;

    private enum Plans {
        SPLIT_RADIX,
        MIXED_RADIX,
        BLUESTEIN
    }

    public FloatFFT_1D(long j) {
        if (j >= 1) {
            boolean z = CommonUtils.isUseLargeArrays() || j * 2 > ((long) LargeArray.getMaxSizeOf32bitArray());
            this.useLargeArrays = z;
            this.n = (int) j;
            this.nl = j;
            if (!z) {
                if (CommonUtils.isPowerOf2(j)) {
                    this.plan = Plans.SPLIT_RADIX;
                    int[] iArr = new int[(((int) FastMath.ceil((double) ((1 << (((int) (FastMath.log((double) (((float) j) + 0.5f)) / FastMath.log(2.0d))) / 2)) + 2))) + 2)];
                    this.ip = iArr;
                    int i = this.n;
                    float[] fArr = new float[i];
                    this.w = fArr;
                    int i2 = (i * 2) >> 2;
                    this.nw = i2;
                    CommonUtils.makewt(i2, iArr, fArr);
                    int i3 = this.n >> 2;
                    this.nc = i3;
                    CommonUtils.makect(i3, this.w, this.nw, this.ip);
                } else if (CommonUtils.getReminder(j, factors) >= 211) {
                    this.plan = Plans.BLUESTEIN;
                    int nextPow2 = CommonUtils.nextPow2((this.n * 2) - 1);
                    this.nBluestein = nextPow2;
                    this.bk1 = new float[(nextPow2 * 2)];
                    this.bk2 = new float[(nextPow2 * 2)];
                    int[] iArr2 = new int[(((int) FastMath.ceil((double) ((1 << (((int) (FastMath.log((double) (((float) nextPow2) + 0.5f)) / FastMath.log(2.0d))) / 2)) + 2))) + 2)];
                    this.ip = iArr2;
                    int i4 = this.nBluestein;
                    float[] fArr2 = new float[i4];
                    this.w = fArr2;
                    int i5 = (i4 * 2) >> 2;
                    this.nw = i5;
                    CommonUtils.makewt(i5, iArr2, fArr2);
                    int i6 = this.nBluestein >> 2;
                    this.nc = i6;
                    CommonUtils.makect(i6, this.w, this.nw, this.ip);
                    bluesteini();
                } else {
                    this.plan = Plans.MIXED_RADIX;
                    int i7 = this.n;
                    this.wtable = new float[((i7 * 4) + 15)];
                    this.wtable_r = new float[((i7 * 2) + 15)];
                    cffti();
                    rffti();
                }
            } else if (CommonUtils.isPowerOf2(j)) {
                this.plan = Plans.SPLIT_RADIX;
                this.ipl = new LongLargeArray(((long) FastMath.ceil((double) ((1 << ((int) (((long) (FastMath.log((double) (((float) this.nl) + 0.5f)) / FastMath.log(2.0d))) / 2))) + 2))) + 2);
                FloatLargeArray floatLargeArray = new FloatLargeArray(this.nl);
                this.wl = floatLargeArray;
                long j2 = (this.nl * 2) >> 2;
                this.nwl = j2;
                CommonUtils.makewt(j2, this.ipl, floatLargeArray);
                long j3 = this.nl >> 2;
                this.ncl = j3;
                CommonUtils.makect(j3, this.wl, this.nwl, this.ipl);
            } else if (CommonUtils.getReminder(this.nl, factors) >= 211) {
                this.plan = Plans.BLUESTEIN;
                this.nBluesteinl = CommonUtils.nextPow2((this.nl * 2) - 1);
                this.bk1l = new FloatLargeArray(this.nBluesteinl * 2);
                this.bk2l = new FloatLargeArray(this.nBluesteinl * 2);
                this.ipl = new LongLargeArray(((long) FastMath.ceil((double) ((1 << ((int) (((long) (FastMath.log((double) (((float) this.nBluesteinl) + 0.5f)) / FastMath.log(2.0d))) / 2))) + 2))) + 2);
                FloatLargeArray floatLargeArray2 = new FloatLargeArray(this.nBluesteinl);
                this.wl = floatLargeArray2;
                long j4 = (this.nBluesteinl * 2) >> 2;
                this.nwl = j4;
                CommonUtils.makewt(j4, this.ipl, floatLargeArray2);
                long j5 = this.nBluesteinl >> 2;
                this.ncl = j5;
                CommonUtils.makect(j5, this.wl, this.nwl, this.ipl);
                bluesteinil();
            } else {
                this.plan = Plans.MIXED_RADIX;
                this.wtablel = new FloatLargeArray((this.nl * 4) + 15);
                this.wtable_rl = new FloatLargeArray((this.nl * 2) + 15);
                cfftil();
                rfftil();
            }
        } else {
            throw new IllegalArgumentException("n must be greater than 0");
        }
    }

    public void complexForward(float[] fArr) {
        complexForward(fArr, 0);
    }

    public void complexForward(FloatLargeArray floatLargeArray) {
        complexForward(floatLargeArray, 0);
    }

    public void complexForward(float[] fArr, int i) {
        if (this.useLargeArrays) {
            complexForward(new FloatLargeArray(fArr), (long) i);
        } else if (this.n != 1) {
            int i2 = AnonymousClass29.$SwitchMap$org$jtransforms$fft$FloatFFT_1D$Plans[this.plan.ordinal()];
            if (i2 == 1) {
                CommonUtils.cftbsub(this.n * 2, fArr, i, this.ip, this.nw, this.w);
            } else if (i2 == 2) {
                cfftf(fArr, i, -1);
            } else if (i2 == 3) {
                bluestein_complex(fArr, i, -1);
            }
        }
    }

    /* renamed from: org.jtransforms.fft.FloatFFT_1D$29  reason: invalid class name */
    static /* synthetic */ class AnonymousClass29 {
        static final /* synthetic */ int[] $SwitchMap$org$jtransforms$fft$FloatFFT_1D$Plans;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                org.jtransforms.fft.FloatFFT_1D$Plans[] r0 = org.jtransforms.fft.FloatFFT_1D.Plans.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$jtransforms$fft$FloatFFT_1D$Plans = r0
                org.jtransforms.fft.FloatFFT_1D$Plans r1 = org.jtransforms.fft.FloatFFT_1D.Plans.SPLIT_RADIX     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$jtransforms$fft$FloatFFT_1D$Plans     // Catch:{ NoSuchFieldError -> 0x001d }
                org.jtransforms.fft.FloatFFT_1D$Plans r1 = org.jtransforms.fft.FloatFFT_1D.Plans.MIXED_RADIX     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$jtransforms$fft$FloatFFT_1D$Plans     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.jtransforms.fft.FloatFFT_1D$Plans r1 = org.jtransforms.fft.FloatFFT_1D.Plans.BLUESTEIN     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.FloatFFT_1D.AnonymousClass29.<clinit>():void");
        }
    }

    public void complexForward(FloatLargeArray floatLargeArray, long j) {
        if (!this.useLargeArrays) {
            if (floatLargeArray.isLarge() || floatLargeArray.isConstant() || j >= 2147483647L) {
                throw new IllegalArgumentException("The data array is too big.");
            }
            complexForward(floatLargeArray.getData(), (int) j);
        } else if (this.nl != 1) {
            int i = AnonymousClass29.$SwitchMap$org$jtransforms$fft$FloatFFT_1D$Plans[this.plan.ordinal()];
            if (i == 1) {
                CommonUtils.cftbsub(2 * this.nl, floatLargeArray, j, this.ipl, this.nwl, this.wl);
            } else if (i == 2) {
                cfftf(floatLargeArray, j, -1);
            } else if (i == 3) {
                bluestein_complex(floatLargeArray, j, -1);
            }
        }
    }

    public void complexInverse(float[] fArr, boolean z) {
        complexInverse(fArr, 0, z);
    }

    public void complexInverse(FloatLargeArray floatLargeArray, boolean z) {
        complexInverse(floatLargeArray, 0, z);
    }

    public void complexInverse(float[] fArr, int i, boolean z) {
        if (this.useLargeArrays) {
            complexInverse(new FloatLargeArray(fArr), (long) i, z);
        } else if (this.n != 1) {
            int i2 = AnonymousClass29.$SwitchMap$org$jtransforms$fft$FloatFFT_1D$Plans[this.plan.ordinal()];
            if (i2 == 1) {
                CommonUtils.cftfsub(this.n * 2, fArr, i, this.ip, this.nw, this.w);
            } else if (i2 == 2) {
                cfftf(fArr, i, 1);
            } else if (i2 == 3) {
                bluestein_complex(fArr, i, 1);
            }
            if (z) {
                int i3 = this.n;
                CommonUtils.scale(i3, 1.0f / ((float) i3), fArr, i, true);
            }
        }
    }

    public void complexInverse(FloatLargeArray floatLargeArray, long j, boolean z) {
        FloatLargeArray floatLargeArray2 = floatLargeArray;
        long j2 = j;
        boolean z2 = z;
        if (!this.useLargeArrays) {
            if (floatLargeArray.isLarge() || floatLargeArray.isConstant() || j2 >= 2147483647L) {
                throw new IllegalArgumentException("The data array is too big.");
            }
            complexInverse(floatLargeArray.getData(), (int) j2, z2);
        } else if (this.nl != 1) {
            int i = AnonymousClass29.$SwitchMap$org$jtransforms$fft$FloatFFT_1D$Plans[this.plan.ordinal()];
            if (i == 1) {
                CommonUtils.cftfsub(2 * this.nl, floatLargeArray, j, this.ipl, this.nwl, this.wl);
            } else if (i == 2) {
                cfftf(floatLargeArray, j2, 1);
            } else if (i == 3) {
                bluestein_complex(floatLargeArray, j2, 1);
            }
            if (z2) {
                long j3 = this.nl;
                CommonUtils.scale(j3, 1.0f / ((float) j3), floatLargeArray, j, true);
            }
        }
    }

    public void realForward(float[] fArr) {
        realForward(fArr, 0);
    }

    public void realForward(FloatLargeArray floatLargeArray) {
        realForward(floatLargeArray, 0);
    }

    public void realForward(float[] fArr, int i) {
        if (this.useLargeArrays) {
            realForward(new FloatLargeArray(fArr), (long) i);
        } else if (this.n != 1) {
            int i2 = AnonymousClass29.$SwitchMap$org$jtransforms$fft$FloatFFT_1D$Plans[this.plan.ordinal()];
            if (i2 == 1) {
                int i3 = this.n;
                if (i3 > 4) {
                    float[] fArr2 = fArr;
                    int i4 = i;
                    CommonUtils.cftfsub(i3, fArr2, i4, this.ip, this.nw, this.w);
                    CommonUtils.rftfsub(this.n, fArr2, i4, this.nc, this.w, this.nw);
                } else if (i3 == 4) {
                    CommonUtils.cftx020(fArr, i);
                }
                float f = fArr[i];
                int i5 = i + 1;
                float f2 = fArr[i5];
                fArr[i] = f + f2;
                fArr[i5] = f - f2;
            } else if (i2 == 2) {
                rfftf(fArr, i);
                for (int i6 = this.n - 1; i6 >= 2; i6--) {
                    int i7 = i + i6;
                    float f3 = fArr[i7];
                    int i8 = i7 - 1;
                    fArr[i7] = fArr[i8];
                    fArr[i8] = f3;
                }
            } else if (i2 == 3) {
                bluestein_real_forward(fArr, i);
            }
        }
    }

    public void realForward(FloatLargeArray floatLargeArray, long j) {
        FloatLargeArray floatLargeArray2 = floatLargeArray;
        long j2 = j;
        if (!this.useLargeArrays) {
            if (floatLargeArray.isLarge() || floatLargeArray.isConstant() || j2 >= 2147483647L) {
                throw new IllegalArgumentException("The data array is too big.");
            }
            realForward(floatLargeArray.getData(), (int) j2);
        } else if (this.nl != 1) {
            int i = AnonymousClass29.$SwitchMap$org$jtransforms$fft$FloatFFT_1D$Plans[this.plan.ordinal()];
            if (i == 1) {
                long j3 = this.nl;
                if (j3 > 4) {
                    FloatLargeArray floatLargeArray3 = floatLargeArray;
                    long j4 = j;
                    CommonUtils.cftfsub(j3, floatLargeArray3, j4, this.ipl, this.nwl, this.wl);
                    CommonUtils.rftfsub(this.nl, floatLargeArray3, j4, this.ncl, this.wl, this.nwl);
                } else if (j3 == 4) {
                    CommonUtils.cftx020(floatLargeArray, j);
                }
                long j5 = j2 + 1;
                float f = floatLargeArray.getFloat(j) - floatLargeArray2.getFloat(j5);
                floatLargeArray2.setFloat(j2, floatLargeArray.getFloat(j) + floatLargeArray2.getFloat(j5));
                floatLargeArray2.setFloat(j5, f);
            } else if (i == 2) {
                rfftf(floatLargeArray, j);
                long j6 = this.nl;
                while (true) {
                    j6--;
                    if (j6 >= 2) {
                        long j7 = j2 + j6;
                        float f2 = floatLargeArray2.getFloat(j7);
                        long j8 = j7 - 1;
                        floatLargeArray2.setFloat(j7, floatLargeArray2.getFloat(j8));
                        floatLargeArray2.setFloat(j8, f2);
                    } else {
                        return;
                    }
                }
            } else if (i == 3) {
                bluestein_real_forward(floatLargeArray, j);
            }
        }
    }

    public void realForwardFull(float[] fArr) {
        realForwardFull(fArr, 0);
    }

    public void realForwardFull(FloatLargeArray floatLargeArray) {
        realForwardFull(floatLargeArray, 0);
    }

    public void realForwardFull(float[] fArr, int i) {
        int i2;
        float[] fArr2 = fArr;
        int i3 = i;
        Class<FloatFFT_1D> cls = FloatFFT_1D.class;
        if (this.useLargeArrays) {
            realForwardFull(new FloatLargeArray(fArr2), (long) i3);
            return;
        }
        int i4 = this.n * 2;
        int i5 = AnonymousClass29.$SwitchMap$org$jtransforms$fft$FloatFFT_1D$Plans[this.plan.ordinal()];
        if (i5 == 1) {
            realForward(fArr, i);
            int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
            if (numberOfThreads <= 1 || ((long) (this.n / 2)) <= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
                for (int i6 = 0; i6 < this.n / 2; i6++) {
                    int i7 = i6 * 2;
                    int i8 = ((i4 - i7) % i4) + i3;
                    int i9 = i7 + i3;
                    fArr2[i8] = fArr2[i9];
                    fArr2[i8 + 1] = -fArr2[i9 + 1];
                }
            } else {
                Future[] futureArr = new Future[numberOfThreads];
                int i10 = (this.n / 2) / numberOfThreads;
                int i11 = 0;
                while (i11 < numberOfThreads) {
                    final int i12 = i11 * i10;
                    final int i13 = i11 == numberOfThreads + -1 ? this.n / 2 : i12 + i10;
                    final int i14 = i;
                    int i15 = i11;
                    final int i16 = i4;
                    Future[] futureArr2 = futureArr;
                    final float[] fArr3 = fArr;
                    futureArr2[i15] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            for (int i = i12; i < i13; i++) {
                                int i2 = i * 2;
                                int i3 = i14;
                                int i4 = i16;
                                int i5 = ((i4 - i2) % i4) + i3;
                                float[] fArr = fArr3;
                                fArr[i5] = fArr[i3 + i2];
                                fArr[i5 + 1] = -fArr[i3 + i2 + 1];
                            }
                        }
                    });
                    i11 = i15 + 1;
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
            int i17 = i3 + 1;
            fArr2[this.n + i3] = -fArr2[i17];
            fArr2[i17] = 0.0f;
        } else if (i5 == 2) {
            rfftf(fArr, i);
            int i18 = this.n;
            if (i18 % 2 == 0) {
                i2 = i18 / 2;
            } else {
                i2 = (i18 + 1) / 2;
            }
            for (int i19 = 1; i19 < i2; i19++) {
                int i20 = i19 * 2;
                int i21 = (i3 + i4) - i20;
                int i22 = i20 + i3;
                fArr2[i21 + 1] = -fArr2[i22];
                fArr2[i21] = fArr2[i22 - 1];
            }
            int i23 = 1;
            while (true) {
                int i24 = this.n;
                if (i23 < i24) {
                    int i25 = (i24 + i3) - i23;
                    int i26 = i25 + 1;
                    float f = fArr2[i26];
                    fArr2[i26] = fArr2[i25];
                    fArr2[i25] = f;
                    i23++;
                } else {
                    fArr2[i3 + 1] = 0.0f;
                    return;
                }
            }
        } else if (i5 == 3) {
            bluestein_real_full(fArr2, i3, -1);
        }
    }

    public void realForwardFull(FloatLargeArray floatLargeArray, long j) {
        long j2;
        FloatLargeArray floatLargeArray2 = floatLargeArray;
        long j3 = j;
        Class<FloatFFT_1D> cls = FloatFFT_1D.class;
        if (this.useLargeArrays) {
            long j4 = this.nl * 2;
            int i = AnonymousClass29.$SwitchMap$org$jtransforms$fft$FloatFFT_1D$Plans[this.plan.ordinal()];
            if (i == 1) {
                realForward(floatLargeArray, j);
                int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
                if (numberOfThreads <= 1 || this.nl / 2 <= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
                    for (long j5 = 0; j5 < this.nl / 2; j5++) {
                        long j6 = j5 * 2;
                        long j7 = ((j4 - j6) % j4) + j3;
                        long j8 = j6 + j3;
                        floatLargeArray2.setFloat(j7, floatLargeArray2.getFloat(j8));
                        floatLargeArray2.setFloat(j7 + 1, -floatLargeArray2.getFloat(j8 + 1));
                    }
                } else {
                    Future[] futureArr = new Future[numberOfThreads];
                    long j9 = (this.nl / 2) / ((long) numberOfThreads);
                    int i2 = 0;
                    while (i2 < numberOfThreads) {
                        final long j10 = ((long) i2) * j9;
                        final long j11 = i2 == numberOfThreads + -1 ? this.nl / 2 : j10 + j9;
                        final long j12 = j;
                        Future[] futureArr2 = futureArr;
                        int i3 = i2;
                        final long j13 = j4;
                        final FloatLargeArray floatLargeArray3 = floatLargeArray;
                        futureArr2[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                for (long j = j10; j < j11; j++) {
                                    long j2 = 2 * j;
                                    long j3 = j12;
                                    long j4 = j13;
                                    long j5 = ((j4 - j2) % j4) + j3;
                                    FloatLargeArray floatLargeArray = floatLargeArray3;
                                    floatLargeArray.setFloat(j5, floatLargeArray.getFloat(j3 + j2));
                                    FloatLargeArray floatLargeArray2 = floatLargeArray3;
                                    floatLargeArray2.setFloat(j5 + 1, -floatLargeArray2.getFloat(j12 + j2 + 1));
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
                floatLargeArray2.setFloat(this.nl + j3, -floatLargeArray2.getFloat(j14));
                floatLargeArray2.setFloat(j14, 0.0f);
            } else if (i == 2) {
                rfftf(floatLargeArray, j);
                long j15 = this.nl;
                if (j15 % 2 == 0) {
                    j2 = j15 / 2;
                } else {
                    j2 = (j15 + 1) / 2;
                }
                for (long j16 = 1; j16 < j2; j16++) {
                    long j17 = j16 * 2;
                    long j18 = (j3 + j4) - j17;
                    long j19 = j17 + j3;
                    floatLargeArray2.setFloat(j18 + 1, -floatLargeArray2.getFloat(j19));
                    floatLargeArray2.setFloat(j18, floatLargeArray2.getFloat(j19 - 1));
                }
                long j20 = 1;
                while (true) {
                    long j21 = this.nl;
                    if (j20 < j21) {
                        long j22 = (j21 + j3) - j20;
                        long j23 = j22 + 1;
                        float f = floatLargeArray2.getFloat(j23);
                        floatLargeArray2.setFloat(j23, floatLargeArray2.getFloat(j22));
                        floatLargeArray2.setFloat(j22, f);
                        j20++;
                    } else {
                        floatLargeArray2.setFloat(j3 + 1, 0.0f);
                        return;
                    }
                }
            } else if (i == 3) {
                bluestein_real_full(floatLargeArray, j, -1);
            }
        } else if (floatLargeArray.isLarge() || floatLargeArray.isConstant() || j3 >= 2147483647L) {
            throw new IllegalArgumentException("The data array is too big.");
        } else {
            realForwardFull(floatLargeArray.getData(), (int) j3);
        }
    }

    public void realInverse(float[] fArr, boolean z) {
        realInverse(fArr, 0, z);
    }

    public void realInverse(FloatLargeArray floatLargeArray, boolean z) {
        realInverse(floatLargeArray, 0, z);
    }

    public void realInverse(float[] fArr, int i, boolean z) {
        if (this.useLargeArrays) {
            realInverse(new FloatLargeArray(fArr), (long) i, z);
        } else if (this.n != 1) {
            int i2 = AnonymousClass29.$SwitchMap$org$jtransforms$fft$FloatFFT_1D$Plans[this.plan.ordinal()];
            if (i2 != 1) {
                if (i2 == 2) {
                    for (int i3 = 2; i3 < this.n; i3++) {
                        int i4 = i + i3;
                        int i5 = i4 - 1;
                        float f = fArr[i5];
                        fArr[i5] = fArr[i4];
                        fArr[i4] = f;
                    }
                    rfftb(fArr, i);
                    if (z) {
                        int i6 = this.n;
                        CommonUtils.scale(i6, 1.0f / ((float) i6), fArr, i, false);
                    }
                } else if (i2 == 3) {
                    bluestein_real_inverse(fArr, i);
                    if (z) {
                        int i7 = this.n;
                        CommonUtils.scale(i7, 1.0f / ((float) i7), fArr, i, false);
                    }
                }
            } else {
                int i8 = i + 1;
                float f2 = (fArr[i] - fArr[i8]) * 0.5f;
                fArr[i8] = f2;
                fArr[i] = fArr[i] - f2;
                int i9 = this.n;
                if (i9 > 4) {
                    float[] fArr2 = fArr;
                    int i10 = i;
                    CommonUtils.rftfsub(i9, fArr2, i10, this.nc, this.w, this.nw);
                    CommonUtils.cftbsub(this.n, fArr2, i10, this.ip, this.nw, this.w);
                } else if (i9 == 4) {
                    CommonUtils.cftxc020(fArr, i);
                }
                if (z) {
                    int i11 = this.n;
                    CommonUtils.scale(i11, 1.0f / (((float) i11) / 2.0f), fArr, i, false);
                }
            }
        }
    }

    public void realInverse(FloatLargeArray floatLargeArray, long j, boolean z) {
        FloatLargeArray floatLargeArray2 = floatLargeArray;
        long j2 = j;
        boolean z2 = z;
        if (!this.useLargeArrays) {
            if (floatLargeArray.isLarge() || floatLargeArray.isConstant() || j2 >= 2147483647L) {
                throw new IllegalArgumentException("The data array is too big.");
            }
            realInverse(floatLargeArray.getData(), (int) j2, z2);
        } else if (this.nl != 1) {
            int i = AnonymousClass29.$SwitchMap$org$jtransforms$fft$FloatFFT_1D$Plans[this.plan.ordinal()];
            if (i == 1) {
                long j3 = j2 + 1;
                floatLargeArray2.setFloat(j3, (floatLargeArray.getFloat(j) - floatLargeArray2.getFloat(j3)) * 0.5f);
                floatLargeArray2.setFloat(j2, floatLargeArray.getFloat(j) - floatLargeArray2.getFloat(j3));
                long j4 = this.nl;
                if (j4 > 4) {
                    FloatLargeArray floatLargeArray3 = floatLargeArray;
                    long j5 = j;
                    CommonUtils.rftfsub(j4, floatLargeArray3, j5, this.ncl, this.wl, this.nwl);
                    CommonUtils.cftbsub(this.nl, floatLargeArray3, j5, this.ipl, this.nwl, this.wl);
                } else if (j4 == 4) {
                    CommonUtils.cftxc020(floatLargeArray, j);
                }
                if (z2) {
                    long j6 = this.nl;
                    CommonUtils.scale(j6, 1.0f / (((float) j6) / 2.0f), floatLargeArray, j, false);
                }
            } else if (i == 2) {
                for (long j7 = 2; j7 < this.nl; j7++) {
                    long j8 = j2 + j7;
                    long j9 = j8 - 1;
                    float f = floatLargeArray2.getFloat(j9);
                    floatLargeArray2.setFloat(j9, floatLargeArray2.getFloat(j8));
                    floatLargeArray2.setFloat(j8, f);
                }
                rfftb(floatLargeArray, j);
                if (z2) {
                    long j10 = this.nl;
                    CommonUtils.scale(j10, 1.0f / ((float) j10), floatLargeArray, j, false);
                }
            } else if (i == 3) {
                bluestein_real_inverse(floatLargeArray, j);
                if (z2) {
                    long j11 = this.nl;
                    CommonUtils.scale(j11, 1.0f / ((float) j11), floatLargeArray, j, false);
                }
            }
        }
    }

    public void realInverseFull(float[] fArr, boolean z) {
        realInverseFull(fArr, 0, z);
    }

    public void realInverseFull(FloatLargeArray floatLargeArray, boolean z) {
        realInverseFull(floatLargeArray, 0, z);
    }

    public void realInverseFull(float[] fArr, int i, boolean z) {
        int i2;
        float[] fArr2 = fArr;
        int i3 = i;
        boolean z2 = z;
        Class<FloatFFT_1D> cls = FloatFFT_1D.class;
        if (this.useLargeArrays) {
            realInverseFull(new FloatLargeArray(fArr2), (long) i3, z2);
            return;
        }
        int i4 = this.n * 2;
        int i5 = AnonymousClass29.$SwitchMap$org$jtransforms$fft$FloatFFT_1D$Plans[this.plan.ordinal()];
        if (i5 == 1) {
            realInverse2(fArr, i, z);
            int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
            if (numberOfThreads <= 1 || ((long) (this.n / 2)) <= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
                for (int i6 = 0; i6 < this.n / 2; i6++) {
                    int i7 = i6 * 2;
                    int i8 = ((i4 - i7) % i4) + i3;
                    int i9 = i7 + i3;
                    fArr2[i8] = fArr2[i9];
                    fArr2[i8 + 1] = -fArr2[i9 + 1];
                }
            } else {
                Future[] futureArr = new Future[numberOfThreads];
                int i10 = (this.n / 2) / numberOfThreads;
                int i11 = 0;
                while (i11 < numberOfThreads) {
                    final int i12 = i11 * i10;
                    final int i13 = i11 == numberOfThreads + -1 ? this.n / 2 : i12 + i10;
                    final int i14 = i;
                    int i15 = i11;
                    final int i16 = i4;
                    Future[] futureArr2 = futureArr;
                    final float[] fArr3 = fArr;
                    futureArr2[i15] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            for (int i = i12; i < i13; i++) {
                                int i2 = i * 2;
                                int i3 = i14;
                                int i4 = i16;
                                int i5 = ((i4 - i2) % i4) + i3;
                                float[] fArr = fArr3;
                                fArr[i5] = fArr[i3 + i2];
                                fArr[i5 + 1] = -fArr[i3 + i2 + 1];
                            }
                        }
                    });
                    i11 = i15 + 1;
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
            int i17 = i3 + 1;
            fArr2[this.n + i3] = -fArr2[i17];
            fArr2[i17] = 0.0f;
        } else if (i5 == 2) {
            rfftf(fArr, i);
            if (z2) {
                int i18 = this.n;
                CommonUtils.scale(i18, 1.0f / ((float) i18), fArr2, i3, false);
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
                float f = -fArr2[i22];
                fArr2[i22] = f;
                fArr2[i23 + 1] = -f;
                fArr2[i23] = fArr2[i22 - 1];
            }
            int i24 = 1;
            while (true) {
                int i25 = this.n;
                if (i24 < i25) {
                    int i26 = (i25 + i3) - i24;
                    int i27 = i26 + 1;
                    float f2 = fArr2[i27];
                    fArr2[i27] = fArr2[i26];
                    fArr2[i26] = f2;
                    i24++;
                } else {
                    fArr2[i3 + 1] = 0.0f;
                    return;
                }
            }
        } else if (i5 == 3) {
            bluestein_real_full(fArr2, i3, 1);
            if (z2) {
                int i28 = this.n;
                CommonUtils.scale(i28, 1.0f / ((float) i28), fArr2, i3, true);
            }
        }
    }

    public void realInverseFull(FloatLargeArray floatLargeArray, long j, boolean z) {
        long j2;
        FloatLargeArray floatLargeArray2 = floatLargeArray;
        long j3 = j;
        boolean z2 = z;
        Class<FloatFFT_1D> cls = FloatFFT_1D.class;
        if (this.useLargeArrays) {
            long j4 = this.nl * 2;
            int i = AnonymousClass29.$SwitchMap$org$jtransforms$fft$FloatFFT_1D$Plans[this.plan.ordinal()];
            if (i == 1) {
                realInverse2(floatLargeArray, j, z);
                int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
                if (numberOfThreads <= 1 || this.nl / 2 <= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
                    for (long j5 = 0; j5 < this.nl / 2; j5++) {
                        long j6 = j5 * 2;
                        long j7 = ((j4 - j6) % j4) + j3;
                        long j8 = j6 + j3;
                        floatLargeArray2.setFloat(j7, floatLargeArray2.getFloat(j8));
                        floatLargeArray2.setFloat(j7 + 1, -floatLargeArray2.getFloat(j8 + 1));
                    }
                } else {
                    Future[] futureArr = new Future[numberOfThreads];
                    long j9 = (this.nl / 2) / ((long) numberOfThreads);
                    int i2 = 0;
                    while (i2 < numberOfThreads) {
                        final long j10 = ((long) i2) * j9;
                        final long j11 = i2 == numberOfThreads + -1 ? this.nl / 2 : j10 + j9;
                        final long j12 = j;
                        Future[] futureArr2 = futureArr;
                        int i3 = i2;
                        final long j13 = j4;
                        final FloatLargeArray floatLargeArray3 = floatLargeArray;
                        futureArr2[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                for (long j = j10; j < j11; j++) {
                                    long j2 = 2 * j;
                                    long j3 = j12;
                                    long j4 = j13;
                                    long j5 = ((j4 - j2) % j4) + j3;
                                    FloatLargeArray floatLargeArray = floatLargeArray3;
                                    floatLargeArray.setFloat(j5, floatLargeArray.getFloat(j3 + j2));
                                    FloatLargeArray floatLargeArray2 = floatLargeArray3;
                                    floatLargeArray2.setFloat(j5 + 1, -floatLargeArray2.getFloat(j12 + j2 + 1));
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
                floatLargeArray2.setFloat(this.nl + j3, -floatLargeArray2.getFloat(j14));
                floatLargeArray2.setFloat(j14, 0.0f);
            } else if (i == 2) {
                rfftf(floatLargeArray, j);
                if (z2) {
                    long j15 = this.nl;
                    CommonUtils.scale(j15, 1.0f / ((float) j15), floatLargeArray, j, false);
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
                    floatLargeArray2.setFloat(j19, -floatLargeArray2.getFloat(j19));
                    floatLargeArray2.setFloat(j20 + 1, -floatLargeArray2.getFloat(j19));
                    floatLargeArray2.setFloat(j20, floatLargeArray2.getFloat(j19 - 1));
                }
                long j21 = 1;
                while (true) {
                    long j22 = this.nl;
                    if (j21 < j22) {
                        long j23 = (j22 + j3) - j21;
                        long j24 = j23 + 1;
                        float f = floatLargeArray2.getFloat(j24);
                        floatLargeArray2.setFloat(j24, floatLargeArray2.getFloat(j23));
                        floatLargeArray2.setFloat(j23, f);
                        j21++;
                    } else {
                        floatLargeArray2.setFloat(j3 + 1, 0.0f);
                        return;
                    }
                }
            } else if (i == 3) {
                bluestein_real_full(floatLargeArray, j, 1);
                if (z2) {
                    long j25 = this.nl;
                    CommonUtils.scale(j25, 1.0f / ((float) j25), floatLargeArray, j, true);
                }
            }
        } else if (floatLargeArray.isLarge() || floatLargeArray.isConstant() || j3 >= 2147483647L) {
            throw new IllegalArgumentException("The data array is too big.");
        } else {
            realInverseFull(floatLargeArray.getData(), (int) j3, z2);
        }
    }

    /* access modifiers changed from: protected */
    public void realInverse2(float[] fArr, int i, boolean z) {
        float[] fArr2 = fArr;
        int i2 = i;
        boolean z2 = z;
        if (this.useLargeArrays) {
            realInverse2(new FloatLargeArray(fArr), (long) i2, z2);
        } else if (this.n != 1) {
            int i3 = AnonymousClass29.$SwitchMap$org$jtransforms$fft$FloatFFT_1D$Plans[this.plan.ordinal()];
            if (i3 == 1) {
                int i4 = this.n;
                if (i4 > 4) {
                    float[] fArr3 = fArr;
                    int i5 = i;
                    CommonUtils.cftfsub(i4, fArr3, i5, this.ip, this.nw, this.w);
                    CommonUtils.rftbsub(this.n, fArr3, i5, this.nc, this.w, this.nw);
                } else if (i4 == 4) {
                    CommonUtils.cftbsub(i4, fArr, i, this.ip, this.nw, this.w);
                }
                float f = fArr2[i2];
                int i6 = i2 + 1;
                float f2 = fArr2[i6];
                fArr2[i2] = f + f2;
                fArr2[i6] = f - f2;
                if (z2) {
                    int i7 = this.n;
                    CommonUtils.scale(i7, 1.0f / ((float) i7), fArr, i, false);
                }
            } else if (i3 == 2) {
                rfftf(fArr, i);
                for (int i8 = this.n - 1; i8 >= 2; i8--) {
                    int i9 = i2 + i8;
                    float f3 = fArr2[i9];
                    int i10 = i9 - 1;
                    fArr2[i9] = fArr2[i10];
                    fArr2[i10] = f3;
                }
                if (z2) {
                    int i11 = this.n;
                    CommonUtils.scale(i11, 1.0f / ((float) i11), fArr, i, false);
                }
                int i12 = this.n;
                if (i12 % 2 == 0) {
                    int i13 = i12 / 2;
                    for (int i14 = 1; i14 < i13; i14++) {
                        int i15 = (i14 * 2) + i2 + 1;
                        fArr2[i15] = -fArr2[i15];
                    }
                    return;
                }
                int i16 = (i12 - 1) / 2;
                for (int i17 = 0; i17 < i16; i17++) {
                    int i18 = (i17 * 2) + i2 + 1;
                    fArr2[i18] = -fArr2[i18];
                }
            } else if (i3 == 3) {
                bluestein_real_inverse2(fArr, i);
                if (z2) {
                    int i19 = this.n;
                    CommonUtils.scale(i19, 1.0f / ((float) i19), fArr, i, false);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void realInverse2(FloatLargeArray floatLargeArray, long j, boolean z) {
        FloatLargeArray floatLargeArray2 = floatLargeArray;
        long j2 = j;
        boolean z2 = z;
        if (!this.useLargeArrays) {
            if (floatLargeArray.isLarge() || floatLargeArray.isConstant() || j2 >= 2147483647L) {
                throw new IllegalArgumentException("The data array is too big.");
            }
            realInverse2(floatLargeArray.getData(), (int) j2, z2);
        } else if (this.nl != 1) {
            int i = AnonymousClass29.$SwitchMap$org$jtransforms$fft$FloatFFT_1D$Plans[this.plan.ordinal()];
            if (i == 1) {
                long j3 = this.nl;
                if (j3 > 4) {
                    FloatLargeArray floatLargeArray3 = floatLargeArray;
                    long j4 = j;
                    CommonUtils.cftfsub(j3, floatLargeArray3, j4, this.ipl, this.nwl, this.wl);
                    CommonUtils.rftbsub(this.nl, floatLargeArray3, j4, this.ncl, this.wl, this.nwl);
                } else if (j3 == 4) {
                    CommonUtils.cftbsub(j3, floatLargeArray, j, this.ipl, this.nwl, this.wl);
                }
                long j5 = j2 + 1;
                float f = floatLargeArray.getFloat(j) - floatLargeArray2.getFloat(j5);
                floatLargeArray2.setFloat(j2, floatLargeArray.getFloat(j) + floatLargeArray2.getFloat(j5));
                floatLargeArray2.setFloat(j5, f);
                if (z2) {
                    long j6 = this.nl;
                    CommonUtils.scale(j6, 1.0f / ((float) j6), floatLargeArray, j, false);
                }
            } else if (i == 2) {
                rfftf(floatLargeArray, j);
                long j7 = this.nl;
                while (true) {
                    j7--;
                    if (j7 < 2) {
                        break;
                    }
                    long j8 = j2 + j7;
                    float f2 = floatLargeArray2.getFloat(j8);
                    long j9 = j8 - 1;
                    floatLargeArray2.setFloat(j8, floatLargeArray2.getFloat(j9));
                    floatLargeArray2.setFloat(j9, f2);
                }
                if (z2) {
                    long j10 = this.nl;
                    CommonUtils.scale(j10, 1.0f / ((float) j10), floatLargeArray, j, false);
                }
                long j11 = this.nl;
                if (j11 % 2 == 0) {
                    long j12 = j11 / 2;
                    for (long j13 = 1; j13 < j12; j13++) {
                        long j14 = (j13 * 2) + j2 + 1;
                        floatLargeArray2.setFloat(j14, -floatLargeArray2.getFloat(j14));
                    }
                    return;
                }
                long j15 = (j11 - 1) / 2;
                for (long j16 = 0; j16 < j15; j16++) {
                    long j17 = (j16 * 2) + j2 + 1;
                    floatLargeArray2.setFloat(j17, -floatLargeArray2.getFloat(j17));
                }
            } else if (i == 3) {
                bluestein_real_inverse2(floatLargeArray, j);
                if (z2) {
                    long j18 = this.nl;
                    CommonUtils.scale(j18, 1.0f / ((float) j18), floatLargeArray, j, false);
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
                    this.wtable[i2 + i11 + 1 + i7] = (float) i10;
                    if (i10 == 2 && i11 != 1) {
                        for (int i14 = 2; i14 <= i11; i14++) {
                            float[] fArr = this.wtable;
                            int i15 = i2 + (i11 - i14) + 2 + i7;
                            fArr[i15 + 1] = fArr[i15];
                        }
                        this.wtable[i2 + 2 + i7] = 2.0f;
                    }
                    if (i13 == 1) {
                        break loop0;
                    }
                    int i16 = i11;
                    i4 = i;
                    i8 = i13;
                }
                i9 = i12;
            }
            float[] fArr2 = this.wtable;
            float f = (float) i4;
            fArr2[i2 + i7] = f;
            fArr2[i2 + 1 + i7] = (float) i11;
            float f2 = TWO_PI / f;
            int i17 = 1;
            int i18 = 1;
            int i19 = 1;
            while (i17 <= i11) {
                int i20 = (int) this.wtable[i2 + i17 + i5 + i7];
                int i21 = i18 * i20;
                int i22 = i4 / i21;
                int i23 = i22 + i22 + i3;
                int i24 = i20 - 1;
                int i25 = 1;
                int i26 = 0;
                while (i25 <= i24) {
                    float[] fArr3 = this.wtable;
                    int i27 = i2 + i19;
                    fArr3[(i27 - 1) + i6] = 1.0f;
                    float f3 = 0.0f;
                    fArr3[i27 + i6] = 0.0f;
                    int i28 = i26 + i18;
                    float f4 = ((float) i28) * f2;
                    int i29 = i19;
                    int i30 = 4;
                    while (i30 <= i23) {
                        i29 += 2;
                        f3 += 1.0f;
                        int i31 = i2 + i29 + i6;
                        int i32 = i11;
                        double d = (double) (f3 * f4);
                        this.wtable[i31 - 1] = (float) FastMath.cos(d);
                        this.wtable[i31] = (float) FastMath.sin(d);
                        i30 += 2;
                        i28 = i28;
                        i24 = i24;
                        i7 = i7;
                        i11 = i32;
                        i18 = i18;
                        f4 = f4;
                    }
                    int i33 = i24;
                    int i34 = i7;
                    int i35 = i11;
                    int i36 = i18;
                    int i37 = i28;
                    if (i20 > 5) {
                        float[] fArr4 = this.wtable;
                        int i38 = i2 + i19 + i6;
                        int i39 = i2 + i29 + i6;
                        fArr4[i38 - 1] = fArr4[i39 - 1];
                        fArr4[i38] = fArr4[i39];
                    }
                    i25++;
                    int i40 = i;
                    i26 = i37;
                    i19 = i29;
                    i24 = i33;
                    i7 = i34;
                    i11 = i35;
                    i18 = i36;
                }
                int i41 = i7;
                int i42 = i11;
                i17++;
                i4 = i;
                i18 = i21;
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
        int i4 = 1;
        if (i3 != 1) {
            int i5 = i3 * 2;
            int i6 = i3 * 4;
            int i7 = 0;
            int i8 = 0;
            int i9 = 0;
            loop0:
            while (true) {
                int i10 = i7 + 1;
                i = 2;
                i8 = i10 <= 4 ? factors[i7] : i8 + 2;
                while (true) {
                    int i11 = i3 / i8;
                    if (i3 - (i8 * i11) != 0) {
                        break;
                    }
                    i2 = i9 + 1;
                    this.wtable[i9 + 2 + i6] = (float) i8;
                    if (i8 == 2 && i2 != 1) {
                        for (int i12 = 2; i12 <= i2; i12++) {
                            int i13 = (i2 - i12) + 2 + i6;
                            float[] fArr = this.wtable;
                            fArr[i13 + 1] = fArr[i13];
                        }
                        this.wtable[i6 + 2] = 2.0f;
                    }
                    if (i11 == 1) {
                        break loop0;
                    }
                    int i14 = i2;
                    i3 = i11;
                    i9 = i14;
                }
                i7 = i10;
            }
            float[] fArr2 = this.wtable;
            int i15 = this.n;
            fArr2[i6] = (float) i15;
            fArr2[i6 + 1] = (float) i2;
            float f = TWO_PI / ((float) i15);
            int i16 = 1;
            int i17 = 1;
            int i18 = 1;
            while (i16 <= i2) {
                i16++;
                int i19 = (int) this.wtable[i16 + i6];
                int i20 = i17 * i19;
                int i21 = this.n / i20;
                int i22 = i21 + i21 + i;
                int i23 = i19 - 1;
                int i24 = 0;
                while (i4 <= i23) {
                    float[] fArr3 = this.wtable;
                    fArr3[(i18 - 1) + i5] = 1.0f;
                    int i25 = i18 + i5;
                    float f2 = 0.0f;
                    fArr3[i25] = 0.0f;
                    int i26 = i24 + i17;
                    float f3 = ((float) i26) * f;
                    int i27 = 4;
                    while (i27 <= i22) {
                        i18 += 2;
                        f2 += 1.0f;
                        int i28 = i18 + i5;
                        int i29 = i26;
                        double d = (double) (f2 * f3);
                        this.wtable[i28 - 1] = (float) FastMath.cos(d);
                        this.wtable[i28] = (float) FastMath.sin(d);
                        i27 += 2;
                        i17 = i17;
                        i2 = i2;
                        i6 = i6;
                        f = f;
                        i26 = i29;
                        i16 = i16;
                    }
                    int i30 = i2;
                    int i31 = i6;
                    int i32 = i26;
                    float f4 = f;
                    int i33 = i16;
                    int i34 = i17;
                    if (i19 > 5) {
                        int i35 = i18 + i5;
                        float[] fArr4 = this.wtable;
                        fArr4[i25 - 1] = fArr4[i35 - 1];
                        fArr4[i25] = fArr4[i35];
                    }
                    i4++;
                    i17 = i34;
                    i2 = i30;
                    i6 = i31;
                    f = f4;
                    i24 = i32;
                    i16 = i33;
                    i = 2;
                }
                int i36 = i16;
                i17 = i20;
                i4 = 1;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void cfftil() {
        long j;
        long j2;
        long j3;
        long j4 = this.nl;
        long j5 = 1;
        if (j4 != 1) {
            long j6 = 2;
            long j7 = j4 * 2;
            long j8 = 4;
            long j9 = j4 * 4;
            long j10 = 0;
            long j11 = 0;
            long j12 = 0;
            loop0:
            while (true) {
                long j13 = j10 + j5;
                long j14 = j13 <= j8 ? (long) factors[(int) j10] : j11 + j6;
                while (true) {
                    long j15 = j4 / j14;
                    if (j4 - (j14 * j15) != 0) {
                        break;
                    }
                    j = j12 + j5;
                    this.wtablel.setFloat(j12 + j6 + j9, (float) j14);
                    j2 = 1;
                    if (j14 == j6 && j != 1) {
                        long j16 = j6;
                        while (j16 <= j) {
                            long j17 = (j - j16) + j6 + j9;
                            FloatLargeArray floatLargeArray = this.wtablel;
                            floatLargeArray.setFloat(j17 + 1, floatLargeArray.getFloat(j17));
                            j16++;
                            j6 = 2;
                        }
                        this.wtablel.setFloat(j9 + 2, 2.0f);
                    }
                    if (j15 == 1) {
                        break loop0;
                    }
                    long j18 = j;
                    j4 = j15;
                    j5 = 1;
                    j12 = j18;
                    j6 = 2;
                    j8 = 4;
                }
                j11 = j14;
                j10 = j13;
            }
            this.wtablel.setFloat(j9, (float) this.nl);
            this.wtablel.setFloat(j9 + 1, (float) j);
            float f = TWO_PI / ((float) this.nl);
            long j19 = 1;
            long j20 = 1;
            long j21 = 1;
            while (j19 <= j) {
                long j22 = j19 + j2;
                long j23 = j;
                long j24 = (long) this.wtablel.getFloat(j22 + j9);
                long j25 = j21 * j24;
                long j26 = j22;
                long j27 = this.nl / j25;
                long j28 = j27 + j27 + 2;
                long j29 = j24 - j2;
                long j30 = j2;
                long j31 = 0;
                while (j30 <= j29) {
                    long j32 = j9;
                    long j33 = j23;
                    this.wtablel.setFloat((j20 - j2) + j7, 1.0f);
                    long j34 = j20 + j7;
                    float f2 = 0.0f;
                    this.wtablel.setFloat(j34, 0.0f);
                    long j35 = j20;
                    long j36 = j31 + j21;
                    float f3 = ((float) j36) * f;
                    long j37 = 4;
                    while (j37 <= j28) {
                        j35 += 2;
                        f2 += 1.0f;
                        long j38 = j28;
                        long j39 = j35 + j7;
                        long j40 = j21;
                        double d = (double) (f2 * f3);
                        this.wtablel.setFloat(j39 - 1, (float) FastMath.cos(d));
                        this.wtablel.setFloat(j39, (float) FastMath.sin(d));
                        j37 += 2;
                        f = f;
                        j28 = j38;
                        f3 = f3;
                        j36 = j36;
                        j21 = j40;
                        j34 = j34;
                    }
                    float f4 = f;
                    long j41 = j28;
                    long j42 = j36;
                    long j43 = j34;
                    long j44 = j21;
                    if (j24 > 5) {
                        long j45 = j35 + j7;
                        FloatLargeArray floatLargeArray2 = this.wtablel;
                        j3 = 1;
                        floatLargeArray2.setFloat(j43 - 1, floatLargeArray2.getFloat(j45 - 1));
                        FloatLargeArray floatLargeArray3 = this.wtablel;
                        floatLargeArray3.setFloat(j43, floatLargeArray3.getFloat(j45));
                    } else {
                        j3 = 1;
                    }
                    j30 += j3;
                    j2 = j3;
                    j23 = j33;
                    j9 = j32;
                    j20 = j35;
                    f = f4;
                    j28 = j41;
                    j31 = j42;
                    j21 = j44;
                }
                long j46 = j20;
                j = j23;
                j21 = j25;
                j19 = j26;
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
                    this.wtable_r[i7 + 2 + i4] = (float) i6;
                    if (i6 == 2 && i != 1) {
                        for (int i10 = 2; i10 <= i; i10++) {
                            int i11 = (i - i10) + 2 + i4;
                            float[] fArr = this.wtable_r;
                            fArr[i11 + 1] = fArr[i11];
                        }
                        this.wtable_r[i4 + 2] = 2.0f;
                    }
                    if (i9 == 1) {
                        break loop0;
                    }
                    i7 = i;
                    i2 = i9;
                }
                i5 = i8;
            }
            float[] fArr2 = this.wtable_r;
            int i12 = this.n;
            fArr2[i4] = (float) i12;
            fArr2[i4 + 1] = (float) i;
            float f = TWO_PI / ((float) i12);
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
                        float f2 = ((float) i21) * f;
                        float f3 = 0.0f;
                        int i22 = i15;
                        int i23 = 3;
                        while (i23 <= i18) {
                            i22 += 2;
                            f3 += 1.0f;
                            int i24 = i22 + this.n;
                            int i25 = i13;
                            double d = (double) (f3 * f2);
                            this.wtable_r[i24 - 2] = (float) FastMath.cos(d);
                            this.wtable_r[i24 - 1] = (float) FastMath.sin(d);
                            i23 += 2;
                            i3 = 1;
                            f = f;
                            i14 = i14;
                            i13 = i25;
                            i19 = i19;
                        }
                        float f4 = f;
                        int i26 = i13;
                        int i27 = i14;
                        int i28 = i19;
                        int i29 = i3;
                        i15 += i18;
                        i20++;
                        i19 = i28;
                    }
                    int i30 = i13;
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
                    this.wtable_rl.setFloat(j11 + j6 + j7, (float) j10);
                    if (j10 == j6) {
                        j5 = 1;
                        if (j != 1) {
                            long j14 = j6;
                            while (j14 <= j) {
                                long j15 = (j - j14) + j6 + j7;
                                long j16 = j10;
                                FloatLargeArray floatLargeArray = this.wtable_rl;
                                floatLargeArray.setFloat(j15 + 1, floatLargeArray.getFloat(j15));
                                j14++;
                                j10 = j16;
                                j11 = j11;
                                j6 = 2;
                            }
                            j3 = j10;
                            j2 = j11;
                            this.wtable_rl.setFloat(j7 + 2, 2.0f);
                        } else {
                            j3 = j10;
                            j2 = j11;
                        }
                    } else {
                        j3 = j10;
                        j2 = j11;
                        j5 = 1;
                    }
                    if (j13 == j5) {
                        break loop0;
                    }
                    j11 = j;
                    j4 = j13;
                    j10 = j3;
                    j6 = 2;
                    j8 = 0;
                }
                j9 = j12;
            }
            this.wtable_rl.setFloat(j7, (float) this.nl);
            this.wtable_rl.setFloat(j7 + j5, (float) j);
            float f = TWO_PI / ((float) this.nl);
            if (j2 != 0) {
                long j17 = 0;
                long j18 = 1;
                long j19 = 1;
                while (j19 <= j2) {
                    j19++;
                    long j20 = (long) this.wtable_rl.getFloat(j19 + j7);
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
                        float f2 = ((float) j29) * f;
                        float f3 = 0.0f;
                        long j30 = 3;
                        long j31 = j25;
                        while (j30 <= j23) {
                            j31 += 2;
                            f3 += 1.0f;
                            long j32 = j29;
                            long j33 = j31 + this.nl;
                            long j34 = j19;
                            double d = (double) (f3 * f2);
                            this.wtable_rl.setFloat(j33 - 2, (float) FastMath.cos(d));
                            this.wtable_rl.setFloat(j33 - 1, (float) FastMath.sin(d));
                            j30 += 2;
                            f = f;
                            j29 = j32;
                            f2 = f2;
                            j18 = j18;
                            j19 = j34;
                            j26 = j26;
                        }
                        float f4 = f;
                        long j35 = j29;
                        long j36 = j19;
                        j25 += j23;
                        j26++;
                        j24 = j28;
                        j27 = j35;
                        j18 = j18;
                    }
                    long j37 = j19;
                    j17 = j25;
                    j18 = j21;
                    j7 = j22;
                }
            }
        }
    }

    private void bluesteini() {
        float f = PI / ((float) this.n);
        float[] fArr = this.bk1;
        fArr[0] = 1.0f;
        fArr[1] = 0.0f;
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
            double d = (double) (((float) i) * f);
            this.bk1[i4] = (float) FastMath.cos(d);
            this.bk1[i4 + 1] = (float) FastMath.sin(d);
            i2++;
        }
        float f2 = 1.0f / ((float) this.nBluestein);
        float[] fArr2 = this.bk2;
        float[] fArr3 = this.bk1;
        fArr2[0] = fArr3[0] * f2;
        fArr2[1] = fArr3[1] * f2;
        for (int i5 = 2; i5 < this.n * 2; i5 += 2) {
            float[] fArr4 = this.bk2;
            float[] fArr5 = this.bk1;
            fArr4[i5] = fArr5[i5] * f2;
            int i6 = i5 + 1;
            fArr4[i6] = fArr5[i6] * f2;
            int i7 = this.nBluestein;
            fArr4[(i7 * 2) - i5] = fArr4[i5];
            fArr4[((i7 * 2) - i5) + 1] = fArr4[i6];
        }
        CommonUtils.cftbsub(this.nBluestein * 2, this.bk2, 0, this.ip, this.nw, this.w);
    }

    private void bluesteinil() {
        float f = PI / ((float) this.nl);
        this.bk1l.setFloat(0, 1.0f);
        this.bk1l.setFloat(1, 0.0f);
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
            double d = (double) (((float) j) * f);
            this.bk1l.setFloat((long) i2, (float) FastMath.cos(d));
            this.bk1l.setFloat((long) (i2 + 1), (float) FastMath.sin(d));
            i++;
        }
        float f2 = 1.0f / ((float) this.nBluesteinl);
        this.bk2l.setFloat(0, this.bk1l.getFloat(0) * f2);
        this.bk2l.setFloat(1, this.bk1l.getFloat(1) * f2);
        int i3 = 2;
        while (true) {
            long j3 = (long) i3;
            if (j3 < this.nl * 2) {
                this.bk2l.setFloat(j3, this.bk1l.getFloat(j3) * f2);
                long j4 = (long) (i3 + 1);
                this.bk2l.setFloat(j4, this.bk1l.getFloat(j4) * f2);
                FloatLargeArray floatLargeArray = this.bk2l;
                floatLargeArray.setFloat((this.nBluesteinl * 2) - j3, floatLargeArray.getFloat(j3));
                FloatLargeArray floatLargeArray2 = this.bk2l;
                floatLargeArray2.setFloat(((this.nBluesteinl * 2) - j3) + 1, floatLargeArray2.getFloat(j4));
                i3 += 2;
            } else {
                CommonUtils.cftbsub(this.nBluesteinl * 2, this.bk2l, 0, this.ipl, this.nwl, this.wl);
                return;
            }
        }
    }

    private void bluestein_complex(float[] fArr, int i, int i2) {
        Class<FloatFFT_1D> cls = FloatFFT_1D.class;
        float[] fArr2 = new float[(this.nBluestein * 2)];
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = 0;
        if (numberOfThreads <= 1 || ((long) this.n) < CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
            if (i2 > 0) {
                for (int i4 = 0; i4 < this.n; i4++) {
                    int i5 = i4 * 2;
                    int i6 = i5 + 1;
                    int i7 = i + i5;
                    int i8 = i + i6;
                    float f = fArr[i7];
                    float[] fArr3 = this.bk1;
                    fArr2[i5] = (f * fArr3[i5]) - (fArr[i8] * fArr3[i6]);
                    fArr2[i6] = (fArr[i7] * fArr3[i6]) + (fArr[i8] * fArr3[i5]);
                }
            } else {
                for (int i9 = 0; i9 < this.n; i9++) {
                    int i10 = i9 * 2;
                    int i11 = i10 + 1;
                    int i12 = i + i10;
                    int i13 = i + i11;
                    float f2 = fArr[i12];
                    float[] fArr4 = this.bk1;
                    fArr2[i10] = (f2 * fArr4[i10]) + (fArr[i13] * fArr4[i11]);
                    fArr2[i11] = ((-fArr[i12]) * fArr4[i11]) + (fArr[i13] * fArr4[i10]);
                }
            }
            CommonUtils.cftbsub(this.nBluestein * 2, fArr2, 0, this.ip, this.nw, this.w);
            if (i2 > 0) {
                for (int i14 = 0; i14 < this.nBluestein; i14++) {
                    int i15 = i14 * 2;
                    int i16 = i15 + 1;
                    float f3 = fArr2[i15];
                    float[] fArr5 = this.bk2;
                    float f4 = fArr5[i16];
                    float f5 = fArr2[i16];
                    float f6 = fArr5[i15];
                    fArr2[i15] = (f3 * f6) + (f5 * f4);
                    fArr2[i16] = ((-f3) * f4) + (f5 * f6);
                }
            } else {
                for (int i17 = 0; i17 < this.nBluestein; i17++) {
                    int i18 = i17 * 2;
                    int i19 = i18 + 1;
                    float f7 = fArr2[i18];
                    float[] fArr6 = this.bk2;
                    float f8 = fArr6[i19];
                    float f9 = fArr2[i19];
                    float f10 = fArr6[i18];
                    fArr2[i18] = (f7 * f10) - (f9 * f8);
                    fArr2[i19] = (f7 * f8) + (f9 * f10);
                }
            }
            CommonUtils.cftfsub(this.nBluestein * 2, fArr2, 0, this.ip, this.nw, this.w);
            if (i2 > 0) {
                while (i3 < this.n) {
                    int i20 = i3 * 2;
                    int i21 = i20 + 1;
                    float[] fArr7 = this.bk1;
                    fArr[i + i20] = (fArr7[i20] * fArr2[i20]) - (fArr7[i21] * fArr2[i21]);
                    fArr[i + i21] = (fArr7[i21] * fArr2[i20]) + (fArr7[i20] * fArr2[i21]);
                    i3++;
                }
                return;
            }
            while (i3 < this.n) {
                int i22 = i3 * 2;
                int i23 = i22 + 1;
                float[] fArr8 = this.bk1;
                fArr[i + i22] = (fArr8[i22] * fArr2[i22]) + (fArr8[i23] * fArr2[i23]);
                fArr[i + i23] = ((-fArr8[i23]) * fArr2[i22]) + (fArr8[i22] * fArr2[i23]);
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
            final float[] fArr9 = fArr2;
            int i31 = i26;
            final float[] fArr10 = fArr;
            futureArr[i31] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    if (i29 > 0) {
                        for (int i = i27; i < i28; i++) {
                            int i2 = i * 2;
                            int i3 = i2 + 1;
                            int i4 = i30;
                            int i5 = i4 + i2;
                            int i6 = i4 + i3;
                            fArr9[i2] = (fArr10[i5] * FloatFFT_1D.this.bk1[i2]) - (fArr10[i6] * FloatFFT_1D.this.bk1[i3]);
                            fArr9[i3] = (fArr10[i5] * FloatFFT_1D.this.bk1[i3]) + (fArr10[i6] * FloatFFT_1D.this.bk1[i2]);
                        }
                        return;
                    }
                    for (int i7 = i27; i7 < i28; i7++) {
                        int i8 = i7 * 2;
                        int i9 = i8 + 1;
                        int i10 = i30;
                        int i11 = i10 + i8;
                        int i12 = i10 + i9;
                        fArr9[i8] = (fArr10[i11] * FloatFFT_1D.this.bk1[i8]) + (fArr10[i12] * FloatFFT_1D.this.bk1[i9]);
                        fArr9[i9] = ((-fArr10[i11]) * FloatFFT_1D.this.bk1[i9]) + (fArr10[i12] * FloatFFT_1D.this.bk1[i8]);
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
        CommonUtils.cftbsub(this.nBluestein * 2, fArr2, 0, this.ip, this.nw, this.w);
        int i32 = this.nBluestein / i24;
        int i33 = 0;
        while (i33 < i24) {
            final int i34 = i33 * i32;
            final int i35 = i33 == i24 + -1 ? this.nBluestein : i34 + i32;
            final int i36 = i2;
            final float[] fArr11 = fArr2;
            futureArr[i33] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    if (i36 > 0) {
                        for (int i = i34; i < i35; i++) {
                            int i2 = i * 2;
                            int i3 = i2 + 1;
                            float f = ((-fArr11[i2]) * FloatFFT_1D.this.bk2[i3]) + (fArr11[i3] * FloatFFT_1D.this.bk2[i2]);
                            float[] fArr = fArr11;
                            fArr[i2] = (fArr[i2] * FloatFFT_1D.this.bk2[i2]) + (fArr11[i3] * FloatFFT_1D.this.bk2[i3]);
                            fArr11[i3] = f;
                        }
                        return;
                    }
                    for (int i4 = i34; i4 < i35; i4++) {
                        int i5 = i4 * 2;
                        int i6 = i5 + 1;
                        float f2 = (fArr11[i5] * FloatFFT_1D.this.bk2[i6]) + (fArr11[i6] * FloatFFT_1D.this.bk2[i5]);
                        float[] fArr2 = fArr11;
                        fArr2[i5] = (fArr2[i5] * FloatFFT_1D.this.bk2[i5]) - (fArr11[i6] * FloatFFT_1D.this.bk2[i6]);
                        fArr11[i6] = f2;
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
        CommonUtils.cftfsub(this.nBluestein * 2, fArr2, 0, this.ip, this.nw, this.w);
        int i37 = this.n / i24;
        while (i3 < i24) {
            final int i38 = i3 * i37;
            final int i39 = i3 == i24 + -1 ? this.n : i38 + i37;
            final int i40 = i2;
            final int i41 = i;
            final float[] fArr12 = fArr;
            int i42 = i24;
            String str2 = str;
            final float[] fArr13 = fArr2;
            futureArr[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    if (i40 > 0) {
                        for (int i = i38; i < i39; i++) {
                            int i2 = i * 2;
                            int i3 = i2 + 1;
                            int i4 = i41;
                            fArr12[i4 + i2] = (FloatFFT_1D.this.bk1[i2] * fArr13[i2]) - (FloatFFT_1D.this.bk1[i3] * fArr13[i3]);
                            fArr12[i4 + i3] = (FloatFFT_1D.this.bk1[i3] * fArr13[i2]) + (FloatFFT_1D.this.bk1[i2] * fArr13[i3]);
                        }
                        return;
                    }
                    for (int i5 = i38; i5 < i39; i5++) {
                        int i6 = i5 * 2;
                        int i7 = i6 + 1;
                        int i8 = i41;
                        fArr12[i8 + i6] = (FloatFFT_1D.this.bk1[i6] * fArr13[i6]) + (FloatFFT_1D.this.bk1[i7] * fArr13[i7]);
                        fArr12[i8 + i7] = ((-FloatFFT_1D.this.bk1[i7]) * fArr13[i6]) + (FloatFFT_1D.this.bk1[i6] * fArr13[i7]);
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

    private void bluestein_complex(FloatLargeArray floatLargeArray, long j, int i) {
        FloatLargeArray floatLargeArray2;
        FloatLargeArray floatLargeArray3 = floatLargeArray;
        Class<FloatFFT_1D> cls = FloatFFT_1D.class;
        FloatLargeArray floatLargeArray4 = new FloatLargeArray(this.nBluesteinl * 2);
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
                    FloatLargeArray floatLargeArray5 = floatLargeArray;
                    floatLargeArray4.setFloat(j4, (floatLargeArray5.getFloat(j6) * this.bk1l.getFloat(j4)) - (floatLargeArray5.getFloat(j7) * this.bk1l.getFloat(j5)));
                    floatLargeArray4.setFloat(j5, (floatLargeArray5.getFloat(j6) * this.bk1l.getFloat(j5)) + (floatLargeArray5.getFloat(j7) * this.bk1l.getFloat(j4)));
                    j3++;
                    j2 = 1;
                }
                floatLargeArray2 = floatLargeArray;
            } else {
                floatLargeArray2 = floatLargeArray;
                long j8 = 1;
                for (long j9 = 0; j9 < this.nl; j9++) {
                    long j10 = j9 * 2;
                    long j11 = j10 + j8;
                    long j12 = j + j10;
                    long j13 = j + j11;
                    floatLargeArray4.setFloat(j10, (floatLargeArray2.getFloat(j12) * this.bk1l.getFloat(j10)) + (floatLargeArray2.getFloat(j13) * this.bk1l.getFloat(j11)));
                    floatLargeArray4.setFloat(j11, ((-floatLargeArray2.getFloat(j12)) * this.bk1l.getFloat(j11)) + (floatLargeArray2.getFloat(j13) * this.bk1l.getFloat(j10)));
                    j8 = 1;
                }
            }
            CommonUtils.cftbsub(this.nBluesteinl * 2, floatLargeArray4, 0, this.ipl, this.nwl, this.wl);
            if (i > 0) {
                for (long j14 = 0; j14 < this.nBluesteinl; j14++) {
                    long j15 = j14 * 2;
                    long j16 = j15 + 1;
                    float f = ((-floatLargeArray4.getFloat(j15)) * this.bk2l.getFloat(j16)) + (floatLargeArray4.getFloat(j16) * this.bk2l.getFloat(j15));
                    floatLargeArray4.setFloat(j15, (floatLargeArray4.getFloat(j15) * this.bk2l.getFloat(j15)) + (floatLargeArray4.getFloat(j16) * this.bk2l.getFloat(j16)));
                    floatLargeArray4.setFloat(j16, f);
                }
            } else {
                long j17 = 1;
                for (long j18 = 0; j18 < this.nBluesteinl; j18++) {
                    long j19 = j18 * 2;
                    long j20 = j19 + j17;
                    float f2 = (floatLargeArray4.getFloat(j19) * this.bk2l.getFloat(j20)) + (floatLargeArray4.getFloat(j20) * this.bk2l.getFloat(j19));
                    floatLargeArray4.setFloat(j19, (floatLargeArray4.getFloat(j19) * this.bk2l.getFloat(j19)) - (floatLargeArray4.getFloat(j20) * this.bk2l.getFloat(j20)));
                    floatLargeArray4.setFloat(j20, f2);
                    j17 = 1;
                }
            }
            CommonUtils.cftfsub(this.nBluesteinl * 2, floatLargeArray4, 0, this.ipl, this.nwl, this.wl);
            if (i > 0) {
                for (long j21 = 0; j21 < this.nl; j21++) {
                    long j22 = j21 * 2;
                    long j23 = j22 + 1;
                    floatLargeArray2.setFloat(j + j22, (this.bk1l.getFloat(j22) * floatLargeArray4.getFloat(j22)) - (this.bk1l.getFloat(j23) * floatLargeArray4.getFloat(j23)));
                    floatLargeArray2.setFloat(j + j23, (this.bk1l.getFloat(j23) * floatLargeArray4.getFloat(j22)) + (this.bk1l.getFloat(j22) * floatLargeArray4.getFloat(j23)));
                }
                return;
            }
            long j24 = 1;
            for (long j25 = 0; j25 < this.nl; j25++) {
                long j26 = j25 * 2;
                long j27 = j26 + j24;
                floatLargeArray2.setFloat(j + j26, (this.bk1l.getFloat(j26) * floatLargeArray4.getFloat(j26)) + (this.bk1l.getFloat(j27) * floatLargeArray4.getFloat(j27)));
                floatLargeArray2.setFloat(j + j27, ((-this.bk1l.getFloat(j27)) * floatLargeArray4.getFloat(j26)) + (this.bk1l.getFloat(j26) * floatLargeArray4.getFloat(j27)));
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
            final FloatLargeArray floatLargeArray6 = floatLargeArray4;
            int i6 = i3;
            final FloatLargeArray floatLargeArray7 = floatLargeArray;
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
                            floatLargeArray6.setFloat(j4, (floatLargeArray7.getFloat(j7) * FloatFFT_1D.this.bk1l.getFloat(j4)) - (floatLargeArray7.getFloat(j8) * FloatFFT_1D.this.bk1l.getFloat(j5)));
                            floatLargeArray6.setFloat(j5, (floatLargeArray7.getFloat(j7) * FloatFFT_1D.this.bk1l.getFloat(j5)) + (floatLargeArray7.getFloat(j8) * FloatFFT_1D.this.bk1l.getFloat(j4)));
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
                        floatLargeArray6.setFloat(j11, (floatLargeArray7.getFloat(j14) * FloatFFT_1D.this.bk1l.getFloat(j11)) + (floatLargeArray7.getFloat(j15) * FloatFFT_1D.this.bk1l.getFloat(j12)));
                        floatLargeArray6.setFloat(j12, ((-floatLargeArray7.getFloat(j14)) * FloatFFT_1D.this.bk1l.getFloat(j12)) + (floatLargeArray7.getFloat(j15) * FloatFFT_1D.this.bk1l.getFloat(j11)));
                        j9 = 1;
                    }
                }
            });
            i4++;
            i3 = i6;
            j28 = j32;
            futureArr = futureArr2;
            FloatLargeArray floatLargeArray8 = floatLargeArray;
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
        CommonUtils.cftbsub(this.nBluesteinl * 2, floatLargeArray4, 0, this.ipl, this.nwl, this.wl);
        long j35 = this.nBluesteinl / j34;
        int i8 = 0;
        while (i8 < i7) {
            final long j36 = ((long) i8) * j35;
            final long j37 = i8 == i7 + -1 ? this.nBluesteinl : j36 + j35;
            final int i9 = i;
            final FloatLargeArray floatLargeArray9 = floatLargeArray4;
            futureArr3[i8] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    if (i9 > 0) {
                        for (long j = j36; j < j37; j++) {
                            long j2 = j * 2;
                            long j3 = j2 + 1;
                            float f = ((-floatLargeArray9.getFloat(j2)) * FloatFFT_1D.this.bk2l.getFloat(j3)) + (floatLargeArray9.getFloat(j3) * FloatFFT_1D.this.bk2l.getFloat(j2));
                            FloatLargeArray floatLargeArray = floatLargeArray9;
                            floatLargeArray.setFloat(j2, (floatLargeArray.getFloat(j2) * FloatFFT_1D.this.bk2l.getFloat(j2)) + (floatLargeArray9.getFloat(j3) * FloatFFT_1D.this.bk2l.getFloat(j3)));
                            floatLargeArray9.setFloat(j3, f);
                        }
                        return;
                    }
                    for (long j4 = j36; j4 < j37; j4++) {
                        long j5 = j4 * 2;
                        long j6 = j5 + 1;
                        float f2 = (floatLargeArray9.getFloat(j5) * FloatFFT_1D.this.bk2l.getFloat(j6)) + (floatLargeArray9.getFloat(j6) * FloatFFT_1D.this.bk2l.getFloat(j5));
                        FloatLargeArray floatLargeArray2 = floatLargeArray9;
                        floatLargeArray2.setFloat(j5, (floatLargeArray2.getFloat(j5) * FloatFFT_1D.this.bk2l.getFloat(j5)) - (floatLargeArray9.getFloat(j6) * FloatFFT_1D.this.bk2l.getFloat(j6)));
                        floatLargeArray9.setFloat(j6, f2);
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
        CommonUtils.cftfsub(this.nBluesteinl * 2, floatLargeArray4, 0, this.ipl, this.nwl, this.wl);
        long j38 = this.nl / j34;
        int i10 = 0;
        while (i10 < i7) {
            final long j39 = ((long) i10) * j38;
            final long j40 = i10 == i7 + -1 ? this.nl : j39 + j38;
            final int i11 = i;
            final long j41 = j;
            final FloatLargeArray floatLargeArray10 = floatLargeArray;
            int i12 = i7;
            String str2 = str;
            final FloatLargeArray floatLargeArray11 = floatLargeArray4;
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
                            floatLargeArray10.setFloat(j6 + j4, (FloatFFT_1D.this.bk1l.getFloat(j4) * floatLargeArray11.getFloat(j4)) - (FloatFFT_1D.this.bk1l.getFloat(j5) * floatLargeArray11.getFloat(j5)));
                            floatLargeArray10.setFloat(j6 + j5, (FloatFFT_1D.this.bk1l.getFloat(j5) * floatLargeArray11.getFloat(j4)) + (FloatFFT_1D.this.bk1l.getFloat(j4) * floatLargeArray11.getFloat(j5)));
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
                        floatLargeArray10.setFloat(j11 + j9, (FloatFFT_1D.this.bk1l.getFloat(j9) * floatLargeArray11.getFloat(j9)) + (FloatFFT_1D.this.bk1l.getFloat(j10) * floatLargeArray11.getFloat(j10)));
                        floatLargeArray10.setFloat(j11 + j10, ((-FloatFFT_1D.this.bk1l.getFloat(j10)) * floatLargeArray11.getFloat(j9)) + (FloatFFT_1D.this.bk1l.getFloat(j9) * floatLargeArray11.getFloat(j10)));
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

    private void bluestein_real_full(float[] fArr, int i, int i2) {
        Class<FloatFFT_1D> cls = FloatFFT_1D.class;
        float[] fArr2 = new float[(this.nBluestein * 2)];
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = 0;
        if (numberOfThreads <= 1 || ((long) this.n) < CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
            if (i2 > 0) {
                for (int i4 = 0; i4 < this.n; i4++) {
                    int i5 = i4 * 2;
                    int i6 = i5 + 1;
                    int i7 = i + i4;
                    float f = fArr[i7];
                    float[] fArr3 = this.bk1;
                    fArr2[i5] = f * fArr3[i5];
                    fArr2[i6] = fArr[i7] * fArr3[i6];
                }
            } else {
                for (int i8 = 0; i8 < this.n; i8++) {
                    int i9 = i8 * 2;
                    int i10 = i9 + 1;
                    int i11 = i + i8;
                    float f2 = fArr[i11];
                    float[] fArr4 = this.bk1;
                    fArr2[i9] = f2 * fArr4[i9];
                    fArr2[i10] = (-fArr[i11]) * fArr4[i10];
                }
            }
            CommonUtils.cftbsub(this.nBluestein * 2, fArr2, 0, this.ip, this.nw, this.w);
            if (i2 > 0) {
                for (int i12 = 0; i12 < this.nBluestein; i12++) {
                    int i13 = i12 * 2;
                    int i14 = i13 + 1;
                    float f3 = fArr2[i13];
                    float[] fArr5 = this.bk2;
                    float f4 = fArr5[i14];
                    float f5 = fArr2[i14];
                    float f6 = fArr5[i13];
                    fArr2[i13] = (f3 * f6) + (f5 * f4);
                    fArr2[i14] = ((-f3) * f4) + (f5 * f6);
                }
            } else {
                for (int i15 = 0; i15 < this.nBluestein; i15++) {
                    int i16 = i15 * 2;
                    int i17 = i16 + 1;
                    float f7 = fArr2[i16];
                    float[] fArr6 = this.bk2;
                    float f8 = fArr6[i17];
                    float f9 = fArr2[i17];
                    float f10 = fArr6[i16];
                    fArr2[i16] = (f7 * f10) - (f9 * f8);
                    fArr2[i17] = (f7 * f8) + (f9 * f10);
                }
            }
            CommonUtils.cftfsub(this.nBluestein * 2, fArr2, 0, this.ip, this.nw, this.w);
            if (i2 > 0) {
                while (i3 < this.n) {
                    int i18 = i3 * 2;
                    int i19 = i18 + 1;
                    float[] fArr7 = this.bk1;
                    fArr[i + i18] = (fArr7[i18] * fArr2[i18]) - (fArr7[i19] * fArr2[i19]);
                    fArr[i + i19] = (fArr7[i19] * fArr2[i18]) + (fArr7[i18] * fArr2[i19]);
                    i3++;
                }
                return;
            }
            while (i3 < this.n) {
                int i20 = i3 * 2;
                int i21 = i20 + 1;
                float[] fArr8 = this.bk1;
                fArr[i + i20] = (fArr8[i20] * fArr2[i20]) + (fArr8[i21] * fArr2[i21]);
                fArr[i + i21] = ((-fArr8[i21]) * fArr2[i20]) + (fArr8[i20] * fArr2[i21]);
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
            final float[] fArr9 = fArr2;
            int i29 = i24;
            final float[] fArr10 = fArr;
            futureArr[i29] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    if (i27 > 0) {
                        for (int i = i25; i < i26; i++) {
                            int i2 = i * 2;
                            int i3 = i2 + 1;
                            int i4 = i28 + i;
                            fArr9[i2] = fArr10[i4] * FloatFFT_1D.this.bk1[i2];
                            fArr9[i3] = fArr10[i4] * FloatFFT_1D.this.bk1[i3];
                        }
                        return;
                    }
                    for (int i5 = i25; i5 < i26; i5++) {
                        int i6 = i5 * 2;
                        int i7 = i6 + 1;
                        int i8 = i28 + i5;
                        fArr9[i6] = fArr10[i8] * FloatFFT_1D.this.bk1[i6];
                        fArr9[i7] = (-fArr10[i8]) * FloatFFT_1D.this.bk1[i7];
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
        CommonUtils.cftbsub(this.nBluestein * 2, fArr2, 0, this.ip, this.nw, this.w);
        int i30 = this.nBluestein / i22;
        int i31 = 0;
        while (i31 < i22) {
            final int i32 = i31 * i30;
            final int i33 = i31 == i22 + -1 ? this.nBluestein : i32 + i30;
            final int i34 = i2;
            final float[] fArr11 = fArr2;
            futureArr[i31] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    if (i34 > 0) {
                        for (int i = i32; i < i33; i++) {
                            int i2 = i * 2;
                            int i3 = i2 + 1;
                            float f = ((-fArr11[i2]) * FloatFFT_1D.this.bk2[i3]) + (fArr11[i3] * FloatFFT_1D.this.bk2[i2]);
                            float[] fArr = fArr11;
                            fArr[i2] = (fArr[i2] * FloatFFT_1D.this.bk2[i2]) + (fArr11[i3] * FloatFFT_1D.this.bk2[i3]);
                            fArr11[i3] = f;
                        }
                        return;
                    }
                    for (int i4 = i32; i4 < i33; i4++) {
                        int i5 = i4 * 2;
                        int i6 = i5 + 1;
                        float f2 = (fArr11[i5] * FloatFFT_1D.this.bk2[i6]) + (fArr11[i6] * FloatFFT_1D.this.bk2[i5]);
                        float[] fArr2 = fArr11;
                        fArr2[i5] = (fArr2[i5] * FloatFFT_1D.this.bk2[i5]) - (fArr11[i6] * FloatFFT_1D.this.bk2[i6]);
                        fArr11[i6] = f2;
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
        CommonUtils.cftfsub(this.nBluestein * 2, fArr2, 0, this.ip, this.nw, this.w);
        int i35 = this.n / i22;
        while (i3 < i22) {
            final int i36 = i3 * i35;
            final int i37 = i3 == i22 + -1 ? this.n : i36 + i35;
            final int i38 = i2;
            final float[] fArr12 = fArr;
            final int i39 = i;
            int i40 = i22;
            String str2 = str;
            final float[] fArr13 = fArr2;
            futureArr[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    if (i38 > 0) {
                        for (int i = i36; i < i37; i++) {
                            int i2 = i * 2;
                            int i3 = i2 + 1;
                            fArr12[i39 + i2] = (FloatFFT_1D.this.bk1[i2] * fArr13[i2]) - (FloatFFT_1D.this.bk1[i3] * fArr13[i3]);
                            fArr12[i39 + i3] = (FloatFFT_1D.this.bk1[i3] * fArr13[i2]) + (FloatFFT_1D.this.bk1[i2] * fArr13[i3]);
                        }
                        return;
                    }
                    for (int i4 = i36; i4 < i37; i4++) {
                        int i5 = i4 * 2;
                        int i6 = i5 + 1;
                        fArr12[i39 + i5] = (FloatFFT_1D.this.bk1[i5] * fArr13[i5]) + (FloatFFT_1D.this.bk1[i6] * fArr13[i6]);
                        fArr12[i39 + i6] = ((-FloatFFT_1D.this.bk1[i6]) * fArr13[i5]) + (FloatFFT_1D.this.bk1[i5] * fArr13[i6]);
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

    private void bluestein_real_full(FloatLargeArray floatLargeArray, long j, long j2) {
        FloatLargeArray floatLargeArray2;
        FloatLargeArray floatLargeArray3;
        FloatLargeArray floatLargeArray4 = floatLargeArray;
        Class<FloatFFT_1D> cls = FloatFFT_1D.class;
        FloatLargeArray floatLargeArray5 = new FloatLargeArray(this.nBluesteinl * 2);
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (numberOfThreads <= 1 || this.nl <= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
            FloatLargeArray floatLargeArray6 = floatLargeArray5;
            int i = (j2 > 0 ? 1 : (j2 == 0 ? 0 : -1));
            if (i > 0) {
                for (long j3 = 0; j3 < this.nl; j3++) {
                    long j4 = j3 * 2;
                    long j5 = j4 + 1;
                    long j6 = j + j3;
                    FloatLargeArray floatLargeArray7 = floatLargeArray;
                    FloatLargeArray floatLargeArray8 = floatLargeArray6;
                    floatLargeArray8.setFloat(j4, floatLargeArray7.getFloat(j6) * this.bk1l.getFloat(j4));
                    floatLargeArray8.setFloat(j5, floatLargeArray7.getFloat(j6) * this.bk1l.getFloat(j5));
                }
                floatLargeArray2 = floatLargeArray;
                floatLargeArray3 = floatLargeArray6;
            } else {
                floatLargeArray2 = floatLargeArray;
                floatLargeArray3 = floatLargeArray6;
                for (long j7 = 0; j7 < this.nl; j7++) {
                    long j8 = j7 * 2;
                    long j9 = j8 + 1;
                    long j10 = j + j7;
                    floatLargeArray3.setFloat(j8, floatLargeArray2.getFloat(j10) * this.bk1l.getFloat(j8));
                    floatLargeArray3.setFloat(j9, (-floatLargeArray2.getFloat(j10)) * this.bk1l.getFloat(j9));
                }
            }
            CommonUtils.cftbsub(this.nBluesteinl * 2, floatLargeArray3, 0, this.ipl, this.nwl, this.wl);
            if (i > 0) {
                for (long j11 = 0; j11 < this.nBluesteinl; j11++) {
                    long j12 = j11 * 2;
                    long j13 = j12 + 1;
                    float f = ((-floatLargeArray3.getFloat(j12)) * this.bk2l.getFloat(j13)) + (floatLargeArray3.getFloat(j13) * this.bk2l.getFloat(j12));
                    floatLargeArray3.setFloat(j12, (floatLargeArray3.getFloat(j12) * this.bk2l.getFloat(j12)) + (floatLargeArray3.getFloat(j13) * this.bk2l.getFloat(j13)));
                    floatLargeArray3.setFloat(j13, f);
                }
            } else {
                for (long j14 = 0; j14 < this.nBluesteinl; j14++) {
                    long j15 = j14 * 2;
                    long j16 = j15 + 1;
                    float f2 = (floatLargeArray3.getFloat(j15) * this.bk2l.getFloat(j16)) + (floatLargeArray3.getFloat(j16) * this.bk2l.getFloat(j15));
                    floatLargeArray3.setFloat(j15, (floatLargeArray3.getFloat(j15) * this.bk2l.getFloat(j15)) - (floatLargeArray3.getFloat(j16) * this.bk2l.getFloat(j16)));
                    floatLargeArray3.setFloat(j16, f2);
                }
            }
            CommonUtils.cftfsub(this.nBluesteinl * 2, floatLargeArray3, 0, this.ipl, this.nwl, this.wl);
            if (i > 0) {
                for (long j17 = 0; j17 < this.nl; j17++) {
                    long j18 = j17 * 2;
                    long j19 = j18 + 1;
                    floatLargeArray2.setFloat(j + j18, (this.bk1l.getFloat(j18) * floatLargeArray3.getFloat(j18)) - (this.bk1l.getFloat(j19) * floatLargeArray3.getFloat(j19)));
                    floatLargeArray2.setFloat(j + j19, (this.bk1l.getFloat(j19) * floatLargeArray3.getFloat(j18)) + (this.bk1l.getFloat(j18) * floatLargeArray3.getFloat(j19)));
                }
                return;
            }
            for (long j20 = 0; j20 < this.nl; j20++) {
                long j21 = j20 * 2;
                long j22 = j21 + 1;
                floatLargeArray2.setFloat(j + j21, (this.bk1l.getFloat(j21) * floatLargeArray3.getFloat(j21)) + (this.bk1l.getFloat(j22) * floatLargeArray3.getFloat(j22)));
                floatLargeArray2.setFloat(j + j22, ((-this.bk1l.getFloat(j22)) * floatLargeArray3.getFloat(j21)) + (this.bk1l.getFloat(j21) * floatLargeArray3.getFloat(j22)));
            }
            return;
        }
        int i2 = 4;
        if (numberOfThreads < 4 || this.nl <= CommonUtils.getThreadsBeginN_1D_FFT_4Threads()) {
            i2 = 2;
        }
        int i3 = i2;
        Future[] futureArr = new Future[i3];
        long j23 = (long) i3;
        long j24 = this.nl / j23;
        int i4 = 0;
        while (i4 < i3) {
            final long j25 = ((long) i4) * j24;
            final long j26 = j2;
            long j27 = j23;
            final long j28 = i4 == i3 + -1 ? this.nl : j25 + j24;
            Future[] futureArr2 = futureArr;
            final long j29 = j;
            int i5 = i3;
            final FloatLargeArray floatLargeArray9 = floatLargeArray5;
            FloatLargeArray floatLargeArray10 = floatLargeArray5;
            final FloatLargeArray floatLargeArray11 = floatLargeArray;
            futureArr2[i4] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    if (j26 > 0) {
                        for (long j = j25; j < j28; j++) {
                            long j2 = j * 2;
                            long j3 = j2 + 1;
                            long j4 = j29 + j;
                            floatLargeArray9.setFloat(j2, floatLargeArray11.getFloat(j4) * FloatFFT_1D.this.bk1l.getFloat(j2));
                            floatLargeArray9.setFloat(j3, floatLargeArray11.getFloat(j4) * FloatFFT_1D.this.bk1l.getFloat(j3));
                        }
                        return;
                    }
                    for (long j5 = j25; j5 < j28; j5++) {
                        long j6 = j5 * 2;
                        long j7 = j6 + 1;
                        long j8 = j29 + j5;
                        floatLargeArray9.setFloat(j6, floatLargeArray11.getFloat(j8) * FloatFFT_1D.this.bk1l.getFloat(j6));
                        floatLargeArray9.setFloat(j7, (-floatLargeArray11.getFloat(j8)) * FloatFFT_1D.this.bk1l.getFloat(j7));
                    }
                }
            });
            i4++;
            i3 = i5;
            futureArr = futureArr2;
            floatLargeArray5 = floatLargeArray10;
            j23 = j27;
            FloatLargeArray floatLargeArray12 = floatLargeArray;
        }
        long j30 = j23;
        Future[] futureArr3 = futureArr;
        int i6 = i3;
        FloatLargeArray floatLargeArray13 = floatLargeArray5;
        String str = null;
        try {
            ConcurrencyUtils.waitForCompletion(futureArr3);
        } catch (InterruptedException e) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
        } catch (ExecutionException e2) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
        }
        CommonUtils.cftbsub(this.nBluesteinl * 2, floatLargeArray13, 0, this.ipl, this.nwl, this.wl);
        long j31 = this.nBluesteinl / j30;
        int i7 = 0;
        while (i7 < i6) {
            final long j32 = ((long) i7) * j31;
            final long j33 = i7 == i6 + -1 ? this.nBluesteinl : j32 + j31;
            final long j34 = j2;
            final FloatLargeArray floatLargeArray14 = floatLargeArray13;
            futureArr3[i7] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    if (j34 > 0) {
                        for (long j = j32; j < j33; j++) {
                            long j2 = j * 2;
                            long j3 = j2 + 1;
                            float f = ((-floatLargeArray14.getFloat(j2)) * FloatFFT_1D.this.bk2l.getFloat(j3)) + (floatLargeArray14.getFloat(j3) * FloatFFT_1D.this.bk2l.getFloat(j2));
                            FloatLargeArray floatLargeArray = floatLargeArray14;
                            floatLargeArray.setFloat(j2, (floatLargeArray.getFloat(j2) * FloatFFT_1D.this.bk2l.getFloat(j2)) + (floatLargeArray14.getFloat(j3) * FloatFFT_1D.this.bk2l.getFloat(j3)));
                            floatLargeArray14.setFloat(j3, f);
                        }
                        return;
                    }
                    for (long j4 = j32; j4 < j33; j4++) {
                        long j5 = j4 * 2;
                        long j6 = j5 + 1;
                        float f2 = (floatLargeArray14.getFloat(j5) * FloatFFT_1D.this.bk2l.getFloat(j6)) + (floatLargeArray14.getFloat(j6) * FloatFFT_1D.this.bk2l.getFloat(j5));
                        FloatLargeArray floatLargeArray2 = floatLargeArray14;
                        floatLargeArray2.setFloat(j5, (floatLargeArray2.getFloat(j5) * FloatFFT_1D.this.bk2l.getFloat(j5)) - (floatLargeArray14.getFloat(j6) * FloatFFT_1D.this.bk2l.getFloat(j6)));
                        floatLargeArray14.setFloat(j6, f2);
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
        CommonUtils.cftfsub(this.nBluesteinl * 2, floatLargeArray13, 0, this.ipl, this.nwl, this.wl);
        long j35 = this.nl / j30;
        int i8 = 0;
        while (i8 < i6) {
            final long j36 = ((long) i8) * j35;
            final long j37 = i8 == i6 + -1 ? this.nl : j36 + j35;
            final long j38 = j2;
            final FloatLargeArray floatLargeArray15 = floatLargeArray;
            final long j39 = j;
            int i9 = i6;
            String str2 = str;
            final FloatLargeArray floatLargeArray16 = floatLargeArray13;
            futureArr3[i8] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    long j = 2;
                    if (j38 > 0) {
                        long j2 = j36;
                        while (j2 < j37) {
                            long j3 = j2 * j;
                            long j4 = j3 + 1;
                            floatLargeArray15.setFloat(j39 + j3, (FloatFFT_1D.this.bk1l.getFloat(j3) * floatLargeArray16.getFloat(j3)) - (FloatFFT_1D.this.bk1l.getFloat(j4) * floatLargeArray16.getFloat(j4)));
                            floatLargeArray15.setFloat(j39 + j4, (FloatFFT_1D.this.bk1l.getFloat(j4) * floatLargeArray16.getFloat(j3)) + (FloatFFT_1D.this.bk1l.getFloat(j3) * floatLargeArray16.getFloat(j4)));
                            j2++;
                            j = 2;
                        }
                        return;
                    }
                    for (long j5 = j36; j5 < j37; j5++) {
                        long j6 = j5 * 2;
                        long j7 = j6 + 1;
                        floatLargeArray15.setFloat(j39 + j6, (FloatFFT_1D.this.bk1l.getFloat(j6) * floatLargeArray16.getFloat(j6)) + (FloatFFT_1D.this.bk1l.getFloat(j7) * floatLargeArray16.getFloat(j7)));
                        floatLargeArray15.setFloat(j39 + j7, ((-FloatFFT_1D.this.bk1l.getFloat(j7)) * floatLargeArray16.getFloat(j6)) + (FloatFFT_1D.this.bk1l.getFloat(j6) * floatLargeArray16.getFloat(j7)));
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

    private void bluestein_real_forward(float[] fArr, int i) {
        Class<FloatFFT_1D> cls = FloatFFT_1D.class;
        final float[] fArr2 = new float[(this.nBluestein * 2)];
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (numberOfThreads <= 1 || ((long) this.n) < CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
            for (int i2 = 0; i2 < this.n; i2++) {
                int i3 = i2 * 2;
                int i4 = i3 + 1;
                int i5 = i + i2;
                float f = fArr[i5];
                float[] fArr3 = this.bk1;
                fArr2[i3] = f * fArr3[i3];
                fArr2[i4] = (-fArr[i5]) * fArr3[i4];
            }
            CommonUtils.cftbsub(this.nBluestein * 2, fArr2, 0, this.ip, this.nw, this.w);
            for (int i6 = 0; i6 < this.nBluestein; i6++) {
                int i7 = i6 * 2;
                int i8 = i7 + 1;
                float f2 = fArr2[i7];
                float[] fArr4 = this.bk2;
                float f3 = fArr4[i8];
                float f4 = fArr2[i8];
                float f5 = fArr4[i7];
                fArr2[i7] = (f2 * f5) - (f4 * f3);
                fArr2[i8] = (f2 * f3) + (f4 * f5);
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
                final float[] fArr5 = fArr2;
                int i15 = i11;
                final float[] fArr6 = fArr;
                futureArr[i15] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i12; i < i13; i++) {
                            int i2 = i * 2;
                            int i3 = i2 + 1;
                            int i4 = i14 + i;
                            fArr5[i2] = fArr6[i4] * FloatFFT_1D.this.bk1[i2];
                            fArr5[i3] = (-fArr6[i4]) * FloatFFT_1D.this.bk1[i3];
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
            CommonUtils.cftbsub(this.nBluestein * 2, fArr2, 0, this.ip, this.nw, this.w);
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
                            float f = (fArr2[i2] * FloatFFT_1D.this.bk2[i3]) + (fArr2[i3] * FloatFFT_1D.this.bk2[i2]);
                            float[] fArr = fArr2;
                            fArr[i2] = (fArr[i2] * FloatFFT_1D.this.bk2[i2]) - (fArr2[i3] * FloatFFT_1D.this.bk2[i3]);
                            fArr2[i3] = f;
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
        CommonUtils.cftfsub(this.nBluestein * 2, fArr2, 0, this.ip, this.nw, this.w);
        int i20 = this.n;
        if (i20 % 2 == 0) {
            float[] fArr7 = this.bk1;
            fArr[i] = (fArr7[0] * fArr2[0]) + (fArr7[1] * fArr2[1]);
            fArr[i + 1] = (fArr7[i20] * fArr2[i20]) + (fArr7[i20 + 1] * fArr2[i20 + 1]);
            for (int i21 = 1; i21 < this.n / 2; i21++) {
                int i22 = i21 * 2;
                int i23 = i22 + 1;
                float[] fArr8 = this.bk1;
                fArr[i + i22] = (fArr8[i22] * fArr2[i22]) + (fArr8[i23] * fArr2[i23]);
                fArr[i + i23] = ((-fArr8[i23]) * fArr2[i22]) + (fArr8[i22] * fArr2[i23]);
            }
            return;
        }
        float[] fArr9 = this.bk1;
        fArr[i] = (fArr9[0] * fArr2[0]) + (fArr9[1] * fArr2[1]);
        fArr[i + 1] = ((-fArr9[i20]) * fArr2[i20 - 1]) + (fArr9[i20 - 1] * fArr2[i20]);
        int i24 = 1;
        while (true) {
            int i25 = this.n;
            if (i24 < (i25 - 1) / 2) {
                int i26 = i24 * 2;
                int i27 = i26 + 1;
                float[] fArr10 = this.bk1;
                fArr[i + i26] = (fArr10[i26] * fArr2[i26]) + (fArr10[i27] * fArr2[i27]);
                fArr[i + i27] = ((-fArr10[i27]) * fArr2[i26]) + (fArr10[i26] * fArr2[i27]);
                i24++;
            } else {
                float[] fArr11 = this.bk1;
                fArr[(i + i25) - 1] = (fArr11[i25 - 1] * fArr2[i25 - 1]) + (fArr11[i25] * fArr2[i25]);
                return;
            }
        }
    }

    private void bluestein_real_forward(FloatLargeArray floatLargeArray, long j) {
        long j2;
        FloatLargeArray floatLargeArray2;
        FloatLargeArray floatLargeArray3;
        FloatLargeArray floatLargeArray4 = floatLargeArray;
        long j3 = j;
        Class<FloatFFT_1D> cls = FloatFFT_1D.class;
        FloatLargeArray floatLargeArray5 = new FloatLargeArray(this.nBluesteinl * 2);
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (numberOfThreads <= 1 || this.nl <= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
            FloatLargeArray floatLargeArray6 = floatLargeArray5;
            for (long j4 = 0; j4 < this.nl; j4++) {
                long j5 = j4 * 2;
                long j6 = j5 + 1;
                long j7 = j + j4;
                FloatLargeArray floatLargeArray7 = floatLargeArray;
                FloatLargeArray floatLargeArray8 = floatLargeArray6;
                floatLargeArray8.setFloat(j5, floatLargeArray7.getFloat(j7) * this.bk1l.getFloat(j5));
                floatLargeArray8.setFloat(j6, (-floatLargeArray7.getFloat(j7)) * this.bk1l.getFloat(j6));
            }
            floatLargeArray2 = floatLargeArray;
            j2 = j;
            FloatLargeArray floatLargeArray9 = floatLargeArray6;
            long j8 = 1;
            FloatLargeArray floatLargeArray10 = floatLargeArray9;
            CommonUtils.cftbsub(this.nBluesteinl * 2, floatLargeArray9, 0, this.ipl, this.nwl, this.wl);
            long j9 = 0;
            while (j9 < this.nBluesteinl) {
                long j10 = j9 * 2;
                long j11 = j10 + j8;
                FloatLargeArray floatLargeArray11 = floatLargeArray10;
                float f = (floatLargeArray11.getFloat(j10) * this.bk2l.getFloat(j11)) + (floatLargeArray11.getFloat(j11) * this.bk2l.getFloat(j10));
                floatLargeArray11.setFloat(j10, (floatLargeArray11.getFloat(j10) * this.bk2l.getFloat(j10)) - (floatLargeArray11.getFloat(j11) * this.bk2l.getFloat(j11)));
                floatLargeArray11.setFloat(j11, f);
                j9++;
                j8 = 1;
            }
            floatLargeArray3 = floatLargeArray10;
        } else {
            int i = 4;
            if (numberOfThreads < 4 || this.nl <= CommonUtils.getThreadsBeginN_1D_FFT_4Threads()) {
                i = 2;
            }
            int i2 = i;
            Future[] futureArr = new Future[i2];
            long j12 = (long) i2;
            long j13 = this.nl / j12;
            int i3 = 0;
            while (i3 < i2) {
                long j14 = ((long) i3) * j13;
                Future[] futureArr2 = futureArr;
                final long j15 = j14;
                final long j16 = i3 == i2 + -1 ? this.nl : j14 + j13;
                final long j17 = j;
                int i4 = i2;
                final FloatLargeArray floatLargeArray12 = floatLargeArray5;
                FloatLargeArray floatLargeArray13 = floatLargeArray5;
                final FloatLargeArray floatLargeArray14 = floatLargeArray;
                futureArr2[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (long j = j15; j < j16; j++) {
                            long j2 = 2 * j;
                            long j3 = j2 + 1;
                            long j4 = j17 + j;
                            floatLargeArray12.setFloat(j2, floatLargeArray14.getFloat(j4) * FloatFFT_1D.this.bk1l.getFloat(j2));
                            floatLargeArray12.setFloat(j3, (-floatLargeArray14.getFloat(j4)) * FloatFFT_1D.this.bk1l.getFloat(j3));
                        }
                    }
                });
                i3++;
                i2 = i4;
                floatLargeArray5 = floatLargeArray13;
                futureArr = futureArr2;
            }
            Future[] futureArr3 = futureArr;
            int i5 = i2;
            FloatLargeArray floatLargeArray15 = floatLargeArray5;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
            } catch (ExecutionException e2) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
            }
            CommonUtils.cftbsub(this.nBluesteinl * 2, floatLargeArray15, 0, this.ipl, this.nwl, this.wl);
            long j18 = this.nBluesteinl / j12;
            int i6 = 0;
            while (i6 < i5) {
                final long j19 = ((long) i6) * j18;
                final long j20 = i6 == i5 + -1 ? this.nBluesteinl : j19 + j18;
                final FloatLargeArray floatLargeArray16 = floatLargeArray15;
                futureArr3[i6] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (long j = j19; j < j20; j++) {
                            long j2 = 2 * j;
                            long j3 = j2 + 1;
                            float f = (floatLargeArray16.getFloat(j2) * FloatFFT_1D.this.bk2l.getFloat(j3)) + (floatLargeArray16.getFloat(j3) * FloatFFT_1D.this.bk2l.getFloat(j2));
                            FloatLargeArray floatLargeArray = floatLargeArray16;
                            floatLargeArray.setFloat(j2, (floatLargeArray.getFloat(j2) * FloatFFT_1D.this.bk2l.getFloat(j2)) - (floatLargeArray16.getFloat(j3) * FloatFFT_1D.this.bk2l.getFloat(j3)));
                            floatLargeArray16.setFloat(j3, f);
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
            floatLargeArray2 = floatLargeArray;
            j2 = j;
            floatLargeArray3 = floatLargeArray15;
        }
        FloatLargeArray floatLargeArray17 = floatLargeArray3;
        CommonUtils.cftfsub(this.nBluesteinl * 2, floatLargeArray3, 0, this.ipl, this.nwl, this.wl);
        if (this.nl % 2 == 0) {
            long j21 = 1;
            floatLargeArray2.setFloat(j2, (this.bk1l.getFloat(0) * floatLargeArray17.getFloat(0)) + (this.bk1l.getFloat(1) * floatLargeArray17.getFloat(1)));
            floatLargeArray2.setFloat(j2 + 1, (this.bk1l.getFloat(this.nl) * floatLargeArray17.getFloat(this.nl)) + (this.bk1l.getFloat(this.nl + 1) * floatLargeArray17.getFloat(this.nl + 1)));
            long j22 = 1;
            while (j22 < this.nl / 2) {
                long j23 = j22 * 2;
                long j24 = j23 + j21;
                floatLargeArray2.setFloat(j2 + j23, (this.bk1l.getFloat(j23) * floatLargeArray17.getFloat(j23)) + (this.bk1l.getFloat(j24) * floatLargeArray17.getFloat(j24)));
                floatLargeArray2.setFloat(j2 + j24, ((-this.bk1l.getFloat(j24)) * floatLargeArray17.getFloat(j23)) + (this.bk1l.getFloat(j23) * floatLargeArray17.getFloat(j24)));
                j22++;
                j21 = 1;
            }
            return;
        }
        long j25 = 1;
        floatLargeArray2.setFloat(j2, (this.bk1l.getFloat(0) * floatLargeArray17.getFloat(0)) + (this.bk1l.getFloat(1) * floatLargeArray17.getFloat(1)));
        floatLargeArray2.setFloat(j2 + 1, ((-this.bk1l.getFloat(this.nl)) * floatLargeArray17.getFloat(this.nl - 1)) + (this.bk1l.getFloat(this.nl - 1) * floatLargeArray17.getFloat(this.nl)));
        long j26 = 1;
        while (true) {
            long j27 = this.nl;
            if (j26 < (j27 - j25) / 2) {
                long j28 = j26 * 2;
                long j29 = j28 + j25;
                floatLargeArray2.setFloat(j2 + j28, (this.bk1l.getFloat(j28) * floatLargeArray17.getFloat(j28)) + (this.bk1l.getFloat(j29) * floatLargeArray17.getFloat(j29)));
                floatLargeArray2.setFloat(j2 + j29, ((-this.bk1l.getFloat(j29)) * floatLargeArray17.getFloat(j28)) + (this.bk1l.getFloat(j28) * floatLargeArray17.getFloat(j29)));
                j26++;
                j25 = 1;
            } else {
                long j30 = j25;
                floatLargeArray2.setFloat((j2 + j27) - j30, (this.bk1l.getFloat(j27 - j30) * floatLargeArray17.getFloat(this.nl - j30)) + (this.bk1l.getFloat(this.nl) * floatLargeArray17.getFloat(this.nl)));
                return;
            }
        }
    }

    private void bluestein_real_inverse(float[] fArr, int i) {
        int i2;
        int i3;
        int i4;
        Class<FloatFFT_1D> cls = FloatFFT_1D.class;
        final float[] fArr2 = new float[(this.nBluestein * 2)];
        int i5 = 0;
        if (this.n % 2 != 0) {
            float f = fArr[i];
            float[] fArr3 = this.bk1;
            fArr2[0] = f * fArr3[0];
            fArr2[1] = fArr[i] * fArr3[1];
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
                float f2 = fArr[i9];
                float[] fArr4 = this.bk1;
                fArr2[i7] = (f2 * fArr4[i7]) - (fArr[i10] * fArr4[i8]);
                fArr2[i8] = (fArr[i9] * fArr4[i8]) + (fArr[i10] * fArr4[i7]);
                i6++;
            }
            float f3 = fArr[(i + i3) - 1];
            float[] fArr5 = this.bk1;
            int i11 = i + 1;
            fArr2[i3 - 1] = (f3 * fArr5[i3 - 1]) - (fArr[i11] * fArr5[i3]);
            fArr2[i3] = (fArr[(i + i3) - 1] * fArr5[i3]) + (fArr[i11] * fArr5[i3 - 1]);
            fArr2[i3 + 1] = (fArr[(i + i3) - 1] * fArr5[i3 + 1]) + (fArr[i11] * fArr5[i3 + 2]);
            fArr2[i3 + 2] = (fArr[(i + i3) - 1] * fArr5[i3 + 2]) - (fArr[i11] * fArr5[i3 + 1]);
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
                float f4 = fArr[i16];
                float[] fArr6 = this.bk1;
                fArr2[i14] = (f4 * fArr6[i14]) + (fArr[i17] * fArr6[i15]);
                fArr2[i15] = (fArr[i16] * fArr6[i15]) - (fArr[i17] * fArr6[i14]);
                i12++;
            }
        } else {
            float f5 = fArr[i];
            float[] fArr7 = this.bk1;
            fArr2[0] = f5 * fArr7[0];
            fArr2[1] = fArr[i] * fArr7[1];
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
                float f6 = fArr[i21];
                float[] fArr8 = this.bk1;
                fArr2[i19] = (f6 * fArr8[i19]) - (fArr[i22] * fArr8[i20]);
                fArr2[i20] = (fArr[i21] * fArr8[i20]) + (fArr[i22] * fArr8[i19]);
                i18++;
            }
            int i23 = i + 1;
            float f7 = fArr[i23];
            float[] fArr9 = this.bk1;
            fArr2[i4] = f7 * fArr9[i4];
            fArr2[i4 + 1] = fArr[i23] * fArr9[i4 + 1];
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
                float f8 = fArr[i28];
                float[] fArr10 = this.bk1;
                fArr2[i26] = (f8 * fArr10[i26]) + (fArr[i29] * fArr10[i27]);
                fArr2[i27] = (fArr[i28] * fArr10[i27]) - (fArr[i29] * fArr10[i26]);
                i24++;
            }
        }
        CommonUtils.cftbsub(this.nBluestein * 2, fArr2, 0, this.ip, this.nw, this.w);
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
                float f9 = fArr2[i31];
                float[] fArr11 = this.bk2;
                float f10 = fArr11[i32];
                float f11 = fArr2[i32];
                float f12 = fArr11[i31];
                fArr2[i31] = (f9 * f12) + (f11 * f10);
                fArr2[i32] = ((-f9) * f10) + (f11 * f12);
                i30++;
            }
            CommonUtils.cftfsub(i2 * 2, fArr2, 0, this.ip, this.nw, this.w);
            while (i5 < this.n) {
                int i33 = i5 * 2;
                int i34 = i33 + 1;
                float[] fArr12 = this.bk1;
                fArr[i + i5] = (fArr12[i33] * fArr2[i33]) - (fArr12[i34] * fArr2[i34]);
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
                        float f = ((-fArr2[i2]) * FloatFFT_1D.this.bk2[i3]) + (fArr2[i3] * FloatFFT_1D.this.bk2[i2]);
                        float[] fArr = fArr2;
                        fArr[i2] = (fArr[i2] * FloatFFT_1D.this.bk2[i2]) + (fArr2[i3] * FloatFFT_1D.this.bk2[i3]);
                        fArr2[i3] = f;
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
        CommonUtils.cftfsub(this.nBluestein * 2, fArr2, 0, this.ip, this.nw, this.w);
        int i40 = this.n / i35;
        while (i5 < i35) {
            final int i41 = i5 * i40;
            final int i42 = i5 == i35 + -1 ? this.n : i41 + i40;
            final float[] fArr13 = fArr;
            final int i43 = i;
            final float[] fArr14 = fArr2;
            futureArr[i5] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    for (int i = i41; i < i42; i++) {
                        int i2 = i * 2;
                        int i3 = i2 + 1;
                        fArr13[i43 + i] = (FloatFFT_1D.this.bk1[i2] * fArr14[i2]) - (FloatFFT_1D.this.bk1[i3] * fArr14[i3]);
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

    private void bluestein_real_inverse(FloatLargeArray floatLargeArray, long j) {
        Class<FloatFFT_1D> cls;
        long j2;
        long j3;
        long j4;
        long j5;
        FloatLargeArray floatLargeArray2 = floatLargeArray;
        Class<FloatFFT_1D> cls2 = FloatFFT_1D.class;
        long j6 = 2;
        FloatLargeArray floatLargeArray3 = new FloatLargeArray(this.nBluesteinl * 2);
        long j7 = 1;
        if (this.nl % 2 != 0) {
            long j8 = 1;
            cls = cls2;
            floatLargeArray3.setFloat(0, floatLargeArray.getFloat(j) * this.bk1l.getFloat(0));
            floatLargeArray3.setFloat(1, floatLargeArray.getFloat(j) * this.bk1l.getFloat(1));
            long j9 = 1;
            while (true) {
                j4 = this.nl;
                if (j9 >= (j4 - j8) / 2) {
                    break;
                }
                long j10 = j9 * 2;
                long j11 = j10 + j8;
                long j12 = j + j10;
                long j13 = j + j11;
                floatLargeArray3.setFloat(j10, (floatLargeArray2.getFloat(j12) * this.bk1l.getFloat(j10)) - (floatLargeArray2.getFloat(j13) * this.bk1l.getFloat(j11)));
                floatLargeArray3.setFloat(j11, (floatLargeArray2.getFloat(j12) * this.bk1l.getFloat(j11)) + (floatLargeArray2.getFloat(j13) * this.bk1l.getFloat(j10)));
                j8 = 1;
                j9++;
            }
            long j14 = j + j8;
            floatLargeArray3.setFloat(j4 - j8, (floatLargeArray2.getFloat((j + j4) - j8) * this.bk1l.getFloat(this.nl - j8)) - (floatLargeArray2.getFloat(j14) * this.bk1l.getFloat(this.nl)));
            long j15 = this.nl;
            floatLargeArray3.setFloat(j15, (floatLargeArray2.getFloat((j + j15) - j8) * this.bk1l.getFloat(this.nl)) + (floatLargeArray2.getFloat(j14) * this.bk1l.getFloat(this.nl - j8)));
            long j16 = this.nl;
            floatLargeArray3.setFloat(j16 + j8, (floatLargeArray2.getFloat((j + j16) - j8) * this.bk1l.getFloat(this.nl + j8)) + (floatLargeArray2.getFloat(j14) * this.bk1l.getFloat(this.nl + 2)));
            long j17 = this.nl;
            j2 = 1;
            floatLargeArray3.setFloat(j17 + 2, (floatLargeArray2.getFloat((j + j17) - 1) * this.bk1l.getFloat(this.nl + 2)) - (floatLargeArray2.getFloat(j14) * this.bk1l.getFloat(this.nl + 1)));
            long j18 = 2;
            long j19 = ((this.nl - 1) / 2) + 2;
            while (true) {
                long j20 = this.nl;
                if (j19 >= j20) {
                    break;
                }
                long j21 = j19 * j18;
                long j22 = j19;
                long j23 = j21 + j2;
                long j24 = (j + (j20 * j18)) - j21;
                long j25 = j24 + j2;
                floatLargeArray3.setFloat(j21, (floatLargeArray2.getFloat(j24) * this.bk1l.getFloat(j21)) + (floatLargeArray2.getFloat(j25) * this.bk1l.getFloat(j23)));
                floatLargeArray3.setFloat(j23, (floatLargeArray2.getFloat(j24) * this.bk1l.getFloat(j23)) - (floatLargeArray2.getFloat(j25) * this.bk1l.getFloat(j21)));
                j2 = 1;
                j19 = j22 + 1;
                j18 = 2;
            }
        } else {
            floatLargeArray3.setFloat(0, floatLargeArray.getFloat(j) * this.bk1l.getFloat(0));
            floatLargeArray3.setFloat(1, floatLargeArray.getFloat(j) * this.bk1l.getFloat(1));
            long j26 = 1;
            while (true) {
                j5 = this.nl;
                if (j26 >= j5 / j6) {
                    break;
                }
                long j27 = j26 * j6;
                long j28 = j27 + j7;
                long j29 = j + j27;
                long j30 = j + j28;
                floatLargeArray3.setFloat(j27, (floatLargeArray2.getFloat(j29) * this.bk1l.getFloat(j27)) - (floatLargeArray2.getFloat(j30) * this.bk1l.getFloat(j28)));
                floatLargeArray3.setFloat(j28, (floatLargeArray2.getFloat(j29) * this.bk1l.getFloat(j28)) + (floatLargeArray2.getFloat(j30) * this.bk1l.getFloat(j27)));
                j26++;
                j7 = 1;
                cls2 = cls2;
                j6 = 2;
            }
            long j31 = j7;
            cls = cls2;
            long j32 = j + j31;
            floatLargeArray3.setFloat(j5, floatLargeArray2.getFloat(j32) * this.bk1l.getFloat(this.nl));
            floatLargeArray3.setFloat(this.nl + j31, floatLargeArray2.getFloat(j32) * this.bk1l.getFloat(this.nl + j31));
            long j33 = 2;
            long j34 = (this.nl / 2) + j31;
            while (true) {
                long j35 = this.nl;
                if (j34 >= j35) {
                    break;
                }
                long j36 = j34 * j33;
                long j37 = j34;
                long j38 = j36 + j31;
                long j39 = (j + (j35 * j33)) - j36;
                long j40 = j39 + j31;
                floatLargeArray3.setFloat(j36, (floatLargeArray2.getFloat(j39) * this.bk1l.getFloat(j36)) + (floatLargeArray2.getFloat(j40) * this.bk1l.getFloat(j38)));
                floatLargeArray3.setFloat(j38, (floatLargeArray2.getFloat(j39) * this.bk1l.getFloat(j38)) - (floatLargeArray2.getFloat(j40) * this.bk1l.getFloat(j36)));
                j31 = 1;
                j34 = j37 + 1;
                j33 = 2;
            }
            j2 = j31;
        }
        long j41 = j2;
        CommonUtils.cftbsub(this.nBluesteinl * 2, floatLargeArray3, 0, this.ipl, this.nwl, this.wl);
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (numberOfThreads <= 1 || this.nl <= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
            long j42 = 0;
            while (true) {
                j3 = this.nBluesteinl;
                if (j42 >= j3) {
                    break;
                }
                long j43 = j42 * 2;
                long j44 = j43 + j41;
                float f = ((-floatLargeArray3.getFloat(j43)) * this.bk2l.getFloat(j44)) + (floatLargeArray3.getFloat(j44) * this.bk2l.getFloat(j43));
                floatLargeArray3.setFloat(j43, (floatLargeArray3.getFloat(j43) * this.bk2l.getFloat(j43)) + (floatLargeArray3.getFloat(j44) * this.bk2l.getFloat(j44)));
                floatLargeArray3.setFloat(j44, f);
                j42 += j41;
            }
            CommonUtils.cftfsub(j3 * 2, floatLargeArray3, 0, this.ipl, this.nwl, this.wl);
            for (long j45 = 0; j45 < this.nl; j45 += j41) {
                long j46 = j45 * 2;
                long j47 = j46 + j41;
                floatLargeArray2.setFloat(j + j45, (this.bk1l.getFloat(j46) * floatLargeArray3.getFloat(j46)) - (this.bk1l.getFloat(j47) * floatLargeArray3.getFloat(j47)));
            }
            return;
        }
        int i = 4;
        if (numberOfThreads < 4 || this.nl <= CommonUtils.getThreadsBeginN_1D_FFT_4Threads()) {
            i = 2;
        }
        int i2 = i;
        Future[] futureArr = new Future[i2];
        long j48 = (long) i2;
        long j49 = this.nBluesteinl / j48;
        int i3 = 0;
        while (i3 < i2) {
            final long j50 = ((long) i3) * j49;
            final long j51 = i3 == i2 + -1 ? this.nBluesteinl : j50 + j49;
            final FloatLargeArray floatLargeArray4 = floatLargeArray3;
            futureArr[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    for (long j = j50; j < j51; j++) {
                        long j2 = 2 * j;
                        long j3 = j2 + 1;
                        float f = ((-floatLargeArray4.getFloat(j2)) * FloatFFT_1D.this.bk2l.getFloat(j3)) + (floatLargeArray4.getFloat(j3) * FloatFFT_1D.this.bk2l.getFloat(j2));
                        FloatLargeArray floatLargeArray = floatLargeArray4;
                        floatLargeArray.setFloat(j2, (floatLargeArray.getFloat(j2) * FloatFFT_1D.this.bk2l.getFloat(j2)) + (floatLargeArray4.getFloat(j3) * FloatFFT_1D.this.bk2l.getFloat(j3)));
                        floatLargeArray4.setFloat(j3, f);
                    }
                }
            });
            i3++;
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
        } catch (ExecutionException e2) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
        }
        CommonUtils.cftfsub(this.nBluesteinl * 2, floatLargeArray3, 0, this.ipl, this.nwl, this.wl);
        long j52 = this.nl / j48;
        int i4 = 0;
        while (i4 < i2) {
            final long j53 = ((long) i4) * j52;
            final long j54 = i4 == i2 + -1 ? this.nl : j53 + j52;
            final FloatLargeArray floatLargeArray5 = floatLargeArray;
            final long j55 = j;
            final FloatLargeArray floatLargeArray6 = floatLargeArray3;
            futureArr[i4] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    for (long j = j53; j < j54; j++) {
                        long j2 = 2 * j;
                        long j3 = j2 + 1;
                        floatLargeArray5.setFloat(j55 + j, (FloatFFT_1D.this.bk1l.getFloat(j2) * floatLargeArray6.getFloat(j2)) - (FloatFFT_1D.this.bk1l.getFloat(j3) * floatLargeArray6.getFloat(j3)));
                    }
                }
            });
            i4++;
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e3) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e3);
        } catch (ExecutionException e4) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e4);
        }
    }

    private void bluestein_real_inverse2(float[] fArr, int i) {
        Class<FloatFFT_1D> cls = FloatFFT_1D.class;
        final float[] fArr2 = new float[(this.nBluestein * 2)];
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (numberOfThreads <= 1 || ((long) this.n) < CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
            for (int i2 = 0; i2 < this.n; i2++) {
                int i3 = i2 * 2;
                int i4 = i3 + 1;
                int i5 = i + i2;
                float f = fArr[i5];
                float[] fArr3 = this.bk1;
                fArr2[i3] = f * fArr3[i3];
                fArr2[i4] = fArr[i5] * fArr3[i4];
            }
            CommonUtils.cftbsub(this.nBluestein * 2, fArr2, 0, this.ip, this.nw, this.w);
            for (int i6 = 0; i6 < this.nBluestein; i6++) {
                int i7 = i6 * 2;
                int i8 = i7 + 1;
                float f2 = fArr2[i7];
                float[] fArr4 = this.bk2;
                float f3 = fArr4[i8];
                float f4 = fArr2[i8];
                float f5 = fArr4[i7];
                fArr2[i7] = (f2 * f5) + (f4 * f3);
                fArr2[i8] = ((-f2) * f3) + (f4 * f5);
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
                final float[] fArr5 = fArr2;
                int i15 = i11;
                final float[] fArr6 = fArr;
                futureArr[i15] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i12; i < i13; i++) {
                            int i2 = i * 2;
                            int i3 = i2 + 1;
                            int i4 = i14 + i;
                            fArr5[i2] = fArr6[i4] * FloatFFT_1D.this.bk1[i2];
                            fArr5[i3] = fArr6[i4] * FloatFFT_1D.this.bk1[i3];
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
            CommonUtils.cftbsub(this.nBluestein * 2, fArr2, 0, this.ip, this.nw, this.w);
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
                            float f = ((-fArr2[i2]) * FloatFFT_1D.this.bk2[i3]) + (fArr2[i3] * FloatFFT_1D.this.bk2[i2]);
                            float[] fArr = fArr2;
                            fArr[i2] = (fArr[i2] * FloatFFT_1D.this.bk2[i2]) + (fArr2[i3] * FloatFFT_1D.this.bk2[i3]);
                            fArr2[i3] = f;
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
        CommonUtils.cftfsub(this.nBluestein * 2, fArr2, 0, this.ip, this.nw, this.w);
        int i20 = this.n;
        if (i20 % 2 == 0) {
            float[] fArr7 = this.bk1;
            fArr[i] = (fArr7[0] * fArr2[0]) - (fArr7[1] * fArr2[1]);
            fArr[i + 1] = (fArr7[i20] * fArr2[i20]) - (fArr7[i20 + 1] * fArr2[i20 + 1]);
            for (int i21 = 1; i21 < this.n / 2; i21++) {
                int i22 = i21 * 2;
                int i23 = i22 + 1;
                float[] fArr8 = this.bk1;
                fArr[i + i22] = (fArr8[i22] * fArr2[i22]) - (fArr8[i23] * fArr2[i23]);
                fArr[i + i23] = (fArr8[i23] * fArr2[i22]) + (fArr8[i22] * fArr2[i23]);
            }
            return;
        }
        float[] fArr9 = this.bk1;
        fArr[i] = (fArr9[0] * fArr2[0]) - (fArr9[1] * fArr2[1]);
        fArr[i + 1] = (fArr9[i20] * fArr2[i20 - 1]) + (fArr9[i20 - 1] * fArr2[i20]);
        int i24 = 1;
        while (true) {
            int i25 = this.n;
            if (i24 < (i25 - 1) / 2) {
                int i26 = i24 * 2;
                int i27 = i26 + 1;
                float[] fArr10 = this.bk1;
                fArr[i + i26] = (fArr10[i26] * fArr2[i26]) - (fArr10[i27] * fArr2[i27]);
                fArr[i + i27] = (fArr10[i27] * fArr2[i26]) + (fArr10[i26] * fArr2[i27]);
                i24++;
            } else {
                float[] fArr11 = this.bk1;
                fArr[(i + i25) - 1] = (fArr11[i25 - 1] * fArr2[i25 - 1]) - (fArr11[i25] * fArr2[i25]);
                return;
            }
        }
    }

    private void bluestein_real_inverse2(FloatLargeArray floatLargeArray, long j) {
        long j2;
        FloatLargeArray floatLargeArray2;
        FloatLargeArray floatLargeArray3;
        FloatLargeArray floatLargeArray4 = floatLargeArray;
        long j3 = j;
        Class<FloatFFT_1D> cls = FloatFFT_1D.class;
        FloatLargeArray floatLargeArray5 = new FloatLargeArray(this.nBluesteinl * 2);
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (numberOfThreads <= 1 || this.nl <= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
            FloatLargeArray floatLargeArray6 = floatLargeArray5;
            for (long j4 = 0; j4 < this.nl; j4++) {
                long j5 = j4 * 2;
                long j6 = j5 + 1;
                long j7 = j + j4;
                FloatLargeArray floatLargeArray7 = floatLargeArray;
                FloatLargeArray floatLargeArray8 = floatLargeArray6;
                floatLargeArray8.setFloat(j5, floatLargeArray7.getFloat(j7) * this.bk1l.getFloat(j5));
                floatLargeArray8.setFloat(j6, floatLargeArray7.getFloat(j7) * this.bk1l.getFloat(j6));
            }
            floatLargeArray2 = floatLargeArray;
            j2 = j;
            FloatLargeArray floatLargeArray9 = floatLargeArray6;
            long j8 = 1;
            FloatLargeArray floatLargeArray10 = floatLargeArray9;
            CommonUtils.cftbsub(this.nBluesteinl * 2, floatLargeArray9, 0, this.ipl, this.nwl, this.wl);
            long j9 = 0;
            while (j9 < this.nBluesteinl) {
                long j10 = j9 * 2;
                long j11 = j10 + j8;
                FloatLargeArray floatLargeArray11 = floatLargeArray10;
                float f = ((-floatLargeArray11.getFloat(j10)) * this.bk2l.getFloat(j11)) + (floatLargeArray11.getFloat(j11) * this.bk2l.getFloat(j10));
                floatLargeArray11.setFloat(j10, (floatLargeArray11.getFloat(j10) * this.bk2l.getFloat(j10)) + (floatLargeArray11.getFloat(j11) * this.bk2l.getFloat(j11)));
                floatLargeArray11.setFloat(j11, f);
                j9++;
                j8 = 1;
            }
            floatLargeArray3 = floatLargeArray10;
        } else {
            int i = 4;
            if (numberOfThreads < 4 || this.nl <= CommonUtils.getThreadsBeginN_1D_FFT_4Threads()) {
                i = 2;
            }
            int i2 = i;
            Future[] futureArr = new Future[i2];
            long j12 = (long) i2;
            long j13 = this.nl / j12;
            int i3 = 0;
            while (i3 < i2) {
                long j14 = ((long) i3) * j13;
                Future[] futureArr2 = futureArr;
                final long j15 = j14;
                final long j16 = i3 == i2 + -1 ? this.nl : j14 + j13;
                final long j17 = j;
                int i4 = i2;
                final FloatLargeArray floatLargeArray12 = floatLargeArray5;
                FloatLargeArray floatLargeArray13 = floatLargeArray5;
                final FloatLargeArray floatLargeArray14 = floatLargeArray;
                futureArr2[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (long j = j15; j < j16; j++) {
                            long j2 = 2 * j;
                            long j3 = j2 + 1;
                            long j4 = j17 + j;
                            floatLargeArray12.setFloat(j2, floatLargeArray14.getFloat(j4) * FloatFFT_1D.this.bk1l.getFloat(j2));
                            floatLargeArray12.setFloat(j3, floatLargeArray14.getFloat(j4) * FloatFFT_1D.this.bk1l.getFloat(j3));
                        }
                    }
                });
                i3++;
                i2 = i4;
                floatLargeArray5 = floatLargeArray13;
                futureArr = futureArr2;
            }
            Future[] futureArr3 = futureArr;
            int i5 = i2;
            FloatLargeArray floatLargeArray15 = floatLargeArray5;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
            } catch (ExecutionException e2) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
            }
            CommonUtils.cftbsub(this.nBluesteinl * 2, floatLargeArray15, 0, this.ipl, this.nwl, this.wl);
            long j18 = this.nBluesteinl / j12;
            int i6 = 0;
            while (i6 < i5) {
                final long j19 = ((long) i6) * j18;
                final long j20 = i6 == i5 + -1 ? this.nBluesteinl : j19 + j18;
                final FloatLargeArray floatLargeArray16 = floatLargeArray15;
                futureArr3[i6] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (long j = j19; j < j20; j++) {
                            long j2 = 2 * j;
                            long j3 = j2 + 1;
                            float f = ((-floatLargeArray16.getFloat(j2)) * FloatFFT_1D.this.bk2l.getFloat(j3)) + (floatLargeArray16.getFloat(j3) * FloatFFT_1D.this.bk2l.getFloat(j2));
                            FloatLargeArray floatLargeArray = floatLargeArray16;
                            floatLargeArray.setFloat(j2, (floatLargeArray.getFloat(j2) * FloatFFT_1D.this.bk2l.getFloat(j2)) + (floatLargeArray16.getFloat(j3) * FloatFFT_1D.this.bk2l.getFloat(j3)));
                            floatLargeArray16.setFloat(j3, f);
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
            floatLargeArray2 = floatLargeArray;
            j2 = j;
            floatLargeArray3 = floatLargeArray15;
        }
        FloatLargeArray floatLargeArray17 = floatLargeArray3;
        CommonUtils.cftfsub(this.nBluesteinl * 2, floatLargeArray3, 0, this.ipl, this.nwl, this.wl);
        if (this.nl % 2 == 0) {
            long j21 = 1;
            floatLargeArray2.setFloat(j2, (this.bk1l.getFloat(0) * floatLargeArray17.getFloat(0)) - (this.bk1l.getFloat(1) * floatLargeArray17.getFloat(1)));
            floatLargeArray2.setFloat(j2 + 1, (this.bk1l.getFloat(this.nl) * floatLargeArray17.getFloat(this.nl)) - (this.bk1l.getFloat(this.nl + 1) * floatLargeArray17.getFloat(this.nl + 1)));
            long j22 = 1;
            while (j22 < this.nl / 2) {
                long j23 = j22 * 2;
                long j24 = j23 + j21;
                floatLargeArray2.setFloat(j2 + j23, (this.bk1l.getFloat(j23) * floatLargeArray17.getFloat(j23)) - (this.bk1l.getFloat(j24) * floatLargeArray17.getFloat(j24)));
                floatLargeArray2.setFloat(j2 + j24, (this.bk1l.getFloat(j24) * floatLargeArray17.getFloat(j23)) + (this.bk1l.getFloat(j23) * floatLargeArray17.getFloat(j24)));
                j22++;
                j21 = 1;
            }
            return;
        }
        long j25 = 1;
        floatLargeArray2.setFloat(j2, (this.bk1l.getFloat(0) * floatLargeArray17.getFloat(0)) - (this.bk1l.getFloat(1) * floatLargeArray17.getFloat(1)));
        floatLargeArray2.setFloat(j2 + 1, (this.bk1l.getFloat(this.nl) * floatLargeArray17.getFloat(this.nl - 1)) + (this.bk1l.getFloat(this.nl - 1) * floatLargeArray17.getFloat(this.nl)));
        long j26 = 1;
        while (true) {
            long j27 = this.nl;
            if (j26 < (j27 - j25) / 2) {
                long j28 = j26 * 2;
                long j29 = j28 + j25;
                floatLargeArray2.setFloat(j2 + j28, (this.bk1l.getFloat(j28) * floatLargeArray17.getFloat(j28)) - (this.bk1l.getFloat(j29) * floatLargeArray17.getFloat(j29)));
                floatLargeArray2.setFloat(j2 + j29, (this.bk1l.getFloat(j29) * floatLargeArray17.getFloat(j28)) + (this.bk1l.getFloat(j28) * floatLargeArray17.getFloat(j29)));
                j26++;
                j25 = 1;
            } else {
                long j30 = j25;
                floatLargeArray2.setFloat((j2 + j27) - j30, (this.bk1l.getFloat(j27 - j30) * floatLargeArray17.getFloat(this.nl - j30)) - (this.bk1l.getFloat(this.nl) * floatLargeArray17.getFloat(this.nl)));
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void rfftf(float[] fArr, int i) {
        int i2 = this.n;
        if (i2 != 1) {
            float[] fArr2 = new float[i2];
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
                                    radfg(i10, i8, i9, i11, fArr, i, fArr2, 0, i12);
                                    i6 = 1;
                                } else {
                                    radfg(i10, i8, i9, i11, fArr2, 0, fArr, i, i12);
                                    i6 = 0;
                                }
                                i7++;
                                i2 = i9;
                                i5 = i12;
                            } else if (i13 == 0) {
                                radf5(i10, i9, fArr, i, fArr2, 0, i12);
                            } else {
                                radf5(i10, i9, fArr2, 0, fArr, i, i12);
                            }
                        } else if (i13 == 0) {
                            radf4(i10, i9, fArr, i, fArr2, 0, i12);
                        } else {
                            radf4(i10, i9, fArr2, 0, fArr, i, i12);
                        }
                    } else if (i13 == 0) {
                        radf3(i10, i9, fArr, i, fArr2, 0, i12);
                    } else {
                        radf3(i10, i9, fArr2, 0, fArr, i, i12);
                    }
                } else if (i13 == 0) {
                    radf2(i10, i9, fArr, i, fArr2, 0, i12);
                } else {
                    radf2(i10, i9, fArr2, 0, fArr, i, i12);
                }
                i6 = i13;
                i7++;
                i2 = i9;
                i5 = i12;
            }
            if (i6 != 1) {
                System.arraycopy(fArr2, 0, fArr, i, this.n);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void rfftf(FloatLargeArray floatLargeArray, long j) {
        FloatLargeArray floatLargeArray2;
        long j2;
        FloatFFT_1D floatFFT_1D = this;
        if (floatFFT_1D.nl != 1) {
            FloatLargeArray floatLargeArray3 = new FloatLargeArray(floatFFT_1D.nl);
            long j3 = floatFFT_1D.nl * 2;
            long j4 = (long) floatFFT_1D.wtable_rl.getFloat(j3 + 1);
            long j5 = floatFFT_1D.nl;
            long j6 = j3 - 1;
            long j7 = 1;
            long j8 = 1;
            while (j8 <= j4) {
                int i = (int) floatFFT_1D.wtable_rl.getFloat((j4 - j8) + 2 + j3);
                long j9 = (long) i;
                long j10 = j5 / j9;
                long j11 = floatFFT_1D.nl / j5;
                long j12 = j11 * j10;
                long j13 = j6 - (((long) (i - 1)) * j11);
                long j14 = 1 - j7;
                if (i == 2) {
                    j2 = j4;
                    floatLargeArray2 = floatLargeArray3;
                    if (j14 == 0) {
                        radf2(j11, j10, floatLargeArray, j, floatLargeArray2, 0, j13);
                    } else {
                        radf2(j11, j10, floatLargeArray2, 0, floatLargeArray, j, j13);
                    }
                } else if (i == 3) {
                    j2 = j4;
                    floatLargeArray2 = floatLargeArray3;
                    if (j14 == 0) {
                        radf3(j11, j10, floatLargeArray, j, floatLargeArray2, 0, j13);
                    } else {
                        radf3(j11, j10, floatLargeArray2, 0, floatLargeArray, j, j13);
                    }
                } else if (i == 4) {
                    j2 = j4;
                    floatLargeArray2 = floatLargeArray3;
                    if (j14 == 0) {
                        radf4(j11, j10, floatLargeArray, j, floatLargeArray2, 0, j13);
                    } else {
                        radf4(j11, j10, floatLargeArray2, 0, floatLargeArray, j, j13);
                    }
                } else if (i != 5) {
                    if (j11 == 1) {
                        j14 = 1 - j14;
                    }
                    if (j14 == 0) {
                        j2 = j4;
                        floatLargeArray2 = floatLargeArray3;
                        radfg(j11, j9, j10, j12, floatLargeArray, j, floatLargeArray3, 0, j13);
                        j7 = 1;
                    } else {
                        j2 = j4;
                        floatLargeArray2 = floatLargeArray3;
                        radfg(j11, j9, j10, j12, floatLargeArray2, 0, floatLargeArray, j, j13);
                        j7 = 0;
                    }
                    j8++;
                    floatFFT_1D = this;
                    j5 = j10;
                    j6 = j13;
                    j4 = j2;
                    floatLargeArray3 = floatLargeArray2;
                } else {
                    j2 = j4;
                    floatLargeArray2 = floatLargeArray3;
                    if (j14 == 0) {
                        radf5(j11, j10, floatLargeArray, j, floatLargeArray2, 0, j13);
                    } else {
                        radf5(j11, j10, floatLargeArray2, 0, floatLargeArray, j, j13);
                    }
                }
                j7 = j14;
                j8++;
                floatFFT_1D = this;
                j5 = j10;
                j6 = j13;
                j4 = j2;
                floatLargeArray3 = floatLargeArray2;
            }
            FloatLargeArray floatLargeArray4 = floatLargeArray3;
            if (j7 != 1) {
                LargeArrayUtils.arraycopy(floatLargeArray4, 0, floatLargeArray, j, this.nl);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void rfftb(float[] fArr, int i) {
        int i2;
        int i3;
        int i4 = this.n;
        if (i4 != 1) {
            float[] fArr2 = new float[i4];
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
                        radb2(i14, i9, fArr, i, fArr2, 0, i7);
                    } else {
                        radb2(i14, i9, fArr2, 0, fArr, i, i7);
                    }
                } else if (i12 == 3) {
                    i2 = i12;
                    if (i10 == 0) {
                        radb3(i14, i9, fArr, i, fArr2, 0, i7);
                    } else {
                        radb3(i14, i9, fArr2, 0, fArr, i, i7);
                    }
                } else if (i12 == 4) {
                    i2 = i12;
                    if (i10 == 0) {
                        radb4(i14, i9, fArr, i, fArr2, 0, i7);
                    } else {
                        radb4(i14, i9, fArr2, 0, fArr, i, i7);
                    }
                } else if (i12 != 5) {
                    if (i10 == 0) {
                        i3 = i14;
                        i2 = i12;
                        radbg(i14, i12, i9, i15, fArr, i, fArr2, 0, i7);
                    } else {
                        i3 = i14;
                        i2 = i12;
                        radbg(i3, i2, i9, i15, fArr2, 0, fArr, i, i7);
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
                        radb5(i14, i9, fArr, i, fArr2, 0, i7);
                    } else {
                        radb5(i14, i9, fArr2, 0, fArr, i, i7);
                    }
                }
                i10 = 1 - i10;
                i7 += (i2 - 1) * i14;
                i8 = i11;
                i9 = i13;
            }
            if (i10 != 0) {
                System.arraycopy(fArr2, 0, fArr, i, this.n);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void rfftb(FloatLargeArray floatLargeArray, long j) {
        FloatLargeArray floatLargeArray2;
        long j2;
        int i;
        FloatFFT_1D floatFFT_1D = this;
        if (floatFFT_1D.nl != 1) {
            FloatLargeArray floatLargeArray3 = new FloatLargeArray(floatFFT_1D.nl);
            long j3 = floatFFT_1D.nl * 2;
            long j4 = (long) floatFFT_1D.wtable_rl.getFloat(j3 + 1);
            long j5 = floatFFT_1D.nl;
            long j6 = 1;
            long j7 = 1;
            long j8 = 0;
            while (j6 <= j4) {
                long j9 = j6 + 1;
                int i2 = (int) floatFFT_1D.wtable_rl.getFloat(j9 + j3);
                long j10 = (long) i2;
                long j11 = j10 * j7;
                long j12 = floatFFT_1D.nl / j11;
                long j13 = j12 * j7;
                if (i2 == 2) {
                    i = i2;
                    j2 = j4;
                    floatLargeArray2 = floatLargeArray3;
                    if (j8 == 0) {
                        radb2(j12, j7, floatLargeArray, j, floatLargeArray2, 0, j5);
                    } else {
                        radb2(j12, j7, floatLargeArray2, 0, floatLargeArray, j, j5);
                    }
                } else if (i2 == 3) {
                    i = i2;
                    j2 = j4;
                    floatLargeArray2 = floatLargeArray3;
                    if (j8 == 0) {
                        radb3(j12, j7, floatLargeArray, j, floatLargeArray2, 0, j5);
                    } else {
                        radb3(j12, j7, floatLargeArray2, 0, floatLargeArray, j, j5);
                    }
                } else if (i2 == 4) {
                    i = i2;
                    j2 = j4;
                    floatLargeArray2 = floatLargeArray3;
                    if (j8 == 0) {
                        radb4(j12, j7, floatLargeArray, j, floatLargeArray2, 0, j5);
                    } else {
                        radb4(j12, j7, floatLargeArray2, 0, floatLargeArray, j, j5);
                    }
                } else if (i2 != 5) {
                    if (j8 == 0) {
                        i = i2;
                        j2 = j4;
                        floatLargeArray2 = floatLargeArray3;
                        radbg(j12, j10, j7, j13, floatLargeArray, j, floatLargeArray3, 0, j5);
                    } else {
                        i = i2;
                        j2 = j4;
                        floatLargeArray2 = floatLargeArray3;
                        radbg(j12, j10, j7, j13, floatLargeArray2, 0, floatLargeArray, j, j5);
                    }
                    if (j12 != 1) {
                        j5 += ((long) (i - 1)) * j12;
                        floatFFT_1D = this;
                        j6 = j9;
                        j7 = j11;
                        j4 = j2;
                        floatLargeArray3 = floatLargeArray2;
                    }
                } else {
                    i = i2;
                    j2 = j4;
                    floatLargeArray2 = floatLargeArray3;
                    if (j8 == 0) {
                        radb5(j12, j7, floatLargeArray, j, floatLargeArray2, 0, j5);
                    } else {
                        radb5(j12, j7, floatLargeArray2, 0, floatLargeArray, j, j5);
                    }
                }
                j8 = 1 - j8;
                j5 += ((long) (i - 1)) * j12;
                floatFFT_1D = this;
                j6 = j9;
                j7 = j11;
                j4 = j2;
                floatLargeArray3 = floatLargeArray2;
            }
            FloatLargeArray floatLargeArray4 = floatLargeArray3;
            if (j8 != 0) {
                LargeArrayUtils.arraycopy(floatLargeArray4, 0, floatLargeArray, j, this.nl);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void radf2(int i, int i2, float[] fArr, int i3, float[] fArr2, int i4, int i5) {
        int i6 = i;
        int i7 = i2;
        int i8 = i7 * i6;
        int i9 = i6 * 2;
        for (int i10 = 0; i10 < i7; i10++) {
            int i11 = i4 + (i10 * i9);
            int i12 = i3 + (i10 * i6);
            int i13 = i12 + i8;
            float f = fArr[i12];
            float f2 = fArr[i13];
            fArr2[i11] = f + f2;
            fArr2[(i11 + i9) - 1] = f - f2;
        }
        int i14 = 2;
        if (i6 >= 2) {
            if (i6 != 2) {
                int i15 = 0;
                while (i15 < i7) {
                    i9 = i15 * i6;
                    int i16 = i9 * 2;
                    int i17 = i16 + i6;
                    int i18 = i9 + i8;
                    for (int i19 = i14; i19 < i6; i19 += 2) {
                        int i20 = (i19 - 1) + i5;
                        int i21 = i4 + i19 + i16;
                        int i22 = i4 + (i6 - i19) + i17;
                        int i23 = i3 + i19;
                        int i24 = i23 + i9;
                        int i25 = i23 + i18;
                        float f3 = fArr[i24 - 1];
                        float f4 = fArr[i24];
                        float f5 = fArr[i25 - 1];
                        float f6 = fArr[i25];
                        float[] fArr3 = this.wtable_r;
                        float f7 = fArr3[i20 - 1];
                        float f8 = fArr3[i20];
                        float f9 = (f7 * f5) + (f8 * f6);
                        float f10 = (f7 * f6) - (f8 * f5);
                        fArr2[i21] = f4 + f10;
                        fArr2[i21 - 1] = f3 + f9;
                        fArr2[i22] = f10 - f4;
                        fArr2[i22 - 1] = f3 - f9;
                    }
                    i15++;
                    i14 = 2;
                }
                if (i6 % 2 != 1) {
                    i14 = 2;
                } else {
                    return;
                }
            }
            int i26 = i9 * i14;
            for (int i27 = 0; i27 < i7; i27++) {
                int i28 = i4 + i26 + i6;
                int i29 = ((i3 + i6) - 1) + (i27 * i6);
                fArr2[i28] = -fArr[i29 + i8];
                fArr2[i28 - 1] = fArr[i29];
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void radf2(long j, long j2, FloatLargeArray floatLargeArray, long j3, FloatLargeArray floatLargeArray2, long j4, long j5) {
        long j6;
        long j7;
        long j8;
        FloatLargeArray floatLargeArray3 = floatLargeArray;
        FloatLargeArray floatLargeArray4 = floatLargeArray2;
        long j9 = j2 * j;
        long j10 = 2;
        long j11 = j * 2;
        long j12 = 0;
        while (j12 < j2) {
            long j13 = j4 + (j12 * j11);
            long j14 = (j13 + j11) - 1;
            long j15 = j3 + (j12 * j);
            long j16 = j11;
            float f = floatLargeArray3.getFloat(j15);
            float f2 = floatLargeArray3.getFloat(j15 + j9);
            floatLargeArray4.setFloat(j13, f + f2);
            floatLargeArray4.setFloat(j14, f - f2);
            j12++;
            j11 = j16;
            j10 = 2;
        }
        long j17 = j11;
        int i = (j > j10 ? 1 : (j == j10 ? 0 : -1));
        if (i >= 0) {
            if (i != 0) {
                j7 = j17;
                long j18 = 0;
                while (j18 < j2) {
                    j7 = j18 * j;
                    long j19 = j7 * j10;
                    long j20 = j19 + j;
                    long j21 = j7 + j9;
                    long j22 = 2;
                    while (j22 < j) {
                        long j23 = j9;
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
                        float f3 = floatLargeArray3.getFloat(j31 - 1);
                        float f4 = floatLargeArray3.getFloat(j31);
                        float f5 = floatLargeArray3.getFloat(j33 - 1);
                        float f6 = floatLargeArray3.getFloat(j33);
                        float f7 = this.wtable_rl.getFloat(j24 - 1);
                        float f8 = this.wtable_rl.getFloat(j24);
                        float f9 = (f7 * f5) + (f8 * f6);
                        float f10 = (f7 * f6) - (f8 * f5);
                        floatLargeArray4.setFloat(j26, f4 + f10);
                        floatLargeArray4.setFloat(j26 - 1, f3 + f9);
                        floatLargeArray4.setFloat(j28, f10 - f4);
                        floatLargeArray4.setFloat(j28 - 1, f3 - f9);
                        j22 += 2;
                        j21 = j34;
                        j19 = j27;
                        j9 = j23;
                        j18 = j25;
                        j20 = j30;
                        j7 = j32;
                    }
                    long j35 = j7;
                    j18++;
                    j10 = 2;
                    j9 = j9;
                }
                j6 = j9;
                j8 = j10;
                if (j % j8 == 1) {
                    return;
                }
            } else {
                j6 = j9;
                j8 = j10;
                j7 = j17;
            }
            long j36 = j7 * j8;
            for (long j37 = 0; j37 < j2; j37++) {
                long j38 = j4 + j36 + j;
                long j39 = ((j3 + j) - 1) + (j37 * j);
                floatLargeArray4.setFloat(j38, -floatLargeArray3.getFloat(j39 + j6));
                floatLargeArray4.setFloat(j38 - 1, floatLargeArray3.getFloat(j39));
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void radb2(int i, int i2, float[] fArr, int i3, float[] fArr2, int i4, int i5) {
        int i6 = i;
        int i7 = i2;
        int i8 = i7 * i6;
        for (int i9 = 0; i9 < i7; i9++) {
            int i10 = i9 * i6;
            int i11 = i10 * 2;
            int i12 = i4 + i10;
            float f = fArr[i3 + i11];
            float f2 = fArr[((i3 + i6) - 1) + i11 + i6];
            fArr2[i12] = f + f2;
            fArr2[i12 + i8] = f - f2;
        }
        int i13 = 2;
        if (i6 >= 2) {
            if (i6 != 2) {
                int i14 = 0;
                while (i14 < i7) {
                    int i15 = i14 * i6;
                    int i16 = i15 * 2;
                    int i17 = i16 + i6;
                    int i18 = i15 + i8;
                    for (int i19 = i13; i19 < i6; i19 += 2) {
                        int i20 = (i19 - 1) + i5;
                        int i21 = i4 + i19;
                        float[] fArr3 = this.wtable_r;
                        float f3 = fArr3[i20 - 1];
                        float f4 = fArr3[i20];
                        int i22 = i3 + i19 + i16;
                        int i23 = i3 + (i6 - i19) + i17;
                        int i24 = i21 + i15;
                        int i25 = i21 + i18;
                        float f5 = fArr[i22 - 1];
                        float f6 = fArr[i23 - 1];
                        float f7 = f5 - f6;
                        float f8 = fArr[i22];
                        float f9 = fArr[i23];
                        float f10 = f8 + f9;
                        fArr2[i24 - 1] = f5 + f6;
                        fArr2[i24] = f8 - f9;
                        fArr2[i25 - 1] = (f3 * f7) - (f4 * f10);
                        fArr2[i25] = (f3 * f10) + (f4 * f7);
                    }
                    i14++;
                    i13 = 2;
                }
                if (i6 % 2 == 1) {
                    return;
                }
            }
            for (int i26 = 0; i26 < i7; i26++) {
                int i27 = i26 * i6;
                int i28 = ((i4 + i6) - 1) + i27;
                int i29 = i3 + (i27 * 2) + i6;
                fArr2[i28] = fArr[i29 - 1] * 2.0f;
                fArr2[i28 + i8] = fArr[i29] * -2.0f;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void radb2(long j, long j2, FloatLargeArray floatLargeArray, long j3, FloatLargeArray floatLargeArray2, long j4, long j5) {
        long j6;
        long j7;
        long j8;
        long j9;
        long j10;
        FloatLargeArray floatLargeArray3 = floatLargeArray;
        FloatLargeArray floatLargeArray4 = floatLargeArray2;
        long j11 = j2 * j;
        long j12 = 0;
        while (true) {
            j6 = 2;
            j7 = 1;
            if (j12 >= j2) {
                break;
            }
            long j13 = j12 * j;
            long j14 = 2 * j13;
            long j15 = j4 + j13;
            float f = floatLargeArray3.getFloat(j3 + j14);
            float f2 = floatLargeArray3.getFloat(((j3 + j) - 1) + j14 + j);
            floatLargeArray4.setFloat(j15, f + f2);
            floatLargeArray4.setFloat(j15 + j11, f - f2);
            j12++;
        }
        int i = (j > 2 ? 1 : (j == 2 ? 0 : -1));
        if (i >= 0) {
            if (i != 0) {
                long j16 = 0;
                while (j16 < j2) {
                    long j17 = j16 * j;
                    long j18 = j17 * j6;
                    long j19 = j18 + j;
                    long j20 = j17 + j11;
                    long j21 = j6;
                    while (j21 < j) {
                        long j22 = (j21 - j7) + j5;
                        long j23 = j4 + j21;
                        long j24 = j11;
                        float f3 = this.wtable_rl.getFloat(j22 - j7);
                        long j25 = j3 + j21 + j18;
                        long j26 = j3 + (j - j21) + j19;
                        long j27 = j18;
                        long j28 = j23 + j17;
                        long j29 = j17;
                        long j30 = j23 + j20;
                        long j31 = j16;
                        long j32 = j25 - 1;
                        float f4 = f3;
                        float f5 = this.wtable_rl.getFloat(j22);
                        long j33 = j26 - 1;
                        float f6 = floatLargeArray3.getFloat(j32) - floatLargeArray3.getFloat(j33);
                        float f7 = floatLargeArray3.getFloat(j25) + floatLargeArray3.getFloat(j26);
                        float f8 = floatLargeArray3.getFloat(j25);
                        float f9 = floatLargeArray3.getFloat(j32);
                        float f10 = floatLargeArray3.getFloat(j26);
                        floatLargeArray4.setFloat(j28 - 1, f9 + floatLargeArray3.getFloat(j33));
                        floatLargeArray4.setFloat(j28, f8 - f10);
                        floatLargeArray4.setFloat(j30 - 1, (f4 * f6) - (f5 * f7));
                        floatLargeArray4.setFloat(j30, (f4 * f7) + (f5 * f6));
                        j21 += 2;
                        j6 = 2;
                        j18 = j27;
                        j7 = 1;
                        j16 = j31;
                        j11 = j24;
                        j17 = j29;
                    }
                    long j34 = j11;
                    long j35 = j6;
                    j16 += j7;
                    j11 = j34;
                }
                j8 = j11;
                j10 = j6;
                j9 = j7;
                if (j % j10 == j9) {
                    return;
                }
            } else {
                j8 = j11;
                j10 = 2;
                j9 = 1;
            }
            for (long j36 = 0; j36 < j2; j36 += j9) {
                long j37 = j36 * j;
                long j38 = ((j4 + j) - j9) + j37;
                long j39 = j3 + (j37 * j10) + j;
                floatLargeArray4.setFloat(j38, floatLargeArray3.getFloat(j39 - j9) * 2.0f);
                floatLargeArray4.setFloat(j38 + j8, floatLargeArray3.getFloat(j39) * -2.0f);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void radf3(int i, int i2, float[] fArr, int i3, float[] fArr2, int i4, int i5) {
        char c2;
        char c3;
        int i6 = i;
        int i7 = i2;
        int i8 = i5 + i6;
        int i9 = i7 * i6;
        int i10 = 0;
        int i11 = 0;
        while (true) {
            c2 = 0;
            c3 = 46039;
            if (i11 >= i7) {
                break;
            }
            int i12 = i11 * i6;
            int i13 = ((i11 * 3) + 1) * i6;
            int i14 = i3 + i12;
            int i15 = i14 + i9;
            int i16 = (i9 * 2) + i14;
            float f = fArr[i14];
            float f2 = fArr[i15];
            float f3 = fArr[i16];
            float f4 = f2 + f3;
            fArr2[i4 + (i12 * 3)] = f + f4;
            fArr2[i4 + i13 + i6] = (f3 - f2) * 0.8660254f;
            fArr2[((i4 + i6) - 1) + i13] = f + (f4 * -0.5f);
            i11++;
        }
        if (i6 != 1) {
            while (i10 < i7) {
                int i17 = i10 * i6;
                int i18 = i17 * 3;
                int i19 = i17 + i9;
                int i20 = i19 + i9;
                int i21 = i18 + i6;
                int i22 = i21 + i6;
                int i23 = 2;
                while (i23 < i6) {
                    int i24 = i23 - 1;
                    int i25 = i24 + i5;
                    int i26 = i24 + i8;
                    float[] fArr3 = this.wtable_r;
                    float f5 = fArr3[i25 - 1];
                    float f6 = fArr3[i25];
                    float f7 = fArr3[i26 - 1];
                    float f8 = fArr3[i26];
                    int i27 = i3 + i23;
                    int i28 = i4 + i23;
                    int i29 = i27 + i17;
                    int i30 = i27 + i19;
                    int i31 = i27 + i20;
                    float f9 = fArr[i29 - 1];
                    float f10 = fArr[i29];
                    float f11 = fArr[i30 - 1];
                    float f12 = fArr[i30];
                    float f13 = fArr[i31 - 1];
                    float f14 = fArr[i31];
                    float f15 = (f5 * f11) + (f6 * f12);
                    float f16 = (f5 * f12) - (f6 * f11);
                    float f17 = (f7 * f13) + (f8 * f14);
                    float f18 = (f7 * f14) - (f8 * f13);
                    float f19 = f15 + f17;
                    float f20 = f16 + f18;
                    float f21 = f9 + (f19 * -0.5f);
                    float f22 = f10 + (f20 * -0.5f);
                    float f23 = (f16 - f18) * 0.8660254f;
                    float f24 = (f17 - f15) * 0.8660254f;
                    int i32 = i28 + i18;
                    int i33 = i4 + (i6 - i23) + i21;
                    int i34 = i28 + i22;
                    fArr2[i32 - 1] = f9 + f19;
                    fArr2[i32] = f10 + f20;
                    fArr2[i33 - 1] = f21 - f23;
                    fArr2[i33] = f24 - f22;
                    fArr2[i34 - 1] = f21 + f23;
                    fArr2[i34] = f22 + f24;
                    i23 += 2;
                    c3 = 46039;
                    c2 = 0;
                }
                char c4 = c2;
                char c5 = c3;
                i10++;
                c3 = c5;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void radf3(long j, long j2, FloatLargeArray floatLargeArray, long j3, FloatLargeArray floatLargeArray2, long j4, long j5) {
        FloatFFT_1D floatFFT_1D = this;
        FloatLargeArray floatLargeArray3 = floatLargeArray;
        FloatLargeArray floatLargeArray4 = floatLargeArray2;
        long j6 = j5 + j;
        long j7 = j2 * j;
        for (long j8 = 0; j8 < j2; j8++) {
            long j9 = j8 * j;
            long j10 = ((j8 * 3) + 1) * j;
            long j11 = j3 + j9;
            long j12 = j11 + j7;
            long j13 = j11 + (2 * j7);
            float f = floatLargeArray3.getFloat(j11);
            float f2 = floatLargeArray3.getFloat(j12);
            float f3 = floatLargeArray3.getFloat(j13);
            float f4 = f2 + f3;
            floatLargeArray4.setFloat(j4 + (j9 * 3), f + f4);
            floatLargeArray4.setFloat(j4 + j10 + j, (f3 - f2) * 0.8660254f);
            floatLargeArray4.setFloat(((j4 + j) - 1) + j10, f + (f4 * -0.5f));
        }
        if (j != 1) {
            long j14 = 0;
            while (j14 < j2) {
                long j15 = j14 * j;
                long j16 = j15 * 3;
                long j17 = j15 + j7;
                long j18 = j17 + j7;
                long j19 = j16 + j;
                long j20 = j19 + j;
                long j21 = 2;
                while (j21 < j) {
                    long j22 = j21 - 1;
                    long j23 = j22 + j5;
                    long j24 = j7;
                    long j25 = j22 + j6;
                    long j26 = j6;
                    float f5 = floatFFT_1D.wtable_rl.getFloat(j23 - 1);
                    float f6 = floatFFT_1D.wtable_rl.getFloat(j23);
                    float f7 = floatFFT_1D.wtable_rl.getFloat(j25 - 1);
                    float f8 = floatFFT_1D.wtable_rl.getFloat(j25);
                    long j27 = j3 + j21;
                    long j28 = j4 + j21;
                    long j29 = j14;
                    long j30 = j27 + j15;
                    long j31 = j15;
                    long j32 = j27 + j17;
                    long j33 = j27 + j18;
                    long j34 = j16;
                    float f9 = floatLargeArray3.getFloat(j30 - 1);
                    float f10 = floatLargeArray3.getFloat(j30);
                    float f11 = floatLargeArray3.getFloat(j32 - 1);
                    float f12 = floatLargeArray3.getFloat(j32);
                    float f13 = floatLargeArray3.getFloat(j33 - 1);
                    float f14 = floatLargeArray3.getFloat(j33);
                    float f15 = (f5 * f11) + (f6 * f12);
                    float f16 = (f5 * f12) - (f6 * f11);
                    float f17 = (f7 * f13) + (f8 * f14);
                    float f18 = (f7 * f14) - (f8 * f13);
                    float f19 = f15 + f17;
                    float f20 = f16 + f18;
                    float f21 = (f16 - f18) * 0.8660254f;
                    float f22 = (f17 - f15) * 0.8660254f;
                    long j35 = j28 + j34;
                    float f23 = (f20 * -0.5f) + f10;
                    long j36 = j4 + (j - j21) + j19;
                    float f24 = (f19 * -0.5f) + f9;
                    long j37 = j28 + j20;
                    floatLargeArray4.setFloat(j35 - 1, f9 + f19);
                    floatLargeArray4.setFloat(j35, f10 + f20);
                    floatLargeArray4.setFloat(j36 - 1, f24 - f21);
                    floatLargeArray4.setFloat(j36, f22 - f23);
                    floatLargeArray4.setFloat(j37 - 1, f24 + f21);
                    floatLargeArray4.setFloat(j37, f23 + f22);
                    j21 += 2;
                    floatFFT_1D = this;
                    floatLargeArray3 = floatLargeArray;
                    j6 = j26;
                    j7 = j24;
                    j14 = j29;
                    j15 = j31;
                    j16 = j34;
                }
                long j38 = j6;
                long j39 = j7;
                j14++;
                floatFFT_1D = this;
                floatLargeArray3 = floatLargeArray;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void radb3(int i, int i2, float[] fArr, int i3, float[] fArr2, int i4, int i5) {
        float f;
        int i6 = i;
        int i7 = i2;
        int i8 = i5 + i6;
        int i9 = 0;
        int i10 = 0;
        while (true) {
            f = -0.5f;
            if (i10 >= i7) {
                break;
            }
            int i11 = i10 * i6;
            int i12 = i3 + (i11 * 3);
            int i13 = (i6 * 2) + i12;
            float f2 = fArr[i12];
            float f3 = fArr[i13 - 1] * 2.0f;
            float f4 = (-0.5f * f3) + f2;
            float f5 = fArr[i13] * 1.7320508f;
            fArr2[i4 + i11] = f2 + f3;
            fArr2[i4 + ((i10 + i7) * i6)] = f4 - f5;
            fArr2[i4 + (((i7 * 2) + i10) * i6)] = f4 + f5;
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
                    float f6 = fArr[i24 - 1];
                    float f7 = fArr[i24];
                    float f8 = fArr[i25 - 1];
                    float f9 = fArr[i25];
                    float f10 = fArr[i26 - 1];
                    float f11 = fArr[i26];
                    float f12 = f8 + f10;
                    float f13 = f6 + (f12 * f);
                    float f14 = f9 - f11;
                    float f15 = f7 + (f14 * f);
                    float f16 = (f8 - f10) * 0.8660254f;
                    float f17 = (f9 + f11) * 0.8660254f;
                    float f18 = f13 - f17;
                    float f19 = f13 + f17;
                    float f20 = f15 + f16;
                    float f21 = f15 - f16;
                    int i27 = i21 - 1;
                    int i28 = i27 + i5;
                    int i29 = i27 + i8;
                    float[] fArr3 = this.wtable_r;
                    float f22 = fArr3[i28 - 1];
                    float f23 = fArr3[i28];
                    float f24 = fArr3[i29 - 1];
                    float f25 = fArr3[i29];
                    int i30 = i23 + i15;
                    int i31 = i23 + i19;
                    int i32 = i23 + i20;
                    fArr2[i30 - 1] = f6 + f12;
                    fArr2[i30] = f7 + f14;
                    fArr2[i31 - 1] = (f22 * f18) - (f23 * f20);
                    fArr2[i31] = (f22 * f20) + (f23 * f18);
                    fArr2[i32 - 1] = (f24 * f19) - (f25 * f21);
                    fArr2[i32] = (f24 * f21) + (f25 * f19);
                    i21 += 2;
                    i6 = i;
                    f = -0.5f;
                }
                i9++;
                i6 = i;
                f = -0.5f;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void radb3(long j, long j2, FloatLargeArray floatLargeArray, long j3, FloatLargeArray floatLargeArray2, long j4, long j5) {
        long j6;
        FloatLargeArray floatLargeArray3 = floatLargeArray;
        FloatLargeArray floatLargeArray4 = floatLargeArray2;
        long j7 = j5 + j;
        long j8 = 0;
        while (true) {
            j6 = 3;
            if (j8 >= j2) {
                break;
            }
            long j9 = j8 * j;
            long j10 = j3 + (3 * j9);
            long j11 = j10 + (j * 2);
            float f = floatLargeArray3.getFloat(j10);
            float f2 = floatLargeArray3.getFloat(j11 - 1) * 2.0f;
            float f3 = (-0.5f * f2) + f;
            float f4 = floatLargeArray3.getFloat(j11) * 1.7320508f;
            floatLargeArray4.setFloat(j4 + j9, f + f2);
            floatLargeArray4.setFloat(j4 + ((j8 + j2) * j), f3 - f4);
            floatLargeArray4.setFloat(j4 + (((2 * j2) + j8) * j), f3 + f4);
            j8++;
        }
        if (j != 1) {
            long j12 = j2 * j;
            long j13 = 0;
            while (j13 < j2) {
                long j14 = j13 * j;
                long j15 = j14 * j6;
                long j16 = j15 + j;
                long j17 = j16 + j;
                long j18 = j14 + j12;
                long j19 = j18 + j12;
                long j20 = 2;
                while (j20 < j) {
                    long j21 = j3 + j20;
                    long j22 = j4 + j20;
                    long j23 = j21 + j15;
                    long j24 = j15;
                    long j25 = j21 + j17;
                    long j26 = j12;
                    long j27 = j3 + (j - j20) + j16;
                    long j28 = j14;
                    float f5 = floatLargeArray3.getFloat(j23 - 1);
                    float f6 = floatLargeArray3.getFloat(j23);
                    float f7 = floatLargeArray3.getFloat(j25 - 1);
                    float f8 = floatLargeArray3.getFloat(j25);
                    float f9 = floatLargeArray3.getFloat(j27 - 1);
                    float f10 = floatLargeArray3.getFloat(j27);
                    float f11 = f7 + f9;
                    float f12 = (f11 * -0.5f) + f5;
                    float f13 = f8 - f10;
                    float f14 = f6 + (f13 * -0.5f);
                    float f15 = (f7 - f9) * 0.8660254f;
                    float f16 = (f8 + f10) * 0.8660254f;
                    float f17 = f12 - f16;
                    float f18 = f12 + f16;
                    float f19 = f14 + f15;
                    float f20 = f14 - f15;
                    long j29 = j20 - 1;
                    long j30 = j29 + j5;
                    float f21 = f18;
                    long j31 = j29 + j7;
                    long j32 = j7;
                    float f22 = this.wtable_rl.getFloat(j30 - 1);
                    float f23 = this.wtable_rl.getFloat(j30);
                    float f24 = this.wtable_rl.getFloat(j31 - 1);
                    float f25 = this.wtable_rl.getFloat(j31);
                    long j33 = j22 + j28;
                    float f26 = f25;
                    long j34 = j22 + j18;
                    float f27 = f23;
                    float f28 = f24;
                    long j35 = j22 + j19;
                    floatLargeArray4.setFloat(j33 - 1, f5 + f11);
                    floatLargeArray4.setFloat(j33, f6 + f13);
                    floatLargeArray4.setFloat(j34 - 1, (f22 * f17) - (f27 * f19));
                    floatLargeArray4.setFloat(j34, (f22 * f19) + (f27 * f17));
                    floatLargeArray4.setFloat(j35 - 1, (f28 * f21) - (f26 * f20));
                    floatLargeArray4.setFloat(j35, (f28 * f20) + (f26 * f21));
                    j20 += 2;
                    floatLargeArray3 = floatLargeArray;
                    j14 = j28;
                    j12 = j26;
                    j15 = j24;
                    j7 = j32;
                }
                long j36 = j12;
                j13++;
                floatLargeArray3 = floatLargeArray;
                j7 = j7;
                j6 = 3;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void radf4(int i, int i2, float[] fArr, int i3, float[] fArr2, int i4, int i5) {
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
            int i16 = i15 + i10;
            float f = fArr[i3 + i12];
            float f2 = fArr[i3 + i14];
            float f3 = fArr[i3 + i15];
            float f4 = fArr[i3 + i16];
            float f5 = f2 + f4;
            float f6 = f + f3;
            int i17 = i4 + i13 + i6 + i6;
            fArr2[i4 + i13] = f5 + f6;
            int i18 = i17 - 1;
            fArr2[i18 + i6 + i6] = f6 - f5;
            fArr2[i18] = f - f3;
            fArr2[i17] = f4 - f2;
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
                        float[] fArr3 = this.wtable_r;
                        float f7 = fArr3[i31 - 1];
                        float f8 = fArr3[i31];
                        float f9 = fArr3[i32 - 1];
                        float f10 = fArr3[i32];
                        float f11 = fArr3[i33 - 1];
                        float f12 = fArr3[i33];
                        int i34 = i3 + i29;
                        int i35 = i4 + i29;
                        int i36 = i4 + (i6 - i29);
                        int i37 = i34 + i21;
                        int i38 = i34 + i22;
                        int i39 = i34 + i23;
                        int i40 = i34 + i24;
                        float f13 = fArr[i37 - 1];
                        float f14 = fArr[i37];
                        float f15 = fArr[i38 - 1];
                        float f16 = fArr[i38];
                        float f17 = fArr[i39 - 1];
                        float f18 = fArr[i39];
                        float f19 = fArr[i40 - 1];
                        float f20 = fArr[i40];
                        float f21 = (f7 * f15) + (f8 * f16);
                        float f22 = (f7 * f16) - (f8 * f15);
                        float f23 = (f9 * f17) + (f10 * f18);
                        float f24 = (f9 * f18) - (f10 * f17);
                        float f25 = (f11 * f19) + (f12 * f20);
                        float f26 = (f11 * f20) - (f12 * f19);
                        float f27 = f21 + f25;
                        float f28 = f25 - f21;
                        float f29 = f22 + f26;
                        float f30 = f22 - f26;
                        float f31 = f14 + f24;
                        float f32 = f14 - f24;
                        float f33 = f13 + f23;
                        float f34 = f13 - f23;
                        int i41 = i35 + i25;
                        int i42 = i36 + i26;
                        int i43 = i35 + i27;
                        int i44 = i36 + i28;
                        fArr2[i41 - 1] = f27 + f33;
                        fArr2[i44 - 1] = f33 - f27;
                        fArr2[i41] = f29 + f31;
                        fArr2[i44] = f29 - f31;
                        fArr2[i43 - 1] = f30 + f34;
                        fArr2[i42 - 1] = f34 - f30;
                        fArr2[i43] = f28 + f32;
                        fArr2[i42] = f28 - f32;
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
                int i50 = i49 + i10;
                int i51 = i47 + i6;
                int i52 = i51 + i6;
                int i53 = (i3 + i6) - 1;
                float f35 = fArr[i46 + i53];
                float f36 = fArr[i48 + i53];
                float f37 = fArr[i49 + i53];
                float f38 = fArr[i53 + i50];
                float f39 = (f36 + f38) * -0.70710677f;
                float f40 = (f36 - f38) * 0.70710677f;
                int i54 = (i4 + i6) - 1;
                fArr2[i47 + i54] = f40 + f35;
                fArr2[i54 + i52] = f35 - f40;
                fArr2[i4 + i51] = f39 - f37;
                fArr2[i4 + i52 + i6] = f39 + f37;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void radf4(long j, long j2, FloatLargeArray floatLargeArray, long j3, FloatLargeArray floatLargeArray2, long j4, long j5) {
        long j6;
        FloatLargeArray floatLargeArray3;
        FloatLargeArray floatLargeArray4 = floatLargeArray;
        FloatLargeArray floatLargeArray5 = floatLargeArray2;
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
            float f = floatLargeArray4.getFloat(j3 + j11);
            long j16 = j8;
            float f2 = floatLargeArray4.getFloat(j3 + j13);
            long j17 = j7;
            float f3 = floatLargeArray4.getFloat(j3 + j14);
            long j18 = j9;
            float f4 = floatLargeArray4.getFloat(j3 + j15);
            float f5 = f2 + f4;
            float f6 = f + f3;
            long j19 = j4 + j12 + j + j;
            floatLargeArray5.setFloat(j4 + j12, f5 + f6);
            long j20 = j19 - 1;
            floatLargeArray5.setFloat(j20 + j + j, f6 - f5);
            floatLargeArray5.setFloat(j20, f - f3);
            floatLargeArray5.setFloat(j19, f4 - f2);
            j10++;
            floatLargeArray4 = floatLargeArray;
            j8 = j16;
            j7 = j17;
            j9 = j18;
        }
        long j21 = j7;
        long j22 = j8;
        long j23 = j9;
        long j24 = 2;
        int i = (j > 2 ? 1 : (j == 2 ? 0 : -1));
        if (i >= 0) {
            if (i != 0) {
                long j25 = 0;
                while (j25 < j2) {
                    long j26 = j25 * j;
                    long j27 = j26 + j23;
                    long j28 = j27 + j23;
                    long j29 = j28 + j23;
                    long j30 = j26 * j6;
                    long j31 = j30 + j;
                    long j32 = j31 + j;
                    long j33 = j32 + j;
                    long j34 = j24;
                    while (j34 < j) {
                        long j35 = j34 - 1;
                        long j36 = j35 + j5;
                        long j37 = j35 + j21;
                        long j38 = j25;
                        long j39 = j35 + j22;
                        long j40 = j29;
                        float f7 = this.wtable_rl.getFloat(j36 - 1);
                        float f8 = this.wtable_rl.getFloat(j36);
                        float f9 = this.wtable_rl.getFloat(j37 - 1);
                        float f10 = this.wtable_rl.getFloat(j37);
                        float f11 = this.wtable_rl.getFloat(j39 - 1);
                        long j41 = j3 + j34;
                        long j42 = j4 + j34;
                        long j43 = j4 + (j - j34);
                        float f12 = this.wtable_rl.getFloat(j39);
                        long j44 = j41 + j26;
                        long j45 = j26;
                        long j46 = j41 + j27;
                        long j47 = j27;
                        long j48 = j41 + j28;
                        long j49 = j41 + j40;
                        long j50 = j28;
                        FloatLargeArray floatLargeArray6 = floatLargeArray;
                        float f13 = floatLargeArray6.getFloat(j44 - 1);
                        float f14 = floatLargeArray6.getFloat(j44);
                        float f15 = f13;
                        float f16 = floatLargeArray6.getFloat(j46 - 1);
                        float f17 = floatLargeArray6.getFloat(j46);
                        float f18 = f14;
                        float f19 = f15;
                        float f20 = floatLargeArray6.getFloat(j48 - 1);
                        float f21 = floatLargeArray6.getFloat(j48);
                        float f22 = floatLargeArray6.getFloat(j49 - 1);
                        float f23 = floatLargeArray6.getFloat(j49);
                        float f24 = (f7 * f16) + (f8 * f17);
                        float f25 = (f7 * f17) - (f8 * f16);
                        float f26 = (f9 * f20) + (f10 * f21);
                        float f27 = (f9 * f21) - (f10 * f20);
                        float f28 = (f11 * f22) + (f12 * f23);
                        float f29 = (f11 * f23) - (f12 * f22);
                        float f30 = f24 + f28;
                        float f31 = f28 - f24;
                        float f32 = f25 + f29;
                        float f33 = f25 - f29;
                        float f34 = f18 + f27;
                        float f35 = f18 - f27;
                        float f36 = f19 + f26;
                        float f37 = f19 - f26;
                        long j51 = j42 + j30;
                        long j52 = j42 + j32;
                        long j53 = j43 + j33;
                        long j54 = j43 + j31;
                        float f38 = f31;
                        float f39 = f35;
                        FloatLargeArray floatLargeArray7 = floatLargeArray2;
                        floatLargeArray7.setFloat(j51 - 1, f30 + f36);
                        floatLargeArray7.setFloat(j53 - 1, f36 - f30);
                        floatLargeArray7.setFloat(j51, f32 + f34);
                        floatLargeArray7.setFloat(j53, f32 - f34);
                        floatLargeArray7.setFloat(j52 - 1, f33 + f37);
                        floatLargeArray7.setFloat(j54 - 1, f37 - f33);
                        floatLargeArray7.setFloat(j52, f38 + f39);
                        floatLargeArray7.setFloat(j54, f38 - f39);
                        j24 = 2;
                        j34 += 2;
                        floatLargeArray5 = floatLargeArray7;
                        j29 = j40;
                        j25 = j38;
                        j26 = j45;
                        j27 = j47;
                        j28 = j50;
                    }
                    FloatLargeArray floatLargeArray8 = floatLargeArray5;
                    j25++;
                    j6 = 4;
                }
                floatLargeArray3 = floatLargeArray5;
                if (j % j24 == 1) {
                    return;
                }
            } else {
                floatLargeArray3 = floatLargeArray5;
            }
            for (long j55 = 0; j55 < j2; j55++) {
                long j56 = j55 * j;
                long j57 = j56 * 4;
                long j58 = j56 + j23;
                long j59 = j58 + j23;
                long j60 = j59 + j23;
                long j61 = j57 + j;
                long j62 = j61 + j;
                long j63 = (j3 + j) - 1;
                FloatLargeArray floatLargeArray9 = floatLargeArray;
                float f40 = floatLargeArray9.getFloat(j63 + j56);
                float f41 = floatLargeArray9.getFloat(j63 + j58);
                float f42 = floatLargeArray9.getFloat(j63 + j59);
                float f43 = floatLargeArray9.getFloat(j63 + j60);
                float f44 = (f41 + f43) * -0.70710677f;
                float f45 = (f41 - f43) * 0.70710677f;
                long j64 = (j4 + j) - 1;
                floatLargeArray3.setFloat(j64 + j57, f45 + f40);
                floatLargeArray3.setFloat(j64 + j62, f40 - f45);
                floatLargeArray3.setFloat(j4 + j61, f44 - f42);
                floatLargeArray3.setFloat(j4 + j62 + j, f44 + f42);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void radb4(int i, int i2, float[] fArr, int i3, float[] fArr2, int i4, int i5) {
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
            int i18 = i17 + i6;
            float f = fArr[i3 + i13];
            float f2 = fArr[i3 + i17];
            int i19 = (i3 + i6) - 1;
            float f3 = fArr[i19 + i18];
            float f4 = fArr[i19 + i16];
            float f5 = f - f3;
            float f6 = f + f3;
            float f7 = f4 + f4;
            float f8 = f2 + f2;
            fArr2[i4 + i12] = f6 + f7;
            fArr2[i4 + i14] = f5 - f8;
            fArr2[i4 + i15] = f6 - f7;
            fArr2[i4 + i15 + i10] = f5 + f8;
        }
        int i20 = 2;
        if (i6 >= 2) {
            if (i6 != 2) {
                int i21 = 0;
                while (i21 < i7) {
                    int i22 = i21 * i6;
                    int i23 = i22 + i10;
                    int i24 = i23 + i10;
                    int i25 = i24 + i10;
                    int i26 = i22 * 4;
                    int i27 = i26 + i6;
                    int i28 = i27 + i6;
                    int i29 = i28 + i6;
                    for (int i30 = i20; i30 < i6; i30 += 2) {
                        int i31 = i30 - 1;
                        int i32 = i31 + i5;
                        int i33 = i31 + i8;
                        int i34 = i31 + i9;
                        float[] fArr3 = this.wtable_r;
                        float f9 = fArr3[i32 - 1];
                        float f10 = fArr3[i32];
                        float f11 = fArr3[i33 - 1];
                        float f12 = fArr3[i33];
                        float f13 = fArr3[i34 - 1];
                        float f14 = fArr3[i34];
                        int i35 = i3 + i30;
                        int i36 = i3 + (i6 - i30);
                        int i37 = i4 + i30;
                        int i38 = i35 + i26;
                        int i39 = i36 + i27;
                        int i40 = i35 + i28;
                        int i41 = i36 + i29;
                        float f15 = fArr[i38 - 1];
                        float f16 = fArr[i38];
                        float f17 = fArr[i39 - 1];
                        float f18 = fArr[i39];
                        float f19 = fArr[i40 - 1];
                        float f20 = fArr[i40];
                        float f21 = fArr[i41 - 1];
                        float f22 = fArr[i41];
                        float f23 = f16 + f22;
                        float f24 = f16 - f22;
                        float f25 = f20 - f18;
                        float f26 = f20 + f18;
                        float f27 = f15 - f21;
                        float f28 = f15 + f21;
                        float f29 = f19 - f17;
                        float f30 = f19 + f17;
                        float f31 = f28 - f30;
                        float f32 = f24 - f25;
                        float f33 = f27 - f26;
                        float f34 = f27 + f26;
                        float f35 = f23 + f29;
                        float f36 = f23 - f29;
                        int i42 = i37 + i22;
                        int i43 = i37 + i23;
                        int i44 = i37 + i24;
                        int i45 = i37 + i25;
                        fArr2[i42 - 1] = f28 + f30;
                        fArr2[i42] = f24 + f25;
                        fArr2[i43 - 1] = (f9 * f33) - (f10 * f35);
                        fArr2[i43] = (f9 * f35) + (f10 * f33);
                        fArr2[i44 - 1] = (f11 * f31) - (f12 * f32);
                        fArr2[i44] = (f11 * f32) + (f12 * f31);
                        fArr2[i45 - 1] = (f13 * f34) - (f14 * f36);
                        fArr2[i45] = (f13 * f36) + (f14 * f34);
                    }
                    i21++;
                    i20 = 2;
                }
                if (i6 % 2 == 1) {
                    return;
                }
            }
            for (int i46 = 0; i46 < i7; i46++) {
                int i47 = i46 * i6;
                int i48 = i47 * 4;
                int i49 = i47 + i10;
                int i50 = i49 + i10;
                int i51 = i48 + i6;
                int i52 = i51 + i6;
                int i53 = i52 + i6;
                int i54 = (i3 + i6) - 1;
                float f37 = fArr[i48 + i54];
                float f38 = fArr[i54 + i52];
                float f39 = fArr[i3 + i51];
                float f40 = fArr[i3 + i53];
                float f41 = f39 + f40;
                float f42 = f40 - f39;
                float f43 = f37 - f38;
                float f44 = f37 + f38;
                int i55 = (i4 + i6) - 1;
                fArr2[i47 + i55] = f44 + f44;
                fArr2[i49 + i55] = (f43 - f41) * 1.4142135f;
                fArr2[i50 + i55] = f42 + f42;
                fArr2[i55 + i50 + i10] = (f43 + f41) * -1.4142135f;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void radb4(long j, long j2, FloatLargeArray floatLargeArray, long j3, FloatLargeArray floatLargeArray2, long j4, long j5) {
        long j6;
        long j7;
        FloatFFT_1D floatFFT_1D = this;
        FloatLargeArray floatLargeArray3 = floatLargeArray;
        FloatLargeArray floatLargeArray4 = floatLargeArray2;
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
            float f = floatLargeArray3.getFloat(j3 + j13);
            float f2 = floatLargeArray3.getFloat(j3 + j17);
            long j19 = (j3 + j) - 1;
            float f3 = floatLargeArray3.getFloat(j19 + j18);
            long j20 = j9;
            float f4 = floatLargeArray3.getFloat(j19 + j16);
            float f5 = f - f3;
            float f6 = f + f3;
            float f7 = f4 + f4;
            float f8 = f2 + f2;
            floatLargeArray4.setFloat(j4 + j12, f6 + f7);
            floatLargeArray4.setFloat(j4 + j14, f5 - f8);
            floatLargeArray4.setFloat(j4 + j15, f6 - f7);
            floatLargeArray4.setFloat(j4 + j15 + j10, f5 + f8);
            j11++;
            j9 = j20;
        }
        long j21 = j9;
        long j22 = 2;
        int i = (j > 2 ? 1 : (j == 2 ? 0 : -1));
        if (i >= 0) {
            if (i != 0) {
                long j23 = 0;
                while (j23 < j2) {
                    long j24 = j23 * j;
                    long j25 = j24 + j10;
                    long j26 = j25 + j10;
                    long j27 = j26 + j10;
                    long j28 = j24 * j6;
                    long j29 = j28 + j;
                    long j30 = j29 + j;
                    long j31 = j30 + j;
                    long j32 = j22;
                    while (j32 < j) {
                        long j33 = j32 - 1;
                        long j34 = j33 + j5;
                        long j35 = j33 + j8;
                        long j36 = j8;
                        long j37 = j33 + j21;
                        long j38 = j10;
                        float f9 = floatFFT_1D.wtable_rl.getFloat(j34 - 1);
                        float f10 = floatFFT_1D.wtable_rl.getFloat(j34);
                        float f11 = floatFFT_1D.wtable_rl.getFloat(j35 - 1);
                        float f12 = floatFFT_1D.wtable_rl.getFloat(j35);
                        float f13 = floatFFT_1D.wtable_rl.getFloat(j37 - 1);
                        long j39 = j3 + j32;
                        long j40 = j3 + (j - j32);
                        long j41 = j4 + j32;
                        long j42 = j23;
                        long j43 = j39 + j28;
                        float f14 = floatFFT_1D.wtable_rl.getFloat(j37);
                        long j44 = j40 + j29;
                        long j45 = j39 + j30;
                        float f15 = f12;
                        float f16 = f13;
                        long j46 = j40 + j31;
                        float f17 = f9;
                        float f18 = f10;
                        float f19 = floatLargeArray3.getFloat(j43 - 1);
                        float f20 = floatLargeArray3.getFloat(j43);
                        float f21 = floatLargeArray3.getFloat(j44 - 1);
                        float f22 = floatLargeArray3.getFloat(j44);
                        long j47 = j24;
                        float f23 = floatLargeArray3.getFloat(j45 - 1);
                        float f24 = floatLargeArray3.getFloat(j45);
                        float f25 = floatLargeArray3.getFloat(j46 - 1);
                        float f26 = floatLargeArray3.getFloat(j46);
                        float f27 = f20 + f26;
                        float f28 = f20 - f26;
                        float f29 = f24 - f22;
                        float f30 = f24 + f22;
                        float f31 = f19 - f25;
                        float f32 = f19 + f25;
                        float f33 = f23 - f21;
                        float f34 = f23 + f21;
                        float f35 = f31 - f30;
                        float f36 = f31 + f30;
                        float f37 = f27 + f33;
                        float f38 = f27 - f33;
                        long j48 = j41 + j47;
                        float f39 = f28 - f29;
                        long j49 = j41 + j25;
                        float f40 = f32 - f34;
                        float f41 = f37;
                        long j50 = j41 + j26;
                        long j51 = j41 + j27;
                        floatLargeArray4.setFloat(j48 - 1, f32 + f34);
                        floatLargeArray4.setFloat(j48, f28 + f29);
                        floatLargeArray4.setFloat(j49 - 1, (f17 * f35) - (f18 * f41));
                        floatLargeArray4.setFloat(j49, (f17 * f41) + (f18 * f35));
                        floatLargeArray4.setFloat(j50 - 1, (f11 * f40) - (f15 * f39));
                        floatLargeArray4.setFloat(j50, (f11 * f39) + (f15 * f40));
                        floatLargeArray4.setFloat(j51 - 1, (f16 * f36) - (f14 * f38));
                        floatLargeArray4.setFloat(j51, (f38 * f16) + (f36 * f14));
                        j32 += 2;
                        j22 = 2;
                        j10 = j38;
                        j8 = j36;
                        j23 = j42;
                        j24 = j47;
                        floatFFT_1D = this;
                        floatLargeArray3 = floatLargeArray;
                    }
                    long j52 = j8;
                    long j53 = j22;
                    long j54 = j10;
                    j23++;
                    j6 = 4;
                    floatFFT_1D = this;
                    floatLargeArray3 = floatLargeArray;
                }
                j7 = j10;
                if (j % j22 == 1) {
                    return;
                }
            } else {
                j7 = j10;
            }
            long j55 = 0;
            while (j55 < j2) {
                long j56 = j55 * j;
                long j57 = j56 * 4;
                long j58 = j56 + j7;
                long j59 = j58 + j7;
                long j60 = j57 + j;
                long j61 = j60 + j;
                long j62 = (j3 + j) - 1;
                FloatLargeArray floatLargeArray5 = floatLargeArray;
                float f42 = floatLargeArray5.getFloat(j62 + j57);
                float f43 = floatLargeArray5.getFloat(j62 + j61);
                long j63 = j55;
                float f44 = floatLargeArray5.getFloat(j3 + j60);
                float f45 = floatLargeArray5.getFloat(j3 + j61 + j);
                float f46 = f44 + f45;
                float f47 = f45 - f44;
                float f48 = f42 - f43;
                float f49 = f42 + f43;
                long j64 = (j4 + j) - 1;
                floatLargeArray4.setFloat(j64 + j56, f49 + f49);
                floatLargeArray4.setFloat(j64 + j58, (f48 - f46) * 1.4142135f);
                floatLargeArray4.setFloat(j64 + j59, f47 + f47);
                floatLargeArray4.setFloat(j64 + j59 + j7, (f48 + f46) * -1.4142135f);
                j55 = j63 + 1;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void radf5(int i, int i2, float[] fArr, int i3, float[] fArr2, int i4, int i5) {
        char c2;
        char c3;
        int i6 = i;
        int i7 = i2;
        int i8 = i5 + i6;
        int i9 = i8 + i6;
        int i10 = i9 + i6;
        int i11 = i7 * i6;
        int i12 = 0;
        int i13 = 0;
        while (true) {
            c2 = 30833;
            c3 = 7101;
            if (i13 >= i7) {
                break;
            }
            int i14 = i13 * i6;
            int i15 = i14 * 5;
            int i16 = i15 + i6;
            int i17 = i16 + i6;
            int i18 = i17 + i6;
            int i19 = i14 + i11;
            int i20 = i19 + i11;
            int i21 = i20 + i11;
            int i22 = (i4 + i6) - 1;
            float f = fArr[i3 + i14];
            float f2 = fArr[i3 + i19];
            float f3 = fArr[i3 + i20];
            float f4 = fArr[i3 + i21];
            float f5 = fArr[i3 + i21 + i11];
            float f6 = f5 + f2;
            float f7 = f5 - f2;
            float f8 = f4 + f3;
            float f9 = f4 - f3;
            fArr2[i4 + i15] = f + f6 + f8;
            fArr2[i22 + i16] = (f6 * 0.309017f) + f + (f8 * -0.809017f);
            fArr2[i4 + i17] = (f7 * 0.95105654f) + (f9 * 0.58778524f);
            fArr2[i22 + i18] = f + (f6 * -0.809017f) + (f8 * 0.309017f);
            fArr2[i4 + i18 + i6] = (f7 * 0.58778524f) - (f9 * 0.95105654f);
            i13++;
        }
        if (i6 != 1) {
            while (i12 < i7) {
                int i23 = i12 * i6;
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
                    float[] fArr3 = this.wtable_r;
                    float f10 = fArr3[i35 - 1];
                    float f11 = fArr3[i35];
                    float f12 = fArr3[i36 - 1];
                    float f13 = fArr3[i36];
                    float f14 = fArr3[i37 - 1];
                    float f15 = fArr3[i37];
                    float f16 = fArr3[i38 - 1];
                    float f17 = fArr3[i38];
                    int i39 = i3 + i33;
                    int i40 = i4 + i33;
                    int i41 = i4 + (i6 - i33);
                    int i42 = i39 + i23;
                    int i43 = i39 + i29;
                    int i44 = i39 + i30;
                    int i45 = i39 + i31;
                    int i46 = i39 + i32;
                    float f18 = fArr[i42 - 1];
                    float f19 = fArr[i42];
                    float f20 = fArr[i43 - 1];
                    float f21 = fArr[i43];
                    float f22 = fArr[i44 - 1];
                    float f23 = fArr[i44];
                    float f24 = fArr[i45 - 1];
                    float f25 = fArr[i45];
                    float f26 = fArr[i46 - 1];
                    float f27 = fArr[i46];
                    float f28 = (f10 * f20) + (f11 * f21);
                    float f29 = (f10 * f21) - (f11 * f20);
                    float f30 = (f12 * f22) + (f13 * f23);
                    float f31 = (f12 * f23) - (f13 * f22);
                    float f32 = (f14 * f24) + (f15 * f25);
                    float f33 = (f14 * f25) - (f15 * f24);
                    float f34 = (f16 * f26) + (f17 * f27);
                    float f35 = (f16 * f27) - (f17 * f26);
                    float f36 = f28 + f34;
                    float f37 = f34 - f28;
                    float f38 = f29 - f35;
                    float f39 = f29 + f35;
                    float f40 = f30 + f32;
                    float f41 = f32 - f30;
                    float f42 = f31 - f33;
                    float f43 = f31 + f33;
                    float f44 = f18 + (f36 * 0.309017f) + (f40 * -0.809017f);
                    float f45 = f19 + (f39 * 0.309017f) + (f43 * -0.809017f);
                    float f46 = f18 + (f36 * -0.809017f) + (f40 * 0.309017f);
                    float f47 = f19 + (f39 * -0.809017f) + (f43 * 0.309017f);
                    float f48 = (f38 * 0.95105654f) + (f42 * 0.58778524f);
                    float f49 = (f37 * 0.95105654f) + (f41 * 0.58778524f);
                    float f50 = (f38 * 0.58778524f) - (f42 * 0.95105654f);
                    float f51 = (f37 * 0.58778524f) - (f41 * 0.95105654f);
                    int i47 = i40 + i24;
                    int i48 = i41 + i25;
                    int i49 = i40 + i26;
                    int i50 = i41 + i27;
                    int i51 = i40 + i28;
                    fArr2[i47 - 1] = f18 + f36 + f40;
                    fArr2[i47] = f19 + f39 + f43;
                    fArr2[i49 - 1] = f44 + f48;
                    fArr2[i48 - 1] = f44 - f48;
                    fArr2[i49] = f45 + f49;
                    fArr2[i48] = f49 - f45;
                    fArr2[i51 - 1] = f46 + f50;
                    fArr2[i50 - 1] = f46 - f50;
                    fArr2[i51] = f47 + f51;
                    fArr2[i50] = f51 - f47;
                    i33 += 2;
                    c2 = 30833;
                    c3 = 7101;
                }
                char c4 = c2;
                char c5 = c3;
                i12++;
                c2 = c4;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void radf5(long j, long j2, FloatLargeArray floatLargeArray, long j3, FloatLargeArray floatLargeArray2, long j4, long j5) {
        FloatFFT_1D floatFFT_1D = this;
        FloatLargeArray floatLargeArray3 = floatLargeArray;
        FloatLargeArray floatLargeArray4 = floatLargeArray2;
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
            long j19 = (j4 + j) - 1;
            float f = floatLargeArray3.getFloat(j3 + j11);
            long j20 = j8;
            float f2 = floatLargeArray3.getFloat(j3 + j16);
            long j21 = j7;
            float f3 = floatLargeArray3.getFloat(j3 + j17);
            long j22 = j6;
            float f4 = floatLargeArray3.getFloat(j3 + j18);
            float f5 = floatLargeArray3.getFloat(j3 + j18 + j9);
            float f6 = f5 + f2;
            float f7 = f5 - f2;
            float f8 = f4 + f3;
            float f9 = f4 - f3;
            floatLargeArray4.setFloat(j4 + j12, f + f6 + f8);
            floatLargeArray4.setFloat(j19 + j13, (f6 * 0.309017f) + f + (f8 * -0.809017f));
            floatLargeArray4.setFloat(j4 + j14, (f7 * 0.95105654f) + (f9 * 0.58778524f));
            floatLargeArray4.setFloat(j19 + j15, f + (f6 * -0.809017f) + (f8 * 0.309017f));
            floatLargeArray4.setFloat(j4 + j15 + j, (f7 * 0.58778524f) - (f9 * 0.95105654f));
            j10++;
            j8 = j20;
            j7 = j21;
            j6 = j22;
            j9 = j9;
        }
        long j23 = j6;
        long j24 = j7;
        long j25 = j8;
        long j26 = j9;
        if (j != 1) {
            long j27 = 0;
            while (j27 < j2) {
                long j28 = j27 * j;
                long j29 = j28 * 5;
                long j30 = j29 + j;
                long j31 = j30 + j;
                long j32 = j31 + j;
                long j33 = j32 + j;
                long j34 = j28 + j26;
                long j35 = j34 + j26;
                long j36 = j35 + j26;
                long j37 = j36 + j26;
                long j38 = 2;
                while (j38 < j) {
                    long j39 = j38 - 1;
                    long j40 = j27;
                    long j41 = j39 + j5;
                    long j42 = j32;
                    long j43 = j39 + j23;
                    long j44 = j31;
                    long j45 = j39 + j24;
                    long j46 = j30;
                    long j47 = j39 + j25;
                    long j48 = j29;
                    float f10 = floatFFT_1D.wtable_rl.getFloat(j41 - 1);
                    float f11 = floatFFT_1D.wtable_rl.getFloat(j41);
                    float f12 = floatFFT_1D.wtable_rl.getFloat(j43 - 1);
                    float f13 = floatFFT_1D.wtable_rl.getFloat(j43);
                    float f14 = floatFFT_1D.wtable_rl.getFloat(j45 - 1);
                    float f15 = floatFFT_1D.wtable_rl.getFloat(j45);
                    float f16 = floatFFT_1D.wtable_rl.getFloat(j47 - 1);
                    long j49 = j3 + j38;
                    long j50 = j4 + j38;
                    long j51 = j4 + (j - j38);
                    long j52 = j49 + j28;
                    long j53 = j28;
                    long j54 = j49 + j34;
                    float f17 = floatFFT_1D.wtable_rl.getFloat(j47);
                    long j55 = j49 + j35;
                    float f18 = f15;
                    float f19 = f16;
                    long j56 = j49 + j36;
                    float f20 = f12;
                    float f21 = f13;
                    long j57 = j49 + j37;
                    float f22 = f14;
                    float f23 = f10;
                    float f24 = floatLargeArray3.getFloat(j52 - 1);
                    float f25 = floatLargeArray3.getFloat(j52);
                    float f26 = floatLargeArray3.getFloat(j54 - 1);
                    float f27 = floatLargeArray3.getFloat(j54);
                    float f28 = f24;
                    float f29 = f25;
                    float f30 = floatLargeArray3.getFloat(j55 - 1);
                    float f31 = floatLargeArray3.getFloat(j55);
                    float f32 = floatLargeArray3.getFloat(j56 - 1);
                    float f33 = floatLargeArray3.getFloat(j56);
                    float f34 = floatLargeArray3.getFloat(j57 - 1);
                    float f35 = floatLargeArray3.getFloat(j57);
                    float f36 = (f23 * f26) + (f11 * f27);
                    float f37 = (f27 * f23) - (f11 * f26);
                    float f38 = (f20 * f30) + (f21 * f31);
                    float f39 = (f31 * f20) - (f30 * f21);
                    float f40 = (f22 * f32) + (f18 * f33);
                    float f41 = (f33 * f22) - (f32 * f18);
                    float f42 = (f19 * f34) + (f17 * f35);
                    float f43 = (f35 * f19) - (f34 * f17);
                    float f44 = f36 + f42;
                    float f45 = f42 - f36;
                    float f46 = f37 - f43;
                    float f47 = f37 + f43;
                    float f48 = f38 + f40;
                    float f49 = f40 - f38;
                    float f50 = f39 - f41;
                    float f51 = f39 + f41;
                    float f52 = (f44 * 0.309017f) + f28 + (f48 * -0.809017f);
                    float f53 = (f47 * 0.309017f) + f29 + (f51 * -0.809017f);
                    float f54 = (f44 * -0.809017f) + f28 + (f48 * 0.309017f);
                    float f55 = f29 + (f47 * -0.809017f) + (f51 * 0.309017f);
                    float f56 = (f46 * 0.95105654f) + (f50 * 0.58778524f);
                    float f57 = (f45 * 0.95105654f) + (f49 * 0.58778524f);
                    float f58 = (f46 * 0.58778524f) - (f50 * 0.95105654f);
                    long j58 = j50 + j48;
                    float f59 = f58;
                    float f60 = (f45 * 0.58778524f) - (f49 * 0.95105654f);
                    long j59 = j50 + j44;
                    long j60 = j51 + j46;
                    long j61 = j51 + j42;
                    long j62 = j50 + j33;
                    floatLargeArray4.setFloat(j58 - 1, f28 + f44 + f48);
                    floatLargeArray4.setFloat(j58, f29 + f47 + f51);
                    floatLargeArray4.setFloat(j59 - 1, f52 + f56);
                    floatLargeArray4.setFloat(j60 - 1, f52 - f56);
                    floatLargeArray4.setFloat(j59, f53 + f57);
                    floatLargeArray4.setFloat(j60, f57 - f53);
                    floatLargeArray4.setFloat(j62 - 1, f54 + f59);
                    floatLargeArray4.setFloat(j61 - 1, f54 - f59);
                    floatLargeArray4.setFloat(j62, f55 + f60);
                    floatLargeArray4.setFloat(j61, f60 - f55);
                    j38 += 2;
                    floatFFT_1D = this;
                    floatLargeArray3 = floatLargeArray;
                    j29 = j48;
                    j27 = j40;
                    j32 = j42;
                    j31 = j44;
                    j30 = j46;
                    j28 = j53;
                }
                j27++;
                floatFFT_1D = this;
                floatLargeArray3 = floatLargeArray;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void radb5(int i, int i2, float[] fArr, int i3, float[] fArr2, int i4, int i5) {
        char c2;
        char c3;
        int i6 = i;
        int i7 = i2;
        int i8 = i5 + i6;
        int i9 = i8 + i6;
        int i10 = i9 + i6;
        int i11 = i7 * i6;
        int i12 = 0;
        int i13 = 0;
        while (true) {
            c2 = 30833;
            c3 = 7101;
            if (i13 >= i7) {
                break;
            }
            int i14 = i13 * i6;
            int i15 = i14 * 5;
            int i16 = i15 + i6;
            int i17 = i16 + i6;
            int i18 = i17 + i6;
            int i19 = i14 + i11;
            int i20 = i19 + i11;
            int i21 = i20 + i11;
            int i22 = (i3 + i6) - 1;
            float f = fArr[i3 + i15];
            float f2 = fArr[i3 + i17] * 2.0f;
            float f3 = fArr[i3 + i18 + i6] * 2.0f;
            float f4 = fArr[i22 + i16] * 2.0f;
            float f5 = fArr[i22 + i18] * 2.0f;
            float f6 = f + (f4 * 0.309017f) + (f5 * -0.809017f);
            float f7 = (-0.809017f * f4) + f + (0.309017f * f5);
            float f8 = (f2 * 0.95105654f) + (f3 * 0.58778524f);
            float f9 = (f2 * 0.58778524f) - (f3 * 0.95105654f);
            fArr2[i4 + i14] = f + f4 + f5;
            fArr2[i4 + i19] = f6 - f8;
            fArr2[i4 + i20] = f7 - f9;
            fArr2[i4 + i21] = f7 + f9;
            fArr2[i4 + i21 + i11] = f6 + f8;
            i13++;
        }
        if (i6 != 1) {
            while (i12 < i7) {
                int i23 = i12 * i6;
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
                    float[] fArr3 = this.wtable_r;
                    float f10 = fArr3[i35 - 1];
                    float f11 = fArr3[i35];
                    float f12 = fArr3[i36 - 1];
                    float f13 = fArr3[i36];
                    float f14 = fArr3[i37 - 1];
                    float f15 = fArr3[i37];
                    float f16 = fArr3[i38 - 1];
                    float f17 = fArr3[i38];
                    int i39 = i3 + i33;
                    int i40 = i3 + (i6 - i33);
                    int i41 = i4 + i33;
                    int i42 = i39 + i24;
                    int i43 = i40 + i25;
                    int i44 = i39 + i26;
                    int i45 = i40 + i27;
                    int i46 = i39 + i28;
                    float f18 = fArr[i42 - 1];
                    float f19 = fArr[i42];
                    float f20 = fArr[i43 - 1];
                    float f21 = fArr[i43];
                    float f22 = fArr[i44 - 1];
                    float f23 = fArr[i44];
                    float f24 = fArr[i45 - 1];
                    float f25 = fArr[i45];
                    float f26 = fArr[i46 - 1];
                    float f27 = fArr[i46];
                    float f28 = f23 + f21;
                    float f29 = f23 - f21;
                    float f30 = f27 + f25;
                    float f31 = f27 - f25;
                    float f32 = f22 - f20;
                    float f33 = f22 + f20;
                    float f34 = f26 - f24;
                    float f35 = f26 + f24;
                    float f36 = f18 + (f33 * 0.309017f) + (f35 * -0.809017f);
                    float f37 = f19 + (f29 * 0.309017f) + (f31 * -0.809017f);
                    float f38 = f18 + (f33 * -0.809017f) + (f35 * 0.309017f);
                    float f39 = f19 + (f29 * -0.809017f) + (f31 * 0.309017f);
                    float f40 = (f32 * 0.95105654f) + (f34 * 0.58778524f);
                    float f41 = (f28 * 0.95105654f) + (f30 * 0.58778524f);
                    float f42 = (f32 * 0.58778524f) - (f34 * 0.95105654f);
                    float f43 = (f28 * 0.58778524f) - (f30 * 0.95105654f);
                    float f44 = f38 - f43;
                    float f45 = f38 + f43;
                    float f46 = f39 + f42;
                    float f47 = f39 - f42;
                    float f48 = f36 + f41;
                    float f49 = f36 - f41;
                    float f50 = f37 - f40;
                    float f51 = f37 + f40;
                    int i47 = i41 + i23;
                    int i48 = i41 + i29;
                    int i49 = i41 + i30;
                    int i50 = i41 + i31;
                    int i51 = i41 + i32;
                    fArr2[i47 - 1] = f18 + f33 + f35;
                    fArr2[i47] = f19 + f29 + f31;
                    fArr2[i48 - 1] = (f10 * f49) - (f11 * f51);
                    fArr2[i48] = (f10 * f51) + (f11 * f49);
                    fArr2[i49 - 1] = (f12 * f44) - (f13 * f46);
                    fArr2[i49] = (f12 * f46) + (f13 * f44);
                    fArr2[i50 - 1] = (f14 * f45) - (f15 * f47);
                    fArr2[i50] = (f14 * f47) + (f15 * f45);
                    fArr2[i51 - 1] = (f16 * f48) - (f17 * f50);
                    fArr2[i51] = (f16 * f50) + (f17 * f48);
                    i33 += 2;
                    c2 = 30833;
                    c3 = 7101;
                }
                char c4 = c2;
                char c5 = c3;
                i12++;
                c2 = c4;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void radb5(long j, long j2, FloatLargeArray floatLargeArray, long j3, FloatLargeArray floatLargeArray2, long j4, long j5) {
        FloatFFT_1D floatFFT_1D = this;
        FloatLargeArray floatLargeArray3 = floatLargeArray;
        FloatLargeArray floatLargeArray4 = floatLargeArray2;
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
            long j19 = (j3 + j) - 1;
            float f = floatLargeArray3.getFloat(j3 + j12);
            long j20 = j8;
            float f2 = floatLargeArray3.getFloat(j3 + j14) * 2.0f;
            long j21 = j7;
            float f3 = floatLargeArray3.getFloat(j3 + j15 + j) * 2.0f;
            long j22 = j6;
            float f4 = floatLargeArray3.getFloat(j19 + j13) * 2.0f;
            float f5 = floatLargeArray3.getFloat(j19 + j15) * 2.0f;
            float f6 = (f4 * 0.309017f) + f + (f5 * -0.809017f);
            float f7 = f + (-0.809017f * f4) + (0.309017f * f5);
            float f8 = (f2 * 0.95105654f) + (f3 * 0.58778524f);
            float f9 = (f2 * 0.58778524f) - (f3 * 0.95105654f);
            floatLargeArray4.setFloat(j4 + j11, f + f4 + f5);
            floatLargeArray4.setFloat(j4 + j16, f6 - f8);
            floatLargeArray4.setFloat(j4 + j17, f7 - f9);
            floatLargeArray4.setFloat(j4 + j18, f7 + f9);
            floatLargeArray4.setFloat(j4 + j18 + j9, f6 + f8);
            j10++;
            j9 = j9;
            j7 = j21;
            j6 = j22;
            j8 = j20;
        }
        long j23 = j6;
        long j24 = j7;
        long j25 = j8;
        long j26 = j9;
        if (j != 1) {
            long j27 = 0;
            while (j27 < j2) {
                long j28 = j27 * j;
                long j29 = j28 * 5;
                long j30 = j29 + j;
                long j31 = j30 + j;
                long j32 = j31 + j;
                long j33 = j32 + j;
                long j34 = j28 + j26;
                long j35 = j34 + j26;
                long j36 = j35 + j26;
                long j37 = j36 + j26;
                long j38 = 2;
                while (j38 < j) {
                    long j39 = j38 - 1;
                    long j40 = j27;
                    long j41 = j39 + j5;
                    long j42 = j28;
                    long j43 = j39 + j23;
                    long j44 = j32;
                    long j45 = j39 + j24;
                    long j46 = j31;
                    long j47 = j39 + j25;
                    long j48 = j30;
                    float f10 = floatFFT_1D.wtable_rl.getFloat(j41 - 1);
                    float f11 = floatFFT_1D.wtable_rl.getFloat(j41);
                    float f12 = floatFFT_1D.wtable_rl.getFloat(j43 - 1);
                    float f13 = floatFFT_1D.wtable_rl.getFloat(j43);
                    float f14 = floatFFT_1D.wtable_rl.getFloat(j45 - 1);
                    float f15 = floatFFT_1D.wtable_rl.getFloat(j45);
                    float f16 = floatFFT_1D.wtable_rl.getFloat(j47 - 1);
                    long j49 = j3 + j38;
                    long j50 = j3 + (j - j38);
                    long j51 = j4 + j38;
                    float f17 = floatFFT_1D.wtable_rl.getFloat(j47);
                    long j52 = j49 + j29;
                    long j53 = j29;
                    long j54 = j50 + j48;
                    float f18 = f15;
                    float f19 = f16;
                    long j55 = j49 + j46;
                    float f20 = f13;
                    float f21 = f14;
                    long j56 = j50 + j44;
                    long j57 = j49 + j33;
                    float f22 = f11;
                    float f23 = f12;
                    float f24 = floatLargeArray3.getFloat(j52 - 1);
                    float f25 = floatLargeArray3.getFloat(j52);
                    float f26 = floatLargeArray3.getFloat(j54 - 1);
                    float f27 = floatLargeArray3.getFloat(j54);
                    float f28 = f24;
                    float f29 = floatLargeArray3.getFloat(j55 - 1);
                    float f30 = floatLargeArray3.getFloat(j55);
                    float f31 = floatLargeArray3.getFloat(j56 - 1);
                    float f32 = floatLargeArray3.getFloat(j56);
                    float f33 = f10;
                    float f34 = floatLargeArray3.getFloat(j57 - 1);
                    float f35 = floatLargeArray3.getFloat(j57);
                    float f36 = f30 + f27;
                    float f37 = f30 - f27;
                    float f38 = f35 + f32;
                    float f39 = f35 - f32;
                    float f40 = f29 - f26;
                    float f41 = f29 + f26;
                    float f42 = f34 - f31;
                    float f43 = f34 + f31;
                    float f44 = (f41 * 0.309017f) + f28 + (f43 * -0.809017f);
                    float f45 = (f37 * 0.309017f) + f25 + (f39 * -0.809017f);
                    float f46 = f28 + (f41 * -0.809017f) + (f43 * 0.309017f);
                    float f47 = f25 + (f37 * -0.809017f) + (f39 * 0.309017f);
                    float f48 = (f40 * 0.95105654f) + (f42 * 0.58778524f);
                    float f49 = (f36 * 0.95105654f) + (f38 * 0.58778524f);
                    float f50 = (f40 * 0.58778524f) - (f42 * 0.95105654f);
                    float f51 = (f36 * 0.58778524f) - (f38 * 0.95105654f);
                    float f52 = f46 - f51;
                    float f53 = f46 + f51;
                    float f54 = f47 + f50;
                    float f55 = f47 - f50;
                    float f56 = f44 + f49;
                    float f57 = f44 - f49;
                    float f58 = f45 - f48;
                    float f59 = f45 + f48;
                    long j58 = j51 + j42;
                    float f60 = f58;
                    float f61 = f59;
                    long j59 = j51 + j34;
                    float f62 = f57;
                    float f63 = f33;
                    long j60 = j51 + j35;
                    long j61 = j51 + j36;
                    long j62 = j51 + j37;
                    float f64 = f41 + f28 + f43;
                    FloatLargeArray floatLargeArray5 = floatLargeArray2;
                    floatLargeArray5.setFloat(j58 - 1, f64);
                    floatLargeArray5.setFloat(j58, f25 + f37 + f39);
                    floatLargeArray5.setFloat(j59 - 1, (f63 * f62) - (f22 * f61));
                    floatLargeArray5.setFloat(j59, (f63 * f61) + (f22 * f62));
                    floatLargeArray5.setFloat(j60 - 1, (f23 * f52) - (f20 * f54));
                    floatLargeArray5.setFloat(j60, (f23 * f54) + (f20 * f52));
                    floatLargeArray5.setFloat(j61 - 1, (f21 * f53) - (f18 * f55));
                    floatLargeArray5.setFloat(j61, (f21 * f55) + (f18 * f53));
                    floatLargeArray5.setFloat(j62 - 1, (f19 * f56) - (f17 * f60));
                    floatLargeArray5.setFloat(j62, (f19 * f60) + (f17 * f56));
                    j38 += 2;
                    floatFFT_1D = this;
                    floatLargeArray3 = floatLargeArray;
                    floatLargeArray4 = floatLargeArray5;
                    j30 = j48;
                    j27 = j40;
                    j28 = j42;
                    j32 = j44;
                    j31 = j46;
                    j29 = j53;
                }
                FloatLargeArray floatLargeArray6 = floatLargeArray4;
                j27++;
                floatFFT_1D = this;
                floatLargeArray3 = floatLargeArray;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void radfg(int i, int i2, int i3, int i4, float[] fArr, int i5, float[] fArr2, int i6, int i7) {
        char c2;
        float f;
        int i8;
        int i9 = i;
        int i10 = i2;
        int i11 = i3;
        int i12 = i4;
        float[] fArr3 = fArr;
        int i13 = i5;
        float[] fArr4 = fArr2;
        int i14 = i6;
        double d = (double) (TWO_PI / ((float) i10));
        float cos = (float) FastMath.cos(d);
        float sin = (float) FastMath.sin(d);
        char c3 = 2;
        int i15 = (i10 + 1) / 2;
        int i16 = (i9 - 1) / 2;
        if (i9 != 1) {
            for (int i17 = 0; i17 < i12; i17++) {
                fArr4[i14 + i17] = fArr3[i13 + i17];
            }
            for (int i18 = 1; i18 < i10; i18++) {
                int i19 = i18 * i11 * i9;
                for (int i20 = 0; i20 < i11; i20++) {
                    int i21 = (i20 * i9) + i19;
                    fArr4[i14 + i21] = fArr3[i13 + i21];
                }
            }
            if (i16 <= i11) {
                int i22 = -i9;
                int i23 = 1;
                while (i23 < i10) {
                    int i24 = i22 + i9;
                    int i25 = i24 - 1;
                    int i26 = i23 * i11 * i9;
                    int i27 = i24;
                    int i28 = c3;
                    while (i28 < i9) {
                        i25 += 2;
                        int i29 = i25 + i7;
                        int i30 = i13 + i28;
                        int i31 = i14 + i28;
                        float[] fArr5 = this.wtable_r;
                        float f2 = fArr5[i29 - 1];
                        float f3 = fArr5[i29];
                        float f4 = sin;
                        for (int i32 = 0; i32 < i11; i32++) {
                            int i33 = (i32 * i9) + i26;
                            int i34 = i31 + i33;
                            int i35 = i30 + i33;
                            float f5 = fArr3[i35 - 1];
                            float f6 = fArr3[i35];
                            fArr4[i34 - 1] = (f2 * f5) + (f3 * f6);
                            fArr4[i34] = (f6 * f2) - (f5 * f3);
                        }
                        i28 += 2;
                        sin = f4;
                    }
                    float f7 = sin;
                    i23++;
                    i22 = i27;
                    c3 = 2;
                }
                f = sin;
                c2 = c3;
            } else {
                f = sin;
                int i36 = -i9;
                for (int i37 = 1; i37 < i10; i37++) {
                    i36 += i9;
                    int i38 = i37 * i11 * i9;
                    int i39 = 0;
                    while (i39 < i11) {
                        int i40 = i36 - 1;
                        int i41 = (i39 * i9) + i38;
                        int i42 = i36;
                        int i43 = 2;
                        while (i43 < i9) {
                            i40 += 2;
                            int i44 = i40 + i7;
                            int i45 = i38;
                            float[] fArr6 = this.wtable_r;
                            float f8 = fArr6[i44 - 1];
                            float f9 = fArr6[i44];
                            int i46 = i14 + i43 + i41;
                            int i47 = i13 + i43 + i41;
                            float f10 = fArr3[i47 - 1];
                            float f11 = fArr3[i47];
                            fArr4[i46 - 1] = (f8 * f10) + (f9 * f11);
                            fArr4[i46] = (f8 * f11) - (f9 * f10);
                            i43 += 2;
                            i38 = i45;
                        }
                        int i48 = i38;
                        i39++;
                        i36 = i42;
                    }
                    int i49 = i36;
                }
                c2 = 2;
            }
            if (i16 >= i11) {
                int i50 = 1;
                while (i50 < i15) {
                    int i51 = i50 * i11 * i9;
                    int i52 = (i10 - i50) * i11 * i9;
                    int i53 = 0;
                    while (i53 < i11) {
                        int i54 = i53 * i9;
                        int i55 = i54 + i51;
                        int i56 = i54 + i52;
                        for (int i57 = c2; i57 < i9; i57 += 2) {
                            int i58 = i13 + i57;
                            int i59 = i14 + i57;
                            int i60 = i58 + i55;
                            int i61 = i58 + i56;
                            int i62 = i59 + i55;
                            int i63 = i59 + i56;
                            float f12 = fArr4[i62 - 1];
                            float f13 = fArr4[i62];
                            float f14 = fArr4[i63 - 1];
                            float f15 = fArr4[i63];
                            fArr3[i60 - 1] = f12 + f14;
                            fArr3[i60] = f13 + f15;
                            fArr3[i61 - 1] = f13 - f15;
                            fArr3[i61] = f14 - f12;
                        }
                        i53++;
                    }
                    i50++;
                }
            } else {
                for (int i64 = 1; i64 < i15; i64++) {
                    int i65 = i64 * i11 * i9;
                    int i66 = (i10 - i64) * i11 * i9;
                    int i67 = c2;
                    while (i67 < i9) {
                        int i68 = i13 + i67;
                        int i69 = i14 + i67;
                        int i70 = i16;
                        for (int i71 = 0; i71 < i11; i71++) {
                            int i72 = i71 * i9;
                            int i73 = i72 + i65;
                            int i74 = i72 + i66;
                            int i75 = i68 + i73;
                            int i76 = i68 + i74;
                            int i77 = i69 + i73;
                            int i78 = i69 + i74;
                            float f16 = fArr4[i77 - 1];
                            float f17 = fArr4[i77];
                            float f18 = fArr4[i78 - 1];
                            float f19 = fArr4[i78];
                            fArr3[i75 - 1] = f16 + f18;
                            fArr3[i75] = f17 + f19;
                            fArr3[i76 - 1] = f17 - f19;
                            fArr3[i76] = f18 - f16;
                        }
                        i67 += 2;
                        i16 = i70;
                    }
                    int i79 = i16;
                }
            }
            i8 = i16;
        } else {
            f = sin;
            c2 = 2;
            i8 = i16;
            System.arraycopy(fArr4, i14, fArr3, i13, i12);
        }
        for (int i80 = 1; i80 < i15; i80++) {
            int i81 = i80 * i11 * i9;
            int i82 = (i10 - i80) * i11 * i9;
            for (int i83 = 0; i83 < i11; i83++) {
                int i84 = i83 * i9;
                int i85 = i84 + i81;
                int i86 = i84 + i82;
                float f20 = fArr4[i14 + i85];
                float f21 = fArr4[i14 + i86];
                fArr3[i85 + i13] = f20 + f21;
                fArr3[i86 + i13] = f21 - f20;
            }
        }
        int i87 = (i10 - 1) * i12;
        float f22 = 1.0f;
        float f23 = 0.0f;
        int i88 = 1;
        while (i88 < i15) {
            float f24 = (cos * f22) - (f * f23);
            f23 = (f23 * cos) + (f22 * f);
            int i89 = i88 * i12;
            int i90 = (i10 - i88) * i12;
            float f25 = cos;
            for (int i91 = 0; i91 < i12; i91++) {
                int i92 = i14 + i91;
                int i93 = i13 + i91;
                fArr4[i92 + i89] = fArr3[i93] + (fArr3[i93 + i12] * f24);
                fArr4[i92 + i90] = fArr3[i93 + i87] * f23;
            }
            float f26 = f23;
            float f27 = f24;
            int i94 = c2;
            while (i94 < i15) {
                float f28 = (f24 * f27) - (f23 * f26);
                f26 = (f26 * f24) + (f27 * f23);
                int i95 = i94 * i12;
                int i96 = (i10 - i94) * i12;
                int i97 = i87;
                for (int i98 = 0; i98 < i12; i98++) {
                    int i99 = i14 + i98;
                    int i100 = i13 + i98;
                    int i101 = i99 + i89;
                    fArr4[i101] = fArr4[i101] + (fArr3[i100 + i95] * f28);
                    int i102 = i99 + i90;
                    fArr4[i102] = fArr4[i102] + (fArr3[i100 + i96] * f26);
                }
                i94++;
                i87 = i97;
                f27 = f28;
            }
            int i103 = i87;
            i88++;
            f22 = f24;
            cos = f25;
        }
        for (int i104 = 1; i104 < i15; i104++) {
            int i105 = i104 * i12;
            for (int i106 = 0; i106 < i12; i106++) {
                int i107 = i14 + i106;
                fArr4[i107] = fArr4[i107] + fArr3[i13 + i106 + i105];
            }
        }
        if (i9 >= i11) {
            for (int i108 = 0; i108 < i11; i108++) {
                int i109 = i108 * i9;
                int i110 = i109 * i10;
                for (int i111 = 0; i111 < i9; i111++) {
                    fArr3[i13 + i111 + i110] = fArr4[i14 + i111 + i109];
                }
            }
        } else {
            for (int i112 = 0; i112 < i9; i112++) {
                for (int i113 = 0; i113 < i11; i113++) {
                    int i114 = i113 * i9;
                    fArr3[i13 + i112 + (i114 * i10)] = fArr4[i14 + i112 + i114];
                }
            }
        }
        int i115 = i10 * i9;
        for (int i116 = 1; i116 < i15; i116++) {
            int i117 = i116 * i11 * i9;
            int i118 = (i10 - i116) * i11 * i9;
            int i119 = i116 * 2 * i9;
            for (int i120 = 0; i120 < i11; i120++) {
                int i121 = i120 * i9;
                int i122 = i120 * i115;
                fArr3[((((i13 + i9) - 1) + i119) - i9) + i122] = fArr4[i121 + i117 + i14];
                fArr3[i13 + i119 + i122] = fArr4[i121 + i118 + i14];
            }
        }
        if (i9 != 1) {
            if (i8 >= i11) {
                for (int i123 = 1; i123 < i15; i123++) {
                    int i124 = i123 * i11 * i9;
                    int i125 = (i10 - i123) * i11 * i9;
                    int i126 = i123 * 2 * i9;
                    int i127 = 0;
                    while (i127 < i11) {
                        int i128 = i127 * i115;
                        int i129 = i127 * i9;
                        int i130 = i115;
                        for (int i131 = c2; i131 < i9; i131 += 2) {
                            int i132 = i13 + i131 + i126 + i128;
                            int i133 = (((i13 + (i9 - i131)) + i126) - i9) + i128;
                            int i134 = i14 + i131 + i129;
                            int i135 = i134 + i124;
                            int i136 = i134 + i125;
                            float f29 = fArr4[i135 - 1];
                            float f30 = fArr4[i135];
                            float f31 = fArr4[i136 - 1];
                            float f32 = fArr4[i136];
                            fArr3[i132 - 1] = f29 + f31;
                            fArr3[i133 - 1] = f29 - f31;
                            fArr3[i132] = f30 + f32;
                            fArr3[i133] = f32 - f30;
                        }
                        i127++;
                        i115 = i130;
                    }
                    int i137 = i115;
                }
                return;
            }
            int i138 = i115;
            int i139 = 1;
            while (i139 < i15) {
                int i140 = i139 * i11 * i9;
                int i141 = (i10 - i139) * i11 * i9;
                int i142 = i139 * 2 * i9;
                int i143 = c2;
                while (i143 < i9) {
                    int i144 = i13 + i143;
                    int i145 = (i9 - i143) + i13;
                    int i146 = i14 + i143;
                    for (int i147 = 0; i147 < i11; i147++) {
                        int i148 = i147 * i138;
                        int i149 = i144 + i142 + i148;
                        int i150 = ((i145 + i142) - i9) + i148;
                        int i151 = i146 + (i147 * i9);
                        int i152 = i151 + i140;
                        int i153 = i151 + i141;
                        float f33 = fArr4[i152 - 1];
                        float f34 = fArr4[i152];
                        float f35 = fArr4[i153 - 1];
                        float f36 = fArr4[i153];
                        fArr3[i149 - 1] = f33 + f35;
                        fArr3[i150 - 1] = f33 - f35;
                        fArr3[i149] = f34 + f36;
                        fArr3[i150] = f36 - f34;
                    }
                    i143 += 2;
                    int i154 = i2;
                }
                i139++;
                i10 = i2;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void radfg(long j, long j2, long j3, long j4, FloatLargeArray floatLargeArray, long j5, FloatLargeArray floatLargeArray2, long j6, long j7) {
        float f;
        FloatFFT_1D floatFFT_1D = this;
        long j8 = j;
        long j9 = j2;
        FloatLargeArray floatLargeArray3 = floatLargeArray;
        FloatLargeArray floatLargeArray4 = floatLargeArray2;
        double d = (double) (TWO_PI / ((float) j9));
        float cos = (float) FastMath.cos(d);
        float sin = (float) FastMath.sin(d);
        long j10 = (j9 + 1) / 2;
        long j11 = (j8 - 1) / 2;
        int i = (j8 > 1 ? 1 : (j8 == 1 ? 0 : -1));
        if (i != 0) {
            for (long j12 = 0; j12 < j4; j12++) {
                floatLargeArray4.setFloat(j6 + j12, floatLargeArray3.getFloat(j5 + j12));
            }
            for (long j13 = 1; j13 < j9; j13++) {
                long j14 = j13 * j3 * j8;
                long j15 = 0;
                while (j15 < j3) {
                    long j16 = (j15 * j8) + j14;
                    floatLargeArray4.setFloat(j6 + j16, floatLargeArray3.getFloat(j5 + j16));
                    j15++;
                    j14 = j14;
                    sin = sin;
                }
                float f2 = sin;
            }
            float f3 = sin;
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
                        float f4 = floatFFT_1D.wtable_rl.getFloat(j24 - 1);
                        float f5 = floatFFT_1D.wtable_rl.getFloat(j24);
                        long j28 = 0;
                        while (j28 < j3) {
                            long j29 = (j28 * j8) + j20;
                            long j30 = j26 + j29;
                            long j31 = j25 + j29;
                            float f6 = floatLargeArray3.getFloat(j31 - 1);
                            float f7 = floatLargeArray3.getFloat(j31);
                            floatLargeArray4.setFloat(j30 - 1, (f4 * f6) + (f5 * f7));
                            floatLargeArray4.setFloat(j30, (f7 * f4) - (f6 * f5));
                            j28++;
                            j8 = j;
                            long j32 = j2;
                            j18 = j18;
                        }
                        long j33 = j18;
                        j21 += 2;
                        floatFFT_1D = this;
                        j8 = j;
                        long j34 = j2;
                        j17 = j23;
                        j19 = j27;
                    }
                    long j35 = j17;
                    j18++;
                    floatFFT_1D = this;
                    j8 = j;
                    j9 = j2;
                }
            } else {
                long j36 = j8;
                long j37 = -j36;
                long j38 = 1;
                while (j38 < j2) {
                    j37 += j36;
                    long j39 = j38 * j3 * j36;
                    long j40 = 0;
                    while (j40 < j3) {
                        long j41 = j37 - 1;
                        long j42 = (j40 * j36) + j39;
                        long j43 = 2;
                        while (j43 < j36) {
                            j41 += 2;
                            long j44 = j37;
                            long j45 = j41 + j7;
                            long j46 = j39;
                            float f8 = this.wtable_rl.getFloat(j45 - 1);
                            float f9 = this.wtable_rl.getFloat(j45);
                            long j47 = j6 + j43 + j42;
                            long j48 = j5 + j43 + j42;
                            int i3 = i2;
                            float f10 = floatLargeArray3.getFloat(j48 - 1);
                            float f11 = floatLargeArray3.getFloat(j48);
                            floatLargeArray4.setFloat(j47 - 1, (f8 * f10) + (f9 * f11));
                            floatLargeArray4.setFloat(j47, (f8 * f11) - (f9 * f10));
                            j43 += 2;
                            j36 = j;
                            i2 = i3;
                            j37 = j44;
                            j39 = j46;
                            j38 = j38;
                        }
                        long j49 = j37;
                        long j50 = j38;
                        int i4 = i2;
                        j40++;
                        j36 = j;
                        j39 = j39;
                    }
                    long j51 = j37;
                    int i5 = i2;
                    j38++;
                    j36 = j;
                }
            }
            if (i2 >= 0) {
                long j52 = 1;
                while (j52 < j10) {
                    long j53 = j52 * j3 * j;
                    long j54 = (j2 - j52) * j3 * j;
                    long j55 = 0;
                    while (j55 < j3) {
                        long j56 = j55 * j;
                        long j57 = j56 + j53;
                        long j58 = j56 + j54;
                        long j59 = 2;
                        while (j59 < j) {
                            long j60 = j5 + j59;
                            long j61 = j6 + j59;
                            long j62 = j54;
                            long j63 = j60 + j57;
                            long j64 = j53;
                            long j65 = j60 + j58;
                            long j66 = j52;
                            long j67 = j61 + j57;
                            long j68 = j57;
                            long j69 = j61 + j58;
                            long j70 = j58;
                            float f12 = floatLargeArray4.getFloat(j67 - 1);
                            float f13 = floatLargeArray4.getFloat(j67);
                            long j71 = j55;
                            float f14 = floatLargeArray4.getFloat(j69 - 1);
                            float f15 = floatLargeArray4.getFloat(j69);
                            floatLargeArray3.setFloat(j63 - 1, f12 + f14);
                            floatLargeArray3.setFloat(j63, f13 + f15);
                            floatLargeArray3.setFloat(j65 - 1, f13 - f15);
                            floatLargeArray3.setFloat(j65, f14 - f12);
                            j59 += 2;
                            j54 = j62;
                            j52 = j66;
                            j58 = j70;
                            j53 = j64;
                            j57 = j68;
                            j55 = j71;
                        }
                        long j72 = j52;
                        long j73 = j54;
                        long j74 = j53;
                        j55++;
                    }
                    j52++;
                }
            } else {
                long j75 = 1;
                while (j75 < j10) {
                    long j76 = j75 * j3 * j;
                    long j77 = (j2 - j75) * j3 * j;
                    for (long j78 = 2; j78 < j; j78 += 2) {
                        long j79 = j5 + j78;
                        long j80 = j6 + j78;
                        long j81 = 0;
                        while (j81 < j3) {
                            long j82 = j81 * j;
                            long j83 = j82 + j76;
                            long j84 = j82 + j77;
                            long j85 = j77;
                            long j86 = j79 + j83;
                            long j87 = j76;
                            long j88 = j79 + j84;
                            long j89 = j79;
                            long j90 = j80 + j83;
                            long j91 = j75;
                            long j92 = j80 + j84;
                            long j93 = j80;
                            float f16 = floatLargeArray4.getFloat(j90 - 1);
                            float f17 = floatLargeArray4.getFloat(j90);
                            float f18 = floatLargeArray4.getFloat(j92 - 1);
                            float f19 = floatLargeArray4.getFloat(j92);
                            floatLargeArray3.setFloat(j86 - 1, f16 + f18);
                            floatLargeArray3.setFloat(j86, f17 + f19);
                            floatLargeArray3.setFloat(j88 - 1, f17 - f19);
                            floatLargeArray3.setFloat(j88, f18 - f16);
                            j81++;
                            j77 = j85;
                            j80 = j93;
                            j75 = j91;
                            j76 = j87;
                            j79 = j89;
                        }
                        long j94 = j75;
                        long j95 = j77;
                        long j96 = j76;
                    }
                    j75++;
                }
            }
            f = f3;
        } else {
            f = sin;
            LargeArrayUtils.arraycopy(floatLargeArray2, j6, floatLargeArray, j5, j4);
        }
        for (long j97 = 1; j97 < j10; j97++) {
            long j98 = j97 * j3 * j;
            long j99 = (j2 - j97) * j3 * j;
            long j100 = 0;
            while (j100 < j3) {
                long j101 = j100 * j;
                long j102 = j101 + j98;
                long j103 = j101 + j99;
                long j104 = j99;
                float f20 = floatLargeArray4.getFloat(j6 + j102);
                float f21 = floatLargeArray4.getFloat(j6 + j103);
                floatLargeArray3.setFloat(j5 + j102, f20 + f21);
                floatLargeArray3.setFloat(j5 + j103, f21 - f20);
                j100++;
                j99 = j104;
                j98 = j98;
            }
        }
        long j105 = (j2 - 1) * j4;
        float f22 = 1.0f;
        float f23 = 0.0f;
        long j106 = 1;
        while (j106 < j10) {
            float f24 = (cos * f22) - (f * f23);
            float f25 = (f23 * cos) + (f * f22);
            long j107 = j106 * j4;
            long j108 = (j2 - j106) * j4;
            long j109 = 0;
            while (j109 < j4) {
                long j110 = j6 + j109;
                long j111 = j106;
                long j112 = j5 + j109;
                float f26 = f25;
                floatLargeArray4.setFloat(j110 + j107, floatLargeArray3.getFloat(j112) + (floatLargeArray3.getFloat(j112 + j4) * f24));
                floatLargeArray4.setFloat(j110 + j108, floatLargeArray3.getFloat(j112 + j105) * f26);
                j109++;
                j106 = j111;
                f25 = f26;
                j107 = j107;
            }
            float f27 = f25;
            long j113 = j106;
            long j114 = j107;
            float f28 = f24;
            float f29 = f27;
            long j115 = 2;
            while (j115 < j10) {
                float f30 = (f24 * f28) - (f27 * f29);
                f29 = (f29 * f24) + (f28 * f27);
                long j116 = j115 * j4;
                long j117 = (j2 - j115) * j4;
                long j118 = 0;
                while (j118 < j4) {
                    long j119 = j6 + j118;
                    long j120 = j5 + j118;
                    float f31 = f;
                    long j121 = j119 + j114;
                    floatLargeArray4.setFloat(j121, floatLargeArray4.getFloat(j121) + (floatLargeArray3.getFloat(j120 + j116) * f30));
                    long j122 = j119 + j108;
                    floatLargeArray4.setFloat(j122, floatLargeArray4.getFloat(j122) + (floatLargeArray3.getFloat(j120 + j117) * f29));
                    j118++;
                    f = f31;
                    j105 = j105;
                    j115 = j115;
                }
                float f32 = f;
                long j123 = j105;
                j115++;
                f28 = f30;
            }
            f22 = f24;
            f23 = f27;
            f = f;
            j106 = j113 + 1;
            j105 = j105;
        }
        for (long j124 = 1; j124 < j10; j124++) {
            long j125 = j124 * j4;
            for (long j126 = 0; j126 < j4; j126++) {
                long j127 = j6 + j126;
                floatLargeArray4.setFloat(j127, floatLargeArray4.getFloat(j127) + floatLargeArray3.getFloat(j5 + j126 + j125));
            }
        }
        if (j >= j3) {
            for (long j128 = 0; j128 < j3; j128++) {
                long j129 = j128 * j;
                long j130 = j129 * j2;
                for (long j131 = 0; j131 < j; j131++) {
                    floatLargeArray3.setFloat(j5 + j131 + j130, floatLargeArray4.getFloat(j6 + j131 + j129));
                }
            }
        } else {
            for (long j132 = 0; j132 < j; j132++) {
                for (long j133 = 0; j133 < j3; j133++) {
                    long j134 = j133 * j;
                    floatLargeArray3.setFloat(j5 + j132 + (j134 * j2), floatLargeArray4.getFloat(j6 + j132 + j134));
                }
            }
        }
        long j135 = j2 * j;
        for (long j136 = 1; j136 < j10; j136++) {
            long j137 = j136 * j3 * j;
            long j138 = (j2 - j136) * j3 * j;
            long j139 = j136 * 2 * j;
            long j140 = 0;
            while (j140 < j3) {
                long j141 = j140 * j;
                long j142 = j140 * j135;
                floatLargeArray3.setFloat(((((j5 + j) - 1) + j139) - j) + j142, floatLargeArray4.getFloat(j6 + j141 + j137));
                floatLargeArray3.setFloat(j5 + j139 + j142, floatLargeArray4.getFloat(j6 + j141 + j138));
                j140++;
                j138 = j138;
                j137 = j137;
            }
        }
        if (i != 0) {
            if (j11 >= j3) {
                long j143 = 1;
                while (j143 < j10) {
                    long j144 = j143 * j3 * j;
                    long j145 = (j2 - j143) * j3 * j;
                    long j146 = j143 * 2 * j;
                    long j147 = 0;
                    while (j147 < j3) {
                        long j148 = j147 * j135;
                        long j149 = j147 * j;
                        long j150 = 2;
                        while (j150 < j) {
                            long j151 = j135;
                            long j152 = j5 + j150 + j146 + j148;
                            long j153 = j146;
                            long j154 = (((j5 + (j - j150)) + j146) - j) + j148;
                            long j155 = j6 + j150 + j149;
                            long j156 = j143;
                            long j157 = j155 + j144;
                            long j158 = j144;
                            long j159 = j155 + j145;
                            long j160 = j145;
                            float f33 = floatLargeArray4.getFloat(j157 - 1);
                            float f34 = floatLargeArray4.getFloat(j157);
                            float f35 = floatLargeArray4.getFloat(j159 - 1);
                            float f36 = floatLargeArray4.getFloat(j159);
                            floatLargeArray3.setFloat(j152 - 1, f33 + f35);
                            floatLargeArray3.setFloat(j154 - 1, f33 - f35);
                            floatLargeArray3.setFloat(j152, f34 + f36);
                            floatLargeArray3.setFloat(j154, f36 - f34);
                            j150 += 2;
                            j135 = j151;
                            j146 = j153;
                            j143 = j156;
                            j144 = j158;
                            j145 = j160;
                            j147 = j147;
                        }
                        long j161 = j135;
                        long j162 = j143;
                        long j163 = j145;
                        long j164 = j146;
                        long j165 = j144;
                        j147++;
                    }
                    long j166 = j135;
                    j143++;
                }
                return;
            }
            long j167 = j135;
            long j168 = 1;
            while (j168 < j10) {
                long j169 = j168 * j3 * j;
                long j170 = (j2 - j168) * j3 * j;
                long j171 = j168 * 2 * j;
                long j172 = 2;
                while (j172 < j) {
                    long j173 = j5 + j172;
                    long j174 = j5 + (j - j172);
                    long j175 = j6 + j172;
                    long j176 = 0;
                    while (j176 < j3) {
                        long j177 = j167;
                        long j178 = j176 * j177;
                        long j179 = j168;
                        long j180 = j173 + j171 + j178;
                        long j181 = j171;
                        long j182 = ((j174 + j171) - j) + j178;
                        long j183 = j175 + (j176 * j);
                        long j184 = j174;
                        long j185 = j183 + j169;
                        long j186 = j169;
                        long j187 = j183 + j170;
                        long j188 = j170;
                        float f37 = floatLargeArray4.getFloat(j185 - 1);
                        float f38 = floatLargeArray4.getFloat(j185);
                        float f39 = floatLargeArray4.getFloat(j187 - 1);
                        float f40 = floatLargeArray4.getFloat(j187);
                        floatLargeArray3.setFloat(j180 - 1, f37 + f39);
                        floatLargeArray3.setFloat(j182 - 1, f37 - f39);
                        floatLargeArray3.setFloat(j180, f38 + f40);
                        floatLargeArray3.setFloat(j182, f40 - f38);
                        j176++;
                        j168 = j179;
                        j171 = j181;
                        j167 = j177;
                        j174 = j184;
                        j170 = j188;
                        j169 = j186;
                    }
                    long j189 = j167;
                    long j190 = j168;
                    long j191 = j170;
                    long j192 = j171;
                    long j193 = j169;
                    j172 += 2;
                    j167 = j189;
                }
                long j194 = j167;
                j168++;
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0176  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x020a  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0223  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x024c A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x024d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void radbg(int r31, int r32, int r33, int r34, float[] r35, int r36, float[] r37, int r38, int r39) {
        /*
            r30 = this;
            r0 = r30
            r1 = r31
            r2 = r32
            r3 = r33
            r4 = r34
            r5 = r35
            r6 = r36
            r7 = r37
            r8 = r38
            r9 = 1086918619(0x40c90fdb, float:6.2831855)
            float r10 = (float) r2
            float r9 = r9 / r10
            double r9 = (double) r9
            double r11 = org.apache.commons.math3.util.FastMath.cos(r9)
            float r11 = (float) r11
            double r9 = org.apache.commons.math3.util.FastMath.sin(r9)
            float r9 = (float) r9
            int r10 = r1 + -1
            r12 = 2
            int r10 = r10 / r12
            int r13 = r2 + 1
            int r13 = r13 / r12
            int r14 = r2 * r1
            if (r1 < r3) goto L_0x004a
            r15 = 0
        L_0x002e:
            if (r15 >= r3) goto L_0x0068
            int r16 = r15 * r1
            int r17 = r15 * r14
            r12 = 0
        L_0x0035:
            if (r12 >= r1) goto L_0x0046
            int r18 = r8 + r12
            int r18 = r18 + r16
            int r19 = r6 + r12
            int r19 = r19 + r17
            r19 = r5[r19]
            r7[r18] = r19
            int r12 = r12 + 1
            goto L_0x0035
        L_0x0046:
            int r15 = r15 + 1
            r12 = 2
            goto L_0x002e
        L_0x004a:
            r12 = 0
        L_0x004b:
            if (r12 >= r1) goto L_0x0068
            int r15 = r8 + r12
            int r16 = r6 + r12
            r0 = 0
        L_0x0052:
            if (r0 >= r3) goto L_0x0063
            int r17 = r0 * r1
            int r17 = r15 + r17
            int r18 = r0 * r14
            int r18 = r16 + r18
            r18 = r5[r18]
            r7[r17] = r18
            int r0 = r0 + 1
            goto L_0x0052
        L_0x0063:
            int r12 = r12 + 1
            r0 = r30
            goto L_0x004b
        L_0x0068:
            int r0 = r6 + r1
            r12 = 1
            int r0 = r0 - r12
            r14 = r12
        L_0x006d:
            if (r14 >= r13) goto L_0x00a5
            int r15 = r2 - r14
            int r16 = r14 * 2
            int r17 = r14 * r3
            int r17 = r17 * r1
            int r15 = r15 * r3
            int r15 = r15 * r1
            int r16 = r16 * r1
            r12 = 0
        L_0x007c:
            if (r12 >= r3) goto L_0x00a1
            int r19 = r12 * r1
            int r20 = r19 * r2
            int r21 = r0 + r16
            int r21 = r21 + r20
            int r21 = r21 - r1
            int r22 = r6 + r16
            int r22 = r22 + r20
            r20 = r5[r21]
            r21 = r5[r22]
            int r19 = r8 + r19
            int r22 = r19 + r17
            float r20 = r20 + r20
            r7[r22] = r20
            int r19 = r19 + r15
            float r21 = r21 + r21
            r7[r19] = r21
            int r12 = r12 + 1
            goto L_0x007c
        L_0x00a1:
            int r14 = r14 + 1
            r12 = 1
            goto L_0x006d
        L_0x00a5:
            if (r1 == r12) goto L_0x016b
            if (r10 < r3) goto L_0x010b
            r12 = 1
        L_0x00aa:
            if (r12 >= r13) goto L_0x016b
            int r0 = r2 - r12
            int r14 = r12 * r3
            int r14 = r14 * r1
            int r0 = r0 * r3
            int r0 = r0 * r1
            int r15 = r12 * 2
            int r15 = r15 * r1
            r16 = r10
            r10 = 0
        L_0x00b9:
            if (r10 >= r3) goto L_0x0106
            int r17 = r10 * r1
            int r19 = r17 + r14
            int r17 = r17 + r0
            int r20 = r10 * r2
            int r20 = r20 * r1
            int r20 = r20 + r15
            r21 = r0
            r0 = 2
        L_0x00ca:
            if (r0 >= r1) goto L_0x0101
            int r22 = r1 - r0
            int r23 = r8 + r0
            int r22 = r6 + r22
            int r24 = r6 + r0
            int r25 = r23 + r19
            int r23 = r23 + r17
            int r24 = r24 + r20
            int r22 = r22 + r20
            int r22 = r22 - r1
            int r26 = r24 + -1
            r26 = r5[r26]
            r24 = r5[r24]
            int r27 = r22 + -1
            r27 = r5[r27]
            r22 = r5[r22]
            int r28 = r25 + -1
            float r29 = r26 + r27
            r7[r28] = r29
            int r28 = r23 + -1
            float r26 = r26 - r27
            r7[r28] = r26
            float r26 = r24 - r22
            r7[r25] = r26
            float r24 = r24 + r22
            r7[r23] = r24
            int r0 = r0 + 2
            goto L_0x00ca
        L_0x0101:
            int r10 = r10 + 1
            r0 = r21
            goto L_0x00b9
        L_0x0106:
            int r12 = r12 + 1
            r10 = r16
            goto L_0x00aa
        L_0x010b:
            r16 = r10
            r12 = 1
        L_0x010e:
            if (r12 >= r13) goto L_0x016d
            int r0 = r2 - r12
            int r10 = r12 * r3
            int r10 = r10 * r1
            int r0 = r0 * r3
            int r0 = r0 * r1
            int r14 = r12 * 2
            int r14 = r14 * r1
            r15 = 2
        L_0x011b:
            if (r15 >= r1) goto L_0x0166
            int r17 = r1 - r15
            int r19 = r8 + r15
            int r17 = r6 + r17
            int r20 = r6 + r15
            r8 = 0
        L_0x0126:
            if (r8 >= r3) goto L_0x0161
            int r21 = r8 * r1
            int r22 = r21 + r10
            int r21 = r21 + r0
            int r23 = r8 * r2
            int r23 = r23 * r1
            int r23 = r23 + r14
            int r22 = r19 + r22
            int r21 = r19 + r21
            int r24 = r20 + r23
            int r23 = r17 + r23
            int r23 = r23 - r1
            int r25 = r24 + -1
            r25 = r5[r25]
            r24 = r5[r24]
            int r26 = r23 + -1
            r26 = r5[r26]
            r23 = r5[r23]
            int r27 = r22 + -1
            float r28 = r25 + r26
            r7[r27] = r28
            int r27 = r21 + -1
            float r25 = r25 - r26
            r7[r27] = r25
            float r25 = r24 - r23
            r7[r22] = r25
            float r24 = r24 + r23
            r7[r21] = r24
            int r8 = r8 + 1
            goto L_0x0126
        L_0x0161:
            int r15 = r15 + 2
            r8 = r38
            goto L_0x011b
        L_0x0166:
            int r12 = r12 + 1
            r8 = r38
            goto L_0x010e
        L_0x016b:
            r16 = r10
        L_0x016d:
            int r0 = r2 + -1
            int r0 = r0 * r4
            r8 = 1065353216(0x3f800000, float:1.0)
            r10 = 0
            r12 = 1
        L_0x0174:
            if (r12 >= r13) goto L_0x0205
            int r14 = r2 - r12
            float r15 = r11 * r8
            float r17 = r9 * r10
            float r15 = r15 - r17
            float r10 = r10 * r11
            float r8 = r8 * r9
            float r10 = r10 + r8
            int r8 = r12 * r4
            int r14 = r14 * r4
            r17 = r9
            r9 = 0
        L_0x0187:
            if (r9 >= r4) goto L_0x01ae
            int r19 = r6 + r9
            r20 = r11
            r11 = r38
            int r21 = r11 + r9
            int r22 = r19 + r8
            r23 = r7[r21]
            int r24 = r21 + r4
            r24 = r7[r24]
            float r24 = r24 * r15
            float r23 = r23 + r24
            r5[r22] = r23
            int r19 = r19 + r14
            int r21 = r21 + r0
            r21 = r7[r21]
            float r21 = r21 * r10
            r5[r19] = r21
            int r9 = r9 + 1
            r11 = r20
            goto L_0x0187
        L_0x01ae:
            r20 = r11
            r11 = r38
            r21 = r10
            r19 = r15
            r9 = 2
        L_0x01b7:
            if (r9 >= r13) goto L_0x01fa
            int r22 = r2 - r9
            float r23 = r15 * r19
            float r24 = r10 * r21
            float r23 = r23 - r24
            float r21 = r21 * r15
            float r19 = r19 * r10
            float r21 = r21 + r19
            int r19 = r9 * r4
            int r22 = r22 * r4
            r24 = r0
            r0 = 0
        L_0x01ce:
            if (r0 >= r4) goto L_0x01f3
            int r25 = r6 + r0
            int r26 = r11 + r0
            int r27 = r25 + r8
            r28 = r5[r27]
            int r29 = r26 + r19
            r29 = r7[r29]
            float r29 = r29 * r23
            float r28 = r28 + r29
            r5[r27] = r28
            int r25 = r25 + r14
            r27 = r5[r25]
            int r26 = r26 + r22
            r26 = r7[r26]
            float r26 = r26 * r21
            float r27 = r27 + r26
            r5[r25] = r27
            int r0 = r0 + 1
            goto L_0x01ce
        L_0x01f3:
            int r9 = r9 + 1
            r19 = r23
            r0 = r24
            goto L_0x01b7
        L_0x01fa:
            r24 = r0
            int r12 = r12 + 1
            r8 = r15
            r9 = r17
            r11 = r20
            goto L_0x0174
        L_0x0205:
            r11 = r38
            r12 = 1
        L_0x0208:
            if (r12 >= r13) goto L_0x0220
            int r0 = r12 * r4
            r8 = 0
        L_0x020d:
            if (r8 >= r4) goto L_0x021d
            int r9 = r11 + r8
            r10 = r7[r9]
            int r14 = r9 + r0
            r14 = r7[r14]
            float r10 = r10 + r14
            r7[r9] = r10
            int r8 = r8 + 1
            goto L_0x020d
        L_0x021d:
            int r12 = r12 + 1
            goto L_0x0208
        L_0x0220:
            r12 = 1
        L_0x0221:
            if (r12 >= r13) goto L_0x0249
            int r0 = r2 - r12
            int r8 = r12 * r3
            int r8 = r8 * r1
            int r0 = r0 * r3
            int r0 = r0 * r1
            r9 = 0
        L_0x022b:
            if (r9 >= r3) goto L_0x0246
            int r10 = r9 * r1
            int r14 = r11 + r10
            int r10 = r10 + r6
            int r15 = r10 + r8
            int r10 = r10 + r0
            r15 = r5[r15]
            r10 = r5[r10]
            int r17 = r14 + r8
            float r19 = r15 - r10
            r7[r17] = r19
            int r14 = r14 + r0
            float r15 = r15 + r10
            r7[r14] = r15
            int r9 = r9 + 1
            goto L_0x022b
        L_0x0246:
            int r12 = r12 + 1
            goto L_0x0221
        L_0x0249:
            r12 = 1
            if (r1 != r12) goto L_0x024d
            return
        L_0x024d:
            r10 = r16
            if (r10 < r3) goto L_0x029e
            r0 = r12
        L_0x0252:
            if (r0 >= r13) goto L_0x02ef
            int r8 = r2 - r0
            int r9 = r0 * r3
            int r9 = r9 * r1
            int r8 = r8 * r3
            int r8 = r8 * r1
            r14 = 0
        L_0x025c:
            if (r14 >= r3) goto L_0x029a
            int r15 = r14 * r1
            r12 = 2
        L_0x0261:
            if (r12 >= r1) goto L_0x0296
            int r16 = r11 + r12
            int r17 = r6 + r12
            int r16 = r16 + r15
            int r19 = r16 + r9
            int r16 = r16 + r8
            int r17 = r17 + r15
            int r20 = r17 + r9
            int r17 = r17 + r8
            int r21 = r20 + -1
            r21 = r5[r21]
            r20 = r5[r20]
            int r22 = r17 + -1
            r22 = r5[r22]
            r17 = r5[r17]
            int r23 = r19 + -1
            float r24 = r21 - r17
            r7[r23] = r24
            int r23 = r16 + -1
            float r21 = r21 + r17
            r7[r23] = r21
            float r17 = r20 + r22
            r7[r19] = r17
            float r20 = r20 - r22
            r7[r16] = r20
            int r12 = r12 + 2
            goto L_0x0261
        L_0x0296:
            int r14 = r14 + 1
            r12 = 1
            goto L_0x025c
        L_0x029a:
            int r0 = r0 + 1
            r12 = 1
            goto L_0x0252
        L_0x029e:
            r12 = 1
        L_0x029f:
            if (r12 >= r13) goto L_0x02ef
            int r0 = r2 - r12
            int r8 = r12 * r3
            int r8 = r8 * r1
            int r0 = r0 * r3
            int r0 = r0 * r1
            r9 = 2
        L_0x02a9:
            if (r9 >= r1) goto L_0x02ea
            int r14 = r11 + r9
            int r15 = r6 + r9
            r16 = r13
            r13 = 0
        L_0x02b2:
            if (r13 >= r3) goto L_0x02e5
            int r17 = r13 * r1
            int r19 = r14 + r17
            int r20 = r19 + r8
            int r19 = r19 + r0
            int r17 = r15 + r17
            int r21 = r17 + r8
            int r17 = r17 + r0
            int r22 = r21 + -1
            r22 = r5[r22]
            r21 = r5[r21]
            int r23 = r17 + -1
            r23 = r5[r23]
            r17 = r5[r17]
            int r24 = r20 + -1
            float r25 = r22 - r17
            r7[r24] = r25
            int r24 = r19 + -1
            float r22 = r22 + r17
            r7[r24] = r22
            float r17 = r21 + r23
            r7[r20] = r17
            float r21 = r21 - r23
            r7[r19] = r21
            int r13 = r13 + 1
            goto L_0x02b2
        L_0x02e5:
            int r9 = r9 + 2
            r13 = r16
            goto L_0x02a9
        L_0x02ea:
            r16 = r13
            int r12 = r12 + 1
            goto L_0x029f
        L_0x02ef:
            java.lang.System.arraycopy(r7, r11, r5, r6, r4)
            r12 = 1
        L_0x02f3:
            if (r12 >= r2) goto L_0x030b
            int r0 = r12 * r3
            int r0 = r0 * r1
            r4 = 0
        L_0x02f9:
            if (r4 >= r3) goto L_0x0308
            int r8 = r4 * r1
            int r8 = r8 + r0
            int r9 = r6 + r8
            int r8 = r8 + r11
            r8 = r7[r8]
            r5[r9] = r8
            int r4 = r4 + 1
            goto L_0x02f9
        L_0x0308:
            int r12 = r12 + 1
            goto L_0x02f3
        L_0x030b:
            if (r10 > r3) goto L_0x0363
            int r0 = -r1
            r12 = 1
        L_0x030f:
            if (r12 >= r2) goto L_0x0360
            int r0 = r0 + r1
            int r4 = r0 + -1
            int r8 = r12 * r3
            int r8 = r8 * r1
            r9 = 2
        L_0x0318:
            if (r9 >= r1) goto L_0x0359
            r10 = 2
            int r4 = r4 + r10
            int r10 = r4 + r39
            r13 = r30
            float[] r14 = r13.wtable_r
            int r15 = r10 + -1
            r15 = r14[r15]
            r10 = r14[r10]
            int r14 = r6 + r9
            int r16 = r11 + r9
            r34 = r0
            r0 = 0
        L_0x032f:
            if (r0 >= r3) goto L_0x0354
            int r17 = r0 * r1
            int r17 = r17 + r8
            int r18 = r14 + r17
            int r17 = r16 + r17
            int r19 = r17 + -1
            r19 = r7[r19]
            r17 = r7[r17]
            int r20 = r18 + -1
            float r21 = r15 * r19
            float r22 = r10 * r17
            float r21 = r21 - r22
            r5[r20] = r21
            float r17 = r17 * r15
            float r19 = r19 * r10
            float r17 = r17 + r19
            r5[r18] = r17
            int r0 = r0 + 1
            goto L_0x032f
        L_0x0354:
            int r9 = r9 + 2
            r0 = r34
            goto L_0x0318
        L_0x0359:
            r13 = r30
            r34 = r0
            int r12 = r12 + 1
            goto L_0x030f
        L_0x0360:
            r13 = r30
            goto L_0x03ad
        L_0x0363:
            r13 = r30
            int r0 = -r1
            r12 = 1
        L_0x0367:
            if (r12 >= r2) goto L_0x03ad
            int r0 = r0 + r1
            int r4 = r12 * r3
            int r4 = r4 * r1
            r8 = 0
        L_0x036e:
            if (r8 >= r3) goto L_0x03aa
            int r9 = r0 + -1
            int r10 = r8 * r1
            int r10 = r10 + r4
            r14 = 2
        L_0x0376:
            if (r14 >= r1) goto L_0x03a7
            r15 = 2
            int r9 = r9 + r15
            int r16 = r9 + r39
            float[] r15 = r13.wtable_r
            int r17 = r16 + -1
            r17 = r15[r17]
            r15 = r15[r16]
            int r16 = r6 + r14
            int r18 = r11 + r14
            int r16 = r16 + r10
            int r18 = r18 + r10
            int r19 = r18 + -1
            r19 = r7[r19]
            r18 = r7[r18]
            int r20 = r16 + -1
            float r21 = r17 * r19
            float r22 = r15 * r18
            float r21 = r21 - r22
            r5[r20] = r21
            float r17 = r17 * r18
            float r15 = r15 * r19
            float r17 = r17 + r15
            r5[r16] = r17
            int r14 = r14 + 2
            goto L_0x0376
        L_0x03a7:
            int r8 = r8 + 1
            goto L_0x036e
        L_0x03aa:
            int r12 = r12 + 1
            goto L_0x0367
        L_0x03ad:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.FloatFFT_1D.radbg(int, int, int, int, float[], int, float[], int, int):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0234  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x02e5  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0306  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x0340 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0341  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void radbg(long r49, long r51, long r53, long r55, pl.edu.icm.jlargearrays.FloatLargeArray r57, long r58, pl.edu.icm.jlargearrays.FloatLargeArray r60, long r61, long r63) {
        /*
            r48 = this;
            r0 = r48
            r1 = r49
            r3 = r51
            r13 = r57
            r14 = r60
            r5 = 1086918619(0x40c90fdb, float:6.2831855)
            float r6 = (float) r3
            float r5 = r5 / r6
            double r5 = (double) r5
            double r7 = org.apache.commons.math3.util.FastMath.cos(r5)
            float r7 = (float) r7
            double r5 = org.apache.commons.math3.util.FastMath.sin(r5)
            float r5 = (float) r5
            r15 = 1
            long r8 = r1 - r15
            r17 = 2
            long r8 = r8 / r17
            long r10 = r3 + r15
            long r10 = r10 / r17
            long r19 = r3 * r1
            int r6 = (r1 > r53 ? 1 : (r1 == r53 ? 0 : -1))
            r21 = 0
            if (r6 < 0) goto L_0x0068
            r23 = r21
        L_0x0030:
            int r6 = (r23 > r53 ? 1 : (r23 == r53 ? 0 : -1))
            if (r6 >= 0) goto L_0x0062
            long r25 = r23 * r1
            long r27 = r23 * r19
            r29 = r21
        L_0x003a:
            int r6 = (r29 > r1 ? 1 : (r29 == r1 ? 0 : -1))
            if (r6 >= 0) goto L_0x005a
            long r31 = r61 + r29
            r12 = r5
            long r5 = r31 + r25
            long r31 = r58 + r29
            r33 = r7
            r34 = r8
            long r7 = r31 + r27
            float r7 = r13.getFloat(r7)
            r14.setFloat(r5, r7)
            long r29 = r29 + r15
            r5 = r12
            r7 = r33
            r8 = r34
            goto L_0x003a
        L_0x005a:
            r12 = r5
            r33 = r7
            r34 = r8
            long r23 = r23 + r15
            goto L_0x0030
        L_0x0062:
            r12 = r5
            r33 = r7
            r34 = r8
            goto L_0x0099
        L_0x0068:
            r12 = r5
            r33 = r7
            r34 = r8
            r5 = r21
        L_0x006f:
            int r7 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r7 >= 0) goto L_0x0099
            long r7 = r61 + r5
            long r23 = r58 + r5
            r25 = r21
        L_0x0079:
            int r9 = (r25 > r53 ? 1 : (r25 == r53 ? 0 : -1))
            if (r9 >= 0) goto L_0x0095
            long r27 = r25 * r1
            long r3 = r7 + r27
            long r27 = r25 * r19
            r29 = r7
            long r7 = r23 + r27
            float r7 = r13.getFloat(r7)
            r14.setFloat(r3, r7)
            long r25 = r25 + r15
            r3 = r51
            r7 = r29
            goto L_0x0079
        L_0x0095:
            long r5 = r5 + r15
            r3 = r51
            goto L_0x006f
        L_0x0099:
            long r3 = r58 + r1
            long r3 = r3 - r15
            r5 = r15
        L_0x009d:
            int r7 = (r5 > r10 ? 1 : (r5 == r10 ? 0 : -1))
            if (r7 >= 0) goto L_0x00ee
            long r7 = r51 - r5
            long r19 = r5 * r17
            long r23 = r5 * r53
            long r23 = r23 * r1
            long r7 = r7 * r53
            long r7 = r7 * r1
            long r19 = r19 * r1
            r25 = r21
        L_0x00b0:
            int r9 = (r25 > r53 ? 1 : (r25 == r53 ? 0 : -1))
            if (r9 >= 0) goto L_0x00e6
            long r27 = r25 * r1
            long r29 = r27 * r51
            long r31 = r3 + r19
            long r31 = r31 + r29
            r36 = r3
            long r3 = r31 - r1
            long r31 = r58 + r19
            r38 = r10
            long r9 = r31 + r29
            float r3 = r13.getFloat(r3)
            float r4 = r13.getFloat(r9)
            long r9 = r61 + r27
            r27 = r12
            long r11 = r9 + r23
            float r3 = r3 + r3
            r14.setFloat(r11, r3)
            long r9 = r9 + r7
            float r4 = r4 + r4
            r14.setFloat(r9, r4)
            long r25 = r25 + r15
            r12 = r27
            r3 = r36
            r10 = r38
            goto L_0x00b0
        L_0x00e6:
            r36 = r3
            r38 = r10
            r27 = r12
            long r5 = r5 + r15
            goto L_0x009d
        L_0x00ee:
            r38 = r10
            r27 = r12
            int r3 = (r1 > r15 ? 1 : (r1 == r15 ? 0 : -1))
            if (r3 == 0) goto L_0x0226
            int r4 = (r34 > r53 ? 1 : (r34 == r53 ? 0 : -1))
            if (r4 < 0) goto L_0x0192
            r4 = r15
        L_0x00fb:
            int r6 = (r4 > r38 ? 1 : (r4 == r38 ? 0 : -1))
            if (r6 >= 0) goto L_0x0226
            long r6 = r51 - r4
            long r8 = r4 * r53
            long r8 = r8 * r1
            long r6 = r6 * r53
            long r6 = r6 * r1
            long r10 = r4 * r17
            long r10 = r10 * r1
            r19 = r21
        L_0x010c:
            int r12 = (r19 > r53 ? 1 : (r19 == r53 ? 0 : -1))
            if (r12 >= 0) goto L_0x0187
            long r23 = r19 * r1
            long r25 = r23 + r8
            long r23 = r23 + r6
            long r28 = r19 * r51
            long r28 = r28 * r1
            long r28 = r28 + r10
            r30 = r17
        L_0x011e:
            int r12 = (r30 > r1 ? 1 : (r30 == r1 ? 0 : -1))
            if (r12 >= 0) goto L_0x0177
            long r36 = r1 - r30
            long r40 = r61 + r30
            long r36 = r58 + r36
            long r42 = r58 + r30
            r44 = r6
            long r6 = r40 + r25
            r46 = r8
            long r8 = r40 + r23
            r40 = r10
            long r10 = r42 + r28
            long r36 = r36 + r28
            r12 = r3
            r42 = r4
            long r3 = r36 - r1
            long r0 = r10 - r15
            float r0 = r13.getFloat(r0)
            float r1 = r13.getFloat(r10)
            long r10 = r3 - r15
            float r2 = r13.getFloat(r10)
            float r3 = r13.getFloat(r3)
            long r4 = r6 - r15
            float r10 = r0 + r2
            r14.setFloat(r4, r10)
            long r4 = r8 - r15
            float r0 = r0 - r2
            r14.setFloat(r4, r0)
            float r0 = r1 - r3
            r14.setFloat(r6, r0)
            float r1 = r1 + r3
            r14.setFloat(r8, r1)
            long r30 = r30 + r17
            r0 = r48
            r1 = r49
            r3 = r12
            r10 = r40
            r4 = r42
            r6 = r44
            r8 = r46
            goto L_0x011e
        L_0x0177:
            r12 = r3
            r42 = r4
            r44 = r6
            r46 = r8
            r40 = r10
            long r19 = r19 + r15
            r0 = r48
            r1 = r49
            goto L_0x010c
        L_0x0187:
            r12 = r3
            r42 = r4
            long r4 = r42 + r15
            r0 = r48
            r1 = r49
            goto L_0x00fb
        L_0x0192:
            r12 = r3
            r0 = r15
        L_0x0194:
            int r2 = (r0 > r38 ? 1 : (r0 == r38 ? 0 : -1))
            if (r2 >= 0) goto L_0x0223
            long r2 = r51 - r0
            long r4 = r0 * r53
            r9 = r49
            long r4 = r4 * r9
            long r2 = r2 * r53
            long r2 = r2 * r9
            long r6 = r0 * r17
            long r6 = r6 * r9
            r19 = r17
        L_0x01a7:
            int r8 = (r19 > r9 ? 1 : (r19 == r9 ? 0 : -1))
            if (r8 >= 0) goto L_0x021e
            long r23 = r9 - r19
            long r25 = r61 + r19
            long r23 = r58 + r23
            long r28 = r58 + r19
            r30 = r21
        L_0x01b5:
            int r8 = (r30 > r53 ? 1 : (r30 == r53 ? 0 : -1))
            if (r8 >= 0) goto L_0x0211
            long r36 = r30 * r9
            long r40 = r36 + r4
            long r36 = r36 + r2
            long r42 = r30 * r51
            long r42 = r42 * r9
            long r42 = r42 + r6
            r44 = r2
            long r2 = r25 + r40
            r40 = r4
            long r4 = r25 + r36
            r36 = r6
            long r6 = r28 + r42
            long r42 = r23 + r42
            r8 = r12
            long r11 = r42 - r9
            r10 = r8
            long r8 = r6 - r15
            float r8 = r13.getFloat(r8)
            float r6 = r13.getFloat(r6)
            r7 = r10
            long r9 = r11 - r15
            float r9 = r13.getFloat(r9)
            float r10 = r13.getFloat(r11)
            long r11 = r2 - r15
            r32 = r7
            float r7 = r8 + r9
            r14.setFloat(r11, r7)
            long r11 = r4 - r15
            float r8 = r8 - r9
            r14.setFloat(r11, r8)
            float r7 = r6 - r10
            r14.setFloat(r2, r7)
            float r6 = r6 + r10
            r14.setFloat(r4, r6)
            long r30 = r30 + r15
            r9 = r49
            r12 = r32
            r6 = r36
            r4 = r40
            r2 = r44
            goto L_0x01b5
        L_0x0211:
            r44 = r2
            r40 = r4
            r36 = r6
            r32 = r12
            long r19 = r19 + r17
            r9 = r49
            goto L_0x01a7
        L_0x021e:
            r32 = r12
            long r0 = r0 + r15
            goto L_0x0194
        L_0x0223:
            r32 = r12
            goto L_0x0228
        L_0x0226:
            r32 = r3
        L_0x0228:
            long r0 = r51 - r15
            long r0 = r0 * r55
            r2 = 1065353216(0x3f800000, float:1.0)
            r3 = 0
            r4 = r15
        L_0x0230:
            int r6 = (r4 > r38 ? 1 : (r4 == r38 ? 0 : -1))
            if (r6 >= 0) goto L_0x02e0
            long r6 = r51 - r4
            float r8 = r33 * r2
            float r9 = r27 * r3
            float r8 = r8 - r9
            float r3 = r3 * r33
            float r2 = r2 * r27
            float r3 = r3 + r2
            long r9 = r4 * r55
            long r6 = r6 * r55
            r11 = r21
        L_0x0246:
            int r2 = (r11 > r55 ? 1 : (r11 == r55 ? 0 : -1))
            if (r2 >= 0) goto L_0x0277
            long r19 = r58 + r11
            r23 = r4
            long r4 = r61 + r11
            r25 = r11
            long r11 = r19 + r9
            float r2 = r14.getFloat(r4)
            r28 = r9
            long r9 = r4 + r55
            float r9 = r14.getFloat(r9)
            float r9 = r9 * r8
            float r2 = r2 + r9
            r13.setFloat(r11, r2)
            long r9 = r19 + r6
            long r4 = r4 + r0
            float r2 = r14.getFloat(r4)
            float r2 = r2 * r3
            r13.setFloat(r9, r2)
            long r11 = r25 + r15
            r4 = r23
            r9 = r28
            goto L_0x0246
        L_0x0277:
            r23 = r4
            r28 = r9
            r9 = r3
            r2 = r8
            r4 = r17
        L_0x027f:
            int r10 = (r4 > r38 ? 1 : (r4 == r38 ? 0 : -1))
            if (r10 >= 0) goto L_0x02d7
            long r10 = r51 - r4
            float r12 = r8 * r2
            float r19 = r3 * r9
            float r12 = r12 - r19
            float r9 = r9 * r8
            float r2 = r2 * r3
            float r9 = r9 + r2
            long r19 = r4 * r55
            long r10 = r10 * r55
            r25 = r21
        L_0x0294:
            int r2 = (r25 > r55 ? 1 : (r25 == r55 ? 0 : -1))
            if (r2 >= 0) goto L_0x02cd
            long r30 = r58 + r25
            long r36 = r61 + r25
            r40 = r0
            long r0 = r30 + r28
            float r2 = r13.getFloat(r0)
            r42 = r3
            r43 = r4
            long r3 = r36 + r19
            float r3 = r14.getFloat(r3)
            float r3 = r3 * r12
            float r2 = r2 + r3
            r13.setFloat(r0, r2)
            long r0 = r30 + r6
            float r2 = r13.getFloat(r0)
            long r3 = r36 + r10
            float r3 = r14.getFloat(r3)
            float r3 = r3 * r9
            float r2 = r2 + r3
            r13.setFloat(r0, r2)
            long r25 = r25 + r15
            r0 = r40
            r3 = r42
            r4 = r43
            goto L_0x0294
        L_0x02cd:
            r40 = r0
            r42 = r3
            r43 = r4
            long r4 = r43 + r15
            r2 = r12
            goto L_0x027f
        L_0x02d7:
            r40 = r0
            r42 = r3
            long r4 = r23 + r15
            r2 = r8
            goto L_0x0230
        L_0x02e0:
            r0 = r15
        L_0x02e1:
            int r2 = (r0 > r38 ? 1 : (r0 == r38 ? 0 : -1))
            if (r2 >= 0) goto L_0x0301
            long r2 = r0 * r55
            r4 = r21
        L_0x02e9:
            int r6 = (r4 > r55 ? 1 : (r4 == r55 ? 0 : -1))
            if (r6 >= 0) goto L_0x02ff
            long r6 = r61 + r4
            float r8 = r14.getFloat(r6)
            long r9 = r6 + r2
            float r9 = r14.getFloat(r9)
            float r8 = r8 + r9
            r14.setFloat(r6, r8)
            long r4 = r4 + r15
            goto L_0x02e9
        L_0x02ff:
            long r0 = r0 + r15
            goto L_0x02e1
        L_0x0301:
            r0 = r15
        L_0x0302:
            int r2 = (r0 > r38 ? 1 : (r0 == r38 ? 0 : -1))
            if (r2 >= 0) goto L_0x033e
            long r2 = r51 - r0
            long r4 = r0 * r53
            long r4 = r4 * r49
            long r2 = r2 * r53
            long r2 = r2 * r49
            r6 = r21
        L_0x0312:
            int r8 = (r6 > r53 ? 1 : (r6 == r53 ? 0 : -1))
            if (r8 >= 0) goto L_0x0339
            long r8 = r6 * r49
            long r10 = r61 + r8
            long r8 = r58 + r8
            r19 = r0
            long r0 = r8 + r4
            long r8 = r8 + r2
            float r0 = r13.getFloat(r0)
            float r1 = r13.getFloat(r8)
            long r8 = r10 + r4
            float r12 = r0 - r1
            r14.setFloat(r8, r12)
            long r10 = r10 + r2
            float r0 = r0 + r1
            r14.setFloat(r10, r0)
            long r6 = r6 + r15
            r0 = r19
            goto L_0x0312
        L_0x0339:
            r19 = r0
            long r0 = r19 + r15
            goto L_0x0302
        L_0x033e:
            if (r32 != 0) goto L_0x0341
            return
        L_0x0341:
            int r0 = (r34 > r53 ? 1 : (r34 == r53 ? 0 : -1))
            if (r0 < 0) goto L_0x03d0
            r1 = r15
        L_0x0346:
            int r3 = (r1 > r38 ? 1 : (r1 == r38 ? 0 : -1))
            if (r3 >= 0) goto L_0x03cc
            long r3 = r51 - r1
            long r5 = r1 * r53
            long r5 = r5 * r49
            long r3 = r3 * r53
            long r3 = r3 * r49
            r7 = r21
        L_0x0356:
            int r9 = (r7 > r53 ? 1 : (r7 == r53 ? 0 : -1))
            if (r9 >= 0) goto L_0x03c4
            long r9 = r7 * r49
            r11 = r17
        L_0x035e:
            int r19 = (r11 > r49 ? 1 : (r11 == r49 ? 0 : -1))
            if (r19 >= 0) goto L_0x03b7
            long r19 = r61 + r11
            long r23 = r58 + r11
            long r19 = r19 + r9
            r25 = r0
            r26 = r1
            long r0 = r19 + r5
            r28 = r7
            long r7 = r19 + r3
            long r23 = r23 + r9
            r19 = r9
            long r9 = r23 + r5
            r30 = r5
            long r5 = r23 + r3
            r23 = r3
            long r2 = r9 - r15
            float r2 = r13.getFloat(r2)
            float r3 = r13.getFloat(r9)
            long r9 = r5 - r15
            float r4 = r13.getFloat(r9)
            float r5 = r13.getFloat(r5)
            long r9 = r0 - r15
            float r6 = r2 - r5
            r14.setFloat(r9, r6)
            long r9 = r7 - r15
            float r2 = r2 + r5
            r14.setFloat(r9, r2)
            float r2 = r3 + r4
            r14.setFloat(r0, r2)
            float r3 = r3 - r4
            r14.setFloat(r7, r3)
            long r11 = r11 + r17
            r9 = r19
            r3 = r23
            r0 = r25
            r1 = r26
            r7 = r28
            r5 = r30
            goto L_0x035e
        L_0x03b7:
            r25 = r0
            r26 = r1
            r23 = r3
            r30 = r5
            r28 = r7
            long r7 = r28 + r15
            goto L_0x0356
        L_0x03c4:
            r25 = r0
            r26 = r1
            long r1 = r26 + r15
            goto L_0x0346
        L_0x03cc:
            r25 = r0
            goto L_0x044e
        L_0x03d0:
            r25 = r0
            r0 = r15
        L_0x03d3:
            int r2 = (r0 > r38 ? 1 : (r0 == r38 ? 0 : -1))
            if (r2 >= 0) goto L_0x044e
            long r2 = r51 - r0
            long r4 = r0 * r53
            long r4 = r4 * r49
            long r2 = r2 * r53
            long r2 = r2 * r49
            r6 = r17
        L_0x03e3:
            int r8 = (r6 > r49 ? 1 : (r6 == r49 ? 0 : -1))
            if (r8 >= 0) goto L_0x0449
            long r8 = r61 + r6
            long r10 = r58 + r6
            r19 = r21
        L_0x03ed:
            int r12 = (r19 > r53 ? 1 : (r19 == r53 ? 0 : -1))
            if (r12 >= 0) goto L_0x0440
            long r23 = r19 * r49
            long r26 = r8 + r23
            r28 = r8
            long r8 = r26 + r4
            r30 = r0
            long r0 = r26 + r2
            long r23 = r10 + r23
            r26 = r10
            long r10 = r23 + r4
            r32 = r4
            long r4 = r23 + r2
            r23 = r2
            long r2 = r10 - r15
            float r2 = r13.getFloat(r2)
            float r3 = r13.getFloat(r10)
            long r10 = r4 - r15
            float r10 = r13.getFloat(r10)
            float r4 = r13.getFloat(r4)
            long r11 = r8 - r15
            float r5 = r2 - r4
            r14.setFloat(r11, r5)
            long r11 = r0 - r15
            float r2 = r2 + r4
            r14.setFloat(r11, r2)
            float r2 = r3 + r10
            r14.setFloat(r8, r2)
            float r3 = r3 - r10
            r14.setFloat(r0, r3)
            long r19 = r19 + r15
            r2 = r23
            r10 = r26
            r8 = r28
            r0 = r30
            r4 = r32
            goto L_0x03ed
        L_0x0440:
            r30 = r0
            r23 = r2
            r32 = r4
            long r6 = r6 + r17
            goto L_0x03e3
        L_0x0449:
            r30 = r0
            long r0 = r30 + r15
            goto L_0x03d3
        L_0x044e:
            r5 = r60
            r6 = r61
            r8 = r57
            r0 = r49
            r9 = r58
            r11 = r55
            pl.edu.icm.jlargearrays.LargeArrayUtils.arraycopy((pl.edu.icm.jlargearrays.FloatLargeArray) r5, (long) r6, (pl.edu.icm.jlargearrays.FloatLargeArray) r8, (long) r9, (long) r11)
            r2 = r15
        L_0x045e:
            int r4 = (r2 > r51 ? 1 : (r2 == r51 ? 0 : -1))
            if (r4 >= 0) goto L_0x047d
            long r4 = r2 * r53
            long r4 = r4 * r0
            r6 = r21
        L_0x0467:
            int r8 = (r6 > r53 ? 1 : (r6 == r53 ? 0 : -1))
            if (r8 >= 0) goto L_0x047b
            long r8 = r6 * r0
            long r8 = r8 + r4
            long r10 = r58 + r8
            long r8 = r61 + r8
            float r8 = r14.getFloat(r8)
            r13.setFloat(r10, r8)
            long r6 = r6 + r15
            goto L_0x0467
        L_0x047b:
            long r2 = r2 + r15
            goto L_0x045e
        L_0x047d:
            if (r25 > 0) goto L_0x0500
            long r2 = -r0
            r4 = r15
        L_0x0481:
            int r6 = (r4 > r51 ? 1 : (r4 == r51 ? 0 : -1))
            if (r6 >= 0) goto L_0x04fc
            long r2 = r2 + r0
            long r6 = r2 - r15
            long r8 = r4 * r53
            long r8 = r8 * r0
            r10 = r17
        L_0x048d:
            int r12 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r12 >= 0) goto L_0x04f1
            long r6 = r6 + r17
            r55 = r2
            long r2 = r6 + r63
            r12 = r48
            r19 = r6
            pl.edu.icm.jlargearrays.FloatLargeArray r6 = r12.wtable_rl
            r23 = r4
            long r4 = r2 - r15
            float r4 = r6.getFloat(r4)
            pl.edu.icm.jlargearrays.FloatLargeArray r5 = r12.wtable_rl
            float r2 = r5.getFloat(r2)
            long r5 = r58 + r10
            long r25 = r61 + r10
            r27 = r21
        L_0x04b1:
            int r3 = (r27 > r53 ? 1 : (r27 == r53 ? 0 : -1))
            if (r3 >= 0) goto L_0x04e4
            long r29 = r27 * r0
            long r29 = r29 + r8
            r31 = r8
            long r7 = r5 + r29
            r33 = r5
            long r5 = r25 + r29
            long r0 = r5 - r15
            float r0 = r14.getFloat(r0)
            float r1 = r14.getFloat(r5)
            long r5 = r7 - r15
            float r3 = r4 * r0
            float r9 = r2 * r1
            float r3 = r3 - r9
            r13.setFloat(r5, r3)
            float r1 = r1 * r4
            float r0 = r0 * r2
            float r1 = r1 + r0
            r13.setFloat(r7, r1)
            long r27 = r27 + r15
            r0 = r49
            r8 = r31
            r5 = r33
            goto L_0x04b1
        L_0x04e4:
            r31 = r8
            long r10 = r10 + r17
            r0 = r49
            r2 = r55
            r6 = r19
            r4 = r23
            goto L_0x048d
        L_0x04f1:
            r12 = r48
            r55 = r2
            r23 = r4
            long r4 = r23 + r15
            r0 = r49
            goto L_0x0481
        L_0x04fc:
            r12 = r48
            goto L_0x057a
        L_0x0500:
            r12 = r48
            long r2 = -r0
            r4 = r15
        L_0x0504:
            int r6 = (r4 > r51 ? 1 : (r4 == r51 ? 0 : -1))
            if (r6 >= 0) goto L_0x057a
            long r2 = r2 + r0
            long r6 = r4 * r53
            long r6 = r6 * r0
            r8 = r21
        L_0x050e:
            int r10 = (r8 > r53 ? 1 : (r8 == r53 ? 0 : -1))
            if (r10 >= 0) goto L_0x0571
            long r10 = r2 - r15
            long r19 = r8 * r0
            long r19 = r19 + r6
            r23 = r17
        L_0x051a:
            int r25 = (r23 > r0 ? 1 : (r23 == r0 ? 0 : -1))
            if (r25 >= 0) goto L_0x0567
            long r10 = r10 + r17
            long r0 = r10 + r63
            r55 = r2
            pl.edu.icm.jlargearrays.FloatLargeArray r2 = r12.wtable_rl
            r25 = r6
            long r6 = r0 - r15
            float r2 = r2.getFloat(r6)
            pl.edu.icm.jlargearrays.FloatLargeArray r3 = r12.wtable_rl
            float r0 = r3.getFloat(r0)
            long r6 = r58 + r23
            long r27 = r61 + r23
            long r6 = r6 + r19
            r29 = r10
            long r10 = r27 + r19
            r27 = r4
            long r3 = r10 - r15
            float r1 = r14.getFloat(r3)
            float r3 = r14.getFloat(r10)
            long r4 = r6 - r15
            float r10 = r2 * r1
            float r11 = r0 * r3
            float r10 = r10 - r11
            r13.setFloat(r4, r10)
            float r2 = r2 * r3
            float r0 = r0 * r1
            float r2 = r2 + r0
            r13.setFloat(r6, r2)
            long r23 = r23 + r17
            r0 = r49
            r2 = r55
            r6 = r25
            r4 = r27
            r10 = r29
            goto L_0x051a
        L_0x0567:
            r55 = r2
            r27 = r4
            r25 = r6
            long r8 = r8 + r15
            r0 = r49
            goto L_0x050e
        L_0x0571:
            r55 = r2
            r27 = r4
            long r4 = r27 + r15
            r0 = r49
            goto L_0x0504
        L_0x057a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.FloatFFT_1D.radbg(long, long, long, long, pl.edu.icm.jlargearrays.FloatLargeArray, long, pl.edu.icm.jlargearrays.FloatLargeArray, long, long):void");
    }

    /* access modifiers changed from: package-private */
    public void cfftf(float[] fArr, int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8 = this.n;
        int i9 = i8 * 2;
        float[] fArr2 = new float[i9];
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
                    passf2(i22, i18, fArr, i, fArr2, 0, i17, i2);
                } else {
                    passf2(i22, i18, fArr2, 0, fArr, i, i17, i2);
                }
            } else if (i19 == 3) {
                i5 = i19;
                i4 = i15;
                i3 = i14;
                i6 = i13;
                i7 = i12;
                if (i16 == 0) {
                    passf3(i22, i18, fArr, i, fArr2, 0, i17, i2);
                } else {
                    passf3(i22, i18, fArr2, 0, fArr, i, i17, i2);
                }
            } else if (i19 == i10) {
                i5 = i19;
                i4 = i15;
                i3 = i14;
                i6 = i13;
                i7 = i12;
                if (i16 == 0) {
                    passf4(i22, i18, fArr, i, fArr2, 0, i17, i2);
                } else {
                    passf4(i22, i18, fArr2, 0, fArr, i, i17, i2);
                }
            } else if (i19 != 5) {
                if (i16 == 0) {
                    i5 = i19;
                    i4 = i15;
                    i3 = i14;
                    i6 = i13;
                    i7 = i12;
                    passfg(iArr, i22, i19, i18, i23, fArr, i, fArr2, 0, i17, i2);
                } else {
                    i5 = i19;
                    i4 = i15;
                    i3 = i14;
                    i6 = i13;
                    i7 = i12;
                    passfg(iArr, i22, i5, i18, i23, fArr2, 0, fArr, i, i17, i2);
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
                    passf5(i22, i18, fArr, i, fArr2, 0, i17, i2);
                } else {
                    passf5(i22, i18, fArr2, 0, fArr, i, i17, i2);
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
            System.arraycopy(fArr2, i24, fArr, i, i9);
        }
    }

    /* access modifiers changed from: package-private */
    public void cfftf(FloatLargeArray floatLargeArray, long j, int i) {
        FloatLargeArray floatLargeArray2;
        int i2;
        long j2;
        long j3;
        int i3;
        long j4;
        long j5;
        int i4;
        FloatLargeArray floatLargeArray3;
        FloatFFT_1D floatFFT_1D = this;
        int i5 = i;
        long j6 = floatFFT_1D.nl * 2;
        FloatLargeArray floatLargeArray4 = new FloatLargeArray(j6);
        long j7 = floatFFT_1D.nl * 4;
        int[] iArr = {0};
        long j8 = (long) floatFFT_1D.wtablel.getFloat(j7 + 1);
        long j9 = 2;
        long j10 = j6;
        long j11 = 1;
        long j12 = 0;
        while (j9 <= j8 + 1) {
            int i6 = (int) floatFFT_1D.wtablel.getFloat(j9 + j7);
            long j13 = (long) i6;
            long j14 = j13 * j11;
            long j15 = floatFFT_1D.nl / j14;
            long j16 = j15 + j15;
            long j17 = j16 * j11;
            if (i6 == 2) {
                j4 = j8;
                j5 = j6;
                i4 = i6;
                i3 = i5;
                floatLargeArray3 = floatLargeArray4;
                if (j12 == 0) {
                    passf2(j16, j11, floatLargeArray, j, floatLargeArray3, 0, j10, (long) i3);
                } else {
                    passf2(j16, j11, floatLargeArray3, 0, floatLargeArray, j, j10, (long) i3);
                }
            } else if (i6 == 3) {
                j4 = j8;
                j5 = j6;
                i4 = i6;
                i3 = i5;
                floatLargeArray3 = floatLargeArray4;
                if (j12 == 0) {
                    passf3(j16, j11, floatLargeArray, j, floatLargeArray3, 0, j10, (long) i3);
                } else {
                    passf3(j16, j11, floatLargeArray3, 0, floatLargeArray, j, j10, (long) i3);
                }
            } else if (i6 == 4) {
                j4 = j8;
                j5 = j6;
                i4 = i6;
                i3 = i5;
                floatLargeArray3 = floatLargeArray4;
                if (j12 == 0) {
                    passf4(j16, j11, floatLargeArray, j, floatLargeArray3, 0, j10, i);
                } else {
                    passf4(j16, j11, floatLargeArray3, 0, floatLargeArray, j, j10, i);
                }
            } else if (i6 != 5) {
                if (j12 == 0) {
                    j3 = j8;
                    j2 = j6;
                    i2 = i6;
                    floatLargeArray2 = floatLargeArray4;
                    passfg(iArr, j16, j13, j11, j17, floatLargeArray, j, floatLargeArray2, 0, j10, (long) i5);
                } else {
                    j3 = j8;
                    j2 = j6;
                    i2 = i6;
                    floatLargeArray2 = floatLargeArray4;
                    passfg(iArr, j16, j13, j11, j17, floatLargeArray2, 0, floatLargeArray, j, j10, (long) i);
                }
                if (iArr[0] != 0) {
                    j12 = 1 - j12;
                }
                i3 = i;
                j10 += ((long) (i2 - 1)) * j16;
                j9++;
                floatFFT_1D = this;
                i5 = i3;
                j11 = j14;
                j8 = j3;
                j6 = j2;
                floatLargeArray4 = floatLargeArray2;
            } else {
                j4 = j8;
                j5 = j6;
                i4 = i6;
                floatLargeArray3 = floatLargeArray4;
                if (j12 == 0) {
                    i3 = i;
                    passf5(j16, j11, floatLargeArray, j, floatLargeArray3, 0, j10, (long) i3);
                } else {
                    i3 = i;
                    passf5(j16, j11, floatLargeArray3, 0, floatLargeArray, j, j10, (long) i3);
                }
            }
            j12 = 1 - j12;
            j10 += ((long) (i2 - 1)) * j16;
            j9++;
            floatFFT_1D = this;
            i5 = i3;
            j11 = j14;
            j8 = j3;
            j6 = j2;
            floatLargeArray4 = floatLargeArray2;
        }
        long j18 = j6;
        FloatLargeArray floatLargeArray5 = floatLargeArray4;
        if (j12 != 0) {
            LargeArrayUtils.arraycopy(floatLargeArray5, 0, floatLargeArray, j, j18);
        }
    }

    /* access modifiers changed from: package-private */
    public void passf2(int i, int i2, float[] fArr, int i3, float[] fArr2, int i4, int i5, int i6) {
        int i7 = i;
        int i8 = i2;
        int i9 = i7 * i8;
        if (i7 <= 2) {
            for (int i10 = 0; i10 < i8; i10++) {
                int i11 = i10 * i7;
                int i12 = i3 + (i11 * 2);
                int i13 = i12 + i7;
                float f = fArr[i12];
                float f2 = fArr[i12 + 1];
                float f3 = fArr[i13];
                float f4 = fArr[i13 + 1];
                int i14 = i4 + i11;
                int i15 = i14 + i9;
                fArr2[i14] = f + f3;
                fArr2[i14 + 1] = f2 + f4;
                fArr2[i15] = f - f3;
                fArr2[i15 + 1] = f2 - f4;
            }
        } else {
            for (int i16 = 0; i16 < i8; i16++) {
                for (int i17 = 0; i17 < i7 - 1; i17 += 2) {
                    int i18 = i16 * i7;
                    int i19 = i3 + i17 + (i18 * 2);
                    int i20 = i19 + i7;
                    float f5 = fArr[i19];
                    float f6 = fArr[i19 + 1];
                    float f7 = fArr[i20];
                    float f8 = fArr[i20 + 1];
                    int i21 = i17 + i5;
                    float[] fArr3 = this.wtable;
                    float f9 = fArr3[i21];
                    float f10 = ((float) i6) * fArr3[i21 + 1];
                    float f11 = f5 - f7;
                    float f12 = f6 - f8;
                    int i22 = i4 + i17 + i18;
                    int i23 = i22 + i9;
                    fArr2[i22] = f5 + f7;
                    fArr2[i22 + 1] = f6 + f8;
                    fArr2[i23] = (f9 * f11) - (f10 * f12);
                    fArr2[i23 + 1] = (f9 * f12) + (f10 * f11);
                }
                int i24 = i6;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void passf2(long j, long j2, FloatLargeArray floatLargeArray, long j3, FloatLargeArray floatLargeArray2, long j4, long j5, long j6) {
        FloatLargeArray floatLargeArray3 = floatLargeArray;
        FloatLargeArray floatLargeArray4 = floatLargeArray2;
        long j7 = j * j2;
        long j8 = 2;
        long j9 = 1;
        if (j <= 2) {
            long j10 = 0;
            while (j10 < j2) {
                long j11 = j10 * j;
                long j12 = j3 + (j11 * j8);
                long j13 = j12 + j;
                float f = floatLargeArray3.getFloat(j12);
                float f2 = floatLargeArray3.getFloat(j12 + j9);
                float f3 = floatLargeArray3.getFloat(j13);
                float f4 = floatLargeArray3.getFloat(j13 + j9);
                long j14 = j4 + j11;
                long j15 = j14 + j7;
                floatLargeArray4.setFloat(j14, f + f3);
                floatLargeArray4.setFloat(j14 + 1, f2 + f4);
                floatLargeArray4.setFloat(j15, f - f3);
                floatLargeArray4.setFloat(j15 + 1, f2 - f4);
                j10++;
                j9 = 1;
                j8 = 2;
            }
            return;
        }
        long j16 = 0;
        while (j16 < j2) {
            long j17 = 0;
            while (j17 < j - 1) {
                long j18 = j16 * j;
                long j19 = j3 + j17 + (j18 * 2);
                long j20 = j19 + j;
                float f5 = floatLargeArray3.getFloat(j19);
                float f6 = floatLargeArray3.getFloat(j19 + 1);
                float f7 = floatLargeArray3.getFloat(j20);
                float f8 = floatLargeArray3.getFloat(j20 + 1);
                long j21 = j16;
                long j22 = j17 + j5;
                float f9 = this.wtablel.getFloat(j22);
                float f10 = ((float) j6) * this.wtablel.getFloat(j22 + 1);
                float f11 = f5 - f7;
                float f12 = f6 - f8;
                long j23 = j4 + j17 + j18;
                long j24 = j23 + j7;
                float f13 = f5 + f7;
                FloatLargeArray floatLargeArray5 = floatLargeArray2;
                floatLargeArray5.setFloat(j23, f13);
                floatLargeArray5.setFloat(j23 + 1, f6 + f8);
                floatLargeArray5.setFloat(j24, (f9 * f11) - (f10 * f12));
                floatLargeArray5.setFloat(j24 + 1, (f12 * f9) + (f10 * f11));
                j17 += 2;
                floatLargeArray3 = floatLargeArray;
                floatLargeArray4 = floatLargeArray5;
                j16 = j21;
            }
            j16++;
            floatLargeArray3 = floatLargeArray;
            floatLargeArray4 = floatLargeArray4;
        }
    }

    /* access modifiers changed from: package-private */
    public void passf3(int i, int i2, float[] fArr, int i3, float[] fArr2, int i4, int i5, int i6) {
        int i7 = i;
        int i8 = i2;
        int i9 = i6;
        int i10 = i5 + i7;
        int i11 = i8 * i7;
        float f = 0.8660254f;
        float f2 = -0.5f;
        int i12 = 2;
        if (i7 == 2) {
            int i13 = 1;
            while (i13 <= i8) {
                int i14 = i3 + (((i13 * 3) - 2) * i7);
                int i15 = i14 + i7;
                int i16 = i14 - i7;
                float f3 = fArr[i14];
                float f4 = fArr[i14 + 1];
                float f5 = fArr[i15];
                float f6 = fArr[i15 + 1];
                float f7 = fArr[i16];
                float f8 = fArr[i16 + 1];
                float f9 = f3 + f5;
                float f10 = f7 + (f9 * f2);
                float f11 = f4 + f6;
                float f12 = f8 + (f11 * f2);
                float f13 = ((float) i9) * 0.8660254f;
                float f14 = (f3 - f5) * f13;
                float f15 = f13 * (f4 - f6);
                int i17 = i4 + ((i13 - 1) * i7);
                int i18 = i17 + i11;
                int i19 = i18 + i11;
                fArr2[i17] = f7 + f9;
                fArr2[i17 + 1] = f8 + f11;
                fArr2[i18] = f10 - f15;
                fArr2[i18 + 1] = f12 + f14;
                fArr2[i19] = f10 + f15;
                fArr2[i19 + 1] = f12 - f14;
                i13++;
                f2 = -0.5f;
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
                    float f16 = fArr[i24];
                    float f17 = fArr[i24 + 1];
                    float f18 = fArr[i25];
                    float f19 = fArr[i25 + 1];
                    float f20 = fArr[i26];
                    float f21 = fArr[i26 + 1];
                    float f22 = f16 + f18;
                    float f23 = f20 + (f22 * -0.5f);
                    float f24 = f17 + f19;
                    float f25 = f21 + (f24 * -0.5f);
                    float f26 = (float) i9;
                    float f27 = f26 * f;
                    float f28 = (f16 - f18) * f27;
                    float f29 = f27 * (f17 - f19);
                    float f30 = f23 - f29;
                    float f31 = f23 + f29;
                    float f32 = f25 + f28;
                    float f33 = f25 - f28;
                    int i27 = i23 + i5;
                    int i28 = i23 + i10;
                    float[] fArr3 = this.wtable;
                    float f34 = fArr3[i27];
                    float f35 = fArr3[i27 + 1] * f26;
                    float f36 = fArr3[i28];
                    float f37 = f26 * fArr3[i28 + 1];
                    int i29 = i23 + i22;
                    int i30 = i29 + i11;
                    int i31 = i30 + i11;
                    fArr2[i29] = f20 + f22;
                    fArr2[i29 + 1] = f21 + f24;
                    fArr2[i30] = (f34 * f30) - (f35 * f32);
                    fArr2[i30 + 1] = (f34 * f32) + (f35 * f30);
                    fArr2[i31] = (f36 * f31) - (f37 * f33);
                    fArr2[i31 + 1] = (f36 * f33) + (f37 * f31);
                    i23 += 2;
                    i7 = i;
                    f = 0.8660254f;
                }
                i20++;
                i7 = i;
                f = 0.8660254f;
                i12 = 2;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void passf3(long j, long j2, FloatLargeArray floatLargeArray, long j3, FloatLargeArray floatLargeArray2, long j4, long j5, long j6) {
        FloatLargeArray floatLargeArray3 = floatLargeArray;
        FloatLargeArray floatLargeArray4 = floatLargeArray2;
        long j7 = j6;
        long j8 = j5 + j;
        long j9 = j2 * j;
        long j10 = 2;
        long j11 = 3;
        float f = -0.5f;
        if (j == 2) {
            long j12 = 1;
            while (j12 <= j2) {
                long j13 = j3 + (((j12 * j11) - j10) * j);
                long j14 = j13 - j;
                float f2 = floatLargeArray3.getFloat(j13);
                float f3 = floatLargeArray3.getFloat(j13 + 1);
                long j15 = j14;
                long j16 = j13 + j;
                float f4 = floatLargeArray3.getFloat(j16);
                float f5 = floatLargeArray3.getFloat(j16 + 1);
                long j17 = j15;
                float f6 = floatLargeArray3.getFloat(j17);
                long j18 = j9;
                float f7 = floatLargeArray3.getFloat(j17 + 1);
                float f8 = f2 + f4;
                float f9 = f6 + (f8 * f);
                float f10 = f3 + f5;
                float f11 = f7 + (f10 * f);
                float f12 = ((float) j7) * 0.8660254f;
                float f13 = (f2 - f4) * f12;
                float f14 = f12 * (f3 - f5);
                long j19 = j4 + ((j12 - 1) * j);
                long j20 = j19 + j18;
                long j21 = j12;
                long j22 = j20 + j18;
                floatLargeArray4.setFloat(j19, floatLargeArray3.getFloat(j17) + f8);
                floatLargeArray4.setFloat(j19 + 1, f7 + f10);
                floatLargeArray4.setFloat(j20, f9 - f14);
                floatLargeArray4.setFloat(j20 + 1, f11 + f13);
                floatLargeArray4.setFloat(j22, f9 + f14);
                floatLargeArray4.setFloat(j22 + 1, f11 - f13);
                j12 = j21 + 1;
                j7 = j6;
                j9 = j18;
                j10 = 2;
                j11 = 3;
                f = -0.5f;
            }
            return;
        }
        long j23 = j9;
        long j24 = 1;
        while (j24 <= j2) {
            long j25 = j3 + (((j24 * 3) - 2) * j);
            long j26 = j4 + ((j24 - 1) * j);
            long j27 = 0;
            while (j27 < j - 1) {
                long j28 = j27 + j25;
                long j29 = j25;
                long j30 = j28 + j;
                long j31 = j24;
                long j32 = j28 - j;
                float f15 = floatLargeArray3.getFloat(j28);
                float f16 = floatLargeArray3.getFloat(j28 + 1);
                float f17 = floatLargeArray3.getFloat(j30);
                float f18 = floatLargeArray3.getFloat(j30 + 1);
                float f19 = floatLargeArray3.getFloat(j32);
                float f20 = floatLargeArray3.getFloat(j32 + 1);
                float f21 = f15 + f17;
                float f22 = f19 + (f21 * -0.5f);
                float f23 = f16 + f18;
                float f24 = f20 + (f23 * -0.5f);
                float f25 = f20;
                float f26 = (float) j6;
                float f27 = f26 * 0.8660254f;
                float f28 = (f15 - f17) * f27;
                float f29 = f27 * (f16 - f18);
                float f30 = f22 - f29;
                float f31 = f22 + f29;
                float f32 = f24 + f28;
                float f33 = f24 - f28;
                long j33 = j27 + j5;
                float f34 = f32;
                long j34 = j27 + j8;
                long j35 = j8;
                float f35 = this.wtablel.getFloat(j33);
                float f36 = this.wtablel.getFloat(j33 + 1) * f26;
                float f37 = this.wtablel.getFloat(j34);
                float f38 = f26 * this.wtablel.getFloat(j34 + 1);
                long j36 = j27 + j26;
                long j37 = j26;
                long j38 = j36 + j23;
                long j39 = j27;
                long j40 = j38 + j23;
                float f39 = f19 + f21;
                FloatLargeArray floatLargeArray5 = floatLargeArray2;
                floatLargeArray5.setFloat(j36, f39);
                floatLargeArray5.setFloat(j36 + 1, f25 + f23);
                floatLargeArray5.setFloat(j38, (f35 * f30) - (f36 * f34));
                floatLargeArray5.setFloat(j38 + 1, (f35 * f34) + (f36 * f30));
                floatLargeArray5.setFloat(j40, (f37 * f31) - (f38 * f33));
                floatLargeArray5.setFloat(j40 + 1, (f37 * f33) + (f38 * f31));
                j27 = j39 + 2;
                floatLargeArray3 = floatLargeArray;
                floatLargeArray4 = floatLargeArray5;
                j25 = j29;
                j24 = j31;
                j8 = j35;
                j26 = j37;
            }
            floatLargeArray3 = floatLargeArray;
            floatLargeArray4 = floatLargeArray4;
            j24++;
            j8 = j8;
        }
    }

    /* access modifiers changed from: package-private */
    public void passf4(int i, int i2, float[] fArr, int i3, float[] fArr2, int i4, int i5, int i6) {
        int i7 = i;
        int i8 = i2;
        int i9 = i6;
        int i10 = i5 + i7;
        int i11 = i10 + i7;
        int i12 = i8 * i7;
        if (i7 == 2) {
            for (int i13 = 0; i13 < i8; i13++) {
                int i14 = i13 * i7;
                int i15 = i3 + (i14 * 4);
                int i16 = i15 + 1;
                int i17 = i16 + i7;
                int i18 = i17 + i7;
                int i19 = i18 + i7;
                float f = fArr[i15];
                float f2 = fArr[i16];
                float f3 = fArr[i17 - 1];
                float f4 = fArr[i17];
                float f5 = fArr[i18 - 1];
                float f6 = fArr[i18];
                float f7 = fArr[i19 - 1];
                float f8 = fArr[i19];
                float f9 = f2 - f6;
                float f10 = f2 + f6;
                float f11 = f8 - f4;
                float f12 = f4 + f8;
                float f13 = f - f5;
                float f14 = f + f5;
                float f15 = f3 - f7;
                float f16 = f3 + f7;
                int i20 = i4 + i14;
                int i21 = i20 + i12;
                int i22 = i21 + i12;
                int i23 = i22 + i12;
                fArr2[i20] = f14 + f16;
                fArr2[i20 + 1] = f10 + f12;
                float f17 = (float) i9;
                float f18 = f11 * f17;
                fArr2[i21] = f13 + f18;
                float f19 = f17 * f15;
                fArr2[i21 + 1] = f9 + f19;
                fArr2[i22] = f14 - f16;
                fArr2[i22 + 1] = f10 - f12;
                fArr2[i23] = f13 - f18;
                fArr2[i23 + 1] = f9 - f19;
            }
        } else {
            int i24 = 0;
            while (i24 < i8) {
                int i25 = i24 * i7;
                int i26 = i3 + 1 + (i25 * 4);
                int i27 = 0;
                while (i27 < i7 - 1) {
                    int i28 = i27 + i26;
                    int i29 = i28 + i7;
                    int i30 = i29 + i7;
                    int i31 = i30 + i7;
                    float f20 = fArr[i28 - 1];
                    float f21 = fArr[i28];
                    float f22 = fArr[i29 - 1];
                    float f23 = fArr[i29];
                    float f24 = fArr[i30 - 1];
                    float f25 = fArr[i30];
                    float f26 = fArr[i31 - 1];
                    float f27 = fArr[i31];
                    float f28 = f21 - f25;
                    float f29 = f21 + f25;
                    float f30 = f23 + f27;
                    float f31 = f27 - f23;
                    float f32 = f20 - f24;
                    float f33 = f20 + f24;
                    float f34 = f22 - f26;
                    float f35 = f22 + f26;
                    float f36 = f33 - f35;
                    float f37 = f29 - f30;
                    float f38 = (float) i9;
                    float f39 = f31 * f38;
                    float f40 = f32 + f39;
                    float f41 = f32 - f39;
                    float f42 = f34 * f38;
                    float f43 = f28 + f42;
                    float f44 = f28 - f42;
                    int i32 = i27 + i5;
                    int i33 = i27 + i10;
                    int i34 = i27 + i11;
                    float[] fArr3 = this.wtable;
                    float f45 = fArr3[i32];
                    float f46 = fArr3[i32 + 1] * f38;
                    float f47 = fArr3[i33];
                    float f48 = fArr3[i33 + 1] * f38;
                    float f49 = fArr3[i34];
                    float f50 = f38 * fArr3[i34 + 1];
                    int i35 = i4 + i27 + i25;
                    int i36 = i35 + i12;
                    int i37 = i36 + i12;
                    int i38 = i37 + i12;
                    fArr2[i35] = f33 + f35;
                    fArr2[i35 + 1] = f29 + f30;
                    fArr2[i36] = (f45 * f40) - (f46 * f43);
                    fArr2[i36 + 1] = (f45 * f43) + (f46 * f40);
                    fArr2[i37] = (f47 * f36) - (f48 * f37);
                    fArr2[i37 + 1] = (f47 * f37) + (f48 * f36);
                    fArr2[i38] = (f49 * f41) - (f50 * f44);
                    fArr2[i38 + 1] = (f49 * f44) + (f50 * f41);
                    i27 += 2;
                    i7 = i;
                    int i39 = i2;
                }
                i24++;
                i7 = i;
                i8 = i2;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void passf4(long j, long j2, FloatLargeArray floatLargeArray, long j3, FloatLargeArray floatLargeArray2, long j4, long j5, int i) {
        FloatLargeArray floatLargeArray3 = floatLargeArray;
        FloatLargeArray floatLargeArray4 = floatLargeArray2;
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
                float f = floatLargeArray3.getFloat(j13);
                float f2 = floatLargeArray3.getFloat(j14);
                float f3 = floatLargeArray3.getFloat(j15 - 1);
                float f4 = floatLargeArray3.getFloat(j15);
                float f5 = floatLargeArray3.getFloat(j16 - 1);
                float f6 = floatLargeArray3.getFloat(j16);
                float f7 = floatLargeArray3.getFloat(j18 - 1);
                float f8 = floatLargeArray3.getFloat(j18);
                float f9 = f2 - f6;
                float f10 = f2 + f6;
                float f11 = f8 - f4;
                float f12 = f4 + f8;
                float f13 = f - f5;
                float f14 = f + f5;
                float f15 = f3 - f7;
                float f16 = f3 + f7;
                long j19 = j4 + j12;
                long j20 = j19 + j17;
                long j21 = j20 + j17;
                float f17 = f15;
                FloatLargeArray floatLargeArray5 = floatLargeArray2;
                floatLargeArray5.setFloat(j19, f14 + f16);
                floatLargeArray5.setFloat(j19 + 1, f10 + f12);
                float f18 = (float) i;
                float f19 = f11 * f18;
                long j22 = j20;
                floatLargeArray5.setFloat(j22, f13 + f19);
                float f20 = f18 * f17;
                floatLargeArray5.setFloat(j22 + 1, f9 + f20);
                floatLargeArray5.setFloat(j21, f14 - f16);
                floatLargeArray5.setFloat(j21 + 1, f10 - f12);
                long j23 = j21 + j17;
                floatLargeArray5.setFloat(j23, f13 - f19);
                floatLargeArray5.setFloat(j23 + 1, f9 - f20);
                j11++;
                floatLargeArray3 = floatLargeArray;
                FloatLargeArray floatLargeArray6 = floatLargeArray5;
                j8 = j17;
                j10 = 4;
            }
        } else {
            FloatLargeArray floatLargeArray7 = floatLargeArray4;
            long j24 = j8;
            long j25 = 0;
            while (j25 < j2) {
                long j26 = j25 * j;
                long j27 = j3 + 1 + (j26 * 4);
                long j28 = 0;
                while (j28 < j - 1) {
                    long j29 = j28 + j27;
                    long j30 = j27;
                    long j31 = j29 + j;
                    long j32 = j25;
                    long j33 = j31 + j;
                    long j34 = j33 + j;
                    long j35 = j26;
                    FloatLargeArray floatLargeArray8 = floatLargeArray;
                    float f21 = floatLargeArray8.getFloat(j29 - 1);
                    float f22 = floatLargeArray8.getFloat(j29);
                    float f23 = floatLargeArray8.getFloat(j31 - 1);
                    float f24 = floatLargeArray8.getFloat(j31);
                    float f25 = floatLargeArray8.getFloat(j33 - 1);
                    float f26 = floatLargeArray8.getFloat(j33);
                    long j36 = j7;
                    float f27 = floatLargeArray8.getFloat(j34 - 1);
                    float f28 = floatLargeArray8.getFloat(j34);
                    float f29 = f22 - f26;
                    float f30 = f22 + f26;
                    float f31 = f24 + f28;
                    float f32 = f28 - f24;
                    float f33 = f21 - f25;
                    float f34 = f21 + f25;
                    float f35 = f23 - f27;
                    float f36 = f23 + f27;
                    float f37 = (float) i2;
                    float f38 = f32 * f37;
                    float f39 = f33 + f38;
                    float f40 = f33 - f38;
                    float f41 = f35 * f37;
                    float f42 = f29 + f41;
                    long j37 = j28 + j5;
                    float f43 = f40;
                    long j38 = j28 + j6;
                    long j39 = j6;
                    long j40 = j28 + j36;
                    float f44 = f29 - f41;
                    float f45 = f30 - f31;
                    float f46 = this.wtablel.getFloat(j37);
                    float f47 = f34 - f36;
                    float f48 = this.wtablel.getFloat(j37 + 1) * f37;
                    float f49 = this.wtablel.getFloat(j38);
                    float f50 = this.wtablel.getFloat(j38 + 1) * f37;
                    float f51 = this.wtablel.getFloat(j40);
                    float f52 = f37 * this.wtablel.getFloat(j40 + 1);
                    long j41 = j4 + j28 + j35;
                    float f53 = f51;
                    long j42 = j41 + j24;
                    float f54 = f49;
                    float f55 = f50;
                    long j43 = j42 + j24;
                    long j44 = j42;
                    long j45 = j43 + j24;
                    float f56 = f34 + f36;
                    FloatLargeArray floatLargeArray9 = floatLargeArray2;
                    floatLargeArray9.setFloat(j41, f56);
                    floatLargeArray9.setFloat(j41 + 1, f30 + f31);
                    long j46 = j44;
                    floatLargeArray9.setFloat(j46, (f46 * f39) - (f48 * f42));
                    floatLargeArray9.setFloat(j46 + 1, (f46 * f42) + (f48 * f39));
                    floatLargeArray9.setFloat(j43, (f54 * f47) - (f55 * f45));
                    floatLargeArray9.setFloat(j43 + 1, (f54 * f45) + (f55 * f47));
                    floatLargeArray9.setFloat(j45, (f53 * f43) - (f52 * f44));
                    floatLargeArray9.setFloat(j45 + 1, (f53 * f44) + (f52 * f43));
                    j28 += 2;
                    i2 = i;
                    floatLargeArray7 = floatLargeArray9;
                    j27 = j30;
                    j26 = j35;
                    j7 = j36;
                    j6 = j39;
                    j9 = 2;
                    j25 = j32;
                }
                long j47 = j25;
                long j48 = j6;
                long j49 = j9;
                FloatLargeArray floatLargeArray10 = floatLargeArray7;
                j7 = j7;
                j9 = j49;
                j25 = j47 + 1;
                i2 = i;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void passf5(int i, int i2, float[] fArr, int i3, float[] fArr2, int i4, int i5, int i6) {
        int i7 = i;
        int i8 = i2;
        int i9 = i6;
        int i10 = i5 + i7;
        int i11 = i10 + i7;
        int i12 = i11 + i7;
        int i13 = i8 * i7;
        float f = 0.58778524f;
        float f2 = 0.95105654f;
        float f3 = -0.809017f;
        float f4 = 0.309017f;
        if (i7 == 2) {
            int i14 = 1;
            while (i14 <= i8) {
                int i15 = i3 + (((i14 * 5) - 4) * i7);
                int i16 = i15 + 1;
                int i17 = i16 + i7;
                int i18 = i16 - i7;
                int i19 = i17 + i7;
                int i20 = i19 + i7;
                float f5 = fArr[i15];
                float f6 = fArr[i16];
                float f7 = fArr[i17 - 1];
                float f8 = fArr[i17];
                float f9 = fArr[i18 - 1];
                float f10 = fArr[i18];
                float f11 = fArr[i19 - 1];
                float f12 = fArr[i19];
                float f13 = fArr[i20 - 1];
                float f14 = fArr[i20];
                float f15 = f6 - f14;
                float f16 = f6 + f14;
                float f17 = f8 - f12;
                float f18 = f8 + f12;
                float f19 = f5 - f13;
                float f20 = f5 + f13;
                float f21 = f7 - f11;
                float f22 = f7 + f11;
                float f23 = f9 + (f20 * 0.309017f) + (f22 * f3);
                float f24 = f10 + (f16 * 0.309017f) + (f18 * f3);
                float f25 = f9 + (f20 * f3) + (f22 * 0.309017f);
                float f26 = f10 + (f16 * f3) + (f18 * 0.309017f);
                float f27 = (float) i9;
                float f28 = ((f19 * 0.95105654f) + (f21 * 0.58778524f)) * f27;
                float f29 = ((f15 * 0.95105654f) + (f17 * 0.58778524f)) * f27;
                float f30 = ((f19 * 0.58778524f) - (f21 * 0.95105654f)) * f27;
                float f31 = f27 * ((f15 * 0.58778524f) - (f17 * 0.95105654f));
                int i21 = i4 + ((i14 - 1) * i7);
                int i22 = i21 + i13;
                int i23 = i22 + i13;
                int i24 = i23 + i13;
                int i25 = i24 + i13;
                fArr2[i21] = f9 + f20 + f22;
                fArr2[i21 + 1] = f10 + f16 + f18;
                fArr2[i22] = f23 - f29;
                fArr2[i22 + 1] = f24 + f28;
                fArr2[i23] = f25 - f31;
                fArr2[i23 + 1] = f26 + f30;
                fArr2[i24] = f25 + f31;
                fArr2[i24 + 1] = f26 - f30;
                fArr2[i25] = f23 + f29;
                fArr2[i25 + 1] = f24 - f28;
                i14++;
                f3 = -0.809017f;
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
                    float f32 = fArr[i30 - 1];
                    float f33 = fArr[i30];
                    float f34 = fArr[i31 - 1];
                    float f35 = fArr[i31];
                    float f36 = fArr[i32 - 1];
                    float f37 = fArr[i32];
                    float f38 = fArr[i33 - 1];
                    float f39 = fArr[i33];
                    float f40 = fArr[i34 - 1];
                    float f41 = fArr[i34];
                    float f42 = f33 - f41;
                    float f43 = f33 + f41;
                    float f44 = f35 - f39;
                    float f45 = f35 + f39;
                    float f46 = f32 - f40;
                    float f47 = f32 + f40;
                    float f48 = f34 - f38;
                    float f49 = f34 + f38;
                    float f50 = f36 + (f47 * f4) + (f49 * -0.809017f);
                    float f51 = f37 + (f43 * f4) + (f45 * -0.809017f);
                    float f52 = f36 + (f47 * -0.809017f) + (f49 * f4);
                    float f53 = f37 + (f43 * -0.809017f) + (f45 * f4);
                    float f54 = (float) i9;
                    float f55 = ((f46 * f2) + (f48 * f)) * f54;
                    float f56 = ((f42 * f2) + (f44 * f)) * f54;
                    float f57 = ((f46 * f) - (f48 * f2)) * f54;
                    float f58 = ((f42 * f) - (f44 * f2)) * f54;
                    float f59 = f52 - f58;
                    float f60 = f52 + f58;
                    float f61 = f53 + f57;
                    float f62 = f53 - f57;
                    float f63 = f50 + f56;
                    float f64 = f50 - f56;
                    float f65 = f51 - f55;
                    float f66 = f51 + f55;
                    int i35 = i29 + i5;
                    int i36 = i29 + i10;
                    int i37 = i29 + i11;
                    int i38 = i29 + i12;
                    float[] fArr3 = this.wtable;
                    float f67 = fArr3[i35];
                    float f68 = fArr3[i35 + 1] * f54;
                    float f69 = fArr3[i36];
                    float f70 = fArr3[i36 + 1] * f54;
                    float f71 = fArr3[i37];
                    float f72 = fArr3[i37 + 1] * f54;
                    float f73 = fArr3[i38];
                    float f74 = f54 * fArr3[i38 + 1];
                    int i39 = i29 + i28;
                    int i40 = i39 + i13;
                    int i41 = i40 + i13;
                    int i42 = i41 + i13;
                    int i43 = i42 + i13;
                    fArr2[i39] = f36 + f47 + f49;
                    fArr2[i39 + 1] = f37 + f43 + f45;
                    fArr2[i40] = (f67 * f64) - (f68 * f66);
                    fArr2[i40 + 1] = (f67 * f66) + (f68 * f64);
                    fArr2[i41] = (f69 * f59) - (f70 * f61);
                    fArr2[i41 + 1] = (f69 * f61) + (f70 * f59);
                    fArr2[i42] = (f71 * f60) - (f72 * f62);
                    fArr2[i42 + 1] = (f71 * f62) + (f72 * f60);
                    fArr2[i43] = (f73 * f63) - (f74 * f65);
                    fArr2[i43 + 1] = (f73 * f65) + (f74 * f63);
                    i29 += 2;
                    f = 1058437400;
                    f2 = 0.95105654f;
                    f4 = 0.309017f;
                }
                i26++;
                f = 0.58778524f;
                f2 = 0.95105654f;
                f4 = 0.309017f;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void passf5(long j, long j2, FloatLargeArray floatLargeArray, long j3, FloatLargeArray floatLargeArray2, long j4, long j5, long j6) {
        FloatLargeArray floatLargeArray3 = floatLargeArray;
        FloatLargeArray floatLargeArray4 = floatLargeArray2;
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
                float f = floatLargeArray3.getFloat(j14);
                float f2 = floatLargeArray3.getFloat(j15);
                float f3 = floatLargeArray3.getFloat(j16 - 1);
                float f4 = floatLargeArray3.getFloat(j16);
                float f5 = floatLargeArray3.getFloat(j18 - 1);
                float f6 = floatLargeArray3.getFloat(j18);
                float f7 = floatLargeArray3.getFloat(j20 - 1);
                float f8 = floatLargeArray3.getFloat(j20);
                float f9 = floatLargeArray3.getFloat(j21 - 1);
                float f10 = floatLargeArray3.getFloat(j21);
                float f11 = f2 - f10;
                float f12 = f2 + f10;
                float f13 = f4 - f8;
                float f14 = f4 + f8;
                float f15 = f - f9;
                float f16 = f + f9;
                float f17 = f3 - f7;
                float f18 = f3 + f7;
                float f19 = (f16 * 0.309017f) + f5 + (f18 * -0.809017f);
                float f20 = (f12 * 0.309017f) + f6 + (f14 * -0.809017f);
                float f21 = (f12 * -0.809017f) + f6 + (f14 * 0.309017f);
                float f22 = (float) j6;
                float f23 = ((f15 * 0.95105654f) + (f17 * 0.58778524f)) * f22;
                float f24 = ((f11 * 0.95105654f) + (f13 * 0.58778524f)) * f22;
                long j22 = j4 + ((j19 - 1) * j);
                long j23 = j22 + j17;
                float f25 = ((f15 * 0.58778524f) - (f17 * 0.95105654f)) * f22;
                long j24 = j23 + j17;
                float f26 = (f16 * -0.809017f) + f5 + (f18 * 0.309017f);
                float f27 = f22 * ((f11 * 0.58778524f) - (f13 * 0.95105654f));
                long j25 = j24 + j17;
                long j26 = j24;
                long j27 = j25 + j17;
                FloatLargeArray floatLargeArray5 = floatLargeArray2;
                floatLargeArray5.setFloat(j22, f5 + f16 + f18);
                floatLargeArray5.setFloat(j22 + 1, f6 + f12 + f14);
                floatLargeArray5.setFloat(j23, f19 - f24);
                floatLargeArray5.setFloat(j23 + 1, f20 + f23);
                long j28 = j26;
                floatLargeArray5.setFloat(j28, f26 - f27);
                floatLargeArray5.setFloat(j28 + 1, f21 + f25);
                floatLargeArray5.setFloat(j25, f26 + f27);
                floatLargeArray5.setFloat(j25 + 1, f21 - f25);
                floatLargeArray5.setFloat(j27, f19 + f24);
                floatLargeArray5.setFloat(j27 + 1, f20 - f23);
                j13 = j19 + 1;
                floatLargeArray3 = floatLargeArray;
                FloatLargeArray floatLargeArray6 = floatLargeArray5;
                j11 = j17;
                long j29 = j6;
            }
        } else {
            FloatLargeArray floatLargeArray7 = floatLargeArray4;
            long j30 = j11;
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
                    long j40 = j33;
                    long j41 = j37 + j;
                    long j42 = j10;
                    long j43 = j41 + j;
                    long j44 = j9;
                    FloatLargeArray floatLargeArray8 = floatLargeArray;
                    long j45 = j8;
                    float f28 = floatLargeArray8.getFloat(j35 - 1);
                    float f29 = floatLargeArray8.getFloat(j35);
                    float f30 = floatLargeArray8.getFloat(j37 - 1);
                    float f31 = floatLargeArray8.getFloat(j37);
                    float f32 = floatLargeArray8.getFloat(j39 - 1);
                    float f33 = floatLargeArray8.getFloat(j39);
                    float f34 = floatLargeArray8.getFloat(j41 - 1);
                    float f35 = floatLargeArray8.getFloat(j41);
                    float f36 = floatLargeArray8.getFloat(j43 - 1);
                    float f37 = floatLargeArray8.getFloat(j43);
                    float f38 = f29 - f37;
                    float f39 = f29 + f37;
                    float f40 = f31 - f35;
                    float f41 = f31 + f35;
                    float f42 = f28 - f36;
                    float f43 = f28 + f36;
                    float f44 = f30 - f34;
                    float f45 = f30 + f34;
                    float f46 = (f43 * 0.309017f) + f32 + (f45 * -0.809017f);
                    float f47 = (f39 * 0.309017f) + f33 + (f41 * -0.809017f);
                    float f48 = (f43 * -0.809017f) + f32 + (f45 * 0.309017f);
                    float f49 = f33 + (f39 * -0.809017f) + (f41 * 0.309017f);
                    float f50 = (float) j6;
                    float f51 = ((f42 * 0.95105654f) + (f44 * 0.58778524f)) * f50;
                    float f52 = ((f38 * 0.95105654f) + (f40 * 0.58778524f)) * f50;
                    float f53 = ((f42 * 0.58778524f) - (f44 * 0.95105654f)) * f50;
                    float f54 = ((f38 * 0.58778524f) - (f40 * 0.95105654f)) * f50;
                    float f55 = f48 - f54;
                    float f56 = f48 + f54;
                    float f57 = f49 + f53;
                    float f58 = f49 - f53;
                    float f59 = f46 + f52;
                    float f60 = f46 - f52;
                    float f61 = f47 - f51;
                    float f62 = f47 + f51;
                    long j46 = j34 + j5;
                    float f63 = f59;
                    float f64 = f61;
                    long j47 = j34 + j45;
                    float f65 = f55;
                    float f66 = f57;
                    long j48 = j34 + j44;
                    float f67 = f62;
                    float f68 = f56;
                    long j49 = j34 + j42;
                    float f69 = f60;
                    float f70 = f41;
                    float f71 = this.wtablel.getFloat(j46);
                    float f72 = this.wtablel.getFloat(j46 + 1) * f50;
                    float f73 = this.wtablel.getFloat(j47);
                    float f74 = this.wtablel.getFloat(j47 + 1) * f50;
                    float f75 = this.wtablel.getFloat(j48);
                    float f76 = this.wtablel.getFloat(j48 + 1) * f50;
                    float f77 = this.wtablel.getFloat(j49);
                    long j50 = j34 + j40;
                    float f78 = f50 * this.wtablel.getFloat(j49 + 1);
                    long j51 = j50 + j30;
                    float f79 = f76;
                    float f80 = f77;
                    long j52 = j51 + j30;
                    float f81 = f73;
                    float f82 = f74;
                    long j53 = j52 + j30;
                    long j54 = j52;
                    long j55 = j53 + j30;
                    FloatLargeArray floatLargeArray9 = floatLargeArray2;
                    floatLargeArray9.setFloat(j50, f32 + f43 + f45);
                    floatLargeArray9.setFloat(j50 + 1, f33 + f39 + f70);
                    floatLargeArray9.setFloat(j51, (f71 * f69) - (f72 * f67));
                    floatLargeArray9.setFloat(j51 + 1, (f71 * f67) + (f72 * f69));
                    long j56 = j54;
                    floatLargeArray9.setFloat(j56, (f81 * f65) - (f82 * f66));
                    floatLargeArray9.setFloat(j56 + 1, (f81 * f66) + (f82 * f65));
                    floatLargeArray9.setFloat(j53, (f75 * f68) - (f79 * f58));
                    floatLargeArray9.setFloat(j53 + 1, (f75 * f58) + (f79 * f68));
                    floatLargeArray9.setFloat(j55, (f80 * f63) - (f78 * f64));
                    floatLargeArray9.setFloat(j55 + 1, (f80 * f64) + (f78 * f63));
                    j34 += 2;
                    j12 = 2;
                    floatLargeArray7 = floatLargeArray9;
                    j32 = j36;
                    j31 = j38;
                    j33 = j40;
                    j10 = j42;
                    j9 = j44;
                    j8 = j45;
                }
                long j57 = j9;
                long j58 = j10;
                long j59 = j12;
                j31++;
                floatLargeArray7 = floatLargeArray7;
                j8 = j8;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void passfg(int[] iArr, int i, int i2, int i3, int i4, float[] fArr, int i5, float[] fArr2, int i6, int i7, int i8) {
        int i9;
        int i10;
        FloatFFT_1D floatFFT_1D = this;
        int i11 = i;
        int i12 = i2;
        int i13 = i3;
        int i14 = i4;
        float[] fArr3 = fArr;
        int i15 = i5;
        float[] fArr4 = fArr2;
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
                        float f = fArr3[i31 + i22 + i28];
                        float f2 = fArr3[i31 + i23 + i28];
                        fArr4[i30 + i26] = f + f2;
                        fArr4[i30 + i27] = f - f2;
                    }
                }
            }
            int i32 = 0;
            while (i32 < i13) {
                int i33 = i32 * i11;
                int i34 = i33 * i12;
                int i35 = i18;
                for (int i36 = 0; i36 < i11; i36++) {
                    fArr4[i16 + i36 + i33] = fArr3[i15 + i36 + i34];
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
                        float f3 = fArr3[i48 + i41 + i47];
                        float f4 = fArr3[i48 + i42 + i47];
                        int i49 = i16 + i44 + i46;
                        fArr4[i49 + i39] = f3 + f4;
                        fArr4[i49 + i40] = f3 - f4;
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
                    fArr4[i16 + i52 + i54] = fArr3[i15 + i52 + (i54 * i12)];
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
            float[] fArr5 = floatFFT_1D.wtable;
            float f5 = fArr5[i63 - 2];
            int i65 = i60;
            float f6 = (float) i8;
            float f7 = fArr5[i63 - 1] * f6;
            for (int i66 = 0; i66 < i14; i66++) {
                int i67 = i15 + i66;
                int i68 = i16 + i66;
                fArr3[i67 + i61] = fArr4[i68] + (fArr4[i68 + i14] * f5);
                fArr3[i67 + i62] = fArr4[i68 + i56] * f7;
            }
            i59 += i11;
            int i69 = i65;
            int i70 = 2;
            while (i70 < i19) {
                int i71 = i12 - i70;
                i69 += i59;
                int i72 = i56;
                int i73 = i9;
                if (i69 > i73) {
                    i69 -= i73;
                }
                int i74 = i69 + i7;
                int i75 = i73;
                float[] fArr6 = floatFFT_1D.wtable;
                float f8 = fArr6[i74 - 2];
                float f9 = fArr6[i74 - 1] * f6;
                int i76 = i70 * i14;
                int i77 = i71 * i14;
                float f10 = f6;
                for (int i78 = 0; i78 < i14; i78++) {
                    int i79 = i15 + i78;
                    int i80 = i16 + i78;
                    int i81 = i79 + i61;
                    fArr3[i81] = fArr3[i81] + (fArr4[i80 + i76] * f8);
                    int i82 = i79 + i62;
                    fArr3[i82] = fArr3[i82] + (fArr4[i80 + i77] * f9);
                }
                i70++;
                i56 = i72;
                i9 = i75;
                f6 = f10;
            }
            int i83 = i56;
            int i84 = i9;
            i58++;
            int i85 = i3;
            i55 = i64;
            i57 = i65;
        }
        int i86 = i55;
        for (int i87 = 1; i87 < i19; i87++) {
            int i88 = i87 * i14;
            for (int i89 = 0; i89 < i14; i89++) {
                int i90 = i16 + i89;
                fArr4[i90] = fArr4[i90] + fArr4[i90 + i88];
            }
        }
        for (int i91 = 1; i91 < i19; i91++) {
            int i92 = i91 * i14;
            int i93 = (i12 - i91) * i14;
            for (int i94 = 1; i94 < i14; i94 += 2) {
                int i95 = i16 + i94;
                int i96 = i15 + i94;
                int i97 = i96 + i92;
                int i98 = i96 + i93;
                float f11 = fArr3[i97 - 1];
                float f12 = fArr3[i97];
                float f13 = fArr3[i98 - 1];
                float f14 = fArr3[i98];
                int i99 = i95 + i92;
                int i100 = i95 + i93;
                fArr4[i99 - 1] = f11 - f14;
                fArr4[i100 - 1] = f11 + f14;
                fArr4[i99] = f12 + f13;
                fArr4[i100] = f12 - f13;
            }
        }
        iArr[0] = 1;
        if (i11 != 2) {
            iArr[0] = 0;
            System.arraycopy(fArr4, i16, fArr3, i15, i14);
            int i101 = i3;
            int i102 = i101 * i11;
            for (int i103 = 1; i103 < i12; i103++) {
                int i104 = i103 * i102;
                for (int i105 = 0; i105 < i101; i105++) {
                    int i106 = i105 * i11;
                    int i107 = i16 + i106 + i104;
                    int i108 = i106 + i15 + i104;
                    fArr3[i108] = fArr4[i107];
                    fArr3[i108 + 1] = fArr4[i107 + 1];
                }
            }
            if (i10 <= i101) {
                int i109 = 0;
                for (int i110 = 1; i110 < i12; i110++) {
                    int i111 = 2;
                    i109 += 2;
                    int i112 = i110 * i101 * i11;
                    int i113 = 3;
                    while (i113 < i11) {
                        int i114 = i109 + 2;
                        int i115 = i114 + i7;
                        int i116 = i115 - 1;
                        float[] fArr7 = floatFFT_1D.wtable;
                        float f15 = fArr7[i115 - i111];
                        float f16 = ((float) i8) * fArr7[i116];
                        int i117 = i15 + i113;
                        int i118 = i16 + i113;
                        int i119 = i114;
                        for (int i120 = 0; i120 < i101; i120++) {
                            int i121 = (i120 * i11) + i112;
                            int i122 = i117 + i121;
                            int i123 = i118 + i121;
                            float f17 = fArr4[i123 - 1];
                            float f18 = fArr4[i123];
                            fArr3[i122 - 1] = (f15 * f17) - (f16 * f18);
                            fArr3[i122] = (f18 * f15) + (f17 * f16);
                        }
                        i113 += 2;
                        i109 = i119;
                        i111 = 2;
                    }
                    int i124 = i8;
                }
                return;
            }
            int i125 = i8;
            int i126 = 1;
            while (i126 < i12) {
                i86 += i11;
                int i127 = i126 * i101 * i11;
                int i128 = 0;
                while (i128 < i101) {
                    int i129 = (i128 * i11) + i127;
                    int i130 = i86;
                    int i131 = 3;
                    while (i131 < i11) {
                        int i132 = i130 + 2;
                        int i133 = i130 + 1 + i7;
                        float[] fArr8 = floatFFT_1D.wtable;
                        float f19 = fArr8[i133 - 1];
                        float f20 = ((float) i125) * fArr8[i133];
                        int i134 = i15 + i131 + i129;
                        int i135 = i16 + i131 + i129;
                        float f21 = fArr4[i135 - 1];
                        float f22 = fArr4[i135];
                        fArr3[i134 - 1] = (f19 * f21) - (f20 * f22);
                        fArr3[i134] = (f19 * f22) + (f20 * f21);
                        i131 += 2;
                        floatFFT_1D = this;
                        i11 = i;
                        i130 = i132;
                    }
                    i128++;
                    floatFFT_1D = this;
                    i11 = i;
                }
                i126++;
                floatFFT_1D = this;
                i11 = i;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void passfg(int[] iArr, long j, long j2, long j3, long j4, FloatLargeArray floatLargeArray, long j5, FloatLargeArray floatLargeArray2, long j6, long j7, long j8) {
        long j9;
        long j10;
        long j11;
        FloatLargeArray floatLargeArray3 = floatLargeArray;
        FloatLargeArray floatLargeArray4 = floatLargeArray2;
        long j12 = j8;
        long j13 = j / 2;
        long j14 = (j2 + 1) / 2;
        long j15 = j2 * j;
        if (j >= j3) {
            for (long j16 = 1; j16 < j14; j16++) {
                long j17 = j16 * j;
                long j18 = (j2 - j16) * j;
                for (long j19 = 0; j19 < j3; j19++) {
                    long j20 = j19 * j;
                    long j21 = j20 + (j17 * j3);
                    long j22 = j20 + (j18 * j3);
                    long j23 = j20 * j2;
                    long j24 = 0;
                    while (j24 < j) {
                        long j25 = j6 + j24;
                        long j26 = j5 + j24;
                        float f = floatLargeArray3.getFloat(j26 + j17 + j23);
                        long j27 = j18;
                        float f2 = floatLargeArray3.getFloat(j26 + j18 + j23);
                        floatLargeArray4.setFloat(j25 + j21, f + f2);
                        floatLargeArray4.setFloat(j25 + j22, f - f2);
                        j24++;
                        j13 = j13;
                        j18 = j27;
                    }
                    long j28 = j18;
                    long j29 = j13;
                }
                long j30 = j13;
            }
            j9 = j13;
            for (long j31 = 0; j31 < j3; j31++) {
                long j32 = j31 * j;
                long j33 = j32 * j2;
                long j34 = 0;
                while (j34 < j) {
                    floatLargeArray4.setFloat(j6 + j34 + j32, floatLargeArray3.getFloat(j5 + j34 + j33));
                    j34++;
                    j15 = j15;
                    j32 = j32;
                }
                long j35 = j15;
            }
            j11 = j15;
            j10 = j14;
        } else {
            j11 = j15;
            j9 = j13;
            long j36 = 1;
            while (j36 < j14) {
                long j37 = j2 - j36;
                long j38 = j36 * j3 * j;
                long j39 = j37 * j3 * j;
                long j40 = j36 * j;
                long j41 = j37 * j;
                long j42 = 0;
                while (j42 < j) {
                    long j43 = 0;
                    while (j43 < j3) {
                        long j44 = j43 * j;
                        long j45 = j44 * j2;
                        long j46 = j5 + j42;
                        float f3 = floatLargeArray3.getFloat(j46 + j40 + j45);
                        long j47 = j41;
                        float f4 = floatLargeArray3.getFloat(j46 + j41 + j45);
                        long j48 = j6 + j42 + j44;
                        floatLargeArray4.setFloat(j48 + j38, f3 + f4);
                        floatLargeArray4.setFloat(j48 + j39, f3 - f4);
                        j43++;
                        long j49 = j8;
                        j14 = j14;
                        j41 = j47;
                    }
                    long j50 = j14;
                    long j51 = j41;
                    j42++;
                    long j52 = j8;
                }
                long j53 = j14;
                j36++;
                long j54 = j8;
            }
            j10 = j14;
            for (long j55 = 0; j55 < j; j55++) {
                for (long j56 = 0; j56 < j3; j56++) {
                    long j57 = j56 * j;
                    floatLargeArray4.setFloat(j6 + j55 + j57, floatLargeArray3.getFloat(j5 + j55 + (j57 * j2)));
                }
            }
        }
        long j58 = 2 - j;
        long j59 = (j2 - 1) * j4;
        long j60 = j58;
        long j61 = 1;
        long j62 = 0;
        while (j61 < j10) {
            long j63 = j60 + j;
            long j64 = j61 * j4;
            long j65 = (j2 - j61) * j4;
            long j66 = j63 + j7;
            long j67 = j63;
            long j68 = j58;
            float f5 = this.wtablel.getFloat(j66 - 2);
            float f6 = (float) j8;
            long j69 = j61;
            float f7 = this.wtablel.getFloat(j66 - 1) * f6;
            long j70 = 0;
            while (j70 < j4) {
                long j71 = j5 + j70;
                float f8 = f6;
                long j72 = j6 + j70;
                floatLargeArray3.setFloat(j71 + j64, floatLargeArray4.getFloat(j72) + (floatLargeArray4.getFloat(j72 + j4) * f5));
                floatLargeArray3.setFloat(j71 + j65, floatLargeArray4.getFloat(j72 + j59) * f7);
                j70++;
                f6 = f8;
                j62 = j62;
            }
            float f9 = f6;
            j62 += j;
            long j73 = j67;
            long j74 = 2;
            while (j74 < j10) {
                long j75 = j2 - j74;
                j73 += j62;
                if (j73 > j11) {
                    j73 -= j11;
                }
                long j76 = j73 + j7;
                long j77 = j59;
                float f10 = this.wtablel.getFloat(j76 - 2);
                long j78 = j62;
                float f11 = this.wtablel.getFloat(j76 - 1) * f9;
                long j79 = j74 * j4;
                long j80 = j75 * j4;
                long j81 = 0;
                while (j81 < j4) {
                    long j82 = j5 + j81;
                    long j83 = j6 + j81;
                    long j84 = j82 + j64;
                    floatLargeArray3.setFloat(j84, floatLargeArray3.getFloat(j84) + (floatLargeArray4.getFloat(j83 + j79) * f10));
                    long j85 = j82 + j65;
                    floatLargeArray3.setFloat(j85, floatLargeArray3.getFloat(j85) + (floatLargeArray4.getFloat(j83 + j80) * f11));
                    j81++;
                    j79 = j79;
                    f9 = f9;
                    j73 = j73;
                }
                float f12 = f9;
                long j86 = j73;
                j74++;
                j62 = j78;
                j59 = j77;
            }
            long j87 = j59;
            long j88 = j62;
            j61 = j69 + 1;
            j60 = j67;
            j58 = j68;
        }
        long j89 = j58;
        for (long j90 = 1; j90 < j10; j90++) {
            long j91 = j90 * j4;
            for (long j92 = 0; j92 < j4; j92++) {
                long j93 = j6 + j92;
                floatLargeArray4.setFloat(j93, floatLargeArray4.getFloat(j93) + floatLargeArray4.getFloat(j93 + j91));
            }
        }
        long j94 = 1;
        while (j94 < j10) {
            long j95 = j94 * j4;
            long j96 = (j2 - j94) * j4;
            long j97 = 1;
            while (j97 < j4) {
                long j98 = j6 + j97;
                long j99 = j5 + j97;
                long j100 = j94;
                long j101 = j99 + j95;
                long j102 = j99 + j96;
                long j103 = j97;
                float f13 = floatLargeArray3.getFloat(j101 - 1);
                float f14 = floatLargeArray3.getFloat(j101);
                float f15 = floatLargeArray3.getFloat(j102 - 1);
                float f16 = floatLargeArray3.getFloat(j102);
                long j104 = j98 + j95;
                long j105 = j98 + j96;
                floatLargeArray4.setFloat(j104 - 1, f13 - f16);
                floatLargeArray4.setFloat(j105 - 1, f13 + f16);
                floatLargeArray4.setFloat(j104, f14 + f15);
                floatLargeArray4.setFloat(j105, f14 - f15);
                j97 = j103 + 2;
                j94 = j100;
                j96 = j96;
            }
            j94++;
        }
        iArr[0] = 1;
        if (j != 2) {
            iArr[0] = 0;
            FloatFFT_1D floatFFT_1D = this;
            LargeArrayUtils.arraycopy(floatLargeArray2, j6, floatLargeArray, j5, j4);
            long j106 = j3 * j;
            for (long j107 = 1; j107 < j2; j107++) {
                long j108 = j107 * j106;
                for (long j109 = 0; j109 < j3; j109++) {
                    long j110 = j109 * j;
                    long j111 = j6 + j110 + j108;
                    long j112 = j5 + j110 + j108;
                    floatLargeArray3.setFloat(j112, floatLargeArray4.getFloat(j111));
                    floatLargeArray3.setFloat(j112 + 1, floatLargeArray4.getFloat(j111 + 1));
                }
            }
            if (j9 <= j3) {
                long j113 = 1;
                long j114 = 0;
                while (j113 < j2) {
                    long j115 = 2;
                    j114 += 2;
                    long j116 = j113 * j3 * j;
                    long j117 = 3;
                    while (j117 < j) {
                        long j118 = j114 + j115;
                        long j119 = j118 + j7;
                        long j120 = j118;
                        float f17 = floatFFT_1D.wtablel.getFloat(j119 - j115);
                        float f18 = ((float) j8) * floatFFT_1D.wtablel.getFloat(j119 - 1);
                        long j121 = j5 + j117;
                        long j122 = j6 + j117;
                        long j123 = 0;
                        while (j123 < j3) {
                            long j124 = (j123 * j) + j116;
                            long j125 = j116;
                            long j126 = j121 + j124;
                            long j127 = j121;
                            long j128 = j122 + j124;
                            long j129 = j122;
                            float f19 = floatLargeArray4.getFloat(j128 - 1);
                            float f20 = floatLargeArray4.getFloat(j128);
                            floatLargeArray3.setFloat(j126 - 1, (f17 * f19) - (f18 * f20));
                            floatLargeArray3.setFloat(j126, (f20 * f17) + (f19 * f18));
                            j123++;
                            floatLargeArray4 = floatLargeArray2;
                            long j130 = j8;
                            j122 = j129;
                            j116 = j125;
                            j121 = j127;
                        }
                        long j131 = j116;
                        j117 += 2;
                        floatLargeArray4 = floatLargeArray2;
                        j115 = 2;
                        j114 = j120;
                    }
                    j113++;
                    floatLargeArray4 = floatLargeArray2;
                }
                return;
            }
            long j132 = 1;
            long j133 = j89;
            while (j132 < j2) {
                j133 += j;
                long j134 = j132 * j3 * j;
                long j135 = 0;
                while (j135 < j3) {
                    long j136 = (j135 * j) + j134;
                    long j137 = j133;
                    long j138 = 3;
                    while (j138 < j) {
                        long j139 = j137 + 2;
                        long j140 = j134;
                        long j141 = j137 + 1 + j7;
                        float f21 = floatFFT_1D.wtablel.getFloat(j141 - 1);
                        long j142 = j133;
                        float f22 = ((float) j8) * floatFFT_1D.wtablel.getFloat(j141);
                        long j143 = j5 + j138 + j136;
                        long j144 = j6 + j138 + j136;
                        long j145 = j136;
                        FloatLargeArray floatLargeArray5 = floatLargeArray2;
                        float f23 = floatLargeArray5.getFloat(j144 - 1);
                        float f24 = floatLargeArray5.getFloat(j144);
                        floatLargeArray3.setFloat(j143 - 1, (f21 * f23) - (f22 * f24));
                        floatLargeArray3.setFloat(j143, (f21 * f24) + (f22 * f23));
                        j138 += 2;
                        floatFFT_1D = this;
                        j134 = j140;
                        j137 = j139;
                        j133 = j142;
                        j136 = j145;
                    }
                    long j146 = j133;
                    j135++;
                    floatFFT_1D = this;
                    j134 = j134;
                }
                long j147 = j133;
                j132++;
                floatFFT_1D = this;
            }
        }
    }
}
