package org.apache.commons.math3.geometry.euclidean.threed;

import java.io.Serializable;
import java.lang.reflect.Array;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

public class Rotation implements Serializable {
    public static final Rotation IDENTITY = new Rotation(1.0d, 0.0d, 0.0d, 0.0d, false);
    private static final long serialVersionUID = -2153622329907944313L;
    private final double q0;
    private final double q1;
    private final double q2;
    private final double q3;

    public Rotation(double d, double d2, double d3, double d4, boolean z) {
        if (z) {
            double sqrt = 1.0d / FastMath.sqrt((((d * d) + (d2 * d2)) + (d3 * d3)) + (d4 * d4));
            d *= sqrt;
            d2 *= sqrt;
            d3 *= sqrt;
            d4 *= sqrt;
        }
        this.q0 = d;
        this.q1 = d2;
        this.q2 = d3;
        this.q3 = d4;
    }

    public Rotation(Vector3D vector3D, double d) throws MathIllegalArgumentException {
        double norm = vector3D.getNorm();
        if (norm != 0.0d) {
            double d2 = d * -0.5d;
            double sin = FastMath.sin(d2) / norm;
            this.q0 = FastMath.cos(d2);
            this.q1 = vector3D.getX() * sin;
            this.q2 = vector3D.getY() * sin;
            this.q3 = sin * vector3D.getZ();
            return;
        }
        throw new MathIllegalArgumentException(LocalizedFormats.ZERO_NORM_FOR_ROTATION_AXIS, new Object[0]);
    }

    public Rotation(double[][] dArr, double d) throws NotARotationMatrixException {
        double[][] dArr2 = dArr;
        if (dArr2.length == 3 && dArr2[0].length == 3 && dArr2[1].length == 3 && dArr2[2].length == 3) {
            double[][] orthogonalizeMatrix = orthogonalizeMatrix(dArr, d);
            double[] dArr3 = orthogonalizeMatrix[0];
            double d2 = dArr3[0];
            double[] dArr4 = orthogonalizeMatrix[1];
            double d3 = dArr4[1];
            double[] dArr5 = orthogonalizeMatrix[2];
            double d4 = dArr5[2];
            double d5 = dArr5[1];
            double d6 = dArr4[2];
            double d7 = dArr4[0];
            double d8 = dArr3[1];
            double d9 = dArr3[2];
            double d10 = ((d2 * ((d3 * d4) - (d5 * d6))) - (d7 * ((d4 * d8) - (d5 * d9)))) + (dArr5[0] * ((d8 * d6) - (d3 * d9)));
            if (d10 >= 0.0d) {
                double[] mat2quat = mat2quat(orthogonalizeMatrix);
                this.q0 = mat2quat[0];
                this.q1 = mat2quat[1];
                this.q2 = mat2quat[2];
                this.q3 = mat2quat[3];
                return;
            }
            throw new NotARotationMatrixException(LocalizedFormats.CLOSEST_ORTHOGONAL_MATRIX_HAS_NEGATIVE_DETERMINANT, Double.valueOf(d10));
        }
        throw new NotARotationMatrixException(LocalizedFormats.ROTATION_MATRIX_DIMENSIONS, Integer.valueOf(dArr2.length), Integer.valueOf(dArr2[0].length));
    }

