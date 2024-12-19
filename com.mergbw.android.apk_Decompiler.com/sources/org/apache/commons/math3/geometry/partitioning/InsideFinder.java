package org.apache.commons.math3.geometry.partitioning;

import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;

class InsideFinder<S extends Space> {
    private boolean minusFound = false;
    private boolean plusFound = false;
    private final Region<S> region;

    public InsideFinder(Region<S> region2) {
        this.region = region2;
    }

    public void recurseSides(BSPTree<S> bSPTree, SubHyperplane<S> subHyperplane) {
        if (bSPTree.getCut() != null) {
            Hyperplane<S> hyperplane = bSPTree.getCut().getHyperplane();
            int i = AnonymousClass1.$SwitchMap$org$apache$commons$math3$geometry$partitioning$Side[subHyperplane.side(hyperplane).ordinal()];
            if (i == 1) {
                if (bSPTree.getCut().side(subHyperplane.getHyperplane()) == Side.PLUS) {
                    if (!this.region.isEmpty(bSPTree.getMinus())) {
                        this.plusFound = true;
                    }
                } else if (!this.region.isEmpty(bSPTree.getMinus())) {
                    this.minusFound = true;
                }
                if (!this.plusFound || !this.minusFound) {
                    recurseSides(bSPTree.getPlus(), subHyperplane);
                }
            } else if (i == 2) {
                if (bSPTree.getCut().side(subHyperplane.getHyperplane()) == Side.PLUS) {
                    if (!this.region.isEmpty(bSPTree.getPlus())) {
                        this.plusFound = true;
                    }
                } else if (!this.region.isEmpty(bSPTree.getPlus())) {
                    this.minusFound = true;
                }
                if (!this.plusFound || !this.minusFound) {
                    recurseSides(bSPTree.getMinus(), subHyperplane);
                }
            } else if (i == 3) {
                SubHyperplane.SplitSubHyperplane<S> split = subHyperplane.split(hyperplane);
                recurseSides(bSPTree.getPlus(), split.getPlus());
                if (!this.plusFound || !this.minusFound) {
                    recurseSides(bSPTree.getMinus(), split.getMinus());
                }
            } else if (bSPTree.getCut().getHyperplane().sameOrientationAs(subHyperplane.getHyperplane())) {
                if (bSPTree.getPlus().getCut() != null || ((Boolean) bSPTree.getPlus().getAttribute()).booleanValue()) {
                    this.plusFound = true;
                }
                if (bSPTree.getMinus().getCut() != null || ((Boolean) bSPTree.getMinus().getAttribute()).booleanValue()) {
                    this.minusFound = true;
                }
            } else {
                if (bSPTree.getPlus().getCut() != null || ((Boolean) bSPTree.getPlus().getAttribute()).booleanValue()) {
                    this.minusFound = true;
                }
                if (bSPTree.getMinus().getCut() != null || ((Boolean) bSPTree.getMinus().getAttribute()).booleanValue()) {
                    this.plusFound = true;
                }
            }
        } else if (((Boolean) bSPTree.getAttribute()).booleanValue()) {
            this.plusFound = true;
            this.minusFound = true;
        }
    }

    /* renamed from: org.apache.commons.math3.geometry.partitioning.InsideFinder$1  reason: invalid class name */
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
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.geometry.partitioning.InsideFinder.AnonymousClass1.<clinit>():void");
        }
    }

    public boolean plusFound() {
        return this.plusFound;
    }

    public boolean minusFound() {
        return this.minusFound;
    }
}
