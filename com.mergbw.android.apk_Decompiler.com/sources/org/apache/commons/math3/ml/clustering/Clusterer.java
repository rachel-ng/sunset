package org.apache.commons.math3.ml.clustering;

import java.util.Collection;
import java.util.List;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.ml.clustering.Clusterable;
import org.apache.commons.math3.ml.distance.DistanceMeasure;

public abstract class Clusterer<T extends Clusterable> {
    private DistanceMeasure measure;

    public abstract List<? extends Cluster<T>> cluster(Collection<T> collection) throws MathIllegalArgumentException, ConvergenceException;

    protected Clusterer(DistanceMeasure distanceMeasure) {
        this.measure = distanceMeasure;
    }

    public DistanceMeasure getDistanceMeasure() {
        return this.measure;
    }

    /* access modifiers changed from: protected */
    public double distance(Clusterable clusterable, Clusterable clusterable2) {
        return this.measure.compute(clusterable.getPoint(), clusterable2.getPoint());
    }
}
