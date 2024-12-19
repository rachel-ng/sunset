package org.apache.commons.math3.dfp;

import androidx.exifinterface.media.ExifInterface;
import androidx.work.WorkRequest;
import java.util.Arrays;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.dfp.DfpField;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.FastMath;

public class Dfp implements RealFieldElement<Dfp> {
    private static final String ADD_TRAP = "add";
    private static final String ALIGN_TRAP = "align";
    private static final String DIVIDE_TRAP = "divide";
    public static final int ERR_SCALE = 32760;
    public static final byte FINITE = 0;
    private static final String GREATER_THAN_TRAP = "greaterThan";
    public static final byte INFINITE = 1;
    private static final String LESS_THAN_TRAP = "lessThan";
    public static final int MAX_EXP = 32768;
    public static final int MIN_EXP = -32767;
    private static final String MULTIPLY_TRAP = "multiply";
    private static final String NAN_STRING = "NaN";
    private static final String NEG_INFINITY_STRING = "-Infinity";
    private static final String NEW_INSTANCE_TRAP = "newInstance";
    private static final String NEXT_AFTER_TRAP = "nextAfter";
    private static final String POS_INFINITY_STRING = "Infinity";
    public static final byte QNAN = 3;
    public static final int RADIX = 10000;
    public static final byte SNAN = 2;
    private static final String SQRT_TRAP = "sqrt";
    private static final String TRUNC_TRAP = "trunc";
    protected int exp;
    private final DfpField field;
    protected int[] mant;
    protected byte nans;
    protected byte sign;

    /* access modifiers changed from: protected */
    public Dfp trap(int i, String str, Dfp dfp, Dfp dfp2, Dfp dfp3) {
        return dfp2;
    }

    protected Dfp(DfpField dfpField) {
        this.mant = new int[dfpField.getRadixDigits()];
        this.sign = 1;
        this.exp = 0;
        this.nans = 0;
        this.field = dfpField;
    }

    protected Dfp(DfpField dfpField, byte b2) {
        this(dfpField, (long) b2);
    }

    protected Dfp(DfpField dfpField, int i) {
        this(dfpField, (long) i);
    }

