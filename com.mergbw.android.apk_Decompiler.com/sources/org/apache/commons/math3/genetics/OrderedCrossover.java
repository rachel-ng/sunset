package org.apache.commons.math3.genetics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.util.FastMath;

public class OrderedCrossover<T> implements CrossoverPolicy {
    public ChromosomePair crossover(Chromosome chromosome, Chromosome chromosome2) throws DimensionMismatchException, MathIllegalArgumentException {
        if ((chromosome instanceof AbstractListChromosome) && (chromosome2 instanceof AbstractListChromosome)) {
            return mate((AbstractListChromosome) chromosome, (AbstractListChromosome) chromosome2);
        }
        throw new MathIllegalArgumentException(LocalizedFormats.INVALID_FIXED_LENGTH_CHROMOSOME, new Object[0]);
    }

    /* access modifiers changed from: protected */
    public ChromosomePair mate(AbstractListChromosome<T> abstractListChromosome, AbstractListChromosome<T> abstractListChromosome2) throws DimensionMismatchException {
        int nextInt;
        int length = abstractListChromosome.getLength();
        if (length == abstractListChromosome2.getLength()) {
            List<T> representation = abstractListChromosome.getRepresentation();
            List<T> representation2 = abstractListChromosome2.getRepresentation();
            ArrayList arrayList = new ArrayList(length);
            ArrayList arrayList2 = new ArrayList(length);
            HashSet hashSet = new HashSet(length);
            HashSet hashSet2 = new HashSet(length);
            RandomGenerator randomGenerator = GeneticAlgorithm.getRandomGenerator();
            int nextInt2 = randomGenerator.nextInt(length);
            do {
                nextInt = randomGenerator.nextInt(length);
            } while (nextInt2 == nextInt);
            int min = FastMath.min(nextInt2, nextInt);
            int max = FastMath.max(nextInt2, nextInt);
            int i = max + 1;
            arrayList.addAll(representation.subList(min, i));
            hashSet.addAll(arrayList);
            arrayList2.addAll(representation2.subList(min, i));
            hashSet2.addAll(arrayList2);
            for (int i2 = 1; i2 <= length; i2++) {
                int i3 = (max + i2) % length;
                T t = representation.get(i3);
                T t2 = representation2.get(i3);
                if (!hashSet.contains(t2)) {
                    arrayList.add(t2);
                    hashSet.add(t2);
                }
                if (!hashSet2.contains(t)) {
                    arrayList2.add(t);
                    hashSet2.add(t);
                }
            }
            Collections.rotate(arrayList, min);
            Collections.rotate(arrayList2, min);
            return new ChromosomePair(abstractListChromosome.newFixedLengthChromosome(arrayList), abstractListChromosome2.newFixedLengthChromosome(arrayList2));
        }
        throw new DimensionMismatchException(abstractListChromosome2.getLength(), length);
    }
}
