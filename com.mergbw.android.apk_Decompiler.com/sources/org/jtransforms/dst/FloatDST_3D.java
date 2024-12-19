package org.jtransforms.dst;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jtransforms.utils.CommonUtils;
import pl.edu.icm.jlargearrays.ConcurrencyUtils;
import pl.edu.icm.jlargearrays.FloatLargeArray;
import pl.edu.icm.jlargearrays.LargeArray;

public class FloatDST_3D {
    /* access modifiers changed from: private */
    public int columns;
    /* access modifiers changed from: private */
    public long columnsl;
    /* access modifiers changed from: private */
    public FloatDST_1D dstColumns;
    /* access modifiers changed from: private */
    public FloatDST_1D dstRows;
    /* access modifiers changed from: private */
    public FloatDST_1D dstSlices;
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

    public FloatDST_3D(long j, long j2, long j3) {
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
        long j5 = j * j2 * j3;
        if (j5 >= CommonUtils.getThreadsBeginN_3D()) {
            this.useThreads = true;
        }
        if (CommonUtils.isPowerOf2(j) && CommonUtils.isPowerOf2(j2) && CommonUtils.isPowerOf2(j3)) {
            this.isPowerOfTwo = true;
        }
        CommonUtils.setUseLargeArrays(j5 > ((long) LargeArray.getMaxSizeOf32bitArray()) ? true : z);
        FloatDST_1D floatDST_1D = new FloatDST_1D(j);
        this.dstSlices = floatDST_1D;
        if (j == j2) {
            this.dstRows = floatDST_1D;
        } else {
            this.dstRows = new FloatDST_1D(j2);
        }
        if (j == j3) {
            this.dstColumns = this.dstSlices;
        } else if (j2 == j3) {
            this.dstColumns = this.dstRows;
        } else {
            this.dstColumns = new FloatDST_1D(j3);
        }
    }

