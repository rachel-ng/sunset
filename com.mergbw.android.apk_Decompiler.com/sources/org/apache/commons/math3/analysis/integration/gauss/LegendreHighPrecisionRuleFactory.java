package org.apache.commons.math3.analysis.integration.gauss;

import androidx.exifinterface.media.ExifInterface;
import java.math.BigDecimal;
import java.math.MathContext;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.Pair;

public class LegendreHighPrecisionRuleFactory extends BaseRuleFactory<BigDecimal> {
    private final MathContext mContext;
    private final BigDecimal minusOne;
    private final BigDecimal oneHalf;
    private final BigDecimal two;

    public LegendreHighPrecisionRuleFactory() {
        this(MathContext.DECIMAL128);
    }

    public LegendreHighPrecisionRuleFactory(MathContext mathContext) {
        this.mContext = mathContext;
        this.two = new BigDecimal(ExifInterface.GPS_MEASUREMENT_2D, mathContext);
        this.minusOne = new BigDecimal("-1", mathContext);
        this.oneHalf = new BigDecimal("0.5", mathContext);
    }

    /* access modifiers changed from: protected */
    public Pair<BigDecimal[], BigDecimal[]> computeRule(int i) throws DimensionMismatchException {
        BigDecimal bigDecimal;
        int i2 = i;
        int i3 = 1;
        if (i2 == 1) {
            return new Pair<>(new BigDecimal[]{BigDecimal.ZERO}, new BigDecimal[]{this.two});
        }
        BigDecimal[] bigDecimalArr = (BigDecimal[]) getRuleInternal(i2 - 1).getFirst();
        BigDecimal[] bigDecimalArr2 = new BigDecimal[i2];
        BigDecimal[] bigDecimalArr3 = new BigDecimal[i2];
        int i4 = i2 / 2;
        int i5 = 0;
        while (i5 < i4) {
            BigDecimal bigDecimal2 = i5 == 0 ? this.minusOne : bigDecimalArr[i5 - 1];
            BigDecimal bigDecimal3 = i4 == i3 ? BigDecimal.ONE : bigDecimalArr[i5];
            BigDecimal bigDecimal4 = BigDecimal.ONE;
            int i6 = i3;
            BigDecimal bigDecimal5 = bigDecimal3;
            BigDecimal bigDecimal6 = BigDecimal.ONE;
            BigDecimal bigDecimal7 = bigDecimal4;
            BigDecimal bigDecimal8 = bigDecimal2;
            while (i6 < i2) {
                BigDecimal[] bigDecimalArr4 = bigDecimalArr;
                BigDecimal bigDecimal9 = new BigDecimal((i6 * 2) + 1, this.mContext);
                BigDecimal bigDecimal10 = new BigDecimal(i6, this.mContext);
                int i7 = i6 + 1;
                BigDecimal bigDecimal11 = new BigDecimal(i7, this.mContext);
                int i8 = i7;
                BigDecimal divide = bigDecimal8.multiply(bigDecimal2.multiply(bigDecimal9, this.mContext), this.mContext).subtract(bigDecimal7.multiply(bigDecimal10, this.mContext), this.mContext).divide(bigDecimal11, this.mContext);
                BigDecimal divide2 = bigDecimal5.multiply(bigDecimal3.multiply(bigDecimal9, this.mContext), this.mContext).subtract(bigDecimal6.multiply(bigDecimal10, this.mContext), this.mContext).divide(bigDecimal11, this.mContext);
                bigDecimal7 = bigDecimal8;
                bigDecimal6 = bigDecimal5;
                bigDecimalArr = bigDecimalArr4;
                i4 = i4;
                bigDecimal8 = divide;
                bigDecimal5 = divide2;
                i6 = i8;
            }
            BigDecimal[] bigDecimalArr5 = bigDecimalArr;
            int i9 = i4;
            BigDecimal multiply = bigDecimal2.add(bigDecimal3, this.mContext).multiply(this.oneHalf, this.mContext);
            BigDecimal bigDecimal12 = BigDecimal.ONE;
            boolean z = false;
            BigDecimal bigDecimal13 = multiply;
            while (!z) {
                z = bigDecimal3.subtract(bigDecimal2, this.mContext).compareTo(multiply.ulp().multiply(BigDecimal.TEN, this.mContext)) <= 0;
                bigDecimal12 = BigDecimal.ONE;
                int i10 = 1;
                bigDecimal13 = multiply;
                while (i10 < i2) {
                    BigDecimal bigDecimal14 = new BigDecimal((i10 * 2) + 1, this.mContext);
                    BigDecimal bigDecimal15 = new BigDecimal(i10, this.mContext);
                    i10++;
                    bigDecimal2 = bigDecimal2;
                    BigDecimal divide3 = bigDecimal13.multiply(multiply.multiply(bigDecimal14, this.mContext), this.mContext).subtract(bigDecimal12.multiply(bigDecimal15, this.mContext), this.mContext).divide(new BigDecimal(i10, this.mContext), this.mContext);
                    bigDecimal12 = bigDecimal13;
                    bigDecimal13 = divide3;
                }
                BigDecimal bigDecimal16 = bigDecimal2;
                if (!z) {
                    if (bigDecimal8.signum() * bigDecimal13.signum() <= 0) {
                        bigDecimal3 = multiply;
                        multiply = bigDecimal16;
                    } else {
                        bigDecimal8 = bigDecimal13;
                    }
                    bigDecimal = multiply;
                    multiply = multiply.add(bigDecimal3, this.mContext).multiply(this.oneHalf, this.mContext);
                } else {
                    bigDecimal = bigDecimal16;
                }
            }
            BigDecimal divide4 = BigDecimal.ONE.subtract(multiply.pow(2, this.mContext), this.mContext).multiply(this.two, this.mContext).divide(bigDecimal12.subtract(multiply.multiply(bigDecimal13, this.mContext), this.mContext).multiply(new BigDecimal(i2, this.mContext)).pow(2, this.mContext), this.mContext);
            bigDecimalArr2[i5] = multiply;
            bigDecimalArr3[i5] = divide4;
            int i11 = (i2 - i5) - 1;
            bigDecimalArr2[i11] = multiply.negate(this.mContext);
            bigDecimalArr3[i11] = divide4;
            i5++;
            i3 = 1;
            bigDecimalArr = bigDecimalArr5;
            i4 = i9;
        }
        int i12 = i4;
        int i13 = i3;
        if (i2 % 2 != 0) {
            BigDecimal bigDecimal17 = BigDecimal.ONE;
            for (int i14 = i13; i14 < i2; i14 += 2) {
                bigDecimal17 = bigDecimal17.multiply(new BigDecimal(i14, this.mContext), this.mContext).divide(new BigDecimal(i14 + 1, this.mContext), this.mContext).negate(this.mContext);
            }
            BigDecimal divide5 = this.two.divide(bigDecimal17.multiply(new BigDecimal(i2, this.mContext), this.mContext).pow(2, this.mContext), this.mContext);
            bigDecimalArr2[i12] = BigDecimal.ZERO;
            bigDecimalArr3[i12] = divide5;
        }
        return new Pair<>(bigDecimalArr2, bigDecimalArr3);
    }
}
