package org.apache.commons.math3.stat.ranking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NotANumberException;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.util.FastMath;

public class NaturalRanking implements RankingAlgorithm {
    public static final NaNStrategy DEFAULT_NAN_STRATEGY = NaNStrategy.FAILED;
    public static final TiesStrategy DEFAULT_TIES_STRATEGY = TiesStrategy.AVERAGE;
    private final NaNStrategy nanStrategy;
    private final RandomDataGenerator randomData;
    private final TiesStrategy tiesStrategy;

    public NaturalRanking() {
        this.tiesStrategy = DEFAULT_TIES_STRATEGY;
        this.nanStrategy = DEFAULT_NAN_STRATEGY;
        this.randomData = null;
    }

    public NaturalRanking(TiesStrategy tiesStrategy2) {
        this.tiesStrategy = tiesStrategy2;
        this.nanStrategy = DEFAULT_NAN_STRATEGY;
        this.randomData = new RandomDataGenerator();
    }

    public NaturalRanking(NaNStrategy naNStrategy) {
        this.nanStrategy = naNStrategy;
        this.tiesStrategy = DEFAULT_TIES_STRATEGY;
        this.randomData = null;
    }

    public NaturalRanking(NaNStrategy naNStrategy, TiesStrategy tiesStrategy2) {
        this.nanStrategy = naNStrategy;
        this.tiesStrategy = tiesStrategy2;
        this.randomData = new RandomDataGenerator();
    }

    public NaturalRanking(RandomGenerator randomGenerator) {
        this.tiesStrategy = TiesStrategy.RANDOM;
        this.nanStrategy = DEFAULT_NAN_STRATEGY;
        this.randomData = new RandomDataGenerator(randomGenerator);
    }

    public NaturalRanking(NaNStrategy naNStrategy, RandomGenerator randomGenerator) {
        this.nanStrategy = naNStrategy;
        this.tiesStrategy = TiesStrategy.RANDOM;
        this.randomData = new RandomDataGenerator(randomGenerator);
    }

    public NaNStrategy getNanStrategy() {
        return this.nanStrategy;
    }

    public TiesStrategy getTiesStrategy() {
        return this.tiesStrategy;
    }

