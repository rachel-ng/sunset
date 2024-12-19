package org.apache.commons.math3.geometry.spherical.twod;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor;
import org.apache.commons.math3.geometry.partitioning.BoundaryAttribute;
import org.apache.commons.math3.geometry.spherical.oned.Arc;
import org.apache.commons.math3.geometry.spherical.oned.ArcsSet;
import org.apache.commons.math3.geometry.spherical.oned.S1Point;

class EdgesBuilder implements BSPTreeVisitor<Sphere2D> {
    private final Map<Edge, BSPTree<Sphere2D>> edgeToNode = new IdentityHashMap();
    private final Map<BSPTree<Sphere2D>, List<Edge>> nodeToEdgesList = new IdentityHashMap();
    private final BSPTree<Sphere2D> root;
    private final double tolerance;

    public void visitLeafNode(BSPTree<Sphere2D> bSPTree) {
    }

    public EdgesBuilder(BSPTree<Sphere2D> bSPTree, double d) {
        this.root = bSPTree;
        this.tolerance = d;
    }

    public BSPTreeVisitor.Order visitOrder(BSPTree<Sphere2D> bSPTree) {
        return BSPTreeVisitor.Order.MINUS_SUB_PLUS;
    }

    public void visitInternalNode(BSPTree<Sphere2D> bSPTree) {
        this.nodeToEdgesList.put(bSPTree, new ArrayList());
        BoundaryAttribute boundaryAttribute = (BoundaryAttribute) bSPTree.getAttribute();
        if (boundaryAttribute.getPlusOutside() != null) {
            addContribution((SubCircle) boundaryAttribute.getPlusOutside(), false, bSPTree);
        }
        if (boundaryAttribute.getPlusInside() != null) {
            addContribution((SubCircle) boundaryAttribute.getPlusInside(), true, bSPTree);
        }
    }

    private void addContribution(SubCircle subCircle, boolean z, BSPTree<Sphere2D> bSPTree) {
        Edge edge;
        Circle circle = (Circle) subCircle.getHyperplane();
        for (Arc next : ((ArcsSet) subCircle.getRemainingRegion()).asList()) {
            Vertex vertex = new Vertex(circle.toSpace((Point) new S1Point(next.getInf())));
            Vertex vertex2 = new Vertex(circle.toSpace((Point) new S1Point(next.getSup())));
            vertex.bindWith(circle);
            vertex2.bindWith(circle);
            if (z) {
                edge = new Edge(vertex2, vertex, next.getSize(), circle.getReverse());
            } else {
                edge = new Edge(vertex, vertex2, next.getSize(), circle);
            }
            this.edgeToNode.put(edge, bSPTree);
            this.nodeToEdgesList.get(bSPTree).add(edge);
        }
    }

    private Edge getFollowingEdge(Edge edge) throws MathIllegalStateException {
        S2Point location = edge.getEnd().getLocation();
        List<BSPTree<Sphere2D>> closeCuts = this.root.getCloseCuts(location, this.tolerance);
        double d = this.tolerance;
        Edge edge2 = null;
        for (BSPTree<Sphere2D> bSPTree : closeCuts) {
            for (Edge edge3 : this.nodeToEdgesList.get(bSPTree)) {
                if (edge3 != edge && edge3.getStart().getIncoming() == null) {
                    double angle = Vector3D.angle(location.getVector(), edge3.getStart().getLocation().getVector());
                    if (angle <= d) {
                        edge2 = edge3;
                        d = angle;
                    }
                }
            }
        }
        if (edge2 != null) {
            return edge2;
        }
        if (Vector3D.angle(location.getVector(), edge.getStart().getLocation().getVector()) <= this.tolerance) {
            return edge;
        }
        throw new MathIllegalStateException(LocalizedFormats.OUTLINE_BOUNDARY_LOOP_OPEN, new Object[0]);
    }

    public List<Edge> getEdges() throws MathIllegalStateException {
        for (Edge next : this.edgeToNode.keySet()) {
            next.setNextEdge(getFollowingEdge(next));
        }
        return new ArrayList(this.edgeToNode.keySet());
    }
}
