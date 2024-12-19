package org.apache.commons.math3.geometry.partitioning;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.util.FastMath;

public class BSPTree<S extends Space> {
    private Object attribute;
    private SubHyperplane<S> cut;
    private BSPTree<S> minus;
    private BSPTree<S> parent;
    private BSPTree<S> plus;

    public interface LeafMerger<S extends Space> {
        BSPTree<S> merge(BSPTree<S> bSPTree, BSPTree<S> bSPTree2, BSPTree<S> bSPTree3, boolean z, boolean z2);
    }

    public interface VanishingCutHandler<S extends Space> {
        BSPTree<S> fixNode(BSPTree<S> bSPTree);
    }

    public BSPTree() {
        this.cut = null;
        this.plus = null;
        this.minus = null;
        this.parent = null;
        this.attribute = null;
    }

    public BSPTree(Object obj) {
        this.cut = null;
        this.plus = null;
        this.minus = null;
        this.parent = null;
        this.attribute = obj;
    }

    public BSPTree(SubHyperplane<S> subHyperplane, BSPTree<S> bSPTree, BSPTree<S> bSPTree2, Object obj) {
        this.cut = subHyperplane;
        this.plus = bSPTree;
        this.minus = bSPTree2;
        this.parent = null;
        this.attribute = obj;
        bSPTree.parent = this;
        bSPTree2.parent = this;
    }

    public boolean insertCut(Hyperplane<S> hyperplane) {
        if (this.cut != null) {
            this.plus.parent = null;
            this.minus.parent = null;
        }
        SubHyperplane<S> fitToCell = fitToCell(hyperplane.wholeHyperplane());
        if (fitToCell == null || fitToCell.isEmpty()) {
            this.cut = null;
            this.plus = null;
            this.minus = null;
            return false;
        }
        this.cut = fitToCell;
        BSPTree<S> bSPTree = new BSPTree<>();
        this.plus = bSPTree;
        bSPTree.parent = this;
        BSPTree<S> bSPTree2 = new BSPTree<>();
        this.minus = bSPTree2;
        bSPTree2.parent = this;
        return true;
    }

    public BSPTree<S> copySelf() {
        SubHyperplane<S> subHyperplane = this.cut;
        if (subHyperplane == null) {
            return new BSPTree<>(this.attribute);
        }
        return new BSPTree<>(subHyperplane.copySelf(), this.plus.copySelf(), this.minus.copySelf(), this.attribute);
    }

    public SubHyperplane<S> getCut() {
        return this.cut;
    }

    public BSPTree<S> getPlus() {
        return this.plus;
    }

    public BSPTree<S> getMinus() {
        return this.minus;
    }

    public BSPTree<S> getParent() {
        return this.parent;
    }

    public void setAttribute(Object obj) {
        this.attribute = obj;
    }

    public Object getAttribute() {
        return this.attribute;
    }

    public void visit(BSPTreeVisitor<S> bSPTreeVisitor) {
        if (this.cut == null) {
            bSPTreeVisitor.visitLeafNode(this);
            return;
        }
        switch (AnonymousClass2.$SwitchMap$org$apache$commons$math3$geometry$partitioning$BSPTreeVisitor$Order[bSPTreeVisitor.visitOrder(this).ordinal()]) {
            case 1:
                this.plus.visit(bSPTreeVisitor);
                this.minus.visit(bSPTreeVisitor);
                bSPTreeVisitor.visitInternalNode(this);
                return;
            case 2:
                this.plus.visit(bSPTreeVisitor);
                bSPTreeVisitor.visitInternalNode(this);
                this.minus.visit(bSPTreeVisitor);
                return;
            case 3:
                this.minus.visit(bSPTreeVisitor);
                this.plus.visit(bSPTreeVisitor);
                bSPTreeVisitor.visitInternalNode(this);
                return;
            case 4:
                this.minus.visit(bSPTreeVisitor);
                bSPTreeVisitor.visitInternalNode(this);
                this.plus.visit(bSPTreeVisitor);
                return;
            case 5:
                bSPTreeVisitor.visitInternalNode(this);
                this.plus.visit(bSPTreeVisitor);
                this.minus.visit(bSPTreeVisitor);
                return;
            case 6:
                bSPTreeVisitor.visitInternalNode(this);
                this.minus.visit(bSPTreeVisitor);
                this.plus.visit(bSPTreeVisitor);
                return;
            default:
                throw new MathInternalError();
        }
    }

