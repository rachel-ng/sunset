package pl.edu.icm.jlargearrays;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.apache.commons.math3.util.FastMath;

public class LargeArrayArithmetics {
    private LargeArrayArithmetics() {
    }

    public static float[] complexSin(float[] fArr) {
        return new float[]{(float) (FastMath.sin((double) fArr[0]) * FastMath.cosh((double) fArr[1])), (float) (FastMath.cos((double) fArr[0]) * FastMath.sinh((double) fArr[1]))};
    }

    public static double[] complexSin(double[] dArr) {
        return new double[]{FastMath.sin(dArr[0]) * FastMath.cosh(dArr[1]), FastMath.cos(dArr[0]) * FastMath.sinh(dArr[1])};
    }

    public static float[] complexCos(float[] fArr) {
        return new float[]{(float) (FastMath.cos((double) fArr[0]) * FastMath.cosh((double) fArr[1])), (float) ((-FastMath.sin((double) fArr[0])) * FastMath.sinh((double) fArr[1]))};
    }

    public static double[] complexCos(double[] dArr) {
        return new double[]{FastMath.cos(dArr[0]) * FastMath.cosh(dArr[1]), (-FastMath.sin(dArr[0])) * FastMath.sinh(dArr[1])};
    }

    public static float[] complexTan(float[] fArr) {
        return complexDiv(complexSin(fArr), complexCos(fArr));
    }

    public static double[] complexTan(double[] dArr) {
        return complexDiv(complexSin(dArr), complexCos(dArr));
    }

    public static float[] complexMult(float[] fArr, float[] fArr2) {
        float f = fArr[0];
        float f2 = fArr2[0];
        float f3 = fArr[1];
        float f4 = fArr2[1];
        return new float[]{(f * f2) - (f3 * f4), (f3 * f2) + (f * f4)};
    }

    public static double[] complexMult(double[] dArr, double[] dArr2) {
        double d = dArr[0];
        double d2 = dArr2[0];
        double d3 = dArr[1];
        double d4 = dArr2[1];
        return new double[]{(d * d2) - (d3 * d4), (d3 * d2) + (d * d4)};
    }

    public static float[] complexDiv(float[] fArr, float[] fArr2) {
        float f = fArr2[0];
        float f2 = fArr2[1];
        float f3 = (f * f) + (f2 * f2);
        float f4 = fArr[0];
        float f5 = fArr[1];
        return new float[]{((f4 * f) + (f5 * f2)) / f3, ((f5 * f) - (f4 * f2)) / f3};
    }

    public static double[] complexDiv(double[] dArr, double[] dArr2) {
        double d = dArr2[0];
        double d2 = dArr2[1];
        double d3 = (d * d) + (d2 * d2);
        double d4 = dArr[0];
        double d5 = dArr[1];
        return new double[]{((d4 * d) + (d5 * d2)) / d3, ((d5 * d) - (d4 * d2)) / d3};
    }

    public static float[] complexPow(float[] fArr, double d) {
        float f = fArr[0];
        float f2 = fArr[1];
        double pow = FastMath.pow(FastMath.sqrt((double) ((f * f) + (f2 * f2))), d);
        double atan2 = d * FastMath.atan2((double) fArr[1], (double) fArr[0]);
        return new float[]{(float) (FastMath.cos(atan2) * pow), (float) (pow * FastMath.sin(atan2))};
    }

    public static double[] complexPow(double[] dArr, double d) {
        double d2 = dArr[0];
        double d3 = dArr[1];
        double pow = FastMath.pow(FastMath.sqrt((d2 * d2) + (d3 * d3)), d);
        double atan2 = d * FastMath.atan2(dArr[1], dArr[0]);
        return new double[]{FastMath.cos(atan2) * pow, pow * FastMath.sin(atan2)};
    }

    public static float[] complexSqrt(float[] fArr) {
        float f = fArr[0];
        float f2 = fArr[1];
        double sqrt = FastMath.sqrt((double) ((f * f) + (f2 * f2)));
        return new float[]{(float) FastMath.sqrt((((double) fArr[0]) + sqrt) / 2.0d), (float) (((double) FastMath.signum(fArr[1])) * FastMath.sqrt((((double) (-fArr[0])) + sqrt) / 2.0d))};
    }

    public static double[] complexSqrt(double[] dArr) {
        double d = dArr[0];
        double d2 = dArr[1];
        double sqrt = FastMath.sqrt((d * d) + (d2 * d2));
        return new double[]{FastMath.sqrt((dArr[0] + sqrt) / 2.0d), FastMath.signum(dArr[1]) * FastMath.sqrt(((-dArr[0]) + sqrt) / 2.0d)};
    }

    public static float complexAbs(float[] fArr) {
        float f = fArr[0];
        float f2 = fArr[1];
        return (float) FastMath.sqrt((double) ((f * f) + (f2 * f2)));
    }

    public static double complexAbs(double[] dArr) {
        double d = dArr[0];
        double d2 = dArr[1];
        return FastMath.sqrt((d * d) + (d2 * d2));
    }

    public static float[] complexLog(float[] fArr) {
        float f = fArr[0];
        float f2 = fArr[1];
        double sqrt = FastMath.sqrt((double) ((f * f) + (f2 * f2)));
        double atan2 = FastMath.atan2((double) fArr[1], (double) fArr[0]);
        return new float[]{(float) FastMath.log(sqrt), (float) atan2};
    }

    public static double[] complexLog(double[] dArr) {
        double d = dArr[0];
        double d2 = dArr[1];
        double sqrt = FastMath.sqrt((d * d) + (d2 * d2));
        return new double[]{FastMath.log(sqrt), FastMath.atan2(dArr[1], dArr[0])};
    }

    public static float[] complexLog10(float[] fArr) {
        double log = FastMath.log(10.0d);
        float f = fArr[0];
        float f2 = fArr[1];
        return new float[]{(float) (FastMath.log(FastMath.sqrt((double) ((f * f) + (f2 * f2)))) / log), (float) (FastMath.atan2((double) fArr[1], (double) fArr[0]) / log)};
    }

    public static double[] complexLog10(double[] dArr) {
        double log = FastMath.log(10.0d);
        double d = dArr[0];
        double d2 = dArr[1];
        return new double[]{FastMath.log(FastMath.sqrt((d * d) + (d2 * d2))) / log, FastMath.atan2(dArr[1], dArr[0]) / log};
    }

    public static float[] complexExp(float[] fArr) {
        return new float[]{(float) (FastMath.exp((double) fArr[0]) * FastMath.cos((double) fArr[1])), (float) (FastMath.exp((double) fArr[0]) * FastMath.sin((double) fArr[1]))};
    }

    public static double[] complexExp(double[] dArr) {
        return new double[]{FastMath.exp(dArr[0]) * FastMath.cos(dArr[1]), FastMath.exp(dArr[0]) * FastMath.sin(dArr[1])};
    }

    public static float[] complexAsin(float[] fArr) {
        float[] complexMult = complexMult(fArr, fArr);
        complexMult[0] = 1.0f - complexMult[0];
        complexMult[1] = 1.0f - complexMult[1];
        float[] complexLog = complexLog(complexMult);
        float[] complexMult2 = complexMult(new float[]{0.0f, 1.0f}, fArr);
        complexLog[0] = complexLog[0] + complexMult2[0];
        complexLog[1] = complexLog[1] + complexMult2[1];
        return complexMult(new float[]{0.0f, -1.0f}, complexLog);
    }

    public static double[] complexAsin(double[] dArr) {
        double[] complexMult = complexMult(dArr, dArr);
        complexMult[0] = 1.0d - complexMult[0];
        complexMult[1] = 1.0d - complexMult[1];
        double[] complexLog = complexLog(complexMult);
        double[] complexMult2 = complexMult(new double[]{0.0d, 1.0d}, dArr);
        complexLog[0] = complexLog[0] + complexMult2[0];
        complexLog[1] = complexLog[1] + complexMult2[1];
        return complexMult(new double[]{0.0d, -1.0d}, complexLog);
    }

    public static float[] complexAcos(float[] fArr) {
        float[] complexMult = complexMult(fArr, fArr);
        complexMult[0] = 1.0f - complexMult[0];
        complexMult[1] = 1.0f - complexMult[1];
        float[] complexMult2 = complexMult(new float[]{0.0f, 1.0f}, complexMult);
        complexMult2[0] = complexMult2[0] + fArr[0];
        complexMult2[1] = complexMult2[1] + fArr[1];
        return complexMult(new float[]{0.0f, -1.0f}, complexLog(complexMult2));
    }

    public static double[] complexAcos(double[] dArr) {
        double[] complexMult = complexMult(dArr, dArr);
        complexMult[0] = 1.0d - complexMult[0];
        complexMult[1] = 1.0d - complexMult[1];
        double[] complexMult2 = complexMult(new double[]{0.0d, 1.0d}, complexMult);
        complexMult2[0] = complexMult2[0] + dArr[0];
        complexMult2[1] = complexMult2[1] + dArr[1];
        return complexMult(new double[]{0.0d, -1.0d}, complexLog(complexMult2));
    }

    public static float[] complexAtan(float[] fArr) {
        float[] fArr2 = {0.0f, 1.0f};
        float f = fArr[0];
        float f2 = fArr[1];
        float[] complexLog = complexLog(complexDiv(new float[]{0.0f + f, 1.0f + f2}, new float[]{0.0f - f, 1.0f - f2}));
        fArr2[1] = (float) (((double) fArr2[1]) / 2.0d);
        return complexMult(fArr2, complexLog);
    }

    public static double[] complexAtan(double[] dArr) {
        double[] dArr2 = {0.0d, 1.0d};
        double d = dArr[0];
        double d2 = dArr[1];
        double[] complexLog = complexLog(complexDiv(new double[]{0.0d + d, 1.0d + d2}, new double[]{0.0d - d, 1.0d - d2}));
        dArr2[1] = dArr2[1] / 2.0d;
        return complexMult(dArr2, complexLog);
    }

    public static LargeArray add(LargeArray largeArray, LargeArray largeArray2) {
        return add(largeArray, largeArray2, largeArray.getType().compareTo(largeArray2.getType()) >= 0 ? largeArray.getType() : largeArray2.getType());
    }

