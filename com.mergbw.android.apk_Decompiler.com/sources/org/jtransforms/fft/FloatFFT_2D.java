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

public class FloatFFT_2D {
    /* access modifiers changed from: private */
    public int columns;
    /* access modifiers changed from: private */
    public long columnsl;
    /* access modifiers changed from: private */
    public FloatFFT_1D fftColumns;
    /* access modifiers changed from: private */
    public FloatFFT_1D fftRows;
    private boolean isPowerOfTwo = false;
    /* access modifiers changed from: private */
    public int rows;
    /* access modifiers changed from: private */
    public long rowsl;
    private boolean useThreads = false;

    public FloatFFT_2D(long j, long j2) {
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
        FloatFFT_1D floatFFT_1D = new FloatFFT_1D(j);
        this.fftRows = floatFFT_1D;
        if (j == j2) {
            this.fftColumns = floatFFT_1D;
        } else {
            this.fftColumns = new FloatFFT_1D(j2);
        }
    }

    public void complexForward(float[] fArr) {
        int i;
        int i2;
        Class<FloatFFT_2D> cls = FloatFFT_2D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = 0;
        if (this.isPowerOfTwo) {
            this.columns *= 2;
            if (numberOfThreads <= 1 || !this.useThreads) {
                while (i3 < this.rows) {
                    this.fftColumns.complexForward(fArr, this.columns * i3);
                    i3++;
                }
                cdft2d_sub(-1, fArr, true);
            } else {
                xdft2d0_subth1(0, -1, fArr, true);
                cdft2d_subth(-1, fArr, true);
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
                this.fftColumns.complexForward(fArr, i6 * i5);
                i6++;
            }
            float[] fArr2 = new float[(i * 2)];
            for (int i7 = 0; i7 < this.columns; i7++) {
                int i8 = i7 * 2;
                for (int i9 = 0; i9 < this.rows; i9++) {
                    int i10 = i9 * 2;
                    int i11 = (i9 * i5) + i8;
                    fArr2[i10] = fArr[i11];
                    fArr2[i10 + 1] = fArr[i11 + 1];
                }
                this.fftRows.complexForward(fArr2);
                for (int i12 = 0; i12 < this.rows; i12++) {
                    int i13 = i12 * 2;
                    int i14 = (i12 * i5) + i8;
                    fArr[i14] = fArr2[i13];
                    fArr[i14 + 1] = fArr2[i13 + 1];
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
            final float[] fArr3 = fArr;
            final int i19 = i5;
            futureArr[i16] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    for (int i = i17; i < i18; i++) {
                        FloatFFT_2D.this.fftColumns.complexForward(fArr3, i19 * i);
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
            final float[] fArr4 = fArr;
            futureArr[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    float[] fArr = new float[(FloatFFT_2D.this.rows * 2)];
                    for (int i = i21; i < i22; i++) {
                        int i2 = i * 2;
                        for (int i3 = 0; i3 < FloatFFT_2D.this.rows; i3++) {
                            int i4 = i3 * 2;
                            int i5 = (i23 * i3) + i2;
                            float[] fArr2 = fArr4;
                            fArr[i4] = fArr2[i5];
                            fArr[i4 + 1] = fArr2[i5 + 1];
                        }
                        FloatFFT_2D.this.fftRows.complexForward(fArr);
                        for (int i6 = 0; i6 < FloatFFT_2D.this.rows; i6++) {
                            int i7 = i6 * 2;
                            int i8 = (i23 * i6) + i2;
                            float[] fArr3 = fArr4;
                            fArr3[i8] = fArr[i7];
                            fArr3[i8 + 1] = fArr[i7 + 1];
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

    public void complexForward(FloatLargeArray floatLargeArray) {
        FloatFFT_2D floatFFT_2D = this;
        FloatLargeArray floatLargeArray2 = floatLargeArray;
        Class<FloatFFT_2D> cls = FloatFFT_2D.class;
        if (floatLargeArray.isLarge() || floatLargeArray.isConstant()) {
            int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
            int i = 0;
            long j = 2;
            if (floatFFT_2D.isPowerOfTwo) {
                floatFFT_2D.columnsl *= 2;
                if (numberOfThreads <= 1 || !floatFFT_2D.useThreads) {
                    while (true) {
                        long j2 = (long) i;
                        if (j2 >= floatFFT_2D.rowsl) {
                            break;
                        }
                        floatFFT_2D.fftColumns.complexForward(floatLargeArray2, j2 * floatFFT_2D.columnsl);
                        i++;
                    }
                    floatFFT_2D.cdft2d_sub(-1, floatLargeArray2, true);
                } else {
                    xdft2d0_subth1(0, -1, floatLargeArray, true);
                    floatFFT_2D.cdft2d_subth(-1, floatLargeArray2, true);
                }
                floatFFT_2D.columnsl /= 2;
                return;
            }
            long j3 = floatFFT_2D.columnsl;
            long j4 = j3 * 2;
            if (numberOfThreads > 1 && floatFFT_2D.useThreads) {
                long j5 = floatFFT_2D.rowsl;
                long j6 = (long) numberOfThreads;
                if (j5 >= j6 && j3 >= j6) {
                    Future[] futureArr = new Future[numberOfThreads];
                    long j7 = j5 / j6;
                    int i2 = 0;
                    while (i2 < numberOfThreads) {
                        final long j8 = ((long) i2) * j7;
                        long j9 = j6;
                        final long j10 = i2 == numberOfThreads + -1 ? floatFFT_2D.rowsl : j8 + j7;
                        final FloatLargeArray floatLargeArray3 = floatLargeArray;
                        Future[] futureArr2 = futureArr;
                        final long j11 = j4;
                        futureArr2[i2] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                for (long j = j8; j < j10; j++) {
                                    FloatFFT_2D.this.fftColumns.complexForward(floatLargeArray3, j11 * j);
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
                    long j13 = floatFFT_2D.columnsl / j12;
                    while (i < numberOfThreads) {
                        final long j14 = ((long) i) * j13;
                        final long j15 = i == numberOfThreads + -1 ? floatFFT_2D.columnsl : j14 + j13;
                        final long j16 = j4;
                        int i3 = numberOfThreads;
                        String str2 = str;
                        final FloatLargeArray floatLargeArray4 = floatLargeArray;
                        futureArr3[i] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                long j = 2;
                                FloatLargeArray floatLargeArray = new FloatLargeArray(FloatFFT_2D.this.rowsl * 2, false);
                                long j2 = j14;
                                while (j2 < j15) {
                                    long j3 = j2 * j;
                                    long j4 = 0;
                                    while (j4 < FloatFFT_2D.this.rowsl) {
                                        long j5 = j4 * j;
                                        long j6 = (j16 * j4) + j3;
                                        floatLargeArray.setDouble(j5, (double) floatLargeArray4.getFloat(j6));
                                        floatLargeArray.setDouble(j5 + 1, (double) floatLargeArray4.getFloat(j6 + 1));
                                        j4++;
                                        j2 = j2;
                                        j = 2;
                                    }
                                    long j7 = j2;
                                    FloatFFT_2D.this.fftRows.complexForward(floatLargeArray);
                                    for (long j8 = 0; j8 < FloatFFT_2D.this.rowsl; j8++) {
                                        long j9 = j8 * 2;
                                        long j10 = (j16 * j8) + j3;
                                        floatLargeArray4.setDouble(j10, (double) floatLargeArray.getFloat(j9));
                                        floatLargeArray4.setDouble(j10 + 1, (double) floatLargeArray.getFloat(j9 + 1));
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
            for (long j17 = 0; j17 < floatFFT_2D.rowsl; j17++) {
                floatFFT_2D.fftColumns.complexForward(floatLargeArray2, j17 * j4);
            }
            FloatLargeArray floatLargeArray5 = new FloatLargeArray(floatFFT_2D.rowsl * 2, false);
            long j18 = 0;
            while (j18 < floatFFT_2D.columnsl) {
                long j19 = j18 * j;
                long j20 = 0;
                while (j20 < floatFFT_2D.rowsl) {
                    long j21 = j20 * j;
                    long j22 = (j20 * j4) + j19;
                    floatLargeArray5.setDouble(j21, (double) floatLargeArray2.getFloat(j22));
                    floatLargeArray5.setDouble(j21 + 1, (double) floatLargeArray2.getFloat(j22 + 1));
                    j20++;
                    j18 = j18;
                    j = 2;
                }
                long j23 = j18;
                floatFFT_2D.fftRows.complexForward(floatLargeArray5);
                long j24 = 0;
                while (j24 < floatFFT_2D.rowsl) {
                    long j25 = j24 * 2;
                    long j26 = (j24 * j4) + j19;
                    floatLargeArray2.setDouble(j26, (double) floatLargeArray5.getFloat(j25));
                    floatLargeArray2.setDouble(j26 + 1, (double) floatLargeArray5.getFloat(j25 + 1));
                    j24++;
                    floatFFT_2D = this;
                }
                j18 = j23 + 1;
                floatFFT_2D = this;
                j = 2;
            }
            return;
        }
        floatFFT_2D.complexForward(floatLargeArray.getData());
    }

    public void complexForward(final float[][] fArr) {
        int i;
        int i2;
        Class<FloatFFT_2D> cls = FloatFFT_2D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = 0;
        if (this.isPowerOfTwo) {
            this.columns *= 2;
            if (numberOfThreads <= 1 || !this.useThreads) {
                while (i3 < this.rows) {
                    this.fftColumns.complexForward(fArr[i3]);
                    i3++;
                }
                cdft2d_sub(-1, fArr, true);
            } else {
                xdft2d0_subth1(0, -1, fArr, true);
                cdft2d_subth(-1, fArr, true);
            }
            this.columns /= 2;
        } else if (numberOfThreads <= 1 || !this.useThreads || (i2 = this.rows) < numberOfThreads || this.columns < numberOfThreads) {
            int i4 = 0;
            while (true) {
                i = this.rows;
                if (i4 >= i) {
                    break;
                }
                this.fftColumns.complexForward(fArr[i4]);
                i4++;
            }
            float[] fArr2 = new float[(i * 2)];
            for (int i5 = 0; i5 < this.columns; i5++) {
                int i6 = i5 * 2;
                for (int i7 = 0; i7 < this.rows; i7++) {
                    int i8 = i7 * 2;
                    float[] fArr3 = fArr[i7];
                    fArr2[i8] = fArr3[i6];
                    fArr2[i8 + 1] = fArr3[i6 + 1];
                }
                this.fftRows.complexForward(fArr2);
                for (int i9 = 0; i9 < this.rows; i9++) {
                    int i10 = i9 * 2;
                    float[] fArr4 = fArr[i9];
                    fArr4[i6] = fArr2[i10];
                    fArr4[i6 + 1] = fArr2[i10 + 1];
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
                            FloatFFT_2D.this.fftColumns.complexForward(fArr[i]);
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
                        float[] fArr = new float[(FloatFFT_2D.this.rows * 2)];
                        for (int i = i16; i < i17; i++) {
                            int i2 = i * 2;
                            for (int i3 = 0; i3 < FloatFFT_2D.this.rows; i3++) {
                                int i4 = i3 * 2;
                                float[] fArr2 = fArr[i3];
                                fArr[i4] = fArr2[i2];
                                fArr[i4 + 1] = fArr2[i2 + 1];
                            }
                            FloatFFT_2D.this.fftRows.complexForward(fArr);
                            for (int i5 = 0; i5 < FloatFFT_2D.this.rows; i5++) {
                                int i6 = i5 * 2;
                                float[] fArr3 = fArr[i5];
                                fArr3[i2] = fArr[i6];
                                fArr3[i2 + 1] = fArr[i6 + 1];
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

    public void complexInverse(float[] fArr, boolean z) {
        int i;
        int i2;
        float[] fArr2 = fArr;
        boolean z2 = z;
        Class<FloatFFT_2D> cls = FloatFFT_2D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = 0;
        if (this.isPowerOfTwo) {
            this.columns *= 2;
            if (numberOfThreads <= 1 || !this.useThreads) {
                while (i3 < this.rows) {
                    this.fftColumns.complexInverse(fArr2, this.columns * i3, z2);
                    i3++;
                }
                cdft2d_sub(1, fArr2, z2);
            } else {
                xdft2d0_subth1(0, 1, fArr2, z2);
                cdft2d_subth(1, fArr2, z2);
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
                this.fftColumns.complexInverse(fArr2, i6 * i5, z2);
                i6++;
            }
            float[] fArr3 = new float[(i * 2)];
            for (int i7 = 0; i7 < this.columns; i7++) {
                int i8 = i7 * 2;
                for (int i9 = 0; i9 < this.rows; i9++) {
                    int i10 = i9 * 2;
                    int i11 = (i9 * i5) + i8;
                    fArr3[i10] = fArr2[i11];
                    fArr3[i10 + 1] = fArr2[i11 + 1];
                }
                this.fftRows.complexInverse(fArr3, z2);
                for (int i12 = 0; i12 < this.rows; i12++) {
                    int i13 = i12 * 2;
                    int i14 = (i12 * i5) + i8;
                    fArr2[i14] = fArr3[i13];
                    fArr2[i14 + 1] = fArr3[i13 + 1];
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
            final float[] fArr4 = fArr;
            final int i19 = i5;
            int i20 = i16;
            final boolean z3 = z;
            futureArr[i20] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    for (int i = i17; i < i18; i++) {
                        FloatFFT_2D.this.fftColumns.complexInverse(fArr4, i19 * i, z3);
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
            final float[] fArr5 = fArr;
            int i25 = numberOfThreads;
            String str2 = str;
            final boolean z4 = z;
            futureArr[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    float[] fArr = new float[(FloatFFT_2D.this.rows * 2)];
                    for (int i = i22; i < i23; i++) {
                        int i2 = i * 2;
                        for (int i3 = 0; i3 < FloatFFT_2D.this.rows; i3++) {
                            int i4 = i3 * 2;
                            int i5 = (i24 * i3) + i2;
                            float[] fArr2 = fArr5;
                            fArr[i4] = fArr2[i5];
                            fArr[i4 + 1] = fArr2[i5 + 1];
                        }
                        FloatFFT_2D.this.fftRows.complexInverse(fArr, z4);
                        for (int i6 = 0; i6 < FloatFFT_2D.this.rows; i6++) {
                            int i7 = i6 * 2;
                            int i8 = (i24 * i6) + i2;
                            float[] fArr3 = fArr5;
                            fArr3[i8] = fArr[i7];
                            fArr3[i8 + 1] = fArr[i7 + 1];
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

    public void complexInverse(FloatLargeArray floatLargeArray, boolean z) {
        FloatLargeArray floatLargeArray2 = floatLargeArray;
        boolean z2 = z;
        Class<FloatFFT_2D> cls = FloatFFT_2D.class;
        if (floatLargeArray.isLarge() || floatLargeArray.isConstant()) {
            int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
            long j = 2;
            if (this.isPowerOfTwo) {
                this.columnsl *= 2;
                if (numberOfThreads <= 1 || !this.useThreads) {
                    for (long j2 = 0; j2 < this.rowsl; j2++) {
                        this.fftColumns.complexInverse(floatLargeArray2, this.columnsl * j2, z2);
                    }
                    cdft2d_sub(1, floatLargeArray2, z2);
                } else {
                    xdft2d0_subth1(0, 1, floatLargeArray, z);
                    cdft2d_subth(1, floatLargeArray2, z2);
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
                        final FloatLargeArray floatLargeArray3 = floatLargeArray;
                        Future[] futureArr2 = futureArr;
                        final long j10 = j4;
                        final boolean z3 = z;
                        futureArr2[i] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                for (long j = j8; j < j9; j++) {
                                    FloatFFT_2D.this.fftColumns.complexInverse(floatLargeArray3, j10 * j, z3);
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
                        final FloatLargeArray floatLargeArray4 = floatLargeArray;
                        long j15 = j11;
                        String str2 = str;
                        final boolean z4 = z;
                        futureArr3[i2] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                long j = 2;
                                FloatLargeArray floatLargeArray = new FloatLargeArray(FloatFFT_2D.this.rowsl * 2, false);
                                long j2 = j12;
                                while (j2 < j13) {
                                    long j3 = j2 * j;
                                    long j4 = 0;
                                    while (j4 < FloatFFT_2D.this.rowsl) {
                                        long j5 = j4 * j;
                                        long j6 = (j14 * j4) + j3;
                                        floatLargeArray.setDouble(j5, (double) floatLargeArray4.getFloat(j6));
                                        floatLargeArray.setDouble(j5 + 1, (double) floatLargeArray4.getFloat(j6 + 1));
                                        j4++;
                                        j2 = j2;
                                        j = 2;
                                    }
                                    long j7 = j2;
                                    FloatFFT_2D.this.fftRows.complexInverse(floatLargeArray, z4);
                                    for (long j8 = 0; j8 < FloatFFT_2D.this.rowsl; j8++) {
                                        long j9 = j8 * 2;
                                        long j10 = (j14 * j8) + j3;
                                        floatLargeArray4.setDouble(j10, (double) floatLargeArray.getFloat(j9));
                                        floatLargeArray4.setDouble(j10 + 1, (double) floatLargeArray.getFloat(j9 + 1));
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
                this.fftColumns.complexInverse(floatLargeArray, j16 * j4, z);
            }
            FloatLargeArray floatLargeArray5 = floatLargeArray;
            boolean z5 = z;
            FloatLargeArray floatLargeArray6 = new FloatLargeArray(this.rowsl * 2);
            long j17 = 0;
            while (j17 < this.columnsl) {
                long j18 = j17 * j;
                long j19 = 0;
                while (j19 < this.rowsl) {
                    long j20 = j19 * j;
                    long j21 = (j19 * j4) + j18;
                    floatLargeArray6.setDouble(j20, (double) floatLargeArray5.getFloat(j21));
                    floatLargeArray6.setDouble(j20 + 1, (double) floatLargeArray5.getFloat(j21 + 1));
                    j19++;
                    j = 2;
                }
                this.fftRows.complexInverse(floatLargeArray6, z5);
                for (long j22 = 0; j22 < this.rowsl; j22++) {
                    long j23 = j22 * 2;
                    long j24 = (j22 * j4) + j18;
                    floatLargeArray5.setDouble(j24, (double) floatLargeArray6.getFloat(j23));
                    floatLargeArray5.setDouble(j24 + 1, (double) floatLargeArray6.getFloat(j23 + 1));
                }
                j17++;
                j = 2;
            }
            return;
        }
        complexInverse(floatLargeArray.getData(), z2);
    }

    public void complexInverse(float[][] fArr, boolean z) {
        int i;
        int i2;
        Class<FloatFFT_2D> cls = FloatFFT_2D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = 0;
        if (this.isPowerOfTwo) {
            this.columns *= 2;
            if (numberOfThreads <= 1 || !this.useThreads) {
                while (i3 < this.rows) {
                    this.fftColumns.complexInverse(fArr[i3], z);
                    i3++;
                }
                cdft2d_sub(1, fArr, z);
            } else {
                xdft2d0_subth1(0, 1, fArr, z);
                cdft2d_subth(1, fArr, z);
            }
            this.columns /= 2;
        } else if (numberOfThreads <= 1 || !this.useThreads || (i2 = this.rows) < numberOfThreads || this.columns < numberOfThreads) {
            int i4 = 0;
            while (true) {
                i = this.rows;
                if (i4 >= i) {
                    break;
                }
                this.fftColumns.complexInverse(fArr[i4], z);
                i4++;
            }
            float[] fArr2 = new float[(i * 2)];
            for (int i5 = 0; i5 < this.columns; i5++) {
                int i6 = i5 * 2;
                for (int i7 = 0; i7 < this.rows; i7++) {
                    int i8 = i7 * 2;
                    float[] fArr3 = fArr[i7];
                    fArr2[i8] = fArr3[i6];
                    fArr2[i8 + 1] = fArr3[i6 + 1];
                }
                this.fftRows.complexInverse(fArr2, z);
                for (int i9 = 0; i9 < this.rows; i9++) {
                    int i10 = i9 * 2;
                    float[] fArr4 = fArr[i9];
                    fArr4[i6] = fArr2[i10];
                    fArr4[i6 + 1] = fArr2[i10 + 1];
                }
            }
        } else {
            Future[] futureArr = new Future[numberOfThreads];
            int i11 = i2 / numberOfThreads;
            int i12 = 0;
            while (i12 < numberOfThreads) {
                final int i13 = i12 * i11;
                final int i14 = i12 == numberOfThreads + -1 ? this.rows : i13 + i11;
                final float[][] fArr5 = fArr;
                final boolean z2 = z;
                futureArr[i12] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i13; i < i14; i++) {
                            FloatFFT_2D.this.fftColumns.complexInverse(fArr5[i], z2);
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
                final float[][] fArr6 = fArr;
                final boolean z3 = z;
                futureArr[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        float[] fArr = new float[(FloatFFT_2D.this.rows * 2)];
                        for (int i = i16; i < i17; i++) {
                            int i2 = i * 2;
                            for (int i3 = 0; i3 < FloatFFT_2D.this.rows; i3++) {
                                int i4 = i3 * 2;
                                float[] fArr2 = fArr6[i3];
                                fArr[i4] = fArr2[i2];
                                fArr[i4 + 1] = fArr2[i2 + 1];
                            }
                            FloatFFT_2D.this.fftRows.complexInverse(fArr, z3);
                            for (int i5 = 0; i5 < FloatFFT_2D.this.rows; i5++) {
                                int i6 = i5 * 2;
                                float[] fArr3 = fArr6[i5];
                                fArr3[i2] = fArr[i6];
                                fArr3[i2 + 1] = fArr[i6 + 1];
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

    public void realForward(float[] fArr) {
        if (!this.isPowerOfTwo) {
            throw new IllegalArgumentException("rows and columns must be power of two numbers");
        } else if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
            for (int i = 0; i < this.rows; i++) {
                this.fftColumns.realForward(fArr, this.columns * i);
            }
            cdft2d_sub(-1, fArr, true);
            rdft2d_sub(1, fArr);
        } else {
            xdft2d0_subth1(1, 1, fArr, true);
            cdft2d_subth(-1, fArr, true);
            rdft2d_sub(1, fArr);
        }
    }

    public void realForward(FloatLargeArray floatLargeArray) {
        if (!this.isPowerOfTwo) {
            throw new IllegalArgumentException("rows and columns must be power of two numbers");
        } else if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
            for (long j = 0; j < this.rowsl; j++) {
                this.fftColumns.realForward(floatLargeArray, this.columnsl * j);
            }
            cdft2d_sub(-1, floatLargeArray, true);
            rdft2d_sub(1, floatLargeArray);
        } else {
            xdft2d0_subth1(1, 1, floatLargeArray, true);
            cdft2d_subth(-1, floatLargeArray, true);
            rdft2d_sub(1, floatLargeArray);
        }
    }

    public void realForward(float[][] fArr) {
        if (!this.isPowerOfTwo) {
            throw new IllegalArgumentException("rows and columns must be power of two numbers");
        } else if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
            for (int i = 0; i < this.rows; i++) {
                this.fftColumns.realForward(fArr[i]);
            }
            cdft2d_sub(-1, fArr, true);
            rdft2d_sub(1, fArr);
        } else {
            xdft2d0_subth1(1, 1, fArr, true);
            cdft2d_subth(-1, fArr, true);
            rdft2d_sub(1, fArr);
        }
    }

    public void realForwardFull(float[] fArr) {
        if (this.isPowerOfTwo) {
            if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
                for (int i = 0; i < this.rows; i++) {
                    this.fftColumns.realForward(fArr, this.columns * i);
                }
                cdft2d_sub(-1, fArr, true);
                rdft2d_sub(1, fArr);
            } else {
                xdft2d0_subth1(1, 1, fArr, true);
                cdft2d_subth(-1, fArr, true);
                rdft2d_sub(1, fArr);
            }
            fillSymmetric(fArr);
            return;
        }
        mixedRadixRealForwardFull(fArr);
    }

    public void realForwardFull(FloatLargeArray floatLargeArray) {
        if (this.isPowerOfTwo) {
            if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
                for (long j = 0; j < this.rowsl; j++) {
                    this.fftColumns.realForward(floatLargeArray, this.columnsl * j);
                }
                cdft2d_sub(-1, floatLargeArray, true);
                rdft2d_sub(1, floatLargeArray);
            } else {
                xdft2d0_subth1(1, 1, floatLargeArray, true);
                cdft2d_subth(-1, floatLargeArray, true);
                rdft2d_sub(1, floatLargeArray);
            }
            fillSymmetric(floatLargeArray);
            return;
        }
        mixedRadixRealForwardFull(floatLargeArray);
    }

    public void realForwardFull(float[][] fArr) {
        if (this.isPowerOfTwo) {
            if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
                for (int i = 0; i < this.rows; i++) {
                    this.fftColumns.realForward(fArr[i]);
                }
                cdft2d_sub(-1, fArr, true);
                rdft2d_sub(1, fArr);
            } else {
                xdft2d0_subth1(1, 1, fArr, true);
                cdft2d_subth(-1, fArr, true);
                rdft2d_sub(1, fArr);
            }
            fillSymmetric(fArr);
            return;
        }
        mixedRadixRealForwardFull(fArr);
    }

    public void realInverse(float[] fArr, boolean z) {
        if (!this.isPowerOfTwo) {
            throw new IllegalArgumentException("rows and columns must be power of two numbers");
        } else if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
            rdft2d_sub(-1, fArr);
            cdft2d_sub(1, fArr, z);
            for (int i = 0; i < this.rows; i++) {
                this.fftColumns.realInverse(fArr, this.columns * i, z);
            }
        } else {
            rdft2d_sub(-1, fArr);
            cdft2d_subth(1, fArr, z);
            xdft2d0_subth1(1, -1, fArr, z);
        }
    }

    public void realInverse(FloatLargeArray floatLargeArray, boolean z) {
        if (!this.isPowerOfTwo) {
            throw new IllegalArgumentException("rows and columns must be power of two numbers");
        } else if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
            rdft2d_sub(-1, floatLargeArray);
            cdft2d_sub(1, floatLargeArray, z);
            for (long j = 0; j < this.rowsl; j++) {
                this.fftColumns.realInverse(floatLargeArray, this.columnsl * j, z);
            }
        } else {
            rdft2d_sub(-1, floatLargeArray);
            cdft2d_subth(1, floatLargeArray, z);
            xdft2d0_subth1(1, -1, floatLargeArray, z);
        }
    }

    public void realInverse(float[][] fArr, boolean z) {
        if (!this.isPowerOfTwo) {
            throw new IllegalArgumentException("rows and columns must be power of two numbers");
        } else if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
            rdft2d_sub(-1, fArr);
            cdft2d_sub(1, fArr, z);
            for (int i = 0; i < this.rows; i++) {
                this.fftColumns.realInverse(fArr[i], z);
            }
        } else {
            rdft2d_sub(-1, fArr);
            cdft2d_subth(1, fArr, z);
            xdft2d0_subth1(1, -1, fArr, z);
        }
    }

    public void realInverseFull(float[] fArr, boolean z) {
        if (this.isPowerOfTwo) {
            if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
                for (int i = 0; i < this.rows; i++) {
                    this.fftColumns.realInverse2(fArr, this.columns * i, z);
                }
                cdft2d_sub(1, fArr, z);
                rdft2d_sub(1, fArr);
            } else {
                xdft2d0_subth2(1, -1, fArr, z);
                cdft2d_subth(1, fArr, z);
                rdft2d_sub(1, fArr);
            }
            fillSymmetric(fArr);
            return;
        }
        mixedRadixRealInverseFull(fArr, z);
    }

    public void realInverseFull(FloatLargeArray floatLargeArray, boolean z) {
        if (this.isPowerOfTwo) {
            if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
                for (long j = 0; j < this.rowsl; j++) {
                    this.fftColumns.realInverse2(floatLargeArray, this.columnsl * j, z);
                }
                cdft2d_sub(1, floatLargeArray, z);
                rdft2d_sub(1, floatLargeArray);
            } else {
                xdft2d0_subth2(1, -1, floatLargeArray, z);
                cdft2d_subth(1, floatLargeArray, z);
                rdft2d_sub(1, floatLargeArray);
            }
            fillSymmetric(floatLargeArray);
            return;
        }
        mixedRadixRealInverseFull(floatLargeArray, z);
    }

    public void realInverseFull(float[][] fArr, boolean z) {
        if (this.isPowerOfTwo) {
            if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
                for (int i = 0; i < this.rows; i++) {
                    this.fftColumns.realInverse2(fArr[i], 0, z);
                }
                cdft2d_sub(1, fArr, z);
                rdft2d_sub(1, fArr);
            } else {
                xdft2d0_subth2(1, -1, fArr, z);
                cdft2d_subth(1, fArr, z);
                rdft2d_sub(1, fArr);
            }
            fillSymmetric(fArr);
            return;
        }
        mixedRadixRealInverseFull(fArr, z);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v0, resolved type: float[][]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void mixedRadixRealForwardFull(float[][] r21) {
        /*
            r20 = this;
            r8 = r20
            r9 = r21
            java.lang.Class<org.jtransforms.fft.FloatFFT_2D> r10 = org.jtransforms.fft.FloatFFT_2D.class
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
            java.lang.Class r0 = java.lang.Float.TYPE
            java.lang.Object r0 = java.lang.reflect.Array.newInstance(r0, r1)
            r15 = r0
            float[][] r15 = (float[][]) r15
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
            org.jtransforms.fft.FloatFFT_2D$13 r7 = new org.jtransforms.fft.FloatFFT_2D$13
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
            r3 = r3[r14]
            r2[r0] = r3
            int r0 = r0 + 1
            goto L_0x0078
        L_0x0087:
            org.jtransforms.fft.FloatFFT_1D r0 = r8.fftRows
            r2 = r15[r14]
            r0.realForwardFull((float[]) r2)
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
            org.jtransforms.fft.FloatFFT_2D$14 r18 = new org.jtransforms.fft.FloatFFT_2D$14
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
            r2 = r2[r13]
            r1[r0] = r2
            int r0 = r0 + 1
            goto L_0x00ef
        L_0x00fe:
            org.jtransforms.fft.FloatFFT_1D r0 = r8.fftRows
            r1 = r15[r11]
            r0.realForwardFull((float[]) r1)
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
            r3 = r3[r13]
            r2[r1] = r3
            int r0 = r0 + 1
            goto L_0x0107
        L_0x011f:
            org.jtransforms.fft.FloatFFT_1D r0 = r8.fftRows
            r1 = r15[r11]
            r0.complexForward((float[]) r1)
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
            org.jtransforms.fft.FloatFFT_2D$15 r16 = new org.jtransforms.fft.FloatFFT_2D$15
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
            org.jtransforms.fft.FloatFFT_2D$16 r7 = new org.jtransforms.fft.FloatFFT_2D$16
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
            org.jtransforms.fft.FloatFFT_1D r1 = r8.fftColumns
            r2 = r9[r0]
            r1.realForward((float[]) r2)
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
            r3 = r3[r1]
            r2[r0] = r3
            int r0 = r0 + 1
            goto L_0x01d3
        L_0x01e3:
            r1 = 0
            org.jtransforms.fft.FloatFFT_1D r0 = r8.fftRows
            r2 = r15[r1]
            r0.realForwardFull((float[]) r2)
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
            r5 = r5[r13]
            r4[r3] = r5
            int r2 = r2 + 1
            goto L_0x01f1
        L_0x020a:
            org.jtransforms.fft.FloatFFT_1D r1 = r8.fftRows
            r2 = r15[r0]
            r1.complexForward((float[]) r2)
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
            r2 = r2[r3]
            r0[r1] = r2
            int r1 = r1 + 1
            goto L_0x021b
        L_0x022b:
            org.jtransforms.fft.FloatFFT_1D r0 = r8.fftRows
            r1 = r15[r11]
            r0.realForwardFull((float[]) r1)
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
            r3 = r3[r4]
            r2[r0] = r3
            int r1 = r1 + 1
            goto L_0x0234
        L_0x024d:
            org.jtransforms.fft.FloatFFT_1D r0 = r8.fftRows
            r1 = r15[r11]
            r0.complexForward((float[]) r1)
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
            r5 = r5[r13]
            r4[r3] = r5
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
            r7 = r6[r2]
            r6[r3] = r7
            int r7 = r3 + 1
            int r10 = r2 + 1
            r11 = r6[r10]
            float r11 = -r11
            r6[r7] = r11
            r6 = r9[r13]
            r11 = r9[r0]
            r2 = r11[r2]
            r6[r3] = r2
            r2 = r11[r10]
            float r2 = -r2
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
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.FloatFFT_2D.mixedRadixRealForwardFull(float[][]):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v0, resolved type: float[][]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void mixedRadixRealForwardFull(float[] r22) {
        /*
            r21 = this;
            r9 = r21
            r10 = r22
            java.lang.Class<org.jtransforms.fft.FloatFFT_2D> r11 = org.jtransforms.fft.FloatFFT_2D.class
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
            java.lang.Class r0 = java.lang.Float.TYPE
            java.lang.Object r0 = java.lang.reflect.Array.newInstance(r0, r1)
            r16 = r0
            float[][] r16 = (float[][]) r16
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
            org.jtransforms.fft.FloatFFT_2D$17 r7 = new org.jtransforms.fft.FloatFFT_2D$17
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
            org.jtransforms.fft.FloatFFT_1D r0 = r9.fftRows
            r2 = r16[r15]
            r0.realForwardFull((float[]) r2)
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
            org.jtransforms.fft.FloatFFT_2D$18 r19 = new org.jtransforms.fft.FloatFFT_2D$18
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
            org.jtransforms.fft.FloatFFT_1D r0 = r9.fftRows
            r1 = r16[r8]
            r0.realForwardFull((float[]) r1)
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
            r2 = r10[r2]
            r3[r1] = r2
            int r0 = r0 + 1
            goto L_0x010e
        L_0x0129:
            org.jtransforms.fft.FloatFFT_1D r0 = r9.fftRows
            r1 = r16[r8]
            r0.complexForward((float[]) r1)
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
            org.jtransforms.fft.FloatFFT_2D$19 r19 = new org.jtransforms.fft.FloatFFT_2D$19
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
            org.jtransforms.fft.FloatFFT_2D$20 r8 = new org.jtransforms.fft.FloatFFT_2D$20
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
            org.jtransforms.fft.FloatFFT_1D r1 = r9.fftColumns
            int r2 = r9.columns
            int r2 = r2 * r0
            r1.realForward((float[]) r10, (int) r2)
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
            org.jtransforms.fft.FloatFFT_1D r0 = r9.fftRows
            r2 = r16[r1]
            r0.realForwardFull((float[]) r2)
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
            r7 = r10[r5]
            r6[r4] = r7
            r7 = 1
            int r4 = r4 + r7
            int r5 = r5 + r7
            r5 = r10[r5]
            r6[r4] = r5
            int r3 = r3 + 1
            goto L_0x0201
        L_0x021b:
            org.jtransforms.fft.FloatFFT_1D r2 = r9.fftRows
            r3 = r16[r0]
            r2.complexForward((float[]) r3)
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
            org.jtransforms.fft.FloatFFT_1D r0 = r9.fftRows
            r2 = r16[r8]
            r0.realForwardFull((float[]) r2)
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
            r3 = r10[r3]
            r4[r2] = r3
            int r0 = r0 + 1
            goto L_0x0247
        L_0x0263:
            org.jtransforms.fft.FloatFFT_1D r0 = r9.fftRows
            r2 = r16[r8]
            r0.complexForward((float[]) r2)
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
            r4 = r4[r14]
            r10[r5] = r4
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
            r4 = r10[r4]
            float r4 = -r4
            r10[r7] = r4
            int r4 = r1 + r5
            int r5 = r0 - r5
            r7 = r10[r5]
            r10[r4] = r7
            int r4 = r4 + r8
            int r5 = r5 + r8
            r5 = r10[r5]
            float r5 = -r5
            r10[r4] = r5
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
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.FloatFFT_2D.mixedRadixRealForwardFull(float[]):void");
    }

    private void mixedRadixRealForwardFull(FloatLargeArray floatLargeArray) {
        FloatFFT_2D floatFFT_2D = this;
        FloatLargeArray floatLargeArray2 = floatLargeArray;
        Class<FloatFFT_2D> cls = FloatFFT_2D.class;
        long j = floatFFT_2D.columnsl;
        long j2 = j * 2;
        long j3 = j / 2;
        long j4 = j3 + 1;
        FloatLargeArray floatLargeArray3 = new FloatLargeArray(j4 * 2 * floatFFT_2D.rowsl);
        long j5 = floatFFT_2D.rowsl * 2;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        long j6 = 0;
        if (numberOfThreads > 1 && floatFFT_2D.useThreads) {
            long j7 = floatFFT_2D.rowsl;
            long j8 = (long) numberOfThreads;
            if (j7 >= j8) {
                long j9 = j3 - 1;
                if (j9 >= j8) {
                    Future[] futureArr = new Future[numberOfThreads];
                    long j10 = j7 / j8;
                    int i = 0;
                    while (i < numberOfThreads) {
                        final long j11 = ((long) i) * j10;
                        final long j12 = i == numberOfThreads + -1 ? floatFFT_2D.rowsl : j11 + j10;
                        Future[] futureArr2 = futureArr;
                        final FloatLargeArray floatLargeArray4 = floatLargeArray;
                        futureArr2[i] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                for (long j = j11; j < j12; j++) {
                                    FloatFFT_2D.this.fftColumns.realForward(floatLargeArray4, FloatFFT_2D.this.columnsl * j);
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
                    for (long j13 = 0; j13 < floatFFT_2D.rowsl; j13++) {
                        floatLargeArray3.setDouble(j13, (double) floatLargeArray2.getFloat(floatFFT_2D.columnsl * j13));
                    }
                    floatFFT_2D.fftRows.realForwardFull(floatLargeArray3);
                    long j14 = j9 / j8;
                    int i2 = 0;
                    while (i2 < numberOfThreads) {
                        final long j15 = (((long) i2) * j14) + 1;
                        final long j16 = i2 == numberOfThreads + -1 ? j3 : j15 + j14;
                        String str2 = str;
                        final FloatLargeArray floatLargeArray5 = floatLargeArray3;
                        long j17 = j8;
                        final long j18 = j5;
                        int i3 = numberOfThreads;
                        final FloatLargeArray floatLargeArray6 = floatLargeArray;
                        futureArr3[i2] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                for (long j = j15; j < j16; j++) {
                                    long j2 = 2;
                                    long j3 = j * 2;
                                    long j4 = 0;
                                    while (j4 < FloatFFT_2D.this.rowsl) {
                                        long j5 = j4 * j2;
                                        long access$500 = (FloatFFT_2D.this.columnsl * j4) + j3;
                                        floatLargeArray5.setDouble((j18 * j) + j5, (double) floatLargeArray6.getFloat(access$500));
                                        floatLargeArray5.setDouble((j18 * j) + j5 + 1, (double) floatLargeArray6.getFloat(access$500 + 1));
                                        j4++;
                                        j2 = 2;
                                    }
                                    FloatFFT_2D.this.fftRows.complexForward(floatLargeArray5, j18 * j);
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
                    if (floatFFT_2D.columnsl % 2 == 0) {
                        while (j6 < floatFFT_2D.rowsl) {
                            floatLargeArray3.setDouble((j3 * j5) + j6, (double) floatLargeArray2.getFloat((floatFFT_2D.columnsl * j6) + 1));
                            j6++;
                        }
                        floatFFT_2D.fftRows.realForwardFull(floatLargeArray3, j3 * j5);
                    } else {
                        while (j6 < floatFFT_2D.rowsl) {
                            long j20 = floatFFT_2D.columnsl * j6;
                            long j21 = (j3 * j5) + (j6 * 2);
                            floatLargeArray3.setDouble(j21, (double) floatLargeArray2.getFloat((j3 * 2) + j20));
                            floatLargeArray3.setDouble(j21 + 1, (double) floatLargeArray2.getFloat(j20 + 1));
                            j6++;
                        }
                        floatFFT_2D.fftRows.complexForward(floatLargeArray3, j3 * j5);
                    }
                    long j22 = floatFFT_2D.rowsl / j19;
                    int i5 = 0;
                    while (i5 < i4) {
                        final long j23 = ((long) i5) * j22;
                        final long j24 = i5 == i4 + -1 ? floatFFT_2D.rowsl : j23 + j22;
                        final long j25 = j4;
                        final long j26 = j2;
                        String str4 = str3;
                        final FloatLargeArray floatLargeArray7 = floatLargeArray;
                        int i6 = i4;
                        final FloatLargeArray floatLargeArray8 = floatLargeArray3;
                        final long j27 = j5;
                        futureArr3[i5] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                for (long j = j23; j < j24; j++) {
                                    long j2 = 2;
                                    long j3 = j * 2;
                                    long j4 = 0;
                                    while (j4 < j25) {
                                        long j5 = (j26 * j) + (j4 * j2);
                                        floatLargeArray7.setDouble(j5, (double) floatLargeArray8.getFloat((j27 * j4) + j3));
                                        floatLargeArray7.setDouble(j5 + 1, (double) floatLargeArray8.getFloat((j27 * j4) + j3 + 1));
                                        j4++;
                                        j2 = 2;
                                    }
                                }
                            }
                        });
                        i5++;
                        FloatLargeArray floatLargeArray9 = floatLargeArray;
                        str3 = str4;
                        i4 = i6;
                        floatLargeArray3 = floatLargeArray3;
                        floatFFT_2D = this;
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
                        final FloatLargeArray floatLargeArray10 = floatLargeArray;
                        futureArr3[i8] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                long j = j28;
                                while (j < j29) {
                                    long j2 = j30 * j;
                                    long j3 = 1;
                                    long access$300 = ((FloatFFT_2D.this.rowsl - j) + 1) * j30;
                                    long j4 = j31;
                                    while (j4 < FloatFFT_2D.this.columnsl) {
                                        long j5 = j4 * 2;
                                        long access$500 = 2 * (FloatFFT_2D.this.columnsl - j4);
                                        FloatLargeArray floatLargeArray = floatLargeArray10;
                                        floatLargeArray.setDouble(j5, (double) floatLargeArray.getFloat(access$500));
                                        FloatLargeArray floatLargeArray2 = floatLargeArray10;
                                        long j6 = j;
                                        floatLargeArray2.setDouble(j5 + 1, (double) (-floatLargeArray2.getFloat(access$500 + 1)));
                                        long j7 = j2 + j5;
                                        long j8 = access$300 - j5;
                                        FloatLargeArray floatLargeArray3 = floatLargeArray10;
                                        floatLargeArray3.setDouble(j7, (double) floatLargeArray3.getFloat(j8));
                                        FloatLargeArray floatLargeArray4 = floatLargeArray10;
                                        floatLargeArray4.setDouble(j7 + 1, (double) (-floatLargeArray4.getFloat(j8 + 1)));
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
        FloatLargeArray floatLargeArray11 = floatLargeArray3;
        FloatFFT_2D floatFFT_2D2 = floatFFT_2D;
        for (long j32 = 0; j32 < floatFFT_2D2.rowsl; j32++) {
            floatFFT_2D2.fftColumns.realForward(floatLargeArray, floatFFT_2D2.columnsl * j32);
        }
        FloatLargeArray floatLargeArray12 = floatLargeArray;
        for (long j33 = 0; j33 < floatFFT_2D2.rowsl; j33++) {
            floatLargeArray11.setDouble(j33, (double) floatLargeArray12.getFloat(floatFFT_2D2.columnsl * j33));
        }
        FloatLargeArray floatLargeArray13 = floatLargeArray11;
        floatFFT_2D2.fftRows.realForwardFull(floatLargeArray13);
        for (long j34 = 1; j34 < j3; j34++) {
            long j35 = 2;
            long j36 = j34 * 2;
            long j37 = 0;
            while (j37 < floatFFT_2D2.rowsl) {
                long j38 = j37 * j35;
                long j39 = (floatFFT_2D2.columnsl * j37) + j36;
                long j40 = (j34 * j5) + j38;
                floatLargeArray13.setDouble(j40, (double) floatLargeArray12.getFloat(j39));
                floatLargeArray13.setDouble(j40 + 1, (double) floatLargeArray12.getFloat(j39 + 1));
                j37++;
                j35 = 2;
            }
            floatFFT_2D2.fftRows.complexForward(floatLargeArray13, j34 * j5);
        }
        if (floatFFT_2D2.columnsl % 2 == 0) {
            for (long j41 = 0; j41 < floatFFT_2D2.rowsl; j41++) {
                floatLargeArray13.setDouble((j3 * j5) + j41, (double) floatLargeArray12.getFloat((floatFFT_2D2.columnsl * j41) + 1));
            }
            floatFFT_2D2.fftRows.realForwardFull(floatLargeArray13, j3 * j5);
        } else {
            for (long j42 = 0; j42 < floatFFT_2D2.rowsl; j42++) {
                long j43 = floatFFT_2D2.columnsl * j42;
                long j44 = (j3 * j5) + (j42 * 2);
                floatLargeArray13.setDouble(j44, (double) floatLargeArray12.getFloat((j3 * 2) + j43));
                floatLargeArray13.setDouble(j44 + 1, (double) floatLargeArray12.getFloat(j43 + 1));
            }
            floatFFT_2D2.fftRows.complexForward(floatLargeArray13, j3 * j5);
        }
        for (long j45 = 0; j45 < floatFFT_2D2.rowsl; j45++) {
            long j46 = 2;
            long j47 = j45 * 2;
            long j48 = 0;
            while (j48 < j4) {
                long j49 = (j45 * j2) + (j48 * j46);
                long j50 = (j48 * j5) + j47;
                floatLargeArray12.setDouble(j49, (double) floatLargeArray13.getFloat(j50));
                floatLargeArray12.setDouble(j49 + 1, (double) floatLargeArray13.getFloat(j50 + 1));
                j48++;
                j46 = 2;
            }
        }
        long j51 = 1;
        while (true) {
            long j52 = floatFFT_2D2.rowsl;
            if (j51 < j52) {
                long j53 = j51 * j2;
                long j54 = ((j52 - j51) + 1) * j2;
                long j55 = j4;
                while (true) {
                    long j56 = floatFFT_2D2.columnsl;
                    if (j55 >= j56) {
                        break;
                    }
                    long j57 = j55 * 2;
                    long j58 = (j56 - j55) * 2;
                    floatLargeArray12.setDouble(j57, (double) floatLargeArray12.getFloat(j58));
                    floatLargeArray12.setDouble(j57 + 1, (double) (-floatLargeArray12.getFloat(j58 + 1)));
                    long j59 = j53 + j57;
                    long j60 = j54 - j57;
                    floatLargeArray12.setDouble(j59, (double) floatLargeArray12.getFloat(j60));
                    floatLargeArray12.setDouble(j59 + 1, (double) (-floatLargeArray12.getFloat(j60 + 1)));
                    j55++;
                    floatFFT_2D2 = this;
                }
                j51++;
                floatFFT_2D2 = this;
            } else {
                return;
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v0, resolved type: float[][]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void mixedRadixRealInverseFull(float[][] r23, boolean r24) {
        /*
            r22 = this;
            r8 = r22
            r9 = r24
            java.lang.Class<org.jtransforms.fft.FloatFFT_2D> r10 = org.jtransforms.fft.FloatFFT_2D.class
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
            java.lang.Class r0 = java.lang.Float.TYPE
            java.lang.Object r0 = java.lang.reflect.Array.newInstance(r0, r1)
            r16 = r0
            float[][] r16 = (float[][]) r16
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
            org.jtransforms.fft.FloatFFT_2D$25 r18 = new org.jtransforms.fft.FloatFFT_2D$25
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
            r2 = r2[r15]
            r1[r0] = r2
            int r0 = r0 + 1
            goto L_0x0092
        L_0x00a1:
            org.jtransforms.fft.FloatFFT_1D r0 = r8.fftRows
            r1 = r16[r15]
            r0.realInverseFull((float[]) r1, (boolean) r9)
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
            org.jtransforms.fft.FloatFFT_2D$26 r18 = new org.jtransforms.fft.FloatFFT_2D$26
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
            r2 = r2[r14]
            r1[r0] = r2
            int r0 = r0 + 1
            goto L_0x0107
        L_0x0116:
            org.jtransforms.fft.FloatFFT_1D r0 = r8.fftRows
            r1 = r16[r12]
            r0.realInverseFull((float[]) r1, (boolean) r9)
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
            r3 = r3[r14]
            r2[r1] = r3
            int r0 = r0 + 1
            goto L_0x011f
        L_0x0137:
            org.jtransforms.fft.FloatFFT_1D r0 = r8.fftRows
            r1 = r16[r12]
            r0.complexInverse((float[]) r1, (boolean) r9)
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
            org.jtransforms.fft.FloatFFT_2D$27 r12 = new org.jtransforms.fft.FloatFFT_2D$27
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
            org.jtransforms.fft.FloatFFT_2D$28 r7 = new org.jtransforms.fft.FloatFFT_2D$28
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
            org.jtransforms.fft.FloatFFT_1D r1 = r8.fftColumns
            r2 = r23[r0]
            r3 = 0
            r1.realInverse2((float[]) r2, (int) r3, (boolean) r9)
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
            r2 = r2[r3]
            r1[r0] = r2
            int r0 = r0 + 1
            goto L_0x01ea
        L_0x01f9:
            org.jtransforms.fft.FloatFFT_1D r0 = r8.fftRows
            r1 = r16[r3]
            r0.realInverseFull((float[]) r1, (boolean) r9)
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
            r5 = r5[r6]
            r4[r2] = r5
            int r3 = r3 + 1
            goto L_0x0206
        L_0x021e:
            org.jtransforms.fft.FloatFFT_1D r1 = r8.fftRows
            r2 = r16[r0]
            r1.complexInverse((float[]) r2, (boolean) r9)
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
            r1 = r1[r14]
            r0[r3] = r1
            int r3 = r3 + 1
            goto L_0x022e
        L_0x023d:
            org.jtransforms.fft.FloatFFT_1D r0 = r8.fftRows
            r1 = r16[r12]
            r0.realInverseFull((float[]) r1, (boolean) r9)
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
            r2 = r2[r14]
            r1[r0] = r2
            int r3 = r3 + 1
            goto L_0x0246
        L_0x025e:
            org.jtransforms.fft.FloatFFT_1D r0 = r8.fftRows
            r1 = r16[r12]
            r0.complexInverse((float[]) r1, (boolean) r9)
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
            r5 = r5[r6]
            r4[r2] = r5
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
            float r9 = -r9
            r5[r6] = r9
            r5 = r23[r14]
            r9 = r23[r0]
            r2 = r9[r2]
            r5[r3] = r2
            r2 = r9[r7]
            float r2 = -r2
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
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.FloatFFT_2D.mixedRadixRealInverseFull(float[][], boolean):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v0, resolved type: float[][]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void mixedRadixRealInverseFull(float[] r24, boolean r25) {
        /*
            r23 = this;
            r9 = r23
            r10 = r24
            r8 = r25
            java.lang.Class<org.jtransforms.fft.FloatFFT_2D> r11 = org.jtransforms.fft.FloatFFT_2D.class
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
            java.lang.Class r0 = java.lang.Float.TYPE
            java.lang.Object r0 = java.lang.reflect.Array.newInstance(r0, r1)
            r17 = r0
            float[][] r17 = (float[][]) r17
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
            org.jtransforms.fft.FloatFFT_2D$29 r20 = new org.jtransforms.fft.FloatFFT_2D$29
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
            org.jtransforms.fft.FloatFFT_1D r0 = r9.fftRows
            r1 = r17[r16]
            r0.realInverseFull((float[]) r1, (boolean) r8)
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
            org.jtransforms.fft.FloatFFT_2D$30 r19 = new org.jtransforms.fft.FloatFFT_2D$30
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
            org.jtransforms.fft.FloatFFT_1D r0 = r9.fftRows
            r1 = r17[r14]
            r0.realInverseFull((float[]) r1, (boolean) r8)
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
            r2 = r10[r2]
            r3[r1] = r2
            int r0 = r0 + 1
            goto L_0x0139
        L_0x0156:
            org.jtransforms.fft.FloatFFT_1D r0 = r9.fftRows
            r1 = r17[r14]
            r0.complexInverse((float[]) r1, (boolean) r8)
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
            org.jtransforms.fft.FloatFFT_2D$31 r19 = new org.jtransforms.fft.FloatFFT_2D$31
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
            org.jtransforms.fft.FloatFFT_2D$32 r8 = new org.jtransforms.fft.FloatFFT_2D$32
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
            org.jtransforms.fft.FloatFFT_1D r1 = r9.fftColumns
            int r2 = r9.columns
            int r2 = r2 * r0
            r1.realInverse2((float[]) r10, (int) r2, (boolean) r8)
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
            org.jtransforms.fft.FloatFFT_1D r0 = r9.fftRows
            r1 = r17[r16]
            r0.realInverseFull((float[]) r1, (boolean) r8)
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
            r3 = r10[r3]
            r4[r2] = r3
            int r1 = r1 + 1
            goto L_0x0232
        L_0x024d:
            org.jtransforms.fft.FloatFFT_1D r0 = r9.fftRows
            r1 = r17[r7]
            r0.complexInverse((float[]) r1, (boolean) r8)
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
            org.jtransforms.fft.FloatFFT_1D r0 = r9.fftRows
            r1 = r17[r14]
            r0.realInverseFull((float[]) r1, (boolean) r8)
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
            r2 = r10[r2]
            r3[r1] = r2
            int r0 = r0 + 1
            goto L_0x027b
        L_0x0298:
            org.jtransforms.fft.FloatFFT_1D r0 = r9.fftRows
            r1 = r17[r14]
            r0.complexInverse((float[]) r1, (boolean) r8)
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
            r3 = r3[r7]
            r10[r12] = r3
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
            r2 = r10[r2]
            float r2 = -r2
            r10[r5] = r2
            int r2 = r12 + r3
            int r3 = r0 - r3
            r5 = r10[r3]
            r10[r2] = r5
            int r2 = r2 + 1
            int r3 = r3 + 1
            r3 = r10[r3]
            float r3 = -r3
            r10[r2] = r3
            int r1 = r1 + 1
            goto L_0x02d2
        L_0x02fc:
            r4 = 2
            int r7 = r7 + 1
            goto L_0x02c6
        L_0x0300:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.FloatFFT_2D.mixedRadixRealInverseFull(float[], boolean):void");
    }

    private void mixedRadixRealInverseFull(FloatLargeArray floatLargeArray, boolean z) {
        FloatFFT_2D floatFFT_2D = this;
        FloatLargeArray floatLargeArray2 = floatLargeArray;
        boolean z2 = z;
        Class<FloatFFT_2D> cls = FloatFFT_2D.class;
        long j = floatFFT_2D.columnsl;
        long j2 = j * 2;
        long j3 = j / 2;
        long j4 = j3 + 1;
        FloatLargeArray floatLargeArray3 = new FloatLargeArray(j4 * 2 * floatFFT_2D.rowsl);
        long j5 = floatFFT_2D.rowsl * 2;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        long j6 = 0;
        if (numberOfThreads > 1 && floatFFT_2D.useThreads) {
            long j7 = floatFFT_2D.rowsl;
            long j8 = (long) numberOfThreads;
            if (j7 >= j8) {
                long j9 = j3 - 1;
                if (j9 >= j8) {
                    Future[] futureArr = new Future[numberOfThreads];
                    long j10 = j7 / j8;
                    int i = 0;
                    while (i < numberOfThreads) {
                        final long j11 = ((long) i) * j10;
                        final long j12 = i == numberOfThreads + -1 ? floatFFT_2D.rowsl : j11 + j10;
                        final FloatLargeArray floatLargeArray4 = floatLargeArray;
                        Future[] futureArr2 = futureArr;
                        final boolean z3 = z;
                        futureArr2[i] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                for (long j = j11; j < j12; j++) {
                                    FloatFFT_2D.this.fftColumns.realInverse2(floatLargeArray4, FloatFFT_2D.this.columnsl * j, z3);
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
                    for (long j13 = 0; j13 < floatFFT_2D.rowsl; j13++) {
                        floatLargeArray3.setDouble(j13, (double) floatLargeArray2.getFloat(floatFFT_2D.columnsl * j13));
                    }
                    floatFFT_2D.fftRows.realInverseFull(floatLargeArray3, z2);
                    long j14 = j9 / j8;
                    int i2 = 0;
                    while (i2 < numberOfThreads) {
                        final long j15 = (((long) i2) * j14) + 1;
                        final long j16 = i2 == numberOfThreads + -1 ? j3 : j15 + j14;
                        final FloatLargeArray floatLargeArray5 = floatLargeArray3;
                        String str2 = str;
                        final long j17 = j5;
                        String str3 = str2;
                        final FloatLargeArray floatLargeArray6 = floatLargeArray;
                        int i3 = numberOfThreads;
                        final boolean z4 = z;
                        futureArr3[i2] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                for (long j = j15; j < j16; j++) {
                                    long j2 = 2;
                                    long j3 = j * 2;
                                    long j4 = 0;
                                    while (j4 < FloatFFT_2D.this.rowsl) {
                                        long j5 = j4 * j2;
                                        long access$500 = (FloatFFT_2D.this.columnsl * j4) + j3;
                                        floatLargeArray5.setDouble((j17 * j) + j5, (double) floatLargeArray6.getFloat(access$500));
                                        floatLargeArray5.setDouble((j17 * j) + j5 + 1, (double) floatLargeArray6.getFloat(access$500 + 1));
                                        j4++;
                                        j2 = 2;
                                    }
                                    FloatFFT_2D.this.fftRows.complexInverse(floatLargeArray5, j17 * j, z4);
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
                    if (floatFFT_2D.columnsl % 2 == 0) {
                        while (j6 < floatFFT_2D.rowsl) {
                            floatLargeArray3.setDouble((j3 * j5) + j6, (double) floatLargeArray2.getFloat((floatFFT_2D.columnsl * j6) + 1));
                            j6++;
                        }
                        floatFFT_2D.fftRows.realInverseFull(floatLargeArray3, j3 * j5, z);
                    } else {
                        boolean z6 = z;
                        while (j6 < floatFFT_2D.rowsl) {
                            long j19 = floatFFT_2D.columnsl * j6;
                            long j20 = (j3 * j5) + (j6 * 2);
                            floatLargeArray3.setDouble(j20, (double) floatLargeArray2.getFloat((j3 * 2) + j19));
                            floatLargeArray3.setDouble(j20 + 1, (double) floatLargeArray2.getFloat(j19 + 1));
                            j6++;
                        }
                        floatFFT_2D.fftRows.complexInverse(floatLargeArray3, j3 * j5, z6);
                    }
                    long j21 = floatFFT_2D.rowsl / j18;
                    int i5 = 0;
                    int i6 = i4;
                    while (i5 < i6) {
                        final long j22 = ((long) i5) * j21;
                        final long j23 = i5 == i6 + -1 ? floatFFT_2D.rowsl : j22 + j21;
                        final long j24 = j4;
                        final long j25 = j2;
                        int i7 = i6;
                        final FloatLargeArray floatLargeArray7 = floatLargeArray;
                        int i8 = i7;
                        String str5 = str4;
                        final FloatLargeArray floatLargeArray8 = floatLargeArray3;
                        final long j26 = j5;
                        futureArr3[i5] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                for (long j = j22; j < j23; j++) {
                                    long j2 = 2;
                                    long j3 = j * 2;
                                    long j4 = 0;
                                    while (j4 < j24) {
                                        long j5 = (j25 * j) + (j4 * j2);
                                        floatLargeArray7.setDouble(j5, (double) floatLargeArray8.getFloat((j26 * j4) + j3));
                                        floatLargeArray7.setDouble(j5 + 1, (double) floatLargeArray8.getFloat((j26 * j4) + j3 + 1));
                                        j4++;
                                        j2 = 2;
                                    }
                                }
                            }
                        });
                        i5++;
                        FloatLargeArray floatLargeArray9 = floatLargeArray;
                        str4 = str5;
                        i6 = i8;
                        floatLargeArray3 = floatLargeArray3;
                        floatFFT_2D = this;
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
                        final FloatLargeArray floatLargeArray10 = floatLargeArray;
                        futureArr3[i10] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                long j = j27;
                                while (j < j28) {
                                    long j2 = j29 * j;
                                    long j3 = 1;
                                    long access$300 = ((FloatFFT_2D.this.rowsl - j) + 1) * j29;
                                    long j4 = j30;
                                    while (j4 < FloatFFT_2D.this.columnsl) {
                                        long j5 = j4 * 2;
                                        long access$500 = 2 * (FloatFFT_2D.this.columnsl - j4);
                                        FloatLargeArray floatLargeArray = floatLargeArray10;
                                        floatLargeArray.setDouble(j5, (double) floatLargeArray.getFloat(access$500));
                                        FloatLargeArray floatLargeArray2 = floatLargeArray10;
                                        long j6 = j;
                                        floatLargeArray2.setDouble(j5 + 1, (double) (-floatLargeArray2.getFloat(access$500 + 1)));
                                        long j7 = j2 + j5;
                                        long j8 = access$300 - j5;
                                        FloatLargeArray floatLargeArray3 = floatLargeArray10;
                                        floatLargeArray3.setDouble(j7, (double) floatLargeArray3.getFloat(j8));
                                        FloatLargeArray floatLargeArray4 = floatLargeArray10;
                                        floatLargeArray4.setDouble(j7 + 1, (double) (-floatLargeArray4.getFloat(j8 + 1)));
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
        FloatLargeArray floatLargeArray11 = floatLargeArray3;
        FloatFFT_2D floatFFT_2D2 = floatFFT_2D;
        for (long j31 = 0; j31 < floatFFT_2D2.rowsl; j31++) {
            floatFFT_2D2.fftColumns.realInverse2(floatLargeArray, floatFFT_2D2.columnsl * j31, z7);
        }
        FloatLargeArray floatLargeArray12 = floatLargeArray;
        for (long j32 = 0; j32 < floatFFT_2D2.rowsl; j32++) {
            floatLargeArray11.setDouble(j32, (double) floatLargeArray12.getFloat(floatFFT_2D2.columnsl * j32));
        }
        FloatLargeArray floatLargeArray13 = floatLargeArray11;
        floatFFT_2D2.fftRows.realInverseFull(floatLargeArray13, z7);
        for (long j33 = 1; j33 < j3; j33++) {
            long j34 = j33 * 2;
            long j35 = 0;
            while (j35 < floatFFT_2D2.rowsl) {
                long j36 = (floatFFT_2D2.columnsl * j35) + j34;
                long j37 = (j35 * 2) + (j33 * j5);
                floatLargeArray13.setDouble(j37, (double) floatLargeArray12.getFloat(j36));
                floatLargeArray13.setDouble(j37 + 1, (double) floatLargeArray12.getFloat(j36 + 1));
                j35++;
                j34 = j34;
            }
            floatFFT_2D2.fftRows.complexInverse(floatLargeArray13, j33 * j5, z7);
        }
        if (floatFFT_2D2.columnsl % 2 == 0) {
            for (long j38 = 0; j38 < floatFFT_2D2.rowsl; j38++) {
                floatLargeArray13.setDouble((j3 * j5) + j38, (double) floatLargeArray12.getFloat((floatFFT_2D2.columnsl * j38) + 1));
            }
            floatFFT_2D2.fftRows.realInverseFull(floatLargeArray13, j3 * j5, z7);
        } else {
            for (long j39 = 0; j39 < floatFFT_2D2.rowsl; j39++) {
                long j40 = floatFFT_2D2.columnsl * j39;
                long j41 = (j3 * j5) + (j39 * 2);
                floatLargeArray13.setDouble(j41, (double) floatLargeArray12.getFloat((j3 * 2) + j40));
                floatLargeArray13.setDouble(j41 + 1, (double) floatLargeArray12.getFloat(j40 + 1));
            }
            floatFFT_2D2.fftRows.complexInverse(floatLargeArray13, j3 * j5, z7);
        }
        for (long j42 = 0; j42 < floatFFT_2D2.rowsl; j42++) {
            long j43 = j42 * 2;
            long j44 = 0;
            while (j44 < j4) {
                long j45 = (j42 * j2) + (j44 * 2);
                long j46 = (j44 * j5) + j43;
                floatLargeArray12.setDouble(j45, (double) floatLargeArray13.getFloat(j46));
                floatLargeArray12.setDouble(j45 + 1, (double) floatLargeArray13.getFloat(j46 + 1));
                j44++;
                j43 = j43;
            }
        }
        long j47 = 1;
        while (true) {
            long j48 = floatFFT_2D2.rowsl;
            if (j47 < j48) {
                long j49 = j47 * j2;
                long j50 = ((j48 - j47) + 1) * j2;
                long j51 = j4;
                while (true) {
                    long j52 = floatFFT_2D2.columnsl;
                    if (j51 >= j52) {
                        break;
                    }
                    long j53 = j51 * 2;
                    long j54 = (j52 - j51) * 2;
                    floatLargeArray12.setDouble(j53, (double) floatLargeArray12.getFloat(j54));
                    floatLargeArray12.setDouble(j53 + 1, (double) (-floatLargeArray12.getFloat(j54 + 1)));
                    long j55 = j49 + j53;
                    long j56 = j50 - j53;
                    floatLargeArray12.setDouble(j55, (double) floatLargeArray12.getFloat(j56));
                    floatLargeArray12.setDouble(j55 + 1, (double) (-floatLargeArray12.getFloat(j56 + 1)));
                    j51++;
                    floatFFT_2D2 = this;
                }
                j47++;
                floatFFT_2D2 = this;
            } else {
                return;
            }
        }
    }

    private void rdft2d_sub(int i, float[] fArr) {
        int i2 = this.rows >> 1;
        if (i < 0) {
            for (int i3 = 1; i3 < i2; i3++) {
                int i4 = this.columns;
                int i5 = i3 * i4;
                int i6 = (this.rows - i3) * i4;
                float f = fArr[i5];
                float f2 = fArr[i6];
                fArr[i5] = f + f2;
                fArr[i6] = f - f2;
                int i7 = i6 + 1;
                float f3 = fArr[i7];
                int i8 = i5 + 1;
                float f4 = fArr[i8];
                fArr[i8] = f4 + f3;
                fArr[i7] = f3 - f4;
            }
            return;
        }
        for (int i9 = 1; i9 < i2; i9++) {
            int i10 = this.columns;
            int i11 = i9 * i10;
            int i12 = (this.rows - i9) * i10;
            float f5 = (fArr[i11] - fArr[i12]) * 0.5f;
            fArr[i12] = f5;
            fArr[i11] = fArr[i11] - f5;
            int i13 = i12 + 1;
            int i14 = i11 + 1;
            float f6 = (fArr[i14] + fArr[i13]) * 0.5f;
            fArr[i13] = f6;
            fArr[i14] = fArr[i14] - f6;
        }
    }

    private void rdft2d_sub(int i, FloatLargeArray floatLargeArray) {
        FloatLargeArray floatLargeArray2 = floatLargeArray;
        long j = this.rowsl >> 1;
        if (i < 0) {
            for (long j2 = 1; j2 < j; j2++) {
                long j3 = this.columnsl;
                long j4 = j2 * j3;
                long j5 = (this.rowsl - j2) * j3;
                float f = floatLargeArray2.getFloat(j4) - floatLargeArray2.getFloat(j5);
                floatLargeArray2.setDouble(j4, (double) (floatLargeArray2.getFloat(j4) + floatLargeArray2.getFloat(j5)));
                floatLargeArray2.setDouble(j5, (double) f);
                long j6 = j5 + 1;
                long j7 = j4 + 1;
                float f2 = floatLargeArray2.getFloat(j6) - floatLargeArray2.getFloat(j7);
                floatLargeArray2.setDouble(j7, (double) (floatLargeArray2.getFloat(j7) + floatLargeArray2.getFloat(j6)));
                floatLargeArray2.setDouble(j6, (double) f2);
            }
            return;
        }
        for (long j8 = 1; j8 < j; j8++) {
            long j9 = this.columnsl;
            long j10 = j8 * j9;
            long j11 = (this.rowsl - j8) * j9;
            floatLargeArray2.setDouble(j11, (double) ((floatLargeArray2.getFloat(j10) - floatLargeArray2.getFloat(j11)) * 0.5f));
            floatLargeArray2.setDouble(j10, (double) (floatLargeArray2.getFloat(j10) - floatLargeArray2.getFloat(j11)));
            long j12 = j11 + 1;
            long j13 = j10 + 1;
            floatLargeArray2.setDouble(j12, (double) ((floatLargeArray2.getFloat(j13) + floatLargeArray2.getFloat(j12)) * 0.5f));
            floatLargeArray2.setDouble(j13, (double) (floatLargeArray2.getFloat(j13) - floatLargeArray2.getFloat(j12)));
        }
    }

    private void rdft2d_sub(int i, float[][] fArr) {
        int i2 = this.rows >> 1;
        if (i < 0) {
            for (int i3 = 1; i3 < i2; i3++) {
                float[] fArr2 = fArr[i3];
                float f = fArr2[0];
                float[] fArr3 = fArr[this.rows - i3];
                float f2 = fArr3[0];
                fArr2[0] = f + f2;
                fArr3[0] = f - f2;
                float f3 = fArr3[1];
                float f4 = fArr2[1];
                fArr2[1] = f4 + f3;
                fArr3[1] = f3 - f4;
            }
            return;
        }
        for (int i4 = 1; i4 < i2; i4++) {
            float[] fArr4 = fArr[this.rows - i4];
            float[] fArr5 = fArr[i4];
            float f5 = (fArr5[0] - fArr4[0]) * 0.5f;
            fArr4[0] = f5;
            fArr5[0] = fArr5[0] - f5;
            float f6 = (fArr5[1] + fArr4[1]) * 0.5f;
            fArr4[1] = f6;
            fArr5[1] = fArr5[1] - f6;
        }
    }

    private void cdft2d_sub(int i, float[] fArr, boolean z) {
        int i2 = this.rows * 8;
        int i3 = this.columns;
        if (i3 == 4) {
            i2 >>= 1;
        } else if (i3 < 4) {
            i2 >>= 2;
        }
        float[] fArr2 = new float[i2];
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
                        fArr2[i9] = fArr[i8];
                        fArr2[i9 + 1] = fArr[i8 + 1];
                        fArr2[i10] = fArr[i8 + 2];
                        fArr2[i10 + 1] = fArr[i8 + 3];
                        fArr2[i11] = fArr[i8 + 4];
                        fArr2[i11 + 1] = fArr[i8 + 5];
                        fArr2[i12] = fArr[i8 + 6];
                        fArr2[i12 + 1] = fArr[i8 + 7];
                        i6++;
                    }
                    this.fftRows.complexForward(fArr2, 0);
                    this.fftRows.complexForward(fArr2, this.rows * 2);
                    this.fftRows.complexForward(fArr2, this.rows * 4);
                    this.fftRows.complexForward(fArr2, this.rows * 6);
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
                        fArr[i15] = fArr2[i16];
                        fArr[i15 + 1] = fArr2[i16 + 1];
                        fArr[i15 + 2] = fArr2[i17];
                        fArr[i15 + 3] = fArr2[i17 + 1];
                        fArr[i15 + 4] = fArr2[i18];
                        fArr[i15 + 5] = fArr2[i18 + 1];
                        fArr[i15 + 6] = fArr2[i19];
                        fArr[i15 + 7] = fArr2[i19 + 1];
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
                    fArr2[i23] = fArr[i22];
                    fArr2[i23 + 1] = fArr[i22 + 1];
                    fArr2[i24] = fArr[i22 + 2];
                    fArr2[i24 + 1] = fArr[i22 + 3];
                    i20++;
                }
                this.fftRows.complexForward(fArr2, 0);
                this.fftRows.complexForward(fArr2, this.rows * 2);
                while (true) {
                    int i25 = this.rows;
                    if (i4 < i25) {
                        int i26 = this.columns * i4;
                        int i27 = i4 * 2;
                        int i28 = (i25 * 2) + i27;
                        fArr[i26] = fArr2[i27];
                        fArr[i26 + 1] = fArr2[i27 + 1];
                        fArr[i26 + 2] = fArr2[i28];
                        fArr[i26 + 3] = fArr2[i28 + 1];
                        i4++;
                    } else {
                        return;
                    }
                }
            } else if (i3 == 2) {
                for (int i29 = 0; i29 < this.rows; i29++) {
                    int i30 = this.columns * i29;
                    int i31 = i29 * 2;
                    fArr2[i31] = fArr[i30];
                    fArr2[i31 + 1] = fArr[i30 + 1];
                }
                this.fftRows.complexForward(fArr2, 0);
                while (i4 < this.rows) {
                    int i32 = this.columns * i4;
                    int i33 = i4 * 2;
                    fArr[i32] = fArr2[i33];
                    fArr[i32 + 1] = fArr2[i33 + 1];
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
                    fArr2[i38] = fArr[i37];
                    fArr2[i38 + 1] = fArr[i37 + 1];
                    fArr2[i39] = fArr[i37 + 2];
                    fArr2[i39 + 1] = fArr[i37 + 3];
                    fArr2[i40] = fArr[i37 + 4];
                    fArr2[i40 + 1] = fArr[i37 + 5];
                    fArr2[i41] = fArr[i37 + 6];
                    fArr2[i41 + 1] = fArr[i37 + 7];
                    i35++;
                }
                this.fftRows.complexInverse(fArr2, 0, z);
                this.fftRows.complexInverse(fArr2, this.rows * 2, z);
                this.fftRows.complexInverse(fArr2, this.rows * 4, z);
                this.fftRows.complexInverse(fArr2, this.rows * 6, z);
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
                    fArr[i44] = fArr2[i45];
                    fArr[i44 + 1] = fArr2[i45 + 1];
                    fArr[i44 + 2] = fArr2[i46];
                    fArr[i44 + 3] = fArr2[i46 + 1];
                    fArr[i44 + 4] = fArr2[i47];
                    fArr[i44 + 5] = fArr2[i47 + 1];
                    fArr[i44 + 6] = fArr2[i48];
                    fArr[i44 + 7] = fArr2[i48 + 1];
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
                fArr2[i52] = fArr[i51];
                fArr2[i52 + 1] = fArr[i51 + 1];
                fArr2[i53] = fArr[i51 + 2];
                fArr2[i53 + 1] = fArr[i51 + 3];
                i49++;
            }
            this.fftRows.complexInverse(fArr2, 0, z);
            this.fftRows.complexInverse(fArr2, this.rows * 2, z);
            while (true) {
                int i54 = this.rows;
                if (i4 < i54) {
                    int i55 = this.columns * i4;
                    int i56 = i4 * 2;
                    int i57 = (i54 * 2) + i56;
                    fArr[i55] = fArr2[i56];
                    fArr[i55 + 1] = fArr2[i56 + 1];
                    fArr[i55 + 2] = fArr2[i57];
                    fArr[i55 + 3] = fArr2[i57 + 1];
                    i4++;
                } else {
                    return;
                }
            }
        } else if (i3 == 2) {
            for (int i58 = 0; i58 < this.rows; i58++) {
                int i59 = this.columns * i58;
                int i60 = i58 * 2;
                fArr2[i60] = fArr[i59];
                fArr2[i60 + 1] = fArr[i59 + 1];
            }
            this.fftRows.complexInverse(fArr2, 0, z);
            while (i4 < this.rows) {
                int i61 = this.columns * i4;
                int i62 = i4 * 2;
                fArr[i61] = fArr2[i62];
                fArr[i61 + 1] = fArr2[i62 + 1];
                i4++;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x024c  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x002e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void cdft2d_sub(int r30, pl.edu.icm.jlargearrays.FloatLargeArray r31, boolean r32) {
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
            pl.edu.icm.jlargearrays.FloatLargeArray r7 = new pl.edu.icm.jlargearrays.FloatLargeArray
            r7.<init>((long) r3)
            r3 = -1
            r15 = 6
            r17 = 3
            r19 = 2
            r21 = 1
            r4 = r30
            if (r4 != r3) goto L_0x024c
            long r2 = r0.columnsl
            int r4 = (r2 > r9 ? 1 : (r2 == r9 ? 0 : -1))
            if (r4 <= 0) goto L_0x016a
            r2 = 0
        L_0x0036:
            long r5 = r0.columnsl
            int r4 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r4 >= 0) goto L_0x0474
            r4 = 0
        L_0x003e:
            long r11 = r0.rowsl
            int r6 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r6 >= 0) goto L_0x00c0
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
            float r6 = r1.getFloat(r13)
            r23 = r11
            double r11 = (double) r6
            r7.setDouble(r9, r11)
            long r9 = r9 + r21
            long r11 = r13 + r21
            float r6 = r1.getFloat(r11)
            double r11 = (double) r6
            r7.setDouble(r9, r11)
            long r8 = r13 + r19
            float r6 = r1.getFloat(r8)
            double r8 = (double) r6
            r7.setDouble(r2, r8)
            long r2 = r2 + r21
            long r8 = r13 + r17
            float r6 = r1.getFloat(r8)
            double r8 = (double) r6
            r7.setDouble(r2, r8)
            r2 = 4
            long r9 = r13 + r2
            float r2 = r1.getFloat(r9)
            double r2 = (double) r2
            r7.setDouble(r4, r2)
            long r4 = r4 + r21
            r2 = 5
            long r8 = r13 + r2
            float r2 = r1.getFloat(r8)
            double r2 = (double) r2
            r7.setDouble(r4, r2)
            long r2 = r13 + r15
            float r2 = r1.getFloat(r2)
            double r2 = (double) r2
            r4 = r23
            r7.setDouble(r4, r2)
            long r11 = r4 + r21
            r2 = 7
            long r13 = r13 + r2
            float r2 = r1.getFloat(r13)
            double r2 = (double) r2
            r7.setDouble(r11, r2)
            long r4 = r27 + r21
            r2 = r25
            r9 = 4
            goto L_0x003e
        L_0x00c0:
            r25 = r2
            org.jtransforms.fft.FloatFFT_1D r2 = r0.fftRows
            r3 = 0
            r2.complexForward((pl.edu.icm.jlargearrays.FloatLargeArray) r7, (long) r3)
            org.jtransforms.fft.FloatFFT_1D r2 = r0.fftRows
            long r3 = r0.rowsl
            long r3 = r3 * r19
            r2.complexForward((pl.edu.icm.jlargearrays.FloatLargeArray) r7, (long) r3)
            org.jtransforms.fft.FloatFFT_1D r2 = r0.fftRows
            long r3 = r0.rowsl
            r5 = 4
            long r3 = r3 * r5
            r2.complexForward((pl.edu.icm.jlargearrays.FloatLargeArray) r7, (long) r3)
            org.jtransforms.fft.FloatFFT_1D r2 = r0.fftRows
            long r3 = r0.rowsl
            long r3 = r3 * r15
            r2.complexForward((pl.edu.icm.jlargearrays.FloatLargeArray) r7, (long) r3)
            r2 = 0
        L_0x00e6:
            long r4 = r0.rowsl
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 >= 0) goto L_0x0161
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
            float r6 = r7.getFloat(r10)
            r23 = r4
            double r4 = (double) r6
            r1.setDouble(r8, r4)
            long r4 = r8 + r21
            long r10 = r10 + r21
            float r6 = r7.getFloat(r10)
            double r10 = (double) r6
            r1.setDouble(r4, r10)
            long r4 = r8 + r19
            float r6 = r7.getFloat(r12)
            double r10 = (double) r6
            r1.setDouble(r4, r10)
            long r4 = r8 + r17
            long r12 = r12 + r21
            float r6 = r7.getFloat(r12)
            double r10 = (double) r6
            r1.setDouble(r4, r10)
            r4 = 4
            long r10 = r8 + r4
            float r4 = r7.getFloat(r2)
            double r4 = (double) r4
            r1.setDouble(r10, r4)
            r4 = 5
            long r13 = r8 + r4
            long r2 = r2 + r21
            float r2 = r7.getFloat(r2)
            double r2 = (double) r2
            r1.setDouble(r13, r2)
            long r2 = r8 + r15
            r4 = r23
            float r6 = r7.getFloat(r4)
            double r10 = (double) r6
            r1.setDouble(r2, r10)
            r2 = 7
            long r8 = r8 + r2
            long r4 = r4 + r21
            float r2 = r7.getFloat(r4)
            double r2 = (double) r2
            r1.setDouble(r8, r2)
            long r2 = r27 + r21
            goto L_0x00e6
        L_0x0161:
            r2 = 8
            long r4 = r25 + r2
            r2 = r4
            r9 = 4
            goto L_0x0036
        L_0x016a:
            r4 = r9
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 != 0) goto L_0x01f9
            r2 = 0
        L_0x0171:
            long r4 = r0.rowsl
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 >= 0) goto L_0x01ac
            long r8 = r0.columnsl
            long r8 = r8 * r2
            long r10 = r2 * r19
            long r4 = r4 * r19
            long r4 = r4 + r10
            float r6 = r1.getFloat(r8)
            double r12 = (double) r6
            r7.setDouble(r10, r12)
            long r10 = r10 + r21
            long r12 = r8 + r21
            float r6 = r1.getFloat(r12)
            double r12 = (double) r6
            r7.setDouble(r10, r12)
            long r10 = r8 + r19
            float r6 = r1.getFloat(r10)
            double r10 = (double) r6
            r7.setDouble(r4, r10)
            long r4 = r4 + r21
            long r8 = r8 + r17
            float r6 = r1.getFloat(r8)
            double r8 = (double) r6
            r7.setDouble(r4, r8)
            long r2 = r2 + r21
            goto L_0x0171
        L_0x01ac:
            org.jtransforms.fft.FloatFFT_1D r2 = r0.fftRows
            r3 = 0
            r2.complexForward((pl.edu.icm.jlargearrays.FloatLargeArray) r7, (long) r3)
            org.jtransforms.fft.FloatFFT_1D r2 = r0.fftRows
            long r3 = r0.rowsl
            long r3 = r3 * r19
            r2.complexForward((pl.edu.icm.jlargearrays.FloatLargeArray) r7, (long) r3)
            r5 = 0
        L_0x01be:
            long r2 = r0.rowsl
            int r4 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r4 >= 0) goto L_0x0474
            long r8 = r0.columnsl
            long r8 = r8 * r5
            long r10 = r5 * r19
            long r2 = r2 * r19
            long r2 = r2 + r10
            float r4 = r7.getFloat(r10)
            double r12 = (double) r4
            r1.setDouble(r8, r12)
            long r12 = r8 + r21
            long r10 = r10 + r21
            float r4 = r7.getFloat(r10)
            double r10 = (double) r4
            r1.setDouble(r12, r10)
            long r10 = r8 + r19
            float r4 = r7.getFloat(r2)
            double r12 = (double) r4
            r1.setDouble(r10, r12)
            long r8 = r8 + r17
            long r2 = r2 + r21
            float r2 = r7.getFloat(r2)
            double r2 = (double) r2
            r1.setDouble(r8, r2)
            long r5 = r5 + r21
            goto L_0x01be
        L_0x01f9:
            int r2 = (r2 > r19 ? 1 : (r2 == r19 ? 0 : -1))
            if (r2 != 0) goto L_0x0474
            r2 = 0
        L_0x01ff:
            long r4 = r0.rowsl
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 >= 0) goto L_0x0221
            long r4 = r0.columnsl
            long r4 = r4 * r2
            long r8 = r2 * r19
            float r6 = r1.getFloat(r4)
            double r10 = (double) r6
            r7.setDouble(r8, r10)
            long r8 = r8 + r21
            long r4 = r4 + r21
            float r4 = r1.getFloat(r4)
            double r4 = (double) r4
            r7.setDouble(r8, r4)
            long r2 = r2 + r21
            goto L_0x01ff
        L_0x0221:
            org.jtransforms.fft.FloatFFT_1D r2 = r0.fftRows
            r3 = 0
            r2.complexForward((pl.edu.icm.jlargearrays.FloatLargeArray) r7, (long) r3)
            r5 = 0
        L_0x022a:
            long r2 = r0.rowsl
            int r2 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r2 >= 0) goto L_0x0474
            long r2 = r0.columnsl
            long r2 = r2 * r5
            long r8 = r5 * r19
            float r4 = r7.getFloat(r8)
            double r10 = (double) r4
            r1.setDouble(r2, r10)
            long r2 = r2 + r21
            long r8 = r8 + r21
            float r4 = r7.getFloat(r8)
            double r8 = (double) r4
            r1.setDouble(r2, r8)
            long r5 = r5 + r21
            goto L_0x022a
        L_0x024c:
            long r3 = r0.columnsl
            r5 = 4
            int r8 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r8 <= 0) goto L_0x038e
            r3 = 0
        L_0x0256:
            long r5 = r0.columnsl
            int r5 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r5 >= 0) goto L_0x0474
            r5 = 0
        L_0x025e:
            long r8 = r0.rowsl
            int r10 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r10 >= 0) goto L_0x02dd
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
            float r14 = r1.getFloat(r10)
            r23 = r8
            double r8 = (double) r14
            r7.setDouble(r12, r8)
            long r12 = r12 + r21
            long r8 = r10 + r21
            float r8 = r1.getFloat(r8)
            double r8 = (double) r8
            r7.setDouble(r12, r8)
            long r8 = r10 + r19
            float r8 = r1.getFloat(r8)
            double r8 = (double) r8
            r7.setDouble(r3, r8)
            long r3 = r3 + r21
            long r8 = r10 + r17
            float r8 = r1.getFloat(r8)
            double r8 = (double) r8
            r7.setDouble(r3, r8)
            r3 = 4
            long r8 = r10 + r3
            float r3 = r1.getFloat(r8)
            double r3 = (double) r3
            r7.setDouble(r5, r3)
            long r5 = r5 + r21
            r3 = 5
            long r13 = r10 + r3
            float r3 = r1.getFloat(r13)
            double r3 = (double) r3
            r7.setDouble(r5, r3)
            long r3 = r10 + r15
            float r3 = r1.getFloat(r3)
            double r3 = (double) r3
            r5 = r23
            r7.setDouble(r5, r3)
            long r8 = r5 + r21
            r3 = 7
            long r10 = r10 + r3
            float r3 = r1.getFloat(r10)
            double r3 = (double) r3
            r7.setDouble(r8, r3)
            long r5 = r27 + r21
            r3 = r25
            goto L_0x025e
        L_0x02dd:
            r25 = r3
            org.jtransforms.fft.FloatFFT_1D r3 = r0.fftRows
            r4 = 0
            r3.complexInverse((pl.edu.icm.jlargearrays.FloatLargeArray) r7, (long) r4, (boolean) r2)
            org.jtransforms.fft.FloatFFT_1D r3 = r0.fftRows
            long r4 = r0.rowsl
            long r4 = r4 * r19
            r3.complexInverse((pl.edu.icm.jlargearrays.FloatLargeArray) r7, (long) r4, (boolean) r2)
            org.jtransforms.fft.FloatFFT_1D r3 = r0.fftRows
            long r4 = r0.rowsl
            r8 = 4
            long r4 = r4 * r8
            r3.complexInverse((pl.edu.icm.jlargearrays.FloatLargeArray) r7, (long) r4, (boolean) r2)
            org.jtransforms.fft.FloatFFT_1D r3 = r0.fftRows
            long r4 = r0.rowsl
            long r4 = r4 * r15
            r3.complexInverse((pl.edu.icm.jlargearrays.FloatLargeArray) r7, (long) r4, (boolean) r2)
            r3 = 0
        L_0x0303:
            long r5 = r0.rowsl
            int r8 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r8 >= 0) goto L_0x0381
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
            float r4 = r7.getFloat(r10)
            r23 = r5
            double r4 = (double) r4
            r1.setDouble(r8, r4)
            long r4 = r8 + r21
            long r10 = r10 + r21
            float r6 = r7.getFloat(r10)
            double r10 = (double) r6
            r1.setDouble(r4, r10)
            long r4 = r8 + r19
            float r6 = r7.getFloat(r12)
            double r10 = (double) r6
            r1.setDouble(r4, r10)
            long r4 = r8 + r17
            long r12 = r12 + r21
            float r6 = r7.getFloat(r12)
            double r10 = (double) r6
            r1.setDouble(r4, r10)
            r4 = 4
            long r10 = r8 + r4
            float r4 = r7.getFloat(r2)
            double r4 = (double) r4
            r1.setDouble(r10, r4)
            r4 = 5
            long r13 = r8 + r4
            long r2 = r2 + r21
            float r2 = r7.getFloat(r2)
            double r2 = (double) r2
            r1.setDouble(r13, r2)
            long r2 = r8 + r15
            r10 = r23
            float r6 = r7.getFloat(r10)
            double r12 = (double) r6
            r1.setDouble(r2, r12)
            r2 = 7
            long r8 = r8 + r2
            long r10 = r10 + r21
            float r6 = r7.getFloat(r10)
            double r10 = (double) r6
            r1.setDouble(r8, r10)
            long r8 = r27 + r21
            r2 = r32
            r3 = r8
            goto L_0x0303
        L_0x0381:
            r2 = 7
            r4 = 5
            r8 = 8
            long r10 = r25 + r8
            r2 = r32
            r3 = r10
            goto L_0x0256
        L_0x038e:
            r5 = 4
            int r2 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r2 != 0) goto L_0x0420
            r2 = 0
        L_0x0396:
            long r4 = r0.rowsl
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 >= 0) goto L_0x03d1
            long r8 = r0.columnsl
            long r8 = r8 * r2
            long r10 = r2 * r19
            long r4 = r4 * r19
            long r4 = r4 + r10
            float r6 = r1.getFloat(r8)
            double r12 = (double) r6
            r7.setDouble(r10, r12)
            long r10 = r10 + r21
            long r12 = r8 + r21
            float r6 = r1.getFloat(r12)
            double r12 = (double) r6
            r7.setDouble(r10, r12)
            long r10 = r8 + r19
            float r6 = r1.getFloat(r10)
            double r10 = (double) r6
            r7.setDouble(r4, r10)
            long r4 = r4 + r21
            long r8 = r8 + r17
            float r6 = r1.getFloat(r8)
            double r8 = (double) r6
            r7.setDouble(r4, r8)
            long r2 = r2 + r21
            goto L_0x0396
        L_0x03d1:
            org.jtransforms.fft.FloatFFT_1D r2 = r0.fftRows
            r5 = r32
            r3 = 0
            r2.complexInverse((pl.edu.icm.jlargearrays.FloatLargeArray) r7, (long) r3, (boolean) r5)
            org.jtransforms.fft.FloatFFT_1D r2 = r0.fftRows
            long r3 = r0.rowsl
            long r3 = r3 * r19
            r2.complexInverse((pl.edu.icm.jlargearrays.FloatLargeArray) r7, (long) r3, (boolean) r5)
            r5 = 0
        L_0x03e5:
            long r2 = r0.rowsl
            int r4 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r4 >= 0) goto L_0x0474
            long r8 = r0.columnsl
            long r8 = r8 * r5
            long r10 = r5 * r19
            long r2 = r2 * r19
            long r2 = r2 + r10
            float r4 = r7.getFloat(r10)
            double r12 = (double) r4
            r1.setDouble(r8, r12)
            long r12 = r8 + r21
            long r10 = r10 + r21
            float r4 = r7.getFloat(r10)
            double r10 = (double) r4
            r1.setDouble(r12, r10)
            long r10 = r8 + r19
            float r4 = r7.getFloat(r2)
            double r12 = (double) r4
            r1.setDouble(r10, r12)
            long r8 = r8 + r17
            long r2 = r2 + r21
            float r2 = r7.getFloat(r2)
            double r2 = (double) r2
            r1.setDouble(r8, r2)
            long r5 = r5 + r21
            goto L_0x03e5
        L_0x0420:
            r5 = r32
            int r2 = (r3 > r19 ? 1 : (r3 == r19 ? 0 : -1))
            if (r2 != 0) goto L_0x0474
            r3 = 0
        L_0x0428:
            long r8 = r0.rowsl
            int r2 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
            if (r2 >= 0) goto L_0x044a
            long r8 = r0.columnsl
            long r8 = r8 * r3
            long r10 = r3 * r19
            float r2 = r1.getFloat(r8)
            double r12 = (double) r2
            r7.setDouble(r10, r12)
            long r10 = r10 + r21
            long r8 = r8 + r21
            float r2 = r1.getFloat(r8)
            double r8 = (double) r2
            r7.setDouble(r10, r8)
            long r3 = r3 + r21
            goto L_0x0428
        L_0x044a:
            org.jtransforms.fft.FloatFFT_1D r2 = r0.fftRows
            r3 = 0
            r2.complexInverse((pl.edu.icm.jlargearrays.FloatLargeArray) r7, (long) r3, (boolean) r5)
            r5 = r3
        L_0x0452:
            long r2 = r0.rowsl
            int r2 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r2 >= 0) goto L_0x0474
            long r2 = r0.columnsl
            long r2 = r2 * r5
            long r8 = r5 * r19
            float r4 = r7.getFloat(r8)
            double r10 = (double) r4
            r1.setDouble(r2, r10)
            long r2 = r2 + r21
            long r8 = r8 + r21
            float r4 = r7.getFloat(r8)
            double r8 = (double) r4
            r1.setDouble(r2, r8)
            long r5 = r5 + r21
            goto L_0x0452
        L_0x0474:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.FloatFFT_2D.cdft2d_sub(int, pl.edu.icm.jlargearrays.FloatLargeArray, boolean):void");
    }

    private void cdft2d_sub(int i, float[][] fArr, boolean z) {
        int i2 = this.rows * 8;
        int i3 = this.columns;
        if (i3 == 4) {
            i2 >>= 1;
        } else if (i3 < 4) {
            i2 >>= 2;
        }
        float[] fArr2 = new float[i2];
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
                        float[] fArr3 = fArr[i5];
                        fArr2[i7] = fArr3[i4];
                        fArr2[i7 + 1] = fArr3[i4 + 1];
                        fArr2[i8] = fArr3[i4 + 2];
                        fArr2[i8 + 1] = fArr3[i4 + 3];
                        fArr2[i9] = fArr3[i4 + 4];
                        fArr2[i9 + 1] = fArr3[i4 + 5];
                        fArr2[i10] = fArr3[i4 + 6];
                        fArr2[i10 + 1] = fArr3[i4 + 7];
                        i5++;
                    }
                    this.fftRows.complexForward(fArr2, 0);
                    this.fftRows.complexForward(fArr2, this.rows * 2);
                    this.fftRows.complexForward(fArr2, this.rows * 4);
                    this.fftRows.complexForward(fArr2, this.rows * 6);
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
                        float[] fArr4 = fArr[i11];
                        fArr4[i4] = fArr2[i13];
                        fArr4[i4 + 1] = fArr2[i13 + 1];
                        fArr4[i4 + 2] = fArr2[i14];
                        fArr4[i4 + 3] = fArr2[i14 + 1];
                        fArr4[i4 + 4] = fArr2[i15];
                        fArr4[i4 + 5] = fArr2[i15 + 1];
                        fArr4[i4 + 6] = fArr2[i16];
                        fArr4[i4 + 7] = fArr2[i16 + 1];
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
                    float[] fArr5 = fArr[i17];
                    fArr2[i19] = fArr5[0];
                    fArr2[i19 + 1] = fArr5[1];
                    fArr2[i20] = fArr5[2];
                    fArr2[i20 + 1] = fArr5[3];
                    i17++;
                }
                this.fftRows.complexForward(fArr2, 0);
                this.fftRows.complexForward(fArr2, this.rows * 2);
                int i21 = 0;
                while (true) {
                    int i22 = this.rows;
                    if (i21 < i22) {
                        int i23 = i21 * 2;
                        int i24 = (i22 * 2) + i23;
                        float[] fArr6 = fArr[i21];
                        fArr6[0] = fArr2[i23];
                        fArr6[1] = fArr2[i23 + 1];
                        fArr6[2] = fArr2[i24];
                        fArr6[3] = fArr2[i24 + 1];
                        i21++;
                    } else {
                        return;
                    }
                }
            } else if (i3 == 2) {
                for (int i25 = 0; i25 < this.rows; i25++) {
                    int i26 = i25 * 2;
                    float[] fArr7 = fArr[i25];
                    fArr2[i26] = fArr7[0];
                    fArr2[i26 + 1] = fArr7[1];
                }
                this.fftRows.complexForward(fArr2, 0);
                for (int i27 = 0; i27 < this.rows; i27++) {
                    int i28 = i27 * 2;
                    float[] fArr8 = fArr[i27];
                    fArr8[0] = fArr2[i28];
                    fArr8[1] = fArr2[i28 + 1];
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
                    float[] fArr9 = fArr[i30];
                    fArr2[i32] = fArr9[i29];
                    fArr2[i32 + 1] = fArr9[i29 + 1];
                    fArr2[i33] = fArr9[i29 + 2];
                    fArr2[i33 + 1] = fArr9[i29 + 3];
                    fArr2[i34] = fArr9[i29 + 4];
                    fArr2[i34 + 1] = fArr9[i29 + 5];
                    fArr2[i35] = fArr9[i29 + 6];
                    fArr2[i35 + 1] = fArr9[i29 + 7];
                    i30++;
                }
                this.fftRows.complexInverse(fArr2, 0, z);
                this.fftRows.complexInverse(fArr2, this.rows * 2, z);
                this.fftRows.complexInverse(fArr2, this.rows * 4, z);
                this.fftRows.complexInverse(fArr2, this.rows * 6, z);
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
                    float[] fArr10 = fArr[i36];
                    fArr10[i29] = fArr2[i38];
                    fArr10[i29 + 1] = fArr2[i38 + 1];
                    fArr10[i29 + 2] = fArr2[i39];
                    fArr10[i29 + 3] = fArr2[i39 + 1];
                    fArr10[i29 + 4] = fArr2[i40];
                    fArr10[i29 + 5] = fArr2[i40 + 1];
                    fArr10[i29 + 6] = fArr2[i41];
                    fArr10[i29 + 7] = fArr2[i41 + 1];
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
                float[] fArr11 = fArr[i42];
                fArr2[i44] = fArr11[0];
                fArr2[i44 + 1] = fArr11[1];
                fArr2[i45] = fArr11[2];
                fArr2[i45 + 1] = fArr11[3];
                i42++;
            }
            this.fftRows.complexInverse(fArr2, 0, z);
            this.fftRows.complexInverse(fArr2, this.rows * 2, z);
            int i46 = 0;
            while (true) {
                int i47 = this.rows;
                if (i46 < i47) {
                    int i48 = i46 * 2;
                    int i49 = (i47 * 2) + i48;
                    float[] fArr12 = fArr[i46];
                    fArr12[0] = fArr2[i48];
                    fArr12[1] = fArr2[i48 + 1];
                    fArr12[2] = fArr2[i49];
                    fArr12[3] = fArr2[i49 + 1];
                    i46++;
                } else {
                    return;
                }
            }
        } else if (i3 == 2) {
            for (int i50 = 0; i50 < this.rows; i50++) {
                int i51 = i50 * 2;
                float[] fArr13 = fArr[i50];
                fArr2[i51] = fArr13[0];
                fArr2[i51 + 1] = fArr13[1];
            }
            this.fftRows.complexInverse(fArr2, 0, z);
            for (int i52 = 0; i52 < this.rows; i52++) {
                int i53 = i52 * 2;
                float[] fArr14 = fArr[i52];
                fArr14[0] = fArr2[i53];
                fArr14[1] = fArr2[i53 + 1];
            }
        }
    }

    private void xdft2d0_subth1(int i, int i2, float[] fArr, boolean z) {
        Class<FloatFFT_2D> cls = FloatFFT_2D.class;
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
            final float[] fArr2 = fArr;
            final boolean z2 = z;
            futureArr[i5] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    if (i6 == 0) {
                        if (i7 == -1) {
                            int i = i8;
                            while (i < FloatFFT_2D.this.rows) {
                                FloatFFT_2D.this.fftColumns.complexForward(fArr2, FloatFFT_2D.this.columns * i);
                                i += i9;
                            }
                            return;
                        }
                        int i2 = i8;
                        while (i2 < FloatFFT_2D.this.rows) {
                            FloatFFT_2D.this.fftColumns.complexInverse(fArr2, FloatFFT_2D.this.columns * i2, z2);
                            i2 += i9;
                        }
                    } else if (i7 == 1) {
                        int i3 = i8;
                        while (i3 < FloatFFT_2D.this.rows) {
                            FloatFFT_2D.this.fftColumns.realForward(fArr2, FloatFFT_2D.this.columns * i3);
                            i3 += i9;
                        }
                    } else {
                        int i4 = i8;
                        while (i4 < FloatFFT_2D.this.rows) {
                            FloatFFT_2D.this.fftColumns.realInverse(fArr2, FloatFFT_2D.this.columns * i4, z2);
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

    private void xdft2d0_subth1(long j, int i, FloatLargeArray floatLargeArray, boolean z) {
        Class<FloatFFT_2D> cls = FloatFFT_2D.class;
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
            final FloatLargeArray floatLargeArray2 = floatLargeArray;
            final boolean z2 = z;
            futureArr[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    if (j3 == 0) {
                        if (i4 == -1) {
                            long j = (long) i5;
                            while (j < FloatFFT_2D.this.rowsl) {
                                FloatFFT_2D.this.fftColumns.complexForward(floatLargeArray2, FloatFFT_2D.this.columnsl * j);
                                j += (long) i6;
                            }
                            return;
                        }
                        long j2 = (long) i5;
                        while (j2 < FloatFFT_2D.this.rowsl) {
                            FloatFFT_2D.this.fftColumns.complexInverse(floatLargeArray2, FloatFFT_2D.this.columnsl * j2, z2);
                            j2 += (long) i6;
                        }
                    } else if (i4 == 1) {
                        long j3 = (long) i5;
                        while (j3 < FloatFFT_2D.this.rowsl) {
                            FloatFFT_2D.this.fftColumns.realForward(floatLargeArray2, FloatFFT_2D.this.columnsl * j3);
                            j3 += (long) i6;
                        }
                    } else {
                        long j4 = (long) i5;
                        while (j4 < FloatFFT_2D.this.rowsl) {
                            FloatFFT_2D.this.fftColumns.realInverse(floatLargeArray2, FloatFFT_2D.this.columnsl * j4, z2);
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

    private void xdft2d0_subth2(int i, int i2, float[] fArr, boolean z) {
        Class<FloatFFT_2D> cls = FloatFFT_2D.class;
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
            final float[] fArr2 = fArr;
            final boolean z2 = z;
            futureArr[i5] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    if (i6 == 0) {
                        if (i7 == -1) {
                            int i = i8;
                            while (i < FloatFFT_2D.this.rows) {
                                FloatFFT_2D.this.fftColumns.complexForward(fArr2, FloatFFT_2D.this.columns * i);
                                i += i9;
                            }
                            return;
                        }
                        int i2 = i8;
                        while (i2 < FloatFFT_2D.this.rows) {
                            FloatFFT_2D.this.fftColumns.complexInverse(fArr2, FloatFFT_2D.this.columns * i2, z2);
                            i2 += i9;
                        }
                    } else if (i7 == 1) {
                        int i3 = i8;
                        while (i3 < FloatFFT_2D.this.rows) {
                            FloatFFT_2D.this.fftColumns.realForward(fArr2, FloatFFT_2D.this.columns * i3);
                            i3 += i9;
                        }
                    } else {
                        int i4 = i8;
                        while (i4 < FloatFFT_2D.this.rows) {
                            FloatFFT_2D.this.fftColumns.realInverse2(fArr2, FloatFFT_2D.this.columns * i4, z2);
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

    private void xdft2d0_subth2(long j, int i, FloatLargeArray floatLargeArray, boolean z) {
        Class<FloatFFT_2D> cls = FloatFFT_2D.class;
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
            final FloatLargeArray floatLargeArray2 = floatLargeArray;
            final boolean z2 = z;
            futureArr[i4] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    if (j3 == 0) {
                        if (i5 == -1) {
                            long j = j2;
                            while (j < FloatFFT_2D.this.rowsl) {
                                FloatFFT_2D.this.fftColumns.complexForward(floatLargeArray2, FloatFFT_2D.this.columnsl * j);
                                j += (long) i6;
                            }
                            return;
                        }
                        long j2 = j2;
                        while (j2 < FloatFFT_2D.this.rowsl) {
                            FloatFFT_2D.this.fftColumns.complexInverse(floatLargeArray2, FloatFFT_2D.this.columnsl * j2, z2);
                            j2 += (long) i6;
                        }
                    } else if (i5 == 1) {
                        long j3 = j2;
                        while (j3 < FloatFFT_2D.this.rowsl) {
                            FloatFFT_2D.this.fftColumns.realForward(floatLargeArray2, FloatFFT_2D.this.columnsl * j3);
                            j3 += (long) i6;
                        }
                    } else {
                        long j4 = j2;
                        while (j4 < FloatFFT_2D.this.rowsl) {
                            FloatFFT_2D.this.fftColumns.realInverse2(floatLargeArray2, FloatFFT_2D.this.columnsl * j4, z2);
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

    private void xdft2d0_subth1(int i, int i2, float[][] fArr, boolean z) {
        Class<FloatFFT_2D> cls = FloatFFT_2D.class;
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
            final float[][] fArr2 = fArr;
            final boolean z2 = z;
            futureArr[i5] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    if (i6 == 0) {
                        if (i7 == -1) {
                            int i = i8;
                            while (i < FloatFFT_2D.this.rows) {
                                FloatFFT_2D.this.fftColumns.complexForward(fArr2[i]);
                                i += i9;
                            }
                            return;
                        }
                        int i2 = i8;
                        while (i2 < FloatFFT_2D.this.rows) {
                            FloatFFT_2D.this.fftColumns.complexInverse(fArr2[i2], z2);
                            i2 += i9;
                        }
                    } else if (i7 == 1) {
                        int i3 = i8;
                        while (i3 < FloatFFT_2D.this.rows) {
                            FloatFFT_2D.this.fftColumns.realForward(fArr2[i3]);
                            i3 += i9;
                        }
                    } else {
                        int i4 = i8;
                        while (i4 < FloatFFT_2D.this.rows) {
                            FloatFFT_2D.this.fftColumns.realInverse(fArr2[i4], z2);
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

    private void xdft2d0_subth2(int i, int i2, float[][] fArr, boolean z) {
        Class<FloatFFT_2D> cls = FloatFFT_2D.class;
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
            final float[][] fArr2 = fArr;
            final boolean z2 = z;
            futureArr[i5] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    if (i6 == 0) {
                        if (i7 == -1) {
                            int i = i8;
                            while (i < FloatFFT_2D.this.rows) {
                                FloatFFT_2D.this.fftColumns.complexForward(fArr2[i]);
                                i += i9;
                            }
                            return;
                        }
                        int i2 = i8;
                        while (i2 < FloatFFT_2D.this.rows) {
                            FloatFFT_2D.this.fftColumns.complexInverse(fArr2[i2], z2);
                            i2 += i9;
                        }
                    } else if (i7 == 1) {
                        int i3 = i8;
                        while (i3 < FloatFFT_2D.this.rows) {
                            FloatFFT_2D.this.fftColumns.realForward(fArr2[i3]);
                            i3 += i9;
                        }
                    } else {
                        int i4 = i8;
                        while (i4 < FloatFFT_2D.this.rows) {
                            FloatFFT_2D.this.fftColumns.realInverse2(fArr2[i4], 0, z2);
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

    private void cdft2d_subth(int i, float[] fArr, boolean z) {
        Class<FloatFFT_2D> cls = FloatFFT_2D.class;
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
            final float[] fArr2 = fArr;
            final boolean z2 = z;
            futureArr[i5] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    float[] fArr = new float[i6];
                    int i = 0;
                    if (i7 == -1) {
                        if (FloatFFT_2D.this.columns > i8 * 4) {
                            int i2 = i9 * 8;
                            while (i2 < FloatFFT_2D.this.columns) {
                                for (int i3 = 0; i3 < FloatFFT_2D.this.rows; i3++) {
                                    int access$400 = (FloatFFT_2D.this.columns * i3) + i2;
                                    int i4 = i3 * 2;
                                    int access$100 = (FloatFFT_2D.this.rows * 2) + i4;
                                    int access$1002 = (FloatFFT_2D.this.rows * 2) + access$100;
                                    int access$1003 = (FloatFFT_2D.this.rows * 2) + access$1002;
                                    float[] fArr2 = fArr2;
                                    fArr[i4] = fArr2[access$400];
                                    fArr[i4 + 1] = fArr2[access$400 + 1];
                                    fArr[access$100] = fArr2[access$400 + 2];
                                    fArr[access$100 + 1] = fArr2[access$400 + 3];
                                    fArr[access$1002] = fArr2[access$400 + 4];
                                    fArr[access$1002 + 1] = fArr2[access$400 + 5];
                                    fArr[access$1003] = fArr2[access$400 + 6];
                                    fArr[access$1003 + 1] = fArr2[access$400 + 7];
                                }
                                FloatFFT_2D.this.fftRows.complexForward(fArr, 0);
                                FloatFFT_2D.this.fftRows.complexForward(fArr, FloatFFT_2D.this.rows * 2);
                                FloatFFT_2D.this.fftRows.complexForward(fArr, FloatFFT_2D.this.rows * 4);
                                FloatFFT_2D.this.fftRows.complexForward(fArr, FloatFFT_2D.this.rows * 6);
                                for (int i5 = 0; i5 < FloatFFT_2D.this.rows; i5++) {
                                    int access$4002 = (FloatFFT_2D.this.columns * i5) + i2;
                                    int i6 = i5 * 2;
                                    int access$1004 = (FloatFFT_2D.this.rows * 2) + i6;
                                    int access$1005 = (FloatFFT_2D.this.rows * 2) + access$1004;
                                    int access$1006 = (FloatFFT_2D.this.rows * 2) + access$1005;
                                    float[] fArr3 = fArr2;
                                    fArr3[access$4002] = fArr[i6];
                                    fArr3[access$4002 + 1] = fArr[i6 + 1];
                                    fArr3[access$4002 + 2] = fArr[access$1004];
                                    fArr3[access$4002 + 3] = fArr[access$1004 + 1];
                                    fArr3[access$4002 + 4] = fArr[access$1005];
                                    fArr3[access$4002 + 5] = fArr[access$1005 + 1];
                                    fArr3[access$4002 + 6] = fArr[access$1006];
                                    fArr3[access$4002 + 7] = fArr[access$1006 + 1];
                                }
                                i2 += i8 * 8;
                            }
                        } else if (FloatFFT_2D.this.columns == i8 * 4) {
                            for (int i7 = 0; i7 < FloatFFT_2D.this.rows; i7++) {
                                int access$4003 = (FloatFFT_2D.this.columns * i7) + (i9 * 4);
                                int i8 = i7 * 2;
                                int access$1007 = (FloatFFT_2D.this.rows * 2) + i8;
                                float[] fArr4 = fArr2;
                                fArr[i8] = fArr4[access$4003];
                                fArr[i8 + 1] = fArr4[access$4003 + 1];
                                fArr[access$1007] = fArr4[access$4003 + 2];
                                fArr[access$1007 + 1] = fArr4[access$4003 + 3];
                            }
                            FloatFFT_2D.this.fftRows.complexForward(fArr, 0);
                            FloatFFT_2D.this.fftRows.complexForward(fArr, FloatFFT_2D.this.rows * 2);
                            while (i < FloatFFT_2D.this.rows) {
                                int access$4004 = (FloatFFT_2D.this.columns * i) + (i9 * 4);
                                int i9 = i * 2;
                                int access$1008 = (FloatFFT_2D.this.rows * 2) + i9;
                                float[] fArr5 = fArr2;
                                fArr5[access$4004] = fArr[i9];
                                fArr5[access$4004 + 1] = fArr[i9 + 1];
                                fArr5[access$4004 + 2] = fArr[access$1008];
                                fArr5[access$4004 + 3] = fArr[access$1008 + 1];
                                i++;
                            }
                        } else if (FloatFFT_2D.this.columns == i8 * 2) {
                            for (int i10 = 0; i10 < FloatFFT_2D.this.rows; i10++) {
                                int access$4005 = (FloatFFT_2D.this.columns * i10) + (i9 * 2);
                                int i11 = i10 * 2;
                                float[] fArr6 = fArr2;
                                fArr[i11] = fArr6[access$4005];
                                fArr[i11 + 1] = fArr6[access$4005 + 1];
                            }
                            FloatFFT_2D.this.fftRows.complexForward(fArr, 0);
                            while (i < FloatFFT_2D.this.rows) {
                                int access$4006 = (FloatFFT_2D.this.columns * i) + (i9 * 2);
                                int i12 = i * 2;
                                float[] fArr7 = fArr2;
                                fArr7[access$4006] = fArr[i12];
                                fArr7[access$4006 + 1] = fArr[i12 + 1];
                                i++;
                            }
                        }
                    } else if (FloatFFT_2D.this.columns > i8 * 4) {
                        int i13 = i9 * 8;
                        while (i13 < FloatFFT_2D.this.columns) {
                            for (int i14 = 0; i14 < FloatFFT_2D.this.rows; i14++) {
                                int access$4007 = (FloatFFT_2D.this.columns * i14) + i13;
                                int i15 = i14 * 2;
                                int access$1009 = (FloatFFT_2D.this.rows * 2) + i15;
                                int access$10010 = (FloatFFT_2D.this.rows * 2) + access$1009;
                                int access$10011 = (FloatFFT_2D.this.rows * 2) + access$10010;
                                float[] fArr8 = fArr2;
                                fArr[i15] = fArr8[access$4007];
                                fArr[i15 + 1] = fArr8[access$4007 + 1];
                                fArr[access$1009] = fArr8[access$4007 + 2];
                                fArr[access$1009 + 1] = fArr8[access$4007 + 3];
                                fArr[access$10010] = fArr8[access$4007 + 4];
                                fArr[access$10010 + 1] = fArr8[access$4007 + 5];
                                fArr[access$10011] = fArr8[access$4007 + 6];
                                fArr[access$10011 + 1] = fArr8[access$4007 + 7];
                            }
                            FloatFFT_2D.this.fftRows.complexInverse(fArr, 0, z2);
                            FloatFFT_2D.this.fftRows.complexInverse(fArr, FloatFFT_2D.this.rows * 2, z2);
                            FloatFFT_2D.this.fftRows.complexInverse(fArr, FloatFFT_2D.this.rows * 4, z2);
                            FloatFFT_2D.this.fftRows.complexInverse(fArr, FloatFFT_2D.this.rows * 6, z2);
                            for (int i16 = 0; i16 < FloatFFT_2D.this.rows; i16++) {
                                int access$4008 = (FloatFFT_2D.this.columns * i16) + i13;
                                int i17 = i16 * 2;
                                int access$10012 = (FloatFFT_2D.this.rows * 2) + i17;
                                int access$10013 = (FloatFFT_2D.this.rows * 2) + access$10012;
                                int access$10014 = (FloatFFT_2D.this.rows * 2) + access$10013;
                                float[] fArr9 = fArr2;
                                fArr9[access$4008] = fArr[i17];
                                fArr9[access$4008 + 1] = fArr[i17 + 1];
                                fArr9[access$4008 + 2] = fArr[access$10012];
                                fArr9[access$4008 + 3] = fArr[access$10012 + 1];
                                fArr9[access$4008 + 4] = fArr[access$10013];
                                fArr9[access$4008 + 5] = fArr[access$10013 + 1];
                                fArr9[access$4008 + 6] = fArr[access$10014];
                                fArr9[access$4008 + 7] = fArr[access$10014 + 1];
                            }
                            i13 += i8 * 8;
                        }
                    } else if (FloatFFT_2D.this.columns == i8 * 4) {
                        for (int i18 = 0; i18 < FloatFFT_2D.this.rows; i18++) {
                            int access$4009 = (FloatFFT_2D.this.columns * i18) + (i9 * 4);
                            int i19 = i18 * 2;
                            int access$10015 = (FloatFFT_2D.this.rows * 2) + i19;
                            float[] fArr10 = fArr2;
                            fArr[i19] = fArr10[access$4009];
                            fArr[i19 + 1] = fArr10[access$4009 + 1];
                            fArr[access$10015] = fArr10[access$4009 + 2];
                            fArr[access$10015 + 1] = fArr10[access$4009 + 3];
                        }
                        FloatFFT_2D.this.fftRows.complexInverse(fArr, 0, z2);
                        FloatFFT_2D.this.fftRows.complexInverse(fArr, FloatFFT_2D.this.rows * 2, z2);
                        while (i < FloatFFT_2D.this.rows) {
                            int access$40010 = (FloatFFT_2D.this.columns * i) + (i9 * 4);
                            int i20 = i * 2;
                            int access$10016 = (FloatFFT_2D.this.rows * 2) + i20;
                            float[] fArr11 = fArr2;
                            fArr11[access$40010] = fArr[i20];
                            fArr11[access$40010 + 1] = fArr[i20 + 1];
                            fArr11[access$40010 + 2] = fArr[access$10016];
                            fArr11[access$40010 + 3] = fArr[access$10016 + 1];
                            i++;
                        }
                    } else if (FloatFFT_2D.this.columns == i8 * 2) {
                        for (int i21 = 0; i21 < FloatFFT_2D.this.rows; i21++) {
                            int access$40011 = (FloatFFT_2D.this.columns * i21) + (i9 * 2);
                            int i22 = i21 * 2;
                            float[] fArr12 = fArr2;
                            fArr[i22] = fArr12[access$40011];
                            fArr[i22 + 1] = fArr12[access$40011 + 1];
                        }
                        FloatFFT_2D.this.fftRows.complexInverse(fArr, 0, z2);
                        while (i < FloatFFT_2D.this.rows) {
                            int access$40012 = (FloatFFT_2D.this.columns * i) + (i9 * 2);
                            int i23 = i * 2;
                            float[] fArr13 = fArr2;
                            fArr13[access$40012] = fArr[i23];
                            fArr13[access$40012 + 1] = fArr[i23 + 1];
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
    private void cdft2d_subth(int r19, pl.edu.icm.jlargearrays.FloatLargeArray r20, boolean r21) {
        /*
            r18 = this;
            r11 = r18
            java.lang.Class<org.jtransforms.fft.FloatFFT_2D> r12 = org.jtransforms.fft.FloatFFT_2D.class
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
            org.jtransforms.fft.FloatFFT_2D$44 r16 = new org.jtransforms.fft.FloatFFT_2D$44
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
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.FloatFFT_2D.cdft2d_subth(int, pl.edu.icm.jlargearrays.FloatLargeArray, boolean):void");
    }

    private void cdft2d_subth(int i, float[][] fArr, boolean z) {
        Class<FloatFFT_2D> cls = FloatFFT_2D.class;
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
            final float[][] fArr2 = fArr;
            final boolean z2 = z;
            futureArr[i5] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    float[] fArr = new float[i6];
                    int i = 0;
                    if (i7 == -1) {
                        if (FloatFFT_2D.this.columns > i8 * 4) {
                            int i2 = i9 * 8;
                            while (i2 < FloatFFT_2D.this.columns) {
                                for (int i3 = 0; i3 < FloatFFT_2D.this.rows; i3++) {
                                    int i4 = i3 * 2;
                                    int access$100 = (FloatFFT_2D.this.rows * 2) + i4;
                                    int access$1002 = (FloatFFT_2D.this.rows * 2) + access$100;
                                    int access$1003 = (FloatFFT_2D.this.rows * 2) + access$1002;
                                    float[] fArr2 = fArr2[i3];
                                    fArr[i4] = fArr2[i2];
                                    fArr[i4 + 1] = fArr2[i2 + 1];
                                    fArr[access$100] = fArr2[i2 + 2];
                                    fArr[access$100 + 1] = fArr2[i2 + 3];
                                    fArr[access$1002] = fArr2[i2 + 4];
                                    fArr[access$1002 + 1] = fArr2[i2 + 5];
                                    fArr[access$1003] = fArr2[i2 + 6];
                                    fArr[access$1003 + 1] = fArr2[i2 + 7];
                                }
                                FloatFFT_2D.this.fftRows.complexForward(fArr, 0);
                                FloatFFT_2D.this.fftRows.complexForward(fArr, FloatFFT_2D.this.rows * 2);
                                FloatFFT_2D.this.fftRows.complexForward(fArr, FloatFFT_2D.this.rows * 4);
                                FloatFFT_2D.this.fftRows.complexForward(fArr, FloatFFT_2D.this.rows * 6);
                                for (int i5 = 0; i5 < FloatFFT_2D.this.rows; i5++) {
                                    int i6 = i5 * 2;
                                    int access$1004 = (FloatFFT_2D.this.rows * 2) + i6;
                                    int access$1005 = (FloatFFT_2D.this.rows * 2) + access$1004;
                                    int access$1006 = (FloatFFT_2D.this.rows * 2) + access$1005;
                                    float[] fArr3 = fArr2[i5];
                                    fArr3[i2] = fArr[i6];
                                    fArr3[i2 + 1] = fArr[i6 + 1];
                                    fArr3[i2 + 2] = fArr[access$1004];
                                    fArr3[i2 + 3] = fArr[access$1004 + 1];
                                    fArr3[i2 + 4] = fArr[access$1005];
                                    fArr3[i2 + 5] = fArr[access$1005 + 1];
                                    fArr3[i2 + 6] = fArr[access$1006];
                                    fArr3[i2 + 7] = fArr[access$1006 + 1];
                                }
                                i2 += i8 * 8;
                            }
                        } else if (FloatFFT_2D.this.columns == i8 * 4) {
                            for (int i7 = 0; i7 < FloatFFT_2D.this.rows; i7++) {
                                int i8 = i7 * 2;
                                int access$1007 = (FloatFFT_2D.this.rows * 2) + i8;
                                float[] fArr4 = fArr2[i7];
                                int i9 = i9;
                                fArr[i8] = fArr4[i9 * 4];
                                fArr[i8 + 1] = fArr4[(i9 * 4) + 1];
                                fArr[access$1007] = fArr4[(i9 * 4) + 2];
                                fArr[access$1007 + 1] = fArr4[(i9 * 4) + 3];
                            }
                            FloatFFT_2D.this.fftRows.complexForward(fArr, 0);
                            FloatFFT_2D.this.fftRows.complexForward(fArr, FloatFFT_2D.this.rows * 2);
                            while (i < FloatFFT_2D.this.rows) {
                                int i10 = i * 2;
                                int access$1008 = (FloatFFT_2D.this.rows * 2) + i10;
                                float[] fArr5 = fArr2[i];
                                int i11 = i9;
                                fArr5[i11 * 4] = fArr[i10];
                                fArr5[(i11 * 4) + 1] = fArr[i10 + 1];
                                fArr5[(i11 * 4) + 2] = fArr[access$1008];
                                fArr5[(i11 * 4) + 3] = fArr[access$1008 + 1];
                                i++;
                            }
                        } else if (FloatFFT_2D.this.columns == i8 * 2) {
                            for (int i12 = 0; i12 < FloatFFT_2D.this.rows; i12++) {
                                int i13 = i12 * 2;
                                float[] fArr6 = fArr2[i12];
                                int i14 = i9;
                                fArr[i13] = fArr6[i14 * 2];
                                fArr[i13 + 1] = fArr6[(i14 * 2) + 1];
                            }
                            FloatFFT_2D.this.fftRows.complexForward(fArr, 0);
                            while (i < FloatFFT_2D.this.rows) {
                                int i15 = i * 2;
                                float[] fArr7 = fArr2[i];
                                int i16 = i9;
                                fArr7[i16 * 2] = fArr[i15];
                                fArr7[(i16 * 2) + 1] = fArr[i15 + 1];
                                i++;
                            }
                        }
                    } else if (FloatFFT_2D.this.columns > i8 * 4) {
                        int i17 = i9 * 8;
                        while (i17 < FloatFFT_2D.this.columns) {
                            for (int i18 = 0; i18 < FloatFFT_2D.this.rows; i18++) {
                                int i19 = i18 * 2;
                                int access$1009 = (FloatFFT_2D.this.rows * 2) + i19;
                                int access$10010 = (FloatFFT_2D.this.rows * 2) + access$1009;
                                int access$10011 = (FloatFFT_2D.this.rows * 2) + access$10010;
                                float[] fArr8 = fArr2[i18];
                                fArr[i19] = fArr8[i17];
                                fArr[i19 + 1] = fArr8[i17 + 1];
                                fArr[access$1009] = fArr8[i17 + 2];
                                fArr[access$1009 + 1] = fArr8[i17 + 3];
                                fArr[access$10010] = fArr8[i17 + 4];
                                fArr[access$10010 + 1] = fArr8[i17 + 5];
                                fArr[access$10011] = fArr8[i17 + 6];
                                fArr[access$10011 + 1] = fArr8[i17 + 7];
                            }
                            FloatFFT_2D.this.fftRows.complexInverse(fArr, 0, z2);
                            FloatFFT_2D.this.fftRows.complexInverse(fArr, FloatFFT_2D.this.rows * 2, z2);
                            FloatFFT_2D.this.fftRows.complexInverse(fArr, FloatFFT_2D.this.rows * 4, z2);
                            FloatFFT_2D.this.fftRows.complexInverse(fArr, FloatFFT_2D.this.rows * 6, z2);
                            for (int i20 = 0; i20 < FloatFFT_2D.this.rows; i20++) {
                                int i21 = i20 * 2;
                                int access$10012 = (FloatFFT_2D.this.rows * 2) + i21;
                                int access$10013 = (FloatFFT_2D.this.rows * 2) + access$10012;
                                int access$10014 = (FloatFFT_2D.this.rows * 2) + access$10013;
                                float[] fArr9 = fArr2[i20];
                                fArr9[i17] = fArr[i21];
                                fArr9[i17 + 1] = fArr[i21 + 1];
                                fArr9[i17 + 2] = fArr[access$10012];
                                fArr9[i17 + 3] = fArr[access$10012 + 1];
                                fArr9[i17 + 4] = fArr[access$10013];
                                fArr9[i17 + 5] = fArr[access$10013 + 1];
                                fArr9[i17 + 6] = fArr[access$10014];
                                fArr9[i17 + 7] = fArr[access$10014 + 1];
                            }
                            i17 += i8 * 8;
                        }
                    } else if (FloatFFT_2D.this.columns == i8 * 4) {
                        for (int i22 = 0; i22 < FloatFFT_2D.this.rows; i22++) {
                            int i23 = i22 * 2;
                            int access$10015 = (FloatFFT_2D.this.rows * 2) + i23;
                            float[] fArr10 = fArr2[i22];
                            int i24 = i9;
                            fArr[i23] = fArr10[i24 * 4];
                            fArr[i23 + 1] = fArr10[(i24 * 4) + 1];
                            fArr[access$10015] = fArr10[(i24 * 4) + 2];
                            fArr[access$10015 + 1] = fArr10[(i24 * 4) + 3];
                        }
                        FloatFFT_2D.this.fftRows.complexInverse(fArr, 0, z2);
                        FloatFFT_2D.this.fftRows.complexInverse(fArr, FloatFFT_2D.this.rows * 2, z2);
                        while (i < FloatFFT_2D.this.rows) {
                            int i25 = i * 2;
                            int access$10016 = (FloatFFT_2D.this.rows * 2) + i25;
                            float[] fArr11 = fArr2[i];
                            int i26 = i9;
                            fArr11[i26 * 4] = fArr[i25];
                            fArr11[(i26 * 4) + 1] = fArr[i25 + 1];
                            fArr11[(i26 * 4) + 2] = fArr[access$10016];
                            fArr11[(i26 * 4) + 3] = fArr[access$10016 + 1];
                            i++;
                        }
                    } else if (FloatFFT_2D.this.columns == i8 * 2) {
                        for (int i27 = 0; i27 < FloatFFT_2D.this.rows; i27++) {
                            int i28 = i27 * 2;
                            float[] fArr12 = fArr2[i27];
                            int i29 = i9;
                            fArr[i28] = fArr12[i29 * 2];
                            fArr[i28 + 1] = fArr12[(i29 * 2) + 1];
                        }
                        FloatFFT_2D.this.fftRows.complexInverse(fArr, 0, z2);
                        while (i < FloatFFT_2D.this.rows) {
                            int i30 = i * 2;
                            float[] fArr13 = fArr2[i];
                            int i31 = i9;
                            fArr13[i31 * 2] = fArr[i30];
                            fArr13[(i31 * 2) + 1] = fArr[i30 + 1];
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

    private void fillSymmetric(float[] fArr) {
        Class<FloatFFT_2D> cls = FloatFFT_2D.class;
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
                fArr[i8] = fArr[i9];
                fArr[i9] = 0.0f;
                int i10 = i9 + 1;
                fArr[i8 + 1] = fArr[i10];
                fArr[i10] = 0.0f;
            }
            i4--;
        }
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (numberOfThreads <= 1 || !this.useThreads || i3 < numberOfThreads) {
            for (int i11 = 1; i11 < i3; i11++) {
                int i12 = i11 * i;
                int i13 = (this.rows - i11) * i;
                int i14 = this.columns;
                fArr[i12 + i14] = fArr[i13 + 1];
                fArr[i12 + i14 + 1] = -fArr[i13];
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
                    fArr[i19] = fArr[i20];
                    fArr[i19 + 1] = -fArr[i20 + 1];
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
                    fArr[i27] = fArr[i26];
                    fArr[i27 + 1] = -fArr[i26 + 1];
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
                final int i32 = i30 * i28;
                final int i33 = i32 + i28;
                final int i34 = i29;
                final float[] fArr2 = fArr;
                int i35 = i30;
                final int i36 = i30 == numberOfThreads + -1 ? i33 + 1 : i33;
                futureArr[i35] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i31; i < i33; i++) {
                            int access$100 = (FloatFFT_2D.this.rows - i) * i34;
                            int access$400 = (i34 * i) + FloatFFT_2D.this.columns;
                            float[] fArr = fArr2;
                            fArr[access$400] = fArr[access$100 + 1];
                            fArr[access$400 + 1] = -fArr[access$100];
                        }
                        for (int i2 = i31; i2 < i33; i2++) {
                            int i3 = i34 * i2;
                            int access$1002 = ((FloatFFT_2D.this.rows - i2) + 1) * i34;
                            int access$4002 = FloatFFT_2D.this.columns;
                            while (true) {
                                access$4002 += 2;
                                if (access$4002 >= i34) {
                                    break;
                                }
                                int i4 = access$1002 - access$4002;
                                int i5 = i3 + access$4002;
                                float[] fArr2 = fArr2;
                                fArr2[i5] = fArr2[i4];
                                fArr2[i5 + 1] = -fArr2[i4 + 1];
                            }
                        }
                        for (int i6 = i32; i6 < i36; i6++) {
                            int access$1003 = (FloatFFT_2D.this.rows - i6) % FloatFFT_2D.this.rows;
                            int i7 = i34;
                            int i8 = access$1003 * i7;
                            int i9 = i7 * i6;
                            int i10 = 0;
                            while (true) {
                                int i11 = i34;
                                if (i10 >= i11) {
                                    break;
                                }
                                int i12 = ((i11 - i10) % i11) + i8;
                                int i13 = i9 + i10;
                                float[] fArr3 = fArr2;
                                fArr3[i12] = fArr3[i13];
                                fArr3[i12 + 1] = -fArr3[i13 + 1];
                                i10 += 2;
                            }
                        }
                    }
                });
                i30 = i35 + 1;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
            } catch (ExecutionException e2) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
            }
        }
        int i37 = this.columns;
        fArr[i37] = -fArr[1];
        fArr[1] = 0.0f;
        int i38 = i3 * i;
        int i39 = i38 + 1;
        fArr[i38 + i37] = -fArr[i39];
        fArr[i39] = 0.0f;
        fArr[i38 + i37 + 1] = 0.0f;
    }

    private void fillSymmetric(FloatLargeArray floatLargeArray) {
        long j;
        long j2;
        FloatLargeArray floatLargeArray2 = floatLargeArray;
        Class<FloatFFT_2D> cls = FloatFFT_2D.class;
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
                floatLargeArray2.setDouble(j12, (double) floatLargeArray2.getFloat(j13));
                floatLargeArray2.setDouble(j13, 0.0d);
                long j14 = j13 + 1;
                floatLargeArray2.setDouble(j12 + 1, (double) floatLargeArray2.getFloat(j14));
                floatLargeArray2.setDouble(j14, 0.0d);
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
                    final FloatLargeArray floatLargeArray3 = floatLargeArray;
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
                                long access$300 = (FloatFFT_2D.this.rowsl - j2) * j22;
                                long access$500 = (j22 * j2) + FloatFFT_2D.this.columnsl;
                                FloatLargeArray floatLargeArray = floatLargeArray3;
                                floatLargeArray.setDouble(access$500, (double) floatLargeArray.getFloat(access$300 + 1));
                                FloatLargeArray floatLargeArray2 = floatLargeArray3;
                                floatLargeArray2.setDouble(access$500 + 1, (double) (-floatLargeArray2.getFloat(access$300)));
                                j2++;
                            }
                            long j3 = j18;
                            while (true) {
                                long j4 = 2;
                                if (j3 >= j20) {
                                    break;
                                }
                                long j5 = j22 * j3;
                                long access$3002 = ((FloatFFT_2D.this.rowsl - j3) + j) * j22;
                                long access$5002 = FloatFFT_2D.this.columnsl;
                                while (true) {
                                    access$5002 += j4;
                                    if (access$5002 >= j22) {
                                        break;
                                    }
                                    long j6 = access$3002 - access$5002;
                                    long j7 = j5 + access$5002;
                                    FloatLargeArray floatLargeArray3 = floatLargeArray3;
                                    floatLargeArray3.setDouble(j7, (double) floatLargeArray3.getFloat(j6));
                                    FloatLargeArray floatLargeArray4 = floatLargeArray3;
                                    j = 1;
                                    floatLargeArray4.setDouble(j7 + 1, (double) (-floatLargeArray4.getFloat(j6 + 1)));
                                    j4 = 2;
                                }
                                j3 += j;
                            }
                            for (long j8 = j23; j8 < j24; j8++) {
                                long access$3003 = (FloatFFT_2D.this.rowsl - j8) % FloatFFT_2D.this.rowsl;
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
                                    FloatLargeArray floatLargeArray5 = floatLargeArray3;
                                    floatLargeArray5.setDouble(j14, (double) floatLargeArray5.getFloat(j15));
                                    FloatLargeArray floatLargeArray6 = floatLargeArray3;
                                    floatLargeArray6.setDouble(j14 + 1, (double) (-floatLargeArray6.getFloat(j15 + 1)));
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
                floatLargeArray2.setDouble(this.columnsl, (double) (-floatLargeArray2.getFloat(1)));
                floatLargeArray2.setDouble(1, 0.0d);
                long j25 = j6 * j4;
                long j26 = j25 + 1;
                floatLargeArray2.setDouble(j25 + this.columnsl, (double) (-floatLargeArray2.getFloat(j26)));
                floatLargeArray2.setDouble(j26, 0.0d);
                floatLargeArray2.setDouble(j25 + this.columnsl + 1, 0.0d);
            }
        }
        for (long j27 = 1; j27 < j6; j27++) {
            long j28 = j27 * j4;
            long j29 = (this.rowsl - j27) * j4;
            floatLargeArray2.setDouble(this.columnsl + j28, (double) floatLargeArray2.getFloat(j29 + 1));
            floatLargeArray2.setDouble(j28 + this.columnsl + 1, (double) (-floatLargeArray2.getFloat(j29)));
        }
        long j30 = 1;
        for (long j31 = 1; j31 < j6; j31 += j30) {
            long j32 = j31 * j4;
            long j33 = ((this.rowsl - j31) + j30) * j4;
            long j34 = this.columnsl + 2;
            while (j34 < j4) {
                long j35 = j32 + j34;
                long j36 = j33 - j34;
                floatLargeArray2.setDouble(j35, (double) floatLargeArray2.getFloat(j36));
                floatLargeArray2.setDouble(j35 + 1, (double) (-floatLargeArray2.getFloat(j36 + 1)));
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
                floatLargeArray2.setDouble(j44, (double) floatLargeArray2.getFloat(j43));
                floatLargeArray2.setDouble(j44 + 1, (double) (-floatLargeArray2.getFloat(j43 + 1)));
            }
            j38++;
            j37 = 2;
        }
        floatLargeArray2.setDouble(this.columnsl, (double) (-floatLargeArray2.getFloat(1)));
        floatLargeArray2.setDouble(1, 0.0d);
        long j252 = j6 * j4;
        long j262 = j252 + 1;
        floatLargeArray2.setDouble(j252 + this.columnsl, (double) (-floatLargeArray2.getFloat(j262)));
        floatLargeArray2.setDouble(j262, 0.0d);
        floatLargeArray2.setDouble(j252 + this.columnsl + 1, 0.0d);
    }

    private void fillSymmetric(float[][] fArr) {
        Class<FloatFFT_2D> cls = FloatFFT_2D.class;
        int i = this.columns * 2;
        int i2 = this.rows / 2;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (numberOfThreads <= 1 || !this.useThreads || i2 < numberOfThreads) {
            for (int i3 = 1; i3 < i2; i3++) {
                float[] fArr2 = fArr[i3];
                int i4 = this.columns;
                float[] fArr3 = fArr[this.rows - i3];
                fArr2[i4] = fArr3[1];
                fArr2[i4 + 1] = -fArr3[0];
            }
            for (int i5 = 1; i5 < i2; i5++) {
                int i6 = this.rows - i5;
                int i7 = this.columns;
                while (true) {
                    i7 += 2;
                    if (i7 >= i) {
                        break;
                    }
                    int i8 = i - i7;
                    float[] fArr4 = fArr[i5];
                    float[] fArr5 = fArr[i6];
                    fArr4[i7] = fArr5[i8];
                    fArr4[i7 + 1] = -fArr5[i8 + 1];
                }
            }
            int i9 = 0;
            while (true) {
                int i10 = this.rows;
                if (i9 > i10 / 2) {
                    break;
                }
                int i11 = (i10 - i9) % i10;
                for (int i12 = 0; i12 < i; i12 += 2) {
                    int i13 = (i - i12) % i;
                    float[] fArr6 = fArr[i11];
                    float[] fArr7 = fArr[i9];
                    fArr6[i13] = fArr7[i12];
                    fArr6[i13 + 1] = -fArr7[i12 + 1];
                }
                i9++;
            }
        } else {
            Future[] futureArr = new Future[numberOfThreads];
            int i14 = i2 / numberOfThreads;
            int i15 = 0;
            while (i15 < numberOfThreads) {
                final int i16 = i15 == 0 ? (i15 * i14) + 1 : i15 * i14;
                final int i17 = i15 * i14;
                final int i18 = i17 + i14;
                final float[][] fArr8 = fArr;
                final int i19 = i;
                int i20 = i15;
                final int i21 = i15 == numberOfThreads + -1 ? i18 + 1 : i18;
                futureArr[i20] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i16; i < i18; i++) {
                            int access$100 = FloatFFT_2D.this.rows - i;
                            float[] fArr = fArr8[i];
                            int access$400 = FloatFFT_2D.this.columns;
                            float[][] fArr2 = fArr8;
                            fArr[access$400] = fArr2[access$100][1];
                            fArr2[i][FloatFFT_2D.this.columns + 1] = -fArr8[access$100][0];
                        }
                        for (int i2 = i16; i2 < i18; i2++) {
                            int access$1002 = FloatFFT_2D.this.rows - i2;
                            int access$4002 = FloatFFT_2D.this.columns;
                            while (true) {
                                access$4002 += 2;
                                int i3 = i19;
                                if (access$4002 >= i3) {
                                    break;
                                }
                                int i4 = i3 - access$4002;
                                float[][] fArr3 = fArr8;
                                float[] fArr4 = fArr3[i2];
                                float[] fArr5 = fArr3[access$1002];
                                fArr4[access$4002] = fArr5[i4];
                                fArr4[access$4002 + 1] = -fArr5[i4 + 1];
                            }
                        }
                        for (int i5 = i17; i5 < i21; i5++) {
                            int access$1003 = (FloatFFT_2D.this.rows - i5) % FloatFFT_2D.this.rows;
                            int i6 = 0;
                            while (true) {
                                int i7 = i19;
                                if (i6 >= i7) {
                                    break;
                                }
                                int i8 = (i7 - i6) % i7;
                                float[][] fArr6 = fArr8;
                                float[] fArr7 = fArr6[access$1003];
                                float[] fArr8 = fArr6[i5];
                                fArr7[i8] = fArr8[i6];
                                fArr7[i8 + 1] = -fArr8[i6 + 1];
                                i6 += 2;
                            }
                        }
                    }
                });
                i15 = i20 + 1;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
            } catch (ExecutionException e2) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
            }
        }
        float[] fArr9 = fArr[0];
        int i22 = this.columns;
        fArr9[i22] = -fArr9[1];
        fArr9[1] = 0.0f;
        float[] fArr10 = fArr[i2];
        fArr10[i22] = -fArr10[1];
        fArr10[1] = 0.0f;
        fArr10[i22 + 1] = 0.0f;
    }
}
