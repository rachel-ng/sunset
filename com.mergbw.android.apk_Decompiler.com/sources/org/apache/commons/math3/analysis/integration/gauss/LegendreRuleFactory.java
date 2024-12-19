package org.apache.commons.math3.analysis.integration.gauss;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.Pair;

public class LegendreRuleFactory extends BaseRuleFactory<Double> {
    /* access modifiers changed from: protected */
    public Pair<Double[], Double[]> computeRule(int i) throws DimensionMismatchException {
        Double[] dArr;
        Double d;
        double d2;
        double d3;
        int i2 = i;
        Double valueOf = Double.valueOf(0.0d);
        int i3 = 1;
        if (i2 == 1) {
            return new Pair<>(new Double[]{valueOf}, new Double[]{Double.valueOf(2.0d)});
        }
        Double[] dArr2 = (Double[]) getRuleInternal(i2 - 1).getFirst();
        Double[] dArr3 = new Double[i2];
        Double[] dArr4 = new Double[i2];
        int i4 = i2 / 2;
        int i5 = 0;
        while (i5 < i4) {
            if (i5 == 0) {
                d2 = -1.0d;
            } else {
                d2 = dArr[i5 - 1].doubleValue();
            }
            if (i4 == i3) {
                d3 = 1.0d;
            } else {
                d3 = dArr[i5].doubleValue();
            }
            int i6 = i3;
            double d4 = d2;
            double d5 = 1.0d;
            while (i6 < i2) {
                int i7 = i6 + 1;
                i6 = i7;
                d5 = d4;
                d4 = (((((double) ((i6 * 2) + 1)) * d2) * d4) - (((double) i6) * d5)) / ((double) i7);
            }
            double d6 = (d2 + d3) * 0.5d;
            double d7 = d6;
            int i8 = 0;
            double d8 = 1.0d;
            while (i8 == 0) {
                i8 = d3 - d2 <= Math.ulp(d6) ? i3 : 0;
                d7 = d6;
                int i9 = i3;
                d8 = 1.0d;
                while (i9 < i2) {
                    i9++;
                    d8 = d7;
                    d = d;
                    d7 = (((((double) ((i9 * 2) + i3)) * d6) * d7) - (((double) i9) * d8)) / ((double) i9);
                    dArr = dArr;
                    i3 = 1;
                }
                Double d9 = d;
                Double[] dArr5 = dArr;
                if (i8 == 0) {
                    if (d4 * d7 <= 0.0d) {
                        d3 = d6;
                    } else {
                        d2 = d6;
                        d4 = d7;
                    }
                    d6 = (d2 + d3) * 0.5d;
                    dArr = dArr5;
                    d = d9;
                } else {
                    dArr = dArr5;
                    d = d9;
                }
                i3 = 1;
            }
            double d10 = ((double) i2) * (d8 - (d7 * d6));
            double d11 = ((1.0d - (d6 * d6)) * 2.0d) / (d10 * d10);
            dArr3[i5] = Double.valueOf(d6);
            dArr4[i5] = Double.valueOf(d11);
            int i10 = (i2 - i5) - 1;
            dArr3[i10] = Double.valueOf(-d6);
            dArr4[i10] = Double.valueOf(d11);
            i5++;
            valueOf = d;
            i3 = 1;
            dArr2 = dArr;
        }
        Double d12 = d;
        int i11 = i3;
        double d13 = 1.0d;
        if (i2 % 2 != 0) {
            for (int i12 = i11; i12 < i2; i12 += 2) {
                d13 = (((double) (-i12)) * d13) / ((double) (i12 + 1));
            }
            double d14 = ((double) i2) * d13;
            dArr3[i4] = d12;
            dArr4[i4] = Double.valueOf(2.0d / (d14 * d14));
        }
        return new Pair<>(dArr3, dArr4);
    }
}
