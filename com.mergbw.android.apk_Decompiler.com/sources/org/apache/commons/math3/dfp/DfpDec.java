package org.apache.commons.math3.dfp;

public class DfpDec extends Dfp {
    protected DfpDec(DfpField dfpField) {
        super(dfpField);
    }

    protected DfpDec(DfpField dfpField, byte b2) {
        super(dfpField, b2);
    }

    protected DfpDec(DfpField dfpField, int i) {
        super(dfpField, i);
    }

    protected DfpDec(DfpField dfpField, long j) {
        super(dfpField, j);
    }

    protected DfpDec(DfpField dfpField, double d) {
        super(dfpField, d);
        round(0);
    }

    public DfpDec(Dfp dfp) {
        super(dfp);
        round(0);
    }

    protected DfpDec(DfpField dfpField, String str) {
        super(dfpField, str);
        round(0);
    }

    protected DfpDec(DfpField dfpField, byte b2, byte b3) {
        super(dfpField, b2, b3);
    }

    public Dfp newInstance() {
        return new DfpDec(getField());
    }

    public Dfp newInstance(byte b2) {
        return new DfpDec(getField(), b2);
    }

    public Dfp newInstance(int i) {
        return new DfpDec(getField(), i);
    }

    public Dfp newInstance(long j) {
        return new DfpDec(getField(), j);
    }

    public Dfp newInstance(double d) {
        return new DfpDec(getField(), d);
    }

    public Dfp newInstance(Dfp dfp) {
        if (getField().getRadixDigits() == dfp.getField().getRadixDigits()) {
            return new DfpDec(dfp);
        }
        getField().setIEEEFlagsBits(1);
        Dfp newInstance = newInstance(getZero());
        newInstance.nans = 3;
        return dotrap(1, "newInstance", dfp, newInstance);
    }

    public Dfp newInstance(String str) {
        return new DfpDec(getField(), str);
    }

    public Dfp newInstance(byte b2, byte b3) {
        return new DfpDec(getField(), b2, b3);
    }

