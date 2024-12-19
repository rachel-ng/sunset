package org.apache.commons.math3.geometry.euclidean.threed;

import java.io.Serializable;
import java.text.NumberFormat;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

public class FieldVector3D<T extends RealFieldElement<T>> implements Serializable {
    private static final long serialVersionUID = 20130224;
    private final T x;
    private final T y;
    private final T z;

    public FieldVector3D(T t, T t2, T t3) {
        this.x = t;
        this.y = t2;
        this.z = t3;
    }

    public FieldVector3D(T[] tArr) throws DimensionMismatchException {
        if (tArr.length == 3) {
            this.x = tArr[0];
            this.y = tArr[1];
            this.z = tArr[2];
            return;
        }
        throw new DimensionMismatchException(tArr.length, 3);
    }

    public FieldVector3D(T t, T t2) {
        RealFieldElement realFieldElement = (RealFieldElement) t2.cos();
        this.x = (RealFieldElement) ((RealFieldElement) t.cos()).multiply(realFieldElement);
        this.y = (RealFieldElement) ((RealFieldElement) t.sin()).multiply(realFieldElement);
        this.z = (RealFieldElement) t2.sin();
    }

    public FieldVector3D(T t, FieldVector3D<T> fieldVector3D) {
        this.x = (RealFieldElement) t.multiply(fieldVector3D.x);
        this.y = (RealFieldElement) t.multiply(fieldVector3D.y);
        this.z = (RealFieldElement) t.multiply(fieldVector3D.z);
    }

    public FieldVector3D(T t, Vector3D vector3D) {
        this.x = (RealFieldElement) t.multiply(vector3D.getX());
        this.y = (RealFieldElement) t.multiply(vector3D.getY());
        this.z = (RealFieldElement) t.multiply(vector3D.getZ());
    }

    public FieldVector3D(double d, FieldVector3D<T> fieldVector3D) {
        this.x = (RealFieldElement) fieldVector3D.x.multiply(d);
        this.y = (RealFieldElement) fieldVector3D.y.multiply(d);
        this.z = (RealFieldElement) fieldVector3D.z.multiply(d);
    }

    public FieldVector3D(T t, FieldVector3D<T> fieldVector3D, T t2, FieldVector3D<T> fieldVector3D2) {
        this.x = (RealFieldElement) t.linearCombination(t, fieldVector3D.getX(), t2, fieldVector3D2.getX());
        this.y = (RealFieldElement) t.linearCombination(t, fieldVector3D.getY(), t2, fieldVector3D2.getY());
        this.z = (RealFieldElement) t.linearCombination(t, fieldVector3D.getZ(), t2, fieldVector3D2.getZ());
    }

    public FieldVector3D(T t, Vector3D vector3D, T t2, Vector3D vector3D2) {
        this.x = (RealFieldElement) t.linearCombination(vector3D.getX(), t, vector3D2.getX(), t2);
        T t3 = t;
        T t4 = t;
        T t5 = t2;
        this.y = (RealFieldElement) t3.linearCombination(vector3D.getY(), t4, vector3D2.getY(), t5);
        this.z = (RealFieldElement) t3.linearCombination(vector3D.getZ(), t4, vector3D2.getZ(), t5);
    }

    public FieldVector3D(double d, FieldVector3D<T> fieldVector3D, double d2, FieldVector3D<T> fieldVector3D2) {
        T x2 = fieldVector3D.getX();
        double d3 = d;
        double d4 = d2;
        this.x = (RealFieldElement) x2.linearCombination(d3, fieldVector3D.getX(), d4, fieldVector3D2.getX());
        this.y = (RealFieldElement) x2.linearCombination(d3, fieldVector3D.getY(), d4, fieldVector3D2.getY());
        this.z = (RealFieldElement) x2.linearCombination(d3, fieldVector3D.getZ(), d4, fieldVector3D2.getZ());
    }

    public FieldVector3D(T t, FieldVector3D<T> fieldVector3D, T t2, FieldVector3D<T> fieldVector3D2, T t3, FieldVector3D<T> fieldVector3D3) {
        T t4 = t;
        this.x = (RealFieldElement) t.linearCombination(t4, fieldVector3D.getX(), t2, fieldVector3D2.getX(), t3, fieldVector3D3.getX());
        T t5 = t;
        T t6 = t2;
        T t7 = t3;
        this.y = (RealFieldElement) t4.linearCombination(t5, fieldVector3D.getY(), t6, fieldVector3D2.getY(), t7, fieldVector3D3.getY());
        this.z = (RealFieldElement) t4.linearCombination(t5, fieldVector3D.getZ(), t6, fieldVector3D2.getZ(), t7, fieldVector3D3.getZ());
    }

