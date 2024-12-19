package org.apache.commons.math3.linear;

import java.io.Serializable;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

public class BlockFieldMatrix<T extends FieldElement<T>> extends AbstractFieldMatrix<T> implements Serializable {
    public static final int BLOCK_SIZE = 36;
    private static final long serialVersionUID = -4602336630143123183L;
    private final int blockColumns;
    private final int blockRows;
    private final T[][] blocks;
    private final int columns;
    private final int rows;

    public BlockFieldMatrix(Field<T> field, int i, int i2) throws NotStrictlyPositiveException {
        super(field, i, i2);
        this.rows = i;
        this.columns = i2;
        this.blockRows = (i + 35) / 36;
        this.blockColumns = (i2 + 35) / 36;
        this.blocks = createBlocksLayout(field, i, i2);
    }

    public BlockFieldMatrix(T[][] tArr) throws DimensionMismatchException {
        this(tArr.length, tArr[0].length, toBlocksLayout(tArr), false);
    }

    public BlockFieldMatrix(int i, int i2, T[][] tArr, boolean z) throws DimensionMismatchException, NotStrictlyPositiveException {
        super(extractField(tArr), i, i2);
        this.rows = i;
        this.columns = i2;
        int i3 = (i + 35) / 36;
        this.blockRows = i3;
        int i4 = (i2 + 35) / 36;
        this.blockColumns = i4;
        if (z) {
            this.blocks = (FieldElement[][]) MathArrays.buildArray(getField(), i3 * i4, -1);
        } else {
            this.blocks = tArr;
        }
        int i5 = 0;
        for (int i6 = 0; i6 < this.blockRows; i6++) {
            int blockHeight = blockHeight(i6);
            int i7 = 0;
            while (i7 < this.blockColumns) {
                if (tArr[i5].length == blockWidth(i7) * blockHeight) {
                    if (z) {
                        this.blocks[i5] = (FieldElement[]) tArr[i5].clone();
                    }
                    i7++;
                    i5++;
                } else {
                    throw new DimensionMismatchException(tArr[i5].length, blockHeight * blockWidth(i7));
                }
            }
        }
    }

    public static <T extends FieldElement<T>> T[][] toBlocksLayout(T[][] tArr) throws DimensionMismatchException {
        T[][] tArr2 = tArr;
        int length = tArr2.length;
        int i = 0;
        int length2 = tArr2[0].length;
        int i2 = (length + 35) / 36;
        int i3 = (length2 + 35) / 36;
        int i4 = 0;
        while (i4 < tArr2.length) {
            int length3 = tArr2[i4].length;
            if (length3 == length2) {
                i4++;
            } else {
                throw new DimensionMismatchException(length2, length3);
            }
        }
        Field extractField = extractField(tArr);
        T[][] tArr3 = (FieldElement[][]) MathArrays.buildArray(extractField, i2 * i3, -1);
        int i5 = 0;
        int i6 = 0;
        while (i5 < i2) {
            int i7 = i5 * 36;
            int min = FastMath.min(i7 + 36, length);
            int i8 = min - i7;
            int i9 = i;
            while (i9 < i3) {
                int i10 = i9 * 36;
                int min2 = FastMath.min(i10 + 36, length2) - i10;
                T[] tArr4 = (FieldElement[]) MathArrays.buildArray(extractField, i8 * min2);
                tArr3[i6] = tArr4;
                int i11 = length;
                int i12 = length2;
                int i13 = i7;
                int i14 = 0;
                while (i13 < min) {
                    System.arraycopy(tArr2[i13], i10, tArr4, i14, min2);
                    i14 += min2;
                    i13++;
                    i2 = i2;
                }
                int i15 = i2;
                i6++;
                i9++;
                length = i11;
                length2 = i12;
            }
            int i16 = length;
            int i17 = length2;
            int i18 = i2;
            i5++;
            i = 0;
        }
        return tArr3;
    }

    public static <T extends FieldElement<T>> T[][] createBlocksLayout(Field<T> field, int i, int i2) {
        int i3 = (i + 35) / 36;
        int i4 = (i2 + 35) / 36;
        T[][] tArr = (FieldElement[][]) MathArrays.buildArray(field, i3 * i4, -1);
        int i5 = 0;
        for (int i6 = 0; i6 < i3; i6++) {
            int i7 = i6 * 36;
            int min = FastMath.min(i7 + 36, i) - i7;
            for (int i8 = 0; i8 < i4; i8++) {
                int i9 = i8 * 36;
                tArr[i5] = (FieldElement[]) MathArrays.buildArray(field, (FastMath.min(i9 + 36, i2) - i9) * min);
                i5++;
            }
        }
        return tArr;
    }

    public FieldMatrix<T> createMatrix(int i, int i2) throws NotStrictlyPositiveException {
        return new BlockFieldMatrix(getField(), i, i2);
    }

    public FieldMatrix<T> copy() {
        BlockFieldMatrix blockFieldMatrix = new BlockFieldMatrix(getField(), this.rows, this.columns);
        int i = 0;
        while (true) {
            T[][] tArr = this.blocks;
            if (i >= tArr.length) {
                return blockFieldMatrix;
            }
            T[] tArr2 = tArr[i];
            System.arraycopy(tArr2, 0, blockFieldMatrix.blocks[i], 0, tArr2.length);
            i++;
        }
    }

    public FieldMatrix<T> add(FieldMatrix<T> fieldMatrix) throws MatrixDimensionMismatchException {
        FieldMatrix<T> fieldMatrix2 = fieldMatrix;
        try {
            return add((BlockFieldMatrix) fieldMatrix2);
        } catch (ClassCastException unused) {
            checkAdditionCompatible(fieldMatrix);
            BlockFieldMatrix blockFieldMatrix = new BlockFieldMatrix(getField(), this.rows, this.columns);
            int i = 0;
            for (int i2 = 0; i2 < blockFieldMatrix.blockRows; i2++) {
                for (int i3 = 0; i3 < blockFieldMatrix.blockColumns; i3++) {
                    T[] tArr = blockFieldMatrix.blocks[i];
                    T[] tArr2 = this.blocks[i];
                    int i4 = i2 * 36;
                    int min = FastMath.min(i4 + 36, this.rows);
                    int i5 = i3 * 36;
                    int min2 = FastMath.min(i5 + 36, this.columns);
                    int i6 = 0;
                    while (i4 < min) {
                        for (int i7 = i5; i7 < min2; i7++) {
                            tArr[i6] = (FieldElement) tArr2[i6].add(fieldMatrix2.getEntry(i4, i7));
                            i6++;
                        }
                        i4++;
                    }
                    i++;
                }
            }
            return blockFieldMatrix;
        }
    }

    public BlockFieldMatrix<T> add(BlockFieldMatrix<T> blockFieldMatrix) throws MatrixDimensionMismatchException {
        checkAdditionCompatible(blockFieldMatrix);
        BlockFieldMatrix<T> blockFieldMatrix2 = new BlockFieldMatrix<>(getField(), this.rows, this.columns);
        int i = 0;
        while (true) {
            T[][] tArr = blockFieldMatrix2.blocks;
            if (i >= tArr.length) {
                return blockFieldMatrix2;
            }
            T[] tArr2 = tArr[i];
            T[] tArr3 = this.blocks[i];
            T[] tArr4 = blockFieldMatrix.blocks[i];
            for (int i2 = 0; i2 < tArr2.length; i2++) {
                tArr2[i2] = (FieldElement) tArr3[i2].add(tArr4[i2]);
            }
            i++;
        }
    }

    public FieldMatrix<T> subtract(FieldMatrix<T> fieldMatrix) throws MatrixDimensionMismatchException {
        FieldMatrix<T> fieldMatrix2 = fieldMatrix;
        try {
            return subtract((BlockFieldMatrix) fieldMatrix2);
        } catch (ClassCastException unused) {
            checkSubtractionCompatible(fieldMatrix);
            BlockFieldMatrix blockFieldMatrix = new BlockFieldMatrix(getField(), this.rows, this.columns);
            int i = 0;
            for (int i2 = 0; i2 < blockFieldMatrix.blockRows; i2++) {
                for (int i3 = 0; i3 < blockFieldMatrix.blockColumns; i3++) {
                    T[] tArr = blockFieldMatrix.blocks[i];
                    T[] tArr2 = this.blocks[i];
                    int i4 = i2 * 36;
                    int min = FastMath.min(i4 + 36, this.rows);
                    int i5 = i3 * 36;
                    int min2 = FastMath.min(i5 + 36, this.columns);
                    int i6 = 0;
                    while (i4 < min) {
                        for (int i7 = i5; i7 < min2; i7++) {
                            tArr[i6] = (FieldElement) tArr2[i6].subtract(fieldMatrix2.getEntry(i4, i7));
                            i6++;
                        }
                        i4++;
                    }
                    i++;
                }
            }
            return blockFieldMatrix;
        }
    }

