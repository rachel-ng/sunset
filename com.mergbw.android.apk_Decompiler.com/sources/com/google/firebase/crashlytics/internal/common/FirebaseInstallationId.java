package com.google.firebase.crashlytics.internal.common;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u000b\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\u000b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/google/firebase/crashlytics/internal/common/FirebaseInstallationId;", "", "fid", "", "authToken", "(Ljava/lang/String;Ljava/lang/String;)V", "getAuthToken", "()Ljava/lang/String;", "getFid", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "com.google.firebase-firebase-crashlytics"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: FirebaseInstallationId.kt */
public final class FirebaseInstallationId {
    private final String authToken;
    private final String fid;

    public static /* synthetic */ FirebaseInstallationId copy$default(FirebaseInstallationId firebaseInstallationId, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = firebaseInstallationId.fid;
        }
        if ((i & 2) != 0) {
            str2 = firebaseInstallationId.authToken;
        }
        return firebaseInstallationId.copy(str, str2);
    }

    public final String component1() {
        return this.fid;
    }

    public final String component2() {
        return this.authToken;
    }

    public final FirebaseInstallationId copy(String str, String str2) {
        return new FirebaseInstallationId(str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FirebaseInstallationId)) {
            return false;
        }
        FirebaseInstallationId firebaseInstallationId = (FirebaseInstallationId) obj;
        return Intrinsics.areEqual((Object) this.fid, (Object) firebaseInstallationId.fid) && Intrinsics.areEqual((Object) this.authToken, (Object) firebaseInstallationId.authToken);
    }

    public int hashCode() {
        String str = this.fid;
        int i = 0;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.authToken;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "FirebaseInstallationId(fid=" + this.fid + ", authToken=" + this.authToken + ')';
    }

    public FirebaseInstallationId(String str, String str2) {
        this.fid = str;
        this.authToken = str2;
    }

    public final String getAuthToken() {
        return this.authToken;
    }

    public final String getFid() {
        return this.fid;
    }
}
