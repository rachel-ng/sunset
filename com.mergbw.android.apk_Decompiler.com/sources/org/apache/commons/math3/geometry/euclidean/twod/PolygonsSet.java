package org.apache.commons.math3.geometry.euclidean.twod;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
import org.apache.commons.math3.geometry.euclidean.oned.Interval;
import org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet;
import org.apache.commons.math3.geometry.euclidean.oned.Vector1D;
import org.apache.commons.math3.geometry.partitioning.AbstractRegion;
import org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor;
import org.apache.commons.math3.geometry.partitioning.BoundaryAttribute;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.NodesSet;
import org.apache.commons.math3.geometry.partitioning.Side;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

public class PolygonsSet extends AbstractRegion<Euclidean2D, Euclidean1D> {
    private static final double DEFAULT_TOLERANCE = 1.0E-10d;
    private Vector2D[][] vertices;

    public PolygonsSet(double d) {
        super(d);
    }

    public PolygonsSet(BSPTree<Euclidean2D> bSPTree, double d) {
        super(bSPTree, d);
    }

    public PolygonsSet(Collection<SubHyperplane<Euclidean2D>> collection, double d) {
        super(collection, d);
    }

    public PolygonsSet(double d, double d2, double d3, double d4, double d5) {
        super((Hyperplane<S>[]) boxBoundary(d, d2, d3, d4, d5), d5);
    }

    public PolygonsSet(double d, Vector2D... vector2DArr) {
        super(verticesToTree(d, vector2DArr), d);
    }

    @Deprecated
    public PolygonsSet() {
        this(1.0E-10d);
    }

    @Deprecated
    public PolygonsSet(BSPTree<Euclidean2D> bSPTree) {
        this(bSPTree, 1.0E-10d);
    }

    @Deprecated
    public PolygonsSet(Collection<SubHyperplane<Euclidean2D>> collection) {
        this(collection, 1.0E-10d);
    }

    @Deprecated
    public PolygonsSet(double d, double d2, double d3, double d4) {
        this(d, d2, d3, d4, 1.0E-10d);
    }

    private static Line[] boxBoundary(double d, double d2, double d3, double d4, double d5) {
        if (d >= d2 - d5 || d3 >= d4 - d5) {
            return null;
        }
        Vector2D vector2D = new Vector2D(d, d3);
        Vector2D vector2D2 = new Vector2D(d, d4);
        Vector2D vector2D3 = new Vector2D(d2, d3);
        Vector2D vector2D4 = new Vector2D(d2, d4);
        return new Line[]{new Line(vector2D, vector2D3, d5), new Line(vector2D3, vector2D4, d5), new Line(vector2D4, vector2D2, d5), new Line(vector2D2, vector2D, d5)};
    }

    private static BSPTree<Euclidean2D> verticesToTree(double d, Vector2D... vector2DArr) {
        int length = vector2DArr.length;
        if (length == 0) {
            return new BSPTree<>(Boolean.TRUE);
        }
        Vertex[] vertexArr = new Vertex[length];
        for (int i = 0; i < length; i++) {
            vertexArr[i] = new Vertex(vector2DArr[i]);
        }
        ArrayList arrayList = new ArrayList(length);
        int i2 = 0;
        while (i2 < length) {
            Vertex vertex = vertexArr[i2];
            i2++;
            Vertex vertex2 = vertexArr[i2 % length];
            Line sharedLineWith = vertex.sharedLineWith(vertex2);
            if (sharedLineWith == null) {
                sharedLineWith = new Line(vertex.getLocation(), vertex2.getLocation(), d);
            }
            arrayList.add(new Edge(vertex, vertex2, sharedLineWith));
            for (int i3 = 0; i3 < length; i3++) {
                Vertex vertex3 = vertexArr[i3];
                if (!(vertex3 == vertex || vertex3 == vertex2 || FastMath.abs(sharedLineWith.getOffset((Point<Euclidean2D>) vertex3.getLocation())) > d)) {
                    vertex3.bindWith(sharedLineWith);
                }
            }
        }
        BSPTree<Euclidean2D> bSPTree = new BSPTree<>();
        insertEdges(d, bSPTree, arrayList);
        return bSPTree;
    }

