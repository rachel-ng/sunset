package org.apache.commons.math3.geometry.spherical.twod;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.enclosing.EnclosingBall;
import org.apache.commons.math3.geometry.enclosing.WelzlEncloser;
import org.apache.commons.math3.geometry.euclidean.threed.Rotation;
import org.apache.commons.math3.geometry.euclidean.threed.SphereGenerator;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.partitioning.AbstractRegion;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.BoundaryProjection;
import org.apache.commons.math3.geometry.partitioning.RegionFactory;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.geometry.spherical.oned.Sphere1D;
import org.apache.commons.math3.util.FastMath;

public class SphericalPolygonsSet extends AbstractRegion<Sphere2D, Sphere1D> {
    private List<Vertex> loops;

    public SphericalPolygonsSet(double d) {
        super(d);
    }

    public SphericalPolygonsSet(Vector3D vector3D, double d) {
        super(new BSPTree(new Circle(vector3D, d).wholeHyperplane(), new BSPTree(Boolean.FALSE), new BSPTree(Boolean.TRUE), (Object) null), d);
    }

    public SphericalPolygonsSet(Vector3D vector3D, Vector3D vector3D2, double d, int i, double d2) {
        this(d2, createRegularPolygonVertices(vector3D, vector3D2, d, i));
    }

    public SphericalPolygonsSet(BSPTree<Sphere2D> bSPTree, double d) {
        super(bSPTree, d);
    }

    public SphericalPolygonsSet(Collection<SubHyperplane<Sphere2D>> collection, double d) {
        super(collection, d);
    }

    public SphericalPolygonsSet(double d, S2Point... s2PointArr) {
        super(verticesToTree(d, s2PointArr), d);
    }

    private static S2Point[] createRegularPolygonVertices(Vector3D vector3D, Vector3D vector3D2, double d, int i) {
        S2Point[] s2PointArr = new S2Point[i];
        s2PointArr[0] = new S2Point(new Rotation(Vector3D.crossProduct(vector3D, vector3D2), d).applyTo(vector3D));
        Rotation rotation = new Rotation(vector3D, 6.283185307179586d / ((double) i));
        for (int i2 = 1; i2 < i; i2++) {
            s2PointArr[i2] = new S2Point(rotation.applyTo(s2PointArr[i2 - 1].getVector()));
        }
        return s2PointArr;
    }

    private static BSPTree<Sphere2D> verticesToTree(double d, S2Point... s2PointArr) {
        double d2 = d;
        S2Point[] s2PointArr2 = s2PointArr;
        int length = s2PointArr2.length;
        if (length == 0) {
            return new BSPTree<>(Boolean.TRUE);
        }
        Vertex[] vertexArr = new Vertex[length];
        for (int i = 0; i < length; i++) {
            vertexArr[i] = new Vertex(s2PointArr2[i]);
        }
        ArrayList arrayList = new ArrayList(length);
        Vertex vertex = vertexArr[length - 1];
        int i2 = 0;
        while (i2 < length) {
            Vertex vertex2 = vertexArr[i2];
            Circle sharedCircleWith = vertex.sharedCircleWith(vertex2);
            if (sharedCircleWith == null) {
                sharedCircleWith = new Circle(vertex.getLocation(), vertex2.getLocation(), d2);
            }
            Circle circle = sharedCircleWith;
            Edge edge = r7;
            Edge edge2 = new Edge(vertex, vertex2, Vector3D.angle(vertex.getLocation().getVector(), vertex2.getLocation().getVector()), circle);
            arrayList.add(edge);
            for (int i3 = 0; i3 < length; i3++) {
                Vertex vertex3 = vertexArr[i3];
                if (!(vertex3 == vertex || vertex3 == vertex2 || FastMath.abs(circle.getOffset((Point<Sphere2D>) vertex3.getLocation())) > d2)) {
                    vertex3.bindWith(circle);
                }
            }
            i2++;
            vertex = vertex2;
        }
        BSPTree<Sphere2D> bSPTree = new BSPTree<>();
        insertEdges(d2, bSPTree, arrayList);
        return bSPTree;
    }

    private static void insertEdges(double d, BSPTree<Sphere2D> bSPTree, List<Edge> list) {
        Edge edge;
        int i = 0;
        loop0:
        while (true) {
            edge = null;
            while (edge == null && i < list.size()) {
                int i2 = i + 1;
                Edge edge2 = list.get(i);
                if (!bSPTree.insertCut(edge2.getCircle())) {
                    i = i2;
                } else {
                    int i3 = i2;
                    edge = edge2;
                    i = i3;
                }
            }
        }
        if (edge == null) {
            BSPTree<Sphere2D> parent = bSPTree.getParent();
            if (parent == null || bSPTree == parent.getMinus()) {
                bSPTree.setAttribute(Boolean.TRUE);
            } else {
                bSPTree.setAttribute(Boolean.FALSE);
            }
        } else {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (Edge next : list) {
                if (next != edge) {
                    next.split(edge.getCircle(), arrayList, arrayList2);
                }
            }
            if (!arrayList.isEmpty()) {
                insertEdges(d, bSPTree.getPlus(), arrayList);
            } else {
                bSPTree.getPlus().setAttribute(Boolean.FALSE);
            }
            if (!arrayList2.isEmpty()) {
                insertEdges(d, bSPTree.getMinus(), arrayList2);
            } else {
                bSPTree.getMinus().setAttribute(Boolean.TRUE);
            }
        }
    }

