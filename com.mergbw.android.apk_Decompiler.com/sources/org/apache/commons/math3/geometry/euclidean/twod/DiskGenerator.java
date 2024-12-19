package org.apache.commons.math3.geometry.euclidean.twod;

import java.util.List;
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.enclosing.EnclosingBall;
import org.apache.commons.math3.geometry.enclosing.SupportBallGenerator;
import org.apache.commons.math3.util.FastMath;

public class DiskGenerator implements SupportBallGenerator<Euclidean2D, Vector2D> {
    public EnclosingBall<Euclidean2D, Vector2D> ballOnSupport(List<Vector2D> list) {
        if (list.size() < 1) {
            return new EnclosingBall<>(Vector2D.ZERO, Double.NEGATIVE_INFINITY, new Vector2D[0]);
        }
        Vector2D vector2D = list.get(0);
        if (list.size() < 2) {
            return new EnclosingBall<>(vector2D, 0.0d, vector2D);
        }
        Vector2D vector2D2 = list.get(1);
        if (list.size() < 3) {
            return new EnclosingBall<>(new Vector2D(0.5d, vector2D, 0.5d, vector2D2), vector2D.distance((Vector<Euclidean2D>) vector2D2) * 0.5d, vector2D, vector2D2);
        }
        Vector2D vector2D3 = list.get(2);
        BigFraction[] bigFractionArr = {new BigFraction(vector2D.getX()), new BigFraction(vector2D2.getX()), new BigFraction(vector2D3.getX())};
        BigFraction[] bigFractionArr2 = {new BigFraction(vector2D.getY()), new BigFraction(vector2D2.getY()), new BigFraction(vector2D3.getY())};
        BigFraction bigFraction = bigFractionArr[0];
        BigFraction multiply = bigFraction.multiply(bigFraction);
        BigFraction bigFraction2 = bigFractionArr2[0];
        BigFraction bigFraction3 = bigFractionArr[1];
        BigFraction multiply2 = bigFraction3.multiply(bigFraction3);
        BigFraction bigFraction4 = bigFractionArr2[1];
        BigFraction bigFraction5 = bigFractionArr[2];
        BigFraction multiply3 = bigFraction5.multiply(bigFraction5);
        BigFraction bigFraction6 = bigFractionArr2[2];
        BigFraction[] bigFractionArr3 = {multiply.add(bigFraction2.multiply(bigFraction2)), multiply2.add(bigFraction4.multiply(bigFraction4)), multiply3.add(bigFraction6.multiply(bigFraction6))};
        BigFraction multiply4 = minor(bigFractionArr, bigFractionArr2).multiply(2);
        BigFraction minor = minor(bigFractionArr3, bigFractionArr2);
        BigFraction minor2 = minor(bigFractionArr3, bigFractionArr);
        BigFraction divide = minor.divide(multiply4);
        BigFraction negate = minor2.divide(multiply4).negate();
        BigFraction subtract = bigFractionArr[0].subtract(divide);
        BigFraction subtract2 = bigFractionArr2[0].subtract(negate);
        BigFraction add = subtract.multiply(subtract).add(subtract2.multiply(subtract2));
        return new EnclosingBall<>(new Vector2D(divide.doubleValue(), negate.doubleValue()), FastMath.sqrt(add.doubleValue()), vector2D, vector2D2, vector2D3);
    }

    private BigFraction minor(BigFraction[] bigFractionArr, BigFraction[] bigFractionArr2) {
        return bigFractionArr2[0].multiply(bigFractionArr[2].subtract(bigFractionArr[1])).add(bigFractionArr2[1].multiply(bigFractionArr[0].subtract(bigFractionArr[2]))).add(bigFractionArr2[2].multiply(bigFractionArr[1].subtract(bigFractionArr[0])));
    }
}
