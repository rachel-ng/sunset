package org.apache.commons.math3.geometry.euclidean.twod.hull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D;
import org.apache.commons.math3.geometry.euclidean.twod.Line;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

public class MonotoneChain extends AbstractConvexHullGenerator2D {
    public /* bridge */ /* synthetic */ ConvexHull2D generate(Collection collection) throws NullArgumentException, ConvergenceException {
        return super.generate((Collection<Vector2D>) collection);
    }

    public /* bridge */ /* synthetic */ double getTolerance() {
        return super.getTolerance();
    }

    public /* bridge */ /* synthetic */ boolean isIncludeCollinearPoints() {
        return super.isIncludeCollinearPoints();
    }

    public MonotoneChain() {
        this(false);
    }

    public MonotoneChain(boolean z) {
        super(z);
    }

    public MonotoneChain(boolean z, double d) {
        super(z, d);
    }

    public Collection<Vector2D> findHullVertices(Collection<Vector2D> collection) {
        ArrayList<Vector2D> arrayList = new ArrayList<>(collection);
        Collections.sort(arrayList, new Comparator<Vector2D>() {
            public int compare(Vector2D vector2D, Vector2D vector2D2) {
                double tolerance = MonotoneChain.this.getTolerance();
                int compareTo = Precision.compareTo(vector2D.getX(), vector2D2.getX(), tolerance);
                return compareTo == 0 ? Precision.compareTo(vector2D.getY(), vector2D2.getY(), tolerance) : compareTo;
            }
        });
        ArrayList arrayList2 = new ArrayList();
        for (Vector2D updateHull : arrayList) {
            updateHull(updateHull, arrayList2);
        }
        ArrayList arrayList3 = new ArrayList();
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            updateHull((Vector2D) arrayList.get(size), arrayList3);
        }
        ArrayList arrayList4 = new ArrayList((arrayList2.size() + arrayList3.size()) - 2);
        for (int i = 0; i < arrayList2.size() - 1; i++) {
            arrayList4.add(arrayList2.get(i));
        }
        for (int i2 = 0; i2 < arrayList3.size() - 1; i2++) {
            arrayList4.add(arrayList3.get(i2));
        }
        if (arrayList4.isEmpty() && !arrayList2.isEmpty()) {
            arrayList4.add(arrayList2.get(0));
        }
        return arrayList4;
    }

    private void updateHull(Vector2D vector2D, List<Vector2D> list) {
        double tolerance = getTolerance();
        if (list.size() != 1 || list.get(0).distance((Vector<Euclidean2D>) vector2D) >= tolerance) {
            while (list.size() >= 2) {
                int size = list.size();
                Vector2D vector2D2 = list.get(size - 2);
                int i = size - 1;
                Vector2D vector2D3 = list.get(i);
                double offset = new Line(vector2D2, vector2D3, tolerance).getOffset((Vector<Euclidean2D>) vector2D);
                if (FastMath.abs(offset) >= tolerance) {
                    if (offset <= 0.0d) {
                        break;
                    }
                    list.remove(i);
                } else {
                    double distance = vector2D2.distance((Vector<Euclidean2D>) vector2D);
                    if (distance >= tolerance && vector2D3.distance((Vector<Euclidean2D>) vector2D) >= tolerance) {
                        double distance2 = vector2D2.distance((Vector<Euclidean2D>) vector2D3);
                        if (isIncludeCollinearPoints()) {
                            if (distance < distance2) {
                                size = i;
                            }
                            list.add(size, vector2D);
                            return;
                        } else if (distance > distance2) {
                            list.remove(i);
                            list.add(vector2D);
                            return;
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                }
            }
            list.add(vector2D);
        }
    }
}
