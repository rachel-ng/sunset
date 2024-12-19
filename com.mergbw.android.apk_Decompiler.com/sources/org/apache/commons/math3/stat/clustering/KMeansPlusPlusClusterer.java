package org.apache.commons.math3.stat.clustering;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.clustering.Clusterable;
import org.apache.commons.math3.stat.descriptive.moment.Variance;
import org.apache.commons.math3.util.MathUtils;

@Deprecated
public class KMeansPlusPlusClusterer<T extends Clusterable<T>> {
    private final EmptyClusterStrategy emptyStrategy;
    private final Random random;

    public enum EmptyClusterStrategy {
        LARGEST_VARIANCE,
        LARGEST_POINTS_NUMBER,
        FARTHEST_POINT,
        ERROR
    }

    public KMeansPlusPlusClusterer(Random random2) {
        this(random2, EmptyClusterStrategy.LARGEST_VARIANCE);
    }

    public KMeansPlusPlusClusterer(Random random2, EmptyClusterStrategy emptyClusterStrategy) {
        this.random = random2;
        this.emptyStrategy = emptyClusterStrategy;
    }

    public List<Cluster<T>> cluster(Collection<T> collection, int i, int i2, int i3) throws MathIllegalArgumentException, ConvergenceException {
        List<Cluster<T>> list = null;
        double d = Double.POSITIVE_INFINITY;
        for (int i4 = 0; i4 < i2; i4++) {
            List<Cluster<T>> cluster = cluster(collection, i, i3);
            double d2 = 0.0d;
            for (Cluster next : cluster) {
                if (!next.getPoints().isEmpty()) {
                    Clusterable center = next.getCenter();
                    Variance variance = new Variance();
                    for (Clusterable distanceFrom : next.getPoints()) {
                        variance.increment(distanceFrom.distanceFrom(center));
                        int i5 = i2;
                    }
                    d2 += variance.getResult();
                }
                int i6 = i2;
            }
            if (d2 <= d) {
                list = cluster;
                d = d2;
            }
        }
        return list;
    }

    public List<Cluster<T>> cluster(Collection<T> collection, int i, int i2) throws MathIllegalArgumentException, ConvergenceException {
        boolean z;
        Clusterable clusterable;
        MathUtils.checkNotNull(collection);
        if (collection.size() >= i) {
            List<Cluster<T>> chooseInitialCenters = chooseInitialCenters(collection, i, this.random);
            int[] iArr = new int[collection.size()];
            assignPointsToClusters(chooseInitialCenters, collection, iArr);
            if (i2 < 0) {
                i2 = Integer.MAX_VALUE;
            }
            int i3 = 0;
            while (i3 < i2) {
                ArrayList arrayList = new ArrayList();
                boolean z2 = false;
                for (Cluster next : chooseInitialCenters) {
                    if (next.getPoints().isEmpty()) {
                        int i4 = AnonymousClass1.$SwitchMap$org$apache$commons$math3$stat$clustering$KMeansPlusPlusClusterer$EmptyClusterStrategy[this.emptyStrategy.ordinal()];
                        z = true;
                        if (i4 == 1) {
                            clusterable = getPointFromLargestVarianceCluster(chooseInitialCenters);
                        } else if (i4 == 2) {
                            clusterable = getPointFromLargestNumberCluster(chooseInitialCenters);
                        } else if (i4 == 3) {
                            clusterable = getFarthestPoint(chooseInitialCenters);
                        } else {
                            throw new ConvergenceException(LocalizedFormats.EMPTY_CLUSTER_IN_K_MEANS, new Object[0]);
                        }
                    } else {
                        Clusterable clusterable2 = (Clusterable) next.getCenter().centroidOf(next.getPoints());
                        z = z2;
                        clusterable = clusterable2;
                    }
                    arrayList.add(new Cluster(clusterable));
                    z2 = z;
                }
                if (assignPointsToClusters(arrayList, collection, iArr) == 0 && !z2) {
                    return arrayList;
                }
                i3++;
                chooseInitialCenters = arrayList;
            }
            return chooseInitialCenters;
        }
        throw new NumberIsTooSmallException(Integer.valueOf(collection.size()), Integer.valueOf(i), false);
    }

