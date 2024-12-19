package com.google.firebase.sessions.settings;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H@"}, d2 = {"<anonymous>", "", "it", "Lorg/json/JSONObject;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "com.google.firebase.sessions.settings.RemoteSettings$updateSettings$2$1", f = "RemoteSettings.kt", i = {0, 0, 0, 1, 1, 2}, l = {125, 128, 131, 133, 134, 136}, m = "invokeSuspend", n = {"sessionSamplingRate", "sessionTimeoutSeconds", "cacheDuration", "sessionSamplingRate", "cacheDuration", "cacheDuration"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$0"})
/* compiled from: RemoteSettings.kt */
final class RemoteSettings$updateSettings$2$1 extends SuspendLambda implements Function2<JSONObject, Continuation<? super Unit>, Object> {
    /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ RemoteSettings this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteSettings$updateSettings$2$1(RemoteSettings remoteSettings, Continuation<? super RemoteSettings$updateSettings$2$1> continuation) {
        super(2, continuation);
        this.this$0 = remoteSettings;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        RemoteSettings$updateSettings$2$1 remoteSettings$updateSettings$2$1 = new RemoteSettings$updateSettings$2$1(this.this$0, continuation);
        remoteSettings$updateSettings$2$1.L$0 = obj;
        return remoteSettings$updateSettings$2$1;
    }

    public final Object invoke(JSONObject jSONObject, Continuation<? super Unit> continuation) {
        return ((RemoteSettings$updateSettings$2$1) create(jSONObject, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00f5, code lost:
        r13 = (java.lang.Integer) r8.element;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00f9, code lost:
        if (r13 == null) goto L_0x011a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00fb, code lost:
        r2 = r12.this$0;
        r13.intValue();
        r12.L$0 = r1;
        r12.L$1 = r0;
        r12.L$2 = null;
        r12.label = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0117, code lost:
        if (r2.getSettingsCache().updateSessionRestartTimeout((java.lang.Integer) r8.element, r12) != r4) goto L_0x011a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0119, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x011a, code lost:
        r13 = (java.lang.Double) r1.element;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x011e, code lost:
        if (r13 == null) goto L_0x013f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0120, code lost:
        r2 = r12.this$0;
        r13.doubleValue();
        r12.L$0 = r0;
        r12.L$1 = null;
        r12.L$2 = null;
        r12.label = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x013c, code lost:
        if (r2.getSettingsCache().updateSamplingRate((java.lang.Double) r1.element, r12) != r4) goto L_0x013f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x013e, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x013f, code lost:
        r13 = (java.lang.Integer) r0.element;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0143, code lost:
        if (r13 == null) goto L_0x0167;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0145, code lost:
        r1 = r12.this$0;
        r13.intValue();
        r12.L$0 = null;
        r12.L$1 = null;
        r12.L$2 = null;
        r12.label = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0161, code lost:
        if (r1.getSettingsCache().updateSessionCacheDuration((java.lang.Integer) r0.element, r12) != r4) goto L_0x0164;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0163, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0164, code lost:
        r13 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0167, code lost:
        r13 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0168, code lost:
        if (r13 != null) goto L_0x0187;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x016a, code lost:
        r13 = r12.this$0.getSettingsCache();
        r0 = kotlin.coroutines.jvm.internal.Boxing.boxInt(86400);
        r12.L$0 = null;
        r12.L$1 = null;
        r12.L$2 = null;
        r12.label = 5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0184, code lost:
        if (r13.updateSessionCacheDuration(r0, r12) != r4) goto L_0x0187;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0186, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0187, code lost:
        r12.L$0 = null;
        r12.L$1 = null;
        r12.L$2 = null;
        r12.label = 6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x01a5, code lost:
        if (r12.this$0.getSettingsCache().updateSessionCacheUpdatedTime(kotlin.coroutines.jvm.internal.Boxing.boxLong(java.lang.System.currentTimeMillis()), r12) != r4) goto L_0x01a8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x01a7, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x01aa, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00d4  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00f3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        /*
            r12 = this;
            java.lang.String r0 = "cache_duration"
            java.lang.String r1 = "session_timeout_seconds"
            java.lang.String r2 = "sampling_rate"
            java.lang.String r3 = "sessions_enabled"
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r5 = r12.label
            r6 = 0
            switch(r5) {
                case 0: goto L_0x0050;
                case 1: goto L_0x003f;
                case 2: goto L_0x0032;
                case 3: goto L_0x0029;
                case 4: goto L_0x0024;
                case 5: goto L_0x001f;
                case 6: goto L_0x001a;
                default: goto L_0x0012;
            }
        L_0x0012:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r0)
            throw r13
        L_0x001a:
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x01a8
        L_0x001f:
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x0187
        L_0x0024:
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x0164
        L_0x0029:
            java.lang.Object r0 = r12.L$0
            kotlin.jvm.internal.Ref$ObjectRef r0 = (kotlin.jvm.internal.Ref.ObjectRef) r0
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x013f
        L_0x0032:
            java.lang.Object r0 = r12.L$1
            kotlin.jvm.internal.Ref$ObjectRef r0 = (kotlin.jvm.internal.Ref.ObjectRef) r0
            java.lang.Object r1 = r12.L$0
            kotlin.jvm.internal.Ref$ObjectRef r1 = (kotlin.jvm.internal.Ref.ObjectRef) r1
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x011a
        L_0x003f:
            java.lang.Object r0 = r12.L$2
            kotlin.jvm.internal.Ref$ObjectRef r0 = (kotlin.jvm.internal.Ref.ObjectRef) r0
            java.lang.Object r1 = r12.L$1
            kotlin.jvm.internal.Ref$ObjectRef r1 = (kotlin.jvm.internal.Ref.ObjectRef) r1
            java.lang.Object r2 = r12.L$0
            kotlin.jvm.internal.Ref$ObjectRef r2 = (kotlin.jvm.internal.Ref.ObjectRef) r2
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x00f0
        L_0x0050:
            kotlin.ResultKt.throwOnFailure(r13)
            java.lang.Object r13 = r12.L$0
            org.json.JSONObject r13 = (org.json.JSONObject) r13
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r7 = "Fetched settings: "
            r5.<init>(r7)
            r5.append(r13)
            java.lang.String r5 = r5.toString()
            java.lang.String r7 = "SessionConfigFetcher"
            android.util.Log.d(r7, r5)
            kotlin.jvm.internal.Ref$ObjectRef r5 = new kotlin.jvm.internal.Ref$ObjectRef
            r5.<init>()
            kotlin.jvm.internal.Ref$ObjectRef r8 = new kotlin.jvm.internal.Ref$ObjectRef
            r8.<init>()
            kotlin.jvm.internal.Ref$ObjectRef r9 = new kotlin.jvm.internal.Ref$ObjectRef
            r9.<init>()
            java.lang.String r10 = "app_quality"
            boolean r11 = r13.has(r10)
            if (r11 == 0) goto L_0x00d1
            java.lang.Object r13 = r13.get(r10)
            java.lang.String r10 = "null cannot be cast to non-null type org.json.JSONObject"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r13, r10)
            org.json.JSONObject r13 = (org.json.JSONObject) r13
            boolean r10 = r13.has(r3)     // Catch:{ JSONException -> 0x00c7 }
            if (r10 == 0) goto L_0x0099
            java.lang.Object r3 = r13.get(r3)     // Catch:{ JSONException -> 0x00c7 }
            java.lang.Boolean r3 = (java.lang.Boolean) r3     // Catch:{ JSONException -> 0x00c7 }
            goto L_0x009a
        L_0x0099:
            r3 = r6
        L_0x009a:
            boolean r10 = r13.has(r2)     // Catch:{ JSONException -> 0x00c5 }
            if (r10 == 0) goto L_0x00a8
            java.lang.Object r2 = r13.get(r2)     // Catch:{ JSONException -> 0x00c5 }
            java.lang.Double r2 = (java.lang.Double) r2     // Catch:{ JSONException -> 0x00c5 }
            r5.element = r2     // Catch:{ JSONException -> 0x00c5 }
        L_0x00a8:
            boolean r2 = r13.has(r1)     // Catch:{ JSONException -> 0x00c5 }
            if (r2 == 0) goto L_0x00b6
            java.lang.Object r1 = r13.get(r1)     // Catch:{ JSONException -> 0x00c5 }
            java.lang.Integer r1 = (java.lang.Integer) r1     // Catch:{ JSONException -> 0x00c5 }
            r8.element = r1     // Catch:{ JSONException -> 0x00c5 }
        L_0x00b6:
            boolean r1 = r13.has(r0)     // Catch:{ JSONException -> 0x00c5 }
            if (r1 == 0) goto L_0x00d2
            java.lang.Object r13 = r13.get(r0)     // Catch:{ JSONException -> 0x00c5 }
            java.lang.Integer r13 = (java.lang.Integer) r13     // Catch:{ JSONException -> 0x00c5 }
            r9.element = r13     // Catch:{ JSONException -> 0x00c5 }
            goto L_0x00d2
        L_0x00c5:
            r13 = move-exception
            goto L_0x00c9
        L_0x00c7:
            r13 = move-exception
            r3 = r6
        L_0x00c9:
            java.lang.String r0 = "Error parsing the configs remotely fetched: "
            java.lang.Throwable r13 = (java.lang.Throwable) r13
            android.util.Log.e(r7, r0, r13)
            goto L_0x00d2
        L_0x00d1:
            r3 = r6
        L_0x00d2:
            if (r3 == 0) goto L_0x00f3
            com.google.firebase.sessions.settings.RemoteSettings r13 = r12.this$0
            r3.booleanValue()
            com.google.firebase.sessions.settings.SettingsCache r13 = r13.getSettingsCache()
            r12.L$0 = r5
            r12.L$1 = r8
            r12.L$2 = r9
            r0 = 1
            r12.label = r0
            java.lang.Object r13 = r13.updateSettingsEnabled(r3, r12)
            if (r13 != r4) goto L_0x00ed
            return r4
        L_0x00ed:
            r2 = r5
            r1 = r8
            r0 = r9
        L_0x00f0:
            r8 = r1
            r1 = r2
            goto L_0x00f5
        L_0x00f3:
            r1 = r5
            r0 = r9
        L_0x00f5:
            T r13 = r8.element
            java.lang.Integer r13 = (java.lang.Integer) r13
            if (r13 == 0) goto L_0x011a
            com.google.firebase.sessions.settings.RemoteSettings r2 = r12.this$0
            java.lang.Number r13 = (java.lang.Number) r13
            r13.intValue()
            com.google.firebase.sessions.settings.SettingsCache r13 = r2.getSettingsCache()
            T r2 = r8.element
            java.lang.Integer r2 = (java.lang.Integer) r2
            r12.L$0 = r1
            r12.L$1 = r0
            r12.L$2 = r6
            r3 = 2
            r12.label = r3
            java.lang.Object r13 = r13.updateSessionRestartTimeout(r2, r12)
            if (r13 != r4) goto L_0x011a
            return r4
        L_0x011a:
            T r13 = r1.element
            java.lang.Double r13 = (java.lang.Double) r13
            if (r13 == 0) goto L_0x013f
            com.google.firebase.sessions.settings.RemoteSettings r2 = r12.this$0
            java.lang.Number r13 = (java.lang.Number) r13
            r13.doubleValue()
            com.google.firebase.sessions.settings.SettingsCache r13 = r2.getSettingsCache()
            T r1 = r1.element
            java.lang.Double r1 = (java.lang.Double) r1
            r12.L$0 = r0
            r12.L$1 = r6
            r12.L$2 = r6
            r2 = 3
            r12.label = r2
            java.lang.Object r13 = r13.updateSamplingRate(r1, r12)
            if (r13 != r4) goto L_0x013f
            return r4
        L_0x013f:
            T r13 = r0.element
            java.lang.Integer r13 = (java.lang.Integer) r13
            if (r13 == 0) goto L_0x0167
            com.google.firebase.sessions.settings.RemoteSettings r1 = r12.this$0
            java.lang.Number r13 = (java.lang.Number) r13
            r13.intValue()
            com.google.firebase.sessions.settings.SettingsCache r13 = r1.getSettingsCache()
            T r0 = r0.element
            java.lang.Integer r0 = (java.lang.Integer) r0
            r12.L$0 = r6
            r12.L$1 = r6
            r12.L$2 = r6
            r1 = 4
            r12.label = r1
            java.lang.Object r13 = r13.updateSessionCacheDuration(r0, r12)
            if (r13 != r4) goto L_0x0164
            return r4
        L_0x0164:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE
            goto L_0x0168
        L_0x0167:
            r13 = r6
        L_0x0168:
            if (r13 != 0) goto L_0x0187
            com.google.firebase.sessions.settings.RemoteSettings r13 = r12.this$0
            com.google.firebase.sessions.settings.SettingsCache r13 = r13.getSettingsCache()
            r0 = 86400(0x15180, float:1.21072E-40)
            java.lang.Integer r0 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r0)
            r12.L$0 = r6
            r12.L$1 = r6
            r12.L$2 = r6
            r1 = 5
            r12.label = r1
            java.lang.Object r13 = r13.updateSessionCacheDuration(r0, r12)
            if (r13 != r4) goto L_0x0187
            return r4
        L_0x0187:
            com.google.firebase.sessions.settings.RemoteSettings r13 = r12.this$0
            com.google.firebase.sessions.settings.SettingsCache r13 = r13.getSettingsCache()
            long r0 = java.lang.System.currentTimeMillis()
            java.lang.Long r0 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r0)
            r1 = r12
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r12.L$0 = r6
            r12.L$1 = r6
            r12.L$2 = r6
            r2 = 6
            r12.label = r2
            java.lang.Object r13 = r13.updateSessionCacheUpdatedTime(r0, r1)
            if (r13 != r4) goto L_0x01a8
            return r4
        L_0x01a8:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.sessions.settings.RemoteSettings$updateSettings$2$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
