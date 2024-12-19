package org.apache.commons.math3.ml.clustering;

import java.util.Collection;
import java.util.List;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.ml.clustering.Clusterable;
import org.apache.commons.math3.ml.clustering.evaluation.ClusterEvaluator;
import org.apache.commons.math3.ml.clustering.evaluation.SumOfClusterVariances;

public class MultiKMeansPlusPlusClusterer<T extends Clusterable> extends Clusterer<T> {
    private final KMeansPlusPlusClusterer<T> clusterer;
    private final ClusterEvaluator<T> evaluator;
    private final int numTrials;

    public MultiKMeansPlusPlusClusterer(KMeansPlusPlusClusterer<T> kMeansPlusPlusClusterer, int i) {
        this(kMeansPlusPlusClusterer, i, new SumOfClusterVariances(kMeansPlusPlusClusterer.getDistanceMeasure()));
    }

    public MultiKMeansPlusPlusClusterer(KMeansPlusPlusClusterer<T> kMeansPlusPlusClusterer, int i, ClusterEvaluator<T> clusterEvaluator) {
        super(kMeansPlusPlusClusterer.getDistanceMeasure());
        this.clusterer = kMeansPlusPlusClusterer;
        this.numTrials = i;
        this.evaluator = clusterEvaluator;
    }

    public KMeansPlusPlusClusterer<T> getClusterer() {
        return this.clusterer;
    }

    public int getNumTrials() {
        return this.numTrials;
    }

    public ClusterEvaluator<T> getClusterEvaluator() {
        return this.evaluator;
    }

    public List<CentroidCluster<T>> cluster(Collection<T> collection) throws MathIllegalArgumentException, ConvergenceException {
        List<CentroidCluster<T>> list = null;
        double d = Double.POSITIVE_INFINITY;
        for (int i = 0; i < this.numTrials; i++) {
            List<CentroidCluster<T>> cluster = this.clusterer.cluster(collection);
            double score = this.evaluator.score(cluster);
            if (this.evaluator.isBetterScore(score, d)) {
                list = cluster;
                d = score;
            }
        }
        return list;
    }
}
