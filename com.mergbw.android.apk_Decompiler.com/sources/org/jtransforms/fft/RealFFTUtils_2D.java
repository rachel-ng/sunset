package org.jtransforms.fft;

import pl.edu.icm.jlargearrays.DoubleLargeArray;
import pl.edu.icm.jlargearrays.FloatLargeArray;

public class RealFFTUtils_2D {
    private static final int ONE = 1;
    private static final long ONEL = 1;
    private static final int TWO = 2;
    private static final long TWOL = 2;
    private static final int ZERO = 0;
    private static final long ZEROL = 0;
    private final int columns;
    private final long columnsl;
    private final int rows;
    private final long rowsl;

    public RealFFTUtils_2D(long j, long j2) {
        this.columns = (int) j2;
        this.rows = (int) j;
        this.columnsl = j2;
        this.rowsl = j;
    }

    public int getIndex(int i, int i2) {
        int i3 = i2 & 1;
        int i4 = i << 1;
        if (i == 0) {
            if (i2 != 1) {
                int i5 = this.columns;
                if (i2 != i5 + 1) {
                    if (i2 == i5) {
                        return 1;
                    }
                    if (i2 < i5) {
                        return i2;
                    }
                    return i3 == 0 ? (i5 << 1) - i2 : -(((i5 << 1) - i2) + 2);
                }
            }
            return Integer.MIN_VALUE;
        } else if (i2 <= 1) {
            int i6 = this.rows;
            if (i4 == i6) {
                if (i3 == 1) {
                    return Integer.MIN_VALUE;
                }
                return (i6 * this.columns) >> 1;
            } else if (i4 < i6) {
                return (this.columns * i) + i3;
            } else {
                if (i3 == 0) {
                    return this.columns * (i6 - i);
                }
                return -((this.columns * (i6 - i)) + 1);
            }
        } else {
            int i7 = this.columns;
            if (i2 == i7 || i2 == i7 + 1) {
                int i8 = this.rows;
                if (i4 == i8) {
                    if (i3 == 1) {
                        return Integer.MIN_VALUE;
                    }
                    return ((i8 * i7) >> 1) + 1;
                } else if (i4 < i8) {
                    return i3 == 0 ? (i7 * (i8 - i)) + 1 : -(i7 * (i8 - i));
                } else {
                    return ((i7 * i) + 1) - i3;
                }
            } else if (i2 < i7) {
                return (i7 * i) + i2;
            } else {
                if (i3 == 0) {
                    return (i7 * ((this.rows + 2) - i)) - i2;
                }
                return -(((i7 * ((this.rows + 2) - i)) - i2) + 2);
            }
        }
    }

    public long getIndex(long j, long j2) {
        long j3 = j2 & 1;
        long j4 = j << 1;
        if (j != 0) {
            if (j2 <= 1) {
                long j5 = this.rowsl;
                if (j4 == j5) {
                    if (j3 == 1) {
                        return Long.MIN_VALUE;
                    }
                    return (j5 * this.columnsl) >> 1;
                } else if (j4 < j5) {
                    return (this.columnsl * j) + j3;
                } else {
                    if (j3 == 0) {
                        return this.columnsl * (j5 - j);
                    }
                    return -((this.columnsl * (j5 - j)) + 1);
                }
            } else {
                long j6 = this.columnsl;
                if (j2 == j6 || j2 == j6 + 1) {
                    long j7 = this.rowsl;
                    if (j4 == j7) {
                        if (j3 == 1) {
                            return Long.MIN_VALUE;
                        }
                        return ((j7 * j6) >> 1) + 1;
                    } else if (j4 < j7) {
                        return j3 == 0 ? (j6 * (j7 - j)) + 1 : -(j6 * (j7 - j));
                    } else {
                        Long.signum(j6);
                        return ((j6 * j) + 1) - j3;
                    }
                } else if (j2 < j6) {
                    return (j6 * j) + j2;
                } else {
                    if (j3 == 0) {
                        return (j6 * ((this.rowsl + 2) - j)) - j2;
                    }
                    return -(((j6 * ((this.rowsl + 2) - j)) - j2) + 2);
                }
            }
        } else if (j2 == 1) {
            return Long.MIN_VALUE;
        } else {
            long j8 = this.columnsl;
            if (j2 == j8 + 1) {
                return Long.MIN_VALUE;
            }
            if (j2 == j8) {
                return 1;
            }
            if (j2 < j8) {
                return j2;
            }
            return j3 == 0 ? (j8 << 1) - j2 : -(((j8 << 1) - j2) + 2);
        }
    }

