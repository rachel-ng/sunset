package com.google.firebase.sessions;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0000\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/google/firebase/sessions/InstallationId;", "", "fid", "", "authToken", "(Ljava/lang/String;Ljava/lang/String;)V", "getAuthToken", "()Ljava/lang/String;", "getFid", "Companion", "com.google.firebase-firebase-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: InstallationId.kt */
public final class InstallationId {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "InstallationId";
    private final String authToken;
    private final String fid;

    public /* synthetic */ InstallationId(String str, String str2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2);
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH@ø\u0001\u0000¢\u0006\u0002\u0010\tR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, d2 = {"Lcom/google/firebase/sessions/InstallationId$Companion;", "", "()V", "TAG", "", "create", "Lcom/google/firebase/sessions/InstallationId;", "firebaseInstallations", "Lcom/google/firebase/installations/FirebaseInstallationsApi;", "(Lcom/google/firebase/installations/FirebaseInstallationsApi;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "com.google.firebase-firebase-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: InstallationId.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARNING: Removed duplicated region for block: B:19:0x0047  */
        /* JADX WARNING: Removed duplicated region for block: B:31:0x008b A[Catch:{ Exception -> 0x0035 }, RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x0029  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object create(com.google.firebase.installations.FirebaseInstallationsApi r9, kotlin.coroutines.Continuation<? super com.google.firebase.sessions.InstallationId> r10) {
            /*
                r8 = this;
                boolean r0 = r10 instanceof com.google.firebase.sessions.InstallationId$Companion$create$1
                if (r0 == 0) goto L_0x0014
                r0 = r10
                com.google.firebase.sessions.InstallationId$Companion$create$1 r0 = (com.google.firebase.sessions.InstallationId$Companion$create$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r1 = r1 & r2
                if (r1 == 0) goto L_0x0014
                int r10 = r0.label
                int r10 = r10 - r2
                r0.label = r10
                goto L_0x0019
            L_0x0014:
                com.google.firebase.sessions.InstallationId$Companion$create$1 r0 = new com.google.firebase.sessions.InstallationId$Companion$create$1
                r0.<init>(r8, r10)
            L_0x0019:
                java.lang.Object r10 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                java.lang.String r3 = ""
                java.lang.String r4 = "InstallationId"
                r5 = 2
                r6 = 1
                if (r2 == 0) goto L_0x0047
                if (r2 == r6) goto L_0x003f
                if (r2 != r5) goto L_0x0037
                java.lang.Object r9 = r0.L$0
                java.lang.String r9 = (java.lang.String) r9
                kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ Exception -> 0x0035 }
                goto L_0x008c
            L_0x0035:
                r10 = move-exception
                goto L_0x0095
            L_0x0037:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r10)
                throw r9
            L_0x003f:
                java.lang.Object r9 = r0.L$0
                com.google.firebase.installations.FirebaseInstallationsApi r9 = (com.google.firebase.installations.FirebaseInstallationsApi) r9
                kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ Exception -> 0x006e }
                goto L_0x005f
            L_0x0047:
                kotlin.ResultKt.throwOnFailure(r10)
                r10 = 0
                com.google.android.gms.tasks.Task r10 = r9.getToken(r10)     // Catch:{ Exception -> 0x006e }
                java.lang.String r2 = "firebaseInstallations.getToken(false)"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r2)     // Catch:{ Exception -> 0x006e }
                r0.L$0 = r9     // Catch:{ Exception -> 0x006e }
                r0.label = r6     // Catch:{ Exception -> 0x006e }
                java.lang.Object r10 = kotlinx.coroutines.tasks.TasksKt.await(r10, r0)     // Catch:{ Exception -> 0x006e }
                if (r10 != r1) goto L_0x005f
                return r1
            L_0x005f:
                com.google.firebase.installations.InstallationTokenResult r10 = (com.google.firebase.installations.InstallationTokenResult) r10     // Catch:{ Exception -> 0x006e }
                java.lang.String r10 = r10.getToken()     // Catch:{ Exception -> 0x006e }
                java.lang.String r2 = "{\n          firebaseInst…).await().token\n        }"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r2)     // Catch:{ Exception -> 0x006e }
                r7 = r10
                r10 = r9
                r9 = r7
                goto L_0x0078
            L_0x006e:
                r10 = move-exception
                java.lang.String r2 = "Error getting authentication token."
                java.lang.Throwable r10 = (java.lang.Throwable) r10
                android.util.Log.w(r4, r2, r10)
                r10 = r9
                r9 = r3
            L_0x0078:
                com.google.android.gms.tasks.Task r10 = r10.getId()     // Catch:{ Exception -> 0x0035 }
                java.lang.String r2 = "firebaseInstallations.id"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r2)     // Catch:{ Exception -> 0x0035 }
                r0.L$0 = r9     // Catch:{ Exception -> 0x0035 }
                r0.label = r5     // Catch:{ Exception -> 0x0035 }
                java.lang.Object r10 = kotlinx.coroutines.tasks.TasksKt.await(r10, r0)     // Catch:{ Exception -> 0x0035 }
                if (r10 != r1) goto L_0x008c
                return r1
            L_0x008c:
                java.lang.String r0 = "{\n          firebaseInst…ions.id.await()\n        }"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r0)     // Catch:{ Exception -> 0x0035 }
                java.lang.String r10 = (java.lang.String) r10     // Catch:{ Exception -> 0x0035 }
                r3 = r10
                goto L_0x009c
            L_0x0095:
                java.lang.String r0 = "Error getting Firebase installation id ."
                java.lang.Throwable r10 = (java.lang.Throwable) r10
                android.util.Log.w(r4, r0, r10)
            L_0x009c:
                com.google.firebase.sessions.InstallationId r10 = new com.google.firebase.sessions.InstallationId
                r0 = 0
                r10.<init>(r3, r9, r0)
                return r10
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.sessions.InstallationId.Companion.create(com.google.firebase.installations.FirebaseInstallationsApi, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    private InstallationId(String str, String str2) {
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
