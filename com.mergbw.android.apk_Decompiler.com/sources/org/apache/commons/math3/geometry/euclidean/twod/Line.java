package org.apache.commons.math3.geometry.euclidean.twod;

import java.awt.geom.AffineTransform;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
import org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet;
import org.apache.commons.math3.geometry.euclidean.oned.OrientedPoint;
import org.apache.commons.math3.geometry.euclidean.oned.Vector1D;
import org.apache.commons.math3.geometry.partitioning.Embedding;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.geometry.partitioning.Transform;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

public class Line implements Hyperplane<Euclidean2D>, Embedding<Euclidean2D, Euclidean1D> {
    private static final double DEFAULT_TOLERANCE = 1.0E-10d;
    private double angle;
    /* access modifiers changed from: private */
    public double cos;
    /* access modifiers changed from: private */
    public double originOffset;
    private Line reverse;
    /* access modifiers changed from: private */
    public double sin;
    /* access modifiers changed from: private */
    public final double tolerance;

    public Line(Vector2D vector2D, Vector2D vector2D2, double d) {
        reset(vector2D, vector2D2);
        this.tolerance = d;
    }

    public Line(Vector2D vector2D, double d, double d2) {
        reset(vector2D, d);
        this.tolerance = d2;
    }

    private Line(double d, double d2, double d3, double d4, double d5) {
        this.angle = d;
        this.cos = d2;
        this.sin = d3;
        this.originOffset = d4;
        this.tolerance = d5;
        this.reverse = null;
    }

    @Deprecated
    public Line(Vector2D vector2D, Vector2D vector2D2) {
        this(vector2D, vector2D2, 1.0E-10d);
    }

    @Deprecated
    public Line(Vector2D vector2D, double d) {
        this(vector2D, d, 1.0E-10d);
    }

    public Line(Line line) {
        this.angle = MathUtils.normalizeAngle(line.angle, 3.141592653589793d);
        this.cos = line.cos;
        this.sin = line.sin;
        this.originOffset = line.originOffset;
        this.tolerance = line.tolerance;
        this.reverse = null;
    }

    public Line copySelf() {
        return new Line(this);
    }

    public void reset(Vector2D vector2D, Vector2D vector2D2) {
        unlinkReverse();
        double x = vector2D2.getX() - vector2D.getX();
        double y = vector2D2.getY() - vector2D.getY();
        double hypot = FastMath.hypot(x, y);
        if (hypot == 0.0d) {
            this.angle = 0.0d;
            this.cos = 1.0d;
            this.sin = 0.0d;
            this.originOffset = vector2D.getY();
            return;
        }
        this.angle = FastMath.atan2(-y, -x) + 3.141592653589793d;
        this.cos = x / hypot;
        this.sin = y / hypot;
        this.originOffset = MathArrays.linearCombination(vector2D2.getX(), vector2D.getY(), -vector2D.getX(), vector2D2.getY()) / hypot;
    }

    public void reset(Vector2D vector2D, double d) {
        unlinkReverse();
        double normalizeAngle = MathUtils.normalizeAngle(d, 3.141592653589793d);
        this.angle = normalizeAngle;
        this.cos = FastMath.cos(normalizeAngle);
        this.sin = FastMath.sin(this.angle);
        this.originOffset = MathArrays.linearCombination(this.cos, vector2D.getY(), -this.sin, vector2D.getX());
    }

    public void revertSelf() {
        unlinkReverse();
        double d = this.angle;
        if (d < 3.141592653589793d) {
            this.angle = d + 3.141592653589793d;
        } else {
            this.angle = d - 3.141592653589793d;
        }
        this.cos = -this.cos;
        this.sin = -this.sin;
        this.originOffset = -this.originOffset;
    }

    private void unlinkReverse() {
        Line line = this.reverse;
        if (line != null) {
            line.reverse = null;
        }
        this.reverse = null;
    }

