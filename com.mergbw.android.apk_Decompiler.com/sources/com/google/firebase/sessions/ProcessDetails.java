package com.google.firebase.sessions;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0011\b\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\bHÆ\u0003J1\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0015\u001a\u00020\b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0019"}, d2 = {"Lcom/google/firebase/sessions/ProcessDetails;", "", "processName", "", "pid", "", "importance", "isDefaultProcess", "", "(Ljava/lang/String;IIZ)V", "getImportance", "()I", "()Z", "getPid", "getProcessName", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "toString", "com.google.firebase-firebase-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SessionEvent.kt */
public final class ProcessDetails {
    private final int importance;
    private final boolean isDefaultProcess;
    private final int pid;
    private final String processName;

    public static /* synthetic */ ProcessDetails copy$default(ProcessDetails processDetails, String str, int i, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = processDetails.processName;
        }
        if ((i3 & 2) != 0) {
            i = processDetails.pid;
        }
        if ((i3 & 4) != 0) {
            i2 = processDetails.importance;
        }
        if ((i3 & 8) != 0) {
            z = processDetails.isDefaultProcess;
        }
        return processDetails.copy(str, i, i2, z);
    }

    public final String component1() {
        return this.processName;
    }

    public final int component2() {
        return this.pid;
    }

    public final int component3() {
        return this.importance;
    }

    public final boolean component4() {
        return this.isDefaultProcess;
    }

    public final ProcessDetails copy(String str, int i, int i2, boolean z) {
        Intrinsics.checkNotNullParameter(str, "processName");
        return new ProcessDetails(str, i, i2, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ProcessDetails)) {
            return false;
        }
        ProcessDetails processDetails = (ProcessDetails) obj;
        return Intrinsics.areEqual((Object) this.processName, (Object) processDetails.processName) && this.pid == processDetails.pid && this.importance == processDetails.importance && this.isDefaultProcess == processDetails.isDefaultProcess;
    }

    public int hashCode() {
        int hashCode = ((((this.processName.hashCode() * 31) + this.pid) * 31) + this.importance) * 31;
        boolean z = this.isDefaultProcess;
        if (z) {
            z = true;
        }
        return hashCode + (z ? 1 : 0);
    }

    public String toString() {
        return "ProcessDetails(processName=" + this.processName + ", pid=" + this.pid + ", importance=" + this.importance + ", isDefaultProcess=" + this.isDefaultProcess + ')';
    }

    public ProcessDetails(String str, int i, int i2, boolean z) {
        Intrinsics.checkNotNullParameter(str, "processName");
        this.processName = str;
        this.pid = i;
        this.importance = i2;
        this.isDefaultProcess = z;
    }

    public final String getProcessName() {
        return this.processName;
    }

    public final int getPid() {
        return this.pid;
    }

    public final int getImportance() {
        return this.importance;
    }

    public final boolean isDefaultProcess() {
        return this.isDefaultProcess;
    }
}
