package org.apache.commons.math3.stat.descriptive.rank;

import java.io.Serializable;
import java.util.Arrays;
import java.util.BitSet;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.descriptive.AbstractUnivariateStatistic;
import org.apache.commons.math3.stat.ranking.NaNStrategy;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.KthSelector;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.MedianOf3PivotingStrategy;
import org.apache.commons.math3.util.PivotingStrategyInterface;
import org.apache.commons.math3.util.Precision;

public class Percentile extends AbstractUnivariateStatistic implements Serializable {
    private static final int MAX_CACHED_LEVELS = 10;
    private static final int PIVOTS_HEAP_LENGTH = 512;
    private static final long serialVersionUID = -8091216485095130416L;
    private int[] cachedPivots;
    private final EstimationType estimationType;
    private final KthSelector kthSelector;
    private final NaNStrategy nanStrategy;
    private double quantile;

    public Percentile() {
        this(50.0d);
    }

    public Percentile(double d) throws MathIllegalArgumentException {
        this(d, EstimationType.LEGACY, NaNStrategy.REMOVED, new KthSelector(new MedianOf3PivotingStrategy()));
    }

    public Percentile(Percentile percentile) throws NullArgumentException {
        MathUtils.checkNotNull(percentile);
        this.estimationType = percentile.getEstimationType();
        this.nanStrategy = percentile.getNaNStrategy();
        this.kthSelector = percentile.getKthSelector();
        setData(percentile.getDataRef());
        int[] iArr = percentile.cachedPivots;
        if (iArr != null) {
            System.arraycopy(iArr, 0, this.cachedPivots, 0, iArr.length);
        }
        setQuantile(percentile.quantile);
    }

    protected Percentile(double d, EstimationType estimationType2, NaNStrategy naNStrategy, KthSelector kthSelector2) throws MathIllegalArgumentException {
        setQuantile(d);
        this.cachedPivots = null;
        MathUtils.checkNotNull(estimationType2);
        MathUtils.checkNotNull(naNStrategy);
        MathUtils.checkNotNull(kthSelector2);
        this.estimationType = estimationType2;
        this.nanStrategy = naNStrategy;
        this.kthSelector = kthSelector2;
    }

    public void setData(double[] dArr) {
        if (dArr == null) {
            this.cachedPivots = null;
        } else {
            int[] iArr = new int[512];
            this.cachedPivots = iArr;
            Arrays.fill(iArr, -1);
        }
        super.setData(dArr);
    }

    public void setData(double[] dArr, int i, int i2) throws MathIllegalArgumentException {
        if (dArr == null) {
            this.cachedPivots = null;
        } else {
            int[] iArr = new int[512];
            this.cachedPivots = iArr;
            Arrays.fill(iArr, -1);
        }
        super.setData(dArr, i, i2);
    }

    public double evaluate(double d) throws MathIllegalArgumentException {
        return evaluate(getDataRef(), d);
    }

    public double evaluate(double[] dArr, double d) throws MathIllegalArgumentException {
        test(dArr, 0, 0);
        return evaluate(dArr, 0, dArr.length, d);
    }

    public double evaluate(double[] dArr, int i, int i2) throws MathIllegalArgumentException {
        return evaluate(dArr, i, i2, this.quantile);
    }

    public double evaluate(double[] dArr, int i, int i2, double d) throws MathIllegalArgumentException {
        test(dArr, i, i2);
        if (d > 100.0d || d <= 0.0d) {
            throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUNDS_QUANTILE_VALUE, Double.valueOf(d), 0, 100);
        } else if (i2 == 0) {
            return Double.NaN;
        } else {
            if (i2 == 1) {
                return dArr[i];
            }
            double[] workArray = getWorkArray(dArr, i, i2);
            int[] pivots = getPivots(dArr);
            if (workArray.length == 0) {
                return Double.NaN;
            }
            return this.estimationType.evaluate(workArray, pivots, d, this.kthSelector);
        }
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public int medianOf3(double[] dArr, int i, int i2) {
        return new MedianOf3PivotingStrategy().pivotIndex(dArr, i, i2);
    }

