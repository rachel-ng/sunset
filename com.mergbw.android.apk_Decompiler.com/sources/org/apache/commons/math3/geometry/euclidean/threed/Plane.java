package org.apache.commons.math3.geometry.euclidean.threed;

import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.euclidean.oned.Vector1D;
import org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D;
import org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.geometry.partitioning.Embedding;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.util.FastMath;

public class Plane implements Hyperplane<Euclidean3D>, Embedding<Euclidean3D, Euclidean2D> {
    private static final double DEFAULT_TOLERANCE = 1.0E-10d;
    private Vector3D origin;
    private double originOffset;
    private final double tolerance;
    private Vector3D u;
    private Vector3D v;
    private Vector3D w;

    public Plane(Vector3D vector3D, double d) throws MathArithmeticException {
        setNormal(vector3D);
        this.tolerance = d;
        this.originOffset = 0.0d;
        setFrame();
    }

    public Plane(Vector3D vector3D, Vector3D vector3D2, double d) throws MathArithmeticException {
        setNormal(vector3D2);
        this.tolerance = d;
        this.originOffset = -vector3D.dotProduct(this.w);
        setFrame();
    }

    public Plane(Vector3D vector3D, Vector3D vector3D2, Vector3D vector3D3, double d) throws MathArithmeticException {
        this(vector3D, vector3D2.subtract((Vector) vector3D).crossProduct(vector3D3.subtract((Vector) vector3D)), d);
    }

    @Deprecated
    public Plane(Vector3D vector3D) throws MathArithmeticException {
        this(vector3D, 1.0E-10d);
    }

    @Deprecated
    public Plane(Vector3D vector3D, Vector3D vector3D2) throws MathArithmeticException {
        this(vector3D, vector3D2, 1.0E-10d);
    }

    @Deprecated
    public Plane(Vector3D vector3D, Vector3D vector3D2, Vector3D vector3D3) throws MathArithmeticException {
        this(vector3D, vector3D2, vector3D3, 1.0E-10d);
    }

    public Plane(Plane plane) {
        this.originOffset = plane.originOffset;
        this.origin = plane.origin;
        this.u = plane.u;
        this.v = plane.v;
        this.w = plane.w;
        this.tolerance = plane.tolerance;
    }

    public Plane copySelf() {
        return new Plane(this);
    }

    public void reset(Vector3D vector3D, Vector3D vector3D2) throws MathArithmeticException {
        setNormal(vector3D2);
        this.originOffset = -vector3D.dotProduct(this.w);
        setFrame();
    }

    public void reset(Plane plane) {
        this.originOffset = plane.originOffset;
        this.origin = plane.origin;
        this.u = plane.u;
        this.v = plane.v;
        this.w = plane.w;
    }

    private void setNormal(Vector3D vector3D) throws MathArithmeticException {
        double norm = vector3D.getNorm();
        if (norm >= 1.0E-10d) {
            this.w = new Vector3D(1.0d / norm, vector3D);
            return;
        }
        throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
    }

    private void setFrame() {
        this.origin = new Vector3D(-this.originOffset, this.w);
        Vector3D orthogonal = this.w.orthogonal();
        this.u = orthogonal;
        this.v = Vector3D.crossProduct(this.w, orthogonal);
    }

    public Vector3D getOrigin() {
        return this.origin;
    }

    public Vector3D getNormal() {
        return this.w;
    }

    public Vector3D getU() {
        return this.u;
    }

    public Vector3D getV() {
        return this.v;
    }

    public Point<Euclidean3D> project(Point<Euclidean3D> point) {
        return toSpace((Vector<Euclidean2D>) toSubSpace((Point) point));
    }

    public double getTolerance() {
        return this.tolerance;
    }

    public void revertSelf() {
        Vector3D vector3D = this.u;
        this.u = this.v;
        this.v = vector3D;
        this.w = this.w.negate();
        this.originOffset = -this.originOffset;
    }

    public Vector2D toSubSpace(Vector<Euclidean3D> vector) {
        return toSubSpace((Point) vector);
    }

    public Vector3D toSpace(Vector<Euclidean2D> vector) {
        return toSpace((Point) vector);
    }

    public Vector2D toSubSpace(Point<Euclidean3D> point) {
        Vector3D vector3D = (Vector3D) point;
        return new Vector2D(vector3D.dotProduct(this.u), vector3D.dotProduct(this.v));
    }

    public Vector3D toSpace(Point<Euclidean2D> point) {
        Vector2D vector2D = (Vector2D) point;
        return new Vector3D(vector2D.getX(), this.u, vector2D.getY(), this.v, -this.originOffset, this.w);
    }

