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

public class DoubleFFT_2D {
    /* access modifiers changed from: private */
    public int columns;
    /* access modifiers changed from: private */
    public long columnsl;
    /* access modifiers changed from: private */
    public DoubleFFT_1D fftColumns;
    /* access modifiers changed from: private */
    public DoubleFFT_1D fftRows;
    private boolean isPowerOfTwo = false;
    /* access modifiers changed from: private */
    public int rows;
    /* access modifiers changed from: private */
    public long rowsl;
    private boolean useThreads = false;

    public DoubleFFT_2D(long j, long j2) {
        boolean z = false;
        if (j <= 1 || j2 <= 1) {
            throw new IllegalArgumentException("rows and columns must be greater than 1");
        }
        this.rows = (int) j;
        this.columns = (int) j2;
        this.rowsl = j;
        this.columnsl = j2;
        if (j * j2 >= CommonUtils.getThreadsBeginN_2D()) {
            this.useThreads = true;
        }
        if (CommonUtils.isPowerOf2(j) && CommonUtils.isPowerOf2(j2)) {
            this.isPowerOfTwo = true;
        }
        CommonUtils.setUseLargeArrays((2 * j) * j2 > ((long) LargeArray.getMaxSizeOf32bitArray()) ? true : z);
        DoubleFFT_1D doubleFFT_1D = new DoubleFFT_1D(j);
        this.fftRows = doubleFFT_1D;
        if (j == j2) {
            this.fftColumns = doubleFFT_1D;
        } else {
            this.fftColumns = new DoubleFFT_1D(j2);
        }
    }

