package org.apache.commons.math3.stat.inference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.math3.distribution.FDistribution;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.apache.commons.math3.util.MathUtils;

public class OneWayAnova {
    public double anovaFValue(Collection<double[]> collection) throws NullArgumentException, DimensionMismatchException {
        return anovaStats(collection).F;
    }

    public double anovaPValue(Collection<double[]> collection) throws NullArgumentException, DimensionMismatchException, ConvergenceException, MaxCountExceededException {
        AnovaStats anovaStats = anovaStats(collection);
        return 1.0d - new FDistribution((RandomGenerator) null, (double) anovaStats.dfbg, (double) anovaStats.dfwg).cumulativeProbability(anovaStats.F);
    }

    public double anovaPValue(Collection<SummaryStatistics> collection, boolean z) throws NullArgumentException, DimensionMismatchException, ConvergenceException, MaxCountExceededException {
        AnovaStats anovaStats = anovaStats(collection, z);
        return 1.0d - new FDistribution((RandomGenerator) null, (double) anovaStats.dfbg, (double) anovaStats.dfwg).cumulativeProbability(anovaStats.F);
    }

    private AnovaStats anovaStats(Collection<double[]> collection) throws NullArgumentException, DimensionMismatchException {
        MathUtils.checkNotNull(collection);
        ArrayList arrayList = new ArrayList(collection.size());
        Iterator<double[]> it = collection.iterator();
        while (true) {
            if (!it.hasNext()) {
                return anovaStats(arrayList, false);
            }
            double[] next = it.next();
            SummaryStatistics summaryStatistics = new SummaryStatistics();
            arrayList.add(summaryStatistics);
            for (double addValue : next) {
                summaryStatistics.addValue(addValue);
            }
        }
    }

    public boolean anovaTest(Collection<double[]> collection, double d) throws NullArgumentException, DimensionMismatchException, OutOfRangeException, ConvergenceException, MaxCountExceededException {
        if (d > 0.0d && d <= 0.5d) {
            return anovaPValue(collection) < d;
        }
        throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, Double.valueOf(d), 0, Double.valueOf(0.5d));
    }

    private AnovaStats anovaStats(Collection<SummaryStatistics> collection, boolean z) throws NullArgumentException, DimensionMismatchException {
        MathUtils.checkNotNull(collection);
        if (!z) {
            if (collection.size() >= 2) {
                for (SummaryStatistics next : collection) {
                    if (next.getN() <= 1) {
                        throw new DimensionMismatchException(LocalizedFormats.TWO_OR_MORE_VALUES_IN_CATEGORY_REQUIRED, (int) next.getN(), 2);
                    }
                }
            } else {
                throw new DimensionMismatchException(LocalizedFormats.TWO_OR_MORE_CATEGORIES_REQUIRED, collection.size(), 2);
            }
        }
        int i = 0;
        double d = 0.0d;
        int i2 = 0;
        double d2 = 0.0d;
        double d3 = 0.0d;
        for (SummaryStatistics next2 : collection) {
            double sum = next2.getSum();
            double sumsq = next2.getSumsq();
            int n = (int) next2.getN();
            i += n;
            d2 += sum;
            d += sumsq;
            i2 += n - 1;
            d3 += sumsq - ((sum * sum) / ((double) n));
        }
        int size = collection.size() - 1;
        return new AnovaStats(size, i2, (((d - ((d2 * d2) / ((double) i))) - d3) / ((double) size)) / (d3 / ((double) i2)));
    }

    private static class AnovaStats {
        /* access modifiers changed from: private */
        public final double F;
        /* access modifiers changed from: private */
        public final int dfbg;
        /* access modifiers changed from: private */
        public final int dfwg;

        private AnovaStats(int i, int i2, double d) {
            this.dfbg = i;
            this.dfwg = i2;
            this.F = d;
        }
    }
}
