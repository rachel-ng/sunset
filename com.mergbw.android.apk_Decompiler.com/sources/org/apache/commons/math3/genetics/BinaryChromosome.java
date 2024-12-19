package org.apache.commons.math3.genetics;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public abstract class BinaryChromosome extends AbstractListChromosome<Integer> {
    public BinaryChromosome(List<Integer> list) throws InvalidRepresentationException {
        super(list);
    }

    public BinaryChromosome(Integer[] numArr) throws InvalidRepresentationException {
        super((T[]) numArr);
    }

    /* access modifiers changed from: protected */
    public void checkValidity(List<Integer> list) throws InvalidRepresentationException {
        for (Integer next : list) {
            int intValue = next.intValue();
            if (intValue >= 0) {
                if (intValue > 1) {
                }
            }
            throw new InvalidRepresentationException(LocalizedFormats.INVALID_BINARY_DIGIT, next);
        }
    }

    public static List<Integer> randomBinaryRepresentation(int i) {
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            arrayList.add(Integer.valueOf(GeneticAlgorithm.getRandomGenerator().nextInt(2)));
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public boolean isSame(Chromosome chromosome) {
        if (!(chromosome instanceof BinaryChromosome)) {
            return false;
        }
        BinaryChromosome binaryChromosome = (BinaryChromosome) chromosome;
        if (getLength() != binaryChromosome.getLength()) {
            return false;
        }
        for (int i = 0; i < getRepresentation().size(); i++) {
            if (!((Integer) getRepresentation().get(i)).equals(binaryChromosome.getRepresentation().get(i))) {
                return false;
            }
        }
        return true;
    }
}
