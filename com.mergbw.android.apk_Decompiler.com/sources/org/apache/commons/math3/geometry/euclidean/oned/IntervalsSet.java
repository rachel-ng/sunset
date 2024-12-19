package org.apache.commons.math3.geometry.euclidean.oned;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.partitioning.AbstractRegion;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.BoundaryProjection;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.util.Precision;

public class IntervalsSet extends AbstractRegion<Euclidean1D, Euclidean1D> implements Iterable<double[]> {
    private static final double DEFAULT_TOLERANCE = 1.0E-10d;

    public IntervalsSet(double d) {
        super(d);
    }

    public IntervalsSet(double d, double d2, double d3) {
        super(buildTree(d, d2, d3), d3);
    }

    public IntervalsSet(BSPTree<Euclidean1D> bSPTree, double d) {
        super(bSPTree, d);
    }

    public IntervalsSet(Collection<SubHyperplane<Euclidean1D>> collection, double d) {
        super(collection, d);
    }

    @Deprecated
    public IntervalsSet() {
        this(1.0E-10d);
    }

    @Deprecated
    public IntervalsSet(double d, double d2) {
        this(d, d2, 1.0E-10d);
    }

    @Deprecated
    public IntervalsSet(BSPTree<Euclidean1D> bSPTree) {
        this(bSPTree, 1.0E-10d);
    }

    @Deprecated
    public IntervalsSet(Collection<SubHyperplane<Euclidean1D>> collection) {
        this(collection, 1.0E-10d);
    }

    private static BSPTree<Euclidean1D> buildTree(double d, double d2, double d3) {
        if (!Double.isInfinite(d) || d >= 0.0d) {
            SubOrientedPoint wholeHyperplane = new OrientedPoint(new Vector1D(d), false, d3).wholeHyperplane();
            if (Double.isInfinite(d2) && d2 > 0.0d) {
                return new BSPTree<>(wholeHyperplane, new BSPTree(Boolean.FALSE), new BSPTree(Boolean.TRUE), (Object) null);
            }
            return new BSPTree<>(wholeHyperplane, new BSPTree(Boolean.FALSE), new BSPTree(new OrientedPoint(new Vector1D(d2), true, d3).wholeHyperplane(), new BSPTree(Boolean.FALSE), new BSPTree(Boolean.TRUE), (Object) null), (Object) null);
        } else if (!Double.isInfinite(d2) || d2 <= 0.0d) {
            return new BSPTree<>(new OrientedPoint(new Vector1D(d2), true, d3).wholeHyperplane(), new BSPTree(Boolean.FALSE), new BSPTree(Boolean.TRUE), (Object) null);
        } else {
            return new BSPTree<>(Boolean.TRUE);
        }
    }

    public IntervalsSet buildNew(BSPTree<Euclidean1D> bSPTree) {
        return new IntervalsSet(bSPTree, getTolerance());
    }

    /* access modifiers changed from: protected */
    public void computeGeometricalProperties() {
        double d = 0.0d;
        if (getTree(false).getCut() == null) {
            setBarycenter(Vector1D.NaN);
            if (((Boolean) getTree(false).getAttribute()).booleanValue()) {
                d = Double.POSITIVE_INFINITY;
            }
            setSize(d);
            return;
        }
        double d2 = 0.0d;
        for (Interval next : asList()) {
            d += next.getSize();
            d2 += next.getSize() * next.getBarycenter();
        }
        setSize(d);
        if (Double.isInfinite(d)) {
            setBarycenter(Vector1D.NaN);
        } else if (d >= Precision.SAFE_MIN) {
            setBarycenter(new Vector1D(d2 / d));
        } else {
            setBarycenter(((OrientedPoint) getTree(false).getCut().getHyperplane()).getLocation());
        }
    }

    public double getInf() {
        BSPTree tree = getTree(false);
        double d = Double.POSITIVE_INFINITY;
        while (tree.getCut() != null) {
            OrientedPoint orientedPoint = (OrientedPoint) tree.getCut().getHyperplane();
            double x = orientedPoint.getLocation().getX();
            tree = orientedPoint.isDirect() ? tree.getMinus() : tree.getPlus();
            d = x;
        }
        if (((Boolean) tree.getAttribute()).booleanValue()) {
            return Double.NEGATIVE_INFINITY;
        }
        return d;
    }

