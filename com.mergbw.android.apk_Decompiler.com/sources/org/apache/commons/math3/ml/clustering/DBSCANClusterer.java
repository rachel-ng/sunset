package org.apache.commons.math3.ml.clustering;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.ml.clustering.Clusterable;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.ml.distance.EuclideanDistance;
import org.apache.commons.math3.util.MathUtils;

public class DBSCANClusterer<T extends Clusterable> extends Clusterer<T> {
    private final double eps;
    private final int minPts;

    private enum PointStatus {
        NOISE,
        PART_OF_CLUSTER
    }

    public DBSCANClusterer(double d, int i) throws NotPositiveException {
        this(d, i, new EuclideanDistance());
    }

    public DBSCANClusterer(double d, int i, DistanceMeasure distanceMeasure) throws NotPositiveException {
        super(distanceMeasure);
        if (d < 0.0d) {
            throw new NotPositiveException(Double.valueOf(d));
        } else if (i >= 0) {
            this.eps = d;
            this.minPts = i;
        } else {
            throw new NotPositiveException(Integer.valueOf(i));
        }
    }

    public double getEps() {
        return this.eps;
    }

    public int getMinPts() {
        return this.minPts;
    }

    public List<Cluster<T>> cluster(Collection<T> collection) throws NullArgumentException {
        MathUtils.checkNotNull(collection);
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        for (T t : collection) {
            if (hashMap.get(t) == null) {
                List<T> neighbors = getNeighbors(t, collection);
                if (neighbors.size() >= this.minPts) {
                    arrayList.add(expandCluster(new Cluster(), t, neighbors, collection, hashMap));
                } else {
                    hashMap.put(t, PointStatus.NOISE);
                }
            }
        }
        return arrayList;
    }

    private Cluster<T> expandCluster(Cluster<T> cluster, T t, List<T> list, Collection<T> collection, Map<Clusterable, PointStatus> map) {
        cluster.addPoint(t);
        map.put(t, PointStatus.PART_OF_CLUSTER);
        List<T> arrayList = new ArrayList<>(list);
        for (int i = 0; i < arrayList.size(); i++) {
            Clusterable clusterable = (Clusterable) arrayList.get(i);
            PointStatus pointStatus = map.get(clusterable);
            if (pointStatus == null) {
                List<T> neighbors = getNeighbors(clusterable, collection);
                if (neighbors.size() >= this.minPts) {
                    arrayList = merge(arrayList, neighbors);
                }
            }
            if (pointStatus != PointStatus.PART_OF_CLUSTER) {
                map.put(clusterable, PointStatus.PART_OF_CLUSTER);
                cluster.addPoint(clusterable);
            }
        }
        return cluster;
    }

    private List<T> getNeighbors(T t, Collection<T> collection) {
        ArrayList arrayList = new ArrayList();
        for (T t2 : collection) {
            if (t != t2 && distance(t2, t) <= this.eps) {
                arrayList.add(t2);
            }
        }
        return arrayList;
    }

    private List<T> merge(List<T> list, List<T> list2) {
        HashSet hashSet = new HashSet(list);
        for (T t : list2) {
            if (!hashSet.contains(t)) {
                list.add(t);
            }
        }
        return list;
    }
}
