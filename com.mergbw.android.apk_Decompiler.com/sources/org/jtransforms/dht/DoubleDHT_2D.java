package org.jtransforms.dht;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.math3.util.FastMath;
import org.jtransforms.utils.CommonUtils;
import pl.edu.icm.jlargearrays.ConcurrencyUtils;
import pl.edu.icm.jlargearrays.DoubleLargeArray;
import pl.edu.icm.jlargearrays.LargeArray;

public class DoubleDHT_2D {
    /* access modifiers changed from: private */
    public int columns;
    /* access modifiers changed from: private */
    public long columnsl;
    /* access modifiers changed from: private */
    public DoubleDHT_1D dhtColumns;
    /* access modifiers changed from: private */
    public DoubleDHT_1D dhtRows;
    private boolean isPowerOfTwo = false;
    /* access modifiers changed from: private */
    public int rows;
    /* access modifiers changed from: private */
    public long rowsl;
    private boolean useThreads = false;

    public DoubleDHT_2D(long j, long j2) {
        boolean z = false;
        if (j <= 1 || j2 <= 1) {
            throw new IllegalArgumentException("rows and columns must be greater than 1");
        }
        this.rows = (int) j;
        this.columns = (int) j2;
        this.rowsl = j;
        this.columnsl = j2;
        long j3 = j * j2;
        if (j3 >= CommonUtils.getThreadsBeginN_2D()) {
            this.useThreads = true;
        }
        if (CommonUtils.isPowerOf2(j) && CommonUtils.isPowerOf2(j2)) {
            this.isPowerOfTwo = true;
        }
        CommonUtils.setUseLargeArrays(j3 > ((long) LargeArray.getMaxSizeOf32bitArray()) ? true : z);
        DoubleDHT_1D doubleDHT_1D = new DoubleDHT_1D(j);
        this.dhtRows = doubleDHT_1D;
        if (j == j2) {
            this.dhtColumns = doubleDHT_1D;
        } else {
            this.dhtColumns = new DoubleDHT_1D(j2);
        }
    }