    /* access modifiers changed from: protected */
    public int getDecimalDigits() {
        return (getRadixDigits() * 4) - 3;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0093, code lost:
        if (r12 != 0) goto L_0x00c5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x009c, code lost:
        if (r12 != 0) goto L_0x00c5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00a3, code lost:
        if (r12 == 0) goto L_0x00a5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00aa, code lost:
        if ((r3 & 1) != 0) goto L_0x00e7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00b1, code lost:
        if (r12 == 0) goto L_0x00b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00b8, code lost:
        if ((r3 & 1) != 1) goto L_0x00e7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00bb, code lost:
        if (r0 > 5) goto L_0x00c5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00be, code lost:
        if (r0 >= 5) goto L_0x00c5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00c3, code lost:
        if (r12 == 0) goto L_0x00e7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int round(int r12) {
        /*
            r11 = this;
            int[] r0 = r11.mant
            int[] r1 = r11.mant
            int r1 = r1.length
            r2 = 1
            int r1 = r1 - r2
            r0 = r0[r1]
            r1 = 0
            if (r0 != 0) goto L_0x000d
            return r1
        L_0x000d:
            int[] r3 = r11.mant
            int r3 = r3.length
            r4 = 4
            int r3 = r3 * r4
            r5 = 1000(0x3e8, float:1.401E-42)
            r6 = r5
        L_0x0015:
            if (r6 <= r0) goto L_0x001c
            int r6 = r6 / 10
            int r3 = r3 + -1
            goto L_0x0015
        L_0x001c:
            int r0 = r11.getDecimalDigits()
            int r3 = r3 - r0
            int r6 = r3 / 4
            r7 = r1
            r8 = r2
        L_0x0025:
            int r9 = r3 % 4
            if (r7 >= r9) goto L_0x002e
            int r8 = r8 * 10
            int r7 = r7 + 1
            goto L_0x0025
        L_0x002e:
            int[] r3 = r11.mant
            r3 = r3[r6]
            if (r8 > r2) goto L_0x0041
            int[] r7 = r11.mant
            int r7 = r7.length
            int r7 = r7 * r4
            int r7 = r7 + -3
            if (r0 != r7) goto L_0x0041
            int r12 = super.round(r12)
            return r12
        L_0x0041:
            if (r8 != r2) goto L_0x0058
            int[] r0 = r11.mant
            int r7 = r6 + -1
            r0 = r0[r7]
            int r0 = r0 / r5
            int r0 = r0 % 10
            int[] r9 = r11.mant
            r10 = r9[r7]
            int r10 = r10 % r5
            r9[r7] = r10
            int[] r5 = r11.mant
            r5 = r5[r7]
            goto L_0x0061
        L_0x0058:
            int r0 = r3 * 10
            int r0 = r0 / r8
            int r0 = r0 % 10
            int r5 = r8 / 10
            int r5 = r3 % r5
        L_0x0061:
            r12 = r12 | r5
            r5 = r1
        L_0x0063:
            if (r5 >= r6) goto L_0x0071
            int[] r7 = r11.mant
            r7 = r7[r5]
            r12 = r12 | r7
            int[] r7 = r11.mant
            r7[r5] = r1
            int r5 = r5 + 1
            goto L_0x0063
        L_0x0071:
            int[] r5 = r11.mant
            int r3 = r3 / r8
            int r7 = r3 * r8
            r5[r6] = r7
            int[] r5 = org.apache.commons.math3.dfp.DfpDec.AnonymousClass1.$SwitchMap$org$apache$commons$math3$dfp$DfpField$RoundingMode
            org.apache.commons.math3.dfp.DfpField r7 = r11.getField()
            org.apache.commons.math3.dfp.DfpField$RoundingMode r7 = r7.getRoundingMode()
            int r7 = r7.ordinal()
            r5 = r5[r7]
            r7 = 5
            switch(r5) {
                case 1: goto L_0x00e7;
                case 2: goto L_0x00c1;
                case 3: goto L_0x00be;
                case 4: goto L_0x00bb;
                case 5: goto L_0x00ad;
                case 6: goto L_0x009f;
                case 7: goto L_0x0096;
                default: goto L_0x008c;
            }
        L_0x008c:
            byte r3 = r11.sign
            r5 = -1
            if (r3 != r5) goto L_0x00e7
            if (r0 != 0) goto L_0x00c5
            if (r12 == 0) goto L_0x00e7
            goto L_0x00c5
        L_0x0096:
            byte r3 = r11.sign
            if (r3 != r2) goto L_0x00e7
            if (r0 != 0) goto L_0x00c5
            if (r12 == 0) goto L_0x00e7
            goto L_0x00c5
        L_0x009f:
            if (r0 > r7) goto L_0x00c5
            if (r0 != r7) goto L_0x00a5
            if (r12 != 0) goto L_0x00c5
        L_0x00a5:
            if (r0 != r7) goto L_0x00e7
            if (r12 != 0) goto L_0x00e7
            r3 = r3 & r2
            if (r3 != 0) goto L_0x00e7
            goto L_0x00c5
        L_0x00ad:
            if (r0 > r7) goto L_0x00c5
            if (r0 != r7) goto L_0x00b3
            if (r12 != 0) goto L_0x00c5
        L_0x00b3:
            if (r0 != r7) goto L_0x00e7
            if (r12 != 0) goto L_0x00e7
            r3 = r3 & r2
            if (r3 != r2) goto L_0x00e7
            goto L_0x00c5
        L_0x00bb:
            if (r0 <= r7) goto L_0x00e7
            goto L_0x00c5
        L_0x00be:
            if (r0 < r7) goto L_0x00e7
            goto L_0x00c5
        L_0x00c1:
            if (r0 != 0) goto L_0x00c5
            if (r12 == 0) goto L_0x00e7
        L_0x00c5:
            int[] r3 = r11.mant
            int r3 = r3.length
            if (r6 >= r3) goto L_0x00da
            int[] r3 = r11.mant
            r3 = r3[r6]
            int r3 = r3 + r8
            int r8 = r3 / 10000
            int[] r5 = r11.mant
            int r3 = r3 % 10000
            r5[r6] = r3
            int r6 = r6 + 1
            goto L_0x00c5
        L_0x00da:
            if (r8 == 0) goto L_0x00e7
            r11.shiftRight()
            int[] r3 = r11.mant
            int[] r5 = r11.mant
            int r5 = r5.length
            int r5 = r5 - r2
            r3[r5] = r8
        L_0x00e7:
            int r2 = r11.exp
            r3 = -32767(0xffffffffffff8001, float:NaN)
            if (r2 >= r3) goto L_0x00f7
            org.apache.commons.math3.dfp.DfpField r12 = r11.getField()
            r0 = 8
            r12.setIEEEFlagsBits(r0)
            return r0
        L_0x00f7:
            int r2 = r11.exp
            r3 = 32768(0x8000, float:4.5918E-41)
            if (r2 <= r3) goto L_0x0106
            org.apache.commons.math3.dfp.DfpField r12 = r11.getField()
            r12.setIEEEFlagsBits(r4)
            return r4
        L_0x0106:
            if (r0 != 0) goto L_0x010c
            if (r12 == 0) goto L_0x010b
            goto L_0x010c
        L_0x010b:
            return r1
        L_0x010c:
            org.apache.commons.math3.dfp.DfpField r12 = r11.getField()
            r0 = 16
            r12.setIEEEFlagsBits(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.dfp.DfpDec.round(int):int");
    }

    /* renamed from: org.apache.commons.math3.dfp.DfpDec$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$math3$dfp$DfpField$RoundingMode;

        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.apache.commons.math3.dfp.DfpField$RoundingMode[] r0 = org.apache.commons.math3.dfp.DfpField.RoundingMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$commons$math3$dfp$DfpField$RoundingMode = r0
                org.apache.commons.math3.dfp.DfpField$RoundingMode r1 = org.apache.commons.math3.dfp.DfpField.RoundingMode.ROUND_DOWN     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$commons$math3$dfp$DfpField$RoundingMode     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.commons.math3.dfp.DfpField$RoundingMode r1 = org.apache.commons.math3.dfp.DfpField.RoundingMode.ROUND_UP     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$commons$math3$dfp$DfpField$RoundingMode     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.commons.math3.dfp.DfpField$RoundingMode r1 = org.apache.commons.math3.dfp.DfpField.RoundingMode.ROUND_HALF_UP     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$commons$math3$dfp$DfpField$RoundingMode     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.commons.math3.dfp.DfpField$RoundingMode r1 = org.apache.commons.math3.dfp.DfpField.RoundingMode.ROUND_HALF_DOWN     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$commons$math3$dfp$DfpField$RoundingMode     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.commons.math3.dfp.DfpField$RoundingMode r1 = org.apache.commons.math3.dfp.DfpField.RoundingMode.ROUND_HALF_EVEN     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$org$apache$commons$math3$dfp$DfpField$RoundingMode     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.apache.commons.math3.dfp.DfpField$RoundingMode r1 = org.apache.commons.math3.dfp.DfpField.RoundingMode.ROUND_HALF_ODD     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$org$apache$commons$math3$dfp$DfpField$RoundingMode     // Catch:{ NoSuchFieldError -> 0x0054 }
                org.apache.commons.math3.dfp.DfpField$RoundingMode r1 = org.apache.commons.math3.dfp.DfpField.RoundingMode.ROUND_CEIL     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$org$apache$commons$math3$dfp$DfpField$RoundingMode     // Catch:{ NoSuchFieldError -> 0x0060 }
                org.apache.commons.math3.dfp.DfpField$RoundingMode r1 = org.apache.commons.math3.dfp.DfpField.RoundingMode.ROUND_FLOOR     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.dfp.DfpDec.AnonymousClass1.<clinit>():void");
        }
    }

    public Dfp nextAfter(Dfp dfp) {
        Dfp dfp2;
        Dfp dfp3;
        if (getField().getRadixDigits() != dfp.getField().getRadixDigits()) {
            getField().setIEEEFlagsBits(1);
            Dfp newInstance = newInstance(getZero());
            newInstance.nans = 3;
            return dotrap(1, "nextAfter", dfp, newInstance);
        }
        boolean lessThan = lessThan(dfp);
        if (equals(dfp)) {
            return newInstance(dfp);
        }
        if (lessThan(getZero())) {
            lessThan = !lessThan;
        }
        if (lessThan) {
            Dfp copysign = copysign(power10((intLog10() - getDecimalDigits()) + 1), this);
            if (equals(getZero())) {
                copysign = power10K(-32768 - this.mant.length);
            }
            if (copysign.equals(getZero())) {
                dfp2 = copysign(newInstance(getZero()), this);
            } else {
                dfp2 = add(copysign);
            }
        } else {
            Dfp copysign2 = copysign(power10(intLog10()), this);
            if (equals(copysign2)) {
                dfp3 = copysign2.divide(power10(getDecimalDigits()));
            } else {
                dfp3 = copysign2.divide(power10(getDecimalDigits() - 1));
            }
            if (equals(getZero())) {
                dfp3 = power10K(-32768 - this.mant.length);
            }
            if (dfp3.equals(getZero())) {
                dfp2 = copysign(newInstance(getZero()), this);
            } else {
                dfp2 = subtract(dfp3);
            }
        }
        if (dfp2.classify() == 1 && classify() != 1) {
            getField().setIEEEFlagsBits(16);
            dfp2 = dotrap(16, "nextAfter", dfp, dfp2);
        }
        if (!dfp2.equals(getZero()) || equals(getZero())) {
            return dfp2;
        }
        getField().setIEEEFlagsBits(16);
        return dotrap(16, "nextAfter", dfp, dfp2);
    }
}
