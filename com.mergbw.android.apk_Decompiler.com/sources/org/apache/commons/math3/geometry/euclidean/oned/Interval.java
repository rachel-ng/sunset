package org.apache.commons.math3.geometry.euclidean.oned;

import org.apache.commons.math3.geometry.partitioning.Region;

public class Interval {
    private final double lower;
    private final double upper;

    public Interval(double d, double d2) {
        this.lower = d;
        this.upper = d2;
    }

    public double getInf() {
        return this.lower;
    }

    @Deprecated
    public double getLower() {
        return getInf();
    }

    public double getSup() {
        return this.upper;
    }

    @Deprecated
    public double getUpper() {
        return getSup();
    }

    public double getSize() {
        return this.upper - this.lower;
    }

    @Deprecated
    public double getLength() {
        return getSize();
    }

    public double getBarycenter() {
        return (this.lower + this.upper) * 0.5d;
    }

    @Deprecated
    public double getMidPoint() {
        return getBarycenter();
    }

    public Region.Location checkPoint(double d, double d2) {
        double d3 = this.lower;
        if (d >= d3 - d2) {
            double d4 = this.upper;
            if (d <= d4 + d2) {
                if (d <= d3 + d2 || d >= d4 - d2) {
                    return Region.Location.BOUNDARY;
                }
                return Region.Location.INSIDE;
            }
        }
        return Region.Location.OUTSIDE;
    }
}