    public void forward(final double[] dArr) {
        int i;
        int i2;
        Class<DoubleDHT_2D> cls = DoubleDHT_2D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = 0;
        if (this.isPowerOfTwo) {
            if (numberOfThreads <= 1 || !this.useThreads) {
                ddxt2d_sub(-1, dArr, true);
                while (i3 < this.rows) {
                    this.dhtColumns.forward(dArr, this.columns * i3);
                    i3++;
                }
            } else {
                ddxt2d_subth(-1, dArr, true);
                ddxt2d0_subth(-1, dArr, true);
            }
            yTransform(dArr);
            return;
        }
        if (numberOfThreads <= 1 || !this.useThreads || (i2 = this.rows) < numberOfThreads || this.columns < numberOfThreads) {
            int i4 = 0;
            while (true) {
                i = this.rows;
                if (i4 >= i) {
                    break;
                }
                this.dhtColumns.forward(dArr, this.columns * i4);
                i4++;
            }
            double[] dArr2 = new double[i];
            for (int i5 = 0; i5 < this.columns; i5++) {
                for (int i6 = 0; i6 < this.rows; i6++) {
                    dArr2[i6] = dArr[(this.columns * i6) + i5];
                }
                this.dhtRows.forward(dArr2);
                for (int i7 = 0; i7 < this.rows; i7++) {
                    dArr[(this.columns * i7) + i5] = dArr2[i7];
                }
            }
        } else {
            Future[] futureArr = new Future[numberOfThreads];
            int i8 = i2 / numberOfThreads;
            int i9 = 0;
            while (i9 < numberOfThreads) {
                final int i10 = i9 * i8;
                final int i11 = i9 == numberOfThreads + -1 ? this.rows : i10 + i8;
                futureArr[i9] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i10; i < i11; i++) {
                            DoubleDHT_2D.this.dhtColumns.forward(dArr, DoubleDHT_2D.this.columns * i);
                        }
                    }
                });
                i9++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
            } catch (ExecutionException e2) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
            }
            int i12 = this.columns / numberOfThreads;
            while (i3 < numberOfThreads) {
                final int i13 = i3 * i12;
                final int i14 = i3 == numberOfThreads + -1 ? this.columns : i13 + i12;
                futureArr[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        double[] dArr = new double[DoubleDHT_2D.this.rows];
                        for (int i = i13; i < i14; i++) {
                            for (int i2 = 0; i2 < DoubleDHT_2D.this.rows; i2++) {
                                dArr[i2] = dArr[(DoubleDHT_2D.this.columns * i2) + i];
                            }
                            DoubleDHT_2D.this.dhtRows.forward(dArr);
                            for (int i3 = 0; i3 < DoubleDHT_2D.this.rows; i3++) {
                                dArr[(DoubleDHT_2D.this.columns * i3) + i] = dArr[i3];
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
        yTransform(dArr);
    }

    public void forward(DoubleLargeArray doubleLargeArray) {
        DoubleLargeArray doubleLargeArray2 = doubleLargeArray;
        Class<DoubleDHT_2D> cls = DoubleDHT_2D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (this.isPowerOfTwo) {
            if (numberOfThreads <= 1 || !this.useThreads) {
                ddxt2d_sub(-1, doubleLargeArray2, true);
                for (long j = 0; j < this.rowsl; j++) {
                    this.dhtColumns.forward(doubleLargeArray2, this.columnsl * j);
                }
            } else {
                ddxt2d_subth(-1, doubleLargeArray2, true);
                ddxt2d0_subth(-1, doubleLargeArray2, true);
            }
            yTransform(doubleLargeArray);
            return;
        }
        int i = 0;
        if (numberOfThreads > 1 && this.useThreads) {
            long j2 = this.rowsl;
            long j3 = (long) numberOfThreads;
            if (j2 >= j3 && this.columnsl >= j3) {
                Future[] futureArr = new Future[numberOfThreads];
                long j4 = j2 / j3;
                int i2 = 0;
                while (i2 < numberOfThreads) {
                    final long j5 = ((long) i2) * j4;
                    final long j6 = i2 == numberOfThreads + -1 ? this.rowsl : j5 + j4;
                    final DoubleLargeArray doubleLargeArray3 = doubleLargeArray;
                    futureArr[i2] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            for (long j = j5; j < j6; j++) {
                                DoubleDHT_2D.this.dhtColumns.forward(doubleLargeArray3, DoubleDHT_2D.this.columnsl * j);
                            }
                        }
                    });
                    i2++;
                }
                String str = null;
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr);
                } catch (InterruptedException e) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
                } catch (ExecutionException e2) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
                }
                long j7 = this.columnsl / j3;
                while (i < numberOfThreads) {
                    final long j8 = ((long) i) * j7;
                    final long j9 = i == numberOfThreads + -1 ? this.columnsl : j8 + j7;
                    int i3 = numberOfThreads;
                    String str2 = str;
                    final DoubleLargeArray doubleLargeArray4 = doubleLargeArray;
                    futureArr[i] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            DoubleLargeArray doubleLargeArray = new DoubleLargeArray(DoubleDHT_2D.this.rowsl, false);
                            for (long j = j8; j < j9; j++) {
                                for (long j2 = 0; j2 < DoubleDHT_2D.this.rowsl; j2++) {
                                    doubleLargeArray.setDouble(j2, doubleLargeArray4.getDouble((DoubleDHT_2D.this.columnsl * j2) + j));
                                }
                                DoubleDHT_2D.this.dhtRows.forward(doubleLargeArray);
                                for (long j3 = 0; j3 < DoubleDHT_2D.this.rowsl; j3++) {
                                    doubleLargeArray4.setDouble((DoubleDHT_2D.this.columnsl * j3) + j, doubleLargeArray.getDouble(j3));
                                }
                            }
                        }
                    });
                    i++;
                    str = str2;
                    numberOfThreads = i3;
                }
                String str3 = str;
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr);
                } catch (InterruptedException e3) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e3);
                } catch (ExecutionException e4) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e4);
                }
                yTransform(doubleLargeArray);
            }
        }
        for (long j10 = 0; j10 < this.rowsl; j10++) {
            this.dhtColumns.forward(doubleLargeArray2, this.columnsl * j10);
        }
        DoubleLargeArray doubleLargeArray5 = new DoubleLargeArray(this.rowsl, false);
        for (long j11 = 0; j11 < this.columnsl; j11++) {
            for (long j12 = 0; j12 < this.rowsl; j12++) {
                doubleLargeArray5.setDouble(j12, doubleLargeArray2.getDouble((this.columnsl * j12) + j11));
            }
            this.dhtRows.forward(doubleLargeArray5);
            for (long j13 = 0; j13 < this.rowsl; j13++) {
                doubleLargeArray2.setDouble((this.columnsl * j13) + j11, doubleLargeArray5.getDouble(j13));
            }
        }
        yTransform(doubleLargeArray);
    }

    public void forward(final double[][] dArr) {
        int i;
        int i2;
        Class<DoubleDHT_2D> cls = DoubleDHT_2D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = 0;
        if (this.isPowerOfTwo) {
            if (numberOfThreads <= 1 || !this.useThreads) {
                ddxt2d_sub(-1, dArr, true);
                while (i3 < this.rows) {
                    this.dhtColumns.forward(dArr[i3]);
                    i3++;
                }
            } else {
                ddxt2d_subth(-1, dArr, true);
                ddxt2d0_subth(-1, dArr, true);
            }
            yTransform(dArr);
            return;
        }
        if (numberOfThreads <= 1 || !this.useThreads || (i2 = this.rows) < numberOfThreads || this.columns < numberOfThreads) {
            int i4 = 0;
            while (true) {
                i = this.rows;
                if (i4 >= i) {
                    break;
                }
                this.dhtColumns.forward(dArr[i4]);
                i4++;
            }
            double[] dArr2 = new double[i];
            for (int i5 = 0; i5 < this.columns; i5++) {
                for (int i6 = 0; i6 < this.rows; i6++) {
                    dArr2[i6] = dArr[i6][i5];
                }
                this.dhtRows.forward(dArr2);
                for (int i7 = 0; i7 < this.rows; i7++) {
                    dArr[i7][i5] = dArr2[i7];
                }
            }
        } else {
            Future[] futureArr = new Future[numberOfThreads];
            int i8 = i2 / numberOfThreads;
            int i9 = 0;
            while (i9 < numberOfThreads) {
                final int i10 = i9 * i8;
                final int i11 = i9 == numberOfThreads + -1 ? this.rows : i10 + i8;
                futureArr[i9] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i10; i < i11; i++) {
                            DoubleDHT_2D.this.dhtColumns.forward(dArr[i]);
                        }
                    }
                });
                i9++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
            } catch (ExecutionException e2) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
            }
            int i12 = this.columns / numberOfThreads;
            while (i3 < numberOfThreads) {
                final int i13 = i3 * i12;
                final int i14 = i3 == numberOfThreads + -1 ? this.columns : i13 + i12;
                futureArr[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        double[] dArr = new double[DoubleDHT_2D.this.rows];
                        for (int i = i13; i < i14; i++) {
                            for (int i2 = 0; i2 < DoubleDHT_2D.this.rows; i2++) {
                                dArr[i2] = dArr[i2][i];
                            }
                            DoubleDHT_2D.this.dhtRows.forward(dArr);
                            for (int i3 = 0; i3 < DoubleDHT_2D.this.rows; i3++) {
                                dArr[i3][i] = dArr[i3];
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
        yTransform(dArr);
    }

    public void inverse(double[] dArr, boolean z) {
        int i;
        int i2;
        Class<DoubleDHT_2D> cls = DoubleDHT_2D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = 0;
        if (this.isPowerOfTwo) {
            if (numberOfThreads <= 1 || !this.useThreads) {
                ddxt2d_sub(1, dArr, z);
                while (i3 < this.rows) {
                    this.dhtColumns.inverse(dArr, this.columns * i3, z);
                    i3++;
                }
            } else {
                ddxt2d_subth(1, dArr, z);
                ddxt2d0_subth(1, dArr, z);
            }
            yTransform(dArr);
            return;
        }
        if (numberOfThreads <= 1 || !this.useThreads || (i2 = this.rows) < numberOfThreads || this.columns < numberOfThreads) {
            int i4 = 0;
            while (true) {
                i = this.rows;
                if (i4 >= i) {
                    break;
                }
                this.dhtColumns.inverse(dArr, this.columns * i4, z);
                i4++;
            }
            double[] dArr2 = new double[i];
            for (int i5 = 0; i5 < this.columns; i5++) {
                for (int i6 = 0; i6 < this.rows; i6++) {
                    dArr2[i6] = dArr[(this.columns * i6) + i5];
                }
                this.dhtRows.inverse(dArr2, z);
                for (int i7 = 0; i7 < this.rows; i7++) {
                    dArr[(this.columns * i7) + i5] = dArr2[i7];
                }
            }
        } else {
            Future[] futureArr = new Future[numberOfThreads];
            int i8 = i2 / numberOfThreads;
            int i9 = 0;
            while (i9 < numberOfThreads) {
                final int i10 = i9 * i8;
                final int i11 = i9 == numberOfThreads + -1 ? this.rows : i10 + i8;
                final double[] dArr3 = dArr;
                final boolean z2 = z;
                futureArr[i9] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i10; i < i11; i++) {
                            DoubleDHT_2D.this.dhtColumns.inverse(dArr3, DoubleDHT_2D.this.columns * i, z2);
                        }
                    }
                });
                i9++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
            } catch (ExecutionException e2) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
            }
            int i12 = this.columns / numberOfThreads;
            while (i3 < numberOfThreads) {
                final int i13 = i3 * i12;
                final int i14 = i3 == numberOfThreads + -1 ? this.columns : i13 + i12;
                final double[] dArr4 = dArr;
                final boolean z3 = z;
                futureArr[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        double[] dArr = new double[DoubleDHT_2D.this.rows];
                        for (int i = i13; i < i14; i++) {
                            for (int i2 = 0; i2 < DoubleDHT_2D.this.rows; i2++) {
                                dArr[i2] = dArr4[(DoubleDHT_2D.this.columns * i2) + i];
                            }
                            DoubleDHT_2D.this.dhtRows.inverse(dArr, z3);
                            for (int i3 = 0; i3 < DoubleDHT_2D.this.rows; i3++) {
                                dArr4[(DoubleDHT_2D.this.columns * i3) + i] = dArr[i3];
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
        yTransform(dArr);
    }

    public void inverse(DoubleLargeArray doubleLargeArray, boolean z) {
        DoubleLargeArray doubleLargeArray2 = doubleLargeArray;
        boolean z2 = z;
        Class<DoubleDHT_2D> cls = DoubleDHT_2D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (this.isPowerOfTwo) {
            if (numberOfThreads <= 1 || !this.useThreads) {
                ddxt2d_sub(1, doubleLargeArray2, z2);
                for (long j = 0; j < this.rowsl; j++) {
                    this.dhtColumns.inverse(doubleLargeArray2, this.columnsl * j, z2);
                }
            } else {
                ddxt2d_subth(1, doubleLargeArray2, z2);
                ddxt2d0_subth(1, doubleLargeArray2, z2);
            }
            yTransform(doubleLargeArray);
            return;
        }
        int i = 0;
        if (numberOfThreads > 1 && this.useThreads) {
            long j2 = this.rowsl;
            long j3 = (long) numberOfThreads;
            if (j2 >= j3 && this.columnsl >= j3) {
                Future[] futureArr = new Future[numberOfThreads];
                long j4 = j2 / j3;
                int i2 = 0;
                while (i2 < numberOfThreads) {
                    final long j5 = ((long) i2) * j4;
                    final long j6 = i2 == numberOfThreads + -1 ? this.rowsl : j5 + j4;
                    long j7 = j3;
                    final DoubleLargeArray doubleLargeArray3 = doubleLargeArray;
                    final boolean z3 = z;
                    futureArr[i2] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            for (long j = j5; j < j6; j++) {
                                DoubleDHT_2D.this.dhtColumns.inverse(doubleLargeArray3, DoubleDHT_2D.this.columnsl * j, z3);
                            }
                        }
                    });
                    i2++;
                    j3 = j7;
                }
                long j8 = j3;
                String str = null;
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr);
                } catch (InterruptedException e) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
                } catch (ExecutionException e2) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
                }
                long j9 = this.columnsl / j8;
                while (i < numberOfThreads) {
                    final long j10 = ((long) i) * j9;
                    final long j11 = i == numberOfThreads + -1 ? this.columnsl : j10 + j9;
                    final DoubleLargeArray doubleLargeArray4 = doubleLargeArray;
                    int i3 = numberOfThreads;
                    String str2 = str;
                    final boolean z4 = z;
                    futureArr[i] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            DoubleLargeArray doubleLargeArray = new DoubleLargeArray(DoubleDHT_2D.this.rowsl, false);
                            for (long j = j10; j < j11; j++) {
                                for (long j2 = 0; j2 < DoubleDHT_2D.this.rowsl; j2++) {
                                    doubleLargeArray.setDouble(j2, doubleLargeArray4.getDouble((DoubleDHT_2D.this.columnsl * j2) + j));
                                }
                                DoubleDHT_2D.this.dhtRows.inverse(doubleLargeArray, z4);
                                for (long j3 = 0; j3 < DoubleDHT_2D.this.rowsl; j3++) {
                                    doubleLargeArray4.setDouble((DoubleDHT_2D.this.columnsl * j3) + j, doubleLargeArray.getDouble(j3));
                                }
                            }
                        }
                    });
                    i++;
                    str = str2;
                    numberOfThreads = i3;
                }
                String str3 = str;
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr);
                } catch (InterruptedException e3) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e3);
                } catch (ExecutionException e4) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e4);
                }
                yTransform(doubleLargeArray);
            }
        }
        for (long j12 = 0; j12 < this.rowsl; j12++) {
            this.dhtColumns.inverse(doubleLargeArray2, this.columnsl * j12, z2);
        }
        DoubleLargeArray doubleLargeArray5 = new DoubleLargeArray(this.rowsl, false);
        for (long j13 = 0; j13 < this.columnsl; j13++) {
            for (long j14 = 0; j14 < this.rowsl; j14++) {
                doubleLargeArray5.setDouble(j14, doubleLargeArray2.getDouble((this.columnsl * j14) + j13));
            }
            this.dhtRows.inverse(doubleLargeArray5, z2);
            for (long j15 = 0; j15 < this.rowsl; j15++) {
                doubleLargeArray2.setDouble((this.columnsl * j15) + j13, doubleLargeArray5.getDouble(j15));
            }
        }
        yTransform(doubleLargeArray);
    }

    public void inverse(double[][] dArr, boolean z) {
        int i;
        int i2;
        Class<DoubleDHT_2D> cls = DoubleDHT_2D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = 0;
        if (this.isPowerOfTwo) {
            if (numberOfThreads <= 1 || !this.useThreads) {
                ddxt2d_sub(1, dArr, z);
                while (i3 < this.rows) {
                    this.dhtColumns.inverse(dArr[i3], z);
                    i3++;
                }
            } else {
                ddxt2d_subth(1, dArr, z);
                ddxt2d0_subth(1, dArr, z);
            }
            yTransform(dArr);
            return;
        }
        if (numberOfThreads <= 1 || !this.useThreads || (i2 = this.rows) < numberOfThreads || this.columns < numberOfThreads) {
            int i4 = 0;
            while (true) {
                i = this.rows;
                if (i4 >= i) {
                    break;
                }
                this.dhtColumns.inverse(dArr[i4], z);
                i4++;
            }
            double[] dArr2 = new double[i];
            for (int i5 = 0; i5 < this.columns; i5++) {
                for (int i6 = 0; i6 < this.rows; i6++) {
                    dArr2[i6] = dArr[i6][i5];
                }
                this.dhtRows.inverse(dArr2, z);
                for (int i7 = 0; i7 < this.rows; i7++) {
                    dArr[i7][i5] = dArr2[i7];
                }
            }
        } else {
            Future[] futureArr = new Future[numberOfThreads];
            int i8 = i2 / numberOfThreads;
            int i9 = 0;
            while (i9 < numberOfThreads) {
                final int i10 = i9 * i8;
                final int i11 = i9 == numberOfThreads + -1 ? this.rows : i10 + i8;
                final double[][] dArr3 = dArr;
                final boolean z2 = z;
                futureArr[i9] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i10; i < i11; i++) {
                            DoubleDHT_2D.this.dhtColumns.inverse(dArr3[i], z2);
                        }
                    }
                });
                i9++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
            } catch (ExecutionException e2) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
            }
            int i12 = this.columns / numberOfThreads;
            while (i3 < numberOfThreads) {
                final int i13 = i3 * i12;
                final int i14 = i3 == numberOfThreads + -1 ? this.columns : i13 + i12;
                final double[][] dArr4 = dArr;
                final boolean z3 = z;
                futureArr[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        double[] dArr = new double[DoubleDHT_2D.this.rows];
                        for (int i = i13; i < i14; i++) {
                            for (int i2 = 0; i2 < DoubleDHT_2D.this.rows; i2++) {
                                dArr[i2] = dArr4[i2][i];
                            }
                            DoubleDHT_2D.this.dhtRows.inverse(dArr, z3);
                            for (int i3 = 0; i3 < DoubleDHT_2D.this.rows; i3++) {
                                dArr4[i3][i] = dArr[i3];
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
        yTransform(dArr);
    }

    private void ddxt2d_subth(int i, double[] dArr, boolean z) {
        Class<DoubleDHT_2D> cls = DoubleDHT_2D.class;
        int min = FastMath.min(this.columns, ConcurrencyUtils.getNumberOfThreads());
        int i2 = this.rows * 4;
        int i3 = this.columns;
        if (i3 == 2) {
            i2 >>= 1;
        } else if (i3 < 2) {
            i2 >>= 2;
        }
        int i4 = i2;
        Future[] futureArr = new Future[min];
        for (int i5 = 0; i5 < min; i5++) {
            final int i6 = i4;
            final int i7 = i;
            final int i8 = i5;
            final int i9 = min;
            final double[] dArr2 = dArr;
            final boolean z2 = z;
            futureArr[i5] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    double[] dArr = new double[i6];
                    if (DoubleDHT_2D.this.columns > 2) {
                        if (i7 == -1) {
                            int i = i8 * 4;
                            while (i < DoubleDHT_2D.this.columns) {
                                for (int i2 = 0; i2 < DoubleDHT_2D.this.rows; i2++) {
                                    int access$000 = (DoubleDHT_2D.this.columns * i2) + i;
                                    int access$200 = DoubleDHT_2D.this.rows + i2;
                                    double[] dArr2 = dArr2;
                                    dArr[i2] = dArr2[access$000];
                                    dArr[access$200] = dArr2[access$000 + 1];
                                    dArr[DoubleDHT_2D.this.rows + access$200] = dArr2[access$000 + 2];
                                    dArr[access$200 + (DoubleDHT_2D.this.rows * 2)] = dArr2[access$000 + 3];
                                }
                                DoubleDHT_2D.this.dhtRows.forward(dArr, 0);
                                DoubleDHT_2D.this.dhtRows.forward(dArr, DoubleDHT_2D.this.rows);
                                DoubleDHT_2D.this.dhtRows.forward(dArr, DoubleDHT_2D.this.rows * 2);
                                DoubleDHT_2D.this.dhtRows.forward(dArr, DoubleDHT_2D.this.rows * 3);
                                for (int i3 = 0; i3 < DoubleDHT_2D.this.rows; i3++) {
                                    int access$0002 = (DoubleDHT_2D.this.columns * i3) + i;
                                    int access$2002 = DoubleDHT_2D.this.rows + i3;
                                    double[] dArr3 = dArr2;
                                    dArr3[access$0002] = dArr[i3];
                                    dArr3[access$0002 + 1] = dArr[access$2002];
                                    dArr3[access$0002 + 2] = dArr[DoubleDHT_2D.this.rows + access$2002];
                                    dArr2[access$0002 + 3] = dArr[access$2002 + (DoubleDHT_2D.this.rows * 2)];
                                }
                                i += i9 * 4;
                            }
                            return;
                        }
                        int i4 = i8 * 4;
                        while (i4 < DoubleDHT_2D.this.columns) {
                            for (int i5 = 0; i5 < DoubleDHT_2D.this.rows; i5++) {
                                int access$0003 = (DoubleDHT_2D.this.columns * i5) + i4;
                                int access$2003 = DoubleDHT_2D.this.rows + i5;
                                double[] dArr4 = dArr2;
                                dArr[i5] = dArr4[access$0003];
                                dArr[access$2003] = dArr4[access$0003 + 1];
                                dArr[DoubleDHT_2D.this.rows + access$2003] = dArr2[access$0003 + 2];
                                dArr[access$2003 + (DoubleDHT_2D.this.rows * 2)] = dArr2[access$0003 + 3];
                            }
                            DoubleDHT_2D.this.dhtRows.inverse(dArr, 0, z2);
                            DoubleDHT_2D.this.dhtRows.inverse(dArr, DoubleDHT_2D.this.rows, z2);
                            DoubleDHT_2D.this.dhtRows.inverse(dArr, DoubleDHT_2D.this.rows * 2, z2);
                            DoubleDHT_2D.this.dhtRows.inverse(dArr, DoubleDHT_2D.this.rows * 3, z2);
                            for (int i6 = 0; i6 < DoubleDHT_2D.this.rows; i6++) {
                                int access$0004 = (DoubleDHT_2D.this.columns * i6) + i4;
                                int access$2004 = DoubleDHT_2D.this.rows + i6;
                                double[] dArr5 = dArr2;
                                dArr5[access$0004] = dArr[i6];
                                dArr5[access$0004 + 1] = dArr[access$2004];
                                dArr5[access$0004 + 2] = dArr[DoubleDHT_2D.this.rows + access$2004];
                                dArr2[access$0004 + 3] = dArr[access$2004 + (DoubleDHT_2D.this.rows * 2)];
                            }
                            i4 += i9 * 4;
                        }
                    } else if (DoubleDHT_2D.this.columns == 2) {
                        for (int i7 = 0; i7 < DoubleDHT_2D.this.rows; i7++) {
                            int access$0005 = (DoubleDHT_2D.this.columns * i7) + (i8 * 2);
                            dArr[i7] = dArr2[access$0005];
                            dArr[DoubleDHT_2D.this.rows + i7] = dArr2[access$0005 + 1];
                        }
                        if (i7 == -1) {
                            DoubleDHT_2D.this.dhtRows.forward(dArr, 0);
                            DoubleDHT_2D.this.dhtRows.forward(dArr, DoubleDHT_2D.this.rows);
                        } else {
                            DoubleDHT_2D.this.dhtRows.inverse(dArr, 0, z2);
                            DoubleDHT_2D.this.dhtRows.inverse(dArr, DoubleDHT_2D.this.rows, z2);
                        }
                        for (int i8 = 0; i8 < DoubleDHT_2D.this.rows; i8++) {
                            int access$0006 = (DoubleDHT_2D.this.columns * i8) + (i8 * 2);
                            double[] dArr6 = dArr2;
                            dArr6[access$0006] = dArr[i8];
                            dArr6[access$0006 + 1] = dArr[DoubleDHT_2D.this.rows + i8];
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

    /* JADX WARNING: Removed duplicated region for block: B:9:0x002d A[LOOP:0: B:8:0x002b->B:9:0x002d, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void ddxt2d_subth(int r19, pl.edu.icm.jlargearrays.DoubleLargeArray r20, boolean r21) {
        /*
            r18 = this;
            r11 = r18
            java.lang.Class<org.jtransforms.dht.DoubleDHT_2D> r12 = org.jtransforms.dht.DoubleDHT_2D.class
            long r0 = r11.columnsl
            int r2 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getNumberOfThreads()
            long r2 = (long) r2
            long r0 = org.apache.commons.math3.util.FastMath.min((long) r0, (long) r2)
            int r0 = (int) r0
            r1 = 4
            long r3 = r11.rowsl
            long r3 = r3 * r1
            long r1 = r11.columnsl
            r5 = 2
            int r7 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r7 != 0) goto L_0x0020
            r1 = 1
        L_0x001e:
            long r3 = r3 >> r1
            goto L_0x0026
        L_0x0020:
            int r1 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r1 >= 0) goto L_0x0026
            r1 = 2
            goto L_0x001e
        L_0x0026:
            r13 = r3
            java.util.concurrent.Future[] r15 = new java.util.concurrent.Future[r0]
            r1 = 0
            r10 = r1
        L_0x002b:
            if (r10 >= r0) goto L_0x004a
            long r6 = (long) r10
            org.jtransforms.dht.DoubleDHT_2D$14 r16 = new org.jtransforms.dht.DoubleDHT_2D$14
            r1 = r16
            r2 = r18
            r3 = r13
            r5 = r19
            r8 = r0
            r9 = r20
            r17 = r10
            r10 = r21
            r1.<init>(r3, r5, r6, r8, r9, r10)
            java.util.concurrent.Future r1 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r16)
            r15[r17] = r1
            int r10 = r17 + 1
            goto L_0x002b
        L_0x004a:
            r1 = 0
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r15)     // Catch:{ InterruptedException -> 0x005f, ExecutionException -> 0x004f }
            goto L_0x006e
        L_0x004f:
            r0 = move-exception
            r2 = r0
            java.lang.String r0 = r12.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r3 = java.util.logging.Level.SEVERE
            r0.log(r3, r1, r2)
            goto L_0x006e
        L_0x005f:
            r0 = move-exception
            r2 = r0
            java.lang.String r0 = r12.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r3 = java.util.logging.Level.SEVERE
            r0.log(r3, r1, r2)
        L_0x006e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.dht.DoubleDHT_2D.ddxt2d_subth(int, pl.edu.icm.jlargearrays.DoubleLargeArray, boolean):void");
    }

    private void ddxt2d_subth(int i, double[][] dArr, boolean z) {
        Class<DoubleDHT_2D> cls = DoubleDHT_2D.class;
        int min = FastMath.min(this.columns, ConcurrencyUtils.getNumberOfThreads());
        int i2 = this.rows * 4;
        int i3 = this.columns;
        if (i3 == 2) {
            i2 >>= 1;
        } else if (i3 < 2) {
            i2 >>= 2;
        }
        int i4 = i2;
        Future[] futureArr = new Future[min];
        for (int i5 = 0; i5 < min; i5++) {
            final int i6 = i4;
            final int i7 = i;
            final int i8 = i5;
            final int i9 = min;
            final double[][] dArr2 = dArr;
            final boolean z2 = z;
            futureArr[i5] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    double[] dArr = new double[i6];
                    if (DoubleDHT_2D.this.columns > 2) {
                        if (i7 == -1) {
                            int i = i8 * 4;
                            while (i < DoubleDHT_2D.this.columns) {
                                for (int i2 = 0; i2 < DoubleDHT_2D.this.rows; i2++) {
                                    int access$200 = DoubleDHT_2D.this.rows + i2;
                                    double[] dArr2 = dArr2[i2];
                                    dArr[i2] = dArr2[i];
                                    dArr[access$200] = dArr2[i + 1];
                                    dArr[DoubleDHT_2D.this.rows + access$200] = dArr2[i2][i + 2];
                                    dArr[access$200 + (DoubleDHT_2D.this.rows * 2)] = dArr2[i2][i + 3];
                                }
                                DoubleDHT_2D.this.dhtRows.forward(dArr, 0);
                                DoubleDHT_2D.this.dhtRows.forward(dArr, DoubleDHT_2D.this.rows);
                                DoubleDHT_2D.this.dhtRows.forward(dArr, DoubleDHT_2D.this.rows * 2);
                                DoubleDHT_2D.this.dhtRows.forward(dArr, DoubleDHT_2D.this.rows * 3);
                                for (int i3 = 0; i3 < DoubleDHT_2D.this.rows; i3++) {
                                    int access$2002 = DoubleDHT_2D.this.rows + i3;
                                    double[] dArr3 = dArr2[i3];
                                    dArr3[i] = dArr[i3];
                                    dArr3[i + 1] = dArr[access$2002];
                                    dArr3[i + 2] = dArr[DoubleDHT_2D.this.rows + access$2002];
                                    dArr2[i3][i + 3] = dArr[access$2002 + (DoubleDHT_2D.this.rows * 2)];
                                }
                                i += i9 * 4;
                            }
                            return;
                        }
                        int i4 = i8 * 4;
                        while (i4 < DoubleDHT_2D.this.columns) {
                            for (int i5 = 0; i5 < DoubleDHT_2D.this.rows; i5++) {
                                int access$2003 = DoubleDHT_2D.this.rows + i5;
                                double[] dArr4 = dArr2[i5];
                                dArr[i5] = dArr4[i4];
                                dArr[access$2003] = dArr4[i4 + 1];
                                dArr[DoubleDHT_2D.this.rows + access$2003] = dArr2[i5][i4 + 2];
                                dArr[access$2003 + (DoubleDHT_2D.this.rows * 2)] = dArr2[i5][i4 + 3];
                            }
                            DoubleDHT_2D.this.dhtRows.inverse(dArr, 0, z2);
                            DoubleDHT_2D.this.dhtRows.inverse(dArr, DoubleDHT_2D.this.rows, z2);
                            DoubleDHT_2D.this.dhtRows.inverse(dArr, DoubleDHT_2D.this.rows * 2, z2);
                            DoubleDHT_2D.this.dhtRows.inverse(dArr, DoubleDHT_2D.this.rows * 3, z2);
                            for (int i6 = 0; i6 < DoubleDHT_2D.this.rows; i6++) {
                                int access$2004 = DoubleDHT_2D.this.rows + i6;
                                double[] dArr5 = dArr2[i6];
                                dArr5[i4] = dArr[i6];
                                dArr5[i4 + 1] = dArr[access$2004];
                                dArr5[i4 + 2] = dArr[DoubleDHT_2D.this.rows + access$2004];
                                dArr2[i6][i4 + 3] = dArr[access$2004 + (DoubleDHT_2D.this.rows * 2)];
                            }
                            i4 += i9 * 4;
                        }
                    } else if (DoubleDHT_2D.this.columns == 2) {
                        for (int i7 = 0; i7 < DoubleDHT_2D.this.rows; i7++) {
                            dArr[i7] = dArr2[i7][i8 * 2];
                            dArr[DoubleDHT_2D.this.rows + i7] = dArr2[i7][(i8 * 2) + 1];
                        }
                        if (i7 == -1) {
                            DoubleDHT_2D.this.dhtRows.forward(dArr, 0);
                            DoubleDHT_2D.this.dhtRows.forward(dArr, DoubleDHT_2D.this.rows);
                        } else {
                            DoubleDHT_2D.this.dhtRows.inverse(dArr, 0, z2);
                            DoubleDHT_2D.this.dhtRows.inverse(dArr, DoubleDHT_2D.this.rows, z2);
                        }
                        for (int i8 = 0; i8 < DoubleDHT_2D.this.rows; i8++) {
                            double[] dArr6 = dArr2[i8];
                            int i9 = i8;
                            dArr6[i9 * 2] = dArr[i8];
                            dArr6[(i9 * 2) + 1] = dArr[DoubleDHT_2D.this.rows + i8];
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

    private void ddxt2d0_subth(int i, double[] dArr, boolean z) {
        Class<DoubleDHT_2D> cls = DoubleDHT_2D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i2 = this.rows;
        if (numberOfThreads <= i2) {
            i2 = ConcurrencyUtils.getNumberOfThreads();
        }
        Future[] futureArr = new Future[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            final int i4 = i;
            final int i5 = i3;
            final int i6 = i2;
            final double[] dArr2 = dArr;
            final boolean z2 = z;
            futureArr[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    if (i4 == -1) {
                        int i = i5;
                        while (i < DoubleDHT_2D.this.rows) {
                            DoubleDHT_2D.this.dhtColumns.forward(dArr2, DoubleDHT_2D.this.columns * i);
                            i += i6;
                        }
                        return;
                    }
                    int i2 = i5;
                    while (i2 < DoubleDHT_2D.this.rows) {
                        DoubleDHT_2D.this.dhtColumns.inverse(dArr2, DoubleDHT_2D.this.columns * i2, z2);
                        i2 += i6;
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

    private void ddxt2d0_subth(int i, DoubleLargeArray doubleLargeArray, boolean z) {
        Class<DoubleDHT_2D> cls = DoubleDHT_2D.class;
        long j = this.rowsl;
        if (((long) ConcurrencyUtils.getNumberOfThreads()) <= j) {
            j = (long) ConcurrencyUtils.getNumberOfThreads();
        }
        int i2 = (int) j;
        Future[] futureArr = new Future[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            final long j2 = (long) i3;
            final int i4 = i;
            final int i5 = i2;
            final DoubleLargeArray doubleLargeArray2 = doubleLargeArray;
            final boolean z2 = z;
            futureArr[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    if (i4 == -1) {
                        long j = j2;
                        while (j < DoubleDHT_2D.this.rowsl) {
                            DoubleDHT_2D.this.dhtColumns.forward(doubleLargeArray2, DoubleDHT_2D.this.columnsl * j);
                            j += (long) i5;
                        }
                        return;
                    }
                    long j2 = j2;
                    while (j2 < DoubleDHT_2D.this.rowsl) {
                        DoubleDHT_2D.this.dhtColumns.inverse(doubleLargeArray2, DoubleDHT_2D.this.columnsl * j2, z2);
                        j2 += (long) i5;
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

    private void ddxt2d0_subth(int i, double[][] dArr, boolean z) {
        Class<DoubleDHT_2D> cls = DoubleDHT_2D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i2 = this.rows;
        if (numberOfThreads <= i2) {
            i2 = ConcurrencyUtils.getNumberOfThreads();
        }
        Future[] futureArr = new Future[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            final int i4 = i;
            final int i5 = i3;
            final int i6 = i2;
            final double[][] dArr2 = dArr;
            final boolean z2 = z;
            futureArr[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    if (i4 == -1) {
                        int i = i5;
                        while (i < DoubleDHT_2D.this.rows) {
                            DoubleDHT_2D.this.dhtColumns.forward(dArr2[i]);
                            i += i6;
                        }
                        return;
                    }
                    int i2 = i5;
                    while (i2 < DoubleDHT_2D.this.rows) {
                        DoubleDHT_2D.this.dhtColumns.inverse(dArr2[i2], z2);
                        i2 += i6;
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

    private void ddxt2d_sub(int i, double[] dArr, boolean z) {
        int i2 = this.rows * 4;
        int i3 = this.columns;
        if (i3 == 2) {
            i2 >>= 1;
        } else if (i3 < 2) {
            i2 >>= 2;
        }
        double[] dArr2 = new double[i2];
        int i4 = 0;
        if (i3 > 2) {
            if (i == -1) {
                for (int i5 = 0; i5 < this.columns; i5 += 4) {
                    int i6 = 0;
                    while (true) {
                        int i7 = this.rows;
                        if (i6 >= i7) {
                            break;
                        }
                        int i8 = (this.columns * i6) + i5;
                        int i9 = i7 + i6;
                        dArr2[i6] = dArr[i8];
                        dArr2[i9] = dArr[i8 + 1];
                        dArr2[i9 + i7] = dArr[i8 + 2];
                        dArr2[i9 + (i7 * 2)] = dArr[i8 + 3];
                        i6++;
                    }
                    this.dhtRows.forward(dArr2, 0);
                    this.dhtRows.forward(dArr2, this.rows);
                    this.dhtRows.forward(dArr2, this.rows * 2);
                    this.dhtRows.forward(dArr2, this.rows * 3);
                    int i10 = 0;
                    while (true) {
                        int i11 = this.rows;
                        if (i10 >= i11) {
                            break;
                        }
                        int i12 = (this.columns * i10) + i5;
                        int i13 = i11 + i10;
                        dArr[i12] = dArr2[i10];
                        dArr[i12 + 1] = dArr2[i13];
                        dArr[i12 + 2] = dArr2[i13 + i11];
                        dArr[i12 + 3] = dArr2[i13 + (i11 * 2)];
                        i10++;
                    }
                }
                return;
            }
            for (int i14 = 0; i14 < this.columns; i14 += 4) {
                int i15 = 0;
                while (true) {
                    int i16 = this.rows;
                    if (i15 >= i16) {
                        break;
                    }
                    int i17 = (this.columns * i15) + i14;
                    int i18 = i16 + i15;
                    dArr2[i15] = dArr[i17];
                    dArr2[i18] = dArr[i17 + 1];
                    dArr2[i18 + i16] = dArr[i17 + 2];
                    dArr2[i18 + (i16 * 2)] = dArr[i17 + 3];
                    i15++;
                }
                this.dhtRows.inverse(dArr2, 0, z);
                this.dhtRows.inverse(dArr2, this.rows, z);
                this.dhtRows.inverse(dArr2, this.rows * 2, z);
                this.dhtRows.inverse(dArr2, this.rows * 3, z);
                int i19 = 0;
                while (true) {
                    int i20 = this.rows;
                    if (i19 >= i20) {
                        break;
                    }
                    int i21 = (this.columns * i19) + i14;
                    int i22 = i20 + i19;
                    dArr[i21] = dArr2[i19];
                    dArr[i21 + 1] = dArr2[i22];
                    dArr[i21 + 2] = dArr2[i22 + i20];
                    dArr[i21 + 3] = dArr2[i22 + (i20 * 2)];
                    i19++;
                }
            }
        } else if (i3 == 2) {
            int i23 = 0;
            while (true) {
                int i24 = this.rows;
                if (i23 >= i24) {
                    break;
                }
                int i25 = this.columns * i23;
                dArr2[i23] = dArr[i25];
                dArr2[i24 + i23] = dArr[i25 + 1];
                i23++;
            }
            if (i == -1) {
                this.dhtRows.forward(dArr2, 0);
                this.dhtRows.forward(dArr2, this.rows);
            } else {
                this.dhtRows.inverse(dArr2, 0, z);
                this.dhtRows.inverse(dArr2, this.rows, z);
            }
            while (true) {
                int i26 = this.rows;
                if (i4 < i26) {
                    int i27 = this.columns * i4;
                    dArr[i27] = dArr2[i4];
                    dArr[i27 + 1] = dArr2[i26 + i4];
                    i4++;
                } else {
                    return;
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x01a4  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x002c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void ddxt2d_sub(int r24, pl.edu.icm.jlargearrays.DoubleLargeArray r25, boolean r26) {
        /*
            r23 = this;
            r0 = r23
            r1 = r24
            r2 = r25
            r3 = r26
            long r4 = r0.rowsl
            r6 = 4
            long r4 = r4 * r6
            long r8 = r0.columnsl
            r10 = 2
            int r12 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r12 != 0) goto L_0x0018
            r8 = 1
        L_0x0016:
            long r4 = r4 >> r8
            goto L_0x001e
        L_0x0018:
            int r8 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r8 >= 0) goto L_0x001e
            r8 = 2
            goto L_0x0016
        L_0x001e:
            pl.edu.icm.jlargearrays.DoubleLargeArray r8 = new pl.edu.icm.jlargearrays.DoubleLargeArray
            r8.<init>((long) r4)
            long r4 = r0.columnsl
            int r9 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            r12 = -1
            r13 = 1
            if (r9 <= 0) goto L_0x01a4
            if (r1 != r12) goto L_0x00e8
            r15 = 0
        L_0x0030:
            long r6 = r0.columnsl
            int r1 = (r15 > r6 ? 1 : (r15 == r6 ? 0 : -1))
            if (r1 >= 0) goto L_0x020a
            r6 = 0
        L_0x0038:
            long r4 = r0.rowsl
            int r1 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r1 >= 0) goto L_0x007c
            long r10 = r0.columnsl
            long r10 = r10 * r6
            long r10 = r10 + r15
            long r4 = r4 + r6
            double r0 = r2.getDouble(r10)
            r8.setDouble(r6, r0)
            long r0 = r10 + r13
            double r0 = r2.getDouble(r0)
            r8.setDouble(r4, r0)
            r0 = r23
            long r13 = r0.rowsl
            long r13 = r13 + r4
            r21 = r6
            r19 = 2
            long r6 = r10 + r19
            double r6 = r2.getDouble(r6)
            r8.setDouble(r13, r6)
            long r6 = r0.rowsl
            long r6 = r6 * r19
            long r4 = r4 + r6
            r6 = 3
            long r10 = r10 + r6
            double r6 = r2.getDouble(r10)
            r8.setDouble(r4, r6)
            r3 = 1
            long r6 = r21 + r3
            r13 = r3
            r10 = 2
            goto L_0x0038
        L_0x007c:
            org.jtransforms.dht.DoubleDHT_1D r1 = r0.dhtRows
            r3 = 0
            r1.forward((pl.edu.icm.jlargearrays.DoubleLargeArray) r8, (long) r3)
            org.jtransforms.dht.DoubleDHT_1D r1 = r0.dhtRows
            long r3 = r0.rowsl
            r1.forward((pl.edu.icm.jlargearrays.DoubleLargeArray) r8, (long) r3)
            org.jtransforms.dht.DoubleDHT_1D r1 = r0.dhtRows
            long r3 = r0.rowsl
            r5 = 2
            long r3 = r3 * r5
            r1.forward((pl.edu.icm.jlargearrays.DoubleLargeArray) r8, (long) r3)
            org.jtransforms.dht.DoubleDHT_1D r1 = r0.dhtRows
            long r3 = r0.rowsl
            r5 = 3
            long r3 = r3 * r5
            r1.forward((pl.edu.icm.jlargearrays.DoubleLargeArray) r8, (long) r3)
            r3 = 0
        L_0x00a0:
            long r5 = r0.rowsl
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 >= 0) goto L_0x00df
            long r9 = r0.columnsl
            long r9 = r9 * r3
            long r9 = r9 + r15
            long r5 = r5 + r3
            double r11 = r8.getDouble(r3)
            r2.setDouble(r9, r11)
            r11 = 1
            long r13 = r9 + r11
            double r11 = r8.getDouble(r5)
            r2.setDouble(r13, r11)
            r11 = 2
            long r13 = r9 + r11
            long r11 = r0.rowsl
            long r11 = r11 + r5
            double r11 = r8.getDouble(r11)
            r2.setDouble(r13, r11)
            r11 = 3
            long r9 = r9 + r11
            long r11 = r0.rowsl
            r13 = 2
            long r11 = r11 * r13
            long r5 = r5 + r11
            double r5 = r8.getDouble(r5)
            r2.setDouble(r9, r5)
            r5 = 1
            long r3 = r3 + r5
            goto L_0x00a0
        L_0x00df:
            r3 = 4
            long r15 = r15 + r3
            r10 = 2
            r13 = 1
            goto L_0x0030
        L_0x00e8:
            r4 = 0
        L_0x00ea:
            long r6 = r0.columnsl
            int r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r1 >= 0) goto L_0x020a
            r6 = 0
        L_0x00f2:
            long r9 = r0.rowsl
            int r1 = (r6 > r9 ? 1 : (r6 == r9 ? 0 : -1))
            if (r1 >= 0) goto L_0x0134
            long r11 = r0.columnsl
            long r11 = r11 * r6
            long r11 = r11 + r4
            long r9 = r9 + r6
            double r13 = r2.getDouble(r11)
            r8.setDouble(r6, r13)
            r15 = r4
            r13 = 1
            long r4 = r11 + r13
            double r4 = r2.getDouble(r4)
            r8.setDouble(r9, r4)
            long r4 = r0.rowsl
            long r4 = r4 + r9
            r21 = r6
            r13 = 2
            long r6 = r11 + r13
            double r6 = r2.getDouble(r6)
            r8.setDouble(r4, r6)
            long r4 = r0.rowsl
            long r4 = r4 * r13
            long r9 = r9 + r4
            r4 = 3
            long r11 = r11 + r4
            double r4 = r2.getDouble(r11)
            r8.setDouble(r9, r4)
            r4 = 1
            long r6 = r21 + r4
            r4 = r15
            goto L_0x00f2
        L_0x0134:
            r15 = r4
            org.jtransforms.dht.DoubleDHT_1D r1 = r0.dhtRows
            r4 = 0
            r1.inverse((pl.edu.icm.jlargearrays.DoubleLargeArray) r8, (long) r4, (boolean) r3)
            org.jtransforms.dht.DoubleDHT_1D r1 = r0.dhtRows
            long r4 = r0.rowsl
            r1.inverse((pl.edu.icm.jlargearrays.DoubleLargeArray) r8, (long) r4, (boolean) r3)
            org.jtransforms.dht.DoubleDHT_1D r1 = r0.dhtRows
            long r4 = r0.rowsl
            r6 = 2
            long r4 = r4 * r6
            r1.inverse((pl.edu.icm.jlargearrays.DoubleLargeArray) r8, (long) r4, (boolean) r3)
            org.jtransforms.dht.DoubleDHT_1D r1 = r0.dhtRows
            long r4 = r0.rowsl
            r6 = 3
            long r4 = r4 * r6
            r1.inverse((pl.edu.icm.jlargearrays.DoubleLargeArray) r8, (long) r4, (boolean) r3)
            r4 = 0
        L_0x0159:
            long r6 = r0.rowsl
            int r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r1 >= 0) goto L_0x0199
            long r9 = r0.columnsl
            long r9 = r9 * r4
            long r9 = r9 + r15
            long r6 = r6 + r4
            double r11 = r8.getDouble(r4)
            r2.setDouble(r9, r11)
            r11 = 1
            long r13 = r9 + r11
            double r11 = r8.getDouble(r6)
            r2.setDouble(r13, r11)
            r13 = 2
            long r11 = r9 + r13
            long r13 = r0.rowsl
            long r13 = r13 + r6
            double r13 = r8.getDouble(r13)
            r2.setDouble(r11, r13)
            r11 = 3
            long r9 = r9 + r11
            long r13 = r0.rowsl
            r17 = 2
            long r13 = r13 * r17
            long r6 = r6 + r13
            double r6 = r8.getDouble(r6)
            r2.setDouble(r9, r6)
            r6 = 1
            long r4 = r4 + r6
            goto L_0x0159
        L_0x0199:
            r4 = 4
            r11 = 3
            r17 = 2
            long r6 = r15 + r4
            r4 = r6
            goto L_0x00ea
        L_0x01a4:
            r17 = r10
            int r4 = (r4 > r17 ? 1 : (r4 == r17 ? 0 : -1))
            if (r4 != 0) goto L_0x020a
            r4 = 0
        L_0x01ac:
            long r6 = r0.rowsl
            int r6 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r6 >= 0) goto L_0x01cb
            long r6 = r0.columnsl
            long r6 = r6 * r4
            double r9 = r2.getDouble(r6)
            r8.setDouble(r4, r9)
            long r9 = r0.rowsl
            long r9 = r9 + r4
            r13 = 1
            long r6 = r6 + r13
            double r6 = r2.getDouble(r6)
            r8.setDouble(r9, r6)
            long r4 = r4 + r13
            goto L_0x01ac
        L_0x01cb:
            if (r1 != r12) goto L_0x01dc
            org.jtransforms.dht.DoubleDHT_1D r1 = r0.dhtRows
            r4 = 0
            r1.forward((pl.edu.icm.jlargearrays.DoubleLargeArray) r8, (long) r4)
            org.jtransforms.dht.DoubleDHT_1D r1 = r0.dhtRows
            long r6 = r0.rowsl
            r1.forward((pl.edu.icm.jlargearrays.DoubleLargeArray) r8, (long) r6)
            goto L_0x01ea
        L_0x01dc:
            r4 = 0
            org.jtransforms.dht.DoubleDHT_1D r1 = r0.dhtRows
            r1.inverse((pl.edu.icm.jlargearrays.DoubleLargeArray) r8, (long) r4, (boolean) r3)
            org.jtransforms.dht.DoubleDHT_1D r1 = r0.dhtRows
            long r6 = r0.rowsl
            r1.inverse((pl.edu.icm.jlargearrays.DoubleLargeArray) r8, (long) r6, (boolean) r3)
        L_0x01ea:
            r6 = r4
        L_0x01eb:
            long r3 = r0.rowsl
            int r1 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r1 >= 0) goto L_0x020a
            long r3 = r0.columnsl
            long r3 = r3 * r6
            double r9 = r8.getDouble(r6)
            r2.setDouble(r3, r9)
            r9 = 1
            long r3 = r3 + r9
            long r11 = r0.rowsl
            long r11 = r11 + r6
            double r11 = r8.getDouble(r11)
            r2.setDouble(r3, r11)
            long r6 = r6 + r9
            goto L_0x01eb
        L_0x020a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.dht.DoubleDHT_2D.ddxt2d_sub(int, pl.edu.icm.jlargearrays.DoubleLargeArray, boolean):void");
    }

    private void ddxt2d_sub(int i, double[][] dArr, boolean z) {
        int i2 = this.rows * 4;
        int i3 = this.columns;
        if (i3 == 2) {
            i2 >>= 1;
        } else if (i3 < 2) {
            i2 >>= 2;
        }
        double[] dArr2 = new double[i2];
        if (i3 > 2) {
            if (i == -1) {
                for (int i4 = 0; i4 < this.columns; i4 += 4) {
                    int i5 = 0;
                    while (true) {
                        int i6 = this.rows;
                        if (i5 >= i6) {
                            break;
                        }
                        int i7 = i6 + i5;
                        double[] dArr3 = dArr[i5];
                        dArr2[i5] = dArr3[i4];
                        dArr2[i7] = dArr3[i4 + 1];
                        dArr2[i7 + i6] = dArr3[i4 + 2];
                        dArr2[i7 + (i6 * 2)] = dArr3[i4 + 3];
                        i5++;
                    }
                    this.dhtRows.forward(dArr2, 0);
                    this.dhtRows.forward(dArr2, this.rows);
                    this.dhtRows.forward(dArr2, this.rows * 2);
                    this.dhtRows.forward(dArr2, this.rows * 3);
                    int i8 = 0;
                    while (true) {
                        int i9 = this.rows;
                        if (i8 >= i9) {
                            break;
                        }
                        int i10 = i9 + i8;
                        double[] dArr4 = dArr[i8];
                        dArr4[i4] = dArr2[i8];
                        dArr4[i4 + 1] = dArr2[i10];
                        dArr4[i4 + 2] = dArr2[i10 + i9];
                        dArr4[i4 + 3] = dArr2[i10 + (i9 * 2)];
                        i8++;
                    }
                }
                return;
            }
            for (int i11 = 0; i11 < this.columns; i11 += 4) {
                int i12 = 0;
                while (true) {
                    int i13 = this.rows;
                    if (i12 >= i13) {
                        break;
                    }
                    int i14 = i13 + i12;
                    double[] dArr5 = dArr[i12];
                    dArr2[i12] = dArr5[i11];
                    dArr2[i14] = dArr5[i11 + 1];
                    dArr2[i14 + i13] = dArr5[i11 + 2];
                    dArr2[i14 + (i13 * 2)] = dArr5[i11 + 3];
                    i12++;
                }
                this.dhtRows.inverse(dArr2, 0, z);
                this.dhtRows.inverse(dArr2, this.rows, z);
                this.dhtRows.inverse(dArr2, this.rows * 2, z);
                this.dhtRows.inverse(dArr2, this.rows * 3, z);
                int i15 = 0;
                while (true) {
                    int i16 = this.rows;
                    if (i15 >= i16) {
                        break;
                    }
                    int i17 = i16 + i15;
                    double[] dArr6 = dArr[i15];
                    dArr6[i11] = dArr2[i15];
                    dArr6[i11 + 1] = dArr2[i17];
                    dArr6[i11 + 2] = dArr2[i17 + i16];
                    dArr6[i11 + 3] = dArr2[i17 + (i16 * 2)];
                    i15++;
                }
            }
        } else if (i3 == 2) {
            int i18 = 0;
            while (true) {
                int i19 = this.rows;
                if (i18 >= i19) {
                    break;
                }
                double[] dArr7 = dArr[i18];
                dArr2[i18] = dArr7[0];
                dArr2[i19 + i18] = dArr7[1];
                i18++;
            }
            if (i == -1) {
                this.dhtRows.forward(dArr2, 0);
                this.dhtRows.forward(dArr2, this.rows);
            } else {
                this.dhtRows.inverse(dArr2, 0, z);
                this.dhtRows.inverse(dArr2, this.rows, z);
            }
            int i20 = 0;
            while (true) {
                int i21 = this.rows;
                if (i20 < i21) {
                    double[] dArr8 = dArr[i20];
                    dArr8[0] = dArr2[i20];
                    dArr8[1] = dArr2[i21 + i20];
                    i20++;
                } else {
                    return;
                }
            }
        }
    }

    private void yTransform(double[] dArr) {
        int i = 0;
        while (true) {
            int i2 = this.rows;
            if (i <= i2 / 2) {
                int i3 = (i2 - i) % i2;
                int i4 = this.columns;
                int i5 = i * i4;
                int i6 = i3 * i4;
                int i7 = 0;
                while (true) {
                    int i8 = this.columns;
                    if (i7 > i8 / 2) {
                        break;
                    }
                    int i9 = (i8 - i7) % i8;
                    int i10 = i5 + i7;
                    double d = dArr[i10];
                    int i11 = i6 + i7;
                    double d2 = dArr[i11];
                    int i12 = i5 + i9;
                    double d3 = dArr[i12];
                    int i13 = i9 + i6;
                    double d4 = dArr[i13];
                    double d5 = ((d + d4) - (d2 + d3)) / 2.0d;
                    dArr[i10] = d - d5;
                    dArr[i11] = d2 + d5;
                    dArr[i12] = d3 + d5;
                    dArr[i13] = d4 - d5;
                    i7++;
                }
                i++;
            } else {
                return;
            }
        }
    }

    private void yTransform(DoubleLargeArray doubleLargeArray) {
        DoubleLargeArray doubleLargeArray2 = doubleLargeArray;
        long j = 0;
        while (true) {
            long j2 = this.rowsl;
            long j3 = 2;
            if (j <= j2 / 2) {
                long j4 = (j2 - j) % j2;
                long j5 = this.columnsl;
                long j6 = j * j5;
                long j7 = j4 * j5;
                long j8 = 0;
                while (true) {
                    long j9 = this.columnsl;
                    if (j8 > j9 / j3) {
                        break;
                    }
                    long j10 = (j9 - j8) % j9;
                    long j11 = j6 + j8;
                    double d = doubleLargeArray2.getDouble(j11);
                    long j12 = j7 + j8;
                    double d2 = doubleLargeArray2.getDouble(j12);
                    long j13 = j6 + j10;
                    double d3 = doubleLargeArray2.getDouble(j13);
                    long j14 = j6;
                    long j15 = j7 + j10;
                    double d4 = doubleLargeArray2.getDouble(j15);
                    double d5 = ((d + d4) - (d2 + d3)) / 2.0d;
                    doubleLargeArray2.setDouble(j11, d - d5);
                    doubleLargeArray2.setDouble(j12, d2 + d5);
                    doubleLargeArray2.setDouble(j13, d3 + d5);
                    doubleLargeArray2.setDouble(j15, d4 - d5);
                    j8++;
                    j6 = j14;
                    j7 = j7;
                    j3 = 2;
                }
                j++;
            } else {
                return;
            }
        }
    }

    private void yTransform(double[][] dArr) {
        int i = 0;
        while (true) {
            int i2 = this.rows;
            if (i <= i2 / 2) {
                int i3 = (i2 - i) % i2;
                int i4 = 0;
                while (true) {
                    int i5 = this.columns;
                    if (i4 > i5 / 2) {
                        break;
                    }
                    int i6 = (i5 - i4) % i5;
                    double[] dArr2 = dArr[i];
                    double d = dArr2[i4];
                    double[] dArr3 = dArr[i3];
                    double d2 = dArr3[i4];
                    double d3 = dArr2[i6];
                    double d4 = dArr3[i6];
                    double d5 = ((d + d4) - (d2 + d3)) / 2.0d;
                    dArr2[i4] = d - d5;
                    dArr3[i4] = d2 + d5;
                    dArr2[i6] = d3 + d5;
                    dArr3[i6] = d4 - d5;
                    i4++;
                }
                i++;
            } else {
                return;
            }
        }
    }
}