    private SubHyperplane<S> fitToCell(SubHyperplane<S> subHyperplane) {
        BSPTree bSPTree = this;
        while (true) {
            BSPTree<S> bSPTree2 = bSPTree.parent;
            if (bSPTree2 == null || subHyperplane == null) {
                return subHyperplane;
            }
            if (bSPTree == bSPTree2.plus) {
                subHyperplane = subHyperplane.split(bSPTree2.cut.getHyperplane()).getPlus();
            } else {
                subHyperplane = subHyperplane.split(bSPTree2.cut.getHyperplane()).getMinus();
            }
            bSPTree = bSPTree.parent;
        }
        return subHyperplane;
    }

    @Deprecated
    public BSPTree<S> getCell(Vector<S> vector) {
        return getCell(vector, 1.0E-10d);
    }

    public BSPTree<S> getCell(Point<S> point, double d) {
        SubHyperplane<S> subHyperplane = this.cut;
        if (subHyperplane == null) {
            return this;
        }
        double offset = subHyperplane.getHyperplane().getOffset(point);
        if (FastMath.abs(offset) < d) {
            return this;
        }
        if (offset <= 0.0d) {
            return this.minus.getCell(point, d);
        }
        return this.plus.getCell(point, d);
    }

    public List<BSPTree<S>> getCloseCuts(Point<S> point, double d) {
        ArrayList arrayList = new ArrayList();
        recurseCloseCuts(point, d, arrayList);
        return arrayList;
    }

    private void recurseCloseCuts(Point<S> point, double d, List<BSPTree<S>> list) {
        SubHyperplane<S> subHyperplane = this.cut;
        if (subHyperplane != null) {
            double offset = subHyperplane.getHyperplane().getOffset(point);
            if (offset < (-d)) {
                this.minus.recurseCloseCuts(point, d, list);
            } else if (offset > d) {
                this.plus.recurseCloseCuts(point, d, list);
            } else {
                list.add(this);
                this.minus.recurseCloseCuts(point, d, list);
                this.plus.recurseCloseCuts(point, d, list);
            }
        }
    }

    private void condense() {
        if (this.cut != null) {
            BSPTree<S> bSPTree = this.plus;
            if (bSPTree.cut == null) {
                BSPTree<S> bSPTree2 = this.minus;
                if (bSPTree2.cut == null) {
                    Object obj = bSPTree.attribute;
                    if ((obj == null && bSPTree2.attribute == null) || (obj != null && obj.equals(bSPTree2.attribute))) {
                        Object obj2 = this.plus.attribute;
                        if (obj2 == null) {
                            obj2 = this.minus.attribute;
                        }
                        this.attribute = obj2;
                        this.cut = null;
                        this.plus = null;
                        this.minus = null;
                    }
                }
            }
        }
    }

    public BSPTree<S> merge(BSPTree<S> bSPTree, LeafMerger<S> leafMerger) {
        return merge(bSPTree, leafMerger, (BSPTree<S>) null, false);
    }

    private BSPTree<S> merge(BSPTree<S> bSPTree, LeafMerger<S> leafMerger, BSPTree<S> bSPTree2, boolean z) {
        SubHyperplane<S> subHyperplane = this.cut;
        if (subHyperplane == null) {
            return leafMerger.merge(this, bSPTree, bSPTree2, z, true);
        }
        if (bSPTree.cut == null) {
            return leafMerger.merge(bSPTree, this, bSPTree2, z, false);
        }
        BSPTree<S> split = bSPTree.split(subHyperplane);
        if (bSPTree2 != null) {
            split.parent = bSPTree2;
            if (z) {
                bSPTree2.plus = split;
            } else {
                bSPTree2.minus = split;
            }
        }
        this.plus.merge(split.plus, leafMerger, split, true);
        this.minus.merge(split.minus, leafMerger, split, false);
        split.condense();
        SubHyperplane<S> subHyperplane2 = split.cut;
        if (subHyperplane2 != null) {
            split.cut = split.fitToCell(subHyperplane2.getHyperplane().wholeHyperplane());
        }
        return split;
    }

