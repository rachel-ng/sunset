package org.apache.commons.math3.geometry.spherical.twod;

import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

public class S2Point implements Point<Sphere2D> {
    public static final S2Point MINUS_I = new S2Point(3.141592653589793d, 1.5707963267948966d, Vector3D.MINUS_I);
    public static final S2Point MINUS_J = new S2Point(4.71238898038469d, 1.5707963267948966d, Vector3D.MINUS_J);
    public static final S2Point MINUS_K = new S2Point(0.0d, 3.141592653589793d, Vector3D.MINUS_K);
    public static final S2Point NaN = new S2Point(Double.NaN, Double.NaN, Vector3D.NaN);
    public static final S2Point PLUS_I = new S2Point(0.0d, 1.5707963267948966d, Vector3D.PLUS_I);
    public static final S2Point PLUS_J = new S2Point(1.5707963267948966d, 1.5707963267948966d, Vector3D.PLUS_J);
    public static final S2Point PLUS_K = new S2Point(0.0d, 0.0d, Vector3D.PLUS_K);
    private static final long serialVersionUID = 20131218;
    private final double phi;
    private final double theta;
    private final Vector3D vector;

    public S2Point(double d, double d2) throws OutOfRangeException {
        this(d, d2, vector(d, d2));
    }

    public S2Point(Vector3D vector3D) throws MathArithmeticException {
        this(FastMath.atan2(vector3D.getY(), vector3D.getX()), Vector3D.angle(Vector3D.PLUS_K, vector3D), vector3D.normalize());
    }

    private S2Point(double d, double d2, Vector3D vector3D) {
        this.theta = d;
        this.phi = d2;
        this.vector = vector3D;
    }

    private static Vector3D vector(double d, double d2) throws OutOfRangeException {
        if (d2 < 0.0d || d2 > 3.141592653589793d) {
            throw new OutOfRangeException(Double.valueOf(d2), 0, Double.valueOf(3.141592653589793d));
        }
        double cos = FastMath.cos(d);
        double sin = FastMath.sin(d);
        double cos2 = FastMath.cos(d2);
        double sin2 = FastMath.sin(d2);
        return new Vector3D(cos * sin2, sin * sin2, cos2);
    }

    public double getTheta() {
        return this.theta;
    }

    public double getPhi() {
        return this.phi;
    }

    public Vector3D getVector() {
        return this.vector;
    }

    public Space getSpace() {
        return Sphere2D.getInstance();
    }

    public boolean isNaN() {
        return Double.isNaN(this.theta) || Double.isNaN(this.phi);
    }

    public S2Point negate() {
        return new S2Point(-this.theta, 3.141592653589793d - this.phi, this.vector.negate());
    }

    public double distance(Point<Sphere2D> point) {
        return distance(this, (S2Point) point);
    }

    public static double distance(S2Point s2Point, S2Point s2Point2) {
        return Vector3D.angle(s2Point.vector, s2Point2.vector);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof S2Point)) {
            return false;
        }
        S2Point s2Point = (S2Point) obj;
        if (s2Point.isNaN()) {
            return isNaN();
        }
        if (this.theta == s2Point.theta && this.phi == s2Point.phi) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (isNaN()) {
            return 542;
        }
        return ((MathUtils.hash(this.theta) * 37) + MathUtils.hash(this.phi)) * TsExtractor.TS_STREAM_TYPE_SPLICE_INFO;
    }
}
