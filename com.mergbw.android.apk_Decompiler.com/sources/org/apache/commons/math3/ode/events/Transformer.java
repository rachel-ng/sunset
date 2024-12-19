package org.apache.commons.math3.ode.events;

import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

enum Transformer {
    UNINITIALIZED {
        /* access modifiers changed from: protected */
        public double transformed(double d) {
            return 0.0d;
        }
    },
    PLUS {
        /* access modifiers changed from: protected */
        public double transformed(double d) {
            return d;
        }
    },
    MINUS {
        /* access modifiers changed from: protected */
        public double transformed(double d) {
            return -d;
        }
    },
    MIN {
        /* access modifiers changed from: protected */
        public double transformed(double d) {
            return FastMath.min(-Precision.SAFE_MIN, FastMath.min(-d, d));
        }
    },
    MAX {
        /* access modifiers changed from: protected */
        public double transformed(double d) {
            return FastMath.max(Precision.SAFE_MIN, FastMath.max(-d, d));
        }
    };

    /* access modifiers changed from: protected */
    public abstract double transformed(double d);
}