    public Rotation(Vector3D vector3D, Vector3D vector3D2, Vector3D vector3D3, Vector3D vector3D4) throws MathArithmeticException {
        Vector3D normalize = vector3D.crossProduct(vector3D2).normalize();
        Vector3D normalize2 = normalize.crossProduct(vector3D).normalize();
        Vector3D normalize3 = vector3D.normalize();
        Vector3D normalize4 = vector3D3.crossProduct(vector3D4).normalize();
        Vector3D normalize5 = normalize4.crossProduct(vector3D3).normalize();
        Vector3D normalize6 = vector3D3.normalize();
        double[] mat2quat = mat2quat(new double[][]{new double[]{MathArrays.linearCombination(normalize3.getX(), normalize6.getX(), normalize2.getX(), normalize5.getX(), normalize.getX(), normalize4.getX()), MathArrays.linearCombination(normalize3.getY(), normalize6.getX(), normalize2.getY(), normalize5.getX(), normalize.getY(), normalize4.getX()), MathArrays.linearCombination(normalize3.getZ(), normalize6.getX(), normalize2.getZ(), normalize5.getX(), normalize.getZ(), normalize4.getX())}, new double[]{MathArrays.linearCombination(normalize3.getX(), normalize6.getY(), normalize2.getX(), normalize5.getY(), normalize.getX(), normalize4.getY()), MathArrays.linearCombination(normalize3.getY(), normalize6.getY(), normalize2.getY(), normalize5.getY(), normalize.getY(), normalize4.getY()), MathArrays.linearCombination(normalize3.getZ(), normalize6.getY(), normalize2.getZ(), normalize5.getY(), normalize.getZ(), normalize4.getY())}, new double[]{MathArrays.linearCombination(normalize3.getX(), normalize6.getZ(), normalize2.getX(), normalize5.getZ(), normalize.getX(), normalize4.getZ()), MathArrays.linearCombination(normalize3.getY(), normalize6.getZ(), normalize2.getY(), normalize5.getZ(), normalize.getY(), normalize4.getZ()), MathArrays.linearCombination(normalize3.getZ(), normalize6.getZ(), normalize2.getZ(), normalize5.getZ(), normalize.getZ(), normalize4.getZ())}});
        this.q0 = mat2quat[0];
        this.q1 = mat2quat[1];
        this.q2 = mat2quat[2];
        this.q3 = mat2quat[3];
    }

    public Rotation(Vector3D vector3D, Vector3D vector3D2) throws MathArithmeticException {
        double norm = vector3D.getNorm() * vector3D2.getNorm();
        if (norm != 0.0d) {
            double dotProduct = vector3D.dotProduct(vector3D2);
            if (dotProduct < -0.999999999999998d * norm) {
                Vector3D orthogonal = vector3D.orthogonal();
                this.q0 = 0.0d;
                this.q1 = -orthogonal.getX();
                this.q2 = -orthogonal.getY();
                this.q3 = -orthogonal.getZ();
                return;
            }
            double sqrt = FastMath.sqrt(((dotProduct / norm) + 1.0d) * 0.5d);
            this.q0 = sqrt;
            double d = 1.0d / ((sqrt * 2.0d) * norm);
            Vector3D crossProduct = vector3D2.crossProduct(vector3D);
            this.q1 = crossProduct.getX() * d;
            this.q2 = crossProduct.getY() * d;
            this.q3 = d * crossProduct.getZ();
            return;
        }
        throw new MathArithmeticException(LocalizedFormats.ZERO_NORM_FOR_ROTATION_DEFINING_VECTOR, new Object[0]);
    }

    public Rotation(RotationOrder rotationOrder, double d, double d2, double d3) {
        Rotation applyTo = new Rotation(rotationOrder.getA1(), d).applyTo(new Rotation(rotationOrder.getA2(), d2).applyTo(new Rotation(rotationOrder.getA3(), d3)));
        this.q0 = applyTo.q0;
        this.q1 = applyTo.q1;
        this.q2 = applyTo.q2;
        this.q3 = applyTo.q3;
    }

