package org.apache.commons.math3.analysis.integration.gauss;

import java.lang.Number;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.Pair;

public abstract class BaseRuleFactory<T extends Number> {
    private final Map<Integer, Pair<T[], T[]>> pointsAndWeights = new TreeMap();
    private final Map<Integer, Pair<double[], double[]>> pointsAndWeightsDouble = new TreeMap();

    /* access modifiers changed from: protected */
    public abstract Pair<T[], T[]> computeRule(int i) throws DimensionMismatchException;

    public Pair<double[], double[]> getRule(int i) throws NotStrictlyPositiveException, DimensionMismatchException {
        if (i > 0) {
            Pair<double[], double[]> pair = this.pointsAndWeightsDouble.get(Integer.valueOf(i));
            if (pair == null) {
                pair = convertToDouble(getRuleInternal(i));
                this.pointsAndWeightsDouble.put(Integer.valueOf(i), pair);
            }
            return new Pair<>(pair.getFirst().clone(), pair.getSecond().clone());
        }
        throw new NotStrictlyPositiveException(LocalizedFormats.NUMBER_OF_POINTS, Integer.valueOf(i));
    }

    /* access modifiers changed from: protected */
    public synchronized Pair<T[], T[]> getRuleInternal(int i) throws DimensionMismatchException {
        Pair<T[], T[]> pair = this.pointsAndWeights.get(Integer.valueOf(i));
        if (pair != null) {
            return pair;
        }
        addRule(computeRule(i));
        return getRuleInternal(i);
    }

    /* access modifiers changed from: protected */
    public void addRule(Pair<T[], T[]> pair) throws DimensionMismatchException {
        if (((Number[]) pair.getFirst()).length == ((Number[]) pair.getSecond()).length) {
            this.pointsAndWeights.put(Integer.valueOf(((Number[]) pair.getFirst()).length), pair);
            return;
        }
        throw new DimensionMismatchException(((Number[]) pair.getFirst()).length, ((Number[]) pair.getSecond()).length);
    }

    private static <T extends Number> Pair<double[], double[]> convertToDouble(Pair<T[], T[]> pair) {
        Number[] numberArr = (Number[]) pair.getFirst();
        Number[] numberArr2 = (Number[]) pair.getSecond();
        int length = numberArr.length;
        double[] dArr = new double[length];
        double[] dArr2 = new double[length];
        for (int i = 0; i < length; i++) {
            dArr[i] = numberArr[i].doubleValue();
            dArr2[i] = numberArr2[i].doubleValue();
        }
        return new Pair<>(dArr, dArr2);
    }
}
