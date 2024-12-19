package org.apache.commons.math3.geometry.spherical.oned;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.partitioning.AbstractRegion;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.BoundaryProjection;
import org.apache.commons.math3.geometry.partitioning.Side;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;

public class ArcsSet extends AbstractRegion<Sphere1D, Sphere1D> implements Iterable<double[]> {
    public ArcsSet(double d) {
        super(d);
    }

    public ArcsSet(double d, double d2, double d3) throws NumberIsTooLargeException {
        super(buildTree(d, d2, d3), d3);
    }

    public ArcsSet(BSPTree<Sphere1D> bSPTree, double d) throws InconsistentStateAt2PiWrapping {
        super(bSPTree, d);
        check2PiConsistency();
    }

    public ArcsSet(Collection<SubHyperplane<Sphere1D>> collection, double d) throws InconsistentStateAt2PiWrapping {
        super(collection, d);
        check2PiConsistency();
    }

    private static BSPTree<Sphere1D> buildTree(double d, double d2, double d3) throws NumberIsTooLargeException {
        if (!Precision.equals(d, d2, 0)) {
            double d4 = d2 - d;
            if (d4 < 6.283185307179586d) {
                if (d <= d2) {
                    double normalizeAngle = MathUtils.normalizeAngle(d, 3.141592653589793d);
                    double d5 = d4 + normalizeAngle;
                    SubLimitAngle wholeHyperplane = new LimitAngle(new S1Point(normalizeAngle), false, d3).wholeHyperplane();
                    if (d5 > 6.283185307179586d) {
                        return new BSPTree<>(wholeHyperplane, new BSPTree(new LimitAngle(new S1Point(d5 - 6.283185307179586d), true, d3).wholeHyperplane(), new BSPTree(Boolean.FALSE), new BSPTree(Boolean.TRUE), (Object) null), new BSPTree(Boolean.TRUE), (Object) null);
                    }
                    return new BSPTree<>(wholeHyperplane, new BSPTree(Boolean.FALSE), new BSPTree(new LimitAngle(new S1Point(d5), true, d3).wholeHyperplane(), new BSPTree(Boolean.FALSE), new BSPTree(Boolean.TRUE), (Object) null), (Object) null);
                }
                throw new NumberIsTooLargeException(LocalizedFormats.ENDPOINTS_NOT_AN_INTERVAL, Double.valueOf(d), Double.valueOf(d2), true);
            }
        }
        return new BSPTree<>(Boolean.TRUE);
    }

    private void check2PiConsistency() throws InconsistentStateAt2PiWrapping {
        BSPTree tree = getTree(false);
        if (tree.getCut() != null) {
            if (((Boolean) getLastLeaf(tree).getAttribute()).booleanValue() ^ ((Boolean) getFirstLeaf(tree).getAttribute()).booleanValue()) {
                throw new InconsistentStateAt2PiWrapping();
            }
        }
    }

    /* access modifiers changed from: private */
    public BSPTree<Sphere1D> getFirstLeaf(BSPTree<Sphere1D> bSPTree) {
        if (bSPTree.getCut() == null) {
            return bSPTree;
        }
        BSPTree<Sphere1D> bSPTree2 = null;
        while (bSPTree != null) {
            bSPTree2 = bSPTree;
            bSPTree = previousInternalNode(bSPTree);
        }
        return leafBefore(bSPTree2);
    }

    private BSPTree<Sphere1D> getLastLeaf(BSPTree<Sphere1D> bSPTree) {
        if (bSPTree.getCut() == null) {
            return bSPTree;
        }
        BSPTree<Sphere1D> bSPTree2 = null;
        while (bSPTree != null) {
            bSPTree2 = bSPTree;
            bSPTree = nextInternalNode(bSPTree);
        }
        return leafAfter(bSPTree2);
    }

    /* access modifiers changed from: private */
    public BSPTree<Sphere1D> getFirstArcStart() {
        BSPTree tree = getTree(false);
        if (tree.getCut() == null) {
            return null;
        }
        BSPTree<Sphere1D> parent = getFirstLeaf(tree).getParent();
        while (parent != null && !isArcStart(parent)) {
            parent = nextInternalNode(parent);
        }
        return parent;
    }

