package org.apache.commons.math3.geometry.spherical.twod;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.Side;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.geometry.spherical.oned.ArcsSet;
import org.apache.commons.math3.geometry.spherical.oned.Sphere1D;

public class SubCircle extends AbstractSubHyperplane<Sphere2D, Sphere1D> {
    public SubCircle(Hyperplane<Sphere2D> hyperplane, Region<Sphere1D> region) {
        super(hyperplane, region);
    }

    /* access modifiers changed from: protected */
    public AbstractSubHyperplane<Sphere2D, Sphere1D> buildNew(Hyperplane<Sphere2D> hyperplane, Region<Sphere1D> region) {
        return new SubCircle(hyperplane, region);
    }

    public Side side(Hyperplane<Sphere2D> hyperplane) {
        Circle circle = (Circle) getHyperplane();
        Circle circle2 = (Circle) hyperplane;
        double angle = Vector3D.angle(circle.getPole(), circle2.getPole());
        if (angle < circle.getTolerance() || angle > 3.141592653589793d - circle.getTolerance()) {
            return Side.HYPER;
        }
        return ((ArcsSet) getRemainingRegion()).side(circle.getInsideArc(circle2));
    }

    public SubHyperplane.SplitSubHyperplane<Sphere2D> split(Hyperplane<Sphere2D> hyperplane) {
        Circle circle = (Circle) getHyperplane();
        Circle circle2 = (Circle) hyperplane;
        double angle = Vector3D.angle(circle.getPole(), circle2.getPole());
        SubCircle subCircle = null;
        if (angle < circle.getTolerance()) {
            return new SubHyperplane.SplitSubHyperplane<>((SubHyperplane) null, this);
        }
        if (angle > 3.141592653589793d - circle.getTolerance()) {
            return new SubHyperplane.SplitSubHyperplane<>(this, (SubCircle) null);
        }
        ArcsSet.Split split = ((ArcsSet) getRemainingRegion()).split(circle.getInsideArc(circle2));
        ArcsSet plus = split.getPlus();
        ArcsSet minus = split.getMinus();
        SubCircle subCircle2 = plus == null ? null : new SubCircle(circle.copySelf(), plus);
        if (minus != null) {
            subCircle = new SubCircle(circle.copySelf(), minus);
        }
        return new SubHyperplane.SplitSubHyperplane<>(subCircle2, subCircle);
    }
}
