package org.apache.commons.math3.optim.linear;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import org.apache.commons.math3.optim.OptimizationData;

public class LinearConstraintSet implements OptimizationData {
    private final Set<LinearConstraint> linearConstraints;

    public LinearConstraintSet(LinearConstraint... linearConstraintArr) {
        this.linearConstraints = new LinkedHashSet();
        for (LinearConstraint add : linearConstraintArr) {
            this.linearConstraints.add(add);
        }
    }

    public LinearConstraintSet(Collection<LinearConstraint> collection) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        this.linearConstraints = linkedHashSet;
        linkedHashSet.addAll(collection);
    }

    public Collection<LinearConstraint> getConstraints() {
        return Collections.unmodifiableSet(this.linearConstraints);
    }
}
