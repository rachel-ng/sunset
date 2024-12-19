package org.apache.commons.math3.stat;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathUtils;

public class Frequency implements Serializable {
    private static final long serialVersionUID = -3845586908418844111L;
    private final TreeMap<Comparable<?>, Long> freqTable;

    public Frequency() {
        this.freqTable = new TreeMap<>();
    }

    public Frequency(Comparator<?> comparator) {
        this.freqTable = new TreeMap<>(comparator);
    }

    public String toString() {
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        StringBuilder sb = new StringBuilder("Value \t Freq. \t Pct. \t Cum Pct. \n");
        for (Comparable next : this.freqTable.keySet()) {
            sb.append(next);
            sb.append(9);
            sb.append(getCount((Comparable<?>) next));
            sb.append(9);
            sb.append(percentInstance.format(getPct((Comparable<?>) next)));
            sb.append(9);
            sb.append(percentInstance.format(getCumPct((Comparable<?>) next)));
            sb.append(10);
        }
        return sb.toString();
    }

    public void addValue(Comparable<?> comparable) throws MathIllegalArgumentException {
        incrementValue(comparable, 1);
    }

    public void addValue(int i) throws MathIllegalArgumentException {
        addValue((Comparable<?>) Long.valueOf((long) i));
    }

    public void addValue(long j) throws MathIllegalArgumentException {
        addValue((Comparable<?>) Long.valueOf(j));
    }

    public void addValue(char c2) throws MathIllegalArgumentException {
        addValue((Comparable<?>) Character.valueOf(c2));
    }

    public void incrementValue(Comparable<?> comparable, long j) throws MathIllegalArgumentException {
        Comparable<?> valueOf = comparable instanceof Integer ? Long.valueOf(((Integer) comparable).longValue()) : comparable;
        try {
            Long l = this.freqTable.get(valueOf);
            if (l == null) {
                this.freqTable.put(valueOf, Long.valueOf(j));
            } else {
                this.freqTable.put(valueOf, Long.valueOf(l.longValue() + j));
            }
        } catch (ClassCastException unused) {
            throw new MathIllegalArgumentException(LocalizedFormats.INSTANCES_NOT_COMPARABLE_TO_EXISTING_VALUES, comparable.getClass().getName());
        }
    }

    public void incrementValue(int i, long j) throws MathIllegalArgumentException {
        incrementValue((Comparable<?>) Long.valueOf((long) i), j);
    }

    public void incrementValue(long j, long j2) throws MathIllegalArgumentException {
        incrementValue((Comparable<?>) Long.valueOf(j), j2);
    }

    public void incrementValue(char c2, long j) throws MathIllegalArgumentException {
        incrementValue((Comparable<?>) Character.valueOf(c2), j);
    }

    public void clear() {
        this.freqTable.clear();
    }

    public Iterator<Comparable<?>> valuesIterator() {
        return this.freqTable.keySet().iterator();
    }

    public Iterator<Map.Entry<Comparable<?>, Long>> entrySetIterator() {
        return this.freqTable.entrySet().iterator();
    }

    public long getSumFreq() {
        long j = 0;
        for (Long longValue : this.freqTable.values()) {
            j += longValue.longValue();
        }
        return j;
    }

    public long getCount(Comparable<?> comparable) {
        if (comparable instanceof Integer) {
            return getCount(((Integer) comparable).longValue());
        }
        try {
            Long l = this.freqTable.get(comparable);
            if (l != null) {
                return l.longValue();
            }
            return 0;
        } catch (ClassCastException unused) {
            return 0;
        }
    }

    public long getCount(int i) {
        return getCount((Comparable<?>) Long.valueOf((long) i));
    }

    public long getCount(long j) {
        return getCount((Comparable<?>) Long.valueOf(j));
    }

    public long getCount(char c2) {
        return getCount((Comparable<?>) Character.valueOf(c2));
    }

    public int getUniqueCount() {
        return this.freqTable.keySet().size();
    }

    public double getPct(Comparable<?> comparable) {
        long sumFreq = getSumFreq();
        if (sumFreq == 0) {
            return Double.NaN;
        }
        return ((double) getCount(comparable)) / ((double) sumFreq);
    }

    public double getPct(int i) {
        return getPct((Comparable<?>) Long.valueOf((long) i));
    }

