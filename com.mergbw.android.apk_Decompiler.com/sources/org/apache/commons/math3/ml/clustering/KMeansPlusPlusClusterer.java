package org.apache.commons.math3.ml.clustering;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.ml.clustering.Clusterable;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.ml.distance.EuclideanDistance;
import org.apache.commons.math3.random.JDKRandomGenerator;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.stat.descriptive.moment.Variance;
import org.apache.commons.math3.util.MathUtils;

public class KMeansPlusPlusClusterer<T extends Clusterable> extends Clusterer<T> {
    private final EmptyClusterStrategy emptyStrategy;
    private final int k;
    private final int maxIterations;
    private final RandomGenerator random;

    public enum EmptyClusterStrategy {
        LARGEST_VARIANCE,
        LARGEST_POINTS_NUMBER,
        FARTHEST_POINT,
        ERROR
    }

    public KMeansPlusPlusClusterer(int i) {
        this(i, -1);
    }

    public KMeansPlusPlusClusterer(int i, int i2) {
        this(i, i2, new EuclideanDistance());
    }

    public KMeansPlusPlusClusterer(int i, int i2, DistanceMeasure distanceMeasure) {
        this(i, i2, distanceMeasure, new JDKRandomGenerator());
    }

    public KMeansPlusPlusClusterer(int i, int i2, DistanceMeasure distanceMeasure, RandomGenerator randomGenerator) {
        this(i, i2, distanceMeasure, randomGenerator, EmptyClusterStrategy.LARGEST_VARIANCE);
    }

    public KMeansPlusPlusClusterer(int i, int i2, DistanceMeasure distanceMeasure, RandomGenerator randomGenerator, EmptyClusterStrategy emptyClusterStrategy) {
        super(distanceMeasure);
        this.k = i;
        this.maxIterations = i2;
        this.random = randomGenerator;
        this.emptyStrategy = emptyClusterStrategy;
    }

    public int getK() {
        return this.k;
    }

    public int getMaxIterations() {
        return this.maxIterations;
    }

    public RandomGenerator getRandomGenerator() {
        return this.random;
    }

    public EmptyClusterStrategy getEmptyClusterStrategy() {
        return this.emptyStrategy;
    }

    public List<CentroidCluster<T>> cluster(Collection<T> collection) throws MathIllegalArgumentException, ConvergenceException {
        boolean z;
        Clusterable clusterable;
        MathUtils.checkNotNull(collection);
        if (collection.size() >= this.k) {
            List<CentroidCluster<T>> chooseInitialCenters = chooseInitialCenters(collection);
            int[] iArr = new int[collection.size()];
            assignPointsToClusters(chooseInitialCenters, collection, iArr);
            int i = this.maxIterations;
            if (i < 0) {
                i = Integer.MAX_VALUE;
            }
            int i2 = 0;
            while (i2 < i) {
                ArrayList arrayList = new ArrayList();
                boolean z2 = false;
                for (CentroidCluster next : chooseInitialCenters) {
                    if (next.getPoints().isEmpty()) {
                        int i3 = AnonymousClass1.$SwitchMap$org$apache$commons$math3$ml$clustering$KMeansPlusPlusClusterer$EmptyClusterStrategy[this.emptyStrategy.ordinal()];
                        z = true;
                        if (i3 == 1) {
                            clusterable = getPointFromLargestVarianceCluster(chooseInitialCenters);
                        } else if (i3 == 2) {
                            clusterable = getPointFromLargestNumberCluster(chooseInitialCenters);
                        } else if (i3 == 3) {
                            clusterable = getFarthestPoint(chooseInitialCenters);
                        } else {
                            throw new ConvergenceException(LocalizedFormats.EMPTY_CLUSTER_IN_K_MEANS, new Object[0]);
                        }
                    } else {
                        Clusterable centroidOf = centroidOf(next.getPoints(), next.getCenter().getPoint().length);
                        z = z2;
                        clusterable = centroidOf;
                    }
                    arrayList.add(new CentroidCluster(clusterable));
                    z2 = z;
                }
                if (assignPointsToClusters(arrayList, collection, iArr) == 0 && !z2) {
                    return arrayList;
                }
                i2++;
                chooseInitialCenters = arrayList;
            }
            return chooseInitialCenters;
        }
        throw new NumberIsTooSmallException(Integer.valueOf(collection.size()), Integer.valueOf(this.k), false);
    }

