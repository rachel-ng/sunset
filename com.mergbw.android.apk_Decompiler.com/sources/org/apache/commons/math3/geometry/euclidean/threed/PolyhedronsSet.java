package org.apache.commons.math3.geometry.euclidean.threed;

import java.awt.geom.AffineTransform;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
import org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D;
import org.apache.commons.math3.geometry.euclidean.twod.Line;
import org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet;
import org.apache.commons.math3.geometry.euclidean.twod.SubLine;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.geometry.partitioning.AbstractRegion;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor;
import org.apache.commons.math3.geometry.partitioning.BoundaryAttribute;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.RegionFactory;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.geometry.partitioning.Transform;
import org.apache.commons.math3.util.FastMath;

public class PolyhedronsSet extends AbstractRegion<Euclidean3D, Euclidean2D> {
    private static final double DEFAULT_TOLERANCE = 1.0E-10d;

    public PolyhedronsSet(double d) {
        super(d);
    }

    public PolyhedronsSet(BSPTree<Euclidean3D> bSPTree, double d) {
        super(bSPTree, d);
    }

    public PolyhedronsSet(Collection<SubHyperplane<Euclidean3D>> collection, double d) {
        super(collection, d);
    }

    public PolyhedronsSet(List<Vector3D> list, List<int[]> list2, double d) {
        super(buildBoundary(list, list2, d), d);
    }

    public PolyhedronsSet(double d, double d2, double d3, double d4, double d5, double d6, double d7) {
        super(buildBoundary(d, d2, d3, d4, d5, d6, d7), d7);
    }

    @Deprecated
    public PolyhedronsSet() {
        this(1.0E-10d);
    }

    @Deprecated
    public PolyhedronsSet(BSPTree<Euclidean3D> bSPTree) {
        this(bSPTree, 1.0E-10d);
    }

    @Deprecated
    public PolyhedronsSet(Collection<SubHyperplane<Euclidean3D>> collection) {
        this(collection, 1.0E-10d);
    }

    @Deprecated
    public PolyhedronsSet(double d, double d2, double d3, double d4, double d5, double d6) {
        this(d, d2, d3, d4, d5, d6, 1.0E-10d);
    }

    private static BSPTree<Euclidean3D> buildBoundary(double d, double d2, double d3, double d4, double d5, double d6, double d7) {
        double d8 = d7;
        if (d >= d2 - d8 || d3 >= d4 - d8 || d5 >= d6 - d8) {
            return new BSPTree<>(Boolean.FALSE);
        }
        Plane plane = new Plane(new Vector3D(d, 0.0d, 0.0d), Vector3D.MINUS_I, d8);
        Plane plane2 = new Plane(new Vector3D(0.0d, d3, 0.0d), Vector3D.MINUS_J, d8);
        Plane plane3 = new Plane(new Vector3D(0.0d, d4, 0.0d), Vector3D.PLUS_J, d8);
        Plane plane4 = new Plane(new Vector3D(0.0d, 0.0d, d5), Vector3D.MINUS_K, d8);
        Plane plane5 = new Plane(new Vector3D(0.0d, 0.0d, d6), Vector3D.PLUS_K, d8);
        return new RegionFactory().buildConvex(plane, new Plane(new Vector3D(d2, 0.0d, 0.0d), Vector3D.PLUS_I, d8), plane2, plane3, plane4, plane5).getTree(false);
    }