    public void complexForward(double[] dArr) {
        int i;
        int i2;
        Class<DoubleFFT_2D> cls = DoubleFFT_2D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = 0;
        if (this.isPowerOfTwo) {
            this.columns *= 2;
            if (numberOfThreads <= 1 || !this.useThreads) {
                while (i3 < this.rows) {
                    this.fftColumns.complexForward(dArr, this.columns * i3);
                    i3++;
                }
                cdft2d_sub(-1, dArr, true);
            } else {
                xdft2d0_subth1(0, -1, dArr, true);
                cdft2d_subth(-1, dArr, true);
            }
            this.columns /= 2;
            return;
        }
        int i4 = this.columns;
        int i5 = i4 * 2;
        if (numberOfThreads <= 1 || !this.useThreads || (i2 = this.rows) < numberOfThreads || i4 < numberOfThreads) {
            int i6 = 0;
            while (true) {
                i = this.rows;
                if (i6 >= i) {
                    break;
                }
                this.fftColumns.complexForward(dArr, i6 * i5);
                i6++;
            }
            double[] dArr2 = new double[(i * 2)];
            for (int i7 = 0; i7 < this.columns; i7++) {
                int i8 = i7 * 2;
                for (int i9 = 0; i9 < this.rows; i9++) {
                    int i10 = i9 * 2;
                    int i11 = (i9 * i5) + i8;
                    dArr2[i10] = dArr[i11];
                    dArr2[i10 + 1] = dArr[i11 + 1];
                }
                this.fftRows.complexForward(dArr2);
                for (int i12 = 0; i12 < this.rows; i12++) {
                    int i13 = i12 * 2;
                    int i14 = (i12 * i5) + i8;
                    dArr[i14] = dArr2[i13];
                    dArr[i14 + 1] = dArr2[i13 + 1];
                }
            }
            return;
        }
        Future[] futureArr = new Future[numberOfThreads];
        int i15 = i2 / numberOfThreads;
        int i16 = 0;
        while (i16 < numberOfThreads) {
            final int i17 = i16 * i15;
            final int i18 = i16 == numberOfThreads + -1 ? this.rows : i17 + i15;
            final double[] dArr3 = dArr;
            final int i19 = i5;
            futureArr[i16] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    for (int i = i17; i < i18; i++) {
                        DoubleFFT_2D.this.fftColumns.complexForward(dArr3, i19 * i);
                    }
                }
            });
            i16++;
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
        } catch (ExecutionException e2) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
        }
        int i20 = this.columns / numberOfThreads;
        while (i3 < numberOfThreads) {
            final int i21 = i3 * i20;
            final int i22 = i3 == numberOfThreads + -1 ? this.columns : i21 + i20;
            final int i23 = i5;
            final double[] dArr4 = dArr;
            futureArr[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    double[] dArr = new double[(DoubleFFT_2D.this.rows * 2)];
                    for (int i = i21; i < i22; i++) {
                        int i2 = i * 2;
                        for (int i3 = 0; i3 < DoubleFFT_2D.this.rows; i3++) {
                            int i4 = i3 * 2;
                            int i5 = (i23 * i3) + i2;
                            double[] dArr2 = dArr4;
                            dArr[i4] = dArr2[i5];
                            dArr[i4 + 1] = dArr2[i5 + 1];
                        }
                        DoubleFFT_2D.this.fftRows.complexForward(dArr);
                        for (int i6 = 0; i6 < DoubleFFT_2D.this.rows; i6++) {
                            int i7 = i6 * 2;
                            int i8 = (i23 * i6) + i2;
                            double[] dArr3 = dArr4;
                            dArr3[i8] = dArr[i7];
                            dArr3[i8 + 1] = dArr[i7 + 1];
                        }
                    }
                }
            });
            i3++;
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e3) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e3);
        } catch (ExecutionException e4) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e4);
        }
    }

    public void complexForward(DoubleLargeArray doubleLargeArray) {
        DoubleFFT_2D doubleFFT_2D = this;
        DoubleLargeArray doubleLargeArray2 = doubleLargeArray;
        Class<DoubleFFT_2D> cls = DoubleFFT_2D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i = 0;
        long j = 2;
        if (doubleFFT_2D.isPowerOfTwo) {
            doubleFFT_2D.columnsl *= 2;
            if (numberOfThreads <= 1 || !doubleFFT_2D.useThreads) {
                while (true) {
                    long j2 = (long) i;
                    if (j2 >= doubleFFT_2D.rowsl) {
                        break;
                    }
                    doubleFFT_2D.fftColumns.complexForward(doubleLargeArray2, j2 * doubleFFT_2D.columnsl);
                    i++;
                }
                doubleFFT_2D.cdft2d_sub(-1, doubleLargeArray2, true);
            } else {
                xdft2d0_subth1(0, -1, doubleLargeArray, true);
                doubleFFT_2D.cdft2d_subth(-1, doubleLargeArray2, true);
            }
            doubleFFT_2D.columnsl /= 2;
            return;
        }
        long j3 = doubleFFT_2D.columnsl;
        long j4 = j3 * 2;
        if (numberOfThreads > 1 && doubleFFT_2D.useThreads) {
            long j5 = doubleFFT_2D.rowsl;
            long j6 = (long) numberOfThreads;
            if (j5 >= j6 && j3 >= j6) {
                Future[] futureArr = new Future[numberOfThreads];
                long j7 = j5 / j6;
                int i2 = 0;
                while (i2 < numberOfThreads) {
                    final long j8 = ((long) i2) * j7;
                    long j9 = j6;
                    final long j10 = i2 == numberOfThreads + -1 ? doubleFFT_2D.rowsl : j8 + j7;
                    final DoubleLargeArray doubleLargeArray3 = doubleLargeArray;
                    Future[] futureArr2 = futureArr;
                    final long j11 = j4;
                    futureArr2[i2] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            for (long j = j8; j < j10; j++) {
                                DoubleFFT_2D.this.fftColumns.complexForward(doubleLargeArray3, j11 * j);
                            }
                        }
                    });
                    i2++;
                    futureArr = futureArr2;
                    j6 = j9;
                }
                long j12 = j6;
                Future[] futureArr3 = futureArr;
                String str = null;
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException e) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
                } catch (ExecutionException e2) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
                }
                long j13 = doubleFFT_2D.columnsl / j12;
                while (i < numberOfThreads) {
                    final long j14 = ((long) i) * j13;
                    final long j15 = i == numberOfThreads + -1 ? doubleFFT_2D.columnsl : j14 + j13;
                    final long j16 = j4;
                    int i3 = numberOfThreads;
                    String str2 = str;
                    final DoubleLargeArray doubleLargeArray4 = doubleLargeArray;
                    futureArr3[i] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            long j = 2;
                            DoubleLargeArray doubleLargeArray = new DoubleLargeArray(DoubleFFT_2D.this.rowsl * 2, false);
                            long j2 = j14;
                            while (j2 < j15) {
                                long j3 = j2 * j;
                                long j4 = 0;
                                while (j4 < DoubleFFT_2D.this.rowsl) {
                                    long j5 = j4 * j;
                                    long j6 = (j16 * j4) + j3;
                                    doubleLargeArray.setDouble(j5, doubleLargeArray4.getDouble(j6));
                                    doubleLargeArray.setDouble(j5 + 1, doubleLargeArray4.getDouble(j6 + 1));
                                    j4++;
                                    j2 = j2;
                                    j = 2;
                                }
                                long j7 = j2;
                                DoubleFFT_2D.this.fftRows.complexForward(doubleLargeArray);
                                for (long j8 = 0; j8 < DoubleFFT_2D.this.rowsl; j8++) {
                                    long j9 = j8 * 2;
                                    long j10 = (j16 * j8) + j3;
                                    doubleLargeArray4.setDouble(j10, doubleLargeArray.getDouble(j9));
                                    doubleLargeArray4.setDouble(j10 + 1, doubleLargeArray.getDouble(j9 + 1));
                                }
                                j2 = j7 + 1;
                                j = 2;
                            }
                        }
                    });
                    i++;
                    str = str2;
                    numberOfThreads = i3;
                }
                String str3 = str;
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                    return;
                } catch (InterruptedException e3) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e3);
                    return;
                } catch (ExecutionException e4) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e4);
                    return;
                }
            }
        }
        for (long j17 = 0; j17 < doubleFFT_2D.rowsl; j17++) {
            doubleFFT_2D.fftColumns.complexForward(doubleLargeArray2, j17 * j4);
        }
        DoubleLargeArray doubleLargeArray5 = new DoubleLargeArray(doubleFFT_2D.rowsl * 2, false);
        long j18 = 0;
        while (j18 < doubleFFT_2D.columnsl) {
            long j19 = j18 * j;
            long j20 = 0;
            while (j20 < doubleFFT_2D.rowsl) {
                long j21 = j20 * j;
                long j22 = (j20 * j4) + j19;
                doubleLargeArray5.setDouble(j21, doubleLargeArray2.getDouble(j22));
                doubleLargeArray5.setDouble(j21 + 1, doubleLargeArray2.getDouble(j22 + 1));
                j20++;
                j18 = j18;
                j = 2;
            }
            long j23 = j18;
            doubleFFT_2D.fftRows.complexForward(doubleLargeArray5);
            long j24 = 0;
            while (j24 < doubleFFT_2D.rowsl) {
                long j25 = j24 * 2;
                long j26 = (j24 * j4) + j19;
                doubleLargeArray2.setDouble(j26, doubleLargeArray5.getDouble(j25));
                doubleLargeArray2.setDouble(j26 + 1, doubleLargeArray5.getDouble(j25 + 1));
                j24++;
                doubleFFT_2D = this;
            }
            j18 = j23 + 1;
            doubleFFT_2D = this;
            j = 2;
        }
    }

    public void complexForward(final double[][] dArr) {
        int i;
        int i2;
        Class<DoubleFFT_2D> cls = DoubleFFT_2D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = 0;
        if (this.isPowerOfTwo) {
            this.columns *= 2;
            if (numberOfThreads <= 1 || !this.useThreads) {
                while (i3 < this.rows) {
                    this.fftColumns.complexForward(dArr[i3]);
                    i3++;
                }
                cdft2d_sub(-1, dArr, true);
            } else {
                xdft2d0_subth1(0, -1, dArr, true);
                cdft2d_subth(-1, dArr, true);
            }
            this.columns /= 2;
        } else if (numberOfThreads <= 1 || !this.useThreads || (i2 = this.rows) < numberOfThreads || this.columns < numberOfThreads) {
            int i4 = 0;
            while (true) {
                i = this.rows;
                if (i4 >= i) {
                    break;
                }
                this.fftColumns.complexForward(dArr[i4]);
                i4++;
            }
            double[] dArr2 = new double[(i * 2)];
            for (int i5 = 0; i5 < this.columns; i5++) {
                int i6 = i5 * 2;
                for (int i7 = 0; i7 < this.rows; i7++) {
                    int i8 = i7 * 2;
                    double[] dArr3 = dArr[i7];
                    dArr2[i8] = dArr3[i6];
                    dArr2[i8 + 1] = dArr3[i6 + 1];
                }
                this.fftRows.complexForward(dArr2);
                for (int i9 = 0; i9 < this.rows; i9++) {
                    int i10 = i9 * 2;
                    double[] dArr4 = dArr[i9];
                    dArr4[i6] = dArr2[i10];
                    dArr4[i6 + 1] = dArr2[i10 + 1];
                }
            }
        } else {
            Future[] futureArr = new Future[numberOfThreads];
            int i11 = i2 / numberOfThreads;
            int i12 = 0;
            while (i12 < numberOfThreads) {
                final int i13 = i12 * i11;
                final int i14 = i12 == numberOfThreads + -1 ? this.rows : i13 + i11;
                futureArr[i12] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i13; i < i14; i++) {
                            DoubleFFT_2D.this.fftColumns.complexForward(dArr[i]);
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
            int i15 = this.columns / numberOfThreads;
            while (i3 < numberOfThreads) {
                final int i16 = i3 * i15;
                final int i17 = i3 == numberOfThreads + -1 ? this.columns : i16 + i15;
                futureArr[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        double[] dArr = new double[(DoubleFFT_2D.this.rows * 2)];
                        for (int i = i16; i < i17; i++) {
                            int i2 = i * 2;
                            for (int i3 = 0; i3 < DoubleFFT_2D.this.rows; i3++) {
                                int i4 = i3 * 2;
                                double[] dArr2 = dArr[i3];
                                dArr[i4] = dArr2[i2];
                                dArr[i4 + 1] = dArr2[i2 + 1];
                            }
                            DoubleFFT_2D.this.fftRows.complexForward(dArr);
                            for (int i5 = 0; i5 < DoubleFFT_2D.this.rows; i5++) {
                                int i6 = i5 * 2;
                                double[] dArr3 = dArr[i5];
                                dArr3[i2] = dArr[i6];
                                dArr3[i2 + 1] = dArr[i6 + 1];
                            }
                        }
                    }
                });
                i3++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e3) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e4);
            }
        }
    }

    public void complexInverse(double[] dArr, boolean z) {
        int i;
        int i2;
        double[] dArr2 = dArr;
        boolean z2 = z;
        Class<DoubleFFT_2D> cls = DoubleFFT_2D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = 0;
        if (this.isPowerOfTwo) {
            this.columns *= 2;
            if (numberOfThreads <= 1 || !this.useThreads) {
                while (i3 < this.rows) {
                    this.fftColumns.complexInverse(dArr2, this.columns * i3, z2);
                    i3++;
                }
                cdft2d_sub(1, dArr2, z2);
            } else {
                xdft2d0_subth1(0, 1, dArr2, z2);
                cdft2d_subth(1, dArr2, z2);
            }
            this.columns /= 2;
            return;
        }
        int i4 = this.columns;
        int i5 = i4 * 2;
        if (numberOfThreads <= 1 || !this.useThreads || (i2 = this.rows) < numberOfThreads || i4 < numberOfThreads) {
            int i6 = 0;
            while (true) {
                i = this.rows;
                if (i6 >= i) {
                    break;
                }
                this.fftColumns.complexInverse(dArr2, i6 * i5, z2);
                i6++;
            }
            double[] dArr3 = new double[(i * 2)];
            for (int i7 = 0; i7 < this.columns; i7++) {
                int i8 = i7 * 2;
                for (int i9 = 0; i9 < this.rows; i9++) {
                    int i10 = i9 * 2;
                    int i11 = (i9 * i5) + i8;
                    dArr3[i10] = dArr2[i11];
                    dArr3[i10 + 1] = dArr2[i11 + 1];
                }
                this.fftRows.complexInverse(dArr3, z2);
                for (int i12 = 0; i12 < this.rows; i12++) {
                    int i13 = i12 * 2;
                    int i14 = (i12 * i5) + i8;
                    dArr2[i14] = dArr3[i13];
                    dArr2[i14 + 1] = dArr3[i13 + 1];
                }
            }
            return;
        }
        Future[] futureArr = new Future[numberOfThreads];
        int i15 = i2 / numberOfThreads;
        int i16 = 0;
        while (i16 < numberOfThreads) {
            final int i17 = i16 * i15;
            final int i18 = i16 == numberOfThreads + -1 ? this.rows : i17 + i15;
            final double[] dArr4 = dArr;
            final int i19 = i5;
            int i20 = i16;
            final boolean z3 = z;
            futureArr[i20] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    for (int i = i17; i < i18; i++) {
                        DoubleFFT_2D.this.fftColumns.complexInverse(dArr4, i19 * i, z3);
                    }
                }
            });
            i16 = i20 + 1;
        }
        String str = null;
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
        } catch (ExecutionException e2) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
        }
        int i21 = this.columns / numberOfThreads;
        while (i3 < numberOfThreads) {
            final int i22 = i3 * i21;
            final int i23 = i3 == numberOfThreads + -1 ? this.columns : i22 + i21;
            final int i24 = i5;
            final double[] dArr5 = dArr;
            int i25 = numberOfThreads;
            String str2 = str;
            final boolean z4 = z;
            futureArr[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    double[] dArr = new double[(DoubleFFT_2D.this.rows * 2)];
                    for (int i = i22; i < i23; i++) {
                        int i2 = i * 2;
                        for (int i3 = 0; i3 < DoubleFFT_2D.this.rows; i3++) {
                            int i4 = i3 * 2;
                            int i5 = (i24 * i3) + i2;
                            double[] dArr2 = dArr5;
                            dArr[i4] = dArr2[i5];
                            dArr[i4 + 1] = dArr2[i5 + 1];
                        }
                        DoubleFFT_2D.this.fftRows.complexInverse(dArr, z4);
                        for (int i6 = 0; i6 < DoubleFFT_2D.this.rows; i6++) {
                            int i7 = i6 * 2;
                            int i8 = (i24 * i6) + i2;
                            double[] dArr3 = dArr5;
                            dArr3[i8] = dArr[i7];
                            dArr3[i8 + 1] = dArr[i7 + 1];
                        }
                    }
                }
            });
            i3++;
            str = str2;
            numberOfThreads = i25;
        }
        String str3 = str;
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e3) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e3);
        } catch (ExecutionException e4) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e4);
        }
    }

    public void complexInverse(DoubleLargeArray doubleLargeArray, boolean z) {
        DoubleLargeArray doubleLargeArray2 = doubleLargeArray;
        boolean z2 = z;
        Class<DoubleFFT_2D> cls = DoubleFFT_2D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        long j = 2;
        if (this.isPowerOfTwo) {
            this.columnsl *= 2;
            if (numberOfThreads <= 1 || !this.useThreads) {
                for (long j2 = 0; j2 < this.rowsl; j2++) {
                    this.fftColumns.complexInverse(doubleLargeArray2, this.columnsl * j2, z2);
                }
                cdft2d_sub(1, doubleLargeArray2, z2);
            } else {
                xdft2d0_subth1(0, 1, doubleLargeArray, z);
                cdft2d_subth(1, doubleLargeArray2, z2);
            }
            this.columnsl /= 2;
            return;
        }
        long j3 = this.columnsl;
        long j4 = j3 * 2;
        if (numberOfThreads > 1 && this.useThreads) {
            long j5 = this.rowsl;
            long j6 = (long) numberOfThreads;
            if (j5 >= j6 && j3 >= j6) {
                Future[] futureArr = new Future[numberOfThreads];
                long j7 = j5 / j6;
                int i = 0;
                while (i < numberOfThreads) {
                    final long j8 = ((long) i) * j7;
                    final long j9 = i == numberOfThreads + -1 ? this.rowsl : j8 + j7;
                    final DoubleLargeArray doubleLargeArray3 = doubleLargeArray;
                    Future[] futureArr2 = futureArr;
                    final long j10 = j4;
                    final boolean z3 = z;
                    futureArr2[i] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            for (long j = j8; j < j9; j++) {
                                DoubleFFT_2D.this.fftColumns.complexInverse(doubleLargeArray3, j10 * j, z3);
                            }
                        }
                    });
                    i++;
                    futureArr = futureArr2;
                }
                Future[] futureArr3 = futureArr;
                String str = null;
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException e) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
                } catch (ExecutionException e2) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
                }
                long j11 = this.columnsl / j6;
                int i2 = 0;
                while (i2 < numberOfThreads) {
                    final long j12 = ((long) i2) * j11;
                    final long j13 = i2 == numberOfThreads + -1 ? this.columnsl : j12 + j11;
                    final long j14 = j4;
                    final DoubleLargeArray doubleLargeArray4 = doubleLargeArray;
                    long j15 = j11;
                    String str2 = str;
                    final boolean z4 = z;
                    futureArr3[i2] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            long j = 2;
                            DoubleLargeArray doubleLargeArray = new DoubleLargeArray(DoubleFFT_2D.this.rowsl * 2, false);
                            long j2 = j12;
                            while (j2 < j13) {
                                long j3 = j2 * j;
                                long j4 = 0;
                                while (j4 < DoubleFFT_2D.this.rowsl) {
                                    long j5 = j4 * j;
                                    long j6 = (j14 * j4) + j3;
                                    doubleLargeArray.setDouble(j5, doubleLargeArray4.getDouble(j6));
                                    doubleLargeArray.setDouble(j5 + 1, doubleLargeArray4.getDouble(j6 + 1));
                                    j4++;
                                    j2 = j2;
                                    j = 2;
                                }
                                long j7 = j2;
                                DoubleFFT_2D.this.fftRows.complexInverse(doubleLargeArray, z4);
                                for (long j8 = 0; j8 < DoubleFFT_2D.this.rowsl; j8++) {
                                    long j9 = j8 * 2;
                                    long j10 = (j14 * j8) + j3;
                                    doubleLargeArray4.setDouble(j10, doubleLargeArray.getDouble(j9));
                                    doubleLargeArray4.setDouble(j10 + 1, doubleLargeArray.getDouble(j9 + 1));
                                }
                                j2 = j7 + 1;
                                j = 2;
                            }
                        }
                    });
                    i2++;
                    str = str2;
                    j11 = j15;
                }
                String str3 = str;
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                    return;
                } catch (InterruptedException e3) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e3);
                    return;
                } catch (ExecutionException e4) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e4);
                    return;
                }
            }
        }
        for (long j16 = 0; j16 < this.rowsl; j16++) {
            this.fftColumns.complexInverse(doubleLargeArray, j16 * j4, z);
        }
        DoubleLargeArray doubleLargeArray5 = doubleLargeArray;
        boolean z5 = z;
        DoubleLargeArray doubleLargeArray6 = new DoubleLargeArray(this.rowsl * 2, false);
        long j17 = 0;
        while (j17 < this.columnsl) {
            long j18 = j17 * j;
            long j19 = 0;
            while (j19 < this.rowsl) {
                long j20 = j19 * j;
                long j21 = (j19 * j4) + j18;
                doubleLargeArray6.setDouble(j20, doubleLargeArray5.getDouble(j21));
                doubleLargeArray6.setDouble(j20 + 1, doubleLargeArray5.getDouble(j21 + 1));
                j19++;
                j17 = j17;
                j = 2;
            }
            long j22 = j17;
            this.fftRows.complexInverse(doubleLargeArray6, z5);
            for (long j23 = 0; j23 < this.rowsl; j23++) {
                long j24 = j23 * 2;
                long j25 = (j23 * j4) + j18;
                doubleLargeArray5.setDouble(j25, doubleLargeArray6.getDouble(j24));
                doubleLargeArray5.setDouble(j25 + 1, doubleLargeArray6.getDouble(j24 + 1));
            }
            j17 = j22 + 1;
            j = 2;
        }
    }

    public void complexInverse(double[][] dArr, boolean z) {
        int i;
        int i2;
        Class<DoubleFFT_2D> cls = DoubleFFT_2D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = 0;
        if (this.isPowerOfTwo) {
            this.columns *= 2;
            if (numberOfThreads <= 1 || !this.useThreads) {
                while (i3 < this.rows) {
                    this.fftColumns.complexInverse(dArr[i3], z);
                    i3++;
                }
                cdft2d_sub(1, dArr, z);
            } else {
                xdft2d0_subth1(0, 1, dArr, z);
                cdft2d_subth(1, dArr, z);
            }
            this.columns /= 2;
        } else if (numberOfThreads <= 1 || !this.useThreads || (i2 = this.rows) < numberOfThreads || this.columns < numberOfThreads) {
            int i4 = 0;
            while (true) {
                i = this.rows;
                if (i4 >= i) {
                    break;
                }
                this.fftColumns.complexInverse(dArr[i4], z);
                i4++;
            }
            double[] dArr2 = new double[(i * 2)];
            for (int i5 = 0; i5 < this.columns; i5++) {
                int i6 = i5 * 2;
                for (int i7 = 0; i7 < this.rows; i7++) {
                    int i8 = i7 * 2;
                    double[] dArr3 = dArr[i7];
                    dArr2[i8] = dArr3[i6];
                    dArr2[i8 + 1] = dArr3[i6 + 1];
                }
                this.fftRows.complexInverse(dArr2, z);
                for (int i9 = 0; i9 < this.rows; i9++) {
                    int i10 = i9 * 2;
                    double[] dArr4 = dArr[i9];
                    dArr4[i6] = dArr2[i10];
                    dArr4[i6 + 1] = dArr2[i10 + 1];
                }
            }
        } else {
            Future[] futureArr = new Future[numberOfThreads];
            int i11 = i2 / numberOfThreads;
            int i12 = 0;
            while (i12 < numberOfThreads) {
                final int i13 = i12 * i11;
                final int i14 = i12 == numberOfThreads + -1 ? this.rows : i13 + i11;
                final double[][] dArr5 = dArr;
                final boolean z2 = z;
                futureArr[i12] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i13; i < i14; i++) {
                            DoubleFFT_2D.this.fftColumns.complexInverse(dArr5[i], z2);
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
            int i15 = this.columns / numberOfThreads;
            while (i3 < numberOfThreads) {
                final int i16 = i3 * i15;
                final int i17 = i3 == numberOfThreads + -1 ? this.columns : i16 + i15;
                final double[][] dArr6 = dArr;
                final boolean z3 = z;
                futureArr[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        double[] dArr = new double[(DoubleFFT_2D.this.rows * 2)];
                        for (int i = i16; i < i17; i++) {
                            int i2 = i * 2;
                            for (int i3 = 0; i3 < DoubleFFT_2D.this.rows; i3++) {
                                int i4 = i3 * 2;
                                double[] dArr2 = dArr6[i3];
                                dArr[i4] = dArr2[i2];
                                dArr[i4 + 1] = dArr2[i2 + 1];
                            }
                            DoubleFFT_2D.this.fftRows.complexInverse(dArr, z3);
                            for (int i5 = 0; i5 < DoubleFFT_2D.this.rows; i5++) {
                                int i6 = i5 * 2;
                                double[] dArr3 = dArr6[i5];
                                dArr3[i2] = dArr[i6];
                                dArr3[i2 + 1] = dArr[i6 + 1];
                            }
                        }
                    }
                });
                i3++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e3) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e4);
            }
        }
    }

    public void realForward(double[] dArr) {
        if (!this.isPowerOfTwo) {
            throw new IllegalArgumentException("rows and columns must be power of two numbers");
        } else if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
            for (int i = 0; i < this.rows; i++) {
                this.fftColumns.realForward(dArr, this.columns * i);
            }
            cdft2d_sub(-1, dArr, true);
            rdft2d_sub(1, dArr);
        } else {
            xdft2d0_subth1(1, 1, dArr, true);
            cdft2d_subth(-1, dArr, true);
            rdft2d_sub(1, dArr);
        }
    }

    public void realForward(DoubleLargeArray doubleLargeArray) {
        if (!this.isPowerOfTwo) {
            throw new IllegalArgumentException("rows and columns must be power of two numbers");
        } else if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
            for (long j = 0; j < this.rowsl; j++) {
                this.fftColumns.realForward(doubleLargeArray, this.columnsl * j);
            }
            cdft2d_sub(-1, doubleLargeArray, true);
            rdft2d_sub(1, doubleLargeArray);
        } else {
            xdft2d0_subth1(1, 1, doubleLargeArray, true);
            cdft2d_subth(-1, doubleLargeArray, true);
            rdft2d_sub(1, doubleLargeArray);
        }
    }

    public void realForward(double[][] dArr) {
        if (!this.isPowerOfTwo) {
            throw new IllegalArgumentException("rows and columns must be power of two numbers");
        } else if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
            for (int i = 0; i < this.rows; i++) {
                this.fftColumns.realForward(dArr[i]);
            }
            cdft2d_sub(-1, dArr, true);
            rdft2d_sub(1, dArr);
        } else {
            xdft2d0_subth1(1, 1, dArr, true);
            cdft2d_subth(-1, dArr, true);
            rdft2d_sub(1, dArr);
        }
    }

    public void realForwardFull(double[] dArr) {
        if (this.isPowerOfTwo) {
            if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
                for (int i = 0; i < this.rows; i++) {
                    this.fftColumns.realForward(dArr, this.columns * i);
                }
                cdft2d_sub(-1, dArr, true);
                rdft2d_sub(1, dArr);
            } else {
                xdft2d0_subth1(1, 1, dArr, true);
                cdft2d_subth(-1, dArr, true);
                rdft2d_sub(1, dArr);
            }
            fillSymmetric(dArr);
            return;
        }
        mixedRadixRealForwardFull(dArr);
    }

    public void realForwardFull(DoubleLargeArray doubleLargeArray) {
        if (this.isPowerOfTwo) {
            if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
                for (long j = 0; j < this.rowsl; j++) {
                    this.fftColumns.realForward(doubleLargeArray, this.columnsl * j);
                }
                cdft2d_sub(-1, doubleLargeArray, true);
                rdft2d_sub(1, doubleLargeArray);
            } else {
                xdft2d0_subth1(1, 1, doubleLargeArray, true);
                cdft2d_subth(-1, doubleLargeArray, true);
                rdft2d_sub(1, doubleLargeArray);
            }
            fillSymmetric(doubleLargeArray);
            return;
        }
        mixedRadixRealForwardFull(doubleLargeArray);
    }

    public void realForwardFull(double[][] dArr) {
        if (this.isPowerOfTwo) {
            if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
                for (int i = 0; i < this.rows; i++) {
                    this.fftColumns.realForward(dArr[i]);
                }
                cdft2d_sub(-1, dArr, true);
                rdft2d_sub(1, dArr);
            } else {
                xdft2d0_subth1(1, 1, dArr, true);
                cdft2d_subth(-1, dArr, true);
                rdft2d_sub(1, dArr);
            }
            fillSymmetric(dArr);
            return;
        }
        mixedRadixRealForwardFull(dArr);
    }

    public void realInverse(double[] dArr, boolean z) {
        if (!this.isPowerOfTwo) {
            throw new IllegalArgumentException("rows and columns must be power of two numbers");
        } else if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
            rdft2d_sub(-1, dArr);
            cdft2d_sub(1, dArr, z);
            for (int i = 0; i < this.rows; i++) {
                this.fftColumns.realInverse(dArr, this.columns * i, z);
            }
        } else {
            rdft2d_sub(-1, dArr);
            cdft2d_subth(1, dArr, z);
            xdft2d0_subth1(1, -1, dArr, z);
        }
    }

    public void realInverse(DoubleLargeArray doubleLargeArray, boolean z) {
        if (!this.isPowerOfTwo) {
            throw new IllegalArgumentException("rows and columns must be power of two numbers");
        } else if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
            rdft2d_sub(-1, doubleLargeArray);
            cdft2d_sub(1, doubleLargeArray, z);
            for (long j = 0; j < this.rowsl; j++) {
                this.fftColumns.realInverse(doubleLargeArray, this.columnsl * j, z);
            }
        } else {
            rdft2d_sub(-1, doubleLargeArray);
            cdft2d_subth(1, doubleLargeArray, z);
            xdft2d0_subth1(1, -1, doubleLargeArray, z);
        }
    }

    public void realInverse(double[][] dArr, boolean z) {
        if (!this.isPowerOfTwo) {
            throw new IllegalArgumentException("rows and columns must be power of two numbers");
        } else if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
            rdft2d_sub(-1, dArr);
            cdft2d_sub(1, dArr, z);
            for (int i = 0; i < this.rows; i++) {
                this.fftColumns.realInverse(dArr[i], z);
            }
        } else {
            rdft2d_sub(-1, dArr);
            cdft2d_subth(1, dArr, z);
            xdft2d0_subth1(1, -1, dArr, z);
        }
    }

    public void realInverseFull(double[] dArr, boolean z) {
        if (this.isPowerOfTwo) {
            if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
                for (int i = 0; i < this.rows; i++) {
                    this.fftColumns.realInverse2(dArr, this.columns * i, z);
                }
                cdft2d_sub(1, dArr, z);
                rdft2d_sub(1, dArr);
            } else {
                xdft2d0_subth2(1, -1, dArr, z);
                cdft2d_subth(1, dArr, z);
                rdft2d_sub(1, dArr);
            }
            fillSymmetric(dArr);
            return;
        }
        mixedRadixRealInverseFull(dArr, z);
    }

    public void realInverseFull(DoubleLargeArray doubleLargeArray, boolean z) {
        if (this.isPowerOfTwo) {
            if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
                for (long j = 0; j < this.rowsl; j++) {
                    this.fftColumns.realInverse2(doubleLargeArray, this.columnsl * j, z);
                }
                cdft2d_sub(1, doubleLargeArray, z);
                rdft2d_sub(1, doubleLargeArray);
            } else {
                xdft2d0_subth2(1, -1, doubleLargeArray, z);
                cdft2d_subth(1, doubleLargeArray, z);
                rdft2d_sub(1, doubleLargeArray);
            }
            fillSymmetric(doubleLargeArray);
            return;
        }
        mixedRadixRealInverseFull(doubleLargeArray, z);
    }

    public void realInverseFull(double[][] dArr, boolean z) {
        if (this.isPowerOfTwo) {
            if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
                for (int i = 0; i < this.rows; i++) {
                    this.fftColumns.realInverse2(dArr[i], 0, z);
                }
                cdft2d_sub(1, dArr, z);
                rdft2d_sub(1, dArr);
            } else {
                xdft2d0_subth2(1, -1, dArr, z);
                cdft2d_subth(1, dArr, z);
                rdft2d_sub(1, dArr);
            }
            fillSymmetric(dArr);
            return;
        }
        mixedRadixRealInverseFull(dArr, z);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v0, resolved type: double[][]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void mixedRadixRealForwardFull(double[][] r21) {
        /*
            r20 = this;
            r8 = r20
            r9 = r21
            java.lang.Class<org.jtransforms.fft.DoubleFFT_2D> r10 = org.jtransforms.fft.DoubleFFT_2D.class
            int r0 = r8.columns
            r7 = 2
            int r11 = r0 / 2
            int r12 = r11 + 1
            int r0 = r8.rows
            int r0 = r0 * r7
            int[] r1 = new int[r7]
            r13 = 1
            r1[r13] = r0
            r14 = 0
            r1[r14] = r12
            java.lang.Class r0 = java.lang.Double.TYPE
            java.lang.Object r0 = java.lang.reflect.Array.newInstance(r0, r1)
            r15 = r0
            double[][] r15 = (double[][]) r15
            int r6 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getNumberOfThreads()
            if (r6 <= r13) goto L_0x01c3
            boolean r0 = r8.useThreads
            if (r0 == 0) goto L_0x01c3
            int r0 = r8.rows
            if (r0 < r6) goto L_0x01c3
            int r1 = r11 + -1
            if (r1 < r6) goto L_0x01c3
            java.util.concurrent.Future[] r5 = new java.util.concurrent.Future[r6]
            int r0 = r0 / r6
            r2 = r14
        L_0x0037:
            if (r2 >= r6) goto L_0x0053
            int r3 = r2 * r0
            int r4 = r6 + -1
            if (r2 != r4) goto L_0x0042
            int r4 = r8.rows
            goto L_0x0044
        L_0x0042:
            int r4 = r3 + r0
        L_0x0044:
            org.jtransforms.fft.DoubleFFT_2D$13 r7 = new org.jtransforms.fft.DoubleFFT_2D$13
            r7.<init>(r3, r4, r9)
            java.util.concurrent.Future r3 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r7)
            r5[r2] = r3
            int r2 = r2 + 1
            r7 = 2
            goto L_0x0037
        L_0x0053:
            r7 = 0
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r5)     // Catch:{ InterruptedException -> 0x0068, ExecutionException -> 0x0058 }
            goto L_0x0077
        L_0x0058:
            r0 = move-exception
            r2 = r0
            java.lang.String r0 = r10.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r3 = java.util.logging.Level.SEVERE
            r0.log(r3, r7, r2)
            goto L_0x0077
        L_0x0068:
            r0 = move-exception
            r2 = r0
            java.lang.String r0 = r10.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r3 = java.util.logging.Level.SEVERE
            r0.log(r3, r7, r2)
        L_0x0077:
            r0 = r14
        L_0x0078:
            int r2 = r8.rows
            if (r0 >= r2) goto L_0x0087
            r2 = r15[r14]
            r3 = r9[r0]
            r17 = r3[r14]
            r2[r0] = r17
            int r0 = r0 + 1
            goto L_0x0078
        L_0x0087:
            org.jtransforms.fft.DoubleFFT_1D r0 = r8.fftRows
            r2 = r15[r14]
            r0.realForwardFull((double[]) r2)
            int r0 = r1 / r6
            r4 = r14
        L_0x0091:
            if (r4 >= r6) goto L_0x00c2
            int r1 = r4 * r0
            int r3 = r1 + 1
            int r1 = r6 + -1
            if (r4 != r1) goto L_0x009e
            r17 = r11
            goto L_0x00a2
        L_0x009e:
            int r1 = r3 + r0
            r17 = r1
        L_0x00a2:
            org.jtransforms.fft.DoubleFFT_2D$14 r18 = new org.jtransforms.fft.DoubleFFT_2D$14
            r1 = r18
            r2 = r20
            r19 = r4
            r4 = r17
            r17 = r5
            r5 = r15
            r14 = r6
            r6 = r21
            r1.<init>(r3, r4, r5, r6)
            java.util.concurrent.Future r1 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r18)
            r17[r19] = r1
            int r4 = r19 + 1
            r6 = r14
            r5 = r17
            r14 = 0
            goto L_0x0091
        L_0x00c2:
            r17 = r5
            r14 = r6
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r17)     // Catch:{ InterruptedException -> 0x00d9, ExecutionException -> 0x00c9 }
            goto L_0x00e8
        L_0x00c9:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = r10.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            r0.log(r2, r7, r1)
            goto L_0x00e8
        L_0x00d9:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = r10.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            r0.log(r2, r7, r1)
        L_0x00e8:
            int r0 = r8.columns
            r1 = 2
            int r0 = r0 % r1
            if (r0 != 0) goto L_0x0106
            r0 = 0
        L_0x00ef:
            int r1 = r8.rows
            if (r0 >= r1) goto L_0x00fe
            r1 = r15[r11]
            r2 = r9[r0]
            r3 = r2[r13]
            r1[r0] = r3
            int r0 = r0 + 1
            goto L_0x00ef
        L_0x00fe:
            org.jtransforms.fft.DoubleFFT_1D r0 = r8.fftRows
            r1 = r15[r11]
            r0.realForwardFull((double[]) r1)
            goto L_0x0126
        L_0x0106:
            r0 = 0
        L_0x0107:
            int r1 = r8.rows
            if (r0 >= r1) goto L_0x011f
            int r1 = r0 * 2
            r2 = r15[r11]
            r3 = r9[r0]
            int r4 = r11 * 2
            r4 = r3[r4]
            r2[r1] = r4
            int r1 = r1 + r13
            r4 = r3[r13]
            r2[r1] = r4
            int r0 = r0 + 1
            goto L_0x0107
        L_0x011f:
            org.jtransforms.fft.DoubleFFT_1D r0 = r8.fftRows
            r1 = r15[r11]
            r0.complexForward((double[]) r1)
        L_0x0126:
            int r0 = r8.rows
            int r11 = r0 / r14
            r0 = 0
        L_0x012b:
            if (r0 >= r14) goto L_0x0152
            int r3 = r0 * r11
            int r6 = r14 + -1
            if (r0 != r6) goto L_0x0136
            int r1 = r8.rows
            goto L_0x0138
        L_0x0136:
            int r1 = r3 + r11
        L_0x0138:
            r4 = r1
            org.jtransforms.fft.DoubleFFT_2D$15 r16 = new org.jtransforms.fft.DoubleFFT_2D$15
            r1 = r16
            r2 = r20
            r5 = r12
            r6 = r21
            r13 = r7
            r7 = r15
            r1.<init>(r3, r4, r5, r6, r7)
            java.util.concurrent.Future r1 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r16)
            r17[r0] = r1
            int r0 = r0 + 1
            r7 = r13
            r13 = 1
            goto L_0x012b
        L_0x0152:
            r13 = r7
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r17)     // Catch:{ InterruptedException -> 0x0167, ExecutionException -> 0x0157 }
            goto L_0x0176
        L_0x0157:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = r10.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            r0.log(r2, r13, r1)
            goto L_0x0176
        L_0x0167:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = r10.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            r0.log(r2, r13, r1)
        L_0x0176:
            r0 = 0
        L_0x0177:
            if (r0 >= r14) goto L_0x019c
            int r1 = r0 * r11
            r2 = 1
            int r3 = r1 + 1
            int r6 = r14 + -1
            if (r0 != r6) goto L_0x0185
            int r1 = r8.rows
            goto L_0x0187
        L_0x0185:
            int r1 = r3 + r11
        L_0x0187:
            r4 = r1
            org.jtransforms.fft.DoubleFFT_2D$16 r7 = new org.jtransforms.fft.DoubleFFT_2D$16
            r1 = r7
            r2 = r20
            r5 = r12
            r6 = r21
            r1.<init>(r3, r4, r5, r6)
            java.util.concurrent.Future r1 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r7)
            r17[r0] = r1
            int r0 = r0 + 1
            goto L_0x0177
        L_0x019c:
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r17)     // Catch:{ InterruptedException -> 0x01b2, ExecutionException -> 0x01a1 }
            goto L_0x02ad
        L_0x01a1:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = r10.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            r0.log(r2, r13, r1)
            goto L_0x02ad
        L_0x01b2:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = r10.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            r0.log(r2, r13, r1)
            goto L_0x02ad
        L_0x01c3:
            r0 = 0
        L_0x01c4:
            int r1 = r8.rows
            if (r0 >= r1) goto L_0x01d2
            org.jtransforms.fft.DoubleFFT_1D r1 = r8.fftColumns
            r2 = r9[r0]
            r1.realForward((double[]) r2)
            int r0 = r0 + 1
            goto L_0x01c4
        L_0x01d2:
            r0 = 0
        L_0x01d3:
            int r1 = r8.rows
            if (r0 >= r1) goto L_0x01e3
            r1 = 0
            r2 = r15[r1]
            r3 = r9[r0]
            r4 = r3[r1]
            r2[r0] = r4
            int r0 = r0 + 1
            goto L_0x01d3
        L_0x01e3:
            r1 = 0
            org.jtransforms.fft.DoubleFFT_1D r0 = r8.fftRows
            r2 = r15[r1]
            r0.realForwardFull((double[]) r2)
            r0 = 1
        L_0x01ec:
            if (r0 >= r11) goto L_0x0214
            int r1 = r0 * 2
            r2 = 0
        L_0x01f1:
            int r3 = r8.rows
            if (r2 >= r3) goto L_0x020a
            int r3 = r2 * 2
            r4 = r15[r0]
            r5 = r9[r2]
            r6 = r5[r1]
            r4[r3] = r6
            r6 = 1
            int r3 = r3 + r6
            int r13 = r1 + 1
            r6 = r5[r13]
            r4[r3] = r6
            int r2 = r2 + 1
            goto L_0x01f1
        L_0x020a:
            org.jtransforms.fft.DoubleFFT_1D r1 = r8.fftRows
            r2 = r15[r0]
            r1.complexForward((double[]) r2)
            int r0 = r0 + 1
            goto L_0x01ec
        L_0x0214:
            int r0 = r8.columns
            r1 = 2
            int r0 = r0 % r1
            if (r0 != 0) goto L_0x0233
            r1 = 0
        L_0x021b:
            int r0 = r8.rows
            if (r1 >= r0) goto L_0x022b
            r0 = r15[r11]
            r2 = r9[r1]
            r3 = 1
            r4 = r2[r3]
            r0[r1] = r4
            int r1 = r1 + 1
            goto L_0x021b
        L_0x022b:
            org.jtransforms.fft.DoubleFFT_1D r0 = r8.fftRows
            r1 = r15[r11]
            r0.realForwardFull((double[]) r1)
            goto L_0x0254
        L_0x0233:
            r1 = 0
        L_0x0234:
            int r0 = r8.rows
            if (r1 >= r0) goto L_0x024d
            int r0 = r1 * 2
            r2 = r15[r11]
            r3 = r9[r1]
            int r4 = r11 * 2
            r4 = r3[r4]
            r2[r0] = r4
            r4 = 1
            int r0 = r0 + r4
            r5 = r3[r4]
            r2[r0] = r5
            int r1 = r1 + 1
            goto L_0x0234
        L_0x024d:
            org.jtransforms.fft.DoubleFFT_1D r0 = r8.fftRows
            r1 = r15[r11]
            r0.complexForward((double[]) r1)
        L_0x0254:
            r1 = 0
        L_0x0255:
            int r0 = r8.rows
            if (r1 >= r0) goto L_0x0277
            int r0 = r1 * 2
            r2 = 0
        L_0x025c:
            if (r2 >= r12) goto L_0x0273
            int r3 = r2 * 2
            r4 = r9[r1]
            r5 = r15[r2]
            r6 = r5[r0]
            r4[r3] = r6
            r6 = 1
            int r3 = r3 + r6
            int r13 = r0 + 1
            r10 = r5[r13]
            r4[r3] = r10
            int r2 = r2 + 1
            goto L_0x025c
        L_0x0273:
            r6 = 1
            int r1 = r1 + 1
            goto L_0x0255
        L_0x0277:
            r6 = 1
            r13 = r6
        L_0x0279:
            int r0 = r8.rows
            if (r13 >= r0) goto L_0x02ad
            int r0 = r0 - r13
            r1 = r12
        L_0x027f:
            int r2 = r8.columns
            if (r1 >= r2) goto L_0x02a8
            int r3 = r1 * 2
            int r2 = r2 - r1
            r4 = 2
            int r2 = r2 * r4
            r5 = 0
            r6 = r9[r5]
            r10 = r6[r2]
            r6[r3] = r10
            int r7 = r3 + 1
            int r10 = r2 + 1
            r14 = r6[r10]
            double r14 = -r14
            r6[r7] = r14
            r6 = r9[r13]
            r11 = r9[r0]
            r14 = r11[r2]
            r6[r3] = r14
            r2 = r11[r10]
            double r2 = -r2
            r6[r7] = r2
            int r1 = r1 + 1
            goto L_0x027f
        L_0x02a8:
            r4 = 2
            r5 = 0
            int r13 = r13 + 1
            goto L_0x0279
        L_0x02ad:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.DoubleFFT_2D.mixedRadixRealForwardFull(double[][]):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v0, resolved type: double[][]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void mixedRadixRealForwardFull(double[] r22) {
        /*
            r21 = this;
            r9 = r21
            r10 = r22
            java.lang.Class<org.jtransforms.fft.DoubleFFT_2D> r11 = org.jtransforms.fft.DoubleFFT_2D.class
            int r0 = r9.columns
            int r12 = r0 * 2
            r7 = 2
            int r8 = r0 / 2
            int r13 = r8 + 1
            int r0 = r9.rows
            int r0 = r0 * r7
            int[] r1 = new int[r7]
            r14 = 1
            r1[r14] = r0
            r15 = 0
            r1[r15] = r13
            java.lang.Class r0 = java.lang.Double.TYPE
            java.lang.Object r0 = java.lang.reflect.Array.newInstance(r0, r1)
            r16 = r0
            double[][] r16 = (double[][]) r16
            int r6 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getNumberOfThreads()
            if (r6 <= r14) goto L_0x01d1
            boolean r0 = r9.useThreads
            if (r0 == 0) goto L_0x01d1
            int r0 = r9.rows
            if (r0 < r6) goto L_0x01d1
            int r1 = r8 + -1
            if (r1 < r6) goto L_0x01d1
            java.util.concurrent.Future[] r5 = new java.util.concurrent.Future[r6]
            int r0 = r0 / r6
            r2 = r15
        L_0x003a:
            if (r2 >= r6) goto L_0x0056
            int r3 = r2 * r0
            int r4 = r6 + -1
            if (r2 != r4) goto L_0x0045
            int r4 = r9.rows
            goto L_0x0047
        L_0x0045:
            int r4 = r3 + r0
        L_0x0047:
            org.jtransforms.fft.DoubleFFT_2D$17 r7 = new org.jtransforms.fft.DoubleFFT_2D$17
            r7.<init>(r3, r4, r10)
            java.util.concurrent.Future r3 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r7)
            r5[r2] = r3
            int r2 = r2 + 1
            r7 = 2
            goto L_0x003a
        L_0x0056:
            r7 = 0
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r5)     // Catch:{ InterruptedException -> 0x006b, ExecutionException -> 0x005b }
            goto L_0x007a
        L_0x005b:
            r0 = move-exception
            r2 = r0
            java.lang.String r0 = r11.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r3 = java.util.logging.Level.SEVERE
            r0.log(r3, r7, r2)
            goto L_0x007a
        L_0x006b:
            r0 = move-exception
            r2 = r0
            java.lang.String r0 = r11.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r3 = java.util.logging.Level.SEVERE
            r0.log(r3, r7, r2)
        L_0x007a:
            r0 = r15
        L_0x007b:
            int r2 = r9.rows
            if (r0 >= r2) goto L_0x008b
            r2 = r16[r15]
            int r3 = r9.columns
            int r3 = r3 * r0
            r3 = r10[r3]
            r2[r0] = r3
            int r0 = r0 + 1
            goto L_0x007b
        L_0x008b:
            org.jtransforms.fft.DoubleFFT_1D r0 = r9.fftRows
            r2 = r16[r15]
            r0.realForwardFull((double[]) r2)
            int r0 = r1 / r6
            r4 = r15
        L_0x0095:
            if (r4 >= r6) goto L_0x00c7
            int r1 = r4 * r0
            int r3 = r1 + 1
            int r1 = r6 + -1
            if (r4 != r1) goto L_0x00a2
            r18 = r8
            goto L_0x00a6
        L_0x00a2:
            int r1 = r3 + r0
            r18 = r1
        L_0x00a6:
            org.jtransforms.fft.DoubleFFT_2D$18 r19 = new org.jtransforms.fft.DoubleFFT_2D$18
            r1 = r19
            r2 = r21
            r20 = r4
            r4 = r18
            r18 = r5
            r5 = r16
            r15 = r6
            r6 = r22
            r1.<init>(r3, r4, r5, r6)
            java.util.concurrent.Future r1 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r19)
            r18[r20] = r1
            int r4 = r20 + 1
            r6 = r15
            r5 = r18
            r15 = 0
            goto L_0x0095
        L_0x00c7:
            r18 = r5
            r15 = r6
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r18)     // Catch:{ InterruptedException -> 0x00de, ExecutionException -> 0x00ce }
            goto L_0x00ed
        L_0x00ce:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = r11.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            r0.log(r2, r7, r1)
            goto L_0x00ed
        L_0x00de:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = r11.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            r0.log(r2, r7, r1)
        L_0x00ed:
            int r0 = r9.columns
            r1 = 2
            int r0 = r0 % r1
            if (r0 != 0) goto L_0x010d
            r0 = 0
        L_0x00f4:
            int r1 = r9.rows
            if (r0 >= r1) goto L_0x0105
            r1 = r16[r8]
            int r2 = r9.columns
            int r2 = r2 * r0
            int r2 = r2 + r14
            r2 = r10[r2]
            r1[r0] = r2
            int r0 = r0 + 1
            goto L_0x00f4
        L_0x0105:
            org.jtransforms.fft.DoubleFFT_1D r0 = r9.fftRows
            r1 = r16[r8]
            r0.realForwardFull((double[]) r1)
            goto L_0x0130
        L_0x010d:
            r0 = 0
        L_0x010e:
            int r1 = r9.rows
            if (r0 >= r1) goto L_0x0129
            int r1 = r0 * 2
            int r2 = r9.columns
            int r2 = r2 * r0
            r3 = r16[r8]
            int r4 = r8 * 2
            int r4 = r4 + r2
            r4 = r10[r4]
            r3[r1] = r4
            int r1 = r1 + r14
            int r2 = r2 + r14
            r4 = r10[r2]
            r3[r1] = r4
            int r0 = r0 + 1
            goto L_0x010e
        L_0x0129:
            org.jtransforms.fft.DoubleFFT_1D r0 = r9.fftRows
            r1 = r16[r8]
            r0.complexForward((double[]) r1)
        L_0x0130:
            int r0 = r9.rows
            int r17 = r0 / r15
            r0 = 0
        L_0x0135:
            if (r0 >= r15) goto L_0x015f
            int r3 = r0 * r17
            int r6 = r15 + -1
            if (r0 != r6) goto L_0x0140
            int r1 = r9.rows
            goto L_0x0142
        L_0x0140:
            int r1 = r3 + r17
        L_0x0142:
            r4 = r1
            org.jtransforms.fft.DoubleFFT_2D$19 r19 = new org.jtransforms.fft.DoubleFFT_2D$19
            r1 = r19
            r2 = r21
            r5 = r13
            r6 = r12
            r8 = r7
            r7 = r22
            r14 = r8
            r8 = r16
            r1.<init>(r3, r4, r5, r6, r7, r8)
            java.util.concurrent.Future r1 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r19)
            r18[r0] = r1
            int r0 = r0 + 1
            r7 = r14
            r14 = 1
            goto L_0x0135
        L_0x015f:
            r14 = r7
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r18)     // Catch:{ InterruptedException -> 0x0174, ExecutionException -> 0x0164 }
            goto L_0x0183
        L_0x0164:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = r11.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            r0.log(r2, r14, r1)
            goto L_0x0183
        L_0x0174:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = r11.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            r0.log(r2, r14, r1)
        L_0x0183:
            r0 = 0
        L_0x0184:
            if (r0 >= r15) goto L_0x01aa
            int r1 = r0 * r17
            r2 = 1
            int r3 = r1 + 1
            int r6 = r15 + -1
            if (r0 != r6) goto L_0x0192
            int r1 = r9.rows
            goto L_0x0194
        L_0x0192:
            int r1 = r3 + r17
        L_0x0194:
            r4 = r1
            org.jtransforms.fft.DoubleFFT_2D$20 r8 = new org.jtransforms.fft.DoubleFFT_2D$20
            r1 = r8
            r2 = r21
            r5 = r12
            r6 = r13
            r7 = r22
            r1.<init>(r3, r4, r5, r6, r7)
            java.util.concurrent.Future r1 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r8)
            r18[r0] = r1
            int r0 = r0 + 1
            goto L_0x0184
        L_0x01aa:
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r18)     // Catch:{ InterruptedException -> 0x01c0, ExecutionException -> 0x01af }
            goto L_0x02c6
        L_0x01af:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = r11.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            r0.log(r2, r14, r1)
            goto L_0x02c6
        L_0x01c0:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = r11.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            r0.log(r2, r14, r1)
            goto L_0x02c6
        L_0x01d1:
            r0 = 0
        L_0x01d2:
            int r1 = r9.rows
            if (r0 >= r1) goto L_0x01e1
            org.jtransforms.fft.DoubleFFT_1D r1 = r9.fftColumns
            int r2 = r9.columns
            int r2 = r2 * r0
            r1.realForward((double[]) r10, (int) r2)
            int r0 = r0 + 1
            goto L_0x01d2
        L_0x01e1:
            r0 = 0
        L_0x01e2:
            int r1 = r9.rows
            if (r0 >= r1) goto L_0x01f3
            r1 = 0
            r2 = r16[r1]
            int r3 = r9.columns
            int r3 = r3 * r0
            r3 = r10[r3]
            r2[r0] = r3
            int r0 = r0 + 1
            goto L_0x01e2
        L_0x01f3:
            r1 = 0
            org.jtransforms.fft.DoubleFFT_1D r0 = r9.fftRows
            r2 = r16[r1]
            r0.realForwardFull((double[]) r2)
            r0 = 1
        L_0x01fc:
            if (r0 >= r8) goto L_0x0225
            int r2 = r0 * 2
            r3 = r1
        L_0x0201:
            int r4 = r9.rows
            if (r3 >= r4) goto L_0x021b
            int r4 = r3 * 2
            int r5 = r9.columns
            int r5 = r5 * r3
            int r5 = r5 + r2
            r6 = r16[r0]
            r14 = r10[r5]
            r6[r4] = r14
            r7 = 1
            int r4 = r4 + r7
            int r5 = r5 + r7
            r14 = r10[r5]
            r6[r4] = r14
            int r3 = r3 + 1
            goto L_0x0201
        L_0x021b:
            org.jtransforms.fft.DoubleFFT_1D r2 = r9.fftRows
            r3 = r16[r0]
            r2.complexForward((double[]) r3)
            int r0 = r0 + 1
            goto L_0x01fc
        L_0x0225:
            int r0 = r9.columns
            r2 = 2
            int r0 = r0 % r2
            if (r0 != 0) goto L_0x0246
            r0 = r1
        L_0x022c:
            int r2 = r9.rows
            if (r0 >= r2) goto L_0x023e
            r2 = r16[r8]
            int r3 = r9.columns
            int r3 = r3 * r0
            r4 = 1
            int r3 = r3 + r4
            r3 = r10[r3]
            r2[r0] = r3
            int r0 = r0 + 1
            goto L_0x022c
        L_0x023e:
            org.jtransforms.fft.DoubleFFT_1D r0 = r9.fftRows
            r2 = r16[r8]
            r0.realForwardFull((double[]) r2)
            goto L_0x026a
        L_0x0246:
            r0 = r1
        L_0x0247:
            int r2 = r9.rows
            if (r0 >= r2) goto L_0x0263
            int r2 = r0 * 2
            int r3 = r9.columns
            int r3 = r3 * r0
            r4 = r16[r8]
            int r5 = r8 * 2
            int r5 = r5 + r3
            r5 = r10[r5]
            r4[r2] = r5
            r5 = 1
            int r2 = r2 + r5
            int r3 = r3 + r5
            r5 = r10[r3]
            r4[r2] = r5
            int r0 = r0 + 1
            goto L_0x0247
        L_0x0263:
            org.jtransforms.fft.DoubleFFT_1D r0 = r9.fftRows
            r2 = r16[r8]
            r0.complexForward((double[]) r2)
        L_0x026a:
            r0 = r1
        L_0x026b:
            int r2 = r9.rows
            if (r0 >= r2) goto L_0x028d
            int r2 = r0 * 2
            r3 = r1
        L_0x0272:
            if (r3 >= r13) goto L_0x028a
            int r4 = r3 * 2
            int r5 = r0 * r12
            int r5 = r5 + r4
            r4 = r16[r3]
            r6 = r4[r2]
            r10[r5] = r6
            r6 = 1
            int r5 = r5 + r6
            int r14 = r2 + 1
            r6 = r4[r14]
            r10[r5] = r6
            int r3 = r3 + 1
            goto L_0x0272
        L_0x028a:
            int r0 = r0 + 1
            goto L_0x026b
        L_0x028d:
            r2 = 1
        L_0x028e:
            int r0 = r9.rows
            if (r2 >= r0) goto L_0x02c6
            int r1 = r2 * r12
            int r0 = r0 - r2
            r3 = 1
            int r0 = r0 + r3
            int r0 = r0 * r12
            r3 = r13
        L_0x0299:
            int r4 = r9.columns
            if (r3 >= r4) goto L_0x02c1
            int r5 = r3 * 2
            int r4 = r4 - r3
            r6 = 2
            int r4 = r4 * r6
            r7 = r10[r4]
            r10[r5] = r7
            int r7 = r5 + 1
            r8 = 1
            int r4 = r4 + r8
            r14 = r10[r4]
            double r14 = -r14
            r10[r7] = r14
            int r4 = r1 + r5
            int r5 = r0 - r5
            r14 = r10[r5]
            r10[r4] = r14
            int r4 = r4 + r8
            int r5 = r5 + r8
            r14 = r10[r5]
            double r14 = -r14
            r10[r4] = r14
            int r3 = r3 + 1
            goto L_0x0299
        L_0x02c1:
            r6 = 2
            r8 = 1
            int r2 = r2 + 1
            goto L_0x028e
        L_0x02c6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.DoubleFFT_2D.mixedRadixRealForwardFull(double[]):void");
    }

    private void mixedRadixRealForwardFull(DoubleLargeArray doubleLargeArray) {
        DoubleFFT_2D doubleFFT_2D = this;
        DoubleLargeArray doubleLargeArray2 = doubleLargeArray;
        Class<DoubleFFT_2D> cls = DoubleFFT_2D.class;
        long j = doubleFFT_2D.columnsl;
        long j2 = j * 2;
        long j3 = j / 2;
        long j4 = j3 + 1;
        DoubleLargeArray doubleLargeArray3 = new DoubleLargeArray(j4 * 2 * doubleFFT_2D.rowsl);
        long j5 = doubleFFT_2D.rowsl * 2;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        long j6 = 0;
        if (numberOfThreads > 1 && doubleFFT_2D.useThreads) {
            long j7 = doubleFFT_2D.rowsl;
            long j8 = (long) numberOfThreads;
            if (j7 >= j8) {
                long j9 = j3 - 1;
                if (j9 >= j8) {
                    Future[] futureArr = new Future[numberOfThreads];
                    long j10 = j7 / j8;
                    int i = 0;
                    while (i < numberOfThreads) {
                        final long j11 = ((long) i) * j10;
                        final long j12 = i == numberOfThreads + -1 ? doubleFFT_2D.rowsl : j11 + j10;
                        Future[] futureArr2 = futureArr;
                        final DoubleLargeArray doubleLargeArray4 = doubleLargeArray;
                        futureArr2[i] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                for (long j = j11; j < j12; j++) {
                                    DoubleFFT_2D.this.fftColumns.realForward(doubleLargeArray4, DoubleFFT_2D.this.columnsl * j);
                                }
                            }
                        });
                        i++;
                        futureArr = futureArr2;
                    }
                    Future[] futureArr3 = futureArr;
                    String str = null;
                    try {
                        ConcurrencyUtils.waitForCompletion(futureArr3);
                    } catch (InterruptedException e) {
                        Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
                    } catch (ExecutionException e2) {
                        Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
                    }
                    for (long j13 = 0; j13 < doubleFFT_2D.rowsl; j13++) {
                        doubleLargeArray3.setDouble(j13, doubleLargeArray2.getDouble(doubleFFT_2D.columnsl * j13));
                    }
                    doubleFFT_2D.fftRows.realForwardFull(doubleLargeArray3);
                    long j14 = j9 / j8;
                    int i2 = 0;
                    while (i2 < numberOfThreads) {
                        final long j15 = (((long) i2) * j14) + 1;
                        final long j16 = i2 == numberOfThreads + -1 ? j3 : j15 + j14;
                        String str2 = str;
                        final DoubleLargeArray doubleLargeArray5 = doubleLargeArray3;
                        long j17 = j8;
                        final long j18 = j5;
                        int i3 = numberOfThreads;
                        final DoubleLargeArray doubleLargeArray6 = doubleLargeArray;
                        futureArr3[i2] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                for (long j = j15; j < j16; j++) {
                                    long j2 = 2;
                                    long j3 = j * 2;
                                    long j4 = 0;
                                    while (j4 < DoubleFFT_2D.this.rowsl) {
                                        long j5 = j4 * j2;
                                        long access$500 = (DoubleFFT_2D.this.columnsl * j4) + j3;
                                        doubleLargeArray5.setDouble((j18 * j) + j5, doubleLargeArray6.getDouble(access$500));
                                        doubleLargeArray5.setDouble((j18 * j) + j5 + 1, doubleLargeArray6.getDouble(access$500 + 1));
                                        j4++;
                                        j2 = 2;
                                    }
                                    DoubleFFT_2D.this.fftRows.complexForward(doubleLargeArray5, j18 * j);
                                }
                            }
                        });
                        i2++;
                        str = str2;
                        numberOfThreads = i3;
                        j8 = j17;
                    }
                    String str3 = str;
                    long j19 = j8;
                    int i4 = numberOfThreads;
                    try {
                        ConcurrencyUtils.waitForCompletion(futureArr3);
                    } catch (InterruptedException e3) {
                        Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e3);
                    } catch (ExecutionException e4) {
                        Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e4);
                    }
                    if (doubleFFT_2D.columnsl % 2 == 0) {
                        while (j6 < doubleFFT_2D.rowsl) {
                            doubleLargeArray3.setDouble((j3 * j5) + j6, doubleLargeArray2.getDouble((doubleFFT_2D.columnsl * j6) + 1));
                            j6++;
                        }
                        doubleFFT_2D.fftRows.realForwardFull(doubleLargeArray3, j3 * j5);
                    } else {
                        while (j6 < doubleFFT_2D.rowsl) {
                            long j20 = doubleFFT_2D.columnsl * j6;
                            long j21 = (j3 * j5) + (j6 * 2);
                            doubleLargeArray3.setDouble(j21, doubleLargeArray2.getDouble((j3 * 2) + j20));
                            doubleLargeArray3.setDouble(j21 + 1, doubleLargeArray2.getDouble(j20 + 1));
                            j6++;
                        }
                        doubleFFT_2D.fftRows.complexForward(doubleLargeArray3, j3 * j5);
                    }
                    long j22 = doubleFFT_2D.rowsl / j19;
                    int i5 = 0;
                    while (i5 < i4) {
                        final long j23 = ((long) i5) * j22;
                        final long j24 = i5 == i4 + -1 ? doubleFFT_2D.rowsl : j23 + j22;
                        final long j25 = j4;
                        final long j26 = j2;
                        String str4 = str3;
                        final DoubleLargeArray doubleLargeArray7 = doubleLargeArray;
                        int i6 = i4;
                        final DoubleLargeArray doubleLargeArray8 = doubleLargeArray3;
                        final long j27 = j5;
                        futureArr3[i5] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                for (long j = j23; j < j24; j++) {
                                    long j2 = 2;
                                    long j3 = j * 2;
                                    long j4 = 0;
                                    while (j4 < j25) {
                                        long j5 = (j26 * j) + (j4 * j2);
                                        doubleLargeArray7.setDouble(j5, doubleLargeArray8.getDouble((j27 * j4) + j3));
                                        doubleLargeArray7.setDouble(j5 + 1, doubleLargeArray8.getDouble((j27 * j4) + j3 + 1));
                                        j4++;
                                        j2 = 2;
                                    }
                                }
                            }
                        });
                        i5++;
                        DoubleLargeArray doubleLargeArray9 = doubleLargeArray;
                        str3 = str4;
                        i4 = i6;
                        doubleLargeArray3 = doubleLargeArray3;
                        doubleFFT_2D = this;
                    }
                    String str5 = str3;
                    int i7 = i4;
                    try {
                        ConcurrencyUtils.waitForCompletion(futureArr3);
                    } catch (InterruptedException e5) {
                        Logger.getLogger(cls.getName()).log(Level.SEVERE, str5, e5);
                    } catch (ExecutionException e6) {
                        Logger.getLogger(cls.getName()).log(Level.SEVERE, str5, e6);
                    }
                    int i8 = 0;
                    int i9 = i7;
                    while (i8 < i9) {
                        final long j28 = (((long) i8) * j22) + 1;
                        String str6 = str5;
                        final long j29 = i8 == i9 + -1 ? this.rowsl : j28 + j22;
                        final long j30 = j2;
                        final long j31 = j4;
                        final DoubleLargeArray doubleLargeArray10 = doubleLargeArray;
                        futureArr3[i8] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                long j = j28;
                                while (j < j29) {
                                    long j2 = j30 * j;
                                    long j3 = 1;
                                    long access$300 = ((DoubleFFT_2D.this.rowsl - j) + 1) * j30;
                                    long j4 = j31;
                                    while (j4 < DoubleFFT_2D.this.columnsl) {
                                        long j5 = j4 * 2;
                                        long access$500 = 2 * (DoubleFFT_2D.this.columnsl - j4);
                                        DoubleLargeArray doubleLargeArray = doubleLargeArray10;
                                        doubleLargeArray.setDouble(j5, doubleLargeArray.getDouble(access$500));
                                        DoubleLargeArray doubleLargeArray2 = doubleLargeArray10;
                                        long j6 = j;
                                        doubleLargeArray2.setDouble(j5 + 1, -doubleLargeArray2.getDouble(access$500 + 1));
                                        long j7 = j2 + j5;
                                        long j8 = access$300 - j5;
                                        DoubleLargeArray doubleLargeArray3 = doubleLargeArray10;
                                        doubleLargeArray3.setDouble(j7, doubleLargeArray3.getDouble(j8));
                                        DoubleLargeArray doubleLargeArray4 = doubleLargeArray10;
                                        doubleLargeArray4.setDouble(j7 + 1, -doubleLargeArray4.getDouble(j8 + 1));
                                        j4++;
                                        j3 = 1;
                                        j = j6;
                                    }
                                    j += j3;
                                }
                            }
                        });
                        i8++;
                        str5 = str6;
                    }
                    String str7 = str5;
                    try {
                        ConcurrencyUtils.waitForCompletion(futureArr3);
                        return;
                    } catch (InterruptedException e7) {
                        Logger.getLogger(cls.getName()).log(Level.SEVERE, str7, e7);
                        return;
                    } catch (ExecutionException e8) {
                        Logger.getLogger(cls.getName()).log(Level.SEVERE, str7, e8);
                        return;
                    }
                }
            }
        }
        DoubleLargeArray doubleLargeArray11 = doubleLargeArray3;
        DoubleFFT_2D doubleFFT_2D2 = doubleFFT_2D;
        for (long j32 = 0; j32 < doubleFFT_2D2.rowsl; j32++) {
            doubleFFT_2D2.fftColumns.realForward(doubleLargeArray, doubleFFT_2D2.columnsl * j32);
        }
        DoubleLargeArray doubleLargeArray12 = doubleLargeArray;
        for (long j33 = 0; j33 < doubleFFT_2D2.rowsl; j33++) {
            doubleLargeArray11.setDouble(j33, doubleLargeArray12.getDouble(doubleFFT_2D2.columnsl * j33));
        }
        DoubleLargeArray doubleLargeArray13 = doubleLargeArray11;
        doubleFFT_2D2.fftRows.realForwardFull(doubleLargeArray13);
        for (long j34 = 1; j34 < j3; j34++) {
            long j35 = 2;
            long j36 = j34 * 2;
            long j37 = 0;
            while (j37 < doubleFFT_2D2.rowsl) {
                long j38 = j37 * j35;
                long j39 = (doubleFFT_2D2.columnsl * j37) + j36;
                long j40 = (j34 * j5) + j38;
                doubleLargeArray13.setDouble(j40, doubleLargeArray12.getDouble(j39));
                doubleLargeArray13.setDouble(j40 + 1, doubleLargeArray12.getDouble(j39 + 1));
                j37++;
                j35 = 2;
            }
            doubleFFT_2D2.fftRows.complexForward(doubleLargeArray13, j34 * j5);
        }
        if (doubleFFT_2D2.columnsl % 2 == 0) {
            for (long j41 = 0; j41 < doubleFFT_2D2.rowsl; j41++) {
                doubleLargeArray13.setDouble((j3 * j5) + j41, doubleLargeArray12.getDouble((doubleFFT_2D2.columnsl * j41) + 1));
            }
            doubleFFT_2D2.fftRows.realForwardFull(doubleLargeArray13, j3 * j5);
        } else {
            for (long j42 = 0; j42 < doubleFFT_2D2.rowsl; j42++) {
                long j43 = doubleFFT_2D2.columnsl * j42;
                long j44 = (j3 * j5) + (j42 * 2);
                doubleLargeArray13.setDouble(j44, doubleLargeArray12.getDouble((j3 * 2) + j43));
                doubleLargeArray13.setDouble(j44 + 1, doubleLargeArray12.getDouble(j43 + 1));
            }
            doubleFFT_2D2.fftRows.complexForward(doubleLargeArray13, j3 * j5);
        }
        for (long j45 = 0; j45 < doubleFFT_2D2.rowsl; j45++) {
            long j46 = 2;
            long j47 = j45 * 2;
            long j48 = 0;
            while (j48 < j4) {
                long j49 = (j45 * j2) + (j48 * j46);
                long j50 = (j48 * j5) + j47;
                doubleLargeArray12.setDouble(j49, doubleLargeArray13.getDouble(j50));
                doubleLargeArray12.setDouble(j49 + 1, doubleLargeArray13.getDouble(j50 + 1));
                j48++;
                j46 = 2;
            }
        }
        long j51 = 1;
        while (true) {
            long j52 = doubleFFT_2D2.rowsl;
            if (j51 < j52) {
                long j53 = j51 * j2;
                long j54 = ((j52 - j51) + 1) * j2;
                long j55 = j4;
                while (true) {
                    long j56 = doubleFFT_2D2.columnsl;
                    if (j55 >= j56) {
                        break;
                    }
                    long j57 = j55 * 2;
                    long j58 = (j56 - j55) * 2;
                    doubleLargeArray12.setDouble(j57, doubleLargeArray12.getDouble(j58));
                    doubleLargeArray12.setDouble(j57 + 1, -doubleLargeArray12.getDouble(j58 + 1));
                    long j59 = j53 + j57;
                    long j60 = j54 - j57;
                    doubleLargeArray12.setDouble(j59, doubleLargeArray12.getDouble(j60));
                    doubleLargeArray12.setDouble(j59 + 1, -doubleLargeArray12.getDouble(j60 + 1));
                    j55++;
                    doubleFFT_2D2 = this;
                }
                j51++;
                doubleFFT_2D2 = this;
            } else {
                return;
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v0, resolved type: double[][]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void mixedRadixRealInverseFull(double[][] r23, boolean r24) {
        /*
            r22 = this;
            r8 = r22
            r9 = r24
            java.lang.Class<org.jtransforms.fft.DoubleFFT_2D> r10 = org.jtransforms.fft.DoubleFFT_2D.class
            int r0 = r8.columns
            r11 = 2
            int r12 = r0 / 2
            int r13 = r12 + 1
            int r0 = r8.rows
            int r0 = r0 * r11
            int[] r1 = new int[r11]
            r14 = 1
            r1[r14] = r0
            r15 = 0
            r1[r15] = r13
            java.lang.Class r0 = java.lang.Double.TYPE
            java.lang.Object r0 = java.lang.reflect.Array.newInstance(r0, r1)
            r16 = r0
            double[][] r16 = (double[][]) r16
            int r7 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getNumberOfThreads()
            if (r7 <= r14) goto L_0x01d8
            boolean r0 = r8.useThreads
            if (r0 == 0) goto L_0x01d8
            int r0 = r8.rows
            if (r0 < r7) goto L_0x01d8
            int r6 = r12 + -1
            if (r6 < r7) goto L_0x01d8
            java.util.concurrent.Future[] r5 = new java.util.concurrent.Future[r7]
            int r0 = r0 / r7
            r4 = r15
        L_0x0038:
            if (r4 >= r7) goto L_0x0069
            int r3 = r4 * r0
            int r1 = r7 + -1
            if (r4 != r1) goto L_0x0043
            int r1 = r8.rows
            goto L_0x0045
        L_0x0043:
            int r1 = r3 + r0
        L_0x0045:
            r17 = r1
            org.jtransforms.fft.DoubleFFT_2D$25 r18 = new org.jtransforms.fft.DoubleFFT_2D$25
            r1 = r18
            r2 = r22
            r19 = r4
            r4 = r17
            r17 = r5
            r5 = r23
            r20 = r6
            r6 = r24
            r1.<init>(r3, r4, r5, r6)
            java.util.concurrent.Future r1 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r18)
            r17[r19] = r1
            int r4 = r19 + 1
            r5 = r17
            r6 = r20
            goto L_0x0038
        L_0x0069:
            r17 = r5
            r20 = r6
            r6 = 0
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r17)     // Catch:{ InterruptedException -> 0x0082, ExecutionException -> 0x0072 }
            goto L_0x0091
        L_0x0072:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = r10.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            r0.log(r2, r6, r1)
            goto L_0x0091
        L_0x0082:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = r10.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            r0.log(r2, r6, r1)
        L_0x0091:
            r0 = r15
        L_0x0092:
            int r1 = r8.rows
            if (r0 >= r1) goto L_0x00a1
            r1 = r16[r15]
            r2 = r23[r0]
            r3 = r2[r15]
            r1[r0] = r3
            int r0 = r0 + 1
            goto L_0x0092
        L_0x00a1:
            org.jtransforms.fft.DoubleFFT_1D r0 = r8.fftRows
            r1 = r16[r15]
            r0.realInverseFull((double[]) r1, (boolean) r9)
            int r0 = r20 / r7
            r5 = r15
        L_0x00ab:
            if (r5 >= r7) goto L_0x00db
            int r1 = r5 * r0
            int r3 = r1 + 1
            int r1 = r7 + -1
            if (r5 != r1) goto L_0x00b7
            r4 = r12
            goto L_0x00ba
        L_0x00b7:
            int r1 = r3 + r0
            r4 = r1
        L_0x00ba:
            org.jtransforms.fft.DoubleFFT_2D$26 r18 = new org.jtransforms.fft.DoubleFFT_2D$26
            r1 = r18
            r2 = r22
            r19 = r5
            r5 = r16
            r15 = r6
            r6 = r23
            r21 = r7
            r7 = r24
            r1.<init>(r3, r4, r5, r6, r7)
            java.util.concurrent.Future r1 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r18)
            r17[r19] = r1
            int r5 = r19 + 1
            r6 = r15
            r7 = r21
            r15 = 0
            goto L_0x00ab
        L_0x00db:
            r15 = r6
            r21 = r7
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r17)     // Catch:{ InterruptedException -> 0x00f2, ExecutionException -> 0x00e2 }
            goto L_0x0101
        L_0x00e2:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = r10.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            r0.log(r2, r15, r1)
            goto L_0x0101
        L_0x00f2:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = r10.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            r0.log(r2, r15, r1)
        L_0x0101:
            int r0 = r8.columns
            int r0 = r0 % r11
            if (r0 != 0) goto L_0x011e
            r0 = 0
        L_0x0107:
            int r1 = r8.rows
            if (r0 >= r1) goto L_0x0116
            r1 = r16[r12]
            r2 = r23[r0]
            r3 = r2[r14]
            r1[r0] = r3
            int r0 = r0 + 1
            goto L_0x0107
        L_0x0116:
            org.jtransforms.fft.DoubleFFT_1D r0 = r8.fftRows
            r1 = r16[r12]
            r0.realInverseFull((double[]) r1, (boolean) r9)
            goto L_0x013e
        L_0x011e:
            r0 = 0
        L_0x011f:
            int r1 = r8.rows
            if (r0 >= r1) goto L_0x0137
            int r1 = r0 * 2
            r2 = r16[r12]
            r3 = r23[r0]
            int r4 = r12 * 2
            r4 = r3[r4]
            r2[r1] = r4
            int r1 = r1 + r14
            r4 = r3[r14]
            r2[r1] = r4
            int r0 = r0 + 1
            goto L_0x011f
        L_0x0137:
            org.jtransforms.fft.DoubleFFT_1D r0 = r8.fftRows
            r1 = r16[r12]
            r0.complexInverse((double[]) r1, (boolean) r9)
        L_0x013e:
            int r0 = r8.rows
            r9 = r21
            int r11 = r0 / r9
            r0 = 0
        L_0x0145:
            if (r0 >= r9) goto L_0x0169
            int r3 = r0 * r11
            int r7 = r9 + -1
            if (r0 != r7) goto L_0x0150
            int r1 = r8.rows
            goto L_0x0152
        L_0x0150:
            int r1 = r3 + r11
        L_0x0152:
            r4 = r1
            org.jtransforms.fft.DoubleFFT_2D$27 r12 = new org.jtransforms.fft.DoubleFFT_2D$27
            r1 = r12
            r2 = r22
            r5 = r13
            r6 = r23
            r7 = r16
            r1.<init>(r3, r4, r5, r6, r7)
            java.util.concurrent.Future r1 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r12)
            r17[r0] = r1
            int r0 = r0 + 1
            goto L_0x0145
        L_0x0169:
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r17)     // Catch:{ InterruptedException -> 0x017d, ExecutionException -> 0x016d }
            goto L_0x018c
        L_0x016d:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = r10.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            r0.log(r2, r15, r1)
            goto L_0x018c
        L_0x017d:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = r10.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            r0.log(r2, r15, r1)
        L_0x018c:
            r0 = 0
        L_0x018d:
            if (r0 >= r9) goto L_0x01b1
            int r1 = r0 * r11
            int r3 = r1 + 1
            int r7 = r9 + -1
            if (r0 != r7) goto L_0x019a
            int r1 = r8.rows
            goto L_0x019c
        L_0x019a:
            int r1 = r3 + r11
        L_0x019c:
            r4 = r1
            org.jtransforms.fft.DoubleFFT_2D$28 r7 = new org.jtransforms.fft.DoubleFFT_2D$28
            r1 = r7
            r2 = r22
            r5 = r13
            r6 = r23
            r1.<init>(r3, r4, r5, r6)
            java.util.concurrent.Future r1 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r7)
            r17[r0] = r1
            int r0 = r0 + 1
            goto L_0x018d
        L_0x01b1:
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r17)     // Catch:{ InterruptedException -> 0x01c7, ExecutionException -> 0x01b6 }
            goto L_0x02b8
        L_0x01b6:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = r10.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            r0.log(r2, r15, r1)
            goto L_0x02b8
        L_0x01c7:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = r10.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            r0.log(r2, r15, r1)
            goto L_0x02b8
        L_0x01d8:
            r0 = 0
        L_0x01d9:
            int r1 = r8.rows
            if (r0 >= r1) goto L_0x01e8
            org.jtransforms.fft.DoubleFFT_1D r1 = r8.fftColumns
            r2 = r23[r0]
            r3 = 0
            r1.realInverse2((double[]) r2, (int) r3, (boolean) r9)
            int r0 = r0 + 1
            goto L_0x01d9
        L_0x01e8:
            r3 = 0
            r0 = r3
        L_0x01ea:
            int r1 = r8.rows
            if (r0 >= r1) goto L_0x01f9
            r1 = r16[r3]
            r2 = r23[r0]
            r4 = r2[r3]
            r1[r0] = r4
            int r0 = r0 + 1
            goto L_0x01ea
        L_0x01f9:
            org.jtransforms.fft.DoubleFFT_1D r0 = r8.fftRows
            r1 = r16[r3]
            r0.realInverseFull((double[]) r1, (boolean) r9)
            r0 = r14
        L_0x0201:
            if (r0 >= r12) goto L_0x0228
            int r1 = r0 * 2
            r3 = 0
        L_0x0206:
            int r2 = r8.rows
            if (r3 >= r2) goto L_0x021e
            int r2 = r3 * 2
            r4 = r16[r0]
            r5 = r23[r3]
            r6 = r5[r1]
            r4[r2] = r6
            int r2 = r2 + r14
            int r6 = r1 + 1
            r6 = r5[r6]
            r4[r2] = r6
            int r3 = r3 + 1
            goto L_0x0206
        L_0x021e:
            org.jtransforms.fft.DoubleFFT_1D r1 = r8.fftRows
            r2 = r16[r0]
            r1.complexInverse((double[]) r2, (boolean) r9)
            int r0 = r0 + 1
            goto L_0x0201
        L_0x0228:
            int r0 = r8.columns
            int r0 = r0 % r11
            if (r0 != 0) goto L_0x0245
            r3 = 0
        L_0x022e:
            int r0 = r8.rows
            if (r3 >= r0) goto L_0x023d
            r0 = r16[r12]
            r1 = r23[r3]
            r4 = r1[r14]
            r0[r3] = r4
            int r3 = r3 + 1
            goto L_0x022e
        L_0x023d:
            org.jtransforms.fft.DoubleFFT_1D r0 = r8.fftRows
            r1 = r16[r12]
            r0.realInverseFull((double[]) r1, (boolean) r9)
            goto L_0x0265
        L_0x0245:
            r3 = 0
        L_0x0246:
            int r0 = r8.rows
            if (r3 >= r0) goto L_0x025e
            int r0 = r3 * 2
            r1 = r16[r12]
            r2 = r23[r3]
            int r4 = r12 * 2
            r4 = r2[r4]
            r1[r0] = r4
            int r0 = r0 + r14
            r4 = r2[r14]
            r1[r0] = r4
            int r3 = r3 + 1
            goto L_0x0246
        L_0x025e:
            org.jtransforms.fft.DoubleFFT_1D r0 = r8.fftRows
            r1 = r16[r12]
            r0.complexInverse((double[]) r1, (boolean) r9)
        L_0x0265:
            r3 = 0
        L_0x0266:
            int r0 = r8.rows
            if (r3 >= r0) goto L_0x0286
            int r0 = r3 * 2
            r1 = 0
        L_0x026d:
            if (r1 >= r13) goto L_0x0283
            int r2 = r1 * 2
            r4 = r23[r3]
            r5 = r16[r1]
            r6 = r5[r0]
            r4[r2] = r6
            int r2 = r2 + r14
            int r6 = r0 + 1
            r6 = r5[r6]
            r4[r2] = r6
            int r1 = r1 + 1
            goto L_0x026d
        L_0x0283:
            int r3 = r3 + 1
            goto L_0x0266
        L_0x0286:
            int r0 = r8.rows
            if (r14 >= r0) goto L_0x02b8
            int r0 = r0 - r14
            r1 = r13
        L_0x028c:
            int r2 = r8.columns
            if (r1 >= r2) goto L_0x02b4
            int r3 = r1 * 2
            int r2 = r2 - r1
            int r2 = r2 * r11
            r4 = 0
            r5 = r23[r4]
            r6 = r5[r2]
            r5[r3] = r6
            int r6 = r3 + 1
            int r7 = r2 + 1
            r9 = r5[r7]
            double r9 = -r9
            r5[r6] = r9
            r5 = r23[r14]
            r9 = r23[r0]
            r15 = r9[r2]
            r5[r3] = r15
            r2 = r9[r7]
            double r2 = -r2
            r5[r6] = r2
            int r1 = r1 + 1
            goto L_0x028c
        L_0x02b4:
            r4 = 0
            int r14 = r14 + 1
            goto L_0x0286
        L_0x02b8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.DoubleFFT_2D.mixedRadixRealInverseFull(double[][], boolean):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v0, resolved type: double[][]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void mixedRadixRealInverseFull(double[] r24, boolean r25) {
        /*
            r23 = this;
            r9 = r23
            r10 = r24
            r8 = r25
            java.lang.Class<org.jtransforms.fft.DoubleFFT_2D> r11 = org.jtransforms.fft.DoubleFFT_2D.class
            int r0 = r9.columns
            int r12 = r0 * 2
            r13 = 2
            int r14 = r0 / 2
            int r15 = r14 + 1
            int r0 = r9.rows
            int r0 = r0 * r13
            int[] r1 = new int[r13]
            r7 = 1
            r1[r7] = r0
            r16 = 0
            r1[r16] = r15
            java.lang.Class r0 = java.lang.Double.TYPE
            java.lang.Object r0 = java.lang.reflect.Array.newInstance(r0, r1)
            r17 = r0
            double[][] r17 = (double[][]) r17
            int r6 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getNumberOfThreads()
            if (r6 <= r7) goto L_0x01fc
            boolean r0 = r9.useThreads
            if (r0 == 0) goto L_0x01fc
            int r0 = r9.rows
            if (r0 < r6) goto L_0x01fc
            int r5 = r14 + -1
            if (r5 < r6) goto L_0x01fc
            java.util.concurrent.Future[] r4 = new java.util.concurrent.Future[r6]
            int r0 = r0 / r6
            r3 = r16
        L_0x003e:
            if (r3 >= r6) goto L_0x0074
            int r18 = r3 * r0
            int r1 = r6 + -1
            if (r3 != r1) goto L_0x0049
            int r1 = r9.rows
            goto L_0x004b
        L_0x0049:
            int r1 = r18 + r0
        L_0x004b:
            r19 = r1
            org.jtransforms.fft.DoubleFFT_2D$29 r20 = new org.jtransforms.fft.DoubleFFT_2D$29
            r1 = r20
            r2 = r23
            r21 = r3
            r3 = r18
            r18 = r4
            r4 = r19
            r19 = r5
            r5 = r24
            r13 = r6
            r6 = r25
            r1.<init>(r3, r4, r5, r6)
            java.util.concurrent.Future r1 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r20)
            r18[r21] = r1
            int r3 = r21 + 1
            r6 = r13
            r4 = r18
            r5 = r19
            r13 = 2
            goto L_0x003e
        L_0x0074:
            r18 = r4
            r19 = r5
            r13 = r6
            r6 = 0
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r18)     // Catch:{ InterruptedException -> 0x008e, ExecutionException -> 0x007e }
            goto L_0x009d
        L_0x007e:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = r11.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            r0.log(r2, r6, r1)
            goto L_0x009d
        L_0x008e:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = r11.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            r0.log(r2, r6, r1)
        L_0x009d:
            r0 = r16
        L_0x009f:
            int r1 = r9.rows
            if (r0 >= r1) goto L_0x00af
            r1 = r17[r16]
            int r2 = r9.columns
            int r2 = r2 * r0
            r2 = r10[r2]
            r1[r0] = r2
            int r0 = r0 + 1
            goto L_0x009f
        L_0x00af:
            org.jtransforms.fft.DoubleFFT_1D r0 = r9.fftRows
            r1 = r17[r16]
            r0.realInverseFull((double[]) r1, (boolean) r8)
            int r0 = r19 / r13
            r5 = r16
        L_0x00ba:
            if (r5 >= r13) goto L_0x00ed
            int r1 = r5 * r0
            int r3 = r1 + 1
            int r1 = r13 + -1
            if (r5 != r1) goto L_0x00c6
            r4 = r14
            goto L_0x00c9
        L_0x00c6:
            int r1 = r3 + r0
            r4 = r1
        L_0x00c9:
            org.jtransforms.fft.DoubleFFT_2D$30 r19 = new org.jtransforms.fft.DoubleFFT_2D$30
            r1 = r19
            r2 = r23
            r20 = r5
            r5 = r17
            r21 = r12
            r12 = r6
            r6 = r24
            r22 = r7
            r7 = r25
            r1.<init>(r3, r4, r5, r6, r7)
            java.util.concurrent.Future r1 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r19)
            r18[r20] = r1
            int r5 = r20 + 1
            r6 = r12
            r12 = r21
            r7 = r22
            goto L_0x00ba
        L_0x00ed:
            r22 = r7
            r21 = r12
            r12 = r6
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r18)     // Catch:{ InterruptedException -> 0x0106, ExecutionException -> 0x00f6 }
            goto L_0x0115
        L_0x00f6:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = r11.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            r0.log(r2, r12, r1)
            goto L_0x0115
        L_0x0106:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = r11.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            r0.log(r2, r12, r1)
        L_0x0115:
            int r0 = r9.columns
            r1 = 2
            int r0 = r0 % r1
            if (r0 != 0) goto L_0x0137
            r0 = r16
        L_0x011d:
            int r1 = r9.rows
            if (r0 >= r1) goto L_0x012f
            r1 = r17[r14]
            int r2 = r9.columns
            int r2 = r2 * r0
            int r2 = r2 + 1
            r2 = r10[r2]
            r1[r0] = r2
            int r0 = r0 + 1
            goto L_0x011d
        L_0x012f:
            org.jtransforms.fft.DoubleFFT_1D r0 = r9.fftRows
            r1 = r17[r14]
            r0.realInverseFull((double[]) r1, (boolean) r8)
            goto L_0x015d
        L_0x0137:
            r0 = r16
        L_0x0139:
            int r1 = r9.rows
            if (r0 >= r1) goto L_0x0156
            int r1 = r0 * 2
            int r2 = r9.columns
            int r2 = r2 * r0
            r3 = r17[r14]
            int r4 = r14 * 2
            int r4 = r4 + r2
            r4 = r10[r4]
            r3[r1] = r4
            int r1 = r1 + 1
            int r2 = r2 + 1
            r4 = r10[r2]
            r3[r1] = r4
            int r0 = r0 + 1
            goto L_0x0139
        L_0x0156:
            org.jtransforms.fft.DoubleFFT_1D r0 = r9.fftRows
            r1 = r17[r14]
            r0.complexInverse((double[]) r1, (boolean) r8)
        L_0x015d:
            int r0 = r9.rows
            int r14 = r0 / r13
            r0 = r16
        L_0x0163:
            if (r0 >= r13) goto L_0x018a
            int r3 = r0 * r14
            int r6 = r13 + -1
            if (r0 != r6) goto L_0x016e
            int r1 = r9.rows
            goto L_0x0170
        L_0x016e:
            int r1 = r3 + r14
        L_0x0170:
            r4 = r1
            org.jtransforms.fft.DoubleFFT_2D$31 r19 = new org.jtransforms.fft.DoubleFFT_2D$31
            r1 = r19
            r2 = r23
            r5 = r15
            r6 = r21
            r7 = r24
            r8 = r17
            r1.<init>(r3, r4, r5, r6, r7, r8)
            java.util.concurrent.Future r1 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r19)
            r18[r0] = r1
            int r0 = r0 + 1
            goto L_0x0163
        L_0x018a:
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r18)     // Catch:{ InterruptedException -> 0x019e, ExecutionException -> 0x018e }
            goto L_0x01ad
        L_0x018e:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = r11.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            r0.log(r2, r12, r1)
            goto L_0x01ad
        L_0x019e:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = r11.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            r0.log(r2, r12, r1)
        L_0x01ad:
            r0 = r16
        L_0x01af:
            if (r0 >= r13) goto L_0x01d5
            int r1 = r0 * r14
            int r3 = r1 + 1
            int r6 = r13 + -1
            if (r0 != r6) goto L_0x01bc
            int r1 = r9.rows
            goto L_0x01be
        L_0x01bc:
            int r1 = r3 + r14
        L_0x01be:
            r4 = r1
            org.jtransforms.fft.DoubleFFT_2D$32 r8 = new org.jtransforms.fft.DoubleFFT_2D$32
            r1 = r8
            r2 = r23
            r5 = r21
            r6 = r15
            r7 = r24
            r1.<init>(r3, r4, r5, r6, r7)
            java.util.concurrent.Future r1 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r8)
            r18[r0] = r1
            int r0 = r0 + 1
            goto L_0x01af
        L_0x01d5:
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r18)     // Catch:{ InterruptedException -> 0x01eb, ExecutionException -> 0x01da }
            goto L_0x0300
        L_0x01da:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = r11.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            r0.log(r2, r12, r1)
            goto L_0x0300
        L_0x01eb:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = r11.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            r0.log(r2, r12, r1)
            goto L_0x0300
        L_0x01fc:
            r22 = r7
            r21 = r12
            r0 = r16
        L_0x0202:
            int r1 = r9.rows
            if (r0 >= r1) goto L_0x0211
            org.jtransforms.fft.DoubleFFT_1D r1 = r9.fftColumns
            int r2 = r9.columns
            int r2 = r2 * r0
            r1.realInverse2((double[]) r10, (int) r2, (boolean) r8)
            int r0 = r0 + 1
            goto L_0x0202
        L_0x0211:
            r0 = r16
        L_0x0213:
            int r1 = r9.rows
            if (r0 >= r1) goto L_0x0223
            r1 = r17[r16]
            int r2 = r9.columns
            int r2 = r2 * r0
            r2 = r10[r2]
            r1[r0] = r2
            int r0 = r0 + 1
            goto L_0x0213
        L_0x0223:
            org.jtransforms.fft.DoubleFFT_1D r0 = r9.fftRows
            r1 = r17[r16]
            r0.realInverseFull((double[]) r1, (boolean) r8)
            r7 = r22
        L_0x022c:
            if (r7 >= r14) goto L_0x0257
            int r0 = r7 * 2
            r1 = r16
        L_0x0232:
            int r2 = r9.rows
            if (r1 >= r2) goto L_0x024d
            int r2 = r1 * 2
            int r3 = r9.columns
            int r3 = r3 * r1
            int r3 = r3 + r0
            r4 = r17[r7]
            r5 = r10[r3]
            r4[r2] = r5
            int r2 = r2 + 1
            int r3 = r3 + 1
            r5 = r10[r3]
            r4[r2] = r5
            int r1 = r1 + 1
            goto L_0x0232
        L_0x024d:
            org.jtransforms.fft.DoubleFFT_1D r0 = r9.fftRows
            r1 = r17[r7]
            r0.complexInverse((double[]) r1, (boolean) r8)
            int r7 = r7 + 1
            goto L_0x022c
        L_0x0257:
            int r0 = r9.columns
            r1 = 2
            int r0 = r0 % r1
            if (r0 != 0) goto L_0x0279
            r0 = r16
        L_0x025f:
            int r1 = r9.rows
            if (r0 >= r1) goto L_0x0271
            r1 = r17[r14]
            int r2 = r9.columns
            int r2 = r2 * r0
            int r2 = r2 + 1
            r2 = r10[r2]
            r1[r0] = r2
            int r0 = r0 + 1
            goto L_0x025f
        L_0x0271:
            org.jtransforms.fft.DoubleFFT_1D r0 = r9.fftRows
            r1 = r17[r14]
            r0.realInverseFull((double[]) r1, (boolean) r8)
            goto L_0x029f
        L_0x0279:
            r0 = r16
        L_0x027b:
            int r1 = r9.rows
            if (r0 >= r1) goto L_0x0298
            int r1 = r0 * 2
            int r2 = r9.columns
            int r2 = r2 * r0
            r3 = r17[r14]
            int r4 = r14 * 2
            int r4 = r4 + r2
            r4 = r10[r4]
            r3[r1] = r4
            int r1 = r1 + 1
            int r2 = r2 + 1
            r4 = r10[r2]
            r3[r1] = r4
            int r0 = r0 + 1
            goto L_0x027b
        L_0x0298:
            org.jtransforms.fft.DoubleFFT_1D r0 = r9.fftRows
            r1 = r17[r14]
            r0.complexInverse((double[]) r1, (boolean) r8)
        L_0x029f:
            r0 = r16
        L_0x02a1:
            int r1 = r9.rows
            if (r0 >= r1) goto L_0x02c4
            int r1 = r0 * 2
            r2 = r16
        L_0x02a9:
            if (r2 >= r15) goto L_0x02c1
            int r3 = r2 * 2
            int r12 = r0 * r21
            int r12 = r12 + r3
            r3 = r17[r2]
            r4 = r3[r1]
            r10[r12] = r4
            int r12 = r12 + 1
            int r7 = r1 + 1
            r4 = r3[r7]
            r10[r12] = r4
            int r2 = r2 + 1
            goto L_0x02a9
        L_0x02c1:
            int r0 = r0 + 1
            goto L_0x02a1
        L_0x02c4:
            r7 = r22
        L_0x02c6:
            int r0 = r9.rows
            if (r7 >= r0) goto L_0x0300
            int r12 = r7 * r21
            int r0 = r0 - r7
            int r0 = r0 + 1
            int r0 = r0 * r21
            r1 = r15
        L_0x02d2:
            int r2 = r9.columns
            if (r1 >= r2) goto L_0x02fc
            int r3 = r1 * 2
            int r2 = r2 - r1
            r4 = 2
            int r2 = r2 * r4
            r5 = r10[r2]
            r10[r3] = r5
            int r5 = r3 + 1
            int r2 = r2 + 1
            r13 = r10[r2]
            double r13 = -r13
            r10[r5] = r13
            int r2 = r12 + r3
            int r3 = r0 - r3
            r5 = r10[r3]
            r10[r2] = r5
            int r2 = r2 + 1
            int r3 = r3 + 1
            r5 = r10[r3]
            double r5 = -r5
            r10[r2] = r5
            int r1 = r1 + 1
            goto L_0x02d2
        L_0x02fc:
            r4 = 2
            int r7 = r7 + 1
            goto L_0x02c6
        L_0x0300:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.DoubleFFT_2D.mixedRadixRealInverseFull(double[], boolean):void");
    }

    private void mixedRadixRealInverseFull(DoubleLargeArray doubleLargeArray, boolean z) {
        DoubleFFT_2D doubleFFT_2D = this;
        DoubleLargeArray doubleLargeArray2 = doubleLargeArray;
        boolean z2 = z;
        Class<DoubleFFT_2D> cls = DoubleFFT_2D.class;
        long j = doubleFFT_2D.columnsl;
        long j2 = j * 2;
        long j3 = j / 2;
        long j4 = j3 + 1;
        DoubleLargeArray doubleLargeArray3 = new DoubleLargeArray(j4 * 2 * doubleFFT_2D.rowsl);
        long j5 = doubleFFT_2D.rowsl * 2;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        long j6 = 0;
        if (numberOfThreads > 1 && doubleFFT_2D.useThreads) {
            long j7 = doubleFFT_2D.rowsl;
            long j8 = (long) numberOfThreads;
            if (j7 >= j8) {
                long j9 = j3 - 1;
                if (j9 >= j8) {
                    Future[] futureArr = new Future[numberOfThreads];
                    long j10 = j7 / j8;
                    int i = 0;
                    while (i < numberOfThreads) {
                        final long j11 = ((long) i) * j10;
                        final long j12 = i == numberOfThreads + -1 ? doubleFFT_2D.rowsl : j11 + j10;
                        final DoubleLargeArray doubleLargeArray4 = doubleLargeArray;
                        Future[] futureArr2 = futureArr;
                        final boolean z3 = z;
                        futureArr2[i] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                for (long j = j11; j < j12; j++) {
                                    DoubleFFT_2D.this.fftColumns.realInverse2(doubleLargeArray4, DoubleFFT_2D.this.columnsl * j, z3);
                                }
                            }
                        });
                        i++;
                        futureArr = futureArr2;
                    }
                    Future[] futureArr3 = futureArr;
                    String str = null;
                    try {
                        ConcurrencyUtils.waitForCompletion(futureArr3);
                    } catch (InterruptedException e) {
                        Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
                    } catch (ExecutionException e2) {
                        Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
                    }
                    for (long j13 = 0; j13 < doubleFFT_2D.rowsl; j13++) {
                        doubleLargeArray3.setDouble(j13, doubleLargeArray2.getDouble(doubleFFT_2D.columnsl * j13));
                    }
                    doubleFFT_2D.fftRows.realInverseFull(doubleLargeArray3, z2);
                    long j14 = j9 / j8;
                    int i2 = 0;
                    while (i2 < numberOfThreads) {
                        final long j15 = (((long) i2) * j14) + 1;
                        final long j16 = i2 == numberOfThreads + -1 ? j3 : j15 + j14;
                        final DoubleLargeArray doubleLargeArray5 = doubleLargeArray3;
                        String str2 = str;
                        final long j17 = j5;
                        String str3 = str2;
                        final DoubleLargeArray doubleLargeArray6 = doubleLargeArray;
                        int i3 = numberOfThreads;
                        final boolean z4 = z;
                        futureArr3[i2] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                for (long j = j15; j < j16; j++) {
                                    long j2 = 2;
                                    long j3 = j * 2;
                                    long j4 = 0;
                                    while (j4 < DoubleFFT_2D.this.rowsl) {
                                        long j5 = j4 * j2;
                                        long access$500 = (DoubleFFT_2D.this.columnsl * j4) + j3;
                                        doubleLargeArray5.setDouble((j17 * j) + j5, doubleLargeArray6.getDouble(access$500));
                                        doubleLargeArray5.setDouble((j17 * j) + j5 + 1, doubleLargeArray6.getDouble(access$500 + 1));
                                        j4++;
                                        j2 = 2;
                                    }
                                    DoubleFFT_2D.this.fftRows.complexInverse(doubleLargeArray5, j17 * j, z4);
                                }
                            }
                        });
                        i2++;
                        str = str3;
                        j8 = j8;
                        numberOfThreads = i3;
                        boolean z5 = z;
                    }
                    String str4 = str;
                    long j18 = j8;
                    int i4 = numberOfThreads;
                    try {
                        ConcurrencyUtils.waitForCompletion(futureArr3);
                    } catch (InterruptedException e3) {
                        Logger.getLogger(cls.getName()).log(Level.SEVERE, str4, e3);
                    } catch (ExecutionException e4) {
                        Logger.getLogger(cls.getName()).log(Level.SEVERE, str4, e4);
                    }
                    if (doubleFFT_2D.columnsl % 2 == 0) {
                        while (j6 < doubleFFT_2D.rowsl) {
                            doubleLargeArray3.setDouble((j3 * j5) + j6, doubleLargeArray2.getDouble((doubleFFT_2D.columnsl * j6) + 1));
                            j6++;
                        }
                        doubleFFT_2D.fftRows.realInverseFull(doubleLargeArray3, j3 * j5, z);
                    } else {
                        boolean z6 = z;
                        while (j6 < doubleFFT_2D.rowsl) {
                            long j19 = doubleFFT_2D.columnsl * j6;
                            long j20 = (j3 * j5) + (j6 * 2);
                            doubleLargeArray3.setDouble(j20, doubleLargeArray2.getDouble((j3 * 2) + j19));
                            doubleLargeArray3.setDouble(j20 + 1, doubleLargeArray2.getDouble(j19 + 1));
                            j6++;
                        }
                        doubleFFT_2D.fftRows.complexInverse(doubleLargeArray3, j3 * j5, z6);
                    }
                    long j21 = doubleFFT_2D.rowsl / j18;
                    int i5 = 0;
                    int i6 = i4;
                    while (i5 < i6) {
                        final long j22 = ((long) i5) * j21;
                        final long j23 = i5 == i6 + -1 ? doubleFFT_2D.rowsl : j22 + j21;
                        final long j24 = j4;
                        final long j25 = j2;
                        int i7 = i6;
                        final DoubleLargeArray doubleLargeArray7 = doubleLargeArray;
                        int i8 = i7;
                        String str5 = str4;
                        final DoubleLargeArray doubleLargeArray8 = doubleLargeArray3;
                        final long j26 = j5;
                        futureArr3[i5] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                for (long j = j22; j < j23; j++) {
                                    long j2 = 2;
                                    long j3 = j * 2;
                                    long j4 = 0;
                                    while (j4 < j24) {
                                        long j5 = (j25 * j) + (j4 * j2);
                                        doubleLargeArray7.setDouble(j5, doubleLargeArray8.getDouble((j26 * j4) + j3));
                                        doubleLargeArray7.setDouble(j5 + 1, doubleLargeArray8.getDouble((j26 * j4) + j3 + 1));
                                        j4++;
                                        j2 = 2;
                                    }
                                }
                            }
                        });
                        i5++;
                        DoubleLargeArray doubleLargeArray9 = doubleLargeArray;
                        str4 = str5;
                        i6 = i8;
                        doubleLargeArray3 = doubleLargeArray3;
                        doubleFFT_2D = this;
                    }
                    int i9 = i6;
                    String str6 = str4;
                    try {
                        ConcurrencyUtils.waitForCompletion(futureArr3);
                    } catch (InterruptedException e5) {
                        Logger.getLogger(cls.getName()).log(Level.SEVERE, str6, e5);
                    } catch (ExecutionException e6) {
                        Logger.getLogger(cls.getName()).log(Level.SEVERE, str6, e6);
                    }
                    int i10 = 0;
                    int i11 = i9;
                    while (i10 < i11) {
                        final long j27 = (((long) i10) * j21) + 1;
                        int i12 = i11;
                        final long j28 = i10 == i11 + -1 ? this.rowsl : j27 + j21;
                        final long j29 = j2;
                        final long j30 = j4;
                        final DoubleLargeArray doubleLargeArray10 = doubleLargeArray;
                        futureArr3[i10] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                long j = j27;
                                while (j < j28) {
                                    long j2 = j29 * j;
                                    long j3 = 1;
                                    long access$300 = ((DoubleFFT_2D.this.rowsl - j) + 1) * j29;
                                    long j4 = j30;
                                    while (j4 < DoubleFFT_2D.this.columnsl) {
                                        long j5 = j4 * 2;
                                        long access$500 = 2 * (DoubleFFT_2D.this.columnsl - j4);
                                        DoubleLargeArray doubleLargeArray = doubleLargeArray10;
                                        doubleLargeArray.setDouble(j5, doubleLargeArray.getDouble(access$500));
                                        DoubleLargeArray doubleLargeArray2 = doubleLargeArray10;
                                        long j6 = j;
                                        doubleLargeArray2.setDouble(j5 + 1, -doubleLargeArray2.getDouble(access$500 + 1));
                                        long j7 = j2 + j5;
                                        long j8 = access$300 - j5;
                                        DoubleLargeArray doubleLargeArray3 = doubleLargeArray10;
                                        doubleLargeArray3.setDouble(j7, doubleLargeArray3.getDouble(j8));
                                        DoubleLargeArray doubleLargeArray4 = doubleLargeArray10;
                                        doubleLargeArray4.setDouble(j7 + 1, -doubleLargeArray4.getDouble(j8 + 1));
                                        j4++;
                                        j3 = 1;
                                        j = j6;
                                    }
                                    j += j3;
                                }
                            }
                        });
                        i10++;
                        i11 = i12;
                    }
                    try {
                        ConcurrencyUtils.waitForCompletion(futureArr3);
                        return;
                    } catch (InterruptedException e7) {
                        Logger.getLogger(cls.getName()).log(Level.SEVERE, str6, e7);
                        return;
                    } catch (ExecutionException e8) {
                        Logger.getLogger(cls.getName()).log(Level.SEVERE, str6, e8);
                        return;
                    }
                }
            }
        }
        boolean z7 = z2;
        DoubleLargeArray doubleLargeArray11 = doubleLargeArray3;
        DoubleFFT_2D doubleFFT_2D2 = doubleFFT_2D;
        for (long j31 = 0; j31 < doubleFFT_2D2.rowsl; j31++) {
            doubleFFT_2D2.fftColumns.realInverse2(doubleLargeArray, doubleFFT_2D2.columnsl * j31, z7);
        }
        DoubleLargeArray doubleLargeArray12 = doubleLargeArray;
        for (long j32 = 0; j32 < doubleFFT_2D2.rowsl; j32++) {
            doubleLargeArray11.setDouble(j32, doubleLargeArray12.getDouble(doubleFFT_2D2.columnsl * j32));
        }
        DoubleLargeArray doubleLargeArray13 = doubleLargeArray11;
        doubleFFT_2D2.fftRows.realInverseFull(doubleLargeArray13, z7);
        for (long j33 = 1; j33 < j3; j33++) {
            long j34 = j33 * 2;
            long j35 = 0;
            while (j35 < doubleFFT_2D2.rowsl) {
                long j36 = (doubleFFT_2D2.columnsl * j35) + j34;
                long j37 = (j35 * 2) + (j33 * j5);
                doubleLargeArray13.setDouble(j37, doubleLargeArray12.getDouble(j36));
                doubleLargeArray13.setDouble(j37 + 1, doubleLargeArray12.getDouble(j36 + 1));
                j35++;
                j34 = j34;
            }
            doubleFFT_2D2.fftRows.complexInverse(doubleLargeArray13, j33 * j5, z7);
        }
        if (doubleFFT_2D2.columnsl % 2 == 0) {
            for (long j38 = 0; j38 < doubleFFT_2D2.rowsl; j38++) {
                doubleLargeArray13.setDouble((j3 * j5) + j38, doubleLargeArray12.getDouble((doubleFFT_2D2.columnsl * j38) + 1));
            }
            doubleFFT_2D2.fftRows.realInverseFull(doubleLargeArray13, j3 * j5, z7);
        } else {
            for (long j39 = 0; j39 < doubleFFT_2D2.rowsl; j39++) {
                long j40 = doubleFFT_2D2.columnsl * j39;
                long j41 = (j3 * j5) + (j39 * 2);
                doubleLargeArray13.setDouble(j41, doubleLargeArray12.getDouble((j3 * 2) + j40));
                doubleLargeArray13.setDouble(j41 + 1, doubleLargeArray12.getDouble(j40 + 1));
            }
            doubleFFT_2D2.fftRows.complexInverse(doubleLargeArray13, j3 * j5, z7);
        }
        for (long j42 = 0; j42 < doubleFFT_2D2.rowsl; j42++) {
            long j43 = j42 * 2;
            long j44 = 0;
            while (j44 < j4) {
                long j45 = (j42 * j2) + (j44 * 2);
                long j46 = (j44 * j5) + j43;
                doubleLargeArray12.setDouble(j45, doubleLargeArray13.getDouble(j46));
                doubleLargeArray12.setDouble(j45 + 1, doubleLargeArray13.getDouble(j46 + 1));
                j44++;
                j43 = j43;
            }
        }
        long j47 = 1;
        while (true) {
            long j48 = doubleFFT_2D2.rowsl;
            if (j47 < j48) {
                long j49 = j47 * j2;
                long j50 = ((j48 - j47) + 1) * j2;
                long j51 = j4;
                while (true) {
                    long j52 = doubleFFT_2D2.columnsl;
                    if (j51 >= j52) {
                        break;
                    }
                    long j53 = j51 * 2;
                    long j54 = (j52 - j51) * 2;
                    doubleLargeArray12.setDouble(j53, doubleLargeArray12.getDouble(j54));
                    doubleLargeArray12.setDouble(j53 + 1, -doubleLargeArray12.getDouble(j54 + 1));
                    long j55 = j49 + j53;
                    long j56 = j50 - j53;
                    doubleLargeArray12.setDouble(j55, doubleLargeArray12.getDouble(j56));
                    doubleLargeArray12.setDouble(j55 + 1, -doubleLargeArray12.getDouble(j56 + 1));
                    j51++;
                    doubleFFT_2D2 = this;
                }
                j47++;
                doubleFFT_2D2 = this;
            } else {
                return;
            }
        }
    }

    private void rdft2d_sub(int i, double[] dArr) {
        int i2 = this.rows >> 1;
        if (i < 0) {
            for (int i3 = 1; i3 < i2; i3++) {
                int i4 = this.columns;
                int i5 = i3 * i4;
                int i6 = (this.rows - i3) * i4;
                double d = dArr[i5];
                double d2 = dArr[i6];
                dArr[i5] = d + d2;
                dArr[i6] = d - d2;
                int i7 = i6 + 1;
                double d3 = dArr[i7];
                int i8 = i5 + 1;
                double d4 = dArr[i8];
                dArr[i8] = d4 + d3;
                dArr[i7] = d3 - d4;
            }
            return;
        }
        for (int i9 = 1; i9 < i2; i9++) {
            int i10 = this.columns;
            int i11 = i9 * i10;
            int i12 = (this.rows - i9) * i10;
            double d5 = (dArr[i11] - dArr[i12]) * 0.5d;
            dArr[i12] = d5;
            dArr[i11] = dArr[i11] - d5;
            int i13 = i12 + 1;
            int i14 = i11 + 1;
            double d6 = (dArr[i14] + dArr[i13]) * 0.5d;
            dArr[i13] = d6;
            dArr[i14] = dArr[i14] - d6;
        }
    }

    private void rdft2d_sub(int i, DoubleLargeArray doubleLargeArray) {
        DoubleLargeArray doubleLargeArray2 = doubleLargeArray;
        long j = this.rowsl >> 1;
        if (i < 0) {
            for (long j2 = 1; j2 < j; j2++) {
                long j3 = this.columnsl;
                long j4 = j2 * j3;
                long j5 = (this.rowsl - j2) * j3;
                double d = doubleLargeArray2.getDouble(j4) - doubleLargeArray2.getDouble(j5);
                doubleLargeArray2.setDouble(j4, doubleLargeArray2.getDouble(j4) + doubleLargeArray2.getDouble(j5));
                doubleLargeArray2.setDouble(j5, d);
                long j6 = j5 + 1;
                long j7 = j4 + 1;
                double d2 = doubleLargeArray2.getDouble(j6) - doubleLargeArray2.getDouble(j7);
                doubleLargeArray2.setDouble(j7, doubleLargeArray2.getDouble(j7) + doubleLargeArray2.getDouble(j6));
                doubleLargeArray2.setDouble(j6, d2);
            }
            return;
        }
        for (long j8 = 1; j8 < j; j8++) {
            long j9 = this.columnsl;
            long j10 = j8 * j9;
            long j11 = (this.rowsl - j8) * j9;
            doubleLargeArray2.setDouble(j11, (doubleLargeArray2.getDouble(j10) - doubleLargeArray2.getDouble(j11)) * 0.5d);
            doubleLargeArray2.setDouble(j10, doubleLargeArray2.getDouble(j10) - doubleLargeArray2.getDouble(j11));
            long j12 = j11 + 1;
            long j13 = j10 + 1;
            doubleLargeArray2.setDouble(j12, (doubleLargeArray2.getDouble(j13) + doubleLargeArray2.getDouble(j12)) * 0.5d);
            doubleLargeArray2.setDouble(j13, doubleLargeArray2.getDouble(j13) - doubleLargeArray2.getDouble(j12));
        }
    }

    private void rdft2d_sub(int i, double[][] dArr) {
        int i2 = this.rows >> 1;
        if (i < 0) {
            for (int i3 = 1; i3 < i2; i3++) {
                double[] dArr2 = dArr[i3];
                double d = dArr2[0];
                double[] dArr3 = dArr[this.rows - i3];
                double d2 = dArr3[0];
                dArr2[0] = d + d2;
                dArr3[0] = d - d2;
                double d3 = dArr3[1];
                double d4 = dArr2[1];
                dArr2[1] = d4 + d3;
                dArr3[1] = d3 - d4;
            }
            return;
        }
        for (int i4 = 1; i4 < i2; i4++) {
            double[] dArr4 = dArr[this.rows - i4];
            double[] dArr5 = dArr[i4];
            double d5 = (dArr5[0] - dArr4[0]) * 0.5d;
            dArr4[0] = d5;
            dArr5[0] = dArr5[0] - d5;
            double d6 = (dArr5[1] + dArr4[1]) * 0.5d;
            dArr4[1] = d6;
            dArr5[1] = dArr5[1] - d6;
        }
    }

    private void cdft2d_sub(int i, double[] dArr, boolean z) {
        boolean z2 = z;
        int i2 = this.rows * 8;
        int i3 = this.columns;
        if (i3 == 4) {
            i2 >>= 1;
        } else if (i3 < 4) {
            i2 >>= 2;
        }
        double[] dArr2 = new double[i2];
        int i4 = 0;
        if (i == -1) {
            if (i3 > 4) {
                for (int i5 = 0; i5 < this.columns; i5 += 8) {
                    int i6 = 0;
                    while (true) {
                        int i7 = this.rows;
                        if (i6 >= i7) {
                            break;
                        }
                        int i8 = (this.columns * i6) + i5;
                        int i9 = i6 * 2;
                        int i10 = (i7 * 2) + i9;
                        int i11 = (i7 * 2) + i10;
                        int i12 = (i7 * 2) + i11;
                        dArr2[i9] = dArr[i8];
                        dArr2[i9 + 1] = dArr[i8 + 1];
                        dArr2[i10] = dArr[i8 + 2];
                        dArr2[i10 + 1] = dArr[i8 + 3];
                        dArr2[i11] = dArr[i8 + 4];
                        dArr2[i11 + 1] = dArr[i8 + 5];
                        dArr2[i12] = dArr[i8 + 6];
                        dArr2[i12 + 1] = dArr[i8 + 7];
                        i6++;
                    }
                    this.fftRows.complexForward(dArr2, 0);
                    this.fftRows.complexForward(dArr2, this.rows * 2);
                    this.fftRows.complexForward(dArr2, this.rows * 4);
                    this.fftRows.complexForward(dArr2, this.rows * 6);
                    int i13 = 0;
                    while (true) {
                        int i14 = this.rows;
                        if (i13 >= i14) {
                            break;
                        }
                        int i15 = (this.columns * i13) + i5;
                        int i16 = i13 * 2;
                        int i17 = (i14 * 2) + i16;
                        int i18 = (i14 * 2) + i17;
                        int i19 = (i14 * 2) + i18;
                        dArr[i15] = dArr2[i16];
                        dArr[i15 + 1] = dArr2[i16 + 1];
                        dArr[i15 + 2] = dArr2[i17];
                        dArr[i15 + 3] = dArr2[i17 + 1];
                        dArr[i15 + 4] = dArr2[i18];
                        dArr[i15 + 5] = dArr2[i18 + 1];
                        dArr[i15 + 6] = dArr2[i19];
                        dArr[i15 + 7] = dArr2[i19 + 1];
                        i13++;
                    }
                }
            } else if (i3 == 4) {
                int i20 = 0;
                while (true) {
                    int i21 = this.rows;
                    if (i20 >= i21) {
                        break;
                    }
                    int i22 = this.columns * i20;
                    int i23 = i20 * 2;
                    int i24 = (i21 * 2) + i23;
                    dArr2[i23] = dArr[i22];
                    dArr2[i23 + 1] = dArr[i22 + 1];
                    dArr2[i24] = dArr[i22 + 2];
                    dArr2[i24 + 1] = dArr[i22 + 3];
                    i20++;
                }
                this.fftRows.complexForward(dArr2, 0);
                this.fftRows.complexForward(dArr2, this.rows * 2);
                while (true) {
                    int i25 = this.rows;
                    if (i4 < i25) {
                        int i26 = this.columns * i4;
                        int i27 = i4 * 2;
                        int i28 = (i25 * 2) + i27;
                        dArr[i26] = dArr2[i27];
                        dArr[i26 + 1] = dArr2[i27 + 1];
                        dArr[i26 + 2] = dArr2[i28];
                        dArr[i26 + 3] = dArr2[i28 + 1];
                        i4++;
                    } else {
                        return;
                    }
                }
            } else if (i3 == 2) {
                for (int i29 = 0; i29 < this.rows; i29++) {
                    int i30 = this.columns * i29;
                    int i31 = i29 * 2;
                    dArr2[i31] = dArr[i30];
                    dArr2[i31 + 1] = dArr[i30 + 1];
                }
                this.fftRows.complexForward(dArr2, 0);
                while (i4 < this.rows) {
                    int i32 = this.columns * i4;
                    int i33 = i4 * 2;
                    dArr[i32] = dArr2[i33];
                    dArr[i32 + 1] = dArr2[i33 + 1];
                    i4++;
                }
            }
        } else if (i3 > 4) {
            for (int i34 = 0; i34 < this.columns; i34 += 8) {
                int i35 = 0;
                while (true) {
                    int i36 = this.rows;
                    if (i35 >= i36) {
                        break;
                    }
                    int i37 = (this.columns * i35) + i34;
                    int i38 = i35 * 2;
                    int i39 = (i36 * 2) + i38;
                    int i40 = (i36 * 2) + i39;
                    int i41 = (i36 * 2) + i40;
                    dArr2[i38] = dArr[i37];
                    dArr2[i38 + 1] = dArr[i37 + 1];
                    dArr2[i39] = dArr[i37 + 2];
                    dArr2[i39 + 1] = dArr[i37 + 3];
                    dArr2[i40] = dArr[i37 + 4];
                    dArr2[i40 + 1] = dArr[i37 + 5];
                    dArr2[i41] = dArr[i37 + 6];
                    dArr2[i41 + 1] = dArr[i37 + 7];
                    i35++;
                }
                this.fftRows.complexInverse(dArr2, 0, z2);
                this.fftRows.complexInverse(dArr2, this.rows * 2, z2);
                this.fftRows.complexInverse(dArr2, this.rows * 4, z2);
                this.fftRows.complexInverse(dArr2, this.rows * 6, z2);
                int i42 = 0;
                while (true) {
                    int i43 = this.rows;
                    if (i42 >= i43) {
                        break;
                    }
                    int i44 = (this.columns * i42) + i34;
                    int i45 = i42 * 2;
                    int i46 = (i43 * 2) + i45;
                    int i47 = (i43 * 2) + i46;
                    int i48 = (i43 * 2) + i47;
                    dArr[i44] = dArr2[i45];
                    dArr[i44 + 1] = dArr2[i45 + 1];
                    dArr[i44 + 2] = dArr2[i46];
                    dArr[i44 + 3] = dArr2[i46 + 1];
                    dArr[i44 + 4] = dArr2[i47];
                    dArr[i44 + 5] = dArr2[i47 + 1];
                    dArr[i44 + 6] = dArr2[i48];
                    dArr[i44 + 7] = dArr2[i48 + 1];
                    i42++;
                }
            }
        } else if (i3 == 4) {
            int i49 = 0;
            while (true) {
                int i50 = this.rows;
                if (i49 >= i50) {
                    break;
                }
                int i51 = this.columns * i49;
                int i52 = i49 * 2;
                int i53 = (i50 * 2) + i52;
                dArr2[i52] = dArr[i51];
                dArr2[i52 + 1] = dArr[i51 + 1];
                dArr2[i53] = dArr[i51 + 2];
                dArr2[i53 + 1] = dArr[i51 + 3];
                i49++;
            }
            this.fftRows.complexInverse(dArr2, 0, z2);
            this.fftRows.complexInverse(dArr2, this.rows * 2, z2);
            while (true) {
                int i54 = this.rows;
                if (i4 < i54) {
                    int i55 = this.columns * i4;
                    int i56 = i4 * 2;
                    int i57 = (i54 * 2) + i56;
                    dArr[i55] = dArr2[i56];
                    dArr[i55 + 1] = dArr2[i56 + 1];
                    dArr[i55 + 2] = dArr2[i57];
                    dArr[i55 + 3] = dArr2[i57 + 1];
                    i4++;
                } else {
                    return;
                }
            }
        } else if (i3 == 2) {
            for (int i58 = 0; i58 < this.rows; i58++) {
                int i59 = this.columns * i58;
                int i60 = i58 * 2;
                dArr2[i60] = dArr[i59];
                dArr2[i60 + 1] = dArr[i59 + 1];
            }
            this.fftRows.complexInverse(dArr2, 0, z2);
            while (i4 < this.rows) {
                int i61 = this.columns * i4;
                int i62 = i4 * 2;
                dArr[i61] = dArr2[i62];
                dArr[i61 + 1] = dArr2[i62 + 1];
                i4++;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x022f  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x002e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void cdft2d_sub(int r30, pl.edu.icm.jlargearrays.DoubleLargeArray r31, boolean r32) {
        /*
            r29 = this;
            r0 = r29
            r1 = r31
            r2 = r32
            long r3 = r0.rowsl
            r5 = 8
            long r3 = r3 * r5
            long r7 = r0.columnsl
            r9 = 4
            int r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r11 != 0) goto L_0x0016
            r7 = 1
        L_0x0014:
            long r3 = r3 >> r7
            goto L_0x001c
        L_0x0016:
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 >= 0) goto L_0x001c
            r7 = 2
            goto L_0x0014
        L_0x001c:
            pl.edu.icm.jlargearrays.DoubleLargeArray r7 = new pl.edu.icm.jlargearrays.DoubleLargeArray
            r7.<init>((long) r3)
            r3 = -1
            r15 = 6
            r17 = 3
            r19 = 2
            r21 = 1
            r4 = r30
            if (r4 != r3) goto L_0x022f
            long r2 = r0.columnsl
            int r4 = (r2 > r9 ? 1 : (r2 == r9 ? 0 : -1))
            if (r4 <= 0) goto L_0x0159
            r2 = 0
        L_0x0036:
            long r5 = r0.columnsl
            int r4 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r4 >= 0) goto L_0x043b
            r4 = 0
        L_0x003e:
            long r11 = r0.rowsl
            int r6 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r6 >= 0) goto L_0x00b7
            long r13 = r0.columnsl
            long r13 = r13 * r4
            long r13 = r13 + r2
            long r9 = r4 * r19
            long r23 = r11 * r19
            r25 = r2
            long r2 = r23 + r9
            long r23 = r11 * r19
            r27 = r4
            long r4 = r2 + r23
            long r11 = r11 * r19
            long r11 = r11 + r4
            r23 = r11
            double r11 = r1.getDouble(r13)
            r7.setDouble(r9, r11)
            long r9 = r9 + r21
            long r11 = r13 + r21
            double r11 = r1.getDouble(r11)
            r7.setDouble(r9, r11)
            long r8 = r13 + r19
            double r8 = r1.getDouble(r8)
            r7.setDouble(r2, r8)
            long r2 = r2 + r21
            long r8 = r13 + r17
            double r8 = r1.getDouble(r8)
            r7.setDouble(r2, r8)
            r2 = 4
            long r9 = r13 + r2
            double r2 = r1.getDouble(r9)
            r7.setDouble(r4, r2)
            long r4 = r4 + r21
            r2 = 5
            long r8 = r13 + r2
            double r2 = r1.getDouble(r8)
            r7.setDouble(r4, r2)
            long r2 = r13 + r15
            double r2 = r1.getDouble(r2)
            r4 = r23
            r7.setDouble(r4, r2)
            long r11 = r4 + r21
            r2 = 7
            long r13 = r13 + r2
            double r2 = r1.getDouble(r13)
            r7.setDouble(r11, r2)
            long r4 = r27 + r21
            r2 = r25
            r9 = 4
            goto L_0x003e
        L_0x00b7:
            r25 = r2
            org.jtransforms.fft.DoubleFFT_1D r2 = r0.fftRows
            r3 = 0
            r2.complexForward((pl.edu.icm.jlargearrays.DoubleLargeArray) r7, (long) r3)
            org.jtransforms.fft.DoubleFFT_1D r2 = r0.fftRows
            long r3 = r0.rowsl
            long r3 = r3 * r19
            r2.complexForward((pl.edu.icm.jlargearrays.DoubleLargeArray) r7, (long) r3)
            org.jtransforms.fft.DoubleFFT_1D r2 = r0.fftRows
            long r3 = r0.rowsl
            r5 = 4
            long r3 = r3 * r5
            r2.complexForward((pl.edu.icm.jlargearrays.DoubleLargeArray) r7, (long) r3)
            org.jtransforms.fft.DoubleFFT_1D r2 = r0.fftRows
            long r3 = r0.rowsl
            long r3 = r3 * r15
            r2.complexForward((pl.edu.icm.jlargearrays.DoubleLargeArray) r7, (long) r3)
            r2 = 0
        L_0x00dd:
            long r4 = r0.rowsl
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 >= 0) goto L_0x0150
            long r8 = r0.columnsl
            long r8 = r8 * r2
            long r8 = r8 + r25
            long r10 = r2 * r19
            long r12 = r4 * r19
            long r12 = r12 + r10
            long r23 = r4 * r19
            r27 = r2
            long r2 = r12 + r23
            long r4 = r4 * r19
            long r4 = r4 + r2
            r23 = r4
            double r4 = r7.getDouble(r10)
            r1.setDouble(r8, r4)
            long r4 = r8 + r21
            long r10 = r10 + r21
            double r10 = r7.getDouble(r10)
            r1.setDouble(r4, r10)
            long r4 = r8 + r19
            double r10 = r7.getDouble(r12)
            r1.setDouble(r4, r10)
            long r4 = r8 + r17
            long r12 = r12 + r21
            double r10 = r7.getDouble(r12)
            r1.setDouble(r4, r10)
            r4 = 4
            long r10 = r8 + r4
            double r4 = r7.getDouble(r2)
            r1.setDouble(r10, r4)
            r4 = 5
            long r13 = r8 + r4
            long r2 = r2 + r21
            double r2 = r7.getDouble(r2)
            r1.setDouble(r13, r2)
            long r2 = r8 + r15
            r4 = r23
            double r10 = r7.getDouble(r4)
            r1.setDouble(r2, r10)
            r2 = 7
            long r8 = r8 + r2
            long r4 = r4 + r21
            double r2 = r7.getDouble(r4)
            r1.setDouble(r8, r2)
            long r2 = r27 + r21
            goto L_0x00dd
        L_0x0150:
            r2 = 8
            long r4 = r25 + r2
            r2 = r4
            r9 = 4
            goto L_0x0036
        L_0x0159:
            r4 = r9
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 != 0) goto L_0x01e0
            r2 = 0
        L_0x0160:
            long r4 = r0.rowsl
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 >= 0) goto L_0x0197
            long r8 = r0.columnsl
            long r8 = r8 * r2
            long r10 = r2 * r19
            long r4 = r4 * r19
            long r4 = r4 + r10
            double r12 = r1.getDouble(r8)
            r7.setDouble(r10, r12)
            long r10 = r10 + r21
            long r12 = r8 + r21
            double r12 = r1.getDouble(r12)
            r7.setDouble(r10, r12)
            long r10 = r8 + r19
            double r10 = r1.getDouble(r10)
            r7.setDouble(r4, r10)
            long r4 = r4 + r21
            long r8 = r8 + r17
            double r8 = r1.getDouble(r8)
            r7.setDouble(r4, r8)
            long r2 = r2 + r21
            goto L_0x0160
        L_0x0197:
            org.jtransforms.fft.DoubleFFT_1D r2 = r0.fftRows
            r3 = 0
            r2.complexForward((pl.edu.icm.jlargearrays.DoubleLargeArray) r7, (long) r3)
            org.jtransforms.fft.DoubleFFT_1D r2 = r0.fftRows
            long r3 = r0.rowsl
            long r3 = r3 * r19
            r2.complexForward((pl.edu.icm.jlargearrays.DoubleLargeArray) r7, (long) r3)
            r5 = 0
        L_0x01a9:
            long r2 = r0.rowsl
            int r4 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r4 >= 0) goto L_0x043b
            long r8 = r0.columnsl
            long r8 = r8 * r5
            long r10 = r5 * r19
            long r2 = r2 * r19
            long r2 = r2 + r10
            double r12 = r7.getDouble(r10)
            r1.setDouble(r8, r12)
            long r12 = r8 + r21
            long r10 = r10 + r21
            double r10 = r7.getDouble(r10)
            r1.setDouble(r12, r10)
            long r10 = r8 + r19
            double r12 = r7.getDouble(r2)
            r1.setDouble(r10, r12)
            long r8 = r8 + r17
            long r2 = r2 + r21
            double r2 = r7.getDouble(r2)
            r1.setDouble(r8, r2)
            long r5 = r5 + r21
            goto L_0x01a9
        L_0x01e0:
            int r2 = (r2 > r19 ? 1 : (r2 == r19 ? 0 : -1))
            if (r2 != 0) goto L_0x043b
            r2 = 0
        L_0x01e6:
            long r4 = r0.rowsl
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 >= 0) goto L_0x0206
            long r4 = r0.columnsl
            long r4 = r4 * r2
            long r8 = r2 * r19
            double r10 = r1.getDouble(r4)
            r7.setDouble(r8, r10)
            long r8 = r8 + r21
            long r4 = r4 + r21
            double r4 = r1.getDouble(r4)
            r7.setDouble(r8, r4)
            long r2 = r2 + r21
            goto L_0x01e6
        L_0x0206:
            org.jtransforms.fft.DoubleFFT_1D r2 = r0.fftRows
            r3 = 0
            r2.complexForward((pl.edu.icm.jlargearrays.DoubleLargeArray) r7, (long) r3)
            r5 = 0
        L_0x020f:
            long r2 = r0.rowsl
            int r2 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r2 >= 0) goto L_0x043b
            long r2 = r0.columnsl
            long r2 = r2 * r5
            long r8 = r5 * r19
            double r10 = r7.getDouble(r8)
            r1.setDouble(r2, r10)
            long r2 = r2 + r21
            long r8 = r8 + r21
            double r8 = r7.getDouble(r8)
            r1.setDouble(r2, r8)
            long r5 = r5 + r21
            goto L_0x020f
        L_0x022f:
            long r3 = r0.columnsl
            r5 = 4
            int r8 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r8 <= 0) goto L_0x0361
            r3 = 0
        L_0x0239:
            long r5 = r0.columnsl
            int r5 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r5 >= 0) goto L_0x043b
            r5 = 0
        L_0x0241:
            long r8 = r0.rowsl
            int r10 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r10 >= 0) goto L_0x02b8
            long r10 = r0.columnsl
            long r10 = r10 * r5
            long r10 = r10 + r3
            long r12 = r5 * r19
            long r23 = r8 * r19
            r25 = r3
            long r3 = r23 + r12
            long r23 = r8 * r19
            r27 = r5
            long r5 = r3 + r23
            long r8 = r8 * r19
            long r8 = r8 + r5
            r23 = r8
            double r8 = r1.getDouble(r10)
            r7.setDouble(r12, r8)
            long r12 = r12 + r21
            long r8 = r10 + r21
            double r8 = r1.getDouble(r8)
            r7.setDouble(r12, r8)
            long r8 = r10 + r19
            double r8 = r1.getDouble(r8)
            r7.setDouble(r3, r8)
            long r3 = r3 + r21
            long r8 = r10 + r17
            double r8 = r1.getDouble(r8)
            r7.setDouble(r3, r8)
            r3 = 4
            long r8 = r10 + r3
            double r3 = r1.getDouble(r8)
            r7.setDouble(r5, r3)
            long r5 = r5 + r21
            r3 = 5
            long r13 = r10 + r3
            double r3 = r1.getDouble(r13)
            r7.setDouble(r5, r3)
            long r3 = r10 + r15
            double r3 = r1.getDouble(r3)
            r5 = r23
            r7.setDouble(r5, r3)
            long r8 = r5 + r21
            r3 = 7
            long r10 = r10 + r3
            double r3 = r1.getDouble(r10)
            r7.setDouble(r8, r3)
            long r5 = r27 + r21
            r3 = r25
            goto L_0x0241
        L_0x02b8:
            r25 = r3
            org.jtransforms.fft.DoubleFFT_1D r3 = r0.fftRows
            r4 = 0
            r3.complexInverse((pl.edu.icm.jlargearrays.DoubleLargeArray) r7, (long) r4, (boolean) r2)
            org.jtransforms.fft.DoubleFFT_1D r3 = r0.fftRows
            long r4 = r0.rowsl
            long r4 = r4 * r19
            r3.complexInverse((pl.edu.icm.jlargearrays.DoubleLargeArray) r7, (long) r4, (boolean) r2)
            org.jtransforms.fft.DoubleFFT_1D r3 = r0.fftRows
            long r4 = r0.rowsl
            r8 = 4
            long r4 = r4 * r8
            r3.complexInverse((pl.edu.icm.jlargearrays.DoubleLargeArray) r7, (long) r4, (boolean) r2)
            org.jtransforms.fft.DoubleFFT_1D r3 = r0.fftRows
            long r4 = r0.rowsl
            long r4 = r4 * r15
            r3.complexInverse((pl.edu.icm.jlargearrays.DoubleLargeArray) r7, (long) r4, (boolean) r2)
            r3 = 0
        L_0x02de:
            long r5 = r0.rowsl
            int r8 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r8 >= 0) goto L_0x0354
            long r8 = r0.columnsl
            long r8 = r8 * r3
            long r8 = r8 + r25
            long r10 = r3 * r19
            long r12 = r5 * r19
            long r12 = r12 + r10
            long r23 = r5 * r19
            r27 = r3
            long r2 = r12 + r23
            long r5 = r5 * r19
            long r5 = r5 + r2
            r23 = r5
            double r4 = r7.getDouble(r10)
            r1.setDouble(r8, r4)
            long r4 = r8 + r21
            long r10 = r10 + r21
            double r10 = r7.getDouble(r10)
            r1.setDouble(r4, r10)
            long r4 = r8 + r19
            double r10 = r7.getDouble(r12)
            r1.setDouble(r4, r10)
            long r4 = r8 + r17
            long r12 = r12 + r21
            double r10 = r7.getDouble(r12)
            r1.setDouble(r4, r10)
            r4 = 4
            long r10 = r8 + r4
            double r4 = r7.getDouble(r2)
            r1.setDouble(r10, r4)
            r4 = 5
            long r13 = r8 + r4
            long r2 = r2 + r21
            double r2 = r7.getDouble(r2)
            r1.setDouble(r13, r2)
            long r2 = r8 + r15
            r10 = r23
            double r12 = r7.getDouble(r10)
            r1.setDouble(r2, r12)
            r2 = 7
            long r8 = r8 + r2
            long r10 = r10 + r21
            double r10 = r7.getDouble(r10)
            r1.setDouble(r8, r10)
            long r8 = r27 + r21
            r2 = r32
            r3 = r8
            goto L_0x02de
        L_0x0354:
            r2 = 7
            r4 = 5
            r8 = 8
            long r10 = r25 + r8
            r2 = r32
            r3 = r10
            goto L_0x0239
        L_0x0361:
            r5 = 4
            int r2 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r2 != 0) goto L_0x03eb
            r2 = 0
        L_0x0369:
            long r4 = r0.rowsl
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 >= 0) goto L_0x03a0
            long r8 = r0.columnsl
            long r8 = r8 * r2
            long r10 = r2 * r19
            long r4 = r4 * r19
            long r4 = r4 + r10
            double r12 = r1.getDouble(r8)
            r7.setDouble(r10, r12)
            long r10 = r10 + r21
            long r12 = r8 + r21
            double r12 = r1.getDouble(r12)
            r7.setDouble(r10, r12)
            long r10 = r8 + r19
            double r10 = r1.getDouble(r10)
            r7.setDouble(r4, r10)
            long r4 = r4 + r21
            long r8 = r8 + r17
            double r8 = r1.getDouble(r8)
            r7.setDouble(r4, r8)
            long r2 = r2 + r21
            goto L_0x0369
        L_0x03a0:
            org.jtransforms.fft.DoubleFFT_1D r2 = r0.fftRows
            r5 = r32
            r3 = 0
            r2.complexInverse((pl.edu.icm.jlargearrays.DoubleLargeArray) r7, (long) r3, (boolean) r5)
            org.jtransforms.fft.DoubleFFT_1D r2 = r0.fftRows
            long r3 = r0.rowsl
            long r3 = r3 * r19
            r2.complexInverse((pl.edu.icm.jlargearrays.DoubleLargeArray) r7, (long) r3, (boolean) r5)
            r5 = 0
        L_0x03b4:
            long r2 = r0.rowsl
            int r4 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r4 >= 0) goto L_0x043b
            long r8 = r0.columnsl
            long r8 = r8 * r5
            long r10 = r5 * r19
            long r2 = r2 * r19
            long r2 = r2 + r10
            double r12 = r7.getDouble(r10)
            r1.setDouble(r8, r12)
            long r12 = r8 + r21
            long r10 = r10 + r21
            double r10 = r7.getDouble(r10)
            r1.setDouble(r12, r10)
            long r10 = r8 + r19
            double r12 = r7.getDouble(r2)
            r1.setDouble(r10, r12)
            long r8 = r8 + r17
            long r2 = r2 + r21
            double r2 = r7.getDouble(r2)
            r1.setDouble(r8, r2)
            long r5 = r5 + r21
            goto L_0x03b4
        L_0x03eb:
            r5 = r32
            int r2 = (r3 > r19 ? 1 : (r3 == r19 ? 0 : -1))
            if (r2 != 0) goto L_0x043b
            r3 = 0
        L_0x03f3:
            long r8 = r0.rowsl
            int r2 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
            if (r2 >= 0) goto L_0x0413
            long r8 = r0.columnsl
            long r8 = r8 * r3
            long r10 = r3 * r19
            double r12 = r1.getDouble(r8)
            r7.setDouble(r10, r12)
            long r10 = r10 + r21
            long r8 = r8 + r21
            double r8 = r1.getDouble(r8)
            r7.setDouble(r10, r8)
            long r3 = r3 + r21
            goto L_0x03f3
        L_0x0413:
            org.jtransforms.fft.DoubleFFT_1D r2 = r0.fftRows
            r3 = 0
            r2.complexInverse((pl.edu.icm.jlargearrays.DoubleLargeArray) r7, (long) r3, (boolean) r5)
            r5 = r3
        L_0x041b:
            long r2 = r0.rowsl
            int r2 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r2 >= 0) goto L_0x043b
            long r2 = r0.columnsl
            long r2 = r2 * r5
            long r8 = r5 * r19
            double r10 = r7.getDouble(r8)
            r1.setDouble(r2, r10)
            long r2 = r2 + r21
            long r8 = r8 + r21
            double r8 = r7.getDouble(r8)
            r1.setDouble(r2, r8)
            long r5 = r5 + r21
            goto L_0x041b
        L_0x043b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.DoubleFFT_2D.cdft2d_sub(int, pl.edu.icm.jlargearrays.DoubleLargeArray, boolean):void");
    }

    private void cdft2d_sub(int i, double[][] dArr, boolean z) {
        boolean z2 = z;
        int i2 = this.rows * 8;
        int i3 = this.columns;
        if (i3 == 4) {
            i2 >>= 1;
        } else if (i3 < 4) {
            i2 >>= 2;
        }
        double[] dArr2 = new double[i2];
        if (i == -1) {
            if (i3 > 4) {
                for (int i4 = 0; i4 < this.columns; i4 += 8) {
                    int i5 = 0;
                    while (true) {
                        int i6 = this.rows;
                        if (i5 >= i6) {
                            break;
                        }
                        int i7 = i5 * 2;
                        int i8 = (i6 * 2) + i7;
                        int i9 = (i6 * 2) + i8;
                        int i10 = (i6 * 2) + i9;
                        double[] dArr3 = dArr[i5];
                        dArr2[i7] = dArr3[i4];
                        dArr2[i7 + 1] = dArr3[i4 + 1];
                        dArr2[i8] = dArr3[i4 + 2];
                        dArr2[i8 + 1] = dArr3[i4 + 3];
                        dArr2[i9] = dArr3[i4 + 4];
                        dArr2[i9 + 1] = dArr3[i4 + 5];
                        dArr2[i10] = dArr3[i4 + 6];
                        dArr2[i10 + 1] = dArr3[i4 + 7];
                        i5++;
                    }
                    this.fftRows.complexForward(dArr2, 0);
                    this.fftRows.complexForward(dArr2, this.rows * 2);
                    this.fftRows.complexForward(dArr2, this.rows * 4);
                    this.fftRows.complexForward(dArr2, this.rows * 6);
                    int i11 = 0;
                    while (true) {
                        int i12 = this.rows;
                        if (i11 >= i12) {
                            break;
                        }
                        int i13 = i11 * 2;
                        int i14 = (i12 * 2) + i13;
                        int i15 = (i12 * 2) + i14;
                        int i16 = (i12 * 2) + i15;
                        double[] dArr4 = dArr[i11];
                        dArr4[i4] = dArr2[i13];
                        dArr4[i4 + 1] = dArr2[i13 + 1];
                        dArr4[i4 + 2] = dArr2[i14];
                        dArr4[i4 + 3] = dArr2[i14 + 1];
                        dArr4[i4 + 4] = dArr2[i15];
                        dArr4[i4 + 5] = dArr2[i15 + 1];
                        dArr4[i4 + 6] = dArr2[i16];
                        dArr4[i4 + 7] = dArr2[i16 + 1];
                        i11++;
                    }
                }
            } else if (i3 == 4) {
                int i17 = 0;
                while (true) {
                    int i18 = this.rows;
                    if (i17 >= i18) {
                        break;
                    }
                    int i19 = i17 * 2;
                    int i20 = (i18 * 2) + i19;
                    double[] dArr5 = dArr[i17];
                    dArr2[i19] = dArr5[0];
                    dArr2[i19 + 1] = dArr5[1];
                    dArr2[i20] = dArr5[2];
                    dArr2[i20 + 1] = dArr5[3];
                    i17++;
                }
                this.fftRows.complexForward(dArr2, 0);
                this.fftRows.complexForward(dArr2, this.rows * 2);
                int i21 = 0;
                while (true) {
                    int i22 = this.rows;
                    if (i21 < i22) {
                        int i23 = i21 * 2;
                        int i24 = (i22 * 2) + i23;
                        double[] dArr6 = dArr[i21];
                        dArr6[0] = dArr2[i23];
                        dArr6[1] = dArr2[i23 + 1];
                        dArr6[2] = dArr2[i24];
                        dArr6[3] = dArr2[i24 + 1];
                        i21++;
                    } else {
                        return;
                    }
                }
            } else if (i3 == 2) {
                for (int i25 = 0; i25 < this.rows; i25++) {
                    int i26 = i25 * 2;
                    double[] dArr7 = dArr[i25];
                    dArr2[i26] = dArr7[0];
                    dArr2[i26 + 1] = dArr7[1];
                }
                this.fftRows.complexForward(dArr2, 0);
                for (int i27 = 0; i27 < this.rows; i27++) {
                    int i28 = i27 * 2;
                    double[] dArr8 = dArr[i27];
                    dArr8[0] = dArr2[i28];
                    dArr8[1] = dArr2[i28 + 1];
                }
            }
        } else if (i3 > 4) {
            for (int i29 = 0; i29 < this.columns; i29 += 8) {
                int i30 = 0;
                while (true) {
                    int i31 = this.rows;
                    if (i30 >= i31) {
                        break;
                    }
                    int i32 = i30 * 2;
                    int i33 = (i31 * 2) + i32;
                    int i34 = (i31 * 2) + i33;
                    int i35 = (i31 * 2) + i34;
                    double[] dArr9 = dArr[i30];
                    dArr2[i32] = dArr9[i29];
                    dArr2[i32 + 1] = dArr9[i29 + 1];
                    dArr2[i33] = dArr9[i29 + 2];
                    dArr2[i33 + 1] = dArr9[i29 + 3];
                    dArr2[i34] = dArr9[i29 + 4];
                    dArr2[i34 + 1] = dArr9[i29 + 5];
                    dArr2[i35] = dArr9[i29 + 6];
                    dArr2[i35 + 1] = dArr9[i29 + 7];
                    i30++;
                }
                this.fftRows.complexInverse(dArr2, 0, z2);
                this.fftRows.complexInverse(dArr2, this.rows * 2, z2);
                this.fftRows.complexInverse(dArr2, this.rows * 4, z2);
                this.fftRows.complexInverse(dArr2, this.rows * 6, z2);
                int i36 = 0;
                while (true) {
                    int i37 = this.rows;
                    if (i36 >= i37) {
                        break;
                    }
                    int i38 = i36 * 2;
                    int i39 = (i37 * 2) + i38;
                    int i40 = (i37 * 2) + i39;
                    int i41 = (i37 * 2) + i40;
                    double[] dArr10 = dArr[i36];
                    dArr10[i29] = dArr2[i38];
                    dArr10[i29 + 1] = dArr2[i38 + 1];
                    dArr10[i29 + 2] = dArr2[i39];
                    dArr10[i29 + 3] = dArr2[i39 + 1];
                    dArr10[i29 + 4] = dArr2[i40];
                    dArr10[i29 + 5] = dArr2[i40 + 1];
                    dArr10[i29 + 6] = dArr2[i41];
                    dArr10[i29 + 7] = dArr2[i41 + 1];
                    i36++;
                }
            }
        } else if (i3 == 4) {
            int i42 = 0;
            while (true) {
                int i43 = this.rows;
                if (i42 >= i43) {
                    break;
                }
                int i44 = i42 * 2;
                int i45 = (i43 * 2) + i44;
                double[] dArr11 = dArr[i42];
                dArr2[i44] = dArr11[0];
                dArr2[i44 + 1] = dArr11[1];
                dArr2[i45] = dArr11[2];
                dArr2[i45 + 1] = dArr11[3];
                i42++;
            }
            this.fftRows.complexInverse(dArr2, 0, z2);
            this.fftRows.complexInverse(dArr2, this.rows * 2, z2);
            int i46 = 0;
            while (true) {
                int i47 = this.rows;
                if (i46 < i47) {
                    int i48 = i46 * 2;
                    int i49 = (i47 * 2) + i48;
                    double[] dArr12 = dArr[i46];
                    dArr12[0] = dArr2[i48];
                    dArr12[1] = dArr2[i48 + 1];
                    dArr12[2] = dArr2[i49];
                    dArr12[3] = dArr2[i49 + 1];
                    i46++;
                } else {
                    return;
                }
            }
        } else if (i3 == 2) {
            for (int i50 = 0; i50 < this.rows; i50++) {
                int i51 = i50 * 2;
                double[] dArr13 = dArr[i50];
                dArr2[i51] = dArr13[0];
                dArr2[i51 + 1] = dArr13[1];
            }
            this.fftRows.complexInverse(dArr2, 0, z2);
            for (int i52 = 0; i52 < this.rows; i52++) {
                int i53 = i52 * 2;
                double[] dArr14 = dArr[i52];
                dArr14[0] = dArr2[i53];
                dArr14[1] = dArr2[i53 + 1];
            }
        }
    }

    private void xdft2d0_subth1(int i, int i2, double[] dArr, boolean z) {
        Class<DoubleFFT_2D> cls = DoubleFFT_2D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = this.rows;
        if (numberOfThreads <= i3) {
            i3 = ConcurrencyUtils.getNumberOfThreads();
        }
        int i4 = i3;
        Future[] futureArr = new Future[i4];
        for (int i5 = 0; i5 < i4; i5++) {
            final int i6 = i;
            final int i7 = i2;
            final int i8 = i5;
            final int i9 = i4;
            final double[] dArr2 = dArr;
            final boolean z2 = z;
            futureArr[i5] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    if (i6 == 0) {
                        if (i7 == -1) {
                            int i = i8;
                            while (i < DoubleFFT_2D.this.rows) {
                                DoubleFFT_2D.this.fftColumns.complexForward(dArr2, DoubleFFT_2D.this.columns * i);
                                i += i9;
                            }
                            return;
                        }
                        int i2 = i8;
                        while (i2 < DoubleFFT_2D.this.rows) {
                            DoubleFFT_2D.this.fftColumns.complexInverse(dArr2, DoubleFFT_2D.this.columns * i2, z2);
                            i2 += i9;
                        }
                    } else if (i7 == 1) {
                        int i3 = i8;
                        while (i3 < DoubleFFT_2D.this.rows) {
                            DoubleFFT_2D.this.fftColumns.realForward(dArr2, DoubleFFT_2D.this.columns * i3);
                            i3 += i9;
                        }
                    } else {
                        int i4 = i8;
                        while (i4 < DoubleFFT_2D.this.rows) {
                            DoubleFFT_2D.this.fftColumns.realInverse(dArr2, DoubleFFT_2D.this.columns * i4, z2);
                            i4 += i9;
                        }
                    }
                }
            });
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
        } catch (ExecutionException e2) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
        }
    }

    private void xdft2d0_subth1(long j, int i, DoubleLargeArray doubleLargeArray, boolean z) {
        Class<DoubleFFT_2D> cls = DoubleFFT_2D.class;
        long j2 = this.rowsl;
        if (((long) ConcurrencyUtils.getNumberOfThreads()) <= j2) {
            j2 = (long) ConcurrencyUtils.getNumberOfThreads();
        }
        int i2 = (int) j2;
        Future[] futureArr = new Future[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            final long j3 = j;
            final int i4 = i;
            final int i5 = i3;
            final int i6 = i2;
            final DoubleLargeArray doubleLargeArray2 = doubleLargeArray;
            final boolean z2 = z;
            futureArr[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    if (j3 == 0) {
                        if (i4 == -1) {
                            long j = (long) i5;
                            while (j < DoubleFFT_2D.this.rowsl) {
                                DoubleFFT_2D.this.fftColumns.complexForward(doubleLargeArray2, DoubleFFT_2D.this.columnsl * j);
                                j += (long) i6;
                            }
                            return;
                        }
                        long j2 = (long) i5;
                        while (j2 < DoubleFFT_2D.this.rowsl) {
                            DoubleFFT_2D.this.fftColumns.complexInverse(doubleLargeArray2, DoubleFFT_2D.this.columnsl * j2, z2);
                            j2 += (long) i6;
                        }
                    } else if (i4 == 1) {
                        long j3 = (long) i5;
                        while (j3 < DoubleFFT_2D.this.rowsl) {
                            DoubleFFT_2D.this.fftColumns.realForward(doubleLargeArray2, DoubleFFT_2D.this.columnsl * j3);
                            j3 += (long) i6;
                        }
                    } else {
                        long j4 = (long) i5;
                        while (j4 < DoubleFFT_2D.this.rowsl) {
                            DoubleFFT_2D.this.fftColumns.realInverse(doubleLargeArray2, DoubleFFT_2D.this.columnsl * j4, z2);
                            j4 += (long) i6;
                        }
                    }
                }
            });
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
        } catch (ExecutionException e2) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
        }
    }

    private void xdft2d0_subth2(int i, int i2, double[] dArr, boolean z) {
        Class<DoubleFFT_2D> cls = DoubleFFT_2D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = this.rows;
        if (numberOfThreads <= i3) {
            i3 = ConcurrencyUtils.getNumberOfThreads();
        }
        int i4 = i3;
        Future[] futureArr = new Future[i4];
        for (int i5 = 0; i5 < i4; i5++) {
            final int i6 = i;
            final int i7 = i2;
            final int i8 = i5;
            final int i9 = i4;
            final double[] dArr2 = dArr;
            final boolean z2 = z;
            futureArr[i5] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    if (i6 == 0) {
                        if (i7 == -1) {
                            int i = i8;
                            while (i < DoubleFFT_2D.this.rows) {
                                DoubleFFT_2D.this.fftColumns.complexForward(dArr2, DoubleFFT_2D.this.columns * i);
                                i += i9;
                            }
                            return;
                        }
                        int i2 = i8;
                        while (i2 < DoubleFFT_2D.this.rows) {
                            DoubleFFT_2D.this.fftColumns.complexInverse(dArr2, DoubleFFT_2D.this.columns * i2, z2);
                            i2 += i9;
                        }
                    } else if (i7 == 1) {
                        int i3 = i8;
                        while (i3 < DoubleFFT_2D.this.rows) {
                            DoubleFFT_2D.this.fftColumns.realForward(dArr2, DoubleFFT_2D.this.columns * i3);
                            i3 += i9;
                        }
                    } else {
                        int i4 = i8;
                        while (i4 < DoubleFFT_2D.this.rows) {
                            DoubleFFT_2D.this.fftColumns.realInverse2(dArr2, DoubleFFT_2D.this.columns * i4, z2);
                            i4 += i9;
                        }
                    }
                }
            });
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
        } catch (ExecutionException e2) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
        }
    }

    private void xdft2d0_subth2(long j, int i, DoubleLargeArray doubleLargeArray, boolean z) {
        Class<DoubleFFT_2D> cls = DoubleFFT_2D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i2 = this.rows;
        if (numberOfThreads <= i2) {
            i2 = ConcurrencyUtils.getNumberOfThreads();
        }
        int i3 = i2;
        Future[] futureArr = new Future[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            final long j2 = (long) i4;
            final long j3 = j;
            final int i5 = i;
            final int i6 = i3;
            final DoubleLargeArray doubleLargeArray2 = doubleLargeArray;
            final boolean z2 = z;
            futureArr[i4] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    if (j3 == 0) {
                        if (i5 == -1) {
                            long j = j2;
                            while (j < DoubleFFT_2D.this.rowsl) {
                                DoubleFFT_2D.this.fftColumns.complexForward(doubleLargeArray2, DoubleFFT_2D.this.columnsl * j);
                                j += (long) i6;
                            }
                            return;
                        }
                        long j2 = j2;
                        while (j2 < DoubleFFT_2D.this.rowsl) {
                            DoubleFFT_2D.this.fftColumns.complexInverse(doubleLargeArray2, DoubleFFT_2D.this.columnsl * j2, z2);
                            j2 += (long) i6;
                        }
                    } else if (i5 == 1) {
                        long j3 = j2;
                        while (j3 < DoubleFFT_2D.this.rowsl) {
                            DoubleFFT_2D.this.fftColumns.realForward(doubleLargeArray2, DoubleFFT_2D.this.columnsl * j3);
                            j3 += (long) i6;
                        }
                    } else {
                        long j4 = j2;
                        while (j4 < DoubleFFT_2D.this.rowsl) {
                            DoubleFFT_2D.this.fftColumns.realInverse2(doubleLargeArray2, DoubleFFT_2D.this.columnsl * j4, z2);
                            j4 += (long) i6;
                        }
                    }
                }
            });
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
        } catch (ExecutionException e2) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
        }
    }

    private void xdft2d0_subth1(int i, int i2, double[][] dArr, boolean z) {
        Class<DoubleFFT_2D> cls = DoubleFFT_2D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = this.rows;
        if (numberOfThreads <= i3) {
            i3 = ConcurrencyUtils.getNumberOfThreads();
        }
        int i4 = i3;
        Future[] futureArr = new Future[i4];
        for (int i5 = 0; i5 < i4; i5++) {
            final int i6 = i;
            final int i7 = i2;
            final int i8 = i5;
            final int i9 = i4;
            final double[][] dArr2 = dArr;
            final boolean z2 = z;
            futureArr[i5] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    if (i6 == 0) {
                        if (i7 == -1) {
                            int i = i8;
                            while (i < DoubleFFT_2D.this.rows) {
                                DoubleFFT_2D.this.fftColumns.complexForward(dArr2[i]);
                                i += i9;
                            }
                            return;
                        }
                        int i2 = i8;
                        while (i2 < DoubleFFT_2D.this.rows) {
                            DoubleFFT_2D.this.fftColumns.complexInverse(dArr2[i2], z2);
                            i2 += i9;
                        }
                    } else if (i7 == 1) {
                        int i3 = i8;
                        while (i3 < DoubleFFT_2D.this.rows) {
                            DoubleFFT_2D.this.fftColumns.realForward(dArr2[i3]);
                            i3 += i9;
                        }
                    } else {
                        int i4 = i8;
                        while (i4 < DoubleFFT_2D.this.rows) {
                            DoubleFFT_2D.this.fftColumns.realInverse(dArr2[i4], z2);
                            i4 += i9;
                        }
                    }
                }
            });
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
        } catch (ExecutionException e2) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
        }
    }

    private void xdft2d0_subth2(int i, int i2, double[][] dArr, boolean z) {
        Class<DoubleFFT_2D> cls = DoubleFFT_2D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = this.rows;
        if (numberOfThreads <= i3) {
            i3 = ConcurrencyUtils.getNumberOfThreads();
        }
        int i4 = i3;
        Future[] futureArr = new Future[i4];
        for (int i5 = 0; i5 < i4; i5++) {
            final int i6 = i;
            final int i7 = i2;
            final int i8 = i5;
            final int i9 = i4;
            final double[][] dArr2 = dArr;
            final boolean z2 = z;
            futureArr[i5] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    if (i6 == 0) {
                        if (i7 == -1) {
                            int i = i8;
                            while (i < DoubleFFT_2D.this.rows) {
                                DoubleFFT_2D.this.fftColumns.complexForward(dArr2[i]);
                                i += i9;
                            }
                            return;
                        }
                        int i2 = i8;
                        while (i2 < DoubleFFT_2D.this.rows) {
                            DoubleFFT_2D.this.fftColumns.complexInverse(dArr2[i2], z2);
                            i2 += i9;
                        }
                    } else if (i7 == 1) {
                        int i3 = i8;
                        while (i3 < DoubleFFT_2D.this.rows) {
                            DoubleFFT_2D.this.fftColumns.realForward(dArr2[i3]);
                            i3 += i9;
                        }
                    } else {
                        int i4 = i8;
                        while (i4 < DoubleFFT_2D.this.rows) {
                            DoubleFFT_2D.this.fftColumns.realInverse2(dArr2[i4], 0, z2);
                            i4 += i9;
                        }
                    }
                }
            });
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
        } catch (ExecutionException e2) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
        }
    }

    private void cdft2d_subth(int i, double[] dArr, boolean z) {
        Class<DoubleFFT_2D> cls = DoubleFFT_2D.class;
        int min = FastMath.min(this.columns / 2, ConcurrencyUtils.getNumberOfThreads());
        int i2 = this.rows * 8;
        int i3 = this.columns;
        if (i3 == 4) {
            i2 >>= 1;
        } else if (i3 < 4) {
            i2 >>= 2;
        }
        int i4 = i2;
        Future[] futureArr = new Future[min];
        for (int i5 = 0; i5 < min; i5++) {
            final int i6 = i4;
            final int i7 = i;
            final int i8 = min;
            final int i9 = i5;
            final double[] dArr2 = dArr;
            final boolean z2 = z;
            futureArr[i5] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    double[] dArr = new double[i6];
                    int i = 0;
                    if (i7 == -1) {
                        if (DoubleFFT_2D.this.columns > i8 * 4) {
                            int i2 = i9 * 8;
                            while (i2 < DoubleFFT_2D.this.columns) {
                                for (int i3 = 0; i3 < DoubleFFT_2D.this.rows; i3++) {
                                    int access$400 = (DoubleFFT_2D.this.columns * i3) + i2;
                                    int i4 = i3 * 2;
                                    int access$100 = (DoubleFFT_2D.this.rows * 2) + i4;
                                    int access$1002 = (DoubleFFT_2D.this.rows * 2) + access$100;
                                    int access$1003 = (DoubleFFT_2D.this.rows * 2) + access$1002;
                                    double[] dArr2 = dArr2;
                                    dArr[i4] = dArr2[access$400];
                                    dArr[i4 + 1] = dArr2[access$400 + 1];
                                    dArr[access$100] = dArr2[access$400 + 2];
                                    dArr[access$100 + 1] = dArr2[access$400 + 3];
                                    dArr[access$1002] = dArr2[access$400 + 4];
                                    dArr[access$1002 + 1] = dArr2[access$400 + 5];
                                    dArr[access$1003] = dArr2[access$400 + 6];
                                    dArr[access$1003 + 1] = dArr2[access$400 + 7];
                                }
                                DoubleFFT_2D.this.fftRows.complexForward(dArr, 0);
                                DoubleFFT_2D.this.fftRows.complexForward(dArr, DoubleFFT_2D.this.rows * 2);
                                DoubleFFT_2D.this.fftRows.complexForward(dArr, DoubleFFT_2D.this.rows * 4);
                                DoubleFFT_2D.this.fftRows.complexForward(dArr, DoubleFFT_2D.this.rows * 6);
                                for (int i5 = 0; i5 < DoubleFFT_2D.this.rows; i5++) {
                                    int access$4002 = (DoubleFFT_2D.this.columns * i5) + i2;
                                    int i6 = i5 * 2;
                                    int access$1004 = (DoubleFFT_2D.this.rows * 2) + i6;
                                    int access$1005 = (DoubleFFT_2D.this.rows * 2) + access$1004;
                                    int access$1006 = (DoubleFFT_2D.this.rows * 2) + access$1005;
                                    double[] dArr3 = dArr2;
                                    dArr3[access$4002] = dArr[i6];
                                    dArr3[access$4002 + 1] = dArr[i6 + 1];
                                    dArr3[access$4002 + 2] = dArr[access$1004];
                                    dArr3[access$4002 + 3] = dArr[access$1004 + 1];
                                    dArr3[access$4002 + 4] = dArr[access$1005];
                                    dArr3[access$4002 + 5] = dArr[access$1005 + 1];
                                    dArr3[access$4002 + 6] = dArr[access$1006];
                                    dArr3[access$4002 + 7] = dArr[access$1006 + 1];
                                }
                                i2 += i8 * 8;
                            }
                        } else if (DoubleFFT_2D.this.columns == i8 * 4) {
                            for (int i7 = 0; i7 < DoubleFFT_2D.this.rows; i7++) {
                                int access$4003 = (DoubleFFT_2D.this.columns * i7) + (i9 * 4);
                                int i8 = i7 * 2;
                                int access$1007 = (DoubleFFT_2D.this.rows * 2) + i8;
                                double[] dArr4 = dArr2;
                                dArr[i8] = dArr4[access$4003];
                                dArr[i8 + 1] = dArr4[access$4003 + 1];
                                dArr[access$1007] = dArr4[access$4003 + 2];
                                dArr[access$1007 + 1] = dArr4[access$4003 + 3];
                            }
                            DoubleFFT_2D.this.fftRows.complexForward(dArr, 0);
                            DoubleFFT_2D.this.fftRows.complexForward(dArr, DoubleFFT_2D.this.rows * 2);
                            while (i < DoubleFFT_2D.this.rows) {
                                int access$4004 = (DoubleFFT_2D.this.columns * i) + (i9 * 4);
                                int i9 = i * 2;
                                int access$1008 = (DoubleFFT_2D.this.rows * 2) + i9;
                                double[] dArr5 = dArr2;
                                dArr5[access$4004] = dArr[i9];
                                dArr5[access$4004 + 1] = dArr[i9 + 1];
                                dArr5[access$4004 + 2] = dArr[access$1008];
                                dArr5[access$4004 + 3] = dArr[access$1008 + 1];
                                i++;
                            }
                        } else if (DoubleFFT_2D.this.columns == i8 * 2) {
                            for (int i10 = 0; i10 < DoubleFFT_2D.this.rows; i10++) {
                                int access$4005 = (DoubleFFT_2D.this.columns * i10) + (i9 * 2);
                                int i11 = i10 * 2;
                                double[] dArr6 = dArr2;
                                dArr[i11] = dArr6[access$4005];
                                dArr[i11 + 1] = dArr6[access$4005 + 1];
                            }
                            DoubleFFT_2D.this.fftRows.complexForward(dArr, 0);
                            while (i < DoubleFFT_2D.this.rows) {
                                int access$4006 = (DoubleFFT_2D.this.columns * i) + (i9 * 2);
                                int i12 = i * 2;
                                double[] dArr7 = dArr2;
                                dArr7[access$4006] = dArr[i12];
                                dArr7[access$4006 + 1] = dArr[i12 + 1];
                                i++;
                            }
                        }
                    } else if (DoubleFFT_2D.this.columns > i8 * 4) {
                        int i13 = i9 * 8;
                        while (i13 < DoubleFFT_2D.this.columns) {
                            for (int i14 = 0; i14 < DoubleFFT_2D.this.rows; i14++) {
                                int access$4007 = (DoubleFFT_2D.this.columns * i14) + i13;
                                int i15 = i14 * 2;
                                int access$1009 = (DoubleFFT_2D.this.rows * 2) + i15;
                                int access$10010 = (DoubleFFT_2D.this.rows * 2) + access$1009;
                                int access$10011 = (DoubleFFT_2D.this.rows * 2) + access$10010;
                                double[] dArr8 = dArr2;
                                dArr[i15] = dArr8[access$4007];
                                dArr[i15 + 1] = dArr8[access$4007 + 1];
                                dArr[access$1009] = dArr8[access$4007 + 2];
                                dArr[access$1009 + 1] = dArr8[access$4007 + 3];
                                dArr[access$10010] = dArr8[access$4007 + 4];
                                dArr[access$10010 + 1] = dArr8[access$4007 + 5];
                                dArr[access$10011] = dArr8[access$4007 + 6];
                                dArr[access$10011 + 1] = dArr8[access$4007 + 7];
                            }
                            DoubleFFT_2D.this.fftRows.complexInverse(dArr, 0, z2);
                            DoubleFFT_2D.this.fftRows.complexInverse(dArr, DoubleFFT_2D.this.rows * 2, z2);
                            DoubleFFT_2D.this.fftRows.complexInverse(dArr, DoubleFFT_2D.this.rows * 4, z2);
                            DoubleFFT_2D.this.fftRows.complexInverse(dArr, DoubleFFT_2D.this.rows * 6, z2);
                            for (int i16 = 0; i16 < DoubleFFT_2D.this.rows; i16++) {
                                int access$4008 = (DoubleFFT_2D.this.columns * i16) + i13;
                                int i17 = i16 * 2;
                                int access$10012 = (DoubleFFT_2D.this.rows * 2) + i17;
                                int access$10013 = (DoubleFFT_2D.this.rows * 2) + access$10012;
                                int access$10014 = (DoubleFFT_2D.this.rows * 2) + access$10013;
                                double[] dArr9 = dArr2;
                                dArr9[access$4008] = dArr[i17];
                                dArr9[access$4008 + 1] = dArr[i17 + 1];
                                dArr9[access$4008 + 2] = dArr[access$10012];
                                dArr9[access$4008 + 3] = dArr[access$10012 + 1];
                                dArr9[access$4008 + 4] = dArr[access$10013];
                                dArr9[access$4008 + 5] = dArr[access$10013 + 1];
                                dArr9[access$4008 + 6] = dArr[access$10014];
                                dArr9[access$4008 + 7] = dArr[access$10014 + 1];
                            }
                            i13 += i8 * 8;
                        }
                    } else if (DoubleFFT_2D.this.columns == i8 * 4) {
                        for (int i18 = 0; i18 < DoubleFFT_2D.this.rows; i18++) {
                            int access$4009 = (DoubleFFT_2D.this.columns * i18) + (i9 * 4);
                            int i19 = i18 * 2;
                            int access$10015 = (DoubleFFT_2D.this.rows * 2) + i19;
                            double[] dArr10 = dArr2;
                            dArr[i19] = dArr10[access$4009];
                            dArr[i19 + 1] = dArr10[access$4009 + 1];
                            dArr[access$10015] = dArr10[access$4009 + 2];
                            dArr[access$10015 + 1] = dArr10[access$4009 + 3];
                        }
                        DoubleFFT_2D.this.fftRows.complexInverse(dArr, 0, z2);
                        DoubleFFT_2D.this.fftRows.complexInverse(dArr, DoubleFFT_2D.this.rows * 2, z2);
                        while (i < DoubleFFT_2D.this.rows) {
                            int access$40010 = (DoubleFFT_2D.this.columns * i) + (i9 * 4);
                            int i20 = i * 2;
                            int access$10016 = (DoubleFFT_2D.this.rows * 2) + i20;
                            double[] dArr11 = dArr2;
                            dArr11[access$40010] = dArr[i20];
                            dArr11[access$40010 + 1] = dArr[i20 + 1];
                            dArr11[access$40010 + 2] = dArr[access$10016];
                            dArr11[access$40010 + 3] = dArr[access$10016 + 1];
                            i++;
                        }
                    } else if (DoubleFFT_2D.this.columns == i8 * 2) {
                        for (int i21 = 0; i21 < DoubleFFT_2D.this.rows; i21++) {
                            int access$40011 = (DoubleFFT_2D.this.columns * i21) + (i9 * 2);
                            int i22 = i21 * 2;
                            double[] dArr12 = dArr2;
                            dArr[i22] = dArr12[access$40011];
                            dArr[i22 + 1] = dArr12[access$40011 + 1];
                        }
                        DoubleFFT_2D.this.fftRows.complexInverse(dArr, 0, z2);
                        while (i < DoubleFFT_2D.this.rows) {
                            int access$40012 = (DoubleFFT_2D.this.columns * i) + (i9 * 2);
                            int i23 = i * 2;
                            double[] dArr13 = dArr2;
                            dArr13[access$40012] = dArr[i23];
                            dArr13[access$40012 + 1] = dArr[i23 + 1];
                            i++;
                        }
                    }
                }
            });
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
        } catch (ExecutionException e2) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:9:0x0030 A[LOOP:0: B:8:0x002e->B:9:0x0030, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void cdft2d_subth(int r19, pl.edu.icm.jlargearrays.DoubleLargeArray r20, boolean r21) {
        /*
            r18 = this;
            r11 = r18
            java.lang.Class<org.jtransforms.fft.DoubleFFT_2D> r12 = org.jtransforms.fft.DoubleFFT_2D.class
            long r0 = r11.columnsl
            r2 = 2
            long r0 = r0 / r2
            int r2 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getNumberOfThreads()
            long r2 = (long) r2
            long r0 = org.apache.commons.math3.util.FastMath.min((long) r0, (long) r2)
            int r0 = (int) r0
            r1 = 8
            long r3 = r11.rowsl
            long r3 = r3 * r1
            long r1 = r11.columnsl
            r5 = 4
            int r7 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r7 != 0) goto L_0x0023
            r1 = 1
        L_0x0021:
            long r3 = r3 >> r1
            goto L_0x0029
        L_0x0023:
            int r1 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r1 >= 0) goto L_0x0029
            r1 = 2
            goto L_0x0021
        L_0x0029:
            r13 = r3
            java.util.concurrent.Future[] r15 = new java.util.concurrent.Future[r0]
            r1 = 0
            r10 = r1
        L_0x002e:
            if (r10 >= r0) goto L_0x004d
            long r7 = (long) r10
            org.jtransforms.fft.DoubleFFT_2D$44 r16 = new org.jtransforms.fft.DoubleFFT_2D$44
            r1 = r16
            r2 = r18
            r3 = r13
            r5 = r19
            r6 = r0
            r9 = r20
            r17 = r10
            r10 = r21
            r1.<init>(r3, r5, r6, r7, r9, r10)
            java.util.concurrent.Future r1 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r16)
            r15[r17] = r1
            int r10 = r17 + 1
            goto L_0x002e
        L_0x004d:
            r1 = 0
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r15)     // Catch:{ InterruptedException -> 0x0062, ExecutionException -> 0x0052 }
            goto L_0x0071
        L_0x0052:
            r0 = move-exception
            r2 = r0
            java.lang.String r0 = r12.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r3 = java.util.logging.Level.SEVERE
            r0.log(r3, r1, r2)
            goto L_0x0071
        L_0x0062:
            r0 = move-exception
            r2 = r0
            java.lang.String r0 = r12.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r3 = java.util.logging.Level.SEVERE
            r0.log(r3, r1, r2)
        L_0x0071:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.DoubleFFT_2D.cdft2d_subth(int, pl.edu.icm.jlargearrays.DoubleLargeArray, boolean):void");
    }

    private void cdft2d_subth(int i, double[][] dArr, boolean z) {
        Class<DoubleFFT_2D> cls = DoubleFFT_2D.class;
        int min = FastMath.min(this.columns / 2, ConcurrencyUtils.getNumberOfThreads());
        int i2 = this.rows * 8;
        int i3 = this.columns;
        if (i3 == 4) {
            i2 >>= 1;
        } else if (i3 < 4) {
            i2 >>= 2;
        }
        int i4 = i2;
        Future[] futureArr = new Future[min];
        for (int i5 = 0; i5 < min; i5++) {
            final int i6 = i4;
            final int i7 = i;
            final int i8 = min;
            final int i9 = i5;
            final double[][] dArr2 = dArr;
            final boolean z2 = z;
            futureArr[i5] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    double[] dArr = new double[i6];
                    int i = 0;
                    if (i7 == -1) {
                        if (DoubleFFT_2D.this.columns > i8 * 4) {
                            int i2 = i9 * 8;
                            while (i2 < DoubleFFT_2D.this.columns) {
                                for (int i3 = 0; i3 < DoubleFFT_2D.this.rows; i3++) {
                                    int i4 = i3 * 2;
                                    int access$100 = (DoubleFFT_2D.this.rows * 2) + i4;
                                    int access$1002 = (DoubleFFT_2D.this.rows * 2) + access$100;
                                    int access$1003 = (DoubleFFT_2D.this.rows * 2) + access$1002;
                                    double[] dArr2 = dArr2[i3];
                                    dArr[i4] = dArr2[i2];
                                    dArr[i4 + 1] = dArr2[i2 + 1];
                                    dArr[access$100] = dArr2[i2 + 2];
                                    dArr[access$100 + 1] = dArr2[i2 + 3];
                                    dArr[access$1002] = dArr2[i2 + 4];
                                    dArr[access$1002 + 1] = dArr2[i2 + 5];
                                    dArr[access$1003] = dArr2[i2 + 6];
                                    dArr[access$1003 + 1] = dArr2[i2 + 7];
                                }
                                DoubleFFT_2D.this.fftRows.complexForward(dArr, 0);
                                DoubleFFT_2D.this.fftRows.complexForward(dArr, DoubleFFT_2D.this.rows * 2);
                                DoubleFFT_2D.this.fftRows.complexForward(dArr, DoubleFFT_2D.this.rows * 4);
                                DoubleFFT_2D.this.fftRows.complexForward(dArr, DoubleFFT_2D.this.rows * 6);
                                for (int i5 = 0; i5 < DoubleFFT_2D.this.rows; i5++) {
                                    int i6 = i5 * 2;
                                    int access$1004 = (DoubleFFT_2D.this.rows * 2) + i6;
                                    int access$1005 = (DoubleFFT_2D.this.rows * 2) + access$1004;
                                    int access$1006 = (DoubleFFT_2D.this.rows * 2) + access$1005;
                                    double[] dArr3 = dArr2[i5];
                                    dArr3[i2] = dArr[i6];
                                    dArr3[i2 + 1] = dArr[i6 + 1];
                                    dArr3[i2 + 2] = dArr[access$1004];
                                    dArr3[i2 + 3] = dArr[access$1004 + 1];
                                    dArr3[i2 + 4] = dArr[access$1005];
                                    dArr3[i2 + 5] = dArr[access$1005 + 1];
                                    dArr3[i2 + 6] = dArr[access$1006];
                                    dArr3[i2 + 7] = dArr[access$1006 + 1];
                                }
                                i2 += i8 * 8;
                            }
                        } else if (DoubleFFT_2D.this.columns == i8 * 4) {
                            for (int i7 = 0; i7 < DoubleFFT_2D.this.rows; i7++) {
                                int i8 = i7 * 2;
                                int access$1007 = (DoubleFFT_2D.this.rows * 2) + i8;
                                double[] dArr4 = dArr2[i7];
                                int i9 = i9;
                                dArr[i8] = dArr4[i9 * 4];
                                dArr[i8 + 1] = dArr4[(i9 * 4) + 1];
                                dArr[access$1007] = dArr4[(i9 * 4) + 2];
                                dArr[access$1007 + 1] = dArr4[(i9 * 4) + 3];
                            }
                            DoubleFFT_2D.this.fftRows.complexForward(dArr, 0);
                            DoubleFFT_2D.this.fftRows.complexForward(dArr, DoubleFFT_2D.this.rows * 2);
                            while (i < DoubleFFT_2D.this.rows) {
                                int i10 = i * 2;
                                int access$1008 = (DoubleFFT_2D.this.rows * 2) + i10;
                                double[] dArr5 = dArr2[i];
                                int i11 = i9;
                                dArr5[i11 * 4] = dArr[i10];
                                dArr5[(i11 * 4) + 1] = dArr[i10 + 1];
                                dArr5[(i11 * 4) + 2] = dArr[access$1008];
                                dArr5[(i11 * 4) + 3] = dArr[access$1008 + 1];
                                i++;
                            }
                        } else if (DoubleFFT_2D.this.columns == i8 * 2) {
                            for (int i12 = 0; i12 < DoubleFFT_2D.this.rows; i12++) {
                                int i13 = i12 * 2;
                                double[] dArr6 = dArr2[i12];
                                int i14 = i9;
                                dArr[i13] = dArr6[i14 * 2];
                                dArr[i13 + 1] = dArr6[(i14 * 2) + 1];
                            }
                            DoubleFFT_2D.this.fftRows.complexForward(dArr, 0);
                            while (i < DoubleFFT_2D.this.rows) {
                                int i15 = i * 2;
                                double[] dArr7 = dArr2[i];
                                int i16 = i9;
                                dArr7[i16 * 2] = dArr[i15];
                                dArr7[(i16 * 2) + 1] = dArr[i15 + 1];
                                i++;
                            }
                        }
                    } else if (DoubleFFT_2D.this.columns > i8 * 4) {
                        int i17 = i9 * 8;
                        while (i17 < DoubleFFT_2D.this.columns) {
                            for (int i18 = 0; i18 < DoubleFFT_2D.this.rows; i18++) {
                                int i19 = i18 * 2;
                                int access$1009 = (DoubleFFT_2D.this.rows * 2) + i19;
                                int access$10010 = (DoubleFFT_2D.this.rows * 2) + access$1009;
                                int access$10011 = (DoubleFFT_2D.this.rows * 2) + access$10010;
                                double[] dArr8 = dArr2[i18];
                                dArr[i19] = dArr8[i17];
                                dArr[i19 + 1] = dArr8[i17 + 1];
                                dArr[access$1009] = dArr8[i17 + 2];
                                dArr[access$1009 + 1] = dArr8[i17 + 3];
                                dArr[access$10010] = dArr8[i17 + 4];
                                dArr[access$10010 + 1] = dArr8[i17 + 5];
                                dArr[access$10011] = dArr8[i17 + 6];
                                dArr[access$10011 + 1] = dArr8[i17 + 7];
                            }
                            DoubleFFT_2D.this.fftRows.complexInverse(dArr, 0, z2);
                            DoubleFFT_2D.this.fftRows.complexInverse(dArr, DoubleFFT_2D.this.rows * 2, z2);
                            DoubleFFT_2D.this.fftRows.complexInverse(dArr, DoubleFFT_2D.this.rows * 4, z2);
                            DoubleFFT_2D.this.fftRows.complexInverse(dArr, DoubleFFT_2D.this.rows * 6, z2);
                            for (int i20 = 0; i20 < DoubleFFT_2D.this.rows; i20++) {
                                int i21 = i20 * 2;
                                int access$10012 = (DoubleFFT_2D.this.rows * 2) + i21;
                                int access$10013 = (DoubleFFT_2D.this.rows * 2) + access$10012;
                                int access$10014 = (DoubleFFT_2D.this.rows * 2) + access$10013;
                                double[] dArr9 = dArr2[i20];
                                dArr9[i17] = dArr[i21];
                                dArr9[i17 + 1] = dArr[i21 + 1];
                                dArr9[i17 + 2] = dArr[access$10012];
                                dArr9[i17 + 3] = dArr[access$10012 + 1];
                                dArr9[i17 + 4] = dArr[access$10013];
                                dArr9[i17 + 5] = dArr[access$10013 + 1];
                                dArr9[i17 + 6] = dArr[access$10014];
                                dArr9[i17 + 7] = dArr[access$10014 + 1];
                            }
                            i17 += i8 * 8;
                        }
                    } else if (DoubleFFT_2D.this.columns == i8 * 4) {
                        for (int i22 = 0; i22 < DoubleFFT_2D.this.rows; i22++) {
                            int i23 = i22 * 2;
                            int access$10015 = (DoubleFFT_2D.this.rows * 2) + i23;
                            double[] dArr10 = dArr2[i22];
                            int i24 = i9;
                            dArr[i23] = dArr10[i24 * 4];
                            dArr[i23 + 1] = dArr10[(i24 * 4) + 1];
                            dArr[access$10015] = dArr10[(i24 * 4) + 2];
                            dArr[access$10015 + 1] = dArr10[(i24 * 4) + 3];
                        }
                        DoubleFFT_2D.this.fftRows.complexInverse(dArr, 0, z2);
                        DoubleFFT_2D.this.fftRows.complexInverse(dArr, DoubleFFT_2D.this.rows * 2, z2);
                        while (i < DoubleFFT_2D.this.rows) {
                            int i25 = i * 2;
                            int access$10016 = (DoubleFFT_2D.this.rows * 2) + i25;
                            double[] dArr11 = dArr2[i];
                            int i26 = i9;
                            dArr11[i26 * 4] = dArr[i25];
                            dArr11[(i26 * 4) + 1] = dArr[i25 + 1];
                            dArr11[(i26 * 4) + 2] = dArr[access$10016];
                            dArr11[(i26 * 4) + 3] = dArr[access$10016 + 1];
                            i++;
                        }
                    } else if (DoubleFFT_2D.this.columns == i8 * 2) {
                        for (int i27 = 0; i27 < DoubleFFT_2D.this.rows; i27++) {
                            int i28 = i27 * 2;
                            double[] dArr12 = dArr2[i27];
                            int i29 = i9;
                            dArr[i28] = dArr12[i29 * 2];
                            dArr[i28 + 1] = dArr12[(i29 * 2) + 1];
                        }
                        DoubleFFT_2D.this.fftRows.complexInverse(dArr, 0, z2);
                        while (i < DoubleFFT_2D.this.rows) {
                            int i30 = i * 2;
                            double[] dArr13 = dArr2[i];
                            int i31 = i9;
                            dArr13[i31 * 2] = dArr[i30];
                            dArr13[(i31 * 2) + 1] = dArr[i30 + 1];
                            i++;
                        }
                    }
                }
            });
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
        } catch (ExecutionException e2) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
        }
    }

    private void fillSymmetric(double[] dArr) {
        Class<DoubleFFT_2D> cls = DoubleFFT_2D.class;
        int i = this.columns * 2;
        int i2 = this.rows;
        int i3 = i2 / 2;
        int i4 = i2 - 1;
        while (true) {
            if (i4 < 1) {
                break;
            }
            int i5 = this.columns * i4;
            int i6 = i5 * 2;
            for (int i7 = 0; i7 < this.columns; i7 += 2) {
                int i8 = i6 + i7;
                int i9 = i5 + i7;
                dArr[i8] = dArr[i9];
                dArr[i9] = 0.0d;
                int i10 = i9 + 1;
                dArr[i8 + 1] = dArr[i10];
                dArr[i10] = 0.0d;
            }
            i4--;
        }
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (numberOfThreads <= 1 || !this.useThreads || i3 < numberOfThreads) {
            for (int i11 = 1; i11 < i3; i11++) {
                int i12 = i11 * i;
                int i13 = (this.rows - i11) * i;
                int i14 = this.columns;
                dArr[i12 + i14] = dArr[i13 + 1];
                dArr[i12 + i14 + 1] = -dArr[i13];
            }
            for (int i15 = 1; i15 < i3; i15++) {
                int i16 = i15 * i;
                int i17 = ((this.rows - i15) + 1) * i;
                int i18 = this.columns;
                while (true) {
                    i18 += 2;
                    if (i18 >= i) {
                        break;
                    }
                    int i19 = i16 + i18;
                    int i20 = i17 - i18;
                    dArr[i19] = dArr[i20];
                    dArr[i19 + 1] = -dArr[i20 + 1];
                }
            }
            int i21 = 0;
            while (true) {
                int i22 = this.rows;
                if (i21 > i22 / 2) {
                    break;
                }
                int i23 = i21 * i;
                int i24 = ((i22 - i21) % i22) * i;
                for (int i25 = 0; i25 < i; i25 += 2) {
                    int i26 = i23 + i25;
                    int i27 = ((i - i25) % i) + i24;
                    dArr[i27] = dArr[i26];
                    dArr[i27 + 1] = -dArr[i26 + 1];
                }
                i21++;
            }
        } else {
            Future[] futureArr = new Future[numberOfThreads];
            int i28 = i3 / numberOfThreads;
            int i29 = this.columns * 2;
            int i30 = 0;
            while (i30 < numberOfThreads) {
                final int i31 = i30 == 0 ? (i30 * i28) + 1 : i30 * i28;
                int i32 = i30 * i28;
                final int i33 = i32 + i28;
                int i34 = i30 == numberOfThreads + -1 ? i33 + 1 : i33;
                final int i35 = i29;
                final double[] dArr2 = dArr;
                int i36 = i30;
                final int i37 = i32;
                Future[] futureArr2 = futureArr;
                final int i38 = i34;
                futureArr2[i36] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i31; i < i33; i++) {
                            int access$100 = (DoubleFFT_2D.this.rows - i) * i35;
                            int access$400 = (i35 * i) + DoubleFFT_2D.this.columns;
                            double[] dArr = dArr2;
                            dArr[access$400] = dArr[access$100 + 1];
                            dArr[access$400 + 1] = -dArr[access$100];
                        }
                        for (int i2 = i31; i2 < i33; i2++) {
                            int i3 = i35 * i2;
                            int access$1002 = ((DoubleFFT_2D.this.rows - i2) + 1) * i35;
                            int access$4002 = DoubleFFT_2D.this.columns;
                            while (true) {
                                access$4002 += 2;
                                if (access$4002 >= i35) {
                                    break;
                                }
                                int i4 = access$1002 - access$4002;
                                int i5 = i3 + access$4002;
                                double[] dArr2 = dArr2;
                                dArr2[i5] = dArr2[i4];
                                dArr2[i5 + 1] = -dArr2[i4 + 1];
                            }
                        }
                        for (int i6 = i37; i6 < i38; i6++) {
                            int access$1003 = (DoubleFFT_2D.this.rows - i6) % DoubleFFT_2D.this.rows;
                            int i7 = i35;
                            int i8 = access$1003 * i7;
                            int i9 = i7 * i6;
                            int i10 = 0;
                            while (true) {
                                int i11 = i35;
                                if (i10 >= i11) {
                                    break;
                                }
                                int i12 = ((i11 - i10) % i11) + i8;
                                int i13 = i9 + i10;
                                double[] dArr3 = dArr2;
                                dArr3[i12] = dArr3[i13];
                                dArr3[i12 + 1] = -dArr3[i13 + 1];
                                i10 += 2;
                            }
                        }
                    }
                });
                i30 = i36 + 1;
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
        int i39 = this.columns;
        dArr[i39] = -dArr[1];
        dArr[1] = 0.0d;
        int i40 = i3 * i;
        int i41 = i40 + 1;
        dArr[i40 + i39] = -dArr[i41];
        dArr[i41] = 0.0d;
        dArr[i40 + i39 + 1] = 0.0d;
    }

    private void fillSymmetric(DoubleLargeArray doubleLargeArray) {
        long j;
        long j2;
        DoubleLargeArray doubleLargeArray2 = doubleLargeArray;
        Class<DoubleFFT_2D> cls = DoubleFFT_2D.class;
        long j3 = 2;
        long j4 = this.columnsl * 2;
        long j5 = this.rowsl;
        long j6 = j5 / 2;
        long j7 = 1;
        long j8 = j5 - 1;
        while (j8 >= j7) {
            long j9 = this.columnsl * j8;
            long j10 = j9 * j3;
            long j11 = 0;
            while (j11 < this.columnsl) {
                long j12 = j10 + j11;
                long j13 = j9 + j11;
                doubleLargeArray2.setDouble(j12, doubleLargeArray2.getDouble(j13));
                doubleLargeArray2.setDouble(j13, 0.0d);
                long j14 = j13 + 1;
                doubleLargeArray2.setDouble(j12 + 1, doubleLargeArray2.getDouble(j14));
                doubleLargeArray2.setDouble(j14, 0.0d);
                j11 += 2;
                j9 = j9;
                j7 = 1;
            }
            j8 -= j7;
            j3 = 2;
        }
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (numberOfThreads > 1 && this.useThreads) {
            long j15 = (long) numberOfThreads;
            if (j6 >= j15) {
                Future[] futureArr = new Future[numberOfThreads];
                long j16 = j6 / j15;
                long j17 = this.columnsl * 2;
                int i = 0;
                while (i < numberOfThreads) {
                    if (i == 0) {
                        j = 1;
                        j2 = (((long) i) * j16) + 1;
                    } else {
                        j = 1;
                        j2 = ((long) i) * j16;
                    }
                    final long j18 = j2;
                    long j19 = ((long) i) * j16;
                    final long j20 = j19 + j16;
                    long j21 = i == numberOfThreads + -1 ? j20 + j : j20;
                    final long j22 = j17;
                    final DoubleLargeArray doubleLargeArray3 = doubleLargeArray;
                    final long j23 = j19;
                    Future[] futureArr2 = futureArr;
                    int i2 = i;
                    final long j24 = j21;
                    futureArr2[i2] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            long j;
                            long j2 = j18;
                            while (true) {
                                j = 1;
                                if (j2 >= j20) {
                                    break;
                                }
                                long access$300 = (DoubleFFT_2D.this.rowsl - j2) * j22;
                                long access$500 = (j22 * j2) + DoubleFFT_2D.this.columnsl;
                                DoubleLargeArray doubleLargeArray = doubleLargeArray3;
                                doubleLargeArray.setDouble(access$500, doubleLargeArray.getDouble(access$300 + 1));
                                DoubleLargeArray doubleLargeArray2 = doubleLargeArray3;
                                doubleLargeArray2.setDouble(access$500 + 1, -doubleLargeArray2.getDouble(access$300));
                                j2++;
                            }
                            long j3 = j18;
                            while (true) {
                                long j4 = 2;
                                if (j3 >= j20) {
                                    break;
                                }
                                long j5 = j22 * j3;
                                long access$3002 = ((DoubleFFT_2D.this.rowsl - j3) + j) * j22;
                                long access$5002 = DoubleFFT_2D.this.columnsl;
                                while (true) {
                                    access$5002 += j4;
                                    if (access$5002 >= j22) {
                                        break;
                                    }
                                    long j6 = access$3002 - access$5002;
                                    long j7 = j5 + access$5002;
                                    DoubleLargeArray doubleLargeArray3 = doubleLargeArray3;
                                    doubleLargeArray3.setDouble(j7, doubleLargeArray3.getDouble(j6));
                                    DoubleLargeArray doubleLargeArray4 = doubleLargeArray3;
                                    j = 1;
                                    doubleLargeArray4.setDouble(j7 + 1, -doubleLargeArray4.getDouble(j6 + 1));
                                    j4 = 2;
                                }
                                j3 += j;
                            }
                            for (long j8 = j23; j8 < j24; j8++) {
                                long access$3003 = (DoubleFFT_2D.this.rowsl - j8) % DoubleFFT_2D.this.rowsl;
                                long j9 = j22;
                                long j10 = access$3003 * j9;
                                long j11 = j9 * j8;
                                long j12 = 0;
                                while (true) {
                                    long j13 = j22;
                                    if (j12 >= j13) {
                                        break;
                                    }
                                    long j14 = ((j13 - j12) % j13) + j10;
                                    long j15 = j11 + j12;
                                    DoubleLargeArray doubleLargeArray5 = doubleLargeArray3;
                                    doubleLargeArray5.setDouble(j14, doubleLargeArray5.getDouble(j15));
                                    DoubleLargeArray doubleLargeArray6 = doubleLargeArray3;
                                    doubleLargeArray6.setDouble(j14 + 1, -doubleLargeArray6.getDouble(j15 + 1));
                                    j12 += 2;
                                }
                            }
                        }
                    });
                    i = i2 + 1;
                    futureArr = futureArr2;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr);
                } catch (InterruptedException e) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
                } catch (ExecutionException e2) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
                }
                doubleLargeArray2.setDouble(this.columnsl, -doubleLargeArray2.getDouble(1));
                doubleLargeArray2.setDouble(1, 0.0d);
                long j25 = j6 * j4;
                long j26 = j25 + 1;
                doubleLargeArray2.setDouble(j25 + this.columnsl, -doubleLargeArray2.getDouble(j26));
                doubleLargeArray2.setDouble(j26, 0.0d);
                doubleLargeArray2.setDouble(j25 + this.columnsl + 1, 0.0d);
            }
        }
        for (long j27 = 1; j27 < j6; j27++) {
            long j28 = j27 * j4;
            long j29 = (this.rowsl - j27) * j4;
            doubleLargeArray2.setDouble(this.columnsl + j28, doubleLargeArray2.getDouble(j29 + 1));
            doubleLargeArray2.setDouble(j28 + this.columnsl + 1, -doubleLargeArray2.getDouble(j29));
        }
        long j30 = 1;
        for (long j31 = 1; j31 < j6; j31 += j30) {
            long j32 = j31 * j4;
            long j33 = ((this.rowsl - j31) + j30) * j4;
            long j34 = this.columnsl + 2;
            while (j34 < j4) {
                long j35 = j32 + j34;
                long j36 = j33 - j34;
                doubleLargeArray2.setDouble(j35, doubleLargeArray2.getDouble(j36));
                doubleLargeArray2.setDouble(j35 + 1, -doubleLargeArray2.getDouble(j36 + 1));
                j34 += 2;
                j32 = j32;
                j30 = 1;
            }
        }
        long j37 = 2;
        long j38 = 0;
        while (true) {
            long j39 = this.rowsl;
            if (j38 > j39 / j37) {
                break;
            }
            long j40 = j38 * j4;
            long j41 = ((j39 - j38) % j39) * j4;
            for (long j42 = 0; j42 < j4; j42 += 2) {
                long j43 = j40 + j42;
                long j44 = ((j4 - j42) % j4) + j41;
                doubleLargeArray2.setDouble(j44, doubleLargeArray2.getDouble(j43));
                doubleLargeArray2.setDouble(j44 + 1, -doubleLargeArray2.getDouble(j43 + 1));
            }
            j38++;
            j37 = 2;
        }
        doubleLargeArray2.setDouble(this.columnsl, -doubleLargeArray2.getDouble(1));
        doubleLargeArray2.setDouble(1, 0.0d);
        long j252 = j6 * j4;
        long j262 = j252 + 1;
        doubleLargeArray2.setDouble(j252 + this.columnsl, -doubleLargeArray2.getDouble(j262));
        doubleLargeArray2.setDouble(j262, 0.0d);
        doubleLargeArray2.setDouble(j252 + this.columnsl + 1, 0.0d);
    }

    private void fillSymmetric(double[][] dArr) {
        char c2;
        Class<DoubleFFT_2D> cls = DoubleFFT_2D.class;
        int i = this.columns * 2;
        int i2 = this.rows / 2;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = 1;
        if (numberOfThreads <= 1 || !this.useThreads || i2 < numberOfThreads) {
            for (int i4 = 1; i4 < i2; i4++) {
                double[] dArr2 = dArr[i4];
                int i5 = this.columns;
                double[] dArr3 = dArr[this.rows - i4];
                dArr2[i5] = dArr3[1];
                dArr2[i5 + 1] = -dArr3[0];
            }
            int i6 = 1;
            while (i6 < i2) {
                int i7 = this.rows - i6;
                int i8 = this.columns + 2;
                while (i8 < i) {
                    int i9 = i - i8;
                    double[] dArr4 = dArr[i6];
                    double[] dArr5 = dArr[i7];
                    dArr4[i8] = dArr5[i9];
                    dArr4[i8 + 1] = -dArr5[i9 + i3];
                    i8 += 2;
                    i3 = 1;
                }
                i6++;
                i3 = 1;
            }
            int i10 = 0;
            while (true) {
                int i11 = this.rows;
                if (i10 > i11 / 2) {
                    break;
                }
                int i12 = (i11 - i10) % i11;
                for (int i13 = 0; i13 < i; i13 += 2) {
                    int i14 = (i - i13) % i;
                    double[] dArr6 = dArr[i12];
                    double[] dArr7 = dArr[i10];
                    dArr6[i14] = dArr7[i13];
                    dArr6[i14 + 1] = -dArr7[i13 + 1];
                }
                i10++;
            }
            c2 = 0;
        } else {
            Future[] futureArr = new Future[numberOfThreads];
            int i15 = i2 / numberOfThreads;
            int i16 = 0;
            while (i16 < numberOfThreads) {
                final int i17 = i16 == 0 ? (i16 * i15) + 1 : i16 * i15;
                final int i18 = i16 * i15;
                final int i19 = i18 + i15;
                final double[][] dArr8 = dArr;
                final int i20 = i;
                int i21 = i16;
                final int i22 = i16 == numberOfThreads + -1 ? i19 + 1 : i19;
                futureArr[i21] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i17; i < i19; i++) {
                            int access$100 = DoubleFFT_2D.this.rows - i;
                            double[] dArr = dArr8[i];
                            int access$400 = DoubleFFT_2D.this.columns;
                            double[][] dArr2 = dArr8;
                            dArr[access$400] = dArr2[access$100][1];
                            dArr2[i][DoubleFFT_2D.this.columns + 1] = -dArr8[access$100][0];
                        }
                        for (int i2 = i17; i2 < i19; i2++) {
                            int access$1002 = DoubleFFT_2D.this.rows - i2;
                            int access$4002 = DoubleFFT_2D.this.columns;
                            while (true) {
                                access$4002 += 2;
                                int i3 = i20;
                                if (access$4002 >= i3) {
                                    break;
                                }
                                int i4 = i3 - access$4002;
                                double[][] dArr3 = dArr8;
                                double[] dArr4 = dArr3[i2];
                                double[] dArr5 = dArr3[access$1002];
                                dArr4[access$4002] = dArr5[i4];
                                dArr4[access$4002 + 1] = -dArr5[i4 + 1];
                            }
                        }
                        for (int i5 = i18; i5 < i22; i5++) {
                            int access$1003 = (DoubleFFT_2D.this.rows - i5) % DoubleFFT_2D.this.rows;
                            int i6 = 0;
                            while (true) {
                                int i7 = i20;
                                if (i6 >= i7) {
                                    break;
                                }
                                int i8 = (i7 - i6) % i7;
                                double[][] dArr6 = dArr8;
                                double[] dArr7 = dArr6[access$1003];
                                double[] dArr8 = dArr6[i5];
                                dArr7[i8] = dArr8[i6];
                                dArr7[i8 + 1] = -dArr8[i6 + 1];
                                i6 += 2;
                            }
                        }
                    }
                });
                i16 = i21 + 1;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
            } catch (ExecutionException e2) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
            }
            c2 = 0;
        }
        double[] dArr9 = dArr[c2];
        int i23 = this.columns;
        dArr9[i23] = -dArr9[1];
        dArr9[1] = 0.0d;
        double[] dArr10 = dArr[i2];
        dArr10[i23] = -dArr10[1];
        dArr10[1] = 0.0d;
        dArr10[i23 + 1] = 0.0d;
    }
}
