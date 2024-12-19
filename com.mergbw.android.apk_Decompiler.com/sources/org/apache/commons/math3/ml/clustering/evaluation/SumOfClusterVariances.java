package org.apache.commons.math3.ml.clustering.evaluation;

import java.util.List;
import org.apache.commons.math3.ml.clustering.Cluster;
import org.apache.commons.math3.ml.clustering.Clusterable;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.stat.descriptive.moment.Variance;

public class SumOfClusterVariances<T extends Clusterable> extends ClusterEvaluator<T> {
    public SumOfClusterVariances(DistanceMeasure distanceMeasure) {
        super(distanceMeasure);
    }

    public double score(List<? extends Cluster<T>> list) {
        double d = 0.0d;
        for (Cluster cluster : list) {
            if (!cluster.getPoints().isEmpty()) {
                Clusterable centroidOf = centroidOf(cluster);
                Variance variance = new Variance();
                for (Clusterable distance : cluster.getPoints()) {
                    variance.increment(distance(distance, centroidOf));
                }
                d += variance.getResult();
            }
        }
        return d;
    }
}
