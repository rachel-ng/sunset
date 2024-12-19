package org.apache.commons.math3.geometry.spherical.oned;

import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.Region;

public class LimitAngle implements Hyperplane<Sphere1D> {
    private boolean direct;
    private S1Point location;
    private final double tolerance;

    public LimitAngle copySelf() {
        return this;
    }

    public LimitAngle(S1Point s1Point, boolean z, double d) {
        this.location = s1Point;
        this.direct = z;
        this.tolerance = d;
    }

    public double getOffset(Point<Sphere1D> point) {
        double alpha = ((S1Point) point).getAlpha() - this.location.getAlpha();
        return this.direct ? alpha : -alpha;
    }

    public boolean isDirect() {
        return this.direct;
    }

    public LimitAngle getReverse() {
        return new LimitAngle(this.location, !this.direct, this.tolerance);
    }

    public SubLimitAngle wholeHyperplane() {
        return new SubLimitAngle(this, (Region<Sphere1D>) null);
    }

    public ArcsSet wholeSpace() {
        return new ArcsSet(this.tolerance);
    }

    public boolean sameOrientationAs(Hyperplane<Sphere1D> hyperplane) {
        return !(((LimitAngle) hyperplane).direct ^ this.direct);
    }

    public S1Point getLocation() {
        return this.location;
    }

    public Point<Sphere1D> project(Point<Sphere1D> point) {
        return this.location;
    }

    public double getTolerance() {
        return this.tolerance;
    }
}