    /* renamed from: org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$math3$ml$clustering$KMeansPlusPlusClusterer$EmptyClusterStrategy;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer$EmptyClusterStrategy[] r0 = org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.EmptyClusterStrategy.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$commons$math3$ml$clustering$KMeansPlusPlusClusterer$EmptyClusterStrategy = r0
                org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer$EmptyClusterStrategy r1 = org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.EmptyClusterStrategy.LARGEST_VARIANCE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$commons$math3$ml$clustering$KMeansPlusPlusClusterer$EmptyClusterStrategy     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer$EmptyClusterStrategy r1 = org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.EmptyClusterStrategy.LARGEST_POINTS_NUMBER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$commons$math3$ml$clustering$KMeansPlusPlusClusterer$EmptyClusterStrategy     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer$EmptyClusterStrategy r1 = org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.EmptyClusterStrategy.FARTHEST_POINT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.AnonymousClass1.<clinit>():void");
        }
    }

    private int assignPointsToClusters(List<CentroidCluster<T>> list, Collection<T> collection, int[] iArr) {
        int i = 0;
        int i2 = 0;
        for (T t : collection) {
            int nearestCluster = getNearestCluster(list, t);
            if (nearestCluster != iArr[i2]) {
                i++;
            }
            list.get(nearestCluster).addPoint(t);
            iArr[i2] = nearestCluster;
            i2++;
        }
        return i;
    }

    private List<CentroidCluster<T>> chooseInitialCenters(Collection<T> collection) {
        List unmodifiableList = Collections.unmodifiableList(new ArrayList(collection));
        int size = unmodifiableList.size();
        boolean[] zArr = new boolean[size];
        ArrayList arrayList = new ArrayList();
        int nextInt = this.random.nextInt(size);
        Clusterable clusterable = (Clusterable) unmodifiableList.get(nextInt);
        arrayList.add(new CentroidCluster(clusterable));
        zArr[nextInt] = true;
        double[] dArr = new double[size];
        for (int i = 0; i < size; i++) {
            if (i != nextInt) {
                double distance = distance(clusterable, (Clusterable) unmodifiableList.get(i));
                dArr[i] = distance * distance;
            }
        }
        while (arrayList.size() < this.k) {
            double d = 0.0d;
            double d2 = 0.0d;
            for (int i2 = 0; i2 < size; i2++) {
                if (!zArr[i2]) {
                    d2 += dArr[i2];
                }
            }
            double nextDouble = this.random.nextDouble() * d2;
            int i3 = 0;
            while (true) {
                if (i3 >= size) {
                    i3 = -1;
                    break;
                }
                if (!zArr[i3]) {
                    d += dArr[i3];
                    if (d >= nextDouble) {
                        break;
                    }
                }
                i3++;
            }
            if (i3 == -1) {
                int i4 = size - 1;
                while (true) {
                    if (i4 < 0) {
                        break;
                    } else if (!zArr[i4]) {
                        i3 = i4;
                        break;
                    } else {
                        i4--;
                    }
                }
            }
            if (i3 < 0) {
                break;
            }
            Clusterable clusterable2 = (Clusterable) unmodifiableList.get(i3);
            arrayList.add(new CentroidCluster(clusterable2));
            zArr[i3] = true;
            if (arrayList.size() < this.k) {
                for (int i5 = 0; i5 < size; i5++) {
                    if (!zArr[i5]) {
                        double distance2 = distance(clusterable2, (Clusterable) unmodifiableList.get(i5));
                        double d3 = distance2 * distance2;
                        if (d3 < dArr[i5]) {
                            dArr[i5] = d3;
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    private T getPointFromLargestVarianceCluster(Collection<CentroidCluster<T>> collection) throws ConvergenceException {
        double d = Double.NEGATIVE_INFINITY;
        CentroidCluster centroidCluster = null;
        for (CentroidCluster next : collection) {
            if (!next.getPoints().isEmpty()) {
                Clusterable center = next.getCenter();
                Variance variance = new Variance();
                for (Clusterable distance : next.getPoints()) {
                    variance.increment(distance(distance, center));
                }
                double result = variance.getResult();
                if (result > d) {
                    centroidCluster = next;
                    d = result;
                }
            }
        }
        if (centroidCluster != null) {
            List points = centroidCluster.getPoints();
            return (Clusterable) points.remove(this.random.nextInt(points.size()));
        }
        throw new ConvergenceException(LocalizedFormats.EMPTY_CLUSTER_IN_K_MEANS, new Object[0]);
    }

    private T getPointFromLargestNumberCluster(Collection<? extends Cluster<T>> collection) throws ConvergenceException {
        Cluster cluster = null;
        int i = 0;
        for (Cluster cluster2 : collection) {
            int size = cluster2.getPoints().size();
            if (size > i) {
                cluster = cluster2;
                i = size;
            }
        }
        if (cluster != null) {
            List points = cluster.getPoints();
            return (Clusterable) points.remove(this.random.nextInt(points.size()));
        }
        throw new ConvergenceException(LocalizedFormats.EMPTY_CLUSTER_IN_K_MEANS, new Object[0]);
    }

    private T getFarthestPoint(Collection<CentroidCluster<T>> collection) throws ConvergenceException {
        Iterator<CentroidCluster<T>> it = collection.iterator();
        double d = Double.NEGATIVE_INFINITY;
        CentroidCluster centroidCluster = null;
        int i = -1;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            CentroidCluster next = it.next();
            Clusterable center = next.getCenter();
            List points = next.getPoints();
            for (int i2 = 0; i2 < points.size(); i2++) {
                double distance = distance((Clusterable) points.get(i2), center);
                if (distance > d) {
                    centroidCluster = next;
                    i = i2;
                    d = distance;
                }
            }
        }
        if (centroidCluster != null) {
            return (Clusterable) centroidCluster.getPoints().remove(i);
        }
        throw new ConvergenceException(LocalizedFormats.EMPTY_CLUSTER_IN_K_MEANS, new Object[0]);
    }

    private int getNearestCluster(Collection<CentroidCluster<T>> collection, T t) {
        double d = Double.MAX_VALUE;
        int i = 0;
        int i2 = 0;
        for (CentroidCluster<T> center : collection) {
            double distance = distance(t, center.getCenter());
            if (distance < d) {
                i = i2;
                d = distance;
            }
            i2++;
        }
        return i;
    }

    private Clusterable centroidOf(Collection<T> collection, int i) {
        int i2;
        double[] dArr = new double[i];
        Iterator<T> it = collection.iterator();
        while (true) {
            i2 = 0;
            if (!it.hasNext()) {
                break;
            }
            double[] point = ((Clusterable) it.next()).getPoint();
            while (i2 < i) {
                dArr[i2] = dArr[i2] + point[i2];
                i2++;
            }
        }
        while (i2 < i) {
            dArr[i2] = dArr[i2] / ((double) collection.size());
            i2++;
        }
        return new DoublePoint(dArr);
    }
}