    public Line getReverse() {
        if (this.reverse == null) {
            double d = this.angle;
            Line line = new Line(d < 3.141592653589793d ? d + 3.141592653589793d : d - 3.141592653589793d, -this.cos, -this.sin, -this.originOffset, this.tolerance);
            this.reverse = line;
            line.reverse = this;
        }
        return this.reverse;
    }

    public Vector1D toSubSpace(Vector<Euclidean2D> vector) {
        return toSubSpace((Point) vector);
    }

    public Vector2D toSpace(Vector<Euclidean1D> vector) {
        return toSpace((Point) vector);
    }

    public Vector1D toSubSpace(Point<Euclidean2D> point) {
        Vector2D vector2D = (Vector2D) point;
        return new Vector1D(MathArrays.linearCombination(this.cos, vector2D.getX(), this.sin, vector2D.getY()));
    }

    public Vector2D toSpace(Point<Euclidean1D> point) {
        double x = ((Vector1D) point).getX();
        return new Vector2D(MathArrays.linearCombination(x, this.cos, -this.originOffset, this.sin), MathArrays.linearCombination(x, this.sin, this.originOffset, this.cos));
    }

    public Vector2D intersection(Line line) {
        double linearCombination = MathArrays.linearCombination(this.sin, line.cos, -line.sin, this.cos);
        if (FastMath.abs(linearCombination) < this.tolerance) {
            return null;
        }
        return new Vector2D(MathArrays.linearCombination(this.cos, line.originOffset, -line.cos, this.originOffset) / linearCombination, MathArrays.linearCombination(this.sin, line.originOffset, -line.sin, this.originOffset) / linearCombination);
    }

    public Point<Euclidean2D> project(Point<Euclidean2D> point) {
        return toSpace((Vector<Euclidean1D>) toSubSpace((Point) point));
    }

    public double getTolerance() {
        return this.tolerance;
    }

    public SubLine wholeHyperplane() {
        return new SubLine((Hyperplane<Euclidean2D>) this, (Region<Euclidean1D>) new IntervalsSet(this.tolerance));
    }

    public PolygonsSet wholeSpace() {
        return new PolygonsSet(this.tolerance);
    }

    public double getOffset(Line line) {
        return this.originOffset + (MathArrays.linearCombination(this.cos, line.cos, this.sin, line.sin) > 0.0d ? -line.originOffset : line.originOffset);
    }

    public double getOffset(Vector<Euclidean2D> vector) {
        return getOffset((Point<Euclidean2D>) vector);
    }

    public double getOffset(Point<Euclidean2D> point) {
        Vector2D vector2D = (Vector2D) point;
        return MathArrays.linearCombination(this.sin, vector2D.getX(), -this.cos, vector2D.getY(), 1.0d, this.originOffset);
    }

    public boolean sameOrientationAs(Hyperplane<Euclidean2D> hyperplane) {
        Line line = (Line) hyperplane;
        return MathArrays.linearCombination(this.sin, line.sin, this.cos, line.cos) >= 0.0d;
    }

    public Vector2D getPointAt(Vector1D vector1D, double d) {
        double x = vector1D.getX();
        double d2 = d - this.originOffset;
        double d3 = x;
        return new Vector2D(MathArrays.linearCombination(d3, this.cos, d2, this.sin), MathArrays.linearCombination(d3, this.sin, -d2, this.cos));
    }

    public boolean contains(Vector2D vector2D) {
        return FastMath.abs(getOffset((Vector<Euclidean2D>) vector2D)) < this.tolerance;
    }

    public double distance(Vector2D vector2D) {
        return FastMath.abs(getOffset((Vector<Euclidean2D>) vector2D));
    }

    public boolean isParallelTo(Line line) {
        return FastMath.abs(MathArrays.linearCombination(this.sin, line.cos, -this.cos, line.sin)) < this.tolerance;
    }

    public void translateToPoint(Vector2D vector2D) {
        this.originOffset = MathArrays.linearCombination(this.cos, vector2D.getY(), -this.sin, vector2D.getX());
    }