    public FieldVector3D(T t, Vector3D vector3D, T t2, Vector3D vector3D2, T t3, Vector3D vector3D3) {
        this.x = (RealFieldElement) t.linearCombination(vector3D.getX(), t, vector3D2.getX(), t2, vector3D3.getX(), t3);
        T t4 = t;
        T t5 = t;
        T t6 = t2;
        T t7 = t3;
        this.y = (RealFieldElement) t4.linearCombination(vector3D.getY(), t5, vector3D2.getY(), t6, vector3D3.getY(), t7);
        this.z = (RealFieldElement) t4.linearCombination(vector3D.getZ(), t5, vector3D2.getZ(), t6, vector3D3.getZ(), t7);
    }

    public FieldVector3D(double d, FieldVector3D<T> fieldVector3D, double d2, FieldVector3D<T> fieldVector3D2, double d3, FieldVector3D<T> fieldVector3D3) {
        T x2 = fieldVector3D.getX();
        double d4 = d;
        double d5 = d2;
        double d6 = d3;
        this.x = (RealFieldElement) x2.linearCombination(d4, fieldVector3D.getX(), d5, fieldVector3D2.getX(), d6, fieldVector3D3.getX());
        this.y = (RealFieldElement) x2.linearCombination(d4, fieldVector3D.getY(), d5, fieldVector3D2.getY(), d6, fieldVector3D3.getY());
        this.z = (RealFieldElement) x2.linearCombination(d4, fieldVector3D.getZ(), d5, fieldVector3D2.getZ(), d6, fieldVector3D3.getZ());
    }

    public FieldVector3D(T t, FieldVector3D<T> fieldVector3D, T t2, FieldVector3D<T> fieldVector3D2, T t3, FieldVector3D<T> fieldVector3D3, T t4, FieldVector3D<T> fieldVector3D4) {
        T t5 = t;
        this.x = (RealFieldElement) t.linearCombination(t5, fieldVector3D.getX(), t2, fieldVector3D2.getX(), t3, fieldVector3D3.getX(), t4, fieldVector3D4.getX());
        T t6 = t;
        T t7 = t2;
        T t8 = t3;
        T t9 = t4;
        this.y = (RealFieldElement) t5.linearCombination(t6, fieldVector3D.getY(), t7, fieldVector3D2.getY(), t8, fieldVector3D3.getY(), t9, fieldVector3D4.getY());
        this.z = (RealFieldElement) t5.linearCombination(t6, fieldVector3D.getZ(), t7, fieldVector3D2.getZ(), t8, fieldVector3D3.getZ(), t9, fieldVector3D4.getZ());
    }

    public FieldVector3D(T t, Vector3D vector3D, T t2, Vector3D vector3D2, T t3, Vector3D vector3D3, T t4, Vector3D vector3D4) {
        this.x = (RealFieldElement) t.linearCombination(vector3D.getX(), t, vector3D2.getX(), t2, vector3D3.getX(), t3, vector3D4.getX(), t4);
        T t5 = t;
        T t6 = t;
        T t7 = t2;
        T t8 = t3;
        T t9 = t4;
        this.y = (RealFieldElement) t5.linearCombination(vector3D.getY(), t6, vector3D2.getY(), t7, vector3D3.getY(), t8, vector3D4.getY(), t9);
        this.z = (RealFieldElement) t5.linearCombination(vector3D.getZ(), t6, vector3D2.getZ(), t7, vector3D3.getZ(), t8, vector3D4.getZ(), t9);
    }

    public FieldVector3D(double d, FieldVector3D<T> fieldVector3D, double d2, FieldVector3D<T> fieldVector3D2, double d3, FieldVector3D<T> fieldVector3D3, double d4, FieldVector3D<T> fieldVector3D4) {
        T x2 = fieldVector3D.getX();
        double d5 = d;
        double d6 = d2;
        double d7 = d3;
        double d8 = d4;
        this.x = (RealFieldElement) x2.linearCombination(d5, fieldVector3D.getX(), d6, fieldVector3D2.getX(), d7, fieldVector3D3.getX(), d8, fieldVector3D4.getX());
        this.y = (RealFieldElement) x2.linearCombination(d5, fieldVector3D.getY(), d6, fieldVector3D2.getY(), d7, fieldVector3D3.getY(), d8, fieldVector3D4.getY());
        this.z = (RealFieldElement) x2.linearCombination(d5, fieldVector3D.getZ(), d6, fieldVector3D2.getZ(), d7, fieldVector3D3.getZ(), d8, fieldVector3D4.getZ());
    }