    public BSPTree<S> split(SubHyperplane<S> subHyperplane) {
        SubHyperplane<S> subHyperplane2 = this.cut;
        if (subHyperplane2 == null) {
            return new BSPTree<>(subHyperplane, copySelf(), new BSPTree(this.attribute), (Object) null);
        }
        Hyperplane<S> hyperplane = subHyperplane2.getHyperplane();
        Hyperplane<S> hyperplane2 = subHyperplane.getHyperplane();
        int i = AnonymousClass2.$SwitchMap$org$apache$commons$math3$geometry$partitioning$Side[subHyperplane.side(hyperplane).ordinal()];
        if (i == 1) {
            BSPTree<S> split = this.plus.split(subHyperplane);
            if (this.cut.side(hyperplane2) == Side.PLUS) {
                BSPTree<S> bSPTree = new BSPTree<>(this.cut.copySelf(), split.plus, this.minus.copySelf(), this.attribute);
                split.plus = bSPTree;
                bSPTree.condense();
                split.plus.parent = split;
            } else {
                BSPTree<S> bSPTree2 = new BSPTree<>(this.cut.copySelf(), split.minus, this.minus.copySelf(), this.attribute);
                split.minus = bSPTree2;
                bSPTree2.condense();
                split.minus.parent = split;
            }
            return split;
        } else if (i == 2) {
            BSPTree<S> split2 = this.minus.split(subHyperplane);
            if (this.cut.side(hyperplane2) == Side.PLUS) {
                BSPTree<S> bSPTree3 = new BSPTree<>(this.cut.copySelf(), this.plus.copySelf(), split2.plus, this.attribute);
                split2.plus = bSPTree3;
                bSPTree3.condense();
                split2.plus.parent = split2;
            } else {
                BSPTree<S> bSPTree4 = new BSPTree<>(this.cut.copySelf(), this.plus.copySelf(), split2.minus, this.attribute);
                split2.minus = bSPTree4;
                bSPTree4.condense();
                split2.minus.parent = split2;
            }
            return split2;
        } else if (i != 3) {
            return hyperplane.sameOrientationAs(hyperplane2) ? new BSPTree<>(subHyperplane, this.plus.copySelf(), this.minus.copySelf(), this.attribute) : new BSPTree<>(subHyperplane, this.minus.copySelf(), this.plus.copySelf(), this.attribute);
        } else {
            SubHyperplane.SplitSubHyperplane<S> split3 = this.cut.split(hyperplane2);
            SubHyperplane.SplitSubHyperplane<S> split4 = subHyperplane.split(hyperplane);
            BSPTree<S> bSPTree5 = new BSPTree<>(subHyperplane, this.plus.split(split4.getPlus()), this.minus.split(split4.getMinus()), (Object) null);
            bSPTree5.plus.cut = split3.getPlus();
            bSPTree5.minus.cut = split3.getMinus();
            BSPTree<S> bSPTree6 = bSPTree5.plus;
            BSPTree<S> bSPTree7 = bSPTree6.minus;
            BSPTree<S> bSPTree8 = bSPTree5.minus.plus;
            bSPTree6.minus = bSPTree8;
            bSPTree8.parent = bSPTree6;
            BSPTree<S> bSPTree9 = bSPTree5.minus;
            bSPTree9.plus = bSPTree7;
            bSPTree7.parent = bSPTree9;
            bSPTree5.plus.condense();
            bSPTree5.minus.condense();
            return bSPTree5;
        }
    }

