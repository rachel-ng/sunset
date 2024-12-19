package org.apache.commons.math3.ml.clustering;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.ml.clustering.Clusterable;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.ml.distance.EuclideanDistance;
import org.apache.commons.math3.random.JDKRandomGenerator;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

public class FuzzyKMeansClusterer<T extends Clusterable> extends Clusterer<T> {
    private static final double DEFAULT_EPSILON = 0.001d;
    private List<CentroidCluster<T>> clusters;
    private final double epsilon;
    private final double fuzziness;
    private final int k;
    private final int maxIterations;
    private double[][] membershipMatrix;
    private List<T> points;
    private final RandomGenerator random;

    public FuzzyKMeansClusterer(int i, double d) throws NumberIsTooSmallException {
        this(i, d, -1, new EuclideanDistance());
    }

    public FuzzyKMeansClusterer(int i, double d, int i2, DistanceMeasure distanceMeasure) throws NumberIsTooSmallException {
        this(i, d, i2, distanceMeasure, DEFAULT_EPSILON, new JDKRandomGenerator());
    }

    public FuzzyKMeansClusterer(int i, double d, int i2, DistanceMeasure distanceMeasure, double d2, RandomGenerator randomGenerator) throws NumberIsTooSmallException {
        super(distanceMeasure);
        if (d > 1.0d) {
            this.k = i;
            this.fuzziness = d;
            this.maxIterations = i2;
            this.epsilon = d2;
            this.random = randomGenerator;
            double[][] dArr = null;
            this.membershipMatrix = null;
            this.points = null;
            this.clusters = null;
            return;
        }
        throw new NumberIsTooSmallException(Double.valueOf(d), Double.valueOf(1.0d), false);
    }

    public int getK() {
        return this.k;
    }

    public double getFuzziness() {
        return this.fuzziness;
    }

    public int getMaxIterations() {
        return this.maxIterations;
    }

    public double getEpsilon() {
        return this.epsilon;
    }

    public RandomGenerator getRandomGenerator() {
        return this.random;
    }

    public RealMatrix getMembershipMatrix() {
        double[][] dArr = this.membershipMatrix;
        if (dArr != null) {
            return MatrixUtils.createRealMatrix(dArr);
        }
        throw new MathIllegalStateException();
    }

    public List<T> getDataPoints() {
        return this.points;
    }

    public List<CentroidCluster<T>> getClusters() {
        return this.clusters;
    }

    public double getObjectiveFunctionValue() {
        List<T> list = this.points;
        if (list == null || this.clusters == null) {
            throw new MathIllegalStateException();
        }
        double d = 0.0d;
        int i = 0;
        for (T t : list) {
            int i2 = 0;
            for (CentroidCluster<T> center : this.clusters) {
                double distance = distance(t, center.getCenter());
                d += distance * distance * FastMath.pow(this.membershipMatrix[i][i2], this.fuzziness);
                i2++;
            }
            i++;
        }
        return d;
    }