    public void forward(float[] fArr, boolean z) {
        int i;
        int i2;
        float[] fArr2 = fArr;
        boolean z2 = z;
        Class<FloatDST_3D> cls = FloatDST_3D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (!this.isPowerOfTwo) {
            int i3 = 0;
            if (numberOfThreads <= 1 || !this.useThreads || (i2 = this.slices) < numberOfThreads || this.rows < numberOfThreads || this.columns < numberOfThreads) {
                for (int i4 = 0; i4 < this.slices; i4++) {
                    int i5 = this.sliceStride * i4;
                    for (int i6 = 0; i6 < this.rows; i6++) {
                        this.dstColumns.forward(fArr2, (this.rowStride * i6) + i5, z2);
                    }
                }
                float[] fArr3 = new float[this.rows];
                int i7 = 0;
                while (true) {
                    i = this.slices;
                    if (i7 >= i) {
                        break;
                    }
                    int i8 = this.sliceStride * i7;
                    for (int i9 = 0; i9 < this.columns; i9++) {
                        for (int i10 = 0; i10 < this.rows; i10++) {
                            fArr3[i10] = fArr2[(this.rowStride * i10) + i8 + i9];
                        }
                        this.dstRows.forward(fArr3, z2);
                        for (int i11 = 0; i11 < this.rows; i11++) {
                            fArr2[(this.rowStride * i11) + i8 + i9] = fArr3[i11];
                        }
                    }
                    i7++;
                }
                float[] fArr4 = new float[i];
                for (int i12 = 0; i12 < this.rows; i12++) {
                    int i13 = this.rowStride * i12;
                    for (int i14 = 0; i14 < this.columns; i14++) {
                        for (int i15 = 0; i15 < this.slices; i15++) {
                            fArr4[i15] = fArr2[(this.sliceStride * i15) + i13 + i14];
                        }
                        this.dstSlices.forward(fArr4, z2);
                        for (int i16 = 0; i16 < this.slices; i16++) {
                            fArr2[(this.sliceStride * i16) + i13 + i14] = fArr4[i16];
                        }
                    }
                }
                return;
            }
            Future[] futureArr = new Future[numberOfThreads];
            int i17 = i2 / numberOfThreads;
            int i18 = 0;
            while (i18 < numberOfThreads) {
                final int i19 = i18 * i17;
                final int i20 = i18 == numberOfThreads + -1 ? this.slices : i19 + i17;
                final float[] fArr5 = fArr;
                final boolean z3 = z;
                futureArr[i18] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i19; i < i20; i++) {
                            int access$000 = FloatDST_3D.this.sliceStride * i;
                            for (int i2 = 0; i2 < FloatDST_3D.this.rows; i2++) {
                                FloatDST_3D.this.dstColumns.forward(fArr5, (FloatDST_3D.this.rowStride * i2) + access$000, z3);
                            }
                        }
                    }
                });
                i18++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
            } catch (ExecutionException e2) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
            }
            int i21 = 0;
            while (i21 < numberOfThreads) {
                final int i22 = i21 * i17;
                final int i23 = i21 == numberOfThreads + -1 ? this.slices : i22 + i17;
                final float[] fArr6 = fArr;
                final boolean z4 = z;
                futureArr[i21] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        float[] fArr = new float[FloatDST_3D.this.rows];
                        for (int i = i22; i < i23; i++) {
                            int access$000 = FloatDST_3D.this.sliceStride * i;
                            for (int i2 = 0; i2 < FloatDST_3D.this.columns; i2++) {
                                for (int i3 = 0; i3 < FloatDST_3D.this.rows; i3++) {
                                    fArr[i3] = fArr6[(FloatDST_3D.this.rowStride * i3) + access$000 + i2];
                                }
                                FloatDST_3D.this.dstRows.forward(fArr, z4);
                                for (int i4 = 0; i4 < FloatDST_3D.this.rows; i4++) {
                                    fArr6[(FloatDST_3D.this.rowStride * i4) + access$000 + i2] = fArr[i4];
                                }
                            }
                        }
                    }
                });
                i21++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e3) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e4);
            }
            int i24 = this.rows / numberOfThreads;
            while (i3 < numberOfThreads) {
                final int i25 = i3 * i24;
                final int i26 = i3 == numberOfThreads + -1 ? this.rows : i25 + i24;
                final float[] fArr7 = fArr;
                final boolean z5 = z;
                futureArr[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        float[] fArr = new float[FloatDST_3D.this.slices];
                        for (int i = i25; i < i26; i++) {
                            int access$200 = FloatDST_3D.this.rowStride * i;
                            for (int i2 = 0; i2 < FloatDST_3D.this.columns; i2++) {
                                for (int i3 = 0; i3 < FloatDST_3D.this.slices; i3++) {
                                    fArr[i3] = fArr7[(FloatDST_3D.this.sliceStride * i3) + access$200 + i2];
                                }
                                FloatDST_3D.this.dstSlices.forward(fArr, z5);
                                for (int i4 = 0; i4 < FloatDST_3D.this.slices; i4++) {
                                    fArr7[(FloatDST_3D.this.sliceStride * i4) + access$200 + i2] = fArr[i4];
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
        } else if (numberOfThreads <= 1 || !this.useThreads) {
            ddxt3da_sub(-1, fArr2, z2);
            ddxt3db_sub(-1, fArr2, z2);
        } else {
            ddxt3da_subth(-1, fArr2, z2);
            ddxt3db_subth(-1, fArr2, z2);
        }
    }

    public void forward(FloatLargeArray floatLargeArray, boolean z) {
        FloatLargeArray floatLargeArray2 = floatLargeArray;
        boolean z2 = z;
        Class<FloatDST_3D> cls = FloatDST_3D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (!this.isPowerOfTwo) {
            if (numberOfThreads > 1 && this.useThreads) {
                long j = this.slicesl;
                long j2 = (long) numberOfThreads;
                if (j >= j2 && this.rowsl >= j2 && this.columnsl >= j2) {
                    Future[] futureArr = new Future[numberOfThreads];
                    long j3 = j / j2;
                    int i = 0;
                    while (i < numberOfThreads) {
                        final long j4 = ((long) i) * j3;
                        final long j5 = i == numberOfThreads + -1 ? this.slicesl : j4 + j3;
                        long j6 = j2;
                        final FloatLargeArray floatLargeArray3 = floatLargeArray;
                        final boolean z3 = z;
                        futureArr[i] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                for (long j = j4; j < j5; j++) {
                                    long access$800 = FloatDST_3D.this.sliceStridel * j;
                                    for (long j2 = 0; j2 < FloatDST_3D.this.rowsl; j2++) {
                                        FloatDST_3D.this.dstColumns.forward(floatLargeArray3, (FloatDST_3D.this.rowStridel * j2) + access$800, z3);
                                    }
                                }
                            }
                        });
                        i++;
                        j2 = j6;
                    }
                    long j7 = j2;
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
                        final long j8 = ((long) i2) * j3;
                        final long j9 = i2 == numberOfThreads + -1 ? this.slicesl : j8 + j3;
                        final FloatLargeArray floatLargeArray4 = floatLargeArray;
                        String str2 = str;
                        final boolean z4 = z;
                        futureArr[i2] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                FloatLargeArray floatLargeArray = new FloatLargeArray(FloatDST_3D.this.rowsl, false);
                                for (long j = j8; j < j9; j++) {
                                    long access$800 = FloatDST_3D.this.sliceStridel * j;
                                    for (long j2 = 0; j2 < FloatDST_3D.this.columnsl; j2++) {
                                        for (long j3 = 0; j3 < FloatDST_3D.this.rowsl; j3++) {
                                            floatLargeArray.setFloat(j3, floatLargeArray4.getFloat((FloatDST_3D.this.rowStridel * j3) + access$800 + j2));
                                        }
                                        FloatDST_3D.this.dstRows.forward(floatLargeArray, z4);
                                        for (long j4 = 0; j4 < FloatDST_3D.this.rowsl; j4++) {
                                            floatLargeArray4.setFloat((FloatDST_3D.this.rowStridel * j4) + access$800 + j2, floatLargeArray.getFloat(j4));
                                        }
                                    }
                                }
                            }
                        });
                        i2++;
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
                    long j10 = this.rowsl / j7;
                    int i3 = 0;
                    while (i3 < numberOfThreads) {
                        final long j11 = ((long) i3) * j10;
                        final long j12 = i3 == numberOfThreads + -1 ? this.rowsl : j11 + j10;
                        final FloatLargeArray floatLargeArray5 = floatLargeArray;
                        final boolean z5 = z;
                        futureArr[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                FloatLargeArray floatLargeArray = new FloatLargeArray(FloatDST_3D.this.slicesl, false);
                                for (long j = j11; j < j12; j++) {
                                    long access$1000 = FloatDST_3D.this.rowStridel * j;
                                    for (long j2 = 0; j2 < FloatDST_3D.this.columnsl; j2++) {
                                        for (long j3 = 0; j3 < FloatDST_3D.this.slicesl; j3++) {
                                            floatLargeArray.setFloat(j3, floatLargeArray5.getFloat((FloatDST_3D.this.sliceStridel * j3) + access$1000 + j2));
                                        }
                                        FloatDST_3D.this.dstSlices.forward(floatLargeArray, z5);
                                        for (long j4 = 0; j4 < FloatDST_3D.this.slicesl; j4++) {
                                            floatLargeArray5.setFloat((FloatDST_3D.this.sliceStridel * j4) + access$1000 + j2, floatLargeArray.getFloat(j4));
                                        }
                                    }
                                }
                            }
                        });
                        i3++;
                    }
                    try {
                        ConcurrencyUtils.waitForCompletion(futureArr);
                        return;
                    } catch (InterruptedException e5) {
                        Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e5);
                        return;
                    } catch (ExecutionException e6) {
                        Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e6);
                        return;
                    }
                }
            }
            for (long j13 = 0; j13 < this.slicesl; j13++) {
                long j14 = this.sliceStridel * j13;
                for (long j15 = 0; j15 < this.rowsl; j15++) {
                    this.dstColumns.forward(floatLargeArray2, (this.rowStridel * j15) + j14, z2);
                }
            }
            FloatLargeArray floatLargeArray6 = new FloatLargeArray(this.rowsl, false);
            for (long j16 = 0; j16 < this.slicesl; j16++) {
                long j17 = this.sliceStridel * j16;
                for (long j18 = 0; j18 < this.columnsl; j18++) {
                    for (long j19 = 0; j19 < this.rowsl; j19++) {
                        floatLargeArray6.setFloat(j19, floatLargeArray2.getFloat((this.rowStridel * j19) + j17 + j18));
                    }
                    this.dstRows.forward(floatLargeArray6, z2);
                    long j20 = 0;
                    while (j20 < this.rowsl) {
                        floatLargeArray2.setFloat((this.rowStridel * j20) + j17 + j18, floatLargeArray6.getFloat(j20));
                        j20++;
                        j17 = j17;
                    }
                    long j21 = j17;
                }
            }
            FloatLargeArray floatLargeArray7 = new FloatLargeArray(this.slicesl, false);
            for (long j22 = 0; j22 < this.rowsl; j22++) {
                long j23 = this.rowStridel * j22;
                for (long j24 = 0; j24 < this.columnsl; j24++) {
                    for (long j25 = 0; j25 < this.slicesl; j25++) {
                        floatLargeArray7.setFloat(j25, floatLargeArray2.getFloat((this.sliceStridel * j25) + j23 + j24));
                    }
                    this.dstSlices.forward(floatLargeArray7, z2);
                    long j26 = 0;
                    while (j26 < this.slicesl) {
                        floatLargeArray2.setFloat((this.sliceStridel * j26) + j23 + j24, floatLargeArray7.getFloat(j26));
                        j26++;
                        j23 = j23;
                    }
                    long j27 = j23;
                }
            }
        } else if (numberOfThreads <= 1 || !this.useThreads) {
            ddxt3da_sub(-1, floatLargeArray2, z2);
            ddxt3db_sub(-1, floatLargeArray2, z2);
        } else {
            ddxt3da_subth(-1, floatLargeArray2, z2);
            ddxt3db_subth(-1, floatLargeArray2, z2);
        }
    }

    public void forward(float[][][] fArr, boolean z) {
        int i;
        int i2;
        float[][][] fArr2 = fArr;
        boolean z2 = z;
        Class<FloatDST_3D> cls = FloatDST_3D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (!this.isPowerOfTwo) {
            int i3 = 0;
            if (numberOfThreads <= 1 || !this.useThreads || (i2 = this.slices) < numberOfThreads || this.rows < numberOfThreads || this.columns < numberOfThreads) {
                for (int i4 = 0; i4 < this.slices; i4++) {
                    for (int i5 = 0; i5 < this.rows; i5++) {
                        this.dstColumns.forward(fArr2[i4][i5], z2);
                    }
                }
                float[] fArr3 = new float[this.rows];
                int i6 = 0;
                while (true) {
                    i = this.slices;
                    if (i6 >= i) {
                        break;
                    }
                    for (int i7 = 0; i7 < this.columns; i7++) {
                        for (int i8 = 0; i8 < this.rows; i8++) {
                            fArr3[i8] = fArr2[i6][i8][i7];
                        }
                        this.dstRows.forward(fArr3, z2);
                        for (int i9 = 0; i9 < this.rows; i9++) {
                            fArr2[i6][i9][i7] = fArr3[i9];
                        }
                    }
                    i6++;
                }
                float[] fArr4 = new float[i];
                for (int i10 = 0; i10 < this.rows; i10++) {
                    for (int i11 = 0; i11 < this.columns; i11++) {
                        for (int i12 = 0; i12 < this.slices; i12++) {
                            fArr4[i12] = fArr2[i12][i10][i11];
                        }
                        this.dstSlices.forward(fArr4, z2);
                        for (int i13 = 0; i13 < this.slices; i13++) {
                            fArr2[i13][i10][i11] = fArr4[i13];
                        }
                    }
                }
                return;
            }
            Future[] futureArr = new Future[numberOfThreads];
            int i14 = i2 / numberOfThreads;
            int i15 = 0;
            while (i15 < numberOfThreads) {
                final int i16 = i15 * i14;
                final int i17 = i15 == numberOfThreads + -1 ? this.slices : i16 + i14;
                final float[][][] fArr5 = fArr;
                final boolean z3 = z;
                futureArr[i15] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i16; i < i17; i++) {
                            for (int i2 = 0; i2 < FloatDST_3D.this.rows; i2++) {
                                FloatDST_3D.this.dstColumns.forward(fArr5[i][i2], z3);
                            }
                        }
                    }
                });
                i15++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
            } catch (ExecutionException e2) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
            }
            int i18 = 0;
            while (i18 < numberOfThreads) {
                final int i19 = i18 * i14;
                final int i20 = i18 == numberOfThreads + -1 ? this.slices : i19 + i14;
                final float[][][] fArr6 = fArr;
                final boolean z4 = z;
                futureArr[i18] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        float[] fArr = new float[FloatDST_3D.this.rows];
                        for (int i = i19; i < i20; i++) {
                            for (int i2 = 0; i2 < FloatDST_3D.this.columns; i2++) {
                                for (int i3 = 0; i3 < FloatDST_3D.this.rows; i3++) {
                                    fArr[i3] = fArr6[i][i3][i2];
                                }
                                FloatDST_3D.this.dstRows.forward(fArr, z4);
                                for (int i4 = 0; i4 < FloatDST_3D.this.rows; i4++) {
                                    fArr6[i][i4][i2] = fArr[i4];
                                }
                            }
                        }
                    }
                });
                i18++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e3) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e4);
            }
            int i21 = this.rows / numberOfThreads;
            while (i3 < numberOfThreads) {
                final int i22 = i3 * i21;
                final int i23 = i3 == numberOfThreads + -1 ? this.rows : i22 + i21;
                final float[][][] fArr7 = fArr;
                final boolean z5 = z;
                futureArr[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        float[] fArr = new float[FloatDST_3D.this.slices];
                        for (int i = i22; i < i23; i++) {
                            for (int i2 = 0; i2 < FloatDST_3D.this.columns; i2++) {
                                for (int i3 = 0; i3 < FloatDST_3D.this.slices; i3++) {
                                    fArr[i3] = fArr7[i3][i][i2];
                                }
                                FloatDST_3D.this.dstSlices.forward(fArr, z5);
                                for (int i4 = 0; i4 < FloatDST_3D.this.slices; i4++) {
                                    fArr7[i4][i][i2] = fArr[i4];
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
        } else if (numberOfThreads <= 1 || !this.useThreads) {
            ddxt3da_sub(-1, fArr2, z2);
            ddxt3db_sub(-1, fArr2, z2);
        } else {
            ddxt3da_subth(-1, fArr2, z2);
            ddxt3db_subth(-1, fArr2, z2);
        }
    }

    public void inverse(float[] fArr, boolean z) {
        int i;
        int i2;
        float[] fArr2 = fArr;
        boolean z2 = z;
        Class<FloatDST_3D> cls = FloatDST_3D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (!this.isPowerOfTwo) {
            int i3 = 0;
            if (numberOfThreads <= 1 || !this.useThreads || (i2 = this.slices) < numberOfThreads || this.rows < numberOfThreads || this.columns < numberOfThreads) {
                for (int i4 = 0; i4 < this.slices; i4++) {
                    int i5 = this.sliceStride * i4;
                    for (int i6 = 0; i6 < this.rows; i6++) {
                        this.dstColumns.inverse(fArr2, (this.rowStride * i6) + i5, z2);
                    }
                }
                float[] fArr3 = new float[this.rows];
                int i7 = 0;
                while (true) {
                    i = this.slices;
                    if (i7 >= i) {
                        break;
                    }
                    int i8 = this.sliceStride * i7;
                    for (int i9 = 0; i9 < this.columns; i9++) {
                        for (int i10 = 0; i10 < this.rows; i10++) {
                            fArr3[i10] = fArr2[(this.rowStride * i10) + i8 + i9];
                        }
                        this.dstRows.inverse(fArr3, z2);
                        for (int i11 = 0; i11 < this.rows; i11++) {
                            fArr2[(this.rowStride * i11) + i8 + i9] = fArr3[i11];
                        }
                    }
                    i7++;
                }
                float[] fArr4 = new float[i];
                for (int i12 = 0; i12 < this.rows; i12++) {
                    int i13 = this.rowStride * i12;
                    for (int i14 = 0; i14 < this.columns; i14++) {
                        for (int i15 = 0; i15 < this.slices; i15++) {
                            fArr4[i15] = fArr2[(this.sliceStride * i15) + i13 + i14];
                        }
                        this.dstSlices.inverse(fArr4, z2);
                        for (int i16 = 0; i16 < this.slices; i16++) {
                            fArr2[(this.sliceStride * i16) + i13 + i14] = fArr4[i16];
                        }
                    }
                }
                return;
            }
            Future[] futureArr = new Future[numberOfThreads];
            int i17 = i2 / numberOfThreads;
            int i18 = 0;
            while (i18 < numberOfThreads) {
                final int i19 = i18 * i17;
                final int i20 = i18 == numberOfThreads + -1 ? this.slices : i19 + i17;
                final float[] fArr5 = fArr;
                final boolean z3 = z;
                futureArr[i18] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i19; i < i20; i++) {
                            int access$000 = FloatDST_3D.this.sliceStride * i;
                            for (int i2 = 0; i2 < FloatDST_3D.this.rows; i2++) {
                                FloatDST_3D.this.dstColumns.inverse(fArr5, (FloatDST_3D.this.rowStride * i2) + access$000, z3);
                            }
                        }
                    }
                });
                i18++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
            } catch (ExecutionException e2) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
            }
            int i21 = 0;
            while (i21 < numberOfThreads) {
                final int i22 = i21 * i17;
                final int i23 = i21 == numberOfThreads + -1 ? this.slices : i22 + i17;
                final float[] fArr6 = fArr;
                final boolean z4 = z;
                futureArr[i21] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        float[] fArr = new float[FloatDST_3D.this.rows];
                        for (int i = i22; i < i23; i++) {
                            int access$000 = FloatDST_3D.this.sliceStride * i;
                            for (int i2 = 0; i2 < FloatDST_3D.this.columns; i2++) {
                                for (int i3 = 0; i3 < FloatDST_3D.this.rows; i3++) {
                                    fArr[i3] = fArr6[(FloatDST_3D.this.rowStride * i3) + access$000 + i2];
                                }
                                FloatDST_3D.this.dstRows.inverse(fArr, z4);
                                for (int i4 = 0; i4 < FloatDST_3D.this.rows; i4++) {
                                    fArr6[(FloatDST_3D.this.rowStride * i4) + access$000 + i2] = fArr[i4];
                                }
                            }
                        }
                    }
                });
                i21++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e3) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e4);
            }
            int i24 = this.rows / numberOfThreads;
            while (i3 < numberOfThreads) {
                final int i25 = i3 * i24;
                final int i26 = i3 == numberOfThreads + -1 ? this.rows : i25 + i24;
                final float[] fArr7 = fArr;
                final boolean z5 = z;
                futureArr[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        float[] fArr = new float[FloatDST_3D.this.slices];
                        for (int i = i25; i < i26; i++) {
                            int access$200 = FloatDST_3D.this.rowStride * i;
                            for (int i2 = 0; i2 < FloatDST_3D.this.columns; i2++) {
                                for (int i3 = 0; i3 < FloatDST_3D.this.slices; i3++) {
                                    fArr[i3] = fArr7[(FloatDST_3D.this.sliceStride * i3) + access$200 + i2];
                                }
                                FloatDST_3D.this.dstSlices.inverse(fArr, z5);
                                for (int i4 = 0; i4 < FloatDST_3D.this.slices; i4++) {
                                    fArr7[(FloatDST_3D.this.sliceStride * i4) + access$200 + i2] = fArr[i4];
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
        } else if (numberOfThreads <= 1 || !this.useThreads) {
            ddxt3da_sub(1, fArr2, z2);
            ddxt3db_sub(1, fArr2, z2);
        } else {
            ddxt3da_subth(1, fArr2, z2);
            ddxt3db_subth(1, fArr2, z2);
        }
    }

    public void inverse(FloatLargeArray floatLargeArray, boolean z) {
        FloatLargeArray floatLargeArray2 = floatLargeArray;
        boolean z2 = z;
        Class<FloatDST_3D> cls = FloatDST_3D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (!this.isPowerOfTwo) {
            if (numberOfThreads > 1 && this.useThreads) {
                long j = this.slicesl;
                long j2 = (long) numberOfThreads;
                if (j >= j2 && this.rowsl >= j2 && this.columnsl >= j2) {
                    Future[] futureArr = new Future[numberOfThreads];
                    long j3 = j / j2;
                    int i = 0;
                    while (i < numberOfThreads) {
                        final long j4 = ((long) i) * j3;
                        final long j5 = i == numberOfThreads + -1 ? this.slicesl : j4 + j3;
                        long j6 = j2;
                        final FloatLargeArray floatLargeArray3 = floatLargeArray;
                        final boolean z3 = z;
                        futureArr[i] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                for (long j = j4; j < j5; j++) {
                                    long access$000 = ((long) FloatDST_3D.this.sliceStride) * j;
                                    for (long j2 = 0; j2 < FloatDST_3D.this.rowsl; j2++) {
                                        FloatDST_3D.this.dstColumns.inverse(floatLargeArray3, (((long) FloatDST_3D.this.rowStride) * j2) + access$000, z3);
                                    }
                                }
                            }
                        });
                        i++;
                        j2 = j6;
                    }
                    long j7 = j2;
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
                        final long j8 = ((long) i2) * j3;
                        final long j9 = i2 == numberOfThreads + -1 ? this.slicesl : j8 + j3;
                        final FloatLargeArray floatLargeArray4 = floatLargeArray;
                        String str2 = str;
                        final boolean z4 = z;
                        futureArr[i2] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                FloatLargeArray floatLargeArray = new FloatLargeArray(FloatDST_3D.this.rowsl, false);
                                for (long j = j8; j < j9; j++) {
                                    long access$000 = ((long) FloatDST_3D.this.sliceStride) * j;
                                    for (long j2 = 0; j2 < FloatDST_3D.this.columnsl; j2++) {
                                        for (long j3 = 0; j3 < FloatDST_3D.this.rowsl; j3++) {
                                            floatLargeArray.setFloat(j3, floatLargeArray4.getFloat((((long) FloatDST_3D.this.rowStride) * j3) + access$000 + j2));
                                        }
                                        FloatDST_3D.this.dstRows.inverse(floatLargeArray, z4);
                                        for (long j4 = 0; j4 < FloatDST_3D.this.rowsl; j4++) {
                                            floatLargeArray4.setFloat((((long) FloatDST_3D.this.rowStride) * j4) + access$000 + j2, floatLargeArray.getFloat(j4));
                                        }
                                    }
                                }
                            }
                        });
                        i2++;
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
                    long j10 = this.rowsl / j7;
                    int i3 = 0;
                    while (i3 < numberOfThreads) {
                        final long j11 = ((long) i3) * j10;
                        final long j12 = i3 == numberOfThreads + -1 ? this.rowsl : j11 + j10;
                        final FloatLargeArray floatLargeArray5 = floatLargeArray;
                        final boolean z5 = z;
                        futureArr[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                FloatLargeArray floatLargeArray = new FloatLargeArray(FloatDST_3D.this.slicesl, false);
                                for (long j = j11; j < j12; j++) {
                                    long access$200 = ((long) FloatDST_3D.this.rowStride) * j;
                                    for (long j2 = 0; j2 < FloatDST_3D.this.columnsl; j2++) {
                                        for (long j3 = 0; j3 < FloatDST_3D.this.slicesl; j3++) {
                                            floatLargeArray.setFloat(j3, floatLargeArray5.getFloat((((long) FloatDST_3D.this.sliceStride) * j3) + access$200 + j2));
                                        }
                                        FloatDST_3D.this.dstSlices.inverse(floatLargeArray, z5);
                                        for (long j4 = 0; j4 < FloatDST_3D.this.slicesl; j4++) {
                                            floatLargeArray5.setFloat((((long) FloatDST_3D.this.sliceStride) * j4) + access$200 + j2, floatLargeArray.getFloat(j4));
                                        }
                                    }
                                }
                            }
                        });
                        i3++;
                    }
                    try {
                        ConcurrencyUtils.waitForCompletion(futureArr);
                        return;
                    } catch (InterruptedException e5) {
                        Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e5);
                        return;
                    } catch (ExecutionException e6) {
                        Logger.getLogger(cls.getName()).log(Level.SEVERE, str3, e6);
                        return;
                    }
                }
            }
            for (long j13 = 0; j13 < this.slicesl; j13++) {
                long j14 = ((long) this.sliceStride) * j13;
                for (long j15 = 0; j15 < this.rowsl; j15++) {
                    this.dstColumns.inverse(floatLargeArray2, (((long) this.rowStride) * j15) + j14, z2);
                }
            }
            FloatLargeArray floatLargeArray6 = new FloatLargeArray(this.rowsl, false);
            for (long j16 = 0; j16 < this.slicesl; j16++) {
                long j17 = ((long) this.sliceStride) * j16;
                for (long j18 = 0; j18 < this.columnsl; j18++) {
                    for (long j19 = 0; j19 < this.rowsl; j19++) {
                        floatLargeArray6.setFloat(j19, floatLargeArray2.getFloat((((long) this.rowStride) * j19) + j17 + j18));
                    }
                    this.dstRows.inverse(floatLargeArray6, z2);
                    long j20 = 0;
                    while (j20 < this.rowsl) {
                        floatLargeArray2.setFloat((((long) this.rowStride) * j20) + j17 + j18, floatLargeArray6.getFloat(j20));
                        j20++;
                        j17 = j17;
                    }
                    long j21 = j17;
                }
            }
            FloatLargeArray floatLargeArray7 = new FloatLargeArray(this.slicesl, false);
            for (long j22 = 0; j22 < this.rowsl; j22++) {
                long j23 = ((long) this.rowStride) * j22;
                for (long j24 = 0; j24 < this.columnsl; j24++) {
                    for (long j25 = 0; j25 < this.slicesl; j25++) {
                        floatLargeArray7.setFloat(j25, floatLargeArray2.getFloat((((long) this.sliceStride) * j25) + j23 + j24));
                    }
                    this.dstSlices.inverse(floatLargeArray7, z2);
                    long j26 = 0;
                    while (j26 < this.slicesl) {
                        floatLargeArray2.setFloat((((long) this.sliceStride) * j26) + j23 + j24, floatLargeArray7.getFloat(j26));
                        j26++;
                        j23 = j23;
                    }
                    long j27 = j23;
                }
            }
        } else if (numberOfThreads <= 1 || !this.useThreads) {
            ddxt3da_sub(1, floatLargeArray2, z2);
            ddxt3db_sub(1, floatLargeArray2, z2);
        } else {
            ddxt3da_subth(1, floatLargeArray2, z2);
            ddxt3db_subth(1, floatLargeArray2, z2);
        }
    }

    public void inverse(float[][][] fArr, boolean z) {
        int i;
        int i2;
        float[][][] fArr2 = fArr;
        boolean z2 = z;
        Class<FloatDST_3D> cls = FloatDST_3D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (!this.isPowerOfTwo) {
            int i3 = 0;
            if (numberOfThreads <= 1 || !this.useThreads || (i2 = this.slices) < numberOfThreads || this.rows < numberOfThreads || this.columns < numberOfThreads) {
                for (int i4 = 0; i4 < this.slices; i4++) {
                    for (int i5 = 0; i5 < this.rows; i5++) {
                        this.dstColumns.inverse(fArr2[i4][i5], z2);
                    }
                }
                float[] fArr3 = new float[this.rows];
                int i6 = 0;
                while (true) {
                    i = this.slices;
                    if (i6 >= i) {
                        break;
                    }
                    for (int i7 = 0; i7 < this.columns; i7++) {
                        for (int i8 = 0; i8 < this.rows; i8++) {
                            fArr3[i8] = fArr2[i6][i8][i7];
                        }
                        this.dstRows.inverse(fArr3, z2);
                        for (int i9 = 0; i9 < this.rows; i9++) {
                            fArr2[i6][i9][i7] = fArr3[i9];
                        }
                    }
                    i6++;
                }
                float[] fArr4 = new float[i];
                for (int i10 = 0; i10 < this.rows; i10++) {
                    for (int i11 = 0; i11 < this.columns; i11++) {
                        for (int i12 = 0; i12 < this.slices; i12++) {
                            fArr4[i12] = fArr2[i12][i10][i11];
                        }
                        this.dstSlices.inverse(fArr4, z2);
                        for (int i13 = 0; i13 < this.slices; i13++) {
                            fArr2[i13][i10][i11] = fArr4[i13];
                        }
                    }
                }
                return;
            }
            Future[] futureArr = new Future[numberOfThreads];
            int i14 = i2 / numberOfThreads;
            int i15 = 0;
            while (i15 < numberOfThreads) {
                final int i16 = i15 * i14;
                final int i17 = i15 == numberOfThreads + -1 ? this.slices : i16 + i14;
                final float[][][] fArr5 = fArr;
                final boolean z3 = z;
                futureArr[i15] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (int i = i16; i < i17; i++) {
                            for (int i2 = 0; i2 < FloatDST_3D.this.rows; i2++) {
                                FloatDST_3D.this.dstColumns.inverse(fArr5[i][i2], z3);
                            }
                        }
                    }
                });
                i15++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
            } catch (ExecutionException e2) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
            }
            int i18 = 0;
            while (i18 < numberOfThreads) {
                final int i19 = i18 * i14;
                final int i20 = i18 == numberOfThreads + -1 ? this.slices : i19 + i14;
                final float[][][] fArr6 = fArr;
                final boolean z4 = z;
                futureArr[i18] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        float[] fArr = new float[FloatDST_3D.this.rows];
                        for (int i = i19; i < i20; i++) {
                            for (int i2 = 0; i2 < FloatDST_3D.this.columns; i2++) {
                                for (int i3 = 0; i3 < FloatDST_3D.this.rows; i3++) {
                                    fArr[i3] = fArr6[i][i3][i2];
                                }
                                FloatDST_3D.this.dstRows.inverse(fArr, z4);
                                for (int i4 = 0; i4 < FloatDST_3D.this.rows; i4++) {
                                    fArr6[i][i4][i2] = fArr[i4];
                                }
                            }
                        }
                    }
                });
                i18++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e3) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e4);
            }
            int i21 = this.rows / numberOfThreads;
            while (i3 < numberOfThreads) {
                final int i22 = i3 * i21;
                final int i23 = i3 == numberOfThreads + -1 ? this.rows : i22 + i21;
                final float[][][] fArr7 = fArr;
                final boolean z5 = z;
                futureArr[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        float[] fArr = new float[FloatDST_3D.this.slices];
                        for (int i = i22; i < i23; i++) {
                            for (int i2 = 0; i2 < FloatDST_3D.this.columns; i2++) {
                                for (int i3 = 0; i3 < FloatDST_3D.this.slices; i3++) {
                                    fArr[i3] = fArr7[i3][i][i2];
                                }
                                FloatDST_3D.this.dstSlices.inverse(fArr, z5);
                                for (int i4 = 0; i4 < FloatDST_3D.this.slices; i4++) {
                                    fArr7[i4][i][i2] = fArr[i4];
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
        } else if (numberOfThreads <= 1 || !this.useThreads) {
            ddxt3da_sub(1, fArr2, z2);
            ddxt3db_sub(1, fArr2, z2);
        } else {
            ddxt3da_subth(1, fArr2, z2);
            ddxt3db_subth(1, fArr2, z2);
        }
    }

    private void ddxt3da_sub(int i, float[] fArr, boolean z) {
        int i2 = this.rows * 4;
        if (this.columns == 2) {
            i2 >>= 1;
        }
        float[] fArr2 = new float[i2];
        if (i == -1) {
            for (int i3 = 0; i3 < this.slices; i3++) {
                int i4 = this.sliceStride * i3;
                for (int i5 = 0; i5 < this.rows; i5++) {
                    this.dstColumns.forward(fArr, (this.rowStride * i5) + i4, z);
                }
                int i6 = this.columns;
                if (i6 > 2) {
                    for (int i7 = 0; i7 < this.columns; i7 += 4) {
                        int i8 = 0;
                        while (true) {
                            int i9 = this.rows;
                            if (i8 >= i9) {
                                break;
                            }
                            int i10 = (this.rowStride * i8) + i4 + i7;
                            int i11 = i9 + i8;
                            fArr2[i8] = fArr[i10];
                            fArr2[i11] = fArr[i10 + 1];
                            fArr2[i11 + i9] = fArr[i10 + 2];
                            fArr2[i11 + (i9 * 2)] = fArr[i10 + 3];
                            i8++;
                        }
                        this.dstRows.forward(fArr2, 0, z);
                        this.dstRows.forward(fArr2, this.rows, z);
                        this.dstRows.forward(fArr2, this.rows * 2, z);
                        this.dstRows.forward(fArr2, this.rows * 3, z);
                        int i12 = 0;
                        while (true) {
                            int i13 = this.rows;
                            if (i12 >= i13) {
                                break;
                            }
                            int i14 = (this.rowStride * i12) + i4 + i7;
                            int i15 = i13 + i12;
                            fArr[i14] = fArr2[i12];
                            fArr[i14 + 1] = fArr2[i15];
                            fArr[i14 + 2] = fArr2[i15 + i13];
                            fArr[i14 + 3] = fArr2[i15 + (i13 * 2)];
                            i12++;
                        }
                    }
                } else if (i6 == 2) {
                    int i16 = 0;
                    while (true) {
                        int i17 = this.rows;
                        if (i16 >= i17) {
                            break;
                        }
                        int i18 = (this.rowStride * i16) + i4;
                        fArr2[i16] = fArr[i18];
                        fArr2[i17 + i16] = fArr[i18 + 1];
                        i16++;
                    }
                    this.dstRows.forward(fArr2, 0, z);
                    this.dstRows.forward(fArr2, this.rows, z);
                    int i19 = 0;
                    while (true) {
                        int i20 = this.rows;
                        if (i19 >= i20) {
                            break;
                        }
                        int i21 = (this.rowStride * i19) + i4;
                        fArr[i21] = fArr2[i19];
                        fArr[i21 + 1] = fArr2[i20 + i19];
                        i19++;
                    }
                }
            }
            return;
        }
        for (int i22 = 0; i22 < this.slices; i22++) {
            int i23 = this.sliceStride * i22;
            for (int i24 = 0; i24 < this.rows; i24++) {
                this.dstColumns.inverse(fArr, (this.rowStride * i24) + i23, z);
            }
            int i25 = this.columns;
            if (i25 > 2) {
                for (int i26 = 0; i26 < this.columns; i26 += 4) {
                    int i27 = 0;
                    while (true) {
                        int i28 = this.rows;
                        if (i27 >= i28) {
                            break;
                        }
                        int i29 = (this.rowStride * i27) + i23 + i26;
                        int i30 = i28 + i27;
                        fArr2[i27] = fArr[i29];
                        fArr2[i30] = fArr[i29 + 1];
                        fArr2[i30 + i28] = fArr[i29 + 2];
                        fArr2[i30 + (i28 * 2)] = fArr[i29 + 3];
                        i27++;
                    }
                    this.dstRows.inverse(fArr2, 0, z);
                    this.dstRows.inverse(fArr2, this.rows, z);
                    this.dstRows.inverse(fArr2, this.rows * 2, z);
                    this.dstRows.inverse(fArr2, this.rows * 3, z);
                    int i31 = 0;
                    while (true) {
                        int i32 = this.rows;
                        if (i31 >= i32) {
                            break;
                        }
                        int i33 = (this.rowStride * i31) + i23 + i26;
                        int i34 = i32 + i31;
                        fArr[i33] = fArr2[i31];
                        fArr[i33 + 1] = fArr2[i34];
                        fArr[i33 + 2] = fArr2[i34 + i32];
                        fArr[i33 + 3] = fArr2[i34 + (i32 * 2)];
                        i31++;
                    }
                }
            } else if (i25 == 2) {
                int i35 = 0;
                while (true) {
                    int i36 = this.rows;
                    if (i35 >= i36) {
                        break;
                    }
                    int i37 = (this.rowStride * i35) + i23;
                    fArr2[i35] = fArr[i37];
                    fArr2[i36 + i35] = fArr[i37 + 1];
                    i35++;
                }
                this.dstRows.inverse(fArr2, 0, z);
                this.dstRows.inverse(fArr2, this.rows, z);
                int i38 = 0;
                while (true) {
                    int i39 = this.rows;
                    if (i38 >= i39) {
                        break;
                    }
                    int i40 = (this.rowStride * i38) + i23;
                    fArr[i40] = fArr2[i38];
                    fArr[i40 + 1] = fArr2[i39 + i38];
                    i38++;
                }
            }
        }
    }

    private void ddxt3da_sub(int i, FloatLargeArray floatLargeArray, boolean z) {
        long j;
        boolean z2;
        long j2;
        FloatLargeArray floatLargeArray2 = floatLargeArray;
        boolean z3 = z;
        long j3 = this.rowsl * 4;
        long j4 = 2;
        if (this.columnsl == 2) {
            j3 >>= 1;
        }
        FloatLargeArray floatLargeArray3 = new FloatLargeArray(j3);
        if (i == -1) {
            long j5 = 0;
            while (j5 < this.slicesl) {
                long j6 = this.sliceStridel * j5;
                for (long j7 = 0; j7 < this.rowsl; j7++) {
                    this.dstColumns.forward(floatLargeArray2, (((long) this.rowStride) * j7) + j6, z3);
                }
                long j8 = this.columnsl;
                if (j8 > j4) {
                    long j9 = 0;
                    while (j9 < this.columnsl) {
                        long j10 = 0;
                        while (true) {
                            long j11 = this.rowsl;
                            if (j10 >= j11) {
                                break;
                            }
                            long j12 = j5;
                            long j13 = (this.rowStridel * j10) + j6 + j9;
                            long j14 = j11 + j10;
                            floatLargeArray3.setFloat(j10, floatLargeArray2.getFloat(j13));
                            floatLargeArray3.setFloat(j14, floatLargeArray2.getFloat(j13 + 1));
                            floatLargeArray3.setFloat(this.rowsl + j14, floatLargeArray2.getFloat(j13 + 2));
                            floatLargeArray3.setFloat(j14 + (this.rowsl * 2), floatLargeArray2.getFloat(j13 + 3));
                            j10++;
                            j5 = j12;
                            j9 = j9;
                            j6 = j6;
                        }
                        long j15 = j5;
                        long j16 = j6;
                        long j17 = j9;
                        this.dstRows.forward(floatLargeArray3, 0, z3);
                        this.dstRows.forward(floatLargeArray3, this.rowsl, z3);
                        this.dstRows.forward(floatLargeArray3, this.rowsl * 2, z3);
                        this.dstRows.forward(floatLargeArray3, this.rowsl * 3, z3);
                        long j18 = 0;
                        while (true) {
                            long j19 = this.rowsl;
                            if (j18 >= j19) {
                                break;
                            }
                            long j20 = j16 + (this.rowStridel * j18) + j17;
                            long j21 = j19 + j18;
                            floatLargeArray2.setFloat(j20, floatLargeArray3.getFloat(j18));
                            floatLargeArray2.setFloat(j20 + 1, floatLargeArray3.getFloat(j21));
                            floatLargeArray2.setFloat(j20 + 2, floatLargeArray3.getFloat(this.rowsl + j21));
                            floatLargeArray2.setFloat(j20 + 3, floatLargeArray3.getFloat(j21 + (this.rowsl * 2)));
                            j18++;
                        }
                        j9 = j17 + 4;
                        j5 = j15;
                        j6 = j16;
                    }
                    j2 = j5;
                } else {
                    j2 = j5;
                    long j22 = j6;
                    if (j8 == j4) {
                        for (long j23 = 0; j23 < this.rowsl; j23++) {
                            long j24 = j22 + (this.rowStridel * j23);
                            floatLargeArray3.setFloat(j23, floatLargeArray2.getFloat(j24));
                            floatLargeArray3.setFloat(this.rowsl + j23, floatLargeArray2.getFloat(j24 + 1));
                        }
                        this.dstRows.forward(floatLargeArray3, 0, z3);
                        this.dstRows.forward(floatLargeArray3, this.rowsl, z3);
                        for (long j25 = 0; j25 < this.rowsl; j25++) {
                            long j26 = j22 + (this.rowStridel * j25);
                            floatLargeArray2.setFloat(j26, floatLargeArray3.getFloat(j25));
                            floatLargeArray2.setFloat(j26 + 1, floatLargeArray3.getFloat(this.rowsl + j25));
                        }
                    }
                }
                j5 = j2 + 1;
                j4 = 2;
            }
            return;
        }
        long j27 = 0;
        while (j27 < this.slicesl) {
            long j28 = this.sliceStridel * j27;
            for (long j29 = 0; j29 < this.rowsl; j29++) {
                this.dstColumns.inverse(floatLargeArray2, (this.rowStridel * j29) + j28, z3);
            }
            long j30 = this.columnsl;
            if (j30 > 2) {
                long j31 = 0;
                while (true) {
                    j = j27;
                    if (j31 >= this.columnsl) {
                        break;
                    }
                    long j32 = 0;
                    while (true) {
                        long j33 = this.rowsl;
                        if (j32 >= j33) {
                            break;
                        }
                        long j34 = (this.rowStridel * j32) + j28 + j31;
                        long j35 = j33 + j32;
                        floatLargeArray3.setFloat(j32, floatLargeArray2.getFloat(j34));
                        floatLargeArray3.setFloat(j35, floatLargeArray2.getFloat(j34 + 1));
                        floatLargeArray3.setFloat(this.rowsl + j35, floatLargeArray2.getFloat(j34 + 2));
                        floatLargeArray3.setFloat(j35 + (this.rowsl * 2), floatLargeArray2.getFloat(j34 + 3));
                        j32++;
                        j31 = j31;
                        j28 = j28;
                    }
                    long j36 = j28;
                    long j37 = j31;
                    this.dstRows.inverse(floatLargeArray3, 0, z3);
                    this.dstRows.inverse(floatLargeArray3, this.rowsl, z3);
                    this.dstRows.inverse(floatLargeArray3, this.rowsl * 2, z3);
                    this.dstRows.inverse(floatLargeArray3, this.rowsl * 3, z3);
                    long j38 = 0;
                    while (true) {
                        long j39 = this.rowsl;
                        if (j38 >= j39) {
                            break;
                        }
                        long j40 = j36 + (this.rowStridel * j38) + j37;
                        long j41 = j39 + j38;
                        floatLargeArray2.setFloat(j40, floatLargeArray3.getFloat(j38));
                        floatLargeArray2.setFloat(j40 + 1, floatLargeArray3.getFloat(j41));
                        floatLargeArray2.setFloat(j40 + 2, floatLargeArray3.getFloat(this.rowsl + j41));
                        floatLargeArray2.setFloat(j40 + 3, floatLargeArray3.getFloat(j41 + (this.rowsl * 2)));
                        j38++;
                        boolean z4 = z;
                    }
                    j31 = j37 + 4;
                    z3 = z;
                    j27 = j;
                    j28 = j36;
                }
            } else {
                j = j27;
                long j42 = j28;
                if (j30 == 2) {
                    for (long j43 = 0; j43 < this.rowsl; j43++) {
                        long j44 = j42 + (this.rowStridel * j43);
                        floatLargeArray3.setFloat(j43, floatLargeArray2.getFloat(j44));
                        floatLargeArray3.setFloat(this.rowsl + j43, floatLargeArray2.getFloat(j44 + 1));
                    }
                    z2 = z;
                    this.dstRows.inverse(floatLargeArray3, 0, z2);
                    this.dstRows.inverse(floatLargeArray3, this.rowsl, z2);
                    for (long j45 = 0; j45 < this.rowsl; j45++) {
                        long j46 = j42 + (this.rowStridel * j45);
                        floatLargeArray2.setFloat(j46, floatLargeArray3.getFloat(j45));
                        floatLargeArray2.setFloat(j46 + 1, floatLargeArray3.getFloat(this.rowsl + j45));
                    }
                    j27 = j + 1;
                    z3 = z2;
                }
            }
            z2 = z;
            j27 = j + 1;
            z3 = z2;
        }
    }

    private void ddxt3da_sub(int i, float[][][] fArr, boolean z) {
        int i2 = this.rows * 4;
        if (this.columns == 2) {
            i2 >>= 1;
        }
        float[] fArr2 = new float[i2];
        if (i == -1) {
            for (int i3 = 0; i3 < this.slices; i3++) {
                for (int i4 = 0; i4 < this.rows; i4++) {
                    this.dstColumns.forward(fArr[i3][i4], z);
                }
                int i5 = this.columns;
                if (i5 > 2) {
                    for (int i6 = 0; i6 < this.columns; i6 += 4) {
                        int i7 = 0;
                        while (true) {
                            int i8 = this.rows;
                            if (i7 >= i8) {
                                break;
                            }
                            int i9 = i8 + i7;
                            float[] fArr3 = fArr[i3][i7];
                            fArr2[i7] = fArr3[i6];
                            fArr2[i9] = fArr3[i6 + 1];
                            fArr2[i9 + i8] = fArr3[i6 + 2];
                            fArr2[i9 + (i8 * 2)] = fArr3[i6 + 3];
                            i7++;
                        }
                        this.dstRows.forward(fArr2, 0, z);
                        this.dstRows.forward(fArr2, this.rows, z);
                        this.dstRows.forward(fArr2, this.rows * 2, z);
                        this.dstRows.forward(fArr2, this.rows * 3, z);
                        int i10 = 0;
                        while (true) {
                            int i11 = this.rows;
                            if (i10 >= i11) {
                                break;
                            }
                            int i12 = i11 + i10;
                            float[] fArr4 = fArr[i3][i10];
                            fArr4[i6] = fArr2[i10];
                            fArr4[i6 + 1] = fArr2[i12];
                            fArr4[i6 + 2] = fArr2[i12 + i11];
                            fArr4[i6 + 3] = fArr2[i12 + (i11 * 2)];
                            i10++;
                        }
                    }
                } else if (i5 == 2) {
                    int i13 = 0;
                    while (true) {
                        int i14 = this.rows;
                        if (i13 >= i14) {
                            break;
                        }
                        float[] fArr5 = fArr[i3][i13];
                        fArr2[i13] = fArr5[0];
                        fArr2[i14 + i13] = fArr5[1];
                        i13++;
                    }
                    this.dstRows.forward(fArr2, 0, z);
                    this.dstRows.forward(fArr2, this.rows, z);
                    int i15 = 0;
                    while (true) {
                        int i16 = this.rows;
                        if (i15 >= i16) {
                            break;
                        }
                        float[] fArr6 = fArr[i3][i15];
                        fArr6[0] = fArr2[i15];
                        fArr6[1] = fArr2[i16 + i15];
                        i15++;
                    }
                }
            }
            return;
        }
        for (int i17 = 0; i17 < this.slices; i17++) {
            for (int i18 = 0; i18 < this.rows; i18++) {
                this.dstColumns.inverse(fArr[i17][i18], z);
            }
            int i19 = this.columns;
            if (i19 > 2) {
                for (int i20 = 0; i20 < this.columns; i20 += 4) {
                    int i21 = 0;
                    while (true) {
                        int i22 = this.rows;
                        if (i21 >= i22) {
                            break;
                        }
                        int i23 = i22 + i21;
                        float[] fArr7 = fArr[i17][i21];
                        fArr2[i21] = fArr7[i20];
                        fArr2[i23] = fArr7[i20 + 1];
                        fArr2[i23 + i22] = fArr7[i20 + 2];
                        fArr2[i23 + (i22 * 2)] = fArr7[i20 + 3];
                        i21++;
                    }
                    this.dstRows.inverse(fArr2, 0, z);
                    this.dstRows.inverse(fArr2, this.rows, z);
                    this.dstRows.inverse(fArr2, this.rows * 2, z);
                    this.dstRows.inverse(fArr2, this.rows * 3, z);
                    int i24 = 0;
                    while (true) {
                        int i25 = this.rows;
                        if (i24 >= i25) {
                            break;
                        }
                        int i26 = i25 + i24;
                        float[] fArr8 = fArr[i17][i24];
                        fArr8[i20] = fArr2[i24];
                        fArr8[i20 + 1] = fArr2[i26];
                        fArr8[i20 + 2] = fArr2[i26 + i25];
                        fArr8[i20 + 3] = fArr2[i26 + (i25 * 2)];
                        i24++;
                    }
                }
            } else if (i19 == 2) {
                int i27 = 0;
                while (true) {
                    int i28 = this.rows;
                    if (i27 >= i28) {
                        break;
                    }
                    float[] fArr9 = fArr[i17][i27];
                    fArr2[i27] = fArr9[0];
                    fArr2[i28 + i27] = fArr9[1];
                    i27++;
                }
                this.dstRows.inverse(fArr2, 0, z);
                this.dstRows.inverse(fArr2, this.rows, z);
                int i29 = 0;
                while (true) {
                    int i30 = this.rows;
                    if (i29 >= i30) {
                        break;
                    }
                    float[] fArr10 = fArr[i17][i29];
                    fArr10[0] = fArr2[i29];
                    fArr10[1] = fArr2[i30 + i29];
                    i29++;
                }
            }
        }
    }

    private void ddxt3db_sub(int i, float[] fArr, boolean z) {
        int i2 = this.slices * 4;
        int i3 = this.columns;
        if (i3 == 2) {
            i2 >>= 1;
        }
        float[] fArr2 = new float[i2];
        if (i == -1) {
            if (i3 > 2) {
                for (int i4 = 0; i4 < this.rows; i4++) {
                    int i5 = this.rowStride * i4;
                    for (int i6 = 0; i6 < this.columns; i6 += 4) {
                        int i7 = 0;
                        while (true) {
                            int i8 = this.slices;
                            if (i7 >= i8) {
                                break;
                            }
                            int i9 = (this.sliceStride * i7) + i5 + i6;
                            int i10 = i8 + i7;
                            fArr2[i7] = fArr[i9];
                            fArr2[i10] = fArr[i9 + 1];
                            fArr2[i10 + i8] = fArr[i9 + 2];
                            fArr2[i10 + (i8 * 2)] = fArr[i9 + 3];
                            i7++;
                        }
                        this.dstSlices.forward(fArr2, 0, z);
                        this.dstSlices.forward(fArr2, this.slices, z);
                        this.dstSlices.forward(fArr2, this.slices * 2, z);
                        this.dstSlices.forward(fArr2, this.slices * 3, z);
                        int i11 = 0;
                        while (true) {
                            int i12 = this.slices;
                            if (i11 >= i12) {
                                break;
                            }
                            int i13 = (this.sliceStride * i11) + i5 + i6;
                            int i14 = i12 + i11;
                            fArr[i13] = fArr2[i11];
                            fArr[i13 + 1] = fArr2[i14];
                            fArr[i13 + 2] = fArr2[i14 + i12];
                            fArr[i13 + 3] = fArr2[i14 + (i12 * 2)];
                            i11++;
                        }
                    }
                }
            } else if (i3 == 2) {
                for (int i15 = 0; i15 < this.rows; i15++) {
                    int i16 = this.rowStride * i15;
                    int i17 = 0;
                    while (true) {
                        int i18 = this.slices;
                        if (i17 >= i18) {
                            break;
                        }
                        int i19 = (this.sliceStride * i17) + i16;
                        fArr2[i17] = fArr[i19];
                        fArr2[i18 + i17] = fArr[i19 + 1];
                        i17++;
                    }
                    this.dstSlices.forward(fArr2, 0, z);
                    this.dstSlices.forward(fArr2, this.slices, z);
                    int i20 = 0;
                    while (true) {
                        int i21 = this.slices;
                        if (i20 >= i21) {
                            break;
                        }
                        int i22 = (this.sliceStride * i20) + i16;
                        fArr[i22] = fArr2[i20];
                        fArr[i22 + 1] = fArr2[i21 + i20];
                        i20++;
                    }
                }
            }
        } else if (i3 > 2) {
            for (int i23 = 0; i23 < this.rows; i23++) {
                int i24 = this.rowStride * i23;
                for (int i25 = 0; i25 < this.columns; i25 += 4) {
                    int i26 = 0;
                    while (true) {
                        int i27 = this.slices;
                        if (i26 >= i27) {
                            break;
                        }
                        int i28 = (this.sliceStride * i26) + i24 + i25;
                        int i29 = i27 + i26;
                        fArr2[i26] = fArr[i28];
                        fArr2[i29] = fArr[i28 + 1];
                        fArr2[i29 + i27] = fArr[i28 + 2];
                        fArr2[i29 + (i27 * 2)] = fArr[i28 + 3];
                        i26++;
                    }
                    this.dstSlices.inverse(fArr2, 0, z);
                    this.dstSlices.inverse(fArr2, this.slices, z);
                    this.dstSlices.inverse(fArr2, this.slices * 2, z);
                    this.dstSlices.inverse(fArr2, this.slices * 3, z);
                    int i30 = 0;
                    while (true) {
                        int i31 = this.slices;
                        if (i30 >= i31) {
                            break;
                        }
                        int i32 = (this.sliceStride * i30) + i24 + i25;
                        int i33 = i31 + i30;
                        fArr[i32] = fArr2[i30];
                        fArr[i32 + 1] = fArr2[i33];
                        fArr[i32 + 2] = fArr2[i33 + i31];
                        fArr[i32 + 3] = fArr2[i33 + (i31 * 2)];
                        i30++;
                    }
                }
            }
        } else if (i3 == 2) {
            for (int i34 = 0; i34 < this.rows; i34++) {
                int i35 = this.rowStride * i34;
                int i36 = 0;
                while (true) {
                    int i37 = this.slices;
                    if (i36 >= i37) {
                        break;
                    }
                    int i38 = (this.sliceStride * i36) + i35;
                    fArr2[i36] = fArr[i38];
                    fArr2[i37 + i36] = fArr[i38 + 1];
                    i36++;
                }
                this.dstSlices.inverse(fArr2, 0, z);
                this.dstSlices.inverse(fArr2, this.slices, z);
                int i39 = 0;
                while (true) {
                    int i40 = this.slices;
                    if (i39 >= i40) {
                        break;
                    }
                    int i41 = (this.sliceStride * i39) + i35;
                    fArr[i41] = fArr2[i39];
                    fArr[i41 + 1] = fArr2[i40 + i39];
                    i39++;
                }
            }
        }
    }

    private void ddxt3db_sub(int i, FloatLargeArray floatLargeArray, boolean z) {
        FloatLargeArray floatLargeArray2 = floatLargeArray;
        boolean z2 = z;
        long j = this.slicesl * 4;
        long j2 = 2;
        if (this.columnsl == 2) {
            j >>= 1;
        }
        FloatLargeArray floatLargeArray3 = new FloatLargeArray(j);
        if (i == -1) {
            long j3 = this.columnsl;
            if (j3 > 2) {
                long j4 = 0;
                while (j4 < this.rowsl) {
                    long j5 = this.rowStridel * j4;
                    long j6 = 0;
                    while (j6 < this.columnsl) {
                        long j7 = 0;
                        while (true) {
                            long j8 = this.slicesl;
                            if (j7 >= j8) {
                                break;
                            }
                            long j9 = (this.sliceStridel * j7) + j5 + j6;
                            long j10 = j8 + j7;
                            floatLargeArray3.setFloat(j7, floatLargeArray2.getFloat(j9));
                            floatLargeArray3.setFloat(j10, floatLargeArray2.getFloat(j9 + 1));
                            floatLargeArray3.setFloat(this.slicesl + j10, floatLargeArray2.getFloat(j9 + 2));
                            floatLargeArray3.setFloat(j10 + (this.slicesl * 2), floatLargeArray2.getFloat(j9 + 3));
                            j7++;
                            j4 = j4;
                            j5 = j5;
                        }
                        long j11 = j4;
                        long j12 = j5;
                        this.dstSlices.forward(floatLargeArray3, 0, z2);
                        this.dstSlices.forward(floatLargeArray3, this.slicesl, z2);
                        this.dstSlices.forward(floatLargeArray3, this.slicesl * 2, z2);
                        this.dstSlices.forward(floatLargeArray3, this.slicesl * 3, z2);
                        long j13 = 0;
                        while (true) {
                            long j14 = this.slicesl;
                            if (j13 >= j14) {
                                break;
                            }
                            long j15 = (this.sliceStridel * j13) + j12 + j6;
                            long j16 = j14 + j13;
                            floatLargeArray2.setFloat(j15, floatLargeArray3.getFloat(j13));
                            floatLargeArray2.setFloat(j15 + 1, floatLargeArray3.getFloat(j16));
                            floatLargeArray2.setFloat(j15 + 2, floatLargeArray3.getFloat(this.slicesl + j16));
                            floatLargeArray2.setFloat(j15 + 3, floatLargeArray3.getFloat(j16 + (this.slicesl * 2)));
                            j13++;
                        }
                        j6 += 4;
                        j2 = 2;
                        j4 = j11;
                        j5 = j12;
                    }
                    long j17 = j2;
                    j4++;
                }
            } else if (j3 == 2) {
                for (long j18 = 0; j18 < this.rowsl; j18++) {
                    long j19 = this.rowStridel * j18;
                    for (long j20 = 0; j20 < this.slicesl; j20++) {
                        long j21 = (this.sliceStridel * j20) + j19;
                        floatLargeArray3.setFloat(j20, floatLargeArray2.getFloat(j21));
                        floatLargeArray3.setFloat(this.slicesl + j20, floatLargeArray2.getFloat(j21 + 1));
                    }
                    this.dstSlices.forward(floatLargeArray3, 0, z2);
                    this.dstSlices.forward(floatLargeArray3, this.slicesl, z2);
                    for (long j22 = 0; j22 < this.slicesl; j22++) {
                        long j23 = (this.sliceStridel * j22) + j19;
                        floatLargeArray2.setFloat(j23, floatLargeArray3.getFloat(j22));
                        floatLargeArray2.setFloat(j23 + 1, floatLargeArray3.getFloat(this.slicesl + j22));
                    }
                }
            }
        } else {
            long j24 = this.columnsl;
            if (j24 > 2) {
                long j25 = 0;
                while (j25 < this.rowsl) {
                    long j26 = this.rowStridel * j25;
                    long j27 = 0;
                    while (j27 < this.columnsl) {
                        long j28 = j25;
                        long j29 = 0;
                        while (true) {
                            long j30 = this.slicesl;
                            if (j29 >= j30) {
                                break;
                            }
                            long j31 = (this.sliceStridel * j29) + j26 + j27;
                            long j32 = j30 + j29;
                            floatLargeArray3.setFloat(j29, floatLargeArray2.getFloat(j31));
                            floatLargeArray3.setFloat(j32, floatLargeArray2.getFloat(j31 + 1));
                            floatLargeArray3.setFloat(this.slicesl + j32, floatLargeArray2.getFloat(j31 + 2));
                            floatLargeArray3.setFloat(j32 + (this.slicesl * 2), floatLargeArray2.getFloat(j31 + 3));
                            j29++;
                            j27 = j27;
                            j26 = j26;
                        }
                        long j33 = j26;
                        long j34 = j27;
                        this.dstSlices.inverse(floatLargeArray3, 0, z2);
                        this.dstSlices.inverse(floatLargeArray3, this.slicesl, z2);
                        this.dstSlices.inverse(floatLargeArray3, this.slicesl * 2, z2);
                        this.dstSlices.inverse(floatLargeArray3, this.slicesl * 3, z2);
                        long j35 = 0;
                        while (true) {
                            long j36 = this.slicesl;
                            if (j35 >= j36) {
                                break;
                            }
                            long j37 = (this.sliceStridel * j35) + j33 + j34;
                            long j38 = j36 + j35;
                            floatLargeArray2.setFloat(j37, floatLargeArray3.getFloat(j35));
                            floatLargeArray2.setFloat(j37 + 1, floatLargeArray3.getFloat(j38));
                            floatLargeArray2.setFloat(j37 + 2, floatLargeArray3.getFloat(this.slicesl + j38));
                            floatLargeArray2.setFloat(j37 + 3, floatLargeArray3.getFloat(j38 + (this.slicesl * 2)));
                            j35++;
                            boolean z3 = z;
                        }
                        j27 = j34 + 4;
                        z2 = z;
                        j25 = j28;
                        j26 = j33;
                    }
                    z2 = z;
                    j25++;
                }
            } else if (j24 == 2) {
                for (long j39 = 0; j39 < this.rowsl; j39++) {
                    long j40 = this.rowStridel * j39;
                    for (long j41 = 0; j41 < this.slicesl; j41++) {
                        long j42 = (this.sliceStridel * j41) + j40;
                        floatLargeArray3.setFloat(j41, floatLargeArray2.getFloat(j42));
                        floatLargeArray3.setFloat(this.slicesl + j41, floatLargeArray2.getFloat(j42 + 1));
                    }
                    boolean z4 = z;
                    this.dstSlices.inverse(floatLargeArray3, 0, z4);
                    this.dstSlices.inverse(floatLargeArray3, this.slicesl, z4);
                    for (long j43 = 0; j43 < this.slicesl; j43++) {
                        long j44 = (this.sliceStridel * j43) + j40;
                        floatLargeArray2.setFloat(j44, floatLargeArray3.getFloat(j43));
                        floatLargeArray2.setFloat(j44 + 1, floatLargeArray3.getFloat(this.slicesl + j43));
                    }
                }
            }
        }
    }

    private void ddxt3db_sub(int i, float[][][] fArr, boolean z) {
        int i2 = this.slices * 4;
        int i3 = this.columns;
        if (i3 == 2) {
            i2 >>= 1;
        }
        float[] fArr2 = new float[i2];
        if (i == -1) {
            if (i3 > 2) {
                for (int i4 = 0; i4 < this.rows; i4++) {
                    for (int i5 = 0; i5 < this.columns; i5 += 4) {
                        int i6 = 0;
                        while (true) {
                            int i7 = this.slices;
                            if (i6 >= i7) {
                                break;
                            }
                            int i8 = i7 + i6;
                            float[] fArr3 = fArr[i6][i4];
                            fArr2[i6] = fArr3[i5];
                            fArr2[i8] = fArr3[i5 + 1];
                            fArr2[i8 + i7] = fArr3[i5 + 2];
                            fArr2[i8 + (i7 * 2)] = fArr3[i5 + 3];
                            i6++;
                        }
                        this.dstSlices.forward(fArr2, 0, z);
                        this.dstSlices.forward(fArr2, this.slices, z);
                        this.dstSlices.forward(fArr2, this.slices * 2, z);
                        this.dstSlices.forward(fArr2, this.slices * 3, z);
                        int i9 = 0;
                        while (true) {
                            int i10 = this.slices;
                            if (i9 >= i10) {
                                break;
                            }
                            int i11 = i10 + i9;
                            float[] fArr4 = fArr[i9][i4];
                            fArr4[i5] = fArr2[i9];
                            fArr4[i5 + 1] = fArr2[i11];
                            fArr4[i5 + 2] = fArr2[i11 + i10];
                            fArr4[i5 + 3] = fArr2[i11 + (i10 * 2)];
                            i9++;
                        }
                    }
                }
            } else if (i3 == 2) {
                for (int i12 = 0; i12 < this.rows; i12++) {
                    int i13 = 0;
                    while (true) {
                        int i14 = this.slices;
                        if (i13 >= i14) {
                            break;
                        }
                        float[] fArr5 = fArr[i13][i12];
                        fArr2[i13] = fArr5[0];
                        fArr2[i14 + i13] = fArr5[1];
                        i13++;
                    }
                    this.dstSlices.forward(fArr2, 0, z);
                    this.dstSlices.forward(fArr2, this.slices, z);
                    int i15 = 0;
                    while (true) {
                        int i16 = this.slices;
                        if (i15 >= i16) {
                            break;
                        }
                        float[] fArr6 = fArr[i15][i12];
                        fArr6[0] = fArr2[i15];
                        fArr6[1] = fArr2[i16 + i15];
                        i15++;
                    }
                }
            }
        } else if (i3 > 2) {
            for (int i17 = 0; i17 < this.rows; i17++) {
                for (int i18 = 0; i18 < this.columns; i18 += 4) {
                    int i19 = 0;
                    while (true) {
                        int i20 = this.slices;
                        if (i19 >= i20) {
                            break;
                        }
                        int i21 = i20 + i19;
                        float[] fArr7 = fArr[i19][i17];
                        fArr2[i19] = fArr7[i18];
                        fArr2[i21] = fArr7[i18 + 1];
                        fArr2[i21 + i20] = fArr7[i18 + 2];
                        fArr2[i21 + (i20 * 2)] = fArr7[i18 + 3];
                        i19++;
                    }
                    this.dstSlices.inverse(fArr2, 0, z);
                    this.dstSlices.inverse(fArr2, this.slices, z);
                    this.dstSlices.inverse(fArr2, this.slices * 2, z);
                    this.dstSlices.inverse(fArr2, this.slices * 3, z);
                    int i22 = 0;
                    while (true) {
                        int i23 = this.slices;
                        if (i22 >= i23) {
                            break;
                        }
                        int i24 = i23 + i22;
                        float[] fArr8 = fArr[i22][i17];
                        fArr8[i18] = fArr2[i22];
                        fArr8[i18 + 1] = fArr2[i24];
                        fArr8[i18 + 2] = fArr2[i24 + i23];
                        fArr8[i18 + 3] = fArr2[i24 + (i23 * 2)];
                        i22++;
                    }
                }
            }
        } else if (i3 == 2) {
            for (int i25 = 0; i25 < this.rows; i25++) {
                int i26 = 0;
                while (true) {
                    int i27 = this.slices;
                    if (i26 >= i27) {
                        break;
                    }
                    float[] fArr9 = fArr[i26][i25];
                    fArr2[i26] = fArr9[0];
                    fArr2[i27 + i26] = fArr9[1];
                    i26++;
                }
                this.dstSlices.inverse(fArr2, 0, z);
                this.dstSlices.inverse(fArr2, this.slices, z);
                int i28 = 0;
                while (true) {
                    int i29 = this.slices;
                    if (i28 >= i29) {
                        break;
                    }
                    float[] fArr10 = fArr[i28][i25];
                    fArr10[0] = fArr2[i28];
                    fArr10[1] = fArr2[i29 + i28];
                    i28++;
                }
            }
        }
    }

    private void ddxt3da_subth(int i, float[] fArr, boolean z) {
        Class<FloatDST_3D> cls = FloatDST_3D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i2 = this.slices;
        if (numberOfThreads <= i2) {
            i2 = ConcurrencyUtils.getNumberOfThreads();
        }
        int i3 = i2;
        int i4 = this.rows * 4;
        if (this.columns == 2) {
            i4 >>= 1;
        }
        int i5 = i4;
        Future[] futureArr = new Future[i3];
        for (int i6 = 0; i6 < i3; i6++) {
            final int i7 = i5;
            final int i8 = i;
            final int i9 = i6;
            final int i10 = i3;
            final float[] fArr2 = fArr;
            final boolean z2 = z;
            futureArr[i6] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    float[] fArr = new float[i7];
                    if (i8 == -1) {
                        int i = i9;
                        while (i < FloatDST_3D.this.slices) {
                            int access$000 = FloatDST_3D.this.sliceStride * i;
                            for (int i2 = 0; i2 < FloatDST_3D.this.rows; i2++) {
                                FloatDST_3D.this.dstColumns.forward(fArr2, (FloatDST_3D.this.rowStride * i2) + access$000, z2);
                            }
                            if (FloatDST_3D.this.columns > 2) {
                                for (int i3 = 0; i3 < FloatDST_3D.this.columns; i3 += 4) {
                                    for (int i4 = 0; i4 < FloatDST_3D.this.rows; i4++) {
                                        int access$200 = (FloatDST_3D.this.rowStride * i4) + access$000 + i3;
                                        int access$100 = FloatDST_3D.this.rows + i4;
                                        float[] fArr2 = fArr2;
                                        fArr[i4] = fArr2[access$200];
                                        fArr[access$100] = fArr2[access$200 + 1];
                                        fArr[FloatDST_3D.this.rows + access$100] = fArr2[access$200 + 2];
                                        fArr[access$100 + (FloatDST_3D.this.rows * 2)] = fArr2[access$200 + 3];
                                    }
                                    FloatDST_3D.this.dstRows.forward(fArr, 0, z2);
                                    FloatDST_3D.this.dstRows.forward(fArr, FloatDST_3D.this.rows, z2);
                                    FloatDST_3D.this.dstRows.forward(fArr, FloatDST_3D.this.rows * 2, z2);
                                    FloatDST_3D.this.dstRows.forward(fArr, FloatDST_3D.this.rows * 3, z2);
                                    for (int i5 = 0; i5 < FloatDST_3D.this.rows; i5++) {
                                        int access$2002 = (FloatDST_3D.this.rowStride * i5) + access$000 + i3;
                                        int access$1002 = FloatDST_3D.this.rows + i5;
                                        float[] fArr3 = fArr2;
                                        fArr3[access$2002] = fArr[i5];
                                        fArr3[access$2002 + 1] = fArr[access$1002];
                                        fArr3[access$2002 + 2] = fArr[FloatDST_3D.this.rows + access$1002];
                                        fArr2[access$2002 + 3] = fArr[access$1002 + (FloatDST_3D.this.rows * 2)];
                                    }
                                }
                            } else if (FloatDST_3D.this.columns == 2) {
                                for (int i6 = 0; i6 < FloatDST_3D.this.rows; i6++) {
                                    int access$2003 = (FloatDST_3D.this.rowStride * i6) + access$000;
                                    fArr[i6] = fArr2[access$2003];
                                    fArr[FloatDST_3D.this.rows + i6] = fArr2[access$2003 + 1];
                                }
                                FloatDST_3D.this.dstRows.forward(fArr, 0, z2);
                                FloatDST_3D.this.dstRows.forward(fArr, FloatDST_3D.this.rows, z2);
                                for (int i7 = 0; i7 < FloatDST_3D.this.rows; i7++) {
                                    int access$2004 = (FloatDST_3D.this.rowStride * i7) + access$000;
                                    float[] fArr4 = fArr2;
                                    fArr4[access$2004] = fArr[i7];
                                    fArr4[access$2004 + 1] = fArr[FloatDST_3D.this.rows + i7];
                                }
                            }
                            i += i10;
                        }
                        return;
                    }
                    int i8 = i9;
                    while (i8 < FloatDST_3D.this.slices) {
                        int access$0002 = FloatDST_3D.this.sliceStride * i8;
                        for (int i9 = 0; i9 < FloatDST_3D.this.rows; i9++) {
                            FloatDST_3D.this.dstColumns.inverse(fArr2, (FloatDST_3D.this.rowStride * i9) + access$0002, z2);
                        }
                        if (FloatDST_3D.this.columns > 2) {
                            for (int i10 = 0; i10 < FloatDST_3D.this.columns; i10 += 4) {
                                for (int i11 = 0; i11 < FloatDST_3D.this.rows; i11++) {
                                    int access$2005 = (FloatDST_3D.this.rowStride * i11) + access$0002 + i10;
                                    int access$1003 = FloatDST_3D.this.rows + i11;
                                    float[] fArr5 = fArr2;
                                    fArr[i11] = fArr5[access$2005];
                                    fArr[access$1003] = fArr5[access$2005 + 1];
                                    fArr[FloatDST_3D.this.rows + access$1003] = fArr2[access$2005 + 2];
                                    fArr[access$1003 + (FloatDST_3D.this.rows * 2)] = fArr2[access$2005 + 3];
                                }
                                FloatDST_3D.this.dstRows.inverse(fArr, 0, z2);
                                FloatDST_3D.this.dstRows.inverse(fArr, FloatDST_3D.this.rows, z2);
                                FloatDST_3D.this.dstRows.inverse(fArr, FloatDST_3D.this.rows * 2, z2);
                                FloatDST_3D.this.dstRows.inverse(fArr, FloatDST_3D.this.rows * 3, z2);
                                for (int i12 = 0; i12 < FloatDST_3D.this.rows; i12++) {
                                    int access$2006 = (FloatDST_3D.this.rowStride * i12) + access$0002 + i10;
                                    int access$1004 = FloatDST_3D.this.rows + i12;
                                    float[] fArr6 = fArr2;
                                    fArr6[access$2006] = fArr[i12];
                                    fArr6[access$2006 + 1] = fArr[access$1004];
                                    fArr6[access$2006 + 2] = fArr[FloatDST_3D.this.rows + access$1004];
                                    fArr2[access$2006 + 3] = fArr[access$1004 + (FloatDST_3D.this.rows * 2)];
                                }
                            }
                        } else if (FloatDST_3D.this.columns == 2) {
                            for (int i13 = 0; i13 < FloatDST_3D.this.rows; i13++) {
                                int access$2007 = (FloatDST_3D.this.rowStride * i13) + access$0002;
                                fArr[i13] = fArr2[access$2007];
                                fArr[FloatDST_3D.this.rows + i13] = fArr2[access$2007 + 1];
                            }
                            FloatDST_3D.this.dstRows.inverse(fArr, 0, z2);
                            FloatDST_3D.this.dstRows.inverse(fArr, FloatDST_3D.this.rows, z2);
                            for (int i14 = 0; i14 < FloatDST_3D.this.rows; i14++) {
                                int access$2008 = (FloatDST_3D.this.rowStride * i14) + access$0002;
                                float[] fArr7 = fArr2;
                                fArr7[access$2008] = fArr[i14];
                                fArr7[access$2008 + 1] = fArr[FloatDST_3D.this.rows + i14];
                            }
                        }
                        i8 += i10;
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

    private void ddxt3da_subth(int i, FloatLargeArray floatLargeArray, boolean z) {
        Class<FloatDST_3D> cls = FloatDST_3D.class;
        long j = this.slicesl;
        if (((long) ConcurrencyUtils.getNumberOfThreads()) <= j) {
            j = (long) ConcurrencyUtils.getNumberOfThreads();
        }
        int i2 = (int) j;
        long j2 = this.rowsl * 4;
        if (this.columnsl == 2) {
            j2 >>= 1;
        }
        long j3 = j2;
        Future[] futureArr = new Future[i2];
        int i3 = 0;
        while (i3 < i2) {
            final long j4 = (long) i3;
            final long j5 = j3;
            final int i4 = i;
            final int i5 = i2;
            final FloatLargeArray floatLargeArray2 = floatLargeArray;
            int i6 = i3;
            final boolean z2 = z;
            futureArr[i6] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    long j;
                    long j2;
                    FloatLargeArray floatLargeArray = new FloatLargeArray(j5);
                    long j3 = 2;
                    long j4 = 1;
                    if (i4 == -1) {
                        long j5 = j4;
                        while (j5 < FloatDST_3D.this.slicesl) {
                            long access$800 = FloatDST_3D.this.sliceStridel * j5;
                            for (long j6 = 0; j6 < FloatDST_3D.this.rowsl; j6 += j4) {
                                FloatDST_3D.this.dstColumns.forward(floatLargeArray2, (FloatDST_3D.this.rowStridel * j6) + access$800, z2);
                            }
                            if (FloatDST_3D.this.columnsl > j3) {
                                long j7 = 0;
                                while (j7 < FloatDST_3D.this.columnsl) {
                                    long j8 = 0;
                                    while (j8 < FloatDST_3D.this.rowsl) {
                                        long access$1000 = (FloatDST_3D.this.rowStridel * j8) + access$800 + j7;
                                        long access$900 = FloatDST_3D.this.rowsl + j8;
                                        floatLargeArray.setFloat(j8, floatLargeArray2.getFloat(access$1000));
                                        floatLargeArray.setFloat(access$900, floatLargeArray2.getFloat(access$1000 + 1));
                                        floatLargeArray.setFloat(FloatDST_3D.this.rowsl + access$900, floatLargeArray2.getFloat(access$1000 + 2));
                                        floatLargeArray.setFloat(access$900 + (FloatDST_3D.this.rowsl * 2), floatLargeArray2.getFloat(access$1000 + 3));
                                        j8++;
                                        j5 = j5;
                                        j7 = j7;
                                    }
                                    long j9 = j5;
                                    long j10 = j7;
                                    FloatDST_3D.this.dstRows.forward(floatLargeArray, 0, z2);
                                    FloatDST_3D.this.dstRows.forward(floatLargeArray, FloatDST_3D.this.rowsl, z2);
                                    FloatDST_3D.this.dstRows.forward(floatLargeArray, FloatDST_3D.this.rowsl * 2, z2);
                                    FloatDST_3D.this.dstRows.forward(floatLargeArray, FloatDST_3D.this.rowsl * 3, z2);
                                    for (long j11 = 0; j11 < FloatDST_3D.this.rowsl; j11++) {
                                        long access$10002 = (FloatDST_3D.this.rowStridel * j11) + access$800 + j10;
                                        long access$9002 = FloatDST_3D.this.rowsl + j11;
                                        floatLargeArray2.setFloat(access$10002, floatLargeArray.getFloat(j11));
                                        floatLargeArray2.setFloat(access$10002 + 1, floatLargeArray.getFloat(access$9002));
                                        floatLargeArray2.setFloat(access$10002 + 2, floatLargeArray.getFloat(access$9002 + FloatDST_3D.this.rowsl));
                                        floatLargeArray2.setFloat(access$10002 + 3, floatLargeArray.getFloat(access$9002 + (FloatDST_3D.this.rowsl * 2)));
                                    }
                                    j7 = j10 + 4;
                                    j5 = j9;
                                }
                                j2 = j5;
                            } else {
                                j2 = j5;
                                if (FloatDST_3D.this.columnsl == j3) {
                                    for (long j12 = 0; j12 < FloatDST_3D.this.rowsl; j12++) {
                                        long access$10003 = (FloatDST_3D.this.rowStridel * j12) + access$800;
                                        floatLargeArray.setFloat(j12, floatLargeArray2.getFloat(access$10003));
                                        floatLargeArray.setFloat(FloatDST_3D.this.rowsl + j12, floatLargeArray2.getFloat(access$10003 + 1));
                                    }
                                    FloatDST_3D.this.dstRows.forward(floatLargeArray, 0, z2);
                                    FloatDST_3D.this.dstRows.forward(floatLargeArray, FloatDST_3D.this.rowsl, z2);
                                    for (long j13 = 0; j13 < FloatDST_3D.this.rowsl; j13++) {
                                        long access$10004 = (FloatDST_3D.this.rowStridel * j13) + access$800;
                                        floatLargeArray2.setFloat(access$10004, floatLargeArray.getFloat(j13));
                                        floatLargeArray2.setFloat(access$10004 + 1, floatLargeArray.getFloat(FloatDST_3D.this.rowsl + j13));
                                    }
                                }
                            }
                            j5 = j2 + ((long) i5);
                            j3 = 2;
                            j4 = 1;
                        }
                        return;
                    }
                    long j14 = j4;
                    while (j14 < FloatDST_3D.this.slicesl) {
                        long access$8002 = FloatDST_3D.this.sliceStridel * j14;
                        for (long j15 = 0; j15 < FloatDST_3D.this.rowsl; j15++) {
                            FloatDST_3D.this.dstColumns.inverse(floatLargeArray2, (FloatDST_3D.this.rowStridel * j15) + access$8002, z2);
                        }
                        if (FloatDST_3D.this.columnsl > 2) {
                            long j16 = 0;
                            while (j16 < FloatDST_3D.this.columnsl) {
                                long j17 = 0;
                                while (j17 < FloatDST_3D.this.rowsl) {
                                    long access$10005 = (FloatDST_3D.this.rowStridel * j17) + access$8002 + j16;
                                    long access$9003 = FloatDST_3D.this.rowsl + j17;
                                    floatLargeArray.setFloat(j17, floatLargeArray2.getFloat(access$10005));
                                    floatLargeArray.setFloat(access$9003, floatLargeArray2.getFloat(access$10005 + 1));
                                    floatLargeArray.setFloat(FloatDST_3D.this.rowsl + access$9003, floatLargeArray2.getFloat(access$10005 + 2));
                                    floatLargeArray.setFloat(access$9003 + (FloatDST_3D.this.rowsl * 2), floatLargeArray2.getFloat(access$10005 + 3));
                                    j17++;
                                    j14 = j14;
                                    j16 = j16;
                                }
                                long j18 = j14;
                                long j19 = j16;
                                FloatDST_3D.this.dstRows.inverse(floatLargeArray, 0, z2);
                                FloatDST_3D.this.dstRows.inverse(floatLargeArray, FloatDST_3D.this.rowsl, z2);
                                FloatDST_3D.this.dstRows.inverse(floatLargeArray, FloatDST_3D.this.rowsl * 2, z2);
                                FloatDST_3D.this.dstRows.inverse(floatLargeArray, FloatDST_3D.this.rowsl * 3, z2);
                                for (long j20 = 0; j20 < FloatDST_3D.this.rowsl; j20++) {
                                    long access$10006 = (FloatDST_3D.this.rowStridel * j20) + access$8002 + j19;
                                    long access$9004 = FloatDST_3D.this.rowsl + j20;
                                    floatLargeArray2.setFloat(access$10006, floatLargeArray.getFloat(j20));
                                    floatLargeArray2.setFloat(access$10006 + 1, floatLargeArray.getFloat(access$9004));
                                    floatLargeArray2.setFloat(access$10006 + 2, floatLargeArray.getFloat(access$9004 + FloatDST_3D.this.rowsl));
                                    floatLargeArray2.setFloat(access$10006 + 3, floatLargeArray.getFloat(access$9004 + (FloatDST_3D.this.rowsl * 2)));
                                }
                                j16 = j19 + 4;
                                j14 = j18;
                            }
                            j = j14;
                        } else {
                            j = j14;
                            if (FloatDST_3D.this.columnsl == 2) {
                                for (long j21 = 0; j21 < FloatDST_3D.this.rowsl; j21++) {
                                    long access$10007 = (FloatDST_3D.this.rowStridel * j21) + access$8002;
                                    floatLargeArray.setFloat(j21, floatLargeArray2.getFloat(access$10007));
                                    floatLargeArray.setFloat(FloatDST_3D.this.rowsl + j21, floatLargeArray2.getFloat(access$10007 + 1));
                                }
                                FloatDST_3D.this.dstRows.inverse(floatLargeArray, 0, z2);
                                FloatDST_3D.this.dstRows.inverse(floatLargeArray, FloatDST_3D.this.rowsl, z2);
                                for (long j22 = 0; j22 < FloatDST_3D.this.rowsl; j22++) {
                                    long access$10008 = (FloatDST_3D.this.rowStridel * j22) + access$8002;
                                    floatLargeArray2.setFloat(access$10008, floatLargeArray.getFloat(j22));
                                    floatLargeArray2.setFloat(access$10008 + 1, floatLargeArray.getFloat(FloatDST_3D.this.rowsl + j22));
                                }
                            }
                        }
                        j14 = j + ((long) i5);
                    }
                }
            });
            i3 = i6 + 1;
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
        } catch (ExecutionException e2) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
        }
    }

    private void ddxt3da_subth(int i, float[][][] fArr, boolean z) {
        Class<FloatDST_3D> cls = FloatDST_3D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i2 = this.slices;
        if (numberOfThreads <= i2) {
            i2 = ConcurrencyUtils.getNumberOfThreads();
        }
        int i3 = i2;
        int i4 = this.rows * 4;
        if (this.columns == 2) {
            i4 >>= 1;
        }
        int i5 = i4;
        Future[] futureArr = new Future[i3];
        for (int i6 = 0; i6 < i3; i6++) {
            final int i7 = i5;
            final int i8 = i;
            final int i9 = i6;
            final int i10 = i3;
            final float[][][] fArr2 = fArr;
            final boolean z2 = z;
            futureArr[i6] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    float[] fArr = new float[i7];
                    if (i8 == -1) {
                        int i = i9;
                        while (i < FloatDST_3D.this.slices) {
                            for (int i2 = 0; i2 < FloatDST_3D.this.rows; i2++) {
                                FloatDST_3D.this.dstColumns.forward(fArr2[i][i2], z2);
                            }
                            if (FloatDST_3D.this.columns > 2) {
                                for (int i3 = 0; i3 < FloatDST_3D.this.columns; i3 += 4) {
                                    for (int i4 = 0; i4 < FloatDST_3D.this.rows; i4++) {
                                        int access$100 = FloatDST_3D.this.rows + i4;
                                        float[] fArr2 = fArr2[i][i4];
                                        fArr[i4] = fArr2[i3];
                                        fArr[access$100] = fArr2[i3 + 1];
                                        fArr[FloatDST_3D.this.rows + access$100] = fArr2[i][i4][i3 + 2];
                                        fArr[access$100 + (FloatDST_3D.this.rows * 2)] = fArr2[i][i4][i3 + 3];
                                    }
                                    FloatDST_3D.this.dstRows.forward(fArr, 0, z2);
                                    FloatDST_3D.this.dstRows.forward(fArr, FloatDST_3D.this.rows, z2);
                                    FloatDST_3D.this.dstRows.forward(fArr, FloatDST_3D.this.rows * 2, z2);
                                    FloatDST_3D.this.dstRows.forward(fArr, FloatDST_3D.this.rows * 3, z2);
                                    for (int i5 = 0; i5 < FloatDST_3D.this.rows; i5++) {
                                        int access$1002 = FloatDST_3D.this.rows + i5;
                                        float[] fArr3 = fArr2[i][i5];
                                        fArr3[i3] = fArr[i5];
                                        fArr3[i3 + 1] = fArr[access$1002];
                                        fArr3[i3 + 2] = fArr[FloatDST_3D.this.rows + access$1002];
                                        fArr2[i][i5][i3 + 3] = fArr[access$1002 + (FloatDST_3D.this.rows * 2)];
                                    }
                                }
                            } else if (FloatDST_3D.this.columns == 2) {
                                for (int i6 = 0; i6 < FloatDST_3D.this.rows; i6++) {
                                    fArr[i6] = fArr2[i][i6][0];
                                    fArr[FloatDST_3D.this.rows + i6] = fArr2[i][i6][1];
                                }
                                FloatDST_3D.this.dstRows.forward(fArr, 0, z2);
                                FloatDST_3D.this.dstRows.forward(fArr, FloatDST_3D.this.rows, z2);
                                for (int i7 = 0; i7 < FloatDST_3D.this.rows; i7++) {
                                    float[] fArr4 = fArr2[i][i7];
                                    fArr4[0] = fArr[i7];
                                    fArr4[1] = fArr[FloatDST_3D.this.rows + i7];
                                }
                            }
                            i += i10;
                        }
                        return;
                    }
                    int i8 = i9;
                    while (i8 < FloatDST_3D.this.slices) {
                        for (int i9 = 0; i9 < FloatDST_3D.this.rows; i9++) {
                            FloatDST_3D.this.dstColumns.inverse(fArr2[i8][i9], z2);
                        }
                        if (FloatDST_3D.this.columns > 2) {
                            for (int i10 = 0; i10 < FloatDST_3D.this.columns; i10 += 4) {
                                for (int i11 = 0; i11 < FloatDST_3D.this.rows; i11++) {
                                    int access$1003 = FloatDST_3D.this.rows + i11;
                                    float[] fArr5 = fArr2[i8][i11];
                                    fArr[i11] = fArr5[i10];
                                    fArr[access$1003] = fArr5[i10 + 1];
                                    fArr[FloatDST_3D.this.rows + access$1003] = fArr2[i8][i11][i10 + 2];
                                    fArr[access$1003 + (FloatDST_3D.this.rows * 2)] = fArr2[i8][i11][i10 + 3];
                                }
                                FloatDST_3D.this.dstRows.inverse(fArr, 0, z2);
                                FloatDST_3D.this.dstRows.inverse(fArr, FloatDST_3D.this.rows, z2);
                                FloatDST_3D.this.dstRows.inverse(fArr, FloatDST_3D.this.rows * 2, z2);
                                FloatDST_3D.this.dstRows.inverse(fArr, FloatDST_3D.this.rows * 3, z2);
                                for (int i12 = 0; i12 < FloatDST_3D.this.rows; i12++) {
                                    int access$1004 = FloatDST_3D.this.rows + i12;
                                    float[] fArr6 = fArr2[i8][i12];
                                    fArr6[i10] = fArr[i12];
                                    fArr6[i10 + 1] = fArr[access$1004];
                                    fArr6[i10 + 2] = fArr[FloatDST_3D.this.rows + access$1004];
                                    fArr2[i8][i12][i10 + 3] = fArr[access$1004 + (FloatDST_3D.this.rows * 2)];
                                }
                            }
                        } else if (FloatDST_3D.this.columns == 2) {
                            for (int i13 = 0; i13 < FloatDST_3D.this.rows; i13++) {
                                fArr[i13] = fArr2[i8][i13][0];
                                fArr[FloatDST_3D.this.rows + i13] = fArr2[i8][i13][1];
                            }
                            FloatDST_3D.this.dstRows.inverse(fArr, 0, z2);
                            FloatDST_3D.this.dstRows.inverse(fArr, FloatDST_3D.this.rows, z2);
                            for (int i14 = 0; i14 < FloatDST_3D.this.rows; i14++) {
                                float[] fArr7 = fArr2[i8][i14];
                                fArr7[0] = fArr[i14];
                                fArr7[1] = fArr[FloatDST_3D.this.rows + i14];
                            }
                        }
                        i8 += i10;
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

    private void ddxt3db_subth(int i, float[] fArr, boolean z) {
        Class<FloatDST_3D> cls = FloatDST_3D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i2 = this.rows;
        if (numberOfThreads <= i2) {
            i2 = ConcurrencyUtils.getNumberOfThreads();
        }
        int i3 = i2;
        int i4 = this.slices * 4;
        if (this.columns == 2) {
            i4 >>= 1;
        }
        int i5 = i4;
        Future[] futureArr = new Future[i3];
        for (int i6 = 0; i6 < i3; i6++) {
            final int i7 = i5;
            final int i8 = i;
            final int i9 = i6;
            final int i10 = i3;
            final float[] fArr2 = fArr;
            final boolean z2 = z;
            futureArr[i6] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    float[] fArr = new float[i7];
                    if (i8 == -1) {
                        if (FloatDST_3D.this.columns > 2) {
                            int i = i9;
                            while (i < FloatDST_3D.this.rows) {
                                int access$200 = FloatDST_3D.this.rowStride * i;
                                for (int i2 = 0; i2 < FloatDST_3D.this.columns; i2 += 4) {
                                    for (int i3 = 0; i3 < FloatDST_3D.this.slices; i3++) {
                                        int access$000 = (FloatDST_3D.this.sliceStride * i3) + access$200 + i2;
                                        int access$600 = FloatDST_3D.this.slices + i3;
                                        float[] fArr2 = fArr2;
                                        fArr[i3] = fArr2[access$000];
                                        fArr[access$600] = fArr2[access$000 + 1];
                                        fArr[FloatDST_3D.this.slices + access$600] = fArr2[access$000 + 2];
                                        fArr[access$600 + (FloatDST_3D.this.slices * 2)] = fArr2[access$000 + 3];
                                    }
                                    FloatDST_3D.this.dstSlices.forward(fArr, 0, z2);
                                    FloatDST_3D.this.dstSlices.forward(fArr, FloatDST_3D.this.slices, z2);
                                    FloatDST_3D.this.dstSlices.forward(fArr, FloatDST_3D.this.slices * 2, z2);
                                    FloatDST_3D.this.dstSlices.forward(fArr, FloatDST_3D.this.slices * 3, z2);
                                    for (int i4 = 0; i4 < FloatDST_3D.this.slices; i4++) {
                                        int access$0002 = (FloatDST_3D.this.sliceStride * i4) + access$200 + i2;
                                        int access$6002 = FloatDST_3D.this.slices + i4;
                                        float[] fArr3 = fArr2;
                                        fArr3[access$0002] = fArr[i4];
                                        fArr3[access$0002 + 1] = fArr[access$6002];
                                        fArr3[access$0002 + 2] = fArr[FloatDST_3D.this.slices + access$6002];
                                        fArr2[access$0002 + 3] = fArr[access$6002 + (FloatDST_3D.this.slices * 2)];
                                    }
                                }
                                i += i10;
                            }
                        } else if (FloatDST_3D.this.columns == 2) {
                            int i5 = i9;
                            while (i5 < FloatDST_3D.this.rows) {
                                int access$2002 = FloatDST_3D.this.rowStride * i5;
                                for (int i6 = 0; i6 < FloatDST_3D.this.slices; i6++) {
                                    int access$0003 = (FloatDST_3D.this.sliceStride * i6) + access$2002;
                                    fArr[i6] = fArr2[access$0003];
                                    fArr[FloatDST_3D.this.slices + i6] = fArr2[access$0003 + 1];
                                }
                                FloatDST_3D.this.dstSlices.forward(fArr, 0, z2);
                                FloatDST_3D.this.dstSlices.forward(fArr, FloatDST_3D.this.slices, z2);
                                for (int i7 = 0; i7 < FloatDST_3D.this.slices; i7++) {
                                    int access$0004 = (FloatDST_3D.this.sliceStride * i7) + access$2002;
                                    float[] fArr4 = fArr2;
                                    fArr4[access$0004] = fArr[i7];
                                    fArr4[access$0004 + 1] = fArr[FloatDST_3D.this.slices + i7];
                                }
                                i5 += i10;
                            }
                        }
                    } else if (FloatDST_3D.this.columns > 2) {
                        int i8 = i9;
                        while (i8 < FloatDST_3D.this.rows) {
                            int access$2003 = FloatDST_3D.this.rowStride * i8;
                            for (int i9 = 0; i9 < FloatDST_3D.this.columns; i9 += 4) {
                                for (int i10 = 0; i10 < FloatDST_3D.this.slices; i10++) {
                                    int access$0005 = (FloatDST_3D.this.sliceStride * i10) + access$2003 + i9;
                                    int access$6003 = FloatDST_3D.this.slices + i10;
                                    float[] fArr5 = fArr2;
                                    fArr[i10] = fArr5[access$0005];
                                    fArr[access$6003] = fArr5[access$0005 + 1];
                                    fArr[FloatDST_3D.this.slices + access$6003] = fArr2[access$0005 + 2];
                                    fArr[access$6003 + (FloatDST_3D.this.slices * 2)] = fArr2[access$0005 + 3];
                                }
                                FloatDST_3D.this.dstSlices.inverse(fArr, 0, z2);
                                FloatDST_3D.this.dstSlices.inverse(fArr, FloatDST_3D.this.slices, z2);
                                FloatDST_3D.this.dstSlices.inverse(fArr, FloatDST_3D.this.slices * 2, z2);
                                FloatDST_3D.this.dstSlices.inverse(fArr, FloatDST_3D.this.slices * 3, z2);
                                for (int i11 = 0; i11 < FloatDST_3D.this.slices; i11++) {
                                    int access$0006 = (FloatDST_3D.this.sliceStride * i11) + access$2003 + i9;
                                    int access$6004 = FloatDST_3D.this.slices + i11;
                                    float[] fArr6 = fArr2;
                                    fArr6[access$0006] = fArr[i11];
                                    fArr6[access$0006 + 1] = fArr[access$6004];
                                    fArr6[access$0006 + 2] = fArr[FloatDST_3D.this.slices + access$6004];
                                    fArr2[access$0006 + 3] = fArr[access$6004 + (FloatDST_3D.this.slices * 2)];
                                }
                            }
                            i8 += i10;
                        }
                    } else if (FloatDST_3D.this.columns == 2) {
                        int i12 = i9;
                        while (i12 < FloatDST_3D.this.rows) {
                            int access$2004 = FloatDST_3D.this.rowStride * i12;
                            for (int i13 = 0; i13 < FloatDST_3D.this.slices; i13++) {
                                int access$0007 = (FloatDST_3D.this.sliceStride * i13) + access$2004;
                                fArr[i13] = fArr2[access$0007];
                                fArr[FloatDST_3D.this.slices + i13] = fArr2[access$0007 + 1];
                            }
                            FloatDST_3D.this.dstSlices.inverse(fArr, 0, z2);
                            FloatDST_3D.this.dstSlices.inverse(fArr, FloatDST_3D.this.slices, z2);
                            for (int i14 = 0; i14 < FloatDST_3D.this.slices; i14++) {
                                int access$0008 = (FloatDST_3D.this.sliceStride * i14) + access$2004;
                                float[] fArr7 = fArr2;
                                fArr7[access$0008] = fArr[i14];
                                fArr7[access$0008 + 1] = fArr[FloatDST_3D.this.slices + i14];
                            }
                            i12 += i10;
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

    private void ddxt3db_subth(int i, FloatLargeArray floatLargeArray, boolean z) {
        Class<FloatDST_3D> cls = FloatDST_3D.class;
        long j = this.rowsl;
        if (((long) ConcurrencyUtils.getNumberOfThreads()) <= j) {
            j = (long) ConcurrencyUtils.getNumberOfThreads();
        }
        int i2 = (int) j;
        long j2 = this.slicesl * 4;
        if (this.columnsl == 2) {
            j2 >>= 1;
        }
        long j3 = j2;
        Future[] futureArr = new Future[i2];
        int i3 = 0;
        while (i3 < i2) {
            final long j4 = (long) i3;
            final long j5 = j3;
            final int i4 = i;
            final int i5 = i2;
            final FloatLargeArray floatLargeArray2 = floatLargeArray;
            int i6 = i3;
            final boolean z2 = z;
            futureArr[i6] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    FloatLargeArray floatLargeArray = new FloatLargeArray(j5);
                    long j = 1;
                    if (i4 == -1) {
                        if (FloatDST_3D.this.columnsl > 2) {
                            long j2 = j4;
                            while (j2 < FloatDST_3D.this.rowsl) {
                                long access$1000 = FloatDST_3D.this.rowStridel * j2;
                                long j3 = 0;
                                while (j3 < FloatDST_3D.this.columnsl) {
                                    long j4 = 0;
                                    while (j4 < FloatDST_3D.this.slicesl) {
                                        long access$800 = (FloatDST_3D.this.sliceStridel * j4) + access$1000 + j3;
                                        long access$1200 = FloatDST_3D.this.slicesl + j4;
                                        floatLargeArray.setFloat(j4, floatLargeArray2.getFloat(access$800));
                                        floatLargeArray.setFloat(access$1200, floatLargeArray2.getFloat(access$800 + j));
                                        floatLargeArray.setFloat(FloatDST_3D.this.slicesl + access$1200, floatLargeArray2.getFloat(access$800 + 2));
                                        floatLargeArray.setFloat(access$1200 + (FloatDST_3D.this.slicesl * 2), floatLargeArray2.getFloat(access$800 + 3));
                                        j4++;
                                        j = 1;
                                        j2 = j2;
                                    }
                                    long j5 = j2;
                                    FloatDST_3D.this.dstSlices.forward(floatLargeArray, 0, z2);
                                    FloatDST_3D.this.dstSlices.forward(floatLargeArray, FloatDST_3D.this.slicesl, z2);
                                    FloatDST_3D.this.dstSlices.forward(floatLargeArray, FloatDST_3D.this.slicesl * 2, z2);
                                    FloatDST_3D.this.dstSlices.forward(floatLargeArray, FloatDST_3D.this.slicesl * 3, z2);
                                    for (long j6 = 0; j6 < FloatDST_3D.this.slicesl; j6++) {
                                        long access$8002 = (FloatDST_3D.this.sliceStridel * j6) + access$1000 + j3;
                                        long access$12002 = FloatDST_3D.this.slicesl + j6;
                                        floatLargeArray2.setFloat(access$8002, floatLargeArray.getFloat(j6));
                                        floatLargeArray2.setFloat(access$8002 + 1, floatLargeArray.getFloat(access$12002));
                                        floatLargeArray2.setFloat(access$8002 + 2, floatLargeArray.getFloat(access$12002 + FloatDST_3D.this.slicesl));
                                        floatLargeArray2.setFloat(access$8002 + 3, floatLargeArray.getFloat(access$12002 + (FloatDST_3D.this.slicesl * 2)));
                                    }
                                    j3 += 4;
                                    j2 = j5;
                                    j = 1;
                                }
                                j2 += (long) i5;
                                j = 1;
                            }
                        } else if (FloatDST_3D.this.columnsl == 2) {
                            for (long j7 = j4; j7 < FloatDST_3D.this.rowsl; j7 += (long) i5) {
                                long access$10002 = FloatDST_3D.this.rowStridel * j7;
                                for (long j8 = 0; j8 < FloatDST_3D.this.slicesl; j8++) {
                                    long access$8003 = (FloatDST_3D.this.sliceStridel * j8) + access$10002;
                                    floatLargeArray.setFloat(j8, floatLargeArray2.getFloat(access$8003));
                                    floatLargeArray.setFloat(FloatDST_3D.this.slicesl + j8, floatLargeArray2.getFloat(access$8003 + 1));
                                }
                                FloatDST_3D.this.dstSlices.forward(floatLargeArray, 0, z2);
                                FloatDST_3D.this.dstSlices.forward(floatLargeArray, FloatDST_3D.this.slicesl, z2);
                                for (long j9 = 0; j9 < FloatDST_3D.this.slicesl; j9++) {
                                    long access$8004 = (FloatDST_3D.this.sliceStridel * j9) + access$10002;
                                    floatLargeArray2.setFloat(access$8004, floatLargeArray.getFloat(j9));
                                    floatLargeArray2.setFloat(access$8004 + 1, floatLargeArray.getFloat(FloatDST_3D.this.slicesl + j9));
                                }
                            }
                        }
                    } else if (FloatDST_3D.this.columnsl > 2) {
                        long j10 = j4;
                        while (j10 < FloatDST_3D.this.rowsl) {
                            long access$10003 = FloatDST_3D.this.rowStridel * j10;
                            long j11 = 0;
                            while (j11 < FloatDST_3D.this.columnsl) {
                                long j12 = 0;
                                while (j12 < FloatDST_3D.this.slicesl) {
                                    long access$8005 = (FloatDST_3D.this.sliceStridel * j12) + access$10003 + j11;
                                    long access$12003 = FloatDST_3D.this.slicesl + j12;
                                    floatLargeArray.setFloat(j12, floatLargeArray2.getFloat(access$8005));
                                    floatLargeArray.setFloat(access$12003, floatLargeArray2.getFloat(access$8005 + 1));
                                    floatLargeArray.setFloat(FloatDST_3D.this.slicesl + access$12003, floatLargeArray2.getFloat(access$8005 + 2));
                                    floatLargeArray.setFloat(access$12003 + (FloatDST_3D.this.slicesl * 2), floatLargeArray2.getFloat(access$8005 + 3));
                                    j12++;
                                    j10 = j10;
                                    j11 = j11;
                                }
                                long j13 = j10;
                                long j14 = j11;
                                FloatDST_3D.this.dstSlices.inverse(floatLargeArray, 0, z2);
                                FloatDST_3D.this.dstSlices.inverse(floatLargeArray, FloatDST_3D.this.slicesl, z2);
                                FloatDST_3D.this.dstSlices.inverse(floatLargeArray, FloatDST_3D.this.slicesl * 2, z2);
                                FloatDST_3D.this.dstSlices.inverse(floatLargeArray, FloatDST_3D.this.slicesl * 3, z2);
                                for (long j15 = 0; j15 < FloatDST_3D.this.slicesl; j15++) {
                                    long access$8006 = (FloatDST_3D.this.sliceStridel * j15) + access$10003 + j14;
                                    long access$12004 = FloatDST_3D.this.slicesl + j15;
                                    floatLargeArray2.setFloat(access$8006, floatLargeArray.getFloat(j15));
                                    floatLargeArray2.setFloat(access$8006 + 1, floatLargeArray.getFloat(access$12004));
                                    floatLargeArray2.setFloat(access$8006 + 2, floatLargeArray.getFloat(access$12004 + FloatDST_3D.this.slicesl));
                                    floatLargeArray2.setFloat(access$8006 + 3, floatLargeArray.getFloat(access$12004 + (FloatDST_3D.this.slicesl * 2)));
                                }
                                j11 = j14 + 4;
                                j10 = j13;
                            }
                            j10 += (long) i5;
                        }
                    } else if (FloatDST_3D.this.columnsl == 2) {
                        for (long j16 = j4; j16 < FloatDST_3D.this.rowsl; j16 += (long) i5) {
                            long access$10004 = FloatDST_3D.this.rowStridel * j16;
                            for (long j17 = 0; j17 < FloatDST_3D.this.slicesl; j17++) {
                                long access$8007 = (FloatDST_3D.this.sliceStridel * j17) + access$10004;
                                floatLargeArray.setFloat(j17, floatLargeArray2.getFloat(access$8007));
                                floatLargeArray.setFloat(FloatDST_3D.this.slicesl + j17, floatLargeArray2.getFloat(access$8007 + 1));
                            }
                            FloatDST_3D.this.dstSlices.inverse(floatLargeArray, 0, z2);
                            FloatDST_3D.this.dstSlices.inverse(floatLargeArray, FloatDST_3D.this.slicesl, z2);
                            for (long j18 = 0; j18 < FloatDST_3D.this.slicesl; j18++) {
                                long access$8008 = (FloatDST_3D.this.sliceStridel * j18) + access$10004;
                                floatLargeArray2.setFloat(access$8008, floatLargeArray.getFloat(j18));
                                floatLargeArray2.setFloat(access$8008 + 1, floatLargeArray.getFloat(FloatDST_3D.this.slicesl + j18));
                            }
                        }
                    }
                }
            });
            i3 = i6 + 1;
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e);
        } catch (ExecutionException e2) {
            Logger.getLogger(cls.getName()).log(Level.SEVERE, (String) null, e2);
        }
    }

    private void ddxt3db_subth(int i, float[][][] fArr, boolean z) {
        Class<FloatDST_3D> cls = FloatDST_3D.class;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i2 = this.rows;
        if (numberOfThreads <= i2) {
            i2 = ConcurrencyUtils.getNumberOfThreads();
        }
        int i3 = i2;
        int i4 = this.slices * 4;
        if (this.columns == 2) {
            i4 >>= 1;
        }
        int i5 = i4;
        Future[] futureArr = new Future[i3];
        for (int i6 = 0; i6 < i3; i6++) {
            final int i7 = i5;
            final int i8 = i;
            final int i9 = i6;
            final int i10 = i3;
            final float[][][] fArr2 = fArr;
            final boolean z2 = z;
            futureArr[i6] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                public void run() {
                    float[] fArr = new float[i7];
                    if (i8 == -1) {
                        if (FloatDST_3D.this.columns > 2) {
                            int i = i9;
                            while (i < FloatDST_3D.this.rows) {
                                for (int i2 = 0; i2 < FloatDST_3D.this.columns; i2 += 4) {
                                    for (int i3 = 0; i3 < FloatDST_3D.this.slices; i3++) {
                                        int access$600 = FloatDST_3D.this.slices + i3;
                                        float[] fArr2 = fArr2[i3][i];
                                        fArr[i3] = fArr2[i2];
                                        fArr[access$600] = fArr2[i2 + 1];
                                        fArr[FloatDST_3D.this.slices + access$600] = fArr2[i3][i][i2 + 2];
                                        fArr[access$600 + (FloatDST_3D.this.slices * 2)] = fArr2[i3][i][i2 + 3];
                                    }
                                    FloatDST_3D.this.dstSlices.forward(fArr, 0, z2);
                                    FloatDST_3D.this.dstSlices.forward(fArr, FloatDST_3D.this.slices, z2);
                                    FloatDST_3D.this.dstSlices.forward(fArr, FloatDST_3D.this.slices * 2, z2);
                                    FloatDST_3D.this.dstSlices.forward(fArr, FloatDST_3D.this.slices * 3, z2);
                                    for (int i4 = 0; i4 < FloatDST_3D.this.slices; i4++) {
                                        int access$6002 = FloatDST_3D.this.slices + i4;
                                        float[] fArr3 = fArr2[i4][i];
                                        fArr3[i2] = fArr[i4];
                                        fArr3[i2 + 1] = fArr[access$6002];
                                        fArr3[i2 + 2] = fArr[FloatDST_3D.this.slices + access$6002];
                                        fArr2[i4][i][i2 + 3] = fArr[access$6002 + (FloatDST_3D.this.slices * 2)];
                                    }
                                }
                                i += i10;
                            }
                        } else if (FloatDST_3D.this.columns == 2) {
                            int i5 = i9;
                            while (i5 < FloatDST_3D.this.rows) {
                                for (int i6 = 0; i6 < FloatDST_3D.this.slices; i6++) {
                                    fArr[i6] = fArr2[i6][i5][0];
                                    fArr[FloatDST_3D.this.slices + i6] = fArr2[i6][i5][1];
                                }
                                FloatDST_3D.this.dstSlices.forward(fArr, 0, z2);
                                FloatDST_3D.this.dstSlices.forward(fArr, FloatDST_3D.this.slices, z2);
                                for (int i7 = 0; i7 < FloatDST_3D.this.slices; i7++) {
                                    float[] fArr4 = fArr2[i7][i5];
                                    fArr4[0] = fArr[i7];
                                    fArr4[1] = fArr[FloatDST_3D.this.slices + i7];
                                }
                                i5 += i10;
                            }
                        }
                    } else if (FloatDST_3D.this.columns > 2) {
                        int i8 = i9;
                        while (i8 < FloatDST_3D.this.rows) {
                            for (int i9 = 0; i9 < FloatDST_3D.this.columns; i9 += 4) {
                                for (int i10 = 0; i10 < FloatDST_3D.this.slices; i10++) {
                                    int access$6003 = FloatDST_3D.this.slices + i10;
                                    float[] fArr5 = fArr2[i10][i8];
                                    fArr[i10] = fArr5[i9];
                                    fArr[access$6003] = fArr5[i9 + 1];
                                    fArr[FloatDST_3D.this.slices + access$6003] = fArr2[i10][i8][i9 + 2];
                                    fArr[access$6003 + (FloatDST_3D.this.slices * 2)] = fArr2[i10][i8][i9 + 3];
                                }
                                FloatDST_3D.this.dstSlices.inverse(fArr, 0, z2);
                                FloatDST_3D.this.dstSlices.inverse(fArr, FloatDST_3D.this.slices, z2);
                                FloatDST_3D.this.dstSlices.inverse(fArr, FloatDST_3D.this.slices * 2, z2);
                                FloatDST_3D.this.dstSlices.inverse(fArr, FloatDST_3D.this.slices * 3, z2);
                                for (int i11 = 0; i11 < FloatDST_3D.this.slices; i11++) {
                                    int access$6004 = FloatDST_3D.this.slices + i11;
                                    float[] fArr6 = fArr2[i11][i8];
                                    fArr6[i9] = fArr[i11];
                                    fArr6[i9 + 1] = fArr[access$6004];
                                    fArr6[i9 + 2] = fArr[FloatDST_3D.this.slices + access$6004];
                                    fArr2[i11][i8][i9 + 3] = fArr[access$6004 + (FloatDST_3D.this.slices * 2)];
                                }
                            }
                            i8 += i10;
                        }
                    } else if (FloatDST_3D.this.columns == 2) {
                        int i12 = i9;
                        while (i12 < FloatDST_3D.this.rows) {
                            for (int i13 = 0; i13 < FloatDST_3D.this.slices; i13++) {
                                fArr[i13] = fArr2[i13][i12][0];
                                fArr[FloatDST_3D.this.slices + i13] = fArr2[i13][i12][1];
                            }
                            FloatDST_3D.this.dstSlices.inverse(fArr, 0, z2);
                            FloatDST_3D.this.dstSlices.inverse(fArr, FloatDST_3D.this.slices, z2);
                            for (int i14 = 0; i14 < FloatDST_3D.this.slices; i14++) {
                                float[] fArr7 = fArr2[i14][i12];
                                fArr7[0] = fArr[i14];
                                fArr7[1] = fArr[FloatDST_3D.this.slices + i14];
                            }
                            i12 += i10;
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
}
