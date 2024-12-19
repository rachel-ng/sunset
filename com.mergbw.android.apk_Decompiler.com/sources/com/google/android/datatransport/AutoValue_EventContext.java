package com.google.android.datatransport;

import com.google.android.datatransport.EventContext;
import java.util.Arrays;
import org.apache.commons.math3.geometry.VectorFormat;

final class AutoValue_EventContext extends EventContext {
    private final byte[] experimentIdsClear;
    private final byte[] experimentIdsEncrypted;
    private final String pseudonymousId;

    private AutoValue_EventContext(String str, byte[] bArr, byte[] bArr2) {
        this.pseudonymousId = str;
        this.experimentIdsClear = bArr;
        this.experimentIdsEncrypted = bArr2;
    }

    public String getPseudonymousId() {
        return this.pseudonymousId;
    }

    public byte[] getExperimentIdsClear() {
        return this.experimentIdsClear;
    }

    public byte[] getExperimentIdsEncrypted() {
        return this.experimentIdsEncrypted;
    }

    public String toString() {
        return "EventContext{pseudonymousId=" + this.pseudonymousId + ", experimentIdsClear=" + Arrays.toString(this.experimentIdsClear) + ", experimentIdsEncrypted=" + Arrays.toString(this.experimentIdsEncrypted) + VectorFormat.DEFAULT_SUFFIX;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EventContext)) {
            return false;
        }
        EventContext eventContext = (EventContext) obj;
        String str = this.pseudonymousId;
        if (str != null ? str.equals(eventContext.getPseudonymousId()) : eventContext.getPseudonymousId() == null) {
            boolean z = eventContext instanceof AutoValue_EventContext;
            if (Arrays.equals(this.experimentIdsClear, z ? ((AutoValue_EventContext) eventContext).experimentIdsClear : eventContext.getExperimentIdsClear())) {
                if (Arrays.equals(this.experimentIdsEncrypted, z ? ((AutoValue_EventContext) eventContext).experimentIdsEncrypted : eventContext.getExperimentIdsEncrypted())) {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        String str = this.pseudonymousId;
        return (((((str == null ? 0 : str.hashCode()) ^ 1000003) * 1000003) ^ Arrays.hashCode(this.experimentIdsClear)) * 1000003) ^ Arrays.hashCode(this.experimentIdsEncrypted);
    }

    static final class Builder extends EventContext.Builder {
        private byte[] experimentIdsClear;
        private byte[] experimentIdsEncrypted;
        private String pseudonymousId;

        Builder() {
        }

        public EventContext.Builder setPseudonymousId(String str) {
            this.pseudonymousId = str;
            return this;
        }

        public EventContext.Builder setExperimentIdsClear(byte[] bArr) {
            this.experimentIdsClear = bArr;
            return this;
        }

        public EventContext.Builder setExperimentIdsEncrypted(byte[] bArr) {
            this.experimentIdsEncrypted = bArr;
            return this;
        }

        public EventContext build() {
            return new AutoValue_EventContext(this.pseudonymousId, this.experimentIdsClear, this.experimentIdsEncrypted);
        }
    }
}
