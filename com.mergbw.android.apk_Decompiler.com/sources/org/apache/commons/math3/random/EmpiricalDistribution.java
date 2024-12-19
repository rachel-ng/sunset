package org.apache.commons.math3.random;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.distribution.AbstractRealDistribution;
import org.apache.commons.math3.distribution.ConstantRealDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.RealDistribution;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.descriptive.StatisticalSummary;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

public class EmpiricalDistribution extends AbstractRealDistribution {
    public static final int DEFAULT_BIN_COUNT = 1000;
    private static final String FILE_CHARSET = "US-ASCII";
    private static final long serialVersionUID = 5729073523949762654L;
    private final int binCount;
    /* access modifiers changed from: private */
    public final List<SummaryStatistics> binStats;
    private double delta;
    private boolean loaded;
    private double max;
    private double min;
    protected final RandomDataGenerator randomData;
    /* access modifiers changed from: private */
    public SummaryStatistics sampleStats;
    private double[] upperBounds;

    public boolean isSupportConnected() {
        return true;
    }

    public boolean isSupportLowerBoundInclusive() {
        return true;
    }

    public boolean isSupportUpperBoundInclusive() {
        return true;
    }

    public double probability(double d) {
        return 0.0d;
    }

    public EmpiricalDistribution() {
        this(1000);
    }

    public EmpiricalDistribution(int i) {
        this(i, new RandomDataGenerator());
    }

    public EmpiricalDistribution(int i, RandomGenerator randomGenerator) {
        this(i, new RandomDataGenerator(randomGenerator));
    }

    public EmpiricalDistribution(RandomGenerator randomGenerator) {
        this(1000, randomGenerator);
    }

    @Deprecated
    public EmpiricalDistribution(int i, RandomDataImpl randomDataImpl) {
        this(i, randomDataImpl.getDelegate());
    }

    @Deprecated
    public EmpiricalDistribution(RandomDataImpl randomDataImpl) {
        this(1000, randomDataImpl);
    }

    private EmpiricalDistribution(int i, RandomDataGenerator randomDataGenerator) {
        super(randomDataGenerator.getRandomGenerator());
        this.sampleStats = null;
        this.max = Double.NEGATIVE_INFINITY;
        this.min = Double.POSITIVE_INFINITY;
        this.delta = 0.0d;
        this.loaded = false;
        this.upperBounds = null;
        this.binCount = i;
        this.randomData = randomDataGenerator;
        this.binStats = new ArrayList();
    }

    public void load(double[] dArr) throws NullArgumentException {
        try {
            new ArrayDataAdapter(dArr).computeStats();
            fillBinStats(new ArrayDataAdapter(dArr));
            this.loaded = true;
        } catch (IOException unused) {
            throw new MathInternalError();
        }
    }

