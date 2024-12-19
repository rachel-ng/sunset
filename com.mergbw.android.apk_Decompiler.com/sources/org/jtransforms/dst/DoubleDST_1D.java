package org.jtransforms.dst;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jtransforms.dct.DoubleDCT_1D;
import org.jtransforms.utils.CommonUtils;
import pl.edu.icm.jlargearrays.ConcurrencyUtils;
import pl.edu.icm.jlargearrays.DoubleLargeArray;
import pl.edu.icm.jlargearrays.LargeArray;

public class DoubleDST_1D {
    private final DoubleDCT_1D dct;
    /* access modifiers changed from: private */
    public final int n;
    /* access modifiers changed from: private */
    public final long nl;
    private final boolean useLargeArrays;

    public DoubleDST_1D(long j) {
        this.n = (int) j;
        this.nl = j;
        this.useLargeArrays = CommonUtils.isUseLargeArrays() || j > ((long) LargeArray.getMaxSizeOf32bitArray());
        this.dct = new DoubleDCT_1D(j);
    }

    public void forward(double[] dArr, boolean z) {
        forward(dArr, 0, z);
    }

    public void forward(DoubleLargeArray doubleLargeArray, boolean z) {
        forward(doubleLargeArray, 0, z);
    }

    public void forward(double[] dArr, int i, boolean z) {
        double[] dArr2 = dArr;
        int i2 = i;
        boolean z2 = z;
        Class<DoubleDST_1D> cls = DoubleDST_1D.class;
        int i3 = this.n;
        if (i3 != 1) {
            if (this.useLargeArrays) {
                forward(new DoubleLargeArray(dArr2), (long) i2, z2);
                return;
            }
            int i4 = i3 / 2;
            int i5 = i3 + i2;
            for (int i6 = i2 + 1; i6 < i5; i6 += 2) {
                dArr2[i6] = -dArr2[i6];
            }
            this.dct.forward(dArr2, i2, z2);
            if (ConcurrencyUtils.getNumberOfThreads() <= 1 || ((long) i4) <= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
                int i7 = (this.n + i2) - 1;
                for (int i8 = 0; i8 < i4; i8++) {
                    int i9 = i2 + i8;
                    double d = dArr2[i9];
                    int i10 = i7 - i8;
                    dArr2[i9] = dArr2[i10];
                    dArr2[i10] = d;
                }
                return;
            }
            int i11 = i4 / 2;
            Future[] futureArr = new Future[2];
            int i12 = 0;
            while (i12 < 2) {
                final int i13 = i12 * i11;
                final int i14 = i12 == 1 ? i4 : i13 + i11;
                final int i15 = i;
                final double[] dArr3 = dArr;
                futureArr[i12] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        int access$000 = (i15 + DoubleDST_1D.this.n) - 1;
                        for (int i = i13; i < i14; i++) {
                            int i2 = i15 + i;
                            double[] dArr = dArr3;
                            double d = dArr[i2];
                            int i3 = access$000 - i;
                            dArr[i2] = dArr[i3];
                            dArr[i3] = d;
                        }
                    }
                });
                i12++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
            } catch (ExecutionException e2) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
            }
        }
    }

    public void forward(DoubleLargeArray doubleLargeArray, long j, boolean z) {
        DoubleLargeArray doubleLargeArray2 = doubleLargeArray;
        long j2 = j;
        boolean z2 = z;
        Class<DoubleDST_1D> cls = DoubleDST_1D.class;
        long j3 = this.nl;
        if (j3 != 1) {
            if (this.useLargeArrays) {
                long j4 = j3 / 2;
                long j5 = j3 + j2;
                for (long j6 = j2 + 1; j6 < j5; j6 += 2) {
                    doubleLargeArray2.setDouble(j6, -doubleLargeArray2.getDouble(j6));
                }
                this.dct.forward(doubleLargeArray2, j2, z2);
                int i = 1;
                if (ConcurrencyUtils.getNumberOfThreads() <= 1 || j4 <= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
                    long j7 = (this.nl + j2) - 1;
                    long j8 = 0;
                    while (j8 < j4) {
                        long j9 = j2 + j8;
                        double d = doubleLargeArray2.getDouble(j9);
                        long j10 = j7 - j8;
                        doubleLargeArray2.setDouble(j9, doubleLargeArray2.getDouble(j10));
                        doubleLargeArray2.setDouble(j10, d);
                        j8++;
                        j7 = j7;
                    }
                    return;
                }
                int i2 = 2;
                long j11 = j4 / ((long) 2);
                Future[] futureArr = new Future[2];
                int i3 = 0;
                while (i3 < i2) {
                    long j12 = ((long) i3) * j11;
                    long j13 = i3 == i ? j4 : j12 + j11;
                    final long j14 = j;
                    int i4 = i3;
                    final long j15 = j12;
                    int i5 = i2;
                    Future[] futureArr2 = futureArr;
                    final long j16 = j13;
                    int i6 = i;
                    final DoubleLargeArray doubleLargeArray3 = doubleLargeArray;
                    futureArr2[i4] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            long access$100 = (j14 + DoubleDST_1D.this.nl) - 1;
                            for (long j = j15; j < j16; j++) {
                                long j2 = j14 + j;
                                double d = doubleLargeArray3.getDouble(j2);
                                long j3 = access$100 - j;
                                DoubleLargeArray doubleLargeArray = doubleLargeArray3;
                                doubleLargeArray.setDouble(j2, doubleLargeArray.getDouble(j3));
                                doubleLargeArray3.setDouble(j3, d);
                            }
                        }
                    });
                    i3 = i4 + 1;
                    futureArr = futureArr2;
                    i2 = i5;
                    i = i6;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr);
                } catch (InterruptedException e) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
                } catch (ExecutionException e2) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
                }
            } else if (doubleLargeArray.isLarge() || doubleLargeArray.isConstant() || j2 >= 2147483647L) {
                throw new IllegalArgumentException("The data array is too big.");
            } else {
                forward(doubleLargeArray.getData(), (int) j2, z2);
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
        Class<DoubleDST_1D> cls = DoubleDST_1D.class;
        int i3 = this.n;
        if (i3 != 1) {
            if (this.useLargeArrays) {
                inverse(new DoubleLargeArray(dArr2), (long) i2, z2);
                return;
            }
            int i4 = i3 / 2;
            if (ConcurrencyUtils.getNumberOfThreads() <= 1 || ((long) i4) <= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
                int i5 = (this.n + i2) - 1;
                for (int i6 = 0; i6 < i4; i6++) {
                    int i7 = i2 + i6;
                    double d = dArr2[i7];
                    int i8 = i5 - i6;
                    dArr2[i7] = dArr2[i8];
                    dArr2[i8] = d;
                }
            } else {
                int i9 = i4 / 2;
                Future[] futureArr = new Future[2];
                int i10 = 0;
                while (i10 < 2) {
                    final int i11 = i10 * i9;
                    final int i12 = i10 == 1 ? i4 : i11 + i9;
                    final int i13 = i;
                    int i14 = i10;
                    final double[] dArr3 = dArr;
                    futureArr[i14] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            int access$000 = (i13 + DoubleDST_1D.this.n) - 1;
                            for (int i = i11; i < i12; i++) {
                                int i2 = i13 + i;
                                double[] dArr = dArr3;
                                double d = dArr[i2];
                                int i3 = access$000 - i;
                                dArr[i2] = dArr[i3];
                                dArr[i3] = d;
                            }
                        }
                    });
                    i10 = i14 + 1;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr);
                } catch (InterruptedException e) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
                } catch (ExecutionException e2) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
                }
            }
            this.dct.inverse(dArr2, i2, z2);
            int i15 = this.n + i2;
            for (int i16 = i2 + 1; i16 < i15; i16 += 2) {
                dArr2[i16] = -dArr2[i16];
            }
        }
    }

    public void inverse(DoubleLargeArray doubleLargeArray, long j, boolean z) {
        DoubleLargeArray doubleLargeArray2 = doubleLargeArray;
        long j2 = j;
        boolean z2 = z;
        Class<DoubleDST_1D> cls = DoubleDST_1D.class;
        long j3 = this.nl;
        if (j3 != 1) {
            if (this.useLargeArrays) {
                long j4 = j3 / 2;
                int i = 1;
                if (ConcurrencyUtils.getNumberOfThreads() <= 1 || j4 <= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
                    long j5 = (this.nl + j2) - 1;
                    long j6 = 0;
                    while (j6 < j4) {
                        long j7 = j2 + j6;
                        double d = doubleLargeArray2.getDouble(j7);
                        long j8 = j5 - j6;
                        doubleLargeArray2.setDouble(j7, doubleLargeArray2.getDouble(j8));
                        doubleLargeArray2.setDouble(j8, d);
                        j6++;
                        j5 = j5;
                    }
                } else {
                    long j9 = j4 / ((long) 2);
                    Future[] futureArr = new Future[2];
                    int i2 = 0;
                    while (i2 < 2) {
                        final long j10 = ((long) i2) * j9;
                        final long j11 = j;
                        Future[] futureArr2 = futureArr;
                        int i3 = i2;
                        final long j12 = i2 == i ? j4 : j10 + j9;
                        int i4 = i;
                        final DoubleLargeArray doubleLargeArray3 = doubleLargeArray;
                        futureArr2[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                long access$100 = (j11 + DoubleDST_1D.this.nl) - 1;
                                for (long j = j10; j < j12; j++) {
                                    long j2 = j11 + j;
                                    double d = doubleLargeArray3.getDouble(j2);
                                    long j3 = access$100 - j;
                                    DoubleLargeArray doubleLargeArray = doubleLargeArray3;
                                    doubleLargeArray.setDouble(j2, doubleLargeArray.getDouble(j3));
                                    doubleLargeArray3.setDouble(j3, d);
                                }
                            }
                        });
                        i2 = i3 + 1;
                        i = i4;
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
                this.dct.inverse(doubleLargeArray2, j2, z2);
                long j13 = this.nl + j2;
                for (long j14 = j2 + 1; j14 < j13; j14 += 2) {
                    doubleLargeArray2.setDouble(j14, -doubleLargeArray2.getDouble(j14));
                }
            } else if (doubleLargeArray.isLarge() || doubleLargeArray.isConstant() || j2 >= 2147483647L) {
                throw new IllegalArgumentException("The data array is too big.");
            } else {
                inverse(doubleLargeArray.getData(), (int) j2, z2);
            }
        }
    }
}