    public double getAngle() {
        return MathUtils.normalizeAngle(this.angle, 3.141592653589793d);
    }

    public void setAngle(double d) {
        unlinkReverse();
        double normalizeAngle = MathUtils.normalizeAngle(d, 3.141592653589793d);
        this.angle = normalizeAngle;
        this.cos = FastMath.cos(normalizeAngle);
        this.sin = FastMath.sin(this.angle);
    }

    public double getOriginOffset() {
        return this.originOffset;
    }

    public void setOriginOffset(double d) {
        unlinkReverse();
        this.originOffset = d;
    }

    public static Transform<Euclidean2D, Euclidean1D> getTransform(AffineTransform affineTransform) throws MathIllegalArgumentException {
        return new LineTransform(affineTransform);
    }

    private static class LineTransform implements Transform<Euclidean2D, Euclidean1D> {
        private double c11;
        private double c1X = MathArrays.linearCombination(this.cXX, this.cY1, -this.cYX, this.cX1);
        private double c1Y;
        private double cX1;
        private double cXX;
        private double cXY;
        private double cY1;
        private double cYX;
        private double cYY;

        public LineTransform(AffineTransform affineTransform) throws MathIllegalArgumentException {
            double[] dArr = new double[6];
            affineTransform.getMatrix(dArr);
            this.cXX = dArr[0];
            double d = dArr[2];
            this.cXY = d;
            double d2 = dArr[4];
            this.cX1 = d2;
            this.cYX = dArr[1];
            double d3 = dArr[3];
            this.cYY = d3;
            double d4 = dArr[5];
            this.cY1 = d4;
            this.c1Y = MathArrays.linearCombination(d, d4, -d3, d2);
            double linearCombination = MathArrays.linearCombination(this.cXX, this.cYY, -this.cYX, this.cXY);
            this.c11 = linearCombination;
            if (FastMath.abs(linearCombination) < 1.0E-20d) {
                throw new MathIllegalArgumentException(LocalizedFormats.NON_INVERTIBLE_TRANSFORM, new Object[0]);
            }
        }

        public Vector2D apply(Point<Euclidean2D> point) {
            Vector2D vector2D = (Vector2D) point;
            double x = vector2D.getX();
            double d = x;
            double y = vector2D.getY();
            return new Vector2D(MathArrays.linearCombination(this.cXX, d, this.cXY, y, this.cX1, 1.0d), MathArrays.linearCombination(this.cYX, d, this.cYY, y, this.cY1, 1.0d));
        }

        public Line apply(Hyperplane<Euclidean2D> hyperplane) {
            Line line = (Line) hyperplane;
            double linearCombination = MathArrays.linearCombination(this.c1X, line.cos, this.c1Y, line.sin, this.c11, line.originOffset);
            double linearCombination2 = MathArrays.linearCombination(this.cXX, line.cos, this.cXY, line.sin);
            double linearCombination3 = MathArrays.linearCombination(this.cYX, line.cos, this.cYY, line.sin);
            double sqrt = 1.0d / FastMath.sqrt((linearCombination3 * linearCombination3) + (linearCombination2 * linearCombination2));
            return new Line(FastMath.atan2(-linearCombination3, -linearCombination2) + 3.141592653589793d, sqrt * linearCombination2, sqrt * linearCombination3, sqrt * linearCombination, line.tolerance);
        }

        public SubHyperplane<Euclidean1D> apply(SubHyperplane<Euclidean1D> subHyperplane, Hyperplane<Euclidean2D> hyperplane, Hyperplane<Euclidean2D> hyperplane2) {
            OrientedPoint orientedPoint = (OrientedPoint) subHyperplane.getHyperplane();
            Line line = (Line) hyperplane;
            return new OrientedPoint(((Line) hyperplane2).toSubSpace((Vector<Euclidean2D>) apply((Point) line.toSpace((Vector<Euclidean1D>) orientedPoint.getLocation()))), orientedPoint.isDirect(), line.tolerance).wholeHyperplane();
        }
    }
}
