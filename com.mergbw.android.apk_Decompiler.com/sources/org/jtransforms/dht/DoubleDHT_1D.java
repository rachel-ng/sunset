package org.jtransforms.dht;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jtransforms.fft.DoubleFFT_1D;
import org.jtransforms.utils.CommonUtils;
import pl.edu.icm.jlargearrays.ConcurrencyUtils;
import pl.edu.icm.jlargearrays.DoubleLargeArray;
import pl.edu.icm.jlargearrays.LargeArray;
import pl.edu.icm.jlargearrays.LargeArrayUtils;

public class DoubleDHT_1D {
    private final DoubleFFT_1D fft;
    /* access modifiers changed from: private */
    public final int n;
    /* access modifiers changed from: private */
    public final long nl;
    private final boolean useLargeArrays;

    public DoubleDHT_1D(long j) {
        this.n = (int) j;
        this.nl = j;
        this.useLargeArrays = CommonUtils.isUseLargeArrays() || j > ((long) LargeArray.getMaxSizeOf32bitArray());
        this.fft = new DoubleFFT_1D(j);
    }

    public void forward(double[] dArr) {
        forward(dArr, 0);
    }

    public void forward(DoubleLargeArray doubleLargeArray) {
        forward(doubleLargeArray, 0);
    }

