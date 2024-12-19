package com.google.common.collect;

import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
public interface BiMap<K, V> extends Map<K, V> {

    /* renamed from: com.google.common.collect.BiMap$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
    }

    @CheckForNull
    V forcePut(@ParametricNullness K k, @ParametricNullness V v);

    BiMap<V, K> inverse();

    @CheckForNull
    V put(@ParametricNullness K k, @ParametricNullness V v);

    void putAll(Map<? extends K, ? extends V> map);

    Set<V> values();
}
