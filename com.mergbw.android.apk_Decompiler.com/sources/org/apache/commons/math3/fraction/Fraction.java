package org.apache.commons.math3.fraction;

import java.io.Serializable;
import java.math.BigInteger;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.ArithmeticUtils;
import org.apache.commons.math3.util.FastMath;

public class Fraction extends Number implements FieldElement<Fraction>, Comparable<Fraction>, Serializable {
    private static final double DEFAULT_EPSILON = 1.0E-5d;
    public static final Fraction FOUR_FIFTHS = new Fraction(4, 5);
    public static final Fraction MINUS_ONE = new Fraction(-1, 1);
    public static final Fraction ONE = new Fraction(1, 1);
    public static final Fraction ONE_FIFTH = new Fraction(1, 5);
    public static final Fraction ONE_HALF = new Fraction(1, 2);
    public static final Fraction ONE_QUARTER = new Fraction(1, 4);
    public static final Fraction ONE_THIRD = new Fraction(1, 3);
    public static final Fraction THREE_FIFTHS = new Fraction(3, 5);
    public static final Fraction THREE_QUARTERS = new Fraction(3, 4);
    public static final Fraction TWO = new Fraction(2, 1);
    public static final Fraction TWO_FIFTHS = new Fraction(2, 5);
    public static final Fraction TWO_QUARTERS = new Fraction(2, 4);
    public static final Fraction TWO_THIRDS = new Fraction(2, 3);
    public static final Fraction ZERO = new Fraction(0, 1);
    private static final long serialVersionUID = 3698073679419233275L;
    private final int denominator;
    private final int numerator;

    public Fraction(double d) throws FractionConversionException {
        this(d, 1.0E-5d, 100);
    }

    public Fraction(double d, double d2, int i) throws FractionConversionException {
        this(d, d2, Integer.MAX_VALUE, i);
    }

    public Fraction(double d, int i) throws FractionConversionException {
        this(d, 0.0d, i, 100);
    }

    private Fraction(double d, double d2, int i, int i2) throws FractionConversionException {
        long j;
        long j2;
        long j3;
        double d3 = d;
        int i3 = i;
        int i4 = i2;
        long floor = (long) FastMath.floor(d);
        if (FastMath.abs(floor) <= 2147483647L) {
            int i5 = 1;
            if (FastMath.abs(((double) floor) - d3) < d2) {
                this.numerator = (int) floor;
                this.denominator = 1;
                return;
            }
            int i6 = 0;
            double d4 = d3;
            long j4 = 1;
            long j5 = 0;
            boolean z = false;
            long j6 = 1;
            long j7 = floor;
            while (true) {
                i6 += i5;
                double d5 = 1.0d / (d4 - ((double) floor));
                long floor2 = (long) FastMath.floor(d5);
                long j8 = floor;
                j = (floor2 * j7) + j6;
                long j9 = floor2;
                j2 = (floor2 * j4) + j5;
                if (FastMath.abs(j) > 2147483647L || FastMath.abs(j2) > 2147483647L) {
                    long j10 = j7;
                } else {
                    long j11 = j7;
                    boolean z2 = z;
                    double d6 = ((double) j) / ((double) j2);
                    if (i6 >= i4 || FastMath.abs(d6 - d3) <= d2 || j2 >= ((long) i3)) {
                        j7 = j11;
                        z = true;
                    } else {
                        j7 = j;
                        j5 = j4;
                        d4 = d5;
                        j6 = j11;
                        j8 = j9;
                        z = z2;
                        j4 = j2;
                    }
                    if (z) {
                        j3 = j4;
                        break;
                    } else {
                        floor = j8;
                        i5 = 1;
                    }
                }
            }
            long j102 = j7;
            if (d2 != 0.0d || FastMath.abs(j4) >= ((long) i3)) {
                throw new FractionConversionException(d, j, j2);
            }
            j3 = j4;
            j7 = j102;
            if (i6 >= i4) {
                throw new FractionConversionException(d3, i4);
            } else if (j2 < ((long) i3)) {
                this.numerator = (int) j;
                this.denominator = (int) j2;
            } else {
                this.numerator = (int) j7;
                this.denominator = (int) j3;
            }
        } else {
            throw new FractionConversionException(d, floor, 1);
        }
    }

