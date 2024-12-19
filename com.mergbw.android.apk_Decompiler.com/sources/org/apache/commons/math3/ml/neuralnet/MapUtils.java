package org.apache.commons.math3.ml.neuralnet;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.HashMap;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D;
import org.apache.commons.math3.util.Pair;

public class MapUtils {
    private MapUtils() {
    }

    public static Neuron findBest(double[] dArr, Iterable<Neuron> iterable, DistanceMeasure distanceMeasure) {
        Neuron neuron = null;
        double d = Double.POSITIVE_INFINITY;
        for (Neuron next : iterable) {
            double compute = distanceMeasure.compute(next.getFeatures(), dArr);
            if (compute < d) {
                neuron = next;
                d = compute;
            }
        }
        return neuron;
    }

    public static Pair<Neuron, Neuron> findBestAndSecondBest(double[] dArr, Iterable<Neuron> iterable, DistanceMeasure distanceMeasure) {
        Neuron[] neuronArr = {null, null};
        double[] dArr2 = {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY};
        for (Neuron next : iterable) {
            double compute = distanceMeasure.compute(next.getFeatures(), dArr);
            double d = dArr2[0];
            if (compute < d) {
                dArr2[1] = d;
                neuronArr[1] = neuronArr[0];
                dArr2[0] = compute;
                neuronArr[0] = next;
            } else if (compute < dArr2[1]) {
                dArr2[1] = compute;
                neuronArr[1] = next;
            }
        }
        return new Pair<>(neuronArr[0], neuronArr[1]);
    }

    public static double[][] computeU(NeuronSquareMesh2D neuronSquareMesh2D, DistanceMeasure distanceMeasure) {
        int numberOfRows = neuronSquareMesh2D.getNumberOfRows();
        int numberOfColumns = neuronSquareMesh2D.getNumberOfColumns();
        int[] iArr = new int[2];
        iArr[1] = numberOfColumns;
        iArr[0] = numberOfRows;
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr);
        Network network = neuronSquareMesh2D.getNetwork();
        for (int i = 0; i < numberOfRows; i++) {
            for (int i2 = 0; i2 < numberOfColumns; i2++) {
                Neuron neuron = neuronSquareMesh2D.getNeuron(i, i2);
                Collection<Neuron> neighbours = network.getNeighbours(neuron);
                double[] features = neuron.getFeatures();
                double d = 0.0d;
                int i3 = 0;
                for (Neuron features2 : neighbours) {
                    i3++;
                    d += distanceMeasure.compute(features, features2.getFeatures());
                }
                dArr[i][i2] = d / ((double) i3);
            }
        }
        return dArr;
    }

    public static int[][] computeHitHistogram(Iterable<double[]> iterable, NeuronSquareMesh2D neuronSquareMesh2D, DistanceMeasure distanceMeasure) {
        HashMap hashMap = new HashMap();
        Network network = neuronSquareMesh2D.getNetwork();
        for (double[] findBest : iterable) {
            Neuron findBest2 = findBest(findBest, network, distanceMeasure);
            Integer num = (Integer) hashMap.get(findBest2);
            if (num == null) {
                hashMap.put(findBest2, 1);
            } else {
                hashMap.put(findBest2, Integer.valueOf(num.intValue() + 1));
            }
        }
        int numberOfRows = neuronSquareMesh2D.getNumberOfRows();
        int numberOfColumns = neuronSquareMesh2D.getNumberOfColumns();
        int[] iArr = new int[2];
        iArr[1] = numberOfColumns;
        iArr[0] = numberOfRows;
        int[][] iArr2 = (int[][]) Array.newInstance(Integer.TYPE, iArr);
        for (int i = 0; i < numberOfRows; i++) {
            for (int i2 = 0; i2 < numberOfColumns; i2++) {
                Integer num2 = (Integer) hashMap.get(neuronSquareMesh2D.getNeuron(i, i2));
                if (num2 == null) {
                    iArr2[i][i2] = 0;
                } else {
                    iArr2[i][i2] = num2.intValue();
                }
            }
        }
        return iArr2;
    }

    public static double computeQuantizationError(Iterable<double[]> iterable, Iterable<Neuron> iterable2, DistanceMeasure distanceMeasure) {
        double d = 0.0d;
        int i = 0;
        for (double[] next : iterable) {
            i++;
            d += distanceMeasure.compute(next, findBest(next, iterable2, distanceMeasure).getFeatures());
        }
        if (i != 0) {
            return d / ((double) i);
        }
        throw new NoDataException();
    }

    public static double computeTopographicError(Iterable<double[]> iterable, Network network, DistanceMeasure distanceMeasure) {
        int i = 0;
        int i2 = 0;
        for (double[] findBestAndSecondBest : iterable) {
            i++;
            Pair<Neuron, Neuron> findBestAndSecondBest2 = findBestAndSecondBest(findBestAndSecondBest, network, distanceMeasure);
            if (!network.getNeighbours(findBestAndSecondBest2.getFirst()).contains(findBestAndSecondBest2.getSecond())) {
                i2++;
            }
        }
        if (i != 0) {
            return ((double) i2) / ((double) i);
        }
        throw new NoDataException();
    }
}
