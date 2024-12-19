package org.apache.commons.math3.genetics;

import java.util.concurrent.TimeUnit;
import org.apache.commons.math3.exception.NumberIsTooSmallException;

public class FixedElapsedTime implements StoppingCondition {
    private long endTime;
    private final long maxTimePeriod;

    public FixedElapsedTime(long j) throws NumberIsTooSmallException {
        this(j, TimeUnit.SECONDS);
    }

    public FixedElapsedTime(long j, TimeUnit timeUnit) throws NumberIsTooSmallException {
        this.endTime = -1;
        if (j >= 0) {
            this.maxTimePeriod = timeUnit.toNanos(j);
            return;
        }
        throw new NumberIsTooSmallException(Long.valueOf(j), 0, true);
    }

    public boolean isSatisfied(Population population) {
        if (this.endTime < 0) {
            this.endTime = System.nanoTime() + this.maxTimePeriod;
        }
        return System.nanoTime() >= this.endTime;
    }
}