    public List<CentroidCluster<T>> cluster(Collection<T> collection) throws MathIllegalArgumentException {
        MathUtils.checkNotNull(collection);
        int size = collection.size();
        int i = 0;
        if (size >= this.k) {
            this.points = Collections.unmodifiableList(new ArrayList(collection));
            this.clusters = new ArrayList();
            int[] iArr = new int[2];
            iArr[1] = this.k;
            iArr[0] = size;
            this.membershipMatrix = (double[][]) Array.newInstance(Double.TYPE, iArr);
            int[] iArr2 = new int[2];
            iArr2[1] = this.k;
            iArr2[0] = size;
            double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr2);
            if (size == 0) {
                return this.clusters;
            }
            initializeMembershipMatrix();
            int length = ((Clusterable) this.points.get(0)).getPoint().length;
            for (int i2 = 0; i2 < this.k; i2++) {
                this.clusters.add(new CentroidCluster(new DoublePoint(new double[length])));
            }
            int i3 = this.maxIterations;
            if (i3 < 0) {
                i3 = Integer.MAX_VALUE;
            }
            do {
                saveMembershipMatrix(dArr);
                updateClusterCenters();
                updateMembershipMatrix();
                if (calculateMaxMembershipChange(dArr) <= this.epsilon || (i = i + 1) >= i3) {
                }
                saveMembershipMatrix(dArr);
                updateClusterCenters();
                updateMembershipMatrix();
                break;
            } while ((i = i + 1) >= i3);
            return this.clusters;
        }
        throw new NumberIsTooSmallException(Integer.valueOf(size), Integer.valueOf(this.k), false);
    }

    private void updateClusterCenters() {
        ArrayList arrayList = new ArrayList(this.k);
        int i = 0;
        for (CentroidCluster<T> center : this.clusters) {
            int length = center.getCenter().getPoint().length;
            double[] dArr = new double[length];
            double d = 0.0d;
            int i2 = 0;
            for (T point : this.points) {
                int i3 = i;
                double pow = FastMath.pow(this.membershipMatrix[i2][i], this.fuzziness);
                double[] point2 = point.getPoint();
                for (int i4 = 0; i4 < length; i4++) {
                    dArr[i4] = dArr[i4] + (point2[i4] * pow);
                }
                d += pow;
                i2++;
                i = i3;
            }
            MathArrays.scaleInPlace(1.0d / d, dArr);
            arrayList.add(new CentroidCluster(new DoublePoint(dArr)));
            i++;
        }
        this.clusters.clear();
        this.clusters = arrayList;
    }

    private void updateMembershipMatrix() {
        double d;
        double d2;
        for (int i = 0; i < this.points.size(); i++) {
            Clusterable clusterable = (Clusterable) this.points.get(i);
            double d3 = Double.MIN_VALUE;
            int i2 = -1;
            for (int i3 = 0; i3 < this.clusters.size(); i3++) {
                double abs = FastMath.abs(distance(clusterable, this.clusters.get(i3).getCenter()));
                double d4 = 0.0d;
                if (abs != 0.0d) {
                    Iterator<CentroidCluster<T>> it = this.clusters.iterator();
                    d = 0.0d;
                    while (true) {
                        if (!it.hasNext()) {
                            d2 = d4;
                            break;
                        }
                        double abs2 = FastMath.abs(distance(clusterable, it.next().getCenter()));
                        if (abs2 == d4) {
                            d2 = d4;
                            d = Double.POSITIVE_INFINITY;
                            break;
                        }
                        d += FastMath.pow(abs / abs2, 2.0d / (this.fuzziness - 1.0d));
                        d4 = 0.0d;
                    }
                } else {
                    d2 = 0.0d;
                    d = 0.0d;
                }
                double d5 = d == d2 ? 1.0d : d == Double.POSITIVE_INFINITY ? d2 : 1.0d / d;
                this.membershipMatrix[i][i3] = d5;
                if (d5 > d3) {
                    i2 = i3;
                    d3 = d5;
                }
            }
            this.clusters.get(i2).addPoint(clusterable);
        }
    }

    private void initializeMembershipMatrix() {
        for (int i = 0; i < this.points.size(); i++) {
            for (int i2 = 0; i2 < this.k; i2++) {
                this.membershipMatrix[i][i2] = this.random.nextDouble();
            }
            double[][] dArr = this.membershipMatrix;
            dArr[i] = MathArrays.normalizeArray(dArr[i], 1.0d);
        }
    }

    private double calculateMaxMembershipChange(double[][] dArr) {
        double d = 0.0d;
        for (int i = 0; i < this.points.size(); i++) {
            for (int i2 = 0; i2 < this.clusters.size(); i2++) {
                d = FastMath.max(FastMath.abs(this.membershipMatrix[i][i2] - dArr[i][i2]), d);
            }
        }
        return d;
    }

    private void saveMembershipMatrix(double[][] dArr) {
        for (int i = 0; i < this.points.size(); i++) {
            System.arraycopy(this.membershipMatrix[i], 0, dArr[i], 0, this.clusters.size());
        }
    }
}