    public double getPct(long j) {
        return getPct((Comparable<?>) Long.valueOf(j));
    }

    public double getPct(char c2) {
        return getPct((Comparable<?>) Character.valueOf(c2));
    }

    public long getCumFreq(Comparable<?> comparable) {
        if (getSumFreq() == 0) {
            return 0;
        }
        if (comparable instanceof Integer) {
            return getCumFreq(((Integer) comparable).longValue());
        }
        Comparator<? super Comparable<?>> comparator = this.freqTable.comparator();
        if (comparator == null) {
            comparator = new NaturalComparator<>();
        }
        try {
            Long l = this.freqTable.get(comparable);
            long longValue = l != null ? l.longValue() : 0;
            if (comparator.compare(comparable, this.freqTable.firstKey()) < 0) {
                return 0;
            }
            if (comparator.compare(comparable, this.freqTable.lastKey()) >= 0) {
                return getSumFreq();
            }
            Iterator<Comparable<?>> valuesIterator = valuesIterator();
            while (valuesIterator.hasNext()) {
                Comparable next = valuesIterator.next();
                if (comparator.compare(comparable, next) <= 0) {
                    break;
                }
                longValue += getCount((Comparable<?>) next);
            }
            return longValue;
        } catch (ClassCastException unused) {
            return 0;
        }
    }

    public long getCumFreq(int i) {
        return getCumFreq((Comparable<?>) Long.valueOf((long) i));
    }

    public long getCumFreq(long j) {
        return getCumFreq((Comparable<?>) Long.valueOf(j));
    }

    public long getCumFreq(char c2) {
        return getCumFreq((Comparable<?>) Character.valueOf(c2));
    }

    public double getCumPct(Comparable<?> comparable) {
        long sumFreq = getSumFreq();
        if (sumFreq == 0) {
            return Double.NaN;
        }
        return ((double) getCumFreq(comparable)) / ((double) sumFreq);
    }

    public double getCumPct(int i) {
        return getCumPct((Comparable<?>) Long.valueOf((long) i));
    }

    public double getCumPct(long j) {
        return getCumPct((Comparable<?>) Long.valueOf(j));
    }

    public double getCumPct(char c2) {
        return getCumPct((Comparable<?>) Character.valueOf(c2));
    }

    public List<Comparable<?>> getMode() {
        long j = 0;
        for (Long longValue : this.freqTable.values()) {
            long longValue2 = longValue.longValue();
            if (longValue2 > j) {
                j = longValue2;
            }
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry next : this.freqTable.entrySet()) {
            if (((Long) next.getValue()).longValue() == j) {
                arrayList.add(next.getKey());
            }
        }
        return arrayList;
    }

    public void merge(Frequency frequency) throws NullArgumentException {
        MathUtils.checkNotNull(frequency, LocalizedFormats.NULL_NOT_ALLOWED, new Object[0]);
        Iterator<Map.Entry<Comparable<?>, Long>> entrySetIterator = frequency.entrySetIterator();
        while (entrySetIterator.hasNext()) {
            Map.Entry next = entrySetIterator.next();
            incrementValue((Comparable<?>) (Comparable) next.getKey(), ((Long) next.getValue()).longValue());
        }
    }

    public void merge(Collection<Frequency> collection) throws NullArgumentException {
        MathUtils.checkNotNull(collection, LocalizedFormats.NULL_NOT_ALLOWED, new Object[0]);
        for (Frequency merge : collection) {
            merge(merge);
        }
    }

    private static class NaturalComparator<T extends Comparable<T>> implements Comparator<Comparable<T>>, Serializable {
        private static final long serialVersionUID = -3852193713161395148L;

        private NaturalComparator() {
        }

        public int compare(Comparable<T> comparable, Comparable<T> comparable2) {
            return comparable.compareTo(comparable2);
        }
    }

    public int hashCode() {
        TreeMap<Comparable<?>, Long> treeMap = this.freqTable;
        return 31 + (treeMap == null ? 0 : treeMap.hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Frequency)) {
            return false;
        }
        Frequency frequency = (Frequency) obj;
        TreeMap<Comparable<?>, Long> treeMap = this.freqTable;
        if (treeMap == null) {
            if (frequency.freqTable != null) {
                return false;
            }
        } else if (!treeMap.equals(frequency.freqTable)) {
            return false;
        }
        return true;
    }
}
