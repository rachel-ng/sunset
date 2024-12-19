package com.google.common.collect;

import java.util.SortedSet;

@ElementTypesAreNonnullByDefault
interface SortedMultisetBridge<E> extends Multiset<E> {

    /* renamed from: com.google.common.collect.SortedMultisetBridge$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
    }

    SortedSet<E> elementSet();
}
