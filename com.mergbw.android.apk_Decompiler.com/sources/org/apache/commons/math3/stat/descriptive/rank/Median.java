package org.apache.commons.math3.stat.descriptive.rank;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.stat.descriptive.rank.Percentile;
import org.apache.commons.math3.stat.ranking.NaNStrategy;
import org.apache.commons.math3.util.KthSelector;

public class Median extends Percentile implements Serializable {
    private static final double FIXED_QUANTILE_50 = 50.0d;
    private static final long serialVersionUID = -3961477041290915687L;

    public Median() {
        super((double) FIXED_QUANTILE_50);
    }

    public Median(Median median) throws NullArgumentException {
        super((Percentile) median);
    }

    private Median(Percentile.EstimationType estimationType, NaNStrategy naNStrategy, KthSelector kthSelector) throws MathIllegalArgumentException {
        super(FIXED_QUANTILE_50, estimationType, naNStrategy, kthSelector);
    }

    public Median withEstimationType(Percentile.EstimationType estimationType) {
        return new Median(estimationType, getNaNStrategy(), getKthSelector());
    }

    public Median withNaNStrategy(NaNStrategy naNStrategy) {
        return new Median(getEstimationType(), naNStrategy, getKthSelector());
    }

    public Median withKthSelector(KthSelector kthSelector) {
        return new Median(getEstimationType(), getNaNStrategy(), kthSelector);
    }
}
