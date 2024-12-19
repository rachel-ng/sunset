package org.apache.commons.math3.geometry.partitioning;

import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor;

class BoundaryBuilder<S extends Space> implements BSPTreeVisitor<S> {
    public void visitLeafNode(BSPTree<S> bSPTree) {
    }

    BoundaryBuilder() {
    }

    public BSPTreeVisitor.Order visitOrder(BSPTree<S> bSPTree) {
        return BSPTreeVisitor.Order.PLUS_MINUS_SUB;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x004b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void visitInternalNode(org.apache.commons.math3.geometry.partitioning.BSPTree<S> r8) {
        /*
            r7 = this;
            org.apache.commons.math3.geometry.partitioning.Characterization r0 = new org.apache.commons.math3.geometry.partitioning.Characterization
            org.apache.commons.math3.geometry.partitioning.BSPTree r1 = r8.getPlus()
            org.apache.commons.math3.geometry.partitioning.SubHyperplane r2 = r8.getCut()
            org.apache.commons.math3.geometry.partitioning.SubHyperplane r2 = r2.copySelf()
            r0.<init>(r1, r2)
            boolean r1 = r0.touchOutside()
            r2 = 0
            if (r1 == 0) goto L_0x0043
            org.apache.commons.math3.geometry.partitioning.Characterization r1 = new org.apache.commons.math3.geometry.partitioning.Characterization
            org.apache.commons.math3.geometry.partitioning.BSPTree r3 = r8.getMinus()
            org.apache.commons.math3.geometry.partitioning.SubHyperplane r4 = r0.outsideTouching()
            r1.<init>(r3, r4)
            boolean r3 = r1.touchInside()
            if (r3 == 0) goto L_0x0043
            org.apache.commons.math3.geometry.partitioning.SubHyperplane r3 = r1.insideTouching()
            org.apache.commons.math3.geometry.partitioning.NodesSet r4 = new org.apache.commons.math3.geometry.partitioning.NodesSet
            r4.<init>()
            org.apache.commons.math3.geometry.partitioning.NodesSet r1 = r1.getInsideSplitters()
            r4.addAll(r1)
            org.apache.commons.math3.geometry.partitioning.NodesSet r1 = r0.getOutsideSplitters()
            r4.addAll(r1)
            goto L_0x0045
        L_0x0043:
            r3 = r2
            r4 = r3
        L_0x0045:
            boolean r1 = r0.touchInside()
            if (r1 == 0) goto L_0x0077
            org.apache.commons.math3.geometry.partitioning.Characterization r1 = new org.apache.commons.math3.geometry.partitioning.Characterization
            org.apache.commons.math3.geometry.partitioning.BSPTree r5 = r8.getMinus()
            org.apache.commons.math3.geometry.partitioning.SubHyperplane r6 = r0.insideTouching()
            r1.<init>(r5, r6)
            boolean r5 = r1.touchOutside()
            if (r5 == 0) goto L_0x0077
            org.apache.commons.math3.geometry.partitioning.SubHyperplane r2 = r1.outsideTouching()
            if (r4 != 0) goto L_0x0069
            org.apache.commons.math3.geometry.partitioning.NodesSet r4 = new org.apache.commons.math3.geometry.partitioning.NodesSet
            r4.<init>()
        L_0x0069:
            org.apache.commons.math3.geometry.partitioning.NodesSet r1 = r1.getOutsideSplitters()
            r4.addAll(r1)
            org.apache.commons.math3.geometry.partitioning.NodesSet r0 = r0.getInsideSplitters()
            r4.addAll(r0)
        L_0x0077:
            if (r4 == 0) goto L_0x0087
            org.apache.commons.math3.geometry.partitioning.BSPTree r0 = r8.getParent()
        L_0x007d:
            if (r0 == 0) goto L_0x0087
            r4.add(r0)
            org.apache.commons.math3.geometry.partitioning.BSPTree r0 = r0.getParent()
            goto L_0x007d
        L_0x0087:
            org.apache.commons.math3.geometry.partitioning.BoundaryAttribute r0 = new org.apache.commons.math3.geometry.partitioning.BoundaryAttribute
            r0.<init>(r3, r2, r4)
            r8.setAttribute(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.geometry.partitioning.BoundaryBuilder.visitInternalNode(org.apache.commons.math3.geometry.partitioning.BSPTree):void");
    }
}