    public BlockFieldMatrix<T> subtract(BlockFieldMatrix<T> blockFieldMatrix) throws MatrixDimensionMismatchException {
        checkSubtractionCompatible(blockFieldMatrix);
        BlockFieldMatrix<T> blockFieldMatrix2 = new BlockFieldMatrix<>(getField(), this.rows, this.columns);
        int i = 0;
        while (true) {
            T[][] tArr = blockFieldMatrix2.blocks;
            if (i >= tArr.length) {
                return blockFieldMatrix2;
            }
            T[] tArr2 = tArr[i];
            T[] tArr3 = this.blocks[i];
            T[] tArr4 = blockFieldMatrix.blocks[i];
            for (int i2 = 0; i2 < tArr2.length; i2++) {
                tArr2[i2] = (FieldElement) tArr3[i2].subtract(tArr4[i2]);
            }
            i++;
        }
    }

    public FieldMatrix<T> scalarAdd(T t) {
        BlockFieldMatrix blockFieldMatrix = new BlockFieldMatrix(getField(), this.rows, this.columns);
        int i = 0;
        while (true) {
            T[][] tArr = blockFieldMatrix.blocks;
            if (i >= tArr.length) {
                return blockFieldMatrix;
            }
            T[] tArr2 = tArr[i];
            T[] tArr3 = this.blocks[i];
            for (int i2 = 0; i2 < tArr2.length; i2++) {
                tArr2[i2] = (FieldElement) tArr3[i2].add(t);
            }
            i++;
        }
    }