    protected Dfp(DfpField dfpField, long j) {
        boolean z;
        this.mant = new int[dfpField.getRadixDigits()];
        int i = 0;
        this.nans = 0;
        this.field = dfpField;
        if (j == Long.MIN_VALUE) {
            j++;
            z = true;
        } else {
            z = false;
        }
        if (j < 0) {
            this.sign = -1;
            j = -j;
        } else {
            this.sign = 1;
        }
        this.exp = 0;
        while (j != 0) {
            int[] iArr = this.mant;
            int length = iArr.length;
            int i2 = this.exp;
            System.arraycopy(iArr, length - i2, iArr, (iArr.length - 1) - i2, i2);
            int[] iArr2 = this.mant;
            iArr2[iArr2.length - 1] = (int) (j % WorkRequest.MIN_BACKOFF_MILLIS);
            j /= WorkRequest.MIN_BACKOFF_MILLIS;
            this.exp++;
        }
        if (z) {
            while (true) {
                int[] iArr3 = this.mant;
                if (i < iArr3.length - 1) {
                    int i3 = iArr3[i];
                    if (i3 != 0) {
                        iArr3[i] = i3 + 1;
                        return;
                    }
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    protected Dfp(DfpField dfpField, double d) {
        DfpField dfpField2 = dfpField;
        this.mant = new int[dfpField.getRadixDigits()];
        this.sign = 1;
        this.exp = 0;
        this.nans = 0;
        this.field = dfpField2;
        long doubleToLongBits = Double.doubleToLongBits(d);
        long j = doubleToLongBits & 4503599627370495L;
        int i = (int) ((9218868437227405312L & doubleToLongBits) >> 52);
        int i2 = i - 1023;
        if (i2 == -1023) {
            if (d != 0.0d) {
                i2 = i - 1022;
                while ((j & 4503599627370496L) == 0) {
                    i2--;
                    j <<= 1;
                }
                j &= 4503599627370495L;
            } else if ((doubleToLongBits & Long.MIN_VALUE) != 0) {
                this.sign = -1;
                return;
            } else {
                return;
            }
        }
        if (i2 != 1024) {
            Dfp multiply = new Dfp(dfpField2, j).divide(new Dfp(dfpField2, 4503599627370496L)).add(dfpField.getOne()).multiply(DfpMath.pow(dfpField.getTwo(), i2));
            multiply = (doubleToLongBits & Long.MIN_VALUE) != 0 ? multiply.negate() : multiply;
            int[] iArr = multiply.mant;
            int[] iArr2 = this.mant;
            System.arraycopy(iArr, 0, iArr2, 0, iArr2.length);
            this.sign = multiply.sign;
            this.exp = multiply.exp;
            this.nans = multiply.nans;
        } else if (d != d) {
            this.sign = 1;
            this.nans = 3;
        } else if (d < 0.0d) {
            this.sign = -1;
            this.nans = 1;
        } else {
            this.sign = 1;
            this.nans = 1;
        }
    }

    public Dfp(Dfp dfp) {
        this.mant = (int[]) dfp.mant.clone();
        this.sign = dfp.sign;
        this.exp = dfp.exp;
        this.nans = dfp.nans;
        this.field = dfp.field;
    }

    protected Dfp(DfpField dfpField, String str) {
        int i;
        int i2;
        int i3;
        int[] iArr;
        String str2 = str;
        this.mant = new int[dfpField.getRadixDigits()];
        int i4 = 1;
        this.sign = 1;
        this.exp = 0;
        this.nans = 0;
        this.field = dfpField;
        int radixDigits = (getRadixDigits() * 4) + 8;
        char[] cArr = new char[radixDigits];
        if (str2.equals(POS_INFINITY_STRING)) {
            this.sign = 1;
            this.nans = 1;
        } else if (str2.equals(NEG_INFINITY_STRING)) {
            this.sign = -1;
            this.nans = 1;
        } else if (str2.equals(NAN_STRING)) {
            this.sign = 1;
            this.nans = 3;
        } else {
            int indexOf = str2.indexOf("e");
            indexOf = indexOf == -1 ? str2.indexOf(ExifInterface.LONGITUDE_EAST) : indexOf;
            if (indexOf != -1) {
                String substring = str2.substring(0, indexOf);
                String substring2 = str2.substring(indexOf + 1);
                boolean z = false;
                i = 0;
                for (int i5 = 0; i5 < substring2.length(); i5++) {
                    if (substring2.charAt(i5) == '-') {
                        z = true;
                    } else if (substring2.charAt(i5) >= '0' && substring2.charAt(i5) <= '9') {
                        i = ((i * 10) + substring2.charAt(i5)) - 48;
                    }
                }
                i = z ? -i : i;
                str2 = substring;
            } else {
                i = 0;
            }
            if (str2.indexOf("-") != -1) {
                this.sign = -1;
            }
            int i6 = 0;
            boolean z2 = false;
            int i7 = 0;
            while (true) {
                if (str2.charAt(i6) >= '1' && str2.charAt(i6) <= '9') {
                    break;
                }
                if (z2 && str2.charAt(i6) == '0') {
                    i7--;
                }
                z2 = str2.charAt(i6) == '.' ? true : z2;
                i6++;
                if (i6 == str2.length()) {
                    break;
                }
            }
            cArr[0] = '0';
            cArr[1] = '0';
            cArr[2] = '0';
            cArr[3] = '0';
            int i8 = i7;
            int i9 = 4;
            int i10 = 0;
            while (true) {
                if (i2 == str2.length()) {
                    i3 = 4;
                    break;
                }
                i3 = 4;
                if (i9 == (this.mant.length * 4) + 5) {
                    break;
                } else if (str2.charAt(i2) == '.') {
                    i2++;
                    i8 = i10;
                    i4 = 1;
                    z2 = true;
                } else {
                    if (str2.charAt(i2) < '0' || str2.charAt(i2) > '9') {
                        i2++;
                    } else {
                        cArr[i9] = str2.charAt(i2);
                        i9++;
                        i2++;
                        i10++;
                    }
                    i4 = 1;
                }
            }
            if (z2 && i9 != i3) {
                while (true) {
                    i9--;
                    if (i9 == i3 || cArr[i9] != '0') {
                        break;
                    }
                    i10--;
                    i3 = 4;
                }
            }
            if (z2 && i10 == 0) {
                i8 = 0;
            }
            i8 = !z2 ? i9 - 4 : i8;
            int i11 = i10 + 3;
            for (int i12 = 4; i11 > i12 && cArr[i11] == '0'; i12 = 4) {
                i11--;
            }
            int i13 = 4;
            int i14 = ((400 - i8) - (i % 4)) % 4;
            int i15 = 4 - i14;
            int i16 = i8 + i14;
            while (true) {
                int i17 = i11 - i15;
                iArr = this.mant;
                if (i17 >= iArr.length * i13) {
                    break;
                }
                int i18 = 0;
                while (i18 < i13) {
                    i11++;
                    cArr[i11] = '0';
                    i18++;
                    i13 = 4;
                }
            }
            for (int length = iArr.length - i4; length >= 0; length--) {
                this.mant[length] = ((cArr[i15] - '0') * 1000) + ((cArr[i15 + 1] - '0') * 100) + ((cArr[i15 + 2] - '0') * 10) + (cArr[i15 + 3] - '0');
                i15 += 4;
            }
            this.exp = (i16 + i) / 4;
            if (i15 < radixDigits) {
                round((cArr[i15] - '0') * 1000);
            }
        }
    }

    protected Dfp(DfpField dfpField, byte b2, byte b3) {
        this.field = dfpField;
        this.mant = new int[dfpField.getRadixDigits()];
        this.sign = b2;
        this.exp = 0;
        this.nans = b3;
    }

    public Dfp newInstance() {
        return new Dfp(getField());
    }

    public Dfp newInstance(byte b2) {
        return new Dfp(getField(), b2);
    }

    public Dfp newInstance(int i) {
        return new Dfp(getField(), i);
    }

    public Dfp newInstance(long j) {
        return new Dfp(getField(), j);
    }

    public Dfp newInstance(double d) {
        return new Dfp(getField(), d);
    }

    public Dfp newInstance(Dfp dfp) {
        if (this.field.getRadixDigits() == dfp.field.getRadixDigits()) {
            return new Dfp(dfp);
        }
        this.field.setIEEEFlagsBits(1);
        Dfp newInstance = newInstance(getZero());
        newInstance.nans = 3;
        return dotrap(1, NEW_INSTANCE_TRAP, dfp, newInstance);
    }

    public Dfp newInstance(String str) {
        return new Dfp(this.field, str);
    }

    public Dfp newInstance(byte b2, byte b3) {
        return this.field.newDfp(b2, b3);
    }

    public DfpField getField() {
        return this.field;
    }

    public int getRadixDigits() {
        return this.field.getRadixDigits();
    }

    public Dfp getZero() {
        return this.field.getZero();
    }

    public Dfp getOne() {
        return this.field.getOne();
    }

    public Dfp getTwo() {
        return this.field.getTwo();
    }

    /* access modifiers changed from: protected */
    public void shiftLeft() {
        for (int length = this.mant.length - 1; length > 0; length--) {
            int[] iArr = this.mant;
            iArr[length] = iArr[length - 1];
        }
        this.mant[0] = 0;
        this.exp--;
    }

    /* access modifiers changed from: protected */
    public void shiftRight() {
        int i = 0;
        while (true) {
            int[] iArr = this.mant;
            if (i < iArr.length - 1) {
                int i2 = i + 1;
                iArr[i] = iArr[i2];
                i = i2;
            } else {
                iArr[iArr.length - 1] = 0;
                this.exp++;
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public int align(int i) {
        int i2 = this.exp - i;
        int i3 = i2 < 0 ? -i2 : i2;
        if (i2 == 0) {
            return 0;
        }
        int[] iArr = this.mant;
        if (i3 > iArr.length + 1) {
            Arrays.fill(iArr, 0);
            this.exp = i;
            this.field.setIEEEFlagsBits(16);
            dotrap(16, ALIGN_TRAP, this, this);
            return 0;
        }
        boolean z = false;
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            if (i2 < 0) {
                if (i4 != 0) {
                    z = true;
                }
                i4 = this.mant[0];
                shiftRight();
            } else {
                shiftLeft();
            }
        }
        if (z) {
            this.field.setIEEEFlagsBits(16);
            dotrap(16, ALIGN_TRAP, this, this);
        }
        return i4;
    }

    public boolean lessThan(Dfp dfp) {
        if (this.field.getRadixDigits() != dfp.field.getRadixDigits()) {
            this.field.setIEEEFlagsBits(1);
            Dfp newInstance = newInstance(getZero());
            newInstance.nans = 3;
            dotrap(1, LESS_THAN_TRAP, dfp, newInstance);
            return false;
        } else if (isNaN() || dfp.isNaN()) {
            this.field.setIEEEFlagsBits(1);
            dotrap(1, LESS_THAN_TRAP, dfp, newInstance(getZero()));
            return false;
        } else if (compare(this, dfp) < 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean greaterThan(Dfp dfp) {
        if (this.field.getRadixDigits() != dfp.field.getRadixDigits()) {
            this.field.setIEEEFlagsBits(1);
            Dfp newInstance = newInstance(getZero());
            newInstance.nans = 3;
            dotrap(1, GREATER_THAN_TRAP, dfp, newInstance);
            return false;
        } else if (isNaN() || dfp.isNaN()) {
            this.field.setIEEEFlagsBits(1);
            dotrap(1, GREATER_THAN_TRAP, dfp, newInstance(getZero()));
            return false;
        } else if (compare(this, dfp) > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean negativeOrNull() {
        if (isNaN()) {
            this.field.setIEEEFlagsBits(1);
            dotrap(1, LESS_THAN_TRAP, this, newInstance(getZero()));
            return false;
        }
        if (this.sign >= 0) {
            int[] iArr = this.mant;
            if (iArr[iArr.length - 1] != 0 || isInfinite()) {
                return false;
            }
        }
        return true;
    }

    public boolean strictlyNegative() {
        if (isNaN()) {
            this.field.setIEEEFlagsBits(1);
            dotrap(1, LESS_THAN_TRAP, this, newInstance(getZero()));
            return false;
        } else if (this.sign >= 0) {
            return false;
        } else {
            int[] iArr = this.mant;
            if (iArr[iArr.length - 1] != 0 || isInfinite()) {
                return true;
            }
            return false;
        }
    }

    public boolean positiveOrNull() {
        if (isNaN()) {
            this.field.setIEEEFlagsBits(1);
            dotrap(1, LESS_THAN_TRAP, this, newInstance(getZero()));
            return false;
        }
        if (this.sign <= 0) {
            int[] iArr = this.mant;
            if (iArr[iArr.length - 1] != 0 || isInfinite()) {
                return false;
            }
        }
        return true;
    }

    public boolean strictlyPositive() {
        if (isNaN()) {
            this.field.setIEEEFlagsBits(1);
            dotrap(1, LESS_THAN_TRAP, this, newInstance(getZero()));
            return false;
        } else if (this.sign <= 0) {
            return false;
        } else {
            int[] iArr = this.mant;
            if (iArr[iArr.length - 1] != 0 || isInfinite()) {
                return true;
            }
            return false;
        }
    }

    public Dfp abs() {
        Dfp newInstance = newInstance(this);
        newInstance.sign = 1;
        return newInstance;
    }

    public boolean isInfinite() {
        return this.nans == 1;
    }

    public boolean isNaN() {
        byte b2 = this.nans;
        return b2 == 3 || b2 == 2;
    }

    public boolean isZero() {
        if (isNaN()) {
            this.field.setIEEEFlagsBits(1);
            dotrap(1, LESS_THAN_TRAP, this, newInstance(getZero()));
            return false;
        }
        int[] iArr = this.mant;
        if (iArr[iArr.length - 1] != 0 || isInfinite()) {
            return false;
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Dfp)) {
            return false;
        }
        Dfp dfp = (Dfp) obj;
        if (isNaN() || dfp.isNaN() || this.field.getRadixDigits() != dfp.field.getRadixDigits() || compare(this, dfp) != 0) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.sign << 8) + 17 + (this.nans << 16) + this.exp + Arrays.hashCode(this.mant);
    }

    public boolean unequal(Dfp dfp) {
        if (isNaN() || dfp.isNaN() || this.field.getRadixDigits() != dfp.field.getRadixDigits()) {
            return false;
        }
        if (greaterThan(dfp) || lessThan(dfp)) {
            return true;
        }
        return false;
    }

    private static int compare(Dfp dfp, Dfp dfp2) {
        int[] iArr = dfp.mant;
        if (iArr[iArr.length - 1] == 0) {
            int[] iArr2 = dfp2.mant;
            if (iArr2[iArr2.length - 1] == 0 && dfp.nans == 0 && dfp2.nans == 0) {
                return 0;
            }
        }
        byte b2 = dfp.sign;
        byte b3 = dfp2.sign;
        if (b2 == b3) {
            byte b4 = dfp.nans;
            if (b4 == 1 && dfp2.nans == 0) {
                return b2;
            }
            if (b4 == 0 && dfp2.nans == 1) {
                return -b3;
            }
            if (b4 == 1 && dfp2.nans == 1) {
                return 0;
            }
            int[] iArr3 = dfp2.mant;
            if (!(iArr3[iArr3.length - 1] == 0 || iArr[iArr3.length - 1] == 0)) {
                int i = dfp.exp;
                int i2 = dfp2.exp;
                if (i < i2) {
                    return -b2;
                }
                if (i > i2) {
                    return b2;
                }
            }
            for (int length = iArr.length - 1; length >= 0; length--) {
                int i3 = dfp.mant[length];
                int i4 = dfp2.mant[length];
                if (i3 > i4) {
                    return dfp.sign;
                }
                if (i3 < i4) {
                    return -dfp.sign;
                }
            }
            return 0;
        } else if (b2 == -1) {
            return -1;
        } else {
            return 1;
        }
    }

    public Dfp rint() {
        return trunc(DfpField.RoundingMode.ROUND_HALF_EVEN);
    }

    public Dfp floor() {
        return trunc(DfpField.RoundingMode.ROUND_FLOOR);
    }

    public Dfp ceil() {
        return trunc(DfpField.RoundingMode.ROUND_CEIL);
    }

    public Dfp remainder(Dfp dfp) {
        Dfp subtract = subtract(divide(dfp).rint().multiply(dfp));
        if (subtract.mant[this.mant.length - 1] == 0) {
            subtract.sign = this.sign;
        }
        return subtract;
    }

    /* access modifiers changed from: protected */
    public Dfp trunc(DfpField.RoundingMode roundingMode) {
        int i;
        if (isNaN()) {
            return newInstance(this);
        }
        if (this.nans == 1) {
            return newInstance(this);
        }
        int[] iArr = this.mant;
        if (iArr[iArr.length - 1] == 0) {
            return newInstance(this);
        }
        int i2 = this.exp;
        if (i2 < 0) {
            this.field.setIEEEFlagsBits(16);
            return dotrap(16, TRUNC_TRAP, this, newInstance(getZero()));
        } else if (i2 >= iArr.length) {
            return newInstance(this);
        } else {
            Dfp newInstance = newInstance(this);
            boolean z = false;
            for (int i3 = 0; i3 < this.mant.length - newInstance.exp; i3++) {
                int[] iArr2 = newInstance.mant;
                z |= iArr2[i3] != 0;
                iArr2[i3] = 0;
            }
            if (!z) {
                return newInstance;
            }
            int i4 = AnonymousClass1.$SwitchMap$org$apache$commons$math3$dfp$DfpField$RoundingMode[roundingMode.ordinal()];
            if (i4 != 1) {
                if (i4 != 2) {
                    Dfp newInstance2 = newInstance("0.5");
                    Dfp subtract = subtract(newInstance);
                    subtract.sign = 1;
                    if (subtract.greaterThan(newInstance2)) {
                        subtract = newInstance(getOne());
                        subtract.sign = this.sign;
                        newInstance = newInstance.add(subtract);
                    }
                    if (subtract.equals(newInstance2) && (i = newInstance.exp) > 0 && (newInstance.mant[this.mant.length - i] & 1) != 0) {
                        Dfp newInstance3 = newInstance(getOne());
                        newInstance3.sign = this.sign;
                        newInstance = newInstance.add(newInstance3);
                    }
                } else if (newInstance.sign == 1) {
                    newInstance = newInstance.add(getOne());
                }
            } else if (newInstance.sign == -1) {
                newInstance = newInstance.add(newInstance(-1));
            }
            this.field.setIEEEFlagsBits(16);
            return dotrap(16, TRUNC_TRAP, this, newInstance);
        }
    }

    public int intValue() {
        Dfp rint = rint();
        if (rint.greaterThan(newInstance(Integer.MAX_VALUE))) {
            return Integer.MAX_VALUE;
        }
        if (rint.lessThan(newInstance(Integer.MIN_VALUE))) {
            return Integer.MIN_VALUE;
        }
        int i = 0;
        for (int length = this.mant.length - 1; length >= this.mant.length - rint.exp; length--) {
            i = (i * 10000) + rint.mant[length];
        }
        return rint.sign == -1 ? -i : i;
    }

    public int log10K() {
        return this.exp - 1;
    }

    public Dfp power10K(int i) {
        Dfp newInstance = newInstance(getOne());
        newInstance.exp = i + 1;
        return newInstance;
    }

    public int intLog10() {
        int[] iArr = this.mant;
        if (iArr[iArr.length - 1] > 1000) {
            return (this.exp * 4) - 1;
        }
        if (iArr[iArr.length - 1] > 100) {
            return (this.exp * 4) - 2;
        }
        if (iArr[iArr.length - 1] > 10) {
            return (this.exp * 4) - 3;
        }
        return (this.exp * 4) - 4;
    }

    public Dfp power10(int i) {
        Dfp newInstance = newInstance(getOne());
        if (i >= 0) {
            newInstance.exp = (i / 4) + 1;
        } else {
            newInstance.exp = (i + 1) / 4;
        }
        int i2 = ((i % 4) + 4) % 4;
        if (i2 == 0) {
            return newInstance;
        }
        if (i2 == 1) {
            return newInstance.multiply(10);
        }
        if (i2 != 2) {
            return newInstance.multiply(1000);
        }
        return newInstance.multiply(100);
    }

    /* access modifiers changed from: protected */
    public int complement(int i) {
        int i2 = 10000 - i;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            int[] iArr = this.mant;
            if (i4 >= iArr.length) {
                break;
            }
            iArr[i4] = 9999 - iArr[i4];
            i4++;
        }
        int i5 = i2 / 10000;
        int i6 = i2 - (i5 * 10000);
        while (true) {
            int[] iArr2 = this.mant;
            if (i3 >= iArr2.length) {
                return i6;
            }
            int i7 = iArr2[i3] + i5;
            i5 = i7 / 10000;
            iArr2[i3] = i7 - (i5 * 10000);
            i3++;
        }
    }

    public Dfp add(Dfp dfp) {
        int i;
        int i2;
        int[] iArr;
        Dfp dfp2 = dfp;
        if (this.field.getRadixDigits() != dfp2.field.getRadixDigits()) {
            this.field.setIEEEFlagsBits(1);
            Dfp newInstance = newInstance(getZero());
            newInstance.nans = 3;
            return dotrap(1, ADD_TRAP, dfp2, newInstance);
        }
        if (!(this.nans == 0 && dfp2.nans == 0)) {
            if (isNaN()) {
                return this;
            }
            if (dfp.isNaN()) {
                return dfp2;
            }
            byte b2 = this.nans;
            if (b2 == 1 && dfp2.nans == 0) {
                return this;
            }
            byte b3 = dfp2.nans;
            if (b3 == 1 && b2 == 0) {
                return dfp2;
            }
            if (b3 == 1 && b2 == 1 && this.sign == dfp2.sign) {
                return dfp2;
            }
            if (b3 == 1 && b2 == 1 && this.sign != dfp2.sign) {
                this.field.setIEEEFlagsBits(1);
                Dfp newInstance2 = newInstance(getZero());
                newInstance2.nans = 3;
                return dotrap(1, ADD_TRAP, dfp2, newInstance2);
            }
        }
        Dfp newInstance3 = newInstance(this);
        Dfp newInstance4 = newInstance(dfp);
        Dfp newInstance5 = newInstance(getZero());
        byte b4 = newInstance3.sign;
        byte b5 = newInstance4.sign;
        newInstance3.sign = 1;
        newInstance4.sign = 1;
        byte b6 = compare(newInstance3, newInstance4) > 0 ? b4 : b5;
        int[] iArr2 = newInstance4.mant;
        int[] iArr3 = this.mant;
        if (iArr2[iArr3.length - 1] == 0) {
            newInstance4.exp = newInstance3.exp;
        }
        if (newInstance3.mant[iArr3.length - 1] == 0) {
            newInstance3.exp = newInstance4.exp;
        }
        int i3 = newInstance3.exp;
        int i4 = newInstance4.exp;
        if (i3 < i4) {
            i2 = newInstance3.align(i4);
            i = 0;
        } else {
            i = newInstance4.align(i3);
            i2 = 0;
        }
        if (b4 != b5) {
            if (b4 == b6) {
                i = newInstance4.complement(i);
            } else {
                i2 = newInstance3.complement(i2);
            }
        }
        int i5 = 0;
        for (int i6 = 0; i6 < this.mant.length; i6++) {
            int i7 = newInstance3.mant[i6] + newInstance4.mant[i6] + i5;
            i5 = i7 / 10000;
            newInstance5.mant[i6] = i7 - (i5 * 10000);
        }
        newInstance5.exp = newInstance3.exp;
        newInstance5.sign = b6;
        if (i5 != 0 && b4 == b5) {
            int i8 = newInstance5.mant[0];
            newInstance5.shiftRight();
            newInstance5.mant[this.mant.length - 1] = i5;
            int round = newInstance5.round(i8);
            if (round != 0) {
                newInstance5 = dotrap(round, ADD_TRAP, dfp2, newInstance5);
            }
        }
        int i9 = 0;
        while (true) {
            iArr = this.mant;
            if (i9 < iArr.length && newInstance5.mant[iArr.length - 1] == 0) {
                newInstance5.shiftLeft();
                if (i9 == 0) {
                    newInstance5.mant[0] = i2 + i;
                    i2 = 0;
                    i = 0;
                }
                i9++;
            }
        }
        if (newInstance5.mant[iArr.length - 1] == 0) {
            newInstance5.exp = 0;
            if (b4 != b5) {
                newInstance5.sign = 1;
            }
        }
        int round2 = newInstance5.round(i2 + i);
        return round2 != 0 ? dotrap(round2, ADD_TRAP, dfp2, newInstance5) : newInstance5;
    }

    public Dfp negate() {
        Dfp newInstance = newInstance(this);
        newInstance.sign = (byte) (-newInstance.sign);
        return newInstance;
    }

    public Dfp subtract(Dfp dfp) {
        return add(dfp.negate());
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002c, code lost:
        if (r8 >= 5000) goto L_0x0044;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002f, code lost:
        if (r8 != 0) goto L_0x0044;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003b, code lost:
        if ((r7.mant[0] & 1) != 1) goto L_0x0063;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0042, code lost:
        if (r8 != 0) goto L_0x0044;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0044, code lost:
        r0 = 0;
        r2 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0046, code lost:
        r4 = r7.mant;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0049, code lost:
        if (r0 >= r4.length) goto L_0x0058;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004b, code lost:
        r5 = r4[r0] + r2;
        r2 = r5 / 10000;
        r4[r0] = r5 - (r2 * 10000);
        r0 = r0 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0058, code lost:
        if (r2 == 0) goto L_0x0063;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x005a, code lost:
        shiftRight();
        r0 = r7.mant;
        r0[r0.length - 1] = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x001a, code lost:
        if (r8 != 0) goto L_0x0044;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0026, code lost:
        if ((r7.mant[0] & 1) != 0) goto L_0x0063;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0029, code lost:
        if (r8 > 5000) goto L_0x0044;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int round(int r8) {
        /*
            r7 = this;
            int[] r0 = org.apache.commons.math3.dfp.Dfp.AnonymousClass1.$SwitchMap$org$apache$commons$math3$dfp$DfpField$RoundingMode
            org.apache.commons.math3.dfp.DfpField r1 = r7.field
            org.apache.commons.math3.dfp.DfpField$RoundingMode r1 = r1.getRoundingMode()
            int r1 = r1.ordinal()
            r0 = r0[r1]
            r1 = 0
            r2 = 5000(0x1388, float:7.006E-42)
            r3 = 1
            switch(r0) {
                case 2: goto L_0x003e;
                case 3: goto L_0x0032;
                case 4: goto L_0x0063;
                case 5: goto L_0x002f;
                case 6: goto L_0x002c;
                case 7: goto L_0x0029;
                case 8: goto L_0x001d;
                default: goto L_0x0015;
            }
        L_0x0015:
            byte r0 = r7.sign
            r2 = -1
            if (r0 != r2) goto L_0x0063
            if (r8 == 0) goto L_0x0063
            goto L_0x0044
        L_0x001d:
            if (r8 > r2) goto L_0x0044
            if (r8 != r2) goto L_0x0063
            int[] r0 = r7.mant
            r0 = r0[r1]
            r0 = r0 & r3
            if (r0 != 0) goto L_0x0063
            goto L_0x0044
        L_0x0029:
            if (r8 <= r2) goto L_0x0063
            goto L_0x0044
        L_0x002c:
            if (r8 < r2) goto L_0x0063
            goto L_0x0044
        L_0x002f:
            if (r8 == 0) goto L_0x0063
            goto L_0x0044
        L_0x0032:
            if (r8 > r2) goto L_0x0044
            if (r8 != r2) goto L_0x0063
            int[] r0 = r7.mant
            r0 = r0[r1]
            r0 = r0 & r3
            if (r0 != r3) goto L_0x0063
            goto L_0x0044
        L_0x003e:
            byte r0 = r7.sign
            if (r0 != r3) goto L_0x0063
            if (r8 == 0) goto L_0x0063
        L_0x0044:
            r0 = r1
            r2 = r3
        L_0x0046:
            int[] r4 = r7.mant
            int r5 = r4.length
            if (r0 >= r5) goto L_0x0058
            r5 = r4[r0]
            int r5 = r5 + r2
            int r2 = r5 / 10000
            int r6 = r2 * 10000
            int r5 = r5 - r6
            r4[r0] = r5
            int r0 = r0 + 1
            goto L_0x0046
        L_0x0058:
            if (r2 == 0) goto L_0x0063
            r7.shiftRight()
            int[] r0 = r7.mant
            int r4 = r0.length
            int r4 = r4 - r3
            r0[r4] = r2
        L_0x0063:
            int r0 = r7.exp
            r2 = -32767(0xffffffffffff8001, float:NaN)
            if (r0 >= r2) goto L_0x0071
            org.apache.commons.math3.dfp.DfpField r8 = r7.field
            r0 = 8
            r8.setIEEEFlagsBits(r0)
            return r0
        L_0x0071:
            r2 = 32768(0x8000, float:4.5918E-41)
            if (r0 <= r2) goto L_0x007d
            org.apache.commons.math3.dfp.DfpField r8 = r7.field
            r0 = 4
            r8.setIEEEFlagsBits(r0)
            return r0
        L_0x007d:
            if (r8 == 0) goto L_0x0087
            org.apache.commons.math3.dfp.DfpField r8 = r7.field
            r0 = 16
            r8.setIEEEFlagsBits(r0)
            return r0
        L_0x0087:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.dfp.Dfp.round(int):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0099, code lost:
        if (r6[r6.length - 1] != 0) goto L_0x009b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00a7, code lost:
        if (r12.mant[r11.mant.length - 1] == 0) goto L_0x00a9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00a9, code lost:
        r11.field.setIEEEFlagsBits(1);
        r0 = newInstance(getZero());
        r0.nans = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00bc, code lost:
        return dotrap(1, MULTIPLY_TRAP, r12, r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.commons.math3.dfp.Dfp multiply(org.apache.commons.math3.dfp.Dfp r12) {
        /*
            r11 = this;
            org.apache.commons.math3.dfp.DfpField r0 = r11.field
            int r0 = r0.getRadixDigits()
            org.apache.commons.math3.dfp.DfpField r1 = r12.field
            int r1 = r1.getRadixDigits()
            r2 = 3
            java.lang.String r3 = "multiply"
            r4 = 1
            if (r0 == r1) goto L_0x0026
            org.apache.commons.math3.dfp.DfpField r0 = r11.field
            r0.setIEEEFlagsBits(r4)
            org.apache.commons.math3.dfp.Dfp r0 = r11.getZero()
            org.apache.commons.math3.dfp.Dfp r0 = r11.newInstance((org.apache.commons.math3.dfp.Dfp) r0)
            r0.nans = r2
            org.apache.commons.math3.dfp.Dfp r12 = r11.dotrap(r4, r3, r12, r0)
            return r12
        L_0x0026:
            org.apache.commons.math3.dfp.Dfp r0 = r11.getZero()
            org.apache.commons.math3.dfp.Dfp r0 = r11.newInstance((org.apache.commons.math3.dfp.Dfp) r0)
            byte r1 = r11.nans
            if (r1 != 0) goto L_0x0036
            byte r1 = r12.nans
            if (r1 == 0) goto L_0x00bd
        L_0x0036:
            boolean r1 = r11.isNaN()
            if (r1 == 0) goto L_0x003d
            return r11
        L_0x003d:
            boolean r1 = r12.isNaN()
            if (r1 == 0) goto L_0x0044
            return r12
        L_0x0044:
            byte r1 = r11.nans
            if (r1 != r4) goto L_0x0063
            byte r5 = r12.nans
            if (r5 != 0) goto L_0x0063
            int[] r5 = r12.mant
            int[] r6 = r11.mant
            int r6 = r6.length
            int r6 = r6 - r4
            r5 = r5[r6]
            if (r5 == 0) goto L_0x0063
            org.apache.commons.math3.dfp.Dfp r0 = r11.newInstance((org.apache.commons.math3.dfp.Dfp) r11)
            byte r1 = r11.sign
            byte r12 = r12.sign
            int r1 = r1 * r12
            byte r12 = (byte) r1
            r0.sign = r12
            return r0
        L_0x0063:
            byte r5 = r12.nans
            if (r5 != r4) goto L_0x007e
            if (r1 != 0) goto L_0x007e
            int[] r6 = r11.mant
            int r7 = r6.length
            int r7 = r7 - r4
            r6 = r6[r7]
            if (r6 == 0) goto L_0x007e
            org.apache.commons.math3.dfp.Dfp r0 = r11.newInstance((org.apache.commons.math3.dfp.Dfp) r12)
            byte r1 = r11.sign
            byte r12 = r12.sign
            int r1 = r1 * r12
            byte r12 = (byte) r1
            r0.sign = r12
            return r0
        L_0x007e:
            if (r5 != r4) goto L_0x008f
            if (r1 != r4) goto L_0x008f
            org.apache.commons.math3.dfp.Dfp r0 = r11.newInstance((org.apache.commons.math3.dfp.Dfp) r11)
            byte r1 = r11.sign
            byte r12 = r12.sign
            int r1 = r1 * r12
            byte r12 = (byte) r1
            r0.sign = r12
            return r0
        L_0x008f:
            if (r5 != r4) goto L_0x009b
            if (r1 != 0) goto L_0x009b
            int[] r6 = r11.mant
            int r7 = r6.length
            int r7 = r7 - r4
            r6 = r6[r7]
            if (r6 == 0) goto L_0x00a9
        L_0x009b:
            if (r1 != r4) goto L_0x00bd
            if (r5 != 0) goto L_0x00bd
            int[] r1 = r12.mant
            int[] r5 = r11.mant
            int r5 = r5.length
            int r5 = r5 - r4
            r1 = r1[r5]
            if (r1 != 0) goto L_0x00bd
        L_0x00a9:
            org.apache.commons.math3.dfp.DfpField r0 = r11.field
            r0.setIEEEFlagsBits(r4)
            org.apache.commons.math3.dfp.Dfp r0 = r11.getZero()
            org.apache.commons.math3.dfp.Dfp r0 = r11.newInstance((org.apache.commons.math3.dfp.Dfp) r0)
            r0.nans = r2
            org.apache.commons.math3.dfp.Dfp r12 = r11.dotrap(r4, r3, r12, r0)
            return r12
        L_0x00bd:
            int[] r1 = r11.mant
            int r1 = r1.length
            int r1 = r1 * 2
            int[] r1 = new int[r1]
            r2 = 0
            r5 = r2
        L_0x00c6:
            int[] r6 = r11.mant
            int r7 = r6.length
            if (r5 >= r7) goto L_0x00f0
            r6 = r2
            r7 = r6
        L_0x00cd:
            int[] r8 = r11.mant
            int r9 = r8.length
            if (r6 >= r9) goto L_0x00e9
            r8 = r8[r5]
            int[] r9 = r12.mant
            r9 = r9[r6]
            int r8 = r8 * r9
            int r9 = r5 + r6
            r10 = r1[r9]
            int r10 = r10 + r7
            int r8 = r8 + r10
            int r7 = r8 / 10000
            int r10 = r7 * 10000
            int r8 = r8 - r10
            r1[r9] = r8
            int r6 = r6 + 1
            goto L_0x00cd
        L_0x00e9:
            int r6 = r8.length
            int r6 = r6 + r5
            r1[r6] = r7
            int r5 = r5 + 1
            goto L_0x00c6
        L_0x00f0:
            int r5 = r6.length
            int r5 = r5 * 2
            int r5 = r5 - r4
            int r6 = r6.length
            int r6 = r6 * 2
            int r6 = r6 - r4
        L_0x00f8:
            if (r6 < 0) goto L_0x0103
            r7 = r1[r6]
            if (r7 == 0) goto L_0x0100
            r5 = r6
            goto L_0x0103
        L_0x0100:
            int r6 = r6 + -1
            goto L_0x00f8
        L_0x0103:
            r6 = r2
        L_0x0104:
            int[] r7 = r11.mant
            int r8 = r7.length
            if (r6 >= r8) goto L_0x0117
            int[] r8 = r0.mant
            int r7 = r7.length
            int r7 = r7 - r6
            int r7 = r7 - r4
            int r9 = r5 - r6
            r9 = r1[r9]
            r8[r7] = r9
            int r6 = r6 + 1
            goto L_0x0104
        L_0x0117:
            int r6 = r11.exp
            int r8 = r12.exp
            int r6 = r6 + r8
            int r6 = r6 + r5
            int r8 = r7.length
            int r8 = r8 * 2
            int r6 = r6 - r8
            int r6 = r6 + r4
            r0.exp = r6
            byte r6 = r11.sign
            byte r8 = r12.sign
            if (r6 != r8) goto L_0x012c
            r6 = r4
            goto L_0x012d
        L_0x012c:
            r6 = -1
        L_0x012d:
            byte r6 = (byte) r6
            r0.sign = r6
            int[] r6 = r0.mant
            int r8 = r7.length
            int r8 = r8 - r4
            r6 = r6[r8]
            if (r6 != 0) goto L_0x013a
            r0.exp = r2
        L_0x013a:
            int r6 = r7.length
            int r6 = r6 - r4
            if (r5 <= r6) goto L_0x0147
            int r2 = r7.length
            int r5 = r5 - r2
            r1 = r1[r5]
            int r1 = r0.round(r1)
            goto L_0x014b
        L_0x0147:
            int r1 = r0.round(r2)
        L_0x014b:
            if (r1 == 0) goto L_0x0151
            org.apache.commons.math3.dfp.Dfp r0 = r11.dotrap(r1, r3, r12, r0)
        L_0x0151:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.dfp.Dfp.multiply(org.apache.commons.math3.dfp.Dfp):org.apache.commons.math3.dfp.Dfp");
    }

    public Dfp multiply(int i) {
        if (i < 0 || i >= 10000) {
            return multiply(newInstance(i));
        }
        return multiplyFast(i);
    }

    private Dfp multiplyFast(int i) {
        int i2;
        Dfp newInstance = newInstance(this);
        if (this.nans != 0) {
            if (isNaN()) {
                return this;
            }
            byte b2 = this.nans;
            if (b2 == 1 && i != 0) {
                return newInstance(this);
            }
            if (b2 == 1 && i == 0) {
                this.field.setIEEEFlagsBits(1);
                Dfp newInstance2 = newInstance(getZero());
                newInstance2.nans = 3;
                return dotrap(1, MULTIPLY_TRAP, newInstance(getZero()), newInstance2);
            }
        }
        if (i < 0 || i >= 10000) {
            this.field.setIEEEFlagsBits(1);
            Dfp newInstance3 = newInstance(getZero());
            newInstance3.nans = 3;
            return dotrap(1, MULTIPLY_TRAP, newInstance3, newInstance3);
        }
        int i3 = 0;
        int i4 = 0;
        while (true) {
            int[] iArr = this.mant;
            if (i3 >= iArr.length) {
                break;
            }
            int i5 = (iArr[i3] * i) + i4;
            i4 = i5 / 10000;
            newInstance.mant[i3] = i5 - (i4 * 10000);
            i3++;
        }
        if (i4 != 0) {
            i2 = newInstance.mant[0];
            newInstance.shiftRight();
            newInstance.mant[this.mant.length - 1] = i4;
        } else {
            i2 = 0;
        }
        if (newInstance.mant[this.mant.length - 1] == 0) {
            newInstance.exp = 0;
        }
        int round = newInstance.round(i2);
        if (round != 0) {
            return dotrap(round, MULTIPLY_TRAP, newInstance, newInstance);
        }
        return newInstance;
    }

    public Dfp divide(Dfp dfp) {
        int[] iArr;
        int i;
        int[] iArr2;
        int i2;
        int i3;
        int i4;
        int[] iArr3;
        Dfp dfp2 = dfp;
        int i5 = 1;
        if (this.field.getRadixDigits() != dfp2.field.getRadixDigits()) {
            this.field.setIEEEFlagsBits(1);
            Dfp newInstance = newInstance(getZero());
            newInstance.nans = 3;
            return dotrap(1, DIVIDE_TRAP, dfp2, newInstance);
        }
        Dfp newInstance2 = newInstance(getZero());
        if (!(this.nans == 0 && dfp2.nans == 0)) {
            if (isNaN()) {
                return this;
            }
            if (dfp.isNaN()) {
                return dfp2;
            }
            byte b2 = this.nans;
            if (b2 == 1 && dfp2.nans == 0) {
                Dfp newInstance3 = newInstance(this);
                newInstance3.sign = (byte) (this.sign * dfp2.sign);
                return newInstance3;
            }
            byte b3 = dfp2.nans;
            if (b3 == 1 && b2 == 0) {
                Dfp newInstance4 = newInstance(getZero());
                newInstance4.sign = (byte) (this.sign * dfp2.sign);
                return newInstance4;
            } else if (b3 == 1 && b2 == 1) {
                this.field.setIEEEFlagsBits(1);
                Dfp newInstance5 = newInstance(getZero());
                newInstance5.nans = 3;
                return dotrap(1, DIVIDE_TRAP, dfp2, newInstance5);
            }
        }
        int[] iArr4 = dfp2.mant;
        int[] iArr5 = this.mant;
        int i6 = 2;
        if (iArr4[iArr5.length - 1] == 0) {
            this.field.setIEEEFlagsBits(2);
            Dfp newInstance6 = newInstance(getZero());
            newInstance6.sign = (byte) (this.sign * dfp2.sign);
            newInstance6.nans = 1;
            return dotrap(2, DIVIDE_TRAP, dfp2, newInstance6);
        }
        int[] iArr6 = new int[(iArr5.length + 1)];
        int[] iArr7 = new int[(iArr5.length + 2)];
        int[] iArr8 = new int[(iArr5.length + 1)];
        iArr6[iArr5.length] = 0;
        iArr7[iArr5.length] = 0;
        iArr7[iArr5.length + 1] = 0;
        iArr8[iArr5.length] = 0;
        int i7 = 0;
        while (true) {
            iArr = this.mant;
            if (i7 >= iArr.length) {
                break;
            }
            iArr6[i7] = iArr[i7];
            iArr7[i7] = 0;
            iArr8[i7] = 0;
            i7++;
        }
        int length = iArr.length + 1;
        int i8 = 0;
        int i9 = 0;
        while (length >= 0) {
            int[] iArr9 = this.mant;
            int i10 = (iArr6[iArr9.length] * 10000) + iArr6[iArr9.length - i];
            int[] iArr10 = dfp2.mant;
            int i11 = i10 / (iArr10[iArr9.length - i] + i);
            int i12 = (i10 + i) / iArr10[iArr9.length - i];
            boolean z = false;
            while (!z) {
                i9 = (i11 + i12) / i6;
                int i13 = 0;
                int i14 = 0;
                while (true) {
                    int[] iArr11 = this.mant;
                    i4 = i11;
                    if (i13 >= iArr11.length + i) {
                        break;
                    }
                    int i15 = ((i13 < iArr11.length ? dfp2.mant[i13] : 0) * i9) + i14;
                    int i16 = i15 / 10000;
                    iArr8[i13] = i15 - (i16 * 10000);
                    i13++;
                    i14 = i16;
                    i11 = i4;
                    i = 1;
                }
                int i17 = 0;
                int i18 = 1;
                while (true) {
                    iArr3 = this.mant;
                    if (i17 >= iArr3.length + 1) {
                        break;
                    }
                    int i19 = (9999 - iArr8[i17]) + iArr6[i17] + i18;
                    i18 = i19 / 10000;
                    iArr8[i17] = i19 - (i18 * 10000);
                    i17++;
                }
                if (i18 == 0) {
                    i12 = i9 - 1;
                    i11 = i4;
                    i = 1;
                    i6 = 2;
                } else {
                    boolean z2 = z;
                    int i20 = ((iArr8[iArr3.length] * 10000) + iArr8[iArr3.length - 1]) / (dfp2.mant[iArr3.length - 1] + 1);
                    i6 = 2;
                    if (i20 >= 2) {
                        i11 = i9 + i20;
                        i = 1;
                        z = z2;
                    } else {
                        boolean z3 = false;
                        for (int length2 = iArr3.length - 1; length2 >= 0; length2--) {
                            int i21 = dfp2.mant[length2];
                            int i22 = iArr8[length2];
                            if (i21 > i22) {
                                z3 = true;
                            }
                            if (i21 < i22) {
                                break;
                            }
                        }
                        z = iArr8[this.mant.length] != 0 ? false : z3;
                        i11 = !z ? i9 + 1 : i4;
                        i = 1;
                    }
                }
            }
            iArr7[length] = i9;
            if (!(i9 == 0 && i8 == 0)) {
                i8++;
            }
            if ((this.field.getRoundingMode() == DfpField.RoundingMode.ROUND_DOWN && i8 == this.mant.length) || i8 > this.mant.length) {
                break;
            }
            iArr6[0] = 0;
            int i23 = 0;
            while (i23 < this.mant.length) {
                int i24 = i23 + 1;
                iArr6[i24] = iArr8[i23];
                i23 = i24;
            }
            length--;
            i5 = 1;
        }
        int[] iArr12 = this.mant;
        int length3 = iArr12.length;
        int length4 = iArr12.length + 1;
        while (true) {
            if (length4 < 0) {
                break;
            } else if (iArr7[length4] != 0) {
                length3 = length4;
                break;
            } else {
                length4--;
            }
        }
        int i25 = 0;
        while (true) {
            iArr2 = this.mant;
            if (i25 >= iArr2.length) {
                break;
            }
            newInstance2.mant[(iArr2.length - i25) - 1] = iArr7[length3 - i25];
            i25++;
        }
        newInstance2.exp = ((this.exp - dfp2.exp) + length3) - iArr2.length;
        newInstance2.sign = (byte) (this.sign == dfp2.sign ? 1 : -1);
        if (newInstance2.mant[iArr2.length - 1] == 0) {
            i2 = 0;
            newInstance2.exp = 0;
        } else {
            i2 = 0;
        }
        if (length3 > iArr2.length - 1) {
            i3 = newInstance2.round(iArr7[length3 - iArr2.length]);
        } else {
            i3 = newInstance2.round(i2);
        }
        return i3 != 0 ? dotrap(i3, DIVIDE_TRAP, dfp2, newInstance2) : newInstance2;
    }

    public Dfp divide(int i) {
        if (this.nans != 0) {
            if (isNaN()) {
                return this;
            }
            if (this.nans == 1) {
                return newInstance(this);
            }
        }
        if (i == 0) {
            this.field.setIEEEFlagsBits(2);
            Dfp newInstance = newInstance(getZero());
            newInstance.sign = this.sign;
            newInstance.nans = 1;
            return dotrap(2, DIVIDE_TRAP, getZero(), newInstance);
        } else if (i < 0 || i >= 10000) {
            this.field.setIEEEFlagsBits(1);
            Dfp newInstance2 = newInstance(getZero());
            newInstance2.nans = 3;
            return dotrap(1, DIVIDE_TRAP, newInstance2, newInstance2);
        } else {
            Dfp newInstance3 = newInstance(this);
            int i2 = 0;
            for (int length = this.mant.length - 1; length >= 0; length--) {
                int[] iArr = newInstance3.mant;
                int i3 = (i2 * 10000) + iArr[length];
                int i4 = i3 / i;
                i2 = i3 - (i4 * i);
                iArr[length] = i4;
            }
            if (newInstance3.mant[this.mant.length - 1] == 0) {
                newInstance3.shiftLeft();
                int i5 = i2 * 10000;
                int i6 = i5 / i;
                i2 = i5 - (i6 * i);
                newInstance3.mant[0] = i6;
            }
            int round = newInstance3.round((i2 * 10000) / i);
            if (round != 0) {
                return dotrap(round, DIVIDE_TRAP, newInstance3, newInstance3);
            }
            return newInstance3;
        }
    }

    public Dfp reciprocal() {
        return this.field.getOne().divide(this);
    }

    public Dfp sqrt() {
        byte b2 = this.nans;
        if (b2 == 0) {
            int[] iArr = this.mant;
            if (iArr[iArr.length - 1] == 0) {
                return newInstance(this);
            }
        }
        if (b2 != 0) {
            if (b2 == 1 && this.sign == 1) {
                return newInstance(this);
            }
            if (b2 == 3) {
                return newInstance(this);
            }
            if (b2 == 2) {
                this.field.setIEEEFlagsBits(1);
                return dotrap(1, SQRT_TRAP, (Dfp) null, newInstance(this));
            }
        }
        if (this.sign == -1) {
            this.field.setIEEEFlagsBits(1);
            Dfp newInstance = newInstance(this);
            newInstance.nans = 3;
            return dotrap(1, SQRT_TRAP, (Dfp) null, newInstance);
        }
        Dfp newInstance2 = newInstance(this);
        int i = newInstance2.exp;
        if (i < -1 || i > 1) {
            newInstance2.exp = this.exp / 2;
        }
        int[] iArr2 = newInstance2.mant;
        int[] iArr3 = this.mant;
        int i2 = iArr2[iArr3.length - 1] / 2000;
        if (i2 == 0) {
            iArr2[iArr3.length - 1] = (iArr2[iArr3.length - 1] / 2) + 1;
        } else if (i2 == 2) {
            iArr2[iArr3.length - 1] = 1500;
        } else if (i2 != 3) {
            iArr2[iArr3.length - 1] = 3000;
        } else {
            iArr2[iArr3.length - 1] = 2200;
        }
        newInstance(newInstance2);
        Dfp zero = getZero();
        getZero();
        while (newInstance2.unequal(zero)) {
            Dfp newInstance3 = newInstance(newInstance2);
            newInstance3.sign = -1;
            Dfp divide = newInstance3.add(divide(newInstance2)).divide(2);
            Dfp add = newInstance2.add(divide);
            if (add.equals(zero) || divide.mant[this.mant.length - 1] == 0) {
                return add;
            }
            zero = newInstance2;
            newInstance2 = add;
        }
        return newInstance2;
    }

    public String toString() {
        byte b2 = this.nans;
        if (b2 == 0) {
            int i = this.exp;
            if (i > this.mant.length || i < -1) {
                return dfp2sci();
            }
            return dfp2string();
        } else if (b2 == 1) {
            return this.sign < 0 ? NEG_INFINITY_STRING : POS_INFINITY_STRING;
        } else {
            return NAN_STRING;
        }
    }

    /* access modifiers changed from: protected */
    public String dfp2sci() {
        int i;
        int[] iArr = this.mant;
        int length = iArr.length * 4;
        char[] cArr = new char[length];
        char[] cArr2 = new char[((iArr.length * 4) + 20)];
        int i2 = 0;
        for (int length2 = iArr.length - 1; length2 >= 0; length2--) {
            int i3 = this.mant[length2];
            cArr[i2] = (char) ((i3 / 1000) + 48);
            cArr[i2 + 1] = (char) (((i3 / 100) % 10) + 48);
            int i4 = i2 + 3;
            cArr[i2 + 2] = (char) (((i3 / 10) % 10) + 48);
            i2 += 4;
            cArr[i4] = (char) ((i3 % 10) + 48);
        }
        int i5 = 0;
        while (i5 < length && cArr[i5] == '0') {
            i5++;
        }
        if (this.sign == -1) {
            cArr2[0] = '-';
            i = 1;
        } else {
            i = 0;
        }
        if (i5 != length) {
            int i6 = i + 1;
            cArr2[i] = cArr[i5];
            int i7 = i + 2;
            cArr2[i6] = '.';
            for (int i8 = i5 + 1; i8 < length; i8++) {
                cArr2[i7] = cArr[i8];
                i7++;
            }
            int i9 = i7 + 1;
            cArr2[i7] = 'e';
            int i10 = ((this.exp * 4) - i5) - 1;
            int i11 = i10 < 0 ? -i10 : i10;
            int i12 = 1000000000;
            while (i12 > i11) {
                i12 /= 10;
            }
            if (i10 < 0) {
                cArr2[i9] = '-';
                i9 = i7 + 2;
            }
            while (i12 > 0) {
                cArr2[i9] = (char) ((i11 / i12) + 48);
                i11 %= i12;
                i12 /= 10;
                i9++;
            }
            return new String(cArr2, 0, i9);
        }
        cArr2[i] = '0';
        cArr2[i + 1] = '.';
        cArr2[i + 2] = '0';
        cArr2[i + 3] = 'e';
        cArr2[i + 4] = '0';
        return new String(cArr2, 0, 5);
    }

    /* access modifiers changed from: protected */
    public String dfp2string() {
        boolean z;
        int i;
        char c2;
        int i2;
        char[] cArr = new char[((this.mant.length * 4) + 20)];
        int i3 = this.exp;
        cArr[0] = ' ';
        int i4 = 1;
        if (i3 <= 0) {
            cArr[1] = '0';
            cArr[2] = '.';
            i = 3;
            z = true;
        } else {
            z = false;
            i = 1;
        }
        while (i3 < 0) {
            cArr[i] = '0';
            cArr[i + 1] = '0';
            int i5 = i + 3;
            cArr[i + 2] = '0';
            i += 4;
            cArr[i5] = '0';
            i3++;
        }
        for (int length = this.mant.length - 1; length >= 0; length--) {
            int i6 = this.mant[length];
            cArr[i] = (char) ((i6 / 1000) + 48);
            cArr[i + 1] = (char) (((i6 / 100) % 10) + 48);
            cArr[i + 2] = (char) (((i6 / 10) % 10) + 48);
            int i7 = i + 4;
            cArr[i + 3] = (char) ((i6 % 10) + 48);
            i3--;
            if (i3 == 0) {
                i2 = i + 5;
                cArr[i7] = '.';
                z = true;
            } else {
                i2 = i7;
            }
        }
        while (i3 > 0) {
            cArr[i] = '0';
            cArr[i + 1] = '0';
            int i8 = i + 3;
            cArr[i + 2] = '0';
            i += 4;
            cArr[i8] = '0';
            i3--;
        }
        if (!z) {
            cArr[i] = '.';
            i++;
        }
        while (true) {
            c2 = cArr[i4];
            if (c2 != '0') {
                break;
            }
            i4++;
        }
        if (c2 == '.') {
            i4--;
        }
        while (cArr[i - 1] == '0') {
            i--;
        }
        if (this.sign < 0) {
            i4--;
            cArr[i4] = '-';
        }
        return new String(cArr, i4, i - i4);
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x006e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.commons.math3.dfp.Dfp dotrap(int r10, java.lang.String r11, org.apache.commons.math3.dfp.Dfp r12, org.apache.commons.math3.dfp.Dfp r13) {
        /*
            r9 = this;
            r0 = 3
            r1 = 1
            if (r10 == r1) goto L_0x00a4
            r2 = 2
            if (r10 == r2) goto L_0x004a
            r0 = 4
            if (r10 == r0) goto L_0x0033
            r0 = 8
            if (r10 == r0) goto L_0x0011
            r7 = r13
            goto L_0x00b3
        L_0x0011:
            int r0 = r13.exp
            int[] r1 = r9.mant
            int r1 = r1.length
            int r0 = r0 + r1
            r1 = -32767(0xffffffffffff8001, float:NaN)
            if (r0 >= r1) goto L_0x0028
            org.apache.commons.math3.dfp.Dfp r0 = r9.getZero()
            org.apache.commons.math3.dfp.Dfp r0 = r9.newInstance((org.apache.commons.math3.dfp.Dfp) r0)
            byte r1 = r13.sign
            r0.sign = r1
            goto L_0x002c
        L_0x0028:
            org.apache.commons.math3.dfp.Dfp r0 = r9.newInstance((org.apache.commons.math3.dfp.Dfp) r13)
        L_0x002c:
            int r1 = r13.exp
            int r1 = r1 + 32760
            r13.exp = r1
            goto L_0x0047
        L_0x0033:
            int r0 = r13.exp
            int r0 = r0 + -32760
            r13.exp = r0
            org.apache.commons.math3.dfp.Dfp r0 = r9.getZero()
            org.apache.commons.math3.dfp.Dfp r0 = r9.newInstance((org.apache.commons.math3.dfp.Dfp) r0)
            byte r2 = r13.sign
            r0.sign = r2
            r0.nans = r1
        L_0x0047:
            r7 = r0
            goto L_0x00b3
        L_0x004a:
            byte r3 = r9.nans
            if (r3 != 0) goto L_0x0069
            int[] r3 = r9.mant
            int r4 = r3.length
            int r4 = r4 - r1
            r3 = r3[r4]
            if (r3 == 0) goto L_0x0069
            org.apache.commons.math3.dfp.Dfp r3 = r9.getZero()
            org.apache.commons.math3.dfp.Dfp r3 = r9.newInstance((org.apache.commons.math3.dfp.Dfp) r3)
            byte r4 = r9.sign
            byte r5 = r12.sign
            int r4 = r4 * r5
            byte r4 = (byte) r4
            r3.sign = r4
            r3.nans = r1
            goto L_0x006a
        L_0x0069:
            r3 = r13
        L_0x006a:
            byte r4 = r9.nans
            if (r4 != 0) goto L_0x0080
            int[] r4 = r9.mant
            int r5 = r4.length
            int r5 = r5 - r1
            r4 = r4[r5]
            if (r4 != 0) goto L_0x0080
            org.apache.commons.math3.dfp.Dfp r3 = r9.getZero()
            org.apache.commons.math3.dfp.Dfp r3 = r9.newInstance((org.apache.commons.math3.dfp.Dfp) r3)
            r3.nans = r0
        L_0x0080:
            byte r4 = r9.nans
            if (r4 == r1) goto L_0x0086
            if (r4 != r0) goto L_0x0090
        L_0x0086:
            org.apache.commons.math3.dfp.Dfp r3 = r9.getZero()
            org.apache.commons.math3.dfp.Dfp r3 = r9.newInstance((org.apache.commons.math3.dfp.Dfp) r3)
            r3.nans = r0
        L_0x0090:
            byte r4 = r9.nans
            if (r4 == r1) goto L_0x0099
            if (r4 != r2) goto L_0x0097
            goto L_0x0099
        L_0x0097:
            r7 = r3
            goto L_0x00b3
        L_0x0099:
            org.apache.commons.math3.dfp.Dfp r1 = r9.getZero()
            org.apache.commons.math3.dfp.Dfp r1 = r9.newInstance((org.apache.commons.math3.dfp.Dfp) r1)
            r1.nans = r0
            goto L_0x00b2
        L_0x00a4:
            org.apache.commons.math3.dfp.Dfp r1 = r9.getZero()
            org.apache.commons.math3.dfp.Dfp r1 = r9.newInstance((org.apache.commons.math3.dfp.Dfp) r1)
            byte r2 = r13.sign
            r1.sign = r2
            r1.nans = r0
        L_0x00b2:
            r7 = r1
        L_0x00b3:
            r3 = r9
            r4 = r10
            r5 = r11
            r6 = r12
            r8 = r13
            org.apache.commons.math3.dfp.Dfp r10 = r3.trap(r4, r5, r6, r7, r8)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.dfp.Dfp.dotrap(int, java.lang.String, org.apache.commons.math3.dfp.Dfp, org.apache.commons.math3.dfp.Dfp):org.apache.commons.math3.dfp.Dfp");
    }

    public int classify() {
        return this.nans;
    }

    public static Dfp copysign(Dfp dfp, Dfp dfp2) {
        Dfp newInstance = dfp.newInstance(dfp);
        newInstance.sign = dfp2.sign;
        return newInstance;
    }

    public Dfp nextAfter(Dfp dfp) {
        Dfp dfp2;
        if (this.field.getRadixDigits() != dfp.field.getRadixDigits()) {
            this.field.setIEEEFlagsBits(1);
            Dfp newInstance = newInstance(getZero());
            newInstance.nans = 3;
            return dotrap(1, NEXT_AFTER_TRAP, dfp, newInstance);
        }
        boolean lessThan = lessThan(dfp);
        if (compare(this, dfp) == 0) {
            return newInstance(dfp);
        }
        if (lessThan(getZero())) {
            lessThan = !lessThan;
        }
        if (lessThan) {
            Dfp newInstance2 = newInstance(getOne());
            newInstance2.exp = (this.exp - this.mant.length) + 1;
            newInstance2.sign = this.sign;
            if (equals(getZero())) {
                newInstance2.exp = -32767 - this.mant.length;
            }
            dfp2 = add(newInstance2);
        } else {
            Dfp newInstance3 = newInstance(getOne());
            newInstance3.exp = this.exp;
            newInstance3.sign = this.sign;
            if (equals(newInstance3)) {
                newInstance3.exp = this.exp - this.mant.length;
            } else {
                newInstance3.exp = (this.exp - this.mant.length) + 1;
            }
            if (equals(getZero())) {
                newInstance3.exp = -32767 - this.mant.length;
            }
            dfp2 = subtract(newInstance3);
        }
        if (dfp2.classify() == 1 && classify() != 1) {
            this.field.setIEEEFlagsBits(16);
            dfp2 = dotrap(16, NEXT_AFTER_TRAP, dfp, dfp2);
        }
        if (!dfp2.equals(getZero()) || equals(getZero())) {
            return dfp2;
        }
        this.field.setIEEEFlagsBits(16);
        return dotrap(16, NEXT_AFTER_TRAP, dfp, dfp2);
    }

    public double toDouble() {
        boolean z;
        Dfp dfp;
        if (isInfinite()) {
            return lessThan(getZero()) ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
        }
        if (isNaN()) {
            return Double.NaN;
        }
        int compare = compare(this, getZero());
        if (compare != 0) {
            if (compare < 0) {
                dfp = negate();
                z = true;
            } else {
                dfp = this;
                z = false;
            }
            int intLog10 = (int) (((double) dfp.intLog10()) * 3.32d);
            if (intLog10 < 0) {
                intLog10--;
            }
            Dfp pow = DfpMath.pow(getTwo(), intLog10);
            while (true) {
                if (!pow.lessThan(dfp) && !pow.equals(dfp)) {
                    break;
                }
                pow = pow.multiply(2);
                intLog10++;
            }
            int i = intLog10 - 1;
            Dfp divide = dfp.divide(DfpMath.pow(getTwo(), i));
            if (i > -1023) {
                divide = divide.subtract(getOne());
            }
            if (i < -1074) {
                return 0.0d;
            }
            if (i <= 1023) {
                String dfp2 = divide.multiply(newInstance(4503599627370496L)).rint().toString();
                long parseLong = Long.parseLong(dfp2.substring(0, dfp2.length() - 1));
                if (parseLong == 4503599627370496L) {
                    parseLong = 0;
                } else {
                    intLog10 = i;
                }
                if (intLog10 <= -1023) {
                    intLog10--;
                }
                while (intLog10 < -1023) {
                    intLog10++;
                    parseLong >>>= 1;
                }
                double longBitsToDouble = Double.longBitsToDouble(((((long) intLog10) + 1023) << 52) | parseLong);
                return z ? -longBitsToDouble : longBitsToDouble;
            } else if (z) {
                return Double.NEGATIVE_INFINITY;
            } else {
                return Double.POSITIVE_INFINITY;
            }
        } else if (this.sign < 0) {
            return -0.0d;
        } else {
            return 0.0d;
        }
    }

    public double[] toSplitDouble() {
        double longBitsToDouble = Double.longBitsToDouble(Double.doubleToLongBits(toDouble()) & -1073741824);
        return new double[]{longBitsToDouble, subtract(newInstance(longBitsToDouble)).toDouble()};
    }

    public double getReal() {
        return toDouble();
    }

    public Dfp add(double d) {
        return add(newInstance(d));
    }

    public Dfp subtract(double d) {
        return subtract(newInstance(d));
    }

    public Dfp multiply(double d) {
        return multiply(newInstance(d));
    }

    public Dfp divide(double d) {
        return divide(newInstance(d));
    }

    public Dfp remainder(double d) {
        return remainder(newInstance(d));
    }

    public long round() {
        return FastMath.round(toDouble());
    }

    public Dfp signum() {
        if (isNaN() || isZero()) {
            return this;
        }
        return newInstance(this.sign > 0 ? 1 : -1);
    }

    public Dfp copySign(Dfp dfp) {
        byte b2 = this.sign;
        if ((b2 < 0 || dfp.sign < 0) && (b2 >= 0 || dfp.sign >= 0)) {
            return negate();
        }
        return this;
    }

    public Dfp copySign(double d) {
        long doubleToLongBits = Double.doubleToLongBits(d);
        byte b2 = this.sign;
        if ((b2 < 0 || doubleToLongBits < 0) && (b2 >= 0 || doubleToLongBits >= 0)) {
            return negate();
        }
        return this;
    }

    public Dfp scalb(int i) {
        return multiply(DfpMath.pow(getTwo(), i));
    }

    public Dfp hypot(Dfp dfp) {
        return multiply(this).add(dfp.multiply(dfp)).sqrt();
    }

    public Dfp cbrt() {
        return rootN(3);
    }

    public Dfp rootN(int i) {
        return this.sign >= 0 ? DfpMath.pow(this, getOne().divide(i)) : DfpMath.pow(negate(), getOne().divide(i)).negate();
    }

    public Dfp pow(double d) {
        return DfpMath.pow(this, newInstance(d));
    }

    public Dfp pow(int i) {
        return DfpMath.pow(this, i);
    }

    public Dfp pow(Dfp dfp) {
        return DfpMath.pow(this, dfp);
    }

    public Dfp exp() {
        return DfpMath.exp(this);
    }

    public Dfp expm1() {
        return DfpMath.exp(this).subtract(getOne());
    }

    public Dfp log() {
        return DfpMath.log(this);
    }

    public Dfp log1p() {
        return DfpMath.log(add(getOne()));
    }

    @Deprecated
    public int log10() {
        return intLog10();
    }

    public Dfp cos() {
        return DfpMath.cos(this);
    }

    public Dfp sin() {
        return DfpMath.sin(this);
    }

    public Dfp tan() {
        return DfpMath.tan(this);
    }

    public Dfp acos() {
        return DfpMath.acos(this);
    }

    public Dfp asin() {
        return DfpMath.asin(this);
    }

    public Dfp atan() {
        return DfpMath.atan(this);
    }

    public Dfp atan2(Dfp dfp) throws DimensionMismatchException {
        Dfp sqrt = dfp.multiply(dfp).add(multiply(this)).sqrt();
        if (dfp.sign >= 0) {
            return getTwo().multiply(divide(sqrt.add(dfp)).atan());
        }
        Dfp multiply = getTwo().multiply(divide(sqrt.subtract(dfp)).atan());
        return newInstance(multiply.sign <= 0 ? -3.141592653589793d : 3.141592653589793d).subtract(multiply);
    }

    public Dfp cosh() {
        return DfpMath.exp(this).add(DfpMath.exp(negate())).divide(2);
    }

    public Dfp sinh() {
        return DfpMath.exp(this).subtract(DfpMath.exp(negate())).divide(2);
    }

    public Dfp tanh() {
        Dfp exp2 = DfpMath.exp(this);
        Dfp exp3 = DfpMath.exp(negate());
        return exp2.subtract(exp3).divide(exp2.add(exp3));
    }

    public Dfp acosh() {
        return multiply(this).subtract(getOne()).sqrt().add(this).log();
    }

    public Dfp asinh() {
        return multiply(this).add(getOne()).sqrt().add(this).log();
    }

    public Dfp atanh() {
        return getOne().add(this).divide(getOne().subtract(this)).log().divide(2);
    }

    public Dfp linearCombination(Dfp[] dfpArr, Dfp[] dfpArr2) throws DimensionMismatchException {
        if (dfpArr.length == dfpArr2.length) {
            Dfp zero = getZero();
            for (int i = 0; i < dfpArr.length; i++) {
                zero = zero.add(dfpArr[i].multiply(dfpArr2[i]));
            }
            return zero;
        }
        throw new DimensionMismatchException(dfpArr.length, dfpArr2.length);
    }

    public Dfp linearCombination(double[] dArr, Dfp[] dfpArr) throws DimensionMismatchException {
        if (dArr.length == dfpArr.length) {
            Dfp zero = getZero();
            for (int i = 0; i < dArr.length; i++) {
                zero = zero.add(dfpArr[i].multiply(dArr[i]));
            }
            return zero;
        }
        throw new DimensionMismatchException(dArr.length, dfpArr.length);
    }

    public Dfp linearCombination(Dfp dfp, Dfp dfp2, Dfp dfp3, Dfp dfp4) {
        return dfp.multiply(dfp2).add(dfp3.multiply(dfp4));
    }

    public Dfp linearCombination(double d, Dfp dfp, double d2, Dfp dfp2) {
        return dfp.multiply(d).add(dfp2.multiply(d2));
    }

    public Dfp linearCombination(Dfp dfp, Dfp dfp2, Dfp dfp3, Dfp dfp4, Dfp dfp5, Dfp dfp6) {
        return dfp.multiply(dfp2).add(dfp3.multiply(dfp4)).add(dfp5.multiply(dfp6));
    }

    public Dfp linearCombination(double d, Dfp dfp, double d2, Dfp dfp2, double d3, Dfp dfp3) {
        return dfp.multiply(d).add(dfp2.multiply(d2)).add(dfp3.multiply(d3));
    }

    public Dfp linearCombination(Dfp dfp, Dfp dfp2, Dfp dfp3, Dfp dfp4, Dfp dfp5, Dfp dfp6, Dfp dfp7, Dfp dfp8) {
        return dfp.multiply(dfp2).add(dfp3.multiply(dfp4)).add(dfp5.multiply(dfp6)).add(dfp7.multiply(dfp8));
    }

    public Dfp linearCombination(double d, Dfp dfp, double d2, Dfp dfp2, double d3, Dfp dfp3, double d4, Dfp dfp4) {
        return dfp.multiply(d).add(dfp2.multiply(d2)).add(dfp3.multiply(d3)).add(dfp4.multiply(d4));
    }
}
