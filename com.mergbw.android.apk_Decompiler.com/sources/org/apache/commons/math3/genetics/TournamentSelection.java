package org.apache.commons.math3.genetics;

import java.util.ArrayList;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class TournamentSelection implements SelectionPolicy {
    private int arity;

    public TournamentSelection(int i) {
        this.arity = i;
    }

    public ChromosomePair select(Population population) throws MathIllegalArgumentException {
        ListPopulation listPopulation = (ListPopulation) population;
        return new ChromosomePair(tournament(listPopulation), tournament(listPopulation));
    }

    private Chromosome tournament(ListPopulation listPopulation) throws MathIllegalArgumentException {
        int populationSize = listPopulation.getPopulationSize();
        int i = this.arity;
        if (populationSize >= i) {
            AnonymousClass1 r0 = new ListPopulation(i) {
                public Population nextGeneration() {
                    return null;
                }
            };
            ArrayList arrayList = new ArrayList(listPopulation.getChromosomes());
            for (int i2 = 0; i2 < this.arity; i2++) {
                int nextInt = GeneticAlgorithm.getRandomGenerator().nextInt(arrayList.size());
                r0.addChromosome((Chromosome) arrayList.get(nextInt));
                arrayList.remove(nextInt);
            }
            return r0.getFittestChromosome();
        }
        throw new MathIllegalArgumentException(LocalizedFormats.TOO_LARGE_TOURNAMENT_ARITY, Integer.valueOf(this.arity), Integer.valueOf(listPopulation.getPopulationSize()));
    }

    public int getArity() {
        return this.arity;
    }

    public void setArity(int i) {
        this.arity = i;
    }
}
