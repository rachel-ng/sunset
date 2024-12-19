package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import org.apache.commons.math3.geometry.VectorFormat;

final class AutoValue_CrashlyticsReport_CustomAttribute extends CrashlyticsReport.CustomAttribute {
    private final String key;
    private final String value;

    private AutoValue_CrashlyticsReport_CustomAttribute(String str, String str2) {
        this.key = str;
        this.value = str2;
    }

    public String getKey() {
        return this.key;
    }

    public String getValue() {
        return this.value;
    }

    public String toString() {
        return "CustomAttribute{key=" + this.key + ", value=" + this.value + VectorFormat.DEFAULT_SUFFIX;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CrashlyticsReport.CustomAttribute)) {
            return false;
        }
        CrashlyticsReport.CustomAttribute customAttribute = (CrashlyticsReport.CustomAttribute) obj;
        if (!this.key.equals(customAttribute.getKey()) || !this.value.equals(customAttribute.getValue())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((this.key.hashCode() ^ 1000003) * 1000003) ^ this.value.hashCode();
    }

    static final class Builder extends CrashlyticsReport.CustomAttribute.Builder {
        private String key;
        private String value;

        Builder() {
        }

        public CrashlyticsReport.CustomAttribute.Builder setKey(String str) {
            if (str != null) {
                this.key = str;
                return this;
            }
            throw new NullPointerException("Null key");
        }

        public CrashlyticsReport.CustomAttribute.Builder setValue(String str) {
            if (str != null) {
                this.value = str;
                return this;
            }
            throw new NullPointerException("Null value");
        }

        public CrashlyticsReport.CustomAttribute build() {
            String str;
            String str2 = this.key;
            if (str2 != null && (str = this.value) != null) {
                return new AutoValue_CrashlyticsReport_CustomAttribute(str2, str);
            }
            StringBuilder sb = new StringBuilder();
            if (this.key == null) {
                sb.append(" key");
            }
            if (this.value == null) {
                sb.append(" value");
            }
            throw new IllegalStateException("Missing required properties:" + sb);
        }
    }
}