    public double getQuantile() {
        return this.quantile;
    }

    public void setQuantile(double d) throws MathIllegalArgumentException {
        if (d <= 0.0d || d > 100.0d) {
            throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUNDS_QUANTILE_VALUE, Double.valueOf(d), 0, 100);
        }
        this.quantile = d;
    }

    public Percentile copy() {
        return new Percentile(this);
    }

    @Deprecated
    public static void copy(Percentile percentile, Percentile percentile2) throws MathUnsupportedOperationException {
        throw new MathUnsupportedOperationException();
    }

    /* access modifiers changed from: protected */
    public double[] getWorkArray(double[] dArr, int i, int i2) {
        if (dArr == getDataRef()) {
            return getDataRef();
        }
        int i3 = AnonymousClass1.$SwitchMap$org$apache$commons$math3$stat$ranking$NaNStrategy[this.nanStrategy.ordinal()];
        if (i3 == 1) {
            return replaceAndSlice(dArr, i, i2, Double.NaN, Double.POSITIVE_INFINITY);
        }
        if (i3 == 2) {
            return replaceAndSlice(dArr, i, i2, Double.NaN, Double.NEGATIVE_INFINITY);
        }
        if (i3 == 3) {
            return removeAndSlice(dArr, i, i2, Double.NaN);
        }
        if (i3 != 4) {
            return copyOf(dArr, i, i2);
        }
        double[] copyOf = copyOf(dArr, i, i2);
        MathArrays.checkNotNaN(copyOf);
        return copyOf;
    }

    /* renamed from: org.apache.commons.math3.stat.descriptive.rank.Percentile$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$math3$stat$ranking$NaNStrategy;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                org.apache.commons.math3.stat.ranking.NaNStrategy[] r0 = org.apache.commons.math3.stat.ranking.NaNStrategy.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$commons$math3$stat$ranking$NaNStrategy = r0
                org.apache.commons.math3.stat.ranking.NaNStrategy r1 = org.apache.commons.math3.stat.ranking.NaNStrategy.MAXIMAL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$commons$math3$stat$ranking$NaNStrategy     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.commons.math3.stat.ranking.NaNStrategy r1 = org.apache.commons.math3.stat.ranking.NaNStrategy.MINIMAL     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$commons$math3$stat$ranking$NaNStrategy     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.commons.math3.stat.ranking.NaNStrategy r1 = org.apache.commons.math3.stat.ranking.NaNStrategy.REMOVED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$commons$math3$stat$ranking$NaNStrategy     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.commons.math3.stat.ranking.NaNStrategy r1 = org.apache.commons.math3.stat.ranking.NaNStrategy.FAILED     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.stat.descriptive.rank.Percentile.AnonymousClass1.<clinit>():void");
        }
    }

    private static double[] copyOf(double[] dArr, int i, int i2) {
        MathArrays.verifyValues(dArr, i, i2);
        return MathArrays.copyOfRange(dArr, i, i2 + i);
    }

    private static double[] replaceAndSlice(double[] dArr, int i, int i2, double d, double d2) {
        double[] copyOf = copyOf(dArr, i, i2);
        for (int i3 = 0; i3 < i2; i3++) {
            copyOf[i3] = Precision.equalsIncludingNaN(d, copyOf[i3]) ? d2 : copyOf[i3];
        }
        return copyOf;
    }

    private static double[] removeAndSlice(double[] dArr, int i, int i2, double d) {
        int i3;
        MathArrays.verifyValues(dArr, i, i2);
        BitSet bitSet = new BitSet(i2);
        int i4 = i;
        while (true) {
            i3 = i + i2;
            if (i4 >= i3) {
                break;
            }
            if (Precision.equalsIncludingNaN(d, dArr[i4])) {
                bitSet.set(i4 - i);
            }
            i4++;
        }
        if (bitSet.isEmpty()) {
            return copyOf(dArr, i, i2);
        }
        int i5 = 0;
        if (bitSet.cardinality() == i2) {
            return new double[0];
        }
        double[] dArr2 = new double[(i2 - bitSet.cardinality())];
        int i6 = i;
        int i7 = 0;
        while (true) {
            int nextSetBit = bitSet.nextSetBit(i5);
            if (nextSetBit == -1) {
                break;
            }
            int i8 = nextSetBit - i5;
            System.arraycopy(dArr, i6, dArr2, i7, i8);
            i7 += i8;
            i5 = bitSet.nextClearBit(nextSetBit);
            i6 = i + i5;
        }
        if (i6 < i3) {
            System.arraycopy(dArr, i6, dArr2, i7, i3 - i6);
        }
        return dArr2;
    }

    private int[] getPivots(double[] dArr) {
        if (dArr == getDataRef()) {
            return this.cachedPivots;
        }
        int[] iArr = new int[512];
        Arrays.fill(iArr, -1);
        return iArr;
    }

    public EstimationType getEstimationType() {
        return this.estimationType;
    }

    public Percentile withEstimationType(EstimationType estimationType2) {
        return new Percentile(this.quantile, estimationType2, this.nanStrategy, this.kthSelector);
    }

    public NaNStrategy getNaNStrategy() {
        return this.nanStrategy;
    }

    public Percentile withNaNStrategy(NaNStrategy naNStrategy) {
        return new Percentile(this.quantile, this.estimationType, naNStrategy, this.kthSelector);
    }

    public KthSelector getKthSelector() {
        return this.kthSelector;
    }

    public PivotingStrategyInterface getPivotingStrategy() {
        return this.kthSelector.getPivotingStrategy();
    }

    public Percentile withKthSelector(KthSelector kthSelector2) {
        return new Percentile(this.quantile, this.estimationType, this.nanStrategy, kthSelector2);
    }

    public enum EstimationType {
        LEGACY("Legacy Apache Commons Math") {
            /* access modifiers changed from: protected */
            public double index(double d, int i) {
                if (Double.compare(d, 0.0d) == 0) {
                    return 0.0d;
                }
                return Double.compare(d, 1.0d) == 0 ? (double) i : ((double) (i + 1)) * d;
            }
        },
        R_1("R-1") {
            /* access modifiers changed from: protected */
            public double index(double d, int i) {
                if (Double.compare(d, 0.0d) == 0) {
                    return 0.0d;
                }
                return (((double) i) * d) + 0.5d;
            }

            /* access modifiers changed from: protected */
            public double estimate(double[] dArr, int[] iArr, double d, int i, KthSelector kthSelector) {
                return super.estimate(dArr, iArr, FastMath.ceil(d - 0.5d), i, kthSelector);
            }
        },
        R_2("R-2") {
            /* access modifiers changed from: protected */
            public double index(double d, int i) {
                if (Double.compare(d, 1.0d) == 0) {
                    return (double) i;
                }
                if (Double.compare(d, 0.0d) == 0) {
                    return 0.0d;
                }
                return 0.5d + (((double) i) * d);
            }

            /* access modifiers changed from: protected */
            public double estimate(double[] dArr, int[] iArr, double d, int i, KthSelector kthSelector) {
                double[] dArr2 = dArr;
                int[] iArr2 = iArr;
                int i2 = i;
                KthSelector kthSelector2 = kthSelector;
                return (super.estimate(dArr2, iArr2, FastMath.ceil(d - 0.5d), i2, kthSelector2) + super.estimate(dArr2, iArr2, FastMath.floor(0.5d + d), i2, kthSelector2)) / 2.0d;
            }
        },
        R_3("R-3") {
            /* access modifiers changed from: protected */
            public double index(double d, int i) {
                double d2 = (double) i;
                if (Double.compare(d, 0.5d / d2) <= 0) {
                    return 0.0d;
                }
                return FastMath.rint(d2 * d);
            }
        },
        R_4("R-4") {
            /* access modifiers changed from: protected */
            public double index(double d, int i) {
                double d2 = (double) i;
                if (Double.compare(d, 1.0d / d2) < 0) {
                    return 0.0d;
                }
                return Double.compare(d, 1.0d) == 0 ? d2 : d2 * d;
            }
        },
        R_5("R-5") {
            /* access modifiers changed from: protected */
            public double index(double d, int i) {
                double d2 = (double) i;
                double d3 = (d2 - 0.5d) / d2;
                if (Double.compare(d, 0.5d / d2) < 0) {
                    return 0.0d;
                }
                return Double.compare(d, d3) >= 0 ? d2 : (d2 * d) + 0.5d;
            }
        },
        R_6("R-6") {
            /* access modifiers changed from: protected */
            public double index(double d, int i) {
                double d2 = (double) (i + 1);
                double d3 = (double) i;
                double d4 = (1.0d * d3) / d2;
                if (Double.compare(d, 1.0d / d2) < 0) {
                    return 0.0d;
                }
                return Double.compare(d, d4) >= 0 ? d3 : d2 * d;
            }
        },
        R_7("R-7") {
            /* access modifiers changed from: protected */
            public double index(double d, int i) {
                if (Double.compare(d, 0.0d) == 0) {
                    return 0.0d;
                }
                return Double.compare(d, 1.0d) == 0 ? (double) i : 1.0d + (((double) (i - 1)) * d);
            }
        },
        R_8("R-8") {
            /* access modifiers changed from: protected */
            public double index(double d, int i) {
                double d2 = (double) i;
                double d3 = d2 + 0.3333333333333333d;
                double d4 = (d2 - 0.3333333333333333d) / d3;
                if (Double.compare(d, 0.6666666666666666d / d3) < 0) {
                    return 0.0d;
                }
                return Double.compare(d, d4) >= 0 ? d2 : (d3 * d) + 0.3333333333333333d;
            }
        },
        R_9("R-9") {
            /* access modifiers changed from: protected */
            public double index(double d, int i) {
                double d2 = (double) i;
                double d3 = 0.25d + d2;
                double d4 = (d2 - 0.375d) / d3;
                if (Double.compare(d, 0.625d / d3) < 0) {
                    return 0.0d;
                }
                return Double.compare(d, d4) >= 0 ? d2 : (d3 * d) + 0.375d;
            }
        };
        
        private final String name;

        /* access modifiers changed from: protected */
        public abstract double index(double d, int i);

        private EstimationType(String str) {
            this.name = str;
        }

        /* access modifiers changed from: protected */
        public double estimate(double[] dArr, int[] iArr, double d, int i, KthSelector kthSelector) {
            double floor = FastMath.floor(d);
            int i2 = (int) floor;
            double d2 = d - floor;
            if (d < 1.0d) {
                return kthSelector.select(dArr, iArr, 0);
            }
            if (d >= ((double) i)) {
                return kthSelector.select(dArr, iArr, i - 1);
            }
            double select = kthSelector.select(dArr, iArr, i2 - 1);
            return select + (d2 * (kthSelector.select(dArr, iArr, i2) - select));
        }

        /* access modifiers changed from: protected */
        public double evaluate(double[] dArr, int[] iArr, double d, KthSelector kthSelector) {
            MathUtils.checkNotNull(dArr);
            if (d > 100.0d || d <= 0.0d) {
                throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUNDS_QUANTILE_VALUE, Double.valueOf(d), 0, 100);
            }
            return estimate(dArr, iArr, index(d / 100.0d, dArr.length), dArr.length, kthSelector);
        }

        public double evaluate(double[] dArr, double d, KthSelector kthSelector) {
            return evaluate(dArr, (int[]) null, d, kthSelector);
        }

        /* access modifiers changed from: package-private */
        public String getName() {
            return this.name;
        }
    }
}
