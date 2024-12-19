package org.apache.commons.math3.geometry.euclidean.twod;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
import org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.RegionFactory;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;

class NestedLoops {
    private Vector2D[] loop;
    private boolean originalIsClockwise;
    private Region<Euclidean2D> polygon;
    private ArrayList<NestedLoops> surrounded;
    private final double tolerance;

    public NestedLoops(double d) {
        this.surrounded = new ArrayList<>();
        this.tolerance = d;
    }

    private NestedLoops(Vector2D[] vector2DArr, double d) throws MathIllegalArgumentException {
        Vector2D[] vector2DArr2 = vector2DArr;
        double d2 = d;
        if (vector2DArr2[0] != null) {
            this.loop = vector2DArr2;
            this.surrounded = new ArrayList<>();
            this.tolerance = d2;
            ArrayList arrayList = new ArrayList();
            Vector2D vector2D = vector2DArr2[vector2DArr2.length - 1];
            int i = 0;
            while (i < vector2DArr2.length) {
                Vector2D vector2D2 = vector2DArr2[i];
                Line line = new Line(vector2D, vector2D2, d2);
                IntervalsSet intervalsSet = r2;
                IntervalsSet intervalsSet2 = new IntervalsSet(line.toSubSpace((Point) vector2D).getX(), line.toSubSpace((Point) vector2D2).getX(), d);
                arrayList.add(new SubLine((Hyperplane<Euclidean2D>) line, (Region<Euclidean1D>) intervalsSet));
                i++;
                vector2D = vector2D2;
            }
            PolygonsSet polygonsSet = new PolygonsSet((Collection<SubHyperplane<Euclidean2D>>) arrayList, d2);
            this.polygon = polygonsSet;
            if (Double.isInfinite(polygonsSet.getSize())) {
                this.polygon = new RegionFactory().getComplement(this.polygon);
                this.originalIsClockwise = false;
                return;
            }
            this.originalIsClockwise = true;
            return;
        }
        throw new MathIllegalArgumentException(LocalizedFormats.OUTLINE_BOUNDARY_LOOP_OPEN, new Object[0]);
    }

    public void add(Vector2D[] vector2DArr) throws MathIllegalArgumentException {
        add(new NestedLoops(vector2DArr, this.tolerance));
    }

    private void add(NestedLoops nestedLoops) throws MathIllegalArgumentException {
        Iterator<NestedLoops> it = this.surrounded.iterator();
        while (it.hasNext()) {
            NestedLoops next = it.next();
            if (next.polygon.contains(nestedLoops.polygon)) {
                next.add(nestedLoops);
                return;
            }
        }
        Iterator<NestedLoops> it2 = this.surrounded.iterator();
        while (it2.hasNext()) {
            NestedLoops next2 = it2.next();
            if (nestedLoops.polygon.contains(next2.polygon)) {
                nestedLoops.surrounded.add(next2);
                it2.remove();
            }
        }
        RegionFactory regionFactory = new RegionFactory();
        Iterator<NestedLoops> it3 = this.surrounded.iterator();
        while (it3.hasNext()) {
            if (!regionFactory.intersection(nestedLoops.polygon, it3.next().polygon).isEmpty()) {
                throw new MathIllegalArgumentException(LocalizedFormats.CROSSING_BOUNDARY_LOOPS, new Object[0]);
            }
        }
        this.surrounded.add(nestedLoops);
    }

    public void correctOrientation() {
        Iterator<NestedLoops> it = this.surrounded.iterator();
        while (it.hasNext()) {
            it.next().setClockWise(true);
        }
    }

    private void setClockWise(boolean z) {
        if (this.originalIsClockwise ^ z) {
            int length = this.loop.length;
            int i = -1;
            while (true) {
                i++;
                length--;
                if (i >= length) {
                    break;
                }
                Vector2D[] vector2DArr = this.loop;
                Vector2D vector2D = vector2DArr[i];
                vector2DArr[i] = vector2DArr[length];
                vector2DArr[length] = vector2D;
            }
        }
        Iterator<NestedLoops> it = this.surrounded.iterator();
        while (it.hasNext()) {
            it.next().setClockWise(!z);
        }
    }
}