    public double getSup() {
        BSPTree tree = getTree(false);
        double d = Double.NEGATIVE_INFINITY;
        while (tree.getCut() != null) {
            OrientedPoint orientedPoint = (OrientedPoint) tree.getCut().getHyperplane();
            double x = orientedPoint.getLocation().getX();
            tree = orientedPoint.isDirect() ? tree.getPlus() : tree.getMinus();
            d = x;
        }
        if (((Boolean) tree.getAttribute()).booleanValue()) {
            return Double.POSITIVE_INFINITY;
        }
        return d;
    }

    public BoundaryProjection<Euclidean1D> projectToBoundary(Point<Euclidean1D> point) {
        double x = ((Vector1D) point).getX();
        Iterator<double[]> it = iterator();
        double d = Double.NEGATIVE_INFINITY;
        while (it.hasNext()) {
            double[] next = it.next();
            double d2 = next[0];
            if (x < d2) {
                double d3 = x - d;
                double d4 = d2 - x;
                if (d3 < d4) {
                    return new BoundaryProjection<>(point, finiteOrNullPoint(d), d3);
                }
                return new BoundaryProjection<>(point, finiteOrNullPoint(d2), d4);
            }
            d = next[1];
            if (x <= d) {
                double d5 = d2 - x;
                double d6 = x - d;
                if (d5 < d6) {
                    return new BoundaryProjection<>(point, finiteOrNullPoint(d), d6);
                }
                return new BoundaryProjection<>(point, finiteOrNullPoint(d2), d5);
            }
        }
        return new BoundaryProjection<>(point, finiteOrNullPoint(d), x - d);
    }

    private Vector1D finiteOrNullPoint(double d) {
        if (Double.isInfinite(d)) {
            return null;
        }
        return new Vector1D(d);
    }