    /* access modifiers changed from: private */
    public boolean isArcStart(BSPTree<Sphere1D> bSPTree) {
        if (!((Boolean) leafBefore(bSPTree).getAttribute()).booleanValue() && ((Boolean) leafAfter(bSPTree).getAttribute()).booleanValue()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public boolean isArcEnd(BSPTree<Sphere1D> bSPTree) {
        if (((Boolean) leafBefore(bSPTree).getAttribute()).booleanValue() && !((Boolean) leafAfter(bSPTree).getAttribute()).booleanValue()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public BSPTree<Sphere1D> nextInternalNode(BSPTree<Sphere1D> bSPTree) {
        if (childAfter(bSPTree).getCut() != null) {
            return leafAfter(bSPTree).getParent();
        }
        while (isAfterParent(bSPTree)) {
            bSPTree = bSPTree.getParent();
        }
        return bSPTree.getParent();
    }

    /* access modifiers changed from: private */
    public BSPTree<Sphere1D> previousInternalNode(BSPTree<Sphere1D> bSPTree) {
        if (childBefore(bSPTree).getCut() != null) {
            return leafBefore(bSPTree).getParent();
        }
        while (isBeforeParent(bSPTree)) {
            bSPTree = bSPTree.getParent();
        }
        return bSPTree.getParent();
    }

    private BSPTree<Sphere1D> leafBefore(BSPTree<Sphere1D> bSPTree) {
        BSPTree<Sphere1D> childBefore = childBefore(bSPTree);
        while (childBefore.getCut() != null) {
            childBefore = childAfter(childBefore);
        }
        return childBefore;
    }

    private BSPTree<Sphere1D> leafAfter(BSPTree<Sphere1D> bSPTree) {
        BSPTree<Sphere1D> childAfter = childAfter(bSPTree);
        while (childAfter.getCut() != null) {
            childAfter = childBefore(childAfter);
        }
        return childAfter;
    }

    private boolean isBeforeParent(BSPTree<Sphere1D> bSPTree) {
        BSPTree<Sphere1D> parent = bSPTree.getParent();
        if (parent != null && bSPTree == childBefore(parent)) {
            return true;
        }
        return false;
    }

    private boolean isAfterParent(BSPTree<Sphere1D> bSPTree) {
        BSPTree<Sphere1D> parent = bSPTree.getParent();
        if (parent != null && bSPTree == childAfter(parent)) {
            return true;
        }
        return false;
    }

    private BSPTree<Sphere1D> childBefore(BSPTree<Sphere1D> bSPTree) {
        if (isDirect(bSPTree)) {
            return bSPTree.getMinus();
        }
        return bSPTree.getPlus();
    }

    private BSPTree<Sphere1D> childAfter(BSPTree<Sphere1D> bSPTree) {
        if (isDirect(bSPTree)) {
            return bSPTree.getPlus();
        }
        return bSPTree.getMinus();
    }

    private boolean isDirect(BSPTree<Sphere1D> bSPTree) {
        return ((LimitAngle) bSPTree.getCut().getHyperplane()).isDirect();
    }

    /* access modifiers changed from: private */
    public double getAngle(BSPTree<Sphere1D> bSPTree) {
        return ((LimitAngle) bSPTree.getCut().getHyperplane()).getLocation().getAlpha();
    }

    public ArcsSet buildNew(BSPTree<Sphere1D> bSPTree) {
        return new ArcsSet(bSPTree, getTolerance());
    }

    /* access modifiers changed from: protected */
    public void computeGeometricalProperties() {
        double d = 6.283185307179586d;
        double d2 = 0.0d;
        if (getTree(false).getCut() == null) {
            setBarycenter(S1Point.NaN);
            if (!((Boolean) getTree(false).getAttribute()).booleanValue()) {
                d = 0.0d;
            }
            setSize(d);
            return;
        }
        Iterator<double[]> it = iterator();
        double d3 = 0.0d;
        while (it.hasNext()) {
            double[] next = it.next();
            double d4 = next[1];
            double d5 = next[0];
            double d6 = d4 - d5;
            d2 += d6;
            d3 += d6 * (d5 + d4);
        }
        setSize(d2);
        if (Precision.equals(d2, 6.283185307179586d, 0)) {
            setBarycenter(S1Point.NaN);
        } else if (d2 >= Precision.SAFE_MIN) {
            setBarycenter(new S1Point(d3 / (d2 * 2.0d)));
        } else {
            setBarycenter(((LimitAngle) getTree(false).getCut().getHyperplane()).getLocation());
        }
    }

    public BoundaryProjection<Sphere1D> projectToBoundary(Point<Sphere1D> point) {
        double alpha = ((S1Point) point).getAlpha();
        Iterator<double[]> it = iterator();
        double d = Double.NaN;
        boolean z = false;
        double d2 = Double.NaN;
        while (it.hasNext()) {
            double[] next = it.next();
            if (Double.isNaN(d2)) {
                d2 = next[0];
            }
            if (!z) {
                double d3 = next[0];
                if (alpha >= d3) {
                    double d4 = next[1];
                    if (alpha <= d4) {
                        double d5 = d3 - alpha;
                        double d6 = alpha - d4;
                        if (d5 < d6) {
                            return new BoundaryProjection<>(point, new S1Point(next[1]), d6);
                        }
                        return new BoundaryProjection<>(point, new S1Point(next[0]), d5);
                    }
                } else if (Double.isNaN(d)) {
                    z = true;
                } else {
                    double d7 = alpha - d;
                    double d8 = next[0] - alpha;
                    if (d7 < d8) {
                        return new BoundaryProjection<>(point, new S1Point(d), d7);
                    }
                    return new BoundaryProjection<>(point, new S1Point(next[0]), d8);
                }
            }
            d = next[1];
        }
        if (Double.isNaN(d)) {
            return new BoundaryProjection<>(point, (Point<Sphere1D>) null, 6.283185307179586d);
        }
        if (z) {
            double d9 = alpha - (d - 6.283185307179586d);
            double d10 = d2 - alpha;
            if (d9 < d10) {
                return new BoundaryProjection<>(point, new S1Point(d), d9);
            }
            return new BoundaryProjection<>(point, new S1Point(d2), d10);
        }
        double d11 = alpha - d;
        double d12 = (6.283185307179586d + d2) - alpha;
        if (d11 < d12) {
            return new BoundaryProjection<>(point, new S1Point(d), d11);
        }
        return new BoundaryProjection<>(point, new S1Point(d2), d12);
    }

    public List<Arc> asList() {
        ArrayList arrayList = new ArrayList();
        Iterator<double[]> it = iterator();
        while (it.hasNext()) {
            double[] next = it.next();
            arrayList.add(new Arc(next[0], next[1], getTolerance()));
        }
        return arrayList;
    }

    public Iterator<double[]> iterator() {
        return new SubArcsIterator();
    }

    private class SubArcsIterator implements Iterator<double[]> {
        private BSPTree<Sphere1D> current;
        private final BSPTree<Sphere1D> firstStart;
        private double[] pending;

        public SubArcsIterator() {
            BSPTree<Sphere1D> access$000 = ArcsSet.this.getFirstArcStart();
            this.firstStart = access$000;
            this.current = access$000;
            if (access$000 != null) {
                selectPending();
            } else if (((Boolean) ArcsSet.this.getFirstLeaf(ArcsSet.this.getTree(false)).getAttribute()).booleanValue()) {
                this.pending = new double[]{0.0d, 6.283185307179586d};
            } else {
                this.pending = null;
            }
        }

        private void selectPending() {
            BSPTree<Sphere1D> bSPTree = this.current;
            while (bSPTree != null && !ArcsSet.this.isArcStart(bSPTree)) {
                bSPTree = ArcsSet.this.nextInternalNode(bSPTree);
            }
            if (bSPTree == null) {
                this.current = null;
                this.pending = null;
                return;
            }
            BSPTree<Sphere1D> bSPTree2 = bSPTree;
            while (bSPTree2 != null && !ArcsSet.this.isArcEnd(bSPTree2)) {
                bSPTree2 = ArcsSet.this.nextInternalNode(bSPTree2);
            }
            if (bSPTree2 != null) {
                this.pending = new double[]{ArcsSet.this.getAngle(bSPTree), ArcsSet.this.getAngle(bSPTree2)};
                this.current = bSPTree2;
                return;
            }
            BSPTree<Sphere1D> bSPTree3 = this.firstStart;
            while (bSPTree3 != null && !ArcsSet.this.isArcEnd(bSPTree3)) {
                bSPTree3 = ArcsSet.this.previousInternalNode(bSPTree3);
            }
            if (bSPTree3 != null) {
                this.pending = new double[]{ArcsSet.this.getAngle(bSPTree), ArcsSet.this.getAngle(bSPTree3) + 6.283185307179586d};
                this.current = null;
                return;
            }
            throw new MathInternalError();
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

    public Side side(Arc arc) {
        double inf = arc.getInf() + 3.141592653589793d;
        double sup = arc.getSup() - arc.getInf();
        Iterator<double[]> it = iterator();
        boolean z = false;
        boolean z2 = false;
        while (it.hasNext()) {
            double[] next = it.next();
            double normalizeAngle = MathUtils.normalizeAngle(next[0], inf) - arc.getInf();
            double d = next[1] - (next[0] - normalizeAngle);
            if (normalizeAngle <= sup - getTolerance() || d >= getTolerance() + 6.283185307179586d) {
                z = true;
            }
            if (d >= getTolerance() + sup) {
                z2 = true;
            }
        }
        if (z) {
            if (z2) {
                return Side.BOTH;
            }
            return Side.MINUS;
        } else if (z2) {
            return Side.PLUS;
        } else {
            return Side.HYPER;
        }
    }

    public Split split(Arc arc) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        double inf = arc.getInf() + 3.141592653589793d;
        double sup = arc.getSup() - arc.getInf();
        Iterator<double[]> it = iterator();
        while (it.hasNext()) {
            double[] next = it.next();
            double normalizeAngle = MathUtils.normalizeAngle(next[0], inf) - arc.getInf();
            double d = next[0];
            double d2 = d - normalizeAngle;
            double d3 = next[1] - d2;
            if (normalizeAngle < sup) {
                arrayList.add(Double.valueOf(d));
                if (d3 > sup) {
                    double d4 = sup + d2;
                    arrayList.add(Double.valueOf(d4));
                    arrayList2.add(Double.valueOf(d4));
                    if (d3 > 6.283185307179586d) {
                        double d5 = d2 + 6.283185307179586d;
                        arrayList2.add(Double.valueOf(d5));
                        arrayList.add(Double.valueOf(d5));
                        arrayList.add(Double.valueOf(next[1]));
                    } else {
                        arrayList2.add(Double.valueOf(next[1]));
                    }
                } else {
                    arrayList.add(Double.valueOf(next[1]));
                }
            } else {
                arrayList2.add(Double.valueOf(d));
                if (d3 > 6.283185307179586d) {
                    double d6 = d2 + 6.283185307179586d;
                    arrayList2.add(Double.valueOf(d6));
                    arrayList.add(Double.valueOf(d6));
                    double d7 = sup + 6.283185307179586d;
                    if (d3 > d7) {
                        double d8 = d7 + d2;
                        arrayList.add(Double.valueOf(d8));
                        arrayList2.add(Double.valueOf(d8));
                        arrayList2.add(Double.valueOf(next[1]));
                    } else {
                        arrayList.add(Double.valueOf(next[1]));
                    }
                } else {
                    arrayList2.add(Double.valueOf(next[1]));
                }
            }
        }
        return new Split(createSplitPart(arrayList));
    }

    private void addArcLimit(BSPTree<Sphere1D> bSPTree, double d, boolean z) {
        LimitAngle limitAngle = new LimitAngle(new S1Point(d), !z, getTolerance());
        BSPTree<Sphere1D> cell = bSPTree.getCell(limitAngle.getLocation(), getTolerance());
        if (cell.getCut() == null) {
            cell.insertCut(limitAngle);
            cell.setAttribute((Object) null);
            cell.getPlus().setAttribute(Boolean.FALSE);
            cell.getMinus().setAttribute(Boolean.TRUE);
            return;
        }
        throw new MathInternalError();
    }

    private ArcsSet createSplitPart(List<Double> list) {
        if (list.isEmpty()) {
            return null;
        }
        int i = 0;
        while (i < list.size()) {
            int size = (i + 1) % list.size();
            double doubleValue = list.get(i).doubleValue();
            if (FastMath.abs(MathUtils.normalizeAngle(list.get(size).doubleValue(), doubleValue) - doubleValue) <= getTolerance()) {
                if (size > 0) {
                    list.remove(size);
                    list.remove(i);
                    i--;
                } else {
                    double doubleValue2 = list.remove(list.size() - 1).doubleValue();
                    double doubleValue3 = list.remove(0).doubleValue();
                    if (!list.isEmpty()) {
                        list.add(Double.valueOf(list.remove(0).doubleValue() + 6.283185307179586d));
                    } else if (doubleValue2 - doubleValue3 > 3.141592653589793d) {
                        return new ArcsSet((BSPTree<Sphere1D>) new BSPTree(Boolean.TRUE), getTolerance());
                    } else {
                        return null;
                    }
                }
            }
            i++;
        }
        BSPTree bSPTree = new BSPTree(Boolean.FALSE);
        for (int i2 = 0; i2 < list.size() - 1; i2 += 2) {
            addArcLimit(bSPTree, list.get(i2).doubleValue(), true);
            addArcLimit(bSPTree, list.get(i2 + 1).doubleValue(), false);
        }
        if (bSPTree.getCut() == null) {
            return null;
        }
        return new ArcsSet((BSPTree<Sphere1D>) bSPTree, getTolerance());
    }

    public static class Split {
        private final ArcsSet minus;
        private final ArcsSet plus;

        private Split(ArcsSet arcsSet, ArcsSet arcsSet2) {
            this.plus = arcsSet;
            this.minus = arcsSet2;
        }

        public ArcsSet getPlus() {
            return this.plus;
        }

        public ArcsSet getMinus() {
            return this.minus;
        }
    }

    public static class InconsistentStateAt2PiWrapping extends MathIllegalArgumentException {
        private static final long serialVersionUID = 20140107;

        public InconsistentStateAt2PiWrapping() {
            super(LocalizedFormats.INCONSISTENT_STATE_AT_2_PI_WRAPPING, new Object[0]);
        }
    }
}
