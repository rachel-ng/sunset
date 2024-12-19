package org.jtransforms.fft;

import org.apache.commons.math3.util.FastMath;
import pl.edu.icm.jlargearrays.DoubleLargeArray;
import pl.edu.icm.jlargearrays.FloatLargeArray;

public class RealFFTUtils_3D {
    private static final int ONE = 1;
    private static final long ONEL = 1;
    private static final int TWO = 2;
    private static final long TWOL = 2;
    private static final int ZERO = 0;
    private static final long ZEROL = 0;
    private final int columns;
    private final long columnsl;
    private final int rowStride;
    private final long rowStridel;
    private final int rows;
    private final long rowsl;
    private final int sliceStride;
    private final long sliceStridel;
    private final int slices;
    private final long slicesl;

    public RealFFTUtils_3D(long j, long j2, long j3) {
        this.slices = (int) j;
        int i = (int) j2;
        this.rows = i;
        int i2 = (int) j3;
        this.columns = i2;
        this.rowStride = i2;
        this.sliceStride = i * i2;
        this.slicesl = j;
        this.rowsl = j2;
        this.columnsl = j3;
        this.rowStridel = j3;
        this.sliceStridel = j2 * j3;
    }

    public int getIndex(int i, int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int i7;
        int i8 = i3 & 1;
        int i9 = i2 << 1;
        int i10 = i << 1;
        int i11 = i == 0 ? 0 : this.slices - i;
        if (i2 == 0) {
            i4 = 0;
        } else {
            i4 = this.rows - i2;
        }
        if (i3 > 1) {
            int i12 = this.columns;
            if (i3 < i12) {
                i5 = i * this.sliceStride;
                i6 = this.rowStride;
                i7 = i5 + (i2 * i6);
            } else if (i3 > i12 + 1) {
                int i13 = (i11 * this.sliceStride) + (i4 * this.rowStride) + ((i12 << 1) - i3);
                return i8 == 0 ? i13 : -(i13 + 2);
            } else if (i2 != 0) {
                int i14 = this.rows;
                if (i9 < i14) {
                    int i15 = (i11 * this.sliceStride) + (i4 * this.rowStride);
                    return i8 == 0 ? i15 + 1 : -i15;
                } else if (i9 > i14) {
                    int i16 = (i * this.sliceStride) + (i2 * this.rowStride);
                    return i8 == 0 ? i16 + 1 : i16;
                } else if (i == 0) {
                    int i17 = (i2 * this.rowStride) + 1;
                    if (i8 == 0) {
                        return i17;
                    }
                    return Integer.MIN_VALUE;
                } else {
                    int i18 = this.slices;
                    if (i10 < i18) {
                        int i19 = (i11 * this.sliceStride) + (i2 * this.rowStride);
                        return i8 == 0 ? i19 + 1 : -i19;
                    } else if (i10 > i18) {
                        int i20 = (i * this.sliceStride) + (i2 * this.rowStride);
                        return i8 == 0 ? i20 + 1 : i20;
                    } else {
                        int i21 = (i * this.sliceStride) + (i2 * this.rowStride);
                        if (i8 == 0) {
                            return i21 + 1;
                        }
                        return Integer.MIN_VALUE;
                    }
                }
            } else if (i != 0) {
                int i22 = this.slices;
                if (i10 < i22) {
                    int i23 = i11 * this.sliceStride;
                    return i8 == 0 ? i23 + 1 : -i23;
                } else if (i10 > i22) {
                    int i24 = i * this.sliceStride;
                    return i8 == 0 ? i24 + 1 : i24;
                } else {
                    int i25 = i * this.sliceStride;
                    if (i8 == 0) {
                        return i25 + 1;
                    }
                    return Integer.MIN_VALUE;
                }
            } else if (i8 == 0) {
                return 1;
            } else {
                return Integer.MIN_VALUE;
            }
        } else if (i2 != 0) {
            int i26 = this.rows;
            if (i9 < i26) {
                i5 = i * this.sliceStride;
                i6 = this.rowStride;
            } else if (i9 > i26) {
                int i27 = (i11 * this.sliceStride) + (i4 * this.rowStride);
                return i8 == 0 ? i27 : -(i27 + 1);
            } else if (i != 0) {
                int i28 = this.slices;
                if (i10 < i28) {
                    i5 = i * this.sliceStride;
                    i6 = this.rowStride;
                } else if (i10 > i28) {
                    int i29 = (i11 * this.sliceStride) + (i2 * this.rowStride);
                    return i8 == 0 ? i29 : -(i29 + 1);
                } else {
                    int i30 = (i * this.sliceStride) + (i2 * this.rowStride);
                    if (i8 == 0) {
                        return i30;
                    }
                    return Integer.MIN_VALUE;
                }
            } else if (i8 == 0) {
                return i2 * this.rowStride;
            } else {
                return Integer.MIN_VALUE;
            }
            i7 = i5 + (i2 * i6);
        } else if (i != 0) {
            int i31 = this.slices;
            if (i10 < i31) {
                i7 = i * this.sliceStride;
            } else if (i10 > i31) {
                int i32 = i11 * this.sliceStride;
                return i8 == 0 ? i32 : -(i32 + 1);
            } else if (i8 == 0) {
                return i * this.sliceStride;
            } else {
                return Integer.MIN_VALUE;
            }
        } else if (i3 == 0) {
            return 0;
        } else {
            return Integer.MIN_VALUE;
        }
        return i7 + i3;
    }