    /* JADX WARNING: type inference failed for: r0v7, types: [pl.edu.icm.jlargearrays.LargeArray] */
    /* JADX WARNING: type inference failed for: r0v11, types: [pl.edu.icm.jlargearrays.LargeArray] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static pl.edu.icm.jlargearrays.LargeArray add(pl.edu.icm.jlargearrays.LargeArray r30, pl.edu.icm.jlargearrays.LargeArray r31, pl.edu.icm.jlargearrays.LargeArrayType r32) {
        /*
            r8 = r30
            r9 = r31
            r0 = r32
            if (r8 == 0) goto L_0x034c
            if (r9 == 0) goto L_0x034c
            long r1 = r30.length()
            long r3 = r31.length()
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 != 0) goto L_0x034c
            boolean r1 = r30.isNumeric()
            if (r1 == 0) goto L_0x034c
            boolean r1 = r31.isNumeric()
            if (r1 == 0) goto L_0x034c
            boolean r1 = r32.isNumericType()
            if (r1 == 0) goto L_0x0344
            long r10 = r30.length()
            boolean r1 = r30.isConstant()
            java.lang.String r2 = "Invalid array type."
            r3 = 2
            r12 = 0
            r4 = 1
            r5 = 0
            if (r1 == 0) goto L_0x00c3
            boolean r1 = r31.isConstant()
            if (r1 == 0) goto L_0x00c3
            boolean r1 = r32.isIntegerNumericType()
            if (r1 == 0) goto L_0x0057
            long r1 = r8.getLong(r12)
            long r3 = r9.getLong(r12)
            long r1 = r1 + r3
            java.lang.Long r1 = java.lang.Long.valueOf(r1)
            pl.edu.icm.jlargearrays.LargeArray r0 = pl.edu.icm.jlargearrays.LargeArrayUtils.createConstant(r0, r10, r1)
            return r0
        L_0x0057:
            boolean r1 = r32.isRealNumericType()
            if (r1 == 0) goto L_0x006f
            double r1 = r8.getDouble(r12)
            double r3 = r9.getDouble(r12)
            double r1 = r1 + r3
            java.lang.Double r1 = java.lang.Double.valueOf(r1)
            pl.edu.icm.jlargearrays.LargeArray r0 = pl.edu.icm.jlargearrays.LargeArrayUtils.createConstant(r0, r10, r1)
            return r0
        L_0x006f:
            pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.COMPLEX_FLOAT
            if (r0 != r1) goto L_0x0096
            r1 = r8
            pl.edu.icm.jlargearrays.ComplexFloatLargeArray r1 = (pl.edu.icm.jlargearrays.ComplexFloatLargeArray) r1
            float[] r1 = r1.getComplexFloat(r12)
            r2 = r9
            pl.edu.icm.jlargearrays.ComplexFloatLargeArray r2 = (pl.edu.icm.jlargearrays.ComplexFloatLargeArray) r2
            float[] r2 = r2.getComplexFloat(r12)
            r6 = r1[r5]
            r7 = r2[r5]
            float r6 = r6 + r7
            r1 = r1[r4]
            r2 = r2[r4]
            float r1 = r1 + r2
            float[] r2 = new float[r3]
            r2[r5] = r6
            r2[r4] = r1
            pl.edu.icm.jlargearrays.LargeArray r0 = pl.edu.icm.jlargearrays.LargeArrayUtils.createConstant(r0, r10, r2)
            return r0
        L_0x0096:
            pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.COMPLEX_DOUBLE
            if (r0 != r1) goto L_0x00bd
            r1 = r8
            pl.edu.icm.jlargearrays.ComplexDoubleLargeArray r1 = (pl.edu.icm.jlargearrays.ComplexDoubleLargeArray) r1
            double[] r1 = r1.getComplexDouble(r12)
            r2 = r9
            pl.edu.icm.jlargearrays.ComplexDoubleLargeArray r2 = (pl.edu.icm.jlargearrays.ComplexDoubleLargeArray) r2
            double[] r2 = r2.getComplexDouble(r12)
            r6 = r1[r5]
            r8 = r2[r5]
            double r6 = r6 + r8
            r8 = r1[r4]
            r1 = r2[r4]
            double r8 = r8 + r1
            double[] r1 = new double[r3]
            r1[r5] = r6
            r1[r4] = r8
            pl.edu.icm.jlargearrays.LargeArray r0 = pl.edu.icm.jlargearrays.LargeArrayUtils.createConstant(r0, r10, r1)
            return r0
        L_0x00bd:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r2)
            throw r0
        L_0x00c3:
            int r1 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getNumberOfThreads()
            long r6 = (long) r1
            long r6 = org.apache.commons.math3.util.FastMath.min((long) r10, (long) r6)
            int r14 = (int) r6
            boolean r1 = r32.isIntegerNumericType()
            r15 = 1
            if (r1 == 0) goto L_0x0148
            pl.edu.icm.jlargearrays.LargeArray r7 = pl.edu.icm.jlargearrays.LargeArrayUtils.create(r0, r10, r5)
            if (r14 < r3) goto L_0x0130
            long r0 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getConcurrentThreshold()
            int r0 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r0 >= 0) goto L_0x00e4
            goto L_0x0130
        L_0x00e4:
            long r0 = (long) r14
            long r17 = r10 / r0
            java.util.concurrent.Future[] r6 = new java.util.concurrent.Future[r14]
        L_0x00e9:
            if (r5 >= r14) goto L_0x0115
            long r0 = (long) r5
            long r1 = r0 * r17
            int r0 = r14 + -1
            if (r5 != r0) goto L_0x00f4
            r3 = r10
            goto L_0x00f6
        L_0x00f4:
            long r3 = r1 + r17
        L_0x00f6:
            pl.edu.icm.jlargearrays.LargeArrayArithmetics$1 r19 = new pl.edu.icm.jlargearrays.LargeArrayArithmetics$1
            r0 = r19
            r20 = r5
            r5 = r7
            r21 = r6
            r6 = r30
            r12 = r7
            r7 = r31
            r0.<init>(r1, r3, r5, r6, r7)
            java.util.concurrent.Future r0 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r19)
            r21[r20] = r0
            int r5 = r20 + 1
            r7 = r12
            r6 = r21
            r12 = 0
            goto L_0x00e9
        L_0x0115:
            r21 = r6
            r12 = r7
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r21)     // Catch:{ InterruptedException | ExecutionException -> 0x011c }
            goto L_0x0145
        L_0x011c:
            r0 = 0
        L_0x011e:
            int r2 = (r0 > r10 ? 1 : (r0 == r10 ? 0 : -1))
            if (r2 >= 0) goto L_0x0145
            long r2 = r8.getLong(r0)
            long r4 = r9.getLong(r0)
            long r2 = r2 + r4
            r12.setLong(r0, r2)
            long r0 = r0 + r15
            goto L_0x011e
        L_0x0130:
            r12 = r7
            r0 = 0
        L_0x0133:
            int r2 = (r0 > r10 ? 1 : (r0 == r10 ? 0 : -1))
            if (r2 >= 0) goto L_0x0145
            long r2 = r8.getLong(r0)
            long r4 = r9.getLong(r0)
            long r2 = r2 + r4
            r12.setLong(r0, r2)
            long r0 = r0 + r15
            goto L_0x0133
        L_0x0145:
            r7 = r12
            goto L_0x033d
        L_0x0148:
            boolean r1 = r32.isRealNumericType()
            if (r1 == 0) goto L_0x01b3
            pl.edu.icm.jlargearrays.LargeArray r12 = pl.edu.icm.jlargearrays.LargeArrayUtils.create(r0, r10, r5)
            if (r14 < r3) goto L_0x019f
            long r0 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getConcurrentThreshold()
            int r0 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r0 >= 0) goto L_0x015d
            goto L_0x019f
        L_0x015d:
            long r0 = (long) r14
            long r17 = r10 / r0
            java.util.concurrent.Future[] r13 = new java.util.concurrent.Future[r14]
            r7 = r5
        L_0x0163:
            if (r7 >= r14) goto L_0x0187
            long r0 = (long) r7
            long r1 = r0 * r17
            int r0 = r14 + -1
            if (r7 != r0) goto L_0x016e
            r3 = r10
            goto L_0x0170
        L_0x016e:
            long r3 = r1 + r17
        L_0x0170:
            pl.edu.icm.jlargearrays.LargeArrayArithmetics$2 r19 = new pl.edu.icm.jlargearrays.LargeArrayArithmetics$2
            r0 = r19
            r5 = r12
            r6 = r30
            r20 = r7
            r7 = r31
            r0.<init>(r1, r3, r5, r6, r7)
            java.util.concurrent.Future r0 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r19)
            r13[r20] = r0
            int r7 = r20 + 1
            goto L_0x0163
        L_0x0187:
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r13)     // Catch:{ InterruptedException | ExecutionException -> 0x018b }
            goto L_0x0145
        L_0x018b:
            r0 = 0
        L_0x018d:
            int r2 = (r0 > r10 ? 1 : (r0 == r10 ? 0 : -1))
            if (r2 >= 0) goto L_0x0145
            double r2 = r8.getDouble(r0)
            double r4 = r9.getDouble(r0)
            double r2 = r2 + r4
            r12.setDouble(r0, r2)
            long r0 = r0 + r15
            goto L_0x018d
        L_0x019f:
            r0 = 0
        L_0x01a1:
            int r2 = (r0 > r10 ? 1 : (r0 == r10 ? 0 : -1))
            if (r2 >= 0) goto L_0x0145
            double r2 = r8.getDouble(r0)
            double r4 = r9.getDouble(r0)
            double r2 = r2 + r4
            r12.setDouble(r0, r2)
            long r0 = r0 + r15
            goto L_0x01a1
        L_0x01b3:
            pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.COMPLEX_FLOAT
            if (r0 != r1) goto L_0x0275
            pl.edu.icm.jlargearrays.LargeArray r1 = pl.edu.icm.jlargearrays.LargeArrayUtils.convert(r8, r0)
            pl.edu.icm.jlargearrays.ComplexFloatLargeArray r1 = (pl.edu.icm.jlargearrays.ComplexFloatLargeArray) r1
            pl.edu.icm.jlargearrays.LargeArray r2 = pl.edu.icm.jlargearrays.LargeArrayUtils.convert(r31, r32)
            pl.edu.icm.jlargearrays.ComplexFloatLargeArray r2 = (pl.edu.icm.jlargearrays.ComplexFloatLargeArray) r2
            pl.edu.icm.jlargearrays.LargeArrayType r6 = r1.getType()
            pl.edu.icm.jlargearrays.LargeArrayType r7 = r30.getType()
            if (r6 != r7) goto L_0x01dd
            pl.edu.icm.jlargearrays.LargeArrayType r6 = r2.getType()
            pl.edu.icm.jlargearrays.LargeArrayType r7 = r31.getType()
            if (r6 != r7) goto L_0x01dd
            pl.edu.icm.jlargearrays.LargeArray r0 = pl.edu.icm.jlargearrays.LargeArrayUtils.create(r0, r10, r5)
            r7 = r0
            goto L_0x01ea
        L_0x01dd:
            pl.edu.icm.jlargearrays.LargeArrayType r0 = r1.getType()
            pl.edu.icm.jlargearrays.LargeArrayType r6 = r30.getType()
            if (r0 == r6) goto L_0x01e9
            r7 = r1
            goto L_0x01ea
        L_0x01e9:
            r7 = r2
        L_0x01ea:
            r0 = r7
            pl.edu.icm.jlargearrays.ComplexFloatLargeArray r0 = (pl.edu.icm.jlargearrays.ComplexFloatLargeArray) r0
            if (r14 < r3) goto L_0x0250
            long r8 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getConcurrentThreshold()
            int r6 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
            if (r6 >= 0) goto L_0x01f8
            goto L_0x0250
        L_0x01f8:
            long r8 = (long) r14
            long r8 = r10 / r8
            java.util.concurrent.Future[] r6 = new java.util.concurrent.Future[r14]
            r12 = r5
        L_0x01fe:
            if (r12 >= r14) goto L_0x0226
            long r3 = (long) r12
            long r23 = r3 * r8
            int r3 = r14 + -1
            if (r12 != r3) goto L_0x020a
            r25 = r10
            goto L_0x020e
        L_0x020a:
            long r3 = r23 + r8
            r25 = r3
        L_0x020e:
            pl.edu.icm.jlargearrays.LargeArrayArithmetics$3 r3 = new pl.edu.icm.jlargearrays.LargeArrayArithmetics$3
            r22 = r3
            r27 = r1
            r28 = r2
            r29 = r0
            r22.<init>(r23, r25, r27, r28, r29)
            java.util.concurrent.Future r3 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r3)
            r6[r12] = r3
            int r12 = r12 + 1
            r3 = 2
            r4 = 1
            goto L_0x01fe
        L_0x0226:
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r6)     // Catch:{ InterruptedException | ExecutionException -> 0x022b }
            goto L_0x033d
        L_0x022b:
            r3 = 0
        L_0x022d:
            int r6 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r6 >= 0) goto L_0x033d
            float[] r6 = r1.getComplexFloat(r3)
            float[] r8 = r2.getComplexFloat(r3)
            r9 = r6[r5]
            r12 = r8[r5]
            float r9 = r9 + r12
            r12 = 1
            r6 = r6[r12]
            r8 = r8[r12]
            float r6 = r6 + r8
            r8 = 2
            float[] r14 = new float[r8]
            r14[r5] = r9
            r14[r12] = r6
            r0.setComplexFloat(r3, r14)
            long r3 = r3 + r15
            goto L_0x022d
        L_0x0250:
            r3 = 0
        L_0x0252:
            int r6 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r6 >= 0) goto L_0x033d
            float[] r6 = r1.getComplexFloat(r3)
            float[] r8 = r2.getComplexFloat(r3)
            r9 = r6[r5]
            r12 = r8[r5]
            float r9 = r9 + r12
            r12 = 1
            r6 = r6[r12]
            r8 = r8[r12]
            float r6 = r6 + r8
            r8 = 2
            float[] r14 = new float[r8]
            r14[r5] = r9
            r14[r12] = r6
            r0.setComplexFloat(r3, r14)
            long r3 = r3 + r15
            goto L_0x0252
        L_0x0275:
            pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.COMPLEX_DOUBLE
            if (r0 != r1) goto L_0x033e
            pl.edu.icm.jlargearrays.LargeArray r1 = pl.edu.icm.jlargearrays.LargeArrayUtils.convert(r8, r0)
            pl.edu.icm.jlargearrays.ComplexDoubleLargeArray r1 = (pl.edu.icm.jlargearrays.ComplexDoubleLargeArray) r1
            pl.edu.icm.jlargearrays.LargeArray r2 = pl.edu.icm.jlargearrays.LargeArrayUtils.convert(r31, r32)
            pl.edu.icm.jlargearrays.ComplexDoubleLargeArray r2 = (pl.edu.icm.jlargearrays.ComplexDoubleLargeArray) r2
            pl.edu.icm.jlargearrays.LargeArrayType r3 = r1.getType()
            pl.edu.icm.jlargearrays.LargeArrayType r4 = r30.getType()
            if (r3 != r4) goto L_0x029f
            pl.edu.icm.jlargearrays.LargeArrayType r3 = r2.getType()
            pl.edu.icm.jlargearrays.LargeArrayType r4 = r31.getType()
            if (r3 != r4) goto L_0x029f
            pl.edu.icm.jlargearrays.LargeArray r0 = pl.edu.icm.jlargearrays.LargeArrayUtils.create(r0, r10, r5)
            r7 = r0
            goto L_0x02ac
        L_0x029f:
            pl.edu.icm.jlargearrays.LargeArrayType r0 = r1.getType()
            pl.edu.icm.jlargearrays.LargeArrayType r3 = r30.getType()
            if (r0 == r3) goto L_0x02ab
            r7 = r1
            goto L_0x02ac
        L_0x02ab:
            r7 = r2
        L_0x02ac:
            r0 = r7
            pl.edu.icm.jlargearrays.ComplexDoubleLargeArray r0 = (pl.edu.icm.jlargearrays.ComplexDoubleLargeArray) r0
            r3 = 2
            if (r14 < r3) goto L_0x0316
            long r3 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getConcurrentThreshold()
            int r3 = (r10 > r3 ? 1 : (r10 == r3 ? 0 : -1))
            if (r3 >= 0) goto L_0x02bb
            goto L_0x0316
        L_0x02bb:
            long r3 = (long) r14
            long r3 = r10 / r3
            java.util.concurrent.Future[] r6 = new java.util.concurrent.Future[r14]
            r8 = r5
        L_0x02c1:
            if (r8 >= r14) goto L_0x02ea
            r12 = r6
            long r5 = (long) r8
            long r23 = r5 * r3
            int r5 = r14 + -1
            if (r8 != r5) goto L_0x02ce
            r25 = r10
            goto L_0x02d2
        L_0x02ce:
            long r5 = r23 + r3
            r25 = r5
        L_0x02d2:
            pl.edu.icm.jlargearrays.LargeArrayArithmetics$4 r5 = new pl.edu.icm.jlargearrays.LargeArrayArithmetics$4
            r22 = r5
            r27 = r1
            r28 = r2
            r29 = r0
            r22.<init>(r23, r25, r27, r28, r29)
            java.util.concurrent.Future r5 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r5)
            r12[r8] = r5
            int r8 = r8 + 1
            r6 = r12
            r5 = 0
            goto L_0x02c1
        L_0x02ea:
            r12 = r6
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r12)     // Catch:{ InterruptedException | ExecutionException -> 0x02ef }
            goto L_0x033d
        L_0x02ef:
            r3 = 0
        L_0x02f1:
            int r5 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r5 >= 0) goto L_0x033d
            double[] r5 = r1.getComplexDouble(r3)
            double[] r6 = r2.getComplexDouble(r3)
            r8 = 0
            r18 = r5[r8]
            r20 = r6[r8]
            double r18 = r18 + r20
            r12 = 1
            r13 = r5[r12]
            r5 = r6[r12]
            double r13 = r13 + r5
            r5 = 2
            double[] r6 = new double[r5]
            r6[r8] = r18
            r6[r12] = r13
            r0.setComplexDouble(r3, r6)
            long r3 = r3 + r15
            goto L_0x02f1
        L_0x0316:
            r3 = 0
        L_0x0318:
            int r5 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r5 >= 0) goto L_0x033d
            double[] r5 = r1.getComplexDouble(r3)
            double[] r6 = r2.getComplexDouble(r3)
            r8 = 0
            r18 = r5[r8]
            r20 = r6[r8]
            double r18 = r18 + r20
            r9 = 1
            r12 = r5[r9]
            r5 = r6[r9]
            double r12 = r12 + r5
            r5 = 2
            double[] r6 = new double[r5]
            r6[r8] = r18
            r6[r9] = r12
            r0.setComplexDouble(r3, r6)
            long r3 = r3 + r15
            goto L_0x0318
        L_0x033d:
            return r7
        L_0x033e:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r2)
            throw r0
        L_0x0344:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Output type must be numeric."
            r0.<init>(r1)
            throw r0
        L_0x034c:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "a == null || b == null || a.length() != b.length() || !a.isNumeric() || !b.isNumeric()"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: pl.edu.icm.jlargearrays.LargeArrayArithmetics.add(pl.edu.icm.jlargearrays.LargeArray, pl.edu.icm.jlargearrays.LargeArray, pl.edu.icm.jlargearrays.LargeArrayType):pl.edu.icm.jlargearrays.LargeArray");
    }

    public static LargeArray diff(LargeArray largeArray, LargeArray largeArray2) {
        return diff(largeArray, largeArray2, largeArray.getType().compareTo(largeArray2.getType()) >= 0 ? largeArray.getType() : largeArray2.getType());
    }

    /* JADX WARNING: type inference failed for: r0v7, types: [pl.edu.icm.jlargearrays.LargeArray] */
    /* JADX WARNING: type inference failed for: r0v11, types: [pl.edu.icm.jlargearrays.LargeArray] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static pl.edu.icm.jlargearrays.LargeArray diff(pl.edu.icm.jlargearrays.LargeArray r30, pl.edu.icm.jlargearrays.LargeArray r31, pl.edu.icm.jlargearrays.LargeArrayType r32) {
        /*
            r8 = r30
            r9 = r31
            r0 = r32
            if (r8 == 0) goto L_0x034c
            if (r9 == 0) goto L_0x034c
            long r1 = r30.length()
            long r3 = r31.length()
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 != 0) goto L_0x034c
            boolean r1 = r30.isNumeric()
            if (r1 == 0) goto L_0x034c
            boolean r1 = r31.isNumeric()
            if (r1 == 0) goto L_0x034c
            boolean r1 = r32.isNumericType()
            if (r1 == 0) goto L_0x0344
            long r10 = r30.length()
            boolean r1 = r30.isConstant()
            java.lang.String r2 = "Invalid array type."
            r3 = 2
            r12 = 0
            r4 = 1
            r5 = 0
            if (r1 == 0) goto L_0x00c3
            boolean r1 = r31.isConstant()
            if (r1 == 0) goto L_0x00c3
            boolean r1 = r32.isIntegerNumericType()
            if (r1 == 0) goto L_0x0057
            long r1 = r8.getLong(r12)
            long r3 = r9.getLong(r12)
            long r1 = r1 - r3
            java.lang.Long r1 = java.lang.Long.valueOf(r1)
            pl.edu.icm.jlargearrays.LargeArray r0 = pl.edu.icm.jlargearrays.LargeArrayUtils.createConstant(r0, r10, r1)
            return r0
        L_0x0057:
            boolean r1 = r32.isRealNumericType()
            if (r1 == 0) goto L_0x006f
            double r1 = r8.getDouble(r12)
            double r3 = r9.getDouble(r12)
            double r1 = r1 - r3
            java.lang.Double r1 = java.lang.Double.valueOf(r1)
            pl.edu.icm.jlargearrays.LargeArray r0 = pl.edu.icm.jlargearrays.LargeArrayUtils.createConstant(r0, r10, r1)
            return r0
        L_0x006f:
            pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.COMPLEX_FLOAT
            if (r0 != r1) goto L_0x0096
            r1 = r8
            pl.edu.icm.jlargearrays.ComplexFloatLargeArray r1 = (pl.edu.icm.jlargearrays.ComplexFloatLargeArray) r1
            float[] r1 = r1.getComplexFloat(r12)
            r2 = r9
            pl.edu.icm.jlargearrays.ComplexFloatLargeArray r2 = (pl.edu.icm.jlargearrays.ComplexFloatLargeArray) r2
            float[] r2 = r2.getComplexFloat(r12)
            r6 = r1[r5]
            r7 = r2[r5]
            float r6 = r6 - r7
            r1 = r1[r4]
            r2 = r2[r4]
            float r1 = r1 - r2
            float[] r2 = new float[r3]
            r2[r5] = r6
            r2[r4] = r1
            pl.edu.icm.jlargearrays.LargeArray r0 = pl.edu.icm.jlargearrays.LargeArrayUtils.createConstant(r0, r10, r2)
            return r0
        L_0x0096:
            pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.COMPLEX_DOUBLE
            if (r0 != r1) goto L_0x00bd
            r1 = r8
            pl.edu.icm.jlargearrays.ComplexDoubleLargeArray r1 = (pl.edu.icm.jlargearrays.ComplexDoubleLargeArray) r1
            double[] r1 = r1.getComplexDouble(r12)
            r2 = r9
            pl.edu.icm.jlargearrays.ComplexDoubleLargeArray r2 = (pl.edu.icm.jlargearrays.ComplexDoubleLargeArray) r2
            double[] r2 = r2.getComplexDouble(r12)
            r6 = r1[r5]
            r8 = r2[r5]
            double r6 = r6 - r8
            r8 = r1[r4]
            r1 = r2[r4]
            double r8 = r8 - r1
            double[] r1 = new double[r3]
            r1[r5] = r6
            r1[r4] = r8
            pl.edu.icm.jlargearrays.LargeArray r0 = pl.edu.icm.jlargearrays.LargeArrayUtils.createConstant(r0, r10, r1)
            return r0
        L_0x00bd:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r2)
            throw r0
        L_0x00c3:
            int r1 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getNumberOfThreads()
            long r6 = (long) r1
            long r6 = org.apache.commons.math3.util.FastMath.min((long) r10, (long) r6)
            int r14 = (int) r6
            boolean r1 = r32.isIntegerNumericType()
            r15 = 1
            if (r1 == 0) goto L_0x0148
            pl.edu.icm.jlargearrays.LargeArray r7 = pl.edu.icm.jlargearrays.LargeArrayUtils.create(r0, r10, r5)
            if (r14 < r3) goto L_0x0130
            long r0 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getConcurrentThreshold()
            int r0 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r0 >= 0) goto L_0x00e4
            goto L_0x0130
        L_0x00e4:
            long r0 = (long) r14
            long r17 = r10 / r0
            java.util.concurrent.Future[] r6 = new java.util.concurrent.Future[r14]
        L_0x00e9:
            if (r5 >= r14) goto L_0x0115
            long r0 = (long) r5
            long r1 = r0 * r17
            int r0 = r14 + -1
            if (r5 != r0) goto L_0x00f4
            r3 = r10
            goto L_0x00f6
        L_0x00f4:
            long r3 = r1 + r17
        L_0x00f6:
            pl.edu.icm.jlargearrays.LargeArrayArithmetics$5 r19 = new pl.edu.icm.jlargearrays.LargeArrayArithmetics$5
            r0 = r19
            r20 = r5
            r5 = r7
            r21 = r6
            r6 = r30
            r12 = r7
            r7 = r31
            r0.<init>(r1, r3, r5, r6, r7)
            java.util.concurrent.Future r0 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r19)
            r21[r20] = r0
            int r5 = r20 + 1
            r7 = r12
            r6 = r21
            r12 = 0
            goto L_0x00e9
        L_0x0115:
            r21 = r6
            r12 = r7
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r21)     // Catch:{ InterruptedException | ExecutionException -> 0x011c }
            goto L_0x0145
        L_0x011c:
            r0 = 0
        L_0x011e:
            int r2 = (r0 > r10 ? 1 : (r0 == r10 ? 0 : -1))
            if (r2 >= 0) goto L_0x0145
            long r2 = r8.getLong(r0)
            long r4 = r9.getLong(r0)
            long r2 = r2 - r4
            r12.setLong(r0, r2)
            long r0 = r0 + r15
            goto L_0x011e
        L_0x0130:
            r12 = r7
            r0 = 0
        L_0x0133:
            int r2 = (r0 > r10 ? 1 : (r0 == r10 ? 0 : -1))
            if (r2 >= 0) goto L_0x0145
            long r2 = r8.getLong(r0)
            long r4 = r9.getLong(r0)
            long r2 = r2 - r4
            r12.setLong(r0, r2)
            long r0 = r0 + r15
            goto L_0x0133
        L_0x0145:
            r7 = r12
            goto L_0x033d
        L_0x0148:
            boolean r1 = r32.isRealNumericType()
            if (r1 == 0) goto L_0x01b3
            pl.edu.icm.jlargearrays.LargeArray r12 = pl.edu.icm.jlargearrays.LargeArrayUtils.create(r0, r10, r5)
            if (r14 < r3) goto L_0x019f
            long r0 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getConcurrentThreshold()
            int r0 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r0 >= 0) goto L_0x015d
            goto L_0x019f
        L_0x015d:
            long r0 = (long) r14
            long r17 = r10 / r0
            java.util.concurrent.Future[] r13 = new java.util.concurrent.Future[r14]
            r7 = r5
        L_0x0163:
            if (r7 >= r14) goto L_0x0187
            long r0 = (long) r7
            long r1 = r0 * r17
            int r0 = r14 + -1
            if (r7 != r0) goto L_0x016e
            r3 = r10
            goto L_0x0170
        L_0x016e:
            long r3 = r1 + r17
        L_0x0170:
            pl.edu.icm.jlargearrays.LargeArrayArithmetics$6 r19 = new pl.edu.icm.jlargearrays.LargeArrayArithmetics$6
            r0 = r19
            r5 = r12
            r6 = r30
            r20 = r7
            r7 = r31
            r0.<init>(r1, r3, r5, r6, r7)
            java.util.concurrent.Future r0 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r19)
            r13[r20] = r0
            int r7 = r20 + 1
            goto L_0x0163
        L_0x0187:
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r13)     // Catch:{ InterruptedException | ExecutionException -> 0x018b }
            goto L_0x0145
        L_0x018b:
            r0 = 0
        L_0x018d:
            int r2 = (r0 > r10 ? 1 : (r0 == r10 ? 0 : -1))
            if (r2 >= 0) goto L_0x0145
            double r2 = r8.getDouble(r0)
            double r4 = r9.getDouble(r0)
            double r2 = r2 - r4
            r12.setDouble(r0, r2)
            long r0 = r0 + r15
            goto L_0x018d
        L_0x019f:
            r0 = 0
        L_0x01a1:
            int r2 = (r0 > r10 ? 1 : (r0 == r10 ? 0 : -1))
            if (r2 >= 0) goto L_0x0145
            double r2 = r8.getDouble(r0)
            double r4 = r9.getDouble(r0)
            double r2 = r2 - r4
            r12.setDouble(r0, r2)
            long r0 = r0 + r15
            goto L_0x01a1
        L_0x01b3:
            pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.COMPLEX_FLOAT
            if (r0 != r1) goto L_0x0275
            pl.edu.icm.jlargearrays.LargeArray r1 = pl.edu.icm.jlargearrays.LargeArrayUtils.convert(r8, r0)
            pl.edu.icm.jlargearrays.ComplexFloatLargeArray r1 = (pl.edu.icm.jlargearrays.ComplexFloatLargeArray) r1
            pl.edu.icm.jlargearrays.LargeArray r2 = pl.edu.icm.jlargearrays.LargeArrayUtils.convert(r31, r32)
            pl.edu.icm.jlargearrays.ComplexFloatLargeArray r2 = (pl.edu.icm.jlargearrays.ComplexFloatLargeArray) r2
            pl.edu.icm.jlargearrays.LargeArrayType r6 = r1.getType()
            pl.edu.icm.jlargearrays.LargeArrayType r7 = r30.getType()
            if (r6 != r7) goto L_0x01dd
            pl.edu.icm.jlargearrays.LargeArrayType r6 = r2.getType()
            pl.edu.icm.jlargearrays.LargeArrayType r7 = r31.getType()
            if (r6 != r7) goto L_0x01dd
            pl.edu.icm.jlargearrays.LargeArray r0 = pl.edu.icm.jlargearrays.LargeArrayUtils.create(r0, r10, r5)
            r7 = r0
            goto L_0x01ea
        L_0x01dd:
            pl.edu.icm.jlargearrays.LargeArrayType r0 = r1.getType()
            pl.edu.icm.jlargearrays.LargeArrayType r6 = r30.getType()
            if (r0 == r6) goto L_0x01e9
            r7 = r1
            goto L_0x01ea
        L_0x01e9:
            r7 = r2
        L_0x01ea:
            r0 = r7
            pl.edu.icm.jlargearrays.ComplexFloatLargeArray r0 = (pl.edu.icm.jlargearrays.ComplexFloatLargeArray) r0
            if (r14 < r3) goto L_0x0250
            long r8 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getConcurrentThreshold()
            int r6 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
            if (r6 >= 0) goto L_0x01f8
            goto L_0x0250
        L_0x01f8:
            long r8 = (long) r14
            long r8 = r10 / r8
            java.util.concurrent.Future[] r6 = new java.util.concurrent.Future[r14]
            r12 = r5
        L_0x01fe:
            if (r12 >= r14) goto L_0x0226
            long r3 = (long) r12
            long r23 = r3 * r8
            int r3 = r14 + -1
            if (r12 != r3) goto L_0x020a
            r25 = r10
            goto L_0x020e
        L_0x020a:
            long r3 = r23 + r8
            r25 = r3
        L_0x020e:
            pl.edu.icm.jlargearrays.LargeArrayArithmetics$7 r3 = new pl.edu.icm.jlargearrays.LargeArrayArithmetics$7
            r22 = r3
            r27 = r1
            r28 = r2
            r29 = r0
            r22.<init>(r23, r25, r27, r28, r29)
            java.util.concurrent.Future r3 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r3)
            r6[r12] = r3
            int r12 = r12 + 1
            r3 = 2
            r4 = 1
            goto L_0x01fe
        L_0x0226:
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r6)     // Catch:{ InterruptedException | ExecutionException -> 0x022b }
            goto L_0x033d
        L_0x022b:
            r3 = 0
        L_0x022d:
            int r6 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r6 >= 0) goto L_0x033d
            float[] r6 = r1.getComplexFloat(r3)
            float[] r8 = r2.getComplexFloat(r3)
            r9 = r6[r5]
            r12 = r8[r5]
            float r9 = r9 - r12
            r12 = 1
            r6 = r6[r12]
            r8 = r8[r12]
            float r6 = r6 - r8
            r8 = 2
            float[] r14 = new float[r8]
            r14[r5] = r9
            r14[r12] = r6
            r0.setComplexFloat(r3, r14)
            long r3 = r3 + r15
            goto L_0x022d
        L_0x0250:
            r3 = 0
        L_0x0252:
            int r6 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r6 >= 0) goto L_0x033d
            float[] r6 = r1.getComplexFloat(r3)
            float[] r8 = r2.getComplexFloat(r3)
            r9 = r6[r5]
            r12 = r8[r5]
            float r9 = r9 - r12
            r12 = 1
            r6 = r6[r12]
            r8 = r8[r12]
            float r6 = r6 - r8
            r8 = 2
            float[] r14 = new float[r8]
            r14[r5] = r9
            r14[r12] = r6
            r0.setComplexFloat(r3, r14)
            long r3 = r3 + r15
            goto L_0x0252
        L_0x0275:
            pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.COMPLEX_DOUBLE
            if (r0 != r1) goto L_0x033e
            pl.edu.icm.jlargearrays.LargeArray r1 = pl.edu.icm.jlargearrays.LargeArrayUtils.convert(r8, r0)
            pl.edu.icm.jlargearrays.ComplexDoubleLargeArray r1 = (pl.edu.icm.jlargearrays.ComplexDoubleLargeArray) r1
            pl.edu.icm.jlargearrays.LargeArray r2 = pl.edu.icm.jlargearrays.LargeArrayUtils.convert(r31, r32)
            pl.edu.icm.jlargearrays.ComplexDoubleLargeArray r2 = (pl.edu.icm.jlargearrays.ComplexDoubleLargeArray) r2
            pl.edu.icm.jlargearrays.LargeArrayType r3 = r1.getType()
            pl.edu.icm.jlargearrays.LargeArrayType r4 = r30.getType()
            if (r3 != r4) goto L_0x029f
            pl.edu.icm.jlargearrays.LargeArrayType r3 = r2.getType()
            pl.edu.icm.jlargearrays.LargeArrayType r4 = r31.getType()
            if (r3 != r4) goto L_0x029f
            pl.edu.icm.jlargearrays.LargeArray r0 = pl.edu.icm.jlargearrays.LargeArrayUtils.create(r0, r10, r5)
            r7 = r0
            goto L_0x02ac
        L_0x029f:
            pl.edu.icm.jlargearrays.LargeArrayType r0 = r1.getType()
            pl.edu.icm.jlargearrays.LargeArrayType r3 = r30.getType()
            if (r0 == r3) goto L_0x02ab
            r7 = r1
            goto L_0x02ac
        L_0x02ab:
            r7 = r2
        L_0x02ac:
            r0 = r7
            pl.edu.icm.jlargearrays.ComplexDoubleLargeArray r0 = (pl.edu.icm.jlargearrays.ComplexDoubleLargeArray) r0
            r3 = 2
            if (r14 < r3) goto L_0x0316
            long r3 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getConcurrentThreshold()
            int r3 = (r10 > r3 ? 1 : (r10 == r3 ? 0 : -1))
            if (r3 >= 0) goto L_0x02bb
            goto L_0x0316
        L_0x02bb:
            long r3 = (long) r14
            long r3 = r10 / r3
            java.util.concurrent.Future[] r6 = new java.util.concurrent.Future[r14]
            r8 = r5
        L_0x02c1:
            if (r8 >= r14) goto L_0x02ea
            r12 = r6
            long r5 = (long) r8
            long r23 = r5 * r3
            int r5 = r14 + -1
            if (r8 != r5) goto L_0x02ce
            r25 = r10
            goto L_0x02d2
        L_0x02ce:
            long r5 = r23 + r3
            r25 = r5
        L_0x02d2:
            pl.edu.icm.jlargearrays.LargeArrayArithmetics$8 r5 = new pl.edu.icm.jlargearrays.LargeArrayArithmetics$8
            r22 = r5
            r27 = r1
            r28 = r2
            r29 = r0
            r22.<init>(r23, r25, r27, r28, r29)
            java.util.concurrent.Future r5 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r5)
            r12[r8] = r5
            int r8 = r8 + 1
            r6 = r12
            r5 = 0
            goto L_0x02c1
        L_0x02ea:
            r12 = r6
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r12)     // Catch:{ InterruptedException | ExecutionException -> 0x02ef }
            goto L_0x033d
        L_0x02ef:
            r3 = 0
        L_0x02f1:
            int r5 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r5 >= 0) goto L_0x033d
            double[] r5 = r1.getComplexDouble(r3)
            double[] r6 = r2.getComplexDouble(r3)
            r8 = 0
            r18 = r5[r8]
            r20 = r6[r8]
            double r18 = r18 - r20
            r12 = 1
            r13 = r5[r12]
            r5 = r6[r12]
            double r13 = r13 - r5
            r5 = 2
            double[] r6 = new double[r5]
            r6[r8] = r18
            r6[r12] = r13
            r0.setComplexDouble(r3, r6)
            long r3 = r3 + r15
            goto L_0x02f1
        L_0x0316:
            r3 = 0
        L_0x0318:
            int r5 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r5 >= 0) goto L_0x033d
            double[] r5 = r1.getComplexDouble(r3)
            double[] r6 = r2.getComplexDouble(r3)
            r8 = 0
            r18 = r5[r8]
            r20 = r6[r8]
            double r18 = r18 - r20
            r9 = 1
            r12 = r5[r9]
            r5 = r6[r9]
            double r12 = r12 - r5
            r5 = 2
            double[] r6 = new double[r5]
            r6[r8] = r18
            r6[r9] = r12
            r0.setComplexDouble(r3, r6)
            long r3 = r3 + r15
            goto L_0x0318
        L_0x033d:
            return r7
        L_0x033e:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r2)
            throw r0
        L_0x0344:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Output type must be numeric."
            r0.<init>(r1)
            throw r0
        L_0x034c:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "a == null || b == null || a.length() != b.length() || !a.isNumeric() || !b.isNumeric()"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: pl.edu.icm.jlargearrays.LargeArrayArithmetics.diff(pl.edu.icm.jlargearrays.LargeArray, pl.edu.icm.jlargearrays.LargeArray, pl.edu.icm.jlargearrays.LargeArrayType):pl.edu.icm.jlargearrays.LargeArray");
    }

    public static LargeArray mult(LargeArray largeArray, LargeArray largeArray2) {
        return mult(largeArray, largeArray2, largeArray.getType().compareTo(largeArray2.getType()) >= 0 ? largeArray.getType() : largeArray2.getType());
    }

    /* JADX WARNING: type inference failed for: r0v7, types: [pl.edu.icm.jlargearrays.LargeArray] */
    /* JADX WARNING: type inference failed for: r0v11, types: [pl.edu.icm.jlargearrays.LargeArray] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static pl.edu.icm.jlargearrays.LargeArray mult(pl.edu.icm.jlargearrays.LargeArray r32, pl.edu.icm.jlargearrays.LargeArray r33, pl.edu.icm.jlargearrays.LargeArrayType r34) {
        /*
            r8 = r32
            r9 = r33
            r0 = r34
            if (r8 == 0) goto L_0x0356
            if (r9 == 0) goto L_0x0356
            long r1 = r32.length()
            long r3 = r33.length()
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 != 0) goto L_0x0356
            boolean r1 = r32.isNumeric()
            if (r1 == 0) goto L_0x0356
            boolean r1 = r33.isNumeric()
            if (r1 == 0) goto L_0x0356
            boolean r1 = r34.isNumericType()
            if (r1 == 0) goto L_0x034e
            long r10 = r32.length()
            boolean r1 = r32.isConstant()
            java.lang.String r2 = "Invalid array type."
            r12 = 0
            if (r1 == 0) goto L_0x00a8
            boolean r1 = r33.isConstant()
            if (r1 == 0) goto L_0x00a8
            boolean r1 = r34.isIntegerNumericType()
            if (r1 == 0) goto L_0x0054
            long r1 = r8.getLong(r12)
            long r3 = r9.getLong(r12)
            long r1 = r1 * r3
            java.lang.Long r1 = java.lang.Long.valueOf(r1)
            pl.edu.icm.jlargearrays.LargeArray r0 = pl.edu.icm.jlargearrays.LargeArrayUtils.createConstant(r0, r10, r1)
            return r0
        L_0x0054:
            boolean r1 = r34.isRealNumericType()
            if (r1 == 0) goto L_0x006c
            double r1 = r8.getDouble(r12)
            double r3 = r9.getDouble(r12)
            double r1 = r1 * r3
            java.lang.Double r1 = java.lang.Double.valueOf(r1)
            pl.edu.icm.jlargearrays.LargeArray r0 = pl.edu.icm.jlargearrays.LargeArrayUtils.createConstant(r0, r10, r1)
            return r0
        L_0x006c:
            pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.COMPLEX_FLOAT
            if (r0 != r1) goto L_0x0087
            r1 = r8
            pl.edu.icm.jlargearrays.ComplexFloatLargeArray r1 = (pl.edu.icm.jlargearrays.ComplexFloatLargeArray) r1
            float[] r1 = r1.getComplexFloat(r12)
            r2 = r9
            pl.edu.icm.jlargearrays.ComplexFloatLargeArray r2 = (pl.edu.icm.jlargearrays.ComplexFloatLargeArray) r2
            float[] r2 = r2.getComplexFloat(r12)
            float[] r1 = complexMult((float[]) r1, (float[]) r2)
            pl.edu.icm.jlargearrays.LargeArray r0 = pl.edu.icm.jlargearrays.LargeArrayUtils.createConstant(r0, r10, r1)
            return r0
        L_0x0087:
            pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.COMPLEX_DOUBLE
            if (r0 != r1) goto L_0x00a2
            r1 = r8
            pl.edu.icm.jlargearrays.ComplexDoubleLargeArray r1 = (pl.edu.icm.jlargearrays.ComplexDoubleLargeArray) r1
            double[] r1 = r1.getComplexDouble(r12)
            r2 = r9
            pl.edu.icm.jlargearrays.ComplexDoubleLargeArray r2 = (pl.edu.icm.jlargearrays.ComplexDoubleLargeArray) r2
            double[] r2 = r2.getComplexDouble(r12)
            double[] r1 = complexMult((double[]) r1, (double[]) r2)
            pl.edu.icm.jlargearrays.LargeArray r0 = pl.edu.icm.jlargearrays.LargeArrayUtils.createConstant(r0, r10, r1)
            return r0
        L_0x00a2:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r2)
            throw r0
        L_0x00a8:
            int r1 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getNumberOfThreads()
            long r3 = (long) r1
            long r3 = org.apache.commons.math3.util.FastMath.min((long) r10, (long) r3)
            int r14 = (int) r3
            boolean r1 = r34.isIntegerNumericType()
            r15 = 1
            r3 = 2
            r4 = 0
            if (r1 == 0) goto L_0x0130
            pl.edu.icm.jlargearrays.LargeArray r7 = pl.edu.icm.jlargearrays.LargeArrayUtils.create(r0, r10, r4)
            if (r14 < r3) goto L_0x0118
            long r0 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getConcurrentThreshold()
            int r0 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r0 >= 0) goto L_0x00cb
            goto L_0x0118
        L_0x00cb:
            long r0 = (long) r14
            long r17 = r10 / r0
            java.util.concurrent.Future[] r6 = new java.util.concurrent.Future[r14]
            r5 = r4
        L_0x00d1:
            if (r5 >= r14) goto L_0x00fd
            long r0 = (long) r5
            long r1 = r0 * r17
            int r0 = r14 + -1
            if (r5 != r0) goto L_0x00dc
            r3 = r10
            goto L_0x00de
        L_0x00dc:
            long r3 = r1 + r17
        L_0x00de:
            pl.edu.icm.jlargearrays.LargeArrayArithmetics$9 r19 = new pl.edu.icm.jlargearrays.LargeArrayArithmetics$9
            r0 = r19
            r20 = r5
            r5 = r7
            r21 = r6
            r6 = r32
            r12 = r7
            r7 = r33
            r0.<init>(r1, r3, r5, r6, r7)
            java.util.concurrent.Future r0 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r19)
            r21[r20] = r0
            int r5 = r20 + 1
            r7 = r12
            r6 = r21
            r12 = 0
            goto L_0x00d1
        L_0x00fd:
            r21 = r6
            r12 = r7
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r21)     // Catch:{ InterruptedException | ExecutionException -> 0x0104 }
            goto L_0x012d
        L_0x0104:
            r0 = 0
        L_0x0106:
            int r2 = (r0 > r10 ? 1 : (r0 == r10 ? 0 : -1))
            if (r2 >= 0) goto L_0x012d
            long r2 = r8.getLong(r0)
            long r4 = r9.getLong(r0)
            long r2 = r2 * r4
            r12.setLong(r0, r2)
            long r0 = r0 + r15
            goto L_0x0106
        L_0x0118:
            r12 = r7
            r0 = 0
        L_0x011b:
            int r2 = (r0 > r10 ? 1 : (r0 == r10 ? 0 : -1))
            if (r2 >= 0) goto L_0x012d
            long r2 = r8.getLong(r0)
            long r4 = r9.getLong(r0)
            long r2 = r2 * r4
            r12.setLong(r0, r2)
            long r0 = r0 + r15
            goto L_0x011b
        L_0x012d:
            r7 = r12
            goto L_0x0347
        L_0x0130:
            boolean r1 = r34.isRealNumericType()
            if (r1 == 0) goto L_0x019b
            pl.edu.icm.jlargearrays.LargeArray r12 = pl.edu.icm.jlargearrays.LargeArrayUtils.create(r0, r10, r4)
            if (r14 < r3) goto L_0x0187
            long r0 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getConcurrentThreshold()
            int r0 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r0 >= 0) goto L_0x0145
            goto L_0x0187
        L_0x0145:
            long r0 = (long) r14
            long r17 = r10 / r0
            java.util.concurrent.Future[] r13 = new java.util.concurrent.Future[r14]
            r7 = r4
        L_0x014b:
            if (r7 >= r14) goto L_0x016f
            long r0 = (long) r7
            long r1 = r0 * r17
            int r0 = r14 + -1
            if (r7 != r0) goto L_0x0156
            r3 = r10
            goto L_0x0158
        L_0x0156:
            long r3 = r1 + r17
        L_0x0158:
            pl.edu.icm.jlargearrays.LargeArrayArithmetics$10 r19 = new pl.edu.icm.jlargearrays.LargeArrayArithmetics$10
            r0 = r19
            r5 = r12
            r6 = r32
            r20 = r7
            r7 = r33
            r0.<init>(r1, r3, r5, r6, r7)
            java.util.concurrent.Future r0 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r19)
            r13[r20] = r0
            int r7 = r20 + 1
            goto L_0x014b
        L_0x016f:
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r13)     // Catch:{ InterruptedException | ExecutionException -> 0x0173 }
            goto L_0x012d
        L_0x0173:
            r0 = 0
        L_0x0175:
            int r2 = (r0 > r10 ? 1 : (r0 == r10 ? 0 : -1))
            if (r2 >= 0) goto L_0x012d
            double r2 = r8.getDouble(r0)
            double r4 = r9.getDouble(r0)
            double r2 = r2 * r4
            r12.setDouble(r0, r2)
            long r0 = r0 + r15
            goto L_0x0175
        L_0x0187:
            r0 = 0
        L_0x0189:
            int r2 = (r0 > r10 ? 1 : (r0 == r10 ? 0 : -1))
            if (r2 >= 0) goto L_0x012d
            double r2 = r8.getDouble(r0)
            double r4 = r9.getDouble(r0)
            double r2 = r2 * r4
            r12.setDouble(r0, r2)
            long r0 = r0 + r15
            goto L_0x0189
        L_0x019b:
            pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.COMPLEX_FLOAT
            r5 = 1
            if (r0 != r1) goto L_0x026c
            pl.edu.icm.jlargearrays.LargeArray r1 = pl.edu.icm.jlargearrays.LargeArrayUtils.convert(r8, r0)
            pl.edu.icm.jlargearrays.ComplexFloatLargeArray r1 = (pl.edu.icm.jlargearrays.ComplexFloatLargeArray) r1
            pl.edu.icm.jlargearrays.LargeArray r2 = pl.edu.icm.jlargearrays.LargeArrayUtils.convert(r33, r34)
            pl.edu.icm.jlargearrays.ComplexFloatLargeArray r2 = (pl.edu.icm.jlargearrays.ComplexFloatLargeArray) r2
            pl.edu.icm.jlargearrays.LargeArrayType r6 = r1.getType()
            pl.edu.icm.jlargearrays.LargeArrayType r7 = r32.getType()
            if (r6 != r7) goto L_0x01c6
            pl.edu.icm.jlargearrays.LargeArrayType r6 = r2.getType()
            pl.edu.icm.jlargearrays.LargeArrayType r7 = r33.getType()
            if (r6 != r7) goto L_0x01c6
            pl.edu.icm.jlargearrays.LargeArray r0 = pl.edu.icm.jlargearrays.LargeArrayUtils.create(r0, r10, r4)
            r7 = r0
            goto L_0x01d3
        L_0x01c6:
            pl.edu.icm.jlargearrays.LargeArrayType r0 = r1.getType()
            pl.edu.icm.jlargearrays.LargeArrayType r6 = r32.getType()
            if (r0 == r6) goto L_0x01d2
            r7 = r1
            goto L_0x01d3
        L_0x01d2:
            r7 = r2
        L_0x01d3:
            r0 = r7
            pl.edu.icm.jlargearrays.ComplexFloatLargeArray r0 = (pl.edu.icm.jlargearrays.ComplexFloatLargeArray) r0
            if (r14 < r3) goto L_0x0240
            long r8 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getConcurrentThreshold()
            int r6 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
            if (r6 >= 0) goto L_0x01e1
            goto L_0x0240
        L_0x01e1:
            long r8 = (long) r14
            long r8 = r10 / r8
            java.util.concurrent.Future[] r6 = new java.util.concurrent.Future[r14]
            r12 = r4
        L_0x01e7:
            if (r12 >= r14) goto L_0x020f
            long r3 = (long) r12
            long r25 = r3 * r8
            int r3 = r14 + -1
            if (r12 != r3) goto L_0x01f3
            r27 = r10
            goto L_0x01f7
        L_0x01f3:
            long r3 = r25 + r8
            r27 = r3
        L_0x01f7:
            pl.edu.icm.jlargearrays.LargeArrayArithmetics$11 r3 = new pl.edu.icm.jlargearrays.LargeArrayArithmetics$11
            r24 = r3
            r29 = r1
            r30 = r2
            r31 = r0
            r24.<init>(r25, r27, r29, r30, r31)
            java.util.concurrent.Future r3 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r3)
            r6[r12] = r3
            int r12 = r12 + 1
            r3 = 2
            r4 = 0
            goto L_0x01e7
        L_0x020f:
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r6)     // Catch:{ InterruptedException | ExecutionException -> 0x0214 }
            goto L_0x0347
        L_0x0214:
            r3 = 0
        L_0x0216:
            int r6 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r6 >= 0) goto L_0x0347
            float[] r6 = r1.getComplexFloat(r3)
            float[] r8 = r2.getComplexFloat(r3)
            r9 = 0
            r12 = r6[r9]
            r14 = r8[r9]
            float r17 = r12 * r14
            r6 = r6[r5]
            r8 = r8[r5]
            float r18 = r6 * r8
            float r17 = r17 - r18
            float r6 = r6 * r14
            float r12 = r12 * r8
            float r6 = r6 + r12
            r8 = 2
            float[] r12 = new float[r8]
            r12[r9] = r17
            r12[r5] = r6
            r0.setComplexFloat(r3, r12)
            long r3 = r3 + r15
            goto L_0x0216
        L_0x0240:
            r3 = 0
        L_0x0242:
            int r6 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r6 >= 0) goto L_0x0347
            float[] r6 = r1.getComplexFloat(r3)
            float[] r8 = r2.getComplexFloat(r3)
            r9 = 0
            r12 = r6[r9]
            r14 = r8[r9]
            float r17 = r12 * r14
            r6 = r6[r5]
            r8 = r8[r5]
            float r18 = r6 * r8
            float r17 = r17 - r18
            float r6 = r6 * r14
            float r12 = r12 * r8
            float r6 = r6 + r12
            r8 = 2
            float[] r12 = new float[r8]
            r12[r9] = r17
            r12[r5] = r6
            r0.setComplexFloat(r3, r12)
            long r3 = r3 + r15
            goto L_0x0242
        L_0x026c:
            pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.COMPLEX_DOUBLE
            if (r0 != r1) goto L_0x0348
            pl.edu.icm.jlargearrays.LargeArray r1 = pl.edu.icm.jlargearrays.LargeArrayUtils.convert(r8, r0)
            pl.edu.icm.jlargearrays.ComplexDoubleLargeArray r1 = (pl.edu.icm.jlargearrays.ComplexDoubleLargeArray) r1
            pl.edu.icm.jlargearrays.LargeArray r2 = pl.edu.icm.jlargearrays.LargeArrayUtils.convert(r33, r34)
            pl.edu.icm.jlargearrays.ComplexDoubleLargeArray r2 = (pl.edu.icm.jlargearrays.ComplexDoubleLargeArray) r2
            pl.edu.icm.jlargearrays.LargeArrayType r3 = r1.getType()
            pl.edu.icm.jlargearrays.LargeArrayType r4 = r32.getType()
            if (r3 != r4) goto L_0x0297
            pl.edu.icm.jlargearrays.LargeArrayType r3 = r2.getType()
            pl.edu.icm.jlargearrays.LargeArrayType r4 = r33.getType()
            if (r3 != r4) goto L_0x0297
            r3 = 0
            pl.edu.icm.jlargearrays.LargeArray r0 = pl.edu.icm.jlargearrays.LargeArrayUtils.create(r0, r10, r3)
            r7 = r0
            goto L_0x02a4
        L_0x0297:
            pl.edu.icm.jlargearrays.LargeArrayType r0 = r1.getType()
            pl.edu.icm.jlargearrays.LargeArrayType r3 = r32.getType()
            if (r0 == r3) goto L_0x02a3
            r7 = r1
            goto L_0x02a4
        L_0x02a3:
            r7 = r2
        L_0x02a4:
            r0 = r7
            pl.edu.icm.jlargearrays.ComplexDoubleLargeArray r0 = (pl.edu.icm.jlargearrays.ComplexDoubleLargeArray) r0
            r3 = 2
            if (r14 < r3) goto L_0x0317
            long r3 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getConcurrentThreshold()
            int r3 = (r10 > r3 ? 1 : (r10 == r3 ? 0 : -1))
            if (r3 >= 0) goto L_0x02b3
            goto L_0x0317
        L_0x02b3:
            long r3 = (long) r14
            long r3 = r10 / r3
            java.util.concurrent.Future[] r6 = new java.util.concurrent.Future[r14]
            r8 = 0
        L_0x02b9:
            if (r8 >= r14) goto L_0x02e2
            r12 = r6
            long r5 = (long) r8
            long r25 = r5 * r3
            int r5 = r14 + -1
            if (r8 != r5) goto L_0x02c6
            r27 = r10
            goto L_0x02ca
        L_0x02c6:
            long r5 = r25 + r3
            r27 = r5
        L_0x02ca:
            pl.edu.icm.jlargearrays.LargeArrayArithmetics$12 r5 = new pl.edu.icm.jlargearrays.LargeArrayArithmetics$12
            r24 = r5
            r29 = r1
            r30 = r2
            r31 = r0
            r24.<init>(r25, r27, r29, r30, r31)
            java.util.concurrent.Future r5 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r5)
            r12[r8] = r5
            int r8 = r8 + 1
            r6 = r12
            r5 = 1
            goto L_0x02b9
        L_0x02e2:
            r12 = r6
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r12)     // Catch:{ InterruptedException | ExecutionException -> 0x02e7 }
            goto L_0x0347
        L_0x02e7:
            r3 = 0
        L_0x02e9:
            int r5 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r5 >= 0) goto L_0x0347
            double[] r5 = r1.getComplexDouble(r3)
            double[] r6 = r2.getComplexDouble(r3)
            r8 = 0
            r17 = r5[r8]
            r19 = r6[r8]
            double r21 = r17 * r19
            r9 = 1
            r23 = r5[r9]
            r5 = r6[r9]
            double r25 = r23 * r5
            double r21 = r21 - r25
            double r23 = r23 * r19
            double r17 = r17 * r5
            double r23 = r23 + r17
            r5 = 2
            double[] r6 = new double[r5]
            r6[r8] = r21
            r6[r9] = r23
            r0.setComplexDouble(r3, r6)
            long r3 = r3 + r15
            goto L_0x02e9
        L_0x0317:
            r3 = 0
        L_0x0319:
            int r5 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r5 >= 0) goto L_0x0347
            double[] r5 = r1.getComplexDouble(r3)
            double[] r6 = r2.getComplexDouble(r3)
            r8 = 0
            r17 = r5[r8]
            r19 = r6[r8]
            double r21 = r17 * r19
            r9 = 1
            r23 = r5[r9]
            r5 = r6[r9]
            double r25 = r23 * r5
            double r21 = r21 - r25
            double r23 = r23 * r19
            double r17 = r17 * r5
            double r23 = r23 + r17
            r5 = 2
            double[] r6 = new double[r5]
            r6[r8] = r21
            r6[r9] = r23
            r0.setComplexDouble(r3, r6)
            long r3 = r3 + r15
            goto L_0x0319
        L_0x0347:
            return r7
        L_0x0348:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r2)
            throw r0
        L_0x034e:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Output type must be numeric."
            r0.<init>(r1)
            throw r0
        L_0x0356:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "a == null || b == null || a.length() != b.length() || !a.isNumeric() || !b.isNumeric()"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: pl.edu.icm.jlargearrays.LargeArrayArithmetics.mult(pl.edu.icm.jlargearrays.LargeArray, pl.edu.icm.jlargearrays.LargeArray, pl.edu.icm.jlargearrays.LargeArrayType):pl.edu.icm.jlargearrays.LargeArray");
    }

    public static LargeArray div(LargeArray largeArray, LargeArray largeArray2) {
        return div(largeArray, largeArray2, largeArray.getType().compareTo(largeArray2.getType()) >= 0 ? largeArray.getType() : largeArray2.getType());
    }

    /* JADX WARNING: type inference failed for: r0v7, types: [pl.edu.icm.jlargearrays.LargeArray] */
    /* JADX WARNING: type inference failed for: r0v11, types: [pl.edu.icm.jlargearrays.LargeArray] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static pl.edu.icm.jlargearrays.LargeArray div(pl.edu.icm.jlargearrays.LargeArray r32, pl.edu.icm.jlargearrays.LargeArray r33, pl.edu.icm.jlargearrays.LargeArrayType r34) {
        /*
            r8 = r32
            r9 = r33
            r0 = r34
            if (r8 == 0) goto L_0x0381
            if (r9 == 0) goto L_0x0381
            long r1 = r32.length()
            long r3 = r33.length()
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 != 0) goto L_0x0381
            boolean r1 = r32.isNumeric()
            if (r1 == 0) goto L_0x0381
            boolean r1 = r33.isNumeric()
            if (r1 == 0) goto L_0x0381
            boolean r1 = r34.isNumericType()
            if (r1 == 0) goto L_0x0379
            long r10 = r32.length()
            boolean r1 = r32.isConstant()
            java.lang.String r2 = "Invalid array type."
            r12 = 0
            if (r1 == 0) goto L_0x00a8
            boolean r1 = r33.isConstant()
            if (r1 == 0) goto L_0x00a8
            boolean r1 = r34.isIntegerNumericType()
            if (r1 == 0) goto L_0x0054
            long r1 = r8.getLong(r12)
            long r3 = r9.getLong(r12)
            long r1 = r1 / r3
            java.lang.Long r1 = java.lang.Long.valueOf(r1)
            pl.edu.icm.jlargearrays.LargeArray r0 = pl.edu.icm.jlargearrays.LargeArrayUtils.createConstant(r0, r10, r1)
            return r0
        L_0x0054:
            boolean r1 = r34.isRealNumericType()
            if (r1 == 0) goto L_0x006c
            double r1 = r8.getDouble(r12)
            double r3 = r9.getDouble(r12)
            double r1 = r1 / r3
            java.lang.Double r1 = java.lang.Double.valueOf(r1)
            pl.edu.icm.jlargearrays.LargeArray r0 = pl.edu.icm.jlargearrays.LargeArrayUtils.createConstant(r0, r10, r1)
            return r0
        L_0x006c:
            pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.COMPLEX_FLOAT
            if (r0 != r1) goto L_0x0087
            r1 = r8
            pl.edu.icm.jlargearrays.ComplexFloatLargeArray r1 = (pl.edu.icm.jlargearrays.ComplexFloatLargeArray) r1
            float[] r1 = r1.getComplexFloat(r12)
            r2 = r9
            pl.edu.icm.jlargearrays.ComplexFloatLargeArray r2 = (pl.edu.icm.jlargearrays.ComplexFloatLargeArray) r2
            float[] r2 = r2.getComplexFloat(r12)
            float[] r1 = complexDiv((float[]) r1, (float[]) r2)
            pl.edu.icm.jlargearrays.LargeArray r0 = pl.edu.icm.jlargearrays.LargeArrayUtils.createConstant(r0, r10, r1)
            return r0
        L_0x0087:
            pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.COMPLEX_DOUBLE
            if (r0 != r1) goto L_0x00a2
            r1 = r8
            pl.edu.icm.jlargearrays.ComplexDoubleLargeArray r1 = (pl.edu.icm.jlargearrays.ComplexDoubleLargeArray) r1
            double[] r1 = r1.getComplexDouble(r12)
            r2 = r9
            pl.edu.icm.jlargearrays.ComplexDoubleLargeArray r2 = (pl.edu.icm.jlargearrays.ComplexDoubleLargeArray) r2
            double[] r2 = r2.getComplexDouble(r12)
            double[] r1 = complexDiv((double[]) r1, (double[]) r2)
            pl.edu.icm.jlargearrays.LargeArray r0 = pl.edu.icm.jlargearrays.LargeArrayUtils.createConstant(r0, r10, r1)
            return r0
        L_0x00a2:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r2)
            throw r0
        L_0x00a8:
            int r1 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getNumberOfThreads()
            long r3 = (long) r1
            long r3 = org.apache.commons.math3.util.FastMath.min((long) r10, (long) r3)
            int r14 = (int) r3
            boolean r1 = r34.isIntegerNumericType()
            r15 = 1
            r3 = 2
            r4 = 0
            if (r1 == 0) goto L_0x0130
            pl.edu.icm.jlargearrays.LargeArray r7 = pl.edu.icm.jlargearrays.LargeArrayUtils.create(r0, r10, r4)
            if (r14 < r3) goto L_0x0118
            long r0 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getConcurrentThreshold()
            int r0 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r0 >= 0) goto L_0x00cb
            goto L_0x0118
        L_0x00cb:
            long r0 = (long) r14
            long r17 = r10 / r0
            java.util.concurrent.Future[] r6 = new java.util.concurrent.Future[r14]
            r5 = r4
        L_0x00d1:
            if (r5 >= r14) goto L_0x00fd
            long r0 = (long) r5
            long r1 = r0 * r17
            int r0 = r14 + -1
            if (r5 != r0) goto L_0x00dc
            r3 = r10
            goto L_0x00de
        L_0x00dc:
            long r3 = r1 + r17
        L_0x00de:
            pl.edu.icm.jlargearrays.LargeArrayArithmetics$13 r19 = new pl.edu.icm.jlargearrays.LargeArrayArithmetics$13
            r0 = r19
            r20 = r5
            r5 = r7
            r21 = r6
            r6 = r32
            r12 = r7
            r7 = r33
            r0.<init>(r1, r3, r5, r6, r7)
            java.util.concurrent.Future r0 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r19)
            r21[r20] = r0
            int r5 = r20 + 1
            r7 = r12
            r6 = r21
            r12 = 0
            goto L_0x00d1
        L_0x00fd:
            r21 = r6
            r12 = r7
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r21)     // Catch:{ InterruptedException | ExecutionException -> 0x0104 }
            goto L_0x012d
        L_0x0104:
            r0 = 0
        L_0x0106:
            int r2 = (r0 > r10 ? 1 : (r0 == r10 ? 0 : -1))
            if (r2 >= 0) goto L_0x012d
            long r2 = r8.getLong(r0)
            long r4 = r9.getLong(r0)
            long r2 = r2 / r4
            r12.setLong(r0, r2)
            long r0 = r0 + r15
            goto L_0x0106
        L_0x0118:
            r12 = r7
            r0 = 0
        L_0x011b:
            int r2 = (r0 > r10 ? 1 : (r0 == r10 ? 0 : -1))
            if (r2 >= 0) goto L_0x012d
            long r2 = r8.getLong(r0)
            long r4 = r9.getLong(r0)
            long r2 = r2 / r4
            r12.setLong(r0, r2)
            long r0 = r0 + r15
            goto L_0x011b
        L_0x012d:
            r7 = r12
            goto L_0x0372
        L_0x0130:
            boolean r1 = r34.isRealNumericType()
            if (r1 == 0) goto L_0x019b
            pl.edu.icm.jlargearrays.LargeArray r12 = pl.edu.icm.jlargearrays.LargeArrayUtils.create(r0, r10, r4)
            if (r14 < r3) goto L_0x0187
            long r0 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getConcurrentThreshold()
            int r0 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r0 >= 0) goto L_0x0145
            goto L_0x0187
        L_0x0145:
            long r0 = (long) r14
            long r17 = r10 / r0
            java.util.concurrent.Future[] r13 = new java.util.concurrent.Future[r14]
            r7 = r4
        L_0x014b:
            if (r7 >= r14) goto L_0x016f
            long r0 = (long) r7
            long r1 = r0 * r17
            int r0 = r14 + -1
            if (r7 != r0) goto L_0x0156
            r3 = r10
            goto L_0x0158
        L_0x0156:
            long r3 = r1 + r17
        L_0x0158:
            pl.edu.icm.jlargearrays.LargeArrayArithmetics$14 r19 = new pl.edu.icm.jlargearrays.LargeArrayArithmetics$14
            r0 = r19
            r5 = r12
            r6 = r32
            r20 = r7
            r7 = r33
            r0.<init>(r1, r3, r5, r6, r7)
            java.util.concurrent.Future r0 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r19)
            r13[r20] = r0
            int r7 = r20 + 1
            goto L_0x014b
        L_0x016f:
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r13)     // Catch:{ InterruptedException | ExecutionException -> 0x0173 }
            goto L_0x012d
        L_0x0173:
            r0 = 0
        L_0x0175:
            int r2 = (r0 > r10 ? 1 : (r0 == r10 ? 0 : -1))
            if (r2 >= 0) goto L_0x012d
            double r2 = r8.getDouble(r0)
            double r4 = r9.getDouble(r0)
            double r2 = r2 / r4
            r12.setDouble(r0, r2)
            long r0 = r0 + r15
            goto L_0x0175
        L_0x0187:
            r0 = 0
        L_0x0189:
            int r2 = (r0 > r10 ? 1 : (r0 == r10 ? 0 : -1))
            if (r2 >= 0) goto L_0x012d
            double r2 = r8.getDouble(r0)
            double r4 = r9.getDouble(r0)
            double r2 = r2 / r4
            r12.setDouble(r0, r2)
            long r0 = r0 + r15
            goto L_0x0189
        L_0x019b:
            pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.COMPLEX_FLOAT
            r5 = 1
            if (r0 != r1) goto L_0x0282
            pl.edu.icm.jlargearrays.LargeArray r1 = pl.edu.icm.jlargearrays.LargeArrayUtils.convert(r8, r0)
            pl.edu.icm.jlargearrays.ComplexFloatLargeArray r1 = (pl.edu.icm.jlargearrays.ComplexFloatLargeArray) r1
            pl.edu.icm.jlargearrays.LargeArray r2 = pl.edu.icm.jlargearrays.LargeArrayUtils.convert(r33, r34)
            pl.edu.icm.jlargearrays.ComplexFloatLargeArray r2 = (pl.edu.icm.jlargearrays.ComplexFloatLargeArray) r2
            pl.edu.icm.jlargearrays.LargeArrayType r6 = r1.getType()
            pl.edu.icm.jlargearrays.LargeArrayType r7 = r32.getType()
            if (r6 != r7) goto L_0x01c6
            pl.edu.icm.jlargearrays.LargeArrayType r6 = r2.getType()
            pl.edu.icm.jlargearrays.LargeArrayType r7 = r33.getType()
            if (r6 != r7) goto L_0x01c6
            pl.edu.icm.jlargearrays.LargeArray r0 = pl.edu.icm.jlargearrays.LargeArrayUtils.create(r0, r10, r4)
            r7 = r0
            goto L_0x01d3
        L_0x01c6:
            pl.edu.icm.jlargearrays.LargeArrayType r0 = r1.getType()
            pl.edu.icm.jlargearrays.LargeArrayType r6 = r32.getType()
            if (r0 == r6) goto L_0x01d2
            r7 = r1
            goto L_0x01d3
        L_0x01d2:
            r7 = r2
        L_0x01d3:
            r0 = r7
            pl.edu.icm.jlargearrays.ComplexFloatLargeArray r0 = (pl.edu.icm.jlargearrays.ComplexFloatLargeArray) r0
            if (r14 < r3) goto L_0x024b
            long r8 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getConcurrentThreshold()
            int r6 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
            if (r6 >= 0) goto L_0x01e1
            goto L_0x024b
        L_0x01e1:
            long r8 = (long) r14
            long r8 = r10 / r8
            java.util.concurrent.Future[] r6 = new java.util.concurrent.Future[r14]
            r12 = r4
        L_0x01e7:
            if (r12 >= r14) goto L_0x020f
            long r3 = (long) r12
            long r25 = r3 * r8
            int r3 = r14 + -1
            if (r12 != r3) goto L_0x01f3
            r27 = r10
            goto L_0x01f7
        L_0x01f3:
            long r3 = r25 + r8
            r27 = r3
        L_0x01f7:
            pl.edu.icm.jlargearrays.LargeArrayArithmetics$15 r3 = new pl.edu.icm.jlargearrays.LargeArrayArithmetics$15
            r24 = r3
            r29 = r1
            r30 = r2
            r31 = r0
            r24.<init>(r25, r27, r29, r30, r31)
            java.util.concurrent.Future r3 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r3)
            r6[r12] = r3
            int r12 = r12 + 1
            r3 = 2
            r4 = 0
            goto L_0x01e7
        L_0x020f:
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r6)     // Catch:{ InterruptedException | ExecutionException -> 0x0214 }
            goto L_0x0372
        L_0x0214:
            r3 = 0
        L_0x0216:
            int r6 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r6 >= 0) goto L_0x0372
            float[] r6 = r1.getComplexFloat(r3)
            float[] r8 = r2.getComplexFloat(r3)
            r9 = 0
            r12 = r8[r9]
            float r14 = r12 * r12
            r8 = r8[r5]
            float r17 = r8 * r8
            float r14 = r14 + r17
            r17 = r6[r9]
            float r18 = r17 * r12
            r6 = r6[r5]
            float r19 = r6 * r8
            float r18 = r18 + r19
            float r18 = r18 / r14
            float r6 = r6 * r12
            float r17 = r17 * r8
            float r6 = r6 - r17
            float r6 = r6 / r14
            r8 = 2
            float[] r12 = new float[r8]
            r12[r9] = r18
            r12[r5] = r6
            r0.setComplexFloat(r3, r12)
            long r3 = r3 + r15
            goto L_0x0216
        L_0x024b:
            r3 = 0
        L_0x024d:
            int r6 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r6 >= 0) goto L_0x0372
            float[] r6 = r1.getComplexFloat(r3)
            float[] r8 = r2.getComplexFloat(r3)
            r9 = 0
            r12 = r8[r9]
            float r14 = r12 * r12
            r8 = r8[r5]
            float r17 = r8 * r8
            float r14 = r14 + r17
            r17 = r6[r9]
            float r18 = r17 * r12
            r6 = r6[r5]
            float r19 = r6 * r8
            float r18 = r18 + r19
            float r18 = r18 / r14
            float r6 = r6 * r12
            float r17 = r17 * r8
            float r6 = r6 - r17
            float r6 = r6 / r14
            r8 = 2
            float[] r12 = new float[r8]
            r12[r9] = r18
            r12[r5] = r6
            r0.setComplexFloat(r3, r12)
            long r3 = r3 + r15
            goto L_0x024d
        L_0x0282:
            pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.COMPLEX_DOUBLE
            if (r0 != r1) goto L_0x0373
            pl.edu.icm.jlargearrays.LargeArray r1 = pl.edu.icm.jlargearrays.LargeArrayUtils.convert(r8, r0)
            pl.edu.icm.jlargearrays.ComplexDoubleLargeArray r1 = (pl.edu.icm.jlargearrays.ComplexDoubleLargeArray) r1
            pl.edu.icm.jlargearrays.LargeArray r2 = pl.edu.icm.jlargearrays.LargeArrayUtils.convert(r33, r34)
            pl.edu.icm.jlargearrays.ComplexDoubleLargeArray r2 = (pl.edu.icm.jlargearrays.ComplexDoubleLargeArray) r2
            pl.edu.icm.jlargearrays.LargeArrayType r3 = r1.getType()
            pl.edu.icm.jlargearrays.LargeArrayType r4 = r32.getType()
            if (r3 != r4) goto L_0x02ad
            pl.edu.icm.jlargearrays.LargeArrayType r3 = r2.getType()
            pl.edu.icm.jlargearrays.LargeArrayType r4 = r33.getType()
            if (r3 != r4) goto L_0x02ad
            r3 = 0
            pl.edu.icm.jlargearrays.LargeArray r0 = pl.edu.icm.jlargearrays.LargeArrayUtils.create(r0, r10, r3)
            r7 = r0
            goto L_0x02ba
        L_0x02ad:
            pl.edu.icm.jlargearrays.LargeArrayType r0 = r1.getType()
            pl.edu.icm.jlargearrays.LargeArrayType r3 = r32.getType()
            if (r0 == r3) goto L_0x02b9
            r7 = r1
            goto L_0x02ba
        L_0x02b9:
            r7 = r2
        L_0x02ba:
            r0 = r7
            pl.edu.icm.jlargearrays.ComplexDoubleLargeArray r0 = (pl.edu.icm.jlargearrays.ComplexDoubleLargeArray) r0
            r3 = 2
            if (r14 < r3) goto L_0x0338
            long r3 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getConcurrentThreshold()
            int r3 = (r10 > r3 ? 1 : (r10 == r3 ? 0 : -1))
            if (r3 >= 0) goto L_0x02ca
            goto L_0x0338
        L_0x02ca:
            long r3 = (long) r14
            long r3 = r10 / r3
            java.util.concurrent.Future[] r6 = new java.util.concurrent.Future[r14]
            r8 = 0
        L_0x02d0:
            if (r8 >= r14) goto L_0x02f9
            r12 = r6
            long r5 = (long) r8
            long r25 = r5 * r3
            int r5 = r14 + -1
            if (r8 != r5) goto L_0x02dd
            r27 = r10
            goto L_0x02e1
        L_0x02dd:
            long r5 = r25 + r3
            r27 = r5
        L_0x02e1:
            pl.edu.icm.jlargearrays.LargeArrayArithmetics$16 r5 = new pl.edu.icm.jlargearrays.LargeArrayArithmetics$16
            r24 = r5
            r29 = r1
            r30 = r2
            r31 = r0
            r24.<init>(r25, r27, r29, r30, r31)
            java.util.concurrent.Future r5 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r5)
            r12[r8] = r5
            int r8 = r8 + 1
            r6 = r12
            r5 = 1
            goto L_0x02d0
        L_0x02f9:
            r12 = r6
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r12)     // Catch:{ InterruptedException | ExecutionException -> 0x02fe }
            goto L_0x0372
        L_0x02fe:
            r3 = 0
        L_0x0300:
            int r5 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r5 >= 0) goto L_0x0372
            double[] r5 = r1.getComplexDouble(r3)
            double[] r6 = r2.getComplexDouble(r3)
            r8 = 0
            r17 = r6[r8]
            double r19 = r17 * r17
            r9 = 1
            r21 = r6[r9]
            double r23 = r21 * r21
            double r19 = r19 + r23
            r23 = r5[r8]
            double r25 = r23 * r17
            r27 = r5[r9]
            double r5 = r27 * r21
            double r25 = r25 + r5
            double r25 = r25 / r19
            double r27 = r27 * r17
            double r23 = r23 * r21
            double r27 = r27 - r23
            double r27 = r27 / r19
            r5 = 2
            double[] r6 = new double[r5]
            r6[r8] = r25
            r6[r9] = r27
            r0.setComplexDouble(r3, r6)
            long r3 = r3 + r15
            goto L_0x0300
        L_0x0338:
            r3 = 0
        L_0x033a:
            int r5 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r5 >= 0) goto L_0x0372
            double[] r5 = r1.getComplexDouble(r3)
            double[] r6 = r2.getComplexDouble(r3)
            r8 = 0
            r17 = r6[r8]
            double r19 = r17 * r17
            r9 = 1
            r21 = r6[r9]
            double r23 = r21 * r21
            double r19 = r19 + r23
            r23 = r5[r8]
            double r25 = r23 * r17
            r27 = r5[r9]
            double r5 = r27 * r21
            double r25 = r25 + r5
            double r25 = r25 / r19
            double r27 = r27 * r17
            double r23 = r23 * r21
            double r27 = r27 - r23
            double r27 = r27 / r19
            r5 = 2
            double[] r6 = new double[r5]
            r6[r8] = r25
            r6[r9] = r27
            r0.setComplexDouble(r3, r6)
            long r3 = r3 + r15
            goto L_0x033a
        L_0x0372:
            return r7
        L_0x0373:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r2)
            throw r0
        L_0x0379:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Output type must be numeric."
            r0.<init>(r1)
            throw r0
        L_0x0381:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "a == null || b == null || a.length() != b.length() || !a.isNumeric() || !b.isNumeric()"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: pl.edu.icm.jlargearrays.LargeArrayArithmetics.div(pl.edu.icm.jlargearrays.LargeArray, pl.edu.icm.jlargearrays.LargeArray, pl.edu.icm.jlargearrays.LargeArrayType):pl.edu.icm.jlargearrays.LargeArray");
    }

    public static LargeArray pow(LargeArray largeArray, double d) {
        return pow(largeArray, d, largeArray.getType().isIntegerNumericType() ? LargeArrayType.FLOAT : largeArray.getType());
    }

    /* JADX WARNING: Removed duplicated region for block: B:101:0x02b0  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x0269  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static pl.edu.icm.jlargearrays.LargeArray pow(pl.edu.icm.jlargearrays.LargeArray r28, double r29, pl.edu.icm.jlargearrays.LargeArrayType r31) {
        /*
            r9 = r28
            r10 = r29
            r0 = r31
            if (r9 == 0) goto L_0x02cf
            boolean r1 = r28.isNumeric()
            if (r1 == 0) goto L_0x02cf
            boolean r1 = r31.isNumericType()
            if (r1 == 0) goto L_0x02c7
            long r12 = r28.length()
            boolean r1 = r28.isConstant()
            java.lang.String r2 = "Invalid array type."
            r14 = 0
            if (r1 == 0) goto L_0x006e
            boolean r1 = r31.isIntegerNumericType()
            if (r1 != 0) goto L_0x005d
            boolean r1 = r31.isRealNumericType()
            if (r1 == 0) goto L_0x002f
            goto L_0x005d
        L_0x002f:
            pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.COMPLEX_FLOAT
            if (r0 != r1) goto L_0x0043
            r1 = r9
            pl.edu.icm.jlargearrays.ComplexFloatLargeArray r1 = (pl.edu.icm.jlargearrays.ComplexFloatLargeArray) r1
            float[] r1 = r1.getComplexFloat(r14)
            float[] r1 = complexPow((float[]) r1, (double) r10)
            pl.edu.icm.jlargearrays.LargeArray r0 = pl.edu.icm.jlargearrays.LargeArrayUtils.createConstant(r0, r12, r1)
            return r0
        L_0x0043:
            pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.COMPLEX_DOUBLE
            if (r0 != r1) goto L_0x0057
            r1 = r9
            pl.edu.icm.jlargearrays.ComplexDoubleLargeArray r1 = (pl.edu.icm.jlargearrays.ComplexDoubleLargeArray) r1
            double[] r1 = r1.getComplexDouble(r14)
            double[] r1 = complexPow((double[]) r1, (double) r10)
            pl.edu.icm.jlargearrays.LargeArray r0 = pl.edu.icm.jlargearrays.LargeArrayUtils.createConstant(r0, r12, r1)
            return r0
        L_0x0057:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r2)
            throw r0
        L_0x005d:
            double r1 = r9.getDouble(r14)
            double r1 = org.apache.commons.math3.util.FastMath.pow((double) r1, (double) r10)
            java.lang.Double r1 = java.lang.Double.valueOf(r1)
            pl.edu.icm.jlargearrays.LargeArray r0 = pl.edu.icm.jlargearrays.LargeArrayUtils.createConstant(r0, r12, r1)
            return r0
        L_0x006e:
            r8 = 0
            pl.edu.icm.jlargearrays.LargeArray r6 = pl.edu.icm.jlargearrays.LargeArrayUtils.create(r0, r12, r8)
            int r1 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getNumberOfThreads()
            long r3 = (long) r1
            long r3 = org.apache.commons.math3.util.FastMath.min((long) r12, (long) r3)
            int r7 = (int) r3
            boolean r1 = r31.isIntegerNumericType()
            r16 = 1
            r5 = 2
            if (r1 != 0) goto L_0x0258
            boolean r1 = r31.isRealNumericType()
            if (r1 == 0) goto L_0x0094
            r0 = r5
            r19 = r6
            r15 = r7
            r21 = r8
            goto L_0x025e
        L_0x0094:
            pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.COMPLEX_FLOAT
            r18 = 1
            if (r0 != r1) goto L_0x018d
            pl.edu.icm.jlargearrays.ComplexFloatLargeArray r9 = (pl.edu.icm.jlargearrays.ComplexFloatLargeArray) r9
            r3 = r6
            pl.edu.icm.jlargearrays.ComplexFloatLargeArray r3 = (pl.edu.icm.jlargearrays.ComplexFloatLargeArray) r3
            if (r7 < r5) goto L_0x013f
            long r0 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getConcurrentThreshold()
            int r0 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r0 >= 0) goto L_0x00ab
            goto L_0x013f
        L_0x00ab:
            long r0 = (long) r7
            long r19 = r12 / r0
            java.util.concurrent.Future[] r4 = new java.util.concurrent.Future[r7]
            r1 = r8
        L_0x00b1:
            if (r1 >= r7) goto L_0x00f3
            r21 = r6
            long r5 = (long) r1
            long r5 = r5 * r19
            int r0 = r7 + -1
            if (r1 != r0) goto L_0x00bf
            r22 = r12
            goto L_0x00c1
        L_0x00bf:
            long r22 = r5 + r19
        L_0x00c1:
            pl.edu.icm.jlargearrays.LargeArrayArithmetics$18 r24 = new pl.edu.icm.jlargearrays.LargeArrayArithmetics$18
            r0 = r24
            r25 = r1
            r1 = r5
            r6 = r3
            r26 = r4
            r3 = r22
            r14 = 2
            r5 = r9
            r28 = r6
            r27 = r7
            r15 = r21
            r6 = r29
            r21 = r8
            r8 = r28
            r0.<init>(r1, r3, r5, r6, r8)
            java.util.concurrent.Future r0 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r24)
            r26[r25] = r0
            int r1 = r25 + 1
            r3 = r28
            r5 = r14
            r6 = r15
            r8 = r21
            r4 = r26
            r7 = r27
            r14 = 0
            goto L_0x00b1
        L_0x00f3:
            r28 = r3
            r26 = r4
            r14 = r5
            r15 = r6
            r21 = r8
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r26)     // Catch:{ InterruptedException | ExecutionException -> 0x0100 }
            goto L_0x0187
        L_0x0100:
            r0 = 0
        L_0x0102:
            int r2 = (r0 > r12 ? 1 : (r0 == r12 ? 0 : -1))
            if (r2 >= 0) goto L_0x0187
            float[] r2 = r9.getComplexFloat(r0)
            r3 = r2[r21]
            float r3 = r3 * r3
            r4 = r2[r18]
            float r4 = r4 * r4
            float r3 = r3 + r4
            double r3 = (double) r3
            double r3 = org.apache.commons.math3.util.FastMath.sqrt(r3)
            double r3 = org.apache.commons.math3.util.FastMath.pow((double) r3, (double) r10)
            r5 = r2[r18]
            double r5 = (double) r5
            r2 = r2[r21]
            double r7 = (double) r2
            double r5 = org.apache.commons.math3.util.FastMath.atan2(r5, r7)
            double r5 = r5 * r10
            double r7 = org.apache.commons.math3.util.FastMath.cos(r5)
            double r7 = r7 * r3
            float r2 = (float) r7
            double r5 = org.apache.commons.math3.util.FastMath.sin(r5)
            double r3 = r3 * r5
            float r3 = (float) r3
            float[] r4 = new float[r14]
            r4[r21] = r2
            r4[r18] = r3
            r6 = r28
            r6.setComplexFloat(r0, r4)
            long r0 = r0 + r16
            goto L_0x0102
        L_0x013f:
            r14 = r5
            r15 = r6
            r21 = r8
            r6 = r3
            r0 = 0
        L_0x0146:
            int r2 = (r0 > r12 ? 1 : (r0 == r12 ? 0 : -1))
            if (r2 >= 0) goto L_0x0187
            float[] r2 = r9.getComplexFloat(r0)
            r3 = r2[r21]
            float r3 = r3 * r3
            r4 = r2[r18]
            float r4 = r4 * r4
            float r3 = r3 + r4
            double r3 = (double) r3
            double r3 = org.apache.commons.math3.util.FastMath.sqrt(r3)
            double r3 = org.apache.commons.math3.util.FastMath.pow((double) r3, (double) r10)
            r5 = r2[r18]
            double r7 = (double) r5
            r2 = r2[r21]
            r19 = r15
            double r14 = (double) r2
            double r7 = org.apache.commons.math3.util.FastMath.atan2(r7, r14)
            double r7 = r7 * r10
            double r14 = org.apache.commons.math3.util.FastMath.cos(r7)
            double r14 = r14 * r3
            float r2 = (float) r14
            double r7 = org.apache.commons.math3.util.FastMath.sin(r7)
            double r3 = r3 * r7
            float r3 = (float) r3
            r4 = 2
            float[] r5 = new float[r4]
            r5[r21] = r2
            r5[r18] = r3
            r6.setComplexFloat(r0, r5)
            long r0 = r0 + r16
            r15 = r19
            r14 = 2
            goto L_0x0146
        L_0x0187:
            r19 = r15
        L_0x0189:
            r2 = r19
            goto L_0x02c6
        L_0x018d:
            r19 = r6
            r27 = r7
            r21 = r8
            pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.COMPLEX_DOUBLE
            if (r0 != r1) goto L_0x0252
            pl.edu.icm.jlargearrays.ComplexDoubleLargeArray r9 = (pl.edu.icm.jlargearrays.ComplexDoubleLargeArray) r9
            r14 = r19
            pl.edu.icm.jlargearrays.ComplexDoubleLargeArray r14 = (pl.edu.icm.jlargearrays.ComplexDoubleLargeArray) r14
            r15 = r27
            r0 = 2
            if (r15 < r0) goto L_0x0219
            long r0 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getConcurrentThreshold()
            int r0 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r0 >= 0) goto L_0x01ac
            goto L_0x0219
        L_0x01ac:
            long r0 = (long) r15
            long r24 = r12 / r0
            java.util.concurrent.Future[] r8 = new java.util.concurrent.Future[r15]
            r6 = r21
        L_0x01b3:
            if (r6 >= r15) goto L_0x01da
            long r0 = (long) r6
            long r1 = r0 * r24
            int r7 = r15 + -1
            if (r6 != r7) goto L_0x01be
            r3 = r12
            goto L_0x01c0
        L_0x01be:
            long r3 = r1 + r24
        L_0x01c0:
            pl.edu.icm.jlargearrays.LargeArrayArithmetics$19 r20 = new pl.edu.icm.jlargearrays.LargeArrayArithmetics$19
            r0 = r20
            r5 = r9
            r26 = r6
            r6 = r29
            r27 = r8
            r8 = r14
            r0.<init>(r1, r3, r5, r6, r8)
            java.util.concurrent.Future r0 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r20)
            r27[r26] = r0
            int r6 = r26 + 1
            r8 = r27
            goto L_0x01b3
        L_0x01da:
            r27 = r8
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r27)     // Catch:{ InterruptedException | ExecutionException -> 0x01e0 }
            goto L_0x0189
        L_0x01e0:
            r0 = 0
        L_0x01e2:
            int r2 = (r0 > r12 ? 1 : (r0 == r12 ? 0 : -1))
            if (r2 >= 0) goto L_0x0189
            double[] r2 = r9.getComplexDouble(r0)
            r3 = r2[r21]
            double r3 = r3 * r3
            r5 = r2[r18]
            double r5 = r5 * r5
            double r3 = r3 + r5
            double r3 = org.apache.commons.math3.util.FastMath.sqrt(r3)
            double r3 = org.apache.commons.math3.util.FastMath.pow((double) r3, (double) r10)
            r5 = r2[r18]
            r7 = r2[r21]
            double r5 = org.apache.commons.math3.util.FastMath.atan2(r5, r7)
            double r5 = r5 * r10
            double r7 = org.apache.commons.math3.util.FastMath.cos(r5)
            double r7 = r7 * r3
            double r5 = org.apache.commons.math3.util.FastMath.sin(r5)
            double r3 = r3 * r5
            r2 = 2
            double[] r5 = new double[r2]
            r5[r21] = r7
            r5[r18] = r3
            r14.setComplexDouble(r0, r5)
            long r0 = r0 + r16
            goto L_0x01e2
        L_0x0219:
            r0 = 0
        L_0x021b:
            int r2 = (r0 > r12 ? 1 : (r0 == r12 ? 0 : -1))
            if (r2 >= 0) goto L_0x0189
            double[] r2 = r9.getComplexDouble(r0)
            r3 = r2[r21]
            double r3 = r3 * r3
            r5 = r2[r18]
            double r5 = r5 * r5
            double r3 = r3 + r5
            double r3 = org.apache.commons.math3.util.FastMath.sqrt(r3)
            double r3 = org.apache.commons.math3.util.FastMath.pow((double) r3, (double) r10)
            r5 = r2[r18]
            r7 = r2[r21]
            double r5 = org.apache.commons.math3.util.FastMath.atan2(r5, r7)
            double r5 = r5 * r10
            double r7 = org.apache.commons.math3.util.FastMath.cos(r5)
            double r7 = r7 * r3
            double r5 = org.apache.commons.math3.util.FastMath.sin(r5)
            double r3 = r3 * r5
            r2 = 2
            double[] r5 = new double[r2]
            r5[r21] = r7
            r5[r18] = r3
            r14.setComplexDouble(r0, r5)
            long r0 = r0 + r16
            goto L_0x021b
        L_0x0252:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r2)
            throw r0
        L_0x0258:
            r19 = r6
            r15 = r7
            r21 = r8
            r0 = r5
        L_0x025e:
            if (r15 < r0) goto L_0x02b0
            long r0 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getConcurrentThreshold()
            int r0 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r0 >= 0) goto L_0x0269
            goto L_0x02b0
        L_0x0269:
            long r0 = (long) r15
            long r24 = r12 / r0
            java.util.concurrent.Future[] r14 = new java.util.concurrent.Future[r15]
            r7 = r21
        L_0x0270:
            if (r7 >= r15) goto L_0x0295
            long r0 = (long) r7
            long r1 = r0 * r24
            int r0 = r15 + -1
            if (r7 != r0) goto L_0x027b
            r3 = r12
            goto L_0x027d
        L_0x027b:
            long r3 = r1 + r24
        L_0x027d:
            pl.edu.icm.jlargearrays.LargeArrayArithmetics$17 r18 = new pl.edu.icm.jlargearrays.LargeArrayArithmetics$17
            r0 = r18
            r5 = r19
            r6 = r28
            r21 = r7
            r7 = r29
            r0.<init>(r1, r3, r5, r6, r7)
            java.util.concurrent.Future r0 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r18)
            r14[r21] = r0
            int r7 = r21 + 1
            goto L_0x0270
        L_0x0295:
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r14)     // Catch:{ InterruptedException | ExecutionException -> 0x029a }
            goto L_0x0189
        L_0x029a:
            r14 = 0
        L_0x029c:
            int r0 = (r14 > r12 ? 1 : (r14 == r12 ? 0 : -1))
            if (r0 >= 0) goto L_0x0189
            double r0 = r9.getDouble(r14)
            double r0 = org.apache.commons.math3.util.FastMath.pow((double) r0, (double) r10)
            r2 = r19
            r2.setDouble(r14, r0)
            long r14 = r14 + r16
            goto L_0x029c
        L_0x02b0:
            r2 = r19
            r14 = 0
        L_0x02b4:
            int r0 = (r14 > r12 ? 1 : (r14 == r12 ? 0 : -1))
            if (r0 >= 0) goto L_0x02c6
            double r0 = r9.getDouble(r14)
            double r0 = org.apache.commons.math3.util.FastMath.pow((double) r0, (double) r10)
            r2.setDouble(r14, r0)
            long r14 = r14 + r16
            goto L_0x02b4
        L_0x02c6:
            return r2
        L_0x02c7:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Output type must be numeric."
            r0.<init>(r1)
            throw r0
        L_0x02cf:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "a == null || !a.isNumeric()"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: pl.edu.icm.jlargearrays.LargeArrayArithmetics.pow(pl.edu.icm.jlargearrays.LargeArray, double, pl.edu.icm.jlargearrays.LargeArrayType):pl.edu.icm.jlargearrays.LargeArray");
    }

    public static LargeArray neg(LargeArray largeArray) {
        return neg(largeArray, largeArray.getType());
    }

    public static LargeArray neg(LargeArray largeArray, LargeArrayType largeArrayType) {
        LargeArray largeArray2 = largeArray;
        LargeArrayType largeArrayType2 = largeArrayType;
        if (largeArray2 == null || !largeArray.isNumeric()) {
            throw new IllegalArgumentException("a == null || !a.isNumeric()");
        } else if (largeArrayType.isNumericType()) {
            long length = largeArray.length();
            long j = 0;
            if (!largeArray.isConstant()) {
                LargeArray create = LargeArrayUtils.create(largeArrayType2, length, false);
                int min = (int) FastMath.min(length, (long) ConcurrencyUtils.getNumberOfThreads());
                if (largeArrayType.isIntegerNumericType()) {
                    if (min < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                        while (j < length) {
                            create.setLong(j, -largeArray2.getLong(j));
                            j++;
                        }
                    } else {
                        long j2 = length / ((long) min);
                        Future[] futureArr = new Future[min];
                        int i = 0;
                        while (i < min) {
                            final long j3 = ((long) i) * j2;
                            final long j4 = i == min + -1 ? length : j3 + j2;
                            int i2 = i;
                            final LargeArray largeArray3 = create;
                            Future[] futureArr2 = futureArr;
                            final LargeArray largeArray4 = largeArray;
                            futureArr2[i2] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                                public void run() {
                                    for (long j = j3; j < j4; j++) {
                                        largeArray3.setLong(j, -largeArray4.getLong(j));
                                    }
                                }
                            });
                            i = i2 + 1;
                            futureArr = futureArr2;
                        }
                        try {
                            ConcurrencyUtils.waitForCompletion(futureArr);
                        } catch (InterruptedException | ExecutionException unused) {
                            while (j < length) {
                                create.setLong(j, -largeArray2.getLong(j));
                                j++;
                            }
                        }
                    }
                } else if (largeArrayType.isRealNumericType()) {
                    if (min < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                        while (j < length) {
                            create.setDouble(j, -largeArray2.getDouble(j));
                            j++;
                        }
                    } else {
                        long j5 = length / ((long) min);
                        Future[] futureArr3 = new Future[min];
                        int i3 = 0;
                        while (i3 < min) {
                            final long j6 = ((long) i3) * j5;
                            final long j7 = i3 == min + -1 ? length : j6 + j5;
                            int i4 = i3;
                            final LargeArray largeArray5 = create;
                            Future[] futureArr4 = futureArr3;
                            final LargeArray largeArray6 = largeArray;
                            futureArr4[i4] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                                public void run() {
                                    for (long j = j6; j < j7; j++) {
                                        largeArray5.setDouble(j, -largeArray6.getDouble(j));
                                    }
                                }
                            });
                            i3 = i4 + 1;
                            futureArr3 = futureArr4;
                        }
                        try {
                            ConcurrencyUtils.waitForCompletion(futureArr3);
                        } catch (InterruptedException | ExecutionException unused2) {
                            while (j < length) {
                                create.setDouble(j, -largeArray2.getDouble(j));
                                j++;
                            }
                        }
                    }
                } else if (largeArrayType2 == LargeArrayType.COMPLEX_FLOAT) {
                    ComplexFloatLargeArray complexFloatLargeArray = (ComplexFloatLargeArray) largeArray2;
                    ComplexFloatLargeArray complexFloatLargeArray2 = (ComplexFloatLargeArray) create;
                    if (min < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                        for (long j8 = 0; j8 < length; j8++) {
                            float[] complexFloat = complexFloatLargeArray.getComplexFloat(j8);
                            complexFloatLargeArray2.setComplexFloat(j8, new float[]{-complexFloat[0], -complexFloat[1]});
                        }
                    } else {
                        long j9 = length / ((long) min);
                        Future[] futureArr5 = new Future[min];
                        int i5 = 0;
                        while (i5 < min) {
                            final long j10 = ((long) i5) * j9;
                            final long j11 = i5 == min + -1 ? length : j10 + j9;
                            final ComplexFloatLargeArray complexFloatLargeArray3 = complexFloatLargeArray;
                            final ComplexFloatLargeArray complexFloatLargeArray4 = complexFloatLargeArray2;
                            futureArr5[i5] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                                public void run() {
                                    for (long j = j10; j < j11; j++) {
                                        float[] complexFloat = complexFloatLargeArray3.getComplexFloat(j);
                                        complexFloatLargeArray4.setComplexFloat(j, new float[]{-complexFloat[0], -complexFloat[1]});
                                    }
                                }
                            });
                            i5++;
                        }
                        try {
                            ConcurrencyUtils.waitForCompletion(futureArr5);
                        } catch (InterruptedException | ExecutionException unused3) {
                            for (long j12 = 0; j12 < length; j12++) {
                                float[] complexFloat2 = complexFloatLargeArray.getComplexFloat(j12);
                                complexFloatLargeArray2.setComplexFloat(j12, new float[]{-complexFloat2[0], -complexFloat2[1]});
                            }
                        }
                    }
                } else if (largeArrayType2 == LargeArrayType.COMPLEX_DOUBLE) {
                    ComplexDoubleLargeArray complexDoubleLargeArray = (ComplexDoubleLargeArray) largeArray2;
                    ComplexDoubleLargeArray complexDoubleLargeArray2 = (ComplexDoubleLargeArray) create;
                    if (min < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                        for (long j13 = 0; j13 < length; j13++) {
                            double[] complexDouble = complexDoubleLargeArray.getComplexDouble(j13);
                            complexDoubleLargeArray2.setComplexDouble(j13, new double[]{-complexDouble[0], -complexDouble[1]});
                        }
                    } else {
                        long j14 = length / ((long) min);
                        Future[] futureArr6 = new Future[min];
                        int i6 = 0;
                        while (i6 < min) {
                            final long j15 = ((long) i6) * j14;
                            final long j16 = i6 == min + -1 ? length : j15 + j14;
                            final ComplexDoubleLargeArray complexDoubleLargeArray3 = complexDoubleLargeArray;
                            final ComplexDoubleLargeArray complexDoubleLargeArray4 = complexDoubleLargeArray2;
                            futureArr6[i6] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                                public void run() {
                                    for (long j = j15; j < j16; j++) {
                                        double[] complexDouble = complexDoubleLargeArray3.getComplexDouble(j);
                                        complexDoubleLargeArray4.setComplexDouble(j, new double[]{-complexDouble[0], -complexDouble[1]});
                                    }
                                }
                            });
                            i6++;
                        }
                        try {
                            ConcurrencyUtils.waitForCompletion(futureArr6);
                        } catch (InterruptedException | ExecutionException unused4) {
                            for (long j17 = 0; j17 < length; j17++) {
                                double[] complexDouble2 = complexDoubleLargeArray.getComplexDouble(j17);
                                complexDoubleLargeArray2.setComplexDouble(j17, new double[]{-complexDouble2[0], -complexDouble2[1]});
                            }
                        }
                    }
                } else {
                    throw new IllegalArgumentException("Invalid array type.");
                }
                return create;
            } else if (largeArrayType.isIntegerNumericType()) {
                return LargeArrayUtils.createConstant(largeArrayType2, length, Long.valueOf(-largeArray2.getLong(0)));
            } else {
                if (largeArrayType.isRealNumericType()) {
                    return LargeArrayUtils.createConstant(largeArrayType2, length, Double.valueOf(-largeArray2.getDouble(0)));
                }
                if (largeArrayType2 == LargeArrayType.COMPLEX_FLOAT) {
                    float[] complexFloat3 = ((ComplexFloatLargeArray) largeArray2).getComplexFloat(0);
                    return LargeArrayUtils.createConstant(largeArrayType2, length, new float[]{-complexFloat3[0], -complexFloat3[1]});
                } else if (largeArrayType2 == LargeArrayType.COMPLEX_DOUBLE) {
                    double[] complexDouble3 = ((ComplexDoubleLargeArray) largeArray2).getComplexDouble(0);
                    return LargeArrayUtils.createConstant(largeArrayType2, length, new double[]{-complexDouble3[0], -complexDouble3[1]});
                } else {
                    throw new IllegalArgumentException("Invalid array type.");
                }
            }
        } else {
            throw new IllegalArgumentException("Output type must be numeric.");
        }
    }

    public static LargeArray sqrt(LargeArray largeArray) {
        return sqrt(largeArray, largeArray.getType().isIntegerNumericType() ? LargeArrayType.FLOAT : largeArray.getType());
    }

    public static LargeArray sqrt(LargeArray largeArray, LargeArrayType largeArrayType) {
        LargeArray largeArray2 = largeArray;
        LargeArrayType largeArrayType2 = largeArrayType;
        if (largeArray2 == null || !largeArray.isNumeric()) {
            throw new IllegalArgumentException("a == null || !a.isNumeric()");
        } else if (largeArrayType.isNumericType()) {
            long length = largeArray.length();
            if (!largeArray.isConstant()) {
                char c2 = 0;
                LargeArray create = LargeArrayUtils.create(largeArrayType2, length, false);
                int min = (int) FastMath.min(length, (long) ConcurrencyUtils.getNumberOfThreads());
                if (!largeArrayType.isIntegerNumericType() && !largeArrayType.isRealNumericType()) {
                    double d = 2.0d;
                    if (largeArrayType2 == LargeArrayType.COMPLEX_FLOAT) {
                        ComplexFloatLargeArray complexFloatLargeArray = (ComplexFloatLargeArray) largeArray2;
                        ComplexFloatLargeArray complexFloatLargeArray2 = (ComplexFloatLargeArray) create;
                        if (min < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                            ComplexFloatLargeArray complexFloatLargeArray3 = complexFloatLargeArray2;
                            char c3 = 0;
                            ComplexFloatLargeArray complexFloatLargeArray4 = complexFloatLargeArray3;
                            long j = 0;
                            while (j < length) {
                                float[] complexFloat = complexFloatLargeArray.getComplexFloat(j);
                                float f = complexFloat[c3];
                                float f2 = complexFloat[1];
                                double sqrt = FastMath.sqrt((double) ((f * f) + (f2 * f2)));
                                complexFloatLargeArray4.setComplexFloat(j, new float[]{(float) FastMath.sqrt((((double) complexFloat[c3]) + sqrt) / 2.0d), (float) (((double) FastMath.signum(complexFloat[1])) * FastMath.sqrt((((double) (-complexFloat[c3])) + sqrt) / 2.0d))});
                                j++;
                                c3 = 0;
                            }
                        } else {
                            long j2 = length / ((long) min);
                            Future[] futureArr = new Future[min];
                            int i = 0;
                            while (i < min) {
                                final long j3 = ((long) i) * j2;
                                final long j4 = i == min + -1 ? length : j3 + j2;
                                final ComplexFloatLargeArray complexFloatLargeArray5 = complexFloatLargeArray;
                                final ComplexFloatLargeArray complexFloatLargeArray6 = complexFloatLargeArray2;
                                futureArr[i] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                                    public void run() {
                                        for (long j = j3; j < j4; j++) {
                                            float[] complexFloat = complexFloatLargeArray5.getComplexFloat(j);
                                            float f = complexFloat[0];
                                            float f2 = complexFloat[1];
                                            double sqrt = FastMath.sqrt((double) ((f * f) + (f2 * f2)));
                                            complexFloatLargeArray6.setComplexFloat(j, new float[]{(float) FastMath.sqrt((((double) complexFloat[0]) + sqrt) / 2.0d), (float) (((double) FastMath.signum(complexFloat[1])) * FastMath.sqrt((((double) (-complexFloat[0])) + sqrt) / 2.0d))});
                                        }
                                    }
                                });
                                i++;
                            }
                            try {
                                ConcurrencyUtils.waitForCompletion(futureArr);
                            } catch (InterruptedException | ExecutionException unused) {
                                long j5 = 0;
                                while (j5 < length) {
                                    float[] complexFloat2 = complexFloatLargeArray.getComplexFloat(j5);
                                    float f3 = complexFloat2[c2];
                                    float f4 = complexFloat2[1];
                                    double sqrt2 = FastMath.sqrt((double) ((f3 * f3) + (f4 * f4)));
                                    float[] fArr = {(float) FastMath.sqrt((((double) complexFloat2[c2]) + sqrt2) / d), (float) (((double) FastMath.signum(complexFloat2[1])) * FastMath.sqrt((((double) (-complexFloat2[0])) + sqrt2) / 2.0d))};
                                    ComplexFloatLargeArray complexFloatLargeArray7 = complexFloatLargeArray2;
                                    complexFloatLargeArray7.setComplexFloat(j5, fArr);
                                    j5++;
                                    d = 2.0d;
                                    complexFloatLargeArray2 = complexFloatLargeArray7;
                                    c2 = 0;
                                }
                            }
                        }
                    } else if (largeArrayType2 == LargeArrayType.COMPLEX_DOUBLE) {
                        ComplexDoubleLargeArray complexDoubleLargeArray = (ComplexDoubleLargeArray) largeArray2;
                        ComplexDoubleLargeArray complexDoubleLargeArray2 = (ComplexDoubleLargeArray) create;
                        if (min < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                            for (long j6 = 0; j6 < length; j6++) {
                                double[] complexDouble = complexDoubleLargeArray.getComplexDouble(j6);
                                double d2 = complexDouble[0];
                                double d3 = complexDouble[1];
                                double sqrt3 = FastMath.sqrt((d2 * d2) + (d3 * d3));
                                complexDoubleLargeArray2.setComplexDouble(j6, new double[]{FastMath.sqrt((complexDouble[0] + sqrt3) / 2.0d), FastMath.signum(complexDouble[1]) * FastMath.sqrt(((-complexDouble[0]) + sqrt3) / 2.0d)});
                            }
                        } else {
                            long j7 = length / ((long) min);
                            Future[] futureArr2 = new Future[min];
                            int i2 = 0;
                            while (i2 < min) {
                                final long j8 = ((long) i2) * j7;
                                final long j9 = i2 == min + -1 ? length : j8 + j7;
                                final ComplexDoubleLargeArray complexDoubleLargeArray3 = complexDoubleLargeArray;
                                final ComplexDoubleLargeArray complexDoubleLargeArray4 = complexDoubleLargeArray2;
                                futureArr2[i2] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                                    public void run() {
                                        for (long j = j8; j < j9; j++) {
                                            double[] complexDouble = complexDoubleLargeArray3.getComplexDouble(j);
                                            double d = complexDouble[0];
                                            double d2 = complexDouble[1];
                                            double sqrt = FastMath.sqrt((d * d) + (d2 * d2));
                                            complexDoubleLargeArray4.setComplexDouble(j, new double[]{FastMath.sqrt((complexDouble[0] + sqrt) / 2.0d), FastMath.signum(complexDouble[1]) * FastMath.sqrt(((-complexDouble[0]) + sqrt) / 2.0d)});
                                        }
                                    }
                                });
                                i2++;
                            }
                            try {
                                ConcurrencyUtils.waitForCompletion(futureArr2);
                            } catch (InterruptedException | ExecutionException unused2) {
                                for (long j10 = 0; j10 < length; j10++) {
                                    double[] complexDouble2 = complexDoubleLargeArray.getComplexDouble(j10);
                                    double d4 = complexDouble2[0];
                                    double d5 = complexDouble2[1];
                                    double sqrt4 = FastMath.sqrt((d4 * d4) + (d5 * d5));
                                    complexDoubleLargeArray2.setComplexDouble(j10, new double[]{FastMath.sqrt((complexDouble2[0] + sqrt4) / 2.0d), FastMath.signum(complexDouble2[1]) * FastMath.sqrt(((-complexDouble2[0]) + sqrt4) / 2.0d)});
                                }
                            }
                        }
                    } else {
                        throw new IllegalArgumentException("Invalid array type.");
                    }
                } else if (min < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                    for (long j11 = 0; j11 < length; j11++) {
                        create.setDouble(j11, FastMath.sqrt(largeArray2.getDouble(j11)));
                    }
                } else {
                    long j12 = length / ((long) min);
                    Future[] futureArr3 = new Future[min];
                    int i3 = 0;
                    while (i3 < min) {
                        final long j13 = ((long) i3) * j12;
                        final long j14 = i3 == min + -1 ? length : j13 + j12;
                        final LargeArray largeArray3 = create;
                        final LargeArray largeArray4 = largeArray;
                        futureArr3[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                for (long j = j13; j < j14; j++) {
                                    largeArray3.setDouble(j, FastMath.sqrt(largeArray4.getDouble(j)));
                                }
                            }
                        });
                        i3++;
                    }
                    try {
                        ConcurrencyUtils.waitForCompletion(futureArr3);
                    } catch (InterruptedException | ExecutionException unused3) {
                        for (long j15 = 0; j15 < length; j15++) {
                            create.setDouble(j15, FastMath.sqrt(largeArray2.getDouble(j15)));
                        }
                    }
                }
                return create;
            } else if (largeArrayType.isIntegerNumericType() || largeArrayType.isRealNumericType()) {
                return LargeArrayUtils.createConstant(largeArrayType2, length, Double.valueOf(FastMath.sqrt(largeArray2.getDouble(0))));
            } else {
                if (largeArrayType2 == LargeArrayType.COMPLEX_FLOAT) {
                    return LargeArrayUtils.createConstant(largeArrayType2, length, complexSqrt(((ComplexFloatLargeArray) largeArray2).getComplexFloat(0)));
                }
                if (largeArrayType2 == LargeArrayType.COMPLEX_DOUBLE) {
                    return LargeArrayUtils.createConstant(largeArrayType2, length, complexSqrt(((ComplexDoubleLargeArray) largeArray2).getComplexDouble(0)));
                }
                throw new IllegalArgumentException("Invalid array type.");
            }
        } else {
            throw new IllegalArgumentException("Output type must be numeric.");
        }
    }

    public static LargeArray log(LargeArray largeArray) {
        return log(largeArray, largeArray.getType().isIntegerNumericType() ? LargeArrayType.FLOAT : largeArray.getType());
    }

    public static LargeArray log(LargeArray largeArray, LargeArrayType largeArrayType) {
        LargeArray largeArray2 = largeArray;
        LargeArrayType largeArrayType2 = largeArrayType;
        if (largeArray2 == null || !largeArray.isNumeric()) {
            throw new IllegalArgumentException("a == null || !a.isNumeric()");
        } else if (largeArrayType.isNumericType()) {
            long length = largeArray.length();
            if (!largeArray.isConstant()) {
                char c2 = 0;
                LargeArray create = LargeArrayUtils.create(largeArrayType2, length, false);
                int min = (int) FastMath.min(length, (long) ConcurrencyUtils.getNumberOfThreads());
                if (!largeArrayType.isIntegerNumericType() && !largeArrayType.isRealNumericType()) {
                    char c3 = 1;
                    if (largeArrayType2 == LargeArrayType.COMPLEX_FLOAT) {
                        ComplexFloatLargeArray complexFloatLargeArray = (ComplexFloatLargeArray) largeArray2;
                        ComplexFloatLargeArray complexFloatLargeArray2 = (ComplexFloatLargeArray) create;
                        if (min < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                            char c4 = 0;
                            ComplexFloatLargeArray complexFloatLargeArray3 = complexFloatLargeArray2;
                            long j = 0;
                            while (j < length) {
                                float[] complexFloat = complexFloatLargeArray.getComplexFloat(j);
                                float f = complexFloat[c4];
                                float f2 = complexFloat[c3];
                                double sqrt = FastMath.sqrt((double) ((f * f) + (f2 * f2)));
                                double atan2 = FastMath.atan2((double) complexFloat[c3], (double) complexFloat[c4]);
                                float[] fArr = new float[2];
                                fArr[c4] = (float) FastMath.log(sqrt);
                                fArr[1] = (float) atan2;
                                complexFloatLargeArray3.setComplexFloat(j, fArr);
                                j++;
                                c3 = 1;
                                c4 = 0;
                            }
                        } else {
                            long j2 = length / ((long) min);
                            Future[] futureArr = new Future[min];
                            int i = 0;
                            while (i < min) {
                                final long j3 = ((long) i) * j2;
                                final long j4 = i == min + -1 ? length : j3 + j2;
                                final ComplexFloatLargeArray complexFloatLargeArray4 = complexFloatLargeArray;
                                final ComplexFloatLargeArray complexFloatLargeArray5 = complexFloatLargeArray2;
                                futureArr[i] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                                    public void run() {
                                        for (long j = j3; j < j4; j++) {
                                            float[] complexFloat = complexFloatLargeArray4.getComplexFloat(j);
                                            float f = complexFloat[0];
                                            float f2 = complexFloat[1];
                                            double sqrt = FastMath.sqrt((double) ((f * f) + (f2 * f2)));
                                            double atan2 = FastMath.atan2((double) complexFloat[1], (double) complexFloat[0]);
                                            complexFloatLargeArray5.setComplexFloat(j, new float[]{(float) FastMath.log(sqrt), (float) atan2});
                                        }
                                    }
                                });
                                i++;
                            }
                            try {
                                ConcurrencyUtils.waitForCompletion(futureArr);
                            } catch (InterruptedException | ExecutionException unused) {
                                long j5 = 0;
                                while (j5 < length) {
                                    float[] complexFloat2 = complexFloatLargeArray.getComplexFloat(j5);
                                    float f3 = complexFloat2[c2];
                                    float f4 = complexFloat2[1];
                                    ComplexFloatLargeArray complexFloatLargeArray6 = complexFloatLargeArray2;
                                    float[] fArr2 = {(float) FastMath.log(FastMath.sqrt((double) ((f3 * f3) + (f4 * f4)))), (float) FastMath.atan2((double) complexFloat2[1], (double) complexFloat2[c2])};
                                    ComplexFloatLargeArray complexFloatLargeArray7 = complexFloatLargeArray6;
                                    complexFloatLargeArray7.setComplexFloat(j5, fArr2);
                                    j5++;
                                    complexFloatLargeArray2 = complexFloatLargeArray7;
                                    c2 = 0;
                                }
                            }
                        }
                    } else if (largeArrayType2 == LargeArrayType.COMPLEX_DOUBLE) {
                        ComplexDoubleLargeArray complexDoubleLargeArray = (ComplexDoubleLargeArray) largeArray2;
                        ComplexDoubleLargeArray complexDoubleLargeArray2 = (ComplexDoubleLargeArray) create;
                        if (min < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                            for (long j6 = 0; j6 < length; j6++) {
                                double[] complexDouble = complexDoubleLargeArray.getComplexDouble(j6);
                                double d = complexDouble[0];
                                double d2 = complexDouble[1];
                                complexDoubleLargeArray2.setComplexDouble(j6, new double[]{FastMath.log(FastMath.sqrt((d * d) + (d2 * d2))), FastMath.atan2(complexDouble[1], complexDouble[0])});
                            }
                        } else {
                            long j7 = length / ((long) min);
                            Future[] futureArr2 = new Future[min];
                            int i2 = 0;
                            while (i2 < min) {
                                final long j8 = ((long) i2) * j7;
                                final long j9 = i2 == min + -1 ? length : j8 + j7;
                                final ComplexDoubleLargeArray complexDoubleLargeArray3 = complexDoubleLargeArray;
                                final ComplexDoubleLargeArray complexDoubleLargeArray4 = complexDoubleLargeArray2;
                                futureArr2[i2] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                                    public void run() {
                                        for (long j = j8; j < j9; j++) {
                                            double[] complexDouble = complexDoubleLargeArray3.getComplexDouble(j);
                                            double d = complexDouble[0];
                                            double d2 = complexDouble[1];
                                            double sqrt = FastMath.sqrt((d * d) + (d2 * d2));
                                            complexDoubleLargeArray4.setComplexDouble(j, new double[]{FastMath.log(sqrt), FastMath.atan2(complexDouble[1], complexDouble[0])});
                                        }
                                    }
                                });
                                i2++;
                            }
                            try {
                                ConcurrencyUtils.waitForCompletion(futureArr2);
                            } catch (InterruptedException | ExecutionException unused2) {
                                for (long j10 = 0; j10 < length; j10++) {
                                    double[] complexDouble2 = complexDoubleLargeArray.getComplexDouble(j10);
                                    double d3 = complexDouble2[0];
                                    double d4 = complexDouble2[1];
                                    complexDoubleLargeArray2.setComplexDouble(j10, new double[]{FastMath.log(FastMath.sqrt((d3 * d3) + (d4 * d4))), FastMath.atan2(complexDouble2[1], complexDouble2[0])});
                                }
                            }
                        }
                    } else {
                        throw new IllegalArgumentException("Invalid array type.");
                    }
                } else if (min < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                    for (long j11 = 0; j11 < length; j11++) {
                        create.setDouble(j11, FastMath.log(largeArray2.getDouble(j11)));
                    }
                } else {
                    long j12 = length / ((long) min);
                    Future[] futureArr3 = new Future[min];
                    int i3 = 0;
                    while (i3 < min) {
                        final long j13 = ((long) i3) * j12;
                        final long j14 = i3 == min + -1 ? length : j13 + j12;
                        final LargeArray largeArray3 = create;
                        final LargeArray largeArray4 = largeArray;
                        futureArr3[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                for (long j = j13; j < j14; j++) {
                                    largeArray3.setDouble(j, FastMath.log(largeArray4.getDouble(j)));
                                }
                            }
                        });
                        i3++;
                    }
                    try {
                        ConcurrencyUtils.waitForCompletion(futureArr3);
                    } catch (InterruptedException | ExecutionException unused3) {
                        for (long j15 = 0; j15 < length; j15++) {
                            create.setDouble(j15, FastMath.log(largeArray2.getDouble(j15)));
                        }
                    }
                }
                return create;
            } else if (largeArrayType.isIntegerNumericType() || largeArrayType.isRealNumericType()) {
                return LargeArrayUtils.createConstant(largeArrayType2, length, Double.valueOf(FastMath.log(largeArray2.getDouble(0))));
            } else {
                if (largeArrayType2 == LargeArrayType.COMPLEX_FLOAT) {
                    return LargeArrayUtils.createConstant(largeArrayType2, length, complexLog(((ComplexFloatLargeArray) largeArray2).getComplexFloat(0)));
                }
                if (largeArrayType2 == LargeArrayType.COMPLEX_DOUBLE) {
                    return LargeArrayUtils.createConstant(largeArrayType2, length, complexLog(((ComplexDoubleLargeArray) largeArray2).getComplexDouble(0)));
                }
                throw new IllegalArgumentException("Invalid array type.");
            }
        } else {
            throw new IllegalArgumentException("Output type must be numeric.");
        }
    }

    public static LargeArray log10(LargeArray largeArray) {
        return log10(largeArray, largeArray.getType().isIntegerNumericType() ? LargeArrayType.FLOAT : largeArray.getType());
    }

    public static LargeArray log10(LargeArray largeArray, LargeArrayType largeArrayType) {
        long j;
        int i;
        LargeArray largeArray2 = largeArray;
        LargeArrayType largeArrayType2 = largeArrayType;
        if (largeArray2 == null || !largeArray.isNumeric()) {
            throw new IllegalArgumentException("a == null || !a.isNumeric()");
        } else if (largeArrayType.isNumericType()) {
            long length = largeArray.length();
            if (!largeArray.isConstant()) {
                LargeArray create = LargeArrayUtils.create(largeArrayType2, length, false);
                int min = (int) FastMath.min(length, (long) ConcurrencyUtils.getNumberOfThreads());
                if (largeArrayType.isIntegerNumericType()) {
                    j = length;
                    i = 2;
                } else if (largeArrayType.isRealNumericType()) {
                    i = 2;
                    j = length;
                } else {
                    if (largeArrayType2 == LargeArrayType.COMPLEX_FLOAT) {
                        double log = FastMath.log(10.0d);
                        ComplexFloatLargeArray complexFloatLargeArray = (ComplexFloatLargeArray) largeArray2;
                        ComplexFloatLargeArray complexFloatLargeArray2 = (ComplexFloatLargeArray) create;
                        if (min < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                            ComplexFloatLargeArray complexFloatLargeArray3 = complexFloatLargeArray2;
                            long j2 = 0;
                            while (j2 < length) {
                                float[] complexFloat = complexFloatLargeArray.getComplexFloat(j2);
                                float f = complexFloat[0];
                                float f2 = complexFloat[1];
                                complexFloatLargeArray3.setComplexFloat(j2, new float[]{(float) (FastMath.log(FastMath.sqrt((double) ((f * f) + (f2 * f2)))) / log), (float) (FastMath.atan2((double) complexFloat[1], (double) complexFloat[0]) / log)});
                                j2++;
                                length = length;
                            }
                        } else {
                            long j3 = length / ((long) min);
                            Future[] futureArr = new Future[min];
                            int i2 = 0;
                            while (i2 < min) {
                                final long j4 = ((long) i2) * j3;
                                final long j5 = i2 == min + -1 ? length : j4 + j3;
                                final ComplexFloatLargeArray complexFloatLargeArray4 = complexFloatLargeArray;
                                final double d = log;
                                final ComplexFloatLargeArray complexFloatLargeArray5 = complexFloatLargeArray2;
                                futureArr[i2] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                                    public void run() {
                                        for (long j = j4; j < j5; j++) {
                                            float[] complexFloat = complexFloatLargeArray4.getComplexFloat(j);
                                            float f = complexFloat[0];
                                            float f2 = complexFloat[1];
                                            double sqrt = FastMath.sqrt((double) ((f * f) + (f2 * f2)));
                                            double atan2 = FastMath.atan2((double) complexFloat[1], (double) complexFloat[0]) / d;
                                            complexFloatLargeArray5.setComplexFloat(j, new float[]{(float) (FastMath.log(sqrt) / d), (float) atan2});
                                        }
                                    }
                                });
                                i2++;
                            }
                            try {
                                ConcurrencyUtils.waitForCompletion(futureArr);
                            } catch (InterruptedException | ExecutionException unused) {
                                long j6 = 0;
                                while (j6 < length) {
                                    float[] complexFloat2 = complexFloatLargeArray.getComplexFloat(j6);
                                    float f3 = complexFloat2[0];
                                    float f4 = complexFloat2[1];
                                    ComplexFloatLargeArray complexFloatLargeArray6 = complexFloatLargeArray2;
                                    complexFloatLargeArray6.setComplexFloat(j6, new float[]{(float) (FastMath.log(FastMath.sqrt((double) ((f3 * f3) + (f4 * f4)))) / log), (float) (FastMath.atan2((double) complexFloat2[1], (double) complexFloat2[0]) / log)});
                                    j6++;
                                    complexFloatLargeArray2 = complexFloatLargeArray6;
                                }
                            }
                        }
                    } else {
                        long j7 = length;
                        if (largeArrayType2 == LargeArrayType.COMPLEX_DOUBLE) {
                            double log2 = FastMath.log(10.0d);
                            ComplexDoubleLargeArray complexDoubleLargeArray = (ComplexDoubleLargeArray) largeArray2;
                            ComplexDoubleLargeArray complexDoubleLargeArray2 = (ComplexDoubleLargeArray) create;
                            if (min < 2 || j7 < ConcurrencyUtils.getConcurrentThreshold()) {
                                for (long j8 = 0; j8 < j7; j8++) {
                                    double[] complexDouble = complexDoubleLargeArray.getComplexDouble(j8);
                                    double d2 = complexDouble[0];
                                    double d3 = complexDouble[1];
                                    complexDoubleLargeArray2.setComplexDouble(j8, new double[]{FastMath.log(FastMath.sqrt((d2 * d2) + (d3 * d3))) / log2, FastMath.atan2(complexDouble[1], complexDouble[0]) / log2});
                                }
                            } else {
                                long j9 = j7 / ((long) min);
                                Future[] futureArr2 = new Future[min];
                                int i3 = 0;
                                while (i3 < min) {
                                    final long j10 = ((long) i3) * j9;
                                    final long j11 = i3 == min + -1 ? j7 : j10 + j9;
                                    final ComplexDoubleLargeArray complexDoubleLargeArray3 = complexDoubleLargeArray;
                                    final double d4 = log2;
                                    final ComplexDoubleLargeArray complexDoubleLargeArray4 = complexDoubleLargeArray2;
                                    futureArr2[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                                        public void run() {
                                            for (long j = j10; j < j11; j++) {
                                                double[] complexDouble = complexDoubleLargeArray3.getComplexDouble(j);
                                                double d = complexDouble[0];
                                                double d2 = complexDouble[1];
                                                double sqrt = FastMath.sqrt((d * d) + (d2 * d2));
                                                complexDoubleLargeArray4.setComplexDouble(j, new double[]{FastMath.log(sqrt) / d4, FastMath.atan2(complexDouble[1], complexDouble[0]) / d4});
                                            }
                                        }
                                    });
                                    i3++;
                                }
                                try {
                                    ConcurrencyUtils.waitForCompletion(futureArr2);
                                } catch (InterruptedException | ExecutionException unused2) {
                                    for (long j12 = 0; j12 < j7; j12++) {
                                        double[] complexDouble2 = complexDoubleLargeArray.getComplexDouble(j12);
                                        double d5 = complexDouble2[0];
                                        double d6 = complexDouble2[1];
                                        complexDoubleLargeArray2.setComplexDouble(j12, new double[]{FastMath.log(FastMath.sqrt((d5 * d5) + (d6 * d6))) / log2, FastMath.atan2(complexDouble2[1], complexDouble2[0]) / log2});
                                    }
                                }
                            }
                        } else {
                            throw new IllegalArgumentException("Invalid array type.");
                        }
                    }
                    return create;
                }
                if (min < i || j < ConcurrencyUtils.getConcurrentThreshold()) {
                    for (long j13 = 0; j13 < j; j13++) {
                        create.setDouble(j13, FastMath.log10(largeArray2.getDouble(j13)));
                    }
                    return create;
                }
                long j14 = j / ((long) min);
                Future[] futureArr3 = new Future[min];
                int i4 = 0;
                while (i4 < min) {
                    final long j15 = ((long) i4) * j14;
                    final long j16 = i4 == min + -1 ? j : j15 + j14;
                    final LargeArray largeArray3 = create;
                    final LargeArray largeArray4 = largeArray;
                    futureArr3[i4] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            for (long j = j15; j < j16; j++) {
                                largeArray3.setDouble(j, FastMath.log10(largeArray4.getDouble(j)));
                            }
                        }
                    });
                    i4++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException | ExecutionException unused3) {
                    for (long j17 = 0; j17 < j; j17++) {
                        create.setDouble(j17, FastMath.log10(largeArray2.getDouble(j17)));
                    }
                }
                return create;
            } else if (largeArrayType.isIntegerNumericType() || largeArrayType.isRealNumericType()) {
                return LargeArrayUtils.createConstant(largeArrayType2, length, Double.valueOf(FastMath.log10(largeArray2.getDouble(0))));
            } else {
                if (largeArrayType2 == LargeArrayType.COMPLEX_FLOAT) {
                    return LargeArrayUtils.createConstant(largeArrayType2, length, complexLog10(((ComplexFloatLargeArray) largeArray2).getComplexFloat(0)));
                }
                if (largeArrayType2 == LargeArrayType.COMPLEX_DOUBLE) {
                    return LargeArrayUtils.createConstant(largeArrayType2, length, complexLog10(((ComplexDoubleLargeArray) largeArray2).getComplexDouble(0)));
                }
                throw new IllegalArgumentException("Invalid array type.");
            }
        } else {
            throw new IllegalArgumentException("Output type must be numeric.");
        }
    }

    public static LargeArray exp(LargeArray largeArray) {
        return exp(largeArray, largeArray.getType().isIntegerNumericType() ? LargeArrayType.FLOAT : largeArray.getType());
    }

    public static LargeArray exp(LargeArray largeArray, LargeArrayType largeArrayType) {
        LargeArray largeArray2 = largeArray;
        LargeArrayType largeArrayType2 = largeArrayType;
        if (largeArray2 == null || !largeArray.isNumeric()) {
            throw new IllegalArgumentException("a == null || !a.isNumeric()");
        } else if (largeArrayType.isNumericType()) {
            long length = largeArray.length();
            if (!largeArray.isConstant()) {
                char c2 = 0;
                LargeArray create = LargeArrayUtils.create(largeArrayType2, length, false);
                int min = (int) FastMath.min(length, (long) ConcurrencyUtils.getNumberOfThreads());
                if (!largeArrayType.isIntegerNumericType() && !largeArrayType.isRealNumericType()) {
                    char c3 = 1;
                    if (largeArrayType2 == LargeArrayType.COMPLEX_FLOAT) {
                        ComplexFloatLargeArray complexFloatLargeArray = (ComplexFloatLargeArray) largeArray2;
                        ComplexFloatLargeArray complexFloatLargeArray2 = (ComplexFloatLargeArray) create;
                        if (min < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                            char c4 = 0;
                            ComplexFloatLargeArray complexFloatLargeArray3 = complexFloatLargeArray2;
                            long j = 0;
                            while (j < length) {
                                float[] complexFloat = complexFloatLargeArray.getComplexFloat(j);
                                float[] fArr = new float[2];
                                fArr[c4] = (float) (FastMath.exp((double) complexFloat[c4]) * FastMath.cos((double) complexFloat[c3]));
                                fArr[1] = (float) (FastMath.exp((double) complexFloat[c4]) * FastMath.sin((double) complexFloat[c3]));
                                complexFloatLargeArray3.setComplexFloat(j, fArr);
                                j++;
                                c4 = 0;
                                c3 = 1;
                            }
                        } else {
                            long j2 = length / ((long) min);
                            Future[] futureArr = new Future[min];
                            int i = 0;
                            while (i < min) {
                                final long j3 = ((long) i) * j2;
                                final long j4 = i == min + -1 ? length : j3 + j2;
                                final ComplexFloatLargeArray complexFloatLargeArray4 = complexFloatLargeArray;
                                final ComplexFloatLargeArray complexFloatLargeArray5 = complexFloatLargeArray2;
                                futureArr[i] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                                    public void run() {
                                        for (long j = j3; j < j4; j++) {
                                            float[] complexFloat = complexFloatLargeArray4.getComplexFloat(j);
                                            complexFloatLargeArray5.setComplexFloat(j, new float[]{(float) (FastMath.exp((double) complexFloat[0]) * FastMath.cos((double) complexFloat[1])), (float) (FastMath.exp((double) complexFloat[0]) * FastMath.sin((double) complexFloat[1]))});
                                        }
                                    }
                                });
                                i++;
                            }
                            try {
                                ConcurrencyUtils.waitForCompletion(futureArr);
                            } catch (InterruptedException | ExecutionException unused) {
                                long j5 = 0;
                                while (j5 < length) {
                                    float[] complexFloat2 = complexFloatLargeArray.getComplexFloat(j5);
                                    ComplexFloatLargeArray complexFloatLargeArray6 = complexFloatLargeArray2;
                                    float[] fArr2 = {(float) (FastMath.exp((double) complexFloat2[c2]) * FastMath.cos((double) complexFloat2[1])), (float) (FastMath.exp((double) complexFloat2[c2]) * FastMath.sin((double) complexFloat2[1]))};
                                    ComplexFloatLargeArray complexFloatLargeArray7 = complexFloatLargeArray6;
                                    complexFloatLargeArray7.setComplexFloat(j5, fArr2);
                                    j5++;
                                    complexFloatLargeArray2 = complexFloatLargeArray7;
                                    c2 = 0;
                                }
                            }
                        }
                    } else if (largeArrayType2 == LargeArrayType.COMPLEX_DOUBLE) {
                        ComplexDoubleLargeArray complexDoubleLargeArray = (ComplexDoubleLargeArray) largeArray2;
                        ComplexDoubleLargeArray complexDoubleLargeArray2 = (ComplexDoubleLargeArray) create;
                        if (min < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                            for (long j6 = 0; j6 < length; j6++) {
                                double[] complexDouble = complexDoubleLargeArray.getComplexDouble(j6);
                                complexDoubleLargeArray2.setComplexDouble(j6, new double[]{FastMath.exp(complexDouble[0]) * FastMath.cos(complexDouble[1]), FastMath.exp(complexDouble[0]) * FastMath.sin(complexDouble[1])});
                            }
                        } else {
                            long j7 = length / ((long) min);
                            Future[] futureArr2 = new Future[min];
                            int i2 = 0;
                            while (i2 < min) {
                                final long j8 = ((long) i2) * j7;
                                final long j9 = i2 == min + -1 ? length : j8 + j7;
                                final ComplexDoubleLargeArray complexDoubleLargeArray3 = complexDoubleLargeArray;
                                final ComplexDoubleLargeArray complexDoubleLargeArray4 = complexDoubleLargeArray2;
                                futureArr2[i2] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                                    public void run() {
                                        for (long j = j8; j < j9; j++) {
                                            double[] complexDouble = complexDoubleLargeArray3.getComplexDouble(j);
                                            complexDoubleLargeArray4.setComplexDouble(j, new double[]{FastMath.exp(complexDouble[0]) * FastMath.cos(complexDouble[1]), FastMath.exp(complexDouble[0]) * FastMath.sin(complexDouble[1])});
                                        }
                                    }
                                });
                                i2++;
                            }
                            try {
                                ConcurrencyUtils.waitForCompletion(futureArr2);
                            } catch (InterruptedException | ExecutionException unused2) {
                                for (long j10 = 0; j10 < length; j10++) {
                                    double[] complexDouble2 = complexDoubleLargeArray.getComplexDouble(j10);
                                    complexDoubleLargeArray2.setComplexDouble(j10, new double[]{FastMath.exp(complexDouble2[0]) * FastMath.cos(complexDouble2[1]), FastMath.exp(complexDouble2[0]) * FastMath.sin(complexDouble2[1])});
                                }
                            }
                        }
                    } else {
                        throw new IllegalArgumentException("Invalid array type.");
                    }
                } else if (min < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                    for (long j11 = 0; j11 < length; j11++) {
                        create.setDouble(j11, FastMath.exp(largeArray2.getDouble(j11)));
                    }
                } else {
                    long j12 = length / ((long) min);
                    Future[] futureArr3 = new Future[min];
                    int i3 = 0;
                    while (i3 < min) {
                        final long j13 = ((long) i3) * j12;
                        final long j14 = i3 == min + -1 ? length : j13 + j12;
                        final LargeArray largeArray3 = create;
                        final LargeArray largeArray4 = largeArray;
                        futureArr3[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                for (long j = j13; j < j14; j++) {
                                    largeArray3.setDouble(j, FastMath.exp(largeArray4.getDouble(j)));
                                }
                            }
                        });
                        i3++;
                    }
                    try {
                        ConcurrencyUtils.waitForCompletion(futureArr3);
                    } catch (InterruptedException | ExecutionException unused3) {
                        for (long j15 = 0; j15 < length; j15++) {
                            create.setDouble(j15, FastMath.exp(largeArray2.getDouble(j15)));
                        }
                    }
                }
                return create;
            } else if (largeArrayType.isIntegerNumericType() || largeArrayType.isRealNumericType()) {
                return LargeArrayUtils.createConstant(largeArrayType2, length, Double.valueOf(FastMath.exp(largeArray2.getDouble(0))));
            } else {
                if (largeArrayType2 == LargeArrayType.COMPLEX_FLOAT) {
                    return LargeArrayUtils.createConstant(largeArrayType2, length, complexExp(((ComplexFloatLargeArray) largeArray2).getComplexFloat(0)));
                }
                if (largeArrayType2 == LargeArrayType.COMPLEX_DOUBLE) {
                    return LargeArrayUtils.createConstant(largeArrayType2, length, complexExp(((ComplexDoubleLargeArray) largeArray2).getComplexDouble(0)));
                }
                throw new IllegalArgumentException("Invalid array type.");
            }
        } else {
            throw new IllegalArgumentException("Output type must be numeric.");
        }
    }

    public static LargeArray abs(LargeArray largeArray) {
        return abs(largeArray, largeArray.getType() == LargeArrayType.COMPLEX_FLOAT ? LargeArrayType.FLOAT : largeArray.getType() == LargeArrayType.COMPLEX_DOUBLE ? LargeArrayType.DOUBLE : largeArray.getType());
    }

    public static LargeArray abs(LargeArray largeArray, LargeArrayType largeArrayType) {
        LargeArray largeArray2 = largeArray;
        LargeArrayType largeArrayType2 = largeArrayType;
        if (largeArray2 == null || !largeArray.isNumeric()) {
            throw new IllegalArgumentException("a == null || !a.isNumeric()");
        } else if (largeArrayType.isNumericType()) {
            long length = largeArray.length();
            long j = 0;
            if (!largeArray.isConstant()) {
                LargeArray create = LargeArrayUtils.create(largeArrayType2, length, false);
                int min = (int) FastMath.min(length, (long) ConcurrencyUtils.getNumberOfThreads());
                if (largeArray.getType().isIntegerNumericType()) {
                    if (min < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                        while (j < length) {
                            create.setDouble(j, FastMath.abs(largeArray2.getDouble(j)));
                            j++;
                        }
                    } else {
                        long j2 = length / ((long) min);
                        Future[] futureArr = new Future[min];
                        int i = 0;
                        while (i < min) {
                            final long j3 = ((long) i) * j2;
                            final long j4 = i == min + -1 ? length : j3 + j2;
                            final LargeArray largeArray3 = create;
                            final LargeArray largeArray4 = largeArray;
                            futureArr[i] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                                public void run() {
                                    for (long j = j3; j < j4; j++) {
                                        largeArray3.setLong(j, FastMath.abs(largeArray4.getLong(j)));
                                    }
                                }
                            });
                            i++;
                        }
                        try {
                            ConcurrencyUtils.waitForCompletion(futureArr);
                        } catch (InterruptedException | ExecutionException unused) {
                            while (j < length) {
                                create.setDouble(j, FastMath.abs(largeArray2.getDouble(j)));
                                j++;
                            }
                        }
                    }
                } else if (largeArray.getType().isRealNumericType()) {
                    if (min < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                        while (j < length) {
                            create.setDouble(j, FastMath.abs(largeArray2.getDouble(j)));
                            j++;
                        }
                    } else {
                        long j5 = length / ((long) min);
                        Future[] futureArr2 = new Future[min];
                        int i2 = 0;
                        while (i2 < min) {
                            final long j6 = ((long) i2) * j5;
                            final long j7 = i2 == min + -1 ? length : j6 + j5;
                            final LargeArray largeArray5 = create;
                            final LargeArray largeArray6 = largeArray;
                            futureArr2[i2] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                                public void run() {
                                    for (long j = j6; j < j7; j++) {
                                        largeArray5.setDouble(j, FastMath.abs(largeArray6.getDouble(j)));
                                    }
                                }
                            });
                            i2++;
                        }
                        try {
                            ConcurrencyUtils.waitForCompletion(futureArr2);
                        } catch (InterruptedException | ExecutionException unused2) {
                            while (j < length) {
                                create.setDouble(j, FastMath.abs(largeArray2.getDouble(j)));
                                j++;
                            }
                        }
                    }
                } else if (largeArray.getType() == LargeArrayType.COMPLEX_FLOAT) {
                    ComplexFloatLargeArray complexFloatLargeArray = (ComplexFloatLargeArray) largeArray2;
                    if (min < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                        LargeArray largeArray7 = create;
                        for (long j8 = 0; j8 < length; j8++) {
                            float[] complexFloat = complexFloatLargeArray.getComplexFloat(j8);
                            float f = complexFloat[0];
                            float f2 = complexFloat[1];
                            largeArray7.setFloat(j8, (float) FastMath.sqrt((double) ((f * f) + (f2 * f2))));
                        }
                        return largeArray7;
                    }
                    long j9 = length / ((long) min);
                    Future[] futureArr3 = new Future[min];
                    int i3 = 0;
                    while (i3 < min) {
                        long j10 = ((long) i3) * j9;
                        long j11 = i3 == min + -1 ? length : j10 + j9;
                        int i4 = min;
                        final long j12 = j10;
                        LargeArray largeArray8 = create;
                        final long j13 = j11;
                        final ComplexFloatLargeArray complexFloatLargeArray2 = complexFloatLargeArray;
                        final LargeArray largeArray9 = largeArray8;
                        futureArr3[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                for (long j = j12; j < j13; j++) {
                                    float[] complexFloat = complexFloatLargeArray2.getComplexFloat(j);
                                    LargeArray largeArray = largeArray9;
                                    float f = complexFloat[0];
                                    float f2 = complexFloat[1];
                                    largeArray.setFloat(j, (float) FastMath.sqrt((double) ((f * f) + (f2 * f2))));
                                }
                            }
                        });
                        i3++;
                        create = largeArray8;
                        min = i4;
                    }
                    LargeArray largeArray10 = create;
                    try {
                        ConcurrencyUtils.waitForCompletion(futureArr3);
                        return largeArray10;
                    } catch (InterruptedException | ExecutionException unused3) {
                        for (long j14 = 0; j14 < length; j14++) {
                            float[] complexFloat2 = complexFloatLargeArray.getComplexFloat(j14);
                            float f3 = complexFloat2[0];
                            float f4 = complexFloat2[1];
                            largeArray10.setFloat(j14, (float) FastMath.sqrt((double) ((f3 * f3) + (f4 * f4))));
                        }
                        return largeArray10;
                    }
                } else {
                    int i5 = min;
                    LargeArray largeArray11 = create;
                    if (largeArray.getType() == LargeArrayType.COMPLEX_DOUBLE) {
                        ComplexDoubleLargeArray complexDoubleLargeArray = (ComplexDoubleLargeArray) largeArray2;
                        int i6 = i5;
                        if (i6 < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                            for (long j15 = 0; j15 < length; j15++) {
                                double[] complexDouble = complexDoubleLargeArray.getComplexDouble(j15);
                                double d = complexDouble[0];
                                double d2 = complexDouble[1];
                                largeArray11.setDouble(j15, FastMath.sqrt((d * d) + (d2 * d2)));
                            }
                            return largeArray11;
                        }
                        long j16 = length / ((long) i6);
                        Future[] futureArr4 = new Future[i6];
                        int i7 = 0;
                        while (i7 < i6) {
                            final long j17 = ((long) i7) * j16;
                            final long j18 = i7 == i6 + -1 ? length : j17 + j16;
                            final ComplexDoubleLargeArray complexDoubleLargeArray2 = complexDoubleLargeArray;
                            final LargeArray largeArray12 = largeArray11;
                            futureArr4[i7] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                                public void run() {
                                    for (long j = j17; j < j18; j++) {
                                        double[] complexDouble = complexDoubleLargeArray2.getComplexDouble(j);
                                        LargeArray largeArray = largeArray12;
                                        double d = complexDouble[0];
                                        double d2 = complexDouble[1];
                                        largeArray.setDouble(j, FastMath.sqrt((d * d) + (d2 * d2)));
                                    }
                                }
                            });
                            i7++;
                        }
                        try {
                            ConcurrencyUtils.waitForCompletion(futureArr4);
                            return largeArray11;
                        } catch (InterruptedException | ExecutionException unused4) {
                            for (long j19 = 0; j19 < length; j19++) {
                                double[] complexDouble2 = complexDoubleLargeArray.getComplexDouble(j19);
                                double d3 = complexDouble2[0];
                                double d4 = complexDouble2[1];
                                largeArray11.setDouble(j19, FastMath.sqrt((d3 * d3) + (d4 * d4)));
                            }
                            return largeArray11;
                        }
                    } else {
                        throw new IllegalArgumentException("Invalid array type.");
                    }
                }
                return create;
            } else if (largeArrayType.isIntegerNumericType()) {
                return LargeArrayUtils.createConstant(largeArrayType2, length, Long.valueOf(FastMath.abs(largeArray2.getLong(0))));
            } else {
                if (largeArrayType.isRealNumericType()) {
                    return LargeArrayUtils.createConstant(largeArrayType2, length, Double.valueOf(FastMath.abs(largeArray2.getDouble(0))));
                }
                if (largeArrayType2 == LargeArrayType.COMPLEX_FLOAT) {
                    return LargeArrayUtils.createConstant(largeArrayType2, length, Float.valueOf(complexAbs(((ComplexFloatLargeArray) largeArray2).getComplexFloat(0))));
                }
                if (largeArrayType2 == LargeArrayType.COMPLEX_DOUBLE) {
                    return LargeArrayUtils.createConstant(largeArrayType2, length, Double.valueOf(complexAbs(((ComplexDoubleLargeArray) largeArray2).getComplexDouble(0))));
                }
                throw new IllegalArgumentException("Invalid array type.");
            }
        } else {
            throw new IllegalArgumentException("Output type must be numeric.");
        }
    }

    public static LargeArray sin(LargeArray largeArray) {
        return sin(largeArray, largeArray.getType().isIntegerNumericType() ? LargeArrayType.FLOAT : largeArray.getType());
    }

    public static LargeArray sin(LargeArray largeArray, LargeArrayType largeArrayType) {
        LargeArray largeArray2 = largeArray;
        LargeArrayType largeArrayType2 = largeArrayType;
        if (largeArray2 == null || !largeArray.isNumeric()) {
            throw new IllegalArgumentException("a == null || !a.isNumeric()");
        } else if (largeArrayType.isNumericType()) {
            long length = largeArray.length();
            if (!largeArray.isConstant()) {
                char c2 = 0;
                LargeArray create = LargeArrayUtils.create(largeArrayType2, length, false);
                int min = (int) FastMath.min(length, (long) ConcurrencyUtils.getNumberOfThreads());
                if (!largeArrayType.isIntegerNumericType() && !largeArrayType.isRealNumericType()) {
                    char c3 = 1;
                    if (largeArrayType2 == LargeArrayType.COMPLEX_FLOAT) {
                        ComplexFloatLargeArray complexFloatLargeArray = (ComplexFloatLargeArray) largeArray2;
                        ComplexFloatLargeArray complexFloatLargeArray2 = (ComplexFloatLargeArray) create;
                        if (min < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                            char c4 = 0;
                            ComplexFloatLargeArray complexFloatLargeArray3 = complexFloatLargeArray2;
                            long j = 0;
                            while (j < length) {
                                float[] complexFloat = complexFloatLargeArray.getComplexFloat(j);
                                float[] fArr = new float[2];
                                fArr[c4] = (float) (FastMath.sin((double) complexFloat[c4]) * FastMath.cosh((double) complexFloat[c3]));
                                fArr[1] = (float) (FastMath.cos((double) complexFloat[c4]) * FastMath.sinh((double) complexFloat[c3]));
                                complexFloatLargeArray3.setComplexFloat(j, fArr);
                                j++;
                                c4 = 0;
                                c3 = 1;
                            }
                        } else {
                            long j2 = length / ((long) min);
                            Future[] futureArr = new Future[min];
                            int i = 0;
                            while (i < min) {
                                final long j3 = ((long) i) * j2;
                                final long j4 = i == min + -1 ? length : j3 + j2;
                                final ComplexFloatLargeArray complexFloatLargeArray4 = complexFloatLargeArray;
                                final ComplexFloatLargeArray complexFloatLargeArray5 = complexFloatLargeArray2;
                                futureArr[i] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                                    public void run() {
                                        for (long j = j3; j < j4; j++) {
                                            float[] complexFloat = complexFloatLargeArray4.getComplexFloat(j);
                                            complexFloatLargeArray5.setComplexFloat(j, new float[]{(float) (FastMath.sin((double) complexFloat[0]) * FastMath.cosh((double) complexFloat[1])), (float) (FastMath.cos((double) complexFloat[0]) * FastMath.sinh((double) complexFloat[1]))});
                                        }
                                    }
                                });
                                i++;
                            }
                            try {
                                ConcurrencyUtils.waitForCompletion(futureArr);
                            } catch (InterruptedException | ExecutionException unused) {
                                long j5 = 0;
                                while (j5 < length) {
                                    float[] complexFloat2 = complexFloatLargeArray.getComplexFloat(j5);
                                    ComplexFloatLargeArray complexFloatLargeArray6 = complexFloatLargeArray2;
                                    float[] fArr2 = {(float) (FastMath.sin((double) complexFloat2[c2]) * FastMath.cosh((double) complexFloat2[1])), (float) (FastMath.cos((double) complexFloat2[c2]) * FastMath.sinh((double) complexFloat2[1]))};
                                    ComplexFloatLargeArray complexFloatLargeArray7 = complexFloatLargeArray6;
                                    complexFloatLargeArray7.setComplexFloat(j5, fArr2);
                                    j5++;
                                    complexFloatLargeArray2 = complexFloatLargeArray7;
                                    c2 = 0;
                                }
                            }
                        }
                    } else if (largeArrayType2 == LargeArrayType.COMPLEX_DOUBLE) {
                        ComplexDoubleLargeArray complexDoubleLargeArray = (ComplexDoubleLargeArray) largeArray2;
                        ComplexDoubleLargeArray complexDoubleLargeArray2 = (ComplexDoubleLargeArray) create;
                        if (min < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                            for (long j6 = 0; j6 < length; j6++) {
                                double[] complexDouble = complexDoubleLargeArray.getComplexDouble(j6);
                                complexDoubleLargeArray2.setComplexDouble(j6, new double[]{FastMath.sin(complexDouble[0]) * FastMath.cosh(complexDouble[1]), FastMath.cos(complexDouble[0]) * FastMath.sinh(complexDouble[1])});
                            }
                        } else {
                            long j7 = length / ((long) min);
                            Future[] futureArr2 = new Future[min];
                            int i2 = 0;
                            while (i2 < min) {
                                final long j8 = ((long) i2) * j7;
                                final long j9 = i2 == min + -1 ? length : j8 + j7;
                                final ComplexDoubleLargeArray complexDoubleLargeArray3 = complexDoubleLargeArray;
                                final ComplexDoubleLargeArray complexDoubleLargeArray4 = complexDoubleLargeArray2;
                                futureArr2[i2] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                                    public void run() {
                                        for (long j = j8; j < j9; j++) {
                                            double[] complexDouble = complexDoubleLargeArray3.getComplexDouble(j);
                                            complexDoubleLargeArray4.setComplexDouble(j, new double[]{FastMath.sin(complexDouble[0]) * FastMath.cosh(complexDouble[1]), FastMath.cos(complexDouble[0]) * FastMath.sinh(complexDouble[1])});
                                        }
                                    }
                                });
                                i2++;
                            }
                            try {
                                ConcurrencyUtils.waitForCompletion(futureArr2);
                            } catch (InterruptedException | ExecutionException unused2) {
                                for (long j10 = 0; j10 < length; j10++) {
                                    double[] complexDouble2 = complexDoubleLargeArray.getComplexDouble(j10);
                                    complexDoubleLargeArray2.setComplexDouble(j10, new double[]{FastMath.sin(complexDouble2[0]) * FastMath.cosh(complexDouble2[1]), FastMath.cos(complexDouble2[0]) * FastMath.sinh(complexDouble2[1])});
                                }
                            }
                        }
                    } else {
                        throw new IllegalArgumentException("Invalid array type.");
                    }
                } else if (min < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                    for (long j11 = 0; j11 < length; j11++) {
                        create.setDouble(j11, FastMath.sin(largeArray2.getDouble(j11)));
                    }
                } else {
                    long j12 = length / ((long) min);
                    Future[] futureArr3 = new Future[min];
                    int i3 = 0;
                    while (i3 < min) {
                        final long j13 = ((long) i3) * j12;
                        final long j14 = i3 == min + -1 ? length : j13 + j12;
                        final LargeArray largeArray3 = create;
                        final LargeArray largeArray4 = largeArray;
                        futureArr3[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                for (long j = j13; j < j14; j++) {
                                    largeArray3.setDouble(j, FastMath.sin(largeArray4.getDouble(j)));
                                }
                            }
                        });
                        i3++;
                    }
                    try {
                        ConcurrencyUtils.waitForCompletion(futureArr3);
                    } catch (InterruptedException | ExecutionException unused3) {
                        for (long j15 = 0; j15 < length; j15++) {
                            create.setDouble(j15, FastMath.sin(largeArray2.getDouble(j15)));
                        }
                    }
                }
                return create;
            } else if (largeArrayType.isIntegerNumericType() || largeArrayType.isRealNumericType()) {
                return LargeArrayUtils.createConstant(largeArrayType2, length, Double.valueOf(FastMath.sin(largeArray2.getDouble(0))));
            } else {
                if (largeArrayType2 == LargeArrayType.COMPLEX_FLOAT) {
                    return LargeArrayUtils.createConstant(largeArrayType2, length, complexSin(((ComplexFloatLargeArray) largeArray2).getComplexFloat(0)));
                }
                if (largeArrayType2 == LargeArrayType.COMPLEX_DOUBLE) {
                    return LargeArrayUtils.createConstant(largeArrayType2, length, complexSin(((ComplexDoubleLargeArray) largeArray2).getComplexDouble(0)));
                }
                throw new IllegalArgumentException("Invalid array type.");
            }
        } else {
            throw new IllegalArgumentException("Output type must be numeric.");
        }
    }

    public static LargeArray cos(LargeArray largeArray) {
        return cos(largeArray, largeArray.getType().isIntegerNumericType() ? LargeArrayType.FLOAT : largeArray.getType());
    }

    public static LargeArray cos(LargeArray largeArray, LargeArrayType largeArrayType) {
        LargeArray largeArray2 = largeArray;
        LargeArrayType largeArrayType2 = largeArrayType;
        if (largeArray2 == null || !largeArray.isNumeric()) {
            throw new IllegalArgumentException("a == null || !a.isNumeric()");
        } else if (largeArrayType.isNumericType()) {
            long length = largeArray.length();
            if (!largeArray.isConstant()) {
                char c2 = 0;
                LargeArray create = LargeArrayUtils.create(largeArrayType2, length, false);
                int min = (int) FastMath.min(length, (long) ConcurrencyUtils.getNumberOfThreads());
                if (!largeArrayType.isIntegerNumericType() && !largeArrayType.isRealNumericType()) {
                    char c3 = 1;
                    if (largeArrayType2 == LargeArrayType.COMPLEX_FLOAT) {
                        ComplexFloatLargeArray complexFloatLargeArray = (ComplexFloatLargeArray) largeArray2;
                        ComplexFloatLargeArray complexFloatLargeArray2 = (ComplexFloatLargeArray) create;
                        if (min < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                            char c4 = 0;
                            ComplexFloatLargeArray complexFloatLargeArray3 = complexFloatLargeArray2;
                            long j = 0;
                            while (j < length) {
                                float[] complexFloat = complexFloatLargeArray.getComplexFloat(j);
                                float[] fArr = new float[2];
                                fArr[c4] = (float) (FastMath.cos((double) complexFloat[c4]) * FastMath.cosh((double) complexFloat[c3]));
                                fArr[1] = (float) ((-FastMath.sin((double) complexFloat[c4])) * FastMath.sinh((double) complexFloat[c3]));
                                complexFloatLargeArray3.setComplexFloat(j, fArr);
                                j++;
                                c4 = 0;
                                c3 = 1;
                            }
                        } else {
                            long j2 = length / ((long) min);
                            Future[] futureArr = new Future[min];
                            int i = 0;
                            while (i < min) {
                                final long j3 = ((long) i) * j2;
                                final long j4 = i == min + -1 ? length : j3 + j2;
                                final ComplexFloatLargeArray complexFloatLargeArray4 = complexFloatLargeArray;
                                final ComplexFloatLargeArray complexFloatLargeArray5 = complexFloatLargeArray2;
                                futureArr[i] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                                    public void run() {
                                        for (long j = j3; j < j4; j++) {
                                            float[] complexFloat = complexFloatLargeArray4.getComplexFloat(j);
                                            complexFloatLargeArray5.setComplexFloat(j, new float[]{(float) (FastMath.cos((double) complexFloat[0]) * FastMath.cosh((double) complexFloat[1])), (float) ((-FastMath.sin((double) complexFloat[0])) * FastMath.sinh((double) complexFloat[1]))});
                                        }
                                    }
                                });
                                i++;
                            }
                            try {
                                ConcurrencyUtils.waitForCompletion(futureArr);
                            } catch (InterruptedException | ExecutionException unused) {
                                long j5 = 0;
                                while (j5 < length) {
                                    float[] complexFloat2 = complexFloatLargeArray.getComplexFloat(j5);
                                    ComplexFloatLargeArray complexFloatLargeArray6 = complexFloatLargeArray2;
                                    float[] fArr2 = {(float) (FastMath.cos((double) complexFloat2[c2]) * FastMath.cosh((double) complexFloat2[1])), (float) ((-FastMath.sin((double) complexFloat2[c2])) * FastMath.sinh((double) complexFloat2[1]))};
                                    ComplexFloatLargeArray complexFloatLargeArray7 = complexFloatLargeArray6;
                                    complexFloatLargeArray7.setComplexFloat(j5, fArr2);
                                    j5++;
                                    complexFloatLargeArray2 = complexFloatLargeArray7;
                                    c2 = 0;
                                }
                            }
                        }
                    } else if (largeArrayType2 == LargeArrayType.COMPLEX_DOUBLE) {
                        ComplexDoubleLargeArray complexDoubleLargeArray = (ComplexDoubleLargeArray) largeArray2;
                        ComplexDoubleLargeArray complexDoubleLargeArray2 = (ComplexDoubleLargeArray) create;
                        if (min < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                            for (long j6 = 0; j6 < length; j6++) {
                                double[] complexDouble = complexDoubleLargeArray.getComplexDouble(j6);
                                complexDoubleLargeArray2.setComplexDouble(j6, new double[]{FastMath.cos(complexDouble[0]) * FastMath.cosh(complexDouble[1]), (-FastMath.sin(complexDouble[0])) * FastMath.sinh(complexDouble[1])});
                            }
                        } else {
                            long j7 = length / ((long) min);
                            Future[] futureArr2 = new Future[min];
                            int i2 = 0;
                            while (i2 < min) {
                                final long j8 = ((long) i2) * j7;
                                final long j9 = i2 == min + -1 ? length : j8 + j7;
                                final ComplexDoubleLargeArray complexDoubleLargeArray3 = complexDoubleLargeArray;
                                final ComplexDoubleLargeArray complexDoubleLargeArray4 = complexDoubleLargeArray2;
                                futureArr2[i2] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                                    public void run() {
                                        for (long j = j8; j < j9; j++) {
                                            double[] complexDouble = complexDoubleLargeArray3.getComplexDouble(j);
                                            complexDoubleLargeArray4.setComplexDouble(j, new double[]{FastMath.cos(complexDouble[0]) * FastMath.cosh(complexDouble[1]), (-FastMath.sin(complexDouble[0])) * FastMath.sinh(complexDouble[1])});
                                        }
                                    }
                                });
                                i2++;
                            }
                            try {
                                ConcurrencyUtils.waitForCompletion(futureArr2);
                            } catch (InterruptedException | ExecutionException unused2) {
                                for (long j10 = 0; j10 < length; j10++) {
                                    double[] complexDouble2 = complexDoubleLargeArray.getComplexDouble(j10);
                                    complexDoubleLargeArray2.setComplexDouble(j10, new double[]{FastMath.cos(complexDouble2[0]) * FastMath.cosh(complexDouble2[1]), (-FastMath.sin(complexDouble2[0])) * FastMath.sinh(complexDouble2[1])});
                                }
                            }
                        }
                    } else {
                        throw new IllegalArgumentException("Invalid array type.");
                    }
                } else if (min < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                    for (long j11 = 0; j11 < length; j11++) {
                        create.setDouble(j11, FastMath.cos(largeArray2.getDouble(j11)));
                    }
                } else {
                    long j12 = length / ((long) min);
                    Future[] futureArr3 = new Future[min];
                    int i3 = 0;
                    while (i3 < min) {
                        final long j13 = ((long) i3) * j12;
                        final long j14 = i3 == min + -1 ? length : j13 + j12;
                        final LargeArray largeArray3 = create;
                        final LargeArray largeArray4 = largeArray;
                        futureArr3[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                            public void run() {
                                for (long j = j13; j < j14; j++) {
                                    largeArray3.setDouble(j, FastMath.cos(largeArray4.getDouble(j)));
                                }
                            }
                        });
                        i3++;
                    }
                    try {
                        ConcurrencyUtils.waitForCompletion(futureArr3);
                    } catch (InterruptedException | ExecutionException unused3) {
                        for (long j15 = 0; j15 < length; j15++) {
                            create.setDouble(j15, FastMath.cos(largeArray2.getDouble(j15)));
                        }
                    }
                }
                return create;
            } else if (largeArrayType.isIntegerNumericType() || largeArrayType.isRealNumericType()) {
                return LargeArrayUtils.createConstant(largeArrayType2, length, Double.valueOf(FastMath.cos(largeArray2.getDouble(0))));
            } else {
                if (largeArrayType2 == LargeArrayType.COMPLEX_FLOAT) {
                    return LargeArrayUtils.createConstant(largeArrayType2, length, complexCos(((ComplexFloatLargeArray) largeArray2).getComplexFloat(0)));
                }
                if (largeArrayType2 == LargeArrayType.COMPLEX_DOUBLE) {
                    return LargeArrayUtils.createConstant(largeArrayType2, length, complexCos(((ComplexDoubleLargeArray) largeArray2).getComplexDouble(0)));
                }
                throw new IllegalArgumentException("Invalid array type.");
            }
        } else {
            throw new IllegalArgumentException("Output type must be numeric.");
        }
    }

    public static LargeArray tan(LargeArray largeArray) {
        return tan(largeArray, largeArray.getType().isIntegerNumericType() ? LargeArrayType.FLOAT : largeArray.getType());
    }

    public static LargeArray tan(LargeArray largeArray, LargeArrayType largeArrayType) {
        LargeArray largeArray2 = largeArray;
        LargeArrayType largeArrayType2 = largeArrayType;
        if (largeArray2 == null || !largeArray.isNumeric()) {
            throw new IllegalArgumentException("a == null || !a.isNumeric()");
        } else if (largeArrayType.isNumericType()) {
            long length = largeArray.length();
            long j = 0;
            if (!largeArray.isConstant()) {
                int i = 0;
                LargeArray create = LargeArrayUtils.create(largeArrayType2, length, false);
                int min = (int) FastMath.min(length, (long) ConcurrencyUtils.getNumberOfThreads());
                if (largeArrayType.isIntegerNumericType() || largeArrayType.isRealNumericType()) {
                    if (min < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                        while (j < length) {
                            create.setDouble(j, FastMath.tan(largeArray2.getDouble(j)));
                            j++;
                        }
                    } else {
                        long j2 = length / ((long) min);
                        Future[] futureArr = new Future[min];
                        int i2 = 0;
                        while (i2 < min) {
                            final long j3 = ((long) i2) * j2;
                            final long j4 = i2 == min + -1 ? length : j3 + j2;
                            int i3 = i2;
                            final LargeArray largeArray3 = create;
                            Future[] futureArr2 = futureArr;
                            final LargeArray largeArray4 = largeArray;
                            futureArr2[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                                public void run() {
                                    for (long j = j3; j < j4; j++) {
                                        largeArray3.setDouble(j, FastMath.tan(largeArray4.getDouble(j)));
                                    }
                                }
                            });
                            i2 = i3 + 1;
                            futureArr = futureArr2;
                        }
                        try {
                            ConcurrencyUtils.waitForCompletion(futureArr);
                        } catch (InterruptedException | ExecutionException unused) {
                            while (j < length) {
                                create.setDouble(j, FastMath.tan(largeArray2.getDouble(j)));
                                j++;
                            }
                        }
                    }
                } else if (largeArrayType2 == LargeArrayType.COMPLEX_FLOAT) {
                    ComplexFloatLargeArray complexFloatLargeArray = (ComplexFloatLargeArray) largeArray2;
                    ComplexFloatLargeArray complexFloatLargeArray2 = (ComplexFloatLargeArray) create;
                    if (min < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                        while (j < length) {
                            float[] complexFloat = complexFloatLargeArray.getComplexFloat(j);
                            complexFloatLargeArray2.setComplexFloat(j, complexDiv(complexSin(complexFloat), complexCos(complexFloat)));
                            j++;
                        }
                    } else {
                        long j5 = length / ((long) min);
                        Future[] futureArr3 = new Future[min];
                        while (i < min) {
                            final long j6 = ((long) i) * j5;
                            final long j7 = i == min + -1 ? length : j6 + j5;
                            final ComplexFloatLargeArray complexFloatLargeArray3 = complexFloatLargeArray;
                            final ComplexFloatLargeArray complexFloatLargeArray4 = complexFloatLargeArray2;
                            futureArr3[i] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                                public void run() {
                                    for (long j = j6; j < j7; j++) {
                                        float[] complexFloat = complexFloatLargeArray3.getComplexFloat(j);
                                        complexFloatLargeArray4.setComplexFloat(j, LargeArrayArithmetics.complexDiv(LargeArrayArithmetics.complexSin(complexFloat), LargeArrayArithmetics.complexCos(complexFloat)));
                                    }
                                }
                            });
                            i++;
                        }
                        try {
                            ConcurrencyUtils.waitForCompletion(futureArr3);
                        } catch (InterruptedException | ExecutionException unused2) {
                            while (j < length) {
                                float[] complexFloat2 = complexFloatLargeArray.getComplexFloat(j);
                                complexFloatLargeArray2.setComplexFloat(j, complexDiv(complexSin(complexFloat2), complexCos(complexFloat2)));
                                j++;
                            }
                        }
                    }
                } else if (largeArrayType2 == LargeArrayType.COMPLEX_DOUBLE) {
                    ComplexDoubleLargeArray complexDoubleLargeArray = (ComplexDoubleLargeArray) largeArray2;
                    ComplexDoubleLargeArray complexDoubleLargeArray2 = (ComplexDoubleLargeArray) create;
                    if (min < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                        while (j < length) {
                            double[] complexDouble = complexDoubleLargeArray.getComplexDouble(j);
                            complexDoubleLargeArray2.setComplexDouble(j, complexDiv(complexSin(complexDouble), complexCos(complexDouble)));
                            j++;
                        }
                    } else {
                        long j8 = length / ((long) min);
                        Future[] futureArr4 = new Future[min];
                        while (i < min) {
                            final long j9 = ((long) i) * j8;
                            final long j10 = i == min + -1 ? length : j9 + j8;
                            final ComplexDoubleLargeArray complexDoubleLargeArray3 = complexDoubleLargeArray;
                            final ComplexDoubleLargeArray complexDoubleLargeArray4 = complexDoubleLargeArray2;
                            futureArr4[i] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                                public void run() {
                                    for (long j = j9; j < j10; j++) {
                                        double[] complexDouble = complexDoubleLargeArray3.getComplexDouble(j);
                                        complexDoubleLargeArray4.setComplexDouble(j, LargeArrayArithmetics.complexDiv(LargeArrayArithmetics.complexSin(complexDouble), LargeArrayArithmetics.complexCos(complexDouble)));
                                    }
                                }
                            });
                            i++;
                        }
                        try {
                            ConcurrencyUtils.waitForCompletion(futureArr4);
                        } catch (InterruptedException | ExecutionException unused3) {
                            while (j < length) {
                                double[] complexDouble2 = complexDoubleLargeArray.getComplexDouble(j);
                                complexDoubleLargeArray2.setComplexDouble(j, complexDiv(complexSin(complexDouble2), complexCos(complexDouble2)));
                                j++;
                            }
                        }
                    }
                } else {
                    throw new IllegalArgumentException("Invalid array type.");
                }
                return create;
            } else if (largeArrayType.isIntegerNumericType() || largeArrayType.isRealNumericType()) {
                return LargeArrayUtils.createConstant(largeArrayType2, length, Double.valueOf(FastMath.tan(largeArray2.getDouble(0))));
            } else {
                if (largeArrayType2 == LargeArrayType.COMPLEX_FLOAT) {
                    return LargeArrayUtils.createConstant(largeArrayType2, length, complexTan(((ComplexFloatLargeArray) largeArray2).getComplexFloat(0)));
                }
                if (largeArrayType2 == LargeArrayType.COMPLEX_DOUBLE) {
                    return LargeArrayUtils.createConstant(largeArrayType2, length, complexTan(((ComplexDoubleLargeArray) largeArray2).getComplexDouble(0)));
                }
                throw new IllegalArgumentException("Invalid array type.");
            }
        } else {
            throw new IllegalArgumentException("Output type must be numeric.");
        }
    }

    public static LargeArray asin(LargeArray largeArray) {
        return asin(largeArray, largeArray.getType().isIntegerNumericType() ? LargeArrayType.FLOAT : largeArray.getType());
    }

    public static LargeArray asin(LargeArray largeArray, LargeArrayType largeArrayType) {
        LargeArray largeArray2 = largeArray;
        LargeArrayType largeArrayType2 = largeArrayType;
        if (largeArray2 == null || !largeArray.isNumeric()) {
            throw new IllegalArgumentException("a == null || !a.isNumeric()");
        } else if (largeArrayType.isNumericType()) {
            long length = largeArray.length();
            long j = 0;
            if (!largeArray.isConstant()) {
                int i = 0;
                LargeArray create = LargeArrayUtils.create(largeArrayType2, length, false);
                int min = (int) FastMath.min(length, (long) ConcurrencyUtils.getNumberOfThreads());
                if (largeArrayType.isIntegerNumericType() || largeArrayType.isRealNumericType()) {
                    if (min < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                        while (j < length) {
                            create.setDouble(j, FastMath.asin(largeArray2.getDouble(j)));
                            j++;
                        }
                    } else {
                        long j2 = length / ((long) min);
                        Future[] futureArr = new Future[min];
                        int i2 = 0;
                        while (i2 < min) {
                            final long j3 = ((long) i2) * j2;
                            final long j4 = i2 == min + -1 ? length : j3 + j2;
                            int i3 = i2;
                            final LargeArray largeArray3 = create;
                            Future[] futureArr2 = futureArr;
                            final LargeArray largeArray4 = largeArray;
                            futureArr2[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                                public void run() {
                                    for (long j = j3; j < j4; j++) {
                                        largeArray3.setDouble(j, FastMath.asin(largeArray4.getDouble(j)));
                                    }
                                }
                            });
                            i2 = i3 + 1;
                            futureArr = futureArr2;
                        }
                        try {
                            ConcurrencyUtils.waitForCompletion(futureArr);
                        } catch (InterruptedException | ExecutionException unused) {
                            while (j < length) {
                                create.setDouble(j, FastMath.asin(largeArray2.getDouble(j)));
                                j++;
                            }
                        }
                    }
                } else if (largeArrayType2 == LargeArrayType.COMPLEX_FLOAT) {
                    ComplexFloatLargeArray complexFloatLargeArray = (ComplexFloatLargeArray) largeArray2;
                    ComplexFloatLargeArray complexFloatLargeArray2 = (ComplexFloatLargeArray) create;
                    if (min < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                        while (j < length) {
                            complexFloatLargeArray2.setComplexFloat(j, complexAsin(complexFloatLargeArray.getComplexFloat(j)));
                            j++;
                        }
                    } else {
                        long j5 = length / ((long) min);
                        Future[] futureArr3 = new Future[min];
                        while (i < min) {
                            final long j6 = ((long) i) * j5;
                            final long j7 = i == min + -1 ? length : j6 + j5;
                            final ComplexFloatLargeArray complexFloatLargeArray3 = complexFloatLargeArray;
                            final ComplexFloatLargeArray complexFloatLargeArray4 = complexFloatLargeArray2;
                            futureArr3[i] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                                public void run() {
                                    for (long j = j6; j < j7; j++) {
                                        complexFloatLargeArray4.setComplexFloat(j, LargeArrayArithmetics.complexAsin(complexFloatLargeArray3.getComplexFloat(j)));
                                    }
                                }
                            });
                            i++;
                        }
                        try {
                            ConcurrencyUtils.waitForCompletion(futureArr3);
                        } catch (InterruptedException | ExecutionException unused2) {
                            while (j < length) {
                                complexFloatLargeArray2.setComplexFloat(j, complexAsin(complexFloatLargeArray.getComplexFloat(j)));
                                j++;
                            }
                        }
                    }
                } else if (largeArrayType2 == LargeArrayType.COMPLEX_DOUBLE) {
                    ComplexDoubleLargeArray complexDoubleLargeArray = (ComplexDoubleLargeArray) largeArray2;
                    ComplexDoubleLargeArray complexDoubleLargeArray2 = (ComplexDoubleLargeArray) create;
                    if (min < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                        while (j < length) {
                            complexDoubleLargeArray2.setComplexDouble(j, complexAsin(complexDoubleLargeArray.getComplexDouble(j)));
                            j++;
                        }
                    } else {
                        long j8 = length / ((long) min);
                        Future[] futureArr4 = new Future[min];
                        while (i < min) {
                            final long j9 = ((long) i) * j8;
                            final long j10 = i == min + -1 ? length : j9 + j8;
                            final ComplexDoubleLargeArray complexDoubleLargeArray3 = complexDoubleLargeArray;
                            final ComplexDoubleLargeArray complexDoubleLargeArray4 = complexDoubleLargeArray2;
                            futureArr4[i] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                                public void run() {
                                    for (long j = j9; j < j10; j++) {
                                        complexDoubleLargeArray4.setComplexDouble(j, LargeArrayArithmetics.complexAsin(complexDoubleLargeArray3.getComplexDouble(j)));
                                    }
                                }
                            });
                            i++;
                        }
                        try {
                            ConcurrencyUtils.waitForCompletion(futureArr4);
                        } catch (InterruptedException | ExecutionException unused3) {
                            while (j < length) {
                                complexDoubleLargeArray2.setComplexDouble(j, complexAsin(complexDoubleLargeArray.getComplexDouble(j)));
                                j++;
                            }
                        }
                    }
                } else {
                    throw new IllegalArgumentException("Invalid array type.");
                }
                return create;
            } else if (largeArrayType.isIntegerNumericType() || largeArrayType.isRealNumericType()) {
                return LargeArrayUtils.createConstant(largeArrayType2, length, Double.valueOf(FastMath.asin(largeArray2.getDouble(0))));
            } else {
                if (largeArrayType2 == LargeArrayType.COMPLEX_FLOAT) {
                    return LargeArrayUtils.createConstant(largeArrayType2, length, complexAsin(((ComplexFloatLargeArray) largeArray2).getComplexFloat(0)));
                }
                if (largeArrayType2 == LargeArrayType.COMPLEX_DOUBLE) {
                    return LargeArrayUtils.createConstant(largeArrayType2, length, complexAsin(((ComplexDoubleLargeArray) largeArray2).getComplexDouble(0)));
                }
                throw new IllegalArgumentException("Invalid array type.");
            }
        } else {
            throw new IllegalArgumentException("Output type must be numeric.");
        }
    }

    public static LargeArray acos(LargeArray largeArray) {
        return acos(largeArray, largeArray.getType().isIntegerNumericType() ? LargeArrayType.FLOAT : largeArray.getType());
    }

    public static LargeArray acos(LargeArray largeArray, LargeArrayType largeArrayType) {
        LargeArray largeArray2 = largeArray;
        LargeArrayType largeArrayType2 = largeArrayType;
        if (largeArray2 == null || !largeArray.isNumeric()) {
            throw new IllegalArgumentException("a == null || !a.isNumeric()");
        } else if (largeArrayType.isNumericType()) {
            long length = largeArray.length();
            long j = 0;
            if (!largeArray.isConstant()) {
                int i = 0;
                LargeArray create = LargeArrayUtils.create(largeArrayType2, length, false);
                int min = (int) FastMath.min(length, (long) ConcurrencyUtils.getNumberOfThreads());
                if (largeArrayType.isIntegerNumericType() || largeArrayType.isRealNumericType()) {
                    if (min < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                        while (j < length) {
                            create.setDouble(j, FastMath.acos(largeArray2.getDouble(j)));
                            j++;
                        }
                    } else {
                        long j2 = length / ((long) min);
                        Future[] futureArr = new Future[min];
                        int i2 = 0;
                        while (i2 < min) {
                            final long j3 = ((long) i2) * j2;
                            final long j4 = i2 == min + -1 ? length : j3 + j2;
                            int i3 = i2;
                            final LargeArray largeArray3 = create;
                            Future[] futureArr2 = futureArr;
                            final LargeArray largeArray4 = largeArray;
                            futureArr2[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                                public void run() {
                                    for (long j = j3; j < j4; j++) {
                                        largeArray3.setDouble(j, FastMath.acos(largeArray4.getDouble(j)));
                                    }
                                }
                            });
                            i2 = i3 + 1;
                            futureArr = futureArr2;
                        }
                        try {
                            ConcurrencyUtils.waitForCompletion(futureArr);
                        } catch (InterruptedException | ExecutionException unused) {
                            while (j < length) {
                                create.setDouble(j, FastMath.acos(largeArray2.getDouble(j)));
                                j++;
                            }
                        }
                    }
                } else if (largeArrayType2 == LargeArrayType.COMPLEX_FLOAT) {
                    ComplexFloatLargeArray complexFloatLargeArray = (ComplexFloatLargeArray) largeArray2;
                    ComplexFloatLargeArray complexFloatLargeArray2 = (ComplexFloatLargeArray) create;
                    if (min < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                        while (j < length) {
                            complexFloatLargeArray2.setComplexFloat(j, complexAcos(complexFloatLargeArray.getComplexFloat(j)));
                            j++;
                        }
                    } else {
                        long j5 = length / ((long) min);
                        Future[] futureArr3 = new Future[min];
                        while (i < min) {
                            final long j6 = ((long) i) * j5;
                            final long j7 = i == min + -1 ? length : j6 + j5;
                            final ComplexFloatLargeArray complexFloatLargeArray3 = complexFloatLargeArray;
                            final ComplexFloatLargeArray complexFloatLargeArray4 = complexFloatLargeArray2;
                            futureArr3[i] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                                public void run() {
                                    for (long j = j6; j < j7; j++) {
                                        complexFloatLargeArray4.setComplexFloat(j, LargeArrayArithmetics.complexAcos(complexFloatLargeArray3.getComplexFloat(j)));
                                    }
                                }
                            });
                            i++;
                        }
                        try {
                            ConcurrencyUtils.waitForCompletion(futureArr3);
                        } catch (InterruptedException | ExecutionException unused2) {
                            while (j < length) {
                                complexFloatLargeArray2.setComplexFloat(j, complexAcos(complexFloatLargeArray.getComplexFloat(j)));
                                j++;
                            }
                        }
                    }
                } else if (largeArrayType2 == LargeArrayType.COMPLEX_DOUBLE) {
                    ComplexDoubleLargeArray complexDoubleLargeArray = (ComplexDoubleLargeArray) largeArray2;
                    ComplexDoubleLargeArray complexDoubleLargeArray2 = (ComplexDoubleLargeArray) create;
                    if (min < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                        while (j < length) {
                            complexDoubleLargeArray2.setComplexDouble(j, complexAcos(complexDoubleLargeArray.getComplexDouble(j)));
                            j++;
                        }
                    } else {
                        long j8 = length / ((long) min);
                        Future[] futureArr4 = new Future[min];
                        while (i < min) {
                            final long j9 = ((long) i) * j8;
                            final long j10 = i == min + -1 ? length : j9 + j8;
                            final ComplexDoubleLargeArray complexDoubleLargeArray3 = complexDoubleLargeArray;
                            final ComplexDoubleLargeArray complexDoubleLargeArray4 = complexDoubleLargeArray2;
                            futureArr4[i] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                                public void run() {
                                    for (long j = j9; j < j10; j++) {
                                        complexDoubleLargeArray4.setComplexDouble(j, LargeArrayArithmetics.complexAcos(complexDoubleLargeArray3.getComplexDouble(j)));
                                    }
                                }
                            });
                            i++;
                        }
                        try {
                            ConcurrencyUtils.waitForCompletion(futureArr4);
                        } catch (InterruptedException | ExecutionException unused3) {
                            while (j < length) {
                                complexDoubleLargeArray2.setComplexDouble(j, complexAcos(complexDoubleLargeArray.getComplexDouble(j)));
                                j++;
                            }
                        }
                    }
                } else {
                    throw new IllegalArgumentException("Invalid array type.");
                }
                return create;
            } else if (largeArrayType.isIntegerNumericType() || largeArrayType.isRealNumericType()) {
                return LargeArrayUtils.createConstant(largeArrayType2, length, Double.valueOf(FastMath.acos(largeArray2.getDouble(0))));
            } else {
                if (largeArrayType2 == LargeArrayType.COMPLEX_FLOAT) {
                    return LargeArrayUtils.createConstant(largeArrayType2, length, complexAcos(((ComplexFloatLargeArray) largeArray2).getComplexFloat(0)));
                }
                if (largeArrayType2 == LargeArrayType.COMPLEX_DOUBLE) {
                    return LargeArrayUtils.createConstant(largeArrayType2, length, complexAcos(((ComplexDoubleLargeArray) largeArray2).getComplexDouble(0)));
                }
                throw new IllegalArgumentException("Invalid array type.");
            }
        } else {
            throw new IllegalArgumentException("Output type must be numeric.");
        }
    }

    public static LargeArray atan(LargeArray largeArray) {
        return atan(largeArray, largeArray.getType().isIntegerNumericType() ? LargeArrayType.FLOAT : largeArray.getType());
    }

    public static LargeArray atan(LargeArray largeArray, LargeArrayType largeArrayType) {
        LargeArray largeArray2 = largeArray;
        LargeArrayType largeArrayType2 = largeArrayType;
        if (largeArray2 == null || !largeArray.isNumeric()) {
            throw new IllegalArgumentException("a == null || !a.isNumeric()");
        } else if (largeArrayType.isNumericType()) {
            long length = largeArray.length();
            long j = 0;
            if (!largeArray.isConstant()) {
                int i = 0;
                LargeArray create = LargeArrayUtils.create(largeArrayType2, length, false);
                int min = (int) FastMath.min(length, (long) ConcurrencyUtils.getNumberOfThreads());
                if (largeArrayType.isIntegerNumericType() || largeArrayType.isRealNumericType()) {
                    if (min < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                        while (j < length) {
                            create.setDouble(j, FastMath.atan(largeArray2.getDouble(j)));
                            j++;
                        }
                    } else {
                        long j2 = length / ((long) min);
                        Future[] futureArr = new Future[min];
                        int i2 = 0;
                        while (i2 < min) {
                            final long j3 = ((long) i2) * j2;
                            final long j4 = i2 == min + -1 ? length : j3 + j2;
                            int i3 = i2;
                            final LargeArray largeArray3 = create;
                            Future[] futureArr2 = futureArr;
                            final LargeArray largeArray4 = largeArray;
                            futureArr2[i3] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                                public void run() {
                                    for (long j = j3; j < j4; j++) {
                                        largeArray3.setDouble(j, FastMath.atan(largeArray4.getDouble(j)));
                                    }
                                }
                            });
                            i2 = i3 + 1;
                            futureArr = futureArr2;
                        }
                        try {
                            ConcurrencyUtils.waitForCompletion(futureArr);
                        } catch (InterruptedException | ExecutionException unused) {
                            while (j < length) {
                                create.setDouble(j, FastMath.atan(largeArray2.getDouble(j)));
                                j++;
                            }
                        }
                    }
                } else if (largeArrayType2 == LargeArrayType.COMPLEX_FLOAT) {
                    ComplexFloatLargeArray complexFloatLargeArray = (ComplexFloatLargeArray) largeArray2;
                    ComplexFloatLargeArray complexFloatLargeArray2 = (ComplexFloatLargeArray) create;
                    if (min < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                        while (j < length) {
                            complexFloatLargeArray2.setComplexFloat(j, complexAtan(complexFloatLargeArray.getComplexFloat(j)));
                            j++;
                        }
                    } else {
                        long j5 = length / ((long) min);
                        Future[] futureArr3 = new Future[min];
                        while (i < min) {
                            final long j6 = ((long) i) * j5;
                            final long j7 = i == min + -1 ? length : j6 + j5;
                            final ComplexFloatLargeArray complexFloatLargeArray3 = complexFloatLargeArray;
                            final ComplexFloatLargeArray complexFloatLargeArray4 = complexFloatLargeArray2;
                            futureArr3[i] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                                public void run() {
                                    for (long j = j6; j < j7; j++) {
                                        complexFloatLargeArray4.setComplexFloat(j, LargeArrayArithmetics.complexAtan(complexFloatLargeArray3.getComplexFloat(j)));
                                    }
                                }
                            });
                            i++;
                        }
                        try {
                            ConcurrencyUtils.waitForCompletion(futureArr3);
                        } catch (InterruptedException | ExecutionException unused2) {
                            while (j < length) {
                                complexFloatLargeArray2.setComplexFloat(j, complexAtan(complexFloatLargeArray.getComplexFloat(j)));
                                j++;
                            }
                        }
                    }
                } else if (largeArrayType2 == LargeArrayType.COMPLEX_DOUBLE) {
                    ComplexDoubleLargeArray complexDoubleLargeArray = (ComplexDoubleLargeArray) largeArray2;
                    ComplexDoubleLargeArray complexDoubleLargeArray2 = (ComplexDoubleLargeArray) create;
                    if (min < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                        while (j < length) {
                            complexDoubleLargeArray2.setComplexDouble(j, complexAtan(complexDoubleLargeArray.getComplexDouble(j)));
                            j++;
                        }
                    } else {
                        long j8 = length / ((long) min);
                        Future[] futureArr4 = new Future[min];
                        while (i < min) {
                            final long j9 = ((long) i) * j8;
                            final long j10 = i == min + -1 ? length : j9 + j8;
                            final ComplexDoubleLargeArray complexDoubleLargeArray3 = complexDoubleLargeArray;
                            final ComplexDoubleLargeArray complexDoubleLargeArray4 = complexDoubleLargeArray2;
                            futureArr4[i] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                                public void run() {
                                    for (long j = j9; j < j10; j++) {
                                        complexDoubleLargeArray4.setComplexDouble(j, LargeArrayArithmetics.complexAtan(complexDoubleLargeArray3.getComplexDouble(j)));
                                    }
                                }
                            });
                            i++;
                        }
                        try {
                            ConcurrencyUtils.waitForCompletion(futureArr4);
                        } catch (InterruptedException | ExecutionException unused3) {
                            while (j < length) {
                                complexDoubleLargeArray2.setComplexDouble(j, complexAtan(complexDoubleLargeArray.getComplexDouble(j)));
                                j++;
                            }
                        }
                    }
                } else {
                    throw new IllegalArgumentException("Invalid array type.");
                }
                return create;
            } else if (largeArrayType.isIntegerNumericType() || largeArrayType.isRealNumericType()) {
                return LargeArrayUtils.createConstant(largeArrayType2, length, Double.valueOf(FastMath.atan(largeArray2.getDouble(0))));
            } else {
                if (largeArrayType2 == LargeArrayType.COMPLEX_FLOAT) {
                    return LargeArrayUtils.createConstant(largeArrayType2, length, complexAtan(((ComplexFloatLargeArray) largeArray2).getComplexFloat(0)));
                }
                if (largeArrayType2 == LargeArrayType.COMPLEX_DOUBLE) {
                    return LargeArrayUtils.createConstant(largeArrayType2, length, complexAtan(((ComplexDoubleLargeArray) largeArray2).getComplexDouble(0)));
                }
                throw new IllegalArgumentException("Invalid array type.");
            }
        } else {
            throw new IllegalArgumentException("Output type must be numeric.");
        }
    }

    public static LargeArray signum(LargeArray largeArray) {
        return signum(largeArray, LargeArrayType.BYTE);
    }

    public static LargeArray signum(LargeArray largeArray, LargeArrayType largeArrayType) {
        LargeArray largeArray2 = largeArray;
        LargeArrayType largeArrayType2 = largeArrayType;
        if (largeArray2 == null || !largeArray.isNumeric() || largeArray.getType() == LargeArrayType.COMPLEX_FLOAT || largeArray.getType() == LargeArrayType.COMPLEX_DOUBLE) {
            throw new IllegalArgumentException("a == null || !a.isNumeric() || a.getType() == LargeArrayType.COMPLEX_FLOAT || a.getType() == LargeArrayType.COMPLEX_DOUBLE");
        } else if (largeArrayType.isNumericType()) {
            long length = largeArray.length();
            long j = 0;
            if (largeArray.isConstant()) {
                return LargeArrayUtils.createConstant(largeArrayType2, length, Byte.valueOf((byte) ((int) FastMath.signum(largeArray2.getDouble(0)))));
            }
            LargeArray create = LargeArrayUtils.create(largeArrayType2, length, false);
            int min = (int) FastMath.min(length, (long) ConcurrencyUtils.getNumberOfThreads());
            if (min < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                while (j < length) {
                    create.setByte(j, (byte) ((int) FastMath.signum(largeArray2.getDouble(j))));
                    j++;
                }
            } else {
                long j2 = length / ((long) min);
                Future[] futureArr = new Future[min];
                int i = 0;
                while (i < min) {
                    final long j3 = ((long) i) * j2;
                    final long j4 = i == min + -1 ? length : j3 + j2;
                    int i2 = i;
                    final LargeArray largeArray3 = create;
                    Future[] futureArr2 = futureArr;
                    final LargeArray largeArray4 = largeArray;
                    futureArr2[i2] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                        public void run() {
                            for (long j = j3; j < j4; j++) {
                                largeArray3.setByte(j, (byte) ((int) FastMath.signum(largeArray4.getDouble(j))));
                            }
                        }
                    });
                    i = i2 + 1;
                    futureArr = futureArr2;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr);
                } catch (InterruptedException | ExecutionException unused) {
                    while (j < length) {
                        create.setByte(j, (byte) ((int) FastMath.signum(largeArray2.getDouble(j))));
                        j++;
                    }
                }
            }
            return create;
        } else {
            throw new IllegalArgumentException("Output type must be numeric.");
        }
    }
}
