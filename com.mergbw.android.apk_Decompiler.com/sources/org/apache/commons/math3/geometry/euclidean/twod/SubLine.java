package org.apache.commons.math3.geometry.euclidean.twod;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
import org.apache.commons.math3.geometry.euclidean.oned.Interval;
import org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet;
import org.apache.commons.math3.geometry.euclidean.oned.OrientedPoint;
import org.apache.commons.math3.geometry.euclidean.oned.SubOrientedPoint;
import org.apache.commons.math3.geometry.euclidean.oned.Vector1D;
import org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.Side;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.util.FastMath;

public class SubLine extends AbstractSubHyperplane<Euclidean2D, Euclidean1D> {
    private static final double DEFAULT_TOLERANCE = 1.0E-10d;

    public SubLine(Hyperplane<Euclidean2D> hyperplane, Region<Euclidean1D> region) {
        super(hyperplane, region);
    }

    public SubLine(Vector2D vector2D, Vector2D vector2D2, double d) {
        super(new Line(vector2D, vector2D2, d), buildIntervalSet(vector2D, vector2D2, d));
    }

    @Deprecated
    public SubLine(Vector2D vector2D, Vector2D vector2D2) {
        this(vector2D, vector2D2, 1.0E-10d);
    }

    public SubLine(Segment segment) {
        super(segment.getLine(), buildIntervalSet(segment.getStart(), segment.getEnd(), segment.getLine().getTolerance()));
    }

    public List<Segment> getSegments() {
        Line line = (Line) getHyperplane();
        List<Interval> asList = ((IntervalsSet) getRemainingRegion()).asList();
        ArrayList arrayList = new ArrayList(asList.size());
        for (Interval next : asList) {
            arrayList.add(new Segment(line.toSpace((Point) new Vector1D(next.getInf())), line.toSpace((Point) new Vector1D(next.getSup())), line));
        }
        return arrayList;
    }

    public Vector2D intersection(SubLine subLine, boolean z) {
        Line line = (Line) getHyperplane();
        Line line2 = (Line) subLine.getHyperplane();
        Vector2D intersection = line.intersection(line2);
        if (intersection == null) {
            return null;
        }
        Region.Location checkPoint = getRemainingRegion().checkPoint(line.toSubSpace((Point) intersection));
        Region.Location checkPoint2 = subLine.getRemainingRegion().checkPoint(line2.toSubSpace((Point) intersection));
        if (z) {
            if (checkPoint == Region.Location.OUTSIDE || checkPoint2 == Region.Location.OUTSIDE) {
                return null;
            }
            return intersection;
        } else if (checkPoint == Region.Location.INSIDE && checkPoint2 == Region.Location.INSIDE) {
            return intersection;
        } else {
            return null;
        }
    }

    private static IntervalsSet buildIntervalSet(Vector2D vector2D, Vector2D vector2D2, double d) {
        Line line = new Line(vector2D, vector2D2, d);
        return new IntervalsSet(line.toSubSpace((Point) vector2D).getX(), line.toSubSpace((Point) vector2D2).getX(), d);
    }

    /* access modifiers changed from: protected */
    public AbstractSubHyperplane<Euclidean2D, Euclidean1D> buildNew(Hyperplane<Euclidean2D> hyperplane, Region<Euclidean1D> region) {
        return new SubLine(hyperplane, region);
    }

    public Side side(Hyperplane<Euclidean2D> hyperplane) {
        Line line = (Line) getHyperplane();
        Line line2 = (Line) hyperplane;
        Vector2D intersection = line.intersection(line2);
        if (intersection == null) {
            double offset = line2.getOffset(line);
            if (offset < -1.0E-10d) {
                return Side.MINUS;
            }
            return offset > 1.0E-10d ? Side.PLUS : Side.HYPER;
        }
        return getRemainingRegion().side(new OrientedPoint(line.toSubSpace((Point) intersection), FastMath.sin(line.getAngle() - line2.getAngle()) < 0.0d, line.getTolerance()));
    }

    public SubHyperplane.SplitSubHyperplane<Euclidean2D> split(Hyperplane<Euclidean2D> hyperplane) {
        Line line = (Line) getHyperplane();
        Line line2 = (Line) hyperplane;
        Vector2D intersection = line.intersection(line2);
        double tolerance = line.getTolerance();
        if (intersection == null) {
            return line2.getOffset(line) < -1.0E-10d ? new SubHyperplane.SplitSubHyperplane<>((SubHyperplane) null, this) : new SubHyperplane.SplitSubHyperplane<>(this, (SubLine) null);
        }
        boolean z = FastMath.sin(line.getAngle() - line2.getAngle()) < 0.0d;
        Vector1D subSpace = line.toSubSpace((Point) intersection);
        SubOrientedPoint wholeHyperplane = new OrientedPoint(subSpace, !z, tolerance).wholeHyperplane();
        SubOrientedPoint wholeHyperplane2 = new OrientedPoint(subSpace, z, tolerance).wholeHyperplane();
        BSPTree split = getRemainingRegion().getTree(false).split(wholeHyperplane2);
        return new SubHyperplane.SplitSubHyperplane<>(new SubLine((Hyperplane<Euclidean2D>) line.copySelf(), (Region<Euclidean1D>) new IntervalsSet((BSPTree<Euclidean1D>) getRemainingRegion().isEmpty(split.getPlus()) ? new BSPTree(Boolean.FALSE) : new BSPTree(wholeHyperplane, new BSPTree(Boolean.FALSE), split.getPlus(), (Object) null), tolerance)), new SubLine((Hyperplane<Euclidean2D>) line.copySelf(), (Region<Euclidean1D>) new IntervalsSet((BSPTree<Euclidean1D>) getRemainingRegion().isEmpty(split.getMinus()) ? new BSPTree(Boolean.FALSE) : new BSPTree(wholeHyperplane2, new BSPTree(Boolean.FALSE), split.getMinus(), (Object) null), tolerance)));
    }
}
