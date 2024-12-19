package pl.edu.icm.jlargearrays;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.apache.commons.math3.util.FastMath;

public class LargeArrayStatistics {
    private LargeArrayStatistics() {
    }

    public static double min(LargeArray largeArray) {
        LargeArray largeArray2 = largeArray;
        if (largeArray2 == null || !largeArray.isNumeric() || largeArray.getType() == LargeArrayType.COMPLEX_FLOAT || largeArray.getType() == LargeArrayType.COMPLEX_DOUBLE) {
            throw new IllegalArgumentException("a == null || !a.isNumeric() || a.getType() == LargeArrayType.COMPLEX_FLOAT || a.getType() == LargeArrayType.COMPLEX_DOUBLE");
        } else if (largeArray.isConstant()) {
            return largeArray2.getDouble(0);
        } else {
            double d = largeArray2.getDouble(0);
            long length = largeArray.length();
            int min = (int) FastMath.min(length, (long) ConcurrencyUtils.getNumberOfThreads());
            if (min < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                for (long j = 1; j < length; j++) {
                    double d2 = largeArray2.getDouble(j);
                    if (d2 < d) {
                        d = d2;
                    }
                }
            } else {
                long j2 = length / ((long) min);
                Future[] futureArr = new Future[min];
                int i = 0;
                while (i < min) {
                    final long j3 = ((long) i) * j2;
                    final LargeArray largeArray3 = largeArray;
                    Future[] futureArr2 = futureArr;
                    int i2 = i;
                    final long j4 = i == min + -1 ? length : j3 + j2;
                    futureArr2[i2] = ConcurrencyUtils.submit(new Callable<Double>() {
                        public Double call() {
                            double d = largeArray3.getDouble(j3);
                            long j = j3;
                            while (true) {
                                j++;
                                if (j >= j4) {
                                    return Double.valueOf(d);
                                }
                                double d2 = largeArray3.getDouble(j);
                                if (d2 < d) {
                                    d = d2;
                                }
                            }
                        }
                    });
                    i = i2 + 1;
                    futureArr = futureArr2;
                }
                Future[] futureArr3 = futureArr;
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                    for (int i3 = 0; i3 < min; i3++) {
                        double doubleValue = ((Double) futureArr3[i3].get()).doubleValue();
                        if (doubleValue < d) {
                            d = doubleValue;
                        }
                    }
                } catch (InterruptedException | ExecutionException unused) {
                    for (long j5 = 1; j5 < length; j5++) {
                        double d3 = largeArray2.getDouble(j5);
                        if (d3 < d) {
                            d = d3;
                        }
                    }
                }
            }
            return d;
        }
    }

    public static double max(LargeArray largeArray) {
        LargeArray largeArray2 = largeArray;
        if (largeArray2 == null || !largeArray.isNumeric() || largeArray.getType() == LargeArrayType.COMPLEX_FLOAT || largeArray.getType() == LargeArrayType.COMPLEX_DOUBLE) {
            throw new IllegalArgumentException("a == null || !a.isNumeric() || a.getType() == LargeArrayType.COMPLEX_FLOAT || a.getType() == LargeArrayType.COMPLEX_DOUBLE");
        } else if (largeArray.isConstant()) {
            return largeArray2.getDouble(0);
        } else {
            double d = largeArray2.getDouble(0);
            long length = largeArray.length();
            int min = (int) FastMath.min(length, (long) ConcurrencyUtils.getNumberOfThreads());
            if (min < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                for (long j = 1; j < length; j++) {
                    double d2 = largeArray2.getDouble(j);
                    if (d2 > d) {
                        d = d2;
                    }
                }
            } else {
                long j2 = length / ((long) min);
                Future[] futureArr = new Future[min];
                int i = 0;
                while (i < min) {
                    final long j3 = ((long) i) * j2;
                    final LargeArray largeArray3 = largeArray;
                    Future[] futureArr2 = futureArr;
                    int i2 = i;
                    final long j4 = i == min + -1 ? length : j3 + j2;
                    futureArr2[i2] = ConcurrencyUtils.submit(new Callable<Double>() {
                        public Double call() {
                            double d = largeArray3.getDouble(j3);
                            long j = j3;
                            while (true) {
                                j++;
                                if (j >= j4) {
                                    return Double.valueOf(d);
                                }
                                double d2 = largeArray3.getDouble(j);
                                if (d2 > d) {
                                    d = d2;
                                }
                            }
                        }
                    });
                    i = i2 + 1;
                    futureArr = futureArr2;
                }
                Future[] futureArr3 = futureArr;
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                    for (int i3 = 0; i3 < min; i3++) {
                        double doubleValue = ((Double) futureArr3[i3].get()).doubleValue();
                        if (doubleValue > d) {
                            d = doubleValue;
                        }
                    }
                } catch (InterruptedException | ExecutionException unused) {
                    for (long j5 = 1; j5 < length; j5++) {
                        double d3 = largeArray2.getDouble(j5);
                        if (d3 > d) {
                            d = d3;
                        }
                    }
                }
            }
            return d;
        }
    }

    public static double sum(LargeArray largeArray) {
        LargeArray largeArray2 = largeArray;
        if (largeArray2 == null || !largeArray.isNumeric() || largeArray.getType() == LargeArrayType.COMPLEX_FLOAT || largeArray.getType() == LargeArrayType.COMPLEX_DOUBLE) {
            throw new IllegalArgumentException("a == null || !a.isNumeric() || a.getType() == LargeArrayType.COMPLEX_FLOAT || a.getType() == LargeArrayType.COMPLEX_DOUBLE");
        }
        long j = 0;
        if (largeArray.isConstant()) {
            return ((double) largeArray.length()) * largeArray2.getDouble(0);
        }
        long length = largeArray.length();
        int min = (int) FastMath.min(length, (long) ConcurrencyUtils.getNumberOfThreads());
        double d = 0.0d;
        if (min < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
            while (j < length) {
                d += largeArray2.getDouble(j);
                j++;
            }
        } else {
            long j2 = length / ((long) min);
            Future[] futureArr = new Future[min];
            int i = 0;
            while (i < min) {
                final long j3 = ((long) i) * j2;
                int i2 = i;
                final long j4 = i == min + -1 ? length : j3 + j2;
                Future[] futureArr2 = futureArr;
                final LargeArray largeArray3 = largeArray;
                futureArr2[i2] = ConcurrencyUtils.submit(new Callable<Double>() {
                    public Double call() {
                        double d = 0.0d;
                        for (long j = j3; j < j4; j++) {
                            d += largeArray3.getDouble(j);
                        }
                        return Double.valueOf(d);
                    }
                });
                i = i2 + 1;
                futureArr = futureArr2;
            }
            Future[] futureArr3 = futureArr;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
                for (int i3 = 0; i3 < min; i3++) {
                    d += ((Double) futureArr3[i3].get()).doubleValue();
                }
            } catch (InterruptedException | ExecutionException unused) {
                while (j < length) {
                    d += largeArray2.getDouble(j);
                    j++;
                }
            }
        }
        return d;
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x009c A[LOOP:2: B:33:0x0098->B:36:0x009c, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static double sumKahan(pl.edu.icm.jlargearrays.LargeArray r25) {
        /*
            r6 = r25
            if (r6 == 0) goto L_0x00c5
            boolean r0 = r25.isNumeric()
            if (r0 == 0) goto L_0x00c5
            pl.edu.icm.jlargearrays.LargeArrayType r0 = r25.getType()
            pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.COMPLEX_FLOAT
            if (r0 == r1) goto L_0x00c5
            pl.edu.icm.jlargearrays.LargeArrayType r0 = r25.getType()
            pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.COMPLEX_DOUBLE
            if (r0 == r1) goto L_0x00c5
            boolean r0 = r25.isConstant()
            r7 = 0
            if (r0 == 0) goto L_0x002d
            long r0 = r25.length()
            double r0 = (double) r0
            double r2 = r6.getDouble(r7)
            double r0 = r0 * r2
            return r0
        L_0x002d:
            long r9 = r25.length()
            int r0 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getNumberOfThreads()
            long r0 = (long) r0
            long r0 = org.apache.commons.math3.util.FastMath.min((long) r9, (long) r0)
            int r11 = (int) r0
            r0 = 2
            r12 = 1
            r14 = 0
            if (r11 < r0) goto L_0x00ad
            long r0 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getConcurrentThreshold()
            int r0 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r0 >= 0) goto L_0x004c
            goto L_0x00ad
        L_0x004c:
            long r0 = (long) r11
            long r16 = r9 / r0
            java.util.concurrent.Future[] r5 = new java.util.concurrent.Future[r11]
            r18 = 0
            r3 = r18
        L_0x0055:
            if (r3 >= r11) goto L_0x007d
            long r0 = (long) r3
            long r1 = r0 * r16
            int r0 = r11 + -1
            if (r3 != r0) goto L_0x0061
            r19 = r9
            goto L_0x0063
        L_0x0061:
            long r19 = r1 + r16
        L_0x0063:
            pl.edu.icm.jlargearrays.LargeArrayStatistics$4 r21 = new pl.edu.icm.jlargearrays.LargeArrayStatistics$4
            r0 = r21
            r22 = r3
            r3 = r19
            r19 = r5
            r5 = r25
            r0.<init>(r1, r3, r5)
            java.util.concurrent.Future r0 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit(r21)
            r19[r22] = r0
            int r3 = r22 + 1
            r5 = r19
            goto L_0x0055
        L_0x007d:
            r19 = r5
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r19)     // Catch:{ InterruptedException | ExecutionException -> 0x0097 }
            r1 = r14
            r0 = r18
        L_0x0085:
            if (r0 >= r11) goto L_0x00c4
            r3 = r19[r0]     // Catch:{ InterruptedException | ExecutionException -> 0x0098 }
            java.lang.Object r3 = r3.get()     // Catch:{ InterruptedException | ExecutionException -> 0x0098 }
            java.lang.Double r3 = (java.lang.Double) r3     // Catch:{ InterruptedException | ExecutionException -> 0x0098 }
            double r3 = r3.doubleValue()     // Catch:{ InterruptedException | ExecutionException -> 0x0098 }
            double r1 = r1 + r3
            int r0 = r0 + 1
            goto L_0x0085
        L_0x0097:
            r1 = r14
        L_0x0098:
            int r0 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r0 >= 0) goto L_0x00c4
            double r3 = r6.getDouble(r7)
            double r3 = r3 - r14
            double r14 = r1 + r3
            double r0 = r14 - r1
            double r0 = r0 - r3
            long r7 = r7 + r12
            r23 = r0
            r1 = r14
            r14 = r23
            goto L_0x0098
        L_0x00ad:
            r0 = r14
        L_0x00ae:
            int r2 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r2 >= 0) goto L_0x00c3
            double r2 = r6.getDouble(r7)
            double r2 = r2 - r14
            double r14 = r0 + r2
            double r0 = r14 - r0
            double r0 = r0 - r2
            long r7 = r7 + r12
            r23 = r0
            r0 = r14
            r14 = r23
            goto L_0x00ae
        L_0x00c3:
            r1 = r0
        L_0x00c4:
            return r1
        L_0x00c5:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "a == null || !a.isNumeric() || a.getType() == LargeArrayType.COMPLEX_FLOAT || a.getType() == LargeArrayType.COMPLEX_DOUBLE"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: pl.edu.icm.jlargearrays.LargeArrayStatistics.sumKahan(pl.edu.icm.jlargearrays.LargeArray):double");
    }

    public static double avg(LargeArray largeArray) {
        LargeArray largeArray2 = largeArray;
        if (largeArray2 == null || !largeArray.isNumeric() || largeArray.getType() == LargeArrayType.COMPLEX_FLOAT || largeArray.getType() == LargeArrayType.COMPLEX_DOUBLE) {
            throw new IllegalArgumentException("a == null || !a.isNumeric() || a.getType() == LargeArrayType.COMPLEX_FLOAT || a.getType() == LargeArrayType.COMPLEX_DOUBLE");
        }
        long j = 0;
        if (largeArray.isConstant()) {
            return largeArray2.getDouble(0);
        }
        long length = largeArray.length();
        int min = (int) FastMath.min(length, (long) ConcurrencyUtils.getNumberOfThreads());
        double d = 0.0d;
        if (min < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
            while (j < length) {
                d += largeArray2.getDouble(j);
                j++;
            }
        } else {
            long j2 = length / ((long) min);
            Future[] futureArr = new Future[min];
            int i = 0;
            while (i < min) {
                final long j3 = ((long) i) * j2;
                int i2 = i;
                final long j4 = i == min + -1 ? length : j3 + j2;
                Future[] futureArr2 = futureArr;
                final LargeArray largeArray3 = largeArray;
                futureArr2[i2] = ConcurrencyUtils.submit(new Callable<Double>() {
                    public Double call() {
                        double d = 0.0d;
                        for (long j = j3; j < j4; j++) {
                            d += largeArray3.getDouble(j);
                        }
                        return Double.valueOf(d);
                    }
                });
                i = i2 + 1;
                futureArr = futureArr2;
            }
            Future[] futureArr3 = futureArr;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
                for (int i3 = 0; i3 < min; i3++) {
                    d += ((Double) futureArr3[i3].get()).doubleValue();
                }
            } catch (InterruptedException | ExecutionException unused) {
                while (j < length) {
                    d += largeArray2.getDouble(j);
                    j++;
                }
            }
        }
        return d / ((double) length);
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x0096 A[LOOP:2: B:33:0x0092->B:36:0x0096, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static double avgKahan(pl.edu.icm.jlargearrays.LargeArray r25) {
        /*
            r6 = r25
            if (r6 == 0) goto L_0x00c1
            boolean r0 = r25.isNumeric()
            if (r0 == 0) goto L_0x00c1
            pl.edu.icm.jlargearrays.LargeArrayType r0 = r25.getType()
            pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.COMPLEX_FLOAT
            if (r0 == r1) goto L_0x00c1
            pl.edu.icm.jlargearrays.LargeArrayType r0 = r25.getType()
            pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.COMPLEX_DOUBLE
            if (r0 == r1) goto L_0x00c1
            boolean r0 = r25.isConstant()
            r7 = 0
            if (r0 == 0) goto L_0x0027
            double r0 = r6.getDouble(r7)
            return r0
        L_0x0027:
            long r9 = r25.length()
            int r0 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getNumberOfThreads()
            long r0 = (long) r0
            long r0 = org.apache.commons.math3.util.FastMath.min((long) r9, (long) r0)
            int r11 = (int) r0
            r0 = 2
            r12 = 1
            r14 = 0
            if (r11 < r0) goto L_0x00a7
            long r0 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getConcurrentThreshold()
            int r0 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r0 >= 0) goto L_0x0046
            goto L_0x00a7
        L_0x0046:
            long r0 = (long) r11
            long r16 = r9 / r0
            java.util.concurrent.Future[] r5 = new java.util.concurrent.Future[r11]
            r18 = 0
            r3 = r18
        L_0x004f:
            if (r3 >= r11) goto L_0x0077
            long r0 = (long) r3
            long r1 = r0 * r16
            int r0 = r11 + -1
            if (r3 != r0) goto L_0x005b
            r19 = r9
            goto L_0x005d
        L_0x005b:
            long r19 = r1 + r16
        L_0x005d:
            pl.edu.icm.jlargearrays.LargeArrayStatistics$6 r21 = new pl.edu.icm.jlargearrays.LargeArrayStatistics$6
            r0 = r21
            r22 = r3
            r3 = r19
            r19 = r5
            r5 = r25
            r0.<init>(r1, r3, r5)
            java.util.concurrent.Future r0 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit(r21)
            r19[r22] = r0
            int r3 = r22 + 1
            r5 = r19
            goto L_0x004f
        L_0x0077:
            r19 = r5
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r19)     // Catch:{ InterruptedException | ExecutionException -> 0x0091 }
            r1 = r14
            r0 = r18
        L_0x007f:
            if (r0 >= r11) goto L_0x00be
            r3 = r19[r0]     // Catch:{ InterruptedException | ExecutionException -> 0x0092 }
            java.lang.Object r3 = r3.get()     // Catch:{ InterruptedException | ExecutionException -> 0x0092 }
            java.lang.Double r3 = (java.lang.Double) r3     // Catch:{ InterruptedException | ExecutionException -> 0x0092 }
            double r3 = r3.doubleValue()     // Catch:{ InterruptedException | ExecutionException -> 0x0092 }
            double r1 = r1 + r3
            int r0 = r0 + 1
            goto L_0x007f
        L_0x0091:
            r1 = r14
        L_0x0092:
            int r0 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r0 >= 0) goto L_0x00be
            double r3 = r6.getDouble(r7)
            double r3 = r3 - r14
            double r14 = r1 + r3
            double r0 = r14 - r1
            double r0 = r0 - r3
            long r7 = r7 + r12
            r23 = r0
            r1 = r14
            r14 = r23
            goto L_0x0092
        L_0x00a7:
            r0 = r14
        L_0x00a8:
            int r2 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r2 >= 0) goto L_0x00bd
            double r2 = r6.getDouble(r7)
            double r2 = r2 - r14
            double r14 = r0 + r2
            double r0 = r14 - r0
            double r0 = r0 - r2
            long r7 = r7 + r12
            r23 = r0
            r0 = r14
            r14 = r23
            goto L_0x00a8
        L_0x00bd:
            r1 = r0
        L_0x00be:
            double r3 = (double) r9
            double r1 = r1 / r3
            return r1
        L_0x00c1:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "a == null || !a.isNumeric() || a.getType() == LargeArrayType.COMPLEX_FLOAT || a.getType() == LargeArrayType.COMPLEX_DOUBLE"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: pl.edu.icm.jlargearrays.LargeArrayStatistics.avgKahan(pl.edu.icm.jlargearrays.LargeArray):double");
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x00a8 A[LOOP:2: B:37:0x00a4->B:39:0x00a8, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static double std(pl.edu.icm.jlargearrays.LargeArray r24) {
        /*
            r6 = r24
            if (r6 == 0) goto L_0x00f6
            boolean r0 = r24.isNumeric()
            if (r0 == 0) goto L_0x00f6
            pl.edu.icm.jlargearrays.LargeArrayType r0 = r24.getType()
            pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.COMPLEX_FLOAT
            if (r0 == r1) goto L_0x00f6
            pl.edu.icm.jlargearrays.LargeArrayType r0 = r24.getType()
            pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.COMPLEX_DOUBLE
            if (r0 == r1) goto L_0x00f6
            boolean r0 = r24.isConstant()
            r7 = 0
            if (r0 == 0) goto L_0x0023
            return r7
        L_0x0023:
            long r9 = r24.length()
            r0 = 2
            int r0 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r0 >= 0) goto L_0x0030
            r0 = 9221120237041090560(0x7ff8000000000000, double:NaN)
            return r0
        L_0x0030:
            int r0 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getNumberOfThreads()
            long r0 = (long) r0
            long r0 = org.apache.commons.math3.util.FastMath.min((long) r9, (long) r0)
            int r11 = (int) r0
            r0 = 2
            r12 = 1
            r14 = 0
            if (r11 < r0) goto L_0x00c3
            long r0 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getConcurrentThreshold()
            int r0 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r0 >= 0) goto L_0x004b
            goto L_0x00c3
        L_0x004b:
            long r0 = (long) r11
            long r16 = r9 / r0
            java.util.concurrent.Future[] r5 = new java.util.concurrent.Future[r11]
            r18 = 0
            r3 = r18
        L_0x0054:
            if (r3 >= r11) goto L_0x007c
            long r0 = (long) r3
            long r1 = r0 * r16
            int r0 = r11 + -1
            if (r3 != r0) goto L_0x0060
            r19 = r9
            goto L_0x0062
        L_0x0060:
            long r19 = r1 + r16
        L_0x0062:
            pl.edu.icm.jlargearrays.LargeArrayStatistics$7 r21 = new pl.edu.icm.jlargearrays.LargeArrayStatistics$7
            r0 = r21
            r22 = r3
            r3 = r19
            r19 = r5
            r5 = r24
            r0.<init>(r1, r3, r5)
            java.util.concurrent.Future r0 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit(r21)
            r19[r22] = r0
            int r3 = r22 + 1
            r5 = r19
            goto L_0x0054
        L_0x007c:
            r19 = r5
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r19)     // Catch:{ InterruptedException | ExecutionException -> 0x009e }
            r1 = r7
            r3 = r1
            r0 = r18
        L_0x0085:
            if (r0 >= r11) goto L_0x00e8
            r5 = r19[r0]     // Catch:{ InterruptedException | ExecutionException -> 0x00a0 }
            java.lang.Object r5 = r5.get()     // Catch:{ InterruptedException | ExecutionException -> 0x00a0 }
            double[] r5 = (double[]) r5     // Catch:{ InterruptedException | ExecutionException -> 0x00a0 }
            double[] r5 = (double[]) r5     // Catch:{ InterruptedException | ExecutionException -> 0x00a0 }
            r16 = r5[r18]     // Catch:{ InterruptedException | ExecutionException -> 0x00a0 }
            double r1 = r1 + r16
            r16 = 1
            r16 = r5[r16]     // Catch:{ InterruptedException | ExecutionException -> 0x00a0 }
            double r3 = r3 + r16
            int r0 = r0 + 1
            goto L_0x0085
        L_0x009e:
            r1 = r7
            r3 = r1
        L_0x00a0:
            r16 = r7
            r18 = r16
        L_0x00a4:
            int r0 = (r14 > r9 ? 1 : (r14 == r9 ? 0 : -1))
            if (r0 >= 0) goto L_0x00e8
            double r20 = r6.getDouble(r14)
            double r16 = r20 - r16
            double r22 = r1 + r16
            double r0 = r22 - r1
            double r16 = r0 - r16
            double r20 = r20 * r20
            double r20 = r20 - r18
            double r0 = r3 + r20
            double r2 = r0 - r3
            double r18 = r2 - r20
            long r14 = r14 + r12
            r3 = r0
            r1 = r22
            goto L_0x00a4
        L_0x00c3:
            r1 = r7
            r3 = r1
            r16 = r3
            r18 = r16
        L_0x00c9:
            int r0 = (r14 > r9 ? 1 : (r14 == r9 ? 0 : -1))
            if (r0 >= 0) goto L_0x00e8
            double r20 = r6.getDouble(r14)
            double r16 = r20 - r16
            double r22 = r1 + r16
            double r0 = r22 - r1
            double r16 = r0 - r16
            double r20 = r20 * r20
            double r20 = r20 - r18
            double r0 = r3 + r20
            double r2 = r0 - r3
            double r18 = r2 - r20
            long r14 = r14 + r12
            r3 = r0
            r1 = r22
            goto L_0x00c9
        L_0x00e8:
            double r5 = (double) r9
            double r1 = r1 / r5
            double r3 = r3 / r5
            double r1 = r1 * r1
            double r3 = r3 - r1
            double r0 = org.apache.commons.math3.util.FastMath.max((double) r7, (double) r3)
            double r0 = org.apache.commons.math3.util.FastMath.sqrt(r0)
            return r0
        L_0x00f6:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "a == null || !a.isNumeric() || a.getType() == LargeArrayType.COMPLEX_FLOAT || a.getType() == LargeArrayType.COMPLEX_DOUBLE"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: pl.edu.icm.jlargearrays.LargeArrayStatistics.std(pl.edu.icm.jlargearrays.LargeArray):double");
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x00a4 A[LOOP:2: B:35:0x00a0->B:38:0x00a4, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static double stdKahan(pl.edu.icm.jlargearrays.LargeArray r23) {
        /*
            r6 = r23
            if (r6 == 0) goto L_0x00d0
            boolean r0 = r23.isNumeric()
            if (r0 == 0) goto L_0x00d0
            pl.edu.icm.jlargearrays.LargeArrayType r0 = r23.getType()
            pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.COMPLEX_FLOAT
            if (r0 == r1) goto L_0x00d0
            pl.edu.icm.jlargearrays.LargeArrayType r0 = r23.getType()
            pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.COMPLEX_DOUBLE
            if (r0 == r1) goto L_0x00d0
            boolean r0 = r23.isConstant()
            r7 = 0
            if (r0 == 0) goto L_0x0023
            return r7
        L_0x0023:
            long r9 = r23.length()
            r0 = 2
            int r0 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r0 >= 0) goto L_0x0030
            r0 = 9221120237041090560(0x7ff8000000000000, double:NaN)
            return r0
        L_0x0030:
            int r0 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getNumberOfThreads()
            long r0 = (long) r0
            long r0 = org.apache.commons.math3.util.FastMath.min((long) r9, (long) r0)
            int r11 = (int) r0
            r0 = 2
            r12 = 1
            r14 = 0
            if (r11 < r0) goto L_0x00b0
            long r0 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getConcurrentThreshold()
            int r0 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r0 >= 0) goto L_0x004b
            goto L_0x00b0
        L_0x004b:
            long r0 = (long) r11
            long r16 = r9 / r0
            java.util.concurrent.Future[] r5 = new java.util.concurrent.Future[r11]
            r18 = 0
            r3 = r18
        L_0x0054:
            if (r3 >= r11) goto L_0x007c
            long r0 = (long) r3
            long r1 = r0 * r16
            int r0 = r11 + -1
            if (r3 != r0) goto L_0x0060
            r19 = r9
            goto L_0x0062
        L_0x0060:
            long r19 = r1 + r16
        L_0x0062:
            pl.edu.icm.jlargearrays.LargeArrayStatistics$8 r21 = new pl.edu.icm.jlargearrays.LargeArrayStatistics$8
            r0 = r21
            r22 = r3
            r3 = r19
            r19 = r5
            r5 = r23
            r0.<init>(r1, r3, r5)
            java.util.concurrent.Future r0 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit(r21)
            r19[r22] = r0
            int r3 = r22 + 1
            r5 = r19
            goto L_0x0054
        L_0x007c:
            r19 = r5
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r19)     // Catch:{ InterruptedException | ExecutionException -> 0x009e }
            r1 = r7
            r3 = r1
            r0 = r18
        L_0x0085:
            if (r0 >= r11) goto L_0x00c2
            r5 = r19[r0]     // Catch:{ InterruptedException | ExecutionException -> 0x00a0 }
            java.lang.Object r5 = r5.get()     // Catch:{ InterruptedException | ExecutionException -> 0x00a0 }
            double[] r5 = (double[]) r5     // Catch:{ InterruptedException | ExecutionException -> 0x00a0 }
            double[] r5 = (double[]) r5     // Catch:{ InterruptedException | ExecutionException -> 0x00a0 }
            r16 = r5[r18]     // Catch:{ InterruptedException | ExecutionException -> 0x00a0 }
            double r1 = r1 + r16
            r16 = 1
            r16 = r5[r16]     // Catch:{ InterruptedException | ExecutionException -> 0x00a0 }
            double r3 = r3 + r16
            int r0 = r0 + 1
            goto L_0x0085
        L_0x009e:
            r1 = r7
            r3 = r1
        L_0x00a0:
            int r0 = (r14 > r9 ? 1 : (r14 == r9 ? 0 : -1))
            if (r0 >= 0) goto L_0x00c2
            double r16 = r6.getDouble(r14)
            double r1 = r1 + r16
            double r16 = r16 * r16
            double r3 = r3 + r16
            long r14 = r14 + r12
            goto L_0x00a0
        L_0x00b0:
            r1 = r7
            r3 = r1
        L_0x00b2:
            int r0 = (r14 > r9 ? 1 : (r14 == r9 ? 0 : -1))
            if (r0 >= 0) goto L_0x00c2
            double r16 = r6.getDouble(r14)
            double r1 = r1 + r16
            double r16 = r16 * r16
            double r3 = r3 + r16
            long r14 = r14 + r12
            goto L_0x00b2
        L_0x00c2:
            double r5 = (double) r9
            double r1 = r1 / r5
            double r3 = r3 / r5
            double r1 = r1 * r1
            double r3 = r3 - r1
            double r0 = org.apache.commons.math3.util.FastMath.max((double) r7, (double) r3)
            double r0 = org.apache.commons.math3.util.FastMath.sqrt(r0)
            return r0
        L_0x00d0:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "a == null || !a.isNumeric() || a.getType() == LargeArrayType.COMPLEX_FLOAT || a.getType() == LargeArrayType.COMPLEX_DOUBLE"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: pl.edu.icm.jlargearrays.LargeArrayStatistics.stdKahan(pl.edu.icm.jlargearrays.LargeArray):double");
    }
}
