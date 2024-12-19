package org.apache.commons.math3.geometry.partitioning;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.math3.geometry.Space;

public class NodesSet<S extends Space> implements Iterable<BSPTree<S>> {
    private List<BSPTree<S>> list = new ArrayList();

    public void add(BSPTree<S> bSPTree) {
        for (BSPTree<S> bSPTree2 : this.list) {
            if (bSPTree == bSPTree2) {
                return;
            }
        }
        this.list.add(bSPTree);
    }

    public void addAll(Iterable<BSPTree<S>> iterable) {
        for (BSPTree<S> add : iterable) {
            add(add);
        }
    }

    public Iterator<BSPTree<S>> iterator() {
        return this.list.iterator();
    }
}