    private static List<SubHyperplane<Euclidean3D>> buildBoundary(List<Vector3D> list, List<int[]> list2, double d) {
        List<Vector3D> list3 = list;
        double d2 = d;
        int i = 0;
        while (i < list.size() - 1) {
            Vector3D vector3D = list3.get(i);
            i++;
            int i2 = i;
            while (true) {
                if (i2 < list.size()) {
                    if (Vector3D.distance(vector3D, list3.get(i2)) > d2) {
                        i2++;
                    } else {
                        throw new MathIllegalArgumentException(LocalizedFormats.CLOSE_VERTICES, Double.valueOf(vector3D.getX()), Double.valueOf(vector3D.getY()), Double.valueOf(vector3D.getZ()));
                    }
                }
            }
        }
        int[][] successors = successors(list3, list2, findReferences(list, list2));
        int i3 = 0;
        while (i3 < list.size()) {
            for (int i4 : successors[i3]) {
                if (i4 >= 0) {
                    boolean z = false;
                    for (int i5 : successors[i4]) {
                        z = z || i5 == i3;
                    }
                    if (!z) {
                        Vector3D vector3D2 = list3.get(i3);
                        Vector3D vector3D3 = list3.get(i4);
                        throw new MathIllegalArgumentException(LocalizedFormats.EDGE_CONNECTED_TO_ONE_FACET, Double.valueOf(vector3D2.getX()), Double.valueOf(vector3D2.getY()), Double.valueOf(vector3D2.getZ()), Double.valueOf(vector3D3.getX()), Double.valueOf(vector3D3.getY()), Double.valueOf(vector3D3.getZ()));
                    }
                }
            }
            i3++;
        }
        ArrayList arrayList = new ArrayList();
        for (int[] next : list2) {
            Plane plane = new Plane(list3.get(next[0]), list3.get(next[1]), list3.get(next[2]), d);
            Vector2D[] vector2DArr = new Vector2D[next.length];
            int i6 = 0;
            while (i6 < next.length) {
                Vector3D vector3D4 = list3.get(next[i6]);
                if (plane.contains(vector3D4)) {
                    vector2DArr[i6] = plane.toSubSpace((Vector<Euclidean3D>) vector3D4);
                    i6++;
                } else {
                    throw new MathIllegalArgumentException(LocalizedFormats.OUT_OF_PLANE, Double.valueOf(vector3D4.getX()), Double.valueOf(vector3D4.getY()), Double.valueOf(vector3D4.getZ()));
                }
            }
            arrayList.add(new SubPlane(plane, new PolygonsSet(d2, vector2DArr)));
        }
        return arrayList;
    }

    private static int[][] findReferences(List<Vector3D> list, List<int[]> list2) {
        int[] iArr = new int[list.size()];
        int i = 0;
        for (int[] next : list2) {
            if (next.length >= 3) {
                for (int i2 : next) {
                    int i3 = iArr[i2] + 1;
                    iArr[i2] = i3;
                    i = FastMath.max(i, i3);
                }
            } else {
                throw new NumberIsTooSmallException(LocalizedFormats.WRONG_NUMBER_OF_POINTS, 3, Integer.valueOf(next.length), true);
            }
        }
        int size = list.size();
        int[] iArr2 = new int[2];
        iArr2[1] = i;
        iArr2[0] = size;
        int[][] iArr3 = (int[][]) Array.newInstance(Integer.TYPE, iArr2);
        for (int[] fill : iArr3) {
            Arrays.fill(fill, -1);
        }
        for (int i4 = 0; i4 < list2.size(); i4++) {
            int[] iArr4 = list2.get(i4);
            int length = iArr4.length;
            for (int i5 = 0; i5 < length; i5++) {
                int i6 = iArr4[i5];
                int i7 = 0;
                while (i7 < i && iArr3[i6][i7] >= 0) {
                    i7++;
                }
                iArr3[i6][i7] = i4;
            }
        }
        return iArr3;
    }

    private static int[][] successors(List<Vector3D> list, List<int[]> list2, int[][] iArr) {
        int i;
        int size = list.size();
        int[] iArr2 = new int[2];
        iArr2[1] = iArr[0].length;
        iArr2[0] = size;
        int[][] iArr3 = (int[][]) Array.newInstance(Integer.TYPE, iArr2);
        for (int[] fill : iArr3) {
            Arrays.fill(fill, -1);
        }
        int i2 = 0;
        while (i2 < list.size()) {
            int i3 = 0;
            while (i3 < iArr3[i2].length && (i = iArr[i2][i3]) >= 0) {
                int[] iArr4 = list2.get(i);
                int i4 = 0;
                while (i4 < iArr4.length && iArr4[i4] != i2) {
                    i4++;
                }
                iArr3[i2][i3] = iArr4[(i4 + 1) % iArr4.length];
                int i5 = 0;
                while (i5 < i3) {
                    int[] iArr5 = iArr3[i2];
                    if (iArr5[i5] != iArr5[i3]) {
                        i5++;
                    } else {
                        Vector3D vector3D = list.get(i2);
                        Vector3D vector3D2 = list.get(iArr3[i2][i3]);
                        throw new MathIllegalArgumentException(LocalizedFormats.FACET_ORIENTATION_MISMATCH, Double.valueOf(vector3D.getX()), Double.valueOf(vector3D.getY()), Double.valueOf(vector3D.getZ()), Double.valueOf(vector3D2.getX()), Double.valueOf(vector3D2.getY()), Double.valueOf(vector3D2.getZ()));
                    }
                }
                i3++;
            }
            i2++;
        }
        return iArr3;
    }

    public PolyhedronsSet buildNew(BSPTree<Euclidean3D> bSPTree) {
        return new PolyhedronsSet(bSPTree, getTolerance());
    }

