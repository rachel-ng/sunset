package org.apache.commons.math3.ml.neuralnet;

public interface UpdateAction {
    void update(Network network, double[] dArr);
}
