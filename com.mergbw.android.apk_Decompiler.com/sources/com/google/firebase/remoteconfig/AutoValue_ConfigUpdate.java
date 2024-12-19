package com.google.firebase.remoteconfig;

import java.util.Set;
import org.apache.commons.math3.geometry.VectorFormat;

final class AutoValue_ConfigUpdate extends ConfigUpdate {
    private final Set<String> updatedKeys;

    AutoValue_ConfigUpdate(Set<String> set) {
        if (set != null) {
            this.updatedKeys = set;
            return;
        }
        throw new NullPointerException("Null updatedKeys");
    }

    public Set<String> getUpdatedKeys() {
        return this.updatedKeys;
    }

    public String toString() {
        return "ConfigUpdate{updatedKeys=" + this.updatedKeys + VectorFormat.DEFAULT_SUFFIX;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ConfigUpdate) {
            return this.updatedKeys.equals(((ConfigUpdate) obj).getUpdatedKeys());
        }
        return false;
    }

    public int hashCode() {
        return this.updatedKeys.hashCode() ^ 1000003;
    }
}
