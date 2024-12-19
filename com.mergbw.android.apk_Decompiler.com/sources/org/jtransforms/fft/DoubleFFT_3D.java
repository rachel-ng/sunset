package org.jtransforms.fft;

import java.lang.reflect.Array;
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

public class DoubleFFT_3D {
    /* access modifiers changed from: private */
    public int columns;
    /* access modifiers changed from: private */
    public long columnsl;
    /* access modifiers changed from: private */
    public DoubleFFT_1D fftColumns;
    /* access modifiers changed from: private */
    public DoubleFFT_1D fftRows;
    /* access modifiers changed from: private */
    public DoubleFFT_1D fftSlices;
    private boolean isPowerOfTwo = false;
    /* access modifiers changed from: private */
    public int rowStride;
    /* access modifiers changed from: private */
    public long rowStridel;
    /* access modifiers changed from: private */
    public int rows;
    /* access modifiers changed from: private */
    public long rowsl;
    /* access modifiers changed from: private */
    public int sliceStride;
    /* access modifiers changed from: private */
    public long sliceStridel;
    /* access modifiers changed from: private */
    public int slices;
    /* access modifiers changed from: private */
    public long slicesl;
    private boolean useThreads = false;

    public DoubleFFT_3D(long j, long j2, long j3) {
        boolean z = false;
        if (j <= 1 || j2 <= 1 || j3 <= 1) {
            throw new IllegalArgumentException("slices, rows and columns must be greater than 1");
        }
        this.slices = (int) j;
        this.rows = (int) j2;
        int i = (int) j3;
        this.columns = i;
        this.slicesl = j;
        this.rowsl = j2;
        this.columnsl = j3;
        long j4 = j2 * j3;
        this.sliceStride = (int) j4;
        this.rowStride = i;
        this.sliceStridel = j4;
        this.rowStridel = j3;
        if (j * j2 * j3 >= CommonUtils.getThreadsBeginN_3D()) {
            this.useThreads = true;
        }
        if (CommonUtils.isPowerOf2(j) && CommonUtils.isPowerOf2(j2) && CommonUtils.isPowerOf2(j3)) {
            this.isPowerOfTwo = true;
        }
        CommonUtils.setUseLargeArrays(((2 * j) * j2) * j3 > ((long) LargeArray.getMaxSizeOf32bitArray()) ? true : z);
        DoubleFFT_1D doubleFFT_1D = new DoubleFFT_1D(j);
        this.fftSlices = doubleFFT_1D;
        if (j == j2) {
            this.fftRows = doubleFFT_1D;
        } else {
            this.fftRows = new DoubleFFT_1D(j2);
        }
        if (j == j3) {
            this.fftColumns = this.fftSlices;
        } else if (j2 == j3) {
            this.fftColumns = this.fftRows;
        } else {
            this.fftColumns = new DoubleFFT_1D(j3);
        }
    }

    public void complexForward(final double[] dArr) {
        int i;
        int i2;
        Class<DoubleFFT_3D> cls = DoubleFFT_3D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = 0;
        if (this.isPowerOfTwo) {
            int i4 = this.columns;
            int i5 = i4 * 2;
            this.columns = i5;
            this.sliceStride = this.rows * i5;
            this.rowStride = i5;
            if (numberOfThreads <= 1 || !this.useThreads) {
                xdft3da_sub2(0, -1, dArr, true);
                cdft3db_sub(-1, dArr, true);
            } else {
                xdft3da_subth2(0, -1, dArr, true);
                cdft3db_subth(-1, dArr, true);
            }
            this.columns = i4;
            this.sliceStride = this.rows * i4;
            this.rowStride = i4;
            return;
        }
        int i6 = this.rows;
        int i7 = this.columns;
        this.sliceStride = i6 * 2 * i7;
        this.rowStride = i7 * 2;
        if (numberOfThreads <= 1 || !this.useThreads || (i2 = this.slices) < numberOfThreads || i6 < numberOfThreads || i7 < numberOfThreads) {
            for (int i8 = 0; i8 < this.slices; i8++) {
                int i9 = this.sliceStride * i8;
                for (int i10 = 0; i10 < this.rows; i10++) {
                    this.fftColumns.complexForward(dArr, (this.rowStride * i10) + i9);
                }
            }
            double[] dArr2 = new double[(this.rows * 2)];
            int i11 = 0;
            while (true) {
                i = this.slices;
                if (i11 >= i) {
                    break;
                }
                int i12 = this.sliceStride * i11;
                for (int i13 = 0; i13 < this.columns; i13++) {
                    int i14 = i13 * 2;
                    for (int i15 = 0; i15 < this.rows; i15++) {
                        int i16 = i12 + i14 + (this.rowStride * i15);
                        int i17 = i15 * 2;
                        dArr2[i17] = dArr[i16];
                        dArr2[i17 + 1] = dArr[i16 + 1];
                    }
                    this.fftRows.complexForward(dArr2);
                    for (int i18 = 0; i18 < this.rows; i18++) {
                        int i19 = i12 + i14 + (this.rowStride * i18);
                        int i20 = i18 * 2;
                        dArr[i19] = dArr2[i20];
                        dArr[i19 + 1] = dArr2[i20 + 1];
                    }
                }
                i11++;
            }
            double[] dArr3 = new double[(i * 2)];
            for (int i21 = 0; i21 < this.rows; i21++) {
                int i22 = this.rowStride * i21;
                for (int i23 = 0; i23 < this.columns; i23++) {
                    int i24 = i23 * 2;
                    for (int i25 = 0; i25 < this.slices; i25++) {
                        int i26 = (this.sliceStride * i25) + i22 + i24;
                        int i27 = i25 * 2;
                        dArr3[i27] = dArr[i26];
                        dArr3[i27 + 1] = dArr[i26 + 1];
                    }
                    this.fftSlices.complexForward(dArr3);
                    for (int i28 = 0; i28 < this.slices; i28++) {
                        int i29 = (this.sliceStride * i28) + i22 + i24;
                        int i30 = i28 * 2;
                        dArr[i29] = dArr3[i30];
                        dArr[i29 + 1] = dArr3[i30 + 1];
                    }
                }
            }
        } else {
            Future[] futureArr = new Future[numberOfThreads];
            int i31 = i2 / numberOfThreads;
            int i32 = 0;
            while (i32 < numberOfThreads) {
                final int i33 = i32 * i31;
                final int i34 = i32 == numberOfThreads + -1 ? this.slices : i33 + i31;
                futureArr[i32] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i33; i < i34; i++) {
                            int access$000 = DoubleFFT_3D.this.sliceStride * i;
                            for (int i2 = 0; i2 < DoubleFFT_3D.this.rows; i2++) {
                                DoubleFFT_3D.this.fftColumns.complexForward(dArr, (DoubleFFT_3D.this.rowStride * i2) + access$000);
                            }
                        }
                    }
                });
                i32++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
            } catch (ExecutionException e2) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
            }
            int i35 = 0;
            while (i35 < numberOfThreads) {
                final int i36 = i35 * i31;
                final int i37 = i35 == numberOfThreads + -1 ? this.slices : i36 + i31;
                futureArr[i35] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        double[] dArr = new double[(DoubleFFT_3D.this.rows * 2)];
                        for (int i = i36; i < i37; i++) {
                            int access$000 = DoubleFFT_3D.this.sliceStride * i;
                            for (int i2 = 0; i2 < DoubleFFT_3D.this.columns; i2++) {
                                int i3 = i2 * 2;
                                for (int i4 = 0; i4 < DoubleFFT_3D.this.rows; i4++) {
                                    int access$200 = access$000 + i3 + (DoubleFFT_3D.this.rowStride * i4);
                                    int i5 = i4 * 2;
                                    double[] dArr2 = dArr;
                                    dArr[i5] = dArr2[access$200];
                                    dArr[i5 + 1] = dArr2[access$200 + 1];
                                }
                                DoubleFFT_3D.this.fftRows.complexForward(dArr);
                                for (int i6 = 0; i6 < DoubleFFT_3D.this.rows; i6++) {
                                    int access$2002 = access$000 + i3 + (DoubleFFT_3D.this.rowStride * i6);
                                    int i7 = i6 * 2;
                                    double[] dArr3 = dArr;
                                    dArr3[access$2002] = dArr[i7];
                                    dArr3[access$2002 + 1] = dArr[i7 + 1];
                                }
                            }
                        }
                    }
                });
                i35++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e3) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e4);
            }
            int i38 = this.rows / numberOfThreads;
            while (i3 < numberOfThreads) {
                final int i39 = i3 * i38;
                final int i40 = i3 == numberOfThreads + -1 ? this.rows : i39 + i38;
                futureArr[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        double[] dArr = new double[(DoubleFFT_3D.this.slices * 2)];
                        for (int i = i39; i < i40; i++) {
                            int access$200 = DoubleFFT_3D.this.rowStride * i;
                            for (int i2 = 0; i2 < DoubleFFT_3D.this.columns; i2++) {
                                int i3 = i2 * 2;
                                for (int i4 = 0; i4 < DoubleFFT_3D.this.slices; i4++) {
                                    int access$000 = (DoubleFFT_3D.this.sliceStride * i4) + access$200 + i3;
                                    int i5 = i4 * 2;
                                    double[] dArr2 = dArr;
                                    dArr[i5] = dArr2[access$000];
                                    dArr[i5 + 1] = dArr2[access$000 + 1];
                                }
                                DoubleFFT_3D.this.fftSlices.complexForward(dArr);
                                for (int i6 = 0; i6 < DoubleFFT_3D.this.slices; i6++) {
                                    int access$0002 = (DoubleFFT_3D.this.sliceStride * i6) + access$200 + i3;
                                    int i7 = i6 * 2;
                                    double[] dArr3 = dArr;
                                    dArr3[access$0002] = dArr[i7];
                                    dArr3[access$0002 + 1] = dArr[i7 + 1];
                                }
                            }
                        }
                    }
                });
                i3++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e5) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e5);
            } catch (ExecutionException e6) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e6);
            }
        }
        int i41 = this.rows;
        int i42 = this.columns;
        this.sliceStride = i41 * i42;
        this.rowStride = i42;
    }

    public void complexForward(DoubleLargeArray doubleLargeArray) {
        long j;
        DoubleLargeArray doubleLargeArray2 = doubleLargeArray;
        Class<DoubleFFT_3D> cls = DoubleFFT_3D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        long j2 = 2;
        if (this.isPowerOfTwo) {
            long j3 = this.columnsl;
            long j4 = 2 * j3;
            this.columnsl = j4;
            this.sliceStridel = this.rowsl * j4;
            this.rowStridel = j4;
            if (numberOfThreads <= 1 || !this.useThreads) {
                xdft3da_sub2(0, -1, doubleLargeArray, true);
                cdft3db_sub(-1, doubleLargeArray2, true);
            } else {
                xdft3da_subth2(0, -1, doubleLargeArray, true);
                cdft3db_subth(-1, doubleLargeArray2, true);
            }
            this.columnsl = j3;
            this.sliceStridel = this.rowsl * j3;
            this.rowStridel = j3;
            return;
        }
        long j5 = this.rowsl;
        long j6 = this.columnsl;
        this.sliceStridel = j5 * 2 * j6;
        this.rowStridel = j6 * 2;
        if (numberOfThreads > 1 && this.useThreads) {
            long j7 = this.slicesl;
            long j8 = (long) numberOfThreads;
            if (j7 >= j8 && j5 >= j8 && j6 >= j8) {
                Future[] futureArr = new Future[numberOfThreads];
                long j9 = j7 / j8;
                int i = 0;
                while (i < numberOfThreads) {
                    final long j10 = ((long) i) * j9;
                    final long j11 = i == numberOfThreads + -1 ? this.slicesl : j10 + j9;
                    final DoubleLargeArray doubleLargeArray3 = doubleLargeArray;
                    futureArr[i] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            for (long j = j10; j < j11; j++) {
                                long access$800 = DoubleFFT_3D.this.sliceStridel * j;
                                for (long j2 = 0; j2 < DoubleFFT_3D.this.rowsl; j2++) {
                                    DoubleFFT_3D.this.fftColumns.complexForward(doubleLargeArray3, (DoubleFFT_3D.this.rowStridel * j2) + access$800);
                                }
                            }
                        }
                    });
                    i++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr);
                } catch (InterruptedException e) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
                } catch (ExecutionException e2) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
                }
                int i2 = 0;
                while (i2 < numberOfThreads) {
                    final long j12 = ((long) i2) * j9;
                    final long j13 = i2 == numberOfThreads + -1 ? this.slicesl : j12 + j9;
                    final DoubleLargeArray doubleLargeArray4 = doubleLargeArray;
                    futureArr[i2] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            long j = 2;
                            DoubleLargeArray doubleLargeArray = new DoubleLargeArray(DoubleFFT_3D.this.rowsl * 2, false);
                            long j2 = j12;
                            while (j2 < j13) {
                                long access$800 = DoubleFFT_3D.this.sliceStridel * j2;
                                long j3 = 0;
                                while (j3 < DoubleFFT_3D.this.columnsl) {
                                    long j4 = j3 * j;
                                    long j5 = 0;
                                    while (j5 < DoubleFFT_3D.this.rowsl) {
                                        long access$1000 = access$800 + j4 + (DoubleFFT_3D.this.rowStridel * j5);
                                        long j6 = j5 * j;
                                        doubleLargeArray.setDouble(j6, doubleLargeArray4.getDouble(access$1000));
                                        doubleLargeArray.setDouble(j6 + 1, doubleLargeArray4.getDouble(access$1000 + 1));
                                        j5++;
                                        j = 2;
                                    }
                                    DoubleFFT_3D.this.fftRows.complexForward(doubleLargeArray);
                                    long j7 = 0;
                                    while (j7 < DoubleFFT_3D.this.rowsl) {
                                        long access$10002 = access$800 + j4 + (DoubleFFT_3D.this.rowStridel * j7);
                                        long j8 = access$800;
                                        long j9 = j7 * 2;
                                        doubleLargeArray4.setDouble(access$10002, doubleLargeArray.getDouble(j9));
                                        doubleLargeArray4.setDouble(access$10002 + 1, doubleLargeArray.getDouble(j9 + 1));
                                        j7++;
                                        access$800 = j8;
                                    }
                                    long j10 = access$800;
                                    j3++;
                                    j = 2;
                                }
                                j2++;
                                j = 2;
                            }
                        }
                    });
                    i2++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr);
                } catch (InterruptedException e3) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e3);
                } catch (ExecutionException e4) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e4);
                }
                long j14 = this.rowsl / j8;
                int i3 = 0;
                while (i3 < numberOfThreads) {
                    final long j15 = ((long) i3) * j14;
                    final long j16 = i3 == numberOfThreads + -1 ? this.rowsl : j15 + j14;
                    final DoubleLargeArray doubleLargeArray5 = doubleLargeArray;
                    futureArr[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            long j = 2;
                            DoubleLargeArray doubleLargeArray = new DoubleLargeArray(DoubleFFT_3D.this.slicesl * 2, false);
                            long j2 = j15;
                            while (j2 < j16) {
                                long access$1000 = DoubleFFT_3D.this.rowStridel * j2;
                                long j3 = 0;
                                while (j3 < DoubleFFT_3D.this.columnsl) {
                                    long j4 = j3 * j;
                                    long j5 = 0;
                                    while (j5 < DoubleFFT_3D.this.slicesl) {
                                        long access$800 = (DoubleFFT_3D.this.sliceStridel * j5) + access$1000 + j4;
                                        long j6 = j5 * j;
                                        doubleLargeArray.setDouble(j6, doubleLargeArray5.getDouble(access$800));
                                        doubleLargeArray.setDouble(j6 + 1, doubleLargeArray5.getDouble(access$800 + 1));
                                        j5++;
                                        j = 2;
                                    }
                                    DoubleFFT_3D.this.fftSlices.complexForward(doubleLargeArray);
                                    long j7 = 0;
                                    while (j7 < DoubleFFT_3D.this.slicesl) {
                                        long access$8002 = (DoubleFFT_3D.this.sliceStridel * j7) + access$1000 + j4;
                                        long j8 = access$1000;
                                        long j9 = j7 * 2;
                                        doubleLargeArray5.setDouble(access$8002, doubleLargeArray.getDouble(j9));
                                        doubleLargeArray5.setDouble(access$8002 + 1, doubleLargeArray.getDouble(j9 + 1));
                                        j7++;
                                        access$1000 = j8;
                                    }
                                    long j10 = access$1000;
                                    j3++;
                                    j = 2;
                                }
                                j2++;
                                j = 2;
                            }
                        }
                    });
                    i3++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr);
                } catch (InterruptedException e5) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e5);
                } catch (ExecutionException e6) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e6);
                }
                long j17 = this.rowsl;
                long j18 = this.columnsl;
                this.sliceStridel = j17 * j18;
                this.rowStridel = j18;
            }
        }
        long j19 = 0;
        while (true) {
            j = 1;
            if (j19 >= this.slicesl) {
                break;
            }
            long j20 = this.sliceStridel * j19;
            for (long j21 = 0; j21 < this.rowsl; j21++) {
                this.fftColumns.complexForward(doubleLargeArray2, (this.rowStridel * j21) + j20);
            }
            j19++;
        }
        DoubleLargeArray doubleLargeArray6 = new DoubleLargeArray(this.rowsl * 2, false);
        long j22 = 0;
        while (j22 < this.slicesl) {
            long j23 = this.sliceStridel * j22;
            long j24 = 0;
            while (j24 < this.columnsl) {
                long j25 = j24 * j2;
                long j26 = 0;
                while (j26 < this.rowsl) {
                    long j27 = j23 + j25 + (this.rowStridel * j26);
                    long j28 = j22;
                    long j29 = j26 * 2;
                    doubleLargeArray6.setDouble(j29, doubleLargeArray2.getDouble(j27));
                    doubleLargeArray6.setDouble(j29 + 1, doubleLargeArray2.getDouble(j27 + 1));
                    j26++;
                    j22 = j28;
                }
                long j30 = j22;
                this.fftRows.complexForward(doubleLargeArray6);
                long j31 = 0;
                while (j31 < this.rowsl) {
                    long j32 = j23 + j25 + (this.rowStridel * j31);
                    long j33 = j23;
                    long j34 = j31 * 2;
                    doubleLargeArray2.setDouble(j32, doubleLargeArray6.getDouble(j34));
                    doubleLargeArray2.setDouble(j32 + 1, doubleLargeArray6.getDouble(j34 + 1));
                    j31++;
                    j23 = j33;
                }
                long j35 = j23;
                j = 1;
                j24++;
                j22 = j30;
                j2 = 2;
            }
            j22 += j;
            j2 = 2;
        }
        DoubleLargeArray doubleLargeArray7 = new DoubleLargeArray(this.slicesl * 2, false);
        long j36 = 0;
        while (j36 < this.rowsl) {
            long j37 = this.rowStridel * j36;
            long j38 = 0;
            while (j38 < this.columnsl) {
                long j39 = j38 * 2;
                long j40 = 0;
                while (j40 < this.slicesl) {
                    long j41 = (this.sliceStridel * j40) + j37 + j39;
                    long j42 = j36;
                    long j43 = j40 * 2;
                    doubleLargeArray7.setDouble(j43, doubleLargeArray2.getDouble(j41));
                    doubleLargeArray7.setDouble(j43 + 1, doubleLargeArray2.getDouble(j41 + 1));
                    j40++;
                    j38 = j38;
                    j36 = j42;
                }
                long j44 = j36;
                long j45 = j38;
                this.fftSlices.complexForward(doubleLargeArray7);
                for (long j46 = 0; j46 < this.slicesl; j46++) {
                    long j47 = (this.sliceStridel * j46) + j37 + j39;
                    long j48 = j46 * 2;
                    doubleLargeArray2.setDouble(j47, doubleLargeArray7.getDouble(j48));
                    doubleLargeArray2.setDouble(j47 + 1, doubleLargeArray7.getDouble(j48 + 1));
                }
                j38 = j45 + 1;
                j36 = j44;
            }
            j36++;
        }
        long j172 = this.rowsl;
        long j182 = this.columnsl;
        this.sliceStridel = j172 * j182;
        this.rowStridel = j182;
    }

    public void complexForward(final double[][][] dArr) {
        int i;
        int i2;
        Class<DoubleFFT_3D> cls = DoubleFFT_3D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = 0;
        if (this.isPowerOfTwo) {
            int i4 = this.columns;
            int i5 = i4 * 2;
            this.columns = i5;
            this.sliceStride = this.rows * i5;
            this.rowStride = i5;
            if (numberOfThreads <= 1 || !this.useThreads) {
                xdft3da_sub2(0, -1, dArr, true);
                cdft3db_sub(-1, dArr, true);
            } else {
                xdft3da_subth2(0, -1, dArr, true);
                cdft3db_subth(-1, dArr, true);
            }
            this.columns = i4;
            this.sliceStride = this.rows * i4;
            this.rowStride = i4;
        } else if (numberOfThreads <= 1 || !this.useThreads || (i2 = this.slices) < numberOfThreads || this.rows < numberOfThreads || this.columns < numberOfThreads) {
            for (int i6 = 0; i6 < this.slices; i6++) {
                for (int i7 = 0; i7 < this.rows; i7++) {
                    this.fftColumns.complexForward(dArr[i6][i7]);
                }
            }
            double[] dArr2 = new double[(this.rows * 2)];
            int i8 = 0;
            while (true) {
                i = this.slices;
                if (i8 >= i) {
                    break;
                }
                for (int i9 = 0; i9 < this.columns; i9++) {
                    int i10 = i9 * 2;
                    for (int i11 = 0; i11 < this.rows; i11++) {
                        int i12 = i11 * 2;
                        double[] dArr3 = dArr[i8][i11];
                        dArr2[i12] = dArr3[i10];
                        dArr2[i12 + 1] = dArr3[i10 + 1];
                    }
                    this.fftRows.complexForward(dArr2);
                    for (int i13 = 0; i13 < this.rows; i13++) {
                        int i14 = i13 * 2;
                        double[] dArr4 = dArr[i8][i13];
                        dArr4[i10] = dArr2[i14];
                        dArr4[i10 + 1] = dArr2[i14 + 1];
                    }
                }
                i8++;
            }
            double[] dArr5 = new double[(i * 2)];
            for (int i15 = 0; i15 < this.rows; i15++) {
                for (int i16 = 0; i16 < this.columns; i16++) {
                    int i17 = i16 * 2;
                    for (int i18 = 0; i18 < this.slices; i18++) {
                        int i19 = i18 * 2;
                        double[] dArr6 = dArr[i18][i15];
                        dArr5[i19] = dArr6[i17];
                        dArr5[i19 + 1] = dArr6[i17 + 1];
                    }
                    this.fftSlices.complexForward(dArr5);
                    for (int i20 = 0; i20 < this.slices; i20++) {
                        int i21 = i20 * 2;
                        double[] dArr7 = dArr[i20][i15];
                        dArr7[i17] = dArr5[i21];
                        dArr7[i17 + 1] = dArr5[i21 + 1];
                    }
                }
            }
        } else {
            Future[] futureArr = new Future[numberOfThreads];
            int i22 = i2 / numberOfThreads;
            int i23 = 0;
            while (i23 < numberOfThreads) {
                final int i24 = i23 * i22;
                final int i25 = i23 == numberOfThreads + -1 ? this.slices : i24 + i22;
                futureArr[i23] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i24; i < i25; i++) {
                            for (int i2 = 0; i2 < DoubleFFT_3D.this.rows; i2++) {
                                DoubleFFT_3D.this.fftColumns.complexForward(dArr[i][i2]);
                            }
                        }
                    }
                });
                i23++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
            } catch (ExecutionException e2) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
            }
            int i26 = 0;
            while (i26 < numberOfThreads) {
                final int i27 = i26 * i22;
                final int i28 = i26 == numberOfThreads + -1 ? this.slices : i27 + i22;
                futureArr[i26] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        double[] dArr = new double[(DoubleFFT_3D.this.rows * 2)];
                        for (int i = i27; i < i28; i++) {
                            for (int i2 = 0; i2 < DoubleFFT_3D.this.columns; i2++) {
                                int i3 = i2 * 2;
                                for (int i4 = 0; i4 < DoubleFFT_3D.this.rows; i4++) {
                                    int i5 = i4 * 2;
                                    double[] dArr2 = dArr[i][i4];
                                    dArr[i5] = dArr2[i3];
                                    dArr[i5 + 1] = dArr2[i3 + 1];
                                }
                                DoubleFFT_3D.this.fftRows.complexForward(dArr);
                                for (int i6 = 0; i6 < DoubleFFT_3D.this.rows; i6++) {
                                    int i7 = i6 * 2;
                                    double[] dArr3 = dArr[i][i6];
                                    dArr3[i3] = dArr[i7];
                                    dArr3[i3 + 1] = dArr[i7 + 1];
                                }
                            }
                        }
                    }
                });
                i26++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e3) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e4);
            }
            int i29 = this.rows / numberOfThreads;
            while (i3 < numberOfThreads) {
                final int i30 = i3 * i29;
                final int i31 = i3 == numberOfThreads + -1 ? this.rows : i30 + i29;
                futureArr[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        double[] dArr = new double[(DoubleFFT_3D.this.slices * 2)];
                        for (int i = i30; i < i31; i++) {
                            for (int i2 = 0; i2 < DoubleFFT_3D.this.columns; i2++) {
                                int i3 = i2 * 2;
                                for (int i4 = 0; i4 < DoubleFFT_3D.this.slices; i4++) {
                                    int i5 = i4 * 2;
                                    double[] dArr2 = dArr[i4][i];
                                    dArr[i5] = dArr2[i3];
                                    dArr[i5 + 1] = dArr2[i3 + 1];
                                }
                                DoubleFFT_3D.this.fftSlices.complexForward(dArr);
                                for (int i6 = 0; i6 < DoubleFFT_3D.this.slices; i6++) {
                                    int i7 = i6 * 2;
                                    double[] dArr3 = dArr[i6][i];
                                    dArr3[i3] = dArr[i7];
                                    dArr3[i3 + 1] = dArr[i7 + 1];
                                }
                            }
                        }
                    }
                });
                i3++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e5) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e5);
            } catch (ExecutionException e6) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e6);
            }
        }
    }

    public void complexInverse(double[] dArr, boolean z) {
        int i;
        int i2;
        double[] dArr2 = dArr;
        boolean z2 = z;
        Class<DoubleFFT_3D> cls = DoubleFFT_3D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = 0;
        if (this.isPowerOfTwo) {
            int i4 = this.columns;
            int i5 = i4 * 2;
            this.columns = i5;
            this.sliceStride = this.rows * i5;
            this.rowStride = i5;
            if (numberOfThreads <= 1 || !this.useThreads) {
                xdft3da_sub2(0, 1, dArr2, z2);
                cdft3db_sub(1, dArr2, z2);
            } else {
                xdft3da_subth2(0, 1, dArr2, z2);
                cdft3db_subth(1, dArr2, z2);
            }
            this.columns = i4;
            this.sliceStride = this.rows * i4;
            this.rowStride = i4;
            return;
        }
        int i6 = this.rows;
        int i7 = this.columns;
        this.sliceStride = i6 * 2 * i7;
        this.rowStride = i7 * 2;
        if (numberOfThreads <= 1 || !this.useThreads || (i2 = this.slices) < numberOfThreads || i6 < numberOfThreads || i7 < numberOfThreads) {
            for (int i8 = 0; i8 < this.slices; i8++) {
                int i9 = this.sliceStride * i8;
                for (int i10 = 0; i10 < this.rows; i10++) {
                    this.fftColumns.complexInverse(dArr2, (this.rowStride * i10) + i9, z2);
                }
            }
            double[] dArr3 = new double[(this.rows * 2)];
            int i11 = 0;
            while (true) {
                i = this.slices;
                if (i11 >= i) {
                    break;
                }
                int i12 = this.sliceStride * i11;
                for (int i13 = 0; i13 < this.columns; i13++) {
                    int i14 = i13 * 2;
                    for (int i15 = 0; i15 < this.rows; i15++) {
                        int i16 = i12 + i14 + (this.rowStride * i15);
                        int i17 = i15 * 2;
                        dArr3[i17] = dArr2[i16];
                        dArr3[i17 + 1] = dArr2[i16 + 1];
                    }
                    this.fftRows.complexInverse(dArr3, z2);
                    for (int i18 = 0; i18 < this.rows; i18++) {
                        int i19 = i12 + i14 + (this.rowStride * i18);
                        int i20 = i18 * 2;
                        dArr2[i19] = dArr3[i20];
                        dArr2[i19 + 1] = dArr3[i20 + 1];
                    }
                }
                i11++;
            }
            double[] dArr4 = new double[(i * 2)];
            for (int i21 = 0; i21 < this.rows; i21++) {
                int i22 = this.rowStride * i21;
                for (int i23 = 0; i23 < this.columns; i23++) {
                    int i24 = i23 * 2;
                    for (int i25 = 0; i25 < this.slices; i25++) {
                        int i26 = (this.sliceStride * i25) + i22 + i24;
                        int i27 = i25 * 2;
                        dArr4[i27] = dArr2[i26];
                        dArr4[i27 + 1] = dArr2[i26 + 1];
                    }
                    this.fftSlices.complexInverse(dArr4, z2);
                    for (int i28 = 0; i28 < this.slices; i28++) {
                        int i29 = (this.sliceStride * i28) + i22 + i24;
                        int i30 = i28 * 2;
                        dArr2[i29] = dArr4[i30];
                        dArr2[i29 + 1] = dArr4[i30 + 1];
                    }
                }
            }
        } else {
            Future[] futureArr = new Future[numberOfThreads];
            int i31 = i2 / numberOfThreads;
            int i32 = 0;
            while (i32 < numberOfThreads) {
                final int i33 = i32 * i31;
                final int i34 = i32 == numberOfThreads + -1 ? this.slices : i33 + i31;
                final double[] dArr5 = dArr;
                final boolean z3 = z;
                futureArr[i32] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i33; i < i34; i++) {
                            int access$000 = DoubleFFT_3D.this.sliceStride * i;
                            for (int i2 = 0; i2 < DoubleFFT_3D.this.rows; i2++) {
                                DoubleFFT_3D.this.fftColumns.complexInverse(dArr5, (DoubleFFT_3D.this.rowStride * i2) + access$000, z3);
                            }
                        }
                    }
                });
                i32++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
            } catch (ExecutionException e2) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
            }
            int i35 = 0;
            while (i35 < numberOfThreads) {
                final int i36 = i35 * i31;
                final int i37 = i35 == numberOfThreads + -1 ? this.slices : i36 + i31;
                final double[] dArr6 = dArr;
                final boolean z4 = z;
                futureArr[i35] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        double[] dArr = new double[(DoubleFFT_3D.this.rows * 2)];
                        for (int i = i36; i < i37; i++) {
                            int access$000 = DoubleFFT_3D.this.sliceStride * i;
                            for (int i2 = 0; i2 < DoubleFFT_3D.this.columns; i2++) {
                                int i3 = i2 * 2;
                                for (int i4 = 0; i4 < DoubleFFT_3D.this.rows; i4++) {
                                    int access$200 = access$000 + i3 + (DoubleFFT_3D.this.rowStride * i4);
                                    int i5 = i4 * 2;
                                    double[] dArr2 = dArr6;
                                    dArr[i5] = dArr2[access$200];
                                    dArr[i5 + 1] = dArr2[access$200 + 1];
                                }
                                DoubleFFT_3D.this.fftRows.complexInverse(dArr, z4);
                                for (int i6 = 0; i6 < DoubleFFT_3D.this.rows; i6++) {
                                    int access$2002 = access$000 + i3 + (DoubleFFT_3D.this.rowStride * i6);
                                    int i7 = i6 * 2;
                                    double[] dArr3 = dArr6;
                                    dArr3[access$2002] = dArr[i7];
                                    dArr3[access$2002 + 1] = dArr[i7 + 1];
                                }
                            }
                        }
                    }
                });
                i35++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e3) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e4);
            }
            int i38 = this.rows / numberOfThreads;
            while (i3 < numberOfThreads) {
                final int i39 = i3 * i38;
                final int i40 = i3 == numberOfThreads + -1 ? this.rows : i39 + i38;
                final double[] dArr7 = dArr;
                final boolean z5 = z;
                futureArr[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        double[] dArr = new double[(DoubleFFT_3D.this.slices * 2)];
                        for (int i = i39; i < i40; i++) {
                            int access$200 = DoubleFFT_3D.this.rowStride * i;
                            for (int i2 = 0; i2 < DoubleFFT_3D.this.columns; i2++) {
                                int i3 = i2 * 2;
                                for (int i4 = 0; i4 < DoubleFFT_3D.this.slices; i4++) {
                                    int access$000 = (DoubleFFT_3D.this.sliceStride * i4) + access$200 + i3;
                                    int i5 = i4 * 2;
                                    double[] dArr2 = dArr7;
                                    dArr[i5] = dArr2[access$000];
                                    dArr[i5 + 1] = dArr2[access$000 + 1];
                                }
                                DoubleFFT_3D.this.fftSlices.complexInverse(dArr, z5);
                                for (int i6 = 0; i6 < DoubleFFT_3D.this.slices; i6++) {
                                    int access$0002 = (DoubleFFT_3D.this.sliceStride * i6) + access$200 + i3;
                                    int i7 = i6 * 2;
                                    double[] dArr3 = dArr7;
                                    dArr3[access$0002] = dArr[i7];
                                    dArr3[access$0002 + 1] = dArr[i7 + 1];
                                }
                            }
                        }
                    }
                });
                i3++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e5) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e5);
            } catch (ExecutionException e6) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e6);
            }
        }
        int i41 = this.rows;
        int i42 = this.columns;
        this.sliceStride = i41 * i42;
        this.rowStride = i42;
    }

    public void complexInverse(DoubleLargeArray doubleLargeArray, boolean z) {
        DoubleLargeArray doubleLargeArray2 = doubleLargeArray;
        boolean z2 = z;
        Class<DoubleFFT_3D> cls = DoubleFFT_3D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        long j = 2;
        if (this.isPowerOfTwo) {
            long j2 = this.columnsl;
            long j3 = 2 * j2;
            this.columnsl = j3;
            this.sliceStridel = this.rowsl * j3;
            this.rowStridel = j3;
            if (numberOfThreads <= 1 || !this.useThreads) {
                xdft3da_sub2(0, 1, doubleLargeArray, z);
                cdft3db_sub(1, doubleLargeArray2, z2);
            } else {
                xdft3da_subth2(0, 1, doubleLargeArray, z);
                cdft3db_subth(1, doubleLargeArray2, z2);
            }
            this.columnsl = j2;
            this.sliceStridel = this.rowsl * j2;
            this.rowStridel = j2;
            return;
        }
        long j4 = this.rowsl;
        long j5 = this.columnsl;
        this.sliceStridel = j4 * 2 * j5;
        this.rowStridel = j5 * 2;
        if (numberOfThreads > 1 && this.useThreads) {
            long j6 = this.slicesl;
            long j7 = (long) numberOfThreads;
            if (j6 >= j7 && j4 >= j7 && j5 >= j7) {
                Future[] futureArr = new Future[numberOfThreads];
                long j8 = j6 / j7;
                int i = 0;
                while (i < numberOfThreads) {
                    final long j9 = ((long) i) * j8;
                    final long j10 = i == numberOfThreads + -1 ? this.slicesl : j9 + j8;
                    final DoubleLargeArray doubleLargeArray3 = doubleLargeArray;
                    final boolean z3 = z;
                    futureArr[i] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            for (long j = j9; j < j10; j++) {
                                long access$800 = DoubleFFT_3D.this.sliceStridel * j;
                                for (long j2 = 0; j2 < DoubleFFT_3D.this.rowsl; j2++) {
                                    DoubleFFT_3D.this.fftColumns.complexInverse(doubleLargeArray3, (DoubleFFT_3D.this.rowStridel * j2) + access$800, z3);
                                }
                            }
                        }
                    });
                    i++;
                }
                String str = null;
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr);
                } catch (InterruptedException e) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
                } catch (ExecutionException e2) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
                }
                int i2 = 0;
                while (i2 < numberOfThreads) {
                    final long j11 = ((long) i2) * j8;
                    final long j12 = i2 == numberOfThreads + -1 ? this.slicesl : j11 + j8;
                    final DoubleLargeArray doubleLargeArray4 = doubleLargeArray;
                    long j13 = j8;
                    String str2 = str;
                    final boolean z4 = z;
                    futureArr[i2] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            long j = 2;
                            DoubleLargeArray doubleLargeArray = new DoubleLargeArray(DoubleFFT_3D.this.rowsl * 2, false);
                            long j2 = j11;
                            while (j2 < j12) {
                                long access$800 = DoubleFFT_3D.this.sliceStridel * j2;
                                long j3 = 0;
                                while (j3 < DoubleFFT_3D.this.columnsl) {
                                    long j4 = j3 * j;
                                    long j5 = 0;
                                    while (j5 < DoubleFFT_3D.this.rowsl) {
                                        long access$1000 = access$800 + j4 + (DoubleFFT_3D.this.rowStridel * j5);
                                        long j6 = j5 * j;
                                        doubleLargeArray.setDouble(j6, doubleLargeArray4.getDouble(access$1000));
                                        doubleLargeArray.setDouble(j6 + 1, doubleLargeArray4.getDouble(access$1000 + 1));
                                        j5++;
                                        j = 2;
                                    }
                                    DoubleFFT_3D.this.fftRows.complexInverse(doubleLargeArray, z4);
                                    long j7 = 0;
                                    while (j7 < DoubleFFT_3D.this.rowsl) {
                                        long access$10002 = access$800 + j4 + (DoubleFFT_3D.this.rowStridel * j7);
                                        long j8 = access$800;
                                        long j9 = j7 * 2;
                                        doubleLargeArray4.setDouble(access$10002, doubleLargeArray.getDouble(j9));
                                        doubleLargeArray4.setDouble(access$10002 + 1, doubleLargeArray.getDouble(j9 + 1));
                                        j7++;
                                        access$800 = j8;
                                    }
                                    long j10 = access$800;
                                    j3++;
                                    j = 2;
                                }
                                j2++;
                                j = 2;
                            }
                        }
                    });
                    i2++;
                    str = str2;
                    j8 = j13;
                }
                String str3 = str;
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr);
                } catch (InterruptedException e3) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e3);
                } catch (ExecutionException e4) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e4);
                }
                long j14 = this.rowsl / j7;
                int i3 = 0;
                while (i3 < numberOfThreads) {
                    final long j15 = ((long) i3) * j14;
                    final long j16 = i3 == numberOfThreads + -1 ? this.rowsl : j15 + j14;
                    final DoubleLargeArray doubleLargeArray5 = doubleLargeArray;
                    final boolean z5 = z;
                    futureArr[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            long j = 2;
                            DoubleLargeArray doubleLargeArray = new DoubleLargeArray(DoubleFFT_3D.this.slicesl * 2, false);
                            long j2 = j15;
                            while (j2 < j16) {
                                long access$1000 = DoubleFFT_3D.this.rowStridel * j2;
                                long j3 = 0;
                                while (j3 < DoubleFFT_3D.this.columnsl) {
                                    long j4 = j3 * j;
                                    long j5 = 0;
                                    while (j5 < DoubleFFT_3D.this.slicesl) {
                                        long access$800 = (DoubleFFT_3D.this.sliceStridel * j5) + access$1000 + j4;
                                        long j6 = j5 * j;
                                        doubleLargeArray.setDouble(j6, doubleLargeArray5.getDouble(access$800));
                                        doubleLargeArray.setDouble(j6 + 1, doubleLargeArray5.getDouble(access$800 + 1));
                                        j5++;
                                        j = 2;
                                    }
                                    DoubleFFT_3D.this.fftSlices.complexInverse(doubleLargeArray, z5);
                                    long j7 = 0;
                                    while (j7 < DoubleFFT_3D.this.slicesl) {
                                        long access$8002 = (DoubleFFT_3D.this.sliceStridel * j7) + access$1000 + j4;
                                        long j8 = access$1000;
                                        long j9 = j7 * 2;
                                        doubleLargeArray5.setDouble(access$8002, doubleLargeArray.getDouble(j9));
                                        doubleLargeArray5.setDouble(access$8002 + 1, doubleLargeArray.getDouble(j9 + 1));
                                        j7++;
                                        access$1000 = j8;
                                    }
                                    long j10 = access$1000;
                                    j3++;
                                    j = 2;
                                }
                                j2++;
                                j = 2;
                            }
                        }
                    });
                    i3++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr);
                } catch (InterruptedException e5) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e5);
                } catch (ExecutionException e6) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e6);
                }
                long j17 = this.rowsl;
                long j18 = this.columnsl;
                this.sliceStridel = j17 * j18;
                this.rowStridel = j18;
            }
        }
        for (long j19 = 0; j19 < this.slicesl; j19++) {
            long j20 = this.sliceStridel * j19;
            for (long j21 = 0; j21 < this.rowsl; j21++) {
                this.fftColumns.complexInverse(doubleLargeArray, (this.rowStridel * j21) + j20, z);
            }
            DoubleLargeArray doubleLargeArray6 = doubleLargeArray;
            boolean z6 = z;
        }
        DoubleLargeArray doubleLargeArray7 = doubleLargeArray;
        boolean z7 = z;
        DoubleLargeArray doubleLargeArray8 = new DoubleLargeArray(this.rowsl * 2, false);
        long j22 = 0;
        while (j22 < this.slicesl) {
            long j23 = this.sliceStridel * j22;
            long j24 = 0;
            while (j24 < this.columnsl) {
                long j25 = j24 * j;
                long j26 = 0;
                while (j26 < this.rowsl) {
                    long j27 = j23 + j25 + (this.rowStridel * j26);
                    long j28 = j22;
                    long j29 = j26 * 2;
                    doubleLargeArray8.setDouble(j29, doubleLargeArray7.getDouble(j27));
                    doubleLargeArray8.setDouble(j29 + 1, doubleLargeArray7.getDouble(j27 + 1));
                    j26++;
                    j23 = j23;
                    j22 = j28;
                }
                long j30 = j22;
                long j31 = j23;
                this.fftRows.complexInverse(doubleLargeArray8, z7);
                long j32 = 0;
                while (j32 < this.rowsl) {
                    long j33 = j31 + j25 + (this.rowStridel * j32);
                    long j34 = j25;
                    long j35 = j32 * 2;
                    doubleLargeArray7.setDouble(j33, doubleLargeArray8.getDouble(j35));
                    doubleLargeArray7.setDouble(j33 + 1, doubleLargeArray8.getDouble(j35 + 1));
                    j32++;
                    j25 = j34;
                }
                j24++;
                j23 = j31;
                j22 = j30;
                j = 2;
            }
            j22++;
            j = 2;
        }
        DoubleLargeArray doubleLargeArray9 = new DoubleLargeArray(this.slicesl * 2, false);
        long j36 = 0;
        while (j36 < this.rowsl) {
            long j37 = this.rowStridel * j36;
            long j38 = 0;
            while (j38 < this.columnsl) {
                long j39 = j38 * 2;
                long j40 = 0;
                while (j40 < this.slicesl) {
                    long j41 = (this.sliceStridel * j40) + j37 + j39;
                    long j42 = j36;
                    long j43 = j40 * 2;
                    doubleLargeArray9.setDouble(j43, doubleLargeArray7.getDouble(j41));
                    doubleLargeArray9.setDouble(j43 + 1, doubleLargeArray7.getDouble(j41 + 1));
                    j40++;
                    j38 = j38;
                    j36 = j42;
                }
                long j44 = j36;
                long j45 = j38;
                this.fftSlices.complexInverse(doubleLargeArray9, z7);
                for (long j46 = 0; j46 < this.slicesl; j46++) {
                    long j47 = (this.sliceStridel * j46) + j37 + j39;
                    long j48 = j46 * 2;
                    doubleLargeArray7.setDouble(j47, doubleLargeArray9.getDouble(j48));
                    doubleLargeArray7.setDouble(j47 + 1, doubleLargeArray9.getDouble(j48 + 1));
                }
                j38 = j45 + 1;
                j36 = j44;
            }
            j36++;
        }
        long j172 = this.rowsl;
        long j182 = this.columnsl;
        this.sliceStridel = j172 * j182;
        this.rowStridel = j182;
    }

    public void complexInverse(double[][][] dArr, boolean z) {
        int i;
        int i2;
        double[][][] dArr2 = dArr;
        boolean z2 = z;
        Class<DoubleFFT_3D> cls = DoubleFFT_3D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = 0;
        if (this.isPowerOfTwo) {
            int i4 = this.columns;
            int i5 = i4 * 2;
            this.columns = i5;
            this.sliceStride = this.rows * i5;
            this.rowStride = i5;
            if (numberOfThreads <= 1 || !this.useThreads) {
                xdft3da_sub2(0, 1, dArr2, z2);
                cdft3db_sub(1, dArr2, z2);
            } else {
                xdft3da_subth2(0, 1, dArr2, z2);
                cdft3db_subth(1, dArr2, z2);
            }
            this.columns = i4;
            this.sliceStride = this.rows * i4;
            this.rowStride = i4;
        } else if (numberOfThreads <= 1 || !this.useThreads || (i2 = this.slices) < numberOfThreads || this.rows < numberOfThreads || this.columns < numberOfThreads) {
            for (int i6 = 0; i6 < this.slices; i6++) {
                for (int i7 = 0; i7 < this.rows; i7++) {
                    this.fftColumns.complexInverse(dArr2[i6][i7], z2);
                }
            }
            double[] dArr3 = new double[(this.rows * 2)];
            int i8 = 0;
            while (true) {
                i = this.slices;
                if (i8 >= i) {
                    break;
                }
                for (int i9 = 0; i9 < this.columns; i9++) {
                    int i10 = i9 * 2;
                    for (int i11 = 0; i11 < this.rows; i11++) {
                        int i12 = i11 * 2;
                        double[] dArr4 = dArr2[i8][i11];
                        dArr3[i12] = dArr4[i10];
                        dArr3[i12 + 1] = dArr4[i10 + 1];
                    }
                    this.fftRows.complexInverse(dArr3, z2);
                    for (int i13 = 0; i13 < this.rows; i13++) {
                        int i14 = i13 * 2;
                        double[] dArr5 = dArr2[i8][i13];
                        dArr5[i10] = dArr3[i14];
                        dArr5[i10 + 1] = dArr3[i14 + 1];
                    }
                }
                i8++;
            }
            double[] dArr6 = new double[(i * 2)];
            for (int i15 = 0; i15 < this.rows; i15++) {
                for (int i16 = 0; i16 < this.columns; i16++) {
                    int i17 = i16 * 2;
                    for (int i18 = 0; i18 < this.slices; i18++) {
                        int i19 = i18 * 2;
                        double[] dArr7 = dArr2[i18][i15];
                        dArr6[i19] = dArr7[i17];
                        dArr6[i19 + 1] = dArr7[i17 + 1];
                    }
                    this.fftSlices.complexInverse(dArr6, z2);
                    for (int i20 = 0; i20 < this.slices; i20++) {
                        int i21 = i20 * 2;
                        double[] dArr8 = dArr2[i20][i15];
                        dArr8[i17] = dArr6[i21];
                        dArr8[i17 + 1] = dArr6[i21 + 1];
                    }
                }
            }
        } else {
            Future[] futureArr = new Future[numberOfThreads];
            int i22 = i2 / numberOfThreads;
            int i23 = 0;
            while (i23 < numberOfThreads) {
                final int i24 = i23 * i22;
                final int i25 = i23 == numberOfThreads + -1 ? this.slices : i24 + i22;
                final double[][][] dArr9 = dArr;
                final boolean z3 = z;
                futureArr[i23] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i24; i < i25; i++) {
                            for (int i2 = 0; i2 < DoubleFFT_3D.this.rows; i2++) {
                                DoubleFFT_3D.this.fftColumns.complexInverse(dArr9[i][i2], z3);
                            }
                        }
                    }
                });
                i23++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
            } catch (ExecutionException e2) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
            }
            int i26 = 0;
            while (i26 < numberOfThreads) {
                final int i27 = i26 * i22;
                final int i28 = i26 == numberOfThreads + -1 ? this.slices : i27 + i22;
                final double[][][] dArr10 = dArr;
                final boolean z4 = z;
                futureArr[i26] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        double[] dArr = new double[(DoubleFFT_3D.this.rows * 2)];
                        for (int i = i27; i < i28; i++) {
                            for (int i2 = 0; i2 < DoubleFFT_3D.this.columns; i2++) {
                                int i3 = i2 * 2;
                                for (int i4 = 0; i4 < DoubleFFT_3D.this.rows; i4++) {
                                    int i5 = i4 * 2;
                                    double[] dArr2 = dArr10[i][i4];
                                    dArr[i5] = dArr2[i3];
                                    dArr[i5 + 1] = dArr2[i3 + 1];
                                }
                                DoubleFFT_3D.this.fftRows.complexInverse(dArr, z4);
                                for (int i6 = 0; i6 < DoubleFFT_3D.this.rows; i6++) {
                                    int i7 = i6 * 2;
                                    double[] dArr3 = dArr10[i][i6];
                                    dArr3[i3] = dArr[i7];
                                    dArr3[i3 + 1] = dArr[i7 + 1];
                                }
                            }
                        }
                    }
                });
                i26++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e3) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e4);
            }
            int i29 = this.rows / numberOfThreads;
            while (i3 < numberOfThreads) {
                final int i30 = i3 * i29;
                final int i31 = i3 == numberOfThreads + -1 ? this.rows : i30 + i29;
                final double[][][] dArr11 = dArr;
                final boolean z5 = z;
                futureArr[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        double[] dArr = new double[(DoubleFFT_3D.this.slices * 2)];
                        for (int i = i30; i < i31; i++) {
                            for (int i2 = 0; i2 < DoubleFFT_3D.this.columns; i2++) {
                                int i3 = i2 * 2;
                                for (int i4 = 0; i4 < DoubleFFT_3D.this.slices; i4++) {
                                    int i5 = i4 * 2;
                                    double[] dArr2 = dArr11[i4][i];
                                    dArr[i5] = dArr2[i3];
                                    dArr[i5 + 1] = dArr2[i3 + 1];
                                }
                                DoubleFFT_3D.this.fftSlices.complexInverse(dArr, z5);
                                for (int i6 = 0; i6 < DoubleFFT_3D.this.slices; i6++) {
                                    int i7 = i6 * 2;
                                    double[] dArr3 = dArr11[i6][i];
                                    dArr3[i3] = dArr[i7];
                                    dArr3[i3 + 1] = dArr[i7 + 1];
                                }
                            }
                        }
                    }
                });
                i3++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e5) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e5);
            } catch (ExecutionException e6) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e6);
            }
        }
    }

    public void realForward(double[] dArr) {
        if (!this.isPowerOfTwo) {
            throw new IllegalArgumentException("slices, rows and columns must be power of two numbers");
        } else if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
            xdft3da_sub1(1, -1, dArr, true);
            cdft3db_sub(-1, dArr, true);
            rdft3d_sub(1, dArr);
        } else {
            xdft3da_subth1(1, -1, dArr, true);
            cdft3db_subth(-1, dArr, true);
            rdft3d_sub(1, dArr);
        }
    }

    public void realForward(DoubleLargeArray doubleLargeArray) {
        if (!this.isPowerOfTwo) {
            throw new IllegalArgumentException("slices, rows and columns must be power of two numbers");
        } else if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
            xdft3da_sub1(1, -1, doubleLargeArray, true);
            cdft3db_sub(-1, doubleLargeArray, true);
            rdft3d_sub(1, doubleLargeArray);
        } else {
            xdft3da_subth1(1, -1, doubleLargeArray, true);
            cdft3db_subth(-1, doubleLargeArray, true);
            rdft3d_sub(1, doubleLargeArray);
        }
    }

    public void realForward(double[][][] dArr) {
        if (!this.isPowerOfTwo) {
            throw new IllegalArgumentException("slices, rows and columns must be power of two numbers");
        } else if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
            xdft3da_sub1(1, -1, dArr, true);
            cdft3db_sub(-1, dArr, true);
            rdft3d_sub(1, dArr);
        } else {
            xdft3da_subth1(1, -1, dArr, true);
            cdft3db_subth(-1, dArr, true);
            rdft3d_sub(1, dArr);
        }
    }

    public void realForwardFull(double[] dArr) {
        if (this.isPowerOfTwo) {
            if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
                xdft3da_sub2(1, -1, dArr, true);
                cdft3db_sub(-1, dArr, true);
                rdft3d_sub(1, dArr);
            } else {
                xdft3da_subth2(1, -1, dArr, true);
                cdft3db_subth(-1, dArr, true);
                rdft3d_sub(1, dArr);
            }
            fillSymmetric(dArr);
            return;
        }
        mixedRadixRealForwardFull(dArr);
    }

    public void realForwardFull(DoubleLargeArray doubleLargeArray) {
        if (this.isPowerOfTwo) {
            if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
                xdft3da_sub2(1, -1, doubleLargeArray, true);
                cdft3db_sub(-1, doubleLargeArray, true);
                rdft3d_sub(1, doubleLargeArray);
            } else {
                xdft3da_subth2(1, -1, doubleLargeArray, true);
                cdft3db_subth(-1, doubleLargeArray, true);
                rdft3d_sub(1, doubleLargeArray);
            }
            fillSymmetric(doubleLargeArray);
            return;
        }
        mixedRadixRealForwardFull(doubleLargeArray);
    }

    public void realForwardFull(double[][][] dArr) {
        if (this.isPowerOfTwo) {
            if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
                xdft3da_sub2(1, -1, dArr, true);
                cdft3db_sub(-1, dArr, true);
                rdft3d_sub(1, dArr);
            } else {
                xdft3da_subth2(1, -1, dArr, true);
                cdft3db_subth(-1, dArr, true);
                rdft3d_sub(1, dArr);
            }
            fillSymmetric(dArr);
            return;
        }
        mixedRadixRealForwardFull(dArr);
    }

    public void realInverse(double[] dArr, boolean z) {
        if (!this.isPowerOfTwo) {
            throw new IllegalArgumentException("slices, rows and columns must be power of two numbers");
        } else if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
            rdft3d_sub(-1, dArr);
            cdft3db_sub(1, dArr, z);
            xdft3da_sub1(1, 1, dArr, z);
        } else {
            rdft3d_sub(-1, dArr);
            cdft3db_subth(1, dArr, z);
            xdft3da_subth1(1, 1, dArr, z);
        }
    }

    public void realInverse(DoubleLargeArray doubleLargeArray, boolean z) {
        if (!this.isPowerOfTwo) {
            throw new IllegalArgumentException("slices, rows and columns must be power of two numbers");
        } else if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
            rdft3d_sub(-1, doubleLargeArray);
            cdft3db_sub(1, doubleLargeArray, z);
            xdft3da_sub1(1, 1, doubleLargeArray, z);
        } else {
            rdft3d_sub(-1, doubleLargeArray);
            cdft3db_subth(1, doubleLargeArray, z);
            xdft3da_subth1(1, 1, doubleLargeArray, z);
        }
    }

    public void realInverse(double[][][] dArr, boolean z) {
        if (!this.isPowerOfTwo) {
            throw new IllegalArgumentException("slices, rows and columns must be power of two numbers");
        } else if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
            rdft3d_sub(-1, dArr);
            cdft3db_sub(1, dArr, z);
            xdft3da_sub1(1, 1, dArr, z);
        } else {
            rdft3d_sub(-1, dArr);
            cdft3db_subth(1, dArr, z);
            xdft3da_subth1(1, 1, dArr, z);
        }
    }

    public void realInverseFull(double[] dArr, boolean z) {
        if (this.isPowerOfTwo) {
            if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
                xdft3da_sub2(1, 1, dArr, z);
                cdft3db_sub(1, dArr, z);
                rdft3d_sub(1, dArr);
            } else {
                xdft3da_subth2(1, 1, dArr, z);
                cdft3db_subth(1, dArr, z);
                rdft3d_sub(1, dArr);
            }
            fillSymmetric(dArr);
            return;
        }
        mixedRadixRealInverseFull(dArr, z);
    }

    public void realInverseFull(DoubleLargeArray doubleLargeArray, boolean z) {
        if (this.isPowerOfTwo) {
            if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
                xdft3da_sub2(1, 1, doubleLargeArray, z);
                cdft3db_sub(1, doubleLargeArray, z);
                rdft3d_sub(1, doubleLargeArray);
            } else {
                xdft3da_subth2(1, 1, doubleLargeArray, z);
                cdft3db_subth(1, doubleLargeArray, z);
                rdft3d_sub(1, doubleLargeArray);
            }
            fillSymmetric(doubleLargeArray);
            return;
        }
        mixedRadixRealInverseFull(doubleLargeArray, z);
    }

    public void realInverseFull(double[][][] dArr, boolean z) {
        if (this.isPowerOfTwo) {
            if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
                xdft3da_sub2(1, 1, dArr, z);
                cdft3db_sub(1, dArr, z);
                rdft3d_sub(1, dArr);
            } else {
                xdft3da_subth2(1, 1, dArr, z);
                cdft3db_subth(1, dArr, z);
                rdft3d_sub(1, dArr);
            }
            fillSymmetric(dArr);
            return;
        }
        mixedRadixRealInverseFull(dArr, z);
    }

    private void mixedRadixRealForwardFull(double[][][] dArr) {
        int i;
        int i2;
        int i3;
        final double[][][] dArr2 = dArr;
        Class<DoubleFFT_3D> cls = DoubleFFT_3D.class;
        int i4 = this.rows;
        double[] dArr3 = new double[(i4 * 2)];
        int i5 = (i4 / 2) + 1;
        int i6 = this.columns * 2;
        if (i4 % 2 == 0) {
            i = i4 / 2;
        } else {
            i = (i4 + 1) / 2;
        }
        int i7 = i;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (numberOfThreads <= 1 || !this.useThreads || (i3 = this.slices) < numberOfThreads || this.columns < numberOfThreads || i5 < numberOfThreads) {
            for (int i8 = 0; i8 < this.slices; i8++) {
                for (int i9 = 0; i9 < this.rows; i9++) {
                    this.fftColumns.realForwardFull(dArr2[i8][i9]);
                }
            }
            int i10 = 0;
            while (true) {
                i2 = this.slices;
                if (i10 >= i2) {
                    break;
                }
                for (int i11 = 0; i11 < this.columns; i11++) {
                    int i12 = i11 * 2;
                    for (int i13 = 0; i13 < this.rows; i13++) {
                        int i14 = i13 * 2;
                        double[] dArr4 = dArr2[i10][i13];
                        dArr3[i14] = dArr4[i12];
                        dArr3[i14 + 1] = dArr4[i12 + 1];
                    }
                    this.fftRows.complexForward(dArr3);
                    for (int i15 = 0; i15 < this.rows; i15++) {
                        int i16 = i15 * 2;
                        double[] dArr5 = dArr2[i10][i15];
                        dArr5[i12] = dArr3[i16];
                        dArr5[i12 + 1] = dArr3[i16 + 1];
                    }
                }
                i10++;
            }
            double[] dArr6 = new double[(i2 * 2)];
            for (int i17 = 0; i17 < i5; i17++) {
                for (int i18 = 0; i18 < this.columns; i18++) {
                    int i19 = i18 * 2;
                    for (int i20 = 0; i20 < this.slices; i20++) {
                        int i21 = i20 * 2;
                        double[] dArr7 = dArr2[i20][i17];
                        dArr6[i21] = dArr7[i19];
                        dArr6[i21 + 1] = dArr7[i19 + 1];
                    }
                    this.fftSlices.complexForward(dArr6);
                    for (int i22 = 0; i22 < this.slices; i22++) {
                        int i23 = i22 * 2;
                        double[] dArr8 = dArr2[i22][i17];
                        dArr8[i19] = dArr6[i23];
                        dArr8[i19 + 1] = dArr6[i23 + 1];
                    }
                }
            }
            int i24 = 0;
            while (true) {
                int i25 = this.slices;
                if (i24 < i25) {
                    int i26 = (i25 - i24) % i25;
                    for (int i27 = 1; i27 < i7; i27++) {
                        int i28 = this.rows - i27;
                        for (int i29 = 0; i29 < this.columns; i29++) {
                            int i30 = i29 * 2;
                            int i31 = i6 - i30;
                            double[] dArr9 = dArr2[i26][i28];
                            double[] dArr10 = dArr2[i24][i27];
                            dArr9[i31 % i6] = dArr10[i30];
                            dArr9[(i31 + 1) % i6] = -dArr10[i30 + 1];
                        }
                    }
                    i24++;
                } else {
                    return;
                }
            }
        } else {
            Future[] futureArr = new Future[numberOfThreads];
            int i32 = i3 / numberOfThreads;
            int i33 = 0;
            while (i33 < numberOfThreads) {
                final int i34 = i33 * i32;
                final int i35 = i33 == numberOfThreads + -1 ? this.slices : i34 + i32;
                futureArr[i33] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i34; i < i35; i++) {
                            for (int i2 = 0; i2 < DoubleFFT_3D.this.rows; i2++) {
                                DoubleFFT_3D.this.fftColumns.realForwardFull(dArr2[i][i2]);
                            }
                        }
                    }
                });
                i33++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
            } catch (ExecutionException e2) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
            }
            int i36 = 0;
            while (i36 < numberOfThreads) {
                final int i37 = i36 * i32;
                final int i38 = i36 == numberOfThreads + -1 ? this.slices : i37 + i32;
                futureArr[i36] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        double[] dArr = new double[(DoubleFFT_3D.this.rows * 2)];
                        for (int i = i37; i < i38; i++) {
                            for (int i2 = 0; i2 < DoubleFFT_3D.this.columns; i2++) {
                                int i3 = i2 * 2;
                                for (int i4 = 0; i4 < DoubleFFT_3D.this.rows; i4++) {
                                    int i5 = i4 * 2;
                                    double[] dArr2 = dArr2[i][i4];
                                    dArr[i5] = dArr2[i3];
                                    dArr[i5 + 1] = dArr2[i3 + 1];
                                }
                                DoubleFFT_3D.this.fftRows.complexForward(dArr);
                                for (int i6 = 0; i6 < DoubleFFT_3D.this.rows; i6++) {
                                    int i7 = i6 * 2;
                                    double[] dArr3 = dArr2[i][i6];
                                    dArr3[i3] = dArr[i7];
                                    dArr3[i3 + 1] = dArr[i7 + 1];
                                }
                            }
                        }
                    }
                });
                i36++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e3) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e4);
            }
            int i39 = i5 / numberOfThreads;
            int i40 = 0;
            while (i40 < numberOfThreads) {
                final int i41 = i40 * i39;
                final int i42 = i40 == numberOfThreads + -1 ? i5 : i41 + i39;
                futureArr[i40] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        double[] dArr = new double[(DoubleFFT_3D.this.slices * 2)];
                        for (int i = i41; i < i42; i++) {
                            for (int i2 = 0; i2 < DoubleFFT_3D.this.columns; i2++) {
                                int i3 = i2 * 2;
                                for (int i4 = 0; i4 < DoubleFFT_3D.this.slices; i4++) {
                                    int i5 = i4 * 2;
                                    double[] dArr2 = dArr2[i4][i];
                                    dArr[i5] = dArr2[i3];
                                    dArr[i5 + 1] = dArr2[i3 + 1];
                                }
                                DoubleFFT_3D.this.fftSlices.complexForward(dArr);
                                for (int i6 = 0; i6 < DoubleFFT_3D.this.slices; i6++) {
                                    int i7 = i6 * 2;
                                    double[] dArr3 = dArr2[i6][i];
                                    dArr3[i3] = dArr[i7];
                                    dArr3[i3 + 1] = dArr[i7 + 1];
                                }
                            }
                        }
                    }
                });
                i40++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e5) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e5);
            } catch (ExecutionException e6) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e6);
            }
            int i43 = this.slices / numberOfThreads;
            int i44 = 0;
            while (i44 < numberOfThreads) {
                final int i45 = i44 * i43;
                final int i46 = i44 == numberOfThreads + -1 ? this.slices : i45 + i43;
                final int i47 = i7;
                final int i48 = i6;
                int i49 = i44;
                final double[][][] dArr11 = dArr;
                futureArr[i49] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i45; i < i46; i++) {
                            int access$600 = (DoubleFFT_3D.this.slices - i) % DoubleFFT_3D.this.slices;
                            for (int i2 = 1; i2 < i47; i2++) {
                                int access$100 = DoubleFFT_3D.this.rows - i2;
                                for (int i3 = 0; i3 < DoubleFFT_3D.this.columns; i3++) {
                                    int i4 = i3 * 2;
                                    int i5 = i48;
                                    int i6 = i5 - i4;
                                    double[][][] dArr = dArr11;
                                    double[] dArr2 = dArr[access$600][access$100];
                                    double[] dArr3 = dArr[i][i2];
                                    dArr2[i6 % i5] = dArr3[i4];
                                    dArr2[(i6 + 1) % i5] = -dArr3[i4 + 1];
                                }
                            }
                        }
                    }
                });
                i44 = i49 + 1;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e7) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e7);
            } catch (ExecutionException e8) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e8);
            }
        }
    }

    private void mixedRadixRealInverseFull(double[][][] dArr, boolean z) {
        int i;
        int i2;
        int i3;
        boolean z2 = z;
        Class<DoubleFFT_3D> cls = DoubleFFT_3D.class;
        int i4 = this.rows;
        double[] dArr2 = new double[(i4 * 2)];
        int i5 = (i4 / 2) + 1;
        int i6 = this.columns * 2;
        if (i4 % 2 == 0) {
            i = i4 / 2;
        } else {
            i = (i4 + 1) / 2;
        }
        int i7 = i;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (numberOfThreads <= 1 || !this.useThreads || (i3 = this.slices) < numberOfThreads || this.columns < numberOfThreads || i5 < numberOfThreads) {
            for (int i8 = 0; i8 < this.slices; i8++) {
                for (int i9 = 0; i9 < this.rows; i9++) {
                    this.fftColumns.realInverseFull(dArr[i8][i9], z2);
                }
            }
            int i10 = 0;
            while (true) {
                i2 = this.slices;
                if (i10 >= i2) {
                    break;
                }
                for (int i11 = 0; i11 < this.columns; i11++) {
                    int i12 = i11 * 2;
                    for (int i13 = 0; i13 < this.rows; i13++) {
                        int i14 = i13 * 2;
                        double[] dArr3 = dArr[i10][i13];
                        dArr2[i14] = dArr3[i12];
                        dArr2[i14 + 1] = dArr3[i12 + 1];
                    }
                    this.fftRows.complexInverse(dArr2, z2);
                    for (int i15 = 0; i15 < this.rows; i15++) {
                        int i16 = i15 * 2;
                        double[] dArr4 = dArr[i10][i15];
                        dArr4[i12] = dArr2[i16];
                        dArr4[i12 + 1] = dArr2[i16 + 1];
                    }
                }
                i10++;
            }
            double[] dArr5 = new double[(i2 * 2)];
            for (int i17 = 0; i17 < i5; i17++) {
                for (int i18 = 0; i18 < this.columns; i18++) {
                    int i19 = i18 * 2;
                    for (int i20 = 0; i20 < this.slices; i20++) {
                        int i21 = i20 * 2;
                        double[] dArr6 = dArr[i20][i17];
                        dArr5[i21] = dArr6[i19];
                        dArr5[i21 + 1] = dArr6[i19 + 1];
                    }
                    this.fftSlices.complexInverse(dArr5, z2);
                    for (int i22 = 0; i22 < this.slices; i22++) {
                        int i23 = i22 * 2;
                        double[] dArr7 = dArr[i22][i17];
                        dArr7[i19] = dArr5[i23];
                        dArr7[i19 + 1] = dArr5[i23 + 1];
                    }
                }
            }
            int i24 = 0;
            while (true) {
                int i25 = this.slices;
                if (i24 < i25) {
                    int i26 = (i25 - i24) % i25;
                    for (int i27 = 1; i27 < i7; i27++) {
                        int i28 = this.rows - i27;
                        for (int i29 = 0; i29 < this.columns; i29++) {
                            int i30 = i29 * 2;
                            int i31 = i6 - i30;
                            double[] dArr8 = dArr[i26][i28];
                            double[] dArr9 = dArr[i24][i27];
                            dArr8[i31 % i6] = dArr9[i30];
                            dArr8[(i31 + 1) % i6] = -dArr9[i30 + 1];
                        }
                    }
                    i24++;
                } else {
                    return;
                }
            }
        } else {
            Future[] futureArr = new Future[numberOfThreads];
            int i32 = i3 / numberOfThreads;
            int i33 = 0;
            while (i33 < numberOfThreads) {
                final int i34 = i33 * i32;
                final int i35 = i33 == numberOfThreads + -1 ? this.slices : i34 + i32;
                final double[][][] dArr10 = dArr;
                final boolean z3 = z;
                futureArr[i33] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i34; i < i35; i++) {
                            for (int i2 = 0; i2 < DoubleFFT_3D.this.rows; i2++) {
                                DoubleFFT_3D.this.fftColumns.realInverseFull(dArr10[i][i2], z3);
                            }
                        }
                    }
                });
                i33++;
            }
            String str = null;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
            } catch (ExecutionException e2) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
            }
            int i36 = 0;
            while (i36 < numberOfThreads) {
                final int i37 = i36 * i32;
                final int i38 = i36 == numberOfThreads + -1 ? this.slices : i37 + i32;
                final double[][][] dArr11 = dArr;
                String str2 = str;
                final boolean z4 = z;
                futureArr[i36] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        double[] dArr = new double[(DoubleFFT_3D.this.rows * 2)];
                        for (int i = i37; i < i38; i++) {
                            for (int i2 = 0; i2 < DoubleFFT_3D.this.columns; i2++) {
                                int i3 = i2 * 2;
                                for (int i4 = 0; i4 < DoubleFFT_3D.this.rows; i4++) {
                                    int i5 = i4 * 2;
                                    double[] dArr2 = dArr11[i][i4];
                                    dArr[i5] = dArr2[i3];
                                    dArr[i5 + 1] = dArr2[i3 + 1];
                                }
                                DoubleFFT_3D.this.fftRows.complexInverse(dArr, z4);
                                for (int i6 = 0; i6 < DoubleFFT_3D.this.rows; i6++) {
                                    int i7 = i6 * 2;
                                    double[] dArr3 = dArr11[i][i6];
                                    dArr3[i3] = dArr[i7];
                                    dArr3[i3 + 1] = dArr[i7 + 1];
                                }
                            }
                        }
                    }
                });
                i36++;
                str = str2;
            }
            String str3 = str;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e3) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e4);
            }
            int i39 = i5 / numberOfThreads;
            int i40 = 0;
            while (i40 < numberOfThreads) {
                final int i41 = i40 * i39;
                final int i42 = i40 == numberOfThreads + -1 ? i5 : i41 + i39;
                final double[][][] dArr12 = dArr;
                int i43 = i40;
                final boolean z5 = z;
                futureArr[i43] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        double[] dArr = new double[(DoubleFFT_3D.this.slices * 2)];
                        for (int i = i41; i < i42; i++) {
                            for (int i2 = 0; i2 < DoubleFFT_3D.this.columns; i2++) {
                                int i3 = i2 * 2;
                                for (int i4 = 0; i4 < DoubleFFT_3D.this.slices; i4++) {
                                    int i5 = i4 * 2;
                                    double[] dArr2 = dArr12[i4][i];
                                    dArr[i5] = dArr2[i3];
                                    dArr[i5 + 1] = dArr2[i3 + 1];
                                }
                                DoubleFFT_3D.this.fftSlices.complexInverse(dArr, z5);
                                for (int i6 = 0; i6 < DoubleFFT_3D.this.slices; i6++) {
                                    int i7 = i6 * 2;
                                    double[] dArr3 = dArr12[i6][i];
                                    dArr3[i3] = dArr[i7];
                                    dArr3[i3 + 1] = dArr[i7 + 1];
                                }
                            }
                        }
                    }
                });
                i40 = i43 + 1;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e5) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e5);
            } catch (ExecutionException e6) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e6);
            }
            int i44 = this.slices / numberOfThreads;
            int i45 = 0;
            while (i45 < numberOfThreads) {
                final int i46 = i45 * i44;
                final int i47 = i45 == numberOfThreads + -1 ? this.slices : i46 + i44;
                final int i48 = i7;
                final int i49 = i6;
                final double[][][] dArr13 = dArr;
                futureArr[i45] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i46; i < i47; i++) {
                            int access$600 = (DoubleFFT_3D.this.slices - i) % DoubleFFT_3D.this.slices;
                            for (int i2 = 1; i2 < i48; i2++) {
                                int access$100 = DoubleFFT_3D.this.rows - i2;
                                for (int i3 = 0; i3 < DoubleFFT_3D.this.columns; i3++) {
                                    int i4 = i3 * 2;
                                    int i5 = i49;
                                    int i6 = i5 - i4;
                                    double[][][] dArr = dArr13;
                                    double[] dArr2 = dArr[access$600][access$100];
                                    double[] dArr3 = dArr[i][i2];
                                    dArr2[i6 % i5] = dArr3[i4];
                                    dArr2[(i6 + 1) % i5] = -dArr3[i4 + 1];
                                }
                            }
                        }
                    }
                });
                i45++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e7) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e7);
            } catch (ExecutionException e8) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e8);
            }
        }
    }

    private void mixedRadixRealForwardFull(double[] dArr) {
        int i;
        int i2;
        double[] dArr2 = dArr;
        Class<DoubleFFT_3D> cls = DoubleFFT_3D.class;
        int i3 = this.columns * 2;
        double[] dArr3 = new double[i3];
        int i4 = this.rows;
        int i5 = (i4 / 2) + 1;
        if (i4 % 2 == 0) {
            i = i4 / 2;
        } else {
            i = (i4 + 1) / 2;
        }
        int i6 = i;
        int i7 = this.sliceStride * 2;
        int i8 = this.rowStride * 2;
        int i9 = this.slices / 2;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (numberOfThreads <= 1 || !this.useThreads || i9 < numberOfThreads || this.columns < numberOfThreads || i5 < numberOfThreads) {
            int i10 = 0;
            int i11 = i6;
            for (int i12 = this.slices - 1; i12 >= 0; i12--) {
                int i13 = this.sliceStride * i12;
                int i14 = i12 * i7;
                for (int i15 = this.rows - 1; i15 >= 0; i15--) {
                    System.arraycopy(dArr2, (this.rowStride * i15) + i13, dArr3, 0, this.columns);
                    this.fftColumns.realForwardFull(dArr3);
                    System.arraycopy(dArr3, 0, dArr2, (i15 * i8) + i14, i3);
                }
            }
            double[] dArr4 = new double[(this.rows * 2)];
            int i16 = 0;
            while (true) {
                i2 = this.slices;
                if (i16 >= i2) {
                    break;
                }
                int i17 = i16 * i7;
                for (int i18 = 0; i18 < this.columns; i18++) {
                    int i19 = i18 * 2;
                    for (int i20 = 0; i20 < this.rows; i20++) {
                        int i21 = i20 * 2;
                        int i22 = (i20 * i8) + i17 + i19;
                        dArr4[i21] = dArr2[i22];
                        dArr4[i21 + 1] = dArr2[i22 + 1];
                    }
                    this.fftRows.complexForward(dArr4);
                    for (int i23 = 0; i23 < this.rows; i23++) {
                        int i24 = i23 * 2;
                        int i25 = (i23 * i8) + i17 + i19;
                        dArr2[i25] = dArr4[i24];
                        dArr2[i25 + 1] = dArr4[i24 + 1];
                    }
                }
                i16++;
            }
            double[] dArr5 = new double[(i2 * 2)];
            for (int i26 = 0; i26 < i5; i26++) {
                int i27 = i26 * i8;
                for (int i28 = 0; i28 < this.columns; i28++) {
                    int i29 = i28 * 2;
                    for (int i30 = 0; i30 < this.slices; i30++) {
                        int i31 = i30 * 2;
                        int i32 = (i30 * i7) + i27 + i29;
                        dArr5[i31] = dArr2[i32];
                        dArr5[i31 + 1] = dArr2[i32 + 1];
                    }
                    this.fftSlices.complexForward(dArr5);
                    for (int i33 = 0; i33 < this.slices; i33++) {
                        int i34 = i33 * 2;
                        int i35 = (i33 * i7) + i27 + i29;
                        dArr2[i35] = dArr5[i34];
                        dArr2[i35 + 1] = dArr5[i34 + 1];
                    }
                }
            }
            int i36 = 0;
            while (true) {
                int i37 = this.slices;
                if (i36 < i37) {
                    int i38 = ((i37 - i36) % i37) * i7;
                    int i39 = i36 * i7;
                    int i40 = 1;
                    int i41 = i11;
                    while (i40 < i41) {
                        int i42 = i40 * i8;
                        int i43 = ((this.rows - i40) * i8) + i38;
                        int i44 = i10;
                        while (i44 < this.columns) {
                            int i45 = i44 * 2;
                            int i46 = i3 - i45;
                            int i47 = i39 + i42 + i45;
                            dArr2[(i46 % i3) + i43] = dArr2[i47];
                            dArr2[((i46 + 1) % i3) + i43] = -dArr2[i47 + 1];
                            i44++;
                            i38 = i38;
                        }
                        int i48 = i38;
                        i40++;
                        i10 = 0;
                    }
                    i36++;
                    i11 = i41;
                    i10 = 0;
                } else {
                    return;
                }
            }
        } else {
            Future[] futureArr = new Future[numberOfThreads];
            int i49 = i9 / numberOfThreads;
            int i50 = 0;
            while (i50 < numberOfThreads) {
                int i51 = (this.slices - 1) - (i50 * i49);
                final int i52 = i3;
                Future[] futureArr2 = futureArr;
                final int i53 = i51;
                final int i54 = i50 == numberOfThreads + -1 ? i9 + 1 : i51 - i49;
                int i55 = numberOfThreads;
                final int i56 = i7;
                int i57 = i9;
                final double[] dArr6 = dArr;
                int i58 = i6;
                final int i59 = i8;
                futureArr2[i50] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        double[] dArr = new double[i52];
                        for (int i = i53; i >= i54; i--) {
                            int access$000 = DoubleFFT_3D.this.sliceStride * i;
                            int i2 = i56 * i;
                            for (int access$100 = DoubleFFT_3D.this.rows - 1; access$100 >= 0; access$100--) {
                                System.arraycopy(dArr6, (DoubleFFT_3D.this.rowStride * access$100) + access$000, dArr, 0, DoubleFFT_3D.this.columns);
                                DoubleFFT_3D.this.fftColumns.realForwardFull(dArr);
                                System.arraycopy(dArr, 0, dArr6, (i59 * access$100) + i2, i52);
                            }
                        }
                    }
                });
                i50++;
                i9 = i57;
                i6 = i58;
                futureArr = futureArr2;
                numberOfThreads = i55;
            }
            Future[] futureArr3 = futureArr;
            int i60 = numberOfThreads;
            int i61 = i9;
            int i62 = i6;
            String str = null;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
            } catch (ExecutionException e2) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
            }
            int i63 = i61 + 1;
            int i64 = this.rows;
            int[] iArr = new int[3];
            iArr[2] = i3;
            iArr[1] = i64;
            iArr[0] = i63;
            double[][][] dArr7 = (double[][][]) Array.newInstance(Double.TYPE, iArr);
            int i65 = 0;
            int i66 = i60;
            while (i65 < i66) {
                final int i67 = i65 * i49;
                final int i68 = i65 == i66 + -1 ? i63 : i67 + i49;
                final double[] dArr8 = dArr;
                final double[][][] dArr9 = dArr7;
                futureArr3[i65] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i67; i < i68; i++) {
                            int access$000 = DoubleFFT_3D.this.sliceStride * i;
                            for (int i2 = 0; i2 < DoubleFFT_3D.this.rows; i2++) {
                                System.arraycopy(dArr8, (DoubleFFT_3D.this.rowStride * i2) + access$000, dArr9[i][i2], 0, DoubleFFT_3D.this.columns);
                                DoubleFFT_3D.this.fftColumns.realForwardFull(dArr9[i][i2]);
                            }
                        }
                    }
                });
                i65++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e3) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e4);
            }
            int i69 = 0;
            while (i69 < i66) {
                final int i70 = i69 * i49;
                final int i71 = i69 == i66 + -1 ? i63 : i70 + i49;
                final int i72 = i7;
                final double[][][] dArr10 = dArr7;
                final double[] dArr11 = dArr;
                String str2 = str;
                final int i73 = i8;
                int i74 = i66;
                final int i75 = i3;
                futureArr3[i69] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i70; i < i71; i++) {
                            int i2 = i72 * i;
                            for (int i3 = 0; i3 < DoubleFFT_3D.this.rows; i3++) {
                                System.arraycopy(dArr10[i][i3], 0, dArr11, (i73 * i3) + i2, i75);
                            }
                        }
                    }
                });
                i69++;
                str = str2;
                i66 = i74;
            }
            String str3 = str;
            int i76 = i66;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e5) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e5);
            } catch (ExecutionException e6) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e6);
            }
            int i77 = i76;
            int i78 = this.slices / i77;
            int i79 = 0;
            while (i79 < i77) {
                final int i80 = i79 * i78;
                final int i81 = i79 == i77 + -1 ? this.slices : i80 + i78;
                final int i82 = i7;
                final int i83 = i8;
                final double[] dArr12 = dArr;
                futureArr3[i79] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        double[] dArr = new double[(DoubleFFT_3D.this.rows * 2)];
                        for (int i = i80; i < i81; i++) {
                            int i2 = i82 * i;
                            for (int i3 = 0; i3 < DoubleFFT_3D.this.columns; i3++) {
                                int i4 = i3 * 2;
                                for (int i5 = 0; i5 < DoubleFFT_3D.this.rows; i5++) {
                                    int i6 = (i83 * i5) + i2 + i4;
                                    int i7 = i5 * 2;
                                    double[] dArr2 = dArr12;
                                    dArr[i7] = dArr2[i6];
                                    dArr[i7 + 1] = dArr2[i6 + 1];
                                }
                                DoubleFFT_3D.this.fftRows.complexForward(dArr);
                                for (int i8 = 0; i8 < DoubleFFT_3D.this.rows; i8++) {
                                    int i9 = (i83 * i8) + i2 + i4;
                                    int i10 = i8 * 2;
                                    double[] dArr3 = dArr12;
                                    dArr3[i9] = dArr[i10];
                                    dArr3[i9 + 1] = dArr[i10 + 1];
                                }
                            }
                        }
                    }
                });
                i79++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e7) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e7);
            } catch (ExecutionException e8) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e8);
            }
            int i84 = i5 / i77;
            int i85 = 0;
            while (i85 < i77) {
                final int i86 = i85 * i84;
                final int i87 = i85 == i77 + -1 ? i5 : i86 + i84;
                final int i88 = i8;
                final int i89 = i7;
                final double[] dArr13 = dArr;
                futureArr3[i85] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        double[] dArr = new double[(DoubleFFT_3D.this.slices * 2)];
                        for (int i = i86; i < i87; i++) {
                            int i2 = i88 * i;
                            for (int i3 = 0; i3 < DoubleFFT_3D.this.columns; i3++) {
                                int i4 = i3 * 2;
                                for (int i5 = 0; i5 < DoubleFFT_3D.this.slices; i5++) {
                                    int i6 = i5 * 2;
                                    int i7 = (i89 * i5) + i2 + i4;
                                    double[] dArr2 = dArr13;
                                    dArr[i6] = dArr2[i7];
                                    dArr[i6 + 1] = dArr2[i7 + 1];
                                }
                                DoubleFFT_3D.this.fftSlices.complexForward(dArr);
                                for (int i8 = 0; i8 < DoubleFFT_3D.this.slices; i8++) {
                                    int i9 = i8 * 2;
                                    int i10 = (i89 * i8) + i2 + i4;
                                    double[] dArr3 = dArr13;
                                    dArr3[i10] = dArr[i9];
                                    dArr3[i10 + 1] = dArr[i9 + 1];
                                }
                            }
                        }
                    }
                });
                i85++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e9) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e9);
            } catch (ExecutionException e10) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e10);
            }
            int i90 = this.slices / i77;
            int i91 = 0;
            while (i91 < i77) {
                final int i92 = i91 * i90;
                final int i93 = i91 == i77 + -1 ? this.slices : i92 + i90;
                final int i94 = i7;
                final int i95 = i62;
                final int i96 = i8;
                final int i97 = i3;
                int i98 = i77;
                final double[] dArr14 = dArr;
                futureArr3[i91] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i92; i < i93; i++) {
                            int access$600 = (DoubleFFT_3D.this.slices - i) % DoubleFFT_3D.this.slices;
                            int i2 = i94;
                            int i3 = access$600 * i2;
                            int i4 = i2 * i;
                            for (int i5 = 1; i5 < i95; i5++) {
                                int i6 = i96;
                                int access$100 = (DoubleFFT_3D.this.rows - i5) * i6;
                                int i7 = i6 * i5;
                                int i8 = access$100 + i3;
                                for (int i9 = 0; i9 < DoubleFFT_3D.this.columns; i9++) {
                                    int i10 = i9 * 2;
                                    int i11 = i97;
                                    int i12 = i11 - i10;
                                    int i13 = i4 + i7 + i10;
                                    double[] dArr = dArr14;
                                    dArr[(i12 % i11) + i8] = dArr[i13];
                                    dArr[((i12 + 1) % i11) + i8] = -dArr[i13 + 1];
                                }
                            }
                        }
                    }
                });
                i91++;
                i77 = i98;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e11) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e11);
            } catch (ExecutionException e12) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e12);
            }
        }
    }

    private void mixedRadixRealForwardFull(DoubleLargeArray doubleLargeArray) {
        long j;
        DoubleLargeArray doubleLargeArray2 = doubleLargeArray;
        Class<DoubleFFT_3D> cls = DoubleFFT_3D.class;
        long j2 = 2;
        long j3 = this.columnsl * 2;
        DoubleLargeArray doubleLargeArray3 = new DoubleLargeArray(j3);
        long j4 = this.rowsl;
        long j5 = (j4 / 2) + 1;
        if (j4 % 2 == 0) {
            j = j4 / 2;
        } else {
            j = (j4 + 1) / 2;
        }
        long j6 = j;
        long j7 = this.sliceStridel * 2;
        long j8 = this.rowStridel * 2;
        long j9 = this.slicesl / 2;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        boolean z = false;
        if (numberOfThreads > 1 && this.useThreads) {
            long j10 = (long) numberOfThreads;
            if (j9 >= j10 && this.columnsl >= j10 && j5 >= j10) {
                Future[] futureArr = new Future[numberOfThreads];
                long j11 = j9 / j10;
                int i = 0;
                while (i < numberOfThreads) {
                    long j12 = (this.slicesl - 1) - (((long) i) * j11);
                    final long j13 = j3;
                    long j14 = j10;
                    final long j15 = j12;
                    boolean z2 = z;
                    final long j16 = i == numberOfThreads + -1 ? j9 + 1 : j12 - j11;
                    Future[] futureArr2 = futureArr;
                    final long j17 = j7;
                    int i2 = numberOfThreads;
                    final DoubleLargeArray doubleLargeArray4 = doubleLargeArray;
                    long j18 = j3;
                    final long j19 = j8;
                    futureArr2[i] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            DoubleLargeArray doubleLargeArray = new DoubleLargeArray(j13);
                            for (long j = j15; j >= j16; j--) {
                                long access$800 = j * DoubleFFT_3D.this.sliceStridel;
                                long j2 = j * j17;
                                for (long access$900 = DoubleFFT_3D.this.rowsl - 1; access$900 >= 0; access$900--) {
                                    LargeArrayUtils.arraycopy(doubleLargeArray4, (DoubleFFT_3D.this.rowStridel * access$900) + access$800, doubleLargeArray, 0, DoubleFFT_3D.this.columnsl);
                                    DoubleFFT_3D.this.fftColumns.realForwardFull(doubleLargeArray);
                                    LargeArrayUtils.arraycopy(doubleLargeArray, 0, doubleLargeArray4, j2 + (j19 * access$900), j13);
                                }
                            }
                        }
                    });
                    i++;
                    numberOfThreads = i2;
                    futureArr = futureArr2;
                    j3 = j18;
                    j10 = j14;
                    z = false;
                    DoubleLargeArray doubleLargeArray5 = doubleLargeArray;
                }
                long j20 = j10;
                Future[] futureArr3 = futureArr;
                int i3 = numberOfThreads;
                long j21 = j3;
                String str = null;
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException e) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
                } catch (ExecutionException e2) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
                }
                long j22 = j9 + 1;
                DoubleLargeArray doubleLargeArray6 = new DoubleLargeArray(this.rowsl * j22 * j21);
                int i4 = 0;
                while (i4 < i3) {
                    final long j23 = ((long) i4) * j11;
                    final long j24 = i4 == i3 + -1 ? j22 : j23 + j11;
                    final DoubleLargeArray doubleLargeArray7 = doubleLargeArray;
                    final DoubleLargeArray doubleLargeArray8 = doubleLargeArray6;
                    final long j25 = j21;
                    futureArr3[i4] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            for (long j = j23; j < j24; j++) {
                                long access$800 = DoubleFFT_3D.this.sliceStridel * j;
                                for (long j2 = 0; j2 < DoubleFFT_3D.this.rowsl; j2++) {
                                    DoubleLargeArray doubleLargeArray = doubleLargeArray8;
                                    long j3 = j25;
                                    LargeArrayUtils.arraycopy(doubleLargeArray7, (DoubleFFT_3D.this.rowStridel * j2) + access$800, doubleLargeArray, (DoubleFFT_3D.this.rowsl * j * j3) + (j3 * j2), DoubleFFT_3D.this.columnsl);
                                    DoubleFFT_1D access$300 = DoubleFFT_3D.this.fftColumns;
                                    DoubleLargeArray doubleLargeArray2 = doubleLargeArray8;
                                    long j4 = j25;
                                    access$300.realForwardFull(doubleLargeArray2, (DoubleFFT_3D.this.rowsl * j * j4) + (j4 * j2));
                                }
                            }
                        }
                    });
                    i4++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException e3) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e3);
                } catch (ExecutionException e4) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e4);
                }
                int i5 = 0;
                while (i5 < i3) {
                    final long j26 = ((long) i5) * j11;
                    final long j27 = i5 == i3 + -1 ? j22 : j26 + j11;
                    final long j28 = j7;
                    final DoubleLargeArray doubleLargeArray9 = doubleLargeArray6;
                    final long j29 = j21;
                    DoubleLargeArray doubleLargeArray10 = doubleLargeArray6;
                    final DoubleLargeArray doubleLargeArray11 = doubleLargeArray;
                    String str2 = str;
                    final long j30 = j8;
                    futureArr3[i5] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            for (long j = j26; j < j27; j++) {
                                long j2 = j28 * j;
                                for (long j3 = 0; j3 < DoubleFFT_3D.this.rowsl; j3++) {
                                    DoubleLargeArray doubleLargeArray = doubleLargeArray9;
                                    long j4 = j29;
                                    LargeArrayUtils.arraycopy(doubleLargeArray, (DoubleFFT_3D.this.rowsl * j * j4) + (j3 * j4), doubleLargeArray11, j2 + (j30 * j3), j4);
                                }
                            }
                        }
                    });
                    i5++;
                    str = str2;
                    doubleLargeArray6 = doubleLargeArray10;
                    i3 = i3;
                }
                String str3 = str;
                int i6 = i3;
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException e5) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e5);
                } catch (ExecutionException e6) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e6);
                }
                String str4 = str3;
                DoubleFFT_3D doubleFFT_3D = this;
                long j31 = doubleFFT_3D.slicesl / j20;
                int i7 = i6;
                int i8 = 0;
                while (i8 < i7) {
                    final long j32 = ((long) i8) * j31;
                    final long j33 = i8 == i7 + -1 ? doubleFFT_3D.slicesl : j32 + j31;
                    final long j34 = j7;
                    final long j35 = j8;
                    final DoubleLargeArray doubleLargeArray12 = doubleLargeArray;
                    futureArr3[i8] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            long j = 2;
                            DoubleLargeArray doubleLargeArray = new DoubleLargeArray(DoubleFFT_3D.this.rowsl * 2, false);
                            long j2 = j32;
                            while (j2 < j33) {
                                long j3 = j34 * j2;
                                long j4 = 0;
                                while (j4 < DoubleFFT_3D.this.columnsl) {
                                    long j5 = j4 * j;
                                    long j6 = 0;
                                    while (j6 < DoubleFFT_3D.this.rowsl) {
                                        long j7 = (j35 * j6) + j3 + j5;
                                        long j8 = j6 * j;
                                        doubleLargeArray.setDouble(j8, doubleLargeArray12.getDouble(j7));
                                        doubleLargeArray.setDouble(j8 + 1, doubleLargeArray12.getDouble(j7 + 1));
                                        j6++;
                                        j = 2;
                                    }
                                    DoubleFFT_3D.this.fftRows.complexForward(doubleLargeArray);
                                    long j9 = 0;
                                    while (j9 < DoubleFFT_3D.this.rowsl) {
                                        long j10 = (j35 * j9) + j3 + j5;
                                        long j11 = j3;
                                        long j12 = j9 * 2;
                                        doubleLargeArray12.setDouble(j10, doubleLargeArray.getDouble(j12));
                                        doubleLargeArray12.setDouble(j10 + 1, doubleLargeArray.getDouble(j12 + 1));
                                        j9++;
                                        j3 = j11;
                                    }
                                    long j13 = j3;
                                    j4++;
                                    j = 2;
                                }
                                j2++;
                                j = 2;
                            }
                        }
                    });
                    i8++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException e7) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, str4, e7);
                } catch (ExecutionException e8) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, str4, e8);
                }
                long j36 = j5 / j20;
                int i9 = 0;
                while (i9 < i7) {
                    final long j37 = ((long) i9) * j36;
                    final long j38 = i9 == i7 + -1 ? j5 : j37 + j36;
                    final long j39 = j8;
                    final long j40 = j7;
                    final DoubleLargeArray doubleLargeArray13 = doubleLargeArray;
                    futureArr3[i9] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            long j = 2;
                            DoubleLargeArray doubleLargeArray = new DoubleLargeArray(DoubleFFT_3D.this.slicesl * 2, false);
                            long j2 = j37;
                            while (j2 < j38) {
                                long j3 = j39 * j2;
                                long j4 = 0;
                                while (true) {
                                    long j5 = 1;
                                    if (j4 >= DoubleFFT_3D.this.columnsl) {
                                        break;
                                    }
                                    long j6 = j4 * j;
                                    long j7 = 0;
                                    while (j7 < DoubleFFT_3D.this.slicesl) {
                                        long j8 = j7 * j;
                                        long j9 = (j40 * j7) + j3 + j6;
                                        doubleLargeArray.setDouble(j8, doubleLargeArray13.getDouble(j9));
                                        doubleLargeArray.setDouble(j8 + 1, doubleLargeArray13.getDouble(j9 + 1));
                                        j7++;
                                        j2 = j2;
                                        j = 2;
                                    }
                                    long j10 = j2;
                                    DoubleFFT_3D.this.fftSlices.complexForward(doubleLargeArray);
                                    for (long j11 = 0; j11 < DoubleFFT_3D.this.slicesl; j11++) {
                                        long j12 = j11 * 2;
                                        long j13 = (j40 * j11) + j3 + j6;
                                        doubleLargeArray13.setDouble(j13, doubleLargeArray.getDouble(j12));
                                        j5 = 1;
                                        doubleLargeArray13.setDouble(j13 + 1, doubleLargeArray.getDouble(j12 + 1));
                                    }
                                    j4 += j5;
                                    j2 = j10;
                                    j = 2;
                                }
                                j2++;
                                j = 2;
                            }
                        }
                    });
                    i9++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException e9) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, str4, e9);
                } catch (ExecutionException e10) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, str4, e10);
                }
                long j41 = doubleFFT_3D.slicesl / j20;
                int i10 = 0;
                while (i10 < i7) {
                    final long j42 = ((long) i10) * j41;
                    final long j43 = i10 == i7 + -1 ? doubleFFT_3D.slicesl : j42 + j41;
                    final long j44 = j7;
                    final long j45 = j6;
                    final long j46 = j8;
                    final long j47 = j21;
                    final DoubleLargeArray doubleLargeArray14 = doubleLargeArray;
                    futureArr3[i10] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            for (long j = j42; j < j43; j++) {
                                long access$1200 = (DoubleFFT_3D.this.slicesl - j) % DoubleFFT_3D.this.slicesl;
                                long j2 = j44;
                                long j3 = access$1200 * j2;
                                long j4 = j2 * j;
                                long j5 = 1;
                                while (j5 < j45) {
                                    long j6 = j46;
                                    long access$900 = (DoubleFFT_3D.this.rowsl - j5) * j6;
                                    long j7 = j6 * j5;
                                    long j8 = access$900 + j3;
                                    long j9 = 0;
                                    while (j9 < DoubleFFT_3D.this.columnsl) {
                                        long j10 = 2 * j9;
                                        long j11 = j3;
                                        long j12 = j47;
                                        long j13 = j12 - j10;
                                        long j14 = j4 + j7 + j10;
                                        long j15 = j4;
                                        DoubleLargeArray doubleLargeArray = doubleLargeArray14;
                                        doubleLargeArray.setDouble((j13 % j12) + j8, doubleLargeArray.getDouble(j14));
                                        DoubleLargeArray doubleLargeArray2 = doubleLargeArray14;
                                        doubleLargeArray2.setDouble(j8 + ((j13 + 1) % j47), -doubleLargeArray2.getDouble(j14 + 1));
                                        j9++;
                                        j3 = j11;
                                        j4 = j15;
                                        j7 = j7;
                                    }
                                    j5++;
                                    j3 = j3;
                                    j4 = j4;
                                }
                            }
                        }
                    });
                    i10++;
                    doubleFFT_3D = this;
                    i7 = i7;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                    return;
                } catch (InterruptedException e11) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e11);
                    return;
                } catch (ExecutionException e12) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e12);
                    return;
                }
            }
        }
        long j48 = j3;
        DoubleFFT_3D doubleFFT_3D2 = this;
        for (long j49 = doubleFFT_3D2.slicesl - 1; j49 >= 0; j49--) {
            long j50 = j49 * doubleFFT_3D2.sliceStridel;
            long j51 = j49 * j7;
            for (long j52 = doubleFFT_3D2.rowsl - 1; j52 >= 0; j52--) {
                LargeArrayUtils.arraycopy(doubleLargeArray, j50 + (doubleFFT_3D2.rowStridel * j52), doubleLargeArray3, 0, doubleFFT_3D2.columnsl);
                doubleFFT_3D2.fftColumns.realForwardFull(doubleLargeArray3);
                LargeArrayUtils.arraycopy(doubleLargeArray3, 0, doubleLargeArray, j51 + (j52 * j8), j48);
            }
        }
        DoubleLargeArray doubleLargeArray15 = new DoubleLargeArray(doubleFFT_3D2.rowsl * 2, false);
        long j53 = 0;
        while (j53 < doubleFFT_3D2.slicesl) {
            long j54 = j53 * j7;
            long j55 = 0;
            while (j55 < doubleFFT_3D2.columnsl) {
                long j56 = j55 * j2;
                long j57 = j54;
                long j58 = 0;
                while (j58 < doubleFFT_3D2.rowsl) {
                    long j59 = j58 * j2;
                    long j60 = j57 + (j58 * j8) + j56;
                    DoubleLargeArray doubleLargeArray16 = doubleLargeArray;
                    doubleLargeArray15.setDouble(j59, doubleLargeArray16.getDouble(j60));
                    doubleLargeArray15.setDouble(j59 + 1, doubleLargeArray16.getDouble(j60 + 1));
                    j58++;
                    j53 = j53;
                    j2 = 2;
                }
                DoubleLargeArray doubleLargeArray17 = doubleLargeArray;
                long j61 = j53;
                doubleFFT_3D2.fftRows.complexForward(doubleLargeArray15);
                for (long j62 = 0; j62 < doubleFFT_3D2.rowsl; j62++) {
                    long j63 = j62 * 2;
                    long j64 = j57 + (j62 * j8) + j56;
                    doubleLargeArray17.setDouble(j64, doubleLargeArray15.getDouble(j63));
                    doubleLargeArray17.setDouble(j64 + 1, doubleLargeArray15.getDouble(j63 + 1));
                }
                j55++;
                j54 = j57;
                j53 = j61;
                j2 = 2;
            }
            DoubleLargeArray doubleLargeArray18 = doubleLargeArray;
            j53++;
            j2 = 2;
        }
        DoubleLargeArray doubleLargeArray19 = doubleLargeArray;
        long j65 = 2;
        DoubleLargeArray doubleLargeArray20 = new DoubleLargeArray(doubleFFT_3D2.slicesl * 2, false);
        long j66 = 0;
        while (j66 < j5) {
            long j67 = j66 * j8;
            long j68 = 0;
            while (j68 < doubleFFT_3D2.columnsl) {
                long j69 = j68 * j65;
                long j70 = 0;
                while (j70 < doubleFFT_3D2.slicesl) {
                    long j71 = j66;
                    long j72 = j70 * 2;
                    long j73 = (j70 * j7) + j67 + j69;
                    doubleLargeArray20.setDouble(j72, doubleLargeArray19.getDouble(j73));
                    doubleLargeArray20.setDouble(j72 + 1, doubleLargeArray19.getDouble(j73 + 1));
                    j70++;
                    j66 = j71;
                    j68 = j68;
                }
                long j74 = j66;
                long j75 = j68;
                doubleFFT_3D2.fftSlices.complexForward(doubleLargeArray20);
                for (long j76 = 0; j76 < doubleFFT_3D2.slicesl; j76++) {
                    long j77 = j76 * 2;
                    long j78 = (j76 * j7) + j67 + j69;
                    doubleLargeArray19.setDouble(j78, doubleLargeArray20.getDouble(j77));
                    doubleLargeArray19.setDouble(j78 + 1, doubleLargeArray20.getDouble(j77 + 1));
                }
                j68 = j75 + 1;
                j66 = j74;
                j65 = 2;
            }
            j66++;
            j65 = 2;
        }
        long j79 = 0;
        while (true) {
            long j80 = doubleFFT_3D2.slicesl;
            if (j79 < j80) {
                long j81 = ((j80 - j79) % j80) * j7;
                long j82 = j79 * j7;
                long j83 = 1;
                while (j83 < j6) {
                    long j84 = j83 * j8;
                    long j85 = ((doubleFFT_3D2.rowsl - j83) * j8) + j81;
                    long j86 = j81;
                    long j87 = 0;
                    while (j87 < doubleFFT_3D2.columnsl) {
                        long j88 = j87 * 2;
                        long j89 = j48 - j88;
                        long j90 = j82 + j84 + j88;
                        doubleLargeArray19.setDouble(j85 + (j89 % j48), doubleLargeArray19.getDouble(j90));
                        doubleLargeArray19.setDouble(j85 + ((j89 + 1) % j48), -doubleLargeArray19.getDouble(j90 + 1));
                        j87++;
                        doubleFFT_3D2 = this;
                        j82 = j82;
                    }
                    long j91 = j82;
                    j83++;
                    doubleFFT_3D2 = this;
                    j81 = j86;
                }
                j79++;
                doubleFFT_3D2 = this;
            } else {
                return;
            }
        }
    }

    private void mixedRadixRealInverseFull(double[] dArr, boolean z) {
        int i;
        int i2;
        double[] dArr2 = dArr;
        boolean z2 = z;
        Class<DoubleFFT_3D> cls = DoubleFFT_3D.class;
        int i3 = this.columns * 2;
        double[] dArr3 = new double[i3];
        int i4 = this.rows;
        int i5 = 1;
        int i6 = (i4 / 2) + 1;
        if (i4 % 2 == 0) {
            i = i4 / 2;
        } else {
            i = (i4 + 1) / 2;
        }
        int i7 = i;
        int i8 = this.sliceStride * 2;
        int i9 = this.rowStride * 2;
        int i10 = this.slices / 2;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (numberOfThreads <= 1 || !this.useThreads || i10 < numberOfThreads || this.columns < numberOfThreads || i6 < numberOfThreads) {
            int i11 = i7;
            int i12 = i6;
            for (int i13 = this.slices - 1; i13 >= 0; i13--) {
                int i14 = this.sliceStride * i13;
                int i15 = i13 * i8;
                for (int i16 = this.rows - 1; i16 >= 0; i16--) {
                    System.arraycopy(dArr2, (this.rowStride * i16) + i14, dArr3, 0, this.columns);
                    this.fftColumns.realInverseFull(dArr3, z);
                    System.arraycopy(dArr3, 0, dArr2, (i16 * i9) + i15, i3);
                }
                boolean z3 = z;
            }
            boolean z4 = z;
            double[] dArr4 = new double[(this.rows * 2)];
            int i17 = 0;
            while (true) {
                i2 = this.slices;
                if (i17 >= i2) {
                    break;
                }
                int i18 = i17 * i8;
                for (int i19 = 0; i19 < this.columns; i19++) {
                    int i20 = i19 * 2;
                    for (int i21 = 0; i21 < this.rows; i21++) {
                        int i22 = i21 * 2;
                        int i23 = (i21 * i9) + i18 + i20;
                        dArr4[i22] = dArr2[i23];
                        dArr4[i22 + 1] = dArr2[i23 + 1];
                    }
                    this.fftRows.complexInverse(dArr4, z4);
                    for (int i24 = 0; i24 < this.rows; i24++) {
                        int i25 = i24 * 2;
                        int i26 = (i24 * i9) + i18 + i20;
                        dArr2[i26] = dArr4[i25];
                        dArr2[i26 + 1] = dArr4[i25 + 1];
                    }
                }
                i17++;
            }
            double[] dArr5 = new double[(i2 * 2)];
            for (int i27 = 0; i27 < i12; i27++) {
                int i28 = i27 * i9;
                for (int i29 = 0; i29 < this.columns; i29++) {
                    int i30 = i29 * 2;
                    for (int i31 = 0; i31 < this.slices; i31++) {
                        int i32 = i31 * 2;
                        int i33 = (i31 * i8) + i28 + i30;
                        dArr5[i32] = dArr2[i33];
                        dArr5[i32 + 1] = dArr2[i33 + 1];
                    }
                    this.fftSlices.complexInverse(dArr5, z4);
                    for (int i34 = 0; i34 < this.slices; i34++) {
                        int i35 = i34 * 2;
                        int i36 = (i34 * i8) + i28 + i30;
                        dArr2[i36] = dArr5[i35];
                        dArr2[i36 + 1] = dArr5[i35 + 1];
                    }
                }
            }
            int i37 = 0;
            while (true) {
                int i38 = this.slices;
                if (i37 < i38) {
                    int i39 = ((i38 - i37) % i38) * i8;
                    int i40 = i37 * i8;
                    int i41 = i11;
                    for (int i42 = 1; i42 < i41; i42++) {
                        int i43 = i42 * i9;
                        int i44 = ((this.rows - i42) * i9) + i39;
                        for (int i45 = 0; i45 < this.columns; i45++) {
                            int i46 = i45 * 2;
                            int i47 = i3 - i46;
                            int i48 = i40 + i43 + i46;
                            dArr2[(i47 % i3) + i44] = dArr2[i48];
                            dArr2[((i47 + 1) % i3) + i44] = -dArr2[i48 + 1];
                        }
                    }
                    i37++;
                    i11 = i41;
                } else {
                    return;
                }
            }
        } else {
            Future[] futureArr = new Future[numberOfThreads];
            int i49 = i10 / numberOfThreads;
            int i50 = 0;
            while (i50 < numberOfThreads) {
                int i51 = (this.slices - i5) - (i50 * i49);
                Future[] futureArr2 = futureArr;
                final int i52 = i3;
                final int i53 = i51;
                int i54 = numberOfThreads;
                final int i55 = i50 == numberOfThreads + -1 ? i10 + 1 : i51 - i49;
                int i56 = i10;
                final int i57 = i8;
                int i58 = i7;
                final double[] dArr6 = dArr;
                int i59 = i6;
                final boolean z5 = z;
                int i60 = i5;
                final int i61 = i9;
                futureArr2[i50] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        double[] dArr = new double[i52];
                        for (int i = i53; i >= i55; i--) {
                            int access$000 = DoubleFFT_3D.this.sliceStride * i;
                            int i2 = i57 * i;
                            for (int access$100 = DoubleFFT_3D.this.rows - 1; access$100 >= 0; access$100--) {
                                System.arraycopy(dArr6, (DoubleFFT_3D.this.rowStride * access$100) + access$000, dArr, 0, DoubleFFT_3D.this.columns);
                                DoubleFFT_3D.this.fftColumns.realInverseFull(dArr, z5);
                                System.arraycopy(dArr, 0, dArr6, (i61 * access$100) + i2, i52);
                            }
                        }
                    }
                });
                i50++;
                i10 = i56;
                i7 = i58;
                futureArr = futureArr2;
                numberOfThreads = i54;
                i6 = i59;
                i5 = i60;
            }
            Future[] futureArr3 = futureArr;
            int i62 = numberOfThreads;
            int i63 = i10;
            int i64 = i7;
            int i65 = i6;
            int i66 = i5;
            String str = null;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
            } catch (ExecutionException e2) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
            }
            int i67 = i63 + 1;
            int i68 = this.rows;
            int[] iArr = new int[3];
            iArr[2] = i3;
            iArr[i66] = i68;
            iArr[0] = i67;
            double[][][] dArr7 = (double[][][]) Array.newInstance(Double.TYPE, iArr);
            int i69 = 0;
            int i70 = i62;
            while (i69 < i70) {
                final int i71 = i69 * i49;
                final int i72 = i69 == i70 + -1 ? i67 : i71 + i49;
                final double[] dArr8 = dArr;
                final double[][][] dArr9 = dArr7;
                final boolean z6 = z;
                futureArr3[i69] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i71; i < i72; i++) {
                            int access$000 = DoubleFFT_3D.this.sliceStride * i;
                            for (int i2 = 0; i2 < DoubleFFT_3D.this.rows; i2++) {
                                System.arraycopy(dArr8, (DoubleFFT_3D.this.rowStride * i2) + access$000, dArr9[i][i2], 0, DoubleFFT_3D.this.columns);
                                DoubleFFT_3D.this.fftColumns.realInverseFull(dArr9[i][i2], z6);
                            }
                        }
                    }
                });
                i69++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e3) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e4);
            }
            int i73 = 0;
            while (i73 < i70) {
                final int i74 = i73 * i49;
                final int i75 = i73 == i70 + -1 ? i67 : i74 + i49;
                final int i76 = i8;
                final double[][][] dArr10 = dArr7;
                final double[] dArr11 = dArr;
                int i77 = i70;
                final int i78 = i9;
                String str2 = str;
                final int i79 = i3;
                futureArr3[i73] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i74; i < i75; i++) {
                            int i2 = i76 * i;
                            for (int i3 = 0; i3 < DoubleFFT_3D.this.rows; i3++) {
                                System.arraycopy(dArr10[i][i3], 0, dArr11, (i78 * i3) + i2, i79);
                            }
                        }
                    }
                });
                i73++;
                str = str2;
                i70 = i77;
                boolean z7 = z;
            }
            int i80 = i70;
            String str3 = str;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e5) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e5);
            } catch (ExecutionException e6) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e6);
            }
            int i81 = this.slices / i80;
            int i82 = 0;
            while (i82 < i80) {
                final int i83 = i82 * i81;
                final int i84 = i82 == i80 + -1 ? this.slices : i83 + i81;
                final int i85 = i8;
                final int i86 = i9;
                final double[] dArr12 = dArr;
                final boolean z8 = z;
                futureArr3[i82] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        double[] dArr = new double[(DoubleFFT_3D.this.rows * 2)];
                        for (int i = i83; i < i84; i++) {
                            int i2 = i85 * i;
                            for (int i3 = 0; i3 < DoubleFFT_3D.this.columns; i3++) {
                                int i4 = i3 * 2;
                                for (int i5 = 0; i5 < DoubleFFT_3D.this.rows; i5++) {
                                    int i6 = (i86 * i5) + i2 + i4;
                                    int i7 = i5 * 2;
                                    double[] dArr2 = dArr12;
                                    dArr[i7] = dArr2[i6];
                                    dArr[i7 + 1] = dArr2[i6 + 1];
                                }
                                DoubleFFT_3D.this.fftRows.complexInverse(dArr, z8);
                                for (int i8 = 0; i8 < DoubleFFT_3D.this.rows; i8++) {
                                    int i9 = (i86 * i8) + i2 + i4;
                                    int i10 = i8 * 2;
                                    double[] dArr3 = dArr12;
                                    dArr3[i9] = dArr[i10];
                                    dArr3[i9 + 1] = dArr[i10 + 1];
                                }
                            }
                        }
                    }
                });
                i82++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e7) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e7);
            } catch (ExecutionException e8) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e8);
            }
            int i87 = i65;
            int i88 = i87 / i80;
            int i89 = 0;
            while (i89 < i80) {
                final int i90 = i89 * i88;
                final int i91 = i89 == i80 + -1 ? i87 : i90 + i88;
                final int i92 = i9;
                final int i93 = i8;
                final double[] dArr13 = dArr;
                int i94 = i89;
                final boolean z9 = z;
                futureArr3[i94] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        double[] dArr = new double[(DoubleFFT_3D.this.slices * 2)];
                        for (int i = i90; i < i91; i++) {
                            int i2 = i92 * i;
                            for (int i3 = 0; i3 < DoubleFFT_3D.this.columns; i3++) {
                                int i4 = i3 * 2;
                                for (int i5 = 0; i5 < DoubleFFT_3D.this.slices; i5++) {
                                    int i6 = i5 * 2;
                                    int i7 = (i93 * i5) + i2 + i4;
                                    double[] dArr2 = dArr13;
                                    dArr[i6] = dArr2[i7];
                                    dArr[i6 + 1] = dArr2[i7 + 1];
                                }
                                DoubleFFT_3D.this.fftSlices.complexInverse(dArr, z9);
                                for (int i8 = 0; i8 < DoubleFFT_3D.this.slices; i8++) {
                                    int i9 = i8 * 2;
                                    int i10 = (i93 * i8) + i2 + i4;
                                    double[] dArr3 = dArr13;
                                    dArr3[i10] = dArr[i9];
                                    dArr3[i10 + 1] = dArr[i9 + 1];
                                }
                            }
                        }
                    }
                });
                i89 = i94 + 1;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e9) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e9);
            } catch (ExecutionException e10) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e10);
            }
            int i95 = this.slices / i80;
            int i96 = 0;
            while (i96 < i80) {
                final int i97 = i96 * i95;
                final int i98 = i96 == i80 + -1 ? this.slices : i97 + i95;
                final int i99 = i8;
                final int i100 = i64;
                final int i101 = i9;
                final int i102 = i3;
                int i103 = i96;
                final double[] dArr14 = dArr;
                futureArr3[i103] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i97; i < i98; i++) {
                            int access$600 = (DoubleFFT_3D.this.slices - i) % DoubleFFT_3D.this.slices;
                            int i2 = i99;
                            int i3 = access$600 * i2;
                            int i4 = i2 * i;
                            for (int i5 = 1; i5 < i100; i5++) {
                                int i6 = i101;
                                int access$100 = (DoubleFFT_3D.this.rows - i5) * i6;
                                int i7 = i6 * i5;
                                int i8 = access$100 + i3;
                                for (int i9 = 0; i9 < DoubleFFT_3D.this.columns; i9++) {
                                    int i10 = i9 * 2;
                                    int i11 = i102;
                                    int i12 = i11 - i10;
                                    int i13 = i4 + i7 + i10;
                                    double[] dArr = dArr14;
                                    dArr[(i12 % i11) + i8] = dArr[i13];
                                    dArr[((i12 + 1) % i11) + i8] = -dArr[i13 + 1];
                                }
                            }
                        }
                    }
                });
                i96 = i103 + 1;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e11) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e11);
            } catch (ExecutionException e12) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e12);
            }
        }
    }

    private void mixedRadixRealInverseFull(DoubleLargeArray doubleLargeArray, boolean z) {
        long j;
        DoubleLargeArray doubleLargeArray2 = doubleLargeArray;
        boolean z2 = z;
        Class<DoubleFFT_3D> cls = DoubleFFT_3D.class;
        long j2 = 2;
        long j3 = this.columnsl * 2;
        DoubleLargeArray doubleLargeArray3 = new DoubleLargeArray(j3);
        long j4 = this.rowsl;
        long j5 = (j4 / 2) + 1;
        if (j4 % 2 == 0) {
            j = j4 / 2;
        } else {
            j = (j4 + 1) / 2;
        }
        long j6 = j;
        long j7 = this.sliceStridel * 2;
        long j8 = this.rowStridel * 2;
        long j9 = this.slicesl / 2;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        boolean z3 = false;
        if (numberOfThreads > 1 && this.useThreads) {
            long j10 = (long) numberOfThreads;
            if (j9 >= j10 && this.columnsl >= j10 && j5 >= j10) {
                Future[] futureArr = new Future[numberOfThreads];
                long j11 = j9 / j10;
                int i = 0;
                while (i < numberOfThreads) {
                    long j12 = (this.slicesl - 1) - (((long) i) * j11);
                    final long j13 = j3;
                    long j14 = j10;
                    final long j15 = j12;
                    int i2 = numberOfThreads;
                    boolean z4 = z3;
                    final long j16 = i == numberOfThreads + -1 ? j9 + 1 : j12 - j11;
                    Future[] futureArr2 = futureArr;
                    final long j17 = j7;
                    long j18 = j3;
                    final DoubleLargeArray doubleLargeArray4 = doubleLargeArray;
                    final boolean z5 = z;
                    final long j19 = j8;
                    futureArr2[i] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            DoubleLargeArray doubleLargeArray = new DoubleLargeArray(j13);
                            for (long j = j15; j >= j16; j--) {
                                long access$800 = j * DoubleFFT_3D.this.sliceStridel;
                                long j2 = j * j17;
                                for (long access$900 = DoubleFFT_3D.this.rowsl - 1; access$900 >= 0; access$900--) {
                                    LargeArrayUtils.arraycopy(doubleLargeArray4, (DoubleFFT_3D.this.rowStridel * access$900) + access$800, doubleLargeArray, 0, DoubleFFT_3D.this.columnsl);
                                    DoubleFFT_3D.this.fftColumns.realInverseFull(doubleLargeArray, z5);
                                    LargeArrayUtils.arraycopy(doubleLargeArray, 0, doubleLargeArray4, j2 + (j19 * access$900), j13);
                                }
                            }
                        }
                    });
                    i++;
                    DoubleLargeArray doubleLargeArray5 = doubleLargeArray;
                    boolean z6 = z;
                    futureArr = futureArr2;
                    j3 = j18;
                    j10 = j14;
                    numberOfThreads = i2;
                    z3 = false;
                }
                long j20 = j10;
                int i3 = numberOfThreads;
                Future[] futureArr3 = futureArr;
                long j21 = j3;
                String str = null;
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException e) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
                } catch (ExecutionException e2) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
                }
                long j22 = j9 + 1;
                DoubleLargeArray doubleLargeArray6 = new DoubleLargeArray(this.rowsl * j22 * j21);
                int i4 = i3;
                int i5 = 0;
                while (i5 < i4) {
                    final long j23 = ((long) i5) * j11;
                    final long j24 = i5 == i4 + -1 ? j22 : j23 + j11;
                    final DoubleLargeArray doubleLargeArray7 = doubleLargeArray;
                    final DoubleLargeArray doubleLargeArray8 = doubleLargeArray6;
                    final long j25 = j21;
                    final boolean z7 = z;
                    futureArr3[i5] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            for (long j = j23; j < j24; j++) {
                                long access$800 = DoubleFFT_3D.this.sliceStridel * j;
                                for (long j2 = 0; j2 < DoubleFFT_3D.this.rowsl; j2++) {
                                    DoubleLargeArray doubleLargeArray = doubleLargeArray8;
                                    long j3 = j25;
                                    LargeArrayUtils.arraycopy(doubleLargeArray7, (DoubleFFT_3D.this.rowStridel * j2) + access$800, doubleLargeArray, (DoubleFFT_3D.this.rowsl * j * j3) + (j3 * j2), DoubleFFT_3D.this.columnsl);
                                    DoubleFFT_1D access$300 = DoubleFFT_3D.this.fftColumns;
                                    DoubleLargeArray doubleLargeArray2 = doubleLargeArray8;
                                    long j4 = j25;
                                    access$300.realInverseFull(doubleLargeArray2, (DoubleFFT_3D.this.rowsl * j * j4) + (j4 * j2), z7);
                                }
                            }
                        }
                    });
                    i5++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException e3) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e3);
                } catch (ExecutionException e4) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e4);
                }
                int i6 = 0;
                while (i6 < i4) {
                    final long j26 = ((long) i6) * j11;
                    final long j27 = i6 == i4 + -1 ? j22 : j26 + j11;
                    final long j28 = j7;
                    final DoubleLargeArray doubleLargeArray9 = doubleLargeArray6;
                    final long j29 = j21;
                    int i7 = i4;
                    final DoubleLargeArray doubleLargeArray10 = doubleLargeArray;
                    String str2 = str;
                    final long j30 = j8;
                    futureArr3[i6] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            for (long j = j26; j < j27; j++) {
                                long j2 = j28 * j;
                                for (long j3 = 0; j3 < DoubleFFT_3D.this.rowsl; j3++) {
                                    DoubleLargeArray doubleLargeArray = doubleLargeArray9;
                                    long j4 = j29;
                                    LargeArrayUtils.arraycopy(doubleLargeArray, (DoubleFFT_3D.this.rowsl * j * j4) + (j3 * j4), doubleLargeArray10, j2 + (j30 * j3), j4);
                                }
                            }
                        }
                    });
                    i6++;
                    str = str2;
                    doubleLargeArray6 = doubleLargeArray6;
                    i4 = i7;
                }
                int i8 = i4;
                String str3 = str;
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException e5) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e5);
                } catch (ExecutionException e6) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e6);
                }
                String str4 = str3;
                DoubleFFT_3D doubleFFT_3D = this;
                long j31 = doubleFFT_3D.slicesl / j20;
                int i9 = i8;
                int i10 = 0;
                while (i10 < i9) {
                    final long j32 = ((long) i10) * j31;
                    final long j33 = i10 == i9 + -1 ? doubleFFT_3D.slicesl : j32 + j31;
                    final long j34 = j7;
                    final long j35 = j8;
                    final DoubleLargeArray doubleLargeArray11 = doubleLargeArray;
                    final boolean z8 = z;
                    futureArr3[i10] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            long j = 2;
                            DoubleLargeArray doubleLargeArray = new DoubleLargeArray(DoubleFFT_3D.this.rowsl * 2, false);
                            long j2 = j32;
                            while (j2 < j33) {
                                long j3 = j34 * j2;
                                long j4 = 0;
                                while (j4 < DoubleFFT_3D.this.columnsl) {
                                    long j5 = j4 * j;
                                    long j6 = 0;
                                    while (j6 < DoubleFFT_3D.this.rowsl) {
                                        long j7 = (j35 * j6) + j3 + j5;
                                        long j8 = j6 * j;
                                        doubleLargeArray.setDouble(j8, doubleLargeArray11.getDouble(j7));
                                        doubleLargeArray.setDouble(j8 + 1, doubleLargeArray11.getDouble(j7 + 1));
                                        j6++;
                                        j = 2;
                                    }
                                    DoubleFFT_3D.this.fftRows.complexInverse(doubleLargeArray, z8);
                                    long j9 = 0;
                                    while (j9 < DoubleFFT_3D.this.rowsl) {
                                        long j10 = (j35 * j9) + j3 + j5;
                                        long j11 = j3;
                                        long j12 = j9 * 2;
                                        doubleLargeArray11.setDouble(j10, doubleLargeArray.getDouble(j12));
                                        doubleLargeArray11.setDouble(j10 + 1, doubleLargeArray.getDouble(j12 + 1));
                                        j9++;
                                        j3 = j11;
                                    }
                                    long j13 = j3;
                                    j4++;
                                    j = 2;
                                }
                                j2++;
                                j = 2;
                            }
                        }
                    });
                    i10++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException e7) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, str4, e7);
                } catch (ExecutionException e8) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, str4, e8);
                }
                long j36 = j5 / j20;
                int i11 = 0;
                while (i11 < i9) {
                    final long j37 = ((long) i11) * j36;
                    final long j38 = i11 == i9 + -1 ? j5 : j37 + j36;
                    final long j39 = j8;
                    final long j40 = j7;
                    final DoubleLargeArray doubleLargeArray12 = doubleLargeArray;
                    final boolean z9 = z;
                    futureArr3[i11] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            long j = 2;
                            DoubleLargeArray doubleLargeArray = new DoubleLargeArray(DoubleFFT_3D.this.slicesl * 2, false);
                            long j2 = j37;
                            while (j2 < j38) {
                                long j3 = j39 * j2;
                                long j4 = 0;
                                while (true) {
                                    long j5 = 1;
                                    if (j4 >= DoubleFFT_3D.this.columnsl) {
                                        break;
                                    }
                                    long j6 = j4 * j;
                                    long j7 = 0;
                                    while (j7 < DoubleFFT_3D.this.slicesl) {
                                        long j8 = j7 * j;
                                        long j9 = (j40 * j7) + j3 + j6;
                                        doubleLargeArray.setDouble(j8, doubleLargeArray12.getDouble(j9));
                                        doubleLargeArray.setDouble(j8 + 1, doubleLargeArray12.getDouble(j9 + 1));
                                        j7++;
                                        j2 = j2;
                                        j = 2;
                                    }
                                    long j10 = j2;
                                    DoubleFFT_3D.this.fftSlices.complexInverse(doubleLargeArray, z9);
                                    for (long j11 = 0; j11 < DoubleFFT_3D.this.slicesl; j11++) {
                                        long j12 = j11 * 2;
                                        long j13 = (j40 * j11) + j3 + j6;
                                        doubleLargeArray12.setDouble(j13, doubleLargeArray.getDouble(j12));
                                        j5 = 1;
                                        doubleLargeArray12.setDouble(j13 + 1, doubleLargeArray.getDouble(j12 + 1));
                                    }
                                    j4 += j5;
                                    j2 = j10;
                                    j = 2;
                                }
                                j2++;
                                j = 2;
                            }
                        }
                    });
                    i11++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException e9) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, str4, e9);
                } catch (ExecutionException e10) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, str4, e10);
                }
                long j41 = doubleFFT_3D.slicesl / j20;
                int i12 = 0;
                while (i12 < i9) {
                    final long j42 = ((long) i12) * j41;
                    final long j43 = i12 == i9 + -1 ? doubleFFT_3D.slicesl : j42 + j41;
                    final long j44 = j7;
                    final long j45 = j6;
                    final long j46 = j8;
                    final long j47 = j21;
                    final DoubleLargeArray doubleLargeArray13 = doubleLargeArray;
                    futureArr3[i12] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            for (long j = j42; j < j43; j++) {
                                long access$1200 = (DoubleFFT_3D.this.slicesl - j) % DoubleFFT_3D.this.slicesl;
                                long j2 = j44;
                                long j3 = access$1200 * j2;
                                long j4 = j2 * j;
                                long j5 = 1;
                                while (j5 < j45) {
                                    long j6 = j46;
                                    long access$900 = (DoubleFFT_3D.this.rowsl - j5) * j6;
                                    long j7 = j6 * j5;
                                    long j8 = access$900 + j3;
                                    long j9 = 0;
                                    while (j9 < DoubleFFT_3D.this.columnsl) {
                                        long j10 = 2 * j9;
                                        long j11 = j3;
                                        long j12 = j47;
                                        long j13 = j12 - j10;
                                        long j14 = j4 + j7 + j10;
                                        long j15 = j4;
                                        DoubleLargeArray doubleLargeArray = doubleLargeArray13;
                                        doubleLargeArray.setDouble((j13 % j12) + j8, doubleLargeArray.getDouble(j14));
                                        DoubleLargeArray doubleLargeArray2 = doubleLargeArray13;
                                        doubleLargeArray2.setDouble(j8 + ((j13 + 1) % j47), -doubleLargeArray2.getDouble(j14 + 1));
                                        j9++;
                                        j3 = j11;
                                        j4 = j15;
                                        j7 = j7;
                                    }
                                    j5++;
                                    j3 = j3;
                                    j4 = j4;
                                }
                            }
                        }
                    });
                    i12++;
                    doubleFFT_3D = this;
                    i9 = i9;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                    return;
                } catch (InterruptedException e11) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e11);
                    return;
                } catch (ExecutionException e12) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e12);
                    return;
                }
            }
        }
        long j48 = j3;
        DoubleFFT_3D doubleFFT_3D2 = this;
        for (long j49 = doubleFFT_3D2.slicesl - 1; j49 >= 0; j49--) {
            long j50 = j49 * doubleFFT_3D2.sliceStridel;
            long j51 = j49 * j7;
            for (long j52 = doubleFFT_3D2.rowsl - 1; j52 >= 0; j52--) {
                LargeArrayUtils.arraycopy(doubleLargeArray, j50 + (doubleFFT_3D2.rowStridel * j52), doubleLargeArray3, 0, doubleFFT_3D2.columnsl);
                doubleFFT_3D2.fftColumns.realInverseFull(doubleLargeArray3, z);
                LargeArrayUtils.arraycopy(doubleLargeArray3, 0, doubleLargeArray, j51 + (j52 * j8), j48);
            }
        }
        DoubleLargeArray doubleLargeArray14 = new DoubleLargeArray(doubleFFT_3D2.rowsl * 2, false);
        long j53 = 0;
        while (j53 < doubleFFT_3D2.slicesl) {
            long j54 = j53 * j7;
            long j55 = 0;
            while (j55 < doubleFFT_3D2.columnsl) {
                long j56 = j55 * j2;
                long j57 = j54;
                long j58 = 0;
                while (j58 < doubleFFT_3D2.rowsl) {
                    long j59 = j58 * j2;
                    long j60 = j57 + (j58 * j8) + j56;
                    DoubleLargeArray doubleLargeArray15 = doubleLargeArray;
                    doubleLargeArray14.setDouble(j59, doubleLargeArray15.getDouble(j60));
                    doubleLargeArray14.setDouble(j59 + 1, doubleLargeArray15.getDouble(j60 + 1));
                    j58++;
                    j53 = j53;
                    j2 = 2;
                }
                DoubleLargeArray doubleLargeArray16 = doubleLargeArray;
                long j61 = j53;
                doubleFFT_3D2.fftRows.complexInverse(doubleLargeArray14, z);
                long j62 = 0;
                while (j62 < doubleFFT_3D2.rowsl) {
                    long j63 = j62 * 2;
                    long j64 = j57 + (j62 * j8) + j56;
                    doubleLargeArray16.setDouble(j64, doubleLargeArray14.getDouble(j63));
                    doubleLargeArray16.setDouble(j64 + 1, doubleLargeArray14.getDouble(j63 + 1));
                    j62++;
                    j56 = j56;
                }
                j55++;
                j54 = j57;
                j53 = j61;
                j2 = 2;
            }
            DoubleLargeArray doubleLargeArray17 = doubleLargeArray;
            boolean z10 = z;
            j53++;
            j2 = 2;
        }
        DoubleLargeArray doubleLargeArray18 = doubleLargeArray;
        boolean z11 = z;
        long j65 = 2;
        DoubleLargeArray doubleLargeArray19 = new DoubleLargeArray(doubleFFT_3D2.slicesl * 2, false);
        long j66 = 0;
        while (j66 < j5) {
            long j67 = j66 * j8;
            long j68 = 0;
            while (j68 < doubleFFT_3D2.columnsl) {
                long j69 = j68 * j65;
                long j70 = 0;
                while (j70 < doubleFFT_3D2.slicesl) {
                    long j71 = j66;
                    long j72 = j70 * 2;
                    long j73 = (j70 * j7) + j67 + j69;
                    doubleLargeArray19.setDouble(j72, doubleLargeArray18.getDouble(j73));
                    doubleLargeArray19.setDouble(j72 + 1, doubleLargeArray18.getDouble(j73 + 1));
                    j70++;
                    j66 = j71;
                    j68 = j68;
                }
                long j74 = j66;
                long j75 = j68;
                doubleFFT_3D2.fftSlices.complexInverse(doubleLargeArray19, z11);
                long j76 = 0;
                while (j76 < doubleFFT_3D2.slicesl) {
                    long j77 = j76 * 2;
                    long j78 = (j76 * j7) + j67 + j69;
                    doubleLargeArray18.setDouble(j78, doubleLargeArray19.getDouble(j77));
                    doubleLargeArray18.setDouble(j78 + 1, doubleLargeArray19.getDouble(j77 + 1));
                    j76++;
                    boolean z12 = z;
                }
                j68 = j75 + 1;
                z11 = z;
                j66 = j74;
                j65 = 2;
            }
            j66++;
            z11 = z;
            j65 = 2;
        }
        long j79 = 0;
        while (true) {
            long j80 = doubleFFT_3D2.slicesl;
            if (j79 < j80) {
                long j81 = ((j80 - j79) % j80) * j7;
                long j82 = j79 * j7;
                long j83 = 1;
                while (j83 < j6) {
                    long j84 = j83 * j8;
                    long j85 = ((doubleFFT_3D2.rowsl - j83) * j8) + j81;
                    long j86 = j81;
                    long j87 = 0;
                    while (j87 < doubleFFT_3D2.columnsl) {
                        long j88 = j87 * 2;
                        long j89 = j48 - j88;
                        long j90 = j82 + j84 + j88;
                        doubleLargeArray18.setDouble(j85 + (j89 % j48), doubleLargeArray18.getDouble(j90));
                        doubleLargeArray18.setDouble(j85 + ((j89 + 1) % j48), -doubleLargeArray18.getDouble(j90 + 1));
                        j87++;
                        doubleFFT_3D2 = this;
                        j82 = j82;
                    }
                    long j91 = j82;
                    j83++;
                    doubleFFT_3D2 = this;
                    j81 = j86;
                }
                j79++;
                doubleFFT_3D2 = this;
            } else {
                return;
            }
        }
    }

    private void xdft3da_sub1(int i, int i2, double[] dArr, boolean z) {
        double[] dArr2 = dArr;
        boolean z2 = z;
        int i3 = this.slices;
        int i4 = this.rows;
        if (i3 < i4) {
            i3 = i4;
        }
        int i5 = i3 * 8;
        int i6 = this.columns;
        if (i6 == 4) {
            i5 >>= 1;
        } else if (i6 < 4) {
            i5 >>= 2;
        }
        double[] dArr3 = new double[i5];
        if (i2 == -1) {
            for (int i7 = 0; i7 < this.slices; i7++) {
                int i8 = this.sliceStride * i7;
                if (i == 0) {
                    for (int i9 = 0; i9 < this.rows; i9++) {
                        this.fftColumns.complexForward(dArr2, (this.rowStride * i9) + i8);
                    }
                } else {
                    for (int i10 = 0; i10 < this.rows; i10++) {
                        this.fftColumns.realForward(dArr2, (this.rowStride * i10) + i8);
                    }
                }
                int i11 = this.columns;
                if (i11 > 4) {
                    for (int i12 = 0; i12 < this.columns; i12 += 8) {
                        int i13 = 0;
                        while (true) {
                            int i14 = this.rows;
                            if (i13 >= i14) {
                                break;
                            }
                            int i15 = (this.rowStride * i13) + i8 + i12;
                            int i16 = i13 * 2;
                            int i17 = (i14 * 2) + i16;
                            int i18 = (i14 * 2) + i17;
                            int i19 = (i14 * 2) + i18;
                            dArr3[i16] = dArr2[i15];
                            dArr3[i16 + 1] = dArr2[i15 + 1];
                            dArr3[i17] = dArr2[i15 + 2];
                            dArr3[i17 + 1] = dArr2[i15 + 3];
                            dArr3[i18] = dArr2[i15 + 4];
                            dArr3[i18 + 1] = dArr2[i15 + 5];
                            dArr3[i19] = dArr2[i15 + 6];
                            dArr3[i19 + 1] = dArr2[i15 + 7];
                            i13++;
                        }
                        this.fftRows.complexForward(dArr3, 0);
                        this.fftRows.complexForward(dArr3, this.rows * 2);
                        this.fftRows.complexForward(dArr3, this.rows * 4);
                        this.fftRows.complexForward(dArr3, this.rows * 6);
                        int i20 = 0;
                        while (true) {
                            int i21 = this.rows;
                            if (i20 >= i21) {
                                break;
                            }
                            int i22 = (this.rowStride * i20) + i8 + i12;
                            int i23 = i20 * 2;
                            int i24 = (i21 * 2) + i23;
                            int i25 = (i21 * 2) + i24;
                            int i26 = (i21 * 2) + i25;
                            dArr2[i22] = dArr3[i23];
                            dArr2[i22 + 1] = dArr3[i23 + 1];
                            dArr2[i22 + 2] = dArr3[i24];
                            dArr2[i22 + 3] = dArr3[i24 + 1];
                            dArr2[i22 + 4] = dArr3[i25];
                            dArr2[i22 + 5] = dArr3[i25 + 1];
                            dArr2[i22 + 6] = dArr3[i26];
                            dArr2[i22 + 7] = dArr3[i26 + 1];
                            i20++;
                        }
                    }
                } else if (i11 == 4) {
                    int i27 = 0;
                    while (true) {
                        int i28 = this.rows;
                        if (i27 >= i28) {
                            break;
                        }
                        int i29 = (this.rowStride * i27) + i8;
                        int i30 = i27 * 2;
                        int i31 = (i28 * 2) + i30;
                        dArr3[i30] = dArr2[i29];
                        dArr3[i30 + 1] = dArr2[i29 + 1];
                        dArr3[i31] = dArr2[i29 + 2];
                        dArr3[i31 + 1] = dArr2[i29 + 3];
                        i27++;
                    }
                    this.fftRows.complexForward(dArr3, 0);
                    this.fftRows.complexForward(dArr3, this.rows * 2);
                    int i32 = 0;
                    while (true) {
                        int i33 = this.rows;
                        if (i32 >= i33) {
                            break;
                        }
                        int i34 = (this.rowStride * i32) + i8;
                        int i35 = i32 * 2;
                        int i36 = (i33 * 2) + i35;
                        dArr2[i34] = dArr3[i35];
                        dArr2[i34 + 1] = dArr3[i35 + 1];
                        dArr2[i34 + 2] = dArr3[i36];
                        dArr2[i34 + 3] = dArr3[i36 + 1];
                        i32++;
                    }
                } else if (i11 == 2) {
                    for (int i37 = 0; i37 < this.rows; i37++) {
                        int i38 = (this.rowStride * i37) + i8;
                        int i39 = i37 * 2;
                        dArr3[i39] = dArr2[i38];
                        dArr3[i39 + 1] = dArr2[i38 + 1];
                    }
                    this.fftRows.complexForward(dArr3, 0);
                    for (int i40 = 0; i40 < this.rows; i40++) {
                        int i41 = (this.rowStride * i40) + i8;
                        int i42 = i40 * 2;
                        dArr2[i41] = dArr3[i42];
                        dArr2[i41 + 1] = dArr3[i42 + 1];
                    }
                }
            }
            return;
        }
        for (int i43 = 0; i43 < this.slices; i43++) {
            int i44 = this.sliceStride * i43;
            if (i == 0) {
                for (int i45 = 0; i45 < this.rows; i45++) {
                    this.fftColumns.complexInverse(dArr2, (this.rowStride * i45) + i44, z2);
                }
            }
            int i46 = this.columns;
            if (i46 > 4) {
                for (int i47 = 0; i47 < this.columns; i47 += 8) {
                    int i48 = 0;
                    while (true) {
                        int i49 = this.rows;
                        if (i48 >= i49) {
                            break;
                        }
                        int i50 = (this.rowStride * i48) + i44 + i47;
                        int i51 = i48 * 2;
                        int i52 = (i49 * 2) + i51;
                        int i53 = (i49 * 2) + i52;
                        int i54 = (i49 * 2) + i53;
                        dArr3[i51] = dArr2[i50];
                        dArr3[i51 + 1] = dArr2[i50 + 1];
                        dArr3[i52] = dArr2[i50 + 2];
                        dArr3[i52 + 1] = dArr2[i50 + 3];
                        dArr3[i53] = dArr2[i50 + 4];
                        dArr3[i53 + 1] = dArr2[i50 + 5];
                        dArr3[i54] = dArr2[i50 + 6];
                        dArr3[i54 + 1] = dArr2[i50 + 7];
                        i48++;
                    }
                    this.fftRows.complexInverse(dArr3, 0, z2);
                    this.fftRows.complexInverse(dArr3, this.rows * 2, z2);
                    this.fftRows.complexInverse(dArr3, this.rows * 4, z2);
                    this.fftRows.complexInverse(dArr3, this.rows * 6, z2);
                    int i55 = 0;
                    while (true) {
                        int i56 = this.rows;
                        if (i55 >= i56) {
                            break;
                        }
                        int i57 = (this.rowStride * i55) + i44 + i47;
                        int i58 = i55 * 2;
                        int i59 = (i56 * 2) + i58;
                        int i60 = (i56 * 2) + i59;
                        int i61 = (i56 * 2) + i60;
                        dArr2[i57] = dArr3[i58];
                        dArr2[i57 + 1] = dArr3[i58 + 1];
                        dArr2[i57 + 2] = dArr3[i59];
                        dArr2[i57 + 3] = dArr3[i59 + 1];
                        dArr2[i57 + 4] = dArr3[i60];
                        dArr2[i57 + 5] = dArr3[i60 + 1];
                        dArr2[i57 + 6] = dArr3[i61];
                        dArr2[i57 + 7] = dArr3[i61 + 1];
                        i55++;
                    }
                }
            } else if (i46 == 4) {
                int i62 = 0;
                while (true) {
                    int i63 = this.rows;
                    if (i62 >= i63) {
                        break;
                    }
                    int i64 = (this.rowStride * i62) + i44;
                    int i65 = i62 * 2;
                    int i66 = (i63 * 2) + i65;
                    dArr3[i65] = dArr2[i64];
                    dArr3[i65 + 1] = dArr2[i64 + 1];
                    dArr3[i66] = dArr2[i64 + 2];
                    dArr3[i66 + 1] = dArr2[i64 + 3];
                    i62++;
                }
                this.fftRows.complexInverse(dArr3, 0, z2);
                this.fftRows.complexInverse(dArr3, this.rows * 2, z2);
                int i67 = 0;
                while (true) {
                    int i68 = this.rows;
                    if (i67 >= i68) {
                        break;
                    }
                    int i69 = (this.rowStride * i67) + i44;
                    int i70 = i67 * 2;
                    int i71 = (i68 * 2) + i70;
                    dArr2[i69] = dArr3[i70];
                    dArr2[i69 + 1] = dArr3[i70 + 1];
                    dArr2[i69 + 2] = dArr3[i71];
                    dArr2[i69 + 3] = dArr3[i71 + 1];
                    i67++;
                }
            } else if (i46 == 2) {
                for (int i72 = 0; i72 < this.rows; i72++) {
                    int i73 = (this.rowStride * i72) + i44;
                    int i74 = i72 * 2;
                    dArr3[i74] = dArr2[i73];
                    dArr3[i74 + 1] = dArr2[i73 + 1];
                }
                this.fftRows.complexInverse(dArr3, 0, z2);
                for (int i75 = 0; i75 < this.rows; i75++) {
                    int i76 = (this.rowStride * i75) + i44;
                    int i77 = i75 * 2;
                    dArr2[i76] = dArr3[i77];
                    dArr2[i76 + 1] = dArr3[i77 + 1];
                }
            }
            if (i != 0) {
                for (int i78 = 0; i78 < this.rows; i78++) {
                    this.fftColumns.realInverse(dArr2, (this.rowStride * i78) + i44, z2);
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:107:0x050d  */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x0521 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0297  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void xdft3da_sub1(long r36, int r38, pl.edu.icm.jlargearrays.DoubleLargeArray r39, boolean r40) {
        /*
            r35 = this;
            r0 = r35
            r1 = r39
            r2 = r40
            long r3 = r0.slicesl
            long r5 = r0.rowsl
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 >= 0) goto L_0x000f
            r3 = r5
        L_0x000f:
            r5 = 8
            long r3 = r3 * r5
            long r7 = r0.columnsl
            r9 = 4
            int r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r11 != 0) goto L_0x001d
            r7 = 1
        L_0x001b:
            long r3 = r3 >> r7
            goto L_0x0023
        L_0x001d:
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 >= 0) goto L_0x0023
            r7 = 2
            goto L_0x001b
        L_0x0023:
            pl.edu.icm.jlargearrays.DoubleLargeArray r7 = new pl.edu.icm.jlargearrays.DoubleLargeArray
            r7.<init>((long) r3)
            r3 = -1
            r15 = 6
            r17 = 3
            r5 = 0
            r19 = 2
            r21 = 1
            r4 = r38
            if (r4 != r3) goto L_0x0297
            r2 = r5
        L_0x0038:
            long r11 = r0.slicesl
            int r4 = (r2 > r11 ? 1 : (r2 == r11 ? 0 : -1))
            if (r4 >= 0) goto L_0x0529
            long r11 = r0.sliceStridel
            long r11 = r11 * r2
            int r4 = (r36 > r5 ? 1 : (r36 == r5 ? 0 : -1))
            if (r4 != 0) goto L_0x005a
            r23 = r5
        L_0x0047:
            long r5 = r0.rowsl
            int r4 = (r23 > r5 ? 1 : (r23 == r5 ? 0 : -1))
            if (r4 >= 0) goto L_0x006e
            org.jtransforms.fft.DoubleFFT_1D r4 = r0.fftColumns
            long r5 = r0.rowStridel
            long r5 = r5 * r23
            long r5 = r5 + r11
            r4.complexForward((pl.edu.icm.jlargearrays.DoubleLargeArray) r1, (long) r5)
            long r23 = r23 + r21
            goto L_0x0047
        L_0x005a:
            r4 = 0
        L_0x005c:
            long r13 = r0.rowsl
            int r6 = (r4 > r13 ? 1 : (r4 == r13 ? 0 : -1))
            if (r6 >= 0) goto L_0x006e
            org.jtransforms.fft.DoubleFFT_1D r6 = r0.fftColumns
            long r13 = r0.rowStridel
            long r13 = r13 * r4
            long r13 = r13 + r11
            r6.realForward((pl.edu.icm.jlargearrays.DoubleLargeArray) r1, (long) r13)
            long r4 = r4 + r21
            goto L_0x005c
        L_0x006e:
            long r4 = r0.columnsl
            int r6 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
            if (r6 <= 0) goto L_0x01ad
            r4 = 0
        L_0x0076:
            long r13 = r0.columnsl
            int r6 = (r4 > r13 ? 1 : (r4 == r13 ? 0 : -1))
            if (r6 >= 0) goto L_0x01a9
            r13 = 0
        L_0x007e:
            long r9 = r0.rowsl
            int r6 = (r13 > r9 ? 1 : (r13 == r9 ? 0 : -1))
            if (r6 >= 0) goto L_0x00fe
            r25 = r2
            long r2 = r0.rowStridel
            long r2 = r2 * r13
            long r2 = r2 + r11
            long r2 = r2 + r4
            r27 = r4
            long r4 = r13 * r19
            long r29 = r9 * r19
            r31 = r11
            long r11 = r29 + r4
            long r29 = r9 * r19
            r33 = r13
            long r13 = r11 + r29
            long r9 = r9 * r19
            long r9 = r9 + r13
            r29 = r9
            double r8 = r1.getDouble(r2)
            r7.setDouble(r4, r8)
            long r4 = r4 + r21
            long r8 = r2 + r21
            double r8 = r1.getDouble(r8)
            r7.setDouble(r4, r8)
            long r4 = r2 + r19
            double r4 = r1.getDouble(r4)
            r7.setDouble(r11, r4)
            long r11 = r11 + r21
            long r4 = r2 + r17
            double r4 = r1.getDouble(r4)
            r7.setDouble(r11, r4)
            r4 = 4
            long r9 = r2 + r4
            double r4 = r1.getDouble(r9)
            r7.setDouble(r13, r4)
            long r13 = r13 + r21
            r4 = 5
            long r8 = r2 + r4
            double r4 = r1.getDouble(r8)
            r7.setDouble(r13, r4)
            long r4 = r2 + r15
            double r4 = r1.getDouble(r4)
            r13 = r29
            r7.setDouble(r13, r4)
            long r9 = r13 + r21
            r4 = 7
            long r2 = r2 + r4
            double r2 = r1.getDouble(r2)
            r7.setDouble(r9, r2)
            long r13 = r33 + r21
            r2 = r25
            r4 = r27
            r11 = r31
            goto L_0x007e
        L_0x00fe:
            r25 = r2
            r27 = r4
            r31 = r11
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
        L_0x0128:
            long r4 = r0.rowsl
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 >= 0) goto L_0x019d
            long r8 = r0.rowStridel
            long r8 = r8 * r2
            long r11 = r31 + r8
            long r11 = r11 + r27
            long r8 = r2 * r19
            long r13 = r4 * r19
            long r13 = r13 + r8
            long r29 = r4 * r19
            r33 = r2
            long r2 = r13 + r29
            long r4 = r4 * r19
            long r4 = r4 + r2
            r29 = r4
            double r4 = r7.getDouble(r8)
            r1.setDouble(r11, r4)
            long r4 = r11 + r21
            long r8 = r8 + r21
            double r8 = r7.getDouble(r8)
            r1.setDouble(r4, r8)
            long r4 = r11 + r19
            double r8 = r7.getDouble(r13)
            r1.setDouble(r4, r8)
            long r4 = r11 + r17
            long r13 = r13 + r21
            double r8 = r7.getDouble(r13)
            r1.setDouble(r4, r8)
            r4 = 4
            long r9 = r11 + r4
            double r4 = r7.getDouble(r2)
            r1.setDouble(r9, r4)
            r4 = 5
            long r13 = r11 + r4
            long r2 = r2 + r21
            double r2 = r7.getDouble(r2)
            r1.setDouble(r13, r2)
            long r2 = r11 + r15
            r4 = r29
            double r8 = r7.getDouble(r4)
            r1.setDouble(r2, r8)
            r2 = 7
            long r11 = r11 + r2
            long r4 = r4 + r21
            double r2 = r7.getDouble(r4)
            r1.setDouble(r11, r2)
            long r2 = r33 + r21
            goto L_0x0128
        L_0x019d:
            r2 = 8
            long r4 = r27 + r2
            r2 = r25
            r11 = r31
            r9 = 4
            goto L_0x0076
        L_0x01a9:
            r25 = r2
            goto L_0x028f
        L_0x01ad:
            r25 = r2
            r2 = r9
            r31 = r11
            int r6 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r6 != 0) goto L_0x023c
            r2 = 0
        L_0x01b8:
            long r4 = r0.rowsl
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 >= 0) goto L_0x01f1
            long r8 = r0.rowStridel
            long r8 = r8 * r2
            long r11 = r31 + r8
            long r8 = r2 * r19
            long r4 = r4 * r19
            long r4 = r4 + r8
            double r13 = r1.getDouble(r11)
            r7.setDouble(r8, r13)
            long r8 = r8 + r21
            long r13 = r11 + r21
            double r13 = r1.getDouble(r13)
            r7.setDouble(r8, r13)
            long r8 = r11 + r19
            double r8 = r1.getDouble(r8)
            r7.setDouble(r4, r8)
            long r4 = r4 + r21
            long r11 = r11 + r17
            double r8 = r1.getDouble(r11)
            r7.setDouble(r4, r8)
            long r2 = r2 + r21
            goto L_0x01b8
        L_0x01f1:
            org.jtransforms.fft.DoubleFFT_1D r2 = r0.fftRows
            r3 = 0
            r2.complexForward((pl.edu.icm.jlargearrays.DoubleLargeArray) r7, (long) r3)
            org.jtransforms.fft.DoubleFFT_1D r2 = r0.fftRows
            long r3 = r0.rowsl
            long r3 = r3 * r19
            r2.complexForward((pl.edu.icm.jlargearrays.DoubleLargeArray) r7, (long) r3)
            r2 = 0
        L_0x0203:
            long r4 = r0.rowsl
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 >= 0) goto L_0x028f
            long r8 = r0.rowStridel
            long r8 = r8 * r2
            long r11 = r31 + r8
            long r8 = r2 * r19
            long r4 = r4 * r19
            long r4 = r4 + r8
            double r13 = r7.getDouble(r8)
            r1.setDouble(r11, r13)
            long r13 = r11 + r21
            long r8 = r8 + r21
            double r8 = r7.getDouble(r8)
            r1.setDouble(r13, r8)
            long r8 = r11 + r19
            double r13 = r7.getDouble(r4)
            r1.setDouble(r8, r13)
            long r11 = r11 + r17
            long r4 = r4 + r21
            double r4 = r7.getDouble(r4)
            r1.setDouble(r11, r4)
            long r2 = r2 + r21
            goto L_0x0203
        L_0x023c:
            int r2 = (r4 > r19 ? 1 : (r4 == r19 ? 0 : -1))
            if (r2 != 0) goto L_0x028f
            r2 = 0
        L_0x0242:
            long r4 = r0.rowsl
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 >= 0) goto L_0x0264
            long r4 = r0.rowStridel
            long r4 = r4 * r2
            long r11 = r31 + r4
            long r4 = r2 * r19
            double r8 = r1.getDouble(r11)
            r7.setDouble(r4, r8)
            long r4 = r4 + r21
            long r11 = r11 + r21
            double r8 = r1.getDouble(r11)
            r7.setDouble(r4, r8)
            long r2 = r2 + r21
            goto L_0x0242
        L_0x0264:
            org.jtransforms.fft.DoubleFFT_1D r2 = r0.fftRows
            r3 = 0
            r2.complexForward((pl.edu.icm.jlargearrays.DoubleLargeArray) r7, (long) r3)
            r2 = 0
        L_0x026d:
            long r4 = r0.rowsl
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 >= 0) goto L_0x028f
            long r4 = r0.rowStridel
            long r4 = r4 * r2
            long r11 = r31 + r4
            long r4 = r2 * r19
            double r8 = r7.getDouble(r4)
            r1.setDouble(r11, r8)
            long r11 = r11 + r21
            long r4 = r4 + r21
            double r4 = r7.getDouble(r4)
            r1.setDouble(r11, r4)
            long r2 = r2 + r21
            goto L_0x026d
        L_0x028f:
            long r2 = r25 + r21
            r5 = 0
            r9 = 4
            goto L_0x0038
        L_0x0297:
            r3 = 0
        L_0x0299:
            long r5 = r0.slicesl
            int r5 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r5 >= 0) goto L_0x0529
            long r5 = r0.sliceStridel
            long r5 = r5 * r3
            r8 = 0
            int r10 = (r36 > r8 ? 1 : (r36 == r8 ? 0 : -1))
            if (r10 != 0) goto L_0x02bc
            r8 = 0
        L_0x02aa:
            long r11 = r0.rowsl
            int r11 = (r8 > r11 ? 1 : (r8 == r11 ? 0 : -1))
            if (r11 >= 0) goto L_0x02bc
            org.jtransforms.fft.DoubleFFT_1D r11 = r0.fftColumns
            long r12 = r0.rowStridel
            long r12 = r12 * r8
            long r12 = r12 + r5
            r11.complexInverse((pl.edu.icm.jlargearrays.DoubleLargeArray) r1, (long) r12, (boolean) r2)
            long r8 = r8 + r21
            goto L_0x02aa
        L_0x02bc:
            long r8 = r0.columnsl
            r11 = 4
            int r13 = (r8 > r11 ? 1 : (r8 == r11 ? 0 : -1))
            if (r13 <= 0) goto L_0x0413
            r8 = 0
        L_0x02c6:
            long r11 = r0.columnsl
            int r11 = (r8 > r11 ? 1 : (r8 == r11 ? 0 : -1))
            if (r11 >= 0) goto L_0x0409
            r11 = 0
        L_0x02ce:
            long r13 = r0.rowsl
            int r25 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r25 >= 0) goto L_0x0353
            r25 = r3
            long r3 = r0.rowStridel
            long r3 = r3 * r11
            long r3 = r3 + r5
            long r3 = r3 + r8
            r27 = r8
            long r8 = r11 * r19
            long r29 = r13 * r19
            r31 = r5
            long r5 = r29 + r8
            long r29 = r13 * r19
            r38 = r10
            r33 = r11
            long r10 = r5 + r29
            long r13 = r13 * r19
            long r13 = r13 + r10
            r29 = r13
            double r12 = r1.getDouble(r3)
            r7.setDouble(r8, r12)
            long r8 = r8 + r21
            long r12 = r3 + r21
            double r12 = r1.getDouble(r12)
            r7.setDouble(r8, r12)
            long r8 = r3 + r19
            double r8 = r1.getDouble(r8)
            r7.setDouble(r5, r8)
            long r5 = r5 + r21
            long r8 = r3 + r17
            double r8 = r1.getDouble(r8)
            r7.setDouble(r5, r8)
            r5 = 4
            long r8 = r3 + r5
            double r5 = r1.getDouble(r8)
            r7.setDouble(r10, r5)
            long r10 = r10 + r21
            r5 = 5
            long r13 = r3 + r5
            double r5 = r1.getDouble(r13)
            r7.setDouble(r10, r5)
            long r5 = r3 + r15
            double r5 = r1.getDouble(r5)
            r10 = r29
            r7.setDouble(r10, r5)
            long r13 = r10 + r21
            r5 = 7
            long r3 = r3 + r5
            double r3 = r1.getDouble(r3)
            r7.setDouble(r13, r3)
            long r11 = r33 + r21
            r10 = r38
            r3 = r25
            r8 = r27
            r5 = r31
            goto L_0x02ce
        L_0x0353:
            r25 = r3
            r31 = r5
            r27 = r8
            r38 = r10
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
        L_0x037f:
            long r5 = r0.rowsl
            int r8 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r8 >= 0) goto L_0x03f7
            long r8 = r0.rowStridel
            long r8 = r8 * r3
            long r8 = r31 + r8
            long r8 = r8 + r27
            long r10 = r3 * r19
            long r12 = r5 * r19
            long r12 = r12 + r10
            long r29 = r5 * r19
            r33 = r3
            long r2 = r12 + r29
            long r5 = r5 * r19
            long r5 = r5 + r2
            r29 = r5
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
            r10 = r29
            double r12 = r7.getDouble(r10)
            r1.setDouble(r2, r12)
            r2 = 7
            long r8 = r8 + r2
            long r10 = r10 + r21
            double r10 = r7.getDouble(r10)
            r1.setDouble(r8, r10)
            long r8 = r33 + r21
            r2 = r40
            r3 = r8
            goto L_0x037f
        L_0x03f7:
            r2 = 7
            r4 = 5
            r10 = 8
            long r8 = r27 + r10
            r10 = r38
            r2 = r40
            r3 = r25
            r5 = r31
            goto L_0x02c6
        L_0x0409:
            r25 = r3
            r31 = r5
            r38 = r10
            r3 = r40
            goto L_0x0509
        L_0x0413:
            r25 = r3
            r31 = r5
            r38 = r10
            r2 = 7
            r4 = 5
            r10 = 8
            r12 = 4
            int r6 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
            if (r6 != 0) goto L_0x04b5
            r8 = 0
        L_0x0427:
            long r2 = r0.rowsl
            int r6 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r6 >= 0) goto L_0x0466
            long r4 = r0.rowStridel
            long r4 = r4 * r8
            long r5 = r31 + r4
            long r10 = r8 * r19
            long r2 = r2 * r19
            long r2 = r2 + r10
            double r12 = r1.getDouble(r5)
            r7.setDouble(r10, r12)
            long r10 = r10 + r21
            long r12 = r5 + r21
            double r12 = r1.getDouble(r12)
            r7.setDouble(r10, r12)
            long r10 = r5 + r19
            double r10 = r1.getDouble(r10)
            r7.setDouble(r2, r10)
            long r2 = r2 + r21
            long r5 = r5 + r17
            double r4 = r1.getDouble(r5)
            r7.setDouble(r2, r4)
            long r8 = r8 + r21
            r4 = 5
            r10 = 8
            r12 = 4
            goto L_0x0427
        L_0x0466:
            org.jtransforms.fft.DoubleFFT_1D r2 = r0.fftRows
            r3 = r40
            r4 = 0
            r2.complexInverse((pl.edu.icm.jlargearrays.DoubleLargeArray) r7, (long) r4, (boolean) r3)
            org.jtransforms.fft.DoubleFFT_1D r2 = r0.fftRows
            long r4 = r0.rowsl
            long r4 = r4 * r19
            r2.complexInverse((pl.edu.icm.jlargearrays.DoubleLargeArray) r7, (long) r4, (boolean) r3)
            r4 = 0
        L_0x047a:
            long r8 = r0.rowsl
            int r2 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r2 >= 0) goto L_0x0509
            long r10 = r0.rowStridel
            long r10 = r10 * r4
            long r10 = r31 + r10
            long r12 = r4 * r19
            long r8 = r8 * r19
            long r8 = r8 + r12
            double r14 = r7.getDouble(r12)
            r1.setDouble(r10, r14)
            long r14 = r10 + r21
            long r12 = r12 + r21
            double r12 = r7.getDouble(r12)
            r1.setDouble(r14, r12)
            long r12 = r10 + r19
            double r14 = r7.getDouble(r8)
            r1.setDouble(r12, r14)
            long r10 = r10 + r17
            long r8 = r8 + r21
            double r8 = r7.getDouble(r8)
            r1.setDouble(r10, r8)
            long r4 = r4 + r21
            r15 = 6
            goto L_0x047a
        L_0x04b5:
            r3 = r40
            int r2 = (r8 > r19 ? 1 : (r8 == r19 ? 0 : -1))
            if (r2 != 0) goto L_0x0509
            r4 = 0
        L_0x04bd:
            long r8 = r0.rowsl
            int r2 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r2 >= 0) goto L_0x04df
            long r8 = r0.rowStridel
            long r8 = r8 * r4
            long r8 = r31 + r8
            long r10 = r4 * r19
            double r12 = r1.getDouble(r8)
            r7.setDouble(r10, r12)
            long r10 = r10 + r21
            long r8 = r8 + r21
            double r8 = r1.getDouble(r8)
            r7.setDouble(r10, r8)
            long r4 = r4 + r21
            goto L_0x04bd
        L_0x04df:
            org.jtransforms.fft.DoubleFFT_1D r2 = r0.fftRows
            r4 = 0
            r2.complexInverse((pl.edu.icm.jlargearrays.DoubleLargeArray) r7, (long) r4, (boolean) r3)
            r8 = r4
        L_0x04e7:
            long r10 = r0.rowsl
            int r2 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r2 >= 0) goto L_0x050b
            long r10 = r0.rowStridel
            long r10 = r10 * r8
            long r10 = r31 + r10
            long r12 = r8 * r19
            double r14 = r7.getDouble(r12)
            r1.setDouble(r10, r14)
            long r10 = r10 + r21
            long r12 = r12 + r21
            double r12 = r7.getDouble(r12)
            r1.setDouble(r10, r12)
            long r8 = r8 + r21
            goto L_0x04e7
        L_0x0509:
            r4 = 0
        L_0x050b:
            if (r38 == 0) goto L_0x0521
            r8 = r4
        L_0x050e:
            long r10 = r0.rowsl
            int r2 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r2 >= 0) goto L_0x0521
            org.jtransforms.fft.DoubleFFT_1D r2 = r0.fftColumns
            long r10 = r0.rowStridel
            long r10 = r10 * r8
            long r10 = r31 + r10
            r2.realInverse((pl.edu.icm.jlargearrays.DoubleLargeArray) r1, (long) r10, (boolean) r3)
            long r8 = r8 + r21
            goto L_0x050e
        L_0x0521:
            long r8 = r25 + r21
            r2 = r3
            r3 = r8
            r15 = 6
            goto L_0x0299
        L_0x0529:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.DoubleFFT_3D.xdft3da_sub1(long, int, pl.edu.icm.jlargearrays.DoubleLargeArray, boolean):void");
    }

    private void xdft3da_sub2(int i, int i2, double[] dArr, boolean z) {
        double[] dArr2 = dArr;
        boolean z2 = z;
        int i3 = this.slices;
        int i4 = this.rows;
        if (i3 < i4) {
            i3 = i4;
        }
        int i5 = i3 * 8;
        int i6 = this.columns;
        if (i6 == 4) {
            i5 >>= 1;
        } else if (i6 < 4) {
            i5 >>= 2;
        }
        double[] dArr3 = new double[i5];
        if (i2 == -1) {
            for (int i7 = 0; i7 < this.slices; i7++) {
                int i8 = this.sliceStride * i7;
                if (i == 0) {
                    for (int i9 = 0; i9 < this.rows; i9++) {
                        this.fftColumns.complexForward(dArr2, (this.rowStride * i9) + i8);
                    }
                } else {
                    for (int i10 = 0; i10 < this.rows; i10++) {
                        this.fftColumns.realForward(dArr2, (this.rowStride * i10) + i8);
                    }
                }
                int i11 = this.columns;
                if (i11 > 4) {
                    for (int i12 = 0; i12 < this.columns; i12 += 8) {
                        int i13 = 0;
                        while (true) {
                            int i14 = this.rows;
                            if (i13 >= i14) {
                                break;
                            }
                            int i15 = (this.rowStride * i13) + i8 + i12;
                            int i16 = i13 * 2;
                            int i17 = (i14 * 2) + i16;
                            int i18 = (i14 * 2) + i17;
                            int i19 = (i14 * 2) + i18;
                            dArr3[i16] = dArr2[i15];
                            dArr3[i16 + 1] = dArr2[i15 + 1];
                            dArr3[i17] = dArr2[i15 + 2];
                            dArr3[i17 + 1] = dArr2[i15 + 3];
                            dArr3[i18] = dArr2[i15 + 4];
                            dArr3[i18 + 1] = dArr2[i15 + 5];
                            dArr3[i19] = dArr2[i15 + 6];
                            dArr3[i19 + 1] = dArr2[i15 + 7];
                            i13++;
                        }
                        this.fftRows.complexForward(dArr3, 0);
                        this.fftRows.complexForward(dArr3, this.rows * 2);
                        this.fftRows.complexForward(dArr3, this.rows * 4);
                        this.fftRows.complexForward(dArr3, this.rows * 6);
                        int i20 = 0;
                        while (true) {
                            int i21 = this.rows;
                            if (i20 >= i21) {
                                break;
                            }
                            int i22 = (this.rowStride * i20) + i8 + i12;
                            int i23 = i20 * 2;
                            int i24 = (i21 * 2) + i23;
                            int i25 = (i21 * 2) + i24;
                            int i26 = (i21 * 2) + i25;
                            dArr2[i22] = dArr3[i23];
                            dArr2[i22 + 1] = dArr3[i23 + 1];
                            dArr2[i22 + 2] = dArr3[i24];
                            dArr2[i22 + 3] = dArr3[i24 + 1];
                            dArr2[i22 + 4] = dArr3[i25];
                            dArr2[i22 + 5] = dArr3[i25 + 1];
                            dArr2[i22 + 6] = dArr3[i26];
                            dArr2[i22 + 7] = dArr3[i26 + 1];
                            i20++;
                        }
                    }
                } else if (i11 == 4) {
                    int i27 = 0;
                    while (true) {
                        int i28 = this.rows;
                        if (i27 >= i28) {
                            break;
                        }
                        int i29 = (this.rowStride * i27) + i8;
                        int i30 = i27 * 2;
                        int i31 = (i28 * 2) + i30;
                        dArr3[i30] = dArr2[i29];
                        dArr3[i30 + 1] = dArr2[i29 + 1];
                        dArr3[i31] = dArr2[i29 + 2];
                        dArr3[i31 + 1] = dArr2[i29 + 3];
                        i27++;
                    }
                    this.fftRows.complexForward(dArr3, 0);
                    this.fftRows.complexForward(dArr3, this.rows * 2);
                    int i32 = 0;
                    while (true) {
                        int i33 = this.rows;
                        if (i32 >= i33) {
                            break;
                        }
                        int i34 = (this.rowStride * i32) + i8;
                        int i35 = i32 * 2;
                        int i36 = (i33 * 2) + i35;
                        dArr2[i34] = dArr3[i35];
                        dArr2[i34 + 1] = dArr3[i35 + 1];
                        dArr2[i34 + 2] = dArr3[i36];
                        dArr2[i34 + 3] = dArr3[i36 + 1];
                        i32++;
                    }
                } else if (i11 == 2) {
                    for (int i37 = 0; i37 < this.rows; i37++) {
                        int i38 = (this.rowStride * i37) + i8;
                        int i39 = i37 * 2;
                        dArr3[i39] = dArr2[i38];
                        dArr3[i39 + 1] = dArr2[i38 + 1];
                    }
                    this.fftRows.complexForward(dArr3, 0);
                    for (int i40 = 0; i40 < this.rows; i40++) {
                        int i41 = (this.rowStride * i40) + i8;
                        int i42 = i40 * 2;
                        dArr2[i41] = dArr3[i42];
                        dArr2[i41 + 1] = dArr3[i42 + 1];
                    }
                }
            }
            return;
        }
        for (int i43 = 0; i43 < this.slices; i43++) {
            int i44 = this.sliceStride * i43;
            if (i == 0) {
                for (int i45 = 0; i45 < this.rows; i45++) {
                    this.fftColumns.complexInverse(dArr2, (this.rowStride * i45) + i44, z2);
                }
            } else {
                for (int i46 = 0; i46 < this.rows; i46++) {
                    this.fftColumns.realInverse2(dArr2, (this.rowStride * i46) + i44, z2);
                }
            }
            int i47 = this.columns;
            if (i47 > 4) {
                for (int i48 = 0; i48 < this.columns; i48 += 8) {
                    int i49 = 0;
                    while (true) {
                        int i50 = this.rows;
                        if (i49 >= i50) {
                            break;
                        }
                        int i51 = (this.rowStride * i49) + i44 + i48;
                        int i52 = i49 * 2;
                        int i53 = (i50 * 2) + i52;
                        int i54 = (i50 * 2) + i53;
                        int i55 = (i50 * 2) + i54;
                        dArr3[i52] = dArr2[i51];
                        dArr3[i52 + 1] = dArr2[i51 + 1];
                        dArr3[i53] = dArr2[i51 + 2];
                        dArr3[i53 + 1] = dArr2[i51 + 3];
                        dArr3[i54] = dArr2[i51 + 4];
                        dArr3[i54 + 1] = dArr2[i51 + 5];
                        dArr3[i55] = dArr2[i51 + 6];
                        dArr3[i55 + 1] = dArr2[i51 + 7];
                        i49++;
                    }
                    this.fftRows.complexInverse(dArr3, 0, z2);
                    this.fftRows.complexInverse(dArr3, this.rows * 2, z2);
                    this.fftRows.complexInverse(dArr3, this.rows * 4, z2);
                    this.fftRows.complexInverse(dArr3, this.rows * 6, z2);
                    int i56 = 0;
                    while (true) {
                        int i57 = this.rows;
                        if (i56 >= i57) {
                            break;
                        }
                        int i58 = (this.rowStride * i56) + i44 + i48;
                        int i59 = i56 * 2;
                        int i60 = (i57 * 2) + i59;
                        int i61 = (i57 * 2) + i60;
                        int i62 = (i57 * 2) + i61;
                        dArr2[i58] = dArr3[i59];
                        dArr2[i58 + 1] = dArr3[i59 + 1];
                        dArr2[i58 + 2] = dArr3[i60];
                        dArr2[i58 + 3] = dArr3[i60 + 1];
                        dArr2[i58 + 4] = dArr3[i61];
                        dArr2[i58 + 5] = dArr3[i61 + 1];
                        dArr2[i58 + 6] = dArr3[i62];
                        dArr2[i58 + 7] = dArr3[i62 + 1];
                        i56++;
                    }
                }
            } else if (i47 == 4) {
                int i63 = 0;
                while (true) {
                    int i64 = this.rows;
                    if (i63 >= i64) {
                        break;
                    }
                    int i65 = (this.rowStride * i63) + i44;
                    int i66 = i63 * 2;
                    int i67 = (i64 * 2) + i66;
                    dArr3[i66] = dArr2[i65];
                    dArr3[i66 + 1] = dArr2[i65 + 1];
                    dArr3[i67] = dArr2[i65 + 2];
                    dArr3[i67 + 1] = dArr2[i65 + 3];
                    i63++;
                }
                this.fftRows.complexInverse(dArr3, 0, z2);
                this.fftRows.complexInverse(dArr3, this.rows * 2, z2);
                int i68 = 0;
                while (true) {
                    int i69 = this.rows;
                    if (i68 >= i69) {
                        break;
                    }
                    int i70 = (this.rowStride * i68) + i44;
                    int i71 = i68 * 2;
                    int i72 = (i69 * 2) + i71;
                    dArr2[i70] = dArr3[i71];
                    dArr2[i70 + 1] = dArr3[i71 + 1];
                    dArr2[i70 + 2] = dArr3[i72];
                    dArr2[i70 + 3] = dArr3[i72 + 1];
                    i68++;
                }
            } else if (i47 == 2) {
                for (int i73 = 0; i73 < this.rows; i73++) {
                    int i74 = (this.rowStride * i73) + i44;
                    int i75 = i73 * 2;
                    dArr3[i75] = dArr2[i74];
                    dArr3[i75 + 1] = dArr2[i74 + 1];
                }
                this.fftRows.complexInverse(dArr3, 0, z2);
                for (int i76 = 0; i76 < this.rows; i76++) {
                    int i77 = (this.rowStride * i76) + i44;
                    int i78 = i76 * 2;
                    dArr2[i77] = dArr3[i78];
                    dArr2[i77 + 1] = dArr3[i78 + 1];
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0297  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void xdft3da_sub2(long r36, int r38, pl.edu.icm.jlargearrays.DoubleLargeArray r39, boolean r40) {
        /*
            r35 = this;
            r0 = r35
            r1 = r39
            r2 = r40
            long r3 = r0.slicesl
            long r5 = r0.rowsl
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 >= 0) goto L_0x000f
            r3 = r5
        L_0x000f:
            r5 = 8
            long r3 = r3 * r5
            long r7 = r0.columnsl
            r9 = 4
            int r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r11 != 0) goto L_0x001d
            r7 = 1
        L_0x001b:
            long r3 = r3 >> r7
            goto L_0x0023
        L_0x001d:
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 >= 0) goto L_0x0023
            r7 = 2
            goto L_0x001b
        L_0x0023:
            pl.edu.icm.jlargearrays.DoubleLargeArray r7 = new pl.edu.icm.jlargearrays.DoubleLargeArray
            r7.<init>((long) r3)
            r3 = -1
            r15 = 6
            r17 = 3
            r5 = 0
            r19 = 2
            r21 = 1
            r4 = r38
            if (r4 != r3) goto L_0x0297
            r2 = r5
        L_0x0038:
            long r11 = r0.slicesl
            int r4 = (r2 > r11 ? 1 : (r2 == r11 ? 0 : -1))
            if (r4 >= 0) goto L_0x0518
            long r11 = r0.sliceStridel
            long r11 = r11 * r2
            int r4 = (r36 > r5 ? 1 : (r36 == r5 ? 0 : -1))
            if (r4 != 0) goto L_0x005a
            r23 = r5
        L_0x0047:
            long r5 = r0.rowsl
            int r4 = (r23 > r5 ? 1 : (r23 == r5 ? 0 : -1))
            if (r4 >= 0) goto L_0x006e
            org.jtransforms.fft.DoubleFFT_1D r4 = r0.fftColumns
            long r5 = r0.rowStridel
            long r5 = r5 * r23
            long r5 = r5 + r11
            r4.complexForward((pl.edu.icm.jlargearrays.DoubleLargeArray) r1, (long) r5)
            long r23 = r23 + r21
            goto L_0x0047
        L_0x005a:
            r4 = 0
        L_0x005c:
            long r13 = r0.rowsl
            int r6 = (r4 > r13 ? 1 : (r4 == r13 ? 0 : -1))
            if (r6 >= 0) goto L_0x006e
            org.jtransforms.fft.DoubleFFT_1D r6 = r0.fftColumns
            long r13 = r0.rowStridel
            long r13 = r13 * r4
            long r13 = r13 + r11
            r6.realForward((pl.edu.icm.jlargearrays.DoubleLargeArray) r1, (long) r13)
            long r4 = r4 + r21
            goto L_0x005c
        L_0x006e:
            long r4 = r0.columnsl
            int r6 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
            if (r6 <= 0) goto L_0x01ad
            r4 = 0
        L_0x0076:
            long r13 = r0.columnsl
            int r6 = (r4 > r13 ? 1 : (r4 == r13 ? 0 : -1))
            if (r6 >= 0) goto L_0x01a9
            r13 = 0
        L_0x007e:
            long r9 = r0.rowsl
            int r6 = (r13 > r9 ? 1 : (r13 == r9 ? 0 : -1))
            if (r6 >= 0) goto L_0x00fe
            r25 = r2
            long r2 = r0.rowStridel
            long r2 = r2 * r13
            long r2 = r2 + r11
            long r2 = r2 + r4
            r27 = r4
            long r4 = r13 * r19
            long r29 = r9 * r19
            r31 = r11
            long r11 = r29 + r4
            long r29 = r9 * r19
            r33 = r13
            long r13 = r11 + r29
            long r9 = r9 * r19
            long r9 = r9 + r13
            r29 = r9
            double r8 = r1.getDouble(r2)
            r7.setDouble(r4, r8)
            long r4 = r4 + r21
            long r8 = r2 + r21
            double r8 = r1.getDouble(r8)
            r7.setDouble(r4, r8)
            long r4 = r2 + r19
            double r4 = r1.getDouble(r4)
            r7.setDouble(r11, r4)
            long r11 = r11 + r21
            long r4 = r2 + r17
            double r4 = r1.getDouble(r4)
            r7.setDouble(r11, r4)
            r4 = 4
            long r9 = r2 + r4
            double r4 = r1.getDouble(r9)
            r7.setDouble(r13, r4)
            long r13 = r13 + r21
            r4 = 5
            long r8 = r2 + r4
            double r4 = r1.getDouble(r8)
            r7.setDouble(r13, r4)
            long r4 = r2 + r15
            double r4 = r1.getDouble(r4)
            r13 = r29
            r7.setDouble(r13, r4)
            long r9 = r13 + r21
            r4 = 7
            long r2 = r2 + r4
            double r2 = r1.getDouble(r2)
            r7.setDouble(r9, r2)
            long r13 = r33 + r21
            r2 = r25
            r4 = r27
            r11 = r31
            goto L_0x007e
        L_0x00fe:
            r25 = r2
            r27 = r4
            r31 = r11
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
        L_0x0128:
            long r4 = r0.rowsl
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 >= 0) goto L_0x019d
            long r8 = r0.rowStridel
            long r8 = r8 * r2
            long r11 = r31 + r8
            long r11 = r11 + r27
            long r8 = r2 * r19
            long r13 = r4 * r19
            long r13 = r13 + r8
            long r29 = r4 * r19
            r33 = r2
            long r2 = r13 + r29
            long r4 = r4 * r19
            long r4 = r4 + r2
            r29 = r4
            double r4 = r7.getDouble(r8)
            r1.setDouble(r11, r4)
            long r4 = r11 + r21
            long r8 = r8 + r21
            double r8 = r7.getDouble(r8)
            r1.setDouble(r4, r8)
            long r4 = r11 + r19
            double r8 = r7.getDouble(r13)
            r1.setDouble(r4, r8)
            long r4 = r11 + r17
            long r13 = r13 + r21
            double r8 = r7.getDouble(r13)
            r1.setDouble(r4, r8)
            r4 = 4
            long r9 = r11 + r4
            double r4 = r7.getDouble(r2)
            r1.setDouble(r9, r4)
            r4 = 5
            long r13 = r11 + r4
            long r2 = r2 + r21
            double r2 = r7.getDouble(r2)
            r1.setDouble(r13, r2)
            long r2 = r11 + r15
            r4 = r29
            double r8 = r7.getDouble(r4)
            r1.setDouble(r2, r8)
            r2 = 7
            long r11 = r11 + r2
            long r4 = r4 + r21
            double r2 = r7.getDouble(r4)
            r1.setDouble(r11, r2)
            long r2 = r33 + r21
            goto L_0x0128
        L_0x019d:
            r2 = 8
            long r4 = r27 + r2
            r2 = r25
            r11 = r31
            r9 = 4
            goto L_0x0076
        L_0x01a9:
            r25 = r2
            goto L_0x028f
        L_0x01ad:
            r25 = r2
            r2 = r9
            r31 = r11
            int r6 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r6 != 0) goto L_0x023c
            r2 = 0
        L_0x01b8:
            long r4 = r0.rowsl
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 >= 0) goto L_0x01f1
            long r8 = r0.rowStridel
            long r8 = r8 * r2
            long r11 = r31 + r8
            long r8 = r2 * r19
            long r4 = r4 * r19
            long r4 = r4 + r8
            double r13 = r1.getDouble(r11)
            r7.setDouble(r8, r13)
            long r8 = r8 + r21
            long r13 = r11 + r21
            double r13 = r1.getDouble(r13)
            r7.setDouble(r8, r13)
            long r8 = r11 + r19
            double r8 = r1.getDouble(r8)
            r7.setDouble(r4, r8)
            long r4 = r4 + r21
            long r11 = r11 + r17
            double r8 = r1.getDouble(r11)
            r7.setDouble(r4, r8)
            long r2 = r2 + r21
            goto L_0x01b8
        L_0x01f1:
            org.jtransforms.fft.DoubleFFT_1D r2 = r0.fftRows
            r3 = 0
            r2.complexForward((pl.edu.icm.jlargearrays.DoubleLargeArray) r7, (long) r3)
            org.jtransforms.fft.DoubleFFT_1D r2 = r0.fftRows
            long r3 = r0.rowsl
            long r3 = r3 * r19
            r2.complexForward((pl.edu.icm.jlargearrays.DoubleLargeArray) r7, (long) r3)
            r2 = 0
        L_0x0203:
            long r4 = r0.rowsl
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 >= 0) goto L_0x028f
            long r8 = r0.rowStridel
            long r8 = r8 * r2
            long r11 = r31 + r8
            long r8 = r2 * r19
            long r4 = r4 * r19
            long r4 = r4 + r8
            double r13 = r7.getDouble(r8)
            r1.setDouble(r11, r13)
            long r13 = r11 + r21
            long r8 = r8 + r21
            double r8 = r7.getDouble(r8)
            r1.setDouble(r13, r8)
            long r8 = r11 + r19
            double r13 = r7.getDouble(r4)
            r1.setDouble(r8, r13)
            long r11 = r11 + r17
            long r4 = r4 + r21
            double r4 = r7.getDouble(r4)
            r1.setDouble(r11, r4)
            long r2 = r2 + r21
            goto L_0x0203
        L_0x023c:
            int r2 = (r4 > r19 ? 1 : (r4 == r19 ? 0 : -1))
            if (r2 != 0) goto L_0x028f
            r2 = 0
        L_0x0242:
            long r4 = r0.rowsl
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 >= 0) goto L_0x0264
            long r4 = r0.rowStridel
            long r4 = r4 * r2
            long r11 = r31 + r4
            long r4 = r2 * r19
            double r8 = r1.getDouble(r11)
            r7.setDouble(r4, r8)
            long r4 = r4 + r21
            long r11 = r11 + r21
            double r8 = r1.getDouble(r11)
            r7.setDouble(r4, r8)
            long r2 = r2 + r21
            goto L_0x0242
        L_0x0264:
            org.jtransforms.fft.DoubleFFT_1D r2 = r0.fftRows
            r3 = 0
            r2.complexForward((pl.edu.icm.jlargearrays.DoubleLargeArray) r7, (long) r3)
            r2 = 0
        L_0x026d:
            long r4 = r0.rowsl
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 >= 0) goto L_0x028f
            long r4 = r0.rowStridel
            long r4 = r4 * r2
            long r11 = r31 + r4
            long r4 = r2 * r19
            double r8 = r7.getDouble(r4)
            r1.setDouble(r11, r8)
            long r11 = r11 + r21
            long r4 = r4 + r21
            double r4 = r7.getDouble(r4)
            r1.setDouble(r11, r4)
            long r2 = r2 + r21
            goto L_0x026d
        L_0x028f:
            long r2 = r25 + r21
            r5 = 0
            r9 = 4
            goto L_0x0038
        L_0x0297:
            r3 = 0
        L_0x0299:
            long r5 = r0.slicesl
            int r5 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r5 >= 0) goto L_0x0518
            long r5 = r0.sliceStridel
            long r5 = r5 * r3
            r8 = 0
            int r10 = (r36 > r8 ? 1 : (r36 == r8 ? 0 : -1))
            if (r10 != 0) goto L_0x02bc
            r8 = 0
        L_0x02aa:
            long r10 = r0.rowsl
            int r10 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r10 >= 0) goto L_0x02d0
            org.jtransforms.fft.DoubleFFT_1D r10 = r0.fftColumns
            long r11 = r0.rowStridel
            long r11 = r11 * r8
            long r11 = r11 + r5
            r10.complexInverse((pl.edu.icm.jlargearrays.DoubleLargeArray) r1, (long) r11, (boolean) r2)
            long r8 = r8 + r21
            goto L_0x02aa
        L_0x02bc:
            r8 = 0
        L_0x02be:
            long r10 = r0.rowsl
            int r10 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r10 >= 0) goto L_0x02d0
            org.jtransforms.fft.DoubleFFT_1D r10 = r0.fftColumns
            long r11 = r0.rowStridel
            long r11 = r11 * r8
            long r11 = r11 + r5
            r10.realInverse2((pl.edu.icm.jlargearrays.DoubleLargeArray) r1, (long) r11, (boolean) r2)
            long r8 = r8 + r21
            goto L_0x02be
        L_0x02d0:
            long r8 = r0.columnsl
            r10 = 4
            int r12 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r12 <= 0) goto L_0x041a
            r8 = 0
        L_0x02da:
            long r10 = r0.columnsl
            int r10 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r10 >= 0) goto L_0x0414
            r10 = 0
        L_0x02e2:
            long r12 = r0.rowsl
            int r14 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r14 >= 0) goto L_0x0362
            r25 = r3
            long r3 = r0.rowStridel
            long r3 = r3 * r10
            long r3 = r3 + r5
            long r3 = r3 + r8
            r27 = r8
            long r8 = r10 * r19
            long r29 = r12 * r19
            r31 = r5
            long r5 = r29 + r8
            long r29 = r12 * r19
            r33 = r10
            long r10 = r5 + r29
            long r12 = r12 * r19
            long r12 = r12 + r10
            r29 = r12
            double r12 = r1.getDouble(r3)
            r7.setDouble(r8, r12)
            long r8 = r8 + r21
            long r12 = r3 + r21
            double r12 = r1.getDouble(r12)
            r7.setDouble(r8, r12)
            long r8 = r3 + r19
            double r8 = r1.getDouble(r8)
            r7.setDouble(r5, r8)
            long r5 = r5 + r21
            long r8 = r3 + r17
            double r8 = r1.getDouble(r8)
            r7.setDouble(r5, r8)
            r5 = 4
            long r8 = r3 + r5
            double r5 = r1.getDouble(r8)
            r7.setDouble(r10, r5)
            long r10 = r10 + r21
            r5 = 5
            long r13 = r3 + r5
            double r5 = r1.getDouble(r13)
            r7.setDouble(r10, r5)
            long r5 = r3 + r15
            double r5 = r1.getDouble(r5)
            r10 = r29
            r7.setDouble(r10, r5)
            long r12 = r10 + r21
            r5 = 7
            long r3 = r3 + r5
            double r3 = r1.getDouble(r3)
            r7.setDouble(r12, r3)
            long r10 = r33 + r21
            r3 = r25
            r8 = r27
            r5 = r31
            goto L_0x02e2
        L_0x0362:
            r25 = r3
            r31 = r5
            r27 = r8
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
        L_0x038c:
            long r5 = r0.rowsl
            int r8 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r8 >= 0) goto L_0x0404
            long r8 = r0.rowStridel
            long r8 = r8 * r3
            long r8 = r31 + r8
            long r8 = r8 + r27
            long r10 = r3 * r19
            long r12 = r5 * r19
            long r12 = r12 + r10
            long r29 = r5 * r19
            r33 = r3
            long r2 = r12 + r29
            long r5 = r5 * r19
            long r5 = r5 + r2
            r29 = r5
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
            r10 = r29
            double r12 = r7.getDouble(r10)
            r1.setDouble(r2, r12)
            r2 = 7
            long r8 = r8 + r2
            long r10 = r10 + r21
            double r10 = r7.getDouble(r10)
            r1.setDouble(r8, r10)
            long r8 = r33 + r21
            r2 = r40
            r3 = r8
            goto L_0x038c
        L_0x0404:
            r2 = 7
            r4 = 5
            r10 = 8
            long r8 = r27 + r10
            r2 = r40
            r3 = r25
            r5 = r31
            goto L_0x02da
        L_0x0414:
            r25 = r3
            r3 = r40
            goto L_0x050e
        L_0x041a:
            r25 = r3
            r31 = r5
            r2 = 7
            r4 = 5
            r10 = 8
            r12 = 4
            int r6 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
            if (r6 != 0) goto L_0x04ba
            r8 = 0
        L_0x042c:
            long r2 = r0.rowsl
            int r6 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r6 >= 0) goto L_0x046b
            long r4 = r0.rowStridel
            long r4 = r4 * r8
            long r5 = r31 + r4
            long r10 = r8 * r19
            long r2 = r2 * r19
            long r2 = r2 + r10
            double r12 = r1.getDouble(r5)
            r7.setDouble(r10, r12)
            long r10 = r10 + r21
            long r12 = r5 + r21
            double r12 = r1.getDouble(r12)
            r7.setDouble(r10, r12)
            long r10 = r5 + r19
            double r10 = r1.getDouble(r10)
            r7.setDouble(r2, r10)
            long r2 = r2 + r21
            long r5 = r5 + r17
            double r4 = r1.getDouble(r5)
            r7.setDouble(r2, r4)
            long r8 = r8 + r21
            r4 = 5
            r10 = 8
            r12 = 4
            goto L_0x042c
        L_0x046b:
            org.jtransforms.fft.DoubleFFT_1D r2 = r0.fftRows
            r3 = r40
            r4 = 0
            r2.complexInverse((pl.edu.icm.jlargearrays.DoubleLargeArray) r7, (long) r4, (boolean) r3)
            org.jtransforms.fft.DoubleFFT_1D r2 = r0.fftRows
            long r4 = r0.rowsl
            long r4 = r4 * r19
            r2.complexInverse((pl.edu.icm.jlargearrays.DoubleLargeArray) r7, (long) r4, (boolean) r3)
            r4 = 0
        L_0x047f:
            long r8 = r0.rowsl
            int r2 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r2 >= 0) goto L_0x050e
            long r10 = r0.rowStridel
            long r10 = r10 * r4
            long r10 = r31 + r10
            long r12 = r4 * r19
            long r8 = r8 * r19
            long r8 = r8 + r12
            double r14 = r7.getDouble(r12)
            r1.setDouble(r10, r14)
            long r14 = r10 + r21
            long r12 = r12 + r21
            double r12 = r7.getDouble(r12)
            r1.setDouble(r14, r12)
            long r12 = r10 + r19
            double r14 = r7.getDouble(r8)
            r1.setDouble(r12, r14)
            long r10 = r10 + r17
            long r8 = r8 + r21
            double r8 = r7.getDouble(r8)
            r1.setDouble(r10, r8)
            long r4 = r4 + r21
            r15 = 6
            goto L_0x047f
        L_0x04ba:
            r3 = r40
            int r2 = (r8 > r19 ? 1 : (r8 == r19 ? 0 : -1))
            if (r2 != 0) goto L_0x050e
            r4 = 0
        L_0x04c2:
            long r8 = r0.rowsl
            int r2 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r2 >= 0) goto L_0x04e4
            long r8 = r0.rowStridel
            long r8 = r8 * r4
            long r8 = r31 + r8
            long r10 = r4 * r19
            double r12 = r1.getDouble(r8)
            r7.setDouble(r10, r12)
            long r10 = r10 + r21
            long r8 = r8 + r21
            double r8 = r1.getDouble(r8)
            r7.setDouble(r10, r8)
            long r4 = r4 + r21
            goto L_0x04c2
        L_0x04e4:
            org.jtransforms.fft.DoubleFFT_1D r2 = r0.fftRows
            r4 = 0
            r2.complexInverse((pl.edu.icm.jlargearrays.DoubleLargeArray) r7, (long) r4, (boolean) r3)
            r8 = r4
        L_0x04ec:
            long r10 = r0.rowsl
            int r2 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r2 >= 0) goto L_0x0510
            long r10 = r0.rowStridel
            long r10 = r10 * r8
            long r10 = r31 + r10
            long r12 = r8 * r19
            double r14 = r7.getDouble(r12)
            r1.setDouble(r10, r14)
            long r10 = r10 + r21
            long r12 = r12 + r21
            double r12 = r7.getDouble(r12)
            r1.setDouble(r10, r12)
            long r8 = r8 + r21
            goto L_0x04ec
        L_0x050e:
            r4 = 0
        L_0x0510:
            long r8 = r25 + r21
            r2 = r3
            r3 = r8
            r15 = 6
            goto L_0x0299
        L_0x0518:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.DoubleFFT_3D.xdft3da_sub2(long, int, pl.edu.icm.jlargearrays.DoubleLargeArray, boolean):void");
    }

    private void xdft3da_sub1(int i, int i2, double[][][] dArr, boolean z) {
        boolean z2 = z;
        int i3 = this.slices;
        int i4 = this.rows;
        if (i3 < i4) {
            i3 = i4;
        }
        int i5 = i3 * 8;
        int i6 = this.columns;
        if (i6 == 4) {
            i5 >>= 1;
        } else if (i6 < 4) {
            i5 >>= 2;
        }
        double[] dArr2 = new double[i5];
        if (i2 == -1) {
            for (int i7 = 0; i7 < this.slices; i7++) {
                if (i == 0) {
                    for (int i8 = 0; i8 < this.rows; i8++) {
                        this.fftColumns.complexForward(dArr[i7][i8]);
                    }
                } else {
                    for (int i9 = 0; i9 < this.rows; i9++) {
                        this.fftColumns.realForward(dArr[i7][i9], 0);
                    }
                }
                int i10 = this.columns;
                if (i10 > 4) {
                    for (int i11 = 0; i11 < this.columns; i11 += 8) {
                        int i12 = 0;
                        while (true) {
                            int i13 = this.rows;
                            if (i12 >= i13) {
                                break;
                            }
                            int i14 = i12 * 2;
                            int i15 = (i13 * 2) + i14;
                            int i16 = (i13 * 2) + i15;
                            int i17 = (i13 * 2) + i16;
                            double[] dArr3 = dArr[i7][i12];
                            dArr2[i14] = dArr3[i11];
                            dArr2[i14 + 1] = dArr3[i11 + 1];
                            dArr2[i15] = dArr3[i11 + 2];
                            dArr2[i15 + 1] = dArr3[i11 + 3];
                            dArr2[i16] = dArr3[i11 + 4];
                            dArr2[i16 + 1] = dArr3[i11 + 5];
                            dArr2[i17] = dArr3[i11 + 6];
                            dArr2[i17 + 1] = dArr3[i11 + 7];
                            i12++;
                        }
                        this.fftRows.complexForward(dArr2, 0);
                        this.fftRows.complexForward(dArr2, this.rows * 2);
                        this.fftRows.complexForward(dArr2, this.rows * 4);
                        this.fftRows.complexForward(dArr2, this.rows * 6);
                        int i18 = 0;
                        while (true) {
                            int i19 = this.rows;
                            if (i18 >= i19) {
                                break;
                            }
                            int i20 = i18 * 2;
                            int i21 = (i19 * 2) + i20;
                            int i22 = (i19 * 2) + i21;
                            int i23 = (i19 * 2) + i22;
                            double[] dArr4 = dArr[i7][i18];
                            dArr4[i11] = dArr2[i20];
                            dArr4[i11 + 1] = dArr2[i20 + 1];
                            dArr4[i11 + 2] = dArr2[i21];
                            dArr4[i11 + 3] = dArr2[i21 + 1];
                            dArr4[i11 + 4] = dArr2[i22];
                            dArr4[i11 + 5] = dArr2[i22 + 1];
                            dArr4[i11 + 6] = dArr2[i23];
                            dArr4[i11 + 7] = dArr2[i23 + 1];
                            i18++;
                        }
                    }
                } else if (i10 == 4) {
                    int i24 = 0;
                    while (true) {
                        int i25 = this.rows;
                        if (i24 >= i25) {
                            break;
                        }
                        int i26 = i24 * 2;
                        int i27 = (i25 * 2) + i26;
                        double[] dArr5 = dArr[i7][i24];
                        dArr2[i26] = dArr5[0];
                        dArr2[i26 + 1] = dArr5[1];
                        dArr2[i27] = dArr5[2];
                        dArr2[i27 + 1] = dArr5[3];
                        i24++;
                    }
                    this.fftRows.complexForward(dArr2, 0);
                    this.fftRows.complexForward(dArr2, this.rows * 2);
                    int i28 = 0;
                    while (true) {
                        int i29 = this.rows;
                        if (i28 >= i29) {
                            break;
                        }
                        int i30 = i28 * 2;
                        int i31 = (i29 * 2) + i30;
                        double[] dArr6 = dArr[i7][i28];
                        dArr6[0] = dArr2[i30];
                        dArr6[1] = dArr2[i30 + 1];
                        dArr6[2] = dArr2[i31];
                        dArr6[3] = dArr2[i31 + 1];
                        i28++;
                    }
                } else if (i10 == 2) {
                    for (int i32 = 0; i32 < this.rows; i32++) {
                        int i33 = i32 * 2;
                        double[] dArr7 = dArr[i7][i32];
                        dArr2[i33] = dArr7[0];
                        dArr2[i33 + 1] = dArr7[1];
                    }
                    this.fftRows.complexForward(dArr2, 0);
                    for (int i34 = 0; i34 < this.rows; i34++) {
                        int i35 = i34 * 2;
                        double[] dArr8 = dArr[i7][i34];
                        dArr8[0] = dArr2[i35];
                        dArr8[1] = dArr2[i35 + 1];
                    }
                }
            }
            return;
        }
        for (int i36 = 0; i36 < this.slices; i36++) {
            if (i == 0) {
                for (int i37 = 0; i37 < this.rows; i37++) {
                    this.fftColumns.complexInverse(dArr[i36][i37], z2);
                }
            }
            int i38 = this.columns;
            if (i38 > 4) {
                for (int i39 = 0; i39 < this.columns; i39 += 8) {
                    int i40 = 0;
                    while (true) {
                        int i41 = this.rows;
                        if (i40 >= i41) {
                            break;
                        }
                        int i42 = i40 * 2;
                        int i43 = (i41 * 2) + i42;
                        int i44 = (i41 * 2) + i43;
                        int i45 = (i41 * 2) + i44;
                        double[] dArr9 = dArr[i36][i40];
                        dArr2[i42] = dArr9[i39];
                        dArr2[i42 + 1] = dArr9[i39 + 1];
                        dArr2[i43] = dArr9[i39 + 2];
                        dArr2[i43 + 1] = dArr9[i39 + 3];
                        dArr2[i44] = dArr9[i39 + 4];
                        dArr2[i44 + 1] = dArr9[i39 + 5];
                        dArr2[i45] = dArr9[i39 + 6];
                        dArr2[i45 + 1] = dArr9[i39 + 7];
                        i40++;
                    }
                    this.fftRows.complexInverse(dArr2, 0, z2);
                    this.fftRows.complexInverse(dArr2, this.rows * 2, z2);
                    this.fftRows.complexInverse(dArr2, this.rows * 4, z2);
                    this.fftRows.complexInverse(dArr2, this.rows * 6, z2);
                    int i46 = 0;
                    while (true) {
                        int i47 = this.rows;
                        if (i46 >= i47) {
                            break;
                        }
                        int i48 = i46 * 2;
                        int i49 = (i47 * 2) + i48;
                        int i50 = (i47 * 2) + i49;
                        int i51 = (i47 * 2) + i50;
                        double[] dArr10 = dArr[i36][i46];
                        dArr10[i39] = dArr2[i48];
                        dArr10[i39 + 1] = dArr2[i48 + 1];
                        dArr10[i39 + 2] = dArr2[i49];
                        dArr10[i39 + 3] = dArr2[i49 + 1];
                        dArr10[i39 + 4] = dArr2[i50];
                        dArr10[i39 + 5] = dArr2[i50 + 1];
                        dArr10[i39 + 6] = dArr2[i51];
                        dArr10[i39 + 7] = dArr2[i51 + 1];
                        i46++;
                    }
                }
            } else if (i38 == 4) {
                int i52 = 0;
                while (true) {
                    int i53 = this.rows;
                    if (i52 >= i53) {
                        break;
                    }
                    int i54 = i52 * 2;
                    int i55 = (i53 * 2) + i54;
                    double[] dArr11 = dArr[i36][i52];
                    dArr2[i54] = dArr11[0];
                    dArr2[i54 + 1] = dArr11[1];
                    dArr2[i55] = dArr11[2];
                    dArr2[i55 + 1] = dArr11[3];
                    i52++;
                }
                this.fftRows.complexInverse(dArr2, 0, z2);
                this.fftRows.complexInverse(dArr2, this.rows * 2, z2);
                int i56 = 0;
                while (true) {
                    int i57 = this.rows;
                    if (i56 >= i57) {
                        break;
                    }
                    int i58 = i56 * 2;
                    int i59 = (i57 * 2) + i58;
                    double[] dArr12 = dArr[i36][i56];
                    dArr12[0] = dArr2[i58];
                    dArr12[1] = dArr2[i58 + 1];
                    dArr12[2] = dArr2[i59];
                    dArr12[3] = dArr2[i59 + 1];
                    i56++;
                }
            } else if (i38 == 2) {
                for (int i60 = 0; i60 < this.rows; i60++) {
                    int i61 = i60 * 2;
                    double[] dArr13 = dArr[i36][i60];
                    dArr2[i61] = dArr13[0];
                    dArr2[i61 + 1] = dArr13[1];
                }
                this.fftRows.complexInverse(dArr2, 0, z2);
                for (int i62 = 0; i62 < this.rows; i62++) {
                    int i63 = i62 * 2;
                    double[] dArr14 = dArr[i36][i62];
                    dArr14[0] = dArr2[i63];
                    dArr14[1] = dArr2[i63 + 1];
                }
            }
            if (i != 0) {
                for (int i64 = 0; i64 < this.rows; i64++) {
                    this.fftColumns.realInverse(dArr[i36][i64], z2);
                }
            }
        }
    }

    private void xdft3da_sub2(int i, int i2, double[][][] dArr, boolean z) {
        boolean z2 = z;
        int i3 = this.slices;
        int i4 = this.rows;
        if (i3 < i4) {
            i3 = i4;
        }
        int i5 = i3 * 8;
        int i6 = this.columns;
        if (i6 == 4) {
            i5 >>= 1;
        } else if (i6 < 4) {
            i5 >>= 2;
        }
        double[] dArr2 = new double[i5];
        if (i2 == -1) {
            for (int i7 = 0; i7 < this.slices; i7++) {
                if (i == 0) {
                    for (int i8 = 0; i8 < this.rows; i8++) {
                        this.fftColumns.complexForward(dArr[i7][i8]);
                    }
                } else {
                    for (int i9 = 0; i9 < this.rows; i9++) {
                        this.fftColumns.realForward(dArr[i7][i9]);
                    }
                }
                int i10 = this.columns;
                if (i10 > 4) {
                    for (int i11 = 0; i11 < this.columns; i11 += 8) {
                        int i12 = 0;
                        while (true) {
                            int i13 = this.rows;
                            if (i12 >= i13) {
                                break;
                            }
                            int i14 = i12 * 2;
                            int i15 = (i13 * 2) + i14;
                            int i16 = (i13 * 2) + i15;
                            int i17 = (i13 * 2) + i16;
                            double[] dArr3 = dArr[i7][i12];
                            dArr2[i14] = dArr3[i11];
                            dArr2[i14 + 1] = dArr3[i11 + 1];
                            dArr2[i15] = dArr3[i11 + 2];
                            dArr2[i15 + 1] = dArr3[i11 + 3];
                            dArr2[i16] = dArr3[i11 + 4];
                            dArr2[i16 + 1] = dArr3[i11 + 5];
                            dArr2[i17] = dArr3[i11 + 6];
                            dArr2[i17 + 1] = dArr3[i11 + 7];
                            i12++;
                        }
                        this.fftRows.complexForward(dArr2, 0);
                        this.fftRows.complexForward(dArr2, this.rows * 2);
                        this.fftRows.complexForward(dArr2, this.rows * 4);
                        this.fftRows.complexForward(dArr2, this.rows * 6);
                        int i18 = 0;
                        while (true) {
                            int i19 = this.rows;
                            if (i18 >= i19) {
                                break;
                            }
                            int i20 = i18 * 2;
                            int i21 = (i19 * 2) + i20;
                            int i22 = (i19 * 2) + i21;
                            int i23 = (i19 * 2) + i22;
                            double[] dArr4 = dArr[i7][i18];
                            dArr4[i11] = dArr2[i20];
                            dArr4[i11 + 1] = dArr2[i20 + 1];
                            dArr4[i11 + 2] = dArr2[i21];
                            dArr4[i11 + 3] = dArr2[i21 + 1];
                            dArr4[i11 + 4] = dArr2[i22];
                            dArr4[i11 + 5] = dArr2[i22 + 1];
                            dArr4[i11 + 6] = dArr2[i23];
                            dArr4[i11 + 7] = dArr2[i23 + 1];
                            i18++;
                        }
                    }
                } else if (i10 == 4) {
                    int i24 = 0;
                    while (true) {
                        int i25 = this.rows;
                        if (i24 >= i25) {
                            break;
                        }
                        int i26 = i24 * 2;
                        int i27 = (i25 * 2) + i26;
                        double[] dArr5 = dArr[i7][i24];
                        dArr2[i26] = dArr5[0];
                        dArr2[i26 + 1] = dArr5[1];
                        dArr2[i27] = dArr5[2];
                        dArr2[i27 + 1] = dArr5[3];
                        i24++;
                    }
                    this.fftRows.complexForward(dArr2, 0);
                    this.fftRows.complexForward(dArr2, this.rows * 2);
                    int i28 = 0;
                    while (true) {
                        int i29 = this.rows;
                        if (i28 >= i29) {
                            break;
                        }
                        int i30 = i28 * 2;
                        int i31 = (i29 * 2) + i30;
                        double[] dArr6 = dArr[i7][i28];
                        dArr6[0] = dArr2[i30];
                        dArr6[1] = dArr2[i30 + 1];
                        dArr6[2] = dArr2[i31];
                        dArr6[3] = dArr2[i31 + 1];
                        i28++;
                    }
                } else if (i10 == 2) {
                    for (int i32 = 0; i32 < this.rows; i32++) {
                        int i33 = i32 * 2;
                        double[] dArr7 = dArr[i7][i32];
                        dArr2[i33] = dArr7[0];
                        dArr2[i33 + 1] = dArr7[1];
                    }
                    this.fftRows.complexForward(dArr2, 0);
                    for (int i34 = 0; i34 < this.rows; i34++) {
                        int i35 = i34 * 2;
                        double[] dArr8 = dArr[i7][i34];
                        dArr8[0] = dArr2[i35];
                        dArr8[1] = dArr2[i35 + 1];
                    }
                }
            }
            return;
        }
        for (int i36 = 0; i36 < this.slices; i36++) {
            if (i == 0) {
                for (int i37 = 0; i37 < this.rows; i37++) {
                    this.fftColumns.complexInverse(dArr[i36][i37], z2);
                }
            } else {
                for (int i38 = 0; i38 < this.rows; i38++) {
                    this.fftColumns.realInverse2(dArr[i36][i38], 0, z2);
                }
            }
            int i39 = this.columns;
            if (i39 > 4) {
                for (int i40 = 0; i40 < this.columns; i40 += 8) {
                    int i41 = 0;
                    while (true) {
                        int i42 = this.rows;
                        if (i41 >= i42) {
                            break;
                        }
                        int i43 = i41 * 2;
                        int i44 = (i42 * 2) + i43;
                        int i45 = (i42 * 2) + i44;
                        int i46 = (i42 * 2) + i45;
                        double[] dArr9 = dArr[i36][i41];
                        dArr2[i43] = dArr9[i40];
                        dArr2[i43 + 1] = dArr9[i40 + 1];
                        dArr2[i44] = dArr9[i40 + 2];
                        dArr2[i44 + 1] = dArr9[i40 + 3];
                        dArr2[i45] = dArr9[i40 + 4];
                        dArr2[i45 + 1] = dArr9[i40 + 5];
                        dArr2[i46] = dArr9[i40 + 6];
                        dArr2[i46 + 1] = dArr9[i40 + 7];
                        i41++;
                    }
                    this.fftRows.complexInverse(dArr2, 0, z2);
                    this.fftRows.complexInverse(dArr2, this.rows * 2, z2);
                    this.fftRows.complexInverse(dArr2, this.rows * 4, z2);
                    this.fftRows.complexInverse(dArr2, this.rows * 6, z2);
                    int i47 = 0;
                    while (true) {
                        int i48 = this.rows;
                        if (i47 >= i48) {
                            break;
                        }
                        int i49 = i47 * 2;
                        int i50 = (i48 * 2) + i49;
                        int i51 = (i48 * 2) + i50;
                        int i52 = (i48 * 2) + i51;
                        double[] dArr10 = dArr[i36][i47];
                        dArr10[i40] = dArr2[i49];
                        dArr10[i40 + 1] = dArr2[i49 + 1];
                        dArr10[i40 + 2] = dArr2[i50];
                        dArr10[i40 + 3] = dArr2[i50 + 1];
                        dArr10[i40 + 4] = dArr2[i51];
                        dArr10[i40 + 5] = dArr2[i51 + 1];
                        dArr10[i40 + 6] = dArr2[i52];
                        dArr10[i40 + 7] = dArr2[i52 + 1];
                        i47++;
                    }
                }
            } else if (i39 == 4) {
                int i53 = 0;
                while (true) {
                    int i54 = this.rows;
                    if (i53 >= i54) {
                        break;
                    }
                    int i55 = i53 * 2;
                    int i56 = (i54 * 2) + i55;
                    double[] dArr11 = dArr[i36][i53];
                    dArr2[i55] = dArr11[0];
                    dArr2[i55 + 1] = dArr11[1];
                    dArr2[i56] = dArr11[2];
                    dArr2[i56 + 1] = dArr11[3];
                    i53++;
                }
                this.fftRows.complexInverse(dArr2, 0, z2);
                this.fftRows.complexInverse(dArr2, this.rows * 2, z2);
                int i57 = 0;
                while (true) {
                    int i58 = this.rows;
                    if (i57 >= i58) {
                        break;
                    }
                    int i59 = i57 * 2;
                    int i60 = (i58 * 2) + i59;
                    double[] dArr12 = dArr[i36][i57];
                    dArr12[0] = dArr2[i59];
                    dArr12[1] = dArr2[i59 + 1];
                    dArr12[2] = dArr2[i60];
                    dArr12[3] = dArr2[i60 + 1];
                    i57++;
                }
            } else if (i39 == 2) {
                for (int i61 = 0; i61 < this.rows; i61++) {
                    int i62 = i61 * 2;
                    double[] dArr13 = dArr[i36][i61];
                    dArr2[i62] = dArr13[0];
                    dArr2[i62 + 1] = dArr13[1];
                }
                this.fftRows.complexInverse(dArr2, 0, z2);
                for (int i63 = 0; i63 < this.rows; i63++) {
                    int i64 = i63 * 2;
                    double[] dArr14 = dArr[i36][i63];
                    dArr14[0] = dArr2[i64];
                    dArr14[1] = dArr2[i64 + 1];
                }
            }
        }
    }

    private void cdft3db_sub(int i, double[] dArr, boolean z) {
        boolean z2 = z;
        int i2 = this.slices;
        int i3 = this.rows;
        if (i2 < i3) {
            i2 = i3;
        }
        int i4 = i2 * 8;
        int i5 = this.columns;
        if (i5 == 4) {
            i4 >>= 1;
        } else if (i5 < 4) {
            i4 >>= 2;
        }
        double[] dArr2 = new double[i4];
        if (i == -1) {
            if (i5 > 4) {
                for (int i6 = 0; i6 < this.rows; i6++) {
                    int i7 = this.rowStride * i6;
                    for (int i8 = 0; i8 < this.columns; i8 += 8) {
                        int i9 = 0;
                        while (true) {
                            int i10 = this.slices;
                            if (i9 >= i10) {
                                break;
                            }
                            int i11 = (this.sliceStride * i9) + i7 + i8;
                            int i12 = i9 * 2;
                            int i13 = (i10 * 2) + i12;
                            int i14 = (i10 * 2) + i13;
                            int i15 = (i10 * 2) + i14;
                            dArr2[i12] = dArr[i11];
                            dArr2[i12 + 1] = dArr[i11 + 1];
                            dArr2[i13] = dArr[i11 + 2];
                            dArr2[i13 + 1] = dArr[i11 + 3];
                            dArr2[i14] = dArr[i11 + 4];
                            dArr2[i14 + 1] = dArr[i11 + 5];
                            dArr2[i15] = dArr[i11 + 6];
                            dArr2[i15 + 1] = dArr[i11 + 7];
                            i9++;
                        }
                        this.fftSlices.complexForward(dArr2, 0);
                        this.fftSlices.complexForward(dArr2, this.slices * 2);
                        this.fftSlices.complexForward(dArr2, this.slices * 4);
                        this.fftSlices.complexForward(dArr2, this.slices * 6);
                        int i16 = 0;
                        while (true) {
                            int i17 = this.slices;
                            if (i16 >= i17) {
                                break;
                            }
                            int i18 = (this.sliceStride * i16) + i7 + i8;
                            int i19 = i16 * 2;
                            int i20 = (i17 * 2) + i19;
                            int i21 = (i17 * 2) + i20;
                            int i22 = (i17 * 2) + i21;
                            dArr[i18] = dArr2[i19];
                            dArr[i18 + 1] = dArr2[i19 + 1];
                            dArr[i18 + 2] = dArr2[i20];
                            dArr[i18 + 3] = dArr2[i20 + 1];
                            dArr[i18 + 4] = dArr2[i21];
                            dArr[i18 + 5] = dArr2[i21 + 1];
                            dArr[i18 + 6] = dArr2[i22];
                            dArr[i18 + 7] = dArr2[i22 + 1];
                            i16++;
                        }
                    }
                }
            } else if (i5 == 4) {
                for (int i23 = 0; i23 < this.rows; i23++) {
                    int i24 = this.rowStride * i23;
                    int i25 = 0;
                    while (true) {
                        int i26 = this.slices;
                        if (i25 >= i26) {
                            break;
                        }
                        int i27 = (this.sliceStride * i25) + i24;
                        int i28 = i25 * 2;
                        int i29 = (i26 * 2) + i28;
                        dArr2[i28] = dArr[i27];
                        dArr2[i28 + 1] = dArr[i27 + 1];
                        dArr2[i29] = dArr[i27 + 2];
                        dArr2[i29 + 1] = dArr[i27 + 3];
                        i25++;
                    }
                    this.fftSlices.complexForward(dArr2, 0);
                    this.fftSlices.complexForward(dArr2, this.slices * 2);
                    int i30 = 0;
                    while (true) {
                        int i31 = this.slices;
                        if (i30 >= i31) {
                            break;
                        }
                        int i32 = (this.sliceStride * i30) + i24;
                        int i33 = i30 * 2;
                        int i34 = (i31 * 2) + i33;
                        dArr[i32] = dArr2[i33];
                        dArr[i32 + 1] = dArr2[i33 + 1];
                        dArr[i32 + 2] = dArr2[i34];
                        dArr[i32 + 3] = dArr2[i34 + 1];
                        i30++;
                    }
                }
            } else if (i5 == 2) {
                for (int i35 = 0; i35 < this.rows; i35++) {
                    int i36 = this.rowStride * i35;
                    for (int i37 = 0; i37 < this.slices; i37++) {
                        int i38 = (this.sliceStride * i37) + i36;
                        int i39 = i37 * 2;
                        dArr2[i39] = dArr[i38];
                        dArr2[i39 + 1] = dArr[i38 + 1];
                    }
                    this.fftSlices.complexForward(dArr2, 0);
                    for (int i40 = 0; i40 < this.slices; i40++) {
                        int i41 = (this.sliceStride * i40) + i36;
                        int i42 = i40 * 2;
                        dArr[i41] = dArr2[i42];
                        dArr[i41 + 1] = dArr2[i42 + 1];
                    }
                }
            }
        } else if (i5 > 4) {
            for (int i43 = 0; i43 < this.rows; i43++) {
                int i44 = this.rowStride * i43;
                for (int i45 = 0; i45 < this.columns; i45 += 8) {
                    int i46 = 0;
                    while (true) {
                        int i47 = this.slices;
                        if (i46 >= i47) {
                            break;
                        }
                        int i48 = (this.sliceStride * i46) + i44 + i45;
                        int i49 = i46 * 2;
                        int i50 = (i47 * 2) + i49;
                        int i51 = (i47 * 2) + i50;
                        int i52 = (i47 * 2) + i51;
                        dArr2[i49] = dArr[i48];
                        dArr2[i49 + 1] = dArr[i48 + 1];
                        dArr2[i50] = dArr[i48 + 2];
                        dArr2[i50 + 1] = dArr[i48 + 3];
                        dArr2[i51] = dArr[i48 + 4];
                        dArr2[i51 + 1] = dArr[i48 + 5];
                        dArr2[i52] = dArr[i48 + 6];
                        dArr2[i52 + 1] = dArr[i48 + 7];
                        i46++;
                    }
                    this.fftSlices.complexInverse(dArr2, 0, z2);
                    this.fftSlices.complexInverse(dArr2, this.slices * 2, z2);
                    this.fftSlices.complexInverse(dArr2, this.slices * 4, z2);
                    this.fftSlices.complexInverse(dArr2, this.slices * 6, z2);
                    int i53 = 0;
                    while (true) {
                        int i54 = this.slices;
                        if (i53 >= i54) {
                            break;
                        }
                        int i55 = (this.sliceStride * i53) + i44 + i45;
                        int i56 = i53 * 2;
                        int i57 = (i54 * 2) + i56;
                        int i58 = (i54 * 2) + i57;
                        int i59 = (i54 * 2) + i58;
                        dArr[i55] = dArr2[i56];
                        dArr[i55 + 1] = dArr2[i56 + 1];
                        dArr[i55 + 2] = dArr2[i57];
                        dArr[i55 + 3] = dArr2[i57 + 1];
                        dArr[i55 + 4] = dArr2[i58];
                        dArr[i55 + 5] = dArr2[i58 + 1];
                        dArr[i55 + 6] = dArr2[i59];
                        dArr[i55 + 7] = dArr2[i59 + 1];
                        i53++;
                    }
                }
            }
        } else if (i5 == 4) {
            for (int i60 = 0; i60 < this.rows; i60++) {
                int i61 = this.rowStride * i60;
                int i62 = 0;
                while (true) {
                    int i63 = this.slices;
                    if (i62 >= i63) {
                        break;
                    }
                    int i64 = (this.sliceStride * i62) + i61;
                    int i65 = i62 * 2;
                    int i66 = (i63 * 2) + i65;
                    dArr2[i65] = dArr[i64];
                    dArr2[i65 + 1] = dArr[i64 + 1];
                    dArr2[i66] = dArr[i64 + 2];
                    dArr2[i66 + 1] = dArr[i64 + 3];
                    i62++;
                }
                this.fftSlices.complexInverse(dArr2, 0, z2);
                this.fftSlices.complexInverse(dArr2, this.slices * 2, z2);
                int i67 = 0;
                while (true) {
                    int i68 = this.slices;
                    if (i67 >= i68) {
                        break;
                    }
                    int i69 = (this.sliceStride * i67) + i61;
                    int i70 = i67 * 2;
                    int i71 = (i68 * 2) + i70;
                    dArr[i69] = dArr2[i70];
                    dArr[i69 + 1] = dArr2[i70 + 1];
                    dArr[i69 + 2] = dArr2[i71];
                    dArr[i69 + 3] = dArr2[i71 + 1];
                    i67++;
                }
            }
        } else if (i5 == 2) {
            for (int i72 = 0; i72 < this.rows; i72++) {
                int i73 = this.rowStride * i72;
                for (int i74 = 0; i74 < this.slices; i74++) {
                    int i75 = (this.sliceStride * i74) + i73;
                    int i76 = i74 * 2;
                    dArr2[i76] = dArr[i75];
                    dArr2[i76 + 1] = dArr[i75 + 1];
                }
                this.fftSlices.complexInverse(dArr2, 0, z2);
                for (int i77 = 0; i77 < this.slices; i77++) {
                    int i78 = (this.sliceStride * i77) + i73;
                    int i79 = i77 * 2;
                    dArr[i78] = dArr2[i79];
                    dArr[i78 + 1] = dArr2[i79 + 1];
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x027d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void cdft3db_sub(int r36, pl.edu.icm.jlargearrays.DoubleLargeArray r37, boolean r38) {
        /*
            r35 = this;
            r0 = r35
            r1 = r37
            r2 = r38
            long r3 = r0.slicesl
            long r5 = r0.rowsl
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 >= 0) goto L_0x000f
            r3 = r5
        L_0x000f:
            r5 = 8
            long r3 = r3 * r5
            long r7 = r0.columnsl
            r9 = 4
            int r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r11 != 0) goto L_0x001d
            r7 = 1
        L_0x001b:
            long r3 = r3 >> r7
            goto L_0x0023
        L_0x001d:
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 >= 0) goto L_0x0023
            r7 = 2
            goto L_0x001b
        L_0x0023:
            pl.edu.icm.jlargearrays.DoubleLargeArray r7 = new pl.edu.icm.jlargearrays.DoubleLargeArray
            r7.<init>((long) r3)
            r3 = -1
            r15 = 6
            r17 = 3
            r21 = 2
            r23 = 1
            r4 = r36
            if (r4 != r3) goto L_0x027d
            long r2 = r0.columnsl
            int r4 = (r2 > r9 ? 1 : (r2 == r9 ? 0 : -1))
            if (r4 <= 0) goto L_0x0180
            r2 = 0
        L_0x003d:
            long r5 = r0.rowsl
            int r4 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r4 >= 0) goto L_0x04e4
            long r4 = r0.rowStridel
            long r4 = r4 * r2
            r25 = 0
        L_0x0048:
            long r11 = r0.columnsl
            int r6 = (r25 > r11 ? 1 : (r25 == r11 ? 0 : -1))
            if (r6 >= 0) goto L_0x0178
            r11 = 0
        L_0x0050:
            long r13 = r0.slicesl
            int r6 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r6 >= 0) goto L_0x00cf
            long r9 = r0.sliceStridel
            long r9 = r9 * r11
            long r9 = r9 + r4
            long r9 = r9 + r25
            r27 = r2
            long r2 = r11 * r21
            long r29 = r13 * r21
            r31 = r4
            long r4 = r29 + r2
            long r29 = r13 * r21
            r33 = r11
            long r11 = r4 + r29
            long r13 = r13 * r21
            long r13 = r13 + r11
            r29 = r13
            double r13 = r1.getDouble(r9)
            r7.setDouble(r2, r13)
            long r2 = r2 + r23
            long r13 = r9 + r23
            double r13 = r1.getDouble(r13)
            r7.setDouble(r2, r13)
            long r2 = r9 + r21
            double r2 = r1.getDouble(r2)
            r7.setDouble(r4, r2)
            long r4 = r4 + r23
            long r2 = r9 + r17
            double r2 = r1.getDouble(r2)
            r7.setDouble(r4, r2)
            r2 = 4
            long r4 = r9 + r2
            double r2 = r1.getDouble(r4)
            r7.setDouble(r11, r2)
            long r11 = r11 + r23
            r2 = 5
            long r13 = r9 + r2
            double r2 = r1.getDouble(r13)
            r7.setDouble(r11, r2)
            long r2 = r9 + r15
            double r2 = r1.getDouble(r2)
            r11 = r29
            r7.setDouble(r11, r2)
            long r13 = r11 + r23
            r2 = 7
            long r9 = r9 + r2
            double r2 = r1.getDouble(r9)
            r7.setDouble(r13, r2)
            long r11 = r33 + r23
            r2 = r27
            r4 = r31
            r9 = 4
            goto L_0x0050
        L_0x00cf:
            r27 = r2
            r31 = r4
            org.jtransforms.fft.DoubleFFT_1D r2 = r0.fftSlices
            r3 = 0
            r2.complexForward((pl.edu.icm.jlargearrays.DoubleLargeArray) r7, (long) r3)
            org.jtransforms.fft.DoubleFFT_1D r2 = r0.fftSlices
            long r3 = r0.slicesl
            long r3 = r3 * r21
            r2.complexForward((pl.edu.icm.jlargearrays.DoubleLargeArray) r7, (long) r3)
            org.jtransforms.fft.DoubleFFT_1D r2 = r0.fftSlices
            long r3 = r0.slicesl
            r5 = 4
            long r3 = r3 * r5
            r2.complexForward((pl.edu.icm.jlargearrays.DoubleLargeArray) r7, (long) r3)
            org.jtransforms.fft.DoubleFFT_1D r2 = r0.fftSlices
            long r3 = r0.slicesl
            long r3 = r3 * r15
            r2.complexForward((pl.edu.icm.jlargearrays.DoubleLargeArray) r7, (long) r3)
            r2 = 0
        L_0x00f7:
            long r4 = r0.slicesl
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 >= 0) goto L_0x016c
            long r8 = r0.sliceStridel
            long r8 = r8 * r2
            long r8 = r8 + r31
            long r8 = r8 + r25
            long r10 = r2 * r21
            long r12 = r4 * r21
            long r12 = r12 + r10
            long r29 = r4 * r21
            r33 = r2
            long r2 = r12 + r29
            long r4 = r4 * r21
            long r4 = r4 + r2
            r29 = r4
            double r4 = r7.getDouble(r10)
            r1.setDouble(r8, r4)
            long r4 = r8 + r23
            long r10 = r10 + r23
            double r10 = r7.getDouble(r10)
            r1.setDouble(r4, r10)
            long r4 = r8 + r21
            double r10 = r7.getDouble(r12)
            r1.setDouble(r4, r10)
            long r4 = r8 + r17
            long r12 = r12 + r23
            double r10 = r7.getDouble(r12)
            r1.setDouble(r4, r10)
            r4 = 4
            long r10 = r8 + r4
            double r4 = r7.getDouble(r2)
            r1.setDouble(r10, r4)
            r4 = 5
            long r13 = r8 + r4
            long r2 = r2 + r23
            double r2 = r7.getDouble(r2)
            r1.setDouble(r13, r2)
            long r2 = r8 + r15
            r4 = r29
            double r10 = r7.getDouble(r4)
            r1.setDouble(r2, r10)
            r2 = 7
            long r8 = r8 + r2
            long r4 = r4 + r23
            double r2 = r7.getDouble(r4)
            r1.setDouble(r8, r2)
            long r2 = r33 + r23
            goto L_0x00f7
        L_0x016c:
            r2 = 8
            long r25 = r25 + r2
            r2 = r27
            r4 = r31
            r9 = 4
            goto L_0x0048
        L_0x0178:
            r27 = r2
            long r2 = r27 + r23
            r9 = 4
            goto L_0x003d
        L_0x0180:
            r4 = r9
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 != 0) goto L_0x021e
            r2 = 0
        L_0x0187:
            long r4 = r0.rowsl
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 >= 0) goto L_0x04e4
            long r4 = r0.rowStridel
            long r4 = r4 * r2
            r8 = 0
        L_0x0192:
            long r10 = r0.slicesl
            int r6 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r6 >= 0) goto L_0x01ce
            long r12 = r0.sliceStridel
            long r12 = r12 * r8
            long r12 = r12 + r4
            long r14 = r8 * r21
            long r10 = r10 * r21
            long r10 = r10 + r14
            r19 = r2
            double r2 = r1.getDouble(r12)
            r7.setDouble(r14, r2)
            long r14 = r14 + r23
            long r2 = r12 + r23
            double r2 = r1.getDouble(r2)
            r7.setDouble(r14, r2)
            long r2 = r12 + r21
            double r2 = r1.getDouble(r2)
            r7.setDouble(r10, r2)
            long r10 = r10 + r23
            long r12 = r12 + r17
            double r2 = r1.getDouble(r12)
            r7.setDouble(r10, r2)
            long r8 = r8 + r23
            r2 = r19
            goto L_0x0192
        L_0x01ce:
            r19 = r2
            org.jtransforms.fft.DoubleFFT_1D r2 = r0.fftSlices
            r8 = 0
            r2.complexForward((pl.edu.icm.jlargearrays.DoubleLargeArray) r7, (long) r8)
            org.jtransforms.fft.DoubleFFT_1D r2 = r0.fftSlices
            long r8 = r0.slicesl
            long r8 = r8 * r21
            r2.complexForward((pl.edu.icm.jlargearrays.DoubleLargeArray) r7, (long) r8)
            r2 = 0
        L_0x01e2:
            long r8 = r0.slicesl
            int r6 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1))
            if (r6 >= 0) goto L_0x021a
            long r10 = r0.sliceStridel
            long r10 = r10 * r2
            long r10 = r10 + r4
            long r12 = r2 * r21
            long r8 = r8 * r21
            long r8 = r8 + r12
            double r14 = r7.getDouble(r12)
            r1.setDouble(r10, r14)
            long r14 = r10 + r23
            long r12 = r12 + r23
            double r12 = r7.getDouble(r12)
            r1.setDouble(r14, r12)
            long r12 = r10 + r21
            double r14 = r7.getDouble(r8)
            r1.setDouble(r12, r14)
            long r10 = r10 + r17
            long r8 = r8 + r23
            double r8 = r7.getDouble(r8)
            r1.setDouble(r10, r8)
            long r2 = r2 + r23
            goto L_0x01e2
        L_0x021a:
            long r2 = r19 + r23
            goto L_0x0187
        L_0x021e:
            int r2 = (r2 > r21 ? 1 : (r2 == r21 ? 0 : -1))
            if (r2 != 0) goto L_0x04e4
            r2 = 0
        L_0x0224:
            long r4 = r0.rowsl
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 >= 0) goto L_0x04e4
            long r4 = r0.rowStridel
            long r4 = r4 * r2
            r8 = 0
        L_0x022f:
            long r10 = r0.slicesl
            int r6 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r6 >= 0) goto L_0x0250
            long r10 = r0.sliceStridel
            long r10 = r10 * r8
            long r10 = r10 + r4
            long r12 = r8 * r21
            double r14 = r1.getDouble(r10)
            r7.setDouble(r12, r14)
            long r12 = r12 + r23
            long r10 = r10 + r23
            double r10 = r1.getDouble(r10)
            r7.setDouble(r12, r10)
            long r8 = r8 + r23
            goto L_0x022f
        L_0x0250:
            org.jtransforms.fft.DoubleFFT_1D r6 = r0.fftSlices
            r8 = 0
            r6.complexForward((pl.edu.icm.jlargearrays.DoubleLargeArray) r7, (long) r8)
            r8 = 0
        L_0x0259:
            long r10 = r0.slicesl
            int r6 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r6 >= 0) goto L_0x027a
            long r10 = r0.sliceStridel
            long r10 = r10 * r8
            long r10 = r10 + r4
            long r12 = r8 * r21
            double r14 = r7.getDouble(r12)
            r1.setDouble(r10, r14)
            long r10 = r10 + r23
            long r12 = r12 + r23
            double r12 = r7.getDouble(r12)
            r1.setDouble(r10, r12)
            long r8 = r8 + r23
            goto L_0x0259
        L_0x027a:
            long r2 = r2 + r23
            goto L_0x0224
        L_0x027d:
            long r3 = r0.columnsl
            r5 = 4
            int r8 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r8 <= 0) goto L_0x03dc
            r3 = 0
        L_0x0287:
            long r5 = r0.rowsl
            int r5 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r5 >= 0) goto L_0x04e4
            long r5 = r0.rowStridel
            long r5 = r5 * r3
            r8 = 0
        L_0x0292:
            long r10 = r0.columnsl
            int r10 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r10 >= 0) goto L_0x03cd
            r10 = 0
        L_0x029a:
            long r12 = r0.slicesl
            int r14 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r14 >= 0) goto L_0x031a
            r25 = r3
            long r3 = r0.sliceStridel
            long r3 = r3 * r10
            long r3 = r3 + r5
            long r3 = r3 + r8
            r27 = r8
            long r8 = r10 * r21
            long r29 = r12 * r21
            r31 = r5
            long r5 = r29 + r8
            long r29 = r12 * r21
            r33 = r10
            long r10 = r5 + r29
            long r12 = r12 * r21
            long r12 = r12 + r10
            r29 = r12
            double r12 = r1.getDouble(r3)
            r7.setDouble(r8, r12)
            long r8 = r8 + r23
            long r12 = r3 + r23
            double r12 = r1.getDouble(r12)
            r7.setDouble(r8, r12)
            long r8 = r3 + r21
            double r8 = r1.getDouble(r8)
            r7.setDouble(r5, r8)
            long r5 = r5 + r23
            long r8 = r3 + r17
            double r8 = r1.getDouble(r8)
            r7.setDouble(r5, r8)
            r5 = 4
            long r8 = r3 + r5
            double r5 = r1.getDouble(r8)
            r7.setDouble(r10, r5)
            long r10 = r10 + r23
            r5 = 5
            long r13 = r3 + r5
            double r5 = r1.getDouble(r13)
            r7.setDouble(r10, r5)
            long r5 = r3 + r15
            double r5 = r1.getDouble(r5)
            r10 = r29
            r7.setDouble(r10, r5)
            long r12 = r10 + r23
            r5 = 7
            long r3 = r3 + r5
            double r3 = r1.getDouble(r3)
            r7.setDouble(r12, r3)
            long r10 = r33 + r23
            r3 = r25
            r8 = r27
            r5 = r31
            goto L_0x029a
        L_0x031a:
            r25 = r3
            r31 = r5
            r27 = r8
            org.jtransforms.fft.DoubleFFT_1D r3 = r0.fftSlices
            r4 = 0
            r3.complexInverse((pl.edu.icm.jlargearrays.DoubleLargeArray) r7, (long) r4, (boolean) r2)
            org.jtransforms.fft.DoubleFFT_1D r3 = r0.fftSlices
            long r4 = r0.slicesl
            long r4 = r4 * r21
            r3.complexInverse((pl.edu.icm.jlargearrays.DoubleLargeArray) r7, (long) r4, (boolean) r2)
            org.jtransforms.fft.DoubleFFT_1D r3 = r0.fftSlices
            long r4 = r0.slicesl
            r8 = 4
            long r4 = r4 * r8
            r3.complexInverse((pl.edu.icm.jlargearrays.DoubleLargeArray) r7, (long) r4, (boolean) r2)
            org.jtransforms.fft.DoubleFFT_1D r3 = r0.fftSlices
            long r4 = r0.slicesl
            long r4 = r4 * r15
            r3.complexInverse((pl.edu.icm.jlargearrays.DoubleLargeArray) r7, (long) r4, (boolean) r2)
            r3 = 0
        L_0x0344:
            long r5 = r0.slicesl
            int r8 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r8 >= 0) goto L_0x03bc
            long r8 = r0.sliceStridel
            long r8 = r8 * r3
            long r8 = r8 + r31
            long r8 = r8 + r27
            long r10 = r3 * r21
            long r12 = r5 * r21
            long r12 = r12 + r10
            long r29 = r5 * r21
            r33 = r3
            long r2 = r12 + r29
            long r5 = r5 * r21
            long r5 = r5 + r2
            r29 = r5
            double r4 = r7.getDouble(r10)
            r1.setDouble(r8, r4)
            long r4 = r8 + r23
            long r10 = r10 + r23
            double r10 = r7.getDouble(r10)
            r1.setDouble(r4, r10)
            long r4 = r8 + r21
            double r10 = r7.getDouble(r12)
            r1.setDouble(r4, r10)
            long r4 = r8 + r17
            long r12 = r12 + r23
            double r10 = r7.getDouble(r12)
            r1.setDouble(r4, r10)
            r4 = 4
            long r10 = r8 + r4
            double r4 = r7.getDouble(r2)
            r1.setDouble(r10, r4)
            r4 = 5
            long r13 = r8 + r4
            long r2 = r2 + r23
            double r2 = r7.getDouble(r2)
            r1.setDouble(r13, r2)
            long r2 = r8 + r15
            r10 = r29
            double r12 = r7.getDouble(r10)
            r1.setDouble(r2, r12)
            r2 = 7
            long r8 = r8 + r2
            long r10 = r10 + r23
            double r10 = r7.getDouble(r10)
            r1.setDouble(r8, r10)
            long r8 = r33 + r23
            r2 = r38
            r3 = r8
            goto L_0x0344
        L_0x03bc:
            r2 = 7
            r4 = 5
            r8 = 8
            long r10 = r27 + r8
            r2 = r38
            r8 = r10
            r3 = r25
            r5 = r31
            goto L_0x0292
        L_0x03cd:
            r25 = r3
            r2 = 7
            r4 = 5
            r8 = 8
            long r10 = r25 + r23
            r2 = r38
            r3 = r10
            goto L_0x0287
        L_0x03dc:
            r5 = 4
            int r2 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r2 != 0) goto L_0x047f
            r3 = 0
        L_0x03e4:
            long r5 = r0.rowsl
            int r2 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r2 >= 0) goto L_0x04e4
            long r5 = r0.rowStridel
            long r5 = r5 * r3
            r8 = 0
        L_0x03ef:
            long r10 = r0.slicesl
            int r2 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r2 >= 0) goto L_0x042b
            long r12 = r0.sliceStridel
            long r12 = r12 * r8
            long r12 = r12 + r5
            long r14 = r8 * r21
            long r10 = r10 * r21
            long r10 = r10 + r14
            r19 = r3
            double r2 = r1.getDouble(r12)
            r7.setDouble(r14, r2)
            long r14 = r14 + r23
            long r2 = r12 + r23
            double r2 = r1.getDouble(r2)
            r7.setDouble(r14, r2)
            long r2 = r12 + r21
            double r2 = r1.getDouble(r2)
            r7.setDouble(r10, r2)
            long r10 = r10 + r23
            long r12 = r12 + r17
            double r2 = r1.getDouble(r12)
            r7.setDouble(r10, r2)
            long r8 = r8 + r23
            r3 = r19
            goto L_0x03ef
        L_0x042b:
            r19 = r3
            org.jtransforms.fft.DoubleFFT_1D r2 = r0.fftSlices
            r8 = r38
            r3 = 0
            r2.complexInverse((pl.edu.icm.jlargearrays.DoubleLargeArray) r7, (long) r3, (boolean) r8)
            org.jtransforms.fft.DoubleFFT_1D r2 = r0.fftSlices
            long r3 = r0.slicesl
            long r3 = r3 * r21
            r2.complexInverse((pl.edu.icm.jlargearrays.DoubleLargeArray) r7, (long) r3, (boolean) r8)
            r3 = 0
        L_0x0441:
            long r9 = r0.slicesl
            int r2 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r2 >= 0) goto L_0x047b
            long r11 = r0.sliceStridel
            long r11 = r11 * r3
            long r11 = r11 + r5
            long r13 = r3 * r21
            long r9 = r9 * r21
            long r9 = r9 + r13
            r15 = r5
            double r5 = r7.getDouble(r13)
            r1.setDouble(r11, r5)
            long r5 = r11 + r23
            long r13 = r13 + r23
            double r13 = r7.getDouble(r13)
            r1.setDouble(r5, r13)
            long r5 = r11 + r21
            double r13 = r7.getDouble(r9)
            r1.setDouble(r5, r13)
            long r11 = r11 + r17
            long r9 = r9 + r23
            double r5 = r7.getDouble(r9)
            r1.setDouble(r11, r5)
            long r3 = r3 + r23
            r5 = r15
            goto L_0x0441
        L_0x047b:
            long r3 = r19 + r23
            goto L_0x03e4
        L_0x047f:
            r8 = r38
            int r2 = (r3 > r21 ? 1 : (r3 == r21 ? 0 : -1))
            if (r2 != 0) goto L_0x04e4
            r3 = 0
        L_0x0487:
            long r5 = r0.rowsl
            int r2 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r2 >= 0) goto L_0x04e4
            long r5 = r0.rowStridel
            long r5 = r5 * r3
            r9 = 0
        L_0x0492:
            long r11 = r0.slicesl
            int r2 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r2 >= 0) goto L_0x04b5
            long r11 = r0.sliceStridel
            long r11 = r11 * r9
            long r11 = r11 + r5
            long r13 = r9 * r21
            r15 = r3
            double r2 = r1.getDouble(r11)
            r7.setDouble(r13, r2)
            long r13 = r13 + r23
            long r11 = r11 + r23
            double r2 = r1.getDouble(r11)
            r7.setDouble(r13, r2)
            long r9 = r9 + r23
            r3 = r15
            goto L_0x0492
        L_0x04b5:
            r15 = r3
            org.jtransforms.fft.DoubleFFT_1D r2 = r0.fftSlices
            r3 = 0
            r2.complexInverse((pl.edu.icm.jlargearrays.DoubleLargeArray) r7, (long) r3, (boolean) r8)
            r9 = r3
        L_0x04be:
            long r11 = r0.slicesl
            int r2 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r2 >= 0) goto L_0x04e1
            long r11 = r0.sliceStridel
            long r11 = r11 * r9
            long r11 = r11 + r5
            long r13 = r9 * r21
            double r3 = r7.getDouble(r13)
            r1.setDouble(r11, r3)
            long r11 = r11 + r23
            long r13 = r13 + r23
            double r2 = r7.getDouble(r13)
            r1.setDouble(r11, r2)
            long r9 = r9 + r23
            r3 = 0
            goto L_0x04be
        L_0x04e1:
            long r3 = r15 + r23
            goto L_0x0487
        L_0x04e4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.DoubleFFT_3D.cdft3db_sub(int, pl.edu.icm.jlargearrays.DoubleLargeArray, boolean):void");
    }

    private void cdft3db_sub(int i, double[][][] dArr, boolean z) {
        boolean z2 = z;
        int i2 = this.slices;
        int i3 = this.rows;
        if (i2 < i3) {
            i2 = i3;
        }
        int i4 = i2 * 8;
        int i5 = this.columns;
        if (i5 == 4) {
            i4 >>= 1;
        } else if (i5 < 4) {
            i4 >>= 2;
        }
        double[] dArr2 = new double[i4];
        if (i == -1) {
            if (i5 > 4) {
                for (int i6 = 0; i6 < this.rows; i6++) {
                    for (int i7 = 0; i7 < this.columns; i7 += 8) {
                        int i8 = 0;
                        while (true) {
                            int i9 = this.slices;
                            if (i8 >= i9) {
                                break;
                            }
                            int i10 = i8 * 2;
                            int i11 = (i9 * 2) + i10;
                            int i12 = (i9 * 2) + i11;
                            int i13 = (i9 * 2) + i12;
                            double[] dArr3 = dArr[i8][i6];
                            dArr2[i10] = dArr3[i7];
                            dArr2[i10 + 1] = dArr3[i7 + 1];
                            dArr2[i11] = dArr3[i7 + 2];
                            dArr2[i11 + 1] = dArr3[i7 + 3];
                            dArr2[i12] = dArr3[i7 + 4];
                            dArr2[i12 + 1] = dArr3[i7 + 5];
                            dArr2[i13] = dArr3[i7 + 6];
                            dArr2[i13 + 1] = dArr3[i7 + 7];
                            i8++;
                        }
                        this.fftSlices.complexForward(dArr2, 0);
                        this.fftSlices.complexForward(dArr2, this.slices * 2);
                        this.fftSlices.complexForward(dArr2, this.slices * 4);
                        this.fftSlices.complexForward(dArr2, this.slices * 6);
                        int i14 = 0;
                        while (true) {
                            int i15 = this.slices;
                            if (i14 >= i15) {
                                break;
                            }
                            int i16 = i14 * 2;
                            int i17 = (i15 * 2) + i16;
                            int i18 = (i15 * 2) + i17;
                            int i19 = (i15 * 2) + i18;
                            double[] dArr4 = dArr[i14][i6];
                            dArr4[i7] = dArr2[i16];
                            dArr4[i7 + 1] = dArr2[i16 + 1];
                            dArr4[i7 + 2] = dArr2[i17];
                            dArr4[i7 + 3] = dArr2[i17 + 1];
                            dArr4[i7 + 4] = dArr2[i18];
                            dArr4[i7 + 5] = dArr2[i18 + 1];
                            dArr4[i7 + 6] = dArr2[i19];
                            dArr4[i7 + 7] = dArr2[i19 + 1];
                            i14++;
                        }
                    }
                }
            } else if (i5 == 4) {
                for (int i20 = 0; i20 < this.rows; i20++) {
                    int i21 = 0;
                    while (true) {
                        int i22 = this.slices;
                        if (i21 >= i22) {
                            break;
                        }
                        int i23 = i21 * 2;
                        int i24 = (i22 * 2) + i23;
                        double[] dArr5 = dArr[i21][i20];
                        dArr2[i23] = dArr5[0];
                        dArr2[i23 + 1] = dArr5[1];
                        dArr2[i24] = dArr5[2];
                        dArr2[i24 + 1] = dArr5[3];
                        i21++;
                    }
                    this.fftSlices.complexForward(dArr2, 0);
                    this.fftSlices.complexForward(dArr2, this.slices * 2);
                    int i25 = 0;
                    while (true) {
                        int i26 = this.slices;
                        if (i25 >= i26) {
                            break;
                        }
                        int i27 = i25 * 2;
                        int i28 = (i26 * 2) + i27;
                        double[] dArr6 = dArr[i25][i20];
                        dArr6[0] = dArr2[i27];
                        dArr6[1] = dArr2[i27 + 1];
                        dArr6[2] = dArr2[i28];
                        dArr6[3] = dArr2[i28 + 1];
                        i25++;
                    }
                }
            } else if (i5 == 2) {
                for (int i29 = 0; i29 < this.rows; i29++) {
                    for (int i30 = 0; i30 < this.slices; i30++) {
                        int i31 = i30 * 2;
                        double[] dArr7 = dArr[i30][i29];
                        dArr2[i31] = dArr7[0];
                        dArr2[i31 + 1] = dArr7[1];
                    }
                    this.fftSlices.complexForward(dArr2, 0);
                    for (int i32 = 0; i32 < this.slices; i32++) {
                        int i33 = i32 * 2;
                        double[] dArr8 = dArr[i32][i29];
                        dArr8[0] = dArr2[i33];
                        dArr8[1] = dArr2[i33 + 1];
                    }
                }
            }
        } else if (i5 > 4) {
            for (int i34 = 0; i34 < this.rows; i34++) {
                for (int i35 = 0; i35 < this.columns; i35 += 8) {
                    int i36 = 0;
                    while (true) {
                        int i37 = this.slices;
                        if (i36 >= i37) {
                            break;
                        }
                        int i38 = i36 * 2;
                        int i39 = (i37 * 2) + i38;
                        int i40 = (i37 * 2) + i39;
                        int i41 = (i37 * 2) + i40;
                        double[] dArr9 = dArr[i36][i34];
                        dArr2[i38] = dArr9[i35];
                        dArr2[i38 + 1] = dArr9[i35 + 1];
                        dArr2[i39] = dArr9[i35 + 2];
                        dArr2[i39 + 1] = dArr9[i35 + 3];
                        dArr2[i40] = dArr9[i35 + 4];
                        dArr2[i40 + 1] = dArr9[i35 + 5];
                        dArr2[i41] = dArr9[i35 + 6];
                        dArr2[i41 + 1] = dArr9[i35 + 7];
                        i36++;
                    }
                    this.fftSlices.complexInverse(dArr2, 0, z2);
                    this.fftSlices.complexInverse(dArr2, this.slices * 2, z2);
                    this.fftSlices.complexInverse(dArr2, this.slices * 4, z2);
                    this.fftSlices.complexInverse(dArr2, this.slices * 6, z2);
                    int i42 = 0;
                    while (true) {
                        int i43 = this.slices;
                        if (i42 >= i43) {
                            break;
                        }
                        int i44 = i42 * 2;
                        int i45 = (i43 * 2) + i44;
                        int i46 = (i43 * 2) + i45;
                        int i47 = (i43 * 2) + i46;
                        double[] dArr10 = dArr[i42][i34];
                        dArr10[i35] = dArr2[i44];
                        dArr10[i35 + 1] = dArr2[i44 + 1];
                        dArr10[i35 + 2] = dArr2[i45];
                        dArr10[i35 + 3] = dArr2[i45 + 1];
                        dArr10[i35 + 4] = dArr2[i46];
                        dArr10[i35 + 5] = dArr2[i46 + 1];
                        dArr10[i35 + 6] = dArr2[i47];
                        dArr10[i35 + 7] = dArr2[i47 + 1];
                        i42++;
                    }
                }
            }
        } else if (i5 == 4) {
            for (int i48 = 0; i48 < this.rows; i48++) {
                int i49 = 0;
                while (true) {
                    int i50 = this.slices;
                    if (i49 >= i50) {
                        break;
                    }
                    int i51 = i49 * 2;
                    int i52 = (i50 * 2) + i51;
                    double[] dArr11 = dArr[i49][i48];
                    dArr2[i51] = dArr11[0];
                    dArr2[i51 + 1] = dArr11[1];
                    dArr2[i52] = dArr11[2];
                    dArr2[i52 + 1] = dArr11[3];
                    i49++;
                }
                this.fftSlices.complexInverse(dArr2, 0, z2);
                this.fftSlices.complexInverse(dArr2, this.slices * 2, z2);
                int i53 = 0;
                while (true) {
                    int i54 = this.slices;
                    if (i53 >= i54) {
                        break;
                    }
                    int i55 = i53 * 2;
                    int i56 = (i54 * 2) + i55;
                    double[] dArr12 = dArr[i53][i48];
                    dArr12[0] = dArr2[i55];
                    dArr12[1] = dArr2[i55 + 1];
                    dArr12[2] = dArr2[i56];
                    dArr12[3] = dArr2[i56 + 1];
                    i53++;
                }
            }
        } else if (i5 == 2) {
            for (int i57 = 0; i57 < this.rows; i57++) {
                for (int i58 = 0; i58 < this.slices; i58++) {
                    int i59 = i58 * 2;
                    double[] dArr13 = dArr[i58][i57];
                    dArr2[i59] = dArr13[0];
                    dArr2[i59 + 1] = dArr13[1];
                }
                this.fftSlices.complexInverse(dArr2, 0, z2);
                for (int i60 = 0; i60 < this.slices; i60++) {
                    int i61 = i60 * 2;
                    double[] dArr14 = dArr[i60][i57];
                    dArr14[0] = dArr2[i61];
                    dArr14[1] = dArr2[i61 + 1];
                }
            }
        }
    }

    private void xdft3da_subth1(int i, int i2, double[] dArr, boolean z) {
        Class<DoubleFFT_3D> cls = DoubleFFT_3D.class;
        int min = FastMath.min(ConcurrencyUtils.getNumberOfThreads(), this.slices);
        int i3 = this.slices;
        int i4 = this.rows;
        if (i3 < i4) {
            i3 = i4;
        }
        int i5 = i3 * 8;
        int i6 = this.columns;
        if (i6 == 4) {
            i5 >>= 1;
        } else if (i6 < 4) {
            i5 >>= 2;
        }
        int i7 = i5;
        Future[] futureArr = new Future[min];
        for (int i8 = 0; i8 < min; i8++) {
            final int i9 = i7;
            final int i10 = i2;
            final int i11 = i8;
            final int i12 = min;
            final int i13 = i;
            final double[] dArr2 = dArr;
            final boolean z2 = z;
            futureArr[i8] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    double[] dArr = new double[i9];
                    if (i10 == -1) {
                        for (int i = i11; i < DoubleFFT_3D.this.slices; i += i12) {
                            int access$000 = DoubleFFT_3D.this.sliceStride * i;
                            if (i13 == 0) {
                                for (int i2 = 0; i2 < DoubleFFT_3D.this.rows; i2++) {
                                    DoubleFFT_3D.this.fftColumns.complexForward(dArr2, (DoubleFFT_3D.this.rowStride * i2) + access$000);
                                }
                            } else {
                                for (int i3 = 0; i3 < DoubleFFT_3D.this.rows; i3++) {
                                    DoubleFFT_3D.this.fftColumns.realForward(dArr2, (DoubleFFT_3D.this.rowStride * i3) + access$000);
                                }
                            }
                            if (DoubleFFT_3D.this.columns > 4) {
                                for (int i4 = 0; i4 < DoubleFFT_3D.this.columns; i4 += 8) {
                                    for (int i5 = 0; i5 < DoubleFFT_3D.this.rows; i5++) {
                                        int access$200 = (DoubleFFT_3D.this.rowStride * i5) + access$000 + i4;
                                        int i6 = i5 * 2;
                                        int access$100 = (DoubleFFT_3D.this.rows * 2) + i6;
                                        int access$1002 = (DoubleFFT_3D.this.rows * 2) + access$100;
                                        int access$1003 = (DoubleFFT_3D.this.rows * 2) + access$1002;
                                        double[] dArr2 = dArr2;
                                        dArr[i6] = dArr2[access$200];
                                        dArr[i6 + 1] = dArr2[access$200 + 1];
                                        dArr[access$100] = dArr2[access$200 + 2];
                                        dArr[access$100 + 1] = dArr2[access$200 + 3];
                                        dArr[access$1002] = dArr2[access$200 + 4];
                                        dArr[access$1002 + 1] = dArr2[access$200 + 5];
                                        dArr[access$1003] = dArr2[access$200 + 6];
                                        dArr[access$1003 + 1] = dArr2[access$200 + 7];
                                    }
                                    DoubleFFT_3D.this.fftRows.complexForward(dArr, 0);
                                    DoubleFFT_3D.this.fftRows.complexForward(dArr, DoubleFFT_3D.this.rows * 2);
                                    DoubleFFT_3D.this.fftRows.complexForward(dArr, DoubleFFT_3D.this.rows * 4);
                                    DoubleFFT_3D.this.fftRows.complexForward(dArr, DoubleFFT_3D.this.rows * 6);
                                    for (int i7 = 0; i7 < DoubleFFT_3D.this.rows; i7++) {
                                        int access$2002 = (DoubleFFT_3D.this.rowStride * i7) + access$000 + i4;
                                        int i8 = i7 * 2;
                                        int access$1004 = (DoubleFFT_3D.this.rows * 2) + i8;
                                        int access$1005 = (DoubleFFT_3D.this.rows * 2) + access$1004;
                                        int access$1006 = (DoubleFFT_3D.this.rows * 2) + access$1005;
                                        double[] dArr3 = dArr2;
                                        dArr3[access$2002] = dArr[i8];
                                        dArr3[access$2002 + 1] = dArr[i8 + 1];
                                        dArr3[access$2002 + 2] = dArr[access$1004];
                                        dArr3[access$2002 + 3] = dArr[access$1004 + 1];
                                        dArr3[access$2002 + 4] = dArr[access$1005];
                                        dArr3[access$2002 + 5] = dArr[access$1005 + 1];
                                        dArr3[access$2002 + 6] = dArr[access$1006];
                                        dArr3[access$2002 + 7] = dArr[access$1006 + 1];
                                    }
                                }
                            } else if (DoubleFFT_3D.this.columns == 4) {
                                for (int i9 = 0; i9 < DoubleFFT_3D.this.rows; i9++) {
                                    int access$2003 = (DoubleFFT_3D.this.rowStride * i9) + access$000;
                                    int i10 = i9 * 2;
                                    int access$1007 = (DoubleFFT_3D.this.rows * 2) + i10;
                                    double[] dArr4 = dArr2;
                                    dArr[i10] = dArr4[access$2003];
                                    dArr[i10 + 1] = dArr4[access$2003 + 1];
                                    dArr[access$1007] = dArr4[access$2003 + 2];
                                    dArr[access$1007 + 1] = dArr4[access$2003 + 3];
                                }
                                DoubleFFT_3D.this.fftRows.complexForward(dArr, 0);
                                DoubleFFT_3D.this.fftRows.complexForward(dArr, DoubleFFT_3D.this.rows * 2);
                                for (int i11 = 0; i11 < DoubleFFT_3D.this.rows; i11++) {
                                    int access$2004 = (DoubleFFT_3D.this.rowStride * i11) + access$000;
                                    int i12 = i11 * 2;
                                    int access$1008 = (DoubleFFT_3D.this.rows * 2) + i12;
                                    double[] dArr5 = dArr2;
                                    dArr5[access$2004] = dArr[i12];
                                    dArr5[access$2004 + 1] = dArr[i12 + 1];
                                    dArr5[access$2004 + 2] = dArr[access$1008];
                                    dArr5[access$2004 + 3] = dArr[access$1008 + 1];
                                }
                            } else if (DoubleFFT_3D.this.columns == 2) {
                                for (int i13 = 0; i13 < DoubleFFT_3D.this.rows; i13++) {
                                    int access$2005 = (DoubleFFT_3D.this.rowStride * i13) + access$000;
                                    int i14 = i13 * 2;
                                    double[] dArr6 = dArr2;
                                    dArr[i14] = dArr6[access$2005];
                                    dArr[i14 + 1] = dArr6[access$2005 + 1];
                                }
                                DoubleFFT_3D.this.fftRows.complexForward(dArr, 0);
                                for (int i15 = 0; i15 < DoubleFFT_3D.this.rows; i15++) {
                                    int access$2006 = (DoubleFFT_3D.this.rowStride * i15) + access$000;
                                    int i16 = i15 * 2;
                                    double[] dArr7 = dArr2;
                                    dArr7[access$2006] = dArr[i16];
                                    dArr7[access$2006 + 1] = dArr[i16 + 1];
                                }
                            }
                        }
                        return;
                    }
                    for (int i17 = i11; i17 < DoubleFFT_3D.this.slices; i17 += i12) {
                        int access$0002 = DoubleFFT_3D.this.sliceStride * i17;
                        if (i13 == 0) {
                            for (int i18 = 0; i18 < DoubleFFT_3D.this.rows; i18++) {
                                DoubleFFT_3D.this.fftColumns.complexInverse(dArr2, (DoubleFFT_3D.this.rowStride * i18) + access$0002, z2);
                            }
                        }
                        if (DoubleFFT_3D.this.columns > 4) {
                            for (int i19 = 0; i19 < DoubleFFT_3D.this.columns; i19 += 8) {
                                for (int i20 = 0; i20 < DoubleFFT_3D.this.rows; i20++) {
                                    int access$2007 = (DoubleFFT_3D.this.rowStride * i20) + access$0002 + i19;
                                    int i21 = i20 * 2;
                                    int access$1009 = (DoubleFFT_3D.this.rows * 2) + i21;
                                    int access$10010 = (DoubleFFT_3D.this.rows * 2) + access$1009;
                                    int access$10011 = (DoubleFFT_3D.this.rows * 2) + access$10010;
                                    double[] dArr8 = dArr2;
                                    dArr[i21] = dArr8[access$2007];
                                    dArr[i21 + 1] = dArr8[access$2007 + 1];
                                    dArr[access$1009] = dArr8[access$2007 + 2];
                                    dArr[access$1009 + 1] = dArr8[access$2007 + 3];
                                    dArr[access$10010] = dArr8[access$2007 + 4];
                                    dArr[access$10010 + 1] = dArr8[access$2007 + 5];
                                    dArr[access$10011] = dArr8[access$2007 + 6];
                                    dArr[access$10011 + 1] = dArr8[access$2007 + 7];
                                }
                                DoubleFFT_3D.this.fftRows.complexInverse(dArr, 0, z2);
                                DoubleFFT_3D.this.fftRows.complexInverse(dArr, DoubleFFT_3D.this.rows * 2, z2);
                                DoubleFFT_3D.this.fftRows.complexInverse(dArr, DoubleFFT_3D.this.rows * 4, z2);
                                DoubleFFT_3D.this.fftRows.complexInverse(dArr, DoubleFFT_3D.this.rows * 6, z2);
                                for (int i22 = 0; i22 < DoubleFFT_3D.this.rows; i22++) {
                                    int access$2008 = (DoubleFFT_3D.this.rowStride * i22) + access$0002 + i19;
                                    int i23 = i22 * 2;
                                    int access$10012 = (DoubleFFT_3D.this.rows * 2) + i23;
                                    int access$10013 = (DoubleFFT_3D.this.rows * 2) + access$10012;
                                    int access$10014 = (DoubleFFT_3D.this.rows * 2) + access$10013;
                                    double[] dArr9 = dArr2;
                                    dArr9[access$2008] = dArr[i23];
                                    dArr9[access$2008 + 1] = dArr[i23 + 1];
                                    dArr9[access$2008 + 2] = dArr[access$10012];
                                    dArr9[access$2008 + 3] = dArr[access$10012 + 1];
                                    dArr9[access$2008 + 4] = dArr[access$10013];
                                    dArr9[access$2008 + 5] = dArr[access$10013 + 1];
                                    dArr9[access$2008 + 6] = dArr[access$10014];
                                    dArr9[access$2008 + 7] = dArr[access$10014 + 1];
                                }
                            }
                        } else if (DoubleFFT_3D.this.columns == 4) {
                            for (int i24 = 0; i24 < DoubleFFT_3D.this.rows; i24++) {
                                int access$2009 = (DoubleFFT_3D.this.rowStride * i24) + access$0002;
                                int i25 = i24 * 2;
                                int access$10015 = (DoubleFFT_3D.this.rows * 2) + i25;
                                double[] dArr10 = dArr2;
                                dArr[i25] = dArr10[access$2009];
                                dArr[i25 + 1] = dArr10[access$2009 + 1];
                                dArr[access$10015] = dArr10[access$2009 + 2];
                                dArr[access$10015 + 1] = dArr10[access$2009 + 3];
                            }
                            DoubleFFT_3D.this.fftRows.complexInverse(dArr, 0, z2);
                            DoubleFFT_3D.this.fftRows.complexInverse(dArr, DoubleFFT_3D.this.rows * 2, z2);
                            for (int i26 = 0; i26 < DoubleFFT_3D.this.rows; i26++) {
                                int access$20010 = (DoubleFFT_3D.this.rowStride * i26) + access$0002;
                                int i27 = i26 * 2;
                                int access$10016 = (DoubleFFT_3D.this.rows * 2) + i27;
                                double[] dArr11 = dArr2;
                                dArr11[access$20010] = dArr[i27];
                                dArr11[access$20010 + 1] = dArr[i27 + 1];
                                dArr11[access$20010 + 2] = dArr[access$10016];
                                dArr11[access$20010 + 3] = dArr[access$10016 + 1];
                            }
                        } else if (DoubleFFT_3D.this.columns == 2) {
                            for (int i28 = 0; i28 < DoubleFFT_3D.this.rows; i28++) {
                                int access$20011 = (DoubleFFT_3D.this.rowStride * i28) + access$0002;
                                int i29 = i28 * 2;
                                double[] dArr12 = dArr2;
                                dArr[i29] = dArr12[access$20011];
                                dArr[i29 + 1] = dArr12[access$20011 + 1];
                            }
                            DoubleFFT_3D.this.fftRows.complexInverse(dArr, 0, z2);
                            for (int i30 = 0; i30 < DoubleFFT_3D.this.rows; i30++) {
                                int access$20012 = (DoubleFFT_3D.this.rowStride * i30) + access$0002;
                                int i31 = i30 * 2;
                                double[] dArr13 = dArr2;
                                dArr13[access$20012] = dArr[i31];
                                dArr13[access$20012 + 1] = dArr[i31 + 1];
                            }
                        }
                        if (i13 != 0) {
                            for (int i32 = 0; i32 < DoubleFFT_3D.this.rows; i32++) {
                                DoubleFFT_3D.this.fftColumns.realInverse(dArr2, (DoubleFFT_3D.this.rowStride * i32) + access$0002, z2);
                            }
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

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0034 A[LOOP:0: B:11:0x0032->B:12:0x0034, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void xdft3da_subth1(long r21, int r23, pl.edu.icm.jlargearrays.DoubleLargeArray r24, boolean r25) {
        /*
            r20 = this;
            r13 = r20
            java.lang.Class<org.jtransforms.fft.DoubleFFT_3D> r14 = org.jtransforms.fft.DoubleFFT_3D.class
            int r0 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getNumberOfThreads()
            long r0 = (long) r0
            long r2 = r13.slicesl
            long r0 = org.apache.commons.math3.util.FastMath.min((long) r0, (long) r2)
            int r0 = (int) r0
            long r1 = r13.slicesl
            long r3 = r13.rowsl
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 >= 0) goto L_0x0019
            r1 = r3
        L_0x0019:
            r3 = 8
            long r1 = r1 * r3
            long r3 = r13.columnsl
            r5 = 4
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 != 0) goto L_0x0027
            r3 = 1
        L_0x0025:
            long r1 = r1 >> r3
            goto L_0x002d
        L_0x0027:
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 >= 0) goto L_0x002d
            r3 = 2
            goto L_0x0025
        L_0x002d:
            r15 = r1
            java.util.concurrent.Future[] r12 = new java.util.concurrent.Future[r0]
            r1 = 0
            r11 = r1
        L_0x0032:
            if (r11 >= r0) goto L_0x0057
            long r6 = (long) r11
            org.jtransforms.fft.DoubleFFT_3D$52 r17 = new org.jtransforms.fft.DoubleFFT_3D$52
            r1 = r17
            r2 = r20
            r3 = r15
            r5 = r23
            r8 = r0
            r9 = r21
            r18 = r11
            r11 = r24
            r19 = r12
            r12 = r25
            r1.<init>(r3, r5, r6, r8, r9, r11, r12)
            java.util.concurrent.Future r1 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r17)
            r19[r18] = r1
            int r11 = r18 + 1
            r12 = r19
            goto L_0x0032
        L_0x0057:
            r19 = r12
            r1 = 0
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r19)     // Catch:{ InterruptedException -> 0x006e, ExecutionException -> 0x005e }
            goto L_0x007d
        L_0x005e:
            r0 = move-exception
            r2 = r0
            java.lang.String r0 = r14.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r3 = java.util.logging.Level.SEVERE
            r0.log(r3, r1, r2)
            goto L_0x007d
        L_0x006e:
            r0 = move-exception
            r2 = r0
            java.lang.String r0 = r14.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r3 = java.util.logging.Level.SEVERE
            r0.log(r3, r1, r2)
        L_0x007d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.DoubleFFT_3D.xdft3da_subth1(long, int, pl.edu.icm.jlargearrays.DoubleLargeArray, boolean):void");
    }

    private void xdft3da_subth2(int i, int i2, double[] dArr, boolean z) {
        Class<DoubleFFT_3D> cls = DoubleFFT_3D.class;
        int min = FastMath.min(ConcurrencyUtils.getNumberOfThreads(), this.slices);
        int i3 = this.slices;
        int i4 = this.rows;
        if (i3 < i4) {
            i3 = i4;
        }
        int i5 = i3 * 8;
        int i6 = this.columns;
        if (i6 == 4) {
            i5 >>= 1;
        } else if (i6 < 4) {
            i5 >>= 2;
        }
        int i7 = i5;
        Future[] futureArr = new Future[min];
        for (int i8 = 0; i8 < min; i8++) {
            final int i9 = i7;
            final int i10 = i2;
            final int i11 = i8;
            final int i12 = min;
            final int i13 = i;
            final double[] dArr2 = dArr;
            final boolean z2 = z;
            futureArr[i8] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    double[] dArr = new double[i9];
                    if (i10 == -1) {
                        for (int i = i11; i < DoubleFFT_3D.this.slices; i += i12) {
                            int access$000 = DoubleFFT_3D.this.sliceStride * i;
                            if (i13 == 0) {
                                for (int i2 = 0; i2 < DoubleFFT_3D.this.rows; i2++) {
                                    DoubleFFT_3D.this.fftColumns.complexForward(dArr2, (DoubleFFT_3D.this.rowStride * i2) + access$000);
                                }
                            } else {
                                for (int i3 = 0; i3 < DoubleFFT_3D.this.rows; i3++) {
                                    DoubleFFT_3D.this.fftColumns.realForward(dArr2, (DoubleFFT_3D.this.rowStride * i3) + access$000);
                                }
                            }
                            if (DoubleFFT_3D.this.columns > 4) {
                                for (int i4 = 0; i4 < DoubleFFT_3D.this.columns; i4 += 8) {
                                    for (int i5 = 0; i5 < DoubleFFT_3D.this.rows; i5++) {
                                        int access$200 = (DoubleFFT_3D.this.rowStride * i5) + access$000 + i4;
                                        int i6 = i5 * 2;
                                        int access$100 = (DoubleFFT_3D.this.rows * 2) + i6;
                                        int access$1002 = (DoubleFFT_3D.this.rows * 2) + access$100;
                                        int access$1003 = (DoubleFFT_3D.this.rows * 2) + access$1002;
                                        double[] dArr2 = dArr2;
                                        dArr[i6] = dArr2[access$200];
                                        dArr[i6 + 1] = dArr2[access$200 + 1];
                                        dArr[access$100] = dArr2[access$200 + 2];
                                        dArr[access$100 + 1] = dArr2[access$200 + 3];
                                        dArr[access$1002] = dArr2[access$200 + 4];
                                        dArr[access$1002 + 1] = dArr2[access$200 + 5];
                                        dArr[access$1003] = dArr2[access$200 + 6];
                                        dArr[access$1003 + 1] = dArr2[access$200 + 7];
                                    }
                                    DoubleFFT_3D.this.fftRows.complexForward(dArr, 0);
                                    DoubleFFT_3D.this.fftRows.complexForward(dArr, DoubleFFT_3D.this.rows * 2);
                                    DoubleFFT_3D.this.fftRows.complexForward(dArr, DoubleFFT_3D.this.rows * 4);
                                    DoubleFFT_3D.this.fftRows.complexForward(dArr, DoubleFFT_3D.this.rows * 6);
                                    for (int i7 = 0; i7 < DoubleFFT_3D.this.rows; i7++) {
                                        int access$2002 = (DoubleFFT_3D.this.rowStride * i7) + access$000 + i4;
                                        int i8 = i7 * 2;
                                        int access$1004 = (DoubleFFT_3D.this.rows * 2) + i8;
                                        int access$1005 = (DoubleFFT_3D.this.rows * 2) + access$1004;
                                        int access$1006 = (DoubleFFT_3D.this.rows * 2) + access$1005;
                                        double[] dArr3 = dArr2;
                                        dArr3[access$2002] = dArr[i8];
                                        dArr3[access$2002 + 1] = dArr[i8 + 1];
                                        dArr3[access$2002 + 2] = dArr[access$1004];
                                        dArr3[access$2002 + 3] = dArr[access$1004 + 1];
                                        dArr3[access$2002 + 4] = dArr[access$1005];
                                        dArr3[access$2002 + 5] = dArr[access$1005 + 1];
                                        dArr3[access$2002 + 6] = dArr[access$1006];
                                        dArr3[access$2002 + 7] = dArr[access$1006 + 1];
                                    }
                                }
                            } else if (DoubleFFT_3D.this.columns == 4) {
                                for (int i9 = 0; i9 < DoubleFFT_3D.this.rows; i9++) {
                                    int access$2003 = (DoubleFFT_3D.this.rowStride * i9) + access$000;
                                    int i10 = i9 * 2;
                                    int access$1007 = (DoubleFFT_3D.this.rows * 2) + i10;
                                    double[] dArr4 = dArr2;
                                    dArr[i10] = dArr4[access$2003];
                                    dArr[i10 + 1] = dArr4[access$2003 + 1];
                                    dArr[access$1007] = dArr4[access$2003 + 2];
                                    dArr[access$1007 + 1] = dArr4[access$2003 + 3];
                                }
                                DoubleFFT_3D.this.fftRows.complexForward(dArr, 0);
                                DoubleFFT_3D.this.fftRows.complexForward(dArr, DoubleFFT_3D.this.rows * 2);
                                for (int i11 = 0; i11 < DoubleFFT_3D.this.rows; i11++) {
                                    int access$2004 = (DoubleFFT_3D.this.rowStride * i11) + access$000;
                                    int i12 = i11 * 2;
                                    int access$1008 = (DoubleFFT_3D.this.rows * 2) + i12;
                                    double[] dArr5 = dArr2;
                                    dArr5[access$2004] = dArr[i12];
                                    dArr5[access$2004 + 1] = dArr[i12 + 1];
                                    dArr5[access$2004 + 2] = dArr[access$1008];
                                    dArr5[access$2004 + 3] = dArr[access$1008 + 1];
                                }
                            } else if (DoubleFFT_3D.this.columns == 2) {
                                for (int i13 = 0; i13 < DoubleFFT_3D.this.rows; i13++) {
                                    int access$2005 = (DoubleFFT_3D.this.rowStride * i13) + access$000;
                                    int i14 = i13 * 2;
                                    double[] dArr6 = dArr2;
                                    dArr[i14] = dArr6[access$2005];
                                    dArr[i14 + 1] = dArr6[access$2005 + 1];
                                }
                                DoubleFFT_3D.this.fftRows.complexForward(dArr, 0);
                                for (int i15 = 0; i15 < DoubleFFT_3D.this.rows; i15++) {
                                    int access$2006 = (DoubleFFT_3D.this.rowStride * i15) + access$000;
                                    int i16 = i15 * 2;
                                    double[] dArr7 = dArr2;
                                    dArr7[access$2006] = dArr[i16];
                                    dArr7[access$2006 + 1] = dArr[i16 + 1];
                                }
                            }
                        }
                        return;
                    }
                    for (int i17 = i11; i17 < DoubleFFT_3D.this.slices; i17 += i12) {
                        int access$0002 = DoubleFFT_3D.this.sliceStride * i17;
                        if (i13 == 0) {
                            for (int i18 = 0; i18 < DoubleFFT_3D.this.rows; i18++) {
                                DoubleFFT_3D.this.fftColumns.complexInverse(dArr2, (DoubleFFT_3D.this.rowStride * i18) + access$0002, z2);
                            }
                        } else {
                            for (int i19 = 0; i19 < DoubleFFT_3D.this.rows; i19++) {
                                DoubleFFT_3D.this.fftColumns.realInverse2(dArr2, (DoubleFFT_3D.this.rowStride * i19) + access$0002, z2);
                            }
                        }
                        if (DoubleFFT_3D.this.columns > 4) {
                            for (int i20 = 0; i20 < DoubleFFT_3D.this.columns; i20 += 8) {
                                for (int i21 = 0; i21 < DoubleFFT_3D.this.rows; i21++) {
                                    int access$2007 = (DoubleFFT_3D.this.rowStride * i21) + access$0002 + i20;
                                    int i22 = i21 * 2;
                                    int access$1009 = (DoubleFFT_3D.this.rows * 2) + i22;
                                    int access$10010 = (DoubleFFT_3D.this.rows * 2) + access$1009;
                                    int access$10011 = (DoubleFFT_3D.this.rows * 2) + access$10010;
                                    double[] dArr8 = dArr2;
                                    dArr[i22] = dArr8[access$2007];
                                    dArr[i22 + 1] = dArr8[access$2007 + 1];
                                    dArr[access$1009] = dArr8[access$2007 + 2];
                                    dArr[access$1009 + 1] = dArr8[access$2007 + 3];
                                    dArr[access$10010] = dArr8[access$2007 + 4];
                                    dArr[access$10010 + 1] = dArr8[access$2007 + 5];
                                    dArr[access$10011] = dArr8[access$2007 + 6];
                                    dArr[access$10011 + 1] = dArr8[access$2007 + 7];
                                }
                                DoubleFFT_3D.this.fftRows.complexInverse(dArr, 0, z2);
                                DoubleFFT_3D.this.fftRows.complexInverse(dArr, DoubleFFT_3D.this.rows * 2, z2);
                                DoubleFFT_3D.this.fftRows.complexInverse(dArr, DoubleFFT_3D.this.rows * 4, z2);
                                DoubleFFT_3D.this.fftRows.complexInverse(dArr, DoubleFFT_3D.this.rows * 6, z2);
                                for (int i23 = 0; i23 < DoubleFFT_3D.this.rows; i23++) {
                                    int access$2008 = (DoubleFFT_3D.this.rowStride * i23) + access$0002 + i20;
                                    int i24 = i23 * 2;
                                    int access$10012 = (DoubleFFT_3D.this.rows * 2) + i24;
                                    int access$10013 = (DoubleFFT_3D.this.rows * 2) + access$10012;
                                    int access$10014 = (DoubleFFT_3D.this.rows * 2) + access$10013;
                                    double[] dArr9 = dArr2;
                                    dArr9[access$2008] = dArr[i24];
                                    dArr9[access$2008 + 1] = dArr[i24 + 1];
                                    dArr9[access$2008 + 2] = dArr[access$10012];
                                    dArr9[access$2008 + 3] = dArr[access$10012 + 1];
                                    dArr9[access$2008 + 4] = dArr[access$10013];
                                    dArr9[access$2008 + 5] = dArr[access$10013 + 1];
                                    dArr9[access$2008 + 6] = dArr[access$10014];
                                    dArr9[access$2008 + 7] = dArr[access$10014 + 1];
                                }
                            }
                        } else if (DoubleFFT_3D.this.columns == 4) {
                            for (int i25 = 0; i25 < DoubleFFT_3D.this.rows; i25++) {
                                int access$2009 = (DoubleFFT_3D.this.rowStride * i25) + access$0002;
                                int i26 = i25 * 2;
                                int access$10015 = (DoubleFFT_3D.this.rows * 2) + i26;
                                double[] dArr10 = dArr2;
                                dArr[i26] = dArr10[access$2009];
                                dArr[i26 + 1] = dArr10[access$2009 + 1];
                                dArr[access$10015] = dArr10[access$2009 + 2];
                                dArr[access$10015 + 1] = dArr10[access$2009 + 3];
                            }
                            DoubleFFT_3D.this.fftRows.complexInverse(dArr, 0, z2);
                            DoubleFFT_3D.this.fftRows.complexInverse(dArr, DoubleFFT_3D.this.rows * 2, z2);
                            for (int i27 = 0; i27 < DoubleFFT_3D.this.rows; i27++) {
                                int access$20010 = (DoubleFFT_3D.this.rowStride * i27) + access$0002;
                                int i28 = i27 * 2;
                                int access$10016 = (DoubleFFT_3D.this.rows * 2) + i28;
                                double[] dArr11 = dArr2;
                                dArr11[access$20010] = dArr[i28];
                                dArr11[access$20010 + 1] = dArr[i28 + 1];
                                dArr11[access$20010 + 2] = dArr[access$10016];
                                dArr11[access$20010 + 3] = dArr[access$10016 + 1];
                            }
                        } else if (DoubleFFT_3D.this.columns == 2) {
                            for (int i29 = 0; i29 < DoubleFFT_3D.this.rows; i29++) {
                                int access$20011 = (DoubleFFT_3D.this.rowStride * i29) + access$0002;
                                int i30 = i29 * 2;
                                double[] dArr12 = dArr2;
                                dArr[i30] = dArr12[access$20011];
                                dArr[i30 + 1] = dArr12[access$20011 + 1];
                            }
                            DoubleFFT_3D.this.fftRows.complexInverse(dArr, 0, z2);
                            for (int i31 = 0; i31 < DoubleFFT_3D.this.rows; i31++) {
                                int access$20012 = (DoubleFFT_3D.this.rowStride * i31) + access$0002;
                                int i32 = i31 * 2;
                                double[] dArr13 = dArr2;
                                dArr13[access$20012] = dArr[i32];
                                dArr13[access$20012 + 1] = dArr[i32 + 1];
                            }
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

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0034 A[LOOP:0: B:11:0x0032->B:12:0x0034, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void xdft3da_subth2(long r21, int r23, pl.edu.icm.jlargearrays.DoubleLargeArray r24, boolean r25) {
        /*
            r20 = this;
            r13 = r20
            java.lang.Class<org.jtransforms.fft.DoubleFFT_3D> r14 = org.jtransforms.fft.DoubleFFT_3D.class
            int r0 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getNumberOfThreads()
            long r0 = (long) r0
            long r2 = r13.slicesl
            long r0 = org.apache.commons.math3.util.FastMath.min((long) r0, (long) r2)
            int r0 = (int) r0
            long r1 = r13.slicesl
            long r3 = r13.rowsl
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 >= 0) goto L_0x0019
            r1 = r3
        L_0x0019:
            r3 = 8
            long r1 = r1 * r3
            long r3 = r13.columnsl
            r5 = 4
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 != 0) goto L_0x0027
            r3 = 1
        L_0x0025:
            long r1 = r1 >> r3
            goto L_0x002d
        L_0x0027:
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 >= 0) goto L_0x002d
            r3 = 2
            goto L_0x0025
        L_0x002d:
            r15 = r1
            java.util.concurrent.Future[] r12 = new java.util.concurrent.Future[r0]
            r1 = 0
            r11 = r1
        L_0x0032:
            if (r11 >= r0) goto L_0x0057
            long r6 = (long) r11
            org.jtransforms.fft.DoubleFFT_3D$54 r17 = new org.jtransforms.fft.DoubleFFT_3D$54
            r1 = r17
            r2 = r20
            r3 = r15
            r5 = r23
            r8 = r0
            r9 = r21
            r18 = r11
            r11 = r24
            r19 = r12
            r12 = r25
            r1.<init>(r3, r5, r6, r8, r9, r11, r12)
            java.util.concurrent.Future r1 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r17)
            r19[r18] = r1
            int r11 = r18 + 1
            r12 = r19
            goto L_0x0032
        L_0x0057:
            r19 = r12
            r1 = 0
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r19)     // Catch:{ InterruptedException -> 0x006e, ExecutionException -> 0x005e }
            goto L_0x007d
        L_0x005e:
            r0 = move-exception
            r2 = r0
            java.lang.String r0 = r14.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r3 = java.util.logging.Level.SEVERE
            r0.log(r3, r1, r2)
            goto L_0x007d
        L_0x006e:
            r0 = move-exception
            r2 = r0
            java.lang.String r0 = r14.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r3 = java.util.logging.Level.SEVERE
            r0.log(r3, r1, r2)
        L_0x007d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.DoubleFFT_3D.xdft3da_subth2(long, int, pl.edu.icm.jlargearrays.DoubleLargeArray, boolean):void");
    }

    private void xdft3da_subth1(int i, int i2, double[][][] dArr, boolean z) {
        Class<DoubleFFT_3D> cls = DoubleFFT_3D.class;
        int min = FastMath.min(ConcurrencyUtils.getNumberOfThreads(), this.slices);
        int i3 = this.slices;
        int i4 = this.rows;
        if (i3 < i4) {
            i3 = i4;
        }
        int i5 = i3 * 8;
        int i6 = this.columns;
        if (i6 == 4) {
            i5 >>= 1;
        } else if (i6 < 4) {
            i5 >>= 2;
        }
        int i7 = i5;
        Future[] futureArr = new Future[min];
        for (int i8 = 0; i8 < min; i8++) {
            final int i9 = i7;
            final int i10 = i2;
            final int i11 = i8;
            final int i12 = min;
            final int i13 = i;
            final double[][][] dArr2 = dArr;
            final boolean z2 = z;
            futureArr[i8] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    double[] dArr = new double[i9];
                    if (i10 == -1) {
                        for (int i = i11; i < DoubleFFT_3D.this.slices; i += i12) {
                            if (i13 == 0) {
                                for (int i2 = 0; i2 < DoubleFFT_3D.this.rows; i2++) {
                                    DoubleFFT_3D.this.fftColumns.complexForward(dArr2[i][i2]);
                                }
                            } else {
                                for (int i3 = 0; i3 < DoubleFFT_3D.this.rows; i3++) {
                                    DoubleFFT_3D.this.fftColumns.realForward(dArr2[i][i3], 0);
                                }
                            }
                            if (DoubleFFT_3D.this.columns > 4) {
                                for (int i4 = 0; i4 < DoubleFFT_3D.this.columns; i4 += 8) {
                                    for (int i5 = 0; i5 < DoubleFFT_3D.this.rows; i5++) {
                                        int i6 = i5 * 2;
                                        int access$100 = (DoubleFFT_3D.this.rows * 2) + i6;
                                        int access$1002 = (DoubleFFT_3D.this.rows * 2) + access$100;
                                        int access$1003 = (DoubleFFT_3D.this.rows * 2) + access$1002;
                                        double[] dArr2 = dArr2[i][i5];
                                        dArr[i6] = dArr2[i4];
                                        dArr[i6 + 1] = dArr2[i4 + 1];
                                        dArr[access$100] = dArr2[i4 + 2];
                                        dArr[access$100 + 1] = dArr2[i4 + 3];
                                        dArr[access$1002] = dArr2[i4 + 4];
                                        dArr[access$1002 + 1] = dArr2[i4 + 5];
                                        dArr[access$1003] = dArr2[i4 + 6];
                                        dArr[access$1003 + 1] = dArr2[i4 + 7];
                                    }
                                    DoubleFFT_3D.this.fftRows.complexForward(dArr, 0);
                                    DoubleFFT_3D.this.fftRows.complexForward(dArr, DoubleFFT_3D.this.rows * 2);
                                    DoubleFFT_3D.this.fftRows.complexForward(dArr, DoubleFFT_3D.this.rows * 4);
                                    DoubleFFT_3D.this.fftRows.complexForward(dArr, DoubleFFT_3D.this.rows * 6);
                                    for (int i7 = 0; i7 < DoubleFFT_3D.this.rows; i7++) {
                                        int i8 = i7 * 2;
                                        int access$1004 = (DoubleFFT_3D.this.rows * 2) + i8;
                                        int access$1005 = (DoubleFFT_3D.this.rows * 2) + access$1004;
                                        int access$1006 = (DoubleFFT_3D.this.rows * 2) + access$1005;
                                        double[] dArr3 = dArr2[i][i7];
                                        dArr3[i4] = dArr[i8];
                                        dArr3[i4 + 1] = dArr[i8 + 1];
                                        dArr3[i4 + 2] = dArr[access$1004];
                                        dArr3[i4 + 3] = dArr[access$1004 + 1];
                                        dArr3[i4 + 4] = dArr[access$1005];
                                        dArr3[i4 + 5] = dArr[access$1005 + 1];
                                        dArr3[i4 + 6] = dArr[access$1006];
                                        dArr3[i4 + 7] = dArr[access$1006 + 1];
                                    }
                                }
                            } else if (DoubleFFT_3D.this.columns == 4) {
                                for (int i9 = 0; i9 < DoubleFFT_3D.this.rows; i9++) {
                                    int i10 = i9 * 2;
                                    int access$1007 = (DoubleFFT_3D.this.rows * 2) + i10;
                                    double[] dArr4 = dArr2[i][i9];
                                    dArr[i10] = dArr4[0];
                                    dArr[i10 + 1] = dArr4[1];
                                    dArr[access$1007] = dArr4[2];
                                    dArr[access$1007 + 1] = dArr4[3];
                                }
                                DoubleFFT_3D.this.fftRows.complexForward(dArr, 0);
                                DoubleFFT_3D.this.fftRows.complexForward(dArr, DoubleFFT_3D.this.rows * 2);
                                for (int i11 = 0; i11 < DoubleFFT_3D.this.rows; i11++) {
                                    int i12 = i11 * 2;
                                    int access$1008 = (DoubleFFT_3D.this.rows * 2) + i12;
                                    double[] dArr5 = dArr2[i][i11];
                                    dArr5[0] = dArr[i12];
                                    dArr5[1] = dArr[i12 + 1];
                                    dArr5[2] = dArr[access$1008];
                                    dArr5[3] = dArr[access$1008 + 1];
                                }
                            } else if (DoubleFFT_3D.this.columns == 2) {
                                for (int i13 = 0; i13 < DoubleFFT_3D.this.rows; i13++) {
                                    int i14 = i13 * 2;
                                    double[] dArr6 = dArr2[i][i13];
                                    dArr[i14] = dArr6[0];
                                    dArr[i14 + 1] = dArr6[1];
                                }
                                DoubleFFT_3D.this.fftRows.complexForward(dArr, 0);
                                for (int i15 = 0; i15 < DoubleFFT_3D.this.rows; i15++) {
                                    int i16 = i15 * 2;
                                    double[] dArr7 = dArr2[i][i15];
                                    dArr7[0] = dArr[i16];
                                    dArr7[1] = dArr[i16 + 1];
                                }
                            }
                        }
                        return;
                    }
                    for (int i17 = i11; i17 < DoubleFFT_3D.this.slices; i17 += i12) {
                        if (i13 == 0) {
                            for (int i18 = 0; i18 < DoubleFFT_3D.this.rows; i18++) {
                                DoubleFFT_3D.this.fftColumns.complexInverse(dArr2[i17][i18], z2);
                            }
                        }
                        if (DoubleFFT_3D.this.columns > 4) {
                            for (int i19 = 0; i19 < DoubleFFT_3D.this.columns; i19 += 8) {
                                for (int i20 = 0; i20 < DoubleFFT_3D.this.rows; i20++) {
                                    int i21 = i20 * 2;
                                    int access$1009 = (DoubleFFT_3D.this.rows * 2) + i21;
                                    int access$10010 = (DoubleFFT_3D.this.rows * 2) + access$1009;
                                    int access$10011 = (DoubleFFT_3D.this.rows * 2) + access$10010;
                                    double[] dArr8 = dArr2[i17][i20];
                                    dArr[i21] = dArr8[i19];
                                    dArr[i21 + 1] = dArr8[i19 + 1];
                                    dArr[access$1009] = dArr8[i19 + 2];
                                    dArr[access$1009 + 1] = dArr8[i19 + 3];
                                    dArr[access$10010] = dArr8[i19 + 4];
                                    dArr[access$10010 + 1] = dArr8[i19 + 5];
                                    dArr[access$10011] = dArr8[i19 + 6];
                                    dArr[access$10011 + 1] = dArr8[i19 + 7];
                                }
                                DoubleFFT_3D.this.fftRows.complexInverse(dArr, 0, z2);
                                DoubleFFT_3D.this.fftRows.complexInverse(dArr, DoubleFFT_3D.this.rows * 2, z2);
                                DoubleFFT_3D.this.fftRows.complexInverse(dArr, DoubleFFT_3D.this.rows * 4, z2);
                                DoubleFFT_3D.this.fftRows.complexInverse(dArr, DoubleFFT_3D.this.rows * 6, z2);
                                for (int i22 = 0; i22 < DoubleFFT_3D.this.rows; i22++) {
                                    int i23 = i22 * 2;
                                    int access$10012 = (DoubleFFT_3D.this.rows * 2) + i23;
                                    int access$10013 = (DoubleFFT_3D.this.rows * 2) + access$10012;
                                    int access$10014 = (DoubleFFT_3D.this.rows * 2) + access$10013;
                                    double[] dArr9 = dArr2[i17][i22];
                                    dArr9[i19] = dArr[i23];
                                    dArr9[i19 + 1] = dArr[i23 + 1];
                                    dArr9[i19 + 2] = dArr[access$10012];
                                    dArr9[i19 + 3] = dArr[access$10012 + 1];
                                    dArr9[i19 + 4] = dArr[access$10013];
                                    dArr9[i19 + 5] = dArr[access$10013 + 1];
                                    dArr9[i19 + 6] = dArr[access$10014];
                                    dArr9[i19 + 7] = dArr[access$10014 + 1];
                                }
                            }
                        } else if (DoubleFFT_3D.this.columns == 4) {
                            for (int i24 = 0; i24 < DoubleFFT_3D.this.rows; i24++) {
                                int i25 = i24 * 2;
                                int access$10015 = (DoubleFFT_3D.this.rows * 2) + i25;
                                double[] dArr10 = dArr2[i17][i24];
                                dArr[i25] = dArr10[0];
                                dArr[i25 + 1] = dArr10[1];
                                dArr[access$10015] = dArr10[2];
                                dArr[access$10015 + 1] = dArr10[3];
                            }
                            DoubleFFT_3D.this.fftRows.complexInverse(dArr, 0, z2);
                            DoubleFFT_3D.this.fftRows.complexInverse(dArr, DoubleFFT_3D.this.rows * 2, z2);
                            for (int i26 = 0; i26 < DoubleFFT_3D.this.rows; i26++) {
                                int i27 = i26 * 2;
                                int access$10016 = (DoubleFFT_3D.this.rows * 2) + i27;
                                double[] dArr11 = dArr2[i17][i26];
                                dArr11[0] = dArr[i27];
                                dArr11[1] = dArr[i27 + 1];
                                dArr11[2] = dArr[access$10016];
                                dArr11[3] = dArr[access$10016 + 1];
                            }
                        } else if (DoubleFFT_3D.this.columns == 2) {
                            for (int i28 = 0; i28 < DoubleFFT_3D.this.rows; i28++) {
                                int i29 = i28 * 2;
                                double[] dArr12 = dArr2[i17][i28];
                                dArr[i29] = dArr12[0];
                                dArr[i29 + 1] = dArr12[1];
                            }
                            DoubleFFT_3D.this.fftRows.complexInverse(dArr, 0, z2);
                            for (int i30 = 0; i30 < DoubleFFT_3D.this.rows; i30++) {
                                int i31 = i30 * 2;
                                double[] dArr13 = dArr2[i17][i30];
                                dArr13[0] = dArr[i31];
                                dArr13[1] = dArr[i31 + 1];
                            }
                        }
                        if (i13 != 0) {
                            for (int i32 = 0; i32 < DoubleFFT_3D.this.rows; i32++) {
                                DoubleFFT_3D.this.fftColumns.realInverse(dArr2[i17][i32], z2);
                            }
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

    private void xdft3da_subth2(int i, int i2, double[][][] dArr, boolean z) {
        Class<DoubleFFT_3D> cls = DoubleFFT_3D.class;
        int min = FastMath.min(ConcurrencyUtils.getNumberOfThreads(), this.slices);
        int i3 = this.slices;
        int i4 = this.rows;
        if (i3 < i4) {
            i3 = i4;
        }
        int i5 = i3 * 8;
        int i6 = this.columns;
        if (i6 == 4) {
            i5 >>= 1;
        } else if (i6 < 4) {
            i5 >>= 2;
        }
        int i7 = i5;
        Future[] futureArr = new Future[min];
        for (int i8 = 0; i8 < min; i8++) {
            final int i9 = i7;
            final int i10 = i2;
            final int i11 = i8;
            final int i12 = min;
            final int i13 = i;
            final double[][][] dArr2 = dArr;
            final boolean z2 = z;
            futureArr[i8] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    double[] dArr = new double[i9];
                    if (i10 == -1) {
                        for (int i = i11; i < DoubleFFT_3D.this.slices; i += i12) {
                            if (i13 == 0) {
                                for (int i2 = 0; i2 < DoubleFFT_3D.this.rows; i2++) {
                                    DoubleFFT_3D.this.fftColumns.complexForward(dArr2[i][i2]);
                                }
                            } else {
                                for (int i3 = 0; i3 < DoubleFFT_3D.this.rows; i3++) {
                                    DoubleFFT_3D.this.fftColumns.realForward(dArr2[i][i3]);
                                }
                            }
                            if (DoubleFFT_3D.this.columns > 4) {
                                for (int i4 = 0; i4 < DoubleFFT_3D.this.columns; i4 += 8) {
                                    for (int i5 = 0; i5 < DoubleFFT_3D.this.rows; i5++) {
                                        int i6 = i5 * 2;
                                        int access$100 = (DoubleFFT_3D.this.rows * 2) + i6;
                                        int access$1002 = (DoubleFFT_3D.this.rows * 2) + access$100;
                                        int access$1003 = (DoubleFFT_3D.this.rows * 2) + access$1002;
                                        double[] dArr2 = dArr2[i][i5];
                                        dArr[i6] = dArr2[i4];
                                        dArr[i6 + 1] = dArr2[i4 + 1];
                                        dArr[access$100] = dArr2[i4 + 2];
                                        dArr[access$100 + 1] = dArr2[i4 + 3];
                                        dArr[access$1002] = dArr2[i4 + 4];
                                        dArr[access$1002 + 1] = dArr2[i4 + 5];
                                        dArr[access$1003] = dArr2[i4 + 6];
                                        dArr[access$1003 + 1] = dArr2[i4 + 7];
                                    }
                                    DoubleFFT_3D.this.fftRows.complexForward(dArr, 0);
                                    DoubleFFT_3D.this.fftRows.complexForward(dArr, DoubleFFT_3D.this.rows * 2);
                                    DoubleFFT_3D.this.fftRows.complexForward(dArr, DoubleFFT_3D.this.rows * 4);
                                    DoubleFFT_3D.this.fftRows.complexForward(dArr, DoubleFFT_3D.this.rows * 6);
                                    for (int i7 = 0; i7 < DoubleFFT_3D.this.rows; i7++) {
                                        int i8 = i7 * 2;
                                        int access$1004 = (DoubleFFT_3D.this.rows * 2) + i8;
                                        int access$1005 = (DoubleFFT_3D.this.rows * 2) + access$1004;
                                        int access$1006 = (DoubleFFT_3D.this.rows * 2) + access$1005;
                                        double[] dArr3 = dArr2[i][i7];
                                        dArr3[i4] = dArr[i8];
                                        dArr3[i4 + 1] = dArr[i8 + 1];
                                        dArr3[i4 + 2] = dArr[access$1004];
                                        dArr3[i4 + 3] = dArr[access$1004 + 1];
                                        dArr3[i4 + 4] = dArr[access$1005];
                                        dArr3[i4 + 5] = dArr[access$1005 + 1];
                                        dArr3[i4 + 6] = dArr[access$1006];
                                        dArr3[i4 + 7] = dArr[access$1006 + 1];
                                    }
                                }
                            } else if (DoubleFFT_3D.this.columns == 4) {
                                for (int i9 = 0; i9 < DoubleFFT_3D.this.rows; i9++) {
                                    int i10 = i9 * 2;
                                    int access$1007 = (DoubleFFT_3D.this.rows * 2) + i10;
                                    double[] dArr4 = dArr2[i][i9];
                                    dArr[i10] = dArr4[0];
                                    dArr[i10 + 1] = dArr4[1];
                                    dArr[access$1007] = dArr4[2];
                                    dArr[access$1007 + 1] = dArr4[3];
                                }
                                DoubleFFT_3D.this.fftRows.complexForward(dArr, 0);
                                DoubleFFT_3D.this.fftRows.complexForward(dArr, DoubleFFT_3D.this.rows * 2);
                                for (int i11 = 0; i11 < DoubleFFT_3D.this.rows; i11++) {
                                    int i12 = i11 * 2;
                                    int access$1008 = (DoubleFFT_3D.this.rows * 2) + i12;
                                    double[] dArr5 = dArr2[i][i11];
                                    dArr5[0] = dArr[i12];
                                    dArr5[1] = dArr[i12 + 1];
                                    dArr5[2] = dArr[access$1008];
                                    dArr5[3] = dArr[access$1008 + 1];
                                }
                            } else if (DoubleFFT_3D.this.columns == 2) {
                                for (int i13 = 0; i13 < DoubleFFT_3D.this.rows; i13++) {
                                    int i14 = i13 * 2;
                                    double[] dArr6 = dArr2[i][i13];
                                    dArr[i14] = dArr6[0];
                                    dArr[i14 + 1] = dArr6[1];
                                }
                                DoubleFFT_3D.this.fftRows.complexForward(dArr, 0);
                                for (int i15 = 0; i15 < DoubleFFT_3D.this.rows; i15++) {
                                    int i16 = i15 * 2;
                                    double[] dArr7 = dArr2[i][i15];
                                    dArr7[0] = dArr[i16];
                                    dArr7[1] = dArr[i16 + 1];
                                }
                            }
                        }
                        return;
                    }
                    for (int i17 = i11; i17 < DoubleFFT_3D.this.slices; i17 += i12) {
                        if (i13 == 0) {
                            for (int i18 = 0; i18 < DoubleFFT_3D.this.rows; i18++) {
                                DoubleFFT_3D.this.fftColumns.complexInverse(dArr2[i17][i18], z2);
                            }
                        } else {
                            for (int i19 = 0; i19 < DoubleFFT_3D.this.rows; i19++) {
                                DoubleFFT_3D.this.fftColumns.realInverse2(dArr2[i17][i19], 0, z2);
                            }
                        }
                        if (DoubleFFT_3D.this.columns > 4) {
                            for (int i20 = 0; i20 < DoubleFFT_3D.this.columns; i20 += 8) {
                                for (int i21 = 0; i21 < DoubleFFT_3D.this.rows; i21++) {
                                    int i22 = i21 * 2;
                                    int access$1009 = (DoubleFFT_3D.this.rows * 2) + i22;
                                    int access$10010 = (DoubleFFT_3D.this.rows * 2) + access$1009;
                                    int access$10011 = (DoubleFFT_3D.this.rows * 2) + access$10010;
                                    double[] dArr8 = dArr2[i17][i21];
                                    dArr[i22] = dArr8[i20];
                                    dArr[i22 + 1] = dArr8[i20 + 1];
                                    dArr[access$1009] = dArr8[i20 + 2];
                                    dArr[access$1009 + 1] = dArr8[i20 + 3];
                                    dArr[access$10010] = dArr8[i20 + 4];
                                    dArr[access$10010 + 1] = dArr8[i20 + 5];
                                    dArr[access$10011] = dArr8[i20 + 6];
                                    dArr[access$10011 + 1] = dArr8[i20 + 7];
                                }
                                DoubleFFT_3D.this.fftRows.complexInverse(dArr, 0, z2);
                                DoubleFFT_3D.this.fftRows.complexInverse(dArr, DoubleFFT_3D.this.rows * 2, z2);
                                DoubleFFT_3D.this.fftRows.complexInverse(dArr, DoubleFFT_3D.this.rows * 4, z2);
                                DoubleFFT_3D.this.fftRows.complexInverse(dArr, DoubleFFT_3D.this.rows * 6, z2);
                                for (int i23 = 0; i23 < DoubleFFT_3D.this.rows; i23++) {
                                    int i24 = i23 * 2;
                                    int access$10012 = (DoubleFFT_3D.this.rows * 2) + i24;
                                    int access$10013 = (DoubleFFT_3D.this.rows * 2) + access$10012;
                                    int access$10014 = (DoubleFFT_3D.this.rows * 2) + access$10013;
                                    double[] dArr9 = dArr2[i17][i23];
                                    dArr9[i20] = dArr[i24];
                                    dArr9[i20 + 1] = dArr[i24 + 1];
                                    dArr9[i20 + 2] = dArr[access$10012];
                                    dArr9[i20 + 3] = dArr[access$10012 + 1];
                                    dArr9[i20 + 4] = dArr[access$10013];
                                    dArr9[i20 + 5] = dArr[access$10013 + 1];
                                    dArr9[i20 + 6] = dArr[access$10014];
                                    dArr9[i20 + 7] = dArr[access$10014 + 1];
                                }
                            }
                        } else if (DoubleFFT_3D.this.columns == 4) {
                            for (int i25 = 0; i25 < DoubleFFT_3D.this.rows; i25++) {
                                int i26 = i25 * 2;
                                int access$10015 = (DoubleFFT_3D.this.rows * 2) + i26;
                                double[] dArr10 = dArr2[i17][i25];
                                dArr[i26] = dArr10[0];
                                dArr[i26 + 1] = dArr10[1];
                                dArr[access$10015] = dArr10[2];
                                dArr[access$10015 + 1] = dArr10[3];
                            }
                            DoubleFFT_3D.this.fftRows.complexInverse(dArr, 0, z2);
                            DoubleFFT_3D.this.fftRows.complexInverse(dArr, DoubleFFT_3D.this.rows * 2, z2);
                            for (int i27 = 0; i27 < DoubleFFT_3D.this.rows; i27++) {
                                int i28 = i27 * 2;
                                int access$10016 = (DoubleFFT_3D.this.rows * 2) + i28;
                                double[] dArr11 = dArr2[i17][i27];
                                dArr11[0] = dArr[i28];
                                dArr11[1] = dArr[i28 + 1];
                                dArr11[2] = dArr[access$10016];
                                dArr11[3] = dArr[access$10016 + 1];
                            }
                        } else if (DoubleFFT_3D.this.columns == 2) {
                            for (int i29 = 0; i29 < DoubleFFT_3D.this.rows; i29++) {
                                int i30 = i29 * 2;
                                double[] dArr12 = dArr2[i17][i29];
                                dArr[i30] = dArr12[0];
                                dArr[i30 + 1] = dArr12[1];
                            }
                            DoubleFFT_3D.this.fftRows.complexInverse(dArr, 0, z2);
                            for (int i31 = 0; i31 < DoubleFFT_3D.this.rows; i31++) {
                                int i32 = i31 * 2;
                                double[] dArr13 = dArr2[i17][i31];
                                dArr13[0] = dArr[i32];
                                dArr13[1] = dArr[i32 + 1];
                            }
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

    private void cdft3db_subth(int i, double[] dArr, boolean z) {
        Class<DoubleFFT_3D> cls = DoubleFFT_3D.class;
        int min = FastMath.min(ConcurrencyUtils.getNumberOfThreads(), this.rows);
        int i2 = this.slices;
        int i3 = this.rows;
        if (i2 < i3) {
            i2 = i3;
        }
        int i4 = i2 * 8;
        int i5 = this.columns;
        if (i5 == 4) {
            i4 >>= 1;
        } else if (i5 < 4) {
            i4 >>= 2;
        }
        int i6 = i4;
        Future[] futureArr = new Future[min];
        for (int i7 = 0; i7 < min; i7++) {
            final int i8 = i6;
            final int i9 = i;
            final int i10 = i7;
            final int i11 = min;
            final double[] dArr2 = dArr;
            final boolean z2 = z;
            futureArr[i7] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    double[] dArr = new double[i8];
                    if (i9 == -1) {
                        if (DoubleFFT_3D.this.columns > 4) {
                            for (int i = i10; i < DoubleFFT_3D.this.rows; i += i11) {
                                int access$200 = DoubleFFT_3D.this.rowStride * i;
                                for (int i2 = 0; i2 < DoubleFFT_3D.this.columns; i2 += 8) {
                                    for (int i3 = 0; i3 < DoubleFFT_3D.this.slices; i3++) {
                                        int access$000 = (DoubleFFT_3D.this.sliceStride * i3) + access$200 + i2;
                                        int i4 = i3 * 2;
                                        int access$600 = (DoubleFFT_3D.this.slices * 2) + i4;
                                        int access$6002 = (DoubleFFT_3D.this.slices * 2) + access$600;
                                        int access$6003 = (DoubleFFT_3D.this.slices * 2) + access$6002;
                                        double[] dArr2 = dArr2;
                                        dArr[i4] = dArr2[access$000];
                                        dArr[i4 + 1] = dArr2[access$000 + 1];
                                        dArr[access$600] = dArr2[access$000 + 2];
                                        dArr[access$600 + 1] = dArr2[access$000 + 3];
                                        dArr[access$6002] = dArr2[access$000 + 4];
                                        dArr[access$6002 + 1] = dArr2[access$000 + 5];
                                        dArr[access$6003] = dArr2[access$000 + 6];
                                        dArr[access$6003 + 1] = dArr2[access$000 + 7];
                                    }
                                    DoubleFFT_3D.this.fftSlices.complexForward(dArr, 0);
                                    DoubleFFT_3D.this.fftSlices.complexForward(dArr, DoubleFFT_3D.this.slices * 2);
                                    DoubleFFT_3D.this.fftSlices.complexForward(dArr, DoubleFFT_3D.this.slices * 4);
                                    DoubleFFT_3D.this.fftSlices.complexForward(dArr, DoubleFFT_3D.this.slices * 6);
                                    for (int i5 = 0; i5 < DoubleFFT_3D.this.slices; i5++) {
                                        int access$0002 = (DoubleFFT_3D.this.sliceStride * i5) + access$200 + i2;
                                        int i6 = i5 * 2;
                                        int access$6004 = (DoubleFFT_3D.this.slices * 2) + i6;
                                        int access$6005 = (DoubleFFT_3D.this.slices * 2) + access$6004;
                                        int access$6006 = (DoubleFFT_3D.this.slices * 2) + access$6005;
                                        double[] dArr3 = dArr2;
                                        dArr3[access$0002] = dArr[i6];
                                        dArr3[access$0002 + 1] = dArr[i6 + 1];
                                        dArr3[access$0002 + 2] = dArr[access$6004];
                                        dArr3[access$0002 + 3] = dArr[access$6004 + 1];
                                        dArr3[access$0002 + 4] = dArr[access$6005];
                                        dArr3[access$0002 + 5] = dArr[access$6005 + 1];
                                        dArr3[access$0002 + 6] = dArr[access$6006];
                                        dArr3[access$0002 + 7] = dArr[access$6006 + 1];
                                    }
                                }
                            }
                        } else if (DoubleFFT_3D.this.columns == 4) {
                            for (int i7 = i10; i7 < DoubleFFT_3D.this.rows; i7 += i11) {
                                int access$2002 = DoubleFFT_3D.this.rowStride * i7;
                                for (int i8 = 0; i8 < DoubleFFT_3D.this.slices; i8++) {
                                    int access$0003 = (DoubleFFT_3D.this.sliceStride * i8) + access$2002;
                                    int i9 = i8 * 2;
                                    int access$6007 = (DoubleFFT_3D.this.slices * 2) + i9;
                                    double[] dArr4 = dArr2;
                                    dArr[i9] = dArr4[access$0003];
                                    dArr[i9 + 1] = dArr4[access$0003 + 1];
                                    dArr[access$6007] = dArr4[access$0003 + 2];
                                    dArr[access$6007 + 1] = dArr4[access$0003 + 3];
                                }
                                DoubleFFT_3D.this.fftSlices.complexForward(dArr, 0);
                                DoubleFFT_3D.this.fftSlices.complexForward(dArr, DoubleFFT_3D.this.slices * 2);
                                for (int i10 = 0; i10 < DoubleFFT_3D.this.slices; i10++) {
                                    int access$0004 = (DoubleFFT_3D.this.sliceStride * i10) + access$2002;
                                    int i11 = i10 * 2;
                                    int access$6008 = (DoubleFFT_3D.this.slices * 2) + i11;
                                    double[] dArr5 = dArr2;
                                    dArr5[access$0004] = dArr[i11];
                                    dArr5[access$0004 + 1] = dArr[i11 + 1];
                                    dArr5[access$0004 + 2] = dArr[access$6008];
                                    dArr5[access$0004 + 3] = dArr[access$6008 + 1];
                                }
                            }
                        } else if (DoubleFFT_3D.this.columns == 2) {
                            for (int i12 = i10; i12 < DoubleFFT_3D.this.rows; i12 += i11) {
                                int access$2003 = DoubleFFT_3D.this.rowStride * i12;
                                for (int i13 = 0; i13 < DoubleFFT_3D.this.slices; i13++) {
                                    int access$0005 = (DoubleFFT_3D.this.sliceStride * i13) + access$2003;
                                    int i14 = i13 * 2;
                                    double[] dArr6 = dArr2;
                                    dArr[i14] = dArr6[access$0005];
                                    dArr[i14 + 1] = dArr6[access$0005 + 1];
                                }
                                DoubleFFT_3D.this.fftSlices.complexForward(dArr, 0);
                                for (int i15 = 0; i15 < DoubleFFT_3D.this.slices; i15++) {
                                    int access$0006 = (DoubleFFT_3D.this.sliceStride * i15) + access$2003;
                                    int i16 = i15 * 2;
                                    double[] dArr7 = dArr2;
                                    dArr7[access$0006] = dArr[i16];
                                    dArr7[access$0006 + 1] = dArr[i16 + 1];
                                }
                            }
                        }
                    } else if (DoubleFFT_3D.this.columns > 4) {
                        for (int i17 = i10; i17 < DoubleFFT_3D.this.rows; i17 += i11) {
                            int access$2004 = DoubleFFT_3D.this.rowStride * i17;
                            for (int i18 = 0; i18 < DoubleFFT_3D.this.columns; i18 += 8) {
                                for (int i19 = 0; i19 < DoubleFFT_3D.this.slices; i19++) {
                                    int access$0007 = (DoubleFFT_3D.this.sliceStride * i19) + access$2004 + i18;
                                    int i20 = i19 * 2;
                                    int access$6009 = (DoubleFFT_3D.this.slices * 2) + i20;
                                    int access$60010 = (DoubleFFT_3D.this.slices * 2) + access$6009;
                                    int access$60011 = (DoubleFFT_3D.this.slices * 2) + access$60010;
                                    double[] dArr8 = dArr2;
                                    dArr[i20] = dArr8[access$0007];
                                    dArr[i20 + 1] = dArr8[access$0007 + 1];
                                    dArr[access$6009] = dArr8[access$0007 + 2];
                                    dArr[access$6009 + 1] = dArr8[access$0007 + 3];
                                    dArr[access$60010] = dArr8[access$0007 + 4];
                                    dArr[access$60010 + 1] = dArr8[access$0007 + 5];
                                    dArr[access$60011] = dArr8[access$0007 + 6];
                                    dArr[access$60011 + 1] = dArr8[access$0007 + 7];
                                }
                                DoubleFFT_3D.this.fftSlices.complexInverse(dArr, 0, z2);
                                DoubleFFT_3D.this.fftSlices.complexInverse(dArr, DoubleFFT_3D.this.slices * 2, z2);
                                DoubleFFT_3D.this.fftSlices.complexInverse(dArr, DoubleFFT_3D.this.slices * 4, z2);
                                DoubleFFT_3D.this.fftSlices.complexInverse(dArr, DoubleFFT_3D.this.slices * 6, z2);
                                for (int i21 = 0; i21 < DoubleFFT_3D.this.slices; i21++) {
                                    int access$0008 = (DoubleFFT_3D.this.sliceStride * i21) + access$2004 + i18;
                                    int i22 = i21 * 2;
                                    int access$60012 = (DoubleFFT_3D.this.slices * 2) + i22;
                                    int access$60013 = (DoubleFFT_3D.this.slices * 2) + access$60012;
                                    int access$60014 = (DoubleFFT_3D.this.slices * 2) + access$60013;
                                    double[] dArr9 = dArr2;
                                    dArr9[access$0008] = dArr[i22];
                                    dArr9[access$0008 + 1] = dArr[i22 + 1];
                                    dArr9[access$0008 + 2] = dArr[access$60012];
                                    dArr9[access$0008 + 3] = dArr[access$60012 + 1];
                                    dArr9[access$0008 + 4] = dArr[access$60013];
                                    dArr9[access$0008 + 5] = dArr[access$60013 + 1];
                                    dArr9[access$0008 + 6] = dArr[access$60014];
                                    dArr9[access$0008 + 7] = dArr[access$60014 + 1];
                                }
                            }
                        }
                    } else if (DoubleFFT_3D.this.columns == 4) {
                        for (int i23 = i10; i23 < DoubleFFT_3D.this.rows; i23 += i11) {
                            int access$2005 = DoubleFFT_3D.this.rowStride * i23;
                            for (int i24 = 0; i24 < DoubleFFT_3D.this.slices; i24++) {
                                int access$0009 = (DoubleFFT_3D.this.sliceStride * i24) + access$2005;
                                int i25 = i24 * 2;
                                int access$60015 = (DoubleFFT_3D.this.slices * 2) + i25;
                                double[] dArr10 = dArr2;
                                dArr[i25] = dArr10[access$0009];
                                dArr[i25 + 1] = dArr10[access$0009 + 1];
                                dArr[access$60015] = dArr10[access$0009 + 2];
                                dArr[access$60015 + 1] = dArr10[access$0009 + 3];
                            }
                            DoubleFFT_3D.this.fftSlices.complexInverse(dArr, 0, z2);
                            DoubleFFT_3D.this.fftSlices.complexInverse(dArr, DoubleFFT_3D.this.slices * 2, z2);
                            for (int i26 = 0; i26 < DoubleFFT_3D.this.slices; i26++) {
                                int access$00010 = (DoubleFFT_3D.this.sliceStride * i26) + access$2005;
                                int i27 = i26 * 2;
                                int access$60016 = (DoubleFFT_3D.this.slices * 2) + i27;
                                double[] dArr11 = dArr2;
                                dArr11[access$00010] = dArr[i27];
                                dArr11[access$00010 + 1] = dArr[i27 + 1];
                                dArr11[access$00010 + 2] = dArr[access$60016];
                                dArr11[access$00010 + 3] = dArr[access$60016 + 1];
                            }
                        }
                    } else if (DoubleFFT_3D.this.columns == 2) {
                        for (int i28 = i10; i28 < DoubleFFT_3D.this.rows; i28 += i11) {
                            int access$2006 = DoubleFFT_3D.this.rowStride * i28;
                            for (int i29 = 0; i29 < DoubleFFT_3D.this.slices; i29++) {
                                int access$00011 = (DoubleFFT_3D.this.sliceStride * i29) + access$2006;
                                int i30 = i29 * 2;
                                double[] dArr12 = dArr2;
                                dArr[i30] = dArr12[access$00011];
                                dArr[i30 + 1] = dArr12[access$00011 + 1];
                            }
                            DoubleFFT_3D.this.fftSlices.complexInverse(dArr, 0, z2);
                            for (int i31 = 0; i31 < DoubleFFT_3D.this.slices; i31++) {
                                int access$00012 = (DoubleFFT_3D.this.sliceStride * i31) + access$2006;
                                int i32 = i31 * 2;
                                double[] dArr13 = dArr2;
                                dArr13[access$00012] = dArr[i32];
                                dArr13[access$00012 + 1] = dArr[i32 + 1];
                            }
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

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0034 A[LOOP:0: B:11:0x0032->B:12:0x0034, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void cdft3db_subth(int r19, pl.edu.icm.jlargearrays.DoubleLargeArray r20, boolean r21) {
        /*
            r18 = this;
            r11 = r18
            java.lang.Class<org.jtransforms.fft.DoubleFFT_3D> r12 = org.jtransforms.fft.DoubleFFT_3D.class
            int r0 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getNumberOfThreads()
            long r0 = (long) r0
            long r2 = r11.rowsl
            long r0 = org.apache.commons.math3.util.FastMath.min((long) r0, (long) r2)
            int r0 = (int) r0
            long r1 = r11.slicesl
            long r3 = r11.rowsl
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 >= 0) goto L_0x0019
            r1 = r3
        L_0x0019:
            r3 = 8
            long r1 = r1 * r3
            long r3 = r11.columnsl
            r5 = 4
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 != 0) goto L_0x0027
            r3 = 1
        L_0x0025:
            long r1 = r1 >> r3
            goto L_0x002d
        L_0x0027:
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 >= 0) goto L_0x002d
            r3 = 2
            goto L_0x0025
        L_0x002d:
            r13 = r1
            java.util.concurrent.Future[] r15 = new java.util.concurrent.Future[r0]
            r1 = 0
            r10 = r1
        L_0x0032:
            if (r10 >= r0) goto L_0x0051
            long r6 = (long) r10
            org.jtransforms.fft.DoubleFFT_3D$58 r16 = new org.jtransforms.fft.DoubleFFT_3D$58
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
            goto L_0x0032
        L_0x0051:
            r1 = 0
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r15)     // Catch:{ InterruptedException -> 0x0066, ExecutionException -> 0x0056 }
            goto L_0x0075
        L_0x0056:
            r0 = move-exception
            r2 = r0
            java.lang.String r0 = r12.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r3 = java.util.logging.Level.SEVERE
            r0.log(r3, r1, r2)
            goto L_0x0075
        L_0x0066:
            r0 = move-exception
            r2 = r0
            java.lang.String r0 = r12.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r3 = java.util.logging.Level.SEVERE
            r0.log(r3, r1, r2)
        L_0x0075:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.DoubleFFT_3D.cdft3db_subth(int, pl.edu.icm.jlargearrays.DoubleLargeArray, boolean):void");
    }

    private void cdft3db_subth(int i, double[][][] dArr, boolean z) {
        Class<DoubleFFT_3D> cls = DoubleFFT_3D.class;
        int min = FastMath.min(ConcurrencyUtils.getNumberOfThreads(), this.rows);
        int i2 = this.slices;
        int i3 = this.rows;
        if (i2 < i3) {
            i2 = i3;
        }
        int i4 = i2 * 8;
        int i5 = this.columns;
        if (i5 == 4) {
            i4 >>= 1;
        } else if (i5 < 4) {
            i4 >>= 2;
        }
        int i6 = i4;
        Future[] futureArr = new Future[min];
        for (int i7 = 0; i7 < min; i7++) {
            final int i8 = i6;
            final int i9 = i;
            final int i10 = i7;
            final int i11 = min;
            final double[][][] dArr2 = dArr;
            final boolean z2 = z;
            futureArr[i7] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    double[] dArr = new double[i8];
                    if (i9 == -1) {
                        if (DoubleFFT_3D.this.columns > 4) {
                            for (int i = i10; i < DoubleFFT_3D.this.rows; i += i11) {
                                for (int i2 = 0; i2 < DoubleFFT_3D.this.columns; i2 += 8) {
                                    for (int i3 = 0; i3 < DoubleFFT_3D.this.slices; i3++) {
                                        int i4 = i3 * 2;
                                        int access$600 = (DoubleFFT_3D.this.slices * 2) + i4;
                                        int access$6002 = (DoubleFFT_3D.this.slices * 2) + access$600;
                                        int access$6003 = (DoubleFFT_3D.this.slices * 2) + access$6002;
                                        double[] dArr2 = dArr2[i3][i];
                                        dArr[i4] = dArr2[i2];
                                        dArr[i4 + 1] = dArr2[i2 + 1];
                                        dArr[access$600] = dArr2[i2 + 2];
                                        dArr[access$600 + 1] = dArr2[i2 + 3];
                                        dArr[access$6002] = dArr2[i2 + 4];
                                        dArr[access$6002 + 1] = dArr2[i2 + 5];
                                        dArr[access$6003] = dArr2[i2 + 6];
                                        dArr[access$6003 + 1] = dArr2[i2 + 7];
                                    }
                                    DoubleFFT_3D.this.fftSlices.complexForward(dArr, 0);
                                    DoubleFFT_3D.this.fftSlices.complexForward(dArr, DoubleFFT_3D.this.slices * 2);
                                    DoubleFFT_3D.this.fftSlices.complexForward(dArr, DoubleFFT_3D.this.slices * 4);
                                    DoubleFFT_3D.this.fftSlices.complexForward(dArr, DoubleFFT_3D.this.slices * 6);
                                    for (int i5 = 0; i5 < DoubleFFT_3D.this.slices; i5++) {
                                        int i6 = i5 * 2;
                                        int access$6004 = (DoubleFFT_3D.this.slices * 2) + i6;
                                        int access$6005 = (DoubleFFT_3D.this.slices * 2) + access$6004;
                                        int access$6006 = (DoubleFFT_3D.this.slices * 2) + access$6005;
                                        double[] dArr3 = dArr2[i5][i];
                                        dArr3[i2] = dArr[i6];
                                        dArr3[i2 + 1] = dArr[i6 + 1];
                                        dArr3[i2 + 2] = dArr[access$6004];
                                        dArr3[i2 + 3] = dArr[access$6004 + 1];
                                        dArr3[i2 + 4] = dArr[access$6005];
                                        dArr3[i2 + 5] = dArr[access$6005 + 1];
                                        dArr3[i2 + 6] = dArr[access$6006];
                                        dArr3[i2 + 7] = dArr[access$6006 + 1];
                                    }
                                }
                            }
                        } else if (DoubleFFT_3D.this.columns == 4) {
                            for (int i7 = i10; i7 < DoubleFFT_3D.this.rows; i7 += i11) {
                                for (int i8 = 0; i8 < DoubleFFT_3D.this.slices; i8++) {
                                    int i9 = i8 * 2;
                                    int access$6007 = (DoubleFFT_3D.this.slices * 2) + i9;
                                    double[] dArr4 = dArr2[i8][i7];
                                    dArr[i9] = dArr4[0];
                                    dArr[i9 + 1] = dArr4[1];
                                    dArr[access$6007] = dArr4[2];
                                    dArr[access$6007 + 1] = dArr4[3];
                                }
                                DoubleFFT_3D.this.fftSlices.complexForward(dArr, 0);
                                DoubleFFT_3D.this.fftSlices.complexForward(dArr, DoubleFFT_3D.this.slices * 2);
                                for (int i10 = 0; i10 < DoubleFFT_3D.this.slices; i10++) {
                                    int i11 = i10 * 2;
                                    int access$6008 = (DoubleFFT_3D.this.slices * 2) + i11;
                                    double[] dArr5 = dArr2[i10][i7];
                                    dArr5[0] = dArr[i11];
                                    dArr5[1] = dArr[i11 + 1];
                                    dArr5[2] = dArr[access$6008];
                                    dArr5[3] = dArr[access$6008 + 1];
                                }
                            }
                        } else if (DoubleFFT_3D.this.columns == 2) {
                            for (int i12 = i10; i12 < DoubleFFT_3D.this.rows; i12 += i11) {
                                for (int i13 = 0; i13 < DoubleFFT_3D.this.slices; i13++) {
                                    int i14 = i13 * 2;
                                    double[] dArr6 = dArr2[i13][i12];
                                    dArr[i14] = dArr6[0];
                                    dArr[i14 + 1] = dArr6[1];
                                }
                                DoubleFFT_3D.this.fftSlices.complexForward(dArr, 0);
                                for (int i15 = 0; i15 < DoubleFFT_3D.this.slices; i15++) {
                                    int i16 = i15 * 2;
                                    double[] dArr7 = dArr2[i15][i12];
                                    dArr7[0] = dArr[i16];
                                    dArr7[1] = dArr[i16 + 1];
                                }
                            }
                        }
                    } else if (DoubleFFT_3D.this.columns > 4) {
                        for (int i17 = i10; i17 < DoubleFFT_3D.this.rows; i17 += i11) {
                            for (int i18 = 0; i18 < DoubleFFT_3D.this.columns; i18 += 8) {
                                for (int i19 = 0; i19 < DoubleFFT_3D.this.slices; i19++) {
                                    int i20 = i19 * 2;
                                    int access$6009 = (DoubleFFT_3D.this.slices * 2) + i20;
                                    int access$60010 = (DoubleFFT_3D.this.slices * 2) + access$6009;
                                    int access$60011 = (DoubleFFT_3D.this.slices * 2) + access$60010;
                                    double[] dArr8 = dArr2[i19][i17];
                                    dArr[i20] = dArr8[i18];
                                    dArr[i20 + 1] = dArr8[i18 + 1];
                                    dArr[access$6009] = dArr8[i18 + 2];
                                    dArr[access$6009 + 1] = dArr8[i18 + 3];
                                    dArr[access$60010] = dArr8[i18 + 4];
                                    dArr[access$60010 + 1] = dArr8[i18 + 5];
                                    dArr[access$60011] = dArr8[i18 + 6];
                                    dArr[access$60011 + 1] = dArr8[i18 + 7];
                                }
                                DoubleFFT_3D.this.fftSlices.complexInverse(dArr, 0, z2);
                                DoubleFFT_3D.this.fftSlices.complexInverse(dArr, DoubleFFT_3D.this.slices * 2, z2);
                                DoubleFFT_3D.this.fftSlices.complexInverse(dArr, DoubleFFT_3D.this.slices * 4, z2);
                                DoubleFFT_3D.this.fftSlices.complexInverse(dArr, DoubleFFT_3D.this.slices * 6, z2);
                                for (int i21 = 0; i21 < DoubleFFT_3D.this.slices; i21++) {
                                    int i22 = i21 * 2;
                                    int access$60012 = (DoubleFFT_3D.this.slices * 2) + i22;
                                    int access$60013 = (DoubleFFT_3D.this.slices * 2) + access$60012;
                                    int access$60014 = (DoubleFFT_3D.this.slices * 2) + access$60013;
                                    double[] dArr9 = dArr2[i21][i17];
                                    dArr9[i18] = dArr[i22];
                                    dArr9[i18 + 1] = dArr[i22 + 1];
                                    dArr9[i18 + 2] = dArr[access$60012];
                                    dArr9[i18 + 3] = dArr[access$60012 + 1];
                                    dArr9[i18 + 4] = dArr[access$60013];
                                    dArr9[i18 + 5] = dArr[access$60013 + 1];
                                    dArr9[i18 + 6] = dArr[access$60014];
                                    dArr9[i18 + 7] = dArr[access$60014 + 1];
                                }
                            }
                        }
                    } else if (DoubleFFT_3D.this.columns == 4) {
                        for (int i23 = i10; i23 < DoubleFFT_3D.this.rows; i23 += i11) {
                            for (int i24 = 0; i24 < DoubleFFT_3D.this.slices; i24++) {
                                int i25 = i24 * 2;
                                int access$60015 = (DoubleFFT_3D.this.slices * 2) + i25;
                                double[] dArr10 = dArr2[i24][i23];
                                dArr[i25] = dArr10[0];
                                dArr[i25 + 1] = dArr10[1];
                                dArr[access$60015] = dArr10[2];
                                dArr[access$60015 + 1] = dArr10[3];
                            }
                            DoubleFFT_3D.this.fftSlices.complexInverse(dArr, 0, z2);
                            DoubleFFT_3D.this.fftSlices.complexInverse(dArr, DoubleFFT_3D.this.slices * 2, z2);
                            for (int i26 = 0; i26 < DoubleFFT_3D.this.slices; i26++) {
                                int i27 = i26 * 2;
                                int access$60016 = (DoubleFFT_3D.this.slices * 2) + i27;
                                double[] dArr11 = dArr2[i26][i23];
                                dArr11[0] = dArr[i27];
                                dArr11[1] = dArr[i27 + 1];
                                dArr11[2] = dArr[access$60016];
                                dArr11[3] = dArr[access$60016 + 1];
                            }
                        }
                    } else if (DoubleFFT_3D.this.columns == 2) {
                        for (int i28 = i10; i28 < DoubleFFT_3D.this.rows; i28 += i11) {
                            for (int i29 = 0; i29 < DoubleFFT_3D.this.slices; i29++) {
                                int i30 = i29 * 2;
                                double[] dArr12 = dArr2[i29][i28];
                                dArr[i30] = dArr12[0];
                                dArr[i30 + 1] = dArr12[1];
                            }
                            DoubleFFT_3D.this.fftSlices.complexInverse(dArr, 0, z2);
                            for (int i31 = 0; i31 < DoubleFFT_3D.this.slices; i31++) {
                                int i32 = i31 * 2;
                                double[] dArr13 = dArr2[i31][i28];
                                dArr13[0] = dArr[i32];
                                dArr13[1] = dArr[i32 + 1];
                            }
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

    private void rdft3d_sub(int i, double[] dArr) {
        int i2 = this.slices >> 1;
        int i3 = this.rows >> 1;
        if (i < 0) {
            for (int i4 = 1; i4 < i2; i4++) {
                int i5 = this.slices - i4;
                int i6 = this.sliceStride;
                int i7 = i4 * i6;
                int i8 = i5 * i6;
                int i9 = this.rowStride;
                int i10 = (i4 * i6) + (i3 * i9);
                int i11 = (i6 * i5) + (i9 * i3);
                double d = dArr[i7];
                double d2 = dArr[i8];
                dArr[i7] = d + d2;
                dArr[i8] = d - d2;
                int i12 = i8 + 1;
                double d3 = dArr[i12];
                int i13 = i7 + 1;
                double d4 = dArr[i13];
                dArr[i13] = d4 + d3;
                dArr[i12] = d3 - d4;
                double d5 = dArr[i10];
                double d6 = dArr[i11];
                dArr[i10] = d5 + d6;
                dArr[i11] = d5 - d6;
                int i14 = i11 + 1;
                double d7 = dArr[i14];
                int i15 = i10 + 1;
                double d8 = dArr[i15];
                dArr[i15] = d8 + d7;
                dArr[i14] = d7 - d8;
                for (int i16 = 1; i16 < i3; i16++) {
                    int i17 = this.rows - i16;
                    int i18 = this.sliceStride;
                    int i19 = this.rowStride;
                    int i20 = (i4 * i18) + (i16 * i19);
                    int i21 = (i5 * i18) + (i17 * i19);
                    double d9 = dArr[i20];
                    double d10 = dArr[i21];
                    dArr[i20] = d9 + d10;
                    dArr[i21] = d9 - d10;
                    int i22 = i21 + 1;
                    double d11 = dArr[i22];
                    int i23 = i20 + 1;
                    double d12 = dArr[i23];
                    dArr[i23] = d12 + d11;
                    dArr[i22] = d11 - d12;
                    int i24 = (i5 * i18) + (i16 * i19);
                    int i25 = (i18 * i4) + (i17 * i19);
                    double d13 = dArr[i24];
                    double d14 = dArr[i25];
                    dArr[i24] = d13 + d14;
                    dArr[i25] = d13 - d14;
                    int i26 = i25 + 1;
                    double d15 = dArr[i26];
                    int i27 = i24 + 1;
                    double d16 = dArr[i27];
                    dArr[i27] = d16 + d15;
                    dArr[i26] = d15 - d16;
                }
            }
            for (int i28 = 1; i28 < i3; i28++) {
                int i29 = this.rows - i28;
                int i30 = this.rowStride;
                int i31 = i28 * i30;
                int i32 = i29 * i30;
                double d17 = dArr[i31];
                double d18 = dArr[i32];
                dArr[i31] = d17 + d18;
                dArr[i32] = d17 - d18;
                int i33 = i32 + 1;
                double d19 = dArr[i33];
                int i34 = i31 + 1;
                double d20 = dArr[i34];
                dArr[i34] = d20 + d19;
                dArr[i33] = d19 - d20;
                int i35 = this.sliceStride;
                int i36 = (i2 * i35) + (i28 * i30);
                int i37 = (i35 * i2) + (i29 * i30);
                double d21 = dArr[i36];
                double d22 = dArr[i37];
                dArr[i36] = d21 + d22;
                dArr[i37] = d21 - d22;
                int i38 = i37 + 1;
                double d23 = dArr[i38];
                int i39 = i36 + 1;
                double d24 = dArr[i39];
                dArr[i39] = d24 + d23;
                dArr[i38] = d23 - d24;
            }
            return;
        }
        for (int i40 = 1; i40 < i2; i40++) {
            int i41 = this.slices - i40;
            int i42 = this.sliceStride;
            int i43 = i41 * i42;
            int i44 = i40 * i42;
            double d25 = (dArr[i44] - dArr[i43]) * 0.5d;
            dArr[i43] = d25;
            dArr[i44] = dArr[i44] - d25;
            int i45 = i43 + 1;
            int i46 = i44 + 1;
            double d26 = (dArr[i46] + dArr[i45]) * 0.5d;
            dArr[i45] = d26;
            dArr[i46] = dArr[i46] - d26;
            int i47 = this.rowStride;
            int i48 = (i41 * i42) + (i3 * i47);
            int i49 = (i42 * i40) + (i47 * i3);
            double d27 = (dArr[i49] - dArr[i48]) * 0.5d;
            dArr[i48] = d27;
            dArr[i49] = dArr[i49] - d27;
            int i50 = i48 + 1;
            int i51 = i49 + 1;
            double d28 = (dArr[i51] + dArr[i50]) * 0.5d;
            dArr[i50] = d28;
            dArr[i51] = dArr[i51] - d28;
            for (int i52 = 1; i52 < i3; i52++) {
                int i53 = this.rows - i52;
                int i54 = this.sliceStride;
                int i55 = this.rowStride;
                int i56 = (i41 * i54) + (i53 * i55);
                int i57 = (i40 * i54) + (i52 * i55);
                double d29 = (dArr[i57] - dArr[i56]) * 0.5d;
                dArr[i56] = d29;
                dArr[i57] = dArr[i57] - d29;
                int i58 = i56 + 1;
                int i59 = i57 + 1;
                double d30 = (dArr[i59] + dArr[i58]) * 0.5d;
                dArr[i58] = d30;
                dArr[i59] = dArr[i59] - d30;
                int i60 = (i40 * i54) + (i53 * i55);
                int i61 = (i54 * i41) + (i55 * i52);
                double d31 = (dArr[i61] - dArr[i60]) * 0.5d;
                dArr[i60] = d31;
                dArr[i61] = dArr[i61] - d31;
                int i62 = i60 + 1;
                int i63 = i61 + 1;
                double d32 = (dArr[i63] + dArr[i62]) * 0.5d;
                dArr[i62] = d32;
                dArr[i63] = dArr[i63] - d32;
            }
        }
        for (int i64 = 1; i64 < i3; i64++) {
            int i65 = this.rows - i64;
            int i66 = this.rowStride;
            int i67 = i65 * i66;
            int i68 = i64 * i66;
            double d33 = (dArr[i68] - dArr[i67]) * 0.5d;
            dArr[i67] = d33;
            dArr[i68] = dArr[i68] - d33;
            int i69 = i67 + 1;
            int i70 = i68 + 1;
            double d34 = (dArr[i70] + dArr[i69]) * 0.5d;
            dArr[i69] = d34;
            dArr[i70] = dArr[i70] - d34;
            int i71 = this.sliceStride;
            int i72 = (i2 * i71) + (i65 * i66);
            int i73 = (i71 * i2) + (i66 * i64);
            double d35 = (dArr[i73] - dArr[i72]) * 0.5d;
            dArr[i72] = d35;
            dArr[i73] = dArr[i73] - d35;
            int i74 = i72 + 1;
            int i75 = i73 + 1;
            double d36 = (dArr[i75] + dArr[i74]) * 0.5d;
            dArr[i74] = d36;
            dArr[i75] = dArr[i75] - d36;
        }
    }

    private void rdft3d_sub(int i, DoubleLargeArray doubleLargeArray) {
        DoubleLargeArray doubleLargeArray2 = doubleLargeArray;
        long j = this.slicesl >> 1;
        long j2 = this.rowsl >> 1;
        if (i < 0) {
            long j3 = 1;
            while (j3 < j) {
                long j4 = this.slicesl - j3;
                long j5 = this.sliceStridel;
                long j6 = j3 * j5;
                long j7 = j4 * j5;
                long j8 = j;
                long j9 = this.rowStridel;
                long j10 = j3;
                long j11 = (j3 * j5) + (j2 * j9);
                long j12 = (j5 * j4) + (j9 * j2);
                double d = doubleLargeArray2.getDouble(j6) - doubleLargeArray2.getDouble(j7);
                long j13 = j4;
                doubleLargeArray2.setDouble(j6, doubleLargeArray2.getDouble(j6) + doubleLargeArray2.getDouble(j7));
                doubleLargeArray2.setDouble(j7, d);
                long j14 = j7 + 1;
                long j15 = j6 + 1;
                double d2 = doubleLargeArray2.getDouble(j14) - doubleLargeArray2.getDouble(j15);
                doubleLargeArray2.setDouble(j15, doubleLargeArray2.getDouble(j15) + doubleLargeArray2.getDouble(j14));
                doubleLargeArray2.setDouble(j14, d2);
                double d3 = doubleLargeArray2.getDouble(j11) - doubleLargeArray2.getDouble(j12);
                doubleLargeArray2.setDouble(j11, doubleLargeArray2.getDouble(j11) + doubleLargeArray2.getDouble(j12));
                doubleLargeArray2.setDouble(j12, d3);
                long j16 = j12 + 1;
                long j17 = j11 + 1;
                double d4 = doubleLargeArray2.getDouble(j16) - doubleLargeArray2.getDouble(j17);
                doubleLargeArray2.setDouble(j17, doubleLargeArray2.getDouble(j17) + doubleLargeArray2.getDouble(j16));
                doubleLargeArray2.setDouble(j16, d4);
                for (long j18 = 1; j18 < j2; j18++) {
                    long j19 = this.rowsl - j18;
                    long j20 = this.sliceStridel;
                    long j21 = this.rowStridel;
                    long j22 = (j10 * j20) + (j18 * j21);
                    long j23 = (j20 * j13) + (j21 * j19);
                    double d5 = doubleLargeArray2.getDouble(j22) - doubleLargeArray2.getDouble(j23);
                    doubleLargeArray2.setDouble(j22, doubleLargeArray2.getDouble(j22) + doubleLargeArray2.getDouble(j23));
                    doubleLargeArray2.setDouble(j23, d5);
                    long j24 = j23 + 1;
                    long j25 = j22 + 1;
                    double d6 = doubleLargeArray2.getDouble(j24) - doubleLargeArray2.getDouble(j25);
                    doubleLargeArray2.setDouble(j25, doubleLargeArray2.getDouble(j25) + doubleLargeArray2.getDouble(j24));
                    doubleLargeArray2.setDouble(j24, d6);
                    long j26 = this.sliceStridel;
                    long j27 = this.rowStridel;
                    long j28 = (j13 * j26) + (j18 * j27);
                    long j29 = (j26 * j10) + (j19 * j27);
                    double d7 = doubleLargeArray2.getDouble(j28) - doubleLargeArray2.getDouble(j29);
                    doubleLargeArray2.setDouble(j28, doubleLargeArray2.getDouble(j28) + doubleLargeArray2.getDouble(j29));
                    doubleLargeArray2.setDouble(j29, d7);
                    long j30 = j29 + 1;
                    long j31 = j28 + 1;
                    double d8 = doubleLargeArray2.getDouble(j30) - doubleLargeArray2.getDouble(j31);
                    doubleLargeArray2.setDouble(j31, doubleLargeArray2.getDouble(j31) + doubleLargeArray2.getDouble(j30));
                    doubleLargeArray2.setDouble(j30, d8);
                }
                j3 = j10 + 1;
                j = j8;
            }
            long j32 = j;
            for (long j33 = 1; j33 < j2; j33++) {
                long j34 = this.rowsl - j33;
                long j35 = this.rowStridel;
                long j36 = j33 * j35;
                long j37 = j35 * j34;
                double d9 = doubleLargeArray2.getDouble(j36) - doubleLargeArray2.getDouble(j37);
                doubleLargeArray2.setDouble(j36, doubleLargeArray2.getDouble(j36) + doubleLargeArray2.getDouble(j37));
                doubleLargeArray2.setDouble(j37, d9);
                long j38 = j37 + 1;
                long j39 = j36 + 1;
                double d10 = doubleLargeArray2.getDouble(j38) - doubleLargeArray2.getDouble(j39);
                doubleLargeArray2.setDouble(j39, doubleLargeArray2.getDouble(j39) + doubleLargeArray2.getDouble(j38));
                doubleLargeArray2.setDouble(j38, d10);
                long j40 = this.sliceStridel;
                long j41 = this.rowStridel;
                long j42 = (j32 * j40) + (j33 * j41);
                long j43 = (j40 * j32) + (j34 * j41);
                double d11 = doubleLargeArray2.getDouble(j42) - doubleLargeArray2.getDouble(j43);
                doubleLargeArray2.setDouble(j42, doubleLargeArray2.getDouble(j42) + doubleLargeArray2.getDouble(j43));
                doubleLargeArray2.setDouble(j43, d11);
                long j44 = j43 + 1;
                long j45 = j42 + 1;
                double d12 = doubleLargeArray2.getDouble(j44) - doubleLargeArray2.getDouble(j45);
                doubleLargeArray2.setDouble(j45, doubleLargeArray2.getDouble(j45) + doubleLargeArray2.getDouble(j44));
                doubleLargeArray2.setDouble(j44, d12);
            }
            return;
        }
        long j46 = j;
        long j47 = 1;
        while (j47 < j46) {
            long j48 = this.slicesl - j47;
            long j49 = this.sliceStridel;
            long j50 = j48 * j49;
            long j51 = j49 * j47;
            long j52 = j47;
            doubleLargeArray2.setDouble(j50, (doubleLargeArray2.getDouble(j51) - doubleLargeArray2.getDouble(j50)) * 0.5d);
            doubleLargeArray2.setDouble(j51, doubleLargeArray2.getDouble(j51) - doubleLargeArray2.getDouble(j50));
            long j53 = j50 + 1;
            long j54 = j51 + 1;
            doubleLargeArray2.setDouble(j53, (doubleLargeArray2.getDouble(j54) + doubleLargeArray2.getDouble(j53)) * 0.5d);
            doubleLargeArray2.setDouble(j54, doubleLargeArray2.getDouble(j54) - doubleLargeArray2.getDouble(j53));
            long j55 = this.sliceStridel;
            long j56 = this.rowStridel;
            long j57 = (j48 * j55) + (j2 * j56);
            long j58 = (j55 * j52) + (j56 * j2);
            doubleLargeArray2.setDouble(j57, (doubleLargeArray2.getDouble(j58) - doubleLargeArray2.getDouble(j57)) * 0.5d);
            doubleLargeArray2.setDouble(j58, doubleLargeArray2.getDouble(j58) - doubleLargeArray2.getDouble(j57));
            long j59 = j57 + 1;
            long j60 = j58 + 1;
            doubleLargeArray2.setDouble(j59, (doubleLargeArray2.getDouble(j60) + doubleLargeArray2.getDouble(j59)) * 0.5d);
            doubleLargeArray2.setDouble(j60, doubleLargeArray2.getDouble(j60) - doubleLargeArray2.getDouble(j59));
            long j61 = 1;
            while (j61 < j2) {
                long j62 = this.rowsl - j61;
                long j63 = this.sliceStridel;
                long j64 = this.rowStridel;
                long j65 = j2;
                long j66 = (j48 * j63) + (j62 * j64);
                long j67 = (j63 * j52) + (j64 * j61);
                doubleLargeArray2.setDouble(j66, (doubleLargeArray2.getDouble(j67) - doubleLargeArray2.getDouble(j66)) * 0.5d);
                doubleLargeArray2.setDouble(j67, doubleLargeArray2.getDouble(j67) - doubleLargeArray2.getDouble(j66));
                long j68 = j66 + 1;
                long j69 = j67 + 1;
                doubleLargeArray2.setDouble(j68, (doubleLargeArray2.getDouble(j69) + doubleLargeArray2.getDouble(j68)) * 0.5d);
                doubleLargeArray2.setDouble(j69, doubleLargeArray2.getDouble(j69) - doubleLargeArray2.getDouble(j68));
                long j70 = this.sliceStridel;
                long j71 = this.rowStridel;
                long j72 = (j52 * j70) + (j62 * j71);
                long j73 = (j70 * j48) + (j71 * j61);
                doubleLargeArray2.setDouble(j72, (doubleLargeArray2.getDouble(j73) - doubleLargeArray2.getDouble(j72)) * 0.5d);
                doubleLargeArray2.setDouble(j73, doubleLargeArray2.getDouble(j73) - doubleLargeArray2.getDouble(j72));
                long j74 = j72 + 1;
                long j75 = j73 + 1;
                doubleLargeArray2.setDouble(j74, (doubleLargeArray2.getDouble(j75) + doubleLargeArray2.getDouble(j74)) * 0.5d);
                doubleLargeArray2.setDouble(j75, doubleLargeArray2.getDouble(j75) - doubleLargeArray2.getDouble(j74));
                j61++;
                j2 = j65;
            }
            j47 = j52 + 1;
            j2 = j2;
        }
        long j76 = j2;
        for (long j77 = 1; j77 < j76; j77++) {
            long j78 = this.rowsl - j77;
            long j79 = this.rowStridel;
            long j80 = j78 * j79;
            long j81 = j79 * j77;
            doubleLargeArray2.setDouble(j80, (doubleLargeArray2.getDouble(j81) - doubleLargeArray2.getDouble(j80)) * 0.5d);
            doubleLargeArray2.setDouble(j81, doubleLargeArray2.getDouble(j81) - doubleLargeArray2.getDouble(j80));
            long j82 = j80 + 1;
            long j83 = j81 + 1;
            doubleLargeArray2.setDouble(j82, (doubleLargeArray2.getDouble(j83) + doubleLargeArray2.getDouble(j82)) * 0.5d);
            doubleLargeArray2.setDouble(j83, doubleLargeArray2.getDouble(j83) - doubleLargeArray2.getDouble(j82));
            long j84 = this.sliceStridel;
            long j85 = this.rowStridel;
            long j86 = (j46 * j84) + (j78 * j85);
            long j87 = (j46 * j84) + (j85 * j77);
            doubleLargeArray2.setDouble(j86, (doubleLargeArray2.getDouble(j87) - doubleLargeArray2.getDouble(j86)) * 0.5d);
            doubleLargeArray2.setDouble(j87, doubleLargeArray2.getDouble(j87) - doubleLargeArray2.getDouble(j86));
            long j88 = j86 + 1;
            long j89 = j87 + 1;
            doubleLargeArray2.setDouble(j88, (doubleLargeArray2.getDouble(j89) + doubleLargeArray2.getDouble(j88)) * 0.5d);
            doubleLargeArray2.setDouble(j89, doubleLargeArray2.getDouble(j89) - doubleLargeArray2.getDouble(j88));
        }
    }

    private void rdft3d_sub(int i, double[][][] dArr) {
        int i2 = this.slices >> 1;
        int i3 = this.rows >> 1;
        if (i < 0) {
            for (int i4 = 1; i4 < i2; i4++) {
                int i5 = this.slices - i4;
                double[][] dArr2 = dArr[i4];
                double[] dArr3 = dArr2[0];
                double d = dArr3[0];
                double[][] dArr4 = dArr[i5];
                double[] dArr5 = dArr4[0];
                double d2 = dArr5[0];
                dArr3[0] = d + d2;
                dArr5[0] = d - d2;
                double d3 = dArr5[1];
                double d4 = dArr3[1];
                dArr3[1] = d4 + d3;
                dArr5[1] = d3 - d4;
                double[] dArr6 = dArr2[i3];
                double d5 = dArr6[0];
                double[] dArr7 = dArr4[i3];
                double d6 = dArr7[0];
                dArr6[0] = d5 + d6;
                dArr7[0] = d5 - d6;
                double d7 = dArr7[1];
                double d8 = dArr6[1];
                dArr6[1] = d8 + d7;
                dArr7[1] = d7 - d8;
                for (int i6 = 1; i6 < i3; i6++) {
                    int i7 = this.rows - i6;
                    double[][] dArr8 = dArr[i4];
                    double[] dArr9 = dArr8[i6];
                    double d9 = dArr9[0];
                    double[][] dArr10 = dArr[i5];
                    double[] dArr11 = dArr10[i7];
                    double d10 = dArr11[0];
                    dArr9[0] = d9 + d10;
                    dArr11[0] = d9 - d10;
                    double d11 = dArr11[1];
                    double d12 = dArr9[1];
                    dArr9[1] = d12 + d11;
                    dArr11[1] = d11 - d12;
                    double[] dArr12 = dArr10[i6];
                    double d13 = dArr12[0];
                    double[] dArr13 = dArr8[i7];
                    double d14 = dArr13[0];
                    dArr12[0] = d13 + d14;
                    dArr13[0] = d13 - d14;
                    double d15 = dArr13[1];
                    double d16 = dArr12[1];
                    dArr12[1] = d16 + d15;
                    dArr13[1] = d15 - d16;
                }
            }
            for (int i8 = 1; i8 < i3; i8++) {
                int i9 = this.rows - i8;
                double[][] dArr14 = dArr[0];
                double[] dArr15 = dArr14[i8];
                double d17 = dArr15[0];
                double[] dArr16 = dArr14[i9];
                double d18 = dArr16[0];
                dArr15[0] = d17 + d18;
                dArr16[0] = d17 - d18;
                double d19 = dArr16[1];
                double d20 = dArr15[1];
                dArr15[1] = d20 + d19;
                dArr16[1] = d19 - d20;
                double[][] dArr17 = dArr[i2];
                double[] dArr18 = dArr17[i8];
                double d21 = dArr18[0];
                double[] dArr19 = dArr17[i9];
                double d22 = dArr19[0];
                dArr18[0] = d21 + d22;
                dArr19[0] = d21 - d22;
                double d23 = dArr19[1];
                double d24 = dArr18[1];
                dArr18[1] = d24 + d23;
                dArr19[1] = d23 - d24;
            }
            return;
        }
        for (int i10 = 1; i10 < i2; i10++) {
            int i11 = this.slices - i10;
            double[][] dArr20 = dArr[i11];
            double[] dArr21 = dArr20[0];
            double[][] dArr22 = dArr[i10];
            double[] dArr23 = dArr22[0];
            double d25 = (dArr23[0] - dArr21[0]) * 0.5d;
            dArr21[0] = d25;
            dArr23[0] = dArr23[0] - d25;
            double d26 = (dArr23[1] + dArr21[1]) * 0.5d;
            dArr21[1] = d26;
            dArr23[1] = dArr23[1] - d26;
            double[] dArr24 = dArr20[i3];
            double[] dArr25 = dArr22[i3];
            double d27 = (dArr25[0] - dArr24[0]) * 0.5d;
            dArr24[0] = d27;
            dArr25[0] = dArr25[0] - d27;
            double d28 = (dArr25[1] + dArr24[1]) * 0.5d;
            dArr24[1] = d28;
            dArr25[1] = dArr25[1] - d28;
            for (int i12 = 1; i12 < i3; i12++) {
                int i13 = this.rows - i12;
                double[][] dArr26 = dArr[i11];
                double[] dArr27 = dArr26[i13];
                double[][] dArr28 = dArr[i10];
                double[] dArr29 = dArr28[i12];
                double d29 = (dArr29[0] - dArr27[0]) * 0.5d;
                dArr27[0] = d29;
                dArr29[0] = dArr29[0] - d29;
                double d30 = (dArr29[1] + dArr27[1]) * 0.5d;
                dArr27[1] = d30;
                dArr29[1] = dArr29[1] - d30;
                double[] dArr30 = dArr28[i13];
                double[] dArr31 = dArr26[i12];
                double d31 = (dArr31[0] - dArr30[0]) * 0.5d;
                dArr30[0] = d31;
                dArr31[0] = dArr31[0] - d31;
                double d32 = (dArr31[1] + dArr30[1]) * 0.5d;
                dArr30[1] = d32;
                dArr31[1] = dArr31[1] - d32;
            }
        }
        for (int i14 = 1; i14 < i3; i14++) {
            int i15 = this.rows - i14;
            double[][] dArr32 = dArr[0];
            double[] dArr33 = dArr32[i15];
            double[] dArr34 = dArr32[i14];
            double d33 = (dArr34[0] - dArr33[0]) * 0.5d;
            dArr33[0] = d33;
            dArr34[0] = dArr34[0] - d33;
            double d34 = (dArr34[1] + dArr33[1]) * 0.5d;
            dArr33[1] = d34;
            dArr34[1] = dArr34[1] - d34;
            double[][] dArr35 = dArr[i2];
            double[] dArr36 = dArr35[i15];
            double[] dArr37 = dArr35[i14];
            double d35 = (dArr37[0] - dArr36[0]) * 0.5d;
            dArr36[0] = d35;
            dArr37[0] = dArr37[0] - d35;
            double d36 = (dArr37[1] + dArr36[1]) * 0.5d;
            dArr36[1] = d36;
            dArr37[1] = dArr37[1] - d36;
        }
    }

    private void fillSymmetric(double[][][] dArr) {
        int i;
        Class<DoubleFFT_3D> cls = DoubleFFT_3D.class;
        int i2 = this.columns * 2;
        int i3 = this.rows / 2;
        int i4 = this.slices / 2;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (numberOfThreads <= 1 || !this.useThreads || (i = this.slices) < numberOfThreads) {
            int i5 = 0;
            while (true) {
                int i6 = this.slices;
                if (i5 >= i6) {
                    break;
                }
                int i7 = (i6 - i5) % i6;
                int i8 = 0;
                while (true) {
                    int i9 = this.rows;
                    if (i8 >= i9) {
                        break;
                    }
                    int i10 = (i9 - i8) % i9;
                    int i11 = 1;
                    while (i11 < this.columns) {
                        int i12 = i2 - i11;
                        double[] dArr2 = dArr[i7][i10];
                        double[] dArr3 = dArr[i5][i8];
                        int i13 = i11 + 2;
                        dArr2[i12] = -dArr3[i13];
                        dArr2[i12 - 1] = dArr3[i11 + 1];
                        i11 = i13;
                    }
                    i8++;
                }
                i5++;
            }
            int i14 = 0;
            while (true) {
                int i15 = this.slices;
                if (i14 >= i15) {
                    break;
                }
                int i16 = (i15 - i14) % i15;
                for (int i17 = 1; i17 < i3; i17++) {
                    double[] dArr4 = dArr[i16][i17];
                    int i18 = this.columns;
                    double[] dArr5 = dArr[i14][this.rows - i17];
                    dArr4[i18] = dArr5[1];
                    dArr5[i18] = dArr5[1];
                    dArr4[i18 + 1] = -dArr5[0];
                    dArr5[i18 + 1] = dArr5[0];
                }
                i14++;
            }
            int i19 = 0;
            while (true) {
                int i20 = this.slices;
                if (i19 >= i20) {
                    break;
                }
                int i21 = (i20 - i19) % i20;
                for (int i22 = 1; i22 < i3; i22++) {
                    double[] dArr6 = dArr[i21][this.rows - i22];
                    double[] dArr7 = dArr[i19][i22];
                    dArr6[0] = dArr7[0];
                    dArr6[1] = -dArr7[1];
                }
                i19++;
            }
        } else {
            Future[] futureArr = new Future[numberOfThreads];
            int i23 = i / numberOfThreads;
            int i24 = 0;
            while (i24 < numberOfThreads) {
                final int i25 = i24 * i23;
                final int i26 = i24 == numberOfThreads + -1 ? this.slices : i25 + i23;
                final int i27 = i2;
                int i28 = i24;
                final double[][][] dArr8 = dArr;
                futureArr[i28] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i25; i < i26; i++) {
                            int access$600 = (DoubleFFT_3D.this.slices - i) % DoubleFFT_3D.this.slices;
                            for (int i2 = 0; i2 < DoubleFFT_3D.this.rows; i2++) {
                                int access$100 = (DoubleFFT_3D.this.rows - i2) % DoubleFFT_3D.this.rows;
                                int i3 = 1;
                                while (i3 < DoubleFFT_3D.this.columns) {
                                    int i4 = i27 - i3;
                                    double[][][] dArr = dArr8;
                                    double[] dArr2 = dArr[access$600][access$100];
                                    double[] dArr3 = dArr[i][i2];
                                    int i5 = i3 + 2;
                                    dArr2[i4] = -dArr3[i5];
                                    dArr2[i4 - 1] = dArr3[i3 + 1];
                                    i3 = i5;
                                }
                            }
                        }
                    }
                });
                i24 = i28 + 1;
            }
            String str = null;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
            } catch (ExecutionException e2) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
            }
            int i29 = 0;
            while (i29 < numberOfThreads) {
                final int i30 = i29 * i23;
                final int i31 = i29 == numberOfThreads + -1 ? this.slices : i30 + i23;
                final int i32 = i3;
                String str2 = str;
                final double[][][] dArr9 = dArr;
                futureArr[i29] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i30; i < i31; i++) {
                            int access$600 = (DoubleFFT_3D.this.slices - i) % DoubleFFT_3D.this.slices;
                            for (int i2 = 1; i2 < i32; i2++) {
                                int access$100 = DoubleFFT_3D.this.rows - i2;
                                double[] dArr = dArr9[access$600][i2];
                                int access$400 = DoubleFFT_3D.this.columns;
                                double[] dArr2 = dArr9[i][access$100];
                                dArr[access$400] = dArr2[1];
                                int access$4002 = DoubleFFT_3D.this.columns;
                                double[][][] dArr3 = dArr9;
                                dArr2[access$4002] = dArr3[i][access$100][1];
                                double[] dArr4 = dArr9[i][access$100];
                                dArr3[access$600][i2][DoubleFFT_3D.this.columns + 1] = -dArr4[0];
                                dArr4[DoubleFFT_3D.this.columns + 1] = dArr9[i][access$100][0];
                            }
                        }
                    }
                });
                i29++;
                str = str2;
            }
            String str3 = str;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e3) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e4);
            }
            int i33 = 0;
            while (i33 < numberOfThreads) {
                final int i34 = i33 * i23;
                final int i35 = i33 == numberOfThreads + -1 ? this.slices : i34 + i23;
                final int i36 = i3;
                final double[][][] dArr10 = dArr;
                futureArr[i33] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i34; i < i35; i++) {
                            int access$600 = (DoubleFFT_3D.this.slices - i) % DoubleFFT_3D.this.slices;
                            for (int i2 = 1; i2 < i36; i2++) {
                                double[][][] dArr = dArr10;
                                double[] dArr2 = dArr[access$600][DoubleFFT_3D.this.rows - i2];
                                double[] dArr3 = dArr[i][i2];
                                dArr2[0] = dArr3[0];
                                dArr2[1] = -dArr3[1];
                            }
                        }
                    }
                });
                i33++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e5) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e5);
            } catch (ExecutionException e6) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e6);
            }
        }
        for (int i37 = 1; i37 < i4; i37++) {
            double[][] dArr11 = dArr[i37];
            double[] dArr12 = dArr11[0];
            int i38 = this.columns;
            double[][] dArr13 = dArr[this.slices - i37];
            double[] dArr14 = dArr13[0];
            dArr12[i38] = dArr14[1];
            dArr14[i38] = dArr14[1];
            dArr12[i38 + 1] = -dArr14[0];
            dArr14[i38 + 1] = dArr14[0];
            double[] dArr15 = dArr11[i3];
            double[] dArr16 = dArr13[i3];
            dArr15[i38] = dArr16[1];
            dArr16[i38] = dArr16[1];
            dArr15[i38 + 1] = -dArr16[0];
            dArr16[i38 + 1] = dArr16[0];
            dArr14[0] = dArr12[0];
            dArr14[1] = -dArr12[1];
            dArr16[0] = dArr15[0];
            dArr16[1] = -dArr15[1];
        }
        double[][] dArr17 = dArr[0];
        double[] dArr18 = dArr17[0];
        int i39 = this.columns;
        dArr18[i39] = dArr18[1];
        dArr18[1] = 0.0d;
        double[] dArr19 = dArr17[i3];
        dArr19[i39] = dArr19[1];
        dArr19[1] = 0.0d;
        double[][] dArr20 = dArr[i4];
        double[] dArr21 = dArr20[0];
        dArr21[i39] = dArr21[1];
        dArr21[1] = 0.0d;
        double[] dArr22 = dArr20[i3];
        dArr22[i39] = dArr22[1];
        dArr22[1] = 0.0d;
        dArr21[i39 + 1] = 0.0d;
        dArr22[i39 + 1] = 0.0d;
    }

    private void fillSymmetric(double[] dArr) {
        int i;
        int i2;
        Class<DoubleFFT_3D> cls = DoubleFFT_3D.class;
        int i3 = this.columns * 2;
        int i4 = this.rows;
        int i5 = i4 / 2;
        int i6 = this.slices;
        int i7 = i6 / 2;
        int i8 = i4 * i3;
        for (int i9 = i6 - 1; i9 >= 1; i9--) {
            int i10 = this.sliceStride * i9;
            int i11 = i10 * 2;
            for (int i12 = 0; i12 < this.rows; i12++) {
                int i13 = this.rowStride * i12;
                int i14 = i13 * 2;
                for (int i15 = 0; i15 < this.columns; i15 += 2) {
                    int i16 = i10 + i13 + i15;
                    int i17 = i11 + i14 + i15;
                    dArr[i17] = dArr[i16];
                    dArr[i16] = 0.0d;
                    int i18 = i16 + 1;
                    dArr[i17 + 1] = dArr[i18];
                    dArr[i18] = 0.0d;
                }
            }
        }
        int i19 = 1;
        while (true) {
            int i20 = this.rows;
            if (i19 >= i20) {
                break;
            }
            int i21 = (i20 - i19) * this.rowStride;
            int i22 = (i20 - i19) * i3;
            for (int i23 = 0; i23 < this.columns; i23 += 2) {
                int i24 = i21 + i23;
                int i25 = i22 + i23;
                dArr[i25] = dArr[i24];
                dArr[i24] = 0.0d;
                int i26 = i24 + 1;
                dArr[i25 + 1] = dArr[i26];
                dArr[i26] = 0.0d;
            }
            i19++;
        }
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (numberOfThreads <= 1 || !this.useThreads || (i2 = this.slices) < numberOfThreads) {
            i = i7;
            int i27 = 0;
            while (true) {
                int i28 = this.slices;
                if (i27 >= i28) {
                    break;
                }
                int i29 = ((i28 - i27) % i28) * i8;
                int i30 = i27 * i8;
                int i31 = 0;
                while (true) {
                    int i32 = this.rows;
                    if (i31 >= i32) {
                        break;
                    }
                    int i33 = ((i32 - i31) % i32) * i3;
                    int i34 = i31 * i3;
                    int i35 = 1;
                    while (i35 < this.columns) {
                        int i36 = ((i29 + i33) + i3) - i35;
                        int i37 = i30 + i34 + i35;
                        dArr[i36] = -dArr[i37 + 2];
                        dArr[i36 - 1] = dArr[i37 + 1];
                        i35 += 2;
                        i29 = i29;
                        i30 = i30;
                    }
                    int i38 = i30;
                    int i39 = i29;
                    i31++;
                }
                i27++;
            }
            int i40 = 0;
            while (true) {
                int i41 = this.slices;
                if (i40 >= i41) {
                    break;
                }
                int i42 = ((i41 - i40) % i41) * i8;
                int i43 = i40 * i8;
                for (int i44 = 1; i44 < i5; i44++) {
                    int i45 = ((this.rows - i44) * i3) + i43;
                    int i46 = this.columns;
                    int i47 = (i44 * i3) + i42 + i46;
                    int i48 = i46 + i45;
                    int i49 = i45 + 1;
                    dArr[i47] = dArr[i49];
                    dArr[i48] = dArr[i49];
                    dArr[i47 + 1] = -dArr[i45];
                    dArr[i48 + 1] = dArr[i45];
                }
                i40++;
            }
            int i50 = 0;
            while (true) {
                int i51 = this.slices;
                if (i50 >= i51) {
                    break;
                }
                int i52 = ((i51 - i50) % i51) * i8;
                int i53 = i50 * i8;
                for (int i54 = 1; i54 < i5; i54++) {
                    int i55 = ((this.rows - i54) * i3) + i52;
                    int i56 = (i54 * i3) + i53;
                    dArr[i55] = dArr[i56];
                    dArr[i55 + 1] = -dArr[i56 + 1];
                }
                i50++;
            }
        } else {
            Future[] futureArr = new Future[numberOfThreads];
            int i57 = i2 / numberOfThreads;
            int i58 = 0;
            while (i58 < numberOfThreads) {
                final int i59 = i58 * i57;
                final int i60 = i58 == numberOfThreads + -1 ? this.slices : i59 + i57;
                final int i61 = i8;
                final int i62 = i3;
                Future[] futureArr2 = futureArr;
                final int i63 = i3;
                int i64 = numberOfThreads;
                final double[] dArr2 = dArr;
                futureArr2[i58] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i59; i < i60; i++) {
                            int access$600 = (DoubleFFT_3D.this.slices - i) % DoubleFFT_3D.this.slices;
                            int i2 = i61;
                            int i3 = access$600 * i2;
                            int i4 = i2 * i;
                            for (int i5 = 0; i5 < DoubleFFT_3D.this.rows; i5++) {
                                int access$100 = (DoubleFFT_3D.this.rows - i5) % DoubleFFT_3D.this.rows;
                                int i6 = i62;
                                int i7 = access$100 * i6;
                                int i8 = i6 * i5;
                                for (int i9 = 1; i9 < DoubleFFT_3D.this.columns; i9 += 2) {
                                    int i10 = ((i3 + i7) + i63) - i9;
                                    int i11 = i4 + i8 + i9;
                                    double[] dArr = dArr2;
                                    dArr[i10] = -dArr[i11 + 2];
                                    dArr[i10 - 1] = dArr[i11 + 1];
                                }
                            }
                        }
                    }
                });
                i58++;
                numberOfThreads = i64;
                futureArr = futureArr2;
            }
            Future[] futureArr3 = futureArr;
            int i65 = numberOfThreads;
            String str = null;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
            } catch (ExecutionException e2) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
            }
            int i66 = 0;
            while (i66 < i65) {
                final int i67 = i66 * i57;
                final int i68 = i66 == i65 + -1 ? this.slices : i67 + i57;
                final int i69 = i8;
                final int i70 = i5;
                final int i71 = i3;
                int i72 = i7;
                String str2 = str;
                final double[] dArr3 = dArr;
                futureArr3[i66] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i67; i < i68; i++) {
                            int access$600 = (DoubleFFT_3D.this.slices - i) % DoubleFFT_3D.this.slices;
                            int i2 = i69;
                            int i3 = access$600 * i2;
                            int i4 = i2 * i;
                            for (int i5 = 1; i5 < i70; i5++) {
                                int i6 = i71;
                                int access$100 = ((DoubleFFT_3D.this.rows - i5) * i6) + i4;
                                int access$400 = (i6 * i5) + i3 + DoubleFFT_3D.this.columns;
                                int access$4002 = DoubleFFT_3D.this.columns + access$100;
                                int i7 = access$100 + 1;
                                double[] dArr = dArr3;
                                dArr[access$400] = dArr[i7];
                                dArr[access$4002] = dArr[i7];
                                dArr[access$400 + 1] = -dArr[access$100];
                                dArr[access$4002 + 1] = dArr[access$100];
                            }
                        }
                    }
                });
                i66++;
                str = str2;
                i7 = i72;
            }
            i = i7;
            String str3 = str;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e3) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e4);
            }
            int i73 = 0;
            while (i73 < i65) {
                final int i74 = i73 * i57;
                final int i75 = i73 == i65 + -1 ? this.slices : i74 + i57;
                final int i76 = i8;
                final int i77 = i5;
                final int i78 = i3;
                final double[] dArr4 = dArr;
                futureArr3[i73] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i74; i < i75; i++) {
                            int access$600 = (DoubleFFT_3D.this.slices - i) % DoubleFFT_3D.this.slices;
                            int i2 = i76;
                            int i3 = access$600 * i2;
                            int i4 = i2 * i;
                            for (int i5 = 1; i5 < i77; i5++) {
                                int i6 = i78;
                                int access$100 = ((DoubleFFT_3D.this.rows - i5) * i6) + i3;
                                int i7 = (i6 * i5) + i4;
                                double[] dArr = dArr4;
                                dArr[access$100] = dArr[i7];
                                dArr[access$100 + 1] = -dArr[i7 + 1];
                            }
                        }
                    }
                });
                i73++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e5) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e5);
            } catch (ExecutionException e6) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e6);
            }
        }
        int i79 = i;
        int i80 = 1;
        while (i80 < i79) {
            int i81 = i80 * i8;
            int i82 = (this.slices - i80) * i8;
            int i83 = i5 * i3;
            int i84 = i81 + i83;
            int i85 = i83 + i82;
            int i86 = this.columns;
            int i87 = i82 + 1;
            dArr[i81 + i86] = dArr[i87];
            dArr[i82 + i86] = dArr[i87];
            dArr[i81 + i86 + 1] = -dArr[i82];
            dArr[i82 + i86 + 1] = dArr[i82];
            int i88 = i85 + 1;
            dArr[i84 + i86] = dArr[i88];
            dArr[i85 + i86] = dArr[i88];
            dArr[i84 + i86 + 1] = -dArr[i85];
            dArr[i86 + i85 + 1] = dArr[i85];
            dArr[i82] = dArr[i81];
            dArr[i87] = -dArr[i81 + 1];
            dArr[i85] = dArr[i84];
            dArr[i88] = -dArr[i84 + 1];
            i80++;
            i3 = i3;
            i5 = i5;
        }
        int i89 = this.columns;
        dArr[i89] = dArr[1];
        dArr[1] = 0.0d;
        int i90 = i5 * i3;
        int i91 = i79 * i8;
        int i92 = i90 + i91;
        int i93 = i90 + i89;
        int i94 = i90 + 1;
        dArr[i93] = dArr[i94];
        dArr[i94] = 0.0d;
        int i95 = i91 + 1;
        dArr[i91 + i89] = dArr[i95];
        dArr[i95] = 0.0d;
        int i96 = i92 + 1;
        dArr[i92 + i89] = dArr[i96];
        dArr[i96] = 0.0d;
        dArr[i91 + i89 + 1] = 0.0d;
        dArr[i92 + i89 + 1] = 0.0d;
    }

    /* JADX WARNING: Removed duplicated region for block: B:100:0x0309 A[LOOP:15: B:98:0x0305->B:100:0x0309, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void fillSymmetric(pl.edu.icm.jlargearrays.DoubleLargeArray r42) {
        /*
            r41 = this;
            r14 = r41
            r15 = r42
            java.lang.Class<org.jtransforms.fft.DoubleFFT_3D> r16 = org.jtransforms.fft.DoubleFFT_3D.class
            long r0 = r14.columnsl
            r2 = 2
            long r17 = r0 * r2
            long r0 = r14.rowsl
            long r19 = r0 / r2
            long r4 = r14.slicesl
            long r21 = r4 / r2
            long r23 = r0 * r17
            r11 = 1
            long r4 = r4 - r11
        L_0x0019:
            int r0 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            r9 = 0
            if (r0 < 0) goto L_0x0079
            long r0 = r14.sliceStridel
            long r0 = r0 * r4
            long r25 = r0 * r2
            r27 = 0
        L_0x0026:
            long r6 = r14.rowsl
            int r6 = (r27 > r6 ? 1 : (r27 == r6 ? 0 : -1))
            if (r6 >= 0) goto L_0x0070
            long r6 = r14.rowStridel
            long r6 = r6 * r27
            long r31 = r6 * r2
            r33 = 0
        L_0x0034:
            long r2 = r14.columnsl
            int r2 = (r33 > r2 ? 1 : (r33 == r2 ? 0 : -1))
            if (r2 >= 0) goto L_0x0067
            long r2 = r0 + r6
            long r2 = r2 + r33
            long r37 = r25 + r31
            long r11 = r37 + r33
            r37 = r0
            double r0 = r15.getDouble(r2)
            r15.setDouble(r11, r0)
            r15.setDouble(r2, r9)
            r39 = 1
            long r2 = r2 + r39
            long r11 = r11 + r39
            double r0 = r15.getDouble(r2)
            r15.setDouble(r11, r0)
            r15.setDouble(r2, r9)
            r0 = 2
            long r33 = r33 + r0
            r0 = r37
            r11 = r39
            goto L_0x0034
        L_0x0067:
            r37 = r0
            r39 = r11
            long r27 = r27 + r39
            r2 = 2
            goto L_0x0026
        L_0x0070:
            r39 = r11
            long r4 = r4 - r39
            r2 = 2
            r11 = 1
            goto L_0x0019
        L_0x0079:
            r0 = 1
        L_0x007b:
            long r2 = r14.rowsl
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 >= 0) goto L_0x00c4
            long r4 = r2 - r0
            long r6 = r14.rowStridel
            long r4 = r4 * r6
            long r2 = r2 - r0
            long r2 = r2 * r17
            r6 = 0
        L_0x008b:
            long r11 = r14.columnsl
            int r8 = (r6 > r11 ? 1 : (r6 == r11 ? 0 : -1))
            if (r8 >= 0) goto L_0x00be
            long r11 = r4 + r6
            long r9 = r2 + r6
            r27 = r2
            double r2 = r15.getDouble(r11)
            r15.setDouble(r9, r2)
            r2 = 0
            r15.setDouble(r11, r2)
            r25 = 1
            long r11 = r11 + r25
            long r9 = r9 + r25
            r31 = r4
            double r4 = r15.getDouble(r11)
            r15.setDouble(r9, r4)
            r15.setDouble(r11, r2)
            r4 = 2
            long r6 = r6 + r4
            r9 = r2
            r2 = r27
            r4 = r31
            goto L_0x008b
        L_0x00be:
            r2 = r9
            r25 = 1
            long r0 = r0 + r25
            goto L_0x007b
        L_0x00c4:
            r2 = r9
            r25 = 1
            int r13 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getNumberOfThreads()
            r0 = 1
            if (r13 <= r0) goto L_0x01f4
            boolean r0 = r14.useThreads
            if (r0 == 0) goto L_0x01f4
            long r0 = r14.slicesl
            long r4 = (long) r13
            int r6 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r6 < 0) goto L_0x01f4
            java.util.concurrent.Future[] r11 = new java.util.concurrent.Future[r13]
            long r27 = r0 / r4
            r29 = 0
            r0 = r29
        L_0x00e1:
            if (r0 >= r13) goto L_0x0118
            long r4 = (long) r0
            long r4 = r4 * r27
            int r1 = r13 + -1
            if (r0 != r1) goto L_0x00ed
            long r6 = r14.slicesl
            goto L_0x00ef
        L_0x00ed:
            long r6 = r4 + r27
        L_0x00ef:
            org.jtransforms.fft.DoubleFFT_3D$66 r30 = new org.jtransforms.fft.DoubleFFT_3D$66
            r1 = r30
            r9 = r2
            r2 = r41
            r3 = r4
            r5 = r6
            r7 = r23
            r9 = r17
            r25 = r11
            r11 = r17
            r15 = r13
            r13 = r42
            r1.<init>(r3, r5, r7, r9, r11, r13)
            java.util.concurrent.Future r1 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r30)
            r25[r0] = r1
            int r0 = r0 + 1
            r13 = r15
            r11 = r25
            r2 = 0
            r25 = 1
            r15 = r42
            goto L_0x00e1
        L_0x0118:
            r25 = r11
            r15 = r13
            r13 = 0
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r25)     // Catch:{ InterruptedException -> 0x0130, ExecutionException -> 0x0120 }
            goto L_0x013f
        L_0x0120:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = r16.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            r0.log(r2, r13, r1)
            goto L_0x013f
        L_0x0130:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = r16.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            r0.log(r2, r13, r1)
        L_0x013f:
            r0 = r29
        L_0x0141:
            if (r0 >= r15) goto L_0x016e
            long r1 = (long) r0
            long r3 = r1 * r27
            int r1 = r15 + -1
            if (r0 != r1) goto L_0x014d
            long r1 = r14.slicesl
            goto L_0x014f
        L_0x014d:
            long r1 = r3 + r27
        L_0x014f:
            r5 = r1
            org.jtransforms.fft.DoubleFFT_3D$67 r26 = new org.jtransforms.fft.DoubleFFT_3D$67
            r1 = r26
            r2 = r41
            r7 = r23
            r9 = r19
            r11 = r17
            r14 = r13
            r13 = r42
            r1.<init>(r3, r5, r7, r9, r11, r13)
            java.util.concurrent.Future r1 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r26)
            r25[r0] = r1
            int r0 = r0 + 1
            r13 = r14
            r14 = r41
            goto L_0x0141
        L_0x016e:
            r14 = r13
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r25)     // Catch:{ InterruptedException -> 0x0183, ExecutionException -> 0x0173 }
            goto L_0x0192
        L_0x0173:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = r16.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            r0.log(r2, r14, r1)
            goto L_0x0192
        L_0x0183:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = r16.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            r0.log(r2, r14, r1)
        L_0x0192:
            r0 = r29
        L_0x0194:
            if (r0 >= r15) goto L_0x01c9
            long r1 = (long) r0
            long r3 = r1 * r27
            int r13 = r15 + -1
            if (r0 != r13) goto L_0x01a3
            r13 = r14
            r14 = r41
            long r1 = r14.slicesl
            goto L_0x01a8
        L_0x01a3:
            r13 = r14
            r14 = r41
            long r1 = r3 + r27
        L_0x01a8:
            r5 = r1
            org.jtransforms.fft.DoubleFFT_3D$68 r26 = new org.jtransforms.fft.DoubleFFT_3D$68
            r1 = r26
            r2 = r41
            r7 = r23
            r9 = r19
            r11 = r17
            r29 = r15
            r15 = r13
            r13 = r42
            r1.<init>(r3, r5, r7, r9, r11, r13)
            java.util.concurrent.Future r1 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r26)
            r25[r0] = r1
            int r0 = r0 + 1
            r14 = r15
            r15 = r29
            goto L_0x0194
        L_0x01c9:
            r15 = r14
            r14 = r41
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r25)     // Catch:{ InterruptedException -> 0x01e0, ExecutionException -> 0x01d0 }
            goto L_0x01ef
        L_0x01d0:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = r16.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            r0.log(r2, r15, r1)
            goto L_0x01ef
        L_0x01e0:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = r16.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            r0.log(r2, r15, r1)
        L_0x01ef:
            r4 = r42
            r1 = r14
            goto L_0x0303
        L_0x01f4:
            r0 = 0
        L_0x01f6:
            long r2 = r14.slicesl
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 >= 0) goto L_0x0268
            long r4 = r2 - r0
            long r4 = r4 % r2
            long r4 = r4 * r23
            long r2 = r0 * r23
            r6 = 0
        L_0x0205:
            long r8 = r14.rowsl
            int r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r10 >= 0) goto L_0x025e
            long r10 = r8 - r6
            long r10 = r10 % r8
            long r10 = r10 * r17
            long r8 = r6 * r17
            r15 = r0
            r12 = 1
        L_0x0215:
            long r0 = r14.columnsl
            int r0 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r0 >= 0) goto L_0x024d
            long r0 = r4 + r10
            long r0 = r0 + r17
            long r0 = r0 - r12
            long r25 = r2 + r8
            long r25 = r25 + r12
            r31 = r2
            r27 = 2
            long r2 = r25 + r27
            r33 = r4
            r4 = r42
            double r2 = r4.getDouble(r2)
            double r2 = -r2
            r4.setDouble(r0, r2)
            r2 = 1
            long r0 = r0 - r2
            r35 = r8
            long r8 = r25 + r2
            double r8 = r4.getDouble(r8)
            r4.setDouble(r0, r8)
            long r12 = r12 + r27
            r2 = r31
            r4 = r33
            r8 = r35
            goto L_0x0215
        L_0x024d:
            r31 = r2
            r33 = r4
            r2 = 1
            r27 = 2
            r4 = r42
            long r6 = r6 + r2
            r0 = r15
            r2 = r31
            r4 = r33
            goto L_0x0205
        L_0x025e:
            r4 = r42
            r15 = r0
            r2 = 1
            r27 = 2
            long r0 = r15 + r2
            goto L_0x01f6
        L_0x0268:
            r4 = r42
            r2 = 1
            r0 = 0
        L_0x026e:
            long r5 = r14.slicesl
            int r7 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r7 >= 0) goto L_0x02c3
            long r7 = r5 - r0
            long r7 = r7 % r5
            long r7 = r7 * r23
            long r5 = r0 * r23
            r11 = r2
        L_0x027c:
            int r9 = (r11 > r19 ? 1 : (r11 == r19 ? 0 : -1))
            if (r9 >= 0) goto L_0x02be
            long r9 = r14.rowsl
            long r9 = r9 - r11
            long r9 = r9 * r17
            long r9 = r9 + r5
            long r15 = r11 * r17
            long r15 = r15 + r7
            long r2 = r14.columnsl
            r25 = r5
            long r5 = r15 + r2
            long r2 = r2 + r9
            r27 = r7
            r15 = 1
            long r7 = r9 + r15
            double r13 = r4.getDouble(r7)
            r4.setDouble(r5, r13)
            double r7 = r4.getDouble(r7)
            r4.setDouble(r2, r7)
            long r5 = r5 + r15
            double r7 = r4.getDouble(r9)
            double r7 = -r7
            r4.setDouble(r5, r7)
            long r2 = r2 + r15
            double r5 = r4.getDouble(r9)
            r4.setDouble(r2, r5)
            long r11 = r11 + r15
            r14 = r41
            r2 = r15
            r5 = r25
            r7 = r27
            goto L_0x027c
        L_0x02be:
            r15 = r2
            long r0 = r0 + r15
            r14 = r41
            goto L_0x026e
        L_0x02c3:
            r6 = 0
            r1 = r41
        L_0x02c7:
            long r2 = r1.slicesl
            int r0 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r0 >= 0) goto L_0x0303
            long r8 = r2 - r6
            long r8 = r8 % r2
            long r8 = r8 * r23
            long r2 = r6 * r23
            r11 = 1
        L_0x02d6:
            int r0 = (r11 > r19 ? 1 : (r11 == r19 ? 0 : -1))
            if (r0 >= 0) goto L_0x02ff
            long r13 = r1.rowsl
            long r13 = r13 - r11
            long r13 = r13 * r17
            long r13 = r13 + r8
            long r15 = r11 * r17
            r25 = r8
            long r8 = r2 + r15
            r15 = r2
            double r2 = r4.getDouble(r8)
            r4.setDouble(r13, r2)
            r2 = 1
            long r13 = r13 + r2
            long r8 = r8 + r2
            double r8 = r4.getDouble(r8)
            double r8 = -r8
            r4.setDouble(r13, r8)
            long r11 = r11 + r2
            r2 = r15
            r8 = r25
            goto L_0x02d6
        L_0x02ff:
            r2 = 1
            long r6 = r6 + r2
            goto L_0x02c7
        L_0x0303:
            r11 = 1
        L_0x0305:
            int r0 = (r11 > r21 ? 1 : (r11 == r21 ? 0 : -1))
            if (r0 >= 0) goto L_0x03af
            long r2 = r11 * r23
            long r5 = r1.slicesl
            long r5 = r5 - r11
            long r5 = r5 * r23
            long r7 = r19 * r17
            long r9 = r2 + r7
            long r7 = r7 + r5
            long r13 = r1.columnsl
            long r13 = r13 + r2
            r25 = r7
            r15 = r11
            r11 = 1
            long r7 = r5 + r11
            double r11 = r4.getDouble(r7)
            r4.setDouble(r13, r11)
            long r11 = r1.columnsl
            long r11 = r11 + r5
            double r13 = r4.getDouble(r7)
            r4.setDouble(r11, r13)
            long r11 = r1.columnsl
            long r11 = r11 + r2
            r13 = 1
            long r11 = r11 + r13
            double r13 = r4.getDouble(r5)
            double r13 = -r13
            r4.setDouble(r11, r13)
            long r11 = r1.columnsl
            long r11 = r11 + r5
            r13 = 1
            long r11 = r11 + r13
            double r13 = r4.getDouble(r5)
            r4.setDouble(r11, r13)
            long r11 = r1.columnsl
            long r11 = r11 + r9
            r27 = r7
            r13 = 1
            long r7 = r25 + r13
            double r13 = r4.getDouble(r7)
            r4.setDouble(r11, r13)
            long r11 = r1.columnsl
            long r11 = r25 + r11
            double r13 = r4.getDouble(r7)
            r4.setDouble(r11, r13)
            long r11 = r1.columnsl
            long r11 = r11 + r9
            r13 = 1
            long r11 = r11 + r13
            r13 = r25
            r25 = r7
            double r7 = r4.getDouble(r13)
            double r7 = -r7
            r4.setDouble(r11, r7)
            long r7 = r1.columnsl
            long r7 = r7 + r13
            r11 = 1
            long r7 = r7 + r11
            double r11 = r4.getDouble(r13)
            r4.setDouble(r7, r11)
            double r7 = r4.getDouble(r2)
            r4.setDouble(r5, r7)
            r5 = 1
            long r2 = r2 + r5
            double r2 = r4.getDouble(r2)
            double r2 = -r2
            r7 = r27
            r4.setDouble(r7, r2)
            double r2 = r4.getDouble(r9)
            r4.setDouble(r13, r2)
            long r9 = r9 + r5
            double r2 = r4.getDouble(r9)
            double r2 = -r2
            r7 = r25
            r4.setDouble(r7, r2)
            long r11 = r15 + r5
            goto L_0x0305
        L_0x03af:
            r5 = 1
            long r2 = r1.columnsl
            double r7 = r4.getDouble(r5)
            r4.setDouble(r2, r7)
            r2 = 0
            r4.setDouble(r5, r2)
            long r19 = r19 * r17
            long r21 = r21 * r23
            long r7 = r19 + r21
            long r9 = r1.columnsl
            long r9 = r19 + r9
            long r11 = r19 + r5
            double r13 = r4.getDouble(r11)
            r4.setDouble(r9, r13)
            r4.setDouble(r11, r2)
            long r9 = r1.columnsl
            long r9 = r21 + r9
            long r11 = r21 + r5
            double r13 = r4.getDouble(r11)
            r4.setDouble(r9, r13)
            r4.setDouble(r11, r2)
            long r9 = r1.columnsl
            long r9 = r9 + r7
            long r11 = r7 + r5
            double r13 = r4.getDouble(r11)
            r4.setDouble(r9, r13)
            r4.setDouble(r11, r2)
            long r9 = r1.columnsl
            long r21 = r21 + r9
            long r9 = r21 + r5
            r4.setDouble(r9, r2)
            long r9 = r1.columnsl
            long r7 = r7 + r9
            long r7 = r7 + r5
            r4.setDouble(r7, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.DoubleFFT_3D.fillSymmetric(pl.edu.icm.jlargearrays.DoubleLargeArray):void");
    }
}