    /* access modifiers changed from: protected */
    public void computeGeometricalProperties() {
        getTree(true).visit(new FacetsContributionVisitor());
        if (getSize() < 0.0d) {
            setSize(Double.POSITIVE_INFINITY);
            setBarycenter(Vector3D.NaN);
            return;
        }
        setSize(getSize() / 3.0d);
        setBarycenter(new Vector3D(1.0d / (getSize() * 4.0d), (Vector3D) getBarycenter()));
    }

    private class FacetsContributionVisitor implements BSPTreeVisitor<Euclidean3D> {
        public void visitLeafNode(BSPTree<Euclidean3D> bSPTree) {
        }

        public FacetsContributionVisitor() {
            PolyhedronsSet.this.setSize(0.0d);
            PolyhedronsSet.this.setBarycenter(new Vector3D(0.0d, 0.0d, 0.0d));
        }

        public BSPTreeVisitor.Order visitOrder(BSPTree<Euclidean3D> bSPTree) {
            return BSPTreeVisitor.Order.MINUS_SUB_PLUS;
        }

        public void visitInternalNode(BSPTree<Euclidean3D> bSPTree) {
            BoundaryAttribute boundaryAttribute = (BoundaryAttribute) bSPTree.getAttribute();
            if (boundaryAttribute.getPlusOutside() != null) {
                addContribution(boundaryAttribute.getPlusOutside(), false);
            }
            if (boundaryAttribute.getPlusInside() != null) {
                addContribution(boundaryAttribute.getPlusInside(), true);
            }
        }

        private void addContribution(SubHyperplane<Euclidean3D> subHyperplane, boolean z) {
            Region remainingRegion = ((SubPlane) subHyperplane).getRemainingRegion();
            double size = remainingRegion.getSize();
            if (Double.isInfinite(size)) {
                PolyhedronsSet.this.setSize(Double.POSITIVE_INFINITY);
                PolyhedronsSet.this.setBarycenter(Vector3D.NaN);
                return;
            }
            Plane plane = (Plane) subHyperplane.getHyperplane();
            Vector3D space = plane.toSpace(remainingRegion.getBarycenter());
            double dotProduct = size * space.dotProduct(plane.getNormal());
            double d = z ? -dotProduct : dotProduct;
            PolyhedronsSet polyhedronsSet = PolyhedronsSet.this;
            polyhedronsSet.setSize(polyhedronsSet.getSize() + d);
            PolyhedronsSet.this.setBarycenter(new Vector3D(1.0d, (Vector3D) PolyhedronsSet.this.getBarycenter(), d, space));
        }
    }

    public SubHyperplane<Euclidean3D> firstIntersection(Vector3D vector3D, Line line) {
        return recurseFirstIntersection(getTree(true), vector3D, line);
    }

    private SubHyperplane<Euclidean3D> recurseFirstIntersection(BSPTree<Euclidean3D> bSPTree, Vector3D vector3D, Line line) {
        Vector3D intersection;
        SubHyperplane<Euclidean3D> boundaryFacet;
        SubHyperplane<Euclidean3D> boundaryFacet2;
        SubHyperplane<Euclidean3D> cut = bSPTree.getCut();
        if (cut == null) {
            return null;
        }
        BSPTree<Euclidean3D> minus = bSPTree.getMinus();
        BSPTree<Euclidean3D> plus = bSPTree.getPlus();
        Plane plane = (Plane) cut.getHyperplane();
        double offset = plane.getOffset((Point<Euclidean3D>) vector3D);
        boolean z = FastMath.abs(offset) < getTolerance();
        if (offset >= 0.0d) {
            BSPTree<Euclidean3D> bSPTree2 = plus;
            plus = minus;
            minus = bSPTree2;
        }
        if (z && (boundaryFacet2 = boundaryFacet(vector3D, bSPTree)) != null) {
            return boundaryFacet2;
        }
        SubHyperplane<Euclidean3D> recurseFirstIntersection = recurseFirstIntersection(minus, vector3D, line);
        if (recurseFirstIntersection != null) {
            return recurseFirstIntersection;
        }
        if (z || (intersection = plane.intersection(line)) == null || line.getAbscissa(intersection) <= line.getAbscissa(vector3D) || (boundaryFacet = boundaryFacet(intersection, bSPTree)) == null) {
            return recurseFirstIntersection(plus, vector3D, line);
        }
        return boundaryFacet;
    }

