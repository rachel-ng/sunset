package org.apache.commons.math3.geometry.partitioning.utilities;

import java.util.Arrays;
import org.apache.commons.math3.util.FastMath;

@Deprecated
public class OrderedTuple implements Comparable<OrderedTuple> {
    private static final long EXPONENT_MASK = 9218868437227405312L;
    private static final long IMPLICIT_ONE = 4503599627370496L;
    private static final long MANTISSA_MASK = 4503599627370495L;
    private static final long SIGN_MASK = Long.MIN_VALUE;
    private double[] components;
    private long[] encoding;
    private int lsb = Integer.MAX_VALUE;
    private boolean nan = false;
    private boolean negInf = false;
    private int offset;
    private boolean posInf = false;

    private static int computeLSB(long j) {
        long j2 = -4294967296L;
        int i = 32;
        int i2 = 0;
        while (i != 0) {
            if ((j & j2) == j) {
                i2 |= i;
                j >>= i;
            }
            i >>= 1;
            j2 >>= i;
        }
        return i2;
    }

    private static int computeMSB(long j) {
        long j2 = 4294967295L;
        int i = 32;
        int i2 = 0;
        while (i != 0) {
            if ((j & j2) != j) {
                i2 |= i;
                j >>= i;
            }
            i >>= 1;
            j2 >>= i;
        }
        return i2;
    }

    private static int exponent(long j) {
        return ((int) ((j & EXPONENT_MASK) >> 52)) - 1075;
    }

    private static long mantissa(long j) {
        return (EXPONENT_MASK & j) == 0 ? (j & MANTISSA_MASK) << 1 : (j & MANTISSA_MASK) | IMPLICIT_ONE;
    }

    private static long sign(long j) {
        return j & Long.MIN_VALUE;
    }

    public OrderedTuple(double... dArr) {
        this.components = (double[]) dArr.clone();
        int i = Integer.MIN_VALUE;
        for (int i2 = 0; i2 < dArr.length; i2++) {
            if (Double.isInfinite(dArr[i2])) {
                if (dArr[i2] < 0.0d) {
                    this.negInf = true;
                } else {
                    this.posInf = true;
                }
            } else if (Double.isNaN(dArr[i2])) {
                this.nan = true;
            } else {
                long doubleToLongBits = Double.doubleToLongBits(dArr[i2]);
                long mantissa = mantissa(doubleToLongBits);
                if (mantissa != 0) {
                    int exponent = exponent(doubleToLongBits);
                    i = FastMath.max(i, computeMSB(mantissa) + exponent);
                    this.lsb = FastMath.min(this.lsb, exponent + computeLSB(mantissa));
                }
            }
        }
        if (this.posInf && this.negInf) {
            this.posInf = false;
            this.negInf = false;
            this.nan = true;
        }
        if (this.lsb <= i) {
            encode(i + 16);
            return;
        }
        this.encoding = new long[]{0};
    }

    private void encode(int i) {
        int i2 = i + 31;
        this.offset = i2;
        int i3 = i2 - (i2 % 32);
        this.offset = i3;
        long[] jArr = this.encoding;
        if (jArr == null || jArr.length != 1 || jArr[0] != 0) {
            this.encoding = new long[(this.components.length * ((((i3 + 1) - this.lsb) + 62) / 63))];
            long j = 0;
            int i4 = 0;
            int i5 = 62;
            while (i4 < this.encoding.length) {
                for (int i6 = 0; i6 < this.components.length; i6++) {
                    if (getBit(i6, i3) != 0) {
                        j |= 1 << i5;
                    }
                    int i7 = i5 - 1;
                    if (i5 == 0) {
                        this.encoding[i4] = j;
                        j = 0;
                        i5 = 62;
                        i4++;
                    } else {
                        i5 = i7;
                    }
                }
                i3--;
            }
        }
    }

    public int compareTo(OrderedTuple orderedTuple) {
        double[] dArr = this.components;
        int length = dArr.length;
        double[] dArr2 = orderedTuple.components;
        if (length != dArr2.length) {
            return dArr.length - dArr2.length;
        }
        if (this.nan) {
            return 1;
        }
        if (orderedTuple.nan || this.negInf || orderedTuple.posInf) {
            return -1;
        }
        if (this.posInf || orderedTuple.negInf) {
            return 1;
        }
        int i = this.offset;
        int i2 = orderedTuple.offset;
        if (i < i2) {
            encode(i2);
        } else if (i > i2) {
            orderedTuple.encode(i);
        }
        int min = FastMath.min(this.encoding.length, orderedTuple.encoding.length);
        for (int i3 = 0; i3 < min; i3++) {
            long j = this.encoding[i3];
            long j2 = orderedTuple.encoding[i3];
            if (j < j2) {
                return -1;
            }
            if (j > j2) {
                return 1;
            }
        }
        long[] jArr = this.encoding;
        int length2 = jArr.length;
        long[] jArr2 = orderedTuple.encoding;
        if (length2 < jArr2.length) {
            return -1;
        }
        if (jArr.length > jArr2.length) {
            return 1;
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OrderedTuple)) {
            return false;
        }
        if (compareTo((OrderedTuple) obj) == 0) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 97;
        int hashCode = ((((((((Arrays.hashCode(this.components) * 37) + this.offset) * 37) + this.lsb) * 37) + (this.posInf ? 97 : 71)) * 37) + (this.negInf ? 97 : 71)) * 37;
        if (!this.nan) {
            i = 71;
        }
        return hashCode + i;
    }

    public double[] getComponents() {
        return (double[]) this.components.clone();
    }

    private int getBit(int i, int i2) {
        int i3;
        long doubleToLongBits = Double.doubleToLongBits(this.components[i]);
        int exponent = exponent(doubleToLongBits);
        if (i2 < exponent || i2 > (i3 = this.offset)) {
            return 0;
        }
        if (i2 == i3) {
            if (sign(doubleToLongBits) == 0) {
                return 1;
            }
            return 0;
        } else if (i2 <= exponent + 52) {
            int i4 = (sign(doubleToLongBits) > 0 ? 1 : (sign(doubleToLongBits) == 0 ? 0 : -1));
            long mantissa = mantissa(doubleToLongBits);
            if (i4 != 0) {
                mantissa = -mantissa;
            }
            return (int) ((mantissa >> (i2 - exponent)) & 1);
        } else if (sign(doubleToLongBits) == 0) {
            return 0;
        } else {
            return 1;
        }
    }
}