    public long getIndex(long j, long j2, long j3) {
        long j4;
        long j5;
        long j6;
        long j7;
        long j8 = j3 & 1;
        long j9 = j2 << 1;
        long j10 = j << 1;
        int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
        long j11 = i == 0 ? 0 : this.slicesl - j;
        int i2 = (j2 > 0 ? 1 : (j2 == 0 ? 0 : -1));
        long j12 = j9;
        if (i2 == 0) {
            j4 = 0;
        } else {
            j4 = this.rowsl - j2;
        }
        if (j3 > 1) {
            long j13 = this.columnsl;
            if (j3 < j13) {
                j5 = this.sliceStridel * j;
                j6 = this.rowStridel;
                j7 = j5 + (j6 * j2);
            } else if (j3 > j13 + 1) {
                long j14 = (j11 * this.sliceStridel) + (j4 * this.rowStridel) + ((j13 << 1) - j3);
                return j8 == 0 ? j14 : -(j14 + 2);
            } else if (i2 != 0) {
                long j15 = this.rowsl;
                if (j12 < j15) {
                    long j16 = (j11 * this.sliceStridel) + (j4 * this.rowStridel);
                    return j8 == 0 ? j16 + 1 : -j16;
                } else if (j12 > j15) {
                    long j17 = (this.sliceStridel * j) + (this.rowStridel * j2);
                    return j8 == 0 ? j17 + 1 : j17;
                } else if (i == 0) {
                    long j18 = (this.rowStridel * j2) + 1;
                    if (j8 == 0) {
                        return j18;
                    }
                    return Long.MIN_VALUE;
                } else {
                    long j19 = this.slicesl;
                    if (j10 < j19) {
                        long j20 = (j11 * this.sliceStridel) + (this.rowStridel * j2);
                        return j8 == 0 ? j20 + 1 : -j20;
                    } else if (j10 > j19) {
                        long j21 = (this.sliceStridel * j) + (this.rowStridel * j2);
                        return j8 == 0 ? j21 + 1 : j21;
                    } else {
                        long j22 = (this.sliceStridel * j) + (this.rowStridel * j2);
                        if (j8 == 0) {
                            return j22 + 1;
                        }
                        return Long.MIN_VALUE;
                    }
                }
            } else if (i == 0) {
                return j8 == 0 ? 1 : Long.MIN_VALUE;
            } else {
                long j23 = this.slicesl;
                if (j10 < j23) {
                    long j24 = j11 * this.sliceStridel;
                    return j8 == 0 ? j24 + 1 : -j24;
                } else if (j10 > j23) {
                    long j25 = this.sliceStridel * j;
                    return j8 == 0 ? j25 + 1 : j25;
                } else {
                    long j26 = this.sliceStridel * j;
                    if (j8 == 0) {
                        return j26 + 1;
                    }
                    return Long.MIN_VALUE;
                }
            }
        } else if (i2 != 0) {
            long j27 = this.rowsl;
            if (j12 < j27) {
                j5 = this.sliceStridel * j;
                j6 = this.rowStridel;
            } else if (j12 > j27) {
                long j28 = (j11 * this.sliceStridel) + (j4 * this.rowStridel);
                return j8 == 0 ? j28 : -(j28 + 1);
            } else if (i != 0) {
                long j29 = this.slicesl;
                if (j10 < j29) {
                    j5 = this.sliceStridel * j;
                    j6 = this.rowStridel;
                } else if (j10 > j29) {
                    long j30 = (j11 * this.sliceStridel) + (this.rowStridel * j2);
                    return j8 == 0 ? j30 : -(j30 + 1);
                } else {
                    long j31 = (this.sliceStridel * j) + (this.rowStridel * j2);
                    if (j8 == 0) {
                        return j31;
                    }
                    return Long.MIN_VALUE;
                }
            } else if (j8 == 0) {
                return j2 * this.rowStridel;
            } else {
                return Long.MIN_VALUE;
            }
            j7 = j5 + (j6 * j2);
        } else if (i != 0) {
            long j32 = this.slicesl;
            if (j10 < j32) {
                j7 = this.sliceStridel * j;
            } else if (j10 > j32) {
                long j33 = j11 * this.sliceStridel;
                return j8 == 0 ? j33 : -(j33 + 1);
            } else if (j8 == 0) {
                return j * this.sliceStridel;
            } else {
                return Long.MIN_VALUE;
            }
        } else if (j3 == 0) {
            return 0;
        } else {
            return Long.MIN_VALUE;
        }
        return j7 + j3;
    }