    private static void insertEdges(double d, BSPTree<Euclidean2D> bSPTree, List<Edge> list) {
        Edge edge;
        double d2 = d;
        BSPTree<Euclidean2D> bSPTree2 = bSPTree;
        int i = 0;
        loop0:
        while (true) {
            edge = null;
            while (edge == null && i < list.size()) {
                int i2 = i + 1;
                Edge edge2 = list.get(i);
                if (edge2.getNode() != null || !bSPTree2.insertCut(edge2.getLine())) {
                    i = i2;
                } else {
                    edge2.setNode(bSPTree2);
                    int i3 = i2;
                    edge = edge2;
                    i = i3;
                }
            }
        }
        List<Edge> list2 = list;
        if (edge == null) {
            BSPTree<Euclidean2D> parent = bSPTree.getParent();
            if (parent == null || bSPTree2 == parent.getMinus()) {
                bSPTree2.setAttribute(Boolean.TRUE);
            } else {
                bSPTree2.setAttribute(Boolean.FALSE);
            }
        } else {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (Edge next : list) {
                if (next != edge) {
                    double offset = edge.getLine().getOffset((Point<Euclidean2D>) next.getStart().getLocation());
                    double offset2 = edge.getLine().getOffset((Point<Euclidean2D>) next.getEnd().getLocation());
                    Side side = FastMath.abs(offset) <= d2 ? Side.HYPER : offset < 0.0d ? Side.MINUS : Side.PLUS;
                    Side side2 = FastMath.abs(offset2) <= d2 ? Side.HYPER : offset2 < 0.0d ? Side.MINUS : Side.PLUS;
                    int i4 = AnonymousClass1.$SwitchMap$org$apache$commons$math3$geometry$partitioning$Side[side.ordinal()];
                    if (i4 != 1) {
                        if (i4 != 2) {
                            if (side2 == Side.PLUS) {
                                arrayList.add(next);
                            } else if (side2 == Side.MINUS) {
                                arrayList2.add(next);
                            }
                        } else if (side2 == Side.PLUS) {
                            Vertex split = next.split(edge.getLine());
                            arrayList2.add(split.getIncoming());
                            arrayList.add(split.getOutgoing());
                        } else {
                            arrayList2.add(next);
                        }
                    } else if (side2 == Side.MINUS) {
                        Vertex split2 = next.split(edge.getLine());
                        arrayList2.add(split2.getOutgoing());
                        arrayList.add(split2.getIncoming());
                    } else {
                        arrayList.add(next);
                    }
                }
            }
            if (!arrayList.isEmpty()) {
                insertEdges(d2, bSPTree.getPlus(), arrayList);
            } else {
                bSPTree.getPlus().setAttribute(Boolean.FALSE);
            }
            if (!arrayList2.isEmpty()) {
                insertEdges(d2, bSPTree.getMinus(), arrayList2);
            } else {
                bSPTree.getMinus().setAttribute(Boolean.TRUE);
            }
        }
    }

