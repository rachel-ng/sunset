package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.collect.Maps;
import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
public class TreeBasedTable<R, C, V> extends StandardRowSortedTable<R, C, V> {
    private static final long serialVersionUID = 0;
    private final Comparator<? super C> columnComparator;

    public /* bridge */ /* synthetic */ Set cellSet() {
        return super.cellSet();
    }

    public /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    public /* bridge */ /* synthetic */ Map column(Object obj) {
        return super.column(obj);
    }

    public /* bridge */ /* synthetic */ Set columnKeySet() {
        return super.columnKeySet();
    }

    public /* bridge */ /* synthetic */ Map columnMap() {
        return super.columnMap();
    }

    public /* bridge */ /* synthetic */ boolean contains(@CheckForNull Object obj, @CheckForNull Object obj2) {
        return super.contains(obj, obj2);
    }

    public /* bridge */ /* synthetic */ boolean containsColumn(@CheckForNull Object obj) {
        return super.containsColumn(obj);
    }

    public /* bridge */ /* synthetic */ boolean containsRow(@CheckForNull Object obj) {
        return super.containsRow(obj);
    }

    public /* bridge */ /* synthetic */ boolean containsValue(@CheckForNull Object obj) {
        return super.containsValue(obj);
    }

    public /* bridge */ /* synthetic */ boolean equals(@CheckForNull Object obj) {
        return super.equals(obj);
    }

    @CheckForNull
    public /* bridge */ /* synthetic */ Object get(@CheckForNull Object obj, @CheckForNull Object obj2) {
        return super.get(obj, obj2);
    }

    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    @CheckForNull
    public /* bridge */ /* synthetic */ Object put(Object obj, Object obj2, Object obj3) {
        return super.put(obj, obj2, obj3);
    }

    public /* bridge */ /* synthetic */ void putAll(Table table) {
        super.putAll(table);
    }

    @CheckForNull
    public /* bridge */ /* synthetic */ Object remove(@CheckForNull Object obj, @CheckForNull Object obj2) {
        return super.remove(obj, obj2);
    }