    public double[] rank(double[] dArr) {
        IntDoublePair[] intDoublePairArr = new IntDoublePair[dArr.length];
        for (int i = 0; i < dArr.length; i++) {
            intDoublePairArr[i] = new IntDoublePair(dArr[i], i);
        }
        int i2 = AnonymousClass1.$SwitchMap$org$apache$commons$math3$stat$ranking$NaNStrategy[this.nanStrategy.ordinal()];
        List<Integer> list = null;
        if (i2 == 1) {
            recodeNaNs(intDoublePairArr, Double.POSITIVE_INFINITY);
        } else if (i2 == 2) {
            recodeNaNs(intDoublePairArr, Double.NEGATIVE_INFINITY);
        } else if (i2 == 3) {
            intDoublePairArr = removeNaNs(intDoublePairArr);
        } else if (i2 == 4) {
            list = getNanPositions(intDoublePairArr);
        } else if (i2 == 5) {
            list = getNanPositions(intDoublePairArr);
            if (list.size() > 0) {
                throw new NotANumberException();
            }
        } else {
            throw new MathInternalError();
        }
        Arrays.sort(intDoublePairArr);
        double[] dArr2 = new double[intDoublePairArr.length];
        dArr2[intDoublePairArr[0].getPosition()] = (double) 1;
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(intDoublePairArr[0].getPosition()));
        int i3 = 1;
        for (int i4 = 1; i4 < intDoublePairArr.length; i4++) {
            if (Double.compare(intDoublePairArr[i4].getValue(), intDoublePairArr[i4 - 1].getValue()) > 0) {
                i3 = i4 + 1;
                if (arrayList.size() > 1) {
                    resolveTie(dArr2, arrayList);
                }
                arrayList = new ArrayList();
                arrayList.add(Integer.valueOf(intDoublePairArr[i4].getPosition()));
            } else {
                arrayList.add(Integer.valueOf(intDoublePairArr[i4].getPosition()));
            }
            dArr2[intDoublePairArr[i4].getPosition()] = (double) i3;
        }
        if (arrayList.size() > 1) {
            resolveTie(dArr2, arrayList);
        }
        if (this.nanStrategy == NaNStrategy.FIXED) {
            restoreNaNs(dArr2, list);
        }
        return dArr2;
    }

    private IntDoublePair[] removeNaNs(IntDoublePair[] intDoublePairArr) {
        if (!containsNaNs(intDoublePairArr)) {
            return intDoublePairArr;
        }
        IntDoublePair[] intDoublePairArr2 = new IntDoublePair[intDoublePairArr.length];
        int i = 0;
        for (int i2 = 0; i2 < intDoublePairArr.length; i2++) {
            if (Double.isNaN(intDoublePairArr[i2].getValue())) {
                for (int i3 = i2 + 1; i3 < intDoublePairArr.length; i3++) {
                    intDoublePairArr[i3] = new IntDoublePair(intDoublePairArr[i3].getValue(), intDoublePairArr[i3].getPosition() - 1);
                }
            } else {
                intDoublePairArr2[i] = new IntDoublePair(intDoublePairArr[i2].getValue(), intDoublePairArr[i2].getPosition());
                i++;
            }
        }
        IntDoublePair[] intDoublePairArr3 = new IntDoublePair[i];
        System.arraycopy(intDoublePairArr2, 0, intDoublePairArr3, 0, i);
        return intDoublePairArr3;
    }

    private void recodeNaNs(IntDoublePair[] intDoublePairArr, double d) {
        for (int i = 0; i < intDoublePairArr.length; i++) {
            if (Double.isNaN(intDoublePairArr[i].getValue())) {
                intDoublePairArr[i] = new IntDoublePair(d, intDoublePairArr[i].getPosition());
            }
        }
    }

    private boolean containsNaNs(IntDoublePair[] intDoublePairArr) {
        for (IntDoublePair value : intDoublePairArr) {
            if (Double.isNaN(value.getValue())) {
                return true;
            }
        }
        return false;
    }

    private void resolveTie(double[] dArr, List<Integer> list) {
        int i = 0;
        double d = dArr[list.get(0).intValue()];
        int size = list.size();
        int i2 = AnonymousClass1.$SwitchMap$org$apache$commons$math3$stat$ranking$TiesStrategy[this.tiesStrategy.ordinal()];
        if (i2 == 1) {
            fill(dArr, list, (((d * 2.0d) + ((double) size)) - 1.0d) / 2.0d);
        } else if (i2 == 2) {
            fill(dArr, list, (d + ((double) size)) - 1.0d);
        } else if (i2 == 3) {
            fill(dArr, list, d);
        } else if (i2 == 4) {
            long round = FastMath.round(d);
            for (Integer intValue : list) {
                dArr[intValue.intValue()] = (double) this.randomData.nextLong(round, (((long) size) + round) - 1);
            }
        } else if (i2 == 5) {
            long round2 = FastMath.round(d);
            for (Integer intValue2 : list) {
                dArr[intValue2.intValue()] = (double) (((long) i) + round2);
                i++;
            }
        } else {
            throw new MathInternalError();
        }
    }

    /* renamed from: org.apache.commons.math3.stat.ranking.NaturalRanking$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$math3$stat$ranking$NaNStrategy;
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$math3$stat$ranking$TiesStrategy;

        /* JADX WARNING: Can't wrap try/catch for region: R(20:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|21|22|23|24|25|26|27|28|(3:29|30|32)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(25:0|1|2|3|(2:5|6)|7|9|10|11|13|14|15|(2:17|18)|19|21|22|23|24|25|26|27|28|29|30|32) */
        /* JADX WARNING: Can't wrap try/catch for region: R(26:0|1|2|3|(2:5|6)|7|9|10|11|13|14|15|17|18|19|21|22|23|24|25|26|27|28|29|30|32) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x004f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0059 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0063 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x006d */
        static {
            /*
                org.apache.commons.math3.stat.ranking.TiesStrategy[] r0 = org.apache.commons.math3.stat.ranking.TiesStrategy.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$commons$math3$stat$ranking$TiesStrategy = r0
                r1 = 1
                org.apache.commons.math3.stat.ranking.TiesStrategy r2 = org.apache.commons.math3.stat.ranking.TiesStrategy.AVERAGE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$org$apache$commons$math3$stat$ranking$TiesStrategy     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.commons.math3.stat.ranking.TiesStrategy r3 = org.apache.commons.math3.stat.ranking.TiesStrategy.MAXIMUM     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$org$apache$commons$math3$stat$ranking$TiesStrategy     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.commons.math3.stat.ranking.TiesStrategy r4 = org.apache.commons.math3.stat.ranking.TiesStrategy.MINIMUM     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                r3 = 4
                int[] r4 = $SwitchMap$org$apache$commons$math3$stat$ranking$TiesStrategy     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.commons.math3.stat.ranking.TiesStrategy r5 = org.apache.commons.math3.stat.ranking.TiesStrategy.RANDOM     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                r4 = 5
                int[] r5 = $SwitchMap$org$apache$commons$math3$stat$ranking$TiesStrategy     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.commons.math3.stat.ranking.TiesStrategy r6 = org.apache.commons.math3.stat.ranking.TiesStrategy.SEQUENTIAL     // Catch:{ NoSuchFieldError -> 0x003e }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r5[r6] = r4     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                org.apache.commons.math3.stat.ranking.NaNStrategy[] r5 = org.apache.commons.math3.stat.ranking.NaNStrategy.values()
                int r5 = r5.length
                int[] r5 = new int[r5]
                $SwitchMap$org$apache$commons$math3$stat$ranking$NaNStrategy = r5
                org.apache.commons.math3.stat.ranking.NaNStrategy r6 = org.apache.commons.math3.stat.ranking.NaNStrategy.MAXIMAL     // Catch:{ NoSuchFieldError -> 0x004f }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x004f }
                r5[r6] = r1     // Catch:{ NoSuchFieldError -> 0x004f }
            L_0x004f:
                int[] r1 = $SwitchMap$org$apache$commons$math3$stat$ranking$NaNStrategy     // Catch:{ NoSuchFieldError -> 0x0059 }
                org.apache.commons.math3.stat.ranking.NaNStrategy r5 = org.apache.commons.math3.stat.ranking.NaNStrategy.MINIMAL     // Catch:{ NoSuchFieldError -> 0x0059 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0059 }
                r1[r5] = r0     // Catch:{ NoSuchFieldError -> 0x0059 }
            L_0x0059:
                int[] r0 = $SwitchMap$org$apache$commons$math3$stat$ranking$NaNStrategy     // Catch:{ NoSuchFieldError -> 0x0063 }
                org.apache.commons.math3.stat.ranking.NaNStrategy r1 = org.apache.commons.math3.stat.ranking.NaNStrategy.REMOVED     // Catch:{ NoSuchFieldError -> 0x0063 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0063 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0063 }
            L_0x0063:
                int[] r0 = $SwitchMap$org$apache$commons$math3$stat$ranking$NaNStrategy     // Catch:{ NoSuchFieldError -> 0x006d }
                org.apache.commons.math3.stat.ranking.NaNStrategy r1 = org.apache.commons.math3.stat.ranking.NaNStrategy.FIXED     // Catch:{ NoSuchFieldError -> 0x006d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006d }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x006d }
            L_0x006d:
                int[] r0 = $SwitchMap$org$apache$commons$math3$stat$ranking$NaNStrategy     // Catch:{ NoSuchFieldError -> 0x0077 }
                org.apache.commons.math3.stat.ranking.NaNStrategy r1 = org.apache.commons.math3.stat.ranking.NaNStrategy.FAILED     // Catch:{ NoSuchFieldError -> 0x0077 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0077 }
                r0[r1] = r4     // Catch:{ NoSuchFieldError -> 0x0077 }
            L_0x0077:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.stat.ranking.NaturalRanking.AnonymousClass1.<clinit>():void");
        }
    }

    private void fill(double[] dArr, List<Integer> list, double d) {
        for (Integer intValue : list) {
            dArr[intValue.intValue()] = d;
        }
    }

    private void restoreNaNs(double[] dArr, List<Integer> list) {
        if (list.size() != 0) {
            for (Integer intValue : list) {
                dArr[intValue.intValue()] = Double.NaN;
            }
        }
    }

    private List<Integer> getNanPositions(IntDoublePair[] intDoublePairArr) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < intDoublePairArr.length; i++) {
            if (Double.isNaN(intDoublePairArr[i].getValue())) {
                arrayList.add(Integer.valueOf(i));
            }
        }
        return arrayList;
    }

    private static class IntDoublePair implements Comparable<IntDoublePair> {
        private final int position;
        private final double value;

        public IntDoublePair(double d, int i) {
            this.value = d;
            this.position = i;
        }

        public int compareTo(IntDoublePair intDoublePair) {
            return Double.compare(this.value, intDoublePair.value);
        }

        public double getValue() {
            return this.value;
        }

        public int getPosition() {
            return this.position;
        }
    }
}
