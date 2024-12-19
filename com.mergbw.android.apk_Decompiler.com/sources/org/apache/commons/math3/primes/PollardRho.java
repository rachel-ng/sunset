package org.apache.commons.math3.primes;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.util.FastMath;

class PollardRho {
    private PollardRho() {
    }

    public static List<Integer> primeFactors(int i) {
        ArrayList arrayList = new ArrayList();
        int smallTrialDivision = SmallPrimes.smallTrialDivision(i, arrayList);
        if (1 == smallTrialDivision) {
            return arrayList;
        }
        if (SmallPrimes.millerRabinPrimeTest(smallTrialDivision)) {
            arrayList.add(Integer.valueOf(smallTrialDivision));
            return arrayList;
        }
        int rhoBrent = rhoBrent(smallTrialDivision);
        arrayList.add(Integer.valueOf(rhoBrent));
        arrayList.add(Integer.valueOf(smallTrialDivision / rhoBrent));
        return arrayList;
    }

    static int rhoBrent(int i) {
        int i2 = i;
        int i3 = SmallPrimes.PRIMES_LAST;
        int i4 = 2;
        int i5 = 1;
        while (true) {
            int i6 = 0;
            int i7 = i4;
            for (int i8 = 0; i8 < i5; i8++) {
                long j = (long) i7;
                i7 = (int) (((j * j) + ((long) i3)) % ((long) i2));
            }
            do {
                int min = FastMath.min(25, i5 - i6);
                int i9 = -3;
                int i10 = 1;
                while (true) {
                    if (i9 >= min) {
                        break;
                    }
                    long j2 = (long) i7;
                    long j3 = (long) i2;
                    i7 = (int) (((j2 * j2) + ((long) i3)) % j3);
                    long abs = (long) FastMath.abs(i4 - i7);
                    if (0 == abs) {
                        i3 += SmallPrimes.PRIMES_LAST;
                        i6 = -25;
                        i5 = 1;
                        i7 = 2;
                        break;
                    }
                    i10 = (int) ((((long) i10) * abs) % j3);
                    if (i10 == 0) {
                        return gcdPositive(FastMath.abs((int) abs), i2);
                    }
                    i9++;
                }
                int gcdPositive = gcdPositive(FastMath.abs(i10), i2);
                if (1 != gcdPositive) {
                    return gcdPositive;
                }
                i6 += 25;
            } while (i6 < i5);
            i5 *= 2;
            i4 = i7;
        }
    }

    static int gcdPositive(int i, int i2) {
        if (i == 0) {
            return i2;
        }
        if (i2 == 0) {
            return i;
        }
        int numberOfTrailingZeros = Integer.numberOfTrailingZeros(i);
        int i3 = i >> numberOfTrailingZeros;
        int numberOfTrailingZeros2 = Integer.numberOfTrailingZeros(i2);
        int i4 = i2 >> numberOfTrailingZeros2;
        int min = FastMath.min(numberOfTrailingZeros, numberOfTrailingZeros2);
        while (i3 != i4) {
            int i5 = i3 - i4;
            i4 = FastMath.min(i3, i4);
            int abs = FastMath.abs(i5);
            i3 = abs >> Integer.numberOfTrailingZeros(abs);
        }
        return i3 << min;
    }
}
