package org.apache.commons.math3.geometry.euclidean.twod.hull;

import java.io.Serializable;
import org.apache.commons.math3.exception.InsufficientDataException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D;
import org.apache.commons.math3.geometry.euclidean.twod.Line;
import org.apache.commons.math3.geometry.euclidean.twod.Segment;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.geometry.hull.ConvexHull;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.RegionFactory;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.Precision;

public class ConvexHull2D implements ConvexHull<Euclidean2D, Vector2D>, Serializable {
    private static final long serialVersionUID = 20140129;
    private transient Segment[] lineSegments;
    private final double tolerance;
    private final Vector2D[] vertices;

    public ConvexHull2D(Vector2D[] vector2DArr, double d) throws MathIllegalArgumentException {
        this.tolerance = d;
        if (isConvex(vector2DArr)) {
            this.vertices = (Vector2D[]) vector2DArr.clone();
            return;
        }
        throw new MathIllegalArgumentException(LocalizedFormats.NOT_CONVEX, new Object[0]);
    }

    private boolean isConvex(Vector2D[] vector2DArr) {
        Vector2D[] vector2DArr2 = vector2DArr;
        if (vector2DArr2.length < 3) {
            return true;
        }
        int i = 0;
        int i2 = 0;
        while (i < vector2DArr2.length) {
            Vector2D vector2D = vector2DArr2[i == 0 ? vector2DArr2.length - 1 : i - 1];
            Vector2D vector2D2 = vector2DArr2[i];
            Vector2D vector2D3 = vector2DArr2[i == vector2DArr2.length - 1 ? 0 : i + 1];
            Vector2D subtract = vector2D2.subtract((Vector) vector2D);
            Vector2D subtract2 = vector2D3.subtract((Vector) vector2D2);
            int compareTo = Precision.compareTo(MathArrays.linearCombination(subtract.getX(), subtract2.getY(), -subtract.getY(), subtract2.getX()), 0.0d, this.tolerance);
            if (((double) compareTo) != 0.0d) {
                if (((double) i2) != 0.0d && compareTo != i2) {
                    return false;
                }
                i2 = compareTo;
            }
            i++;
        }
        return true;
    }

    public Vector2D[] getVertices() {
        return (Vector2D[]) this.vertices.clone();
    }

    public Segment[] getLineSegments() {
        return (Segment[]) retrieveLineSegments().clone();
    }

    private Segment[] retrieveLineSegments() {
        if (this.lineSegments == null) {
            Vector2D[] vector2DArr = this.vertices;
            int length = vector2DArr.length;
            int i = 0;
            if (length <= 1) {
                this.lineSegments = new Segment[0];
            } else if (length == 2) {
                Segment[] segmentArr = new Segment[1];
                this.lineSegments = segmentArr;
                Vector2D vector2D = vector2DArr[0];
                Vector2D vector2D2 = vector2DArr[1];
                segmentArr[0] = new Segment(vector2D, vector2D2, new Line(vector2D, vector2D2, this.tolerance));
            } else {
                this.lineSegments = new Segment[length];
                int length2 = vector2DArr.length;
                Vector2D vector2D3 = null;
                Vector2D vector2D4 = null;
                int i2 = 0;
                while (i < length2) {
                    Vector2D vector2D5 = vector2DArr[i];
                    if (vector2D3 == null) {
                        vector2D4 = vector2D5;
                    } else {
                        this.lineSegments[i2] = new Segment(vector2D3, vector2D5, new Line(vector2D3, vector2D5, this.tolerance));
                        i2++;
                    }
                    i++;
                    vector2D3 = vector2D5;
                }
                this.lineSegments[i2] = new Segment(vector2D3, vector2D4, new Line(vector2D3, vector2D4, this.tolerance));
            }
        }
        return this.lineSegments;
    }

    public Region<Euclidean2D> createRegion() throws InsufficientDataException {
        if (this.vertices.length >= 3) {
            RegionFactory regionFactory = new RegionFactory();
            Segment[] retrieveLineSegments = retrieveLineSegments();
            Line[] lineArr = new Line[retrieveLineSegments.length];
            for (int i = 0; i < retrieveLineSegments.length; i++) {
                lineArr[i] = retrieveLineSegments[i].getLine();
            }
            return regionFactory.buildConvex(lineArr);
        }
        throw new InsufficientDataException();
    }
}