    public FieldMatrix<T> scalarMultiply(T t) {
        BlockFieldMatrix blockFieldMatrix = new BlockFieldMatrix(getField(), this.rows, this.columns);
        int i = 0;
        while (true) {
            T[][] tArr = blockFieldMatrix.blocks;
            if (i >= tArr.length) {
                return blockFieldMatrix;
            }
            T[] tArr2 = tArr[i];
            T[] tArr3 = this.blocks[i];
            for (int i2 = 0; i2 < tArr2.length; i2++) {
                tArr2[i2] = (FieldElement) tArr3[i2].multiply(t);
            }
            i++;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v18, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v8, resolved type: org.apache.commons.math3.FieldElement} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.commons.math3.linear.FieldMatrix<T> multiply(org.apache.commons.math3.linear.FieldMatrix<T> r26) throws org.apache.commons.math3.exception.DimensionMismatchException {
        /*
            r25 = this;
            r0 = r25
            r1 = r26
            r2 = r1
            org.apache.commons.math3.linear.BlockFieldMatrix r2 = (org.apache.commons.math3.linear.BlockFieldMatrix) r2     // Catch:{ ClassCastException -> 0x000c }
            org.apache.commons.math3.linear.BlockFieldMatrix r1 = r0.multiply(r2)     // Catch:{ ClassCastException -> 0x000c }
            return r1
        L_0x000c:
            r25.checkMultiplicationCompatible(r26)
            org.apache.commons.math3.linear.BlockFieldMatrix r2 = new org.apache.commons.math3.linear.BlockFieldMatrix
            org.apache.commons.math3.Field r3 = r25.getField()
            int r4 = r0.rows
            int r5 = r26.getColumnDimension()
            r2.<init>(r3, r4, r5)
            org.apache.commons.math3.Field r3 = r25.getField()
            java.lang.Object r3 = r3.getZero()
            org.apache.commons.math3.FieldElement r3 = (org.apache.commons.math3.FieldElement) r3
            r5 = 0
            r6 = 0
        L_0x002a:
            int r7 = r2.blockRows
            if (r5 >= r7) goto L_0x00ed
            int r7 = r5 * 36
            int r8 = r7 + 36
            int r9 = r0.rows
            int r8 = org.apache.commons.math3.util.FastMath.min((int) r8, (int) r9)
            r9 = 0
        L_0x0039:
            int r10 = r2.blockColumns
            if (r9 >= r10) goto L_0x00e5
            int r10 = r9 * 36
            int r11 = r10 + 36
            int r12 = r26.getColumnDimension()
            int r11 = org.apache.commons.math3.util.FastMath.min((int) r11, (int) r12)
            T[][] r12 = r2.blocks
            r12 = r12[r6]
            r13 = 0
        L_0x004e:
            int r14 = r0.blockColumns
            if (r13 >= r14) goto L_0x00d7
            int r14 = r0.blockWidth(r13)
            T[][] r15 = r0.blocks
            int r4 = r0.blockColumns
            int r4 = r4 * r5
            int r4 = r4 + r13
            r4 = r15[r4]
            int r15 = r13 * 36
            r0 = r7
            r16 = 0
        L_0x0063:
            if (r0 >= r8) goto L_0x00c7
            int r17 = r0 - r7
            int r17 = r17 * r14
            r18 = r3
            int r3 = r17 + r14
            r19 = r7
            r7 = r10
        L_0x0070:
            if (r7 >= r11) goto L_0x00b8
            r20 = r8
            r21 = r10
            r22 = r11
            r11 = r15
            r8 = r17
            r10 = r18
        L_0x007d:
            if (r8 >= r3) goto L_0x009d
            r23 = r3
            r3 = r4[r8]
            r24 = r4
            org.apache.commons.math3.FieldElement r4 = r1.getEntry(r11, r7)
            java.lang.Object r3 = r3.multiply(r4)
            java.lang.Object r3 = r10.add(r3)
            r10 = r3
            org.apache.commons.math3.FieldElement r10 = (org.apache.commons.math3.FieldElement) r10
            int r11 = r11 + 1
            int r8 = r8 + 1
            r3 = r23
            r4 = r24
            goto L_0x007d
        L_0x009d:
            r23 = r3
            r24 = r4
            r3 = r12[r16]
            java.lang.Object r3 = r3.add(r10)
            org.apache.commons.math3.FieldElement r3 = (org.apache.commons.math3.FieldElement) r3
            r12[r16] = r3
            int r16 = r16 + 1
            int r7 = r7 + 1
            r8 = r20
            r10 = r21
            r11 = r22
            r3 = r23
            goto L_0x0070
        L_0x00b8:
            r24 = r4
            r20 = r8
            r21 = r10
            r22 = r11
            int r0 = r0 + 1
            r3 = r18
            r7 = r19
            goto L_0x0063
        L_0x00c7:
            r18 = r3
            r19 = r7
            r20 = r8
            r21 = r10
            r22 = r11
            int r13 = r13 + 1
            r0 = r25
            goto L_0x004e
        L_0x00d7:
            r18 = r3
            r19 = r7
            r20 = r8
            int r6 = r6 + 1
            int r9 = r9 + 1
            r0 = r25
            goto L_0x0039
        L_0x00e5:
            r18 = r3
            int r5 = r5 + 1
            r0 = r25
            goto L_0x002a
        L_0x00ed:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.linear.BlockFieldMatrix.multiply(org.apache.commons.math3.linear.FieldMatrix):org.apache.commons.math3.linear.FieldMatrix");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v12, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v10, resolved type: org.apache.commons.math3.FieldElement} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v23, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v21, resolved type: org.apache.commons.math3.FieldElement} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.commons.math3.linear.BlockFieldMatrix<T> multiply(org.apache.commons.math3.linear.BlockFieldMatrix<T> r27) throws org.apache.commons.math3.exception.DimensionMismatchException {
        /*
            r26 = this;
            r0 = r26
            r1 = r27
            r26.checkMultiplicationCompatible(r27)
            org.apache.commons.math3.linear.BlockFieldMatrix r2 = new org.apache.commons.math3.linear.BlockFieldMatrix
            org.apache.commons.math3.Field r3 = r26.getField()
            int r4 = r0.rows
            int r5 = r1.columns
            r2.<init>(r3, r4, r5)
            org.apache.commons.math3.Field r3 = r26.getField()
            java.lang.Object r3 = r3.getZero()
            org.apache.commons.math3.FieldElement r3 = (org.apache.commons.math3.FieldElement) r3
            r5 = 0
            r6 = 0
        L_0x0020:
            int r7 = r2.blockRows
            if (r5 >= r7) goto L_0x0147
            int r7 = r5 * 36
            int r8 = r7 + 36
            int r9 = r0.rows
            int r8 = org.apache.commons.math3.util.FastMath.min((int) r8, (int) r9)
            r9 = 0
        L_0x002f:
            int r10 = r2.blockColumns
            if (r9 >= r10) goto L_0x0139
            int r10 = r2.blockWidth(r9)
            int r11 = r10 + r10
            int r12 = r11 + r10
            int r13 = r12 + r10
            T[][] r14 = r2.blocks
            r14 = r14[r6]
            r15 = 0
        L_0x0042:
            int r4 = r0.blockColumns
            if (r15 >= r4) goto L_0x0123
            int r4 = r0.blockWidth(r15)
            r16 = r3
            T[][] r3 = r0.blocks
            r17 = r2
            int r2 = r0.blockColumns
            int r2 = r2 * r5
            int r2 = r2 + r15
            r2 = r3[r2]
            T[][] r3 = r1.blocks
            int r0 = r1.blockColumns
            int r0 = r0 * r15
            int r0 = r0 + r9
            r0 = r3[r0]
            r3 = r7
            r18 = 0
        L_0x0061:
            if (r3 >= r8) goto L_0x010f
            int r19 = r3 - r7
            int r19 = r19 * r4
            int r1 = r19 + r4
            r20 = r4
            r4 = 0
        L_0x006c:
            if (r4 >= r10) goto L_0x00ff
            r23 = r4
            r21 = r7
            r22 = r8
            r8 = r16
            r7 = r19
        L_0x0078:
            r24 = r5
            int r5 = r1 + -3
            if (r7 >= r5) goto L_0x00ce
            r5 = r2[r7]
            r25 = r9
            r9 = r0[r23]
            java.lang.Object r5 = r5.multiply(r9)
            java.lang.Object r5 = r8.add(r5)
            org.apache.commons.math3.FieldElement r5 = (org.apache.commons.math3.FieldElement) r5
            int r8 = r7 + 1
            r8 = r2[r8]
            int r9 = r23 + r10
            r9 = r0[r9]
            java.lang.Object r8 = r8.multiply(r9)
            java.lang.Object r5 = r5.add(r8)
            org.apache.commons.math3.FieldElement r5 = (org.apache.commons.math3.FieldElement) r5
            int r8 = r7 + 2
            r8 = r2[r8]
            int r9 = r23 + r11
            r9 = r0[r9]
            java.lang.Object r8 = r8.multiply(r9)
            java.lang.Object r5 = r5.add(r8)
            org.apache.commons.math3.FieldElement r5 = (org.apache.commons.math3.FieldElement) r5
            int r8 = r7 + 3
            r8 = r2[r8]
            int r9 = r23 + r12
            r9 = r0[r9]
            java.lang.Object r8 = r8.multiply(r9)
            java.lang.Object r5 = r5.add(r8)
            r8 = r5
            org.apache.commons.math3.FieldElement r8 = (org.apache.commons.math3.FieldElement) r8
            int r7 = r7 + 4
            int r23 = r23 + r13
            r5 = r24
            r9 = r25
            goto L_0x0078
        L_0x00ce:
            r25 = r9
        L_0x00d0:
            if (r7 >= r1) goto L_0x00e7
            int r5 = r7 + 1
            r7 = r2[r7]
            r9 = r0[r23]
            java.lang.Object r7 = r7.multiply(r9)
            java.lang.Object r7 = r8.add(r7)
            r8 = r7
            org.apache.commons.math3.FieldElement r8 = (org.apache.commons.math3.FieldElement) r8
            int r23 = r23 + r10
            r7 = r5
            goto L_0x00d0
        L_0x00e7:
            r5 = r14[r18]
            java.lang.Object r5 = r5.add(r8)
            org.apache.commons.math3.FieldElement r5 = (org.apache.commons.math3.FieldElement) r5
            r14[r18] = r5
            int r18 = r18 + 1
            int r4 = r4 + 1
            r7 = r21
            r8 = r22
            r5 = r24
            r9 = r25
            goto L_0x006c
        L_0x00ff:
            r24 = r5
            r21 = r7
            r22 = r8
            r25 = r9
            int r3 = r3 + 1
            r1 = r27
            r4 = r20
            goto L_0x0061
        L_0x010f:
            r24 = r5
            r21 = r7
            r22 = r8
            r25 = r9
            int r15 = r15 + 1
            r0 = r26
            r1 = r27
            r3 = r16
            r2 = r17
            goto L_0x0042
        L_0x0123:
            r17 = r2
            r16 = r3
            r24 = r5
            r21 = r7
            r22 = r8
            r25 = r9
            int r6 = r6 + 1
            int r9 = r25 + 1
            r0 = r26
            r1 = r27
            goto L_0x002f
        L_0x0139:
            r17 = r2
            r16 = r3
            r24 = r5
            int r5 = r24 + 1
            r0 = r26
            r1 = r27
            goto L_0x0020
        L_0x0147:
            r17 = r2
            return r17
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.linear.BlockFieldMatrix.multiply(org.apache.commons.math3.linear.BlockFieldMatrix):org.apache.commons.math3.linear.BlockFieldMatrix");
    }

    public T[][] getData() {
        T[][] tArr = (FieldElement[][]) MathArrays.buildArray(getField(), getRowDimension(), getColumnDimension());
        int i = this.columns - ((this.blockColumns - 1) * 36);
        for (int i2 = 0; i2 < this.blockRows; i2++) {
            int i3 = i2 * 36;
            int min = FastMath.min(i3 + 36, this.rows);
            int i4 = 0;
            int i5 = 0;
            while (i3 < min) {
                T[] tArr2 = tArr[i3];
                int i6 = this.blockColumns * i2;
                int i7 = 0;
                int i8 = 0;
                while (i7 < this.blockColumns - 1) {
                    System.arraycopy(this.blocks[i6], i4, tArr2, i8, 36);
                    i8 += 36;
                    i7++;
                    i6++;
                }
                System.arraycopy(this.blocks[i6], i5, tArr2, i8, i);
                i4 += 36;
                i5 += i;
                i3++;
            }
        }
        return tArr;
    }

    public FieldMatrix<T> getSubMatrix(int i, int i2, int i3, int i4) throws OutOfRangeException, NumberIsTooSmallException {
        int i5;
        int i6;
        int i7;
        checkSubMatrixIndex(i, i2, i3, i4);
        BlockFieldMatrix blockFieldMatrix = new BlockFieldMatrix(getField(), (i2 - i) + 1, (i4 - i3) + 1);
        int i8 = i % 36;
        int i9 = i3 / 36;
        int i10 = i3 % 36;
        int i11 = i / 36;
        int i12 = 0;
        while (i12 < blockFieldMatrix.blockRows) {
            int blockHeight = blockFieldMatrix.blockHeight(i12);
            int i13 = i9;
            int i14 = 0;
            while (i14 < blockFieldMatrix.blockColumns) {
                int blockWidth = blockFieldMatrix.blockWidth(i14);
                T[] tArr = blockFieldMatrix.blocks[(blockFieldMatrix.blockColumns * i12) + i14];
                int i15 = (this.blockColumns * i11) + i13;
                int blockWidth2 = blockWidth(i13);
                int i16 = blockHeight + i8;
                int i17 = i16 - 36;
                int i18 = blockWidth + i10;
                int i19 = i18 - 36;
                if (i17 <= 0) {
                    i7 = i13;
                    i6 = i14;
                    i5 = i12;
                    if (i19 > 0) {
                        int blockWidth3 = blockWidth(i7 + 1);
                        int i20 = i8;
                        int i21 = i16;
                        T[] tArr2 = tArr;
                        int i22 = blockWidth;
                        copyBlockPart(this.blocks[i15], blockWidth2, i20, i21, i10, 36, tArr2, i22, 0, 0);
                        copyBlockPart(this.blocks[i15 + 1], blockWidth3, i20, i21, 0, i19, tArr2, i22, 0, blockWidth - i19);
                    } else {
                        copyBlockPart(this.blocks[i15], blockWidth2, i8, i16, i10, i18, tArr, blockWidth, 0, 0);
                    }
                } else if (i19 > 0) {
                    int blockWidth4 = blockWidth(i13 + 1);
                    int i23 = i8;
                    T[] tArr3 = tArr;
                    i7 = i13;
                    int i24 = blockWidth;
                    i6 = i14;
                    i5 = i12;
                    copyBlockPart(this.blocks[i15], blockWidth2, i23, 36, i10, 36, tArr3, i24, 0, 0);
                    int i25 = blockWidth - i19;
                    copyBlockPart(this.blocks[i15 + 1], blockWidth4, i23, 36, 0, i19, tArr3, i24, 0, i25);
                    int i26 = i17;
                    int i27 = blockHeight - i17;
                    copyBlockPart(this.blocks[i15 + this.blockColumns], blockWidth2, 0, i26, i10, 36, tArr3, i24, i27, 0);
                    copyBlockPart(this.blocks[i15 + this.blockColumns + 1], blockWidth4, 0, i26, 0, i19, tArr3, i24, i27, i25);
                } else {
                    i7 = i13;
                    i6 = i14;
                    i5 = i12;
                    int i28 = blockWidth2;
                    int i29 = i10;
                    int i30 = i18;
                    T[] tArr4 = tArr;
                    int i31 = blockWidth;
                    copyBlockPart(this.blocks[i15], i28, i8, 36, i29, i30, tArr4, i31, 0, 0);
                    copyBlockPart(this.blocks[i15 + this.blockColumns], i28, 0, i17, i29, i30, tArr4, i31, blockHeight - i17, 0);
                }
                i13 = i7 + 1;
                i14 = i6 + 1;
                i12 = i5;
            }
            i11++;
            i12++;
        }
        return blockFieldMatrix;
    }

    private void copyBlockPart(T[] tArr, int i, int i2, int i3, int i4, int i5, T[] tArr2, int i6, int i7, int i8) {
        int i9 = i5 - i4;
        int i10 = (i2 * i) + i4;
        int i11 = (i7 * i6) + i8;
        while (i2 < i3) {
            System.arraycopy(tArr, i10, tArr2, i11, i9);
            i10 += i;
            i11 += i6;
            i2++;
        }
    }

    public void setSubMatrix(T[][] tArr, int i, int i2) throws DimensionMismatchException, OutOfRangeException, NoDataException, NullArgumentException {
        BlockFieldMatrix blockFieldMatrix = this;
        T[][] tArr2 = tArr;
        int i3 = i;
        int i4 = i2;
        MathUtils.checkNotNull(tArr);
        int i5 = 0;
        int length = tArr2[0].length;
        if (length != 0) {
            int length2 = tArr2.length + i3;
            int i6 = i4 + length;
            blockFieldMatrix.checkSubMatrixIndex(i3, length2 - 1, i4, i6 - 1);
            int length3 = tArr2.length;
            while (i5 < length3) {
                T[] tArr3 = tArr2[i5];
                if (tArr3.length == length) {
                    i5++;
                } else {
                    throw new DimensionMismatchException(length, tArr3.length);
                }
            }
            int i7 = i3 / 36;
            int i8 = (length2 + 35) / 36;
            int i9 = i4 / 36;
            int i10 = (i6 + 35) / 36;
            while (i7 < i8) {
                int blockHeight = blockFieldMatrix.blockHeight(i7);
                int i11 = i7 * 36;
                int max = FastMath.max(i3, i11);
                int min = FastMath.min(length2, blockHeight + i11);
                int i12 = i9;
                while (i12 < i10) {
                    int blockWidth = blockFieldMatrix.blockWidth(i12);
                    int i13 = i12 * 36;
                    int max2 = FastMath.max(i4, i13);
                    int i14 = i8;
                    int min2 = FastMath.min(i6, i13 + blockWidth) - max2;
                    int i15 = length2;
                    int i16 = i9;
                    T[] tArr4 = blockFieldMatrix.blocks[(blockFieldMatrix.blockColumns * i7) + i12];
                    int i17 = max;
                    while (i17 < min) {
                        System.arraycopy(tArr2[i17 - i3], max2 - i4, tArr4, ((i17 - i11) * blockWidth) + (max2 - i13), min2);
                        i17++;
                        tArr2 = tArr;
                        i3 = i;
                    }
                    i12++;
                    blockFieldMatrix = this;
                    tArr2 = tArr;
                    i3 = i;
                    i8 = i14;
                    length2 = i15;
                    i9 = i16;
                }
                int i18 = i8;
                int i19 = length2;
                int i20 = i9;
                i7++;
                blockFieldMatrix = this;
                tArr2 = tArr;
                i3 = i;
            }
            return;
        }
        throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN);
    }

    public FieldMatrix<T> getRowMatrix(int i) throws OutOfRangeException {
        checkRowIndex(i);
        BlockFieldMatrix blockFieldMatrix = new BlockFieldMatrix(getField(), 1, this.columns);
        int i2 = i / 36;
        int i3 = i - (i2 * 36);
        T[] tArr = blockFieldMatrix.blocks[0];
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < this.blockColumns; i6++) {
            int blockWidth = blockWidth(i6);
            T[] tArr2 = this.blocks[(this.blockColumns * i2) + i6];
            int length = tArr.length - i4;
            if (blockWidth > length) {
                int i7 = i3 * blockWidth;
                System.arraycopy(tArr2, i7, tArr, i4, length);
                i5++;
                tArr = blockFieldMatrix.blocks[i5];
                int i8 = blockWidth - length;
                System.arraycopy(tArr2, i7, tArr, 0, i8);
                i4 = i8;
            } else {
                System.arraycopy(tArr2, i3 * blockWidth, tArr, i4, blockWidth);
                i4 += blockWidth;
            }
        }
        return blockFieldMatrix;
    }

    public void setRowMatrix(int i, FieldMatrix<T> fieldMatrix) throws MatrixDimensionMismatchException, OutOfRangeException {
        try {
            setRowMatrix(i, (BlockFieldMatrix) fieldMatrix);
        } catch (ClassCastException unused) {
            super.setRowMatrix(i, fieldMatrix);
        }
    }

    public void setRowMatrix(int i, BlockFieldMatrix<T> blockFieldMatrix) throws MatrixDimensionMismatchException, OutOfRangeException {
        checkRowIndex(i);
        int columnDimension = getColumnDimension();
        if (blockFieldMatrix.getRowDimension() == 1 && blockFieldMatrix.getColumnDimension() == columnDimension) {
            int i2 = i / 36;
            int i3 = i - (i2 * 36);
            T[] tArr = blockFieldMatrix.blocks[0];
            int i4 = 0;
            int i5 = 0;
            for (int i6 = 0; i6 < this.blockColumns; i6++) {
                int blockWidth = blockWidth(i6);
                T[] tArr2 = this.blocks[(this.blockColumns * i2) + i6];
                int length = tArr.length - i4;
                if (blockWidth > length) {
                    int i7 = i3 * blockWidth;
                    System.arraycopy(tArr, i4, tArr2, i7, length);
                    i5++;
                    tArr = blockFieldMatrix.blocks[i5];
                    int i8 = blockWidth - length;
                    System.arraycopy(tArr, 0, tArr2, i7, i8);
                    i4 = i8;
                } else {
                    System.arraycopy(tArr, i4, tArr2, i3 * blockWidth, blockWidth);
                    i4 += blockWidth;
                }
            }
            return;
        }
        throw new MatrixDimensionMismatchException(blockFieldMatrix.getRowDimension(), blockFieldMatrix.getColumnDimension(), 1, columnDimension);
    }

    public FieldMatrix<T> getColumnMatrix(int i) throws OutOfRangeException {
        checkColumnIndex(i);
        BlockFieldMatrix blockFieldMatrix = new BlockFieldMatrix(getField(), this.rows, 1);
        int i2 = i / 36;
        int i3 = i - (i2 * 36);
        int blockWidth = blockWidth(i2);
        T[] tArr = blockFieldMatrix.blocks[0];
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < this.blockRows; i6++) {
            int blockHeight = blockHeight(i6);
            T[] tArr2 = this.blocks[(this.blockColumns * i6) + i2];
            int i7 = 0;
            while (i7 < blockHeight) {
                if (i4 >= tArr.length) {
                    i5++;
                    tArr = blockFieldMatrix.blocks[i5];
                    i4 = 0;
                }
                tArr[i4] = tArr2[(i7 * blockWidth) + i3];
                i7++;
                i4++;
            }
        }
        return blockFieldMatrix;
    }

    public void setColumnMatrix(int i, FieldMatrix<T> fieldMatrix) throws MatrixDimensionMismatchException, OutOfRangeException {
        try {
            setColumnMatrix(i, (BlockFieldMatrix) fieldMatrix);
        } catch (ClassCastException unused) {
            super.setColumnMatrix(i, fieldMatrix);
        }
    }

    /* access modifiers changed from: package-private */
    public void setColumnMatrix(int i, BlockFieldMatrix<T> blockFieldMatrix) throws MatrixDimensionMismatchException, OutOfRangeException {
        checkColumnIndex(i);
        int rowDimension = getRowDimension();
        if (blockFieldMatrix.getRowDimension() == rowDimension && blockFieldMatrix.getColumnDimension() == 1) {
            int i2 = i / 36;
            int i3 = i - (i2 * 36);
            int blockWidth = blockWidth(i2);
            T[] tArr = blockFieldMatrix.blocks[0];
            int i4 = 0;
            int i5 = 0;
            for (int i6 = 0; i6 < this.blockRows; i6++) {
                int blockHeight = blockHeight(i6);
                T[] tArr2 = this.blocks[(this.blockColumns * i6) + i2];
                int i7 = 0;
                while (i7 < blockHeight) {
                    if (i4 >= tArr.length) {
                        i5++;
                        tArr = blockFieldMatrix.blocks[i5];
                        i4 = 0;
                    }
                    tArr2[(i7 * blockWidth) + i3] = tArr[i4];
                    i7++;
                    i4++;
                }
            }
            return;
        }
        throw new MatrixDimensionMismatchException(blockFieldMatrix.getRowDimension(), blockFieldMatrix.getColumnDimension(), rowDimension, 1);
    }

    public FieldVector<T> getRowVector(int i) throws OutOfRangeException {
        checkRowIndex(i);
        FieldElement[] fieldElementArr = (FieldElement[]) MathArrays.buildArray(getField(), this.columns);
        int i2 = i / 36;
        int i3 = i - (i2 * 36);
        int i4 = 0;
        for (int i5 = 0; i5 < this.blockColumns; i5++) {
            int blockWidth = blockWidth(i5);
            System.arraycopy(this.blocks[(this.blockColumns * i2) + i5], i3 * blockWidth, fieldElementArr, i4, blockWidth);
            i4 += blockWidth;
        }
        return new ArrayFieldVector(getField(), (T[]) fieldElementArr, false);
    }

    public void setRowVector(int i, FieldVector<T> fieldVector) throws MatrixDimensionMismatchException, OutOfRangeException {
        try {
            setRow(i, ((ArrayFieldVector) fieldVector).getDataRef());
        } catch (ClassCastException unused) {
            super.setRowVector(i, fieldVector);
        }
    }

    public FieldVector<T> getColumnVector(int i) throws OutOfRangeException {
        checkColumnIndex(i);
        FieldElement[] fieldElementArr = (FieldElement[]) MathArrays.buildArray(getField(), this.rows);
        int i2 = i / 36;
        int i3 = i - (i2 * 36);
        int blockWidth = blockWidth(i2);
        int i4 = 0;
        for (int i5 = 0; i5 < this.blockRows; i5++) {
            int blockHeight = blockHeight(i5);
            T[] tArr = this.blocks[(this.blockColumns * i5) + i2];
            int i6 = 0;
            while (i6 < blockHeight) {
                fieldElementArr[i4] = tArr[(i6 * blockWidth) + i3];
                i6++;
                i4++;
            }
        }
        return new ArrayFieldVector(getField(), (T[]) fieldElementArr, false);
    }

    public void setColumnVector(int i, FieldVector<T> fieldVector) throws OutOfRangeException, MatrixDimensionMismatchException {
        try {
            setColumn(i, ((ArrayFieldVector) fieldVector).getDataRef());
        } catch (ClassCastException unused) {
            super.setColumnVector(i, fieldVector);
        }
    }

    public T[] getRow(int i) throws OutOfRangeException {
        checkRowIndex(i);
        T[] tArr = (FieldElement[]) MathArrays.buildArray(getField(), this.columns);
        int i2 = i / 36;
        int i3 = i - (i2 * 36);
        int i4 = 0;
        for (int i5 = 0; i5 < this.blockColumns; i5++) {
            int blockWidth = blockWidth(i5);
            System.arraycopy(this.blocks[(this.blockColumns * i2) + i5], i3 * blockWidth, tArr, i4, blockWidth);
            i4 += blockWidth;
        }
        return tArr;
    }

    public void setRow(int i, T[] tArr) throws OutOfRangeException, MatrixDimensionMismatchException {
        checkRowIndex(i);
        int columnDimension = getColumnDimension();
        if (tArr.length == columnDimension) {
            int i2 = i / 36;
            int i3 = i - (i2 * 36);
            int i4 = 0;
            for (int i5 = 0; i5 < this.blockColumns; i5++) {
                int blockWidth = blockWidth(i5);
                System.arraycopy(tArr, i4, this.blocks[(this.blockColumns * i2) + i5], i3 * blockWidth, blockWidth);
                i4 += blockWidth;
            }
            return;
        }
        throw new MatrixDimensionMismatchException(1, tArr.length, 1, columnDimension);
    }

    public T[] getColumn(int i) throws OutOfRangeException {
        checkColumnIndex(i);
        T[] tArr = (FieldElement[]) MathArrays.buildArray(getField(), this.rows);
        int i2 = i / 36;
        int i3 = i - (i2 * 36);
        int blockWidth = blockWidth(i2);
        int i4 = 0;
        for (int i5 = 0; i5 < this.blockRows; i5++) {
            int blockHeight = blockHeight(i5);
            T[] tArr2 = this.blocks[(this.blockColumns * i5) + i2];
            int i6 = 0;
            while (i6 < blockHeight) {
                tArr[i4] = tArr2[(i6 * blockWidth) + i3];
                i6++;
                i4++;
            }
        }
        return tArr;
    }

    public void setColumn(int i, T[] tArr) throws MatrixDimensionMismatchException, OutOfRangeException {
        checkColumnIndex(i);
        int rowDimension = getRowDimension();
        if (tArr.length == rowDimension) {
            int i2 = i / 36;
            int i3 = i - (i2 * 36);
            int blockWidth = blockWidth(i2);
            int i4 = 0;
            for (int i5 = 0; i5 < this.blockRows; i5++) {
                int blockHeight = blockHeight(i5);
                T[] tArr2 = this.blocks[(this.blockColumns * i5) + i2];
                int i6 = 0;
                while (i6 < blockHeight) {
                    tArr2[(i6 * blockWidth) + i3] = tArr[i4];
                    i6++;
                    i4++;
                }
            }
            return;
        }
        throw new MatrixDimensionMismatchException(tArr.length, 1, rowDimension, 1);
    }

    public T getEntry(int i, int i2) throws OutOfRangeException {
        checkRowIndex(i);
        checkColumnIndex(i2);
        int i3 = i / 36;
        int i4 = i2 / 36;
        return this.blocks[(i3 * this.blockColumns) + i4][((i - (i3 * 36)) * blockWidth(i4)) + (i2 - (i4 * 36))];
    }

    public void setEntry(int i, int i2, T t) throws OutOfRangeException {
        checkRowIndex(i);
        checkColumnIndex(i2);
        int i3 = i / 36;
        int i4 = i2 / 36;
        this.blocks[(i3 * this.blockColumns) + i4][((i - (i3 * 36)) * blockWidth(i4)) + (i2 - (i4 * 36))] = t;
    }

    public void addToEntry(int i, int i2, T t) throws OutOfRangeException {
        checkRowIndex(i);
        checkColumnIndex(i2);
        int i3 = i / 36;
        int i4 = i2 / 36;
        int blockWidth = ((i - (i3 * 36)) * blockWidth(i4)) + (i2 - (i4 * 36));
        T[] tArr = this.blocks[(i3 * this.blockColumns) + i4];
        tArr[blockWidth] = (FieldElement) tArr[blockWidth].add(t);
    }

    public void multiplyEntry(int i, int i2, T t) throws OutOfRangeException {
        checkRowIndex(i);
        checkColumnIndex(i2);
        int i3 = i / 36;
        int i4 = i2 / 36;
        int blockWidth = ((i - (i3 * 36)) * blockWidth(i4)) + (i2 - (i4 * 36));
        T[] tArr = this.blocks[(i3 * this.blockColumns) + i4];
        tArr[blockWidth] = (FieldElement) tArr[blockWidth].multiply(t);
    }

    public FieldMatrix<T> transpose() {
        int rowDimension = getRowDimension();
        BlockFieldMatrix blockFieldMatrix = new BlockFieldMatrix(getField(), getColumnDimension(), rowDimension);
        int i = 0;
        for (int i2 = 0; i2 < this.blockColumns; i2++) {
            for (int i3 = 0; i3 < this.blockRows; i3++) {
                T[] tArr = blockFieldMatrix.blocks[i];
                T[] tArr2 = this.blocks[(this.blockColumns * i3) + i2];
                int i4 = i2 * 36;
                int min = FastMath.min(i4 + 36, this.columns);
                int i5 = i3 * 36;
                int min2 = FastMath.min(i5 + 36, this.rows);
                int i6 = 0;
                for (int i7 = i4; i7 < min; i7++) {
                    int i8 = min - i4;
                    int i9 = i7 - i4;
                    for (int i10 = i5; i10 < min2; i10++) {
                        tArr[i6] = tArr2[i9];
                        i6++;
                        i9 += i8;
                    }
                }
                i++;
            }
        }
        return blockFieldMatrix;
    }

    public int getRowDimension() {
        return this.rows;
    }

    public int getColumnDimension() {
        return this.columns;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v3, resolved type: org.apache.commons.math3.FieldElement} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v23, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v11, resolved type: org.apache.commons.math3.FieldElement} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public T[] operate(T[] r18) throws org.apache.commons.math3.exception.DimensionMismatchException {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            int r2 = r1.length
            int r3 = r0.columns
            if (r2 != r3) goto L_0x00d1
            org.apache.commons.math3.Field r2 = r17.getField()
            int r3 = r0.rows
            java.lang.Object[] r2 = org.apache.commons.math3.util.MathArrays.buildArray(r2, r3)
            org.apache.commons.math3.FieldElement[] r2 = (org.apache.commons.math3.FieldElement[]) r2
            org.apache.commons.math3.Field r3 = r17.getField()
            java.lang.Object r3 = r3.getZero()
            org.apache.commons.math3.FieldElement r3 = (org.apache.commons.math3.FieldElement) r3
            r5 = 0
        L_0x0020:
            int r6 = r0.blockRows
            if (r5 >= r6) goto L_0x00d0
            int r6 = r5 * 36
            int r7 = r6 + 36
            int r8 = r0.rows
            int r7 = org.apache.commons.math3.util.FastMath.min((int) r7, (int) r8)
            r8 = 0
        L_0x002f:
            int r9 = r0.blockColumns
            if (r8 >= r9) goto L_0x00ca
            T[][] r10 = r0.blocks
            int r9 = r9 * r5
            int r9 = r9 + r8
            r9 = r10[r9]
            int r10 = r8 * 36
            int r11 = r10 + 36
            int r12 = r0.columns
            int r11 = org.apache.commons.math3.util.FastMath.min((int) r11, (int) r12)
            r12 = r6
            r13 = 0
        L_0x0045:
            if (r12 >= r7) goto L_0x00c4
            r15 = r3
            r14 = r10
        L_0x0049:
            int r4 = r11 + -3
            if (r14 >= r4) goto L_0x009b
            r4 = r9[r13]
            r16 = r3
            r3 = r1[r14]
            java.lang.Object r3 = r4.multiply(r3)
            java.lang.Object r3 = r15.add(r3)
            org.apache.commons.math3.FieldElement r3 = (org.apache.commons.math3.FieldElement) r3
            int r4 = r13 + 1
            r4 = r9[r4]
            int r15 = r14 + 1
            r15 = r1[r15]
            java.lang.Object r4 = r4.multiply(r15)
            java.lang.Object r3 = r3.add(r4)
            org.apache.commons.math3.FieldElement r3 = (org.apache.commons.math3.FieldElement) r3
            int r4 = r13 + 2
            r4 = r9[r4]
            int r15 = r14 + 2
            r15 = r1[r15]
            java.lang.Object r4 = r4.multiply(r15)
            java.lang.Object r3 = r3.add(r4)
            org.apache.commons.math3.FieldElement r3 = (org.apache.commons.math3.FieldElement) r3
            int r4 = r13 + 3
            r4 = r9[r4]
            int r15 = r14 + 3
            r15 = r1[r15]
            java.lang.Object r4 = r4.multiply(r15)
            java.lang.Object r3 = r3.add(r4)
            r15 = r3
            org.apache.commons.math3.FieldElement r15 = (org.apache.commons.math3.FieldElement) r15
            int r13 = r13 + 4
            int r14 = r14 + 4
            r3 = r16
            goto L_0x0049
        L_0x009b:
            r16 = r3
        L_0x009d:
            if (r14 >= r11) goto L_0x00b5
            int r3 = r13 + 1
            r4 = r9[r13]
            int r13 = r14 + 1
            r14 = r1[r14]
            java.lang.Object r4 = r4.multiply(r14)
            java.lang.Object r4 = r15.add(r4)
            r15 = r4
            org.apache.commons.math3.FieldElement r15 = (org.apache.commons.math3.FieldElement) r15
            r14 = r13
            r13 = r3
            goto L_0x009d
        L_0x00b5:
            r3 = r2[r12]
            java.lang.Object r3 = r3.add(r15)
            org.apache.commons.math3.FieldElement r3 = (org.apache.commons.math3.FieldElement) r3
            r2[r12] = r3
            int r12 = r12 + 1
            r3 = r16
            goto L_0x0045
        L_0x00c4:
            r16 = r3
            int r8 = r8 + 1
            goto L_0x002f
        L_0x00ca:
            r16 = r3
            int r5 = r5 + 1
            goto L_0x0020
        L_0x00d0:
            return r2
        L_0x00d1:
            org.apache.commons.math3.exception.DimensionMismatchException r2 = new org.apache.commons.math3.exception.DimensionMismatchException
            int r1 = r1.length
            int r3 = r0.columns
            r2.<init>(r1, r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.linear.BlockFieldMatrix.operate(org.apache.commons.math3.FieldElement[]):org.apache.commons.math3.FieldElement[]");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v18, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v7, resolved type: org.apache.commons.math3.FieldElement} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public T[] preMultiply(T[] r22) throws org.apache.commons.math3.exception.DimensionMismatchException {
        /*
            r21 = this;
            r0 = r21
            r1 = r22
            int r2 = r1.length
            int r3 = r0.rows
            if (r2 != r3) goto L_0x00f0
            org.apache.commons.math3.Field r2 = r21.getField()
            int r3 = r0.columns
            java.lang.Object[] r2 = org.apache.commons.math3.util.MathArrays.buildArray(r2, r3)
            org.apache.commons.math3.FieldElement[] r2 = (org.apache.commons.math3.FieldElement[]) r2
            org.apache.commons.math3.Field r3 = r21.getField()
            java.lang.Object r3 = r3.getZero()
            org.apache.commons.math3.FieldElement r3 = (org.apache.commons.math3.FieldElement) r3
            r5 = 0
        L_0x0020:
            int r6 = r0.blockColumns
            if (r5 >= r6) goto L_0x00ef
            int r6 = r0.blockWidth(r5)
            int r7 = r6 + r6
            int r8 = r7 + r6
            int r9 = r8 + r6
            int r10 = r5 * 36
            int r11 = r10 + 36
            int r12 = r0.columns
            int r11 = org.apache.commons.math3.util.FastMath.min((int) r11, (int) r12)
            r12 = 0
        L_0x0039:
            int r13 = r0.blockRows
            if (r12 >= r13) goto L_0x00e9
            T[][] r13 = r0.blocks
            int r14 = r0.blockColumns
            int r14 = r14 * r12
            int r14 = r14 + r5
            r13 = r13[r14]
            int r14 = r12 * 36
            int r15 = r14 + 36
            int r4 = r0.rows
            int r4 = org.apache.commons.math3.util.FastMath.min((int) r15, (int) r4)
            r15 = r10
        L_0x0050:
            if (r15 >= r11) goto L_0x00df
            int r16 = r15 - r10
            r17 = r3
            r18 = r10
            r10 = r17
            r3 = r14
        L_0x005b:
            r19 = r11
            int r11 = r4 + -3
            if (r3 >= r11) goto L_0x00b0
            r11 = r13[r16]
            r20 = r14
            r14 = r1[r3]
            java.lang.Object r11 = r11.multiply(r14)
            java.lang.Object r10 = r10.add(r11)
            org.apache.commons.math3.FieldElement r10 = (org.apache.commons.math3.FieldElement) r10
            int r11 = r16 + r6
            r11 = r13[r11]
            int r14 = r3 + 1
            r14 = r1[r14]
            java.lang.Object r11 = r11.multiply(r14)
            java.lang.Object r10 = r10.add(r11)
            org.apache.commons.math3.FieldElement r10 = (org.apache.commons.math3.FieldElement) r10
            int r11 = r16 + r7
            r11 = r13[r11]
            int r14 = r3 + 2
            r14 = r1[r14]
            java.lang.Object r11 = r11.multiply(r14)
            java.lang.Object r10 = r10.add(r11)
            org.apache.commons.math3.FieldElement r10 = (org.apache.commons.math3.FieldElement) r10
            int r11 = r16 + r8
            r11 = r13[r11]
            int r14 = r3 + 3
            r14 = r1[r14]
            java.lang.Object r11 = r11.multiply(r14)
            java.lang.Object r10 = r10.add(r11)
            org.apache.commons.math3.FieldElement r10 = (org.apache.commons.math3.FieldElement) r10
            int r16 = r16 + r9
            int r3 = r3 + 4
            r11 = r19
            r14 = r20
            goto L_0x005b
        L_0x00b0:
            r20 = r14
        L_0x00b2:
            if (r3 >= r4) goto L_0x00c9
            r11 = r13[r16]
            int r14 = r3 + 1
            r3 = r1[r3]
            java.lang.Object r3 = r11.multiply(r3)
            java.lang.Object r3 = r10.add(r3)
            r10 = r3
            org.apache.commons.math3.FieldElement r10 = (org.apache.commons.math3.FieldElement) r10
            int r16 = r16 + r6
            r3 = r14
            goto L_0x00b2
        L_0x00c9:
            r3 = r2[r15]
            java.lang.Object r3 = r3.add(r10)
            org.apache.commons.math3.FieldElement r3 = (org.apache.commons.math3.FieldElement) r3
            r2[r15] = r3
            int r15 = r15 + 1
            r3 = r17
            r10 = r18
            r11 = r19
            r14 = r20
            goto L_0x0050
        L_0x00df:
            r17 = r3
            r18 = r10
            r19 = r11
            int r12 = r12 + 1
            goto L_0x0039
        L_0x00e9:
            r17 = r3
            int r5 = r5 + 1
            goto L_0x0020
        L_0x00ef:
            return r2
        L_0x00f0:
            org.apache.commons.math3.exception.DimensionMismatchException r2 = new org.apache.commons.math3.exception.DimensionMismatchException
            int r1 = r1.length
            int r3 = r0.rows
            r2.<init>(r1, r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.linear.BlockFieldMatrix.preMultiply(org.apache.commons.math3.FieldElement[]):org.apache.commons.math3.FieldElement[]");
    }

    public T walkInRowOrder(FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor) {
        int i = this.rows;
        int i2 = this.columns;
        fieldMatrixChangingVisitor.start(i, i2, 0, i - 1, 0, i2 - 1);
        for (int i3 = 0; i3 < this.blockRows; i3++) {
            int i4 = i3 * 36;
            int min = FastMath.min(i4 + 36, this.rows);
            for (int i5 = i4; i5 < min; i5++) {
                for (int i6 = 0; i6 < this.blockColumns; i6++) {
                    int blockWidth = blockWidth(i6);
                    int i7 = i6 * 36;
                    int min2 = FastMath.min(i7 + 36, this.columns);
                    T[] tArr = this.blocks[(this.blockColumns * i3) + i6];
                    int i8 = (i5 - i4) * blockWidth;
                    while (i7 < min2) {
                        tArr[i8] = fieldMatrixChangingVisitor.visit(i5, i7, tArr[i8]);
                        i8++;
                        i7++;
                    }
                }
            }
        }
        return fieldMatrixChangingVisitor.end();
    }

    public T walkInRowOrder(FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor) {
        int i = this.rows;
        int i2 = this.columns;
        fieldMatrixPreservingVisitor.start(i, i2, 0, i - 1, 0, i2 - 1);
        for (int i3 = 0; i3 < this.blockRows; i3++) {
            int i4 = i3 * 36;
            int min = FastMath.min(i4 + 36, this.rows);
            for (int i5 = i4; i5 < min; i5++) {
                for (int i6 = 0; i6 < this.blockColumns; i6++) {
                    int blockWidth = blockWidth(i6);
                    int i7 = i6 * 36;
                    int min2 = FastMath.min(i7 + 36, this.columns);
                    T[] tArr = this.blocks[(this.blockColumns * i3) + i6];
                    int i8 = (i5 - i4) * blockWidth;
                    while (i7 < min2) {
                        fieldMatrixPreservingVisitor.visit(i5, i7, tArr[i8]);
                        i8++;
                        i7++;
                    }
                }
            }
        }
        return fieldMatrixPreservingVisitor.end();
    }

    public T walkInRowOrder(FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor, int i, int i2, int i3, int i4) throws OutOfRangeException, NumberIsTooSmallException {
        int i5;
        int i6 = i;
        int i7 = i2;
        int i8 = i3;
        int i9 = i4;
        checkSubMatrixIndex(i6, i7, i8, i9);
        fieldMatrixChangingVisitor.start(this.rows, this.columns, i, i2, i3, i4);
        for (int i10 = i6 / 36; i10 < (i7 / 36) + 1; i10 = i5) {
            int i11 = i10 * 36;
            i5 = i10 + 1;
            int min = FastMath.min(i5 * 36, i7 + 1);
            for (int max = FastMath.max(i6, i11); max < min; max++) {
                int i12 = i8 / 36;
                while (i12 < (i9 / 36) + 1) {
                    int blockWidth = blockWidth(i12);
                    int i13 = i12 * 36;
                    int max2 = FastMath.max(i8, i13);
                    int i14 = i12 + 1;
                    int i15 = i5;
                    int min2 = FastMath.min(i14 * 36, i9 + 1);
                    int i16 = min;
                    T[] tArr = this.blocks[(this.blockColumns * i10) + i12];
                    int i17 = (((max - i11) * blockWidth) + max2) - i13;
                    while (max2 < min2) {
                        tArr[i17] = fieldMatrixChangingVisitor.visit(max, max2, tArr[i17]);
                        i17++;
                        max2++;
                    }
                    FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor2 = fieldMatrixChangingVisitor;
                    i12 = i14;
                    i5 = i15;
                    min = i16;
                }
                FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor3 = fieldMatrixChangingVisitor;
                int i18 = i5;
                int i19 = min;
            }
            FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor4 = fieldMatrixChangingVisitor;
        }
        FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor5 = fieldMatrixChangingVisitor;
        return fieldMatrixChangingVisitor.end();
    }

    public T walkInRowOrder(FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor, int i, int i2, int i3, int i4) throws OutOfRangeException, NumberIsTooSmallException {
        int i5;
        int i6 = i;
        int i7 = i2;
        int i8 = i3;
        int i9 = i4;
        checkSubMatrixIndex(i6, i7, i8, i9);
        fieldMatrixPreservingVisitor.start(this.rows, this.columns, i, i2, i3, i4);
        for (int i10 = i6 / 36; i10 < (i7 / 36) + 1; i10 = i5) {
            int i11 = i10 * 36;
            i5 = i10 + 1;
            int min = FastMath.min(i5 * 36, i7 + 1);
            for (int max = FastMath.max(i6, i11); max < min; max++) {
                int i12 = i8 / 36;
                while (i12 < (i9 / 36) + 1) {
                    int blockWidth = blockWidth(i12);
                    int i13 = i12 * 36;
                    int max2 = FastMath.max(i8, i13);
                    int i14 = i12 + 1;
                    int i15 = i5;
                    int min2 = FastMath.min(i14 * 36, i9 + 1);
                    int i16 = min;
                    T[] tArr = this.blocks[(this.blockColumns * i10) + i12];
                    int i17 = (((max - i11) * blockWidth) + max2) - i13;
                    while (max2 < min2) {
                        fieldMatrixPreservingVisitor.visit(max, max2, tArr[i17]);
                        i17++;
                        max2++;
                    }
                    FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor2 = fieldMatrixPreservingVisitor;
                    i12 = i14;
                    i5 = i15;
                    min = i16;
                }
                FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor3 = fieldMatrixPreservingVisitor;
                int i18 = i5;
                int i19 = min;
            }
            FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor4 = fieldMatrixPreservingVisitor;
        }
        FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor5 = fieldMatrixPreservingVisitor;
        return fieldMatrixPreservingVisitor.end();
    }

    public T walkInOptimizedOrder(FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor) {
        int i = this.rows;
        int i2 = this.columns;
        fieldMatrixChangingVisitor.start(i, i2, 0, i - 1, 0, i2 - 1);
        int i3 = 0;
        for (int i4 = 0; i4 < this.blockRows; i4++) {
            int i5 = i4 * 36;
            int min = FastMath.min(i5 + 36, this.rows);
            for (int i6 = 0; i6 < this.blockColumns; i6++) {
                int i7 = i6 * 36;
                int min2 = FastMath.min(i7 + 36, this.columns);
                T[] tArr = this.blocks[i3];
                int i8 = 0;
                for (int i9 = i5; i9 < min; i9++) {
                    for (int i10 = i7; i10 < min2; i10++) {
                        tArr[i8] = fieldMatrixChangingVisitor.visit(i9, i10, tArr[i8]);
                        i8++;
                    }
                }
                i3++;
            }
        }
        return fieldMatrixChangingVisitor.end();
    }

    public T walkInOptimizedOrder(FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor) {
        int i = this.rows;
        int i2 = this.columns;
        fieldMatrixPreservingVisitor.start(i, i2, 0, i - 1, 0, i2 - 1);
        int i3 = 0;
        for (int i4 = 0; i4 < this.blockRows; i4++) {
            int i5 = i4 * 36;
            int min = FastMath.min(i5 + 36, this.rows);
            for (int i6 = 0; i6 < this.blockColumns; i6++) {
                int i7 = i6 * 36;
                int min2 = FastMath.min(i7 + 36, this.columns);
                T[] tArr = this.blocks[i3];
                int i8 = 0;
                for (int i9 = i5; i9 < min; i9++) {
                    for (int i10 = i7; i10 < min2; i10++) {
                        fieldMatrixPreservingVisitor.visit(i9, i10, tArr[i8]);
                        i8++;
                    }
                }
                i3++;
            }
        }
        return fieldMatrixPreservingVisitor.end();
    }

    public T walkInOptimizedOrder(FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor, int i, int i2, int i3, int i4) throws OutOfRangeException, NumberIsTooSmallException {
        BlockFieldMatrix blockFieldMatrix = this;
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        blockFieldMatrix.checkSubMatrixIndex(i5, i6, i7, i8);
        fieldMatrixChangingVisitor.start(blockFieldMatrix.rows, blockFieldMatrix.columns, i, i2, i3, i4);
        int i9 = i5 / 36;
        while (i9 < (i6 / 36) + 1) {
            int i10 = i9 * 36;
            int max = FastMath.max(i5, i10);
            int i11 = i9 + 1;
            int min = FastMath.min(i11 * 36, i6 + 1);
            int i12 = i7 / 36;
            while (i12 < (i8 / 36) + 1) {
                int blockWidth = blockFieldMatrix.blockWidth(i12);
                int i13 = i12 * 36;
                int max2 = FastMath.max(i7, i13);
                int i14 = i12 + 1;
                int i15 = max;
                int min2 = FastMath.min(i14 * 36, i8 + 1);
                int i16 = i11;
                T[] tArr = blockFieldMatrix.blocks[(blockFieldMatrix.blockColumns * i9) + i12];
                int i17 = i15;
                while (i17 < min) {
                    int i18 = (((i17 - i10) * blockWidth) + max2) - i13;
                    int i19 = max2;
                    while (i19 < min2) {
                        int i20 = i10;
                        tArr[i18] = fieldMatrixChangingVisitor.visit(i17, i19, tArr[i18]);
                        i18++;
                        i19++;
                        i9 = i9;
                        i10 = i20;
                    }
                    int i21 = i9;
                    int i22 = i10;
                    FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor2 = fieldMatrixChangingVisitor;
                    i17++;
                    i10 = i22;
                }
                int i23 = i10;
                FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor3 = fieldMatrixChangingVisitor;
                blockFieldMatrix = this;
                i12 = i14;
                max = i15;
                i11 = i16;
                i10 = i23;
            }
            FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor4 = fieldMatrixChangingVisitor;
            blockFieldMatrix = this;
            i9 = i11;
        }
        FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor5 = fieldMatrixChangingVisitor;
        return fieldMatrixChangingVisitor.end();
    }

    public T walkInOptimizedOrder(FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor, int i, int i2, int i3, int i4) throws OutOfRangeException, NumberIsTooSmallException {
        BlockFieldMatrix blockFieldMatrix = this;
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        blockFieldMatrix.checkSubMatrixIndex(i5, i6, i7, i8);
        fieldMatrixPreservingVisitor.start(blockFieldMatrix.rows, blockFieldMatrix.columns, i, i2, i3, i4);
        int i9 = i5 / 36;
        while (i9 < (i6 / 36) + 1) {
            int i10 = i9 * 36;
            int max = FastMath.max(i5, i10);
            int i11 = i9 + 1;
            int min = FastMath.min(i11 * 36, i6 + 1);
            int i12 = i7 / 36;
            while (i12 < (i8 / 36) + 1) {
                int blockWidth = blockFieldMatrix.blockWidth(i12);
                int i13 = i12 * 36;
                int max2 = FastMath.max(i7, i13);
                int i14 = i12 + 1;
                int i15 = max;
                int min2 = FastMath.min(i14 * 36, i8 + 1);
                int i16 = i11;
                T[] tArr = blockFieldMatrix.blocks[(blockFieldMatrix.blockColumns * i9) + i12];
                int i17 = i15;
                while (i17 < min) {
                    int i18 = (((i17 - i10) * blockWidth) + max2) - i13;
                    int i19 = max2;
                    while (i19 < min2) {
                        int i20 = i10;
                        fieldMatrixPreservingVisitor.visit(i17, i19, tArr[i18]);
                        i18++;
                        i19++;
                        i9 = i9;
                        i10 = i20;
                    }
                    int i21 = i9;
                    int i22 = i10;
                    FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor2 = fieldMatrixPreservingVisitor;
                    i17++;
                    i10 = i22;
                }
                int i23 = i10;
                FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor3 = fieldMatrixPreservingVisitor;
                blockFieldMatrix = this;
                i12 = i14;
                max = i15;
                i11 = i16;
                i10 = i23;
            }
            FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor4 = fieldMatrixPreservingVisitor;
            blockFieldMatrix = this;
            i9 = i11;
        }
        FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor5 = fieldMatrixPreservingVisitor;
        return fieldMatrixPreservingVisitor.end();
    }

    private int blockHeight(int i) {
        if (i == this.blockRows - 1) {
            return this.rows - (i * 36);
        }
        return 36;
    }

    private int blockWidth(int i) {
        if (i == this.blockColumns - 1) {
            return this.columns - (i * 36);
        }
        return 36;
    }
}