    private static double[] mat2quat(double[][] dArr) {
        double[] dArr2 = new double[4];
        double d = dArr[0][0];
        double d2 = dArr[1][1];
        double d3 = dArr[2][2];
        double d4 = d + d2 + d3;
        if (d4 > -0.19d) {
            double sqrt = FastMath.sqrt(d4 + 1.0d) * 0.5d;
            dArr2[0] = sqrt;
            double d5 = 0.25d / sqrt;
            double[] dArr3 = dArr[1];
            double d6 = dArr3[2];
            double[] dArr4 = dArr[2];
            dArr2[1] = (d6 - dArr4[1]) * d5;
            double d7 = dArr4[0];
            double[] dArr5 = dArr[0];
            dArr2[2] = (d7 - dArr5[2]) * d5;
            dArr2[3] = d5 * (dArr5[1] - dArr3[0]);
        } else {
            double d8 = (d - d2) - d3;
            if (d8 > -0.19d) {
                double sqrt2 = FastMath.sqrt(d8 + 1.0d) * 0.5d;
                dArr2[1] = sqrt2;
                double d9 = 0.25d / sqrt2;
                double[] dArr6 = dArr[1];
                double d10 = dArr6[2];
                double[] dArr7 = dArr[2];
                dArr2[0] = (d10 - dArr7[1]) * d9;
                double[] dArr8 = dArr[0];
                dArr2[2] = (dArr8[1] + dArr6[0]) * d9;
                dArr2[3] = d9 * (dArr8[2] + dArr7[0]);
            } else {
                double d11 = (d2 - d) - d3;
                if (d11 > -0.19d) {
                    double sqrt3 = FastMath.sqrt(d11 + 1.0d) * 0.5d;
                    dArr2[2] = sqrt3;
                    double d12 = 0.25d / sqrt3;
                    double[] dArr9 = dArr[2];
                    double d13 = dArr9[0];
                    double[] dArr10 = dArr[0];
                    dArr2[0] = (d13 - dArr10[2]) * d12;
                    double d14 = dArr10[1];
                    double[] dArr11 = dArr[1];
                    dArr2[1] = (d14 + dArr11[0]) * d12;
                    dArr2[3] = d12 * (dArr9[1] + dArr11[2]);
                } else {
                    double sqrt4 = FastMath.sqrt(((d3 - d) - d2) + 1.0d) * 0.5d;
                    dArr2[3] = sqrt4;
                    double d15 = 0.25d / sqrt4;
                    double[] dArr12 = dArr[0];
                    double d16 = dArr12[1];
                    double[] dArr13 = dArr[1];
                    dArr2[0] = (d16 - dArr13[0]) * d15;
                    double d17 = dArr12[2];
                    double[] dArr14 = dArr[2];
                    dArr2[1] = (d17 + dArr14[0]) * d15;
                    dArr2[2] = d15 * (dArr14[1] + dArr13[2]);
                }
            }
        }
        return dArr2;
    }

    public Rotation revert() {
        return new Rotation(-this.q0, this.q1, this.q2, this.q3, false);
    }

    public double getQ0() {
        return this.q0;
    }

    public double getQ1() {
        return this.q1;
    }

    public double getQ2() {
        return this.q2;
    }

    public double getQ3() {
        return this.q3;
    }

    public Vector3D getAxis() {
        double d = this.q1;
        double d2 = this.q2;
        double d3 = (d * d) + (d2 * d2);
        double d4 = this.q3;
        double d5 = d3 + (d4 * d4);
        if (d5 == 0.0d) {
            return new Vector3D(1.0d, 0.0d, 0.0d);
        }
        if (this.q0 < 0.0d) {
            double sqrt = 1.0d / FastMath.sqrt(d5);
            return new Vector3D(this.q1 * sqrt, this.q2 * sqrt, this.q3 * sqrt);
        }
        double sqrt2 = -1.0d / FastMath.sqrt(d5);
        return new Vector3D(this.q1 * sqrt2, this.q2 * sqrt2, this.q3 * sqrt2);
    }

    public double getAngle() {
        double asin;
        double d = this.q0;
        if (d < -0.1d || d > 0.1d) {
            double d2 = this.q1;
            double d3 = this.q2;
            double d4 = (d2 * d2) + (d3 * d3);
            double d5 = this.q3;
            asin = FastMath.asin(FastMath.sqrt(d4 + (d5 * d5)));
        } else if (d < 0.0d) {
            asin = FastMath.acos(-d);
        } else {
            asin = FastMath.acos(d);
        }
        return asin * 2.0d;
    }

