package org.apache.commons.math3.ml.neuralnet.twod;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.ml.neuralnet.FeatureInitializer;
import org.apache.commons.math3.ml.neuralnet.Network;
import org.apache.commons.math3.ml.neuralnet.Neuron;
import org.apache.commons.math3.ml.neuralnet.SquareNeighbourhood;

public class NeuronSquareMesh2D implements Serializable {
    private static final long serialVersionUID = 1;
    private final long[][] identifiers;
    private final SquareNeighbourhood neighbourhood;
    private final Network network;
    private final int numberOfColumns;
    private final int numberOfRows;
    private final boolean wrapColumns;
    private final boolean wrapRows;

    NeuronSquareMesh2D(boolean z, boolean z2, SquareNeighbourhood squareNeighbourhood, double[][][] dArr) {
        int length = dArr.length;
        this.numberOfRows = length;
        double[][] dArr2 = dArr[0];
        int length2 = dArr2.length;
        this.numberOfColumns = length2;
        if (length < 2) {
            throw new NumberIsTooSmallException(Integer.valueOf(length), 2, true);
        } else if (length2 >= 2) {
            this.wrapRows = z;
            this.wrapColumns = z2;
            this.neighbourhood = squareNeighbourhood;
            this.network = new Network(0, dArr2[0].length);
            int[] iArr = new int[2];
            iArr[1] = length2;
            iArr[0] = length;
            this.identifiers = (long[][]) Array.newInstance(Long.TYPE, iArr);
            for (int i = 0; i < this.numberOfRows; i++) {
                for (int i2 = 0; i2 < this.numberOfColumns; i2++) {
                    this.identifiers[i][i2] = this.network.createNeuron(dArr[i][i2]);
                }
            }
            createLinks();
        } else {
            throw new NumberIsTooSmallException(Integer.valueOf(length2), 2, true);
        }
    }

    public NeuronSquareMesh2D(int i, boolean z, int i2, boolean z2, SquareNeighbourhood squareNeighbourhood, FeatureInitializer[] featureInitializerArr) {
        if (i < 2) {
            throw new NumberIsTooSmallException(Integer.valueOf(i), 2, true);
        } else if (i2 >= 2) {
            this.numberOfRows = i;
            this.wrapRows = z;
            this.numberOfColumns = i2;
            this.wrapColumns = z2;
            this.neighbourhood = squareNeighbourhood;
            int[] iArr = new int[2];
            iArr[1] = i2;
            iArr[0] = i;
            this.identifiers = (long[][]) Array.newInstance(Long.TYPE, iArr);
            int length = featureInitializerArr.length;
            this.network = new Network(0, length);
            for (int i3 = 0; i3 < i; i3++) {
                for (int i4 = 0; i4 < i2; i4++) {
                    double[] dArr = new double[length];
                    for (int i5 = 0; i5 < length; i5++) {
                        dArr[i5] = featureInitializerArr[i5].value();
                    }
                    this.identifiers[i3][i4] = this.network.createNeuron(dArr);
                }
            }
            createLinks();
        } else {
            throw new NumberIsTooSmallException(Integer.valueOf(i2), 2, true);
        }
    }

    public Network getNetwork() {
        return this.network;
    }

    public int getNumberOfRows() {
        return this.numberOfRows;
    }

    public int getNumberOfColumns() {
        return this.numberOfColumns;
    }

    public Neuron getNeuron(int i, int i2) {
        if (i < 0 || i >= this.numberOfRows) {
            throw new OutOfRangeException(Integer.valueOf(i), 0, Integer.valueOf(this.numberOfRows - 1));
        } else if (i2 >= 0 && i2 < this.numberOfColumns) {
            return this.network.getNeuron(this.identifiers[i][i2]);
        } else {
            throw new OutOfRangeException(Integer.valueOf(i2), 0, Integer.valueOf(this.numberOfColumns - 1));
        }
    }