    public void load(URL url) throws IOException, NullArgumentException, ZeroException {
        MathUtils.checkNotNull(url);
        Charset forName = Charset.forName("US-ASCII");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream(), forName));
        try {
            new StreamDataAdapter(bufferedReader).computeStats();
            if (this.sampleStats.getN() != 0) {
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(url.openStream(), forName));
                try {
                    fillBinStats(new StreamDataAdapter(bufferedReader2));
                    this.loaded = true;
                    try {
                        bufferedReader2.close();
                    } catch (IOException unused) {
                    }
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = bufferedReader2;
                    try {
                        bufferedReader.close();
                    } catch (IOException unused2) {
                    }
                    throw th;
                }
            } else {
                throw new ZeroException(LocalizedFormats.URL_CONTAINS_NO_DATA, url);
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedReader.close();
            throw th;
        }
    }

    public void load(File file) throws IOException, NullArgumentException {
        MathUtils.checkNotNull(file);
        Charset forName = Charset.forName("US-ASCII");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), forName));
        try {
            new StreamDataAdapter(bufferedReader).computeStats();
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new FileInputStream(file), forName));
            try {
                fillBinStats(new StreamDataAdapter(bufferedReader2));
                this.loaded = true;
                try {
                    bufferedReader2.close();
                } catch (IOException unused) {
                }
            } catch (Throwable th) {
                th = th;
                bufferedReader = bufferedReader2;
                try {
                    bufferedReader.close();
                } catch (IOException unused2) {
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedReader.close();
            throw th;
        }
    }

    private abstract class DataAdapter {
        public abstract void computeBinStats() throws IOException;

        public abstract void computeStats() throws IOException;

        private DataAdapter() {
        }
    }

    private class StreamDataAdapter extends DataAdapter {
        private BufferedReader inputStream;

        public StreamDataAdapter(BufferedReader bufferedReader) {
            super();
            this.inputStream = bufferedReader;
        }

        public void computeBinStats() throws IOException {
            while (true) {
                String readLine = this.inputStream.readLine();
                if (readLine != null) {
                    double parseDouble = Double.parseDouble(readLine);
                    ((SummaryStatistics) EmpiricalDistribution.this.binStats.get(EmpiricalDistribution.this.findBin(parseDouble))).addValue(parseDouble);
                } else {
                    this.inputStream.close();
                    this.inputStream = null;
                    return;
                }
            }
        }

        public void computeStats() throws IOException {
            SummaryStatistics unused = EmpiricalDistribution.this.sampleStats = new SummaryStatistics();
            while (true) {
                String readLine = this.inputStream.readLine();
                if (readLine != null) {
                    EmpiricalDistribution.this.sampleStats.addValue(Double.parseDouble(readLine));
                } else {
                    this.inputStream.close();
                    this.inputStream = null;
                    return;
                }
            }
        }
    }

    private class ArrayDataAdapter extends DataAdapter {
        private double[] inputArray;

        public ArrayDataAdapter(double[] dArr) throws NullArgumentException {
            super();
            MathUtils.checkNotNull(dArr);
            this.inputArray = dArr;
        }

        public void computeStats() throws IOException {
            SummaryStatistics unused = EmpiricalDistribution.this.sampleStats = new SummaryStatistics();
            for (double addValue : this.inputArray) {
                EmpiricalDistribution.this.sampleStats.addValue(addValue);
            }
        }

        public void computeBinStats() throws IOException {
            for (int i = 0; i < this.inputArray.length; i++) {
                ((SummaryStatistics) EmpiricalDistribution.this.binStats.get(EmpiricalDistribution.this.findBin(this.inputArray[i]))).addValue(this.inputArray[i]);
            }
        }
    }

    private void fillBinStats(DataAdapter dataAdapter) throws IOException {
        this.min = this.sampleStats.getMin();
        double max2 = this.sampleStats.getMax();
        this.max = max2;
        this.delta = (max2 - this.min) / ((double) this.binCount);
        if (!this.binStats.isEmpty()) {
            this.binStats.clear();
        }
        for (int i = 0; i < this.binCount; i++) {
            this.binStats.add(i, new SummaryStatistics());
        }
        dataAdapter.computeBinStats();
        double[] dArr = new double[this.binCount];
        this.upperBounds = dArr;
        dArr[0] = ((double) this.binStats.get(0).getN()) / ((double) this.sampleStats.getN());
        int i2 = 1;
        while (true) {
            int i3 = this.binCount;
            if (i2 < i3 - 1) {
                double[] dArr2 = this.upperBounds;
                dArr2[i2] = dArr2[i2 - 1] + (((double) this.binStats.get(i2).getN()) / ((double) this.sampleStats.getN()));
                i2++;
            } else {
                this.upperBounds[i3 - 1] = 1.0d;
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    public int findBin(double d) {
        return FastMath.min(FastMath.max(((int) FastMath.ceil((d - this.min) / this.delta)) - 1, 0), this.binCount - 1);
    }

    public double getNextValue() throws MathIllegalStateException {
        if (this.loaded) {
            return sample();
        }
        throw new MathIllegalStateException(LocalizedFormats.DISTRIBUTION_NOT_LOADED, new Object[0]);
    }

    public StatisticalSummary getSampleStats() {
        return this.sampleStats;
    }

    public int getBinCount() {
        return this.binCount;
    }

    public List<SummaryStatistics> getBinStats() {
        return this.binStats;
    }

    public double[] getUpperBounds() {
        double[] dArr = new double[this.binCount];
        int i = 0;
        while (true) {
            int i2 = this.binCount;
            if (i < i2 - 1) {
                int i3 = i + 1;
                dArr[i] = this.min + (this.delta * ((double) i3));
                i = i3;
            } else {
                dArr[i2 - 1] = this.max;
                return dArr;
            }
        }
    }

    public double[] getGeneratorUpperBounds() {
        double[] dArr = this.upperBounds;
        int length = dArr.length;
        double[] dArr2 = new double[length];
        System.arraycopy(dArr, 0, dArr2, 0, length);
        return dArr2;
    }

    public boolean isLoaded() {
        return this.loaded;
    }

    public void reSeed(long j) {
        this.randomData.reSeed(j);
    }

    public double density(double d) {
        if (d < this.min || d > this.max) {
            return 0.0d;
        }
        int findBin = findBin(d);
        return (getKernel(this.binStats.get(findBin)).density(d) * pB(findBin)) / kB(findBin);
    }

    public double cumulativeProbability(double d) {
        if (d < this.min) {
            return 0.0d;
        }
        if (d >= this.max) {
            return 1.0d;
        }
        int findBin = findBin(d);
        double pBminus = pBminus(findBin);
        double pB = pB(findBin);
        RealDistribution k = k(d);
        if (k instanceof ConstantRealDistribution) {
            return d < k.getNumericalMean() ? pBminus : pBminus + pB;
        }
        return pBminus + (pB * ((k.cumulativeProbability(d) - k.cumulativeProbability(findBin == 0 ? this.min : getUpperBounds()[findBin - 1])) / kB(findBin)));
    }

    public double inverseCumulativeProbability(double d) throws OutOfRangeException {
        int i;
        int i2 = 0;
        if (d < 0.0d || d > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(d), 0, 1);
        } else if (d == 0.0d) {
            return getSupportLowerBound();
        } else {
            if (i == 0) {
                return getSupportUpperBound();
            }
            while (cumBinP(i2) < d) {
                i2++;
            }
            RealDistribution kernel = getKernel(this.binStats.get(i2));
            double kB = kB(i2);
            double d2 = i2 == 0 ? this.min : getUpperBounds()[i2 - 1];
            double cumulativeProbability = kernel.cumulativeProbability(d2);
            double pB = pB(i2);
            double pBminus = d - pBminus(i2);
            if (pBminus <= 0.0d) {
                return d2;
            }
            return kernel.inverseCumulativeProbability(cumulativeProbability + ((pBminus * kB) / pB));
        }
    }

    public double getNumericalMean() {
        return this.sampleStats.getMean();
    }

    public double getNumericalVariance() {
        return this.sampleStats.getVariance();
    }

    public double getSupportLowerBound() {
        return this.min;
    }

    public double getSupportUpperBound() {
        return this.max;
    }

    public void reseedRandomGenerator(long j) {
        this.randomData.reSeed(j);
    }

    private double pB(int i) {
        if (i == 0) {
            return this.upperBounds[0];
        }
        double[] dArr = this.upperBounds;
        return dArr[i] - dArr[i - 1];
    }

    private double pBminus(int i) {
        if (i == 0) {
            return 0.0d;
        }
        return this.upperBounds[i - 1];
    }

    private double kB(int i) {
        double d;
        double d2;
        double[] upperBounds2 = getUpperBounds();
        RealDistribution kernel = getKernel(this.binStats.get(i));
        if (i == 0) {
            d2 = this.min;
            d = upperBounds2[0];
        } else {
            d2 = upperBounds2[i - 1];
            d = upperBounds2[i];
        }
        return kernel.cumulativeProbability(d2, d);
    }

    private RealDistribution k(double d) {
        return getKernel(this.binStats.get(findBin(d)));
    }

    private double cumBinP(int i) {
        return this.upperBounds[i];
    }

    /* access modifiers changed from: protected */
    public RealDistribution getKernel(SummaryStatistics summaryStatistics) {
        if (summaryStatistics.getN() == 1 || summaryStatistics.getVariance() == 0.0d) {
            return new ConstantRealDistribution(summaryStatistics.getMean());
        }
        return new NormalDistribution(this.randomData.getRandomGenerator(), summaryStatistics.getMean(), summaryStatistics.getStandardDeviation(), 1.0E-9d);
    }
}