    /* renamed from: org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$math3$geometry$partitioning$Side;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
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
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.AnonymousClass1.<clinit>():void");
        }
    }

    private static class Vertex {
        private Edge incoming = null;
        private final List<Line> lines = new ArrayList();
        private final Vector2D location;
        private Edge outgoing = null;

        public Vertex(Vector2D vector2D) {
            this.location = vector2D;
        }

        public Vector2D getLocation() {
            return this.location;
        }

        public void bindWith(Line line) {
            this.lines.add(line);
        }

        public Line sharedLineWith(Vertex vertex) {
            for (Line next : this.lines) {
                Iterator<Line> it = vertex.lines.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (next == it.next()) {
                            return next;
                        }
                    }
                }
            }
            return null;
        }

        public void setIncoming(Edge edge) {
            this.incoming = edge;
            bindWith(edge.getLine());
        }

        public Edge getIncoming() {
            return this.incoming;
        }

        public void setOutgoing(Edge edge) {
            this.outgoing = edge;
            bindWith(edge.getLine());
        }

        public Edge getOutgoing() {
            return this.outgoing;
        }
    }

    private static class Edge {
        private final Vertex end;
        private final Line line;
        private BSPTree<Euclidean2D> node = null;
        private final Vertex start;

        public Edge(Vertex vertex, Vertex vertex2, Line line2) {
            this.start = vertex;
            this.end = vertex2;
            this.line = line2;
            vertex.setOutgoing(this);
            vertex2.setIncoming(this);
        }

        public Vertex getStart() {
            return this.start;
        }

        public Vertex getEnd() {
            return this.end;
        }

        public Line getLine() {
            return this.line;
        }

        public void setNode(BSPTree<Euclidean2D> bSPTree) {
            this.node = bSPTree;
        }

        public BSPTree<Euclidean2D> getNode() {
            return this.node;
        }

        public Vertex split(Line line2) {
            Vertex vertex = new Vertex(this.line.intersection(line2));
            vertex.bindWith(line2);
            Edge edge = new Edge(this.start, vertex, this.line);
            Edge edge2 = new Edge(vertex, this.end, this.line);
            edge.node = this.node;
            edge2.node = this.node;
            return vertex;
        }
    }

    public PolygonsSet buildNew(BSPTree<Euclidean2D> bSPTree) {
        return new PolygonsSet(bSPTree, getTolerance());
    }

    /* access modifiers changed from: protected */
    public void computeGeometricalProperties() {
        Vector2D[][] vertices2 = getVertices();
        if (vertices2.length == 0) {
            BSPTree tree = getTree(false);
            if (tree.getCut() != null || !((Boolean) tree.getAttribute()).booleanValue()) {
                setSize(0.0d);
                setBarycenter(new Vector2D(0.0d, 0.0d));
                return;
            }
            setSize(Double.POSITIVE_INFINITY);
            setBarycenter(Vector2D.NaN);
        } else if (vertices2[0][0] == null) {
            setSize(Double.POSITIVE_INFINITY);
            setBarycenter(Vector2D.NaN);
        } else {
            double d = 0.0d;
            double d2 = 0.0d;
            double d3 = 0.0d;
            for (Vector2D[] vector2DArr : vertices2) {
                double x = vector2DArr[vector2DArr.length - 1].getX();
                double y = vector2DArr[vector2DArr.length - 1].getY();
                int length = vector2DArr.length;
                int i = 0;
                while (i < length) {
                    Vector2D vector2D = vector2DArr[i];
                    double x2 = vector2D.getX();
                    double y2 = vector2D.getY();
                    double d4 = (x * y2) - (y * x2);
                    d += d4;
                    d2 += (x + x2) * d4;
                    d3 += d4 * (y + y2);
                    i++;
                    x = x2;
                    y = y2;
                }
            }
            if (d < 0.0d) {
                setSize(Double.POSITIVE_INFINITY);
                setBarycenter(Vector2D.NaN);
                return;
            }
            setSize(d / 2.0d);
            double d5 = d * 3.0d;
            setBarycenter(new Vector2D(d2 / d5, d3 / d5));
        }
    }

