package org.apache.commons.math3.analysis.interpolation;

import org.apache.commons.math3.analysis.TrivariateFunction;
import org.apache.commons.math3.exception.OutOfRangeException;

public class TricubicInterpolatingFunction implements TrivariateFunction {
    private static final double[][] AINV = {new double[]{1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{-3.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{2.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{-3.0d, 0.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 0.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{9.0d, -9.0d, -9.0d, 9.0d, 0.0d, 0.0d, 0.0d, 0.0d, 6.0d, 3.0d, -6.0d, -3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 6.0d, -6.0d, 3.0d, -3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 4.0d, 2.0d, 2.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{-6.0d, 6.0d, 6.0d, -6.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, -3.0d, 3.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, -4.0d, 4.0d, -2.0d, 2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, -2.0d, -1.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{2.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{-6.0d, 6.0d, 6.0d, -6.0d, 0.0d, 0.0d, 0.0d, 0.0d, -4.0d, -2.0d, 4.0d, 2.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 3.0d, -3.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, -1.0d, -2.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{4.0d, -4.0d, -4.0d, 4.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, 2.0d, -2.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, -2.0d, 2.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 1.0d, 1.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 0.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 0.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 9.0d, -9.0d, -9.0d, 9.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 6.0d, 3.0d, -6.0d, -3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 6.0d, -6.0d, 3.0d, -3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 4.0d, 2.0d, 2.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -6.0d, 6.0d, 6.0d, -6.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, -3.0d, 3.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, -4.0d, 4.0d, -2.0d, 2.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, -2.0d, -1.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -6.0d, 6.0d, 6.0d, -6.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -4.0d, -2.0d, 4.0d, 2.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 3.0d, -3.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, -1.0d, -2.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 4.0d, -4.0d, -4.0d, 4.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, 2.0d, -2.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, -2.0d, 2.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 1.0d, 1.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{-3.0d, 0.0d, 0.0d, 0.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 0.0d, 0.0d, 0.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{9.0d, -9.0d, 0.0d, 0.0d, -9.0d, 9.0d, 0.0d, 0.0d, 6.0d, 3.0d, 0.0d, 0.0d, -6.0d, -3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 6.0d, -6.0d, 0.0d, 0.0d, 3.0d, -3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 4.0d, 2.0d, 0.0d, 0.0d, 2.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{-6.0d, 6.0d, 0.0d, 0.0d, 6.0d, -6.0d, 0.0d, 0.0d, -3.0d, -3.0d, 0.0d, 0.0d, 3.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -4.0d, 4.0d, 0.0d, 0.0d, -2.0d, 2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, -2.0d, 0.0d, 0.0d, -1.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 0.0d, 0.0d, 0.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 0.0d, 0.0d, 0.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, -1.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 9.0d, -9.0d, 0.0d, 0.0d, -9.0d, 9.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 6.0d, 3.0d, 0.0d, 0.0d, -6.0d, -3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 6.0d, -6.0d, 0.0d, 0.0d, 3.0d, -3.0d, 0.0d, 0.0d, 4.0d, 2.0d, 0.0d, 0.0d, 2.0d, 1.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -6.0d, 6.0d, 0.0d, 0.0d, 6.0d, -6.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, -3.0d, 0.0d, 0.0d, 3.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -4.0d, 4.0d, 0.0d, 0.0d, -2.0d, 2.0d, 0.0d, 0.0d, -2.0d, -2.0d, 0.0d, 0.0d, -1.0d, -1.0d, 0.0d, 0.0d}, new double[]{9.0d, 0.0d, -9.0d, 0.0d, -9.0d, 0.0d, 9.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 6.0d, 0.0d, 3.0d, 0.0d, -6.0d, 0.0d, -3.0d, 0.0d, 6.0d, 0.0d, -6.0d, 0.0d, 3.0d, 0.0d, -3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 4.0d, 0.0d, 2.0d, 0.0d, 2.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 9.0d, 0.0d, -9.0d, 0.0d, -9.0d, 0.0d, 9.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 6.0d, 0.0d, 3.0d, 0.0d, -6.0d, 0.0d, -3.0d, 0.0d, 6.0d, 0.0d, -6.0d, 0.0d, 3.0d, 0.0d, -3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 4.0d, 0.0d, 2.0d, 0.0d, 2.0d, 0.0d, 1.0d, 0.0d}, new double[]{-27.0d, 27.0d, 27.0d, -27.0d, 27.0d, -27.0d, -27.0d, 27.0d, -18.0d, -9.0d, 18.0d, 9.0d, 18.0d, 9.0d, -18.0d, -9.0d, -18.0d, 18.0d, -9.0d, 9.0d, 18.0d, -18.0d, 9.0d, -9.0d, -18.0d, 18.0d, 18.0d, -18.0d, -9.0d, 9.0d, 9.0d, -9.0d, -12.0d, -6.0d, -6.0d, -3.0d, 12.0d, 6.0d, 6.0d, 3.0d, -12.0d, -6.0d, 12.0d, 6.0d, -6.0d, -3.0d, 6.0d, 3.0d, -12.0d, 12.0d, -6.0d, 6.0d, -6.0d, 6.0d, -3.0d, 3.0d, -8.0d, -4.0d, -4.0d, -2.0d, -4.0d, -2.0d, -2.0d, -1.0d}, new double[]{18.0d, -18.0d, -18.0d, 18.0d, -18.0d, 18.0d, 18.0d, -18.0d, 9.0d, 9.0d, -9.0d, -9.0d, -9.0d, -9.0d, 9.0d, 9.0d, 12.0d, -12.0d, 6.0d, -6.0d, -12.0d, 12.0d, -6.0d, 6.0d, 12.0d, -12.0d, -12.0d, 12.0d, 6.0d, -6.0d, -6.0d, 6.0d, 6.0d, 6.0d, 3.0d, 3.0d, -6.0d, -6.0d, -3.0d, -3.0d, 6.0d, 6.0d, -6.0d, -6.0d, 3.0d, 3.0d, -3.0d, -3.0d, 8.0d, -8.0d, 4.0d, -4.0d, 4.0d, -4.0d, 2.0d, -2.0d, 4.0d, 4.0d, 2.0d, 2.0d, 2.0d, 2.0d, 1.0d, 1.0d}, new double[]{-6.0d, 0.0d, 6.0d, 0.0d, 6.0d, 0.0d, -6.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 0.0d, -3.0d, 0.0d, 3.0d, 0.0d, 3.0d, 0.0d, -4.0d, 0.0d, 4.0d, 0.0d, -2.0d, 0.0d, 2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, -2.0d, 0.0d, -1.0d, 0.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -6.0d, 0.0d, 6.0d, 0.0d, 6.0d, 0.0d, -6.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 0.0d, -3.0d, 0.0d, 3.0d, 0.0d, 3.0d, 0.0d, -4.0d, 0.0d, 4.0d, 0.0d, -2.0d, 0.0d, 2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, -2.0d, 0.0d, -1.0d, 0.0d, -1.0d, 0.0d}, new double[]{18.0d, -18.0d, -18.0d, 18.0d, -18.0d, 18.0d, 18.0d, -18.0d, 12.0d, 6.0d, -12.0d, -6.0d, -12.0d, -6.0d, 12.0d, 6.0d, 9.0d, -9.0d, 9.0d, -9.0d, -9.0d, 9.0d, -9.0d, 9.0d, 12.0d, -12.0d, -12.0d, 12.0d, 6.0d, -6.0d, -6.0d, 6.0d, 6.0d, 3.0d, 6.0d, 3.0d, -6.0d, -3.0d, -6.0d, -3.0d, 8.0d, 4.0d, -8.0d, -4.0d, 4.0d, 2.0d, -4.0d, -2.0d, 6.0d, -6.0d, 6.0d, -6.0d, 3.0d, -3.0d, 3.0d, -3.0d, 4.0d, 2.0d, 4.0d, 2.0d, 2.0d, 1.0d, 2.0d, 1.0d}, new double[]{-12.0d, 12.0d, 12.0d, -12.0d, 12.0d, -12.0d, -12.0d, 12.0d, -6.0d, -6.0d, 6.0d, 6.0d, 6.0d, 6.0d, -6.0d, -6.0d, -6.0d, 6.0d, -6.0d, 6.0d, 6.0d, -6.0d, 6.0d, -6.0d, -8.0d, 8.0d, 8.0d, -8.0d, -4.0d, 4.0d, 4.0d, -4.0d, -3.0d, -3.0d, -3.0d, -3.0d, 3.0d, 3.0d, 3.0d, 3.0d, -4.0d, -4.0d, 4.0d, 4.0d, -2.0d, -2.0d, 2.0d, 2.0d, -4.0d, 4.0d, -4.0d, 4.0d, -2.0d, 2.0d, -2.0d, 2.0d, -2.0d, -2.0d, -2.0d, -2.0d, -1.0d, -1.0d, -1.0d, -1.0d}, new double[]{2.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{-6.0d, 6.0d, 0.0d, 0.0d, 6.0d, -6.0d, 0.0d, 0.0d, -4.0d, -2.0d, 0.0d, 0.0d, 4.0d, 2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 3.0d, 0.0d, 0.0d, -3.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, -1.0d, 0.0d, 0.0d, -2.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{4.0d, -4.0d, 0.0d, 0.0d, -4.0d, 4.0d, 0.0d, 0.0d, 2.0d, 2.0d, 0.0d, 0.0d, -2.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, -2.0d, 0.0d, 0.0d, 2.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 1.0d, 0.0d, 0.0d, 1.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -6.0d, 6.0d, 0.0d, 0.0d, 6.0d, -6.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -4.0d, -2.0d, 0.0d, 0.0d, 4.0d, 2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 3.0d, 0.0d, 0.0d, -3.0d, 3.0d, 0.0d, 0.0d, -2.0d, -1.0d, 0.0d, 0.0d, -2.0d, -1.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 4.0d, -4.0d, 0.0d, 0.0d, -4.0d, 4.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, 2.0d, 0.0d, 0.0d, -2.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, -2.0d, 0.0d, 0.0d, 2.0d, -2.0d, 0.0d, 0.0d, 1.0d, 1.0d, 0.0d, 0.0d, 1.0d, 1.0d, 0.0d, 0.0d}, new double[]{-6.0d, 0.0d, 6.0d, 0.0d, 6.0d, 0.0d, -6.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -4.0d, 0.0d, -2.0d, 0.0d, 4.0d, 0.0d, 2.0d, 0.0d, -3.0d, 0.0d, 3.0d, 0.0d, -3.0d, 0.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, -1.0d, 0.0d, -2.0d, 0.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -6.0d, 0.0d, 6.0d, 0.0d, 6.0d, 0.0d, -6.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -4.0d, 0.0d, -2.0d, 0.0d, 4.0d, 0.0d, 2.0d, 0.0d, -3.0d, 0.0d, 3.0d, 0.0d, -3.0d, 0.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, -1.0d, 0.0d, -2.0d, 0.0d, -1.0d, 0.0d}, new double[]{18.0d, -18.0d, -18.0d, 18.0d, -18.0d, 18.0d, 18.0d, -18.0d, 12.0d, 6.0d, -12.0d, -6.0d, -12.0d, -6.0d, 12.0d, 6.0d, 12.0d, -12.0d, 6.0d, -6.0d, -12.0d, 12.0d, -6.0d, 6.0d, 9.0d, -9.0d, -9.0d, 9.0d, 9.0d, -9.0d, -9.0d, 9.0d, 8.0d, 4.0d, 4.0d, 2.0d, -8.0d, -4.0d, -4.0d, -2.0d, 6.0d, 3.0d, -6.0d, -3.0d, 6.0d, 3.0d, -6.0d, -3.0d, 6.0d, -6.0d, 3.0d, -3.0d, 6.0d, -6.0d, 3.0d, -3.0d, 4.0d, 2.0d, 2.0d, 1.0d, 4.0d, 2.0d, 2.0d, 1.0d}, new double[]{-12.0d, 12.0d, 12.0d, -12.0d, 12.0d, -12.0d, -12.0d, 12.0d, -6.0d, -6.0d, 6.0d, 6.0d, 6.0d, 6.0d, -6.0d, -6.0d, -8.0d, 8.0d, -4.0d, 4.0d, 8.0d, -8.0d, 4.0d, -4.0d, -6.0d, 6.0d, 6.0d, -6.0d, -6.0d, 6.0d, 6.0d, -6.0d, -4.0d, -4.0d, -2.0d, -2.0d, 4.0d, 4.0d, 2.0d, 2.0d, -3.0d, -3.0d, 3.0d, 3.0d, -3.0d, -3.0d, 3.0d, 3.0d, -4.0d, 4.0d, -2.0d, 2.0d, -4.0d, 4.0d, -2.0d, 2.0d, -2.0d, -2.0d, -1.0d, -1.0d, -2.0d, -2.0d, -1.0d, -1.0d}, new double[]{4.0d, 0.0d, -4.0d, 0.0d, -4.0d, 0.0d, 4.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, 0.0d, 2.0d, 0.0d, -2.0d, 0.0d, -2.0d, 0.0d, 2.0d, 0.0d, -2.0d, 0.0d, 2.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 4.0d, 0.0d, -4.0d, 0.0d, -4.0d, 0.0d, 4.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, 0.0d, 2.0d, 0.0d, -2.0d, 0.0d, -2.0d, 0.0d, 2.0d, 0.0d, -2.0d, 0.0d, 2.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d}, new double[]{-12.0d, 12.0d, 12.0d, -12.0d, 12.0d, -12.0d, -12.0d, 12.0d, -8.0d, -4.0d, 8.0d, 4.0d, 8.0d, 4.0d, -8.0d, -4.0d, -6.0d, 6.0d, -6.0d, 6.0d, 6.0d, -6.0d, 6.0d, -6.0d, -6.0d, 6.0d, 6.0d, -6.0d, -6.0d, 6.0d, 6.0d, -6.0d, -4.0d, -2.0d, -4.0d, -2.0d, 4.0d, 2.0d, 4.0d, 2.0d, -4.0d, -2.0d, 4.0d, 2.0d, -4.0d, -2.0d, 4.0d, 2.0d, -3.0d, 3.0d, -3.0d, 3.0d, -3.0d, 3.0d, -3.0d, 3.0d, -2.0d, -1.0d, -2.0d, -1.0d, -2.0d, -1.0d, -2.0d, -1.0d}, new double[]{8.0d, -8.0d, -8.0d, 8.0d, -8.0d, 8.0d, 8.0d, -8.0d, 4.0d, 4.0d, -4.0d, -4.0d, -4.0d, -4.0d, 4.0d, 4.0d, 4.0d, -4.0d, 4.0d, -4.0d, -4.0d, 4.0d, -4.0d, 4.0d, 4.0d, -4.0d, -4.0d, 4.0d, 4.0d, -4.0d, -4.0d, 4.0d, 2.0d, 2.0d, 2.0d, 2.0d, -2.0d, -2.0d, -2.0d, -2.0d, 2.0d, 2.0d, -2.0d, -2.0d, 2.0d, 2.0d, -2.0d, -2.0d, 2.0d, -2.0d, 2.0d, -2.0d, 2.0d, -2.0d, 2.0d, -2.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d}};
    private final TricubicFunction[][][] splines;
    private final double[] xval;
    private final double[] yval;
    private final double[] zval;

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: double[][][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v0, resolved type: double[][][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v0, resolved type: double[][][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v0, resolved type: double[][][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v0, resolved type: double[][][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v0, resolved type: double[][][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v0, resolved type: double[][][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v7, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v34, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v9, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v36, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v11, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v38, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v13, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v40, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v15, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v42, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v17, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v44, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v19, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v46, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v6, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v7, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v52, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v53, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v9, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v10, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v55, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v56, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v12, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v13, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v58, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v59, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v15, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v16, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v61, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v62, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v18, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v19, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v64, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v65, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v21, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v22, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v67, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v68, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v24, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v25, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v70, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v71, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v8, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v1, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r39v2, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r55v0, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r56v0, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r57v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v9, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r59v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r55v1, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r61v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r63v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r65v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r67v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r69v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v10, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v2, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r55v2, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r71v0, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r72v0, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r73v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v11, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r75v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r71v1, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r77v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r79v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r81v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r83v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r85v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v12, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v3, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r71v2, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r87v0, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r88v0, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r89v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v13, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r91v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r87v1, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r93v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r95v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r97v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r99v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r101v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v14, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v2, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r87v2, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v0, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v4, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r103v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v15, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r105v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v1, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r107v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r109v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r111v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r113v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v3, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v16, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v5, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r115v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r117v0, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r118v0, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r119v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v17, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r121v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r117v1, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r123v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r125v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r127v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r129v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r131v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v18, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v2, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r117v2, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r31v0, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v6, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r133v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v19, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r135v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r31v1, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r137v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r139v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r141v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r143v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v3, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v20, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r32v1, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r145v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r33v0, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v7, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r147v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v21, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r149v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r33v1, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r151v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r153v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r155v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r157v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r32v2, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v23, resolved type: double[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public TricubicInterpolatingFunction(double[] r160, double[] r161, double[] r162, double[][][] r163, double[][][] r164, double[][][] r165, double[][][] r166, double[][][] r167, double[][][] r168, double[][][] r169, double[][][] r170) throws org.apache.commons.math3.exception.NoDataException, org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.NonMonotonicSequenceException {
        /*
            r159 = this;
            r0 = r159
            r1 = r162
            r2 = r163
            r3 = r164
            r4 = r165
            r5 = r166
            r6 = r167
            r7 = r168
            r8 = r169
            r9 = r170
            r159.<init>()
            r10 = r160
            int r11 = r10.length
            r12 = r161
            int r13 = r12.length
            int r14 = r1.length
            if (r11 == 0) goto L_0x048e
            if (r13 == 0) goto L_0x048e
            int r15 = r1.length
            if (r15 == 0) goto L_0x048e
            int r15 = r2.length
            if (r15 == 0) goto L_0x048e
            r15 = 0
            r1 = r2[r15]
            int r1 = r1.length
            if (r1 == 0) goto L_0x048e
            int r1 = r2.length
            if (r11 != r1) goto L_0x0487
            int r1 = r3.length
            if (r11 != r1) goto L_0x0480
            int r1 = r4.length
            if (r11 != r1) goto L_0x0479
            int r1 = r5.length
            if (r11 != r1) goto L_0x0472
            int r1 = r6.length
            if (r11 != r1) goto L_0x046b
            int r1 = r7.length
            if (r11 != r1) goto L_0x0464
            int r1 = r8.length
            if (r11 != r1) goto L_0x045d
            int r1 = r9.length
            if (r11 != r1) goto L_0x0456
            org.apache.commons.math3.util.MathArrays.checkOrder(r160)
            org.apache.commons.math3.util.MathArrays.checkOrder(r161)
            org.apache.commons.math3.util.MathArrays.checkOrder(r162)
            java.lang.Object r1 = r160.clone()
            double[] r1 = (double[]) r1
            r0.xval = r1
            java.lang.Object r1 = r161.clone()
            double[] r1 = (double[]) r1
            r0.yval = r1
            java.lang.Object r1 = r162.clone()
            double[] r1 = (double[]) r1
            r0.zval = r1
            r1 = 1
            int r11 = r11 - r1
            int r10 = r13 + -1
            int r12 = r14 + -1
            r15 = 3
            int[] r1 = new int[r15]
            r17 = 2
            r1[r17] = r12
            r18 = 1
            r1[r18] = r10
            r16 = 0
            r1[r16] = r11
            java.lang.Class<org.apache.commons.math3.analysis.interpolation.TricubicFunction> r15 = org.apache.commons.math3.analysis.interpolation.TricubicFunction.class
            java.lang.Object r1 = java.lang.reflect.Array.newInstance(r15, r1)
            org.apache.commons.math3.analysis.interpolation.TricubicFunction[][][] r1 = (org.apache.commons.math3.analysis.interpolation.TricubicFunction[][][]) r1
            r0.splines = r1
            r1 = 0
        L_0x0087:
            if (r1 >= r11) goto L_0x0455
            r15 = r2[r1]
            int r15 = r15.length
            if (r15 != r13) goto L_0x044b
            r15 = r3[r1]
            int r15 = r15.length
            if (r15 != r13) goto L_0x0441
            r15 = r4[r1]
            int r15 = r15.length
            if (r15 != r13) goto L_0x0437
            r15 = r5[r1]
            int r15 = r15.length
            if (r15 != r13) goto L_0x042d
            r15 = r6[r1]
            int r15 = r15.length
            if (r15 != r13) goto L_0x0423
            r15 = r7[r1]
            int r15 = r15.length
            if (r15 != r13) goto L_0x0419
            r15 = r8[r1]
            int r15 = r15.length
            if (r15 != r13) goto L_0x040f
            r15 = r9[r1]
            int r15 = r15.length
            if (r15 != r13) goto L_0x0402
            int r15 = r1 + 1
            r162 = r11
            double[] r11 = r0.xval
            r18 = r11[r15]
            r20 = r11[r1]
            double r18 = r18 - r20
            r11 = 0
        L_0x00be:
            if (r11 >= r10) goto L_0x03f6
            r20 = r2[r1]
            r21 = r10
            r10 = r20[r11]
            int r10 = r10.length
            if (r10 != r14) goto L_0x03eb
            r10 = r3[r1]
            r10 = r10[r11]
            int r10 = r10.length
            if (r10 != r14) goto L_0x03e0
            r10 = r4[r1]
            r10 = r10[r11]
            int r10 = r10.length
            if (r10 != r14) goto L_0x03d5
            r10 = r5[r1]
            r10 = r10[r11]
            int r10 = r10.length
            if (r10 != r14) goto L_0x03ca
            r10 = r6[r1]
            r10 = r10[r11]
            int r10 = r10.length
            if (r10 != r14) goto L_0x03bf
            r10 = r7[r1]
            r10 = r10[r11]
            int r10 = r10.length
            if (r10 != r14) goto L_0x03b4
            r10 = r8[r1]
            r10 = r10[r11]
            int r10 = r10.length
            if (r10 != r14) goto L_0x03a9
            r10 = r9[r1]
            r10 = r10[r11]
            int r10 = r10.length
            if (r10 != r14) goto L_0x039e
            int r10 = r11 + 1
            r20 = r13
            double[] r13 = r0.yval
            r22 = r13[r10]
            r24 = r13[r11]
            double r22 = r22 - r24
            double r24 = r18 * r22
            r13 = 0
        L_0x0109:
            if (r13 >= r12) goto L_0x038e
            int r26 = r13 + 1
            r27 = r12
            double[] r12 = r0.zval
            r28 = r12[r26]
            r30 = r12[r13]
            double r28 = r28 - r30
            double r30 = r18 * r28
            double r32 = r22 * r28
            double r34 = r18 * r32
            r12 = r2[r1]
            r36 = r12[r11]
            r37 = r36[r13]
            r39 = r2[r15]
            r40 = r39[r11]
            r41 = r40[r13]
            r12 = r12[r10]
            r43 = r12[r13]
            r39 = r39[r10]
            r45 = r39[r13]
            r47 = r36[r26]
            r49 = r40[r26]
            r51 = r12[r26]
            r53 = r39[r26]
            r12 = r3[r1]
            r36 = r12[r11]
            r39 = r36[r13]
            double r39 = r39 * r18
            r55 = r3[r15]
            r56 = r55[r11]
            r57 = r56[r13]
            double r57 = r57 * r18
            r12 = r12[r10]
            r59 = r12[r13]
            double r59 = r59 * r18
            r55 = r55[r10]
            r61 = r55[r13]
            double r61 = r61 * r18
            r63 = r36[r26]
            double r63 = r63 * r18
            r65 = r56[r26]
            double r65 = r65 * r18
            r67 = r12[r26]
            double r67 = r67 * r18
            r69 = r55[r26]
            double r69 = r69 * r18
            r12 = r4[r1]
            r36 = r12[r11]
            r55 = r36[r13]
            double r55 = r55 * r22
            r71 = r4[r15]
            r72 = r71[r11]
            r73 = r72[r13]
            double r73 = r73 * r22
            r12 = r12[r10]
            r75 = r12[r13]
            double r75 = r75 * r22
            r71 = r71[r10]
            r77 = r71[r13]
            double r77 = r77 * r22
            r79 = r36[r26]
            double r79 = r79 * r22
            r81 = r72[r26]
            double r81 = r81 * r22
            r83 = r12[r26]
            double r83 = r83 * r22
            r85 = r71[r26]
            double r85 = r85 * r22
            r12 = r5[r1]
            r36 = r12[r11]
            r71 = r36[r13]
            double r71 = r71 * r28
            r87 = r5[r15]
            r88 = r87[r11]
            r89 = r88[r13]
            double r89 = r89 * r28
            r12 = r12[r10]
            r91 = r12[r13]
            double r91 = r91 * r28
            r87 = r87[r10]
            r93 = r87[r13]
            double r93 = r93 * r28
            r95 = r36[r26]
            double r95 = r95 * r28
            r97 = r88[r26]
            double r97 = r97 * r28
            r99 = r12[r26]
            double r99 = r99 * r28
            r101 = r87[r26]
            double r101 = r101 * r28
            r12 = r6[r1]
            r28 = r12[r11]
            r87 = r28[r13]
            double r87 = r87 * r24
            r29 = r6[r15]
            r36 = r29[r11]
            r103 = r36[r13]
            double r103 = r103 * r24
            r12 = r12[r10]
            r105 = r12[r13]
            double r105 = r105 * r24
            r29 = r29[r10]
            r107 = r29[r13]
            double r107 = r107 * r24
            r109 = r28[r26]
            double r109 = r109 * r24
            r111 = r36[r26]
            double r111 = r111 * r24
            r113 = r12[r26]
            double r113 = r113 * r24
            r28 = r29[r26]
            double r28 = r28 * r24
            r12 = r7[r1]
            r36 = r12[r11]
            r115 = r36[r13]
            double r115 = r115 * r30
            r117 = r7[r15]
            r118 = r117[r11]
            r119 = r118[r13]
            double r119 = r119 * r30
            r12 = r12[r10]
            r121 = r12[r13]
            double r121 = r121 * r30
            r117 = r117[r10]
            r123 = r117[r13]
            double r123 = r123 * r30
            r125 = r36[r26]
            double r125 = r125 * r30
            r127 = r118[r26]
            double r127 = r127 * r30
            r129 = r12[r26]
            double r129 = r129 * r30
            r131 = r117[r26]
            double r131 = r131 * r30
            r12 = r8[r1]
            r30 = r12[r11]
            r117 = r30[r13]
            double r117 = r117 * r32
            r31 = r8[r15]
            r36 = r31[r11]
            r133 = r36[r13]
            double r133 = r133 * r32
            r12 = r12[r10]
            r135 = r12[r13]
            double r135 = r135 * r32
            r31 = r31[r10]
            r137 = r31[r13]
            double r137 = r137 * r32
            r139 = r30[r26]
            double r139 = r139 * r32
            r141 = r36[r26]
            double r141 = r141 * r32
            r143 = r12[r26]
            double r143 = r143 * r32
            r30 = r31[r26]
            double r30 = r30 * r32
            r12 = r9[r1]
            r32 = r12[r11]
            r145 = r32[r13]
            double r145 = r145 * r34
            r33 = r9[r15]
            r36 = r33[r11]
            r147 = r36[r13]
            double r147 = r147 * r34
            r12 = r12[r10]
            r149 = r12[r13]
            double r149 = r149 * r34
            r33 = r33[r10]
            r151 = r33[r13]
            double r151 = r151 * r34
            r153 = r32[r26]
            double r153 = r153 * r34
            r155 = r36[r26]
            double r155 = r155 * r34
            r157 = r12[r26]
            double r157 = r157 * r34
            r32 = r33[r26]
            double r32 = r32 * r34
            r12 = 64
            double[] r12 = new double[r12]
            r16 = 0
            r12[r16] = r37
            r34 = 1
            r12[r34] = r41
            r12[r17] = r43
            r35 = 3
            r12[r35] = r45
            r36 = 4
            r12[r36] = r47
            r36 = 5
            r12[r36] = r49
            r36 = 6
            r12[r36] = r51
            r36 = 7
            r12[r36] = r53
            r36 = 8
            r12[r36] = r39
            r36 = 9
            r12[r36] = r57
            r36 = 10
            r12[r36] = r59
            r36 = 11
            r12[r36] = r61
            r36 = 12
            r12[r36] = r63
            r36 = 13
            r12[r36] = r65
            r36 = 14
            r12[r36] = r67
            r36 = 15
            r12[r36] = r69
            r36 = 16
            r12[r36] = r55
            r36 = 17
            r12[r36] = r73
            r36 = 18
            r12[r36] = r75
            r36 = 19
            r12[r36] = r77
            r36 = 20
            r12[r36] = r79
            r36 = 21
            r12[r36] = r81
            r36 = 22
            r12[r36] = r83
            r36 = 23
            r12[r36] = r85
            r36 = 24
            r12[r36] = r71
            r36 = 25
            r12[r36] = r89
            r36 = 26
            r12[r36] = r91
            r36 = 27
            r12[r36] = r93
            r36 = 28
            r12[r36] = r95
            r36 = 29
            r12[r36] = r97
            r36 = 30
            r12[r36] = r99
            r36 = 31
            r12[r36] = r101
            r36 = 32
            r12[r36] = r87
            r36 = 33
            r12[r36] = r103
            r36 = 34
            r12[r36] = r105
            r36 = 35
            r12[r36] = r107
            r36 = 36
            r12[r36] = r109
            r36 = 37
            r12[r36] = r111
            r36 = 38
            r12[r36] = r113
            r36 = 39
            r12[r36] = r28
            r28 = 40
            r12[r28] = r115
            r28 = 41
            r12[r28] = r119
            r28 = 42
            r12[r28] = r121
            r28 = 43
            r12[r28] = r123
            r28 = 44
            r12[r28] = r125
            r28 = 45
            r12[r28] = r127
            r28 = 46
            r12[r28] = r129
            r28 = 47
            r12[r28] = r131
            r28 = 48
            r12[r28] = r117
            r28 = 49
            r12[r28] = r133
            r28 = 50
            r12[r28] = r135
            r28 = 51
            r12[r28] = r137
            r28 = 52
            r12[r28] = r139
            r28 = 53
            r12[r28] = r141
            r28 = 54
            r12[r28] = r143
            r28 = 55
            r12[r28] = r30
            r28 = 56
            r12[r28] = r145
            r28 = 57
            r12[r28] = r147
            r28 = 58
            r12[r28] = r149
            r28 = 59
            r12[r28] = r151
            r28 = 60
            r12[r28] = r153
            r28 = 61
            r12[r28] = r155
            r28 = 62
            r12[r28] = r157
            r28 = 63
            r12[r28] = r32
            r160 = r10
            org.apache.commons.math3.analysis.interpolation.TricubicFunction[][][] r10 = r0.splines
            r10 = r10[r1]
            r10 = r10[r11]
            r161 = r15
            org.apache.commons.math3.analysis.interpolation.TricubicFunction r15 = new org.apache.commons.math3.analysis.interpolation.TricubicFunction
            double[] r12 = r0.computeCoefficients(r12)
            r15.<init>(r12)
            r10[r13] = r15
            r10 = r160
            r15 = r161
            r13 = r26
            r12 = r27
            goto L_0x0109
        L_0x038e:
            r160 = r10
            r16 = 0
            r34 = 1
            r35 = 3
            r11 = r160
            r13 = r20
            r10 = r21
            goto L_0x00be
        L_0x039e:
            org.apache.commons.math3.exception.DimensionMismatchException r2 = new org.apache.commons.math3.exception.DimensionMismatchException
            r1 = r9[r1]
            r1 = r1[r11]
            int r1 = r1.length
            r2.<init>(r1, r14)
            throw r2
        L_0x03a9:
            org.apache.commons.math3.exception.DimensionMismatchException r2 = new org.apache.commons.math3.exception.DimensionMismatchException
            r1 = r8[r1]
            r1 = r1[r11]
            int r1 = r1.length
            r2.<init>(r1, r14)
            throw r2
        L_0x03b4:
            org.apache.commons.math3.exception.DimensionMismatchException r2 = new org.apache.commons.math3.exception.DimensionMismatchException
            r1 = r7[r1]
            r1 = r1[r11]
            int r1 = r1.length
            r2.<init>(r1, r14)
            throw r2
        L_0x03bf:
            org.apache.commons.math3.exception.DimensionMismatchException r2 = new org.apache.commons.math3.exception.DimensionMismatchException
            r1 = r6[r1]
            r1 = r1[r11]
            int r1 = r1.length
            r2.<init>(r1, r14)
            throw r2
        L_0x03ca:
            org.apache.commons.math3.exception.DimensionMismatchException r2 = new org.apache.commons.math3.exception.DimensionMismatchException
            r1 = r5[r1]
            r1 = r1[r11]
            int r1 = r1.length
            r2.<init>(r1, r14)
            throw r2
        L_0x03d5:
            org.apache.commons.math3.exception.DimensionMismatchException r2 = new org.apache.commons.math3.exception.DimensionMismatchException
            r1 = r4[r1]
            r1 = r1[r11]
            int r1 = r1.length
            r2.<init>(r1, r14)
            throw r2
        L_0x03e0:
            org.apache.commons.math3.exception.DimensionMismatchException r2 = new org.apache.commons.math3.exception.DimensionMismatchException
            r1 = r3[r1]
            r1 = r1[r11]
            int r1 = r1.length
            r2.<init>(r1, r14)
            throw r2
        L_0x03eb:
            org.apache.commons.math3.exception.DimensionMismatchException r3 = new org.apache.commons.math3.exception.DimensionMismatchException
            r1 = r2[r1]
            r1 = r1[r11]
            int r1 = r1.length
            r3.<init>(r1, r14)
            throw r3
        L_0x03f6:
            r161 = r15
            r16 = 0
            r35 = 3
            r1 = r161
            r11 = r162
            goto L_0x0087
        L_0x0402:
            r20 = r13
            org.apache.commons.math3.exception.DimensionMismatchException r2 = new org.apache.commons.math3.exception.DimensionMismatchException
            r1 = r9[r1]
            int r1 = r1.length
            r9 = r20
            r2.<init>(r1, r9)
            throw r2
        L_0x040f:
            r9 = r13
            org.apache.commons.math3.exception.DimensionMismatchException r2 = new org.apache.commons.math3.exception.DimensionMismatchException
            r1 = r8[r1]
            int r1 = r1.length
            r2.<init>(r1, r9)
            throw r2
        L_0x0419:
            r9 = r13
            org.apache.commons.math3.exception.DimensionMismatchException r2 = new org.apache.commons.math3.exception.DimensionMismatchException
            r1 = r7[r1]
            int r1 = r1.length
            r2.<init>(r1, r9)
            throw r2
        L_0x0423:
            r9 = r13
            org.apache.commons.math3.exception.DimensionMismatchException r2 = new org.apache.commons.math3.exception.DimensionMismatchException
            r1 = r6[r1]
            int r1 = r1.length
            r2.<init>(r1, r9)
            throw r2
        L_0x042d:
            r9 = r13
            org.apache.commons.math3.exception.DimensionMismatchException r2 = new org.apache.commons.math3.exception.DimensionMismatchException
            r1 = r5[r1]
            int r1 = r1.length
            r2.<init>(r1, r9)
            throw r2
        L_0x0437:
            r9 = r13
            org.apache.commons.math3.exception.DimensionMismatchException r2 = new org.apache.commons.math3.exception.DimensionMismatchException
            r1 = r4[r1]
            int r1 = r1.length
            r2.<init>(r1, r9)
            throw r2
        L_0x0441:
            r9 = r13
            org.apache.commons.math3.exception.DimensionMismatchException r2 = new org.apache.commons.math3.exception.DimensionMismatchException
            r1 = r3[r1]
            int r1 = r1.length
            r2.<init>(r1, r9)
            throw r2
        L_0x044b:
            r9 = r13
            org.apache.commons.math3.exception.DimensionMismatchException r3 = new org.apache.commons.math3.exception.DimensionMismatchException
            r1 = r2[r1]
            int r1 = r1.length
            r3.<init>(r1, r9)
            throw r3
        L_0x0455:
            return
        L_0x0456:
            org.apache.commons.math3.exception.DimensionMismatchException r1 = new org.apache.commons.math3.exception.DimensionMismatchException
            int r2 = r9.length
            r1.<init>(r11, r2)
            throw r1
        L_0x045d:
            org.apache.commons.math3.exception.DimensionMismatchException r1 = new org.apache.commons.math3.exception.DimensionMismatchException
            int r2 = r8.length
            r1.<init>(r11, r2)
            throw r1
        L_0x0464:
            org.apache.commons.math3.exception.DimensionMismatchException r1 = new org.apache.commons.math3.exception.DimensionMismatchException
            int r2 = r7.length
            r1.<init>(r11, r2)
            throw r1
        L_0x046b:
            org.apache.commons.math3.exception.DimensionMismatchException r1 = new org.apache.commons.math3.exception.DimensionMismatchException
            int r2 = r6.length
            r1.<init>(r11, r2)
            throw r1
        L_0x0472:
            org.apache.commons.math3.exception.DimensionMismatchException r1 = new org.apache.commons.math3.exception.DimensionMismatchException
            int r2 = r5.length
            r1.<init>(r11, r2)
            throw r1
        L_0x0479:
            org.apache.commons.math3.exception.DimensionMismatchException r1 = new org.apache.commons.math3.exception.DimensionMismatchException
            int r2 = r4.length
            r1.<init>(r11, r2)
            throw r1
        L_0x0480:
            org.apache.commons.math3.exception.DimensionMismatchException r1 = new org.apache.commons.math3.exception.DimensionMismatchException
            int r2 = r3.length
            r1.<init>(r11, r2)
            throw r1
        L_0x0487:
            org.apache.commons.math3.exception.DimensionMismatchException r1 = new org.apache.commons.math3.exception.DimensionMismatchException
            int r2 = r2.length
            r1.<init>(r11, r2)
            throw r1
        L_0x048e:
            org.apache.commons.math3.exception.NoDataException r1 = new org.apache.commons.math3.exception.NoDataException
            r1.<init>()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.analysis.interpolation.TricubicInterpolatingFunction.<init>(double[], double[], double[], double[][][], double[][][], double[][][], double[][][], double[][][], double[][][], double[][][], double[][][]):void");
    }

    public double value(double d, double d2, double d3) throws OutOfRangeException {
        double d4 = d;
        double d5 = d2;
        double d6 = d3;
        int searchIndex = searchIndex(d4, this.xval);
        if (searchIndex != -1) {
            int searchIndex2 = searchIndex(d5, this.yval);
            if (searchIndex2 != -1) {
                int searchIndex3 = searchIndex(d6, this.zval);
                if (searchIndex3 != -1) {
                    double[] dArr = this.xval;
                    double d7 = dArr[searchIndex];
                    double d8 = (d4 - d7) / (dArr[searchIndex + 1] - d7);
                    double[] dArr2 = this.yval;
                    double d9 = dArr2[searchIndex2];
                    double d10 = (d5 - d9) / (dArr2[searchIndex2 + 1] - d9);
                    double[] dArr3 = this.zval;
                    double d11 = dArr3[searchIndex3];
                    return this.splines[searchIndex][searchIndex2][searchIndex3].value(d8, d10, (d6 - d11) / (dArr3[searchIndex3 + 1] - d11));
                }
                Double valueOf = Double.valueOf(d3);
                Double valueOf2 = Double.valueOf(this.zval[0]);
                double[] dArr4 = this.zval;
                throw new OutOfRangeException(valueOf, valueOf2, Double.valueOf(dArr4[dArr4.length - 1]));
            }
            Double valueOf3 = Double.valueOf(d2);
            Double valueOf4 = Double.valueOf(this.yval[0]);
            double[] dArr5 = this.yval;
            throw new OutOfRangeException(valueOf3, valueOf4, Double.valueOf(dArr5[dArr5.length - 1]));
        }
        Double valueOf5 = Double.valueOf(d);
        Double valueOf6 = Double.valueOf(this.xval[0]);
        double[] dArr6 = this.xval;
        throw new OutOfRangeException(valueOf5, valueOf6, Double.valueOf(dArr6[dArr6.length - 1]));
    }

    public boolean isValidPoint(double d, double d2, double d3) {
        double[] dArr = this.xval;
        if (d >= dArr[0] && d <= dArr[dArr.length - 1]) {
            double[] dArr2 = this.yval;
            if (d2 >= dArr2[0] && d2 <= dArr2[dArr2.length - 1]) {
                double[] dArr3 = this.zval;
                return d3 >= dArr3[0] && d3 <= dArr3[dArr3.length - 1];
            }
        }
    }

    private int searchIndex(double d, double[] dArr) {
        if (d < dArr[0]) {
            return -1;
        }
        int length = dArr.length;
        for (int i = 1; i < length; i++) {
            if (d <= dArr[i]) {
                return i - 1;
            }
        }
        return -1;
    }

    private double[] computeCoefficients(double[] dArr) {
        double[] dArr2 = new double[64];
        for (int i = 0; i < 64; i++) {
            double[] dArr3 = AINV[i];
            double d = 0.0d;
            for (int i2 = 0; i2 < 64; i2++) {
                d += dArr3[i2] * dArr[i2];
            }
            dArr2[i] = d;
        }
        return dArr2;
    }
}