    public SphericalPolygonsSet buildNew(BSPTree<Sphere2D> bSPTree) {
        return new SphericalPolygonsSet(bSPTree, getTolerance());
    }

    /* access modifiers changed from: protected */
    public void computeGeometricalProperties() throws MathIllegalStateException {
        BSPTree tree = getTree(true);
        if (tree.getCut() != null) {
            PropertiesComputer propertiesComputer = new PropertiesComputer(getTolerance());
            tree.visit(propertiesComputer);
            setSize(propertiesComputer.getArea());
            setBarycenter(propertiesComputer.getBarycenter());
        } else if (tree.getCut() != null || !((Boolean) tree.getAttribute()).booleanValue()) {
            setSize(0.0d);
            setBarycenter(S2Point.NaN);
        } else {
            setSize(12.566370614359172d);
            setBarycenter(new S2Point(0.0d, 0.0d));
        }
    }

    public List<Vertex> getBoundaryLoops() throws MathIllegalStateException {
        if (this.loops == null) {
            if (getTree(false).getCut() == null) {
                this.loops = Collections.emptyList();
            } else {
                BSPTree tree = getTree(true);
                EdgesBuilder edgesBuilder = new EdgesBuilder(tree, getTolerance());
                tree.visit(edgesBuilder);
                List<Edge> edges = edgesBuilder.getEdges();
                this.loops = new ArrayList();
                while (!edges.isEmpty()) {
                    Edge edge = edges.get(0);
                    Vertex start = edge.getStart();
                    this.loops.add(start);
                    do {
                        Iterator<Edge> it = edges.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                if (it.next() == edge) {
                                    it.remove();
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                        edge = edge.getEnd().getOutgoing();
                    } while (edge.getStart() != start);
                }
            }
        }
        return Collections.unmodifiableList(this.loops);
    }

    public EnclosingBall<Sphere2D, S2Point> getEnclosingCap() {
        if (isEmpty()) {
            return new EnclosingBall<>(S2Point.PLUS_K, Double.NEGATIVE_INFINITY, new S2Point[0]);
        }
        if (isFull()) {
            return new EnclosingBall<>(S2Point.PLUS_K, Double.POSITIVE_INFINITY, new S2Point[0]);
        }
        BSPTree tree = getTree(false);
        if (isEmpty(tree.getMinus()) && isFull(tree.getPlus())) {
            return new EnclosingBall<>(new S2Point(((Circle) tree.getCut().getHyperplane()).getPole()).negate(), 1.5707963267948966d, new S2Point[0]);
        }
        if (isFull(tree.getMinus()) && isEmpty(tree.getPlus())) {
            return new EnclosingBall<>(new S2Point(((Circle) tree.getCut().getHyperplane()).getPole()), 1.5707963267948966d, new S2Point[0]);
        }
        List<Vector3D> insidePoints = getInsidePoints();
        for (Vertex next : getBoundaryLoops()) {
            int i = 0;
            Vertex vertex = next;
            while (true) {
                if (i == 0 || vertex != next) {
                    i++;
                    insidePoints.add(vertex.getLocation().getVector());
                    vertex = vertex.getOutgoing().getEnd();
                }
            }
        }
        EnclosingBall<S, P> enclose = new WelzlEncloser(getTolerance(), new SphereGenerator()).enclose(insidePoints);
        Vector3D[] vector3DArr = (Vector3D[]) enclose.getSupport();
        double radius = enclose.getRadius();
        double norm = ((Vector3D) enclose.getCenter()).getNorm();
        if (norm < getTolerance()) {
            EnclosingBall<Sphere2D, S2Point> enclosingBall = new EnclosingBall<>(S2Point.PLUS_K, Double.POSITIVE_INFINITY, new S2Point[0]);
            for (Vector3D s2Point : getOutsidePoints()) {
                S2Point s2Point2 = new S2Point(s2Point);
                BoundaryProjection projectToBoundary = projectToBoundary(s2Point2);
                if (3.141592653589793d - projectToBoundary.getOffset() < enclosingBall.getRadius()) {
                    enclosingBall = new EnclosingBall<>(s2Point2.negate(), 3.141592653589793d - projectToBoundary.getOffset(), (S2Point) projectToBoundary.getProjected());
                }
            }
            return enclosingBall;
        }
        S2Point[] s2PointArr = new S2Point[vector3DArr.length];
        for (int i2 = 0; i2 < vector3DArr.length; i2++) {
            s2PointArr[i2] = new S2Point(vector3DArr[i2]);
        }
        return new EnclosingBall<>(new S2Point((Vector3D) enclose.getCenter()), FastMath.acos((((norm * norm) + 1.0d) - (radius * radius)) / (norm * 2.0d)), s2PointArr);
    }

    private List<Vector3D> getInsidePoints() {
        PropertiesComputer propertiesComputer = new PropertiesComputer(getTolerance());
        getTree(true).visit(propertiesComputer);
        return propertiesComputer.getConvexCellsInsidePoints();
    }

    private List<Vector3D> getOutsidePoints() {
        PropertiesComputer propertiesComputer = new PropertiesComputer(getTolerance());
        ((SphericalPolygonsSet) new RegionFactory().getComplement(this)).getTree(true).visit(propertiesComputer);
        return propertiesComputer.getConvexCellsInsidePoints();
    }
}