    public double[] getAngles(RotationOrder rotationOrder) throws CardanEulerSingularityException {
        if (rotationOrder == RotationOrder.XYZ) {
            Vector3D applyTo = applyTo(Vector3D.PLUS_K);
            Vector3D applyInverseTo = applyInverseTo(Vector3D.PLUS_I);
            if (applyInverseTo.getZ() < -0.9999999999d || applyInverseTo.getZ() > 0.9999999999d) {
                throw new CardanEulerSingularityException(true);
            }
            return new double[]{FastMath.atan2(-applyTo.getY(), applyTo.getZ()), FastMath.asin(applyInverseTo.getZ()), FastMath.atan2(-applyInverseTo.getY(), applyInverseTo.getX())};
        } else if (rotationOrder == RotationOrder.XZY) {
            Vector3D applyTo2 = applyTo(Vector3D.PLUS_J);
            Vector3D applyInverseTo2 = applyInverseTo(Vector3D.PLUS_I);
            if (applyInverseTo2.getY() < -0.9999999999d || applyInverseTo2.getY() > 0.9999999999d) {
                throw new CardanEulerSingularityException(true);
            }
            return new double[]{FastMath.atan2(applyTo2.getZ(), applyTo2.getY()), -FastMath.asin(applyInverseTo2.getY()), FastMath.atan2(applyInverseTo2.getZ(), applyInverseTo2.getX())};
        } else if (rotationOrder == RotationOrder.YXZ) {
            Vector3D applyTo3 = applyTo(Vector3D.PLUS_K);
            Vector3D applyInverseTo3 = applyInverseTo(Vector3D.PLUS_J);
            if (applyInverseTo3.getZ() < -0.9999999999d || applyInverseTo3.getZ() > 0.9999999999d) {
                throw new CardanEulerSingularityException(true);
            }
            return new double[]{FastMath.atan2(applyTo3.getX(), applyTo3.getZ()), -FastMath.asin(applyInverseTo3.getZ()), FastMath.atan2(applyInverseTo3.getX(), applyInverseTo3.getY())};
        } else if (rotationOrder == RotationOrder.YZX) {
            Vector3D applyTo4 = applyTo(Vector3D.PLUS_I);
            Vector3D applyInverseTo4 = applyInverseTo(Vector3D.PLUS_J);
            if (applyInverseTo4.getX() < -0.9999999999d || applyInverseTo4.getX() > 0.9999999999d) {
                throw new CardanEulerSingularityException(true);
            }
            return new double[]{FastMath.atan2(-applyTo4.getZ(), applyTo4.getX()), FastMath.asin(applyInverseTo4.getX()), FastMath.atan2(-applyInverseTo4.getZ(), applyInverseTo4.getY())};
        } else if (rotationOrder == RotationOrder.ZXY) {
            Vector3D applyTo5 = applyTo(Vector3D.PLUS_J);
            Vector3D applyInverseTo5 = applyInverseTo(Vector3D.PLUS_K);
            if (applyInverseTo5.getY() < -0.9999999999d || applyInverseTo5.getY() > 0.9999999999d) {
                throw new CardanEulerSingularityException(true);
            }
            return new double[]{FastMath.atan2(-applyTo5.getX(), applyTo5.getY()), FastMath.asin(applyInverseTo5.getY()), FastMath.atan2(-applyInverseTo5.getX(), applyInverseTo5.getZ())};
        } else if (rotationOrder == RotationOrder.ZYX) {
            Vector3D applyTo6 = applyTo(Vector3D.PLUS_I);
            Vector3D applyInverseTo6 = applyInverseTo(Vector3D.PLUS_K);
            if (applyInverseTo6.getX() < -0.9999999999d || applyInverseTo6.getX() > 0.9999999999d) {
                throw new CardanEulerSingularityException(true);
            }
            return new double[]{FastMath.atan2(applyTo6.getY(), applyTo6.getX()), -FastMath.asin(applyInverseTo6.getX()), FastMath.atan2(applyInverseTo6.getY(), applyInverseTo6.getZ())};
        } else if (rotationOrder == RotationOrder.XYX) {
            Vector3D applyTo7 = applyTo(Vector3D.PLUS_I);
            Vector3D applyInverseTo7 = applyInverseTo(Vector3D.PLUS_I);
            if (applyInverseTo7.getX() < -0.9999999999d || applyInverseTo7.getX() > 0.9999999999d) {
                throw new CardanEulerSingularityException(false);
            }
            return new double[]{FastMath.atan2(applyTo7.getY(), -applyTo7.getZ()), FastMath.acos(applyInverseTo7.getX()), FastMath.atan2(applyInverseTo7.getY(), applyInverseTo7.getZ())};
        } else if (rotationOrder == RotationOrder.XZX) {
            Vector3D applyTo8 = applyTo(Vector3D.PLUS_I);
            Vector3D applyInverseTo8 = applyInverseTo(Vector3D.PLUS_I);
            if (applyInverseTo8.getX() < -0.9999999999d || applyInverseTo8.getX() > 0.9999999999d) {
                throw new CardanEulerSingularityException(false);
            }
            return new double[]{FastMath.atan2(applyTo8.getZ(), applyTo8.getY()), FastMath.acos(applyInverseTo8.getX()), FastMath.atan2(applyInverseTo8.getZ(), -applyInverseTo8.getY())};
        } else if (rotationOrder == RotationOrder.YXY) {
            Vector3D applyTo9 = applyTo(Vector3D.PLUS_J);
            Vector3D applyInverseTo9 = applyInverseTo(Vector3D.PLUS_J);
            if (applyInverseTo9.getY() < -0.9999999999d || applyInverseTo9.getY() > 0.9999999999d) {
                throw new CardanEulerSingularityException(false);
            }
            return new double[]{FastMath.atan2(applyTo9.getX(), applyTo9.getZ()), FastMath.acos(applyInverseTo9.getY()), FastMath.atan2(applyInverseTo9.getX(), -applyInverseTo9.getZ())};
        } else if (rotationOrder == RotationOrder.YZY) {
            Vector3D applyTo10 = applyTo(Vector3D.PLUS_J);
            Vector3D applyInverseTo10 = applyInverseTo(Vector3D.PLUS_J);
            if (applyInverseTo10.getY() < -0.9999999999d || applyInverseTo10.getY() > 0.9999999999d) {
                throw new CardanEulerSingularityException(false);
            }
            return new double[]{FastMath.atan2(applyTo10.getZ(), -applyTo10.getX()), FastMath.acos(applyInverseTo10.getY()), FastMath.atan2(applyInverseTo10.getZ(), applyInverseTo10.getX())};
        } else if (rotationOrder == RotationOrder.ZXZ) {
            Vector3D applyTo11 = applyTo(Vector3D.PLUS_K);
            Vector3D applyInverseTo11 = applyInverseTo(Vector3D.PLUS_K);
            if (applyInverseTo11.getZ() < -0.9999999999d || applyInverseTo11.getZ() > 0.9999999999d) {
                throw new CardanEulerSingularityException(false);
            }
            return new double[]{FastMath.atan2(applyTo11.getX(), -applyTo11.getY()), FastMath.acos(applyInverseTo11.getZ()), FastMath.atan2(applyInverseTo11.getX(), applyInverseTo11.getY())};
        } else {
            Vector3D applyTo12 = applyTo(Vector3D.PLUS_K);
            Vector3D applyInverseTo12 = applyInverseTo(Vector3D.PLUS_K);
            if (applyInverseTo12.getZ() < -0.9999999999d || applyInverseTo12.getZ() > 0.9999999999d) {
                throw new CardanEulerSingularityException(false);
            }
            return new double[]{FastMath.atan2(applyTo12.getY(), applyTo12.getX()), FastMath.acos(applyInverseTo12.getZ()), FastMath.atan2(applyInverseTo12.getY(), -applyInverseTo12.getX())};
        }
    }