    public T getX() {
        return this.x;
    }

    public T getY() {
        return this.y;
    }

    public T getZ() {
        return this.z;
    }

    public T[] toArray() {
        T[] tArr = (RealFieldElement[]) MathArrays.buildArray(this.x.getField(), 3);
        tArr[0] = this.x;
        tArr[1] = this.y;
        tArr[2] = this.z;
        return tArr;
    }

    public Vector3D toVector3D() {
        return new Vector3D(this.x.getReal(), this.y.getReal(), this.z.getReal());
    }

    public T getNorm1() {
        return (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.x.abs()).add(this.y.abs())).add(this.z.abs());
    }

    public T getNorm() {
        T t = this.x;
        T t2 = this.y;
        T t3 = this.z;
        return (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t.multiply(t)).add(t2.multiply(t2))).add(t3.multiply(t3))).sqrt();
    }

    public T getNormSq() {
        T t = this.x;
        T t2 = this.y;
        T t3 = this.z;
        return (RealFieldElement) ((RealFieldElement) ((RealFieldElement) t.multiply(t)).add(t2.multiply(t2))).add(t3.multiply(t3));
    }

    public T getNormInf() {
        T t = (RealFieldElement) this.x.abs();
        T t2 = (RealFieldElement) this.y.abs();
        T t3 = (RealFieldElement) this.z.abs();
        return t.getReal() <= t2.getReal() ? t2.getReal() <= t3.getReal() ? t3 : t2 : t.getReal() <= t3.getReal() ? t3 : t;
    }

    public T getAlpha() {
        return (RealFieldElement) this.y.atan2(this.x);
    }

    public T getDelta() {
        return (RealFieldElement) ((RealFieldElement) this.z.divide(getNorm())).asin();
    }

    public FieldVector3D<T> add(FieldVector3D<T> fieldVector3D) {
        return new FieldVector3D<>((RealFieldElement) this.x.add(fieldVector3D.x), (RealFieldElement) this.y.add(fieldVector3D.y), (RealFieldElement) this.z.add(fieldVector3D.z));
    }

    public FieldVector3D<T> add(Vector3D vector3D) {
        return new FieldVector3D<>((RealFieldElement) this.x.add(vector3D.getX()), (RealFieldElement) this.y.add(vector3D.getY()), (RealFieldElement) this.z.add(vector3D.getZ()));
    }

    public FieldVector3D<T> add(T t, FieldVector3D<T> fieldVector3D) {
        return new FieldVector3D<>((RealFieldElement) this.x.getField().getOne(), this, (RealFieldElement) t, (FieldVector3D) fieldVector3D);
    }

    public FieldVector3D<T> add(T t, Vector3D vector3D) {
        return new FieldVector3D<>((RealFieldElement) this.x.add(t.multiply(vector3D.getX())), (RealFieldElement) this.y.add(t.multiply(vector3D.getY())), (RealFieldElement) this.z.add(t.multiply(vector3D.getZ())));
    }

    public FieldVector3D<T> add(double d, FieldVector3D<T> fieldVector3D) {
        return new FieldVector3D(1.0d, this, d, (FieldVector3D) fieldVector3D);
    }

    public FieldVector3D<T> add(double d, Vector3D vector3D) {
        return new FieldVector3D<>((RealFieldElement) this.x.add(vector3D.getX() * d), (RealFieldElement) this.y.add(vector3D.getY() * d), (RealFieldElement) this.z.add(d * vector3D.getZ()));
    }

    public FieldVector3D<T> subtract(FieldVector3D<T> fieldVector3D) {
        return new FieldVector3D<>((RealFieldElement) this.x.subtract(fieldVector3D.x), (RealFieldElement) this.y.subtract(fieldVector3D.y), (RealFieldElement) this.z.subtract(fieldVector3D.z));
    }

    public FieldVector3D<T> subtract(Vector3D vector3D) {
        return new FieldVector3D<>((RealFieldElement) this.x.subtract(vector3D.getX()), (RealFieldElement) this.y.subtract(vector3D.getY()), (RealFieldElement) this.z.subtract(vector3D.getZ()));
    }

    public FieldVector3D<T> subtract(T t, FieldVector3D<T> fieldVector3D) {
        return new FieldVector3D<>((RealFieldElement) this.x.getField().getOne(), this, (RealFieldElement) t.negate(), (FieldVector3D) fieldVector3D);
    }

    public FieldVector3D<T> subtract(T t, Vector3D vector3D) {
        return new FieldVector3D<>((RealFieldElement) this.x.subtract(t.multiply(vector3D.getX())), (RealFieldElement) this.y.subtract(t.multiply(vector3D.getY())), (RealFieldElement) this.z.subtract(t.multiply(vector3D.getZ())));
    }

    public FieldVector3D<T> subtract(double d, FieldVector3D<T> fieldVector3D) {
        return new FieldVector3D(1.0d, this, -d, (FieldVector3D) fieldVector3D);
    }

    public FieldVector3D<T> subtract(double d, Vector3D vector3D) {
        return new FieldVector3D<>((RealFieldElement) this.x.subtract(vector3D.getX() * d), (RealFieldElement) this.y.subtract(vector3D.getY() * d), (RealFieldElement) this.z.subtract(d * vector3D.getZ()));
    }

    public FieldVector3D<T> normalize() throws MathArithmeticException {
        RealFieldElement norm = getNorm();
        if (norm.getReal() != 0.0d) {
            return scalarMultiply((RealFieldElement) norm.reciprocal());
        }
        throw new MathArithmeticException(LocalizedFormats.CANNOT_NORMALIZE_A_ZERO_NORM_VECTOR, new Object[0]);
    }

    public FieldVector3D<T> orthogonal() throws MathArithmeticException {
        double real = getNorm().getReal() * 0.6d;
        if (real == 0.0d) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
        } else if (FastMath.abs(this.x.getReal()) <= real) {
            T t = this.y;
            T t2 = this.z;
            RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t.multiply(t)).add(t2.multiply(t2))).sqrt()).reciprocal();
            return new FieldVector3D<>((RealFieldElement) realFieldElement.getField().getZero(), (RealFieldElement) realFieldElement.multiply(this.z), (RealFieldElement) ((RealFieldElement) realFieldElement.multiply(this.y)).negate());
        } else if (FastMath.abs(this.y.getReal()) <= real) {
            T t3 = this.x;
            T t4 = this.z;
            RealFieldElement realFieldElement2 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t3.multiply(t3)).add(t4.multiply(t4))).sqrt()).reciprocal();
            return new FieldVector3D<>((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply(this.z)).negate(), (RealFieldElement) realFieldElement2.getField().getZero(), (RealFieldElement) realFieldElement2.multiply(this.x));
        } else {
            T t5 = this.x;
            T t6 = this.y;
            RealFieldElement realFieldElement3 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t5.multiply(t5)).add(t6.multiply(t6))).sqrt()).reciprocal();
            return new FieldVector3D<>((RealFieldElement) realFieldElement3.multiply(this.y), (RealFieldElement) ((RealFieldElement) realFieldElement3.multiply(this.x)).negate(), (RealFieldElement) realFieldElement3.getField().getZero());
        }
    }

    public static <T extends RealFieldElement<T>> T angle(FieldVector3D<T> fieldVector3D, FieldVector3D<T> fieldVector3D2) throws MathArithmeticException {
        RealFieldElement realFieldElement = (RealFieldElement) fieldVector3D.getNorm().multiply(fieldVector3D2.getNorm());
        if (realFieldElement.getReal() != 0.0d) {
            T dotProduct = dotProduct(fieldVector3D, fieldVector3D2);
            double real = realFieldElement.getReal() * 0.9999d;
            if (dotProduct.getReal() >= (-real) && dotProduct.getReal() <= real) {
                return (RealFieldElement) ((RealFieldElement) dotProduct.divide(realFieldElement)).acos();
            }
            FieldVector3D<T> crossProduct = crossProduct(fieldVector3D, fieldVector3D2);
            if (dotProduct.getReal() >= 0.0d) {
                return (RealFieldElement) ((RealFieldElement) crossProduct.getNorm().divide(realFieldElement)).asin();
            }
            return (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) crossProduct.getNorm().divide(realFieldElement)).asin()).subtract(3.141592653589793d)).negate();
        }
        throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
    }

    public static <T extends RealFieldElement<T>> T angle(FieldVector3D<T> fieldVector3D, Vector3D vector3D) throws MathArithmeticException {
        RealFieldElement realFieldElement = (RealFieldElement) fieldVector3D.getNorm().multiply(vector3D.getNorm());
        if (realFieldElement.getReal() != 0.0d) {
            T dotProduct = dotProduct(fieldVector3D, vector3D);
            double real = realFieldElement.getReal() * 0.9999d;
            if (dotProduct.getReal() >= (-real) && dotProduct.getReal() <= real) {
                return (RealFieldElement) ((RealFieldElement) dotProduct.divide(realFieldElement)).acos();
            }
            FieldVector3D<T> crossProduct = crossProduct(fieldVector3D, vector3D);
            if (dotProduct.getReal() >= 0.0d) {
                return (RealFieldElement) ((RealFieldElement) crossProduct.getNorm().divide(realFieldElement)).asin();
            }
            return (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) crossProduct.getNorm().divide(realFieldElement)).asin()).subtract(3.141592653589793d)).negate();
        }
        throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
    }

    public static <T extends RealFieldElement<T>> T angle(Vector3D vector3D, FieldVector3D<T> fieldVector3D) throws MathArithmeticException {
        return angle(fieldVector3D, vector3D);
    }

    public FieldVector3D<T> negate() {
        return new FieldVector3D<>((RealFieldElement) this.x.negate(), (RealFieldElement) this.y.negate(), (RealFieldElement) this.z.negate());
    }

    public FieldVector3D<T> scalarMultiply(T t) {
        return new FieldVector3D<>((RealFieldElement) this.x.multiply(t), (RealFieldElement) this.y.multiply(t), (RealFieldElement) this.z.multiply(t));
    }

    public FieldVector3D<T> scalarMultiply(double d) {
        return new FieldVector3D<>((RealFieldElement) this.x.multiply(d), (RealFieldElement) this.y.multiply(d), (RealFieldElement) this.z.multiply(d));
    }

    public boolean isNaN() {
        return Double.isNaN(this.x.getReal()) || Double.isNaN(this.y.getReal()) || Double.isNaN(this.z.getReal());
    }

    public boolean isInfinite() {
        return !isNaN() && (Double.isInfinite(this.x.getReal()) || Double.isInfinite(this.y.getReal()) || Double.isInfinite(this.z.getReal()));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FieldVector3D)) {
            return false;
        }
        FieldVector3D fieldVector3D = (FieldVector3D) obj;
        if (fieldVector3D.isNaN()) {
            return isNaN();
        }
        if (!this.x.equals(fieldVector3D.x) || !this.y.equals(fieldVector3D.y) || !this.z.equals(fieldVector3D.z)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        if (isNaN()) {
            return 409;
        }
        return ((this.x.hashCode() * 107) + (this.y.hashCode() * 83) + this.z.hashCode()) * 311;
    }

    public T dotProduct(FieldVector3D<T> fieldVector3D) {
        T t = this.x;
        return (RealFieldElement) t.linearCombination(t, fieldVector3D.x, this.y, fieldVector3D.y, this.z, fieldVector3D.z);
    }

    public T dotProduct(Vector3D vector3D) {
        return (RealFieldElement) this.x.linearCombination(vector3D.getX(), this.x, vector3D.getY(), this.y, vector3D.getZ(), this.z);
    }

    public FieldVector3D<T> crossProduct(FieldVector3D<T> fieldVector3D) {
        return new FieldVector3D<>((RealFieldElement) this.x.linearCombination(this.y, fieldVector3D.z, this.z.negate(), fieldVector3D.y), (RealFieldElement) this.y.linearCombination(this.z, fieldVector3D.x, this.x.negate(), fieldVector3D.z), (RealFieldElement) this.z.linearCombination(this.x, fieldVector3D.y, this.y.negate(), fieldVector3D.x));
    }

    public FieldVector3D<T> crossProduct(Vector3D vector3D) {
        return new FieldVector3D<>((RealFieldElement) this.x.linearCombination(vector3D.getZ(), this.y, -vector3D.getY(), this.z), (RealFieldElement) this.y.linearCombination(vector3D.getX(), this.z, -vector3D.getZ(), this.x), (RealFieldElement) this.z.linearCombination(vector3D.getY(), this.x, -vector3D.getX(), this.y));
    }

    public T distance1(FieldVector3D<T> fieldVector3D) {
        return (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) fieldVector3D.x.subtract(this.x)).abs()).add((RealFieldElement) ((RealFieldElement) fieldVector3D.y.subtract(this.y)).abs())).add((RealFieldElement) ((RealFieldElement) fieldVector3D.z.subtract(this.z)).abs());
    }

    public T distance1(Vector3D vector3D) {
        return (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.x.subtract(vector3D.getX())).abs()).add((RealFieldElement) ((RealFieldElement) this.y.subtract(vector3D.getY())).abs())).add((RealFieldElement) ((RealFieldElement) this.z.subtract(vector3D.getZ())).abs());
    }

    public T distance(FieldVector3D<T> fieldVector3D) {
        RealFieldElement realFieldElement = (RealFieldElement) fieldVector3D.x.subtract(this.x);
        RealFieldElement realFieldElement2 = (RealFieldElement) fieldVector3D.y.subtract(this.y);
        RealFieldElement realFieldElement3 = (RealFieldElement) fieldVector3D.z.subtract(this.z);
        return (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement.multiply(realFieldElement)).add(realFieldElement2.multiply(realFieldElement2))).add(realFieldElement3.multiply(realFieldElement3))).sqrt();
    }

    public T distance(Vector3D vector3D) {
        RealFieldElement realFieldElement = (RealFieldElement) this.x.subtract(vector3D.getX());
        RealFieldElement realFieldElement2 = (RealFieldElement) this.y.subtract(vector3D.getY());
        RealFieldElement realFieldElement3 = (RealFieldElement) this.z.subtract(vector3D.getZ());
        return (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement.multiply(realFieldElement)).add(realFieldElement2.multiply(realFieldElement2))).add(realFieldElement3.multiply(realFieldElement3))).sqrt();
    }

    public T distanceInf(FieldVector3D<T> fieldVector3D) {
        T t = (RealFieldElement) ((RealFieldElement) fieldVector3D.x.subtract(this.x)).abs();
        T t2 = (RealFieldElement) ((RealFieldElement) fieldVector3D.y.subtract(this.y)).abs();
        T t3 = (RealFieldElement) ((RealFieldElement) fieldVector3D.z.subtract(this.z)).abs();
        return t.getReal() <= t2.getReal() ? t2.getReal() <= t3.getReal() ? t3 : t2 : t.getReal() <= t3.getReal() ? t3 : t;
    }

    public T distanceInf(Vector3D vector3D) {
        T t = (RealFieldElement) ((RealFieldElement) this.x.subtract(vector3D.getX())).abs();
        T t2 = (RealFieldElement) ((RealFieldElement) this.y.subtract(vector3D.getY())).abs();
        T t3 = (RealFieldElement) ((RealFieldElement) this.z.subtract(vector3D.getZ())).abs();
        return t.getReal() <= t2.getReal() ? t2.getReal() <= t3.getReal() ? t3 : t2 : t.getReal() <= t3.getReal() ? t3 : t;
    }

    public T distanceSq(FieldVector3D<T> fieldVector3D) {
        RealFieldElement realFieldElement = (RealFieldElement) fieldVector3D.x.subtract(this.x);
        RealFieldElement realFieldElement2 = (RealFieldElement) fieldVector3D.y.subtract(this.y);
        RealFieldElement realFieldElement3 = (RealFieldElement) fieldVector3D.z.subtract(this.z);
        return (RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement.multiply(realFieldElement)).add(realFieldElement2.multiply(realFieldElement2))).add(realFieldElement3.multiply(realFieldElement3));
    }

    public T distanceSq(Vector3D vector3D) {
        RealFieldElement realFieldElement = (RealFieldElement) this.x.subtract(vector3D.getX());
        RealFieldElement realFieldElement2 = (RealFieldElement) this.y.subtract(vector3D.getY());
        RealFieldElement realFieldElement3 = (RealFieldElement) this.z.subtract(vector3D.getZ());
        return (RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement.multiply(realFieldElement)).add(realFieldElement2.multiply(realFieldElement2))).add(realFieldElement3.multiply(realFieldElement3));
    }

    public static <T extends RealFieldElement<T>> T dotProduct(FieldVector3D<T> fieldVector3D, FieldVector3D<T> fieldVector3D2) {
        return fieldVector3D.dotProduct(fieldVector3D2);
    }

    public static <T extends RealFieldElement<T>> T dotProduct(FieldVector3D<T> fieldVector3D, Vector3D vector3D) {
        return fieldVector3D.dotProduct(vector3D);
    }

    public static <T extends RealFieldElement<T>> T dotProduct(Vector3D vector3D, FieldVector3D<T> fieldVector3D) {
        return fieldVector3D.dotProduct(vector3D);
    }

    public static <T extends RealFieldElement<T>> FieldVector3D<T> crossProduct(FieldVector3D<T> fieldVector3D, FieldVector3D<T> fieldVector3D2) {
        return fieldVector3D.crossProduct(fieldVector3D2);
    }

    public static <T extends RealFieldElement<T>> FieldVector3D<T> crossProduct(FieldVector3D<T> fieldVector3D, Vector3D vector3D) {
        return fieldVector3D.crossProduct(vector3D);
    }

    public static <T extends RealFieldElement<T>> FieldVector3D<T> crossProduct(Vector3D vector3D, FieldVector3D<T> fieldVector3D) {
        return new FieldVector3D<>((RealFieldElement) fieldVector3D.x.linearCombination(vector3D.getY(), fieldVector3D.z, -vector3D.getZ(), fieldVector3D.y), (RealFieldElement) fieldVector3D.y.linearCombination(vector3D.getZ(), fieldVector3D.x, -vector3D.getX(), fieldVector3D.z), (RealFieldElement) fieldVector3D.z.linearCombination(vector3D.getX(), fieldVector3D.y, -vector3D.getY(), fieldVector3D.x));
    }

    public static <T extends RealFieldElement<T>> T distance1(FieldVector3D<T> fieldVector3D, FieldVector3D<T> fieldVector3D2) {
        return fieldVector3D.distance1(fieldVector3D2);
    }

    public static <T extends RealFieldElement<T>> T distance1(FieldVector3D<T> fieldVector3D, Vector3D vector3D) {
        return fieldVector3D.distance1(vector3D);
    }

    public static <T extends RealFieldElement<T>> T distance1(Vector3D vector3D, FieldVector3D<T> fieldVector3D) {
        return fieldVector3D.distance1(vector3D);
    }

    public static <T extends RealFieldElement<T>> T distance(FieldVector3D<T> fieldVector3D, FieldVector3D<T> fieldVector3D2) {
        return fieldVector3D.distance(fieldVector3D2);
    }

    public static <T extends RealFieldElement<T>> T distance(FieldVector3D<T> fieldVector3D, Vector3D vector3D) {
        return fieldVector3D.distance(vector3D);
    }

    public static <T extends RealFieldElement<T>> T distance(Vector3D vector3D, FieldVector3D<T> fieldVector3D) {
        return fieldVector3D.distance(vector3D);
    }

    public static <T extends RealFieldElement<T>> T distanceInf(FieldVector3D<T> fieldVector3D, FieldVector3D<T> fieldVector3D2) {
        return fieldVector3D.distanceInf(fieldVector3D2);
    }

    public static <T extends RealFieldElement<T>> T distanceInf(FieldVector3D<T> fieldVector3D, Vector3D vector3D) {
        return fieldVector3D.distanceInf(vector3D);
    }

    public static <T extends RealFieldElement<T>> T distanceInf(Vector3D vector3D, FieldVector3D<T> fieldVector3D) {
        return fieldVector3D.distanceInf(vector3D);
    }

    public static <T extends RealFieldElement<T>> T distanceSq(FieldVector3D<T> fieldVector3D, FieldVector3D<T> fieldVector3D2) {
        return fieldVector3D.distanceSq(fieldVector3D2);
    }

    public static <T extends RealFieldElement<T>> T distanceSq(FieldVector3D<T> fieldVector3D, Vector3D vector3D) {
        return fieldVector3D.distanceSq(vector3D);
    }

    public static <T extends RealFieldElement<T>> T distanceSq(Vector3D vector3D, FieldVector3D<T> fieldVector3D) {
        return fieldVector3D.distanceSq(vector3D);
    }

    public String toString() {
        return Vector3DFormat.getInstance().format(toVector3D());
    }

    public String toString(NumberFormat numberFormat) {
        return new Vector3DFormat(numberFormat).format(toVector3D());
    }
}