    public Vector3D getPointAt(Vector2D vector2D, double d) {
        return new Vector3D(vector2D.getX(), this.u, vector2D.getY(), this.v, d - this.originOffset, this.w);
    }

    public boolean isSimilarTo(Plane plane) {
        double angle = Vector3D.angle(this.w, plane.w);
        return (angle < 1.0E-10d && FastMath.abs(this.originOffset - plane.originOffset) < this.tolerance) || (angle > 3.141592653489793d && FastMath.abs(this.originOffset + plane.originOffset) < this.tolerance);
    }

    public Plane rotate(Vector3D vector3D, Rotation rotation) {
        Plane plane = new Plane(vector3D.add((Vector) rotation.applyTo(this.origin.subtract((Vector) vector3D))), rotation.applyTo(this.w), this.tolerance);
        plane.u = rotation.applyTo(this.u);
        plane.v = rotation.applyTo(this.v);
        return plane;
    }

    public Plane translate(Vector3D vector3D) {
        Plane plane = new Plane(this.origin.add((Vector) vector3D), this.w, this.tolerance);
        plane.u = this.u;
        plane.v = this.v;
        return plane;
    }

    public Vector3D intersection(Line line) {
        Vector3D direction = line.getDirection();
        double dotProduct = this.w.dotProduct(direction);
        if (FastMath.abs(dotProduct) < 1.0E-10d) {
            return null;
        }
        Vector3D space = line.toSpace((Point) Vector1D.ZERO);
        return new Vector3D(1.0d, space, (-(this.originOffset + this.w.dotProduct(space))) / dotProduct, direction);
    }

    public Line intersection(Plane plane) {
        Vector3D crossProduct = Vector3D.crossProduct(this.w, plane.w);
        double norm = crossProduct.getNorm();
        double d = this.tolerance;
        if (norm < d) {
            return null;
        }
        Vector3D intersection = intersection(this, plane, new Plane(crossProduct, d));
        return new Line(intersection, intersection.add((Vector) crossProduct), this.tolerance);
    }

    public static Vector3D intersection(Plane plane, Plane plane2, Plane plane3) {
        Plane plane4 = plane;
        Plane plane5 = plane2;
        Plane plane6 = plane3;
        double x = plane4.w.getX();
        double y = plane4.w.getY();
        double z = plane4.w.getZ();
        double d = plane4.originOffset;
        double x2 = plane5.w.getX();
        double y2 = plane5.w.getY();
        double z2 = plane5.w.getZ();
        double d2 = plane5.originOffset;
        double x3 = plane6.w.getX();
        double d3 = d;
        double y3 = plane6.w.getY();
        double d4 = z;
        double z3 = plane6.w.getZ();
        double d5 = y;
        double d6 = plane6.originOffset;
        double d7 = (y2 * z3) - (y3 * z2);
        double d8 = z3;
        double d9 = (z2 * x3) - (z3 * x2);
        double d10 = x2;
        double d11 = (x2 * y3) - (x3 * y2);
        double d12 = (x * d7) + (d5 * d9) + (d4 * d11);
        if (FastMath.abs(d12) < 1.0E-10d) {
            return null;
        }
        double d13 = 1.0d / d12;
        double d14 = d2;
        return new Vector3D(((((-d7) * d3) - (((d4 * y3) - (d8 * d5)) * d14)) - (((z2 * d5) - (d4 * y2)) * d6)) * d13, ((((-d9) * d3) - (((d8 * x) - (d4 * x3)) * d14)) - (((d4 * d10) - (z2 * x)) * d6)) * d13, ((((-d11) * d3) - (((x3 * d5) - (y3 * x)) * d14)) - (((y2 * x) - (d5 * d10)) * d6)) * d13);
    }

    public SubPlane wholeHyperplane() {
        return new SubPlane(this, new PolygonsSet(this.tolerance));
    }

    public PolyhedronsSet wholeSpace() {
        return new PolyhedronsSet(this.tolerance);
    }

    public boolean contains(Vector3D vector3D) {
        return FastMath.abs(getOffset((Vector<Euclidean3D>) vector3D)) < this.tolerance;
    }

    public double getOffset(Plane plane) {
        return this.originOffset + (sameOrientationAs(plane) ? -plane.originOffset : plane.originOffset);
    }

    public double getOffset(Vector<Euclidean3D> vector) {
        return getOffset((Point<Euclidean3D>) vector);
    }

    public double getOffset(Point<Euclidean3D> point) {
        return ((Vector3D) point).dotProduct(this.w) + this.originOffset;
    }

    public boolean sameOrientationAs(Hyperplane<Euclidean3D> hyperplane) {
        return ((Plane) hyperplane).w.dotProduct(this.w) > 0.0d;
    }
}
