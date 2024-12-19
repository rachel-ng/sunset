package org.apache.commons.math3.genetics;

import java.util.Iterator;

public abstract class Chromosome implements Comparable<Chromosome>, Fitness {
    private static final double NO_FITNESS = Double.NEGATIVE_INFINITY;
    private double fitness = Double.NEGATIVE_INFINITY;

    /* access modifiers changed from: protected */
    public boolean isSame(Chromosome chromosome) {
        return false;
    }

    public double getFitness() {
        if (this.fitness == Double.NEGATIVE_INFINITY) {
            this.fitness = fitness();
        }
        return this.fitness;
    }

    public int compareTo(Chromosome chromosome) {
        return Double.compare(getFitness(), chromosome.getFitness());
    }

    /* access modifiers changed from: protected */
    public Chromosome findSameChromosome(Population population) {
        Iterator it = population.iterator();
        while (it.hasNext()) {
            Chromosome chromosome = (Chromosome) it.next();
            if (isSame(chromosome)) {
                return chromosome;
            }
        }
        return null;
    }

    public void searchForFitnessUpdate(Population population) {
        Chromosome findSameChromosome = findSameChromosome(population);
        if (findSameChromosome != null) {
            this.fitness = findSameChromosome.getFitness();
        }
    }
}