    public double[][] getMatrix() {
        double d = this.q0;
        double d2 = d * d;
        double d3 = this.q1;
        double d4 = d * d3;
        double d5 = this.q2;
        double d6 = d * d5;
        double d7 = this.q3;
        double d8 = d * d7;
        double d9 = d3 * d3;
        double d10 = d3 * d5;
        double d11 = d3 * d7;
        double d12 = d5 * d5;
        double d13 = d5 * d7;
        double d14 = d4;
        double[][] dArr = {new double[3], new double[3], new double[3]};
        double[] dArr2 = dArr[0];
        dArr2[0] = ((d9 + d2) * 2.0d) - 1.0d;
        double[] dArr3 = dArr[1];
        dArr3[0] = (d10 - d8) * 2.0d;
        double[] dArr4 = dArr[2];
        dArr4[0] = (d11 + d6) * 2.0d;
        dArr2[1] = (d10 + d8) * 2.0d;
        dArr3[1] = ((d2 + d12) * 2.0d) - 1.0d;
        dArr4[1] = (d13 - d14) * 2.0d;
        dArr2[2] = (d11 - d6) * 2.0d;
        dArr3[2] = (d13 + d14) * 2.0d;
        dArr4[2] = ((d2 + (d7 * d7)) * 2.0d) - 1.0d;
        return dArr;
    }