    public void pack(double d, int i, int i2, double[] dArr, int i3) {
        int index = getIndex(i, i2);
        if (index >= 0) {
            dArr[i3 + index] = d;
        } else if (index > Integer.MIN_VALUE) {
            dArr[i3 - index] = -d;
        } else {
            throw new IllegalArgumentException(String.format("[%d][%d] component cannot be modified (always zero)", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
        }
    }

    public void pack(double d, long j, long j2, DoubleLargeArray doubleLargeArray, long j3) {
        long index = getIndex(j, j2);
        if (index >= 0) {
            doubleLargeArray.setDouble(j3 + index, d);
        } else if (index > Long.MIN_VALUE) {
            doubleLargeArray.setDouble(j3 - index, -d);
        } else {
            throw new IllegalArgumentException(String.format("[%d][%d] component cannot be modified (always zero)", new Object[]{Long.valueOf(j), Long.valueOf(j2)}));
        }
    }

    public void pack(double d, int i, int i2, double[][] dArr) {
        int index = getIndex(i, i2);
        if (index >= 0) {
            int i3 = this.columns;
            dArr[index / i3][index % i3] = d;
        } else if (index > Integer.MIN_VALUE) {
            int i4 = -index;
            int i5 = this.columns;
            dArr[i4 / i5][i4 % i5] = -d;
        } else {
            throw new IllegalArgumentException(String.format("[%d][%d] component cannot be modified (always zero)", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
        }
    }

    public void pack(float f, int i, int i2, float[] fArr, int i3) {
        int index = getIndex(i, i2);
        if (index >= 0) {
            fArr[i3 + index] = f;
        } else if (index > Integer.MIN_VALUE) {
            fArr[i3 - index] = -f;
        } else {
            throw new IllegalArgumentException(String.format("[%d][%d] component cannot be modified (always zero)", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
        }
    }

    public void pack(float f, long j, long j2, FloatLargeArray floatLargeArray, long j3) {
        long index = getIndex(j, j2);
        if (index >= 0) {
            floatLargeArray.setFloat(j3 + index, f);
        } else if (index > Long.MIN_VALUE) {
            floatLargeArray.setFloat(j3 - index, -f);
        } else {
            throw new IllegalArgumentException(String.format("[%d][%d] component cannot be modified (always zero)", new Object[]{Long.valueOf(j), Long.valueOf(j2)}));
        }
    }

    public void pack(float f, int i, int i2, float[][] fArr) {
        int index = getIndex(i, i2);
        if (index >= 0) {
            int i3 = this.columns;
            fArr[index / i3][index % i3] = f;
        } else if (index > Integer.MIN_VALUE) {
            int i4 = -index;
            int i5 = this.columns;
            fArr[i4 / i5][i4 % i5] = -f;
        } else {
            throw new IllegalArgumentException(String.format("[%d][%d] component cannot be modified (always zero)", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
        }
    }

    public double unpack(int i, int i2, double[] dArr, int i3) {
        int index = getIndex(i, i2);
        if (index >= 0) {
            return dArr[i3 + index];
        }
        if (index > Integer.MIN_VALUE) {
            return -dArr[i3 - index];
        }
        return 0.0d;
    }

    public double unpack(long j, long j2, DoubleLargeArray doubleLargeArray, long j3) {
        long index = getIndex(j, j2);
        if (index >= 0) {
            return doubleLargeArray.getDouble(j3 + index);
        }
        if (index > Long.MIN_VALUE) {
            return -doubleLargeArray.getDouble(j3 - index);
        }
        return 0.0d;
    }

    public double unpack(int i, int i2, double[][] dArr) {
        int index = getIndex(i, i2);
        if (index >= 0) {
            int i3 = this.columns;
            return dArr[index / i3][index % i3];
        } else if (index <= Integer.MIN_VALUE) {
            return 0.0d;
        } else {
            int i4 = -index;
            int i5 = this.columns;
            return -dArr[i4 / i5][i4 % i5];
        }
    }

    public float unpack(int i, int i2, float[] fArr, int i3) {
        int index = getIndex(i, i2);
        if (index >= 0) {
            return fArr[i3 + index];
        }
        if (index > Integer.MIN_VALUE) {
            return -fArr[i3 - index];
        }
        return 0.0f;
    }

    public float unpack(long j, long j2, FloatLargeArray floatLargeArray, long j3) {
        long index = getIndex(j, j2);
        if (index >= 0) {
            return floatLargeArray.getFloat(j3 + index);
        }
        if (index > Long.MIN_VALUE) {
            return -floatLargeArray.getFloat(j3 - index);
        }
        return 0.0f;
    }

    public float unpack(int i, int i2, float[][] fArr) {
        int index = getIndex(i, i2);
        if (index >= 0) {
            int i3 = this.columns;
            return fArr[index / i3][index % i3];
        } else if (index <= Integer.MIN_VALUE) {
            return 0.0f;
        } else {
            int i4 = -index;
            int i5 = this.columns;
            return -fArr[i4 / i5][i4 % i5];
        }
    }
}