    public Vector2D[][] getVertices() {
        Iterator it;
        int i;
        Iterator it2;
        if (this.vertices == null) {
            int i2 = 0;
            if (getTree(false).getCut() == null) {
                this.vertices = new Vector2D[0][];
            } else {
                SegmentsBuilder segmentsBuilder = new SegmentsBuilder(getTolerance());
                int i3 = 1;
                getTree(true).visit(segmentsBuilder);
                List<ConnectableSegment> segments = segmentsBuilder.getSegments();
                int size = segments.size() - naturalFollowerConnections(segments);
                if (size > 0) {
                    size -= splitEdgeConnections(segments);
                }
                if (size > 0) {
                    closeVerticesConnections(segments);
                }
                ArrayList arrayList = new ArrayList();
                while (true) {
                    ConnectableSegment unprocessed = getUnprocessed(segments);
                    if (unprocessed == null) {
                        break;
                    }
                    List<Segment> followLoop = followLoop(unprocessed);
                    if (followLoop != null) {
                        if (followLoop.get(0).getStart() == null) {
                            arrayList.add(0, followLoop);
                        } else {
                            arrayList.add(followLoop);
                        }
                    }
                }
                this.vertices = new Vector2D[arrayList.size()][];
                Iterator it3 = arrayList.iterator();
                int i4 = 0;
                while (it3.hasNext()) {
                    List<Segment> list = (List) it3.next();
                    Vector2D vector2D = null;
                    if (list.size() < 2 || (list.size() == 2 && ((Segment) list.get(i2)).getStart() == null && ((Segment) list.get(i3)).getEnd() == null)) {
                        it = it3;
                        Line line = ((Segment) list.get(i2)).getLine();
                        Vector2D[][] vector2DArr = this.vertices;
                        Vector2D[] vector2DArr2 = new Vector2D[3];
                        vector2DArr2[i2] = null;
                        i = 1;
                        vector2DArr2[1] = line.toSpace((Point) new Vector1D(-3.4028234663852886E38d));
                        vector2DArr2[2] = line.toSpace((Point) new Vector1D(3.4028234663852886E38d));
                        vector2DArr[i4] = vector2DArr2;
                        i4++;
                    } else {
                        if (((Segment) list.get(i2)).getStart() == null) {
                            int size2 = list.size();
                            Vector2D[] vector2DArr3 = new Vector2D[(size2 + 2)];
                            int i5 = i2;
                            for (Segment segment : list) {
                                if (i5 == 0) {
                                    double x = segment.getLine().toSubSpace((Point) segment.getEnd()).getX();
                                    it2 = it3;
                                    double max = x - FastMath.max(1.0d, FastMath.abs(x / 2.0d));
                                    int i6 = i5 + 1;
                                    vector2DArr3[i5] = vector2D;
                                    i5 += 2;
                                    vector2DArr3[i6] = segment.getLine().toSpace((Point) new Vector1D(max));
                                } else {
                                    it2 = it3;
                                }
                                int i7 = size2 + 1;
                                if (i5 < i7) {
                                    vector2DArr3[i5] = segment.getEnd();
                                    i5++;
                                }
                                if (i5 == i7) {
                                    double x2 = segment.getLine().toSubSpace((Point) segment.getStart()).getX();
                                    vector2DArr3[i5] = segment.getLine().toSpace((Point) new Vector1D(x2 + FastMath.max(1.0d, FastMath.abs(x2 / 2.0d))));
                                    i5++;
                                }
                                it3 = it2;
                                vector2D = null;
                            }
                            it = it3;
                            this.vertices[i4] = vector2DArr3;
                            i4++;
                        } else {
                            it = it3;
                            Vector2D[] vector2DArr4 = new Vector2D[list.size()];
                            int i8 = 0;
                            for (Segment start : list) {
                                vector2DArr4[i8] = start.getStart();
                                i8++;
                            }
                            this.vertices[i4] = vector2DArr4;
                            i4++;
                        }
                        i2 = 0;
                        i = 1;
                    }
                    i3 = i;
                    it3 = it;
                }
            }
        }
        return (Vector2D[][]) this.vertices.clone();
    }

