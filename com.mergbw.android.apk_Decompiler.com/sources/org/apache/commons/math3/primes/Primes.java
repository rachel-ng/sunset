package org.apache.commons.math3.primes;

import java.util.List;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class Primes {
    private Primes() {
    }

    public static boolean isPrime(int i) {
        if (i < 2) {
            return false;
        }
        int[] iArr = SmallPrimes.PRIMES;
        int length = iArr.length;
        int i2 = 0;
        while (i2 < length) {
            int i3 = iArr[i2];
            if (i % i3 != 0) {
                i2++;
            } else if (i == i3) {
                return true;
            } else {
                return false;
            }
        }
        return SmallPrimes.millerRabinPrimeTest(i);
    }

    public static int nextPrime(int i) {
        int i2;
        if (i < 0) {
            throw new MathIllegalArgumentException(LocalizedFormats.NUMBER_TOO_SMALL, Integer.valueOf(i), 0);
        } else if (i == 2 || (i2 = i | 1) == 1) {
            return 2;
        } else {
            if (isPrime(i2)) {
                return i2;
            }
            int i3 = i2 % 3;
            if (i3 == 0) {
                i2 += 2;
            } else if (1 == i3) {
                i2 += 4;
            }
            while (!isPrime(i2)) {
                int i4 = i2 + 2;
                if (isPrime(i4)) {
                    return i4;
                }
                i2 += 6;
            }
            return i2;
        }
    }

    public static List<Integer> primeFactors(int i) {
        if (i >= 2) {
            return SmallPrimes.trialDivision(i);
        }
        throw new MathIllegalArgumentException(LocalizedFormats.NUMBER_TOO_SMALL, Integer.valueOf(i), 2);
    }
}