    public Vector3D applyTo(Vector3D vector3D) {
        double x = vector3D.getX();
        double y = vector3D.getY();
        double z = vector3D.getZ();
        double d = (this.q1 * x) + (this.q2 * y) + (this.q3 * z);
        double d2 = this.q0;
        double d3 = this.q2;
        double d4 = d3 * z;
        double d5 = d3;
        double d6 = this.q3;
        double d7 = z;
        double d8 = this.q1;
        return new Vector3D((((((x * d2) - (d4 - (d6 * y))) * d2) + (d * d8)) * 2.0d) - x, (((((y * d2) - ((d6 * x) - (d8 * d7))) * d2) + (d * d5)) * 2.0d) - y, (((d2 * ((d7 * d2) - ((d8 * y) - (x * d5)))) + (d * d6)) * 2.0d) - d7);
    }

    public void applyTo(double[] dArr, double[] dArr2) {
        double d = dArr[0];
        double d2 = dArr[1];
        double d3 = dArr[2];
        double d4 = this.q1;
        double d5 = this.q2;
        double d6 = d2;
        double d7 = this.q3;
        double d8 = (d4 * d) + (d5 * d2) + (d7 * d3);
        double d9 = this.q0;
        dArr2[0] = (((((d * d9) - ((d5 * d3) - (d7 * d6))) * d9) + (d8 * d4)) * 2.0d) - d;
        dArr2[1] = (((((d6 * d9) - ((d7 * d) - (d4 * d3))) * d9) + (d8 * d5)) * 2.0d) - d6;
        dArr2[2] = (((d9 * ((d3 * d9) - ((d4 * d6) - (d5 * d)))) + (d8 * d7)) * 2.0d) - d3;
    }

    public Vector3D applyInverseTo(Vector3D vector3D) {
        double x = vector3D.getX();
        double y = vector3D.getY();
        double z = vector3D.getZ();
        double d = (this.q1 * x) + (this.q2 * y) + (this.q3 * z);
        double d2 = -this.q0;
        double d3 = this.q2;
        double d4 = d3 * z;
        double d5 = d3;
        double d6 = this.q3;
        double d7 = z;
        double d8 = this.q1;
        return new Vector3D((((((x * d2) - (d4 - (d6 * y))) * d2) + (d * d8)) * 2.0d) - x, (((((y * d2) - ((d6 * x) - (d8 * d7))) * d2) + (d * d5)) * 2.0d) - y, (((d2 * ((d7 * d2) - ((d8 * y) - (x * d5)))) + (d * d6)) * 2.0d) - d7);
    }