    public /* bridge */ /* synthetic */ int size() {
        return super.size();
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public /* bridge */ /* synthetic */ Collection values() {
        return super.values();
    }

    private static class Factory<C, V> implements Supplier<TreeMap<C, V>>, Serializable {
        private static final long serialVersionUID = 0;
        final Comparator<? super C> comparator;

        Factory(Comparator<? super C> comparator2) {
            this.comparator = comparator2;
        }

        public TreeMap<C, V> get() {
            return new TreeMap<>(this.comparator);
        }
    }

    public static <R extends Comparable, C extends Comparable, V> TreeBasedTable<R, C, V> create() {
        return new TreeBasedTable<>(Ordering.natural(), Ordering.natural());
    }

    public static <R, C, V> TreeBasedTable<R, C, V> create(Comparator<? super R> comparator, Comparator<? super C> comparator2) {
        Preconditions.checkNotNull(comparator);
        Preconditions.checkNotNull(comparator2);
        return new TreeBasedTable<>(comparator, comparator2);
    }

    public static <R, C, V> TreeBasedTable<R, C, V> create(TreeBasedTable<R, C, ? extends V> treeBasedTable) {
        TreeBasedTable<R, C, V> treeBasedTable2 = new TreeBasedTable<>(treeBasedTable.rowComparator(), treeBasedTable.columnComparator());
        treeBasedTable2.putAll(treeBasedTable);
        return treeBasedTable2;
    }

    TreeBasedTable(Comparator<? super R> comparator, Comparator<? super C> comparator2) {
        super(new TreeMap(comparator), new Factory(comparator2));
        this.columnComparator = comparator2;
    }

    @Deprecated
    public Comparator<? super R> rowComparator() {
        return (Comparator) Objects.requireNonNull(rowKeySet().comparator());
    }

    @Deprecated
    public Comparator<? super C> columnComparator() {
        return this.columnComparator;
    }

    public SortedMap<C, V> row(R r) {
        return new TreeRow(this, r);
    }

    private class TreeRow extends StandardTable<R, C, V>.Row implements SortedMap<C, V> {
        @CheckForNull
        final C lowerBound;
        @CheckForNull
        final C upperBound;
        @CheckForNull
        transient SortedMap<C, V> wholeRow;

        TreeRow(TreeBasedTable treeBasedTable, R r) {
            this(r, (Object) null, (Object) null);
        }

        TreeRow(R r, @CheckForNull C c2, @CheckForNull C c3) {
            super(r);
            this.lowerBound = c2;
            this.upperBound = c3;
            Preconditions.checkArgument(c2 == null || c3 == null || compare(c2, c3) <= 0);
        }

        public SortedSet<C> keySet() {
            return new Maps.SortedKeySet(this);
        }

        public Comparator<? super C> comparator() {
            return TreeBasedTable.this.columnComparator();
        }

        /* access modifiers changed from: package-private */
        public int compare(Object obj, Object obj2) {
            return comparator().compare(obj, obj2);
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:1:0x0002, code lost:
            r0 = r1.lowerBound;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:5:0x000c, code lost:
            r0 = r1.upperBound;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean rangeContains(@javax.annotation.CheckForNull java.lang.Object r2) {
            /*
                r1 = this;
                if (r2 == 0) goto L_0x0018
                C r0 = r1.lowerBound
                if (r0 == 0) goto L_0x000c
                int r0 = r1.compare(r0, r2)
                if (r0 > 0) goto L_0x0018
            L_0x000c:
                C r0 = r1.upperBound
                if (r0 == 0) goto L_0x0016
                int r2 = r1.compare(r0, r2)
                if (r2 <= 0) goto L_0x0018
            L_0x0016:
                r2 = 1
                goto L_0x0019
            L_0x0018:
                r2 = 0
            L_0x0019:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.TreeBasedTable.TreeRow.rangeContains(java.lang.Object):boolean");
        }

        public SortedMap<C, V> subMap(C c2, C c3) {
            Preconditions.checkArgument(rangeContains(Preconditions.checkNotNull(c2)) && rangeContains(Preconditions.checkNotNull(c3)));
            return new TreeRow(this.rowKey, c2, c3);
        }

        public SortedMap<C, V> headMap(C c2) {
            Preconditions.checkArgument(rangeContains(Preconditions.checkNotNull(c2)));
            return new TreeRow(this.rowKey, this.lowerBound, c2);
        }

        public SortedMap<C, V> tailMap(C c2) {
            Preconditions.checkArgument(rangeContains(Preconditions.checkNotNull(c2)));
            return new TreeRow(this.rowKey, c2, this.upperBound);
        }

        public C firstKey() {
            updateBackingRowMapField();
            if (this.backingRowMap != null) {
                return ((SortedMap) this.backingRowMap).firstKey();
            }
            throw new NoSuchElementException();
        }

        public C lastKey() {
            updateBackingRowMapField();
            if (this.backingRowMap != null) {
                return ((SortedMap) this.backingRowMap).lastKey();
            }
            throw new NoSuchElementException();
        }

        /* access modifiers changed from: package-private */
        public void updateWholeRowField() {
            SortedMap<C, V> sortedMap = this.wholeRow;
            if (sortedMap == null || (sortedMap.isEmpty() && TreeBasedTable.this.backingMap.containsKey(this.rowKey))) {
                this.wholeRow = (SortedMap) TreeBasedTable.this.backingMap.get(this.rowKey);
            }
        }

        /* access modifiers changed from: package-private */
        @CheckForNull
        public SortedMap<C, V> computeBackingRowMap() {
            updateWholeRowField();
            SortedMap<C, V> sortedMap = this.wholeRow;
            if (sortedMap == null) {
                return null;
            }
            C c2 = this.lowerBound;
            if (c2 != null) {
                sortedMap = sortedMap.tailMap(c2);
            }
            C c3 = this.upperBound;
            return c3 != null ? sortedMap.headMap(c3) : sortedMap;
        }

        /* access modifiers changed from: package-private */
        public void maintainEmptyInvariant() {
            updateWholeRowField();
            SortedMap<C, V> sortedMap = this.wholeRow;
            if (sortedMap != null && sortedMap.isEmpty()) {
                TreeBasedTable.this.backingMap.remove(this.rowKey);
                this.wholeRow = null;
                this.backingRowMap = null;
            }
        }

        public boolean containsKey(@CheckForNull Object obj) {
            return rangeContains(obj) && super.containsKey(obj);
        }

        @CheckForNull
        public V put(C c2, V v) {
            Preconditions.checkArgument(rangeContains(Preconditions.checkNotNull(c2)));
            return super.put(c2, v);
        }
    }

    public SortedSet<R> rowKeySet() {
        return super.rowKeySet();
    }

    public SortedMap<R, Map<C, V>> rowMap() {
        return super.rowMap();
    }

    /* access modifiers changed from: package-private */
    public Iterator<C> createColumnKeyIterator() {
        final Comparator columnComparator2 = columnComparator();
        final UnmodifiableIterator mergeSorted = Iterators.mergeSorted(Iterables.transform(this.backingMap.values(), new TreeBasedTable$$ExternalSyntheticLambda0()), columnComparator2);
        return new AbstractIterator<C>(this) {
            @CheckForNull
            C lastValue;

            /* access modifiers changed from: protected */
            /* JADX WARNING: Removed duplicated region for block: B:2:0x0008  */
            @javax.annotation.CheckForNull
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public C computeNext() {
                /*
                    r3 = this;
                L_0x0000:
                    java.util.Iterator r0 = r1
                    boolean r0 = r0.hasNext()
                    if (r0 == 0) goto L_0x001e
                    java.util.Iterator r0 = r1
                    java.lang.Object r0 = r0.next()
                    C r1 = r3.lastValue
                    if (r1 == 0) goto L_0x001b
                    java.util.Comparator r2 = r0
                    int r1 = r2.compare(r0, r1)
                    if (r1 != 0) goto L_0x001b
                    goto L_0x0000
                L_0x001b:
                    r3.lastValue = r0
                    return r0
                L_0x001e:
                    r0 = 0
                    r3.lastValue = r0
                    java.lang.Object r0 = r3.endOfData()
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.TreeBasedTable.AnonymousClass1.computeNext():java.lang.Object");
            }
        };
    }
}
