package org.apache.commons.math3.geometry.partitioning;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;

class Characterization<S extends Space> {
    private final NodesSet<S> insideSplitters = new NodesSet<>();
    private SubHyperplane<S> insideTouching = null;
    private final NodesSet<S> outsideSplitters = new NodesSet<>();
    private SubHyperplane<S> outsideTouching = null;

    public Characterization(BSPTree<S> bSPTree, SubHyperplane<S> subHyperplane) {
        characterize(bSPTree, subHyperplane, new ArrayList());
    }

    private void characterize(BSPTree<S> bSPTree, SubHyperplane<S> subHyperplane, List<BSPTree<S>> list) {
        if (bSPTree.getCut() != null) {
            Hyperplane<S> hyperplane = bSPTree.getCut().getHyperplane();
            int i = AnonymousClass1.$SwitchMap$org$apache$commons$math3$geometry$partitioning$Side[subHyperplane.side(hyperplane).ordinal()];
            if (i == 1) {
                characterize(bSPTree.getPlus(), subHyperplane, list);
            } else if (i == 2) {
                characterize(bSPTree.getMinus(), subHyperplane, list);
            } else if (i == 3) {
                SubHyperplane.SplitSubHyperplane<S> split = subHyperplane.split(hyperplane);
                list.add(bSPTree);
                characterize(bSPTree.getPlus(), split.getPlus(), list);
                characterize(bSPTree.getMinus(), split.getMinus(), list);
                list.remove(list.size() - 1);
            } else {
                throw new MathInternalError();
            }
        } else if (((Boolean) bSPTree.getAttribute()).booleanValue()) {
            addInsideTouching(subHyperplane, list);
        } else {
            addOutsideTouching(subHyperplane, list);
        }
    }

    /* renamed from: org.apache.commons.math3.geometry.partitioning.Characterization$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$math3$geometry$partitioning$Side;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
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
                int[] r0 = $SwitchMap$org$apache$commons$math3$geometry$partitioning$Side     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.commons.math3.geometry.partitioning.Side r1 = org.apache.commons.math3.geometry.partitioning.Side.BOTH     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.geometry.partitioning.Characterization.AnonymousClass1.<clinit>():void");
        }
    }

    private void addOutsideTouching(SubHyperplane<S> subHyperplane, List<BSPTree<S>> list) {
        SubHyperplane<S> subHyperplane2 = this.outsideTouching;
        if (subHyperplane2 == null) {
            this.outsideTouching = subHyperplane;
        } else {
            this.outsideTouching = subHyperplane2.reunite(subHyperplane);
        }
        this.outsideSplitters.addAll(list);
    }

    private void addInsideTouching(SubHyperplane<S> subHyperplane, List<BSPTree<S>> list) {
        SubHyperplane<S> subHyperplane2 = this.insideTouching;
        if (subHyperplane2 == null) {
            this.insideTouching = subHyperplane;
        } else {
            this.insideTouching = subHyperplane2.reunite(subHyperplane);
        }
        this.insideSplitters.addAll(list);
    }

    public boolean touchOutside() {
        SubHyperplane<S> subHyperplane = this.outsideTouching;
        return subHyperplane != null && !subHyperplane.isEmpty();
    }

    public SubHyperplane<S> outsideTouching() {
        return this.outsideTouching;
    }

    public NodesSet<S> getOutsideSplitters() {
        return this.outsideSplitters;
    }

    public boolean touchInside() {
        SubHyperplane<S> subHyperplane = this.insideTouching;
        return subHyperplane != null && !subHyperplane.isEmpty();
    }

    public SubHyperplane<S> insideTouching() {
        return this.insideTouching;
    }

    public NodesSet<S> getInsideSplitters() {
        return this.insideSplitters;
    }
}
