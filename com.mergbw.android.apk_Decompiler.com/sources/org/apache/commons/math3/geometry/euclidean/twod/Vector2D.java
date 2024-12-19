package org.apache.commons.math3.geometry.euclidean.twod;

import java.text.NumberFormat;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

public class Vector2D implements Vector<Euclidean2D> {
    public static final Vector2D NEGATIVE_INFINITY = new Vector2D(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
    public static final Vector2D NaN = new Vector2D(Double.NaN, Double.NaN);
    public static final Vector2D POSITIVE_INFINITY = new Vector2D(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
    public static final Vector2D ZERO = new Vector2D(0.0d, 0.0d);
    private static final long serialVersionUID = 266938651998679754L;
    private final double x;
    private final double y;

    public Vector2D(double d, double d2) {
        this.x = d;
        this.y = d2;
    }

    public Vector2D(double[] dArr) throws DimensionMismatchException {
        if (dArr.length == 2) {
            this.x = dArr[0];
            this.y = dArr[1];
            return;
        }
        throw new DimensionMismatchException(dArr.length, 2);
    }

    public Vector2D(double d, Vector2D vector2D) {
        this.x = vector2D.x * d;
        this.y = d * vector2D.y;
    }

    public Vector2D(double d, Vector2D vector2D, double d2, Vector2D vector2D2) {
        this.x = (vector2D.x * d) + (vector2D2.x * d2);
        this.y = (d * vector2D.y) + (d2 * vector2D2.y);
    }

    public Vector2D(double d, Vector2D vector2D, double d2, Vector2D vector2D2, double d3, Vector2D vector2D3) {
        this.x = (vector2D.x * d) + (vector2D2.x * d2) + (vector2D3.x * d3);
        this.y = (d * vector2D.y) + (d2 * vector2D2.y) + (d3 * vector2D3.y);
    }

    public Vector2D(double d, Vector2D vector2D, double d2, Vector2D vector2D2, double d3, Vector2D vector2D3, double d4, Vector2D vector2D4) {
        Vector2D vector2D5 = vector2D;
        Vector2D vector2D6 = vector2D2;
        Vector2D vector2D7 = vector2D3;
        Vector2D vector2D8 = vector2D4;
        this.x = (vector2D5.x * d) + (vector2D6.x * d2) + (vector2D7.x * d3) + (vector2D8.x * d4);
        this.y = (vector2D5.y * d) + (vector2D6.y * d2) + (vector2D7.y * d3) + (vector2D8.y * d4);
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double[] toArray() {
        return new double[]{this.x, this.y};
    }

    public Space getSpace() {
        return Euclidean2D.getInstance();
    }

    public Vector2D getZero() {
        return ZERO;
    }

    public double getNorm1() {
        return FastMath.abs(this.x) + FastMath.abs(this.y);
    }

    public double getNorm() {
        double d = this.x;
        double d2 = this.y;
        return FastMath.sqrt((d * d) + (d2 * d2));
    }

    public double getNormSq() {
        double d = this.x;
        double d2 = this.y;
        return (d * d) + (d2 * d2);
    }

    public double getNormInf() {
        return FastMath.max(FastMath.abs(this.x), FastMath.abs(this.y));
    }

    public Vector2D add(Vector<Euclidean2D> vector) {
        Vector2D vector2D = (Vector2D) vector;
        return new Vector2D(this.x + vector2D.getX(), this.y + vector2D.getY());
    }

    public Vector2D add(double d, Vector<Euclidean2D> vector) {
        Vector2D vector2D = (Vector2D) vector;
        return new Vector2D(this.x + (vector2D.getX() * d), this.y + (d * vector2D.getY()));
    }

    public Vector2D subtract(Vector<Euclidean2D> vector) {
        Vector2D vector2D = (Vector2D) vector;
        return new Vector2D(this.x - vector2D.x, this.y - vector2D.y);
    }

    public Vector2D subtract(double d, Vector<Euclidean2D> vector) {
        Vector2D vector2D = (Vector2D) vector;
        return new Vector2D(this.x - (vector2D.getX() * d), this.y - (d * vector2D.getY()));
    }

    public Vector2D normalize() throws MathArithmeticException {
        double norm = getNorm();
        if (norm != 0.0d) {
            return scalarMultiply(1.0d / norm);
        }
        throw new MathArithmeticException(LocalizedFormats.CANNOT_NORMALIZE_A_ZERO_NORM_VECTOR, new Object[0]);
    }

    public static double angle(Vector2D vector2D, Vector2D vector2D2) throws MathArithmeticException {
        double norm = vector2D.getNorm() * vector2D2.getNorm();
        if (norm != 0.0d) {
            double dotProduct = vector2D.dotProduct(vector2D2);
            double d = 0.9999d * norm;
            if (dotProduct >= (-d) && dotProduct <= d) {
                return FastMath.acos(dotProduct / norm);
            }
            double abs = FastMath.abs(MathArrays.linearCombination(vector2D.x, vector2D2.y, -vector2D.y, vector2D2.x));
            if (dotProduct >= 0.0d) {
                return FastMath.asin(abs / norm);
            }
            return 3.141592653589793d - FastMath.asin(abs / norm);
        }
        throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
    }

    public Vector2D negate() {
        return new Vector2D(-this.x, -this.y);
    }

    public Vector2D scalarMultiply(double d) {
        return new Vector2D(this.x * d, d * this.y);
    }

    public boolean isNaN() {
        return Double.isNaN(this.x) || Double.isNaN(this.y);
    }

    public boolean isInfinite() {
        return !isNaN() && (Double.isInfinite(this.x) || Double.isInfinite(this.y));
    }

    public double distance1(Vector<Euclidean2D> vector) {
        Vector2D vector2D = (Vector2D) vector;
        return FastMath.abs(vector2D.x - this.x) + FastMath.abs(vector2D.y - this.y);
    }

    public double distance(Vector<Euclidean2D> vector) {
        return distance((Point<Euclidean2D>) vector);
    }

    public double distance(Point<Euclidean2D> point) {
        Vector2D vector2D = (Vector2D) point;
        double d = vector2D.x - this.x;
        double d2 = vector2D.y - this.y;
        return FastMath.sqrt((d * d) + (d2 * d2));
    }

    public double distanceInf(Vector<Euclidean2D> vector) {
        Vector2D vector2D = (Vector2D) vector;
        return FastMath.max(FastMath.abs(vector2D.x - this.x), FastMath.abs(vector2D.y - this.y));
    }

    public double distanceSq(Vector<Euclidean2D> vector) {
        Vector2D vector2D = (Vector2D) vector;
        double d = vector2D.x - this.x;
        double d2 = vector2D.y - this.y;
        return (d * d) + (d2 * d2);
    }

    public double dotProduct(Vector<Euclidean2D> vector) {
        Vector2D vector2D = (Vector2D) vector;
        return MathArrays.linearCombination(this.x, vector2D.x, this.y, vector2D.y);
    }

    public double crossProduct(Vector2D vector2D, Vector2D vector2D2) {
        return MathArrays.linearCombination(vector2D2.getX() - vector2D.getX(), getY() - vector2D.getY(), -(getX() - vector2D.getX()), vector2D2.getY() - vector2D.getY());
    }

    public static double distance(Vector2D vector2D, Vector2D vector2D2) {
        return vector2D.distance((Vector<Euclidean2D>) vector2D2);
    }

    public static double distanceInf(Vector2D vector2D, Vector2D vector2D2) {
        return vector2D.distanceInf(vector2D2);
    }

    public static double distanceSq(Vector2D vector2D, Vector2D vector2D2) {
        return vector2D.distanceSq(vector2D2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Vector2D)) {
            return false;
        }
        Vector2D vector2D = (Vector2D) obj;
        if (vector2D.isNaN()) {
            return isNaN();
        }
        if (this.x == vector2D.x && this.y == vector2D.y) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (isNaN()) {
            return 542;
        }
        return ((MathUtils.hash(this.x) * 76) + MathUtils.hash(this.y)) * 122;
    }

    public String toString() {
        return Vector2DFormat.getInstance().format(this);
    }

    public String toString(NumberFormat numberFormat) {
        return new Vector2DFormat(numberFormat).format(this);
    }
}
