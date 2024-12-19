package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import java.util.Map;

@ElementTypesAreNonnullByDefault
class SingletonImmutableTable<R, C, V> extends ImmutableTable<R, C, V> {
    final C singleColumnKey;
    final R singleRowKey;
    final V singleValue;

    public int size() {
        return 1;
    }

    SingletonImmutableTable(R r, C c2, V v) {
        this.singleRowKey = Preconditions.checkNotNull(r);
        this.singleColumnKey = Preconditions.checkNotNull(c2);
        this.singleValue = Preconditions.checkNotNull(v);
    }

    SingletonImmutableTable(Table.Cell<R, C, V> cell) {
        this(cell.getRowKey(), cell.getColumnKey(), cell.getValue());
    }

    public ImmutableMap<R, V> column(C c2) {
        Preconditions.checkNotNull(c2);
        if (containsColumn(c2)) {
            return ImmutableMap.of(this.singleRowKey, this.singleValue);
        }
        return ImmutableMap.of();
    }

    public ImmutableMap<C, Map<R, V>> columnMap() {
        return ImmutableMap.of(this.singleColumnKey, ImmutableMap.of(this.singleRowKey, this.singleValue));
    }

    public ImmutableMap<R, Map<C, V>> rowMap() {
        return ImmutableMap.of(this.singleRowKey, ImmutableMap.of(this.singleColumnKey, this.singleValue));
    }

    /* access modifiers changed from: package-private */
    public ImmutableSet<Table.Cell<R, C, V>> createCellSet() {
        return ImmutableSet.of(cellOf(this.singleRowKey, this.singleColumnKey, this.singleValue));
    }

    /* access modifiers changed from: package-private */
    public ImmutableCollection<V> createValues() {
        return ImmutableSet.of(this.singleValue);
    }

    /* access modifiers changed from: package-private */
    public ImmutableTable.SerializedForm createSerializedForm() {
        return ImmutableTable.SerializedForm.create(this, new int[]{0}, new int[]{0});
    }
}
