package org.apache.commons.math3.linear;

import java.lang.reflect.Array;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class SingularValueDecomposition {
    private static final double EPS = 2.220446049250313E-16d;
    private static final double TINY = 1.6033346880071782E-291d;
    private RealMatrix cachedS;
    private final RealMatrix cachedU;
    private RealMatrix cachedUt;
    private final RealMatrix cachedV;
    private RealMatrix cachedVt;
    private final int m;
    private final int n;
    /* access modifiers changed from: private */
    public final double[] singularValues;
    private final double tol;
    private final boolean transposed;

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v0, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v0, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r35v1, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v1, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v8, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v9, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v1, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v10, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v11, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v5, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v3, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v1, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v6, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v15, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v16, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v8, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v3, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v6, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v2, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v1, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v5, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v9, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v5, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v9, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v14, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v10, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v5, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v16, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v22, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v3, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v25, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v11, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v17, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v8, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v2, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v28, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v17, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v15, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v16, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v20, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v13, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v13, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v22, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v14, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v14, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v23, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v24, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v26, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v17, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v9, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v35, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v13, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v22, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v20, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v25, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v29, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v27, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v16, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v19, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v21, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v22, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v15, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v22, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v24, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v17, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v42, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v33, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v48, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v36, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v39, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v40, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v34, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v38, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v21, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v23, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v23, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v18, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v34, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v25, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v20, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v37, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v23, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v37, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v39, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v57, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v41, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v44, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r35v2, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v25, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v26, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v44, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v45, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v46, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v48, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v27, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v52, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v53, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v28, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v54, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v27, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v12, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v27, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v32, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v14, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v26, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v29, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v15, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v34, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v59, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v60, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v61, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v36, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v62, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v63, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v64, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v37, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v67, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v27, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v38, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v68, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v39, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v40, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v31, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v33, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v21, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v3, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v29, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v23, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v5, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v30, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r35v4, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v77, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v7, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v38, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v91, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v93, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v51, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v12, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v79, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v38, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v40, resolved type: double[][]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SingularValueDecomposition(org.apache.commons.math3.linear.RealMatrix r35) {
        /*
            r34 = this;
            r0 = r34
            r34.<init>()
            int r1 = r35.getRowDimension()
            int r2 = r35.getColumnDimension()
            r3 = 0
            r4 = 1
            if (r1 >= r2) goto L_0x0028
            r0.transposed = r4
            org.apache.commons.math3.linear.RealMatrix r1 = r35.transpose()
            double[][] r1 = r1.getData()
            int r2 = r35.getColumnDimension()
            r0.m = r2
            int r2 = r35.getRowDimension()
            r0.n = r2
            goto L_0x003a
        L_0x0028:
            r0.transposed = r3
            double[][] r1 = r35.getData()
            int r2 = r35.getRowDimension()
            r0.m = r2
            int r2 = r35.getColumnDimension()
            r0.n = r2
        L_0x003a:
            int r2 = r0.n
            double[] r5 = new double[r2]
            r0.singularValues = r5
            int r5 = r0.m
            r6 = 2
            int[] r7 = new int[r6]
            r7[r4] = r2
            r7[r3] = r5
            java.lang.Class r2 = java.lang.Double.TYPE
            java.lang.Object r2 = java.lang.reflect.Array.newInstance(r2, r7)
            double[][] r2 = (double[][]) r2
            int r5 = r0.n
            int[] r7 = new int[r6]
            r7[r4] = r5
            r7[r3] = r5
            java.lang.Class r5 = java.lang.Double.TYPE
            java.lang.Object r5 = java.lang.reflect.Array.newInstance(r5, r7)
            double[][] r5 = (double[][]) r5
            int r7 = r0.n
            double[] r8 = new double[r7]
            int r9 = r0.m
            double[] r10 = new double[r9]
            int r9 = r9 - r4
            int r7 = org.apache.commons.math3.util.FastMath.min((int) r9, (int) r7)
            int r9 = r0.n
            int r9 = r9 - r6
            int r9 = org.apache.commons.math3.util.FastMath.max((int) r3, (int) r9)
            r11 = r3
        L_0x0076:
            int r12 = org.apache.commons.math3.util.FastMath.max((int) r7, (int) r9)
            r13 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r15 = 0
            if (r11 >= r12) goto L_0x01ed
            if (r11 >= r7) goto L_0x00e0
            double[] r12 = r0.singularValues
            r12[r11] = r15
            r12 = r11
        L_0x0087:
            int r3 = r0.m
            if (r12 >= r3) goto L_0x00a6
            double[] r3 = r0.singularValues
            r35 = r5
            r4 = r3[r11]
            r18 = r1[r12]
            r20 = r7
            r6 = r18[r11]
            double r4 = org.apache.commons.math3.util.FastMath.hypot(r4, r6)
            r3[r11] = r4
            int r12 = r12 + 1
            r5 = r35
            r7 = r20
            r4 = 1
            r6 = 2
            goto L_0x0087
        L_0x00a6:
            r35 = r5
            r20 = r7
            double[] r3 = r0.singularValues
            r4 = r3[r11]
            int r6 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r6 == 0) goto L_0x00d8
            r6 = r1[r11]
            r21 = r6[r11]
            int r6 = (r21 > r15 ? 1 : (r21 == r15 ? 0 : -1))
            if (r6 >= 0) goto L_0x00bd
            double r4 = -r4
            r3[r11] = r4
        L_0x00bd:
            r3 = r11
        L_0x00be:
            int r4 = r0.m
            if (r3 >= r4) goto L_0x00d1
            r4 = r1[r3]
            r5 = r4[r11]
            double[] r7 = r0.singularValues
            r21 = r7[r11]
            double r5 = r5 / r21
            r4[r11] = r5
            int r3 = r3 + 1
            goto L_0x00be
        L_0x00d1:
            r3 = r1[r11]
            r4 = r3[r11]
            double r4 = r4 + r13
            r3[r11] = r4
        L_0x00d8:
            double[] r3 = r0.singularValues
            r4 = r3[r11]
            double r4 = -r4
            r3[r11] = r4
            goto L_0x00e4
        L_0x00e0:
            r35 = r5
            r20 = r7
        L_0x00e4:
            int r3 = r11 + 1
            r4 = r3
        L_0x00e7:
            int r5 = r0.n
            if (r4 >= r5) goto L_0x0131
            r5 = r20
            if (r11 >= r5) goto L_0x0124
            double[] r6 = r0.singularValues
            r20 = r6[r11]
            int r6 = (r20 > r15 ? 1 : (r20 == r15 ? 0 : -1))
            if (r6 == 0) goto L_0x0124
            r6 = r11
            r13 = r15
        L_0x00f9:
            int r7 = r0.m
            if (r6 >= r7) goto L_0x010a
            r7 = r1[r6]
            r22 = r7[r11]
            r24 = r7[r4]
            double r22 = r22 * r24
            double r13 = r13 + r22
            int r6 = r6 + 1
            goto L_0x00f9
        L_0x010a:
            double r6 = -r13
            r12 = r1[r11]
            r13 = r12[r11]
            double r6 = r6 / r13
            r12 = r11
        L_0x0111:
            int r13 = r0.m
            if (r12 >= r13) goto L_0x0124
            r13 = r1[r12]
            r22 = r13[r4]
            r24 = r13[r11]
            double r24 = r24 * r6
            double r22 = r22 + r24
            r13[r4] = r22
            int r12 = r12 + 1
            goto L_0x0111
        L_0x0124:
            r6 = r1[r11]
            r12 = r6[r4]
            r8[r4] = r12
            int r4 = r4 + 1
            r20 = r5
            r13 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            goto L_0x00e7
        L_0x0131:
            r5 = r20
            if (r11 >= r5) goto L_0x0145
            r4 = r11
        L_0x0136:
            int r6 = r0.m
            if (r4 >= r6) goto L_0x0145
            r6 = r2[r4]
            r7 = r1[r4]
            r12 = r7[r11]
            r6[r11] = r12
            int r4 = r4 + 1
            goto L_0x0136
        L_0x0145:
            if (r11 >= r9) goto L_0x01e4
            r8[r11] = r15
            r4 = r3
        L_0x014a:
            int r6 = r0.n
            if (r4 >= r6) goto L_0x015b
            r6 = r8[r11]
            r12 = r8[r4]
            double r6 = org.apache.commons.math3.util.FastMath.hypot(r6, r12)
            r8[r11] = r6
            int r4 = r4 + 1
            goto L_0x014a
        L_0x015b:
            r6 = r8[r11]
            int r4 = (r6 > r15 ? 1 : (r6 == r15 ? 0 : -1))
            if (r4 == 0) goto L_0x0180
            r12 = r8[r3]
            int r4 = (r12 > r15 ? 1 : (r12 == r15 ? 0 : -1))
            if (r4 >= 0) goto L_0x016a
            double r6 = -r6
            r8[r11] = r6
        L_0x016a:
            r4 = r3
        L_0x016b:
            int r6 = r0.n
            if (r4 >= r6) goto L_0x0179
            r6 = r8[r4]
            r12 = r8[r11]
            double r6 = r6 / r12
            r8[r4] = r6
            int r4 = r4 + 1
            goto L_0x016b
        L_0x0179:
            r6 = r8[r3]
            r12 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r6 = r6 + r12
            r8[r3] = r6
        L_0x0180:
            r6 = r8[r11]
            double r6 = -r6
            r8[r11] = r6
            int r4 = r0.m
            if (r3 >= r4) goto L_0x01d6
            int r4 = (r6 > r15 ? 1 : (r6 == r15 ? 0 : -1))
            if (r4 == 0) goto L_0x01d6
            r4 = r3
        L_0x018e:
            int r6 = r0.m
            if (r4 >= r6) goto L_0x0197
            r10[r4] = r15
            int r4 = r4 + 1
            goto L_0x018e
        L_0x0197:
            r4 = r3
        L_0x0198:
            int r6 = r0.n
            if (r4 >= r6) goto L_0x01b4
            r6 = r3
        L_0x019d:
            int r7 = r0.m
            if (r6 >= r7) goto L_0x01b1
            r12 = r10[r6]
            r14 = r8[r4]
            r7 = r1[r6]
            r20 = r7[r4]
            double r14 = r14 * r20
            double r12 = r12 + r14
            r10[r6] = r12
            int r6 = r6 + 1
            goto L_0x019d
        L_0x01b1:
            int r4 = r4 + 1
            goto L_0x0198
        L_0x01b4:
            r4 = r3
        L_0x01b5:
            int r6 = r0.n
            if (r4 >= r6) goto L_0x01d6
            r6 = r8[r4]
            double r6 = -r6
            r12 = r8[r3]
            double r6 = r6 / r12
            r12 = r3
        L_0x01c0:
            int r13 = r0.m
            if (r12 >= r13) goto L_0x01d3
            r13 = r1[r12]
            r14 = r13[r4]
            r20 = r10[r12]
            double r20 = r20 * r6
            double r14 = r14 + r20
            r13[r4] = r14
            int r12 = r12 + 1
            goto L_0x01c0
        L_0x01d3:
            int r4 = r4 + 1
            goto L_0x01b5
        L_0x01d6:
            r4 = r3
        L_0x01d7:
            int r6 = r0.n
            if (r4 >= r6) goto L_0x01e4
            r6 = r35[r4]
            r12 = r8[r4]
            r6[r11] = r12
            int r4 = r4 + 1
            goto L_0x01d7
        L_0x01e4:
            r11 = r3
            r7 = r5
            r3 = 0
            r4 = 1
            r6 = 2
            r5 = r35
            goto L_0x0076
        L_0x01ed:
            r35 = r5
            r5 = r7
            int r3 = r0.n
            if (r5 >= r3) goto L_0x01fc
            double[] r4 = r0.singularValues
            r6 = r1[r5]
            r10 = r6[r5]
            r4[r5] = r10
        L_0x01fc:
            int r4 = r0.m
            if (r4 >= r3) goto L_0x0206
            double[] r4 = r0.singularValues
            int r6 = r3 + -1
            r4[r6] = r15
        L_0x0206:
            int r4 = r9 + 1
            if (r4 >= r3) goto L_0x0212
            r1 = r1[r9]
            int r4 = r3 + -1
            r6 = r1[r4]
            r8[r9] = r6
        L_0x0212:
            int r1 = r3 + -1
            r8[r1] = r15
            r4 = r5
        L_0x0217:
            int r6 = r0.n
            if (r4 >= r6) goto L_0x0230
            r6 = 0
        L_0x021c:
            int r7 = r0.m
            if (r6 >= r7) goto L_0x0227
            r7 = r2[r6]
            r7[r4] = r15
            int r6 = r6 + 1
            goto L_0x021c
        L_0x0227:
            r6 = r2[r4]
            r10 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r6[r4] = r10
            int r4 = r4 + 1
            goto L_0x0217
        L_0x0230:
            r4 = 1
            int r7 = r5 + -1
        L_0x0233:
            if (r7 < 0) goto L_0x02ab
            double[] r4 = r0.singularValues
            r5 = r4[r7]
            int r4 = (r5 > r15 ? 1 : (r5 == r15 ? 0 : -1))
            if (r4 == 0) goto L_0x0296
            int r4 = r7 + 1
        L_0x023f:
            int r5 = r0.n
            if (r4 >= r5) goto L_0x0272
            r5 = r7
            r10 = r15
        L_0x0245:
            int r6 = r0.m
            if (r5 >= r6) goto L_0x0255
            r6 = r2[r5]
            r12 = r6[r7]
            r22 = r6[r4]
            double r12 = r12 * r22
            double r10 = r10 + r12
            int r5 = r5 + 1
            goto L_0x0245
        L_0x0255:
            double r5 = -r10
            r10 = r2[r7]
            r11 = r10[r7]
            double r5 = r5 / r11
            r10 = r7
        L_0x025c:
            int r11 = r0.m
            if (r10 >= r11) goto L_0x026f
            r11 = r2[r10]
            r12 = r11[r4]
            r22 = r11[r7]
            double r22 = r22 * r5
            double r12 = r12 + r22
            r11[r4] = r12
            int r10 = r10 + 1
            goto L_0x025c
        L_0x026f:
            int r4 = r4 + 1
            goto L_0x023f
        L_0x0272:
            r4 = r7
        L_0x0273:
            int r5 = r0.m
            if (r4 >= r5) goto L_0x0281
            r5 = r2[r4]
            r10 = r5[r7]
            double r10 = -r10
            r5[r7] = r10
            int r4 = r4 + 1
            goto L_0x0273
        L_0x0281:
            r4 = r2[r7]
            r5 = r4[r7]
            r10 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r5 = r5 + r10
            r4[r7] = r5
            r4 = 0
        L_0x028b:
            int r5 = r7 + -1
            if (r4 >= r5) goto L_0x02a8
            r5 = r2[r4]
            r5[r7] = r15
            int r4 = r4 + 1
            goto L_0x028b
        L_0x0296:
            r4 = 0
        L_0x0297:
            int r5 = r0.m
            if (r4 >= r5) goto L_0x02a2
            r5 = r2[r4]
            r5[r7] = r15
            int r4 = r4 + 1
            goto L_0x0297
        L_0x02a2:
            r4 = r2[r7]
            r5 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r4[r7] = r5
        L_0x02a8:
            int r7 = r7 + -1
            goto L_0x0233
        L_0x02ab:
            int r4 = r0.n
            r5 = 1
            int r4 = r4 - r5
        L_0x02af:
            if (r4 < 0) goto L_0x0304
            if (r4 >= r9) goto L_0x02ef
            r5 = r8[r4]
            int r5 = (r5 > r15 ? 1 : (r5 == r15 ? 0 : -1))
            if (r5 == 0) goto L_0x02ef
            int r5 = r4 + 1
            r6 = r5
        L_0x02bc:
            int r7 = r0.n
            if (r6 >= r7) goto L_0x02ef
            r7 = r5
            r10 = r15
        L_0x02c2:
            int r12 = r0.n
            if (r7 >= r12) goto L_0x02d2
            r12 = r35[r7]
            r13 = r12[r4]
            r22 = r12[r6]
            double r13 = r13 * r22
            double r10 = r10 + r13
            int r7 = r7 + 1
            goto L_0x02c2
        L_0x02d2:
            double r10 = -r10
            r7 = r35[r5]
            r12 = r7[r4]
            double r10 = r10 / r12
            r7 = r5
        L_0x02d9:
            int r12 = r0.n
            if (r7 >= r12) goto L_0x02ec
            r12 = r35[r7]
            r13 = r12[r6]
            r22 = r12[r4]
            double r22 = r22 * r10
            double r13 = r13 + r22
            r12[r6] = r13
            int r7 = r7 + 1
            goto L_0x02d9
        L_0x02ec:
            int r6 = r6 + 1
            goto L_0x02bc
        L_0x02ef:
            r5 = 0
        L_0x02f0:
            int r6 = r0.n
            if (r5 >= r6) goto L_0x02fb
            r6 = r35[r5]
            r6[r4] = r15
            int r5 = r5 + 1
            goto L_0x02f0
        L_0x02fb:
            r5 = r35[r4]
            r6 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r5[r4] = r6
            int r4 = r4 + -1
            goto L_0x02af
        L_0x0304:
            r4 = 4372995238176751616(0x3cb0000000000000, double:2.220446049250313E-16)
            if (r3 <= 0) goto L_0x05fd
            int r6 = r3 + -2
            r7 = r6
        L_0x030b:
            r9 = 256705178760118272(0x390000000000000, double:1.6033346880071782E-291)
            if (r7 < 0) goto L_0x0334
            double[] r11 = r0.singularValues
            r12 = r11[r7]
            double r11 = org.apache.commons.math3.util.FastMath.abs((double) r12)
            double[] r13 = r0.singularValues
            int r14 = r7 + 1
            r20 = r13[r14]
            double r13 = org.apache.commons.math3.util.FastMath.abs((double) r20)
            double r11 = r11 + r13
            double r11 = r11 * r4
            double r11 = r11 + r9
            r13 = r8[r7]
            double r13 = org.apache.commons.math3.util.FastMath.abs((double) r13)
            int r11 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1))
            if (r11 > 0) goto L_0x0331
            r8[r7] = r15
            goto L_0x0334
        L_0x0331:
            int r7 = r7 + -1
            goto L_0x030b
        L_0x0334:
            r11 = 3
            if (r7 != r6) goto L_0x0339
            r4 = 4
            goto L_0x037f
        L_0x0339:
            int r12 = r3 + -1
            r13 = r12
        L_0x033c:
            if (r13 < r7) goto L_0x0375
            if (r13 != r7) goto L_0x0341
            goto L_0x0375
        L_0x0341:
            if (r13 == r3) goto L_0x034a
            r20 = r8[r13]
            double r20 = org.apache.commons.math3.util.FastMath.abs((double) r20)
            goto L_0x034c
        L_0x034a:
            r20 = r15
        L_0x034c:
            int r14 = r7 + 1
            if (r13 == r14) goto L_0x0359
            int r14 = r13 + -1
            r22 = r8[r14]
            double r22 = org.apache.commons.math3.util.FastMath.abs((double) r22)
            goto L_0x035b
        L_0x0359:
            r22 = r15
        L_0x035b:
            double r20 = r20 + r22
            double[] r14 = r0.singularValues
            r22 = r14[r13]
            double r22 = org.apache.commons.math3.util.FastMath.abs((double) r22)
            double r20 = r20 * r4
            double r20 = r20 + r9
            int r14 = (r22 > r20 ? 1 : (r22 == r20 ? 0 : -1))
            if (r14 > 0) goto L_0x0372
            double[] r4 = r0.singularValues
            r4[r13] = r15
            goto L_0x0375
        L_0x0372:
            int r13 = r13 + -1
            goto L_0x033c
        L_0x0375:
            if (r13 != r7) goto L_0x0379
            r4 = r11
            goto L_0x037f
        L_0x0379:
            if (r13 != r12) goto L_0x037d
            r4 = 1
            goto L_0x037f
        L_0x037d:
            r7 = r13
            r4 = 2
        L_0x037f:
            int r5 = r7 + 1
            r9 = 1
            if (r4 == r9) goto L_0x0595
            r9 = 2
            if (r4 == r9) goto L_0x0546
            if (r4 == r11) goto L_0x03f4
            double[] r4 = r0.singularValues
            r6 = r4[r5]
            int r10 = (r6 > r15 ? 1 : (r6 == r15 ? 0 : -1))
            if (r10 > 0) goto L_0x03a7
            int r10 = (r6 > r15 ? 1 : (r6 == r15 ? 0 : -1))
            if (r10 >= 0) goto L_0x0397
            double r6 = -r6
            goto L_0x0398
        L_0x0397:
            r6 = r15
        L_0x0398:
            r4[r5] = r6
            r4 = 0
        L_0x039b:
            if (r4 > r1) goto L_0x03a7
            r6 = r35[r4]
            r10 = r6[r5]
            double r10 = -r10
            r6[r5] = r10
            int r4 = r4 + 1
            goto L_0x039b
        L_0x03a7:
            if (r5 >= r1) goto L_0x03ec
            double[] r4 = r0.singularValues
            r6 = r4[r5]
            int r10 = r5 + 1
            r11 = r4[r10]
            int r13 = (r6 > r11 ? 1 : (r6 == r11 ? 0 : -1))
            if (r13 < 0) goto L_0x03b6
            goto L_0x03ec
        L_0x03b6:
            r4[r5] = r11
            r4[r10] = r6
            int r4 = r0.n
            r6 = 1
            int r4 = r4 - r6
            if (r5 >= r4) goto L_0x03d2
            r4 = 0
        L_0x03c1:
            int r6 = r0.n
            if (r4 >= r6) goto L_0x03d2
            r6 = r35[r4]
            r11 = r6[r10]
            r13 = r6[r5]
            r6[r10] = r13
            r6[r5] = r11
            int r4 = r4 + 1
            goto L_0x03c1
        L_0x03d2:
            int r4 = r0.m
            r6 = 1
            int r4 = r4 - r6
            if (r5 >= r4) goto L_0x03ea
            r4 = 0
        L_0x03d9:
            int r6 = r0.m
            if (r4 >= r6) goto L_0x03ea
            r6 = r2[r4]
            r11 = r6[r10]
            r13 = r6[r5]
            r6[r10] = r13
            r6[r5] = r11
            int r4 = r4 + 1
            goto L_0x03d9
        L_0x03ea:
            r5 = r10
            goto L_0x03a7
        L_0x03ec:
            int r3 = r3 + -1
            r25 = r1
            r20 = r2
            goto L_0x05f5
        L_0x03f4:
            double[] r4 = r0.singularValues
            int r7 = r3 + -1
            r10 = r4[r7]
            double r10 = org.apache.commons.math3.util.FastMath.abs((double) r10)
            double[] r4 = r0.singularValues
            r12 = r4[r6]
            double r12 = org.apache.commons.math3.util.FastMath.abs((double) r12)
            double r10 = org.apache.commons.math3.util.FastMath.max((double) r10, (double) r12)
            r12 = r8[r6]
            double r12 = org.apache.commons.math3.util.FastMath.abs((double) r12)
            double r10 = org.apache.commons.math3.util.FastMath.max((double) r10, (double) r12)
            double[] r4 = r0.singularValues
            r12 = r4[r5]
            double r12 = org.apache.commons.math3.util.FastMath.abs((double) r12)
            double r10 = org.apache.commons.math3.util.FastMath.max((double) r10, (double) r12)
            r12 = r8[r5]
            double r12 = org.apache.commons.math3.util.FastMath.abs((double) r12)
            double r10 = org.apache.commons.math3.util.FastMath.max((double) r10, (double) r12)
            double[] r4 = r0.singularValues
            r12 = r4[r7]
            double r12 = r12 / r10
            r18 = r4[r6]
            double r18 = r18 / r10
            r20 = r8[r6]
            double r20 = r20 / r10
            r22 = r4[r5]
            double r22 = r22 / r10
            r24 = r8[r5]
            double r24 = r24 / r10
            double r10 = r18 + r12
            double r18 = r18 - r12
            double r10 = r10 * r18
            double r18 = r20 * r20
            double r10 = r10 + r18
            r18 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r10 = r10 / r18
            double r20 = r20 * r12
            double r20 = r20 * r20
            int r4 = (r10 > r15 ? 1 : (r10 == r15 ? 0 : -1))
            if (r4 != 0) goto L_0x045e
            int r4 = (r20 > r15 ? 1 : (r20 == r15 ? 0 : -1))
            if (r4 == 0) goto L_0x045a
            goto L_0x045e
        L_0x045a:
            r14 = r3
            r20 = r15
            goto L_0x046f
        L_0x045e:
            double r18 = r10 * r10
            double r18 = r18 + r20
            r14 = r3
            double r3 = org.apache.commons.math3.util.FastMath.sqrt(r18)
            int r18 = (r10 > r15 ? 1 : (r10 == r15 ? 0 : -1))
            if (r18 >= 0) goto L_0x046c
            double r3 = -r3
        L_0x046c:
            double r10 = r10 + r3
            double r20 = r20 / r10
        L_0x046f:
            double r3 = r22 + r12
            double r10 = r22 - r12
            double r3 = r3 * r10
            double r3 = r3 + r20
            double r22 = r22 * r24
            r12 = r5
            r10 = r22
        L_0x047b:
            if (r12 >= r7) goto L_0x0537
            double r18 = org.apache.commons.math3.util.FastMath.hypot(r3, r10)
            double r3 = r3 / r18
            double r10 = r10 / r18
            if (r12 == r5) goto L_0x048b
            int r13 = r12 + -1
            r8[r13] = r18
        L_0x048b:
            double[] r13 = r0.singularValues
            r18 = r13[r12]
            double r20 = r3 * r18
            r22 = r8[r12]
            double r24 = r10 * r22
            r16 = r14
            double r14 = r20 + r24
            double r22 = r22 * r3
            double r18 = r18 * r10
            double r22 = r22 - r18
            r8[r12] = r22
            int r18 = r12 + 1
            r19 = r13[r18]
            r22 = r5
            r21 = r6
            double r5 = r10 * r19
            double r19 = r19 * r3
            r13[r18] = r19
            r13 = 0
        L_0x04b0:
            int r9 = r0.n
            if (r13 >= r9) goto L_0x04d6
            r9 = r35[r13]
            r23 = r9[r12]
            double r26 = r3 * r23
            r28 = r9[r18]
            double r30 = r10 * r28
            double r26 = r26 + r30
            r25 = r1
            r20 = r2
            double r1 = -r10
            double r1 = r1 * r23
            double r28 = r28 * r3
            double r1 = r1 + r28
            r9[r18] = r1
            r9[r12] = r26
            int r13 = r13 + 1
            r2 = r20
            r1 = r25
            goto L_0x04b0
        L_0x04d6:
            r25 = r1
            r20 = r2
            double r1 = org.apache.commons.math3.util.FastMath.hypot(r14, r5)
            double r14 = r14 / r1
            double r5 = r5 / r1
            double[] r3 = r0.singularValues
            r3[r12] = r1
            r1 = r8[r12]
            double r9 = r14 * r1
            r23 = r3[r18]
            double r26 = r5 * r23
            double r9 = r9 + r26
            r26 = r9
            double r9 = -r5
            double r1 = r1 * r9
            double r23 = r23 * r14
            double r1 = r1 + r23
            r3[r18] = r1
            r1 = r8[r18]
            double r3 = r5 * r1
            double r1 = r1 * r14
            r8[r18] = r1
            int r1 = r0.m
            r2 = 1
            int r1 = r1 - r2
            if (r12 >= r1) goto L_0x0523
            r1 = 0
        L_0x0506:
            int r11 = r0.m
            if (r1 >= r11) goto L_0x0523
            r11 = r20[r1]
            r23 = r11[r12]
            double r28 = r14 * r23
            r30 = r11[r18]
            double r32 = r5 * r30
            double r28 = r28 + r32
            double r23 = r23 * r9
            double r30 = r30 * r14
            double r23 = r23 + r30
            r11[r18] = r23
            r11[r12] = r28
            int r1 = r1 + 1
            goto L_0x0506
        L_0x0523:
            r10 = r3
            r14 = r16
            r12 = r18
            r2 = r20
            r6 = r21
            r5 = r22
            r1 = r25
            r3 = r26
            r9 = 2
            r15 = 0
            goto L_0x047b
        L_0x0537:
            r25 = r1
            r20 = r2
            r21 = r6
            r16 = r14
            r2 = 1
            r8[r21] = r3
            r1 = r16
            goto L_0x05f4
        L_0x0546:
            r25 = r1
            r20 = r2
            r16 = r3
            r22 = r5
            r2 = 1
            r3 = r8[r7]
            r5 = 0
            r8[r7] = r5
            r1 = r16
            r5 = r22
        L_0x0559:
            if (r5 >= r1) goto L_0x05f4
            double[] r6 = r0.singularValues
            r9 = r6[r5]
            double r9 = org.apache.commons.math3.util.FastMath.hypot(r9, r3)
            double[] r6 = r0.singularValues
            r11 = r6[r5]
            double r11 = r11 / r9
            double r3 = r3 / r9
            r6[r5] = r9
            double r9 = -r3
            r13 = r8[r5]
            double r15 = r9 * r13
            double r13 = r13 * r11
            r8[r5] = r13
            r6 = 0
        L_0x0574:
            int r13 = r0.m
            if (r6 >= r13) goto L_0x0591
            r13 = r20[r6]
            r17 = r13[r5]
            double r21 = r11 * r17
            r23 = r13[r7]
            double r26 = r3 * r23
            double r21 = r21 + r26
            double r17 = r17 * r9
            double r23 = r23 * r11
            double r17 = r17 + r23
            r13[r7] = r17
            r13[r5] = r21
            int r6 = r6 + 1
            goto L_0x0574
        L_0x0591:
            int r5 = r5 + 1
            r3 = r15
            goto L_0x0559
        L_0x0595:
            r25 = r1
            r20 = r2
            r1 = r3
            r22 = r5
            r21 = r6
            r2 = r9
            r3 = r8[r21]
            r6 = 0
            r8[r21] = r6
            r5 = r21
            r9 = r22
        L_0x05a9:
            if (r5 < r9) goto L_0x05f4
            double[] r10 = r0.singularValues
            r11 = r10[r5]
            double r10 = org.apache.commons.math3.util.FastMath.hypot(r11, r3)
            double[] r12 = r0.singularValues
            r13 = r12[r5]
            double r13 = r13 / r10
            double r6 = r3 / r10
            r12[r5] = r10
            if (r5 == r9) goto L_0x05c7
            double r3 = -r6
            int r10 = r5 + -1
            r11 = r8[r10]
            double r3 = r3 * r11
            double r11 = r11 * r13
            r8[r10] = r11
        L_0x05c7:
            r10 = 0
        L_0x05c8:
            int r11 = r0.n
            if (r10 >= r11) goto L_0x05ec
            r11 = r35[r10]
            r15 = r11[r5]
            double r17 = r13 * r15
            int r12 = r1 + -1
            r21 = r11[r12]
            double r23 = r6 * r21
            double r17 = r17 + r23
            r26 = r3
            double r2 = -r6
            double r2 = r2 * r15
            double r21 = r21 * r13
            double r2 = r2 + r21
            r11[r12] = r2
            r11[r5] = r17
            int r10 = r10 + 1
            r3 = r26
            r2 = 1
            goto L_0x05c8
        L_0x05ec:
            r26 = r3
            int r5 = r5 + -1
            r2 = 1
            r6 = 0
            goto L_0x05a9
        L_0x05f4:
            r3 = r1
        L_0x05f5:
            r2 = r20
            r1 = r25
            r15 = 0
            goto L_0x0304
        L_0x05fd:
            r20 = r2
            int r1 = r0.m
            double r1 = (double) r1
            double[] r3 = r0.singularValues
            r6 = 0
            r6 = r3[r6]
            double r1 = r1 * r6
            double r1 = r1 * r4
            double r3 = org.apache.commons.math3.util.Precision.SAFE_MIN
            double r3 = org.apache.commons.math3.util.FastMath.sqrt(r3)
            double r1 = org.apache.commons.math3.util.FastMath.max((double) r1, (double) r3)
            r0.tol = r1
            boolean r1 = r0.transposed
            if (r1 != 0) goto L_0x0626
            org.apache.commons.math3.linear.RealMatrix r1 = org.apache.commons.math3.linear.MatrixUtils.createRealMatrix(r20)
            r0.cachedU = r1
            org.apache.commons.math3.linear.RealMatrix r1 = org.apache.commons.math3.linear.MatrixUtils.createRealMatrix(r35)
            r0.cachedV = r1
            goto L_0x0632
        L_0x0626:
            org.apache.commons.math3.linear.RealMatrix r1 = org.apache.commons.math3.linear.MatrixUtils.createRealMatrix(r35)
            r0.cachedU = r1
            org.apache.commons.math3.linear.RealMatrix r1 = org.apache.commons.math3.linear.MatrixUtils.createRealMatrix(r20)
            r0.cachedV = r1
        L_0x0632:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.linear.SingularValueDecomposition.<init>(org.apache.commons.math3.linear.RealMatrix):void");
    }

    public RealMatrix getU() {
        return this.cachedU;
    }

    public RealMatrix getUT() {
        if (this.cachedUt == null) {
            this.cachedUt = getU().transpose();
        }
        return this.cachedUt;
    }

    public RealMatrix getS() {
        if (this.cachedS == null) {
            this.cachedS = MatrixUtils.createRealDiagonalMatrix(this.singularValues);
        }
        return this.cachedS;
    }

    public double[] getSingularValues() {
        return (double[]) this.singularValues.clone();
    }

    public RealMatrix getV() {
        return this.cachedV;
    }

    public RealMatrix getVT() {
        if (this.cachedVt == null) {
            this.cachedVt = getV().transpose();
        }
        return this.cachedVt;
    }

    public RealMatrix getCovariance(double d) {
        int length = this.singularValues.length;
        int i = 0;
        while (i < length && this.singularValues[i] >= d) {
            i++;
        }
        if (i != 0) {
            int[] iArr = new int[2];
            iArr[1] = length;
            iArr[0] = i;
            final double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr);
            getVT().walkInOptimizedOrder((RealMatrixPreservingVisitor) new DefaultRealMatrixPreservingVisitor() {
                public void visit(int i, int i2, double d) {
                    dArr[i][i2] = d / SingularValueDecomposition.this.singularValues[i];
                }
            }, 0, i - 1, 0, length - 1);
            Array2DRowRealMatrix array2DRowRealMatrix = new Array2DRowRealMatrix(dArr, false);
            return array2DRowRealMatrix.transpose().multiply(array2DRowRealMatrix);
        }
        throw new NumberIsTooLargeException(LocalizedFormats.TOO_LARGE_CUTOFF_SINGULAR_VALUE, Double.valueOf(d), Double.valueOf(this.singularValues[0]), true);
    }

    public double getNorm() {
        return this.singularValues[0];
    }

    public double getConditionNumber() {
        double[] dArr = this.singularValues;
        return dArr[0] / dArr[this.n - 1];
    }

    public double getInverseConditionNumber() {
        double[] dArr = this.singularValues;
        return dArr[this.n - 1] / dArr[0];
    }

    public int getRank() {
        int i = 0;
        int i2 = 0;
        while (true) {
            double[] dArr = this.singularValues;
            if (i >= dArr.length) {
                return i2;
            }
            if (dArr[i] > this.tol) {
                i2++;
            }
            i++;
        }
    }

    public DecompositionSolver getSolver() {
        return new Solver(this.singularValues, getUT(), getV(), getRank() == this.m, this.tol);
    }

    private static class Solver implements DecompositionSolver {
        private boolean nonSingular;
        private final RealMatrix pseudoInverse;

        private Solver(double[] dArr, RealMatrix realMatrix, RealMatrix realMatrix2, boolean z, double d) {
            double[][] data = realMatrix.getData();
            for (int i = 0; i < dArr.length; i++) {
                double d2 = dArr[i];
                double d3 = d2 > d ? 1.0d / d2 : 0.0d;
                double[] dArr2 = data[i];
                for (int i2 = 0; i2 < dArr2.length; i2++) {
                    dArr2[i2] = dArr2[i2] * d3;
                }
            }
            this.pseudoInverse = realMatrix2.multiply(new Array2DRowRealMatrix(data, false));
            this.nonSingular = z;
        }

        public RealVector solve(RealVector realVector) {
            return this.pseudoInverse.operate(realVector);
        }

        public RealMatrix solve(RealMatrix realMatrix) {
            return this.pseudoInverse.multiply(realMatrix);
        }

        public boolean isNonSingular() {
            return this.nonSingular;
        }

        public RealMatrix getInverse() {
            return this.pseudoInverse;
        }
    }
}
