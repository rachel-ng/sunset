package org.apache.commons.math3.ml.neuralnet.sofm;

import java.util.Iterator;
import org.apache.commons.math3.ml.neuralnet.Network;

public class KohonenTrainingTask implements Runnable {
    private final Iterator<double[]> featuresIterator;
    private final Network net;
    private final KohonenUpdateAction updateAction;

    public KohonenTrainingTask(Network network, Iterator<double[]> it, KohonenUpdateAction kohonenUpdateAction) {
        this.net = network;
        this.featuresIterator = it;
        this.updateAction = kohonenUpdateAction;
    }

    public void run() {
        while (this.featuresIterator.hasNext()) {
            this.updateAction.update(this.net, this.featuresIterator.next());
        }
    }
}
