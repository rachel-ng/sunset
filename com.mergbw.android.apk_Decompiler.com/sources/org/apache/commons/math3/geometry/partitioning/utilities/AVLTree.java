package org.apache.commons.math3.geometry.partitioning.utilities;

import java.lang.Comparable;

@Deprecated
public class AVLTree<T extends Comparable<T>> {
    /* access modifiers changed from: private */

    /* renamed from: top  reason: collision with root package name */
    public AVLTree<T>.Node f74top = null;

    private enum Skew {
        LEFT_HIGH,
        RIGHT_HIGH,
        BALANCED
    }

    public void insert(T t) {
        if (t != null) {
            AVLTree<T>.Node node = this.f74top;
            if (node == null) {
                this.f74top = new Node(t, (AVLTree<T>.Node) null);
            } else {
                node.insert(t);
            }
        }
    }

    public boolean delete(T t) {
        if (t != null) {
            AVLTree<T>.Node notSmaller = getNotSmaller(t);
            while (notSmaller != null) {
                if (notSmaller.element == t) {
                    notSmaller.delete();
                    return true;
                } else if (notSmaller.element.compareTo(t) > 0) {
                    return false;
                } else {
                    notSmaller = notSmaller.getNext();
                }
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return this.f74top == null;
    }

    public int size() {
        AVLTree<T>.Node node = this.f74top;
        if (node == null) {
            return 0;
        }
        return node.size();
    }

    public AVLTree<T>.Node getSmallest() {
        AVLTree<T>.Node node = this.f74top;
        if (node == null) {
            return null;
        }
        return node.getSmallest();
    }

    public AVLTree<T>.Node getLargest() {
        AVLTree<T>.Node node = this.f74top;
        if (node == null) {
            return null;
        }
        return node.getLargest();
    }

    public AVLTree<T>.Node getNotSmaller(T t) {
        AVLTree<T>.Node node = this.f74top;
        AVLTree<T>.Node node2 = null;
        while (node != null) {
            if (node.element.compareTo(t) < 0) {
                if (node.right == null) {
                    return node2;
                }
                node = node.right;
            } else if (node.left == null) {
                return node;
            } else {
                node2 = node;
                node = node.left;
            }
        }
        return null;
    }

    public AVLTree<T>.Node getNotLarger(T t) {
        AVLTree<T>.Node node = this.f74top;
        AVLTree<T>.Node node2 = null;
        while (node != null) {
            if (node.element.compareTo(t) > 0) {
                if (node.left == null) {
                    return node2;
                }
                node = node.left;
            } else if (node.right == null) {
                return node;
            } else {
                node2 = node;
                node = node.right;
            }
        }
        return null;
    }

    public class Node {
        /* access modifiers changed from: private */
        public T element;
        /* access modifiers changed from: private */
        public AVLTree<T>.Node left = null;
        private AVLTree<T>.Node parent;
        /* access modifiers changed from: private */
        public AVLTree<T>.Node right = null;
        private Skew skew;

        Node(T t, AVLTree<T>.Node node) {
            this.element = t;
            this.parent = node;
            this.skew = Skew.BALANCED;
        }

        public T getElement() {
            return this.element;
        }

        /* access modifiers changed from: package-private */
        public int size() {
            AVLTree<T>.Node node = this.left;
            int i = 0;
            int size = (node == null ? 0 : node.size()) + 1;
            AVLTree<T>.Node node2 = this.right;
            if (node2 != null) {
                i = node2.size();
            }
            return size + i;
        }

        /* access modifiers changed from: package-private */
        public AVLTree<T>.Node getSmallest() {
            AVLTree<T>.Node node = this;
            while (true) {
                AVLTree<T>.Node node2 = node.left;
                if (node2 == null) {
                    return node;
                }
                node = node2;
            }
        }

        /* access modifiers changed from: package-private */
        public AVLTree<T>.Node getLargest() {
            AVLTree<T>.Node node = this;
            while (true) {
                AVLTree<T>.Node node2 = node.right;
                if (node2 == null) {
                    return node;
                }
                node = node2;
            }
        }

        public AVLTree<T>.Node getPrevious() {
            AVLTree<T>.Node largest;
            AVLTree<T>.Node node = this.left;
            if (node != null && (largest = node.getLargest()) != null) {
                return largest;
            }
            AVLTree<T>.Node node2 = this;
            while (true) {
                AVLTree<T>.Node node3 = node2.parent;
                if (node3 == null) {
                    return null;
                }
                if (node2 != node3.left) {
                    return node3;
                }
                node2 = node3;
            }
        }

        public AVLTree<T>.Node getNext() {
            AVLTree<T>.Node smallest;
            AVLTree<T>.Node node = this.right;
            if (node != null && (smallest = node.getSmallest()) != null) {
                return smallest;
            }
            AVLTree<T>.Node node2 = this;
            while (true) {
                AVLTree<T>.Node node3 = node2.parent;
                if (node3 == null) {
                    return null;
                }
                if (node2 != node3.right) {
                    return node3;
                }
                node2 = node3;
            }
        }

        /* access modifiers changed from: package-private */
        public boolean insert(T t) {
            if (t.compareTo(this.element) < 0) {
                AVLTree<T>.Node node = this.left;
                if (node == null) {
                    this.left = new Node(t, this);
                    return rebalanceLeftGrown();
                } else if (node.insert(t)) {
                    return rebalanceLeftGrown();
                } else {
                    return false;
                }
            } else {
                AVLTree<T>.Node node2 = this.right;
                if (node2 == null) {
                    this.right = new Node(t, this);
                    return rebalanceRightGrown();
                } else if (node2.insert(t)) {
                    return rebalanceRightGrown();
                } else {
                    return false;
                }
            }
        }

        public void delete() {
            AVLTree<T>.Node node;
            boolean z;
            AVLTree<T>.Node node2;
            AVLTree<T>.Node node3 = this.parent;
            if (node3 == null && this.left == null && this.right == null) {
                this.element = null;
                Node unused = AVLTree.this.f74top = null;
                return;
            }
            AVLTree<T>.Node node4 = this.left;
            if (node4 == null && this.right == null) {
                this.element = null;
                node = null;
                z = this == node3.left;
                node2 = this;
            } else {
                node2 = node4 != null ? node4.getLargest() : this.right.getSmallest();
                this.element = node2.element;
                z = node2 == node2.parent.left;
                node = node2.left;
                if (node == null) {
                    node = node2.right;
                }
            }
            AVLTree<T>.Node node5 = node2.parent;
            if (z) {
                node5.left = node;
            } else {
                node5.right = node;
            }
            if (node != null) {
                node.parent = node5;
            }
            while (true) {
                if (z) {
                    if (!node5.rebalanceLeftShrunk()) {
                        return;
                    }
                } else if (!node5.rebalanceRightShrunk()) {
                    return;
                }
                AVLTree<T>.Node node6 = node5.parent;
                if (node6 != null) {
                    AVLTree<T>.Node node7 = node6;
                    z = node5 == node6.left;
                    node5 = node7;
                } else {
                    return;
                }
            }
        }

        private boolean rebalanceLeftGrown() {
            int i = AnonymousClass1.$SwitchMap$org$apache$commons$math3$geometry$partitioning$utilities$AVLTree$Skew[this.skew.ordinal()];
            if (i == 1) {
                if (this.left.skew == Skew.LEFT_HIGH) {
                    rotateCW();
                    this.skew = Skew.BALANCED;
                    this.right.skew = Skew.BALANCED;
                } else {
                    AVLTree<T>.Node node = this.left;
                    Skew skew2 = node.right.skew;
                    node.rotateCCW();
                    rotateCW();
                    int i2 = AnonymousClass1.$SwitchMap$org$apache$commons$math3$geometry$partitioning$utilities$AVLTree$Skew[skew2.ordinal()];
                    if (i2 == 1) {
                        this.left.skew = Skew.BALANCED;
                        this.right.skew = Skew.RIGHT_HIGH;
                    } else if (i2 != 2) {
                        this.left.skew = Skew.BALANCED;
                        this.right.skew = Skew.BALANCED;
                    } else {
                        this.left.skew = Skew.LEFT_HIGH;
                        this.right.skew = Skew.BALANCED;
                    }
                    this.skew = Skew.BALANCED;
                }
                return false;
            } else if (i != 2) {
                this.skew = Skew.LEFT_HIGH;
                return true;
            } else {
                this.skew = Skew.BALANCED;
                return false;
            }
        }

        private boolean rebalanceRightGrown() {
            int i = AnonymousClass1.$SwitchMap$org$apache$commons$math3$geometry$partitioning$utilities$AVLTree$Skew[this.skew.ordinal()];
            if (i == 1) {
                this.skew = Skew.BALANCED;
                return false;
            } else if (i != 2) {
                this.skew = Skew.RIGHT_HIGH;
                return true;
            } else {
                if (this.right.skew == Skew.RIGHT_HIGH) {
                    rotateCCW();
                    this.skew = Skew.BALANCED;
                    this.left.skew = Skew.BALANCED;
                } else {
                    AVLTree<T>.Node node = this.right;
                    Skew skew2 = node.left.skew;
                    node.rotateCW();
                    rotateCCW();
                    int i2 = AnonymousClass1.$SwitchMap$org$apache$commons$math3$geometry$partitioning$utilities$AVLTree$Skew[skew2.ordinal()];
                    if (i2 == 1) {
                        this.left.skew = Skew.BALANCED;
                        this.right.skew = Skew.RIGHT_HIGH;
                    } else if (i2 != 2) {
                        this.left.skew = Skew.BALANCED;
                        this.right.skew = Skew.BALANCED;
                    } else {
                        this.left.skew = Skew.LEFT_HIGH;
                        this.right.skew = Skew.BALANCED;
                    }
                    this.skew = Skew.BALANCED;
                }
                return false;
            }
        }

        private boolean rebalanceLeftShrunk() {
            int i = AnonymousClass1.$SwitchMap$org$apache$commons$math3$geometry$partitioning$utilities$AVLTree$Skew[this.skew.ordinal()];
            if (i == 1) {
                this.skew = Skew.BALANCED;
                return true;
            } else if (i != 2) {
                this.skew = Skew.RIGHT_HIGH;
                return false;
            } else if (this.right.skew == Skew.RIGHT_HIGH) {
                rotateCCW();
                this.skew = Skew.BALANCED;
                this.left.skew = Skew.BALANCED;
                return true;
            } else if (this.right.skew == Skew.BALANCED) {
                rotateCCW();
                this.skew = Skew.LEFT_HIGH;
                this.left.skew = Skew.RIGHT_HIGH;
                return false;
            } else {
                AVLTree<T>.Node node = this.right;
                Skew skew2 = node.left.skew;
                node.rotateCW();
                rotateCCW();
                int i2 = AnonymousClass1.$SwitchMap$org$apache$commons$math3$geometry$partitioning$utilities$AVLTree$Skew[skew2.ordinal()];
                if (i2 == 1) {
                    this.left.skew = Skew.BALANCED;
                    this.right.skew = Skew.RIGHT_HIGH;
                } else if (i2 != 2) {
                    this.left.skew = Skew.BALANCED;
                    this.right.skew = Skew.BALANCED;
                } else {
                    this.left.skew = Skew.LEFT_HIGH;
                    this.right.skew = Skew.BALANCED;
                }
                this.skew = Skew.BALANCED;
                return true;
            }
        }

        private boolean rebalanceRightShrunk() {
            int i = AnonymousClass1.$SwitchMap$org$apache$commons$math3$geometry$partitioning$utilities$AVLTree$Skew[this.skew.ordinal()];
            if (i != 1) {
                if (i != 2) {
                    this.skew = Skew.LEFT_HIGH;
                    return false;
                }
                this.skew = Skew.BALANCED;
                return true;
            } else if (this.left.skew == Skew.LEFT_HIGH) {
                rotateCW();
                this.skew = Skew.BALANCED;
                this.right.skew = Skew.BALANCED;
                return true;
            } else if (this.left.skew == Skew.BALANCED) {
                rotateCW();
                this.skew = Skew.RIGHT_HIGH;
                this.right.skew = Skew.LEFT_HIGH;
                return false;
            } else {
                AVLTree<T>.Node node = this.left;
                Skew skew2 = node.right.skew;
                node.rotateCCW();
                rotateCW();
                int i2 = AnonymousClass1.$SwitchMap$org$apache$commons$math3$geometry$partitioning$utilities$AVLTree$Skew[skew2.ordinal()];
                if (i2 == 1) {
                    this.left.skew = Skew.BALANCED;
                    this.right.skew = Skew.RIGHT_HIGH;
                } else if (i2 != 2) {
                    this.left.skew = Skew.BALANCED;
                    this.right.skew = Skew.BALANCED;
                } else {
                    this.left.skew = Skew.LEFT_HIGH;
                    this.right.skew = Skew.BALANCED;
                }
                this.skew = Skew.BALANCED;
                return true;
            }
        }

        private void rotateCW() {
            T t = this.element;
            AVLTree<T>.Node node = this.left;
            this.element = node.element;
            node.element = t;
            this.left = node.left;
            node.left = node.right;
            node.right = this.right;
            this.right = node;
            AVLTree<T>.Node node2 = this.left;
            if (node2 != null) {
                node2.parent = this;
            }
            AVLTree<T>.Node node3 = node.right;
            if (node3 != null) {
                node3.parent = node;
            }
        }

        private void rotateCCW() {
            T t = this.element;
            AVLTree<T>.Node node = this.right;
            this.element = node.element;
            node.element = t;
            this.right = node.right;
            node.right = node.left;
            node.left = this.left;
            this.left = node;
            AVLTree<T>.Node node2 = this.right;
            if (node2 != null) {
                node2.parent = this;
            }
            AVLTree<T>.Node node3 = node.left;
            if (node3 != null) {
                node3.parent = node;
            }
        }
    }

    /* renamed from: org.apache.commons.math3.geometry.partitioning.utilities.AVLTree$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$math3$geometry$partitioning$utilities$AVLTree$Skew;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                org.apache.commons.math3.geometry.partitioning.utilities.AVLTree$Skew[] r0 = org.apache.commons.math3.geometry.partitioning.utilities.AVLTree.Skew.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$commons$math3$geometry$partitioning$utilities$AVLTree$Skew = r0
                org.apache.commons.math3.geometry.partitioning.utilities.AVLTree$Skew r1 = org.apache.commons.math3.geometry.partitioning.utilities.AVLTree.Skew.LEFT_HIGH     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$commons$math3$geometry$partitioning$utilities$AVLTree$Skew     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.commons.math3.geometry.partitioning.utilities.AVLTree$Skew r1 = org.apache.commons.math3.geometry.partitioning.utilities.AVLTree.Skew.RIGHT_HIGH     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.geometry.partitioning.utilities.AVLTree.AnonymousClass1.<clinit>():void");
        }
    }
}
