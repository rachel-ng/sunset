package org.apache.commons.math3.geometry.spherical.twod;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Vertex {
    private final List<Circle> circles = new ArrayList();
    private Edge incoming = null;
    private final S2Point location;
    private Edge outgoing = null;

    Vertex(S2Point s2Point) {
        this.location = s2Point;
    }

    public S2Point getLocation() {
        return this.location;
    }

    /* access modifiers changed from: package-private */
    public void bindWith(Circle circle) {
        this.circles.add(circle);
    }

    /* access modifiers changed from: package-private */
    public Circle sharedCircleWith(Vertex vertex) {
        for (Circle next : this.circles) {
            Iterator<Circle> it = vertex.circles.iterator();
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

    /* access modifiers changed from: package-private */
    public void setIncoming(Edge edge) {
        this.incoming = edge;
        bindWith(edge.getCircle());
    }

    public Edge getIncoming() {
        return this.incoming;
    }

    /* access modifiers changed from: package-private */
    public void setOutgoing(Edge edge) {
        this.outgoing = edge;
        bindWith(edge.getCircle());
    }

    public Edge getOutgoing() {
        return this.outgoing;
    }
}