    private int naturalFollowerConnections(List<ConnectableSegment> list) {
        int i = 0;
        for (ConnectableSegment next : list) {
            if (next.getNext() == null) {
                BSPTree<Euclidean2D> node = next.getNode();
                BSPTree<Euclidean2D> endNode = next.getEndNode();
                Iterator<ConnectableSegment> it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    ConnectableSegment next2 = it.next();
                    if (next2.getPrevious() == null && next2.getNode() == endNode && next2.getStartNode() == node) {
                        next.setNext(next2);
                        next2.setPrevious(next);
                        i++;
                        break;
                    }
                }
            }
        }
        return i;
    }

    private int splitEdgeConnections(List<ConnectableSegment> list) {
        int i = 0;
        for (ConnectableSegment next : list) {
            if (next.getNext() == null) {
                Hyperplane<Euclidean2D> hyperplane = next.getNode().getCut().getHyperplane();
                BSPTree<Euclidean2D> endNode = next.getEndNode();
                Iterator<ConnectableSegment> it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    ConnectableSegment next2 = it.next();
                    if (next2.getPrevious() == null && next2.getNode().getCut().getHyperplane() == hyperplane && next2.getStartNode() == endNode) {
                        next.setNext(next2);
                        next2.setPrevious(next);
                        i++;
                        break;
                    }
                }
            }
        }
        return i;
    }

    private int closeVerticesConnections(List<ConnectableSegment> list) {
        int i = 0;
        for (ConnectableSegment next : list) {
            if (next.getNext() == null && next.getEnd() != null) {
                Vector2D end = next.getEnd();
                ConnectableSegment connectableSegment = null;
                double d = Double.POSITIVE_INFINITY;
                for (ConnectableSegment next2 : list) {
                    if (next2.getPrevious() == null && next2.getStart() != null) {
                        double distance = Vector2D.distance(end, next2.getStart());
                        if (distance < d) {
                            connectableSegment = next2;
                            d = distance;
                        }
                    }
                }
                if (d <= getTolerance()) {
                    next.setNext(connectableSegment);
                    connectableSegment.setPrevious(next);
                    i++;
                }
            }
        }
        return i;
    }

    private ConnectableSegment getUnprocessed(List<ConnectableSegment> list) {
        for (ConnectableSegment next : list) {
            if (!next.isProcessed()) {
                return next;
            }
        }
        return null;
    }

    private List<Segment> followLoop(ConnectableSegment connectableSegment) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(connectableSegment);
        connectableSegment.setProcessed(true);
        ConnectableSegment next = connectableSegment.getNext();
        while (next != connectableSegment && next != null) {
            arrayList.add(next);
            next.setProcessed(true);
            next = next.getNext();
        }
        if (next == null) {
            for (ConnectableSegment previous = connectableSegment.getPrevious(); previous != null; previous = previous.getPrevious()) {
                arrayList.add(0, previous);
                previous.setProcessed(true);
            }
        }
        filterSpuriousVertices(arrayList);
        if (arrayList.size() != 2 || ((Segment) arrayList.get(0)).getStart() == null) {
            return arrayList;
        }
        return null;
    }

    private void filterSpuriousVertices(List<Segment> list) {
        int i = 0;
        while (i < list.size()) {
            Segment segment = list.get(i);
            int size = (i + 1) % list.size();
            Segment segment2 = list.get(size);
            if (segment2 != null && Precision.equals(segment.getLine().getAngle(), segment2.getLine().getAngle(), Precision.EPSILON)) {
                list.set(size, new Segment(segment.getStart(), segment2.getEnd(), segment.getLine()));
                list.remove(i);
                i--;
            }
            i++;
        }
    }

    private static class ConnectableSegment extends Segment {
        private final BSPTree<Euclidean2D> endNode;
        private ConnectableSegment next = null;
        private final BSPTree<Euclidean2D> node;
        private ConnectableSegment previous = null;
        private boolean processed = false;
        private final BSPTree<Euclidean2D> startNode;

        public ConnectableSegment(Vector2D vector2D, Vector2D vector2D2, Line line, BSPTree<Euclidean2D> bSPTree, BSPTree<Euclidean2D> bSPTree2, BSPTree<Euclidean2D> bSPTree3) {
            super(vector2D, vector2D2, line);
            this.node = bSPTree;
            this.startNode = bSPTree2;
            this.endNode = bSPTree3;
        }

        public BSPTree<Euclidean2D> getNode() {
            return this.node;
        }

        public BSPTree<Euclidean2D> getStartNode() {
            return this.startNode;
        }

        public BSPTree<Euclidean2D> getEndNode() {
            return this.endNode;
        }

        public ConnectableSegment getPrevious() {
            return this.previous;
        }

        public void setPrevious(ConnectableSegment connectableSegment) {
            this.previous = connectableSegment;
        }

        public ConnectableSegment getNext() {
            return this.next;
        }

        public void setNext(ConnectableSegment connectableSegment) {
            this.next = connectableSegment;
        }

        public void setProcessed(boolean z) {
            this.processed = z;
        }

        public boolean isProcessed() {
            return this.processed;
        }
    }

    private static class SegmentsBuilder implements BSPTreeVisitor<Euclidean2D> {
        private final List<ConnectableSegment> segments = new ArrayList();
        private final double tolerance;

        public void visitLeafNode(BSPTree<Euclidean2D> bSPTree) {
        }

        public SegmentsBuilder(double d) {
            this.tolerance = d;
        }

        public BSPTreeVisitor.Order visitOrder(BSPTree<Euclidean2D> bSPTree) {
            return BSPTreeVisitor.Order.MINUS_SUB_PLUS;
        }

        public void visitInternalNode(BSPTree<Euclidean2D> bSPTree) {
            BoundaryAttribute boundaryAttribute = (BoundaryAttribute) bSPTree.getAttribute();
            NodesSet splitters = boundaryAttribute.getSplitters();
            if (boundaryAttribute.getPlusOutside() != null) {
                addContribution(boundaryAttribute.getPlusOutside(), bSPTree, splitters, false);
            }
            if (boundaryAttribute.getPlusInside() != null) {
                addContribution(boundaryAttribute.getPlusInside(), bSPTree, splitters, true);
            }
        }

        private void addContribution(SubHyperplane<Euclidean2D> subHyperplane, BSPTree<Euclidean2D> bSPTree, Iterable<BSPTree<Euclidean2D>> iterable, boolean z) {
            Iterable<BSPTree<Euclidean2D>> iterable2 = iterable;
            Line line = (Line) subHyperplane.getHyperplane();
            for (Interval next : ((IntervalsSet) ((AbstractSubHyperplane) subHyperplane).getRemainingRegion()).asList()) {
                Vector2D space = Double.isInfinite(next.getInf()) ? null : line.toSpace((Point) new Vector1D(next.getInf()));
                Vector2D space2 = Double.isInfinite(next.getSup()) ? null : line.toSpace((Point) new Vector1D(next.getSup()));
                BSPTree<Euclidean2D> selectClosest = selectClosest(space, iterable2);
                BSPTree<Euclidean2D> selectClosest2 = selectClosest(space2, iterable2);
                if (z) {
                    this.segments.add(new ConnectableSegment(space2, space, line.getReverse(), bSPTree, selectClosest2, selectClosest));
                } else {
                    this.segments.add(new ConnectableSegment(space, space2, line, bSPTree, selectClosest, selectClosest2));
                }
            }
        }

        private BSPTree<Euclidean2D> selectClosest(Vector2D vector2D, Iterable<BSPTree<Euclidean2D>> iterable) {
            double d = Double.POSITIVE_INFINITY;
            BSPTree<Euclidean2D> bSPTree = null;
            for (BSPTree<Euclidean2D> next : iterable) {
                double abs = FastMath.abs(next.getCut().getHyperplane().getOffset(vector2D));
                if (abs < d) {
                    bSPTree = next;
                    d = abs;
                }
            }
            if (d <= this.tolerance) {
                return bSPTree;
            }
            return null;
        }

        public List<ConnectableSegment> getSegments() {
            return this.segments;
        }
    }
}
