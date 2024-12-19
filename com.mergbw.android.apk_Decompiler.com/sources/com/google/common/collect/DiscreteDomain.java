package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import java.io.Serializable;
import java.lang.Comparable;
import java.math.BigInteger;
import java.util.NoSuchElementException;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
public abstract class DiscreteDomain<C extends Comparable> {
    final boolean supportsFastOffset;

    public abstract long distance(C c2, C c3);

    @CheckForNull
    public abstract C next(C c2);

    @CheckForNull
    public abstract C previous(C c2);

    public static DiscreteDomain<Integer> integers() {
        return IntegerDomain.INSTANCE;
    }

    private static final class IntegerDomain extends DiscreteDomain<Integer> implements Serializable {
        /* access modifiers changed from: private */
        public static final IntegerDomain INSTANCE = new IntegerDomain();
        private static final long serialVersionUID = 0;

        IntegerDomain() {
            super(true);
        }

        @CheckForNull
        public Integer next(Integer num) {
            int intValue = num.intValue();
            if (intValue == Integer.MAX_VALUE) {
                return null;
            }
            return Integer.valueOf(intValue + 1);
        }

        @CheckForNull
        public Integer previous(Integer num) {
            int intValue = num.intValue();
            if (intValue == Integer.MIN_VALUE) {
                return null;
            }
            return Integer.valueOf(intValue - 1);
        }

        /* access modifiers changed from: package-private */
        public Integer offset(Integer num, long j) {
            CollectPreconditions.checkNonnegative(j, "distance");
            return Integer.valueOf(Ints.checkedCast(num.longValue() + j));
        }

        public long distance(Integer num, Integer num2) {
            return ((long) num2.intValue()) - ((long) num.intValue());
        }

        public Integer minValue() {
            return Integer.MIN_VALUE;
        }

        public Integer maxValue() {
            return Integer.MAX_VALUE;
        }

        private Object readResolve() {
            return INSTANCE;
        }

        public String toString() {
            return "DiscreteDomain.integers()";
        }
    }

    public static DiscreteDomain<Long> longs() {
        return LongDomain.INSTANCE;
    }

    private static final class LongDomain extends DiscreteDomain<Long> implements Serializable {
        /* access modifiers changed from: private */
        public static final LongDomain INSTANCE = new LongDomain();
        private static final long serialVersionUID = 0;

        LongDomain() {
            super(true);
        }

        @CheckForNull
        public Long next(Long l) {
            long longValue = l.longValue();
            if (longValue == Long.MAX_VALUE) {
                return null;
            }
            return Long.valueOf(longValue + 1);
        }

        @CheckForNull
        public Long previous(Long l) {
            long longValue = l.longValue();
            if (longValue == Long.MIN_VALUE) {
                return null;
            }
            return Long.valueOf(longValue - 1);
        }

        /* access modifiers changed from: package-private */
        public Long offset(Long l, long j) {
            CollectPreconditions.checkNonnegative(j, "distance");
            long longValue = l.longValue() + j;
            if (longValue < 0) {
                Preconditions.checkArgument(l.longValue() < 0, "overflow");
            }
            return Long.valueOf(longValue);
        }

        public long distance(Long l, Long l2) {
            long longValue = l2.longValue() - l.longValue();
            if (l2.longValue() > l.longValue() && longValue < 0) {
                return Long.MAX_VALUE;
            }
            if (l2.longValue() >= l.longValue() || longValue <= 0) {
                return longValue;
            }
            return Long.MIN_VALUE;
        }

        public Long minValue() {
            return Long.MIN_VALUE;
        }

        public Long maxValue() {
            return Long.MAX_VALUE;
        }

        private Object readResolve() {
            return INSTANCE;
        }

        public String toString() {
            return "DiscreteDomain.longs()";
        }
    }

    public static DiscreteDomain<BigInteger> bigIntegers() {
        return BigIntegerDomain.INSTANCE;
    }

    private static final class BigIntegerDomain extends DiscreteDomain<BigInteger> implements Serializable {
        /* access modifiers changed from: private */
        public static final BigIntegerDomain INSTANCE = new BigIntegerDomain();
        private static final BigInteger MAX_LONG = BigInteger.valueOf(Long.MAX_VALUE);
        private static final BigInteger MIN_LONG = BigInteger.valueOf(Long.MIN_VALUE);
        private static final long serialVersionUID = 0;

        BigIntegerDomain() {
            super(true);
        }

        public BigInteger next(BigInteger bigInteger) {
            return bigInteger.add(BigInteger.ONE);
        }

        public BigInteger previous(BigInteger bigInteger) {
            return bigInteger.subtract(BigInteger.ONE);
        }

        /* access modifiers changed from: package-private */
        public BigInteger offset(BigInteger bigInteger, long j) {
            CollectPreconditions.checkNonnegative(j, "distance");
            return bigInteger.add(BigInteger.valueOf(j));
        }

        public long distance(BigInteger bigInteger, BigInteger bigInteger2) {
            return bigInteger2.subtract(bigInteger).max(MIN_LONG).min(MAX_LONG).longValue();
        }

        private Object readResolve() {
            return INSTANCE;
        }

        public String toString() {
            return "DiscreteDomain.bigIntegers()";
        }
    }

    protected DiscreteDomain() {
        this(false);
    }

    private DiscreteDomain(boolean z) {
        this.supportsFastOffset = z;
    }

    /* access modifiers changed from: package-private */
    public C offset(C c2, long j) {
        CollectPreconditions.checkNonnegative(j, "distance");
        long j2 = 0;
        C c3 = c2;
        while (j2 < j) {
            c3 = next(c3);
            if (c3 != null) {
                j2++;
            } else {
                String valueOf = String.valueOf(c2);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 51);
                sb.append("overflowed computing offset(");
                sb.append(valueOf);
                sb.append(", ");
                sb.append(j);
                sb.append(")");
                throw new IllegalArgumentException(sb.toString());
            }
        }
        return c3;
    }

    public C minValue() {
        throw new NoSuchElementException();
    }

    public C maxValue() {
        throw new NoSuchElementException();
    }
}
