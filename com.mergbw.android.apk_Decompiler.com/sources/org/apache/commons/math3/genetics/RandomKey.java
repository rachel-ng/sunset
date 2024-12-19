package org.apache.commons.math3.genetics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public abstract class RandomKey<T> extends AbstractListChromosome<Double> implements PermutationChromosome<T> {
    private final List<Integer> baseSeqPermutation;
    private final List<Double> sortedRepresentation;

    public RandomKey(List<Double> list) throws InvalidRepresentationException {
        super(list);
        ArrayList arrayList = new ArrayList(getRepresentation());
        Collections.sort(arrayList);
        List<Double> unmodifiableList = Collections.unmodifiableList(arrayList);
        this.sortedRepresentation = unmodifiableList;
        this.baseSeqPermutation = Collections.unmodifiableList(decodeGeneric(baseSequence(getLength()), getRepresentation(), unmodifiableList));
    }

    public RandomKey(Double[] dArr) throws InvalidRepresentationException {
        this((List<Double>) Arrays.asList(dArr));
    }

    public List<T> decode(List<T> list) {
        return decodeGeneric(list, getRepresentation(), this.sortedRepresentation);
    }

    private static <S> List<S> decodeGeneric(List<S> list, List<Double> list2, List<Double> list3) throws DimensionMismatchException {
        int size = list.size();
        if (list2.size() != size) {
            throw new DimensionMismatchException(list2.size(), size);
        } else if (list3.size() == size) {
            ArrayList arrayList = new ArrayList(list2);
            ArrayList arrayList2 = new ArrayList(size);
            for (int i = 0; i < size; i++) {
                int indexOf = arrayList.indexOf(list3.get(i));
                arrayList2.add(list.get(indexOf));
                arrayList.set(indexOf, (Object) null);
            }
            return arrayList2;
        } else {
            throw new DimensionMismatchException(list3.size(), size);
        }
    }

    /* access modifiers changed from: protected */
    public boolean isSame(Chromosome chromosome) {
        if (!(chromosome instanceof RandomKey)) {
            return false;
        }
        RandomKey randomKey = (RandomKey) chromosome;
        if (getLength() != randomKey.getLength()) {
            return false;
        }
        List<Integer> list = this.baseSeqPermutation;
        List<Integer> list2 = randomKey.baseSeqPermutation;
        for (int i = 0; i < getLength(); i++) {
            if (list.get(i) != list2.get(i)) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:3:0x000a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void checkValidity(java.util.List<java.lang.Double> r8) throws org.apache.commons.math3.genetics.InvalidRepresentationException {
        /*
            r7 = this;
            java.util.Iterator r8 = r8.iterator()
        L_0x0004:
            boolean r0 = r8.hasNext()
            if (r0 == 0) goto L_0x003d
            java.lang.Object r0 = r8.next()
            java.lang.Double r0 = (java.lang.Double) r0
            double r1 = r0.doubleValue()
            r3 = 0
            int r3 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r3 < 0) goto L_0x0021
            r3 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 > 0) goto L_0x0021
            goto L_0x0004
        L_0x0021:
            org.apache.commons.math3.genetics.InvalidRepresentationException r8 = new org.apache.commons.math3.genetics.InvalidRepresentationException
            org.apache.commons.math3.exception.util.LocalizedFormats r1 = org.apache.commons.math3.exception.util.LocalizedFormats.OUT_OF_RANGE_SIMPLE
            r2 = 0
            java.lang.Integer r3 = java.lang.Integer.valueOf(r2)
            r4 = 1
            java.lang.Integer r5 = java.lang.Integer.valueOf(r4)
            r6 = 3
            java.lang.Object[] r6 = new java.lang.Object[r6]
            r6[r2] = r0
            r6[r4] = r3
            r0 = 2
            r6[r0] = r5
            r8.<init>(r1, r6)
            throw r8
        L_0x003d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.genetics.RandomKey.checkValidity(java.util.List):void");
    }

    public static final List<Double> randomPermutation(int i) {
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            arrayList.add(Double.valueOf(GeneticAlgorithm.getRandomGenerator().nextDouble()));
        }
        return arrayList;
    }

    public static final List<Double> identityPermutation(int i) {
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            arrayList.add(Double.valueOf(((double) i2) / ((double) i)));
        }
        return arrayList;
    }

    public static <S> List<Double> comparatorPermutation(List<S> list, Comparator<S> comparator) {
        ArrayList arrayList = new ArrayList(list);
        Collections.sort(arrayList, comparator);
        return inducedPermutation(list, arrayList);
    }

    public static <S> List<Double> inducedPermutation(List<S> list, List<S> list2) throws DimensionMismatchException, MathIllegalArgumentException {
        if (list.size() == list2.size()) {
            int size = list.size();
            ArrayList arrayList = new ArrayList(list);
            Double[] dArr = new Double[size];
            int i = 0;
            while (i < size) {
                int indexOf = arrayList.indexOf(list2.get(i));
                if (indexOf != -1) {
                    dArr[indexOf] = Double.valueOf(((double) i) / ((double) size));
                    arrayList.set(indexOf, (Object) null);
                    i++;
                } else {
                    throw new MathIllegalArgumentException(LocalizedFormats.DIFFERENT_ORIG_AND_PERMUTED_DATA, new Object[0]);
                }
            }
            return Arrays.asList(dArr);
        }
        throw new DimensionMismatchException(list2.size(), list.size());
    }

    public String toString() {
        return String.format("(f=%s pi=(%s))", new Object[]{Double.valueOf(getFitness()), this.baseSeqPermutation});
    }

    private static List<Integer> baseSequence(int i) {
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            arrayList.add(Integer.valueOf(i2));
        }
        return arrayList;
    }
}
