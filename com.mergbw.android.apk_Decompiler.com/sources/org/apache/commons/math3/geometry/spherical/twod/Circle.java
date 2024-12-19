package org.apache.commons.math3.geometry.spherical.twod;

import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.euclidean.threed.Rotation;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.partitioning.Embedding;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.geometry.partitioning.Transform;
import org.apache.commons.math3.geometry.spherical.oned.Arc;
import org.apache.commons.math3.geometry.spherical.oned.ArcsSet;
import org.apache.commons.math3.geometry.spherical.oned.S1Point;
import org.apache.commons.math3.geometry.spherical.oned.Sphere1D;
import org.apache.commons.math3.util.FastMath;

public class Circle implements Hyperplane<Sphere2D>, Embedding<Sphere2D, Sphere1D> {
    /* access modifiers changed from: private */
    public Vector3D pole;
    /* access modifiers changed from: private */
    public final double tolerance;
    /* access modifiers changed from: private */
    public Vector3D x;
    /* access modifiers changed from: private */
    public Vector3D y;

    public Circle(Vector3D vector3D, double d) {
        reset(vector3D);
        this.tolerance = d;
    }

    public Circle(S2Point s2Point, S2Point s2Point2, double d) {
        reset(s2Point.getVector().crossProduct(s2Point2.getVector()));
        this.tolerance = d;
    }

    private Circle(Vector3D vector3D, Vector3D vector3D2, Vector3D vector3D3, double d) {
        this.pole = vector3D;
        this.x = vector3D2;
        this.y = vector3D3;
        this.tolerance = d;
    }

    public Circle(Circle circle) {
        this(circle.pole, circle.x, circle.y, circle.tolerance);
    }

    public Circle copySelf() {
        return new Circle(this);
    }

    public void reset(Vector3D vector3D) {
        this.pole = vector3D.normalize();
        Vector3D orthogonal = vector3D.orthogonal();
        this.x = orthogonal;
        this.y = Vector3D.crossProduct(vector3D, orthogonal).normalize();
    }

    public void revertSelf() {
        this.y = this.y.negate();
        this.pole = this.pole.negate();
    }

    public Circle getReverse() {
        return new Circle(this.pole.negate(), this.x, this.y.negate(), this.tolerance);
    }

    public Point<Sphere2D> project(Point<Sphere2D> point) {
        return toSpace((Point) toSubSpace((Point) point));
    }

    public double getTolerance() {
        return this.tolerance;
    }

    public S1Point toSubSpace(Point<Sphere2D> point) {
        return new S1Point(getPhase(((S2Point) point).getVector()));
    }

    public double getPhase(Vector3D vector3D) {
        return FastMath.atan2(-vector3D.dotProduct(this.y), -vector3D.dotProduct(this.x)) + 3.141592653589793d;
    }

    public S2Point toSpace(Point<Sphere1D> point) {
        return new S2Point(getPointAt(((S1Point) point).getAlpha()));
    }

    public Vector3D getPointAt(double d) {
        return new Vector3D(FastMath.cos(d), this.x, FastMath.sin(d), this.y);
    }

    public Vector3D getXAxis() {
        return this.x;
    }

    public Vector3D getYAxis() {
        return this.y;
    }

    public Vector3D getPole() {
        return this.pole;
    }

    public Arc getInsideArc(Circle circle) {
        double phase = getPhase(circle.pole);
        return new Arc(phase - 1.5707963267948966d, phase + 1.5707963267948966d, this.tolerance);
    }

    public SubCircle wholeHyperplane() {
        return new SubCircle(this, new ArcsSet(this.tolerance));
    }

    public SphericalPolygonsSet wholeSpace() {
        return new SphericalPolygonsSet(this.tolerance);
    }

    public double getOffset(Point<Sphere2D> point) {
        return getOffset(((S2Point) point).getVector());
    }

    public double getOffset(Vector3D vector3D) {
        return Vector3D.angle(this.pole, vector3D) - 1.5707963267948966d;
    }

    public boolean sameOrientationAs(Hyperplane<Sphere2D> hyperplane) {
        return Vector3D.dotProduct(this.pole, ((Circle) hyperplane).pole) >= 0.0d;
    }

    public static Transform<Sphere2D, Sphere1D> getTransform(Rotation rotation) {
        return new CircleTransform(rotation);
    }

    private static class CircleTransform implements Transform<Sphere2D, Sphere1D> {
        private final Rotation rotation;

        public SubHyperplane<Sphere1D> apply(SubHyperplane<Sphere1D> subHyperplane, Hyperplane<Sphere2D> hyperplane, Hyperplane<Sphere2D> hyperplane2) {
            return subHyperplane;
        }

        public CircleTransform(Rotation rotation2) {
            this.rotation = rotation2;
        }

        public S2Point apply(Point<Sphere2D> point) {
            return new S2Point(this.rotation.applyTo(((S2Point) point).getVector()));
        }

        public Circle apply(Hyperplane<Sphere2D> hyperplane) {
            Circle circle = (Circle) hyperplane;
            return new Circle(this.rotation.applyTo(circle.pole), this.rotation.applyTo(circle.x), this.rotation.applyTo(circle.y), circle.tolerance);
        }
    }
}
