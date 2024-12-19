package org.apache.commons.math3.random;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.Arrays;
import org.apache.commons.math3.exception.MathParseException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.util.FastMath;

public class SobolSequenceGenerator implements RandomVectorGenerator {
    private static final int BITS = 52;
    private static final String FILE_CHARSET = "US-ASCII";
    private static final int MAX_DIMENSION = 1000;
    private static final String RESOURCE_NAME = "/assets/org/apache/commons/math3/random/new-joe-kuo-6.1000";
    private static final double SCALE = FastMath.pow(2.0d, 52);
    private int count = 0;
    private final int dimension;
    private final long[][] direction;
    private final long[] x;

    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x003b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SobolSequenceGenerator(int r6) throws org.apache.commons.math3.exception.OutOfRangeException {
        /*
            r5 = this;
            r5.<init>()
            r0 = 0
            r5.count = r0
            r1 = 1000(0x3e8, float:1.401E-42)
            r2 = 1
            if (r6 < r2) goto L_0x0051
            if (r6 > r1) goto L_0x0051
            java.lang.Class r1 = r5.getClass()
            java.lang.String r3 = "/assets/org/apache/commons/math3/random/new-joe-kuo-6.1000"
            java.io.InputStream r1 = r1.getResourceAsStream(r3)
            if (r1 == 0) goto L_0x004b
            r5.dimension = r6
            r3 = 2
            int[] r3 = new int[r3]
            r4 = 53
            r3[r2] = r4
            r3[r0] = r6
            java.lang.Class r0 = java.lang.Long.TYPE
            java.lang.Object r0 = java.lang.reflect.Array.newInstance(r0, r3)
            long[][] r0 = (long[][]) r0
            r5.direction = r0
            long[] r6 = new long[r6]
            r5.x = r6
            r5.initFromStream(r1)     // Catch:{ IOException -> 0x0041, MathParseException -> 0x003b }
            r1.close()     // Catch:{ IOException -> 0x0038 }
        L_0x0038:
            return
        L_0x0039:
            r6 = move-exception
            goto L_0x0047
        L_0x003b:
            org.apache.commons.math3.exception.MathInternalError r6 = new org.apache.commons.math3.exception.MathInternalError     // Catch:{ all -> 0x0039 }
            r6.<init>()     // Catch:{ all -> 0x0039 }
            throw r6     // Catch:{ all -> 0x0039 }
        L_0x0041:
            org.apache.commons.math3.exception.MathInternalError r6 = new org.apache.commons.math3.exception.MathInternalError     // Catch:{ all -> 0x0039 }
            r6.<init>()     // Catch:{ all -> 0x0039 }
            throw r6     // Catch:{ all -> 0x0039 }
        L_0x0047:
            r1.close()     // Catch:{ IOException -> 0x004a }
        L_0x004a:
            throw r6
        L_0x004b:
            org.apache.commons.math3.exception.MathInternalError r6 = new org.apache.commons.math3.exception.MathInternalError
            r6.<init>()
            throw r6
        L_0x0051:
            org.apache.commons.math3.exception.OutOfRangeException r0 = new org.apache.commons.math3.exception.OutOfRangeException
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r0.<init>(r6, r2, r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.random.SobolSequenceGenerator.<init>(int):void");
    }

    public SobolSequenceGenerator(int i, InputStream inputStream) throws NotStrictlyPositiveException, MathParseException, IOException {
        if (i >= 1) {
            this.dimension = i;
            int[] iArr = new int[2];
            iArr[1] = 53;
            iArr[0] = i;
            this.direction = (long[][]) Array.newInstance(Long.TYPE, iArr);
            this.x = new long[i];
            int initFromStream = initFromStream(inputStream);
            if (initFromStream < i) {
                throw new OutOfRangeException(Integer.valueOf(i), 1, Integer.valueOf(initFromStream));
            }
            return;
        }
        throw new NotStrictlyPositiveException(Integer.valueOf(i));
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:24:0x007e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int initFromStream(java.io.InputStream r13) throws org.apache.commons.math3.exception.MathParseException, java.io.IOException {
        /*
            r12 = this;
            r0 = 1
            r1 = r0
        L_0x0002:
            r2 = 52
            if (r1 > r2) goto L_0x0016
            long[][] r2 = r12.direction
            r3 = 0
            r2 = r2[r3]
            int r3 = 52 - r1
            r4 = 1
            long r3 = r4 << r3
            r2[r1] = r3
            int r1 = r1 + 1
            goto L_0x0002
        L_0x0016:
            java.lang.String r1 = "US-ASCII"
            java.nio.charset.Charset r1 = java.nio.charset.Charset.forName(r1)
            java.io.BufferedReader r2 = new java.io.BufferedReader
            java.io.InputStreamReader r3 = new java.io.InputStreamReader
            r3.<init>(r13, r1)
            r2.<init>(r3)
            r2.readLine()     // Catch:{ all -> 0x008e }
            r13 = 2
            r1 = -1
            r3 = r13
            r4 = r0
        L_0x002d:
            java.lang.String r5 = r2.readLine()     // Catch:{ all -> 0x008e }
            if (r5 == 0) goto L_0x008a
            java.util.StringTokenizer r1 = new java.util.StringTokenizer     // Catch:{ all -> 0x008e }
            java.lang.String r6 = " "
            r1.<init>(r5, r6)     // Catch:{ all -> 0x008e }
            java.lang.String r6 = r1.nextToken()     // Catch:{ NoSuchElementException -> 0x0084, NumberFormatException -> 0x007e }
            int r6 = java.lang.Integer.parseInt(r6)     // Catch:{ NoSuchElementException -> 0x0084, NumberFormatException -> 0x007e }
            if (r6 < r13) goto L_0x0072
            int r7 = r12.dimension     // Catch:{ NoSuchElementException -> 0x0084, NumberFormatException -> 0x007e }
            if (r6 > r7) goto L_0x0072
            java.lang.String r7 = r1.nextToken()     // Catch:{ NoSuchElementException -> 0x0084, NumberFormatException -> 0x007e }
            int r7 = java.lang.Integer.parseInt(r7)     // Catch:{ NoSuchElementException -> 0x0084, NumberFormatException -> 0x007e }
            java.lang.String r8 = r1.nextToken()     // Catch:{ NoSuchElementException -> 0x0084, NumberFormatException -> 0x007e }
            int r8 = java.lang.Integer.parseInt(r8)     // Catch:{ NoSuchElementException -> 0x0084, NumberFormatException -> 0x007e }
            int r9 = r7 + 1
            int[] r9 = new int[r9]     // Catch:{ NoSuchElementException -> 0x0084, NumberFormatException -> 0x007e }
            r10 = r0
        L_0x005d:
            if (r10 > r7) goto L_0x006c
            java.lang.String r11 = r1.nextToken()     // Catch:{ NoSuchElementException -> 0x0084, NumberFormatException -> 0x007e }
            int r11 = java.lang.Integer.parseInt(r11)     // Catch:{ NoSuchElementException -> 0x0084, NumberFormatException -> 0x007e }
            r9[r10] = r11     // Catch:{ NoSuchElementException -> 0x0084, NumberFormatException -> 0x007e }
            int r10 = r10 + 1
            goto L_0x005d
        L_0x006c:
            int r1 = r4 + 1
            r12.initDirectionVector(r4, r8, r9)     // Catch:{ NoSuchElementException -> 0x0084, NumberFormatException -> 0x007e }
            r4 = r1
        L_0x0072:
            int r1 = r12.dimension     // Catch:{ NoSuchElementException -> 0x0084, NumberFormatException -> 0x007e }
            if (r6 <= r1) goto L_0x007a
            r2.close()
            return r6
        L_0x007a:
            int r3 = r3 + 1
            r1 = r6
            goto L_0x002d
        L_0x007e:
            org.apache.commons.math3.exception.MathParseException r13 = new org.apache.commons.math3.exception.MathParseException     // Catch:{ all -> 0x008e }
            r13.<init>(r5, r3)     // Catch:{ all -> 0x008e }
            throw r13     // Catch:{ all -> 0x008e }
        L_0x0084:
            org.apache.commons.math3.exception.MathParseException r13 = new org.apache.commons.math3.exception.MathParseException     // Catch:{ all -> 0x008e }
            r13.<init>(r5, r3)     // Catch:{ all -> 0x008e }
            throw r13     // Catch:{ all -> 0x008e }
        L_0x008a:
            r2.close()
            return r1
        L_0x008e:
            r13 = move-exception
            r2.close()
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.random.SobolSequenceGenerator.initFromStream(java.io.InputStream):int");
    }

    private void initDirectionVector(int i, int i2, int[] iArr) {
        int length = iArr.length;
        int i3 = length - 1;
        for (int i4 = 1; i4 <= i3; i4++) {
            this.direction[i][i4] = ((long) iArr[i4]) << (52 - i4);
        }
        for (int i5 = length; i5 <= 52; i5++) {
            long[] jArr = this.direction[i];
            long j = jArr[i5 - i3];
            jArr[i5] = j ^ (j >> i3);
            int i6 = 1;
            while (true) {
                int i7 = length - 2;
                if (i6 > i7) {
                    break;
                }
                long[] jArr2 = this.direction[i];
                jArr2[i5] = jArr2[i5] ^ (((long) ((i2 >> (i7 - i6)) & 1)) * jArr2[i5 - i6]);
                i6++;
            }
        }
    }

    public double[] nextVector() {
        double[] dArr = new double[this.dimension];
        int i = this.count;
        if (i == 0) {
            this.count = i + 1;
            return dArr;
        }
        int i2 = i - 1;
        int i3 = 1;
        while ((i2 & 1) == 1) {
            i2 >>= 1;
            i3++;
        }
        for (int i4 = 0; i4 < this.dimension; i4++) {
            long[] jArr = this.x;
            long j = jArr[i4] ^ this.direction[i4][i3];
            jArr[i4] = j;
            dArr[i4] = ((double) j) / SCALE;
        }
        this.count++;
        return dArr;
    }

    public double[] skipTo(int i) throws NotPositiveException {
        if (i == 0) {
            Arrays.fill(this.x, 0);
        } else {
            int i2 = i - 1;
            long j = (long) (i2 ^ (i2 >> 1));
            for (int i3 = 0; i3 < this.dimension; i3++) {
                long j2 = 0;
                for (int i4 = 1; i4 <= 52; i4++) {
                    long j3 = j >> (i4 - 1);
                    if (j3 == 0) {
                        break;
                    }
                    j2 ^= (j3 & 1) * this.direction[i3][i4];
                }
                this.x[i3] = j2;
            }
        }
        this.count = i;
        return nextVector();
    }

    public int getNextIndex() {
        return this.count;
    }
}