    private SubHyperplane<Euclidean3D> boundaryFacet(Vector3D vector3D, BSPTree<Euclidean3D> bSPTree) {
        Vector2D subSpace = ((Plane) bSPTree.getCut().getHyperplane()).toSubSpace((Point) vector3D);
        BoundaryAttribute boundaryAttribute = (BoundaryAttribute) bSPTree.getAttribute();
        if (boundaryAttribute.getPlusOutside() != null && ((SubPlane) boundaryAttribute.getPlusOutside()).getRemainingRegion().checkPoint(subSpace) == Region.Location.INSIDE) {
            return boundaryAttribute.getPlusOutside();
        }
        if (boundaryAttribute.getPlusInside() == null || ((SubPlane) boundaryAttribute.getPlusInside()).getRemainingRegion().checkPoint(subSpace) != Region.Location.INSIDE) {
            return null;
        }
        return boundaryAttribute.getPlusInside();
    }

    public PolyhedronsSet rotate(Vector3D vector3D, Rotation rotation) {
        return (PolyhedronsSet) applyTransform(new RotationTransform(vector3D, rotation));
    }

    private static class RotationTransform implements Transform<Euclidean3D, Euclidean2D> {
        private Plane cachedOriginal;
        private Transform<Euclidean2D, Euclidean1D> cachedTransform;
        private Vector3D center;
        private Rotation rotation;

        public RotationTransform(Vector3D vector3D, Rotation rotation2) {
            this.center = vector3D;
            this.rotation = rotation2;
        }

        public Vector3D apply(Point<Euclidean3D> point) {
            return new Vector3D(1.0d, this.center, 1.0d, this.rotation.applyTo(((Vector3D) point).subtract((Vector) this.center)));
        }

        public Plane apply(Hyperplane<Euclidean3D> hyperplane) {
            return ((Plane) hyperplane).rotate(this.center, this.rotation);
        }

        public SubHyperplane<Euclidean2D> apply(SubHyperplane<Euclidean2D> subHyperplane, Hyperplane<Euclidean3D> hyperplane, Hyperplane<Euclidean3D> hyperplane2) {
            Hyperplane<Euclidean3D> hyperplane3 = hyperplane;
            if (hyperplane3 != this.cachedOriginal) {
                Plane plane = (Plane) hyperplane3;
                Plane plane2 = (Plane) hyperplane2;
                Vector3D origin = plane.getOrigin();
                Vector3D space = plane.toSpace((Point) new Vector2D(1.0d, 0.0d));
                Vector3D space2 = plane.toSpace((Point) new Vector2D(0.0d, 1.0d));
                Vector2D subSpace = plane2.toSubSpace((Point) apply((Point) origin));
                Vector2D subSpace2 = plane2.toSubSpace((Point) apply((Point) space));
                Vector2D subSpace3 = plane2.toSubSpace((Point) apply((Point) space2));
                AffineTransform affineTransform = new AffineTransform(subSpace2.getX() - subSpace.getX(), subSpace2.getY() - subSpace.getY(), subSpace3.getX() - subSpace.getX(), subSpace3.getY() - subSpace.getY(), subSpace.getX(), subSpace.getY());
                this.cachedOriginal = plane;
                this.cachedTransform = Line.getTransform(affineTransform);
            }
            return ((SubLine) subHyperplane).applyTransform(this.cachedTransform);
        }
    }

    public PolyhedronsSet translate(Vector3D vector3D) {
        return (PolyhedronsSet) applyTransform(new TranslationTransform(vector3D));
    }

    private static class TranslationTransform implements Transform<Euclidean3D, Euclidean2D> {
        private Plane cachedOriginal;
        private Transform<Euclidean2D, Euclidean1D> cachedTransform;
        private Vector3D translation;

        public TranslationTransform(Vector3D vector3D) {
            this.translation = vector3D;
        }

        public Vector3D apply(Point<Euclidean3D> point) {
            return new Vector3D(1.0d, (Vector3D) point, 1.0d, this.translation);
        }

        public Plane apply(Hyperplane<Euclidean3D> hyperplane) {
            return ((Plane) hyperplane).translate(this.translation);
        }

        public SubHyperplane<Euclidean2D> apply(SubHyperplane<Euclidean2D> subHyperplane, Hyperplane<Euclidean3D> hyperplane, Hyperplane<Euclidean3D> hyperplane2) {
            if (hyperplane != this.cachedOriginal) {
                Plane plane = (Plane) hyperplane;
                Vector2D subSpace = ((Plane) hyperplane2).toSubSpace((Point) apply((Point) plane.getOrigin()));
                AffineTransform translateInstance = AffineTransform.getTranslateInstance(subSpace.getX(), subSpace.getY());
                this.cachedOriginal = plane;
                this.cachedTransform = Line.getTransform(translateInstance);
            }
            return ((SubLine) subHyperplane).applyTransform(this.cachedTransform);
        }
    }
}
