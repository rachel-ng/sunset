package org.apache.commons.math3.geometry.euclidean.threed;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.enclosing.EnclosingBall;
import org.apache.commons.math3.geometry.enclosing.SupportBallGenerator;
import org.apache.commons.math3.geometry.euclidean.twod.DiskGenerator;
import org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.FastMath;

public class SphereGenerator implements SupportBallGenerator<Euclidean3D, Vector3D> {
    public EnclosingBall<Euclidean3D, Vector3D> ballOnSupport(List<Vector3D> list) {
        List<Vector3D> list2 = list;
        if (list.size() < 1) {
            return new EnclosingBall<>(Vector3D.ZERO, Double.NEGATIVE_INFINITY, new Vector3D[0]);
        }
        Vector3D vector3D = list2.get(0);
        if (list.size() < 2) {
            return new EnclosingBall<>(vector3D, 0.0d, vector3D);
        }
        Vector3D vector3D2 = list2.get(1);
        if (list.size() < 3) {
            return new EnclosingBall<>(new Vector3D(0.5d, vector3D, 0.5d, vector3D2), vector3D.distance((Vector<Euclidean3D>) vector3D2) * 0.5d, vector3D, vector3D2);
        }
        Vector3D vector3D3 = list2.get(2);
        if (list.size() < 4) {
            Plane plane = new Plane(vector3D, vector3D2, vector3D3, (vector3D.getNorm1() + vector3D2.getNorm1() + vector3D3.getNorm1()) * 1.0E-10d);
            EnclosingBall<Euclidean2D, Vector2D> ballOnSupport = new DiskGenerator().ballOnSupport(Arrays.asList(new Vector2D[]{plane.toSubSpace((Vector<Euclidean3D>) vector3D), plane.toSubSpace((Vector<Euclidean3D>) vector3D2), plane.toSubSpace((Vector<Euclidean3D>) vector3D3)}));
            return new EnclosingBall<>(plane.toSpace((Vector<Euclidean2D>) ballOnSupport.getCenter()), ballOnSupport.getRadius(), vector3D, vector3D2, vector3D3);
        }
        Vector3D vector3D4 = list2.get(3);
        BigFraction[] bigFractionArr = {new BigFraction(vector3D.getX()), new BigFraction(vector3D2.getX()), new BigFraction(vector3D3.getX()), new BigFraction(vector3D4.getX())};
        BigFraction[] bigFractionArr2 = {new BigFraction(vector3D.getY()), new BigFraction(vector3D2.getY()), new BigFraction(vector3D3.getY()), new BigFraction(vector3D4.getY())};
        BigFraction[] bigFractionArr3 = {new BigFraction(vector3D.getZ()), new BigFraction(vector3D2.getZ()), new BigFraction(vector3D3.getZ()), new BigFraction(vector3D4.getZ())};
        BigFraction bigFraction = bigFractionArr[0];
        BigFraction multiply = bigFraction.multiply(bigFraction);
        BigFraction bigFraction2 = bigFractionArr2[0];
        BigFraction add = multiply.add(bigFraction2.multiply(bigFraction2));
        BigFraction bigFraction3 = bigFractionArr3[0];
        BigFraction bigFraction4 = bigFractionArr[1];
        BigFraction multiply2 = bigFraction4.multiply(bigFraction4);
        BigFraction bigFraction5 = bigFractionArr2[1];
        BigFraction add2 = multiply2.add(bigFraction5.multiply(bigFraction5));
        BigFraction bigFraction6 = bigFractionArr3[1];
        BigFraction bigFraction7 = bigFractionArr[2];
        BigFraction multiply3 = bigFraction7.multiply(bigFraction7);
        BigFraction bigFraction8 = bigFractionArr2[2];
        BigFraction add3 = multiply3.add(bigFraction8.multiply(bigFraction8));
        BigFraction bigFraction9 = bigFractionArr3[2];
        BigFraction bigFraction10 = bigFractionArr[3];
        BigFraction multiply4 = bigFraction10.multiply(bigFraction10);
        BigFraction bigFraction11 = bigFractionArr2[3];
        BigFraction add4 = multiply4.add(bigFraction11.multiply(bigFraction11));
        BigFraction bigFraction12 = bigFractionArr3[3];
        BigFraction[] bigFractionArr4 = {add.add(bigFraction3.multiply(bigFraction3)), add2.add(bigFraction6.multiply(bigFraction6)), add3.add(bigFraction9.multiply(bigFraction9)), add4.add(bigFraction12.multiply(bigFraction12))};
        BigFraction[] bigFractionArr5 = bigFractionArr2;
        BigFraction multiply5 = minor(bigFractionArr, bigFractionArr5, bigFractionArr3).multiply(2);
        BigFraction minor = minor(bigFractionArr4, bigFractionArr5, bigFractionArr3);
        BigFraction minor2 = minor(bigFractionArr4, bigFractionArr, bigFractionArr3);
        BigFraction minor3 = minor(bigFractionArr4, bigFractionArr, bigFractionArr5);
        BigFraction divide = minor.divide(multiply5);
        BigFraction negate = minor2.divide(multiply5).negate();
        BigFraction divide2 = minor3.divide(multiply5);
        BigFraction subtract = bigFractionArr[0].subtract(divide);
        BigFraction subtract2 = bigFractionArr5[0].subtract(negate);
        BigFraction subtract3 = bigFractionArr3[0].subtract(divide2);
        BigFraction add5 = subtract.multiply(subtract).add(subtract2.multiply(subtract2)).add(subtract3.multiply(subtract3));
        return new EnclosingBall<>(new Vector3D(divide.doubleValue(), negate.doubleValue(), divide2.doubleValue()), FastMath.sqrt(add5.doubleValue()), vector3D, vector3D2, vector3D3, vector3D4);
    }