    private void createLinks() {
        ArrayList<Long> arrayList = new ArrayList<>();
        int i = this.numberOfRows - 1;
        int i2 = this.numberOfColumns - 1;
        for (int i3 = 0; i3 < this.numberOfRows; i3++) {
            for (int i4 = 0; i4 < this.numberOfColumns; i4++) {
                arrayList.clear();
                int i5 = AnonymousClass1.$SwitchMap$org$apache$commons$math3$ml$neuralnet$SquareNeighbourhood[this.neighbourhood.ordinal()];
                if (i5 == 1) {
                    if (i3 > 0) {
                        if (i4 > 0) {
                            arrayList.add(Long.valueOf(this.identifiers[i3 - 1][i4 - 1]));
                        }
                        if (i4 < i2) {
                            arrayList.add(Long.valueOf(this.identifiers[i3 - 1][i4 + 1]));
                        }
                    }
                    if (i3 < i) {
                        if (i4 > 0) {
                            arrayList.add(Long.valueOf(this.identifiers[i3 + 1][i4 - 1]));
                        }
                        if (i4 < i2) {
                            arrayList.add(Long.valueOf(this.identifiers[i3 + 1][i4 + 1]));
                        }
                    }
                    if (this.wrapRows) {
                        if (i3 == 0) {
                            if (i4 > 0) {
                                arrayList.add(Long.valueOf(this.identifiers[i][i4 - 1]));
                            }
                            if (i4 < i2) {
                                arrayList.add(Long.valueOf(this.identifiers[i][i4 + 1]));
                            }
                        } else if (i3 == i) {
                            if (i4 > 0) {
                                arrayList.add(Long.valueOf(this.identifiers[0][i4 - 1]));
                            }
                            if (i4 < i2) {
                                arrayList.add(Long.valueOf(this.identifiers[0][i4 + 1]));
                            }
                        }
                    }
                    if (this.wrapColumns) {
                        if (i4 == 0) {
                            if (i3 > 0) {
                                arrayList.add(Long.valueOf(this.identifiers[i3 - 1][i2]));
                            }
                            if (i3 < i) {
                                arrayList.add(Long.valueOf(this.identifiers[i3 + 1][i2]));
                            }
                        } else if (i4 == i2) {
                            if (i3 > 0) {
                                arrayList.add(Long.valueOf(this.identifiers[i3 - 1][0]));
                            }
                            if (i3 < i) {
                                arrayList.add(Long.valueOf(this.identifiers[i3 + 1][0]));
                            }
                        }
                    }
                    if (this.wrapRows && this.wrapColumns) {
                        if (i3 == 0 && i4 == 0) {
                            arrayList.add(Long.valueOf(this.identifiers[i][i2]));
                        } else if (i3 == 0 && i4 == i2) {
                            arrayList.add(Long.valueOf(this.identifiers[i][0]));
                        } else if (i3 == i && i4 == 0) {
                            arrayList.add(Long.valueOf(this.identifiers[0][i2]));
                        } else if (i3 == i && i4 == i2) {
                            arrayList.add(Long.valueOf(this.identifiers[0][0]));
                        }
                    }
                } else if (i5 != 2) {
                    throw new MathInternalError();
                }
                if (i3 > 0) {
                    arrayList.add(Long.valueOf(this.identifiers[i3 - 1][i4]));
                }
                if (i3 < i) {
                    arrayList.add(Long.valueOf(this.identifiers[i3 + 1][i4]));
                }
                if (this.wrapRows) {
                    if (i3 == 0) {
                        arrayList.add(Long.valueOf(this.identifiers[i][i4]));
                    } else if (i3 == i) {
                        arrayList.add(Long.valueOf(this.identifiers[0][i4]));
                    }
                }
                if (i4 > 0) {
                    arrayList.add(Long.valueOf(this.identifiers[i3][i4 - 1]));
                }
                if (i4 < i2) {
                    arrayList.add(Long.valueOf(this.identifiers[i3][i4 + 1]));
                }
                if (this.wrapColumns) {
                    if (i4 == 0) {
                        arrayList.add(Long.valueOf(this.identifiers[i3][i2]));
                    } else if (i4 == i2) {
                        arrayList.add(Long.valueOf(this.identifiers[i3][0]));
                    }
                }
                Neuron neuron = this.network.getNeuron(this.identifiers[i3][i4]);
                for (Long longValue : arrayList) {
                    this.network.addLink(neuron, this.network.getNeuron(longValue.longValue()));
                }
            }
        }
    }

    /* renamed from: org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$math3$ml$neuralnet$SquareNeighbourhood;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                org.apache.commons.math3.ml.neuralnet.SquareNeighbourhood[] r0 = org.apache.commons.math3.ml.neuralnet.SquareNeighbourhood.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$commons$math3$ml$neuralnet$SquareNeighbourhood = r0
                org.apache.commons.math3.ml.neuralnet.SquareNeighbourhood r1 = org.apache.commons.math3.ml.neuralnet.SquareNeighbourhood.MOORE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$commons$math3$ml$neuralnet$SquareNeighbourhood     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.commons.math3.ml.neuralnet.SquareNeighbourhood r1 = org.apache.commons.math3.ml.neuralnet.SquareNeighbourhood.VON_NEUMANN     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.AnonymousClass1.<clinit>():void");
        }
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new IllegalStateException();
    }

    private Object writeReplace() {
        int i = this.numberOfRows;
        int[] iArr = new int[2];
        iArr[1] = this.numberOfColumns;
        iArr[0] = i;
        double[][][] dArr = (double[][][]) Array.newInstance(double[].class, iArr);
        for (int i2 = 0; i2 < this.numberOfRows; i2++) {
            for (int i3 = 0; i3 < this.numberOfColumns; i3++) {
                dArr[i2][i3] = getNeuron(i2, i3).getFeatures();
            }
        }
        return new SerializationProxy(this.wrapRows, this.wrapColumns, this.neighbourhood, dArr);
    }

    private static class SerializationProxy implements Serializable {
        private static final long serialVersionUID = 20130226;
        private final double[][][] featuresList;
        private final SquareNeighbourhood neighbourhood;
        private final boolean wrapColumns;
        private final boolean wrapRows;

        SerializationProxy(boolean z, boolean z2, SquareNeighbourhood squareNeighbourhood, double[][][] dArr) {
            this.wrapRows = z;
            this.wrapColumns = z2;
            this.neighbourhood = squareNeighbourhood;
            this.featuresList = dArr;
        }

        private Object readResolve() {
            return new NeuronSquareMesh2D(this.wrapRows, this.wrapColumns, this.neighbourhood, this.featuresList);
        }
    }
}