    /* renamed from: org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$math3$stat$clustering$KMeansPlusPlusClusterer$EmptyClusterStrategy;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer$EmptyClusterStrategy[] r0 = org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.EmptyClusterStrategy.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$commons$math3$stat$clustering$KMeansPlusPlusClusterer$EmptyClusterStrategy = r0
                org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer$EmptyClusterStrategy r1 = org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.EmptyClusterStrategy.LARGEST_VARIANCE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$commons$math3$stat$clustering$KMeansPlusPlusClusterer$EmptyClusterStrategy     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer$EmptyClusterStrategy r1 = org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.EmptyClusterStrategy.LARGEST_POINTS_NUMBER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$commons$math3$stat$clustering$KMeansPlusPlusClusterer$EmptyClusterStrategy     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer$EmptyClusterStrategy r1 = org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.EmptyClusterStrategy.FARTHEST_POINT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.AnonymousClass1.<clinit>():void");
        }
    }

    private static <T extends Clusterable<T>> int assignPointsToClusters(List<Cluster<T>> list, Collection<T> collection, int[] iArr) {
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

    private static <T extends Clusterable<T>> List<Cluster<T>> chooseInitialCenters(Collection<T> collection, int i, Random random2) {
        int i2 = i;
        List unmodifiableList = Collections.unmodifiableList(new ArrayList(collection));
        int size = unmodifiableList.size();
        boolean[] zArr = new boolean[size];
        ArrayList arrayList = new ArrayList();
        int nextInt = random2.nextInt(size);
        Clusterable clusterable = (Clusterable) unmodifiableList.get(nextInt);
        arrayList.add(new Cluster(clusterable));
        zArr[nextInt] = true;
        double[] dArr = new double[size];
        for (int i3 = 0; i3 < size; i3++) {
            if (i3 != nextInt) {
                double distanceFrom = clusterable.distanceFrom(unmodifiableList.get(i3));
                dArr[i3] = distanceFrom * distanceFrom;
            }
        }
        while (arrayList.size() < i2) {
            double d = 0.0d;
            double d2 = 0.0d;
            for (int i4 = 0; i4 < size; i4++) {
                if (!zArr[i4]) {
                    d2 += dArr[i4];
                }
            }
            double nextDouble = random2.nextDouble() * d2;
            int i5 = 0;
            while (true) {
                if (i5 >= size) {
                    i5 = -1;
                    break;
                }
                if (!zArr[i5]) {
                    d += dArr[i5];
                    if (d >= nextDouble) {
                        break;
                    }
                }
                i5++;
            }
            if (i5 == -1) {
                int i6 = size - 1;
                while (true) {
                    if (i6 < 0) {
                        break;
                    } else if (!zArr[i6]) {
                        i5 = i6;
                        break;
                    } else {
                        i6--;
                    }
                }
            }
            if (i5 < 0) {
                break;
            }
            Clusterable clusterable2 = (Clusterable) unmodifiableList.get(i5);
            arrayList.add(new Cluster(clusterable2));
            zArr[i5] = true;
            if (arrayList.size() < i2) {
                for (int i7 = 0; i7 < size; i7++) {
                    if (!zArr[i7]) {
                        double distanceFrom2 = clusterable2.distanceFrom(unmodifiableList.get(i7));
                        double d3 = distanceFrom2 * distanceFrom2;
                        if (d3 < dArr[i7]) {
                            dArr[i7] = d3;
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    private T getPointFromLargestVarianceCluster(Collection<Cluster<T>> collection) throws ConvergenceException {
        double d = Double.NEGATIVE_INFINITY;
        Cluster cluster = null;
        for (Cluster next : collection) {
            if (!next.getPoints().isEmpty()) {
                Clusterable center = next.getCenter();
                Variance variance = new Variance();
                for (Clusterable distanceFrom : next.getPoints()) {
                    variance.increment(distanceFrom.distanceFrom(center));
                }
                double result = variance.getResult();
                if (result > d) {
                    cluster = next;
                    d = result;
                }
            }
        }
        if (cluster != null) {
            List points = cluster.getPoints();
            return (Clusterable) points.remove(this.random.nextInt(points.size()));
        }
        throw new ConvergenceException(LocalizedFormats.EMPTY_CLUSTER_IN_K_MEANS, new Object[0]);
    }

    private T getPointFromLargestNumberCluster(Collection<Cluster<T>> collection) throws ConvergenceException {
        Cluster cluster = null;
        int i = 0;
        for (Cluster next : collection) {
            int size = next.getPoints().size();
            if (size > i) {
                cluster = next;
                i = size;
            }
        }
        if (cluster != null) {
            List points = cluster.getPoints();
            return (Clusterable) points.remove(this.random.nextInt(points.size()));
        }
        throw new ConvergenceException(LocalizedFormats.EMPTY_CLUSTER_IN_K_MEANS, new Object[0]);
    }

    private T getFarthestPoint(Collection<Cluster<T>> collection) throws ConvergenceException {
        Iterator<Cluster<T>> it = collection.iterator();
        double d = Double.NEGATIVE_INFINITY;
        Cluster cluster = null;
        int i = -1;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Cluster next = it.next();
            Clusterable center = next.getCenter();
            List points = next.getPoints();
            for (int i2 = 0; i2 < points.size(); i2++) {
                double distanceFrom = ((Clusterable) points.get(i2)).distanceFrom(center);
                if (distanceFrom > d) {
                    cluster = next;
                    i = i2;
                    d = distanceFrom;
                }
            }
        }
        if (cluster != null) {
            return (Clusterable) cluster.getPoints().remove(i);
        }
        throw new ConvergenceException(LocalizedFormats.EMPTY_CLUSTER_IN_K_MEANS, new Object[0]);
    }

    private static <T extends Clusterable<T>> int getNearestCluster(Collection<Cluster<T>> collection, T t) {
        double d = Double.MAX_VALUE;
        int i = 0;
        int i2 = 0;
        for (Cluster<T> center : collection) {
            double distanceFrom = t.distanceFrom(center.getCenter());
            if (distanceFrom < d) {
                i = i2;
                d = distanceFrom;
            }
            i2++;
        }
        return i;
    }
}
