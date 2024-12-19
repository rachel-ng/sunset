package org.jtransforms.dht;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.math3.util.FastMath;
import org.jtransforms.utils.CommonUtils;
import pl.edu.icm.jlargearrays.ConcurrencyUtils;
import pl.edu.icm.jlargearrays.FloatLargeArray;
import pl.edu.icm.jlargearrays.LargeArray;

public class FloatDHT_2D {
    /* access modifiers changed from: private */
    public int columns;
    /* access modifiers changed from: private */
    public long columnsl;
    /* access modifiers changed from: private */
    public FloatDHT_1D dhtColumns;
    /* access modifiers changed from: private */
    public FloatDHT_1D dhtRows;
    private boolean isPowerOfTwo = false;
    /* access modifiers changed from: private */
    public int rows;
    /* access modifiers changed from: private */
    public long rowsl;
    private boolean useThreads = false;

    public FloatDHT_2D(long j, long j2) {
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
        FloatDHT_1D floatDHT_1D = new FloatDHT_1D(j);
        this.dhtRows = floatDHT_1D;
        if (j == j2) {
            this.dhtColumns = floatDHT_1D;
        } else {
            this.dhtColumns = new FloatDHT_1D(j2);
        }
    }

    public void forward(final float[] fArr) {
        int i;
        int i2;
        Class<FloatDHT_2D> cls = FloatDHT_2D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = 0;
        if (this.isPowerOfTwo) {
            if (numberOfThreads <= 1 || !this.useThreads) {
                ddxt2d_sub(-1, fArr, true);
                while (i3 < this.rows) {
                    this.dhtColumns.forward(fArr, this.columns * i3);
                    i3++;
                }
            } else {
                ddxt2d_subth(-1, fArr, true);
                ddxt2d0_subth(-1, fArr, true);
            }
            yTransform(fArr);
            return;
        }
        if (numberOfThreads <= 1 || !this.useThreads || (i2 = this.rows) < numberOfThreads || this.columns < numberOfThreads) {
            int i4 = 0;
            while (true) {
                i = this.rows;
                if (i4 >= i) {
                    break;
                }
                this.dhtColumns.forward(fArr, this.columns * i4);
                i4++;
            }
            float[] fArr2 = new float[i];
            for (int i5 = 0; i5 < this.columns; i5++) {
                for (int i6 = 0; i6 < this.rows; i6++) {
                    fArr2[i6] = fArr[(this.columns * i6) + i5];
                }
                this.dhtRows.forward(fArr2);
                for (int i7 = 0; i7 < this.rows; i7++) {
                    fArr[(this.columns * i7) + i5] = fArr2[i7];
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
                            FloatDHT_2D.this.dhtColumns.forward(fArr, FloatDHT_2D.this.columns * i);
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
                        float[] fArr = new float[FloatDHT_2D.this.rows];
                        for (int i = i13; i < i14; i++) {
                            for (int i2 = 0; i2 < FloatDHT_2D.this.rows; i2++) {
                                fArr[i2] = fArr[(FloatDHT_2D.this.columns * i2) + i];
                            }
                            FloatDHT_2D.this.dhtRows.forward(fArr);
                            for (int i3 = 0; i3 < FloatDHT_2D.this.rows; i3++) {
                                fArr[(FloatDHT_2D.this.columns * i3) + i] = fArr[i3];
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
        yTransform(fArr);
    }

    public void forward(FloatLargeArray floatLargeArray) {
        FloatLargeArray floatLargeArray2 = floatLargeArray;
        Class<FloatDHT_2D> cls = FloatDHT_2D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (this.isPowerOfTwo) {
            if (numberOfThreads <= 1 || !this.useThreads) {
                ddxt2d_sub(-1, floatLargeArray2, true);
                for (long j = 0; j < this.rowsl; j++) {
                    this.dhtColumns.forward(floatLargeArray2, this.columnsl * j);
                }
            } else {
                ddxt2d_subth(-1, floatLargeArray2, true);
                ddxt2d0_subth(-1, floatLargeArray2, true);
            }
            yTransform(floatLargeArray);
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
                    final FloatLargeArray floatLargeArray3 = floatLargeArray;
                    futureArr[i2] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            for (long j = j5; j < j6; j++) {
                                FloatDHT_2D.this.dhtColumns.forward(floatLargeArray3, FloatDHT_2D.this.columnsl * j);
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
                    final FloatLargeArray floatLargeArray4 = floatLargeArray;
                    futureArr[i] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            FloatLargeArray floatLargeArray = new FloatLargeArray(FloatDHT_2D.this.rowsl, false);
                            for (long j = j8; j < j9; j++) {
                                for (long j2 = 0; j2 < FloatDHT_2D.this.rowsl; j2++) {
                                    floatLargeArray.setFloat(j2, floatLargeArray4.getFloat((FloatDHT_2D.this.columnsl * j2) + j));
                                }
                                FloatDHT_2D.this.dhtRows.forward(floatLargeArray);
                                for (long j3 = 0; j3 < FloatDHT_2D.this.rowsl; j3++) {
                                    floatLargeArray4.setFloat((FloatDHT_2D.this.columnsl * j3) + j, floatLargeArray.getFloat(j3));
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
                yTransform(floatLargeArray);
            }
        }
        for (long j10 = 0; j10 < this.rowsl; j10++) {
            this.dhtColumns.forward(floatLargeArray2, this.columnsl * j10);
        }
        FloatLargeArray floatLargeArray5 = new FloatLargeArray(this.rowsl, false);
        for (long j11 = 0; j11 < this.columnsl; j11++) {
            for (long j12 = 0; j12 < this.rowsl; j12++) {
                floatLargeArray5.setFloat(j12, floatLargeArray2.getFloat((this.columnsl * j12) + j11));
            }
            this.dhtRows.forward(floatLargeArray5);
            for (long j13 = 0; j13 < this.rowsl; j13++) {
                floatLargeArray2.setFloat((this.columnsl * j13) + j11, floatLargeArray5.getFloat(j13));
            }
        }
        yTransform(floatLargeArray);
    }

    public void forward(final float[][] fArr) {
        int i;
        int i2;
        Class<FloatDHT_2D> cls = FloatDHT_2D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = 0;
        if (this.isPowerOfTwo) {
            if (numberOfThreads <= 1 || !this.useThreads) {
                ddxt2d_sub(-1, fArr, true);
                while (i3 < this.rows) {
                    this.dhtColumns.forward(fArr[i3]);
                    i3++;
                }
            } else {
                ddxt2d_subth(-1, fArr, true);
                ddxt2d0_subth(-1, fArr, true);
            }
            yTransform(fArr);
            return;
        }
        if (numberOfThreads <= 1 || !this.useThreads || (i2 = this.rows) < numberOfThreads || this.columns < numberOfThreads) {
            int i4 = 0;
            while (true) {
                i = this.rows;
                if (i4 >= i) {
                    break;
                }
                this.dhtColumns.forward(fArr[i4]);
                i4++;
            }
            float[] fArr2 = new float[i];
            for (int i5 = 0; i5 < this.columns; i5++) {
                for (int i6 = 0; i6 < this.rows; i6++) {
                    fArr2[i6] = fArr[i6][i5];
                }
                this.dhtRows.forward(fArr2);
                for (int i7 = 0; i7 < this.rows; i7++) {
                    fArr[i7][i5] = fArr2[i7];
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
                            FloatDHT_2D.this.dhtColumns.forward(fArr[i]);
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
                        float[] fArr = new float[FloatDHT_2D.this.rows];
                        for (int i = i13; i < i14; i++) {
                            for (int i2 = 0; i2 < FloatDHT_2D.this.rows; i2++) {
                                fArr[i2] = fArr[i2][i];
                            }
                            FloatDHT_2D.this.dhtRows.forward(fArr);
                            for (int i3 = 0; i3 < FloatDHT_2D.this.rows; i3++) {
                                fArr[i3][i] = fArr[i3];
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
        yTransform(fArr);
    }

    public void inverse(float[] fArr, boolean z) {
        int i;
        int i2;
        Class<FloatDHT_2D> cls = FloatDHT_2D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = 0;
        if (this.isPowerOfTwo) {
            if (numberOfThreads <= 1 || !this.useThreads) {
                ddxt2d_sub(1, fArr, z);
                while (i3 < this.rows) {
                    this.dhtColumns.inverse(fArr, this.columns * i3, z);
                    i3++;
                }
            } else {
                ddxt2d_subth(1, fArr, z);
                ddxt2d0_subth(1, fArr, z);
            }
            yTransform(fArr);
            return;
        }
        if (numberOfThreads <= 1 || !this.useThreads || (i2 = this.rows) < numberOfThreads || this.columns < numberOfThreads) {
            int i4 = 0;
            while (true) {
                i = this.rows;
                if (i4 >= i) {
                    break;
                }
                this.dhtColumns.inverse(fArr, this.columns * i4, z);
                i4++;
            }
            float[] fArr2 = new float[i];
            for (int i5 = 0; i5 < this.columns; i5++) {
                for (int i6 = 0; i6 < this.rows; i6++) {
                    fArr2[i6] = fArr[(this.columns * i6) + i5];
                }
                this.dhtRows.inverse(fArr2, z);
                for (int i7 = 0; i7 < this.rows; i7++) {
                    fArr[(this.columns * i7) + i5] = fArr2[i7];
                }
            }
        } else {
            Future[] futureArr = new Future[numberOfThreads];
            int i8 = i2 / numberOfThreads;
            int i9 = 0;
            while (i9 < numberOfThreads) {
                final int i10 = i9 * i8;
                final int i11 = i9 == numberOfThreads + -1 ? this.rows : i10 + i8;
                final float[] fArr3 = fArr;
                final boolean z2 = z;
                futureArr[i9] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i10; i < i11; i++) {
                            FloatDHT_2D.this.dhtColumns.inverse(fArr3, FloatDHT_2D.this.columns * i, z2);
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
                final float[] fArr4 = fArr;
                final boolean z3 = z;
                futureArr[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        float[] fArr = new float[FloatDHT_2D.this.rows];
                        for (int i = i13; i < i14; i++) {
                            for (int i2 = 0; i2 < FloatDHT_2D.this.rows; i2++) {
                                fArr[i2] = fArr4[(FloatDHT_2D.this.columns * i2) + i];
                            }
                            FloatDHT_2D.this.dhtRows.inverse(fArr, z3);
                            for (int i3 = 0; i3 < FloatDHT_2D.this.rows; i3++) {
                                fArr4[(FloatDHT_2D.this.columns * i3) + i] = fArr[i3];
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
        yTransform(fArr);
    }

    public void inverse(FloatLargeArray floatLargeArray, boolean z) {
        FloatLargeArray floatLargeArray2 = floatLargeArray;
        boolean z2 = z;
        Class<FloatDHT_2D> cls = FloatDHT_2D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (this.isPowerOfTwo) {
            if (numberOfThreads <= 1 || !this.useThreads) {
                ddxt2d_sub(1, floatLargeArray2, z2);
                for (long j = 0; j < this.rowsl; j++) {
                    this.dhtColumns.inverse(floatLargeArray2, this.columnsl * j, z2);
                }
            } else {
                ddxt2d_subth(1, floatLargeArray2, z2);
                ddxt2d0_subth(1, floatLargeArray2, z2);
            }
            yTransform(floatLargeArray);
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
                    final FloatLargeArray floatLargeArray3 = floatLargeArray;
                    final boolean z3 = z;
                    futureArr[i2] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            for (long j = j5; j < j6; j++) {
                                FloatDHT_2D.this.dhtColumns.inverse(floatLargeArray3, FloatDHT_2D.this.columnsl * j, z3);
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
                    final FloatLargeArray floatLargeArray4 = floatLargeArray;
                    int i3 = numberOfThreads;
                    String str2 = str;
                    final boolean z4 = z;
                    futureArr[i] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            FloatLargeArray floatLargeArray = new FloatLargeArray(FloatDHT_2D.this.rowsl, false);
                            for (long j = j10; j < j11; j++) {
                                for (long j2 = 0; j2 < FloatDHT_2D.this.rowsl; j2++) {
                                    floatLargeArray.setFloat(j2, floatLargeArray4.getFloat((FloatDHT_2D.this.columnsl * j2) + j));
                                }
                                FloatDHT_2D.this.dhtRows.inverse(floatLargeArray, z4);
                                for (long j3 = 0; j3 < FloatDHT_2D.this.rowsl; j3++) {
                                    floatLargeArray4.setFloat((FloatDHT_2D.this.columnsl * j3) + j, floatLargeArray.getFloat(j3));
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
                yTransform(floatLargeArray);
            }
        }
        for (long j12 = 0; j12 < this.rowsl; j12++) {
            this.dhtColumns.inverse(floatLargeArray2, this.columnsl * j12, z2);
        }
        FloatLargeArray floatLargeArray5 = new FloatLargeArray(this.rowsl, false);
        for (long j13 = 0; j13 < this.columnsl; j13++) {
            for (long j14 = 0; j14 < this.rowsl; j14++) {
                floatLargeArray5.setFloat(j14, floatLargeArray2.getFloat((this.columnsl * j14) + j13));
            }
            this.dhtRows.inverse(floatLargeArray5, z2);
            for (long j15 = 0; j15 < this.rowsl; j15++) {
                floatLargeArray2.setFloat((this.columnsl * j15) + j13, floatLargeArray5.getFloat(j15));
            }
        }
        yTransform(floatLargeArray);
    }

    public void inverse(float[][] fArr, boolean z) {
        int i;
        int i2;
        Class<FloatDHT_2D> cls = FloatDHT_2D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = 0;
        if (this.isPowerOfTwo) {
            if (numberOfThreads <= 1 || !this.useThreads) {
                ddxt2d_sub(1, fArr, z);
                while (i3 < this.rows) {
                    this.dhtColumns.inverse(fArr[i3], z);
                    i3++;
                }
            } else {
                ddxt2d_subth(1, fArr, z);
                ddxt2d0_subth(1, fArr, z);
            }
            yTransform(fArr);
            return;
        }
        if (numberOfThreads <= 1 || !this.useThreads || (i2 = this.rows) < numberOfThreads || this.columns < numberOfThreads) {
            int i4 = 0;
            while (true) {
                i = this.rows;
                if (i4 >= i) {
                    break;
                }
                this.dhtColumns.inverse(fArr[i4], z);
                i4++;
            }
            float[] fArr2 = new float[i];
            for (int i5 = 0; i5 < this.columns; i5++) {
                for (int i6 = 0; i6 < this.rows; i6++) {
                    fArr2[i6] = fArr[i6][i5];
                }
                this.dhtRows.inverse(fArr2, z);
                for (int i7 = 0; i7 < this.rows; i7++) {
                    fArr[i7][i5] = fArr2[i7];
                }
            }
        } else {
            Future[] futureArr = new Future[numberOfThreads];
            int i8 = i2 / numberOfThreads;
            int i9 = 0;
            while (i9 < numberOfThreads) {
                final int i10 = i9 * i8;
                final int i11 = i9 == numberOfThreads + -1 ? this.rows : i10 + i8;
                final float[][] fArr3 = fArr;
                final boolean z2 = z;
                futureArr[i9] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i10; i < i11; i++) {
                            FloatDHT_2D.this.dhtColumns.inverse(fArr3[i], z2);
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
                final float[][] fArr4 = fArr;
                final boolean z3 = z;
                futureArr[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        float[] fArr = new float[FloatDHT_2D.this.rows];
                        for (int i = i13; i < i14; i++) {
                            for (int i2 = 0; i2 < FloatDHT_2D.this.rows; i2++) {
                                fArr[i2] = fArr4[i2][i];
                            }
                            FloatDHT_2D.this.dhtRows.inverse(fArr, z3);
                            for (int i3 = 0; i3 < FloatDHT_2D.this.rows; i3++) {
                                fArr4[i3][i] = fArr[i3];
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
        yTransform(fArr);
    }

    private void ddxt2d_subth(int i, float[] fArr, boolean z) {
        Class<FloatDHT_2D> cls = FloatDHT_2D.class;
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
            final float[] fArr2 = fArr;
            final boolean z2 = z;
            futureArr[i5] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    float[] fArr = new float[i6];
                    if (FloatDHT_2D.this.columns > 2) {
                        if (i7 == -1) {
                            int i = i8 * 4;
                            while (i < FloatDHT_2D.this.columns) {
                                for (int i2 = 0; i2 < FloatDHT_2D.this.rows; i2++) {
                                    int access$000 = (FloatDHT_2D.this.columns * i2) + i;
                                    int access$200 = FloatDHT_2D.this.rows + i2;
                                    float[] fArr2 = fArr2;
                                    fArr[i2] = fArr2[access$000];
                                    fArr[access$200] = fArr2[access$000 + 1];
                                    fArr[FloatDHT_2D.this.rows + access$200] = fArr2[access$000 + 2];
                                    fArr[access$200 + (FloatDHT_2D.this.rows * 2)] = fArr2[access$000 + 3];
                                }
                                FloatDHT_2D.this.dhtRows.forward(fArr, 0);
                                FloatDHT_2D.this.dhtRows.forward(fArr, FloatDHT_2D.this.rows);
                                FloatDHT_2D.this.dhtRows.forward(fArr, FloatDHT_2D.this.rows * 2);
                                FloatDHT_2D.this.dhtRows.forward(fArr, FloatDHT_2D.this.rows * 3);
                                for (int i3 = 0; i3 < FloatDHT_2D.this.rows; i3++) {
                                    int access$0002 = (FloatDHT_2D.this.columns * i3) + i;
                                    int access$2002 = FloatDHT_2D.this.rows + i3;
                                    float[] fArr3 = fArr2;
                                    fArr3[access$0002] = fArr[i3];
                                    fArr3[access$0002 + 1] = fArr[access$2002];
                                    fArr3[access$0002 + 2] = fArr[FloatDHT_2D.this.rows + access$2002];
                                    fArr2[access$0002 + 3] = fArr[access$2002 + (FloatDHT_2D.this.rows * 2)];
                                }
                                i += i9 * 4;
                            }
                            return;
                        }
                        int i4 = i8 * 4;
                        while (i4 < FloatDHT_2D.this.columns) {
                            for (int i5 = 0; i5 < FloatDHT_2D.this.rows; i5++) {
                                int access$0003 = (FloatDHT_2D.this.columns * i5) + i4;
                                int access$2003 = FloatDHT_2D.this.rows + i5;
                                float[] fArr4 = fArr2;
                                fArr[i5] = fArr4[access$0003];
                                fArr[access$2003] = fArr4[access$0003 + 1];
                                fArr[FloatDHT_2D.this.rows + access$2003] = fArr2[access$0003 + 2];
                                fArr[access$2003 + (FloatDHT_2D.this.rows * 2)] = fArr2[access$0003 + 3];
                            }
                            FloatDHT_2D.this.dhtRows.inverse(fArr, 0, z2);
                            FloatDHT_2D.this.dhtRows.inverse(fArr, FloatDHT_2D.this.rows, z2);
                            FloatDHT_2D.this.dhtRows.inverse(fArr, FloatDHT_2D.this.rows * 2, z2);
                            FloatDHT_2D.this.dhtRows.inverse(fArr, FloatDHT_2D.this.rows * 3, z2);
                            for (int i6 = 0; i6 < FloatDHT_2D.this.rows; i6++) {
                                int access$0004 = (FloatDHT_2D.this.columns * i6) + i4;
                                int access$2004 = FloatDHT_2D.this.rows + i6;
                                float[] fArr5 = fArr2;
                                fArr5[access$0004] = fArr[i6];
                                fArr5[access$0004 + 1] = fArr[access$2004];
                                fArr5[access$0004 + 2] = fArr[FloatDHT_2D.this.rows + access$2004];
                                fArr2[access$0004 + 3] = fArr[access$2004 + (FloatDHT_2D.this.rows * 2)];
                            }
                            i4 += i9 * 4;
                        }
                    } else if (FloatDHT_2D.this.columns == 2) {
                        for (int i7 = 0; i7 < FloatDHT_2D.this.rows; i7++) {
                            int access$0005 = (FloatDHT_2D.this.columns * i7) + (i8 * 2);
                            fArr[i7] = fArr2[access$0005];
                            fArr[FloatDHT_2D.this.rows + i7] = fArr2[access$0005 + 1];
                        }
                        if (i7 == -1) {
                            FloatDHT_2D.this.dhtRows.forward(fArr, 0);
                            FloatDHT_2D.this.dhtRows.forward(fArr, FloatDHT_2D.this.rows);
                        } else {
                            FloatDHT_2D.this.dhtRows.inverse(fArr, 0, z2);
                            FloatDHT_2D.this.dhtRows.inverse(fArr, FloatDHT_2D.this.rows, z2);
                        }
                        for (int i8 = 0; i8 < FloatDHT_2D.this.rows; i8++) {
                            int access$0006 = (FloatDHT_2D.this.columns * i8) + (i8 * 2);
                            float[] fArr6 = fArr2;
                            fArr6[access$0006] = fArr[i8];
                            fArr6[access$0006 + 1] = fArr[FloatDHT_2D.this.rows + i8];
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
    private void ddxt2d_subth(int r19, pl.edu.icm.jlargearrays.FloatLargeArray r20, boolean r21) {
        /*
            r18 = this;
            r11 = r18
            java.lang.Class<org.jtransforms.dht.FloatDHT_2D> r12 = org.jtransforms.dht.FloatDHT_2D.class
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
            org.jtransforms.dht.FloatDHT_2D$14 r16 = new org.jtransforms.dht.FloatDHT_2D$14
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
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.dht.FloatDHT_2D.ddxt2d_subth(int, pl.edu.icm.jlargearrays.FloatLargeArray, boolean):void");
    }

    private void ddxt2d_subth(int i, float[][] fArr, boolean z) {
        Class<FloatDHT_2D> cls = FloatDHT_2D.class;
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
            final float[][] fArr2 = fArr;
            final boolean z2 = z;
            futureArr[i5] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    float[] fArr = new float[i6];
                    if (FloatDHT_2D.this.columns > 2) {
                        if (i7 == -1) {
                            int i = i8 * 4;
                            while (i < FloatDHT_2D.this.columns) {
                                for (int i2 = 0; i2 < FloatDHT_2D.this.rows; i2++) {
                                    int access$200 = FloatDHT_2D.this.rows + i2;
                                    float[] fArr2 = fArr2[i2];
                                    fArr[i2] = fArr2[i];
                                    fArr[access$200] = fArr2[i + 1];
                                    fArr[FloatDHT_2D.this.rows + access$200] = fArr2[i2][i + 2];
                                    fArr[access$200 + (FloatDHT_2D.this.rows * 2)] = fArr2[i2][i + 3];
                                }
                                FloatDHT_2D.this.dhtRows.forward(fArr, 0);
                                FloatDHT_2D.this.dhtRows.forward(fArr, FloatDHT_2D.this.rows);
                                FloatDHT_2D.this.dhtRows.forward(fArr, FloatDHT_2D.this.rows * 2);
                                FloatDHT_2D.this.dhtRows.forward(fArr, FloatDHT_2D.this.rows * 3);
                                for (int i3 = 0; i3 < FloatDHT_2D.this.rows; i3++) {
                                    int access$2002 = FloatDHT_2D.this.rows + i3;
                                    float[] fArr3 = fArr2[i3];
                                    fArr3[i] = fArr[i3];
                                    fArr3[i + 1] = fArr[access$2002];
                                    fArr3[i + 2] = fArr[FloatDHT_2D.this.rows + access$2002];
                                    fArr2[i3][i + 3] = fArr[access$2002 + (FloatDHT_2D.this.rows * 2)];
                                }
                                i += i9 * 4;
                            }
                            return;
                        }
                        int i4 = i8 * 4;
                        while (i4 < FloatDHT_2D.this.columns) {
                            for (int i5 = 0; i5 < FloatDHT_2D.this.rows; i5++) {
                                int access$2003 = FloatDHT_2D.this.rows + i5;
                                float[] fArr4 = fArr2[i5];
                                fArr[i5] = fArr4[i4];
                                fArr[access$2003] = fArr4[i4 + 1];
                                fArr[FloatDHT_2D.this.rows + access$2003] = fArr2[i5][i4 + 2];
                                fArr[access$2003 + (FloatDHT_2D.this.rows * 2)] = fArr2[i5][i4 + 3];
                            }
                            FloatDHT_2D.this.dhtRows.inverse(fArr, 0, z2);
                            FloatDHT_2D.this.dhtRows.inverse(fArr, FloatDHT_2D.this.rows, z2);
                            FloatDHT_2D.this.dhtRows.inverse(fArr, FloatDHT_2D.this.rows * 2, z2);
                            FloatDHT_2D.this.dhtRows.inverse(fArr, FloatDHT_2D.this.rows * 3, z2);
                            for (int i6 = 0; i6 < FloatDHT_2D.this.rows; i6++) {
                                int access$2004 = FloatDHT_2D.this.rows + i6;
                                float[] fArr5 = fArr2[i6];
                                fArr5[i4] = fArr[i6];
                                fArr5[i4 + 1] = fArr[access$2004];
                                fArr5[i4 + 2] = fArr[FloatDHT_2D.this.rows + access$2004];
                                fArr2[i6][i4 + 3] = fArr[access$2004 + (FloatDHT_2D.this.rows * 2)];
                            }
                            i4 += i9 * 4;
                        }
                    } else if (FloatDHT_2D.this.columns == 2) {
                        for (int i7 = 0; i7 < FloatDHT_2D.this.rows; i7++) {
                            fArr[i7] = fArr2[i7][i8 * 2];
                            fArr[FloatDHT_2D.this.rows + i7] = fArr2[i7][(i8 * 2) + 1];
                        }
                        if (i7 == -1) {
                            FloatDHT_2D.this.dhtRows.forward(fArr, 0);
                            FloatDHT_2D.this.dhtRows.forward(fArr, FloatDHT_2D.this.rows);
                        } else {
                            FloatDHT_2D.this.dhtRows.inverse(fArr, 0, z2);
                            FloatDHT_2D.this.dhtRows.inverse(fArr, FloatDHT_2D.this.rows, z2);
                        }
                        for (int i8 = 0; i8 < FloatDHT_2D.this.rows; i8++) {
                            float[] fArr6 = fArr2[i8];
                            int i9 = i8;
                            fArr6[i9 * 2] = fArr[i8];
                            fArr6[(i9 * 2) + 1] = fArr[FloatDHT_2D.this.rows + i8];
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

    private void ddxt2d0_subth(int i, float[] fArr, boolean z) {
        Class<FloatDHT_2D> cls = FloatDHT_2D.class;
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
            final float[] fArr2 = fArr;
            final boolean z2 = z;
            futureArr[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    if (i4 == -1) {
                        int i = i5;
                        while (i < FloatDHT_2D.this.rows) {
                            FloatDHT_2D.this.dhtColumns.forward(fArr2, FloatDHT_2D.this.columns * i);
                            i += i6;
                        }
                        return;
                    }
                    int i2 = i5;
                    while (i2 < FloatDHT_2D.this.rows) {
                        FloatDHT_2D.this.dhtColumns.inverse(fArr2, FloatDHT_2D.this.columns * i2, z2);
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

    private void ddxt2d0_subth(int i, FloatLargeArray floatLargeArray, boolean z) {
        Class<FloatDHT_2D> cls = FloatDHT_2D.class;
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
            final FloatLargeArray floatLargeArray2 = floatLargeArray;
            final boolean z2 = z;
            futureArr[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    if (i4 == -1) {
                        long j = j2;
                        while (j < FloatDHT_2D.this.rowsl) {
                            FloatDHT_2D.this.dhtColumns.forward(floatLargeArray2, FloatDHT_2D.this.columnsl * j);
                            j += (long) i5;
                        }
                        return;
                    }
                    long j2 = j2;
                    while (j2 < FloatDHT_2D.this.rowsl) {
                        FloatDHT_2D.this.dhtColumns.inverse(floatLargeArray2, FloatDHT_2D.this.columnsl * j2, z2);
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

    private void ddxt2d0_subth(int i, float[][] fArr, boolean z) {
        Class<FloatDHT_2D> cls = FloatDHT_2D.class;
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
            final float[][] fArr2 = fArr;
            final boolean z2 = z;
            futureArr[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    if (i4 == -1) {
                        int i = i5;
                        while (i < FloatDHT_2D.this.rows) {
                            FloatDHT_2D.this.dhtColumns.forward(fArr2[i]);
                            i += i6;
                        }
                        return;
                    }
                    int i2 = i5;
                    while (i2 < FloatDHT_2D.this.rows) {
                        FloatDHT_2D.this.dhtColumns.inverse(fArr2[i2], z2);
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

    private void ddxt2d_sub(int i, float[] fArr, boolean z) {
        int i2 = this.rows * 4;
        int i3 = this.columns;
        if (i3 == 2) {
            i2 >>= 1;
        } else if (i3 < 2) {
            i2 >>= 2;
        }
        float[] fArr2 = new float[i2];
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
                        fArr2[i6] = fArr[i8];
                        fArr2[i9] = fArr[i8 + 1];
                        fArr2[i9 + i7] = fArr[i8 + 2];
                        fArr2[i9 + (i7 * 2)] = fArr[i8 + 3];
                        i6++;
                    }
                    this.dhtRows.forward(fArr2, 0);
                    this.dhtRows.forward(fArr2, this.rows);
                    this.dhtRows.forward(fArr2, this.rows * 2);
                    this.dhtRows.forward(fArr2, this.rows * 3);
                    int i10 = 0;
                    while (true) {
                        int i11 = this.rows;
                        if (i10 >= i11) {
                            break;
                        }
                        int i12 = (this.columns * i10) + i5;
                        int i13 = i11 + i10;
                        fArr[i12] = fArr2[i10];
                        fArr[i12 + 1] = fArr2[i13];
                        fArr[i12 + 2] = fArr2[i13 + i11];
                        fArr[i12 + 3] = fArr2[i13 + (i11 * 2)];
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
                    fArr2[i15] = fArr[i17];
                    fArr2[i18] = fArr[i17 + 1];
                    fArr2[i18 + i16] = fArr[i17 + 2];
                    fArr2[i18 + (i16 * 2)] = fArr[i17 + 3];
                    i15++;
                }
                this.dhtRows.inverse(fArr2, 0, z);
                this.dhtRows.inverse(fArr2, this.rows, z);
                this.dhtRows.inverse(fArr2, this.rows * 2, z);
                this.dhtRows.inverse(fArr2, this.rows * 3, z);
                int i19 = 0;
                while (true) {
                    int i20 = this.rows;
                    if (i19 >= i20) {
                        break;
                    }
                    int i21 = (this.columns * i19) + i14;
                    int i22 = i20 + i19;
                    fArr[i21] = fArr2[i19];
                    fArr[i21 + 1] = fArr2[i22];
                    fArr[i21 + 2] = fArr2[i22 + i20];
                    fArr[i21 + 3] = fArr2[i22 + (i20 * 2)];
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
                fArr2[i23] = fArr[i25];
                fArr2[i24 + i23] = fArr[i25 + 1];
                i23++;
            }
            if (i == -1) {
                this.dhtRows.forward(fArr2, 0);
                this.dhtRows.forward(fArr2, this.rows);
            } else {
                this.dhtRows.inverse(fArr2, 0, z);
                this.dhtRows.inverse(fArr2, this.rows, z);
            }
            while (true) {
                int i26 = this.rows;
                if (i4 < i26) {
                    int i27 = this.columns * i4;
                    fArr[i27] = fArr2[i4];
                    fArr[i27 + 1] = fArr2[i26 + i4];
                    i4++;
                } else {
                    return;
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x01a2  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x002c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void ddxt2d_sub(int r24, pl.edu.icm.jlargearrays.FloatLargeArray r25, boolean r26) {
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
            pl.edu.icm.jlargearrays.FloatLargeArray r8 = new pl.edu.icm.jlargearrays.FloatLargeArray
            r8.<init>((long) r4)
            long r4 = r0.columnsl
            int r9 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            r12 = -1
            r13 = 1
            if (r9 <= 0) goto L_0x01a2
            if (r1 != r12) goto L_0x00e6
            r15 = 0
        L_0x0030:
            long r6 = r0.columnsl
            int r1 = (r15 > r6 ? 1 : (r15 == r6 ? 0 : -1))
            if (r1 >= 0) goto L_0x0208
            r6 = 0
        L_0x0038:
            long r4 = r0.rowsl
            int r1 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r1 >= 0) goto L_0x007a
            long r10 = r0.columnsl
            long r10 = r10 * r6
            long r10 = r10 + r15
            long r4 = r4 + r6
            float r1 = r2.getFloat(r10)
            r8.setFloat(r6, r1)
            r21 = r6
            long r6 = r10 + r13
            float r1 = r2.getFloat(r6)
            r8.setFloat(r4, r1)
            long r6 = r0.rowsl
            long r6 = r6 + r4
            r19 = 2
            long r13 = r10 + r19
            float r1 = r2.getFloat(r13)
            r8.setFloat(r6, r1)
            long r6 = r0.rowsl
            long r6 = r6 * r19
            long r4 = r4 + r6
            r6 = 3
            long r10 = r10 + r6
            float r1 = r2.getFloat(r10)
            r8.setFloat(r4, r1)
            r3 = 1
            long r6 = r21 + r3
            r13 = r3
            r10 = 2
            goto L_0x0038
        L_0x007a:
            org.jtransforms.dht.FloatDHT_1D r1 = r0.dhtRows
            r3 = 0
            r1.forward((pl.edu.icm.jlargearrays.FloatLargeArray) r8, (long) r3)
            org.jtransforms.dht.FloatDHT_1D r1 = r0.dhtRows
            long r3 = r0.rowsl
            r1.forward((pl.edu.icm.jlargearrays.FloatLargeArray) r8, (long) r3)
            org.jtransforms.dht.FloatDHT_1D r1 = r0.dhtRows
            long r3 = r0.rowsl
            r5 = 2
            long r3 = r3 * r5
            r1.forward((pl.edu.icm.jlargearrays.FloatLargeArray) r8, (long) r3)
            org.jtransforms.dht.FloatDHT_1D r1 = r0.dhtRows
            long r3 = r0.rowsl
            r5 = 3
            long r3 = r3 * r5
            r1.forward((pl.edu.icm.jlargearrays.FloatLargeArray) r8, (long) r3)
            r3 = 0
        L_0x009e:
            long r5 = r0.rowsl
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 >= 0) goto L_0x00dd
            long r9 = r0.columnsl
            long r9 = r9 * r3
            long r9 = r9 + r15
            long r5 = r5 + r3
            float r1 = r8.getFloat(r3)
            r2.setFloat(r9, r1)
            r11 = 1
            long r13 = r9 + r11
            float r1 = r8.getFloat(r5)
            r2.setFloat(r13, r1)
            r11 = 2
            long r13 = r9 + r11
            long r11 = r0.rowsl
            long r11 = r11 + r5
            float r1 = r8.getFloat(r11)
            r2.setFloat(r13, r1)
            r11 = 3
            long r9 = r9 + r11
            long r11 = r0.rowsl
            r13 = 2
            long r11 = r11 * r13
            long r5 = r5 + r11
            float r1 = r8.getFloat(r5)
            r2.setFloat(r9, r1)
            r5 = 1
            long r3 = r3 + r5
            goto L_0x009e
        L_0x00dd:
            r3 = 4
            long r15 = r15 + r3
            r10 = 2
            r13 = 1
            goto L_0x0030
        L_0x00e6:
            r4 = 0
        L_0x00e8:
            long r6 = r0.columnsl
            int r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r1 >= 0) goto L_0x0208
            r6 = 0
        L_0x00f0:
            long r9 = r0.rowsl
            int r1 = (r6 > r9 ? 1 : (r6 == r9 ? 0 : -1))
            if (r1 >= 0) goto L_0x0132
            long r11 = r0.columnsl
            long r11 = r11 * r6
            long r11 = r11 + r4
            long r9 = r9 + r6
            float r1 = r2.getFloat(r11)
            r8.setFloat(r6, r1)
            r15 = r4
            r13 = 1
            long r4 = r11 + r13
            float r1 = r2.getFloat(r4)
            r8.setFloat(r9, r1)
            long r4 = r0.rowsl
            long r4 = r4 + r9
            r21 = r6
            r13 = 2
            long r6 = r11 + r13
            float r1 = r2.getFloat(r6)
            r8.setFloat(r4, r1)
            long r4 = r0.rowsl
            long r4 = r4 * r13
            long r9 = r9 + r4
            r4 = 3
            long r11 = r11 + r4
            float r1 = r2.getFloat(r11)
            r8.setFloat(r9, r1)
            r4 = 1
            long r6 = r21 + r4
            r4 = r15
            goto L_0x00f0
        L_0x0132:
            r15 = r4
            org.jtransforms.dht.FloatDHT_1D r1 = r0.dhtRows
            r4 = 0
            r1.inverse((pl.edu.icm.jlargearrays.FloatLargeArray) r8, (long) r4, (boolean) r3)
            org.jtransforms.dht.FloatDHT_1D r1 = r0.dhtRows
            long r4 = r0.rowsl
            r1.inverse((pl.edu.icm.jlargearrays.FloatLargeArray) r8, (long) r4, (boolean) r3)
            org.jtransforms.dht.FloatDHT_1D r1 = r0.dhtRows
            long r4 = r0.rowsl
            r6 = 2
            long r4 = r4 * r6
            r1.inverse((pl.edu.icm.jlargearrays.FloatLargeArray) r8, (long) r4, (boolean) r3)
            org.jtransforms.dht.FloatDHT_1D r1 = r0.dhtRows
            long r4 = r0.rowsl
            r6 = 3
            long r4 = r4 * r6
            r1.inverse((pl.edu.icm.jlargearrays.FloatLargeArray) r8, (long) r4, (boolean) r3)
            r4 = 0
        L_0x0157:
            long r6 = r0.rowsl
            int r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r1 >= 0) goto L_0x0197
            long r9 = r0.columnsl
            long r9 = r9 * r4
            long r9 = r9 + r15
            long r6 = r6 + r4
            float r1 = r8.getFloat(r4)
            r2.setFloat(r9, r1)
            r11 = 1
            long r13 = r9 + r11
            float r1 = r8.getFloat(r6)
            r2.setFloat(r13, r1)
            r13 = 2
            long r11 = r9 + r13
            long r13 = r0.rowsl
            long r13 = r13 + r6
            float r1 = r8.getFloat(r13)
            r2.setFloat(r11, r1)
            r11 = 3
            long r9 = r9 + r11
            long r13 = r0.rowsl
            r17 = 2
            long r13 = r13 * r17
            long r6 = r6 + r13
            float r1 = r8.getFloat(r6)
            r2.setFloat(r9, r1)
            r6 = 1
            long r4 = r4 + r6
            goto L_0x0157
        L_0x0197:
            r4 = 4
            r11 = 3
            r17 = 2
            long r6 = r15 + r4
            r4 = r6
            goto L_0x00e8
        L_0x01a2:
            r17 = r10
            int r4 = (r4 > r17 ? 1 : (r4 == r17 ? 0 : -1))
            if (r4 != 0) goto L_0x0208
            r4 = 0
        L_0x01aa:
            long r6 = r0.rowsl
            int r6 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r6 >= 0) goto L_0x01c9
            long r6 = r0.columnsl
            long r6 = r6 * r4
            float r9 = r2.getFloat(r6)
            r8.setFloat(r4, r9)
            long r9 = r0.rowsl
            long r9 = r9 + r4
            r13 = 1
            long r6 = r6 + r13
            float r6 = r2.getFloat(r6)
            r8.setFloat(r9, r6)
            long r4 = r4 + r13
            goto L_0x01aa
        L_0x01c9:
            if (r1 != r12) goto L_0x01da
            org.jtransforms.dht.FloatDHT_1D r1 = r0.dhtRows
            r4 = 0
            r1.forward((pl.edu.icm.jlargearrays.FloatLargeArray) r8, (long) r4)
            org.jtransforms.dht.FloatDHT_1D r1 = r0.dhtRows
            long r6 = r0.rowsl
            r1.forward((pl.edu.icm.jlargearrays.FloatLargeArray) r8, (long) r6)
            goto L_0x01e8
        L_0x01da:
            r4 = 0
            org.jtransforms.dht.FloatDHT_1D r1 = r0.dhtRows
            r1.inverse((pl.edu.icm.jlargearrays.FloatLargeArray) r8, (long) r4, (boolean) r3)
            org.jtransforms.dht.FloatDHT_1D r1 = r0.dhtRows
            long r6 = r0.rowsl
            r1.inverse((pl.edu.icm.jlargearrays.FloatLargeArray) r8, (long) r6, (boolean) r3)
        L_0x01e8:
            r6 = r4
        L_0x01e9:
            long r3 = r0.rowsl
            int r1 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r1 >= 0) goto L_0x0208
            long r3 = r0.columnsl
            long r3 = r3 * r6
            float r1 = r8.getFloat(r6)
            r2.setFloat(r3, r1)
            r9 = 1
            long r3 = r3 + r9
            long r11 = r0.rowsl
            long r11 = r11 + r6
            float r1 = r8.getFloat(r11)
            r2.setFloat(r3, r1)
            long r6 = r6 + r9
            goto L_0x01e9
        L_0x0208:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.dht.FloatDHT_2D.ddxt2d_sub(int, pl.edu.icm.jlargearrays.FloatLargeArray, boolean):void");
    }

    private void ddxt2d_sub(int i, float[][] fArr, boolean z) {
        int i2 = this.rows * 4;
        int i3 = this.columns;
        if (i3 == 2) {
            i2 >>= 1;
        } else if (i3 < 2) {
            i2 >>= 2;
        }
        float[] fArr2 = new float[i2];
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
                        float[] fArr3 = fArr[i5];
                        fArr2[i5] = fArr3[i4];
                        fArr2[i7] = fArr3[i4 + 1];
                        fArr2[i7 + i6] = fArr3[i4 + 2];
                        fArr2[i7 + (i6 * 2)] = fArr3[i4 + 3];
                        i5++;
                    }
                    this.dhtRows.forward(fArr2, 0);
                    this.dhtRows.forward(fArr2, this.rows);
                    this.dhtRows.forward(fArr2, this.rows * 2);
                    this.dhtRows.forward(fArr2, this.rows * 3);
                    int i8 = 0;
                    while (true) {
                        int i9 = this.rows;
                        if (i8 >= i9) {
                            break;
                        }
                        int i10 = i9 + i8;
                        float[] fArr4 = fArr[i8];
                        fArr4[i4] = fArr2[i8];
                        fArr4[i4 + 1] = fArr2[i10];
                        fArr4[i4 + 2] = fArr2[i10 + i9];
                        fArr4[i4 + 3] = fArr2[i10 + (i9 * 2)];
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
                    float[] fArr5 = fArr[i12];
                    fArr2[i12] = fArr5[i11];
                    fArr2[i14] = fArr5[i11 + 1];
                    fArr2[i14 + i13] = fArr5[i11 + 2];
                    fArr2[i14 + (i13 * 2)] = fArr5[i11 + 3];
                    i12++;
                }
                this.dhtRows.inverse(fArr2, 0, z);
                this.dhtRows.inverse(fArr2, this.rows, z);
                this.dhtRows.inverse(fArr2, this.rows * 2, z);
                this.dhtRows.inverse(fArr2, this.rows * 3, z);
                int i15 = 0;
                while (true) {
                    int i16 = this.rows;
                    if (i15 >= i16) {
                        break;
                    }
                    int i17 = i16 + i15;
                    float[] fArr6 = fArr[i15];
                    fArr6[i11] = fArr2[i15];
                    fArr6[i11 + 1] = fArr2[i17];
                    fArr6[i11 + 2] = fArr2[i17 + i16];
                    fArr6[i11 + 3] = fArr2[i17 + (i16 * 2)];
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
                float[] fArr7 = fArr[i18];
                fArr2[i18] = fArr7[0];
                fArr2[i19 + i18] = fArr7[1];
                i18++;
            }
            if (i == -1) {
                this.dhtRows.forward(fArr2, 0);
                this.dhtRows.forward(fArr2, this.rows);
            } else {
                this.dhtRows.inverse(fArr2, 0, z);
                this.dhtRows.inverse(fArr2, this.rows, z);
            }
            int i20 = 0;
            while (true) {
                int i21 = this.rows;
                if (i20 < i21) {
                    float[] fArr8 = fArr[i20];
                    fArr8[0] = fArr2[i20];
                    fArr8[1] = fArr2[i21 + i20];
                    i20++;
                } else {
                    return;
                }
            }
        }
    }

    private void yTransform(float[] fArr) {
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
                    float f = fArr[i10];
                    int i11 = i6 + i7;
                    float f2 = fArr[i11];
                    int i12 = i5 + i9;
                    float f3 = fArr[i12];
                    int i13 = i9 + i6;
                    float f4 = fArr[i13];
                    float f5 = ((f + f4) - (f2 + f3)) / 2.0f;
                    fArr[i10] = f - f5;
                    fArr[i11] = f2 + f5;
                    fArr[i12] = f3 + f5;
                    fArr[i13] = f4 - f5;
                    i7++;
                }
                i++;
            } else {
                return;
            }
        }
    }

    private void yTransform(FloatLargeArray floatLargeArray) {
        FloatDHT_2D floatDHT_2D = this;
        FloatLargeArray floatLargeArray2 = floatLargeArray;
        long j = 0;
        while (true) {
            long j2 = floatDHT_2D.rowsl;
            long j3 = 2;
            if (j <= j2 / 2) {
                long j4 = (j2 - j) % j2;
                long j5 = floatDHT_2D.columnsl;
                long j6 = j * j5;
                long j7 = j4 * j5;
                long j8 = 0;
                while (true) {
                    long j9 = floatDHT_2D.columnsl;
                    if (j8 > j9 / j3) {
                        break;
                    }
                    long j10 = (j9 - j8) % j9;
                    long j11 = j6 + j8;
                    float f = floatLargeArray2.getFloat(j11);
                    long j12 = j7 + j8;
                    float f2 = floatLargeArray2.getFloat(j12);
                    long j13 = j6 + j10;
                    float f3 = floatLargeArray2.getFloat(j13);
                    long j14 = j6;
                    long j15 = j7 + j10;
                    float f4 = floatLargeArray2.getFloat(j15);
                    float f5 = ((f + f4) - (f2 + f3)) / 2.0f;
                    floatLargeArray2.setFloat(j11, f - f5);
                    floatLargeArray2.setFloat(j12, f2 + f5);
                    floatLargeArray2.setFloat(j13, f3 + f5);
                    floatLargeArray2.setFloat(j15, f4 - f5);
                    j8++;
                    j3 = 2;
                    floatDHT_2D = this;
                    j6 = j14;
                }
                j++;
                floatDHT_2D = this;
            } else {
                return;
            }
        }
    }

    private void yTransform(float[][] fArr) {
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
                    float[] fArr2 = fArr[i];
                    float f = fArr2[i4];
                    float[] fArr3 = fArr[i3];
                    float f2 = fArr3[i4];
                    float f3 = fArr2[i6];
                    float f4 = fArr3[i6];
                    float f5 = ((f + f4) - (f2 + f3)) / 2.0f;
                    fArr2[i4] = f - f5;
                    fArr3[i4] = f2 + f5;
                    fArr2[i6] = f3 + f5;
                    fArr3[i6] = f4 - f5;
                    i4++;
                }
                i++;
            } else {
                return;
            }
        }
    }
}