    /* renamed from: org.apache.commons.math3.geometry.partitioning.BSPTree$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$math3$geometry$partitioning$BSPTreeVisitor$Order;
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$math3$geometry$partitioning$Side;

        /* JADX WARNING: Can't wrap try/catch for region: R(21:0|(2:1|2)|3|(2:5|6)|7|9|10|11|13|14|15|16|17|18|19|20|21|22|23|24|26) */
        /* JADX WARNING: Can't wrap try/catch for region: R(22:0|1|2|3|(2:5|6)|7|9|10|11|13|14|15|16|17|18|19|20|21|22|23|24|26) */
        /* JADX WARNING: Can't wrap try/catch for region: R(23:0|1|2|3|5|6|7|9|10|11|13|14|15|16|17|18|19|20|21|22|23|24|26) */
        /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0039 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0043 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x004d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0058 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0063 */
        static {
            /*
                org.apache.commons.math3.geometry.partitioning.Side[] r0 = org.apache.commons.math3.geometry.partitioning.Side.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$commons$math3$geometry$partitioning$Side = r0
                r1 = 1
                org.apache.commons.math3.geometry.partitioning.Side r2 = org.apache.commons.math3.geometry.partitioning.Side.PLUS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$org$apache$commons$math3$geometry$partitioning$Side     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.commons.math3.geometry.partitioning.Side r3 = org.apache.commons.math3.geometry.partitioning.Side.MINUS     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$org$apache$commons$math3$geometry$partitioning$Side     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.commons.math3.geometry.partitioning.Side r4 = org.apache.commons.math3.geometry.partitioning.Side.BOTH     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor$Order[] r3 = org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor.Order.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$org$apache$commons$math3$geometry$partitioning$BSPTreeVisitor$Order = r3
                org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor$Order r4 = org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor.Order.PLUS_MINUS_SUB     // Catch:{ NoSuchFieldError -> 0x0039 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0039 }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0039 }
            L_0x0039:
                int[] r1 = $SwitchMap$org$apache$commons$math3$geometry$partitioning$BSPTreeVisitor$Order     // Catch:{ NoSuchFieldError -> 0x0043 }
                org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor$Order r3 = org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor.Order.PLUS_SUB_MINUS     // Catch:{ NoSuchFieldError -> 0x0043 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0043 }
                r1[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0043 }
            L_0x0043:
                int[] r0 = $SwitchMap$org$apache$commons$math3$geometry$partitioning$BSPTreeVisitor$Order     // Catch:{ NoSuchFieldError -> 0x004d }
                org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor$Order r1 = org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor.Order.MINUS_PLUS_SUB     // Catch:{ NoSuchFieldError -> 0x004d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004d }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004d }
            L_0x004d:
                int[] r0 = $SwitchMap$org$apache$commons$math3$geometry$partitioning$BSPTreeVisitor$Order     // Catch:{ NoSuchFieldError -> 0x0058 }
                org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor$Order r1 = org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor.Order.MINUS_SUB_PLUS     // Catch:{ NoSuchFieldError -> 0x0058 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0058 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0058 }
            L_0x0058:
                int[] r0 = $SwitchMap$org$apache$commons$math3$geometry$partitioning$BSPTreeVisitor$Order     // Catch:{ NoSuchFieldError -> 0x0063 }
                org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor$Order r1 = org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor.Order.SUB_PLUS_MINUS     // Catch:{ NoSuchFieldError -> 0x0063 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0063 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0063 }
            L_0x0063:
                int[] r0 = $SwitchMap$org$apache$commons$math3$geometry$partitioning$BSPTreeVisitor$Order     // Catch:{ NoSuchFieldError -> 0x006e }
                org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor$Order r1 = org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor.Order.SUB_MINUS_PLUS     // Catch:{ NoSuchFieldError -> 0x006e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006e }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006e }
            L_0x006e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.geometry.partitioning.BSPTree.AnonymousClass2.<clinit>():void");
        }
    }

    @Deprecated
    public void insertInTree(BSPTree<S> bSPTree, boolean z) {
        insertInTree(bSPTree, z, new VanishingCutHandler<S>() {
            public BSPTree<S> fixNode(BSPTree<S> bSPTree) {
                throw new MathIllegalStateException(LocalizedFormats.NULL_NOT_ALLOWED, new Object[0]);
            }
        });
    }

    public void insertInTree(BSPTree<S> bSPTree, boolean z, VanishingCutHandler<S> vanishingCutHandler) {
        this.parent = bSPTree;
        if (bSPTree != null) {
            if (z) {
                bSPTree.plus = this;
            } else {
                bSPTree.minus = this;
            }
        }
        if (this.cut != null) {
            BSPTree bSPTree2 = this;
            while (true) {
                BSPTree<S> bSPTree3 = bSPTree2.parent;
                if (bSPTree3 == null) {
                    break;
                }
                Hyperplane<S> hyperplane = bSPTree3.cut.getHyperplane();
                if (bSPTree2 == bSPTree2.parent.plus) {
                    this.cut = this.cut.split(hyperplane).getPlus();
                    this.plus.chopOffMinus(hyperplane, vanishingCutHandler);
                    this.minus.chopOffMinus(hyperplane, vanishingCutHandler);
                } else {
                    this.cut = this.cut.split(hyperplane).getMinus();
                    this.plus.chopOffPlus(hyperplane, vanishingCutHandler);
                    this.minus.chopOffPlus(hyperplane, vanishingCutHandler);
                }
                if (this.cut == null) {
                    BSPTree<S> fixNode = vanishingCutHandler.fixNode(this);
                    SubHyperplane<S> subHyperplane = fixNode.cut;
                    this.cut = subHyperplane;
                    this.plus = fixNode.plus;
                    this.minus = fixNode.minus;
                    this.attribute = fixNode.attribute;
                    if (subHyperplane == null) {
                        break;
                    }
                }
                bSPTree2 = bSPTree2.parent;
            }
            condense();
        }
    }

    public BSPTree<S> pruneAroundConvexCell(Object obj, Object obj2, Object obj3) {
        BSPTree<S> bSPTree;
        BSPTree<S> bSPTree2 = new BSPTree<>(obj);
        BSPTree bSPTree3 = this;
        while (true) {
            BSPTree<S> bSPTree4 = bSPTree3.parent;
            if (bSPTree4 == null) {
                return bSPTree2;
            }
            SubHyperplane<S> copySelf = bSPTree4.cut.copySelf();
            BSPTree bSPTree5 = new BSPTree(obj2);
            if (bSPTree3 == bSPTree3.parent.plus) {
                bSPTree = new BSPTree<>(copySelf, bSPTree2, bSPTree5, obj3);
            } else {
                bSPTree = new BSPTree<>(copySelf, bSPTree5, bSPTree2, obj3);
            }
            bSPTree2 = bSPTree;
            bSPTree3 = bSPTree3.parent;
        }
    }

    private void chopOffMinus(Hyperplane<S> hyperplane, VanishingCutHandler<S> vanishingCutHandler) {
        SubHyperplane<S> subHyperplane = this.cut;
        if (subHyperplane != null) {
            this.cut = subHyperplane.split(hyperplane).getPlus();
            this.plus.chopOffMinus(hyperplane, vanishingCutHandler);
            this.minus.chopOffMinus(hyperplane, vanishingCutHandler);
            if (this.cut == null) {
                BSPTree<S> fixNode = vanishingCutHandler.fixNode(this);
                this.cut = fixNode.cut;
                this.plus = fixNode.plus;
                this.minus = fixNode.minus;
                this.attribute = fixNode.attribute;
            }
        }
    }

    private void chopOffPlus(Hyperplane<S> hyperplane, VanishingCutHandler<S> vanishingCutHandler) {
        SubHyperplane<S> subHyperplane = this.cut;
        if (subHyperplane != null) {
            this.cut = subHyperplane.split(hyperplane).getMinus();
            this.plus.chopOffPlus(hyperplane, vanishingCutHandler);
            this.minus.chopOffPlus(hyperplane, vanishingCutHandler);
            if (this.cut == null) {
                BSPTree<S> fixNode = vanishingCutHandler.fixNode(this);
                this.cut = fixNode.cut;
                this.plus = fixNode.plus;
                this.minus = fixNode.minus;
                this.attribute = fixNode.attribute;
            }
        }
    }
}