    public void forward(double[] dArr, int i) {
        double[] dArr2 = dArr;
        int i2 = i;
        Class<DoubleDHT_1D> cls = DoubleDHT_1D.class;
        if (this.n != 1) {
            if (this.useLargeArrays) {
                forward(new DoubleLargeArray(dArr2), (long) i2);
                return;
            }
            this.fft.realForward(dArr2, i2);
            int i3 = this.n;
            double[] dArr3 = new double[i3];
            System.arraycopy(dArr2, i2, dArr3, 0, i3);
            int i4 = this.n / 2;
            if (ConcurrencyUtils.getNumberOfThreads() <= 1 || ((long) i4) <= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
                for (int i5 = 1; i5 < i4; i5++) {
                    int i6 = i5 * 2;
                    int i7 = i6 + 1;
                    dArr2[i2 + i5] = dArr3[i6] - dArr3[i7];
                    dArr2[(this.n + i2) - i5] = dArr3[i6] + dArr3[i7];
                }
            } else {
                int i8 = i4 / 2;
                Future[] futureArr = new Future[2];
                int i9 = 0;
                while (i9 < 2) {
                    final int i10 = (i9 * i8) + 1;
                    final int i11 = i9 == 1 ? i4 : i10 + i8;
                    final double[] dArr4 = dArr;
                    int i12 = i9;
                    final int i13 = i;
                    Future[] futureArr2 = futureArr;
                    final double[] dArr5 = dArr3;
                    futureArr2[i12] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            for (int i = i10; i < i11; i++) {
                                int i2 = i * 2;
                                int i3 = i2 + 1;
                                double[] dArr = dArr4;
                                int i4 = i13;
                                double[] dArr2 = dArr5;
                                dArr[i4 + i] = dArr2[i2] - dArr2[i3];
                                double[] dArr3 = dArr5;
                                dArr[(i4 + DoubleDHT_1D.this.n) - i] = dArr3[i2] + dArr3[i3];
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
                dArr2[i2 + i4] = dArr3[1];
                return;
            }
            int i15 = i2 + i4;
            dArr2[i15] = dArr3[i14 - 1] - dArr3[1];
            dArr2[i15 + 1] = dArr3[i14 - 1] + dArr3[1];
        }
    }

    public void forward(DoubleLargeArray doubleLargeArray, long j) {
        long j2;
        DoubleLargeArray doubleLargeArray2 = doubleLargeArray;
        long j3 = j;
        Class<DoubleDHT_1D> cls = DoubleDHT_1D.class;
        long j4 = 1;
        if (this.nl != 1) {
            if (this.useLargeArrays) {
                this.fft.realForward(doubleLargeArray2, j3);
                DoubleLargeArray doubleLargeArray3 = new DoubleLargeArray(this.nl, false);
                DoubleLargeArray doubleLargeArray4 = doubleLargeArray3;
                int i = 0;
                LargeArrayUtils.arraycopy(doubleLargeArray, j, doubleLargeArray3, 0, this.nl);
                long j5 = this.nl / 2;
                int i2 = 1;
                if (ConcurrencyUtils.getNumberOfThreads() <= 1 || j5 <= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
                    j2 = 1;
                    long j6 = 1;
                    while (j6 < j5) {
                        long j7 = j6 * 2;
                        long j8 = j7 + j2;
                        DoubleLargeArray doubleLargeArray5 = doubleLargeArray4;
                        DoubleLargeArray doubleLargeArray6 = doubleLargeArray;
                        doubleLargeArray6.setDouble(j3 + j6, doubleLargeArray5.getDouble(j7) - doubleLargeArray5.getDouble(j8));
                        doubleLargeArray6.setDouble((this.nl + j3) - j6, doubleLargeArray5.getDouble(j7) + doubleLargeArray5.getDouble(j8));
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
                        final DoubleLargeArray doubleLargeArray7 = doubleLargeArray;
                        int i5 = i2;
                        final long j12 = j;
                        final DoubleLargeArray doubleLargeArray8 = doubleLargeArray4;
                        futureArr2[i] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                for (long j = j10; j < j11; j++) {
                                    long j2 = 2 * j;
                                    long j3 = j2 + 1;
                                    doubleLargeArray7.setDouble(j12 + j, doubleLargeArray8.getDouble(j2) - doubleLargeArray8.getDouble(j3));
                                    doubleLargeArray7.setDouble((j12 + DoubleDHT_1D.this.nl) - j, doubleLargeArray8.getDouble(j2) + doubleLargeArray8.getDouble(j3));
                                }
                            }
                        });
                        i++;
                        j4 = j4;
                        i3 = i4;
                        i2 = i5;
                        futureArr = futureArr2;
                        DoubleLargeArray doubleLargeArray9 = doubleLargeArray;
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
                long j13 = j2;
                DoubleLargeArray doubleLargeArray10 = doubleLargeArray4;
                DoubleLargeArray doubleLargeArray11 = doubleLargeArray;
                long j14 = this.nl;
                if (j14 % 2 == 0) {
                    doubleLargeArray11.setDouble(j3 + j5, doubleLargeArray10.getDouble(j13));
                    return;
                }
                long j15 = j3 + j5;
                doubleLargeArray11.setDouble(j15, doubleLargeArray10.getDouble(j14 - j13) - doubleLargeArray10.getDouble(j13));
                doubleLargeArray11.setDouble(j15 + j13, doubleLargeArray10.getDouble(this.nl - j13) + doubleLargeArray10.getDouble(j13));
            } else if (doubleLargeArray.isLarge() || doubleLargeArray.isConstant() || j3 >= 2147483647L) {
                throw new IllegalArgumentException("The data array is too big.");
            } else {
                forward(doubleLargeArray.getData(), (int) j3);
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
        if (this.n != 1) {
            if (this.useLargeArrays) {
                inverse(new DoubleLargeArray(dArr), (long) i, z);
                return;
            }
            forward(dArr, i);
            if (z) {
                int i2 = this.n;
                CommonUtils.scale(i2, 1.0d / ((double) i2), dArr, i, false);
            }
        }
    }

    public void inverse(DoubleLargeArray doubleLargeArray, long j, boolean z) {
        if (this.n != 1) {
            if (this.useLargeArrays) {
                forward(doubleLargeArray, j);
                if (z) {
                    int i = this.n;
                    CommonUtils.scale((long) i, 1.0d / ((double) i), doubleLargeArray, j, false);
                }
            } else if (doubleLargeArray.isLarge() || doubleLargeArray.isConstant() || j >= 2147483647L) {
                throw new IllegalArgumentException("The data array is too big.");
            } else {
                inverse(doubleLargeArray.getData(), (int) j, z);
            }
        }
    }
}
