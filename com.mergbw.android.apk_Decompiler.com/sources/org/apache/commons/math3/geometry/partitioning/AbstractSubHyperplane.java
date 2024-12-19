package org.apache.commons.math3.geometry.partitioning;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;

public abstract class AbstractSubHyperplane<S extends Space, T extends Space> implements SubHyperplane<S> {
    private final Hyperplane<S> hyperplane;
    private final Region<T> remainingRegion;

    /* access modifiers changed from: protected */
    public abstract AbstractSubHyperplane<S, T> buildNew(Hyperplane<S> hyperplane2, Region<T> region);

    public abstract Side side(Hyperplane<S> hyperplane2);

    public abstract SubHyperplane.SplitSubHyperplane<S> split(Hyperplane<S> hyperplane2);

    protected AbstractSubHyperplane(Hyperplane<S> hyperplane2, Region<T> region) {
        this.hyperplane = hyperplane2;
        this.remainingRegion = region;
    }

    public AbstractSubHyperplane<S, T> copySelf() {
        return buildNew(this.hyperplane.copySelf(), this.remainingRegion);
    }

    public Hyperplane<S> getHyperplane() {
        return this.hyperplane;
    }

    public Region<T> getRemainingRegion() {
        return this.remainingRegion;
    }

    public double getSize() {
        return this.remainingRegion.getSize();
    }

    public AbstractSubHyperplane<S, T> reunite(SubHyperplane<S> subHyperplane) {
        return buildNew(this.hyperplane, new RegionFactory().union(this.remainingRegion, ((AbstractSubHyperplane) subHyperplane).remainingRegion));
    }

    public AbstractSubHyperplane<S, T> applyTransform(Transform<S, T> transform) {
        BoundaryAttribute boundaryAttribute;
        Hyperplane<S> apply = transform.apply(this.hyperplane);
        HashMap hashMap = new HashMap();
        BSPTree<T> recurseTransform = recurseTransform(this.remainingRegion.getTree(false), apply, transform, hashMap);
        for (Map.Entry entry : hashMap.entrySet()) {
            if (!(((BSPTree) entry.getKey()).getCut() == null || (boundaryAttribute = (BoundaryAttribute) ((BSPTree) entry.getKey()).getAttribute()) == null)) {
                BoundaryAttribute boundaryAttribute2 = (BoundaryAttribute) ((BSPTree) entry.getValue()).getAttribute();
                Iterator it = boundaryAttribute.getSplitters().iterator();
                while (it.hasNext()) {
                    boundaryAttribute2.getSplitters().add((BSPTree) hashMap.get((BSPTree) it.next()));
                }
            }
        }
        return buildNew(apply, this.remainingRegion.buildNew(recurseTransform));
    }

    private BSPTree<T> recurseTransform(BSPTree<T> bSPTree, Hyperplane<S> hyperplane2, Transform<S, T> transform, Map<BSPTree<T>, BSPTree<T>> map) {
        BSPTree<T> bSPTree2;
        if (bSPTree.getCut() == null) {
            bSPTree2 = new BSPTree<>(bSPTree.getAttribute());
        } else {
            BoundaryAttribute boundaryAttribute = (BoundaryAttribute) bSPTree.getAttribute();
            if (boundaryAttribute != null) {
                SubHyperplane<T> subHyperplane = null;
                SubHyperplane<T> apply = boundaryAttribute.getPlusOutside() == null ? null : transform.apply(boundaryAttribute.getPlusOutside(), this.hyperplane, hyperplane2);
                if (boundaryAttribute.getPlusInside() != null) {
                    subHyperplane = transform.apply(boundaryAttribute.getPlusInside(), this.hyperplane, hyperplane2);
                }
                boundaryAttribute = new BoundaryAttribute(apply, subHyperplane, new NodesSet());
            }
            bSPTree2 = new BSPTree<>(transform.apply(bSPTree.getCut(), this.hyperplane, hyperplane2), recurseTransform(bSPTree.getPlus(), hyperplane2, transform, map), recurseTransform(bSPTree.getMinus(), hyperplane2, transform, map), boundaryAttribute);
        }
        map.put(bSPTree, bSPTree2);
        return bSPTree2;
    }

    public boolean isEmpty() {
        return this.remainingRegion.isEmpty();
    }
}