    private BigFraction minor(BigFraction[] bigFractionArr, BigFraction[] bigFractionArr2, BigFraction[] bigFractionArr3) {
        return bigFractionArr2[0].multiply(bigFractionArr3[1]).multiply(bigFractionArr[2].subtract(bigFractionArr[3])).add(bigFractionArr2[0].multiply(bigFractionArr3[2]).multiply(bigFractionArr[3].subtract(bigFractionArr[1]))).add(bigFractionArr2[0].multiply(bigFractionArr3[3]).multiply(bigFractionArr[1].subtract(bigFractionArr[2]))).add(bigFractionArr2[1].multiply(bigFractionArr3[0]).multiply(bigFractionArr[3].subtract(bigFractionArr[2]))).add(bigFractionArr2[1].multiply(bigFractionArr3[2]).multiply(bigFractionArr[0].subtract(bigFractionArr[3]))).add(bigFractionArr2[1].multiply(bigFractionArr3[3]).multiply(bigFractionArr[2].subtract(bigFractionArr[0]))).add(bigFractionArr2[2].multiply(bigFractionArr3[0]).multiply(bigFractionArr[1].subtract(bigFractionArr[3]))).add(bigFractionArr2[2].multiply(bigFractionArr3[1]).multiply(bigFractionArr[3].subtract(bigFractionArr[0]))).add(bigFractionArr2[2].multiply(bigFractionArr3[3]).multiply(bigFractionArr[0].subtract(bigFractionArr[1]))).add(bigFractionArr2[3].multiply(bigFractionArr3[0]).multiply(bigFractionArr[2].subtract(bigFractionArr[1]))).add(bigFractionArr2[3].multiply(bigFractionArr3[1]).multiply(bigFractionArr[0].subtract(bigFractionArr[2]))).add(bigFractionArr2[3].multiply(bigFractionArr3[2]).multiply(bigFractionArr[1].subtract(bigFractionArr[0])));
    }
}
