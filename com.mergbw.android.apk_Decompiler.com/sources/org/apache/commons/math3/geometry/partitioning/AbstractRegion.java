package org.apache.commons.math3.geometry.partitioning;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;

public abstract class AbstractRegion<S extends Space, T extends Space> implements Region<S> {
    private Point<S> barycenter;
    private double size;
    private final double tolerance;
    private BSPTree<S> tree;

    public abstract AbstractRegion<S, T> buildNew(BSPTree<S> bSPTree);

    /* access modifiers changed from: protected */
    public abstract void computeGeometricalProperties();

    protected AbstractRegion(double d) {
        this.tree = new BSPTree<>(Boolean.TRUE);
        this.tolerance = d;
    }

    protected AbstractRegion(BSPTree<S> bSPTree, double d) {
        this.tree = bSPTree;
        this.tolerance = d;
    }

    protected AbstractRegion(Collection<SubHyperplane<S>> collection, double d) {
        this.tolerance = d;
        if (collection.size() == 0) {
            this.tree = new BSPTree<>(Boolean.TRUE);
            return;
        }
        TreeSet treeSet = new TreeSet(new Comparator<SubHyperplane<S>>() {
            public int compare(SubHyperplane<S> subHyperplane, SubHyperplane<S> subHyperplane2) {
                if (subHyperplane2.getSize() < subHyperplane.getSize()) {
                    return -1;
                }
                return subHyperplane == subHyperplane2 ? 0 : 1;
            }
        });
        treeSet.addAll(collection);
        BSPTree<S> bSPTree = new BSPTree<>();
        this.tree = bSPTree;
        insertCuts(bSPTree, treeSet);
        this.tree.visit(new BSPTreeVisitor<S>() {
            public void visitInternalNode(BSPTree<S> bSPTree) {
            }

            public BSPTreeVisitor.Order visitOrder(BSPTree<S> bSPTree) {
                return BSPTreeVisitor.Order.PLUS_SUB_MINUS;
            }

            public void visitLeafNode(BSPTree<S> bSPTree) {
                if (bSPTree.getParent() == null || bSPTree == bSPTree.getParent().getMinus()) {
                    bSPTree.setAttribute(Boolean.TRUE);
                } else {
                    bSPTree.setAttribute(Boolean.FALSE);
                }
            }
        });
    }

    public AbstractRegion(Hyperplane<S>[] hyperplaneArr, double d) {
        this.tolerance = d;
        if (hyperplaneArr == null || hyperplaneArr.length == 0) {
            this.tree = new BSPTree<>(Boolean.FALSE);
            return;
        }
        BSPTree<S> tree2 = hyperplaneArr[0].wholeSpace().getTree(false);
        this.tree = tree2;
        tree2.setAttribute(Boolean.TRUE);
        for (Hyperplane<S> insertCut : hyperplaneArr) {
            if (tree2.insertCut(insertCut)) {
                tree2.setAttribute((Object) null);
                tree2.getPlus().setAttribute(Boolean.FALSE);
                tree2 = tree2.getMinus();
                tree2.setAttribute(Boolean.TRUE);
            }
        }
    }

    public double getTolerance() {
        return this.tolerance;
    }