    public void applyInverseTo(double[] dArr, double[] dArr2) {
        double d = dArr[0];
        double d2 = dArr[1];
        double d3 = dArr[2];
        double d4 = this.q1;
        double d5 = this.q2;
        double d6 = d2;
        double d7 = this.q3;
        double d8 = (d4 * d) + (d5 * d2) + (d7 * d3);
        double d9 = d3;
        double d10 = -this.q0;
        dArr2[0] = (((((d * d10) - ((d5 * d9) - (d7 * d6))) * d10) + (d8 * d4)) * 2.0d) - d;
        dArr2[1] = (((((d6 * d10) - ((d7 * d) - (d4 * d9))) * d10) + (d8 * d5)) * 2.0d) - d6;
        dArr2[2] = (((d10 * ((d9 * d10) - ((d4 * d6) - (d5 * d)))) + (d8 * d7)) * 2.0d) - d9;
    }

    public Rotation applyTo(Rotation rotation) {
        Rotation rotation2;
        Rotation rotation3 = rotation;
        double d = rotation3.q0;
        double d2 = this.q0;
        double d3 = d * d2;
        double d4 = rotation3.q1;
        double d5 = this.q1;
        double d6 = rotation3.q2;
        double d7 = d;
        double d8 = this.q2;
        double d9 = (d4 * d5) + (d6 * d8);
        double d10 = d8;
        double d11 = rotation3.q3;
        double d12 = d6;
        double d13 = this.q3;
        double d14 = d3 - (d9 + (d11 * d13));
        double d15 = (d4 * d2) + (d7 * d5) + ((d12 * d13) - (d11 * d10));
        double d16 = d14;
        double d17 = d15;
        double d18 = (d12 * d2) + (d7 * d10) + ((d11 * d5) - (d4 * d13));
        new Rotation(d16, d17, d18, ((d4 * d10) - (d12 * d5)) + (d11 * d2) + (d7 * d13), false);
        return rotation2;
    }

    public Rotation applyInverseTo(Rotation rotation) {
        Rotation rotation2;
        Rotation rotation3 = rotation;
        double d = rotation3.q0;
        double d2 = this.q0;
        double d3 = rotation3.q1;
        double d4 = this.q1;
        double d5 = rotation3.q2;
        double d6 = d;
        double d7 = this.q2;
        double d8 = (d3 * d4) + (d5 * d7);
        double d9 = d7;
        double d10 = rotation3.q3;
        double d11 = d5;
        double d12 = this.q3;
        double d13 = ((-d) * d2) - (d8 + (d10 * d12));
        double d14 = ((-d3) * d2) + (d6 * d4) + ((d11 * d12) - (d10 * d9));
        double d15 = d11;
        double d16 = ((-d15) * d2) + (d6 * d9) + ((d10 * d4) - (d3 * d12));
        double d17 = d13;
        double d18 = d14;
        double d19 = d16;
        new Rotation(d17, d18, d19, ((d3 * d9) - (d15 * d4)) + ((-d10) * d2) + (d6 * d12), false);
        return rotation2;
    }

