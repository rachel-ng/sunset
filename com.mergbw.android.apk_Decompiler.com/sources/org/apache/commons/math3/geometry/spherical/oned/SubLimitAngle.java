package org.apache.commons.math3.geometry.spherical.oned;

import org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.Side;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;

public class SubLimitAngle extends AbstractSubHyperplane<Sphere1D, Sphere1D> {
    public double getSize() {
        return 0.0d;
    }

    public boolean isEmpty() {
        return false;
    }

    public SubLimitAngle(Hyperplane<Sphere1D> hyperplane, Region<Sphere1D> region) {
        super(hyperplane, region);
    }

    /* access modifiers changed from: protected */
    public AbstractSubHyperplane<Sphere1D, Sphere1D> buildNew(Hyperplane<Sphere1D> hyperplane, Region<Sphere1D> region) {
        return new SubLimitAngle(hyperplane, region);
    }

    public Side side(Hyperplane<Sphere1D> hyperplane) {
        double offset = hyperplane.getOffset(((LimitAngle) getHyperplane()).getLocation());
        if (offset < -1.0E-10d) {
            return Side.MINUS;
        }
        return offset > 1.0E-10d ? Side.PLUS : Side.HYPER;
    }

    public SubHyperplane.SplitSubHyperplane<Sphere1D> split(Hyperplane<Sphere1D> hyperplane) {
        return hyperplane.getOffset(((LimitAngle) getHyperplane()).getLocation()) < -1.0E-10d ? new SubHyperplane.SplitSubHyperplane<>((SubHyperplane) null, this) : new SubHyperplane.SplitSubHyperplane<>(this, (SubLimitAngle) null);
    }
}
