package org.apache.commons.math3.geometry.enclosing;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Space;

public class WelzlEncloser<S extends Space, P extends Point<S>> implements Encloser<S, P> {
    private final SupportBallGenerator<S, P> generator;
    private final double tolerance;

    public WelzlEncloser(double d, SupportBallGenerator<S, P> supportBallGenerator) {
        this.tolerance = d;
        this.generator = supportBallGenerator;
    }

    public EnclosingBall<S, P> enclose(Iterable<P> iterable) {
        if (iterable == null || !iterable.iterator().hasNext()) {
            return this.generator.ballOnSupport(new ArrayList());
        }
        return pivotingBall(iterable);
    }

    private EnclosingBall<S, P> pivotingBall(Iterable<P> iterable) {
        Point point = (Point) iterable.iterator().next();
        ArrayList arrayList = new ArrayList(point.getSpace().getDimension() + 1);
        ArrayList arrayList2 = new ArrayList(point.getSpace().getDimension() + 1);
        arrayList.add(point);
        EnclosingBall<S, P> moveToFrontBall = moveToFrontBall(arrayList, arrayList.size(), arrayList2);
        while (true) {
            P selectFarthest = selectFarthest(iterable, moveToFrontBall);
            if (moveToFrontBall.contains(selectFarthest, this.tolerance)) {
                return moveToFrontBall;
            }
            arrayList2.clear();
            arrayList2.add(selectFarthest);
            EnclosingBall<S, P> moveToFrontBall2 = moveToFrontBall(arrayList, arrayList.size(), arrayList2);
            if (moveToFrontBall2.getRadius() >= moveToFrontBall.getRadius()) {
                arrayList.add(0, selectFarthest);
                arrayList.subList(moveToFrontBall2.getSupportSize(), arrayList.size()).clear();
                moveToFrontBall = moveToFrontBall2;
            } else {
                throw new MathInternalError();
            }
        }
    }

    private EnclosingBall<S, P> moveToFrontBall(List<P> list, int i, List<P> list2) {
        EnclosingBall<S, P> ballOnSupport = this.generator.ballOnSupport(list2);
        if (ballOnSupport.getSupportSize() <= ballOnSupport.getCenter().getSpace().getDimension()) {
            for (int i2 = 0; i2 < i; i2++) {
                Point point = (Point) list.get(i2);
                if (!ballOnSupport.contains(point, this.tolerance)) {
                    list2.add(point);
                    ballOnSupport = moveToFrontBall(list, i2, list2);
                    list2.remove(list2.size() - 1);
                    for (int i3 = i2; i3 > 0; i3--) {
                        list.set(i3, list.get(i3 - 1));
                    }
                    list.set(0, point);
                }
            }
        }
        return ballOnSupport;
    }

    public P selectFarthest(Iterable<P> iterable, EnclosingBall<S, P> enclosingBall) {
        P center = enclosingBall.getCenter();
        P p = null;
        double d = -1.0d;
        for (P p2 : iterable) {
            double distance = p2.distance(center);
            if (distance > d) {
                p = p2;
                d = distance;
            }
        }
        return p;
    }
}