    private double[][] orthogonalizeMatrix(double[][] dArr, double d) throws NotARotationMatrixException {
        double[] dArr2 = dArr[0];
        double[] dArr3 = dArr[1];
        double[] dArr4 = dArr[2];
        double d2 = dArr2[0];
        double d3 = dArr2[1];
        double d4 = dArr2[2];
        double d5 = dArr3[0];
        double d6 = dArr3[1];
        double d7 = dArr3[2];
        double d8 = dArr4[0];
        double d9 = dArr4[1];
        double d10 = dArr4[2];
        int[] iArr = new int[2];
        iArr[1] = 3;
        char c2 = 0;
        iArr[0] = 3;
        double[][] dArr5 = (double[][]) Array.newInstance(Double.TYPE, iArr);
        double[] dArr6 = dArr5[0];
        double[] dArr7 = dArr5[1];
        double[] dArr8 = dArr5[2];
        double d11 = 0.0d;
        int i = 0;
        while (true) {
            int i2 = i + 1;
            double[][] dArr9 = dArr5;
            if (i2 < 11) {
                double d12 = dArr2[c2];
                double d13 = dArr3[c2];
                double d14 = dArr4[c2];
                double d15 = (d12 * d2) + (d13 * d5) + (d14 * d8);
                double d16 = dArr2[1];
                double d17 = dArr3[1];
                double d18 = dArr4[1];
                double d19 = (d16 * d2) + (d17 * d5) + (d18 * d8);
                double d20 = dArr2[2];
                double d21 = dArr3[2];
                double d22 = dArr4[2];
                double d23 = (d20 * d2) + (d21 * d5) + (d22 * d8);
                double d24 = (d12 * d3) + (d13 * d6) + (d14 * d9);
                double d25 = (d16 * d3) + (d17 * d6) + (d18 * d9);
                double d26 = (d20 * d3) + (d21 * d6) + (d22 * d9);
                double d27 = (d12 * d4) + (d13 * d7) + (d14 * d10);
                double d28 = (d16 * d4) + (d17 * d7) + (d18 * d10);
                double d29 = (d20 * d4) + (d21 * d7) + (d22 * d10);
                dArr6[0] = d2 - (((((d2 * d15) + (d3 * d19)) + (d4 * d23)) - d12) * 0.5d);
                dArr6[1] = d3 - (((((d2 * d24) + (d3 * d25)) + (d4 * d26)) - d16) * 0.5d);
                dArr6[2] = d4 - (((((d2 * d27) + (d3 * d28)) + (d4 * d29)) - d20) * 0.5d);
                dArr7[0] = d5 - (((((d5 * d15) + (d6 * d19)) + (d7 * d23)) - dArr3[0]) * 0.5d);
                dArr7[1] = d6 - (((((d5 * d24) + (d6 * d25)) + (d7 * d26)) - dArr3[1]) * 0.5d);
                dArr7[2] = d7 - (((((d5 * d27) + (d6 * d28)) + (d7 * d29)) - dArr3[2]) * 0.5d);
                double d30 = d8 - (((((d15 * d8) + (d19 * d9)) + (d23 * d10)) - dArr4[0]) * 0.5d);
                dArr8[0] = d30;
                double d31 = d9 - (((((d24 * d8) + (d25 * d9)) + (d26 * d10)) - dArr4[1]) * 0.5d);
                dArr8[1] = d31;
                double d32 = d10 - (((((d8 * d27) + (d9 * d28)) + (d29 * d10)) - dArr4[2]) * 0.5d);
                dArr8[2] = d32;
                double d33 = dArr6[0] - dArr2[0];
                double d34 = dArr6[1] - dArr2[1];
                double d35 = dArr6[2] - dArr2[2];
                double d36 = dArr7[0] - dArr3[0];
                double d37 = dArr7[1] - dArr3[1];
                double d38 = dArr7[2] - dArr3[2];
                double d39 = d30 - dArr4[0];
                double d40 = d31 - dArr4[1];
                double d41 = d32 - dArr4[2];
                double d42 = (d33 * d33) + (d34 * d34) + (d35 * d35) + (d36 * d36) + (d37 * d37) + (d38 * d38) + (d39 * d39) + (d40 * d40) + (d41 * d41);
                if (FastMath.abs(d42 - d11) <= d) {
                    return dArr9;
                }
                double d43 = dArr6[0];
                double d44 = dArr6[1];
                double d45 = dArr6[2];
                double d46 = dArr7[0];
                double d47 = dArr7[1];
                double d48 = dArr7[2];
                double d49 = dArr8[0];
                i = i2;
                dArr5 = dArr9;
                double d50 = dArr8[1];
                c2 = 0;
                double d51 = d49;
                d10 = dArr8[2];
                d11 = d42;
                d2 = d43;
                d3 = d44;
                d4 = d45;
                d5 = d46;
                d6 = d47;
                d7 = d48;
                d8 = d51;
                d9 = d50;
            } else {
                LocalizedFormats localizedFormats = LocalizedFormats.UNABLE_TO_ORTHOGONOLIZE_MATRIX;
                Object[] objArr = new Object[1];
                objArr[c2] = Integer.valueOf(i);
                throw new NotARotationMatrixException(localizedFormats, objArr);
            }
        }
    }

    public static double distance(Rotation rotation, Rotation rotation2) {
        return rotation.applyInverseTo(rotation2).getAngle();
    }
}