    private void insertCuts(BSPTree<S> bSPTree, Collection<SubHyperplane<S>> collection) {
        Hyperplane hyperplane;
        Iterator<SubHyperplane<S>> it = collection.iterator();
        loop0:
        while (true) {
            hyperplane = null;
            while (true) {
                if (hyperplane == null && it.hasNext()) {
                    hyperplane = it.next().getHyperplane();
                    if (!bSPTree.insertCut(hyperplane.copySelf())) {
                    }
                }
            }
        }
        if (it.hasNext()) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            while (it.hasNext()) {
                SubHyperplane next = it.next();
                int i = AnonymousClass3.$SwitchMap$org$apache$commons$math3$geometry$partitioning$Side[next.side(hyperplane).ordinal()];
                if (i == 1) {
                    arrayList.add(next);
                } else if (i == 2) {
                    arrayList2.add(next);
                } else if (i == 3) {
                    SubHyperplane.SplitSubHyperplane split = next.split(hyperplane);
                    arrayList.add(split.getPlus());
                    arrayList2.add(split.getMinus());
                }
            }
            insertCuts(bSPTree.getPlus(), arrayList);
            insertCuts(bSPTree.getMinus(), arrayList2);
        }
    }

    /* renamed from: org.apache.commons.math3.geometry.partitioning.AbstractRegion$3  reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$math3$geometry$partitioning$Side;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                org.apache.commons.math3.geometry.partitioning.Side[] r0 = org.apache.commons.math3.geometry.partitioning.Side.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$commons$math3$geometry$partitioning$Side = r0
                org.apache.commons.math3.geometry.partitioning.Side r1 = org.apache.commons.math3.geometry.partitioning.Side.PLUS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$commons$math3$geometry$partitioning$Side     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.commons.math3.geometry.partitioning.Side r1 = org.apache.commons.math3.geometry.partitioning.Side.MINUS     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$commons$math3$geometry$partitioning$Side     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.commons.math3.geometry.partitioning.Side r1 = org.apache.commons.math3.geometry.partitioning.Side.BOTH     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.geometry.partitioning.AbstractRegion.AnonymousClass3.<clinit>():void");
        }
    }

    public AbstractRegion<S, T> copySelf() {
        return buildNew(this.tree.copySelf());
    }

    public boolean isEmpty() {
        return isEmpty(this.tree);
    }

    public boolean isEmpty(BSPTree<S> bSPTree) {
        if (bSPTree.getCut() == null) {
            return !((Boolean) bSPTree.getAttribute()).booleanValue();
        }
        if (!isEmpty(bSPTree.getMinus()) || !isEmpty(bSPTree.getPlus())) {
            return false;
        }
        return true;
    }

    public boolean isFull() {
        return isFull(this.tree);
    }

    public boolean isFull(BSPTree<S> bSPTree) {
        if (bSPTree.getCut() == null) {
            return ((Boolean) bSPTree.getAttribute()).booleanValue();
        }
        return isFull(bSPTree.getMinus()) && isFull(bSPTree.getPlus());
    }

    public boolean contains(Region<S> region) {
        return new RegionFactory().difference(region, this).isEmpty();
    }

    public BoundaryProjection<S> projectToBoundary(Point<S> point) {
        BoundaryProjector boundaryProjector = new BoundaryProjector(point);
        getTree(true).visit(boundaryProjector);
        return boundaryProjector.getProjection();
    }

    public Region.Location checkPoint(Vector<S> vector) {
        return checkPoint(vector);
    }

    public Region.Location checkPoint(Point<S> point) {
        return checkPoint(this.tree, point);
    }

    /* access modifiers changed from: protected */
    public Region.Location checkPoint(BSPTree<S> bSPTree, Vector<S> vector) {
        return checkPoint(bSPTree, vector);
    }

    /* access modifiers changed from: protected */
    public Region.Location checkPoint(BSPTree<S> bSPTree, Point<S> point) {
        BSPTree<S> cell = bSPTree.getCell(point, this.tolerance);
        if (cell.getCut() == null) {
            return ((Boolean) cell.getAttribute()).booleanValue() ? Region.Location.INSIDE : Region.Location.OUTSIDE;
        }
        Region.Location checkPoint = checkPoint(cell.getMinus(), point);
        return checkPoint == checkPoint(cell.getPlus(), point) ? checkPoint : Region.Location.BOUNDARY;
    }

    public BSPTree<S> getTree(boolean z) {
        if (z && this.tree.getCut() != null && this.tree.getAttribute() == null) {
            this.tree.visit(new BoundaryBuilder());
        }
        return this.tree;
    }

    public double getBoundarySize() {
        BoundarySizeVisitor boundarySizeVisitor = new BoundarySizeVisitor();
        getTree(true).visit(boundarySizeVisitor);
        return boundarySizeVisitor.getSize();
    }

    public double getSize() {
        if (this.barycenter == null) {
            computeGeometricalProperties();
        }
        return this.size;
    }

    /* access modifiers changed from: protected */
    public void setSize(double d) {
        this.size = d;
    }

    public Point<S> getBarycenter() {
        if (this.barycenter == null) {
            computeGeometricalProperties();
        }
        return this.barycenter;
    }

    /* access modifiers changed from: protected */
    public void setBarycenter(Vector<S> vector) {
        setBarycenter(vector);
    }

    /* access modifiers changed from: protected */
    public void setBarycenter(Point<S> point) {
        this.barycenter = point;
    }

    public Side side(Hyperplane<S> hyperplane) {
        InsideFinder insideFinder = new InsideFinder(this);
        insideFinder.recurseSides(this.tree, hyperplane.wholeHyperplane());
        return insideFinder.plusFound() ? insideFinder.minusFound() ? Side.BOTH : Side.PLUS : insideFinder.minusFound() ? Side.MINUS : Side.HYPER;
    }

    public SubHyperplane<S> intersection(SubHyperplane<S> subHyperplane) {
        return recurseIntersection(this.tree, subHyperplane);
    }

    private SubHyperplane<S> recurseIntersection(BSPTree<S> bSPTree, SubHyperplane<S> subHyperplane) {
        if (bSPTree.getCut() != null) {
            Hyperplane<S> hyperplane = bSPTree.getCut().getHyperplane();
            int i = AnonymousClass3.$SwitchMap$org$apache$commons$math3$geometry$partitioning$Side[subHyperplane.side(hyperplane).ordinal()];
            if (i == 1) {
                return recurseIntersection(bSPTree.getPlus(), subHyperplane);
            }
            if (i == 2) {
                return recurseIntersection(bSPTree.getMinus(), subHyperplane);
            }
            if (i != 3) {
                return recurseIntersection(bSPTree.getPlus(), recurseIntersection(bSPTree.getMinus(), subHyperplane));
            }
            SubHyperplane.SplitSubHyperplane<S> split = subHyperplane.split(hyperplane);
            SubHyperplane<S> recurseIntersection = recurseIntersection(bSPTree.getPlus(), split.getPlus());
            SubHyperplane<S> recurseIntersection2 = recurseIntersection(bSPTree.getMinus(), split.getMinus());
            if (recurseIntersection == null) {
                return recurseIntersection2;
            }
            if (recurseIntersection2 == null) {
                return recurseIntersection;
            }
            return recurseIntersection.reunite(recurseIntersection2);
        } else if (((Boolean) bSPTree.getAttribute()).booleanValue()) {
            return subHyperplane.copySelf();
        } else {
            return null;
        }
    }

    public AbstractRegion<S, T> applyTransform(Transform<S, T> transform) {
        BoundaryAttribute boundaryAttribute;
        HashMap hashMap = new HashMap();
        BSPTree<S> recurseTransform = recurseTransform(getTree(false), transform, hashMap);
        for (Map.Entry entry : hashMap.entrySet()) {
            if (!(((BSPTree) entry.getKey()).getCut() == null || (boundaryAttribute = (BoundaryAttribute) ((BSPTree) entry.getKey()).getAttribute()) == null)) {
                BoundaryAttribute boundaryAttribute2 = (BoundaryAttribute) ((BSPTree) entry.getValue()).getAttribute();
                Iterator it = boundaryAttribute.getSplitters().iterator();
                while (it.hasNext()) {
                    boundaryAttribute2.getSplitters().add((BSPTree) hashMap.get((BSPTree) it.next()));
                }
            }
        }
        return buildNew(recurseTransform);
    }

    private BSPTree<S> recurseTransform(BSPTree<S> bSPTree, Transform<S, T> transform, Map<BSPTree<S>, BSPTree<S>> map) {
        BSPTree<S> bSPTree2;
        if (bSPTree.getCut() == null) {
            bSPTree2 = new BSPTree<>(bSPTree.getAttribute());
        } else {
            AbstractSubHyperplane<S, T> applyTransform = ((AbstractSubHyperplane) bSPTree.getCut()).applyTransform(transform);
            BoundaryAttribute boundaryAttribute = (BoundaryAttribute) bSPTree.getAttribute();
            if (boundaryAttribute != null) {
                AbstractSubHyperplane<S, T> abstractSubHyperplane = null;
                AbstractSubHyperplane<S, T> applyTransform2 = boundaryAttribute.getPlusOutside() == null ? null : ((AbstractSubHyperplane) boundaryAttribute.getPlusOutside()).applyTransform(transform);
                if (boundaryAttribute.getPlusInside() != null) {
                    abstractSubHyperplane = ((AbstractSubHyperplane) boundaryAttribute.getPlusInside()).applyTransform(transform);
                }
                boundaryAttribute = new BoundaryAttribute(applyTransform2, abstractSubHyperplane, new NodesSet());
            }
            bSPTree2 = new BSPTree<>(applyTransform, recurseTransform(bSPTree.getPlus(), transform, map), recurseTransform(bSPTree.getMinus(), transform, map), boundaryAttribute);
        }
        map.put(bSPTree, bSPTree2);
        return bSPTree2;
    }
}
