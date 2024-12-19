package org.apache.commons.math3.geometry.euclidean.threed;

import java.io.Serializable;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathArrays;

public class FieldRotation<T extends RealFieldElement<T>> implements Serializable {
    private static final long serialVersionUID = 20130224;
    private final T q0;
    private final T q1;
    private final T q2;
    private final T q3;

    public FieldRotation(T t, T t2, T t3, T t4, boolean z) {
        if (z) {
            RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t.multiply(t)).add(t2.multiply(t2))).add(t3.multiply(t3))).add(t4.multiply(t4))).sqrt()).reciprocal();
            this.q0 = (RealFieldElement) realFieldElement.multiply(t);
            this.q1 = (RealFieldElement) realFieldElement.multiply(t2);
            this.q2 = (RealFieldElement) realFieldElement.multiply(t3);
            this.q3 = (RealFieldElement) realFieldElement.multiply(t4);
            return;
        }
        this.q0 = t;
        this.q1 = t2;
        this.q2 = t3;
        this.q3 = t4;
    }

    public FieldRotation(FieldVector3D<T> fieldVector3D, T t) throws MathIllegalArgumentException {
        T norm = fieldVector3D.getNorm();
        if (norm.getReal() != 0.0d) {
            RealFieldElement realFieldElement = (RealFieldElement) t.multiply(-0.5d);
            RealFieldElement realFieldElement2 = (RealFieldElement) ((RealFieldElement) realFieldElement.sin()).divide(norm);
            this.q0 = (RealFieldElement) realFieldElement.cos();
            this.q1 = (RealFieldElement) realFieldElement2.multiply(fieldVector3D.getX());
            this.q2 = (RealFieldElement) realFieldElement2.multiply(fieldVector3D.getY());
            this.q3 = (RealFieldElement) realFieldElement2.multiply(fieldVector3D.getZ());
            return;
        }
        throw new MathIllegalArgumentException(LocalizedFormats.ZERO_NORM_FOR_ROTATION_AXIS, new Object[0]);
    }

    public FieldRotation(T[][] tArr, double d) throws NotARotationMatrixException {
        if (tArr.length == 3 && tArr[0].length == 3 && tArr[1].length == 3 && tArr[2].length == 3) {
            RealFieldElement[][] orthogonalizeMatrix = orthogonalizeMatrix(tArr, d);
            RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) orthogonalizeMatrix[0][0].multiply((RealFieldElement) ((RealFieldElement) orthogonalizeMatrix[1][1].multiply(orthogonalizeMatrix[2][2])).subtract(orthogonalizeMatrix[2][1].multiply(orthogonalizeMatrix[1][2])))).subtract(orthogonalizeMatrix[1][0].multiply((RealFieldElement) ((RealFieldElement) orthogonalizeMatrix[0][1].multiply(orthogonalizeMatrix[2][2])).subtract(orthogonalizeMatrix[2][1].multiply(orthogonalizeMatrix[0][2]))))).add(orthogonalizeMatrix[2][0].multiply((RealFieldElement) ((RealFieldElement) orthogonalizeMatrix[0][1].multiply(orthogonalizeMatrix[1][2])).subtract(orthogonalizeMatrix[1][1].multiply(orthogonalizeMatrix[0][2]))));
            if (realFieldElement.getReal() >= 0.0d) {
                T[] mat2quat = mat2quat(orthogonalizeMatrix);
                this.q0 = mat2quat[0];
                this.q1 = mat2quat[1];
                this.q2 = mat2quat[2];
                this.q3 = mat2quat[3];
                return;
            }
            throw new NotARotationMatrixException(LocalizedFormats.CLOSEST_ORTHOGONAL_MATRIX_HAS_NEGATIVE_DETERMINANT, realFieldElement);
        }
        throw new NotARotationMatrixException(LocalizedFormats.ROTATION_MATRIX_DIMENSIONS, Integer.valueOf(tArr.length), Integer.valueOf(tArr[0].length));
    }

    public FieldRotation(FieldVector3D<T> fieldVector3D, FieldVector3D<T> fieldVector3D2, FieldVector3D<T> fieldVector3D3, FieldVector3D<T> fieldVector3D4) throws MathArithmeticException {
        FieldVector3D<T> normalize = FieldVector3D.crossProduct(fieldVector3D, fieldVector3D2).normalize();
        FieldVector3D<T> normalize2 = FieldVector3D.crossProduct(normalize, fieldVector3D).normalize();
        FieldVector3D<T> normalize3 = fieldVector3D.normalize();
        FieldVector3D<T> normalize4 = FieldVector3D.crossProduct(fieldVector3D3, fieldVector3D4).normalize();
        FieldVector3D<T> normalize5 = FieldVector3D.crossProduct(normalize4, fieldVector3D3).normalize();
        FieldVector3D<T> normalize6 = fieldVector3D3.normalize();
        RealFieldElement[][] realFieldElementArr = (RealFieldElement[][]) MathArrays.buildArray(normalize3.getX().getField(), 3, 3);
        realFieldElementArr[0][0] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) normalize3.getX().multiply(normalize6.getX())).add(normalize2.getX().multiply(normalize5.getX()))).add(normalize.getX().multiply(normalize4.getX()));
        realFieldElementArr[0][1] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) normalize3.getY().multiply(normalize6.getX())).add(normalize2.getY().multiply(normalize5.getX()))).add(normalize.getY().multiply(normalize4.getX()));
        realFieldElementArr[0][2] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) normalize3.getZ().multiply(normalize6.getX())).add(normalize2.getZ().multiply(normalize5.getX()))).add(normalize.getZ().multiply(normalize4.getX()));
        realFieldElementArr[1][0] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) normalize3.getX().multiply(normalize6.getY())).add(normalize2.getX().multiply(normalize5.getY()))).add(normalize.getX().multiply(normalize4.getY()));
        realFieldElementArr[1][1] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) normalize3.getY().multiply(normalize6.getY())).add(normalize2.getY().multiply(normalize5.getY()))).add(normalize.getY().multiply(normalize4.getY()));
        realFieldElementArr[1][2] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) normalize3.getZ().multiply(normalize6.getY())).add(normalize2.getZ().multiply(normalize5.getY()))).add(normalize.getZ().multiply(normalize4.getY()));
        realFieldElementArr[2][0] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) normalize3.getX().multiply(normalize6.getZ())).add(normalize2.getX().multiply(normalize5.getZ()))).add(normalize.getX().multiply(normalize4.getZ()));
        realFieldElementArr[2][1] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) normalize3.getY().multiply(normalize6.getZ())).add(normalize2.getY().multiply(normalize5.getZ()))).add(normalize.getY().multiply(normalize4.getZ()));
        realFieldElementArr[2][2] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) normalize3.getZ().multiply(normalize6.getZ())).add(normalize2.getZ().multiply(normalize5.getZ()))).add(normalize.getZ().multiply(normalize4.getZ()));
        T[] mat2quat = mat2quat(realFieldElementArr);
        this.q0 = mat2quat[0];
        this.q1 = mat2quat[1];
        this.q2 = mat2quat[2];
        this.q3 = mat2quat[3];
    }

    public FieldRotation(FieldVector3D<T> fieldVector3D, FieldVector3D<T> fieldVector3D2) throws MathArithmeticException {
        RealFieldElement realFieldElement = (RealFieldElement) fieldVector3D.getNorm().multiply(fieldVector3D2.getNorm());
        if (realFieldElement.getReal() != 0.0d) {
            T dotProduct = FieldVector3D.dotProduct(fieldVector3D, fieldVector3D2);
            if (dotProduct.getReal() < realFieldElement.getReal() * -0.999999999999998d) {
                FieldVector3D<T> orthogonal = fieldVector3D.orthogonal();
                this.q0 = (RealFieldElement) realFieldElement.getField().getZero();
                this.q1 = (RealFieldElement) orthogonal.getX().negate();
                this.q2 = (RealFieldElement) orthogonal.getY().negate();
                this.q3 = (RealFieldElement) orthogonal.getZ().negate();
                return;
            }
            T t = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) dotProduct.divide(realFieldElement)).add(1.0d)).multiply(0.5d)).sqrt();
            this.q0 = t;
            RealFieldElement realFieldElement2 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) t.multiply(realFieldElement)).multiply(2.0d)).reciprocal();
            FieldVector3D<T> crossProduct = FieldVector3D.crossProduct(fieldVector3D2, fieldVector3D);
            this.q1 = (RealFieldElement) realFieldElement2.multiply(crossProduct.getX());
            this.q2 = (RealFieldElement) realFieldElement2.multiply(crossProduct.getY());
            this.q3 = (RealFieldElement) realFieldElement2.multiply(crossProduct.getZ());
            return;
        }
        throw new MathArithmeticException(LocalizedFormats.ZERO_NORM_FOR_ROTATION_DEFINING_VECTOR, new Object[0]);
    }

    public FieldRotation(RotationOrder rotationOrder, T t, T t2, T t3) {
        RealFieldElement realFieldElement = (RealFieldElement) t.getField().getOne();
        FieldRotation applyTo = new FieldRotation(new FieldVector3D(realFieldElement, rotationOrder.getA1()), t).applyTo(new FieldRotation(new FieldVector3D(realFieldElement, rotationOrder.getA2()), t2).applyTo(new FieldRotation(new FieldVector3D(realFieldElement, rotationOrder.getA3()), t3)));
        this.q0 = applyTo.q0;
        this.q1 = applyTo.q1;
        this.q2 = applyTo.q2;
        this.q3 = applyTo.q3;
    }

    private T[] mat2quat(T[][] tArr) {
        T[] tArr2 = (RealFieldElement[]) MathArrays.buildArray(tArr[0][0].getField(), 4);
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) tArr[0][0].add(tArr[1][1])).add(tArr[2][2]);
        if (realFieldElement.getReal() > -0.19d) {
            T t = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement.add(1.0d)).sqrt()).multiply(0.5d);
            tArr2[0] = t;
            RealFieldElement realFieldElement2 = (RealFieldElement) ((RealFieldElement) t.reciprocal()).multiply(0.25d);
            tArr2[1] = (RealFieldElement) realFieldElement2.multiply(tArr[1][2].subtract(tArr[2][1]));
            tArr2[2] = (RealFieldElement) realFieldElement2.multiply(tArr[2][0].subtract(tArr[0][2]));
            tArr2[3] = (RealFieldElement) realFieldElement2.multiply(tArr[0][1].subtract(tArr[1][0]));
        } else {
            RealFieldElement realFieldElement3 = (RealFieldElement) ((RealFieldElement) tArr[0][0].subtract(tArr[1][1])).subtract(tArr[2][2]);
            if (realFieldElement3.getReal() > -0.19d) {
                T t2 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement3.add(1.0d)).sqrt()).multiply(0.5d);
                tArr2[1] = t2;
                RealFieldElement realFieldElement4 = (RealFieldElement) ((RealFieldElement) t2.reciprocal()).multiply(0.25d);
                tArr2[0] = (RealFieldElement) realFieldElement4.multiply(tArr[1][2].subtract(tArr[2][1]));
                tArr2[2] = (RealFieldElement) realFieldElement4.multiply(tArr[0][1].add(tArr[1][0]));
                tArr2[3] = (RealFieldElement) realFieldElement4.multiply(tArr[0][2].add(tArr[2][0]));
            } else {
                RealFieldElement realFieldElement5 = (RealFieldElement) ((RealFieldElement) tArr[1][1].subtract(tArr[0][0])).subtract(tArr[2][2]);
                if (realFieldElement5.getReal() > -0.19d) {
                    T t3 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement5.add(1.0d)).sqrt()).multiply(0.5d);
                    tArr2[2] = t3;
                    RealFieldElement realFieldElement6 = (RealFieldElement) ((RealFieldElement) t3.reciprocal()).multiply(0.25d);
                    tArr2[0] = (RealFieldElement) realFieldElement6.multiply(tArr[2][0].subtract(tArr[0][2]));
                    tArr2[1] = (RealFieldElement) realFieldElement6.multiply(tArr[0][1].add(tArr[1][0]));
                    tArr2[3] = (RealFieldElement) realFieldElement6.multiply(tArr[2][1].add(tArr[1][2]));
                } else {
                    T t4 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) tArr[2][2].subtract(tArr[0][0])).subtract(tArr[1][1])).add(1.0d)).sqrt()).multiply(0.5d);
                    tArr2[3] = t4;
                    RealFieldElement realFieldElement7 = (RealFieldElement) ((RealFieldElement) t4.reciprocal()).multiply(0.25d);
                    tArr2[0] = (RealFieldElement) realFieldElement7.multiply(tArr[0][1].subtract(tArr[1][0]));
                    tArr2[1] = (RealFieldElement) realFieldElement7.multiply(tArr[0][2].add(tArr[2][0]));
                    tArr2[2] = (RealFieldElement) realFieldElement7.multiply(tArr[2][1].add(tArr[1][2]));
                }
            }
        }
        return tArr2;
    }

    public FieldRotation<T> revert() {
        return new FieldRotation((RealFieldElement) this.q0.negate(), this.q1, this.q2, this.q3, false);
    }

    public T getQ0() {
        return this.q0;
    }

    public T getQ1() {
        return this.q1;
    }

    public T getQ2() {
        return this.q2;
    }

    public T getQ3() {
        return this.q3;
    }

    public FieldVector3D<T> getAxis() {
        T t = this.q1;
        T t2 = this.q2;
        T t3 = this.q3;
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) t.multiply(t)).add(t2.multiply(t2))).add(t3.multiply(t3));
        if (realFieldElement.getReal() == 0.0d) {
            Field field = realFieldElement.getField();
            return new FieldVector3D<>((RealFieldElement) field.getOne(), (RealFieldElement) field.getZero(), (RealFieldElement) field.getZero());
        } else if (this.q0.getReal() < 0.0d) {
            RealFieldElement realFieldElement2 = (RealFieldElement) ((RealFieldElement) realFieldElement.sqrt()).reciprocal();
            return new FieldVector3D<>((RealFieldElement) this.q1.multiply(realFieldElement2), (RealFieldElement) this.q2.multiply(realFieldElement2), (RealFieldElement) this.q3.multiply(realFieldElement2));
        } else {
            RealFieldElement realFieldElement3 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement.sqrt()).reciprocal()).negate();
            return new FieldVector3D<>((RealFieldElement) this.q1.multiply(realFieldElement3), (RealFieldElement) this.q2.multiply(realFieldElement3), (RealFieldElement) this.q3.multiply(realFieldElement3));
        }
    }

    public T getAngle() {
        if (this.q0.getReal() < -0.1d || this.q0.getReal() > 0.1d) {
            T t = this.q1;
            T t2 = this.q2;
            T t3 = this.q3;
            return (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t.multiply(t)).add(t2.multiply(t2))).add(t3.multiply(t3))).sqrt()).asin()).multiply(2);
        } else if (this.q0.getReal() < 0.0d) {
            return (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q0.negate()).acos()).multiply(2);
        } else {
            return (RealFieldElement) ((RealFieldElement) this.q0.acos()).multiply(2);
        }
    }

    public T[] getAngles(RotationOrder rotationOrder) throws CardanEulerSingularityException {
        if (rotationOrder == RotationOrder.XYZ) {
            FieldVector3D applyTo = applyTo(vector(0.0d, 0.0d, 1.0d));
            FieldVector3D applyInverseTo = applyInverseTo(vector(1.0d, 0.0d, 0.0d));
            if (applyInverseTo.getZ().getReal() >= -0.9999999999d && applyInverseTo.getZ().getReal() <= 0.9999999999d) {
                return buildArray((RealFieldElement) ((RealFieldElement) applyTo.getY().negate()).atan2(applyTo.getZ()), (RealFieldElement) applyInverseTo.getZ().asin(), (RealFieldElement) ((RealFieldElement) applyInverseTo.getY().negate()).atan2(applyInverseTo.getX()));
            }
            throw new CardanEulerSingularityException(true);
        } else if (rotationOrder == RotationOrder.XZY) {
            FieldVector3D applyTo2 = applyTo(vector(0.0d, 1.0d, 0.0d));
            FieldVector3D applyInverseTo2 = applyInverseTo(vector(1.0d, 0.0d, 0.0d));
            if (applyInverseTo2.getY().getReal() >= -0.9999999999d && applyInverseTo2.getY().getReal() <= 0.9999999999d) {
                return buildArray((RealFieldElement) applyTo2.getZ().atan2(applyTo2.getY()), (RealFieldElement) ((RealFieldElement) applyInverseTo2.getY().asin()).negate(), (RealFieldElement) applyInverseTo2.getZ().atan2(applyInverseTo2.getX()));
            }
            throw new CardanEulerSingularityException(true);
        } else if (rotationOrder == RotationOrder.YXZ) {
            FieldVector3D applyTo3 = applyTo(vector(0.0d, 0.0d, 1.0d));
            FieldVector3D applyInverseTo3 = applyInverseTo(vector(0.0d, 1.0d, 0.0d));
            if (applyInverseTo3.getZ().getReal() >= -0.9999999999d && applyInverseTo3.getZ().getReal() <= 0.9999999999d) {
                return buildArray((RealFieldElement) applyTo3.getX().atan2(applyTo3.getZ()), (RealFieldElement) ((RealFieldElement) applyInverseTo3.getZ().asin()).negate(), (RealFieldElement) applyInverseTo3.getX().atan2(applyInverseTo3.getY()));
            }
            throw new CardanEulerSingularityException(true);
        } else if (rotationOrder == RotationOrder.YZX) {
            FieldVector3D applyTo4 = applyTo(vector(1.0d, 0.0d, 0.0d));
            FieldVector3D applyInverseTo4 = applyInverseTo(vector(0.0d, 1.0d, 0.0d));
            if (applyInverseTo4.getX().getReal() >= -0.9999999999d && applyInverseTo4.getX().getReal() <= 0.9999999999d) {
                return buildArray((RealFieldElement) ((RealFieldElement) applyTo4.getZ().negate()).atan2(applyTo4.getX()), (RealFieldElement) applyInverseTo4.getX().asin(), (RealFieldElement) ((RealFieldElement) applyInverseTo4.getZ().negate()).atan2(applyInverseTo4.getY()));
            }
            throw new CardanEulerSingularityException(true);
        } else if (rotationOrder == RotationOrder.ZXY) {
            FieldVector3D applyTo5 = applyTo(vector(0.0d, 1.0d, 0.0d));
            FieldVector3D applyInverseTo5 = applyInverseTo(vector(0.0d, 0.0d, 1.0d));
            if (applyInverseTo5.getY().getReal() >= -0.9999999999d && applyInverseTo5.getY().getReal() <= 0.9999999999d) {
                return buildArray((RealFieldElement) ((RealFieldElement) applyTo5.getX().negate()).atan2(applyTo5.getY()), (RealFieldElement) applyInverseTo5.getY().asin(), (RealFieldElement) ((RealFieldElement) applyInverseTo5.getX().negate()).atan2(applyInverseTo5.getZ()));
            }
            throw new CardanEulerSingularityException(true);
        } else if (rotationOrder == RotationOrder.ZYX) {
            FieldVector3D applyTo6 = applyTo(vector(1.0d, 0.0d, 0.0d));
            FieldVector3D applyInverseTo6 = applyInverseTo(vector(0.0d, 0.0d, 1.0d));
            if (applyInverseTo6.getX().getReal() >= -0.9999999999d && applyInverseTo6.getX().getReal() <= 0.9999999999d) {
                return buildArray((RealFieldElement) applyTo6.getY().atan2(applyTo6.getX()), (RealFieldElement) ((RealFieldElement) applyInverseTo6.getX().asin()).negate(), (RealFieldElement) applyInverseTo6.getY().atan2(applyInverseTo6.getZ()));
            }
            throw new CardanEulerSingularityException(true);
        } else if (rotationOrder == RotationOrder.XYX) {
            FieldVector3D applyTo7 = applyTo(vector(1.0d, 0.0d, 0.0d));
            FieldVector3D applyInverseTo7 = applyInverseTo(vector(1.0d, 0.0d, 0.0d));
            if (applyInverseTo7.getX().getReal() >= -0.9999999999d && applyInverseTo7.getX().getReal() <= 0.9999999999d) {
                return buildArray((RealFieldElement) applyTo7.getY().atan2(applyTo7.getZ().negate()), (RealFieldElement) applyInverseTo7.getX().acos(), (RealFieldElement) applyInverseTo7.getY().atan2(applyInverseTo7.getZ()));
            }
            throw new CardanEulerSingularityException(false);
        } else if (rotationOrder == RotationOrder.XZX) {
            FieldVector3D applyTo8 = applyTo(vector(1.0d, 0.0d, 0.0d));
            FieldVector3D applyInverseTo8 = applyInverseTo(vector(1.0d, 0.0d, 0.0d));
            if (applyInverseTo8.getX().getReal() >= -0.9999999999d && applyInverseTo8.getX().getReal() <= 0.9999999999d) {
                return buildArray((RealFieldElement) applyTo8.getZ().atan2(applyTo8.getY()), (RealFieldElement) applyInverseTo8.getX().acos(), (RealFieldElement) applyInverseTo8.getZ().atan2(applyInverseTo8.getY().negate()));
            }
            throw new CardanEulerSingularityException(false);
        } else if (rotationOrder == RotationOrder.YXY) {
            FieldVector3D applyTo9 = applyTo(vector(0.0d, 1.0d, 0.0d));
            FieldVector3D applyInverseTo9 = applyInverseTo(vector(0.0d, 1.0d, 0.0d));
            if (applyInverseTo9.getY().getReal() >= -0.9999999999d && applyInverseTo9.getY().getReal() <= 0.9999999999d) {
                return buildArray((RealFieldElement) applyTo9.getX().atan2(applyTo9.getZ()), (RealFieldElement) applyInverseTo9.getY().acos(), (RealFieldElement) applyInverseTo9.getX().atan2(applyInverseTo9.getZ().negate()));
            }
            throw new CardanEulerSingularityException(false);
        } else if (rotationOrder == RotationOrder.YZY) {
            FieldVector3D applyTo10 = applyTo(vector(0.0d, 1.0d, 0.0d));
            FieldVector3D applyInverseTo10 = applyInverseTo(vector(0.0d, 1.0d, 0.0d));
            if (applyInverseTo10.getY().getReal() >= -0.9999999999d && applyInverseTo10.getY().getReal() <= 0.9999999999d) {
                return buildArray((RealFieldElement) applyTo10.getZ().atan2(applyTo10.getX().negate()), (RealFieldElement) applyInverseTo10.getY().acos(), (RealFieldElement) applyInverseTo10.getZ().atan2(applyInverseTo10.getX()));
            }
            throw new CardanEulerSingularityException(false);
        } else if (rotationOrder == RotationOrder.ZXZ) {
            FieldVector3D applyTo11 = applyTo(vector(0.0d, 0.0d, 1.0d));
            FieldVector3D applyInverseTo11 = applyInverseTo(vector(0.0d, 0.0d, 1.0d));
            if (applyInverseTo11.getZ().getReal() >= -0.9999999999d && applyInverseTo11.getZ().getReal() <= 0.9999999999d) {
                return buildArray((RealFieldElement) applyTo11.getX().atan2(applyTo11.getY().negate()), (RealFieldElement) applyInverseTo11.getZ().acos(), (RealFieldElement) applyInverseTo11.getX().atan2(applyInverseTo11.getY()));
            }
            throw new CardanEulerSingularityException(false);
        } else {
            FieldVector3D applyTo12 = applyTo(vector(0.0d, 0.0d, 1.0d));
            FieldVector3D applyInverseTo12 = applyInverseTo(vector(0.0d, 0.0d, 1.0d));
            if (applyInverseTo12.getZ().getReal() >= -0.9999999999d && applyInverseTo12.getZ().getReal() <= 0.9999999999d) {
                return buildArray((RealFieldElement) applyTo12.getY().atan2(applyTo12.getX()), (RealFieldElement) applyInverseTo12.getZ().acos(), (RealFieldElement) applyInverseTo12.getY().atan2(applyInverseTo12.getX().negate()));
            }
            throw new CardanEulerSingularityException(false);
        }
    }

    private T[] buildArray(T t, T t2, T t3) {
        T[] tArr = (RealFieldElement[]) MathArrays.buildArray(t.getField(), 3);
        tArr[0] = t;
        tArr[1] = t2;
        tArr[2] = t3;
        return tArr;
    }

    private FieldVector3D<T> vector(double d, double d2, double d3) {
        RealFieldElement realFieldElement = (RealFieldElement) this.q0.getField().getZero();
        return new FieldVector3D<>((RealFieldElement) realFieldElement.add(d), (RealFieldElement) realFieldElement.add(d2), (RealFieldElement) realFieldElement.add(d3));
    }

    public T[][] getMatrix() {
        T t = this.q0;
        RealFieldElement realFieldElement = (RealFieldElement) t.multiply(t);
        RealFieldElement realFieldElement2 = (RealFieldElement) this.q0.multiply(this.q1);
        RealFieldElement realFieldElement3 = (RealFieldElement) this.q0.multiply(this.q2);
        RealFieldElement realFieldElement4 = (RealFieldElement) this.q0.multiply(this.q3);
        T t2 = this.q1;
        RealFieldElement realFieldElement5 = (RealFieldElement) this.q1.multiply(this.q2);
        RealFieldElement realFieldElement6 = (RealFieldElement) this.q1.multiply(this.q3);
        T t3 = this.q2;
        RealFieldElement realFieldElement7 = (RealFieldElement) this.q2.multiply(this.q3);
        T t4 = this.q3;
        T[][] tArr = (RealFieldElement[][]) MathArrays.buildArray(this.q0.getField(), 3, 3);
        tArr[0][0] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement.add((RealFieldElement) t2.multiply(t2))).multiply(2)).subtract(1.0d);
        tArr[1][0] = (RealFieldElement) ((RealFieldElement) realFieldElement5.subtract(realFieldElement4)).multiply(2);
        tArr[2][0] = (RealFieldElement) ((RealFieldElement) realFieldElement6.add(realFieldElement3)).multiply(2);
        tArr[0][1] = (RealFieldElement) ((RealFieldElement) realFieldElement5.add(realFieldElement4)).multiply(2);
        tArr[1][1] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement.add((RealFieldElement) t3.multiply(t3))).multiply(2)).subtract(1.0d);
        tArr[2][1] = (RealFieldElement) ((RealFieldElement) realFieldElement7.subtract(realFieldElement2)).multiply(2);
        tArr[0][2] = (RealFieldElement) ((RealFieldElement) realFieldElement6.subtract(realFieldElement3)).multiply(2);
        tArr[1][2] = (RealFieldElement) ((RealFieldElement) realFieldElement7.add(realFieldElement2)).multiply(2);
        tArr[2][2] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement.add((RealFieldElement) t4.multiply(t4))).multiply(2)).subtract(1.0d);
        return tArr;
    }

    public Rotation toRotation() {
        return new Rotation(this.q0.getReal(), this.q1.getReal(), this.q2.getReal(), this.q3.getReal(), false);
    }

    public FieldVector3D<T> applyTo(FieldVector3D<T> fieldVector3D) {
        T x = fieldVector3D.getX();
        T y = fieldVector3D.getY();
        T z = fieldVector3D.getZ();
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q1.multiply(x)).add(this.q2.multiply(y))).add(this.q3.multiply(z));
        T t = this.q0;
        T t2 = this.q0;
        T t3 = this.q0;
        return new FieldVector3D<>((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t.multiply(((RealFieldElement) x.multiply(t)).subtract(((RealFieldElement) this.q2.multiply(z)).subtract(this.q3.multiply(y))))).add(realFieldElement.multiply(this.q1))).multiply(2)).subtract(x), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t2.multiply(((RealFieldElement) y.multiply(t2)).subtract(((RealFieldElement) this.q3.multiply(x)).subtract(this.q1.multiply(z))))).add(realFieldElement.multiply(this.q2))).multiply(2)).subtract(y), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t3.multiply(((RealFieldElement) z.multiply(t3)).subtract(((RealFieldElement) this.q1.multiply(y)).subtract(this.q2.multiply(x))))).add(realFieldElement.multiply(this.q3))).multiply(2)).subtract(z));
    }

    public FieldVector3D<T> applyTo(Vector3D vector3D) {
        double x = vector3D.getX();
        double y = vector3D.getY();
        double z = vector3D.getZ();
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q1.multiply(x)).add(this.q2.multiply(y))).add(this.q3.multiply(z));
        T t = this.q0;
        T t2 = this.q0;
        T t3 = this.q0;
        return new FieldVector3D<>((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t.multiply(((RealFieldElement) t.multiply(x)).subtract(((RealFieldElement) this.q2.multiply(z)).subtract(this.q3.multiply(y))))).add(realFieldElement.multiply(this.q1))).multiply(2)).subtract(x), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t2.multiply(((RealFieldElement) t2.multiply(y)).subtract(((RealFieldElement) this.q3.multiply(x)).subtract(this.q1.multiply(z))))).add(realFieldElement.multiply(this.q2))).multiply(2)).subtract(y), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t3.multiply(((RealFieldElement) t3.multiply(z)).subtract(((RealFieldElement) this.q1.multiply(y)).subtract(this.q2.multiply(x))))).add(realFieldElement.multiply(this.q3))).multiply(2)).subtract(z));
    }

    public void applyTo(T[] tArr, T[] tArr2) {
        T t = tArr[0];
        T t2 = tArr[1];
        T t3 = tArr[2];
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q1.multiply(t)).add(this.q2.multiply(t2))).add(this.q3.multiply(t3));
        T t4 = this.q0;
        tArr2[0] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t4.multiply(((RealFieldElement) t.multiply(t4)).subtract(((RealFieldElement) this.q2.multiply(t3)).subtract(this.q3.multiply(t2))))).add(realFieldElement.multiply(this.q1))).multiply(2)).subtract(t);
        T t5 = this.q0;
        tArr2[1] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t5.multiply(((RealFieldElement) t2.multiply(t5)).subtract(((RealFieldElement) this.q3.multiply(t)).subtract(this.q1.multiply(t3))))).add(realFieldElement.multiply(this.q2))).multiply(2)).subtract(t2);
        T t6 = this.q0;
        tArr2[2] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t6.multiply(((RealFieldElement) t3.multiply(t6)).subtract(((RealFieldElement) this.q1.multiply(t2)).subtract(this.q2.multiply(t))))).add(realFieldElement.multiply(this.q3))).multiply(2)).subtract(t3);
    }

    public void applyTo(double[] dArr, T[] tArr) {
        double d = dArr[0];
        double d2 = dArr[1];
        double d3 = dArr[2];
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q1.multiply(d)).add(this.q2.multiply(d2))).add(this.q3.multiply(d3));
        T t = this.q0;
        tArr[0] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t.multiply(((RealFieldElement) t.multiply(d)).subtract(((RealFieldElement) this.q2.multiply(d3)).subtract(this.q3.multiply(d2))))).add(realFieldElement.multiply(this.q1))).multiply(2)).subtract(d);
        T t2 = this.q0;
        tArr[1] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t2.multiply(((RealFieldElement) t2.multiply(d2)).subtract(((RealFieldElement) this.q3.multiply(d)).subtract(this.q1.multiply(d3))))).add(realFieldElement.multiply(this.q2))).multiply(2)).subtract(d2);
        T t3 = this.q0;
        tArr[2] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t3.multiply(((RealFieldElement) t3.multiply(d3)).subtract(((RealFieldElement) this.q1.multiply(d2)).subtract(this.q2.multiply(d))))).add(realFieldElement.multiply(this.q3))).multiply(2)).subtract(d3);
    }

    public static <T extends RealFieldElement<T>> FieldVector3D<T> applyTo(Rotation rotation, FieldVector3D<T> fieldVector3D) {
        T x = fieldVector3D.getX();
        T y = fieldVector3D.getY();
        T z = fieldVector3D.getZ();
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) x.multiply(rotation.getQ1())).add(y.multiply(rotation.getQ2()))).add(z.multiply(rotation.getQ3()));
        return new FieldVector3D<>((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) x.multiply(rotation.getQ0())).subtract(((RealFieldElement) z.multiply(rotation.getQ2())).subtract(y.multiply(rotation.getQ3())))).multiply(rotation.getQ0())).add(realFieldElement.multiply(rotation.getQ1()))).multiply(2)).subtract(x), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) y.multiply(rotation.getQ0())).subtract(((RealFieldElement) x.multiply(rotation.getQ3())).subtract(z.multiply(rotation.getQ1())))).multiply(rotation.getQ0())).add(realFieldElement.multiply(rotation.getQ2()))).multiply(2)).subtract(y), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) z.multiply(rotation.getQ0())).subtract(((RealFieldElement) y.multiply(rotation.getQ1())).subtract(x.multiply(rotation.getQ2())))).multiply(rotation.getQ0())).add(realFieldElement.multiply(rotation.getQ3()))).multiply(2)).subtract(z));
    }

    public FieldVector3D<T> applyInverseTo(FieldVector3D<T> fieldVector3D) {
        T x = fieldVector3D.getX();
        T y = fieldVector3D.getY();
        T z = fieldVector3D.getZ();
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q1.multiply(x)).add(this.q2.multiply(y))).add(this.q3.multiply(z));
        RealFieldElement realFieldElement2 = (RealFieldElement) this.q0.negate();
        return new FieldVector3D<>((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply(((RealFieldElement) x.multiply(realFieldElement2)).subtract(((RealFieldElement) this.q2.multiply(z)).subtract(this.q3.multiply(y))))).add(realFieldElement.multiply(this.q1))).multiply(2)).subtract(x), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply(((RealFieldElement) y.multiply(realFieldElement2)).subtract(((RealFieldElement) this.q3.multiply(x)).subtract(this.q1.multiply(z))))).add(realFieldElement.multiply(this.q2))).multiply(2)).subtract(y), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply(((RealFieldElement) z.multiply(realFieldElement2)).subtract(((RealFieldElement) this.q1.multiply(y)).subtract(this.q2.multiply(x))))).add(realFieldElement.multiply(this.q3))).multiply(2)).subtract(z));
    }

    public FieldVector3D<T> applyInverseTo(Vector3D vector3D) {
        double x = vector3D.getX();
        double y = vector3D.getY();
        double z = vector3D.getZ();
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q1.multiply(x)).add(this.q2.multiply(y))).add(this.q3.multiply(z));
        RealFieldElement realFieldElement2 = (RealFieldElement) this.q0.negate();
        return new FieldVector3D<>((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply(((RealFieldElement) realFieldElement2.multiply(x)).subtract(((RealFieldElement) this.q2.multiply(z)).subtract(this.q3.multiply(y))))).add(realFieldElement.multiply(this.q1))).multiply(2)).subtract(x), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply(((RealFieldElement) realFieldElement2.multiply(y)).subtract(((RealFieldElement) this.q3.multiply(x)).subtract(this.q1.multiply(z))))).add(realFieldElement.multiply(this.q2))).multiply(2)).subtract(y), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply(((RealFieldElement) realFieldElement2.multiply(z)).subtract(((RealFieldElement) this.q1.multiply(y)).subtract(this.q2.multiply(x))))).add(realFieldElement.multiply(this.q3))).multiply(2)).subtract(z));
    }

    public void applyInverseTo(T[] tArr, T[] tArr2) {
        T t = tArr[0];
        T t2 = tArr[1];
        T t3 = tArr[2];
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q1.multiply(t)).add(this.q2.multiply(t2))).add(this.q3.multiply(t3));
        RealFieldElement realFieldElement2 = (RealFieldElement) this.q0.negate();
        tArr2[0] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply(((RealFieldElement) t.multiply(realFieldElement2)).subtract(((RealFieldElement) this.q2.multiply(t3)).subtract(this.q3.multiply(t2))))).add(realFieldElement.multiply(this.q1))).multiply(2)).subtract(t);
        tArr2[1] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply(((RealFieldElement) t2.multiply(realFieldElement2)).subtract(((RealFieldElement) this.q3.multiply(t)).subtract(this.q1.multiply(t3))))).add(realFieldElement.multiply(this.q2))).multiply(2)).subtract(t2);
        tArr2[2] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply(((RealFieldElement) t3.multiply(realFieldElement2)).subtract(((RealFieldElement) this.q1.multiply(t2)).subtract(this.q2.multiply(t))))).add(realFieldElement.multiply(this.q3))).multiply(2)).subtract(t3);
    }

    public void applyInverseTo(double[] dArr, T[] tArr) {
        double d = dArr[0];
        double d2 = dArr[1];
        double d3 = dArr[2];
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q1.multiply(d)).add(this.q2.multiply(d2))).add(this.q3.multiply(d3));
        RealFieldElement realFieldElement2 = (RealFieldElement) this.q0.negate();
        tArr[0] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply(((RealFieldElement) realFieldElement2.multiply(d)).subtract(((RealFieldElement) this.q2.multiply(d3)).subtract(this.q3.multiply(d2))))).add(realFieldElement.multiply(this.q1))).multiply(2)).subtract(d);
        tArr[1] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply(((RealFieldElement) realFieldElement2.multiply(d2)).subtract(((RealFieldElement) this.q3.multiply(d)).subtract(this.q1.multiply(d3))))).add(realFieldElement.multiply(this.q2))).multiply(2)).subtract(d2);
        tArr[2] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply(((RealFieldElement) realFieldElement2.multiply(d3)).subtract(((RealFieldElement) this.q1.multiply(d2)).subtract(this.q2.multiply(d))))).add(realFieldElement.multiply(this.q3))).multiply(2)).subtract(d3);
    }

    public static <T extends RealFieldElement<T>> FieldVector3D<T> applyInverseTo(Rotation rotation, FieldVector3D<T> fieldVector3D) {
        T x = fieldVector3D.getX();
        T y = fieldVector3D.getY();
        T z = fieldVector3D.getZ();
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) x.multiply(rotation.getQ1())).add(y.multiply(rotation.getQ2()))).add(z.multiply(rotation.getQ3()));
        double d = -rotation.getQ0();
        return new FieldVector3D<>((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) x.multiply(d)).subtract(((RealFieldElement) z.multiply(rotation.getQ2())).subtract(y.multiply(rotation.getQ3())))).multiply(d)).add(realFieldElement.multiply(rotation.getQ1()))).multiply(2)).subtract(x), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) y.multiply(d)).subtract(((RealFieldElement) x.multiply(rotation.getQ3())).subtract(z.multiply(rotation.getQ1())))).multiply(d)).add(realFieldElement.multiply(rotation.getQ2()))).multiply(2)).subtract(y), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) z.multiply(d)).subtract(((RealFieldElement) y.multiply(rotation.getQ1())).subtract(x.multiply(rotation.getQ2())))).multiply(d)).add(realFieldElement.multiply(rotation.getQ3()))).multiply(2)).subtract(z));
    }

    public FieldRotation<T> applyTo(FieldRotation<T> fieldRotation) {
        return new FieldRotation((RealFieldElement) ((RealFieldElement) fieldRotation.q0.multiply(this.q0)).subtract(((RealFieldElement) ((RealFieldElement) fieldRotation.q1.multiply(this.q1)).add(fieldRotation.q2.multiply(this.q2))).add(fieldRotation.q3.multiply(this.q3))), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) fieldRotation.q1.multiply(this.q0)).add(fieldRotation.q0.multiply(this.q1))).add(((RealFieldElement) fieldRotation.q2.multiply(this.q3)).subtract(fieldRotation.q3.multiply(this.q2))), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) fieldRotation.q2.multiply(this.q0)).add(fieldRotation.q0.multiply(this.q2))).add(((RealFieldElement) fieldRotation.q3.multiply(this.q1)).subtract(fieldRotation.q1.multiply(this.q3))), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) fieldRotation.q3.multiply(this.q0)).add(fieldRotation.q0.multiply(this.q3))).add(((RealFieldElement) fieldRotation.q1.multiply(this.q2)).subtract(fieldRotation.q2.multiply(this.q1))), false);
    }

    public FieldRotation<T> applyTo(Rotation rotation) {
        return new FieldRotation((RealFieldElement) ((RealFieldElement) this.q0.multiply(rotation.getQ0())).subtract(((RealFieldElement) ((RealFieldElement) this.q1.multiply(rotation.getQ1())).add(this.q2.multiply(rotation.getQ2()))).add(this.q3.multiply(rotation.getQ3()))), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q0.multiply(rotation.getQ1())).add(this.q1.multiply(rotation.getQ0()))).add(((RealFieldElement) this.q3.multiply(rotation.getQ2())).subtract(this.q2.multiply(rotation.getQ3()))), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q0.multiply(rotation.getQ2())).add(this.q2.multiply(rotation.getQ0()))).add(((RealFieldElement) this.q1.multiply(rotation.getQ3())).subtract(this.q3.multiply(rotation.getQ1()))), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q0.multiply(rotation.getQ3())).add(this.q3.multiply(rotation.getQ0()))).add(((RealFieldElement) this.q2.multiply(rotation.getQ1())).subtract(this.q1.multiply(rotation.getQ2()))), false);
    }

    public static <T extends RealFieldElement<T>> FieldRotation<T> applyTo(Rotation rotation, FieldRotation<T> fieldRotation) {
        return new FieldRotation((RealFieldElement) ((RealFieldElement) fieldRotation.q0.multiply(rotation.getQ0())).subtract(((RealFieldElement) ((RealFieldElement) fieldRotation.q1.multiply(rotation.getQ1())).add(fieldRotation.q2.multiply(rotation.getQ2()))).add(fieldRotation.q3.multiply(rotation.getQ3()))), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) fieldRotation.q1.multiply(rotation.getQ0())).add(fieldRotation.q0.multiply(rotation.getQ1()))).add(((RealFieldElement) fieldRotation.q2.multiply(rotation.getQ3())).subtract(fieldRotation.q3.multiply(rotation.getQ2()))), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) fieldRotation.q2.multiply(rotation.getQ0())).add(fieldRotation.q0.multiply(rotation.getQ2()))).add(((RealFieldElement) fieldRotation.q3.multiply(rotation.getQ1())).subtract(fieldRotation.q1.multiply(rotation.getQ3()))), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) fieldRotation.q3.multiply(rotation.getQ0())).add(fieldRotation.q0.multiply(rotation.getQ3()))).add(((RealFieldElement) fieldRotation.q1.multiply(rotation.getQ2())).subtract(fieldRotation.q2.multiply(rotation.getQ1()))), false);
    }

    public FieldRotation<T> applyInverseTo(FieldRotation<T> fieldRotation) {
        return new FieldRotation((RealFieldElement) ((RealFieldElement) ((RealFieldElement) fieldRotation.q0.multiply(this.q0)).add(((RealFieldElement) ((RealFieldElement) fieldRotation.q1.multiply(this.q1)).add(fieldRotation.q2.multiply(this.q2))).add(fieldRotation.q3.multiply(this.q3)))).negate(), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) fieldRotation.q0.multiply(this.q1)).add(((RealFieldElement) fieldRotation.q2.multiply(this.q3)).subtract(fieldRotation.q3.multiply(this.q2)))).subtract(fieldRotation.q1.multiply(this.q0)), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) fieldRotation.q0.multiply(this.q2)).add(((RealFieldElement) fieldRotation.q3.multiply(this.q1)).subtract(fieldRotation.q1.multiply(this.q3)))).subtract(fieldRotation.q2.multiply(this.q0)), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) fieldRotation.q0.multiply(this.q3)).add(((RealFieldElement) fieldRotation.q1.multiply(this.q2)).subtract(fieldRotation.q2.multiply(this.q1)))).subtract(fieldRotation.q3.multiply(this.q0)), false);
    }

    public FieldRotation<T> applyInverseTo(Rotation rotation) {
        return new FieldRotation((RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q0.multiply(rotation.getQ0())).add(((RealFieldElement) ((RealFieldElement) this.q1.multiply(rotation.getQ1())).add(this.q2.multiply(rotation.getQ2()))).add(this.q3.multiply(rotation.getQ3())))).negate(), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q1.multiply(rotation.getQ0())).add(((RealFieldElement) this.q3.multiply(rotation.getQ2())).subtract(this.q2.multiply(rotation.getQ3())))).subtract(this.q0.multiply(rotation.getQ1())), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q2.multiply(rotation.getQ0())).add(((RealFieldElement) this.q1.multiply(rotation.getQ3())).subtract(this.q3.multiply(rotation.getQ1())))).subtract(this.q0.multiply(rotation.getQ2())), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q3.multiply(rotation.getQ0())).add(((RealFieldElement) this.q2.multiply(rotation.getQ1())).subtract(this.q1.multiply(rotation.getQ2())))).subtract(this.q0.multiply(rotation.getQ3())), false);
    }

    public static <T extends RealFieldElement<T>> FieldRotation<T> applyInverseTo(Rotation rotation, FieldRotation<T> fieldRotation) {
        return new FieldRotation((RealFieldElement) ((RealFieldElement) ((RealFieldElement) fieldRotation.q0.multiply(rotation.getQ0())).add(((RealFieldElement) ((RealFieldElement) fieldRotation.q1.multiply(rotation.getQ1())).add(fieldRotation.q2.multiply(rotation.getQ2()))).add(fieldRotation.q3.multiply(rotation.getQ3())))).negate(), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) fieldRotation.q0.multiply(rotation.getQ1())).add(((RealFieldElement) fieldRotation.q2.multiply(rotation.getQ3())).subtract(fieldRotation.q3.multiply(rotation.getQ2())))).subtract(fieldRotation.q1.multiply(rotation.getQ0())), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) fieldRotation.q0.multiply(rotation.getQ2())).add(((RealFieldElement) fieldRotation.q3.multiply(rotation.getQ1())).subtract(fieldRotation.q1.multiply(rotation.getQ3())))).subtract(fieldRotation.q2.multiply(rotation.getQ0())), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) fieldRotation.q0.multiply(rotation.getQ3())).add(((RealFieldElement) fieldRotation.q1.multiply(rotation.getQ2())).subtract(fieldRotation.q2.multiply(rotation.getQ1())))).subtract(fieldRotation.q3.multiply(rotation.getQ0())), false);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v48, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v15, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v16, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v13, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v21, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private T[][] orthogonalizeMatrix(T[][] r32, double r33) throws org.apache.commons.math3.geometry.euclidean.threed.NotARotationMatrixException {
        /*
            r31 = this;
            r0 = 0
            r1 = r32[r0]
            r2 = r1[r0]
            r3 = 1
            r4 = r1[r3]
            r5 = 2
            r1 = r1[r5]
            r6 = r32[r3]
            r7 = r6[r0]
            r8 = r6[r3]
            r6 = r6[r5]
            r9 = r32[r5]
            r10 = r9[r0]
            r11 = r9[r3]
            r9 = r9[r5]
            org.apache.commons.math3.Field r12 = r2.getField()
            r13 = 3
            java.lang.Object[][] r12 = org.apache.commons.math3.util.MathArrays.buildArray(r12, r13, r13)
            org.apache.commons.math3.RealFieldElement[][] r12 = (org.apache.commons.math3.RealFieldElement[][]) r12
            r13 = 0
            r15 = r0
        L_0x0029:
            int r5 = r15 + 1
            r3 = 11
            if (r5 >= r3) goto L_0x049b
            r3 = r32[r0]
            r3 = r3[r0]
            java.lang.Object r3 = r3.multiply(r2)
            org.apache.commons.math3.RealFieldElement r3 = (org.apache.commons.math3.RealFieldElement) r3
            r15 = 1
            r17 = r32[r15]
            r15 = r17[r0]
            java.lang.Object r15 = r15.multiply(r7)
            java.lang.Object r3 = r3.add(r15)
            org.apache.commons.math3.RealFieldElement r3 = (org.apache.commons.math3.RealFieldElement) r3
            r15 = 2
            r17 = r32[r15]
            r15 = r17[r0]
            java.lang.Object r15 = r15.multiply(r10)
            java.lang.Object r3 = r3.add(r15)
            org.apache.commons.math3.RealFieldElement r3 = (org.apache.commons.math3.RealFieldElement) r3
            r15 = r32[r0]
            r17 = 1
            r15 = r15[r17]
            java.lang.Object r15 = r15.multiply(r2)
            org.apache.commons.math3.RealFieldElement r15 = (org.apache.commons.math3.RealFieldElement) r15
            r18 = r32[r17]
            r0 = r18[r17]
            java.lang.Object r0 = r0.multiply(r7)
            java.lang.Object r0 = r15.add(r0)
            org.apache.commons.math3.RealFieldElement r0 = (org.apache.commons.math3.RealFieldElement) r0
            r15 = 2
            r16 = r32[r15]
            r15 = r16[r17]
            java.lang.Object r15 = r15.multiply(r10)
            java.lang.Object r0 = r0.add(r15)
            org.apache.commons.math3.RealFieldElement r0 = (org.apache.commons.math3.RealFieldElement) r0
            r15 = 0
            r16 = r32[r15]
            r18 = r5
            r15 = 2
            r5 = r16[r15]
            java.lang.Object r5 = r5.multiply(r2)
            org.apache.commons.math3.RealFieldElement r5 = (org.apache.commons.math3.RealFieldElement) r5
            r16 = r32[r17]
            r20 = r13
            r13 = r16[r15]
            java.lang.Object r13 = r13.multiply(r7)
            java.lang.Object r5 = r5.add(r13)
            org.apache.commons.math3.RealFieldElement r5 = (org.apache.commons.math3.RealFieldElement) r5
            r13 = r32[r15]
            r13 = r13[r15]
            java.lang.Object r13 = r13.multiply(r10)
            java.lang.Object r5 = r5.add(r13)
            org.apache.commons.math3.RealFieldElement r5 = (org.apache.commons.math3.RealFieldElement) r5
            r13 = 0
            r14 = r32[r13]
            r14 = r14[r13]
            java.lang.Object r14 = r14.multiply(r4)
            org.apache.commons.math3.RealFieldElement r14 = (org.apache.commons.math3.RealFieldElement) r14
            r15 = 1
            r17 = r32[r15]
            r15 = r17[r13]
            java.lang.Object r15 = r15.multiply(r8)
            java.lang.Object r14 = r14.add(r15)
            org.apache.commons.math3.RealFieldElement r14 = (org.apache.commons.math3.RealFieldElement) r14
            r15 = 2
            r17 = r32[r15]
            r15 = r17[r13]
            java.lang.Object r15 = r15.multiply(r11)
            java.lang.Object r14 = r14.add(r15)
            org.apache.commons.math3.RealFieldElement r14 = (org.apache.commons.math3.RealFieldElement) r14
            r15 = r32[r13]
            r13 = 1
            r15 = r15[r13]
            java.lang.Object r15 = r15.multiply(r4)
            org.apache.commons.math3.RealFieldElement r15 = (org.apache.commons.math3.RealFieldElement) r15
            r17 = r32[r13]
            r22 = r10
            r10 = r17[r13]
            java.lang.Object r10 = r10.multiply(r8)
            java.lang.Object r10 = r15.add(r10)
            org.apache.commons.math3.RealFieldElement r10 = (org.apache.commons.math3.RealFieldElement) r10
            r15 = 2
            r16 = r32[r15]
            r15 = r16[r13]
            java.lang.Object r15 = r15.multiply(r11)
            java.lang.Object r10 = r10.add(r15)
            org.apache.commons.math3.RealFieldElement r10 = (org.apache.commons.math3.RealFieldElement) r10
            r15 = 0
            r16 = r32[r15]
            r15 = 2
            r13 = r16[r15]
            java.lang.Object r13 = r13.multiply(r4)
            org.apache.commons.math3.RealFieldElement r13 = (org.apache.commons.math3.RealFieldElement) r13
            r16 = 1
            r23 = r32[r16]
            r24 = r7
            r7 = r23[r15]
            java.lang.Object r7 = r7.multiply(r8)
            java.lang.Object r7 = r13.add(r7)
            org.apache.commons.math3.RealFieldElement r7 = (org.apache.commons.math3.RealFieldElement) r7
            r13 = r32[r15]
            r13 = r13[r15]
            java.lang.Object r13 = r13.multiply(r11)
            java.lang.Object r7 = r7.add(r13)
            org.apache.commons.math3.RealFieldElement r7 = (org.apache.commons.math3.RealFieldElement) r7
            r13 = 0
            r15 = r32[r13]
            r15 = r15[r13]
            java.lang.Object r15 = r15.multiply(r1)
            org.apache.commons.math3.RealFieldElement r15 = (org.apache.commons.math3.RealFieldElement) r15
            r17 = 1
            r19 = r32[r17]
            r23 = r11
            r11 = r19[r13]
            java.lang.Object r11 = r11.multiply(r6)
            java.lang.Object r11 = r15.add(r11)
            org.apache.commons.math3.RealFieldElement r11 = (org.apache.commons.math3.RealFieldElement) r11
            r15 = 2
            r19 = r32[r15]
            r15 = r19[r13]
            java.lang.Object r15 = r15.multiply(r9)
            java.lang.Object r11 = r11.add(r15)
            org.apache.commons.math3.RealFieldElement r11 = (org.apache.commons.math3.RealFieldElement) r11
            r15 = r32[r13]
            r13 = r15[r17]
            java.lang.Object r13 = r13.multiply(r1)
            org.apache.commons.math3.RealFieldElement r13 = (org.apache.commons.math3.RealFieldElement) r13
            r15 = r32[r17]
            r15 = r15[r17]
            java.lang.Object r15 = r15.multiply(r6)
            java.lang.Object r13 = r13.add(r15)
            org.apache.commons.math3.RealFieldElement r13 = (org.apache.commons.math3.RealFieldElement) r13
            r15 = 2
            r16 = r32[r15]
            r15 = r16[r17]
            java.lang.Object r15 = r15.multiply(r9)
            java.lang.Object r13 = r13.add(r15)
            org.apache.commons.math3.RealFieldElement r13 = (org.apache.commons.math3.RealFieldElement) r13
            r15 = 0
            r16 = r32[r15]
            r25 = r8
            r15 = 2
            r8 = r16[r15]
            java.lang.Object r8 = r8.multiply(r1)
            org.apache.commons.math3.RealFieldElement r8 = (org.apache.commons.math3.RealFieldElement) r8
            r16 = r32[r17]
            r26 = r13
            r13 = r16[r15]
            java.lang.Object r13 = r13.multiply(r6)
            java.lang.Object r8 = r8.add(r13)
            org.apache.commons.math3.RealFieldElement r8 = (org.apache.commons.math3.RealFieldElement) r8
            r13 = r32[r15]
            r13 = r13[r15]
            java.lang.Object r13 = r13.multiply(r9)
            java.lang.Object r8 = r8.add(r13)
            org.apache.commons.math3.RealFieldElement r8 = (org.apache.commons.math3.RealFieldElement) r8
            r13 = 0
            r15 = r12[r13]
            java.lang.Object r19 = r2.multiply(r3)
            r13 = r19
            org.apache.commons.math3.RealFieldElement r13 = (org.apache.commons.math3.RealFieldElement) r13
            r28 = r9
            java.lang.Object r9 = r4.multiply(r0)
            java.lang.Object r9 = r13.add(r9)
            org.apache.commons.math3.RealFieldElement r9 = (org.apache.commons.math3.RealFieldElement) r9
            java.lang.Object r13 = r1.multiply(r5)
            java.lang.Object r9 = r9.add(r13)
            org.apache.commons.math3.RealFieldElement r9 = (org.apache.commons.math3.RealFieldElement) r9
            r13 = 0
            r19 = r32[r13]
            r27 = r5
            r5 = r19[r13]
            java.lang.Object r5 = r9.subtract(r5)
            org.apache.commons.math3.RealFieldElement r5 = (org.apache.commons.math3.RealFieldElement) r5
            r9 = r14
            r13 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            java.lang.Object r5 = r5.multiply(r13)
            java.lang.Object r5 = r2.subtract(r5)
            org.apache.commons.math3.RealFieldElement r5 = (org.apache.commons.math3.RealFieldElement) r5
            r19 = 0
            r15[r19] = r5
            r5 = r12[r19]
            java.lang.Object r15 = r2.multiply(r9)
            org.apache.commons.math3.RealFieldElement r15 = (org.apache.commons.math3.RealFieldElement) r15
            java.lang.Object r13 = r4.multiply(r10)
            java.lang.Object r13 = r15.add(r13)
            org.apache.commons.math3.RealFieldElement r13 = (org.apache.commons.math3.RealFieldElement) r13
            java.lang.Object r14 = r1.multiply(r7)
            java.lang.Object r13 = r13.add(r14)
            org.apache.commons.math3.RealFieldElement r13 = (org.apache.commons.math3.RealFieldElement) r13
            r14 = r32[r19]
            r15 = 1
            r14 = r14[r15]
            java.lang.Object r13 = r13.subtract(r14)
            org.apache.commons.math3.RealFieldElement r13 = (org.apache.commons.math3.RealFieldElement) r13
            r30 = r9
            r14 = r10
            r9 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            java.lang.Object r13 = r13.multiply(r9)
            java.lang.Object r9 = r4.subtract(r13)
            org.apache.commons.math3.RealFieldElement r9 = (org.apache.commons.math3.RealFieldElement) r9
            r5[r15] = r9
            r5 = r12[r19]
            java.lang.Object r2 = r2.multiply(r11)
            org.apache.commons.math3.RealFieldElement r2 = (org.apache.commons.math3.RealFieldElement) r2
            r13 = r26
            java.lang.Object r4 = r4.multiply(r13)
            java.lang.Object r2 = r2.add(r4)
            org.apache.commons.math3.RealFieldElement r2 = (org.apache.commons.math3.RealFieldElement) r2
            java.lang.Object r4 = r1.multiply(r8)
            java.lang.Object r2 = r2.add(r4)
            org.apache.commons.math3.RealFieldElement r2 = (org.apache.commons.math3.RealFieldElement) r2
            r4 = r32[r19]
            r9 = 2
            r4 = r4[r9]
            java.lang.Object r2 = r2.subtract(r4)
            org.apache.commons.math3.RealFieldElement r2 = (org.apache.commons.math3.RealFieldElement) r2
            r9 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            java.lang.Object r2 = r2.multiply(r9)
            java.lang.Object r1 = r1.subtract(r2)
            org.apache.commons.math3.RealFieldElement r1 = (org.apache.commons.math3.RealFieldElement) r1
            r2 = 2
            r5[r2] = r1
            r1 = 1
            r2 = r12[r1]
            r4 = r24
            java.lang.Object r5 = r4.multiply(r3)
            org.apache.commons.math3.RealFieldElement r5 = (org.apache.commons.math3.RealFieldElement) r5
            r9 = r25
            java.lang.Object r10 = r9.multiply(r0)
            java.lang.Object r5 = r5.add(r10)
            org.apache.commons.math3.RealFieldElement r5 = (org.apache.commons.math3.RealFieldElement) r5
            r10 = r27
            java.lang.Object r15 = r6.multiply(r10)
            java.lang.Object r5 = r5.add(r15)
            org.apache.commons.math3.RealFieldElement r5 = (org.apache.commons.math3.RealFieldElement) r5
            r15 = r32[r1]
            r17 = 0
            r15 = r15[r17]
            java.lang.Object r5 = r5.subtract(r15)
            org.apache.commons.math3.RealFieldElement r5 = (org.apache.commons.math3.RealFieldElement) r5
            r19 = r2
            r1 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            java.lang.Object r5 = r5.multiply(r1)
            java.lang.Object r1 = r4.subtract(r5)
            org.apache.commons.math3.RealFieldElement r1 = (org.apache.commons.math3.RealFieldElement) r1
            r19[r17] = r1
            r1 = 1
            r2 = r12[r1]
            r5 = r30
            java.lang.Object r15 = r4.multiply(r5)
            org.apache.commons.math3.RealFieldElement r15 = (org.apache.commons.math3.RealFieldElement) r15
            java.lang.Object r1 = r9.multiply(r14)
            java.lang.Object r1 = r15.add(r1)
            org.apache.commons.math3.RealFieldElement r1 = (org.apache.commons.math3.RealFieldElement) r1
            java.lang.Object r15 = r6.multiply(r7)
            java.lang.Object r1 = r1.add(r15)
            org.apache.commons.math3.RealFieldElement r1 = (org.apache.commons.math3.RealFieldElement) r1
            r15 = 1
            r17 = r32[r15]
            r24 = r7
            r7 = r17[r15]
            java.lang.Object r1 = r1.subtract(r7)
            org.apache.commons.math3.RealFieldElement r1 = (org.apache.commons.math3.RealFieldElement) r1
            r7 = r6
            r5 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            java.lang.Object r1 = r1.multiply(r5)
            java.lang.Object r1 = r9.subtract(r1)
            org.apache.commons.math3.RealFieldElement r1 = (org.apache.commons.math3.RealFieldElement) r1
            r2[r15] = r1
            r1 = r12[r15]
            java.lang.Object r2 = r4.multiply(r11)
            org.apache.commons.math3.RealFieldElement r2 = (org.apache.commons.math3.RealFieldElement) r2
            java.lang.Object r4 = r9.multiply(r13)
            java.lang.Object r2 = r2.add(r4)
            org.apache.commons.math3.RealFieldElement r2 = (org.apache.commons.math3.RealFieldElement) r2
            java.lang.Object r4 = r7.multiply(r8)
            java.lang.Object r2 = r2.add(r4)
            org.apache.commons.math3.RealFieldElement r2 = (org.apache.commons.math3.RealFieldElement) r2
            r4 = r32[r15]
            r5 = 2
            r4 = r4[r5]
            java.lang.Object r2 = r2.subtract(r4)
            org.apache.commons.math3.RealFieldElement r2 = (org.apache.commons.math3.RealFieldElement) r2
            r5 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            java.lang.Object r2 = r2.multiply(r5)
            java.lang.Object r2 = r7.subtract(r2)
            org.apache.commons.math3.RealFieldElement r2 = (org.apache.commons.math3.RealFieldElement) r2
            r4 = 2
            r1[r4] = r2
            r1 = r12[r4]
            r2 = r22
            java.lang.Object r3 = r2.multiply(r3)
            org.apache.commons.math3.RealFieldElement r3 = (org.apache.commons.math3.RealFieldElement) r3
            r5 = r23
            java.lang.Object r0 = r5.multiply(r0)
            java.lang.Object r0 = r3.add(r0)
            org.apache.commons.math3.RealFieldElement r0 = (org.apache.commons.math3.RealFieldElement) r0
            r9 = r28
            java.lang.Object r3 = r9.multiply(r10)
            java.lang.Object r0 = r0.add(r3)
            org.apache.commons.math3.RealFieldElement r0 = (org.apache.commons.math3.RealFieldElement) r0
            r3 = r32[r4]
            r6 = 0
            r3 = r3[r6]
            java.lang.Object r0 = r0.subtract(r3)
            org.apache.commons.math3.RealFieldElement r0 = (org.apache.commons.math3.RealFieldElement) r0
            r4 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            java.lang.Object r0 = r0.multiply(r4)
            java.lang.Object r0 = r2.subtract(r0)
            org.apache.commons.math3.RealFieldElement r0 = (org.apache.commons.math3.RealFieldElement) r0
            r1[r6] = r0
            r0 = 2
            r1 = r12[r0]
            r3 = r30
            java.lang.Object r3 = r2.multiply(r3)
            org.apache.commons.math3.RealFieldElement r3 = (org.apache.commons.math3.RealFieldElement) r3
            r4 = r23
            java.lang.Object r5 = r4.multiply(r14)
            java.lang.Object r3 = r3.add(r5)
            org.apache.commons.math3.RealFieldElement r3 = (org.apache.commons.math3.RealFieldElement) r3
            r7 = r24
            java.lang.Object r5 = r9.multiply(r7)
            java.lang.Object r3 = r3.add(r5)
            org.apache.commons.math3.RealFieldElement r3 = (org.apache.commons.math3.RealFieldElement) r3
            r5 = r32[r0]
            r6 = 1
            r5 = r5[r6]
            java.lang.Object r3 = r3.subtract(r5)
            org.apache.commons.math3.RealFieldElement r3 = (org.apache.commons.math3.RealFieldElement) r3
            r14 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            java.lang.Object r3 = r3.multiply(r14)
            java.lang.Object r3 = r4.subtract(r3)
            org.apache.commons.math3.RealFieldElement r3 = (org.apache.commons.math3.RealFieldElement) r3
            r1[r6] = r3
            r1 = r12[r0]
            java.lang.Object r2 = r2.multiply(r11)
            org.apache.commons.math3.RealFieldElement r2 = (org.apache.commons.math3.RealFieldElement) r2
            java.lang.Object r3 = r4.multiply(r13)
            java.lang.Object r2 = r2.add(r3)
            org.apache.commons.math3.RealFieldElement r2 = (org.apache.commons.math3.RealFieldElement) r2
            java.lang.Object r3 = r9.multiply(r8)
            java.lang.Object r2 = r2.add(r3)
            org.apache.commons.math3.RealFieldElement r2 = (org.apache.commons.math3.RealFieldElement) r2
            r3 = r32[r0]
            r3 = r3[r0]
            java.lang.Object r2 = r2.subtract(r3)
            org.apache.commons.math3.RealFieldElement r2 = (org.apache.commons.math3.RealFieldElement) r2
            r3 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            java.lang.Object r2 = r2.multiply(r3)
            java.lang.Object r2 = r9.subtract(r2)
            org.apache.commons.math3.RealFieldElement r2 = (org.apache.commons.math3.RealFieldElement) r2
            r1[r0] = r2
            r0 = 0
            r1 = r12[r0]
            r1 = r1[r0]
            double r1 = r1.getReal()
            r3 = r32[r0]
            r3 = r3[r0]
            double r3 = r3.getReal()
            double r1 = r1 - r3
            r3 = r12[r0]
            r4 = 1
            r3 = r3[r4]
            double r5 = r3.getReal()
            r3 = r32[r0]
            r3 = r3[r4]
            double r7 = r3.getReal()
            double r5 = r5 - r7
            r3 = r12[r0]
            r7 = 2
            r3 = r3[r7]
            double r8 = r3.getReal()
            r3 = r32[r0]
            r3 = r3[r7]
            double r10 = r3.getReal()
            double r8 = r8 - r10
            r3 = r12[r4]
            r3 = r3[r0]
            double r10 = r3.getReal()
            r3 = r32[r4]
            r3 = r3[r0]
            double r13 = r3.getReal()
            double r10 = r10 - r13
            r0 = r12[r4]
            r0 = r0[r4]
            double r13 = r0.getReal()
            r0 = r32[r4]
            r0 = r0[r4]
            double r22 = r0.getReal()
            double r13 = r13 - r22
            r0 = r12[r4]
            r3 = 2
            r0 = r0[r3]
            double r15 = r0.getReal()
            r0 = r32[r4]
            r0 = r0[r3]
            double r22 = r0.getReal()
            double r22 = r15 - r22
            r0 = r12[r3]
            r4 = 0
            r0 = r0[r4]
            double r15 = r0.getReal()
            r0 = r32[r3]
            r0 = r0[r4]
            double r24 = r0.getReal()
            double r24 = r15 - r24
            r0 = r12[r3]
            r4 = 1
            r0 = r0[r4]
            double r15 = r0.getReal()
            r0 = r32[r3]
            r0 = r0[r4]
            double r26 = r0.getReal()
            double r26 = r15 - r26
            r0 = r12[r3]
            r0 = r0[r3]
            double r28 = r0.getReal()
            r0 = r32[r3]
            r0 = r0[r3]
            double r3 = r0.getReal()
            double r28 = r28 - r3
            double r1 = r1 * r1
            double r5 = r5 * r5
            double r1 = r1 + r5
            double r8 = r8 * r8
            double r1 = r1 + r8
            double r10 = r10 * r10
            double r1 = r1 + r10
            double r13 = r13 * r13
            double r1 = r1 + r13
            double r22 = r22 * r22
            double r1 = r1 + r22
            double r24 = r24 * r24
            double r1 = r1 + r24
            double r26 = r26 * r26
            double r1 = r1 + r26
            double r28 = r28 * r28
            double r13 = r1 + r28
            double r0 = r13 - r20
            double r0 = org.apache.commons.math3.util.FastMath.abs((double) r0)
            int r0 = (r0 > r33 ? 1 : (r0 == r33 ? 0 : -1))
            if (r0 > 0) goto L_0x047c
            return r12
        L_0x047c:
            r0 = 0
            r1 = r12[r0]
            r2 = r1[r0]
            r3 = 1
            r4 = r1[r3]
            r5 = 2
            r1 = r1[r5]
            r6 = r12[r3]
            r7 = r6[r0]
            r8 = r6[r3]
            r6 = r6[r5]
            r9 = r12[r5]
            r10 = r9[r0]
            r11 = r9[r3]
            r9 = r9[r5]
            r15 = r18
            goto L_0x0029
        L_0x049b:
            r3 = 1
            org.apache.commons.math3.geometry.euclidean.threed.NotARotationMatrixException r1 = new org.apache.commons.math3.geometry.euclidean.threed.NotARotationMatrixException
            org.apache.commons.math3.exception.util.LocalizedFormats r2 = org.apache.commons.math3.exception.util.LocalizedFormats.UNABLE_TO_ORTHOGONOLIZE_MATRIX
            java.lang.Integer r4 = java.lang.Integer.valueOf(r15)
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r3[r0] = r4
            r1.<init>(r2, r3)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.orthogonalizeMatrix(org.apache.commons.math3.RealFieldElement[][], double):org.apache.commons.math3.RealFieldElement[][]");
    }

    public static <T extends RealFieldElement<T>> T distance(FieldRotation<T> fieldRotation, FieldRotation<T> fieldRotation2) {
        return fieldRotation.applyInverseTo(fieldRotation2).getAngle();
    }
}