    public void pack(double d, int i, int i2, int i3, double[] dArr, int i4) {
        int index = getIndex(i, i2, i3);
        if (index >= 0) {
            dArr[i4 + index] = d;
        } else if (index > Integer.MIN_VALUE) {
            dArr[i4 - index] = -d;
        } else {
            throw new IllegalArgumentException(String.format("[%d][%d][%d] component cannot be modified (always zero)", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)}));
        }
    }

    public void pack(double d, long j, long j2, long j3, DoubleLargeArray doubleLargeArray, long j4) {
        double d2 = d;
        DoubleLargeArray doubleLargeArray2 = doubleLargeArray;
        long index = getIndex(j, j2, j3);
        if (index >= 0) {
            doubleLargeArray2.setDouble(j4 + index, d);
        } else if (index > Long.MIN_VALUE) {
            doubleLargeArray2.setDouble(j4 - index, -d2);
        } else {
            throw new IllegalArgumentException(String.format("[%d][%d][%d] component cannot be modified (always zero)", new Object[]{Long.valueOf(j), Long.valueOf(j2), Long.valueOf(j3)}));
        }
    }

    public void pack(double d, int i, int i2, int i3, double[][][] dArr) {
        int index = getIndex(i, i2, i3);
        int abs = FastMath.abs(index);
        int i4 = this.sliceStride;
        int i5 = abs / i4;
        int i6 = abs % i4;
        int i7 = this.rowStride;
        int i8 = i6 / i7;
        int i9 = i6 % i7;
        if (index >= 0) {
            dArr[i5][i8][i9] = d;
        } else if (index > Integer.MIN_VALUE) {
            dArr[i5][i8][i9] = -d;
        } else {
            throw new IllegalArgumentException(String.format("[%d][%d] component cannot be modified (always zero)", new Object[]{Integer.valueOf(i2), Integer.valueOf(i3)}));
        }
    }

    public void pack(float f, int i, int i2, int i3, float[] fArr, int i4) {
        int index = getIndex(i, i2, i3);
        if (index >= 0) {
            fArr[i4 + index] = f;
        } else if (index > Integer.MIN_VALUE) {
            fArr[i4 - index] = -f;
        } else {
            throw new IllegalArgumentException(String.format("[%d][%d][%d] component cannot be modified (always zero)", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)}));
        }
    }

    public void pack(float f, long j, long j2, long j3, FloatLargeArray floatLargeArray, long j4) {
        float f2 = f;
        FloatLargeArray floatLargeArray2 = floatLargeArray;
        long index = getIndex(j, j2, j3);
        if (index >= 0) {
            floatLargeArray2.setFloat(j4 + index, f);
        } else if (index > Long.MIN_VALUE) {
            floatLargeArray2.setFloat(j4 - index, -f2);
        } else {
            throw new IllegalArgumentException(String.format("[%d][%d][%d] component cannot be modified (always zero)", new Object[]{Long.valueOf(j), Long.valueOf(j2), Long.valueOf(j3)}));
        }
    }

    public void pack(float f, int i, int i2, int i3, float[][][] fArr) {
        int index = getIndex(i, i2, i3);
        int abs = FastMath.abs(index);
        int i4 = this.sliceStride;
        int i5 = abs / i4;
        int i6 = abs % i4;
        int i7 = this.rowStride;
        int i8 = i6 / i7;
        int i9 = i6 % i7;
        if (index >= 0) {
            fArr[i5][i8][i9] = f;
        } else if (index > Integer.MIN_VALUE) {
            fArr[i5][i8][i9] = -f;
        } else {
            throw new IllegalArgumentException(String.format("[%d][%d][%d] component cannot be modified (always zero)", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)}));
        }
    }

    public double unpack(int i, int i2, int i3, double[] dArr, int i4) {
        int index = getIndex(i, i2, i3);
        if (index >= 0) {
            return dArr[i4 + index];
        }
        if (index > Integer.MIN_VALUE) {
            return -dArr[i4 - index];
        }
        return 0.0d;
    }

    public double unpack(long j, long j2, long j3, DoubleLargeArray doubleLargeArray, long j4) {
        long index = getIndex(j, j2, j3);
        if (index >= 0) {
            return doubleLargeArray.getDouble(j4 + index);
        }
        if (index > Long.MIN_VALUE) {
            return -doubleLargeArray.getDouble(j4 - index);
        }
        return 0.0d;
    }

    public double unpack(int i, int i2, int i3, double[][][] dArr) {
        int index = getIndex(i, i2, i3);
        int abs = FastMath.abs(index);
        int i4 = this.sliceStride;
        int i5 = abs / i4;
        int i6 = abs % i4;
        int i7 = this.rowStride;
        int i8 = i6 / i7;
        int i9 = i6 % i7;
        if (index >= 0) {
            return dArr[i5][i8][i9];
        }
        if (index > Integer.MIN_VALUE) {
            return -dArr[i5][i8][i9];
        }
        return 0.0d;
    }

    public float unpack(int i, int i2, int i3, float[] fArr, int i4) {
        int index = getIndex(i, i2, i3);
        if (index >= 0) {
            return fArr[i4 + index];
        }
        if (index > Integer.MIN_VALUE) {
            return -fArr[i4 - index];
        }
        return 0.0f;
    }

    public float unpack(long j, long j2, long j3, FloatLargeArray floatLargeArray, long j4) {
        long index = getIndex(j, j2, j3);
        if (index >= 0) {
            return floatLargeArray.getFloat(j4 + index);
        }
        if (index > Long.MIN_VALUE) {
            return -floatLargeArray.getFloat(j4 - index);
        }
        return 0.0f;
    }

    public float unpack(int i, int i2, int i3, float[][][] fArr) {
        int index = getIndex(i, i2, i3);
        int abs = FastMath.abs(index);
        int i4 = this.sliceStride;
        int i5 = abs / i4;
        int i6 = abs % i4;
        int i7 = this.rowStride;
        int i8 = i6 / i7;
        int i9 = i6 % i7;
        if (index >= 0) {
            return fArr[i5][i8][i9];
        }
        if (index > Integer.MIN_VALUE) {
            return -fArr[i5][i8][i9];
        }
        return 0.0f;
    }
}
