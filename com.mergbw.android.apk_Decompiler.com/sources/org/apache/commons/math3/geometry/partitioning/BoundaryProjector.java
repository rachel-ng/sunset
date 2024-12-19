package org.apache.commons.math3.geometry.partitioning;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.util.FastMath;

class BoundaryProjector<S extends Space, T extends Space> implements BSPTreeVisitor<S> {
    private BSPTree<S> leaf = null;
    private double offset = Double.POSITIVE_INFINITY;
    private final Point<S> original;
    private Point<S> projected = null;

    public BoundaryProjector(Point<S> point) {
        this.original = point;
    }

    public BSPTreeVisitor.Order visitOrder(BSPTree<S> bSPTree) {
        if (bSPTree.getCut().getHyperplane().getOffset(this.original) <= 0.0d) {
            return BSPTreeVisitor.Order.MINUS_SUB_PLUS;
        }
        return BSPTreeVisitor.Order.PLUS_SUB_MINUS;
    }

    public void visitInternalNode(BSPTree<S> bSPTree) {
        Hyperplane<S> hyperplane = bSPTree.getCut().getHyperplane();
        double offset2 = hyperplane.getOffset(this.original);
        if (FastMath.abs(offset2) < this.offset) {
            Point<S> project = hyperplane.project(this.original);
            List<Region<T>> boundaryRegions = boundaryRegions(bSPTree);
            boolean z = false;
            for (Region next : boundaryRegions) {
                if (!z && belongsToPart(project, hyperplane, next)) {
                    this.projected = project;
                    this.offset = FastMath.abs(offset2);
                    z = true;
                }
            }
            if (!z) {
                for (Region<T> singularProjection : boundaryRegions) {
                    Point<S> singularProjection2 = singularProjection(project, hyperplane, singularProjection);
                    if (singularProjection2 != null) {
                        double distance = this.original.distance(singularProjection2);
                        if (distance < this.offset) {
                            this.projected = singularProjection2;
                            this.offset = distance;
                        }
                    }
                }
            }
        }
    }

    public void visitLeafNode(BSPTree<S> bSPTree) {
        if (this.leaf == null) {
            this.leaf = bSPTree;
        }
    }

    public BoundaryProjection<S> getProjection() {
        double copySign = FastMath.copySign(this.offset, ((Boolean) this.leaf.getAttribute()).booleanValue() ? -1.0d : 1.0d);
        this.offset = copySign;
        return new BoundaryProjection<>(this.original, this.projected, copySign);
    }

    private List<Region<T>> boundaryRegions(BSPTree<S> bSPTree) {
        ArrayList arrayList = new ArrayList(2);
        BoundaryAttribute boundaryAttribute = (BoundaryAttribute) bSPTree.getAttribute();
        addRegion(boundaryAttribute.getPlusInside(), arrayList);
        addRegion(boundaryAttribute.getPlusOutside(), arrayList);
        return arrayList;
    }

    private void addRegion(SubHyperplane<S> subHyperplane, List<Region<T>> list) {
        Region remainingRegion;
        if (subHyperplane != null && (remainingRegion = ((AbstractSubHyperplane) subHyperplane).getRemainingRegion()) != null) {
            list.add(remainingRegion);
        }
    }

    private boolean belongsToPart(Point<S> point, Hyperplane<S> hyperplane, Region<T> region) {
        return region.checkPoint(((Embedding) hyperplane).toSubSpace(point)) != Region.Location.OUTSIDE;
    }

    private Point<S> singularProjection(Point<S> point, Hyperplane<S> hyperplane, Region<T> region) {
        Embedding embedding = (Embedding) hyperplane;
        BoundaryProjection<T> projectToBoundary = region.projectToBoundary(embedding.toSubSpace(point));
        if (projectToBoundary.getProjected() == null) {
            return null;
        }
        return embedding.toSpace(projectToBoundary.getProjected());
    }
}
