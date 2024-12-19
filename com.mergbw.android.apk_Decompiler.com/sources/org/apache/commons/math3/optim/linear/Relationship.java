package org.apache.commons.math3.optim.linear;

public enum Relationship {
    EQ("="),
    LEQ("<="),
    GEQ(">=");
    
    private final String stringValue;

    private Relationship(String str) {
        this.stringValue = str;
    }

    public String toString() {
        return this.stringValue;
    }

    /* renamed from: org.apache.commons.math3.optim.linear.Relationship$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$math3$optim$linear$Relationship = null;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                org.apache.commons.math3.optim.linear.Relationship[] r0 = org.apache.commons.math3.optim.linear.Relationship.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$commons$math3$optim$linear$Relationship = r0
                org.apache.commons.math3.optim.linear.Relationship r1 = org.apache.commons.math3.optim.linear.Relationship.LEQ     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$commons$math3$optim$linear$Relationship     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.commons.math3.optim.linear.Relationship r1 = org.apache.commons.math3.optim.linear.Relationship.GEQ     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.optim.linear.Relationship.AnonymousClass1.<clinit>():void");
        }
    }

    public Relationship oppositeRelationship() {
        int i = AnonymousClass1.$SwitchMap$org$apache$commons$math3$optim$linear$Relationship[ordinal()];
        if (i == 1) {
            return GEQ;
        }
        if (i != 2) {
            return EQ;
        }
        return LEQ;
    }
}