    public Fraction(int i) {
        this(i, 1);
    }

    public Fraction(int i, int i2) {
        if (i2 != 0) {
            if (i2 < 0) {
                if (i == Integer.MIN_VALUE || i2 == Integer.MIN_VALUE) {
                    throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_FRACTION, Integer.valueOf(i), Integer.valueOf(i2));
                }
                i = -i;
                i2 = -i2;
            }
            int gcd = ArithmeticUtils.gcd(i, i2);
            if (gcd > 1) {
                i /= gcd;
                i2 /= gcd;
            }
            if (i2 < 0) {
                i = -i;
                i2 = -i2;
            }
            this.numerator = i;
            this.denominator = i2;
            return;
        }
        throw new MathArithmeticException(LocalizedFormats.ZERO_DENOMINATOR_IN_FRACTION, Integer.valueOf(i), Integer.valueOf(i2));
    }

    public Fraction abs() {
        if (this.numerator >= 0) {
            return this;
        }
        return negate();
    }

    public int compareTo(Fraction fraction) {
        int i = ((((long) this.numerator) * ((long) fraction.denominator)) > (((long) this.denominator) * ((long) fraction.numerator)) ? 1 : ((((long) this.numerator) * ((long) fraction.denominator)) == (((long) this.denominator) * ((long) fraction.numerator)) ? 0 : -1));
        if (i < 0) {
            return -1;
        }
        return i > 0 ? 1 : 0;
    }

    public double doubleValue() {
        return ((double) this.numerator) / ((double) this.denominator);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Fraction)) {
            return false;
        }
        Fraction fraction = (Fraction) obj;
        if (this.numerator == fraction.numerator && this.denominator == fraction.denominator) {
            return true;
        }
        return false;
    }

    public float floatValue() {
        return (float) doubleValue();
    }

    public int getDenominator() {
        return this.denominator;
    }

    public int getNumerator() {
        return this.numerator;
    }

    public int hashCode() {
        return ((this.numerator + 629) * 37) + this.denominator;
    }

    public int intValue() {
        return (int) doubleValue();
    }

    public long longValue() {
        return (long) doubleValue();
    }

    public Fraction negate() {
        int i = this.numerator;
        if (i != Integer.MIN_VALUE) {
            return new Fraction(-i, this.denominator);
        }
        throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_FRACTION, Integer.valueOf(this.numerator), Integer.valueOf(this.denominator));
    }

    public Fraction reciprocal() {
        return new Fraction(this.denominator, this.numerator);
    }

    public Fraction add(Fraction fraction) {
        return addSub(fraction, true);
    }

    public Fraction add(int i) {
        int i2 = this.numerator;
        int i3 = this.denominator;
        return new Fraction(i2 + (i * i3), i3);
    }

    public Fraction subtract(Fraction fraction) {
        return addSub(fraction, false);
    }

    public Fraction subtract(int i) {
        int i2 = this.numerator;
        int i3 = this.denominator;
        return new Fraction(i2 - (i * i3), i3);
    }

    private Fraction addSub(Fraction fraction, boolean z) {
        int i;
        if (fraction == null) {
            throw new NullArgumentException(LocalizedFormats.FRACTION, new Object[0]);
        } else if (this.numerator == 0) {
            return z ? fraction : fraction.negate();
        } else {
            if (fraction.numerator == 0) {
                return this;
            }
            int gcd = ArithmeticUtils.gcd(this.denominator, fraction.denominator);
            if (gcd == 1) {
                int mulAndCheck = ArithmeticUtils.mulAndCheck(this.numerator, fraction.denominator);
                int mulAndCheck2 = ArithmeticUtils.mulAndCheck(fraction.numerator, this.denominator);
                return new Fraction(z ? ArithmeticUtils.addAndCheck(mulAndCheck, mulAndCheck2) : ArithmeticUtils.subAndCheck(mulAndCheck, mulAndCheck2), ArithmeticUtils.mulAndCheck(this.denominator, fraction.denominator));
            }
            BigInteger multiply = BigInteger.valueOf((long) this.numerator).multiply(BigInteger.valueOf((long) (fraction.denominator / gcd)));
            BigInteger multiply2 = BigInteger.valueOf((long) fraction.numerator).multiply(BigInteger.valueOf((long) (this.denominator / gcd)));
            BigInteger add = z ? multiply.add(multiply2) : multiply.subtract(multiply2);
            int intValue = add.mod(BigInteger.valueOf((long) gcd)).intValue();
            if (intValue == 0) {
                i = gcd;
            } else {
                i = ArithmeticUtils.gcd(intValue, gcd);
            }
            BigInteger divide = add.divide(BigInteger.valueOf((long) i));
            if (divide.bitLength() <= 31) {
                return new Fraction(divide.intValue(), ArithmeticUtils.mulAndCheck(this.denominator / gcd, fraction.denominator / i));
            }
            throw new MathArithmeticException(LocalizedFormats.NUMERATOR_OVERFLOW_AFTER_MULTIPLY, divide);
        }
    }

    public Fraction multiply(Fraction fraction) {
        if (fraction != null) {
            int i = this.numerator;
            if (i == 0 || fraction.numerator == 0) {
                return ZERO;
            }
            int gcd = ArithmeticUtils.gcd(i, fraction.denominator);
            int gcd2 = ArithmeticUtils.gcd(fraction.numerator, this.denominator);
            return getReducedFraction(ArithmeticUtils.mulAndCheck(this.numerator / gcd, fraction.numerator / gcd2), ArithmeticUtils.mulAndCheck(this.denominator / gcd2, fraction.denominator / gcd));
        }
        throw new NullArgumentException(LocalizedFormats.FRACTION, new Object[0]);
    }

    public Fraction multiply(int i) {
        return new Fraction(this.numerator * i, this.denominator);
    }

    public Fraction divide(Fraction fraction) {
        if (fraction == null) {
            throw new NullArgumentException(LocalizedFormats.FRACTION, new Object[0]);
        } else if (fraction.numerator != 0) {
            return multiply(fraction.reciprocal());
        } else {
            throw new MathArithmeticException(LocalizedFormats.ZERO_FRACTION_TO_DIVIDE_BY, Integer.valueOf(fraction.numerator), Integer.valueOf(fraction.denominator));
        }
    }

    public Fraction divide(int i) {
        return new Fraction(this.numerator, this.denominator * i);
    }

    public double percentageValue() {
        return doubleValue() * 100.0d;
    }

    public static Fraction getReducedFraction(int i, int i2) {
        if (i2 == 0) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_DENOMINATOR_IN_FRACTION, Integer.valueOf(i), Integer.valueOf(i2));
        } else if (i == 0) {
            return ZERO;
        } else {
            if (i2 == Integer.MIN_VALUE && (i & 1) == 0) {
                i /= 2;
                i2 /= 2;
            }
            if (i2 < 0) {
                if (i == Integer.MIN_VALUE || i2 == Integer.MIN_VALUE) {
                    throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_FRACTION, Integer.valueOf(i), Integer.valueOf(i2));
                }
                i = -i;
                i2 = -i2;
            }
            int gcd = ArithmeticUtils.gcd(i, i2);
            return new Fraction(i / gcd, i2 / gcd);
        }
    }

    public String toString() {
        if (this.denominator == 1) {
            return Integer.toString(this.numerator);
        }
        if (this.numerator == 0) {
            return SessionDescription.SUPPORTED_SDP_VERSION;
        }
        return this.numerator + " / " + this.denominator;
    }

    public FractionField getField() {
        return FractionField.getInstance();
    }
}
