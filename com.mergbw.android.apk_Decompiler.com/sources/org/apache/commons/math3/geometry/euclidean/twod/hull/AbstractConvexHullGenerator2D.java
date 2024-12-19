package org.apache.commons.math3.geometry.euclidean.twod.hull;

import java.util.Collection;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.MathUtils;

abstract class AbstractConvexHullGenerator2D implements ConvexHullGenerator2D {
    private static final double DEFAULT_TOLERANCE = 1.0E-10d;
    private final boolean includeCollinearPoints;
    private final double tolerance;

    /* access modifiers changed from: protected */
    public abstract Collection<Vector2D> findHullVertices(Collection<Vector2D> collection);

    protected AbstractConvexHullGenerator2D(boolean z) {
        this(z, 1.0E-10d);
    }

    protected AbstractConvexHullGenerator2D(boolean z, double d) {
        this.includeCollinearPoints = z;
        this.tolerance = d;
    }

    public double getTolerance() {
        return this.tolerance;
    }

    public boolean isIncludeCollinearPoints() {
        return this.includeCollinearPoints;
    }

    public ConvexHull2D generate(Collection<Vector2D> collection) throws NullArgumentException, ConvergenceException {
        MathUtils.checkNotNull(collection);
        if (collection.size() >= 2) {
            collection = findHullVertices(collection);
        }
        try {
            return new ConvexHull2D((Vector2D[]) collection.toArray(new Vector2D[collection.size()]), this.tolerance);
        } catch (MathIllegalArgumentException unused) {
            throw new ConvergenceException();
        }
    }
}
