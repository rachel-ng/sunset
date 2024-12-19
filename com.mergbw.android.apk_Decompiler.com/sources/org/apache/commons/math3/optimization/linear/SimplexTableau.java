package org.apache.commons.math3.optimization.linear;

import androidx.exifinterface.media.ExifInterface;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.PointValuePair;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

@Deprecated
class SimplexTableau implements Serializable {
    private static final double CUTOFF_THRESHOLD = 1.0E-12d;
    private static final int DEFAULT_ULPS = 10;
    private static final String NEGATIVE_VAR_COLUMN_LABEL = "x-";
    private static final long serialVersionUID = -1369660067587938365L;
    private final List<String> columnLabels;
    private final List<LinearConstraint> constraints;
    private final double epsilon;
    private final LinearObjectiveFunction f;
    private final int maxUlps;
    private int numArtificialVariables;
    private final int numDecisionVariables;
    private final int numSlackVariables;
    private final boolean restrictToNonNegative;
    private transient RealMatrix tableau;

    SimplexTableau(LinearObjectiveFunction linearObjectiveFunction, Collection<LinearConstraint> collection, GoalType goalType, boolean z, double d) {
        this(linearObjectiveFunction, collection, goalType, z, d, 10);
    }

    SimplexTableau(LinearObjectiveFunction linearObjectiveFunction, Collection<LinearConstraint> collection, GoalType goalType, boolean z, double d, int i) {
        this.columnLabels = new ArrayList();
        this.f = linearObjectiveFunction;
        this.constraints = normalizeConstraints(collection);
        this.restrictToNonNegative = z;
        this.epsilon = d;
        this.maxUlps = i;
        boolean z2 = true;
        this.numDecisionVariables = linearObjectiveFunction.getCoefficients().getDimension() + (z ^ true ? 1 : 0);
        this.numSlackVariables = getConstraintTypeCounts(Relationship.LEQ) + getConstraintTypeCounts(Relationship.GEQ);
        this.numArtificialVariables = getConstraintTypeCounts(Relationship.EQ) + getConstraintTypeCounts(Relationship.GEQ);
        this.tableau = createTableau(goalType != GoalType.MAXIMIZE ? false : z2);
        initializeColumnLabels();
    }

