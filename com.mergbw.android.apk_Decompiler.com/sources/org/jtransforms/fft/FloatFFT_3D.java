package org.jtransforms.fft;

import java.lang.reflect.Array;
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

public class FloatFFT_3D {
    /* access modifiers changed from: private */
    public int columns;
    /* access modifiers changed from: private */
    public long columnsl;
    /* access modifiers changed from: private */
    public FloatFFT_1D fftColumns;
    /* access modifiers changed from: private */
    public FloatFFT_1D fftRows;
    /* access modifiers changed from: private */
    public FloatFFT_1D fftSlices;
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

    public FloatFFT_3D(long j, long j2, long j3) {
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
        FloatFFT_1D floatFFT_1D = new FloatFFT_1D(j);
        this.fftSlices = floatFFT_1D;
        if (j == j2) {
            this.fftRows = floatFFT_1D;
        } else {
            this.fftRows = new FloatFFT_1D(j2);
        }
        if (j == j3) {
            this.fftColumns = this.fftSlices;
        } else if (j2 == j3) {
            this.fftColumns = this.fftRows;
        } else {
            this.fftColumns = new FloatFFT_1D(j3);
        }
    }

    public void complexForward(final float[] fArr) {
        int i;
        int i2;
        Class<FloatFFT_3D> cls = FloatFFT_3D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = 0;
        if (this.isPowerOfTwo) {
            int i4 = this.columns;
            int i5 = i4 * 2;
            this.columns = i5;
            this.sliceStride = this.rows * i5;
            this.rowStride = i5;
            if (numberOfThreads <= 1 || !this.useThreads) {
                xdft3da_sub2(0, -1, fArr, true);
                cdft3db_sub(-1, fArr, true);
            } else {
                xdft3da_subth2(0, -1, fArr, true);
                cdft3db_subth(-1, fArr, true);
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
                    this.fftColumns.complexForward(fArr, (this.rowStride * i10) + i9);
                }
            }
            float[] fArr2 = new float[(this.rows * 2)];
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
                        fArr2[i17] = fArr[i16];
                        fArr2[i17 + 1] = fArr[i16 + 1];
                    }
                    this.fftRows.complexForward(fArr2);
                    for (int i18 = 0; i18 < this.rows; i18++) {
                        int i19 = i12 + i14 + (this.rowStride * i18);
                        int i20 = i18 * 2;
                        fArr[i19] = fArr2[i20];
                        fArr[i19 + 1] = fArr2[i20 + 1];
                    }
                }
                i11++;
            }
            float[] fArr3 = new float[(i * 2)];
            for (int i21 = 0; i21 < this.rows; i21++) {
                int i22 = this.rowStride * i21;
                for (int i23 = 0; i23 < this.columns; i23++) {
                    int i24 = i23 * 2;
                    for (int i25 = 0; i25 < this.slices; i25++) {
                        int i26 = (this.sliceStride * i25) + i22 + i24;
                        int i27 = i25 * 2;
                        fArr3[i27] = fArr[i26];
                        fArr3[i27 + 1] = fArr[i26 + 1];
                    }
                    this.fftSlices.complexForward(fArr3);
                    for (int i28 = 0; i28 < this.slices; i28++) {
                        int i29 = (this.sliceStride * i28) + i22 + i24;
                        int i30 = i28 * 2;
                        fArr[i29] = fArr3[i30];
                        fArr[i29 + 1] = fArr3[i30 + 1];
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
                            int access$000 = FloatFFT_3D.this.sliceStride * i;
                            for (int i2 = 0; i2 < FloatFFT_3D.this.rows; i2++) {
                                FloatFFT_3D.this.fftColumns.complexForward(fArr, (FloatFFT_3D.this.rowStride * i2) + access$000);
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
                        float[] fArr = new float[(FloatFFT_3D.this.rows * 2)];
                        for (int i = i36; i < i37; i++) {
                            int access$000 = FloatFFT_3D.this.sliceStride * i;
                            for (int i2 = 0; i2 < FloatFFT_3D.this.columns; i2++) {
                                int i3 = i2 * 2;
                                for (int i4 = 0; i4 < FloatFFT_3D.this.rows; i4++) {
                                    int access$200 = access$000 + i3 + (FloatFFT_3D.this.rowStride * i4);
                                    int i5 = i4 * 2;
                                    float[] fArr2 = fArr;
                                    fArr[i5] = fArr2[access$200];
                                    fArr[i5 + 1] = fArr2[access$200 + 1];
                                }
                                FloatFFT_3D.this.fftRows.complexForward(fArr);
                                for (int i6 = 0; i6 < FloatFFT_3D.this.rows; i6++) {
                                    int access$2002 = access$000 + i3 + (FloatFFT_3D.this.rowStride * i6);
                                    int i7 = i6 * 2;
                                    float[] fArr3 = fArr;
                                    fArr3[access$2002] = fArr[i7];
                                    fArr3[access$2002 + 1] = fArr[i7 + 1];
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
                        float[] fArr = new float[(FloatFFT_3D.this.slices * 2)];
                        for (int i = i39; i < i40; i++) {
                            int access$200 = FloatFFT_3D.this.rowStride * i;
                            for (int i2 = 0; i2 < FloatFFT_3D.this.columns; i2++) {
                                int i3 = i2 * 2;
                                for (int i4 = 0; i4 < FloatFFT_3D.this.slices; i4++) {
                                    int access$000 = (FloatFFT_3D.this.sliceStride * i4) + access$200 + i3;
                                    int i5 = i4 * 2;
                                    float[] fArr2 = fArr;
                                    fArr[i5] = fArr2[access$000];
                                    fArr[i5 + 1] = fArr2[access$000 + 1];
                                }
                                FloatFFT_3D.this.fftSlices.complexForward(fArr);
                                for (int i6 = 0; i6 < FloatFFT_3D.this.slices; i6++) {
                                    int access$0002 = (FloatFFT_3D.this.sliceStride * i6) + access$200 + i3;
                                    int i7 = i6 * 2;
                                    float[] fArr3 = fArr;
                                    fArr3[access$0002] = fArr[i7];
                                    fArr3[access$0002 + 1] = fArr[i7 + 1];
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

    public void complexForward(FloatLargeArray floatLargeArray) {
        long j;
        FloatLargeArray floatLargeArray2 = floatLargeArray;
        Class<FloatFFT_3D> cls = FloatFFT_3D.class;
        if (floatLargeArray.isLarge() || floatLargeArray.isConstant()) {
            int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
            long j2 = 2;
            if (this.isPowerOfTwo) {
                long j3 = this.columnsl;
                long j4 = 2 * j3;
                this.columnsl = j4;
                this.sliceStridel = this.rowsl * j4;
                this.rowStridel = j4;
                if (numberOfThreads <= 1 || !this.useThreads) {
                    xdft3da_sub2(0, -1, floatLargeArray, true);
                    cdft3db_sub(-1, floatLargeArray2, true);
                } else {
                    xdft3da_subth2(0, -1, floatLargeArray, true);
                    cdft3db_subth(-1, floatLargeArray2, true);
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
                        final FloatLargeArray floatLargeArray3 = floatLargeArray;
                        futureArr[i] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                for (long j = j10; j < j11; j++) {
                                    long access$800 = FloatFFT_3D.this.sliceStridel * j;
                                    for (long j2 = 0; j2 < FloatFFT_3D.this.rowsl; j2++) {
                                        FloatFFT_3D.this.fftColumns.complexForward(floatLargeArray3, (FloatFFT_3D.this.rowStridel * j2) + access$800);
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
                        final FloatLargeArray floatLargeArray4 = floatLargeArray;
                        futureArr[i2] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                long j = 2;
                                FloatLargeArray floatLargeArray = new FloatLargeArray(FloatFFT_3D.this.rowsl * 2, false);
                                long j2 = j12;
                                while (j2 < j13) {
                                    long access$800 = FloatFFT_3D.this.sliceStridel * j2;
                                    long j3 = 0;
                                    while (j3 < FloatFFT_3D.this.columnsl) {
                                        long j4 = j3 * j;
                                        for (long j5 = 0; j5 < FloatFFT_3D.this.rowsl; j5++) {
                                            long access$1000 = access$800 + j4 + (FloatFFT_3D.this.rowStridel * j5);
                                            long j6 = j5 * j;
                                            floatLargeArray.setFloat(j6, floatLargeArray4.getFloat(access$1000));
                                            floatLargeArray.setFloat(j6 + 1, floatLargeArray4.getFloat(access$1000 + 1));
                                        }
                                        FloatFFT_3D.this.fftRows.complexForward(floatLargeArray);
                                        long j7 = 0;
                                        while (j7 < FloatFFT_3D.this.rowsl) {
                                            long access$10002 = access$800 + j4 + (FloatFFT_3D.this.rowStridel * j7);
                                            long j8 = access$800;
                                            long j9 = j7 * j;
                                            floatLargeArray4.setFloat(access$10002, floatLargeArray.getFloat(j9));
                                            floatLargeArray4.setFloat(access$10002 + 1, floatLargeArray.getFloat(j9 + 1));
                                            j7++;
                                            access$800 = j8;
                                            j = 2;
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
                        final FloatLargeArray floatLargeArray5 = floatLargeArray;
                        futureArr[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                long j = 2;
                                FloatLargeArray floatLargeArray = new FloatLargeArray(FloatFFT_3D.this.slicesl * 2, false);
                                long j2 = j15;
                                while (j2 < j16) {
                                    long access$1000 = FloatFFT_3D.this.rowStridel * j2;
                                    long j3 = 0;
                                    while (j3 < FloatFFT_3D.this.columnsl) {
                                        long j4 = j3 * j;
                                        for (long j5 = 0; j5 < FloatFFT_3D.this.slicesl; j5++) {
                                            long access$800 = (FloatFFT_3D.this.sliceStridel * j5) + access$1000 + j4;
                                            long j6 = j5 * j;
                                            floatLargeArray.setFloat(j6, floatLargeArray5.getFloat(access$800));
                                            floatLargeArray.setFloat(j6 + 1, floatLargeArray5.getFloat(access$800 + 1));
                                        }
                                        FloatFFT_3D.this.fftSlices.complexForward(floatLargeArray);
                                        long j7 = 0;
                                        while (j7 < FloatFFT_3D.this.slicesl) {
                                            long access$8002 = (FloatFFT_3D.this.sliceStridel * j7) + access$1000 + j4;
                                            long j8 = access$1000;
                                            long j9 = j7 * j;
                                            floatLargeArray5.setFloat(access$8002, floatLargeArray.getFloat(j9));
                                            floatLargeArray5.setFloat(access$8002 + 1, floatLargeArray.getFloat(j9 + 1));
                                            j7++;
                                            access$1000 = j8;
                                            j = 2;
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
                    return;
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
                    this.fftColumns.complexForward(floatLargeArray2, (this.rowStridel * j21) + j20);
                }
                j19++;
            }
            FloatLargeArray floatLargeArray6 = new FloatLargeArray(this.rowsl * 2, false);
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
                        floatLargeArray6.setFloat(j29, floatLargeArray2.getFloat(j27));
                        floatLargeArray6.setFloat(j29 + 1, floatLargeArray2.getFloat(j27 + 1));
                        j26++;
                        j22 = j28;
                    }
                    long j30 = j22;
                    this.fftRows.complexForward(floatLargeArray6);
                    long j31 = 0;
                    while (j31 < this.rowsl) {
                        long j32 = j23 + j25 + (this.rowStridel * j31);
                        long j33 = j23;
                        long j34 = j31 * 2;
                        floatLargeArray2.setFloat(j32, floatLargeArray6.getFloat(j34));
                        floatLargeArray2.setFloat(j32 + 1, floatLargeArray6.getFloat(j34 + 1));
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
            FloatLargeArray floatLargeArray7 = new FloatLargeArray(this.slicesl * 2, false);
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
                        floatLargeArray7.setFloat(j43, floatLargeArray2.getFloat(j41));
                        floatLargeArray7.setFloat(j43 + 1, floatLargeArray2.getFloat(j41 + 1));
                        j40++;
                        j36 = j42;
                    }
                    long j44 = j36;
                    this.fftSlices.complexForward(floatLargeArray7);
                    long j45 = 0;
                    while (j45 < this.slicesl) {
                        long j46 = (this.sliceStridel * j45) + j37 + j39;
                        long j47 = j37;
                        long j48 = j45 * 2;
                        floatLargeArray2.setFloat(j46, floatLargeArray7.getFloat(j48));
                        floatLargeArray2.setFloat(j46 + 1, floatLargeArray7.getFloat(j48 + 1));
                        j45++;
                        j37 = j47;
                    }
                    long j49 = j37;
                    j38++;
                    j36 = j44;
                }
                j36++;
            }
            long j172 = this.rowsl;
            long j182 = this.columnsl;
            this.sliceStridel = j172 * j182;
            this.rowStridel = j182;
            return;
        }
        complexForward(floatLargeArray.getData());
    }

    public void complexForward(final float[][][] fArr) {
        int i;
        int i2;
        Class<FloatFFT_3D> cls = FloatFFT_3D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = 0;
        if (this.isPowerOfTwo) {
            int i4 = this.columns;
            int i5 = i4 * 2;
            this.columns = i5;
            this.sliceStride = this.rows * i5;
            this.rowStride = i5;
            if (numberOfThreads <= 1 || !this.useThreads) {
                xdft3da_sub2(0, -1, fArr, true);
                cdft3db_sub(-1, fArr, true);
            } else {
                xdft3da_subth2(0, -1, fArr, true);
                cdft3db_subth(-1, fArr, true);
            }
            this.columns = i4;
            this.sliceStride = this.rows * i4;
            this.rowStride = i4;
        } else if (numberOfThreads <= 1 || !this.useThreads || (i2 = this.slices) < numberOfThreads || this.rows < numberOfThreads || this.columns < numberOfThreads) {
            for (int i6 = 0; i6 < this.slices; i6++) {
                for (int i7 = 0; i7 < this.rows; i7++) {
                    this.fftColumns.complexForward(fArr[i6][i7]);
                }
            }
            float[] fArr2 = new float[(this.rows * 2)];
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
                        float[] fArr3 = fArr[i8][i11];
                        fArr2[i12] = fArr3[i10];
                        fArr2[i12 + 1] = fArr3[i10 + 1];
                    }
                    this.fftRows.complexForward(fArr2);
                    for (int i13 = 0; i13 < this.rows; i13++) {
                        int i14 = i13 * 2;
                        float[] fArr4 = fArr[i8][i13];
                        fArr4[i10] = fArr2[i14];
                        fArr4[i10 + 1] = fArr2[i14 + 1];
                    }
                }
                i8++;
            }
            float[] fArr5 = new float[(i * 2)];
            for (int i15 = 0; i15 < this.rows; i15++) {
                for (int i16 = 0; i16 < this.columns; i16++) {
                    int i17 = i16 * 2;
                    for (int i18 = 0; i18 < this.slices; i18++) {
                        int i19 = i18 * 2;
                        float[] fArr6 = fArr[i18][i15];
                        fArr5[i19] = fArr6[i17];
                        fArr5[i19 + 1] = fArr6[i17 + 1];
                    }
                    this.fftSlices.complexForward(fArr5);
                    for (int i20 = 0; i20 < this.slices; i20++) {
                        int i21 = i20 * 2;
                        float[] fArr7 = fArr[i20][i15];
                        fArr7[i17] = fArr5[i21];
                        fArr7[i17 + 1] = fArr5[i21 + 1];
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
                            for (int i2 = 0; i2 < FloatFFT_3D.this.rows; i2++) {
                                FloatFFT_3D.this.fftColumns.complexForward(fArr[i][i2]);
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
                        float[] fArr = new float[(FloatFFT_3D.this.rows * 2)];
                        for (int i = i27; i < i28; i++) {
                            for (int i2 = 0; i2 < FloatFFT_3D.this.columns; i2++) {
                                int i3 = i2 * 2;
                                for (int i4 = 0; i4 < FloatFFT_3D.this.rows; i4++) {
                                    int i5 = i4 * 2;
                                    float[] fArr2 = fArr[i][i4];
                                    fArr[i5] = fArr2[i3];
                                    fArr[i5 + 1] = fArr2[i3 + 1];
                                }
                                FloatFFT_3D.this.fftRows.complexForward(fArr);
                                for (int i6 = 0; i6 < FloatFFT_3D.this.rows; i6++) {
                                    int i7 = i6 * 2;
                                    float[] fArr3 = fArr[i][i6];
                                    fArr3[i3] = fArr[i7];
                                    fArr3[i3 + 1] = fArr[i7 + 1];
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
                        float[] fArr = new float[(FloatFFT_3D.this.slices * 2)];
                        for (int i = i30; i < i31; i++) {
                            for (int i2 = 0; i2 < FloatFFT_3D.this.columns; i2++) {
                                int i3 = i2 * 2;
                                for (int i4 = 0; i4 < FloatFFT_3D.this.slices; i4++) {
                                    int i5 = i4 * 2;
                                    float[] fArr2 = fArr[i4][i];
                                    fArr[i5] = fArr2[i3];
                                    fArr[i5 + 1] = fArr2[i3 + 1];
                                }
                                FloatFFT_3D.this.fftSlices.complexForward(fArr);
                                for (int i6 = 0; i6 < FloatFFT_3D.this.slices; i6++) {
                                    int i7 = i6 * 2;
                                    float[] fArr3 = fArr[i6][i];
                                    fArr3[i3] = fArr[i7];
                                    fArr3[i3 + 1] = fArr[i7 + 1];
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

    public void complexInverse(float[] fArr, boolean z) {
        int i;
        int i2;
        float[] fArr2 = fArr;
        boolean z2 = z;
        Class<FloatFFT_3D> cls = FloatFFT_3D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = 0;
        if (this.isPowerOfTwo) {
            int i4 = this.columns;
            int i5 = i4 * 2;
            this.columns = i5;
            this.sliceStride = this.rows * i5;
            this.rowStride = i5;
            if (numberOfThreads <= 1 || !this.useThreads) {
                xdft3da_sub2(0, 1, fArr2, z2);
                cdft3db_sub(1, fArr2, z2);
            } else {
                xdft3da_subth2(0, 1, fArr2, z2);
                cdft3db_subth(1, fArr2, z2);
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
                    this.fftColumns.complexInverse(fArr2, (this.rowStride * i10) + i9, z2);
                }
            }
            float[] fArr3 = new float[(this.rows * 2)];
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
                        fArr3[i17] = fArr2[i16];
                        fArr3[i17 + 1] = fArr2[i16 + 1];
                    }
                    this.fftRows.complexInverse(fArr3, z2);
                    for (int i18 = 0; i18 < this.rows; i18++) {
                        int i19 = i12 + i14 + (this.rowStride * i18);
                        int i20 = i18 * 2;
                        fArr2[i19] = fArr3[i20];
                        fArr2[i19 + 1] = fArr3[i20 + 1];
                    }
                }
                i11++;
            }
            float[] fArr4 = new float[(i * 2)];
            for (int i21 = 0; i21 < this.rows; i21++) {
                int i22 = this.rowStride * i21;
                for (int i23 = 0; i23 < this.columns; i23++) {
                    int i24 = i23 * 2;
                    for (int i25 = 0; i25 < this.slices; i25++) {
                        int i26 = (this.sliceStride * i25) + i22 + i24;
                        int i27 = i25 * 2;
                        fArr4[i27] = fArr2[i26];
                        fArr4[i27 + 1] = fArr2[i26 + 1];
                    }
                    this.fftSlices.complexInverse(fArr4, z2);
                    for (int i28 = 0; i28 < this.slices; i28++) {
                        int i29 = (this.sliceStride * i28) + i22 + i24;
                        int i30 = i28 * 2;
                        fArr2[i29] = fArr4[i30];
                        fArr2[i29 + 1] = fArr4[i30 + 1];
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
                final float[] fArr5 = fArr;
                final boolean z3 = z;
                futureArr[i32] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i33; i < i34; i++) {
                            int access$000 = FloatFFT_3D.this.sliceStride * i;
                            for (int i2 = 0; i2 < FloatFFT_3D.this.rows; i2++) {
                                FloatFFT_3D.this.fftColumns.complexInverse(fArr5, (FloatFFT_3D.this.rowStride * i2) + access$000, z3);
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
                final float[] fArr6 = fArr;
                final boolean z4 = z;
                futureArr[i35] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        float[] fArr = new float[(FloatFFT_3D.this.rows * 2)];
                        for (int i = i36; i < i37; i++) {
                            int access$000 = FloatFFT_3D.this.sliceStride * i;
                            for (int i2 = 0; i2 < FloatFFT_3D.this.columns; i2++) {
                                int i3 = i2 * 2;
                                for (int i4 = 0; i4 < FloatFFT_3D.this.rows; i4++) {
                                    int access$200 = access$000 + i3 + (FloatFFT_3D.this.rowStride * i4);
                                    int i5 = i4 * 2;
                                    float[] fArr2 = fArr6;
                                    fArr[i5] = fArr2[access$200];
                                    fArr[i5 + 1] = fArr2[access$200 + 1];
                                }
                                FloatFFT_3D.this.fftRows.complexInverse(fArr, z4);
                                for (int i6 = 0; i6 < FloatFFT_3D.this.rows; i6++) {
                                    int access$2002 = access$000 + i3 + (FloatFFT_3D.this.rowStride * i6);
                                    int i7 = i6 * 2;
                                    float[] fArr3 = fArr6;
                                    fArr3[access$2002] = fArr[i7];
                                    fArr3[access$2002 + 1] = fArr[i7 + 1];
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
                final float[] fArr7 = fArr;
                final boolean z5 = z;
                futureArr[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        float[] fArr = new float[(FloatFFT_3D.this.slices * 2)];
                        for (int i = i39; i < i40; i++) {
                            int access$200 = FloatFFT_3D.this.rowStride * i;
                            for (int i2 = 0; i2 < FloatFFT_3D.this.columns; i2++) {
                                int i3 = i2 * 2;
                                for (int i4 = 0; i4 < FloatFFT_3D.this.slices; i4++) {
                                    int access$000 = (FloatFFT_3D.this.sliceStride * i4) + access$200 + i3;
                                    int i5 = i4 * 2;
                                    float[] fArr2 = fArr7;
                                    fArr[i5] = fArr2[access$000];
                                    fArr[i5 + 1] = fArr2[access$000 + 1];
                                }
                                FloatFFT_3D.this.fftSlices.complexInverse(fArr, z5);
                                for (int i6 = 0; i6 < FloatFFT_3D.this.slices; i6++) {
                                    int access$0002 = (FloatFFT_3D.this.sliceStride * i6) + access$200 + i3;
                                    int i7 = i6 * 2;
                                    float[] fArr3 = fArr7;
                                    fArr3[access$0002] = fArr[i7];
                                    fArr3[access$0002 + 1] = fArr[i7 + 1];
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

    public void complexInverse(FloatLargeArray floatLargeArray, boolean z) {
        FloatLargeArray floatLargeArray2 = floatLargeArray;
        boolean z2 = z;
        Class<FloatFFT_3D> cls = FloatFFT_3D.class;
        if (floatLargeArray.isLarge() || floatLargeArray.isConstant()) {
            int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
            long j = 2;
            if (this.isPowerOfTwo) {
                long j2 = this.columnsl;
                long j3 = 2 * j2;
                this.columnsl = j3;
                this.sliceStridel = this.rowsl * j3;
                this.rowStridel = j3;
                if (numberOfThreads <= 1 || !this.useThreads) {
                    xdft3da_sub2(0, 1, floatLargeArray, z);
                    cdft3db_sub(1, floatLargeArray2, z2);
                } else {
                    xdft3da_subth2(0, 1, floatLargeArray, z);
                    cdft3db_subth(1, floatLargeArray2, z2);
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
                        final FloatLargeArray floatLargeArray3 = floatLargeArray;
                        final boolean z3 = z;
                        futureArr[i] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                for (long j = j9; j < j10; j++) {
                                    long access$800 = FloatFFT_3D.this.sliceStridel * j;
                                    for (long j2 = 0; j2 < FloatFFT_3D.this.rowsl; j2++) {
                                        FloatFFT_3D.this.fftColumns.complexInverse(floatLargeArray3, (FloatFFT_3D.this.rowStridel * j2) + access$800, z3);
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
                        final FloatLargeArray floatLargeArray4 = floatLargeArray;
                        long j13 = j8;
                        String str2 = str;
                        final boolean z4 = z;
                        futureArr[i2] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                long j = 2;
                                FloatLargeArray floatLargeArray = new FloatLargeArray(FloatFFT_3D.this.rowsl * 2, false);
                                long j2 = j11;
                                while (j2 < j12) {
                                    long access$800 = FloatFFT_3D.this.sliceStridel * j2;
                                    long j3 = 0;
                                    while (j3 < FloatFFT_3D.this.columnsl) {
                                        long j4 = j3 * j;
                                        for (long j5 = 0; j5 < FloatFFT_3D.this.rowsl; j5++) {
                                            long access$1000 = access$800 + j4 + (FloatFFT_3D.this.rowStridel * j5);
                                            long j6 = j5 * j;
                                            floatLargeArray.setFloat(j6, floatLargeArray4.getFloat(access$1000));
                                            floatLargeArray.setFloat(j6 + 1, floatLargeArray4.getFloat(access$1000 + 1));
                                        }
                                        FloatFFT_3D.this.fftRows.complexInverse(floatLargeArray, z4);
                                        long j7 = 0;
                                        while (j7 < FloatFFT_3D.this.rowsl) {
                                            long access$10002 = access$800 + j4 + (FloatFFT_3D.this.rowStridel * j7);
                                            long j8 = access$800;
                                            long j9 = j7 * j;
                                            floatLargeArray4.setFloat(access$10002, floatLargeArray.getFloat(j9));
                                            floatLargeArray4.setFloat(access$10002 + 1, floatLargeArray.getFloat(j9 + 1));
                                            j7++;
                                            access$800 = j8;
                                            j = 2;
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
                        final FloatLargeArray floatLargeArray5 = floatLargeArray;
                        final boolean z5 = z;
                        futureArr[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                long j = 2;
                                FloatLargeArray floatLargeArray = new FloatLargeArray(FloatFFT_3D.this.slicesl * 2, false);
                                long j2 = j15;
                                while (j2 < j16) {
                                    long access$1000 = FloatFFT_3D.this.rowStridel * j2;
                                    long j3 = 0;
                                    while (j3 < FloatFFT_3D.this.columnsl) {
                                        long j4 = j3 * j;
                                        for (long j5 = 0; j5 < FloatFFT_3D.this.slicesl; j5++) {
                                            long access$800 = (FloatFFT_3D.this.sliceStridel * j5) + access$1000 + j4;
                                            long j6 = j5 * j;
                                            floatLargeArray.setFloat(j6, floatLargeArray5.getFloat(access$800));
                                            floatLargeArray.setFloat(j6 + 1, floatLargeArray5.getFloat(access$800 + 1));
                                        }
                                        FloatFFT_3D.this.fftSlices.complexInverse(floatLargeArray, z5);
                                        long j7 = 0;
                                        while (j7 < FloatFFT_3D.this.slicesl) {
                                            long access$8002 = (FloatFFT_3D.this.sliceStridel * j7) + access$1000 + j4;
                                            long j8 = access$1000;
                                            long j9 = j7 * j;
                                            floatLargeArray5.setFloat(access$8002, floatLargeArray.getFloat(j9));
                                            floatLargeArray5.setFloat(access$8002 + 1, floatLargeArray.getFloat(j9 + 1));
                                            j7++;
                                            access$1000 = j8;
                                            j = 2;
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
                    return;
                }
            }
            for (long j19 = 0; j19 < this.slicesl; j19++) {
                long j20 = this.sliceStridel * j19;
                for (long j21 = 0; j21 < this.rowsl; j21++) {
                    this.fftColumns.complexInverse(floatLargeArray, (this.rowStridel * j21) + j20, z);
                }
                FloatLargeArray floatLargeArray6 = floatLargeArray;
                boolean z6 = z;
            }
            FloatLargeArray floatLargeArray7 = floatLargeArray;
            boolean z7 = z;
            FloatLargeArray floatLargeArray8 = new FloatLargeArray(this.rowsl * 2, false);
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
                        floatLargeArray8.setFloat(j29, floatLargeArray7.getFloat(j27));
                        floatLargeArray8.setFloat(j29 + 1, floatLargeArray7.getFloat(j27 + 1));
                        j26++;
                        j23 = j23;
                        j22 = j28;
                    }
                    long j30 = j22;
                    long j31 = j23;
                    this.fftRows.complexInverse(floatLargeArray8, z7);
                    long j32 = 0;
                    while (j32 < this.rowsl) {
                        long j33 = j31 + j25 + (this.rowStridel * j32);
                        long j34 = j25;
                        long j35 = j32 * 2;
                        floatLargeArray7.setFloat(j33, floatLargeArray8.getFloat(j35));
                        floatLargeArray7.setFloat(j33 + 1, floatLargeArray8.getFloat(j35 + 1));
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
            FloatLargeArray floatLargeArray9 = new FloatLargeArray(this.slicesl * 2, false);
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
                        floatLargeArray9.setFloat(j43, floatLargeArray7.getFloat(j41));
                        floatLargeArray9.setFloat(j43 + 1, floatLargeArray7.getFloat(j41 + 1));
                        j40++;
                        j38 = j38;
                        j36 = j42;
                    }
                    long j44 = j36;
                    long j45 = j38;
                    this.fftSlices.complexInverse(floatLargeArray9, z7);
                    for (long j46 = 0; j46 < this.slicesl; j46++) {
                        long j47 = (this.sliceStridel * j46) + j37 + j39;
                        long j48 = j46 * 2;
                        floatLargeArray7.setFloat(j47, floatLargeArray9.getFloat(j48));
                        floatLargeArray7.setFloat(j47 + 1, floatLargeArray9.getFloat(j48 + 1));
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
            return;
        }
        complexInverse(floatLargeArray.getData(), z2);
    }

    public void complexInverse(float[][][] fArr, boolean z) {
        int i;
        int i2;
        float[][][] fArr2 = fArr;
        boolean z2 = z;
        Class<FloatFFT_3D> cls = FloatFFT_3D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = 0;
        if (this.isPowerOfTwo) {
            int i4 = this.columns;
            int i5 = i4 * 2;
            this.columns = i5;
            this.sliceStride = this.rows * i5;
            this.rowStride = i5;
            if (numberOfThreads <= 1 || !this.useThreads) {
                xdft3da_sub2(0, 1, fArr2, z2);
                cdft3db_sub(1, fArr2, z2);
            } else {
                xdft3da_subth2(0, 1, fArr2, z2);
                cdft3db_subth(1, fArr2, z2);
            }
            this.columns = i4;
            this.sliceStride = this.rows * i4;
            this.rowStride = i4;
        } else if (numberOfThreads <= 1 || !this.useThreads || (i2 = this.slices) < numberOfThreads || this.rows < numberOfThreads || this.columns < numberOfThreads) {
            for (int i6 = 0; i6 < this.slices; i6++) {
                for (int i7 = 0; i7 < this.rows; i7++) {
                    this.fftColumns.complexInverse(fArr2[i6][i7], z2);
                }
            }
            float[] fArr3 = new float[(this.rows * 2)];
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
                        float[] fArr4 = fArr2[i8][i11];
                        fArr3[i12] = fArr4[i10];
                        fArr3[i12 + 1] = fArr4[i10 + 1];
                    }
                    this.fftRows.complexInverse(fArr3, z2);
                    for (int i13 = 0; i13 < this.rows; i13++) {
                        int i14 = i13 * 2;
                        float[] fArr5 = fArr2[i8][i13];
                        fArr5[i10] = fArr3[i14];
                        fArr5[i10 + 1] = fArr3[i14 + 1];
                    }
                }
                i8++;
            }
            float[] fArr6 = new float[(i * 2)];
            for (int i15 = 0; i15 < this.rows; i15++) {
                for (int i16 = 0; i16 < this.columns; i16++) {
                    int i17 = i16 * 2;
                    for (int i18 = 0; i18 < this.slices; i18++) {
                        int i19 = i18 * 2;
                        float[] fArr7 = fArr2[i18][i15];
                        fArr6[i19] = fArr7[i17];
                        fArr6[i19 + 1] = fArr7[i17 + 1];
                    }
                    this.fftSlices.complexInverse(fArr6, z2);
                    for (int i20 = 0; i20 < this.slices; i20++) {
                        int i21 = i20 * 2;
                        float[] fArr8 = fArr2[i20][i15];
                        fArr8[i17] = fArr6[i21];
                        fArr8[i17 + 1] = fArr6[i21 + 1];
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
                final float[][][] fArr9 = fArr;
                final boolean z3 = z;
                futureArr[i23] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i24; i < i25; i++) {
                            for (int i2 = 0; i2 < FloatFFT_3D.this.rows; i2++) {
                                FloatFFT_3D.this.fftColumns.complexInverse(fArr9[i][i2], z3);
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
                final float[][][] fArr10 = fArr;
                final boolean z4 = z;
                futureArr[i26] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        float[] fArr = new float[(FloatFFT_3D.this.rows * 2)];
                        for (int i = i27; i < i28; i++) {
                            for (int i2 = 0; i2 < FloatFFT_3D.this.columns; i2++) {
                                int i3 = i2 * 2;
                                for (int i4 = 0; i4 < FloatFFT_3D.this.rows; i4++) {
                                    int i5 = i4 * 2;
                                    float[] fArr2 = fArr10[i][i4];
                                    fArr[i5] = fArr2[i3];
                                    fArr[i5 + 1] = fArr2[i3 + 1];
                                }
                                FloatFFT_3D.this.fftRows.complexInverse(fArr, z4);
                                for (int i6 = 0; i6 < FloatFFT_3D.this.rows; i6++) {
                                    int i7 = i6 * 2;
                                    float[] fArr3 = fArr10[i][i6];
                                    fArr3[i3] = fArr[i7];
                                    fArr3[i3 + 1] = fArr[i7 + 1];
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
                final float[][][] fArr11 = fArr;
                final boolean z5 = z;
                futureArr[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        float[] fArr = new float[(FloatFFT_3D.this.slices * 2)];
                        for (int i = i30; i < i31; i++) {
                            for (int i2 = 0; i2 < FloatFFT_3D.this.columns; i2++) {
                                int i3 = i2 * 2;
                                for (int i4 = 0; i4 < FloatFFT_3D.this.slices; i4++) {
                                    int i5 = i4 * 2;
                                    float[] fArr2 = fArr11[i4][i];
                                    fArr[i5] = fArr2[i3];
                                    fArr[i5 + 1] = fArr2[i3 + 1];
                                }
                                FloatFFT_3D.this.fftSlices.complexInverse(fArr, z5);
                                for (int i6 = 0; i6 < FloatFFT_3D.this.slices; i6++) {
                                    int i7 = i6 * 2;
                                    float[] fArr3 = fArr11[i6][i];
                                    fArr3[i3] = fArr[i7];
                                    fArr3[i3 + 1] = fArr[i7 + 1];
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

    public void realForward(float[] fArr) {
        if (!this.isPowerOfTwo) {
            throw new IllegalArgumentException("slices, rows and columns must be power of two numbers");
        } else if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
            xdft3da_sub1(1, -1, fArr, true);
            cdft3db_sub(-1, fArr, true);
            rdft3d_sub(1, fArr);
        } else {
            xdft3da_subth1(1, -1, fArr, true);
            cdft3db_subth(-1, fArr, true);
            rdft3d_sub(1, fArr);
        }
    }

    public void realForward(FloatLargeArray floatLargeArray) {
        if (!this.isPowerOfTwo) {
            throw new IllegalArgumentException("slices, rows and columns must be power of two numbers");
        } else if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
            xdft3da_sub1(1, -1, floatLargeArray, true);
            cdft3db_sub(-1, floatLargeArray, true);
            rdft3d_sub(1, floatLargeArray);
        } else {
            xdft3da_subth1(1, -1, floatLargeArray, true);
            cdft3db_subth(-1, floatLargeArray, true);
            rdft3d_sub(1, floatLargeArray);
        }
    }

    public void realForward(float[][][] fArr) {
        if (!this.isPowerOfTwo) {
            throw new IllegalArgumentException("slices, rows and columns must be power of two numbers");
        } else if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
            xdft3da_sub1(1, -1, fArr, true);
            cdft3db_sub(-1, fArr, true);
            rdft3d_sub(1, fArr);
        } else {
            xdft3da_subth1(1, -1, fArr, true);
            cdft3db_subth(-1, fArr, true);
            rdft3d_sub(1, fArr);
        }
    }

    public void realForwardFull(float[] fArr) {
        if (this.isPowerOfTwo) {
            if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
                xdft3da_sub2(1, -1, fArr, true);
                cdft3db_sub(-1, fArr, true);
                rdft3d_sub(1, fArr);
            } else {
                xdft3da_subth2(1, -1, fArr, true);
                cdft3db_subth(-1, fArr, true);
                rdft3d_sub(1, fArr);
            }
            fillSymmetric(fArr);
            return;
        }
        mixedRadixRealForwardFull(fArr);
    }

    public void realForwardFull(FloatLargeArray floatLargeArray) {
        if (this.isPowerOfTwo) {
            if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
                xdft3da_sub2(1, -1, floatLargeArray, true);
                cdft3db_sub(-1, floatLargeArray, true);
                rdft3d_sub(1, floatLargeArray);
            } else {
                xdft3da_subth2(1, -1, floatLargeArray, true);
                cdft3db_subth(-1, floatLargeArray, true);
                rdft3d_sub(1, floatLargeArray);
            }
            fillSymmetric(floatLargeArray);
            return;
        }
        mixedRadixRealForwardFull(floatLargeArray);
    }

    public void realForwardFull(float[][][] fArr) {
        if (this.isPowerOfTwo) {
            if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
                xdft3da_sub2(1, -1, fArr, true);
                cdft3db_sub(-1, fArr, true);
                rdft3d_sub(1, fArr);
            } else {
                xdft3da_subth2(1, -1, fArr, true);
                cdft3db_subth(-1, fArr, true);
                rdft3d_sub(1, fArr);
            }
            fillSymmetric(fArr);
            return;
        }
        mixedRadixRealForwardFull(fArr);
    }

    public void realInverse(float[] fArr, boolean z) {
        if (!this.isPowerOfTwo) {
            throw new IllegalArgumentException("slices, rows and columns must be power of two numbers");
        } else if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
            rdft3d_sub(-1, fArr);
            cdft3db_sub(1, fArr, z);
            xdft3da_sub1(1, 1, fArr, z);
        } else {
            rdft3d_sub(-1, fArr);
            cdft3db_subth(1, fArr, z);
            xdft3da_subth1(1, 1, fArr, z);
        }
    }

    public void realInverse(FloatLargeArray floatLargeArray, boolean z) {
        if (!this.isPowerOfTwo) {
            throw new IllegalArgumentException("slices, rows and columns must be power of two numbers");
        } else if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
            rdft3d_sub(-1, floatLargeArray);
            cdft3db_sub(1, floatLargeArray, z);
            xdft3da_sub1(1, 1, floatLargeArray, z);
        } else {
            rdft3d_sub(-1, floatLargeArray);
            cdft3db_subth(1, floatLargeArray, z);
            xdft3da_subth1(1, 1, floatLargeArray, z);
        }
    }

    public void realInverse(float[][][] fArr, boolean z) {
        if (!this.isPowerOfTwo) {
            throw new IllegalArgumentException("slices, rows and columns must be power of two numbers");
        } else if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
            rdft3d_sub(-1, fArr);
            cdft3db_sub(1, fArr, z);
            xdft3da_sub1(1, 1, fArr, z);
        } else {
            rdft3d_sub(-1, fArr);
            cdft3db_subth(1, fArr, z);
            xdft3da_subth1(1, 1, fArr, z);
        }
    }

    public void realInverseFull(float[] fArr, boolean z) {
        if (this.isPowerOfTwo) {
            if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
                xdft3da_sub2(1, 1, fArr, z);
                cdft3db_sub(1, fArr, z);
                rdft3d_sub(1, fArr);
            } else {
                xdft3da_subth2(1, 1, fArr, z);
                cdft3db_subth(1, fArr, z);
                rdft3d_sub(1, fArr);
            }
            fillSymmetric(fArr);
            return;
        }
        mixedRadixRealInverseFull(fArr, z);
    }

    public void realInverseFull(FloatLargeArray floatLargeArray, boolean z) {
        if (this.isPowerOfTwo) {
            if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
                xdft3da_sub2(1, 1, floatLargeArray, z);
                cdft3db_sub(1, floatLargeArray, z);
                rdft3d_sub(1, floatLargeArray);
            } else {
                xdft3da_subth2(1, 1, floatLargeArray, z);
                cdft3db_subth(1, floatLargeArray, z);
                rdft3d_sub(1, floatLargeArray);
            }
            fillSymmetric(floatLargeArray);
            return;
        }
        mixedRadixRealInverseFull(floatLargeArray, z);
    }

    public void realInverseFull(float[][][] fArr, boolean z) {
        if (this.isPowerOfTwo) {
            if (ConcurrencyUtils.getNumberOfThreads() <= 1 || !this.useThreads) {
                xdft3da_sub2(1, 1, fArr, z);
                cdft3db_sub(1, fArr, z);
                rdft3d_sub(1, fArr);
            } else {
                xdft3da_subth2(1, 1, fArr, z);
                cdft3db_subth(1, fArr, z);
                rdft3d_sub(1, fArr);
            }
            fillSymmetric(fArr);
            return;
        }
        mixedRadixRealInverseFull(fArr, z);
    }

    private void mixedRadixRealForwardFull(float[][][] fArr) {
        int i;
        int i2;
        int i3;
        final float[][][] fArr2 = fArr;
        Class<FloatFFT_3D> cls = FloatFFT_3D.class;
        int i4 = this.rows;
        float[] fArr3 = new float[(i4 * 2)];
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
                    this.fftColumns.realForwardFull(fArr2[i8][i9]);
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
                        float[] fArr4 = fArr2[i10][i13];
                        fArr3[i14] = fArr4[i12];
                        fArr3[i14 + 1] = fArr4[i12 + 1];
                    }
                    this.fftRows.complexForward(fArr3);
                    for (int i15 = 0; i15 < this.rows; i15++) {
                        int i16 = i15 * 2;
                        float[] fArr5 = fArr2[i10][i15];
                        fArr5[i12] = fArr3[i16];
                        fArr5[i12 + 1] = fArr3[i16 + 1];
                    }
                }
                i10++;
            }
            float[] fArr6 = new float[(i2 * 2)];
            for (int i17 = 0; i17 < i5; i17++) {
                for (int i18 = 0; i18 < this.columns; i18++) {
                    int i19 = i18 * 2;
                    for (int i20 = 0; i20 < this.slices; i20++) {
                        int i21 = i20 * 2;
                        float[] fArr7 = fArr2[i20][i17];
                        fArr6[i21] = fArr7[i19];
                        fArr6[i21 + 1] = fArr7[i19 + 1];
                    }
                    this.fftSlices.complexForward(fArr6);
                    for (int i22 = 0; i22 < this.slices; i22++) {
                        int i23 = i22 * 2;
                        float[] fArr8 = fArr2[i22][i17];
                        fArr8[i19] = fArr6[i23];
                        fArr8[i19 + 1] = fArr6[i23 + 1];
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
                            float[] fArr9 = fArr2[i26][i28];
                            float[] fArr10 = fArr2[i24][i27];
                            fArr9[i31 % i6] = fArr10[i30];
                            fArr9[(i31 + 1) % i6] = -fArr10[i30 + 1];
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
                            for (int i2 = 0; i2 < FloatFFT_3D.this.rows; i2++) {
                                FloatFFT_3D.this.fftColumns.realForwardFull(fArr2[i][i2]);
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
                        float[] fArr = new float[(FloatFFT_3D.this.rows * 2)];
                        for (int i = i37; i < i38; i++) {
                            for (int i2 = 0; i2 < FloatFFT_3D.this.columns; i2++) {
                                int i3 = i2 * 2;
                                for (int i4 = 0; i4 < FloatFFT_3D.this.rows; i4++) {
                                    int i5 = i4 * 2;
                                    float[] fArr2 = fArr2[i][i4];
                                    fArr[i5] = fArr2[i3];
                                    fArr[i5 + 1] = fArr2[i3 + 1];
                                }
                                FloatFFT_3D.this.fftRows.complexForward(fArr);
                                for (int i6 = 0; i6 < FloatFFT_3D.this.rows; i6++) {
                                    int i7 = i6 * 2;
                                    float[] fArr3 = fArr2[i][i6];
                                    fArr3[i3] = fArr[i7];
                                    fArr3[i3 + 1] = fArr[i7 + 1];
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
                        float[] fArr = new float[(FloatFFT_3D.this.slices * 2)];
                        for (int i = i41; i < i42; i++) {
                            for (int i2 = 0; i2 < FloatFFT_3D.this.columns; i2++) {
                                int i3 = i2 * 2;
                                for (int i4 = 0; i4 < FloatFFT_3D.this.slices; i4++) {
                                    int i5 = i4 * 2;
                                    float[] fArr2 = fArr2[i4][i];
                                    fArr[i5] = fArr2[i3];
                                    fArr[i5 + 1] = fArr2[i3 + 1];
                                }
                                FloatFFT_3D.this.fftSlices.complexForward(fArr);
                                for (int i6 = 0; i6 < FloatFFT_3D.this.slices; i6++) {
                                    int i7 = i6 * 2;
                                    float[] fArr3 = fArr2[i6][i];
                                    fArr3[i3] = fArr[i7];
                                    fArr3[i3 + 1] = fArr[i7 + 1];
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
                final float[][][] fArr11 = fArr;
                futureArr[i49] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i45; i < i46; i++) {
                            int access$600 = (FloatFFT_3D.this.slices - i) % FloatFFT_3D.this.slices;
                            for (int i2 = 1; i2 < i47; i2++) {
                                int access$100 = FloatFFT_3D.this.rows - i2;
                                for (int i3 = 0; i3 < FloatFFT_3D.this.columns; i3++) {
                                    int i4 = i3 * 2;
                                    int i5 = i48;
                                    int i6 = i5 - i4;
                                    float[][][] fArr = fArr11;
                                    float[] fArr2 = fArr[access$600][access$100];
                                    float[] fArr3 = fArr[i][i2];
                                    fArr2[i6 % i5] = fArr3[i4];
                                    fArr2[(i6 + 1) % i5] = -fArr3[i4 + 1];
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

    private void mixedRadixRealInverseFull(float[][][] fArr, boolean z) {
        int i;
        int i2;
        int i3;
        boolean z2 = z;
        Class<FloatFFT_3D> cls = FloatFFT_3D.class;
        int i4 = this.rows;
        float[] fArr2 = new float[(i4 * 2)];
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
                    this.fftColumns.realInverseFull(fArr[i8][i9], z2);
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
                        float[] fArr3 = fArr[i10][i13];
                        fArr2[i14] = fArr3[i12];
                        fArr2[i14 + 1] = fArr3[i12 + 1];
                    }
                    this.fftRows.complexInverse(fArr2, z2);
                    for (int i15 = 0; i15 < this.rows; i15++) {
                        int i16 = i15 * 2;
                        float[] fArr4 = fArr[i10][i15];
                        fArr4[i12] = fArr2[i16];
                        fArr4[i12 + 1] = fArr2[i16 + 1];
                    }
                }
                i10++;
            }
            float[] fArr5 = new float[(i2 * 2)];
            for (int i17 = 0; i17 < i5; i17++) {
                for (int i18 = 0; i18 < this.columns; i18++) {
                    int i19 = i18 * 2;
                    for (int i20 = 0; i20 < this.slices; i20++) {
                        int i21 = i20 * 2;
                        float[] fArr6 = fArr[i20][i17];
                        fArr5[i21] = fArr6[i19];
                        fArr5[i21 + 1] = fArr6[i19 + 1];
                    }
                    this.fftSlices.complexInverse(fArr5, z2);
                    for (int i22 = 0; i22 < this.slices; i22++) {
                        int i23 = i22 * 2;
                        float[] fArr7 = fArr[i22][i17];
                        fArr7[i19] = fArr5[i23];
                        fArr7[i19 + 1] = fArr5[i23 + 1];
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
                            float[] fArr8 = fArr[i26][i28];
                            float[] fArr9 = fArr[i24][i27];
                            fArr8[i31 % i6] = fArr9[i30];
                            fArr8[(i31 + 1) % i6] = -fArr9[i30 + 1];
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
                final float[][][] fArr10 = fArr;
                final boolean z3 = z;
                futureArr[i33] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i34; i < i35; i++) {
                            for (int i2 = 0; i2 < FloatFFT_3D.this.rows; i2++) {
                                FloatFFT_3D.this.fftColumns.realInverseFull(fArr10[i][i2], z3);
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
                final float[][][] fArr11 = fArr;
                String str2 = str;
                final boolean z4 = z;
                futureArr[i36] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        float[] fArr = new float[(FloatFFT_3D.this.rows * 2)];
                        for (int i = i37; i < i38; i++) {
                            for (int i2 = 0; i2 < FloatFFT_3D.this.columns; i2++) {
                                int i3 = i2 * 2;
                                for (int i4 = 0; i4 < FloatFFT_3D.this.rows; i4++) {
                                    int i5 = i4 * 2;
                                    float[] fArr2 = fArr11[i][i4];
                                    fArr[i5] = fArr2[i3];
                                    fArr[i5 + 1] = fArr2[i3 + 1];
                                }
                                FloatFFT_3D.this.fftRows.complexInverse(fArr, z4);
                                for (int i6 = 0; i6 < FloatFFT_3D.this.rows; i6++) {
                                    int i7 = i6 * 2;
                                    float[] fArr3 = fArr11[i][i6];
                                    fArr3[i3] = fArr[i7];
                                    fArr3[i3 + 1] = fArr[i7 + 1];
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
                final float[][][] fArr12 = fArr;
                int i43 = i40;
                final boolean z5 = z;
                futureArr[i43] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        float[] fArr = new float[(FloatFFT_3D.this.slices * 2)];
                        for (int i = i41; i < i42; i++) {
                            for (int i2 = 0; i2 < FloatFFT_3D.this.columns; i2++) {
                                int i3 = i2 * 2;
                                for (int i4 = 0; i4 < FloatFFT_3D.this.slices; i4++) {
                                    int i5 = i4 * 2;
                                    float[] fArr2 = fArr12[i4][i];
                                    fArr[i5] = fArr2[i3];
                                    fArr[i5 + 1] = fArr2[i3 + 1];
                                }
                                FloatFFT_3D.this.fftSlices.complexInverse(fArr, z5);
                                for (int i6 = 0; i6 < FloatFFT_3D.this.slices; i6++) {
                                    int i7 = i6 * 2;
                                    float[] fArr3 = fArr12[i6][i];
                                    fArr3[i3] = fArr[i7];
                                    fArr3[i3 + 1] = fArr[i7 + 1];
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
                final float[][][] fArr13 = fArr;
                futureArr[i45] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i46; i < i47; i++) {
                            int access$600 = (FloatFFT_3D.this.slices - i) % FloatFFT_3D.this.slices;
                            for (int i2 = 1; i2 < i48; i2++) {
                                int access$100 = FloatFFT_3D.this.rows - i2;
                                for (int i3 = 0; i3 < FloatFFT_3D.this.columns; i3++) {
                                    int i4 = i3 * 2;
                                    int i5 = i49;
                                    int i6 = i5 - i4;
                                    float[][][] fArr = fArr13;
                                    float[] fArr2 = fArr[access$600][access$100];
                                    float[] fArr3 = fArr[i][i2];
                                    fArr2[i6 % i5] = fArr3[i4];
                                    fArr2[(i6 + 1) % i5] = -fArr3[i4 + 1];
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

    private void mixedRadixRealForwardFull(float[] fArr) {
        int i;
        int i2;
        float[] fArr2 = fArr;
        Class<FloatFFT_3D> cls = FloatFFT_3D.class;
        int i3 = this.columns * 2;
        float[] fArr3 = new float[i3];
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
            int i10 = i6;
            for (int i11 = this.slices - 1; i11 >= 0; i11--) {
                int i12 = this.sliceStride * i11;
                int i13 = i11 * i7;
                for (int i14 = this.rows - 1; i14 >= 0; i14--) {
                    System.arraycopy(fArr2, (this.rowStride * i14) + i12, fArr3, 0, this.columns);
                    this.fftColumns.realForwardFull(fArr3);
                    System.arraycopy(fArr3, 0, fArr2, (i14 * i8) + i13, i3);
                }
            }
            float[] fArr4 = new float[(this.rows * 2)];
            int i15 = 0;
            while (true) {
                i2 = this.slices;
                if (i15 >= i2) {
                    break;
                }
                int i16 = i15 * i7;
                for (int i17 = 0; i17 < this.columns; i17++) {
                    int i18 = i17 * 2;
                    for (int i19 = 0; i19 < this.rows; i19++) {
                        int i20 = i19 * 2;
                        int i21 = (i19 * i8) + i16 + i18;
                        fArr4[i20] = fArr2[i21];
                        fArr4[i20 + 1] = fArr2[i21 + 1];
                    }
                    this.fftRows.complexForward(fArr4);
                    for (int i22 = 0; i22 < this.rows; i22++) {
                        int i23 = i22 * 2;
                        int i24 = (i22 * i8) + i16 + i18;
                        fArr2[i24] = fArr4[i23];
                        fArr2[i24 + 1] = fArr4[i23 + 1];
                    }
                }
                i15++;
            }
            float[] fArr5 = new float[(i2 * 2)];
            for (int i25 = 0; i25 < i5; i25++) {
                int i26 = i25 * i8;
                for (int i27 = 0; i27 < this.columns; i27++) {
                    int i28 = i27 * 2;
                    for (int i29 = 0; i29 < this.slices; i29++) {
                        int i30 = i29 * 2;
                        int i31 = (i29 * i7) + i26 + i28;
                        fArr5[i30] = fArr2[i31];
                        fArr5[i30 + 1] = fArr2[i31 + 1];
                    }
                    this.fftSlices.complexForward(fArr5);
                    for (int i32 = 0; i32 < this.slices; i32++) {
                        int i33 = i32 * 2;
                        int i34 = (i32 * i7) + i26 + i28;
                        fArr2[i34] = fArr5[i33];
                        fArr2[i34 + 1] = fArr5[i33 + 1];
                    }
                }
            }
            int i35 = 0;
            while (true) {
                int i36 = this.slices;
                if (i35 < i36) {
                    int i37 = ((i36 - i35) % i36) * i7;
                    int i38 = i35 * i7;
                    int i39 = i10;
                    for (int i40 = 1; i40 < i39; i40++) {
                        int i41 = i40 * i8;
                        int i42 = ((this.rows - i40) * i8) + i37;
                        for (int i43 = 0; i43 < this.columns; i43++) {
                            int i44 = i43 * 2;
                            int i45 = i3 - i44;
                            int i46 = i38 + i41 + i44;
                            fArr2[(i45 % i3) + i42] = fArr2[i46];
                            fArr2[((i45 + 1) % i3) + i42] = -fArr2[i46 + 1];
                        }
                    }
                    i35++;
                    i10 = i39;
                } else {
                    return;
                }
            }
        } else {
            Future[] futureArr = new Future[numberOfThreads];
            int i47 = i9 / numberOfThreads;
            int i48 = 0;
            while (i48 < numberOfThreads) {
                int i49 = (this.slices - 1) - (i48 * i47);
                final int i50 = i3;
                Future[] futureArr2 = futureArr;
                final int i51 = i49;
                final int i52 = i48 == numberOfThreads + -1 ? i9 + 1 : i49 - i47;
                int i53 = numberOfThreads;
                final int i54 = i7;
                int i55 = i9;
                final float[] fArr6 = fArr;
                int i56 = i6;
                final int i57 = i8;
                futureArr2[i48] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        float[] fArr = new float[i50];
                        for (int i = i51; i >= i52; i--) {
                            int access$000 = FloatFFT_3D.this.sliceStride * i;
                            int i2 = i54 * i;
                            for (int access$100 = FloatFFT_3D.this.rows - 1; access$100 >= 0; access$100--) {
                                System.arraycopy(fArr6, (FloatFFT_3D.this.rowStride * access$100) + access$000, fArr, 0, FloatFFT_3D.this.columns);
                                FloatFFT_3D.this.fftColumns.realForwardFull(fArr);
                                System.arraycopy(fArr, 0, fArr6, (i57 * access$100) + i2, i50);
                            }
                        }
                    }
                });
                i48++;
                i9 = i55;
                i6 = i56;
                futureArr = futureArr2;
                numberOfThreads = i53;
            }
            Future[] futureArr3 = futureArr;
            int i58 = numberOfThreads;
            int i59 = i9;
            int i60 = i6;
            String str = null;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
            } catch (ExecutionException e2) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
            }
            int i61 = i59 + 1;
            int i62 = this.rows;
            int[] iArr = new int[3];
            iArr[2] = i3;
            iArr[1] = i62;
            iArr[0] = i61;
            float[][][] fArr7 = (float[][][]) Array.newInstance(Float.TYPE, iArr);
            int i63 = 0;
            int i64 = i58;
            while (i63 < i64) {
                final int i65 = i63 * i47;
                final int i66 = i63 == i64 + -1 ? i61 : i65 + i47;
                final float[] fArr8 = fArr;
                final float[][][] fArr9 = fArr7;
                futureArr3[i63] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i65; i < i66; i++) {
                            int access$000 = FloatFFT_3D.this.sliceStride * i;
                            for (int i2 = 0; i2 < FloatFFT_3D.this.rows; i2++) {
                                System.arraycopy(fArr8, (FloatFFT_3D.this.rowStride * i2) + access$000, fArr9[i][i2], 0, FloatFFT_3D.this.columns);
                                FloatFFT_3D.this.fftColumns.realForwardFull(fArr9[i][i2]);
                            }
                        }
                    }
                });
                i63++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e3) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e4);
            }
            int i67 = 0;
            while (i67 < i64) {
                final int i68 = i67 * i47;
                final int i69 = i67 == i64 + -1 ? i61 : i68 + i47;
                final int i70 = i7;
                final float[][][] fArr10 = fArr7;
                final float[] fArr11 = fArr;
                String str2 = str;
                final int i71 = i8;
                int i72 = i64;
                final int i73 = i3;
                futureArr3[i67] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i68; i < i69; i++) {
                            int i2 = i70 * i;
                            for (int i3 = 0; i3 < FloatFFT_3D.this.rows; i3++) {
                                System.arraycopy(fArr10[i][i3], 0, fArr11, (i71 * i3) + i2, i73);
                            }
                        }
                    }
                });
                i67++;
                str = str2;
                i64 = i72;
            }
            String str3 = str;
            int i74 = i64;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e5) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e5);
            } catch (ExecutionException e6) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e6);
            }
            int i75 = i74;
            int i76 = this.slices / i75;
            int i77 = 0;
            while (i77 < i75) {
                final int i78 = i77 * i76;
                final int i79 = i77 == i75 + -1 ? this.slices : i78 + i76;
                final int i80 = i7;
                final int i81 = i8;
                final float[] fArr12 = fArr;
                futureArr3[i77] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        float[] fArr = new float[(FloatFFT_3D.this.rows * 2)];
                        for (int i = i78; i < i79; i++) {
                            int i2 = i80 * i;
                            for (int i3 = 0; i3 < FloatFFT_3D.this.columns; i3++) {
                                int i4 = i3 * 2;
                                for (int i5 = 0; i5 < FloatFFT_3D.this.rows; i5++) {
                                    int i6 = (i81 * i5) + i2 + i4;
                                    int i7 = i5 * 2;
                                    float[] fArr2 = fArr12;
                                    fArr[i7] = fArr2[i6];
                                    fArr[i7 + 1] = fArr2[i6 + 1];
                                }
                                FloatFFT_3D.this.fftRows.complexForward(fArr);
                                for (int i8 = 0; i8 < FloatFFT_3D.this.rows; i8++) {
                                    int i9 = (i81 * i8) + i2 + i4;
                                    int i10 = i8 * 2;
                                    float[] fArr3 = fArr12;
                                    fArr3[i9] = fArr[i10];
                                    fArr3[i9 + 1] = fArr[i10 + 1];
                                }
                            }
                        }
                    }
                });
                i77++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e7) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e7);
            } catch (ExecutionException e8) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e8);
            }
            int i82 = i5 / i75;
            int i83 = 0;
            while (i83 < i75) {
                final int i84 = i83 * i82;
                final int i85 = i83 == i75 + -1 ? i5 : i84 + i82;
                final int i86 = i8;
                final int i87 = i7;
                final float[] fArr13 = fArr;
                futureArr3[i83] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        float[] fArr = new float[(FloatFFT_3D.this.slices * 2)];
                        for (int i = i84; i < i85; i++) {
                            int i2 = i86 * i;
                            for (int i3 = 0; i3 < FloatFFT_3D.this.columns; i3++) {
                                int i4 = i3 * 2;
                                for (int i5 = 0; i5 < FloatFFT_3D.this.slices; i5++) {
                                    int i6 = i5 * 2;
                                    int i7 = (i87 * i5) + i2 + i4;
                                    float[] fArr2 = fArr13;
                                    fArr[i6] = fArr2[i7];
                                    fArr[i6 + 1] = fArr2[i7 + 1];
                                }
                                FloatFFT_3D.this.fftSlices.complexForward(fArr);
                                for (int i8 = 0; i8 < FloatFFT_3D.this.slices; i8++) {
                                    int i9 = i8 * 2;
                                    int i10 = (i87 * i8) + i2 + i4;
                                    float[] fArr3 = fArr13;
                                    fArr3[i10] = fArr[i9];
                                    fArr3[i10 + 1] = fArr[i9 + 1];
                                }
                            }
                        }
                    }
                });
                i83++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e9) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e9);
            } catch (ExecutionException e10) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e10);
            }
            int i88 = this.slices / i75;
            int i89 = 0;
            while (i89 < i75) {
                final int i90 = i89 * i88;
                final int i91 = i89 == i75 + -1 ? this.slices : i90 + i88;
                final int i92 = i7;
                final int i93 = i60;
                final int i94 = i8;
                final int i95 = i3;
                int i96 = i75;
                final float[] fArr14 = fArr;
                futureArr3[i89] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i90; i < i91; i++) {
                            int access$600 = (FloatFFT_3D.this.slices - i) % FloatFFT_3D.this.slices;
                            int i2 = i92;
                            int i3 = access$600 * i2;
                            int i4 = i2 * i;
                            for (int i5 = 1; i5 < i93; i5++) {
                                int i6 = i94;
                                int access$100 = (FloatFFT_3D.this.rows - i5) * i6;
                                int i7 = i6 * i5;
                                int i8 = access$100 + i3;
                                for (int i9 = 0; i9 < FloatFFT_3D.this.columns; i9++) {
                                    int i10 = i9 * 2;
                                    int i11 = i95;
                                    int i12 = i11 - i10;
                                    int i13 = i4 + i7 + i10;
                                    float[] fArr = fArr14;
                                    fArr[(i12 % i11) + i8] = fArr[i13];
                                    fArr[((i12 + 1) % i11) + i8] = -fArr[i13 + 1];
                                }
                            }
                        }
                    }
                });
                i89++;
                i75 = i96;
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

    private void mixedRadixRealForwardFull(FloatLargeArray floatLargeArray) {
        long j;
        FloatLargeArray floatLargeArray2 = floatLargeArray;
        Class<FloatFFT_3D> cls = FloatFFT_3D.class;
        long j2 = 2;
        long j3 = this.columnsl * 2;
        FloatLargeArray floatLargeArray3 = new FloatLargeArray(j3);
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
                    final FloatLargeArray floatLargeArray4 = floatLargeArray;
                    long j18 = j3;
                    final long j19 = j8;
                    futureArr2[i] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            FloatLargeArray floatLargeArray = new FloatLargeArray(j13);
                            for (long j = j15; j >= j16; j--) {
                                long access$800 = j * FloatFFT_3D.this.sliceStridel;
                                long j2 = j * j17;
                                for (long access$900 = FloatFFT_3D.this.rowsl - 1; access$900 >= 0; access$900--) {
                                    LargeArrayUtils.arraycopy(floatLargeArray4, (FloatFFT_3D.this.rowStridel * access$900) + access$800, floatLargeArray, 0, FloatFFT_3D.this.columnsl);
                                    FloatFFT_3D.this.fftColumns.realForwardFull(floatLargeArray);
                                    LargeArrayUtils.arraycopy(floatLargeArray, 0, floatLargeArray4, j2 + (j19 * access$900), j13);
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
                    FloatLargeArray floatLargeArray5 = floatLargeArray;
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
                FloatLargeArray floatLargeArray6 = new FloatLargeArray(this.rowsl * j22 * j21);
                int i4 = 0;
                while (i4 < i3) {
                    final long j23 = ((long) i4) * j11;
                    final long j24 = i4 == i3 + -1 ? j22 : j23 + j11;
                    final FloatLargeArray floatLargeArray7 = floatLargeArray;
                    final FloatLargeArray floatLargeArray8 = floatLargeArray6;
                    final long j25 = j21;
                    futureArr3[i4] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            for (long j = j23; j < j24; j++) {
                                long access$800 = FloatFFT_3D.this.sliceStridel * j;
                                for (long j2 = 0; j2 < FloatFFT_3D.this.rowsl; j2++) {
                                    FloatLargeArray floatLargeArray = floatLargeArray8;
                                    long j3 = j25;
                                    LargeArrayUtils.arraycopy(floatLargeArray7, (FloatFFT_3D.this.rowStridel * j2) + access$800, floatLargeArray, (FloatFFT_3D.this.rowsl * j * j3) + (j3 * j2), FloatFFT_3D.this.columnsl);
                                    FloatFFT_1D access$300 = FloatFFT_3D.this.fftColumns;
                                    FloatLargeArray floatLargeArray2 = floatLargeArray8;
                                    long j4 = j25;
                                    access$300.realForwardFull(floatLargeArray2, (FloatFFT_3D.this.rowsl * j * j4) + (j4 * j2));
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
                    final FloatLargeArray floatLargeArray9 = floatLargeArray6;
                    final long j29 = j21;
                    FloatLargeArray floatLargeArray10 = floatLargeArray6;
                    final FloatLargeArray floatLargeArray11 = floatLargeArray;
                    String str2 = str;
                    final long j30 = j8;
                    futureArr3[i5] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            for (long j = j26; j < j27; j++) {
                                long j2 = j28 * j;
                                for (long j3 = 0; j3 < FloatFFT_3D.this.rowsl; j3++) {
                                    FloatLargeArray floatLargeArray = floatLargeArray9;
                                    long j4 = j29;
                                    LargeArrayUtils.arraycopy(floatLargeArray, (FloatFFT_3D.this.rowsl * j * j4) + (j3 * j4), floatLargeArray11, j2 + (j30 * j3), j4);
                                }
                            }
                        }
                    });
                    i5++;
                    str = str2;
                    floatLargeArray6 = floatLargeArray10;
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
                FloatFFT_3D floatFFT_3D = this;
                long j31 = floatFFT_3D.slicesl / j20;
                int i7 = i6;
                int i8 = 0;
                while (i8 < i7) {
                    final long j32 = ((long) i8) * j31;
                    final long j33 = i8 == i7 + -1 ? floatFFT_3D.slicesl : j32 + j31;
                    final long j34 = j7;
                    final long j35 = j8;
                    final FloatLargeArray floatLargeArray12 = floatLargeArray;
                    futureArr3[i8] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            long j = 2;
                            FloatLargeArray floatLargeArray = new FloatLargeArray(FloatFFT_3D.this.rowsl * 2, false);
                            long j2 = j32;
                            while (j2 < j33) {
                                long j3 = j34 * j2;
                                long j4 = 0;
                                while (j4 < FloatFFT_3D.this.columnsl) {
                                    long j5 = j4 * j;
                                    for (long j6 = 0; j6 < FloatFFT_3D.this.rowsl; j6++) {
                                        long j7 = (j35 * j6) + j3 + j5;
                                        long j8 = j6 * j;
                                        floatLargeArray.setFloat(j8, floatLargeArray12.getFloat(j7));
                                        floatLargeArray.setFloat(j8 + 1, floatLargeArray12.getFloat(j7 + 1));
                                    }
                                    FloatFFT_3D.this.fftRows.complexForward(floatLargeArray);
                                    long j9 = 0;
                                    while (j9 < FloatFFT_3D.this.rowsl) {
                                        long j10 = (j35 * j9) + j3 + j5;
                                        long j11 = j3;
                                        long j12 = j9 * j;
                                        floatLargeArray12.setFloat(j10, floatLargeArray.getFloat(j12));
                                        floatLargeArray12.setFloat(j10 + 1, floatLargeArray.getFloat(j12 + 1));
                                        j9++;
                                        j3 = j11;
                                        j = 2;
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
                    final FloatLargeArray floatLargeArray13 = floatLargeArray;
                    futureArr3[i9] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            long j = 2;
                            FloatLargeArray floatLargeArray = new FloatLargeArray(FloatFFT_3D.this.slicesl * 2, false);
                            long j2 = j37;
                            while (j2 < j38) {
                                long j3 = j39 * j2;
                                long j4 = 0;
                                while (true) {
                                    long j5 = 1;
                                    if (j4 >= FloatFFT_3D.this.columnsl) {
                                        break;
                                    }
                                    long j6 = j4 * j;
                                    long j7 = 0;
                                    while (j7 < FloatFFT_3D.this.slicesl) {
                                        long j8 = j7 * j;
                                        long j9 = (j40 * j7) + j3 + j6;
                                        floatLargeArray.setFloat(j8, floatLargeArray13.getFloat(j9));
                                        floatLargeArray.setFloat(j8 + 1, floatLargeArray13.getFloat(j9 + 1));
                                        j7++;
                                        j = 2;
                                    }
                                    FloatFFT_3D.this.fftSlices.complexForward(floatLargeArray);
                                    long j10 = 0;
                                    while (j10 < FloatFFT_3D.this.slicesl) {
                                        long j11 = j10 * 2;
                                        long j12 = (j40 * j10) + j3 + j6;
                                        floatLargeArray13.setFloat(j12, floatLargeArray.getFloat(j11));
                                        floatLargeArray13.setFloat(j12 + 1, floatLargeArray.getFloat(j11 + 1));
                                        j10++;
                                        j5 = 1;
                                        j3 = j3;
                                    }
                                    long j13 = j3;
                                    j4 += j5;
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
                long j41 = floatFFT_3D.slicesl / j20;
                int i10 = 0;
                while (i10 < i7) {
                    final long j42 = ((long) i10) * j41;
                    final long j43 = i10 == i7 + -1 ? floatFFT_3D.slicesl : j42 + j41;
                    final long j44 = j7;
                    final long j45 = j6;
                    final long j46 = j8;
                    final long j47 = j21;
                    final FloatLargeArray floatLargeArray14 = floatLargeArray;
                    futureArr3[i10] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            for (long j = j42; j < j43; j++) {
                                long access$1200 = (FloatFFT_3D.this.slicesl - j) % FloatFFT_3D.this.slicesl;
                                long j2 = j44;
                                long j3 = access$1200 * j2;
                                long j4 = j2 * j;
                                for (long j5 = 1; j5 < j45; j5++) {
                                    long j6 = j46;
                                    long access$900 = (FloatFFT_3D.this.rowsl - j5) * j6;
                                    long j7 = j6 * j5;
                                    long j8 = access$900 + j3;
                                    long j9 = 0;
                                    while (j9 < FloatFFT_3D.this.columnsl) {
                                        long j10 = 2 * j9;
                                        long j11 = j3;
                                        long j12 = j47;
                                        long j13 = j12 - j10;
                                        long j14 = j4 + j7 + j10;
                                        long j15 = j4;
                                        FloatLargeArray floatLargeArray = floatLargeArray14;
                                        floatLargeArray.setFloat((j13 % j12) + j8, floatLargeArray.getFloat(j14));
                                        FloatLargeArray floatLargeArray2 = floatLargeArray14;
                                        floatLargeArray2.setFloat(j8 + ((j13 + 1) % j47), -floatLargeArray2.getFloat(j14 + 1));
                                        j9++;
                                        j3 = j11;
                                        j4 = j15;
                                    }
                                    long j16 = j3;
                                    long j17 = j4;
                                }
                            }
                        }
                    });
                    i10++;
                    floatFFT_3D = this;
                    i7 = i7;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException e11) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e11);
                } catch (ExecutionException e12) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e12);
                }
                return;
            }
        }
        long j48 = j3;
        for (long j49 = this.slicesl - 1; j49 >= 0; j49--) {
            long j50 = j49 * this.sliceStridel;
            long j51 = j49 * j7;
            for (long j52 = this.rowsl - 1; j52 >= 0; j52--) {
                LargeArrayUtils.arraycopy(floatLargeArray, j50 + (this.rowStridel * j52), floatLargeArray3, 0, this.columnsl);
                this.fftColumns.realForwardFull(floatLargeArray3);
                LargeArrayUtils.arraycopy(floatLargeArray3, 0, floatLargeArray, j51 + (j52 * j8), j48);
            }
        }
        FloatLargeArray floatLargeArray15 = new FloatLargeArray(this.rowsl * 2, false);
        long j53 = 0;
        while (j53 < this.slicesl) {
            long j54 = j53 * j7;
            long j55 = 0;
            while (j55 < this.columnsl) {
                long j56 = j55 * j2;
                long j57 = j54;
                long j58 = 0;
                while (j58 < this.rowsl) {
                    long j59 = j58 * j2;
                    long j60 = j57 + (j58 * j8) + j56;
                    FloatLargeArray floatLargeArray16 = floatLargeArray;
                    floatLargeArray15.setFloat(j59, floatLargeArray16.getFloat(j60));
                    floatLargeArray15.setFloat(j59 + 1, floatLargeArray16.getFloat(j60 + 1));
                    j58++;
                    j2 = 2;
                }
                FloatLargeArray floatLargeArray17 = floatLargeArray;
                this.fftRows.complexForward(floatLargeArray15);
                for (long j61 = 0; j61 < this.rowsl; j61++) {
                    long j62 = j61 * 2;
                    long j63 = j57 + (j61 * j8) + j56;
                    floatLargeArray17.setFloat(j63, floatLargeArray15.getFloat(j62));
                    floatLargeArray17.setFloat(j63 + 1, floatLargeArray15.getFloat(j62 + 1));
                }
                j55++;
                j54 = j57;
                j2 = 2;
            }
            FloatLargeArray floatLargeArray18 = floatLargeArray;
            j53++;
            j2 = 2;
        }
        FloatLargeArray floatLargeArray19 = floatLargeArray;
        long j64 = 2;
        FloatLargeArray floatLargeArray20 = new FloatLargeArray(this.slicesl * 2, false);
        long j65 = 0;
        while (j65 < j5) {
            long j66 = j65 * j8;
            long j67 = 0;
            while (j67 < this.columnsl) {
                long j68 = j67 * j64;
                long j69 = 0;
                while (j69 < this.slicesl) {
                    long j70 = j65;
                    long j71 = j69 * 2;
                    long j72 = (j69 * j7) + j66 + j68;
                    floatLargeArray20.setFloat(j71, floatLargeArray19.getFloat(j72));
                    floatLargeArray20.setFloat(j71 + 1, floatLargeArray19.getFloat(j72 + 1));
                    j69++;
                    j65 = j70;
                }
                long j73 = j65;
                this.fftSlices.complexForward(floatLargeArray20);
                for (long j74 = 0; j74 < this.slicesl; j74++) {
                    long j75 = j74 * 2;
                    long j76 = (j74 * j7) + j66 + j68;
                    floatLargeArray19.setFloat(j76, floatLargeArray20.getFloat(j75));
                    floatLargeArray19.setFloat(j76 + 1, floatLargeArray20.getFloat(j75 + 1));
                }
                j67++;
                j65 = j73;
                j64 = 2;
            }
            j65++;
            j64 = 2;
        }
        long j77 = 0;
        while (true) {
            long j78 = this.slicesl;
            if (j77 < j78) {
                long j79 = ((j78 - j77) % j78) * j7;
                long j80 = j77 * j7;
                long j81 = 1;
                while (j81 < j6) {
                    long j82 = j81 * j8;
                    long j83 = ((this.rowsl - j81) * j8) + j79;
                    long j84 = j79;
                    long j85 = 0;
                    while (j85 < this.columnsl) {
                        long j86 = j85 * 2;
                        long j87 = j48 - j86;
                        long j88 = j80 + j82 + j86;
                        floatLargeArray19.setFloat(j83 + (j87 % j48), floatLargeArray19.getFloat(j88));
                        floatLargeArray19.setFloat(j83 + ((j87 + 1) % j48), -floatLargeArray19.getFloat(j88 + 1));
                        j85++;
                        j80 = j80;
                    }
                    long j89 = j80;
                    j81++;
                    j79 = j84;
                }
                j77++;
            } else {
                return;
            }
        }
    }

    private void mixedRadixRealInverseFull(float[] fArr, boolean z) {
        int i;
        int i2;
        float[] fArr2 = fArr;
        boolean z2 = z;
        Class<FloatFFT_3D> cls = FloatFFT_3D.class;
        int i3 = this.columns * 2;
        float[] fArr3 = new float[i3];
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
                    System.arraycopy(fArr2, (this.rowStride * i16) + i14, fArr3, 0, this.columns);
                    this.fftColumns.realInverseFull(fArr3, z);
                    System.arraycopy(fArr3, 0, fArr2, (i16 * i9) + i15, i3);
                }
                boolean z3 = z;
            }
            boolean z4 = z;
            float[] fArr4 = new float[(this.rows * 2)];
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
                        fArr4[i22] = fArr2[i23];
                        fArr4[i22 + 1] = fArr2[i23 + 1];
                    }
                    this.fftRows.complexInverse(fArr4, z4);
                    for (int i24 = 0; i24 < this.rows; i24++) {
                        int i25 = i24 * 2;
                        int i26 = (i24 * i9) + i18 + i20;
                        fArr2[i26] = fArr4[i25];
                        fArr2[i26 + 1] = fArr4[i25 + 1];
                    }
                }
                i17++;
            }
            float[] fArr5 = new float[(i2 * 2)];
            for (int i27 = 0; i27 < i12; i27++) {
                int i28 = i27 * i9;
                for (int i29 = 0; i29 < this.columns; i29++) {
                    int i30 = i29 * 2;
                    for (int i31 = 0; i31 < this.slices; i31++) {
                        int i32 = i31 * 2;
                        int i33 = (i31 * i8) + i28 + i30;
                        fArr5[i32] = fArr2[i33];
                        fArr5[i32 + 1] = fArr2[i33 + 1];
                    }
                    this.fftSlices.complexInverse(fArr5, z4);
                    for (int i34 = 0; i34 < this.slices; i34++) {
                        int i35 = i34 * 2;
                        int i36 = (i34 * i8) + i28 + i30;
                        fArr2[i36] = fArr5[i35];
                        fArr2[i36 + 1] = fArr5[i35 + 1];
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
                            fArr2[(i47 % i3) + i44] = fArr2[i48];
                            fArr2[((i47 + 1) % i3) + i44] = -fArr2[i48 + 1];
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
                final float[] fArr6 = fArr;
                int i59 = i6;
                final boolean z5 = z;
                int i60 = i5;
                final int i61 = i9;
                futureArr2[i50] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        float[] fArr = new float[i52];
                        for (int i = i53; i >= i55; i--) {
                            int access$000 = FloatFFT_3D.this.sliceStride * i;
                            int i2 = i57 * i;
                            for (int access$100 = FloatFFT_3D.this.rows - 1; access$100 >= 0; access$100--) {
                                System.arraycopy(fArr6, (FloatFFT_3D.this.rowStride * access$100) + access$000, fArr, 0, FloatFFT_3D.this.columns);
                                FloatFFT_3D.this.fftColumns.realInverseFull(fArr, z5);
                                System.arraycopy(fArr, 0, fArr6, (i61 * access$100) + i2, i52);
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
            float[][][] fArr7 = (float[][][]) Array.newInstance(Float.TYPE, iArr);
            int i69 = 0;
            int i70 = i62;
            while (i69 < i70) {
                final int i71 = i69 * i49;
                final int i72 = i69 == i70 + -1 ? i67 : i71 + i49;
                final float[] fArr8 = fArr;
                final float[][][] fArr9 = fArr7;
                final boolean z6 = z;
                futureArr3[i69] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i71; i < i72; i++) {
                            int access$000 = FloatFFT_3D.this.sliceStride * i;
                            for (int i2 = 0; i2 < FloatFFT_3D.this.rows; i2++) {
                                System.arraycopy(fArr8, (FloatFFT_3D.this.rowStride * i2) + access$000, fArr9[i][i2], 0, FloatFFT_3D.this.columns);
                                FloatFFT_3D.this.fftColumns.realInverseFull(fArr9[i][i2], z6);
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
                final float[][][] fArr10 = fArr7;
                final float[] fArr11 = fArr;
                int i77 = i70;
                final int i78 = i9;
                String str2 = str;
                final int i79 = i3;
                futureArr3[i73] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i74; i < i75; i++) {
                            int i2 = i76 * i;
                            for (int i3 = 0; i3 < FloatFFT_3D.this.rows; i3++) {
                                System.arraycopy(fArr10[i][i3], 0, fArr11, (i78 * i3) + i2, i79);
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
                final float[] fArr12 = fArr;
                final boolean z8 = z;
                futureArr3[i82] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        float[] fArr = new float[(FloatFFT_3D.this.rows * 2)];
                        for (int i = i83; i < i84; i++) {
                            int i2 = i85 * i;
                            for (int i3 = 0; i3 < FloatFFT_3D.this.columns; i3++) {
                                int i4 = i3 * 2;
                                for (int i5 = 0; i5 < FloatFFT_3D.this.rows; i5++) {
                                    int i6 = (i86 * i5) + i2 + i4;
                                    int i7 = i5 * 2;
                                    float[] fArr2 = fArr12;
                                    fArr[i7] = fArr2[i6];
                                    fArr[i7 + 1] = fArr2[i6 + 1];
                                }
                                FloatFFT_3D.this.fftRows.complexInverse(fArr, z8);
                                for (int i8 = 0; i8 < FloatFFT_3D.this.rows; i8++) {
                                    int i9 = (i86 * i8) + i2 + i4;
                                    int i10 = i8 * 2;
                                    float[] fArr3 = fArr12;
                                    fArr3[i9] = fArr[i10];
                                    fArr3[i9 + 1] = fArr[i10 + 1];
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
                final float[] fArr13 = fArr;
                int i94 = i89;
                final boolean z9 = z;
                futureArr3[i94] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        float[] fArr = new float[(FloatFFT_3D.this.slices * 2)];
                        for (int i = i90; i < i91; i++) {
                            int i2 = i92 * i;
                            for (int i3 = 0; i3 < FloatFFT_3D.this.columns; i3++) {
                                int i4 = i3 * 2;
                                for (int i5 = 0; i5 < FloatFFT_3D.this.slices; i5++) {
                                    int i6 = i5 * 2;
                                    int i7 = (i93 * i5) + i2 + i4;
                                    float[] fArr2 = fArr13;
                                    fArr[i6] = fArr2[i7];
                                    fArr[i6 + 1] = fArr2[i7 + 1];
                                }
                                FloatFFT_3D.this.fftSlices.complexInverse(fArr, z9);
                                for (int i8 = 0; i8 < FloatFFT_3D.this.slices; i8++) {
                                    int i9 = i8 * 2;
                                    int i10 = (i93 * i8) + i2 + i4;
                                    float[] fArr3 = fArr13;
                                    fArr3[i10] = fArr[i9];
                                    fArr3[i10 + 1] = fArr[i9 + 1];
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
                final float[] fArr14 = fArr;
                futureArr3[i103] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i97; i < i98; i++) {
                            int access$600 = (FloatFFT_3D.this.slices - i) % FloatFFT_3D.this.slices;
                            int i2 = i99;
                            int i3 = access$600 * i2;
                            int i4 = i2 * i;
                            for (int i5 = 1; i5 < i100; i5++) {
                                int i6 = i101;
                                int access$100 = (FloatFFT_3D.this.rows - i5) * i6;
                                int i7 = i6 * i5;
                                int i8 = access$100 + i3;
                                for (int i9 = 0; i9 < FloatFFT_3D.this.columns; i9++) {
                                    int i10 = i9 * 2;
                                    int i11 = i102;
                                    int i12 = i11 - i10;
                                    int i13 = i4 + i7 + i10;
                                    float[] fArr = fArr14;
                                    fArr[(i12 % i11) + i8] = fArr[i13];
                                    fArr[((i12 + 1) % i11) + i8] = -fArr[i13 + 1];
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

    private void mixedRadixRealInverseFull(FloatLargeArray floatLargeArray, boolean z) {
        long j;
        FloatLargeArray floatLargeArray2 = floatLargeArray;
        boolean z2 = z;
        Class<FloatFFT_3D> cls = FloatFFT_3D.class;
        long j2 = 2;
        long j3 = this.columnsl * 2;
        FloatLargeArray floatLargeArray3 = new FloatLargeArray(j3);
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
                    final FloatLargeArray floatLargeArray4 = floatLargeArray;
                    final boolean z5 = z;
                    final long j19 = j8;
                    futureArr2[i] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            FloatLargeArray floatLargeArray = new FloatLargeArray(j13);
                            for (long j = j15; j >= j16; j--) {
                                long access$800 = j * FloatFFT_3D.this.sliceStridel;
                                long j2 = j * j17;
                                for (long access$900 = FloatFFT_3D.this.rowsl - 1; access$900 >= 0; access$900--) {
                                    LargeArrayUtils.arraycopy(floatLargeArray4, (FloatFFT_3D.this.rowStridel * access$900) + access$800, floatLargeArray, 0, FloatFFT_3D.this.columnsl);
                                    FloatFFT_3D.this.fftColumns.realInverseFull(floatLargeArray, z5);
                                    LargeArrayUtils.arraycopy(floatLargeArray, 0, floatLargeArray4, j2 + (j19 * access$900), j13);
                                }
                            }
                        }
                    });
                    i++;
                    FloatLargeArray floatLargeArray5 = floatLargeArray;
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
                FloatLargeArray floatLargeArray6 = new FloatLargeArray(this.rowsl * j22 * j21);
                int i4 = i3;
                int i5 = 0;
                while (i5 < i4) {
                    final long j23 = ((long) i5) * j11;
                    final long j24 = i5 == i4 + -1 ? j22 : j23 + j11;
                    final FloatLargeArray floatLargeArray7 = floatLargeArray;
                    final FloatLargeArray floatLargeArray8 = floatLargeArray6;
                    final long j25 = j21;
                    final boolean z7 = z;
                    futureArr3[i5] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            for (long j = j23; j < j24; j++) {
                                long access$800 = FloatFFT_3D.this.sliceStridel * j;
                                for (long j2 = 0; j2 < FloatFFT_3D.this.rowsl; j2++) {
                                    FloatLargeArray floatLargeArray = floatLargeArray8;
                                    long j3 = j25;
                                    LargeArrayUtils.arraycopy(floatLargeArray7, (FloatFFT_3D.this.rowStridel * j2) + access$800, floatLargeArray, (FloatFFT_3D.this.rowsl * j * j3) + (j3 * j2), FloatFFT_3D.this.columnsl);
                                    FloatFFT_1D access$300 = FloatFFT_3D.this.fftColumns;
                                    FloatLargeArray floatLargeArray2 = floatLargeArray8;
                                    long j4 = j25;
                                    access$300.realInverseFull(floatLargeArray2, (FloatFFT_3D.this.rowsl * j * j4) + (j4 * j2), z7);
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
                    final FloatLargeArray floatLargeArray9 = floatLargeArray6;
                    final long j29 = j21;
                    int i7 = i4;
                    final FloatLargeArray floatLargeArray10 = floatLargeArray;
                    String str2 = str;
                    final long j30 = j8;
                    futureArr3[i6] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            for (long j = j26; j < j27; j++) {
                                long j2 = j28 * j;
                                for (long j3 = 0; j3 < FloatFFT_3D.this.rowsl; j3++) {
                                    FloatLargeArray floatLargeArray = floatLargeArray9;
                                    long j4 = j29;
                                    LargeArrayUtils.arraycopy(floatLargeArray, (FloatFFT_3D.this.rowsl * j * j4) + (j3 * j4), floatLargeArray10, j2 + (j30 * j3), j4);
                                }
                            }
                        }
                    });
                    i6++;
                    str = str2;
                    floatLargeArray6 = floatLargeArray6;
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
                FloatFFT_3D floatFFT_3D = this;
                long j31 = floatFFT_3D.slicesl / j20;
                int i9 = i8;
                int i10 = 0;
                while (i10 < i9) {
                    final long j32 = ((long) i10) * j31;
                    final long j33 = i10 == i9 + -1 ? floatFFT_3D.slicesl : j32 + j31;
                    final long j34 = j7;
                    final long j35 = j8;
                    final FloatLargeArray floatLargeArray11 = floatLargeArray;
                    final boolean z8 = z;
                    futureArr3[i10] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            long j = 2;
                            FloatLargeArray floatLargeArray = new FloatLargeArray(FloatFFT_3D.this.rowsl * 2, false);
                            long j2 = j32;
                            while (j2 < j33) {
                                long j3 = j34 * j2;
                                long j4 = 0;
                                while (j4 < FloatFFT_3D.this.columnsl) {
                                    long j5 = j4 * j;
                                    for (long j6 = 0; j6 < FloatFFT_3D.this.rowsl; j6++) {
                                        long j7 = (j35 * j6) + j3 + j5;
                                        long j8 = j6 * j;
                                        floatLargeArray.setFloat(j8, floatLargeArray11.getFloat(j7));
                                        floatLargeArray.setFloat(j8 + 1, floatLargeArray11.getFloat(j7 + 1));
                                    }
                                    FloatFFT_3D.this.fftRows.complexInverse(floatLargeArray, z8);
                                    long j9 = 0;
                                    while (j9 < FloatFFT_3D.this.rowsl) {
                                        long j10 = (j35 * j9) + j3 + j5;
                                        long j11 = j3;
                                        long j12 = j9 * j;
                                        floatLargeArray11.setFloat(j10, floatLargeArray.getFloat(j12));
                                        floatLargeArray11.setFloat(j10 + 1, floatLargeArray.getFloat(j12 + 1));
                                        j9++;
                                        j3 = j11;
                                        j = 2;
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
                    final FloatLargeArray floatLargeArray12 = floatLargeArray;
                    final boolean z9 = z;
                    futureArr3[i11] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            long j = 2;
                            FloatLargeArray floatLargeArray = new FloatLargeArray(FloatFFT_3D.this.slicesl * 2, false);
                            long j2 = j37;
                            while (j2 < j38) {
                                long j3 = j39 * j2;
                                long j4 = 0;
                                while (true) {
                                    long j5 = 1;
                                    if (j4 >= FloatFFT_3D.this.columnsl) {
                                        break;
                                    }
                                    long j6 = j4 * j;
                                    long j7 = 0;
                                    while (j7 < FloatFFT_3D.this.slicesl) {
                                        long j8 = j7 * j;
                                        long j9 = (j40 * j7) + j3 + j6;
                                        floatLargeArray.setFloat(j8, floatLargeArray12.getFloat(j9));
                                        floatLargeArray.setFloat(j8 + 1, floatLargeArray12.getFloat(j9 + 1));
                                        j7++;
                                        j = 2;
                                    }
                                    FloatFFT_3D.this.fftSlices.complexInverse(floatLargeArray, z9);
                                    long j10 = 0;
                                    while (j10 < FloatFFT_3D.this.slicesl) {
                                        long j11 = j10 * 2;
                                        long j12 = (j40 * j10) + j3 + j6;
                                        floatLargeArray12.setFloat(j12, floatLargeArray.getFloat(j11));
                                        floatLargeArray12.setFloat(j12 + 1, floatLargeArray.getFloat(j11 + 1));
                                        j10++;
                                        j5 = 1;
                                        j3 = j3;
                                    }
                                    long j13 = j3;
                                    j4 += j5;
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
                long j41 = floatFFT_3D.slicesl / j20;
                int i12 = 0;
                while (i12 < i9) {
                    final long j42 = ((long) i12) * j41;
                    final long j43 = i12 == i9 + -1 ? floatFFT_3D.slicesl : j42 + j41;
                    final long j44 = j7;
                    final long j45 = j6;
                    final long j46 = j8;
                    final long j47 = j21;
                    final FloatLargeArray floatLargeArray13 = floatLargeArray;
                    futureArr3[i12] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            for (long j = j42; j < j43; j++) {
                                long access$1200 = (FloatFFT_3D.this.slicesl - j) % FloatFFT_3D.this.slicesl;
                                long j2 = j44;
                                long j3 = access$1200 * j2;
                                long j4 = j2 * j;
                                for (long j5 = 1; j5 < j45; j5++) {
                                    long j6 = j46;
                                    long access$900 = (FloatFFT_3D.this.rowsl - j5) * j6;
                                    long j7 = j6 * j5;
                                    long j8 = access$900 + j3;
                                    long j9 = 0;
                                    while (j9 < FloatFFT_3D.this.columnsl) {
                                        long j10 = 2 * j9;
                                        long j11 = j3;
                                        long j12 = j47;
                                        long j13 = j12 - j10;
                                        long j14 = j4 + j7 + j10;
                                        long j15 = j4;
                                        FloatLargeArray floatLargeArray = floatLargeArray13;
                                        floatLargeArray.setFloat((j13 % j12) + j8, floatLargeArray.getFloat(j14));
                                        FloatLargeArray floatLargeArray2 = floatLargeArray13;
                                        floatLargeArray2.setFloat(j8 + ((j13 + 1) % j47), -floatLargeArray2.getFloat(j14 + 1));
                                        j9++;
                                        j3 = j11;
                                        j4 = j15;
                                    }
                                    long j16 = j3;
                                    long j17 = j4;
                                }
                            }
                        }
                    });
                    i12++;
                    floatFFT_3D = this;
                    i9 = i9;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException e11) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e11);
                } catch (ExecutionException e12) {
                    Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e12);
                }
                return;
            }
        }
        long j48 = j3;
        for (long j49 = this.slicesl - 1; j49 >= 0; j49--) {
            long j50 = j49 * this.sliceStridel;
            long j51 = j49 * j7;
            for (long j52 = this.rowsl - 1; j52 >= 0; j52--) {
                LargeArrayUtils.arraycopy(floatLargeArray, j50 + (this.rowStridel * j52), floatLargeArray3, 0, this.columnsl);
                this.fftColumns.realInverseFull(floatLargeArray3, z);
                LargeArrayUtils.arraycopy(floatLargeArray3, 0, floatLargeArray, j51 + (j52 * j8), j48);
            }
        }
        FloatLargeArray floatLargeArray14 = new FloatLargeArray(this.rowsl * 2, false);
        long j53 = 0;
        while (j53 < this.slicesl) {
            long j54 = j53 * j7;
            long j55 = 0;
            while (j55 < this.columnsl) {
                long j56 = j55 * j2;
                long j57 = j54;
                long j58 = 0;
                while (j58 < this.rowsl) {
                    long j59 = j58 * j2;
                    long j60 = j57 + (j58 * j8) + j56;
                    FloatLargeArray floatLargeArray15 = floatLargeArray;
                    floatLargeArray14.setFloat(j59, floatLargeArray15.getFloat(j60));
                    floatLargeArray14.setFloat(j59 + 1, floatLargeArray15.getFloat(j60 + 1));
                    j58++;
                    j2 = 2;
                }
                FloatLargeArray floatLargeArray16 = floatLargeArray;
                this.fftRows.complexInverse(floatLargeArray14, z);
                long j61 = 0;
                while (j61 < this.rowsl) {
                    long j62 = j61 * 2;
                    long j63 = j57 + (j61 * j8) + j56;
                    floatLargeArray16.setFloat(j63, floatLargeArray14.getFloat(j62));
                    floatLargeArray16.setFloat(j63 + 1, floatLargeArray14.getFloat(j62 + 1));
                    j61++;
                    boolean z10 = z;
                }
                j55++;
                j54 = j57;
                j2 = 2;
            }
            FloatLargeArray floatLargeArray17 = floatLargeArray;
            j53++;
            j2 = 2;
        }
        FloatLargeArray floatLargeArray18 = floatLargeArray;
        long j64 = 2;
        FloatLargeArray floatLargeArray19 = new FloatLargeArray(this.slicesl * 2, false);
        long j65 = 0;
        while (j65 < j5) {
            long j66 = j65 * j8;
            long j67 = 0;
            while (j67 < this.columnsl) {
                long j68 = j67 * j64;
                long j69 = 0;
                while (j69 < this.slicesl) {
                    long j70 = j65;
                    long j71 = j69 * 2;
                    long j72 = (j69 * j7) + j66 + j68;
                    floatLargeArray19.setFloat(j71, floatLargeArray18.getFloat(j72));
                    floatLargeArray19.setFloat(j71 + 1, floatLargeArray18.getFloat(j72 + 1));
                    j69++;
                    j65 = j70;
                }
                long j73 = j65;
                this.fftSlices.complexInverse(floatLargeArray19, z);
                long j74 = 0;
                while (j74 < this.slicesl) {
                    long j75 = j74 * 2;
                    long j76 = (j74 * j7) + j66 + j68;
                    floatLargeArray18.setFloat(j76, floatLargeArray19.getFloat(j75));
                    floatLargeArray18.setFloat(j76 + 1, floatLargeArray19.getFloat(j75 + 1));
                    j74++;
                    boolean z11 = z;
                }
                j67++;
                j65 = j73;
                j64 = 2;
            }
            j65++;
            j64 = 2;
        }
        long j77 = 0;
        while (true) {
            long j78 = this.slicesl;
            if (j77 < j78) {
                long j79 = ((j78 - j77) % j78) * j7;
                long j80 = j77 * j7;
                long j81 = 1;
                while (j81 < j6) {
                    long j82 = j81 * j8;
                    long j83 = ((this.rowsl - j81) * j8) + j79;
                    long j84 = j79;
                    long j85 = 0;
                    while (j85 < this.columnsl) {
                        long j86 = j85 * 2;
                        long j87 = j48 - j86;
                        long j88 = j80 + j82 + j86;
                        floatLargeArray18.setFloat(j83 + (j87 % j48), floatLargeArray18.getFloat(j88));
                        floatLargeArray18.setFloat(j83 + ((j87 + 1) % j48), -floatLargeArray18.getFloat(j88 + 1));
                        j85++;
                        j80 = j80;
                    }
                    long j89 = j80;
                    j81++;
                    j79 = j84;
                }
                j77++;
            } else {
                return;
            }
        }
    }

    private void xdft3da_sub1(int i, int i2, float[] fArr, boolean z) {
        float[] fArr2 = fArr;
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
        float[] fArr3 = new float[i5];
        if (i2 == -1) {
            for (int i7 = 0; i7 < this.slices; i7++) {
                int i8 = this.sliceStride * i7;
                if (i == 0) {
                    for (int i9 = 0; i9 < this.rows; i9++) {
                        this.fftColumns.complexForward(fArr2, (this.rowStride * i9) + i8);
                    }
                } else {
                    for (int i10 = 0; i10 < this.rows; i10++) {
                        this.fftColumns.realForward(fArr2, (this.rowStride * i10) + i8);
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
                            fArr3[i16] = fArr2[i15];
                            fArr3[i16 + 1] = fArr2[i15 + 1];
                            fArr3[i17] = fArr2[i15 + 2];
                            fArr3[i17 + 1] = fArr2[i15 + 3];
                            fArr3[i18] = fArr2[i15 + 4];
                            fArr3[i18 + 1] = fArr2[i15 + 5];
                            fArr3[i19] = fArr2[i15 + 6];
                            fArr3[i19 + 1] = fArr2[i15 + 7];
                            i13++;
                        }
                        this.fftRows.complexForward(fArr3, 0);
                        this.fftRows.complexForward(fArr3, this.rows * 2);
                        this.fftRows.complexForward(fArr3, this.rows * 4);
                        this.fftRows.complexForward(fArr3, this.rows * 6);
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
                            fArr2[i22] = fArr3[i23];
                            fArr2[i22 + 1] = fArr3[i23 + 1];
                            fArr2[i22 + 2] = fArr3[i24];
                            fArr2[i22 + 3] = fArr3[i24 + 1];
                            fArr2[i22 + 4] = fArr3[i25];
                            fArr2[i22 + 5] = fArr3[i25 + 1];
                            fArr2[i22 + 6] = fArr3[i26];
                            fArr2[i22 + 7] = fArr3[i26 + 1];
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
                        fArr3[i30] = fArr2[i29];
                        fArr3[i30 + 1] = fArr2[i29 + 1];
                        fArr3[i31] = fArr2[i29 + 2];
                        fArr3[i31 + 1] = fArr2[i29 + 3];
                        i27++;
                    }
                    this.fftRows.complexForward(fArr3, 0);
                    this.fftRows.complexForward(fArr3, this.rows * 2);
                    int i32 = 0;
                    while (true) {
                        int i33 = this.rows;
                        if (i32 >= i33) {
                            break;
                        }
                        int i34 = (this.rowStride * i32) + i8;
                        int i35 = i32 * 2;
                        int i36 = (i33 * 2) + i35;
                        fArr2[i34] = fArr3[i35];
                        fArr2[i34 + 1] = fArr3[i35 + 1];
                        fArr2[i34 + 2] = fArr3[i36];
                        fArr2[i34 + 3] = fArr3[i36 + 1];
                        i32++;
                    }
                } else if (i11 == 2) {
                    for (int i37 = 0; i37 < this.rows; i37++) {
                        int i38 = (this.rowStride * i37) + i8;
                        int i39 = i37 * 2;
                        fArr3[i39] = fArr2[i38];
                        fArr3[i39 + 1] = fArr2[i38 + 1];
                    }
                    this.fftRows.complexForward(fArr3, 0);
                    for (int i40 = 0; i40 < this.rows; i40++) {
                        int i41 = (this.rowStride * i40) + i8;
                        int i42 = i40 * 2;
                        fArr2[i41] = fArr3[i42];
                        fArr2[i41 + 1] = fArr3[i42 + 1];
                    }
                }
            }
            return;
        }
        for (int i43 = 0; i43 < this.slices; i43++) {
            int i44 = this.sliceStride * i43;
            if (i == 0) {
                for (int i45 = 0; i45 < this.rows; i45++) {
                    this.fftColumns.complexInverse(fArr2, (this.rowStride * i45) + i44, z2);
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
                        fArr3[i51] = fArr2[i50];
                        fArr3[i51 + 1] = fArr2[i50 + 1];
                        fArr3[i52] = fArr2[i50 + 2];
                        fArr3[i52 + 1] = fArr2[i50 + 3];
                        fArr3[i53] = fArr2[i50 + 4];
                        fArr3[i53 + 1] = fArr2[i50 + 5];
                        fArr3[i54] = fArr2[i50 + 6];
                        fArr3[i54 + 1] = fArr2[i50 + 7];
                        i48++;
                    }
                    this.fftRows.complexInverse(fArr3, 0, z2);
                    this.fftRows.complexInverse(fArr3, this.rows * 2, z2);
                    this.fftRows.complexInverse(fArr3, this.rows * 4, z2);
                    this.fftRows.complexInverse(fArr3, this.rows * 6, z2);
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
                        fArr2[i57] = fArr3[i58];
                        fArr2[i57 + 1] = fArr3[i58 + 1];
                        fArr2[i57 + 2] = fArr3[i59];
                        fArr2[i57 + 3] = fArr3[i59 + 1];
                        fArr2[i57 + 4] = fArr3[i60];
                        fArr2[i57 + 5] = fArr3[i60 + 1];
                        fArr2[i57 + 6] = fArr3[i61];
                        fArr2[i57 + 7] = fArr3[i61 + 1];
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
                    fArr3[i65] = fArr2[i64];
                    fArr3[i65 + 1] = fArr2[i64 + 1];
                    fArr3[i66] = fArr2[i64 + 2];
                    fArr3[i66 + 1] = fArr2[i64 + 3];
                    i62++;
                }
                this.fftRows.complexInverse(fArr3, 0, z2);
                this.fftRows.complexInverse(fArr3, this.rows * 2, z2);
                int i67 = 0;
                while (true) {
                    int i68 = this.rows;
                    if (i67 >= i68) {
                        break;
                    }
                    int i69 = (this.rowStride * i67) + i44;
                    int i70 = i67 * 2;
                    int i71 = (i68 * 2) + i70;
                    fArr2[i69] = fArr3[i70];
                    fArr2[i69 + 1] = fArr3[i70 + 1];
                    fArr2[i69 + 2] = fArr3[i71];
                    fArr2[i69 + 3] = fArr3[i71 + 1];
                    i67++;
                }
            } else if (i46 == 2) {
                for (int i72 = 0; i72 < this.rows; i72++) {
                    int i73 = (this.rowStride * i72) + i44;
                    int i74 = i72 * 2;
                    fArr3[i74] = fArr2[i73];
                    fArr3[i74 + 1] = fArr2[i73 + 1];
                }
                this.fftRows.complexInverse(fArr3, 0, z2);
                for (int i75 = 0; i75 < this.rows; i75++) {
                    int i76 = (this.rowStride * i75) + i44;
                    int i77 = i75 * 2;
                    fArr2[i76] = fArr3[i77];
                    fArr2[i76 + 1] = fArr3[i77 + 1];
                }
            }
            if (i != 0) {
                for (int i78 = 0; i78 < this.rows; i78++) {
                    this.fftColumns.realInverse(fArr2, (this.rowStride * i78) + i44, z2);
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:107:0x050d  */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x0521 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0297  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void xdft3da_sub1(long r36, int r38, pl.edu.icm.jlargearrays.FloatLargeArray r39, boolean r40) {
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
            pl.edu.icm.jlargearrays.FloatLargeArray r7 = new pl.edu.icm.jlargearrays.FloatLargeArray
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
            org.jtransforms.fft.FloatFFT_1D r4 = r0.fftColumns
            long r5 = r0.rowStridel
            long r5 = r5 * r23
            long r5 = r5 + r11
            r4.complexForward((pl.edu.icm.jlargearrays.FloatLargeArray) r1, (long) r5)
            long r23 = r23 + r21
            goto L_0x0047
        L_0x005a:
            r4 = 0
        L_0x005c:
            long r13 = r0.rowsl
            int r6 = (r4 > r13 ? 1 : (r4 == r13 ? 0 : -1))
            if (r6 >= 0) goto L_0x006e
            org.jtransforms.fft.FloatFFT_1D r6 = r0.fftColumns
            long r13 = r0.rowStridel
            long r13 = r13 * r4
            long r13 = r13 + r11
            r6.realForward((pl.edu.icm.jlargearrays.FloatLargeArray) r1, (long) r13)
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
            float r6 = r1.getFloat(r2)
            r7.setFloat(r4, r6)
            long r4 = r4 + r21
            r29 = r9
            long r8 = r2 + r21
            float r6 = r1.getFloat(r8)
            r7.setFloat(r4, r6)
            long r4 = r2 + r19
            float r4 = r1.getFloat(r4)
            r7.setFloat(r11, r4)
            long r11 = r11 + r21
            long r4 = r2 + r17
            float r4 = r1.getFloat(r4)
            r7.setFloat(r11, r4)
            r4 = 4
            long r9 = r2 + r4
            float r4 = r1.getFloat(r9)
            r7.setFloat(r13, r4)
            long r13 = r13 + r21
            r4 = 5
            long r8 = r2 + r4
            float r4 = r1.getFloat(r8)
            r7.setFloat(r13, r4)
            long r4 = r2 + r15
            float r4 = r1.getFloat(r4)
            r13 = r29
            r7.setFloat(r13, r4)
            long r9 = r13 + r21
            r4 = 7
            long r2 = r2 + r4
            float r2 = r1.getFloat(r2)
            r7.setFloat(r9, r2)
            long r13 = r33 + r21
            r2 = r25
            r4 = r27
            r11 = r31
            goto L_0x007e
        L_0x00fe:
            r25 = r2
            r27 = r4
            r31 = r11
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
            float r6 = r7.getFloat(r8)
            r1.setFloat(r11, r6)
            r29 = r4
            long r4 = r11 + r21
            long r8 = r8 + r21
            float r6 = r7.getFloat(r8)
            r1.setFloat(r4, r6)
            long r4 = r11 + r19
            float r6 = r7.getFloat(r13)
            r1.setFloat(r4, r6)
            long r4 = r11 + r17
            long r13 = r13 + r21
            float r6 = r7.getFloat(r13)
            r1.setFloat(r4, r6)
            r4 = 4
            long r9 = r11 + r4
            float r4 = r7.getFloat(r2)
            r1.setFloat(r9, r4)
            r4 = 5
            long r13 = r11 + r4
            long r2 = r2 + r21
            float r2 = r7.getFloat(r2)
            r1.setFloat(r13, r2)
            long r2 = r11 + r15
            r4 = r29
            float r6 = r7.getFloat(r4)
            r1.setFloat(r2, r6)
            r2 = 7
            long r11 = r11 + r2
            long r4 = r4 + r21
            float r2 = r7.getFloat(r4)
            r1.setFloat(r11, r2)
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
            float r6 = r1.getFloat(r11)
            r7.setFloat(r8, r6)
            long r8 = r8 + r21
            long r13 = r11 + r21
            float r6 = r1.getFloat(r13)
            r7.setFloat(r8, r6)
            long r8 = r11 + r19
            float r6 = r1.getFloat(r8)
            r7.setFloat(r4, r6)
            long r4 = r4 + r21
            long r11 = r11 + r17
            float r6 = r1.getFloat(r11)
            r7.setFloat(r4, r6)
            long r2 = r2 + r21
            goto L_0x01b8
        L_0x01f1:
            org.jtransforms.fft.FloatFFT_1D r2 = r0.fftRows
            r3 = 0
            r2.complexForward((pl.edu.icm.jlargearrays.FloatLargeArray) r7, (long) r3)
            org.jtransforms.fft.FloatFFT_1D r2 = r0.fftRows
            long r3 = r0.rowsl
            long r3 = r3 * r19
            r2.complexForward((pl.edu.icm.jlargearrays.FloatLargeArray) r7, (long) r3)
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
            float r6 = r7.getFloat(r8)
            r1.setFloat(r11, r6)
            long r13 = r11 + r21
            long r8 = r8 + r21
            float r6 = r7.getFloat(r8)
            r1.setFloat(r13, r6)
            long r8 = r11 + r19
            float r6 = r7.getFloat(r4)
            r1.setFloat(r8, r6)
            long r11 = r11 + r17
            long r4 = r4 + r21
            float r4 = r7.getFloat(r4)
            r1.setFloat(r11, r4)
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
            float r6 = r1.getFloat(r11)
            r7.setFloat(r4, r6)
            long r4 = r4 + r21
            long r11 = r11 + r21
            float r6 = r1.getFloat(r11)
            r7.setFloat(r4, r6)
            long r2 = r2 + r21
            goto L_0x0242
        L_0x0264:
            org.jtransforms.fft.FloatFFT_1D r2 = r0.fftRows
            r3 = 0
            r2.complexForward((pl.edu.icm.jlargearrays.FloatLargeArray) r7, (long) r3)
            r2 = 0
        L_0x026d:
            long r4 = r0.rowsl
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 >= 0) goto L_0x028f
            long r4 = r0.rowStridel
            long r4 = r4 * r2
            long r11 = r31 + r4
            long r4 = r2 * r19
            float r6 = r7.getFloat(r4)
            r1.setFloat(r11, r6)
            long r11 = r11 + r21
            long r4 = r4 + r21
            float r4 = r7.getFloat(r4)
            r1.setFloat(r11, r4)
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
            org.jtransforms.fft.FloatFFT_1D r11 = r0.fftColumns
            long r12 = r0.rowStridel
            long r12 = r12 * r8
            long r12 = r12 + r5
            r11.complexInverse((pl.edu.icm.jlargearrays.FloatLargeArray) r1, (long) r12, (boolean) r2)
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
            float r12 = r1.getFloat(r3)
            r7.setFloat(r8, r12)
            long r8 = r8 + r21
            r29 = r13
            long r12 = r3 + r21
            float r12 = r1.getFloat(r12)
            r7.setFloat(r8, r12)
            long r8 = r3 + r19
            float r8 = r1.getFloat(r8)
            r7.setFloat(r5, r8)
            long r5 = r5 + r21
            long r8 = r3 + r17
            float r8 = r1.getFloat(r8)
            r7.setFloat(r5, r8)
            r5 = 4
            long r8 = r3 + r5
            float r5 = r1.getFloat(r8)
            r7.setFloat(r10, r5)
            long r10 = r10 + r21
            r5 = 5
            long r13 = r3 + r5
            float r5 = r1.getFloat(r13)
            r7.setFloat(r10, r5)
            long r5 = r3 + r15
            float r5 = r1.getFloat(r5)
            r10 = r29
            r7.setFloat(r10, r5)
            long r13 = r10 + r21
            r5 = 7
            long r3 = r3 + r5
            float r3 = r1.getFloat(r3)
            r7.setFloat(r13, r3)
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
            float r4 = r7.getFloat(r10)
            r1.setFloat(r8, r4)
            r29 = r5
            long r4 = r8 + r21
            long r10 = r10 + r21
            float r6 = r7.getFloat(r10)
            r1.setFloat(r4, r6)
            long r4 = r8 + r19
            float r6 = r7.getFloat(r12)
            r1.setFloat(r4, r6)
            long r4 = r8 + r17
            long r12 = r12 + r21
            float r6 = r7.getFloat(r12)
            r1.setFloat(r4, r6)
            r4 = 4
            long r10 = r8 + r4
            float r4 = r7.getFloat(r2)
            r1.setFloat(r10, r4)
            r4 = 5
            long r13 = r8 + r4
            long r2 = r2 + r21
            float r2 = r7.getFloat(r2)
            r1.setFloat(r13, r2)
            long r2 = r8 + r15
            r10 = r29
            float r6 = r7.getFloat(r10)
            r1.setFloat(r2, r6)
            r2 = 7
            long r8 = r8 + r2
            long r10 = r10 + r21
            float r6 = r7.getFloat(r10)
            r1.setFloat(r8, r6)
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
            float r4 = r1.getFloat(r5)
            r7.setFloat(r10, r4)
            long r10 = r10 + r21
            long r12 = r5 + r21
            float r4 = r1.getFloat(r12)
            r7.setFloat(r10, r4)
            long r10 = r5 + r19
            float r4 = r1.getFloat(r10)
            r7.setFloat(r2, r4)
            long r2 = r2 + r21
            long r5 = r5 + r17
            float r4 = r1.getFloat(r5)
            r7.setFloat(r2, r4)
            long r8 = r8 + r21
            r4 = 5
            r10 = 8
            r12 = 4
            goto L_0x0427
        L_0x0466:
            org.jtransforms.fft.FloatFFT_1D r2 = r0.fftRows
            r3 = r40
            r4 = 0
            r2.complexInverse((pl.edu.icm.jlargearrays.FloatLargeArray) r7, (long) r4, (boolean) r3)
            org.jtransforms.fft.FloatFFT_1D r2 = r0.fftRows
            long r4 = r0.rowsl
            long r4 = r4 * r19
            r2.complexInverse((pl.edu.icm.jlargearrays.FloatLargeArray) r7, (long) r4, (boolean) r3)
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
            float r2 = r7.getFloat(r12)
            r1.setFloat(r10, r2)
            long r14 = r10 + r21
            long r12 = r12 + r21
            float r2 = r7.getFloat(r12)
            r1.setFloat(r14, r2)
            long r12 = r10 + r19
            float r2 = r7.getFloat(r8)
            r1.setFloat(r12, r2)
            long r10 = r10 + r17
            long r8 = r8 + r21
            float r2 = r7.getFloat(r8)
            r1.setFloat(r10, r2)
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
            float r2 = r1.getFloat(r8)
            r7.setFloat(r10, r2)
            long r10 = r10 + r21
            long r8 = r8 + r21
            float r2 = r1.getFloat(r8)
            r7.setFloat(r10, r2)
            long r4 = r4 + r21
            goto L_0x04bd
        L_0x04df:
            org.jtransforms.fft.FloatFFT_1D r2 = r0.fftRows
            r4 = 0
            r2.complexInverse((pl.edu.icm.jlargearrays.FloatLargeArray) r7, (long) r4, (boolean) r3)
            r8 = r4
        L_0x04e7:
            long r10 = r0.rowsl
            int r2 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r2 >= 0) goto L_0x050b
            long r10 = r0.rowStridel
            long r10 = r10 * r8
            long r10 = r31 + r10
            long r12 = r8 * r19
            float r2 = r7.getFloat(r12)
            r1.setFloat(r10, r2)
            long r10 = r10 + r21
            long r12 = r12 + r21
            float r2 = r7.getFloat(r12)
            r1.setFloat(r10, r2)
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
            org.jtransforms.fft.FloatFFT_1D r2 = r0.fftColumns
            long r10 = r0.rowStridel
            long r10 = r10 * r8
            long r10 = r31 + r10
            r2.realInverse((pl.edu.icm.jlargearrays.FloatLargeArray) r1, (long) r10, (boolean) r3)
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
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.FloatFFT_3D.xdft3da_sub1(long, int, pl.edu.icm.jlargearrays.FloatLargeArray, boolean):void");
    }

    private void xdft3da_sub2(int i, int i2, float[] fArr, boolean z) {
        float[] fArr2 = fArr;
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
        float[] fArr3 = new float[i5];
        if (i2 == -1) {
            for (int i7 = 0; i7 < this.slices; i7++) {
                int i8 = this.sliceStride * i7;
                if (i == 0) {
                    for (int i9 = 0; i9 < this.rows; i9++) {
                        this.fftColumns.complexForward(fArr2, (this.rowStride * i9) + i8);
                    }
                } else {
                    for (int i10 = 0; i10 < this.rows; i10++) {
                        this.fftColumns.realForward(fArr2, (this.rowStride * i10) + i8);
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
                            fArr3[i16] = fArr2[i15];
                            fArr3[i16 + 1] = fArr2[i15 + 1];
                            fArr3[i17] = fArr2[i15 + 2];
                            fArr3[i17 + 1] = fArr2[i15 + 3];
                            fArr3[i18] = fArr2[i15 + 4];
                            fArr3[i18 + 1] = fArr2[i15 + 5];
                            fArr3[i19] = fArr2[i15 + 6];
                            fArr3[i19 + 1] = fArr2[i15 + 7];
                            i13++;
                        }
                        this.fftRows.complexForward(fArr3, 0);
                        this.fftRows.complexForward(fArr3, this.rows * 2);
                        this.fftRows.complexForward(fArr3, this.rows * 4);
                        this.fftRows.complexForward(fArr3, this.rows * 6);
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
                            fArr2[i22] = fArr3[i23];
                            fArr2[i22 + 1] = fArr3[i23 + 1];
                            fArr2[i22 + 2] = fArr3[i24];
                            fArr2[i22 + 3] = fArr3[i24 + 1];
                            fArr2[i22 + 4] = fArr3[i25];
                            fArr2[i22 + 5] = fArr3[i25 + 1];
                            fArr2[i22 + 6] = fArr3[i26];
                            fArr2[i22 + 7] = fArr3[i26 + 1];
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
                        fArr3[i30] = fArr2[i29];
                        fArr3[i30 + 1] = fArr2[i29 + 1];
                        fArr3[i31] = fArr2[i29 + 2];
                        fArr3[i31 + 1] = fArr2[i29 + 3];
                        i27++;
                    }
                    this.fftRows.complexForward(fArr3, 0);
                    this.fftRows.complexForward(fArr3, this.rows * 2);
                    int i32 = 0;
                    while (true) {
                        int i33 = this.rows;
                        if (i32 >= i33) {
                            break;
                        }
                        int i34 = (this.rowStride * i32) + i8;
                        int i35 = i32 * 2;
                        int i36 = (i33 * 2) + i35;
                        fArr2[i34] = fArr3[i35];
                        fArr2[i34 + 1] = fArr3[i35 + 1];
                        fArr2[i34 + 2] = fArr3[i36];
                        fArr2[i34 + 3] = fArr3[i36 + 1];
                        i32++;
                    }
                } else if (i11 == 2) {
                    for (int i37 = 0; i37 < this.rows; i37++) {
                        int i38 = (this.rowStride * i37) + i8;
                        int i39 = i37 * 2;
                        fArr3[i39] = fArr2[i38];
                        fArr3[i39 + 1] = fArr2[i38 + 1];
                    }
                    this.fftRows.complexForward(fArr3, 0);
                    for (int i40 = 0; i40 < this.rows; i40++) {
                        int i41 = (this.rowStride * i40) + i8;
                        int i42 = i40 * 2;
                        fArr2[i41] = fArr3[i42];
                        fArr2[i41 + 1] = fArr3[i42 + 1];
                    }
                }
            }
            return;
        }
        for (int i43 = 0; i43 < this.slices; i43++) {
            int i44 = this.sliceStride * i43;
            if (i == 0) {
                for (int i45 = 0; i45 < this.rows; i45++) {
                    this.fftColumns.complexInverse(fArr2, (this.rowStride * i45) + i44, z2);
                }
            } else {
                for (int i46 = 0; i46 < this.rows; i46++) {
                    this.fftColumns.realInverse2(fArr2, (this.rowStride * i46) + i44, z2);
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
                        fArr3[i52] = fArr2[i51];
                        fArr3[i52 + 1] = fArr2[i51 + 1];
                        fArr3[i53] = fArr2[i51 + 2];
                        fArr3[i53 + 1] = fArr2[i51 + 3];
                        fArr3[i54] = fArr2[i51 + 4];
                        fArr3[i54 + 1] = fArr2[i51 + 5];
                        fArr3[i55] = fArr2[i51 + 6];
                        fArr3[i55 + 1] = fArr2[i51 + 7];
                        i49++;
                    }
                    this.fftRows.complexInverse(fArr3, 0, z2);
                    this.fftRows.complexInverse(fArr3, this.rows * 2, z2);
                    this.fftRows.complexInverse(fArr3, this.rows * 4, z2);
                    this.fftRows.complexInverse(fArr3, this.rows * 6, z2);
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
                        fArr2[i58] = fArr3[i59];
                        fArr2[i58 + 1] = fArr3[i59 + 1];
                        fArr2[i58 + 2] = fArr3[i60];
                        fArr2[i58 + 3] = fArr3[i60 + 1];
                        fArr2[i58 + 4] = fArr3[i61];
                        fArr2[i58 + 5] = fArr3[i61 + 1];
                        fArr2[i58 + 6] = fArr3[i62];
                        fArr2[i58 + 7] = fArr3[i62 + 1];
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
                    fArr3[i66] = fArr2[i65];
                    fArr3[i66 + 1] = fArr2[i65 + 1];
                    fArr3[i67] = fArr2[i65 + 2];
                    fArr3[i67 + 1] = fArr2[i65 + 3];
                    i63++;
                }
                this.fftRows.complexInverse(fArr3, 0, z2);
                this.fftRows.complexInverse(fArr3, this.rows * 2, z2);
                int i68 = 0;
                while (true) {
                    int i69 = this.rows;
                    if (i68 >= i69) {
                        break;
                    }
                    int i70 = (this.rowStride * i68) + i44;
                    int i71 = i68 * 2;
                    int i72 = (i69 * 2) + i71;
                    fArr2[i70] = fArr3[i71];
                    fArr2[i70 + 1] = fArr3[i71 + 1];
                    fArr2[i70 + 2] = fArr3[i72];
                    fArr2[i70 + 3] = fArr3[i72 + 1];
                    i68++;
                }
            } else if (i47 == 2) {
                for (int i73 = 0; i73 < this.rows; i73++) {
                    int i74 = (this.rowStride * i73) + i44;
                    int i75 = i73 * 2;
                    fArr3[i75] = fArr2[i74];
                    fArr3[i75 + 1] = fArr2[i74 + 1];
                }
                this.fftRows.complexInverse(fArr3, 0, z2);
                for (int i76 = 0; i76 < this.rows; i76++) {
                    int i77 = (this.rowStride * i76) + i44;
                    int i78 = i76 * 2;
                    fArr2[i77] = fArr3[i78];
                    fArr2[i77 + 1] = fArr3[i78 + 1];
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0297  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void xdft3da_sub2(long r36, int r38, pl.edu.icm.jlargearrays.FloatLargeArray r39, boolean r40) {
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
            pl.edu.icm.jlargearrays.FloatLargeArray r7 = new pl.edu.icm.jlargearrays.FloatLargeArray
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
            org.jtransforms.fft.FloatFFT_1D r4 = r0.fftColumns
            long r5 = r0.rowStridel
            long r5 = r5 * r23
            long r5 = r5 + r11
            r4.complexForward((pl.edu.icm.jlargearrays.FloatLargeArray) r1, (long) r5)
            long r23 = r23 + r21
            goto L_0x0047
        L_0x005a:
            r4 = 0
        L_0x005c:
            long r13 = r0.rowsl
            int r6 = (r4 > r13 ? 1 : (r4 == r13 ? 0 : -1))
            if (r6 >= 0) goto L_0x006e
            org.jtransforms.fft.FloatFFT_1D r6 = r0.fftColumns
            long r13 = r0.rowStridel
            long r13 = r13 * r4
            long r13 = r13 + r11
            r6.realForward((pl.edu.icm.jlargearrays.FloatLargeArray) r1, (long) r13)
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
            float r6 = r1.getFloat(r2)
            r7.setFloat(r4, r6)
            long r4 = r4 + r21
            r29 = r9
            long r8 = r2 + r21
            float r6 = r1.getFloat(r8)
            r7.setFloat(r4, r6)
            long r4 = r2 + r19
            float r4 = r1.getFloat(r4)
            r7.setFloat(r11, r4)
            long r11 = r11 + r21
            long r4 = r2 + r17
            float r4 = r1.getFloat(r4)
            r7.setFloat(r11, r4)
            r4 = 4
            long r9 = r2 + r4
            float r4 = r1.getFloat(r9)
            r7.setFloat(r13, r4)
            long r13 = r13 + r21
            r4 = 5
            long r8 = r2 + r4
            float r4 = r1.getFloat(r8)
            r7.setFloat(r13, r4)
            long r4 = r2 + r15
            float r4 = r1.getFloat(r4)
            r13 = r29
            r7.setFloat(r13, r4)
            long r9 = r13 + r21
            r4 = 7
            long r2 = r2 + r4
            float r2 = r1.getFloat(r2)
            r7.setFloat(r9, r2)
            long r13 = r33 + r21
            r2 = r25
            r4 = r27
            r11 = r31
            goto L_0x007e
        L_0x00fe:
            r25 = r2
            r27 = r4
            r31 = r11
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
            float r6 = r7.getFloat(r8)
            r1.setFloat(r11, r6)
            r29 = r4
            long r4 = r11 + r21
            long r8 = r8 + r21
            float r6 = r7.getFloat(r8)
            r1.setFloat(r4, r6)
            long r4 = r11 + r19
            float r6 = r7.getFloat(r13)
            r1.setFloat(r4, r6)
            long r4 = r11 + r17
            long r13 = r13 + r21
            float r6 = r7.getFloat(r13)
            r1.setFloat(r4, r6)
            r4 = 4
            long r9 = r11 + r4
            float r4 = r7.getFloat(r2)
            r1.setFloat(r9, r4)
            r4 = 5
            long r13 = r11 + r4
            long r2 = r2 + r21
            float r2 = r7.getFloat(r2)
            r1.setFloat(r13, r2)
            long r2 = r11 + r15
            r4 = r29
            float r6 = r7.getFloat(r4)
            r1.setFloat(r2, r6)
            r2 = 7
            long r11 = r11 + r2
            long r4 = r4 + r21
            float r2 = r7.getFloat(r4)
            r1.setFloat(r11, r2)
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
            float r6 = r1.getFloat(r11)
            r7.setFloat(r8, r6)
            long r8 = r8 + r21
            long r13 = r11 + r21
            float r6 = r1.getFloat(r13)
            r7.setFloat(r8, r6)
            long r8 = r11 + r19
            float r6 = r1.getFloat(r8)
            r7.setFloat(r4, r6)
            long r4 = r4 + r21
            long r11 = r11 + r17
            float r6 = r1.getFloat(r11)
            r7.setFloat(r4, r6)
            long r2 = r2 + r21
            goto L_0x01b8
        L_0x01f1:
            org.jtransforms.fft.FloatFFT_1D r2 = r0.fftRows
            r3 = 0
            r2.complexForward((pl.edu.icm.jlargearrays.FloatLargeArray) r7, (long) r3)
            org.jtransforms.fft.FloatFFT_1D r2 = r0.fftRows
            long r3 = r0.rowsl
            long r3 = r3 * r19
            r2.complexForward((pl.edu.icm.jlargearrays.FloatLargeArray) r7, (long) r3)
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
            float r6 = r7.getFloat(r8)
            r1.setFloat(r11, r6)
            long r13 = r11 + r21
            long r8 = r8 + r21
            float r6 = r7.getFloat(r8)
            r1.setFloat(r13, r6)
            long r8 = r11 + r19
            float r6 = r7.getFloat(r4)
            r1.setFloat(r8, r6)
            long r11 = r11 + r17
            long r4 = r4 + r21
            float r4 = r7.getFloat(r4)
            r1.setFloat(r11, r4)
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
            float r6 = r1.getFloat(r11)
            r7.setFloat(r4, r6)
            long r4 = r4 + r21
            long r11 = r11 + r21
            float r6 = r1.getFloat(r11)
            r7.setFloat(r4, r6)
            long r2 = r2 + r21
            goto L_0x0242
        L_0x0264:
            org.jtransforms.fft.FloatFFT_1D r2 = r0.fftRows
            r3 = 0
            r2.complexForward((pl.edu.icm.jlargearrays.FloatLargeArray) r7, (long) r3)
            r2 = 0
        L_0x026d:
            long r4 = r0.rowsl
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 >= 0) goto L_0x028f
            long r4 = r0.rowStridel
            long r4 = r4 * r2
            long r11 = r31 + r4
            long r4 = r2 * r19
            float r6 = r7.getFloat(r4)
            r1.setFloat(r11, r6)
            long r11 = r11 + r21
            long r4 = r4 + r21
            float r4 = r7.getFloat(r4)
            r1.setFloat(r11, r4)
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
            org.jtransforms.fft.FloatFFT_1D r10 = r0.fftColumns
            long r11 = r0.rowStridel
            long r11 = r11 * r8
            long r11 = r11 + r5
            r10.complexInverse((pl.edu.icm.jlargearrays.FloatLargeArray) r1, (long) r11, (boolean) r2)
            long r8 = r8 + r21
            goto L_0x02aa
        L_0x02bc:
            r8 = 0
        L_0x02be:
            long r10 = r0.rowsl
            int r10 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r10 >= 0) goto L_0x02d0
            org.jtransforms.fft.FloatFFT_1D r10 = r0.fftColumns
            long r11 = r0.rowStridel
            long r11 = r11 * r8
            long r11 = r11 + r5
            r10.realInverse2((pl.edu.icm.jlargearrays.FloatLargeArray) r1, (long) r11, (boolean) r2)
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
            float r14 = r1.getFloat(r3)
            r7.setFloat(r8, r14)
            long r8 = r8 + r21
            r29 = r12
            long r12 = r3 + r21
            float r12 = r1.getFloat(r12)
            r7.setFloat(r8, r12)
            long r8 = r3 + r19
            float r8 = r1.getFloat(r8)
            r7.setFloat(r5, r8)
            long r5 = r5 + r21
            long r8 = r3 + r17
            float r8 = r1.getFloat(r8)
            r7.setFloat(r5, r8)
            r5 = 4
            long r8 = r3 + r5
            float r5 = r1.getFloat(r8)
            r7.setFloat(r10, r5)
            long r10 = r10 + r21
            r5 = 5
            long r13 = r3 + r5
            float r5 = r1.getFloat(r13)
            r7.setFloat(r10, r5)
            long r5 = r3 + r15
            float r5 = r1.getFloat(r5)
            r10 = r29
            r7.setFloat(r10, r5)
            long r12 = r10 + r21
            r5 = 7
            long r3 = r3 + r5
            float r3 = r1.getFloat(r3)
            r7.setFloat(r12, r3)
            long r10 = r33 + r21
            r3 = r25
            r8 = r27
            r5 = r31
            goto L_0x02e2
        L_0x0362:
            r25 = r3
            r31 = r5
            r27 = r8
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
            float r4 = r7.getFloat(r10)
            r1.setFloat(r8, r4)
            r29 = r5
            long r4 = r8 + r21
            long r10 = r10 + r21
            float r6 = r7.getFloat(r10)
            r1.setFloat(r4, r6)
            long r4 = r8 + r19
            float r6 = r7.getFloat(r12)
            r1.setFloat(r4, r6)
            long r4 = r8 + r17
            long r12 = r12 + r21
            float r6 = r7.getFloat(r12)
            r1.setFloat(r4, r6)
            r4 = 4
            long r10 = r8 + r4
            float r4 = r7.getFloat(r2)
            r1.setFloat(r10, r4)
            r4 = 5
            long r13 = r8 + r4
            long r2 = r2 + r21
            float r2 = r7.getFloat(r2)
            r1.setFloat(r13, r2)
            long r2 = r8 + r15
            r10 = r29
            float r6 = r7.getFloat(r10)
            r1.setFloat(r2, r6)
            r2 = 7
            long r8 = r8 + r2
            long r10 = r10 + r21
            float r6 = r7.getFloat(r10)
            r1.setFloat(r8, r6)
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
            float r4 = r1.getFloat(r5)
            r7.setFloat(r10, r4)
            long r10 = r10 + r21
            long r12 = r5 + r21
            float r4 = r1.getFloat(r12)
            r7.setFloat(r10, r4)
            long r10 = r5 + r19
            float r4 = r1.getFloat(r10)
            r7.setFloat(r2, r4)
            long r2 = r2 + r21
            long r5 = r5 + r17
            float r4 = r1.getFloat(r5)
            r7.setFloat(r2, r4)
            long r8 = r8 + r21
            r4 = 5
            r10 = 8
            r12 = 4
            goto L_0x042c
        L_0x046b:
            org.jtransforms.fft.FloatFFT_1D r2 = r0.fftRows
            r3 = r40
            r4 = 0
            r2.complexInverse((pl.edu.icm.jlargearrays.FloatLargeArray) r7, (long) r4, (boolean) r3)
            org.jtransforms.fft.FloatFFT_1D r2 = r0.fftRows
            long r4 = r0.rowsl
            long r4 = r4 * r19
            r2.complexInverse((pl.edu.icm.jlargearrays.FloatLargeArray) r7, (long) r4, (boolean) r3)
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
            float r2 = r7.getFloat(r12)
            r1.setFloat(r10, r2)
            long r14 = r10 + r21
            long r12 = r12 + r21
            float r2 = r7.getFloat(r12)
            r1.setFloat(r14, r2)
            long r12 = r10 + r19
            float r2 = r7.getFloat(r8)
            r1.setFloat(r12, r2)
            long r10 = r10 + r17
            long r8 = r8 + r21
            float r2 = r7.getFloat(r8)
            r1.setFloat(r10, r2)
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
            float r2 = r1.getFloat(r8)
            r7.setFloat(r10, r2)
            long r10 = r10 + r21
            long r8 = r8 + r21
            float r2 = r1.getFloat(r8)
            r7.setFloat(r10, r2)
            long r4 = r4 + r21
            goto L_0x04c2
        L_0x04e4:
            org.jtransforms.fft.FloatFFT_1D r2 = r0.fftRows
            r4 = 0
            r2.complexInverse((pl.edu.icm.jlargearrays.FloatLargeArray) r7, (long) r4, (boolean) r3)
            r8 = r4
        L_0x04ec:
            long r10 = r0.rowsl
            int r2 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r2 >= 0) goto L_0x0510
            long r10 = r0.rowStridel
            long r10 = r10 * r8
            long r10 = r31 + r10
            long r12 = r8 * r19
            float r2 = r7.getFloat(r12)
            r1.setFloat(r10, r2)
            long r10 = r10 + r21
            long r12 = r12 + r21
            float r2 = r7.getFloat(r12)
            r1.setFloat(r10, r2)
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
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.FloatFFT_3D.xdft3da_sub2(long, int, pl.edu.icm.jlargearrays.FloatLargeArray, boolean):void");
    }

    private void xdft3da_sub1(int i, int i2, float[][][] fArr, boolean z) {
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
        float[] fArr2 = new float[i5];
        if (i2 == -1) {
            for (int i7 = 0; i7 < this.slices; i7++) {
                if (i == 0) {
                    for (int i8 = 0; i8 < this.rows; i8++) {
                        this.fftColumns.complexForward(fArr[i7][i8]);
                    }
                } else {
                    for (int i9 = 0; i9 < this.rows; i9++) {
                        this.fftColumns.realForward(fArr[i7][i9], 0);
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
                            float[] fArr3 = fArr[i7][i12];
                            fArr2[i14] = fArr3[i11];
                            fArr2[i14 + 1] = fArr3[i11 + 1];
                            fArr2[i15] = fArr3[i11 + 2];
                            fArr2[i15 + 1] = fArr3[i11 + 3];
                            fArr2[i16] = fArr3[i11 + 4];
                            fArr2[i16 + 1] = fArr3[i11 + 5];
                            fArr2[i17] = fArr3[i11 + 6];
                            fArr2[i17 + 1] = fArr3[i11 + 7];
                            i12++;
                        }
                        this.fftRows.complexForward(fArr2, 0);
                        this.fftRows.complexForward(fArr2, this.rows * 2);
                        this.fftRows.complexForward(fArr2, this.rows * 4);
                        this.fftRows.complexForward(fArr2, this.rows * 6);
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
                            float[] fArr4 = fArr[i7][i18];
                            fArr4[i11] = fArr2[i20];
                            fArr4[i11 + 1] = fArr2[i20 + 1];
                            fArr4[i11 + 2] = fArr2[i21];
                            fArr4[i11 + 3] = fArr2[i21 + 1];
                            fArr4[i11 + 4] = fArr2[i22];
                            fArr4[i11 + 5] = fArr2[i22 + 1];
                            fArr4[i11 + 6] = fArr2[i23];
                            fArr4[i11 + 7] = fArr2[i23 + 1];
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
                        float[] fArr5 = fArr[i7][i24];
                        fArr2[i26] = fArr5[0];
                        fArr2[i26 + 1] = fArr5[1];
                        fArr2[i27] = fArr5[2];
                        fArr2[i27 + 1] = fArr5[3];
                        i24++;
                    }
                    this.fftRows.complexForward(fArr2, 0);
                    this.fftRows.complexForward(fArr2, this.rows * 2);
                    int i28 = 0;
                    while (true) {
                        int i29 = this.rows;
                        if (i28 >= i29) {
                            break;
                        }
                        int i30 = i28 * 2;
                        int i31 = (i29 * 2) + i30;
                        float[] fArr6 = fArr[i7][i28];
                        fArr6[0] = fArr2[i30];
                        fArr6[1] = fArr2[i30 + 1];
                        fArr6[2] = fArr2[i31];
                        fArr6[3] = fArr2[i31 + 1];
                        i28++;
                    }
                } else if (i10 == 2) {
                    for (int i32 = 0; i32 < this.rows; i32++) {
                        int i33 = i32 * 2;
                        float[] fArr7 = fArr[i7][i32];
                        fArr2[i33] = fArr7[0];
                        fArr2[i33 + 1] = fArr7[1];
                    }
                    this.fftRows.complexForward(fArr2, 0);
                    for (int i34 = 0; i34 < this.rows; i34++) {
                        int i35 = i34 * 2;
                        float[] fArr8 = fArr[i7][i34];
                        fArr8[0] = fArr2[i35];
                        fArr8[1] = fArr2[i35 + 1];
                    }
                }
            }
            return;
        }
        for (int i36 = 0; i36 < this.slices; i36++) {
            if (i == 0) {
                for (int i37 = 0; i37 < this.rows; i37++) {
                    this.fftColumns.complexInverse(fArr[i36][i37], z2);
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
                        float[] fArr9 = fArr[i36][i40];
                        fArr2[i42] = fArr9[i39];
                        fArr2[i42 + 1] = fArr9[i39 + 1];
                        fArr2[i43] = fArr9[i39 + 2];
                        fArr2[i43 + 1] = fArr9[i39 + 3];
                        fArr2[i44] = fArr9[i39 + 4];
                        fArr2[i44 + 1] = fArr9[i39 + 5];
                        fArr2[i45] = fArr9[i39 + 6];
                        fArr2[i45 + 1] = fArr9[i39 + 7];
                        i40++;
                    }
                    this.fftRows.complexInverse(fArr2, 0, z2);
                    this.fftRows.complexInverse(fArr2, this.rows * 2, z2);
                    this.fftRows.complexInverse(fArr2, this.rows * 4, z2);
                    this.fftRows.complexInverse(fArr2, this.rows * 6, z2);
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
                        float[] fArr10 = fArr[i36][i46];
                        fArr10[i39] = fArr2[i48];
                        fArr10[i39 + 1] = fArr2[i48 + 1];
                        fArr10[i39 + 2] = fArr2[i49];
                        fArr10[i39 + 3] = fArr2[i49 + 1];
                        fArr10[i39 + 4] = fArr2[i50];
                        fArr10[i39 + 5] = fArr2[i50 + 1];
                        fArr10[i39 + 6] = fArr2[i51];
                        fArr10[i39 + 7] = fArr2[i51 + 1];
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
                    float[] fArr11 = fArr[i36][i52];
                    fArr2[i54] = fArr11[0];
                    fArr2[i54 + 1] = fArr11[1];
                    fArr2[i55] = fArr11[2];
                    fArr2[i55 + 1] = fArr11[3];
                    i52++;
                }
                this.fftRows.complexInverse(fArr2, 0, z2);
                this.fftRows.complexInverse(fArr2, this.rows * 2, z2);
                int i56 = 0;
                while (true) {
                    int i57 = this.rows;
                    if (i56 >= i57) {
                        break;
                    }
                    int i58 = i56 * 2;
                    int i59 = (i57 * 2) + i58;
                    float[] fArr12 = fArr[i36][i56];
                    fArr12[0] = fArr2[i58];
                    fArr12[1] = fArr2[i58 + 1];
                    fArr12[2] = fArr2[i59];
                    fArr12[3] = fArr2[i59 + 1];
                    i56++;
                }
            } else if (i38 == 2) {
                for (int i60 = 0; i60 < this.rows; i60++) {
                    int i61 = i60 * 2;
                    float[] fArr13 = fArr[i36][i60];
                    fArr2[i61] = fArr13[0];
                    fArr2[i61 + 1] = fArr13[1];
                }
                this.fftRows.complexInverse(fArr2, 0, z2);
                for (int i62 = 0; i62 < this.rows; i62++) {
                    int i63 = i62 * 2;
                    float[] fArr14 = fArr[i36][i62];
                    fArr14[0] = fArr2[i63];
                    fArr14[1] = fArr2[i63 + 1];
                }
            }
            if (i != 0) {
                for (int i64 = 0; i64 < this.rows; i64++) {
                    this.fftColumns.realInverse(fArr[i36][i64], z2);
                }
            }
        }
    }

    private void xdft3da_sub2(int i, int i2, float[][][] fArr, boolean z) {
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
        float[] fArr2 = new float[i5];
        if (i2 == -1) {
            for (int i7 = 0; i7 < this.slices; i7++) {
                if (i == 0) {
                    for (int i8 = 0; i8 < this.rows; i8++) {
                        this.fftColumns.complexForward(fArr[i7][i8]);
                    }
                } else {
                    for (int i9 = 0; i9 < this.rows; i9++) {
                        this.fftColumns.realForward(fArr[i7][i9]);
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
                            float[] fArr3 = fArr[i7][i12];
                            fArr2[i14] = fArr3[i11];
                            fArr2[i14 + 1] = fArr3[i11 + 1];
                            fArr2[i15] = fArr3[i11 + 2];
                            fArr2[i15 + 1] = fArr3[i11 + 3];
                            fArr2[i16] = fArr3[i11 + 4];
                            fArr2[i16 + 1] = fArr3[i11 + 5];
                            fArr2[i17] = fArr3[i11 + 6];
                            fArr2[i17 + 1] = fArr3[i11 + 7];
                            i12++;
                        }
                        this.fftRows.complexForward(fArr2, 0);
                        this.fftRows.complexForward(fArr2, this.rows * 2);
                        this.fftRows.complexForward(fArr2, this.rows * 4);
                        this.fftRows.complexForward(fArr2, this.rows * 6);
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
                            float[] fArr4 = fArr[i7][i18];
                            fArr4[i11] = fArr2[i20];
                            fArr4[i11 + 1] = fArr2[i20 + 1];
                            fArr4[i11 + 2] = fArr2[i21];
                            fArr4[i11 + 3] = fArr2[i21 + 1];
                            fArr4[i11 + 4] = fArr2[i22];
                            fArr4[i11 + 5] = fArr2[i22 + 1];
                            fArr4[i11 + 6] = fArr2[i23];
                            fArr4[i11 + 7] = fArr2[i23 + 1];
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
                        float[] fArr5 = fArr[i7][i24];
                        fArr2[i26] = fArr5[0];
                        fArr2[i26 + 1] = fArr5[1];
                        fArr2[i27] = fArr5[2];
                        fArr2[i27 + 1] = fArr5[3];
                        i24++;
                    }
                    this.fftRows.complexForward(fArr2, 0);
                    this.fftRows.complexForward(fArr2, this.rows * 2);
                    int i28 = 0;
                    while (true) {
                        int i29 = this.rows;
                        if (i28 >= i29) {
                            break;
                        }
                        int i30 = i28 * 2;
                        int i31 = (i29 * 2) + i30;
                        float[] fArr6 = fArr[i7][i28];
                        fArr6[0] = fArr2[i30];
                        fArr6[1] = fArr2[i30 + 1];
                        fArr6[2] = fArr2[i31];
                        fArr6[3] = fArr2[i31 + 1];
                        i28++;
                    }
                } else if (i10 == 2) {
                    for (int i32 = 0; i32 < this.rows; i32++) {
                        int i33 = i32 * 2;
                        float[] fArr7 = fArr[i7][i32];
                        fArr2[i33] = fArr7[0];
                        fArr2[i33 + 1] = fArr7[1];
                    }
                    this.fftRows.complexForward(fArr2, 0);
                    for (int i34 = 0; i34 < this.rows; i34++) {
                        int i35 = i34 * 2;
                        float[] fArr8 = fArr[i7][i34];
                        fArr8[0] = fArr2[i35];
                        fArr8[1] = fArr2[i35 + 1];
                    }
                }
            }
            return;
        }
        for (int i36 = 0; i36 < this.slices; i36++) {
            if (i == 0) {
                for (int i37 = 0; i37 < this.rows; i37++) {
                    this.fftColumns.complexInverse(fArr[i36][i37], z2);
                }
            } else {
                for (int i38 = 0; i38 < this.rows; i38++) {
                    this.fftColumns.realInverse2(fArr[i36][i38], 0, z2);
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
                        float[] fArr9 = fArr[i36][i41];
                        fArr2[i43] = fArr9[i40];
                        fArr2[i43 + 1] = fArr9[i40 + 1];
                        fArr2[i44] = fArr9[i40 + 2];
                        fArr2[i44 + 1] = fArr9[i40 + 3];
                        fArr2[i45] = fArr9[i40 + 4];
                        fArr2[i45 + 1] = fArr9[i40 + 5];
                        fArr2[i46] = fArr9[i40 + 6];
                        fArr2[i46 + 1] = fArr9[i40 + 7];
                        i41++;
                    }
                    this.fftRows.complexInverse(fArr2, 0, z2);
                    this.fftRows.complexInverse(fArr2, this.rows * 2, z2);
                    this.fftRows.complexInverse(fArr2, this.rows * 4, z2);
                    this.fftRows.complexInverse(fArr2, this.rows * 6, z2);
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
                        float[] fArr10 = fArr[i36][i47];
                        fArr10[i40] = fArr2[i49];
                        fArr10[i40 + 1] = fArr2[i49 + 1];
                        fArr10[i40 + 2] = fArr2[i50];
                        fArr10[i40 + 3] = fArr2[i50 + 1];
                        fArr10[i40 + 4] = fArr2[i51];
                        fArr10[i40 + 5] = fArr2[i51 + 1];
                        fArr10[i40 + 6] = fArr2[i52];
                        fArr10[i40 + 7] = fArr2[i52 + 1];
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
                    float[] fArr11 = fArr[i36][i53];
                    fArr2[i55] = fArr11[0];
                    fArr2[i55 + 1] = fArr11[1];
                    fArr2[i56] = fArr11[2];
                    fArr2[i56 + 1] = fArr11[3];
                    i53++;
                }
                this.fftRows.complexInverse(fArr2, 0, z2);
                this.fftRows.complexInverse(fArr2, this.rows * 2, z2);
                int i57 = 0;
                while (true) {
                    int i58 = this.rows;
                    if (i57 >= i58) {
                        break;
                    }
                    int i59 = i57 * 2;
                    int i60 = (i58 * 2) + i59;
                    float[] fArr12 = fArr[i36][i57];
                    fArr12[0] = fArr2[i59];
                    fArr12[1] = fArr2[i59 + 1];
                    fArr12[2] = fArr2[i60];
                    fArr12[3] = fArr2[i60 + 1];
                    i57++;
                }
            } else if (i39 == 2) {
                for (int i61 = 0; i61 < this.rows; i61++) {
                    int i62 = i61 * 2;
                    float[] fArr13 = fArr[i36][i61];
                    fArr2[i62] = fArr13[0];
                    fArr2[i62 + 1] = fArr13[1];
                }
                this.fftRows.complexInverse(fArr2, 0, z2);
                for (int i63 = 0; i63 < this.rows; i63++) {
                    int i64 = i63 * 2;
                    float[] fArr14 = fArr[i36][i63];
                    fArr14[0] = fArr2[i64];
                    fArr14[1] = fArr2[i64 + 1];
                }
            }
        }
    }

    private void cdft3db_sub(int i, float[] fArr, boolean z) {
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
        float[] fArr2 = new float[i4];
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
                            fArr2[i12] = fArr[i11];
                            fArr2[i12 + 1] = fArr[i11 + 1];
                            fArr2[i13] = fArr[i11 + 2];
                            fArr2[i13 + 1] = fArr[i11 + 3];
                            fArr2[i14] = fArr[i11 + 4];
                            fArr2[i14 + 1] = fArr[i11 + 5];
                            fArr2[i15] = fArr[i11 + 6];
                            fArr2[i15 + 1] = fArr[i11 + 7];
                            i9++;
                        }
                        this.fftSlices.complexForward(fArr2, 0);
                        this.fftSlices.complexForward(fArr2, this.slices * 2);
                        this.fftSlices.complexForward(fArr2, this.slices * 4);
                        this.fftSlices.complexForward(fArr2, this.slices * 6);
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
                            fArr[i18] = fArr2[i19];
                            fArr[i18 + 1] = fArr2[i19 + 1];
                            fArr[i18 + 2] = fArr2[i20];
                            fArr[i18 + 3] = fArr2[i20 + 1];
                            fArr[i18 + 4] = fArr2[i21];
                            fArr[i18 + 5] = fArr2[i21 + 1];
                            fArr[i18 + 6] = fArr2[i22];
                            fArr[i18 + 7] = fArr2[i22 + 1];
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
                        fArr2[i28] = fArr[i27];
                        fArr2[i28 + 1] = fArr[i27 + 1];
                        fArr2[i29] = fArr[i27 + 2];
                        fArr2[i29 + 1] = fArr[i27 + 3];
                        i25++;
                    }
                    this.fftSlices.complexForward(fArr2, 0);
                    this.fftSlices.complexForward(fArr2, this.slices * 2);
                    int i30 = 0;
                    while (true) {
                        int i31 = this.slices;
                        if (i30 >= i31) {
                            break;
                        }
                        int i32 = (this.sliceStride * i30) + i24;
                        int i33 = i30 * 2;
                        int i34 = (i31 * 2) + i33;
                        fArr[i32] = fArr2[i33];
                        fArr[i32 + 1] = fArr2[i33 + 1];
                        fArr[i32 + 2] = fArr2[i34];
                        fArr[i32 + 3] = fArr2[i34 + 1];
                        i30++;
                    }
                }
            } else if (i5 == 2) {
                for (int i35 = 0; i35 < this.rows; i35++) {
                    int i36 = this.rowStride * i35;
                    for (int i37 = 0; i37 < this.slices; i37++) {
                        int i38 = (this.sliceStride * i37) + i36;
                        int i39 = i37 * 2;
                        fArr2[i39] = fArr[i38];
                        fArr2[i39 + 1] = fArr[i38 + 1];
                    }
                    this.fftSlices.complexForward(fArr2, 0);
                    for (int i40 = 0; i40 < this.slices; i40++) {
                        int i41 = (this.sliceStride * i40) + i36;
                        int i42 = i40 * 2;
                        fArr[i41] = fArr2[i42];
                        fArr[i41 + 1] = fArr2[i42 + 1];
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
                        fArr2[i49] = fArr[i48];
                        fArr2[i49 + 1] = fArr[i48 + 1];
                        fArr2[i50] = fArr[i48 + 2];
                        fArr2[i50 + 1] = fArr[i48 + 3];
                        fArr2[i51] = fArr[i48 + 4];
                        fArr2[i51 + 1] = fArr[i48 + 5];
                        fArr2[i52] = fArr[i48 + 6];
                        fArr2[i52 + 1] = fArr[i48 + 7];
                        i46++;
                    }
                    this.fftSlices.complexInverse(fArr2, 0, z2);
                    this.fftSlices.complexInverse(fArr2, this.slices * 2, z2);
                    this.fftSlices.complexInverse(fArr2, this.slices * 4, z2);
                    this.fftSlices.complexInverse(fArr2, this.slices * 6, z2);
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
                        fArr[i55] = fArr2[i56];
                        fArr[i55 + 1] = fArr2[i56 + 1];
                        fArr[i55 + 2] = fArr2[i57];
                        fArr[i55 + 3] = fArr2[i57 + 1];
                        fArr[i55 + 4] = fArr2[i58];
                        fArr[i55 + 5] = fArr2[i58 + 1];
                        fArr[i55 + 6] = fArr2[i59];
                        fArr[i55 + 7] = fArr2[i59 + 1];
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
                    fArr2[i65] = fArr[i64];
                    fArr2[i65 + 1] = fArr[i64 + 1];
                    fArr2[i66] = fArr[i64 + 2];
                    fArr2[i66 + 1] = fArr[i64 + 3];
                    i62++;
                }
                this.fftSlices.complexInverse(fArr2, 0, z2);
                this.fftSlices.complexInverse(fArr2, this.slices * 2, z2);
                int i67 = 0;
                while (true) {
                    int i68 = this.slices;
                    if (i67 >= i68) {
                        break;
                    }
                    int i69 = (this.sliceStride * i67) + i61;
                    int i70 = i67 * 2;
                    int i71 = (i68 * 2) + i70;
                    fArr[i69] = fArr2[i70];
                    fArr[i69 + 1] = fArr2[i70 + 1];
                    fArr[i69 + 2] = fArr2[i71];
                    fArr[i69 + 3] = fArr2[i71 + 1];
                    i67++;
                }
            }
        } else if (i5 == 2) {
            for (int i72 = 0; i72 < this.rows; i72++) {
                int i73 = this.rowStride * i72;
                for (int i74 = 0; i74 < this.slices; i74++) {
                    int i75 = (this.sliceStride * i74) + i73;
                    int i76 = i74 * 2;
                    fArr2[i76] = fArr[i75];
                    fArr2[i76 + 1] = fArr[i75 + 1];
                }
                this.fftSlices.complexInverse(fArr2, 0, z2);
                for (int i77 = 0; i77 < this.slices; i77++) {
                    int i78 = (this.sliceStride * i77) + i73;
                    int i79 = i77 * 2;
                    fArr[i78] = fArr2[i79];
                    fArr[i78 + 1] = fArr2[i79 + 1];
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x027d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void cdft3db_sub(int r36, pl.edu.icm.jlargearrays.FloatLargeArray r37, boolean r38) {
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
            pl.edu.icm.jlargearrays.FloatLargeArray r7 = new pl.edu.icm.jlargearrays.FloatLargeArray
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
            if (r4 >= 0) goto L_0x04e1
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
            float r6 = r1.getFloat(r9)
            r7.setFloat(r2, r6)
            long r2 = r2 + r23
            r29 = r13
            long r13 = r9 + r23
            float r6 = r1.getFloat(r13)
            r7.setFloat(r2, r6)
            long r2 = r9 + r21
            float r2 = r1.getFloat(r2)
            r7.setFloat(r4, r2)
            long r4 = r4 + r23
            long r2 = r9 + r17
            float r2 = r1.getFloat(r2)
            r7.setFloat(r4, r2)
            r2 = 4
            long r4 = r9 + r2
            float r2 = r1.getFloat(r4)
            r7.setFloat(r11, r2)
            long r11 = r11 + r23
            r2 = 5
            long r13 = r9 + r2
            float r2 = r1.getFloat(r13)
            r7.setFloat(r11, r2)
            long r2 = r9 + r15
            float r2 = r1.getFloat(r2)
            r11 = r29
            r7.setFloat(r11, r2)
            long r13 = r11 + r23
            r2 = 7
            long r9 = r9 + r2
            float r2 = r1.getFloat(r9)
            r7.setFloat(r13, r2)
            long r11 = r33 + r23
            r2 = r27
            r4 = r31
            r9 = 4
            goto L_0x0050
        L_0x00cf:
            r27 = r2
            r31 = r4
            org.jtransforms.fft.FloatFFT_1D r2 = r0.fftSlices
            r3 = 0
            r2.complexForward((pl.edu.icm.jlargearrays.FloatLargeArray) r7, (long) r3)
            org.jtransforms.fft.FloatFFT_1D r2 = r0.fftSlices
            long r3 = r0.slicesl
            long r3 = r3 * r21
            r2.complexForward((pl.edu.icm.jlargearrays.FloatLargeArray) r7, (long) r3)
            org.jtransforms.fft.FloatFFT_1D r2 = r0.fftSlices
            long r3 = r0.slicesl
            r5 = 4
            long r3 = r3 * r5
            r2.complexForward((pl.edu.icm.jlargearrays.FloatLargeArray) r7, (long) r3)
            org.jtransforms.fft.FloatFFT_1D r2 = r0.fftSlices
            long r3 = r0.slicesl
            long r3 = r3 * r15
            r2.complexForward((pl.edu.icm.jlargearrays.FloatLargeArray) r7, (long) r3)
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
            float r6 = r7.getFloat(r10)
            r1.setFloat(r8, r6)
            r29 = r4
            long r4 = r8 + r23
            long r10 = r10 + r23
            float r6 = r7.getFloat(r10)
            r1.setFloat(r4, r6)
            long r4 = r8 + r21
            float r6 = r7.getFloat(r12)
            r1.setFloat(r4, r6)
            long r4 = r8 + r17
            long r12 = r12 + r23
            float r6 = r7.getFloat(r12)
            r1.setFloat(r4, r6)
            r4 = 4
            long r10 = r8 + r4
            float r4 = r7.getFloat(r2)
            r1.setFloat(r10, r4)
            r4 = 5
            long r13 = r8 + r4
            long r2 = r2 + r23
            float r2 = r7.getFloat(r2)
            r1.setFloat(r13, r2)
            long r2 = r8 + r15
            r4 = r29
            float r6 = r7.getFloat(r4)
            r1.setFloat(r2, r6)
            r2 = 7
            long r8 = r8 + r2
            long r4 = r4 + r23
            float r2 = r7.getFloat(r4)
            r1.setFloat(r8, r2)
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
            if (r4 >= 0) goto L_0x04e1
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
            float r6 = r1.getFloat(r12)
            r7.setFloat(r14, r6)
            long r14 = r14 + r23
            r19 = r2
            long r2 = r12 + r23
            float r2 = r1.getFloat(r2)
            r7.setFloat(r14, r2)
            long r2 = r12 + r21
            float r2 = r1.getFloat(r2)
            r7.setFloat(r10, r2)
            long r10 = r10 + r23
            long r12 = r12 + r17
            float r2 = r1.getFloat(r12)
            r7.setFloat(r10, r2)
            long r8 = r8 + r23
            r2 = r19
            goto L_0x0192
        L_0x01ce:
            r19 = r2
            org.jtransforms.fft.FloatFFT_1D r2 = r0.fftSlices
            r8 = 0
            r2.complexForward((pl.edu.icm.jlargearrays.FloatLargeArray) r7, (long) r8)
            org.jtransforms.fft.FloatFFT_1D r2 = r0.fftSlices
            long r8 = r0.slicesl
            long r8 = r8 * r21
            r2.complexForward((pl.edu.icm.jlargearrays.FloatLargeArray) r7, (long) r8)
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
            float r6 = r7.getFloat(r12)
            r1.setFloat(r10, r6)
            long r14 = r10 + r23
            long r12 = r12 + r23
            float r6 = r7.getFloat(r12)
            r1.setFloat(r14, r6)
            long r12 = r10 + r21
            float r6 = r7.getFloat(r8)
            r1.setFloat(r12, r6)
            long r10 = r10 + r17
            long r8 = r8 + r23
            float r6 = r7.getFloat(r8)
            r1.setFloat(r10, r6)
            long r2 = r2 + r23
            goto L_0x01e2
        L_0x021a:
            long r2 = r19 + r23
            goto L_0x0187
        L_0x021e:
            int r2 = (r2 > r21 ? 1 : (r2 == r21 ? 0 : -1))
            if (r2 != 0) goto L_0x04e1
            r2 = 0
        L_0x0224:
            long r4 = r0.rowsl
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 >= 0) goto L_0x04e1
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
            float r6 = r1.getFloat(r10)
            r7.setFloat(r12, r6)
            long r12 = r12 + r23
            long r10 = r10 + r23
            float r6 = r1.getFloat(r10)
            r7.setFloat(r12, r6)
            long r8 = r8 + r23
            goto L_0x022f
        L_0x0250:
            org.jtransforms.fft.FloatFFT_1D r6 = r0.fftSlices
            r8 = 0
            r6.complexForward((pl.edu.icm.jlargearrays.FloatLargeArray) r7, (long) r8)
            r8 = 0
        L_0x0259:
            long r10 = r0.slicesl
            int r6 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r6 >= 0) goto L_0x027a
            long r10 = r0.sliceStridel
            long r10 = r10 * r8
            long r10 = r10 + r4
            long r12 = r8 * r21
            float r6 = r7.getFloat(r12)
            r1.setFloat(r10, r6)
            long r10 = r10 + r23
            long r12 = r12 + r23
            float r6 = r7.getFloat(r12)
            r1.setFloat(r10, r6)
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
            if (r5 >= 0) goto L_0x04e1
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
            float r14 = r1.getFloat(r3)
            r7.setFloat(r8, r14)
            long r8 = r8 + r23
            r29 = r12
            long r12 = r3 + r23
            float r12 = r1.getFloat(r12)
            r7.setFloat(r8, r12)
            long r8 = r3 + r21
            float r8 = r1.getFloat(r8)
            r7.setFloat(r5, r8)
            long r5 = r5 + r23
            long r8 = r3 + r17
            float r8 = r1.getFloat(r8)
            r7.setFloat(r5, r8)
            r5 = 4
            long r8 = r3 + r5
            float r5 = r1.getFloat(r8)
            r7.setFloat(r10, r5)
            long r10 = r10 + r23
            r5 = 5
            long r13 = r3 + r5
            float r5 = r1.getFloat(r13)
            r7.setFloat(r10, r5)
            long r5 = r3 + r15
            float r5 = r1.getFloat(r5)
            r10 = r29
            r7.setFloat(r10, r5)
            long r12 = r10 + r23
            r5 = 7
            long r3 = r3 + r5
            float r3 = r1.getFloat(r3)
            r7.setFloat(r12, r3)
            long r10 = r33 + r23
            r3 = r25
            r8 = r27
            r5 = r31
            goto L_0x029a
        L_0x031a:
            r25 = r3
            r31 = r5
            r27 = r8
            org.jtransforms.fft.FloatFFT_1D r3 = r0.fftSlices
            r4 = 0
            r3.complexInverse((pl.edu.icm.jlargearrays.FloatLargeArray) r7, (long) r4, (boolean) r2)
            org.jtransforms.fft.FloatFFT_1D r3 = r0.fftSlices
            long r4 = r0.slicesl
            long r4 = r4 * r21
            r3.complexInverse((pl.edu.icm.jlargearrays.FloatLargeArray) r7, (long) r4, (boolean) r2)
            org.jtransforms.fft.FloatFFT_1D r3 = r0.fftSlices
            long r4 = r0.slicesl
            r8 = 4
            long r4 = r4 * r8
            r3.complexInverse((pl.edu.icm.jlargearrays.FloatLargeArray) r7, (long) r4, (boolean) r2)
            org.jtransforms.fft.FloatFFT_1D r3 = r0.fftSlices
            long r4 = r0.slicesl
            long r4 = r4 * r15
            r3.complexInverse((pl.edu.icm.jlargearrays.FloatLargeArray) r7, (long) r4, (boolean) r2)
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
            float r4 = r7.getFloat(r10)
            r1.setFloat(r8, r4)
            r29 = r5
            long r4 = r8 + r23
            long r10 = r10 + r23
            float r6 = r7.getFloat(r10)
            r1.setFloat(r4, r6)
            long r4 = r8 + r21
            float r6 = r7.getFloat(r12)
            r1.setFloat(r4, r6)
            long r4 = r8 + r17
            long r12 = r12 + r23
            float r6 = r7.getFloat(r12)
            r1.setFloat(r4, r6)
            r4 = 4
            long r10 = r8 + r4
            float r4 = r7.getFloat(r2)
            r1.setFloat(r10, r4)
            r4 = 5
            long r13 = r8 + r4
            long r2 = r2 + r23
            float r2 = r7.getFloat(r2)
            r1.setFloat(r13, r2)
            long r2 = r8 + r15
            r10 = r29
            float r6 = r7.getFloat(r10)
            r1.setFloat(r2, r6)
            r2 = 7
            long r8 = r8 + r2
            long r10 = r10 + r23
            float r6 = r7.getFloat(r10)
            r1.setFloat(r8, r6)
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
            if (r2 >= 0) goto L_0x04e1
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
            float r2 = r1.getFloat(r12)
            r7.setFloat(r14, r2)
            long r14 = r14 + r23
            r19 = r3
            long r2 = r12 + r23
            float r2 = r1.getFloat(r2)
            r7.setFloat(r14, r2)
            long r2 = r12 + r21
            float r2 = r1.getFloat(r2)
            r7.setFloat(r10, r2)
            long r10 = r10 + r23
            long r12 = r12 + r17
            float r2 = r1.getFloat(r12)
            r7.setFloat(r10, r2)
            long r8 = r8 + r23
            r3 = r19
            goto L_0x03ef
        L_0x042b:
            r19 = r3
            org.jtransforms.fft.FloatFFT_1D r2 = r0.fftSlices
            r8 = r38
            r3 = 0
            r2.complexInverse((pl.edu.icm.jlargearrays.FloatLargeArray) r7, (long) r3, (boolean) r8)
            org.jtransforms.fft.FloatFFT_1D r2 = r0.fftSlices
            long r3 = r0.slicesl
            long r3 = r3 * r21
            r2.complexInverse((pl.edu.icm.jlargearrays.FloatLargeArray) r7, (long) r3, (boolean) r8)
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
            float r2 = r7.getFloat(r13)
            r1.setFloat(r11, r2)
            r15 = r5
            long r5 = r11 + r23
            long r13 = r13 + r23
            float r2 = r7.getFloat(r13)
            r1.setFloat(r5, r2)
            long r5 = r11 + r21
            float r2 = r7.getFloat(r9)
            r1.setFloat(r5, r2)
            long r11 = r11 + r17
            long r9 = r9 + r23
            float r2 = r7.getFloat(r9)
            r1.setFloat(r11, r2)
            long r3 = r3 + r23
            r5 = r15
            goto L_0x0441
        L_0x047b:
            long r3 = r19 + r23
            goto L_0x03e4
        L_0x047f:
            r8 = r38
            int r2 = (r3 > r21 ? 1 : (r3 == r21 ? 0 : -1))
            if (r2 != 0) goto L_0x04e1
            r3 = 0
        L_0x0487:
            long r5 = r0.rowsl
            int r2 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r2 >= 0) goto L_0x04e1
            long r5 = r0.rowStridel
            long r5 = r5 * r3
            r9 = 0
        L_0x0492:
            long r11 = r0.slicesl
            int r2 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r2 >= 0) goto L_0x04b3
            long r11 = r0.sliceStridel
            long r11 = r11 * r9
            long r11 = r11 + r5
            long r13 = r9 * r21
            float r2 = r1.getFloat(r11)
            r7.setFloat(r13, r2)
            long r13 = r13 + r23
            long r11 = r11 + r23
            float r2 = r1.getFloat(r11)
            r7.setFloat(r13, r2)
            long r9 = r9 + r23
            goto L_0x0492
        L_0x04b3:
            org.jtransforms.fft.FloatFFT_1D r2 = r0.fftSlices
            r9 = 0
            r2.complexInverse((pl.edu.icm.jlargearrays.FloatLargeArray) r7, (long) r9, (boolean) r8)
            r11 = r9
        L_0x04bb:
            long r13 = r0.slicesl
            int r2 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r2 >= 0) goto L_0x04de
            long r13 = r0.sliceStridel
            long r13 = r13 * r11
            long r13 = r13 + r5
            long r9 = r11 * r21
            float r2 = r7.getFloat(r9)
            r1.setFloat(r13, r2)
            long r13 = r13 + r23
            long r9 = r9 + r23
            float r2 = r7.getFloat(r9)
            r1.setFloat(r13, r2)
            long r11 = r11 + r23
            r9 = 0
            goto L_0x04bb
        L_0x04de:
            long r3 = r3 + r23
            goto L_0x0487
        L_0x04e1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.FloatFFT_3D.cdft3db_sub(int, pl.edu.icm.jlargearrays.FloatLargeArray, boolean):void");
    }

    private void cdft3db_sub(int i, float[][][] fArr, boolean z) {
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
        float[] fArr2 = new float[i4];
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
                            float[] fArr3 = fArr[i8][i6];
                            fArr2[i10] = fArr3[i7];
                            fArr2[i10 + 1] = fArr3[i7 + 1];
                            fArr2[i11] = fArr3[i7 + 2];
                            fArr2[i11 + 1] = fArr3[i7 + 3];
                            fArr2[i12] = fArr3[i7 + 4];
                            fArr2[i12 + 1] = fArr3[i7 + 5];
                            fArr2[i13] = fArr3[i7 + 6];
                            fArr2[i13 + 1] = fArr3[i7 + 7];
                            i8++;
                        }
                        this.fftSlices.complexForward(fArr2, 0);
                        this.fftSlices.complexForward(fArr2, this.slices * 2);
                        this.fftSlices.complexForward(fArr2, this.slices * 4);
                        this.fftSlices.complexForward(fArr2, this.slices * 6);
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
                            float[] fArr4 = fArr[i14][i6];
                            fArr4[i7] = fArr2[i16];
                            fArr4[i7 + 1] = fArr2[i16 + 1];
                            fArr4[i7 + 2] = fArr2[i17];
                            fArr4[i7 + 3] = fArr2[i17 + 1];
                            fArr4[i7 + 4] = fArr2[i18];
                            fArr4[i7 + 5] = fArr2[i18 + 1];
                            fArr4[i7 + 6] = fArr2[i19];
                            fArr4[i7 + 7] = fArr2[i19 + 1];
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
                        float[] fArr5 = fArr[i21][i20];
                        fArr2[i23] = fArr5[0];
                        fArr2[i23 + 1] = fArr5[1];
                        fArr2[i24] = fArr5[2];
                        fArr2[i24 + 1] = fArr5[3];
                        i21++;
                    }
                    this.fftSlices.complexForward(fArr2, 0);
                    this.fftSlices.complexForward(fArr2, this.slices * 2);
                    int i25 = 0;
                    while (true) {
                        int i26 = this.slices;
                        if (i25 >= i26) {
                            break;
                        }
                        int i27 = i25 * 2;
                        int i28 = (i26 * 2) + i27;
                        float[] fArr6 = fArr[i25][i20];
                        fArr6[0] = fArr2[i27];
                        fArr6[1] = fArr2[i27 + 1];
                        fArr6[2] = fArr2[i28];
                        fArr6[3] = fArr2[i28 + 1];
                        i25++;
                    }
                }
            } else if (i5 == 2) {
                for (int i29 = 0; i29 < this.rows; i29++) {
                    for (int i30 = 0; i30 < this.slices; i30++) {
                        int i31 = i30 * 2;
                        float[] fArr7 = fArr[i30][i29];
                        fArr2[i31] = fArr7[0];
                        fArr2[i31 + 1] = fArr7[1];
                    }
                    this.fftSlices.complexForward(fArr2, 0);
                    for (int i32 = 0; i32 < this.slices; i32++) {
                        int i33 = i32 * 2;
                        float[] fArr8 = fArr[i32][i29];
                        fArr8[0] = fArr2[i33];
                        fArr8[1] = fArr2[i33 + 1];
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
                        float[] fArr9 = fArr[i36][i34];
                        fArr2[i38] = fArr9[i35];
                        fArr2[i38 + 1] = fArr9[i35 + 1];
                        fArr2[i39] = fArr9[i35 + 2];
                        fArr2[i39 + 1] = fArr9[i35 + 3];
                        fArr2[i40] = fArr9[i35 + 4];
                        fArr2[i40 + 1] = fArr9[i35 + 5];
                        fArr2[i41] = fArr9[i35 + 6];
                        fArr2[i41 + 1] = fArr9[i35 + 7];
                        i36++;
                    }
                    this.fftSlices.complexInverse(fArr2, 0, z2);
                    this.fftSlices.complexInverse(fArr2, this.slices * 2, z2);
                    this.fftSlices.complexInverse(fArr2, this.slices * 4, z2);
                    this.fftSlices.complexInverse(fArr2, this.slices * 6, z2);
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
                        float[] fArr10 = fArr[i42][i34];
                        fArr10[i35] = fArr2[i44];
                        fArr10[i35 + 1] = fArr2[i44 + 1];
                        fArr10[i35 + 2] = fArr2[i45];
                        fArr10[i35 + 3] = fArr2[i45 + 1];
                        fArr10[i35 + 4] = fArr2[i46];
                        fArr10[i35 + 5] = fArr2[i46 + 1];
                        fArr10[i35 + 6] = fArr2[i47];
                        fArr10[i35 + 7] = fArr2[i47 + 1];
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
                    float[] fArr11 = fArr[i49][i48];
                    fArr2[i51] = fArr11[0];
                    fArr2[i51 + 1] = fArr11[1];
                    fArr2[i52] = fArr11[2];
                    fArr2[i52 + 1] = fArr11[3];
                    i49++;
                }
                this.fftSlices.complexInverse(fArr2, 0, z2);
                this.fftSlices.complexInverse(fArr2, this.slices * 2, z2);
                int i53 = 0;
                while (true) {
                    int i54 = this.slices;
                    if (i53 >= i54) {
                        break;
                    }
                    int i55 = i53 * 2;
                    int i56 = (i54 * 2) + i55;
                    float[] fArr12 = fArr[i53][i48];
                    fArr12[0] = fArr2[i55];
                    fArr12[1] = fArr2[i55 + 1];
                    fArr12[2] = fArr2[i56];
                    fArr12[3] = fArr2[i56 + 1];
                    i53++;
                }
            }
        } else if (i5 == 2) {
            for (int i57 = 0; i57 < this.rows; i57++) {
                for (int i58 = 0; i58 < this.slices; i58++) {
                    int i59 = i58 * 2;
                    float[] fArr13 = fArr[i58][i57];
                    fArr2[i59] = fArr13[0];
                    fArr2[i59 + 1] = fArr13[1];
                }
                this.fftSlices.complexInverse(fArr2, 0, z2);
                for (int i60 = 0; i60 < this.slices; i60++) {
                    int i61 = i60 * 2;
                    float[] fArr14 = fArr[i60][i57];
                    fArr14[0] = fArr2[i61];
                    fArr14[1] = fArr2[i61 + 1];
                }
            }
        }
    }

    private void xdft3da_subth1(int i, int i2, float[] fArr, boolean z) {
        Class<FloatFFT_3D> cls = FloatFFT_3D.class;
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
            final float[] fArr2 = fArr;
            final boolean z2 = z;
            futureArr[i8] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    float[] fArr = new float[i9];
                    if (i10 == -1) {
                        int i = i11;
                        while (i < FloatFFT_3D.this.slices) {
                            int access$000 = FloatFFT_3D.this.sliceStride * i;
                            if (i13 == 0) {
                                for (int i2 = 0; i2 < FloatFFT_3D.this.rows; i2++) {
                                    FloatFFT_3D.this.fftColumns.complexForward(fArr2, (FloatFFT_3D.this.rowStride * i2) + access$000);
                                }
                            } else {
                                for (int i3 = 0; i3 < FloatFFT_3D.this.rows; i3++) {
                                    FloatFFT_3D.this.fftColumns.realForward(fArr2, (FloatFFT_3D.this.rowStride * i3) + access$000);
                                }
                            }
                            if (FloatFFT_3D.this.columns > 4) {
                                for (int i4 = 0; i4 < FloatFFT_3D.this.columns; i4 += 8) {
                                    for (int i5 = 0; i5 < FloatFFT_3D.this.rows; i5++) {
                                        int access$200 = (FloatFFT_3D.this.rowStride * i5) + access$000 + i4;
                                        int i6 = i5 * 2;
                                        int access$100 = (FloatFFT_3D.this.rows * 2) + i6;
                                        int access$1002 = (FloatFFT_3D.this.rows * 2) + access$100;
                                        int access$1003 = (FloatFFT_3D.this.rows * 2) + access$1002;
                                        float[] fArr2 = fArr2;
                                        fArr[i6] = fArr2[access$200];
                                        fArr[i6 + 1] = fArr2[access$200 + 1];
                                        fArr[access$100] = fArr2[access$200 + 2];
                                        fArr[access$100 + 1] = fArr2[access$200 + 3];
                                        fArr[access$1002] = fArr2[access$200 + 4];
                                        fArr[access$1002 + 1] = fArr2[access$200 + 5];
                                        fArr[access$1003] = fArr2[access$200 + 6];
                                        fArr[access$1003 + 1] = fArr2[access$200 + 7];
                                    }
                                    FloatFFT_3D.this.fftRows.complexForward(fArr, 0);
                                    FloatFFT_3D.this.fftRows.complexForward(fArr, FloatFFT_3D.this.rows * 2);
                                    FloatFFT_3D.this.fftRows.complexForward(fArr, FloatFFT_3D.this.rows * 4);
                                    FloatFFT_3D.this.fftRows.complexForward(fArr, FloatFFT_3D.this.rows * 6);
                                    for (int i7 = 0; i7 < FloatFFT_3D.this.rows; i7++) {
                                        int access$2002 = (FloatFFT_3D.this.rowStride * i7) + access$000 + i4;
                                        int i8 = i7 * 2;
                                        int access$1004 = (FloatFFT_3D.this.rows * 2) + i8;
                                        int access$1005 = (FloatFFT_3D.this.rows * 2) + access$1004;
                                        int access$1006 = (FloatFFT_3D.this.rows * 2) + access$1005;
                                        float[] fArr3 = fArr2;
                                        fArr3[access$2002] = fArr[i8];
                                        fArr3[access$2002 + 1] = fArr[i8 + 1];
                                        fArr3[access$2002 + 2] = fArr[access$1004];
                                        fArr3[access$2002 + 3] = fArr[access$1004 + 1];
                                        fArr3[access$2002 + 4] = fArr[access$1005];
                                        fArr3[access$2002 + 5] = fArr[access$1005 + 1];
                                        fArr3[access$2002 + 6] = fArr[access$1006];
                                        fArr3[access$2002 + 7] = fArr[access$1006 + 1];
                                    }
                                }
                            } else if (FloatFFT_3D.this.columns == 4) {
                                for (int i9 = 0; i9 < FloatFFT_3D.this.rows; i9++) {
                                    int access$2003 = (FloatFFT_3D.this.rowStride * i9) + access$000;
                                    int i10 = i9 * 2;
                                    int access$1007 = (FloatFFT_3D.this.rows * 2) + i10;
                                    float[] fArr4 = fArr2;
                                    fArr[i10] = fArr4[access$2003];
                                    fArr[i10 + 1] = fArr4[access$2003 + 1];
                                    fArr[access$1007] = fArr4[access$2003 + 2];
                                    fArr[access$1007 + 1] = fArr4[access$2003 + 3];
                                }
                                FloatFFT_3D.this.fftRows.complexForward(fArr, 0);
                                FloatFFT_3D.this.fftRows.complexForward(fArr, FloatFFT_3D.this.rows * 2);
                                for (int i11 = 0; i11 < FloatFFT_3D.this.rows; i11++) {
                                    int access$2004 = (FloatFFT_3D.this.rowStride * i11) + access$000;
                                    int i12 = i11 * 2;
                                    int access$1008 = (FloatFFT_3D.this.rows * 2) + i12;
                                    float[] fArr5 = fArr2;
                                    fArr5[access$2004] = fArr[i12];
                                    fArr5[access$2004 + 1] = fArr[i12 + 1];
                                    fArr5[access$2004 + 2] = fArr[access$1008];
                                    fArr5[access$2004 + 3] = fArr[access$1008 + 1];
                                }
                            } else if (FloatFFT_3D.this.columns == 2) {
                                for (int i13 = 0; i13 < FloatFFT_3D.this.rows; i13++) {
                                    int access$2005 = (FloatFFT_3D.this.rowStride * i13) + access$000;
                                    int i14 = i13 * 2;
                                    float[] fArr6 = fArr2;
                                    fArr[i14] = fArr6[access$2005];
                                    fArr[i14 + 1] = fArr6[access$2005 + 1];
                                }
                                FloatFFT_3D.this.fftRows.complexForward(fArr, 0);
                                for (int i15 = 0; i15 < FloatFFT_3D.this.rows; i15++) {
                                    int access$2006 = (FloatFFT_3D.this.rowStride * i15) + access$000;
                                    int i16 = i15 * 2;
                                    float[] fArr7 = fArr2;
                                    fArr7[access$2006] = fArr[i16];
                                    fArr7[access$2006 + 1] = fArr[i16 + 1];
                                }
                            }
                            i += i12;
                        }
                        return;
                    }
                    int i17 = i11;
                    while (i17 < FloatFFT_3D.this.slices) {
                        int access$0002 = FloatFFT_3D.this.sliceStride * i17;
                        if (i13 == 0) {
                            for (int i18 = 0; i18 < FloatFFT_3D.this.rows; i18++) {
                                FloatFFT_3D.this.fftColumns.complexInverse(fArr2, (FloatFFT_3D.this.rowStride * i18) + access$0002, z2);
                            }
                        }
                        if (FloatFFT_3D.this.columns > 4) {
                            for (int i19 = 0; i19 < FloatFFT_3D.this.columns; i19 += 8) {
                                for (int i20 = 0; i20 < FloatFFT_3D.this.rows; i20++) {
                                    int access$2007 = (FloatFFT_3D.this.rowStride * i20) + access$0002 + i19;
                                    int i21 = i20 * 2;
                                    int access$1009 = (FloatFFT_3D.this.rows * 2) + i21;
                                    int access$10010 = (FloatFFT_3D.this.rows * 2) + access$1009;
                                    int access$10011 = (FloatFFT_3D.this.rows * 2) + access$10010;
                                    float[] fArr8 = fArr2;
                                    fArr[i21] = fArr8[access$2007];
                                    fArr[i21 + 1] = fArr8[access$2007 + 1];
                                    fArr[access$1009] = fArr8[access$2007 + 2];
                                    fArr[access$1009 + 1] = fArr8[access$2007 + 3];
                                    fArr[access$10010] = fArr8[access$2007 + 4];
                                    fArr[access$10010 + 1] = fArr8[access$2007 + 5];
                                    fArr[access$10011] = fArr8[access$2007 + 6];
                                    fArr[access$10011 + 1] = fArr8[access$2007 + 7];
                                }
                                FloatFFT_3D.this.fftRows.complexInverse(fArr, 0, z2);
                                FloatFFT_3D.this.fftRows.complexInverse(fArr, FloatFFT_3D.this.rows * 2, z2);
                                FloatFFT_3D.this.fftRows.complexInverse(fArr, FloatFFT_3D.this.rows * 4, z2);
                                FloatFFT_3D.this.fftRows.complexInverse(fArr, FloatFFT_3D.this.rows * 6, z2);
                                for (int i22 = 0; i22 < FloatFFT_3D.this.rows; i22++) {
                                    int access$2008 = (FloatFFT_3D.this.rowStride * i22) + access$0002 + i19;
                                    int i23 = i22 * 2;
                                    int access$10012 = (FloatFFT_3D.this.rows * 2) + i23;
                                    int access$10013 = (FloatFFT_3D.this.rows * 2) + access$10012;
                                    int access$10014 = (FloatFFT_3D.this.rows * 2) + access$10013;
                                    float[] fArr9 = fArr2;
                                    fArr9[access$2008] = fArr[i23];
                                    fArr9[access$2008 + 1] = fArr[i23 + 1];
                                    fArr9[access$2008 + 2] = fArr[access$10012];
                                    fArr9[access$2008 + 3] = fArr[access$10012 + 1];
                                    fArr9[access$2008 + 4] = fArr[access$10013];
                                    fArr9[access$2008 + 5] = fArr[access$10013 + 1];
                                    fArr9[access$2008 + 6] = fArr[access$10014];
                                    fArr9[access$2008 + 7] = fArr[access$10014 + 1];
                                }
                            }
                        } else if (FloatFFT_3D.this.columns == 4) {
                            for (int i24 = 0; i24 < FloatFFT_3D.this.rows; i24++) {
                                int access$2009 = (FloatFFT_3D.this.rowStride * i24) + access$0002;
                                int i25 = i24 * 2;
                                int access$10015 = (FloatFFT_3D.this.rows * 2) + i25;
                                float[] fArr10 = fArr2;
                                fArr[i25] = fArr10[access$2009];
                                fArr[i25 + 1] = fArr10[access$2009 + 1];
                                fArr[access$10015] = fArr10[access$2009 + 2];
                                fArr[access$10015 + 1] = fArr10[access$2009 + 3];
                            }
                            FloatFFT_3D.this.fftRows.complexInverse(fArr, 0, z2);
                            FloatFFT_3D.this.fftRows.complexInverse(fArr, FloatFFT_3D.this.rows * 2, z2);
                            for (int i26 = 0; i26 < FloatFFT_3D.this.rows; i26++) {
                                int access$20010 = (FloatFFT_3D.this.rowStride * i26) + access$0002;
                                int i27 = i26 * 2;
                                int access$10016 = (FloatFFT_3D.this.rows * 2) + i27;
                                float[] fArr11 = fArr2;
                                fArr11[access$20010] = fArr[i27];
                                fArr11[access$20010 + 1] = fArr[i27 + 1];
                                fArr11[access$20010 + 2] = fArr[access$10016];
                                fArr11[access$20010 + 3] = fArr[access$10016 + 1];
                            }
                        } else if (FloatFFT_3D.this.columns == 2) {
                            for (int i28 = 0; i28 < FloatFFT_3D.this.rows; i28++) {
                                int access$20011 = (FloatFFT_3D.this.rowStride * i28) + access$0002;
                                int i29 = i28 * 2;
                                float[] fArr12 = fArr2;
                                fArr[i29] = fArr12[access$20011];
                                fArr[i29 + 1] = fArr12[access$20011 + 1];
                            }
                            FloatFFT_3D.this.fftRows.complexInverse(fArr, 0, z2);
                            for (int i30 = 0; i30 < FloatFFT_3D.this.rows; i30++) {
                                int access$20012 = (FloatFFT_3D.this.rowStride * i30) + access$0002;
                                int i31 = i30 * 2;
                                float[] fArr13 = fArr2;
                                fArr13[access$20012] = fArr[i31];
                                fArr13[access$20012 + 1] = fArr[i31 + 1];
                            }
                        }
                        if (i13 != 0) {
                            for (int i32 = 0; i32 < FloatFFT_3D.this.rows; i32++) {
                                FloatFFT_3D.this.fftColumns.realInverse(fArr2, (FloatFFT_3D.this.rowStride * i32) + access$0002, z2);
                            }
                        }
                        i17 += i12;
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
    private void xdft3da_subth1(long r21, int r23, pl.edu.icm.jlargearrays.FloatLargeArray r24, boolean r25) {
        /*
            r20 = this;
            r13 = r20
            java.lang.Class<org.jtransforms.fft.FloatFFT_3D> r14 = org.jtransforms.fft.FloatFFT_3D.class
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
            org.jtransforms.fft.FloatFFT_3D$52 r17 = new org.jtransforms.fft.FloatFFT_3D$52
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
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.FloatFFT_3D.xdft3da_subth1(long, int, pl.edu.icm.jlargearrays.FloatLargeArray, boolean):void");
    }

    private void xdft3da_subth2(int i, int i2, float[] fArr, boolean z) {
        Class<FloatFFT_3D> cls = FloatFFT_3D.class;
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
            final float[] fArr2 = fArr;
            final boolean z2 = z;
            futureArr[i8] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    float[] fArr = new float[i9];
                    if (i10 == -1) {
                        int i = i11;
                        while (i < FloatFFT_3D.this.slices) {
                            int access$000 = FloatFFT_3D.this.sliceStride * i;
                            if (i13 == 0) {
                                for (int i2 = 0; i2 < FloatFFT_3D.this.rows; i2++) {
                                    FloatFFT_3D.this.fftColumns.complexForward(fArr2, (FloatFFT_3D.this.rowStride * i2) + access$000);
                                }
                            } else {
                                for (int i3 = 0; i3 < FloatFFT_3D.this.rows; i3++) {
                                    FloatFFT_3D.this.fftColumns.realForward(fArr2, (FloatFFT_3D.this.rowStride * i3) + access$000);
                                }
                            }
                            if (FloatFFT_3D.this.columns > 4) {
                                for (int i4 = 0; i4 < FloatFFT_3D.this.columns; i4 += 8) {
                                    for (int i5 = 0; i5 < FloatFFT_3D.this.rows; i5++) {
                                        int access$200 = (FloatFFT_3D.this.rowStride * i5) + access$000 + i4;
                                        int i6 = i5 * 2;
                                        int access$100 = (FloatFFT_3D.this.rows * 2) + i6;
                                        int access$1002 = (FloatFFT_3D.this.rows * 2) + access$100;
                                        int access$1003 = (FloatFFT_3D.this.rows * 2) + access$1002;
                                        float[] fArr2 = fArr2;
                                        fArr[i6] = fArr2[access$200];
                                        fArr[i6 + 1] = fArr2[access$200 + 1];
                                        fArr[access$100] = fArr2[access$200 + 2];
                                        fArr[access$100 + 1] = fArr2[access$200 + 3];
                                        fArr[access$1002] = fArr2[access$200 + 4];
                                        fArr[access$1002 + 1] = fArr2[access$200 + 5];
                                        fArr[access$1003] = fArr2[access$200 + 6];
                                        fArr[access$1003 + 1] = fArr2[access$200 + 7];
                                    }
                                    FloatFFT_3D.this.fftRows.complexForward(fArr, 0);
                                    FloatFFT_3D.this.fftRows.complexForward(fArr, FloatFFT_3D.this.rows * 2);
                                    FloatFFT_3D.this.fftRows.complexForward(fArr, FloatFFT_3D.this.rows * 4);
                                    FloatFFT_3D.this.fftRows.complexForward(fArr, FloatFFT_3D.this.rows * 6);
                                    for (int i7 = 0; i7 < FloatFFT_3D.this.rows; i7++) {
                                        int access$2002 = (FloatFFT_3D.this.rowStride * i7) + access$000 + i4;
                                        int i8 = i7 * 2;
                                        int access$1004 = (FloatFFT_3D.this.rows * 2) + i8;
                                        int access$1005 = (FloatFFT_3D.this.rows * 2) + access$1004;
                                        int access$1006 = (FloatFFT_3D.this.rows * 2) + access$1005;
                                        float[] fArr3 = fArr2;
                                        fArr3[access$2002] = fArr[i8];
                                        fArr3[access$2002 + 1] = fArr[i8 + 1];
                                        fArr3[access$2002 + 2] = fArr[access$1004];
                                        fArr3[access$2002 + 3] = fArr[access$1004 + 1];
                                        fArr3[access$2002 + 4] = fArr[access$1005];
                                        fArr3[access$2002 + 5] = fArr[access$1005 + 1];
                                        fArr3[access$2002 + 6] = fArr[access$1006];
                                        fArr3[access$2002 + 7] = fArr[access$1006 + 1];
                                    }
                                }
                            } else if (FloatFFT_3D.this.columns == 4) {
                                for (int i9 = 0; i9 < FloatFFT_3D.this.rows; i9++) {
                                    int access$2003 = (FloatFFT_3D.this.rowStride * i9) + access$000;
                                    int i10 = i9 * 2;
                                    int access$1007 = (FloatFFT_3D.this.rows * 2) + i10;
                                    float[] fArr4 = fArr2;
                                    fArr[i10] = fArr4[access$2003];
                                    fArr[i10 + 1] = fArr4[access$2003 + 1];
                                    fArr[access$1007] = fArr4[access$2003 + 2];
                                    fArr[access$1007 + 1] = fArr4[access$2003 + 3];
                                }
                                FloatFFT_3D.this.fftRows.complexForward(fArr, 0);
                                FloatFFT_3D.this.fftRows.complexForward(fArr, FloatFFT_3D.this.rows * 2);
                                for (int i11 = 0; i11 < FloatFFT_3D.this.rows; i11++) {
                                    int access$2004 = (FloatFFT_3D.this.rowStride * i11) + access$000;
                                    int i12 = i11 * 2;
                                    int access$1008 = (FloatFFT_3D.this.rows * 2) + i12;
                                    float[] fArr5 = fArr2;
                                    fArr5[access$2004] = fArr[i12];
                                    fArr5[access$2004 + 1] = fArr[i12 + 1];
                                    fArr5[access$2004 + 2] = fArr[access$1008];
                                    fArr5[access$2004 + 3] = fArr[access$1008 + 1];
                                }
                            } else if (FloatFFT_3D.this.columns == 2) {
                                for (int i13 = 0; i13 < FloatFFT_3D.this.rows; i13++) {
                                    int access$2005 = (FloatFFT_3D.this.rowStride * i13) + access$000;
                                    int i14 = i13 * 2;
                                    float[] fArr6 = fArr2;
                                    fArr[i14] = fArr6[access$2005];
                                    fArr[i14 + 1] = fArr6[access$2005 + 1];
                                }
                                FloatFFT_3D.this.fftRows.complexForward(fArr, 0);
                                for (int i15 = 0; i15 < FloatFFT_3D.this.rows; i15++) {
                                    int access$2006 = (FloatFFT_3D.this.rowStride * i15) + access$000;
                                    int i16 = i15 * 2;
                                    float[] fArr7 = fArr2;
                                    fArr7[access$2006] = fArr[i16];
                                    fArr7[access$2006 + 1] = fArr[i16 + 1];
                                }
                            }
                            i += i12;
                        }
                        return;
                    }
                    int i17 = i11;
                    while (i17 < FloatFFT_3D.this.slices) {
                        int access$0002 = FloatFFT_3D.this.sliceStride * i17;
                        if (i13 == 0) {
                            for (int i18 = 0; i18 < FloatFFT_3D.this.rows; i18++) {
                                FloatFFT_3D.this.fftColumns.complexInverse(fArr2, (FloatFFT_3D.this.rowStride * i18) + access$0002, z2);
                            }
                        } else {
                            for (int i19 = 0; i19 < FloatFFT_3D.this.rows; i19++) {
                                FloatFFT_3D.this.fftColumns.realInverse2(fArr2, (FloatFFT_3D.this.rowStride * i19) + access$0002, z2);
                            }
                        }
                        if (FloatFFT_3D.this.columns > 4) {
                            for (int i20 = 0; i20 < FloatFFT_3D.this.columns; i20 += 8) {
                                for (int i21 = 0; i21 < FloatFFT_3D.this.rows; i21++) {
                                    int access$2007 = (FloatFFT_3D.this.rowStride * i21) + access$0002 + i20;
                                    int i22 = i21 * 2;
                                    int access$1009 = (FloatFFT_3D.this.rows * 2) + i22;
                                    int access$10010 = (FloatFFT_3D.this.rows * 2) + access$1009;
                                    int access$10011 = (FloatFFT_3D.this.rows * 2) + access$10010;
                                    float[] fArr8 = fArr2;
                                    fArr[i22] = fArr8[access$2007];
                                    fArr[i22 + 1] = fArr8[access$2007 + 1];
                                    fArr[access$1009] = fArr8[access$2007 + 2];
                                    fArr[access$1009 + 1] = fArr8[access$2007 + 3];
                                    fArr[access$10010] = fArr8[access$2007 + 4];
                                    fArr[access$10010 + 1] = fArr8[access$2007 + 5];
                                    fArr[access$10011] = fArr8[access$2007 + 6];
                                    fArr[access$10011 + 1] = fArr8[access$2007 + 7];
                                }
                                FloatFFT_3D.this.fftRows.complexInverse(fArr, 0, z2);
                                FloatFFT_3D.this.fftRows.complexInverse(fArr, FloatFFT_3D.this.rows * 2, z2);
                                FloatFFT_3D.this.fftRows.complexInverse(fArr, FloatFFT_3D.this.rows * 4, z2);
                                FloatFFT_3D.this.fftRows.complexInverse(fArr, FloatFFT_3D.this.rows * 6, z2);
                                for (int i23 = 0; i23 < FloatFFT_3D.this.rows; i23++) {
                                    int access$2008 = (FloatFFT_3D.this.rowStride * i23) + access$0002 + i20;
                                    int i24 = i23 * 2;
                                    int access$10012 = (FloatFFT_3D.this.rows * 2) + i24;
                                    int access$10013 = (FloatFFT_3D.this.rows * 2) + access$10012;
                                    int access$10014 = (FloatFFT_3D.this.rows * 2) + access$10013;
                                    float[] fArr9 = fArr2;
                                    fArr9[access$2008] = fArr[i24];
                                    fArr9[access$2008 + 1] = fArr[i24 + 1];
                                    fArr9[access$2008 + 2] = fArr[access$10012];
                                    fArr9[access$2008 + 3] = fArr[access$10012 + 1];
                                    fArr9[access$2008 + 4] = fArr[access$10013];
                                    fArr9[access$2008 + 5] = fArr[access$10013 + 1];
                                    fArr9[access$2008 + 6] = fArr[access$10014];
                                    fArr9[access$2008 + 7] = fArr[access$10014 + 1];
                                }
                            }
                        } else if (FloatFFT_3D.this.columns == 4) {
                            for (int i25 = 0; i25 < FloatFFT_3D.this.rows; i25++) {
                                int access$2009 = (FloatFFT_3D.this.rowStride * i25) + access$0002;
                                int i26 = i25 * 2;
                                int access$10015 = (FloatFFT_3D.this.rows * 2) + i26;
                                float[] fArr10 = fArr2;
                                fArr[i26] = fArr10[access$2009];
                                fArr[i26 + 1] = fArr10[access$2009 + 1];
                                fArr[access$10015] = fArr10[access$2009 + 2];
                                fArr[access$10015 + 1] = fArr10[access$2009 + 3];
                            }
                            FloatFFT_3D.this.fftRows.complexInverse(fArr, 0, z2);
                            FloatFFT_3D.this.fftRows.complexInverse(fArr, FloatFFT_3D.this.rows * 2, z2);
                            for (int i27 = 0; i27 < FloatFFT_3D.this.rows; i27++) {
                                int access$20010 = (FloatFFT_3D.this.rowStride * i27) + access$0002;
                                int i28 = i27 * 2;
                                int access$10016 = (FloatFFT_3D.this.rows * 2) + i28;
                                float[] fArr11 = fArr2;
                                fArr11[access$20010] = fArr[i28];
                                fArr11[access$20010 + 1] = fArr[i28 + 1];
                                fArr11[access$20010 + 2] = fArr[access$10016];
                                fArr11[access$20010 + 3] = fArr[access$10016 + 1];
                            }
                        } else if (FloatFFT_3D.this.columns == 2) {
                            for (int i29 = 0; i29 < FloatFFT_3D.this.rows; i29++) {
                                int access$20011 = (FloatFFT_3D.this.rowStride * i29) + access$0002;
                                int i30 = i29 * 2;
                                float[] fArr12 = fArr2;
                                fArr[i30] = fArr12[access$20011];
                                fArr[i30 + 1] = fArr12[access$20011 + 1];
                            }
                            FloatFFT_3D.this.fftRows.complexInverse(fArr, 0, z2);
                            for (int i31 = 0; i31 < FloatFFT_3D.this.rows; i31++) {
                                int access$20012 = (FloatFFT_3D.this.rowStride * i31) + access$0002;
                                int i32 = i31 * 2;
                                float[] fArr13 = fArr2;
                                fArr13[access$20012] = fArr[i32];
                                fArr13[access$20012 + 1] = fArr[i32 + 1];
                            }
                        }
                        i17 += i12;
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
    private void xdft3da_subth2(long r21, int r23, pl.edu.icm.jlargearrays.FloatLargeArray r24, boolean r25) {
        /*
            r20 = this;
            r13 = r20
            java.lang.Class<org.jtransforms.fft.FloatFFT_3D> r14 = org.jtransforms.fft.FloatFFT_3D.class
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
            org.jtransforms.fft.FloatFFT_3D$54 r17 = new org.jtransforms.fft.FloatFFT_3D$54
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
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.FloatFFT_3D.xdft3da_subth2(long, int, pl.edu.icm.jlargearrays.FloatLargeArray, boolean):void");
    }

    private void xdft3da_subth1(int i, int i2, float[][][] fArr, boolean z) {
        Class<FloatFFT_3D> cls = FloatFFT_3D.class;
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
            final float[][][] fArr2 = fArr;
            final boolean z2 = z;
            futureArr[i8] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    float[] fArr = new float[i9];
                    if (i10 == -1) {
                        int i = i11;
                        while (i < FloatFFT_3D.this.slices) {
                            if (i13 == 0) {
                                for (int i2 = 0; i2 < FloatFFT_3D.this.rows; i2++) {
                                    FloatFFT_3D.this.fftColumns.complexForward(fArr2[i][i2]);
                                }
                            } else {
                                for (int i3 = 0; i3 < FloatFFT_3D.this.rows; i3++) {
                                    FloatFFT_3D.this.fftColumns.realForward(fArr2[i][i3], 0);
                                }
                            }
                            if (FloatFFT_3D.this.columns > 4) {
                                for (int i4 = 0; i4 < FloatFFT_3D.this.columns; i4 += 8) {
                                    for (int i5 = 0; i5 < FloatFFT_3D.this.rows; i5++) {
                                        int i6 = i5 * 2;
                                        int access$100 = (FloatFFT_3D.this.rows * 2) + i6;
                                        int access$1002 = (FloatFFT_3D.this.rows * 2) + access$100;
                                        int access$1003 = (FloatFFT_3D.this.rows * 2) + access$1002;
                                        float[] fArr2 = fArr2[i][i5];
                                        fArr[i6] = fArr2[i4];
                                        fArr[i6 + 1] = fArr2[i4 + 1];
                                        fArr[access$100] = fArr2[i4 + 2];
                                        fArr[access$100 + 1] = fArr2[i4 + 3];
                                        fArr[access$1002] = fArr2[i4 + 4];
                                        fArr[access$1002 + 1] = fArr2[i4 + 5];
                                        fArr[access$1003] = fArr2[i4 + 6];
                                        fArr[access$1003 + 1] = fArr2[i4 + 7];
                                    }
                                    FloatFFT_3D.this.fftRows.complexForward(fArr, 0);
                                    FloatFFT_3D.this.fftRows.complexForward(fArr, FloatFFT_3D.this.rows * 2);
                                    FloatFFT_3D.this.fftRows.complexForward(fArr, FloatFFT_3D.this.rows * 4);
                                    FloatFFT_3D.this.fftRows.complexForward(fArr, FloatFFT_3D.this.rows * 6);
                                    for (int i7 = 0; i7 < FloatFFT_3D.this.rows; i7++) {
                                        int i8 = i7 * 2;
                                        int access$1004 = (FloatFFT_3D.this.rows * 2) + i8;
                                        int access$1005 = (FloatFFT_3D.this.rows * 2) + access$1004;
                                        int access$1006 = (FloatFFT_3D.this.rows * 2) + access$1005;
                                        float[] fArr3 = fArr2[i][i7];
                                        fArr3[i4] = fArr[i8];
                                        fArr3[i4 + 1] = fArr[i8 + 1];
                                        fArr3[i4 + 2] = fArr[access$1004];
                                        fArr3[i4 + 3] = fArr[access$1004 + 1];
                                        fArr3[i4 + 4] = fArr[access$1005];
                                        fArr3[i4 + 5] = fArr[access$1005 + 1];
                                        fArr3[i4 + 6] = fArr[access$1006];
                                        fArr3[i4 + 7] = fArr[access$1006 + 1];
                                    }
                                }
                            } else if (FloatFFT_3D.this.columns == 4) {
                                for (int i9 = 0; i9 < FloatFFT_3D.this.rows; i9++) {
                                    int i10 = i9 * 2;
                                    int access$1007 = (FloatFFT_3D.this.rows * 2) + i10;
                                    float[] fArr4 = fArr2[i][i9];
                                    fArr[i10] = fArr4[0];
                                    fArr[i10 + 1] = fArr4[1];
                                    fArr[access$1007] = fArr4[2];
                                    fArr[access$1007 + 1] = fArr4[3];
                                }
                                FloatFFT_3D.this.fftRows.complexForward(fArr, 0);
                                FloatFFT_3D.this.fftRows.complexForward(fArr, FloatFFT_3D.this.rows * 2);
                                for (int i11 = 0; i11 < FloatFFT_3D.this.rows; i11++) {
                                    int i12 = i11 * 2;
                                    int access$1008 = (FloatFFT_3D.this.rows * 2) + i12;
                                    float[] fArr5 = fArr2[i][i11];
                                    fArr5[0] = fArr[i12];
                                    fArr5[1] = fArr[i12 + 1];
                                    fArr5[2] = fArr[access$1008];
                                    fArr5[3] = fArr[access$1008 + 1];
                                }
                            } else if (FloatFFT_3D.this.columns == 2) {
                                for (int i13 = 0; i13 < FloatFFT_3D.this.rows; i13++) {
                                    int i14 = i13 * 2;
                                    float[] fArr6 = fArr2[i][i13];
                                    fArr[i14] = fArr6[0];
                                    fArr[i14 + 1] = fArr6[1];
                                }
                                FloatFFT_3D.this.fftRows.complexForward(fArr, 0);
                                for (int i15 = 0; i15 < FloatFFT_3D.this.rows; i15++) {
                                    int i16 = i15 * 2;
                                    float[] fArr7 = fArr2[i][i15];
                                    fArr7[0] = fArr[i16];
                                    fArr7[1] = fArr[i16 + 1];
                                }
                            }
                            i += i12;
                        }
                        return;
                    }
                    int i17 = i11;
                    while (i17 < FloatFFT_3D.this.slices) {
                        if (i13 == 0) {
                            for (int i18 = 0; i18 < FloatFFT_3D.this.rows; i18++) {
                                FloatFFT_3D.this.fftColumns.complexInverse(fArr2[i17][i18], z2);
                            }
                        }
                        if (FloatFFT_3D.this.columns > 4) {
                            for (int i19 = 0; i19 < FloatFFT_3D.this.columns; i19 += 8) {
                                for (int i20 = 0; i20 < FloatFFT_3D.this.rows; i20++) {
                                    int i21 = i20 * 2;
                                    int access$1009 = (FloatFFT_3D.this.rows * 2) + i21;
                                    int access$10010 = (FloatFFT_3D.this.rows * 2) + access$1009;
                                    int access$10011 = (FloatFFT_3D.this.rows * 2) + access$10010;
                                    float[] fArr8 = fArr2[i17][i20];
                                    fArr[i21] = fArr8[i19];
                                    fArr[i21 + 1] = fArr8[i19 + 1];
                                    fArr[access$1009] = fArr8[i19 + 2];
                                    fArr[access$1009 + 1] = fArr8[i19 + 3];
                                    fArr[access$10010] = fArr8[i19 + 4];
                                    fArr[access$10010 + 1] = fArr8[i19 + 5];
                                    fArr[access$10011] = fArr8[i19 + 6];
                                    fArr[access$10011 + 1] = fArr8[i19 + 7];
                                }
                                FloatFFT_3D.this.fftRows.complexInverse(fArr, 0, z2);
                                FloatFFT_3D.this.fftRows.complexInverse(fArr, FloatFFT_3D.this.rows * 2, z2);
                                FloatFFT_3D.this.fftRows.complexInverse(fArr, FloatFFT_3D.this.rows * 4, z2);
                                FloatFFT_3D.this.fftRows.complexInverse(fArr, FloatFFT_3D.this.rows * 6, z2);
                                for (int i22 = 0; i22 < FloatFFT_3D.this.rows; i22++) {
                                    int i23 = i22 * 2;
                                    int access$10012 = (FloatFFT_3D.this.rows * 2) + i23;
                                    int access$10013 = (FloatFFT_3D.this.rows * 2) + access$10012;
                                    int access$10014 = (FloatFFT_3D.this.rows * 2) + access$10013;
                                    float[] fArr9 = fArr2[i17][i22];
                                    fArr9[i19] = fArr[i23];
                                    fArr9[i19 + 1] = fArr[i23 + 1];
                                    fArr9[i19 + 2] = fArr[access$10012];
                                    fArr9[i19 + 3] = fArr[access$10012 + 1];
                                    fArr9[i19 + 4] = fArr[access$10013];
                                    fArr9[i19 + 5] = fArr[access$10013 + 1];
                                    fArr9[i19 + 6] = fArr[access$10014];
                                    fArr9[i19 + 7] = fArr[access$10014 + 1];
                                }
                            }
                        } else if (FloatFFT_3D.this.columns == 4) {
                            for (int i24 = 0; i24 < FloatFFT_3D.this.rows; i24++) {
                                int i25 = i24 * 2;
                                int access$10015 = (FloatFFT_3D.this.rows * 2) + i25;
                                float[] fArr10 = fArr2[i17][i24];
                                fArr[i25] = fArr10[0];
                                fArr[i25 + 1] = fArr10[1];
                                fArr[access$10015] = fArr10[2];
                                fArr[access$10015 + 1] = fArr10[3];
                            }
                            FloatFFT_3D.this.fftRows.complexInverse(fArr, 0, z2);
                            FloatFFT_3D.this.fftRows.complexInverse(fArr, FloatFFT_3D.this.rows * 2, z2);
                            for (int i26 = 0; i26 < FloatFFT_3D.this.rows; i26++) {
                                int i27 = i26 * 2;
                                int access$10016 = (FloatFFT_3D.this.rows * 2) + i27;
                                float[] fArr11 = fArr2[i17][i26];
                                fArr11[0] = fArr[i27];
                                fArr11[1] = fArr[i27 + 1];
                                fArr11[2] = fArr[access$10016];
                                fArr11[3] = fArr[access$10016 + 1];
                            }
                        } else if (FloatFFT_3D.this.columns == 2) {
                            for (int i28 = 0; i28 < FloatFFT_3D.this.rows; i28++) {
                                int i29 = i28 * 2;
                                float[] fArr12 = fArr2[i17][i28];
                                fArr[i29] = fArr12[0];
                                fArr[i29 + 1] = fArr12[1];
                            }
                            FloatFFT_3D.this.fftRows.complexInverse(fArr, 0, z2);
                            for (int i30 = 0; i30 < FloatFFT_3D.this.rows; i30++) {
                                int i31 = i30 * 2;
                                float[] fArr13 = fArr2[i17][i30];
                                fArr13[0] = fArr[i31];
                                fArr13[1] = fArr[i31 + 1];
                            }
                        }
                        if (i13 != 0) {
                            for (int i32 = 0; i32 < FloatFFT_3D.this.rows; i32++) {
                                FloatFFT_3D.this.fftColumns.realInverse(fArr2[i17][i32], z2);
                            }
                        }
                        i17 += i12;
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

    private void xdft3da_subth2(int i, int i2, float[][][] fArr, boolean z) {
        Class<FloatFFT_3D> cls = FloatFFT_3D.class;
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
            final float[][][] fArr2 = fArr;
            final boolean z2 = z;
            futureArr[i8] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    float[] fArr = new float[i9];
                    if (i10 == -1) {
                        int i = i11;
                        while (i < FloatFFT_3D.this.slices) {
                            if (i13 == 0) {
                                for (int i2 = 0; i2 < FloatFFT_3D.this.rows; i2++) {
                                    FloatFFT_3D.this.fftColumns.complexForward(fArr2[i][i2]);
                                }
                            } else {
                                for (int i3 = 0; i3 < FloatFFT_3D.this.rows; i3++) {
                                    FloatFFT_3D.this.fftColumns.realForward(fArr2[i][i3]);
                                }
                            }
                            if (FloatFFT_3D.this.columns > 4) {
                                for (int i4 = 0; i4 < FloatFFT_3D.this.columns; i4 += 8) {
                                    for (int i5 = 0; i5 < FloatFFT_3D.this.rows; i5++) {
                                        int i6 = i5 * 2;
                                        int access$100 = (FloatFFT_3D.this.rows * 2) + i6;
                                        int access$1002 = (FloatFFT_3D.this.rows * 2) + access$100;
                                        int access$1003 = (FloatFFT_3D.this.rows * 2) + access$1002;
                                        float[] fArr2 = fArr2[i][i5];
                                        fArr[i6] = fArr2[i4];
                                        fArr[i6 + 1] = fArr2[i4 + 1];
                                        fArr[access$100] = fArr2[i4 + 2];
                                        fArr[access$100 + 1] = fArr2[i4 + 3];
                                        fArr[access$1002] = fArr2[i4 + 4];
                                        fArr[access$1002 + 1] = fArr2[i4 + 5];
                                        fArr[access$1003] = fArr2[i4 + 6];
                                        fArr[access$1003 + 1] = fArr2[i4 + 7];
                                    }
                                    FloatFFT_3D.this.fftRows.complexForward(fArr, 0);
                                    FloatFFT_3D.this.fftRows.complexForward(fArr, FloatFFT_3D.this.rows * 2);
                                    FloatFFT_3D.this.fftRows.complexForward(fArr, FloatFFT_3D.this.rows * 4);
                                    FloatFFT_3D.this.fftRows.complexForward(fArr, FloatFFT_3D.this.rows * 6);
                                    for (int i7 = 0; i7 < FloatFFT_3D.this.rows; i7++) {
                                        int i8 = i7 * 2;
                                        int access$1004 = (FloatFFT_3D.this.rows * 2) + i8;
                                        int access$1005 = (FloatFFT_3D.this.rows * 2) + access$1004;
                                        int access$1006 = (FloatFFT_3D.this.rows * 2) + access$1005;
                                        float[] fArr3 = fArr2[i][i7];
                                        fArr3[i4] = fArr[i8];
                                        fArr3[i4 + 1] = fArr[i8 + 1];
                                        fArr3[i4 + 2] = fArr[access$1004];
                                        fArr3[i4 + 3] = fArr[access$1004 + 1];
                                        fArr3[i4 + 4] = fArr[access$1005];
                                        fArr3[i4 + 5] = fArr[access$1005 + 1];
                                        fArr3[i4 + 6] = fArr[access$1006];
                                        fArr3[i4 + 7] = fArr[access$1006 + 1];
                                    }
                                }
                            } else if (FloatFFT_3D.this.columns == 4) {
                                for (int i9 = 0; i9 < FloatFFT_3D.this.rows; i9++) {
                                    int i10 = i9 * 2;
                                    int access$1007 = (FloatFFT_3D.this.rows * 2) + i10;
                                    float[] fArr4 = fArr2[i][i9];
                                    fArr[i10] = fArr4[0];
                                    fArr[i10 + 1] = fArr4[1];
                                    fArr[access$1007] = fArr4[2];
                                    fArr[access$1007 + 1] = fArr4[3];
                                }
                                FloatFFT_3D.this.fftRows.complexForward(fArr, 0);
                                FloatFFT_3D.this.fftRows.complexForward(fArr, FloatFFT_3D.this.rows * 2);
                                for (int i11 = 0; i11 < FloatFFT_3D.this.rows; i11++) {
                                    int i12 = i11 * 2;
                                    int access$1008 = (FloatFFT_3D.this.rows * 2) + i12;
                                    float[] fArr5 = fArr2[i][i11];
                                    fArr5[0] = fArr[i12];
                                    fArr5[1] = fArr[i12 + 1];
                                    fArr5[2] = fArr[access$1008];
                                    fArr5[3] = fArr[access$1008 + 1];
                                }
                            } else if (FloatFFT_3D.this.columns == 2) {
                                for (int i13 = 0; i13 < FloatFFT_3D.this.rows; i13++) {
                                    int i14 = i13 * 2;
                                    float[] fArr6 = fArr2[i][i13];
                                    fArr[i14] = fArr6[0];
                                    fArr[i14 + 1] = fArr6[1];
                                }
                                FloatFFT_3D.this.fftRows.complexForward(fArr, 0);
                                for (int i15 = 0; i15 < FloatFFT_3D.this.rows; i15++) {
                                    int i16 = i15 * 2;
                                    float[] fArr7 = fArr2[i][i15];
                                    fArr7[0] = fArr[i16];
                                    fArr7[1] = fArr[i16 + 1];
                                }
                            }
                            i += i12;
                        }
                        return;
                    }
                    int i17 = i11;
                    while (i17 < FloatFFT_3D.this.slices) {
                        if (i13 == 0) {
                            for (int i18 = 0; i18 < FloatFFT_3D.this.rows; i18++) {
                                FloatFFT_3D.this.fftColumns.complexInverse(fArr2[i17][i18], z2);
                            }
                        } else {
                            for (int i19 = 0; i19 < FloatFFT_3D.this.rows; i19++) {
                                FloatFFT_3D.this.fftColumns.realInverse2(fArr2[i17][i19], 0, z2);
                            }
                        }
                        if (FloatFFT_3D.this.columns > 4) {
                            for (int i20 = 0; i20 < FloatFFT_3D.this.columns; i20 += 8) {
                                for (int i21 = 0; i21 < FloatFFT_3D.this.rows; i21++) {
                                    int i22 = i21 * 2;
                                    int access$1009 = (FloatFFT_3D.this.rows * 2) + i22;
                                    int access$10010 = (FloatFFT_3D.this.rows * 2) + access$1009;
                                    int access$10011 = (FloatFFT_3D.this.rows * 2) + access$10010;
                                    float[] fArr8 = fArr2[i17][i21];
                                    fArr[i22] = fArr8[i20];
                                    fArr[i22 + 1] = fArr8[i20 + 1];
                                    fArr[access$1009] = fArr8[i20 + 2];
                                    fArr[access$1009 + 1] = fArr8[i20 + 3];
                                    fArr[access$10010] = fArr8[i20 + 4];
                                    fArr[access$10010 + 1] = fArr8[i20 + 5];
                                    fArr[access$10011] = fArr8[i20 + 6];
                                    fArr[access$10011 + 1] = fArr8[i20 + 7];
                                }
                                FloatFFT_3D.this.fftRows.complexInverse(fArr, 0, z2);
                                FloatFFT_3D.this.fftRows.complexInverse(fArr, FloatFFT_3D.this.rows * 2, z2);
                                FloatFFT_3D.this.fftRows.complexInverse(fArr, FloatFFT_3D.this.rows * 4, z2);
                                FloatFFT_3D.this.fftRows.complexInverse(fArr, FloatFFT_3D.this.rows * 6, z2);
                                for (int i23 = 0; i23 < FloatFFT_3D.this.rows; i23++) {
                                    int i24 = i23 * 2;
                                    int access$10012 = (FloatFFT_3D.this.rows * 2) + i24;
                                    int access$10013 = (FloatFFT_3D.this.rows * 2) + access$10012;
                                    int access$10014 = (FloatFFT_3D.this.rows * 2) + access$10013;
                                    float[] fArr9 = fArr2[i17][i23];
                                    fArr9[i20] = fArr[i24];
                                    fArr9[i20 + 1] = fArr[i24 + 1];
                                    fArr9[i20 + 2] = fArr[access$10012];
                                    fArr9[i20 + 3] = fArr[access$10012 + 1];
                                    fArr9[i20 + 4] = fArr[access$10013];
                                    fArr9[i20 + 5] = fArr[access$10013 + 1];
                                    fArr9[i20 + 6] = fArr[access$10014];
                                    fArr9[i20 + 7] = fArr[access$10014 + 1];
                                }
                            }
                        } else if (FloatFFT_3D.this.columns == 4) {
                            for (int i25 = 0; i25 < FloatFFT_3D.this.rows; i25++) {
                                int i26 = i25 * 2;
                                int access$10015 = (FloatFFT_3D.this.rows * 2) + i26;
                                float[] fArr10 = fArr2[i17][i25];
                                fArr[i26] = fArr10[0];
                                fArr[i26 + 1] = fArr10[1];
                                fArr[access$10015] = fArr10[2];
                                fArr[access$10015 + 1] = fArr10[3];
                            }
                            FloatFFT_3D.this.fftRows.complexInverse(fArr, 0, z2);
                            FloatFFT_3D.this.fftRows.complexInverse(fArr, FloatFFT_3D.this.rows * 2, z2);
                            for (int i27 = 0; i27 < FloatFFT_3D.this.rows; i27++) {
                                int i28 = i27 * 2;
                                int access$10016 = (FloatFFT_3D.this.rows * 2) + i28;
                                float[] fArr11 = fArr2[i17][i27];
                                fArr11[0] = fArr[i28];
                                fArr11[1] = fArr[i28 + 1];
                                fArr11[2] = fArr[access$10016];
                                fArr11[3] = fArr[access$10016 + 1];
                            }
                        } else if (FloatFFT_3D.this.columns == 2) {
                            for (int i29 = 0; i29 < FloatFFT_3D.this.rows; i29++) {
                                int i30 = i29 * 2;
                                float[] fArr12 = fArr2[i17][i29];
                                fArr[i30] = fArr12[0];
                                fArr[i30 + 1] = fArr12[1];
                            }
                            FloatFFT_3D.this.fftRows.complexInverse(fArr, 0, z2);
                            for (int i31 = 0; i31 < FloatFFT_3D.this.rows; i31++) {
                                int i32 = i31 * 2;
                                float[] fArr13 = fArr2[i17][i31];
                                fArr13[0] = fArr[i32];
                                fArr13[1] = fArr[i32 + 1];
                            }
                        }
                        i17 += i12;
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

    private void cdft3db_subth(int i, float[] fArr, boolean z) {
        Class<FloatFFT_3D> cls = FloatFFT_3D.class;
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
            final float[] fArr2 = fArr;
            final boolean z2 = z;
            futureArr[i7] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    float[] fArr = new float[i8];
                    if (i9 == -1) {
                        if (FloatFFT_3D.this.columns > 4) {
                            int i = i10;
                            while (i < FloatFFT_3D.this.rows) {
                                int access$200 = FloatFFT_3D.this.rowStride * i;
                                for (int i2 = 0; i2 < FloatFFT_3D.this.columns; i2 += 8) {
                                    for (int i3 = 0; i3 < FloatFFT_3D.this.slices; i3++) {
                                        int access$000 = (FloatFFT_3D.this.sliceStride * i3) + access$200 + i2;
                                        int i4 = i3 * 2;
                                        int access$600 = (FloatFFT_3D.this.slices * 2) + i4;
                                        int access$6002 = (FloatFFT_3D.this.slices * 2) + access$600;
                                        int access$6003 = (FloatFFT_3D.this.slices * 2) + access$6002;
                                        float[] fArr2 = fArr2;
                                        fArr[i4] = fArr2[access$000];
                                        fArr[i4 + 1] = fArr2[access$000 + 1];
                                        fArr[access$600] = fArr2[access$000 + 2];
                                        fArr[access$600 + 1] = fArr2[access$000 + 3];
                                        fArr[access$6002] = fArr2[access$000 + 4];
                                        fArr[access$6002 + 1] = fArr2[access$000 + 5];
                                        fArr[access$6003] = fArr2[access$000 + 6];
                                        fArr[access$6003 + 1] = fArr2[access$000 + 7];
                                    }
                                    FloatFFT_3D.this.fftSlices.complexForward(fArr, 0);
                                    FloatFFT_3D.this.fftSlices.complexForward(fArr, FloatFFT_3D.this.slices * 2);
                                    FloatFFT_3D.this.fftSlices.complexForward(fArr, FloatFFT_3D.this.slices * 4);
                                    FloatFFT_3D.this.fftSlices.complexForward(fArr, FloatFFT_3D.this.slices * 6);
                                    for (int i5 = 0; i5 < FloatFFT_3D.this.slices; i5++) {
                                        int access$0002 = (FloatFFT_3D.this.sliceStride * i5) + access$200 + i2;
                                        int i6 = i5 * 2;
                                        int access$6004 = (FloatFFT_3D.this.slices * 2) + i6;
                                        int access$6005 = (FloatFFT_3D.this.slices * 2) + access$6004;
                                        int access$6006 = (FloatFFT_3D.this.slices * 2) + access$6005;
                                        float[] fArr3 = fArr2;
                                        fArr3[access$0002] = fArr[i6];
                                        fArr3[access$0002 + 1] = fArr[i6 + 1];
                                        fArr3[access$0002 + 2] = fArr[access$6004];
                                        fArr3[access$0002 + 3] = fArr[access$6004 + 1];
                                        fArr3[access$0002 + 4] = fArr[access$6005];
                                        fArr3[access$0002 + 5] = fArr[access$6005 + 1];
                                        fArr3[access$0002 + 6] = fArr[access$6006];
                                        fArr3[access$0002 + 7] = fArr[access$6006 + 1];
                                    }
                                }
                                i += i11;
                            }
                        } else if (FloatFFT_3D.this.columns == 4) {
                            int i7 = i10;
                            while (i7 < FloatFFT_3D.this.rows) {
                                int access$2002 = FloatFFT_3D.this.rowStride * i7;
                                for (int i8 = 0; i8 < FloatFFT_3D.this.slices; i8++) {
                                    int access$0003 = (FloatFFT_3D.this.sliceStride * i8) + access$2002;
                                    int i9 = i8 * 2;
                                    int access$6007 = (FloatFFT_3D.this.slices * 2) + i9;
                                    float[] fArr4 = fArr2;
                                    fArr[i9] = fArr4[access$0003];
                                    fArr[i9 + 1] = fArr4[access$0003 + 1];
                                    fArr[access$6007] = fArr4[access$0003 + 2];
                                    fArr[access$6007 + 1] = fArr4[access$0003 + 3];
                                }
                                FloatFFT_3D.this.fftSlices.complexForward(fArr, 0);
                                FloatFFT_3D.this.fftSlices.complexForward(fArr, FloatFFT_3D.this.slices * 2);
                                for (int i10 = 0; i10 < FloatFFT_3D.this.slices; i10++) {
                                    int access$0004 = (FloatFFT_3D.this.sliceStride * i10) + access$2002;
                                    int i11 = i10 * 2;
                                    int access$6008 = (FloatFFT_3D.this.slices * 2) + i11;
                                    float[] fArr5 = fArr2;
                                    fArr5[access$0004] = fArr[i11];
                                    fArr5[access$0004 + 1] = fArr[i11 + 1];
                                    fArr5[access$0004 + 2] = fArr[access$6008];
                                    fArr5[access$0004 + 3] = fArr[access$6008 + 1];
                                }
                                i7 += i11;
                            }
                        } else if (FloatFFT_3D.this.columns == 2) {
                            int i12 = i10;
                            while (i12 < FloatFFT_3D.this.rows) {
                                int access$2003 = FloatFFT_3D.this.rowStride * i12;
                                for (int i13 = 0; i13 < FloatFFT_3D.this.slices; i13++) {
                                    int access$0005 = (FloatFFT_3D.this.sliceStride * i13) + access$2003;
                                    int i14 = i13 * 2;
                                    float[] fArr6 = fArr2;
                                    fArr[i14] = fArr6[access$0005];
                                    fArr[i14 + 1] = fArr6[access$0005 + 1];
                                }
                                FloatFFT_3D.this.fftSlices.complexForward(fArr, 0);
                                for (int i15 = 0; i15 < FloatFFT_3D.this.slices; i15++) {
                                    int access$0006 = (FloatFFT_3D.this.sliceStride * i15) + access$2003;
                                    int i16 = i15 * 2;
                                    float[] fArr7 = fArr2;
                                    fArr7[access$0006] = fArr[i16];
                                    fArr7[access$0006 + 1] = fArr[i16 + 1];
                                }
                                i12 += i11;
                            }
                        }
                    } else if (FloatFFT_3D.this.columns > 4) {
                        int i17 = i10;
                        while (i17 < FloatFFT_3D.this.rows) {
                            int access$2004 = FloatFFT_3D.this.rowStride * i17;
                            for (int i18 = 0; i18 < FloatFFT_3D.this.columns; i18 += 8) {
                                for (int i19 = 0; i19 < FloatFFT_3D.this.slices; i19++) {
                                    int access$0007 = (FloatFFT_3D.this.sliceStride * i19) + access$2004 + i18;
                                    int i20 = i19 * 2;
                                    int access$6009 = (FloatFFT_3D.this.slices * 2) + i20;
                                    int access$60010 = (FloatFFT_3D.this.slices * 2) + access$6009;
                                    int access$60011 = (FloatFFT_3D.this.slices * 2) + access$60010;
                                    float[] fArr8 = fArr2;
                                    fArr[i20] = fArr8[access$0007];
                                    fArr[i20 + 1] = fArr8[access$0007 + 1];
                                    fArr[access$6009] = fArr8[access$0007 + 2];
                                    fArr[access$6009 + 1] = fArr8[access$0007 + 3];
                                    fArr[access$60010] = fArr8[access$0007 + 4];
                                    fArr[access$60010 + 1] = fArr8[access$0007 + 5];
                                    fArr[access$60011] = fArr8[access$0007 + 6];
                                    fArr[access$60011 + 1] = fArr8[access$0007 + 7];
                                }
                                FloatFFT_3D.this.fftSlices.complexInverse(fArr, 0, z2);
                                FloatFFT_3D.this.fftSlices.complexInverse(fArr, FloatFFT_3D.this.slices * 2, z2);
                                FloatFFT_3D.this.fftSlices.complexInverse(fArr, FloatFFT_3D.this.slices * 4, z2);
                                FloatFFT_3D.this.fftSlices.complexInverse(fArr, FloatFFT_3D.this.slices * 6, z2);
                                for (int i21 = 0; i21 < FloatFFT_3D.this.slices; i21++) {
                                    int access$0008 = (FloatFFT_3D.this.sliceStride * i21) + access$2004 + i18;
                                    int i22 = i21 * 2;
                                    int access$60012 = (FloatFFT_3D.this.slices * 2) + i22;
                                    int access$60013 = (FloatFFT_3D.this.slices * 2) + access$60012;
                                    int access$60014 = (FloatFFT_3D.this.slices * 2) + access$60013;
                                    float[] fArr9 = fArr2;
                                    fArr9[access$0008] = fArr[i22];
                                    fArr9[access$0008 + 1] = fArr[i22 + 1];
                                    fArr9[access$0008 + 2] = fArr[access$60012];
                                    fArr9[access$0008 + 3] = fArr[access$60012 + 1];
                                    fArr9[access$0008 + 4] = fArr[access$60013];
                                    fArr9[access$0008 + 5] = fArr[access$60013 + 1];
                                    fArr9[access$0008 + 6] = fArr[access$60014];
                                    fArr9[access$0008 + 7] = fArr[access$60014 + 1];
                                }
                            }
                            i17 += i11;
                        }
                    } else if (FloatFFT_3D.this.columns == 4) {
                        int i23 = i10;
                        while (i23 < FloatFFT_3D.this.rows) {
                            int access$2005 = FloatFFT_3D.this.rowStride * i23;
                            for (int i24 = 0; i24 < FloatFFT_3D.this.slices; i24++) {
                                int access$0009 = (FloatFFT_3D.this.sliceStride * i24) + access$2005;
                                int i25 = i24 * 2;
                                int access$60015 = (FloatFFT_3D.this.slices * 2) + i25;
                                float[] fArr10 = fArr2;
                                fArr[i25] = fArr10[access$0009];
                                fArr[i25 + 1] = fArr10[access$0009 + 1];
                                fArr[access$60015] = fArr10[access$0009 + 2];
                                fArr[access$60015 + 1] = fArr10[access$0009 + 3];
                            }
                            FloatFFT_3D.this.fftSlices.complexInverse(fArr, 0, z2);
                            FloatFFT_3D.this.fftSlices.complexInverse(fArr, FloatFFT_3D.this.slices * 2, z2);
                            for (int i26 = 0; i26 < FloatFFT_3D.this.slices; i26++) {
                                int access$00010 = (FloatFFT_3D.this.sliceStride * i26) + access$2005;
                                int i27 = i26 * 2;
                                int access$60016 = (FloatFFT_3D.this.slices * 2) + i27;
                                float[] fArr11 = fArr2;
                                fArr11[access$00010] = fArr[i27];
                                fArr11[access$00010 + 1] = fArr[i27 + 1];
                                fArr11[access$00010 + 2] = fArr[access$60016];
                                fArr11[access$00010 + 3] = fArr[access$60016 + 1];
                            }
                            i23 += i11;
                        }
                    } else if (FloatFFT_3D.this.columns == 2) {
                        int i28 = i10;
                        while (i28 < FloatFFT_3D.this.rows) {
                            int access$2006 = FloatFFT_3D.this.rowStride * i28;
                            for (int i29 = 0; i29 < FloatFFT_3D.this.slices; i29++) {
                                int access$00011 = (FloatFFT_3D.this.sliceStride * i29) + access$2006;
                                int i30 = i29 * 2;
                                float[] fArr12 = fArr2;
                                fArr[i30] = fArr12[access$00011];
                                fArr[i30 + 1] = fArr12[access$00011 + 1];
                            }
                            FloatFFT_3D.this.fftSlices.complexInverse(fArr, 0, z2);
                            for (int i31 = 0; i31 < FloatFFT_3D.this.slices; i31++) {
                                int access$00012 = (FloatFFT_3D.this.sliceStride * i31) + access$2006;
                                int i32 = i31 * 2;
                                float[] fArr13 = fArr2;
                                fArr13[access$00012] = fArr[i32];
                                fArr13[access$00012 + 1] = fArr[i32 + 1];
                            }
                            i28 += i11;
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
    private void cdft3db_subth(int r19, pl.edu.icm.jlargearrays.FloatLargeArray r20, boolean r21) {
        /*
            r18 = this;
            r11 = r18
            java.lang.Class<org.jtransforms.fft.FloatFFT_3D> r12 = org.jtransforms.fft.FloatFFT_3D.class
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
            org.jtransforms.fft.FloatFFT_3D$58 r16 = new org.jtransforms.fft.FloatFFT_3D$58
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
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.FloatFFT_3D.cdft3db_subth(int, pl.edu.icm.jlargearrays.FloatLargeArray, boolean):void");
    }

    private void cdft3db_subth(int i, float[][][] fArr, boolean z) {
        Class<FloatFFT_3D> cls = FloatFFT_3D.class;
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
            final float[][][] fArr2 = fArr;
            final boolean z2 = z;
            futureArr[i7] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    float[] fArr = new float[i8];
                    if (i9 == -1) {
                        if (FloatFFT_3D.this.columns > 4) {
                            int i = i10;
                            while (i < FloatFFT_3D.this.rows) {
                                for (int i2 = 0; i2 < FloatFFT_3D.this.columns; i2 += 8) {
                                    for (int i3 = 0; i3 < FloatFFT_3D.this.slices; i3++) {
                                        int i4 = i3 * 2;
                                        int access$600 = (FloatFFT_3D.this.slices * 2) + i4;
                                        int access$6002 = (FloatFFT_3D.this.slices * 2) + access$600;
                                        int access$6003 = (FloatFFT_3D.this.slices * 2) + access$6002;
                                        float[] fArr2 = fArr2[i3][i];
                                        fArr[i4] = fArr2[i2];
                                        fArr[i4 + 1] = fArr2[i2 + 1];
                                        fArr[access$600] = fArr2[i2 + 2];
                                        fArr[access$600 + 1] = fArr2[i2 + 3];
                                        fArr[access$6002] = fArr2[i2 + 4];
                                        fArr[access$6002 + 1] = fArr2[i2 + 5];
                                        fArr[access$6003] = fArr2[i2 + 6];
                                        fArr[access$6003 + 1] = fArr2[i2 + 7];
                                    }
                                    FloatFFT_3D.this.fftSlices.complexForward(fArr, 0);
                                    FloatFFT_3D.this.fftSlices.complexForward(fArr, FloatFFT_3D.this.slices * 2);
                                    FloatFFT_3D.this.fftSlices.complexForward(fArr, FloatFFT_3D.this.slices * 4);
                                    FloatFFT_3D.this.fftSlices.complexForward(fArr, FloatFFT_3D.this.slices * 6);
                                    for (int i5 = 0; i5 < FloatFFT_3D.this.slices; i5++) {
                                        int i6 = i5 * 2;
                                        int access$6004 = (FloatFFT_3D.this.slices * 2) + i6;
                                        int access$6005 = (FloatFFT_3D.this.slices * 2) + access$6004;
                                        int access$6006 = (FloatFFT_3D.this.slices * 2) + access$6005;
                                        float[] fArr3 = fArr2[i5][i];
                                        fArr3[i2] = fArr[i6];
                                        fArr3[i2 + 1] = fArr[i6 + 1];
                                        fArr3[i2 + 2] = fArr[access$6004];
                                        fArr3[i2 + 3] = fArr[access$6004 + 1];
                                        fArr3[i2 + 4] = fArr[access$6005];
                                        fArr3[i2 + 5] = fArr[access$6005 + 1];
                                        fArr3[i2 + 6] = fArr[access$6006];
                                        fArr3[i2 + 7] = fArr[access$6006 + 1];
                                    }
                                }
                                i += i11;
                            }
                        } else if (FloatFFT_3D.this.columns == 4) {
                            int i7 = i10;
                            while (i7 < FloatFFT_3D.this.rows) {
                                for (int i8 = 0; i8 < FloatFFT_3D.this.slices; i8++) {
                                    int i9 = i8 * 2;
                                    int access$6007 = (FloatFFT_3D.this.slices * 2) + i9;
                                    float[] fArr4 = fArr2[i8][i7];
                                    fArr[i9] = fArr4[0];
                                    fArr[i9 + 1] = fArr4[1];
                                    fArr[access$6007] = fArr4[2];
                                    fArr[access$6007 + 1] = fArr4[3];
                                }
                                FloatFFT_3D.this.fftSlices.complexForward(fArr, 0);
                                FloatFFT_3D.this.fftSlices.complexForward(fArr, FloatFFT_3D.this.slices * 2);
                                for (int i10 = 0; i10 < FloatFFT_3D.this.slices; i10++) {
                                    int i11 = i10 * 2;
                                    int access$6008 = (FloatFFT_3D.this.slices * 2) + i11;
                                    float[] fArr5 = fArr2[i10][i7];
                                    fArr5[0] = fArr[i11];
                                    fArr5[1] = fArr[i11 + 1];
                                    fArr5[2] = fArr[access$6008];
                                    fArr5[3] = fArr[access$6008 + 1];
                                }
                                i7 += i11;
                            }
                        } else if (FloatFFT_3D.this.columns == 2) {
                            int i12 = i10;
                            while (i12 < FloatFFT_3D.this.rows) {
                                for (int i13 = 0; i13 < FloatFFT_3D.this.slices; i13++) {
                                    int i14 = i13 * 2;
                                    float[] fArr6 = fArr2[i13][i12];
                                    fArr[i14] = fArr6[0];
                                    fArr[i14 + 1] = fArr6[1];
                                }
                                FloatFFT_3D.this.fftSlices.complexForward(fArr, 0);
                                for (int i15 = 0; i15 < FloatFFT_3D.this.slices; i15++) {
                                    int i16 = i15 * 2;
                                    float[] fArr7 = fArr2[i15][i12];
                                    fArr7[0] = fArr[i16];
                                    fArr7[1] = fArr[i16 + 1];
                                }
                                i12 += i11;
                            }
                        }
                    } else if (FloatFFT_3D.this.columns > 4) {
                        int i17 = i10;
                        while (i17 < FloatFFT_3D.this.rows) {
                            for (int i18 = 0; i18 < FloatFFT_3D.this.columns; i18 += 8) {
                                for (int i19 = 0; i19 < FloatFFT_3D.this.slices; i19++) {
                                    int i20 = i19 * 2;
                                    int access$6009 = (FloatFFT_3D.this.slices * 2) + i20;
                                    int access$60010 = (FloatFFT_3D.this.slices * 2) + access$6009;
                                    int access$60011 = (FloatFFT_3D.this.slices * 2) + access$60010;
                                    float[] fArr8 = fArr2[i19][i17];
                                    fArr[i20] = fArr8[i18];
                                    fArr[i20 + 1] = fArr8[i18 + 1];
                                    fArr[access$6009] = fArr8[i18 + 2];
                                    fArr[access$6009 + 1] = fArr8[i18 + 3];
                                    fArr[access$60010] = fArr8[i18 + 4];
                                    fArr[access$60010 + 1] = fArr8[i18 + 5];
                                    fArr[access$60011] = fArr8[i18 + 6];
                                    fArr[access$60011 + 1] = fArr8[i18 + 7];
                                }
                                FloatFFT_3D.this.fftSlices.complexInverse(fArr, 0, z2);
                                FloatFFT_3D.this.fftSlices.complexInverse(fArr, FloatFFT_3D.this.slices * 2, z2);
                                FloatFFT_3D.this.fftSlices.complexInverse(fArr, FloatFFT_3D.this.slices * 4, z2);
                                FloatFFT_3D.this.fftSlices.complexInverse(fArr, FloatFFT_3D.this.slices * 6, z2);
                                for (int i21 = 0; i21 < FloatFFT_3D.this.slices; i21++) {
                                    int i22 = i21 * 2;
                                    int access$60012 = (FloatFFT_3D.this.slices * 2) + i22;
                                    int access$60013 = (FloatFFT_3D.this.slices * 2) + access$60012;
                                    int access$60014 = (FloatFFT_3D.this.slices * 2) + access$60013;
                                    float[] fArr9 = fArr2[i21][i17];
                                    fArr9[i18] = fArr[i22];
                                    fArr9[i18 + 1] = fArr[i22 + 1];
                                    fArr9[i18 + 2] = fArr[access$60012];
                                    fArr9[i18 + 3] = fArr[access$60012 + 1];
                                    fArr9[i18 + 4] = fArr[access$60013];
                                    fArr9[i18 + 5] = fArr[access$60013 + 1];
                                    fArr9[i18 + 6] = fArr[access$60014];
                                    fArr9[i18 + 7] = fArr[access$60014 + 1];
                                }
                            }
                            i17 += i11;
                        }
                    } else if (FloatFFT_3D.this.columns == 4) {
                        int i23 = i10;
                        while (i23 < FloatFFT_3D.this.rows) {
                            for (int i24 = 0; i24 < FloatFFT_3D.this.slices; i24++) {
                                int i25 = i24 * 2;
                                int access$60015 = (FloatFFT_3D.this.slices * 2) + i25;
                                float[] fArr10 = fArr2[i24][i23];
                                fArr[i25] = fArr10[0];
                                fArr[i25 + 1] = fArr10[1];
                                fArr[access$60015] = fArr10[2];
                                fArr[access$60015 + 1] = fArr10[3];
                            }
                            FloatFFT_3D.this.fftSlices.complexInverse(fArr, 0, z2);
                            FloatFFT_3D.this.fftSlices.complexInverse(fArr, FloatFFT_3D.this.slices * 2, z2);
                            for (int i26 = 0; i26 < FloatFFT_3D.this.slices; i26++) {
                                int i27 = i26 * 2;
                                int access$60016 = (FloatFFT_3D.this.slices * 2) + i27;
                                float[] fArr11 = fArr2[i26][i23];
                                fArr11[0] = fArr[i27];
                                fArr11[1] = fArr[i27 + 1];
                                fArr11[2] = fArr[access$60016];
                                fArr11[3] = fArr[access$60016 + 1];
                            }
                            i23 += i11;
                        }
                    } else if (FloatFFT_3D.this.columns == 2) {
                        int i28 = i10;
                        while (i28 < FloatFFT_3D.this.rows) {
                            for (int i29 = 0; i29 < FloatFFT_3D.this.slices; i29++) {
                                int i30 = i29 * 2;
                                float[] fArr12 = fArr2[i29][i28];
                                fArr[i30] = fArr12[0];
                                fArr[i30 + 1] = fArr12[1];
                            }
                            FloatFFT_3D.this.fftSlices.complexInverse(fArr, 0, z2);
                            for (int i31 = 0; i31 < FloatFFT_3D.this.slices; i31++) {
                                int i32 = i31 * 2;
                                float[] fArr13 = fArr2[i31][i28];
                                fArr13[0] = fArr[i32];
                                fArr13[1] = fArr[i32 + 1];
                            }
                            i28 += i11;
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

    private void rdft3d_sub(int i, float[] fArr) {
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
                float f = fArr[i7];
                float f2 = fArr[i8];
                fArr[i7] = f + f2;
                fArr[i8] = f - f2;
                int i12 = i8 + 1;
                float f3 = fArr[i12];
                int i13 = i7 + 1;
                float f4 = fArr[i13];
                fArr[i13] = f4 + f3;
                fArr[i12] = f3 - f4;
                float f5 = fArr[i10];
                float f6 = fArr[i11];
                fArr[i10] = f5 + f6;
                fArr[i11] = f5 - f6;
                int i14 = i11 + 1;
                float f7 = fArr[i14];
                int i15 = i10 + 1;
                float f8 = fArr[i15];
                fArr[i15] = f8 + f7;
                fArr[i14] = f7 - f8;
                for (int i16 = 1; i16 < i3; i16++) {
                    int i17 = this.rows - i16;
                    int i18 = this.sliceStride;
                    int i19 = this.rowStride;
                    int i20 = (i4 * i18) + (i16 * i19);
                    int i21 = (i5 * i18) + (i17 * i19);
                    float f9 = fArr[i20];
                    float f10 = fArr[i21];
                    fArr[i20] = f9 + f10;
                    fArr[i21] = f9 - f10;
                    int i22 = i21 + 1;
                    float f11 = fArr[i22];
                    int i23 = i20 + 1;
                    float f12 = fArr[i23];
                    fArr[i23] = f12 + f11;
                    fArr[i22] = f11 - f12;
                    int i24 = (i5 * i18) + (i16 * i19);
                    int i25 = (i18 * i4) + (i17 * i19);
                    float f13 = fArr[i24];
                    float f14 = fArr[i25];
                    fArr[i24] = f13 + f14;
                    fArr[i25] = f13 - f14;
                    int i26 = i25 + 1;
                    float f15 = fArr[i26];
                    int i27 = i24 + 1;
                    float f16 = fArr[i27];
                    fArr[i27] = f16 + f15;
                    fArr[i26] = f15 - f16;
                }
            }
            for (int i28 = 1; i28 < i3; i28++) {
                int i29 = this.rows - i28;
                int i30 = this.rowStride;
                int i31 = i28 * i30;
                int i32 = i29 * i30;
                float f17 = fArr[i31];
                float f18 = fArr[i32];
                fArr[i31] = f17 + f18;
                fArr[i32] = f17 - f18;
                int i33 = i32 + 1;
                float f19 = fArr[i33];
                int i34 = i31 + 1;
                float f20 = fArr[i34];
                fArr[i34] = f20 + f19;
                fArr[i33] = f19 - f20;
                int i35 = this.sliceStride;
                int i36 = (i2 * i35) + (i28 * i30);
                int i37 = (i35 * i2) + (i29 * i30);
                float f21 = fArr[i36];
                float f22 = fArr[i37];
                fArr[i36] = f21 + f22;
                fArr[i37] = f21 - f22;
                int i38 = i37 + 1;
                float f23 = fArr[i38];
                int i39 = i36 + 1;
                float f24 = fArr[i39];
                fArr[i39] = f24 + f23;
                fArr[i38] = f23 - f24;
            }
            return;
        }
        for (int i40 = 1; i40 < i2; i40++) {
            int i41 = this.slices - i40;
            int i42 = this.sliceStride;
            int i43 = i41 * i42;
            int i44 = i40 * i42;
            float f25 = (fArr[i44] - fArr[i43]) * 0.5f;
            fArr[i43] = f25;
            fArr[i44] = fArr[i44] - f25;
            int i45 = i43 + 1;
            int i46 = i44 + 1;
            float f26 = (fArr[i46] + fArr[i45]) * 0.5f;
            fArr[i45] = f26;
            fArr[i46] = fArr[i46] - f26;
            int i47 = this.rowStride;
            int i48 = (i41 * i42) + (i3 * i47);
            int i49 = (i42 * i40) + (i47 * i3);
            float f27 = (fArr[i49] - fArr[i48]) * 0.5f;
            fArr[i48] = f27;
            fArr[i49] = fArr[i49] - f27;
            int i50 = i48 + 1;
            int i51 = i49 + 1;
            float f28 = (fArr[i51] + fArr[i50]) * 0.5f;
            fArr[i50] = f28;
            fArr[i51] = fArr[i51] - f28;
            for (int i52 = 1; i52 < i3; i52++) {
                int i53 = this.rows - i52;
                int i54 = this.sliceStride;
                int i55 = this.rowStride;
                int i56 = (i41 * i54) + (i53 * i55);
                int i57 = (i40 * i54) + (i52 * i55);
                float f29 = (fArr[i57] - fArr[i56]) * 0.5f;
                fArr[i56] = f29;
                fArr[i57] = fArr[i57] - f29;
                int i58 = i56 + 1;
                int i59 = i57 + 1;
                float f30 = (fArr[i59] + fArr[i58]) * 0.5f;
                fArr[i58] = f30;
                fArr[i59] = fArr[i59] - f30;
                int i60 = (i40 * i54) + (i53 * i55);
                int i61 = (i54 * i41) + (i55 * i52);
                float f31 = (fArr[i61] - fArr[i60]) * 0.5f;
                fArr[i60] = f31;
                fArr[i61] = fArr[i61] - f31;
                int i62 = i60 + 1;
                int i63 = i61 + 1;
                float f32 = (fArr[i63] + fArr[i62]) * 0.5f;
                fArr[i62] = f32;
                fArr[i63] = fArr[i63] - f32;
            }
        }
        for (int i64 = 1; i64 < i3; i64++) {
            int i65 = this.rows - i64;
            int i66 = this.rowStride;
            int i67 = i65 * i66;
            int i68 = i64 * i66;
            float f33 = (fArr[i68] - fArr[i67]) * 0.5f;
            fArr[i67] = f33;
            fArr[i68] = fArr[i68] - f33;
            int i69 = i67 + 1;
            int i70 = i68 + 1;
            float f34 = (fArr[i70] + fArr[i69]) * 0.5f;
            fArr[i69] = f34;
            fArr[i70] = fArr[i70] - f34;
            int i71 = this.sliceStride;
            int i72 = (i2 * i71) + (i65 * i66);
            int i73 = (i71 * i2) + (i66 * i64);
            float f35 = (fArr[i73] - fArr[i72]) * 0.5f;
            fArr[i72] = f35;
            fArr[i73] = fArr[i73] - f35;
            int i74 = i72 + 1;
            int i75 = i73 + 1;
            float f36 = (fArr[i75] + fArr[i74]) * 0.5f;
            fArr[i74] = f36;
            fArr[i75] = fArr[i75] - f36;
        }
    }

    private void rdft3d_sub(int i, FloatLargeArray floatLargeArray) {
        FloatLargeArray floatLargeArray2 = floatLargeArray;
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
                float f = floatLargeArray2.getFloat(j6) - floatLargeArray2.getFloat(j7);
                floatLargeArray2.setFloat(j6, floatLargeArray2.getFloat(j6) + floatLargeArray2.getFloat(j7));
                floatLargeArray2.setFloat(j7, f);
                long j13 = j7 + 1;
                long j14 = j6 + 1;
                float f2 = floatLargeArray2.getFloat(j13) - floatLargeArray2.getFloat(j14);
                floatLargeArray2.setFloat(j14, floatLargeArray2.getFloat(j14) + floatLargeArray2.getFloat(j13));
                floatLargeArray2.setFloat(j13, f2);
                float f3 = floatLargeArray2.getFloat(j11) - floatLargeArray2.getFloat(j12);
                floatLargeArray2.setFloat(j11, floatLargeArray2.getFloat(j11) + floatLargeArray2.getFloat(j12));
                floatLargeArray2.setFloat(j12, f3);
                long j15 = j12 + 1;
                long j16 = j11 + 1;
                float f4 = floatLargeArray2.getFloat(j15) - floatLargeArray2.getFloat(j16);
                floatLargeArray2.setFloat(j16, floatLargeArray2.getFloat(j16) + floatLargeArray2.getFloat(j15));
                floatLargeArray2.setFloat(j15, f4);
                for (long j17 = 1; j17 < j2; j17++) {
                    long j18 = this.rowsl - j17;
                    long j19 = this.sliceStridel;
                    long j20 = this.rowStridel;
                    long j21 = (j10 * j19) + (j17 * j20);
                    long j22 = (j19 * j4) + (j20 * j18);
                    float f5 = floatLargeArray2.getFloat(j21) - floatLargeArray2.getFloat(j22);
                    floatLargeArray2.setFloat(j21, floatLargeArray2.getFloat(j21) + floatLargeArray2.getFloat(j22));
                    floatLargeArray2.setFloat(j22, f5);
                    long j23 = j22 + 1;
                    long j24 = j21 + 1;
                    float f6 = floatLargeArray2.getFloat(j23) - floatLargeArray2.getFloat(j24);
                    floatLargeArray2.setFloat(j24, floatLargeArray2.getFloat(j24) + floatLargeArray2.getFloat(j23));
                    floatLargeArray2.setFloat(j23, f6);
                    long j25 = this.sliceStridel;
                    long j26 = this.rowStridel;
                    long j27 = (j4 * j25) + (j17 * j26);
                    long j28 = (j25 * j10) + (j18 * j26);
                    float f7 = floatLargeArray2.getFloat(j27) - floatLargeArray2.getFloat(j28);
                    floatLargeArray2.setFloat(j27, floatLargeArray2.getFloat(j27) + floatLargeArray2.getFloat(j28));
                    floatLargeArray2.setFloat(j28, f7);
                    long j29 = j28 + 1;
                    long j30 = j27 + 1;
                    float f8 = floatLargeArray2.getFloat(j29) - floatLargeArray2.getFloat(j30);
                    floatLargeArray2.setFloat(j30, floatLargeArray2.getFloat(j30) + floatLargeArray2.getFloat(j29));
                    floatLargeArray2.setFloat(j29, f8);
                }
                j3 = j10 + 1;
                j = j8;
            }
            long j31 = j;
            for (long j32 = 1; j32 < j2; j32++) {
                long j33 = this.rowsl - j32;
                long j34 = this.rowStridel;
                long j35 = j32 * j34;
                long j36 = j34 * j33;
                float f9 = floatLargeArray2.getFloat(j35) - floatLargeArray2.getFloat(j36);
                floatLargeArray2.setFloat(j35, floatLargeArray2.getFloat(j35) + floatLargeArray2.getFloat(j36));
                floatLargeArray2.setFloat(j36, f9);
                long j37 = j36 + 1;
                long j38 = j35 + 1;
                float f10 = floatLargeArray2.getFloat(j37) - floatLargeArray2.getFloat(j38);
                floatLargeArray2.setFloat(j38, floatLargeArray2.getFloat(j38) + floatLargeArray2.getFloat(j37));
                floatLargeArray2.setFloat(j37, f10);
                long j39 = this.sliceStridel;
                long j40 = this.rowStridel;
                long j41 = (j31 * j39) + (j32 * j40);
                long j42 = (j39 * j31) + (j33 * j40);
                float f11 = floatLargeArray2.getFloat(j41) - floatLargeArray2.getFloat(j42);
                floatLargeArray2.setFloat(j41, floatLargeArray2.getFloat(j41) + floatLargeArray2.getFloat(j42));
                floatLargeArray2.setFloat(j42, f11);
                long j43 = j42 + 1;
                long j44 = j41 + 1;
                float f12 = floatLargeArray2.getFloat(j43) - floatLargeArray2.getFloat(j44);
                floatLargeArray2.setFloat(j44, floatLargeArray2.getFloat(j44) + floatLargeArray2.getFloat(j43));
                floatLargeArray2.setFloat(j43, f12);
            }
            return;
        }
        long j45 = j;
        for (long j46 = 1; j46 < j45; j46++) {
            long j47 = this.slicesl - j46;
            long j48 = this.sliceStridel;
            long j49 = j47 * j48;
            long j50 = j48 * j46;
            floatLargeArray2.setFloat(j49, (floatLargeArray2.getFloat(j50) - floatLargeArray2.getFloat(j49)) * 0.5f);
            floatLargeArray2.setFloat(j50, floatLargeArray2.getFloat(j50) - floatLargeArray2.getFloat(j49));
            long j51 = j49 + 1;
            long j52 = j50 + 1;
            floatLargeArray2.setFloat(j51, (floatLargeArray2.getFloat(j52) + floatLargeArray2.getFloat(j51)) * 0.5f);
            floatLargeArray2.setFloat(j52, floatLargeArray2.getFloat(j52) - floatLargeArray2.getFloat(j51));
            long j53 = this.sliceStridel;
            long j54 = this.rowStridel;
            long j55 = (j47 * j53) + (j2 * j54);
            long j56 = (j53 * j46) + (j54 * j2);
            floatLargeArray2.setFloat(j55, (floatLargeArray2.getFloat(j56) - floatLargeArray2.getFloat(j55)) * 0.5f);
            floatLargeArray2.setFloat(j56, floatLargeArray2.getFloat(j56) - floatLargeArray2.getFloat(j55));
            long j57 = j55 + 1;
            long j58 = j56 + 1;
            floatLargeArray2.setFloat(j57, (floatLargeArray2.getFloat(j58) + floatLargeArray2.getFloat(j57)) * 0.5f);
            floatLargeArray2.setFloat(j58, floatLargeArray2.getFloat(j58) - floatLargeArray2.getFloat(j57));
            long j59 = 1;
            while (j59 < j2) {
                long j60 = this.rowsl - j59;
                long j61 = this.sliceStridel;
                long j62 = j47;
                long j63 = this.rowStridel;
                long j64 = j2;
                long j65 = (j47 * j61) + (j60 * j63);
                long j66 = (j61 * j46) + (j63 * j59);
                floatLargeArray2.setFloat(j65, (floatLargeArray2.getFloat(j66) - floatLargeArray2.getFloat(j65)) * 0.5f);
                floatLargeArray2.setFloat(j66, floatLargeArray2.getFloat(j66) - floatLargeArray2.getFloat(j65));
                long j67 = j65 + 1;
                long j68 = j66 + 1;
                floatLargeArray2.setFloat(j67, (floatLargeArray2.getFloat(j68) + floatLargeArray2.getFloat(j67)) * 0.5f);
                floatLargeArray2.setFloat(j68, floatLargeArray2.getFloat(j68) - floatLargeArray2.getFloat(j67));
                long j69 = this.sliceStridel;
                long j70 = this.rowStridel;
                long j71 = (j46 * j69) + (j60 * j70);
                long j72 = (j69 * j62) + (j70 * j59);
                floatLargeArray2.setFloat(j71, (floatLargeArray2.getFloat(j72) - floatLargeArray2.getFloat(j71)) * 0.5f);
                floatLargeArray2.setFloat(j72, floatLargeArray2.getFloat(j72) - floatLargeArray2.getFloat(j71));
                long j73 = j71 + 1;
                long j74 = j72 + 1;
                floatLargeArray2.setFloat(j73, (floatLargeArray2.getFloat(j74) + floatLargeArray2.getFloat(j73)) * 0.5f);
                floatLargeArray2.setFloat(j74, floatLargeArray2.getFloat(j74) - floatLargeArray2.getFloat(j73));
                j59++;
                j47 = j62;
                j2 = j64;
            }
            long j75 = j2;
        }
        long j76 = j2;
        for (long j77 = 1; j77 < j76; j77++) {
            long j78 = this.rowsl - j77;
            long j79 = this.rowStridel;
            long j80 = j78 * j79;
            long j81 = j79 * j77;
            floatLargeArray2.setFloat(j80, (floatLargeArray2.getFloat(j81) - floatLargeArray2.getFloat(j80)) * 0.5f);
            floatLargeArray2.setFloat(j81, floatLargeArray2.getFloat(j81) - floatLargeArray2.getFloat(j80));
            long j82 = j80 + 1;
            long j83 = j81 + 1;
            floatLargeArray2.setFloat(j82, (floatLargeArray2.getFloat(j83) + floatLargeArray2.getFloat(j82)) * 0.5f);
            floatLargeArray2.setFloat(j83, floatLargeArray2.getFloat(j83) - floatLargeArray2.getFloat(j82));
            long j84 = this.sliceStridel;
            long j85 = this.rowStridel;
            long j86 = (j45 * j84) + (j78 * j85);
            long j87 = (j45 * j84) + (j85 * j77);
            floatLargeArray2.setFloat(j86, (floatLargeArray2.getFloat(j87) - floatLargeArray2.getFloat(j86)) * 0.5f);
            floatLargeArray2.setFloat(j87, floatLargeArray2.getFloat(j87) - floatLargeArray2.getFloat(j86));
            long j88 = j86 + 1;
            long j89 = j87 + 1;
            floatLargeArray2.setFloat(j88, (floatLargeArray2.getFloat(j89) + floatLargeArray2.getFloat(j88)) * 0.5f);
            floatLargeArray2.setFloat(j89, floatLargeArray2.getFloat(j89) - floatLargeArray2.getFloat(j88));
        }
    }

    private void rdft3d_sub(int i, float[][][] fArr) {
        int i2 = this.slices >> 1;
        int i3 = this.rows >> 1;
        if (i < 0) {
            for (int i4 = 1; i4 < i2; i4++) {
                int i5 = this.slices - i4;
                float[][] fArr2 = fArr[i4];
                float[] fArr3 = fArr2[0];
                float f = fArr3[0];
                float[][] fArr4 = fArr[i5];
                float[] fArr5 = fArr4[0];
                float f2 = fArr5[0];
                fArr3[0] = f + f2;
                fArr5[0] = f - f2;
                float f3 = fArr5[1];
                float f4 = fArr3[1];
                fArr3[1] = f4 + f3;
                fArr5[1] = f3 - f4;
                float[] fArr6 = fArr2[i3];
                float f5 = fArr6[0];
                float[] fArr7 = fArr4[i3];
                float f6 = fArr7[0];
                fArr6[0] = f5 + f6;
                fArr7[0] = f5 - f6;
                float f7 = fArr7[1];
                float f8 = fArr6[1];
                fArr6[1] = f8 + f7;
                fArr7[1] = f7 - f8;
                for (int i6 = 1; i6 < i3; i6++) {
                    int i7 = this.rows - i6;
                    float[][] fArr8 = fArr[i4];
                    float[] fArr9 = fArr8[i6];
                    float f9 = fArr9[0];
                    float[][] fArr10 = fArr[i5];
                    float[] fArr11 = fArr10[i7];
                    float f10 = fArr11[0];
                    fArr9[0] = f9 + f10;
                    fArr11[0] = f9 - f10;
                    float f11 = fArr11[1];
                    float f12 = fArr9[1];
                    fArr9[1] = f12 + f11;
                    fArr11[1] = f11 - f12;
                    float[] fArr12 = fArr10[i6];
                    float f13 = fArr12[0];
                    float[] fArr13 = fArr8[i7];
                    float f14 = fArr13[0];
                    fArr12[0] = f13 + f14;
                    fArr13[0] = f13 - f14;
                    float f15 = fArr13[1];
                    float f16 = fArr12[1];
                    fArr12[1] = f16 + f15;
                    fArr13[1] = f15 - f16;
                }
            }
            for (int i8 = 1; i8 < i3; i8++) {
                int i9 = this.rows - i8;
                float[][] fArr14 = fArr[0];
                float[] fArr15 = fArr14[i8];
                float f17 = fArr15[0];
                float[] fArr16 = fArr14[i9];
                float f18 = fArr16[0];
                fArr15[0] = f17 + f18;
                fArr16[0] = f17 - f18;
                float f19 = fArr16[1];
                float f20 = fArr15[1];
                fArr15[1] = f20 + f19;
                fArr16[1] = f19 - f20;
                float[][] fArr17 = fArr[i2];
                float[] fArr18 = fArr17[i8];
                float f21 = fArr18[0];
                float[] fArr19 = fArr17[i9];
                float f22 = fArr19[0];
                fArr18[0] = f21 + f22;
                fArr19[0] = f21 - f22;
                float f23 = fArr19[1];
                float f24 = fArr18[1];
                fArr18[1] = f24 + f23;
                fArr19[1] = f23 - f24;
            }
            return;
        }
        for (int i10 = 1; i10 < i2; i10++) {
            int i11 = this.slices - i10;
            float[][] fArr20 = fArr[i11];
            float[] fArr21 = fArr20[0];
            float[][] fArr22 = fArr[i10];
            float[] fArr23 = fArr22[0];
            float f25 = (fArr23[0] - fArr21[0]) * 0.5f;
            fArr21[0] = f25;
            fArr23[0] = fArr23[0] - f25;
            float f26 = (fArr23[1] + fArr21[1]) * 0.5f;
            fArr21[1] = f26;
            fArr23[1] = fArr23[1] - f26;
            float[] fArr24 = fArr20[i3];
            float[] fArr25 = fArr22[i3];
            float f27 = (fArr25[0] - fArr24[0]) * 0.5f;
            fArr24[0] = f27;
            fArr25[0] = fArr25[0] - f27;
            float f28 = (fArr25[1] + fArr24[1]) * 0.5f;
            fArr24[1] = f28;
            fArr25[1] = fArr25[1] - f28;
            for (int i12 = 1; i12 < i3; i12++) {
                int i13 = this.rows - i12;
                float[][] fArr26 = fArr[i11];
                float[] fArr27 = fArr26[i13];
                float[][] fArr28 = fArr[i10];
                float[] fArr29 = fArr28[i12];
                float f29 = (fArr29[0] - fArr27[0]) * 0.5f;
                fArr27[0] = f29;
                fArr29[0] = fArr29[0] - f29;
                float f30 = (fArr29[1] + fArr27[1]) * 0.5f;
                fArr27[1] = f30;
                fArr29[1] = fArr29[1] - f30;
                float[] fArr30 = fArr28[i13];
                float[] fArr31 = fArr26[i12];
                float f31 = (fArr31[0] - fArr30[0]) * 0.5f;
                fArr30[0] = f31;
                fArr31[0] = fArr31[0] - f31;
                float f32 = (fArr31[1] + fArr30[1]) * 0.5f;
                fArr30[1] = f32;
                fArr31[1] = fArr31[1] - f32;
            }
        }
        for (int i14 = 1; i14 < i3; i14++) {
            int i15 = this.rows - i14;
            float[][] fArr32 = fArr[0];
            float[] fArr33 = fArr32[i15];
            float[] fArr34 = fArr32[i14];
            float f33 = (fArr34[0] - fArr33[0]) * 0.5f;
            fArr33[0] = f33;
            fArr34[0] = fArr34[0] - f33;
            float f34 = (fArr34[1] + fArr33[1]) * 0.5f;
            fArr33[1] = f34;
            fArr34[1] = fArr34[1] - f34;
            float[][] fArr35 = fArr[i2];
            float[] fArr36 = fArr35[i15];
            float[] fArr37 = fArr35[i14];
            float f35 = (fArr37[0] - fArr36[0]) * 0.5f;
            fArr36[0] = f35;
            fArr37[0] = fArr37[0] - f35;
            float f36 = (fArr37[1] + fArr36[1]) * 0.5f;
            fArr36[1] = f36;
            fArr37[1] = fArr37[1] - f36;
        }
    }

    private void fillSymmetric(float[][][] fArr) {
        int i;
        Class<FloatFFT_3D> cls = FloatFFT_3D.class;
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
                        float[] fArr2 = fArr[i7][i10];
                        float[] fArr3 = fArr[i5][i8];
                        int i13 = i11 + 2;
                        fArr2[i12] = -fArr3[i13];
                        fArr2[i12 - 1] = fArr3[i11 + 1];
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
                    float[] fArr4 = fArr[i16][i17];
                    int i18 = this.columns;
                    float[] fArr5 = fArr[i14][this.rows - i17];
                    fArr4[i18] = fArr5[1];
                    fArr5[i18] = fArr5[1];
                    fArr4[i18 + 1] = -fArr5[0];
                    fArr5[i18 + 1] = fArr5[0];
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
                    float[] fArr6 = fArr[i21][this.rows - i22];
                    float[] fArr7 = fArr[i19][i22];
                    fArr6[0] = fArr7[0];
                    fArr6[1] = -fArr7[1];
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
                final float[][][] fArr8 = fArr;
                futureArr[i28] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i25; i < i26; i++) {
                            int access$600 = (FloatFFT_3D.this.slices - i) % FloatFFT_3D.this.slices;
                            for (int i2 = 0; i2 < FloatFFT_3D.this.rows; i2++) {
                                int access$100 = (FloatFFT_3D.this.rows - i2) % FloatFFT_3D.this.rows;
                                int i3 = 1;
                                while (i3 < FloatFFT_3D.this.columns) {
                                    int i4 = i27 - i3;
                                    float[][][] fArr = fArr8;
                                    float[] fArr2 = fArr[access$600][access$100];
                                    float[] fArr3 = fArr[i][i2];
                                    int i5 = i3 + 2;
                                    fArr2[i4] = -fArr3[i5];
                                    fArr2[i4 - 1] = fArr3[i3 + 1];
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
                final float[][][] fArr9 = fArr;
                futureArr[i29] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i30; i < i31; i++) {
                            int access$600 = (FloatFFT_3D.this.slices - i) % FloatFFT_3D.this.slices;
                            for (int i2 = 1; i2 < i32; i2++) {
                                int access$100 = FloatFFT_3D.this.rows - i2;
                                float[] fArr = fArr9[access$600][i2];
                                int access$400 = FloatFFT_3D.this.columns;
                                float[] fArr2 = fArr9[i][access$100];
                                fArr[access$400] = fArr2[1];
                                int access$4002 = FloatFFT_3D.this.columns;
                                float[][][] fArr3 = fArr9;
                                fArr2[access$4002] = fArr3[i][access$100][1];
                                float[] fArr4 = fArr9[i][access$100];
                                fArr3[access$600][i2][FloatFFT_3D.this.columns + 1] = -fArr4[0];
                                fArr4[FloatFFT_3D.this.columns + 1] = fArr9[i][access$100][0];
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
                final float[][][] fArr10 = fArr;
                futureArr[i33] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i34; i < i35; i++) {
                            int access$600 = (FloatFFT_3D.this.slices - i) % FloatFFT_3D.this.slices;
                            for (int i2 = 1; i2 < i36; i2++) {
                                float[][][] fArr = fArr10;
                                float[] fArr2 = fArr[access$600][FloatFFT_3D.this.rows - i2];
                                float[] fArr3 = fArr[i][i2];
                                fArr2[0] = fArr3[0];
                                fArr2[1] = -fArr3[1];
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
            float[][] fArr11 = fArr[i37];
            float[] fArr12 = fArr11[0];
            int i38 = this.columns;
            float[][] fArr13 = fArr[this.slices - i37];
            float[] fArr14 = fArr13[0];
            fArr12[i38] = fArr14[1];
            fArr14[i38] = fArr14[1];
            fArr12[i38 + 1] = -fArr14[0];
            fArr14[i38 + 1] = fArr14[0];
            float[] fArr15 = fArr11[i3];
            float[] fArr16 = fArr13[i3];
            fArr15[i38] = fArr16[1];
            fArr16[i38] = fArr16[1];
            fArr15[i38 + 1] = -fArr16[0];
            fArr16[i38 + 1] = fArr16[0];
            fArr14[0] = fArr12[0];
            fArr14[1] = -fArr12[1];
            fArr16[0] = fArr15[0];
            fArr16[1] = -fArr15[1];
        }
        float[][] fArr17 = fArr[0];
        float[] fArr18 = fArr17[0];
        int i39 = this.columns;
        fArr18[i39] = fArr18[1];
        fArr18[1] = 0.0f;
        float[] fArr19 = fArr17[i3];
        fArr19[i39] = fArr19[1];
        fArr19[1] = 0.0f;
        float[][] fArr20 = fArr[i4];
        float[] fArr21 = fArr20[0];
        fArr21[i39] = fArr21[1];
        fArr21[1] = 0.0f;
        float[] fArr22 = fArr20[i3];
        fArr22[i39] = fArr22[1];
        fArr22[1] = 0.0f;
        fArr21[i39 + 1] = 0.0f;
        fArr22[i39 + 1] = 0.0f;
    }

    private void fillSymmetric(float[] fArr) {
        int i;
        int i2;
        Class<FloatFFT_3D> cls = FloatFFT_3D.class;
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
                    fArr[i17] = fArr[i16];
                    fArr[i16] = 0.0f;
                    int i18 = i16 + 1;
                    fArr[i17 + 1] = fArr[i18];
                    fArr[i18] = 0.0f;
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
                fArr[i25] = fArr[i24];
                fArr[i24] = 0.0f;
                int i26 = i24 + 1;
                fArr[i25 + 1] = fArr[i26];
                fArr[i26] = 0.0f;
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
                    for (int i35 = 1; i35 < this.columns; i35 += 2) {
                        int i36 = ((i29 + i33) + i3) - i35;
                        int i37 = i30 + i34 + i35;
                        fArr[i36] = -fArr[i37 + 2];
                        fArr[i36 - 1] = fArr[i37 + 1];
                    }
                    i31++;
                }
                i27++;
            }
            int i38 = 0;
            while (true) {
                int i39 = this.slices;
                if (i38 >= i39) {
                    break;
                }
                int i40 = ((i39 - i38) % i39) * i8;
                int i41 = i38 * i8;
                for (int i42 = 1; i42 < i5; i42++) {
                    int i43 = ((this.rows - i42) * i3) + i41;
                    int i44 = this.columns;
                    int i45 = (i42 * i3) + i40 + i44;
                    int i46 = i44 + i43;
                    int i47 = i43 + 1;
                    fArr[i45] = fArr[i47];
                    fArr[i46] = fArr[i47];
                    fArr[i45 + 1] = -fArr[i43];
                    fArr[i46 + 1] = fArr[i43];
                }
                i38++;
            }
            int i48 = 0;
            while (true) {
                int i49 = this.slices;
                if (i48 >= i49) {
                    break;
                }
                int i50 = ((i49 - i48) % i49) * i8;
                int i51 = i48 * i8;
                for (int i52 = 1; i52 < i5; i52++) {
                    int i53 = ((this.rows - i52) * i3) + i50;
                    int i54 = (i52 * i3) + i51;
                    fArr[i53] = fArr[i54];
                    fArr[i53 + 1] = -fArr[i54 + 1];
                }
                i48++;
            }
        } else {
            Future[] futureArr = new Future[numberOfThreads];
            int i55 = i2 / numberOfThreads;
            int i56 = 0;
            while (i56 < numberOfThreads) {
                final int i57 = i56 * i55;
                final int i58 = i56 == numberOfThreads + -1 ? this.slices : i57 + i55;
                final int i59 = i8;
                final int i60 = i3;
                Future[] futureArr2 = futureArr;
                final int i61 = i3;
                int i62 = numberOfThreads;
                final float[] fArr2 = fArr;
                futureArr2[i56] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i57; i < i58; i++) {
                            int access$600 = (FloatFFT_3D.this.slices - i) % FloatFFT_3D.this.slices;
                            int i2 = i59;
                            int i3 = access$600 * i2;
                            int i4 = i2 * i;
                            for (int i5 = 0; i5 < FloatFFT_3D.this.rows; i5++) {
                                int access$100 = (FloatFFT_3D.this.rows - i5) % FloatFFT_3D.this.rows;
                                int i6 = i60;
                                int i7 = access$100 * i6;
                                int i8 = i6 * i5;
                                for (int i9 = 1; i9 < FloatFFT_3D.this.columns; i9 += 2) {
                                    int i10 = ((i3 + i7) + i61) - i9;
                                    int i11 = i4 + i8 + i9;
                                    float[] fArr = fArr2;
                                    fArr[i10] = -fArr[i11 + 2];
                                    fArr[i10 - 1] = fArr[i11 + 1];
                                }
                            }
                        }
                    }
                });
                i56++;
                numberOfThreads = i62;
                futureArr = futureArr2;
            }
            Future[] futureArr3 = futureArr;
            int i63 = numberOfThreads;
            String str = null;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
            } catch (ExecutionException e2) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
            }
            int i64 = 0;
            while (i64 < i63) {
                final int i65 = i64 * i55;
                final int i66 = i64 == i63 + -1 ? this.slices : i65 + i55;
                final int i67 = i8;
                final int i68 = i5;
                final int i69 = i3;
                int i70 = i7;
                String str2 = str;
                final float[] fArr3 = fArr;
                futureArr3[i64] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i65; i < i66; i++) {
                            int access$600 = (FloatFFT_3D.this.slices - i) % FloatFFT_3D.this.slices;
                            int i2 = i67;
                            int i3 = access$600 * i2;
                            int i4 = i2 * i;
                            for (int i5 = 1; i5 < i68; i5++) {
                                int i6 = i69;
                                int access$100 = ((FloatFFT_3D.this.rows - i5) * i6) + i4;
                                int access$400 = (i6 * i5) + i3 + FloatFFT_3D.this.columns;
                                int access$4002 = FloatFFT_3D.this.columns + access$100;
                                int i7 = access$100 + 1;
                                float[] fArr = fArr3;
                                fArr[access$400] = fArr[i7];
                                fArr[access$4002] = fArr[i7];
                                fArr[access$400 + 1] = -fArr[access$100];
                                fArr[access$4002 + 1] = fArr[access$100];
                            }
                        }
                    }
                });
                i64++;
                str = str2;
                i7 = i70;
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
            int i71 = 0;
            while (i71 < i63) {
                final int i72 = i71 * i55;
                final int i73 = i71 == i63 + -1 ? this.slices : i72 + i55;
                final int i74 = i8;
                final int i75 = i5;
                final int i76 = i3;
                final float[] fArr4 = fArr;
                futureArr3[i71] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i72; i < i73; i++) {
                            int access$600 = (FloatFFT_3D.this.slices - i) % FloatFFT_3D.this.slices;
                            int i2 = i74;
                            int i3 = access$600 * i2;
                            int i4 = i2 * i;
                            for (int i5 = 1; i5 < i75; i5++) {
                                int i6 = i76;
                                int access$100 = ((FloatFFT_3D.this.rows - i5) * i6) + i3;
                                int i7 = (i6 * i5) + i4;
                                float[] fArr = fArr4;
                                fArr[access$100] = fArr[i7];
                                fArr[access$100 + 1] = -fArr[i7 + 1];
                            }
                        }
                    }
                });
                i71++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e5) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e5);
            } catch (ExecutionException e6) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e6);
            }
        }
        int i77 = i;
        for (int i78 = 1; i78 < i77; i78++) {
            int i79 = i78 * i8;
            int i80 = (this.slices - i78) * i8;
            int i81 = i5 * i3;
            int i82 = i79 + i81;
            int i83 = i81 + i80;
            int i84 = this.columns;
            int i85 = i80 + 1;
            fArr[i79 + i84] = fArr[i85];
            fArr[i80 + i84] = fArr[i85];
            fArr[i79 + i84 + 1] = -fArr[i80];
            fArr[i80 + i84 + 1] = fArr[i80];
            int i86 = i83 + 1;
            fArr[i82 + i84] = fArr[i86];
            fArr[i83 + i84] = fArr[i86];
            fArr[i82 + i84 + 1] = -fArr[i83];
            fArr[i84 + i83 + 1] = fArr[i83];
            fArr[i80] = fArr[i79];
            fArr[i85] = -fArr[i79 + 1];
            fArr[i83] = fArr[i82];
            fArr[i86] = -fArr[i82 + 1];
        }
        int i87 = this.columns;
        fArr[i87] = fArr[1];
        fArr[1] = 0.0f;
        int i88 = i5 * i3;
        int i89 = i77 * i8;
        int i90 = i88 + i89;
        int i91 = i88 + i87;
        int i92 = i88 + 1;
        fArr[i91] = fArr[i92];
        fArr[i92] = 0.0f;
        int i93 = i89 + 1;
        fArr[i89 + i87] = fArr[i93];
        fArr[i93] = 0.0f;
        int i94 = i90 + 1;
        fArr[i90 + i87] = fArr[i94];
        fArr[i94] = 0.0f;
        fArr[i89 + i87 + 1] = 0.0f;
        fArr[i90 + i87 + 1] = 0.0f;
    }

    /* JADX WARNING: Removed duplicated region for block: B:100:0x02e3 A[LOOP:15: B:98:0x02df->B:100:0x02e3, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void fillSymmetric(pl.edu.icm.jlargearrays.FloatLargeArray r38) {
        /*
            r37 = this;
            r14 = r37
            r15 = r38
            java.lang.Class<org.jtransforms.fft.FloatFFT_3D> r16 = org.jtransforms.fft.FloatFFT_3D.class
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
            r13 = 0
            if (r0 < 0) goto L_0x0070
            long r0 = r14.sliceStridel
            long r0 = r0 * r4
            long r8 = r0 * r2
            r25 = 0
        L_0x0025:
            long r6 = r14.rowsl
            int r6 = (r25 > r6 ? 1 : (r25 == r6 ? 0 : -1))
            if (r6 >= 0) goto L_0x0069
            long r6 = r14.rowStridel
            long r6 = r6 * r25
            long r29 = r6 * r2
            r31 = 0
        L_0x0033:
            long r2 = r14.columnsl
            int r2 = (r31 > r2 ? 1 : (r31 == r2 ? 0 : -1))
            if (r2 >= 0) goto L_0x0062
            long r2 = r0 + r6
            long r2 = r2 + r31
            long r35 = r8 + r29
            long r11 = r35 + r31
            float r10 = r15.getFloat(r2)
            r15.setFloat(r11, r10)
            r15.setFloat(r2, r13)
            r35 = 1
            long r2 = r2 + r35
            long r11 = r11 + r35
            float r10 = r15.getFloat(r2)
            r15.setFloat(r11, r10)
            r15.setFloat(r2, r13)
            r2 = 2
            long r31 = r31 + r2
            r11 = r35
            goto L_0x0033
        L_0x0062:
            r35 = r11
            long r25 = r25 + r35
            r2 = 2
            goto L_0x0025
        L_0x0069:
            r35 = r11
            long r4 = r4 - r35
            r2 = 2
            goto L_0x0019
        L_0x0070:
            r35 = 1
        L_0x0072:
            long r0 = r14.rowsl
            int r2 = (r35 > r0 ? 1 : (r35 == r0 ? 0 : -1))
            if (r2 >= 0) goto L_0x00ae
            long r2 = r0 - r35
            long r4 = r14.rowStridel
            long r2 = r2 * r4
            long r0 = r0 - r35
            long r0 = r0 * r17
            r4 = 0
        L_0x0083:
            long r6 = r14.columnsl
            int r6 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r6 >= 0) goto L_0x00a9
            long r6 = r2 + r4
            long r8 = r0 + r4
            float r10 = r15.getFloat(r6)
            r15.setFloat(r8, r10)
            r15.setFloat(r6, r13)
            r11 = 1
            long r6 = r6 + r11
            long r8 = r8 + r11
            float r10 = r15.getFloat(r6)
            r15.setFloat(r8, r10)
            r15.setFloat(r6, r13)
            r6 = 2
            long r4 = r4 + r6
            goto L_0x0083
        L_0x00a9:
            r11 = 1
            long r35 = r35 + r11
            goto L_0x0072
        L_0x00ae:
            r11 = 1
            int r9 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getNumberOfThreads()
            r0 = 1
            if (r9 <= r0) goto L_0x01d6
            boolean r0 = r14.useThreads
            if (r0 == 0) goto L_0x01d6
            long r0 = r14.slicesl
            long r2 = (long) r9
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 < 0) goto L_0x01d6
            java.util.concurrent.Future[] r10 = new java.util.concurrent.Future[r9]
            long r25 = r0 / r2
            r27 = 0
            r0 = r27
        L_0x00ca:
            if (r0 >= r9) goto L_0x00fe
            long r1 = (long) r0
            long r3 = r1 * r25
            int r1 = r9 + -1
            if (r0 != r1) goto L_0x00d6
            long r1 = r14.slicesl
            goto L_0x00d8
        L_0x00d6:
            long r1 = r3 + r25
        L_0x00d8:
            r5 = r1
            org.jtransforms.fft.FloatFFT_3D$66 r28 = new org.jtransforms.fft.FloatFFT_3D$66
            r1 = r28
            r2 = r37
            r7 = r23
            r15 = r9
            r29 = r10
            r9 = r17
            r11 = r17
            r13 = r38
            r1.<init>(r3, r5, r7, r9, r11, r13)
            java.util.concurrent.Future r1 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r28)
            r29[r0] = r1
            int r0 = r0 + 1
            r9 = r15
            r10 = r29
            r11 = 1
            r13 = 0
            r15 = r38
            goto L_0x00ca
        L_0x00fe:
            r15 = r9
            r29 = r10
            r13 = 0
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r29)     // Catch:{ InterruptedException -> 0x0116, ExecutionException -> 0x0106 }
            goto L_0x0125
        L_0x0106:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = r16.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            r0.log(r2, r13, r1)
            goto L_0x0125
        L_0x0116:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = r16.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            r0.log(r2, r13, r1)
        L_0x0125:
            r0 = r27
        L_0x0127:
            if (r0 >= r15) goto L_0x0154
            long r1 = (long) r0
            long r3 = r1 * r25
            int r9 = r15 + -1
            if (r0 != r9) goto L_0x0133
            long r1 = r14.slicesl
            goto L_0x0135
        L_0x0133:
            long r1 = r3 + r25
        L_0x0135:
            r5 = r1
            org.jtransforms.fft.FloatFFT_3D$67 r28 = new org.jtransforms.fft.FloatFFT_3D$67
            r1 = r28
            r2 = r37
            r7 = r23
            r9 = r19
            r11 = r17
            r14 = r13
            r13 = r38
            r1.<init>(r3, r5, r7, r9, r11, r13)
            java.util.concurrent.Future r1 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r28)
            r29[r0] = r1
            int r0 = r0 + 1
            r13 = r14
            r14 = r37
            goto L_0x0127
        L_0x0154:
            r14 = r13
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r29)     // Catch:{ InterruptedException -> 0x0169, ExecutionException -> 0x0159 }
            goto L_0x0178
        L_0x0159:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = r16.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            r0.log(r2, r14, r1)
            goto L_0x0178
        L_0x0169:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = r16.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            r0.log(r2, r14, r1)
        L_0x0178:
            r0 = r27
        L_0x017a:
            if (r0 >= r15) goto L_0x01ac
            long r1 = (long) r0
            long r3 = r1 * r25
            int r9 = r15 + -1
            r13 = r14
            r14 = r37
            if (r0 != r9) goto L_0x0189
            long r1 = r14.slicesl
            goto L_0x018b
        L_0x0189:
            long r1 = r3 + r25
        L_0x018b:
            r5 = r1
            org.jtransforms.fft.FloatFFT_3D$68 r27 = new org.jtransforms.fft.FloatFFT_3D$68
            r1 = r27
            r2 = r37
            r7 = r23
            r9 = r19
            r11 = r17
            r28 = r15
            r15 = r13
            r13 = r38
            r1.<init>(r3, r5, r7, r9, r11, r13)
            java.util.concurrent.Future r1 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r27)
            r29[r0] = r1
            int r0 = r0 + 1
            r14 = r15
            r15 = r28
            goto L_0x017a
        L_0x01ac:
            r15 = r14
            r14 = r37
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r29)     // Catch:{ InterruptedException -> 0x01c3, ExecutionException -> 0x01b3 }
            goto L_0x01d2
        L_0x01b3:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = r16.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            r0.log(r2, r15, r1)
            goto L_0x01d2
        L_0x01c3:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = r16.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            r0.log(r2, r15, r1)
        L_0x01d2:
            r4 = r38
            goto L_0x02dd
        L_0x01d6:
            r0 = 0
        L_0x01d8:
            long r2 = r14.slicesl
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 >= 0) goto L_0x024a
            long r4 = r2 - r0
            long r4 = r4 % r2
            long r4 = r4 * r23
            long r2 = r0 * r23
            r6 = 0
        L_0x01e7:
            long r8 = r14.rowsl
            int r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r10 >= 0) goto L_0x0240
            long r10 = r8 - r6
            long r10 = r10 % r8
            long r10 = r10 * r17
            long r8 = r6 * r17
            r15 = r0
            r12 = 1
        L_0x01f7:
            long r0 = r14.columnsl
            int r0 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r0 >= 0) goto L_0x022f
            long r0 = r4 + r10
            long r0 = r0 + r17
            long r0 = r0 - r12
            long r25 = r2 + r8
            long r25 = r25 + r12
            r31 = r2
            r29 = 2
            long r2 = r25 + r29
            r33 = r4
            r4 = r38
            float r2 = r4.getFloat(r2)
            float r2 = -r2
            r4.setFloat(r0, r2)
            r2 = 1
            long r0 = r0 - r2
            r35 = r8
            long r8 = r25 + r2
            float r5 = r4.getFloat(r8)
            r4.setFloat(r0, r5)
            long r12 = r12 + r29
            r2 = r31
            r4 = r33
            r8 = r35
            goto L_0x01f7
        L_0x022f:
            r31 = r2
            r33 = r4
            r2 = 1
            r29 = 2
            r4 = r38
            long r6 = r6 + r2
            r0 = r15
            r2 = r31
            r4 = r33
            goto L_0x01e7
        L_0x0240:
            r4 = r38
            r15 = r0
            r2 = 1
            r29 = 2
            long r0 = r15 + r2
            goto L_0x01d8
        L_0x024a:
            r4 = r38
            r2 = 1
            r0 = 0
        L_0x0250:
            long r5 = r14.slicesl
            int r7 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r7 >= 0) goto L_0x02a1
            long r7 = r5 - r0
            long r7 = r7 % r5
            long r7 = r7 * r23
            long r5 = r0 * r23
            r11 = r2
        L_0x025e:
            int r9 = (r11 > r19 ? 1 : (r11 == r19 ? 0 : -1))
            if (r9 >= 0) goto L_0x029e
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
            r29 = r7
            r15 = 1
            long r7 = r9 + r15
            float r13 = r4.getFloat(r7)
            r4.setFloat(r5, r13)
            float r7 = r4.getFloat(r7)
            r4.setFloat(r2, r7)
            long r5 = r5 + r15
            float r7 = r4.getFloat(r9)
            float r7 = -r7
            r4.setFloat(r5, r7)
            long r2 = r2 + r15
            float r5 = r4.getFloat(r9)
            r4.setFloat(r2, r5)
            long r11 = r11 + r15
            r2 = r15
            r5 = r25
            r7 = r29
            goto L_0x025e
        L_0x029e:
            r15 = r2
            long r0 = r0 + r15
            goto L_0x0250
        L_0x02a1:
            r6 = 0
        L_0x02a3:
            long r0 = r14.slicesl
            int r2 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r2 >= 0) goto L_0x02dd
            long r2 = r0 - r6
            long r2 = r2 % r0
            long r2 = r2 * r23
            long r0 = r6 * r23
            r11 = 1
        L_0x02b2:
            int r5 = (r11 > r19 ? 1 : (r11 == r19 ? 0 : -1))
            if (r5 >= 0) goto L_0x02d9
            long r8 = r14.rowsl
            long r8 = r8 - r11
            long r8 = r8 * r17
            long r8 = r8 + r2
            long r15 = r11 * r17
            r25 = r2
            long r2 = r0 + r15
            float r5 = r4.getFloat(r2)
            r4.setFloat(r8, r5)
            r15 = 1
            long r8 = r8 + r15
            long r2 = r2 + r15
            float r2 = r4.getFloat(r2)
            float r2 = -r2
            r4.setFloat(r8, r2)
            long r11 = r11 + r15
            r2 = r25
            goto L_0x02b2
        L_0x02d9:
            r15 = 1
            long r6 = r6 + r15
            goto L_0x02a3
        L_0x02dd:
            r11 = 1
        L_0x02df:
            int r0 = (r11 > r21 ? 1 : (r11 == r21 ? 0 : -1))
            if (r0 >= 0) goto L_0x037f
            long r0 = r11 * r23
            long r2 = r14.slicesl
            long r2 = r2 - r11
            long r2 = r2 * r23
            long r5 = r19 * r17
            long r7 = r0 + r5
            long r5 = r5 + r2
            long r9 = r14.columnsl
            long r9 = r9 + r0
            r25 = r5
            r15 = r11
            r11 = 1
            long r5 = r2 + r11
            float r13 = r4.getFloat(r5)
            r4.setFloat(r9, r13)
            long r9 = r14.columnsl
            long r9 = r9 + r2
            float r13 = r4.getFloat(r5)
            r4.setFloat(r9, r13)
            long r9 = r14.columnsl
            long r9 = r9 + r0
            long r9 = r9 + r11
            float r13 = r4.getFloat(r2)
            float r13 = -r13
            r4.setFloat(r9, r13)
            long r9 = r14.columnsl
            long r9 = r9 + r2
            long r9 = r9 + r11
            float r13 = r4.getFloat(r2)
            r4.setFloat(r9, r13)
            long r9 = r14.columnsl
            long r9 = r9 + r7
            r27 = r5
            long r5 = r25 + r11
            float r13 = r4.getFloat(r5)
            r4.setFloat(r9, r13)
            long r9 = r14.columnsl
            long r9 = r25 + r9
            float r13 = r4.getFloat(r5)
            r4.setFloat(r9, r13)
            long r9 = r14.columnsl
            long r9 = r9 + r7
            long r9 = r9 + r11
            r11 = r25
            float r13 = r4.getFloat(r11)
            float r13 = -r13
            r4.setFloat(r9, r13)
            long r9 = r14.columnsl
            long r9 = r9 + r11
            r13 = 1
            long r9 = r9 + r13
            float r13 = r4.getFloat(r11)
            r4.setFloat(r9, r13)
            float r9 = r4.getFloat(r0)
            r4.setFloat(r2, r9)
            r2 = 1
            long r0 = r0 + r2
            float r0 = r4.getFloat(r0)
            float r0 = -r0
            r9 = r27
            r4.setFloat(r9, r0)
            float r0 = r4.getFloat(r7)
            r4.setFloat(r11, r0)
            long r7 = r7 + r2
            float r0 = r4.getFloat(r7)
            float r0 = -r0
            r4.setFloat(r5, r0)
            long r11 = r15 + r2
            r14 = r37
            goto L_0x02df
        L_0x037f:
            r1 = r14
            r2 = 1
            long r5 = r1.columnsl
            float r0 = r4.getFloat(r2)
            r4.setFloat(r5, r0)
            r5 = 0
            r4.setFloat(r2, r5)
            long r19 = r19 * r17
            long r21 = r21 * r23
            long r6 = r19 + r21
            long r8 = r1.columnsl
            long r8 = r19 + r8
            long r10 = r19 + r2
            float r0 = r4.getFloat(r10)
            r4.setFloat(r8, r0)
            r4.setFloat(r10, r5)
            long r8 = r1.columnsl
            long r8 = r21 + r8
            long r11 = r21 + r2
            float r0 = r4.getFloat(r11)
            r4.setFloat(r8, r0)
            r4.setFloat(r11, r5)
            long r8 = r1.columnsl
            long r8 = r8 + r6
            long r11 = r6 + r2
            float r0 = r4.getFloat(r11)
            r4.setFloat(r8, r0)
            r4.setFloat(r11, r5)
            long r8 = r1.columnsl
            long r21 = r21 + r8
            long r8 = r21 + r2
            r4.setFloat(r8, r5)
            long r8 = r1.columnsl
            long r6 = r6 + r8
            long r6 = r6 + r2
            r4.setFloat(r6, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.FloatFFT_3D.fillSymmetric(pl.edu.icm.jlargearrays.FloatLargeArray):void");
    }
}
