package org.apache.commons.math3.geometry.euclidean.threed;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.euclidean.oned.Interval;
import org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet;
import org.apache.commons.math3.geometry.euclidean.oned.Vector1D;
import org.apache.commons.math3.geometry.partitioning.Region;

public class SubLine {
    private static final double DEFAULT_TOLERANCE = 1.0E-10d;
    private final Line line;
    private final IntervalsSet remainingRegion;

    public SubLine(Line line2, IntervalsSet intervalsSet) {
        this.line = line2;
        this.remainingRegion = intervalsSet;
    }

    public SubLine(Vector3D vector3D, Vector3D vector3D2, double d) throws MathIllegalArgumentException {
        this(new Line(vector3D, vector3D2, d), buildIntervalSet(vector3D, vector3D2, d));
    }

    public SubLine(Vector3D vector3D, Vector3D vector3D2) throws MathIllegalArgumentException {
        this(vector3D, vector3D2, 1.0E-10d);
    }

    public SubLine(Segment segment) throws MathIllegalArgumentException {
        this(segment.getLine(), buildIntervalSet(segment.getStart(), segment.getEnd(), segment.getLine().getTolerance()));
    }

    public List<Segment> getSegments() {
        List<Interval> asList = this.remainingRegion.asList();
        ArrayList arrayList = new ArrayList(asList.size());
        for (Interval next : asList) {
            arrayList.add(new Segment(this.line.toSpace((Point) new Vector1D(next.getInf())), this.line.toSpace((Point) new Vector1D(next.getSup())), this.line));
        }
        return arrayList;
    }

    public Vector3D intersection(SubLine subLine, boolean z) {
        Vector3D intersection = this.line.intersection(subLine.line);
        if (intersection == null) {
            return null;
        }
        Region.Location checkPoint = this.remainingRegion.checkPoint(this.line.toSubSpace((Point) intersection));
        Region.Location checkPoint2 = subLine.remainingRegion.checkPoint(subLine.line.toSubSpace((Point) intersection));
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

    private static IntervalsSet buildIntervalSet(Vector3D vector3D, Vector3D vector3D2, double d) throws MathIllegalArgumentException {
        Line line2 = new Line(vector3D, vector3D2, d);
        return new IntervalsSet(line2.toSubSpace((Point) vector3D).getX(), line2.toSubSpace((Point) vector3D2).getX(), d);
    }
}