    /* access modifiers changed from: protected */
    public void initializeColumnLabels() {
        if (getNumObjectiveFunctions() == 2) {
            this.columnLabels.add(ExifInterface.LONGITUDE_WEST);
        }
        this.columnLabels.add("Z");
        for (int i = 0; i < getOriginalNumDecisionVariables(); i++) {
            this.columnLabels.add("x" + i);
        }
        if (!this.restrictToNonNegative) {
            this.columnLabels.add(NEGATIVE_VAR_COLUMN_LABEL);
        }
        for (int i2 = 0; i2 < getNumSlackVariables(); i2++) {
            this.columnLabels.add("s" + i2);
        }
        for (int i3 = 0; i3 < getNumArtificialVariables(); i3++) {
            this.columnLabels.add("a" + i3);
        }
        this.columnLabels.add("RHS");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00f8  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x011a A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.commons.math3.linear.RealMatrix createTableau(boolean r17) {
        /*
            r16 = this;
            r0 = r16
            int r1 = r0.numDecisionVariables
            int r2 = r0.numSlackVariables
            int r1 = r1 + r2
            int r2 = r0.numArtificialVariables
            int r1 = r1 + r2
            int r2 = r16.getNumObjectiveFunctions()
            int r1 = r1 + r2
            int r2 = r1 + 1
            java.util.List<org.apache.commons.math3.optimization.linear.LinearConstraint> r3 = r0.constraints
            int r3 = r3.size()
            int r4 = r16.getNumObjectiveFunctions()
            int r3 = r3 + r4
            org.apache.commons.math3.linear.Array2DRowRealMatrix r4 = new org.apache.commons.math3.linear.Array2DRowRealMatrix
            r4.<init>((int) r3, (int) r2)
            int r2 = r16.getNumObjectiveFunctions()
            r3 = 2
            r5 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            r7 = 0
            if (r2 != r3) goto L_0x002e
            r4.setEntry(r7, r7, r5)
        L_0x002e:
            int r2 = r16.getNumObjectiveFunctions()
            r3 = 1
            if (r2 != r3) goto L_0x0037
            r2 = r7
            goto L_0x0038
        L_0x0037:
            r2 = r3
        L_0x0038:
            r8 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            if (r17 == 0) goto L_0x003e
            r10 = r8
            goto L_0x003f
        L_0x003e:
            r10 = r5
        L_0x003f:
            r4.setEntry(r2, r2, r10)
            org.apache.commons.math3.optimization.linear.LinearObjectiveFunction r10 = r0.f
            org.apache.commons.math3.linear.RealVector r10 = r10.getCoefficients()
            if (r17 == 0) goto L_0x004e
            org.apache.commons.math3.linear.RealVector r10 = r10.mapMultiply(r5)
        L_0x004e:
            double[] r11 = r10.toArray()
            double[][] r12 = r4.getDataRef()
            r12 = r12[r2]
            r0.copyArray(r11, r12)
            org.apache.commons.math3.optimization.linear.LinearObjectiveFunction r11 = r0.f
            double r11 = r11.getConstantTerm()
            if (r17 == 0) goto L_0x0064
            goto L_0x0065
        L_0x0064:
            double r11 = r11 * r5
        L_0x0065:
            r4.setEntry(r2, r1, r11)
            boolean r11 = r0.restrictToNonNegative
            if (r11 != 0) goto L_0x0078
            int r11 = r16.getSlackVariableOffset()
            int r11 = r11 - r3
            double r12 = getInvertedCoefficientSum(r10)
            r4.setEntry(r2, r11, r12)
        L_0x0078:
            r2 = r7
            r10 = r2
            r11 = r10
        L_0x007b:
            java.util.List<org.apache.commons.math3.optimization.linear.LinearConstraint> r12 = r0.constraints
            int r12 = r12.size()
            if (r2 >= r12) goto L_0x011f
            java.util.List<org.apache.commons.math3.optimization.linear.LinearConstraint> r12 = r0.constraints
            java.lang.Object r12 = r12.get(r2)
            org.apache.commons.math3.optimization.linear.LinearConstraint r12 = (org.apache.commons.math3.optimization.linear.LinearConstraint) r12
            int r13 = r16.getNumObjectiveFunctions()
            int r13 = r13 + r2
            org.apache.commons.math3.linear.RealVector r14 = r12.getCoefficients()
            double[] r14 = r14.toArray()
            double[][] r15 = r4.getDataRef()
            r15 = r15[r13]
            r0.copyArray(r14, r15)
            boolean r14 = r0.restrictToNonNegative
            if (r14 != 0) goto L_0x00b5
            int r14 = r16.getSlackVariableOffset()
            int r14 = r14 - r3
            org.apache.commons.math3.linear.RealVector r15 = r12.getCoefficients()
            double r5 = getInvertedCoefficientSum(r15)
            r4.setEntry(r13, r14, r5)
        L_0x00b5:
            double r5 = r12.getValue()
            r4.setEntry(r13, r1, r5)
            org.apache.commons.math3.optimization.linear.Relationship r5 = r12.getRelationship()
            org.apache.commons.math3.optimization.linear.Relationship r6 = org.apache.commons.math3.optimization.linear.Relationship.LEQ
            if (r5 != r6) goto L_0x00d0
            int r5 = r16.getSlackVariableOffset()
            int r6 = r10 + 1
            int r5 = r5 + r10
            r4.setEntry(r13, r5, r8)
            r10 = r6
            goto L_0x00e6
        L_0x00d0:
            org.apache.commons.math3.optimization.linear.Relationship r5 = r12.getRelationship()
            org.apache.commons.math3.optimization.linear.Relationship r6 = org.apache.commons.math3.optimization.linear.Relationship.GEQ
            if (r5 != r6) goto L_0x00e6
            int r5 = r16.getSlackVariableOffset()
            int r6 = r10 + 1
            int r5 = r5 + r10
            r14 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            r4.setEntry(r13, r5, r14)
            r10 = r6
            goto L_0x00e8
        L_0x00e6:
            r14 = -4616189618054758400(0xbff0000000000000, double:-1.0)
        L_0x00e8:
            org.apache.commons.math3.optimization.linear.Relationship r5 = r12.getRelationship()
            org.apache.commons.math3.optimization.linear.Relationship r6 = org.apache.commons.math3.optimization.linear.Relationship.EQ
            if (r5 == r6) goto L_0x00f8
            org.apache.commons.math3.optimization.linear.Relationship r5 = r12.getRelationship()
            org.apache.commons.math3.optimization.linear.Relationship r6 = org.apache.commons.math3.optimization.linear.Relationship.GEQ
            if (r5 != r6) goto L_0x011a
        L_0x00f8:
            int r5 = r16.getArtificialVariableOffset()
            int r5 = r5 + r11
            r4.setEntry(r7, r5, r8)
            int r5 = r16.getArtificialVariableOffset()
            int r6 = r11 + 1
            int r5 = r5 + r11
            r4.setEntry(r13, r5, r8)
            org.apache.commons.math3.linear.RealVector r5 = r4.getRowVector(r7)
            org.apache.commons.math3.linear.RealVector r11 = r4.getRowVector(r13)
            org.apache.commons.math3.linear.RealVector r5 = r5.subtract(r11)
            r4.setRowVector(r7, r5)
            r11 = r6
        L_0x011a:
            int r2 = r2 + 1
            r5 = r14
            goto L_0x007b
        L_0x011f:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.optimization.linear.SimplexTableau.createTableau(boolean):org.apache.commons.math3.linear.RealMatrix");
    }

    public List<LinearConstraint> normalizeConstraints(Collection<LinearConstraint> collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        for (LinearConstraint normalize : collection) {
            arrayList.add(normalize(normalize));
        }
        return arrayList;
    }

    private LinearConstraint normalize(LinearConstraint linearConstraint) {
        if (linearConstraint.getValue() < 0.0d) {
            return new LinearConstraint(linearConstraint.getCoefficients().mapMultiply(-1.0d), linearConstraint.getRelationship().oppositeRelationship(), linearConstraint.getValue() * -1.0d);
        }
        return new LinearConstraint(linearConstraint.getCoefficients(), linearConstraint.getRelationship(), linearConstraint.getValue());
    }

    /* access modifiers changed from: protected */
    public final int getNumObjectiveFunctions() {
        return this.numArtificialVariables > 0 ? 2 : 1;
    }

    private int getConstraintTypeCounts(Relationship relationship) {
        int i = 0;
        for (LinearConstraint relationship2 : this.constraints) {
            if (relationship2.getRelationship() == relationship) {
                i++;
            }
        }
        return i;
    }

    protected static double getInvertedCoefficientSum(RealVector realVector) {
        double d = 0.0d;
        for (double d2 : realVector.toArray()) {
            d -= d2;
        }
        return d;
    }

    /* access modifiers changed from: protected */
    public Integer getBasicRow(int i) {
        Integer num = null;
        for (int i2 = 0; i2 < getHeight(); i2++) {
            double entry = getEntry(i2, i);
            if (Precision.equals(entry, 1.0d, this.maxUlps) && num == null) {
                num = Integer.valueOf(i2);
            } else if (!Precision.equals(entry, 0.0d, this.maxUlps)) {
                return null;
            }
        }
        return num;
    }

    /* access modifiers changed from: protected */
    public void dropPhase1Objective() {
        if (getNumObjectiveFunctions() != 1) {
            TreeSet treeSet = new TreeSet();
            treeSet.add(0);
            for (int numObjectiveFunctions = getNumObjectiveFunctions(); numObjectiveFunctions < getArtificialVariableOffset(); numObjectiveFunctions++) {
                if (Precision.compareTo(this.tableau.getEntry(0, numObjectiveFunctions), 0.0d, this.epsilon) > 0) {
                    treeSet.add(Integer.valueOf(numObjectiveFunctions));
                }
            }
            for (int i = 0; i < getNumArtificialVariables(); i++) {
                int artificialVariableOffset = getArtificialVariableOffset() + i;
                if (getBasicRow(artificialVariableOffset) == null) {
                    treeSet.add(Integer.valueOf(artificialVariableOffset));
                }
            }
            int[] iArr = new int[2];
            iArr[1] = getWidth() - treeSet.size();
            iArr[0] = getHeight() - 1;
            double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr);
            for (int i2 = 1; i2 < getHeight(); i2++) {
                int i3 = 0;
                for (int i4 = 0; i4 < getWidth(); i4++) {
                    if (!treeSet.contains(Integer.valueOf(i4))) {
                        dArr[i2 - 1][i3] = this.tableau.getEntry(i2, i4);
                        i3++;
                    }
                }
            }
            Integer[] numArr = (Integer[]) treeSet.toArray(new Integer[treeSet.size()]);
            for (int length = numArr.length - 1; length >= 0; length--) {
                this.columnLabels.remove(numArr[length].intValue());
            }
            this.tableau = new Array2DRowRealMatrix(dArr);
            this.numArtificialVariables = 0;
        }
    }

    private void copyArray(double[] dArr, double[] dArr2) {
        System.arraycopy(dArr, 0, dArr2, getNumObjectiveFunctions(), dArr.length);
    }

    /* access modifiers changed from: package-private */
    public boolean isOptimal() {
        for (int numObjectiveFunctions = getNumObjectiveFunctions(); numObjectiveFunctions < getWidth() - 1; numObjectiveFunctions++) {
            if (Precision.compareTo(this.tableau.getEntry(0, numObjectiveFunctions), 0.0d, this.epsilon) < 0) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public PointValuePair getSolution() {
        double d;
        double d2;
        int indexOf = this.columnLabels.indexOf(NEGATIVE_VAR_COLUMN_LABEL);
        Integer basicRow = indexOf > 0 ? getBasicRow(indexOf) : null;
        if (basicRow == null) {
            d = 0.0d;
        } else {
            d = getEntry(basicRow.intValue(), getRhsOffset());
        }
        HashSet hashSet = new HashSet();
        int originalNumDecisionVariables = getOriginalNumDecisionVariables();
        double[] dArr = new double[originalNumDecisionVariables];
        for (int i = 0; i < originalNumDecisionVariables; i++) {
            int indexOf2 = this.columnLabels.indexOf("x" + i);
            if (indexOf2 < 0) {
                dArr[i] = 0.0d;
            } else {
                Integer basicRow2 = getBasicRow(indexOf2);
                if (basicRow2 != null && basicRow2.intValue() == 0) {
                    dArr[i] = 0.0d;
                } else if (hashSet.contains(basicRow2)) {
                    dArr[i] = 0.0d - (this.restrictToNonNegative ? 0.0d : d);
                } else {
                    hashSet.add(basicRow2);
                    if (basicRow2 == null) {
                        d2 = 0.0d;
                    } else {
                        d2 = getEntry(basicRow2.intValue(), getRhsOffset());
                    }
                    dArr[i] = d2 - (this.restrictToNonNegative ? 0.0d : d);
                }
            }
        }
        return new PointValuePair(dArr, this.f.getValue(dArr));
    }

    /* access modifiers changed from: protected */
    public void divideRow(int i, double d) {
        for (int i2 = 0; i2 < getWidth(); i2++) {
            RealMatrix realMatrix = this.tableau;
            realMatrix.setEntry(i, i2, realMatrix.getEntry(i, i2) / d);
        }
    }

    /* access modifiers changed from: protected */
    public void subtractRow(int i, int i2, double d) {
        for (int i3 = 0; i3 < getWidth(); i3++) {
            double entry = this.tableau.getEntry(i, i3) - (this.tableau.getEntry(i2, i3) * d);
            if (FastMath.abs(entry) < 1.0E-12d) {
                entry = 0.0d;
            }
            this.tableau.setEntry(i, i3, entry);
        }
    }

    /* access modifiers changed from: protected */
    public final int getWidth() {
        return this.tableau.getColumnDimension();
    }

    /* access modifiers changed from: protected */
    public final int getHeight() {
        return this.tableau.getRowDimension();
    }

    /* access modifiers changed from: protected */
    public final double getEntry(int i, int i2) {
        return this.tableau.getEntry(i, i2);
    }

    /* access modifiers changed from: protected */
    public final void setEntry(int i, int i2, double d) {
        this.tableau.setEntry(i, i2, d);
    }

    /* access modifiers changed from: protected */
    public final int getSlackVariableOffset() {
        return getNumObjectiveFunctions() + this.numDecisionVariables;
    }

    /* access modifiers changed from: protected */
    public final int getArtificialVariableOffset() {
        return getNumObjectiveFunctions() + this.numDecisionVariables + this.numSlackVariables;
    }

    /* access modifiers changed from: protected */
    public final int getRhsOffset() {
        return getWidth() - 1;
    }

    /* access modifiers changed from: protected */
    public final int getNumDecisionVariables() {
        return this.numDecisionVariables;
    }

    /* access modifiers changed from: protected */
    public final int getOriginalNumDecisionVariables() {
        return this.f.getCoefficients().getDimension();
    }

    /* access modifiers changed from: protected */
    public final int getNumSlackVariables() {
        return this.numSlackVariables;
    }

    /* access modifiers changed from: protected */
    public final int getNumArtificialVariables() {
        return this.numArtificialVariables;
    }

    /* access modifiers changed from: protected */
    public final double[][] getData() {
        return this.tableau.getData();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SimplexTableau)) {
            return false;
        }
        SimplexTableau simplexTableau = (SimplexTableau) obj;
        if (this.restrictToNonNegative == simplexTableau.restrictToNonNegative && this.numDecisionVariables == simplexTableau.numDecisionVariables && this.numSlackVariables == simplexTableau.numSlackVariables && this.numArtificialVariables == simplexTableau.numArtificialVariables && this.epsilon == simplexTableau.epsilon && this.maxUlps == simplexTableau.maxUlps && this.f.equals(simplexTableau.f) && this.constraints.equals(simplexTableau.constraints) && this.tableau.equals(simplexTableau.tableau)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((Boolean.valueOf(this.restrictToNonNegative).hashCode() ^ this.numDecisionVariables) ^ this.numSlackVariables) ^ this.numArtificialVariables) ^ Double.valueOf(this.epsilon).hashCode()) ^ this.maxUlps) ^ this.f.hashCode()) ^ this.constraints.hashCode()) ^ this.tableau.hashCode();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        MatrixUtils.serializeRealMatrix(this.tableau, objectOutputStream);
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        MatrixUtils.deserializeRealMatrix(this, "tableau", objectInputStream);
    }
}
