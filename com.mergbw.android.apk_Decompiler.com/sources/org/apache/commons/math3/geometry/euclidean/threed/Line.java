package org.apache.commons.math3.geometry.euclidean.threed;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
import org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet;
import org.apache.commons.math3.geometry.euclidean.oned.Vector1D;
import org.apache.commons.math3.geometry.partitioning.Embedding;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

public class Line implements Embedding<Euclidean3D, Euclidean1D> {
    private static final double DEFAULT_TOLERANCE = 1.0E-10d;
    private Vector3D direction;
    private final double tolerance;
    private Vector3D zero;

    public Line(Vector3D vector3D, Vector3D vector3D2, double d) throws MathIllegalArgumentException {
        reset(vector3D, vector3D2);
        this.tolerance = d;
    }

    public Line(Line line) {
        this.direction = line.direction;
        this.zero = line.zero;
        this.tolerance = line.tolerance;
    }

    @Deprecated
    public Line(Vector3D vector3D, Vector3D vector3D2) throws MathIllegalArgumentException {
        this(vector3D, vector3D2, 1.0E-10d);
    }

    public void reset(Vector3D vector3D, Vector3D vector3D2) throws MathIllegalArgumentException {
        Vector3D subtract = vector3D2.subtract((Vector) vector3D);
        double normSq = subtract.getNormSq();
        if (normSq != 0.0d) {
            this.direction = new Vector3D(1.0d / FastMath.sqrt(normSq), subtract);
            this.zero = new Vector3D(1.0d, vector3D, (-vector3D.dotProduct(subtract)) / normSq, subtract);
            return;
        }
        throw new MathIllegalArgumentException(LocalizedFormats.ZERO_NORM, new Object[0]);
    }

    public double getTolerance() {
        return this.tolerance;
    }

    public Line revert() {
        Line line = new Line(this);
        line.direction = line.direction.negate();
        return line;
    }

    public Vector3D getDirection() {
        return this.direction;
    }

    public Vector3D getOrigin() {
        return this.zero;
    }

    public double getAbscissa(Vector3D vector3D) {
        return vector3D.subtract((Vector) this.zero).dotProduct(this.direction);
    }

    public Vector3D pointAt(double d) {
        return new Vector3D(1.0d, this.zero, d, this.direction);
    }

    public Vector1D toSubSpace(Vector<Euclidean3D> vector) {
        return toSubSpace((Point) vector);
    }

    public Vector3D toSpace(Vector<Euclidean1D> vector) {
        return toSpace((Point) vector);
    }

    public Vector1D toSubSpace(Point<Euclidean3D> point) {
        return new Vector1D(getAbscissa((Vector3D) point));
    }

    public Vector3D toSpace(Point<Euclidean1D> point) {
        return pointAt(((Vector1D) point).getX());
    }

    public boolean isSimilarTo(Line line) {
        double angle = Vector3D.angle(this.direction, line.direction);
        double d = this.tolerance;
        return (angle < d || angle > 3.141592653589793d - d) && contains(line.zero);
    }

    public boolean contains(Vector3D vector3D) {
        return distance(vector3D) < this.tolerance;
    }

    public double distance(Vector3D vector3D) {
        Vector3D subtract = vector3D.subtract((Vector) this.zero);
        return new Vector3D(1.0d, subtract, -subtract.dotProduct(this.direction), this.direction).getNorm();
    }

    public double distance(Line line) {
        Vector3D crossProduct = Vector3D.crossProduct(this.direction, line.direction);
        double norm = crossProduct.getNorm();
        if (norm < Precision.SAFE_MIN) {
            return distance(line.zero);
        }
        return FastMath.abs(line.zero.subtract((Vector) this.zero).dotProduct(crossProduct) / norm);
    }

    public Vector3D closestPoint(Line line) {
        Line line2 = line;
        double dotProduct = this.direction.dotProduct(line2.direction);
        double d = 1.0d - (dotProduct * dotProduct);
        if (d < Precision.EPSILON) {
            return this.zero;
        }
        Vector3D subtract = line2.zero.subtract((Vector) this.zero);
        return new Vector3D(1.0d, this.zero, (subtract.dotProduct(this.direction) - (subtract.dotProduct(line2.direction) * dotProduct)) / d, this.direction);
    }

    public Vector3D intersection(Line line) {
        Vector3D closestPoint = closestPoint(line);
        if (line.contains(closestPoint)) {
            return closestPoint;
        }
        return null;
    }

    public SubLine wholeLine() {
        return new SubLine(this, new IntervalsSet(this.tolerance));
    }
}
