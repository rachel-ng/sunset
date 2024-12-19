package org.jtransforms.dht;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jtransforms.fft.FloatFFT_1D;
import org.jtransforms.utils.CommonUtils;
import pl.edu.icm.jlargearrays.ConcurrencyUtils;
import pl.edu.icm.jlargearrays.FloatLargeArray;
import pl.edu.icm.jlargearrays.LargeArray;
import pl.edu.icm.jlargearrays.LargeArrayUtils;

public class FloatDHT_1D {
    private final FloatFFT_1D fft;
    /* access modifiers changed from: private */
    public final int n;
    /* access modifiers changed from: private */
    public final long nl;
    private final boolean useLargeArrays;

    public FloatDHT_1D(long j) {
        this.n = (int) j;
        this.nl = j;
        this.useLargeArrays = CommonUtils.isUseLargeArrays() || j > ((long) LargeArray.getMaxSizeOf32bitArray());
        this.fft = new FloatFFT_1D(j);
    }

    public void forward(float[] fArr) {
        forward(fArr, 0);
    }

    public void forward(FloatLargeArray floatLargeArray) {
        forward(floatLargeArray, 0);
    }

    public void forward(float[] fArr, int i) {
        float[] fArr2 = fArr;
        int i2 = i;
        Class<FloatDHT_1D> cls = FloatDHT_1D.class;
        if (this.n != 1) {
            if (this.useLargeArrays) {
                forward(new FloatLargeArray(fArr2), (long) i2);
                return;
            }
            this.fft.realForward(fArr2, i2);
            int i3 = this.n;
            float[] fArr3 = new float[i3];
            System.arraycopy(fArr2, i2, fArr3, 0, i3);
            int i4 = this.n / 2;
            if (ConcurrencyUtils.getNumberOfThreads() <= 1 || ((long) i4) <= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
                for (int i5 = 1; i5 < i4; i5++) {
                    int i6 = i5 * 2;
                    int i7 = i6 + 1;
                    fArr2[i2 + i5] = fArr3[i6] - fArr3[i7];
                    fArr2[(this.n + i2) - i5] = fArr3[i6] + fArr3[i7];
                }
            } else {
                int i8 = i4 / 2;
                Future[] futureArr = new Future[2];
                int i9 = 0;
                while (i9 < 2) {
                    final int i10 = (i9 * i8) + 1;
                    final int i11 = i9 == 1 ? i4 : i10 + i8;
                    final float[] fArr4 = fArr;
                    int i12 = i9;
                    final int i13 = i;
                    Future[] futureArr2 = futureArr;
                    final float[] fArr5 = fArr3;
                    futureArr2[i12] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            for (int i = i10; i < i11; i++) {
                                int i2 = i * 2;
                                int i3 = i2 + 1;
                                float[] fArr = fArr4;
                                int i4 = i13;
                                float[] fArr2 = fArr5;
                                fArr[i4 + i] = fArr2[i2] - fArr2[i3];
                                float[] fArr3 = fArr5;
                                fArr[(i4 + FloatDHT_1D.this.n) - i] = fArr3[i2] + fArr3[i3];
                            }
                        }
                    });
                    i9 = i12 + 1;
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
            int i14 = this.n;
            if (i14 % 2 == 0) {
                fArr2[i2 + i4] = fArr3[1];
                return;
            }
            int i15 = i2 + i4;
            fArr2[i15] = fArr3[i14 - 1] - fArr3[1];
            fArr2[i15 + 1] = fArr3[i14 - 1] + fArr3[1];
        }
    }

    public void forward(FloatLargeArray floatLargeArray, long j) {
        long j2;
        FloatLargeArray floatLargeArray2 = floatLargeArray;
        long j3 = j;
        Class<FloatDHT_1D> cls = FloatDHT_1D.class;
        long j4 = 1;
        if (this.nl != 1) {
            if (this.useLargeArrays) {
                this.fft.realForward(floatLargeArray2, j3);
                FloatLargeArray floatLargeArray3 = new FloatLargeArray(this.nl, false);
                FloatLargeArray floatLargeArray4 = floatLargeArray3;
                int i = 0;
                LargeArrayUtils.arraycopy(floatLargeArray, j, floatLargeArray3, 0, this.nl);
                long j5 = this.nl / 2;
                int i2 = 1;
                if (ConcurrencyUtils.getNumberOfThreads() <= 1 || j5 <= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
                    j2 = 1;
                    long j6 = 1;
                    while (j6 < j5) {
                        long j7 = j6 * 2;
                        long j8 = j7 + j2;
                        FloatLargeArray floatLargeArray5 = floatLargeArray4;
                        FloatLargeArray floatLargeArray6 = floatLargeArray;
                        floatLargeArray6.setFloat(j3 + j6, floatLargeArray5.getFloat(j7) - floatLargeArray5.getFloat(j8));
                        floatLargeArray6.setFloat((this.nl + j3) - j6, floatLargeArray5.getFloat(j7) + floatLargeArray5.getFloat(j8));
                        j6++;
                        j2 = 1;
                    }
                } else {
                    int i3 = 2;
                    long j9 = j5 / ((long) 2);
                    Future[] futureArr = new Future[2];
                    while (i < i3) {
                        final long j10 = (((long) i) * j9) + j4;
                        Future[] futureArr2 = futureArr;
                        final long j11 = i == i2 ? j5 : j10 + j9;
                        int i4 = i3;
                        final FloatLargeArray floatLargeArray7 = floatLargeArray;
                        int i5 = i2;
                        final long j12 = j;
                        final FloatLargeArray floatLargeArray8 = floatLargeArray4;
                        futureArr2[i] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                for (long j = j10; j < j11; j++) {
                                    long j2 = 2 * j;
                                    long j3 = j2 + 1;
                                    floatLargeArray7.setFloat(j12 + j, floatLargeArray8.getFloat(j2) - floatLargeArray8.getFloat(j3));
                                    floatLargeArray7.setFloat((j12 + FloatDHT_1D.this.nl) - j, floatLargeArray8.getFloat(j2) + floatLargeArray8.getFloat(j3));
                                }
                            }
                        });
                        i++;
                        j4 = j4;
                        i3 = i4;
                        i2 = i5;
                        futureArr = futureArr2;
                        FloatLargeArray floatLargeArray9 = floatLargeArray;
                    }
                    j2 = j4;
                    try {
                        ConcurrencyUtils.waitForCompletion(futureArr);
                    } catch (InterruptedException e) {
                        Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
                    } catch (ExecutionException e2) {
                        Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
                    }
                }
                FloatLargeArray floatLargeArray10 = floatLargeArray;
                long j13 = j2;
                FloatLargeArray floatLargeArray11 = floatLargeArray4;
                long j14 = this.nl;
                if (j14 % 2 == 0) {
                    floatLargeArray10.setFloat(j3 + j5, floatLargeArray11.getFloat(j13));
                    return;
                }
                long j15 = j3 + j5;
                floatLargeArray10.setFloat(j15, floatLargeArray11.getFloat(j14 - j13) - floatLargeArray11.getFloat(j13));
                floatLargeArray10.setFloat(j15 + j13, floatLargeArray11.getFloat(this.nl - j13) + floatLargeArray11.getFloat(j13));
            } else if (floatLargeArray.isLarge() || floatLargeArray.isConstant() || j3 >= 2147483647L) {
                throw new IllegalArgumentException("The data array is too big.");
            } else {
                forward(floatLargeArray.getData(), (int) j3);
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
        if (this.n != 1) {
            if (this.useLargeArrays) {
                inverse(new FloatLargeArray(fArr), (long) i, z);
                return;
            }
            forward(fArr, i);
            if (z) {
                int i2 = this.n;
                CommonUtils.scale(i2, 1.0f / ((float) i2), fArr, i, false);
            }
        }
    }

    public void inverse(FloatLargeArray floatLargeArray, long j, boolean z) {
        if (this.n != 1) {
            if (this.useLargeArrays) {
                forward(floatLargeArray, j);
                if (z) {
                    int i = this.n;
                    CommonUtils.scale((long) i, 1.0f / ((float) i), floatLargeArray, j, false);
                }
            } else if (floatLargeArray.isLarge() || floatLargeArray.isConstant() || j >= 2147483647L) {
                throw new IllegalArgumentException("The data array is too big.");
            } else {
                inverse(floatLargeArray.getData(), (int) j, z);
            }
        }
    }
}
