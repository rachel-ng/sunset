package org.apache.commons.math3.ml.clustering.evaluation;

import java.util.List;
import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.ml.clustering.Cluster;
import org.apache.commons.math3.ml.clustering.Clusterable;
import org.apache.commons.math3.ml.clustering.DoublePoint;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.ml.distance.EuclideanDistance;

public abstract class ClusterEvaluator<T extends Clusterable> {
    private final DistanceMeasure measure;

    public boolean isBetterScore(double d, double d2) {
        return d < d2;
    }

    public abstract double score(List<? extends Cluster<T>> list);

    public ClusterEvaluator() {
        this(new EuclideanDistance());
    }

    public ClusterEvaluator(DistanceMeasure distanceMeasure) {
        this.measure = distanceMeasure;
    }

    /* access modifiers changed from: protected */
    public double distance(Clusterable clusterable, Clusterable clusterable2) {
        return this.measure.compute(clusterable.getPoint(), clusterable2.getPoint());
    }

    /* access modifiers changed from: protected */
    public Clusterable centroidOf(Cluster<T> cluster) {
        List<T> points = cluster.getPoints();
        if (points.isEmpty()) {
            return null;
        }
        if (cluster instanceof CentroidCluster) {
            return ((CentroidCluster) cluster).getCenter();
        }
        int length = ((Clusterable) points.get(0)).getPoint().length;
        double[] dArr = new double[length];
        for (T point : points) {
            double[] point2 = point.getPoint();
            for (int i = 0; i < length; i++) {
                dArr[i] = dArr[i] + point2[i];
            }
        }
        for (int i2 = 0; i2 < length; i2++) {
            dArr[i2] = dArr[i2] / ((double) points.size());
        }
        return new DoublePoint(dArr);
    }
}
