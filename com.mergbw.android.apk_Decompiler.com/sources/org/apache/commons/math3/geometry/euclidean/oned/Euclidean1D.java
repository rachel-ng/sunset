package org.apache.commons.math3.geometry.euclidean.oned;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Space;

public class Euclidean1D implements Serializable, Space {
    private static final long serialVersionUID = -1178039568877797126L;

    public int getDimension() {
        return 1;
    }

    private Euclidean1D() {
    }

    public static Euclidean1D getInstance() {
        return LazyHolder.INSTANCE;
    }

    public Space getSubSpace() throws NoSubSpaceException {
        throw new NoSubSpaceException();
    }

    private static class LazyHolder {
        /* access modifiers changed from: private */
        public static final Euclidean1D INSTANCE = new Euclidean1D();

        private LazyHolder() {
        }
    }

    private Object readResolve() {
        return LazyHolder.INSTANCE;
    }

    public static class NoSubSpaceException extends MathUnsupportedOperationException {
        private static final long serialVersionUID = 20140225;

        public NoSubSpaceException() {
            super(LocalizedFormats.NOT_SUPPORTED_IN_DIMENSION_N, 1);
        }
    }
}