    public List<Interval> asList() {
        ArrayList arrayList = new ArrayList();
        Iterator<double[]> it = iterator();
        while (it.hasNext()) {
            double[] next = it.next();
            arrayList.add(new Interval(next[0], next[1]));
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public BSPTree<Euclidean1D> getFirstLeaf(BSPTree<Euclidean1D> bSPTree) {
        if (bSPTree.getCut() == null) {
            return bSPTree;
        }
        BSPTree<Euclidean1D> bSPTree2 = null;
        while (bSPTree != null) {
            bSPTree2 = bSPTree;
            bSPTree = previousInternalNode(bSPTree);
        }
        return leafBefore(bSPTree2);
    }

    /* access modifiers changed from: private */
    public BSPTree<Euclidean1D> getFirstIntervalBoundary() {
        BSPTree tree = getTree(false);
        if (tree.getCut() == null) {
            return null;
        }
        BSPTree<Euclidean1D> parent = getFirstLeaf(tree).getParent();
        while (parent != null && !isIntervalStart(parent) && !isIntervalEnd(parent)) {
            parent = nextInternalNode(parent);
        }
        return parent;
    }

    /* access modifiers changed from: private */
    public boolean isIntervalStart(BSPTree<Euclidean1D> bSPTree) {
        if (!((Boolean) leafBefore(bSPTree).getAttribute()).booleanValue() && ((Boolean) leafAfter(bSPTree).getAttribute()).booleanValue()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public boolean isIntervalEnd(BSPTree<Euclidean1D> bSPTree) {
        if (((Boolean) leafBefore(bSPTree).getAttribute()).booleanValue() && !((Boolean) leafAfter(bSPTree).getAttribute()).booleanValue()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public BSPTree<Euclidean1D> nextInternalNode(BSPTree<Euclidean1D> bSPTree) {
        if (childAfter(bSPTree).getCut() != null) {
            return leafAfter(bSPTree).getParent();
        }
        while (isAfterParent(bSPTree)) {
            bSPTree = bSPTree.getParent();
        }
        return bSPTree.getParent();
    }

    private BSPTree<Euclidean1D> previousInternalNode(BSPTree<Euclidean1D> bSPTree) {
        if (childBefore(bSPTree).getCut() != null) {
            return leafBefore(bSPTree).getParent();
        }
        while (isBeforeParent(bSPTree)) {
            bSPTree = bSPTree.getParent();
        }
        return bSPTree.getParent();
    }

    private BSPTree<Euclidean1D> leafBefore(BSPTree<Euclidean1D> bSPTree) {
        BSPTree<Euclidean1D> childBefore = childBefore(bSPTree);
        while (childBefore.getCut() != null) {
            childBefore = childAfter(childBefore);
        }
        return childBefore;
    }

    private BSPTree<Euclidean1D> leafAfter(BSPTree<Euclidean1D> bSPTree) {
        BSPTree<Euclidean1D> childAfter = childAfter(bSPTree);
        while (childAfter.getCut() != null) {
            childAfter = childBefore(childAfter);
        }
        return childAfter;
    }

    private boolean isBeforeParent(BSPTree<Euclidean1D> bSPTree) {
        BSPTree<Euclidean1D> parent = bSPTree.getParent();
        if (parent != null && bSPTree == childBefore(parent)) {
            return true;
        }
        return false;
    }

    private boolean isAfterParent(BSPTree<Euclidean1D> bSPTree) {
        BSPTree<Euclidean1D> parent = bSPTree.getParent();
        if (parent != null && bSPTree == childAfter(parent)) {
            return true;
        }
        return false;
    }

    private BSPTree<Euclidean1D> childBefore(BSPTree<Euclidean1D> bSPTree) {
        if (isDirect(bSPTree)) {
            return bSPTree.getMinus();
        }
        return bSPTree.getPlus();
    }

    private BSPTree<Euclidean1D> childAfter(BSPTree<Euclidean1D> bSPTree) {
        if (isDirect(bSPTree)) {
            return bSPTree.getPlus();
        }
        return bSPTree.getMinus();
    }

    private boolean isDirect(BSPTree<Euclidean1D> bSPTree) {
        return ((OrientedPoint) bSPTree.getCut().getHyperplane()).isDirect();
    }

    /* access modifiers changed from: private */
    public double getAngle(BSPTree<Euclidean1D> bSPTree) {
        return ((OrientedPoint) bSPTree.getCut().getHyperplane()).getLocation().getX();
    }

    public Iterator<double[]> iterator() {
        return new SubIntervalsIterator();
    }

    private class SubIntervalsIterator implements Iterator<double[]> {
        private BSPTree<Euclidean1D> current;
        private double[] pending;

        public SubIntervalsIterator() {
            BSPTree<Euclidean1D> access$000 = IntervalsSet.this.getFirstIntervalBoundary();
            this.current = access$000;
            if (access$000 == null) {
                if (((Boolean) IntervalsSet.this.getFirstLeaf(IntervalsSet.this.getTree(false)).getAttribute()).booleanValue()) {
                    this.pending = new double[]{Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY};
                } else {
                    this.pending = null;
                }
            } else if (IntervalsSet.this.isIntervalEnd(access$000)) {
                this.pending = new double[]{Double.NEGATIVE_INFINITY, IntervalsSet.this.getAngle(this.current)};
            } else {
                selectPending();
            }
        }

        private void selectPending() {
            BSPTree<Euclidean1D> bSPTree = this.current;
            while (bSPTree != null && !IntervalsSet.this.isIntervalStart(bSPTree)) {
                bSPTree = IntervalsSet.this.nextInternalNode(bSPTree);
            }
            if (bSPTree == null) {
                this.current = null;
                this.pending = null;
                return;
            }
            BSPTree<Euclidean1D> bSPTree2 = bSPTree;
            while (bSPTree2 != null && !IntervalsSet.this.isIntervalEnd(bSPTree2)) {
                bSPTree2 = IntervalsSet.this.nextInternalNode(bSPTree2);
            }
            if (bSPTree2 != null) {
                this.pending = new double[]{IntervalsSet.this.getAngle(bSPTree), IntervalsSet.this.getAngle(bSPTree2)};
                this.current = bSPTree2;
                return;
            }
            this.pending = new double[]{IntervalsSet.this.getAngle(bSPTree), Double.POSITIVE_INFINITY};
            this.current = null;
        }

        public boolean hasNext() {
            return this.pending != null;
        }

        public double[] next() {
            double[] dArr = this.pending;
            if (dArr != null) {
                selectPending();
                return dArr;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
