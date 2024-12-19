package org.apache.commons.math3.geometry.spherical.twod;

import java.util.List;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.spherical.oned.Arc;
import org.apache.commons.math3.util.MathUtils;

public class Edge {
    private final Circle circle;
    private Vertex end;
    private final double length;
    private final Vertex start;

    Edge(Vertex vertex, Vertex vertex2, double d, Circle circle2) {
        this.start = vertex;
        this.end = vertex2;
        this.length = d;
        this.circle = circle2;
        vertex.setOutgoing(this);
        vertex2.setIncoming(this);
    }

    public Vertex getStart() {
        return this.start;
    }

    public Vertex getEnd() {
        return this.end;
    }

    public double getLength() {
        return this.length;
    }

    public Circle getCircle() {
        return this.circle;
    }

    public Vector3D getPointAt(double d) {
        Circle circle2 = this.circle;
        return circle2.getPointAt(d + circle2.getPhase(this.start.getLocation().getVector()));
    }

    /* access modifiers changed from: package-private */
    public void setNextEdge(Edge edge) {
        Vertex start2 = edge.getStart();
        this.end = start2;
        start2.setIncoming(this);
        this.end.bindWith(getCircle());
    }

    /* access modifiers changed from: package-private */
    public void split(Circle circle2, List<Edge> list, List<Edge> list2) {
        double phase = this.circle.getPhase(this.start.getLocation().getVector());
        Arc insideArc = this.circle.getInsideArc(circle2);
        double normalizeAngle = MathUtils.normalizeAngle(insideArc.getInf(), 3.141592653589793d + phase) - phase;
        double size = normalizeAngle + insideArc.getSize();
        double d = size - 6.283185307179586d;
        double tolerance = this.circle.getTolerance();
        Vertex vertex = this.start;
        if (d >= this.length - tolerance) {
            list2.add(this);
            return;
        }
        List<Edge> list3 = list2;
        int i = (d > 0.0d ? 1 : (d == 0.0d ? 0 : -1));
        if (i >= 0) {
            vertex = addSubEdge(vertex, new Vertex(new S2Point(this.circle.getPointAt(phase + d))), d, list2, circle2);
        } else {
            d = 0.0d;
        }
        double d2 = this.length;
        if (normalizeAngle < d2 - tolerance) {
            List<Edge> list4 = list;
            double d3 = phase + normalizeAngle;
            Vertex addSubEdge = addSubEdge(vertex, new Vertex(new S2Point(this.circle.getPointAt(d3))), normalizeAngle - d, list, circle2);
            double d4 = this.length;
            if (size >= d4 - tolerance) {
                addSubEdge(addSubEdge, this.end, d4 - normalizeAngle, list2, circle2);
                return;
            }
            Circle circle3 = circle2;
            addSubEdge(addSubEdge(addSubEdge, new Vertex(new S2Point(this.circle.getPointAt(d3))), normalizeAngle - normalizeAngle, list2, circle3), this.end, this.length - normalizeAngle, list, circle3);
        } else if (i >= 0) {
            addSubEdge(vertex, this.end, d2 - d, list, circle2);
        } else {
            list.add(this);
        }
    }

    private Vertex addSubEdge(Vertex vertex, Vertex vertex2, double d, List<Edge> list, Circle circle2) {
        if (d <= this.circle.getTolerance()) {
            return vertex;
        }
        vertex2.bindWith(circle2);
        list.add(new Edge(vertex, vertex2, d, this.circle));
        return vertex2;
    }
}
