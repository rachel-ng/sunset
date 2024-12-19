package org.apache.commons.math3.stat.correlation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.ranking.NaNStrategy;
import org.apache.commons.math3.stat.ranking.NaturalRanking;
import org.apache.commons.math3.stat.ranking.RankingAlgorithm;

public class SpearmansCorrelation {
    private final RealMatrix data;
    private final PearsonsCorrelation rankCorrelation;
    private final RankingAlgorithm rankingAlgorithm;

    public SpearmansCorrelation() {
        this((RankingAlgorithm) new NaturalRanking());
    }

    public SpearmansCorrelation(RankingAlgorithm rankingAlgorithm2) {
        this.data = null;
        this.rankingAlgorithm = rankingAlgorithm2;
        this.rankCorrelation = null;
    }

    public SpearmansCorrelation(RealMatrix realMatrix) {
        this(realMatrix, new NaturalRanking());
    }

    public SpearmansCorrelation(RealMatrix realMatrix, RankingAlgorithm rankingAlgorithm2) {
        this.rankingAlgorithm = rankingAlgorithm2;
        RealMatrix rankTransform = rankTransform(realMatrix);
        this.data = rankTransform;
        this.rankCorrelation = new PearsonsCorrelation(rankTransform);
    }

    public RealMatrix getCorrelationMatrix() {
        return this.rankCorrelation.getCorrelationMatrix();
    }

    public PearsonsCorrelation getRankCorrelation() {
        return this.rankCorrelation;
    }

    public RealMatrix computeCorrelationMatrix(RealMatrix realMatrix) {
        return new PearsonsCorrelation().computeCorrelationMatrix(rankTransform(realMatrix));
    }

    public RealMatrix computeCorrelationMatrix(double[][] dArr) {
        return computeCorrelationMatrix((RealMatrix) new BlockRealMatrix(dArr));
    }

    public double correlation(double[] dArr, double[] dArr2) {
        if (dArr.length != dArr2.length) {
            throw new DimensionMismatchException(dArr.length, dArr2.length);
        } else if (dArr.length >= 2) {
            if ((this.rankingAlgorithm instanceof NaturalRanking) && NaNStrategy.REMOVED == ((NaturalRanking) this.rankingAlgorithm).getNanStrategy()) {
                HashSet hashSet = new HashSet();
                hashSet.addAll(getNaNPositions(dArr));
                hashSet.addAll(getNaNPositions(dArr2));
                dArr = removeValues(dArr, hashSet);
                dArr2 = removeValues(dArr2, hashSet);
            }
            return new PearsonsCorrelation().correlation(this.rankingAlgorithm.rank(dArr), this.rankingAlgorithm.rank(dArr2));
        } else {
            throw new MathIllegalArgumentException(LocalizedFormats.INSUFFICIENT_DIMENSION, Integer.valueOf(dArr.length), 2);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0065 A[LOOP:2: B:17:0x005f->B:19:0x0065, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.apache.commons.math3.linear.RealMatrix rankTransform(org.apache.commons.math3.linear.RealMatrix r6) {
        /*
            r5 = this;
            org.apache.commons.math3.stat.ranking.RankingAlgorithm r0 = r5.rankingAlgorithm
            boolean r1 = r0 instanceof org.apache.commons.math3.stat.ranking.NaturalRanking
            r2 = 0
            if (r1 == 0) goto L_0x0058
            org.apache.commons.math3.stat.ranking.NaturalRanking r0 = (org.apache.commons.math3.stat.ranking.NaturalRanking) r0
            org.apache.commons.math3.stat.ranking.NaNStrategy r0 = r0.getNanStrategy()
            org.apache.commons.math3.stat.ranking.NaNStrategy r1 = org.apache.commons.math3.stat.ranking.NaNStrategy.REMOVED
            if (r0 != r1) goto L_0x0058
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>()
            r1 = r2
        L_0x0017:
            int r3 = r6.getColumnDimension()
            if (r1 >= r3) goto L_0x002b
            double[] r3 = r6.getColumn(r1)
            java.util.List r3 = r5.getNaNPositions(r3)
            r0.addAll(r3)
            int r1 = r1 + 1
            goto L_0x0017
        L_0x002b:
            boolean r1 = r0.isEmpty()
            if (r1 != 0) goto L_0x0058
            org.apache.commons.math3.linear.BlockRealMatrix r1 = new org.apache.commons.math3.linear.BlockRealMatrix
            int r3 = r6.getRowDimension()
            int r4 = r0.size()
            int r3 = r3 - r4
            int r4 = r6.getColumnDimension()
            r1.<init>(r3, r4)
            r3 = r2
        L_0x0044:
            int r4 = r1.getColumnDimension()
            if (r3 >= r4) goto L_0x0059
            double[] r4 = r6.getColumn(r3)
            double[] r4 = r5.removeValues(r4, r0)
            r1.setColumn(r3, r4)
            int r3 = r3 + 1
            goto L_0x0044
        L_0x0058:
            r1 = 0
        L_0x0059:
            if (r1 != 0) goto L_0x005f
            org.apache.commons.math3.linear.RealMatrix r1 = r6.copy()
        L_0x005f:
            int r6 = r1.getColumnDimension()
            if (r2 >= r6) goto L_0x0075
            org.apache.commons.math3.stat.ranking.RankingAlgorithm r6 = r5.rankingAlgorithm
            double[] r0 = r1.getColumn(r2)
            double[] r6 = r6.rank(r0)
            r1.setColumn(r2, r6)
            int r2 = r2 + 1
            goto L_0x005f
        L_0x0075:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.stat.correlation.SpearmansCorrelation.rankTransform(org.apache.commons.math3.linear.RealMatrix):org.apache.commons.math3.linear.RealMatrix");
    }

    private List<Integer> getNaNPositions(double[] dArr) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < dArr.length; i++) {
            if (Double.isNaN(dArr[i])) {
                arrayList.add(Integer.valueOf(i));
            }
        }
        return arrayList;
    }

    private double[] removeValues(double[] dArr, Set<Integer> set) {
        if (set.isEmpty()) {
            return dArr;
        }
        double[] dArr2 = new double[(dArr.length - set.size())];
        int i = 0;
        for (int i2 = 0; i2 < dArr.length; i2++) {
            if (!set.contains(Integer.valueOf(i2))) {
                dArr2[i] = dArr[i2];
                i++;
            }
        }
        return dArr2;
    }
}
